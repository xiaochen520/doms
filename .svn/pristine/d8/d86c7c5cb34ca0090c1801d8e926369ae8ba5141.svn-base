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

import com.echo.dto.PcCdOrgT;
import com.echo.dto.PcCdServerNodeT;
import com.echo.dto.PcCdThinoilWellT;
import com.echo.dto.PcRdLoginfoT;
import com.echo.dto.User;
import com.echo.service.CommonService;
import com.echo.service.LogService;
import com.echo.service.ManifoldBasicService;
import com.echo.service.OrgService;
import com.echo.service.RuleWellService;
import com.echo.service.SagdService;
import com.echo.service.ThinOilWellService;
import com.echo.util.AuthorityUtil;
import com.echo.util.CommonsUtil;
import com.echo.util.DateBean;
import com.echo.util.DynamicDataExportUtil;
import com.echo.util.PageVariableUtil;
import com.echo.util.StringUtil;

public class ThinOilWellAction {
private ThinOilWellService thinOilWellService;
	private CommonService  commonService  ;
	private  ManifoldBasicService  manifoldBasicService;
	private SagdService sagdService;
	private RuleWellService ruleWellService;
	private InputStream excelFile = null;
	
	public InputStream getExcelFile() {
		return excelFile;
	}

	public void setThinOilWellService(ThinOilWellService thinOilWellService) {
	this.thinOilWellService = thinOilWellService;
	}
	private OrgService orgService;
	public void setOrgService(OrgService orgService) {
		this.orgService = orgService;
	}
	private LogService logService;

	public LogService getLogService() {
		return logService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	
	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}

	public void setManifoldBasicService(ManifoldBasicService manifoldBasicService) {
		this.manifoldBasicService = manifoldBasicService;
	}
	
	public void setSagdService(SagdService sagdService) {
		this.sagdService = sagdService;
	}
	public void setRuleWellService(RuleWellService ruleWellService) {
		this.ruleWellService = ruleWellService;
	}
	public String getFileName() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd ");
		String downloadFileName = (sf.format(new Date()).toString())+ "稀油油井基础信息.xls";
		try {
			downloadFileName = new String(downloadFileName.getBytes(),"ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return downloadFileName;
	}
	HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
	public String searchThinOil() throws IOException {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String stationNumber = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("oilstationname1")));
		String boilersName = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("areablock1")));
		String areablock = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("blockstationname1")));
		String ghname = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("ghname")));
		String oilname = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("rulewellname1")));
		String jrbz1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("jrbz1")));
		String dyearC = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("dyearC")));
		 int pageNo = 1; //页索引参数名当前页
 		String sort = "";		//页排序列名
 		String order = "";//页排序方向
 		int rowsPerpage = 0; //每页显示条数
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
		
 		JSONObject jsonobj = thinOilWellService.searchThinOil(stationNumber,boilersName,areablock,ghname,oilname,jrbz1,dyearC,pageNo,sort,order,rowsPerpage);
 		if(jsonobj != null){
			out.println(jsonobj);
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
			gridJson = AuthorityUtil.getGridJson("稀油油井基础信息", user, PageVariableUtil.THINOIL_WELL_PAGE_GRID);
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

	public String removeThinoil() throws IOException {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		String stationId = StringUtil.toStr(request.getParameter("wellid"));
		String orgId = StringUtil.toStr(request.getParameter("org_id"));
		boolean deleteflg = false;
		JSONObject obj = new JSONObject();
		try {
			deleteflg = thinOilWellService.removeStationInfo(stationId,orgId);
		} catch (Exception e) {
			String errmsg = e.getCause().toString();
			//ORA-02292: 违反完整约束条件 (NEWPORTAL.FK58_PC_RPD_WELL_SAGDD_T) - 已找到子记录
			if(!"".equals(errmsg) && errmsg.indexOf("ORA-02292") != -1 && errmsg.indexOf("FK58_PC_RPD_WELL_SAGDD_T") != -1){
				obj = CommonsUtil.getRrturnJson("","稀油井基础信息删除失败-稀油井日报中存在子记录" ,"", null, null);
			}else if(!"".equals(errmsg) && errmsg.indexOf("ORA-02292") != -1 && errmsg.indexOf("FK_PC_RPD_W_FK52_PC_R_PC_CD_WE") != -1){
				obj = CommonsUtil.getRrturnJson("","稀油井基础信息删除失败-稀油井班报中存在子记录" ,"", null, null);
			} else{
				obj = CommonsUtil.getRrturnJson("",errmsg ,"", null, null);
			}
			e.printStackTrace();
		}
		
		if(deleteflg){
			
			//添加系统LOG
			try {
				User user1 = (User) session.getAttribute("userInfo");
				PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "2", "稀油油井基础信息", stationId);
				logService.addLog(log);
			} catch (Exception e) {
				e.printStackTrace();
				obj = CommonsUtil.getRrturnJson("-10003", "","", null, null);
			}
			
			
		}
		out.print(obj);
		return null;
	}
	
	public String queryWellInfo() throws IOException {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String boilername = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("orgid")));
		JSONArray jsonArr = null;
		try {
			jsonArr = thinOilWellService.queryWellInfo(boilername);
		} catch (Exception e) {
//			this.addFieldError("errermsg", "锅炉信息获取失败~");
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
			jsonArr = thinOilWellService.cascadeInit();
		} catch (Exception e) {
//			this.addFieldError("errermsg", "级联菜单初始化失败~");
			e.printStackTrace();
		}
		if(jsonArr != null){
			out.println(jsonArr);
		}else{
			out.println("");
		}
		return null;
	}
	
	public String addThinoil() throws Exception{

		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		boolean  addflg = true;
		JSONObject obj = new JSONObject();
		List<Object[]> orgList = null;
		PcCdThinoilWellT thin = new PcCdThinoilWellT();
		//稀油井父节点管汇ID
		String pid = "";
		
		String thinoilwellname = StringUtil.toStr(request.getParameter("well_name").trim());
		String pipesink = StringUtil.toStr(request.getParameter("pipesink").trim());
		String wellCode = StringUtil.toStr(request.getParameter("wellCode").trim());
		String orgid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("org_id")));
		
		List<PcCdThinoilWellT> thinlist = null;
		try {
			thinlist = thinOilWellService.searchThisName(thinoilwellname);
		} catch (Exception e) {
			e.printStackTrace();
			//稀油井名称获取失败
			outCode = "-10410";
		}
		//稀油井名称已经存在,请使用其他稀油井名称
		if(thinlist != null && thinlist.size()>0){
			outCode = "-10411";
		}
		
		if("1".equals(outCode)){
			//所属管汇
			List<Object[]>  maniList = null;
			try {
				maniList = manifoldBasicService.searchPidBymanifoldid(pipesink);
			} catch (Exception e) {
				e.printStackTrace();
				//管汇名称获取失败
				outCode = "-11501";
			}
			if(maniList !=null && maniList.size()>0){
				pid = maniList.get(0)[2].toString();
				
			}else{
				outCode = "-11507";
			}
			if("1".equals(outCode)){
				if(wellCode!=null && !"".equals(wellCode)){
					String sql = "select * from pc_cd_thinoil_well_t s left join pc_cd_org_t o on s.org_id=o.org_id where 1=1";
					orgList =  orgService.searchOrg(wellCode,orgid, sql);
					if(orgList.size()>0){
						obj = CommonsUtil.getRrturnJson("-11220", "稀油编码已存在","", null, null);
						out.print(obj);
						return null;
					}
				}
				User sessionuser = (User) session.getAttribute("userInfo");
				thin.setWellName(thinoilwellname);
				String statusvalues = StringUtil.toStr(request.getParameter("status_value").trim());
				if (!"".equals(statusvalues)) {
					try {
						statusvalues = commonService.getStatusValueINT(statusvalues);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				String wellAreaSelf = StringUtil.toStr(request.getParameter("wellAreaSelf").trim());
				/*if("1".equals(wellAreaSelf)){
					try {
						wellAreaSelf = ruleWellService.serarchWellAreaSelf(wellAreaSelf);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}*/
				thin.setQkid(wellAreaSelf);
				thin.setBewellName(StringUtil.toStr(request.getParameter("bewell_name").trim()));
				String valve_coding =  StringUtil.toStr(request.getParameter("valve_coding").trim());
				if(valve_coding !=null && !"".equals(valve_coding)){
					thin.setValveCoding(Byte.parseByte(valve_coding));
				}else{
					thin.setValveCoding(null);
				}
				String channel_no = StringUtil.toStr(request.getParameter("channel_no").trim());
					thin.setChannelNo(CommonsUtil.getByteData(channel_no));
					
				String outputCoefficient = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("output_coefficient")));
				if(outputCoefficient !=null && !"".equals(outputCoefficient)){
				thin.setOutputCoefficient(java.math.BigDecimal.valueOf(Double.parseDouble(outputCoefficient)));
				}else{
					thin.setOutputCoefficient(null);
				}
				//产能设计年
				thin.setDyear(StringUtil.toStr(request.getParameter("dyear").trim()));
				//加密采集的间隔
				String interval = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("interval")));
				if(interval !=null &&  !interval.equals("")){
					thin.setInterval(java.math.BigDecimal.valueOf(Double.parseDouble(interval)));
				}else{
					thin.setInterval(null);
				}
				//开始时间
				String stratTIME = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("stratTIME")));
				thin.setStratTIME(DateBean.getStrDateTime(stratTIME));
				//结束时间
				String endTIME = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("endTIME")));
				thin.setEndTIME(DateBean.getStrDateTime(endTIME));
				//加密井标志
				String flag = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("flag")));
				if(flag !=null &&  !flag.equals("")){
					thin.setFlag(java.math.BigDecimal.valueOf(Double.parseDouble(flag)));
					}else{
						thin.setFlag(null);
					}
				thin.setRemark(StringUtil.toStr(request.getParameter("remark").trim()));
				thin.setRlastOper( sessionuser.getOperName());
				thin.setRlastOdate(new Date());
				PcCdOrgT org = new PcCdOrgT();
						
				String commissioningDate = StringUtil.toStr(request.getParameter("commissioning_date").trim());
				if(commissioningDate != null && !"".equals(commissioningDate)){
					org.setCommissioningDate(DateBean.getStrDate(commissioningDate));
				}
				PcCdServerNodeT ser = new PcCdServerNodeT(); //服务器逻辑表
				List<PcCdServerNodeT> serverlist = null;
				String  scadaNode = StringUtil.toStr(request.getParameter("ljjd_s").trim());
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
				
				org.setCode(StringUtil.toStr(request.getParameter("wellCode").trim()));
				String switchInFlag = StringUtil.toStr(request.getParameter("jrbz").trim());
				org.setSwitchInFlag(switchInFlag);
				org.setStatusValue(statusvalues);
				org.setStructurename(thinoilwellname);
				org.setPid(pid);
				org.setStructuretype((byte)9);
				org.setRemark(StringUtil.toStr(request.getParameter("remark").trim()));
				org.setPcCdThinoilWellT(thin);
				thin.setPcCdOrgT(org);
						
				try {
					addflg = thinOilWellService.addThin(thin); 
				} catch (Exception e) {
					String errmsg = e.getCause().getCause().toString();
					
					if(!"".equals(errmsg) && errmsg.indexOf("ORA-01438") != -1){
						obj = CommonsUtil.getRrturnJson("","您输入信息超过数据库数据精度范围" ,"", null, null);
					} else{
						obj = CommonsUtil.getRrturnJson("",errmsg ,"", null, null);
					}
					
					e.printStackTrace();
					//管汇信息添加失败
					//outCode = "-10412";
				}
				
			
				if("1".equals(outCode) && addflg == true){
					
					//添加系统LOG
					try {
						User user1 = (User) session.getAttribute("userInfo");
						PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "2", "稀油油井基础信息", thin.getWellid());
						logService.addLog(log);
					} catch (Exception e) {
						e.printStackTrace();
						outCode = "-10003";
					}
				}	
						
			}
			
		}
		if(!"1".equals(outCode)){
			obj = CommonsUtil.getRrturnJson(outCode, "","", null, null);
		}
		out.print(obj);
		return null;
	}
	

	
	public String updateThinoil() throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		boolean  addflg = true;
		JSONObject  obj = new JSONObject();
		List<PcCdThinoilWellT> thinList = null;
		List<Object[]> orgList = null;
		PcCdThinoilWellT thin = null;
		//thin = new PcCdThinoilWellT();
		//稀油井父节点管汇ID
		String pid = "";
		//查询常规油井 井名
		String thinwellid = StringUtil.toStr(request.getParameter("wellid").trim());
		String thinoilwellname = StringUtil.toStr(request.getParameter("well_name").trim());
		String pipesink = StringUtil.toStr(request.getParameter("pipesink").trim());
		String blockstationname = StringUtil.toStr(request.getParameter("blockstationname").trim());
		
		String wellCode = StringUtil.toStr(request.getParameter("wellCode").trim());
		String orgid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("org_id")));
				try {
//					thinList = thinOilWellService.searchThinById("",thinoilwellname);
					thinList = thinOilWellService.searchThisName(thinoilwellname);
				} catch (Exception e) {
					e.printStackTrace();
					//稀油井名称获取失败
					outCode="-10410";
				}
				
				if(thinList !=null && thinList.size()==1){
					if(!thinwellid.equals(thinList.get(0).getWellid())){
						//常规油井同条进行修改
						outCode="-10411";
					}
				}
				if(thinList !=null && thinList.size()>1){
					//常规油井名称已存在多条，不容许修改
					outCode="-10411";
				}else{
					try {
						thinList = new ArrayList<PcCdThinoilWellT>();
						thinList  = thinOilWellService.searchThinById(thinwellid,"");
						thin = thinList.get(0);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				if("1".equals(outCode)){
					//所属管汇
					List<Object[]>  maniList = null;
					try {
						maniList = manifoldBasicService.searchPidBymanifoldid(pipesink);
					} catch (Exception e) {
						e.printStackTrace();
						//管汇信息获取失败
						outCode = "-11501";
					}
					if(maniList !=null && maniList.size()>0){
						pid = maniList.get(0)[2].toString();
						
					}else{
						outCode = "-11507";
					}
					if("1".equals(outCode)){
					
						if(wellCode!=null && !"".equals(wellCode)){
							String sql = "select * from pc_cd_thinoil_well_t s left join pc_cd_org_t o on s.org_id=o.org_id where 1=1";
							orgList =  orgService.searchOrg(wellCode,orgid, sql);
							if(orgList.size()>0){
								obj = CommonsUtil.getRrturnJson("-11220", "稀油编码已存在","", null, null);
								out.print(obj);
								return null;
							}
						}
					User sessionuser = (User) session.getAttribute("userInfo");
					thin.setWellName(thinoilwellname);
					
					thin.setBewellName(StringUtil.toStr(request.getParameter("bewell_name").trim()));
					String wellAreaSelf = StringUtil.toStr(request.getParameter("wellAreaSelf").trim());
					/*if("1".equals(wellAreaSelf)){
						try {
							wellAreaSelf = ruleWellService.serarchWellAreaSelf(wellAreaSelf);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}*/
					thin.setQkid(wellAreaSelf);
					String valve_coding =  StringUtil.toStr(request.getParameter("valve_coding").trim());
					if(valve_coding !=null && !"".equals(valve_coding)){
						thin.setValveCoding(Byte.parseByte(valve_coding));
					}else{
						thin.setValveCoding(null);
					}
					String channel_no = StringUtil.toStr(request.getParameter("channel_no").trim());
						thin.setChannelNo(CommonsUtil.getByteData(channel_no));
						
					String outputCoefficient = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("output_coefficient")));
					if(outputCoefficient !=null && !"".equals(outputCoefficient)){
					thin.setOutputCoefficient(java.math.BigDecimal.valueOf(Double.parseDouble(outputCoefficient)));
					}else{
						thin.setOutputCoefficient(null);
					}
					//产能设计年
					thin.setDyear(StringUtil.toStr(request.getParameter("dyear").trim()));
					//加密采集的间隔
					String interval = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("interval")));
					if(interval !=null &&  !interval.equals("")){
						thin.setInterval(java.math.BigDecimal.valueOf(Double.parseDouble(interval)));
					}else{
						thin.setInterval(null);
					}
					
					//开始时间
					String stratTIME = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("stratTIME")));
					thin.setStratTIME(DateBean.getStrDateTime(stratTIME));
					//结束时间
					String endTIME = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("endTIME")));
					thin.setEndTIME(DateBean.getStrDateTime(endTIME));
					//加密井标志
					String flag = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("flag")));
					if(flag !=null &&  !flag.equals("")){
						thin.setFlag(java.math.BigDecimal.valueOf(Double.parseDouble(flag)));
						}else{
							thin.setFlag(null);
						}
					
					thin.setRemark(StringUtil.toStr(request.getParameter("remark").trim()));
					thin.setRlastOper( sessionuser.getOperName());
					thin.setRlastOdate(new Date());
					 PcCdOrgT org = thin.getPcCdOrgT();
					 //投产日期
					String commissioningDate = StringUtil.toStr(request.getParameter("commissioning_date").trim());
					if(commissioningDate != null && !"".equals(commissioningDate)){
						org.setCommissioningDate(DateBean.getStrDate(commissioningDate));
					}
					String statusvalues = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("status_value")));
					
					if (!"".equals(statusvalues)) {
						try {
							statusvalues = commonService.getStatusValueINT(statusvalues);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					org.setCode(StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("wellCode"))).trim());
					String switchInFlag = StringUtil.toStr(request.getParameter("jrbz").trim());
					org.setSwitchInFlag(switchInFlag);
					String  scadaNode = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("ljjd_s")));
					org.setScadaNode(scadaNode);
					 org.setStatusValue(statusvalues);
					 org.setStructurename(thinoilwellname);
					 org.setPid(pid);
					 org.setStructuretype((byte)9);
						org.setRemark(StringUtil.toStr(request.getParameter("remark").trim()));
					 org.setPcCdThinoilWellT(thin);
					 thin.setPcCdOrgT(org);
							
					try {
						addflg = thinOilWellService.updateThin(thin); 
					} catch (Exception e) {
						
						String errmsg = e.getCause().getCause().toString();
						if(!"".equals(errmsg) && errmsg.indexOf("ORA-01438") != -1){
							obj = CommonsUtil.getRrturnJson("","您输入信息超过数据库数据精度范围" ,"", null, null);
						} else{
							obj = CommonsUtil.getRrturnJson("",errmsg ,"", null, null);
						}
						e.printStackTrace();
						//稀油井信息更新失败
						//outCode="-10413";
					}
				
				}
//				
//		if("1".equals(outCode) && addflg == true){
//			
//			//添加系统LOG
//			try {
//				User user1 = (User) session.getAttribute("userInfo");
//				PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "2", "稀油油井基础信息", thin.getWellid());
//				logService.addLog(log);
//			} catch (Exception e) {
//				e.printStackTrace();
//				outCode = "-10003";
//			}
//			
//		}
		}
		if(!"1".equals(outCode)){
			obj = CommonsUtil.getRrturnJson(outCode, "", "",null, null);
			}	

		out.print(obj);
		return null;
	}
	
	public  String onExport()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		
		String oilstationname1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("oilstationname1")));
		String blockstationname1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("blockstationname1")));
		String rulewellname1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("rulewellname1")));
		String areablock = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("areablock")));
		String jrbz1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("jrbz1")));
		String totalNum = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("totalNum")));
		String dyearC = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("dyearC")));

		HashMap<String,Object> dataMap = null;
		try {
			dataMap = thinOilWellService.searchOnExport(oilstationname1,blockstationname1,rulewellname1,areablock,jrbz1,dyearC,totalNum);
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
			java.io.ByteArrayOutputStream baos = DynamicDataExportUtil.basicDataExporReport(dataLsit,templetFilePath,"稀油油井基础信息");
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
