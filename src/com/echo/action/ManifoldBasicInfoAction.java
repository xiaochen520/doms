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
import com.echo.dto.PcCdBranchingT;
import com.echo.dto.PcCdManifoldT;
import com.echo.dto.PcCdOrgT;
import com.echo.dto.PcCdServerNodeT;
import com.echo.dto.PcCdStationT;
import com.echo.dto.PcRdLoginfoT;
import com.echo.dto.User;
import com.echo.service.BoilerBasicService;
import com.echo.service.BranchingBasicService;
import com.echo.service.CommonService;
import com.echo.service.LogService;
import com.echo.service.ManifoldBasicService;
import com.echo.service.SagdService;
import com.echo.service.StationTService;
import com.echo.util.AuthorityUtil;
import com.echo.util.CommonsUtil;
import com.echo.util.DateBean;
import com.echo.util.DynamicDataExportUtil;
import com.echo.util.PageVariableUtil;
import com.echo.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

public class ManifoldBasicInfoAction extends ActionSupport  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BoilerBasicService boilerBasicService;
	private ManifoldBasicService manifoldBasicService;
	private BranchingBasicService branchingBasicService;
	private StationTService stationTService;
	private CommonService commonService;
	private LogService logService;
	public StationTService getStationTService() {
		return stationTService;
	}
	public CommonService getCommonService() {
		return commonService;
	}

	private SagdService sagdService;
	private InputStream excelFile = null;

	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}
	public void setBranchingBasicService(BranchingBasicService branchingBasicService) {
		this.branchingBasicService = branchingBasicService;
	}

	
	public void setStationTService(StationTService stationTService) {
		this.stationTService = stationTService;
	}

	HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话

	public BoilerBasicService getBoilerBasicService() {
		return boilerBasicService;
	}

	public void setBoilerBasicService(BoilerBasicService boilerBasicService) {
		this.boilerBasicService = boilerBasicService;
	}

	public ManifoldBasicService getManifoldBasicService() {
		return manifoldBasicService;
	}

	public void setManifoldBasicService(ManifoldBasicService manifoldBasicService) {
		this.manifoldBasicService = manifoldBasicService;
	}
	public void setSagdService(SagdService sagdService) {
		this.sagdService = sagdService;
	}

	public InputStream getExcelFile() {
		return excelFile;
	}
	public String getFileName() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd ");
		String downloadFileName = (sf.format(new Date()).toString())+ "管汇点基础信息.xls";
		try {
			downloadFileName = new String(downloadFileName.getBytes(),"ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return downloadFileName;
	}
	@SuppressWarnings("unused")
	public String queryManifoldBasicInfo() throws IOException {
			HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
			HttpServletResponse response=ServletActionContext.getResponse();//响应对象
			response.setCharacterEncoding("utf-8");
			response.setHeader("ContentType","text/xml");
			PrintWriter out = response.getWriter();
			
			String stationNumber = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("oilationname")));
			String area = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("areablock")));
			String areablock = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("blockstationname")));
			String ghmc = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("boilersName")));
			String ghid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("ghname")));
			String jrbz1 =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("jrbz1")));
			String dyearC =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("dyearC")));
			String teamGROUP =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("teamGROUP1")));
			
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
				jsonobj = manifoldBasicService.queryManifoldBasicInfo(stationNumber, ghid, areablock,ghmc,jrbz1,dyearC,teamGROUP,pageNo,sort,order,rowsPerpage);
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
			jsonArr = manifoldBasicService.queryOilSationInfo();
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
	
	public String queryAreablockInfo() throws IOException {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String arg = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("orgid")));
		JSONArray jsonArr = null;
		
		try {
			jsonArr = manifoldBasicService.queryAreablockInfo(arg);
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
			jsonArr = manifoldBasicService.queryStationInfo(oilid,areaid,selecteValue);
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
	
	public String queryManifoldNameInfo() throws IOException {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String manifoldOrgId = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("orgid")));
		String ghorg = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("ghorg")));
		JSONArray jsonArr = null;
		try {
			jsonArr = manifoldBasicService.queryManifoldNameInfo(manifoldOrgId,ghorg);
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
			gridJson = AuthorityUtil.getGridJson("管汇点基础信息", user, PageVariableUtil.MANIFOLD_PAGE_GRID);
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
		List<PcCdManifoldT> manifoldlist = null;
		List<Object[]> lists = null;
		List<PcCdStationT> stationlist = null;
		JSONObject obj = new JSONObject();
		PcCdManifoldT manifold = null;
		PcCdBranchingT pcCdBranchingT = null;
		PcCdOrgT org = new PcCdOrgT();
		String outCode = "1";
		String manifoldid = "";
		String pid = "";
		boolean addflg = true;
		User user1 = (User) session.getAttribute("userInfo");
		
		if(request.getParameter("manifoldid") != null && !"".equals(request.getParameter("manifoldid"))){
			manifoldid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("manifoldid")));
		}
		String zh2 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("zh2")));
		String zh2name = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("zh2name")));
		String ghmc2 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("ghmc2")));
		String ghdm2 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("ghdm2")));
		if (zh2 == null || "".equals(zh2)) {
			List<Object[]> stationlist1 = manifoldBasicService.searchStationByName(zh2name);
			if (stationlist1 != null && stationlist1.size() > 0) {
				zh2 = stationlist1.get(0) + "";
			}
			else {
//			-11117	未识别注转站ID 请重新选择注转站	
				out.println("-11117");
				return null;
			}
		}
		//获取对应注转站下管汇是否存在
//
//			if(zh2 != null && !"".equals(zh2)){
//				if(ghmc2 != null && !"".equals(ghmc2)){
//					try {
//						lists = manifoldBasicService.searchManiCheck(zh2, ghmc2);
//					} catch (Exception e) {
//						e.printStackTrace();
//						//-11511	管汇信息获取失败
//						outCode = "-11511";
//					}
//					
//				}
//				pid = zh2; 
//			}else{
//				//-11117	未识别注转站ID 请重新选择注转站	
//				outCode = "-11117";
//				
//			}
//		
		//if("1".equals(outCode)){
			//修改
	//		if(!"".equals(manifoldid)){
//				if(lists != null && lists.size()>0){
//					String org_ids = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("org_ids")));
//					if(!org_ids.equals(lists.get(0)[0].toString())){
//						//-11516	注转站下已存在该管汇名称，请使用其他管汇名称
//						outCode = "-11516";
//					}
//					
//				}
		//修改		
				if(manifoldid !=null && !"".equals(manifoldid)){
					try {
						manifoldlist = manifoldBasicService.searchManiByid("",ghmc2);
					} catch (Exception e) {
						e.printStackTrace();
						//-11511	管汇信息获取失败
						outCode = "-11511";
					}
					
					if(manifoldlist != null && manifoldlist.size()>0){
						if(manifoldlist.size()==1){
							//要修改为本身数据可以修改
							if(manifoldid.equals(manifoldlist.get(0).getManifoldid())){
								manifold = manifoldlist.get(0);
							}else{
								//管汇名称已存在，不许修改
								outCode="-11519";
							}
							
							
						}else if(manifoldlist.size() > 1){
						//管汇名称已存在，不许修改
							outCode="-11519";
						}
						
					}else{
						try {
							manifoldlist = manifoldBasicService.searchManiByid(manifoldid,"");
						} catch (Exception e) {
							e.printStackTrace();
							//-11511	管汇信息获取失败
							outCode = "-11511";
						}
						
						if(manifoldlist != null && manifoldlist.size()>0){
							manifold = manifoldlist.get(0);
						}else{
							manifold = new PcCdManifoldT();
							manifold.setManifoldid(manifoldid);
						}
						
						//manifold.setGhmc(ghmc2);
					}
					
					
			//添加
			}else{
				try {
					manifoldlist = manifoldBasicService.searchManiByid(manifoldid,ghmc2);
				} catch (Exception e) {
					e.printStackTrace();
					//-11511	管汇信息获取失败
					outCode = "-11511";
				}
				if(manifoldlist != null && manifoldlist.size()>0){
					//管汇名称已存在，请使用其他名称
					outCode="-11518";
				}else{
				manifold = new PcCdManifoldT();
				
				}
			}

		
		if("1".equals(outCode)){
			String branchingn = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("branchingn")));
			if(branchingn != null && !"".equals(branchingn)){
				List<PcCdBranchingT> branlist = null;
				try {
					branlist = branchingBasicService.searchBranchs(branchingn, "");
				} catch (Exception e) {
					e.printStackTrace();
					//-11601  分线信息获取错误
					outCode = "-11601";
				}
				
				if(branlist != null && branlist.size()>0){
					pcCdBranchingT = branlist.get(0);
				}else{
					//-11607  分线ID未识别，请重新选择分线信息
					outCode = "-11607";
				}
				
			}
			
		}
		
		
		if("1".equals(outCode)){
			
//			if(ghdm2!=null && !"".equals(ghdm2)){
//				manifoldlist =  manifoldBasicService.searchManiBydm(ghdm2,zh2);
//				if(manifoldlist == null){
//					obj = CommonsUtil.getRrturnJson("-11220", "管汇代码已存在","", null, null);
//					out.print(obj);
//					return null;
//				}
//			}
		
			String qkid = manifoldBasicService.searchQUKUAI(zh2);
//			if(qkid == null && qkid.equals("")){
//				//-11117	未识别注转站ID 请重新选择注转站	
//				out.println("-11117");
//				return null;
//			}
			
			
			List<Object[]> qdjArr = new ArrayList<Object[]>();
			qdjArr = manifoldBasicService.searchQKID(qkid,ghdm2);
			if(manifoldid !=null && !manifoldid.equals("")){
				
				
				if(qdjArr !=null && qdjArr.size()>0){
					if(qdjArr.size()==1){
						Object[] ghqk = qdjArr.get(0);
						if(!manifoldid.equals(String.valueOf(ghqk[0]))){
						//if(qdjArr.equals(ghqk)){
							obj = CommonsUtil.getRrturnJson("-11220", "管汇代码已存在","", null, null);
							out.print(obj);
							return null;
						}
					}else if(qdjArr.size()>1){
						obj = CommonsUtil.getRrturnJson("-11220", "管汇代码已存在","", null, null);
						out.print(obj);
						return null;
					}
				}
				
				//manifoldlist = manifoldBasicService.searchManiBydm(ghdm2,zh2);
			
			}else{
				if(qdjArr !=null && qdjArr.size()>0){
					obj = CommonsUtil.getRrturnJson("-11220", "管汇代码已存在","", null, null);
					out.print(obj);
					return null;
				}
		}
			
			//修改		
			if(manifoldid !=null && !"".equals(manifoldid)){
				if(manifold.getPcCdOrgT()!= null){
					org = manifold.getPcCdOrgT();
				}else{
					//-11511	管汇信息获取失败
					outCode = "-11511";
				}
			}
			manifold.setGhmc(ghmc2);
			manifold.setPcCdBranchingT(pcCdBranchingT);
		
			manifold.setGhdm(ghdm2);
			String dtfs2 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("dtfs2")));
			if(dtfs2 != null && !"".equals(dtfs2)){
				manifold.setDtfs(Byte.parseByte(dtfs2));
			}
			String dtfmc1s = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("dtfmc1s")));
			manifold.setDtfmc1(dtfmc1s);
			String dtftds1s = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("dtftds1s")));
			if(dtftds1s != null && !"".equals(dtftds1s)){
				manifold.setDtftds1(Byte.parseByte(dtftds1s));
				}else{
					manifold.setDtftds1(null);
				}
			String dtfmc2s = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("dtfmc2s")));
			manifold.setDtfmc2(dtfmc2s);
			String dtftds2s = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("dtftds2s")));
			if(dtftds2s != null && !"".equals(dtftds2s)){
				manifold.setDtftds2(Byte.parseByte(dtftds2s));
				}else{
					manifold.setDtftds2(null);
				}
			
			String jrbz = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("jrbz")));
			if(jrbz != null && !"".equals(jrbz)){
				org.setSwitchInFlag(jrbz);
			}
			String remark2 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("remark2")));
			String teamG = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("teamG")));
			manifold.setTeamId(teamG);
			manifold.setRemark(remark2);
			String pid2  = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("pid2")));
			if(pid2!=null && !"".equals(pid2)){
				manifold.setPid2(pid2);
			}else{
				//-11117	未识别注转站ID 请重新选择注转站	
				obj = CommonsUtil.getRrturnJson("-11117", "未识别计量注转站ID 请重新选择计量注转站","", null, null);
				out.print(obj);
				return null;
			}
			String dyear =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("dyear")));
			manifold.setDyear(dyear);
			//计量器状态  新添加
			String INSTRU_STVA = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("INSTRU_STVA")));
			if( INSTRU_STVA !=null  && INSTRU_STVA.equals("正常")){
				manifold.setInstruStva(java.math.BigDecimal.valueOf(0));
			}else if(INSTRU_STVA !=null  && INSTRU_STVA.equals("不正常")){
				manifold.setInstruStva(java.math.BigDecimal.valueOf(1));
			}else{
				manifold.setInstruStva(null);
			}
			
			String  code  = StringUtil.toStr(request.getParameter("code").trim());
			org.setCode(code);
//			String statusvalues = "";
//			try {
				String status_value = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("status_value")));
//				if(status_value != null && !"".equals(status_value)){
//					statusvalues = commonService.getStatusValueINT(status_value);
//				}
				
//			} catch (Exception e) {
//				e.printStackTrace();
//				//-10010	建设状态获取失败
//				outCode = "-10010";
//				
//			}
				
				
				String commissioningDate = StringUtil.toStr(request.getParameter("commissioning_date").trim());
				if(commissioningDate != null && !"".equals(commissioningDate)){
					org.setCommissioningDate(DateBean.getStrDate(commissioningDate));
				}
				PcCdServerNodeT ser = new PcCdServerNodeT(); //服务器逻辑表
				List<PcCdServerNodeT> serverlist = null;
				String scadaNode = StringUtil.toStr(request.getParameter("ljjd_s").trim());
				try {
					serverlist = sagdService.getServerNode(scadaNode);
				} catch (Exception e) {
					e.printStackTrace();
					//-10208 服务器逻辑节点信息获取失败
					outCode = "-10208";
				}
				//serverlist.get(0);
				if(serverlist != null && serverlist.size()>0){
					ser = serverlist.get(0);
				}else{
					//-10208 服务器逻辑节点信息获取失败
					outCode = "-10208";
				}
				//rule.setPcCdServerNodeT(ser);
				org.setScadaNode(scadaNode);
			manifold.setRlastOper(user1.getOperName());
			manifold.setRlastOdate(new Date());
			org.setStructurename(ghmc2);
			org.setStructuretype((byte) 8);
			org.setPid(zh2);
			org.setStatusValue(status_value);
			org.setRemark(remark2);
			org.setPcCdManifoldT(manifold);
			manifold.setPcCdOrgT(org);
		}
		
		if("1".equals(outCode)){
			if("1".equals(outCode)){
				//修改
				if(manifoldid != null && !"".equals(manifoldid)){
					try {
						addflg = manifoldBasicService.updateManifold(manifold);
					} catch (Exception e) {
						e.printStackTrace();
						
						//-11513 管汇信息修改失败
						outCode = "-11513";
					}
				}else{
					try {
						addflg = manifoldBasicService.addManifold(manifold);
					} catch (Exception e) {
						e.printStackTrace();
						//-11512 管汇信息添加失败
						outCode = "-11512";
					}
				}
			}
		}
		
		if("1".equals(outCode) && addflg == true){
			if(manifoldid != null && !"".equals(manifoldid)){
				//添加系统LOG
				try {
					PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "3", "管汇点基础信息",manifold.getManifoldid());
					logService.addLog(log);
				} catch (Exception e) {
					e.printStackTrace();
					outCode = "-10002";
				}
			}else{
				//添加系统LOG
				try {
					PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "1", "管汇点基础信息", manifold.getManifoldid());
					logService.addLog(log);
				} catch (Exception e) {
					e.printStackTrace();
					outCode = "-10001";
				}
				
			}
		
		}	

		out.println(outCode);
		return null;
	}

	
	@SuppressWarnings("unused")
	public String removeManifoldBasicInfo() throws IOException {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String manifoldid = StringUtil.toStr(request.getParameter("manifoldid"));
		String orgid = StringUtil.toStr(request.getParameter("org_id"));
		String outCode = "1";
		boolean deleteflg = false;
		try {
			deleteflg = manifoldBasicService.removeManifoldBasicInfo(manifoldid , orgid);
		} catch (Exception e) {
			//this.addFieldError("errermsg", "删除锅炉信息失败~");
			e.printStackTrace();
		}
		if(deleteflg){
			out.print("1");
			//添加系统LOG
		}else{
			out.print("0");
		}
		return null;
	}
	
	public String cascadeInit() throws IOException {
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONObject jsonArr = null;
		try {
			jsonArr = manifoldBasicService.cascadeInit();
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
	@SuppressWarnings("unchecked")
	public  String onExport()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String oilationname = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("oilationname")));
		String blockstationname = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("blockstationname")));
		//管汇
		String boilersName = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("boilersName")));
		String jrbz1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("jrbz1")));
		String totalNum = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("totalNum")));
		String dyearC = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("dyearC")));
		
		if("".equals(oilationname)  && "".equals(blockstationname) 
				 && "".equals(boilersName) && "".equals(boilersName) && "".equals(totalNum)){
			out.println("");
			return null;
		}
	
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
			dataMap = manifoldBasicService.searchOnExport(oilationname,blockstationname,boilersName,jrbz1,pageNo,sort,order,rowsPerpage,dyearC,totalNum);
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
			java.io.ByteArrayOutputStream baos = DynamicDataExportUtil.basicDataExporReport(dataLsit,templetFilePath,"管汇点基础信息");
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
	public String queryTeamGInfo() throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
			response.setCharacterEncoding("utf-8");
			response.setHeader("ContentType","text/xml");
			PrintWriter out = response.getWriter();
			JSONArray jsonArr = null;
			String group = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("group")));
			try {
				jsonArr = manifoldBasicService.queryTeamGInfo( group );
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
}
