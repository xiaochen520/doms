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
import com.echo.dto.PcCdBoilerT;
import com.echo.dto.PcCdOrgT;
import com.echo.dto.PcRdLoginfoT;
import com.echo.dto.User;
import com.echo.service.BoilerBasicService;
import com.echo.service.CommonService;
import com.echo.service.LogService;
import com.echo.util.AuthorityUtil;
import com.echo.util.CommonsUtil;
import com.echo.util.DateBean;
import com.echo.util.DynamicDataExportUtil;
import com.echo.util.PageVariableUtil;
import com.echo.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

public class BoilerBasicInfoAction extends ActionSupport  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BoilerBasicService boilerBasicService;
	private CommonService commonService;
	private InputStream excelFile = null;
	
	public InputStream getExcelFile() {
		return excelFile;
	}

	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}
	private LogService logService;
	
	public LogService getLogService() {
		return logService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话

	public BoilerBasicService getBoilerBasicService() {
		return boilerBasicService;
	}

	public void setBoilerBasicService(BoilerBasicService boilerBasicService) {
		this.boilerBasicService = boilerBasicService;
	}

	public String getFileName() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd ");
		String downloadFileName = (sf.format(new Date()).toString())+ "锅炉基础信息.xls";
		try {
			downloadFileName = new String(downloadFileName.getBytes(),"ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return downloadFileName;
	}
	public String queryBoilerBasicInfo() throws IOException {
			HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
			HttpServletResponse response=ServletActionContext.getResponse();//响应对象
			response.setCharacterEncoding("utf-8");
			response.setHeader("ContentType","text/xml");
			PrintWriter out = response.getWriter();
			
			String stationNumber = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("oilationname")));
			String boilersName = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("blockstationname")));
			String areablock = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("areablock")));
			//String ghname = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("ghname")));
			String oilname = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("boilersName")));
			String acceptunit = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("acceptunit")));
			
			String jrbz = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("jrbz")));
			String status_value = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("status_value")));
			String dyear = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("dyear")));
			
			int pageNo = 1; //页索引参数名当前页
			String sort = "";		//页排序列名
			String order = "";//页排序方向
			int rowsPerpage = 0; //每页显示条数
			if(request.getParameter("pageNo") != null && !"".equals(request.getParameter("pageNo"))){
				pageNo = Integer.parseInt(request.getParameter("pageNo"));	
			}
			
			sort = StringUtil.isNullUtil(request.getParameter("sort"));
			order = StringUtil.isNullUtil(request.getParameter("order"));
			if( request.getParameter("rowsPerpage") != null && !"".equals( request.getParameter("rowsPerpage"))){
				rowsPerpage = Integer.parseInt(request.getParameter("rowsPerpage"));
			}
			JSONObject jsonobj = null;
			try {
				jsonobj = boilerBasicService.queryBoilerBasicInfo(stationNumber, boilersName, areablock,acceptunit,oilname,jrbz,status_value,dyear,pageNo,sort,order,rowsPerpage);
			} catch (Exception e) {
				this.addFieldError("errermsg", "锅炉基础信息查询数据失败~ 请联系管理员");
				e.printStackTrace();
				return "fail";
			}
			
			if(jsonobj != null){
				out.println(jsonobj);
			}else{
				out.println("");
			}
			
		return null;
	}
	
	public String queryOilSationInfo() throws IOException {
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONArray jsonArr = null;
		try {
			jsonArr = boilerBasicService.queryOilSationInfo();
		} catch (Exception e) {
			this.addFieldError("errermsg", "采油站信息获取失败~");
			e.printStackTrace();
		}
		if(jsonArr != null){
			out.println(jsonArr);
		}else{
			out.println("");
		}
		return null;
	}

	public String searchAcceptunit() throws IOException {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String arg = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("orgid")));
		JSONArray jsonArr = null;
		
		try {
			jsonArr = boilerBasicService.queryAreablockInfo(arg);
		} catch (Exception e) {
			this.addFieldError("errermsg", "区块信息获取失败~");
			e.printStackTrace();
		}
		if(jsonArr != null){
			out.println(jsonArr);
		}else{
			out.println("");
		}
		return null;
	}
	
	public String searchSrqk() throws IOException {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String arg = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("orgid")));
		JSONArray jsonArr = null;
		
		try {
			jsonArr = boilerBasicService.queryAreablockInfo(arg);
		} catch (Exception e) {
			this.addFieldError("errermsg", "区块信息获取失败~");
			e.printStackTrace();
		}
		if(jsonArr != null){
			out.println(jsonArr);
		}else{
			out.println("");
		}
		return null;
	}
	
	public String queryAreablockInfo() throws IOException {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String arg = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("orgid")));
		JSONArray jsonArr = null;
		
		try {
			jsonArr = boilerBasicService.queryAreablockInfo(arg);
		} catch (Exception e) {
			this.addFieldError("errermsg", "区块信息获取失败~");
			e.printStackTrace();
		}
		if(jsonArr != null){
			out.println(jsonArr);
		}else{
			out.println("");
		}
		return null;
	}
	
	
	public String queryStationInfo() throws IOException {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		
		String areaid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("areaid")));
		String selecteValue = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("selecteValue")));
		String oilid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("oilid")));
		JSONArray jsonArr = null;
		try {
			jsonArr = boilerBasicService.queryStationInfo(oilid,areaid,selecteValue);
		} catch (Exception e) {
			// TODO: handle exception
			this.addFieldError("errermsg", "供热站信息获取失败~");
			e.printStackTrace();
		}
		if(jsonArr != null){
			out.println(jsonArr);
		}else{
			out.println("");
		}
		return null;
	}
	
	public String queryBoilersNameInfo() throws IOException {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String boilername = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("orgid")));
		String pageid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("pageid")));
		JSONArray jsonArr = null;
		try {
			jsonArr = boilerBasicService.queryBoilersNameInfo(boilername,pageid);
		} catch (Exception e) {
			this.addFieldError("errermsg", "锅炉信息获取失败~");
			e.printStackTrace();
		}
		if(jsonArr != null){
			out.println(jsonArr);
		}else{
			out.println("");
		}
		return null;
	}

	//根据权限进行页面初始化
	public String pageInit()throws IOException{
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		
		//根据用户权限生成用户权限生成页面布局格式
		User user = (User) session.getAttribute("userInfo");
//		
		String gridJson;
		try {
			gridJson = AuthorityUtil.getGridJson("锅炉基础信息", user, PageVariableUtil.BOILER_PAGE_GRID);
		} catch (Exception e) {
			e.printStackTrace();
			this.addFieldError("errermsg", "系统菜单无法初始化~ 请联系管理员");
			return "fail";
		}
		out.print(gridJson);
		return null;
	}
	
	@SuppressWarnings("unused")
	public String saveOrUpdate() throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONObject obj = new  JSONObject();
//		String outCode = "1";
		String yxz2 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("yxz2")));
		String grzh = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("grzh")));
		String grzhName = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("grzhName")));
		String qkid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("hidsqqk2id")));
		String sqdwid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("hidsqdw2id")));
		String boilerid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("boilerid")));
		String org_id = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("org_id")));
		String boilername2 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("boilername2")));
		String boilertype2 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("boilertype2")));
		String boilercode2 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("boilercode2")));
		String rating_capacity = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("rating_capacity")));
		String injection_allocation_rate = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("injection_allocation_rate")));
		String commissioning_date = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("commissioning_date")));
		//String status_value = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("status_value")));
		String jrbz = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("jrbz")));
		String scadaNode = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("ljjd_s")));
		String factory_nf = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("factory_nf")));
		String remark2 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("remark2")));
		String dyear = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("sjcnn")));
		
		if (!"".equals(boilername2)) {
			
			
			List<Object[]> boilerList = boilerBasicService.searchBoilerByName(boilername2);
			
			//修改
			if(boilerid != null && !"".equals(boilerid)){
				if(boilerList.size() > 1 ){
					obj = CommonsUtil.getRrturnJson("","锅炉名称："+boilername2+" -已存在请使用其他名称" , "",null, null);
					out.print(obj);
					return null;
				}else if(boilerList.size() == 1 && !boilerList.get(0)[0].toString().equals(boilerid)){
					obj = CommonsUtil.getRrturnJson("","锅炉名称："+boilername2+" -已存在请使用其他名称" , "",null, null);
					out.print(obj);
					return null;
				}
				
			//添加
			}else{
				if(boilerList.size() > 0 ){
					obj = CommonsUtil.getRrturnJson("","锅炉名称："+boilername2+" -已存在请使用其他名称" , "",null, null);
					out.print(obj);
					return null;
				}
			}
			
		}
		
		if ("".equals(grzh)) {
			List<Object[]> stationList = boilerBasicService.searchStationByName(grzhName);
			if (stationList != null && stationList.size() > 0) {
				//grzh = stationList.get(0)[0].toString();
				if(!grzh.equals(stationList.get(0)[0].toString())){
					grzh = stationList.get(0)[0].toString();
				}
			}else {
				obj = CommonsUtil.getRrturnJson("","供热站："+grzhName+" -数据获取失败请重新选择供热站" , "",null, null);
				out.print(obj);
				return null;
			}
		}
		
		String statusvalues = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("status_value")));
		
		if (!"".equals(statusvalues)) {
			try {
				statusvalues = commonService.getStatusValueINT(statusvalues);
			} catch (Exception e) {
				obj = CommonsUtil.getRrturnJson("","建设状态："+statusvalues+" -数据获取失败请重新选择建设状态" , "",null, null);
				e.printStackTrace();
				out.print(obj);
				return null;
			}
		}
		List<PcCdBoilerT> boilers = new ArrayList<PcCdBoilerT>();
		PcCdBoilerT boiler;
		PcCdOrgT org = null;
		
		if (!"".equals(boilerid)) {//有锅炉id 为更新操作
			//org = (PcCdOrgT) boilerBasicDao.queryOrg(org_id);
			try {
				boilers = (List<PcCdBoilerT>) boilerBasicService.searchBoilerByName("", boilerid);
			} catch (Exception e) {
				obj = CommonsUtil.getRrturnJson("","锅炉基础信息获取失败" , "",null, null);
				
				e.printStackTrace();
				out.print(obj);
				return null;
			}
			
			boiler = boilers.get(0);
			org = boiler.getPcCdOrgT();
		}else {
			boiler = new PcCdBoilerT();
			org = new PcCdOrgT();
			
		}
		boiler.setDyear(dyear);
		boiler.setBoilerName(boilername2);
		boiler.setBoilerType(boilertype2);
		User sessionuser = (User) session.getAttribute("userInfo");
		boiler.setRlastOper(sessionuser.getOperName());
		boiler.setRlastOdate(new Date());
		boiler.setRemark(remark2);
		if(rating_capacity != null && !"".equals(rating_capacity)){
			boiler.setRatingCapacity(java.math.BigDecimal.valueOf(Double.parseDouble(rating_capacity)));
		}else{
			boiler.setRatingCapacity(null);
		}
		if(injection_allocation_rate != null && !"".equals(injection_allocation_rate)){
			boiler.setInjectionAllocationRate(java.math.BigDecimal.valueOf(Double.parseDouble(injection_allocation_rate)));
			
		}else{
			boiler.setInjectionAllocationRate(null);
		}
		
		if(qkid != null && !"".equals(qkid)){
			boiler.setQkid(qkid);
		}else{
			boiler.setQkid(null);
		}
		if(sqdwid != null && !"".equals(sqdwid)){
			boiler.setAcceptUnit(sqdwid);
		}else{
			boiler.setAcceptUnit(null);
		}
		if(factory_nf != null && !"".equals(factory_nf)){
			boiler.setFactoryUc(factory_nf);
		}else{
			boiler.setFactoryUc(null);
		}
		
		org.setStructurename(boilername2);
		org.setStructuretype((byte) 10);
		org.setPid(grzh);
		//org.setTreeorder("");
		//org.setA2id("");
		if(statusvalues != null && !"".equals(statusvalues)){
			org.setStatusValue(statusvalues);
		}else{
			org.setStatusValue(null);
		}
		
		if(commissioning_date != null && !"".equals(commissioning_date)){
			org.setCommissioningDate(DateBean.getStrDate(commissioning_date));
		}else{
			org.setCommissioningDate(null);
		}
		
		if(scadaNode != null && !"".equals(scadaNode)){
			org.setScadaNode(scadaNode);
		}else{
			org.setScadaNode(null);
		}
		
		if(boilercode2 != null && !"".equals(boilercode2)){
			org.setCode(boilercode2);
		}else{
			org.setCode(null);
		}
		
		if(jrbz != null && !"".equals(jrbz)){
			org.setSwitchInFlag(jrbz);
		}else{
			org.setSwitchInFlag(null);
		}
		org.setRemark(remark2);
		boiler.setPcCdOrgT(org);
		org.setPcCdBoilerT(boiler);
		
		
		boolean operFlag = false;
		try {
			operFlag = boilerBasicService.saveOrUpdate(boiler,"");
		} catch (Exception e) {
			String  errmsg = e.getCause().getCause().toString();
			
			if(!"".equals(errmsg) && errmsg.indexOf("ORA-01438") != -1){
				obj = CommonsUtil.getRrturnJson("","您输入信息超过数据库数据精度范围" ,"", null, null);
			} else{
				obj = CommonsUtil.getRrturnJson("",errmsg ,"", null, null);
				
			}
			
			e.printStackTrace();
			out.print(obj);
			return null;
		}
		
		
		if(operFlag){
				if(boilerid != null && !"".equals(boilerid)){
					//添加系统LOG
					try {
						PcRdLoginfoT log = CommonsUtil.getLogInfo(sessionuser, "3", "锅炉基础信息", boiler.getBoilerid());
						logService.addLog(log);
					} catch (Exception e) {
						e.printStackTrace();
						obj = CommonsUtil.getRrturnJson("","锅炉基础信息修改操作LOG信息添加失败" , "",null, null);
						out.print(obj);
						return null;
					}
				}else{
					//添加系统LOG
					try {
						PcRdLoginfoT log = CommonsUtil.getLogInfo(sessionuser, "1", "锅炉基础信息", boiler.getBoilerid());
						logService.addLog(log);
					} catch (Exception e) {
						e.printStackTrace();
						obj = CommonsUtil.getRrturnJson("","锅炉基础信息添加操作LOG信息添加失败" , "",null, null);
						out.print(obj);
						return null;
					}
					
				}
				
			
		}else{
			if(boilerid != null && !"".equals(boilerid)){
				obj = CommonsUtil.getRrturnJson("","锅炉基础信息："+boilername2+" -修改失败" , "",null, null);
				out.print(obj);
				return null;
			}else{
				
				obj = CommonsUtil.getRrturnJson("","锅炉基础信息："+boilername2+" -添加失败" , "",null, null);
				out.print(obj);
				return null;
			}
		}
		out.print(obj);
		return null;
	}

	
	public String removeBoilerBasicInfo() throws IOException {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		
		String bid = StringUtil.toStr(request.getParameter("boilerid"));
		String orgid = StringUtil.toStr(request.getParameter("org_id"));
		boolean deleteflg = false;
		JSONObject obj = new JSONObject();
		try {
			deleteflg = boilerBasicService.removeBoilerBasicInfo(bid,orgid );
		} catch (Exception e) {
			String errmsg = e.getCause().getCause().toString();
			//ORA-02292: 违反完整约束条件 (NEWPORTAL.FK58_PC_RPD_WELL_SAGDD_T) - 已找到子记录
			if(!"".equals(errmsg) && errmsg.indexOf("ORA-02292") != -1 ){
				obj = CommonsUtil.getRrturnJson("","锅炉基础信息删除失败-锅炉日报中存在子记录" ,"", null, null);
			}else if(!"".equals(errmsg) && errmsg.indexOf("ORA-02292") != -1 ){
				obj = CommonsUtil.getRrturnJson("","锅炉基础信息删除失败-锅炉班报中存在子记录" ,"", null, null);
			} else{
				obj = CommonsUtil.getRrturnJson("",errmsg ,"", null, null);
			}
			e.printStackTrace();
		}
		if(deleteflg){
			
			//添加系统LOG
			try {
				User user1 = (User) session.getAttribute("userInfo");
				PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "2", "锅炉基础信息", bid);
				logService.addLog(log);
			} catch (Exception e) {
				e.printStackTrace();
				obj = CommonsUtil.getRrturnJson("-10003", "","", null, null);
			}
			
			
		}
		out.print(obj);
		return null;
	}
	
	public String cascadeInit() throws IOException {
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONObject jsonArr = null;
		String pageid = StringUtil.isNullUtil(request.getParameter("pageid"));
		try {
			jsonArr = boilerBasicService.cascadeInit(pageid);
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

	public String searchGroupInfo() throws IOException {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONArray jsonArr = null;
		String arg = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("arg")));
		try {
			jsonArr = boilerBasicService.searchGroupInfo(arg);
		} catch (Exception e) {
			this.addFieldError("errermsg", "采油站信息获取失败~");
			e.printStackTrace();
		}
		if(jsonArr != null){
			out.println(jsonArr);
		}else{
			out.println("");
		}
		return null;
	}
	@SuppressWarnings("unchecked")
	public  String onExport()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String acceptunit = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("acceptunit")));
		String blockstationname = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("blockstationname")));
		String boilersName = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("boilersName")));
		String areablock = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("areablock")));
		String jrbz1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("jrbz1")));
		String status_value1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("status_value1")));
		String dyear = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("dyear")));
		String totalNum = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("totalNum")));

		HashMap<String,Object> dataMap = null;
		try {
			dataMap = boilerBasicService.searchOnExport(acceptunit,blockstationname,boilersName,areablock,jrbz1,status_value1,dyear,totalNum);
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
			String templetFilePath = request.getSession().getServletContext().getRealPath("/") + "exceltemplet\\基础信息报表.xls";
//			将用户对象列表输出到workbook的流中，ExcelUtil类进行了封装，后续分析！
			java.io.ByteArrayOutputStream baos = DynamicDataExportUtil.basicDataExporReport(dataLsit,templetFilePath,"锅炉基础信息");
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
}