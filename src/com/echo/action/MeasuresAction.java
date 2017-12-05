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

import com.echo.dto.PcProWellStimDaily;
import com.echo.dto.PcRdLoginfoT;
import com.echo.dto.User;
import com.echo.service.LogService;
import com.echo.service.MeasuresService;
import com.echo.util.AuthorityUtil;
import com.echo.util.CommonsUtil;
import com.echo.util.DateBean;
import com.echo.util.DynamicDataExportUtil;
import com.echo.util.PageVariableUtil;
import com.echo.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

public class MeasuresAction extends ActionSupport{
	private MeasuresService measuresService;
	private LogService logService;
	public void setMeasuresService(MeasuresService measuresService) {
		this.measuresService = measuresService;
	}
	private InputStream excelFile = null;
	
	public InputStream getExcelFile() {
		return excelFile;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	public void setExcelFile(InputStream excelFile) {
		this.excelFile = excelFile;
	}
	HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
	public String getFileName() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd ");
		String downloadFileName = (sf.format(new Date()).toString())+ "措施日报维护.xls";
		try {
			downloadFileName = new String(downloadFileName.getBytes(),"ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}	
		return downloadFileName;
	}
	
	//根据权限进行页面初始化
	public String pageInit()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		//根据用户权限生成用户权限生成页面布局格式
		User user = (User) session.getAttribute("userInfo");
//		
		String argA = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("arg"))); 
		String gridJson = null;
		try {
			
				gridJson = AuthorityUtil.getGridJson1("MENU127", user, PageVariableUtil.CSWH_PAGE_GRID);
			
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
	public String searchDatas() throws IOException {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
		User user = (User) session.getAttribute("userInfo");
		String deptype = user.getDepType();   //用户所属部门类型   15：班组  4采油站
		
		String oilName = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("CYZ")));
		String groupTeam = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("TEAM")));
		String wellname = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("WELLNAME")));

		String stime=StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("txtDate")));
		String etime=StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("txtDate1")));
		String totalNum = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("totalNum")));

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
			dataMap = measuresService.searchData(oilName,groupTeam,wellname,stime,etime,pageNo,sort,order,rowsPerpage,totalNum,deptype);
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
			String templetFilePath = request.getSession().getServletContext().getRealPath("/") + "exceltemplet\\ExportRBWH\\措施日报.xls";
//			将用户对象列表输出到workbook的流中，ExcelUtil类进行了封装，后续分析！                                                              
			java.io.ByteArrayOutputStream baos = DynamicDataExportUtil.dailycsWHExporReport(dataLsit,templetFilePath,"措施日报");
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
	
	public  String searchStmiuType()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONArray arr = null;
		try {
			arr = measuresService.searchStmiuType();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(arr !=null && arr.size()>0){
			out.print(arr);
		}else{
			out.print("");
		}
		return null;
	}
	
	public  String addorUpdateDatas()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		User user = (User) session.getAttribute("userInfo");
		JSONObject  obj = new JSONObject();
		
		List<Object[]> authorityDatas = null;
		authorityDatas = AuthorityUtil.updateDatasAuthority(user,session);
		  String WELLNAME =StringUtil.toStr(StringUtil.isNullStr(request.getParameter("WELLNAME")));
		  
		  if(WELLNAME != null && !"".equals(WELLNAME)){
			if(AuthorityUtil.authorityDos(WELLNAME, authorityDatas) == false){
				obj = CommonsUtil.getRrturnJson("","您没有对改生成单位进行添加或修改的权限，请联系管理员添加权限" ,"", null, null);
				out.print(obj);
				return null;
			}
		  }
		  String START_DATETIME =StringUtil.toStr(StringUtil.isNullStr(request.getParameter("START_DATETIME")));
		  String END_DATETIME =StringUtil.toStr(StringUtil.isNullStr(request.getParameter("END_DATETIME")));
		  //措施代码
		  String P_DESCRIPTION  =StringUtil.toStr(StringUtil.isNullStr(request.getParameter("P_DESCRIPTION")));
		  List<Object[]> codeLst = measuresService.searchCOnly(P_DESCRIPTION);
		  String stmiuCode = "";
		  if(codeLst !=null && codeLst.size()>0){
			   //stmiuCode = codeLst.get(0).toString();
			   stmiuCode = measuresService.searchRem(P_DESCRIPTION);
		  }else{
			  obj = CommonsUtil.getRrturnJson("","措施代码："+P_DESCRIPTION+" -请查看措施代码填写是否正确" , "",null, null);
				out.print(obj);
				return null;
		  }
		 // String stmiuCode = measuresService.searchRem(P_DESCRIPTION);
		  //措施井重点标志
		  String KEY_DOWN_TAG =StringUtil.toStr(StringUtil.isNullStr(request.getParameter("KEY_DOWN_TAG")));
		  String REMARK =StringUtil.toStr(StringUtil.isNullStr(request.getParameter("REMARK")));
 		  String ORG_ID =StringUtil.toStr(StringUtil.isNullStr(request.getParameter("ORG_ID")));
 		  if("".equals(ORG_ID)){
 			 ORG_ID = measuresService.searchOrd_Id(WELLNAME);
 		  }
		  String MEASURE_ID =StringUtil.toStr(StringUtil.isNullStr(request.getParameter("MEASURE_ID")));
		
		  
		  List<PcProWellStimDaily> StimDList = null;
		  PcProWellStimDaily  sd = null;
		  if(MEASURE_ID  !=null && !"".equals(MEASURE_ID)){
			  //修改
			  try {
				  //StimDList = measuresService.serarchCheckOnly("","",WELLNAME,"","");
				  StimDList = measuresService.serarchCheckOnly("","",WELLNAME,START_DATETIME,stmiuCode);
//				  if(StimDList !=null && StimDList.size()>0){
//					  obj = CommonsUtil.getRrturnJson("","井名称："+WELLNAME+" "+START_DATETIME+" "+stmiuCode+"-不唯一" , "",null, null);
//						out.print(obj);
//						return null;
//				  }
			} catch (Exception e) {
				e.printStackTrace();
			}
			  if(StimDList !=null && StimDList.size()>0){
				  if(StimDList.size() ==1){
					  if(MEASURE_ID.equals(StimDList.get(0).getMeasureId())){
							  sd =  StimDList.get(0);
					  }else{
							//obj = CommonsUtil.getRrturnJson("","井名称："+WELLNAME+" -已存在请使用其他名称" , "",null, null);
						  obj = CommonsUtil.getRrturnJson("","井名称："+WELLNAME+"- 措施开始时间 "+START_DATETIME+" 措施代码 "+P_DESCRIPTION+"-不唯一" , "",null, null);
							out.print(obj);
							return null;
					  }
				  }else{
					  obj = CommonsUtil.getRrturnJson("","井名称："+WELLNAME+"- 措施开始时间 "+START_DATETIME+" 措施代码 "+P_DESCRIPTION+"-不唯一" , "",null, null);
					  //obj = CommonsUtil.getRrturnJson("","井名称："+WELLNAME+" -已存在请使用其他名称" , "",null, null);
					  out.print(obj);
					  return null; 
				  }
			  }else{
				  StimDList = measuresService.serarchCheckOnly(MEASURE_ID,"","","","");
				  sd = StimDList.get(0);
			  }
		  }else{
			  //添加
			  StimDList = measuresService.serarchCheckOnly("","",WELLNAME,START_DATETIME,stmiuCode);
			  if(StimDList !=null && StimDList.size()>0){
				  obj = CommonsUtil.getRrturnJson("","井名称："+WELLNAME+"- 措施开始时间 "+START_DATETIME+" 措施代码 "+P_DESCRIPTION+"-不唯一" , "",null, null);
					out.print(obj);
					return null;
			  }else{
				  sd = new PcProWellStimDaily();
			  }
			  //StimDList = measuresService.serarchCheckOnly("","",WELLNAME,"","");
//			  if(StimDList !=null && StimDList.size()>0){
//				  	obj = CommonsUtil.getRrturnJson("","井名称："+WELLNAME+" -已存在请使用其他名称" , "",null, null);
//					out.print(obj);
//					return null;
		  }
		  sd.setWellId(WELLNAME);  //保存的是井名
		  sd.setOrgId(ORG_ID);
			if(START_DATETIME != null && !"".equals(START_DATETIME)){
				 sd.setStartDatetime(DateBean.getStrDateTime(DateBean.getParamTime(START_DATETIME)));
			}else{
				sd.setStartDatetime(null);
			}
			if(END_DATETIME != null && !"".equals(END_DATETIME)){
				 sd.setEndDatetime(DateBean.getStrDateTime(DateBean.getParamTime(END_DATETIME)));
			}else{
				 sd.setEndDatetime(null);
			}
		  sd.setRemark(REMARK);
		  if(KEY_DOWN_TAG.equals("是")){
			  sd.setKeyDownTag((byte)1);
		  }else  if(KEY_DOWN_TAG.equals("否")){
			  sd.setKeyDownTag((byte)0);
		  }else{
			  sd.setKeyDownTag(AuthorityUtil.getKEYTAG(WELLNAME));
		  }
		  //sd.setStimCode(codeLst.get(0).toString());
		  sd.setStimCode(stmiuCode);
		  sd.setRlastOper(user.getOperName());
		  sd.setRlastOdate( new Date());
		  StimDList.clear();
		  StimDList.add(sd);
		  boolean flag = true;
		 try {
			   flag = measuresService.saveOrUpdate(StimDList);
		} catch (Exception e) {
			e.printStackTrace();
			obj = CommonsUtil.getRrturnJson("","保存失败" ,"", null, null);
			out.print(obj);
			return null;
		}
		if(MEASURE_ID != null && !"".equals(MEASURE_ID)){
			//修改系统LOG
			try {
				PcRdLoginfoT log = CommonsUtil.getLogInfo(user, "3", "措施", sd.getMeasureId());
				logService.addLog(log);
			} catch (Exception e) {
				e.printStackTrace();
				obj = CommonsUtil.getRrturnJson("","措施信息LOG修改日志添加失败" ,"", null, null);
				out.print(obj);
				return null;
			}
		}else{
			//添加系统LOG
			try {
				PcRdLoginfoT log = CommonsUtil.getLogInfo(user, "1", "措施", sd.getMeasureId());
				logService.addLog(log);
			} catch (Exception e) {
				e.printStackTrace();
				obj = CommonsUtil.getRrturnJson("","措施信息LOG添加日志添加失败" ,"", null, null);
				out.print(obj);
				return null;
			}
		}
		out.print(obj);
		return null;
	}
	
	public String removeData() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		User user = (User) session.getAttribute("userInfo");
		JSONObject obj = new JSONObject();
		List<Object[]> authorityDatas = null;
		authorityDatas = AuthorityUtil.updateDatasAuthority(user,session);
		  String WELLNAME =StringUtil.toStr(StringUtil.isNullStr(request.getParameter("WELLNAME")));
		  
		  if(WELLNAME != null && !"".equals(WELLNAME)){
			if(AuthorityUtil.authorityDos(WELLNAME, authorityDatas) == false){
				obj = CommonsUtil.getRrturnJson("","您没有对改生成单位进行删除的权限，请联系管理员添加权限" ,"", null, null);
				out.print(obj);
				return null;
			}
		  }
		String MEASURE_ID = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("MEASURE_ID")));
		boolean  deleteFlag = false;
	
		try {
			deleteFlag = measuresService.removeDatas(MEASURE_ID);
		} catch (Exception e) {
			String errmsg = e.getCause().toString();
			
			obj = CommonsUtil.getRrturnJson("",errmsg ,"", null, null);
			e.printStackTrace();
		}
		
		if(deleteFlag){
			//添加系统LOG
			try {
				User user1 = (User) session.getAttribute("userInfo");
				PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "2", "措施维护", MEASURE_ID);
				logService.addLog(log);
			} catch (Exception e) {
				e.printStackTrace();
				obj = CommonsUtil.getRrturnJson("-10003", "","", null, null);
			}
			
		}
		out.print(obj);
		return null;
	}
}	
