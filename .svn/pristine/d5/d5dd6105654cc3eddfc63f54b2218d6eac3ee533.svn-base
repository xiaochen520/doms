//package com.echo.action;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import net.sf.json.JSONArray;
//
//import org.apache.struts2.ServletActionContext;
//
//import com.echo.dto.TbFunctionmenu;
//import com.echo.dto.TbLoginfo;
//import com.echo.dto.TbRight;
//import com.echo.dto.TbRole;
//import com.echo.dto.TbUser;
//import com.echo.service.LogService;
//import com.echo.service.RightService;
//import com.echo.util.AuthorityUtil;
//import com.echo.util.CommonsUtil;
//import com.echo.util.PageVariableUtil;
//import com.echo.util.StringUtil;
//
//public class RightAction{
//	/**
//	 * 
//	 */
//	private RightService rightService;
//	private LogService logService;
//	public void setLogService(LogService logService) {
//		this.logService = logService;
//	}
//	HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
//
//	public void setRightService(RightService rightService) {
//		this.rightService = rightService;
//	}
//
//	//根据权限进行页面初始化
//	public String pageInit()throws IOException{
//		
//		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
//		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
//		response.setCharacterEncoding("utf-8");
//		response.setHeader("ContentType","text/xml");
//		PrintWriter out = response.getWriter();
//		
//		//根据用户权限生成用户权限生成页面布局格式
//		TbUser user = (TbUser) session.getAttribute("userInfo");
//		
//		String gridJson = AuthorityUtil.getGridJson("权限信息管理", user, PageVariableUtil.RIGHTINFO_PAGE_GRID);
////		System.out.println(gridJson);
//		out.print(gridJson);
//		return null;
//	}
//	
//	//获取可设置权限菜单
//	public String seachMenu() throws IOException {
//		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
//		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
//		response.setCharacterEncoding("utf-8");
//		response.setHeader("ContentType","text/xml");
//		PrintWriter out = response.getWriter();
//		String rightid =StringUtil.toStr(request.getParameter("rightid").trim()); 
//		JSONArray menuJson = new JSONArray();
//		 menuJson = rightService.searchmenu();
//		if(rightid != null && !"".equals(rightid)){
//			JSONArray jsonobj = rightService.searchRightbyID(rightid);
//			if(jsonobj != null){
//				out.print(menuJson.toString()+"|"+jsonobj.toString());
//			}else{
//				out.print(menuJson);
//			}
//			
//		}else{
//			 out.print(menuJson);
//		}
//		return null;
//	}
//
//	public String addRight() throws IOException {
//		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
//		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
//		List<TbRole> roleids = new ArrayList<TbRole>();
//		response.setCharacterEncoding("utf-8");
//		response.setHeader("ContentType","text/xml");
//		PrintWriter out = response.getWriter();
//		TbRight tbright = new TbRight();
//		//权限名称
//		tbright.setRightname(StringUtil.toStr(request.getParameter("right").trim())); 
//		List<TbFunctionmenu> menuList = rightService.searchMenuALL();
//		
//		String menuname = StringUtil.toStr(request.getParameter("menuname").trim());
//		String menuid = AuthorityUtil.getMenuNameByName(menuname, menuList);
//		
//		if("显示功能菜单".equals(StringUtil.toStr(request.getParameter("right").trim()))){
//			tbright.setMenuid(menuid);
//		}else{
//			tbright.setInpage(menuid);
//		}
//		//
//		
//		tbright.setDispalycolumn(StringUtil.toStr(request.getParameter("dispalycolumn").trim()));
//		String node = StringUtil.toStr(request.getParameter("structurenode").trim());
//		if("全部显示".equals(node)){
//			tbright.setStructurenode((byte)0);
//		}else{
//			tbright.setStructurenode((byte)1);
//		}
//		
//		tbright.setRightdescribe(StringUtil.toStr(request.getParameter("right").trim())+"-"+menuname);
//		tbright.setEnabled((byte) 0);
//		tbright.setBz(StringUtil.toStr(request.getParameter("txtbz").trim()));
//		boolean addflg = true;
//		
//		
//		addflg = rightService.addRight(tbright);
//		
//		if(addflg){
//			//添加系统LOG
//			TbUser userinfo = (TbUser) session.getAttribute("userInfo");
//			TbLoginfo log = CommonsUtil.getLogInfo(userinfo, (byte) 1, "权限信息管理", tbright.getRightid());
//			logService.addLog(log);
//			out.print("1");
//		}else{
//			out.print("-1");
//		}
//		return null;
//	}
//	
//	
//	public String updateRight() throws IOException {
//		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
//		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
//		List<TbRole> roleids = new ArrayList<TbRole>();
//		response.setCharacterEncoding("utf-8");
//		response.setHeader("ContentType","text/xml");
//		PrintWriter out = response.getWriter();
//		TbRight tbright = new TbRight();
//		
//		tbright.setRightid(StringUtil.toStr(request.getParameter("rid").trim())); 
//		//权限名称
//		tbright.setRightname(StringUtil.toStr(request.getParameter("right").trim())); 
//		List<TbFunctionmenu> menuList = rightService.searchMenuALL();
//		
//		String menuname = StringUtil.toStr(request.getParameter("menuname").trim());
//		String menuid = AuthorityUtil.getMenuNameByName(menuname, menuList);
//		
//		if("显示功能菜单".equals(StringUtil.toStr(request.getParameter("right").trim()))){
//			tbright.setMenuid(menuid);
//		}else{
//			tbright.setInpage(menuid);
//		}
//		//
//		
//		tbright.setDispalycolumn(StringUtil.toStr(request.getParameter("dispalycolumn").trim()));
//		String node = StringUtil.toStr(request.getParameter("structurenode").trim());
//		if("全部显示".equals(node)){
//			tbright.setStructurenode((byte)0);
//		}else{
//			tbright.setStructurenode((byte)1);
//		}
//		
//		tbright.setRightdescribe(StringUtil.toStr(request.getParameter("right").trim())+"-"+menuname);
//		tbright.setEnabled((byte) 0);
//		tbright.setBz(StringUtil.toStr(request.getParameter("txtbz").trim()));
//		boolean addflg = true;
//		
//		
//		addflg = rightService.updateRight(tbright);
//		
//		if(addflg){
//			//添加系统LOG
//			TbUser userinfo = (TbUser) session.getAttribute("userInfo");
//			TbLoginfo log = CommonsUtil.getLogInfo(userinfo, (byte) 3, "权限信息管理", tbright.getRightid());
//			logService.addLog(log);
//			out.print("1");
//		}else{
//			out.print("-1");
//		}
//		return null;
//	}
//	
//	public String seachRight() throws IOException {
//			HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
//			HttpServletResponse response=ServletActionContext.getResponse();//响应对象
//			response.setCharacterEncoding("utf-8");
//			response.setHeader("ContentType","text/xml");
//			PrintWriter out = response.getWriter();
//			
//            String	rightname =   StringUtil.toStr(request.getParameter("rightname").trim());	
//			// user.setUserPhoto(name)
//            JSONArray jsonobj = rightService.searchRight(rightname);
//			if(jsonobj != null){
//				out.println(jsonobj);
//			}else{
//				out.println("");
//			}
//			
//		return null;
//	}
//	
//	
//	public String seachRightById() throws IOException {
//		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
//		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
//		response.setCharacterEncoding("utf-8");
//		response.setHeader("ContentType","text/xml");
//		PrintWriter out = response.getWriter();
//		
//        String	rightid =   request.getParameter("rightid").trim();	
//		// user.setUserPhoto(name)
//        JSONArray jsonobj = rightService.searchRightbyID(rightid);
//		if(jsonobj != null){
//			out.println(jsonobj);
//		}else{
//			out.println("");
//		}
//		
//	return null;
//}
//	
//
//
//	public String updateOneUser() throws IOException {
//		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
//		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
//		response.setCharacterEncoding("utf-8");
//		response.setHeader("ContentType","text/xml");
//		
//		return "updateuserinfo";
//	}
//	public String removeRight() throws IOException {
//		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
//		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
//		response.setCharacterEncoding("utf-8");
//		response.setHeader("ContentType","text/xml");
//		PrintWriter out = response.getWriter();
//		
//		String rightID = StringUtil.toStr(request.getParameter("rightID"));
//		
//		boolean deleteflg = rightService.removeRight(rightID);
//		
//		if(deleteflg){
//			//添加系统LOG
//			TbUser userinfo = (TbUser) session.getAttribute("userInfo");
//			TbLoginfo log = CommonsUtil.getLogInfo(userinfo, (byte) 2, "权限信息管理", rightID);
//			logService.addLog(log);
//			out.print("1");
//		}else{
//			out.print("-1");
//			
//		}
//		return null;
//	}
//	
//
//}
