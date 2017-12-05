package com.echo.action;

import java.io.ByteArrayInputStream;
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

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.hssf.converter.ExcelToHtmlUtils;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ConditionalFormattingRule;
import org.apache.poi.ss.usermodel.FontFormatting;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.SheetConditionalFormatting;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.struts2.ServletActionContext;

import com.echo.dto.PcRdLoginfoT;
import com.echo.dto.PcRpdReportMessageT;
import com.echo.dto.PcRpdU1WaterAutoT;
import com.echo.dto.PcRpdUThinWaterAutoT;
import com.echo.dto.User;
import com.echo.service.LogService;
import com.echo.service.ReportMessageService;
import com.echo.service.U1s1gyService;
import com.echo.service.U1s2gyService;
import com.echo.util.AuthorityUtil;
import com.echo.util.CommonsUtil;
import com.echo.util.DateBean;
import com.echo.util.ExcelUtil;
import com.echo.util.PropertiesConfig;
import com.echo.util.ReportFormsBaseUitl;
import com.echo.util.StringUtil;
import com.echo.util.U2DataExportUtil;
import com.opensymphony.xwork2.ActionSupport;

public class U1s1gyAction extends ReportFormsBaseUitl{
	/**
	 * // 水一区罐液位及流量报表
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
	private final String fileName = "一号稠油联合处理站水一区罐液位及流量报表.xls";

	public String getFileName() {
		return super.getFileName(fileName);
	}
	public String searchU1s1gy()throws Exception{
			HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
			HttpServletResponse response=ServletActionContext.getResponse();//响应对象
			response.setCharacterEncoding("utf-8");
			response.setHeader("ContentType","text/xml");
			PrintWriter out = response.getWriter();
			List<PcRpdReportMessageT> reportMessage = null;
			JSONArray U1S1S = null;
			JSONObject obj = null;
			JSONObject jsonobj = null;
			String txtDate = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("txtDate")));
			String[] reportMessages = new String[5];
			//获取当天所以二号稠油联合站原油系统日报数据
			
			PcRpdReportMessageT reportM = null;
			if(txtDate == null || "".equals(txtDate)){
				txtDate = DateBean.getSystemTime1();
			}
			
			try {
				U1S1S = u1s1gyService.searchU1s1gy(txtDate);
				if(U1S1S != null && U1S1S.size()>0){
					jsonobj = new JSONObject();
					jsonobj.put("U1S1S", U1S1S);
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
				reportMessage = reportMessageService.SreachReportMessages("", "一号稠油联合处理站水一区罐液位及流量报表", txtDate);
				if(reportMessage != null && reportMessage.size()>0){
					reportM = reportMessage.get(0);
					
				}else{
					reportM = new PcRpdReportMessageT();
					reportM.setRq(DateBean.getStrDate(txtDate));
					reportM.setBbmc("一号稠油联合处理站水一区罐液位及流量报表");
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
					reportMessage = reportMessageService.SreachReportMessages("", "一号稠油联合处理站水一区罐液位及流量报表", txtDate);
					reportM = reportMessage.get(0);
					if(updateflg){
						//添加系统LOG
						try {
							User user1 = (User) session.getAttribute("userInfo");
							PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "1", "水一区罐液位报表", reportM.getRpdReportMessageId());
							logService.addLog(log);
						} catch (Exception e) {
							e.printStackTrace();
							obj = CommonsUtil.getRrturnJson("","水一区罐液位日志添加失败" ,"", null, null);
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
//			request.setAttribute("U2OILs", U2OILs);
//			request.setAttribute("reportMessages", reportMessages);
			
		return null;
		
		
		

		}
		
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
				
				obj = CommonsUtil.getRrturnJson("","一号稠油联合处理站水一区罐液位及流量报表ID" ,"", null, null);
				out.print(obj);
				return null;
			}
			
			if(reportMessage != null && reportMessage.size()>0){
				reportM = reportMessage.get(0);
			}else{
				obj = CommonsUtil.getRrturnJson("","未获取到水一区罐液位及流量日报表ID对应数据" ,"", null, null);
				out.print(obj);
				return null;
			}
			reportM.setShr(user1.getOperName());
			reportM.setShsj(new Date());
			reportM.setBbmc("一号稠油联合处理站水一区罐液位及流量报表");

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
					PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "3", "水一区日报表", reportM.getRpdReportMessageId());
					logService.addLog(log);
				} catch (Exception e) {
					e.printStackTrace();
					obj = CommonsUtil.getRrturnJson("","水一区日志修改失败" ,"", null, null);
				}
			}
			out.print(obj);
			return null;
		}
		
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
			
			List<PcRpdReportMessageT> reportMessage = null;
			PcRpdReportMessageT reportM = null;
			if(RPDREPORTMESSAGEID != null && !"".equals(RPDREPORTMESSAGEID)){
				reportMessage = reportMessageService.SreachReportMessages(RPDREPORTMESSAGEID, "", "");
			}
			
			if(reportMessage != null && reportMessage.size()>0){
				reportM = reportMessage.get(0);
			}else{
				if(RQ != null && !"".equals(RQ)){
					
					reportMessage = reportMessageService.SreachReportMessages("", "一号稠油联合处理站水一区罐液位及流量报表", RQ);
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
//			reportM.setShr(SHR);
			reportM.setRq(DateBean.getStrDate(RQ));
			reportM.setBbmc("一号稠油联合处理站水一区罐液位及流量报表");
			reportM.setBz(BZ);
			
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String reportDate = df.format(new Date());
			String firstStr = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("firstStr")));
			firstStr = firstStr.substring(0,firstStr.length()-1);
			String[] firstList = firstStr.split(";",-1);
			for(int i=0;i<firstList.length;i++){
				String[] firstPra = firstList[i].split(",",-1);
					String id = firstPra[0];
					List<PcRpdU1WaterAutoT> usList = null;
					PcRpdU1WaterAutoT us = null;
					if(id != null && !"".equals(id)){
						try {
							usList = u1s1gyService.SreachU1S1gy(id, "");
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
						us = new PcRpdU1WaterAutoT();
					}
					String beforetime = DateBean.getBeforeDAYTime(RQ);
					int calcNum = u1s1gyService.searchCalcNum();
					int zeroIndex = -calcNum/2;
					if(i>=zeroIndex){
						us.setBbsj(sf.parse(RQ +" "+ firstPra[1]));
					}else{
						us.setBbsj(sf.parse(beforetime +" "+ firstPra[1]));
					}
					us.setRpdU1WaterAutoId(firstPra[0]);
					us.setFt1001(CommonsUtil.isNullOrEmpty(firstPra[2]));
					us.setS1hssllj(CommonsUtil.isNullOrEmpty(firstPra[3]));
					us.setFt1005(CommonsUtil.isNullOrEmpty(firstPra[4]));
					us.setFt1004(CommonsUtil.isNullOrEmpty(firstPra[5]));
					us.setFt1003(CommonsUtil.isNullOrEmpty(firstPra[6]));
					us.setFt1002(CommonsUtil.isNullOrEmpty(firstPra[7]));
					us.setFt1016(CommonsUtil.isNullOrEmpty(firstPra[8]));
					//us.setS1rgsdlj(CommonsUtil.isNullOrEmpty(firstPra[9]));
					//us.setFit801(CommonsUtil.isNullOrEmpty(firstPra[10]));
					us.setLt1001(CommonsUtil.isNullOrEmpty(firstPra[9]));
					us.setLt1002(CommonsUtil.isNullOrEmpty(firstPra[10]));
					us.setLt1007(CommonsUtil.isNullOrEmpty(firstPra[11]));
					us.setLt1012(CommonsUtil.isNullOrEmpty(firstPra[12]));
					us.setLt1011(CommonsUtil.isNullOrEmpty(firstPra[13]));
					

	         		boolean updateflg = false;
	        		try {
	        			updateflg = u1s1gyService.updateU1S1gy(us);
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
					obj = CommonsUtil.getRrturnJson("","水一区日志修改失败" ,"", null, null);
				}
			}
			out.print(obj);
			return null;
		
		}
		
		
		
		public String exportU1S1() throws Exception {
			HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
			String txtDate = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("txtDate")));
			if ("".equals(txtDate)) {
				txtDate = DateBean.getSystemTime1();
			}
			String templetFilePath = request.getSession().getServletContext().getRealPath("/") + "exceltemplet\\combination\\一号联合站\\一号稠油联合处理站水一区罐液位及流量报表.xls";
			HSSFWorkbook wb = ExcelToHtmlUtils.loadXls( new File( templetFilePath ) );						
			HSSFSheet sheet = wb.getSheetAt(0);

			PropertiesConfig pc = new PropertiesConfig();
			String U1WaterAuto = pc.getSystemConfiguration("sql");
			String U1WatInsertP = pc.getSystemConfiguration("U1insert");
			
			List<Object[]> list = u1s2gyService.searchExportData(txtDate, U1WaterAuto);
			if (list != null && list.size() > 0) {
				//调用插入数据样式的方法
				//List<Object[]> objDataList = U2DataExportUtil.splitSumData(list);
				
				U2DataExportUtil.insertDataByPosition(list, wb, sheet, U1WatInsertP);
			}
			String baseInfoSql = "select r.zbr1,r.shr,r.rq,r.bz from pc_rpd_report_message_t r where r.bbmc='一号稠油联合处理站水一区罐液位及流量报表' and r.rq=TO_DATE('" + txtDate + "','YYYY-MM-DD') ";
			list = u1s2gyService.searchExportData("", baseInfoSql);
			if (list != null && list.size() > 0) {
				// 获取单位、值班人、审核人、日期、备注数据插入位置
				String[] basePostion = pc.getSystemConfiguration( "U1ComInsert" ).split(",");
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
