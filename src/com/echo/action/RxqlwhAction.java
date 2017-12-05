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

import com.echo.dto.PcProWellHotWashing;
import com.echo.dto.PcProWellStimDaily;
import com.echo.dto.PcRdLoginfoT;
import com.echo.dto.User;
import com.echo.service.LogService;
import com.echo.service.MeasuresService;
import com.echo.service.RxqlwhService;
import com.echo.util.AuthorityUtil;
import com.echo.util.CommonsUtil;
import com.echo.util.DateBean;
import com.echo.util.DynamicDataExportUtil;
import com.echo.util.PageVariableUtil;
import com.echo.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

public class RxqlwhAction extends ActionSupport{
	private RxqlwhService rxqlwhService;
	private LogService logService;
	
	public void setRxqlwhService(RxqlwhService rxqlwhService) {
		this.rxqlwhService = rxqlwhService;
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
		String downloadFileName = (sf.format(new Date()).toString())+ "热洗清蜡日报维护.xls";
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
				//措施维护A表 录入
				gridJson = AuthorityUtil.getGridJson1("MENU129", user, PageVariableUtil.RXGLWH_PAGE_GRID);
			
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
			dataMap = rxqlwhService.searchData(oilName,groupTeam,wellname,stime,etime,pageNo,sort,order,rowsPerpage,totalNum,deptype);
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
			String templetFilePath = request.getSession().getServletContext().getRealPath("/") + "exceltemplet\\ExportRBWH\\热洗清蜡日报.xls";
//			将用户对象列表输出到workbook的流中，ExcelUtil类进行了封装，后续分析！                                                              
			java.io.ByteArrayOutputStream baos = DynamicDataExportUtil.dailyrxqlWHExporReport(dataLsit,templetFilePath,"热洗清蜡");
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
		  String WELLNAME =StringUtil.toStr(StringUtil.isNullStr(request.getParameter("well_name")));
		  String ORG_ID =StringUtil.toStr(StringUtil.isNullStr(request.getParameter("ORG_ID")));
		  if(WELLNAME != null && !"".equals(WELLNAME)){
			if(AuthorityUtil.authorityDos(WELLNAME, authorityDatas) == false){
				obj = CommonsUtil.getRrturnJson("","您没有对改生成单位进行添加或修改的权限，请联系管理员添加权限" ,"", null, null);
				out.print(obj);
				return null;
			}
		  }else{
			  obj = CommonsUtil.getRrturnJson("","井名不为空，请选择要操作日报的井名" ,"", null, null);
				out.print(obj);
				return null;
		  }

		  List<Object[]> orgList = null;
		  String sql = "select * from pc_cd_org_t t where 1=1 and  t.structurename = '"+WELLNAME+"' and t.structuretype = 9";
			try {
				 orgList = rxqlwhService.searchDatas(sql);
			} catch (Exception e) {
				e.printStackTrace();
				obj = CommonsUtil.getRrturnJson("","热洗清蜡："+WELLNAME+"组织结构信息获取错误" ,"", null, null);
				out.print(obj);
				return null;
			}
			if(orgList !=null && orgList.size()>0){
				ORG_ID = orgList.get(0)[0].toString();
			}else{
				obj = CommonsUtil.getRrturnJson("","热洗清蜡："+WELLNAME+"组织结构信息井名称获取错误" ,"", null, null);
				out.print(obj);
				return null;
			}
	  
		  String event_date =StringUtil.toStr(StringUtil.isNullStr(request.getParameter("event_date")));
		  String stim_code =StringUtil.toStr(StringUtil.isNullStr(request.getParameter("stim_code")));
		  String HOT_WASH_TYPE =StringUtil.toStr(StringUtil.isNullStr(request.getParameter("HOT_WASH_TYPE")));
		  String PARAFFIN_MELT_TEMP =StringUtil.toStr(StringUtil.isNullStr(request.getParameter("PARAFFIN_MELT_TEMP")));
		  String PARAFFIN_MELT_VOL =StringUtil.toStr(StringUtil.isNullStr(request.getParameter("PARAFFIN_MELT_VOL")));
		  String REMARK =StringUtil.toStr(StringUtil.isNullStr(request.getParameter("REMARK")));
		  String HOTWASH_PARAFFIN_ID =StringUtil.toStr(StringUtil.isNullStr(request.getParameter("HOTWASH_PARAFFIN_ID")));
		 
		  
		  List<PcProWellHotWashing> hotList= null;
		  PcProWellHotWashing  hw = null;
		  
		  if(HOTWASH_PARAFFIN_ID !=null && !"".equals(HOTWASH_PARAFFIN_ID)){
			  try {
				  hotList = rxqlwhService.searchCheck("","",WELLNAME,event_date,stim_code);
			} catch (Exception e) {
				e.printStackTrace();
				obj = CommonsUtil.getRrturnJson("","热洗清蜡信息：ID未识别获取数据失败" ,"", null, null);
				out.print(obj);
				return null;
			}
			  if(hotList !=null && hotList.size()>0){
				  if(hotList.size() ==1){
					  if(HOTWASH_PARAFFIN_ID.equals(hotList.get(0).getHotwashParaffinId())){
						  hw =  hotList.get(0);
					  }else{
							//obj = CommonsUtil.getRrturnJson("","井名称："+WELLNAME+" -已存在请使用其他名称" , "",null, null);
						  obj = CommonsUtil.getRrturnJson("","井名称："+WELLNAME+"- 日期 "+event_date+" 维护工作类型 "+stim_code+"-不唯一" , "",null, null);
							out.print(obj);
							return null;
					  }
				  }else{
					  obj = CommonsUtil.getRrturnJson("","井名称："+WELLNAME+"- 日期 "+event_date+" 维护工作类型 "+stim_code+"-不唯一" , "",null, null);
					  //obj = CommonsUtil.getRrturnJson("","井名称："+WELLNAME+" -已存在请使用其他名称" , "",null, null);
					  out.print(obj);
					  return null; 
				  }
			  }else{
				  hotList = rxqlwhService.searchCheck(HOTWASH_PARAFFIN_ID,"","","","");
				  hw = hotList.get(0);
			  }
		  }else{
			  try {
				  hotList = rxqlwhService.searchCheck("","",WELLNAME,event_date,stim_code);
			  } catch (Exception e) {
				e.printStackTrace();
				obj = CommonsUtil.getRrturnJson("","热洗清蜡信息：ID未识别获取数据失败" ,"", null, null);
				out.print(obj);
				return null;
			}
			  if(hotList !=null && hotList.size()>0){
				  obj = CommonsUtil.getRrturnJson("","井名称："+WELLNAME+"- 日期 "+event_date+" 维护工作类型 "+stim_code+"-已存在相同信息" , "",null, null);
					out.print(obj);
					return null;
			  }else{
				  hw= new PcProWellHotWashing();
			  }
			 
		  }
		  hw.setOrgId(ORG_ID);
		  hw.setWellId(WELLNAME);
		  hw.setEventDate(DateBean.getStrDateTime(DateBean.getParamTime(event_date)));
		  hw.setParaffinStimType(stim_code);
		  hw.setHotWashType(HOT_WASH_TYPE);
		  hw.setParaffinMeltVol(CommonsUtil.getBigDecimalData(PARAFFIN_MELT_VOL));
		  hw.setParaffinMeltTemp(CommonsUtil.getBigDecimalData(PARAFFIN_MELT_TEMP));
		  hw.setRemark(REMARK);
		  hw.setRlastOper(user.getOperName());
		  hw.setRlastOdate(new Date());
		  hotList.clear();
		  hotList.add(hw);
		  boolean flag = true;
			 try {
				   flag = rxqlwhService.saveOrUpdate(hotList);
			} catch (Exception e) {
				e.printStackTrace();
				obj = CommonsUtil.getRrturnJson("","保存失败" ,"", null, null);
				out.print(obj);
				return null;
			}
			if(HOTWASH_PARAFFIN_ID != null && !"".equals(HOTWASH_PARAFFIN_ID)){
				//修改系统LOG
				try {
					PcRdLoginfoT log = CommonsUtil.getLogInfo(user, "3", "热洗清蜡", hw.getHotwashParaffinId());
					logService.addLog(log);
				} catch (Exception e) {
					e.printStackTrace();
					obj = CommonsUtil.getRrturnJson("","热洗清蜡信息LOG修改日志添加失败" ,"", null, null);
					out.print(obj);
					return null;
				}
			}else{
				//添加系统LOG
				try {
					PcRdLoginfoT log = CommonsUtil.getLogInfo(user, "1", "热洗清蜡", hw.getHotwashParaffinId());
					logService.addLog(log);
				} catch (Exception e) {
					e.printStackTrace();
					obj = CommonsUtil.getRrturnJson("","热洗清蜡信息LOG添加日志添加失败" ,"", null, null);
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
		  String WELLNAME =StringUtil.toStr(StringUtil.isNullStr(request.getParameter("well_name")));
		  
		  if(WELLNAME != null && !"".equals(WELLNAME)){
			if(AuthorityUtil.authorityDos(WELLNAME, authorityDatas) == false){
				obj = CommonsUtil.getRrturnJson("","您没有对改生成单位进行删除的权限，请联系管理员添加权限" ,"", null, null);
				out.print(obj);
				return null;
			}
		  }
		String flowing_paraffin_id = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("HOTWASH_PARAFFIN_ID")));
		boolean  deleteFlag = false;
	
		try {
			deleteFlag = rxqlwhService.removeDatas(flowing_paraffin_id);
		} catch (Exception e) {
			String errmsg = e.getCause().toString();
			
			obj = CommonsUtil.getRrturnJson("",errmsg ,"", null, null);
			e.printStackTrace();
		}
		
		if(deleteFlag){
			//添加系统LOG
			try {
				User user1 = (User) session.getAttribute("userInfo");
				PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "2", "热洗维护", flowing_paraffin_id);
				logService.addLog(log);
			} catch (Exception e) {
				e.printStackTrace();
				obj = CommonsUtil.getRrturnJson("-10003", "","", null, null);
			}
			
		}
		out.print(obj);
		return null;
	}
	
	public String searchHOT_WASH_TYPE()throws  Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONArray  arr= null;
		try {
			arr = rxqlwhService.searchHOT_WASH_TYPE();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(arr !=null &&  arr.size()>0){
			out.print(arr);
		}else{
			out.print("");
		}
		return null;
	}
}	
