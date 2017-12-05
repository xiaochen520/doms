package com.echo.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.echo.dto.PcCdMenuT;
import com.echo.dto.PcCdPortalMenuT;
import com.echo.dto.User;
import com.echo.service.PortalService;
import com.echo.service.SystemTreeService;
import com.echo.util.AuthorityUtil;
import com.echo.util.CommonsUtil;
import com.echo.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

public class SystemTreeAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1057113054045397499L;
	HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
	
	
	private SystemTreeService systemTreeService;
	private PortalService	portalService;
	
	public void setPortalService(PortalService portalService) {
		this.portalService = portalService;
	}


	public void setSystemTreeService(SystemTreeService systemTreeService) {
		this.systemTreeService = systemTreeService;
	}
	
	
	/**
	 * 获取系统功能菜单数据
	 * @return
	 * @throws Exception
	 */
	public String getMenuData() throws Exception{
		
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		//获取session中用户信息
		User user = (User) session.getAttribute("userInfo");
        String jsonobj = systemTreeService.searchMenu(user);
        if(jsonobj != null){
			out.println(jsonobj);
		}else{
			out.println("");
		}
		
		return null;
	}
	
	/**
	 * 获取组织结构数据所有
	 * @return
	 * @throws Exception
	 */
	public String getStructureData() throws Exception{
		
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		
		
		JSONArray jsonobj = systemTreeService.searchStructure();
		
		
		if(jsonobj != null){
			out.println(jsonobj);
		}else{
			out.println("");
		}
		
		return null;
	}
	
	/**
	 * 获取功能菜单数据初始化数据即父节点为
	 * @return
	 * @throws Exception
	 */
	public String getMenuTree() throws Exception{
		
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		//获取session中用户信息
		User user = (User) session.getAttribute("userInfo");
		JSONArray jsonobj = systemTreeService.searchMenuNew(user);
        if(jsonobj != null){
			out.println(jsonobj);
		}
		return null;
	}
	
	
	/**
	 * 获取PORTAL功能菜单数据初始化数据即父节点为
	 * @return
	 * @throws Exception
	 */
	public String getPortalTree() throws Exception{
		
		
		
		//JSONArray jsonobj = systemTreeService.searchMenuNew(user);
		

		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		JSONArray jsonArr =  null;
		JSONArray childrenArr =  new JSONArray();
		JSONObject tjo = null;
		//获取session中用户信息
		User user = (User) session.getAttribute("userInfo");
		String nodeid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("nodeid")));

		List<PcCdPortalMenuT> portalList = null;
		try {
			if("".equals(nodeid)){
				portalList = portalService.searchPortal("AD13630FDC324D90859F5AD363A3DKKA" ,"");
			}else{
				
				String sql = "FROM PcCdPortalMenuT P WHERE 1=1 and  P.fatherPortalmenuid = '"+nodeid+"' order by nlssort(P.portalmenuName , 'NLS_SORT=SCHINESE_STROKE_M')"	;
				portalList = portalService.searchPortals(sql);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			//portalmenu数据获取失败
			outCode = "-16101";
		}
		
		
		if(portalList != null && portalList.size()>0){
			//根据用户权限赛选菜单数据
			portalList = AuthorityUtil.PortalMenuAuthority(user, portalList);
			jsonArr = new JSONArray();
			for(PcCdPortalMenuT p : portalList){
				tjo = new JSONObject();
				tjo.put("url", p.getPortalmenuUrl());
				tjo.put("text",p.getPortalmenuName());
				tjo.put("id",p.getPortalmenuid());
				tjo.put("pid",p.getFatherPortalmenuid());
				tjo.put("nodetype",p.getEnabled());
				if(p.getEnabled() != null && !"3".equals(String.valueOf(p.getEnabled()))){
					tjo.put("isexpand",false);
					tjo.put("children",childrenArr);
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
	public String getChridMenuTree() throws Exception{
		
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String menuid  = StringUtil.toStr(request.getParameter("menuid").trim());
		//获取session中用户信息
		User user = (User) session.getAttribute("userInfo");
		JSONArray jsonobj = systemTreeService.searchChridMenu(user,menuid);
        if(jsonobj != null){
			out.println(jsonobj);
		}
		return null;
	}
	/**
	 * 获取组织结构数据所有
	 * @return
	 * @throws Exception
	 */
	public String getMenuTreeData() throws Exception{
		
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		
		String menuid  = StringUtil.toStr(request.getParameter("menuid").trim());
		String menuname = "";
		String menusql = "";
		JSONArray jsonobj = null;
		User user = (User) session.getAttribute("userInfo");
		List<PcCdMenuT> menulist = user.getMenuList();
		
		if(menulist != null && menulist.size()>0){
			for(PcCdMenuT menu :menulist){
				
				if(menuid.equals(menu.getMenuid())){
					menuname = menu.getRightName();
					break;
				}
				
			}
			
		}
		if(menuname != null && !"".equals(menuname)){
			menusql = CommonsUtil.getMenuSQL(menuname,"","1");
			if(menusql != null && !"".equals(menusql)){
				jsonobj = systemTreeService.menuTreeData(menusql);
			}
			
		}
		
		if(jsonobj != null){
			out.println(jsonobj);
		}
		
		return null;
	}
	
	/**
	 * 获取炉线管理可选树列表
	 * @return
	 * @throws Exception
	 */
	public String getBLTreeData() throws Exception{
		
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		
		String menuname = "可选井口树";
		String menusql = "";
		JSONArray jsonobj = null;
			menusql = CommonsUtil.getMenuSQL(menuname,"","1");
			if(menusql != null && !"".equals(menusql)){
				jsonobj = systemTreeService.menuTreeData(menusql);
			}
			
		
		if(jsonobj != null){
			out.println(jsonobj);
		}
		
		return null;
	}
	/**
	 * 通过传入节点，过去该节点下子节点
	 * @return
	 * @throws Exception
	 */
	public String getOrgChridData() throws Exception{
		
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		
		String id = request.getParameter("id").trim();
		String menuid  = StringUtil.toStr(request.getParameter("menuid").trim());
		if(menuid != null && !"".equals(menuid)){
			menuid = menuid.substring(0, 7);
		}
		String menuname="";
		String menusql = "";
		JSONArray jsonobj =  new JSONArray();
		User user = (User) session.getAttribute("userInfo");
		List<PcCdMenuT> menulist = user.getMenuList();
		
		if(menulist != null && menulist.size()>0){
			for(PcCdMenuT menu :menulist){
				
				if(menuid.equals(menu.getMenuid())){
					menuname = menu.getRightName();
					break;
				}
				
			}
			
		}
		if(menuname != null && !"".equals(menuname)){
			menusql = CommonsUtil.getMenuSQL(menuname,id,"3");
			if(menusql != null && !"".equals(menusql)){
				//jsonobj = systemTreeService.menuTreeData(menusql);
				jsonobj =  systemTreeService.searchOrgChrid(menusql);
			}
			
		}
		
		out.println(jsonobj);
		return null;
	}
	
	
	/**
	 * 通过传入节点，过去该节点下子节点
	 * @return
	 * @throws Exception
	 */
	public String getBLOrgChridData() throws Exception{
		
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		
		String id = request.getParameter("id").trim();
		String menuname="可选井口树";
		String menusql = "";
		JSONArray jsonobj =  new JSONArray();
			menusql = CommonsUtil.getMenuSQL(menuname,id,"3");
			if(menusql != null && !"".equals(menusql)){
				//jsonobj = systemTreeService.menuTreeData(menusql);
				jsonobj =  systemTreeService.searchOrgChrid(menusql);
			}
			
		
		out.println(jsonobj);
		return null;
	}
	
	/**
	 * 通过传入节点，过去该节点下子节点
	 * @return
	 * @throws Exception
	 */
	public String getFilterOrgData() throws Exception{
		
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		
		String node = StringUtil.toStr(request.getParameter("node").trim());
		String menuid = StringUtil.toStr(request.getParameter("menuid").trim());
		String menuname="";
		String menusql = "";
		JSONArray jsonobj = null;
		User user = (User) session.getAttribute("userInfo");
		List<PcCdMenuT> menulist = user.getMenuList();
		
		if(menulist != null && menulist.size()>0){
			for(PcCdMenuT menu :menulist){
				
				if(menuid.equals(menu.getMenuid())){
					menuname = menu.getRightName();
					break;
				}
				
			}
			
		}
		if(menuname != null && !"".equals(menuname)){
			menusql = CommonsUtil.getMenuSQL(menuname,node,"2");
			if(menusql != null && !"".equals(menusql)){
				jsonobj = systemTreeService.searchFilter(menusql);
			}
			
		}
		
		if(jsonobj != null){
			out.println(jsonobj);
		}else{
			out.println("");
		}
		
		return null;
	}
	
	
	/**
	 * 通过传入节点id 获取节点详细数据
	 * @return
	 * @throws Exception
	 */
	public String getNodeType() throws Exception{
		
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		
		String nodeid = StringUtil.toStr(request.getParameter("nodeid").trim());
		JSONObject jsonobj = systemTreeService.searchNodeInfo(nodeid);
		if(jsonobj != null){
			out.println(jsonobj);
		}else{
			out.println("");
		}
		
		return null;
	}
	/**
	 * 获取组织结构数据-最小节点为数组结构类型为6  下拉树
	 * @return
	 * @throws Exception
	 */
	public String getStructure6Data() throws Exception{
		
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		
		JSONArray jsonobj = systemTreeService.searchStructure("6");
		if(jsonobj != null){
			out.println(jsonobj);
		}else{
			out.println("");
		}
		
		return null;
	}
	/**
	 * 获取组织结构数据-最小节点为数组结构类型为5 下拉树
	 * @return
	 * @throws Exception
	 */
	public String getStructure5Data() throws Exception{
		
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		
		JSONArray jsonobj = systemTreeService.searchStructure("5");
		if(jsonobj != null){
			out.println(jsonobj);
		}else{
			out.println("");
		}
		
		return null;
	}
	/**
	 * 判断用户选择节点是否为气井节点 如果是返回气井节点信息，如果不是提示用户选择气井
	 * @return
	 * @throws Exception
	 */
	public String getQjLocalStr() throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String	node =   request.getParameter("nodes").trim();	
		String localStr = systemTreeService.searchQjLocalStructure(node,"7");
		if(localStr != null && !"".equals(localStr)){
			out.println(localStr);
		}else{
			out.println("-2");
		}
		return null;
		
	}
	
	/**
	 * 获取当前节点所属组织结构
	 * @return
	 * @throws Exception
	 */
	public String getLocalStr() throws Exception{
		
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String	node =   request.getParameter("nodes").trim();	
		String localStr = systemTreeService.searchLocalStructure(node);
		if(localStr != null){
			out.println(localStr);
		}else{
			out.println("");
		}
		return null;
	}
	/**
	 * 获取集气站组织结构节点
	 * @return
	 * @throws Exception
	 */
	public String searchStructureJqz() throws Exception{
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String hql="FROM TbStructure s where s.structuretype=5";
		 JSONArray jsonobj = systemTreeService.searchStructureJqz(hql);
			if(jsonobj != null){
				out.println(jsonobj);
			}else{
				out.println("");
			}
		
		
		return null;
	}
	
	/**
	 * 获取排采队组织结构节点
	 * @return
	 * @throws Exception
	 */
	public String searchPcdStructure() throws Exception{
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONArray jsonobj = systemTreeService.searchPcdStructure();
		if(jsonobj != null){
			out.println(jsonobj);
		}else{
			out.println("");
		}
		return null;
	}
	
	
	/**
	 * 获取气井或井场自动补全数据
	 * @return
	 * @throws Exception
	 */
	public String searchAutoCompleteData() throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String flg = StringUtil.toStr(request.getParameter("flg").trim());
		JSONArray welljson =  systemTreeService.searchAutoCompleteData(flg);
		
		if(welljson != null){
			out.println(welljson);
		}else{
			out.println("");
		}
		return null;
	}
	
	
	
}
