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
import com.echo.dto.PcCdAreaInfoT;
import com.echo.dto.PcCdOrgT;
import com.echo.dto.PcCdZoneT;
import com.echo.dto.PcRdLoginfoT;
import com.echo.dto.User;
import com.echo.service.AreaInfoService;
import com.echo.service.LogService;
import com.echo.util.AuthorityUtil;
import com.echo.util.CommonsUtil;
import com.echo.util.PageVariableUtil;
import com.echo.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

public class AreaInfoAction extends ActionSupport {
	private static final long serialVersionUID = 12L;
	HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
	private AreaInfoService areaInfoService;
	
	

	public AreaInfoService getAreaInfoService() {
		return areaInfoService;
	}

	public void setAreaInfoService(AreaInfoService areaInfoService) {
		this.areaInfoService = areaInfoService;
	}

	private LogService logService;

	public LogService getLogService() {
		return logService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	public String searchAreaInfo() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		
		String stationNumber = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("areablock")));
		String boilersName = "";
		String areablock ="";
		String outCode = "1";
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
			jsonobj = areaInfoService.searchAreaInfo(stationNumber, boilersName, areablock,pageNo,sort,order,rowsPerpage);
		} catch (Exception e) {
			outCode = "-17101";
		}
		
		if(jsonobj != null && "1".equals(outCode)){
			out.println(jsonobj);
			
		}else{
			out.println(outCode);
		}
		
	return null;

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
//		
		String gridJson = null;
		try {
			gridJson = AuthorityUtil.getGridJson("区块基础信息", user, PageVariableUtil.AREAINFO_PAGE_GRID);
		} catch (Exception e) {
			e.printStackTrace();
			outCode = "-10004";
		}
		
		if(gridJson != null && "1".equals(outCode)){
			//System.out.println(gridJson);
			out.print(gridJson);
		}else{
			out.print(outCode);
		}
		return null;
	}
	
	@SuppressWarnings("unused")
	public String removeArea() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		String stationId = StringUtil.toStr(request.getParameter("qkId"));
		String org_id = StringUtil.toStr(request.getParameter("org_id"));
		boolean deleteflg = false;
		JSONObject obj = new JSONObject();
		try {
			deleteflg = areaInfoService.removeStationInfo(stationId ,org_id);
		} catch (Exception e) {
			String errmsg = e.getCause().getCause().toString();
			//ORA-02292: 违反完整约束条件 (NEWPORTAL.FK58_PC_RPD_WELL_SAGDD_T) - 已找到子记录
			if(!"".equals(errmsg) && errmsg.indexOf("ORA-02292") != -1 && errmsg.indexOf("FK58_PC_RPD_WELL_SAGDD_T") != -1){
				obj = CommonsUtil.getRrturnJson("","区块基础信息删除失败-区块日报中存在子记录" ,"", null, null);
			}else if(!"".equals(errmsg) && errmsg.indexOf("ORA-02292") != -1 && errmsg.indexOf("FK_PC_RPD_W_FK52_PC_R_PC_CD_WE") != -1){
				obj = CommonsUtil.getRrturnJson("","区块基础信息删除失败-区块班报中存在子记录" ,"", null, null);
			} else{
				obj = CommonsUtil.getRrturnJson("",errmsg ,"", null, null);
			}
			e.printStackTrace();
		}
		
		if(deleteflg){
			
			//添加系统LOG
			try {
				User user1 = (User) session.getAttribute("userInfo");
				PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "2", "区块信息", stationId);
				logService.addLog(log);
			} catch (Exception e) {
				e.printStackTrace();
				obj = CommonsUtil.getRrturnJson("-10003", "", "",null, null);
			}
			
			
		}
		out.print(obj);
		return null;
	}

	@SuppressWarnings("unused")
	public  String addArea() throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONObject obj = new JSONObject();
		String outCode = "1";
		boolean  addflg = true;
		
		List<PcCdAreaInfoT> areaList = null;
		PcCdAreaInfoT area = null ;
		String qkmc = StringUtil.toStr(request.getParameter("qkmc").trim());
		String qkmcS = StringUtil.toStr(request.getParameter("qkmc_s").trim());
		try {
			areaList  =  areaInfoService.searchQkmc(qkmc);
		} catch (Exception e) {
			e.printStackTrace();
			//区块名称获取失败
			outCode="-11210";
		}
		if(areaList !=null && areaList.size()>0){
			//区块名称已存在
			outCode="-11211";
		}else{
			if(qkmcS!=null && !"".equals(qkmcS)){
				try {
					areaList = areaInfoService.searchByQkmc_s("",qkmcS);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(areaList.size()>0){
					obj = CommonsUtil.getRrturnJson("-11220", "区块代码已存在","", null, null);
					out.print(obj);
					return null;
				}
			}
			area = new PcCdAreaInfoT();
			area.setQkmc(qkmc);
			area.setQkmcS(StringUtil.toStr(request.getParameter("qkmc_s").trim()));
			String zone = StringUtil.toStr(request.getParameter("zone").trim());
			List<PcCdZoneT> zonelist = null;
			try {
				zonelist = areaInfoService.searchZonefo(zone,"");;
			} catch (Exception e) {
				e.printStackTrace();
				outCode ="11215";
				//区类代码获取失败
			}
			if("1".equals(outCode) && zonelist != null && zonelist.size()>0){
				PcCdZoneT pcCdZoneT = zonelist.get(0);
				area.setPcCdZoneT(pcCdZoneT);
				pcCdZoneT.setZoneName(zone);
				PcCdOrgT org = new PcCdOrgT();
				org.setStructurename(qkmc);
				org.setStructuretype((byte)6);
				org.setPcCdAreaInfoT(area);
				area.setPcCdOrgT(org);
				//pcCdZoneT.setPcCdAreaInfoT(area);
				
			try {
					 addflg = areaInfoService.addArea(area);
				} catch (Exception e) {
					e.printStackTrace();
					//区块添加失败
					outCode="-11212";
				}
			}
		}
		out.print(outCode);
		return  null;
	}
	@SuppressWarnings("unused")
	public  String updateArea() throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONObject obj = new JSONObject();
		String outCode = "1";
		boolean  addflg = true;
		
		List<PcCdAreaInfoT> areaList = null;
		PcCdAreaInfoT area = null ;
		String qkid = StringUtil.toStr(request.getParameter("qkid").trim());
		String qkmc = StringUtil.toStr(request.getParameter("qkmc").trim());
		String zone = StringUtil.toStr(request.getParameter("zone").trim());
		String qkmcS = StringUtil.toStr(request.getParameter("qkmc_s").trim());
		if(qkid != null && !"".equals(qkid)){
		if(qkmcS!=null && !"".equals(qkmcS)){
			try {
				areaList = areaInfoService.searchByQkmc_s(qkid,qkmcS);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(areaList.size()>0){
				obj = CommonsUtil.getRrturnJson("-11220", "区块代码已存在","", null, null);
				out.print(obj);
				return null;
			}
		}
		try {
			areaList  =  areaInfoService.searchByName("",qkmc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(areaList !=null && areaList.size()==1){
			//同条件数据进行修改
			if(!qkid.equals(areaList.get(0).getQkid())){
			outCode="-11211";
			
			}
		
		}else if(areaList !=null && areaList.size()>1){
			//数据库中存在多条数据
			outCode="-11211";
		}else{
			try {
				areaList = new ArrayList<PcCdAreaInfoT>();
				areaList = areaInfoService.searchByName(qkid, "");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
			if(qkid !=null && !"".equals(qkid) && "1".endsWith(outCode)){
				area=areaList.get(0);
				List<PcCdZoneT> zonelist = null;
				try {
					zonelist = areaInfoService.searchZonefo(zone,"");
				} catch (Exception e) {
					e.printStackTrace();
					outCode ="";
				}
				if( zonelist != null && zonelist.size()>0){
					PcCdZoneT pcCdZoneT = zonelist.get(0);
					area.setQkmc(qkmc);
					area.setQkmcS(StringUtil.toStr(request.getParameter("qkmc_s").trim()));
					area.setPcCdZoneT(pcCdZoneT);
					PcCdOrgT org = null;
					
					org = area.getPcCdOrgT();
					
					org.setStructurename(qkmc);
					org.setStructuretype((byte)6);
					org.setPcCdAreaInfoT(area);
					area.setPcCdOrgT(org);
				try {
					addflg = areaInfoService.updateArea(area);
				} catch (Exception e) {
					e.printStackTrace();
					//区块更新失败
					outCode="-11214";
				}
			}
			}
		}else{
			outCode="-11213";
			//区块id获取失败
		}
		out.print(outCode);
		return  null;
	}
	
	public String searchZoneInfo() throws Exception {
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		JSONArray zoneArr = null; 
		JSONObject tjo = null;
		try {
			List<PcCdZoneT> zones= areaInfoService.searchZonefo("","");
			
			
			if(zones != null && zones.size()>0){
				zoneArr = new JSONArray();
				for(PcCdZoneT z :zones){
					tjo = new JSONObject();
					tjo.put("id",z.getZoneCode());
					tjo.put("text",z.getZoneName());
					zoneArr.add(tjo);
				}
			}
		} catch (Exception e) {
			outCode = "";
		}
		
		if(zoneArr != null && "1".equals(outCode)){
			out.println(zoneArr);
			
		}else{
			out.println(outCode);
		}
		
	return null;
	}
}