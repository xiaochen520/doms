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

import com.echo.dto.PcCdIpUsedT;
import com.echo.dto.User;
import com.echo.service.IPUsedService;
import com.echo.util.AuthorityUtil;
import com.echo.util.CommonsUtil;
import com.echo.util.DateBean;
import com.echo.util.DynamicDataExportUtil;
import com.echo.util.PageVariableUtil;
import com.echo.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

public class IPUsedAction extends ActionSupport{
	 private IPUsedService ipUsedService;

	public void setIpUsedService(IPUsedService ipUsedService) {
		this.ipUsedService = ipUsedService;
	}
	HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
	private InputStream excelFile = null;
	public String getFileName() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd ");
		String downloadFileName = (sf.format(new Date()).toString())+ "IP使用表.xls";
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
	public String searchDatas() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String category = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("category")));
		String area = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("area")));
		String unit = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("unit")));
		String INSTRU_1TN = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("INSTRU_1TN")));
		String shebei_name = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("shebei_name")));
		String ip = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("ip")));
		String code2 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("code2")));
		String is_used = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("is_used")));
		String rlastOdate = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("rlastOdate")));
		String txtDate = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("txtDate")));
		String txtDate1 = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("txtDate1")));
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
//	 		if("".equals(category) && "".equals(area)  && "".equals(unit) && "".equals(is_used) 
//					 && "".equals(shebei_name) && "".equals(ip) && "".equals(code2) && "".equals(totalNum)){
//				out.println("");
//				return null;
//			}
	 		HashMap<String,Object> dataMap = null;
	 		try {
		 		dataMap = ipUsedService.searchDatas(category, area, unit,INSTRU_1TN,shebei_name,ip,code2,is_used,rlastOdate,txtDate,txtDate1,pageNo,sort,order,rowsPerpage,totalNum);

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
				String templetFilePath = request.getSession().getServletContext().getRealPath("/") + "exceltemplet\\自动化专网IP规划建表信息整理.xls";
//				将用户对象列表输出到workbook的流中，ExcelUtil类进行了封装，后续分析！
				java.io.ByteArrayOutputStream baos = DynamicDataExportUtil.ipExporReport(dataLsit,templetFilePath,"IP使用表");
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
		String outCode = "1";
		//根据用户权限生成用户权限生成页面布局格式
		User user = (User) session.getAttribute("userInfo");
//		
		String gridJson = null;
		try {
			gridJson = AuthorityUtil.getGridJson1("MENU122", user, PageVariableUtil.IP_USED_T);
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
	
	public  String searchCategory() throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONArray jsonArr  =null ;
		
		String  arg = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("Category")));
		try {
			jsonArr = ipUsedService.searchCategory(arg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(jsonArr !=null ){
			out.print(jsonArr);
		}else{
			out.print("");
		}
		
		return null;
	}
	public  String searchArea()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String arg = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("arg")));
		JSONArray jsonArr = null;
		try {
			jsonArr = ipUsedService.searchArea(arg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(jsonArr !=null){
			out.print(jsonArr);
		}else{
			out.print("");
		}
		return null;
	}
	public String searchShebei()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String arg = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("arg")));
		
		JSONArray jsonArr = null;
		try {
			jsonArr = ipUsedService.searchShebei(arg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(jsonArr !=null){
			out.print(jsonArr);
		}else{
			out.print("");
		}
		return null;
	}
	//根据对象类型变设备
	public String searchOnChangeSB()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String catid = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("catid")));
		String shid = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("shid")));
		
		JSONArray jsonArr = null;
		try {
			jsonArr = ipUsedService.searchOnChangeSB(catid,shid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(jsonArr !=null){
			out.print(jsonArr);
		}else{
			out.print("");
		}
		return null;
	}
	public String searchDevice()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String arg = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("arg")));
		
		JSONArray jsonArr = null;
		try {
			jsonArr = ipUsedService.searchDevice(arg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(jsonArr !=null){
			out.print(jsonArr);
		}else{
			out.print("");
		}
		return null;
	}
	public String searchUnit()throws Exception{
		HttpServletRequest request  = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONArray jsonArr = null;
		try {
			jsonArr =ipUsedService.searchUnit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(jsonArr !=null){
			out.print(jsonArr);
		}else{
			out.print("");
		}
		return  null;
	}
	//  级联是应该用 所属对象
	public String searchOnChangeUnit()throws Exception{
		HttpServletRequest request  = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String 	catid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("catid")));
		String	arid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("arid")));
		JSONArray jsonArr = null;
		try {
			jsonArr =ipUsedService.searchOnChangeUnit(catid,arid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(jsonArr !=null){
			out.print(jsonArr);
		}else{
			out.print("");
		}
		return  null;
	}
	//  设备大类名:
	public String searchInstru_1tn1()throws Exception{
		HttpServletRequest request  = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONArray jsonArr = null;
		try {
			jsonArr =ipUsedService.searchInstruData();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(jsonArr !=null){
			out.print(jsonArr);
		}else{
			out.print("");
		}
		return  null;
	}
	
	public String updateIpUsed()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		List<PcCdIpUsedT> IpList = null;
		PcCdIpUsedT ip = null;
		boolean addflg = true;
		String outCode = "1";
		JSONObject obj = new JSONObject();
		User sessionuser = (User) session.getAttribute("userInfo");

		//"IP":IP,"category":category ,"area":area,"unit":unit ,"instrumentation_name":instrumentation_name,
		//"IP_NO":IP_NO,"device":device,"interface2th":interface2th,"segment_code2":segment_code2,"is_used":is_used,"start_date":start_date,
		//"start_date":start_date,"start_date":stop_date,"remark":remark,"VPN_IP":VPN_IP
		String id = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("IP"))); 
		try {
			IpList = ipUsedService.searchIpId(id);
		} catch (Exception e) {
			e.printStackTrace();
			obj = CommonsUtil.getRrturnJson("", "IP使用表ID获取失败","", null, null);
			out.print(obj);
			return null;
		}
		if(IpList != null && IpList.size()>0){
			ip = new PcCdIpUsedT();
			
			String category = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("category"))); 
			String area = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("area"))); 
			String unit = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("unit"))); 
			List<Object[]> orgData = null;
			try {
				orgData = ipUsedService.searchOrgID(unit);
			} catch (Exception e) {
				e.printStackTrace();
				obj = CommonsUtil.getRrturnJson("", "未知错误！请联系管理员。","", null, null);
				out.print(obj);
				return null;
			}
			if(orgData == null || orgData.size()==0){
				obj = CommonsUtil.getRrturnJson("", "您添加的所属对象不存在，请重新确定您添加数据的准确性。","", null, null);
				out.print(obj);
				return null;
			}
			String instrumentationName = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("instrumentation_name"))); 
			String ipNo = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("IP_NO"))); 
			String device = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("device"))); 
			String interface2th = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("interface2th"))); 
			String isUsed = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("is_used"))); 
			String startDate = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("start_date"))); 
			String stopDate = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("stop_date"))); 
			String remark = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("remark"))); 
			String vpnIp = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("VPN_IP"))); 
			String segment_code2 = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("segment_code2")));
			String unitOrgId = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("unitOrgId")));
			String categoryID = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("categoryID")));
			String INSTRU_CLC = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("INSTRU_CLC")));
			String  segmentId = ipUsedService.searchSegmentId(segment_code2);
			
			ip.setSegmentId(segmentId);
			ip.setIp(id);
			 if(isUsed.equals("没有分配")){
	            	ip.setIsUsed(java.math.BigDecimal.valueOf(0));
	            }else if(isUsed.equals("正式在用")){
	            	ip.setIsUsed(java.math.BigDecimal.valueOf(1));
	            }else if(isUsed.equals("正式分配")){
	            	ip.setIsUsed(java.math.BigDecimal.valueOf(2));
	            }else if(isUsed.equals("临时分配")){
	            	ip.setIsUsed(java.math.BigDecimal.valueOf(3));
	            }else if(isUsed.equals("IP已经停用")){
	            	ip.setIsUsed(java.math.BigDecimal.valueOf(8));
	            }else{
	            	ip.setIsUsed(null);
	            }
			if(isUsed.equals("没有分配")){
				 
				ip.setCategory(null);
				ip.setArea(null);
				ip.setUnit(null);
				ip.setInstrumentationName(null);
				ip.setIpNo(CommonsUtil.getBigDecimalData(null));
				ip.setDevice(null);
				ip.setInterface2th(null);
				ip.setOrgId(null);
				ip.setObjectCode(null);
				ip.setInstruClc(null);
				ip.setStartDate(DateBean.getStrDateNotNull(null));
				ip.setStopDate(DateBean.getStrDateNotNull(null));
				ip.setRemark(null);
				ip.setVpnIp(null);
			 }else{
				 ip.setCategory(category);
					ip.setArea(area);
					ip.setUnit(unit);
					ip.setInstrumentationName(instrumentationName);
					ip.setIpNo(CommonsUtil.getBigDecimalData(ipNo));
					ip.setDevice(device);
					ip.setInterface2th(interface2th);
					ip.setOrgId(unitOrgId);
					ip.setObjectCode(categoryID);
					ip.setInstruClc(INSTRU_CLC);
					ip.setStartDate(DateBean.getStrDateNotNull(startDate));
					ip.setStopDate(DateBean.getStrDateNotNull(stopDate));
					ip.setRemark(remark);
					ip.setVpnIp(vpnIp);
			}
			
			//ip.setSegmentId(segmentId);
			
			ip.setRlastOdate(new Date());
			ip.setRlastOper(sessionuser.getOperName());
			try {
				addflg = ipUsedService.updateIpUsed(ip);
			} catch (Exception e) {
				e.printStackTrace();
				obj = CommonsUtil.getRrturnJson("", "IP使用表更新失败","", null, null);
				out.print(obj);
				return null;
			}
			
		}else{
			obj = CommonsUtil.getRrturnJson("", "IP使用表ID获取失败","", null, null);
			out.print(obj);
			return null;
		}
		out.print(outCode);
		return  null;
	}
}
