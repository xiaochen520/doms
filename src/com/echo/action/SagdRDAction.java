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

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import com.echo.dto.User;
import com.echo.service.LogService;
import com.echo.service.SagdService;
import com.echo.util.AuthorityUtil;
import com.echo.util.DynamicDataExportUtil;
import com.echo.util.PageVariableUtil;
import com.echo.util.StringUtil;

/**
 * 
 * @author 王博
 * @date 2017-5-4
 * @time 下午04:17:47
 * 
 */
@SuppressWarnings("all")
public class SagdRDAction {
	private SagdService sagdService;
	private InputStream excelFile = null;
	private LogService logService;
	HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话

	
	public String searchSagdRD() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest(); // 请求对象
		HttpServletResponse response = ServletActionContext.getResponse();// 响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType", "text/xml");
		PrintWriter out = response.getWriter();
		String datastype = StringUtil.toStr(StringUtil.isNullUtil(request
				.getParameter("datastype")));
		String oilNumber = StringUtil.toStr(StringUtil.isNullUtil(request
				.getParameter("oilationname")));
		String ghmc = StringUtil.toStr(StringUtil.isNullUtil(request
				.getParameter("ghmc")));
		String oilname = StringUtil.toStr(StringUtil.isNullUtil(request
				.getParameter("wellnum")));
		String startDate = StringUtil.toStr(StringUtil.isNullUtil(request
				.getParameter("startDate")));
		String endDate = StringUtil.toStr(StringUtil.isNullUtil(request
				.getParameter("endDate")));
		String totalNum = StringUtil.toStr(StringUtil.isNullUtil(request
				.getParameter("totalNum")));

		int pageNo = 1; // 页索引参数名当前页
		String sort = ""; // 页排序列名
		String order = "";// 页排序方向
		int rowsPerpage = 0; // 每页显示条数
		if (request.getParameter("pageNo") != null
				&& !"".equals(request.getParameter("pageNo"))) {
			pageNo = Integer.parseInt(request.getParameter("pageNo").trim());
		}

		if (request.getParameter("sort") != null) {
			sort = request.getParameter("sort").trim();
		}

		if (request.getParameter("order") != null) {
			order = request.getParameter("order").trim();
		}

		if (request.getParameter("rowsPerpage") != null
				&& !"".equals(request.getParameter("rowsPerpage"))) {
			rowsPerpage = Integer.parseInt(request.getParameter("rowsPerpage")
					.trim());
		}
		if ("".equals(datastype) && "".equals(oilNumber) && "".equals(ghmc)
				&& "".equals(oilname) && "".equals(startDate)
				&& "".equals(endDate) && "".equals(totalNum)) {
			out.println("");
			return null;
		}
		HashMap<String, Object> dataMap = null;
		try {
			dataMap = sagdService.searchSagdRDWell(datastype, oilNumber, ghmc,
					oilname, startDate, endDate, pageNo, sort, order,
					rowsPerpage, totalNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 返回条数
		if ("totalNum".equals(totalNum)) {
			Object total = dataMap.get("totalNum");
			out.println(total);
			out.flush();
			out.close();
			return null;
		}

		Object jsonobj = dataMap.get("json");
		// 返回显示数据
		if (jsonobj != null && "".equals(totalNum)) {
			out.println(jsonobj);
			out.flush();
			out.close();
		} else {// 导出报表
			response.resetBuffer();
			response.reset();
			List<Object[]> dataLsit = (List<Object[]>) dataMap.get("list");
			String templetFilePath = request.getSession().getServletContext()
					.getRealPath("/")
					+ "exceltemplet\\动态数据报表.xls";
			// 将用户对象列表输出到workbook的流中，ExcelUtil类进行了封装，后续分析！
			String sheetName = "SAGD井动态数据";
			if (!"1".equals(datastype)) {
				sheetName += datastype;
			}
			java.io.ByteArrayOutputStream baos = DynamicDataExportUtil
					.dynamicDataExporReport(dataLsit, templetFilePath,
							sheetName);
			if (baos != null) {
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

	// 根据权限进行页面初始化
	public String pageInit() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();// 响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType", "text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		// 根据用户权限生成用户权限生成页面布局格式
		User user = (User) session.getAttribute("userInfo");
		//		
		String gridJson = null;
		try {
			gridJson = AuthorityUtil.getGridJson("SAGD井动态数据", user,
					PageVariableUtil.SAGDRDALL_PAGE_GRID);
		} catch (Exception e) {
			e.printStackTrace();
			outCode = "-10004";
		}

		if (gridJson != null && "1".equals(outCode)) {
			out.print(gridJson);
		} else {
			out.print(outCode);
		}
		return null;
	}
	

	/*
	 * 动态曲线
	 */
	public String searchSagdRDLine(){
		
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		String oilname = StringUtil.toStr(StringUtil.isNullUtil(request
				.getParameter("wellnum")));
		String startDate = StringUtil.toStr(StringUtil.isNullUtil(request
				.getParameter("startDate")));
		String endDate = StringUtil.toStr(StringUtil.isNullUtil(request
				.getParameter("endDate")));
		PrintWriter out=null;
		JSONObject json=null;
		 
		
		try {
			out = response.getWriter();
			json = sagdService.searchSagdCurve(oilname, startDate,endDate);
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}	

		if(json != null){
			out.println(json);
			out.flush();
			out.close();
		}
		return null;
	}
	
	
	
	public String getFileName() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd ");
		String downloadFileName = (sf.format(new Date()).toString())
				+ "SAGD井动态数据.xls";
		try {
			downloadFileName = new String(downloadFileName.getBytes(),
					"ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return downloadFileName;
	}

	// 根据权限进行页面初始化
	public String pageTypeInit() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest(); // 请求对象
		HttpServletResponse response = ServletActionContext.getResponse();// 响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType", "text/xml");
		PrintWriter out = response.getWriter();
		String datastype = StringUtil.toStr(StringUtil.isNullUtil(request
				.getParameter("datastype")));
		String outCode = "1";
		// 根据用户权限生成用户权限生成页面布局格式
		User user = (User) session.getAttribute("userInfo");
		String pagename = "";
		String gridJson = null;
		try {
			if ("1".equals(datastype)) {
				pagename = PageVariableUtil.SAGDRDALL_PAGE_GRID;
			} else if ("2".equals(datastype)) {
				pagename = PageVariableUtil.SAGDRDALL_PAGE_GRID2;
			} else if ("3".equals(datastype)) {
				pagename = PageVariableUtil.SAGDRDALL_PAGE_GRID3;
			} else if ("4".equals(datastype)) {
				pagename = PageVariableUtil.SAGDRDALL_PAGE_GRID4;
			} else if ("5".equals(datastype)) {
				pagename = PageVariableUtil.SAGDRDALL_PAGE_GRID5;
			} else {
				outCode = "-10004";

			}
			gridJson = AuthorityUtil.getGridJson("SAGD井动态数据", user, pagename);
		} catch (Exception e) {
			e.printStackTrace();
			outCode = "-10004";
		}
		if (gridJson != null && "1".equals(outCode)) {
			// System.out.println(gridJson);
			out.print(gridJson);
		} else {
			out.print(outCode);
		}
		return null;
	}

	public void setSagdService(SagdService sagdService) {
		this.sagdService = sagdService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	public InputStream getExcelFile() {
		return excelFile;
	}
}
