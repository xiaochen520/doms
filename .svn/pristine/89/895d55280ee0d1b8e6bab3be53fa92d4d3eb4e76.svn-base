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

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;

import com.echo.dto.User;
import com.echo.service.SjtdjctjbService;
import com.echo.util.AuthorityUtil;
import com.echo.util.DynamicDataExportUtil;
import com.echo.util.PageVariableUtil;
import com.echo.util.StringUtil;
import com.jspsmart.upload.Request;
import com.opensymphony.xwork2.ActionSupport;

public class SjtdjctjbAction extends ActionSupport{
	
	private SjtdjctjbService sjtdjctjbService;
	
	public void setSjtdjctjbService(SjtdjctjbService sjtdjctjbService) {
		this.sjtdjctjbService = sjtdjctjbService;
	}
	HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话

private InputStream excelFile = null;
	
	public InputStream getExcelFile() {
		return excelFile;
	}

	public void setExcelFile(InputStream excelFile) {
		this.excelFile = excelFile;
	}
	public String getFileName() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd ");
		String downloadFileName = (sf.format(new Date()).toString())+ "数据通断检查统计表.xls";
		try {
			downloadFileName = new String(downloadFileName.getBytes(),"ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}	
		return downloadFileName;
	}
	
	//数据通断检查统计表
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
			gridJson = AuthorityUtil.getGridJson1("MENU068", user, PageVariableUtil.SJTDJCTJB_PAGE_GRID);
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
	//数据通断检查动态表
	public String DTpageInit()throws Exception{
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
			gridJson = AuthorityUtil.getGridJson1("MENU069", user, PageVariableUtil.SJTDJCDTB_PAGE_GRID);
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
	public  String searchObjectName()throws Exception{
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		
		JSONArray arr = new JSONArray();
		try {
			arr = sjtdjctjbService.searchObjectName();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(arr !=null && arr.size()>0){
			out.print(arr);
		}else{
			out.print("");
		}
		return  null;
	}
	public String searchOnChangeName()throws Exception{
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String typeName  = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("typeName")));
		JSONArray arr = new JSONArray();
		try {
			arr = sjtdjctjbService.searchOnChangeName(typeName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(arr !=null && arr.size()>0){
			out.print(arr);
		}else{
			out.print("");
		}
		return  null;
	}
	public String searchDatas()throws Exception{

		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
        
		
		String OBJECT_TYPE_NAME = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("OBJECT_TYPE_NAME")));
		String CHECK_DATE = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("CHECK_DATE")));
		String totalNum = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("totalNum")));

		int pageNo = 1;  //页索引参数名当前页
		String sort = "";  //页排序列名
		String order = ""; //页排序方向
		int rowsPerpage = 0; //每页显示条数
		if(request.getParameter("pageNo") != null && !"".equals(request.getParameter("pageNo"))){
			pageNo = Integer.parseInt(request.getParameter("pageNo").trim());	
		}
		if(request.getParameter("sort") != null){
			sort = request.getParameter("sort").trim();
		}
		if(request.getParameter("order") != null){
			order = request.getParameter("order").trim();
		}
		if( request.getParameter("rowsPerpage") != null && !"".equals( request.getParameter("rowsPerpage"))){
			rowsPerpage = Integer.parseInt(request.getParameter("rowsPerpage").trim());
		}
		HashMap<String,Object> dataMap = null;
		try {
			dataMap = sjtdjctjbService.searchData(OBJECT_TYPE_NAME,CHECK_DATE ,pageNo,sort,order,rowsPerpage,totalNum);
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
			String templetFilePath = request.getSession().getServletContext().getRealPath("/") + "exceltemplet\\数据通断检查表.xls";
//			将用户对象列表输出到workbook的流中，ExcelUtil类进行了封装，后续分析！                                                              
			java.io.ByteArrayOutputStream baos = DynamicDataExportUtil.checkDataExporReport(dataLsit,templetFilePath,"统计表");
			if(baos != null){
				byte[] ba = baos.toByteArray();
				excelFile = new ByteArrayInputStream(ba);
				baos.flush();
				baos.close();
			}
			return "excel";
		}
		
		return null;

	
	}
	public String searchDTDatas()throws Exception{
		
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		
		
		String OBJECT_TYPE_NAME = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("OBJECT_TYPE_NAME")));
		String OBJECT_NAME = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("OBJECT_NAME")));
		String CHECK_DATE = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("CHECK_DATE")));
		
		String DATA_INTERNUM1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("DATA_INTERNUM1")));
		String DATA_INTERACT1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("DATA_INTERACT1")));
		String DATA_INTERNUM2 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("DATA_INTERNUM2")));
		String DATA_INTERACT2 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("DATA_INTERACT2")));
		String DATA_INTERNUM3 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("DATA_INTERNUM3")));
		String DATA_INTERACT3 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("DATA_INTERACT3")));
		
		String totalNum = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("totalNum")));
		
		int pageNo = 1;  //页索引参数名当前页
		String sort = "";  //页排序列名
		String order = ""; //页排序方向
		int rowsPerpage = 0; //每页显示条数
		if(request.getParameter("pageNo") != null && !"".equals(request.getParameter("pageNo"))){
			pageNo = Integer.parseInt(request.getParameter("pageNo").trim());	
		}
		if(request.getParameter("sort") != null){
			sort = request.getParameter("sort").trim();
		}
		if(request.getParameter("order") != null){
			order = request.getParameter("order").trim();
		}
		if( request.getParameter("rowsPerpage") != null && !"".equals( request.getParameter("rowsPerpage"))){
			rowsPerpage = Integer.parseInt(request.getParameter("rowsPerpage").trim());
		}
		HashMap<String,Object> dataMap = null;
		try {
			dataMap = sjtdjctjbService.searchDTData(OBJECT_TYPE_NAME,OBJECT_NAME,CHECK_DATE ,DATA_INTERNUM1,DATA_INTERACT1,DATA_INTERNUM2,DATA_INTERACT2,DATA_INTERNUM3,DATA_INTERACT3,pageNo,sort,order,rowsPerpage,totalNum);
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
			String templetFilePath = request.getSession().getServletContext().getRealPath("/") + "exceltemplet\\数据通断检查表.xls";
//			将用户对象列表输出到workbook的流中，ExcelUtil类进行了封装，后续分析！                                                              
			java.io.ByteArrayOutputStream baos = DynamicDataExportUtil.checkDataExporReport(dataLsit,templetFilePath,"动态表");
			if(baos != null){
				byte[] ba = baos.toByteArray();
				excelFile = new ByteArrayInputStream(ba);
				baos.flush();
				baos.close();
			}
			return "excel";
		}
		return null;
	}
}
