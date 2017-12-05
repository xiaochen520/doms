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
import com.echo.dto.PcRpdXzyzhg;
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

public class cysc1Action {

	HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
	private LineMeasureService LineMeasureService;
	private ReportMessageService reportMessageService;
	private LogService logService;
	private InputStream excelFile = null;
	public String getFileName() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd ");
		String downloadFileName = (sf.format(new Date()).toString())+ "一号稠油联合处理站日生产数据.xls";
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
			butJson = AuthorityUtil.getButtonJson("MENU200", user);
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
	public String searchCysc()throws Exception{	
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONArray U2OILs = null;
		JSONObject obj = null;
		JSONObject jsonobj = null;
		String date = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("txtDate")));
		//获取1号稠油生产数据		
		PcRpdReportMessageT reportM = null;	
		if(date == null || "".equals(date)){	
			date = DateBean.getSystemTime1();
		}
		try {
			U2OILs = LineMeasureService.searchCysc(date);			
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
		obj = CommonsUtil.getRrturnJson("","" ,"", jsonobj, null);
		out.print(obj);		
	return null;
	}
		
		public String exportcyfx1() throws Exception {
			HttpServletRequest request=ServletActionContext.getRequest(); //请求对象		
			PropertiesConfig pc = new PropertiesConfig();	
			String templetFilePath = request.getSession().getServletContext().getRealPath("/") + "exceltemplet\\combination\\一号稠油联合处理站日生产数据.xls";
			HSSFWorkbook wb = ExcelToHtmlUtils.loadXls( new File( templetFilePath ) );
			HSSFSheet sheet = wb.getSheetAt(0);						
			String txtYear = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("SYear")));
			String txtMonth = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("SMonth")));
			List<String> date = null;
			if(txtYear != null && !"".equals(txtYear) && txtMonth != null && !"".equals(txtMonth)){
				date = DateBean.getYeahMonth(txtYear, txtMonth);
//				System.out.println("1111111111111111111111"+date);
			}else{
				Calendar now = Calendar.getInstance();  
				date = DateBean.getYeahMonth(String.valueOf(now.get(Calendar.YEAR)), String.valueOf((now.get(Calendar.MONTH) + 1)));			
				
			}
			
			String fields = "select rownum,to_char(a.report_date,'YYYY-MM-DD')as report_date,jhg_yel,jhg_youl,jhg_ds,tsb,wuy,"+
					"wuypk,fxccy,pank,ftq_105,ftq_105oq,ftq_105hs,ftq_107,ftq_107oq,ftq_107hs,ftq_102,ftq_102oq," +
					"ftq_102hs,ftq_c1,ftq_c1y,ftq_103,ftq_103oq,ftq_103hs,ftq_s2,ftq_s2jy,"+
					"ftq_s2od,ftq_s2hs,ftq_s1o,ftq_s1s,ftq_s1y,ftq_cy1zy,ftq_cy1zo,remark  from  PC_RPD_CY1_LINE_MESSURE_T a where 1=1";
				fields += " and a.report_date between TO_DATE('"+date.get(0)+"','YYYY-MM-DD') and TO_DATE('"+date.get(1)+"','YYYY-MM-DD') order by a.report_date";			
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

		//修改
		public String update() throws Exception {
			HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
			HttpServletResponse response=ServletActionContext.getResponse();//响应对象
			response.setCharacterEncoding("utf-8");
			response.setHeader("ContentType","text/xml");
			PrintWriter out = response.getWriter();
			JSONObject obj = new JSONObject();	
			String firstStr = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("firstStr")));
			
			
			firstStr = firstStr.substring(0,firstStr.length()-1);
			String[] firstList = firstStr.split(";",-1);
			for(int i=0;i<firstList.length;i++){
				String[] firstPra = firstList[i].split(",",-1);
					String id = firstPra[0];
					List<PcRpdXzyzhg> usList = null;
					PcRpdXzyzhg us = null;
					if(id != null && !"".equals(id)){
						try {
							usList = LineMeasureService.SreachXzyzhg(id, "");
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					if(usList != null && usList.size()>0){
						us = usList.get(0);						
					}else{
						us = new PcRpdXzyzhg();
					}					
//					if(firstPra[1].equals("01")){
//						beforetime = RQ;
//					}					
//					us.setReportsj(sf.parse(beforetime+" "+ firstPra[1]));
					us.setCyqjkyl(CommonsUtil.isNullOrEmpty(firstPra[2]));
					us.setCyqckyl(CommonsUtil.isNullOrEmpty(firstPra[3]));
					us.setFlq1jkyl(CommonsUtil.isNullOrEmpty(firstPra[4]));
					us.setFlq1ckyl(CommonsUtil.isNullOrEmpty(firstPra[5]));
					us.setFlq1yw(CommonsUtil.isNullOrEmpty(firstPra[6]));
					us.setFlq2jkyl(CommonsUtil.isNullOrEmpty(firstPra[7]));
					us.setFlq2ckyl(CommonsUtil.isNullOrEmpty(firstPra[8]));
					us.setFlq2yw(CommonsUtil.isNullOrEmpty(firstPra[9]));
					us.setWsb1jkyl(CommonsUtil.isNullOrEmpty(firstPra[10]));
					us.setWsb1ckyl(CommonsUtil.isNullOrEmpty(firstPra[11]));
					us.setWsb2jkyl(CommonsUtil.isNullOrEmpty(firstPra[12]));
					us.setWsb2ckyl(CommonsUtil.isNullOrEmpty(firstPra[13]));
					us.setWsbbppl(CommonsUtil.isNullOrEmpty(firstPra[14]));
					us.setXbl1jkyl(CommonsUtil.isNullOrEmpty(firstPra[15]));
					us.setXbl1ckyl(CommonsUtil.isNullOrEmpty(firstPra[16]));
					us.setXbl1jkwd(CommonsUtil.isNullOrEmpty(firstPra[17]));
					us.setXbl1ckwd(CommonsUtil.isNullOrEmpty(firstPra[18]));
					us.setXbl2jkyl(CommonsUtil.isNullOrEmpty(firstPra[19]));
					us.setXbl2ckyl(CommonsUtil.isNullOrEmpty(firstPra[20]));
					us.setXbl2jkwd(CommonsUtil.isNullOrEmpty(firstPra[21]));
					us.setXbl2ckwd(CommonsUtil.isNullOrEmpty(firstPra[22]));
					us.setSggyw(CommonsUtil.isNullOrEmpty(firstPra[23]));
					us.setHsyhs(CommonsUtil.isNullOrEmpty(firstPra[24]));
					us.setTrqyl(CommonsUtil.isNullOrEmpty(firstPra[25]));
					us.setBsqqbyl(CommonsUtil.isNullOrEmpty(firstPra[26]));
					us.setBsqqbll(CommonsUtil.isNullOrEmpty(firstPra[27]));
					us.setBsqqbwd(CommonsUtil.isNullOrEmpty(firstPra[28]));
					us.setBsqqblllj(CommonsUtil.isNullOrEmpty(firstPra[29]));
					us.setGqqbfqyl(CommonsUtil.isNullOrEmpty(firstPra[30]));
					us.setGqqbfhyl(CommonsUtil.isNullOrEmpty(firstPra[31]));					
					us.setGqqbwd(CommonsUtil.isNullOrEmpty(firstPra[32]));
					us.setGqqbll(CommonsUtil.isNullOrEmpty(firstPra[33]));
					us.setGqqblllj(CommonsUtil.isNullOrEmpty(firstPra[34]));
					us.setQjqll(CommonsUtil.isNullOrEmpty(firstPra[35]));
					us.setQjqlllj(CommonsUtil.isNullOrEmpty(firstPra[36]));	         		
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
	        			//return null;
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
				
				obj = CommonsUtil.getRrturnJson("","夏转油综合岗日报ID" ,"", null, null);
				out.print(obj);
				return null;
			}
			
			if(reportMessage != null && reportMessage.size()>0){
				reportM = reportMessage.get(0);
			}else{
				obj = CommonsUtil.getRrturnJson("","未获取到夏转油综合岗日报ID对应数据" ,"", null, null);
				out.print(obj);
				return null;
			}
			reportM.setShr(user1.getOperName());
			reportM.setShsj(new Date());
			reportM.setBbmc("夏转油综合岗日报");

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
					PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "3", "夏转油综合岗日报", reportM.getRpdReportMessageId());
					logService.addLog(log);
				} catch (Exception e) {
					e.printStackTrace();
					obj = CommonsUtil.getRrturnJson("","夏转油综合岗日报日志修改失败" ,"", null, null);
				}
			}
			out.print(obj);
			return null;
		}
}
