package com.echo.action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.ServletActionContext;

import com.echo.dto.PcRdLoginfoT;
import com.echo.dto.PcRpdReportMessageT;
import com.echo.dto.PcRpdU2WaterQualityT;
import com.echo.dto.User;
import com.echo.service.LogService;
import com.echo.service.ReportMessageService;
import com.echo.service.U2szjcService;
import com.echo.util.AuthorityUtil;
import com.echo.util.CommonsUtil;
import com.echo.util.DateBean;
import com.echo.util.ExcelUtil;
import com.echo.util.PageVariableUtil;
import com.echo.util.PropertiesConfig;
import com.echo.util.PropertyUtil;
import com.echo.util.StringUtil;
import com.echo.util.U2DataExportUtil;
import com.opensymphony.xwork2.ActionSupport;

public class U2szjcAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;

	HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
	private InputStream excelFile = null;
	private U2szjcService u2szjcService;
	private ReportMessageService reportMessageService;

	public void setReportMessageService(ReportMessageService reportMessageService) {
		this.reportMessageService = reportMessageService;
	}

	

	public void setU2szjcService(U2szjcService u2szjcService) {
		this.u2szjcService = u2szjcService;
	}



	private LogService logService;

	public LogService getLogService() {
		return logService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	
	public String onAudit() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONObject obj = new JSONObject();
		User user1 = (User) session.getAttribute("userInfo");
		String RPDREPORTMESSAGEID = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("RPDREPORTMESSAGEID")));
		List<PcRpdReportMessageT> reportMessage = null;
		PcRpdReportMessageT reportM = null;
		if(RPDREPORTMESSAGEID != null && !"".equals(RPDREPORTMESSAGEID)){
			reportMessage = reportMessageService.SreachReportMessages(RPDREPORTMESSAGEID, "", "");
		}else{
			
			obj = CommonsUtil.getRrturnJson("","未获取到二号稠油联合站原油系统日报数据ID" ,"", null, null);
			out.print(obj);
			return null;
		}
		
		if(reportMessage != null && reportMessage.size()>0){
			reportM = reportMessage.get(0);
		}else{
			obj = CommonsUtil.getRrturnJson("","未获取到二号稠油联合站原油系统日报数据ID对应数据" ,"", null, null);
			out.print(obj);
			return null;
		}
		reportM.setShr(user1.getOperName());
		reportM.setShsj(new Date());

		boolean updateflg = false;
		try {
			updateflg = reportMessageService.updateReportMessages(reportM);
		} catch (Exception e) {
			String  errmsg = e.getCause().toString();
			
			if(!"".equals(errmsg) && errmsg.indexOf("ORA-01438") != -1){
				obj = CommonsUtil.getRrturnJson("","您输入信息超过数据库数据精度范围" ,"", null, null);
			} else{
				obj = CommonsUtil.getRrturnJson("",errmsg ,"", null, null);
			}
			e.printStackTrace();
			out.print(obj);
			return null;
		}
		
		if(updateflg){
			//添加系统LOG
			try {
				PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "3", "原油处理加药", reportM.getRpdReportMessageId());
				logService.addLog(log);
			} catch (Exception e) {
				e.printStackTrace();
				obj = CommonsUtil.getRrturnJson("","原油处理加药日志修改失败" ,"", null, null);
			}
		}
		out.print(obj);
		return null;
	}
	public String updateU2szjc() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONObject obj = new JSONObject();
//		String rowData = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("rowData")));
		
		String REPORT_DATE = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("REPORT_DATE")));
		String REPORT_TIME = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("REPORT_TIME")));
		String HYCJC = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("HYCJC")));
		String HYDJ = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("HYDJ")));
		String HY1_2 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("HY1_2")));
		String HYDC = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("HYDC")));
		String HYFC = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("HYFC")));
		String HYHNC = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("HYHNC")));
		String HYYJJ = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("HYYJJ")));
		String HYYJC = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("HYYJC")));

		String HYEJC = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("HYEJC")));
		String HYRHS = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("HYRHS")));
		String XFCJC = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("XFCJC")));
		String XFDJ = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("XFDJ")));
		String XF1_2 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("XF1_2")));
		String XFDC = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("XFDC")));
		String XFFC = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("XFFC")));
		String XFHNC = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("XFHNC")));
		String XFYJJ = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("XFYJJ")));
		String XFYJC = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("XFYJC")));

		String XFEJC = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("XFEJC")));
		String XFRHS = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("XFRHS")));
		String YDCJC = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("YDCJC")));
		String YDDJ = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("YDDJ")));
		String YDDC = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("YDDC")));
		String YDFC = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("YDFC")));
		String YDHNC = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("YDHNC")));
		String YDYJJ = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("YDYJJ")));
		String HNYJC = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("HNYJC")));
		String HNEJC = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("HNEJC")));

		String RPD_U2_WATER_QUALITY_ID = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("RPD_U2_WATER_QUALITY_ID")));
		
		PcRpdU2WaterQualityT prs = null;
//		JSONArray jsonArr = JSONArray.fromObject(rowData);
		User user = (User) session.getAttribute("userInfo");
//		JSONObject jsonObj = null;
		//请求的long参数
//		String[] jsonArgs = {"HYCJC","HYDJ","HY1_2","HYDC","HYFC","HYHNC","HYYJJ","HYYJC","HYEJC","HYRHS","XFCJC","XFDJ","XF1_2","XFDC","XFFC","XFHNC","XFYJJ","XFYJC","XFEJC","XFRHS","YDCJC","YDDJ","YDDC","YDFC","YDHNC","YDYJJ","HNYJC","HNEJC"};
//		String[] filedName = {"hycjc","hydj","hy12","hydc","hyfc","hyhnc","hyyjj","hyyjc","hyejc","hyrhs","xfcjc","xfdj","xf12","xfdc","xffc","xfhnc","xfyjj","xfyjc","xfejc","xfrhs","ydcjc","yddj","yddc","ydfc","ydhnc","ydyjj","hnyjc","hnejc"};
//		for (Object jo : jsonArr) {
//			jsonObj = JSONObject.fromObject(jo);
			if (RPD_U2_WATER_QUALITY_ID != null && !"".equals(RPD_U2_WATER_QUALITY_ID)) {
				List<PcRpdU2WaterQualityT> pros = null;
				//RPD_U2_WATER_QUALITY_ID = jsonObj.get("RPD_U2_WATER_QUALITY_ID").toString();
				try {
					pros = u2szjcService.searchU2WaterQuality(RPD_U2_WATER_QUALITY_ID);
				} catch (Exception e) {
					e.printStackTrace();
					obj = CommonsUtil.getRrturnJson("","水质监测数据ID数据获取失败，请联系管理员" ,"", null, null);
					out.print(obj);
					return null;
				}
				
				if(pros != null && pros.size()>0){
					prs = pros.get(0);
					
					prs.setHycjc(CommonsUtil.getBigDecimalData(HYCJC));
					prs.setHydj(CommonsUtil.getBigDecimalData(HYDJ));
					prs.setHy12(CommonsUtil.getBigDecimalData(HY1_2));
					prs.setHydc(CommonsUtil.getBigDecimalData(HYDC));
					prs.setHyfc(CommonsUtil.getBigDecimalData(HYFC));
					prs.setHyhnc(CommonsUtil.getBigDecimalData(HYHNC));
					prs.setHyyjj(CommonsUtil.getBigDecimalData(HYYJJ));
					prs.setHyyjc(CommonsUtil.getBigDecimalData(HYYJC));
					prs.setHyejc(CommonsUtil.getBigDecimalData(HYEJC));
					prs.setHyrhs(CommonsUtil.getBigDecimalData(HYRHS));
					prs.setXfcjc(CommonsUtil.getBigDecimalData(XFCJC));
					prs.setXfdj(CommonsUtil.getBigDecimalData(XFDJ));
					prs.setXf12(CommonsUtil.getBigDecimalData(XF1_2));
					prs.setXfdc(CommonsUtil.getBigDecimalData(XFDC));
					prs.setXffc(CommonsUtil.getBigDecimalData(XFFC));
					prs.setXfhnc(CommonsUtil.getBigDecimalData(XFHNC));
					prs.setXfyjj(CommonsUtil.getBigDecimalData(XFYJJ));
					prs.setXfyjc(CommonsUtil.getBigDecimalData(XFYJC));
					prs.setXfejc(CommonsUtil.getBigDecimalData(XFEJC));
					prs.setXfrhs(CommonsUtil.getBigDecimalData(XFRHS));
					prs.setYdcjc(CommonsUtil.getBigDecimalData(YDCJC));
					prs.setYddj(CommonsUtil.getBigDecimalData(YDDJ));
					prs.setYddc(CommonsUtil.getBigDecimalData(YDDC));
					prs.setYdfc(CommonsUtil.getBigDecimalData(YDFC));
					prs.setYdhnc(CommonsUtil.getBigDecimalData(YDHNC));
					prs.setYdyjj(CommonsUtil.getBigDecimalData(YDYJJ));
					prs.setHnyjc(CommonsUtil.getBigDecimalData(HNYJC));
					prs.setHnejc(CommonsUtil.getBigDecimalData(HNEJC));
//					String argVal = "";
//					for (int i = 0; i < jsonArgs.length; i++) {
//						try {
//							argVal = "";
//							//为对象赋值
//							if (jsonObj.get(jsonArgs[i].toString()) != null) {
//								argVal = jsonObj.get(jsonArgs[i].toString()).toString();
//								argVal = PropertyUtil.removeColor(argVal);
//								if(!"".equals(argVal)){
//									
//									PropertyUtil.setProperty(prs, filedName[i], java.math.BigDecimal.valueOf(Double.parseDouble(argVal)));
//								}else{
//									PropertyUtil.setProperty(prs, filedName[i], null);
//								}
//								
//							}else {
//								PropertyUtil.setProperty(prs, filedName[i], null);
//							}
//							
//						} catch (Exception e) {
//							e.printStackTrace();
//							obj = CommonsUtil.getRrturnJson("","数据转换失败，请检查输入是否有误" ,"", null, null);
//							out.print(obj);
//							return null;
//						}
//						
//						
//					}
					
					prs.setRlastOper(user.getOperName());
					prs.setRlastOdate(new Date(System.currentTimeMillis()));
					
					
					boolean flg = false;
					try {
						flg = u2szjcService.addOrUpdateData(prs);
					} catch (Exception e) {
						e.printStackTrace();
						obj = CommonsUtil.getRrturnJson("","水质监测数据更新失败" ,"", null, null);
						out.print(obj);
						return null;
					}
					
					if(flg){
						//添加系统LOG
						try {
							PcRdLoginfoT log = CommonsUtil.getLogInfo(user, "3", "U2联合站水质监测", RPD_U2_WATER_QUALITY_ID);
							logService.addLog(log);
						} catch (Exception e) {
							e.printStackTrace();
							obj = CommonsUtil.getRrturnJson("","稠油2号联合站水质监测数据更新LOG添加失败" ,"", null, null);
							out.print(obj);
							return null;
						}
					}
					
					
				}else{
					obj = CommonsUtil.getRrturnJson("","水质监测数据ID数据获取失败，请联系管理员" ,"", null, null);
					out.print(obj);
					return null;
				}
				
			}else{
				obj = CommonsUtil.getRrturnJson("","您要修改的数据未获取到水质监测数据ID,请重新选择数据" ,"", null, null);
				out.print(obj);
				return null;
			}
			
//		}
		
		out.print(obj);
		return null;
	}
	public InputStream getExcelFile() {
		return excelFile;
	}
	public void setExcelFile(InputStream excelFile) {
		this.excelFile = excelFile;
	}
	
	public String getFileName() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd ");
		String downloadFileName = (sf.format(new Date()).toString())+ "二号稠油联合处理站-水质监测报表.xls";
		try {
			downloadFileName = new String(downloadFileName.getBytes(),"ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return downloadFileName;
	}
	public String searchU2SZJC() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONObject obj = null;
		String txtYeah = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("SYear")));
		String txtMonth = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("SMonth")));
		List<String> date = null;
		JSONObject u2szjcs = null;
		if(txtYeah != null && !"".equals(txtYeah) && txtMonth != null && !"".equals(txtMonth)){
			date = DateBean.getYeahMonth(txtYeah, txtMonth);
		}else{
			Calendar now = Calendar.getInstance();  
			date = DateBean.getYeahMonth(String.valueOf(now.get(Calendar.YEAR)), String.valueOf((now.get(Calendar.MONTH) + 1)));
		
		}
		
		try {
			u2szjcs = u2szjcService.searchU2szjc(date);
			obj = CommonsUtil.getRrturnJson("","" ,"", u2szjcs, null);
		} catch (Exception e) {
			e.printStackTrace();
			obj = CommonsUtil.getRrturnJson("","二号稠油联合站水质监测数据获取失败" ,"", null, null);
			out.print(obj);
			return null;
		}

		out.print(u2szjcs);
		
	return null;
	
	
	
}
	
	
	
	public String exportU2_SZJC() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		String txtYeah = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("SYear")));
		String txtMonth = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("SMonth")));
		
		boolean flag = false;  //数据比较返回值
		
		String templetFilePath = request.getSession().getServletContext().getRealPath("/") + "exceltemplet\\combination\\二号联合站\\二号稠油联合处理站-水质监测报表.xls";
		HSSFWorkbook wb = ExcelToHtmlUtils.loadXls( new File( templetFilePath ) );
		HSSFSheet sheet = wb.getSheetAt(0);
		
		String temp = txtYeah+"."+txtMonth;
		wb.setSheetName(0, temp);
		
		List<String> date = null;
		if(txtYeah != null && !"".equals(txtYeah) && txtMonth != null && !"".equals(txtMonth)){
			date = DateBean.getYeahMonth(txtYeah, txtMonth);
		}else{
			Calendar now = Calendar.getInstance();  
			date = DateBean.getYeahMonth(String.valueOf(now.get(Calendar.YEAR)), String.valueOf((now.get(Calendar.MONTH) + 1)));
		
		}
		
		
		
		PropertiesConfig pc = new PropertiesConfig();
		String fields = pc.getSystemConfiguration("U2UZJCSQL");
		
		List<Object[]> list = u2szjcService.searchExportData(date,fields);
		String U2UZJC_START_INSERT = pc.getSystemConfiguration("U2UZJC_START_INSERT");
		
		if (list.size() != 0 && list != null) {
			PropertiesConfig propertiesConfig = new PropertiesConfig();//写在adction 里 在查询拼接sql及导出报表时都要用到
			
			
			CellStyle cs1 = wb.createCellStyle();
			cs1 = ExcelUtil.getDataStyle(cs1);
			
			
			List<Object[]> objDataList = U2DataExportUtil.splitU2szjcData(list);
			U2DataExportUtil.insertDataByPosition(objDataList, wb, sheet, U2UZJC_START_INSERT);
			
			CellReference cr = new CellReference(U2UZJC_START_INSERT);
			int startRowNum = cr.getRow();
			Row dataRow;
			sheet.getLastRowNum();
			Cell dataCell;
			
//			//获取数据范围值
//			String[] compreCloumnValue = new String[sheet.getRow(0).getLastCellNum()];
//			compreCloumnValue[3] = pc.getSystemConfiguration("HYDJ");
//			compreCloumnValue[4] = pc.getSystemConfiguration("HY1_2");
//			compreCloumnValue[5] = pc.getSystemConfiguration("HYDC");
//			compreCloumnValue[6] = pc.getSystemConfiguration("HYFC");
//			compreCloumnValue[7] = pc.getSystemConfiguration("HYHNC");
//			compreCloumnValue[8] = pc.getSystemConfiguration("HYYJJ");
//			compreCloumnValue[9] = pc.getSystemConfiguration("HYYJC");
//			compreCloumnValue[10] = pc.getSystemConfiguration("HYEJC");
//			compreCloumnValue[11] = pc.getSystemConfiguration("HYRHS");
//			
//			compreCloumnValue[13] = pc.getSystemConfiguration("XFDJ");
//			compreCloumnValue[14] = pc.getSystemConfiguration("XF1_2");
//			compreCloumnValue[15] = pc.getSystemConfiguration("XFDC");
//			compreCloumnValue[16] = pc.getSystemConfiguration("XFFC");
//			compreCloumnValue[17] = pc.getSystemConfiguration("XFHNC");
//			compreCloumnValue[18] = pc.getSystemConfiguration("XFYJJ");
//			compreCloumnValue[19] = pc.getSystemConfiguration("XFYJC");
//			compreCloumnValue[20] = pc.getSystemConfiguration("XFEJC");
//			compreCloumnValue[21] = pc.getSystemConfiguration("XFRHS");
			int k = objDataList.get(0).length;
			for (int i = startRowNum; i < objDataList.size()+startRowNum; i++) {
				dataRow = sheet.getRow(i);
				dataCell = dataRow.getCell(0);
				if (dataCell.getStringCellValue() == null || "".equals(dataCell.getStringCellValue())) {
					CellStyle style = wb.createCellStyle();
//					style = ExcelUtil.getDataStyle(style);
					// 设置水平对齐的样式为居中对齐;
//					style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
					// 设置垂直对齐的样式为居中对齐;
					ExcelUtil.setStyle(wb, style);
//					style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
					style.setFillForegroundColor(IndexedColors.ROSE.getIndex());
				    style.setFillPattern(CellStyle.SOLID_FOREGROUND);
				    for (int j = 0; j < k; j++) {
				    	dataRow.getCell(j).setCellStyle(style);
					}
				}
//				else{
//					for (int j = 0; j < dataRow.getLastCellNum(); j++) {
//						
//						if(j != 0 && j != 1 && j != 2 && j != 12 && j != 22 && j != 23 && j != 24 && j != 25 && j != 26&& j != 27&& j != 28&& j != 29){
//						
//							flag= CommonsUtil.U2compreData(dataRow.getCell(j).getNumericCellValue(), compreCloumnValue[j]);
//							//字体设置为红色
//							if(flag){
//								
//								ExcelUtil.setStyle(wb, cs1);
//								HSSFFont font = wb.createFont();
//							    font.setColor(HSSFColor.RED.index);
//							    cs1.setFont(font);
//								dataRow.getCell(j).setCellStyle(cs1);
//								
//							}
//							
//						}
//						
//					}
//					
//				}
//				CellStyle cs = wb.createCellStyle();
//				cs = ExcelUtil.getDataStyle(cs);
//				ExcelUtil.setStyle(wb, cs);
//				// 设置水平对齐的样式为居中对齐;
//				cs.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//				// 设置垂直对齐的样式为居中对齐;
//				cs.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
//				HSSFFont font = wb.createFont();
//			    font.setColor(HSSFColor.BLUE.index);
//			    cs.setFont(font);
//
//				dataRow.getCell(4).setCellStyle(cs);
//				dataRow.getCell(5).setCellStyle(cs);
//				dataRow.getCell(6).setCellStyle(cs);
//				
//				CellStyle style = wb.createCellStyle();
//				style = ExcelUtil.getDataStyle(style);
//				// 设置水平对齐的样式为居中对齐;
//				style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//				// 设置垂直对齐的样式为居中对齐;
//				ExcelUtil.setStyle(wb, style);
//				style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
//				style.setFillForegroundColor(IndexedColors.TURQUOISE.getIndex());
//			    style.setFillPattern(CellStyle.SOLID_FOREGROUND);
//			    dataRow.getCell(9).setCellStyle(style);
//			    dataRow.getCell(10).setCellStyle(style);
			}
			

//			HSSFSheetConditionalFormatting scf = sheet.getSheetConditionalFormatting(); 
			
//			scf = ExcelUtil.setFormula(scf, "COUNTBLANK(D4:D11)>0", HSSFColor.RED.index, "D4:D35","3000");
			
			
		}
		
		
		
		
		java.io.ByteArrayOutputStream baos = U2DataExportUtil.ExporDataStream(wb);
		if(baos != null){
			byte[] ba = baos.toByteArray();
			excelFile = new ByteArrayInputStream(ba);
			baos.flush();
			baos.close();
		}
		return "excel";
	}
	
	
	public String pageInit()throws Exception{
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		//根据用户权限生成用户权限生成页面布局格式
		User user = (User) session.getAttribute("userInfo");
//		
		String gridJson = null;
		try {
			gridJson = AuthorityUtil.getGridJson1("MENU088", user, PageVariableUtil.U2_WATER_QUALITY);
		} catch (Exception e) {
			e.printStackTrace();
			outCode = "-10004";
		}
		
		if(gridJson != null && "1".equals(outCode)){
			//System.out.println(gridJson);
			out.print(gridJson);
		}else{
			out.print(outCode);
		}
		return null;
	}
}
