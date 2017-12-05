package com.echo.action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

public class cyfx1Action {
	HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
	private LineMeasureService LineMeasureService;
	private ReportMessageService reportMessageService;
	private LogService logService;
	private InputStream excelFile = null;
	public String getFileName() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd ");
		String downloadFileName = (sf.format(new Date()).toString())+ "一号稠油处理站分线计量日报表.xls";
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
			butJson = AuthorityUtil.getButtonJson("MENU197", user);
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
	
	@SuppressWarnings("unused")
	public String searchFxjl1()throws Exception{	
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		List<PcRpdReportMessageT> reportMessage = null;
		JSONArray U2OILs = null;
		JSONObject obj = null;
		JSONObject jsonobj = null;
		List<String> date = null;
		JSONObject u2szjcs = null;
		PcRpdReportMessageT reportM = null;

		String txtYear = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("SYear")));
		String txtMonth = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("SMonth")));
		String txtDate =  DateBean.getSystemTime1();

		if(txtYear != null && !"".equals(txtYear) && txtMonth != null && !"".equals(txtMonth)){
			date = DateBean.getYeahMonth1(txtYear, txtMonth);
		}else{
			Calendar now = Calendar.getInstance();  
			date = DateBean.getYeahMonth(String.valueOf(now.get(Calendar.YEAR)), String.valueOf((now.get(Calendar.MONTH) + 1)));		
		}
		try {
			U2OILs = LineMeasureService.searchFxjl1(date,txtYear,txtMonth);			
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
			reportMessage = reportMessageService.SreachReportMessages("", "一号稠油处理站分线计量日报表", txtDate);
			if(reportMessage != null && reportMessage.size()>0){
				reportM = reportMessage.get(0);
			}else{
				reportM = new PcRpdReportMessageT();
				reportM.setRq(DateBean.getStrDate(txtDate));
				reportM.setBbmc("一号稠油处理站分线计量日报表");
		
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
				reportMessage = reportMessageService.SreachReportMessages("", "一号稠油处理站分线计量日报表", txtDate);
				reportM = reportMessage.get(0);
				if(updateflg){
					//添加系统LOG
					try {
						User user1 = (User) session.getAttribute("userInfo");
						PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "1", "一号稠油处理站分线计量日报表", reportM.getRpdReportMessageId());
						logService.addLog(log);
					} catch (Exception e) {
						e.printStackTrace();
						obj = CommonsUtil.getRrturnJson("","一号稠油处理站分线计量日报表日志添加失败" ,"", null, null);
					}
				}
			}
			obj = new JSONObject();
			obj.put("RPDREPORTMESSAGEID", reportM.getRpdReportMessageId());
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
		
		public String exportcyfx1() throws Exception {
			HttpServletRequest request=ServletActionContext.getRequest(); //请求对象		
			PropertiesConfig pc = new PropertiesConfig();	
			String templetFilePath = request.getSession().getServletContext().getRealPath("/") + "exceltemplet\\combination\\一号稠油处理站分线计量日报表.xls";
			HSSFWorkbook wb = ExcelToHtmlUtils.loadXls( new File( templetFilePath ) );
			HSSFSheet sheet = wb.getSheetAt(0);						
			String txtYear = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("SYear")));
			String txtMonth = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("SMonth")));
			List<String> date = null;
			if(txtYear != null && !"".equals(txtYear) && txtMonth != null && !"".equals(txtMonth)){
				date = DateBean.getYeahMonth(txtYear, txtMonth);
			}else{
				Calendar now = Calendar.getInstance();  
				date = DateBean.getYeahMonth(String.valueOf(now.get(Calendar.YEAR)), String.valueOf((now.get(Calendar.MONTH) + 1)));				
			}			

			String fields = "select rpd_cy1_line_messure_id,report_date,jhg_yel,jhg_youl,jhg_ds,tsb,wuy,"+
						"wuypk,fxccy,pank,ftq_102,ftq_102oq,ftq_102hs,ftq_105,ftq_105oq,ftq_105hs,ftq_107,ftq_107oq,ftq_107hs," +
						" ftq_c1y,ftq_c1,ftq_103,ftq_103oq,ftq_103hs,ftq_s01,zjfyl,ftq_s01oq,ftq_s01hs,"+
						"ftq_s1y,ftq_s1s,zyel,yel,youl,remark  from  PC_RPD_CY1_LINE_MESSURE_V a where 1=1";
			fields += " and a.report_date between '"+date.get(0)+"'and '"+date.get(1)+"' order by a.report_date";					
				List<Object[]> cyfx1List = LineMeasureService.searchcyfx1("", fields);
			if ( cyfx1List == null || cyfx1List.size() == 0 ) {
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
			String[] timeStr = pc.getSystemConfiguration("time17").split("&");

			String[][] dates = new String[3][];
			for (int i = 0; i < dates.length; i++) {
				dates[i] = timeStr[i].split(",");
			}
			String[] cyfx1DataInsertPostions = pc.getSystemConfiguration("cyfx1DataInsertPostions").split(",");			
			U2DataExportUtil.TemporalGroupingMonth(wb, sheet, cyfx1List, dates, cyfx1DataInsertPostions, txtYear, txtMonth);
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
