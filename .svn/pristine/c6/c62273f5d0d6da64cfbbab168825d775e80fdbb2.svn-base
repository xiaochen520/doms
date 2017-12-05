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

import com.echo.dto.PcCdGasWellT;
import com.echo.dto.PcCdOrgT;
import com.echo.dto.PcCdServerNodeT;
import com.echo.dto.PcRdLoginfoT;
import com.echo.dto.User;
import com.echo.service.CommonService;
import com.echo.service.GasWellService;
import com.echo.service.LogService;
import com.echo.service.OrgService;
import com.echo.service.SagdService;
import com.echo.util.AuthorityUtil;
import com.echo.util.CommonsUtil;
import com.echo.util.DateBean;
import com.echo.util.DynamicDataExportUtil;
import com.echo.util.PageVariableUtil;
import com.echo.util.StringUtil;

public class GasWellAction {
	private GasWellService gasWellService;
	private  CommonService  commonService;
	private SagdService sagdService;
	private InputStream excelFile = null;
	
	public InputStream getExcelFile() {
		return excelFile;
	}

	public void setGasWellService(GasWellService gasWellService) {
		this.gasWellService = gasWellService;
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
	
	public void setSagdService(SagdService sagdService) {
		this.sagdService = sagdService;
	}
	public String getFileName() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd ");
		String downloadFileName = (sf.format(new Date()).toString())+ "气井基础信息.xls";
		try {
			downloadFileName = new String(downloadFileName.getBytes(),"ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return downloadFileName;
	}
	HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
	public String searchGasWell() throws IOException {
		
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String stationNumber = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("oilstationname1")));
		String boilersName = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("areablock1")));
		String areablock = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("blockstationname1")));
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
		
 		JSONObject jsonobj = gasWellService.searchGasWell(stationNumber,boilersName,areablock,oilname,jrbz1,dyearC,pageNo,sort,order,rowsPerpage);
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
			gridJson = AuthorityUtil.getGridJson("气井基础信息", user, PageVariableUtil.GAS_WELL_PAGE_GRID);
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
	
	public String removeGasWell() throws IOException {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		String stationId = StringUtil.toStr(request.getParameter("gas_wellid"));
		String orgId = StringUtil.toStr(request.getParameter("org_id"));
		boolean deleteflg = false;
		JSONObject obj = new JSONObject();
		try {
			deleteflg = gasWellService.removeStationInfo(stationId,orgId);
		} catch (Exception e) {
			String errmsg = e.getCause().toString();
			//ORA-02292: 违反完整约束条件 (NEWPORTAL.FK58_PC_RPD_WELL_SAGDD_T) - 已找到子记录
			if(!"".equals(errmsg) && errmsg.indexOf("ORA-02292") != -1 && errmsg.indexOf("FK58_PC_RPD_WELL_SAGDD_T") != -1){
				obj = CommonsUtil.getRrturnJson("","气井基础信息删除失败-气井日报中存在子记录" ,"", null, null);
			}else if(!"".equals(errmsg) && errmsg.indexOf("ORA-02292") != -1 && errmsg.indexOf("FK_PC_RPD_W_FK52_PC_R_PC_CD_WE") != -1){
				obj = CommonsUtil.getRrturnJson("","气井基础信息删除失败-气井班报中存在子记录" , "",null, null);
			} else{
				obj = CommonsUtil.getRrturnJson("",errmsg ,"", null, null);
			}
			e.printStackTrace();
		}
		
		if(deleteflg){
			
			//添加系统LOG
			try {
				User user1 = (User) session.getAttribute("userInfo");
				PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "2", "气井基础信息", stationId);
				logService.addLog(log);
			} catch (Exception e) {
				e.printStackTrace();
				obj = CommonsUtil.getRrturnJson("-10003", "","", null, null);
			}
			
			
		}
		out.print(obj);
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
			jsonArr = gasWellService.queryStationInfo(oilid,areaid,selecteValue);
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
		String boilername = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("orgid")));
		JSONArray jsonArr = null;
		try {
			jsonArr = gasWellService.queryWellInfo(boilername);
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
			jsonArr = gasWellService.cascadeInit();
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
	public String addGasWell() throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		boolean  addflg = true;
		List<PcCdGasWellT> gasList = null;
		List<Object[]> orgList = null;
		JSONObject obj = new JSONObject();
		PcCdGasWellT gas = null;
		gas = new PcCdGasWellT();
		
		String pid = "";
		String gaswellname = StringUtil.toStr(request.getParameter("well_name").trim());
		//所属气站
		String gasstationname = StringUtil.toStr(request.getParameter("blockstationname").trim());
		String wellCode = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("wellCode")));
		String orgid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("org_id")));
		
		//查询常规油井 井名
		try {
			gasList = gasWellService.searchGasName(gaswellname);
		} catch (Exception e) {
			e.printStackTrace();
			outCode = "-10510";
			//气井信息名称获取失败
		}
		if(gasList !=null && gasList.size()>0){
			//气井名称已存在，请换其他气井名称
			outCode="-10511";
		}if("1".equals(outCode)){
			//所属注气站
			List<Object[]>  maniList = null;
			try {
				maniList = gasWellService.searchGasation(gasstationname);
			} catch (Exception e) {
				e.printStackTrace();
				//所属气站信息获取失败
				outCode = "-11110";
			}
			if(maniList !=null && maniList.size()>0){
			pid = maniList.get(0)[0].toString();
				
			}else{
				outCode = "-11110";
			}
			if("1".equals(outCode)){
				if(wellCode!=null && !"".equals(wellCode)){
					String sql = "select * from pc_cd_gas_well_t s left join pc_cd_org_t o on s.org_id=o.org_id where 1=1";
					orgList =  orgService.searchOrg(wellCode,orgid, sql);
					if(orgList.size()>0){
						obj = CommonsUtil.getRrturnJson("-11220", "井号编码已存在","", null, null);
						out.print(obj);
						return null;
					}
				}
			User sessionuser = (User) session.getAttribute("userInfo");
			gas.setWellName(gaswellname);
			
			gas.setBewellName(StringUtil.toStr(request.getParameter("bewell_name").trim()));
			gas.setWellName(gaswellname);
			String wellAreaSelf = StringUtil.toStr(request.getParameter("wellAreaSelf").trim());
			gas.setQkid(wellAreaSelf);
			gas.setDyear(StringUtil.toStr(request.getParameter("dyear").trim()));
			gas.setRemark(StringUtil.toStr(request.getParameter("remark").trim()));
			gas.setRlastOper( sessionuser.getOperName());
			gas.setRlastOdate(new Date());
			 PcCdOrgT org = new PcCdOrgT();
			org.setCode(StringUtil.toStr(request.getParameter("wellCode").trim()));
			String switchInFlag = StringUtil.toStr(request.getParameter("jrbz"));
			org.setSwitchInFlag(switchInFlag);
			 //投产日期
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
				//serverlist = sagdService.getServerNode(StringUtil.toStr(request.getParameter("ljjd_s").trim()));
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
			String statusvalues = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("status_value")));
			
			if (!"".equals(statusvalues)) {
				try {
					statusvalues = commonService.getStatusValueINT(statusvalues);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			org.setStatusValue(statusvalues);
			
			 org.setStructurename(gaswellname);
			 org.setPid(pid);
			 org.setStructuretype((byte)9);
			 org.setPcCdGasWellT(gas);
			 gas.setPcCdOrgT(org);
				
				try {
					addflg = gasWellService.addGas(gas); 
				} catch (Exception e) {
					e.printStackTrace();
					//气井添加失败
					outCode="-10512";
				}
			
		}
			}
		if("1".equals(outCode) && addflg ==true){
			
			//添加系统LOG
			try {
				User user1 = (User) session.getAttribute("userInfo");
				PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "2", "气井基础信息", gas.getGasWellid());
				logService.addLog(log);
			} catch (Exception e) {
				e.printStackTrace();
				outCode = "-10003";
			}
		}
		
		out.print(outCode);
		return null;
	}
	
	public String updateGasWell() throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		boolean  addflg = true;
		List<PcCdGasWellT> gasList = null;
		List<Object[]> orgList = null;
		JSONObject obj = new JSONObject();
		PcCdGasWellT gas = null;
		String pid="";
		//所属气站
		String gasstationname = StringUtil.toStr(request.getParameter("blockstationname").trim());
		//查询 井名
		String gasname = StringUtil.toStr(request.getParameter("well_name").trim());
		String  gasid = StringUtil.toStr(request.getParameter("gas_wellid").trim());
		String wellCode = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("wellCode")));
		String orgid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("org_id")));
		try {
			gasList = gasWellService.searchGasById("",gasname);
		
		} catch (Exception e) {
			e.printStackTrace();
			//气井名称获取失败
			outCode="-10510";
		}
		if(gasList !=null && gasList.size()==1){
			if(!gasid.equals(gasList.get(0).getGasWellid())){
				//油井同条进行修改
				outCode="-10511";
			}
		}
		if(gasList !=null && gasList.size()>1){
			//井名称已存在多条，不容许修改
			outCode="-10511";
		}else{
			try {
				gasList = new ArrayList<PcCdGasWellT>();
				gasList  = gasWellService.searchGasById(gasid,"");
				gas= gasList.get(0);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}if("1".equals(outCode)){
			//所属注气站
			List<Object[]>  maniList = null;
			try {
				maniList = gasWellService.searchGasation(gasstationname);
				
			} catch (Exception e) {
				e.printStackTrace();
				//所属气站信息获取失败
				outCode = "-11110";
			}
			if(maniList !=null && maniList.size()>0){
				pid = maniList.get(0)[0].toString();
				
			}else{
				outCode = "-11110";
			}
			if("1".equals(outCode)){
				if(wellCode!=null && !"".equals(wellCode)){
					String sql = "select * from pc_cd_gas_well_t s left join pc_cd_org_t o on s.org_id=o.org_id where 1=1";
					orgList =  orgService.searchOrg(wellCode,orgid, sql);
					if(orgList.size()>0){
						obj = CommonsUtil.getRrturnJson("-11220", "井号编码已存在","", null, null);
						out.print(obj);
						return null;
					}
				}
			User sessionuser = (User) session.getAttribute("userInfo");
			gas.setWellName(gasname);
			
			gas.setBewellName(StringUtil.toStr(request.getParameter("bewell_name").trim()));
			gas.setRemark(StringUtil.toStr(request.getParameter("remark").trim()));
			gas.setRlastOper( sessionuser.getOperName());
			gas.setRlastOdate(new Date());
			 PcCdOrgT org = null;
			 org = gas.getPcCdOrgT();
			 gas.setDyear(StringUtil.toStr(request.getParameter("dyear").trim()));
			 String wellAreaSelf = StringUtil.toStr(request.getParameter("wellAreaSelf").trim());
			 gas.setQkid(wellAreaSelf);
			org.setCode(StringUtil.toStr(request.getParameter("wellCode").trim()));
			String scadaNode = StringUtil.toStr(request.getParameter("ljjd_s").trim());
			org.setScadaNode(scadaNode);
			String switchInFlag = StringUtil.toStr(request.getParameter("jrbz").trim());
			org.setSwitchInFlag(switchInFlag);
			 //投产日期
			String commissioningDate = StringUtil.toStr(request.getParameter("commissioning_date").trim());
			if(commissioningDate != null && !"".equals(commissioningDate)){
				org.setCommissioningDate(DateBean.getStrDate(commissioningDate));
			}else{
				org.setCommissioningDate(null);
			}
			String statusvalues = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("status_value")));
			
			if (!"".equals(statusvalues)) {
				try {
					statusvalues = commonService.getStatusValueINT(statusvalues);
					org.setStatusValue(statusvalues);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else{
				org.setStatusValue(null);
			}
			
			org.setPid(gas.getGasWellid());
			org.setPid(pid);
			org.setStructuretype((byte) 9);
			org.setStructurename(gasname);
			org.setPcCdGasWellT(gas);
			gas.setPcCdOrgT(org);
					
					try {
						addflg = gasWellService.updateGas(gas); 
					} catch (Exception e) {
						e.printStackTrace();
						//所属气站更新失败
						outCode="-10503";
					}
	
				}
//		if("1".equals(outCode) && addflg == true){
//			
//			//添加系统LOG
//			try {
//				User user1 = (User) session.getAttribute("userInfo");
//				PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "2", "气井基础信息", gas.setGasWellid());
//				logService.addLog(log);
//			} catch (Exception e) {
//				e.printStackTrace();
//				outCode = "-10003";
//			}
//			
//		}
		}
		out.print(outCode);
		return null;
	}
	public  String onExport()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String oilstationname1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("oilstationname1")));
		String areablock1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("areablock")));
		String blockstationname1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("blockstationname1")));
		String rulewellname1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("rulewellname1")));
		String jrbz1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("jrbz1")));
		String totalNum = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("totalNum")));
		String dyearC = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("dyearC")));

		HashMap<String,Object> dataMap = null;
		try {
			dataMap = gasWellService.searchOnExport(oilstationname1,areablock1,blockstationname1,rulewellname1,jrbz1,dyearC,totalNum);
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
			java.io.ByteArrayOutputStream baos = DynamicDataExportUtil.basicDataExporReport(dataLsit,templetFilePath,"气井基础信息");
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
