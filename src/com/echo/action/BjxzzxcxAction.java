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
import com.echo.service.BjfzService;
import com.echo.service.LogService;
import com.echo.util.AuthorityUtil;
import com.echo.util.DynamicDataExportUtil;
import com.echo.util.PageVariableUtil;
import com.echo.util.StringUtil;

public class BjxzzxcxAction {

	private BjfzService bjfzService;
	private InputStream excelFile = null;
	
	public void setBjfzService(BjfzService bjfzService) {
		this.bjfzService = bjfzService;
	}
	@SuppressWarnings("unused")
	private LogService logService;

	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	
	public String getFileName() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd ");
		String downloadFileName = (sf.format(new Date()).toString())+ "报警限值执行信息查询.xls";
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
				gridJson = AuthorityUtil.getGridJson("报警限值执行信息查询", user, PageVariableUtil.BJXZCX_PAGE_GRID);
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
		
	
		//查询数据
		@SuppressWarnings("unchecked")
		public String searchBjcx() throws Exception {
			
			HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
			HttpServletResponse response=ServletActionContext.getResponse();//响应对象
			response.setCharacterEncoding("utf-8");
			response.setHeader("ContentType","text/xml");
			PrintWriter out = response.getWriter();		
			String startDate = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("startDate")));
			String endDate = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("endDate")));
			String totalNum = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("totalNum")));
			
			
			if(startDate.equals("")||startDate.equals("undefined")||startDate==null){
				Date date=new Date();
				SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:MM:SS");
				startDate=sf.format(date);
			}
			if(endDate.equals("")||endDate.equals("undefined")||endDate==null){
				Date date=new Date();
				SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:MM:SS");
				endDate=sf.format(date);				
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
	 		if("".equals(startDate) && "".equals(endDate) && "".equals(totalNum)){
				out.println("");
				return null;
			}
			HashMap<String,Object> dataMap = null;
			try {
				
				dataMap = bjfzService.searchBjxzcx(startDate,endDate,pageNo,sort,order,rowsPerpage,totalNum);
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
//				将用户对象列表输出到workbook的流中，ExcelUtil类进行了封装，后续分析！
//				String sheetName = "重18SAGD-3#站动态数据";				
				java.io.ByteArrayOutputStream baos = DynamicDataExportUtil.dynamicDataExporReport(dataLsit,templetFilePath,"报警限值执行信息查询");
				if(baos != null){
					byte[] ba = baos.toByteArray();
					excelFile = new ByteArrayInputStream(ba);
					baos.flush();
					baos.close();
				}
				return "excel";
			}
			out.println("");
			return null;
		}
	
	
}
