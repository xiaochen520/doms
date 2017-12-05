package com.echo.action;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.hssf.converter.ExcelToHtmlUtils;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ConditionalFormattingRule;
import org.apache.poi.ss.usermodel.FontFormatting;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.SheetConditionalFormatting;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.struts2.ServletActionContext;

import com.echo.service.CrudeOilService;
import com.echo.util.CommonsUtil;
import com.echo.util.DateBean;
import com.echo.util.ExcelUtil;
import com.echo.util.PropertiesConfig;
import com.echo.util.ReportFormsBaseUitl;
import com.echo.util.StringUtil;
import com.echo.util.U2DataExportUtil;
//import com.opensymphony.xwork2.ActionSupport;

public class CrudeOilAction extends ReportFormsBaseUitl{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
	private CrudeOilService crudeOilService;
	private final String fileName = "原油交接台账.xls";
	
	public void setCrudeOilService(CrudeOilService crudeOilService) {
		this.crudeOilService = crudeOilService;
	}

	public String getFileName() {
		return super.getFileName(fileName);
	}
	
	public String exportCrude() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		String txtYeah = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("SYear")));
		String txtMonth = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("SMonth")));
		String txtDate = DateBean.getYeahMonthO(txtYeah,txtMonth);
		String templetFilePath = request.getSession().getServletContext().getRealPath("/") + "exceltemplet\\combination\\原油交接台账.xls";
		HSSFWorkbook wb = ExcelToHtmlUtils.loadXls( new File( templetFilePath ) );
		HSSFSheet sheet = wb.getSheetAt(0);
		//第一季度：2月-4月 第二季度：5月7月 第三季度：8月-10月 第四季度：11月-1月
		String sheetName = txtYeah;
		int monthValue = Integer.parseInt(txtMonth);
		if(monthValue >= 2 && monthValue <= 4){
			sheetName += ". 一季度外输管线";
		}
		else if(monthValue >= 5 && monthValue <= 7){
			sheetName += ". 二季度外输管线";
		}
		else if(monthValue >= 8 && monthValue <= 10){
			sheetName += ". 三季度外输管线";
		}else{
			sheetName += ". 四季度外输管线";
		}
		wb.setSheetName(0, sheetName);
		PropertiesConfig pc = new PropertiesConfig();
		String fields = pc.getSystemConfiguration("sqlFields");
		
		List<Object[]> list = crudeOilService.searchExportData(txtDate,fields);
		String p = pc.getSystemConfiguration("yunyouInsertP");
		if (list.size() != 0 && list != null) {
			List<Object[]> objDataList = U2DataExportUtil.splitLogData(list);
			CellReference cr = new CellReference(p);
			int startRowNum = cr.getRow();
			Row dataRow = sheet.getRow(startRowNum - 1);
			HSSFFont font;
			
			CellStyle[] sytles = new CellStyle[dataRow.getLastCellNum()];
//			获取各列样式 字体 、小时位数、背景色
			for (int i = 0; i < dataRow.getLastCellNum(); i++) {
				sytles[i] = dataRow.getCell(cr.getCol() + i).getCellStyle();
				ExcelUtil.setStyle(wb, sytles[i]);
			}

			//调用插入数据样式的方法
			U2DataExportUtil.insertDataByPosition(objDataList, wb, sheet, p, sytles);
			Cell dataCell;
			CellStyle style;
			HSSFFont f = wb.getFontAt(dataRow.getCell(0).getCellStyle().getFontIndex());
			font = wb.createFont();
			font.setFontName(f.getFontName());
			font.setFontHeightInPoints(f.getFontHeightInPoints());
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			//设置列头行字体加粗
			style = wb.createCellStyle();
			style.cloneStyleFrom(dataRow.getCell(0).getCellStyle());
			style.setFont(font);
			for (int i = 0; i < dataRow.getPhysicalNumberOfCells(); i++) {
				dataRow.getCell(i).setCellStyle(style);
			}
			
			for (int i = startRowNum; i <= sheet.getLastRowNum(); i++) {
				dataRow = sheet.getRow(i);
				dataCell = dataRow.getCell(0);
				style = wb.createCellStyle();
				style.cloneStyleFrom(dataRow.getCell(0).getCellStyle());
				if ("合计交罐".equals(dataCell.getStringCellValue())) {
					style.setFillForegroundColor(IndexedColors.RED.getIndex());
					style.setFillPattern(CellStyle.SOLID_FOREGROUND);
				}
				style.setFont(font);
				dataCell.setCellStyle(style);
			}
//			设置条件格式 
			SheetConditionalFormatting sheetCF = sheet.getSheetConditionalFormatting();
			ConditionalFormattingRule rule = sheetCF.createConditionalFormattingRule("ISERROR(A35/0)");
			FontFormatting ff = rule.createFontFormatting();
//			ff.setFontStyle(false, true);//字体加粗
			ff.setFontColorIndex(IndexedColors.BLUE.index);
			String rangeAdderssStr = "$E$3:$G$";//开始位置 $E$3:结束位置$G$14
			rangeAdderssStr += sheet.getPhysicalNumberOfRows();
			CellRangeAddress[] regions = { CellRangeAddress.valueOf(rangeAdderssStr) };
			sheetCF.addConditionalFormatting(regions, rule);
		}

		OutputStreamAndCloseBaos(U2DataExportUtil.ExporDataStream(wb));
		return "excel";
	
	}
	
	@SuppressWarnings("unused")
	public String searchCrudeOil() throws Exception{
		
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		
		List<String> date = null;
		//String txtDate = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("txtDate")));
		String txtYeah = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("SYear")));
		String txtMonth = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("SMonth")));
		String txtDate = DateBean.getYeahMonthO(txtYeah,txtMonth);
//			SimpleDateFormat sf= new SimpleDateFormat("yyyy.MM");
//			
//		if(txtYeah != null && !"".equals(txtYeah) && txtMonth != null && !"".equals(txtMonth)){
//			date = DateBean.getYeahMonth(txtYeah, txtMonth);
//		}else{
//			Calendar now = Calendar.getInstance();  
//			date = DateBean.getYeahMonth(String.valueOf(now.get(Calendar.YEAR)), String.valueOf((now.get(Calendar.MONTH) + 1)));
//		
//		}
		
		JSONArray oilAll = null;
		JSONObject  obj = null;
		try {
			oilAll =  crudeOilService.searchCrudeOil(txtDate);
			if(oilAll !=null && oilAll.size()>0){
				obj = new JSONObject();
				obj.put("oilAll", oilAll);
				
				}else{
					obj = CommonsUtil.getRrturnJson("","当前日期没有数据请选择其他日期" ,"", null, null);
					out.print(obj);
					return null;
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		out.print(obj);
		return  null;
	}
}