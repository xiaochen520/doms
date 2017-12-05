package com.echo.action;

import java.io.ByteArrayInputStream;
import java.io.IOException;
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

import com.echo.dto.PcCdNetworkMdT;
import com.echo.dto.PcCdOrgT;
import com.echo.dto.PcRdLoginfoT;
import com.echo.dto.User;
import com.echo.service.GwghjcxxService;
import com.echo.service.LogService;
import com.echo.util.AuthorityUtil;
import com.echo.util.CommonsUtil;
import com.echo.util.DateBean;
import com.echo.util.DynamicDataExportUtil;
import com.echo.util.PageVariableUtil;
import com.echo.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

public class GwghjcxxAction extends ActionSupport{
	private LogService logService;
	private GwghjcxxService gwghjcxxService;
	
	
	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	public void setGwghjcxxService(GwghjcxxService gwghjcxxService) {
		this.gwghjcxxService = gwghjcxxService;
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
		String downloadFileName = (sf.format(new Date()).toString())+ "管网管汇基础信息.xls";
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
			gridJson = AuthorityUtil.getGridJson1("MENU150", user, PageVariableUtil.GWGHJCXX_PAGE_GRID);
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
	
	public String searchAllData() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		
		String oilName = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("oilName")));
		String stationName = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("stationName")));
		String network = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("NETWORK")));
		String totalNum = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("totalNum")));

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
			dataMap = gwghjcxxService.searchData(oilName,stationName,network,pageNo,sort,order,rowsPerpage,totalNum);
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
			java.io.ByteArrayOutputStream baos = DynamicDataExportUtil.basicDataExporReport(dataLsit,templetFilePath,"管网管汇信息");
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
	
	//ligerComboBox
	public String cascadeInit()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONObject  obj = new JSONObject();
		try {
			obj = gwghjcxxService.searchCascadeInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(obj !=null && obj.size()>0){
			out.print(obj);
		}else{
			out.print("");
		}
		return  null;
	}
	
	public String searchOnChangeOil()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String oilname = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("oilname")));
		String stationname = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("stationname")));
		JSONObject  obj = new JSONObject();
		try {
			obj = gwghjcxxService.searchOnChangeOil(oilname,stationname);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(obj !=null && obj.size()>0){
			out.print(obj);
		}else{
			out.print("");
		}
		return  null;
	}
	
	public String searchStation()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONArray arr = new JSONArray();
		try {
			arr = gwghjcxxService.searchStation();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(arr !=null && arr.size()>0){
			out.print(arr);
		}else{
			out.print("");
		}
		return  null;
	}
	public  String saveorUpdateData()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONObject  obj = new JSONObject();
		
		User user = (User) session.getAttribute("userInfo");
		
		String  NETWORK_MD_ID = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("NETWORK_MD_ID")));
		String  ORG_ID = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("ORG_ID")));
	   	String  edit_STATION = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("edit_STATION")));
  	    String  edit_NETWORK = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("edit_NETWORK")));
  	    String  edit_CODE =   StringUtil.toStr(StringUtil.isNullStr(request.getParameter("edit_CODE")));
  	    String  edit_RTUCODE = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("edit_RTUCODE")));
  	    String  edit_JRBZ =   StringUtil.toStr(StringUtil.isNullStr(request.getParameter("edit_JRBZ"))); 
	    String  edit_FWQLJJD = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("edit_FWQLJJD")));
	    String  edit_INSTALLDATE  = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("edit_INSTALLDATE")));
	    String  edit_REMARK  	   = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("edit_REMARK")));
	    
	    if(edit_NETWORK ==null ||   "".equals(edit_NETWORK)){
	    	obj = CommonsUtil.getRrturnJson("","管汇名称不许为空" , "",null, null);
			out.print(obj);
			return null;
	    }
	    List<Object[]> checkList =null;
	    try {
	    	checkList = gwghjcxxService.searchCheckNot(edit_STATION);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    if(checkList ==null &&  checkList.size()<0){
	    	obj = CommonsUtil.getRrturnJson("","注转战在组织结构中不存在" , "",null, null);
			out.print(obj);
			return null;
	    }
	    List<PcCdNetworkMdT> netList = null;
	    PcCdNetworkMdT net = null;
	    PcCdOrgT org = null;
	    //修改
	    if(NETWORK_MD_ID !=null && !"".equals(NETWORK_MD_ID)){
	    	try {
	    		netList = gwghjcxxService.searchCheck("",edit_NETWORK);
			} catch (Exception e) {
				e.printStackTrace();
					obj = CommonsUtil.getRrturnJson("","管网管汇基础信息ID获取失败" , "",null, null);
					out.print(obj);
					return null;
			}
			if(netList !=null && netList.size()>0){
				if(netList.size()==1 && netList.get(0).getNetworkMdId().equals(NETWORK_MD_ID)){
					net = netList.get(0);
				}else{
					obj = CommonsUtil.getRrturnJson("","管汇名称"+edit_NETWORK+"已存在,请使用其他名称" , "",null, null);
					out.print(obj);
					return null;
				}
			}else{
				net = new  PcCdNetworkMdT();
			}
	    	
	    }else{
	    	//tianjia 
	    	netList = gwghjcxxService.searchCheck("",edit_NETWORK);
	    	if(netList !=null &&  netList.size()>0){
	    		obj = CommonsUtil.getRrturnJson("","管汇名称"+edit_NETWORK+"已存在,请使用其他名称" , "",null, null);
				out.print(obj);
				return null;
	    	}else{
	    		net = new  PcCdNetworkMdT();
	    	}
	    }
	    
	    if(NETWORK_MD_ID !=null && !"".equals(NETWORK_MD_ID)){
	    	 org = net.getPcCdOrgT();
	    }else{
	    	 org = new PcCdOrgT();
	    }
	    net.setPipeNetwork(edit_NETWORK);
	    net.setRtuCode(edit_RTUCODE);
	    net.setInstallDate(DateBean.getStrDate(edit_INSTALLDATE));
	    net.setRemark(edit_REMARK);
	    net.setRlastOper(user.getOperName());
	    net.setRlastOdate(new Date());
	    org.setPid(edit_STATION);
	    org.setStructuretype( (byte)21);
	    org.setStructurename(edit_NETWORK);
	    org.setSwitchInFlag(edit_JRBZ);
	    org.setScadaNode(edit_FWQLJJD);
	    org.setCode(edit_CODE);
	    net.setPcCdOrgT(org);
	    
	    boolean  flag = false;
	    
	    try {
			flag = gwghjcxxService.saveOrUpdate(net);
		} catch (Exception e) {
		e.printStackTrace();
		}
		//添加日志
		if(NETWORK_MD_ID !=null && !"".equals(NETWORK_MD_ID)){
	  			try {
					User user1 = (User) session.getAttribute("userInfo");
					PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "3", "管网管汇", net.getNetworkMdId());
					logService.addLog(log);
				} catch (Exception e) {
					e.printStackTrace();
					obj = CommonsUtil.getRrturnJson("", "管网管汇添加日志失败","", null, null);
	  				out.print(obj);
	  				return null;
				}
			}else{
				try {
					User user1 = (User) session.getAttribute("userInfo");
					PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "1", "管网管汇", net.getNetworkMdId());
					logService.addLog(log);
				} catch (Exception e) {
					e.printStackTrace();
					obj = CommonsUtil.getRrturnJson("", "管网管汇修改日志失败","", null, null);
	  				out.print(obj);
	  				return null;
				}
			}
		out.print(obj);
		return null;
	}
	
	public String removeData() throws IOException {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		String mdId = StringUtil.toStr(request.getParameter("NETWORK_MD_ID"));
		String orgId = StringUtil.toStr(request.getParameter("ORG_ID"));
		boolean deleteflg = false;
		JSONObject obj = new JSONObject();
		try {
			deleteflg = gwghjcxxService.removeData(mdId,orgId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(deleteflg){
			//添加系统LOG
			try {
				User user1 = (User) session.getAttribute("userInfo");
				PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "2", "管网管汇信息", mdId);
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
