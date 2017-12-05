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

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import com.echo.dto.PcRdLoginfoT;
import com.echo.dto.PcRpdReportMessageT;
import com.echo.dto.PcRpdU1OilAutoT;
import com.echo.dto.User;
import com.echo.service.LogService;
import com.echo.service.ReportMessageService;
import com.echo.service.U1FXJLService;
import com.echo.util.AuthorityUtil;
import com.echo.util.CommonsUtil;
import com.echo.util.DateBean;
import com.echo.util.PropertiesConfig;
import com.echo.util.ReportFormsBaseUitl;
import com.echo.util.StringUtil;
import com.echo.util.U2DataExportUtil;
public class U1FXJLAction extends ReportFormsBaseUitl {
	private U1FXJLService u1fxjlService;
	private final String fileName ="一号稠油联合处理站分线计量流量日报表.xls";
	
	private LogService logService;

	private ReportMessageService reportMessageService;

	public void setU1fxjlService(U1FXJLService u1fxjlService) {
		this.u1fxjlService = u1fxjlService;
	}

	public void setReportMessageService(ReportMessageService reportMessageService) {
		this.reportMessageService = reportMessageService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	
	public String getFileName() {
		return super.getFileName(fileName);
	}

	HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
	@SuppressWarnings("unchecked")
	public String searchU1FXJL() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String reportDate = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("reportDate")));
		String totalNum = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("totalNum")));
		List<PcRpdReportMessageT> reportMessage = null;
		PcRpdReportMessageT reportM = null;
		JSONObject jsonobj = null;
		JSONObject obj = null;
		JSONArray u1fxjlArr = null;
		
		if(reportDate == null || "".equals(reportDate)){
			reportDate = DateBean.getSystemTime1();
		}
		try {
			u1fxjlArr = u1fxjlService.searchU1FXJL(reportDate,totalNum);
			
			if(u1fxjlArr != null && u1fxjlArr.size()>0){
				jsonobj = new JSONObject();
				jsonobj.put("U1FXJL", u1fxjlArr);
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
			reportMessage = reportMessageService.SreachReportMessages("", "一号稠油联合处理站分线计量流量日报表", reportDate);
			if(reportMessage != null && reportMessage.size()>0){
				reportM = reportMessage.get(0);
				
			}else{
				reportM = new PcRpdReportMessageT();
				reportM.setRq(DateBean.getStrDate(reportDate));
				reportM.setBbmc("一号稠油联合处理站分线计量流量日报表");
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
				reportMessage = reportMessageService.SreachReportMessages("", "一号稠油联合处理站分线计量流量日报表", reportDate);
				reportM = reportMessage.get(0);
				if(updateflg){
					//添加系统LOG
					try {
						User user1 = (User) session.getAttribute("userInfo");
						PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "1", "分线计量流量日报表", reportM.getRpdReportMessageId());
						logService.addLog(log);
					} catch (Exception e) {
						e.printStackTrace();
						obj = CommonsUtil.getRrturnJson("","一号稠油联合处理站分线计量流量日报表日志添加失败" ,"", null, null);
					}
				}
			}
			
			obj = new JSONObject();
			obj.put("RPDREPORTMESSAGEID", reportM.getRpdReportMessageId());
			obj.put("ZBR1", reportM.getZbr1());
			obj.put("SHR", reportM.getShr());
			obj.put("RQ", reportDate);
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
		return null;
	}
	
	public String updateU1FXJL() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONObject obj = new JSONObject();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String RPDREPORTMESSAGEID = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("RPDREPORTMESSAGEID")));
		String SHR = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("SHR")));
		String ZBR1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("ZBR1")));
		String BZ = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("BZ")));
		String RQ = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("RQ")));
		String firstStr = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("firstStr")));
		boolean updateflg = false;
		firstStr = firstStr.substring(0,firstStr.length()-1);
		String[] firstList = firstStr.split(";",-1);
		for(int i=0;i<firstList.length;i++){
			String[] firstPra = firstList[i].split(",",-1);
			String RPD_U1_OIL_AUTO_ID = firstPra[0];
			List<PcRpdU1OilAutoT> oilAuto = null;
			PcRpdU1OilAutoT oil = null;
			if(RPD_U1_OIL_AUTO_ID != null && !"".equals(RPD_U1_OIL_AUTO_ID)){
				oilAuto = u1fxjlService.SreachOILAuto(RPD_U1_OIL_AUTO_ID, "");
			}
			
			if(oilAuto != null && oilAuto.size()>0){
				oil = oilAuto.get(0);
			}else{
				oil = new PcRpdU1OilAutoT();
			}
			
			String beforetime = DateBean.getBeforeDAYTime(RQ);
			int calcNum = u1fxjlService.searchCalcNum();
			int zeroIndex = -calcNum/2;
			if(i>=zeroIndex){
				
				oil.setBbsj(sf.parse(RQ +" "+ firstPra[1]));
			}else{
				oil.setBbsj(sf.parse(beforetime +" "+ firstPra[1]));
			}
			oil.setRpdU1OilAutoId(firstPra[0]);
			oil.setTe002(CommonsUtil.isNullOrEmpty(firstPra[2]));
			oil.setPt002(CommonsUtil.isNullOrEmpty(firstPra[3]));
			
			oil.setAit002(CommonsUtil.isNullOrEmpty(firstPra[4]));
			oil.setX2z32bxlj(CommonsUtil.isNullOrEmpty(firstPra[5]));
			oil.setFTQ_102OQ(CommonsUtil.isNullOrEmpty(firstPra[6]));
			
			oil.setTe005(CommonsUtil.isNullOrEmpty(firstPra[7]));
			oil.setPt005(CommonsUtil.isNullOrEmpty(firstPra[8]));
			
			oil.setAit005(CommonsUtil.isNullOrEmpty(firstPra[9]));
			oil.setX51311lj(CommonsUtil.isNullOrEmpty(firstPra[10]));
			oil.setFTQ_105OQ(CommonsUtil.isNullOrEmpty(firstPra[11]));
			
			oil.setTe007(CommonsUtil.isNullOrEmpty(firstPra[12]));
			oil.setPt007(CommonsUtil.isNullOrEmpty(firstPra[13]));
			
			oil.setAit007(CommonsUtil.isNullOrEmpty(firstPra[14]));
			oil.setX7z32nxlj(CommonsUtil.isNullOrEmpty(firstPra[15]));
			oil.setFTQ_107OQ(CommonsUtil.isNullOrEmpty(firstPra[16]));
			
			oil.setTe008(CommonsUtil.isNullOrEmpty(firstPra[17]));
			oil.setPt008(CommonsUtil.isNullOrEmpty(firstPra[18]));
			
			oil.setAit008(CommonsUtil.isNullOrEmpty(firstPra[19]));
			oil.setX8xytxlj(CommonsUtil.isNullOrEmpty(firstPra[20]));
			oil.setFTQ_108OQ(CommonsUtil.isNullOrEmpty(firstPra[21]));
			
			oil.setPt004(CommonsUtil.isNullOrEmpty(firstPra[22]));
			oil.setGhjxtcclj(CommonsUtil.isNullOrEmpty(firstPra[23]));
			oil.setTsbccyl(CommonsUtil.isNullOrEmpty(firstPra[24]));
			oil.setTsbcclj(CommonsUtil.isNullOrEmpty(firstPra[25]));
			oil.setWyccyl(CommonsUtil.isNullOrEmpty(firstPra[26]));
			oil.setWycclj(CommonsUtil.isNullOrEmpty(firstPra[27]));
			oil.setTe2hz(CommonsUtil.isNullOrEmpty(firstPra[28]));
			oil.setPt2hz(CommonsUtil.isNullOrEmpty(firstPra[29]));
			oil.setQ2zlj(CommonsUtil.isNullOrEmpty(firstPra[30]));
			oil.setTe201(CommonsUtil.isNullOrEmpty(firstPra[31]));
			oil.setPt201(CommonsUtil.isNullOrEmpty(firstPra[32]));
			oil.setGhzlj(CommonsUtil.isNullOrEmpty(firstPra[33]));
			oil.setBdcjcyglj(CommonsUtil.isNullOrEmpty(firstPra[34]));
     		
    		try {
    			updateflg = u1fxjlService.updateOILAuto(oil);
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
		
		List<PcRpdReportMessageT> reportMessage = null;
		PcRpdReportMessageT reportM = null;
		if(RPDREPORTMESSAGEID != null && !"".equals(RPDREPORTMESSAGEID)){
			reportMessage = reportMessageService.SreachReportMessages(RPDREPORTMESSAGEID, "", "");
		}
		
		if(reportMessage != null && reportMessage.size()>0){
			reportM = reportMessage.get(0);
		}else{
			if(RQ != null && !"".equals(RQ)){
				
				reportMessage = reportMessageService.SreachReportMessages("", "一号稠油联合处理站分线计量流量日报表", RQ);
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
		reportM.setBz(BZ);
		reportM.setRq(DateBean.getStrDate(RQ));
		reportM.setBbmc("一号稠油联合处理站分线计量流量日报表");
		
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
				PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "3", "分线计量流量日报表", reportM.getRpdReportMessageId());
				logService.addLog(log);
			} catch (Exception e) {
				e.printStackTrace();
				obj = CommonsUtil.getRrturnJson("","一号稠油联合处理站分线计量流量日报表日志修改失败" ,"", null, null);
			}
		}
		out.print(obj);
		return null;
	}
	public HSSFWorkbook getWb(String date) throws Exception{
		String txtDate = getParameters(date);
		if ("".equals(txtDate)) {
			txtDate = DateBean.getSystemTime1();
		}
		HSSFWorkbook wb = getWorkBook("exceltemplet\\combination\\一号联合站\\一号稠油联合处理站分线计量流量日报表.xls");
		HSSFSheet sheet = wb.getSheetAt(0);

		PropertiesConfig pc = new PropertiesConfig();
		String U1WaterAuto = pc.getSystemConfiguration("PC_RPD_U1_OIL_AUTO_T_FXJL");
		String U1WatInsertP = pc.getSystemConfiguration("U1FXJLinsert");
		
		List<Object[]> list = u1fxjlService.searchU2_WATER(txtDate, U1WaterAuto);
		if (list != null && list.size() > 0) {
			//调用插入数据样式的方法
			U2DataExportUtil.insertDataByPosition(list, wb, sheet, U1WatInsertP);
		}
		String baseInfoSql = "select r.zbr1,r.shr,r.rq,r.bz from pc_rpd_report_message_t r where r.bbmc='一号稠油联合处理站分线计量流量日报表' and r.rq=TO_DATE('" + txtDate + "','YYYY-MM-DD') ";
		list = u1fxjlService.searchU2_WATER("", baseInfoSql);
		if (list != null && list.size() > 0) {
			// 获取单位、值班人、审核人、日期、备注数据插入位置
			String[] basePostion = pc.getSystemConfiguration( "U1FXJLComInsert" ).split(",");
			for (int j = 0; j < basePostion.length; j++) {
				if (list.get(0)[j] != null) {
					U2DataExportUtil.insertExcelData(wb, sheet, basePostion[j], 0, new Object[]{list.get(0)[j]});
				}
			}
		}
		return wb;
	}
	public String exportU2_WATER() throws Exception {
		HSSFWorkbook wb = getWb("reportDate");

		wb.setForceFormulaRecalculation(true);
		OutputStreamAndCloseBaos(U2DataExportUtil.ExporDataStream(wb));
		return "excel";
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
			butJson = AuthorityUtil.getButtonJson("MENU112", user);
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
			
			obj = CommonsUtil.getRrturnJson("","未获取到一号稠油联合处理站分线计量流量日报表数据ID" ,"", null, null);
			out.print(obj);
			return null;
		}
		
		if(reportMessage != null && reportMessage.size()>0){
			reportM = reportMessage.get(0);
		}else{
			obj = CommonsUtil.getRrturnJson("","未获取到一号稠油联合处理站分线计量流量日报表ID对应数据" ,"", null, null);
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
				PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "3", "分线计量流量日报表", reportM.getRpdReportMessageId());
				logService.addLog(log);
			} catch (Exception e) {
				e.printStackTrace();
				obj = CommonsUtil.getRrturnJson("","一号稠油联合处理站分线计量流量日报表日志修改失败" ,"", null, null);
			}
		}
		out.print(obj);
		return null;
	}
	
	public String searchFlagDate() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String reportDate = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("reportDate")));
		String waterList = u1fxjlService.searchFlagDate(reportDate);
		if (!"".endsWith(waterList)) {
			out.print(waterList);
		}
		else {
			out.print("-1");
		}
		return null;
	}
}
