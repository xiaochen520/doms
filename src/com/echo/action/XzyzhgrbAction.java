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
import com.echo.dto.PcRpdReportMessageT;
import com.echo.dto.PcRpdXzyzhg;
import com.echo.dto.PcRpdXzyzhgzh;
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

public class XzyzhgrbAction {

	HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
	private LineMeasureService LineMeasureService;
	private ReportMessageService reportMessageService;
	private LogService logService;
	private InputStream excelFile = null;
	public String getFileName() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd ");
		String downloadFileName = (sf.format(new Date()).toString())+ "夏转油综合岗日报.xls";
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
			butJson = AuthorityUtil.getButtonJson("MENU177", user);
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
	
	
	public String searchXzyzhg()throws Exception{	
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
			U1S1S = LineMeasureService.searchLineXzyzhgrb(date);
			if(U1S1S != null && U1S1S.size()>0){
				jsonobj = new JSONObject();
				jsonobj.put("U1S1S", U1S1S);
				U1S1SS = LineMeasureService.searchLineXzyzhgrb1(date);
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
			reportMessage = reportMessageService.SreachReportMessages("", "夏转油综合岗日报", date);
			if(reportMessage != null && reportMessage.size()>0){
				reportM = reportMessage.get(0);
				
			}else{
				reportM = new PcRpdReportMessageT();
				reportM.setRq(DateBean.getStrDate(date));
				reportM.setBbmc("夏转油综合岗日报");
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
				reportMessage = reportMessageService.SreachReportMessages("", "夏转油综合岗日报", date);
				reportM = reportMessage.get(0);
				if(updateflg){
					//添加系统LOG
					try {
						User user1 = (User) session.getAttribute("userInfo");
						PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "1", "夏转油综合岗日报", reportM.getRpdReportMessageId());
						logService.addLog(log);
					} catch (Exception e) {
						e.printStackTrace();
						obj = CommonsUtil.getRrturnJson("","夏转油综合岗日志添加失败" ,"", null, null);
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
		
		public String exportxzyzhgrb() throws Exception {
			HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
			String txtDate = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("txtDate")));
			if ("".equals(txtDate)) {
				txtDate = DateBean.getSystemTime1();
//				return null;
			}
			String templetFilePath = request.getSession().getServletContext().getRealPath("/") + "exceltemplet\\combination\\夏转油综合岗日报表.xls";
			HSSFWorkbook wb = ExcelToHtmlUtils.loadXls( new File( templetFilePath ) );
			HSSFSheet sheet = wb.getSheetAt(0);			
			wb.setSheetName(0, txtDate);			
			PropertiesConfig pc = new PropertiesConfig();
//			String stratime =DateBean.getDynamicTime("-16", txtDate, "0");
//			String endtime =DateBean.getDynamicTime("-16", txtDate, "1");
			String stratime =DateBean.getBeforeDAYTime(txtDate);
			
			String fields = " select cyqjkyl,cyqckyl,flq1jkyl,flq1ckyl,flq1yw,flq2jkyl,flq2ckyl,flq2yw,wsb1jkyl,wsb1ckyl,"+
					"wsb2jkyl,wsb2ckyl,wsbbppl,xbl1jkyl,xbl1ckyl,xbl1jkwd,xbl1ckwd,xbl2jkyl,xbl2ckyl,xbl2jkwd,xbl2ckwd,sggyw,hsyhs,"+
        "trqyl,bsqqbyl,bsqqbll,bsqqbwd,bsqqblllj,gqqbfqyl,gqqbfhyl,gqqbwd,gqqbll,gqqblllj,qjqll,qjqlllj from pc_rpd_zyz_t a where 1=1"+			
		" and a.BBSJ between TO_DATE('"+stratime+" 02:00','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+txtDate+" 00:00','YYYY-MM-DD HH24:MI:SS') order by a.BBSJ";
        List<Object[]> list = LineMeasureService.searchExportData("",fields);	
			
        if (list != null && list.size() > 0) {				
			U2DataExportUtil.insertDataByPosition(list, wb, sheet, "B6");
		}
			String baseInfoSql = "select  rwsq,rq from  pc_rpd_zyz_general_t a where 1=1 and a.rq = TO_DATE('"+txtDate+"','YYYY-MM-DD')";
			list = LineMeasureService.searchExportData("", baseInfoSql);

			if (list != null && list.size() > 0) {
				String[] basePostion = pc.getSystemConfiguration( "XZYZHInsert" ).split(",");
				for (int j = 0; j < basePostion.length; j++) {
					if (list.get(0)[j] != null) {
						U2DataExportUtil.insertExcelData(wb, sheet, basePostion[j], 0, new Object[]{list.get(0)[j]});
					}
				}
			}
			String baseInfoSql1 = "select r.shr,r.rq from pc_rpd_report_message_t r where r.bbmc='夏转油综合岗日报' and r.rq=TO_DATE('" + txtDate + "','YYYY-MM-DD') ";
			list = LineMeasureService.searchExportData("", baseInfoSql1);
			if (list != null && list.size() > 0) {
				// 获取单位、值班人、审核人、日期、备注数据插入位置
				String[] basePostion = pc.getSystemConfiguration( "XZYZHomInsert" ).split(",");
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

	
		
		//修改
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
			String rwsq = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("rwsq")));
			List<PcRpdXzyzhgzh> xzyzhgzh1 = null;
			PcRpdXzyzhgzh xzyzhgzh = null;

			if(ID != null && !"".equals(ID)){
				xzyzhgzh1 = LineMeasureService.SreachXzyzhgzh(ID, "");
			}			
			if(xzyzhgzh1 != null && xzyzhgzh1.size()>0){
				xzyzhgzh = xzyzhgzh1.get(0);
			}else{
				xzyzhgzh = new PcRpdXzyzhgzh();
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
					reportMessage = reportMessageService.SreachReportMessages("", "夏转油综合岗日报", RQ);
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
			reportM.setBbmc("夏转油综合岗日报");			
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String firstStr = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("firstStr")));
			firstStr = firstStr.substring(0,firstStr.length()-1);			
			String[] firstList = firstStr.split(";",-1);
			String beforetime = DateBean.getBeforeDAYTime(RQ);
			//String time = DateBean.getTimeDAYTime(RQ);
			//System.out.println(time);
			for(int i=0;i<firstList.length;i++){
				String[] firstPra = firstList[i].split(",",-1);
					String id = firstPra[0];
//					System.out.println("********************111"+id+"222********************");
					List<PcRpdXzyzhg> usList = null;
					PcRpdXzyzhg us = null;
					if(id != null && !"".equals(id)){
						try {
							usList = LineMeasureService.SreachXzyzhg(id, "");
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					if(usList != null && usList.size()>0){
						us = usList.get(0);						
					}else{

						us = new PcRpdXzyzhg();
					}					
					if(firstPra[1].equals("00:00")){
						beforetime = RQ;
					}					
					us.setReportsj(sf.parse(beforetime+" "+ firstPra[1]));
					//System.out.println(" "+us.getReportsj());
//					us.setRpdU2WaterAutoId(firstPra[0]);
					us.setCyqjkyl(CommonsUtil.isNullOrEmpty(firstPra[2]));
					us.setCyqckyl(CommonsUtil.isNullOrEmpty(firstPra[3]));
					us.setFlq1jkyl(CommonsUtil.isNullOrEmpty(firstPra[4]));
					us.setFlq1ckyl(CommonsUtil.isNullOrEmpty(firstPra[5]));
					us.setFlq1yw(CommonsUtil.isNullOrEmpty(firstPra[6]));
					us.setFlq2jkyl(CommonsUtil.isNullOrEmpty(firstPra[7]));
					us.setFlq2ckyl(CommonsUtil.isNullOrEmpty(firstPra[8]));
					us.setFlq2yw(CommonsUtil.isNullOrEmpty(firstPra[9]));
					us.setWsb1jkyl(CommonsUtil.isNullOrEmpty(firstPra[10]));
					us.setWsb1ckyl(CommonsUtil.isNullOrEmpty(firstPra[11]));
					us.setWsb2jkyl(CommonsUtil.isNullOrEmpty(firstPra[12]));
					us.setWsb2ckyl(CommonsUtil.isNullOrEmpty(firstPra[13]));
					us.setWsbbppl(CommonsUtil.isNullOrEmpty(firstPra[14]));
					us.setXbl1jkyl(CommonsUtil.isNullOrEmpty(firstPra[15]));
					us.setXbl1ckyl(CommonsUtil.isNullOrEmpty(firstPra[16]));
					us.setXbl1jkwd(CommonsUtil.isNullOrEmpty(firstPra[17]));
					us.setXbl1ckwd(CommonsUtil.isNullOrEmpty(firstPra[18]));
					us.setXbl2jkyl(CommonsUtil.isNullOrEmpty(firstPra[19]));
					us.setXbl2ckyl(CommonsUtil.isNullOrEmpty(firstPra[20]));
					us.setXbl2jkwd(CommonsUtil.isNullOrEmpty(firstPra[21]));
					us.setXbl2ckwd(CommonsUtil.isNullOrEmpty(firstPra[22]));
					us.setSggyw(CommonsUtil.isNullOrEmpty(firstPra[23]));
					us.setHsyhs(CommonsUtil.isNullOrEmpty(firstPra[24]));
					us.setTrqyl(CommonsUtil.isNullOrEmpty(firstPra[25]));
					us.setBsqqbyl(CommonsUtil.isNullOrEmpty(firstPra[26]));
					us.setBsqqbll(CommonsUtil.isNullOrEmpty(firstPra[27]));
					us.setBsqqbwd(CommonsUtil.isNullOrEmpty(firstPra[28]));
					us.setBsqqblllj(CommonsUtil.isNullOrEmpty(firstPra[29]));
					us.setGqqbfqyl(CommonsUtil.isNullOrEmpty(firstPra[30]));
					us.setGqqbfhyl(CommonsUtil.isNullOrEmpty(firstPra[31]));					
					us.setGqqbwd(CommonsUtil.isNullOrEmpty(firstPra[32]));
					us.setGqqbll(CommonsUtil.isNullOrEmpty(firstPra[33]));
					us.setGqqblllj(CommonsUtil.isNullOrEmpty(firstPra[34]));
					us.setQjqll(CommonsUtil.isNullOrEmpty(firstPra[35]));
					us.setQjqlllj(CommonsUtil.isNullOrEmpty(firstPra[36]));	         		
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
				if(!rwsq.equals("")){
//					u2tsjy1.setRpdU2TSJYId(jyid1);;
					xzyzhgzh.setRwsq(CommonsUtil.isNullOrEmpty(rwsq));
					xzyzhgzh.setReportsj(DateBean.getStrDate(RQ));
					updateflg = LineMeasureService.updatedata(xzyzhgzh);
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
					PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "3", "夏转油综合岗日报", reportM.getRpdReportMessageId());
					logService.addLog(log);
				} catch (Exception e) {
					e.printStackTrace();
					obj = CommonsUtil.getRrturnJson("","夏转油综合岗日报修改失败" ,"", null, null);
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
				
				obj = CommonsUtil.getRrturnJson("","夏转油综合岗日报ID" ,"", null, null);
				out.print(obj);
				return null;
			}
			
			if(reportMessage != null && reportMessage.size()>0){
				reportM = reportMessage.get(0);
			}else{
				obj = CommonsUtil.getRrturnJson("","未获取到夏转油综合岗日报ID对应数据" ,"", null, null);
				out.print(obj);
				return null;
			}
			reportM.setShr(user1.getOperName());
			reportM.setShsj(new Date());
			reportM.setBbmc("夏转油综合岗日报");

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
					PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "3", "夏转油综合岗日报", reportM.getRpdReportMessageId());
					logService.addLog(log);
				} catch (Exception e) {
					e.printStackTrace();
					obj = CommonsUtil.getRrturnJson("","夏转油综合岗日报日志修改失败" ,"", null, null);
				}
			}
			out.print(obj);
			return null;
		}
		
		
}
