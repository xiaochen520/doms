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

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.echo.dto.User;
import com.echo.service.U1yqdtService;
import com.echo.util.AuthorityUtil;
import com.echo.util.DynamicDataExportUtil;
import com.echo.util.PageVariableUtil;
import com.echo.util.StringUtil;

public class U1fxdtAction {
   private U1yqdtService u1yqdtService;
   HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话

	public void setU1yqdtService(U1yqdtService u1yqdtService) {
		this.u1yqdtService = u1yqdtService;
	}
	
	public U1yqdtService getU1yqdtService() {
		return u1yqdtService;
	}
	private InputStream excelFile = null;
	public String getFileName() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd ");
		String downloadFileName = (sf.format(new Date()).toString())+ "稀油注输联合站（分线计量）动态数据报表.xls";
		try {
			downloadFileName = new String(downloadFileName.getBytes(),"ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return downloadFileName;
	}
   
	public void setExcelFile(InputStream excelFile) {
		this.excelFile = excelFile;
	}

	public String serarchU1fxdt()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String startDate = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("date")));
		String endDate = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("date1")));
		String totalNum = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("totalNum")));
		
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
		 		dataMap = u1yqdtService.serarchU1fxdt(startDate,endDate,pageNo,sort,order,rowsPerpage,totalNum);

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
//			else {//导出报表
//				response.resetBuffer();
//				response.reset();
//				List<Object[]> dataLsit = (List<Object[]>) dataMap.get("list");
//				String templetFilePath = request.getSession().getServletContext().getRealPath("/") + "exceltemplet\\稀油注输联合站（油区）动态数据报表.xls";
////				将用户对象列表输出到workbook的流中，ExcelUtil类进行了封装，后续分析！
//				java.io.ByteArrayOutputStream baos = DynamicDataExportUtil.dynamicDataExporReport(dataLsit,templetFilePath,"稀油注输联合站（油区）");
//				if(baos != null){
//					byte[] ba = baos.toByteArray();
//					excelFile = new ByteArrayInputStream(ba);
//					baos.flush();
//					baos.close();
//				}
//				return "excel";
//			}
			
			return null;
	 		
		}
	
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
			gridJson = AuthorityUtil.getGridJson("分线计量动态数据", user, PageVariableUtil.PC_RD_U1_OIL_AUTO_T_GRID);
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
}
