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
import com.echo.dto.PcCdRuleWellT;
import com.echo.dto.PcCdServerNodeT;
import com.echo.dto.PcRdLoginfoT;
import com.echo.dto.User;
import com.echo.service.CommonService;
import com.echo.service.LogService;
import com.echo.service.ManifoldBasicService;
import com.echo.service.OrgService;
import com.echo.service.RuleWellService;
import com.echo.service.SagdService;
import com.echo.service.StationTService;
import com.echo.util.AuthorityUtil;
import com.echo.util.CommonsUtil;
import com.echo.util.DateBean;
import com.echo.util.DynamicDataExportUtil;
import com.echo.util.PageVariableUtil;
import com.echo.util.StringUtil;

public class RuleWellAction {
	private RuleWellService ruleWellService;
	private StationTService stationTService;
	private ManifoldBasicService manifoldBasicService;
	private OrgService orgService;
	private InputStream excelFile = null;
	
	
	public InputStream getExcelFile() {
		return excelFile;
	}

	public void setOrgService(OrgService orgService) {
		this.orgService = orgService;
	}
	private SagdService sagdService;
	public void setManifoldBasicService(ManifoldBasicService manifoldBasicService) {
		this.manifoldBasicService = manifoldBasicService;
	}

	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}
	private CommonService commonService;
	
	public void setRuleWellService(RuleWellService ruleWellService) {
		this.ruleWellService = ruleWellService;
	}
	public StationTService getStationTService() {
		return stationTService;
	}
	private LogService logService;

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	public void setStationTService(StationTService stationTService) {
		this.stationTService = stationTService;
	}
	public void setSagdService(SagdService sagdService) {
		this.sagdService = sagdService;
	}
	public String getFileName() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd ");
		String downloadFileName = (sf.format(new Date()).toString())+ "常规油井基础信息.xls";
		try {
			downloadFileName = new String(downloadFileName.getBytes(),"ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return downloadFileName;
	}
	HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
	public String searchRullWell() throws Exception {
		
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
		String dyear = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("dyear")));
		
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
		
 		JSONObject jsonobj = ruleWellService.searchRullWell(stationNumber,boilersName,areablock,ghname,oilname,jrbz1,dyear,pageNo,sort,order,rowsPerpage);
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
		
		//根据用户权限生成用户权限生成页面布局格式
		User user = (User) session.getAttribute("userInfo");
		
		String gridJson = AuthorityUtil.getGridJson("常规油井基础信息", user, PageVariableUtil.RULE_WELL_PAGE_GRID);
//		System.out.println(gridJson);
		out.print(gridJson);
		return null;
	}
	@SuppressWarnings("unused")
	public String removeRuleWell() throws IOException {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		String stationId = StringUtil.toStr(request.getParameter("rule_wellId"));
		String orgId = StringUtil.toStr(request.getParameter("org_id"));
		boolean deleteflg = false;
		JSONObject obj = new JSONObject();
		try {
			deleteflg = ruleWellService.removeStationInfo(stationId,orgId);
		} catch (Exception e) {
			String errmsg = e.getCause().toString();
			if(!"".equals(errmsg) && errmsg.indexOf("ORA-02292") != -1 ){
				obj = CommonsUtil.getRrturnJson("","常规基础信息删除失败-常规井日报中存在子记录" ,"", null, null);
			}else if(!"".equals(errmsg) && errmsg.indexOf("ORA-02292") != -1 ){
				obj = CommonsUtil.getRrturnJson("","常规基础信息删除失败-常规井班报中存在子记录" , "",null, null);
			} else{
				obj = CommonsUtil.getRrturnJson("",errmsg ,"", null, null);
			}
			e.printStackTrace();
			//outCode = "-17105";
		}
		
		if(deleteflg){
			
			//添加系统LOG
			try {
				User user1 = (User) session.getAttribute("userInfo");
				PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "2", "常规油井基础信息", stationId);
				logService.addLog(log);
			} catch (Exception e) {
				e.printStackTrace();
				obj = CommonsUtil.getRrturnJson("-10003", "","", null, null);
			}
			
			
		}
		out.print(obj);
		return null;
	}
	
	public String queryOilSationInfo() throws IOException {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONArray jsonArr = null;
		String arg = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("arg")));
		try {
			jsonArr = ruleWellService.queryOilSationInfo(arg);
		} catch (Exception e) {
//			this.addFieldError("errermsg", "采油站信息获取失败~");
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
			jsonArr = ruleWellService.queryAreablockInfo(arg);
		} catch (Exception e) {
//			this.addFieldError("errermsg", "区块信息获取失败~");
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
		
		String areaid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("areaid")));
		String selecteValue = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("selecteValue")));
		String oilid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("oilid")));
		JSONArray jsonArr = null;
		try {
			jsonArr = ruleWellService.queryStationInfo(oilid,areaid,selecteValue);
		} catch (Exception e) {
			// TODO: handle exception
//			this.addFieldError("errermsg", "供热站信息获取失败~");
			e.printStackTrace();
		}
		if(jsonArr != null){
			out.println(jsonArr);
		}else{
			out.println("");
		}
		return null;
	}
	
	public String queryWellInfo() throws IOException {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String orgid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("orgid")));
		String arg = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("arg")));
		JSONArray jsonArr = null;
		try {
			jsonArr = ruleWellService.queryWellInfo(arg,orgid);
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
	public String querywellAreaSelf() throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String wellAreaSelf = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("wellAreaSelf")));
		JSONArray jsonArr = null;
		try {
			jsonArr = ruleWellService.querywellAreaSelf(wellAreaSelf);
		} catch (Exception e) {
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
			jsonArr = ruleWellService.cascadeInit();
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
	
	/*
	 * Add
	 */
	@SuppressWarnings("unused")
	public String addRuleWell() throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		boolean  addflg = true;
		PcCdRuleWellT rule = null;
		List<PcCdRuleWellT> ruleList = null;
		List<Object[]> orgList = null;
		rule = new PcCdRuleWellT();
		JSONObject obj = new JSONObject();
		//稀油井父节点管汇ID
		String pid = "";
		String rulewellname = StringUtil.toStr(request.getParameter("well_name").trim());
		String pipesink = StringUtil.toStr(request.getParameter("pipesink").trim());
		String manifoldName = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("manifoldname")));
		String well_cole = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("well_cole")));
		String orgid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("orgid")));
		if (pipesink == null || "".equals(pipesink)) {
			List<Object[]> manifoldList;
			try {
				manifoldList = manifoldBasicService.searchManid(manifoldName);
				if (manifoldList != null && manifoldList.size() > 0) {
					pipesink = manifoldList.get(0) + "";
				}
				else {
					outCode = "-11507";
				}
			} catch (Exception e) {
				outCode = "-11507";
				//e.printStackTrace();
			}
			
		}
		//查询常规油井 井名
		try {
			ruleList = ruleWellService.searchRuleByName(rulewellname);
		} catch (Exception e) {
			e.printStackTrace();
			//稀油油井名称获取失败;
			outCode ="-10314";
		}
		if(ruleList !=null && ruleList.size()>0){
			//常规油井名称已存在，不容许添加
			outCode="-10311";
		}
		if("1".equals(outCode)){
			if(pipesink != null && !"".equals(pipesink)){
				//所属管汇
				List<Object[]>  maniList = null;
				try {
					maniList = manifoldBasicService.searchPidBymanifoldid(pipesink);
				} catch (Exception e) {
					e.printStackTrace();
					//-11501	管汇信息获取失败
					outCode = "-11501";
				}
				if(maniList !=null && maniList.size()>0){
					pid = maniList.get(0)[2].toString();
					
				}else{
					//-11507	未获取管汇标识请重新选择管汇
					outCode = "-11507";
				}
			}else{
				//-11507	未获取管汇标识请重新选择管汇
				outCode = "-11507";
			}
		}
		if("1".equals(outCode)){
			
			if(well_cole!=null && !"".equals(well_cole)){
				String sql = "select * from pc_cd_rule_well_t s left join pc_cd_org_t o on s.org_id=o.org_id where 1=1";
				orgList =  orgService.searchOrg(well_cole,orgid, sql);
				if(orgList.size()>0){
					obj = CommonsUtil.getRrturnJson("-11220", "井号编码已存在","", null, null);
					out.print(obj);
					return null;
				}
			}
			User sessionuser = (User) session.getAttribute("userInfo");
			
			String dyear = StringUtil.toStr(request.getParameter("sjcnn").trim());
			if(dyear != null && !"".equals(dyear)){
				rule.setdYear(dyear);
			}
			String jmbz = StringUtil.isNullUtil(request.getParameter("jmbz"));
			if(!"".equals(jmbz)){
				if(jmbz.equals("已加密")){
					rule.setIncreaseFreqFlag((byte) 1);
				}else  if(jmbz.equals("未加密")){
					rule.setIncreaseFreqFlag((byte) 0);
				}
			}
			String increase_interval = StringUtil.isNullUtil(request.getParameter("jmjg"));
			if(!"".equals(increase_interval)){
				rule.setIncreaseInterval(Short.valueOf(increase_interval));
			}
			String jmstime = StringUtil.toStr(request.getParameter("jmstime").trim());
			if(jmstime != null && !"".equals(jmstime)){
				rule.setStartIncreaseFreqTime(DateBean.getStrDateTime(jmstime));
			}
			String jmetime = StringUtil.toStr(request.getParameter("jmetime").trim());
			if(jmetime != null && !"".equals(jmetime)){
				rule.setEndIncreaseFreqTime(DateBean.getStrDateTime(jmetime));
			}
			
			
			rule.setWellName(rulewellname);
			
			String bewell_name = StringUtil.toStr(request.getParameter("bewell_name").trim());
			if(bewell_name != null && !"".equals(bewell_name)){
				rule.setBewellName(bewell_name);
			}
			String outputCoefficient = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("output_coefficient")));
			
			if(outputCoefficient !=null && !"".equals(outputCoefficient)){
				rule.setOutputCoefficient(java.math.BigDecimal.valueOf(Double.parseDouble(outputCoefficient)));
			}else{
				rule.setOutputCoefficient(null);
			}
			String valve_coding =  StringUtil.toStr(request.getParameter("valve_coding").trim());
			if(valve_coding !=null && !"".equals(valve_coding)){
				rule.setValveCoding(Byte.parseByte(valve_coding));
			}else{
				rule.setValveCoding(null);
			}
			String channel_no = StringUtil.toStr(request.getParameter("channel_no").trim());
			if(channel_no !=null && !"".equals(channel_no)){
				rule.setChannelNo(Byte.parseByte(channel_no));
			}else{
				rule.setChannelNo(null);
			}
			rule.setRemark(StringUtil.toStr(request.getParameter("remark").trim()));
			rule.setRlastOper( sessionuser.getOperName());
			rule.setRlastOdate(new Date());
			 PcCdOrgT org = new PcCdOrgT();;
			 //投产日期
			String commissioningDate = StringUtil.toStr(request.getParameter("commissioning_date").trim());
			if(commissioningDate != null && !"".equals(commissioningDate)){
				org.setCommissioningDate(DateBean.getStrDate(commissioningDate));
			}
			org.setCode(StringUtil.toStr(request.getParameter("well_cole").trim()));
			String wellAreaSelf = StringUtil.toStr(request.getParameter("wellAreaSelf").trim());
			rule.setQkid(wellAreaSelf);
			String statusvalues = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("status_value")));
			
			if (!"".equals(statusvalues)) {
				try {
					statusvalues = commonService.getStatusValueINT(statusvalues);
				} catch (Exception e) {
					e.printStackTrace();
				}
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
			String  switchInFlag =  StringUtil.toStr(request.getParameter("jrbz").trim());
			org.setSwitchInFlag(switchInFlag);
			
			//rule.setPcCdServerNodeT(ser);
			org.setStatusValue(statusvalues);
			org.setStructurename(rulewellname);
			org.setPid(pid);
			org.setStructuretype((byte)9);
			org.setRemark(StringUtil.toStr(request.getParameter("remark").trim()));
			org.setPcCdRuleWellT(rule);
			rule.setPcCdOrgT(org);
			try {
				addflg = ruleWellService.addRule(rule);
			} catch (Exception e) {
				String errmsg = e.getCause().getCause().toString();
//				String case1 = errmsg.
				//ORA-02292: 违反完整约束条件 (NEWPORTAL.FK58_PC_RPD_WELL_SAGDD_T) - 已找到子记录
				if(!"".equals(errmsg) && errmsg.indexOf("ORA-01438") != -1){
					obj = CommonsUtil.getRrturnJson("","您输入信息超过数据库数据精度范围" , "",null, null);
				} else{
					obj = CommonsUtil.getRrturnJson("",errmsg ,"", null, null);
				}
				e.printStackTrace();
				//outCode="-10312";
				//常规油井添加失败
			}
		
		}
		if("1".equals(outCode) && addflg == true){
				//添加系统LOG
				try {
					User user1 = (User) session.getAttribute("userInfo");
					PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "2", "常规油井基础信息", rule.getRuleWellid());
					logService.addLog(log);
				} catch (Exception e) {
					e.printStackTrace();
					outCode = "-10003";
				}
				
		}
		/*else{
			//管汇站号获取失败
			outCode="";
		}*/
		if(!"1".equals(outCode)){
			obj = CommonsUtil.getRrturnJson(outCode, "","", null, null);
		}
		out.print(obj);
		return null;
	}
	
	public String updateRuleWell() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		boolean addflg = true;
		PcCdRuleWellT rule = null;
		List<PcCdRuleWellT> ruleList = null;
		List<Object[]> orgList = null;
		rule = new PcCdRuleWellT();
		PcCdOrgT org = null;
		JSONObject obj = new JSONObject();
		//常规油井父节点管汇的ID
		String pid="";
				//查询常规油井 井名
		String rulewellid = StringUtil.toStr(request.getParameter("rule_wellid").trim());
		String rulewellname = StringUtil.toStr(request.getParameter("well_name").trim());
		String pipesink = StringUtil.toStr(request.getParameter("pipesink").trim());
		String well_cole = StringUtil.toStr(request.getParameter("well_cole").trim());
		String orgid = StringUtil.toStr(request.getParameter("org_id").trim());
				try {
					ruleList = ruleWellService.searchRuleById("",rulewellname);
				} catch (Exception e) {
					e.printStackTrace();
					//常规油井名称获取失败;
					outCode ="-10314";
				}
				if(ruleList !=null && ruleList.size()==1){
					if(!rulewellid.equals(ruleList.get(0).getRuleWellid())){
						//常规油井名称已存在，请使用其他常规油井名称
						outCode="-10311";
					}
				}
				if(ruleList !=null && ruleList.size()>1){
					//常规油井名称已存在多条，不容许修改
					outCode="-10313";
				}else{
					try {
						ruleList = new ArrayList<PcCdRuleWellT>();
						ruleList  = ruleWellService.searchRuleById(rulewellid,"");
						if(ruleList != null && ruleList.size()>0){
							rule = ruleList.get(0);
							org = rule.getPcCdOrgT();
						}else{
							//-10315	未识别常规油井ID
							outCode="-10315";
						}
						
					} catch (Exception e) {
						e.printStackTrace();
						//常规油井名称获取失败;
						outCode ="-10314";
					}
				}
				if("1".equals(outCode)){
					//所属管汇
					List<Object[]>  maniList = null;
					try {
						maniList = manifoldBasicService.searchPidBymanifoldid(pipesink);
					} catch (Exception e) {
						e.printStackTrace();
						//-11501	管汇信息获取失败
						outCode = "-11501";
					}
					if(maniList !=null && maniList.size()>0){
						pid = maniList.get(0)[2].toString();
						
					}else{
						//-11507	为获取管汇标识请重新选择管汇	
						outCode = "-11507";
					}
					if("1".equals(outCode)){
						if(well_cole!=null && !"".equals(well_cole)){
							String sql = "select * from pc_cd_rule_well_t s left join pc_cd_org_t o on s.org_id=o.org_id where 1=1";
							orgList =  orgService.searchOrg(well_cole,orgid, sql);
							if(orgList.size()>0){
								obj = CommonsUtil.getRrturnJson("-11220", "井号编码已存在", "",null, null);
								out.print(obj);
								return null;
							}
						}
					User sessionuser = (User) session.getAttribute("userInfo");
					
					String dyear = StringUtil.toStr(request.getParameter("sjcnn").trim());
					if(dyear != null && !"".equals(dyear)){
						rule.setdYear(dyear);
					}
					String jmbz = StringUtil.isNullUtil(request.getParameter("jmbz"));
					if(!"".equals(jmbz)){
						rule.setIncreaseFreqFlag(Byte.parseByte(jmbz));
					}
					
					String increase_interval = StringUtil.isNullUtil(request.getParameter("jmjg"));
					if(!"".equals(increase_interval)){
						rule.setIncreaseInterval(Short.valueOf(increase_interval));
					}
					String jmstime = StringUtil.toStr(request.getParameter("jmstime").trim());
					if(jmstime != null && !"".equals(jmstime)){
						rule.setStartIncreaseFreqTime(StringUtil.parseDate("yyyy-MM-dd hh:mm",jmstime));
					}
					String jmetime = StringUtil.toStr(request.getParameter("jmetime").trim());
					if(jmetime != null && !"".equals(jmetime)){
						rule.setEndIncreaseFreqTime(StringUtil.parseDate("yyyy-MM-dd hh:mm",jmetime));
					}
					
					rule.setWellName(rulewellname);
					
					rule.setBewellName(StringUtil.toStr(request.getParameter("bewell_name").trim()));
					
					String valve_coding =  StringUtil.toStr(request.getParameter("valve_coding").trim());
					if(valve_coding !=null && !"".equals(valve_coding)){
						rule.setValveCoding(Byte.parseByte(valve_coding));
					}else{
						rule.setValveCoding(null);
					}
					String channel_no = StringUtil.toStr(request.getParameter("channel_no").trim());
					if(channel_no !=null && !"".equals(channel_no)){
						rule.setChannelNo(Byte.parseByte(channel_no));
					}else{
						rule.setChannelNo(null);
					}
					//分产系数	
					String outputCoefficient = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("output_coefficient")));
					if(outputCoefficient !=null && !"".equals(outputCoefficient)){
						rule.setOutputCoefficient(java.math.BigDecimal.valueOf(Double.parseDouble(outputCoefficient)));
					}else{
						rule.setOutputCoefficient(null);
					}
					String wellAreaSelf = StringUtil.toStr(request.getParameter("wellAreaSelf").trim());
					rule.setQkid(wellAreaSelf);
					rule.setRemark(StringUtil.toStr(request.getParameter("remark").trim()));
					rule.setRlastOper( sessionuser.getOperName());
					rule.setRlastOdate(new Date());
					org.setCode(StringUtil.toStr(request.getParameter("well_cole").trim()));
					String scadaNode = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("ljjd_s")));
					org.setScadaNode(scadaNode);
					String  switchInFlag = StringUtil.toStr(request.getParameter("jrbz").trim());
					org.setSwitchInFlag(switchInFlag);
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
					org.setStatusValue(statusvalues);
					
					org.setStructurename(rulewellname);
					org.setPid(pid);
					org.setStructuretype((byte)9);
					org.setRemark(StringUtil.toStr(request.getParameter("remark").trim()));
					rule.setPcCdOrgT(org);
					org.setPcCdRuleWellT(rule);
					try {
						addflg = ruleWellService.updateRule(rule);
					} catch (Exception e) {
						
						String errmsg = e.getCause().getCause().toString();
						if(!"".equals(errmsg) && errmsg.indexOf("ORA-01438") != -1){
							obj = CommonsUtil.getRrturnJson("","您输入信息超过数据库数据精度范围" ,"", null, null);
						} else{
							obj = CommonsUtil.getRrturnJson("",errmsg ,"", null, null);
						}
						e.printStackTrace();
						//常规油井更新失败
						//outCode = "-10317";
					
				} 
			if("1".equals(outCode) && addflg == true){
				//添加系统LOG
				//添加系统LOG
				try {
					User user1 = (User) session.getAttribute("userInfo");
					PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "3", "常规油井基础信息", rule.getRuleWellid());
					logService.addLog(log);
				} catch (Exception e) {
					e.printStackTrace();
					outCode = "-10003";
				}
			}
					}
				}
				 /* else{
					outCode = "";
				}*/
				
		if(!"1".equals(outCode)){
			obj = CommonsUtil.getRrturnJson(outCode, "","", null, null);
		}
		
		out.print(obj);
		return null;
	
		}
	@SuppressWarnings("unchecked")
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
		String dyear = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("dyear")));
		String totalNum = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("totalNum")));

		HashMap<String,Object> dataMap = null;
		try {
			dataMap = ruleWellService.searchOnExport(oilstationname1,blockstationname1,rulewellname1,areablock,jrbz1,dyear,totalNum);
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
			java.io.ByteArrayOutputStream baos = DynamicDataExportUtil.basicDataExporReport(dataLsit,templetFilePath,"常规油井基础信息");
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
