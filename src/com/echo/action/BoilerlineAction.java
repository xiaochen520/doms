package com.echo.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.echo.dto.PcCdBlCellT;
import com.echo.dto.PcCdBoilerCellT;
import com.echo.dto.PcCdWellCellT;
import com.echo.dto.PcRdLoginfoT;
import com.echo.dto.User;
import com.echo.service.BoilerlineService;
import com.echo.service.LogService;
import com.echo.util.AuthorityUtil;
import com.echo.util.CommonsUtil;
import com.echo.util.PageVariableUtil;
import com.echo.util.PropertyUtil;
import com.echo.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

public class BoilerlineAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4154345270195887433L;
	private BoilerlineService boilerlineService;
	private LogService logService;

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	public void setBoilerlineService(BoilerlineService boilerlineService) {
		this.boilerlineService = boilerlineService;
	}



	HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
	
	/**
	 * 用于接收前台传入的参数 SET AND GET
	 */

	
	
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
			gridJson = AuthorityUtil.getGridJson("炉线管理", user, PageVariableUtil.BOILERLINE_PAGE_GRID);
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

	
//	public String addDep() throws Exception {
//		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
//		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
////		List<TbRole> roleids = new ArrayList<TbRole>();
//		response.setCharacterEncoding("utf-8");
//		response.setHeader("ContentType","text/xml");
//		PrintWriter out = response.getWriter();
//		PcCdDepartmentT dep = null;
//		boolean addflg = true;
//		String outCode = "1";
//		User sessionuser = (User) session.getAttribute("userInfo");
//		dep = new PcCdDepartmentT();
//		String department_name = StringUtil.toStr(request.getParameter("department_name").trim());
//		List<PcCdDepartmentT> departmentT = null;
//		try {
//			departmentT = departmentService.searchDepartment("", department_name);
//		} catch (Exception e) {
//			e.printStackTrace();
//			outCode = "-11701";
//		}
//		
//		if(departmentT != null && departmentT.size()>0){
//			outCode = "-11707";
//		}
//		if("1".equals(outCode)){
//			dep.setDepartmentName(department_name);
//			dep.setDepartmentHeader(StringUtil.toStr(request.getParameter("department_header").trim()));
//			dep.setDepartmentAddress(StringUtil.toStr(request.getParameter("department_address").trim()));
//			String phone = StringUtil.toStr(request.getParameter("department_phone").trim());
//			if(phone != null && !"".equals(phone)){
//				dep.setDepartmentPhone(Long.parseLong(phone));
//			}
//			dep.setRemark(StringUtil.toStr(request.getParameter("remark").trim()));
//			dep.setRlastOdate(new Date());
//			dep.setRlastOper(sessionuser.getOperName());
//			//PcCdOrgT org = new PcCdOrgT();
//			//org.setStructurename(department_name);
//			//部门数据暂时设定20
//			//org.setStructuretype((byte)20);
//			//org.setPid("ORG001");
////			dep.setPcCdOrgT(org);
////			org.setPcCdDepartmentT(dep);
////			
//			try {
//				addflg = departmentService.addDep(dep);
//			} catch (Exception e) {
//				e.printStackTrace();
//				//部门信息添加失败
//				outCode = "-11701";
//			}
//			if("1".equals(outCode) && addflg == true){
//				//添加系统LOG
//				try {
//					PcRdLoginfoT log = CommonsUtil.getLogInfo(sessionuser, "1", "部门基础信息", dep.getDepartmentid());
//					logService.addLog(log);
//				} catch (Exception e) {
//					e.printStackTrace();
//					outCode = "-10001";
//				}
//				
//			}
//		}
//		
//		out.print(outCode);
//		return null;
//	}
	
	@SuppressWarnings("unused")
	public String seachblines() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String	username =""; //用戶名
		int pageNo = 1; //页索引参数名当前页
		String sort = "";		//页排序列名
		String order = "";//页排序方向
		int rowsPerpage = 0; //每页显示条数
		String outCode = "1";
		String txtdate ="";
		
		if(request.getParameter("txtdate") != null){
			txtdate = StringUtil.toStr(request.getParameter("txtdate").trim());	
		}
		
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
		List<Object[]> bline = null;
		try {
			jsonobj = boilerlineService.searchBlines(txtdate,pageNo,sort,order,rowsPerpage);
		} catch (Exception e) {
			e.printStackTrace();
			//路线管理数据获取失败
			outCode = "-17101";
		}
	
		
		if(jsonobj != null && "1".equals(outCode)){
			if(jsonobj.get("ERRORS") != null && !"".equals(jsonobj.get("ERRORS"))){
				out.println(jsonobj.get("ERRORS"));
			}
			out.println(jsonobj);
			
		}else{
			out.println(outCode);
		}
		
	return null;
}
	
	
	@SuppressWarnings("unused")
	public String getSAGDFP() throws Exception {

		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONObject obj = new JSONObject();
//		String outCode = "1";
		List<String> rusl = new ArrayList<String>();
		try {
			rusl  = boilerlineService.getSAGDFP();
		} catch (Exception e) {
			e.printStackTrace();
			//当前日期的注汽分配失败
//			outCode = "-17104";
			//String errmsg = e.getCause().getLocalizedMessage();
			obj = CommonsUtil.getRrturnJson("当前日期的注汽分配失败","" ,"", null, null);
		}
		
		if(rusl != null && rusl.size()>0){
			obj = CommonsUtil.getRrturnJson("",rusl.get(1).toString() ,"", null, null);
			
//			if("0".equals(rusl.get(0))){
////				outCode = "-17104";
////				out.println(outCode);
//			
//			}else{
//				
//				out.println(rusl.get(1));
//			}
		}else{
			obj = CommonsUtil.getRrturnJson("-17104","" ,"", null, null);
		}
//		if(jsonArr != null && "1".equals(outCode)){
//			out.println(jsonArr);
//			
//		}else{
			out.println(obj);
//		}
			return null;
		

		
		
	}
	public String seachGLJKS() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		String cell_id = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("CELL_ID")));
		JSONObject jsonArr = null;
		try {
			jsonArr  = boilerlineService.seachGLJKS(cell_id);
		} catch (Exception e) {
			e.printStackTrace();
			//炉线节点与锅炉、井口对应关系数据获取失败
			outCode = "-17102";
		}
		
		
		if(jsonArr != null && "1".equals(outCode)){
			out.println(jsonArr);
			
		}else{
			out.println(outCode);
		}
			return null;
		
}
	

//	public String updateDep() throws Exception {
//		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
//		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
//		response.setCharacterEncoding("utf-8");
//		response.setHeader("ContentType","text/xml");
//		PrintWriter out = response.getWriter();
//		String outCode = "1";
//		List<PcCdDepartmentT> depList = null;
//		List<PcCdDepartmentT> nameList = null;
//		PcCdDepartmentT dep = null;
//		String depid = request.getParameter("depid").trim();
//		String departmentname = StringUtil.toStr(request.getParameter("department_name").trim());
//		if(depid != null && !"".equals(depid)){
//			try {
//				depList = (List<PcCdDepartmentT>) departmentService.searchDepartment(depid, "");
//				nameList = (List<PcCdDepartmentT>) departmentService.searchDepartment("", departmentname);
//			} catch (Exception e1) {
//				e1.printStackTrace();
//				outCode = "-11703";
//			}
//			boolean addflg = true;
//			User sessionuser = (User) session.getAttribute("userInfo");
//			if(nameList != null && nameList.size()>0){
//				if(!depid.equals(nameList.get(0).getDepartmentid())){
//					outCode = "-11707";
//				}
//			}
//			
//			if("1".equals(outCode) && depList != null && depList.size()>0){
//				dep = depList.get(0);
//				
//				dep.setDepartmentName(departmentname);
//				dep.setDepartmentHeader(StringUtil.toStr(request.getParameter("department_header").trim()));
//				dep.setDepartmentAddress(StringUtil.toStr(request.getParameter("department_address").trim()));
//				String phone = StringUtil.toStr(request.getParameter("department_phone").trim());
//				if(phone != null && !"".equals(phone)){
//					dep.setDepartmentPhone(Long.parseLong(phone));
//				}
//				dep.setRemark(StringUtil.toStr(request.getParameter("remark").trim()));
//				dep.setRlastOper(sessionuser.getOperName());
//				dep.setRlastOdate(new Date());
//				//PcCdOrgT org = new PcCdOrgT();
//				//org = dep.getPcCdOrgT();
//				//org.setStructurename(departmentname);
////				org.setPcCdDepartmentT(dep);
////				dep.setPcCdOrgT(org);
//				
//				try {
//					addflg = departmentService.updateDep(dep);
//				} catch (Exception e) {
//					e.printStackTrace();
//					outCode = "-11704";
//				}
//				
//			}
//			
//			
//			if("1".equals(outCode) && addflg == true){
//				//添加系统LOG
//				try {
//					PcRdLoginfoT log = CommonsUtil.getLogInfo(sessionuser, "3", "部门基础信息", dep.getDepartmentid());
//					logService.addLog(log);
//				} catch (Exception e) {
//					e.printStackTrace();
//					outCode = "-10002";
//				}
//			}
//			
//		}else{
//			outCode = "-11703";
//		}
//		out.print(outCode);
//		return null;
//	}

	@SuppressWarnings("unused")
	public String removeLX() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		String cell_id = StringUtil.toStr(request.getParameter("CELL_ID").trim());
		
		boolean deleteflg = true;
		JSONObject obj = new JSONObject();
		try {
			deleteflg = boilerlineService.removeLX(cell_id);
		} catch (Exception e) {
			String errmsg = e.getCause().getCause().toString();
			//ORA-02292: 违反完整约束条件 (NEWPORTAL.FK58_PC_RPD_WELL_SAGDD_T) - 已找到子记录
			if(!"".equals(errmsg) && errmsg.indexOf("ORA-02292") != -1){
				obj = CommonsUtil.getRrturnJson("","炉线基础信息删除失败-炉线日报中存在子记录" ,"", null, null);
			}else if(!"".equals(errmsg) && errmsg.indexOf("ORA-02292") != -1 ){
				obj = CommonsUtil.getRrturnJson("","炉线基础信息删除失败-炉线班报中存在子记录" , "",null, null);
			} else{
				obj = CommonsUtil.getRrturnJson("",errmsg ,"", null, null);
			}
			e.printStackTrace();
		}
		
		if("1".equals(outCode) && deleteflg == true){
			
			//添加系统LOG
			try {
				User user1 = (User) session.getAttribute("userInfo");
				PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "2", "炉线管理", cell_id);
//				logService.addLog(log);
			} catch (Exception e) {
				e.printStackTrace();
				obj = CommonsUtil.getRrturnJson("-10003", "","", null, null);
			}
			
			
		}
		
		out.print(obj);
		return null;
	}
	public String seachSelectData() throws Exception {
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONArray jsonArr = null;
		try {
			jsonArr = boilerlineService.seachSelectData();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(jsonArr != null){
			out.println(jsonArr);
		}else{
			out.println("");
		}
		return null;
	}

	@SuppressWarnings("unused")
	public String addOrUpdateBoilerLine() throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String boilerList = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("listbox1")));
		String wellList = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("listbox12")));
		String rowData = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("rowData")));
		PcCdBlCellT blCell = new PcCdBlCellT();
		PcCdBoilerCellT boilerCell = null;
		PcCdWellCellT wellCell = null;
		Date nowDate = new Date();
		User user = (User)session.getAttribute("userInfo");
		boolean operFlag = true;
		//请求的long参数
		String[] jsonArgs = {"CELL_ID","CELL_NAME","SPLIT_MODEL","VALID_DATE","OWNER_ORG","RLAST_OPER","RLAST_ODATE","REMARK"};
		String[] filedName = {"cellId","cellName","splitModel","validDate","ownerOrg","rlastOper","rlastOdate","remark"};
		JSONObject jsonObj = null;
		JSONArray jsonArr = JSONArray.fromObject(rowData);
		JSONArray boilerArr = null;
		boolean flg = false;
		Set<PcCdBoilerCellT> pcCdBoilerCellTs = null;
		Set<PcCdWellCellT> pcCdWellCellT = null;
		String outCode = "1";
		JSONObject objs = new JSONObject();
		StringBuffer jkidstr = new StringBuffer();
		StringBuffer glidstr = new StringBuffer();
		String cellId = null;
		for (Object jo : jsonArr) {
			jsonObj = JSONObject.fromObject(jo);
			if(jsonObj.get("CELL_ID") != null ){
				cellId = jsonObj.get("CELL_ID").toString();
			}
			
			String wellName = jsonObj.get("CELL_NAME").toString();
//			Date getDataDate = StringUtil.parseDate("yyyy-MM-dd",jsonObj.get("VALID_DATE").toString());
			List<PcCdBlCellT> getPcCdBlCellT = null;
			
			//更新记录
			if (!"".equals(cellId)) {
				getPcCdBlCellT = boilerlineService.searchBoilerLineByName("",wellName, jsonObj.get("VALID_DATE").toString());//更新时存在指定日期的 同名，同日期的记录 报错
				if(getPcCdBlCellT != null && getPcCdBlCellT.size()> 1){
					//炉线单元更新时存在指定日期的 同名，同日期的记录 报错
					outCode = "-17106";
				
				}else if (getPcCdBlCellT != null && getPcCdBlCellT.size() == 1) {
					if(!cellId.equals(getPcCdBlCellT.get(0).getCellId())){
						
						outCode = "-17106";
					}
				}else{
					getPcCdBlCellT = boilerlineService.searchBoilerLineByName(cellId,"",""); //获取要更新记录
				}
				
				blCell = getPcCdBlCellT.get(0);
				
				//添加新记录
			}else {
				try {
					getPcCdBlCellT = boilerlineService.searchBoilerLineByName("",wellName, jsonObj.get("VALID_DATE").toString());//添加时存在同一天 同井的记录 报错
					
				} catch (Exception e) {
					e.printStackTrace();
					//添加炉线单元在当日存在相同炉线名称
					outCode = "-17107";
					
				}
				if (getPcCdBlCellT != null && getPcCdBlCellT.size() > 0) {
					outCode = "-17107";
				}
			}
			
			
			for (int i = 0; i < jsonArgs.length; i++) {
				try {
					String argVal = null;
					if(jsonObj.get(jsonArgs[i].toString()) != null && !"".equals(jsonObj.get(jsonArgs[i].toString()))){
						argVal =  jsonObj.get(jsonArgs[i].toString()).toString();
					}else{
						PropertyUtil.setProperty(blCell, filedName[i], null);
						continue;
					}
					
					if (i == 3) {
						PropertyUtil.setProperty(blCell, filedName[i], StringUtil.parseDate("yyyy-MM-dd",argVal));
						continue;
					}else if(i == 5){
						continue;
					}else if(i == 6){
						continue;
					}
					PropertyUtil.setProperty(blCell, filedName[i], argVal);
					
					//如果长整型参数不为空 set值 
				} catch (Exception e) {
					e.printStackTrace();
					//炉线单元数据格式错误
					outCode = "-17108";
				}
				
			}
			
			blCell.setRlastOper(user.getOperName());
			blCell.setRlastOdate(new Date(System.currentTimeMillis()));
			if (boilerList != null && !"".equals(boilerList)&& !"null".equals(boilerList)) {
				pcCdBoilerCellTs = new HashSet<PcCdBoilerCellT>(0);
				boilerArr = JSONArray.fromObject(boilerList);
				for (Object obj : boilerArr) {
					boilerCell = new PcCdBoilerCellT();
					jsonObj = JSONObject.fromObject(obj);
					if (jsonObj.get("id") != null && !"".equals(StringUtil.isNullUtil(jsonObj.get("id").toString()))) {
						boilerCell.setBoilerId(jsonObj.get("id").toString());
						glidstr.append("'"+jsonObj.get("id").toString()+"',");
						if (jsonObj.get("noded") != null && !"".equals(StringUtil.isNullUtil(jsonObj.get("noded").toString()))) {
							boilerCell.setBoilerCellId(jsonObj.get("noded").toString());
						}
						
						boilerCell.setPcCdBlCellT(blCell);
						pcCdBoilerCellTs.add(boilerCell);
					}
					
				}
			}
			JSONArray wellArr = JSONArray.fromObject(wellList);
			if (wellList != null && !"".equals(wellList)&& !"null".equals(wellList)) {
				pcCdWellCellT = new HashSet<PcCdWellCellT>(0);
				boilerArr = JSONArray.fromObject(wellList);
				for (Object obj : wellArr) {
					wellCell =  new PcCdWellCellT();
					jsonObj = JSONObject.fromObject(obj);
					if(jsonObj != null && jsonObj.get("ORG_ID") != null && !"".equals(jsonObj.get("ORG_ID"))){
						wellCell.setOrgId(jsonObj.get("ORG_ID").toString());
						if(jsonObj.get("ORG_ID") != null && !"".equals(jsonObj.get("ORG_ID").toString())){
							jkidstr.append("'"+jsonObj.get("ORG_ID").toString()+"',");
						}
						
						if (!"".equals(StringUtil.isNullUtil(jsonObj.get("WELL_CELL_ID").toString()))) {
							wellCell.setWellCellId(jsonObj.get("WELL_CELL_ID").toString());
						}
						
						if (jsonObj.get("SPLIT_COEFFICIENT") != null && !"".equals(StringUtil.isNullUtil(jsonObj.get("SPLIT_COEFFICIENT").toString()))) {
							wellCell.setSplitCoefficient(java.math.BigDecimal.valueOf(Double.parseDouble(jsonObj.get("SPLIT_COEFFICIENT").toString())) );
						}else{
							wellCell.setSplitCoefficient(null);
						}
						if (jsonObj.get("P_FLOWMETER") != null && !"".equals(StringUtil.isNullUtil(jsonObj.get("P_FLOWMETER").toString()))) {
//							if ("1号流量计".equals((String) jsonObj.get("P_FLOWMETER"))) {
//								wellCell.setPFlowmeter(1);
//							}
//							else if ("2号流量计".equals((String) jsonObj.get("P_FLOWMETER"))) {
//								wellCell.setPFlowmeter(2);
//							}
//							else if ("双流量计".equals((String) jsonObj.get("P_FLOWMETER"))) {
//								wellCell.setPFlowmeter(3);
//							}else {
								wellCell.setPFlowmeter(Integer.parseInt(jsonObj.get("P_FLOWMETER").toString()));
//							}
						}else{
							wellCell.setPFlowmeter(null);
						}
						
						if (jsonObj.get("I_FLOWMETER") != null && !"".equals(StringUtil.isNullUtil(jsonObj.get("I_FLOWMETER").toString()))) {
//							if ("1号流量计".equals((String) jsonObj.get("I_FLOWMETER"))) {
//								wellCell.setIFlowmeter(1);
//							}
//							else if ("2号流量计".equals((String) jsonObj.get("I_FLOWMETER"))) {
//								wellCell.setIFlowmeter(2);
//							}
//							else if ("双流量计".equals((String) jsonObj.get("I_FLOWMETER"))) {
//								wellCell.setIFlowmeter(3);
//							}else {
								wellCell.setIFlowmeter(Integer.parseInt(jsonObj.get("I_FLOWMETER").toString()));
//							}
						}else{
							wellCell.setIFlowmeter(null);
						}
						wellCell.setPcCdBlCellT(blCell);
						pcCdWellCellT.add(wellCell);
					}
				
				}
			}
			blCell.setPcCdBoilerCellTs(pcCdBoilerCellTs);
			blCell.setPcCdWellCellTs(pcCdWellCellT);
		}	
			if("1".equals(outCode)){
				if(cellId != null && !"".equals(cellId)){
					//更新
					boolean deleteflg = true;
					try {
						
						String jkid = "";
						String glid = "";
						if(jkidstr != null && jkidstr.length() >0){
							jkid = jkidstr.substring(0, jkidstr.length()-1).toString();
						}
						if(glidstr != null && glidstr.length() >0){
							glid = glidstr.substring(0, glidstr.length()-1).toString();
						}
						deleteflg = boilerlineService.removeAllWELLS(jkid, glid,cellId);
//						deleteflg = boilerlineService.removeLXchrid(cellId);
					} catch (Exception e) {
						e.printStackTrace();
						deleteflg = false;
						//炉线单元数据更新失败
						outCode = "-17109";
						
					}
					if(deleteflg){
						try {
							operFlag = boilerlineService.addOrUpdateBoilerLine(blCell);
						} catch (Exception e) {
							e.printStackTrace();
							outCode = "-17109";
							
						}
						
					}else{
						outCode = "-17109";
					}
					
				}else{
	//				添加
					try {
						operFlag = boilerlineService.addBoilerLine(blCell);
					} catch (Exception e) {
						e.printStackTrace();
						//炉线单元数据添加失败
						outCode = "-17110";
					}
					
				}
			}
			
			//TODO//	
//			//添加成功后删除 存在的井口及锅炉记录
//			if(operFlag){
//				Set<PcCdBoilerCellT> boilerCellTs = blCell.getPcCdBoilerCellTs();
//				Set<PcCdWellCellT> wellCellTs = blCell.getPcCdWellCellTs();
//				
//				List<String> boileridlist = new ArrayList<String>(); //添加的锅炉 
//				 List<String> boilerCellIdlist = new ArrayList<String>();//添加的锅炉炉线ID
//				List<String> wellidlist = new ArrayList<String>();//添加的井口 
//				List<String> wellCellIdlist = new ArrayList<String>();//添加的井口炉线ID 
//				
//				if(boilerCellTs != null && boilerCellTs.size()>0){
//					 Iterator it = boilerCellTs.iterator();
//					 while(it.hasNext()){
//						 PcCdBoilerCellT BoilerCell = (PcCdBoilerCellT)it.next();
////							sb.append(right.getRightid()+",");
//						 	boileridlist.add(BoilerCell.getBoilerId());
//						 	boilerCellIdlist.add(BoilerCell.getBoilerCellId());
//						 }
//				}
//				
//				if(wellCellTs != null && wellCellTs.size()>0){
//					 Iterator it = wellCellTs.iterator();
//					 while(it.hasNext()){
//						 PcCdWellCellT wellCells = (PcCdWellCellT)it.next();
////							sb.append(right.getRightid()+",");
//						 	wellidlist.add(wellCells.getOrgId());
//						 	wellCellIdlist.add(wellCells.getWellCellId());
//						 }
//				}
//				
//				
//				try {
//					flg = boilerlineService.removeSY(boileridlist, boilerCellIdlist, wellidlist, wellCellIdlist);
//				} catch (Exception e) {
//					e.printStackTrace();
//					objs = CommonsUtil.getRrturnJson("","炉线管理井口及锅炉当天在其他炉线信息中数据删除失败" , null, null);
//					
//				}
//			}
			
			
			if("1".equals(outCode) && flg == true){
				if(cellId != null && !"".equals(cellId)){
					//添加系统LOG
					try {
						User user1 = (User) session.getAttribute("userInfo");
						PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "3", "炉线管理", blCell.getCellId());
						logService.addLog(log);
					} catch (Exception e) {
						e.printStackTrace();
						outCode = "-10002";
					}
				}else{
					//添加系统LOG
					try {
						User user1 = (User) session.getAttribute("userInfo");
						PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "1", "炉线管理", blCell.getCellId());
						logService.addLog(log);
					} catch (Exception e) {
						e.printStackTrace();
						outCode = "-10001";
					}
					
				}
			}	
		if(!"1".equals(outCode)){
			objs = CommonsUtil.getRrturnJson(outCode, "","", null, null);
		}
		out.print(objs);
		return null;
	}
}