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


public class GrglzbAction {

	HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
	private LineMeasureService LineMeasureService;
	private ReportMessageService reportMessageService;
	private LogService logService;
	private InputStream excelFile = null;
	public String getFileName() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd ");
		String downloadFileName = (sf.format(new Date()).toString())+ "过热锅炉运行合格情况统计表（周报）.xls";
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
			butJson = AuthorityUtil.getButtonJson("MENU183", user);
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

	
	
	public String searchGrglzb()throws Exception{	
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
		
		PcRpdReportMessageT reportM = null;
		if(txtDate == null || "".equals(txtDate)){
			txtDate = DateBean.getSystemTime1();
		}		
		try {
			U2OILs = LineMeasureService.searchGrglzb(txtDate);			
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
		JSONObject tree = new JSONObject();
		tree.putAll(jsonobj);
		try {
			reportMessage = reportMessageService.SreachReportMessages("", "过热锅炉运行合格情况统计表（周报）", txtDate);
			if(reportMessage != null && reportMessage.size()>0){
				reportM = reportMessage.get(0);
				
			}else{
				reportM = new PcRpdReportMessageT();
				reportM.setRq(DateBean.getStrDate(txtDate));
				reportM.setBbmc("过热锅炉运行合格情况统计表（周报）");
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
				reportMessage = reportMessageService.SreachReportMessages("", "过热锅炉运行合格情况统计表（周报）", txtDate);
				reportM = reportMessage.get(0);
				if(updateflg){
					//添加系统LOG
					try {
						User user1 = (User) session.getAttribute("userInfo");
						PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "1", "过热锅炉运行合格情况统计表（周报）", reportM.getRpdReportMessageId());
						logService.addLog(log);
					} catch (Exception e) {
						e.printStackTrace();
						obj = CommonsUtil.getRrturnJson("","过热锅炉运行合格情况统计表（周报）日志添加失败" ,"", null, null);
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
		tree.putAll(jsonobj);
		obj = CommonsUtil.getRrturnJson("","" ,"", tree, null);
		out.print(obj);
	return null;
	}


		@SuppressWarnings("unused")
		public String exportGrglzb() throws Exception {
			HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
			String txtDate = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("txtDate")));			
			PropertiesConfig pc = new PropertiesConfig();
			List<PcRpdReportMessageT> reportMessage = reportMessageService.SreachReportMessages("", "过热锅炉运行合格情况统计表（周报）",txtDate);//值班人、审核人、日期、备注			
			String grglzpm = pc.getSystemConfiguration("grglzpm");			
			String templetFilePath = request.getSession().getServletContext().getRealPath("/") + "exceltemplet\\combination\\过热锅炉运行合格情况统计表（周报）.xls";
			HSSFWorkbook wb = ExcelToHtmlUtils.loadXls( new File( templetFilePath ) );
			HSSFSheet sheet = wb.getSheetAt(0);
			//取一周范围的值
			String stratime =DateBean.getBefore6DAYTime(txtDate);
			String endtime = txtDate;
			String fields = "select rownum,acquisition_time,gqdw,glzts,glyxts,hgglts,bhgglts,hgl,bhggllh from (select rownum,acquisition_time,gqdw,glzts,glyxts,hgglts,bhgglts,hgl,bhggllh from PC_RPD_BOILER_SUPERHEAT_YC_T where 1=1"
					 +" and acquisition_time between TO_DATE('"+stratime+"','YYYY-MM-DD') and TO_DATE('"+endtime+"','YYYY-MM-DD') order by acquisition_time)";	

			List<Object[]> grglzbList = LineMeasureService.searchGrglzbXY("", fields);

			if ( grglzbList == null || grglzbList.size() == 0 ) {
				java.io.ByteArrayOutputStream baos = U2DataExportUtil.ExporDataStream(wb);
				if(baos != null){
					byte[] ba = baos.toByteArray();
					excelFile = new ByteArrayInputStream(ba);
					baos.flush();
					baos.close();
				}
				return "excel";
			}
			//分段时间
			String[] timeStr = pc.getSystemConfiguration("time15").split("&");

			String[][] dates = new String[7][];
			for (int i = 0; i < dates.length; i++) {
				dates[i] = timeStr[i].split(",");
			}
//			U2DataExportUtil.setDates(txtDate, dates);
			//插入数据位置
			String[] GrglzbDataInsertPostions = pc.getSystemConfiguration("GrglzbDataInsertPostions").split(",");
			U2DataExportUtil.TemporalGroupingWeek(wb, sheet, grglzbList, dates, GrglzbDataInsertPostions, txtDate);
			wb.setForceFormulaRecalculation(true);
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
