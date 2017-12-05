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
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;
import com.echo.dto.PcCdBoilerT;
import com.echo.dto.PcRpdBoilerHighDryT;
import com.echo.dto.User;
import com.echo.service.GGDGLRDService;
import com.echo.service.LogService;
import com.echo.util.AuthorityUtil;
import com.echo.util.CommonsUtil;
import com.echo.util.DynamicDataExportUtil;
import com.echo.util.PageVariableUtil;
import com.echo.util.PropertyUtil;
import com.echo.util.StringUtil;

public class GGDRDAction {


	private GGDGLRDService ggdglRDService;
	private InputStream excelFile = null;

	
	public void setGgdglRDService(GGDGLRDService ggdglRDService) {
		this.ggdglRDService = ggdglRDService;
	}
	private LogService logService;

	public LogService getLogService() {
		return logService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	
	public String getFileName() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd ");
		String downloadFileName = (sf.format(new Date()).toString())+ "高干度锅炉动态数据.xls";
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		String pageCode = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("pageCode")));
		if ("日报".equals(pageCode) || "rb".equals(pageCode)) {
			downloadFileName = (sf.format(new Date()).toString())+ "高干度锅炉日报.xls";
		}
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
	public String searchGGDRD() throws IOException {
		
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String oilationname = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("oilationname")));  //采油站
		String areablock = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("areablock")));  //区块
		String blockstationname = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("blockstationname"))); //供热站
		String ghname = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("ghname")));  //
		String boilersName = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("boilersName")));   //锅炉
		String acceptunit = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("acceptunit")));  // 受气单位
		String stime=StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("date"))); 
		String etime=StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("date1")));
		String totalNum = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("totalNum")));
		String pageCode = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("pageCode")));
		
		if("".equals(oilationname)  && "".equals(boilersName) 
				 && "".equals(areablock) && "".equals(ghname) && "".equals(blockstationname)&& "".equals(acceptunit)
				 && "".equals(stime) && "".equals(etime) && "".equals(totalNum)){
			out.println("");
			return null;
		}
		if(stime.equals("")||stime.equals("undefined")||stime==null){
			Date date=new Date();
			SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			stime=sf.format(date);
			
		}
		if(etime.equals("")||etime.equals("undefined")||etime==null){
			Date date=new Date();
			SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			etime=sf.format(date); 
			
		}
		
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
			dataMap = ggdglRDService.searchData(oilationname, areablock,blockstationname,ghname,boilersName,acceptunit,stime,etime,pageNo,sort,order,rowsPerpage,pageCode,totalNum);
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
			String templetFilePath = request.getSession().getServletContext().getRealPath("/") + "exceltemplet\\动态数据报表.xls";
//			将用户对象列表输出到workbook的流中，ExcelUtil类进行了封装，后续分析！
			java.io.ByteArrayOutputStream baos = null;
			if ("日报".equals(pageCode) || "rb".equals(pageCode)) {
				templetFilePath = request.getSession().getServletContext().getRealPath("/") + "exceltemplet\\日报数据.xls";
				baos = DynamicDataExportUtil.dailyDataExporReport(dataLsit,templetFilePath,"高干度锅炉日报");
			}
			else {
				baos = DynamicDataExportUtil.dynamicDataExporReport(dataLsit,templetFilePath,"高干度锅炉动态数据");
			}
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
	
	
	
	//根据权限进行页面初始化
	public String pageInit()throws Exception{
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		//根据用户权限生成用户权限生成页面布局格式
		User user = (User) session.getAttribute("userInfo");
		String arg = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("arg")));
		String gridJson = null;
		try {
			if (!"rb".equals(arg) && !"rbwh".equals(arg)) {
				gridJson = AuthorityUtil.getGridJson("高干度锅炉动态数据", user, PageVariableUtil.GGDBOILER_RD_PAGE_GRID);
			}
			else if ("rbwh".equals(arg)) {
				gridJson = AuthorityUtil.getGridJson("高干度锅炉日报维护", user, PageVariableUtil.PC_RPD_BOILER_HIGH_DRY_T_WH);
			}
			else {
				gridJson = AuthorityUtil.getGridJson("高干度锅炉日报", user, PageVariableUtil.PC_RPD_BOILER_HIGH_DRY_T);
			}
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
	public String saveOrUpdate() throws Exception{
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONObject obj = new  JSONObject();
		HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
		User user = (User) session.getAttribute("userInfo");
		String outCode = "1";
		//根据用户权限生成用户权限生成页面布局格式
//		User user = (User) session.getAttribute("userInfo");
		String[] decimalArgsName = {"gas_into_press","gas1_press","fire_measure","gas_flow","pumpin_press","pumpout_temp"
				,"pumpfc_frequency","watersupply_flow","steamin_temp","steamout_temp","steam_dryness","rs_press_reduction","superheat_press_reduction","rerheat_press_reduction","mixer_press_reduction"
				,"pumpout_press","csin_press","csin_temp","csout_press","csout_temp","convectionr_press_reduction","rsin_press","rsin_temp","rs_press","rsout_temp","rs_temp"
				,"reheat_press_entrance","reheat_temp_entrance","reheat_temp_export","gas2_press","gas3_press","lube_temp","burner_temp","hearth_press","ejectsmoke_temp"
				,"system_voltage","pump_motor_current","pump_motor_temp","fan_motor_current","fan_air_intake_temp","fuel_gas_density","h2s_density","system_press_reduction","runtime",
				"dailyCumulativeSteam","dailyCumulativeGas","sewageBufferTank"};
		String[] decimalfiledsName = {"gasIntoPress","gas1Press","fireMeasure","gasFlow","pumpinPress","pumpoutTemp","pumpfcFrequency","watersupplyFlow","steaminTemp","steamoutTemp","steamDryness","rsPressReduction","superheatPressReduction","rerheatPressReduction","mixerPressReduction","pumpoutPress","csinPress","csinTemp","csoutPress","csoutTemp","convectionrPressReduction","rsinPress","rsinTemp","rsPress","rsoutTemp","rsTemp","reheatPressEntrance","reheatTempEntrance","reheatTempExport","gas2Press","gas3Press","lubeTemp","burnerTemp","hearthPress","ejectsmokeTemp","systemVoltage","pumpMotorCurrent","pumpMotorTemp","fanMotorCurrent","fanAirIntakeTemp","fuelGasDensity","h2sDensity","systemPressReduction","runtime","dailyCumulativeSteam","dailyCumulativeGas","sewageBufferTank"};
		String[] strArgsName = {"block_name","blockstation_name","steam_work_group","steam_inje_unit","oildrilling_station_name"};
		String[] strfiledsName = {"blockName","blockstationName","steamWorkGroup","steamInjeUnit","oildrillingStationName"};
		//		String boilerData = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("boilerData")));
		PcRpdBoilerHighDryT prbhd = null;
		String argVal = null;
		argVal = StringUtil.isNullUtil(request.getParameter("rpdBoilerHighDryId"));
		if (!"".equals(argVal)) {
			prbhd = ggdglRDService.searchGGDRDBoiler(argVal);
		}
		else {
			prbhd = new PcRpdBoilerHighDryT();
		}
		PcCdBoilerT pcCdBoilerT = null;
		argVal = StringUtil.isNullUtil(StringUtil.toStr(request.getParameter("boiler_name")));
		if (!"".equals(argVal)) {
			prbhd.setBoilerName(argVal);
			pcCdBoilerT = ggdglRDService.searchBoiler(argVal);
		}
		else {
			pcCdBoilerT = new PcCdBoilerT();
		}
		prbhd.setPcCdBoilerT(pcCdBoilerT);
		for (int i = 0; i < decimalfiledsName.length; i++) {
			argVal = StringUtil.isNullUtil(request.getParameter(decimalArgsName[i]));
		//	if (!"".equals(argVal)) {
				try {
					PropertyUtil.setProperty(prbhd, decimalfiledsName[i],CommonsUtil.getBigDecimalData(argVal));
				} catch (Exception e) {
					e.printStackTrace();
				}
			//}
		}
		for (int i = 0; i < strfiledsName.length; i++) {
			argVal = StringUtil.isNullUtil(StringUtil.toStr(request.getParameter(strArgsName[i])));
			//if (!"".equals(argVal)) {
				try {
					PropertyUtil.setProperty(prbhd, strfiledsName[i], String.valueOf(argVal));
				} catch (Exception e) {
					e.printStackTrace();
				}
			//}
		}
		argVal = StringUtil.isNullUtil(request.getParameter("reportDate"));
		if (!"".equals(argVal)) {
			PropertyUtil.setProperty(prbhd, "reportDate", StringUtil.parseDate("yyyy-MM-dd",argVal));
		}
		String REMARK =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("REMARK")));
		prbhd.setRemark(REMARK);
		prbhd.setRlastOper(user.getOperName());
		prbhd.setRlastOdate(new Date());
		String gridJson = null;
		boolean operFlag = false;
		try {
			operFlag = ggdglRDService.saveOrUpdate(prbhd);
			if (operFlag) {
				out.print("1");
			}
		} catch (Exception e) {
			String  errmsg = e.getCause().getCause().toString();
			
			/*if(!"".equals(errmsg) && errmsg.indexOf("ORA-01438") != -1){
				obj = CommonsUtil.getRrturnJson("","您输入信息超过数据库数据精度范围" ,"", null, null);
			} else{
				obj = CommonsUtil.getRrturnJson("",errmsg ,"", null, null);
				
			}*/
			
			e.printStackTrace();
			out.print(obj);
			return null;
		}
		return gridJson;
	}
	@SuppressWarnings("unused")
	public String removeGGDRDBoiler() throws IOException {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		
		String rpdBoilerHighDryId = StringUtil.toStr(request.getParameter("rpdBoilerHighDryId"));
		boolean deleteflg = false;
		JSONObject obj = new JSONObject();
		try {
			deleteflg = ggdglRDService.removeGGDRDBoiler(rpdBoilerHighDryId);
		} catch (Exception e) {
			String errmsg = e.getCause().getCause().toString();
			//ORA-02292: 违反完整约束条件 (NEWPORTAL.FK58_PC_RPD_WELL_SAGDD_T) - 已找到子记录
			/*if(!"".equals(errmsg) && errmsg.indexOf("ORA-02292") != -1 ){
				obj = CommonsUtil.getRrturnJson("","锅炉基础信息删除失败-锅炉日报中存在子记录" ,"", null, null);
			}else if(!"".equals(errmsg) && errmsg.indexOf("ORA-02292") != -1 ){
				obj = CommonsUtil.getRrturnJson("","锅炉基础信息删除失败-锅炉班报中存在子记录" ,"", null, null);
			} else{
				obj = CommonsUtil.getRrturnJson("",errmsg ,"", null, null);
			}*/
			e.printStackTrace();
		}
		if(deleteflg){
			
			//添加系统LOG
			/*try {
				User user1 = (User) session.getAttribute("userInfo");
				PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "2", "锅炉基础信息", bid);
				logService.addLog(log);
			} catch (Exception e) {
				e.printStackTrace();
				obj = CommonsUtil.getRrturnJson("-10003", "","", null, null);
			}*/
			
			
		}
		//out.print(obj);
		return null;
	}
	
	
	
	
	//曲线
	public String searchAlarmLine() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();		
		
		String areablock = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("areablock")));
		String blockstationname = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("blockstationname")));
		String boilersName = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("boilersName")));
		String oilationname = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("oilationname")));

		String startDate = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("startDate")));
		String endDate = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("endDate")));
		JSONObject json = ggdglRDService.searchGgdglResults(areablock, blockstationname,boilersName,oilationname, startDate,endDate);
		if(json != null){
			out.println(json);
			out.flush();
			out.close();
		}
		
		return null;
	}
	
	//曲线
	public String searchAlarmLine1() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();		
		
		String areablock = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("areablock")));
		String blockstationname = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("blockstationname")));
		String boilersName = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("boilersName")));
		String oilationname = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("oilationname")));

		String startDate = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("startDate")));
		String endDate = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("endDate")));
		JSONObject json = ggdglRDService.searchGgdglResults1(areablock, blockstationname,boilersName,oilationname, startDate,endDate);
		if(json != null){
			out.println(json);
			out.flush();
			out.close();
		}
		
		return null;
	}
	
}
