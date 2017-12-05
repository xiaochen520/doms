package com.echo.action;

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

import com.echo.dto.PcCdMenuT;
import com.echo.dto.PcCdRoleT;
import com.echo.dto.PcCdUserT;
import com.echo.dto.PcRdLoginfoT;
import com.echo.dto.User;
import com.echo.service.DepartmentService;
import com.echo.service.LogService;
import com.echo.service.RoleService;
import com.echo.service.UserService;
import com.echo.util.AuthorityUtil;
import com.echo.util.CommonsUtil;
import com.echo.util.PageVariableUtil;
import com.echo.util.StringUtil;
import com.echo.util.SystemUtil;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4154345270195887462L;
	private UserService userService;
	private RoleService roleService;
	@SuppressWarnings("unused")
	private DepartmentService departmentService;
	private LogService logService;
	
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}



	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
	
	/**
	 * 用于接收前台传入的参数 SET AND GET
	 */


	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	//根据权限进行页面初始化
	@SuppressWarnings("unused")
	public String pageInit()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		
		//根据用户权限生成用户权限生成页面布局格式
		User user = (User) session.getAttribute("userInfo");
		//获取用户页面权限
		String outCode = "1";
		
		JSONObject tjo = new JSONObject();
		
		try {
			
//			tjo.put("USERNAMES", userobj);
			String grids = AuthorityUtil.getGridJson("操作员信息管理", user,PageVariableUtil.USERINFO_PAGE_GRID);
			//String rights = AuthorityUtil.PageAuthority(user, "操作员信息管理");
			tjo.put("GRIDS", grids);
		} catch (Exception e) {
			e.printStackTrace();
			outCode = "-10004";
		}
		
		if(tjo != null && "1".equals(outCode)){
			out.print(tjo);
		}else{
			out.print(outCode);
		}
		return null;
		
	}
	public String adduser() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		PcCdUserT user = null;
		boolean addflg = true;
		String outCode = "1";
		User sessionuser = (User) session.getAttribute("userInfo");
		user = new PcCdUserT();
		//注册时间
		user.setAudbshorDate(new Date());
		//名字
		String opercode = StringUtil.toStr(request.getParameter("oper_code").trim());
		user.setOperCode(opercode); 
		List<Object[]> userl = null;
		try {
			userl = userService.searchUserChick(opercode);
		} catch (Exception e) {
			e.printStackTrace();
			outCode= "-15801";
		}
		
		if("1".equals(outCode)){
			if(userl != null && userl.size() >0){
				outCode= "-15806";
			}else{
				
				//真实名字
				user.setOperName(StringUtil.toStr(request.getParameter("oper_name").trim())); 
				//密码
				String password = StringUtil.toStr(request.getParameter("oper_pass").trim());
				if(password != null && password.length() != 32){
					user.setOperPass(SystemUtil.md5(password));
				}else{
					user.setOperPass(password);
				}
				//部门
				String depid = StringUtil.toStr(request.getParameter("depid").trim());
				String depname = StringUtil.toStr(request.getParameter("depname").trim());
				List<Object[]> deplist = null;
				try {
					if(depid != null && !"".equals(depid) && depname != null && !"".equals(depname)){
						String sql = "select * from pc_cd_org_t t where t.org_id='"+depid+"' and t.structurename ='"+depname+"'";
						deplist = userService.searchDatas(sql);
						if(deplist == null || deplist.size() < 1 ){
							outCode = "-11706";
						}
						
					}else{
						
					}
					
				} catch (Exception e) {
					e.printStackTrace();
					outCode = "-11706";
				}
				
				
				if("1".equals(outCode)){
						user.setDepartmentid(depid);	
					
					//当前状态
					user.setOperSdbsadbse(StringUtil.toStr(request.getParameter("oper_sdbsadbse").trim()));
					//备注
					user.setRemark(StringUtil.toStr(request.getParameter("remark").trim()));
					//修改人
					user.setRlastOper(sessionuser.getOperName());
					//修改时间
					user.setRlastOdate(new Date());
					//角色
					String roles =  StringUtil.toStr(request.getParameter("roles").trim());
					//添加角色
					List<PcCdRoleT> rolelist = null;
					Set<PcCdRoleT> tbRole = new HashSet<PcCdRoleT>(0);
					if(roles != null && !"".equals(roles)){
						String[] roleids = roles.split(";");
						if(roleids != null && roleids.length >0){
							for(String roleid : roleids){
								rolelist = new ArrayList<PcCdRoleT>();
								try {
									rolelist = roleService.searchRole(roleid,"");
								} catch (Exception e) {
									e.printStackTrace();
									outCode = "-15901";
								}
								
								if(rolelist != null && rolelist.size() >0){
									
									for(PcCdRoleT tr : rolelist){
										tr.getPcCdUserTs().add(user);
										tbRole.add(tr);
									}
								}
								
							}
						}
					}
					user.setPcCdRoleTs(tbRole);
					if("1".equals(outCode)){
						try {
							addflg = userService.addUser(user);
						
						} catch (Exception e) {
							e.printStackTrace();
							outCode = "-15805";
						}
						if("1".equals(outCode) && addflg == true){
							//添加系统LOG
							try {
								PcRdLoginfoT log = CommonsUtil.getLogInfo(sessionuser, "1", "用户信息管理", user.getUserid());
								logService.addLog(log);
							} catch (Exception e) {
								e.printStackTrace();							
								outCode = "-10001";
							}							
						}
					}					
				}			
			}			
		}
		out.print(outCode);
		return null;
	}
	
	public String seachUser() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		int pageNo = 1; //页索引参数名当前页
		String sort = "";		//页排序列名
		String order = "";//页排序方向
		int rowsPerpage = 0; //每页显示条数
		String outCode = "1";
		
		String username = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("username")));
		String depname = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("depname")));

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
			jsonobj = userService.searchUserByName(depname,username,pageNo,sort,order,rowsPerpage);
		} catch (Exception e) {
			e.printStackTrace();
			outCode = "-15801";
		}
		
		if(jsonobj != null && "1".equals(outCode)){
			out.println(jsonobj);
			
		}else{
			out.println(outCode);
		}
		
	return null;
}
	

	public String updateUser() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String oper_code = StringUtil.toStr(request.getParameter("oper_code").trim());
		String userid = request.getParameter("userid").trim();
		String outCode = "1";
		List<PcCdUserT> userList = null;
		List<Object[]> nameList = null;
		try {
			userList = (List<PcCdUserT>) userService.searchUser(userid);
			nameList = userService.searchUserChick(oper_code);
		} catch (Exception e1) {
			e1.printStackTrace();
			outCode = "-15802";
		}
		boolean addflg = true;
		PcCdUserT user = null;
		User sessionuser = (User) session.getAttribute("userInfo");
		if(nameList != null && nameList.size()>0){
			if(!userid.equals(nameList.get(0)[1])){
				outCode = "-15806";
			}
		}
		
		if("1".equals(outCode)){
			if(userList != null && userList.size()>0){
				user = userList.get(0);
				//注册时间
				user.setAudbshorDate(new Date());
				//名字
				user.setOperCode(oper_code); 
				//真实名字
				user.setOperName(StringUtil.toStr(request.getParameter("oper_name").trim())); 
				//密码
				String password = StringUtil.toStr(request.getParameter("oper_pass").trim());
				if(password != null && password.length() != 32){
					user.setOperPass(SystemUtil.md5(password));
				}else{
					user.setOperPass(password);
				}
				//部门
				String depid = StringUtil.toStr(request.getParameter("depid").trim());
				String depname = StringUtil.toStr(request.getParameter("depname").trim());
				List<Object[]> deplist = null;
				try {
					if(depid != null && !"".equals(depid) && depname != null && !"".equals(depname)){
						String sql = "select * from pc_cd_org_t t where t.org_id='"+depid+"' and t.structurename ='"+depname+"'";
						deplist = userService.searchDatas(sql);
						if(deplist == null || deplist.size() < 1 ){
							outCode = "-11706";
						}
						
					}else{
						
					}
					
				} catch (Exception e) {
					e.printStackTrace();
					outCode = "-11706";
				}
				
				
				if("1".equals(outCode)){
						user.setDepartmentid(depid);	
					
					//当前状态
					user.setOperSdbsadbse(StringUtil.toStr(request.getParameter("oper_sdbsadbse").trim()));
					//备注
					user.setRemark(StringUtil.toStr(request.getParameter("remark").trim()));
					//修改人
					user.setRlastOper(sessionuser.getOperName());
					//修改时间
					user.setRlastOdate(new Date());
					//角色
					String roles =  StringUtil.toStr(request.getParameter("roles").trim());
					//添加角色
					List<PcCdRoleT> rolelist = null;
					Set<PcCdRoleT> tbRole = new HashSet<PcCdRoleT>(0);
					if(roles != null && !"".equals(roles)){
						String[] roleids = roles.split(";");
						if(roleids != null && roleids.length >0){
							for(String roleid : roleids){
								rolelist = new ArrayList<PcCdRoleT>();
								try {
									rolelist = roleService.searchRole("",roleid);
								} catch (Exception e) {
									e.printStackTrace();
									outCode = "-15901";
								}
								
								if(rolelist != null && rolelist.size() >0){
									
									for(PcCdRoleT tr : rolelist){
										tr.getPcCdUserTs().add(user);
										tbRole.add(tr);
									}
								}
								
							}
						}
					}
					user.setPcCdRoleTs(tbRole);
					if("1".equals(outCode)){
						try {
							addflg = userService.updateUser(user);
						} catch (Exception e) {
							e.printStackTrace();
							outCode = "-15803";
						}
					}
					
					
				}
				
				if("1".equals(outCode) && addflg == true){
					//添加系统LOG
					try {
						PcRdLoginfoT log = CommonsUtil.getLogInfo(sessionuser, "3", "用户信息管理", user.getUserid());
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
	
	
	public String updatepassword() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String oldpassword = StringUtil.toStr(request.getParameter("oldpassword").trim());
		String newpassword = SystemUtil.md5(StringUtil.toStr(request.getParameter("newpassword").trim()));
		User user1 = (User) session.getAttribute("userInfo");
		if(SystemUtil.md5(oldpassword).equals(user1.getOperPass())){
			
			List<PcCdUserT> userList = null;
			try {
				userList = (List<PcCdUserT>) userService.searchUser(user1.getUserid());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if(userList != null && userList.size()>0){
				
				PcCdUserT u = userList.get(0);
				u.setOperPass(newpassword);
				user1.setOperPass(newpassword);
				boolean flg = userService.updateUser(u);
				

				if(flg){
					//添加系统LOG
					PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "3", "用户信息管理", u.getUserid());
					logService.addLog(log);
					user1.setOperPass(newpassword);
					session.setAttribute("userInfo", user1);
					out.print("1");
				}else{
					out.print("-1");
				}
			}
		//原始密码不正确
		}else{
			out.print("-2");
		}
		
		
		return null;
	}
	public String removeUser() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		String userId = StringUtil.toStr(request.getParameter("userID").trim());
		boolean deleteflg = true;
		
		try {
			deleteflg = userService.removeUser(userId);
		} catch (Exception e) {
			e.printStackTrace();
			outCode = "-15804";
		}
		
		if(deleteflg){
			
			//添加系统LOG
			try {
				User user1 = (User) session.getAttribute("userInfo");
				PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "2", "用户信息管理", userId);
				logService.addLog(log);
			} catch (Exception e) {
				e.printStackTrace();
				outCode = "-10003";
			}
		}
		out.print(outCode);
		return null;
	}
	
	@SuppressWarnings("unused")
	public String Unlock() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		String userId = StringUtil.toStr(request.getParameter("userID").trim());
		int  unlockflg = 0;
		JSONObject obj = new JSONObject();
		try {
			unlockflg = AuthorityUtil.Unlock(userId);
		} catch (Exception e) {
			e.printStackTrace();
			obj = CommonsUtil.getRrturnJson("","解锁失败" ,"", null, null);
		}
		
		if(unlockflg >0){
			
			//添加系统LOG
			try {
				User user1 = (User) session.getAttribute("userInfo");
				PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "解锁", "用户信息管理", userId);
				logService.addLog(log);
			} catch (Exception e) {
				e.printStackTrace();
				outCode = "-10003";
			}
		}
		out.print(obj);
		return null;
	}
//	
//	
	/**
	 * 获取权限下拉框数据
	 */
	public String getUserRoles() throws Exception {
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		 JSONArray jsonobj = null;
		try {
			jsonobj = userService.searchUserRole();
		} catch (Exception e) {
			e.printStackTrace();
			outCode = "-15901";
			
		}
		
		if(jsonobj != null && "1".equals(outCode)){
			out.println(jsonobj);
		}else{
			out.println("");
		}
		
		
		return null;
	}
	
	/**
	 * 获取部门下拉框数据
	 */
	public String getUserDep() throws Exception {
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		 JSONArray jsonobj = null;
		try {
			jsonobj = userService.searchUserDep();
		} catch (Exception e) {
			e.printStackTrace();
			outCode = "-11706";
			
		}
			if(jsonobj != null && "1".equals(outCode)){
				out.println(jsonobj);
			}else{
				out.println("");
			}
		
		
		return null;
	}
	
	
	/**
	 * 已不用
	 * @return
	 * @throws Exception
	 */
	public String getMenuNameJudge() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		
		
		String menuId = StringUtil.toStr(request.getParameter("menuid").trim());
		String nodeType = StringUtil.toStr(request.getParameter("datatype").trim());
		String returnStr = "";
		String outStr ="SESSCUSS";
		User user = (User) session.getAttribute("userInfo");
		List<PcCdMenuT> menulist = user.getMenuList();
		
		if(menulist != null && menulist.size()>0){
			for(PcCdMenuT menu :menulist){
				
				if(menuId.equals(menu.getMenuid())){
					returnStr = menu.getRightName();
					
				}
				
			}
			
		}
		//SAGD井基础信息
		if("SAGD井基础信息".equals(returnStr) || "常规油井基础信息".equals(returnStr) || "稀油油井基础信息".equals(returnStr)){
			
			if(!"4".equals(nodeType) && !"7".equals(nodeType) && !"8".equals(nodeType) && !"9".equals(nodeType)){
				
				outStr = "当前页面为-"+returnStr+":请选择采油站、注转站、管汇或井口数据进行查询";
			}
			
		}else if("气井基础信息".equals(returnStr)){
			
			if(!"4".equals(nodeType) && !"12".equals(nodeType) && !"9".equals(nodeType)){
				
				outStr = "当前页面为-"+returnStr+":请选择采油站、气站或井口数据进行查询";
			}	
		}else if("水源井基础信息".equals(returnStr)){
			if(!"5".equals(nodeType) && !"11".equals(nodeType) && !"9".equals(nodeType)){
				
				outStr = "当前页面为-"+returnStr+":请选择联合站、处理站或井口数据进行查询";
			}	
		}else if("注水撬基础信息".equals(returnStr)){
			if(!"4".equals(nodeType) && !"13".equals(nodeType)){
				outStr = "当前页面为-"+returnStr+":请选择采油站或注水撬数据进行查询";
			}	
			
		}else if("注水井基础信息".equals(returnStr)){
			if(!"4".equals(nodeType) && !"13".equals(nodeType) && !"9".equals(nodeType)){
				outStr = "当前页面为-"+returnStr+":请选择采油站、注水撬或井口数据进行查询";
			}
		}else if("观察井基础信息".equals(returnStr)){
			if(!"4".equals(nodeType) && !"7".equals(nodeType) && !"9".equals(nodeType)){
				outStr = "当前页面为-"+returnStr+":请选择采油站、注转站或井口数据进行查询";
			}
		}else if("联合站基础信息".equals(returnStr)){
			if(!"5".equals(nodeType)){
				outStr = "当前页面为-"+returnStr+":请选择联合站数据进行查询";
			}
		}else if("注转站基础信息".equals(returnStr)){
			if(!"4".equals(nodeType) && !"5".equals(nodeType) && !"7".equals(nodeType)){
				outStr = "当前页面为-"+returnStr+":请选择采油站、联合站或注转站数据进行查询";
			}
		}else if("采油站基础信息".equals(returnStr)){
			if(!"4".equals(nodeType)){
				outStr = "当前页面为-"+returnStr+":请选择采油站数据进行查询";
			}
		}else if("锅炉基础信息".equals(returnStr)){
			if(!"5".equals(nodeType) && !"14".equals(nodeType) && !"7".equals(nodeType) && !"10".equals(nodeType)){
				outStr = "当前页面为-"+returnStr+":请选择联合站、运行组、供热站或锅炉数据进行查询";
			}
		}else if("管汇点基础信息".equals(returnStr)){
			if(!"4".equals(nodeType) && !"7".equals(nodeType) && !"8".equals(nodeType)){
				outStr = "当前页面为-"+returnStr+":请选择采油站、注转站或管汇数据进行查询";
			}
		}else if("分线基础信息".equals(returnStr)){
			if(!"5".equals(nodeType) ){
				outStr = "当前页面为-"+returnStr+":请选择联合站数据进行查询";
			}
		}else if("部门基础信息".equals(returnStr)){
			
		}else if("SAGD井动态数据".equals(returnStr)){
			
		}else if("常规油井动态数据".equals(returnStr)){
			
		}else if("稀油油井动态数据".equals(returnStr)){
			
		}else if("采出液动态数据".equals(returnStr)){
			
		}else if("观察井动态数据".equals(returnStr)){
			
		}else if("功图".equals(returnStr)){
			
		}else if("气井动态数据".equals(returnStr)){
			
		}else if("水源井动态数据".equals(returnStr)){
			
		}else if("注水井动态数据".equals(returnStr)){
			
		}else if("联合站动态数据".equals(returnStr)){
			
		}else if("注转站动态数据".equals(returnStr)){
			
		}else if("湿蒸汽锅炉动态数据".equals(returnStr)){
			
		}else if("过热锅炉".equals(returnStr)){
			
		}else if("管汇点动态数据".equals(returnStr)){
			
		}else if("异常井查询".equals(returnStr)){
			
		}else if("日报维护".equals(returnStr)){
			
		}else if("SAGD井日报维护".equals(returnStr)){
			
		}else if("稀油井日报维护".equals(returnStr)){
			
		}else if("观察井日报维护".equals(returnStr)){
			
		}else if("气井日报维护".equals(returnStr)){
			
		}else if("水源井日报维护".equals(returnStr)){
			
		}else if("注水井日报维护".equals(returnStr)){
			
		}else if("联合站日报维护".equals(returnStr)){
			
		}else if("注转站日报维护".equals(returnStr)){
			
		}else if("湿蒸汽锅炉日报维护".equals(returnStr)){
			
		}else if("过热锅炉日报维护".equals(returnStr)){
			
		}else if("管汇点日报维护".equals(returnStr)){
			
		}else if("SAGD井日报".equals(returnStr)){
			
		}else if("稀油井日报".equals(returnStr)){
			
		}else if("观察井日报".equals(returnStr)){
			
		}else if("气井日报".equals(returnStr)){
			
		}else if("水源井日报".equals(returnStr)){
			
		}else if("注水井日报".equals(returnStr)){
			
		}else if("联合站日报".equals(returnStr)){
			
		}else if("注转站日报".equals(returnStr)){
			
		}else if("湿蒸汽锅炉日报".equals(returnStr)){
			
		}else if("过热锅炉日报".equals(returnStr)){
			
		}else if("管汇点日报".equals(returnStr)){
			
		}else if("操作员信息管理".equals(returnStr)){
			outStr = "当前页面为-"+returnStr+":咱不支持公共收索";
		}else if("角色信息管理".equals(returnStr)){
			outStr = "当前页面为-"+returnStr+":咱不支持收索";
		}else if("操作日志查询".equals(returnStr)){
			outStr = "当前页面为-"+returnStr+":咱不支持收索";
		}else if("Portal菜单维护".equals(returnStr)){
			outStr = "当前页面为-"+returnStr+":咱不支持收索";
		}else if("控制器通信信息".equals(returnStr)){
			
		}else if("仪表设备信息".equals(returnStr)){
			
		}else if("采集点对象规范".equals(returnStr)){
			
		}else if("采集参数点规范".equals(returnStr)){
			
		}else if("对象采集参数管理".equals(returnStr)){
			
		}
		out.print(outStr);
		return null;
	}

	

}
