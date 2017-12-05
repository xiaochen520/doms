package com.echo.action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.hssf.converter.ExcelToHtmlUtils;
import org.apache.poi.hssf.record.PageBreakRecord.Break;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.junit.runner.Request;

import com.echo.dto.PcCdInstruMentationDT;
import com.echo.dto.PcRdLoginfoT;
import com.echo.dto.PcCdInstruMentationT;
import com.echo.dto.User;
import com.echo.service.InstruMentationService;
import com.echo.service.LogService;
import com.echo.util.AuthorityUtil;
import com.echo.util.CommonsUtil;
import com.echo.util.DateBean;
import com.echo.util.DynamicDataExportUtil;
import com.echo.util.PageVariableUtil;
import com.echo.util.PropertiesConfig;
import com.echo.util.StringUtil;
import com.echo.util.U2DataExportUtil;
import com.opensymphony.xwork2.ActionSupport;
public class InstruMentationAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private InstruMentationService instruMentationService;
	
	private LogService logService;
	
	private InputStream excelFile = null;
	
	public InputStream getExcelFile() {
		return excelFile;
	}
	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	
	HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
	public InstruMentationService getInstruMentationService() {
		return instruMentationService;
	}

	public void setInstruMentationService(InstruMentationService instruMentationService) {
		this.instruMentationService = instruMentationService;
	}
	
	public String getFileName() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd ");
		String downloadFileName = (sf.format(new Date()).toString())+ "仪表.xls";
		try {
			downloadFileName = new String(downloadFileName.getBytes(),"ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}	
		return downloadFileName;
	}
	
	public String searchInstru() throws IOException {

		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
        
		String  oilName = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("oilName")));
		String  objectCode = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("objectCode")));
		String  areaName = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("areaName")));
		String  staName = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("staName")));
		String  ownObject = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("ownObject")));
		String  INSTRU_1TN = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("INSTRU_1TN")));
		String  INSTRU_CLN = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("INSTRU_CLN")));
		String  FACTORY_NS = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("FACTORY_NS")));
		String  INSTRU_C3N = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("INSTRU_C3N")));
		String  INSTRU_STNS = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("INSTRU_STNS")));
		String  superStns = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("superStns")));
		String  txtDate = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("txtDate")));
		String  txtDate1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("txtDate1")));
		
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
			dataMap = instruMentationService.searchInstru(oilName,objectCode,areaName,staName,
					ownObject,INSTRU_1TN,INSTRU_CLN,FACTORY_NS,INSTRU_C3N,INSTRU_STNS,
					superStns,txtDate,txtDate1,
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
			java.io.ByteArrayOutputStream baos = DynamicDataExportUtil.systemExporReport(dataLsit,templetFilePath,"仪表设备");
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
	
	public String pageInit()throws Exception{
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		
		//根据用户权限生成用户权限生成页面布局格式
		User user = (User) session.getAttribute("userInfo");
//		
		String gridJson;
		try {
			//仪表设备信息
			gridJson = AuthorityUtil.getGridJson("仪表设备信息", user, PageVariableUtil.INSTRU_MENTATION_PAGE_GRID);
		} catch (Exception e) {
			e.printStackTrace();
			this.addFieldError("errermsg", "系统菜单无法初始化~ 请联系管理员");
			return "fail";
		}
		out.print(gridJson);
		return null;
	}
	
	public String cascadeInit() throws IOException {
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONObject jsonArr = null;
		try {
			jsonArr = instruMentationService.cascadeInit();
		} catch (Exception e) {
			this.addFieldError("errermsg", "级联菜单初始化失败~");
			e.printStackTrace();
		}
		if(jsonArr != null){
			out.println(jsonArr);
		}else{
			out.println("");
		}
		return null;
	}
	
	public String searchOnChangeArea() throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONArray jsonArr  =null ;
		String oilName = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("oilName")));
		String objectCode = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("objectCode")));
		String areaName = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("areaName")));
		String args = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("args")));
		try {
			jsonArr = instruMentationService.searchOnChangeArea(oilName,objectCode,areaName,args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(jsonArr !=null){
			out.print( jsonArr);
		}else {
			out.print("");
		}
	 return null;
	}
	public String searchOnChangeOwnObject() throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONArray jsonArr  =null ;
		String oilName = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("oilName")));
		String objectCode = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("objectCode")));
		String areaName = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("areaName")));
		String staName = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("staName")));
		String args = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("args")));
		try {
			jsonArr = instruMentationService.searchOnChangeOwnObject(oilName,objectCode,areaName,staName,args);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if(jsonArr !=null){
			out.print( jsonArr);
		}else {
			out.print("");
		}
		return null;
	}
	public  String searchOnChangeInstru() throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONArray jsonArr  =null ;
		String  objectCode = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("objectCode")));
		String  INSTRU_1TN = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("INSTRU_1TN")));
		try {
			jsonArr = instruMentationService.searchOnChangeInstru(objectCode,INSTRU_1TN);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if(jsonArr !=null){
			out.print( jsonArr);
		}else {
			out.print("");
		}
		return null;
	}
	public String searchOnChangeFactoryns() throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONArray jsonArr  =null ;
		String  INSTRU_CLN = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("INSTRU_CLN")));
		try {
			jsonArr = instruMentationService.searchOnChangeFactoryns(INSTRU_CLN);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if(jsonArr !=null){
			out.print( jsonArr);
		}else {
			out.print("");
		}
		return null;
	}
	
	public String searchOnChangeInstruc3n()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String INSTRU_CLN = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("INSTRU_CLN")));
		String FACTORY_NS = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("FACTORY_NS")));
		JSONArray jsonArr = null;
		try {
			jsonArr = instruMentationService.searchOnChangeInstruc3n(INSTRU_CLN,FACTORY_NS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(jsonArr != null){
			out.println(jsonArr);
		}else{
			out.println("");
		}
			return null;
		}
	
	public String searchOnChangeSuperStns()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String objectCode = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("objectCode")));
		JSONArray jsonArr = null;
		try {
			jsonArr = instruMentationService.searchOnChangeSuperStns(objectCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(jsonArr != null){
			out.println(jsonArr);
		}else{
			out.println("");
		}
			return null;
		}
	
	public String searchGHID()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		//String type = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("instrumentation_type")));
		JSONArray jsonArr = null;
		try {
			jsonArr = instruMentationService.searchGHID();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(jsonArr != null){
			out.println(jsonArr);
		}else{
			out.println("");
		}
			return null;
	}
	public String searchOnChangeYBGhuo()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String objectCode = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("objectCode")));
		String INSTRU_1TN = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("INSTRU_1TN")));
		JSONArray jsonArr = null;
		try {
			jsonArr = instruMentationService.searchOnChangeYBGhuo(objectCode,INSTRU_1TN);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(jsonArr != null){
			out.println(jsonArr);
		}else{
			out.println("");
		}
			return null;
	}
	public String removeInstru() throws Exception {
	
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		String INSTRUMENT_ID = StringUtil.toStr(request.getParameter("INSTRUMENT_ID"));
		//String orgId = StringUtil.toStr(request.getParameter("org_id"));

		boolean deleteflg = false;
		JSONObject obj = new JSONObject();
		try {
			deleteflg = instruMentationService.removeInstrumention(INSTRUMENT_ID);
		} catch (Exception e) {
//			String errmsg = e.getCause().toString();
//			if(!"".equals(errmsg) && errmsg.indexOf("ORA-02292") != -1 ){
//				obj = CommonsUtil.getRrturnJson("","仪表设备信息删除失败-违反完整约束条件" , null, null);
//			}else if(!"".equals(errmsg) && errmsg.indexOf("ORA-02292") != -1 ){
//				obj = CommonsUtil.getRrturnJson("","仪表设备信息删除失败-违反完整约束条件" , null, null);
//			} else{
//				obj = CommonsUtil.getRrturnJson("",errmsg , null, null);
//			}
			e.printStackTrace();
			 //obj = CommonsUtil.getRrturnJson("","对象类型不能为空" , "",null, null);
			 obj = CommonsUtil.getRrturnJson("-16312", "","", null, null);
		}
		if(deleteflg){
			//添加系统LOG
			try {
				User user1 = (User) session.getAttribute("userInfo");
				PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "2", "仪表", INSTRUMENT_ID);
				logService.addLog(log);
			} catch (Exception e) {
				e.printStackTrace();
				obj = CommonsUtil.getRrturnJson("-10003", "","", null, null);
			}
		}
		out.print(obj);
		return null;
	}
	
	public String saveorUpdateData()throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		User user = (User) session.getAttribute("userInfo");
		JSONObject obj = new JSONObject();
		
		String INSTRUMENT_ID = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("INSTRUMENT_ID")));
		String EditObjectCode = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("EditObjectCode")));
		String EditSuperName = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("EditSuperName")));
		String EditInstruSBMC = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("EditInstruSBMC")));
		String EditInstruSJSB = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("EditInstruSJSB")));
		String EditInstruZT = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("EditInstruZT")));
		if(EditInstruZT ==null || "".equals(EditInstruZT)){
			obj = CommonsUtil.getRrturnJson("", "仪表状态不能为空","", null, null);
			out.print(obj);
			return null;
		}
		String EditRemark = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("EditRemark")));
		String EditInstruGHID = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("EditInstruGHID")));
		String EditInstruGHIDN = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("EditInstruGHIDN")));
		String stationNo  = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("stationNo")));
		List<PcCdInstruMentationT> imList = null;
		PcCdInstruMentationT  im = null;
		List<PcCdInstruMentationDT> bmList = null;
		PcCdInstruMentationDT bm = null; 
		//OBJECT_CODE, ORG_ID, INSTRUS_ID, INSTRU_CLC   unique
		
		List<Object[]> uniqueList =  instruMentationService.searchUnique(EditObjectCode,EditSuperName,EditInstruGHIDN,EditInstruSBMC);
		if(INSTRUMENT_ID !=null && !"".equals(INSTRUMENT_ID)){
			if(uniqueList !=null && uniqueList.size()>0){
				if(uniqueList.size() !=1){
					 obj = CommonsUtil.getRrturnJson("","所属对象类型+所属对象+仪表供货ID名+设备名称： -违反唯一约束条件，在库中已存在相同的数据" , "",null, null);
					 out.print(obj);
					 return null;
				}
			}
		}else{
			if(uniqueList !=null && uniqueList.size()>0){
					 obj = CommonsUtil.getRrturnJson("","所属对象类型+所属对象+仪表供货ID名+设备名称： -违反唯一约束条件，在库中已存在相同的数据" , "",null, null);
					 out.print(obj);
					 return null;
			}
		}
		if(INSTRUMENT_ID !=null && !"".equals(INSTRUMENT_ID)){
			try {
				imList = instruMentationService.searchOnlyData(INSTRUMENT_ID);
			} catch (Exception e) {
				e.printStackTrace();
				obj = CommonsUtil.getRrturnJson("", "未知错误！请联系管理员。","", null, null);
				out.print(obj);
				return null;
			}
			if(imList !=null && imList.size()>0){
				if(imList.size() ==1  && INSTRUMENT_ID .equals(imList.get(0).getInstrumentId())){
					im = imList.get(0);
				}else{
					obj = CommonsUtil.getRrturnJson("", "数据存在多条不需修改。","", null, null);
					out.print(obj);
					return null;
				}
			}
		/*	try {
				bmList = instruMentationService.searchOnlyDataDTDB(INSTRUMENT_ID);
				//bm = bmList.get(0);
			} catch (Exception e) {
				e.printStackTrace();
//				obj = CommonsUtil.getRrturnJson("", "未知错误！请联系管理员。","", null, null);
//				out.print(obj);
//				return null;
			}
			if( !bm.getOrgId().equals(EditSuperName)|| !EditObjectCode.equals(bm.getObjectCode()) ||
					!EditInstruSBMC.equals(bm.getInstruClc()) || !EditInstruSJSB.equals(bm.getSjrtuClc()) ||
					!EditInstruZT.equals(bm.getInstruStva()) || !EditInstruGHID.equals(bm.getInstrumentIdn())  ||
					!EditInstruGHIDN.equals(bm.getInstrumentId())) {
					bm.setInstrumentId(INSTRUMENT_ID);
					bm.setOrgId(EditSuperName);
					bm.setObjectCode(EditObjectCode);
					bm.setInstruClc(EditInstruSBMC);
					bm.setSjrtuClc(EditInstruSJSB);
					bm.setRemark(EditRemark);
					bm.setInstruStva(CommonsUtil.getBigDecimalData(EditInstruZT));
					bm.setInstrusId(EditInstruGHIDN);
					bm.setInstrumentIdn(EditInstruGHID);
					bm.setRlastOper(user.getOperName());
					bm.setRlastOdate(new Date());
				}*/
		}else{
			im = new PcCdInstruMentationT();
//			bm = new PcCdInstruMentationDT();
//			bm.setOrgId(EditSuperName);
//			bm.setObjectCode(EditObjectCode);
//			bm.setInstruClc(EditInstruSBMC);
//			bm.setSjrtuClc(EditInstruSJSB);
//			bm.setRemark(EditRemark);
//			bm.setInstruStva(CommonsUtil.getBigDecimalData(EditInstruZT));
//			bm.setInstrusId(EditInstruGHIDN);
//			bm.setInstrumentIdn(EditInstruGHID);
//			bm.setRlastOper(user.getOperName());
//			bm.setRlastOdate(new Date());
		}
		//组织结构
		im.setOrgId(EditSuperName);
		//对象类型
		im.setObjectCode(EditObjectCode);
		//设备名称
		im.setInstruClc(EditInstruSBMC);
		//上级设备
		im.setSjrtuClc(EditInstruSJSB);
		//备注
		im.setRemark(EditRemark);
		//状态
		im.setInstruStva(CommonsUtil.getBigDecimalData(EditInstruZT));
		//供货ID
		im.setInstrusId(EditInstruGHIDN);
		im.setInstrumentIdn(EditInstruGHID);
		im.setStationNo(stationNo);
		
		im.setRlastOper(user.getOperName());
		im.setRlastOdate(new Date());
		
		boolean flag = true;
		try {
				flag = instruMentationService.SaveOrUpdateInstru(im);
				//if(INSTRUMENT_ID !=null &&  !"".equals(INSTRUMENT_ID)){
				//flag = instruMentationService.SaveOrUpdateInstruDT(bm);
				//}
			} catch (Exception e) {
				e.printStackTrace();
				String errmsg = e.getCause().toString();
				if(!"".equals(errmsg) && errmsg.indexOf("ORA-02291") != -1 ){
					obj = CommonsUtil.getRrturnJson("","违反完整约束条件" ,"", null, null);
					out.print(obj);
				}else  if(!"".equals(errmsg) && errmsg.indexOf("ORA-12899") != -1 ){
					obj = CommonsUtil.getRrturnJson("","您输入的值超出限制范围" ,"", null, null);
					out.print(obj);
				}else if(!"".equals(errmsg) && errmsg.indexOf("ORA-00001") != -1){
					obj = CommonsUtil.getRrturnJson("","违反条件" ,"", null, null);
					out.print(obj);
				}else if(!"".equals(errmsg) && errmsg.indexOf("ORA-01400") != -1){
					obj = CommonsUtil.getRrturnJson("","无法将 NULL插入" ,"", null, null);
					out.print(obj);
				}else{
					obj = CommonsUtil.getRrturnJson("",errmsg ,"", null, null);
					out.print(obj);
				}
				
			}
			//动态
			//↓为了比较进行插入 动态表
			//List<PcCdInstruMentationDT> bmList = null;
			//PcCdInstruMentationDT bm = null;
			List<String> strList = new ArrayList<String>();
	
			if(INSTRUMENT_ID !=null &&  !INSTRUMENT_ID.equals("")){
				bmList = instruMentationService.searchOnlyDataDTDB(INSTRUMENT_ID);
				if(bmList ==null && bmList.size()<=0){
					strList  = instruMentationService.creaPIns("p_ComInsert", INSTRUMENT_ID,EditInstruGHID,EditInstruGHIDN,EditSuperName,EditObjectCode,EditInstruSBMC,EditInstruSJSB,EditInstruZT,EditRemark,user.getOperName(), new Date());
				}
			}
			//添加日志
			if(INSTRUMENT_ID !=null && !"".equals(INSTRUMENT_ID)){
		  			try {
						User user1 = (User) session.getAttribute("userInfo");
						PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "3", "仪表",im.getInstrumentId());
						logService.addLog(log);
					} catch (Exception e) {
						e.printStackTrace();
						obj = CommonsUtil.getRrturnJson("", "仪表设备添加日志失败","", null, null);
		  				out.print(obj);
		  				return null;
					}
				}else{
					try {
						User user1 = (User) session.getAttribute("userInfo");
						PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "1", "仪表", im.getInstrumentId());
						logService.addLog(log);
					} catch (Exception e) {
						e.printStackTrace();
						obj = CommonsUtil.getRrturnJson("", "仪表设备修改日志失败","", null, null);
		  				out.print(obj);
		  				return null;
					}
				}
			out.print(obj);
		return null;
	}
	
	public String DispalyParam () throws Exception{
		
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String data = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("data")));
		
		JSONObject obj = new JSONObject();
		
		obj.put("EditFactoryGH", "");
		obj.put("EditInstruSBXH", "");
		obj.put("EditFactoryZC", "");
		if(data != null && !"".equals(data)){
			String sql ="select instrus_idn ,instrus_id,SFACTORY_NS , MFACTORY_NS ,INSTRU_C3N  from pc_cd_instru_namec_v t  " +
						"	where instrus_id ='"+data+"'";
			List<Object[]> list = AuthorityUtil.getDatas(sql);
			if(list != null && list.size()>0){
				obj.put("EditFactoryGH",  CommonsUtil.getClearNullData(list.get(0)[2]));
				obj.put("EditFactoryZC", CommonsUtil.getClearNullData(list.get(0)[3]));
				obj.put("EditInstruSBXH",  CommonsUtil.getClearNullData(list.get(0)[4]));
			}
		}
		out.print(obj);
		return null;
	}
	
	
	
/*	public String exceptDatas()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		//response.setCharacterEncoding("utf-8");
		//response.setHeader("ContentType","text/xml");
		
		
		String oil = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("oilstationname")));
		String bloSta = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("blockstationname")));
		String well = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("wellnum")));
		String boiler = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("boiler")));
		String YBtype = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("YBtype")));
		String factory_nf = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("factory_nf")));
		String object_type_name = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("object_type_name")));
		String ptable = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("ptable")));
		
//		if( ptable !=null  &&  ptable.equals("ptable")){
//			List<Object[]> manyData = instruMentationService.searchCount(oil,bloSta,well,boiler,YBtype,factory_nf,object_type_name);
//		}
		// pc_cd_point_table_v
		List<Object[]> sDataSet = null;
		List<String> jzzorgids = new ArrayList<String>();
		List<String> orgList = new ArrayList<String>();
		List<String> typeList = new ArrayList<String>();
		try {
			sDataSet = instruMentationService.searchAllData(oil,bloSta,well,boiler,YBtype,factory_nf,object_type_name,ptable);
		} catch (Exception e) {
			e.printStackTrace();
		}
//		if( ptable !=null  &&  ptable.equals("ptable")){
//			PrintWriter out = response.getWriter();
//			int total = sDataSet.size();
//			//HashMap<String,Object>  dataMap = (sDataSet.size(), total + "");
//				//Object total = dataMap.get("totalNum");
//				out.println(total);
//				out.flush();
//				out.close();
//				return null;
//		}
		PropertiesConfig pc = new PropertiesConfig();
		//插入的位置
		String insPtableFir = pc.getSystemConfiguration("insPtableFir");
		String insPtableTwo = pc.getSystemConfiguration("insPtableTwo");
		
		String templetFilePath = request.getSession().getServletContext().getRealPath("/") + "exceltemplet\\仪表设备点表生成.xls";
		HSSFWorkbook wb = ExcelToHtmlUtils.loadXls( new File( templetFilePath ) );
		HSSFSheet sheet = wb.getSheetAt(0);
		List<Object[]> lobj = new ArrayList<Object[]>();
		List<Object[]> nameList = new ArrayList<Object[]>();
		
		Object[] param = null;
		if (sDataSet != null && sDataSet.size() > 0) {
			
			for (int i = 0; i < sDataSet.size(); i++) {
				Object[] firData = sDataSet.get(i);
				param = new Object[firData.length-2];
				for(int k=0;k<firData.length;k++){
					//获取接转站IDlist  组织结构ID
					if("7".equals(CommonsUtil.getClearNullData(firData[firData.length-1]))){
						jzzorgids.add(CommonsUtil.getClearNullData(firData[firData.length-2]));
					}
					if(k < firData.length-2){
						param[k] = 	firData[k];
					}
					// 此处管理所属单位
						//orgList.add(CommonsUtil.getClearNullData(firData[firData.length-2]));
						//typeList.add(CommonsUtil.getClearNullData(firData[firData.length-1]));
					}
				}
				lobj.add(param);
			}
			//所属单位

			
			
				//管汇
			if(jzzorgids != null && jzzorgids.size()>0){
				String pids = "";
				for(String ls : CommonsUtil.setData(jzzorgids)){
					if(!"".equals(ls)){
						pids += "'"+ls+"',";
					}
				}
				pids = pids.substring(0, pids.length()-1);
				List<Object[]> newDatas = null;
				if(pids != null && pids.length()>1){
					try {
						newDatas = instruMentationService.searchMafDatas(pids);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
				if(newDatas != null && newDatas.size()>0){
					for(Object[] lo :newDatas){
						lobj.add(lo);
					}
				}
			}
			U2DataExportUtil.insertDataByPosition(lobj, wb, sheet, insPtableFir);
//			List<Object[]> orgDatas = null;
//			if(orgList !=null && orgList.size()>0){
//				String  org_id ="";
//				String   type  ="";
//				
//				List<Object[]> oilNameDatas = null;
//				Object[] suoOil = new Object[1];
//				//for (Object orgId : orgList) {
//					for (int i = 0; i < orgList.size(); i++) {
//						Object orgId = orgList.get(i);
//						Object orgIdType = typeList.get(i);
//						if(!"".equals(orgId)){
//							org_id =orgId.toString();
//							type  = orgIdType.toString();
//							orgDatas = instruMentationService.searchOilName(org_id,type);
//							U2DataExportUtil.insertDataByPosition(orgDatas, wb, sheet, insPtableTwo);
////							for (int j = 0; j < orgDatas.size(); j++) {
////								Object[] twoData = orgDatas.get(j);
////								for (int k = 0; k < twoData.length; k++) {
////									suoOil[k] = twoData[k];
////								}
////							}
//							//Object[] oilName = orgDatas.get(0);
//							//oilNameDatas.add(oilName);
//							//break;
//						}
//					
//				} 
//					//nameList.add(suoOil);
////				if(orgDatas != null && orgDatas.size()>0){
////					for(Object[] name :orgDatas){
////						nameList.add(name);
////					}
////				}
//			}
		
		java.io.ByteArrayOutputStream baos = U2DataExportUtil.ExporDataStream(wb);
		if(baos != null){
			byte[] ba = baos.toByteArray();
			excelFile = new ByteArrayInputStream(ba);
			baos.flush();
			baos.close();
		}
		return "excel";
	}*/
		public String exceptDatas()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		//response.setCharacterEncoding("utf-8");
		//response.setHeader("ContentType","text/xml");
		
		JSONObject  obj =new JSONObject();
		String oilName = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("oilName")));
		String objectCode = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("objectCode")));
		String ownObject = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("ownObject")));
		String staName = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("staName")));
		String areaName = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("areaName")));
		String INSTRU_CLN = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("INSTRU_CLN")));
		String INSTRU_STNS = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("INSTRU_STNS")));
		String superStns = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("superStns")));
		
		String pointCode = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("pointCode")));
		String alamOr = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("alamOr")));
		
		HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
		User user = (User) session.getAttribute("userInfo");
		String MyDate = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("MyDate")));
		
		

		List<String> rusl = new ArrayList<String>();
		
		try {
			rusl  = instruMentationService.creaTable("p_TAG_MAKE_BY_INSTRU", oilName,objectCode,ownObject,staName,areaName,INSTRU_CLN,INSTRU_STNS,superStns,pointCode,alamOr,user.getOperName(),MyDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(rusl != null && rusl.size()>0 ){
			if(Integer.parseInt(rusl.get(0).toString()) == 0){
				obj = CommonsUtil.getRrturnJson("",rusl.get(1).toString() ,"", null, null);
			}else{
				obj = CommonsUtil.getRrturnJson("","" ,rusl.get(1).toString(), null, null);
			}
			
		}else{
			obj = CommonsUtil.getRrturnJson("","当前日期的数据准备失败" ,"", null, null);
		}
		
		PropertiesConfig pc = new PropertiesConfig();
		//插入总条数的位置的位置
		String insPtableFir = pc.getSystemConfiguration("insPtableFir");
		//插入主题数据的位置
		String insPtableTwo = pc.getSystemConfiguration("insPtableTwo");
		
		String templetFilePath = request.getSession().getServletContext().getRealPath("/") + "exceltemplet\\仪表生成点表新.xls";
		HSSFWorkbook wb = ExcelToHtmlUtils.loadXls( new File( templetFilePath ) );
		HSSFSheet sheet = wb.getSheetAt(0);
		List<Object[]> dataList = null;
		try {
			dataList = instruMentationService.searchAllData(oilName,objectCode,ownObject,staName,areaName,INSTRU_CLN,INSTRU_STNS,superStns,pointCode,alamOr,MyDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
//		List<Object[]> listObj = new ArrayList<Object[]>();
//		for (Object[] objects : dataList) {
//			listObj.add(objects);
//		}
		int countData = dataList.size();
		String data = String.valueOf(countData);
		//插入数据的主题位置
		U2DataExportUtil.insertDataByPosition(dataList, wb, sheet, insPtableTwo);
		//插入总数的位置
		U2DataExportUtil.insertExcelTableHead(wb, sheet, insPtableFir ,data);
		java.io.ByteArrayOutputStream baos = U2DataExportUtil.ExporDataStream(wb);
		if(baos != null){
			byte[] ba = baos.toByteArray();
			excelFile = new ByteArrayInputStream(ba);
			baos.flush();
			baos.close();
		}
		return "excel";
	}
}
	
