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
import com.echo.dto.PcRpdReportRhs2T;
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

	public class u2rhsAction{

		/**
		 * 
		 */

		HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
		private InputStream excelFile = null;
		private LineMeasureService LineMeasureService;
		private ReportMessageService reportMessageService;
		
		
		public LineMeasureService getLineMeasureService() {
			return LineMeasureService;
		}

		public void setLineMeasureService(LineMeasureService LineMeasureService) {
			this.LineMeasureService = LineMeasureService;
		}




		private LogService logService;

		public LogService getLogService() {
			return logService;
		}

		public void setLogService(LogService logService) {
			this.logService = logService;
		}
		


		public ReportMessageService getReportMessageService() {
			return reportMessageService;
		}

		public void setReportMessageService(ReportMessageService reportMessageService) {
			this.reportMessageService = reportMessageService;
		}

		public String getFileName() {
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd ");
			String downloadFileName = (sf.format(new Date()).toString())+ "2#软化水站综合日报.xls";
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
				butJson = AuthorityUtil.getButtonJson("MENU209", user);
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
		
		
		
		
		
		public String searchZHRB() throws Exception {
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
				U2OILs = LineMeasureService.searchU2rhz(txtDate);			
				
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
				reportMessage = reportMessageService.SreachReportMessages("", "2#软化水站综合日报", txtDate);
				if(reportMessage != null && reportMessage.size()>0){
					reportM = reportMessage.get(0);
				}else{
					reportM = new PcRpdReportMessageT();
					reportM.setRq(DateBean.getStrDate(txtDate));
					reportM.setBbmc("2#软化水站综合日报");

								
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
					reportMessage = reportMessageService.SreachReportMessages("", "2#软化水站综合日报", txtDate);
					reportM = reportMessage.get(0);
					if(updateflg){
						//添加系统LOG
						try {
							User user1 = (User) session.getAttribute("userInfo");
							PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "1", "2#软化水站综合日报", reportM.getRpdReportMessageId());
							logService.addLog(log);
						} catch (Exception e) {
							e.printStackTrace();
							obj = CommonsUtil.getRrturnJson("","2#软化水站综合日报日志添加失败" ,"", null, null);
						}
					}
				}
				obj = new JSONObject();
				obj.put("RPDREPORTMESSAGEID", reportM.getRpdReportMessageId());
				obj.put("ZBR1", reportM.getZbr1());
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

		
		//修改
		@SuppressWarnings("null")
		public String update() throws Exception {
			HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
			HttpServletResponse response=ServletActionContext.getResponse();//响应对象
			response.setCharacterEncoding("utf-8");
			response.setHeader("ContentType","text/xml");
			PrintWriter out = response.getWriter();
			JSONObject obj = new JSONObject();	
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			User user = (User) session.getAttribute("userInfo");	
			String RPDREPORTMESSAGEID = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("RPDREPORTMESSAGEID")));
			String RQ = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("RQ")));
			List<PcRpdReportMessageT> reportMessage = null;
			PcRpdReportMessageT reportM = null;
			if(RPDREPORTMESSAGEID != null && !"".equals(RPDREPORTMESSAGEID)){
				reportMessage = reportMessageService.SreachReportMessages(RPDREPORTMESSAGEID, "", "");
				
				//reportM.setZbr1(user.getOperName());
			}			
			if(reportMessage != null && reportMessage.size()>0){
				reportM = reportMessage.get(0);
			}else{
				if(RQ != null && !"".equals(RQ)){					
					reportMessage = reportMessageService.SreachReportMessages("", "2#软化水站综合日报", RQ);
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
			reportM.setBbmc("2#软化水站综合日报");					
			reportM.setZbr1(user.getOperName());
		

			String firstStr = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("firstStr")));	
			firstStr = firstStr.substring(0,firstStr.length()-1);
			String[] firstList = firstStr.split(";",-1);
			for(int i=0;i<firstList.length;i++){
				String[] firstPra = firstList[i].split(",",-1);
					String id = firstPra[0];
					List<PcRpdReportRhs2T> usList = null;
					PcRpdReportRhs2T us = null;
					if(id != null && !"".equals(id)){
						try {
							usList = LineMeasureService.Sreachu2rhs(id, "");
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					if(usList != null && usList.size()>0){
						us = usList.get(0);						
					}else{
						us = new PcRpdReportRhs2T();
					}										
					us.setReport_date(sf.parse(RQ));
					us.setRhqts_72qs(CommonsUtil.isNullOrEmpty(firstPra[2]));
					us.setRhqts_72ws(CommonsUtil.isNullOrEmpty(firstPra[3]));
					us.setRhqts_216ws(CommonsUtil.isNullOrEmpty(firstPra[4]));
					us.setYxzs_72qs(CommonsUtil.isNullOrEmpty(firstPra[5]));
					us.setYxzs_72ws(CommonsUtil.isNullOrEmpty(firstPra[6]));
					us.setYxzs_216ws(CommonsUtil.isNullOrEmpty(firstPra[7]));
					us.setSs_72qs(CommonsUtil.isNullOrEmpty(firstPra[8]));
					us.setSs_72ws(CommonsUtil.isNullOrEmpty(firstPra[9]));					
					us.setSs_216ws(CommonsUtil.isNullOrEmpty(firstPra[10]));
					us.setRzl_72qs(CommonsUtil.isNullOrEmpty(firstPra[11]));
					us.setRzl_72ws(CommonsUtil.isNullOrEmpty(firstPra[12]));
					us.setRzl_216ws(CommonsUtil.isNullOrEmpty(firstPra[13]));
					us.setZszs_72qs(CommonsUtil.isNullOrEmpty(firstPra[14]));
					us.setZszs_72ws(CommonsUtil.isNullOrEmpty(firstPra[15]));
					us.setZszs_216ws(CommonsUtil.isNullOrEmpty(firstPra[16]));
					us.setZsysl_72qs(CommonsUtil.isNullOrEmpty(firstPra[17]));					
					us.setZsysl_72ws(CommonsUtil.isNullOrEmpty(firstPra[18]));
					us.setZsysl_216ws(CommonsUtil.isNullOrEmpty(firstPra[19]));
					us.setDwcy_hy_2r(CommonsUtil.isNullOrEmpty(firstPra[20]));
					us.setJyb_hy_2r(CommonsUtil.isNullOrEmpty(firstPra[21]));
					us.setGL3529_HY(CommonsUtil.isNullOrEmpty(firstPra[22]));
					us.setZ45sagd_hy(CommonsUtil.isNullOrEmpty(firstPra[23]));
					us.setGwcy_jzs(CommonsUtil.isNullOrEmpty(firstPra[24]));
					us.setJyb_jzs(CommonsUtil.isNullOrEmpty(firstPra[25]));
					us.setZ1sagd_hy(CommonsUtil.isNullOrEmpty(firstPra[26]));					
					us.setDwcy_hy_2r_jcr(firstPra[27]);
					us.setJyb_hy_2r_jcr(firstPra[28]);
					us.setGL3529_HY_JCR(firstPra[29]);
					us.setZ45sagd_hy_jcr(firstPra[30]);
					us.setZ1sagd_hy_jcr(firstPra[31]);
					us.setGwcy_jzs_jcr(firstPra[32]);
					us.setJyb_jzs_jcr(firstPra[33]);					
					us.setSds_bb(CommonsUtil.isNullOrEmpty(firstPra[34]));
					us.setSds_yb(CommonsUtil.isNullOrEmpty(firstPra[35]));
					us.setSds_kc(CommonsUtil.isNullOrEmpty(firstPra[36]));
					us.setQs_yd(CommonsUtil.isNullOrEmpty(firstPra[37]));
					us.setQs_lg(CommonsUtil.isNullOrEmpty(firstPra[38]));
					us.setQs_jd(CommonsUtil.isNullOrEmpty(firstPra[39]));
					us.setWs_yd(CommonsUtil.isNullOrEmpty(firstPra[40]));
					us.setWs_lg(CommonsUtil.isNullOrEmpty(firstPra[41]));
					us.setWs_jd(CommonsUtil.isNullOrEmpty(firstPra[42]));					
					us.setGhj_yd(CommonsUtil.isNullOrEmpty(firstPra[43]));
					us.setGhj_lg(CommonsUtil.isNullOrEmpty(firstPra[44]));
					us.setGhj_jd(CommonsUtil.isNullOrEmpty(firstPra[45]));
					us.setHy1(CommonsUtil.isNullOrEmpty(firstPra[46]));
					us.setHy2(CommonsUtil.isNullOrEmpty(firstPra[47]));
					us.setHy3(CommonsUtil.isNullOrEmpty(firstPra[48]));
					us.setXf1(CommonsUtil.isNullOrEmpty(firstPra[49]));
					us.setXf2(CommonsUtil.isNullOrEmpty(firstPra[50]));
					us.setXf3(CommonsUtil.isNullOrEmpty(firstPra[51]));					
					us.setXgnh_d1(CommonsUtil.isNullOrEmpty(firstPra[52]));
					us.setXgnh_d2(CommonsUtil.isNullOrEmpty(firstPra[53]));
					us.setXgnh_d3(CommonsUtil.isNullOrEmpty(firstPra[54]));
					us.setXgnh_d4(CommonsUtil.isNullOrEmpty(firstPra[55]));
					us.setXgnh_y1(CommonsUtil.isNullOrEmpty(firstPra[56]));
					us.setXgnh_y2(CommonsUtil.isNullOrEmpty(firstPra[57]));
					us.setXgnh_y3(CommonsUtil.isNullOrEmpty(firstPra[58]));
					us.setXgnh_y4(CommonsUtil.isNullOrEmpty(firstPra[59]));
					us.setXgnh_y5(CommonsUtil.isNullOrEmpty(firstPra[60]));					
					us.setXgnh_y6(CommonsUtil.isNullOrEmpty(firstPra[61]));
				
					us.setCh1(firstPra[62]);
					us.setCh2(firstPra[63]);
					us.setCh3(firstPra[64]);
					us.setCh4(firstPra[65]);
					us.setCh5(firstPra[66]);
					us.setCh6(firstPra[67]);
					us.setXgnh_y1_jsr(firstPra[68]);
					us.setXgnh_y2_jsr(firstPra[69]);
					us.setXgnh_y3_jsr(firstPra[70]);
					us.setXgnh_y4_jsr(firstPra[71]);
					us.setXgnh_y5_jsr(firstPra[72]);
					us.setXgnh_y6_jsr(firstPra[73]);
					us.setFst_ys(CommonsUtil.isNullOrEmpty(firstPra[74]));
					us.setFst_cs(CommonsUtil.isNullOrEmpty(firstPra[75]));
					us.setHzbsl(CommonsUtil.isNullOrEmpty(firstPra[76]));
					us.setHgbsl(CommonsUtil.isNullOrEmpty(firstPra[77]));
					us.setYcpwsl(CommonsUtil.isNullOrEmpty(firstPra[78]));


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
				obj = CommonsUtil.getRrturnJson("","2#软化水站综合日报ID" ,"", null, null);
				out.print(obj);
				return null;
			}
			
			if(reportMessage != null && reportMessage.size()>0){
				reportM = reportMessage.get(0);
			}else{
				obj = CommonsUtil.getRrturnJson("","未获取到2#软化水站综合日报ID对应数据" ,"", null, null);
				out.print(obj);
				return null;
			}
			reportM.setShr(user1.getOperName());
			reportM.setShsj(new Date());
			reportM.setBbmc("2#软化水站综合日报");

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
					PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "3", "2#软化水站综合日报", reportM.getRpdReportMessageId());
					logService.addLog(log);
				} catch (Exception e) {
					e.printStackTrace();
					obj = CommonsUtil.getRrturnJson("","2#软化水站综合日报日志修改失败" ,"", null, null);
				}
			}
			out.print(obj);
			return null;
		}
		
		
		

		public String exportData() throws Exception {
			HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
			String txtDate = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("txtDate")));
			if ("".equals(txtDate)) {
				txtDate = DateBean.getSystemTime1();
			}
			String templetFilePath = request.getSession().getServletContext().getRealPath("/") + "exceltemplet\\combination\\2#软化水站综合日报.xls";

			HSSFWorkbook wb = ExcelToHtmlUtils.loadXls( new File( templetFilePath ) );
			HSSFSheet sheet = wb.getSheetAt(0);

			PropertiesConfig pc = new PropertiesConfig();
			String Others = pc.getSystemConfiguration("tabletop"); //值班人,审核人，日期
			
			String SqlRhq = pc.getSystemConfiguration("SqlRhq"); //软化器台数
			String insertRhq =  pc.getSystemConfiguration("insertRhq");	//软化器台数插入位置
			String SqlYxzs = pc.getSystemConfiguration("SqlYxzs"); //运行组数
			String insertYxzs =  pc.getSystemConfiguration("insertYxzs");	//运行组数插入位置
			String SqlSs = pc.getSystemConfiguration("SqlSs"); //合计瞬时量
			String insertSs =  pc.getSystemConfiguration("insertSs");	//合计瞬时量插入位置
			String SqlRzl = pc.getSystemConfiguration("SqlRzl"); //日总量
			String insertRzl =  pc.getSystemConfiguration("insertRzl");	//日总量插入位置
			String SqlZszs = pc.getSystemConfiguration("SqlZszs"); //日再生组数
			String insertZszs =  pc.getSystemConfiguration("insertZszs");	//日再生组数插入位置
			String SqlZsysl = pc.getSystemConfiguration("SqlZsysl"); //再生用水量
			String insertZsysl =  pc.getSystemConfiguration("insertZsysl");	//再生用水量插入位置
			
			String SqlD1 = pc.getSystemConfiguration("SqlD1"); //电1#
			String insertD1 =  pc.getSystemConfiguration("insertD1");	//电1#插入位置
			String SqlD2 = pc.getSystemConfiguration("SqlD2"); //电2#
			String insertD2 =  pc.getSystemConfiguration("insertD2");	//电2#插入位置
			String SqlD3 = pc.getSystemConfiguration("SqlD3"); //电3#
			String insertD3 =  pc.getSystemConfiguration("insertD3");	//电3#插入位置
			String SqlD4 = pc.getSystemConfiguration("SqlD4"); //电4#
			String insertD4 =  pc.getSystemConfiguration("insertD4");	//电4#插入位置
			String SqlY1 = pc.getSystemConfiguration("SqlY1"); //盐1
			String insertY1 =  pc.getSystemConfiguration("insertY1");	//盐1插入位置
			String SqlY2 = pc.getSystemConfiguration("SqlY2"); //盐2
			String insertY2 =  pc.getSystemConfiguration("insertY2");	//盐2插入位置
			String SqlY3 = pc.getSystemConfiguration("SqlY3"); //盐3
			String insertY3 =  pc.getSystemConfiguration("insertY3");	//盐3插入位置
			String SqlY4 = pc.getSystemConfiguration("SqlY4"); //盐4
			String insertY4 =  pc.getSystemConfiguration("insertY4");	//盐4插入位置
			String SqlY5 = pc.getSystemConfiguration("SqlY5"); //盐5
			String insertY5 =  pc.getSystemConfiguration("insertY5");	//盐5插入位置
			String SqlY6 = pc.getSystemConfiguration("SqlY6"); //盐6
			String insertY6 =  pc.getSystemConfiguration("insertY6");	//盐6插入位置
			
			String SqlDWCY = pc.getSystemConfiguration("SqlDWCY"); //2#软化水除氧器出口
			String insertDWCY =  pc.getSystemConfiguration("insertDWCY");	//2#软化水除氧器出口插入位置
			String SqlJYB_HY = pc.getSystemConfiguration("SqlJYB_HY"); //2#软化水加药泵出口
			String insertJYB_HY =  pc.getSystemConfiguration("insertJYB_HY");	//2#软化水加药泵出口插入位置
			String SqlGL35_29 = pc.getSystemConfiguration("SqlGL35_29"); //35-29#站锅炉泵进口
			String insertGL35_29 =  pc.getSystemConfiguration("insertGL35_29");	//35-29#站锅炉泵进口插入位置
			String SqlZ45SAGD = pc.getSystemConfiguration("SqlZ45SAGD"); //重45SAGD站锅炉泵进口
			String insertZ45SAGD =  pc.getSystemConfiguration("insertZ45SAGD");	//重45SAGD站锅炉泵进口插入位置
			String SqlZ1SAGD = pc.getSystemConfiguration("SqlZ1SAGD"); //重1-1#站锅炉泵进口
			String insertZ1SAGD =  pc.getSystemConfiguration("insertZ1SAGD");	//重1-1#站锅炉泵进口插入位置
			String SqlGWCY = pc.getSystemConfiguration("SqlGWCY"); //集中水处理除氧器出口
			String insertGWCY =  pc.getSystemConfiguration("insertGWCY");	//集中水处理除氧器出口插入位置
			String SqlJYB = pc.getSystemConfiguration("SqlJYB"); //集中水处理加药泵出口
			String insertJYB =  pc.getSystemConfiguration("insertJYB");	//集中水处理加药泵出口插入位置
			
			String SqlSDS = pc.getSystemConfiguration("SqlSDS"); //亚硫酸钠加药量
			String insertSDS =  pc.getSystemConfiguration("insertSDS");	//亚硫酸钠加药量入位置
			String SqlYS = pc.getSystemConfiguration("SqlYS"); //用水
			String insertYS =  pc.getSystemConfiguration("insertYS");	//用水插入位置
			String SqlCS = pc.getSystemConfiguration("SqlCS"); //产水
			String insertCS =  pc.getSystemConfiguration("insertCS");	//产水入位置
			String SqlHZBSL = pc.getSystemConfiguration("SqlHZBSL"); //回注泵
			String insertHZBSL =  pc.getSystemConfiguration("insertHZBSL");	//回注泵插入位置
			String SqlHGBSL = pc.getSystemConfiguration("SqlHGBSL"); //回灌泵
			String insertHGBSL =  pc.getSystemConfiguration("insertHGBSL");	//回灌泵插入位置
			String SqlYCPWSL = pc.getSystemConfiguration("SqlYCPWSL"); //宇澄排污
			String insertYCPWSL =  pc.getSystemConfiguration("insertYCPWSL");	//宇澄排污插入位置
			String SqlQS = pc.getSystemConfiguration("SqlQS"); //清水
			String insertQS =  pc.getSystemConfiguration("insertQS");	//清水插入位置
			String SqlWS = pc.getSystemConfiguration("SqlWS"); //污水
			String insertWS =  pc.getSystemConfiguration("insertWS");	//污水插入位置
			String SqlGHJ = pc.getSystemConfiguration("SqlGHJ"); //管汇间
			String insertGHJ =  pc.getSystemConfiguration("insertGHJ");	//管汇间插入位置
			String SqlHY = pc.getSystemConfiguration("SqlHY"); //含油
			String insertHY =  pc.getSystemConfiguration("insertHY");	//含油插入位置
			String SqlXF = pc.getSystemConfiguration("SqlXF"); //悬浮
			String insertXF =  pc.getSystemConfiguration("insertXF");	//悬浮插入位置

	
			List<Object[]> listrhq = LineMeasureService.searchExportDatas(txtDate, SqlRhq);
			if (listrhq != null && listrhq.size() > 0) {
				U2DataExportUtil.insertDataByPosition(listrhq, wb, sheet, insertRhq);
			}			
			List<Object[]> listyxzs = LineMeasureService.searchExportDatas(txtDate, SqlYxzs);
			if (listyxzs != null && listyxzs.size() > 0) {
				U2DataExportUtil.insertDataByPosition(listyxzs, wb, sheet, insertYxzs);
			}
			List<Object[]> listss = LineMeasureService.searchExportDatas(txtDate, SqlSs);
			if (listss != null && listss.size() > 0) {
				U2DataExportUtil.insertDataByPosition(listss, wb, sheet, insertSs);
			}
			List<Object[]> listrzl = LineMeasureService.searchExportDatas(txtDate, SqlRzl);
			if (listrzl != null && listrzl.size() > 0) {
				U2DataExportUtil.insertDataByPosition(listrzl, wb, sheet, insertRzl);
			}			
			List<Object[]> listzszs = LineMeasureService.searchExportDatas(txtDate, SqlZszs);
			if (listzszs != null && listzszs.size() > 0) {
				U2DataExportUtil.insertDataByPosition(listyxzs, wb, sheet, insertZszs);
			}
			List<Object[]> listzsysl = LineMeasureService.searchExportDatas(txtDate, SqlZsysl);
			if (listzsysl != null && listzsysl.size() > 0) {
				U2DataExportUtil.insertDataByPosition(listzsysl, wb, sheet, insertZsysl);
			}

			
			List<Object[]> listd1 = LineMeasureService.searchExportDatas(txtDate, SqlD1);
			if (listd1 != null && listd1.size() > 0) {
				U2DataExportUtil.insertDataByPosition(listd1, wb, sheet, insertD1);
			}
			List<Object[]> listd2 = LineMeasureService.searchExportDatas(txtDate, SqlD2);
			if (listd2 != null && listd2.size() > 0) {
				U2DataExportUtil.insertDataByPosition(listd2, wb, sheet, insertD2);
			}
			List<Object[]> listd3 = LineMeasureService.searchExportDatas(txtDate, SqlD3);
			if (listd3 != null && listd3.size() > 0) {
				U2DataExportUtil.insertDataByPosition(listd3, wb, sheet, insertD3);
			}
			List<Object[]> listd4 = LineMeasureService.searchExportDatas(txtDate, SqlD4);
			if (listd4 != null && listd4.size() > 0) {
				U2DataExportUtil.insertDataByPosition(listd4, wb, sheet, insertD4);
			}
			List<Object[]> listy1 = LineMeasureService.searchExportDatas(txtDate, SqlY1);
			if (listy1 != null && listy1.size() > 0) {
				U2DataExportUtil.insertDataByPosition(listy1, wb, sheet, insertY1);
			}
			List<Object[]> listy2 = LineMeasureService.searchExportDatas(txtDate, SqlY2);
			if (listy2 != null && listy2.size() > 0) {
				U2DataExportUtil.insertDataByPosition(listy2, wb, sheet, insertY2);
			}
			List<Object[]> listy3 = LineMeasureService.searchExportDatas(txtDate, SqlY3);
			if (listy3 != null && listy3.size() > 0) {
				U2DataExportUtil.insertDataByPosition(listy3, wb, sheet, insertY3);
			}
			List<Object[]> listy4 = LineMeasureService.searchExportDatas(txtDate, SqlY4);
			if (listy4 != null && listy4.size() > 0) {
				U2DataExportUtil.insertDataByPosition(listy4, wb, sheet, insertY4);
			}
			List<Object[]> listy5 = LineMeasureService.searchExportDatas(txtDate, SqlY5);
			if (listy5 != null && listy5.size() > 0) {
				U2DataExportUtil.insertDataByPosition(listy5, wb, sheet, insertY5);
			}
			List<Object[]> listy6 = LineMeasureService.searchExportDatas(txtDate, SqlY6);
			if (listy6 != null && listy6.size() > 0) {
				U2DataExportUtil.insertDataByPosition(listy6, wb, sheet, insertY6);
			}
			
			List<Object[]> listdwcy = LineMeasureService.searchExportDatas(txtDate, SqlDWCY);
			if (listdwcy != null && listdwcy.size() > 0) {
				U2DataExportUtil.insertDataByPosition(listdwcy, wb, sheet, insertDWCY);
			}			
			List<Object[]> listjybhy = LineMeasureService.searchExportDatas(txtDate, SqlJYB_HY);
			if (listjybhy != null && listjybhy.size() > 0) {
				U2DataExportUtil.insertDataByPosition(listjybhy, wb, sheet, insertJYB_HY);
			}
			List<Object[]> listgl3529 = LineMeasureService.searchExportDatas(txtDate, SqlGL35_29);
			if (listgl3529 != null && listgl3529.size() > 0) {
				U2DataExportUtil.insertDataByPosition(listgl3529, wb, sheet, insertGL35_29);
			}
			List<Object[]> listz45sagd = LineMeasureService.searchExportDatas(txtDate, SqlZ45SAGD);
			if (listz45sagd != null && listz45sagd.size() > 0) {
				U2DataExportUtil.insertDataByPosition(listz45sagd, wb, sheet, insertZ45SAGD);
			}			
			List<Object[]> listz1sagd = LineMeasureService.searchExportDatas(txtDate, SqlZ1SAGD);
			if (listz1sagd != null && listz1sagd.size() > 0) {
				U2DataExportUtil.insertDataByPosition(listz1sagd, wb, sheet, insertZ1SAGD);
			}
			List<Object[]> listgwcy = LineMeasureService.searchExportDatas(txtDate, SqlGWCY);
			if (listgwcy != null && listgwcy.size() > 0) {
				U2DataExportUtil.insertDataByPosition(listgwcy, wb, sheet, insertGWCY);
			}
			List<Object[]> listjyb = LineMeasureService.searchExportDatas(txtDate, SqlJYB);
			if (listjyb != null && listjyb.size() > 0) {
				U2DataExportUtil.insertDataByPosition(listjyb, wb, sheet, insertJYB);
			}
			
			
			List<Object[]> listsds = LineMeasureService.searchExportDatas(txtDate, SqlSDS);
			if (listsds != null && listsds.size() > 0) {
				U2DataExportUtil.insertDataByPosition(listsds, wb, sheet, insertSDS);
			}			
			List<Object[]> listys = LineMeasureService.searchExportDatas(txtDate, SqlYS);
			if (listys != null && listys.size() > 0) {
				U2DataExportUtil.insertDataByPosition(listys, wb, sheet, insertYS);
			}
			List<Object[]> listcs = LineMeasureService.searchExportDatas(txtDate, SqlCS);
			if (listcs != null && listcs.size() > 0) {
				U2DataExportUtil.insertDataByPosition(listcs, wb, sheet, insertCS);
			}
			List<Object[]> listhzbsl = LineMeasureService.searchExportDatas(txtDate, SqlHZBSL);
			if (listhzbsl != null && listhzbsl.size() > 0) {
				U2DataExportUtil.insertDataByPosition(listhzbsl, wb, sheet, insertHZBSL);
			}			
			List<Object[]> listhgbsl = LineMeasureService.searchExportDatas(txtDate, SqlHGBSL);
			if (listhgbsl != null && listhgbsl.size() > 0) {
				U2DataExportUtil.insertDataByPosition(listhgbsl, wb, sheet, insertHGBSL);
			}
			List<Object[]> listycpwsl = LineMeasureService.searchExportDatas(txtDate, SqlYCPWSL);
			if (listycpwsl != null && listycpwsl.size() > 0) {
				U2DataExportUtil.insertDataByPosition(listycpwsl, wb, sheet, insertYCPWSL);
			}
			List<Object[]> listqs = LineMeasureService.searchExportDatas(txtDate, SqlQS);
			if (listqs != null && listqs.size() > 0) {
				U2DataExportUtil.insertDataByPosition(listqs, wb, sheet, insertQS);
			}
			List<Object[]> listws = LineMeasureService.searchExportDatas(txtDate, SqlWS);
			if (listws != null && listws.size() > 0) {
				U2DataExportUtil.insertDataByPosition(listws, wb, sheet, insertWS);
			}			
			List<Object[]> listghj = LineMeasureService.searchExportDatas(txtDate, SqlGHJ);
			if (listghj != null && listghj.size() > 0) {
				U2DataExportUtil.insertDataByPosition(listghj, wb, sheet, insertGHJ);
			}
			List<Object[]> listhy = LineMeasureService.searchExportDatas(txtDate, SqlHY);
			if (listhy != null && listhy.size() > 0) {
				U2DataExportUtil.insertDataByPosition(listhy, wb, sheet, insertHY);
			}
			List<Object[]> listxf = LineMeasureService.searchExportDatas(txtDate, SqlXF);
			if (listxf != null && listxf.size() > 0) {
				U2DataExportUtil.insertDataByPosition(listxf, wb, sheet, insertXF);
			}
			
//			获取值班人、审核人、日期、数据位置
			List<Object[]> otherlList = LineMeasureService.searchExportTopDatas(txtDate, Others);
			if(otherlList !=null && otherlList.size()>0){
				String[] OtherPostion = pc.getSystemConfiguration( "insertTop" ).split(",");
					for (int j = 0; j < OtherPostion.length; j++) {
						if (otherlList.get(0)[j] != null &&  !"".equals(otherlList.get(0)[j])){
							U2DataExportUtil.insertExcelData(wb, sheet, OtherPostion[j], 0, new Object[]{otherlList.get(0)[j]});
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

}


