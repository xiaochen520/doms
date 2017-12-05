package com.echo.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.echo.dto.PcCdOrgT;
import com.echo.dto.PcCdServerNodeT;
import com.echo.dto.PcCdWellSagdT;
import com.echo.dto.PcRdLoginfoT;
import com.echo.dto.User;
import com.echo.service.CommonService;
import com.echo.service.LogService;
import com.echo.service.ManifoldBasicService;
import com.echo.service.SagdService;
import com.echo.util.AuthorityUtil;
import com.echo.util.CommonsUtil;
import com.echo.util.DateBean;
import com.echo.util.PageVariableUtil;
import com.echo.util.StringUtil;

public class RuleWellRPDWHAction {
	private SagdService sagdService;
	private ManifoldBasicService manifoldBasicService;
	private CommonService commonService;
	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}

	public void setSagdService(SagdService sagdService) {
		this.sagdService = sagdService;
	}
	private LogService logService;

	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	
	public void setManifoldBasicService(ManifoldBasicService manifoldBasicService) {
		this.manifoldBasicService = manifoldBasicService;
	}
	HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
	public String searchSagdBaisc() throws Exception {
		
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String stationNumber = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("oilstationname")));
		String boilersName = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("areablock")));
		String areablock = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("blockstationname")));
		String ghname = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("ghname")));
		String oilname = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("wellnum")));
		
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
		
 		HashMap<String,Object> dataMap = sagdService.searchSagdWell("","","","","",stationNumber,boilersName,areablock,ghname,oilname,pageNo,sort,order,rowsPerpage);
 		Object jsonobj = dataMap.get("json");
 		if(jsonobj != null){
			out.println(jsonobj);
		}
		return null;
	}
	//根据权限进行页面初始化
	public String SagdRPDpageInit()throws Exception{
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
			gridJson = AuthorityUtil.getGridJson("SAGD井日报", user, PageVariableUtil.SAGDDRPDALL_PAGE_GRID);
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
	
	//根据权限进行页面初始化
	public String SagdRPDWHpageInit()throws Exception{
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
			gridJson = AuthorityUtil.getGridJson("SAGD井日报维护", user, PageVariableUtil.SAGDDRPDWHALL_PAGE_GRID);
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
	
	public String removeSagdRPD() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		String stationId = StringUtil.toStr(request.getParameter("sagdId"));
		String orgId = StringUtil.toStr(request.getParameter("org_id"));
		boolean deleteflg = false;
		try {
			deleteflg = sagdService.removeStationInfo(stationId,orgId);
		} catch (Exception e) {
			//this.addFieldError("errermsg", "删除采油站信息失败~");
			e.printStackTrace();
			outCode = "-17105";
		}
		
		if(deleteflg){
			
			//添加系统LOG
			try {
				User user1 = (User) session.getAttribute("userInfo");
				PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "2", "SAGD井基础信息", stationId);
				logService.addLog(log);
			} catch (Exception e) {
				e.printStackTrace();
				outCode = "-10003";
			}
			
			
		}
		out.print(outCode);
		return null;
	}
	
	public String queryAreablockInfo() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONArray jsonArr = null;
		String arg = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("arg")));
		
		try {
			jsonArr = sagdService.queryAreablockInfo(arg);
		} catch (Exception e) {
			//this.addFieldError("errermsg", "区块信息获取失败~");
			e.printStackTrace();
		}
		if(jsonArr != null){
			out.println(jsonArr);
		}else{
			out.println("");
		}
		return null;
	}
	
	
	public String queryStationInfo() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		
		String areaid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("areaid")));
		JSONArray jsonArr = null;
		try {
			jsonArr = sagdService.queryStationInfo(areaid);
		} catch (Exception e) {
			// TODO: handle exception
//			this.addFieldError("errermsg", "供热站信息获取失败~");
			e.printStackTrace();
		}
		if(jsonArr != null){
			out.println(jsonArr);
		}else{
			out.println("");
		}
		return null;
	}
	
	public String queryWellNameInfo() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String boilername = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("orgid")));
		JSONArray jsonArr = null;
		try {
			jsonArr = sagdService.queryWellNameInfo(boilername);
		} catch (Exception e) {
//			this.addFieldError("errermsg", "锅炉信息获取失败~");
			e.printStackTrace();
		}
		if(jsonArr != null){
			out.println(jsonArr);
		}else{
			out.println("");
		}
		return null;
	}
	public String cascadeInit() throws Exception {
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONObject jsonArr = null;
		try {
			jsonArr = sagdService.cascadeInit();
		} catch (Exception e) {
//			this.addFieldError("errermsg", "级联菜单初始化失败~");
			e.printStackTrace();
		}
		if(jsonArr != null){
			out.println(jsonArr);
		}else{
			out.println("");
		}
		return null;
	}
	public String addOrUpdateSagd() throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		boolean addflg  = true;
		PcCdWellSagdT  sagd= null;
		PcCdOrgT org = null;
		List<PcCdWellSagdT> sagdlist = null;
		List<PcCdWellSagdT> sagdlistid = null;
		List<Object[]> ghlist = null;
		
		//父节点管汇ID
		
		out.print(outCode);
		return null;
	}
	public String queryGridDataInfo() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONArray jsonArr = null;
		String wellName = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("wellName")));
		String orgId = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("orgId")));
		try {
			jsonArr = sagdService.queryGridDataInfo(wellName,orgId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(jsonArr != null){
			out.println(jsonArr);
		}else{
			out.println("-1");
		}
		return null;
	}
	
}
