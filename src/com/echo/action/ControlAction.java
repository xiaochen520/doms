package com.echo.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;
import com.echo.dto.PcCdInstruMentationT;
import com.echo.dto.PcRdLoginfoT;
import com.echo.dto.User;
import com.echo.dto.PcCdControllerT;
import com.echo.service.ControllerService;
import com.echo.service.LogService;
import com.echo.util.AuthorityUtil;
import com.echo.util.CommonsUtil;
import com.echo.util.PageVariableUtil;
import com.echo.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;
public class ControlAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ControllerService controllerService;
	
	private LogService logService;

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
	
	public ControllerService getControllerService() {
		return controllerService;
	}
	public void setControllerService(ControllerService controllerService) {
		this.controllerService = controllerService;
	}
	
	public String searchControl() throws IOException {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		//采油站 联合站
		String oilstationname = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("oilstationname")));
		//注转站 注水橇 处理站 运行组
		String blockstationname = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("blockstationname")));
		//井
		String wellnum = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("wellnum")));
		//锅炉
		String boiler = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("boiler")));
		String instrutype = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("instrutype")));
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
//		if("".equals(oilstationname) && "".equals(blockstationname)  && "".equals(wellnum) && "".equals(boiler) && "".equals(instrutype)){
//			out.println("");
//			return null;
//		}
//		if (!"".equals(blockstationname)) {//注转站、注水撬
//			oilstationname = blockstationname;
//		}
//		if (!"".equals(wellnum)) {//井
//			oilstationname = wellnum;
//		}
//		if (!"".equals(boiler)) {//锅炉
//			oilstationname = boiler;
//		}
		JSONObject jsonobj = null;
		try {
			jsonobj = controllerService.searchControl(oilstationname,blockstationname,wellnum,boiler,instrutype,pageNo,sort,order,rowsPerpage);
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
			//仪表设备信息
			gridJson = AuthorityUtil.getGridJson("控制器通信信息", user, PageVariableUtil.CONTROLLER_PAGE_GRID);
		} catch (Exception e) {
			e.printStackTrace();
			this.addFieldError("errermsg", "系统菜单无法初始化~ 请联系管理员");
			return "fail";
		}
		out.print(gridJson);
		return null;
	}
	public String cascadeInit() throws IOException {
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONObject jsonArr = null;
		try {
			jsonArr = controllerService.cascadeInit();
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
	
	public String searchCommunicationType()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		//驱动类型    111
		String commtype = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("communication_type")));
		JSONArray jsonArr = null;
		try {
			jsonArr = controllerService.searchCommtype(commtype);
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
	public String searchIpadd()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		 //IP地址    222
		String ipadd = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("ip_address")));
		JSONArray jsonArr = null;
		try {
			jsonArr = controllerService.searchIpadd(ipadd);
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
	public String searchStationno()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		 //通讯站号  333
		String stationno = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("station_no")));
		JSONArray jsonArr = null;
		try {
			jsonArr = controllerService.searchStationno(stationno);
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
	public String searchComPort()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		 //通讯端口 444
		String comport = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("communication_port")));
		JSONArray jsonArr = null;
		try {
			jsonArr = controllerService.searchComport(comport);
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
	public String searchEquipstatus()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		 //设备状态 555
		String equipstatus = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("equipment_status")));
		JSONArray jsonArr = null;
		try {
			jsonArr = controllerService.searchEquipstatus(equipstatus);
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
	
	public String removeControl() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		String controllerId = StringUtil.toStr(request.getParameter("controllerid"));
		//String orgId = StringUtil.toStr(request.getParameter("org_id"));
		boolean deleteflg = false;
		try {
			deleteflg = controllerService.removeController(controllerId);
		} catch (Exception e) {
			e.printStackTrace();
			outCode = "-17105";
		}
		
		if(deleteflg){
			
			//添加系统LOG
			try {
				User user1 = (User) session.getAttribute("userInfo");
				PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "2", "控制器基础信息", controllerId);
				logService.addLog(log);
			} catch (Exception e) {
				e.printStackTrace();
				outCode = "-10003";
			}
			
			
		}
		out.print(outCode);
		return null;
	}
	
	@SuppressWarnings("unused")
	public String SaveOrUpdateControl()throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		User sessionuser = (User) session.getAttribute("userInfo");
		List<PcCdControllerT>  ConList = null;
		PcCdControllerT  con = null;
		PcCdInstruMentationT  im = null;
		List<PcCdInstruMentationT> ImList = null;
		im = new PcCdInstruMentationT();
		JSONObject obj = new JSONObject();
		String  controllerid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("controllerid")));
		String facility_name  =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("ybName")));
		
		String  instrumentationid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("instrumentationid")));


		if(controllerid !=null && !"".equals(controllerid)){
			//修改
			try {
				ConList = controllerService.searchControllerId(controllerid,"","");
				con = ConList.get(0);
			} catch (Exception e) {
				e.printStackTrace();
			}
//			if(instrumentationid !=null && !"".equals(instrumentationid)){
//				try {
//					ConList = controllerService.searchControllerId("",instrumentationid,facility_name);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//				
//				if(ConList !=null && ConList.size()>0){
//					if(ConList.size()==1){
//						if(instrumentationid.equals(ConList.get(0).getInstrumentationid()) && controllerid.equals(ConList.get(0).getControllerid())){
//							con= ConList.get(0);
//						}else{
//							outCode="-16299";
//						}
//					}else if(ConList.size()>1){
//						//此控制器已有相同的仪表设备
//						outCode="-16299";
//					}
//				}else{
//					con.setInstrumentationid(instrumentationid);
//				}
//			}
		}else{
			//添加
			
			if(instrumentationid !=null && !"".equals(instrumentationid)){
				try {
					ConList = controllerService.searchControllerId("",instrumentationid,"");
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(ConList !=null && ConList.size()>0){
						//此控制器已有相同的仪表设备
					obj = CommonsUtil.getRrturnJson("","此控制器已有相同的仪表设备！" ,"", null, null);
					out.print(obj);
					return null;
				}
			}
			con = new PcCdControllerT();
			con.setInstrumentationid(instrumentationid);
		}
	
		
	if("1".equals(outCode)){
		con.setFacilityName(facility_name);
		String  communication_type = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("communication_type")));
			con.setCommunicationType(communication_type);
		String  station_no = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("station_no")));
			con.setStationNo(station_no);
		String communication_port = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("communication_port")));
			con.setCommunicationPort(communication_port);
		String instrustva = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("equipment_status")));
			con.setEquipmentStatus(instrustva);
		String  remark = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("remark")));
			con.setRemark(remark);
		String IP_ADDRESS =StringUtil.toStr(StringUtil.isNullStr(request.getParameter("IP_ADDRESS")));
			con.setIpAddress(IP_ADDRESS);
			con.setRlastOdate(new Date());
			con.setRlastOper(sessionuser.getOperName());
	
		boolean operFlag = true;
		try {
			operFlag = controllerService.SaveOrUpdateControl(con);
		} catch (Exception e) {
			
			e.printStackTrace();
			String  errmsg = e.getCause().getCause().toString();
			if(!"".equals(errmsg) && errmsg.indexOf("ORA-01438") != -1){
				//obj = CommonsUtil.getRrturnJson("","您输入信息超过数据库数据精度范围" , null, null);
				obj = CommonsUtil.getRrturnJson("","您输入信息超过数据库数据精度范围" ,"", null, null);
				out.print(obj);
				return null;
			} else if(!"".equals(errmsg) && errmsg.indexOf("ORA-02291") != -1){
				obj = CommonsUtil.getRrturnJson("","未找到父项关键字(在仪表设备表中没有您要添加的控制器信息，请先到仪表设备维护信息中添加)" ,"", null, null);
				out.print(obj);
				return null;
			}else{
				obj = CommonsUtil.getRrturnJson("",errmsg ,"", null, null);
			}
		}
	}
//		if("1".equals(outCode)){
//			 out.print(outCode);
//			return null;
//		}
		
	out.print(obj);
		return null;
	}
	public String searchYBNameQuery() throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		 //通讯站号  333
		String instrutype = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("instrutype")));
		JSONArray jsonArr = null;
		try {
			jsonArr = controllerService.searchYBNameQuery(instrutype);
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
	public String searchYBNameQueryJL() throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String boildid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("boilerid")));
		String data1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("data1")));
		JSONArray jsonArr = null;
		try {
			jsonArr = controllerService.searchYBNameQueryJL(boildid,data1);
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
	//查询
	public String queryOilSationInfo() throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONArray jsonArr  =null ;
		String  arg = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("arg")));
		try {
			jsonArr = controllerService.queryConOilSationInfo(arg);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if(jsonArr !=null){
			out.print( jsonArr);
		}else {
			out.print("");
		}
		return null;
	}
	public  String queryInjectionTopryInfo() throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONArray jsonArr  =null ;
		String  arg = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("arg")));
		try {
			jsonArr = controllerService.queryConInjectionTopryInfoAdd(arg);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if(jsonArr !=null){
			out.print( jsonArr);
		}else {
			out.print("");
		}
		return null;
	}
	public String queryWellInfo() throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONArray jsonArr  =null ;
		String  arg = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("arg")));
		try {
			jsonArr = controllerService.queryConWellInfo(arg);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if(jsonArr !=null){
			out.print( jsonArr);
		}else {
			out.print("");
		}
		return null;
	}
	public String queryBoilersNameInfo() throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONArray jsonArr  =null ;
		String arg = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("arg")));
		try {
			jsonArr = controllerService.queryConBoilersNameInfo(arg);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if(jsonArr !=null){
			out.print( jsonArr);
		}else {
			out.print("");
		}
				return null;
				}
}
