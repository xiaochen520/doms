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
import com.echo.dto.PcCdWaterSourceWellT;
import com.echo.dto.PcCdWaterfloodingWellT;
import com.echo.dto.PcRdLoginfoT;
import com.echo.dto.User;
import com.echo.service.CommonService;
import com.echo.service.LogService;
import com.echo.service.OrgService;
import com.echo.service.SagdService;
import com.echo.service.WaterFloodingService;
import com.echo.util.AuthorityUtil;
import com.echo.util.CommonsUtil;
import com.echo.util.DateBean;
import com.echo.util.DynamicDataExportUtil;
import com.echo.util.PageVariableUtil;
import com.echo.util.StringUtil;

public class WaterFloodingAction {
	private WaterFloodingService waterFloodingService;
	private CommonService commonService;
	private SagdService sagdService;
	private InputStream excelFile = null;
	
	
	public InputStream getExcelFile() {
		return excelFile;
	}
	public void setWaterFloodingService(WaterFloodingService waterFloodingService) {
		this.waterFloodingService = waterFloodingService;
	}
	private LogService logService;

	public LogService getLogService() {
		return logService;
	}

	private OrgService orgService;
	public void setOrgService(OrgService orgService) {
		this.orgService = orgService;
	}
	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	
	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}
	public void setSagdService(SagdService sagdService) {
		this.sagdService = sagdService;
	}
	public String getFileName() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd ");
		String downloadFileName = (sf.format(new Date()).toString())+ "注水井基础信息.xls";
		try {
			downloadFileName = new String(downloadFileName.getBytes(),"ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return downloadFileName;
	}
	HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
	public String searchwaterFL() throws IOException {
		
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String stationNumber = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("oilstationname1")));
		String boilersName = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("areablock")));
		String areablock = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("blockstationname1")));
		String oilname = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("rulewellname1")));
		String zsq = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("waterflooding1")));
		String JRBZ1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("JRBZ1")));
		String dyearC = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("dyearC")));
		String groupName = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("groupName")));
		
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
		
 		JSONObject jsonobj = waterFloodingService.searchwaterFL(stationNumber,boilersName,areablock,oilname,zsq,JRBZ1,dyearC,groupName,pageNo,sort,order,rowsPerpage);
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
			gridJson = AuthorityUtil.getGridJson("注水井基础信息", user, PageVariableUtil.WATERFLOODING_PAGE_GRID);
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
	
	public String removeWaterFL() throws IOException {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		String stationId = StringUtil.toStr(request.getParameter("waterflooding_wellid"));
		String orgId = StringUtil.toStr(request.getParameter("org_id"));
		boolean deleteflg = false;
		JSONObject obj = new JSONObject();
		try {
			deleteflg = waterFloodingService.removeStationInfo(stationId,orgId);
		} catch (Exception e) {
			String errmsg = e.getCause().toString();
			//ORA-02292: 违反完整约束条件 (NEWPORTAL.FK58_PC_RPD_WELL_SAGDD_T) - 已找到子记录
			if(!"".equals(errmsg) && errmsg.indexOf("ORA-02292") != -1 && errmsg.indexOf("FK58_PC_RPD_WELL_SAGDD_T") != -1){
				obj = CommonsUtil.getRrturnJson("","注水井基础信息删除失败-注水井日报中存在子记录" ,"", null, null);
			}else if(!"".equals(errmsg) && errmsg.indexOf("ORA-02292") != -1 && errmsg.indexOf("FK_PC_RPD_W_FK52_PC_R_PC_CD_WE") != -1){
				obj = CommonsUtil.getRrturnJson("","注水井基础信息删除失败-注水井班报中存在子记录" ,"", null, null);
			} else{
				obj = CommonsUtil.getRrturnJson("",errmsg ,"", null, null);
			}
			e.printStackTrace();
		}
		
		if(deleteflg){
			
			//添加系统LOG
			try {
				User user1 = (User) session.getAttribute("userInfo");
				PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "2", "注水井基础信息", stationId);
				logService.addLog(log);
			} catch (Exception e) {
				e.printStackTrace();
				obj = CommonsUtil.getRrturnJson("-10003", "","", null, null);
			}
			
			
		}
		out.print(obj);
		return null;
	}
	
	public String queryWaterflooding() throws IOException {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String boilername = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("orgid")));
		JSONArray jsonArr = null;
		try {
			jsonArr = waterFloodingService.queryWaterflooding(boilername);
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
			jsonArr = waterFloodingService.cascadeInit();
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
	public String addorUpdateWaterFlooding() throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		JSONObject obj = new JSONObject();
		PrintWriter out = response.getWriter();
		String outCode = "1";

		List<PcCdWaterfloodingWellT> wellList = null;
		
		PcCdWaterfloodingWellT waterWell = null;
		List<Object[]> orgList = null;
		String id = "";
		String pid = "";
		String well_name = "";
		String blockstationname = "";
		boolean addflg = true;
		String waterfloodingCode = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("waterfloodingCode")));
		String orgid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("org_id")));
		
		User sessionuser = (User) session.getAttribute("userInfo");
		if(request.getParameter("id") != null && !"".equals(request.getParameter("id"))){
			id = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("id")));
		}
		well_name = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("waterflooding_name")));
		try {
			wellList = waterFloodingService.searchWaterFlood("", well_name);
		} catch (Exception e) {
			// -10810注水井井信息获取失败
			e.printStackTrace();
			outCode = "-10810";
		}
		
		//修改
		if(id != null && !"".equals(id)){
			//-10811 注水井名已存在请使用其他注水井名
			if(wellList != null && wellList.size()>0){
				if(!id.equals(String.valueOf(wellList.get(0).getWaterfloodingWellid()))){
					outCode = "-10811";
				}
				
			}
			waterWell = wellList.get(0);
		//添加	
		}else{
			//-10811注水井名已存在请使用其他注水井名
			if(wellList != null && wellList.size()>0){
				outCode = "-10811";
			}
			waterWell = new PcCdWaterfloodingWellT();
		}
		
		if("1".equals(outCode)){
			//获取父id注转站
			blockstationname = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("water_injectiontopr_name")));
			if(blockstationname != null && !"".equals(blockstationname)){
				try {
					orgList = waterFloodingService.searchQiao(blockstationname, "13");
				} catch (Exception e) {
					e.printStackTrace();
					//-10710 所属注水撬信息获取失败
					outCode = "-10710";
				}
				
			}else{
				//-10711 所属注水撬信息未识别
				outCode = "-10711";
			}
			
		}
		if("1".equals(outCode) && orgList != null && orgList.size()>0){
			
			pid = orgList.get(0)[0].toString();
			waterWell.setWaterfloodingName(well_name);
			if (null != request.getParameter("channel_number") && !"".endsWith(request.getParameter("channel_number"))) {
				waterWell.setChannelNumber(Byte.parseByte(request.getParameter("channel_number").trim()));
			}
			if (null != request.getParameter("wellAreaSelf") && !"".endsWith(request.getParameter("wellAreaSelf"))) {
				String wellAreaSelf = StringUtil.toStr(request.getParameter("wellAreaSelf").trim());
				waterWell.setQkid(wellAreaSelf);
			}
			if (null != request.getParameter("remark") && !"".endsWith(request.getParameter("remark"))) {
				waterWell.setRemark(StringUtil.toStr(request.getParameter("remark").trim()));
			}
			String dyear = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("dyear")));
				waterWell.setDyear(dyear);
			waterWell.setRlastOper(sessionuser.getOperName());
			waterWell.setRlastOdate(new Date());
			
			PcCdOrgT org =  null;
			//修改
			if(id != null && !"".equals(id)){
				org = waterWell.getPcCdOrgT();
			}else{
				org = new PcCdOrgT();
			}
			
			org.setStructurename(well_name);
			org.setCode(StringUtil.toStr(request.getParameter("waterfloodingCode").trim()));
			String status_value = StringUtil.toStr(request.getParameter("status_value").trim());
			try {
				if(status_value != null && !"".equals(status_value)){
					status_value = commonService.getStatusValueINT(status_value);
				}
			} catch (Exception e) {
				e.printStackTrace();
				//-10010 建设状态获取失败
				outCode = "-10010";
			}
			if("1".equals(outCode)){
				if(waterfloodingCode!=null && !"".equals(waterfloodingCode)){
					String sql = "select * from pc_cd_waterflooding_well_t s left join pc_cd_org_t o on s.org_id=o.org_id where 1=1";
					orgList =  orgService.searchOrg(waterfloodingCode,orgid, sql);
					if(orgList.size()>0){
						obj = CommonsUtil.getRrturnJson("-11220", "井号编码已存在","", null, null);
						out.print(obj);
						return null;
					}
				}
				
				org.setStatusValue(status_value);
				String commissioningDate = StringUtil.toStr(request.getParameter("commissioning_date").trim());
				if(commissioningDate != null && !"".equals(commissioningDate)){
					org.setCommissioningDate(DateBean.getStrDate(commissioningDate));
				}else{
					org.setCommissioningDate(null);
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
				String switchInFlag = StringUtil.toStr(request.getParameter("jrbz").trim());
				org.setSwitchInFlag(switchInFlag);
				//waterWell.setPcCdServerNodeT(ser);
				org.setStructuretype((byte) 9);
				org.setPid(pid);
				org.setPcCdWaterfloodingWellT(waterWell);
				waterWell.setPcCdOrgT(org);
				
				try {
					if(id != null && !"".equals(id)){
						addflg = waterFloodingService.updatewell(waterWell);
					}else{
						addflg = waterFloodingService.addwell(waterWell);
					}
				} catch (Exception e) {
					e.printStackTrace();
					if(id != null && !"".equals(id)){
//						-10812注水井信息修改失败
						outCode = "-10812";
					}else{
//						-10813 注水井信息添加失败
						outCode = "-10813";
					}
				}
			}
			
			
		}
		
		if("1".equals(outCode) && addflg == true){
			if(id != null && !"".equals(id)){
				//添加系统LOG
				try {
					PcRdLoginfoT log = CommonsUtil.getLogInfo(sessionuser, "3", "注水井基础信息", waterWell.getWaterfloodingWellid());
					logService.addLog(log);
				} catch (Exception e) {
					e.printStackTrace();
					outCode = "-10002";
				}
			}else{
				//添加系统LOG
				try {
					PcRdLoginfoT log = CommonsUtil.getLogInfo(sessionuser, "1", "注水井基础信息", waterWell.getWaterfloodingWellid());
					logService.addLog(log);
				} catch (Exception e) {
					e.printStackTrace();
				
					outCode = "-10001";
				}
			}
			
			
		}
		out.print(outCode);
		return null;
	}
	//导出报表
	public  String onExport()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
	
		String oilstationname1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("oilstationname1")));
		String rulewellname1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("rulewellname1")));
		String areablock = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("areablock")));
		String waterflooding1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("waterflooding1")));
		String jrbz1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("JRBZ1")));
		String totalNum = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("totalNum")));
		String dyearC = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("dyearC")));
		String groupName = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("groupName")));
//		if("".equals(oilationname)  && "".equals(blockstationname) 
//				 && "".equals(boilersName) && "".equals(boilersName) && "".equals(totalNum)){
//			out.println("");
//			return null;
//		}
//	
//		int pageNo = 1; //页索引参数名当前页
//		String sort = "";		//页排序列名
//		String order = "";//页排序方向
//		int rowsPerpage = 0; //每页显示条数
//		if(request.getParameter("pageNo") != null && !"".equals(request.getParameter("pageNo"))){
//			pageNo = Integer.parseInt(request.getParameter("pageNo").trim());	
//		}
//		
//		if(request.getParameter("sort") != null){
//			sort = request.getParameter("sort").trim();
//		}
//		
//		if(request.getParameter("order") != null){
//			order = request.getParameter("order").trim();
//		}
//		
//		if( request.getParameter("rowsPerpage") != null && !"".equals( request.getParameter("rowsPerpage"))){
//			rowsPerpage = Integer.parseInt(request.getParameter("rowsPerpage").trim());
//		}
		HashMap<String,Object> dataMap = null;
		try {
			//dataMap = waterFloodingService.searchOnExport(totalNum);
			
			dataMap = waterFloodingService.searchOnExport(oilstationname1,rulewellname1,waterflooding1,areablock,jrbz1,dyearC,groupName,totalNum);
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
			java.io.ByteArrayOutputStream baos = DynamicDataExportUtil.basicDataExporReport(dataLsit,templetFilePath,"注水井基础信息");
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
	public String oilStation() throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String boilername = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("orgid")));
		JSONArray jsonArr = null;
		try {
			jsonArr = waterFloodingService.queryOilStation(boilername);
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
	public String injectName() throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		//String boilername = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("orgid")));
		JSONArray jsonArr = null;
		try {
			jsonArr = waterFloodingService.injectName();
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
}
