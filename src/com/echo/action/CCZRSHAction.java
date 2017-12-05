package com.echo.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.echo.dto.PcRdLoginfoT;
import com.echo.dto.User;
import com.echo.service.LogService;
import com.echo.service.impl.CCZRSHServiceImpl;
import com.echo.util.AuthorityUtil;
import com.echo.util.CommonsUtil;
import com.echo.util.DateBean;
import com.echo.util.PageVariableUtil;
import com.echo.util.StringUtil;

public class CCZRSHAction{
	private CCZRSHServiceImpl cCZRSHService;
	private LogService logService;


	public void setLogService(LogService logService) {
		this.logService = logService;
	}



	public void setcCZRSHService(CCZRSHServiceImpl cCZRSHService) {
		this.cCZRSHService = cCZRSHService;
	}

	
	
	//根据权限进行页面初始化
	public String pageCCInit()throws Exception{
		HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		//根据用户权限生成用户权限生成页面布局格式
		User user = (User) session.getAttribute("userInfo");
		String gridJson = null;
		try {
			gridJson = AuthorityUtil.getGridJson1("MENU041", user, PageVariableUtil.CCRBSHTJ_PAGE_GRID);
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

	//根据权限进行页面初始化
	public String pageZRInit()throws Exception{
		HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		//根据用户权限生成用户权限生成页面布局格式
		User user = (User) session.getAttribute("userInfo");
		String gridJson = null;
		try {
			gridJson = AuthorityUtil.getGridJson1("MENU036", user, PageVariableUtil.ZRRBSHTJ_PAGE_GRID);
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
	public String searchCCDatas() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
		User user = (User) session.getAttribute("userInfo");
		String deptype = user.getDepType();   //用户所属部门类型   15：班组  4采油站
		String stautsid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("stautsid")));
		String txtDate = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("txtDate")));
		JSONObject obj = new JSONObject();
 		if(txtDate == null || "".equals(txtDate)){
 			txtDate = DateBean.getSystemTime1();
 		}
		List<Object[]> todayList = null;
		List<Object[]> yestodayList = null;
		JSONArray jsonArr = new JSONArray();
		JSONObject tjo = new JSONObject();
		String zjs = "0";
		String kjs = "0";
		String cyl = "0.00";
		try {
			
			//当日 数据
			todayList = cCZRSHService.searchCCDatas(stautsid,txtDate,deptype,user.getDepname());
			// 前一天数据
			String yestoday = DateBean.getBeforeDAYTime(txtDate);
			yestodayList = cCZRSHService.searchCCDatas(stautsid,yestoday,deptype,user.getDepname());
			
			
		} catch (Exception e) {
			e.printStackTrace();
			obj = CommonsUtil.getRrturnJson("","采出日报审核统计信息获取失败" ,"", null, null);
			out.println(obj);
			return null;
		}
		
		if(todayList != null && todayList.size()>0){
			
			for(int i = 0; i< todayList.size() ; i++){
				if(todayList.get(i)[0] != null && !"".equals(todayList.get(i)[0])
						&& todayList.get(i)[1] != null && !"".equals(todayList.get(i)[1])){
					tjo = new JSONObject();
					zjs = "0";
					kjs = "0";
					cyl = "0.00";
					tjo.put("OILNAME",todayList.get(i)[0].toString());
					tjo.put("TEAM",todayList.get(i)[1].toString());
					tjo.put("REPORT_DATE",txtDate);
					
					for(int j = 0; j < yestodayList.size();j ++){
						
						if(todayList.get(i)[1].toString().equals(yestodayList.get(j)[1].toString())){
							zjs = CommonsUtil.getIntData(yestodayList.get(j)[2]);
							kjs = CommonsUtil.getIntData(yestodayList.get(j)[3]);
							cyl = CommonsUtil.getNotTwoData(yestodayList.get(j)[4]);
						}
					}
					
					tjo.put("WELLSUM",CommonsUtil.getClearNullData(todayList.get(i)[2]));
					tjo.put("WELLSUM_COMPARE",Integer.parseInt(CommonsUtil.getIntData(todayList.get(i)[2]))-Integer.parseInt(zjs));
					
					tjo.put("OPENWELL",CommonsUtil.getClearNullData(todayList.get(i)[3]));
					tjo.put("OPENWELL_COMPARE",Integer.parseInt(CommonsUtil.getIntData(todayList.get(i)[3]))-Integer.parseInt(kjs));
					if(todayList.get(i)[4] !=null  && !"".equals(todayList.get(i)[4].toString())){
						tjo.put("LIQUID",String.valueOf(CommonsUtil.getNotTwoData(todayList.get(i)[4])));
						tjo.put("LIQUID_COMPARE",String.valueOf(Double.parseDouble(CommonsUtil.getNotTwoData(todayList.get(i)[4]))-Double.parseDouble(cyl)));
						
					}else{
						tjo.put("LIQUID","");
						tjo.put("LIQUID_COMPARE","");
						
					}
					//tjo.put("LIQUID",String.valueOf(CommonsUtil.getNotTwoData(todayList.get(i)[4])));
					//tjo.put("LIQUID_COMPARE",String.valueOf(Double.parseDouble(CommonsUtil.getNotTwoData(todayList.get(i)[4]))-Double.parseDouble(cyl)));
					
					if(todayList.get(i)[5] != null && !"".equals(todayList.get(i)[5].toString()) 
							&& !"null".equals(todayList.get(i)[5].toString()) && !"0".equals(todayList.get(i)[5].toString())){
						tjo.put("SH_STAUTS","已审核");
					}else{
						tjo.put("SH_STAUTS","未审核");
					}
					
					if(todayList.get(i)[6] != null && !"".equals(todayList.get(i)[6].toString()) 
							&& !"null".equals(todayList.get(i)[6].toString()) && !"0".equals(todayList.get(i)[6].toString())){
						tjo.put("DZ_STAUTS","已审核");
					}else{
						tjo.put("DZ_STAUTS","未审核");
					}
					jsonArr.add(tjo);
				}else{
					tjo.put("OILNAME",CommonsUtil.getClearNullData(todayList.get(i)[0]));
					tjo.put("TEAM",CommonsUtil.getClearNullData(todayList.get(i)[1]));
					tjo.put("REPORT_DATE",txtDate);
					tjo.put("WELLSUM",CommonsUtil.getClearNullData(todayList.get(i)[2]));
					tjo.put("WELLSUM_COMPARE","无所属生成单位无对比");
					tjo.put("OPENWELL",CommonsUtil.getClearNullData(todayList.get(i)[3]));
					tjo.put("OPENWELL_COMPARE","无所属生成单位无对比");
					tjo.put("LIQUID",String.valueOf(CommonsUtil.getNotTwoData(todayList.get(i)[4])));
					tjo.put("LIQUID_COMPARE","无所属生成单位无对比");
					if(todayList.get(i)[5] != null && !"".equals(todayList.get(i)[5].toString()) 
							&& !"null".equals(todayList.get(i)[5].toString()) && !"0".equals(todayList.get(i)[5].toString())){
						tjo.put("SH_STAUTS","已审核");
					}else{
						tjo.put("SH_STAUTS","未审核");
					}
					
					if(todayList.get(i)[6] != null && !"".equals(todayList.get(i)[6].toString()) 
							&& !"null".equals(todayList.get(i)[6].toString()) && !"0".equals(todayList.get(i)[6].toString())){
						tjo.put("DZ_STAUTS","已审核");
					}else{
						tjo.put("DZ_STAUTS","未审核");
					}
					jsonArr.add(tjo);
				}
				
			}
			tjo.put("Rows",jsonArr);
			tjo.put("Total",todayList.size());
		}else{
			tjo.put("Rows",jsonArr);
			tjo.put("Total",0);
		}
		out.println(tjo);
		return null;
	}
	
	public String searchZRDatas() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
		User user = (User) session.getAttribute("userInfo");
		String deptype = user.getDepType();   //用户所属部门类型   15：班组  4采油站
		String stautsid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("stautsid")));
		String txtDate = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("txtDate")));
		JSONObject obj = new JSONObject();
 		if(txtDate == null || "".equals(txtDate)){
 			txtDate = DateBean.getSystemTime1();
 		}
		List<Object[]> todayList = null;
		List<Object[]> yestodayList = null;
		JSONArray jsonArr = new JSONArray();
		JSONObject tjo = new JSONObject();
		String zjs = "0";
		String kjs = "0";
		String cyl = "0.00";
		try {
			
			//当日 数据
			todayList = cCZRSHService.searchZRDatas(stautsid,txtDate,deptype,user.getDepname());
			// 前一天数据
			String yestoday = DateBean.getBeforeDAYTime(txtDate);
			yestodayList = cCZRSHService.searchZRDatas(stautsid,yestoday,deptype,user.getDepname());
			
			
		} catch (Exception e) {
			e.printStackTrace();
			obj = CommonsUtil.getRrturnJson("","采出日报审核统计信息获取失败" ,"", null, null);
			out.println(obj);
			return null;
		}
		
		if(todayList != null && todayList.size()>0){
			
			for(int i = 0; i< todayList.size() ; i++){
				if(todayList.get(i)[0] != null && !"".equals(todayList.get(i)[0])
						&& todayList.get(i)[1] != null && !"".equals(todayList.get(i)[1])){
					tjo = new JSONObject();
					zjs = "0";
					kjs = "0";
					cyl = "0.00";
					tjo.put("OILNAME",todayList.get(i)[0].toString());
					tjo.put("TEAM",todayList.get(i)[1].toString());
					tjo.put("REPORT_DATE",txtDate);
					
					for(int j = 0; j < yestodayList.size();j ++){
						
						if(todayList.get(i)[1].toString().equals(yestodayList.get(j)[1].toString())){
							zjs = CommonsUtil.getIntData(yestodayList.get(j)[2]);
							kjs = CommonsUtil.getIntData(yestodayList.get(j)[3]);
							cyl = CommonsUtil.getNotTwoData(yestodayList.get(j)[4]);
						}
					}
					
					tjo.put("WELLSUM",CommonsUtil.getClearNullData(todayList.get(i)[2]));
					tjo.put("WELLSUM_COMPARE",Integer.parseInt(CommonsUtil.getIntData(todayList.get(i)[2]))-Integer.parseInt(zjs));
					
					tjo.put("OPENWELL",CommonsUtil.getClearNullData(todayList.get(i)[3]));
					tjo.put("OPENWELL_COMPARE",Integer.parseInt(CommonsUtil.getIntData(todayList.get(i)[3]))-Integer.parseInt(kjs));
					
//					tjo.put("LIQUID",String.valueOf(CommonsUtil.getNotTwoData(todayList.get(i)[4])));
//					tjo.put("LIQUID_COMPARE",String.valueOf(Double.parseDouble(CommonsUtil.getNotTwoData(todayList.get(i)[4]))-Double.parseDouble(cyl)));
					//判断今天为空
					if(todayList.get(i)[4] !=null  && !"".equals(todayList.get(i)[4].toString())){
						tjo.put("LIQUID",String.valueOf(CommonsUtil.getNotTwoData(todayList.get(i)[4])));
						tjo.put("LIQUID_COMPARE",String.valueOf(Double.parseDouble(CommonsUtil.getNotTwoData(todayList.get(i)[4]))-Double.parseDouble(cyl)));
						
					}else{
						tjo.put("LIQUID","");
						tjo.put("LIQUID_COMPARE","");
						
					}
					if(todayList.get(i)[5] != null && !"".equals(todayList.get(i)[5].toString()) 
							&& !"null".equals(todayList.get(i)[5].toString()) && !"0".equals(todayList.get(i)[5].toString())){
						tjo.put("SH_STAUTS","已审核");
					}else{
						tjo.put("SH_STAUTS","未审核");
					}
					
					if(todayList.get(i)[6] != null && !"".equals(todayList.get(i)[6].toString()) 
							&& !"null".equals(todayList.get(i)[6].toString()) && !"0".equals(todayList.get(i)[6].toString())){
						tjo.put("DZ_STAUTS","已审核");
					}else{
						tjo.put("DZ_STAUTS","未审核");
					}
					jsonArr.add(tjo);
				}else{
					tjo.put("OILNAME",CommonsUtil.getClearNullData(todayList.get(i)[0]));
					tjo.put("TEAM",CommonsUtil.getClearNullData(todayList.get(i)[1]));
					tjo.put("REPORT_DATE",txtDate);
					tjo.put("WELLSUM",CommonsUtil.getClearNullData(todayList.get(i)[2]));
					tjo.put("WELLSUM_COMPARE","无所属生成单位无对比");
					tjo.put("OPENWELL",CommonsUtil.getClearNullData(todayList.get(i)[3]));
					tjo.put("OPENWELL_COMPARE","无所属生成单位无对比");
					tjo.put("LIQUID",String.valueOf(CommonsUtil.getNotTwoData(todayList.get(i)[4])));
					tjo.put("LIQUID_COMPARE","无所属生成单位无对比");
					if(todayList.get(i)[5] != null && !"".equals(todayList.get(i)[5].toString()) 
							&& !"null".equals(todayList.get(i)[5].toString()) && !"0".equals(todayList.get(i)[5].toString())){
						tjo.put("SH_STAUTS","已审核");
					}else{
						tjo.put("SH_STAUTS","未审核");
					}
					
					if(todayList.get(i)[6] != null && !"".equals(todayList.get(i)[6].toString()) 
							&& !"null".equals(todayList.get(i)[6].toString()) && !"0".equals(todayList.get(i)[6].toString())){
						tjo.put("DZ_STAUTS","已审核");
					}else{
						tjo.put("DZ_STAUTS","未审核");
					}
					jsonArr.add(tjo);
				}
				
			}
			tjo.put("Rows",jsonArr);
			tjo.put("Total",todayList.size());
		}else{
			tjo.put("Rows",jsonArr);
			tjo.put("Total",0);
		}
		out.println(tjo);
		return null;
	}
	
	
	@SuppressWarnings("unused")
	public String onAudit() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONObject obj = new JSONObject();
		HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
		User user = (User) session.getAttribute("userInfo");
		String arg = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("arg")));
		String txtDate = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("txtDate")));
		String nowdate = DateBean.getSystemTime1();
		String deptype = user.getDepType();   //用户所属部门类型   15：班组  4采油站
		String depname = user.getDepname(); 
		String tablename ="";
		String sql = "";
		int sum = 0;
		String rbid ="";
		String team = "team";
		String cyz = "OILSTATIONNAME";
		if("4".equals(deptype) || "15".equals(deptype)){
			if(!nowdate.equals(txtDate)){
				obj = CommonsUtil.getRrturnJson("","只能对当天数据日报数据进行审核" ,"", null, null);
				out.print(obj);
				return null;
			}
			if("4".equals(deptype) ){
				if("dzzaudit".equals(arg)){
					if(depname.indexOf("乌尔禾") != -1 || depname.indexOf("夏子街") != -1){
						tablename = " pc_rpd_thin_wellwb_t " ;
						rbid = "THINWELLRPD";
						cyz = "oilname";
						
					}else{
						tablename = " pc_rpd_rule_well_prodb_t " ;
						rbid = "RPD_RULE_WELL_PROD_ID";
					}
					
					boolean stautsflg = AuthorityUtil.checkeUserStauts(nowdate, "采出_地质组审核", user);
					
					if(stautsflg == false){
						obj = CommonsUtil.getRrturnJson("","每天只能进行一次地质组审核，今天已执行过该操作" ,"", null, null);
						out.println(obj);
						return null;
						
					}else{
						
						sql ="update "+tablename+" set(GEOLOGY_CHECK_OPER,GEOLOGY_CHECK_DATE,RLAST_OPER,RLAST_ODATE)=(SELECT '"+user.getOperName()+"',sysdate,'"+user.getOperName()+"',sysdate FROM DUAL)  where ";
						sql += rbid +" in ( "+
						 " select "+rbid+" from "+tablename.substring(0, tablename.length()-2)+"v "+
						 " where  report_date = to_date('"+nowdate+"','YYYY-MM-DD')" +
						//and  TEAMNAME = '采油三站一班'
						" and "+cyz+" = '"+depname+"' )";
						
							
					}
					
					try {
						sum = cCZRSHService.onAudit(sql);
					} catch (Exception e) {
						e.printStackTrace();
						obj = CommonsUtil.getRrturnJson("","审核失败，请联系管理员" ,"", null, null);
						out.print(obj);
						return null;
					}
					
					if(sum >= 0){
						if(sum == 0){
							obj = CommonsUtil.getRrturnJson("","无数据进行审核" ,"", null, null);
							out.print(obj);
							return null;	
						}else{
							obj = CommonsUtil.getRrturnJson("","" ,String.valueOf(sum), null, null);
							try {
								int sign = AuthorityUtil.updateUserStauts(nowdate, "采出_地质组审核", session);
								
								try {
									PcRdLoginfoT log = CommonsUtil.getLogInfo(user, "9", "采出日报审核统计", "");
									logService.addLog(log);
								} catch (Exception e) {
									e.printStackTrace();
									obj = CommonsUtil.getRrturnJson("","采出日报审核统计地质组审核日志添加失败" ,"", null, null);
								}
								
							} catch (Exception e) {
								obj = CommonsUtil.getRrturnJson("","地质组审核后部门功能锁定失败" ,"", null, null);
								out.println(obj);
								return null;
							}
							
						}
						
					}else{
						obj = CommonsUtil.getRrturnJson("","审核失败，请联系管理员" ,"", null, null);
						out.print(obj);
						return null;	
					}
					
				}else{
					obj = CommonsUtil.getRrturnJson("","您无审核权限" ,"", null, null);
					out.print(obj);
					return null;
				}
				
			}else{
				if("bzaudit".equals(arg)){
					if(depname.indexOf("乌尔禾") != -1 || depname.indexOf("夏子街") != -1){
						tablename = " pc_rpd_thin_wellw_t " ;
						rbid = "THINWELLRPD";
						
					}else{
						tablename = " pc_rpd_rule_well_prod_t " ;
						rbid = "RPD_RULE_WELL_PROD_ID";
						team = "TEAMNAME";
					}
					
					boolean stautsflg = AuthorityUtil.checkeUserStauts(nowdate, "采出_班组审核", user);
					
					if(stautsflg == false){
						obj = CommonsUtil.getRrturnJson("","每天只能进行一次班组审核，今天已执行过该操作" ,"", null, null);
						out.println(obj);
						return null;
						
					}else{
						
						sql ="update "+tablename+" set(CLASS_CHECK_OPER,CLASS_CHECK_DATE,RLAST_OPER,RLAST_ODATE)=(SELECT '"+user.getOperName()+"',sysdate,'"+user.getOperName()+"',sysdate FROM DUAL)  where " ;
						sql += rbid +" in ( "+
						 " select "+rbid+" from "+tablename.substring(0, tablename.length()-2)+"v "+
						 " where  report_date = to_date('"+nowdate+"','YYYY-MM-DD')" +
						" and "+team+" = '"+depname+"' )";
					}
					
					try {
						sum = cCZRSHService.onAudit(sql);
					} catch (Exception e) {
						e.printStackTrace();
						obj = CommonsUtil.getRrturnJson("","审核失败，请联系管理员" ,"", null, null);
						out.print(obj);
						return null;
					}
					
					if(sum >= 0){
						if(sum == 0){
							obj = CommonsUtil.getRrturnJson("","无数据进行审核" ,"", null, null);
							out.print(obj);
							return null;	
						}else{
							
							List<String> rusl = new ArrayList<String>();
							try {
								if(depname.indexOf("乌尔禾") != -1 || depname.indexOf("夏子街") != -1){
									rusl  = cCZRSHService.onAuditAfter("p_prepare_thin_well_audit", DateBean.getSystemTime1(), user.getUserid());
									
								}else{
									rusl  = cCZRSHService.onAuditAfter("p_prepare_rule_well_audit", DateBean.getSystemTime1(), user.getUserid());
								}
								
								
							} catch (Exception e) {
								e.printStackTrace();
								obj = CommonsUtil.getRrturnJson("","当前日期的审核后数据复制失败，请联系管理员" ,"", null, null);
								out.print(obj);
								return null;
							}
							
							if(rusl != null && rusl.size()>0 ){
								if(Integer.parseInt(rusl.get(0).toString()) == 0){
									obj = CommonsUtil.getRrturnJson("",rusl.get(1).toString() ,"", null, null);
									out.print(obj);
									return null;
								}
								
							}else{
								obj = CommonsUtil.getRrturnJson("","当前日期的审核后数据复制失败，请联系管理员" ,"", null, null);
								out.print(obj);
								return null;
							}
							
							
							obj = CommonsUtil.getRrturnJson("","" ,String.valueOf(sum), null, null);
							try {
								int sign = AuthorityUtil.updateUserStauts(nowdate, "采出_班组审核", session);
								
								try {
									PcRdLoginfoT log = CommonsUtil.getLogInfo(user, "8", "采出日报审核统计", "");
									logService.addLog(log);
								} catch (Exception e) {
									e.printStackTrace();
									obj = CommonsUtil.getRrturnJson("","采出日报审核统计班组组审核日志添加失败" ,"", null, null);
								}
								
							} catch (Exception e) {
								obj = CommonsUtil.getRrturnJson("","班组审核后部门功能锁定失败" ,"", null, null);
								out.println(obj);
								return null;
							}
							
						}
						
					}else{
						obj = CommonsUtil.getRrturnJson("","审核失败，请联系管理员" ,"", null, null);
						out.print(obj);
						return null;	
					}
					
				}else{
					obj = CommonsUtil.getRrturnJson("","您无审核权限" ,"", null, null);
					out.print(obj);
					return null;
				}
				
			}
			
			
			
		}else{
			obj = CommonsUtil.getRrturnJson("","您无审核权限" ,"", null, null);
			out.print(obj);
			return null;
		
		}
		
		
		
		out.print(obj);
		return null;
	}
	
	
	@SuppressWarnings("unused")
	public String onZrAudit() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONObject obj = new JSONObject();
		HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
		User user = (User) session.getAttribute("userInfo");
		String arg = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("arg")));
		String txtDate = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("txtDate")));
		String nowdate = DateBean.getSystemTime1();
		String deptype = user.getDepType();   //用户所属部门类型   15：班组  4采油站
		String depname = user.getDepname(); 
		String tablename ="";
		String sql = "";
		int sum = 0;
		String rbid="";
		
		if("4".equals(deptype) || "15".equals(deptype)){
			if(!nowdate.equals(txtDate)){
				obj = CommonsUtil.getRrturnJson("","只能对当天数据日报数据进行审核" ,"", null, null);
				out.print(obj);
				return null;
			}
			if("4".equals(deptype) ){
				if("dzzaudit".equals(arg)){
					if(depname.indexOf("乌尔禾") != -1 || depname.indexOf("夏子街") != -1){
						tablename = " pc_rpd_waterflooding_wellb_t " ;
						rbid ="WELLRPDID";
					}else{
						tablename = " pc_rpd_gas_dailyb_t " ;
						rbid="GASWELLRPDID";
					}
					
					boolean stautsflg = AuthorityUtil.checkeUserStauts(nowdate, "注入_地质组审核", user);
					
					if(stautsflg == false){
						obj = CommonsUtil.getRrturnJson("","每天只能进行一次地质组审核，今天已执行过该操作" ,"", null, null);
						out.println(obj);
						return null;
						
					}else{
						
						sql ="update "+tablename+" set(GEOLOGY_CHECK_OPER,GEOLOGY_CHECK_DATE,RLAST_OPER,RLAST_ODATE)=(SELECT '"+user.getOperName()+"',sysdate,'"+user.getOperName()+"',sysdate FROM DUAL)  where " ;
						sql += rbid +" in ( "+
						 " select "+rbid+" from "+tablename.substring(0, tablename.length()-2)+"v "+
						 " where  report_date = to_date('"+nowdate+"','YYYY-MM-DD')" +
						" and oilname = '"+depname+"' )";
					}
					
					try {
						sum = cCZRSHService.onAudit(sql);
					} catch (Exception e) {
						e.printStackTrace();
						obj = CommonsUtil.getRrturnJson("","审核失败，请联系管理员" ,"", null, null);
						out.print(obj);
						return null;
					}
					
					if(sum >= 0){
						if(sum == 0){
							obj = CommonsUtil.getRrturnJson("","无数据进行审核" ,"", null, null);
							out.print(obj);
							return null;	
						}else{
							obj = CommonsUtil.getRrturnJson("","" ,String.valueOf(sum), null, null);
							try {
								int sign = AuthorityUtil.updateUserStauts(nowdate, "注入_地质组审核", session);
								
								try {
									PcRdLoginfoT log = CommonsUtil.getLogInfo(user, "9", "注入日报审核统计", "");
									logService.addLog(log);
								} catch (Exception e) {
									e.printStackTrace();
									obj = CommonsUtil.getRrturnJson("","注入日报审核统计地质组审核日志添加失败" ,"", null, null);
								}
								
							} catch (Exception e) {
								obj = CommonsUtil.getRrturnJson("","地质组审核后部门功能锁定失败" ,"", null, null);
								out.println(obj);
								return null;
							}
							
						}
						
					}else{
						obj = CommonsUtil.getRrturnJson("","审核失败，请联系管理员" ,"", null, null);
						out.print(obj);
						return null;	
					}
					
				}else{
					obj = CommonsUtil.getRrturnJson("","您无审核权限" ,"", null, null);
					out.print(obj);
					return null;
				}
				
			}else{
				if("bzaudit".equals(arg)){
					if(depname.indexOf("乌尔禾") != -1 || depname.indexOf("夏子街") != -1){
						tablename = " pc_rpd_waterflooding_well_t " ;
						rbid ="WELLRPDID";
						
					}else{
						tablename = " pc_rpd_gas_daily_t " ;
						rbid="GASWELLRPDID";
					}
					
					boolean stautsflg = AuthorityUtil.checkeUserStauts(nowdate, "注入_班组审核", user);
					
					if(stautsflg == false){
						obj = CommonsUtil.getRrturnJson("","每天只能进行一次班组审核，今天已执行过该操作" ,"", null, null);
						out.println(obj);
						return null;
						
					}else{
						
						sql ="update "+tablename+" set(CLASS_CHECK_OPER,CLASS_CHECK_DATE,RLAST_OPER,RLAST_ODATE)=(SELECT '"+user.getOperName()+"',sysdate,'"+user.getOperName()+"',sysdate FROM DUAL)  where ";
						sql += rbid +" in ( "+
						 " select "+rbid+" from "+tablename.substring(0, tablename.length()-2)+"v "+
						 " where  report_date = to_date('"+nowdate+"','YYYY-MM-DD')" +
						" and TEAM = '"+depname+"' )";
					}
					
					try {
						sum = cCZRSHService.onAudit(sql);
					} catch (Exception e) {
						e.printStackTrace();
						obj = CommonsUtil.getRrturnJson("","审核失败，请联系管理员" ,"", null, null);
						out.print(obj);
						return null;
					}
					
					if(sum >= 0){
						if(sum == 0){
							obj = CommonsUtil.getRrturnJson("","无数据进行审核" ,"", null, null);
							out.print(obj);
							return null;	
						}else{
							
							List<String> rusl = new ArrayList<String>();
							try {
								if(depname.indexOf("乌尔禾") != -1 || depname.indexOf("夏子街") != -1){
									rusl  = cCZRSHService.onAuditAfter("p_prepare_water_flooding_audit", DateBean.getSystemTime1(), user.getUserid());
									
								}else{
									rusl  = cCZRSHService.onAuditAfter("p_prepare_gas_daily_audit", DateBean.getSystemTime1(), user.getUserid());
								}
								
								
							} catch (Exception e) {
								e.printStackTrace();
								obj = CommonsUtil.getRrturnJson("","当前日期的审核后数据复制失败，请联系管理员" ,"", null, null);
								out.print(obj);
								return null;
							}
							
							if(rusl != null && rusl.size()>0 ){
								if(Integer.parseInt(rusl.get(0).toString()) == 0){
									obj = CommonsUtil.getRrturnJson("",rusl.get(1).toString() ,"", null, null);
									out.print(obj);
									return null;
								}
								
							}else{
								obj = CommonsUtil.getRrturnJson("","当前日期的审核后数据复制失败，请联系管理员" ,"", null, null);
								out.print(obj);
								return null;
							}
							
							
							obj = CommonsUtil.getRrturnJson("","" ,String.valueOf(sum), null, null);
							try {
								int sign = AuthorityUtil.updateUserStauts(nowdate, "注入_班组审核", session);
								
								try {
									PcRdLoginfoT log = CommonsUtil.getLogInfo(user, "8", "注入日报审核统计", "");
									logService.addLog(log);
								} catch (Exception e) {
									e.printStackTrace();
									obj = CommonsUtil.getRrturnJson("","注入日报审核统计班组组审核日志添加失败" ,"", null, null);
								}
								
							} catch (Exception e) {
								obj = CommonsUtil.getRrturnJson("","班组审核后部门功能锁定失败" ,"", null, null);
								out.println(obj);
								return null;
							}
							
						}
						
					}else{
						obj = CommonsUtil.getRrturnJson("","审核失败，请联系管理员" ,"", null, null);
						out.print(obj);
						return null;	
					}
					
				}else{
					obj = CommonsUtil.getRrturnJson("","您无审核权限" ,"", null, null);
					out.print(obj);
					return null;
				}
				
			}
			
			
			
		}else{
			obj = CommonsUtil.getRrturnJson("","您无审核权限" ,"", null, null);
			out.print(obj);
			return null;
		
		}
		
		
		
		out.print(obj);
		return null;
	}

}