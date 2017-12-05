package com.echo.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.echo.dto.User;
import com.echo.service.BoilerRoomGasMonitoringService;
import com.echo.service.LogService;
import com.echo.util.AuthorityUtil;
import com.echo.util.PageVariableUtil;
import com.echo.util.StringUtil;

/**
 * 
 * @author 王博
 * @date 2017-5-3
 * @time 上午11:04:24
 * 
 */

public class BoilerRoomGasMonitoringDataAction {

	private BoilerRoomGasMonitoringService boilerRoomGasMonitoringService;
	private LogService logService;
	HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话

	// 根据权限进行页面初始化
	public String pageInit() throws Exception {
		HttpServletResponse response = initHttpServletResponse();
		PrintWriter out = response.getWriter();
		String outCode = "1";
		// 根据用户权限生成用户权限生成页面布局格式
		User user = (User) session.getAttribute("userInfo");
		String gridJson = null;
		try {
			gridJson = AuthorityUtil.getGridJson("锅炉房气体监测数据", user,
					PageVariableUtil.BOILER_ROOM_GAS_MONITORING_DATA);

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



	public String searchBlockstationData() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest(); // 请求对象
		HttpServletResponse response = initHttpServletResponse();// 响应对象
		PrintWriter out = response.getWriter();
		String boilersName = StringUtil.toStr(StringUtil.isNullUtil(request
				.getParameter("blockstationname")));
		String stime = StringUtil.toStr(StringUtil.isNullUtil(request
				.getParameter("date")));
		String etime = StringUtil.toStr(StringUtil.isNullUtil(request
				.getParameter("date1")));
		String totalNum = StringUtil.toStr(StringUtil.isNullUtil(request
				.getParameter("totalNum")));
		if (stime.equals("") || stime.equals("undefined") || stime == null) {
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			stime = sf.format(date);

		}
		if (etime.equals("") || etime.equals("undefined") || etime == null) {
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			etime = sf.format(date);
		}

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
		HashMap<String, Object> dataMap = null;
		try {

			dataMap = boilerRoomGasMonitoringService.searchData(boilersName,
					stime, etime, pageNo, sort, order, rowsPerpage, totalNum);
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
		}
		return null;
	}

	private HttpServletResponse initHttpServletResponse() {
		HttpServletResponse response = ServletActionContext.getResponse();// 响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType", "text/xml");
		return response;
	}
	
	public LogService getLogService() {
		return logService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	public BoilerRoomGasMonitoringService getBoilerRoomGasMonitoringService() {
		return boilerRoomGasMonitoringService;
	}

	public void setBoilerRoomGasMonitoringService(
			BoilerRoomGasMonitoringService boilerRoomGasMonitoringService) {
		this.boilerRoomGasMonitoringService = boilerRoomGasMonitoringService;
	}

}
