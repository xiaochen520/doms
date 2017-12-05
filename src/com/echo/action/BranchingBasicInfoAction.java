package com.echo.action;

import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;
import com.echo.dto.PcCdBranchingT;
import com.echo.dto.PcRdLoginfoT;
import com.echo.dto.User;
import com.echo.service.BoilerBasicService;
import com.echo.service.BranchingBasicService;
import com.echo.service.LogService;
import com.echo.util.AuthorityUtil;
import com.echo.util.CommonsUtil;
import com.echo.util.PageVariableUtil;
import com.echo.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

public class BranchingBasicInfoAction extends ActionSupport  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BoilerBasicService boilerBasicService;
	private BranchingBasicService branchingBasicService;
	private LogService logService;



	public void setLogService(LogService logService) {
		this.logService = logService;
	}


	HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话

	public BoilerBasicService getBoilerBasicService() {
		return boilerBasicService;
	}

	public void setBoilerBasicService(BoilerBasicService boilerBasicService) {
		this.boilerBasicService = boilerBasicService;
	}
	
	public BranchingBasicService getBranchingBasicService() {
		return branchingBasicService;
	}

	public void setBranchingBasicService(BranchingBasicService branchingBasicService) {
		this.branchingBasicService = branchingBasicService;
	}

	public String queryBranchingBasicInfo() throws Exception {
			HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
			HttpServletResponse response=ServletActionContext.getResponse();//响应对象
			response.setCharacterEncoding("utf-8");
			response.setHeader("ContentType","text/xml");
			PrintWriter out = response.getWriter();
			
			String stationNumber = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("stationNumber")));
			String boilersName = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("branching_name")));
			String areablock = "";
			
			int pageNo = 1; //页索引参数名当前页
			String sort = "";		//页排序列名
			String order = "";//页排序方向
			int rowsPerpage = 0; //每页显示条数
			if(request.getParameter("pageNo") != null && !"".equals(request.getParameter("pageNo"))){
				pageNo = Integer.parseInt(request.getParameter("pageNo"));	
			}
			
			sort = StringUtil.isNullUtil(request.getParameter("sort"));
			order = StringUtil.isNullUtil(request.getParameter("order"));
			if( request.getParameter("rowsPerpage") != null && !"".equals( request.getParameter("rowsPerpage"))){
				rowsPerpage = Integer.parseInt(request.getParameter("rowsPerpage"));
			}
			JSONObject jsonobj = null;
			try {
				jsonobj = branchingBasicService.queryBranchingBasicInfo(stationNumber, boilersName, areablock,pageNo,sort,order,rowsPerpage);
			} catch (Exception e) {
				e.printStackTrace();
				return "fail";
			}
			
			if(jsonobj != null){
				out.println(jsonobj);
			}else{
				out.println("");
			}
			
		return null;
	}
	
	public String queryCombinationStationName() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONArray jsonArr = null;
		String arg = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("arg")));
		try {
			jsonArr = branchingBasicService.queryCombinationStationName(arg);
		} catch (Exception e) {
			this.addFieldError("errermsg", "锅炉信息获取失败~");
			e.printStackTrace();
		}
		if(jsonArr != null){
			out.println(jsonArr);
		}else{
			out.println("");
		}
		return null;
	}
	
	public String queryBranchingNameInfo() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String orgid = "";
		if(request.getParameter("orgid")!= null && !"".equals(request.getParameter("orgid"))){
			orgid = StringUtil.isNullUtil(request.getParameter("orgid"));
		}
		JSONArray jsonArr = null;
		try {
			jsonArr = branchingBasicService.queryBranchingNameInfo(orgid);
		} catch (Exception e) {
			this.addFieldError("errermsg", "锅炉信息获取失败~");
			e.printStackTrace();
		}
		if(jsonArr != null){
			out.println(jsonArr);
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
//		
		String gridJson;
		try {
			gridJson = AuthorityUtil.getGridJson("分线基础信息", user, PageVariableUtil.BRANCHING_PAGE_GRID);
		} catch (Exception e) {
			e.printStackTrace();
			this.addFieldError("errermsg", "系统菜单无法初始化~ 请联系管理员");
			return "fail";
		}
		out.print(gridJson);
		return null;
	}
	
	
	
	public String saveOrUpdate() throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		List<PcCdBranchingT> branchinglist = null;
		PcCdBranchingT branch = null;
		String outCode = "1";
		String branchingid = "";
		boolean addflg = true;
		User user1 = (User) session.getAttribute("userInfo");
		if(request.getParameter("branchingid2")!= null && !"".equals(request.getParameter("branchingid2"))){
			branchingid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("branchingid2")));
		}
		
		
		String branching_name = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("branching_name2")));
		String branching_code = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("branching_code2")));
		String combination_station = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("combination_station2")));
		String remark = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("remark2")));
		try {
			branchinglist = branchingBasicService.searchBranchs("", branching_name);
		} catch (Exception e) {
			e.printStackTrace();
			//-11601  分线信息获取错误
			outCode = "-11601";
		}
		//修改
		if(branchingid != null && !"".equals(branchingid)){
			if(branchinglist != null && branchinglist.size()>0){
				if(!branchingid.equals(branchinglist.get(0).getBranchingid())){
					//-11606  分线名称已存在，请使用其他分线名称
					outCode = "-11606";
				}
				
			}
			if("1".equals(outCode)){
				try {
					branchinglist = branchingBasicService.searchBranchs(branchingid, "");
				} catch (Exception e) {
					e.printStackTrace();
					//-11601  分线信息获取错误
					outCode = "-11601";
				}
				if("1".equals(outCode)){
					if(branchinglist != null && branchinglist.size()>0){
						branch = branchinglist.get(0);
					}else{
						//-11607  分线ID未识别，请重新选择分线信息
						outCode = "-11607";
					}
				}
				
			}
			
		//添加
		}else{
			if(branchinglist != null && branchinglist.size()>0){
				//-11606  分线名称已存在，请使用其他分线名称
				outCode = "-11606";
			}
			
			branch = new PcCdBranchingT();
		}
		
		if("1".equals(outCode)){
			
			branch.setBranchingName(branching_name);
			branch.setBranchingCode(branching_code);
			branch.setCombinationStation(combination_station);
			branch.setRemark(remark);
			branch.setRlastOper(user1.getOperName());
			branch.setRlastOdate(new Date());
			
			//修改
			if(branchingid != null && !"".equals(branchingid)){
				try {
					addflg = branchingBasicService.updatebranch(branch);
				} catch (Exception e) {
					e.printStackTrace();
					
					//-11603  分线信息修改失败
					outCode = "-11603";
				}
			}else{
				try {
					addflg = branchingBasicService.addbranch(branch);
				} catch (Exception e) {
					e.printStackTrace();
					//-11602  分线信息添加失败
					outCode = "-11602";
				}
			}
			
		}
		if("1".equals(outCode) && addflg == true){
			if(branchingid != null && !"".equals(branchingid)){
				//添加系统LOG
				try {
					PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "3", "分线基础信息", branch.getBranchingid());
					logService.addLog(log);
				} catch (Exception e) {
					e.printStackTrace();
					outCode = "-10002";
				}
			}else{
				//添加系统LOG
				try {
					PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "1", "分线基础信息", branch.getBranchingid());
					logService.addLog(log);
				} catch (Exception e) {
					e.printStackTrace();
					outCode = "-10001";
				}
				
			}
			
		
		}	

		out.println(outCode);
		return null;
	}

	
	public String removeBranchingBasicInfo() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String branchingid = StringUtil.toStr(request.getParameter("branchingid"));
		boolean deleteflg = false;
		JSONObject obj = new JSONObject();
		try {
			deleteflg = branchingBasicService.removeBranch(branchingid);
		} catch (Exception e) {
			String errmsg = e.getCause().toString();
			//ORA-02292: 违反完整约束条件 (NEWPORTAL.FK58_PC_RPD_WELL_SAGDD_T) - 已找到子记录
			if(!"".equals(errmsg) && errmsg.indexOf("ORA-02292") != -1 ){
				obj = CommonsUtil.getRrturnJson("","分线基础信息删除失败-分线日报中存在子记录" ,"", null, null);
			}else if(!"".equals(errmsg) && errmsg.indexOf("ORA-02292") != -1 ){
				obj = CommonsUtil.getRrturnJson("","分线基础信息删除失败-分线班报中存在子记录" ,"", null, null);
			} else{
				obj = CommonsUtil.getRrturnJson("",errmsg , "",null, null);
			}
			e.printStackTrace();
		}
		if(deleteflg){
			//添加系统LOG
			try {
				User user1 = (User) session.getAttribute("userInfo");
				PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "2", "分线基础信息", branchingid);
				logService.addLog(log);
			} catch (Exception e) {
				e.printStackTrace();
				obj = CommonsUtil.getRrturnJson("-10003", "","", null, null);
			}
			
		}
		out.print(obj);
		return null;
	}

}
