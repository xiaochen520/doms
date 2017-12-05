package com.echo.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.echo.dto.PcCdRightT;
import com.echo.dto.PcCdRoleT;
import com.echo.dto.PcRdLoginfoT;
import com.echo.dto.User;
import com.echo.service.LogService;
import com.echo.service.RoleService;
import com.echo.service.UserService;
import com.echo.util.AuthorityUtil;
import com.echo.util.CommonsUtil;
import com.echo.util.PageVariableUtil;
import com.echo.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

public class RoleAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4154345270195887462L;
	private UserService userService;
	private RoleService roleService;
	private LogService logService;
	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
	
	/**
	 * 用于接收前台传入的参数 SET AND GET
	 */
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}



	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	//根据权限进行页面初始化
	public String pageInit()throws Exception{
		
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		//根据用户权限生成用户权限生成页面布局格式
		User user = (User) session.getAttribute("userInfo");
		
		String gridJson = null;
		try {
			gridJson = AuthorityUtil.getGridJson("角色信息管理", user, PageVariableUtil.ROLEINFO_PAGE_GRID);
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
	
	//获取可设置权限菜单
	public String getright() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String roleId = StringUtil.toStr(request.getParameter("roleID"));
		
		return null;
	}

	public String addRole() throws IOException {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		PcCdRoleT role = new PcCdRoleT();
		User userinfo = (User) session.getAttribute("userInfo");
		//"role_name":addname ,"role_described":updatdes,"remark":addremarke
		String role_name =  StringUtil.toStr(request.getParameter("role_name").trim());
		List<PcCdRoleT> roleList = null;
		try {
			roleList = roleService.searchRole("", role_name);
		} catch (Exception e) {
			e.printStackTrace();
			outCode = "-15902";
		}
		if(roleList != null && roleList.size()>0){
			outCode = "-15907";
		}
		
		if("1".equals(outCode)){
			//角色
			role.setRoleName(role_name);
			//角色详情
			role.setRoleDescribed(StringUtil.toStr(request.getParameter("role_described").trim()));
			//备注
			role.setRemark(StringUtil.toStr(request.getParameter("remark").trim()));
			//修改人
			role.setRlastOper(userinfo.getOperName());
			//角色
			role.setRlastOdate(new Date());
			//String roles =  StringUtil.toStr(request.getParameter("roles"));
			
			boolean addflg = true;
			
			try {
				addflg = roleService.addRole(role);
			} catch (Exception e) {
				e.printStackTrace();
				outCode = "-15902";
			}
			if("1".equals(outCode) && addflg== true){
				//添加系统LOG
				try {
					PcRdLoginfoT log = CommonsUtil.getLogInfo(userinfo, "1", "角色信息管理", role.getRoleid());
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
	
	public String updateRole() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象

		String outCode = "1";
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		User userinfo = (User) session.getAttribute("userInfo");
//		TbRole role = new TbRole();
		List<PcCdRoleT> brole = null;
		List<PcCdRoleT> namebrole = null;
		String roleid = request.getParameter("roleid").trim();
		String role_name = StringUtil.toStr(request.getParameter("role_name").trim());
		try {
			brole = roleService.searchRole(roleid,"");
			namebrole = roleService.searchRole("",role_name);
		} catch (Exception e) {
			e.printStackTrace();
			outCode = "-15901";
		}
		
		PcCdRoleT role = null;
		if(brole != null && brole.size()>0){
			role = brole.get(0);
		}else{
			outCode = "-15901";
		}
		
		if(namebrole != null && namebrole.size()>0){
			
			if(!roleid.equals(namebrole.get(0).getRoleid())){
				outCode = "-15907";
			}
		}
		
		if("1".equals(outCode)){
			//角色名字
			role.setRoleName(role_name);
			//角色详情
			role.setRoleDescribed(StringUtil.toStr(request.getParameter("role_described").trim()));
			//备注
			role.setRemark(StringUtil.toStr(request.getParameter("remark").trim()));
			//修改人
			role.setRlastOper(userinfo.getOperName());

			//角色
			role.setRlastOdate(new Date());
			
			boolean addflg = true;
			
			try {
				addflg = roleService.updateRole(role);
			} catch (Exception e) {
				e.printStackTrace();
				outCode = "-15903";
			}
			
			
			if("1".equals(outCode) && addflg == true){
				//添加系统LOG
				try {
					PcRdLoginfoT log = CommonsUtil.getLogInfo(userinfo, "3", "角色信息管理", role.getRoleid());
					logService.addLog(log);
				} catch (Exception e) {
					e.printStackTrace();
					outCode = "-10002";
				}
				
			}
		}
		
		out.print(outCode);
		return null;
	}
	
	public String seachRole() throws Exception {
			HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
			HttpServletResponse response=ServletActionContext.getResponse();//响应对象
			response.setCharacterEncoding("utf-8");
			response.setHeader("ContentType","text/xml");
			PrintWriter out = response.getWriter();
			String	rolename =  "";
			String outCode = "1";
			if(request.getParameter("selectrole") != null){
				rolename = StringUtil.toStr(request.getParameter("selectrole").trim());	
				
			}
			// user.setUserPhoto(name)
            JSONArray jsonArr = null;
//            List<PcCdRoleT> rolelist = null;
            List<Object[]> rolelist = null;
            try {
//            	rolelist = roleService.searchRole("",rolename);
            	rolelist = roleService.searchRole(rolename);
			} catch (Exception e) {
				e.printStackTrace();
				outCode = "-15901";
			}
            
            if(rolelist != null && rolelist.size()>0){

    			
    			//生成树节点json文档
    			jsonArr = new JSONArray();   
    			
//    			for(PcCdRoleT entry  : rolelist){
    			for(int i = 0; i< rolelist.size();i++){
    				JSONObject tjo = new JSONObject();
    				tjo.put("ROLEID", rolelist.get(i)[0]);
    				tjo.put("ROLE_NAME",rolelist.get(i)[1]);
    				if(rolelist.get(i)[2] != null){
    					tjo.put("ROLE_DESCRIBED", rolelist.get(i)[2]);
    				}else{
    					tjo.put("ROLE_DESCRIBED", "");
    				}
    				if(rolelist.get(i)[4] != null ){
    					tjo.put("RLAST_ODATE",String.valueOf(rolelist.get(i)[5]));
    				}else{
    					tjo.put("RLAST_ODATE","");
    				}
    				if(rolelist.get(i)[5]!= null){
    					tjo.put("RLAST_OPER",rolelist.get(i)[4]);
    				}else{
    					tjo.put("RLAST_OPER","");
    				}
    				if(rolelist.get(i)[3] != null){
    					tjo.put("REMARK", rolelist.get(i)[3]);
    				}else{
    					
    					tjo.put("REMARK", "");
    				}
    				
    				jsonArr.add(tjo);
    				
    			}
            	
            }
			if(jsonArr != null &&  "1".equals(outCode)){
				out.println(jsonArr);
			}else{
				out.print(outCode);
			}
			
		return null;
	}
	
	
	public String addOneUser() throws IOException {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		
		String userId = StringUtil.toStr(request.getParameter("userID"));
		
		User user = userService.searchUserById(userId);
		
		request.setAttribute("userinfo", user);
		
		return "adduserinfo";
	}
	
	public String searchOneUser() throws IOException {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		
		String userId = StringUtil.toStr(request.getParameter("userID"));
		User user = userService.searchUserById(userId);
		
		request.setAttribute("userinfo", user);
		
			
		return "userview";
		}
	public String accredit1() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		JSONArray roleJson = null;
		String roleId = StringUtil.toStr(request.getParameter("roleID"));
		if(roleId != null && !"".equals(roleId)){
			
			try {
				roleJson = roleService.searchRoleInfo(roleId);
			} catch (Exception e) {
				e.printStackTrace();
				outCode = "-16901";
			}
			
		}else{
			outCode = "-15904";
		}
		
		if(roleJson != null && "1".equals(outCode)){
			out.print(roleJson);
		}else{
			out.print(outCode);
		}
		return null;
	}
	
	//组织树形结构权限授权
	public String RoleAccredit() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		JSONArray roleJson = null;
		String roleId = StringUtil.toStr(request.getParameter("roleID"));
		if(roleId != null && !"".equals(roleId)){
			
			try {
				roleJson = roleService.RoleAccredit(roleId);
			} catch (Exception e) {
				e.printStackTrace();
				outCode = "-16901";
			}
			
		}else{
			outCode = "-15904";
		}
		
		if(roleJson != null && "1".equals(outCode)){
			out.print(roleJson);
		}else{
			out.print(outCode);
		}
		return null;
	}
	
	public String accredit() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		JSONObject roleJson = null;
		String roleId = StringUtil.toStr(request.getParameter("roleID"));
		if(roleId != null && !"".equals(roleId)){
			
			try {
				roleJson = roleService.searchAccRoles(roleId);
			} catch (Exception e) {
				e.printStackTrace();
				outCode = "-16901";
			}
			
		}else{
			outCode = "-15904";
		}
		
		if(roleJson != null && "1".equals(outCode)){
			out.print(roleJson);
		}else{
			out.print(outCode);
		}
		return null;
	}
	
	public String accreditDone() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		List<PcCdRightT> rights = new ArrayList<PcCdRightT>();
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		List<PcCdRoleT> brole = null;
		try {
			brole = roleService.searchRole(request.getParameter("roleid").trim(),"");
		} catch (Exception e) {
			e.printStackTrace();
			outCode = "-15901";
			
		}
		
		if("1".equals(outCode)){
			PcCdRoleT role = null;
			if(brole != null && brole.size()>0){
				role = brole.get(0);
			}else{
				role = new PcCdRoleT();
				role.setRoleid(StringUtil.toStr(request.getParameter("roleid").trim()));
			}
	//
			//选择权限
			String chkrole =  StringUtil.toStr(request.getParameter("chkrole"));
			if(chkrole != null && !"".equals(chkrole)){
				chkrole = chkrole.substring(0, chkrole.length()-1);
			}
			boolean addflg = true;
			Set<PcCdRightT> tbRights = new HashSet<PcCdRightT>(0);
			//添加角色
			if(chkrole != null && !"".equals(chkrole)){
				String[] right = chkrole.split(",");
				//
				try {
					rights = roleService.searchRightByIds(right);
				} catch (Exception e) {
					e.printStackTrace();
					outCode = "-16901";
				}
				
			}
			
			if("1".equals(outCode)){
				if(rights != null && rights.size()>0){
					for(PcCdRightT tr : rights){
						tr.getPcCdRoleTs().add(role);
						tbRights.add(tr);
						
					}
				}
				role.setPcCdRightTs(tbRights);
				try {
					addflg = roleService.updateRole(role);
				} catch (Exception e) {
					e.printStackTrace();
					outCode = "-15905";
				}
				if("1".equals(outCode) && addflg == true){
					//添加系统LOG
					User userinfo = (User) session.getAttribute("userInfo");
					PcRdLoginfoT log = CommonsUtil.getLogInfo(userinfo, "3", "角色信息管理", role.getRoleid());
					try {
						logService.addLog(log);
					} catch (Exception e) {
						e.printStackTrace();
						outCode = "-10002";
					}
				}
			}
		}
		
		out.print(outCode);
		return null;
	}
//	
//	public String roleChecked() throws IOException {
//		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
//		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
//		response.setCharacterEncoding("utf-8");
//		response.setHeader("ContentType","text/xml");
//		PrintWriter out = response.getWriter();
//		String rolename = request.getParameter("rolename");
//		if(rolename != null && !"".equals(rolename)){
//			JSONArray roleJson = roleService.searchRoleInfo(rolename,"");
//			if(roleJson != null){
//				out.print(roleJson);
//			}else{
//				out.print("");
//			}
//		}
//		
//		return null;
//	}
//	
//	public String getAllRight() throws IOException {
//		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
//		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
//		response.setCharacterEncoding("utf-8");
//		response.setHeader("ContentType","text/xml");
//		PrintWriter out = response.getWriter();
//		JSONArray roleJson = roleService.searchAllRight();
//		out.print(roleJson);
//		return null;
//	}
	public String removeRole() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		String roleId = StringUtil.toStr(request.getParameter("roleid").trim());
		
		boolean deleteflg = true;
		try {
			deleteflg = roleService.removeUser(roleId);
		} catch (Exception e) {
			e.printStackTrace();
			outCode = "-15905";
		}
		
		
		if(deleteflg){
			//添加系统LOG
			try {
				User userinfo = (User) session.getAttribute("userInfo");
				PcRdLoginfoT log = CommonsUtil.getLogInfo(userinfo, "2", "角色信息管理", roleId);
				logService.addLog(log);
			} catch (Exception e) {
				e.printStackTrace();
				outCode = "-10003";
			}
			
		}else{
			outCode = "-15906";
		}
		
		out.print(outCode);
		return null;
	}
//	
//	
//	public String getUserRoles() throws IOException {
//		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
//		response.setCharacterEncoding("utf-8");
//		response.setHeader("ContentType","text/xml");
//		PrintWriter out = response.getWriter();
//		
//		 JSONArray jsonobj = userService.searchUserRole();
//			if(jsonobj != null){
//				out.println(jsonobj);
//			}else{
//				out.println("");
//			}
//		
//		
//		return null;
//	}
	

}
