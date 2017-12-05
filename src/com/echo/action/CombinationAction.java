package com.echo.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.echo.dto.PcCdCombinationStationT;
import com.echo.dto.PcCdOrgT;
import com.echo.dto.PcRdLoginfoT;
import com.echo.dto.User;
import com.echo.service.CombinationService;
import com.echo.service.CommonService;
import com.echo.service.LogService;
import com.echo.util.AuthorityUtil;
import com.echo.util.CommonsUtil;
import com.echo.util.PageVariableUtil;
import com.echo.util.StringUtil;

public class CombinationAction {
	private CombinationService combinationService;
	private LogService logService;
	private   CommonService commonService;
	public LogService getLogService() {
		return logService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	
	public CombinationService getCombinationService() {
		return combinationService;
	}
	public void setCombinationService(CombinationService combinationService) {
		this.combinationService = combinationService;
	}
	
	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}

	HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
	@SuppressWarnings("unused")
	public String searchCombination() throws IOException {
		
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
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
 		JSONObject jsonobj = null;
 		String stationNumber = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("combinationnname")));
 		try {
 		jsonobj = combinationService.searchCombination(stationNumber,"","",pageNo,sort,order,rowsPerpage);
 		} catch (Exception e) {
			outCode = "-17101";
		}
 		if(jsonobj != null){
			out.println(jsonobj);
		}
		return null;
	}
	//根据权限进行页面初始化
	public String pageInit()throws Exception{
		
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		
		//根据用户权限生成用户权限生成页面布局格式
		User user = (User) session.getAttribute("userInfo");
		
		String gridJson = AuthorityUtil.getGridJson("联合站基础信息", user, PageVariableUtil.COMBINATION_PAGE_GRID);
//		System.out.println(gridJson);
		out.print(gridJson);
		return null;
	}
	@SuppressWarnings("unused")
	public String removeCombination() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		String stationId = StringUtil.toStr(request.getParameter("combinationId"));
		String orgId = StringUtil.toStr(request.getParameter("org_id"));
		boolean deleteflg = false;
		JSONObject obj = new JSONObject();
		try {
			deleteflg = combinationService.removeStationInfo(stationId , orgId);
		} catch (Exception e) {
			String errmsg = e.getCause().toString();
			//ORA-02292: 违反完整约束条件 (NEWPORTAL.FK58_PC_RPD_WELL_SAGDD_T) - 已找到子记录
			if(!"".equals(errmsg) && errmsg.indexOf("ORA-02292") != -1 && errmsg.indexOf("FK58_PC_RPD_WELL_SAGDD_T") != -1){
				obj = CommonsUtil.getRrturnJson("","联合站基础信息删除失败-联合站日报中存在子记录" ,"", null, null);
			}else if(!"".equals(errmsg) && errmsg.indexOf("ORA-02292") != -1 && errmsg.indexOf("FK_PC_RPD_W_FK52_PC_R_PC_CD_WE") != -1){
				obj = CommonsUtil.getRrturnJson("","联合站基础信息删除失败-联合站班报中存在子记录" , "",null, null);
			} else{
				obj = CommonsUtil.getRrturnJson("",errmsg ,"", null, null);
			}
			e.printStackTrace();
		}
		
		if(deleteflg){
			
			//添加系统LOG
			try {
				User user1 = (User) session.getAttribute("userInfo");
				PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "2", "联合站基础信息", stationId);
				logService.addLog(log);
			} catch (Exception e) {
				e.printStackTrace();
				obj = CommonsUtil.getRrturnJson("-10003","", "", null, null);
			}
			
			
		}
		out.print(obj);
		return null;
	}
	
	
	@SuppressWarnings("unused")
	public	String addCombination() throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		List<PcCdCombinationStationT>  combiList = null;
		PcCdCombinationStationT comb = null;
		boolean addflg =  true;

		String  combinationStationName = StringUtil.toStr(request.getParameter("combination_station_name").trim());
		
		try {
			combiList = combinationService.searchName(combinationStationName);
		} catch (Exception e) {
			e.printStackTrace();
			//联合站名称获取失败
			outCode = "-11010";
		}
		if(combiList !=null && combiList.size()>0){
			//联合站已存在,不允许用户添加
			outCode = "-11011";
		}else{
			
			User sessionuser = (User) session.getAttribute("userInfo");
			comb = new PcCdCombinationStationT();
			comb.setCombinationStationName(combinationStationName);
			
			String combinationStationType = StringUtil.toStr(request.getParameter("combination_station_type").trim());
			if(combinationStationType != null && !"".equals(combinationStationType)){
				comb.setCombinationStationType(combinationStationType);
			}
			

			
			comb.setRemark(StringUtil.toStr(request.getParameter("remark").trim()));
			comb.setRlastOper(sessionuser.getOperName());
			comb.setRlastOdate(new Date());
			
			PcCdOrgT org = new PcCdOrgT();
				org.setCode(StringUtil.toStr(request.getParameter("combination_station_code").trim()));
			org.setStructurename(combinationStationName);
//			String commissioning_date = StringUtil.toStr(request.getParameter("commissioning_date").trim());
//			if(commissioning_date !=null && !"".equals(commissioning_date)){
//				org.setCommissioningDate(DateBean.getStrDate(commissioning_date));
//			}
			String statusvalues = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("status_value")));
			
			if (!"".equals(statusvalues)) {
				try {
					statusvalues = commonService.getStatusValueINT(statusvalues);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			org.setStatusValue(statusvalues);
			org.setPid("F24C74E3ACB645679444FE61D9DD1552");
			org.setStructuretype((byte) 5);
			
			comb.setPcCdOrgT(org);
			org.setPcCdCombinationStationT(comb);
			
			try {
				addflg = combinationService.addComb(comb);
			} catch (Exception e) {
				e.printStackTrace();
				//联合站添加失败
				outCode="-11012";
			}
		}
		out.print(outCode);
		return null;
	}
	
	@SuppressWarnings("unused")
	public  String  updateCombination() throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		
		List<PcCdCombinationStationT> combList = null;
		PcCdCombinationStationT comb = null;

		String combinationStationid = StringUtil.toStr(request.getParameter("combination_stationid").trim());
		String combinationStationName = StringUtil.toStr(request.getParameter("combination_station_name").trim());
		if(combinationStationid !=null && !"".equals(combinationStationid)){
			try {
				combList = combinationService.serachCombById("" ,combinationStationName );
			} catch (Exception e) {
				e.printStackTrace();
				outCode="-11010";
			}
			if(combList != null && combList.size()==1){
				if(!combinationStationid.equals(combList.get(0).getCombinationStationid())){
					//联合站同条数据进行修改
					outCode = "11013";
				}

			}else if (combList != null && combList.size()>1){
					//联合站名称已存在不能修改
				//联合站名称已存在
					outCode = "-11011";
				}else{
					try {
						combList = new ArrayList<PcCdCombinationStationT>();
						combList  = combinationService.serachCombById(combinationStationid,"");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
					boolean addflg = true;
					User sessionuser = (User) session.getAttribute("userInfo");
					if(combList !=null && combList.size()>0){
						comb = combList.get(0);
						comb.setCombinationStationName(combinationStationName);
						String combination_station_type = StringUtil.toStr(request.getParameter("combination_station_type").trim());
						if(combination_station_type != null && !"".equals(combination_station_type)){
							comb.setCombinationStationType(combination_station_type);
						}
						

						comb.setRemark(StringUtil.toStr(request.getParameter("remark").trim()));
						comb.setRlastOper(sessionuser.getOperName());
						comb.setRlastOdate(new Date());
						PcCdOrgT org = null;
						org = comb.getPcCdOrgT();
						org.setCode(StringUtil.toStr(request.getParameter("combination_station_code").trim()));
						org.setStructurename(combinationStationName);
						//String commissioning_date = StringUtil.toStr(request.getParameter("commissioning_date").trim());
//						if(commissioning_date !=null && !"".equals(commissioning_date)){
//							org.setCommissioningDate(DateBean.getStrDate(commissioning_date));
//						}
						String statusvalues = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("status_value")));
						
						if (!"".equals(statusvalues)) {
							try {
								statusvalues = commonService.getStatusValueINT(statusvalues);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						org.setStatusValue(statusvalues);
						org.setPid("F24C74E3ACB645679444FE61D9DD1552");
						org.setStructuretype((byte) 5);
						org.setPcCdCombinationStationT(comb);
						comb.setPcCdOrgT(org);
						
					try {
						addflg = combinationService.updateComb(comb);
					} catch (Exception e) {
						e.printStackTrace();
						outCode = "-11112";
							//联合站更新失败
					}
					}
		
		}else{
			//联合站ID获取失败
			outCode = "-11114";
		}
		out.print(outCode);
		return null;
	}

}