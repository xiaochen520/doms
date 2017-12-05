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
import com.echo.dto.PcRpdUThinWaterAutoT;
import com.echo.dto.PcRpdUThinWaterGeneralT;
import com.echo.dto.User;
import com.echo.service.LogService;
import com.echo.service.XYWSCLService;
import com.echo.util.AuthorityUtil;
import com.echo.util.CommonsUtil;
import com.echo.util.DateBean;
import com.echo.util.PropertiesConfig;
import com.echo.util.StringUtil;
import com.echo.util.U2DataExportUtil;
import com.opensymphony.xwork2.ActionSupport;

public class XYWSCLAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;

	HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
	private InputStream excelFile = null;
	private XYWSCLService xYWSCLService;

	public void setxYWSCLService(XYWSCLService xYWSCLService) {
		this.xYWSCLService = xYWSCLService;
	}



	private LogService logService;

	public LogService getLogService() {
		return logService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	
	public String onAudit() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONObject obj = new JSONObject();
		User user1 = (User) session.getAttribute("userInfo");
		String RPD_U_THIN_WATER_GENERAL_ID = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("RPD_U_THIN_WATER_GENERAL_ID")));
		List<PcRpdUThinWaterGeneralT> waterGeneral = null;
		PcRpdUThinWaterGeneralT waterGen = null;
		if(RPD_U_THIN_WATER_GENERAL_ID != null && !"".equals(RPD_U_THIN_WATER_GENERAL_ID)){
			waterGeneral = xYWSCLService.SreachWaterGeneral(RPD_U_THIN_WATER_GENERAL_ID, "");
		}else{
			
			obj = CommonsUtil.getRrturnJson("","未获取到稀油联合站污水处理系统日报数据ID" ,"", null, null);
			out.print(obj);
			return null;
		}
		
		if(waterGeneral != null && waterGeneral.size()>0){
			waterGen = waterGeneral.get(0);
		}else{
			obj = CommonsUtil.getRrturnJson("","未获取到二号稀油联合站污水处理日报数据ID对应数据" ,"", null, null);
			out.print(obj);
			return null;
		}
		waterGen.setShr(user1.getOperName());
		waterGen.setShsj(new Date());

		boolean updateflg = false;
		try {
			updateflg = xYWSCLService.updateWaterGeneral(waterGen);
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
				PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "3", "污水处理", waterGen.getRpdUThinWaterGeneralId());
				logService.addLog(log);
			} catch (Exception e) {
				e.printStackTrace();
				obj = CommonsUtil.getRrturnJson("","污水处理日志修改失败" ,"", null, null);
			}
		}
		out.print(obj);
		return null;
	}
	public String updateRPDREPORTMESSAGE() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONObject obj = new JSONObject();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String reportDate = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("BBRQ1")));
		
		if(reportDate == null || "".equals(reportDate)){
			reportDate = df.format(new Date());
		}
		boolean updateflg = false;
		String firstStr = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("firstStr")));
		firstStr = firstStr.substring(0,firstStr.length()-1);
		String[] firstList = firstStr.split(";",-1);
		for(int i=0;i<firstList.length;i++){
			String[] firstPra = firstList[i].split(",",-1);
			String RPD_U_THIN_WATER_AUTO_ID = firstPra[0];
			List<PcRpdUThinWaterAutoT> waterAuto = null;
			PcRpdUThinWaterAutoT waterA = null;
			if(RPD_U_THIN_WATER_AUTO_ID != null && !"".equals(RPD_U_THIN_WATER_AUTO_ID)){
				waterAuto = xYWSCLService.SreachWaterAuto(RPD_U_THIN_WATER_AUTO_ID, "");
			}
			
			if(waterAuto != null && waterAuto.size()>0){
				waterA = waterAuto.get(0);
			}else{
				waterA = new PcRpdUThinWaterAutoT();
			}
			
			String beforetime = DateBean.getBeforeDAYTime(reportDate);
			int calcNum = xYWSCLService.searchCalcNum();
			int zeroIndex = -calcNum/2;
			if(i>=zeroIndex){
				
				waterA.setBbsj(sf.parse(reportDate +" "+ firstPra[1]));
			}else{
				waterA.setBbsj(sf.parse(beforetime +" "+ firstPra[1]));
			}
			waterA.setRpdUThinWaterAutoId(firstPra[0]);
			//waterA.setBbsj(firstPra[1]);
			waterA.setL1_1_TCG(CommonsUtil.isNullOrEmpty(firstPra[2]));
     		waterA.setLI_2_TCG(CommonsUtil.isNullOrEmpty(firstPra[3]));
     		waterA.setLI_1_HCG(CommonsUtil.isNullOrEmpty(firstPra[4]));
     		waterA.setLI_2_HCG(CommonsUtil.isNullOrEmpty(firstPra[5]));
     		waterA.setLi_1_jhg(CommonsUtil.isNullOrEmpty(firstPra[6]));
     		waterA.setLi_2_jhg(CommonsUtil.isNullOrEmpty(firstPra[7]));
     		waterA.setLI_1_WSC(CommonsUtil.isNullOrEmpty(firstPra[8]));
     		waterA.setLI_2_WSC(CommonsUtil.isNullOrEmpty(firstPra[9]));
    		waterA.setLI_WYG(CommonsUtil.isNullOrEmpty(firstPra[10]));
     		waterA.setLT_XFYW(CommonsUtil.isNullOrEmpty(firstPra[11]));
     		waterA.setFI_YYCLZLS(CommonsUtil.isNullOrEmpty(firstPra[12]));
     	//	waterA.setYJ1XFWLJ(CommonsUtil.isNullOrEmpty(firstPra[13]));
     		waterA.setYQLSXFWLJ(CommonsUtil.isNullOrEmpty(firstPra[13]));
     		waterA.setFI_YTZS(CommonsUtil.isNullOrEmpty(firstPra[14]));
     		waterA.setGLQCSXFWLJ(CommonsUtil.isNullOrEmpty(firstPra[15]));
     		waterA.setFI_JHSO1(CommonsUtil.isNullOrEmpty(firstPra[16]));
     		waterA.setWSBCKXFWLJ(CommonsUtil.isNullOrEmpty(firstPra[17]));

     		waterA.setFI_1_FYJ(CommonsUtil.isNullOrEmpty(firstPra[18]));
     		waterA.setYJ1XFWLJ(CommonsUtil.isNullOrEmpty(firstPra[19]));
     		waterA.setFI_1_FYQ(CommonsUtil.isNullOrEmpty(firstPra[20]));
     		waterA.setFYQ1JSXFWLJ(CommonsUtil.isNullOrEmpty(firstPra[21]));
     		waterA.setFI_2_YJO1(CommonsUtil.isNullOrEmpty(firstPra[22]));
     		waterA.setFYQ1YL2XFWLJ(CommonsUtil.isNullOrEmpty(firstPra[23]));
     		
     		waterA.setFI_3_FYJO1(CommonsUtil.isNullOrEmpty(firstPra[24]));
     		waterA.setFYQ1YJ3XFWLJ(CommonsUtil.isNullOrEmpty(firstPra[25]));
     		waterA.setFI_2_FYQ(CommonsUtil.isNullOrEmpty(firstPra[26]));
     		waterA.setFYQ2JSXFWLJ(CommonsUtil.isNullOrEmpty(firstPra[27]));
     		waterA.setFI_2_YJO2(CommonsUtil.isNullOrEmpty(firstPra[28]));
     		waterA.setFYQ2YL2XFWLJ(CommonsUtil.isNullOrEmpty(firstPra[29]));
     		waterA.setFI_3_FYJO2(CommonsUtil.isNullOrEmpty(firstPra[30]));
     		waterA.setFYQ2YJ3XFWLJ(CommonsUtil.isNullOrEmpty(firstPra[31]));
     		waterA.setFI_3_FYQ(CommonsUtil.isNullOrEmpty(firstPra[32]));
     		waterA.setFYQ3JSXFWLJ(CommonsUtil.isNullOrEmpty(firstPra[33]));
     		waterA.setFI_2_YJO3(CommonsUtil.isNullOrEmpty(firstPra[34]));
     		waterA.setFYQ3YL2XFWLJ(CommonsUtil.isNullOrEmpty(firstPra[35]));
     		waterA.setFI_3_FYJO3(CommonsUtil.isNullOrEmpty(firstPra[36]));
     		waterA.setFYQ3YJ3XFWLJ(CommonsUtil.isNullOrEmpty(firstPra[37]));
     		
    		try {
    			updateflg = xYWSCLService.updateWaterAuto(waterA);
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
    		if(!updateflg){
    			obj = CommonsUtil.getRrturnJson("","第"+(i+1)+"行修改失败" ,"", null, null);
    		}
		}
		
		//水源井
     	String SYJ1SL = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("SYJ1SL")));	
     	String SYJ2SL = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("SYJ2SL")));	
     	String SYJ3SL = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("SYJ3SL")));

     	//其他数据
     	String CJSJHY = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("CJSJHY")));
     	String DJHY = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("DJHY")));
     	String DCHY = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("DCHY")));	
     	String XJHY = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("XJHY")));	
     	String XCHY = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("XCHY")));	
     	String YJJKHY = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("YJJKHY")));	
     	String YJCKHY = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("YJCKHY")));	
     	String EJCKHY = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("EJCKHY")));	
     	String ZSJKHY = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("ZSJKHY")));	
     	String W33HY = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("W33HY")));	
     	String GYHY = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("GYHY")));	
     	String CGYHY = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("CGYHY")));
     	
     	String CJSJHY1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("CJSJHY1")));
     	String DJHY1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("DJHY1")));
     	String DCHY1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("DCHY1")));	
     	String XJHY1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("XJHY1")));	
     	String XCHY1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("XCHY1")));	
     	String YJJKHY1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("YJJKHY1")));	
     	String YJCKHY1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("YJCKHY1")));	
     	String EJCKHY1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("EJCKHY1")));	
     	String ZSJKHY1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("ZSJKHY1")));	
     	String W33HY1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("W33HY1")));	
     	String GYHY1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("GYHY1")));	
     	String CGYHY1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("CGYHY1")));
     	
     	String CJSJHY2 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("CJSJHY2")));
     	String DJHY2 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("DJHY2")));
     	String DCHY2 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("DCHY2")));	
     	String XJHY2 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("XJHY2")));	
     	String XCHY2 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("XCHY2")));	
     	String YJJKHY2 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("YJJKHY2")));	
     	String YJCKHY2 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("YJCKHY2")));	
     	String EJCKHY2 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("EJCKHY2")));	
     	String ZSJKHY2 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("ZSJKHY2")));	
     	String W33HY2 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("W33HY2")));	
     	String GYHY2 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("GYHY2")));	
     	String CGYHY2 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("CGYHY2")));
     	
     	String CJSJXFW = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("CJSJXFW")));	
     	String DJXFW = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("DJXFW")));	
     	String DCXFW = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("DCXFW")));	
     	String XJXFW = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("XJXFW")));	
     	String XCXFW = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("XCXFW")));	
     	String YJJKXFW = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("YJJKXFW")));	
     	String YJCKXFW = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("YJCKXFW")));	
     	String EJCKXFW = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("EJCKXFW")));	
     	String ZSJKXFW = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("ZSJKXFW")));	
     	String W33XFW = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("W33XFW")));	
     	String GYXFW = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("GYXFW")));	
     	String CGYXFW = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("CGYXFW")));
     	
     	String CJSJXFW1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("CJSJXFW1")));	
     	String DJXFW1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("DJXFW1")));	
     	String DCXFW1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("DCXFW1")));	
     	String XJXFW1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("XJXFW1")));	
     	String XCXFW1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("XCXFW1")));	
     	String YJJKXFW1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("YJJKXFW1")));	
     	String YJCKXFW1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("YJCKXFW1")));	
     	String EJCKXFW1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("EJCKXFW1")));	
     	String ZSJKXFW1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("ZSJKXFW1")));	
     	String W33XFW1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("W33XFW1")));	
     	String GYXFW1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("GYXFW1")));	
     	String CGYXFW1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("CGYXFW1")));
     	
     	
     	String CJSJXFW2 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("CJSJXFW2")));	
     	String DJXFW2 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("DJXFW2")));	
     	String DCXFW2 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("DCXFW2")));	
     	String XJXFW2 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("XJXFW2")));	
     	String XCXFW2 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("XCXFW2")));	
     	String YJJKXFW2 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("YJJKXFW2")));	
     	String YJCKXFW2 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("YJCKXFW2")));	
     	String EJCKXFW2 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("EJCKXFW2")));	
     	String ZSJKXFW2 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("ZSJKXFW2")));	
     	String W33XFW2 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("W33XFW2")));	
     	String GYXFW2 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("GYXFW2")));	
     	String CGYXFW2 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("CGYXFW2")));
     	
     	String XJJYL = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("XJJYL")));	
     	String ZGJYL = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("ZGJYL")));	
     	String FXYL = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("FXYL")));	
		String FPJYL = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("FPJYL")));	
		String SNL = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("SNL")));	
		String ZYJYL = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("ZYJYL")));
		
		
		String RPD_U_THIN_WATER_GENERAL_ID = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("RPD_U_THIN_WATER_GENERAL_ID")));
		String ZBR = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("ZBR")));
		String BZ = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("BZ")));
		String RQ = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("RQ")));
		if(RQ == null || RQ.equals("")){
			RQ = reportDate;
		}
		List<PcRpdUThinWaterGeneralT> waterGeneral = null;
		PcRpdUThinWaterGeneralT waterGen = null;
		if(RPD_U_THIN_WATER_GENERAL_ID != null && !"".equals(RPD_U_THIN_WATER_GENERAL_ID)){
			waterGeneral = xYWSCLService.SreachWaterGeneral(RPD_U_THIN_WATER_GENERAL_ID, "");
		}
		
		if(waterGeneral != null && waterGeneral.size()>0){
			waterGen = waterGeneral.get(0);
		}else{
			if(RQ != null && !"".equals(RQ)){
				
				waterGeneral = xYWSCLService.SreachWaterGeneral("", RQ);
			}else{
				obj = CommonsUtil.getRrturnJson("","系统无当前日期，请重新选择数据" ,"", null, null);
				out.print(obj);
				return null;
			}
			
			if(waterGeneral != null && waterGeneral.size()>0){
				obj = CommonsUtil.getRrturnJson("","系统已存在当前报表日报记录，请选择其他日期" ,"", null, null);
				out.print(obj);
				return null;
			}else{
				waterGen = new PcRpdUThinWaterGeneralT();
			}
		}
		
		waterGen.setRpdUThinWaterGeneralId(RPD_U_THIN_WATER_GENERAL_ID);
		waterGen.setZbr(ZBR);
		waterGen.setBbrq(DateBean.getStrDate(RQ));
		waterGen.setBz(BZ);

		waterGen.setSyj1sl(CommonsUtil.isNullOrEmpty(SYJ1SL));
		waterGen.setSyj2sl(CommonsUtil.isNullOrEmpty(SYJ2SL));
		waterGen.setSyj3sl(CommonsUtil.isNullOrEmpty(SYJ3SL));
		
		waterGen.setCjsjhy(CJSJHY);
		waterGen.setDjhy(CommonsUtil.isNullOrEmpty(DJHY));
		waterGen.setDchy(CommonsUtil.isNullOrEmpty(DCHY));
		waterGen.setXjhy(CommonsUtil.isNullOrEmpty(XJHY));
		waterGen.setXchy(CommonsUtil.isNullOrEmpty(XCHY));
		waterGen.setYjjkhy(CommonsUtil.isNullOrEmpty(YJJKHY));
		waterGen.setYjckhy(CommonsUtil.isNullOrEmpty(YJCKHY));
		waterGen.setEjckhy(CommonsUtil.isNullOrEmpty(EJCKHY));
		waterGen.setZsjkhy(CommonsUtil.isNullOrEmpty(ZSJKHY));
		waterGen.setW33hy(CommonsUtil.isNullOrEmpty(W33HY));
		waterGen.setGyhy(CommonsUtil.isNullOrEmpty(GYHY));
		waterGen.setCgyhy(CommonsUtil.isNullOrEmpty(CGYHY));
		
		waterGen.setCjsjhy1(CJSJHY1);
		waterGen.setDjhy1(CommonsUtil.isNullOrEmpty(DJHY1));
		waterGen.setDchy1(CommonsUtil.isNullOrEmpty(DCHY1));
		waterGen.setXjhy1(CommonsUtil.isNullOrEmpty(XJHY1));
		waterGen.setXchy1(CommonsUtil.isNullOrEmpty(XCHY1));
		waterGen.setYjjkhy1(CommonsUtil.isNullOrEmpty(YJJKHY1));
		waterGen.setYjckhy1(CommonsUtil.isNullOrEmpty(YJCKHY1));
		waterGen.setEjckhy1(CommonsUtil.isNullOrEmpty(EJCKHY1));
		waterGen.setZsjkhy1(CommonsUtil.isNullOrEmpty(ZSJKHY1));
		waterGen.setW33hy1(CommonsUtil.isNullOrEmpty(W33HY1));
		waterGen.setGyhy1(CommonsUtil.isNullOrEmpty(GYHY1));
		waterGen.setCgyhy1(CommonsUtil.isNullOrEmpty(CGYHY1));
		
		waterGen.setCjsjhy2(CJSJHY2);
		waterGen.setDjhy2(CommonsUtil.isNullOrEmpty(DJHY2));
		waterGen.setDchy2(CommonsUtil.isNullOrEmpty(DCHY2));
		waterGen.setXjhy2(CommonsUtil.isNullOrEmpty(XJHY2));
		waterGen.setXchy2(CommonsUtil.isNullOrEmpty(XCHY2));
		waterGen.setYjjkhy2(CommonsUtil.isNullOrEmpty(YJJKHY2));
		waterGen.setYjckhy2(CommonsUtil.isNullOrEmpty(YJCKHY2));
		waterGen.setEjckhy2(CommonsUtil.isNullOrEmpty(EJCKHY2));
		waterGen.setZsjkhy2(CommonsUtil.isNullOrEmpty(ZSJKHY2));
		waterGen.setW33hy2(CommonsUtil.isNullOrEmpty(W33HY2));
		waterGen.setGyhy2(CommonsUtil.isNullOrEmpty(GYHY2));
		waterGen.setCgyhy2(CommonsUtil.isNullOrEmpty(CGYHY2));
		
		waterGen.setCjsjxfw(CJSJXFW);
		waterGen.setDjxfw(CommonsUtil.isNullOrEmpty(DJXFW));
		waterGen.setDcxfw(CommonsUtil.isNullOrEmpty(DCXFW));
		waterGen.setXjxfw(CommonsUtil.isNullOrEmpty(XJXFW));
		waterGen.setXcxfw(CommonsUtil.isNullOrEmpty(XCXFW));
		waterGen.setYjjkxfw(CommonsUtil.isNullOrEmpty(YJJKXFW));
		waterGen.setYjckxfw(CommonsUtil.isNullOrEmpty(YJCKXFW));
		waterGen.setEjckxfw(CommonsUtil.isNullOrEmpty(EJCKXFW));
		waterGen.setZsjkxfw(CommonsUtil.isNullOrEmpty(ZSJKXFW));
		waterGen.setW33xfw(CommonsUtil.isNullOrEmpty(W33XFW));
		waterGen.setGyxfw(CommonsUtil.isNullOrEmpty(GYXFW));
		waterGen.setCgyxfw(CommonsUtil.isNullOrEmpty(CGYXFW));
		
		waterGen.setCjsjxfw1(CJSJXFW1);
		waterGen.setDjxfw1(CommonsUtil.isNullOrEmpty(DJXFW1));
		waterGen.setDcxfw1(CommonsUtil.isNullOrEmpty(DCXFW1));
		waterGen.setXjxfw1(CommonsUtil.isNullOrEmpty(XJXFW1));
		waterGen.setXcxfw1(CommonsUtil.isNullOrEmpty(XCXFW1));
		waterGen.setYjjkxfw1(CommonsUtil.isNullOrEmpty(YJJKXFW1));
		waterGen.setYjckxfw1(CommonsUtil.isNullOrEmpty(YJCKXFW1));
		waterGen.setEjckxfw1(CommonsUtil.isNullOrEmpty(EJCKXFW1));
		waterGen.setZsjkxfw1(CommonsUtil.isNullOrEmpty(ZSJKXFW1));
		waterGen.setW33xfw1(CommonsUtil.isNullOrEmpty(W33XFW1));
		waterGen.setGyxfw1(CommonsUtil.isNullOrEmpty(GYXFW1));
		waterGen.setCgyxfw1(CommonsUtil.isNullOrEmpty(CGYXFW1));
		
		waterGen.setCjsjxfw2(CJSJXFW2);
		waterGen.setDjxfw2(CommonsUtil.isNullOrEmpty(DJXFW2));
		waterGen.setDcxfw2(CommonsUtil.isNullOrEmpty(DCXFW2));
		waterGen.setXjxfw2(CommonsUtil.isNullOrEmpty(XJXFW2));
		waterGen.setXcxfw2(CommonsUtil.isNullOrEmpty(XCXFW2));
		waterGen.setYjjkxfw2(CommonsUtil.isNullOrEmpty(YJJKXFW2));
		waterGen.setYjckxfw2(CommonsUtil.isNullOrEmpty(YJCKXFW2));
		waterGen.setEjckxfw2(CommonsUtil.isNullOrEmpty(EJCKXFW2));
		waterGen.setZsjkxfw2(CommonsUtil.isNullOrEmpty(ZSJKXFW2));
		waterGen.setW33xfw2(CommonsUtil.isNullOrEmpty(W33XFW2));
		waterGen.setGyxfw2(CommonsUtil.isNullOrEmpty(GYXFW2));
		waterGen.setCgyxfw2(CommonsUtil.isNullOrEmpty(CGYXFW2));
		
		
		
		waterGen.setXjjyl(CommonsUtil.isNullOrEmpty(XJJYL));
		waterGen.setZgjyl(CommonsUtil.isNullOrEmpty(ZGJYL));
		waterGen.setFxyl(CommonsUtil.isNullOrEmpty(FXYL));
		waterGen.setFpjyl(CommonsUtil.isNullOrEmpty(FPJYL));
		waterGen.setSnl(CommonsUtil.isNullOrEmpty(SNL));
		waterGen.setZyjyl(CommonsUtil.isNullOrEmpty(ZYJYL));
		
		try {
			updateflg = xYWSCLService.updateWaterGeneral(waterGen);
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
				PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "3", "污水处理综合日报", waterGen.getRpdUThinWaterGeneralId());
				logService.addLog(log);
			} catch (Exception e) {
				e.printStackTrace();
				obj = CommonsUtil.getRrturnJson("","污水处理综合日报修改失败" ,"", null, null);
			}
		}
		out.print(obj);
		return null;
	}
	public InputStream getExcelFile() {
		return excelFile;
	}
	public void setExcelFile(InputStream excelFile) {
		this.excelFile = excelFile;
	}
	
	public String getFileName() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd ");
		String downloadFileName = (sf.format(new Date()).toString())+ "污水处理综合日报.xls";
		try {
			downloadFileName = new String(downloadFileName.getBytes(),"ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return downloadFileName;
	}
	public String searchXYWSDatas() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String txtDate = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("txtDate")));
		List<PcRpdUThinWaterGeneralT> waterGen = null;
		PcRpdUThinWaterGeneralT waterG = null;
		//获取当天所以二号稠油联合站原油系统日报数据
		JSONObject XYWSCLobj = null;
		JSONObject obj = null;
		if(txtDate == null || "".equals(txtDate)){
			txtDate = DateBean.getSystemTime1();
		}
		
		try {
			XYWSCLobj = xYWSCLService.searchXYWSDatas(txtDate);

		} catch (Exception e) {
			e.printStackTrace();
			obj = CommonsUtil.getRrturnJson("","当前日期搜索失败请选择其他日期" ,"", null, null);
			out.print(obj);
			return null;
		}
		try {
			waterGen = xYWSCLService.SreachWaterGen(txtDate);
			if(waterGen != null && waterGen.size()>0){
				
			}else{
				waterG = new PcRpdUThinWaterGeneralT();
				waterG.setBbrq(DateBean.getStrDate(txtDate));
				boolean updateflg = false;
				try {
					updateflg = xYWSCLService.updateWaterGeneral(waterG);
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
						PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "1", "综合日报", waterG.getRpdUThinWaterGeneralId());
						logService.addLog(log);
					} catch (Exception e) {
						e.printStackTrace();
						obj = CommonsUtil.getRrturnJson("","综合日报日志添加失败" ,"", null, null);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			obj = CommonsUtil.getRrturnJson("","当前日期搜索失败请选择其他日期" ,"", null, null);
			out.print(obj);
			return null;
		}
		
		JSONArray otherData = xYWSCLService.searchWSCLOther(txtDate);
		XYWSCLobj.put("OTHERDATA", otherData);
		
		obj = CommonsUtil.getRrturnJson("","" ,"", XYWSCLobj, null);
		out.print(obj);
	return null;
	
	
	
}
	
	//根据权限进行页面初始化
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
			butJson = AuthorityUtil.getButtonJson("MENU092", user);
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
	
	public String searchU2YYCLJY() throws Exception {
		
		return null;
	}
	
	public String exportU2_OIL() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		PropertiesConfig pc = new PropertiesConfig();
		String txtDate = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("txtDate")));
		if ("".equals(txtDate)) {
			txtDate = DateBean.getSystemTime1();
		}
		String fields = pc.getSystemConfiguration("U_THIN_WATERFields");
		String templetFilePath = request.getSession().getServletContext().getRealPath("/") + "exceltemplet\\combination\\污水处理综合日报.xls";
		HSSFWorkbook wb = ExcelToHtmlUtils.loadXls( new File( templetFilePath ) );
		HSSFSheet sheet = wb.getSheetAt(0);
		List<Object[]> ThinWateList = xYWSCLService.searchWSCLOther(txtDate, fields, "");
		if ( ThinWateList != null && ThinWateList.size() != 0 ) {
			String[] UThinWaterAutoInsertPostion = pc.getSystemConfiguration("U_THIN_WATERAutoInsertPostion").split(",");
			String[] startPositions = pc.getSystemConfiguration("U_THIN_WATERFieldStartPositions").split(",");
			String[] endPositions = pc.getSystemConfiguration("U_THIN_WATERFieldEndPositions").split(",");
			String[][] positions = new String[startPositions.length][2];
			for (int i = 0; i < startPositions.length; i++) {
				positions[i][0] = startPositions[i];
				positions[i][1] = endPositions[i];
			}
			Object[] objDataList = U2DataExportUtil.splitDataByPosition(ThinWateList, positions);
			//按分组 插入数据 
			U2DataExportUtil.insertDataByPosition(objDataList, wb, sheet, UThinWaterAutoInsertPostion);
		}
		fields = pc.getSystemConfiguration("U_THIN_WATEROtherFields");
		List<Object[]> ThinWateOtherList = xYWSCLService.searchWSCLOther(txtDate, fields, "PC_RPD_U_THIN_WATER_GENERAL_T");
		if ( ThinWateOtherList != null && ThinWateOtherList.size() != 0 ) {
			String[] OtherInsertPostion = pc.getSystemConfiguration("U_THIN_WATEROtherInsertPostion").split(",");
			String[] startPositionsO = pc.getSystemConfiguration("U_THIN_WATEROtherStartPositions").split(",");
			String[] endPositionsO = pc.getSystemConfiguration("U_THIN_WATEROtherEndPositions").split(",");
			String[][] positionsOther = new String[startPositionsO.length][2];
			for (int i = 0; i < startPositionsO.length; i++) {
				positionsOther[i][0] = startPositionsO[i];
				positionsOther[i][1] = endPositionsO[i];
			}
			Object[] objDataLists = U2DataExportUtil.splitDataByPosition(ThinWateOtherList, positionsOther);
			//按分组 插入数据 
			U2DataExportUtil.insertDataByPosition(objDataLists, wb, sheet, OtherInsertPostion);
			// 获取单位、值班人、审核人、日期、备注数据
			String[] baseInfo = pc.getSystemConfiguration( "U_THIN_WATERBaseFields" ).split(",");
			int sp = Integer.parseInt(baseInfo[0]);//数据开始位置
			// 获取单位、值班人、审核人、日期、备注数据插入位置
			String[] basePostion = pc.getSystemConfiguration( "U_THIN_WATERBaseInfo" ).split(",");
			for (int j = 0; j < basePostion.length; j++) {
				if (ThinWateOtherList.get(0)[sp + j] != null) {
					U2DataExportUtil.insertExcelData(wb, sheet, basePostion[j], 0, new Object[]{ThinWateOtherList.get(0)[sp + j]});
				}
			}
		}
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
}
