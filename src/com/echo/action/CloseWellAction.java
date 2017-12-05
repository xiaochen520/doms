package com.echo.action;

import java.io.File;
import java.io.IOException;
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

import org.apache.poi.hssf.converter.ExcelToHtmlUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.struts2.ServletActionContext;

import com.echo.dto.PcRdLoginfoT;
import com.echo.dto.PcWellDowntime;
import com.echo.dto.User;
import com.echo.service.CloseWellService;
import com.echo.service.LogService;
import com.echo.util.AuthorityUtil;
import com.echo.util.CommonsUtil;
import com.echo.util.DateBean;
import com.echo.util.ExcelUtil;
import com.echo.util.PageVariableUtil;
import com.echo.util.ReportFormsBaseUitl;
import com.echo.util.StringUtil;
import com.echo.util.U2DataExportUtil;

public class CloseWellAction extends ReportFormsBaseUitl{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CloseWellService closeWellService;
	private LogService logService;

	public void setCloseWellService(CloseWellService closeWellService) {
		this.closeWellService = closeWellService;
	}

	

	public void setLogService(LogService logService) {
		this.logService = logService;
	}



	//private final String fileName = "关井日报报表.xls";
	public String getFileName() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd ");
		String downloadFileName = (sf.format(new Date()).toString())+ "关井日报报表.xls";
		try {
			downloadFileName = new String(downloadFileName.getBytes(),"ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}	
		return downloadFileName;
	}
	HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
	@SuppressWarnings("unchecked")
	public String searchDatas() throws IOException {
		
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String CYZ = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("CYZ")));
		String TEAM = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("TEAM")));
		String WELLNAME = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("WELLNAME")));
		String txtDate = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("txtDate")));
		String txtDate1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("txtDate1")));
		String tableid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("tableid")));
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
 		//JSONObject jsonobj=null;
		try {
			dataMap = closeWellService.searchDatas(totalNum,CYZ,TEAM,WELLNAME,tableid,txtDate,txtDate1,pageNo,sort,order,rowsPerpage);
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
					String templetFilePath = request.getSession().getServletContext().getRealPath("/") + "exceltemplet\\ExportRBWH\\关井日报.xls";
					
					HSSFWorkbook wb = ExcelToHtmlUtils.loadXls( new File( templetFilePath ) );
					HSSFSheet sheet = wb.getSheetAt(0);
					
					if (dataLsit.size() != 0 && dataLsit != null) {
						
						CellStyle cs1 = wb.createCellStyle();
						cs1 = ExcelUtil.getDataStyle(cs1);
						U2DataExportUtil.insertDataByPosition(dataLsit, wb, sheet, "A3");
					}
					
					OutputStreamAndCloseBaos(U2DataExportUtil.ExporDataStream(wb));
					return "excel";
				}
				out.println("");
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
		//根据用户权限生成用户权限生成页面布局格式
		User user = (User) session.getAttribute("userInfo");
//		
		String arg = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("arg"))); 
		String gridJson = null;
		try {
			if("up".equals(arg)){
//				gridJson = AuthorityUtil.getGridJson1("MENU131", user, PageVariableUtil.GJWHB_PAGE_GRID);
			}else{
				gridJson = AuthorityUtil.getGridJson1("MENU126", user, PageVariableUtil.GJWH_PAGE_GRID);
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
	public String removeData() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		User user = (User) session.getAttribute("userInfo");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		JSONObject obj = new JSONObject();
		List<Object[]> authorityDatas = null;
		authorityDatas = AuthorityUtil.updateDatasAuthority(user,session);
		  String WELLNAME =StringUtil.toStr(StringUtil.isNullStr(request.getParameter("wellname")));
		  
		  if(WELLNAME != null && !"".equals(WELLNAME)){
			if(AuthorityUtil.authorityDos(WELLNAME, authorityDatas) == false){
				obj = CommonsUtil.getRrturnJson("","您没有对改生成单位进行删除的权限，请联系管理员添加权限" ,"", null, null);
				out.print(obj);
				return null;
			}
		  }
		String closing_well_id = StringUtil.toStr(request.getParameter("closing_well_id"));
		boolean deleteflg = false;
		
		try {
			deleteflg = closeWellService.removeDatas(closing_well_id);
		} catch (Exception e) {
			String errmsg = e.getCause().toString();
			
			obj = CommonsUtil.getRrturnJson("",errmsg ,"", null, null);
			e.printStackTrace();
		}
		
		if(deleteflg){
			//添加系统LOG
			try {
				User user1 = (User) session.getAttribute("userInfo");
				PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "2", "关井维护", closing_well_id);
				logService.addLog(log);
			} catch (Exception e) {
				e.printStackTrace();
				obj = CommonsUtil.getRrturnJson("-10003", "","", null, null);
			}
			
		}
		out.print(obj);
		return null;
	}
	
	
	
	@SuppressWarnings("unused")
	public String addorUpdateDatas() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();

		JSONObject obj = new JSONObject();
		List<PcWellDowntime> gjList = null;
		List<PcWellDowntime> gjList1 = null;
		PcWellDowntime wellInfo = null;
		List<Object[]> orgList = null;
		String id = "";
		String well_name = "";
		String org_id ="";
		boolean addflg = true;
		User user = (User) session.getAttribute("userInfo");
		if(request.getParameter("closing_well_id") != null && !"".equals(request.getParameter("closing_well_id"))){
			id = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("closing_well_id")));
		}
		well_name = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("param_well_name")));
		String downtime_type = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("param_downtime_type")));
		String key_down_tag = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("param_key_down_tag")));
		String start_datetime = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("param_start_datetime")));
		String end_datetime = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("param_end_datetime")));
		String remark = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("param_remark")));
		
		
		
		
		//用户添加修改生产单位权限范围（组织结构ID）
		List<Object[]> authorityDatas = null;
		authorityDatas = AuthorityUtil.updateDatasAuthority(user,session);
		
		if(well_name != null && !"".equals(well_name)){
			if(AuthorityUtil.authorityDos(well_name, authorityDatas) == false){
				obj = CommonsUtil.getRrturnJson("","您没有对改生成单位进行添加或修改的权限，请联系管理员添加权限" ,"", null, null);
				out.print(obj);
				return null;
			}
		}else{
			obj = CommonsUtil.getRrturnJson("","井名不为空，请选择要操作日报的井名" ,"", null, null);
			out.print(obj);
			return null;
			
		}
		
		if(downtime_type == null || "".equals(downtime_type)){
			obj = CommonsUtil.getRrturnJson("","关井信息：关井原因不能为空，请重新选择" ,"", null, null);
			out.print(obj);
			return null;
		}
		
		//修改
		if(id != null && !"".equals(id)){
			try {
				gjList = closeWellService.searchOneData(id, "", "", "");
			} catch (Exception e) {
				e.printStackTrace();
				obj = CommonsUtil.getRrturnJson("","关井信息：ID未识别获取数据失败" ,"", null, null);
				out.print(obj);
				return null;
			}
			
			if(gjList1 != null && gjList1.size() >0){
				if(gjList1.size() ==1 && "id".equals(gjList1.get(0).getClosingWellId()) ){
					
				}else{
					obj = CommonsUtil.getRrturnJson("","关井信息：数据已存在" ,"", null, null);
					out.print(obj);
					return null;
				}
			}
			
			wellInfo = gjList.get(0);
		//添加	
		}else{
			//根据信息判断数据唯一行
//			try {
//				gjList = closeWellService.searchOneData("", well_name, start_datetime, end_datetime);
//			} catch (Exception e) {
//				e.printStackTrace();
//				obj = CommonsUtil.getRrturnJson("","关井信息：数据唯一性数据获取失败" ,"", null, null);
//				out.print(obj);
//				return null;
//			}
//			if(gjList != null && gjList.size()>0){
//				obj = CommonsUtil.getRrturnJson("","关井信息："+well_name+" 关井信息已存在" ,"", null, null);
//				out.print(obj);
//				return null;
//			}
			if(gjList1 != null && gjList1.size() >0){
				obj = CommonsUtil.getRrturnJson("","关井信息：数据已存在" ,"", null, null);
				out.print(obj);
				return null;
			}
			wellInfo = new PcWellDowntime();
		}
			String sql = "select * from pc_cd_org_t t where 1=1 and  t.structurename = '"+well_name+"' and t.structuretype = 9";
			try {
				orgList = closeWellService.searchDatas(sql);
			} catch (Exception e) {
				e.printStackTrace();
				obj = CommonsUtil.getRrturnJson("","关井信息："+well_name+"组织结构信息获取错误" ,"", null, null);
				out.print(obj);
				return null;
			}
			
			if(orgList != null && orgList.size()>0){
				org_id = orgList.get(0)[0].toString();
			}else{
				obj = CommonsUtil.getRrturnJson("","关井信息："+well_name+"组织结构信息不存在" ,"", null, null);
				out.print(obj);
				return null;
			}
			
			wellInfo.setOrgId(org_id);
			wellInfo.setWellId(well_name);
			wellInfo.setDowntimeType(downtime_type);
			if(key_down_tag != null && !"".equals(key_down_tag)){
				wellInfo.setKeyDownTag(Byte.parseByte(key_down_tag));
			}else{
				//对为填重点井表示进行自动计量获取
				wellInfo.setKeyDownTag(AuthorityUtil.getKEYTAG(well_name));
			}
			
			if(start_datetime != null && !"".equals(start_datetime)){
				wellInfo.setStartDatetime(DateBean.getStrDateTime(DateBean.getParamTime(start_datetime)));
			}else{
				wellInfo.setStartDatetime(null);
			}
			if(end_datetime != null && !"".equals(end_datetime)){
				wellInfo.setEndDatetime(DateBean.getStrDateTime(DateBean.getParamTime(end_datetime)));
			}else{
				wellInfo.setEndDatetime(null);
			}
			wellInfo.setRlastOper(user.getOperName());
			wellInfo.setRlastOdate(new Date());
			wellInfo.setRemark(remark);
				
				try {
						addflg = closeWellService.saveOrUpdate(wellInfo);
				} catch (Exception e) {
					e.printStackTrace();
					obj = CommonsUtil.getRrturnJson("","关井信息："+well_name+"操作入库失败" ,"", null, null);
					out.print(obj);
					return null;
				}
			
			
		
			if(id != null && !"".equals(id)){
				//修改系统LOG
				try {
					PcRdLoginfoT log = CommonsUtil.getLogInfo(user, "3", "关井", wellInfo.getClosingWellId());
					logService.addLog(log);
				} catch (Exception e) {
					e.printStackTrace();
					obj = CommonsUtil.getRrturnJson("","关井信息LOG修改日志添加失败" ,"", null, null);
					out.print(obj);
					return null;
				}
			}else{
				//添加系统LOG
				try {
					PcRdLoginfoT log = CommonsUtil.getLogInfo(user, "1", "关井", wellInfo.getWellId());
					logService.addLog(log);
				} catch (Exception e) {
					e.printStackTrace();
					obj = CommonsUtil.getRrturnJson("","关井信息LOG添加日志添加失败" ,"", null, null);
					out.print(obj);
					return null;
				}
			}
			
			
		
			out.print(obj);
		return null;
	}
	
	public String searchDOWNTIME_TYPE() throws Exception {
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONArray  ArrList = null;
		try {
			ArrList =closeWellService.searchDOWNTIME_TYPE();
		} catch (Exception e) {
			e.printStackTrace();
		}
	if(ArrList !=null){
		out.println(ArrList);
	}else{
		out.println("");
	}
		return null;
	
	}
	
}
