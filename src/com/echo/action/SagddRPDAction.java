package com.echo.action;

import java.io.ByteArrayInputStream;
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

import com.echo.dto.PcRpdWellSagddT;
import com.echo.dto.User;
import com.echo.service.LogService;
import com.echo.service.SagdService;
import com.echo.util.AuthorityUtil;
import com.echo.util.CommonsUtil;
import com.echo.util.DynamicDataExportUtil;
import com.echo.util.PageVariableUtil;
import com.echo.util.PropertyUtil;
import com.echo.util.StringUtil;

public class SagddRPDAction {
	private static final String String = null;
	private SagdService sagdService;
	private InputStream excelFile = null;
	
	public void setSagdService(SagdService sagdService) {
		this.sagdService = sagdService;
	}
	@SuppressWarnings("unused")
	private LogService logService;

	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	
	public String getFileName() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd ");
		String downloadFileName = (sf.format(new Date()).toString())+ "SAGD日报数据.xls";
		try {
			downloadFileName = new String(downloadFileName.getBytes(),"ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return downloadFileName;
	}

	public InputStream getExcelFile() {
		return excelFile;
	}
	HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
	@SuppressWarnings("unchecked")
	
	public String searchSagdRPD() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String oilNumber = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("oilationname")));
		String ghname = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("ghmc")));
		String well = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("wellnum")));	
		String area = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("areablock")));	
		String bzhf = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("BZHF")));
		String startDate = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("startDate")));
		String endDate = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("endDate")));
		String totalNum = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("totalNum")));
		String search = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("search")));
		
		
		System.out.println("ffffffffffffffffffffffffffff"+area);
		System.out.println("ffffffffffffffffffffffffffff"+ghname);
		System.out.println("ffffffffffffffffffffffffffff"+oilNumber);
		System.out.println("ffffffffffffffffffffffffffff"+bzhf);
		
		
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
		if("".equals(oilNumber) && "".equals(ghname) && "".equals(well) && "".equals(area) && "".equals(bzhf)
				 && "".equals(startDate) && "".equals(endDate) && "".equals(totalNum)){
			out.println("");
			return null;
		}
		HashMap<String,Object> dataMap = null;
		try {
			
			dataMap = sagdService.searchSagdRPD(oilNumber,ghname,well,area,startDate,endDate,pageNo,sort,order,rowsPerpage,totalNum,search,bzhf);
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
			String templetFilePath = request.getSession().getServletContext().getRealPath("/") + "exceltemplet\\日报数据.xls";
//			将用户对象列表输出到workbook的流中，ExcelUtil类进行了封装，后续分析！
			java.io.ByteArrayOutputStream baos = DynamicDataExportUtil.dailyDataExporReport(dataLsit,templetFilePath,"SAGD井日报");
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
	
	//sagd井日报生成
	@SuppressWarnings("unchecked")
	public String searchSagdRPD1() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String oilNumber = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("oilationname")));
		String ghname = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("ghmc")));
		String well = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("wellnum")));
		String area = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("areablock")));
		String bzhf = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("BZHF")));		
		String startDate = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("startDate")));
		String endDate = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("endDate")));
		String totalNum = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("totalNum")));
		String search = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("search")));
		
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
		if( "".equals(oilNumber) && "".equals(ghname) && "".equals(well) && "".equals(area) && "".equals(bzhf)
				 && "".equals(startDate) && "".equals(endDate) && "".equals(totalNum)){
			out.println("");
			return null;
		}
		HashMap<String,Object> dataMap = null;
		try {
			
			dataMap = sagdService.searchSagdRPD1(oilNumber,ghname,well,area,startDate,endDate,pageNo,sort,order,rowsPerpage,totalNum,search,bzhf);
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
			String templetFilePath = request.getSession().getServletContext().getRealPath("/") + "exceltemplet\\日报数据.xls";
//			将用户对象列表输出到workbook的流中，ExcelUtil类进行了封装，后续分析！
			java.io.ByteArrayOutputStream baos = DynamicDataExportUtil.dailyDataExporReport(dataLsit,templetFilePath,"SAGD井日报");
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
	
	
	
	
	
	//SAGD日报动态曲线图
	public String getline()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		
		String sagdid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("sagdid")));
		String prvarid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("prvarid")));
		
		String startDate = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("startDate")));
		String endDate = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("endDate")));
		
		List<Object[]> pointline = null;
		try {
			pointline = sagdService.searchSagdLine(sagdid,prvarid,startDate,endDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
//		JSONObject gridJson = null;
		JSONArray arr = null;
		if(pointline != null && pointline.size()>0){
			arr = new JSONArray();
			for(Object[] obj :pointline){
//				gridJson.put(obj[1], obj[0]);
//				arr.add(gridJson);
//				gridJson = new JSONObject();
//				gridJson.put("TUXINGS", pointline);
				arr.add(obj);
			}
			
		}
		 
		if("1".equals(outCode)){
			out.print(arr);
		}else{
			out.print(outCode);
		}
		return null;
	}
	
	//SAGD日报数据曲线图
	public String getline2()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		
		String sagdid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("sagdid")));
		String prvarid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("prvarid")));
		
		prvarid = prvarid.replaceAll(";", ",");
		String startDate = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("startDate")));
		String endDate = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("endDate")));
		
		List<Object[]> pointline = null;
		try {
			pointline = sagdService.searchSagdLine2(sagdid,prvarid,startDate,endDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
//		JSONObject gridJson = null;
		JSONArray arr = null;
		if(pointline != null && pointline.size()>0){
			arr = new JSONArray();
			for(Object[] obj :pointline){
				if(obj!=null)
					arr.add(obj);
			}
			
		}
		 
		if("1".equals(outCode)){
			out.print(arr);
		}else{
			out.print(outCode);
		}
		return null;
	}
	
	//sagd日报详细信息页面初始化
	public String SagdRPDviewpageInit()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		//String sagdid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("sagdid")));
		String sagddid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("sagddid")));
		String[] cloumnsName = {"JHMC","WELL_CODE","OILSTATIONNAME","QKMC","BLOCKSTATIONNAME","MANIFOLD","BRANCHING_NAME",
				"REPORT_DATE","P_PRESSURE_PRESS","P_PRESSURE_TEMP","P_LOOPED_PRESS","P_LOOPED_TEMP","P_DRIVEPIPE_PRESS","I_PRESSURE_PRESS","I_PRESSURE_TEMP",
				"I_LOOPED_PRESS","I_LOOPED_TEMP","I_DRIVEPIPE_PRESS","NO1STEAM_TEMP","NO1STEAM_PRESS","NO1STEAM_DIFP","NO1INSTANT_FLOW","LOWSUM_FLOW1",
				"NO1INSTANT_DRYNESS","NO1CONTROL_STATUS","NO1CONTROL_APERTURE","NO1FLOW_SET","NO2STEAM_TEMP","NO2STEAM_PRESS","NO2STEAM_DIFP","NO2INSTANT_FLOW",
				"LOWSUM_FLOW2","NO2INSTANT_DRYNESS","NO2CONTROL_STATUS","NO2CONTROL_APERTURE","NO2FLOW_SET","P_PRESS1","P_PRESS2","P_TEMP1","P_TEMP2","P_TEMP3",
				"P_TEMP4","P_TEMP5","P_TEMP6","P_TEMP7","P_TEMP8","P_TEMP9","P_TEMP10","P_TEMP11","P_TEMP12",
				"NOW_LOAD","MOTOR_PRESS_A","MOTOR_TEMP_A","MOTOR_PRESS_B","MOTOR_TEMP_B","MOTOR_PRESS_C","MOTOR_TEMP_C","CLIQUE_CONTROL_APERTURE2","ACTIVE_ENERGY","IDLE_ENERGY",
				"POWER_FACTOR","JIG_FREQUENCY","STROKE","PUMPING_RUNNING_STATE","MOTORSTATUS","CONVERSION_PRESS","NOW_FREQUENCY","CONVERSION_ELECTRICITY","MOTOR_MODE","CLIQUE_CONTROL_APERTURE1",
				"RUNTIME","DAILY_OUTPUT","DAILY_POWER_CONSUMPTIVE","DAILY_CUMULATIVE_STEAM1","DAILY_CUMULATIVE_STEAM2","REMARK"};
		List<Object[]> datas = new ArrayList<Object[]>();
		if(!"".equals(sagddid)){
			try {
				datas  = sagdService.searchSagdRPDview(sagddid,cloumnsName);
			} catch (Exception e) {
				e.printStackTrace();
				outCode = "-10004";
			}
		//数据错误
		}else{
			
		}
		JSONObject gridJson = null;
		
//		JSONArray arr = new JSONArray();
		if("1".equals(outCode) && datas != null && datas.size()>0){
			gridJson = new JSONObject();
			for(int i = 0; i <cloumnsName.length;i++){
				
				if(datas.get(0)[i] != null ){
					if("NO1CONTROL_STATUS".equals(cloumnsName[i])){
						if("0".equals(datas.get(0)[i].toString())){
							gridJson.put(cloumnsName[i],"手动");
						}else{
							gridJson.put(cloumnsName[i],"自动");
						}
					}else if("NO2CONTROL_STATUS".equals(cloumnsName[i])){
						if("0".equals(datas.get(0)[i].toString())){
							gridJson.put(cloumnsName[i],"手动");
						}else{
							gridJson.put(cloumnsName[i],"自动");
						}
					}else if("PUMPING_RUNNING_STATE".equals(cloumnsName[i])){
						if("1".equals(datas.get(0)[i].toString())){
							gridJson.put(cloumnsName[i],"指示");
						}else if("2".equals(datas.get(0)[i].toString())){
							gridJson.put(cloumnsName[i],"空闲");
						}else if("4".equals(datas.get(0)[i].toString())){
							gridJson.put(cloumnsName[i],"停机");
						}else if("8".equals(datas.get(0)[i].toString())){
							gridJson.put(cloumnsName[i],"死停");
						}
					}else if("MOTORSTATUS".equals(cloumnsName[i])){
						if("0".equals(datas.get(0)[i].toString())){
							gridJson.put(cloumnsName[i],"自动");
						}else{
							gridJson.put(cloumnsName[i],"手动");
						}
					}else if("MOTOR_MODE".equals(cloumnsName[i])){
						if("0".equals(datas.get(0)[i].toString())){
							gridJson.put(cloumnsName[i],"工频");
						}else{
							gridJson.put(cloumnsName[i],"变频");
						}
					}else{
						gridJson.put(cloumnsName[i],datas.get(0)[i].toString());
					}
					
				}else{
					gridJson.put(cloumnsName[i],"");
				}
				
			}
			
		}
		
		if(gridJson != null && "1".equals(outCode)){
			//System.out.println(gridJson);
			out.print(gridJson);
		}else{
			out.print(outCode);
		}
		return null;
	}
	
	//根据权限进行页面初始化
	public String SagdRPDWHpageInit()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		//String sagdid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("sagdid")));
		String sagddid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("sagddid")));
		String[] cloumnsName = {"JHMC","WELL_CODE","OILSTATIONNAME","QKMC","BLOCKSTATIONNAME","MANIFOLD","BRANCHING_NAME",
				"REPORT_DATE","P_PRESSURE_PRESS","P_PRESSURE_TEMP","P_LOOPED_PRESS","P_LOOPED_TEMP","P_DRIVEPIPE_PRESS","I_PRESSURE_PRESS","I_PRESSURE_TEMP",
				"I_LOOPED_PRESS","I_LOOPED_TEMP","I_DRIVEPIPE_PRESS","NO1STEAM_TEMP","NO1STEAM_PRESS","NO1STEAM_DIFP","NO1INSTANT_FLOW","LOWSUM_FLOW1",
				"NO1INSTANT_DRYNESS","NO1CONTROL_STATUS","NO1CONTROL_APERTURE","NO1FLOW_SET","NO2STEAM_TEMP","NO2STEAM_PRESS","NO2STEAM_DIFP","NO2INSTANT_FLOW",
				"LOWSUM_FLOW2","NO2INSTANT_DRYNESS","NO2CONTROL_STATUS","NO2CONTROL_APERTURE","NO2FLOW_SET","P_PRESS1","P_PRESS2","P_TEMP1","P_TEMP2","P_TEMP3",
				"P_TEMP4","P_TEMP5","P_TEMP6","P_TEMP7","P_TEMP8","P_TEMP9","P_TEMP10","P_TEMP11","P_TEMP12",
				"NOW_LOAD","MOTOR_PRESS_A","MOTOR_TEMP_A","MOTOR_PRESS_B","MOTOR_TEMP_B","MOTOR_PRESS_C","MOTOR_TEMP_C","CLIQUE_CONTROL_APERTURE2","ACTIVE_ENERGY","IDLE_ENERGY",
				"POWER_FACTOR","JIG_FREQUENCY","STROKE","PUMPING_RUNNING_STATE","MOTORSTATUS","CONVERSION_PRESS","NOW_FREQUENCY","CONVERSION_ELECTRICITY","MOTOR_MODE","CLIQUE_CONTROL_APERTURE1",
				"RUNTIME","DAILY_OUTPUT","DAILY_POWER_CONSUMPTIVE","DAILY_CUMULATIVE_STEAM1","DAILY_CUMULATIVE_STEAM2","REMARK"};
		List<Object[]> datas = new ArrayList<Object[]>();
		if(!"".equals(sagddid)){
			try {
				datas  = sagdService.searchSagdRPDview(sagddid,cloumnsName);
			} catch (Exception e) {
				e.printStackTrace();
				outCode = "-10004";
			}
		//数据错误
		}else{
			
		}
		JSONObject gridJson = null;
		
//		JSONArray arr = new JSONArray();
		if("1".equals(outCode) && datas != null && datas.size()>0){
			gridJson = new JSONObject();
			for(int i = 0; i <cloumnsName.length;i++){
				
				if(datas.get(0)[i] != null ){
					if("NO1CONTROL_STATUS".equals(cloumnsName[i])){
						if("0".equals(datas.get(0)[i].toString())){
							gridJson.put(cloumnsName[i],"手动");
						}else{
							gridJson.put(cloumnsName[i],"自动");
						}
					}else{
						gridJson.put(cloumnsName[i],datas.get(0)[i].toString());
					}
					
				}else{
					gridJson.put(cloumnsName[i],"");
				}
				
			}
			
		}
		
		if(gridJson != null && "1".equals(outCode)){
			//System.out.println(gridJson);
			out.print(gridJson);
		}else{
			out.print(outCode);
		}
		return null;
	}
	
	
	//根据权限进行页面初始化
	public String SagdRPDpageInit()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		String arg = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("arg")));
		String pageName = "SAGD井日报";

		//根据用户权限生成用户权限生成页面布局格式
		User user = (User) session.getAttribute("userInfo");
		
		String gridJson = null;
		try {
			
			if("wh".equals(arg)){
				pageName = "SAGD井日报维护";
				gridJson = AuthorityUtil.getGridJson(pageName, user, PageVariableUtil.SAGDDRPDWHALL_PAGE_GRID);
			}else if("sc".equals(arg)){
				pageName = "SAGD井日报生成";
				gridJson = AuthorityUtil.getGridJson(pageName, user, PageVariableUtil.SAGDDRPDWHALL_PAGE_GRID1);
			}else {
					gridJson = AuthorityUtil.getGridJson(pageName, user, PageVariableUtil.SAGDDRPDZQJ_PAGE_GRID);				
			}
			
			
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
	public String ButtonInit()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		String arg = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("arg")));
		String pageName = "SAGD井日报";
		
		//根据用户权限生成用户权限生成页面布局格式
		User user = (User) session.getAttribute("userInfo");
		
		String gridJson = null;
		try {
			if("wh".equals(arg)){
				pageName = "SAGD井日报维护";
				gridJson = AuthorityUtil.getGridJson(pageName, user, PageVariableUtil.SAGDDRPDWHALL_PAGE_GRID);
			}else if("sc".equals(arg)){
				pageName = "SAGD井日报生成";
				gridJson = AuthorityUtil.getGridJson(pageName, user, PageVariableUtil.SAGDDRPDWHALL_PAGE_GRID1);
			}else {
					gridJson = AuthorityUtil.getGridJson(pageName, user, PageVariableUtil.SAGDDRPDZQJ_PAGE_GRID);				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			outCode = "-10004";
		}
		
		if(gridJson != null && "1".equals(outCode)){
			//System.out.println(gridJson);
			//out.print(gridJson);
		}else{
			//out.print(outCode);
		}
		return null;
	}
	/**
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	public String addOrUpdateSagddRPD() throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String sagddid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("sagddids")));
		String rowData = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("rowData")));
		PcRpdWellSagddT prs = new PcRpdWellSagddT();
		String i_water = null;
		String p_water = null;
		Date nowDate = new Date();
		User user = null;
		boolean operFlag = true;
		List<PcRpdWellSagddT> prsList = new ArrayList<PcRpdWellSagddT>();
		user = (User) session.getAttribute("userInfo");
		if (!"".equals(sagddid)) {//审核
			String[] sagddids = sagddid.split(",");
			for (String sid : sagddids) {//审核一条或多条记录
					prs = sagdService.searchSagrd(sid);
//				if (!"".equals(prs.getVerifier()) && !"".equals(prs.getVerifyTime())) {//已经审核过的数据,不需再次进行审核
//					out.println("-13404");
//					return null;
//				}
				prs.setVerifier(user.getOperName());
				prs.setVerifyTime(nowDate);
				prsList.add(prs);
			}
		}
		else {
			//请求的long参数
			String[] jsonArgs = {"REPORT_DATE","SAGDID","I_INJE_TIME_RATION","I_PROC_TIME_RATION",
					"I_P_FLOW_NIPPLE","I_PRESSURE_PRESS",
					"I_PRESSURE_TEMP","I_I_FLOW_NIPPLE",
					"I_LOOPED_PRESS","I_LOOPED_TEMP","I_DRIVEPIPE_PRESS",
					"I_DAILY_OUTPUT","I_DAILY_OIL_OUTPUT","I_WATER_RATION",
					"I_STEAM_FLOW","I_DAILY_CUMULATIVE_STEAM",
					"I_REMARK","P_INJE_TIME_RATION","P_PROC_TIME_RATION","RUNTIME",
					"PUMP_ERROR_TIME","PUMP_DIAMETER","STROKE","JIG_FREQUENCY",
					"P_P_FLOW_NIPPLE","P_PRESSURE_PRESS","P_PRESSURE_TEMP","P_I_FLOW_NIPPLE",
					"P_LOOPED_PRESS","P_LOOPED_TEMP","P_DRIVEPIPE_PRESS","P_DAILY_OUTPUT",
					"P_DAILY_OIL_OUTPUT","P_WATER_RATION","P_STEAM_FLOW","P_DAILY_CUMULATIVE_STEAM",
					"P_TEMP1","P_TEMP2","P_TEMP3","P_TEMP4","P_TEMP5","P_TEMP6","P_TEMP7","P_TEMP8",
					"P_TEMP9","P_TEMP10","P_TEMP11","P_TEMP12","P_PRESS1","P_PRESS2","REMARK","P_BACK_PRES","I_BACK_PRES","CONTAINING_WATER_FLG",
					"P_STIMU_CODE", "P_SHUTIN_CODE","P_SHUTIN_TIME","P_OPEN_TIME","I_STIMU_CODE","I_SHUTIN_CODE","I_SHUTIN_TIME","I_OPEN_TIME"};
			String[] filedName = {"reportDate","sagdid","IInjeTimeRation","IProcTimeRation","IPFlowNipple",
					"IPressurePress","IPressureTemp","IIFlowNipple","ILoopedPress","ILoopedTemp","IDrivepipePress","IDailyOutput",
					"IDailyOilOutput","IWaterRation","ISteamFlow","IDailyCumulativeSteam","IRemark","PInjeTimeRation","PProcTimeRation",
					"runtime","pumpErrorTime","pumpDiameter","stroke","jigFrequency","PPFlowNipple","PPressurePress","PPressureTemp",
					"PIFlowNipple","PLoopedPress","PLoopedTemp","PDrivepipePress","PDailyOutput","PDailyOilOutput","PWaterRation",
					"PSteamFlow","PDailyCumulativeSteam","PTemp1","PTemp2","PTemp3","PTemp4","PTemp5","PTemp6","PTemp7",
					"PTemp8","PTemp9","PTemp10","PTemp11","PTemp12","PPress1","PPress2","remark","PbackPres","IbackPres","containingWaterFlg",
					"PStimuCode","PShutinCode","PShutinTime","POpenTime","IStimuCode","IShutinCode","IShutinTime","IOpenTime"};
			JSONArray jsonArr = JSONArray.fromObject(rowData);
			JSONObject jsonObj = null;
			for (Object jo : jsonArr) {
				jsonObj = JSONObject.fromObject(jo);
				String sagdid = jsonObj.get("SAGDID").toString();
				if (jsonObj.get("SAGDDID") != null && !"".equals(jsonObj.get("SAGDDID"))) {
					
					List<PcRpdWellSagddT> pros = null;
					try {
						pros = sagdService.searchSagrdByName(sagdid, jsonObj.get("REPORT_DATE").toString());//更新时存在指定日期的 同井同id的记录 报错
					} catch (Exception e) {
						e.printStackTrace();
					}
					sagddid = (String) jsonObj.get("SAGDDID");
					if(pros != null && pros.size()>0){
						prs = pros.get(0);
						if(prs.getIWaterRation()!=null){
							i_water = prs.getIWaterRation().toString();
						}
						if(prs.getPWaterRation()!=null)
							p_water = prs.getPWaterRation().toString();
						if (!sagddid.equals(prs.getSagddid())) {
							out.println("-13404");
							return null;
						}
						
					}
					
				}
				else {
					List<PcRpdWellSagddT> getPcRpdWellSagddT = null;
					try {
						getPcRpdWellSagddT = sagdService.searchSagrdByName(sagdid, jsonObj.get("REPORT_DATE").toString());//添加时存在同一天 同井的记录 报错
					} catch (Exception e) {
						e.printStackTrace();
						out.println("-13405");
						return null;
					}
					if (getPcRpdWellSagddT != null && getPcRpdWellSagddT.size() > 0) {
						out.println("-13405");
						return null;
					}
				}
				String argVal = "";
				for (int i = 0; i < jsonArgs.length; i++) {
					try {
						argVal = "";
						if (jsonObj.get(jsonArgs[i].toString()) != null) {
							argVal = jsonObj.get(jsonArgs[i].toString()).toString();
						}
						else {
							PropertyUtil.setProperty(prs, filedName[i], null);
							continue;
						}
						if (!"".equals(argVal) && (i == 1 || i == 16 || i == 50 || i == 54 || i == 55 || i == 58 || i == 59 )) {
							PropertyUtil.setProperty(prs, filedName[i], String.valueOf(argVal));
							continue;
						}
						else if (!"".equals(argVal) && (i == 0)) {
							PropertyUtil.setProperty(prs, filedName[i], StringUtil.parseDate("yyyy-MM-dd",argVal));
							continue;
						}else if (!"".equals(argVal) && (i==56 || i==57 || i==60 || i==61)) {
							PropertyUtil.setProperty(prs, filedName[i], StringUtil.parseDate("yyyy-MM-dd hh:mm",argVal));
							continue;
						}
						if(i==13 && jsonObj.get("SAGDDID") != null && !"".equals(jsonObj.get("SAGDDID")) && i_water!= argVal){
							PropertyUtil.setProperty(prs, filedName[51], java.math.BigDecimal.valueOf(1));
							if(argVal.indexOf("<span")>=0){
								PropertyUtil.setProperty(prs, filedName[i], java.math.BigDecimal.valueOf(Double.parseDouble(argVal.substring(0, argVal.indexOf("</span>")).substring(argVal.substring(0, argVal.indexOf("</span>")).indexOf(">")+1))));
							}else{
								if(argVal!="" && !"".equals(argVal))
									PropertyUtil.setProperty(prs, filedName[i], java.math.BigDecimal.valueOf(Double.parseDouble(argVal)));
								else{
									PropertyUtil.setProperty(prs, filedName[i], null);
								}
							}
							continue;
						}
						if(i==33 && jsonObj.get("SAGDDID") != null && !"".equals(jsonObj.get("SAGDDID")) && p_water!= argVal){
							PropertyUtil.setProperty(prs, filedName[51], java.math.BigDecimal.valueOf(1));
							if(argVal.indexOf("<span")>=0){
								PropertyUtil.setProperty(prs, filedName[i], java.math.BigDecimal.valueOf(Double.parseDouble(argVal.substring(0, argVal.indexOf("</span>")).substring(argVal.substring(0, argVal.indexOf("</span>")).indexOf(">")+1))));
							}else{
								if(argVal!="" && !"".equals(argVal))
									PropertyUtil.setProperty(prs, filedName[i], java.math.BigDecimal.valueOf(Double.parseDouble(argVal)));
								else{
									PropertyUtil.setProperty(prs, filedName[i], null);
								}
							}
							continue;
						}
						if (!"".equals(argVal)) {
							PropertyUtil.setProperty(prs, filedName[i], java.math.BigDecimal.valueOf(Double.parseDouble(argVal)));
						}
						
					} catch (Exception e) {
						e.printStackTrace();
					}					
				}
				prs.setRlastOper(user.getOperName());
				prs.setRlastOdate(new Date(System.currentTimeMillis()));
			}
		}
		
		try {
			if (prsList.size() > 0) {
				for (PcRpdWellSagddT pcRpdWellSagddT : prsList) {
					operFlag = sagdService.addOrUpdateSagddRPD(pcRpdWellSagddT);
				}
			} else {
				operFlag = sagdService.addOrUpdateSagddRPD(prs);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (sagddid == null || "".equals(sagddid)) {
				out.println("-13401");//返回添加信息错误代码
			}
			else {
				out.println("-13402");//返回修改信息错误代码
			}
		}		
		if(operFlag){
			out.println("1");
		}
		return null;
	}
	@SuppressWarnings("unused")
	public String removeSagddRPD() throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		String outCode = "1";
		PrintWriter out = response.getWriter();
		String sagddid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("sagddid")));
//		String datatype = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("datatype")));
		boolean operFlag = true; 
		try {
			operFlag = sagdService.removeSagddRPD(sagddid);
		} catch (Exception e) {
			e.printStackTrace();
			out.println("-13403");//返回错误代码
		}
		out.print("1");
		return null;
	}

	public String dayreport() throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		List<String> result = new ArrayList<String>(); 
		JSONObject obj = new JSONObject();
		
		String txtDate = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("txtDate")));


		
		try {
			result = sagdService.dayreport(txtDate);
		} catch (Exception e) {
			e.printStackTrace();
			obj = CommonsUtil.getRrturnJson("","当日日报生成失败" ,"", null, null);
		}
		
		if(result != null && result.size()>0){
			if("1".equals(result.get(0).toString())){
				obj = CommonsUtil.getRrturnJson("","" ,result.get(1).toString(), null, null);
			}else{
				obj = CommonsUtil.getRrturnJson("",result.get(1).toString() ,"", null, null);
			}
			
	
		}else{
			obj = CommonsUtil.getRrturnJson("","当日日报生成失败" ,"", null, null);
		}
		out.println(obj);
		return null;
	}
}
