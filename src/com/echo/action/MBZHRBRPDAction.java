package com.echo.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import net.sf.json.JSONObject;
import org.apache.poi.hssf.converter.ExcelToHtmlConverter;
import org.apache.poi.hssf.converter.ExcelToHtmlUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.util.IOUtils;
import org.apache.struts2.ServletActionContext;
import org.w3c.dom.Document;
import com.echo.dto.PcRdLoginfoT;
import com.echo.dto.PcRpdU1SagdAutoT;
import com.echo.dto.PcRpdU1SagdGeneralT;
import com.echo.dto.PcRpdU1SagdLiquidT;
import com.echo.dto.PcRpdU1SagdPivotalT;
import com.echo.dto.User;
import com.echo.service.LogService;
import com.echo.service.MBZHRBRPDService;
import com.echo.util.AuthorityUtil;
import com.echo.util.CommonsUtil;
import com.echo.util.DateBean;
import com.echo.util.PropertiesConfig;
import com.echo.util.StringUtil;
import com.echo.util.U2DataExportUtil;
import com.opensymphony.xwork2.ActionSupport;


public class MBZHRBRPDAction extends ActionSupport {
	private static final long serialVersionUID = 12L;
	HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
	private InputStream excelFile = null;
	private MBZHRBRPDService mbzhrbrpdService;
	
	private LogService logService;

	public void setMbzhrbrpdService(MBZHRBRPDService mbzhrbrpdService) {
		this.mbzhrbrpdService = mbzhrbrpdService;
	}

	public MBZHRBRPDService getMbzhrbrpdService() {
		return mbzhrbrpdService;
	}

	public LogService getLogService() {
		return logService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	
	public String getFileName() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd ");
		String downloadFileName = (sf.format(new Date()).toString())+ "SAGD密闭试验站报表.xls";
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
		JSONObject jsonobj = new JSONObject();
		String txtDate = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("txtDate")));
	
		JSONObject obj = new JSONObject();
		if(txtDate == null || "".equals(txtDate)){
			txtDate = DateBean.getSystemTime1();
		}
		
		try {
			jsonobj = mbzhrbrpdService.searchZHRB(txtDate);
			
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
			butJson = AuthorityUtil.getButtonJson("MENU101", user);
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
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String reportDate = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("RQ")));
		
		if(reportDate == null || "".equals(reportDate)){
			reportDate = df.format(new Date());
		}
		boolean updateflg = false;
		String firstStr = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("firstStr"))); //重要参数
		firstStr = firstStr.substring(0,firstStr.length()-1);
		String[] firstList = firstStr.split(";",-1);
		String beforetime = DateBean.getBeforeDAYTime(reportDate);
		for(int i=0;i<firstList.length;i++){
			String[] firstPra = firstList[i].split(",",-1);
			String RD_U1_SAGD_PIVOTAL_ID = firstPra[0]; 
			List<PcRpdU1SagdPivotalT> pivotal = null;
			PcRpdU1SagdPivotalT pivotalRec = null;
			if(RD_U1_SAGD_PIVOTAL_ID != null && !"".equals(RD_U1_SAGD_PIVOTAL_ID)){
				pivotal = mbzhrbrpdService.searchU1SagdPivotal(RD_U1_SAGD_PIVOTAL_ID, "");
				if(pivotal != null && pivotal.size()>0){
					pivotalRec = pivotal.get(0);
				}else{
					pivotalRec = new PcRpdU1SagdPivotalT();
				}
				
				int calcNum = mbzhrbrpdService.searchCalcNum();
				int zeroIndex = -calcNum/4;
				if(i>=zeroIndex){
					
					pivotalRec.setBbsj(sf.parse(reportDate +" "+ firstPra[1]));
				}else{
					pivotalRec.setBbsj(sf.parse(beforetime +" "+ firstPra[1]));
				}

				pivotalRec.setRpdU1SagdPivotalId(firstPra[0]);
				//pivotalRec.setBbsj(firstPra[1]);
				pivotalRec.setYjcslj(CommonsUtil.isNullOrEmpty(firstPra[2]));
	     		pivotalRec.setRhxcslj(CommonsUtil.isNullOrEmpty(firstPra[3]));
	     		pivotalRec.setRhx1jylj(CommonsUtil.isNullOrEmpty(firstPra[4]));
	     		pivotalRec.setRhx2jylj(CommonsUtil.isNullOrEmpty(firstPra[5]));
	     		pivotalRec.setRhx3jylj(CommonsUtil.isNullOrEmpty(firstPra[6]));
	     		pivotalRec.setZcsl(CommonsUtil.isNullOrEmpty(firstPra[7]));
	     		pivotalRec.setBhl(CommonsUtil.isNullOrEmpty(firstPra[8]));
	     		pivotalRec.setDtcylj(CommonsUtil.isNullOrEmpty(firstPra[9]));
	    		pivotalRec.setDtcyll(CommonsUtil.isNullOrEmpty(firstPra[10]));
	     		pivotalRec.setXxlljlj(CommonsUtil.isNullOrEmpty(firstPra[11]));
	     		pivotalRec.setXxlljll(CommonsUtil.isNullOrEmpty(firstPra[12]));
	     		pivotalRec.setCcllj(CommonsUtil.isNullOrEmpty(firstPra[13]));
	     		pivotalRec.setSxcshy(CommonsUtil.isNullOrEmpty(firstPra[14]));
	     		pivotalRec.setDtcshy(CommonsUtil.isNullOrEmpty(firstPra[15]));
	     		
	    		try {
	    			updateflg = mbzhrbrpdService.updatePivotal(pivotalRec);
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
		}
		
		String secondStr = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("secondStr"))); //重要参数
		secondStr = secondStr.substring(0,secondStr.length()-1);
		String[] secondList = secondStr.split(";",-1);
		for(int i=0;i<secondList.length;i++){
			String[] secondPra = secondList[i].split(",",-1);
			String RD_U1_SAGD_AUTO_ID = secondPra[0]; 
			List<PcRpdU1SagdAutoT> auto = null;
			PcRpdU1SagdAutoT autoRec = null;
			if(RD_U1_SAGD_AUTO_ID != null && !"".equals(RD_U1_SAGD_AUTO_ID)){
				auto = mbzhrbrpdService.searchU1SagdAuto(RD_U1_SAGD_AUTO_ID, "");
				
				if(auto != null && auto.size()>0){
					autoRec = auto.get(0);
				}else{
					autoRec = new PcRpdU1SagdAutoT();
				}
				
				int calcNum = mbzhrbrpdService.searchCalcNum();
				int zeroIndex = -calcNum/2;
				if(i>=zeroIndex){
					
					autoRec.setBbsj(sf.parse(reportDate +" "+ secondPra[1]));
				}else{
					autoRec.setBbsj(sf.parse(beforetime +" "+ secondPra[1]));
				}

				autoRec.setRpdU1SagdAutoId(secondPra[0]);
				//pivotalRec.setBbsj(firstPra[1]);
				autoRec.setYcljg1yw(CommonsUtil.isNullOrEmpty(secondPra[2]));
	     		autoRec.setYcljg2yw(CommonsUtil.isNullOrEmpty(secondPra[3]));	
	     		autoRec.setYcljjyl(CommonsUtil.isNullOrEmpty(secondPra[4]));	
	     		autoRec.setZxprjg3yw(CommonsUtil.isNullOrEmpty(secondPra[5]));	
	     		autoRec.setZxprjg4yw(CommonsUtil.isNullOrEmpty(secondPra[6]));	
	     		autoRec.setZxprjjyl(CommonsUtil.isNullOrEmpty(secondPra[7]));	
	     		autoRec.setYclg5yw(CommonsUtil.isNullOrEmpty(secondPra[8]));	
	     		autoRec.setYclg5jyl(CommonsUtil.isNullOrEmpty(secondPra[9]));	
	     		autoRec.setYclg6yw(CommonsUtil.isNullOrEmpty(secondPra[10]));	
	     		autoRec.setYclg6jyl(CommonsUtil.isNullOrEmpty(secondPra[11]));
	     		autoRec.setJyb1ckyl(CommonsUtil.isNullOrEmpty(secondPra[12]));
	     		autoRec.setJyb1kd(CommonsUtil.isNullOrEmpty(secondPra[13]));		
	     		autoRec.setJyb2ckyl(CommonsUtil.isNullOrEmpty(secondPra[14]));
	     		autoRec.setJyb2kd(CommonsUtil.isNullOrEmpty(secondPra[15]));
	     		autoRec.setJyb3ckyl(CommonsUtil.isNullOrEmpty(secondPra[16]));
	     		autoRec.setJyb3kd(CommonsUtil.isNullOrEmpty(secondPra[17]));	
	     		autoRec.setJyb4ckyl(CommonsUtil.isNullOrEmpty(secondPra[18]));	
	     		autoRec.setJyb4kd(CommonsUtil.isNullOrEmpty(secondPra[19]));	
	     		autoRec.setCcbckyl(CommonsUtil.isNullOrEmpty(secondPra[20]));	
	     		autoRec.setCcbpl(CommonsUtil.isNullOrEmpty(secondPra[21]));	
	     		autoRec.setPr3902(CommonsUtil.isNullOrEmpty(secondPra[22]));	
	     		autoRec.setLrc2101a(CommonsUtil.isNullOrEmpty(secondPra[23]));	
	     		autoRec.setPt2112(CommonsUtil.isNullOrEmpty(secondPra[24]));	
	     		autoRec.setLrc2101b(CommonsUtil.isNullOrEmpty(secondPra[25]));
	     		autoRec.setPrc3flq(CommonsUtil.isNullOrEmpty(secondPra[26]));
	     		autoRec.setLrc3flq(CommonsUtil.isNullOrEmpty(secondPra[27]));		
	     		autoRec.setTr3101a(CommonsUtil.isNullOrEmpty(secondPra[28]));
	     		autoRec.setLit3101a(CommonsUtil.isNullOrEmpty(secondPra[29]));
	     		autoRec.setPr3101a(CommonsUtil.isNullOrEmpty(secondPra[30]));
	     		autoRec.setTr3101b(CommonsUtil.isNullOrEmpty(secondPra[31]));	
	     		autoRec.setLit3101b(CommonsUtil.isNullOrEmpty(secondPra[32]));	
	     		autoRec.setPr3101b(CommonsUtil.isNullOrEmpty(secondPra[33]));	
	     		autoRec.setTr3101c(CommonsUtil.isNullOrEmpty(secondPra[34]));	
	     		autoRec.setLit3101c(CommonsUtil.isNullOrEmpty(secondPra[35]));	
	     		autoRec.setPr3101c(CommonsUtil.isNullOrEmpty(secondPra[36]));	
	     		autoRec.setLrc3202d(CommonsUtil.isNullOrEmpty(secondPra[37]));	
	     		autoRec.setPra3201d(CommonsUtil.isNullOrEmpty(secondPra[38]));	
	     		autoRec.setTr3201d(CommonsUtil.isNullOrEmpty(secondPra[39]));
	     		autoRec.setPrc3703(CommonsUtil.isNullOrEmpty(secondPra[40]));
	     		autoRec.setPrc3702(CommonsUtil.isNullOrEmpty(secondPra[41]));		
	     		autoRec.setPrc3706(CommonsUtil.isNullOrEmpty(secondPra[42]));
	     		autoRec.setPrc3704(CommonsUtil.isNullOrEmpty(secondPra[43]));
	     		autoRec.setPrc3705(CommonsUtil.isNullOrEmpty(secondPra[44]));
	     		autoRec.setPrc3707(CommonsUtil.isNullOrEmpty(secondPra[45]));	
	     		autoRec.setYsjP1(CommonsUtil.isNullOrEmpty(secondPra[46]));	
	     		autoRec.setYsjT1(CommonsUtil.isNullOrEmpty(secondPra[47]));	
	     		autoRec.setYsjP2(CommonsUtil.isNullOrEmpty(secondPra[48]));	
	     		autoRec.setYsjT2(CommonsUtil.isNullOrEmpty(secondPra[49]));	
	     		autoRec.setLrc3201a(CommonsUtil.isNullOrEmpty(secondPra[50]));	
	     		autoRec.setLrc3202a(CommonsUtil.isNullOrEmpty(secondPra[51]));	
	     		autoRec.setLrc3203a(CommonsUtil.isNullOrEmpty(secondPra[52]));
	     		autoRec.setPr3201a(CommonsUtil.isNullOrEmpty(secondPra[53]));
	     		autoRec.setTr3201a(CommonsUtil.isNullOrEmpty(secondPra[54]));
	     		autoRec.setLrc3201b(CommonsUtil.isNullOrEmpty(secondPra[55]));		
	     		autoRec.setLrc3202b(CommonsUtil.isNullOrEmpty(secondPra[56]));
	     		autoRec.setLrc3203b(CommonsUtil.isNullOrEmpty(secondPra[57]));
	     		autoRec.setPr3201b(CommonsUtil.isNullOrEmpty(secondPra[58]));
	     		autoRec.setTr3201b(CommonsUtil.isNullOrEmpty(secondPra[59]));	
	     		autoRec.setLrc3201c(CommonsUtil.isNullOrEmpty(secondPra[60]));	
	     		autoRec.setLrc3202c(CommonsUtil.isNullOrEmpty(secondPra[61]));	
	     		autoRec.setLrc3203c(CommonsUtil.isNullOrEmpty(secondPra[62]));	
	     		autoRec.setPr3201c(CommonsUtil.isNullOrEmpty(secondPra[63]));	
	     		autoRec.setTr3201c(CommonsUtil.isNullOrEmpty(secondPra[64]));	
	     		autoRec.setTt102(CommonsUtil.isNullOrEmpty(secondPra[65]));	
	     		autoRec.setTt105(CommonsUtil.isNullOrEmpty(secondPra[66]));	
	     		autoRec.setHrq1lyhqwd(CommonsUtil.isNullOrEmpty(secondPra[67]));
	     		autoRec.setTt115(CommonsUtil.isNullOrEmpty(secondPra[68]));
	     		autoRec.setFit101(CommonsUtil.isNullOrEmpty(secondPra[69]));		
	     		autoRec.setTt101(CommonsUtil.isNullOrEmpty(secondPra[70]));
	     		autoRec.setTt106(CommonsUtil.isNullOrEmpty(secondPra[71]));
	     		autoRec.setHrq2lyhqwd(CommonsUtil.isNullOrEmpty(secondPra[72]));
	     		autoRec.setTt115(CommonsUtil.isNullOrEmpty(secondPra[73]));
	     		autoRec.setFit102(CommonsUtil.isNullOrEmpty(secondPra[74]));
	     		autoRec.setTt103(CommonsUtil.isNullOrEmpty(secondPra[75]));
	     		autoRec.setTt107(CommonsUtil.isNullOrEmpty(secondPra[76]));
	     		autoRec.setHrq3lyhqwd(CommonsUtil.isNullOrEmpty(secondPra[77]));
	     		autoRec.setFit103(CommonsUtil.isNullOrEmpty(secondPra[78]));
	     		autoRec.setTt112(CommonsUtil.isNullOrEmpty(secondPra[79]));
	     		autoRec.setTt104(CommonsUtil.isNullOrEmpty(secondPra[80]));
	     		autoRec.setTt108(CommonsUtil.isNullOrEmpty(secondPra[81]));
	     		autoRec.setHrq4lyhqwd(CommonsUtil.isNullOrEmpty(secondPra[82]));
	     		autoRec.setTt112(CommonsUtil.isNullOrEmpty(secondPra[83]));
	     		autoRec.setFit104(CommonsUtil.isNullOrEmpty(secondPra[84]));
	     		autoRec.setTe2102(CommonsUtil.isNullOrEmpty(secondPra[85]));
	     		autoRec.setFit2101(CommonsUtil.isNullOrEmpty(secondPra[86]));
	     		autoRec.setTrc3301(CommonsUtil.isNullOrEmpty(secondPra[87]));
	     		autoRec.setFit2102(CommonsUtil.isNullOrEmpty(secondPra[88]));
	     		autoRec.setTe2113(CommonsUtil.isNullOrEmpty(secondPra[89]));
	     		autoRec.setFit2105(CommonsUtil.isNullOrEmpty(secondPra[90]));
	     		autoRec.setTe2114(CommonsUtil.isNullOrEmpty(secondPra[91]));
	     		autoRec.setFit2106(CommonsUtil.isNullOrEmpty(secondPra[92]));
	     		autoRec.setGzqyl(CommonsUtil.isNullOrEmpty(secondPra[93]));
	     		autoRec.setGzqwd(CommonsUtil.isNullOrEmpty(secondPra[94]));
	     		
	    		try {
	    			updateflg = mbzhrbrpdService.updateAuto(autoRec);
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
			
			
		}
		String RD_U1_SAGD_LIQUID_ID3 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("RD_U1_SAGD_LIQUID_ID3")));
     	String RD_U1_SAGD_LIQUID_ID4 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("RD_U1_SAGD_LIQUID_ID4")));
     	String RD_U1_SAGD_LIQUID_ID5 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("RD_U1_SAGD_LIQUID_ID5")));
     	
     	String ZYL16 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("ZYL16")));
		String SXCS16 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("SXCS16")));
		String RHXCS16 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("RHXCS16")));
		String DTCYL16 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("DTCYL16")));

		String ZYL20 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("ZYL20")));
		String SXCS20 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("SXCS20")));
		String RHXCS20 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("RHXCS20")));
		String DTCYL20 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("DTCYL20")));

		String ZYL8 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("ZYL8")));
		String SXCS8 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("SXCS8")));
		String RHXCS8 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("RHXCS8")));
		String DTCYL8 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("DTCYL8")));

		List<PcRpdU1SagdLiquidT> liquidList16 = null;
		List<PcRpdU1SagdLiquidT> liquidList20 = null;
		List<PcRpdU1SagdLiquidT> liquidList8 = null;
		PcRpdU1SagdLiquidT sagdliq16 = null;
		PcRpdU1SagdLiquidT sagdliq20 = null;
		PcRpdU1SagdLiquidT sagdliq8 = null;
		if(RD_U1_SAGD_LIQUID_ID3 != null && !"".equals(RD_U1_SAGD_LIQUID_ID3)){
			liquidList16 = mbzhrbrpdService.SreachLiquid(RD_U1_SAGD_LIQUID_ID3, "");
			if(liquidList16 != null && liquidList16.size()>0){
				sagdliq16 = liquidList16.get(0);
			}else{
				sagdliq16 = new PcRpdU1SagdLiquidT();
			}
			sagdliq16.setRpdU1SagdLiquidId(RD_U1_SAGD_LIQUID_ID3);
			sagdliq16.setBbsj(sf.parse(beforetime+" 16:00"));
			sagdliq16.setZyl(CommonsUtil.isNullOrEmpty(ZYL16));
			sagdliq16.setSxcs(CommonsUtil.isNullOrEmpty(SXCS16));
			sagdliq16.setRhxcs(CommonsUtil.isNullOrEmpty(RHXCS16));
			sagdliq16.setDtcyl(CommonsUtil.isNullOrEmpty(DTCYL16));
		}
		
		
		if(RD_U1_SAGD_LIQUID_ID4 != null && !"".equals(RD_U1_SAGD_LIQUID_ID4)){
			liquidList20 = mbzhrbrpdService.SreachLiquid(RD_U1_SAGD_LIQUID_ID4, "");
			if(liquidList20 != null && liquidList20.size()>0){
				sagdliq20 = liquidList20.get(0);
			}else{
				sagdliq20 = new PcRpdU1SagdLiquidT();
			}
			sagdliq20.setRpdU1SagdLiquidId(RD_U1_SAGD_LIQUID_ID4);
			sagdliq20.setBbsj(sf.parse(beforetime+" 20:00"));
			sagdliq20.setZyl(CommonsUtil.isNullOrEmpty(ZYL20));
			sagdliq20.setSxcs(CommonsUtil.isNullOrEmpty(SXCS20));
			sagdliq20.setRhxcs(CommonsUtil.isNullOrEmpty(RHXCS20));
			sagdliq20.setDtcyl(CommonsUtil.isNullOrEmpty(DTCYL20));
		}
		
		
		if(RD_U1_SAGD_LIQUID_ID5 != null && !"".equals(RD_U1_SAGD_LIQUID_ID5)){
			liquidList8 = mbzhrbrpdService.SreachLiquid(RD_U1_SAGD_LIQUID_ID5, "");
			
			if(liquidList8 != null && liquidList8.size()>0){
				sagdliq8 = liquidList8.get(0);
			}else{
				sagdliq8 = new PcRpdU1SagdLiquidT();
			}
			sagdliq8.setRpdU1SagdLiquidId(RD_U1_SAGD_LIQUID_ID5);
			sagdliq8.setBbsj(sf.parse(reportDate+" 08:00"));
			sagdliq8.setZyl(CommonsUtil.isNullOrEmpty(ZYL8));
			sagdliq8.setSxcs(CommonsUtil.isNullOrEmpty(SXCS8));
			sagdliq8.setRhxcs(CommonsUtil.isNullOrEmpty(RHXCS8));
			sagdliq8.setDtcyl(CommonsUtil.isNullOrEmpty(DTCYL8));
		}
		

		try {
			if(sagdliq16!=null)
				updateflg = mbzhrbrpdService.updateLiq(sagdliq16);
			if(sagdliq20!=null)
				updateflg = mbzhrbrpdService.updateLiq(sagdliq20);
			if(sagdliq8!=null)
				updateflg = mbzhrbrpdService.updateLiq(sagdliq8);
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
				PcRdLoginfoT log = null;
				if(sagdliq16!=null){
					log = CommonsUtil.getLogInfo(user1, "3", "密闭综合日报", sagdliq16.getRpdU1SagdLiquidId());
					logService.addLog(log);
				}
				if(sagdliq20!=null){
					log = CommonsUtil.getLogInfo(user1, "3", "密闭综合日报", sagdliq20.getRpdU1SagdLiquidId());
					logService.addLog(log);

				}
				if(sagdliq8!=null){
					log = CommonsUtil.getLogInfo(user1, "3", "密闭综合日报", sagdliq8.getRpdU1SagdLiquidId());
					logService.addLog(log);
				}
			} catch (Exception e) {
				e.printStackTrace();
				obj = CommonsUtil.getRrturnJson("","密闭综合日报修改失败" ,"", null, null);
			}
		}
		
		
     	//浓度
     	String RD_U1_SAGD_GENERAL_ID = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("RD_U1_SAGD_GENERAL_ID")));
     	String RCCYL = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("RCCYL")));
     	String YCLJ = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("YCLJ")));
     	String ZXPRJ = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("ZXPRJ")));
		
		String ZYGZ = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("ZYGZ")));
		String ZBR = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("ZBR")));
		String BZ = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("REMARK")));
		String RQ = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("RQ")));
		if(RQ == null || RQ.equals("")){
			RQ = reportDate;
		}
		List<PcRpdU1SagdGeneralT> sagdGenList = null;
		PcRpdU1SagdGeneralT sagdGen = null;
		if(RD_U1_SAGD_GENERAL_ID != null && !"".equals(RD_U1_SAGD_GENERAL_ID)){
			sagdGenList = mbzhrbrpdService.SreachGeneral(RD_U1_SAGD_GENERAL_ID, "");
		}
		
		if(sagdGenList != null && sagdGenList.size()>0){
			sagdGen = sagdGenList.get(0);
		}else{
			if(RQ != null && !"".equals(RQ)){
				
				sagdGenList = mbzhrbrpdService.SreachGeneral("", RQ);
			}else{
				obj = CommonsUtil.getRrturnJson("","系统无当前日期，请重新选择数据" ,"", null, null);
				out.print(obj);
				return null;
			}
			
			if(sagdGenList != null && sagdGenList.size()>0){
				obj = CommonsUtil.getRrturnJson("","系统已存在当前报表日报记录，请选择其他日期" ,"", null, null);
				out.print(obj);
				return null;
			}else{
				sagdGen = new PcRpdU1SagdGeneralT();
			}
		}
		
		sagdGen.setRpdU1SagdGeneralId(RD_U1_SAGD_GENERAL_ID);
		sagdGen.setRccyl(CommonsUtil.isNullOrEmpty(RCCYL));
		sagdGen.setYclj(CommonsUtil.isNullOrEmpty(YCLJ));
		sagdGen.setZxprj(CommonsUtil.isNullOrEmpty(ZXPRJ));
		sagdGen.setZbr(ZBR);
		sagdGen.setBbrq(DateBean.getStrDate(RQ));
		sagdGen.setRemark(BZ);
		sagdGen.setZygz(ZYGZ);

		try {
			updateflg = mbzhrbrpdService.updateGeneral(sagdGen);
			System.out.println(""+updateflg);
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
				if(sagdGen!=null){
					User user1 = (User) session.getAttribute("userInfo");
					PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "3", "密闭综合日报", sagdGen.getRpdU1SagdGeneralId());
					logService.addLog(log);
				}
			} catch (Exception e) {
				e.printStackTrace();
				obj = CommonsUtil.getRrturnJson("","密闭综合日报修改失败" ,"", null, null);
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
		String RD_U1_SAGD_GENERAL_ID = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("RD_U1_SAGD_GENERAL_ID")));
		List<PcRpdU1SagdGeneralT> sagdGeneral = null;
		PcRpdU1SagdGeneralT sagdGen = null;
		if(RD_U1_SAGD_GENERAL_ID != null && !"".equals(RD_U1_SAGD_GENERAL_ID)){
			sagdGeneral = mbzhrbrpdService.SreachGeneral(RD_U1_SAGD_GENERAL_ID, "");
		}else{
			
			obj = CommonsUtil.getRrturnJson("","未获取到稀油联合站污水处理系统日报数据ID" ,"", null, null);
			out.print(obj);
			return null;
		}
		
		if(sagdGeneral != null && sagdGeneral.size()>0){
			sagdGen = sagdGeneral.get(0);
		}else{
			obj = CommonsUtil.getRrturnJson("","未获取到二号稀油联合站污水处理日报数据ID对应数据" ,"", null, null);
			out.print(obj);
			return null;
		}
		sagdGen.setShr(user1.getOperName());
		sagdGen.setShsj(new Date());

		boolean updateflg = false;
		try {
			updateflg = mbzhrbrpdService.updateGeneral(sagdGen);
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
				PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "3", "污水处理", sagdGen.getRpdU1SagdGeneralId());
				logService.addLog(log);
			} catch (Exception e) {
				e.printStackTrace();
				obj = CommonsUtil.getRrturnJson("","污水处理日志修改失败" ,"", null, null);
			}
		}
		out.print(obj);
		return null;
	}
	
	
	
	
	
	
	
	
	@SuppressWarnings("unused")
	public String exportData() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		//获取报表的时间
		String txtDate = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("reportDate")));
		//如果得到的报表时间为空，则自动定义为系统当前时间
		if ("".equals(txtDate)) {
			txtDate = DateBean.getSystemTime1();
//			return null;
		}
		//获取要导出文件EXCL报表的路径
		String templetFilePath = request.getSession().getServletContext().getRealPath("/") + "exceltemplet\\combination\\SAGD密闭试验站报表.xls";
		//通过路径获取该EXCL报表对象
		HSSFWorkbook wb = ExcelToHtmlUtils.loadXls( new File( templetFilePath ) );
		//一个EXCL表格中可能有多个表,锁定第一张表为导出报表格式
		HSSFSheet sheet = wb.getSheetAt(0);
//		4条sql
		//获取自定义配置文件对象
		PropertiesConfig pc = new PropertiesConfig();
		String sagdPivotal = pc.getSystemConfiguration("PC_RPD_U1_SAGD_PIVOTAL_T");
		String sagdGeneral = pc.getSystemConfiguration("PC_RPD_U1_SAGD_GENERAL_T");
		String sagdLiquid = pc.getSystemConfiguration("PC_RPD_U1_SAGD_LIQUID_T");
		String sagdAuto = pc.getSystemConfiguration("PC_RPD_U1_SAGD_AUTO_T");
		String Others = pc.getSystemConfiguration("Others");
//		4张表日期字段
		String spt = pc.getSystemConfiguration("spt");
		String sgt = pc.getSystemConfiguration("sgt");
		String slt = pc.getSystemConfiguration("slt");
		String rpdsat = pc.getSystemConfiguration("rpdsat");
		String other = pc.getSystemConfiguration("other");		
//		获取值班人、审核人、日期、备注数据位置
		List<Object[]> otherlList = mbzhrbrpdService.searchExportOther(txtDate, Others, other, "");
		//String basePostion = pc.getSystemConfiguration("MbzhrbrpdBaseInfo");
		if(otherlList !=null && otherlList.size()>0){
			String[] basePostion = pc.getSystemConfiguration( "MbzhrbrpdBaseInfo" ).split(",");
				for (int j = 0; j < basePostion.length; j++) {
					if (otherlList.get(0)[j] != null &&  !"".equals(otherlList.get(0)[j])){
						U2DataExportUtil.insertExcelData(wb, sheet, basePostion[j], 0, new Object[]{otherlList.get(0)[j]});
					}
			}
		}
		List<Object[]> sagdPivotalList = mbzhrbrpdService.searchExportData(txtDate, sagdPivotal, spt, "");
		String[] sptPostions = pc.getSystemConfiguration("sptPostions").split(",");
		String[] sptStartPositions = pc.getSystemConfiguration("sptStartPositions").split(",");
		String[] sptEndPositions = pc.getSystemConfiguration("sptEndPositions").split(",");
		String[][] sptPositions = new String[sptStartPositions.length][2];
		for (int i = 0; i < sptStartPositions.length; i++) {
			sptPositions[i][0] = sptStartPositions[i];
			sptPositions[i][1] = sptEndPositions[i];
		}
		Object[] sptObjDataList = U2DataExportUtil.splitDataByPosition(sagdPivotalList, sptPositions);
		//按分组 插入数据 
		U2DataExportUtil.insertDataByPosition(sptObjDataList, wb, sheet, sptPostions);	
		List<Object[]> sagdGeneralList = mbzhrbrpdService.searchExportDataDay(txtDate, sagdGeneral, sgt, "");		
		List<Object[]>  listo = new ArrayList<Object[]>();
		int arrIndexo = 0;
		Object[] oTempo;
		for (int i = 0; i < sagdGeneralList.size(); i++) {
			Object[] os = sagdGeneralList.get(i);
			if (i == 1) {
				for (int j = 0; j < os.length; j++) {
					listo.get(arrIndexo++)[1] = os[j];
					oTempo = new Object[2];
					oTempo[1] = os[j];
					//listo.add(oTempo);
				}
			}else{
				for (int j = 0; j < os.length; j++) {
					oTempo = new Object[2];
					oTempo[0] = os[j];
					listo.add(oTempo);
				}
			}
		}		
		String sgtPostions= pc.getSystemConfiguration("sgtPostions");
		U2DataExportUtil.insertDataByPosition(listo, wb, sheet, sgtPostions);
		//yestoday
		List<Object[]> sagdLiquidList = mbzhrbrpdService.searchExportDataTime(txtDate, sagdLiquid, slt, "");
		List<Object[]>  list = new ArrayList<Object[]>();
		Object[] obj = new Object[2];
		Object[] oTemp;
		int arrIndex = 0;
		//导出的数据，时间不能为空，如果为空导出的数据为位置可能是错误的
		for (int i = 0; i < sagdLiquidList.size(); i++) {
			Object[] os = sagdLiquidList.get(i);

			if (i >= 3) {
				for (int j = 0; j < os.length; j++) {
					list.get(arrIndex++)[1] = os[j];
					oTemp = new Object[2];
					oTemp[0] = os[j];
				}
			}else{
				for (int j = 0; j < os.length; j++) {
					oTemp = new Object[2];
					oTemp[0] = os[j];
					list.add(oTemp);
				}
			}
		}		
		String sltPostions = pc.getSystemConfiguration("sltPostions");//获取插入的位置
		U2DataExportUtil.insertDataByPosition(list, wb, sheet, "Y5");
		List<Object[]> sagdAutoList = mbzhrbrpdService.searchExportData(txtDate, sagdAuto, rpdsat, "");
		String[] rpdsatPostions = pc.getSystemConfiguration("rpdsatPostions").split(",");
		String[] rpdsatStartPositions = pc.getSystemConfiguration("rpdsatStartPositions").split(",");
		String[] rpdsatEndPositions = pc.getSystemConfiguration("rpdsatEndPositions").split(",");
		String[][] rpdsatPositions = new String[rpdsatStartPositions.length][2];
		for (int i = 0; i < rpdsatStartPositions.length; i++) {
			rpdsatPositions[i][0] = rpdsatStartPositions[i];
			rpdsatPositions[i][1] = rpdsatEndPositions[i];
		}
		Object[] rpdsatObjDataList = U2DataExportUtil.splitDataByPosition(sagdAutoList, rpdsatPositions);
		//按分组 插入数据 
		U2DataExportUtil.insertDataByPosition(rpdsatObjDataList, wb, sheet, rpdsatPostions);		
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
          System.out.println(bos.toString() + "==========================================");
          String htmlStr = bos.toString().split("css\">")[1];
          String[] styleBodyStr = htmlStr.split("</style>");
          System.out.println(styleBodyStr[0]);
          String tableStr = "<table" + styleBodyStr[1].split("<table")[1].split("</body>")[0];
          System.out.println("===========" + tableStr);
		}
		
		return "excel";
	}
}
