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
import com.echo.dto.PcRdLoginfoT;
import com.echo.dto.PcRpdBoilerCommonT;
import com.echo.dto.User;
import com.echo.service.LogService;
import com.echo.service.SRGLRDService;
import com.echo.util.AuthorityUtil;
import com.echo.util.CommonsUtil;
import com.echo.util.DynamicDataExportUtil;
import com.echo.util.PageVariableUtil;
import com.echo.util.PropertyUtil;
import com.echo.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

public class BoilerCommondRPDAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SRGLRDService srglRDService;
	private InputStream excelFile = null;
	
	
	public void setSrglRDService(SRGLRDService srglRDService) {
		this.srglRDService = srglRDService;
	}
	private LogService logService;

	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	
	public String getFileName() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd ");
		String downloadFileName = (sf.format(new Date()).toString())+ "湿蒸汽锅炉日报.xls";
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
	public String searchSRGLRB() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String  oilname = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("oilationname")));
		String  areablock= StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("areablock")));
		String  stationNumber = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("blockstationname")));
		String group = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("group")));
		String boilersName = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("boilersName")));
		String stime=StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("date")));
		String etime=StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("date1")));
		String totalNum = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("totalNum")));
		String pageCode = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("pageCode")));
		String oilNmame = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("oilNmame")));
		if("".equals(stationNumber)  && "".equals(areablock) 
				 && "".equals(oilname) && "".equals(group) && "".equals(boilersName)
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
			dataMap = srglRDService.searchRPData(oilname,areablock,stationNumber,group,boilersName,stime,etime,oilNmame,pageCode,pageNo,sort,order,rowsPerpage,totalNum);
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
			java.io.ByteArrayOutputStream baos = DynamicDataExportUtil.dailyDataExporReport(dataLsit,templetFilePath,"湿蒸汽锅炉日报");
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
	
	public String searchPTglview() throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		String rbid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("rbid")));
		String[] cloumnsName = {"BLOCK_NAME","BOILER_NAME","REPORT_DATE","RUNTIME","GAS_INTO_PRESS","GAS1_PRESS","FIRE_MEASURE","GAS_FLOW",
				"PUMPIN_PRESS","PUMPOUT_TEMP","PUMPFC_FREQUENCY","WATERSUPPLY_FLOW","STEAMIN_TEMP","STEAMOUT_TEMP","STEAM_DRYNESS",
				"PUMPOUT_PRESS","CSIN_PRESS","CSIN_TEMP","CSOUT_PRESS","CSOUT_TEMP","CONVECTIONR_PRESS_REDUCTION","RSIN_PRESS","RSIN_TEMP",
				"RS_PRESS_REDUCTION","RS_TEMP","GAS2_PRESS","GAS3_PRESS","LUBE_PRESS","LUBE_TEMP","FAN_AIR_INTAKE_TEMP","BURNER_TEMP","HEARTH_PRESS","EJECTSMOKE_TEMP",
				"SYSTEM_VOLTAGE","PUMP_MOTOR_CURRENT","PUMP_MOTOR_TEMP","FAN_MOTOR_CURRENT",
				"DAILY_CUMULATIVE_GAS","DAILY_CUMULATIVE_STEAM","FUEL_GAS_DENSITY","H2S_DENSITY","STEAM_WORK_GROUP","OILDRILLING_STATION_NAME","STEAM_INJE_UNIT"};
		List<Object[]> datas = new ArrayList<Object[]>();
		if(!"".equals(rbid)){
			try {
				datas  = srglRDService.searchboilerRPDview(rbid,cloumnsName);
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
	
	//根据权限进行页面初始化
	@SuppressWarnings("unused")
	public String pageInit()throws Exception{
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		HttpServletRequest request=ServletActionContext.getRequest();
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		//根据用户权限生成用户权限生成页面布局格式
		User user = (User) session.getAttribute("userInfo");
		String	arg = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("arg")));	
		String gridJson = null;
		try {
			gridJson = AuthorityUtil.getGridJson("湿蒸汽锅炉日报", user, PageVariableUtil.SRGLCOMMONDWH_RPD_PAGE_GRID);
//			if ("rpd".equals(arg)) {
//				gridJson = AuthorityUtil.getGridJson("湿蒸汽锅炉日报维护", user, PageVariableUtil.SRGLCOMMOND_RPD_PAGE_GRID);
//			}
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
	
	
	public String pageInitSearchRPDWH()throws Exception{
		
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		
		//根据用户权限生成用户权限生成页面布局格式
		User user = (User) session.getAttribute("userInfo");
		//湿蒸汽锅炉日报维护数据
		String gridJson = AuthorityUtil.getGridJson("湿蒸汽锅炉日报维护", user, PageVariableUtil.SRGLCOMMOND_RPD_PAGE_GRID);
//		System.out.println(gridJson);
		out.print(gridJson);
		return null;
	}
	
	@SuppressWarnings("unused")
	public String SaveOrUpadateBoilerCommondRPDWH()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		List<PcRpdBoilerCommonT> pbcList = null;
		PcRpdBoilerCommonT ps = null;
		HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
		User user = (User) session.getAttribute("userInfo");
		JSONObject obj = new  JSONObject();
		String outCode = "1";
		boolean addflg = true;
		String commondid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("RPD_BOILER_COMMON_ID")));
		String boilerName  =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("BOILER_NAME")));
		//String boilerid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("BOILERID")));
		String reportDate = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("REPORT_DATE")));
		String  STEAM_WORK_GROUP= StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("STEAM_WORK_GROUP")));
		String  OILDRILLING_STATION_NAME= StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("OILDRILLING_STATION_NAME")));
		//String BLOCKSTATION_NAME = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("BLOCKSTATION_NAME")));
		String BLOCK_NAME  = StringUtil.toStr(StringUtil.toStr(request.getParameter("BLOCK_NAME")));
		String STEAM_INJE_UNIT = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("STEAM_INJE_UNIT")));


		if( reportDate !=null && !"".equals(reportDate)){
		}
		//修改
		if(commondid !=null && !"".equals(commondid)){
			try {
				pbcList = srglRDService.searchCommobs("" ,"", boilerName,reportDate );
				if(pbcList != null && pbcList.size()>0){
					if(pbcList.size() == 1){
						//要修改为本身数据运行修改
						if(commondid.equals(pbcList.get(0).getRpdBoilerCommonId())){
							ps = pbcList.get(0);
						//数据库中存在另一条该锅炉日报记录不许修改
						}else{
							obj = CommonsUtil.getRrturnJson("","锅炉名称："+boilerName+" -已存在请使用其他名称" , "",null, null);
							out.print(obj);
							return null;
							//outCode = "-14206";
						}
					//要修改数据数据库中存在多条
					}else if(pbcList.size() >1){
						obj = CommonsUtil.getRrturnJson("","锅炉名称："+boilerName+" -已存在请使用其他名称" , "",null, null);
						out.print(obj);
						return null;
						//outCode = "-14207";
					}
					
				//要修改数据，数据库中不存在允许修改
				}else{
					pbcList = srglRDService.searchCommobs(commondid ,"", "","" );
					ps = pbcList.get(0);
					
				}
			} catch (Exception e) {
				e.printStackTrace();
				//湿蒸汽锅炉日报维护信息获取失败
				outCode = "-14201";
			}

		//添加	
		}else{
			try {
				pbcList = srglRDService.searchCommobs("" ,"", boilerName,reportDate);
				//数据库中存在此记录不允许用户添加新纪录
				if(pbcList != null && pbcList.size()>0){
					//outCode = "-14208";
					obj = CommonsUtil.getRrturnJson("","锅炉名称："+boilerName+" -已存在请使用其他名称" , "",null, null);
					out.print(obj);
					return null;
				//数据库中存在记录不允许用户添加新纪录
				}else{
					ps = new PcRpdBoilerCommonT();
				}
			} catch (Exception e) {
				e.printStackTrace();
				//湿蒸汽锅炉日报维护信息获取失败
				outCode = "-14201";
			}
		}
		//if("1".equals(outCode)){
		//请求的long参数
		String[] longArgs = {
				"RUNTIME",
				"GAS_INTO_PRESS",
				"GAS1_PRESS",
				"FIRE_MEASURE",
				//"GAS_FLOW",
				"PUMPIN_PRESS",
				"PUMPOUT_TEMP",
				"PUMPFC_FREQUENCY",
				"WATERSUPPLY_FLOW",
				"STEAMIN_TEMP",
				"STEAMOUT_TEMP",
				"STEAM_DRYNESS",
				"PUMPOUT_PRESS",
				"CSIN_PRESS",
				"CSIN_TEMP",
				"CSOUT_PRESS",
				"CSOUT_TEMP",
				"CONVECTIONR_PRESS_REDUCTION",
				"RSIN_PRESS",
				"RSIN_TEMP",
				"RS_PRESS_REDUCTION",
				"RS_TEMP",
				"GAS2_PRESS",
				"GAS3_PRESS",
				"LUBE_PRESS",
				"LUBE_TEMP",
				"FAN_AIR_INTAKE_TEMP",
				"BURNER_TEMP",
				"HEARTH_PRESS",
				"EJECTSMOKE_TEMP",
				"SYSTEM_VOLTAGE",
				"PUMP_MOTOR_CURRENT",
				"PUMP_MOTOR_TEMP",
				"FAN_MOTOR_CURRENT",
				"FUEL_GAS_DENSITY",
				"H2S_DENSITY",
				"DAILY_CUMULATIVE_GAS",
				"DAILY_CUMULATIVE_STEAM",
				"sewageBufferTank"
			};
		String[] methodNames = {
				"runtime",
				"gasIntoPress",
				"gas1Press",
				"fireMeasure",
				//"gasFlow",
				"pumpinPress",
				"pumpoutTemp",
				"pumpfcFrequency",
				"watersupplyFlow",
				"steaminTemp",
				"steamoutTemp",
				"steamDryness",
				"pumpoutPress",
				"csinPress",
				"csinTemp",
				"csoutPress",
				"csoutTemp",
				"convectionrPressReduction",
				"rsinPress",
				"rsinTemp",
				"rsPressReduction",
				"rsTemp",
				"gas2Press",
				"gas3Press",
				"lubePress",	
				"lubeTemp",
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
				"dailyCumulativeSteam",
				"sewageBufferTank"
//				"centralHeatPress",
//				"hotblastTemp",
//				"pumpinTemp",
//				"leakHuntPress",
//				"gasTotal",
//				"dailyCumulativeWater",
//				"watersupplyTotal",
				};// 此行方法名称需修改
		for (int i = 0; i < longArgs.length; i++) {
			String argVal = StringUtil.isNullUtil(request.getParameter(longArgs[i]));
			//if (!"".equals(argVal)) {//如果长整型参数不为空 set值 
				try {
					PropertyUtil.setProperty(ps, methodNames[i],CommonsUtil.getBigDecimalData(argVal));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			//}
		}
//		if (!"".equals(BLOCK_NAME)) {
//			List<Object[]> stationList = srglRDService.searchArea(BLOCK_NAME);
//			if (stationList != null && stationList.size() > 0) {
//				if(!BLOCK_NAME.equals(stationList.get(0)[0].toString())){
//					BLOCK_NAME = stationList.get(0)[0].toString();
//				}
//			}else {
//				obj = CommonsUtil.getRrturnJson("","区块："+BLOCK_NAME+" -数据获取失败请重新选择区块" , "",null, null);
//				out.print(obj);
//				return null;
//			}
//		}
		ps.setBlockName(BLOCK_NAME);
		ps.setBoilerName(boilerName);
		
		String boilerid = srglRDService.serachBoilerid(boilerName);
		ps.setBoilerid(boilerid);
		
		//ps.setReportDate(DateBean.getStrDate(reportDate));
		ps.setReportDate(StringUtil.parseDate("yyyy-MM-dd",reportDate));
		ps.setSteamWorkGroup(STEAM_WORK_GROUP);
		ps.setOildrillingStationName(OILDRILLING_STATION_NAME);
		
		String blockStationName = srglRDService.searchBlockName(boilerName);
		ps.setBlockstationName(blockStationName);
		ps.setSteamInjeUnit(STEAM_INJE_UNIT);
		String  GAS_FLOW= StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("GAS_FLOW")));
		if(GAS_FLOW !=null && !"".equals(GAS_FLOW)){
			ps.setGasFlow(java.math.BigDecimal.valueOf(Double.parseDouble(GAS_FLOW)));
		}else{
			ps.setGasFlow(null);
		}
		String  REMARK = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("REMARK")));
				ps.setRemark(REMARK); 
				ps.setRlastOper(user.getOperName());
				ps.setRlastOdate(new Date());
				
				try {
					if(commondid != null && !"".equals(commondid)){
						addflg = srglRDService.updatePsgl(ps);
						
					}else{
						addflg = srglRDService.addPsgl(ps);
						
					}
				} catch (Exception e) {
					String  errmsg = e.getCause().getCause().toString();
					if(!"".equals(errmsg) && errmsg.indexOf("ORA-02291") != -1){
						obj = CommonsUtil.getRrturnJson("","违反完整约束条件 - 未找到父项关键字" ,"", null, null);
					} else{
						obj = CommonsUtil.getRrturnJson("",errmsg ,"", null, null);
						
					}
					if(!"".equals(errmsg) && errmsg.indexOf("ORA-01438") != -1){
						obj = CommonsUtil.getRrturnJson("","您输入的值大于为此列指定的允许精度的范围" ,"", null, null);
					} else{
						obj = CommonsUtil.getRrturnJson("",errmsg ,"", null, null);
						
					}
					
					e.printStackTrace();
					if(commondid != null && !"".equals(commondid)){
//						//湿蒸汽锅炉日报维护信息更新失败
//						obj = CommonsUtil.getRrturnJson("","湿蒸气锅炉："+boilerName+" -更新失败" , "",null, null);
//						out.print(obj);
//						return null;
						//outCode = "-14203";
					}else{
//						//湿蒸汽锅炉日报维护信息添加失败
						//outCode = "-14204";
//						obj = CommonsUtil.getRrturnJson("","湿蒸气锅炉："+boilerName+" -添加失败" , "",null, null);
//						out.print(obj);
//						return null;
					}
				}
			//}
	//	}
		out.print(obj);
		return null;
}
	public String removeBoilerCommondRP() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		//String sagddid = StringUtil.toStr(request.getParameter("sagddid"));
		String commondid = StringUtil.toStr(request.getParameter("commondid"));
		boolean deleteflg = false;
		try {
			deleteflg = srglRDService.removeBoilerCommondRP(commondid);
		} catch (Exception e) {
			//this.addFieldError("errermsg", "删除采油站信息失败~");
			e.printStackTrace();
			//湿蒸汽锅炉日报维护信息删除失败
			outCode = "-14211";
		}
		if(deleteflg){
			
			//添加系统LOG
			try {
				User user1 = (User) session.getAttribute("userInfo");
				PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "2", "湿蒸汽锅炉日报维护", commondid);
				logService.addLog(log);
			} catch (Exception e) {
				e.printStackTrace();
				outCode = "-10003";
			}
		}
		out.print(outCode);
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
		JSONObject json = srglRDService.searchSrglResults1(areablock, blockstationname,boilersName,oilationname, startDate,endDate);
		if(json != null){
			out.println(json);
			out.flush();
			out.close();
		}
		
		return null;
	}
	
	
}