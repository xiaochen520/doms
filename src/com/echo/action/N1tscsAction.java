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
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import com.echo.dto.PcRdLoginfoT;
import com.echo.dto.PcRpdReportMessageT;
import com.echo.dto.PcRpdU1DehydrationT;
import com.echo.dto.PcRpdUThinWaterAutoT;
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
		//1号脱水参数
public class N1tscsAction extends ActionSupport{
	/**
	 * 
	 */
	private ReportMessageService reportMessageService;
	private LineMeasureService lineMeasureService;
	private LogService logService;
	private InputStream excelFile = null;
	
	public InputStream getExcelFile() {
		return excelFile;
	}

	public void setLineMeasureService(LineMeasureService lineMeasureService) {
		this.lineMeasureService = lineMeasureService;
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
		String downloadFileName = (sf.format(new Date()).toString())+ "一号稠油联合处理站原油脱水参数表.xls";
		try {
			downloadFileName = new String(downloadFileName.getBytes(),"ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return downloadFileName;
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
			U2OILs = lineMeasureService.searchN1tscs(txtDate);
			
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
			reportMessage = reportMessageService.SreachReportMessages("", "一号稠油联合处理站原油脱水参数表", txtDate);
			if(reportMessage != null && reportMessage.size()>0){
				reportM = reportMessage.get(0);
				
			}else{
				reportM = new PcRpdReportMessageT();
				reportM.setRq(DateBean.getStrDate(txtDate));
				reportM.setBbmc("一号稠油联合处理站原油脱水参数表");
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
				reportMessage = reportMessageService.SreachReportMessages("", "一号稠油联合处理站原油脱水参数表", txtDate);
				reportM = reportMessage.get(0);
				if(updateflg){
					//添加系统LOG
					try {
						User user1 = (User) session.getAttribute("userInfo");
						PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "1", "一号稠油联合处理站原油脱水参数表", reportM.getRpdReportMessageId());
						logService.addLog(log);
					} catch (Exception e) {
						e.printStackTrace();
						obj = CommonsUtil.getRrturnJson("","一号稠油联合处理站原油脱水参数表日志添加失败" ,"", null, null);
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
	
	public String pageInit()throws IOException{
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		JSONObject obj = new JSONObject();
		//根据用户权限生成用户权限生成页面布局格式
		User user = (User) session.getAttribute("userInfo");
//		
		JSONObject butJson = null;
		try {
			butJson = AuthorityUtil.getButtonJson("MENU175", user);
		} catch (Exception e) {
			e.printStackTrace();
			obj = CommonsUtil.getRrturnJson("","页面初始化权限获取失败" ,"", null, null);
			out.print(obj);
			return null;
			
		}
		
		obj = CommonsUtil.getRrturnJson("","" ,"", butJson, null);
		out.print(obj);
		return null;
	
	}
	
	@SuppressWarnings("unchecked")
	public String exportLineMeasure() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		String txtDate = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("txtDate")));
		if ("".equals(txtDate)) {
			txtDate = DateBean.getSystemTime1();
//			return null;
		}
		PropertiesConfig pc = new PropertiesConfig();
		String fields = pc.getSystemConfiguration("lineMeasureFieldsTS");
		List<PcRpdReportMessageT> reportMessage = reportMessageService.SreachReportMessages("", "一号稠油联合处理站原油脱水参数表",txtDate);//值班人、审核人、日期、备注
//		获取值班人、审核人、日期、备注数据插入位置
		String U2_WATERWatch = pc.getSystemConfiguration("lineMeasureTS");
		String U2_WATERAuditor = pc.getSystemConfiguration("lineMeasureTS");
		String U2_WATERReportTime = pc.getSystemConfiguration("lineReportTimeTS");
		//String U2_WATERremark = pc.getSystemConfiguration("lineMeasureR");
		String templetFilePath = request.getSession().getServletContext().getRealPath("/") + "exceltemplet\\combination\\一号稠油联合处理站原油脱水参数表.xls";
		HSSFWorkbook wb = ExcelToHtmlUtils.loadXls( new File( templetFilePath ) );
		HSSFSheet sheet = wb.getSheetAt(0);
		List<Object[]> lineMeasureList = lineMeasureService.searchLineN1tscs(txtDate, fields);
		
		U2DataExportUtil.insertExcelData(wb, sheet, U2_WATERReportTime, 0, new Object[]{txtDate});
		if(reportMessage.size()>0){
			if (!"".equals(StringUtil.isNullUtil(reportMessage.get(0).getZbr1()))) {
				U2DataExportUtil.insertExcelData(wb, sheet, U2_WATERWatch, 0, new Object[]{reportMessage.get(0).getZbr1()});
			}
			if (!"".equals(StringUtil.isNullUtil(reportMessage.get(0).getShr()))) {
				U2DataExportUtil.insertExcelData(wb, sheet, U2_WATERAuditor, 0, new Object[]{reportMessage.get(0).getShr()});
			}
//			if (null != reportMessage.get(0).getRq()) {
//				U2DataExportUtil.insertExcelData(wb, sheet, U2_WATERReportTime, 0, new Object[]{reportMessage.get(0).getRq()});
//			}
//			if (!"".equals(StringUtil.isNullUtil(reportMessage.get(0).getBz()))) {
//				U2DataExportUtil.insertExcelData(wb, sheet, U2_WATERremark, 0, new Object[]{reportMessage.get(0).getBz()});
//			}
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
		String[] timeStr = pc.getSystemConfiguration("time11").split("&");
		String[][] dates = new String[2][];
		for (int i = 0; i < dates.length; i++) {
			dates[i] = timeStr[i].split(",");
		}
		U2DataExportUtil.setDates(txtDate, dates);
		//插入数据位置
		String[] lineMeasurePositions = pc.getSystemConfiguration("lineMeasureDataInsertPostionsTS").split(",");
		
		
		U2DataExportUtil.TemporalGrouping(wb, sheet, lineMeasureList, dates, lineMeasurePositions, txtDate);

		
		wb.setForceFormulaRecalculation(true);
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
				
				reportMessage = reportMessageService.SreachReportMessages("", "一号稠油联合处理站原油脱水参数表", RQ);
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
		reportM.setBbmc("一号稠油联合处理站原油脱水参数表");
		//reportM.setBz(BZ);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String firstStr = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("firstStr")));
		firstStr = firstStr.substring(0,firstStr.length()-1);
		String[] firstList = firstStr.split(";",-1);
		String beforetime = DateBean.getBeforeDAYTime(RQ);
		for(int i=0;i<firstList.length;i++){
			String[] firstPra = firstList[i].split(",",-1);
				String id = firstPra[0];
				List<PcRpdU1DehydrationT> waterAuto = null;
				PcRpdU1DehydrationT waterA = null;
				if(id != null && !"".equals(id)){
					try {
						waterAuto = lineMeasureService.SreachN1tscsAuto(id, "");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				if(waterAuto != null && waterAuto.size()>0){
					waterA = waterAuto.get(0);
				}else{
//					obj = CommonsUtil.getRrturnJson("","报表ID获取失败，没有此时的您要修改的数据，请选择其他时间" ,"", null, null);
//					out.print(obj);
//					return null;
					waterA = new PcRpdU1DehydrationT();
				}
				
				if(firstPra[1].equals("00:00")){
					beforetime = RQ;
				}

					waterA.setBbsj(sf.parse(beforetime +" "+ firstPra[1]));
					waterA.setLT201(CommonsUtil.isNullOrEmpty(firstPra[2]));
					waterA.setLT202(CommonsUtil.isNullOrEmpty(firstPra[3]));
					waterA.setLT203(CommonsUtil.isNullOrEmpty(firstPra[4]));
					waterA.setLT204(CommonsUtil.isNullOrEmpty(firstPra[5]));
					waterA.setLT205(CommonsUtil.isNullOrEmpty(firstPra[6]));
					waterA.setLT206(CommonsUtil.isNullOrEmpty(firstPra[7]));
					waterA.setLT207(CommonsUtil.isNullOrEmpty(firstPra[8]));
					waterA.setLT208(CommonsUtil.isNullOrEmpty(firstPra[9]));
					waterA.setLRR_218(CommonsUtil.isNullOrEmpty(firstPra[10]));
					waterA.setLRR_217(CommonsUtil.isNullOrEmpty(firstPra[11]));
					waterA.setLRR_220(CommonsUtil.isNullOrEmpty(firstPra[12]));
					waterA.setLRR_219(CommonsUtil.isNullOrEmpty(firstPra[13]));
					waterA.setTSB1PL(CommonsUtil.isNullOrEmpty(firstPra[14]));
					waterA.setTSB2PL(CommonsUtil.isNullOrEmpty(firstPra[15]));
					waterA.setTSB3PL(CommonsUtil.isNullOrEmpty(firstPra[16]));
					waterA.setTSB4PL(CommonsUtil.isNullOrEmpty(firstPra[17]));
					waterA.setTSB5PL(CommonsUtil.isNullOrEmpty(firstPra[18]));
					waterA.setT1001(CommonsUtil.isNullOrEmpty(firstPra[19]));
					waterA.setR_225(CommonsUtil.isNullOrEmpty(firstPra[20]));
					
										
				//}

         		boolean updateflg = false;
        		try {
        			updateflg = lineMeasureService.updatedata(waterA);
        		} catch (Exception e) {
        			String  errmsg = e.getCause().toString();
        			
        			if(!"".equals(errmsg) && errmsg.indexOf("ORA-01438") != -1){
        				obj = CommonsUtil.getRrturnJson("","您输入信息超过数据库数据精度范围" ,"", null, null);
        			} else{
        				obj = CommonsUtil.getRrturnJson("",errmsg ,"", null, null);
        			}
        			e.printStackTrace();
        			out.print(obj);
        			//return null;
        		}
        		if(!updateflg){
        			obj = CommonsUtil.getRrturnJson("","第"+(i+1)+"行修改失败" ,"", null, null);
        		}
			
		
		}
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
				PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "3", "一号稠油联合处理站原油脱水参数表", reportM.getRpdReportMessageId());
				logService.addLog(log);
			} catch (Exception e) {
				e.printStackTrace();
				obj = CommonsUtil.getRrturnJson("","一号稠油联合处理站原油脱水参数表" ,"", null, null);
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
			
			obj = CommonsUtil.getRrturnJson("","未获取到一号稠油联合处理站原油脱水参数表数据ID" ,"", null, null);
			out.print(obj);
			return null;
		}
		
		if(reportMessage != null && reportMessage.size()>0){
			reportM = reportMessage.get(0);
		}else{
			obj = CommonsUtil.getRrturnJson("","未获取到一号稠油联合处理站原油脱水参数表数据ID对应数据" ,"", null, null);
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
				PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "3", "一号稠油联合处理站原油脱水参数表", reportM.getRpdReportMessageId());
				logService.addLog(log);
			} catch (Exception e) {
				e.printStackTrace();
				obj = CommonsUtil.getRrturnJson("","分线计量管汇间日志修改失败" ,"", null, null);
			}
		}
		out.print(obj);
		return null;
	}
}
