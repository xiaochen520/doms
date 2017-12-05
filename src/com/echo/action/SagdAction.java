package com.echo.action;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
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
import com.echo.dto.PcCdWellSagdT;
import com.echo.dto.PcRdLoginfoT;
import com.echo.dto.User;
import com.echo.service.CommonService;
import com.echo.service.LogService;
import com.echo.service.ManifoldBasicService;
import com.echo.service.OrgService;
import com.echo.service.RuleWellService;
import com.echo.service.SagdService;
import com.echo.util.AuthorityUtil;
import com.echo.util.CommonsUtil;
import com.echo.util.DateBean;
import com.echo.util.DynamicDataExportUtil;
import com.echo.util.PageVariableUtil;
import com.echo.util.StringUtil;

public class SagdAction {
	private SagdService sagdService;
	private RuleWellService ruleWellService;
	private ManifoldBasicService manifoldBasicService;
	@SuppressWarnings("unused")
	private CommonService commonService;
	private OrgService orgService;
	private InputStream excelFile = null;
	public void setOrgService(OrgService orgService) {
		this.orgService = orgService;
	}

	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}

	public void setSagdService(SagdService sagdService) {
		this.sagdService = sagdService;
	}
	private LogService logService;

	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	
	public void setManifoldBasicService(ManifoldBasicService manifoldBasicService) {
		this.manifoldBasicService = manifoldBasicService;
	}
	public InputStream getExcelFile() {
		return excelFile;
	}
	public void setExcelFile(InputStream excelFile) {
		this.excelFile = excelFile;
	}
	public String getFileName() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd ");
		String downloadFileName = (sf.format(new Date()).toString())+ "SAGD井基础信息.xls";
		try {
			downloadFileName = new String(downloadFileName.getBytes(),"ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return downloadFileName;
	}
	HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
	@SuppressWarnings("unchecked")
	public String searchSagdBaisc() throws IOException {
		
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String stationNumber = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("oilstationname")));
		String boilersName = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("areablock")));
		String oilNumber = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("oilationname")));
		String ghname = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("ghmc")));
		String oilname = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("wellnum")));
		String jrbz = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("jrbz")));
		String prodStages = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("prod_stages")));
		String statusValue = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("status_value")));
		String dyear = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("dyear1")));
		String totalNum = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("totalNum")));
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
 		HashMap<String,Object> dataMap = null;
 		try {
 			dataMap = sagdService.searchSagdWell(dyear,jrbz, prodStages, statusValue, totalNum,stationNumber,boilersName,oilNumber,ghname,oilname,pageNo,sort,order,rowsPerpage);
		} catch (Exception e) {
			// TODO: handle exception
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
			java.io.ByteArrayOutputStream baos = DynamicDataExportUtil.basicDataExporReport(dataLsit,templetFilePath,"SAGD井基础信息");
			if(baos != null){
				byte[] ba = baos.toByteArray();
				excelFile = new ByteArrayInputStream(ba);
				baos.flush();
				baos.close();
			}
			return "excel";
		}
		out.println("");
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
			gridJson = AuthorityUtil.getGridJson("SAGD井基础信息", user, PageVariableUtil.SAGD_PAGE_GRID);
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
	public String removeSagd() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		String stationId = StringUtil.toStr(request.getParameter("sagdId"));
		String orgId = StringUtil.toStr(request.getParameter("org_id"));
		boolean deleteflg = false;
		JSONObject obj = new JSONObject();
		try {
			deleteflg = sagdService.removeStationInfo(stationId,orgId);
		} catch (Exception e) {

			String errmsg = e.getCause().toString();
			//ORA-02292: 违反完整约束条件 (NEWPORTAL.FK58_PC_RPD_WELL_SAGDD_T) - 已找到子记录
			if(!"".equals(errmsg) && errmsg.indexOf("ORA-02292") != -1 ){
				obj = CommonsUtil.getRrturnJson("","SAGD基础信息删除失败-SAGD日报中存在子记录" , "",null, null);
			}else if(!"".equals(errmsg) && errmsg.indexOf("ORA-02292") != -1 ){
				obj = CommonsUtil.getRrturnJson("","SAGD基础信息删除失败-SAGD班报中存在子记录" , "",null, null);
				//FK01_PC_CD_WELL_CELL_T
			} else if(!"".equals(errmsg) && errmsg.indexOf("ORA-02292") != -1 ){
				obj = CommonsUtil.getRrturnJson("","SAGD基础信息删除失败-井口所属炉线信息中存在子记录" ,"", null, null);
				
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
				PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "2", "SAGD井基础信息", stationId);
				logService.addLog(log);
			} catch (Exception e) {
				e.printStackTrace();
				//outCode = "-10003";
				obj = CommonsUtil.getRrturnJson("-10003", "", "",null, null);
			}
			
			
		}
		out.print(obj);
		return null;
	}
	//区块号
	public String queryAreablockInfo() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONArray jsonArr = null;
		String arg = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("arg")));
		
		try {
			jsonArr = sagdService.queryAreablockInfo(arg);
		} catch (Exception e) {
			//this.addFieldError("errermsg", "区块信息获取失败~");
			e.printStackTrace();
		}
		if(jsonArr != null){
			out.println(jsonArr);
		}else{
			out.println("");
		}
		return null;
	}
	
	//
	
	public String searchAreablockInfo() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONArray jsonArr = null;
		String arg = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("arg")));
		
		try {
			jsonArr = sagdService.searchAreablockInfo(arg);
		} catch (Exception e) {
			//this.addFieldError("errermsg", "区块信息获取失败~");
			e.printStackTrace();
		}
		if(jsonArr != null){
			out.println(jsonArr);
		}else{
			out.println("");
		}
		return null;
	}
	
	
	
	public String queryoilationnameInfo() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONArray jsonArr = null;
		String arg = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("arg")));
		
		try {
			jsonArr = sagdService.queryoilationnameInfo(arg);
		} catch (Exception e) {
			//this.addFieldError("errermsg", "区块信息获取失败~");
			e.printStackTrace();
		}
		if(jsonArr != null){
			out.println(jsonArr);
		}else{
			out.println("");
		}
		return null;
	}
	
	public String searchoilationnameInfo() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONArray jsonArr = null;
		String arg = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("arg")));
		
		try {
			jsonArr = sagdService.searchoilationnameInfo(arg);
		} catch (Exception e) {
			//this.addFieldError("errermsg", "区块信息获取失败~");
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
		
		String areaid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("areaid")));
		JSONArray jsonArr = null;
		try {
			jsonArr = sagdService.queryStationInfo(areaid);
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
	
	
	public String queryGhmcInfo() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		
		String oilationnameid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("oilationnameid")));
		JSONArray jsonArr = null;
		try {
			jsonArr = sagdService.queryGhmcInfo(oilationnameid);
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
	
	
	
	public String queryBZInfo() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();		
		String arg = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("arg")));
		JSONArray jsonArr = null;
		try {
			jsonArr = sagdService.queryBZInfo(arg);
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
	
	
	
	
	public String queryWellNameInfo() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String ghmcid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("ghmcid")));
		System.out.println(ghmcid+"#######");
		JSONArray jsonArr = null;
		try {
			jsonArr = sagdService.queryWellNameInfo(ghmcid);
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
	
	public String queryWellNameInfo1() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String boilername = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("ghid")));
		JSONArray jsonArr = null;
		try {
			jsonArr = sagdService.queryWellNameInfo1(boilername);
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
	
	public String queryDescInfo() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String boilername = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("type")));
		JSONArray jsonArr = null;
		try {
			jsonArr = sagdService.queryDescInfo(boilername);
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
	
	
	public String cascadeInit() throws Exception {
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONObject jsonArr = null;
		try {
			jsonArr = sagdService.cascadeInit();
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
	
	//sagd分站级联采油站-管汇-井
	public String cascadeInit1() throws Exception {
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONObject jsonArr = null;
		try {
			jsonArr = sagdService.cascadeInit1();
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
	
	
	@SuppressWarnings("unused")
	public String addOrUpdateSagd() throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		boolean addflg  = true;
		JSONObject obj = new JSONObject();
		PcCdWellSagdT  sagd= null;
		PcCdOrgT org = null;
		List<PcCdWellSagdT> sagdlist = null;
		List<PcCdWellSagdT> sagdlistid = null;
		List<Object[]> orgList = null;
		List<Object[]> ghlist = null;
		
		//父节点管汇ID
		String pid = null;

		String sagdid = "";
		if(request.getParameter("sagdid")!= null && !"".equals(request.getParameter("sagdid"))){
			sagdid =StringUtil.toStr(request.getParameter("sagdid").trim());//井名id
			
		}
		String sagdwellname = StringUtil.toStr(request.getParameter("jhmc").trim());//井名
		String jhmc_s = StringUtil.toStr(request.getParameter("jhmc_s").trim());
		String manifold = StringUtil.toStr(request.getParameter("manifold").trim());
		String manifoldName = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("manifoldname")));
		String orgid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("org_id")));
		if (manifold == null || "".equals(manifold)) {
			List<Object[]> manifoldList = manifoldBasicService.searchManid(manifoldName);
			if (manifoldList != null && manifoldList.size() > 0) {
				manifold = manifoldList.get(0)[0].toString();
			}
			else {
				outCode = "-11507";
			}
		}
		
		try {
			sagdlist =  sagdService.searchSagdname("",sagdwellname);
		} catch (Exception e) {
			e.printStackTrace();
			//-10201 SAGD井信息获取失败
			outCode = "-10201";
		}
		
		
		//修改
		//String sagdid = StringUtil.toStr(request.getParameter("sagdid").trim());
		if(sagdid != null && !"".equals(sagdid)){
			
			if(sagdlist != null && sagdlist.size()>0){
				if(!sagdid.equals(sagdlist.get(0).getSagdid())){
					//-10206 SAGD井名已存在请使用其他井名
					outCode = "-10206";
				}
			}
			
			if("1".equals(outCode)){
				try {
					sagdlistid =  sagdService.searchSagdname(sagdid,"");
				} catch (Exception e) {
					e.printStackTrace();
					//-10201 SAGD井信息获取失败
					outCode = "-10201";
				}
				
				if(sagdlistid != null && sagdlistid.size()>0){
					sagd = sagdlistid.get(0);
					org = sagd.getPcCdOrgT();
				}else{
					//-10207 SAGD井ID未识别
					outCode = "-10207";
				}
				
			}
			
		//添加
		}else{
			if(sagdlist != null && sagdlist.size()>0){
				//-10206 SAGD井名已存在请使用其他井名
				outCode = "-10206";
			}
			sagd = new PcCdWellSagdT();
			org = new PcCdOrgT();
		}
		
		if("1".equals(outCode)){
			
			if(manifold != null && !"".equals(manifold)){
				try {
					ghlist = manifoldBasicService.searchPidBymanifoldid(manifold);
				} catch (Exception e) {
					e.printStackTrace();
					//-11501 管汇信息获取失败
					outCode = "-11501";
				}
				
				if(ghlist != null && ghlist.size()>0){
					pid = ghlist.get(0)[2].toString();
					
				}else{
					//-11501 管汇信息获取失败
					outCode = "-11501";
				}
				
			}else{
				//-11507 为获取管汇标识请重新选择管汇
				outCode = "-11507";
			}
			
		}
			if("1".equals(outCode)){
				if(jhmc_s!=null && !"".equals(jhmc_s)){
					String sql = "select * from Pc_Cd_Well_Sagd_t s left join pc_cd_org_t o on s.org_id=o.org_id where 1=1";
					orgList =  orgService.searchOrg(jhmc_s,orgid,sql);
					if(orgList.size()>0 && !orgList.get(0)[0].equals(sagdid)){
						obj = CommonsUtil.getRrturnJson("-11220", "井号编码已存在","", null, null);
						out.print(obj);
						return null;
					}
				}
				
				User sessionuser = (User) session.getAttribute("userInfo");
				sagd.setJhmc(sagdwellname); //井名
				
				PcCdServerNodeT ser = new PcCdServerNodeT(); //服务器逻辑表
				//ser.setLjjdS(StringUtil.toStr(request.getParameter("ljjd_s").trim())); //scada服务器逻辑节点名  
				List<PcCdServerNodeT> serverlist = null;
//				try {
//					serverlist = sagdService.getServerNode(StringUtil.toStr(request.getParameter("ljjd_s").trim()));
//				} catch (Exception e) {
//					e.printStackTrace();
//					//-10208 服务器逻辑节点信息获取失败
//					outCode = "-10208";
//					
//				}
//				//serverlist.get(0);
//				if(serverlist != null && serverlist.size()>0){
//					ser = serverlist.get(0);
//				}else{
//					//-10208 服务器逻辑节点信息获取失败
//					outCode = "-10208";
//				}
				if("1".equals(outCode)){
//					sagd.setPcCdServerNodeT(ser);
					sagd.setDtfmcS(StringUtil.toStr(request.getParameter("dtfmc_s").trim()));//多通阀编码
					if(request.getParameter("tdh") != null && !"".equals(request.getParameter("tdh"))){
						sagd.setTdh(Byte.parseByte(request.getParameter("tdh").trim()));//通道号
					}else{
						sagd.setTdh(null);//通道号
					}
					String auf = StringUtil.toStr(request.getParameter("auf").trim()); //上自动化标志
					if(auf.equals("已上")){
						sagd.setAuf("1");
					}else if(auf.equals("未上")){
						sagd.setAuf("0");
					}else{
						sagd.setAuf("");
					}
					
					String whrtu_cnf = StringUtil.toStr(request.getParameter("whrtu_cnf").trim());//井口控制器接入
					if(whrtu_cnf.equals("已接")){
						sagd.setWhrtuCnf("1");
					}else  if(whrtu_cnf.equals("未接")){
						sagd.setWhrtuCnf("0");
					}else{
						sagd.setWhrtuCnf("");
					}
					String wsrtu_cnf = StringUtil.toStr(request.getParameter("wsrtu_cnf").trim());//蒸气控制器接入
					if(wsrtu_cnf.equals("已接")){
						sagd.setWsrtuCnf("1");
					}else  if(wsrtu_cnf.equals("未接")){
						sagd.setWhrtuCnf("0");
					}else{
						sagd.setWhrtuCnf("");
					}
					String p_ugrtu_cnf = StringUtil.toStr(request.getParameter("p_ugrtu_cnf").trim());//井下控制器接入
					if(p_ugrtu_cnf.equals("已接")){
						sagd.setPUgrtuCnf("1");
					}else  if(p_ugrtu_cnf.equals("未接")){
						sagd.setPUgrtuCnf("0");
					}else{
						sagd.setPUgrtuCnf("");
					}
					if(request.getParameter("p_ugtem_num") != null && !"".equals(request.getParameter("p_ugtem_num"))){
						sagd.setPUgtemNum(Byte.parseByte(request.getParameter("p_ugtem_num").trim()));//井下温度点数
					}else{
						sagd.setPUgtemNum(null);//井下温度点数
					}
					
					String p_purtu_cnf= StringUtil.toStr(request.getParameter("p_purtu_cnf").trim());//抽油机控制器接入
					if(p_purtu_cnf.equals("已接")){
						sagd.setPPurtuCnf("1");
					}else  if(p_purtu_cnf.equals("未接")){
						sagd.setPPurtuCnf("0");
					}else{
						sagd.setPPurtuCnf("");
					}
					sagd.setRemark(StringUtil.toStr(request.getParameter("remark").trim()));
					String wwork_flag = StringUtil.toStr(request.getParameter("wwork_flag").trim());//施工井标志
					if(wwork_flag.equals("施工")){
						sagd.setWworkFlag("1");
						if(request.getParameter("wwork_date") != null && !"".equals(request.getParameter("wwork_date"))){
							sagd.setWworkDate(DateBean.getStrDate(StringUtil.toStr(request.getParameter("wwork_date").trim()))); //施工开始日期
						}
					}else  if(wwork_flag.equals("未施工")){
						sagd.setWworkFlag("0");
						sagd.setWworkDate(null); //施工开始日期
					}else{
						sagd.setWworkFlag("");
					}
					
					String wabnormal_flag = StringUtil.toStr(request.getParameter("wabnormal_flag").trim());//异常井标志
					if(wabnormal_flag.equals("异常")){
						sagd.setWabnormalFlag("1");
						if(request.getParameter("wabnormal_date") != null && !"".equals(request.getParameter("wabnormal_date"))){
							sagd.setWabnormalDate(DateBean.getStrDate(request.getParameter("wabnormal_date").trim())); //异常井开始时间
						}
					}else  if(wabnormal_flag.equals("正常")){
						sagd.setWabnormalFlag("0");
							sagd.setWabnormalDate(null); //异常井开始时间
					}
					if(request.getParameter("wabnormal_date") != null && !"".equals(request.getParameter("wabnormal_date"))){
						sagd.setWabnormalDate(DateBean.getStrDate(request.getParameter("wabnormal_date").trim())); //异常井开始时间
					}
					if(request.getParameter("wells_num") != null && !"".equals(request.getParameter("wells_num"))){
						sagd.setWellsNum(Byte.parseByte(StringUtil.toStr(request.getParameter("wells_num").trim())));//井口数
					}else{
						sagd.setWellsNum(null);//井口数
					}
					sagd.setWellsNam(StringUtil.toStr(request.getParameter("wells_nam").trim())); //井口名称列举
					
					if(request.getParameter("scan_time") != null && !"".equals(request.getParameter("scan_time"))){
						String scan_time = StringUtil.toStr(request.getParameter("scan_time").trim());
						if(scan_time.toUpperCase().indexOf("M") == -1){
							scan_time += "M";
						}
						sagd.setScanTime(scan_time); //ifix触发器扫描时间
					}
					if(request.getParameter("phase") != null && !"".equals(request.getParameter("phase"))){
						sagd.setPhase(StringUtil.toStr(request.getParameter("phase").trim()));//ifix触发器相位时间
					}
					if(request.getParameter("dyear") != null && !"".equals(request.getParameter("dyear"))){
						sagd.setDyear(StringUtil.toStr(request.getParameter("dyear").trim())); //设计产能年
					}
					
					String p_dlt_cnf = StringUtil.toStr(request.getParameter("p_dlt_cnf").trim());//有无电流图标志
					if(p_dlt_cnf.equals("无")){
						sagd.setPDltCnf("1");
					}else  if(p_dlt_cnf.equals("有")){
						sagd.setPDltCnf("0");
					}else{
						sagd.setPDltCnf("");
					}
					sagd.setWellRtuAdr(StringUtil.toStr(request.getParameter("well_rtu_adr").trim()));//井口RTU地址:
					//分产系数
					String outputCoefficient = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("output_coefficient")));
					if(outputCoefficient !=null && !"".equals(outputCoefficient)){
						sagd.setOutputCoefficient(java.math.BigDecimal.valueOf(Double.parseDouble(outputCoefficient)));
					}else{
						sagd.setOutputCoefficient(null);
					}
					
					String prodStages = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("prod_stages"))); //生产阶段	
					sagd.setProdStages(prodStages);
					String pProdMode = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("p_prod_mode"))); //P井生产方式
					try {
						if(pProdMode != null && !"".equals(pProdMode)){
							List<Object[]> pmode = sagdService.SearchMode("", pProdMode);
							if(pmode != null && pmode.size()>0){
								sagd.setPProdMode(pmode.get(0)[0].toString());
							}else{
								//P井生产方式信息获取失败请重新选择；
								outCode ="-10213";
							}
						}else{
							sagd.setPProdMode(null);
						}
						
					} catch (Exception e) {
						e.printStackTrace();
						outCode ="-10213";
					}
					
//						
					
					String iProdMode = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("i_prod_mode"))); //I井生产方式
					try {
						if(iProdMode != null && !"".equals(iProdMode)){
							List<Object[]> imode = sagdService.SearchMode("", iProdMode);
							if(imode != null && imode.size()>0){
								sagd.setIProdMode(imode.get(0)[0].toString());
							}else{
								//P井生产方式信息获取失败请重新选择；
								outCode ="-10214";
							}
							
						}else{
							sagd.setIProdMode(null);
						}
					} catch (Exception e) {
						e.printStackTrace();
						outCode ="-10214";
					}
					//sagd.setiProdMode(iProdMode);
					
					
					sagd.setRlastOper(sessionuser.getOperName());
					sagd.setRlastOdate(new Date());
					String wellAreaSelf = StringUtil.toStr(request.getParameter("wellAreaSelf").trim());
					sagd.setQkid(wellAreaSelf);
					
					String commissioningDate = StringUtil.toStr(request.getParameter("commissioning_date").trim());
					if(commissioningDate != null && !"".equals(commissioningDate)){
						org.setCommissioningDate(DateBean.getStrDate(commissioningDate));
					}
					if (!"".equals(StringUtil.isNullUtil(request.getParameter("jhmc_s")))) {
							org.setCode(request.getParameter("jhmc_s")); //井码
					}
				/*	String statusvalues = "";
					try {
						String status_value = StringUtil.isNullUtil(request.getParameter("status_value"));
						if(status_value != null && !"".equals(status_value)){
							statusvalues = commonService.getStatusValueINT(status_value);
						}
						
					} catch (Exception e) {
						e.printStackTrace();
						//-10010	建设状态获取失败
						outCode = "-10010";
						
					}*/
					String start_increase_freq_time = StringUtil.isNullUtil(request.getParameter("start_increase_freq_time"));
					if(!"".equals(start_increase_freq_time)){
						sagd.setStartIncreaseFreqTime(DateBean.getFormatDate(start_increase_freq_time + ":00","yyyy-MM-dd HH:mm:ss"));
					}
					String end_increase_freq_time = StringUtil.isNullUtil(request.getParameter("end_increase_freq_time"));
					if(!"".equals(end_increase_freq_time)){
						sagd.setEndIncreaseFreqTime(DateBean.getFormatDate(end_increase_freq_time + ":00","yyyy-MM-dd HH:mm:ss"));
					}
					String increase_freq_flag = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("increase_freq_flag")));
					if(!"".equals(increase_freq_flag)){
						if(increase_freq_flag.equals("已加密")){
							sagd.setIncreaseFreqFlag((byte) 0);
						}else  if(increase_freq_flag.equals("未加密")){
							sagd.setIncreaseFreqFlag((byte) 1);
						}
					}
					String increase_interval = StringUtil.isNullUtil(request.getParameter("increase_interval"));
					if(!"".equals(increase_interval)){
						sagd.setIncreaseInterval(Short.valueOf(increase_interval));
					}
					String statusvalues = StringUtil.isNullUtil(request.getParameter("status_value"));
					if (!"".equals(statusvalues)) {
						org.setStatusValue(statusvalues);		
					}
					org.setStructurename(sagdwellname);
					String scadaNode =  StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("ljjd_s")));
					org.setScadaNode(scadaNode);
					String switchInFlag = StringUtil.toStr(request.getParameter("jrbz").trim());
					org.setSwitchInFlag(switchInFlag);
					org.setPid(pid);
					org.setStructuretype((byte)9);
					org.setPcCdWellSagdT(sagd);
					sagd.setPcCdOrgT(org);
					
					if("1".equals(outCode)){
						//修改
						if(sagdid != null && !"".equals(sagdid)){
							try {
								addflg = sagdService.updateSagd(sagd);
							} catch (Exception e) {
								String  errmsg = e.getCause().getCause().toString();
								
								if(!"".equals(errmsg) && errmsg.indexOf("ORA-01438") != -1){
									obj = CommonsUtil.getRrturnJson("","您输入信息超过数据库数据精度范围" ,"", null, null);
								} else{
									obj = CommonsUtil.getRrturnJson("",errmsg ,"", null, null);
								}
								e.printStackTrace();
								//-10202 SAGD井信息添加失败
								//outCode = "-10202";
							}
						}else{
							try {
								addflg = sagdService.addSagd(sagd);
							} catch (Exception e) {
								String  errmsg = e.getCause().toString();
								
								if(!"".equals(errmsg) && errmsg.indexOf("ORA-01438") != -1){
									obj = CommonsUtil.getRrturnJson("","您输入信息超过数据库数据精度范围" ,"", null, null);
								} else{
									obj = CommonsUtil.getRrturnJson("",errmsg ,"", null, null);
								}
								e.printStackTrace();
								//-10203 SAGD井信息修改失败
								//outCode = "-10203";
							}
						}
					}
					
					if("1".equals(outCode) && addflg == true){
						if(sagdid != null && !"".equals(sagdid)){
							//添加系统LOG
							try {
								User user1 = (User) session.getAttribute("userInfo");
								PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "3", "SAGD井基础信息", sagd.getSagdid());
								logService.addLog(log);
							} catch (Exception e) {
								e.printStackTrace();
								outCode = "-10002";
							}
						}else{
							//添加系统LOG
							try {
								User user1 = (User) session.getAttribute("userInfo");
								PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "1", "SAGD井基础信息", sagd.getSagdid());
								logService.addLog(log);
							} catch (Exception e) {
								e.printStackTrace();
								outCode = "-10001";
							}
							
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
	
	public String SearchPstages() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String prod_stages = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("prod_stages")));
		JSONArray jsonArr = null;
		try {
			jsonArr  = sagdService.SearchPstages(prod_stages);
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
	public String SearchPmode() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String p_prod_mode = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("p_prod_mode")));
		JSONArray jsonArr = null;
		try {
			jsonArr  = sagdService.SearchPmode(p_prod_mode);
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
	public String SearchImode() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String i_prod_mode = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("i_prod_mode")));
		JSONArray jsonArr = null;
		try {
			jsonArr  = sagdService.SearchPmode(i_prod_mode);
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
	public String getServerNode() throws Exception {
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		List<PcCdServerNodeT> sertverList = null;
		JSONArray arr = null;
		try {
			sertverList  = sagdService.getServerNode("");
			
			if(sertverList != null && sertverList.size()>0){
				arr = new JSONArray();
				JSONObject jsobj = null;
				for(PcCdServerNodeT node :sertverList){
					jsobj = new JSONObject();
					jsobj.put("id", node.getScadaNode());
					jsobj.put("text",node.getRemark());
					arr.add(jsobj);
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(arr != null){
			out.println(arr);
		}else{
			out.println("");
		}
		return null;
	}
	public String searchAutoCompleteData() throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String flg = StringUtil.toStr(request.getParameter("flg").trim());
		JSONArray welljson =  sagdService.searchAutoCompleteData(flg);
		
		if(welljson != null){
			out.println(welljson);
		}else{
			out.println("");
		}
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
	
	
	
	
	
	
}
