package com.echo.action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.hssf.converter.ExcelToHtmlUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import com.echo.dto.PcRdLoginfoT;
import com.echo.dto.PcRpdReportMessageT;
import com.echo.dto.User;
import com.echo.service.LineMeasureService;
import com.echo.service.LogService;
import com.echo.service.ReportMessageService;
import com.echo.util.AuthorityUtil;
import com.echo.util.CommonsUtil;
import com.echo.util.DateBean;
import com.echo.util.PropertiesConfig;
import com.echo.util.StringUtil;
import com.echo.util.U2DataExportUtil;
import com.opensymphony.xwork2.ActionSupport;
		//稀油注输分线计量
public class LineMeasureXyAction extends ActionSupport{
	/**
	 * 
	 */
	private ReportMessageService reportMessageService;
	private LineMeasureService LineMeasureService;
	private LogService logService;
	private InputStream excelFile = null;
	
	public InputStream getExcelFile() {
		return excelFile;
	}

	public void setLineMeasureService(LineMeasureService LineMeasureService) {
		this.LineMeasureService = LineMeasureService;
	}

	public void setReportMessageService(ReportMessageService reportMessageService) {
		this.reportMessageService = reportMessageService;
	}

	private static final long serialVersionUID = 1L;
	HttpSession  session = ServletActionContext.getRequest().getSession(true); 
	
	public LogService getLogService() {
		return logService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	
	public String getFileName() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd ");
		String downloadFileName = (sf.format(new Date()).toString())+ "稀油注输联合站分线计量日报表.xls";
		try {
			downloadFileName = new String(downloadFileName.getBytes(),"ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return downloadFileName;
	}
	
	//分线计量2导出
	public String exportLineMeasure() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		String txtDate = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("txtDate")));
		if ("".equals(txtDate)) {
			txtDate = DateBean.getSystemTime1();
//			return null;
		}
		PropertiesConfig pc = new PropertiesConfig();
		String fields = pc.getSystemConfiguration("lineMeasureFieldsXY");
		List<PcRpdReportMessageT> reportMessage = reportMessageService.SreachReportMessages("", "稀油注输联合站分线计量日报表",txtDate);//值班人、审核人、日期、备注
//		获取值班人、审核人、日期、备注数据插入位置
		String U2_WATERWatch = pc.getSystemConfiguration("lineMeasureWXY");
		String U2_WATERAuditor = pc.getSystemConfiguration("lineMeasureAXY");
		String U2_WATERReportTime = pc.getSystemConfiguration("lineReportTimeXY");
		String U2_WATERremark = pc.getSystemConfiguration("lineMeasureRXY");
		String templetFilePath = request.getSession().getServletContext().getRealPath("/") + "exceltemplet\\combination\\稀油注输联合站分线计量日报表.xls";
		HSSFWorkbook wb = ExcelToHtmlUtils.loadXls( new File( templetFilePath ) );
		HSSFSheet sheet = wb.getSheetAt(0);
		List<Object[]> lineMeasureList = LineMeasureService.searchLineMeasureXY(txtDate, fields);
		U2DataExportUtil.insertExcelData(wb, sheet, U2_WATERReportTime, 0, new Object[]{txtDate});
//		HSSFWorkbook wb = U2DataExportUtil.BranchingGrouping(lineMeasureList, templetFilePath, positions, dates, lineMeasure, txtDate);
		if(reportMessage.size()>0){
			if (!"".equals(StringUtil.isNullUtil(reportMessage.get(0).getZbr1()))) {
				U2DataExportUtil.insertExcelData(wb, sheet, U2_WATERWatch, 0, new Object[]{reportMessage.get(0).getZbr1()});
			}
			if (!"".equals(StringUtil.isNullUtil(reportMessage.get(0).getShr()))) {
				U2DataExportUtil.insertExcelData(wb, sheet, U2_WATERAuditor, 0, new Object[]{reportMessage.get(0).getShr()});
			}
//			if (null != reportMessage.get(0).getRq()) {
//				
//			}
			if (!"".equals(StringUtil.isNullUtil(reportMessage.get(0).getBz()))) {
				U2DataExportUtil.insertExcelData(wb, sheet, U2_WATERremark, 0, new Object[]{reportMessage.get(0).getBz()});
			}
		}
		if ( lineMeasureList == null || lineMeasureList.size() == 0 ) {
			java.io.ByteArrayOutputStream baos = U2DataExportUtil.ExporDataStream(wb);
			if(baos != null){
				byte[] ba = baos.toByteArray();
				excelFile = new ByteArrayInputStream(ba);
				baos.flush();
				baos.close();
			}
			return "excel";
		}
		//分段时间
		String[] timeStr = pc.getSystemConfiguration("time10").split("&");
		String[][] dates = new String[3][];
		for (int i = 0; i < dates.length; i++) {
			dates[i] = timeStr[i].split(",");
		}
		U2DataExportUtil.setDates(txtDate, dates);
		//插入数据位置
		String[] lineMeasurePositions = pc.getSystemConfiguration("lineMeasureDataInsertPostionsXY").split(",");
		
//		String[] lineNames = pc.getSystemConfiguration("lineNames").split(",");
		
		U2DataExportUtil.TemporalGrouping(wb, sheet, lineMeasureList, dates, lineMeasurePositions, txtDate);

		
		wb.setForceFormulaRecalculation(true);
//		String[] U2_OIL_calcFlag = pc.getSystemConfiguration("U2_OIL_calcFlag").split(",");
//		String[] U2_OIL_calcValue = pc.getSystemConfiguration("U2_OIL_calcValue").split(",");
//		HSSFFormulaEvaluator eval = new HSSFFormulaEvaluator(wb);
//		U2DataExportUtil.calcVale(sheet, U2_OIL_calcFlag, U2_OIL_calcValue, eval);
		java.io.ByteArrayOutputStream baos = U2DataExportUtil.ExporDataStream(wb);
		if(baos != null){
			byte[] ba = baos.toByteArray();
			excelFile = new ByteArrayInputStream(ba);
			baos.flush();
			baos.close();
		}
		return "excel";
	}
	public String updateRPDREPORTMESSAGE() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONObject obj = new JSONObject();
		//data: {"RPDREPORTMESSAGEID":RPDREPORTMESSAGEID,"SHR":SHR,"ZBR1":ZBR1,"BZ":BZ,"RQ":RQ}
		String RPDREPORTMESSAGEID = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("RPDREPORTMESSAGEID")));
		String SHR = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("SHR")));
		String ZBR1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("ZBR1")));
		String BZ = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("BZ")));
		String RQ = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("RQ")));
		List<PcRpdReportMessageT> reportMessage = null;
		PcRpdReportMessageT reportM = null;
		if(RPDREPORTMESSAGEID != null && !"".equals(RPDREPORTMESSAGEID)){
			reportMessage = reportMessageService.SreachReportMessages(RPDREPORTMESSAGEID, "", "");
		}
		
		if(reportMessage != null && reportMessage.size()>0){
			reportM = reportMessage.get(0);
		}else{
			if(RQ != null && !"".equals(RQ)){
				
				reportMessage = reportMessageService.SreachReportMessages("", "稀油注输联合站分线计量日报表", RQ);
			}else{
				obj = CommonsUtil.getRrturnJson("","系统无当前日期，请重新选择数据" ,"", null, null);
				out.print(obj);
				return null;
			}
			
			if(reportMessage != null && reportMessage.size()>0){
				obj = CommonsUtil.getRrturnJson("","系统已存在当前报表日报记录，请选择其他日期" ,"", null, null);
			}else{
				reportM = new PcRpdReportMessageT();
			}
		}
		
		reportM.setRpdReportMessageId(RPDREPORTMESSAGEID);
		reportM.setZbr1(ZBR1);
		reportM.setShr(SHR);
		reportM.setRq(DateBean.getStrDate(RQ));
		reportM.setBbmc("稀油注输联合站分线计量日报表");
		reportM.setBz(BZ);
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
				User user1 = (User) session.getAttribute("userInfo");
				PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "3", "稀油注输联合站分线计量", reportM.getRpdReportMessageId());
				logService.addLog(log);
			} catch (Exception e) {
				e.printStackTrace();
				obj = CommonsUtil.getRrturnJson("","稀油注输联合站分线计量" ,"", null, null);
			}
		}
		out.print(obj);
		return null;
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
			
			obj = CommonsUtil.getRrturnJson("","未获取到稀油注输联合站分线计量日报表数据ID" ,"", null, null);
			out.print(obj);
			return null;
		}
		
		if(reportMessage != null && reportMessage.size()>0){
			reportM = reportMessage.get(0);
		}else{
			obj = CommonsUtil.getRrturnJson("","未获取到稀油注输联合站分线计量日报表数据ID对应数据" ,"", null, null);
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
				PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "3", "稀油注输联合站分线计量", reportM.getRpdReportMessageId());
				logService.addLog(log);
			} catch (Exception e) {
				e.printStackTrace();
				obj = CommonsUtil.getRrturnJson("","稀油注输联合站分线计量日志修改失败" ,"", null, null);
			}
		}
		out.print(obj);
		return null;
	}
	public String searchLineMeasu() throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		List<PcRpdReportMessageT> reportMessage = null;
		JSONArray U2OILs = null;
		JSONObject obj = null;
		JSONObject jsonobj = null;
		String txtDate = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("txtDate")));
		String[] reportMessages = new String[5];
		//获取当天所以二号稠油联合站原油系统日报数据
		
		PcRpdReportMessageT reportM = null;
		if(txtDate == null || "".equals(txtDate)){
			txtDate = DateBean.getSystemTime1();
		}
		
		try {
			U2OILs = LineMeasureService.searchLineXy(txtDate);
			
			if(U2OILs != null && U2OILs.size()>0){
				jsonobj = new JSONObject();
				jsonobj.put("U2OILS", U2OILs);
			}else{
				obj = CommonsUtil.getRrturnJson("","当前日期搜索无数据请选择其他日期" ,"", null, null);
				out.print(obj);
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			obj = CommonsUtil.getRrturnJson("","当前日期搜索失败请选择其他日期" ,"", null, null);
			out.print(obj);
			return null;
		}
		JSONObject tree = new JSONObject();
		tree.putAll(jsonobj);
		try {
			reportMessage = reportMessageService.SreachReportMessages("", "稀油注输联合站分线计量日报表", txtDate);
			if(reportMessage != null && reportMessage.size()>0){
				reportM = reportMessage.get(0);
				
			}else{
				reportM = new PcRpdReportMessageT();
				reportM.setRq(DateBean.getStrDate(txtDate));
				reportM.setBbmc("稀油注输联合站分线计量日报表");
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
				reportMessage = reportMessageService.SreachReportMessages("", "稀油注输联合站分线计量日报表", txtDate);
				reportM = reportMessage.get(0);
				if(updateflg){
					//添加系统LOG
					try {
						User user1 = (User) session.getAttribute("userInfo");
						PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "1", "稀油注输联合站分线计量日报表", reportM.getRpdReportMessageId());
						logService.addLog(log);
					} catch (Exception e) {
						e.printStackTrace();
						obj = CommonsUtil.getRrturnJson("","稀油注输联合站分线计量日报表日志添加失败" ,"", null, null);
					}
				}
				
				
				
			}
			
			
			obj = new JSONObject();
			obj.put("RPDREPORTMESSAGEID", reportM.getRpdReportMessageId());
			obj.put("ZBR1", reportM.getZbr1());
			obj.put("SHR", reportM.getShr());
			obj.put("RQ", txtDate);
			obj.put("BZ", reportM.getBz());
			jsonobj.put("REPORTMESSAGE", obj);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			obj = CommonsUtil.getRrturnJson("","当前日期搜索失败请选择其他日期" ,"", null, null);
			out.print(obj);
			return null;
		}
		
		tree.putAll(jsonobj);
		obj = CommonsUtil.getRrturnJson("","" ,"", tree, null);
		out.print(obj);
	return null;
	
	
	

	
	}
}
