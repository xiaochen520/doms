package com.echo.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
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
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.hssf.converter.ExcelToHtmlConverter;
import org.apache.poi.hssf.converter.ExcelToHtmlUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.util.IOUtils;
import org.apache.struts2.ServletActionContext;
import org.w3c.dom.Document;

import com.echo.dto.PcRdLoginfoT;
import com.echo.dto.PcRpdCrudeTransitionT;
import com.echo.dto.PcRpdReportMessageT;
import com.echo.dto.PcRpdU2GeneralT;
import com.echo.dto.User;
import com.echo.service.LogService;
import com.echo.service.ReportMessageService;
import com.echo.service.ZHRBRPDService;
import com.echo.util.AuthorityUtil;
import com.echo.util.CommonsUtil;
import com.echo.util.DateBean;
import com.echo.util.ExcelUtil;
import com.echo.util.PropertiesConfig;
import com.echo.util.StringUtil;
import com.echo.util.U2DataExportUtil;
import com.opensymphony.xwork2.ActionSupport;

public class ZHRBRPDAction extends ActionSupport {
	private static final long serialVersionUID = 12L;
	HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
	private InputStream excelFile = null;
	private ZHRBRPDService zhrbrpdService;
	private ReportMessageService reportMessageService;
	
	
	private LogService logService;

	public LogService getLogService() {
		return logService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	
	public ZHRBRPDService getZhrbrpdService() {
		return zhrbrpdService;
	}

	public void setZhrbrpdService(ZHRBRPDService zhrbrpdService) {
		this.zhrbrpdService = zhrbrpdService;
	}

	public ReportMessageService getReportMessageService() {
		return reportMessageService;
	}

	public void setReportMessageService(ReportMessageService reportMessageService) {
		this.reportMessageService = reportMessageService;
	}

	public String getFileName() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd ");
		String downloadFileName = (sf.format(new Date()).toString())+ "二号稠油联合处理站日生产简报-新（综合日报）.xls";
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
	public String searchZHRB() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		List<PcRpdReportMessageT> reportMessage = null;
		JSONObject jsonobj = new JSONObject();
		String txtDate = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("txtDate")));
		//获取当天所以二号稠油联合站原油系统日报数据
		JSONObject obj = new JSONObject();
		PcRpdReportMessageT reportM = null;
		if(txtDate == null || "".equals(txtDate)){
			txtDate = DateBean.getSystemTime1();
		}
		
		try {
			jsonobj = zhrbrpdService.searchZHRB(txtDate);
			
		} catch (Exception e) {
			e.printStackTrace();
			obj = CommonsUtil.getRrturnJson("","当前日期搜索失败请选择其他日期" ,"", null, null);
			out.print(obj);
			return null;
		}
		JSONArray yyjjdata = zhrbrpdService.searchYYJJ(txtDate);
		jsonobj.put("YYJJDATA", yyjjdata);
		try {
			reportMessage = reportMessageService.SreachReportMessages("", "二号稠油联合处理站日生产简报-新（综合日报）", txtDate);
			if(reportMessage != null && reportMessage.size()>0){
				reportM = reportMessage.get(0);
				
			}else{
				reportM = new PcRpdReportMessageT();
				reportM.setRq(DateBean.getStrDate(txtDate));
				reportM.setBbmc("二号稠油联合处理站日生产简报-新（综合日报）");
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
				reportMessage = reportMessageService.SreachReportMessages("", "二号稠油联合处理站日生产简报-新（综合日报）", txtDate);
				reportM = reportMessage.get(0);
				if(updateflg){
					//添加系统LOG
					try {
						User user1 = (User) session.getAttribute("userInfo");
						PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "1", "综合日报", reportM.getRpdReportMessageId());
						logService.addLog(log);
					} catch (Exception e) {
						e.printStackTrace();
						obj = CommonsUtil.getRrturnJson("","综合日报日志添加失败" ,"", null, null);
					}
				}
			}
			
			
			obj = new JSONObject();
			obj.put("RPDREPORTMESSAGEID", reportM.getRpdReportMessageId());
			obj.put("TBR", reportM.getTbr());
			obj.put("SHR", reportM.getShr());
			obj.put("RQ", DateBean.getChinaDate(txtDate));
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
//		request.setAttribute("U2OILs", U2OILs);
//		request.setAttribute("reportMessages", reportMessages);
		
	return null;
	
	
	
}
	
	//根据权限进行页面初始化
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
			butJson = AuthorityUtil.getButtonJson("MENU080", user);
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
	
	public String updateZHRB() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONObject obj = new JSONObject();
		
//		"RPDREPORTMESSAGEID":RPDREPORTMESSAGEID,"TBR":TBR,"BZ":BZ,"RQ":RQ,
		String TRPD_U2_GENERAL_ID = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("TRPD_U2_GENERAL_ID")));  //当日2号联合处理站综合日报id
		//产量分析
		String RPDREPORTMESSAGEID = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("RPDREPORTMESSAGEID")));
		String TBR = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("TBR")));
		String BZ = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("BZ")));
		String RQ = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("RQ")));
		String CYEL = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("CYEL")));
		String CYOUL = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("CYOUL")));
		String WSYL = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("WSYL")));
		String KCYL = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("KCYL")));
		
		//正相破乳剂
		String ZXPRJYDJYL = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("ZXPRJYDJYL")));
		String ZXPRJYDJYND = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("ZXPRJYDJYND")));
		String ZXPRJEDJYL = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("ZXPRJEDJYL")));
		String ZXPRJEDJYND = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("ZXPRJEDJYND")));
		String ZXPRJZHND = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("ZXPRJZHND")));
		//反相药剂
		String FXPRJYDJYL = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("FXPRJYDJYL")));
		String FXPRJYDJYND = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("FXPRJYDJYND")));
		String FXPRJEDJYL = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("FXPRJEDJYL")));
		String FXPRJEDJYND = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("FXPRJEDJYND")));
		String FXPRJZHND = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("FXPRJZHND")));
		
		//分线计量
		String CYEZLY = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("CYEZLY")));
		String CYSZLY = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("CYSZLY")));
		String CCYL = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("CCYL")));
		
		//重点节点参数
		String CCYNDQ = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("CCYNDQ")));
		String CJG1HS = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("CJG1HS")));
		String CJG2HS = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("CJG2HS")));
		String TSBHS = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("TSBHS")));
		String XBCRWD = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("XBCRWD")));
		
		//原油交接情况 第一行
		String RPD_CRUDE_TRANSITION_ID0 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("RPD_CRUDE_TRANSITION_ID0")));
		String GH0 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("GH0")));
		String GYW0 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("GYW0")));
		String DYW0 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("DYW0")));
		String SM0 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("SM0")));
		String BM0 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("BM0")));
		String GW0 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("GW0")));
		String HS0 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("HS0")));
		String MYL0 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("MYL0")));
		String CYL0 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("CYL0")));
		String SL0 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("SL0")));
		
		//原油交接情况 第二行
		String RPD_CRUDE_TRANSITION_ID1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("RPD_CRUDE_TRANSITION_ID1")));
		String GH1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("GH1")));
		String GYW1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("GYW1")));
		String DYW1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("DYW1")));
		String SM1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("SM1")));
		String BM1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("BM1")));
		String GW1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("GW1")));
		String HS1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("HS1")));
		String MYL1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("MYL1")));
		String CYL1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("CYL1")));
		String SL1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("SL1")));
		//原油交接情况 第三行
		String RPD_CRUDE_TRANSITION_ID2 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("RPD_CRUDE_TRANSITION_ID2")));
		String GH2 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("GH2")));
		String GYW2 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("GYW2")));
		String DYW2 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("DYW2")));
		String SM2 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("SM2")));
		String BM2 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("BM2")));
		String GW2 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("GW2")));
		String HS2 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("HS2")));
		String MYL2 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("MYL2")));
		String CYL2 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("CYL2")));
		String SL2 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("SL2")));
		
		//水量
		String LSL = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("LSL")));
		String FYQL = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("FYQL")));
		String GLQL = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("GLQL")));
		String WSHS = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("WSHS")));
		String WSL = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("WSL")));
		//水质指标
		String CJGCSHY = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("CJGCSHY")));
		String TCGJKHY = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("TCGJKHY")));
		String TCGJKXF = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("TCGJKXF")));
		String TCGCKHY = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("TCGCKHY")));
		String TCGCKXF = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("TCGCKXF")));
		String FYGCKHY = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("FYGCKHY")));
		String FYGCKXF = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("FYGCKXF")));
		String YJGLQCKHY = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("YJGLQCKHY")));
		String YJGLQCKXF = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("YJGLQCKXF")));
		String EJGLQCKHY = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("EJGLQCKHY")));
		String EJGLQCKXF = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("EJGLQCKXF")));
		//SAGD循环预热液处理
		String SXHYRYZLYYL = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("SXHYRYZLYYL")));
		String SXHYRYZLYWD = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("SXHYRYZLYWD")));
		String SXHYRYZYL = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("SXHYRYZYL")));
		String SXHYRYYJSL = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("SXHYRYYJSL")));
		String SXHYRYZLYLSWD = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("SXHYRYZLYLSWD")));
		String SXHYRYZLYHSWD = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("SXHYRYZLYHSWD")));
		String SXHYRYNJSWD = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("SXHYRYNJSWD")));
		String SXHYRYHRHYWD = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("SXHYRYHRHYWD")));
		
		List<PcRpdReportMessageT> reportMessage = null;
		PcRpdReportMessageT reportM = null;
		if(RPDREPORTMESSAGEID != null && !"".equals(RPDREPORTMESSAGEID)){
			reportMessage = reportMessageService.SreachReportMessages(RPDREPORTMESSAGEID, "", "");
		}else{
			obj = CommonsUtil.getRrturnJson("","未获取联合站非综合日报日志信息ID" ,"", null, null);
			out.print(obj);
			return null;
			
		}
		if(RQ != null && !"".equals(RQ)){
			RQ = DateBean.getChinaDateD(RQ);
		}else{
			obj = CommonsUtil.getRrturnJson("","未获取当前日期信息，请重新收索" ,"", null, null);
			out.print(obj);
			return null;
		}
		
		
		if(reportMessage != null && reportMessage.size()>0){
			reportM = reportMessage.get(0);
		}else{
			if(RQ != null && !"".equals(RQ)){
				
				reportMessage = reportMessageService.SreachReportMessages("", "二号稠油联合处理站日生产简报-新（综合日报）", RQ);
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
		
		boolean flag = true;
		 if(GH0 == "" || GH0 == null ){
			
			if(GH1 != "" && GH1 != null){
				if(GH1.equals(GH2)){
					flag = false;
				}
			}
				
		 }

		 if(GH1 == "" || GH1 == null ){
			if(GH2 != "" && GH2 != null ){
				if(GH0.equals(GH2)){
					flag = false;
				}
			}	
		 }

		 if(GH2 == "" || GH2 == null ){
			if(GH0 != "" && GH0 != null ){
				if(GH0.equals(GH1)){
					flag = false;
				}
			}	
		 }

		 if(GH0 != "" && GH0 != null && 
			GH1 != "" && GH1 != null &&
			GH2 != "" && GH2 != null){
			if(GH0.equals(GH1) || GH0.equals(GH2) || GH1.equals(GH2)){
				flag = false;
			}
		}
		
		 List<PcRpdCrudeTransitionT> crudeTransitions = null;
		 PcRpdCrudeTransitionT  crudeTransition0 = null;
		 PcRpdCrudeTransitionT  crudeTransition1 = null;
		 PcRpdCrudeTransitionT  crudeTransition2 = null;
		 
		 PropertiesConfig pc = new PropertiesConfig();//写在adction 里 在查询拼接sql及导出报表时都要用到 
		 String lhzid = pc.getSystemConfiguration("CRUDE_TRANSITIONorg_id");
		 boolean ghflag = false;
		 
		if(flag){
			//第一行记录
			if(RPD_CRUDE_TRANSITION_ID0 != null && !"".equals(RPD_CRUDE_TRANSITION_ID0)){
					try {
						crudeTransitions = zhrbrpdService.searchCrudeTransition("", GH0,RQ,lhzid);
					} catch (Exception e) {
						e.printStackTrace();
						obj = CommonsUtil.getRrturnJson("","当前报表原油交接记录获取失败" ,"", null, null);
						out.print(obj);
						return null;
					}
					
					if(crudeTransitions != null && crudeTransitions.size()>0){
						
						if(crudeTransitions.size() > 1){
							obj = CommonsUtil.getRrturnJson("","当前报表原油交接记录中存在重复罐号" ,"", null, null);
							out.print(obj);
							return null;
						}else if(crudeTransitions.size() == 1){
							if(RPD_CRUDE_TRANSITION_ID0.equals(crudeTransitions.get(0).getRpdCrudeTransitionId())){
								crudeTransition0 = crudeTransitions.get(0);
							}else{
								obj = CommonsUtil.getRrturnJson("","当前报表原油交接记录中存在重复罐号" ,"", null, null);
								out.print(obj);
								return null;
							}
						}else{
							crudeTransition0 = new PcRpdCrudeTransitionT();
						}
					}else{
						crudeTransition0 = new PcRpdCrudeTransitionT();
						
					}
				}else{
					crudeTransition0 = new PcRpdCrudeTransitionT();
				}
				
			crudeTransition0.setRpdCrudeTransitionId(RPD_CRUDE_TRANSITION_ID0);
			crudeTransition0.setRq(DateBean.getStrDate(RQ));
			crudeTransition0.setOrgId(lhzid);
			crudeTransition0.setGh(GH0);
			if(GYW0 != null && !"".equals(GYW0)){
				crudeTransition0.setGyw(java.math.BigDecimal.valueOf(Double.parseDouble(GYW0)));
			}else{
				crudeTransition0.setGyw(null);
			}
			
			if(DYW0 != null && !"".equals(DYW0)){
				crudeTransition0.setDyw(java.math.BigDecimal.valueOf(Double.parseDouble(DYW0)));
			}else{
				crudeTransition0.setDyw(null);
			}
			
			if(SM0 != null && !"".equals(SM0)){
				crudeTransition0.setSm(java.math.BigDecimal.valueOf(Double.parseDouble(SM0)));
			}else{
				crudeTransition0.setSm(null);
			}
			
			if(BM0 != null && !"".equals(BM0)){
				crudeTransition0.setBm(java.math.BigDecimal.valueOf(Double.parseDouble(BM0)));
			}else{
				crudeTransition0.setBm(null);
			}
			
			if(GW0 != null && !"".equals(GW0)){
				crudeTransition0.setGw(java.math.BigDecimal.valueOf(Double.parseDouble(GW0)));
			}else{
				crudeTransition0.setGw(null);
			}
			
			if(HS0 != null && !"".equals(HS0)){
				crudeTransition0.setHs(java.math.BigDecimal.valueOf(Double.parseDouble(HS0)));
			}else{
				crudeTransition0.setHs(null);
			}
			
			if(MYL0 != null && !"".equals(MYL0)){
				crudeTransition0.setMyl(java.math.BigDecimal.valueOf(Double.parseDouble(MYL0)));
			}else{
				crudeTransition0.setMyl(null);
			}
			
			if(CYL0 != null && !"".equals(CYL0)){
				crudeTransition0.setCyl(java.math.BigDecimal.valueOf(Double.parseDouble(CYL0)));
			}else{
				crudeTransition0.setCyl(null);
			}
			
			if(SL0 != null && !"".equals(SL0)){
				crudeTransition0.setSl(java.math.BigDecimal.valueOf(Double.parseDouble(SL0)));
			}else{
				crudeTransition0.setSl(null);
			}
			
			
			//第二行记录
			if(RPD_CRUDE_TRANSITION_ID1 != null && !"".equals(RPD_CRUDE_TRANSITION_ID1)){
					try {
						crudeTransitions = zhrbrpdService.searchCrudeTransition("", GH1,RQ,lhzid);
					} catch (Exception e) {
						e.printStackTrace();
						obj = CommonsUtil.getRrturnJson("","当前报表原油交接记录获取失败" ,"", null, null);
						out.print(obj);
						return null;
					}
					
					if(crudeTransitions != null && crudeTransitions.size()>0){
						
						if(crudeTransitions.size() > 1){
							obj = CommonsUtil.getRrturnJson("","当前报表原油交接记录中存在重复罐号" ,"", null, null);
							out.print(obj);
							return null;
						}else if(crudeTransitions.size() == 1){
							if(RPD_CRUDE_TRANSITION_ID1.equals(crudeTransitions.get(0).getRpdCrudeTransitionId())){
								crudeTransition1 = crudeTransitions.get(0);
							}else{
								obj = CommonsUtil.getRrturnJson("","当前报表原油交接记录中存在重复罐号" ,"", null, null);
								out.print(obj);
								return null;
							}
						}else{
							crudeTransition1 = new PcRpdCrudeTransitionT();
						}
					}else{
						crudeTransition1 = new PcRpdCrudeTransitionT();
						
					}
				}else{
					crudeTransition1 = new PcRpdCrudeTransitionT();
				}
				
			crudeTransition1.setRpdCrudeTransitionId(RPD_CRUDE_TRANSITION_ID1);
			crudeTransition1.setRq(DateBean.getStrDate(RQ));
			crudeTransition1.setOrgId(lhzid);
			crudeTransition1.setGh(GH1);
			if(GYW1 != null && !"".equals(GYW1)){
				crudeTransition1.setGyw(java.math.BigDecimal.valueOf(Double.parseDouble(GYW1)));
			}else{
				crudeTransition1.setGyw(null);
			}
			
			if(DYW1 != null && !"".equals(DYW1)){
				crudeTransition1.setDyw(java.math.BigDecimal.valueOf(Double.parseDouble(DYW1)));
			}else{
				crudeTransition1.setDyw(null);
			}
			
			if(SM1 != null && !"".equals(SM1)){
				crudeTransition1.setSm(java.math.BigDecimal.valueOf(Double.parseDouble(SM1)));
			}else{
				crudeTransition1.setSm(null);
			}
			
			if(BM1 != null && !"".equals(BM1)){
				crudeTransition1.setBm(java.math.BigDecimal.valueOf(Double.parseDouble(BM1)));
			}else{
				crudeTransition1.setBm(null);
			}
			
			if(GW1 != null && !"".equals(GW1)){
				crudeTransition1.setGw(java.math.BigDecimal.valueOf(Double.parseDouble(GW1)));
			}else{
				crudeTransition1.setGw(null);
			}
			
			if(HS1 != null && !"".equals(HS1)){
				crudeTransition1.setHs(java.math.BigDecimal.valueOf(Double.parseDouble(HS1)));
			}else{
				crudeTransition1.setHs(null);
			}
			
			if(MYL1 != null && !"".equals(MYL1)){
				crudeTransition1.setMyl(java.math.BigDecimal.valueOf(Double.parseDouble(MYL1)));
			}else{
				crudeTransition1.setMyl(null);
			}
			
			if(CYL1 != null && !"".equals(CYL1)){
				crudeTransition1.setCyl(java.math.BigDecimal.valueOf(Double.parseDouble(CYL1)));
			}else{
				crudeTransition1.setCyl(null);
			}
			
			if(SL1 != null && !"".equals(SL1)){
				crudeTransition1.setSl(java.math.BigDecimal.valueOf(Double.parseDouble(SL1)));
			}else{
				crudeTransition1.setSl(null);
			}
			
			
			
			//第三行记录
			if(RPD_CRUDE_TRANSITION_ID2 != null && !"".equals(RPD_CRUDE_TRANSITION_ID2)){
					try {
						crudeTransitions = zhrbrpdService.searchCrudeTransition("", GH2,RQ,lhzid);
					} catch (Exception e) {
						e.printStackTrace();
						obj = CommonsUtil.getRrturnJson("","当前报表原油交接记录获取失败" ,"", null, null);
						out.print(obj);
						return null;
					}
					
					if(crudeTransitions != null && crudeTransitions.size()>0){
						
						if(crudeTransitions.size() > 1){
							obj = CommonsUtil.getRrturnJson("","当前报表原油交接记录中存在重复罐号" ,"", null, null);
							out.print(obj);
							return null;
						}else if(crudeTransitions.size() == 1){
							if(RPD_CRUDE_TRANSITION_ID2.equals(crudeTransitions.get(0).getRpdCrudeTransitionId())){
								crudeTransition2 = crudeTransitions.get(0);
							}else{
								obj = CommonsUtil.getRrturnJson("","当前报表原油交接记录中存在重复罐号" ,"", null, null);
								out.print(obj);
								return null;
							}
						}else{
							crudeTransition2 = new PcRpdCrudeTransitionT();
						}
					}else{
						crudeTransition2 = new PcRpdCrudeTransitionT();
						
					}
				}else{
					crudeTransition2 = new PcRpdCrudeTransitionT();
				}
				
			crudeTransition2.setRpdCrudeTransitionId(RPD_CRUDE_TRANSITION_ID2);
			crudeTransition2.setRq(DateBean.getStrDate(RQ));
			crudeTransition2.setOrgId(lhzid);
			crudeTransition2.setGh(GH2);
			if(GYW2 != null && !"".equals(GYW2)){
				crudeTransition2.setGyw(java.math.BigDecimal.valueOf(Double.parseDouble(GYW2)));
			}else{
				crudeTransition2.setGyw(null);
			}
			
			if(DYW2 != null && !"".equals(DYW2)){
				crudeTransition2.setDyw(java.math.BigDecimal.valueOf(Double.parseDouble(DYW2)));
			}else{
				crudeTransition2.setDyw(null);
			}
			
			if(SM2 != null && !"".equals(SM2)){
				crudeTransition2.setSm(java.math.BigDecimal.valueOf(Double.parseDouble(SM2)));
			}else{
				crudeTransition2.setSm(null);
			}
			
			if(BM2 != null && !"".equals(BM2)){
				crudeTransition2.setBm(java.math.BigDecimal.valueOf(Double.parseDouble(BM2)));
			}else{
				crudeTransition2.setBm(null);
			}
			
			if(GW2 != null && !"".equals(GW2)){
				crudeTransition2.setGw(java.math.BigDecimal.valueOf(Double.parseDouble(GW2)));
			}else{
				crudeTransition2.setGw(null);
			}
			
			if(HS2 != null && !"".equals(HS2)){
				crudeTransition2.setHs(java.math.BigDecimal.valueOf(Double.parseDouble(HS2)));
			}else{
				crudeTransition2.setHs(null);
			}
			
			if(MYL2 != null && !"".equals(MYL2)){
				crudeTransition2.setMyl(java.math.BigDecimal.valueOf(Double.parseDouble(MYL2)));
			}else{
				crudeTransition2.setMyl(null);
			}
			
			if(CYL2 != null && !"".equals(CYL2)){
				crudeTransition2.setCyl(java.math.BigDecimal.valueOf(Double.parseDouble(CYL2)));
			}else{
				crudeTransition2.setCyl(null);
			}
			
			if(SL2 != null && !"".equals(SL2)){
				crudeTransition2.setSl(java.math.BigDecimal.valueOf(Double.parseDouble(SL2)));
			}else{
				crudeTransition2.setSl(null);
			}
		
		}else{
			obj = CommonsUtil.getRrturnJson("","当前报表原油交接记录中存在重复罐号" ,"", null, null);
			out.print(obj);
			return null;
		}
		
		List<PcRpdU2GeneralT> U2Generals = null;
		PcRpdU2GeneralT u2General = null;
//		TRPD_U2_GENERAL_ID
		if(TRPD_U2_GENERAL_ID != null && !"".equals(TRPD_U2_GENERAL_ID)){
			U2Generals = zhrbrpdService.searchU2General(TRPD_U2_GENERAL_ID, "");
			if(U2Generals != null && U2Generals.size()>0){
				u2General = U2Generals.get(0);
			}else{
				obj = CommonsUtil.getRrturnJson("","2号联合处理站综合日报当天报表数据ID获取失败" ,"", null, null);
				out.print(obj);
				return null;
			}
			
		}else{
			U2Generals = zhrbrpdService.searchU2General("", RQ);
			if(U2Generals != null && U2Generals.size()>0){
				obj = CommonsUtil.getRrturnJson("","2号联合处理站综合日报当天报表数据已存在请刷新页面获取报表ID" ,"", null, null);
				out.print(obj);
				return null;
			}else{
				u2General = new PcRpdU2GeneralT();
			}
		}
		
		u2General.setRpdU2GeneralId(TRPD_U2_GENERAL_ID);
		u2General.setRq(DateBean.getStrDate(RQ));
		if(CYEL != null && !"".equals(CYEL)){
			u2General.setCyel(java.math.BigDecimal.valueOf(Double.parseDouble(CYEL)));
		}else{
			u2General.setCyel(null);
		}
		if(CYOUL != null && !"".equals(CYOUL)){
			u2General.setCyoul(java.math.BigDecimal.valueOf(Double.parseDouble(CYOUL)));
		}else{
			u2General.setCyoul(null);
		}
		if(WSYL != null && !"".equals(WSYL)){
			u2General.setWsyl(java.math.BigDecimal.valueOf(Double.parseDouble(WSYL)));
		}else{
			u2General.setWsyl(null);
		}
		if(KCYL != null && !"".equals(KCYL)){
			u2General.setKcy(java.math.BigDecimal.valueOf(Double.parseDouble(KCYL)));
		}else{
			u2General.setKcy(null);
		}
		
		if(ZXPRJYDJYL != null && !"".equals(ZXPRJYDJYL)){
			u2General.setZxprjydjyl(java.math.BigDecimal.valueOf(Double.parseDouble(ZXPRJYDJYL)));
		}else{
			u2General.setZxprjydjyl(null);
		}
		
		if(ZXPRJYDJYND != null && !"".equals(ZXPRJYDJYND)){
			u2General.setZxprjydjynd(java.math.BigDecimal.valueOf(Double.parseDouble(ZXPRJYDJYND)));
		}else{
			u2General.setZxprjydjynd(null);
		}
		
		if(ZXPRJEDJYL != null && !"".equals(ZXPRJEDJYL)){
			u2General.setZxprjedjyl(java.math.BigDecimal.valueOf(Double.parseDouble(ZXPRJEDJYL)));
		}else{
			u2General.setZxprjedjyl(null);
		}
		
		if(ZXPRJEDJYND != null && !"".equals(ZXPRJEDJYND)){
			u2General.setZxprjedjynd(java.math.BigDecimal.valueOf(Double.parseDouble(ZXPRJEDJYND)));
		}else{
			u2General.setZxprjedjynd(null);
		}
		
		if(ZXPRJZHND != null && !"".equals(ZXPRJZHND)){
			u2General.setZxprjzhnd(java.math.BigDecimal.valueOf(Double.parseDouble(ZXPRJZHND)));
		}else{
			u2General.setZxprjzhnd(null);
		}
		
		if(FXPRJYDJYL != null && !"".equals(FXPRJYDJYL)){
			u2General.setFxprjydjyl(java.math.BigDecimal.valueOf(Double.parseDouble(FXPRJYDJYL)));
		}else{
			u2General.setFxprjydjyl(null);
		}
		
		if(FXPRJYDJYND != null && !"".equals(FXPRJYDJYND)){
			u2General.setFxprjydjynd(java.math.BigDecimal.valueOf(Double.parseDouble(FXPRJYDJYND)));
		}else{
			u2General.setFxprjydjynd(null);
		}
		
		if(FXPRJEDJYL != null && !"".equals(FXPRJEDJYL)){
			u2General.setFxprjedjyl(java.math.BigDecimal.valueOf(Double.parseDouble(FXPRJEDJYL)));
		}else{
			u2General.setFxprjedjyl(null);
		}
		
		
		if(FXPRJEDJYND != null && !"".equals(FXPRJEDJYND)){
			u2General.setFxprjedjynd(java.math.BigDecimal.valueOf(Double.parseDouble(FXPRJEDJYND)));
		}else{
			u2General.setFxprjedjynd(null);
		}
		
		if(FXPRJZHND != null && !"".equals(FXPRJZHND)){
			u2General.setFxprjzhnd(java.math.BigDecimal.valueOf(Double.parseDouble(FXPRJZHND)));
		}else{
			u2General.setFxprjzhnd(null);
		}
		
		if(CYEZLY != null && !"".equals(CYEZLY)){
			u2General.setCyezly(java.math.BigDecimal.valueOf(Double.parseDouble(CYEZLY)));
		}else{
			u2General.setCyezly(null);
		}
		if(CYSZLY != null && !"".equals(CYSZLY)){
			u2General.setCyszly(java.math.BigDecimal.valueOf(Double.parseDouble(CYSZLY)));
		}else{
			u2General.setCyszly(null);
		}
		if(CCYL != null && !"".equals(CCYL)){
			u2General.setCcyl(java.math.BigDecimal.valueOf(Double.parseDouble(CCYL)));
		}else{
			u2General.setCcyl(null);
		}
		if(CCYNDQ != null && !"".equals(CCYNDQ)){
			u2General.setCcynd(java.math.BigDecimal.valueOf(Double.parseDouble(CCYNDQ)));
		}else{
			u2General.setCcynd(null);
		}
		if(CJG1HS != null && !"".equals(CJG1HS)){
			u2General.setCjg1hs(java.math.BigDecimal.valueOf(Double.parseDouble(CJG1HS)));
		}else{
			u2General.setCjg1hs(null);
		}
		
		if(CJG2HS != null && !"".equals(CJG2HS)){
			u2General.setCjg2hs(java.math.BigDecimal.valueOf(Double.parseDouble(CJG2HS)));
		}else{
			u2General.setCjg2hs(null);
		}
		if(TSBHS != null && !"".equals(TSBHS)){
			u2General.setTsbhs(java.math.BigDecimal.valueOf(Double.parseDouble(TSBHS)));
		}else{
			u2General.setTsbhs(null);
		}
		if(XBCRWD != null && !"".equals(XBCRWD)){
			u2General.setXbcrwd(java.math.BigDecimal.valueOf(Double.parseDouble(XBCRWD)));
		}else{
			u2General.setXbcrwd(null);
		}
		if(LSL != null && !"".equals(LSL)){
			u2General.setLsl(java.math.BigDecimal.valueOf(Double.parseDouble(LSL)));
		}else{
			u2General.setLsl(null);
		}
		if(FYQL != null && !"".equals(FYQL)){
			u2General.setFyql(java.math.BigDecimal.valueOf(Double.parseDouble(FYQL)));
		}else{
			u2General.setFxprjzhnd(null);
		}
		if(GLQL != null && !"".equals(GLQL)){
			u2General.setGlql(java.math.BigDecimal.valueOf(Double.parseDouble(GLQL)));
		}else{
			u2General.setGlql(null);
		}
		if(WSHS != null && !"".equals(WSHS)){
			u2General.setWshs(java.math.BigDecimal.valueOf(Double.parseDouble(WSHS)));
		}else{
			u2General.setWshs(null);
		}
		if(WSL != null && !"".equals(WSL)){
			u2General.setWsl(java.math.BigDecimal.valueOf(Double.parseDouble(WSL)));
		}else{
			u2General.setWsl(null);
		}
		
//		CJGCSHY	NUMBER(8,2)	Y			沉降罐出水含油    27
		if(CJGCSHY != null && !"".equals(CJGCSHY)){
			u2General.setCjgcshy(java.math.BigDecimal.valueOf(Double.parseDouble(CJGCSHY)));
		}else{
			u2General.setCjgcshy(null);
		}
//		TCGJKHY	NUMBER(8,2)	Y			调储罐进口含油    28
		if(TCGJKHY != null && !"".equals(TCGJKHY)){
			u2General.setTcgjkhy(java.math.BigDecimal.valueOf(Double.parseDouble(TCGJKHY)));
		}else{
			u2General.setTcgjkhy(null);
		}
//		TCGJKXF	NUMBER(8,2)	Y			调储罐进口悬浮   29
		if(TCGJKXF != null && !"".equals(TCGJKXF)){
			u2General.setTcgjkxf(java.math.BigDecimal.valueOf(Double.parseDouble(TCGJKXF)));
		}else{
			u2General.setTcgjkxf(null);
		}
//		TCGCKHY	NUMBER(8,2)	Y			调储罐出口含油   30
		if(TCGCKHY != null && !"".equals(TCGCKHY)){
			u2General.setTcgckhy(java.math.BigDecimal.valueOf(Double.parseDouble(TCGCKHY)));
		}else{
			u2General.setTcgckhy(null);
		}
//		TCGCKXF	NUMBER(8,2)	Y			调储罐出口悬浮  31
		if(TCGCKXF != null && !"".equals(TCGCKXF)){
			u2General.setTcgckxf(java.math.BigDecimal.valueOf(Double.parseDouble(TCGCKXF)));
		}else{
			u2General.setTcgckxf(null);
		}
//		FYGCKHY	NUMBER(8,2)	Y			反应罐出口含油   32
		if(FYGCKHY != null && !"".equals(FYGCKHY)){
			u2General.setFygckhy(java.math.BigDecimal.valueOf(Double.parseDouble(FYGCKHY)));
		}else{
			u2General.setFygckhy(null);
		}
//		FYGCKXF	NUMBER(8,2)	Y			反应罐出口悬浮  33
		if(FYGCKXF != null && !"".equals(FYGCKXF)){
			u2General.setFygckxf(java.math.BigDecimal.valueOf(Double.parseDouble(FYGCKXF)));
		}else{
			u2General.setFygckxf(null);
		}
//		YJGLQCKHY	NUMBER(8,2)	Y			一级过滤器出口含油  34
		if(YJGLQCKHY != null && !"".equals(YJGLQCKHY)){
			u2General.setYjglqckhy(java.math.BigDecimal.valueOf(Double.parseDouble(YJGLQCKHY)));
		}else{
			u2General.setYjglqckhy(null);
		}
//		YJGLQCKXF	NUMBER(8,2)	Y			一级过滤器出口悬浮  35
		if(YJGLQCKXF != null && !"".equals(YJGLQCKXF)){
			u2General.setYjglqckxf(java.math.BigDecimal.valueOf(Double.parseDouble(YJGLQCKXF)));
		}else{
			u2General.setYjglqckxf(null);
		}
//		EJGLQCKHY	NUMBER(8,2)	Y			二级过滤器出口含油   36
		if(EJGLQCKHY != null && !"".equals(EJGLQCKHY)){
			u2General.setEjglqckhy(java.math.BigDecimal.valueOf(Double.parseDouble(EJGLQCKHY)));
		}else{
			u2General.setEjglqckhy(null);
		}
//		EJGLQCKXF	NUMBER(8,2)	Y			二级过滤器出口悬浮   37
		if(EJGLQCKXF != null && !"".equals(EJGLQCKXF)){
			u2General.setEjglqckxf(java.math.BigDecimal.valueOf(Double.parseDouble(EJGLQCKXF)));
		}else{
			u2General.setEjglqckxf(null);
		}
		
		if(SXHYRYZLYYL != null && !"".equals(SXHYRYZLYYL)){
			u2General.setSxhyryzlyyl(java.math.BigDecimal.valueOf(Double.parseDouble(SXHYRYZLYYL)));
		}else{
			u2General.setSxhyryzlyyl(null);
		}
		
		if(SXHYRYZLYWD != null && !"".equals(SXHYRYZLYWD)){
			u2General.setSxhyryzlywd(java.math.BigDecimal.valueOf(Double.parseDouble(SXHYRYZLYWD)));
		}else{
			u2General.setSxhyryzlywd(null);
		}
		
		if(SXHYRYZYL != null && !"".equals(SXHYRYZYL)){
			u2General.setSxhyryzyl(java.math.BigDecimal.valueOf(Double.parseDouble(SXHYRYZYL)));
		}else{
			u2General.setSxhyryzyl(null);
		}
		
		if(SXHYRYYJSL != null && !"".equals(SXHYRYYJSL)){
			u2General.setSxhyryyjsl(java.math.BigDecimal.valueOf(Double.parseDouble(SXHYRYYJSL)));
		}else{
			u2General.setSxhyryyjsl(null);
		}
		
		if(SXHYRYZLYLSWD != null && !"".equals(SXHYRYZLYLSWD)){
			u2General.setSxhyryzlylswd(java.math.BigDecimal.valueOf(Double.parseDouble(SXHYRYZLYLSWD)));
		}else{
			u2General.setSxhyryzlylswd(null);
		}
		
		if(SXHYRYZLYHSWD != null && !"".equals(SXHYRYZLYHSWD)){
			u2General.setSxhyryzlyhswd(java.math.BigDecimal.valueOf(Double.parseDouble(SXHYRYZLYHSWD)));
		}else{
			u2General.setSxhyryzlyhswd(null);
		}
		
		if(SXHYRYNJSWD != null && !"".equals(SXHYRYNJSWD)){
			u2General.setSxhyrynjswd(java.math.BigDecimal.valueOf(Double.parseDouble(SXHYRYNJSWD)));
		}else{
			u2General.setSxhyrynjswd(null);
		}
		
		if(SXHYRYHRHYWD != null && !"".equals(SXHYRYHRHYWD)){
			u2General.setSxhyryhrhywd(java.math.BigDecimal.valueOf(Double.parseDouble(SXHYRYHRHYWD)));
		}else{
			u2General.setSxhyryhrhywd(null);
		}
		
		reportM.setRpdReportMessageId(RPDREPORTMESSAGEID);
		reportM.setTbr(TBR);
		reportM.setRq(DateBean.getStrDate(RQ));
		reportM.setBbmc("二号稠油联合处理站日生产简报-新（综合日报）");
		reportM.setBz(BZ);
		
		
		
		
		
		boolean updateflg = false;
		try {
			updateflg = zhrbrpdService.updateZHRB(reportM,u2General,crudeTransition0,crudeTransition1,crudeTransition2);
		} catch (Exception e) {
			String  errmsg = e.getCause().toString();
			if(!"".equals(errmsg) && errmsg.indexOf("ORA-01438") != -1){
				obj = CommonsUtil.getRrturnJson("","您输入信息超过数据库数据精度范围" ,"", null, null);
			} else{
				obj = CommonsUtil.getRrturnJson("",errmsg ,"综合日报数据更新失败", null, null);
			}
			e.printStackTrace();
			out.print(obj);
			return null;
		}
		
		if(updateflg){
			//添加系统LOG
			try {
				User user1 = (User) session.getAttribute("userInfo");
				PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "3", "综合日报", reportM.getRpdReportMessageId());
				logService.addLog(log);
			} catch (Exception e) {
				e.printStackTrace();
				obj = CommonsUtil.getRrturnJson("","综合日报日志修改失败" ,"", null, null);
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
			
			obj = CommonsUtil.getRrturnJson("","未获取到二号稠油联合站综合日报数据ID" ,"", null, null);
			out.print(obj);
			return null;
		}
		
		if(reportMessage != null && reportMessage.size()>0){
			reportM = reportMessage.get(0);
		}else{
			obj = CommonsUtil.getRrturnJson("","未获取到二号稠油联合站综合日报数据ID对应数据" ,"", null, null);
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
	
	
	
	
	@SuppressWarnings("unused")
	public String exportU2_GENERAL() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		String txtDate = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("reportDate")));
		if ("".equals(txtDate)) {
			txtDate = DateBean.getSystemTime1();
//			return null;
		}
		PropertiesConfig pc = new PropertiesConfig();
		String fields = pc.getSystemConfiguration("U2_GENERALFields");
		List<PcRpdReportMessageT> reportMessage = reportMessageService.SreachReportMessages("", "二号稠油联合处理站日生产简报-新（综合日报）", txtDate);//值班人、审核人、日期、备注
//		获取值班人、审核人、日期、备注数据插入位置
		String U2_GENERALWatch = pc.getSystemConfiguration("U2_GENERALWatch");
		String U2_GENERALAuditor = pc.getSystemConfiguration("U2_GENERALAuditor");
		String U2_GENERALReportTime = pc.getSystemConfiguration("U2_GENERALReportTime");
		String U2_GENERALremark = pc.getSystemConfiguration("U2_GENERALremark");
		List<Object[]> U2_GENERALDataList = zhrbrpdService.searchU2_GENERAL(txtDate, fields, "");
		//分段时间
		String[] timeStr = pc.getSystemConfiguration("time").split("&");
		String[][] dates = new String[3][];
		for (int i = 0; i < dates.length; i++) {
			dates[i] = timeStr[i].split(",");
		}
		U2DataExportUtil.setDates(txtDate, dates);
		String[] startPositions = pc.getSystemConfiguration("startPositions").split(",");
		String[] endPositions = pc.getSystemConfiguration("endPositions").split(",");
		String[][] positions = new String[startPositions.length][2];
		for (int i = 0; i < startPositions.length; i++) {
			positions[i][0] = startPositions[i];
			positions[i][1] = endPositions[i];
		}
		//按位置 分割  splitDataByPosition    U2_GENERALDataList 数据        positions 位置                                     
		Object[] objDataList = U2DataExportUtil.splitDataByPosition(U2_GENERALDataList, positions);
		
		String templetFilePath = request.getSession().getServletContext().getRealPath("/") + "exceltemplet\\combination\\二号稠油联合处理站日生产简报.xls";
		//插入数据位置
		String[] cellPositions = pc.getSystemConfiguration("U2_GENERALDataInsertPostion").split(",");
		HSSFWorkbook wb = ExcelToHtmlUtils.loadXls( new File( templetFilePath ) );
		//wb.setForceFormulaRecalculation(true);
		HSSFSheet sheet = wb.getSheetAt(0);
		if ( U2_GENERALDataList == null || U2_GENERALDataList.size() == 0 ) {//查询数据为空
			java.io.ByteArrayOutputStream baos = U2DataExportUtil.ExporDataStream(wb);
			if(baos != null){
				byte[] ba = baos.toByteArray();
				excelFile = new ByteArrayInputStream(ba);
				baos.flush();
				baos.close();
			}
			return "excel";
		}
		String qDate =(String) U2_GENERALDataList.get(0)[0];
		if (U2_GENERALDataList.size() == 1) {
			if (qDate.subSequence(8, 10).equals(txtDate.subSequence(8, 10))) {//查询数据结果为一条时  插入数据位置为当日的位置
				cellPositions = pc.getSystemConfiguration("U2_GENERALDataInsertPostion_today").split(",");
				U2DataExportUtil.insertDataByPosition(objDataList, wb, sheet, cellPositions);
			}
			
		}
		else {
			U2DataExportUtil.insertDataByPosition(objDataList, wb, sheet, cellPositions);
		}
		//插入原油交接情况
		String crudeTransitionFields = pc.getSystemConfiguration("CRUDE_TRANSITION");
		String Org_id = pc.getSystemConfiguration("CRUDE_TRANSITIONorg_id");
		String[] crudeTransitionDataInsertP = pc.getSystemConfiguration("CRUDE_TRANSITIONDataInsertP").split(",");
		String[] sPositions = pc.getSystemConfiguration("sPositions").split(",");
		String[] ePositions = pc.getSystemConfiguration("ePositions").split(",");
		positions = new String[sPositions.length][2];
		for (int i = 0; i < sPositions.length; i++) {
			positions[i][0] = sPositions[i];
			positions[i][1] = ePositions[i];
		}
		List<Object[]>  crudeTransitionList = zhrbrpdService.searchU2_GENERAL(txtDate, crudeTransitionFields, "CRUDE_TRANSITION");
		if (crudeTransitionList != null && crudeTransitionList.size() > 0) {
			objDataList = U2DataExportUtil.splitDataByPosition(crudeTransitionList, positions);
			U2DataExportUtil.insertDataByPosition(objDataList, wb, sheet, crudeTransitionDataInsertP);
		}
		//插入水质指标数据  为当日数据
			int splitStart = Integer.parseInt(pc.getSystemConfiguration("GENERAL_WATER").split(",")[0].toString().split("-")[0]);//截取数据开始位置
			int splitDnd = Integer.parseInt(pc.getSystemConfiguration("GENERAL_WATER").split(",")[0].toString().split("-")[1]);//截取数据结束位置
			String position = pc.getSystemConfiguration("GENERAL_WATER").split(",")[1];//数据插入位置
			objDataList = new Object[1];
			if (U2_GENERALDataList.size() == 2) {
				objDataList = U2_GENERALDataList.get(1);
			}
			else {
				if (qDate.subSequence(8, 10).equals(txtDate.subSequence(8, 10))) {
					objDataList = U2_GENERALDataList.get(0);
				}
			}

			if (objDataList != null && objDataList.length != 1) {
				Object[] objData = new Object[splitDnd - splitStart + 1];//结束位置-开始位置 =截取的字段个数
				boolean flag = false;
				for (int i = 0; i < objData.length; i++) {
					objData[i] = objDataList[splitStart];
					if (splitStart > 0 && objDataList[splitStart] != null) {
						flag = true;
					}
					splitStart++;
				}
				if (objData != null && objData.length > 0 && flag) {
					String[] flagKey = {"cjgcshy","tcgjkhy","tcgjkxf","tcgckhy","tcgckxf","fygckhy","fygckxf","yjglqckhy","yjglqckxf","ejglqckhy","ejglqckxf"};
					int[] flagValue = new int[flagKey.length];
					for (int i = 0; i < flagKey.length; i++) {
						flagValue[i] = Integer.parseInt(pc.getSystemConfiguration(flagKey[i]));
					}
					
					CellReference cr = new CellReference(position);
					CellStyle dataStyle = ExcelUtil.getStyle(wb);
					dataStyle = ExcelUtil.getDataStyle(dataStyle);
					int startCol = cr.getCol();
					for (int j = 0; j < objData.length; j++) {//
						//水质指标部分值大于指标标准限时显示红色
						if (objData[j] == null) {
							continue;
						}
						if (Double.parseDouble(objData[j].toString()) > flagValue[j]) {
							CellStyle style = ExcelUtil.getStyle(wb);
							style = ExcelUtil.getDataStyle(style);
							Font font = wb.createFont();
							font.setColor(Font.COLOR_RED);
							style.setFont(font);
//							sheet.getRow(cr.getRow()).getCell(j + startCol).setCellStyle(style);
							U2DataExportUtil.insertExcelCell(sheet, j + startCol, cr.getRow(), objData[j], style);
							continue;
						}
						U2DataExportUtil.insertExcelCell(sheet, j + startCol, cr.getRow(), objData[j], dataStyle);
					}
				}
			}
		//插入 值班人、审核人、日期、备注
		if(reportMessage.size()>0){
			if (!"".equals(StringUtil.isNullUtil(reportMessage.get(0).getTbr()))) {
				U2DataExportUtil.insertExcelData(wb, sheet, U2_GENERALWatch, 0, new Object[]{reportMessage.get(0).getTbr()});
			}
			if (!"".equals(StringUtil.isNullUtil(reportMessage.get(0).getShr()))) {
				U2DataExportUtil.insertExcelData(wb, sheet, U2_GENERALAuditor, 0, new Object[]{reportMessage.get(0).getShr()});
			}
//			if (null != reportMessage.get(0).getRq()) {
				U2DataExportUtil.insertExcelData(wb, sheet, U2_GENERALReportTime, 0, new Object[]{DateBean.getChinaDate(txtDate)});
//			}
			if (!"".equals(StringUtil.isNullUtil(reportMessage.get(0).getBz()))) {
				U2DataExportUtil.insertExcelData(wb, sheet, U2_GENERALremark, 0, new Object[]{reportMessage.get(0).getBz()});
			}
		}
		if (true) {//导出
			wb.setForceFormulaRecalculation(true);
			java.io.ByteArrayOutputStream baos = U2DataExportUtil.ExporDataStream(wb);
			if(baos != null){
				byte[] ba = baos.toByteArray();
				excelFile = new ByteArrayInputStream(ba);
				baos.flush();
				baos.close();
			}
		}else {//html
			Document doc = ExcelToHtmlConverter.process( wb );
			TransformerFactory tf = TransformerFactory.newInstance();
            Transformer serializer = tf.newTransformer();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();  
            serializer.transform(new DOMSource(doc), new StreamResult(bos));
                IOUtils.closeQuietly( bos );
   //       System.out.println(bos.toString() + "==========================================");
          String htmlStr = bos.toString().split("css\">")[1];
          String[] styleBodyStr = htmlStr.split("</style>");
   //       System.out.println(styleBodyStr[0]);
          String tableStr = "<table" + styleBodyStr[1].split("<table")[1].split("</body>")[0];
 //         System.out.println("===========" + tableStr);
		}
		
		return "excel";
	}
}
