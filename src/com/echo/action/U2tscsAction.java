package com.echo.action;


import java.io.File;
import java.io.PrintWriter;
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
import com.echo.dto.PcRpdU2TSJY;
import com.echo.dto.PcRpdU2TSWS;
import com.echo.dto.PcRpdU2TSZH;
import com.echo.dto.PcRpdU2WaterAutoT;
import com.echo.dto.User;
import com.echo.service.LogService;
import com.echo.service.ReportMessageService;
import com.echo.service.U1s1gyService;
import com.echo.service.U1s2gyService;
import com.echo.util.AuthorityUtil;
import com.echo.util.CommonsUtil;
import com.echo.util.DateBean;
import com.echo.util.PropertiesConfig;
import com.echo.util.ReportFormsBaseUitl;
import com.echo.util.StringUtil;
import com.echo.util.U2DataExportUtil;

public class U2tscsAction extends ReportFormsBaseUitl{
	/**
	 * // 特二联脱水报表
	 */
	HttpSession  session = ServletActionContext.getRequest().getSession(true); 
	private static final long serialVersionUID = 1L;
	private U1s1gyService u1s1gyService;
	private U1s2gyService u1s2gyService;
	private ReportMessageService reportMessageService;
	private LogService logService;
	
	public void setU1s2gyService(U1s2gyService u1s2gyService) {
		this.u1s2gyService = u1s2gyService;
	}

	public void setReportMessageService(ReportMessageService reportMessageService) {
		this.reportMessageService = reportMessageService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	public void setU1s1gyService(U1s1gyService u1s1gyService) {
		this.u1s1gyService = u1s1gyService;
	}
	private final String fileName = "特二联脱水报表.xls";

	public String getFileName() {
		return super.getFileName(fileName);
	}
	@SuppressWarnings("unused")
	public String searchU2tscs()throws Exception{
			HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
			HttpServletResponse response=ServletActionContext.getResponse();//响应对象
			response.setCharacterEncoding("utf-8");
			response.setHeader("ContentType","text/xml");
			PrintWriter out = response.getWriter();
			List<PcRpdReportMessageT> reportMessage = null;
			JSONArray U1S1S = null;
			JSONArray U1S1SS = null;
			JSONObject obj = null;
			JSONObject obj1 = null;
			JSONObject jsonobj = null;
			String txtDate = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("txtDate")));
			String[] reportMessages = new String[5];
			//获取当天所以二号稠油联合站原油系统日报数据
			
			PcRpdReportMessageT reportM = null;
			if(txtDate == null || "".equals(txtDate)){
				txtDate = DateBean.getSystemTime1();
			}
			
			try {
				obj1 = u1s1gyService.searchU2tscs1(txtDate);
				U1S1S = (JSONArray) obj1.get("arr");
				double lit311 = (Double) obj1.get("lit311");
				if(U1S1S != null && U1S1S.size()>0){
					jsonobj = new JSONObject();
					jsonobj.put("U1S1S", U1S1S);
					U1S1SS = u1s1gyService.searchU2tscs2(txtDate,lit311);
					if(U1S1SS != null && U1S1SS.size()>0){
						jsonobj.put("U1S1SS", U1S1SS);
					}
				}
				
//				else{
//					obj = CommonsUtil.getRrturnJson("","当前日期搜索无数据请选择其他日期" ,"", null, null);
//					out.print(obj);
//					return null;
//				}
			} catch (Exception e) {
				e.printStackTrace();
				obj = CommonsUtil.getRrturnJson("","当前日期搜索失败请选择其他日期" ,"", null, null);
				out.print(obj);
				return null;
			}
			try {
				reportMessage = reportMessageService.SreachReportMessages("", "特二联脱水报表", txtDate);
				if(reportMessage != null && reportMessage.size()>0){
					reportM = reportMessage.get(0);
					
				}else{
					reportM = new PcRpdReportMessageT();
					reportM.setRq(DateBean.getStrDate(txtDate));
					reportM.setBbmc("特二联脱水报表");
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
					reportMessage = reportMessageService.SreachReportMessages("", "特二联脱水报表", txtDate);
					reportM = reportMessage.get(0);
					if(updateflg){
						//添加系统LOG
						try {
							User user1 = (User) session.getAttribute("userInfo");
							PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "1", "特二联脱水报表", reportM.getRpdReportMessageId());
							logService.addLog(log);
						} catch (Exception e) {
							e.printStackTrace();
							obj = CommonsUtil.getRrturnJson("","特二联脱水日志添加失败" ,"", null, null);
						}
					}
				}
				
				
				obj = new JSONObject();
				obj.put("RPDREPORTMESSAGEID", reportM.getRpdReportMessageId());
				obj.put("ZBR1", reportM.getZbr1());
				obj.put("ZBR2", reportM.getZbr2());
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
			
			
			obj = CommonsUtil.getRrturnJson("","" ,"", jsonobj, null);
			out.print(obj);
//			request.setAttribute("U2OILs", U2OILs);
//			request.setAttribute("reportMessages", reportMessages);
			
		return null;
		
		
		

		}
		
		@SuppressWarnings("unused")
		public String pageInit()throws Exception{
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
				butJson = AuthorityUtil.getButtonJson("MENU114", user);
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
				
				obj = CommonsUtil.getRrturnJson("","特二联脱水报表ID" ,"", null, null);
				out.print(obj);
				return null;
			}
			
			if(reportMessage != null && reportMessage.size()>0){
				reportM = reportMessage.get(0);
			}else{
				obj = CommonsUtil.getRrturnJson("","未获取到特二联脱水报表ID对应数据" ,"", null, null);
				out.print(obj);
				return null;
			}
			reportM.setShr(user1.getOperName());
			reportM.setShsj(new Date());
			reportM.setBbmc("特二联脱水报表");

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
					PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "3", "特二联脱水日报表", reportM.getRpdReportMessageId());
					logService.addLog(log);
				} catch (Exception e) {
					e.printStackTrace();
					obj = CommonsUtil.getRrturnJson("","特二联脱水日志修改失败" ,"", null, null);
				}
			}
			out.print(obj);
			return null;
		}
		

		
		
		
		
		
		
		
		
		
		
		
		@SuppressWarnings("unused")
		public String updateU1S1gy() throws Exception {
			HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
			HttpServletResponse response=ServletActionContext.getResponse();//响应对象
			response.setCharacterEncoding("utf-8");
			response.setHeader("ContentType","text/xml");
			PrintWriter out = response.getWriter();
			JSONObject obj = new JSONObject();
			String RPDREPORTMESSAGEID = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("RPDREPORTMESSAGEID")));
			String ZBR1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("ZBR1")));
			String BZ = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("BZ")));
			String RQ = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("RQ")));
			String ZBR2 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("ZBR2")));
			String GH = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("GH")));
			String CYW = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("CYW")));
			String TYW = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("TYW")));
			String WGH = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("WGH")));
			String WCYW = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("WCYW")));
			String WTYW = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("WTYW")));
			String GH2 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("GH2")));
			String CYW2 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("CYW2")));
			String TYW2 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("TYW2")));
			String HCGDSHSSJ = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("HCGDSHSSJ")));
			String HCGDSHSBP = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("HCGDSHSBP")));
			String WGH2 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("WGH2")));
			String WCYW2 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("WCYW2")));
			String WTYW2 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("WTYW2")));
			String jyid1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("jyid1")));
			String jyid2 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("jyid2")));
			String wsid1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("wsid1")));
			String wsid2 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("wsid2")));
			String zhid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("zhid")));
			List<PcRpdU2TSJY> tsjy1 = null;
			PcRpdU2TSJY u2tsjy1 = null;
			List<PcRpdU2TSJY> tsjy2 = null;
			PcRpdU2TSJY u2tsjy2 = null;
			if(jyid1 != null && !"".equals(jyid1)){
				tsjy1 = u1s1gyService.SreachTSJY(jyid1, "");
			}			
			if(tsjy1 != null && tsjy1.size()>0){
				u2tsjy1 = tsjy1.get(0);
			}else{
				u2tsjy1 = new PcRpdU2TSJY();
			}
			if(jyid2 != null && !"".equals(jyid2)){
				tsjy2 = u1s1gyService.SreachTSJY(jyid2, "");
			}			
			if(tsjy2 != null && tsjy2.size()>0){
				u2tsjy2 = tsjy2.get(0);
			}else{
				u2tsjy2 = new PcRpdU2TSJY();
			}
			
			List<PcRpdU2TSWS> tsws1 = null;
			PcRpdU2TSWS u2tsws1 = null;
			List<PcRpdU2TSWS> tsws2 = null;
			PcRpdU2TSWS u2tsws2 = null;
			if(wsid1 != null && !"".equals(wsid1)){
				tsws1 = u1s1gyService.SreachTSWS(wsid1, "");
			}			
			if(tsws1 != null && tsws1.size()>0){
				u2tsws1 = tsws1.get(0);
			}else{
				u2tsws1 = new PcRpdU2TSWS();
			}
			if(wsid2 != null && !"".equals(wsid2)){
				tsws2 = u1s1gyService.SreachTSWS(wsid2, "");
			}			
			if(tsws2 != null && tsws2.size()>0){
				u2tsws2 = tsws2.get(0);
			}else{
				u2tsws2 = new PcRpdU2TSWS();
			}
			
			List<PcRpdU2TSZH> tszh = null;
			PcRpdU2TSZH u2tszh = null;
			if(zhid != null && !"".equals(zhid)){
				tszh = u1s1gyService.SreachTSZH(zhid, "");
			}			
			if(tszh != null && tszh.size()>0){
				u2tszh = tszh.get(0);
			}else{
				u2tszh = new PcRpdU2TSZH();
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
					
					reportMessage = reportMessageService.SreachReportMessages("", "特二联脱水报表", RQ);
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
			reportM.setZbr1(ZBR1);
			reportM.setShr(ZBR2);
			reportM.setRq(DateBean.getStrDate(RQ));
			reportM.setBbmc("特二联脱水报表");
			reportM.setBz(BZ);
			
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String firstStr = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("firstStr")));
			firstStr = firstStr.substring(0,firstStr.length()-1);
			String[] firstList = firstStr.split(";",-1);
			String beforetime = DateBean.getBeforeDAYTime(RQ);
			for(int i=0;i<firstList.length;i++){
				String[] firstPra = firstList[i].split(",",-1);
					String id = firstPra[0];
					List<PcRpdU2WaterAutoT> usList = null;
					PcRpdU2WaterAutoT us = null;
					if(id != null && !"".equals(id)){
						try {
							usList = u1s1gyService.SreachU2S1gy(id, "");
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					if(usList != null && usList.size()>0){
						us = usList.get(0);
						
					}else{
//						obj = CommonsUtil.getRrturnJson("","报表ID获取失败，没有此时的您要修改的数据，请选择其他时间" ,"", null, null);
//						out.print(obj);
//						return null;
						us = new PcRpdU2WaterAutoT();
					}
					
					if(firstPra[1].equals("00:00")){
						beforetime = RQ;
					}
					us.setReportsj(sf.parse(beforetime +" "+ firstPra[1]));
//					us.setRpdU2WaterAutoId(firstPra[0]);
					us.setLIT_10307(CommonsUtil.isNullOrEmpty(firstPra[2]));
					us.setLIT_10308(CommonsUtil.isNullOrEmpty(firstPra[3]));
					us.setLIT_10309(CommonsUtil.isNullOrEmpty(firstPra[4]));
					us.setLIT_10310(CommonsUtil.isNullOrEmpty(firstPra[5]));
					us.setLIT_10311(CommonsUtil.isNullOrEmpty(firstPra[6]));
					us.setLIT_10312(CommonsUtil.isNullOrEmpty(firstPra[7]));
					us.setLIT_10313(CommonsUtil.isNullOrEmpty(firstPra[8]));
					us.setLIT_10314(CommonsUtil.isNullOrEmpty(firstPra[9]));
					us.setLIT_10305(CommonsUtil.isNullOrEmpty(firstPra[10]));
					us.setLIT_10306(CommonsUtil.isNullOrEmpty(firstPra[11]));
					us.setTSB1PL(CommonsUtil.isNullOrEmpty(firstPra[12]));
					us.setTSB2PL(CommonsUtil.isNullOrEmpty(firstPra[13]));
					us.setTSB3PL(CommonsUtil.isNullOrEmpty(firstPra[14]));
					us.setTT_10311A(CommonsUtil.isNullOrEmpty(firstPra[15]));
					us.setWSBPL(CommonsUtil.isNullOrEmpty(firstPra[16]));

	         		boolean updateflg = false;
	        		try {
	        			updateflg = u1s1gyService.updateU2S1gy(us);
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
				if(!GH.equals("") || !CYW.equals("") || !TYW.equals("")){
//					u2tsjy1.setRpdU2TSJYId(jyid1);
					u2tsjy1.setGH(GH);
					u2tsjy1.setCYW(CommonsUtil.isNullOrEmpty(CYW));
					u2tsjy1.setTYW(CommonsUtil.isNullOrEmpty(TYW));
					u2tsjy1.setReportsj(DateBean.getStrDate(RQ));
					updateflg = u1s1gyService.updateU2S1gy(u2tsjy1);
				}
				if(!GH2.equals("") || !CYW2.equals("") || !TYW2.equals("")){
//					u2tsjy2.setRpdU2TSJYId(jyid2);
					u2tsjy2.setGH(GH2);
					u2tsjy2.setCYW(CommonsUtil.isNullOrEmpty(CYW2));
					u2tsjy2.setTYW(CommonsUtil.isNullOrEmpty(TYW2));
					u2tsjy2.setReportsj(DateBean.getStrDate(RQ));
					updateflg = u1s1gyService.updateU2S1gy(u2tsjy2);
				}
				if(!WGH.equals("") || !WCYW.equals("") || !WTYW.equals("")){
//					u2tsws1.setRpdU2TSWSId(wsid1);
					u2tsws1.setGH(WGH);
					u2tsws1.setCYW(CommonsUtil.isNullOrEmpty(WCYW));
					u2tsws1.setTYW(CommonsUtil.isNullOrEmpty(WTYW));
					u2tsws1.setReportsj(DateBean.getStrDate(RQ));
					updateflg = u1s1gyService.updateU2S1gy(u2tsws1);
				}
				if(!WGH2.equals("") || !WCYW2.equals("") || !WTYW2.equals("")){
//					u2tsws2.setRpdU2TSWSId(wsid2);
					u2tsws2.setGH(WGH2);
					u2tsws2.setCYW(CommonsUtil.isNullOrEmpty(WCYW2));
					u2tsws2.setTYW(CommonsUtil.isNullOrEmpty(WTYW2));
					u2tsws2.setReportsj(DateBean.getStrDate(RQ));
					updateflg = u1s1gyService.updateU2S1gy(u2tsws2);
				}
				if(!HCGDSHSBP.equals("") || !HCGDSHSSJ.equals("")){
//					u2tszh.setRpdU2TSZHId(zhid);
					u2tszh.setHCGDSHSBP(CommonsUtil.isNullOrEmpty(HCGDSHSBP));
					u2tszh.setHCGDSHSSJ(CommonsUtil.isNullOrEmpty(HCGDSHSSJ));
					u2tszh.setReportsj(DateBean.getStrDate(RQ));
					updateflg = u1s1gyService.updateU2S1gy(u2tszh);
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
					PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "3", "特二联脱水", reportM.getRpdReportMessageId());
					logService.addLog(log);
				} catch (Exception e) {
					e.printStackTrace();
					obj = CommonsUtil.getRrturnJson("","特二联脱水日志修改失败" ,"", null, null);
				}
			}
			out.print(obj);
			return null;
		
		}
		
		
		
	
		
		
		@SuppressWarnings("unused")
		public String exportU1S1() throws Exception {
			HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
			String txtDate = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("txtDate")));
			if ("".equals(txtDate)) {
				txtDate = DateBean.getSystemTime1();
			}
			String templetFilePath = request.getSession().getServletContext().getRealPath("/") + "exceltemplet\\combination\\二号联合站\\特二联脱水报表.xls";
			HSSFWorkbook wb = ExcelToHtmlUtils.loadXls( new File( templetFilePath ) );						
			HSSFSheet sheet = wb.getSheetAt(0);

			PropertiesConfig pc = new PropertiesConfig();
			String U1WaterAuto = "select LIT_10307, LIT_10308, LIT_10309, LIT_10310, LIT_10311 ,LIT_10312, LIT_10313,LIT_10314, LIT_10305, LIT_10306, TSB1PL ,TSB2PL,TSB3PL,TT_10311A,WSBPL from pc_rpd_U2_dehydration_t";
			String stratime =DateBean.getDynamicTime("-16", txtDate, "0");
			String endtime =DateBean.getDynamicTime("-16", txtDate, "1");
			U1WaterAuto += " where REPORT_TIME between TO_DATE('" + stratime + "','YYYY-MM-DD HH24:MI:SS') and TO_DATE('" + endtime + "','YYYY-MM-DD HH24:MI:SS') order by REPORT_TIME";
			String U1WatInsertP = pc.getSystemConfiguration("U1insert");
			
			List<Object[]> list = u1s2gyService.searchExportData("", U1WaterAuto);
			if (list != null && list.size() > 0) {
				//调用插入数据样式的方法
				//List<Object[]> objDataList = U2DataExportUtil.splitSumData(list);
				
				U2DataExportUtil.insertDataByPosition(list, wb, sheet, U1WatInsertP);
			}
			String baseInfoSql = "select r.zbr1,r.shr,r.rq,r.bz, r.zbr2 from pc_rpd_report_message_t r where r.bbmc='特二联脱水报表' and r.rq=TO_DATE('" + txtDate + "','YYYY-MM-DD') ";
			list = u1s2gyService.searchExportData("", baseInfoSql);
			if (list != null && list.size() > 0) {
				// 获取单位、值班人、审核人、日期、备注数据插入位置
				String[] basePostion = pc.getSystemConfiguration( "TSComInsert" ).split(",");
				for (int j = 0; j < basePostion.length; j++) {
					if (list.get(0)[j] != null) {
						U2DataExportUtil.insertExcelData(wb, sheet, basePostion[j], 0, new Object[]{list.get(0)[j]});
					}
				}
			}
			String jysql = "select r.GH,r.CYW,r.TYW from PC_RPD_U2_TSJY_T r where r.REPORT_DATE=TO_DATE('" + txtDate + "','YYYY-MM-DD')";
			list = u1s2gyService.searchExportData("", jysql);
			if (list != null && list.size() > 0) {
				// 获取单位、值班人、审核人、日期、备注数据插入位置
				String[] basePostions = pc.getSystemConfiguration("TSjyComInsert").split("&");
				String[][] basePostion = new String[basePostions.length][];
				for (int i = 0; i < basePostion.length; i++) {
					basePostion[i] = basePostions[i].split(",");
				}
				for (int j = 0; j < basePostion[0].length; j++) {
					if (list.get(0)[j] != null) {
						U2DataExportUtil.insertExcelData(wb, sheet, basePostion[0][j], 0, new Object[]{list.get(0)[j]});
					}
				}
				if(list.size()>1){
					for (int j = 0; j < basePostion[1].length; j++) {
						if (list.get(1)[j] != null) {
							U2DataExportUtil.insertExcelData(wb, sheet, basePostion[1][j], 0, new Object[]{list.get(1)[j]});
						}
					}
				}
				
			}
			String wssql = "select r.GH,r.CYW,r.TYW from PC_RPD_U2_TSWS_T r where r.REPORT_DATE=TO_DATE('" + txtDate + "','YYYY-MM-DD')";
			list = u1s2gyService.searchExportData("", wssql);
			if (list != null && list.size() > 0) {
				// 获取单位、值班人、审核人、日期、备注数据插入位置
				String[] basePostions = pc.getSystemConfiguration("TSwsComInsert").split("&");
				String[][] basePostion = new String[basePostions.length][];
				for (int i = 0; i < basePostion.length; i++) {
					basePostion[i] = basePostions[i].split(",");
				}
				for (int j = 0; j < basePostion[0].length; j++) {
					if (list.get(0)[j] != null) {
						U2DataExportUtil.insertExcelData(wb, sheet, basePostion[0][j], 0, new Object[]{list.get(0)[j]});
					}
				}
				if(list.size()>1){
					for (int j = 0; j < basePostion[1].length; j++) {
						if (list.get(1)[j] != null) {
							U2DataExportUtil.insertExcelData(wb, sheet, basePostion[1][j], 0, new Object[]{list.get(1)[j]});
						}
					}
				}
				
			}
			String zhSql = "select r.HCGDSHSSJ,r.HCGDSHSBP from PC_RPD_U2_TSZH_T r where r.REPORT_DATE=TO_DATE('" + txtDate + "','YYYY-MM-DD')";
			list = u1s2gyService.searchExportData("", baseInfoSql);
			if (list != null && list.size() > 0) {
				// 获取单位、值班人、审核人、日期、备注数据插入位置
				String[] basePostion = pc.getSystemConfiguration( "TSzhComInsert" ).split(",");
				for (int j = 0; j < basePostion.length; j++) {
					if (list.get(0)[j] != null) {
						U2DataExportUtil.insertExcelData(wb, sheet, basePostion[j], 0, new Object[]{list.get(0)[j]});
					}
				}
			}
//				wb.setForceFormulaRecalculation(true);
			OutputStreamAndCloseBaos(U2DataExportUtil.ExporDataStream(wb));
			return "excel";
		
		}
		
}
