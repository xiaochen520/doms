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
import com.echo.dto.PcRdLoginfoT;
import com.echo.dto.PcRpdBoilerSuperheatT;
import com.echo.dto.User;
import com.echo.service.BoilerCrosslRDService;
import com.echo.service.LogService;
import com.echo.service.SRGLRDService;
import com.echo.util.AuthorityUtil;
import com.echo.util.CommonsUtil;
import com.echo.util.DynamicDataExportUtil;
import com.echo.util.PageVariableUtil;
import com.echo.util.PropertyUtil;
import com.echo.util.StringUtil;

public class BoilerCrossdRPDAction {
	
	private BoilerCrosslRDService boilerCrosslRDService;
	private InputStream excelFile = null;
	private SRGLRDService srglRDService;
	


	private LogService logService;

	public LogService getLogService() {
		return logService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	
	public void setBoilerCrosslRDService(BoilerCrosslRDService boilerCrosslRDService) {
		this.boilerCrosslRDService = boilerCrosslRDService;
	}

	public SRGLRDService getSrglRDService() {
		return srglRDService;
	}

	public void setSrglRDService(SRGLRDService srglRDService) {
		this.srglRDService = srglRDService;
	}

	public String getFileName() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd ");
		String downloadFileName = (sf.format(new Date()).toString())+ "过热锅炉日报.xls";
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
	public String searchBoilerCrossRPD() throws IOException {
		
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String block_name = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("areablock")));  //区块
		String blockstationname = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("blockstationname")));  //供热站
		String group = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("group")));
		String ghname = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("oilstationname")));  //采油站
		String boilersName = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("boilersName")));   //锅炉
		String acceptunit =  StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("acceptunit")));   //受气单位
		String stime=StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("startDate")));
		String etime=StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("endDate")));
		String totalNum = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("totalNum")));
		
		if(stime.equals("")||stime.equals("undefined")||stime==null){
			Date date=new Date();
			SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
			stime=sf.format(date);
			
		}
		if(etime.equals("")||etime.equals("undefined")||etime==null){
			Date date=new Date();
			SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
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
		if("".equals(block_name) && "".equals(blockstationname)  && "".equals(group) && "".equals(boilersName)
				 && "".equals(stime) && "".equals(etime) && "".equals(totalNum)){
			out.println("");
			return null;
		}
		HashMap<String,Object> dataMap = null;
		try {
			
			dataMap = boilerCrosslRDService.searchRPDData(block_name,blockstationname, group, ghname, boilersName,acceptunit, stime, etime, pageNo, sort, order, rowsPerpage, totalNum);
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
			java.io.ByteArrayOutputStream baos = DynamicDataExportUtil.dailyDataExporReport(dataLsit,templetFilePath,"过热锅炉日报");
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
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		String arg = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("arg")));
		//根据用户权限生成用户权限生成页面布局格式
		User user = (User) session.getAttribute("userInfo");
		String gridJson = null;
		try {
			gridJson = AuthorityUtil.getGridJson("过热锅炉日报", user, PageVariableUtil.BOILER_CROSSDD_RPD_PAGE_GRID);

			if ("wh".equals(arg)) {
				gridJson = AuthorityUtil.getGridJson("过热锅炉日报维护", user, PageVariableUtil.BOILER_CROSSDDWH_RPD_PAGE_GRID);
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
	
	public String removeBoilerCrpssdRPD() throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String boilerCrossddid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("id")));
		boolean operFlag = true;
		String outCode = "1";
		try {
			operFlag = boilerCrosslRDService.removeBoilerCrpssdRPD(boilerCrossddid);
		} catch (Exception e) {
			e.printStackTrace();
			outCode="-14305";//返回错误代码
		}
		
			if(operFlag){
			
			//添加系统LOG
			try {
				User user1 = (User) session.getAttribute("userInfo");
				PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "2", "过热汽锅炉日报维护", boilerCrossddid);
				logService.addLog(log);
			} catch (Exception e) {
				e.printStackTrace();
				outCode = "-10003";
			}
		}
		out.print(outCode);
		return null;
	}
	@SuppressWarnings("unused")
	public String SaveOrUpadateBoilerCrossdRPD()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		List<PcRpdBoilerSuperheatT> pbcList = null;
		PcRpdBoilerSuperheatT psc = null;
		HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
		User user = (User) session.getAttribute("userInfo");
		String outCode = "1";
		boolean addflg = true;
		String crossddid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("boilerCrossddid")));
		String boilerName  =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("boiler_name")));
		String boilerid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("boilerid")));
		String block_name = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("block_name")));
		String reportDate = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("report_date")));
		String blockstationName = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("blockstation_name")));
		String steamWorkGroup = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("steam_work_group")));
		String steamInjeUnit = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("steam_inje_unit")));
		String oildrilling_station_name = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("oildrilling_station_name")));
		String sewageBufferTank = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("sewageBufferTank")));
		//修改
		if(crossddid !=null && !"".equals(crossddid)){
			try {
				pbcList = boilerCrosslRDService.searchCross("" ,boilerid, "",reportDate );
				if(pbcList != null && pbcList.size()>0){
					if(pbcList.size() == 1){
						//要修改为本身数据可以修改
						if(crossddid.equals(pbcList.get(0).getRpdBoilerSuperheatId())){
							psc = pbcList.get(0);
						//数据库中存在另一条该锅炉日报记录不许修改
						}else{
							outCode = "-14302";
						}
					//要修改数据数据库中存在多条
					}else if(pbcList.size() >1){
						
						outCode = "-14303";
					}
					
				//要修改数据，数据库中不存在允许修改
				}else{
					pbcList = boilerCrosslRDService.searchCross(crossddid ,"", "","" );
					psc = pbcList.get(0);
					
				}
			} catch (Exception e) {
				e.printStackTrace();
				//过热锅炉日报维护信息获取失败
				outCode = "-14301";
			}

		//添加	
		}else{
			try {
				pbcList = boilerCrosslRDService.searchCross("" ,boilerid, "",reportDate);
				//数据库中存在此记录不允许用户添加新纪录
				if(pbcList != null && pbcList.size()>0){
					outCode = "-14304";
					out.print(outCode);
					return null;
				//数据库中不存在记录允许用户添加新纪录
				}else{
					psc = new PcRpdBoilerSuperheatT();
					
				}
			} catch (Exception e) {
				e.printStackTrace();
				//过热锅炉日报维护信息获取失败
				outCode = "-14301";
			}
		}
		
		//  if("1".equals(outCode)){
			//请求的long参数
				String[] longArgs = {
						"runtime",
						"gas_into_press",
						"gas1_press",
						"fire_measure",
						"gas_flow",
						"pumpin_press",
						"pumpout_temp",
						"pumpfc_frequency","watersupply_flow",
						"steamin_temp","steamout_temp",
						"superheat_degree","superheatout_temp",
						"sl_level","rs_press_reduction",
						"superheat_press_reduction","mixer_press_reduction",
						"pumpout_press","csin_press",
						"csin_temp","csout_press",
						"csout_temp","convectionr_press_reduction",
						"rsin_press","rsin_temp",
						"rs_press","rsout_temp",
						"rs_temp","rs_dryness",
						"separator_press_export","superheatin_press",
						"superheatin_temp","superheatout_press",
						"superheat_temp","superheatin_flow",
						"mixer_press_water","gas2_press",
						"gas3_press","lube_temp",
						"fan_air_export_press",
						"fan_air_intake_temp",	
						"burner_temp","hearth_press",
						"ejectsmoke_temp","system_voltage",
						"pump_motor_current","pump_motor_temp",
						"fan_motor_current",
						"fuel_gas_density",
						"h2s_density",
						"daily_cumulative_gas",
						"steam_inje_unit",
						"daily_cumulative_steam",
						"sewageBufferTank"};
				String[] methodNames = {
						"runtime",
						"gasIntoPress",
						"gas1Press",
						"fireMeasure",
						"gasFlow",
						"pumpinPress",
						"pumpoutTemp",
						"pumpfcFrequency",
						"watersupplyFlow",
						"steaminTemp",
						"steamoutTemp",
						"superheatDegree",
						"superheatoutTemp",
						"slLevel",
						"rsPressReduction",
						"superheatPressReduction",
						"mixerPressReduction",
						"pumpoutPress",
						"csinPress",
						"csinTemp",
						"csoutPress",
						"csoutTemp",
						"convectionrPressReduction",
						"rsinPress",
						"rsinTemp",
						"rsPress",
						"rsoutTemp",
						"rsTemp",
						"rsDryness",
						"separatorPressExport",
						"superheatinPress",
						"superheatinTemp",
						"superheatoutPress",
						"superheatTemp",
						"superheatinFlow",
						"mixerPressWater",
						"gas2Press",
						"gas3Press",
						"lubeTemp",
						"fanAirExportPress",
						"fanAirIntakeTemp",			
						"burnerTemp",
						"hearthPress",
						"ejectsmokeTemp",
						"systemVoltage",
						"pumpMotorCurrent",
						"pumpMotorTemp",
						"fanMotorCurrent",
						"fuelGasDensity",
						"h2sDensity",
						"dailyCumulativeGas",
						"steamInjeUnit",
						"dailyCumulativeSteam",
						"sewageBufferTank"
						
				};// 此行方法名称需修改
				for (int i = 0; i < longArgs.length; i++) {
					String argVal = StringUtil.isNullUtil(request.getParameter(longArgs[i]));
					//if (!"".equals(argVal)) {//如果长整型参数不为空 set值 
						try {
							PropertyUtil.setProperty(psc, methodNames[i],CommonsUtil.getBigDecimalData(argVal));
							//PropertyUtil.setProperty(psc, methodNames[i],java.math.BigDecimal.valueOf(Double.parseDouble(argVal)));
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					//}
				}
				String REMARK = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("REMARK")));
				psc.setRemark(REMARK);
				psc.setRlastOper(user.getOperName());
				psc.setRlastOdate(new Date());
				psc.setBoilerid(boilerid);
				psc.setBoilerName(boilerName);
				psc.setBlockName(block_name);
				psc.setSteamWorkGroup(steamWorkGroup);
				psc.setSteamInjeUnit(steamInjeUnit);
				psc.setOildrillingStationName(oildrilling_station_name);
				String blockStationName = boilerCrosslRDService.searchBlockName(boilerName);
				psc.setBlockstationName(blockStationName);
				psc.setReportDate(StringUtil.parseDate("yyyy-MM-dd",reportDate));
				
				//psc.setBoilerCrossddid(crossddid);
				try {
					if(crossddid != null && !"".equals(crossddid)){
						addflg = boilerCrosslRDService.updatePsc(psc);
						
					}else{
						addflg = boilerCrosslRDService.addPsc(psc);
						
					}
				} catch (Exception e) {
					e.printStackTrace();
					if(crossddid != null && !"".equals(crossddid)){
//						//过热锅炉日报维护信息更新失败
						outCode = "-14306";
					}else{
//						//过热锅炉日报维护信息添加失败
						outCode = "-14308";
					}
				}
			//}
		out.print(outCode);
		return null;
	}
	/**
	public String SaveOrUpadateBoilerCrossdRPD()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		
		String crossddid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("boilerCrossddids")));
		String rowData = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("rowData")));
		PcRpdBoilerSuperheatT prs = new PcRpdBoilerSuperheatT();
		User user = null;
		boolean operFlag = true;
		List<PcRpdBoilerSuperheatT> prsList = new ArrayList<PcRpdBoilerSuperheatT>();
		user = (User) session.getAttribute("userInfo");
			String[] jsonArgs = {
					"REPORT_DATE",
					"RPD_BOILER_SUPERHEAT_ID",
					"BOILERID",
					"OILDRILLING_STATION_NAME",
					"BLOCKSTATION_NAME",
					"BOILER_NAME",
					"STEAMOUT_TEMP",
					"STEAMIN_TEMP",
					"EJECTSMOKE_TEMP",
					"BURNER_TEMP",
					"CSIN_PRESS",
					"CSOUT_PRESS",
					"CSIN_TEMP",
					"CSOUT_TEMP",
					"SL_LEVEL",
					"SUPERHEAT_TEMP",
					"SUPERHEAT_PIEZORESISTANCE",
					"SUPERHEATIN_TEMP",
					"SUPERHEATOUT_TEMP",
					"SUPERHEATIN_PRESS",
					"SUPERHEATOUT_PRESS",
					"SUPERHEATIN_FLOW",
					"HEARTH_PRESS",
					"GAS1_PRESS",
					"GAS2_PRESS",
					"GAS3_PRESS",
					"RS_TEMP",
					"RS_DRYNESS",
					"RS_PIEZORESISTANCE",
					"RSIN_PRESS",
					"RS_PRESS",
					"RSIN_TEMP",
					"RSOUT_TEMP",
					"PUMPA_FLOW",
					"PUMPB_FLOW",
					"PUMPC_FLOW",
					"PUMPA_PRESS",
					"PUMPB_PRESS",
					"PUMPC_PRESS",
					"FANA_ELECTRICITY",
					"FANB_ELECTRICITY",
					"FANC_ELECTRICITY",
					"PUMPFC_FREQUENCY",
					"PUMPIN_PRESS",
					"PUMPOUT_PRESS",
					"PUMPIN_TEMP",
					"PUMPOUT_TEMP",
					"WATERSUPPLY_FLOW",
					"WATERSUPPLY_TOTAL",
					"GAS_FLOW",
					"GAS_TOTAL",
					"STEAMINJECTION_TOTAL",
					"DAILY_CUMULATIVE_WATER",
					"DAILY_CUMULATIVE_GAS",
					"DAILY_CUMULATIVE_STEAM",
					"GAS_INTO_PRESS",
					"FIRE_MEASURE",
					"SUPERHEAT_DEGREE",
					"MIXER_PRESS_REDUCTION",
					"CONVECTIONR_PRESS_REDUCTION",
					"SEPARATOR_PRESS_EXPORT",
					"MIXER_PRESS_WATER",
					"LUBE_TEMP",
					"SYSTEM_VOLTAGE",
					"PUMP_MOTOR_TEMP",
					"PUMP_MOTOR_CURRENT",
					"RS_PRESS_REDUCTION",
					"SUPERHEAT_PRESS_REDUCTION",
					"FAN_MOTOR_CURRENT",
					"BLOCK_NAME",
					"STEAM_WORK_GROUP",
					"STEAM_INJE_UNIT",
					"FAN_AIR_INTAKE_TEMP",
					"H2S_DENSITY",		
					"FUEL_GAS_DENSITY",			
					"FAN_AIR_EXPORT_PRESS",
					"RUNTIME"

			};
			String[] filedName = {
					"reportDate",
					"rpdBoilerSuperheatId",
					"boilerid",
					"oildrillingStationName",
					"blockstationName",
					"boilerName",
					"steamoutTemp",
					"steaminTemp",
					"ejectsmokeTemp",
					"burnerTemp",
					"csinPress",
					"csoutPress",
					"csinTemp",
					"csoutTemp",
					"slLevel",
					"superheatTemp",
					"superheatPiezoresistance",
					"superheatinTemp",
					"superheatoutTemp",
					"superheatinPress",
					"superheatoutPress",
					"superheatinFlow",
					"hearthPress",
					"gas1Press",
					"gas2Press",
					"gas3Press",
					"rsTemp",
					"rsDryness",
					"rsPiezoresistance",
					"rsinPress",
					"rsPress",
					"rsinTemp",
					"rsoutTemp",
					"pumpaFlow",
					"pumpbFlow",
					"pumpcFlow",
					"pumpaPress",
					"pumpbPress",
					"pumpcPress",
					"fanaElectricity",
					"fanbElectricity",
					"fancElectricity",
					"pumpfcFrequency",
					"pumpinPress",
					"pumpoutPress",
					"pumpinTemp",
					"pumpoutTemp",
					"watersupplyFlow",
					"watersupplyTotal",
					"gasFlow",
					"gasTotal",
					"steaminjectionTotal",
					"dailyCumulativeWater",
					"dailyCumulativeGas",
					"dailyCumulativeSteam",
					"gasIntoPress",
					"fireMeasure",
					"superheatDegree",
					"mixerPressReduction",
					"convectionrPressReduction",
					"separatorPressExport",
					"mixerPressWater",
					"lubeTemp",
					"systemVoltage",
					"pumpMotorTemp",
					"pumpMotorCurrent",
					"rsPressReduction",
					"superheatPressReduction",
					"fanMotorCurrent",
					"blockName",
					"steamWorkGroup",
					"steamInjeUnit",
					"fanAirIntakeTemp",			
					"h2sDensity",			
					"fuelGasDensity",			
					"fanAirExportPress",			
					"runtime"

			};
			JSONArray jsonArr = JSONArray.fromObject(rowData);
			JSONObject jsonObj = null;
			for (Object jo : jsonArr) {
				jsonObj = JSONObject.fromObject(jo);
				String rpdid = "";
				if(jsonObj.get("rpd_boiler_superheat_id")!=null){
					rpdid = jsonObj.get("rpd_boiler_superheat_id").toString();
				}
				crossddid = rpdid;
				if (jsonObj.get("rpd_boiler_superheat_id") != null && !"".equals(jsonObj.get("rpd_boiler_superheat_id"))) {
					
					List<PcRpdBoilerSuperheatT> pros = null;
					try {
						pros = boilerCrosslRDService.searchCross("", "", jsonObj.get("BOILER_NAME").toString(), jsonObj.get("REPORT_DATE").toString());//更新时存在指定日期的 同井同id的记录 报错
					} catch (Exception e) {
						e.printStackTrace();
					}
					if(pros != null && pros.size()>0){
						prs = pros.get(0);
						if (!rpdid.equals(prs.getRpdBoilerSuperheatId())) {
							out.println("-13404");
							return null;
						}
					}
				}
				else {
					List<PcRpdBoilerSuperheatT> getPcRpdBoilerSuperheatT = null;
					try {
						getPcRpdBoilerSuperheatT = boilerCrosslRDService.searchCross(rpdid,"","", jsonObj.get("REPORT_DATE").toString());//添加时存在同一天 同井的记录 报错
					} catch (Exception e) {
						e.printStackTrace();
						out.println("-13405");
						return null;
					}
					if (getPcRpdBoilerSuperheatT != null && getPcRpdBoilerSuperheatT.size() > 0) {
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
						if (!"".equals(argVal) && (i==1 || i==2 || i == 3 || i == 4 || i == 5 || i == 69 || i == 70 || i == 71)) {
							PropertyUtil.setProperty(prs, filedName[i], String.valueOf(argVal));
							continue;
						}
						else if (!"".equals(argVal) && i == 0) {
							PropertyUtil.setProperty(prs, filedName[i], StringUtil.parseDate("yyyy-MM-dd",argVal));
							continue;
						}
						if (!"".equals(argVal)) {
							PropertyUtil.setProperty(prs, filedName[i], java.math.BigDecimal.valueOf(Double.parseDouble(argVal)));
						}else{
							PropertyUtil.setProperty(prs, filedName[i], null);
						}
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
			}
				
		try {
			if (crossddid != null && !"".equals(crossddid)) {
				String blockStationName = boilerCrosslRDService.searchBlockName(prs.getBoilerName());
				prs.setBlockstationName(blockStationName);
				operFlag = boilerCrosslRDService.updatePsc(prs);
				//}
			} else {
				String blockStationName = boilerCrosslRDService.searchBlockName(prs.getBoilerName());
				prs.setBlockstationName(blockStationName);
				operFlag = boilerCrosslRDService.addPsc(prs);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (crossddid == null || "".equals(crossddid)) {
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
	}*/
	public String searchGGglview() throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		String rbid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("rbid")));
		String[] cloumnsName = {"BOILER_NAME","CJSJS","GLBH","GRZH","OILDRILLING_STATION_NAME","BLOCKSTATION_NAME","YXZ","AREABLOCK",
				"STEAMOUT_TEMP","STEAMIN_TEMP","EJECTSMOKE_TEMP","BURNER_TEMP","CSIN_PRESS","CSOUT_PRESS","CSIN_TEMP",
				"CSOUT_TEMP","SL_LEVEL","SUPERHEAT_TEMP","SUPERHEAT_PIEZORESISTANCE","SUPERHEATIN_TEMP","SUPERHEATOUT_TEMP","SUPERHEATIN_PRESS","SUPERHEATOUT_PRESS",
				"SUPERHEATIN_FLOW","HEARTH_PRESS","GAS1_PRESS","GAS2_PRESS","GAS3_PRESS","RS_TEMP","RS_DRYNESS","RS_PIEZORESISTANCE","RSIN_PRESS","RS_PRESS",
				"RSIN_TEMP","RSOUT_TEMP","PUMPA_FLOW","PUMPB_FLOW","PUMPC_FLOW","PUMPA_PRESS","PUMPB_PRESS","PUMPC_PRESS","FANA_ELECTRICITY","FANB_ELECTRICITY",
				"FANC_ELECTRICITY","PUMPFC_FREQUENCY","PUMPIN_PRESS","PUMPOUT_PRESS","PUMPIN_TEMP","PUMPOUT_TEMP","WATERSUPPLY_FLOW",
				"WATERSUPPLY_TOTAL","GAS_FLOW","GAS_TOTAL","STEAMINJECTION_TOTAL","DAILY_CUMULATIVE_WATER","DAILY_CUMULATIVE_GAS","DAILY_CUMULATIVE_STEAM"
				};
		List<Object[]> datas = new ArrayList<Object[]>();
		if(!"".equals(rbid)){
			try {
				datas  = srglRDService.searchggboilerRPDview(rbid,cloumnsName);
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
//					if("NO1CONTROL_STATUS".equals(cloumnsName[i])){
//						if("0".equals(datas.get(0)[i].toString())){
//							gridJson.put(cloumnsName[i],"手动");
//						}else{
//							gridJson.put(cloumnsName[i],"自动");
//						}
//					}else{
						gridJson.put(cloumnsName[i],datas.get(0)[i].toString());
//					}
					
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
	
	public String getline()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		
		String glid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("glid")));
		String prvarid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("prvarid")));
		
		String startDate = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("startDate")));
		String endDate = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("endDate")));
		
		List<Object[]> pointline = null;
		try {
			pointline = srglRDService.searchboilerLine(glid,prvarid,startDate,endDate);
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
				arr.add(obj);
//				gridJson.put("TUXINGS", pointline);
			}
			
		}
		 
		if("1".equals(outCode)){
			out.print(arr);
		}else{
			out.print(outCode);
		}
		return null;
	}
	
	
	public String getGGline()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		
		String glid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("glid")));
		String prvarid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("prvarid")));
		
		String startDate = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("startDate")));
		String endDate = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("endDate")));
		
		List<Object[]> pointline = null;
		try {
			pointline = srglRDService.searchGGboilerLine(glid,prvarid,startDate,endDate);
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
				arr.add(obj);
//				gridJson.put("TUXINGS", pointline);
			}
			
		}
		 
		if("1".equals(outCode)){
			out.print(arr);
		}else{
			out.print(outCode);
		}
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
		
		JSONObject json = srglRDService.searchBolierSupResults(areablock, blockstationname,boilersName,oilationname, startDate,endDate);
		if(json != null){
			out.println(json);
			out.flush();
			out.close();
		}
		
		return null;
	}
	
	
}
