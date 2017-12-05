package com.echo.action;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.echo.dto.PcCdAreaInfoT;
import com.echo.dto.PcCdOildrillingStationT;
import com.echo.dto.PcCdOrgT;
import com.echo.dto.PcCdServerNodeT;
import com.echo.dto.PcCdStationT;
import com.echo.dto.PcRdLoginfoT;
import com.echo.dto.User;
import com.echo.service.AreaInfoService;
import com.echo.service.BoilerBasicService;
import com.echo.service.CommonService;
import com.echo.service.LogService;
import com.echo.service.OilDrillingService;
import com.echo.service.SagdService;
import com.echo.service.StationTService;
import com.echo.util.AuthorityUtil;
import com.echo.util.CommonsUtil;
import com.echo.util.DateBean;
import com.echo.util.DynamicDataExportUtil;
import com.echo.util.PageVariableUtil;
import com.echo.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

public class StationTAction extends ActionSupport  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 12L;
	private StationTService stationTService;
	private AreaInfoService areaInfoService;
	private OilDrillingService oilDriService;
	private  CommonService  commonService;
	private InputStream excelFile = null;
	
	
	public InputStream getExcelFile() {
		return excelFile;
	}

	private SagdService sagdService;
	public void setAreaInfoService(AreaInfoService areaInfoService) {
		this.areaInfoService = areaInfoService;
	}

	private  LogService logService;
	HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
	
	public StationTService getStationTService() {
		return stationTService;
	}

	public void setStationTService(StationTService stationTService) {
		this.stationTService = stationTService;
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
	public String getFileName() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd ");
		String downloadFileName = (sf.format(new Date()).toString())+ "注转站基础信息.xls";
		try {
			downloadFileName = new String(downloadFileName.getBytes(),"ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return downloadFileName;
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
			String jrbz = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("jrbz")));
			String dyear = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("dyear1")));
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
				jsonobj = stationTService.queryStationBasicInfo(dyear,stationNumber,lhz,boilersName,areablock,zztype,jrbz,pageNo,sort,order,rowsPerpage);
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
	
	public String queryOilSationInfo() throws IOException {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String arg = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("arg")));
		JSONArray jsonArr = null;
		try {
			jsonArr = stationTService.queryOilSationInfo(arg);
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
	
	public String queryAreablockInfo() throws IOException {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String arg = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("orgid")));
		JSONArray jsonArr = null;
		
		try {
			jsonArr = stationTService.queryAreablockInfo(arg);
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
	
	
	public String queryStationInfo() throws IOException {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String combinationid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("combinationid")));
		String areaid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("areaid")));
		String selecteValue = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("selecteValue")));
		String oilid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("oilid")));
		String views = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("views")));
		JSONArray jsonArr = null;
		try {
			jsonArr = stationTService.queryStationInfo(combinationid,oilid,areaid,selecteValue,views);
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
			gridJson = AuthorityUtil.getGridJson("注转站基础信息", user, PageVariableUtil.STATIONT_PAGE_GRID);
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
		List<PcCdStationT> staList = null;
		PcCdStationT sta=null;
		
		//String oildrilling_name = request.getParameter("oildrilling_name").trim();
		//String blockstationid = request.getParameter("blockstationid").trim();
		String blockstationname = StringUtil.toStr(request.getParameter("blockstation_name").trim());
		  
			try {
				staList = stationTService.searchstatinByName(blockstationname);
			} catch (Exception e) {
				e.printStackTrace();
				//注转战名称获取信息失败
				outCode="-11110";
				
			}
			if(staList != null && staList.size() >0){
				//注转站以存在，不允许用户添加
				outCode = "-11111";
				
			}else{
				boolean addflg = true;
				User sessionuser = (User) session.getAttribute("userInfo");
					sta = new PcCdStationT();
					
					sta.setBlockstationName(blockstationname);
					
					//List<PcCdOildrillingStationT>  oild = null;
					//try {
						//oild = oilDriService.serachOild("", StringUtil.toStr(request.getParameter("oildrilling_name").trim()));
					//} catch (Exception e) {
					//	e.printStackTrace();
					//	outCode="-11310";
					//}
					String oildrilling_name = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("oildrilling_name")));
					JSONArray jsonArr = null;
						try {
							jsonArr = stationTService.searchSationInfo();
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
						
						
						String bstype=StringUtil.toStr(request.getParameter("bs_type").trim());
						if(bstype.equals("注汽接转站")){
							sta.setBsType("1");
						}else if(bstype.equals("注汽站")){
							sta.setBsType("2");
						}else if(bstype.equals("接转站")){
							sta.setBsType("3");
						}else if(bstype.equals("管汇")){
							sta.setBsType("4");
						}else if(bstype.equals("管汇")){
							sta.setBsType("5");
						}else{
							sta.setBsType("");
						}

						sta.setRemark(StringUtil.toStr(request.getParameter("remark").trim()));
						sta.setRlastOper(sessionuser.getOperName());
						sta.setRlastOdate(new Date());
						String boiler_qty = StringUtil.toStr(request.getParameter("boilerqty").trim());
						if(boiler_qty != null && !"".equals(boiler_qty)){
							sta.setBoilerQty(Integer.parseInt(boiler_qty));
						}
						sta.setSteamRemark(StringUtil.toStr(request.getParameter("steamremark").trim()));
						String yxz2 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("yxz2")));
						if(yxz2 !=null && !"".equals(yxz2)){
							sta.setSteamInjectionOwner(yxz2);
						}
//						sta.setstatusvalue
						PcCdOrgT org = new PcCdOrgT();;
						org.setStructurename(blockstationname);
						String design_date = StringUtil.isNullUtil(request.getParameter("dyear"));
						if (!"".equals(design_date)) {
							sta.setDyear(design_date);
						}
						String statusvalues = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("status_value")));
						//String statusvalues="";
//						if (!"".equals(statusvalues)) {
//						try {
//							statusvalues = commonService.getStatusValueINT(StringUtil.toStr(request.getParameter("status_value").trim()));
//						} catch (Exception e) {
//							e.printStackTrace();
//						}
//						}
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
						String jrbz=StringUtil.toStr(request.getParameter("jrbz").trim());
						/*if(jrbz.equals("未接入")){
							org.setSwitchInFlag("0");
						}else if(jrbz.equals("接入")){
							org.setSwitchInFlag("1");
						}*/
						org.setSwitchInFlag(jrbz);
						org.setCode(StringUtil.toStr(request.getParameter("blockstationcode").trim()));
						org.setStructuretype((byte) 7);
						
						if(oildrilling_name != null && !"".equals(oildrilling_name)){
							org.setPid(oildrilling_name);
						}
						sta.setPcCdOrgT(org);
					}
					}
					
					try {
						addflg = stationTService.addSta(sta);
					} catch (Exception e) {
						e.printStackTrace();
						//注转战添加失败
						outCode = "-11112";
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
		List<PcCdStationT> staList = null;
		PcCdStationT sta=null;
		
		String blockstationid = request.getParameter("blockstationid").trim();
		String blockstation_name = StringUtil.toStr(request.getParameter("blockstation_name").trim());	
		
		if(blockstationid != null && !"".equals(blockstationid)){
			try {
				staList = stationTService.searchstatinById("", blockstation_name);
			} catch (Exception e) {
				e.printStackTrace();
				out.print("-11110");
				return null;
			}
			
			if(staList != null && staList.size() == 1){
				//同条数据进行修改
				if(!blockstationid.equals(staList.get(0).getBlockstationid())){
					out.print("-11111");
					return null;
				}
			//数据库存在多条名称相同数据
			}else if(staList != null && staList.size() > 1){
				out.print("-11109");
				return null;
			}else{

				try {
					staList = new ArrayList<PcCdStationT>();
					staList = stationTService.searchstatinById(blockstationid, "");
				} catch (Exception e1) {
					e1.printStackTrace();
					out.print("-11703");
					return null;
				}
				
			}
			
			boolean addflg = true;
			User sessionuser = (User) session.getAttribute("userInfo");
			if(staList != null && staList.size()>0){
				sta = staList.get(0);
				String blockstationname = StringUtil.toStr(request.getParameter("blockstation_name").trim());
				sta.setBlockstationName(blockstationname);
				
				String oildrilling_name = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("oildrilling_name")));
				JSONArray jsonArr = null;
					try {
						jsonArr = stationTService.searchSationInfo();
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
					
					String bstype=StringUtil.toStr(request.getParameter("bs_type").trim());
					if(bstype.equals("注汽接转站")){
						sta.setBsType("1");
					}else if(bstype.equals("注汽站")){
						sta.setBsType("2");
					}else if(bstype.equals("接转站")){
						sta.setBsType("3");
					}else if(bstype.equals("管汇")){
						sta.setBsType("4");
					}else{
						sta.setBsType("");
					}
					
					sta.setRemark(StringUtil.toStr(request.getParameter("remark").trim()));
					String boiler_qty = StringUtil.toStr(request.getParameter("boilerqty").trim());
					if(boiler_qty != null && !"".equals(boiler_qty)){
						sta.setBoilerQty(Integer.parseInt(boiler_qty));
					}else{
						sta.setBoilerQty(null);
					}
					
					sta.setSteamRemark(StringUtil.toStr(request.getParameter("steamremark").trim()));
					sta.setRlastOper(sessionuser.getOperName());
					sta.setRlastOdate(new Date());
					String yxz2 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("yxz2")));
					if(yxz2 !=null && !"".equals(yxz2)){
						sta.setSteamInjectionOwner(yxz2);
					}
//					sta.setstatusvalue
					PcCdOrgT org  = null;
					if(sta.getPcCdOrgT() != null && sta.getPcCdOrgT().getOrgId() != null){
						org = sta.getPcCdOrgT();
					}else{
						org = new PcCdOrgT();
					}
					
					org.setStructurename(blockstationname);
					String commissioningDate = StringUtil.toStr(request.getParameter("commissioning_date").trim());
					if(commissioningDate != null && !"".equals(commissioningDate)){
						org.setCommissioningDate(DateBean.getStrDate(commissioningDate));
					}
					String design_date = StringUtil.isNullUtil(request.getParameter("dyear"));
					if (!"".equals(design_date)) {
						sta.setDyear(design_date);
					}
					String statusvalues = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("status_value")));
//					if(!"".equals(statusvalues)){
//					try {
//						statusvalues = commonService.getStatusValueINT(StringUtil.toStr(request.getParameter("status_value").trim()));
//					} catch (Exception e) {
//						e.printStackTrace();
//						
//					}
//					}
					String jrbz=StringUtil.isNullUtil(request.getParameter("jrbz"));
					
					/*if(jrbz.equals("接入")){
						org.setSwitchInFlag("1");
					}else if(jrbz.equals("未接入")){
						org.setSwitchInFlag("0");
					}else{
						org.setSwitchInFlag("");
					}*/
					org.setSwitchInFlag(jrbz);
					String scadaNode = StringUtil.toStr(request.getParameter("ljjd_s").trim());
					org.setScadaNode(scadaNode);
					org.setStatusValue(statusvalues);
					org.setStructuretype((byte) 7);
					org.setCode(StringUtil.toStr(request.getParameter("blockstationcode").trim()));
					
					if(oildrilling_name != null && !"".equals(oildrilling_name)){
						org.setPid(oildrilling_name);
					}
					sta.setPcCdOrgT(org);
				}
				}
				try {
					addflg = stationTService.updateSta(sta);
				} catch (Exception e) {
					e.printStackTrace();
					//注转战更新失败
					out.print("-11113");
					return null;
				}
				
			}
		}else{
			//注转战ID获取失败
			out.print("-11116");
			return null;
		}
		out.print(outCode);
		return null;
	}

	
	public String removeStation() throws IOException {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		String stationId = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("stationId")));
		String org_id = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("org_id")));
		boolean deleteflg = false;
		JSONObject obj = new JSONObject();
		try {
			deleteflg = stationTService.removeStationInfo(stationId,org_id);
		} catch (Exception e) {
			String errmsg = e.getCause().toString();
			//ORA-02292: 违反完整约束条件 (NEWPORTAL.FK58_PC_RPD_WELL_SAGDD_T) - 已找到子记录
			if(!"".equals(errmsg) && errmsg.indexOf("ORA-02292") != -1 && errmsg.indexOf("FK58_PC_RPD_WELL_SAGDD_T") != -1){
				obj = CommonsUtil.getRrturnJson("","注转站基础信息删除失败-注转站日报中存在子记录" , "",null, null);
			}else if(!"".equals(errmsg) && errmsg.indexOf("ORA-02292") != -1 && errmsg.indexOf("FK_PC_RPD_W_FK52_PC_R_PC_CD_WE") != -1){
				obj = CommonsUtil.getRrturnJson("","注转站基础信息删除失败-注转站班报中存在子记录" ,"", null, null);
			} else{
				obj = CommonsUtil.getRrturnJson("",errmsg , "",null, null);
			}
			e.printStackTrace();
		}
		
		if(deleteflg){
			
			//添加系统LOG
			try {
				User user1 = (User) session.getAttribute("userInfo");
				PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "2", "注转站基础信息", stationId);
				logService.addLog(log);
			} catch (Exception e) {
				e.printStackTrace();
				obj = CommonsUtil.getRrturnJson("-10003", "","", null, null);
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
			jsonArr = stationTService.searchStatusValue();
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
	
	public String searchStatusValueAll() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		//String id = request.getParameter("id");
		JSONArray jsonArr = null;
		try {
			jsonArr = stationTService.searchStatusValue1();
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

	public String cascadeInit() throws IOException {
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONObject jsonArr = null;
		try {
			jsonArr = stationTService.cascadeInit();
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
	
	public String stationType() throws IOException {
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONArray jsonArr = null;
		try {
			jsonArr = stationTService.stationType();
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
	
	public String searchSationInfo() throws IOException {
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONArray jsonArr = null;
		try {
			jsonArr = stationTService.searchSationInfo();
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
	
	public String searchTeamoil() throws IOException {
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONArray jsonArr = null;
		try {
			jsonArr = stationTService.searchSationInfo();
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
	
	public  String onExport()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String oilstationname1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("oilstationname")));
		String areablock1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("areablock")));
		String blockstationtype = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("blockstationtype")));
		String blockstationname = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("blockstationname")));
		String jrbz1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("jrbz1")));
		String combination = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("combination")));
		String totalNum = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("totalNum")));
		String dyear = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("dyear1")));
		HashMap<String,Object> dataMap = null;
		try {
			dataMap = stationTService.searchOnExport(dyear,oilstationname1,areablock1,blockstationtype,blockstationname,jrbz1,combination,totalNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//返回条数
		if ("totalNum".equals(totalNum)) {
			Object total = dataMap.get("totalNum");
			out.println(total);
			out.flush();
			out.close();
			return null;
		}
		
		Object jsonobj = dataMap.get("json");
		//返回显示数据
		if(jsonobj != null && "".equals(totalNum)){
			out.println(jsonobj);
			out.flush();
			out.close();
		}
		else {//导出报表
			response.resetBuffer();
			response.reset();
			List<Object[]> dataLsit = (List<Object[]>) dataMap.get("list");
			String templetFilePath = request.getSession().getServletContext().getRealPath("/") + "exceltemplet\\基础信息报表.xls";
//			将用户对象列表输出到workbook的流中，ExcelUtil类进行了封装，后续分析！
			java.io.ByteArrayOutputStream baos = DynamicDataExportUtil.basicDataExporReport(dataLsit,templetFilePath,"注转站基础信息");
			if(baos != null){
				byte[] ba = baos.toByteArray();
				excelFile = new ByteArrayInputStream(ba);
				baos.flush();
				baos.close();
			}
			return "excel";
		}
		return null;
	} 
}
