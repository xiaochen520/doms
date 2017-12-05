package com.echo.action;

import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.echo.dto.PcCdAlarmManagT;
import com.echo.dto.PcRdLoginfoT;
import com.echo.dto.User;
import com.echo.service.BjfzService;
import com.echo.service.LogService;
import com.echo.util.AuthorityUtil;
import com.echo.util.CommonsUtil;
import com.echo.util.PageVariableUtil;
import com.echo.util.StringUtil;

public class BjfzzskAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BjfzService bjfzService;

	private LogService logService;

	public BjfzService getBjfzService() {
		return bjfzService;
	}
	public void setBjfzService(BjfzService bjfzService) {
		this.bjfzService = bjfzService;
	}
	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	
	HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
	
	
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
			String grids = AuthorityUtil.getGridJson("报警辅助知识库管理", user,PageVariableUtil.BJZSK_PAGE_GRID);
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
	
	

	public String searchBjzsk() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		int pageNo = 1; //页索引参数名当前页
		String sort = "";		//页排序列名
		String order = "";//页排序方向
		int rowsPerpage = 0; //每页显示条数		
		sort = StringUtil.isNullUtil(request.getParameter("sort"));
		order = StringUtil.isNullUtil(request.getParameter("order"));
		if( request.getParameter("rowsPerpage") != null && !"".equals( request.getParameter("rowsPerpage"))){
			rowsPerpage = Integer.parseInt(request.getParameter("rowsPerpage"));
		}
		JSONObject jsonobj = null;
		try {
			jsonobj = bjfzService.searchBjzsk(pageNo,sort,order,rowsPerpage);
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

	
	public String adduser() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		PcCdAlarmManagT alarmMana = null;
		boolean addflg = true;
		String outCode = "1";
		User sessionuser = (User) session.getAttribute("userInfo");
		alarmMana = new PcCdAlarmManagT();
		//名字
		String username = StringUtil.toStr(request.getParameter("username").trim());

		alarmMana.setUsername(username); 
		List<Object[]> userl = null;
		try {
			userl = bjfzService.searchUserChick(username);
		} catch (Exception e) {
			e.printStackTrace();
			outCode= "-15801";
		}		
		if("1".equals(outCode)){
			if(userl != null && userl.size() >0){
				outCode= "-15806";
			}else{				
				//推送人
				alarmMana.setUsername(StringUtil.toStr(request.getParameter("username").trim())); 
				//电话
				String phone = StringUtil.toStr(request.getParameter("phone").trim());
				//所属部门
				String dept = StringUtil.toStr(request.getParameter("department").trim());
				String alarmkey = StringUtil.toStr(request.getParameter("alarm_key").trim());
				String dep_manag = StringUtil.toStr(request.getParameter("dep_manag").trim());
				String data_unit = StringUtil.toStr(request.getParameter("data_unit").trim());

				
				List<Object[]> deplist = null;
				try {
					if(dept != null && !"".equals(dept)){
						String sql = "select * from pc_cd_org_t t where t.structurename ='"+dept+"'";
						deplist = bjfzService.searchDatas(sql);
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
					alarmMana.setPhone(phone);
					alarmMana.setDept(dept);
					alarmMana.setAlarmkey(alarmkey);
					alarmMana.setDep_manag(dep_manag);
					alarmMana.setData_unit(data_unit);
					//当前状态
//					alarmMana.setOperSdbsadbse(StringUtil.toStr(request.getParameter("oper_sdbsadbse").trim()));
					//备注
					alarmMana.setRemark(StringUtil.toStr(request.getParameter("remark").trim()));
					//修改人
//					alarmMana.setRlastOper(sessionuser.getOperName());
					//修改时间
//					alarmMana.setRlastOdate(new Date());


					if("1".equals(outCode)){
						try {
							addflg = bjfzService.addUser(alarmMana);						
						} catch (Exception e) {
							e.printStackTrace();
							outCode = "-15805";
						}
						if("1".equals(outCode) && addflg == true){
							//添加系统LOG
							try {
								PcRdLoginfoT log = CommonsUtil.getLogInfo(sessionuser, "1", "报警辅助用户管理", alarmMana.getAlarmuserid());
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
	
	
	@SuppressWarnings("unused")
	public String updateUser() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String username = StringUtil.toStr(request.getParameter("username").trim());
		String userid = request.getParameter("alarmuserid").trim();
		String outCode = "1";
		List<PcCdAlarmManagT> userList = null;
		List<Object[]> nameList = null;
		try {
			userList = (List<PcCdAlarmManagT>) bjfzService.searchUser(userid);
			nameList = bjfzService.searchUserChick(username);
		} catch (Exception e1) {
			e1.printStackTrace();
			outCode = "-15802";
		}
		boolean addflg = true;
		PcCdAlarmManagT user = null;
		User sessionuser = (User) session.getAttribute("userInfo");
		if(nameList != null && nameList.size()>0){
			if(!userid.equals(nameList.get(0)[1])){
				outCode = "-15806";
			}
		}		
		if("1".equals(outCode)){
			if(userList != null && userList.size()>0){
				user = userList.get(0);
				//推送人
				user.setUsername(StringUtil.toStr(request.getParameter("username").trim())); 
				//电话
				String phone = StringUtil.toStr(request.getParameter("phone").trim());
				//所属部门
//				System.out.println(StringUtil.toStr(request.getParameter("department").trim()));
				
				
//				String dept = StringUtil.toStr(request.getParameter("department").trim());
				String alarmkey = StringUtil.toStr(request.getParameter("alarm_key").trim());
				String dep_manag = StringUtil.toStr(request.getParameter("dep_manag").trim());
				String data_unit = StringUtil.toStr(request.getParameter("data_unit").trim());
				List<Object[]> deplist = null;
//				try {
//					if(dept != null && !"".equals(dept)){
//						String sql = "select * from pc_cd_org_t t where t.structurename ='"+dept+"'";
//						deplist = bjfzService.searchDatas(sql);
//						if(deplist == null || deplist.size() < 1 ){
//							outCode = "-11706";
//						}						
//					}else{						
//					}					
//				} catch (Exception e) {
//					e.printStackTrace();
//					outCode = "-11706";
//				}					
				
				if("1".equals(outCode)){
					user.setPhone(phone);
//					user.setDept(dept);
					user.setAlarmkey(alarmkey);
					user.setDep_manag(dep_manag);
					user.setData_unit(data_unit);
					//备注
					user.setRemark(StringUtil.toStr(request.getParameter("remark").trim()));


					if("1".equals(outCode)){
						try {
							addflg = bjfzService.updateUser(user);
						} catch (Exception e) {
							e.printStackTrace();
							outCode = "-15803";
						}
					}										
				}				
				if("1".equals(outCode) && addflg == true){
					//添加系统LOG
					try {
						PcRdLoginfoT log = CommonsUtil.getLogInfo(sessionuser, "3", "用户信息管理", user.getAlarmuserid());
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
			deleteflg = bjfzService.removeUser(userId);
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
			jsonobj = bjfzService.searchUserDep();
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
	
}
