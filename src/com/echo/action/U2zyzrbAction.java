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
import com.echo.dto.PcRpdU2zyz;
import com.echo.dto.PcRpdU2zyzzh;
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

/**
 * 2#转油站日报表Action
 * */
public class U2zyzrbAction {
	
	HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
	private LineMeasureService LineMeasureService;
	private ReportMessageService reportMessageService;
	private LogService logService;
	private InputStream excelFile = null;
	public String getFileName() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd ");
		String downloadFileName = (sf.format(new Date()).toString())+ "2#转油站日报.xls";
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
//				
				JSONObject butJson = null;
				try {
					butJson = AuthorityUtil.getButtonJson("MENU176", user);
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

	
	public String searchU2()throws Exception{
	
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
		//获取2#转油站工作日数据
		
		PcRpdReportMessageT reportM = null;
	
		if(date == null || "".equals(date)){
	
			date = DateBean.getSystemTime1();
		}
		
		try {
			U1S1S = LineMeasureService.searchLineU2zyzrb(date);
		 
			if(U1S1S != null && U1S1S.size()>0){
				 
				jsonobj = new JSONObject();
				jsonobj.put("U1S1S", U1S1S);
				U1S1SS = LineMeasureService.searchLineU2zyzrb1(date);
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
			reportMessage = reportMessageService.SreachReportMessages("", "2#转油站日报", date);
			if(reportMessage != null && reportMessage.size()>0){
				reportM = reportMessage.get(0);
				
			}else{
				reportM = new PcRpdReportMessageT();
				reportM.setRq(DateBean.getStrDate(date));
				reportM.setBbmc("2#转油站日报");
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
				reportMessage = reportMessageService.SreachReportMessages("", "2#转油站日报", date);
				reportM = reportMessage.get(0);
				if(updateflg){
					//添加系统LOG
					try {
						User user1 = (User) session.getAttribute("userInfo");
						PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "1", "2#转油站日报", reportM.getRpdReportMessageId());
						logService.addLog(log);
					} catch (Exception e) {
						e.printStackTrace();
						obj = CommonsUtil.getRrturnJson("","2#转油站日志添加失败" ,"", null, null);
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
	

	
	
	//2#转油站日报表查询
	@SuppressWarnings("unused")
	public String searchU2ZYZ() throws IOException{
		//设置输出格式
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		//定义PcRpdReportMessageT的一个对象
		List<PcRpdReportMessageT> reportMessage = null;
		
		return "";
	}
	
	
	
	//2#转油站日报表  报表导出
	public String exportU2ZYZRB() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		String txtDate = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("txtDate")));
		if ("".equals(txtDate)) {
			txtDate = DateBean.getSystemTime1();
//			return null;
		}
		String templetFilePath = request.getSession().getServletContext().getRealPath("/") + "exceltemplet\\combination\\2#转油站日报.xls";
		HSSFWorkbook wb = ExcelToHtmlUtils.loadXls( new File( templetFilePath ) );
		HSSFSheet sheet = wb.getSheetAt(0);	//获取到工作表，因为一个excel可能有多个工作表	
		wb.setSheetName(0, txtDate);			
		PropertiesConfig pc = new PropertiesConfig();
		String stratime =DateBean.getDynamicTime("-16", txtDate, "0");
		String endtime =DateBean.getDynamicTime("-16", txtDate, "1");
		String fields1 = "select PT104_1,PT105_1,jrl1jkwd,jrl1ckwd,jrl1jqyl,jrl1sw,PT106_1,PT107_1,jrl2jkwd,jrl2ckwd,jrl2jqyl,jrl2sw,cyqqy,cyqfy,cyqyw,flq1jyyl,flq1qy,flq1fy,flq1yw,flq2jyyl,flq2qy,flq2fy,flq2yw from  PC_RPD_ZYZ2_T a where 1=1"+
				" and a.BBSJ between TO_DATE('"+stratime+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+endtime+"','YYYY-MM-DD HH24:MI:SS') order by a.BBSJ";			
		List<Object[]> list = LineMeasureService.searchExportData("",fields1);	

		if (list != null && list.size() > 0) {				
			U2DataExportUtil.insertDataByPosition(list, wb, sheet, "B6");
		}
		
		
		String fields2	= "select wsb1kyl,PT108_1,TT104_1,wsb1ckwd,wsb1pl,wsb2kyl,PT109_1,TT105_1,wsb2ckwd,wsb2pl,LIT105_1,LIT106_1,stllllj,cyqlllj from  PC_RPD_ZYZ2_T a where 1=1"+
				" and a.BBSJ between TO_DATE('"+stratime+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+endtime+"','YYYY-MM-DD HH24:MI:SS') order by a.BBSJ";
		
		list = LineMeasureService.searchExportData("",fields2);	
		if (list != null && list.size() > 0) {				
			U2DataExportUtil.insertDataByPosition(list, wb, sheet, "B22");
		}
		
		String baseInfoSql = "select rxc,rcye,rcyou,rcq,ryq from  PC_RPD_ZYZ2_general_T a where 1=1 and a.rq = TO_DATE('"+txtDate+"','YYYY-MM-DD')";
		list = LineMeasureService.searchExportData("", baseInfoSql);
		if (list != null && list.size() > 0) {
			String[] basePostion = pc.getSystemConfiguration( "U2Insert" ).split(",");
			for (int j = 0; j < basePostion.length; j++) {
				if (list.get(0)[j] != null) {
					//U2DataExportUtil.insertDataByPosition(list2, wb, sheet, "P22");
					U2DataExportUtil.insertExcelData(wb, sheet, basePostion[j], 0, new Object[]{list.get(0)[j]});
				}
			}
		}
		String baseInfoSql1 = "select r.shr,r.rq from pc_rpd_report_message_t r where r.bbmc='2#转油站日报' and r.rq=TO_DATE('" + txtDate + "','YYYY-MM-DD') ";
		list = LineMeasureService.searchExportData("", baseInfoSql1);
		if (list != null && list.size() > 0) {
			// 获取单位、值班人、审核人、日期、备注数据插入位置
			String[] basePostion = pc.getSystemConfiguration( "U2omInsert" ).split(",");
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
	
	
	
	
	
	//修改报表
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
		String rxc = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("rxc")));
		String rcye = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("rcye")));
		String rcyou = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("rcyou")));
		String rcq = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("rcq")));
		String ryq = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("ryq")));
		
		List<PcRpdU2zyzzh> zyzzh1 = null;
		PcRpdU2zyzzh u2zyzzh = null;

		if(ID != null && !"".equals(ID)){
			zyzzh1 = LineMeasureService.SreachU2zyzzh(ID, "");
		}			
		if(zyzzh1 != null && zyzzh1.size()>0){
			u2zyzzh = zyzzh1.get(0);
		}else{
			u2zyzzh = new PcRpdU2zyzzh();
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
				
				reportMessage = reportMessageService.SreachReportMessages("", "2#转油站日报", RQ);
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
		reportM.setBbmc("2#转油站日报");
		
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String firstStr = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("firstStr")));
		firstStr = firstStr.substring(0,firstStr.length()-1);
		String[] firstList = firstStr.split(";",-1);
		String beforetime = DateBean.getBeforeDAYTime(RQ);
		for(int i=0;i<firstList.length;i++){
			String[] firstPra = firstList[i].split(",",-1);
			System.out.println(firstList.length);
		
				String id = firstPra[0];
				System.out.println("***********************"+id);
				List<PcRpdU2zyz> usList = null;
				PcRpdU2zyz us = null;
				if(id != null && !"".equals(id)){
					try {
						usList = LineMeasureService.SreachU2zyz(id, "");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				if(usList != null && usList.size()>0){
					us = usList.get(0);					
				}else{
//					obj = CommonsUtil.getRrturnJson("","报表ID获取失败，没有此时的您要修改的数据，请选择其他时间" ,"", null, null);
//					out.print(obj);
//					return null;
					us = new PcRpdU2zyz();
				}				
				if(firstPra[1].equals("00:00")){
					beforetime = RQ;
				}
				us.setReportsj(sf.parse(beforetime +" "+ firstPra[1]));
				System.out.println("测试"+sf.parse(beforetime +" "+ firstPra[1]));
//				us.setRpdU2WaterAutoId(firstPra[0]);
				us.setPT104_1(CommonsUtil.isNullOrEmpty(firstPra[2]));
				us.setPT105_1(CommonsUtil.isNullOrEmpty(firstPra[3]));
				us.setJrl1jkwd(CommonsUtil.isNullOrEmpty(firstPra[4]));
				us.setJrl1ckwd(CommonsUtil.isNullOrEmpty(firstPra[5]));
				us.setJrl1jqyl(CommonsUtil.isNullOrEmpty(firstPra[6]));
				us.setJrl1sw(CommonsUtil.isNullOrEmpty(firstPra[7]));
				us.setPT106_1(CommonsUtil.isNullOrEmpty(firstPra[8]));
				us.setPT107_1(CommonsUtil.isNullOrEmpty(firstPra[9]));
				us.setJrl2jkwd(CommonsUtil.isNullOrEmpty(firstPra[10]));
				us.setJrl2ckwd(CommonsUtil.isNullOrEmpty(firstPra[11]));
				us.setJrl2jqyl(CommonsUtil.isNullOrEmpty(firstPra[12]));
				us.setJrl2sw(CommonsUtil.isNullOrEmpty(firstPra[13]));
				us.setCyqqy(CommonsUtil.isNullOrEmpty(firstPra[14]));
				us.setCyqfy(CommonsUtil.isNullOrEmpty(firstPra[15]));
				us.setCyqyw(CommonsUtil.isNullOrEmpty(firstPra[16]));
				us.setFlq1jyyl(CommonsUtil.isNullOrEmpty(firstPra[17]));
				us.setFlq1qy(CommonsUtil.isNullOrEmpty(firstPra[18]));
				us.setFlq1fy(CommonsUtil.isNullOrEmpty(firstPra[19]));
				us.setFlq1yw(CommonsUtil.isNullOrEmpty(firstPra[20]));
				us.setFlq2jyyl(CommonsUtil.isNullOrEmpty(firstPra[21]));
				us.setFlq2qy(CommonsUtil.isNullOrEmpty(firstPra[22]));
				us.setFlq2fy(CommonsUtil.isNullOrEmpty(firstPra[23]));
				us.setFlq2yw(CommonsUtil.isNullOrEmpty(firstPra[24]));
				us.setWsb1kyl(CommonsUtil.isNullOrEmpty(firstPra[25]));
				us.setPT108_1(CommonsUtil.isNullOrEmpty(firstPra[26]));
				us.setTT104_1(CommonsUtil.isNullOrEmpty(firstPra[27]));
				us.setWsb1ckwd(CommonsUtil.isNullOrEmpty(firstPra[28]));
				us.setWsb1pl(CommonsUtil.isNullOrEmpty(firstPra[29]));
				us.setWsb2kyl(CommonsUtil.isNullOrEmpty(firstPra[30]));
				us.setPT109_1(CommonsUtil.isNullOrEmpty(firstPra[31]));
				
				us.setTT105_1(CommonsUtil.isNullOrEmpty(firstPra[32]));
				us.setWsb2ckwd(CommonsUtil.isNullOrEmpty(firstPra[33]));
				us.setWsb2pl(CommonsUtil.isNullOrEmpty(firstPra[34]));
				us.setLIT105_1(CommonsUtil.isNullOrEmpty(firstPra[35]));
				us.setLIT106_1(CommonsUtil.isNullOrEmpty(firstPra[36]));
				us.setStllllj(CommonsUtil.isNullOrEmpty(firstPra[37]));
				us.setCyqlllj(CommonsUtil.isNullOrEmpty(firstPra[38]));

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
			if(!rxc.equals("") || !rcye.equals("") || !rcyou.equals("") || !rcq.equals("") || !ryq.equals("")){
//				u2tsjy1.setRpdU2TSJYId(jyid1);
				u2zyzzh.setRxc(CommonsUtil.isNullOrEmpty(rxc));
				u2zyzzh.setRcye(CommonsUtil.isNullOrEmpty(rcye));
				u2zyzzh.setRcyou(CommonsUtil.isNullOrEmpty(rcyou));
				u2zyzzh.setRcq(CommonsUtil.isNullOrEmpty(rcq));
				u2zyzzh.setRyq(CommonsUtil.isNullOrEmpty(ryq));
				u2zyzzh.setReportsj(DateBean.getStrDate(RQ));
				updateflg = LineMeasureService.updatedata(u2zyzzh);
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
				PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "3", "2#转油站日报", reportM.getRpdReportMessageId());
				logService.addLog(log);
			} catch (Exception e) {
				e.printStackTrace();
				obj = CommonsUtil.getRrturnJson("","2#转油站日报修改失败" ,"", null, null);
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
			
			obj = CommonsUtil.getRrturnJson("","2#转油站日报ID" ,"", null, null);
			out.print(obj);
			return null;
		}
		
		if(reportMessage != null && reportMessage.size()>0){
			reportM = reportMessage.get(0);
		}else{
			obj = CommonsUtil.getRrturnJson("","未获取到2#转油站日报ID对应数据" ,"", null, null);
			out.print(obj);
			return null;
		}
		reportM.setShr(user1.getOperName());
		reportM.setShsj(new Date());
		reportM.setBbmc("2#转油站日报");

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
				PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "3", "2#转油站日报", reportM.getRpdReportMessageId());
				logService.addLog(log);
			} catch (Exception e) {
				e.printStackTrace();
				obj = CommonsUtil.getRrturnJson("","2#转油站日报日志修改失败" ,"", null, null);
			}
		}
		out.print(obj);
		return null;
	}
}
