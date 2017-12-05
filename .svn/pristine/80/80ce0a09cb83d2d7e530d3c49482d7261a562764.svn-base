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
import com.echo.dto.PcRdLoginfoT;
import com.echo.dto.User;
import com.echo.service.JzcjdxxService;
import com.echo.service.LogService;
import com.echo.util.AuthorityUtil;
import com.echo.util.CommonsUtil;
import com.echo.util.DateBean;
import com.echo.util.DynamicDataExportUtil;
import com.echo.util.PageVariableUtil;
import com.echo.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

public class JzcjdxxAction extends ActionSupport {
	private JzcjdxxService jzcjdxxService;
	private LogService logService;
	public void setJzcjdxxService(JzcjdxxService jzcjdxxService) {
		this.jzcjdxxService = jzcjdxxService;
	}
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
		String downloadFileName = (sf.format(new Date()).toString())+ "井站采集点信息.xls";
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
			gridJson = AuthorityUtil.getGridJson1("MENU066", user, PageVariableUtil.JZCJDXX_PAGE_GRID);
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
        
		String OBJECT_CODE_Q = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("OBJECT_CODE_Q")));
		String INSTRUMENT_CALLED_Q = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("INSTRUMENT_CALLED_Q")));
		String CONTROL_OR_Q = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("CONTROL_OR_Q")));
		
		String ACCESS_SIGNS_Q = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("ACCESS_SIGNS_Q")));
		String CONTROLLER_TYPE_Q = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("CONTROLLER_TYPE_Q")));
		String POINT_TYPE_Q = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("POINT_TYPE_Q")));
		String ALARM_OR_Q = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("ALARM_OR_Q")));
		String ALARM_CODE_Q = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("ALARM_CODE_Q")));
		String HISTORY_CURVE_Q = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("HISTORY_CURVE_Q")));
		
		String CREATE_DATE_Q = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("CREATE_DATE_Q")));
		String CREATE_DATE_QA = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("CREATE_DATE_QA")));
		String POINT_CODE_Q = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("POINT_CODE_Q")));
		String totalNum = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("totalNum")));

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
			dataMap = jzcjdxxService.searchData(OBJECT_CODE_Q,INSTRUMENT_CALLED_Q,CONTROL_OR_Q,
					ACCESS_SIGNS_Q,CONTROLLER_TYPE_Q,POINT_TYPE_Q,ALARM_OR_Q,ALARM_CODE_Q,HISTORY_CURVE_Q,
					CREATE_DATE_Q,CREATE_DATE_QA,POINT_CODE_Q,
					pageNo,sort,order,rowsPerpage,totalNum);
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
			String templetFilePath = request.getSession().getServletContext().getRealPath("/") + "exceltemplet\\系统管理报表.xls";
//			将用户对象列表输出到workbook的流中，ExcelUtil类进行了封装，后续分析！                                                              
			java.io.ByteArrayOutputStream baos = DynamicDataExportUtil.systemExporReport(dataLsit,templetFilePath,"井站采集点信息");
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
	
	public String saveorUpdateData()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		User user = (User) session.getAttribute("userInfo");
		JSONObject  obj = new JSONObject();
		String  SMALL_STATION_ID= StringUtil.toStr(StringUtil.isNullStr(request.getParameter("SMALL_STATION_ID")));
		String  OBJECT_CODE= StringUtil.toStr(StringUtil.isNullStr(request.getParameter("OBJECT_CODE")));
		if(OBJECT_CODE ==null &&  "".equals(OBJECT_CODE)){
			 obj = CommonsUtil.getRrturnJson("","对象类型不能为空" , "",null, null);
				out.print(obj);
				return null;
		}
		String  POINT_NAME= StringUtil.toStr(StringUtil.isNullStr(request.getParameter("POINT_NAME")));
		String  INTERFACE_REMARK= StringUtil.toStr(StringUtil.isNullStr(request.getParameter("INTERFACE_REMARK")));
		String  ACCESS_SIGNS= StringUtil.toStr(StringUtil.isNullStr(request.getParameter("ACCESS_SIGNS")));
		String  POINT_TYPE= StringUtil.toStr(StringUtil.isNullStr(request.getParameter("POINT_TYPE")));
		String  RANGES_UPPER= StringUtil.toStr(StringUtil.isNullStr(request.getParameter("RANGES_UPPER")));
		String  RANGES_DOWN= StringUtil.toStr(StringUtil.isNullStr(request.getParameter("RANGES_DOWN")));
		String  IFIX_UNIT= StringUtil.toStr(StringUtil.isNullStr(request.getParameter("IFIX_UNIT")));
		String  CONTROL_OR= StringUtil.toStr(StringUtil.isNullStr(request.getParameter("CONTROL_OR")));
		String  HISTORY_CURVE= StringUtil.toStr(StringUtil.isNullStr(request.getParameter("HISTORY_CURVE")));
		String  ORACLE_SAVE= StringUtil.toStr(StringUtil.isNullStr(request.getParameter("ORACLE_SAVE")));
//		if(TAG_NAME ==null &&  "".equals(TAG_NAME)){
//			 obj = CommonsUtil.getRrturnJson("","标签名不能为空" , "",null, null);
//				out.print(obj);
//				return null;
//		}
		String  ALARM_OR= StringUtil.toStr(StringUtil.isNullStr(request.getParameter("ALARM_OR")));
		String  ALARM_VALUE= StringUtil.toStr(StringUtil.isNullStr(request.getParameter("ALARM_VALUE")));
		String  ALARM_LIMITLL= StringUtil.toStr(StringUtil.isNullStr(request.getParameter("ALARM_LIMITLL")));
		String  ALARM_LIMITL= StringUtil.toStr(StringUtil.isNullStr(request.getParameter("ALARM_LIMITL")));
		String  ALARM_LIMITH= StringUtil.toStr(StringUtil.isNullStr(request.getParameter("ALARM_LIMITH")));
		String  ALARM_LIMITHH= StringUtil.toStr(StringUtil.isNullStr(request.getParameter("ALARM_LIMITHH")));
		//
		String  REMARK= StringUtil.toStr(StringUtil.isNullStr(request.getParameter("REMARK")));
		String  JRKZQ= StringUtil.toStr(StringUtil.isNullStr(request.getParameter("JRKZQ")));
		
		String DATA_TYPE =  StringUtil.toStr(StringUtil.isNullStr(request.getParameter("DATA_TYPE")));
		String PLC_ADDRESS =  StringUtil.toStr(StringUtil.isNullStr(request.getParameter("PLC_ADDRESS")));
		
		String POINT_DESC =  StringUtil.toStr(StringUtil.isNullStr(request.getParameter("POINT_DESC")));
		String POINT_REMARK =  StringUtil.toStr(StringUtil.isNullStr(request.getParameter("POINT_REMARK")));
		String VALUE_RATIO =  StringUtil.toStr(StringUtil.isNullStr(request.getParameter("VALUE_RATIO")));
		String MAILING_ADDRESS =  StringUtil.toStr(StringUtil.isNullStr(request.getParameter("MAILING_ADDRESS")));
		String MAILING_ADDRESSB =  StringUtil.toStr(StringUtil.isNullStr(request.getParameter("MAILING_ADDRESSB")));
		String POINT_CODE =  StringUtil.toStr(StringUtil.isNullStr(request.getParameter("POINT_CODE")));
		
		if(POINT_CODE ==null ||  "".equals(POINT_CODE)){
			 obj = CommonsUtil.getRrturnJson("","点代码不能为空" , "",null, null);
				out.print(obj);
				return null;
		}
		
		String YCGLSB =  StringUtil.toStr(StringUtil.isNullStr(request.getParameter("YCGLSB")));
		String DATA_TYPE2 =  StringUtil.toStr(StringUtil.isNullStr(request.getParameter("DATA_TYPE2")));
		String PLC_ADDRESS2 = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("PLC_ADDRESS2")));
		String POINT_DESC2 =  StringUtil.toStr(StringUtil.isNullStr(request.getParameter("POINT_DESC2")));
		String POINT_REMARK2 =  StringUtil.toStr(StringUtil.isNullStr(request.getParameter("POINT_REMARK2")));
		String VALUE_RATIO2 =  StringUtil.toStr(StringUtil.isNullStr(request.getParameter("VALUE_RATIO2")));
		String MAILING_ADDRESS2 =  StringUtil.toStr(StringUtil.isNullStr(request.getParameter("MAILING_ADDRESS2")));
		String MAILING_ADDRESSB2 =  StringUtil.toStr(StringUtil.isNullStr(request.getParameter("MAILING_ADDRESSB2")));
		List<PcCdSmallStationT>  smList = null;
		PcCdSmallStationT sm = null;
		
		if(SMALL_STATION_ID !=null && !"".equals(SMALL_STATION_ID)){
			 try {
				 smList = jzcjdxxService.searchCheckD(SMALL_STATION_ID,"","");
				 //smList = jzcjdxxService.searchCheckD("",TAG_NAME);
			} catch (Exception e) {
				e.printStackTrace();
				 obj = CommonsUtil.getRrturnJson("","井站采集点信息管理ID获取失败" , "",null, null);
					out.print(obj);
					return null;
			}
			if(smList !=null &&  smList.size()>0){
				if(smList.size()==1 && smList.get(0).getSmallStationId().equals(SMALL_STATION_ID) ){
					sm = smList.get(0);
				}else{
					obj = CommonsUtil.getRrturnJson("","数据存在多条不许修改" , "",null, null);
					out.print(obj);
					return null;
				}
			}else{
				smList = jzcjdxxService.searchCheckD("",POINT_CODE,OBJECT_CODE);
				if(smList !=null  &&  smList.size()>0){
					 obj = CommonsUtil.getRrturnJson("","对象类型+标签名："+POINT_CODE+" -已存在请使用其他标签名称" , "",null, null);
						out.print(obj);
						return null;
				}
				sm = new PcCdSmallStationT();
			}
		}else{
			smList = jzcjdxxService.searchCheckD("",POINT_CODE,OBJECT_CODE);
			if(smList !=null  &&  smList.size()>0){
				 obj = CommonsUtil.getRrturnJson("","对象类型+标签名："+POINT_CODE+" -已存在请使用其他标签名称" , "",null, null);
					out.print(obj);
					return null;
			}
			sm = new PcCdSmallStationT();
		}
		try {
			smList = jzcjdxxService.searchCheckD("",POINT_CODE,OBJECT_CODE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(smList !=null  &&  smList.size()>0){
			if(smList.size()==1 && sm.getPointCode().equals(POINT_CODE) && sm.getObjectCode().equals(OBJECT_CODE)){
				
			}else{
			 obj = CommonsUtil.getRrturnJson("","对象类型+标签名："+POINT_CODE+" -已存在请使用其他标签名称" , "",null, null);
				out.print(obj);
				return null;
			}
		}
		sm.setObjectCode(OBJECT_CODE);
		sm.setPointName(POINT_NAME);
		sm.setInterfaceRemark(INTERFACE_REMARK);
		//sm.setAccessSigns(ACCESS_SIGNS);
		if(ACCESS_SIGNS.equals("是")){
			sm.setAccessSigns("0");
		}else  if(ACCESS_SIGNS.equals("否")){
			sm.setAccessSigns("1");
		}else{
			sm.setAccessSigns("");
		}
		sm.setPointType(POINT_TYPE);
		sm.setRangesUpper(RANGES_UPPER);
		sm.setRangesDown(RANGES_DOWN);
		sm.setIfixUnit(IFIX_UNIT);
		//sm.setControlOr(CONTROL_OR);
		
		if(CONTROL_OR.equals("是")){
			sm.setControlOr("0");
		}else  if(CONTROL_OR.equals("否")){
			sm.setControlOr("1");
		}else{
			sm.setControlOr("");
		}
		
		if(HISTORY_CURVE.equals("是")){
			sm.setHistoryCurve("0");
		}else  if(HISTORY_CURVE.equals("否")){
			sm.setHistoryCurve("1");
		}else{
			sm.setHistoryCurve("");
		}
		
		if(ORACLE_SAVE.equals("是")){
			sm.setOracleSave("0");
		}else  if(ORACLE_SAVE.equals("否")){
			sm.setOracleSave("1");
		}else{
			sm.setOracleSave("");
		};
		
		if(ALARM_OR.equals("是")){
			sm.setAlarmOr("0");
		}else  if(ALARM_OR.equals("否")){
			sm.setAlarmOr("1");
		}else{
			sm.setAlarmOr("");
		}
		sm.setAlarmValue(ALARM_VALUE);
		sm.setAlarmLimitll(ALARM_LIMITLL);
		sm.setAlarmLimitl(ALARM_LIMITL);
		sm.setAlarmLimith(ALARM_LIMITH);
		sm.setAlarmLimithh(ALARM_LIMITHH);
		sm.setRemark(REMARK);
		sm.setTortuCode(JRKZQ);
		sm.setDataType(DATA_TYPE);
		sm.setPlcAddress(PLC_ADDRESS);
		sm.setPointDesc(POINT_DESC);
		sm.setPointRemark(POINT_REMARK);
		sm.setValueRatio(VALUE_RATIO);
		sm.setMailingAddress(MAILING_ADDRESS);
		sm.setMailingAddressb(MAILING_ADDRESSB);
		sm.setPointCode(POINT_CODE);
		sm.setInstrumentCalled(YCGLSB);
		sm.setDataType2(DATA_TYPE2);
		sm.setPlcAddress2(PLC_ADDRESS2);
		sm.setPointDesc2(POINT_DESC2);
		sm.setPointRemark2(POINT_REMARK2);
		sm.setValueRatio2(VALUE_RATIO2);
		sm.setMailingAddress2(MAILING_ADDRESS2);
		sm.setMailingAddressb2(MAILING_ADDRESSB2);
		
		
		sm.setRlastOper(user.getOperName());
		sm.setRlastOdate(new Date());
		String  createDate = DateBean.getSystemTime1();
		sm.setCreateDate(DateBean.getStrDate(createDate));
		boolean flag = false;
		try {
				flag = jzcjdxxService.saveData(sm);
			} catch (Exception e) {
				
				String errmsg = e.getCause().getCause().toString();
				if(!"".equals(errmsg) && errmsg.indexOf("ORA-02291") != -1 ){
					obj = CommonsUtil.getRrturnJson("","违反完整约束条件" ,"", null, null);
				}else{
					obj = CommonsUtil.getRrturnJson("",errmsg ,"", null, null);
				}
				e.printStackTrace();
			}
			
			//添加日志
			if(SMALL_STATION_ID !=null && !"".equals(SMALL_STATION_ID)){
		  			try {
						User user1 = (User) session.getAttribute("userInfo");
						PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "3", "井站",sm.getSmallStationId());
						logService.addLog(log);
					} catch (Exception e) {
						e.printStackTrace();
						obj = CommonsUtil.getRrturnJson("", "井站采集点添加日志失败","", null, null);
		  				out.print(obj);
		  				return null;
					}
				}else{
					try {
						User user1 = (User) session.getAttribute("userInfo");
						PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "1", "井站", sm.getSmallStationId());
						logService.addLog(log);
					} catch (Exception e) {
						e.printStackTrace();
						obj = CommonsUtil.getRrturnJson("", "井站采集点修改日志失败","", null, null);
		  				out.print(obj);
		  				return null;
					}
				}
			out.print(obj);
			return null;
		}
	
	public String cascadeInit() throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONObject   objLis = new JSONObject();
		try {
			objLis = jzcjdxxService.searchCascadeInit();
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
			delFlag = jzcjdxxService.deleteData(SMALL_STATION_ID);
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
	public String searchOnChange()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONArray   objList = null;
		String   qxlxId = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("qxlxId")));
		 try {
			 objList = jzcjdxxService.searchOnChangeData(qxlxId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(objList !=null && objList.size()>0){
			out.print(objList);
		}else{
			out.print("");
		}
		return  null;
	}
	
	public String OnChangeData() throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONObject   objLis = new JSONObject();
		String queID = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("queID")));
		String editId = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("editId")));
		try {
			objLis = jzcjdxxService.searchOnChangeData(queID,editId);
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
	public String OnChangeDataEdit() throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONObject   objLis = new JSONObject();
		String editId = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("editId")));
		try {
			objLis = jzcjdxxService.searchOnChangeDataEdit(editId);
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
}
