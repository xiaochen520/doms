package com.echo.action;


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
import org.apache.struts2.ServletActionContext;
import com.echo.dto.PcRdLoginfoT;
import com.echo.dto.PcRpdReportMessageT;
import com.echo.dto.PcRpdXylScMessureT;
import com.echo.dto.User;
import com.echo.service.LineMeasureService;
import com.echo.service.LogService;
import com.echo.service.ReportMessageService;
import com.echo.util.AuthorityUtil;
import com.echo.util.CommonsUtil;
import com.echo.util.DateBean;
import com.echo.util.StringUtil;


public class xyfxsc3Action {
	HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
	private LineMeasureService LineMeasureService;
	private ReportMessageService reportMessageService;
	private LogService logService;
	private InputStream excelFile = null;
	public String getFileName() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd ");
		String downloadFileName = (sf.format(new Date()).toString())+ "稀油注输联合站日生产数据.xls";
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
	//根据权限进行页面初始化
	public String pageInit()throws Exception{
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONObject obj = new JSONObject();
		//根据用户权限生成用户权限生成页面布局格式
		User user = (User) session.getAttribute("userInfo");		
		JSONObject butJson = null;
		try {
			butJson = AuthorityUtil.getButtonJson("MENU202", user);
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
	
	public String searchFxjl3()throws Exception{	
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
		//获取2#转油站工作日数据
		
		PcRpdReportMessageT reportM = null;
	
		if(txtDate == null || "".equals(txtDate)){
	
			txtDate = DateBean.getSystemTime1();
		}
		try {
			U2OILs = LineMeasureService.searchFxjlsc3(txtDate);			
			
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
		try {
			reportMessage = reportMessageService.SreachReportMessages("", "稀油注输联合站日生产数据", txtDate);
			if(reportMessage != null && reportMessage.size()>0){
				reportM = reportMessage.get(0);
			}else{
				reportM = new PcRpdReportMessageT();
				reportM.setRq(DateBean.getStrDate(txtDate));
				reportM.setBbmc("稀油注输联合站日生产数据");
		
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
				reportMessage = reportMessageService.SreachReportMessages("", "稀油注输联合站日生产数据", txtDate);
				reportM = reportMessage.get(0);
				if(updateflg){
					//添加系统LOG
					try {
						User user1 = (User) session.getAttribute("userInfo");
						PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "1", "稀油注输联合站日生产数据", reportM.getRpdReportMessageId());
						logService.addLog(log);
					} catch (Exception e) {
						e.printStackTrace();
						obj = CommonsUtil.getRrturnJson("","稀油注输联合站日生产数据日志添加失败" ,"", null, null);
					}
				}
			}
			obj = new JSONObject();
			obj.put("RPDREPORTMESSAGEID", reportM.getRpdReportMessageId());
			obj.put("SHR", reportM.getShr());
			obj.put("RQ", txtDate);
			
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
		
//		public String exportcyfx1() throws Exception {
//			HttpServletRequest request=ServletActionContext.getRequest(); //请求对象		
//			PropertiesConfig pc = new PropertiesConfig();	
//			String templetFilePath = request.getSession().getServletContext().getRealPath("/") + "exceltemplet\\combination\\一号稠油联合处理站日生产数据.xls";
//			HSSFWorkbook wb = ExcelToHtmlUtils.loadXls( new File( templetFilePath ) );
//			HSSFSheet sheet = wb.getSheetAt(0);						
//			String txtYear = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("SYear")));
//			String txtMonth = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("SMonth")));
//			List<String> date = null;
//			if(txtYear != null && !"".equals(txtYear) && txtMonth != null && !"".equals(txtMonth)){
//				date = DateBean.getYeahMonth(txtYear, txtMonth);
//			}else{
//				Calendar now = Calendar.getInstance();  
//				date = DateBean.getYeahMonth(String.valueOf(now.get(Calendar.YEAR)), String.valueOf((now.get(Calendar.MONTH) + 1)));				
//			}			
//			String fields = "select rpd_cy1_sc_messure_id ,to_char(a.report_date,'YYYY-MM-DD')as report_date,jhg_youl,jhg_ds,tsb," +
//					"wuy,wuypk,fxccy,pank,ftq_s1s,remark from  PC_RPD_CY1_SC_MESSURE_T a where 1=1";
//			fields += " and a.report_date =TO_DATE('"+date+"','YYYY-MM-DD')";				
//				List<Object[]> cyfx1List = LineMeasureService.searchcyfx1("", fields);
//			if ( cyfx1List == null || cyfx1List.size() == 0 ) {
//				java.io.ByteArrayOutputStream baos = U2DataExportUtil.ExporDataStream(wb);
//				if(baos != null){
//					byte[] ba = baos.toByteArray();
//					excelFile = new ByteArrayInputStream(ba);
//					baos.flush();
//					baos.close();
//				}
//				return "excel";
//			}
//			//分段时间
//			String[] timeStr = pc.getSystemConfiguration("time17").split("&");
//
//			String[][] dates = new String[3][];
//			for (int i = 0; i < dates.length; i++) {
//				dates[i] = timeStr[i].split(",");
//			}
//			String[] cyfx1DataInsertPostions = pc.getSystemConfiguration("cyfx1DataInsertPostions").split(",");			
//			U2DataExportUtil.TemporalGroupingMonth(wb, sheet, cyfx1List, dates, cyfx1DataInsertPostions, txtYear, txtMonth);
//			wb.setForceFormulaRecalculation(true);
//			java.io.ByteArrayOutputStream baos = U2DataExportUtil.ExporDataStream(wb);
//			if(baos != null){
//				byte[] ba = baos.toByteArray();
//				excelFile = new ByteArrayInputStream(ba);
//				baos.flush();
//				baos.close();
//			}
//			return "excel";
//		}

		//修改
		public String update() throws Exception {
			HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
			HttpServletResponse response=ServletActionContext.getResponse();//响应对象
			response.setCharacterEncoding("utf-8");
			response.setHeader("ContentType","text/xml");
			PrintWriter out = response.getWriter();
			JSONObject obj = new JSONObject();	
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			
			String RPDREPORTMESSAGEID = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("RPDREPORTMESSAGEID")));
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
					reportMessage = reportMessageService.SreachReportMessages("", "稀油注输联合站日生产数据", RQ);
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
			reportM.setBbmc("稀油注输联合站日生产数据");

			String firstStr = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("firstStr")));	
			firstStr = firstStr.substring(0,firstStr.length()-1);
			String[] firstList = firstStr.split(";",-1);
			for(int i=0;i<firstList.length;i++){
				String[] firstPra = firstList[i].split(",",-1);
					String id = firstPra[0];
					List<PcRpdXylScMessureT> usList = null;
					PcRpdXylScMessureT us = null;
					if(id != null && !"".equals(id)){
						try {
							usList = LineMeasureService.Sreachxyfx3(id, "");
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					if(usList != null && usList.size()>0){
						us = usList.get(0);						
					}else{
						us = new PcRpdXylScMessureT();
					}										
					us.setReportdate(sf.parse(RQ));
					us.setJhgye(CommonsUtil.isNullOrEmpty(firstPra[2]));
					us.setJhgyou(CommonsUtil.isNullOrEmpty(firstPra[3]));
					us.setPk(CommonsUtil.isNullOrEmpty(firstPra[4]));
					us.setRemark(firstPra[5]);
					
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
	        		}
	        		if(!updateflg){
	        			obj = CommonsUtil.getRrturnJson("","第"+(i+1)+"行修改失败" ,"", null, null);
	        		}			
			}
			out.print(obj);
			return null;		
		}
		
		
		//审核
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
				obj = CommonsUtil.getRrturnJson("","稀油注输联合站日生产数据ID" ,"", null, null);
				out.print(obj);
				return null;
			}
			
			if(reportMessage != null && reportMessage.size()>0){
				reportM = reportMessage.get(0);
			}else{
				obj = CommonsUtil.getRrturnJson("","未获取到稀油注输联合站日生产数据ID对应数据" ,"", null, null);
				out.print(obj);
				return null;
			}
			reportM.setShr(user1.getOperName());
			reportM.setShsj(new Date());
			reportM.setBbmc("稀油注输联合站日生产数据");

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
					PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "3", "稀油注输联合站日生产数据", reportM.getRpdReportMessageId());
					logService.addLog(log);
				} catch (Exception e) {
					e.printStackTrace();
					obj = CommonsUtil.getRrturnJson("","稀油注输联合站日生产数据日志修改失败" ,"", null, null);
				}
			}
			out.print(obj);
			return null;
		}
		
}
