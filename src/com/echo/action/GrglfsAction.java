package com.echo.action;

import java.io.ByteArrayInputStream;
import java.io.IOException;
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
import com.echo.service.BoilerSuperheatRDService;
import com.echo.service.LogService;
import com.echo.service.SRGLRDService;
import com.echo.util.AuthorityUtil;
import com.echo.util.DynamicDataExportUtil;
import com.echo.util.PageVariableUtil;
import com.echo.util.StringUtil;

public class GrglfsAction {

	private BoilerSuperheatRDService boilerSuperheatRDService;
	private InputStream excelFile = null;
	private SRGLRDService srglRDService;
	


	private LogService logService;

	public LogService getLogService() {
		return logService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	


	public BoilerSuperheatRDService getBoilerSuperheatRDService() {
		return boilerSuperheatRDService;
	}

	public void setBoilerSuperheatRDService(
			BoilerSuperheatRDService boilerSuperheatRDService) {
		this.boilerSuperheatRDService = boilerSuperheatRDService;
	}

	public SRGLRDService getSrglRDService() {
		return srglRDService;
	}

	public void setSrglRDService(SRGLRDService srglRDService) {
		this.srglRDService = srglRDService;
	}

	public String getFileName() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd ");
		String downloadFileName = (sf.format(new Date()).toString())+ "过热锅炉日报.xls";
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
	HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
	
	
	
	//根据权限进行页面初始化
	@SuppressWarnings("unused")
	//根据权限进行页面初始化
	public String pageInit()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";

		//根据用户权限生成用户权限生成页面布局格式
		User user = (User) session.getAttribute("userInfo");
		String gridJson = null;
		try {
			gridJson = AuthorityUtil.getGridJson("过热锅炉分时数据", user, PageVariableUtil.PC_RPD_BOILER_SUP_HOURS_T);

		} catch (Exception e) {
			e.printStackTrace();
			outCode = "-10004";
		}
		
		if(gridJson != null && "1".equals(outCode)){
			out.print(gridJson);
		}else{
			out.print(outCode);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public String searchGrglRPD() throws IOException {		
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String oilname = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("oilationname")));
		String areablock = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("areablock")));
		String blockname = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("blockstationname")));
		String ghname = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("ghname")));
		String boilersname = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("boilersName")));
		String acceptunit = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("acceptunit")));
		String group = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("group")));
		String stime=StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("startDate")));
		String etime=StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("endDate")));
		String totalNum = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("totalNum")));
		
		if(stime.equals("")||stime.equals("undefined")||stime==null){
			Date date=new Date();
			SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			stime=sf.format(date);
			
		}
		if(etime.equals("")||etime.equals("undefined")||etime==null){
			Date date=new Date();
			SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			etime=sf.format(date);
			
		}
		
		int pageNo = 1; //页索引参数名当前页
		String sort = "";		//页排序列名
		String order = "";//页排序方向
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
			
			dataMap = boilerSuperheatRDService.searchData1(oilname,blockname,areablock,ghname,boilersname,acceptunit,stime,etime,pageNo,sort,order,rowsPerpage,totalNum,group);
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
			String templetFilePath = request.getSession().getServletContext().getRealPath("/") + "exceltemplet\\动态数据报表.xls";
//			将用户对象列表输出到workbook的流中，ExcelUtil类进行了封装，后续分析！
			java.io.ByteArrayOutputStream baos = DynamicDataExportUtil.dynamicDataExporReport(dataLsit,templetFilePath,"过热锅炉");
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
