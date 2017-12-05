package com.echo.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.echo.dto.User;
import com.echo.service.SearchQueryAllService;
import com.echo.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

public class SearchQueryAllAction extends ActionSupport{
	private SearchQueryAllService searchQueryAllService;

	public SearchQueryAllService getSearchQueryAllService() {
		return searchQueryAllService;
	}

	public void setSearchQueryAllService(SearchQueryAllService searchQueryAllService) {
		this.searchQueryAllService = searchQueryAllService;
	}
	
	public String queryGroupInfo()throws Exception{

		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		
		String oilid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("oilid")));
		JSONArray jsonArr = null;
		try {
			jsonArr = searchQueryAllService.queryGroupInfo(oilid);
		} catch (Exception e) {
			this.addFieldError("errermsg", "班组信息获取失败~");
			e.printStackTrace();
		}
		if(jsonArr != null){
			out.println(jsonArr);
		}else{
			out.println("");
		}
		return null;
	
	}
	public String queryGuanHuiInfo()throws Exception{
		
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		
		String oilid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("oilid")));
		JSONArray jsonArr = null;
		try {
			jsonArr = searchQueryAllService.queryGuanHuiInfo(oilid);
		} catch (Exception e) {
			this.addFieldError("errermsg", "班组信息获取失败~");
			e.printStackTrace();
		}
		if(jsonArr != null){
			out.println(jsonArr);
		}else{
			out.println("");
		}
		return null;
		
	}
	public String queryOilGuanHuiInfo()throws Exception{
		
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		
		String oilid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("oilid")));
		JSONArray jsonArr = null;
		try {
			jsonArr = searchQueryAllService.queryOilGuanHuiInfo(oilid);
		} catch (Exception e) {
			this.addFieldError("errermsg", "班组信息获取失败~");
			e.printStackTrace();
		}
		if(jsonArr != null){
			out.println(jsonArr);
		}else{
			out.println("");
		}
		return null;
		
	}
	/**
	 *班组 注转战 分别关联 管汇
	 * @return
	 * @throws Exception
	 */
	
	public String searchGHcommon()throws Exception{
		
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		
		String cyzid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("cyzid")));
		String zzzid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("zzzid")));
		String teamid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("teamid")));
		String param = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("param")));
		JSONArray jsonArr = null;
		try {
			jsonArr = searchQueryAllService.searchGHcommon(cyzid,zzzid,teamid,param);
		} catch (Exception e) {
			this.addFieldError("errermsg", "班组信息获取失败~");
			e.printStackTrace();
		}
		if(jsonArr != null){
			out.println(jsonArr);
		}else{
			out.println("");
		}
		return null;
		
	}
	/**
	 * 选择采油站， 关联 ，注转战，班组  ，管汇
	 * @return
	 * @throws Exception
	 */
	public String searchOilChangeData()throws Exception{

	HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
	HttpServletResponse response=ServletActionContext.getResponse();//响应对象
	response.setCharacterEncoding("utf-8");
	response.setHeader("ContentType","text/xml");
	PrintWriter out = response.getWriter();
	
	String oilid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("oilid")));
	JSONObject jsonArr = null;
	try {
		jsonArr = searchQueryAllService.searchOilChangeData(oilid);
	} catch (Exception e) {
		this.addFieldError("errermsg", "班组信息获取失败~");
		e.printStackTrace();
	}
	if(jsonArr != null){
		out.println(jsonArr);
	}else{
		out.println("");
	}
	return null;
	}
	
	/**
	 *所有班组
	 * @return
	 * @throws Exception
	 */
	
	public String searchAllGroup()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		
		JSONArray  ArrList = null;
		try {
			ArrList =searchQueryAllService.searchAllGroup();
		} catch (Exception e) {
			e.printStackTrace();
		}
	if(ArrList !=null){
		out.println(ArrList);
	}else{
		out.println("");
	}
		return null;
	}
	/**
	 *所有注转战
	 * @return
	 * @throws Exception
	 */
	public String searchAllSatation()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String paramArg = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("param")));
		JSONArray  ArrList = null;
		try {
			ArrList =searchQueryAllService.searchAllSatation(paramArg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	if(ArrList !=null){
		out.println(ArrList);
	}else{
		out.println("");
	}
		return null;
	}
	/**
	 *所有管汇
	 */
	public String searchAllMani()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String groupMani = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("groupID")));
		String stationMani = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("stationID")));
		String ManiWell = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("well")));
		String  nullParam = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("param")));
		JSONArray  ArrList = null;
		try {
			ArrList =searchQueryAllService.searchAllMani(groupMani,stationMani,ManiWell,nullParam);
		} catch (Exception e) {
			e.printStackTrace();
		}
	if(ArrList !=null){
		out.println(ArrList);
	}else{
		out.println("");
	}
		return null;
	}
	
	/**
	 *edit
	 */
	public String searchConnect()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		//采油站， 班组，接转战 管汇  井
		String OilUnitID = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("maniID")));
		String GroupTeamID = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("maniID")));
		String StationID  =	StringUtil.toStr(StringUtil.isNullStr(request.getParameter("maniID")));
		String maniID = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("maniID")));
		String wellNameID = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("parID")));
		//String  nullParam = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("param")));
		JSONObject  ArrList = null;
		try {
			ArrList =searchQueryAllService.searchConnect(OilUnitID,GroupTeamID,StationID,maniID,wellNameID);
		} catch (Exception e) {
			e.printStackTrace();
		}
	if(ArrList !=null){
		out.println(ArrList);
	}else{
		out.println("");
	}
		return null;
	}
	//采油站 班组，注水撬 （注水井）
	
	public String searchOilGroupWaterInjec()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		//采油站， 班组，接转战 管汇  井  （名称）
		String OilId = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("OilName")));
		String groupId = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("groupName")));
		String injeId  =	StringUtil.toStr(StringUtil.isNullStr(request.getParameter("injeName")));
		JSONObject  ArrList = null;
		try {
			ArrList =searchQueryAllService.searchOilGroupWaterInjec(OilId,groupId,injeId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	if(ArrList !=null){
		out.println(ArrList);
	}else{
		out.println("");
	}
		return null;
	}
	// 班组  变 注水撬
	public String searchGroupWellOnchange()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		//采油站， 班组，接转战 管汇  井  （名称）
		String teamName = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("orgid")));
		
		JSONArray  ArrList = null;
		try {
			ArrList =searchQueryAllService.searchGroupWellOnchange(teamName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	if(ArrList !=null){
		out.println(ArrList);
	}else{
		out.println("");
	}
		return null;
	}
	
	public String searchOnChangeManiWell()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		//采油站， 班组，接转战 管汇  井  （名称）
		String groupName = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("groupName")));
		String StationName = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("StationName")));
		String ManiName = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("ManiName")));
		
		String arg =StringUtil.toStr(StringUtil.isNullStr(request.getParameter("arg")));
		JSONArray  ArrList = null;
		try {
			ArrList =searchQueryAllService.searchOnChangeManiWell(groupName,StationName,ManiName,arg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	if(ArrList !=null){
		out.println(ArrList);
	}else{
		out.println("");
	}
		return null;
	}
	public String searchOnChangeManiRuleWell()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		//采油站， 班组，接转战 管汇  井  （名称）
		
		String ManiName = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("ManiName")));
		
		JSONArray  ArrList = null;
		try {
			ArrList =searchQueryAllService.searchOnChangeManiRuleWell(ManiName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	if(ArrList !=null){
		out.println(ArrList);
	}else{
		out.println("");
	}
		return null;
	}
	public String searchChangeGroupOnWell()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		//班组 变井
		String groupName = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("groupName")));
		
		JSONArray  ArrList = null;
		try {
			ArrList =searchQueryAllService.searchChangeGroupOnWell(groupName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	if(ArrList !=null){
		out.println(ArrList);
	}else{
		out.println("");
	}
		return null;
	}
	
	/**
	 * 全部站库采集点页面初始化下拉框数据获取
	 */
	
	public String searchZKCJD()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String arg = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("arg")));
		JSONObject  ArrList = null;
		try {
			ArrList =searchQueryAllService.searchALLZKCJD(arg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	if(ArrList !=null){
		out.println(ArrList);
	}else{
		out.println("");
	}
		return null;
	}	
	
	
	/**
	 * 全部 采油站-班组-井号（SAGD井，常规稠油井，稀油井，注水井） 联动查询 全部 采油站数据
	 */
	
	public String searchALLCYZ()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String arg = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("arg")));
		JSONObject  ArrList = null;
		try {
			ArrList =searchQueryAllService.searchALLCYZ(arg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	if(ArrList !=null){
		out.println(ArrList);
	}else{
		out.println("");
	}
		return null;
	}
	
	
	/**
	 * 采油站-班组-井号（SAGD井，常规稠油井，稀油井，注水井） 联动查询
	 */
	
	public String searchTeam()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		//班组 变井
		String CYZ = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("CYZ")));
		String TEAM = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("TEAM")));
		String arg = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("arg")));
		JSONArray  ArrList = null;
		try {
			ArrList =searchQueryAllService.searchTeam(CYZ,arg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	if(ArrList !=null){
		out.println(ArrList);
	}else{
		out.println("");
	}
		return null;
	}
	
	/**
	 * 采油站-班组-井号（SAGD井，常规稠油井，稀油井，注水井） 联动查询
	 */
	
	public String searchCYZTEAMJH()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		//班组 变井
		String CYZ = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("CYZ")));
		String TEAM = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("TEAM")));
		String arg = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("arg")));
		
		JSONArray  ArrList = null;
		try {
			ArrList =searchQueryAllService.searchCYZTEAMJH(CYZ,TEAM,arg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	if(ArrList !=null){
		out.println(ArrList);
	}else{
		out.println("");
	}
		return null;
	}
	
	/**
	 * 获取用户所属部门所有的井号
	 * @return
	 * @throws Exception
	 */
	public String searchUserWell()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		//班组 变井
		User user = (User) session.getAttribute("userInfo");
		
		JSONArray  ArrList = null;
		try {
			ArrList =searchQueryAllService.searchUserWell(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	if(ArrList !=null){
		out.println(ArrList);
	}else{
		out.println("");
	}
		return null;
	}
	
	
	
	
}
