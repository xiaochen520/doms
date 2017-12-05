package com.echo.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;


import com.echo.dto.PcCdObservationWellT;
import com.echo.dto.PcCdOrgT;
import com.echo.dto.PcCdServerNodeT;
import com.echo.dto.PcCdWaterSourceWellT;
import com.echo.dto.PcRdLoginfoT;
import com.echo.dto.User;
import com.echo.service.CommonService;
import com.echo.service.LogService;
import com.echo.service.ObservationService;
import com.echo.service.SagdService;

import com.echo.util.AuthorityUtil;
import com.echo.util.CommonsUtil;
import com.echo.util.DateBean;
import com.echo.util.PageVariableUtil;
import com.echo.util.StringUtil;

public class ObservationAction {
	private ObservationService observationService;
	private LogService logService;
	private CommonService  commonService;
	private SagdService sagdService;
	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	
	public ObservationService getObservationService() {
		return observationService;
	}

	public void setObservationService(ObservationService observationService) {
		this.observationService = observationService;
	}
	public void setSagdService(SagdService sagdService) {
		this.sagdService = sagdService;
	}
	HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
	public String searchObservation() throws IOException {
		
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String stationNumber = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("oilstationname1")));
		String str = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("areablock1")));
		String areablock = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("blockstationname1")));
	
		String oilname = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("rulewellname1")));
		
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
		
 		JSONObject jsonobj = observationService.searchObservationWell(stationNumber,str,areablock,oilname,pageNo,sort,order,rowsPerpage);
 		if(jsonobj != null){
			out.println(jsonobj);
		}
		return null;
	}
	//根据权限进行页面初始化
	public String pageInit()throws Exception{
		
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		
		//根据用户权限生成用户权限生成页面布局格式
		User user = (User) session.getAttribute("userInfo");
		
		String gridJson = AuthorityUtil.getGridJson("观察井基础信息", user, PageVariableUtil.OBSERVATION_PAGE_GRID);
//		System.out.println(gridJson);
		out.print(gridJson);
		return null;
	}
	public String searchStructureJc() throws Exception{
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String hql="select org_id,structurename from PC_CD_ORG_T t where t.structuretype=8 ";
		 JSONArray jsonobj = observationService.searchStructureJc(hql);
			if(jsonobj != null){
				out.println(jsonobj);
			}else{
				out.println("");
			}
		
		
		return null;
	}
	
	public String addorUpdateObservation() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";

		List<PcCdObservationWellT> wellList = null;
		
		PcCdObservationWellT waterWell = null;
		waterWell = new PcCdObservationWellT();
		List<Object[]> orgList = null;
		String id = "";
		String pid = "";
		//String well_name = "";
		String blockstationname = "";
		boolean addflg = true;
		User sessionuser = (User) session.getAttribute("userInfo");
		if(request.getParameter("id") != null && !"".equals(request.getParameter("id"))){
			id = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("id")));
		}
		String well_name = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("well_name")));
		
//		try {
//			wellList = observationService.searchObservation( "",well_name);
//		} catch (Exception e) {
//			// -10910 观察井井信息获取失败
//			e.printStackTrace();
//			outCode = "-10910";
//		}
		
		//修改
		if(id != null && !"".equals(id)){
			try {
				wellList = observationService.searchObservation( id,"");
			} catch (Exception e) {
				// -10910 观察井井信息获取失败
				e.printStackTrace();
				outCode = "-10910";
			}
			//-10911 观察井名已存在请使用其他观察井名
			if(wellList != null && wellList.size()>0){
				if(!id.equals(String.valueOf(wellList.get(0).getObservationWellid()))){
					outCode = "-10911";
				}
				
			}
			waterWell = wellList.get(0);
			
		//添加	
		}else{
			//-10911 观察井名已存在请使用其他观察井名
			if(wellList != null && wellList.size()>0){
				try {
					wellList = observationService.searchObservation( "",well_name);
				} catch (Exception e) {
					// -10910 观察井井信息获取失败
					e.printStackTrace();
					outCode = "-10910";
				}
				outCode = "-10911";
			}
			//waterWell = new PcCdObservationWellT();
		}
		
		if("1".equals(outCode)){
			//获取父id注转站
			blockstationname = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("blockstationname")));
			if(blockstationname != null && !"".equals(blockstationname)){
				try {
					orgList = observationService.searchSation(blockstationname, "7");
				} catch (Exception e) {
					e.printStackTrace();
					//-11110 所属注转战信息获取失败
					outCode = "-11110";
				}
				
			}else{
				//-11115所属注转战信息未识别
				outCode = "-11115";
			}
			
		}
		if("1".equals(outCode) && orgList != null && orgList.size()>0){
			
			pid = orgList.get(0)[0].toString();
			waterWell.setWellName(well_name);
			
			//waterWell.setWellCode(StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("wellCode"))));
			waterWell.setBewellName(StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("bewell_name"))));
			waterWell.setRemark(StringUtil.toStr(request.getParameter("remark").trim()));
			waterWell.setRlastOper(sessionuser.getOperName());
			waterWell.setRlastOdate(new Date());
			
			PcCdOrgT org =  null;
			
			//修改
			if(id != null && !"".equals(id)){
				org = waterWell.getPcCdOrgT();
			}else{
				org = new PcCdOrgT();
			}
			org.setCode(StringUtil.toStr(request.getParameter("wellCode").trim()));
			
			org.setStructurename(well_name);
			String status_value = StringUtil.toStr(request.getParameter("status_value").trim());
			try {
				if(status_value != null && !"".equals(status_value)){
					status_value = commonService.getStatusValueINT(status_value);
				}
			} catch (Exception e) {
				e.printStackTrace();
				//-10010 建设状态获取失败
				outCode = "-10010";
			}
			if("1".equals(outCode)){
				org.setStatusValue(status_value);
				String commissioningDate = StringUtil.toStr(request.getParameter("commissioning_date").trim());
				if(commissioningDate != null && !"".equals(commissioningDate)){
					org.setCommissioningDate(DateBean.getStrDate(commissioningDate));
				}
				String wellAreaSelf =  StringUtil.toStr(request.getParameter("wellAreaSelf").trim());
				waterWell.setQkid(wellAreaSelf);
				PcCdServerNodeT ser = new PcCdServerNodeT(); //服务器逻辑表
				List<PcCdServerNodeT> serverlist = null;
				String scadaNode = StringUtil.toStr(request.getParameter("ljjd_s").trim());
				try {
					serverlist = sagdService.getServerNode(scadaNode);
				} catch (Exception e) {
					e.printStackTrace();
					//-10208 服务器逻辑节点信息获取失败
					outCode = "-10208";
					
				}
				if(serverlist != null && serverlist.size()>0){
					ser = serverlist.get(0);
				}else{
					//-10208 服务器逻辑节点信息获取失败
					outCode = "-10208";
				}
				org.setScadaNode(scadaNode);
				String switchInFlag = StringUtil.toStr(request.getParameter("jrbz").trim());
				org.setSwitchInFlag(switchInFlag);
				org.setStructuretype((byte) 9);
				org.setPid(pid);
				org.setPcCdObservationWellT(waterWell);
				waterWell.setPcCdOrgT(org);
				
				try {
					if(id != null && !"".equals(id)){
						addflg = observationService.UpdateObs(waterWell);
					}else{
						addflg = observationService.addObs(waterWell);
					}
				} catch (Exception e) {
					e.printStackTrace();
					if(id != null && !"".equals(id)){
//						-10912 观察井信息修改失败
						outCode = "-10912";
					}else{
//						-10913 观察井信息添加失败
						outCode = "-10913";
					}
				}
			}
			
			
		}
		
		if("1".equals(outCode) && addflg == true){
			if(id != null && !"".equals(id)){
				//添加系统LOG
				try {
					PcRdLoginfoT log = CommonsUtil.getLogInfo(sessionuser, "3", "注水撬基础信息", waterWell.getObservationWellid());
					logService.addLog(log);
				} catch (Exception e) {
					e.printStackTrace();
					outCode = "-10002";
				}
			}else{
				//添加系统LOG
				try {
					PcRdLoginfoT log = CommonsUtil.getLogInfo(sessionuser, "1", "注水撬基础信息", waterWell.getObservationWellid());
					logService.addLog(log);
				} catch (Exception e) {
					e.printStackTrace();
				
					outCode = "-10001";
				}
			}
			
			
		}
		
		out.print(outCode);
		return null;
	
		
	}

	public String queryWellInfo() throws IOException {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String boilername = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("orgid")));
		JSONArray jsonArr = null;
		try {
			jsonArr = observationService.queryWellInfo(boilername);
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
	
	public String removeObsWell() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		String obsId = StringUtil.toStr(request.getParameter("observation_wellid"));
		String orgId = StringUtil.toStr(request.getParameter("org_id"));
		boolean deleteflg = false;
		JSONObject obj = new JSONObject();
		try {
			deleteflg = observationService.removeObswell(obsId,orgId);
		} catch (Exception e) {
			String errmsg = e.getCause().toString();
			//ORA-02292: 违反完整约束条件 (NEWPORTAL.FK58_PC_RPD_WELL_SAGDD_T) - 已找到子记录
			if(!"".equals(errmsg) && errmsg.indexOf("ORA-02292") != -1 && errmsg.indexOf("FK58_PC_RPD_WELL_SAGDD_T") != -1){
				obj = CommonsUtil.getRrturnJson("","观察井基础信息删除失败-观察井日报中存在子记录" ,"", null, null);
			}else if(!"".equals(errmsg) && errmsg.indexOf("ORA-02292") != -1 && errmsg.indexOf("FK_PC_RPD_W_FK52_PC_R_PC_CD_WE") != -1){
				obj = CommonsUtil.getRrturnJson("","观察井基础信息删除失败-观察井班报中存在子记录" ,"", null, null);
			} else{
				obj = CommonsUtil.getRrturnJson("",errmsg ,"", null, null);
			}
			e.printStackTrace();
		}
		
		if(deleteflg){
			
			//添加系统LOG
			try {
				User user1 = (User) session.getAttribute("userInfo");
				PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "2", "观察井基础信息", obsId);
				logService.addLog(log);
			} catch (Exception e) {
				e.printStackTrace();
				obj = CommonsUtil.getRrturnJson("-10003", "","", null, null);
			}
			
			
		}
		out.print(obj);
		return null;
	}
	
	public String cascadeInit() throws Exception {
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONObject jsonArr = null;
		try {
			jsonArr = observationService.cascadeInit();
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
}
