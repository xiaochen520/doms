package com.echo.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.echo.dto.PcCdPortalMenuT;
import com.echo.dto.PcCdPortalParamT;
import com.echo.dto.PcCdRoleT;
import com.echo.dto.PcRdLoginfoT;
import com.echo.dto.User;
import com.echo.service.LogService;
import com.echo.service.PortalService;
import com.echo.util.AuthorityUtil;
import com.echo.util.CommonsUtil;
import com.echo.util.PropertyUtil;
import com.echo.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.inject.util.Strings;

public class PortalAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4154345270195887462L;
	private PortalService  portalService;
	private LogService logService;
	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
	

	public void setPortalService(PortalService portalService) {
		this.portalService = portalService;
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
		
		String menuJson = null;
		try {
			menuJson = AuthorityUtil.getMenuJson("Portal菜单维护", user);
		} catch (Exception e) {
			e.printStackTrace();
			outCode = "-10004";
		}
		
		if(menuJson != null && "1".equals(outCode)){
			out.print(menuJson);
		}else{
			out.print(outCode);
		}
		
		return null;
	}
	
	public String addPortalNode() throws Exception {
		JSONObject obj = new JSONObject();
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String errorMsg = "";
		PcCdRoleT role = new PcCdRoleT();
		User userinfo = (User) session.getAttribute("userInfo");
		String pid =  StringUtil.toStr(StringUtil.isNullStr(request.getParameter("pid")));
		String nodetype =  StringUtil.toStr(StringUtil.isNullStr(request.getParameter("nodetype")));
		String menuname =  StringUtil.toStr(StringUtil.isNullStr(request.getParameter("menuname")));
		String menuaddr =  StringUtil.toStr(StringUtil.isNullStr(request.getParameter("menuaddr")));
		String MENU_ORDER =  StringUtil.toStr(StringUtil.isNullStr(request.getParameter("MENU_ORDER")));
		String RIGHTS =  StringUtil.toStr(StringUtil.isNullStr(request.getParameter("RIGHTS")));  //
	
		String portalid = "";
		List<PcCdPortalMenuT> portalList = null;
		PcCdPortalMenuT portalO = new PcCdPortalMenuT();
		
		boolean addflg = true;
		try {
			portalList = portalService.searchPortal("", menuname);
		} catch (Exception e) {
			e.printStackTrace();
			obj = CommonsUtil.getRrturnJson("", "PORTAL菜单数据获取错误", "",null, null);
			out.print(obj);
			return null;
		}
		if(portalList != null && portalList.size()>0){
			obj = CommonsUtil.getRrturnJson("", "新建PORTAL节点名称已存在，请使用其他名称！", "",null, null);
			out.print(obj);
			return null;
		}else{
			
			portalO.setPortalmenuName(menuname);
			if(pid != null && !"".equals(pid)){
				portalO.setFatherPortalmenuid(pid);
			}else{
				obj = CommonsUtil.getRrturnJson("", "未获取当前节点父节点ID，请重新选择节点或联系管理员", "",null, null);
				out.print(obj);
				return null;
			}
			
			portalO.setPortalmenuUrl(menuaddr);
			if(nodetype != null && !"".equals(nodetype)){
				int lvl = Integer.parseInt(nodetype) +1;
				portalO.setEnabled((byte) lvl);
			}else{
				obj = CommonsUtil.getRrturnJson("", "未获取当前节点等级，请重新选择节点或联系管理员", "",null, null);
				out.print(obj);
				return null;
			}
			portalO.setMenuOrder(MENU_ORDER);
			
		}
		boolean operFlag = true;
			try {
				addflg = portalService.addPortal(portalO);
				
				String updateRowDate =  StringUtil.toStr(StringUtil.isNullStr(request.getParameter("updateRowDate")));
				//String[] paramData = updateRowDate.split(",");
				List<PcCdPortalParamT> paramList = null;
				PcCdPortalParamT  param = new PcCdPortalParamT();
				String[] jsonArgs = {"PARAMNAME","PARAMVALUE"};
				String[] filedName ={"paramname","paramvalue"}; 
				JSONArray jsonArr = JSONArray.fromObject(updateRowDate);
				JSONObject jsonObj = null;
				for (Object jo : jsonArr) {
					jsonObj = JSONObject.fromObject(jo);
					
					String  portalParamId= jsonObj.get("PARAMID").toString();
					//paramList =portalService.searchPar(portalParamId);
					//String portalmenuid = jsonObj.get("PORTALMENUID").toString();
					String paramname = jsonObj.get("PARAMNAME").toString();
					String paramvalue = jsonObj.get("PARAMVALUE").toString();
					param = new PcCdPortalParamT();
					param.setPortalParamId(portalParamId);
					//param.setPortalmenuid(pid);
					param.setParamname(paramname);
					param.setParamvalue(paramvalue);
					String portalmenuid  = portalService.searchMenuname(menuname);
					param.setPortalmenuid(portalmenuid);
					try {
						operFlag = portalService.addOrUpdatePortal(param);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				obj = CommonsUtil.getRrturnJson("", "PORTAL节点："+menuname+"添加失败", "",null, null);
				out.print(obj);
				return null;
			}
			portalid = portalO.getPortalmenuid();
			boolean insertflg = true;
			if(operFlag== true && addflg== true){
				//添加显示权限
				if("true".equals(RIGHTS)){
					portalList = portalService.searchPortal("", menuname);
					if(portalList != null && portalList.size()>0){
						portalid = portalList.get(0).getPortalmenuid();
						String sql = "insert into pc_cd_right_t  values(sys_guid(),'"+portalid+"','显示功能菜单','"+menuname+"',0,'显示功能菜单-"+menuname+"',null,1)";
						try {
							insertflg = portalService.removeParams(sql);
						} catch (Exception e) {
							e.printStackTrace();
							obj = CommonsUtil.getRrturnJson("", "PORTAL节点："+menuname+"添加成功，显示权限添加失败", "",null, null);
							out.print(obj);
							return null;
						}
						
					}else{
						obj = CommonsUtil.getRrturnJson("", "PORTAL节点："+menuname+"添加失败", "",null, null);
						out.print(obj);
						return null;
						
					}
					
					
					
				}
				
				//添加系统LOG
				try {
					PcRdLoginfoT log = CommonsUtil.getLogInfo(userinfo, "1", "Portal菜单维护", portalO.getPortalmenuid());
					logService.addLog(log);
				} catch (Exception e) {
					e.printStackTrace();
					obj = CommonsUtil.getRrturnJson("", "添加操作LOG 添加失败", "",null, null);
				}
			}
		
		JSONObject jsonobj  = new JSONObject();
		if(obj != null && obj.get("ERRMSG") != null && !"".equals(obj.get("ERRMSG"))){
			out.print(obj);
			return null;
		}else{
			jsonobj.put("url", portalO.getPortalmenuUrl());
			jsonobj.put("text",portalO.getPortalmenuName());
			jsonobj.put("id",portalO.getPortalmenuid());
			jsonobj.put("pid",portalO.getFatherPortalmenuid());
			jsonobj.put("nodetype",portalO.getEnabled());
			if(portalO.getEnabled() != null && !"3".equals(String.valueOf(portalO.getEnabled()))){
				jsonobj.put("isexpand",false);
				jsonobj.put("children",new JSONArray());
			}
			
			obj = CommonsUtil.getRrturnJson("", "", "",jsonobj, null);
		}
		

		
		out.print(obj);
		return null;
	}
	
	public String updatePortalNode() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String errorMsg = "";
		PcCdRoleT role = new PcCdRoleT();
		User userinfo = (User) session.getAttribute("userInfo");
		String id =  StringUtil.toStr(StringUtil.isNullStr(request.getParameter("id")));
		//String nodetype =  StringUtil.toStr(StringUtil.isNullStr(request.getParameter("nodetype")));
		String menuname =  StringUtil.toStr(StringUtil.isNullStr(request.getParameter("menuname")));
		String menuaddr =  StringUtil.toStr(StringUtil.isNullStr(request.getParameter("menuaddr")));
		String MENU_ORDER =  StringUtil.toStr(StringUtil.isNullStr(request.getParameter("MENU_ORDER")));
		String RIGHTS =  StringUtil.toStr(StringUtil.isNullStr(request.getParameter("RIGHTS")));  //
		String RIGHTID = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("RIGHTID")));
		JSONObject obj = new JSONObject();
		List<PcCdPortalMenuT> portalList = null;
		PcCdPortalMenuT portalO = null;
		
		boolean addflg = true;
		
		if("".equals(id)){
			obj = CommonsUtil.getRrturnJson("", "未获取PORTAL菜单数据ID，请重新选择", "",null, null);
			out.print(obj);
			return null;
		}
		try {
			portalList = portalService.searchPortal("", menuname);
		} catch (Exception e) {
			e.printStackTrace();
			obj = CommonsUtil.getRrturnJson("", "PORTAL菜单数据获取错误", "",null, null);
			out.print(obj);
			return null;
		}
		
		if(portalList != null && portalList.size()>0){
			if(!id.equals(portalList.get(0).getPortalmenuid())){
				obj = CommonsUtil.getRrturnJson("", "PORTAL节点名称已存在，请使用其他名称！", "",null, null);
				out.print(obj);
				return null;
			}
			
		}
		
		portalList = portalService.searchPortal(id, "");
		
		if(portalList != null && portalList.size()>0){
			portalO = portalList.get(0);
		}else{
			obj = CommonsUtil.getRrturnJson("", "未获取PORTAL菜单数据，请重新选择", "",null, null);
			out.print(obj);
			return null;
			
		}
		portalO.setPortalmenuName(menuname);
		portalO.setPortalmenuUrl(menuaddr);
		portalO.setMenuOrder(MENU_ORDER);
			
		boolean operFlag = true;
		boolean deleteflg = true;
		if("".equals(errorMsg)){
			try {
				String sql  = "delete from PC_CD_PORTAL_PARAM_T   where PORTALMENUID = '"+id+"'"; 
				
				try {
					deleteflg = portalService.removeParams(sql);
				} catch (Exception e) {
					e.printStackTrace();
					obj = CommonsUtil.getRrturnJson("", "数据更新失败", "",null, null);
					out.print(obj);
					return null;
				}
					
				if(!deleteflg){
					obj = CommonsUtil.getRrturnJson("", "数据更新失败", "",null, null);
					out.print(obj);
					return null;
					
				}
				
				operFlag = portalService.addPortal(portalO);
				String updateRowDate =  StringUtil.toStr(StringUtil.isNullStr(request.getParameter("updateRowDate")));
//				List<PcCdPortalParamT> paramList = null;
				PcCdPortalParamT  param = new PcCdPortalParamT();
//				String[] jsonArgs = {"PARAMNAME","PARAMVALUE"};
//				String[] filedName ={"paramname","paramvalue"}; 
				JSONArray jsonArr = JSONArray.fromObject(updateRowDate);
				JSONObject jsonObj = null;
				for (Object jo : jsonArr) {
					jsonObj = JSONObject.fromObject(jo);
					
					String  portalParamId= jsonObj.get("PARAMID").toString();
					//paramList =portalService.searchPar(portalParamId);
					//String portalmenuid = jsonObj.get("PORTALMENUID").toString();
					String paramname = jsonObj.get("PARAMNAME").toString();
					String paramvalue = jsonObj.get("PARAMVALUE").toString();
					param = new PcCdPortalParamT();
					param.setPortalParamId(portalParamId);
					//param.setPortalmenuid(pid);
					param.setParamname(paramname);
					param.setParamvalue(paramvalue);
//					String portalmenuid  = portalService.searchMenuname(menuname);
					param.setPortalmenuid(id);
					try {
						operFlag = portalService.addOrUpdatePortal(param);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsg = "PORTAL节点："+menuname+"修改失败";
			}
			
			
		}
			boolean insertflg = true;
			if("".equals(errorMsg) && addflg== true){
				String sql ="";
				//添加显示权限
				if("true".equals(RIGHTS)){
					
					if(RIGHTID != null && !"".equals(RIGHTID)){
						sql = "update  pc_cd_right_t set menu_code='"+menuname+"',REMARK='显示功能菜单-"+menuname+"' where rightid='"+RIGHTID+"'";
						try {
							insertflg = portalService.removeParams(sql);
						} catch (Exception e) {
							e.printStackTrace();
							obj = CommonsUtil.getRrturnJson("", "PORTAL节点："+menuname+"添加成功，显示权限修改失败", "",null, null);
							out.print(obj);
							return null;
						}
						// 不存在添加
					}else{
						sql = "insert into pc_cd_right_t  values(sys_guid(),'"+id+"','显示功能菜单','"+menuname+"',0,'显示功能菜单-"+menuname+"',null,1)";
						try {
							insertflg = portalService.removeParams(sql);
						} catch (Exception e) {
							e.printStackTrace();
							obj = CommonsUtil.getRrturnJson("", "PORTAL节点："+menuname+"添加成功，显示权限添加失败", "",null, null);
							out.print(obj);
							return null;
						}
					}
						
				}else{
					if(RIGHTID != null && !"".equals(RIGHTID)){
						List<String> sqls = new ArrayList<String>();
						sql = "delete from pc_cd_role_right_t where rightid='"+RIGHTID+"'";
						sqls.add(sql);
						sql = "delete from pc_cd_right_t where rightid='"+RIGHTID+"'";
						sqls.add(sql);
						try {
							insertflg = portalService.buthEquerSql(sqls);
						} catch (Exception e) {
							e.printStackTrace();
							obj = CommonsUtil.getRrturnJson("", "PORTAL节点："+menuname+"添加成功，显示权限删除失败", "",null, null);
							out.print(obj);
							return null;
						}
						// 不存在添加
					}
					
					
				}
				//添加系统LOG
				try {
					PcRdLoginfoT log = CommonsUtil.getLogInfo(userinfo, "3", "Portal菜单维护", portalO.getPortalmenuid());
					logService.addLog(log);
				} catch (Exception e) {
					e.printStackTrace();
					errorMsg = "修改操作LOG 添加失败";
				}
			}
		
		JSONObject jsonobj  = new JSONObject();
		if(!"".equals(errorMsg)){
			obj = CommonsUtil.getRrturnJson("", errorMsg, "",null, null);
		}else{
			jsonobj.put("url", portalO.getPortalmenuUrl());
			jsonobj.put("text",portalO.getPortalmenuName());
			jsonobj.put("id",portalO.getPortalmenuid());
			jsonobj.put("pid",portalO.getFatherPortalmenuid());
			jsonobj.put("nodetype",portalO.getEnabled());
			if(portalO.getEnabled() != null && !"3".equals(String.valueOf(portalO.getEnabled()))){
				jsonobj.put("isexpand",false);
				jsonobj.put("children",new JSONArray());
			}
			
			obj = CommonsUtil.getRrturnJson("", "", "",jsonobj, null);
		}
		

		
		out.print(obj);
		return null;
	}
	
	public String seachUpdateData()throws Exception {
		
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONObject obj = new JSONObject();
		String id = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("id")));

		List<Object[]> portalList = null;
		try {
			String sql ="select a.portalmenuid,a.portalmenu_name,a.father_portalmenuid,a.portalmenu_url,a.menu_order,b.rightid from pc_cd_portal_menu_t a  "+
						" left join  pc_cd_right_t b on a.portalmenuid = b.menuid "+
						" where a.portalmenuid = '"+id+"'";
			portalList = AuthorityUtil.getDatas(sql);
			
		} catch (Exception e) {
			e.printStackTrace();
			obj = CommonsUtil.getRrturnJson("", "PORTAL菜单节点更新初始化数据获取失败","", null, null);
			out.print(obj);
			return null;
		}
		
		
		if(portalList != null && portalList.size()>0){
			obj.put("PORTALMENUID", CommonsUtil.getClearNullData(portalList.get(0)[0]));
			obj.put("MENUORDER", CommonsUtil.getClearNullData(portalList.get(0)[4]));
			obj.put("RIGHTID", CommonsUtil.getClearNullData(portalList.get(0)[5]));
			
		}else{
			
			obj = CommonsUtil.getRrturnJson("", "PORTAL菜单节点更新初始化数据获取失败","", null, null);
			out.print(obj);
			return null;
		}
		
	
		
		out.print(obj);
		return null;
		
	}
	public String seachPortalMenu() throws Exception {
			HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
			HttpServletResponse response=ServletActionContext.getResponse();//响应对象
			response.setCharacterEncoding("utf-8");
			response.setHeader("ContentType","text/xml");
			PrintWriter out = response.getWriter();
			String outCode = "1";
			JSONArray jsonArr =  null;
			JSONArray childrenArr =  new JSONArray();
			JSONObject tjo = null;
			String nodeid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("nodeid")));

			List<PcCdPortalMenuT> portalList = null;
			try {
				if("".equals(nodeid)){
					portalList = portalService.searchPortal("AD13630FDC324D90859F5AD363A3DKKA" ,"");
				}else{
					
					String sql = "FROM PcCdPortalMenuT P WHERE 1=1 and  P.fatherPortalmenuid = '"+nodeid+"' order by nlssort(P.menuOrder , 'NLS_SORT=SCHINESE_STROKE_M')"	;
					portalList = portalService.searchPortals(sql);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				//portalmenu数据获取失败
				outCode = "-16101";
			}
			
			
			if(portalList != null && portalList.size()>0){
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
	
	public String seachPor() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		JSONArray jsonArr =  null;
		JSONObject tjo = null;
		String nodeid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("id")));
		List<Object[]> paramList = null;
		try {
//			if("".equals(nodeid)){
//				portalList = portalService.searchPortal("AD13630FDC324D90859F5AD363A3DKKA" ,"");
//			}else{
				
//				String sql = "FROM PcCdPortalParamT P WHERE 1=1 and  P.portalmenuid= '"+nodeid+"' order by nlssort( P.paramname , 'NLS_SORT=SCHINESE_STROKE_M')"	;
//				portalList = portalService.searchPor(sql);
			//}
			String sql ="select a.portal_param_id , a.portalmenuid,a.paramname,a.paramvalue,a.remark  from  pc_cd_portal_param_t a left join pc_cd_portal_menu_t b  on  a.portalmenuid = b.portalmenuid where a.portalmenuid='"+nodeid+"'";
			 paramList = portalService.searchParam(sql);
		} catch (Exception e) {
			e.printStackTrace();
			//portalmenu数据获取失败
			outCode = "-16101";
		}
		if(paramList != null && paramList.size()>0){
			jsonArr = new JSONArray();
			for(Object[] p : paramList){
				tjo = new JSONObject();
				tjo.put("PARAMID",p[0]);
				tjo.put("PORTALMENUID", p[1]);
				tjo.put("PARAMNAME", p[2]);
				tjo.put("PARAMVALUE",p[3]);
				tjo.put("remark",p[4]);
				jsonArr.add(tjo);
			}
		}
		
		if(jsonArr != null &&  "1".equals(outCode)){
			tjo = new JSONObject();
			tjo.put("Rows",jsonArr);
			tjo.put("Total",paramList.size());
			out.println(tjo);
		}else{
			out.print(outCode);
		}
		
	return null;
}
	public String removePortalNode() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String errorMsg = "";
		String nowid = StringUtil.toStr(request.getParameter("nowid").trim());
		String nodetype = StringUtil.toStr(request.getParameter("nodetype").trim());
		boolean deleteflg = true;
		JSONObject obj = new JSONObject();
		List<Object[]> remList = new ArrayList<Object[]>();
		if(nowid != null && !"".equals(nowid)){
			//获取当前节点，及其下所有叶子
			String sql = "select  a.*,b.rightid from "+
				"(select * from pc_cd_portal_menu_t "+
						"	start with  portalmenuid='"+nowid+"'"+
						"	connect by prior portalmenuid = father_portalmenuid ) a left join pc_cd_right_t b on a.portalmenuid = b.menuid";
			try {
				remList = portalService.searchPortal(sql);
			} catch (Exception e) {
				e.printStackTrace();
				errorMsg = "当前节点，及其下所有叶子节点获取失败，请重新选择";
			}
			
			if(remList != null && remList.size()>0){
				
				String outStr = "";
				for(Object[] onfo :remList){
					if(onfo[6] != null && !"".equals(CommonsUtil.getClearNullData(onfo[6]))){
						outStr += CommonsUtil.getClearNullData(onfo[1])+",";
					}
				}
				if(outStr != null && !"".equals(outStr)){
					
					obj = CommonsUtil.getRrturnJson("", "节点删除失败："+outStr.substring(0, outStr.length()-2)+"用有显示权限，请删除对应显示权限在删除该节点","", null, null);
					out.print(obj);
					return null;
				}
				try {
					String [] remIds = new String[remList.size()];
					for(int i = 0 ; i<remList.size(); i++){
						remIds[i] = remList.get(i)[0].toString();
					}
					//String paramID = portalService.searchParamId(nowid);
					try {
						deleteflg = portalService.removePortal(remIds);
					} catch (Exception e) {
						errorMsg = "当前节点及其下所有叶子节点删除失败";
						e.printStackTrace();
					}
					//deleteflg = portalService.removePortal(remIds);
				} catch (Exception e) {
					e.printStackTrace();
					errorMsg = "当前节点及其下所有叶子节点删除失败";
				}
			}else{
				errorMsg = "数据库中不存在节点相关数据请重新选择节点";
			}
			
			
		}
		
//		
//		
		if(deleteflg && "".equals(errorMsg)){
			//添加系统LOG
			try {
				User userinfo = (User) session.getAttribute("userInfo");
				PcRdLoginfoT log = CommonsUtil.getLogInfo(userinfo, "2", "Portal菜单维护", nowid);
				logService.addLog(log);
			} catch (Exception e) {
				e.printStackTrace();
				errorMsg = "删除操作LOG 添加失败";
			}
		}
		
		
		if(!"".equals(errorMsg)){
			obj = CommonsUtil.getRrturnJson("", errorMsg,"", null, null);
		}
		out.print(obj);
		return null;
	}
	//打开连接
	public  String openPortalMenu()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONObject obj = new JSONObject();
		String url = "";
		String id = StringUtil.toStr(StringUtil.toStr(request.getParameter("id")));
		User user = (User) session.getAttribute("userInfo");
		try {
			url = portalService.searchOpenMenuId(id,user);
		} catch (Exception e) {
			e.printStackTrace();
			obj = CommonsUtil.getRrturnJson("", "PORTAL菜单地址获取失败","", null, null);
			out.print(obj);
			return null;
		}
		
		if("".equals(url) || url.length() == 0){
			obj = CommonsUtil.getRrturnJson("", "无法获取PORTAL菜单地址，请联系管理员","", null, null);
			out.print(obj);
			return null;
		}
		
		out.print(obj = CommonsUtil.getRrturnJson("", "",url, null, null));
		return null;
	}
	
}
