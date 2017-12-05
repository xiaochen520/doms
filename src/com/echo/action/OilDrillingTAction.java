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
import org.junit.runner.Request;

import com.echo.dto.PcCdOildrillingStationT;
import com.echo.dto.PcCdOrgT;
import com.echo.dto.PcRdLoginfoT;
import com.echo.dto.User;
import com.echo.service.CommonService;
import com.echo.service.LogService;
import com.echo.service.OilDrillingService;
import com.echo.util.AuthorityUtil;
import com.echo.util.CommonsUtil;
import com.echo.util.DateBean;
import com.echo.util.PageVariableUtil;
import com.echo.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

public class OilDrillingTAction extends ActionSupport {
	private static final long serialVersionUID = 12L;
	HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
	private OilDrillingService oilDriService;
	private CommonService commonService;
	public OilDrillingService getOilDriService() {
		return oilDriService;
	}

	public void setOilDriService(OilDrillingService oilDriService) {
		this.oilDriService = oilDriService;
	}

	private LogService logService;

	public LogService getLogService() {
		return logService;
	}

	
	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	public String searchOilStation() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		
		String stationNumber = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("oilstationname")));
		String boilersName = "";
		String areablock ="";
		String outCode = "1";
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
			jsonobj = oilDriService.queryOilStation(stationNumber, boilersName, areablock,pageNo,sort,order,rowsPerpage);
		} catch (Exception e) {
			outCode = "-17101";
		}
		
		if(jsonobj != null && "1".equals(outCode)){
			out.println(jsonobj);
			
		}else{
			out.println(outCode);
		}
		
	return null;
	
	
	
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
			gridJson = AuthorityUtil.getGridJson("采油站基础信息", user, PageVariableUtil.OILDRILLING_PAGE_GRID);
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
	
	public String addOildrilling()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		boolean  addflg = true;
		PcCdOildrillingStationT oild = null;
		List<PcCdOildrillingStationT> oildList = null;
		String oildrilling_stationname = StringUtil.toStr(request.getParameter("oildrilling_stationname").trim());
		try {
			oildList = oilDriService.searchstatinByName(oildrilling_stationname);
			//查询采油队
		} catch (Exception e) {
			e.printStackTrace();
			//采油站名称获取失败
			outCode="-11310";
		}
		if(oildList !=null && oildList.size()>0){
			//采油站名称已存在，不容许添加
			outCode="-11311";
		}else{
			
			User sessionuser = (User) session.getAttribute("userInfo");
			oild= new PcCdOildrillingStationT();
			oild.setOildrillingStationName(oildrilling_stationname);
			oild.setRlastOper(sessionuser.getOperName());
			oild.setRlastOdate(new Date());
			oild.setRemark(StringUtil.toStr(request.getParameter("remark").trim()));
			
			PcCdOrgT org = null;
			org = new PcCdOrgT();
			String statusvalues = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("status_value")));
			if (!"".equals(statusvalues)) {
				try {
					statusvalues = commonService.getStatusValueINT(statusvalues);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			org.setStatusValue(statusvalues);
			org.setStructurename(oildrilling_stationname);
			//oild.setScrappedDate(DateBean.getStrDate(request.getParameter("scrapped_date").trim()));
			//String commissioningDate = StringUtil.toStr(request.getParameter("commissioning_date").trim());
			//if(commissioningDate != null && !"".equals(commissioningDate)){
				//org.setCommissioningDate(DateBean.getStrDate(commissioningDate));
			//}
			org.setStructuretype((byte) 4);
			org.setPid("ORG001");
			oild.setPcCdOrgT(org);
			org.setPcCdOildrillingStationT(oild);
			try {
				addflg = oilDriService.addOil(oild);
			} catch (Exception e) {
				e.printStackTrace();
				outCode="-11312";
				//采油队更新失败
			}
			
		}
//		if(addflg){
//			//添加系统LOG
//			try {
//				PcRdLoginfoT log = CommonsUtil.getLogInfo(sessionuser, "1", "部门基础信息", oild.getOildrillingStationid());
//				logService.addLog(log);
//			} catch (Exception e) {
//				e.printStackTrace();
//				outCode = "-10001";
//			}
//			
//		}
		out.print(outCode);
		
		return null;
	}
	
	public  String  updateOildrilling() throws IOException{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		
		List<PcCdOildrillingStationT> oildrList = null;
		PcCdOildrillingStationT  oild = null;

		String oildrillingstationid = request.getParameter("oildrilling_stationid").trim();
		String oildrillingstationname = StringUtil.toStr(request.getParameter("oildrilling_stationname").trim());
		if(oildrillingstationid !=null && !"".equals(oildrillingstationname)){
			try {
				oildrList = oilDriService.serachOildById("" ,oildrillingstationname );
			} catch (Exception e) {
				e.printStackTrace();
				outCode="-11310";
			}
			if(oildrList != null && oildrList.size()==1){
				if(!oildrillingstationid.equals(oildrList.get(0).getOildrillingStationid())){
					//采油队铜条数据进行修改
					outCode = "-11311";
				}
			}else if (oildrList != null && oildrList.size()>1){
					//采油队数据库名称已存在不能修改
					outCode = "11311";
				}else{
					try {
						oildrList = new ArrayList<PcCdOildrillingStationT>();
						oildrList  = oilDriService.serachOildById(oildrillingstationid,"");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
					boolean addflg = true;
					User sessionuser = (User) session.getAttribute("userInfo");
					if(oildrList !=null && oildrList.size()>0){
						oild = oildrList.get(0);
						String oildrillingStationName = StringUtil.toStr(request.getParameter("oildrilling_stationname").trim());
						oild.setOildrillingStationName(oildrillingStationName);
						oild.setRlastOper(sessionuser.getOperName());
						oild.setRlastOdate(new Date());
						oild.setRemark(StringUtil.toStr(request.getParameter("remark").trim()));
						PcCdOrgT org = null;
						org = oild.getPcCdOrgT();
						org.setStructurename(oildrillingStationName);
						String statusvalues = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("status_value")));
						
						if (!"".equals(statusvalues)) {
							try {
								statusvalues = commonService.getStatusValueINT(statusvalues);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						org.setStatusValue(statusvalues);
//						String commissioningDate  = StringUtil.toStr(request.getParameter("commissioning_date").trim());
//						if( commissioningDate != null && !"".equals(commissioningDate)){
//							org.setCommissioningDate(DateBean.getStrDate(commissioningDate));
//							
//						}
					}
					try {
						addflg = oilDriService.updateSta(oild);
					} catch (Exception e) {
						e.printStackTrace();
						outCode = "11313";
							//采油站更新失败
					}
					
				
			
		
		}else{
			//采油站ID获取失败
			outCode = "-113";
		}
		out.print(outCode);
		return null;
		
	}
	
	
	public String removeOildrilling() throws IOException {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		String stationId = StringUtil.toStr(request.getParameter("oildrilling_stationId"));
		String org_id = StringUtil.toStr(request.getParameter("org_id"));
		boolean deleteflg = false;
		JSONObject obj = new JSONObject();
		try {
			deleteflg = oilDriService.removeStationInfo(stationId,org_id);
		} catch (Exception e) {
			String errmsg = e.getCause().toString();
			//ORA-02292: 违反完整约束条件 (NEWPORTAL.FK58_PC_RPD_WELL_SAGDD_T) - 已找到子记录
			if(!"".equals(errmsg) && errmsg.indexOf("ORA-02292") != -1 && errmsg.indexOf("FK58_PC_RPD_WELL_SAGDD_T") != -1){
				obj = CommonsUtil.getRrturnJson("","采油站基础信息删除失败-采油站日报中存在子记录" ,"", null, null);
			}else if(!"".equals(errmsg) && errmsg.indexOf("ORA-02292") != -1 && errmsg.indexOf("FK_PC_RPD_W_FK52_PC_R_PC_CD_WE") != -1){
				obj = CommonsUtil.getRrturnJson("","采油站基础信息删除失败-采油站班报中存在子记录" ,"", null, null);
			} else{
				obj = CommonsUtil.getRrturnJson("",errmsg ,"", null, null);
			}
			e.printStackTrace();
		}
		
		if(deleteflg){
			
			//添加系统LOG
			try {
				User user1 = (User) session.getAttribute("userInfo");
				PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "2", "采油站基础信息", stationId);
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
