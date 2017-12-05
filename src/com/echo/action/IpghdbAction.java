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

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.echo.dto.PcCdIpSegmentT;
import com.echo.dto.PcCdOrgT;
import com.echo.dto.PcRdLoginfoT;
import com.echo.dto.User;
import com.echo.service.CommonService;
import com.echo.service.IpghdbService;
import com.echo.service.LogService;
import com.echo.util.AuthorityUtil;
import com.echo.util.CommonsUtil;
import com.echo.util.DynamicDataExportUtil;
import com.echo.util.PageVariableUtil;
import com.echo.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

public class IpghdbAction extends ActionSupport {
	private static final long serialVersionUID = 12L;
	HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
	private IpghdbService ipghdbService;
	private InputStream excelFile = null;
	private CommonService commonService;
	

	private LogService logService;

	public void setIpghdbService(IpghdbService ipghdbService) {
		this.ipghdbService = ipghdbService;
	}


	public IpghdbService getIpghdbService() {
		return ipghdbService;
	}


	public LogService getLogService() {
		return logService;
	}

	
	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	public void setExcelFile(InputStream excelFile) {
		this.excelFile = excelFile;
	}


	public InputStream getExcelFile() {
		return excelFile;
	}
	public String getFileName() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd ");
		String downloadFileName = (sf.format(new Date()).toString())+ "IP规划大表.xls";
		try {
			downloadFileName = new String(downloadFileName.getBytes(),"ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return downloadFileName;
	}

	public String searchIpghdb() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String totalNum = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("totalNum")));
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
		HashMap<String,Object> dataMap = null;
		try {
			dataMap = ipghdbService.queryIpghdb(totalNum,pageNo,sort,order,rowsPerpage);
		} catch (Exception e) {
			outCode = "-17101";
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
			String templetFilePath = request.getSession().getServletContext().getRealPath("/") + "exceltemplet\\自动化专网IP规划建表信息整理.xls";
//			将用户对象列表输出到workbook的流中，ExcelUtil类进行了封装，后续分析！
			java.io.ByteArrayOutputStream baos = DynamicDataExportUtil.ipExporReport(dataLsit,templetFilePath,"IP规划大表");
			if(baos != null){
				byte[] ba = baos.toByteArray();
				setExcelFile(new ByteArrayInputStream(ba));
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
			gridJson = AuthorityUtil.getGridJson1("MENU120", user, PageVariableUtil.IPGHDB_PAGE_GRID);
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
	
	public String addIpghdb()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		boolean  addflg = true;
		PcCdIpSegmentT ipSeg = null;
		List<PcCdIpSegmentT> ipSegList = null;
		String segment_code2 = StringUtil.toStr(request.getParameter("segment_code2").trim());
		try {
			ipSegList = ipghdbService.searchIpSegByCode2(segment_code2);
		} catch (Exception e) {
			e.printStackTrace();
			outCode="-11310";
		}
		if(ipSegList !=null && ipSegList.size()>0){
			outCode="-11311";
		}else{
			
			User sessionuser = (User) session.getAttribute("userInfo");
			ipSeg= new PcCdIpSegmentT();
			ipSeg.setSegmentCode1(StringUtil.toStr(request.getParameter("segment_code1").trim()));
			ipSeg.setSegmentCode2(segment_code2);
			ipSeg.setSegmentName(StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("segment_name"))));
			ipSeg.setIpSegment(StringUtil.toStr(request.getParameter("ip_segment").trim()));
			ipSeg.setIpStart(Short.parseShort(StringUtil.toStr(request.getParameter("ip_start").trim())));
			ipSeg.setIpEnd(Short.parseShort(StringUtil.toStr(request.getParameter("ip_end").trim())));
			ipSeg.setIsUsed(Byte.parseByte(StringUtil.toStr(request.getParameter("is_used").trim())));
			ipSeg.setExplanation(StringUtil.toStr(request.getParameter("explanation").trim()));
			ipSeg.setRemark(StringUtil.toStr(request.getParameter("remark").trim()));
			ipSeg.setRlastOper(sessionuser.getOperName());
			ipSeg.setRlastOdate(new Date());
			
			try {
				addflg = ipghdbService.addIpSeg(ipSeg);
			} catch (Exception e) {
				e.printStackTrace();
				outCode="-11312";
			}
		}
		out.print(outCode);
		
		return null;
	}
	
	public  String  updateIpghdb() throws IOException{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		
		List<PcCdIpSegmentT> ipSegList = null;
		PcCdIpSegmentT  ipSeg = null;

		String segment_id = request.getParameter("segment_id").trim();
		String segment_code2 = StringUtil.toStr(request.getParameter("segment_code2").trim());
		if(segment_id !=null && !"".equals(segment_code2)){
			try {
				ipSegList = ipghdbService.serachIpSegById("" ,segment_code2);
			} catch (Exception e) {
				e.printStackTrace();
				outCode="-11310";
			}
			if(ipSegList != null && ipSegList.size()==1){
				if(!segment_id.equals(ipSegList.get(0).getSegmentId())){
					outCode = "-11311";
				}
			}else if (ipSegList != null && ipSegList.size()>1){
					//采油队数据库名称已存在不能修改
					outCode = "11311";
				}else{
					try {
						ipSegList = new ArrayList<PcCdIpSegmentT>();
						ipSegList  = ipghdbService.serachIpSegById(segment_id,"");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
					boolean addflg = true;
					User sessionuser = (User) session.getAttribute("userInfo");
					if(ipSegList !=null && ipSegList.size()>0){
						ipSeg = ipSegList.get(0);
						ipSeg.setSegmentCode1(StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("segment_code1"))));
						ipSeg.setSegmentCode2(StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("segment_code2"))));
						ipSeg.setSegmentName(StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("segment_name"))));
						ipSeg.setIpSegment(StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("ip_segment"))));
						ipSeg.setIpStart(Short.parseShort(StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("ip_start")))));
						ipSeg.setIpEnd(Short.parseShort(StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("ip_end")))));
						ipSeg.setIsUsed(Byte.parseByte(StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("is_used")))));
						ipSeg.setExplanation(StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("explanation"))));
						ipSeg.setRemark(StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("remark"))));
						ipSeg.setRlastOper(sessionuser.getOperName());
						ipSeg.setRlastOdate(new Date());
						PcCdOrgT org = null;
					}
					try {
						addflg = ipghdbService.updateIpSeg(ipSeg);
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
	
	
	public String removeIpghdb() throws IOException {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		String segment_id = StringUtil.toStr(request.getParameter("segment_id"));
		boolean deleteflg = false;
		JSONObject obj = new JSONObject();
		try {
			deleteflg = ipghdbService.removeIpSeg(segment_id);
		} catch (Exception e) {
			String errmsg = e.getCause().toString();
			e.printStackTrace();
		}
		
		if(deleteflg){
			
			//添加系统LOG
			try {
				User user1 = (User) session.getAttribute("userInfo");
				PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "2", "IP规划大表", segment_id);
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
