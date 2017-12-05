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

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.echo.dto.PcCdCliquePoolT;
import com.echo.dto.PcCdLargeStationT;
import com.echo.dto.PcCdOrgT;
import com.echo.dto.PcRdLoginfoT;
import com.echo.dto.User;
import com.echo.service.LogService;
import com.echo.service.ZkxtxxService;
import com.echo.util.AuthorityUtil;
import com.echo.util.CommonsUtil;
import com.echo.util.DateBean;
import com.echo.util.DynamicDataExportUtil;
import com.echo.util.PageVariableUtil;
import com.echo.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

public class ZkxtxxAction extends ActionSupport  {
	/**
	 * 
	 */
	private ZkxtxxService zkxtxxService;
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
	

	
	public void setZkxtxxService(ZkxtxxService zkxtxxService) {
		this.zkxtxxService = zkxtxxService;
	}


	public String getFileName() {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		String EXCLENAME = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("EXCLENAME")));
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd ");
		String downloadFileName = (sf.format(new Date()).toString())+ "站库系统信息.xls";
		
//		if("生产点表".equals(EXCLENAME)){
//			downloadFileName = (sf.format(new Date()).toString())+ "点表信息.xls";
//			
//		}
		
		try {
			downloadFileName = new String(downloadFileName.getBytes(),"ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return downloadFileName;
	}
	public String searchDatas() throws Exception {
			HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
			HttpServletResponse response=ServletActionContext.getResponse();//响应对象
			response.setCharacterEncoding("utf-8");
			response.setHeader("ContentType","text/xml");
			PrintWriter out = response.getWriter();
			
			String objecttype = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("objecttype")));
			String qkmc = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("qkmc")));
			String unitname = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("unitname")));
			String systemcode = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("systemcode")));
			String ljjds = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("ljjds")));
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
				dataMap = zkxtxxService.searchDatas(objecttype,qkmc,unitname,systemcode,ljjds,totalNum,pageNo,sort,order,rowsPerpage);
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
				String templetFilePath = request.getSession().getServletContext().getRealPath("/") + "exceltemplet\\系统管理报表.xls";
//				将用户对象列表输出到workbook的流中，ExcelUtil类进行了封装，后续分析！                                                              
				java.io.ByteArrayOutputStream baos = DynamicDataExportUtil.systemExporReport(dataLsit,templetFilePath,"站库系统信息");
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
			gridJson = AuthorityUtil.getGridJson1("MENU064", user, PageVariableUtil.ZKXTXX_PAGE_GRID);
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
	public String addOrupdateData() throws Exception {
		
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		List<PcCdLargeStationT> largestations = null;
		List<PcCdLargeStationT> largestations1 = null;
		List<Object[]> orgList = null;
		JSONObject obj = new JSONObject();
		PcCdLargeStationT cliquePool=null;
		List<PcCdOrgT> orgs = null;
		PcCdOrgT org = null;
		User user = (User) session.getAttribute("userInfo");
		 
		String large_station_id = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("large_station_id")));	
		
		String OBJECT_NAME = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("OBJECT_NAME")));
		String QKMC = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("QKMC")));
		String UNIT_NAME = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("UNIT_NAME")));
		String SYSTEM_CODE = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("SYSTEM_CODE")));
		String DOWN_SOFTWARE_MODEL = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("DOWN_SOFTWARE_MODEL")));
		String DOWN_SOFTWARE_NAME = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("DOWN_SOFTWARE_NAME")));
		String UPPER_SOFTWARE_MODEL = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("UPPER_SOFTWARE_MODEL")));
		String UPPER_SOFTWARE_NAME = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("UPPER_SOFTWARE_NAME")));
		String INTEGRATOR_NAME = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("INTEGRATOR_NAME")));
		String LJJD_S = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("LJJD_S")));
		
		String remark = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("remark")));
		
		String ACCESS_SIGNS = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("ACCESS_SIGNS")));
		String COMUNIT = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("COMUNIT")));
		
		if(OBJECT_NAME == null || "".equals(OBJECT_NAME)){
			obj = CommonsUtil.getRrturnJson("", "对象类型不能为空", "", null, null);
			out.print(obj);
			return null;
		}
		
		if(QKMC == null || "".equals(QKMC)){
			obj = CommonsUtil.getRrturnJson("", "区块名称不能为空", "", null, null);
			out.print(obj);
			return null;
		}
		if(UNIT_NAME == null || "".equals(UNIT_NAME)){
			obj = CommonsUtil.getRrturnJson("", "单元名不能为空", "", null, null);
			out.print(obj);
			return null;
		}
		if(SYSTEM_CODE == null || "".equals(SYSTEM_CODE)){
			obj = CommonsUtil.getRrturnJson("", "系统代码不能为空", "", null, null);
			out.print(obj);
			return null;
		}
		
		try {
			largestations = zkxtxxService.searchData("", QKMC,UNIT_NAME,"");
			largestations1 = zkxtxxService.searchData("", "","",SYSTEM_CODE);
		} catch (Exception e) {
			e.printStackTrace();
			obj = CommonsUtil.getRrturnJson("", "站库系统信息获取失败", "", null, null);
			out.print(obj);
			return null;
		}
		//更新
		if(large_station_id != null && !"".equals(large_station_id)){
		
			if(largestations != null && largestations.size()>0){
				if(largestations.size() ==1){
					if(!large_station_id.equals(largestations.get(0).getLargeStationId())){
						obj = CommonsUtil.getRrturnJson("", "区块名称,单元名已存在不能重复！", "", null, null);
						out.print(obj);
						return null;
					}
					
				}else if(largestations.size() > 1){
					obj = CommonsUtil.getRrturnJson("", "区块名称,单元名已存在不能重复！", "", null, null);
					out.print(obj);
					return null;
				}
			}
			
			if(largestations1 != null && largestations1.size()>0){
				if(largestations1.size() ==1){
					if(!large_station_id.equals(largestations1.get(0).getLargeStationId())){
						obj = CommonsUtil.getRrturnJson("", "系统代码已存在不能重复！", "", null, null);
						out.print(obj);
						return null;
					}
					
				}else if(largestations1.size() > 1){
					obj = CommonsUtil.getRrturnJson("", "系统代码已存在不能重复！", "", null, null);
					out.print(obj);
					return null;
				}
			}
			
			
			
			try {
				largestations = zkxtxxService.searchData(large_station_id, "","","");
			} catch (Exception e) {
				e.printStackTrace();
				obj = CommonsUtil.getRrturnJson("", "阀池数据获取失败", "", null, null);
				out.print(obj);
				return null;
			}
			
			
			if(largestations != null && largestations.size()>0){
				cliquePool = largestations.get(0);
				
			}
			
		//添加
		}else{
			if(largestations != null && largestations.size()>0){
				obj = CommonsUtil.getRrturnJson("", "区块名称,单元名已存在不能重复！", "", null, null);
				out.print(obj);
				return null;
			}
			
			if(largestations1 != null && largestations1.size()>0){
				obj = CommonsUtil.getRrturnJson("", "系统代码已存在不能重复！", "", null, null);
				out.print(obj);
				return null;
			}
			
			cliquePool = new PcCdLargeStationT();
			cliquePool.setCreateDate(new Date());
		}
		
		cliquePool.setObjectCode(OBJECT_NAME);
		cliquePool.setQkmc(QKMC);
		cliquePool.setUnitName(UNIT_NAME);
		cliquePool.setSystemCode(SYSTEM_CODE);
		cliquePool.setDownSoftwareModel(DOWN_SOFTWARE_MODEL);
		cliquePool.setDownSoftwareFacturer(DOWN_SOFTWARE_NAME);
		cliquePool.setUpperSoftwareModel(UPPER_SOFTWARE_MODEL);
		cliquePool.setUpperSoftwareFacturer(UPPER_SOFTWARE_NAME);
		cliquePool.setIntegratorCode(INTEGRATOR_NAME);
		cliquePool.setScadaNode(LJJD_S);
		if(ACCESS_SIGNS.equals("接入")){
			cliquePool.setAccessSigns("0");
		}else if(ACCESS_SIGNS.equals("未接入")){
			cliquePool.setAccessSigns("1");
		}else{
			cliquePool.setAccessSigns("");
		}
		
		cliquePool.setCombinationStationid(COMUNIT);
		
		cliquePool.setRemark(remark);
		cliquePool.setRlastOper(user.getOperName());
		cliquePool.setRlastOdate(new Date());
		
		boolean flg = true;
		try {
			flg = zkxtxxService.addOrUpdateData(cliquePool);
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
		
		
		if(large_station_id != null && !"".equals(large_station_id)){
			//添加系统LOG
			try {
				PcRdLoginfoT log = CommonsUtil.getLogInfo(user, "3", "阀池基础信息", cliquePool.getLargeStationId());
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
				PcRdLoginfoT log = CommonsUtil.getLogInfo(user, "1", "阀池基础信息", cliquePool.getLargeStationId());
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

	
	public String removeData() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		
		String large_station_id = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("large_station_id")));
		String org_id = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("org_id")));
		JSONObject obj = new JSONObject();
		boolean deleteflg = false;
		try {
			deleteflg = zkxtxxService.removeData(org_id);
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
				PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "2", "阀池基础信息", large_station_id);
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

	public String getComData()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		
		String oilid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("oilid")));
		JSONArray jsonArr = null;
		try {
			jsonArr = zkxtxxService.getComData(oilid);
		} catch (Exception e) {
			this.addFieldError("errermsg", "班组信息获取失败~");
			e.printStackTrace();
		}
		if(jsonArr != null){
			out.println(jsonArr);
		}else{
			out.println("");
		}
		return null;
	}
	
	
}
