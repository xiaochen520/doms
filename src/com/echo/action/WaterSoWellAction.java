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
import com.echo.dto.PcRdLoginfoT;
import com.echo.dto.User;
import com.echo.service.CommonService;
import com.echo.service.LogService;
import com.echo.service.SagdService;
import com.echo.service.WaterSoWellService;
import com.echo.util.AuthorityUtil;
import com.echo.util.CommonsUtil;
import com.echo.util.DateBean;
import com.echo.util.DynamicDataExportUtil;
import com.echo.util.PageVariableUtil;
import com.echo.util.StringUtil;

public class WaterSoWellAction {
	private WaterSoWellService waterSoWellService;
	private CommonService commonService;
	private SagdService sagdService;
	private InputStream excelFile = null;
	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}
	public void setWaterSoWellService(WaterSoWellService waterSoWellService) {
		this.waterSoWellService = waterSoWellService;
	}
	private LogService logService;

	public LogService getLogService() {
		return logService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	public void setSagdService(SagdService sagdService) {
		this.sagdService = sagdService;
	}
	
	public InputStream getExcelFile() {
		return excelFile;
	}
	public void setExcelFile(InputStream excelFile) {
		this.excelFile = excelFile;
	}
	public String getFileName() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd ");
		String downloadFileName = (sf.format(new Date()).toString())+ "水源井基础信息.xls";
		try {
			downloadFileName = new String(downloadFileName.getBytes(),"ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return downloadFileName;
	}
	HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话

	
	public String searchWaterSoWell() throws IOException {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String stationNumber = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("combination1")));
		String boilersName = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("wellnum1")));
		String areablock = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("watersowell1")));
		String ghid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("ghname")));
		String jrbz = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("jrbz")));
		String totalNum = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("totalNum")));
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
// 		if("".equals(stationNumber) && "".equals(boilersName)  && "".equals(areablock) && "".equals(ghid)){
//			out.println("");
//			return null;
//		}
 		HashMap<String,Object> dataMap = null;
 		//JSONObject jsonobj=null;
		try {
			dataMap = waterSoWellService.searchWaterSoWell(totalNum,jrbz,stationNumber,boilersName,areablock,ghid,dyearC,pageNo,sort,order,rowsPerpage);
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
					String templetFilePath = request.getSession().getServletContext().getRealPath("/") + "exceltemplet\\ExportRBWH\\关井日报模板.xls";
//					将用户对象列表输出到workbook的流中，ExcelUtil类进行了封装，后续分析！
					java.io.ByteArrayOutputStream baos = DynamicDataExportUtil.basicDataExporReport(dataLsit,templetFilePath,"关井日报");
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
			gridJson = AuthorityUtil.getGridJson("水源井基础信息", user, PageVariableUtil.WATERSOWELL_PAGE_GRID);
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
	
	public String removeWaterSoWell() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		String stationId = StringUtil.toStr(request.getParameter("water_source_wellid"));
		String orgId = StringUtil.toStr(request.getParameter("org_id"));
		boolean deleteflg = false;
		JSONObject obj = new JSONObject();
		try {
			deleteflg = waterSoWellService.removeStationInfo(stationId,orgId);
		} catch (Exception e) {
			String errmsg = e.getCause().toString();
			//ORA-02292: 违反完整约束条件 (NEWPORTAL.FK58_PC_RPD_WELL_SAGDD_T) - 已找到子记录
			if(!"".equals(errmsg) && errmsg.indexOf("ORA-02292") != -1 && errmsg.indexOf("FK58_PC_RPD_WELL_SAGDD_T") != -1){
				obj = CommonsUtil.getRrturnJson("","水源井基础信息删除失败-水源井日报中存在子记录" , "",null, null);
			}else if(!"".equals(errmsg) && errmsg.indexOf("ORA-02292") != -1 && errmsg.indexOf("FK_PC_RPD_W_FK52_PC_R_PC_CD_WE") != -1){
				obj = CommonsUtil.getRrturnJson("","水源井基础信息删除失败-水源井班报中存在子记录" ,"", null, null);
			} else{
				obj = CommonsUtil.getRrturnJson("",errmsg ,"", null, null);
			}
			e.printStackTrace();
		}
		
		if(deleteflg){
			
			//添加系统LOG
			try {
				User user1 = (User) session.getAttribute("userInfo");
				PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "2", "水源井基础信息", stationId);
				logService.addLog(log);
			} catch (Exception e) {
				e.printStackTrace();
				obj = CommonsUtil.getRrturnJson("-10003", "","", null, null);
			}
			
			
		}
		out.print(obj);
		return null;
	}
	public String queryCombinationInfo() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String arg = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("arg")));
		
		JSONArray jsonArr = null;
		try {
			jsonArr = waterSoWellService.queryCombinationInfo(arg);
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
	
	public String queryWellInfo() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String orgid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("stationid")));
		JSONArray jsonArr = null;
		try {
			jsonArr = waterSoWellService.queryWellInfo(orgid);
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
	
	public String queryWaterSoWell() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String wellId = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("orgid")));
		JSONArray jsonArr = null;
		try {
			jsonArr = waterSoWellService.queryWaterSoWell(wellId);
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
	
	
	public String getWaterStation() throws Exception {
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONArray jsonArr = null;
		JSONObject obj = null;
		List<Object[]>  objlist = null;
		try {
			objlist = commonService.getStationList("水源井基础信息",11);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(objlist != null && objlist.size()>0){
			jsonArr = new JSONArray();
			for(Object[] zzzobj:objlist){
				
				obj = new JSONObject();
				obj.put("id", zzzobj[0]);
				obj.put("text", zzzobj[3]);
				jsonArr.add(obj);
			}
			
		}
		
		if(jsonArr != null){
			out.println(jsonArr);
		}else{
			out.println("");
		}
		return null;
	}
	
	
	public String addorUpdateWaterSoWell() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";

		List<PcCdWaterSourceWellT> wellList = null;
		
		PcCdWaterSourceWellT waterWell = null;
		List<Object[]> orgList = null;
		String id = "";
		String pid = "";
		String well_name = "";
		String blockstationname = "";
		boolean addflg = true;
		User sessionuser = (User) session.getAttribute("userInfo");
		if(request.getParameter("id") != null && !"".equals(request.getParameter("id"))){
			id = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("id")));
		}
		well_name = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("well_name")));
//		try {
//			wellList = waterSoWellService.searchWaterSoWells("", well_name);
//		} catch (Exception e) {
//			// -10601 水源井信息获取失败
//			e.printStackTrace();
//			outCode = "-10601";
//		}
		
		//修改
		if(id != null && !"".equals(id)){
			try {
				wellList = waterSoWellService.searchWaterSoWells(id, "");
			} catch (Exception e) {
				// -10601 水源井信息获取失败
				e.printStackTrace();
				outCode = "-10601";
			}
			//-10607 水源井名已存在请使用其他水源井名
			if(wellList != null && wellList.size()>0){
				if(!id.equals(String.valueOf(wellList.get(0).getWaterSourceWellid()))){
					outCode = "-10607";
				}
				
			}
			waterWell = wellList.get(0);
		//添加	
		}else{
			//-10607 水源井名已存在请使用其他水源井名
			try {
				wellList = waterSoWellService.searchWaterSoWells("", well_name);
			} catch (Exception e) {
				// -10601 水源井信息获取失败
				e.printStackTrace();
				outCode = "-10601";
			}
			if(wellList != null && wellList.size()>0){
				outCode = "-10607";
			}
			waterWell = new PcCdWaterSourceWellT();
		}
		
		if("1".equals(outCode)){
			//获取父id注转站
			blockstationname = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("wellnum")));
			if(blockstationname != null && !"".equals(blockstationname)){
				try {
					orgList = waterSoWellService.searchOrg(blockstationname, "11");
				} catch (Exception e) {
					e.printStackTrace();
					//-11108 所属处理站信息获取失败
					outCode = "-11108";
				}
				
			}else{
				//-11109 所属处理站信息未识别
				outCode = "-11109";
			}
			
		}
		if("1".equals(outCode) && orgList != null && orgList.size()>0){
			
			pid = orgList.get(0)[0].toString();
			waterWell.setWellName(well_name);
			
			waterWell.setBewellName(StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("bewell_name"))));
			waterWell.setRemark(StringUtil.toStr(request.getParameter("remark").trim()));
			waterWell.setDyear(StringUtil.toStr(request.getParameter("dyear").trim()));
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
				org.setStatusValue(status_value);
				String commissioningDate = StringUtil.toStr(request.getParameter("commissioning_date").trim());
				if(commissioningDate != null && !"".equals(commissioningDate)){
					org.setCommissioningDate(DateBean.getStrDate(commissioningDate));
				}else{
					org.setCommissioningDate(null);
					}
				PcCdServerNodeT ser = new PcCdServerNodeT(); //服务器逻辑表
				List<PcCdServerNodeT> serverlist = null;
				String scadaNode =StringUtil.toStr(request.getParameter("ljjd_s").trim());
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
				String switchInFlag  =  StringUtil.toStr(request.getParameter("jrbz").trim());
				org.setSwitchInFlag(switchInFlag);
				org.setCode(StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("well_cole"))));
				//waterWell.setPcCdServerNodeT(ser);
				org.setStructuretype((byte) 9);
				org.setPid(pid);
				org.setPcCdWaterSourceWellT(waterWell);
				waterWell.setPcCdOrgT(org);
				
				try {
					if(id != null && !"".equals(id)){
						addflg = waterSoWellService.updatewell(waterWell);
					}else{
						addflg = waterSoWellService.addwell(waterWell);
					}
				} catch (Exception e) {
					e.printStackTrace();
					if(id != null && !"".equals(id)){
//						-10603 水源井信息修改失败
						outCode = "-10603";
					}else{
//						-10602 水源井信息添加失败
						outCode = "-10602";
					}
				}
			}
			
			
		}
		
		if("1".equals(outCode) && addflg == true){
			if(id != null && !"".equals(id)){
				//添加系统LOG
				try {
					PcRdLoginfoT log = CommonsUtil.getLogInfo(sessionuser, "3", "水源井基础信息", waterWell.getWaterSourceWellid());
					logService.addLog(log);
				} catch (Exception e) {
					e.printStackTrace();
					outCode = "-10002";
				}
			}else{
				//添加系统LOG
				try {
					PcRdLoginfoT log = CommonsUtil.getLogInfo(sessionuser, "1", "水源井基础信息", waterWell.getWaterSourceWellid());
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
	
	public String cascadeInit() throws Exception {
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONObject jsonArr = null;
		try {
			jsonArr = waterSoWellService.cascadeInit();
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
}
