package com.echo.action;

import java.io.ByteArrayInputStream;
import java.io.File;
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
import com.echo.dto.PcRpd1836;
import com.echo.dto.PcRpd1836zh;
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

public class xiarbAction {
	HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
	private LineMeasureService LineMeasureService;
	private ReportMessageService reportMessageService;
	private LogService logService;
	private InputStream excelFile = null;
	public String getFileName() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd ");
		String downloadFileName = (sf.format(new Date()).toString())+ "夏18-36注水井日报.xls";
		try {
			downloadFileName = new String(downloadFileName.getBytes(),"ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return downloadFileName;
	}

	public InputStream getExcelFile() {
		return excelFile;
	}


	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	public void setReportMessageService(ReportMessageService reportMessageService) {
		this.reportMessageService = reportMessageService;
	}
	public void setLineMeasureService(LineMeasureService LineMeasureService) {
		this.LineMeasureService = LineMeasureService;
	}
	public String searchXIA()throws Exception{
		
		System.out.println("111111111111111111111");
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		List<PcRpdReportMessageT> reportMessage = null;
		JSONArray U1S1S = null;
		JSONArray U1S1SS = null;
		JSONObject obj = null;
		JSONObject jsonobj = null;
		String date = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("txtDate")));
		//获取当天所以二号稠油联合站原油系统日报数据
		
		PcRpdReportMessageT reportM = null;
		if(date == null || "".equals(date)){
			date = DateBean.getSystemTime1();
		}
		
		try {
			U1S1S = LineMeasureService.searchLinex1836rb(date);
			if(U1S1S != null && U1S1S.size()>0){
				jsonobj = new JSONObject();
				jsonobj.put("U1S1S", U1S1S);
				U1S1SS = LineMeasureService.searchLinex1836rb1(date);
				if(U1S1SS != null && U1S1SS.size()>0){
					jsonobj.put("U1S1SS", U1S1SS);
				}
			}
			
//			else{
//				obj = CommonsUtil.getRrturnJson("","当前日期搜索无数据请选择其他日期" ,"", null, null);
//				out.print(obj);
//				return null;
//			}
		} catch (Exception e) {
			e.printStackTrace();
			obj = CommonsUtil.getRrturnJson("","当前日期搜索失败请选择其他日期" ,"", null, null);
			out.print(obj);
			return null;
		}
		try {
			reportMessage = reportMessageService.SreachReportMessages("", "夏18-36注水井日报", date);
			if(reportMessage != null && reportMessage.size()>0){
				reportM = reportMessage.get(0);
				
			}else{
				reportM = new PcRpdReportMessageT();
				reportM.setRq(DateBean.getStrDate(date));
				reportM.setBbmc("夏18-36注水井日报");
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
				reportMessage = reportMessageService.SreachReportMessages("", "夏18-36注水井日报", date);
				reportM = reportMessage.get(0);
				if(updateflg){
					//添加系统LOG
					try {
						User user1 = (User) session.getAttribute("userInfo");
						PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "1", "夏18-36注水井日报", reportM.getRpdReportMessageId());
						logService.addLog(log);
					} catch (Exception e) {
						e.printStackTrace();
						obj = CommonsUtil.getRrturnJson("","夏18-36注水井日志添加失败" ,"", null, null);
					}
				}
			}
			
			
			obj = new JSONObject();
			obj.put("RPDREPORTMESSAGEID", reportM.getRpdReportMessageId());
			obj.put("SHR", reportM.getShr());
			obj.put("RQ", date);
			jsonobj.put("REPORTMESSAGE", obj);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			obj = CommonsUtil.getRrturnJson("","当前日期搜索失败请选择其他日期" ,"", null, null);
			out.print(obj);
			return null;
		}
		
		
		obj = CommonsUtil.getRrturnJson("","" ,"", jsonobj, null);
		out.print(obj);
		
	return null;
	
	
	

	}

		//根据权限进行页面初始化
		public String pageInit()throws Exception{
			HttpServletResponse response=ServletActionContext.getResponse();//响应对象
			response.setCharacterEncoding("utf-8");
			response.setHeader("ContentType","text/xml");
			PrintWriter out = response.getWriter();
			JSONObject obj = new JSONObject();
			//根据用户权限生成用户权限生成页面布局格式
			User user = (User) session.getAttribute("userInfo");
//			
			JSONObject butJson = null;
			try {
				butJson = AuthorityUtil.getButtonJson("MENU172", user);
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
		public String exportx1836RB() throws Exception {
			HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
			String txtDate = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("txtDate")));
			if ("".equals(txtDate)) {
				txtDate = DateBean.getSystemTime1();
//				return null;
			}
			String templetFilePath = request.getSession().getServletContext().getRealPath("/") + "exceltemplet\\combination\\夏18-36注水井日报表.xls";
			HSSFWorkbook wb = ExcelToHtmlUtils.loadXls( new File( templetFilePath ) );
			HSSFSheet sheet = wb.getSheetAt(0);			
			wb.setSheetName(0, txtDate);			
			PropertiesConfig pc = new PropertiesConfig();
			String fields = pc.getSystemConfiguration("XIASQL");			
			List<Object[]> list = LineMeasureService.searchExportData(txtDate,fields);	
			U2DataExportUtil.insertExcelData(wb, sheet, "AC2", 0, new Object[]{txtDate});

			if (list != null && list.size() > 0) {				
				U2DataExportUtil.insertDataByPosition(list, wb, sheet, "B5");
			}
			String baseInfoSql = "select B1ZSL,B2ZSL,B3ZSL,RZSL from  PC_RPD_X18_36ZS_ZH_T a where 1=1 and a.REPORT_DATE = TO_DATE('"+txtDate+"','YYYY-MM-DD')";
			list = LineMeasureService.searchExportData("", baseInfoSql);
			if (list != null && list.size() > 0) {
				String[] basePostion = pc.getSystemConfiguration( "XIAInsert" ).split(",");
				for (int j = 0; j < basePostion.length; j++) {
					if (list.get(0)[j] != null) {
						U2DataExportUtil.insertExcelData(wb, sheet, basePostion[j], 0, new Object[]{list.get(0)[j]});
					}
				}
			}
			String baseInfoSql1 = "select r.shr,r.rq from pc_rpd_report_message_t r where r.bbmc='夏18-36注水井日报' and r.rq=TO_DATE('" + txtDate + "','YYYY-MM-DD') ";
			list = LineMeasureService.searchExportData("", baseInfoSql1);
			if (list != null && list.size() > 0) {
				// 获取单位、值班人、审核人、日期、备注数据插入位置
				String[] basePostion = pc.getSystemConfiguration( "XIAomInsert" ).split(",");
				for (int j = 0; j < basePostion.length; j++) {
					if (list.get(0)[j] != null) {
						U2DataExportUtil.insertExcelData(wb, sheet, basePostion[j], 0, new Object[]{list.get(0)[j]});
					}
				}
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
		public String update() throws Exception {
			HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
			HttpServletResponse response=ServletActionContext.getResponse();//响应对象
			response.setCharacterEncoding("utf-8");
			response.setHeader("ContentType","text/xml");
			PrintWriter out = response.getWriter();
			JSONObject obj = new JSONObject();
			String RPDREPORTMESSAGEID = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("RPDREPORTMESSAGEID")));

			String RQ = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("RQ")));
			String ID = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("ID")));
			String B1ZSL = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("B1ZSL")));
			String B2ZSL = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("B2ZSL")));
			String B3ZSL = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("B3ZSL")));
			String RZSL = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("RZSL")));
			List<PcRpd1836zh> tsjy1 = null;
			PcRpd1836zh u2tsjy1 = null;

			if(ID != null && !"".equals(ID)){
				tsjy1 = LineMeasureService.Sreach1836zh(ID, "");
			}			
			if(tsjy1 != null && tsjy1.size()>0){
				u2tsjy1 = tsjy1.get(0);
			}else{
				u2tsjy1 = new PcRpd1836zh();
			}

			
			
			List<PcRpdReportMessageT> reportMessage = null;
			PcRpdReportMessageT reportM = null;
			if(RPDREPORTMESSAGEID != null && !"".equals(RPDREPORTMESSAGEID)){
				reportMessage = reportMessageService.SreachReportMessages(RPDREPORTMESSAGEID, "", "");
			}
			
			if(reportMessage != null && reportMessage.size()>0){
				reportM = reportMessage.get(0);
			}else{
				if(RQ != null && !"".equals(RQ)){
					
					reportMessage = reportMessageService.SreachReportMessages("", "夏18-36注水井日报", RQ);
				}else{
					obj = CommonsUtil.getRrturnJson("","系统无当前日期，请重新选择数据" ,"", null, null);
					out.print(obj);
					return null;
				}
				
				if(reportMessage != null && reportMessage.size()>0){
					obj = CommonsUtil.getRrturnJson("","系统已存在当前报表日报记录，请选择其他日期" ,"", null, null);
					out.print(obj);
					return null;
				}else{
					reportM = new PcRpdReportMessageT();
				}
			}
			
			reportM.setRpdReportMessageId(RPDREPORTMESSAGEID);
			reportM.setRq(DateBean.getStrDate(RQ));
			reportM.setBbmc("夏18-36注水井日报");
			
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String firstStr = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("firstStr")));
			firstStr = firstStr.substring(0,firstStr.length()-1);
			String[] firstList = firstStr.split(";",-1);
			String beforetime = DateBean.getBeforeDAYTime(RQ);
			for(int i=0;i<firstList.length;i++){
				String[] firstPra = firstList[i].split(",",-1);
					String id = firstPra[0];
					List<PcRpd1836> usList = null;
					PcRpd1836 us = null;
					if(id != null && !"".equals(id)){
						try {
							usList = LineMeasureService.Sreach1836(id, "");
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					if(usList != null && usList.size()>0){
						us = usList.get(0);
						
					}else{
						us = new PcRpd1836();
					}					
					if(firstPra[1].equals("00:00")){
						beforetime = RQ;
					}
					us.setReportsj(sf.parse(beforetime +" "+ firstPra[1]));
					us.setPRSA_301(CommonsUtil.isNullOrEmpty(firstPra[2]));
					us.setPRSA_306(CommonsUtil.isNullOrEmpty(firstPra[3]));
					us.setTRSA_301(CommonsUtil.isNullOrEmpty(firstPra[4]));
					us.setZSB1DY(CommonsUtil.isNullOrEmpty(firstPra[5]));
					us.setZSB1DL(CommonsUtil.isNullOrEmpty(firstPra[6]));
					us.setPRSA_302(CommonsUtil.isNullOrEmpty(firstPra[7]));
					us.setPRSA_307(CommonsUtil.isNullOrEmpty(firstPra[8]));
					us.setTRSA_302(CommonsUtil.isNullOrEmpty(firstPra[9]));
					us.setZSB2DY(CommonsUtil.isNullOrEmpty(firstPra[10]));
					us.setZSB2DL(CommonsUtil.isNullOrEmpty(firstPra[11]));
					us.setSR_301(CommonsUtil.isNullOrEmpty(firstPra[12]));
					us.setPRSA_303(CommonsUtil.isNullOrEmpty(firstPra[13]));
					us.setPRSA_308(CommonsUtil.isNullOrEmpty(firstPra[14]));
					us.setTRSA_303(CommonsUtil.isNullOrEmpty(firstPra[15]));
					us.setZSB3DY(CommonsUtil.isNullOrEmpty(firstPra[16]));
					us.setZSB3DL(CommonsUtil.isNullOrEmpty(firstPra[17]));
					us.setPRSA_304(CommonsUtil.isNullOrEmpty(firstPra[18]));
					us.setPRSA_309(CommonsUtil.isNullOrEmpty(firstPra[19]));
					us.setTRSA_304(CommonsUtil.isNullOrEmpty(firstPra[20]));
					us.setZSB4DY(CommonsUtil.isNullOrEmpty(firstPra[21]));
					us.setZSB4DL(CommonsUtil.isNullOrEmpty(firstPra[22]));
					us.setBP34PL(CommonsUtil.isNullOrEmpty(firstPra[23]));
					us.setTSBLLLJ(CommonsUtil.isNullOrEmpty(firstPra[24]));
					us.setFRQ_501(CommonsUtil.isNullOrEmpty(firstPra[25]));
					us.setSR_501(CommonsUtil.isNullOrEmpty(firstPra[26]));
					us.setLRSA_501(CommonsUtil.isNullOrEmpty(firstPra[27]));
					us.setLRSA_502(CommonsUtil.isNullOrEmpty(firstPra[28]));
					us.setPRSA_305(CommonsUtil.isNullOrEmpty(firstPra[29]));
					us.setFRQ_301(CommonsUtil.isNullOrEmpty(firstPra[30]));
					us.setFRQ_302(CommonsUtil.isNullOrEmpty(firstPra[31]));
	         		boolean updateflg = false;
	        		try {
	        			updateflg = LineMeasureService.updatedata(us);
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
				if(!B1ZSL.equals("") || !B2ZSL.equals("") || !B3ZSL.equals("") || !RZSL.equals("")){
//					u2tsjy1.setRpdU2TSJYId(jyid1);
					u2tsjy1.setB1ZSL(CommonsUtil.isNullOrEmpty(B1ZSL));
					u2tsjy1.setB2ZSL(CommonsUtil.isNullOrEmpty(B2ZSL));
					u2tsjy1.setB3ZSL(CommonsUtil.isNullOrEmpty(B3ZSL));
					u2tsjy1.setRZSL(CommonsUtil.isNullOrEmpty(RZSL));
					u2tsjy1.setReportsj(DateBean.getStrDate(RQ));
					updateflg = LineMeasureService.updatedata(u2tsjy1);
				}
				
					
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
					PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "3", "夏18-36注水井日报", reportM.getRpdReportMessageId());
					logService.addLog(log);
				} catch (Exception e) {
					e.printStackTrace();
					obj = CommonsUtil.getRrturnJson("","夏18-36注水井日报修改失败" ,"", null, null);
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
				
				obj = CommonsUtil.getRrturnJson("","夏18-36注水井日报ID" ,"", null, null);
				out.print(obj);
				return null;
			}
			
			if(reportMessage != null && reportMessage.size()>0){
				reportM = reportMessage.get(0);
			}else{
				obj = CommonsUtil.getRrturnJson("","未获取到夏18-36注水井日报ID对应数据" ,"", null, null);
				out.print(obj);
				return null;
			}
			reportM.setShr(user1.getOperName());
			reportM.setShsj(new Date());
			reportM.setBbmc("夏18-36注水井日报");

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
					PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "3", "夏18-36注水井日报", reportM.getRpdReportMessageId());
					logService.addLog(log);
				} catch (Exception e) {
					e.printStackTrace();
					obj = CommonsUtil.getRrturnJson("","夏18-36注水井日报日志修改失败" ,"", null, null);
				}
			}
			out.print(obj);
			return null;
		}
}
