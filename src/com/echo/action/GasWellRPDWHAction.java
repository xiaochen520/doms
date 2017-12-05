package com.echo.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.echo.dto.PcRpdGasWelldT;
import com.echo.dto.User;
import com.echo.service.GasWellRPDService;
import com.echo.util.AuthorityUtil;
import com.echo.util.CommonsUtil;
import com.echo.util.DateBean;
import com.echo.util.PageVariableUtil;
import com.echo.util.StringUtil;

public class GasWellRPDWHAction {
	//气井日报维护
	HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
	private GasWellRPDService  gaswellrpdservice;
	
	public void setGaswellrpdservice(GasWellRPDService gaswellrpdservice) {
		this.gaswellrpdservice = gaswellrpdservice;
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
//		
		String gridJson = null;
		try {
			gridJson = AuthorityUtil.getGridJson1("MENU037", user, PageVariableUtil.GAS_WELL_RPD_T);
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
	public  String saveOrupdateGasRPDWH()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		String outCode = "1";
		PrintWriter out = response.getWriter();
		JSONObject  obj = new JSONObject();
		
		
		String gas_welldid = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("gas_welldid")));
		String welName = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("welName")));
		String reportDate = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("reportDate")));
		
		Date  date = new Date();
		SimpleDateFormat  sf = new SimpleDateFormat("yyyy-MM-dd");
		String noDay = sf.format(date);
		if(!reportDate.equals(noDay)){
			obj = CommonsUtil.getRrturnJson("","时间："+reportDate+" -不是当前时间不让添加或修改操作" , "",null, null);
			out.print(obj);
			return null;
		}
		String tubingPres = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("tubingPres")));
		String  wellTemp= StringUtil.toStr(StringUtil.isNullStr(request.getParameter("wellTemp")));
		String delivery = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("delivery")));
		String  discharge= StringUtil.toStr(StringUtil.isNullStr(request.getParameter("discharge")));
		String daliyFlow = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("daliyFlow")));
		
		List<PcRpdGasWelldT> WHList = null;
		PcRpdGasWelldT wh = null;
		//根据名字 查ID
		String gaswellid = gaswellrpdservice.searchWelID(welName);
	/*	//String rulewellid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("sagddids")));
		//井的ID
		
		User user = (User) session.getAttribute("userInfo");
		//JSONObject obj = new JSONObject();
		//用户添加修改生产单位权限范围（组织结构ID）
		List<Object[]> authorityDatas = null;
		List<Object[]> orgids = null;
		authorityDatas = AuthorityUtil.updateDatasAuthority(user,session);
		//用要修改或添加生成单位ID 获取对应组织结构id
		String sql ="select t.org_id from pc_cd_rule_well_t t where t.rule_wellid ='"+gaswellid+"'";
		
		try {
			orgids = AuthorityUtil.getDatas(sql);
			
		} catch (Exception e) {
			e.printStackTrace();
			obj = CommonsUtil.getRrturnJson("","生产数据组织结构ID 获取失败" ,"", null, null);
			out.print(obj);
			return null;
		}
		
		if(orgids != null && orgids.size() >0){
			String orgid = orgids.get(0)[0].toString();
			if(AuthorityUtil.authorityDos(orgids.get(0)[0].toString(), authorityDatas) == false){
				obj = CommonsUtil.getRrturnJson("","您没有对改生成单位进行添加或修改的权限，请联系管理员添加权限" ,"", null, null);
				out.print(obj);
				return null;
			}
		}*/
		

		if(gas_welldid !=null && !"".equals(gas_welldid)){
			//修改
			try {
				WHList = gaswellrpdservice.searchOnly("",gaswellid , reportDate );
				if(WHList !=null && WHList.size()>0){
					if(WHList.size() ==1){
						if(gas_welldid.equals(WHList.get(0).getGasWelldid())){
							wh = WHList.get(0);
						}else{
							obj = CommonsUtil.getRrturnJson("","井名称："+welName+" -已存在请使用其他名称" , "",null, null);
							out.print(obj);
							return null;	
						}
					}else if(WHList.size() >1){
						obj = CommonsUtil.getRrturnJson("","井名称："+welName+" -已存在请使用其他名称" , "",null, null);
						out.print(obj);
						return null;
					}
					//wh.setGasWelldid(gas_welldid);
				}else {
					WHList = gaswellrpdservice.searchOnly(gas_welldid,"" , "" );
					wh = WHList.get(0);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else{
			//添加 
			
			try {
				WHList = gaswellrpdservice.searchOnly("",gaswellid , reportDate );
				if(WHList !=null && WHList.size()>0){
//					if(WHList.size() ==1){
//						if(!gaswellid.equals(WHList.get(0).getGaswellid())){
//							wh = WHList.get(0);
//						}else{
//							obj = CommonsUtil.getRrturnJson("","井名称："+welName+" -已存在请使用其他名称" , "",null, null);
//							out.print(obj);
//							return null;	
//						}
//					}else if(WHList.size() >1){
						obj = CommonsUtil.getRrturnJson("","井名称："+welName+" -已存在请使用其他名称" , "",null, null);
						out.print(obj);
						return null;
//					}
					//wh.setGasWelldid(gas_welldid);
				}else {
					wh = new PcRpdGasWelldT();
				}
			} catch (Exception e) {
				
			}
			
		}
		wh.setGaswellid(gaswellid);
		wh.setReportDate(DateBean.getStrDate(reportDate));
		wh.setTubingPres(CommonsUtil.isNullOrEmpty(tubingPres));
		wh.setWellTemp(CommonsUtil.isNullOrEmpty(wellTemp));
		wh.setInstantaneousDelivery(CommonsUtil.isNullOrEmpty(delivery));
		wh.setCumulativeDischarge(CommonsUtil.isNullOrEmpty(discharge));
		wh.setDaliyCumulativeFlow(CommonsUtil.isNullOrEmpty(daliyFlow));		
		
		boolean flag = false;
		try {
			flag = gaswellrpdservice.saveOrupdateGasRPDWH(wh);
		} catch (Exception e) {
			String  errmsg = e.getCause().toString();
			if(!"".equals(errmsg) && errmsg.indexOf("ORA-01438") != -1){
				obj = CommonsUtil.getRrturnJson("","您输入信息超过数据库数据精度范围" ,"", null, null);
			} else{
				obj = CommonsUtil.getRrturnJson("",errmsg ,"", null, null);
			}
			e.printStackTrace();
			out.print(obj);
		}
		out.print(outCode);
		return  null;
	}
	public String removeGasRPDWH()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		String whId = StringUtil.toStr(request.getParameter("gas_welldid"));
		boolean deleteflg = false;
		try {
			deleteflg = gaswellrpdservice.removeGasRPDWH(whId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		out.print(outCode);
		return  null;
	}
	
	
//	public String searchGasWelRPDWH() throws Exception {
//		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
//		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
//		response.setCharacterEncoding("utf-8");
//		response.setHeader("ContentType","text/xml");
//		PrintWriter out = response.getWriter();
//		JSONObject obj = new JSONObject();
//		String GasName = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("gasname")));
//		String startDate = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("textDate")));
//		String endStart = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("textDate1")));
//		String outCode = "1";
//		int pageNo = 1; //页索引参数名当前页
//		String sort = "";		//页排序列名
//		String order = "";//页排序方向
//		int rowsPerpage = 0; //每页显示条数
//		if(request.getParameter("pageNo") != null && !"".equals(request.getParameter("pageNo"))){
//			pageNo = Integer.parseInt(request.getParameter("pageNo"));	
//		}
//		
//		sort = StringUtil.isNullUtil(request.getParameter("sort"));
//		order = StringUtil.isNullUtil(request.getParameter("order"));
//		if( request.getParameter("rowsPerpage") != null && !"".equals( request.getParameter("rowsPerpage"))){
//			rowsPerpage = Integer.parseInt(request.getParameter("rowsPerpage"));
//		}
//		JSONObject jsonobj = null;
//		try {
//			jsonobj = gaswellrpdservice.searchGasWelRPDWH(GasName, startDate, endStart,pageNo,sort,order,rowsPerpage);
//		} catch (Exception e) {
//			e.printStackTrace();
//			obj = CommonsUtil.getRrturnJson("","查询出现错误" ,"", null, null);
//			out.print(obj);
//			return null;
//		}
//		
//		if(jsonobj != null && "1".equals(outCode)){
//			out.println(jsonobj);
//			
//		}else{
//			out.println(outCode);
//		}
//		
//	return null;
//	
//}
}
