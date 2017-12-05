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

import net.sf.json.JSONObject;

import org.apache.poi.hssf.converter.ExcelToHtmlUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import com.echo.dto.User;
import com.echo.service.U2fxjldtService;
import com.echo.util.AuthorityUtil;
import com.echo.util.CommonsUtil;
import com.echo.util.DateBean;
import com.echo.util.PageVariableUtil;
import com.echo.util.PropertiesConfig;
import com.echo.util.StringUtil;
import com.echo.util.U2DataExportUtil;

public class fxjlrbAction {
	HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
	private U2fxjldtService u2fxjldtService;
	private InputStream excelFile = null;
	public String getFileName() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd ");
		String downloadFileName = (sf.format(new Date()).toString())+ "风城油田作业区各采油站综合日报表.xls";
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


	

	public void setU2fxjldtService(U2fxjldtService u2fxjldtService) {
		this.u2fxjldtService = u2fxjldtService;
	}

	public String searchDatas() throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONObject obj = null;
		String txtYeah = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("SYear")));
		String txtMonth = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("SMonth")));
		List<String> date = null;
		JSONObject u2szjcs = null;
		if(txtYeah != null && !"".equals(txtYeah) && txtMonth != null && !"".equals(txtMonth)){
			date = DateBean.getYeahMonth(txtYeah, txtMonth);
		}else{
			Calendar now = Calendar.getInstance();  
			date = DateBean.getYeahMonth(String.valueOf(now.get(Calendar.YEAR)), String.valueOf((now.get(Calendar.MONTH) + 1)));
		
		}
		
		try {
			u2szjcs = u2fxjldtService.searchfxjlrb(date);
			obj = CommonsUtil.getRrturnJson("","" ,"", u2szjcs, null);
		} catch (Exception e) {
			e.printStackTrace();
			obj = CommonsUtil.getRrturnJson("","获取各采油站综合日报表失败!" ,"", null, null);
			out.print(obj);
			return null;
		}

		out.print(u2szjcs);
		
	return null;
		}
		//根据权限进行页面初始化
		public String pageInit()throws Exception{
			HttpServletResponse response=ServletActionContext.getResponse();//响应对象
			response.setCharacterEncoding("utf-8");
			response.setHeader("ContentType","text/xml");
			PrintWriter out = response.getWriter();
			String outCode = "1";
			//根据用户权限生成用户权限生成页面布局格式
			User user = (User) session.getAttribute("userInfo");
//			
			String gridJson = null;
			try {
				gridJson = AuthorityUtil.getGridJson1("MENU168", user, PageVariableUtil.FXJLRB_PAGE_GRID);
			} catch (Exception e) {
				e.printStackTrace();
				outCode = "-10004";
			}
			
			if(gridJson != null && "1".equals(outCode)){
				//System.out.println(gridJson);
				out.print(gridJson);
			}else{
				out.print(outCode);
			}
			return null;
	}
		public String exportFXJLRB() throws Exception {
			HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
			String txtYeah = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("SYear")));
			String txtMonth = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("SMonth")));
			User user = (User) session.getAttribute("userInfo");
			String txtDate = DateBean.getSystemTime1();
			String username = user.getOperName();
			String templetFilePath = request.getSession().getServletContext().getRealPath("/") + "exceltemplet\\combination\\风城油田作业区各采油站综合日报表.xls";
			HSSFWorkbook wb = ExcelToHtmlUtils.loadXls( new File( templetFilePath ) );
			HSSFSheet sheet = wb.getSheetAt(0);
			
			String temp = txtYeah+"."+txtMonth;
			wb.setSheetName(0, temp);
			
			List<String> date = null;
			if(txtYeah != null && !"".equals(txtYeah) && txtMonth != null && !"".equals(txtMonth)){
				date = DateBean.getYeahMonth(txtYeah, txtMonth);
			}else{
				Calendar now = Calendar.getInstance();  
				date = DateBean.getYeahMonth(String.valueOf(now.get(Calendar.YEAR)), String.valueOf((now.get(Calendar.MONTH) + 1)));
			
			}

			PropertiesConfig pc = new PropertiesConfig();
			String fields = pc.getSystemConfiguration("FXJLRBSQL");			
			List<Object[]> list = u2fxjldtService.searchExportData(date,fields);
			String U2UZJC_START_INSERT = pc.getSystemConfiguration("FXJLRB_START_INSERT");
			String FXtime = pc.getSystemConfiguration("FXJLRBtime");
			String FXname = pc.getSystemConfiguration("FXJLRBname");	
			U2DataExportUtil.insertExcelData(wb, sheet, FXtime, 0, new Object[]{txtDate});
			U2DataExportUtil.insertExcelData(wb, sheet, FXname, 0, new Object[]{username});
			if (list.size() != 0 && list != null) {
				if (list != null && list.size() > 0) {					
					U2DataExportUtil.insertDataByPosition(list, wb, sheet, U2UZJC_START_INSERT);
				}
			}				
			java.io.ByteArrayOutputStream baos = U2DataExportUtil.ExporDataStream(wb);
			if(baos != null){
				byte[] ba = baos.toByteArray();
				excelFile = new ByteArrayInputStream(ba);
				baos.flush();
				baos.close();
			}
			return "excel";
		}
		
		
		
		
		public String searchAlarmLine() throws Exception {
			HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
			HttpServletResponse response=ServletActionContext.getResponse();//响应对象
			response.setCharacterEncoding("utf-8");
			response.setHeader("ContentType","text/xml");
			PrintWriter out = response.getWriter();
		
			String year = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("year")));
			String month = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("month")));
			List<String> date = null;
			if(year != null && !"".equals(year) && month != null && !"".equals(month)){
				date = DateBean.getYeahMonth(year, month);
			}else{
				Calendar now = Calendar.getInstance();  
				date = DateBean.getYeahMonth(String.valueOf(now.get(Calendar.YEAR)), String.valueOf((now.get(Calendar.MONTH) + 1)));
			
			}

			JSONObject json = u2fxjldtService.searchSagdghResults(date);
			if(json != null){
				out.println(json);
				out.flush();
				out.close();
			}
			
			return null;
		}
}
