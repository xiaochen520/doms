package com.echo.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;
import com.echo.dto.PcCdSubcoolDefaultParam;
import com.echo.dto.PcRdLoginfoT;
import com.echo.dto.User;
import com.echo.service.LogService;
import com.echo.service.SubcoolService;
import com.echo.util.AuthorityUtil;
import com.echo.util.CommonsUtil;
import com.echo.util.DynamicDataExportUtil;
import com.echo.util.PageVariableUtil;
import com.echo.util.StringUtil;

/**
 * 
 * @Company: Etrol
 * @ClassName: SubCoolAction 
 * @author LIJUN
 * @date 2015-8-27
 */
public class SubCoolAction {

	private SubcoolService subcoolService;
	private LogService logService;
	private InputStream excelFile = null;
	private InputStream adjustFile = null;
	HttpSession session = ServletActionContext.getRequest().getSession(true);
	
	public SubcoolService getSubcoolService() {
		return subcoolService;
	}

	public void setSubcoolService(SubcoolService subcoolService) {
		this.subcoolService = subcoolService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	
	public String getAdjustName() {
		String downloadFileName = "风城SAGD调控措施单.xls";
		try {
			downloadFileName = new String(downloadFileName.getBytes(),"ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return downloadFileName;
	}
	
	public String getFileName() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String downloadFileName = "SubCool历史数据(" + format.format(new Date()) + ").xls";
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
	
	public InputStream getAdjustFile() {
		return adjustFile;
	}

	@SuppressWarnings("unused")
	public String defaultParamInit() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		JSONObject jsonObj = null;
		
		try {
			jsonObj = subcoolService.getSubcoolDefaultParam("subcool");
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		if(jsonObj != null && "1".equals(outCode)){
			out.print(jsonObj);
		}else{
			outCode = "-16001";
			out.print(outCode);
		}
		return null;
	}
	
	public String setDefaultParam() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		
		int methodId = Integer.valueOf(request.getParameter("cal"));
		BigDecimal max = new BigDecimal(request.getParameter("max_subcool"));
		BigDecimal min = new BigDecimal(request.getParameter("min_subcool"));
		int flow = Integer.valueOf(request.getParameter("flow"));
		
		PcCdSubcoolDefaultParam subcoolParam = new PcCdSubcoolDefaultParam();
		subcoolParam.setSystemName("subcool");
		switch (methodId) {
			case 1:
				subcoolParam.setCalculateMethods("I井套压");
				break;
			case 2:
				subcoolParam.setCalculateMethods("(I井注汽压力+I井套压)/2");
				break;
			case 3:
				subcoolParam.setCalculateMethods("I井注汽压力 - 1");
				break;
			default:
				break;
		}
		subcoolParam.setCalculateMethodsId(methodId);
		subcoolParam.setCalculateRate(20);
		subcoolParam.setMaxSubcool(max);
		subcoolParam.setMinSubcool(min);
		subcoolParam.setFlowMethods(flow);
		try {
			subcoolService.setSubcoolDefaultParam(subcoolParam);
		} catch (Exception e) {
			e.printStackTrace();
			outCode = "-16002";
		}
		out.print(outCode);
		return null;
	}
	
	public String alarmParamInit() throws Exception {
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		//根据用户权限生成用户权限生成页面布局格式
		User user = (User) session.getAttribute("userInfo");
		
		String gridJson = null;
		try {
			gridJson = AuthorityUtil.getGridJson("SUBCOOL井组参数设置", user, PageVariableUtil.SUBCOOL_ALARM_GRID);
		} catch (Exception e) {
			e.printStackTrace();
			outCode = "-10004";
		}
		
		if(gridJson != null && "1".equals(outCode)){
			out.print(gridJson);
		}else{
			out.print(outCode);
		}
		
		return null;
	}
	
	public String alarmInfoInit() throws Exception {
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		//根据用户权限生成用户权限生成页面布局格式
		User user = (User) session.getAttribute("userInfo");
		
		String gridJson = null;
		try {
			gridJson = AuthorityUtil.getGridJson("SUBCOOL历史数据", user, PageVariableUtil.SUBCOOL_INFO_GRID);
		} catch (Exception e) {
			e.printStackTrace();
			outCode = "-10004";
		}
		
		if(gridJson != null && "1".equals(outCode)){
			out.print(gridJson);
		}else{
			out.print(outCode);
		}
		
		return null;
	}
	
	public String alarmNewInit() throws Exception {
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		//根据用户权限生成用户权限生成页面布局格式
		User user = (User) session.getAttribute("userInfo");
		
		String gridJson = null;
		try {
			gridJson = AuthorityUtil.getGridJson("SUBCOOL当前数据", user, PageVariableUtil.SUBCOOL_NEW_GRID);
		} catch (Exception e) {
			e.printStackTrace();
			outCode = "-10004";
		}
		
		if(gridJson != null && "1".equals(outCode)){
			out.print(gridJson);
		}else{
			out.print(outCode);
		}
		
		return null;
	}
	
	@SuppressWarnings("unused")
	public String searchAlarmParam() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		
		String area = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("areablock")));
		String oil = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("oilstationname")));
		String block = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("blockstationname")));
		String jh = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("wellnum")));
		
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
 		
 		HashMap<String,Object> dataMap = subcoolService.searchSubcoolAlarmParam(area, block, jh, pageNo, sort, order, rowsPerpage);
		
		Object jsonobj = dataMap.get("json");
		//返回显示数据
		if(jsonobj != null){
			out.println(jsonobj);
			out.flush();
			out.close();
		}
 		
 		return null;
	}
	
	public String addAlarmParam() throws Exception {
		int count = 0;
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONObject obj = new JSONObject();
		
		String area = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("areablock")));
		String block = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("blockstationname")));
		String jh = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("wellnum")));
		
		int cal = Integer.parseInt(request.getParameter("cal").trim());
		BigDecimal min = new BigDecimal(request.getParameter("min").trim());
		BigDecimal max = new BigDecimal(request.getParameter("max").trim());
		int flow = Integer.parseInt(request.getParameter("flow").trim());
		
		count = subcoolService.addSubcoolAlarmParam(area, block, jh, cal, min, max, flow);
		obj.put("count", count);
		out.println(obj);
		
		if(count > 0) {
			User user = (User) session.getAttribute("userInfo");
			PcRdLoginfoT log = CommonsUtil.getLogInfo(user, "1", "Subcool报警参数信息", jh);
			logService.addLog(log);
		}
		return null;
	}
	
	public String modifyAlarmParam() throws Exception {

		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONObject obj = new JSONObject();
		
		String calid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("calid")));
		
		int cal = Integer.parseInt(request.getParameter("cal").trim());
		BigDecimal min = new BigDecimal(request.getParameter("min").trim());
		BigDecimal max = new BigDecimal(request.getParameter("max").trim());
		int flow = Integer.parseInt(request.getParameter("flow").trim());
		
		boolean result = subcoolService.modifySubcoolAlarmParam(calid, cal, min, max, flow);
		obj.put("result", result);
		out.println(obj);
		if(result) {
			User user = (User) session.getAttribute("userInfo");
			PcRdLoginfoT log = CommonsUtil.getLogInfo(user, "3", "Subcool报警参数信息", calid);
			logService.addLog(log);
		}
		return null;
	}
	
	public String removeAlarmParam() throws Exception {

		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONObject obj = new JSONObject();
		
		String calid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("calid")));
		
		boolean result = subcoolService.removeSubcoolAlarmParam(calid);
		obj.put("result", result);
		out.println(obj);
		if(result) {
			User user = (User) session.getAttribute("userInfo");
			PcRdLoginfoT log = CommonsUtil.getLogInfo(user, "2", "Subcool报警参数信息", calid);
			logService.addLog(log);
		}
		return null;
	}
	
	@SuppressWarnings("unused")
	public String searchAlarmInfo() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		
		String area = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("areablock")));
		String oil = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("oilstationname")));
		String block = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("blockstationname")));
		String jh = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("wellnum")));
		String filter = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("filter")));
		String start = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("start")));
		String end = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("end")));

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
 		
 		HashMap<String,Object> dataMap = subcoolService.searchSubcoolCalResults(area, block, jh, filter, start, end, pageNo, sort, order, rowsPerpage);
		
		Object jsonobj = dataMap.get("json");
		//返回显示数据
		if(jsonobj != null){
			out.println(jsonobj);
			out.flush();
			out.close();
		}
 		
 		return null;
	}
	
	@SuppressWarnings("unused")
	public String searchNewInfo() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		
		String area = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("areablock")));
		String oil = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("oilstationname")));
		String block = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("blockstationname")));
		String jh = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("wellnum")));
 		
 		Object jsonobj = subcoolService.searchSubcoolNew(area, block, jh);
		
		//返回显示数据
		if(jsonobj != null){
			out.println(jsonobj);
			out.flush();
			out.close();
		}
 		
 		return null;
	}
	
	@SuppressWarnings("unused")
	public String searchAlarmLine() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		
		String area = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("areablock")));
		String oil = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("oilstationname")));
		String block = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("blockstationname")));
		String jh = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("wellnum")));
		String start = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("start")));
		String end = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("end")));
		
//		System.out.println("@@@@@@@@"+start+"@@@@@@@@@@@@");
//		System.out.println("@@@@@@@@"+end+"@@@@@@@@@@@@");
		
		
		JSONObject json = subcoolService.searchSubcoolCalResults(area, block, jh, start, end);
		if(json != null){
			out.println(json);
			out.flush();
			out.close();
		}
		
		return null;
	}
	
	public String getAlarmInfo() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		
		String sagdid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("sagdid")));
		
		String outCode = "1";
		JSONObject jsonObj = null;
		
		try {
			jsonObj = subcoolService.getSubcoolCalResult(sagdid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(jsonObj != null && "1".equals(outCode)){
			out.print(jsonObj);
		}else{
			outCode = "-16002";
			out.print(outCode);
		}
		return null;
	}
	
	public String modifySuggestParam() throws Exception {

		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONObject obj = new JSONObject();
		String outCode = "1";
		
		String rdid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("rdid")));
		BigDecimal modifyflow = new BigDecimal(request.getParameter("modifyflow").trim());
		BigDecimal modifyjig = new BigDecimal(request.getParameter("modifyjig").trim());
		BigDecimal modifynip = new BigDecimal(request.getParameter("modifynip").trim());
		Integer modifymode = new Integer(request.getParameter("modifymode").trim());
		
		User user = (User) session.getAttribute("userInfo");
		boolean result = subcoolService.modifySubcoolSuggestParam(rdid, modifyflow, modifyjig, modifynip, modifymode, user);
		if(!result) {
			outCode = "-16002";
		}
		obj.put("outCode", outCode);
		out.println(obj);
		if(result) {
			PcRdLoginfoT log = CommonsUtil.getLogInfo(user, "3", "Subcool建议控制参数信息", rdid);
			logService.addLog(log);
		}
		return null;
	}
	
	@SuppressWarnings("unused")
	public String exportAlarmInfo() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		
		String type = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("type")));
		String area = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("areablock")));
		String oil = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("oilstationname")));
		String block = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("blockstationname")));
		String jh = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("wellnum")));
		String start = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("start")));
		String end = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("end")));
		
		if ("total".equals(type)) {
			int total = subcoolService.getExportTotal(area, block, jh, start, end);
			out.println(total);
			out.flush();
			out.close();
			return null;
		} else if ("export".equals(type)) {
			response.resetBuffer();
			response.reset();
			
			String templetFilePath = request.getSession().getServletContext().getRealPath("/") + "exceltemplet\\动态数据报表.xls";
			List<Object[]> dataLsit = subcoolService.exportAlarmInfo(area, block, jh, start, end);
			String sheetName = "SubCool历史数据";
			ByteArrayOutputStream baos = DynamicDataExportUtil.dynamicDataExporReport(dataLsit,templetFilePath,sheetName);
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
	
	public String exportSuggest() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		User user = (User) session.getAttribute("userInfo");
		String sagds = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("sagds")));
		if(!"".equals(sagds) || sagds != null) {
			response.resetBuffer();
			response.reset();
			
			List<Object[]> dataLsit = subcoolService.exportSuggest(sagds);
			
			ByteArrayOutputStream baos = DynamicDataExportUtil.dynamicDataExporReport(dataLsit, user);
			if(baos != null){
				byte[] ba = baos.toByteArray();
				adjustFile = new ByteArrayInputStream(ba);
				baos.flush();
				baos.close();
				return "adjust";
			}
		}
		out.println("");
		return null;
	}
}
