package com.echo.action;

import java.io.ByteArrayInputStream;
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
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;
import com.echo.dto.PcCdCliquePoolT;
import com.echo.dto.PcCdOrgT;
import com.echo.dto.PcRdLoginfoT;
import com.echo.dto.User;
import com.echo.service.FcjcxxService;
import com.echo.service.LogService;
import com.echo.util.AuthorityUtil;
import com.echo.util.CommonsUtil;
import com.echo.util.DateBean;
import com.echo.util.DynamicDataExportUtil;
import com.echo.util.PageVariableUtil;
import com.echo.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

public class FcjcxxAction extends ActionSupport  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private FcjcxxService fcjcxxService;
	private InputStream excelFile = null;
	private  LogService logService;
	public InputStream getExcelFile() {
		return excelFile;
	}


	public void setExcelFile(InputStream excelFile) {
		this.excelFile = excelFile;
	}


	
	HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
	

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	
	public void setFcjcxxService(FcjcxxService fcjcxxService) {
		this.fcjcxxService = fcjcxxService;
	}

	public String getFileName() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd ");
		String downloadFileName = (sf.format(new Date()).toString())+ "阀池基础信息.xls";
		try {
			downloadFileName = new String(downloadFileName.getBytes(),"ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return downloadFileName;
	}
	@SuppressWarnings({ "unused", "unchecked" })
	public String searchDatas() throws Exception {
			HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
			HttpServletResponse response=ServletActionContext.getResponse();//响应对象
			response.setCharacterEncoding("utf-8");
			response.setHeader("ContentType","text/xml");
			PrintWriter out = response.getWriter();
			
			String oilstationname = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("oilstationname")));
			String blockstationname = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("blockstationname")));
			String cliquepool = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("cliquepool")));
			String cliquetype = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("cliquetype")));
			String totalNum = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("totalNum")));
		
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
			HashMap<String,Object> dataMap = null;
			try {
				dataMap = fcjcxxService.searchDatas(oilstationname,blockstationname,cliquepool,cliquetype,totalNum,pageNo,sort,order,rowsPerpage);
			} catch (Exception e) {
				outCode = "-11101";
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
//				将用户对象列表输出到workbook的流中，ExcelUtil类进行了封装，后续分析！
				java.io.ByteArrayOutputStream baos = DynamicDataExportUtil.basicDataExporReport(dataLsit,templetFilePath,"阀池基础信息");
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
			gridJson = AuthorityUtil.getGridJson1("MENU151", user, PageVariableUtil.FCJCXX_PAGE_GRID);
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
	
	//修改
	@SuppressWarnings("unused")
	public String addOrupdateData() throws Exception {
		
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		List<PcCdCliquePoolT> cliquePools = null;
		List<Object[]> orgList = null;
		JSONObject obj = new JSONObject();
		PcCdCliquePoolT cliquePool=null;
		List<PcCdOrgT> orgs = null;
		PcCdOrgT org = null;
		User user = (User) session.getAttribute("userInfo");
		 
		String org_id = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("org_id")));	
		String clique_id = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("clique_id")));
		
		String CLIQUE_POOL_NAME = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("CLIQUE_POOL_NAME")));
		String CODE = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("CODE")));
		String CLIQUE_TYPE = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("CLIQUE_TYPE")));
		String STATION_NAME = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("STATION_NAME")));
		String RTU_CODE = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("RTU_CODE")));
		String LJJD_S = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("LJJD_S")));
		String JRBZ = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("JRBZ")));
		String INSTALL_DATE = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("INSTALL_DATE")));
		String remark = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("remark")));
		
		if(CLIQUE_POOL_NAME == null || "".equals(CLIQUE_POOL_NAME)){
			obj = CommonsUtil.getRrturnJson("", "阀池名称不能为空", "", null, null);
			out.print(obj);
			return null;
		}
		
		
		try {
			cliquePools = fcjcxxService.searchData("", CLIQUE_POOL_NAME);
		} catch (Exception e) {
			e.printStackTrace();
			obj = CommonsUtil.getRrturnJson("", "阀池数据获取失败", "", null, null);
			out.print(obj);
			return null;
		}
		//更新
		if(clique_id != null && !"".equals(clique_id)){
		
			if(cliquePools != null && cliquePools.size()>0){
				if(cliquePools.size() ==1){
					if(!clique_id.equals(cliquePools.get(0).getCliqueId())){
						obj = CommonsUtil.getRrturnJson("", "阀池名称已存,请修改其他名称", "", null, null);
						out.print(obj);
						return null;
					}
					
				}else if(cliquePools.size() > 1){
					obj = CommonsUtil.getRrturnJson("", "阀池名称已存,请修改其他名称", "", null, null);
					out.print(obj);
					return null;
				}
			}
			try {
				cliquePools = fcjcxxService.searchData(clique_id, "");
			} catch (Exception e) {
				e.printStackTrace();
				obj = CommonsUtil.getRrturnJson("", "阀池数据获取失败", "", null, null);
				out.print(obj);
				return null;
			}
			
			
			if(cliquePools != null && cliquePools.size()>0){
				cliquePool = cliquePools.get(0);
				org = cliquePool.getPcCdOrgT();
//				try {
//					orgs = fcjcxxService.searchOrgData(cliquePool.getCliqueId(), "");
//				} catch (Exception e) {
//					e.printStackTrace();
//					obj = CommonsUtil.getRrturnJson("", "组织结构数据获取失败", "", null, null);
//					out.print(obj);
//					return null;
//				}
//				
//				
//				if(orgs != null && orgs.size()>0){
//					org = orgs.get(0);
//				}else{
//					obj = CommonsUtil.getRrturnJson("", "组织结构数据获取失败", "", null, null);
//					out.print(obj);
//					return null;
//				}
				
			}
			
		//添加
		}else{
			if(cliquePools != null && cliquePools.size()>0){
				obj = CommonsUtil.getRrturnJson("", "阀池名称已存,请修改其他名称", "", null, null);
				out.print(obj);
				return null;
			}
			
			cliquePool = new PcCdCliquePoolT();
			org = new PcCdOrgT();
		}
		
		cliquePool.setCliquePoolName(CLIQUE_POOL_NAME);
		cliquePool.setCliqueType(CLIQUE_TYPE);
		cliquePool.setRtuCode(RTU_CODE);
		cliquePool.setInstallDate(DateBean.getStrDateNotNull(CommonsUtil.getClearNullData(INSTALL_DATE)));
		cliquePool.setRemark(remark);
		cliquePool.setRlastOper(user.getOperName());
		cliquePool.setRlastOdate(new Date());
		
		
		org.setStructurename(CLIQUE_POOL_NAME);
		org.setStructuretype((byte)22);
		if(STATION_NAME != null && !"".equals(STATION_NAME)){
			try {
				orgs = fcjcxxService.searchOrgData(STATION_NAME, "");
			} catch (Exception e) {
				e.printStackTrace();
				obj = CommonsUtil.getRrturnJson("", "所属注转站数据获取失败", "", null, null);
				out.print(obj);
				return null;
			}
			if(orgs != null && orgs.size()>0){
				org.setPid(STATION_NAME);
			}else{
				obj = CommonsUtil.getRrturnJson("", "所属注转站未获取组织结构数据，请重新选择", "", null, null);
				out.print(obj);
				return null;
			}
			
		}else{
			org.setPid(null);
		}
		org.setScadaNode(LJJD_S);
		org.setSwitchInFlag(JRBZ);
		org.setCode(CODE);
		
		org.setPcCdCliquePoolT(cliquePool);
		cliquePool.setPcCdOrgT(org);
		
		boolean flg = true;
		try {
			flg = fcjcxxService.addOrUpdateData(cliquePool);
		} catch (Exception e) {
			String  errmsg = e.getCause().getCause().toString();
			
			if(!"".equals(errmsg) && errmsg.indexOf("ORA-01438") != -1){
				obj = CommonsUtil.getRrturnJson("","您输入信息超过数据库数据精度范围" ,"", null, null);
			} else{
				obj = CommonsUtil.getRrturnJson("","操作失败："+errmsg ,"", null, null);
			}
			e.printStackTrace();
			out.print(obj);
			return null;
		}
		
		
		if(clique_id != null && !"".equals(clique_id)){
			//添加系统LOG
			try {
				PcRdLoginfoT log = CommonsUtil.getLogInfo(user, "1", "阀池基础信息", cliquePool.getCliqueId());
				logService.addLog(log);
			} catch (Exception e) {
				e.printStackTrace();
				obj = CommonsUtil.getRrturnJson("", "阀池基础信修改加成功，LOG信息添加失败", "", null, null);
				out.print(obj);
				return null;
			}
		}else{
			//添加系统LOG
			try {
				PcRdLoginfoT log = CommonsUtil.getLogInfo(user, "3", "阀池基础信息", cliquePool.getCliqueId());
				logService.addLog(log);
			} catch (Exception e) {
				e.printStackTrace();
				obj = CommonsUtil.getRrturnJson("", "阀池基础信息息添成功，LOG信息添加失败", "", null, null);
				out.print(obj);
				return null;
			}
			
		}
		out.print(obj);
		return null;
	}

	
	@SuppressWarnings("unused")
	public String removeData() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		
		String clique_id = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("clique_id")));
		String org_id = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("org_id")));
		JSONObject obj = new JSONObject();
		boolean deleteflg = false;
		try {
			deleteflg = fcjcxxService.removeData(org_id);
		} catch (Exception e) {
			String errmsg = e.getCause().toString();
			//ORA-02292: 违反完整约束条件 (NEWPORTAL.FK58_PC_RPD_WELL_SAGDD_T) - 已找到子记录
			if(!"".equals(errmsg) && errmsg.indexOf("ORA-02292") != -1 && errmsg.indexOf("FK58_PC_RPD_WELL_SAGDD_T") != -1){
				obj = CommonsUtil.getRrturnJson("","气站基础信息删除失败-气站日报中存在子记录" ,"", null, null);
			}else if(!"".equals(errmsg) && errmsg.indexOf("ORA-02292") != -1 && errmsg.indexOf("FK_PC_RPD_W_FK52_PC_R_PC_CD_WE") != -1){
				obj = CommonsUtil.getRrturnJson("","气站基础信息删除失败-气站班报中存在子记录" ,"", null, null);
			} else{
				obj = CommonsUtil.getRrturnJson("",errmsg ,"", null, null);
			}
			e.printStackTrace();
		}
		
		if(deleteflg){
			
			//添加系统LOG
			try {
				User user1 = (User) session.getAttribute("userInfo");
				PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "2", "阀池基础信息", clique_id);
				logService.addLog(log);
			} catch (Exception e) {
				e.printStackTrace();
				obj = CommonsUtil.getRrturnJson("-10003", "", "",null, null);
			}
			
			
		}else{
			obj = CommonsUtil.getRrturnJson("","阀池基础信息删除失败" ,"", null, null);
		}
		out.print(obj);
		return null;
	}

	
	
}
