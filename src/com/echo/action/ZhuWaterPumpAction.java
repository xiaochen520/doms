package com.echo.action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
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

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.hssf.converter.ExcelToHtmlUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import com.echo.dto.PcRdLoginfoT;
import com.echo.dto.PcRpdReportMessageT;
import com.echo.dto.PcRpdUThinWaterAutoT;
import com.echo.dto.User;
import com.echo.service.LogService;
import com.echo.service.ReportMessageService;
import com.echo.service.ZhuWaterPumpService;
import com.echo.util.AuthorityUtil;
import com.echo.util.CommonsUtil;
import com.echo.util.DateBean;
import com.echo.util.PropertiesConfig;
import com.echo.util.StringUtil;
import com.echo.util.U2DataExportUtil;
import com.opensymphony.xwork2.ActionSupport;

public class ZhuWaterPumpAction extends ActionSupport{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HttpSession session = ServletActionContext.getRequest().getSession();
	private InputStream excelFile = null;
	private ReportMessageService reportMessageService;
	private ZhuWaterPumpService zhuWaterPumpService;
	private LogService logService;
	
	public void setZhuWaterPumpService(ZhuWaterPumpService zhuWaterPumpService) {
		this.zhuWaterPumpService = zhuWaterPumpService;
	}
	public InputStream getExcelFile() {
		return excelFile;
	}
	public String getFileName() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd ");
		String downloadFileName = (sf.format(new Date()).toString())+ "污水处理岗注水泵房运行原始日报.xls";
		try {
			downloadFileName = new String(downloadFileName.getBytes(),"ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return downloadFileName;
	}
	public void setReportMessageService(ReportMessageService reportMessageService) {
		this.reportMessageService = reportMessageService;
	}
	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	public String searchZhuPump() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter  out = response.getWriter();
		List<PcRpdReportMessageT>  reportMessage = null;
		JSONArray pump = null;
		JSONObject obj = null;
		JSONObject jsonobj = new JSONObject();
		PcRpdReportMessageT reportM = null;
		
		String txtDate = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("txtDate")));
		try {
			pump = zhuWaterPumpService.searchZhuPump(txtDate);
			if(pump !=null && pump.size()>0){
				jsonobj = new JSONObject();
				jsonobj.put("pump", pump.get(0));
				jsonobj.put("pump1", pump.get(1));
			}
			
//			if(id!=null && id.equals("")){
//			pump = zhuWaterPumpService.searchZhuPump(id);
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			reportMessage = reportMessageService.SreachReportMessages("", "注输联合站注水泵房日报", txtDate);
			if(reportMessage != null && reportMessage.size()>0){
				reportM = reportMessage.get(0);
				
			}else{
				reportM = new PcRpdReportMessageT();
				reportM.setRq(DateBean.getStrDate(txtDate));
				reportM.setBbmc("注输联合站注水泵房日报");
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
				reportMessage = reportMessageService.SreachReportMessages("", "注输联合站注水泵房日报", txtDate);
				reportM = reportMessage.get(0);
				if(updateflg){
					//添加系统LOG
					try {
						User user1 = (User) session.getAttribute("userInfo");
						PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "1", "注输联合站注水泵房日报", reportM.getRpdReportMessageId());
						logService.addLog(log);
					} catch (Exception e) {
						e.printStackTrace();
						obj = CommonsUtil.getRrturnJson("","注输联合站注水泵房日志添加失败" ,"", null, null);
					}
				}
				
				
				
			}
			
			
			obj = new JSONObject();
			obj.put("RPDREPORTMESSAGEID", reportM.getRpdReportMessageId());
			obj.put("ZBR1", reportM.getZbr1());
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
		return  null;
	}
	
	public String pageInit()throws IOException{
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
			butJson = AuthorityUtil.getButtonJson("MENU093", user);
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
			
			obj = CommonsUtil.getRrturnJson("","未获取到注输联合站注水泵房日报表ID" ,"", null, null);
			out.print(obj);
			return null;
		}
		
		if(reportMessage != null && reportMessage.size()>0){
			reportM = reportMessage.get(0);
		}else{
			obj = CommonsUtil.getRrturnJson("","未获取到注输联合站注水泵房日报表ID对应数据" ,"", null, null);
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
				PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "3", "注水泵房", reportM.getRpdReportMessageId());
				logService.addLog(log);
			} catch (Exception e) {
				e.printStackTrace();
				obj = CommonsUtil.getRrturnJson("","注输联合站注水泵房日志修改失败" ,"", null, null);
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
		//data: {"RPDREPORTMESSAGEID":RPDREPORTMESSAGEID,"SHR":SHR,"ZBR1":ZBR1,"BZ":BZ,"RQ":RQ}
		String RPDREPORTMESSAGEID = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("RPDREPORTMESSAGEID")));
//		String SHR = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("SHR")));
		String ZBR1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("ZBR1")));
		String BZ = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("BZ")));
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
				
				reportMessage = reportMessageService.SreachReportMessages("", "注输联合站注水泵房日报", RQ);
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
//		reportM.setShr(SHR);
		reportM.setRq(DateBean.getStrDate(RQ));
		reportM.setBbmc("注输联合站注水泵房日报");
		reportM.setBz(BZ);
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String reportDate = df.format(new Date());
		String firstStr = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("firstStr")));
		firstStr = firstStr.substring(0,firstStr.length()-1);
		String[] firstList = firstStr.split(";",-1);
		int zeroIndex = 16;
		String beforetime = DateBean.getBeforeDAYTime(RQ);
		for(int i=0;i<firstList.length;i++){
			String[] firstPra = firstList[i].split(",",-1);
				String id = firstPra[0];
				List<PcRpdUThinWaterAutoT> waterAuto = null;
				PcRpdUThinWaterAutoT waterA = null;
				if(id != null && !"".equals(id)){
					try {
						waterAuto = zhuWaterPumpService.SreachPumpAuto(id, "");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				if(waterAuto != null && waterAuto.size()>0){
					waterA = waterAuto.get(0);
				}else{
//					obj = CommonsUtil.getRrturnJson("","报表ID获取失败，没有此时的您要修改的数据，请选择其他时间" ,"", null, null);
//					out.print(obj);
//					return null;
					waterA = new PcRpdUThinWaterAutoT();
				}				
				if(i>=zeroIndex){
					
					waterA.setBbsj(sf.parse(RQ +" "+ firstPra[1]));
				}else{
					waterA.setBbsj(sf.parse(beforetime +" "+ firstPra[1]));
				}
			//	if(waterAuto != null && waterAuto.size()>0){
					waterA.setRpdUThinWaterAutoId(firstPra[0]);
					waterA.setZSBF_ZDY(CommonsUtil.isNullOrEmpty(firstPra[2]));
					waterA.setPt_101a(CommonsUtil.isNullOrEmpty(firstPra[3]));
					waterA.setPt_101b(CommonsUtil.isNullOrEmpty(firstPra[4]));
					waterA.setTT_101(CommonsUtil.isNullOrEmpty(firstPra[5]));
					waterA.setZsb1dl(CommonsUtil.isNullOrEmpty(firstPra[6]));
					waterA.setPt_102a(CommonsUtil.isNullOrEmpty(firstPra[7]));
					waterA.setPt_102b(CommonsUtil.isNullOrEmpty(firstPra[8]));
					waterA.setTT_102(CommonsUtil.isNullOrEmpty(firstPra[9]));
					waterA.setZsb2dl(CommonsUtil.isNullOrEmpty(firstPra[10]));
					waterA.setSc12_zsb_alm_sv(CommonsUtil.isNullOrEmpty(firstPra[11]));
					waterA.setPt_103a(CommonsUtil.isNullOrEmpty(firstPra[12]));
					waterA.setPt_103b(CommonsUtil.isNullOrEmpty(firstPra[13]));
					waterA.setTT_103(CommonsUtil.isNullOrEmpty(firstPra[14]));
					waterA.setZsb3dl(CommonsUtil.isNullOrEmpty(firstPra[15]));
					waterA.setPt_104a(CommonsUtil.isNullOrEmpty(firstPra[16]));
					waterA.setPt_104b(CommonsUtil.isNullOrEmpty(firstPra[17]));
					waterA.setTT_104(CommonsUtil.isNullOrEmpty(firstPra[18]));
					waterA.setZsb4dl(CommonsUtil.isNullOrEmpty(firstPra[19]));
					waterA.setSc34_zsb_alm_sv(CommonsUtil.isNullOrEmpty(firstPra[20]));
					waterA.setFT_WSB(CommonsUtil.isNullOrEmpty(firstPra[21]));
					waterA.setWsbxfwlj(CommonsUtil.isNullOrEmpty(firstPra[22]));
					waterA.setWsb1pl(CommonsUtil.isNullOrEmpty(firstPra[23]));
					waterA.setLi_1_jhg(CommonsUtil.isNullOrEmpty(firstPra[24]));
					waterA.setLi_2_jhg(CommonsUtil.isNullOrEmpty(firstPra[25]));
					waterA.setPT_WSB(CommonsUtil.isNullOrEmpty(firstPra[26]));
					waterA.setPT_DX_ZSB(CommonsUtil.isNullOrEmpty(firstPra[27]));
					waterA.setFT_DX(CommonsUtil.isNullOrEmpty(firstPra[28]));
					waterA.setFt_dx_add(CommonsUtil.isNullOrEmpty(firstPra[29]));
					waterA.setPT_D2X(CommonsUtil.isNullOrEmpty(firstPra[30]));
					waterA.setPT_XX_ZSB(CommonsUtil.isNullOrEmpty(firstPra[31]));
					waterA.setFT_XX(CommonsUtil.isNullOrEmpty(firstPra[32]));
					waterA.setFt_xx_add(CommonsUtil.isNullOrEmpty(firstPra[33]));
					waterA.setZSBF_XXXYL(CommonsUtil.isNullOrEmpty(firstPra[34]));
					waterA.setZSBF_XXXSSLL(CommonsUtil.isNullOrEmpty(firstPra[35]));
					waterA.setZSBF_XXXLLLJ(CommonsUtil.isNullOrEmpty(firstPra[36]));
					
				//}

         		boolean updateflg = false;
        		try {
        			updateflg = zhuWaterPumpService.updatePumpAuto(waterA);
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
				PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "3", "注水泵房", reportM.getRpdReportMessageId());
				logService.addLog(log);
			} catch (Exception e) {
				e.printStackTrace();
				obj = CommonsUtil.getRrturnJson("","注输联合站注水泵房日志修改失败" ,"", null, null);
			}
		}
		out.print(obj);
		return null;
	}

	public String exportU2_OIL() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		PropertiesConfig pc = new PropertiesConfig();
		String txtDate = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("txtDate")));
		if ("".equals(txtDate)) {
			txtDate = DateBean.getSystemTime1();
		}
		String fields = pc.getSystemConfiguration("TRIGGERED_PROCESSFields");
		String templetFilePath = request.getSession().getServletContext().getRealPath("/") + "exceltemplet\\combination\\污水处理岗注水泵房运行原始日报表.xls";
		HSSFWorkbook wb = ExcelToHtmlUtils.loadXls( new File( templetFilePath ) );
		HSSFSheet sheet = wb.getSheetAt(0);
		List<Object[]> zhuWaterList = zhuWaterPumpService.searchWSCLOther(txtDate, fields);
		if ( zhuWaterList != null && zhuWaterList.size() != 0 ) {
			String insertPostion = pc.getSystemConfiguration("TRIGGERED_PROCESSnsertPostion");
			U2DataExportUtil.insertDataByPosition(zhuWaterList, wb, sheet, insertPostion);
		}
		// 获取单位、值班人、审核人、日期、备注数据插入位置
		List<PcRpdReportMessageT> reportMessage = reportMessageService.SreachReportMessages("", "注输联合站注水泵房日报", txtDate);
		String[] basePostion = pc.getSystemConfiguration( "TRIGGERED_PROCESSBaseInfo" ).split(",");
		if(reportMessage.size()>0){
			if (!"".equals(StringUtil.isNullStr(reportMessage.get(0).getZbr1()))) {
				U2DataExportUtil.insertExcelData(wb, sheet, basePostion[0], 0, new Object[]{reportMessage.get(0).getZbr1()});
			}
			if (!"".equals(StringUtil.isNullStr(reportMessage.get(0).getShr()))) {
				U2DataExportUtil.insertExcelData(wb, sheet, basePostion[1], 0, new Object[]{reportMessage.get(0).getShr()});
			}
			Date rq = reportMessage.get(0).getRq();
			if (!"".equals(StringUtil.isNullStr(rq.toString()))) {
				U2DataExportUtil.insertExcelData(wb, sheet, basePostion[2], 0, new Object[]{ DateBean.getChinaDate(rq.toString()) });
			}			
			if (!"".equals(StringUtil.isNullStr(reportMessage.get(0).getBz()))) {
				U2DataExportUtil.insertExcelData(wb, sheet, basePostion[3], 0, new Object[]{reportMessage.get(0).getBz()});
			}
//			U2DataExportUtil.insertExcelData(wb, sheet, basePostion[4], 0, new Object[]{reportMessage.get(0).getTbr()});
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
