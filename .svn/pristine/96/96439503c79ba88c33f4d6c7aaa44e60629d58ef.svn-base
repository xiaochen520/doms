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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.echo.dto.PcRdLoginfoT;
import com.echo.dto.PcRpdRuleWellProdT;
import com.echo.dto.PcRpdRuleWellProdbT;
import com.echo.dto.User;
import com.echo.service.LogService;
import com.echo.service.RuleWellService;
import com.echo.util.AuthorityUtil;
import com.echo.util.CommonsUtil;
import com.echo.util.DateBean;
import com.echo.util.DynamicDataExportUtil;
import com.echo.util.ExcelSheetParser;
import com.echo.util.PageVariableUtil;
import com.echo.util.PropertyUtil;
import com.echo.util.StringUtil;

public class RuleWellRPDAction {
	private RuleWellService ruleWellService;
	private InputStream excelFile = null;
	
	private LogService logService;

	public void setRuleWellService(RuleWellService ruleWellService) {
		this.ruleWellService = ruleWellService;
	}

	public RuleWellService getRuleWellService() {
		return ruleWellService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	
	public String getFileName() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd ");
		String downloadFileName = (sf.format(new Date()).toString())+ "采出班报-稠油日报.xls";
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
	
	@SuppressWarnings("unchecked")
	public String searchRuleRPD() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
		User user = (User) session.getAttribute("userInfo");
		String deptype = user.getDepType();   //用户所属部门类型   15：班组  4采油站
		
		String oil = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("oilstationname")));
		String banzu = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("groupname")));
		String station = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("blockstationname")));
		String boilersName = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("boilersName")));
		String well = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("wellnum")));
		
		String startDate = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("startDate")));
		String endDate = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("endDate")));
		String totalNum = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("totalNum")));
		String shstatus = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("shstatus")));
		
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
		if("".equals(oil)  && "".equals(banzu) 
				 && "".equals(station) && "".equals(boilersName) && "".equals(well)
				 && "".equals(startDate) && "".equals(endDate) && "".equals(totalNum)){
			out.println("");
			return null;
		}
		HashMap<String,Object> dataMap = null;
		try {
			dataMap = ruleWellService.searchRullWellRPD(oil,banzu,station,boilersName,well,shstatus,startDate,endDate,pageNo,sort,order,rowsPerpage,totalNum,deptype);
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
			String templetFilePath = request.getSession().getServletContext().getRealPath("/") + "exceltemplet\\ExportRBWH\\采出班报-稠油日报.xls";
//			将用户对象列表输出到workbook的流中，ExcelUtil类进行了封装，后续分析！
			java.io.ByteArrayOutputStream baos = DynamicDataExportUtil.dailyRuleWHDataExporReport(dataLsit,templetFilePath,"稠油日报");
			if(baos != null){
				byte[] ba = baos.toByteArray();
				excelFile = new ByteArrayInputStream(ba);
				baos.flush();
				baos.close();
			}
			return "excel";
		}
		out.println("");
		return null;
	}
	
	
	
	public String Dataready() throws Exception {

		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONObject obj = new JSONObject();
//		String outCode = "1";
		List<String> rusl = new ArrayList<String>();
		HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
		User user = (User) session.getAttribute("userInfo");
		String bm = user.getDepname();
		String nowdata = DateBean.getSystemTime1();
		String sql = " select * from PC_RPD_RULE_WELL_PROD_T t where 1=1 and t.REPORT_DATE = to_date('"+nowdata+"','YYYY-MM-DD')";
		//String sql = " select  *  from  pc_rpd_rule_well_prod_v t where TEAMNAME ='"+bm+"' and t.REPORT_DATE = to_date('"+nowdata+"','YYYY-MM-DD')";
		//获取当天数据
				List<Object[]> todayDatas = AuthorityUtil.commonssql(sql);
				if(todayDatas != null && todayDatas.size() > 0){
					obj = CommonsUtil.getRrturnJson("","一天只能进行一次数据准备,您已经准备过一次了。。。" ,"", null, null);
					out.print(obj);
					return null;
			}
		
		try {
			rusl  = ruleWellService.Dataready("p_prepare_rule_well_prod", DateBean.getSystemTime1(), user.getUserid());
		} catch (Exception e) {
			e.printStackTrace();
			//当前日期的数据准备
			obj = CommonsUtil.getRrturnJson("","当前日期的数据准备失败" ,"", null, null);
		}
		
		if(rusl != null && rusl.size()>0 ){
			if(Integer.parseInt(rusl.get(0).toString()) == 0){
				obj = CommonsUtil.getRrturnJson("",rusl.get(1).toString() ,"", null, null);
			}else if(Integer.parseInt(rusl.get(0).toString()) == 1){
				obj = CommonsUtil.getRrturnJson("","" ,rusl.get(1).toString(), null, null);
			}
			
		}else{
			obj = CommonsUtil.getRrturnJson("","当前日期的数据准备失败" ,"", null, null);
		}
			out.println(obj);
			return null;
		

		
		
	}
	/**
	 * 自动化提取
	 * @return
	 * @throws Exception
	 */
	public String Automate() throws Exception {

		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONObject obj = new JSONObject();
//		String outCode = "1";
		List<String> rusl = new ArrayList<String>();
		HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
		User user = (User) session.getAttribute("userInfo");
		//当前系统时间
		 String nowdata = DateBean.getSystemTime1();
		 //判断B表是否存在当天数据， 如果不存在提示--请联系班组人员尽快进行数据审核
		String sql = " select * from pc_rpd_rule_well_prodb_t t where 1=1 and t.REPORT_DATE = to_date('"+nowdata+"','YYYY-MM-DD')";
		//获取当天数据
		List<Object[]> todayDatas = AuthorityUtil.commonssql(sql);
		
		if(todayDatas == null || todayDatas.size() <= 0){
			obj = CommonsUtil.getRrturnJson("","请联系班组人员尽快进行数据审核" ,"", null, null);
			out.println(obj);
			return null;
		}
		
		
		boolean stautsflg = AuthorityUtil.checkeUserStauts(nowdata, "自动化提取", user);
		
		if(stautsflg == false){
			obj = CommonsUtil.getRrturnJson("","每天只能进行一次自动化提取，今天已执行过该操作" ,"", null, null);
			out.println(obj);
			return null;
			
		}
		try {
			rusl  = ruleWellService.Automate("p_prepare_rule_prod_auto", nowdata, user.getUserid(),"RULE_AUTO_SELETE_TIME");
			
		} catch (Exception e) {
			e.printStackTrace();
			//当前日期的数据准备
			obj = CommonsUtil.getRrturnJson("","当前日期的自动化提取失败" ,"", null, null);
			out.println(obj);
			return null;
		}
		
		if(rusl != null && rusl.size() > 0 ){
			int sign = -1;
			if(Integer.parseInt(rusl.get(0).toString()) == 1){
				try {
					sign = AuthorityUtil.updateUserStauts(nowdata, "自动化提取", session);
				} catch (Exception e) {
					obj = CommonsUtil.getRrturnJson("","自动化提取后部门功能锁定失败" ,"", null, null);
					out.println(obj);
					return null;
				}
				
				obj = CommonsUtil.getRrturnJson("","" ,rusl.get(1).toString(), null, null);
				
			}else{
				obj = CommonsUtil.getRrturnJson("",rusl.get(1).toString() ,"", null, null);
				out.println(obj);
				return null;
			}
			
		}else{
			obj = CommonsUtil.getRrturnJson("","当前日期的自动化提取失败" ,"", null, null);
			out.println(obj);
			return null;
		}
			
			//添加系统LOG
			try {
				PcRdLoginfoT log = CommonsUtil.getLogInfo(user, "12", "稠油日报", "");
				logService.addLog(log);
			} catch (Exception e) {
				e.printStackTrace();
				obj = CommonsUtil.getRrturnJson("","稠油日报自动化提取LOG添加失败" ,"", null, null);
				out.println(obj);
				return null;
			}
			out.println(obj);
			return null;	
		
		
	}
	//根据权限进行页面初始化
	public String ruleWellRPDpageInit()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		String arg = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("arg")));
//		String pageName = "常规油井日报";
		HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
		//根据用户权限生成用户权限生成页面布局格式
		User user = (User) session.getAttribute("userInfo");
		
		String gridJson = null;
		try {
			if (!"wh".equals(arg)) {
				gridJson = AuthorityUtil.getGridJson1("MENU123", user, PageVariableUtil.RULEWELL_PAGE_GRID);
			}
			else {
//				pageName = "常规油井日报维护";
				gridJson = AuthorityUtil.getGridJson1("MENU124", user, PageVariableUtil.RULEWELL_WH_PAGE_GRID);
			}
			
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
	
	public String importExcel()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String path = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("excelPath")));
		
		if(path != null && !"".equals(path)){
			path = path.replaceAll("\\\\", "\\\\\\\\");
			File file = new File(path);
//			   String templetFilePath = request.getSession().getServletContext().getRealPath("/") + "exceltemplet\\基础信息报表.xls";
			   ExcelSheetParser parser = new ExcelSheetParser(file);
			   List<List> datas = parser.getDatasInSheet(0);

			   for(int i=0;i<datas.size();i++){//显示数据
			    List row = datas.get(i);
			    for(short n=0;n<row.size() ;n++){
			     Object value = row.get(n);
			     String data = String.valueOf(value);
			     System.out.print(data +"\t");
			    }
			    System.out.println();
			   }
		}
		 
		
		return null;
		
	}
	public String ButtonInit()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		String arg = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("arg")));
		String pageName = "SAGD井日报";
		
		//根据用户权限生成用户权限生成页面布局格式
		HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
		User user = (User) session.getAttribute("userInfo");
		
		String gridJson = null;
		try {
			if (!"wh".equals(arg)) {
				gridJson = AuthorityUtil.getGridJson(pageName, user, PageVariableUtil.SAGDDRPDZQJ_PAGE_GRID);
			}
			else {
				pageName = "SAGD井日报维护";
				gridJson = AuthorityUtil.getGridJson(pageName, user, PageVariableUtil.SAGDDRPDWHALL_PAGE_GRID);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			outCode = "-10004";
		}
		
		if(gridJson != null && "1".equals(outCode)){
			//System.out.println(gridJson);
			//out.print(gridJson);
		}else{
			//out.print(outCode);
		}
		return null;
	}
	
	
	public String addOrUpdateRuleRPD() throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		
		String wellname = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("WellNameEdit")));
		HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
		User user = (User) session.getAttribute("userInfo");
		//user.getDepname();
		String deptype = user.getDepType();   //用户所属部门类型   15：班组  4采油站
		String bm = user.getDepname();
		JSONObject obj = new JSONObject();
		
		String nowdata = DateBean.getSystemTime1();
		//权限锁定判断 判断当前用户 导入操作是否在审核之后  
//		boolean lockflg = AuthorityUtil.checkeUserStauts(nowdata, "", user);
//		
//		if(lockflg == false){
//			//this.addFieldError("errermsg", "当日数据已审核，不能进行EXCEL导入操作! ");
//			//return "fail";
//			obj = CommonsUtil.getRrturnJson("","数据已经审核过,不能添加和修改操作" ,"", null, null);
//			out.print(obj);
//			return null;
//		}
		String ouditSql = null;
		if("15".equals(deptype)){
			
			 ouditSql = " select * from PC_RPD_RULE_WELL_PROD_T t where 1=1 and t.REPORT_DATE = to_date('"+nowdata+"','YYYY-MM-DD')" +
			 		    " and  class_check_oper   is   not null";
			// ouditSql = " select  *  from  pc_rpd_rule_well_prod_v t  where 1=1 and  t.REPORT_DATE = to_date('"+nowdata+"','YYYY-MM-DD') and TEAMNAME ='"+bm+"'"+
			 //				" and  class_check_oper   is   not null";

		}else{
			 ouditSql = " select * from PC_RPD_RULE_WELL_PRODB_T t where 1=1 and t.REPORT_DATE = to_date('"+nowdata+"','YYYY-MM-DD')" +
			 		" and geology_check_oper   IS  not  null ";
			// ouditSql = " select  *  from  pc_rpd_rule_well_prod_v t  where 1=1 and  t.REPORT_DATE = to_date('"+nowdata+"','YYYY-MM-DD') and TEAMNAME ='"+bm+"'"
			 	//			" and geology_check_oper   IS  not  null ";

		}
		List<Object[] >ouditDatas = AuthorityUtil.commonssql(ouditSql);
		if(ouditDatas !=null && ouditDatas.size()>0){
			//this.addFieldError("errermsg", "当日数据已审核，不能进行EXCEL导入操作!! ");
			obj = CommonsUtil.getRrturnJson("","数据已经审核过,不能添加和修改操作" ,"", null, null);
			out.print(obj);
			return null;
		}
		if("15".equals(deptype)){
			String sql = " select * from PC_RPD_RULE_WELL_PROD_T t where 1=1 and t.REPORT_DATE = to_date('"+nowdata+"','YYYY-MM-DD')";
			// ouditSql = " select  *  from  pc_rpd_rule_well_prod_v t  where 1=1 and  t.REPORT_DATE = to_date('"+nowdata+"','YYYY-MM-DD') and TEAMNAME ='"+bm+"'";
			
			//获取当天数据
			List<Object[]> todayDatas = AuthorityUtil.commonssql(sql);
			if(todayDatas != null && todayDatas.size() <=0){
				obj = CommonsUtil.getRrturnJson("","请先数据准备" ,"", null, null);
				out.print(obj);
				return null;
			}
		}else{
			String sql = " select * from PC_RPD_RULE_WELL_PRODB_T t where 1=1 and t.REPORT_DATE = to_date('"+nowdata+"','YYYY-MM-DD')";
			//String sql = " select  *  from  pc_rpd_rule_well_prod_v t  where 1=1 and  t.REPORT_DATE = to_date('"+nowdata+"','YYYY-MM-DD') and TEAMNAME ='"+bm+"'"

			//获取当天数据
			List<Object[]> todayDatas = AuthorityUtil.commonssql(sql);
			if(todayDatas != null && todayDatas.size() <=0){
				obj = CommonsUtil.getRrturnJson("","请联系班组人员确定已经审核过 ？" ,"", null, null);
				out.print(obj);
				return null;
			}
		}
		//用户添加修改生产单位权限范围（组织结构ID）
		List<Object[]> authorityDatas = null;
		authorityDatas = AuthorityUtil.updateDatasAuthority(user,session);
		
		if(wellname != null && !"".equals(wellname)){
			if(AuthorityUtil.authorityDos(wellname, authorityDatas) == false){
				obj = CommonsUtil.getRrturnJson("","您没有对改生成单位进行添加或修改的权限，请联系管理员添加权限" ,"", null, null);
				out.print(obj);
				return null;
			}
		}else{
			obj = CommonsUtil.getRrturnJson("","井名不为空，请选择要操作日报的井名" ,"", null, null);
			out.print(obj);
			return null;
			
		}

		String RULEWELLRPD  = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("THINWELLRPD")));
		String RPDRULEWELLPRODID  = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("RPDRULEWELLPRODID")));
		
		String WellName  = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("WellNameEdit")));
  	   	String rq = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("rq")));
  	   	//获取当前系统时间，判断 日期不为当天 不能操作
  	   		Date today = new Date();
  			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
  	   		String td = sdf.format(today);
  	   		if(!rq.equals(td)){
  	   		 obj = CommonsUtil.getRrturnJson("","日期："+rq+" 不为当前日期-只能操作当前的数据" , "",null, null);
				 out.print(obj);
				return null;
  	   		}
  		String PROTT = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("PROTT")));
  		String STROKE = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("STROKE")));
  		String AT_TIMES = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("AT_TIMES")));
  		String NOZZLE = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("NOZZLE")));
  		String PRESSURE = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("PRESSURE")));
  		String TCPR = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("TCPR")));
  		String BACKPRE = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("BACKPRE")));
  		String TOT = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("TOT")));
  		String NFV = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("NFV")));
  		String GASP = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("GASP")));
  		String SAMPLI = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("SAMPLI")));
  		String PUMPING_TIME = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("PUMPING_TIME")));
  		String PUMPING_MACHINE = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("PUMPING_MACHINE")));
  		String PUMPING_DESCRIPTION = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("PUMPING_DESCRIPTION")));
  		String REMARK = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("REMARK")));
  		
  		//井的组织结构ID 也是报表的井ID
  		String orgId = ruleWellService.searchRuleOrgId(WellName);
  		
		
		
		//if("3".equals(deptype) ||"4".equals(deptype) || "15".equals(deptype)){
		//}
  		String TableName = "";
  		
  		if("15".equals(deptype)){
  			List<PcRpdRuleWellProdT> rpdList = null;
  	  		PcRpdRuleWellProdT rpdR = null;
  	  		
  			if(RPDRULEWELLPRODID !=null && !"".equals(RPDRULEWELLPRODID)){
  	  			//修改
  	  			try {
  	  				rpdList = ruleWellService.searchOnlyNameRq(orgId,rq,"");
  				} catch (Exception e) {
  					e.printStackTrace();
  					obj = CommonsUtil.getRrturnJson("","未知错误，请联系管理员" ,"", null, null);
						out.print(obj);
						return null;
  	  			}
  				if(rpdList !=null && rpdList.size()>0){
  					if( rpdList.size()==1){
  						if(RPDRULEWELLPRODID.equals(rpdList.get(0).getRpdRuleWellProdId())){
  							rpdR =rpdList.get(0); 
  							if(rpdR.getClassCheckOper() !=null && !"".equals(rpdR.getClassCheckOper())){
  								obj = CommonsUtil.getRrturnJson("","数据已经审核过,不能添加和修改操作" ,"", null, null);
  								out.print(obj);
  								return null;
  							}
  							
  						}else{
  							obj = CommonsUtil.getRrturnJson("", "当天此井已存在","", null, null);
  							out.print(obj);
  							return null;
  						}
  					}else{
  						obj = CommonsUtil.getRrturnJson("", "当天此井已存在","", null, null);
  						out.print(obj);
  						return null;
  					}
  					
  				}else{
  					rpdList = ruleWellService.searchOnlyNameRq("","",RPDRULEWELLPRODID);
  					rpdR =rpdList.get(0); 
  				}
  	  			
  	  		}else{
  	  			//添加
  	  			try {
  	  				rpdList = ruleWellService.searchOnlyNameRq("",rq,"");
				} catch (Exception e) {
					e.printStackTrace();
				}
  	  			
  	  			if(rpdList !=null && rpdList.size()>0){
  	  				try {
  	  					rpdList = ruleWellService.searchOnlyNameRq(orgId,rq,"");
					} catch (Exception e) {
						e.printStackTrace();
					}
					if(rpdList !=null && rpdList.size()>0){
						 obj = CommonsUtil.getRrturnJson("","井名称："+WellName+" -当天已存在请使用其他名称" , "",null, null);
							out.print(obj);
							return null;
					}else{
  	  					rpdR = new PcRpdRuleWellProdT();
  	  					rpdR.setLiquidFlag(java.math.BigDecimal.valueOf(0));
  	  					//java.math.BigDecimal.valueOf(Double.parseDouble(rating_capacity))
					}
  	  			}else{
  	  				obj = CommonsUtil.getRrturnJson("", "请数据准备","", null, null);
					out.print(obj);
					return null;
  	  			}
  	  		}
  	  		
  	  		rpdR.setOrgId(orgId);
  	  		rpdR.setReportDate(DateBean.getStrDate(rq));
  	  		rpdR.setWellName(wellname);
  	  		rpdR.setProcTimeRation(CommonsUtil.getBigDecimalData(PROTT));
  	  		rpdR.setStroke(CommonsUtil.getBigDecimalData(STROKE));
  	  		rpdR.setJigFrequency(CommonsUtil.getBigDecimalData(AT_TIMES));
  	  		rpdR.setFlowNipple(CommonsUtil.getBigDecimalData(NOZZLE));
  	  		rpdR.setTubingPres(CommonsUtil.getBigDecimalData(PRESSURE));
  	  		rpdR.setCasingPres(CommonsUtil.getBigDecimalData(TCPR));
  	  		rpdR.setBackPres(CommonsUtil.getBigDecimalData(BACKPRE));
  	  		rpdR.setOilTemp(CommonsUtil.getBigDecimalData(TOT));
  	  		rpdR.setLiquidOutput(CommonsUtil.getBigDecimalData(NFV));
  	  		rpdR.setGasOutput(CommonsUtil.getBigDecimalData(GASP));
  	  		rpdR.setSampling(SAMPLI);
  	  		rpdR.setRuntime(CommonsUtil.getBigDecimalData(PUMPING_TIME));
  	  		rpdR.setPumpErrorTime(CommonsUtil.getBigDecimalData(PUMPING_MACHINE));
  	  		rpdR.setPumpErrorDesc(PUMPING_DESCRIPTION);
  	  		rpdR.setRemark(REMARK);
  	  		//此处记录上次操作者
  	  		rpdR.setRlastOper(user.getOperName());
  	  		rpdR.setRlastOdate( new Date());
  	  		
  	  		boolean  flag = true;
  	  		
  	  		try {
  	  			flag = ruleWellService.saveRpdRull(rpdR);
  			} catch (Exception e) {
  				e.printStackTrace();
  				//obj = CommonsUtil.getRrturnJson("", "操作失败","", null, null);
  				obj = CommonsUtil.getRrturnJson("", "ORA-01438: 值大于为此列指定的允许精度","", null, null);
  				out.print(obj);
  				return null;
  			}
  			if(RPDRULEWELLPRODID !=null && !"".equals(RPDRULEWELLPRODID) ){
	  			try {
					User user1 = (User) session.getAttribute("userInfo");
					PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "3", "班组日报修改成功", rpdR.getRpdRuleWellProdId());
					logService.addLog(log);
				} catch (Exception e) {
					e.printStackTrace();
					obj = CommonsUtil.getRrturnJson("", "添加日志失败","", null, null);
	  				out.print(obj);
	  				return null;
				}
  			}else{
  				try {
					User user1 = (User) session.getAttribute("userInfo");
					PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "1", "班组日报添加成功", rpdR.getRpdRuleWellProdId());
					logService.addLog(log);
				} catch (Exception e) {
					e.printStackTrace();
					obj = CommonsUtil.getRrturnJson("", "添加日志失败","", null, null);
	  				out.print(obj);
	  				return null;
				}
  			}
  		}else{
  			
  			List<PcRpdRuleWellProdbT> rpdbList = null;
  	  		PcRpdRuleWellProdbT rpdbR = null;
  	  		
  			if(RPDRULEWELLPRODID !=null && !"".equals(RPDRULEWELLPRODID)){
  	  			//修改
  	  			try {
  	  				rpdbList = ruleWellService.searchOnlyNameRqB(orgId,rq,"");
  				} catch (Exception e) {
  					e.printStackTrace();
  	  			}
  				if(rpdbList !=null && rpdbList.size()>0){
  					if( rpdbList.size()==1){
  						if(RPDRULEWELLPRODID.equals(rpdbList.get(0).getRpdRuleWellProdId())){
  							rpdbR =rpdbList.get(0); 
  							if(rpdbR.getGeologyCheckOper() !=null && !"".equals(rpdbR.getGeologyCheckOper())){
  								obj = CommonsUtil.getRrturnJson("","数据已经审核过,不能添加和修改操作" ,"", null, null);
  								out.print(obj);
  								return null;
  							}
  						}else{
  							obj = CommonsUtil.getRrturnJson("", "当天此井已存在","", null, null);
  							out.print(obj);
  							return null;
  						}
  					}else{
  						obj = CommonsUtil.getRrturnJson("", "当天此井已存在","", null, null);
  						out.print(obj);
  						return null;
  					}
  					
  				}else{
  					rpdbList = ruleWellService.searchOnlyNameRqB("","",RPDRULEWELLPRODID);
  					rpdbR =rpdbList.get(0); 
  				}
  	  			
  	  		}else{
  	  			//添加
  	  			try {
  	  				rpdbList = ruleWellService.searchOnlyNameRqB("",rq,"");
				} catch (Exception e) {
					e.printStackTrace();
				}
		  	  		if(rpdbList !=null && rpdbList.size()>0){
		  	  			try {
		  	  				rpdbList = ruleWellService.searchOnlyNameRqB(orgId,rq,"");
						} catch (Exception e) {
							e.printStackTrace();
						}
						if(rpdbList !=null && rpdbList.size()>0){
							obj = CommonsUtil.getRrturnJson("","井名称："+WellName+" -当天已存在请使用其他名称" , "",null, null);
							out.print(obj);
							return null;
						}else{
							rpdbR = new PcRpdRuleWellProdbT();
							rpdbR.setLiquidFlag(java.math.BigDecimal.valueOf(0));
						}
		  	  		}else{
			  	  		obj = CommonsUtil.getRrturnJson("", "请联系班组人员尽快审核","", null, null);
						out.print(obj);
						return null;
					}
  	  		}
  	  		
  	  		rpdbR.setOrgId(orgId);
  	  		rpdbR.setReportDate(DateBean.getStrDate(rq));
  	  		rpdbR.setWellName(wellname);
  	  		rpdbR.setProcTimeRation(CommonsUtil.getBigDecimalData(PROTT));
  	  		rpdbR.setStroke(CommonsUtil.getBigDecimalData(STROKE));
  	  		rpdbR.setJigFrequency(CommonsUtil.getBigDecimalData(AT_TIMES));
  	  		rpdbR.setFlowNipple(CommonsUtil.getBigDecimalData(NOZZLE));
  	  		rpdbR.setTubingPres(CommonsUtil.getBigDecimalData(PRESSURE));
  	  		rpdbR.setCasingPres(CommonsUtil.getBigDecimalData(TCPR));
  	  		rpdbR.setBackPres(CommonsUtil.getBigDecimalData(BACKPRE));
  	  		rpdbR.setOilTemp(CommonsUtil.getBigDecimalData(TOT));
  	  		rpdbR.setLiquidOutput(CommonsUtil.getBigDecimalData(NFV));
  	  		rpdbR.setGasOutput(CommonsUtil.getBigDecimalData(GASP));
  	  		rpdbR.setSampling(SAMPLI);
  	  		rpdbR.setRuntime(CommonsUtil.getBigDecimalData(PUMPING_TIME));
  	  		rpdbR.setPumpErrorTime(CommonsUtil.getBigDecimalData(PUMPING_MACHINE));
  	  		rpdbR.setPumpErrorDesc(PUMPING_DESCRIPTION);
  	  		rpdbR.setRemark(REMARK);
  	  		//此处记录上次操作者
  	  		rpdbR.setRlastOper(user.getOperName());
  	  		rpdbR.setRlastOdate( new Date());
  	  		
  	  		boolean  flag = true;
  	  		try {
  	  			flag = ruleWellService.saveRpdRullB(rpdbR);
  			} catch (Exception e) {
  				e.printStackTrace();
  				obj = CommonsUtil.getRrturnJson("", "ORA-01438: 值大于为此列指定的允许精度","", null, null);
  				out.print(obj);
  				return null;
  			}
  			if(RPDRULEWELLPRODID !=null && !"".equals(RPDRULEWELLPRODID)){
	  			try {
					User user1 = (User) session.getAttribute("userInfo");
					PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "3", "地质组日报修改成功", rpdbR.getRpdRuleWellProdId());
					logService.addLog(log);
				} catch (Exception e) {
					e.printStackTrace();
					obj = CommonsUtil.getRrturnJson("", "添加日志失败","", null, null);
	  				out.print(obj);
	  				return null;
				}
  			}else{
  				try {
					User user1 = (User) session.getAttribute("userInfo");
					PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "1", "地质组日报添加成功", rpdbR.getRpdRuleWellProdId());
					logService.addLog(log);
				} catch (Exception e) {
					e.printStackTrace();
					obj = CommonsUtil.getRrturnJson("", "添加日志失败","", null, null);
	  				out.print(obj);
	  				return null;
				}
  			}
  		
  		}
  		out.print("1");
		return null;
	}
	public String removeSagddRPD() throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String sagddid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("sagddid")));
//		String datatype = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("datatype")));
		boolean operFlag = true; 
		try {
			//operFlag = ruleWellService.removeSagddRPD(sagddid);
		} catch (Exception e) {
			e.printStackTrace();
			out.println("-13403");//返回错误代码
		}
		
		if(operFlag){
			out.println("1");
		}
		return null;
	}
	
	public String dayreport() throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		List<String> result = new ArrayList<String>(); 
		JSONObject obj = new JSONObject();
		try {
			//result = ruleWellService.dayreport();
		} catch (Exception e) {
			e.printStackTrace();
			obj = CommonsUtil.getRrturnJson("","当日日报生成失败" ,"", null, null);
		}
		
		if(result != null && result.size()>0){
			if("1".equals(result.get(0).toString())){
				obj = CommonsUtil.getRrturnJson("","" ,result.get(1).toString(), null, null);
			}else{
				obj = CommonsUtil.getRrturnJson("",result.get(1).toString() ,"", null, null);
			}
			
	
		}else{
			obj = CommonsUtil.getRrturnJson("","当日日报生成失败" ,"", null, null);
		}
		out.println(obj);
		return null;
	}
	
	public String queryTeamInfo() throws IOException {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONArray jsonArr = null;
		String arg = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("oilid")));
		try {
			jsonArr = ruleWellService.queryTeamInfo(arg);
		} catch (Exception e) {
//			this.addFieldError("errermsg", "采油站信息获取失败~");
			e.printStackTrace();
		}
		if(jsonArr != null){
			out.println(jsonArr);
		}else{
			out.println("");
		}
		return null;
	}
	public String removeRullRPDId()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String rullRPDId = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("RULEWELLRPDID")));
		
		String wellname = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("wellName")));
		HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
		User user = (User) session.getAttribute("userInfo");
		JSONObject  obj = new JSONObject();
		String deptype = user.getDepType();   //用户所属部门类型   15：班组  4采油站
		if("15".equals(deptype)){
			List<PcRpdRuleWellProdT> rpdList = null;
  	  		PcRpdRuleWellProdT rpdR = null;
  	  			rpdList = ruleWellService.searchOnlyNameRq("","",rullRPDId);
  	  			if(rpdList !=null && rpdList.size()>0){
  	  				rpdR = rpdList.get(0);
  	  				if(rpdR.getClassCheckOper() !=null && !"".equals(rpdR.getClassCheckOper())){
  	  					obj = CommonsUtil.getRrturnJson("","数据已经审核过,不能删除操作" ,"", null, null);
						out.print(obj);
						return null;
  	  				}
  	  			}
		}else{
			List<PcRpdRuleWellProdbT> rpdbList = null;
  	  		PcRpdRuleWellProdbT rpdbR = null;
  	  			rpdbList = ruleWellService.searchOnlyNameRqB("","",rullRPDId);
  	  			if(rpdbList !=null && rpdbList.size()>0){
  	  				rpdbR = rpdbList.get(0);
	  	  			if(rpdbR.getGeologyCheckOper() !=null && !"".equals(rpdbR.getGeologyCheckOper())){
						obj = CommonsUtil.getRrturnJson("","数据已经审核过,不能删除操作" ,"", null, null);
						out.print(obj);
						return null;
	  	  			}
				}
		}
		
		String nowdata = DateBean.getSystemTime1();
		//权限锁定判断 判断当前用户 导入操作是否在审核之后  
		boolean lockflg = AuthorityUtil.checkeUserStauts(nowdata, "", user);
		
		if(lockflg == false){
			//this.addFieldError("errermsg", "当日数据已审核，不能进行EXCEL导入操作! ");
			//return "fail";
			obj = CommonsUtil.getRrturnJson("","数据已经审核过,不能删除操作" ,"", null, null);
			out.print(obj);
			return null;
			
		}
		//用户添加修改生产单位权限范围（组织结构ID）
		List<Object[]> authorityDatas = null;
		authorityDatas = AuthorityUtil.updateDatasAuthority(user,session);
		
		if(wellname != null && !"".equals(wellname)){
			if(AuthorityUtil.authorityDos(wellname, authorityDatas) == false){
				obj = CommonsUtil.getRrturnJson("","您没有对改生成单位进行添加或修改的权限，请联系管理员添加权限" ,"", null, null);
				out.print(obj);
				return null;
			}
		}else{
			obj = CommonsUtil.getRrturnJson("","井名不为空，请选择要操作日报的井名" ,"", null, null);
			out.print(obj);
			return null;
			
		}
	
		
		boolean Flag = true; 
		try {
			Flag = ruleWellService.removeRullRPDId(rullRPDId,deptype);
		} catch (Exception e) {
			e.printStackTrace();
			obj = CommonsUtil.getRrturnJson("","删除失败" ,"", null, null);
			out.print(obj);
			return null;
		}
		try {
			User user1 = (User) session.getAttribute("userInfo");
			PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "2", "稠油井删除日志", rullRPDId);
			logService.addLog(log);
		} catch (Exception e) {
			e.printStackTrace();
			obj = CommonsUtil.getRrturnJson("-10003", "","", null, null);
		}
		if(Flag){
			out.println("1");
		}
		return null;
	
	}
}
