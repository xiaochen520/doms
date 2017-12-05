package com.echo.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.echo.dto.User;
import com.echo.service.GasWellRPDService;
import com.echo.util.AuthorityUtil;
import com.echo.util.DynamicDataExportUtil;
import com.echo.util.PageVariableUtil;
import com.echo.util.StringUtil;

public class GasWellRPDAction {
	//气井日报
	private GasWellRPDService  gaswellrpdservice;
	HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
	public void setGaswellrpdservice(GasWellRPDService gaswellrpdservice) {
		this.gaswellrpdservice = gaswellrpdservice;
	}
	private InputStream excelFile = null;
	
	public InputStream getExcelFile() {
		return excelFile;
	}

	public void setExcelFile(InputStream excelFile) {
		this.excelFile = excelFile;
	}

	public String getFileName() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd ");
		String downloadFileName = (sf.format(new Date()).toString())+ "气井日报.xls";
		try {
			downloadFileName = new String(downloadFileName.getBytes(),"ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}	
		return downloadFileName;
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
			gridJson = AuthorityUtil.getGridJson1("MENU049", user, PageVariableUtil.GAS_WELL_RPD_T);
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
	
	public String searchGWRPD()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String oil = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("oilstationname1")));
		String areablock1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("areablock1")));
		String blockstationname1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("blockstationname1")));
		String rulewellname1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("rulewellname1")));

		String startDate = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("date")));
		String endDate = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("date1")));
		String totalNum = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("totalNum")));
//		if("".equals(oil)  && "".equals(gasT) 
//				 && "".equals(wName) && "".equals(startDate) && "".equals(endDate)
//				 && "".equals(totalNum) ){
//			out.println("");
//			return null;
//		}
		if(startDate.equals("")||startDate.equals("undefined")||startDate==null){
			Date date=new Date();
			SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
			startDate=sf.format(date);
			
		}
		if(endDate.equals("")||endDate.equals("undefined")||endDate==null){
			Date date=new Date();
			SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
			endDate=sf.format(date); 
			
		}
		String outCode = "1";
		int pageNo = 1; //页索引参数名当前页
		String sort = "";		//页排序列名
		String order = "";//页排序方向
		int rowsPerpage = 0; //每页显示条数
		if(request.getParameter("pageNo") != null && !"".equals(request.getParameter("pageNo"))){
			pageNo = Integer.parseInt(request.getParameter("pageNo"));	
		}
		
		sort = StringUtil.isNullUtil(request.getParameter("sort"));
		order = StringUtil.isNullUtil(request.getParameter("order"));
		if( request.getParameter("rowsPerpage") != null && !"".equals( request.getParameter("rowsPerpage"))){
			rowsPerpage = Integer.parseInt(request.getParameter("rowsPerpage"));
		}
		HashMap<String,Object> dataMap = null;
		try {
			dataMap = gaswellrpdservice.searchGWRPD(oil,areablock1,blockstationname1,rulewellname1,startDate,endDate,totalNum,pageNo,sort,order,rowsPerpage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//返回条数
		if ("totalNum".equals(totalNum)) {
			Object total = dataMap.get("totalNum");
			out.println(total);
			out.flush();
			out.close();
			return null;
		}
		
		Object jsonobj = dataMap.get("json");
		//返回显示数据
		if(jsonobj != null && "".equals(totalNum)){
			out.println(jsonobj);
			out.flush();
			out.close();
		}
		else {//导出报表
			response.resetBuffer();
			response.reset();
			List<Object[]> dataLsit = (List<Object[]>) dataMap.get("list");
			String templetFilePath = request.getSession().getServletContext().getRealPath("/") + "exceltemplet\\日报数据.xls";
//			将用户对象列表输出到workbook的流中，ExcelUtil类进行了封装，后续分析！
			java.io.ByteArrayOutputStream baos = DynamicDataExportUtil.dailyDataExporReport(dataLsit,templetFilePath,"气井日报");
			if(baos != null){
				byte[] ba = baos.toByteArray();
				setExcelFile(new ByteArrayInputStream(ba));
				baos.flush();
				baos.close();
			}
			return "excel";
		}
		out.println("");
		return null;
	
	
	}
}
