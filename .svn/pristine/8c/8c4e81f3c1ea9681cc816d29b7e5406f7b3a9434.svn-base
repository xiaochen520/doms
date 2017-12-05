package com.echo.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.echo.dto.PcCdAreaInfoT;
import com.echo.dto.PcCdGasstation;
import com.echo.dto.PcCdOrgT;
import com.echo.dto.PcCdServerNodeT;
import com.echo.dto.PcRdLoginfoT;
import com.echo.dto.User;
import com.echo.service.AreaInfoService;
import com.echo.service.CommonService;
import com.echo.service.GasstationService;
import com.echo.service.LogService;
import com.echo.service.OilDrillingService;
import com.echo.service.OrgService;
import com.echo.service.SagdService;
import com.echo.util.AuthorityUtil;
import com.echo.util.CommonsUtil;
import com.echo.util.DateBean;
import com.echo.util.PageVariableUtil;
import com.echo.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

public class GasstationAction extends ActionSupport  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 12L;
	private GasstationService gasstationService;
	private AreaInfoService areaInfoService;
	private OilDrillingService oilDriService;
	private  CommonService  commonService;
	private SagdService sagdService;
	public void setAreaInfoService(AreaInfoService areaInfoService) {
		this.areaInfoService = areaInfoService;
	}

	private OrgService orgService;
	public void setOrgService(OrgService orgService) {
		this.orgService = orgService;
	}
	private  LogService logService;
	HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
	
	public GasstationService getGasstationService() {
		return gasstationService;
	}

	public void setGasstationService(GasstationService gasstationService) {
		this.gasstationService = gasstationService;
	}
	
	public LogService getLogService() {
		return logService;
	}
	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	public void setOilDriService(OilDrillingService oilDriService) {
		this.oilDriService = oilDriService;
	}
	public void setSagdService(SagdService sagdService) {
		this.sagdService = sagdService;
	}
	public String searchStation() throws Exception {
			HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
			HttpServletResponse response=ServletActionContext.getResponse();//响应对象
			response.setCharacterEncoding("utf-8");
			response.setHeader("ContentType","text/xml");
			PrintWriter out = response.getWriter();
			
			String stationNumber = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("oilstationname")));
			String boilersName = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("areablock")));
			String areablock = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("blockstationname")));
			String lhz = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("combination")));
			String zztype = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("blockstationtype")));
			String dyearC = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("dyearC")));
			String jrbz = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("jrbz")));
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
				jsonobj = gasstationService.queryStationBasicInfo(jrbz,stationNumber,lhz,boilersName,areablock,zztype,dyearC,pageNo,sort,order,rowsPerpage);
			} catch (Exception e) {
				outCode = "-11101";
			}
			
			if(jsonobj != null && "1".equals(outCode)){
				out.println(jsonobj);
				
			}else{
				out.println(outCode);
			}
			
		return null;
	}
	
	public String queryOilSationInfo() throws Exception {
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONArray jsonArr = null;
		try {
			jsonArr = gasstationService.queryOilSationInfo();
		} catch (Exception e) {
			this.addFieldError("errermsg", "采油站信息获取失败~");
			e.printStackTrace();
		}
		if(jsonArr != null){
			out.println(jsonArr);
		}else{
			out.println("");
		}
		return null;
	}
	
	public String queryAreablockInfo() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String arg = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("orgid")));
		JSONArray jsonArr = null;
		
		try {
			jsonArr = gasstationService.queryAreablockInfo(arg);
		} catch (Exception e) {
			this.addFieldError("errermsg", "区块信息获取失败~");
			e.printStackTrace();
		}
		if(jsonArr != null){
			out.println(jsonArr);
		}else{
			out.println("");
		}
		return null;
	}
	
	
	public String queryStationInfo() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String combinationid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("combinationid")));
		String areaid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("areaid")));
		String selecteValue = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("selecteValue")));
		String oilid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("oilid")));
		JSONArray jsonArr = null;
		try {
			jsonArr = gasstationService.queryStationInfo(combinationid,oilid,areaid,selecteValue);
		} catch (Exception e) {
			// TODO: handle exception
			this.addFieldError("errermsg", "供热站信息获取失败~");
			e.printStackTrace();
		}
		if(jsonArr != null){
			out.println(jsonArr);
		}else{
			out.println("");
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
			gridJson = AuthorityUtil.getGridJson("气站基础信息", user, PageVariableUtil.GASSTATIONT_PAGE_GRID);
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
	
	public String addSta() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		List<PcCdGasstation> staList = null;
		List<Object[]> orgList = null;
		JSONObject obj = new JSONObject();
		PcCdGasstation sta=null;
		
		//String oildrilling_name = request.getParameter("oildrilling_name").trim();
		//String stationid = request.getParameter("stationid").trim();
		String blockstationname = StringUtil.toStr(request.getParameter("blockstation_name").trim());
		String code = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("code")));
		String orgid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("org_id")));
		  
			try {
				staList = gasstationService.searchstatinByName(blockstationname);
			} catch (Exception e) {
				e.printStackTrace();
				//注转战名称获取信息失败
				outCode="-11110";
				
			}
			if(staList != null && staList.size() >0){
				//气站站名称已存在，请使用其他名称
				outCode = "-10111";
				
			}else{
				if(code!=null && !"".equals(code)){
					String sql = "select * from pc_cd_gas_station_t s left join pc_cd_org_t o on s.org_id=o.org_id where 1=1";
					orgList =  orgService.searchOrg(code,orgid, sql);
					if(orgList.size()>0){
						obj = CommonsUtil.getRrturnJson("-11220", "气站编码已存在","", null, null);
						out.print(obj);
						return null;
					}
				}
				boolean addflg = true;
				User sessionuser = (User) session.getAttribute("userInfo");
					sta = new PcCdGasstation();
					
					sta.setStationName(blockstationname);
					
					//List<PcCdOildrillingGasstation>  oild = null;
					//try {
						//oild = oilDriService.serachOild("", StringUtil.toStr(request.getParameter("oildrilling_name").trim()));
					//} catch (Exception e) {
					//	e.printStackTrace();
					//	outCode="-11310";
					//}
					String oildrilling_name = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("oildrilling_name")));
					JSONArray jsonArr = null;
						try {
							jsonArr = gasstationService.searchSationInfo();
						} catch (Exception e) {
							e.printStackTrace();
							//所属站信息获取失败~
							outCode="";
						}
					if(jsonArr !=null && jsonArr.size()>0){
						
					
						List<PcCdAreaInfoT> area = null;
						try {
							area = areaInfoService.searchRole("", StringUtil.toStr(request.getParameter("qkmc").trim()));
						} catch (Exception e) {
							e.printStackTrace();
							//区块名称获取失败
							outCode="-11210";
						}
						
					if(area != null && area.size()>0){
						sta.setPcCdAreaInfoT(area.get(0));
						String ghs = StringUtil.toStr(request.getParameter("ghs").trim());
						if(ghs != null && !"".equals(ghs)){
							sta.setGhs(Byte.parseByte(ghs));
						}
						String cygs = StringUtil.toStr(request.getParameter("cygs").trim());
						if(cygs != null && !"".equals(cygs)){
							sta.setCygs(Byte.parseByte(cygs));
						}
						sta.setCyggg(StringUtil.toStr(request.getParameter("cyggg").trim()));
						String zybs = StringUtil.toStr(request.getParameter("zybs").trim());
						if(zybs != null && !"".equals(zybs)){
							sta.setZybs(Long.parseLong(zybs));
						}
						
						
						//String bstype="1";
						sta.setBsType("1");
						
						sta.setRemark(StringUtil.toStr(request.getParameter("remark").trim()));
						sta.setRlastOper(sessionuser.getOperName());
						sta.setRlastOdate(new Date());
						
//						sta.setstatusvalue
						PcCdOrgT org = new PcCdOrgT();;
						org.setStructurename(blockstationname);
						
						String statusvalues = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("status_value")));
						//设计产能年
						String dyear = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("dyear")));
						sta.setDyear(dyear);
						org.setStatusValue(statusvalues);
						String commissioningDate = StringUtil.toStr(request.getParameter("commissioning_date").trim());
						if(commissioningDate != null && !"".equals(commissioningDate)){
							org.setCommissioningDate(DateBean.getStrDate(commissioningDate));
						}
						PcCdServerNodeT ser = new PcCdServerNodeT(); //服务器逻辑表
						List<PcCdServerNodeT> serverlist = null;
						String scadaNode = StringUtil.toStr(request.getParameter("ljjd_s").trim());
						try {
							serverlist = sagdService.getServerNode(scadaNode);
						} catch (Exception e) {
							e.printStackTrace();
							//-10208 服务器逻辑节点信息获取失败
							outCode = "-10208";
							
						}
						//serverlist.get(0);
						if(serverlist != null && serverlist.size()>0){
							ser = serverlist.get(0);
						}else{
							//-10208 服务器逻辑节点信息获取失败
							outCode = "-10208";
						}
						org.setScadaNode(scadaNode);
						org.setCode(StringUtil.toStr(request.getParameter("code").trim()));
						org.setStructuretype((byte) 12);
						String yxz2 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("yxz2")));
						if(yxz2 !=null && !"".equals(yxz2)){
						org.setPid(yxz2);
						}else{
							org.setPid(oildrilling_name);
						}
						String jrbz=StringUtil.toStr(request.getParameter("jrbz").trim());
						if(jrbz.equals("接入")){
							org.setSwitchInFlag("0");
						}else if(jrbz.equals("未接入")){
							org.setSwitchInFlag("1");
						}else{
							org.setSwitchInFlag("");
						}
						org.setSwitchInFlag(jrbz);
						sta.setPcCdOrgT(org);
					}
					}
					
					try {
						addflg = gasstationService.addSta(sta);
					} catch (Exception e) {
						e.printStackTrace();
						//注转战添加失败
						outCode = "-10166";
					}
					
				
			}
		out.print(outCode);
		return null;}
	//修改
	public String updateStation() throws Exception {
		
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		List<PcCdGasstation> staList = null;
		List<Object[]> orgList = null;
		JSONObject obj = new JSONObject();
		PcCdGasstation sta=null;
		String stationid = request.getParameter("blockstationid").trim();
		String blockstation_name = StringUtil.toStr(request.getParameter("blockstation_name").trim());	
		String code = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("code")));
		String orgid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("org_id")));
		if(stationid != null && !"".equals(stationid)){
			try {
				staList = gasstationService.searchstatinById("", blockstation_name);
			} catch (Exception e) {
				e.printStackTrace();
				outCode = "-11101";
			}
			
			if(staList != null && staList.size() == 1){
				//同条数据进行修改
				if(!stationid.equals(staList.get(0).getStationid())){
					outCode = "-11109";
				}
			//数据库存在多条名称相同数据
			}else if(staList != null && staList.size() > 1){
				outCode = "-11109";
			}else{

				try {
					staList = new ArrayList<PcCdGasstation>();
					staList = gasstationService.searchstatinById(stationid, "");
				} catch (Exception e1) {
					e1.printStackTrace();
					outCode = "-11703";
				}
				
			}
			if(code!=null && !"".equals(code)){
				String sql = "select * from pc_cd_gas_station_t s left join pc_cd_org_t o on s.org_id=o.org_id where 1=1";
				orgList =  orgService.searchOrg(code,orgid, sql);
				if(orgList.size()>1){
					obj = CommonsUtil.getRrturnJson("-11220", "气站编码已存在","", null, null);
					out.print(obj);
					return null;
				}
			}
			boolean addflg = true;
			User sessionuser = (User) session.getAttribute("userInfo");
			if(staList != null && staList.size()>0){
				sta = staList.get(0);
				String blockstationname = StringUtil.toStr(request.getParameter("blockstation_name").trim());
				sta.setStationName(blockstationname);
				
				String oildrilling_name = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("oildrilling_name")));
				JSONArray jsonArr = null;
					try {
						jsonArr = gasstationService.searchSationInfo();
					} catch (Exception e) {
						e.printStackTrace();
						//所属站信息获取失败~
						outCode="";
					}
				if(jsonArr !=null && jsonArr.size()>0){

					List<PcCdAreaInfoT> area = null;
				try {
					area = 	areaInfoService.searchRole("", StringUtil.toStr(request.getParameter("qkmc").trim()));
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(area != null && area.size()>0){
					sta.setPcCdAreaInfoT(area.get(0));
					String ghs = StringUtil.toStr(request.getParameter("ghs").trim());
					if(ghs != null && !"".equals(ghs)){
						sta.setGhs(Byte.parseByte(ghs));
					}
					String cygs = StringUtil.toStr(request.getParameter("cygs").trim());
					if(cygs != null && !"".equals(cygs)){
						sta.setCygs(Byte.parseByte(cygs));
					}
					sta.setCyggg(StringUtil.toStr(request.getParameter("cyggg").trim()));
					String zybs = StringUtil.toStr(request.getParameter("zybs").trim());
					if(zybs != null && !"".equals(zybs)){
						sta.setZybs(Long.parseLong(zybs));
					}
					
					sta.setBsType("1");
					sta.setRemark(StringUtil.toStr(request.getParameter("remark").trim()));
					sta.setRlastOper(sessionuser.getOperName());
					sta.setRlastOdate(new Date());
					
//					sta.setstatusvalue
					PcCdOrgT org = sta.getPcCdOrgT();
					org.setStructurename(blockstationname);
					String commissioningDate = StringUtil.toStr(request.getParameter("commissioning_date").trim());
					if(commissioningDate != null && !"".equals(commissioningDate)){
						org.setCommissioningDate(DateBean.getStrDate(commissioningDate));
					}else{
						org.setCommissioningDate(null);
					}
					String statusvalues = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("status_value")));
					//设计产能年
					String dyear = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("dyear")));
					sta.setDyear(dyear);
					String jrbz=StringUtil.toStr(request.getParameter("jrbz").trim());
					
//					if(jrbz.equals("接入")){
//						org.setSwitchInFlag("1");
//					}else if(jrbz.equals("未接入")){
//						org.setSwitchInFlag("0");
//					}else{
//						org.setSwitchInFlag("");
//					}
					org.setSwitchInFlag(jrbz);
					org.setCode(StringUtil.toStr(request.getParameter("code").trim()));
					String scadaNode = StringUtil.toStr(request.getParameter("ljjd_s").trim());
					org.setScadaNode(scadaNode);
					org.setStatusValue(statusvalues);
					org.setStructuretype((byte) 12);
					
					sta.setPcCdOrgT(org);
				}
				}
				
				try {
					addflg = gasstationService.updateSta(sta);
				} catch (Exception e) {
					e.printStackTrace();
					//注转战更新失败
					outCode = "-10167";
				}
				
			}
		}else{
			//注转战ID获取失败
			outCode = "-11168";
		}
		out.print(outCode);
		return null;
	}

	
	public String removeStation() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		String stationId = StringUtil.toStr(request.getParameter("org_id"));
		JSONObject obj = new JSONObject();
		boolean deleteflg = false;
		try {
			deleteflg = gasstationService.removeStationInfo(stationId);
		} catch (Exception e) {
			String errmsg = e.getCause().toString();
			//ORA-02292: 违反完整约束条件 (NEWPORTAL.FK58_PC_RPD_WELL_SAGDD_T) - 已找到子记录
			if(!"".equals(errmsg) && errmsg.indexOf("ORA-02292") != -1 && errmsg.indexOf("FK58_PC_RPD_WELL_SAGDD_T") != -1){
				obj = CommonsUtil.getRrturnJson("","气站基础信息删除失败-气站日报中存在子记录" ,"", null, null);
			}else if(!"".equals(errmsg) && errmsg.indexOf("ORA-02292") != -1 && errmsg.indexOf("FK_PC_RPD_W_FK52_PC_R_PC_CD_WE") != -1){
				obj = CommonsUtil.getRrturnJson("","气站基础信息删除失败-气站班报中存在子记录" ,"", null, null);
			} else{
				obj = CommonsUtil.getRrturnJson("",errmsg ,"", null, null);
			}
			e.printStackTrace();
		}
		
		if(deleteflg){
			
			//添加系统LOG
			try {
				User user1 = (User) session.getAttribute("userInfo");
				PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "2", "气站基础信息", stationId);
				logService.addLog(log);
			} catch (Exception e) {
				e.printStackTrace();
				obj = CommonsUtil.getRrturnJson("-10003", "", "",null, null);
			}
			
			
		}
		out.print(obj);
		return null;
	}
	public String searchStatusValue() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		//String id = request.getParameter("id");
		JSONArray jsonArr = null;
		try {
			jsonArr = gasstationService.searchStatusValue();
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

	public String cascadeInit() throws Exception {
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONObject jsonArr = null;
		try {
			jsonArr = gasstationService.cascadeInit();
		} catch (Exception e) {
			this.addFieldError("errermsg", "级联菜单初始化失败~");
			e.printStackTrace();
		}
		if(jsonArr != null){
			out.println(jsonArr);
		}else{
			out.println("");
		}
		return null;
	}
	
	public String gasstationype() throws Exception {
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONArray jsonArr = null;
		try {
			jsonArr = gasstationService.gasstationype();
		} catch (Exception e) {
			// TODO: handle exception
			this.addFieldError("errermsg", "供热站信息获取失败~");
			e.printStackTrace();
		}
		if(jsonArr != null){
			out.println(jsonArr);
		}else{
			out.println("");
		}
		return null;
	}
	
	public String searchSationInfo() throws Exception {
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONArray jsonArr = null;
		try {
			jsonArr = gasstationService.searchSationInfo();
		} catch (Exception e) {
			this.addFieldError("errermsg", "采油站信息获取失败~");
			e.printStackTrace();
		}
		if(jsonArr != null){
			out.println(jsonArr);
		}else{
			out.println("");
		}
		return null;
	}
}
