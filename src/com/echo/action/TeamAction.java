package com.echo.action;

import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.echo.dao.CommonDao;
import com.echo.dto.PcCdDepartmentT;
import com.echo.dto.PcCdOrgT;
import com.echo.dto.PcCdTeamT;
import com.echo.dto.PcRdLoginfoT;
import com.echo.dto.User;
import com.echo.service.LogService;
import com.echo.service.TeamService;
import com.echo.util.AuthorityUtil;
import com.echo.util.CommonsUtil;
import com.echo.util.PageVariableUtil;
import com.echo.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

public class TeamAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4154345270195887433L;
	private TeamService teamService;
	private LogService logService;
	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	
	public void setTeamService(TeamService teamService) {
		this.teamService = teamService;
	}


	
	
	/**
	 * 用于接收前台传入的参数 SET AND GET
	 */

	//根据权限进行页面初始化
	public String pageInit()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
		//根据用户权限生成用户权限生成页面布局格式
		User user = (User) session.getAttribute("userInfo");
		//获取用户页面权限
		JSONObject tjo = new JSONObject();
		
		try {
			String grids = AuthorityUtil.getGridJson1("MENU017", user,PageVariableUtil.TEAM_PAGE_GRID);
			tjo.put("GRIDS", grids);
		} catch (Exception e) {
			e.printStackTrace();
			outCode = "-10004";
		}
		
		if("1".equals(outCode)){
			out.print(tjo);
		}else{
			out.print(outCode);
		}
		return null;
		
	}

	
	public String seachDeps() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String	username =""; //用戶名
		String	department =""; //部门
		int pageNo = 1; //页索引参数名当前页
		String sort = "";		//页排序列名
		String order = "";//页排序方向
		int rowsPerpage = 0; //每页显示条数
		String outCode = "1";
		if(request.getParameter("depname") != null){
			department = StringUtil.toStr(request.getParameter("depname").trim());	
		}
		
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

		
		JSONObject jsonobj = null;
		try {
			jsonobj = teamService.searchDeps(department,pageNo,sort,order,rowsPerpage);
		} catch (Exception e) {
			e.printStackTrace();
			outCode = "-10005";
		}
		
		if(jsonobj != null && "1".equals(outCode)){
			out.println(jsonobj);
			
		}else{
			out.println(outCode);
		}
		
	return null;
}
	

	public String addOrUpdateDep() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
		User sessionuser = (User) session.getAttribute("userInfo");
		JSONObject obj = new JSONObject();
		List<PcCdTeamT> teamList = null;
		PcCdTeamT team = null;
		PcCdOrgT org = null;
		//data: {"teamname":teamname1 ,"orgname":orgname1,"bz":bz1 ,"teamid":teamid1,"orgid":orgid1},
		String teamname = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("teamname")));
		String orgname = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("orgname")));
		String bz = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("bz")));
		String teamid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("teamid")));
		String cyzid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("cyzid")));
		
		if(cyzid != null && !"".equals(cyzid) && orgname != null && !"".equals(orgname)){
			
		}else{
			obj = CommonsUtil.getRrturnJson("","操作失败 请选择所属机构" ,"", null, null);
			out.print(obj);
			return null;
			
		}
		
		if(teamname != null && !"".equals(teamname)){
			
			teamList = teamService.searchTea("", teamname);
			
			if(teamList != null && teamList.size()>1){
				obj = CommonsUtil.getRrturnJson("","操作失败 井组名称已存在，请输入其他井组名称" ,"", null, null);
				out.print(obj);
				return null;
			}else if(teamList != null && teamList.size()==1){
				team = teamList.get(0);
				if(!teamid.equals(team.getTeamId())){
					obj = CommonsUtil.getRrturnJson("","操作失败 井组名称已存在，请输入其他井组名称" ,"", null, null);
					out.print(obj);
					return null;
				}
			}
		}else{
			obj = CommonsUtil.getRrturnJson("","操作失败 井组名称不为空" ,"", null, null);
			out.print(obj);
			return null;
			
		}
		
		//修改
		if(teamid != null && !"".equals(teamid)){
			
			try {
				teamList = teamService.searchTea(teamid, "");
				
				if(teamList != null && teamList.size()>0){
					team = teamList.get(0);
					org = team.getPcCdOrgT();
				}else{
					obj = CommonsUtil.getRrturnJson("","班组ID数据获取失败,请联系管理员" ,"", null, null);
					out.print(obj);
					return null;
				}
			} catch (Exception e) {
				e.printStackTrace();
				obj = CommonsUtil.getRrturnJson("","班组数据获取失败" ,"", null, null);
				out.print(obj);
				return null;
			}
			
		}else{
			team = new PcCdTeamT();
			org = new PcCdOrgT();
		}
		
		
		team.setTeam(teamname);
		team.setRlastOper(sessionuser.getOperName());
		team.setRlastOdate(new Date());
		team.setRemark(bz);
		org.setStructurename(teamname);
		org.setPid(cyzid);
		org.setStructuretype((byte)15);
		org.setPcCdTeamT(team);
		team.setPcCdOrgT(org);
		boolean addflg = false;
		try {
			addflg = teamService.addOrupdateTeam(team);
		} catch (Exception e) {
			e.printStackTrace();
			obj = CommonsUtil.getRrturnJson("","班组数据更新失败" ,"", null, null);
			out.print(obj);
			return null;
		}
		
		if(addflg == true){
			if(teamid != null && !"".equals(teamid)){
				//更新系统LOG
				try {
					PcRdLoginfoT log = CommonsUtil.getLogInfo(sessionuser, "3", "班组基础信息", teamid);
					logService.addLog(log);
				} catch (Exception e) {
					e.printStackTrace();
					obj = CommonsUtil.getRrturnJson("","更新班组数据LOG失败" ,"", null, null);
					out.print(obj);
					return null;
				}
			}else{
				//添加系统LOG
				try {
					PcRdLoginfoT log = CommonsUtil.getLogInfo(sessionuser, "1", "班组基础信息", team.getTeamId());
					logService.addLog(log);
				} catch (Exception e) {
					e.printStackTrace();
					obj = CommonsUtil.getRrturnJson("","添加班组数据LOG失败" ,"", null, null);
					out.print(obj);
					return null;
				}
				
			}
		}
		out.print(obj);
		return null;
	}

	public String removeDep() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
		String outCode = "1";
		String depid = StringUtil.toStr(request.getParameter("depid").trim());
		String org_id = StringUtil.toStr(request.getParameter("orgid").trim());
		
		boolean deleteflg = true;
		JSONObject obj = new JSONObject();
		try {
			deleteflg = teamService.removeTeam(depid,org_id);
		} catch (Exception e) {
			String errmsg = e.getCause().toString();
			//ORA-02292: 违反完整约束条件 (NEWPORTAL.FK58_PC_RPD_WELL_SAGDD_T) - 已找到子记录
			if(!"".equals(errmsg) && errmsg.indexOf("ORA-02292") != -1 && errmsg.indexOf("FK58_PC_RPD_WELL_SAGDD_T") != -1){
				obj = CommonsUtil.getRrturnJson("","部门基础信息删除失败-部门日报中存在子记录" ,"", null, null);
			}else if(!"".equals(errmsg) && errmsg.indexOf("ORA-02292") != -1 && errmsg.indexOf("FK_PC_RPD_W_FK52_PC_R_PC_CD_WE") != -1){
				obj = CommonsUtil.getRrturnJson("","部门基础信息删除失败-部门班报中存在子记录" ,"", null, null);
			} else{
				obj = CommonsUtil.getRrturnJson("",errmsg ,"", null, null);
			}
			e.printStackTrace();
		}
		
		if("1".equals(outCode) && deleteflg == true){
			
			//添加系统LOG
			try {
				User user1 = (User) session.getAttribute("userInfo");
				PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "2", "部门基础信息", depid);
				logService.addLog(log);
			} catch (Exception e) {
				e.printStackTrace();
				obj = CommonsUtil.getRrturnJson("-10003", "", "",null, null);
			}
			
			
		}
		
		out.print(obj);
		return null;
	}
	

}
