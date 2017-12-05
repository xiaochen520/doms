package com.echo.action;

import java.io.ByteArrayInputStream;
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

import com.echo.dto.PcCdSmallStationT;
import com.echo.dto.PcCdStationPointT;
import com.echo.dto.PcRdLoginfoT;
import com.echo.dto.User;
import com.echo.service.JzcjdxxService;
import com.echo.service.LogService;
import com.echo.service.ZkcjdService;
import com.echo.util.AuthorityUtil;
import com.echo.util.CommonsUtil;
import com.echo.util.DateBean;
import com.echo.util.DynamicDataExportUtil;
import com.echo.util.PageVariableUtil;
import com.echo.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

public class ZkcjdAction extends ActionSupport{
	private ZkcjdService zkcjdService;

	public void setZkcjdService(ZkcjdService zkcjdService) {
		this.zkcjdService = zkcjdService;
	}
	private LogService logService;

	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	private InputStream excelFile = null;
	
	public InputStream getExcelFile() {
		return excelFile;
	}

	public void setExcelFile(InputStream excelFile) {
		this.excelFile = excelFile;
	}
	HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
	public String getFileName() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd ");
		String downloadFileName = (sf.format(new Date()).toString())+ "站库采集点.xls";
		try {
			downloadFileName = new String(downloadFileName.getBytes(),"ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}	
		return downloadFileName;
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
		String gridJson = null;
		try { 
			gridJson = AuthorityUtil.getGridJson1("MENU065", user, PageVariableUtil.ZKCJD_PAGE_GRID);
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
	
	public String searchDatas()throws Exception{

		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
        
		String SYSTEM_CODE_Q = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("SYSTEM_CODE_Q")));
		String POINT_TYPE_Q = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("POINT_TYPE_Q")));
		String CREATE_DATE_Q = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("CREATE_DATE_Q")));
		String FLOW_CODE_Q = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("FLOW_CODE_Q")));
		String ALARM_OR_Q = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("ALARM_OR_Q")));
		String ALARM_CODE_Q = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("ALARM_CODE_Q")));
		String DEVICE_CODE_Q = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("DEVICE_CODE_Q")));
		String totalNum = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("totalNum")));
		String ptable = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("ptable")));

		int pageNo = 1;  //页索引参数名当前页
		String sort = "";  //页排序列名
		String order = ""; //页排序方向
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
			dataMap = zkcjdService.searchData(SYSTEM_CODE_Q,POINT_TYPE_Q,CREATE_DATE_Q,FLOW_CODE_Q,ALARM_OR_Q,ALARM_CODE_Q,DEVICE_CODE_Q,pageNo,sort,order,rowsPerpage,totalNum,ptable);
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
		//返回条数点表生成
		if ("ptable".equals(ptable)) {
			Object total = dataMap.get("ptable");
			out.println(total);
			out.flush();
			out.close();
			return null;
		}
		Object jsonobj = dataMap.get("json");
		//返回显示数据
			if(jsonobj != null && "".equals(totalNum) && "".equals(ptable)){
				out.println(jsonobj);
				out.flush();
				out.close();
			}else  
				if ("Eptable".equals(ptable)){
				if(jsonobj != null && "".equals(ptable)){
					out.println(jsonobj);
					out.flush();
					out.close();
				} 
				else {//导出报表
					response.resetBuffer();
					response.reset();
					List<Object[]> dataLsit = (List<Object[]>) dataMap.get("list");
					String templetFilePath = request.getSession().getServletContext().getRealPath("/") + "exceltemplet\\点表生成.xls";
		//			将用户对象列表输出到workbook的流中，ExcelUtil类进行了封装，后续分析！                                                              
					java.io.ByteArrayOutputStream baos = DynamicDataExportUtil.pTableExporReport(dataLsit,templetFilePath,"站库采集点点表生成");
					if(baos != null){
						byte[] ba = baos.toByteArray();
						excelFile = new ByteArrayInputStream(ba);
						baos.flush();
						baos.close();
					}
					return "excel";
				}
			}
			else {//导出报表
				response.resetBuffer();
				response.reset();
				List<Object[]> dataLsit = (List<Object[]>) dataMap.get("list");
				String templetFilePath = request.getSession().getServletContext().getRealPath("/") + "exceltemplet\\系统管理报表.xls";
	//			将用户对象列表输出到workbook的流中，ExcelUtil类进行了封装，后续分析！                                                              
				java.io.ByteArrayOutputStream baos = DynamicDataExportUtil.systemExporReport(dataLsit,templetFilePath,"站库采集点");
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
	
	public String removeData()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONObject   obj = new JSONObject();
		
		String  SMALL_STATION_ID  = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("SMALL_STATION_ID")));
		
		boolean delFlag = false;
		try {
			delFlag = zkcjdService.deleteData(SMALL_STATION_ID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(delFlag){
			//添加系统LOG
			try {
				User user1 = (User) session.getAttribute("userInfo");
				PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "2", "井站", SMALL_STATION_ID);
				logService.addLog(log);
			} catch (Exception e) {
				e.printStackTrace();
				obj = CommonsUtil.getRrturnJson("-10003", "","", null, null);
			}
			
			
		}
		out.print(obj);
		return  null;
	}
	public String cascadeInit() throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONObject   objLis = new JSONObject();
		try {
			objLis = zkcjdService.searchCascadeInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(objLis !=null && objLis.size()>0){
			out.print(objLis);
		}else{
			out.print("");
		}
		return null;
		
	}
	public String searchOnChange() throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONArray   objLis = new JSONArray();
		String code = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("code")));
		try {
			objLis = zkcjdService.searchOnChange(code);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(objLis !=null && objLis.size()>0){
			out.print(objLis);
		}else{
			out.print("");
		}
		return null;
		
	}
	//流程
	public String searchOnChangeLC() throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONArray   objLis = new JSONArray();
		String code = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("code")));
		try {
			objLis = zkcjdService.searchOnChangeLC(code);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(objLis !=null && objLis.size()>0){
			out.print(objLis);
		}else{
			out.print("");
		}
		return null;
		
	}
	//流程编辑
	public String searchOnChangeLCeDIT() throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONArray   objLis = new JSONArray();
		String code = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("code")));
		try {
			objLis = zkcjdService.searchOnChangeLCeDIT(code);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(objLis !=null && objLis.size()>0){
			out.print(objLis);
		}else{
			out.print("");
		}
		return null;
		
	}
	public  String saveorUpdateData()throws  Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONObject  obj  =new  JSONObject();
		User user = (User) session.getAttribute("userInfo");
		String SYSTEM_CODE = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("SYSTEM_CODE")));
		String TAG_NAME= StringUtil.toStr(StringUtil.isNullStr(request.getParameter("TAG_NAME")));
		String REMARK = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("REMARK")));
		String POINT_TYPE = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("POINT_TYPE")));
		String UNIT = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("UNIT")));
		String FLOW_CODE = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("FLOW_CODE")));
		String RANGES_DOWN = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("RANGES_DOWN")));
		String RANGES_UPPER = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("RANGES_UPPER")));
		String ALARM_LIMITLL = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("ALARM_LIMITLL")));
		String ALARM_LIMITL = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("ALARM_LIMITL")));
		String ALARM_LIMITH = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("ALARM_LIMITH")));
		String ALARM_LIMITHH = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("ALARM_LIMITHH")));
		String ALARM_OR = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("ALARM_OR")));
		String ALARM_CODE = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("ALARM_CODE")));
		String DEVICE_CODE = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("DEVICE_CODE")));
		String HISTORY_CURVE= StringUtil.toStr(StringUtil.isNullStr(request.getParameter("HISTORY_CURVE")));
		String ORACLE_SAVE = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("ORACLE_SAVE")));
		String STATION_POINT_ID = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("STATION_POINT_ID")));
		
		String BEIZHU = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("BEIZHU")));
		String CONTROL_OR = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("CONTROL_OR")));
		String ACCESS_SIGNS = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("ACCESS_SIGNS")));

		List<PcCdStationPointT> spList = null;
		PcCdStationPointT sp = null;
		if(STATION_POINT_ID !=null  && !"".equals(STATION_POINT_ID)){
			try {
				spList = zkcjdService.searchCheckD(STATION_POINT_ID ,"");
			} catch (Exception e) {
				 obj = CommonsUtil.getRrturnJson("","表ID获取失败" , "",null, null);
					out.print(obj);
					return null;
			}
			if( spList !=null &&  spList.size()>0){
				if(spList.size() ==1 && spList.get(0).getStationPointId().equals(STATION_POINT_ID) ){
					sp = spList.get(0);
				}else{
					obj = CommonsUtil.getRrturnJson("","数据存在多条不许修改" , "",null, null);
					out.print(obj);
					return null;
				}
			}else{
				spList = zkcjdService.searchCheckD("" ,TAG_NAME);
				if(spList !=null  &&  spList.size()>0){
					 obj = CommonsUtil.getRrturnJson("","标签名："+TAG_NAME+" -已存在请使用其他标签名称" , "",null, null);
						out.print(obj);
						return null;
				}
				sp = new PcCdStationPointT();
			}
		}else{
			spList = zkcjdService.searchCheckD("" ,TAG_NAME);
			if(spList !=null  &&  spList.size()>0){
				 obj = CommonsUtil.getRrturnJson("","标签名："+TAG_NAME+" -已存在请使用其他标签名称" , "",null, null);
					out.print(obj);
					return null;
			}
			sp = new PcCdStationPointT();
		}
		try {
			spList = zkcjdService.searchCheckD("" ,TAG_NAME);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(spList !=null  && spList.size()>0){
			if(spList.size() ==1 &&  sp.getTagName().equals(TAG_NAME)){
				
			}else{
				 obj = CommonsUtil.getRrturnJson("","标签名："+TAG_NAME+" -已存在请使用其他标签名称" , "",null, null);
					out.print(obj);
					return null;
			}
		}
			sp.setSystemCode(SYSTEM_CODE);
			sp.setTagName(TAG_NAME);
			sp.setRemark(REMARK);
			sp.setPointType(POINT_TYPE);
			sp.setUnit(UNIT);
			String  createDate = DateBean.getSystemTime1();
			sp.setCreateDate(DateBean.getStrDate(createDate));
			sp.setFlowCode(FLOW_CODE);
			sp.setAlarmCode(ALARM_CODE);
			sp.setDeviceCode(DEVICE_CODE);
			sp.setRangesDown(CommonsUtil.getBigDecimalData(CommonsUtil.getNotTwoData(RANGES_DOWN)));
			sp.setRangesUpper(CommonsUtil.getBigDecimalData(CommonsUtil.getNotTwoData(RANGES_UPPER)));
			sp.setAlarmLimitll(CommonsUtil.getBigDecimalData(CommonsUtil.getNotTwoData(ALARM_LIMITLL)));
			sp.setAlarmLimitl(CommonsUtil.getBigDecimalData(CommonsUtil.getNotTwoData(ALARM_LIMITL)));
			sp.setAlarmLimith(CommonsUtil.getBigDecimalData(CommonsUtil.getNotTwoData(ALARM_LIMITH)));
			sp.setAlarmLimithh(CommonsUtil.getBigDecimalData(CommonsUtil.getNotTwoData(ALARM_LIMITHH)));
			
			if(ALARM_OR.equals("是")){
				sp.setAlarmOr("0");
			}else  if(ALARM_OR.equals("否")){
				sp.setAlarmOr("1");
			}else{
				sp.setAlarmOr("");
			}
			
			if(HISTORY_CURVE.equals("是")){
				sp.setHistoryCurve("0");
			}else  if(HISTORY_CURVE.equals("否")){
				sp.setHistoryCurve("1");
			}else{
				sp.setHistoryCurve("");
			}
			if(ORACLE_SAVE.equals("是")){
				sp.setOracleSave("0");
			}else  if(ORACLE_SAVE.equals("否")){
				sp.setOracleSave("1");
			}else{
				sp.setOracleSave("");
			}
			// "BEIZHU":BEIZHU,"CONTROL_OR":CONTROL_OR, "":ACCESS_SIGNS
			if(CONTROL_OR.equals("是")){
				sp.setControlOr("0");
			}else if(CONTROL_OR.equals("否")){
				sp.setControlOr("1");
			}else{
				sp.setControlOr("");
			}
			if(ACCESS_SIGNS.equals("是")){
				sp.setAccessSigns("0");
			}else if(ACCESS_SIGNS.equals("否")){
				sp.setAccessSigns("1");
			}else{
				sp.setAccessSigns("");
			}
			sp.setBeizhu(BEIZHU);
			sp.setRlastOper(user.getOperName());
			sp.setRlastOdate(new Date());
		
		boolean flag = false;
		try {
				flag = zkcjdService.saveData(sp);
			} catch (Exception e) {
				e.printStackTrace();
				String errmsg = e.getCause().getCause().toString();
				if(!"".equals(errmsg) && errmsg.indexOf("ORA-01400") != -1){
					obj = CommonsUtil.getRrturnJson("","无法将 NULL 插入" , "",null, null);
					out.print(obj);
	  				return null;
				} else  if (!"".equals(errmsg) && errmsg.indexOf("ORA-02291") != -1){
					obj = CommonsUtil.getRrturnJson("","违反完整约束条件- 未找到父项关键字" , "",null, null);
					out.print(obj);
					return null;
			}else{
					obj = CommonsUtil.getRrturnJson("",errmsg ,"", null, null);
					out.print(obj);
	  				return null;
				}
				
			}
			
			//添加日志
			if(STATION_POINT_ID !=null && !"".equals(STATION_POINT_ID)){
		  			try {
						User user1 = (User) session.getAttribute("userInfo");
						PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "3", "站库",sp.getStationPointId());
						logService.addLog(log);
					} catch (Exception e) {
						e.printStackTrace();
						obj = CommonsUtil.getRrturnJson("", "站库采集点添加日志失败","", null, null);
		  				out.print(obj);
		  				return null;
					}
				}else{
					try {
						User user1 = (User) session.getAttribute("userInfo");
						PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "1", "站库", sp.getStationPointId());
						logService.addLog(log);
					} catch (Exception e) {
						e.printStackTrace();
						obj = CommonsUtil.getRrturnJson("", "站库采集点修改日志失败","", null, null);
		  				out.print(obj);
		  				return null;
					}
				}
			out.print(obj);
			return null;
	}
	
}
