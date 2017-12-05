package com.echo.action;

import java.io.File;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.poi.hssf.converter.ExcelToHtmlUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.ServletActionContext;

import com.echo.dto.PcRdLoginfoT;
import com.echo.dto.PcRpdU1WaterQuality1T;
import com.echo.dto.User;
import com.echo.service.LogService;
import com.echo.service.U1szjcService;
import com.echo.util.AuthorityUtil;
import com.echo.util.CommonsUtil;
import com.echo.util.DateBean;
import com.echo.util.ExcelUtil;
import com.echo.util.PageVariableUtil;
import com.echo.util.PropertiesConfig;
import com.echo.util.ReportFormsBaseUitl;
import com.echo.util.StringUtil;
import com.echo.util.U2DataExportUtil;

public class U1Q1szjcAction extends ReportFormsBaseUitl {
	
	private static final long serialVersionUID = 1L;

	HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
	private U1szjcService u1szjcService;


	public void setU1szjcService(U1szjcService u1szjcService) {
		this.u1szjcService = u1szjcService;
	}


	private LogService logService;

	public LogService getLogService() {
		return logService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	
	private final String fileName = "一号稠油联合处理站水一区水质监测记录报表.xls";
	
	public String getFileName() {
		return super.getFileName(fileName);
	}
	
//	public String onAudit() throws Exception {
//		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
//		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
//		response.setCharacterEncoding("utf-8");
//		response.setHeader("ContentType","text/xml");
//		PrintWriter out = response.getWriter();
//		JSONObject obj = new JSONObject();
//		User user1 = (User) session.getAttribute("userInfo");
//		String RPDREPORTMESSAGEID = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("RPDREPORTMESSAGEID")));
//		List<PcRpdReportMessageT> reportMessage = null;
//		PcRpdReportMessageT reportM = null;
//		if(RPDREPORTMESSAGEID != null && !"".equals(RPDREPORTMESSAGEID)){
//			reportMessage = reportMessageService.SreachReportMessages(RPDREPORTMESSAGEID, "", "");
//		}else{
//			
//			obj = CommonsUtil.getRrturnJson("","未获取到二号稠油联合站原油系统日报数据ID" ,"", null, null);
//			out.print(obj);
//			return null;
//		}
//		
//		if(reportMessage != null && reportMessage.size()>0){
//			reportM = reportMessage.get(0);
//		}else{
//			obj = CommonsUtil.getRrturnJson("","未获取到二号稠油联合站原油系统日报数据ID对应数据" ,"", null, null);
//			out.print(obj);
//			return null;
//		}
//		reportM.setShr(user1.getOperName());
//		reportM.setShsj(new Date());
//
//		boolean updateflg = false;
//		try {
//			updateflg = reportMessageService.updateReportMessages(reportM);
//		} catch (Exception e) {
//			String  errmsg = e.getCause().toString();
//			
//			if(!"".equals(errmsg) && errmsg.indexOf("ORA-01438") != -1){
//				obj = CommonsUtil.getRrturnJson("","您输入信息超过数据库数据精度范围" ,"", null, null);
//			} else{
//				obj = CommonsUtil.getRrturnJson("",errmsg ,"", null, null);
//			}
//			e.printStackTrace();
//			out.print(obj);
//			return null;
//		}
//		
//		if(updateflg){
//			//添加系统LOG
//			try {
//				PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "3", "原油处理加药", reportM.getRpdReportMessageId());
//				logService.addLog(log);
//			} catch (Exception e) {
//				e.printStackTrace();
//				obj = CommonsUtil.getRrturnJson("","原油处理加药日志修改失败" ,"", null, null);
//			}
//		}
//		out.print(obj);
//		return null;
//	}
	public String updateU2szjc() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONObject obj = new JSONObject();
		
//		String BBRQ = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("rowdata.BBRQ")));
//		String BBSJ = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("rowdata.BBSJ")));
		String HYTCGJK = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("HYTCGJK")));
		String HYCJG11GCS = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("HYCJG11GCS")));
		String HYSAGDCS = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("HYSAGDCS")));
		String HYXLJK = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("HYXLJK")));
		String HYXLCK = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("HYXLCK")));
		String HYG2_1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("HYG2_1")));
		String HYTCGCK = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("HYTCGCK")));
		String HYGYGCK = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("HYGYGCK")));
		String HYXBGCK = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("HYXBGCK")));
		String HYYJJK = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("HYYJJK")));
		String HYYKCK = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("HYYKCK")));
		String HYEJCK = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("HYEJCK")));
		String XFWTCGJK = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("XFWTCGJK")));
		String XFWCJGGCS = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("XFWCJGGCS")));
		String XFWXLJK = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("XFWXLJK")));
		String XFWXLCK1J = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("XFWXLCK1J")));
		String XFWG2_1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("XFWG2_1")));
		String XFWTCGCK = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("XFWTCGCK")));
		String XFWFYGCK = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("XFWFYGCK")));
		String XFWXBGCK = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("XFWXBGCK")));
		String XFWYJJK = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("XFWYJJK")));
		String XFWYJCK = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("XFWYJCK")));
		String XFWEJCK = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("XFWEJCK")));
		String YDFYGCK = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("YDFYGCK")));
		String YDEJCK = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("YDEJCK")));
		String RPD_U1_WATER_QUALITY1_ID = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("RPD_U1_WATER_QUALITY1_ID")));
		         
		String YDTCGJK = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("YDTCGJK")));
		String YD2_1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("YD2_1")));
		String YDTCGCK = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("YDTCGCK")));
		String YDXBGCK = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("YDXBGCK")));
		String YDYJJK = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("YDYJJK")));
		String YDYJCK = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("YDYJCK")));
		String GTCGJK = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("GTCGJK")));
		String GFYQCK = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("GFYQCK")));
		String GXBGCK = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("GXBGCK")));
		String BZ = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("BZ")));

		PcRpdU1WaterQuality1T prs = null;
		User user = (User) session.getAttribute("userInfo");
		JSONObject jsonObj = null;
		//请求的long参数
//		String[] jsonArgs = {"HYCJC","HYDJ","HY1_2","HYDC","HYFC","HYHNC","HYYJJ","HYYJC","HYEJC","HYRHS","XFCJC","XFDJ","XF1_2","XFDC","XFFC","XFHNC","XFYJJ","XFYJC","XFEJC","XFRHS","YDCJC","YDDJ","YDDC","YDFC","YDHNC","YDYJJ","HNYJC","HNEJC"};
//		String[] filedName = {"hycjc","hydj","hy12","hydc","hyfc","hyhnc","hyyjj","hyyjc","hyejc","hyrhs","xfcjc","xfdj","xf12","xfdc","xffc","xfhnc","xfyjj","xfyjc","xfejc","xfrhs","ydcjc","yddj","yddc","ydfc","ydhnc","ydyjj","hnyjc","hnejc"};
//		for (Object jo : jsonArr) {
//			jsonObj = JSONObject.fromObject(jo);
			if (RPD_U1_WATER_QUALITY1_ID != null && !"".equals(RPD_U1_WATER_QUALITY1_ID)) {
				List<PcRpdU1WaterQuality1T> pros = null;
				try {
					pros = u1szjcService.searchU2WaterQuality(RPD_U1_WATER_QUALITY1_ID);
				} catch (Exception e) {
					e.printStackTrace();
					obj = CommonsUtil.getRrturnJson("","一号稠油联合处理站水一区水质监测记录数据获取失败，请联系管理员" ,"", null, null);
					out.print(obj);
					return null;
				}
				
				if(pros != null && pros.size()>0){
					prs = pros.get(0);
					
					prs.setHytcgjk(CommonsUtil.getBigDecimalData(HYTCGJK));
					prs.setHycjg11gcs(CommonsUtil.getBigDecimalData(HYCJG11GCS));
					prs.setHysagdcs(CommonsUtil.getBigDecimalData(HYSAGDCS));
					prs.setHyxljk(CommonsUtil.getBigDecimalData(HYXLJK));
					prs.setHyxlck(CommonsUtil.getBigDecimalData(HYXLCK));
					prs.setHyg21(CommonsUtil.getBigDecimalData(HYG2_1));
					prs.setHytcgck(CommonsUtil.getBigDecimalData(HYTCGCK));
					prs.setHygygck(CommonsUtil.getBigDecimalData(HYGYGCK));
					prs.setHyxbgck(CommonsUtil.getBigDecimalData(HYXBGCK));
					prs.setHyyjjk(CommonsUtil.getBigDecimalData(HYYJJK));
					prs.setHyykck(CommonsUtil.getBigDecimalData(HYYKCK));
					prs.setHyejck(CommonsUtil.getBigDecimalData(HYEJCK));
					prs.setXfwtcgjk(CommonsUtil.getBigDecimalData(XFWTCGJK));
					prs.setXfwcjggcs(CommonsUtil.getBigDecimalData(XFWCJGGCS));
					prs.setXfwxljk(CommonsUtil.getBigDecimalData(XFWXLJK));
					prs.setXfwxlck1j(CommonsUtil.getBigDecimalData(XFWXLCK1J));
					prs.setXfwg21(CommonsUtil.getBigDecimalData(XFWG2_1));
					prs.setXfwtcgck(CommonsUtil.getBigDecimalData(XFWTCGCK));
					prs.setXfwfygck(CommonsUtil.getBigDecimalData(XFWFYGCK));
					prs.setXfwxbgck(CommonsUtil.getBigDecimalData(XFWXBGCK));
					prs.setXfwyjjk(CommonsUtil.getBigDecimalData(XFWYJJK));
					prs.setXfwyjck(CommonsUtil.getBigDecimalData(XFWYJCK));
					prs.setXfwejck(CommonsUtil.getBigDecimalData(XFWEJCK));
					prs.setYdfygck(CommonsUtil.getBigDecimalData(YDFYGCK));
					prs.setYdejck(CommonsUtil.getBigDecimalData(YDEJCK));
					        
					prs.setYDTCGJK(CommonsUtil.getBigDecimalData(YDTCGJK));
					prs.setYD2_1(CommonsUtil.getBigDecimalData(YD2_1));
					prs.setYDTCGCK(CommonsUtil.getBigDecimalData(YDTCGCK));
					prs.setYDXBGCK(CommonsUtil.getBigDecimalData(YDXBGCK));
					prs.setYDYJJK(CommonsUtil.getBigDecimalData(YDYJJK));
					prs.setYDYJCK(CommonsUtil.getBigDecimalData(YDYJCK));
					prs.setGTCGJK(CommonsUtil.getBigDecimalData(GTCGJK));
					prs.setGFYQCK(CommonsUtil.getBigDecimalData(GFYQCK));
					prs.setGXBGCK(CommonsUtil.getBigDecimalData(GXBGCK));
					prs.setBZ(BZ);
					prs.setRlastOper(user.getOperName());
					prs.setRlastOdate(new Date(System.currentTimeMillis()));
					
					
					boolean flg = false;
					try {
						flg = u1szjcService.addOrUpdateData(prs);
					} catch (Exception e) {
						e.printStackTrace();
						obj = CommonsUtil.getRrturnJson("","水质监测数据更新失败" ,"", null, null);
						out.print(obj);
						return null;
					}
					
					if(flg){
						//添加系统LOG
						try {
							PcRdLoginfoT log = CommonsUtil.getLogInfo(user, "3", "U1水一区水质监测", RPD_U1_WATER_QUALITY1_ID);
							logService.addLog(log);
						} catch (Exception e) {
							e.printStackTrace();
							obj = CommonsUtil.getRrturnJson("","稠油1号联合站水一区水质监测数据更新LOG添加失败" ,"", null, null);
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
			u2szjcs = u1szjcService.searchU2szjc(date);
			obj = CommonsUtil.getRrturnJson("","" ,"", u2szjcs, null);
		} catch (Exception e) {
			e.printStackTrace();
			obj = CommonsUtil.getRrturnJson("","一号稠油联合处理站水一区水质监测数据获取失败" ,"", null, null);
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
		
		String templetFilePath = request.getSession().getServletContext().getRealPath("/") + "exceltemplet\\combination\\一号联合站\\一号稠油联合处理站水一区水质监测记录报表.xls";
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
		String fields = pc.getSystemConfiguration("U1S1SZJCSQL");
		
		List<Object[]> list = u1szjcService.searchExportData(date,fields);
		String U2UZJC_START_INSERT = pc.getSystemConfiguration("U1S1SZJC_START_INSERT");
		
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
					style.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.getIndex());
				    style.setFillPattern(CellStyle.SOLID_FOREGROUND);
					
//					style.setFillBackgroundColor(HSSFColor.LIGHT_TURQUOISE.index);
//					style.setFillPattern(CellStyle.SOLID_FOREGROUND);
				    
				    for (int j = 0; j <k; j++) {
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
		
		
		
		
		OutputStreamAndCloseBaos(U2DataExportUtil.ExporDataStream(wb));
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
			gridJson = AuthorityUtil.getGridJson1("MENU116", user, PageVariableUtil.U1_WATER_QUALITY1);
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
