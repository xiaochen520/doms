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

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.echo.dto.PcRdLoginfoT;
import com.echo.dto.PcRpdGasDailyT;
import com.echo.dto.PcRpdGasDailybT;
import com.echo.dto.User;
import com.echo.service.GasDailyWHService;
import com.echo.service.LogService;
import com.echo.util.AuthorityUtil;
import com.echo.util.CommonsUtil;
import com.echo.util.DateBean;
import com.echo.util.DynamicDataExportUtil;
import com.echo.util.PageVariableUtil;
import com.echo.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

public class GasDailyWHAction extends ActionSupport{
	private GasDailyWHService gasDailyWHService;
	private LogService logService;
	
	public void setGasDailyWHService(GasDailyWHService gasDailyWHService) {
		this.gasDailyWHService = gasDailyWHService;
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
		String downloadFileName = (sf.format(new Date()).toString())+ "注汽井日报维护.xls";
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
		try { //注汽井日报维护
			gridJson = AuthorityUtil.getGridJson1("MENU040", user, PageVariableUtil.GASDAILYWH_T);
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
	public String searchRRDData() throws IOException {
		//deptype 的时候查询A表
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
		User user = (User) session.getAttribute("userInfo");
		String deptype = user.getDepType();   //用户所属部门类型   15：班组  4采油站
		
		String oilName = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("oilstationname")));
		String groupTeam = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("banzu")));
		String stationName = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("blockstationname")));
		String maniName = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("maniName")));
		String wellName = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("wellName")));

		String stime=StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("date")));
		String etime=StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("date1")));
		String totalNum = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("totalNum")));
//		if("".equals(oilName)  && "".equals(groupTeam) 
//				 && "".equals(stationName) && "".equals(maniName) && "".equals(wellName)
//				 && "".equals(stime) && "".equals(etime)){
//			out.println("");
//			return null;
//		}
//		if(totalNum !=null && !"".equals(totalNum)){
//		if(stime.equals("")||stime.equals("undefined")||stime==null){
//			Date date=new Date();
//			SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
//			stime=sf.format(date);
//			
//		}
//		if(etime.equals("")||etime.equals("undefined")||etime==null){
//			Date date=new Date();
//			SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
//			etime=sf.format(date);
//			
//		}
//		}
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
			dataMap = gasDailyWHService.searchData(oilName,groupTeam,stationName,maniName,wellName,stime,etime,pageNo,sort,order,rowsPerpage,totalNum,deptype);
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
			String templetFilePath = request.getSession().getServletContext().getRealPath("/") + "exceltemplet\\ExportRBWH\\注入班报-注汽日报.xls";
//			将用户对象列表输出到workbook的流中，ExcelUtil类进行了封装，后续分析！                                                              
			java.io.ByteArrayOutputStream baos = DynamicDataExportUtil.dailyGasDataExporReport(dataLsit,templetFilePath,"注汽日报");
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
	public String saveOrUpdate()throws Exception{

		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode ="1";
		String wellrpdid = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("GASWELLRPDID"))); 
		//注水井ID
		//String waterfloodingWellid = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("waterfloodingWellid"))); 
		JSONObject obj = new JSONObject();

		String wellname = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("wellname"))); //ID
		
		HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
		User user = (User) session.getAttribute("userInfo");
		
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
		String deptype = user.getDepType();   //用户所属部门类型   15：班组  4采油站
		
		String ouditSql = null;
		if("15".equals(deptype)){
			
			 ouditSql = " select * from pc_rpd_gas_daily_t t where 1=1 and t.REPORT_DATE = to_date('"+nowdata+"','YYYY-MM-DD')" +
			 		    " and  class_check_oper   is   not null";
			// ouditSql = " select  *  from  pc_rpd_gas_daily_v t  where 1=1 and  t.REPORT_DATE = to_date('"+nowdata+"','YYYY-MM-DD') and team ='"+bm+"'"+
			 //				" and  class_check_oper   is   not null";

		}else{
			 ouditSql = " select * from pc_rpd_gas_dailyb_t t where 1=1 and t.REPORT_DATE = to_date('"+nowdata+"','YYYY-MM-DD')" +
			 		" and geology_check_oper   IS  not  null ";
			// ouditSql = " select  *  from  pc_rpd_gas_dailyb_v t  where 1=1 and  t.REPORT_DATE = to_date('"+nowdata+"','YYYY-MM-DD') and team ='"+bm+"'"
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
			String sql = " select * from pc_rpd_gas_daily_t t where 1=1 and t.REPORT_DATE = to_date('"+nowdata+"','YYYY-MM-DD')";
			// sql = " select  *  from  pc_rpd_gas_daily_v t  where 1=1 and  t.REPORT_DATE = to_date('"+nowdata+"','YYYY-MM-DD') and team ='"+bm+"'";

			//获取当天数据
			List<Object[]> todayDatas = AuthorityUtil.commonssql(sql);
			if(todayDatas == null || todayDatas.size() <= 0){
				obj = CommonsUtil.getRrturnJson("","请先进行数据准备" ,"", null, null);
				out.print(obj);
				return null;
			}
		}else{
			String sql = " select * from pc_rpd_gas_dailyb_t t where 1=1 and t.REPORT_DATE = to_date('"+nowdata+"','YYYY-MM-DD')";
			//String sql = " select  *  from  pc_rpd_gas_dailyb_v t  where 1=1 and  t.REPORT_DATE = to_date('"+nowdata+"','YYYY-MM-DD') and team ='"+bm+"'"

			//获取当天数据
			List<Object[]> todayDatas = AuthorityUtil.commonssql(sql);
			if(todayDatas == null || todayDatas.size() <= 0){
				obj = CommonsUtil.getRrturnJson("","请联系班组人员确定已经审核过 ？" ,"", null, null);
				out.print(obj);
				return null;
			}
		}
		//用户添加修改生产单位权限范围（组织结构ID）
		List<Object[]> authorityDatas = null;
			authorityDatas = AuthorityUtil.updateDatasAuthority(user,session);
		//authorityDatas = AuthorityUtil.updateDatasAuthorityCheck(user,session);
		
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
		String cjsj = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("cjsj"))); 
  	   	//获取当前系统时间，判断 日期不为当天 不能操作
	   		Date today = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	   		String td = sdf.format(today);
	   		if(!cjsj.equals(td)){
	   		 obj = CommonsUtil.getRrturnJson("","日期："+cjsj+" 不为当前日期-只能操作当前日期的数据" , "",null, null);
			 out.print(obj);
			return null;
	   		}
		String zqzwater = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("zqzwater"))); 
		String pqjby = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("pqjby"))); 
		String jkyy = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("jkyy"))); 
		String ty = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("ty"))); 
		String pqj = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("pqj"))); 
		String jk = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("jk"))); 
		String pzl = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("pzl"))); 
		String rzl = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("rzl"))); 
		String remark = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("remark"))); 
		

		
		String orgId ="";
		if("15".equals(deptype)){
			//班组
			List<PcRpdGasDailyT> RPDList = null;
			PcRpdGasDailyT rpd = null;
			
			if(wellrpdid !=null && !"".equals(wellrpdid)){
				 orgId = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("ORG_ID")));
				//修改
				try {
					RPDList  = gasDailyWHService.searchCheckOnly("",cjsj,"",wellname);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(RPDList !=null && RPDList.size()>0){
					if(RPDList.size()==1){
						if(wellrpdid.equals(RPDList.get(0).getGaswellrpdid())){
						rpd = RPDList.get(0);
						if(rpd.getClassCheckOper() !=null && !"".equals(rpd.getClassCheckOper())){
							obj = CommonsUtil.getRrturnJson("","数据已经审核过,不能添加和修改操作" ,"", null, null);
								out.print(obj);
								return null;
						}
						}else{
							obj = CommonsUtil.getRrturnJson("","井名称："+wellname+" -当天已存在请使用其他井名称" , "",null, null);
							out.print(obj);
							return null;
						}
					}else{
						obj = CommonsUtil.getRrturnJson("","井名称："+wellname+" -当天已存在请使用其他井名称" , "",null, null);
						out.print(obj);
						return null;
					}
					
				}else{
					RPDList  = gasDailyWHService.searchCheckOnly("","",wellrpdid,"");
					rpd = RPDList.get(0);
				}
				
			}else{
				//添加
				try {
					RPDList  = gasDailyWHService.searchCheckOnly("",cjsj,"","");
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(RPDList !=null && RPDList.size()>0){
					try {
						RPDList  = gasDailyWHService.searchCheckOnly("",cjsj,"",wellname);
					} catch (Exception e) {
						e.printStackTrace();
					}
					if(RPDList !=null && RPDList.size()>0){
						//obj = CommonsUtil.getRrturnJson("", "当天此井已存在","", null, null);
						obj = CommonsUtil.getRrturnJson("","井名称："+wellname+" -当天已存在请使用其他井名称" , "",null, null);
						out.print(obj);
						return null;
					}else{
						rpd = new PcRpdGasDailyT();
						 orgId =gasDailyWHService.searchOrgId(wellname);
						 rpd.setLiquidFlag((byte)0);
//						 if(orgId == null && "".equals(orgId)){
//							 obj = CommonsUtil.getRrturnJson("","井名称："+wellname+" -在稠油井基础信息中无对应组织结构ID,请在基础信息中添加" , "",null, null);
//								out.print(obj);
//								return null;
//						 }
					}
						
				}else{
					obj = CommonsUtil.getRrturnJson("", "请数据准备","", null, null);
					out.print(obj);
					return null;
				}
			}
			
			rpd.setOrgId(orgId);
			rpd.setWellName(wellname);
			rpd.setReportDate(DateBean.getStrDate(cjsj));
			rpd.setZqzwater(CommonsUtil.getBigDecimalData(zqzwater));
			rpd.setPqjby(CommonsUtil.getBigDecimalData(pqjby));
			rpd.setJkyy(CommonsUtil.getBigDecimalData(jkyy));
			rpd.setTy(CommonsUtil.getBigDecimalData(ty));
			rpd.setPqj(CommonsUtil.getBigDecimalData(pqj));
			rpd.setJk(CommonsUtil.getBigDecimalData(jk));
			rpd.setPzl(CommonsUtil.getBigDecimalData(pzl));
			rpd.setRzl(CommonsUtil.getBigDecimalData(rzl));
			rpd.setRemark(remark);
			//rpd.setLiquidFlag((byte)0);
			rpd.setRlastOper(user.getOperName());
			rpd.setRlastOdate(new Date());
			boolean flag = true;
			try {
				flag = gasDailyWHService.saveOrUpdate(rpd);
			} catch (Exception e) {
				obj = CommonsUtil.getRrturnJson("", "ORA-01438: 值大于为此列指定的允许精度","", null, null);
				out.print(obj);
				e.printStackTrace();
			}
			
			//添加日志
			if(wellrpdid !=null && !"".equals(wellrpdid)){
	  			try {
					User user1 = (User) session.getAttribute("userInfo");
					PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "3", "班组日报修改日志成功", rpd.getGaswellrpdid());
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
					PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "1", "班组日报添加日志成功", rpd.getGaswellrpdid());
					logService.addLog(log);
				} catch (Exception e) {
					e.printStackTrace();
					obj = CommonsUtil.getRrturnJson("", "添加日志失败","", null, null);
	  				out.print(obj);
	  				return null;
				}
  			}
			
		}else{
			List<PcRpdGasDailybT> RPDbList = null;
			PcRpdGasDailybT RPDb = null;
			if(wellrpdid !=null && !"".equals(wellrpdid)){
				//修改
				 orgId = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("ORG_ID")));
				try {
					RPDbList  = gasDailyWHService.searchCheckbOnly("",cjsj,"",wellname);
				} catch (Exception e) {
					e.printStackTrace();
					obj = CommonsUtil.getRrturnJson("","查询出现错误" , "",null, null);
					out.print(obj);
					return null;
				}
				if(RPDbList !=null && RPDbList.size()>0){
					if(RPDbList.size()==1){
						if(wellrpdid.equals(RPDbList.get(0).getGaswellrpdid())){
							RPDb = RPDbList.get(0);
							if(RPDb.getGeologyCheckOper() !=null && !"".equals(RPDb.getGeologyCheckOper())){
								obj = CommonsUtil.getRrturnJson("","数据已经审核过,不能添加和修改操作" ,"", null, null);
  								out.print(obj);
  								return null;
							}
						}else{
							obj = CommonsUtil.getRrturnJson("","井名称："+wellname+" -当天已存在请使用其他井名称" , "",null, null);
							out.print(obj);
							return null;
						}
					}else{
						//obj = CommonsUtil.getRrturnJson("","井名称："+wellname+" -已存在请使用其他名称" , "",null, null);
						obj = CommonsUtil.getRrturnJson("","井名称："+wellname+" -当天已存在多条请使用其他井名称" , "",null, null);
						out.print(obj);
						return null;
					}
					
				}else{
					RPDbList  = gasDailyWHService.searchCheckbOnly("","",wellrpdid,"");
					RPDb = RPDbList.get(0);
				}
				
			}else{
				//添加
				try {
					RPDbList  = gasDailyWHService.searchCheckbOnly("",cjsj,"","");
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(RPDbList !=null && RPDbList.size()>0){
					try {
						RPDbList  = gasDailyWHService.searchCheckbOnly("",cjsj,"",wellname);
					} catch (Exception e) {
						e.printStackTrace();
					}
					if(RPDbList !=null && RPDbList.size()>0){
						obj = CommonsUtil.getRrturnJson("","井名称："+wellname+" -当天已存在请使用其他井名称" , "",null, null);
						out.print(obj);
						return null;
					}else{
						RPDb = new PcRpdGasDailybT();
						 orgId =gasDailyWHService.searchOrgId(wellname);
						 RPDb.setLiquidFlag((byte)0);
					}
				}else{
					obj = CommonsUtil.getRrturnJson("", "请数据准备","", null, null);
					out.print(obj);
					return null;
				}
			}
			RPDb.setOrgId(orgId);
			RPDb.setWellName(wellname);
			RPDb.setReportDate(DateBean.getStrDate(cjsj));
			RPDb.setZqzwater(CommonsUtil.getBigDecimalData(zqzwater));
			RPDb.setPqjby(CommonsUtil.getBigDecimalData(pqjby));
			RPDb.setJkyy(CommonsUtil.getBigDecimalData(jkyy));
			RPDb.setTy(CommonsUtil.getBigDecimalData(ty));
			RPDb.setPqj(CommonsUtil.getBigDecimalData(pqj));
			RPDb.setJk(CommonsUtil.getBigDecimalData(jk));
			RPDb.setPzl(CommonsUtil.getBigDecimalData(pzl));
			RPDb.setRzl(CommonsUtil.getBigDecimalData(rzl));
			RPDb.setRemark(remark);
			//RPDb.setLiquidFlag((byte)0);
			RPDb.setRlastOper(user.getOperName());
			RPDb.setRlastOdate(new Date());
			boolean flag = true;
			try {
				flag = gasDailyWHService.saveOrUpdateB(RPDb);
			} catch (Exception e) {
				obj = CommonsUtil.getRrturnJson("", "ORA-01438: 值大于为此列指定的允许精度","", null, null);
				out.print(obj);
				e.printStackTrace();
			}
			
			//添加日志
			if(wellrpdid !=null && !"".equals(wellrpdid)){
	  			try {
					User user1 = (User) session.getAttribute("userInfo");
					PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "3", "班组日报修改日志成功", RPDb.getGaswellrpdid());
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
					PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "1", "班组日报添加日志成功", RPDb.getGaswellrpdid());
					logService.addLog(log);
				} catch (Exception e) {
					e.printStackTrace();
					obj = CommonsUtil.getRrturnJson("", "添加日志失败","", null, null);
	  				out.print(obj);
	  				return null;
				}
  			}
		}
		
		out.print(outCode);
		return null;
	
	}
	
	public  String removeDataRPD()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode ="1";
		JSONObject  obj = new JSONObject();
		String rpdId = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("GASWELLRPDID")));
		String wellname = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("wellname")));
		HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
		User user = (User) session.getAttribute("userInfo");
		
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
		
		String deptype = user.getDepType();   //用户所属部门类型   15：班组  4采油站
		
		//JSONObject obj = new JSONObject();
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
		if("15".equals(deptype)){
			//班组
			List<PcRpdGasDailyT> RPDList = null;
			PcRpdGasDailyT rpd = null;
			
				try {
					RPDList  = gasDailyWHService.searchCheckOnly("","",rpdId,"");
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(RPDList !=null && RPDList.size()>0){
						rpd = RPDList.get(0);
						if(rpd.getClassCheckOper() !=null && !"".equals(rpd.getClassCheckOper())){
							obj = CommonsUtil.getRrturnJson("","数据已经审核过,不能删除操作" ,"", null, null);
							out.print(obj);
							return null;
						}
				}
		}else{
			List<PcRpdGasDailybT> RPDbList = null;
			PcRpdGasDailybT RPDb = null;
			
				try {
					RPDbList  = gasDailyWHService.searchCheckbOnly("","",rpdId,"");
				} catch (Exception e) {
					e.printStackTrace();
					obj = CommonsUtil.getRrturnJson("","查询出现错误" , "",null, null);
					out.print(obj);
					return null;
				}
				if(RPDbList !=null && RPDbList.size()>0){
							RPDb = RPDbList.get(0);
							if(RPDb.getGeologyCheckOper() !=null && !"".equals(RPDb.getGeologyCheckOper())){
								obj = CommonsUtil.getRrturnJson("","数据已经审核过,不能删除操作" ,"", null, null);
  								out.print(obj);
  								return null;
							}
						}
		}
		boolean deleteFlag = true;
		try {
			deleteFlag = gasDailyWHService.removeDataRPD(rpdId,deptype);
		} catch (Exception e) {
			e.printStackTrace();
			obj = CommonsUtil.getRrturnJson("", "删除失败","", null, null);
			out.print(obj);
			return null;
		}
		try {
			User user1 = (User) session.getAttribute("userInfo");
			PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "2", "删除添加日志成功", rpdId);
			logService.addLog(log);
		} catch (Exception e) {
			e.printStackTrace();
			obj = CommonsUtil.getRrturnJson("", "添加日志失败","", null, null);
				out.print(obj);
				return null;
		}
		out.print(outCode);
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
		//一天只能进行一次数据准备
		String nowdata = DateBean.getSystemTime1();
		String sql = " select * from pc_rpd_gas_daily_t t where 1=1 and t.REPORT_DATE = to_date('"+nowdata+"','YYYY-MM-DD')";
		//String  sql = " select * from pc_rpd_gas_daily_v t where 1=1 and t.REPORT_DATE = to_date('"+nowdata+"','YYYY-MM-DD') and team ='"+bm+"'";	

		//获取当天数据
		List<Object[]> todayDatas = AuthorityUtil.commonssql(sql);
		if(todayDatas != null && todayDatas.size() > 0){
			obj = CommonsUtil.getRrturnJson("","一天只能进行一次数据准备" ,"", null, null);
			out.print(obj);
			return null;
		}
		
		try {
			rusl  = gasDailyWHService.Dataready("p_prepare_gas_daily_prod", DateBean.getSystemTime1(), user.getUserid());
		} catch (Exception e) {
			e.printStackTrace();
			//当前日期的数据准备
			obj = CommonsUtil.getRrturnJson("","当前日期的数据准备失败" ,"", null, null);
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
		
		try {
			User user1 = (User) session.getAttribute("userInfo");
			PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "1", "数据准备日志添加成功", "");
			logService.addLog(log);
		} catch (Exception e) {
			e.printStackTrace();
			obj = CommonsUtil.getRrturnJson("", "添加日志失败","", null, null);
				out.print(obj);
				return null;
		}
			out.println(obj);
			return null;
		
	}
	
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
		String sql = " select * from pc_rpd_gas_dailyb_t t where 1=1 and t.REPORT_DATE = to_date('"+nowdata+"','YYYY-MM-DD')";
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
			rusl  = gasDailyWHService.Automate("p_prepare_thin_prod_auto", nowdata, user.getUserid(),"THIN_AUTO_SELETE_TIME");
			
		} catch (Exception e) {
			e.printStackTrace();
			//当前日期的数据准备
			obj = CommonsUtil.getRrturnJson("","当前日期的自动化提取失败" ,"", null, null);
			out.println(obj);
			return null;
		}
		
		if(rusl != null && rusl.size()>0 ){
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
				PcRdLoginfoT log = CommonsUtil.getLogInfo(user, "12", "稀油日报", "");
				logService.addLog(log);
			} catch (Exception e) {
				e.printStackTrace();
				obj = CommonsUtil.getRrturnJson("","稀油日报自动化提取LOG添加失败" ,"", null, null);
				out.println(obj);
				return null;
			}
			
			out.println(obj);
			return null;	
	}
}
