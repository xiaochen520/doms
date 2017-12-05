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

import com.echo.dto.PcCdAreaInfoT;
import com.echo.dto.PcCdOildrillingStationT;
import com.echo.dto.PcCdOrgT;
import com.echo.dto.PcCdWaterInjectiontopryT;
import com.echo.dto.PcRdLoginfoT;
import com.echo.dto.User;
import com.echo.service.AreaInfoService;
import com.echo.service.CommonService;
import com.echo.service.LogService;
import com.echo.service.OilDrillingService;
import com.echo.service.WaterInjectionService;
import com.echo.util.AuthorityUtil;
import com.echo.util.CommonsUtil;
import com.echo.util.DateBean;
import com.echo.util.DynamicDataExportUtil;
import com.echo.util.PageVariableUtil;
import com.echo.util.StringUtil;

public class WaterInjectionTopryAction {
	private WaterInjectionService waterInjectionService;
	OilDrillingService oilDriService;
	private LogService logService;
	private CommonService commonService;
	private AreaInfoService areaInfoService;
	private InputStream excelFile = null;
	
	public InputStream getExcelFile() {
		return excelFile;
	}


	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}

	
	public void setAreaInfoService(AreaInfoService areaInfoService) {
		this.areaInfoService = areaInfoService;
	}


	public void setOilDriService(OilDrillingService oilDriService) {
		this.oilDriService = oilDriService;
	}


	public void setWaterInjectionService(WaterInjectionService waterInjectionService) {
		this.waterInjectionService = waterInjectionService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	public String getFileName() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd ");
		String downloadFileName = (sf.format(new Date()).toString())+ "注水撬基础信息.xls";
		try {
			downloadFileName = new String(downloadFileName.getBytes(),"ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return downloadFileName;
	}
	HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
	public String searchwaterIT() throws IOException {
		
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String stationNumber = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("oilstationname1")));
		String boilersName = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("areablock1")));
		String areablock = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("blockstationname1")));
		String oilname = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("rulewellname1")));
		String JRBZ1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("JRBZ1")));
		String dyearC = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("dyearC")));
		String groupTeam = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("groupTeam")));
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
		
 		JSONObject jsonobj = waterInjectionService.searchwaterIT(stationNumber,boilersName,areablock,oilname,JRBZ1,dyearC,groupTeam,pageNo,sort,order,rowsPerpage);
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
			gridJson = AuthorityUtil.getGridJson("注水撬基础信息", user, PageVariableUtil.WATER_INJECTIONTOPRID_PAGE_GRID);
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
		String stationId = StringUtil.toStr(request.getParameter("water_injectiontoprid"));
		String orgId = StringUtil.toStr(request.getParameter("org_id"));
		boolean deleteflg = false;
		JSONObject obj = new JSONObject();
		try {
			deleteflg = waterInjectionService.removeStationInfo(stationId,orgId);
		} catch (Exception e) {
			String errmsg = e.getCause().toString();
			//ORA-02292: 违反完整约束条件 (NEWPORTAL.FK58_PC_RPD_WELL_SAGDD_T) - 已找到子记录
			if(!"".equals(errmsg) && errmsg.indexOf("ORA-02292") != -1 && errmsg.indexOf("FK58_PC_RPD_WELL_SAGDD_T") != -1){
				obj = CommonsUtil.getRrturnJson("","注水撬基础信息删除失败-注水撬日报中存在子记录" ,"", null, null);
			}else if(!"".equals(errmsg) && errmsg.indexOf("ORA-02292") != -1 && errmsg.indexOf("FK_PC_RPD_W_FK52_PC_R_PC_CD_WE") != -1){
				obj = CommonsUtil.getRrturnJson("","注水撬基础信息删除失败-注水撬班报中存在子记录" ,"", null, null);
			} else{
				obj = CommonsUtil.getRrturnJson("",errmsg ,"", null, null);
			}
			e.printStackTrace();
		}
		
		if(deleteflg){
			
			//添加系统LOG
			try {
				User user1 = (User) session.getAttribute("userInfo");
				PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "2", "注水撬基础信息", stationId);
				logService.addLog(log);
			} catch (Exception e) {
				e.printStackTrace();
				obj = CommonsUtil.getRrturnJson("-10003", "", "",null, null);
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
			jsonArr = waterInjectionService.queryWellInfo(boilername);
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
	
	
	public String addOrUpdateWIT() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		String wellid = "";
		List<PcCdWaterInjectiontopryT> injectiontoprys = null;
		
		PcCdWaterInjectiontopryT injection = null;
		PcCdOrgT org = null;
		//List<PcCdOildrillingStationT> stations = null;
		String pid = "";
		boolean flag = true;
		User user1 = (User) session.getAttribute("userInfo");
		
		if(request.getParameter("id") != null && !"".equals(request.getParameter("id").trim())){
			wellid = StringUtil.toStr(request.getParameter("id"));
		}
		String wellname = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("water_injectiontopr_name")));
		
		try {
			injectiontoprys = waterInjectionService.getInjectiontoprys("", wellname);
		} catch (Exception e) {
			e.printStackTrace();
			//-10701  注水撬信息获取错误
			outCode = "-10701";
		}
		

		//修改
		if(wellid != null && !"".equals(wellid)){
			
			if(injectiontoprys != null && injectiontoprys.size()>0){
				if(!wellid.equals(injectiontoprys.get(0).getWaterInjectiontoprid())){
					//-10706  注水撬名称已存在，请使用其他注水撬名称
					outCode = "-10706";
				}
			}
			
			if("1".equals(outCode)){
				try {
					injectiontoprys = waterInjectionService.getInjectiontoprys(wellid, "");
				} catch (Exception e) {
					e.printStackTrace();
					//-10701  注水撬信息获取错误
					outCode = "-10701";
				}
				
				if(injectiontoprys != null && injectiontoprys.size()>0){
					injection = injectiontoprys.get(0);
					org = injection.getPcCdOrgT();
					
				}else{
					//-10708  注水撬ID未识别，请重新选择注水撬
					outCode = "-10708";
				}
				
			}
		//添加	
		}else{
			if(injectiontoprys != null && injectiontoprys.size()>0){
				//-10706  注水撬名称已存在，请使用其他注水撬名称
				outCode = "-10706";
			}
			injection = new PcCdWaterInjectiontopryT();
			org = new PcCdOrgT();
		}
		if("1".equals(outCode)){
			

			String oilstation = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("oilstation")));
//			if(oilstation != null && !"".equals(oilstation)){
//				 pid = waterInjectionService.searchTeamOrd(oilstation);
//			}
//			if(oilstation != null && !"".equals(oilstation)){
//				try {
//					stations = oilDriService.serachOild(oilstation, "");
//				} catch (Exception e) {
//					e.printStackTrace();
//					//-11314	采油站信息获取失败
//					outCode = "-11314";
//				}
//				if("1".equals(outCode)){
//					if(stations != null && stations.size()>0){
//						pid = stations.get(0).getOildrillingStationid();
//					}else{
//						//-11316	未识别采油站ID请重新选择
//						outCode = "-11316";
//					}
//					
//				}
//				
//			}else{
//				//-11315	未获取采油站ID请重新选择
//				outCode = "-11315";
//				
//			}
			
			if("1".equals(outCode)){
				String areaid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("areaid")));
				//pid = stations.get(0).getOildrillingStationid();
				List<PcCdAreaInfoT> area = null;
				try {
					area = areaInfoService.searchByName(areaid,"");
				} catch (Exception e) {
					e.printStackTrace();
					//区块名称获取失败
					outCode="-11210";
				}
				
			if(area != null && area.size()>0){
				injection.setQkid(areaid);
				//org.setStructuretype((byte)3);
				String statusvalues = "";
				try {
					String status_value = StringUtil.toStr(request.getParameter("status_value").trim());
					if(status_value != null && !"".equals(status_value)){
						statusvalues = commonService.getStatusValueINT(status_value);
					}
					
				} catch (Exception e) {
					e.printStackTrace();
					//-10010	建设状态获取失败
					outCode = "-10010";
					
				}
			
			if("1".equals(outCode)){
				injection.setWaterInjectiontoprName(wellname);
				injection.setQkid(areaid);
				String commissioning_date = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("commissioning_date")));
				if(commissioning_date != null && !"".equals(commissioning_date)){
					Date da = DateBean.getStrDate(commissioning_date);
					org.setCommissioningDate(da);
					
				}else{
					org.setCommissioningDate(null);
				}
//				String scrapped_date = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("scrapped_date")));
//				if(scrapped_date != null && !"".equals(scrapped_date)){
//					
//					injection.setScrappedDate(DateBean.getStrDate(scrapped_date));
//				}
				
				injection.setRemark(StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("remark"))));
				org.setRemark(StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("remark"))));
				injection.setRlastOper(user1.getOperName());
				injection.setRlastOdate(new Date());
				String code =  StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("code").trim()));
				org.setCode(code);
				String scadaNode  = StringUtil.toStr(request.getParameter("ljjd_s").trim());
				org.setScadaNode(scadaNode);
				String switchInFlag = StringUtil.toStr(request.getParameter("jrbz").trim());
				org.setSwitchInFlag(switchInFlag);
				org.setPid(oilstation);
				org.setStructuretype((byte) 13);
				org.setStatusValue(statusvalues);
				org.setStructurename(wellname);
				org.setPcCdWaterInjectiontopryT(injection);
				injection.setPcCdOrgT(org);
				String dyear = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("dyear")));
				injection.setDyear(dyear);
				if("1".equals(outCode)){

					//修改
					if(wellid != null && !"".equals(wellid)){
						try {
							flag = waterInjectionService.updateINT(injection);
						} catch (Exception e) {
							e.printStackTrace();
							
							//-10703  注水撬修改失败
							outCode = "-10703";
						}
					}else{
						try {
							flag = waterInjectionService.addINT(injection);
						} catch (Exception e) {
							e.printStackTrace();
							//-10702  注水撬添加失败
							outCode = "-10702";
						}
					}
				
				}
				}
				
			}
			
			}
		}
		
		if("1".equals(outCode) && flag == true){
			if(wellid != null && !"".equals(wellid)){
				//添加系统LOG
				try {
					
					PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "3", "注水撬基础信息", injection.getWaterInjectiontoprid());
					logService.addLog(log);
				} catch (Exception e) {
					e.printStackTrace();
					outCode = "-10002";
				}
			}else{
				//添加系统LOG
				try {
					PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "1", "注水撬基础信息", injection.getWaterInjectiontoprid());
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
	
	public  String onExport()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String stationNumber = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("oilstationname1")));
		String areablock1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("areablock1")));
		String rulewellname1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("rulewellname1")));
		String JRBZ1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("JRBZ1")));
		String dyearC = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("dyearC")));
		String totalNum = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("totalNum")));
		String groupTeam = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("groupTeam")));
		HashMap<String,Object> dataMap = null;
		try {
			dataMap = waterInjectionService.searchOnExport(stationNumber,areablock1,rulewellname1,JRBZ1,dyearC,groupTeam,totalNum);
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
			java.io.ByteArrayOutputStream baos = DynamicDataExportUtil.basicDataExporReport(dataLsit,templetFilePath,"注水撬基础信息");
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
