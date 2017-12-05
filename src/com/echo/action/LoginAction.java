package com.echo.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import com.echo.dto.PcCdMenuT;
import com.echo.dto.PcCdRightT;
import com.echo.dto.PcCdRoleT;
import com.echo.dto.PcRdLoginfoT;
import com.echo.dto.User;
import com.echo.service.LogService;
import com.echo.service.SystemTreeService;
import com.echo.service.UserService;
import com.echo.util.CommonsUtil;
import com.echo.util.DateBean;
import com.echo.util.SystemUtil;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport{
	
//	public static final Logger log = (Logger) LogFactory.getLog(LoginAction.class);

	private static final long serialVersionUID = -2633922627922072389L;
	private UserService userService;
	private SystemTreeService systemTreeService;
	private LogService logService;
	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setSystemTreeService(SystemTreeService systemTreeService) {
		this.systemTreeService = systemTreeService;
	}
	/**
	 * 过滤字段
	 * 采用正则表达式将包含有 单引号(')，
	 * 分号(;) 和 注释符号(--)的语句给替换
	 * @param str
	 * @return
	 */
	
	/**
	 * session 过期后跳转到登陆界面
	 * @return
	 * @throws Exception
	 */
	public String sessionFilter() throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		
		PrintWriter out = response.getWriter();

		User isLog = (User) request.getSession().getAttribute("userInfo");   
	    if ((isLog != null) && ((isLog.getOperName() != null) || (isLog.getOperName() != ""))) { 
	    	out.println("1");
	    }   
	    else 
	    {
	    	//防止用户注销后，点击IE后退键返回
			String url = request.getContextPath();//获得当前工程名
			out.println(" " + url + "/index.jsp");
	    }
	    return null;
	}
	public static String TransactSQLInjection(String str)
    {
          return str.replaceAll(".*([';]+|(--)+).*", " ");
    }
	
	
	/**
	 * 验证用户登录
	 * @return
	 * @throws Exception
	 */
	public String SystemLogin() throws Exception{
		
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		Cookie cookieName = null;
		//从前台获取登录的用户名和密码
		String username = request.getParameter("username").trim();
		String passwordinit = request.getParameter("password").trim();
		//if(password != null && !"".equals(password) && password.length() != 32){
		String password = SystemUtil.md5(request.getParameter("password").trim());
		//}
		//on 选中，null 为选中
		//记住密码
		String autopas = request.getParameter("autopas");
		//自动登录
//		 Set<TbRight> rightInUser = new HashSet<TbRight>(0);
		 
		List<Object[]> roleList = null;
			
			//获取用户权限与角色
			/**
			 *  0：userid=用户ID
				1：oper_code=操作员代码
				2：oper_name=OPER_NAME
				3：oper_pass=口令
				4：oper_sdbsadbse=当前状态
				5：audbshor_date=注册日期
				6：sdbsadbse_date=状态改变日期
				7：l_login_date=最后登录时间
				8: l_logoudbs_date=最后退出时间
				9：rlast_oper=此记录上次操作者
				10：rlast_odate=此记录上次操作日期
				11：remark=备注
				12：roleid=角色编码
				13：role_name=角色名称
				14：role_described=角色描述
				15：RLAST_OPER=修改人
				16：RLAST_ODATE=修改时间
				17：rightid=权限编码
				18：menuid=菜单编码
				19: right_name=权限名称
				20: menu_code=所在菜单名称
				21：departmentid=部门ID
				22：department_name=部门名称
				23：department_type=部门类型
			 */
		
			try {
				roleList = userService.searchRight(username,password);
			} catch (HibernateException e) {
				e.printStackTrace();
				this.addFieldError("errermsg", "用户验证获取权限失败~！");
				return "fail";
			}
//			userList = userService.searchUserByNamePas(username,password);
			if(roleList != null && roleList.size() > 0){
				HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
				//记住密码
				if(autopas != null && "on".equals(autopas)){
					
					cookieName = new Cookie("cookieName", username+"|"+passwordinit);
//					if(autologin != null && "on".equals(autologin)){
//						cookieName = new Cookie("cookieName", username+"|"+password+"|1|1");
//					}else{
//						cookieName = new Cookie("cookieName", username+"|"+password+"|0|1");
//					}
				}else{
					cookieName = new Cookie("cookieName", username+"|"+""+"|");
//					if(autologin != null && "on".equals(autologin)){
//						cookieName = new Cookie("cookieName", username+"|"+""+"|1|0");
//					}else{
//						cookieName = new Cookie("cookieName", username+"|"+""+"|0|0");
//					}
				}
				 cookieName.setMaxAge(60*60*100*1000);
				 response.addCookie(cookieName);
				
				session.invalidate();
				// 设置会话的生命时间
				session = ServletActionContext.getRequest().getSession(true);
				session.setMaxInactiveInterval(365 * 24 * 3600);
				User user  = new User();
				//	0：userid=用户ID
				user.setUserid(String.valueOf(roleList.get(0)[0]));
			//	 PcCdDepartmentT pcCdDepartmentT = new PcCdDepartmentT();
				 //21：departmentid=部门ID
				 user.setDepid(String.valueOf(roleList.get(0)[21]));
//				 pcCdDepartmentT.setDepartmentid(String.valueOf(roleList.get(0)[21]));
				 //22：department_name=部门名称
				 user.setDepname(String.valueOf(roleList.get(0)[22]));
//				 pcCdDepartmentT.setDepartmentName(String.valueOf(roleList.get(0)[22]));
				 user.setDepType(String.valueOf(roleList.get(0)[23]));
				 
				 if(roleList.get(0)[23] != null && !"".equals(CommonsUtil.getClearNullData(roleList.get(0)[23]))){
					 if("3".equals(CommonsUtil.getClearNullData(roleList.get(0)[23]))){
						 user.setCyz("全部");
						 user.setTeam("");
					 }else if("4".equals(CommonsUtil.getClearNullData(roleList.get(0)[22]))){
						 user.setCyz(CommonsUtil.getClearNullData(roleList.get(0)[21]));
						 user.setTeam("");
					 }else if("15".equals(CommonsUtil.getClearNullData(roleList.get(0)[23]))){
						 user.setCyz("全部");
						 user.setTeam(CommonsUtil.getClearNullData(roleList.get(0)[22]));
					 }else{
						 user.setCyz("");
						 user.setTeam("");
					 }
				 }
//				 user.setPcCdDepartmentT(pcCdDepartmentT);
				 //1：oper_code=操作员代码
				user.setOperCode(String.valueOf(roleList.get(0)[1]));
				//2：oper_name=OPER_NAME
				user.setOperName(String.valueOf(roleList.get(0)[2]));
				//3：oper_pass=口令
				user.setOperPass(String.valueOf(roleList.get(0)[3]));
				//4：oper_sdbsadbse=当前状态
				user.setOperSdbsadbse(String.valueOf(roleList.get(0)[4]));
				//5：audbshor_date=注册日期
				Date jionDate = DateBean.getStrDate(String.valueOf(roleList.get(0)[5]));
				user.setAudbshorDate(jionDate);
				//6：sdbsadbse_date=状态改变日期
				Date sdbsadbse_date = DateBean.getStrDate(CommonsUtil.getClearNullData(roleList.get(0)[6]));
				user.setSdbsadbseDate(sdbsadbse_date);
				//7：l_login_date=最后登录时间
				Date l_login_date = DateBean.getStrDate(String.valueOf(roleList.get(0)[7]));
				user.setLLoginDate(l_login_date);
				//8: l_logoudbs_date=最后退出时间
				Date l_logoudbs_date = DateBean.getStrDate(String.valueOf(roleList.get(0)[8]));
				user.setLLogoudbsDate(l_logoudbs_date);
				//9：rlast_oper=此记录上次操作者
				user.setRlastOper(String.valueOf(roleList.get(0)[9]));
				//10：rlast_odate=此记录上次操作日期
				Date rlast_odate = DateBean.getStrDate(String.valueOf(roleList.get(0)[10]));
				user.setRlastOdate(rlast_odate);
				//11：remark=备注
				user.setRemark(String.valueOf(roleList.get(0)[11]));
//				Set<E> pcRdLoginfoTs = new HashSet(0);
//				pcCdUserRoleTs = new HashSet(0);
				
				List<PcCdRoleT> pcCdUserRoleTs = new ArrayList<PcCdRoleT>();
				List<PcCdRightT> pcCdRightTs = new ArrayList<PcCdRightT>();
//				user.setIp(String.valueOf(roleList.get(0)[0]));
				PcCdRightT rights = null;
				PcCdRoleT role = null;
				int roleflg = 0;
				int rightflg = 0;
				for(Object[] userobj :roleList){
						rights = new PcCdRightT();
						role = new PcCdRoleT();
						roleflg = 0;
						rightflg = 0;
//						12：roleid=角色编码
						if(pcCdUserRoleTs.size() >0){
							for(PcCdRoleT pcrole :pcCdUserRoleTs){
								if(pcrole.getRoleid().equals(String.valueOf(userobj[12]))){
									
									roleflg = 1;
									break;
								}
								
							}
						}
						if(roleflg == 0){
							//	12：roleid=角色编码
							role.setRoleid(String.valueOf(userobj[12]));
//							13：role_name=角色名称
							role.setRoleName(String.valueOf(userobj[13]));
//							14：role_described=角色描述
							role.setRoleDescribed(String.valueOf(userobj[14]));
//							15：update_oper=修改人
							role.setRlastOper(String.valueOf(userobj[15]));
//							16：update_time=修改时间
							Date update_time = DateBean.getStrDate(String.valueOf(userobj[16]));
							role.setRlastOdate(update_time);
							pcCdUserRoleTs.add(role);
							
						}
						
						if(pcCdRightTs.size() >0){
							for(PcCdRightT pcright :pcCdRightTs){
								if(pcright.getRightid().equals(String.valueOf(userobj[17]))){
									
									rightflg = 1;
									break;
								}
								
							}
						}
						if(rightflg == 0){
							//17：rightid=权限编码
							rights.setRightid(String.valueOf(userobj[17]));
//							18：menuid=菜单编码
							rights.setMenuid(String.valueOf(userobj[18]));
//							19: right_name=权限名称
							rights.setRightName(String.valueOf(userobj[19]));
//							20: menu_code=所在菜单名称
							rights.setMenuCode(String.valueOf(userobj[20]));
							pcCdRightTs.add(rights);
							
						}
				}
				
				user.setPcCdUserRoleTs(pcCdUserRoleTs);
				user.setPcCdRightTs(pcCdRightTs);
				//添加最后登录时间
				try {
					userService.updateUserLastLogin(user.getUserid(),1);
				} catch (Exception e) {
					e.printStackTrace();
					this.addFieldError("errermsg", "用户最后登录时间更新失败~");
					return "fail";
				}
				

				//获取本地IP
				user.setIp(CommonsUtil.getIpAddress());
				request.setAttribute("username", username);
				List<PcCdMenuT> menuList = null;
				try {
					menuList = systemTreeService.searchMenu();
				} catch (Exception e) {
					e.printStackTrace();
					this.addFieldError("errermsg", "系统菜单无法初始化~ 请联系管理员");
					return "fail";
				}
				
				
				if(menuList != null && menuList.size()>0){
					user.setMenuList(menuList);
				}
				
				//将用户信息填入session
				session.setAttribute("userInfo", user);
				//全局变量排采队
				session.setAttribute("VARIABLEPCD", "");
				//全局变量集气站
				session.setAttribute("VARIABLEJQZ", "");
				//全局变量井场
				session.setAttribute("VARIABLEJC", "");
				//全局变量井口
				session.setAttribute("VARIABLEJK", "");
				//全局变量开始时间
				session.setAttribute("VARIABLESTARTDATE", "");
				//全局变量结束时间
				session.setAttribute("VARIABLEENDDATE", "");
				//全局变量时间
				session.setAttribute("VARIABLEDATE", "");
				//添加系统LOG
				try {
					PcRdLoginfoT log = CommonsUtil.getLogInfo(user, "5", null, null);
					logService.addLog(log);
				} catch (Exception e) {
					e.printStackTrace();
					this.addFieldError("errermsg", "退出操作LOG信息添加失败~");
					return "fail";
				}
				
			}else{
				cookieName = new Cookie("cookieName", username+"||");
				this.addFieldError("errermsg", "用户名或密码错误");
				return "fail";
			}
		
		
		return "success";
	}
	/**
	 * 退出登录
	 * @return
	 * @throws Exception
	 */
	public String SystemLoginOut() throws Exception{
		HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
		User user = (User) session.getAttribute("userInfo");
		//添加系统LOG
		try {
			PcRdLoginfoT log = CommonsUtil.getLogInfo(user,"6", null, null);
			logService.addLog(log);
		} catch (Exception e) {
			e.printStackTrace();
			this.addFieldError("errermsg", "退出操作LOG信息添加失败~");
			return "fail";
		}
		
		try {
			userService.updateUserLastLogin(user.getUserid(),2);
		} catch (Exception e) {
			e.printStackTrace();
			this.addFieldError("errermsg", "用户最后退出时间更新失败~");
			return "fail";
		}
		
		//清楚SESSION
		session.removeAttribute("userInfo");
		
		return "fail";
	}
}
