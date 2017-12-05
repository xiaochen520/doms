package com.echo.action;

import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.echo.dto.PcCdDepartmentT;
import com.echo.dto.PcRdLoginfoT;
import com.echo.dto.User;
import com.echo.service.DepartmentService;
import com.echo.service.LogService;
import com.echo.util.AuthorityUtil;
import com.echo.util.CommonsUtil;
import com.echo.util.PageVariableUtil;
import com.echo.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

public class DepartemtAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4154345270195887433L;
	private DepartmentService departmentService;
	private LogService logService;
	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
	
	/**
	 * 用于接收前台传入的参数 SET AND GET
	 */

	//根据权限进行页面初始化
	@SuppressWarnings("unused")
	public String pageInit()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		
		//根据用户权限生成用户权限生成页面布局格式
		User user = (User) session.getAttribute("userInfo");
		//获取用户页面权限
		JSONObject tjo = new JSONObject();
		
		try {
			String grids = AuthorityUtil.getGridJson("部门基础信息", user,PageVariableUtil.DEPARTMENT_PAGE_GRID);
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

	public String addDep() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
//		List<TbRole> roleids = new ArrayList<TbRole>();
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		PcCdDepartmentT dep = null;
		boolean addflg = true;
		String outCode = "1";
		User sessionuser = (User) session.getAttribute("userInfo");
		dep = new PcCdDepartmentT();
		String department_name = StringUtil.toStr(request.getParameter("department_name").trim());
		List<PcCdDepartmentT> departmentT = null;
		try {
			departmentT = departmentService.searchDepartment("", department_name);
		} catch (Exception e) {
			e.printStackTrace();
			outCode = "-11701";
		}
		
		if(departmentT != null && departmentT.size()>0){
			outCode = "-11707";
		}
		if("1".equals(outCode)){
			dep.setDepartmentName(department_name);
			dep.setDepartmentHeader(StringUtil.toStr(request.getParameter("department_header").trim()));
			dep.setDepartmentAddress(StringUtil.toStr(request.getParameter("department_address").trim()));
			String phone = StringUtil.toStr(request.getParameter("department_phone").trim());
			if(phone != null && !"".equals(phone)){
				dep.setDepartmentPhone(Long.parseLong(phone));
			}
			dep.setRemark(StringUtil.toStr(request.getParameter("remark").trim()));
			dep.setRlastOdate(new Date());
			dep.setRlastOper(sessionuser.getOperName());
			//PcCdOrgT org = new PcCdOrgT();
			//org.setStructurename(department_name);
			//部门数据暂时设定20
			//org.setStructuretype((byte)20);
			//org.setPid("ORG001");
//			dep.setPcCdOrgT(org);
//			org.setPcCdDepartmentT(dep);
//			
			try {
				addflg = departmentService.addDep(dep);
			} catch (Exception e) {
				e.printStackTrace();
				//部门信息添加失败
				outCode = "-11701";
			}
			if("1".equals(outCode) && addflg == true){
				//添加系统LOG
				try {
					PcRdLoginfoT log = CommonsUtil.getLogInfo(sessionuser, "1", "部门基础信息", dep.getDepartmentid());
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
	
	@SuppressWarnings("unused")
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
			jsonobj = departmentService.searchDeps(department,pageNo,sort,order,rowsPerpage);
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
	

	public String updateDep() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		List<PcCdDepartmentT> depList = null;
		List<PcCdDepartmentT> nameList = null;
		PcCdDepartmentT dep = null;
		String depid = request.getParameter("depid").trim();
		String departmentname = StringUtil.toStr(request.getParameter("department_name").trim());
		if(depid != null && !"".equals(depid)){
			try {
				depList = (List<PcCdDepartmentT>) departmentService.searchDepartment(depid, "");
				nameList = (List<PcCdDepartmentT>) departmentService.searchDepartment("", departmentname);
			} catch (Exception e1) {
				e1.printStackTrace();
				outCode = "-11703";
			}
			boolean addflg = true;
			User sessionuser = (User) session.getAttribute("userInfo");
			if(nameList != null && nameList.size()>0){
				if(!depid.equals(nameList.get(0).getDepartmentid())){
					outCode = "-11707";
				}
			}
			
			if("1".equals(outCode) && depList != null && depList.size()>0){
				dep = depList.get(0);
				
				dep.setDepartmentName(departmentname);
				dep.setDepartmentHeader(StringUtil.toStr(request.getParameter("department_header").trim()));
				dep.setDepartmentAddress(StringUtil.toStr(request.getParameter("department_address").trim()));
				String phone = StringUtil.toStr(request.getParameter("department_phone").trim());
				if(phone != null && !"".equals(phone)){
					dep.setDepartmentPhone(Long.parseLong(phone));
				}else{
					dep.setDepartmentPhone(null);
				}
				dep.setRemark(StringUtil.toStr(request.getParameter("remark").trim()));
				dep.setRlastOper(sessionuser.getOperName());
				dep.setRlastOdate(new Date());
				//PcCdOrgT org = new PcCdOrgT();
				//org = dep.getPcCdOrgT();
				//org.setStructurename(departmentname);
//				org.setPcCdDepartmentT(dep);
//				dep.setPcCdOrgT(org);
				
				try {
					addflg = departmentService.updateDep(dep);
				} catch (Exception e) {
					e.printStackTrace();
					outCode = "-11704";
				}
				
			}
			
			
			if("1".equals(outCode) && addflg == true){
				//添加系统LOG
				try {
					PcRdLoginfoT log = CommonsUtil.getLogInfo(sessionuser, "3", "部门基础信息", dep.getDepartmentid());
					logService.addLog(log);
				} catch (Exception e) {
					e.printStackTrace();
					outCode = "-10002";
				}
			}
			
		}else{
			outCode = "-11703";
		}
		out.print(outCode);
		return null;
	}

	public String removeDep() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		String depid = StringUtil.toStr(request.getParameter("depid").trim());
		String org_id = "";
		if(request.getParameter("org_id") != null){
			org_id = StringUtil.toStr(request.getParameter("org_id").trim());
		}
		
		boolean deleteflg = true;
		JSONObject obj = new JSONObject();
		try {
			deleteflg = departmentService.removeDep(depid,org_id);
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
