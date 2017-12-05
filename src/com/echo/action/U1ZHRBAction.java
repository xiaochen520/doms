package com.echo.action;

import java.io.File;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.json.JSONObject;
import org.apache.poi.hssf.converter.ExcelToHtmlUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import com.echo.dto.PcRdLoginfoT;
import com.echo.dto.PcRpdCrudeTransitionT;
import com.echo.dto.PcRpdU1GeneralT;
import com.echo.dto.User;
import com.echo.service.LogService;
import com.echo.service.U1ZHRBService;
import com.echo.util.AuthorityUtil;
import com.echo.util.CommonsUtil;
import com.echo.util.DateBean;
import com.echo.util.PropertiesConfig;
import com.echo.util.ReportFormsBaseUitl;
import com.echo.util.StringUtil;
import com.echo.util.U2DataExportUtil;

public class U1ZHRBAction extends ReportFormsBaseUitl{
	
	private static final long serialVersionUID = 1L;
	HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
	// 水一区罐液位及流量报表
	private LogService logService;
	private U1ZHRBService u1ZHRBService;

	public void setU1ZHRBService(U1ZHRBService u1zhrbService) {
		u1ZHRBService = u1zhrbService;
	}

	public LogService getLogService() {
		return logService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	public String getFileName() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd ");
		String downloadFileName = (sf.format(new Date()).toString())+ "1#稠油联合处理站日生产运行简报.xls";
		try {
			downloadFileName = new String(downloadFileName.getBytes(),"ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return downloadFileName;
	}
	//根据权限进行页面初始化
	@SuppressWarnings("unused")
	public String pageInit()throws Exception{
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String outCode = "1";
		JSONObject obj = new JSONObject();
		//根据用户权限生成用户权限生成页面布局格式
		User user = (User) session.getAttribute("userInfo");
//		
		JSONObject butJson = null;
		try {
			butJson = AuthorityUtil.getButtonJson("MENU111", user);
		} catch (Exception e) {
			e.printStackTrace();
			obj = CommonsUtil.getRrturnJson("","页面初始化权限获取失败" ,"", null, null);
			out.print(obj);
			return null;
			
		}
		
		obj = CommonsUtil.getRrturnJson("","" ,"", butJson, null);
		out.print(obj);
		return null;
	}
	public String searchU1ZHRB() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest(); // 请求对象
		HttpServletResponse response = ServletActionContext.getResponse();// 响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType", "text/xml");
		PrintWriter out = response.getWriter();
		JSONObject jsonobj = new JSONObject();
		String txtDate = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("txtDate")));
		JSONObject obj = new JSONObject();
//		if (txtDate == null || "".equals(txtDate)) {
//			txtDate = DateBean.getSystemTime1();
//		}
		try {
			jsonobj = u1ZHRBService.searchU1ZHRB(txtDate);
		} catch (Exception e) {
			e.printStackTrace();
			obj = CommonsUtil.getRrturnJson("", "当前日期搜索失败请选择其他日期", "", null,null);
			out.print(obj);
			return null;
		}
		obj = CommonsUtil.getRrturnJson("", "", "", jsonobj, null);
		out.print(obj);
		return null;
	}
	
	@SuppressWarnings({ "unused" })
	public String updateU1ZHRB()throws Exception{	
		HttpServletRequest request = ServletActionContext.getRequest(); // 请求对象
		HttpServletResponse response = ServletActionContext.getResponse();// 响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType", "text/xml");
		PrintWriter out = response.getWriter();
	
		List<PcRpdU1GeneralT>  U1List =  null;
		PcRpdU1GeneralT u1 = new PcRpdU1GeneralT();
		String bz = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("bz")));
		String cyel = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("cyel")));
		String cyoul = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("cyoul")));
		String wsyl = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("wsyl")));
		String kcyl = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("kcyl")));
		String ccl = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("ccl")));
		String xll = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("xll")));
		String zxjyl = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("zxjyl")));	
		String zxnd = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("zxnd")));
		String cjgckfxyl = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("cjgckfxyl")));
		String cjgckfxynd = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("cjgckfxynd")));
		String cygckfxyl = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("cygckfxyl")));
		String cygckfxynd = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("cygckfxynd")));
		String z1xhyryyclj = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("z1xhyryyclj")));
		String z1xhyryycljnd = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("z1xhyryycljnd")));
		
		String z32nx = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("z32nx")));
		String z32bx = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("z32bx")));	
		String z181x = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("z181x")));	
		String z1311x = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("z1311x")));	
		String z1xhyry = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("z1xhyry")));
		String xyt = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("xyt")));
				
		String syzdtshyl = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("syzdtshyl")));
		String syzccl = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("syzccl")));	
		String syzcsl = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("syzcsl")));	
		String syzyl = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("syzyl")));	
		String syzcyl = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("syzcyl")));
		String syzyclj = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("syzyclj")));
		String syzzxprj = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("syzzxprj")));	
		String syzcshy = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("syzcshy")));
		String syzcyhs = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("syzcyhs")));
		String syzwshyl = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("syzwshyl")));
		String qfyl = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("qfyl")));
		String qfsl = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("qfsl")));	
		String qflyl = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("qflyl")));	
		String qfjsj = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("qfjsj")));	
		String qfznj = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("qfznj")));
		
		String s1lsl = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("s1lsl")));
		String s1fyq = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("s1fyq")));
		String s1glcs = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("s1glcs")));
		String s1hsl = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("s1hsl")));
		String s1wp = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("s1wp")));
		
		String s2lsl = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("s2lsl")));
		String s2qfjcll = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("s2qfjcll")));
		String s2glcs = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("s2glcs")));
		String s2wshsl = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("s2wshsl")));
		String s2jsjjyl = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("s2jsjjyl")));
		String s2jsjnd = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("s2jsjnd")));
		String s2xnjjyl = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("s2xnjjyl")));
		String s2xnjnd = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("s2xnjnd")));
				
		String s1hytcgjk = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("s1hytcgjk")));
		String s1xfwtcgjk = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("s1xfwtcgjk")));
		String s1hytcgck = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("s1hytcgck")));
		String s1xfwtcgck = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("s1xfwtcgck")));
		String s1hyfygck = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("s1hyfygck")));
		String s1xfwfygck = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("s1xfwfygck")));
		String s1hyyjck = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("s1hyyjck")));
		String s1xfwyjck = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("s1xfwyjck")));
		String s1hyejck = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("s1hyejck")));
		String s1xfwejck = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("s1xfwejck")));
				
		String cjg11hs = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("cjg11hs")));
		String cjg12hs = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("cjg12hs")));
		String tsbhs = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("tsbhs")));
		String gcs11hy = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("gcs11hy")));
		//String syzcshy = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("syzcshy")));
		//String syzcyhs = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("syzcyhs")));
				
		String s2hytcgjk = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("s2hytcgjk")));
		String s2xfwtcgjk = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("s2xfwtcgjk")));
		String s2hytcgck = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("s2hytcgck")));
		String s2xfwtcgck = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("s2xfwtcgck")));
		String s2hyqfjck = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("s2hyqfjck")));
		String s2xfwqfjck = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("s2xfwqfjck")));
		String s2hyyjck = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("s2hyyjck")));
		String s2xfwyjck = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("s2xfwyjck")));
		String s2hyejck = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("s2hyejck")));
		String s2xfwejck = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("s2xfwejck")));
				
		String jcjggcshy = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("jcjggcshy")));
		String jcjggcsxfw = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("jcjggcsxfw")));
		String wfchshy = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("wfchshy")));
		String wfchsxfw = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("wfchsxfw")));
				
		String bdczyw = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("bdczyw")));
		String c1yw = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("c1yw")));
		String c2yw = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("c2yw")));
		String wfcyw = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("wfcyw")));
		String dbczhssl = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("dbczhssl")));
		String t1xynl = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("t1xynl")));
		String t2xynl = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("t2xynl")));
		String cyzfyl = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("cyzfyl")));
		String jsj = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("jsj")));
		String znj = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("znj")));
		
		String gcs12hy = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("gcs12hy")));
		String syzhszhyl = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("syzhszhyl")));
		String bdcsy = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("bdcsy")));
		
		String id = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("id")));
		String rq = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("RQ")));
		//cyel	cyoul	wsyl	kcyl	ccl	xll	zxjyl		zxnd		
		//cjgckfxyl	cjgckfxynd	cygckfxyl	cygckfxynd	z1xhyryyclj	z1xhyryycljnd
		u1.setRpdU1GeneralId(id);
		u1.setRq(DateBean.getStrDate(rq));
		u1.setBZ(bz);
	    u1.setCyel(CommonsUtil.getBigDecimalData(cyel));
	    u1.setCyoul(CommonsUtil.isNullOrEmpty(cyoul));
	    u1.setWsyl(CommonsUtil.isNullOrEmpty(wsyl));
	    u1.setKcyl(CommonsUtil.isNullOrEmpty(kcyl));
	    u1.setCcl(CommonsUtil.isNullOrEmpty(ccl));
	    u1.setXll(CommonsUtil.isNullOrEmpty(xll));
	    u1.setZxjyl(CommonsUtil.isNullOrEmpty(zxjyl));
	    u1.setZxnd(CommonsUtil.isNullOrEmpty(zxnd));
	    u1.setCjgckfxyl(CommonsUtil.isNullOrEmpty(cjgckfxyl));
	    u1.setCjgckfxynd(CommonsUtil.isNullOrEmpty(cjgckfxynd));
	    u1.setCygckfxyl(CommonsUtil.isNullOrEmpty(cygckfxyl));
	    u1.setCygckfxynd(CommonsUtil.isNullOrEmpty(cygckfxynd));
	    u1.setZ1xhyryyclj(CommonsUtil.isNullOrEmpty(z1xhyryyclj));
	    u1.setZ1xhyryycljnd(CommonsUtil.isNullOrEmpty(z1xhyryycljnd));
	    //z32nx	z32bx	z181x	z1311x	z1xhyry	xyt
	    u1.setZ32nx(CommonsUtil.isNullOrEmpty(z32nx));
	    u1.setZ32bx(CommonsUtil.isNullOrEmpty(z32bx));
	    u1.setZ181x(CommonsUtil.isNullOrEmpty(z181x));
	    u1.setZ1311x(CommonsUtil.isNullOrEmpty(z1311x));
	    u1.setZ1xhyry(CommonsUtil.isNullOrEmpty(z1xhyry));
	    u1.setXyt(CommonsUtil.isNullOrEmpty(xyt));
	    
	   // syzdtshyl	syzccl	syzcsl	syzyl	syzcyl
	   // syzyclj	syzzxprj	syzcshy	syzcyhs	syzwshyl
	    u1.setSyzdtshyl(CommonsUtil.isNullOrEmpty(syzdtshyl));
	    u1.setSyzccl(CommonsUtil.isNullOrEmpty(syzccl));
	    u1.setSyzcsl(CommonsUtil.isNullOrEmpty(syzcsl));
	    u1.setSyzyl(CommonsUtil.isNullOrEmpty(syzyl));
	    u1.setSyzcyl(CommonsUtil.isNullOrEmpty(syzcyl));
	    u1.setSyzyclj(CommonsUtil.isNullOrEmpty(syzyclj));
	    u1.setSyzzxprj(CommonsUtil.isNullOrEmpty(syzzxprj));
	    u1.setSyzcshy(CommonsUtil.isNullOrEmpty(syzcshy));
	    u1.setSyzcyhs(CommonsUtil.isNullOrEmpty(syzcyhs));
	    u1.setSyzwshyl(CommonsUtil.isNullOrEmpty(syzwshyl));
	    
	    //qfyl	qfsl	qflyl	qfjsj	qfznj
	    u1.setQfyl(CommonsUtil.isNullOrEmpty(qfyl));
	    u1.setQfsl(CommonsUtil.isNullOrEmpty(qfsl));
	    u1.setQflyl(CommonsUtil.isNullOrEmpty(qflyl));
	    u1.setQfjsj(CommonsUtil.isNullOrEmpty(qfjsj));
	    u1.setQfznj(CommonsUtil.isNullOrEmpty(qfznj));
	    
	   // s1lsl	s1fyq	s1glcs	s1hsl	s1wp
	    u1.setS1lsl(CommonsUtil.isNullOrEmpty(s1lsl));
	    u1.setS1fyq(CommonsUtil.isNullOrEmpty(s1fyq));
	    u1.setS1glcs(CommonsUtil.isNullOrEmpty(s1glcs));
	    u1.setS1hsl(CommonsUtil.isNullOrEmpty(s1hsl));
	    u1.setS1wp(CommonsUtil.isNullOrEmpty(s1wp));
	    
	   // s2lsl	s2qfjcll	s2glcs	s2wshsl	s2jsjjyl	s2jsjnd	s2xnjjyl	s2xnjnd
	    u1.setS2lsl(CommonsUtil.isNullOrEmpty(s2lsl));
	    u1.setS2qfjcll(CommonsUtil.isNullOrEmpty(s2qfjcll));
	    u1.setS2glcs(CommonsUtil.isNullOrEmpty(s2glcs));
	    u1.setS2wshsl(CommonsUtil.isNullOrEmpty(s2wshsl));
	    u1.setS2jsjjyl(CommonsUtil.isNullOrEmpty(s2jsjjyl));
	    u1.setS2jsjnd(CommonsUtil.isNullOrEmpty(s2jsjnd));
	    u1.setS2xnjjyl(CommonsUtil.isNullOrEmpty(s2xnjjyl));
	    u1.setS2xnjnd(CommonsUtil.isNullOrEmpty(s2xnjnd));
	    
	    //s1hytcgjk	s1xfwtcgjk	s1hytcgck	s1xfwtcgck	s1hyfygck	s1xfwfygck
	   // s1hyyjck	s1xfwyjck	s1hyejck	s1xfwejck
	    u1.setS1hytcgjk(CommonsUtil.isNullOrEmpty(s1hytcgjk));
	    u1.setS1xfwtcgjk(CommonsUtil.isNullOrEmpty(s1xfwtcgjk));
	    u1.setS1hytcgck(CommonsUtil.isNullOrEmpty(s1hytcgck));
	    u1.setS1xfwtcgck(CommonsUtil.isNullOrEmpty(s1xfwtcgck));
	    u1.setS1hyfygck(CommonsUtil.isNullOrEmpty(s1hyfygck));
	    u1.setS1xfwfygck(CommonsUtil.isNullOrEmpty(s1xfwfygck));
	    u1.setS1hyyjck(CommonsUtil.isNullOrEmpty(s1hyyjck));
	    u1.setS1xfwyjck(CommonsUtil.isNullOrEmpty(s1xfwyjck));
	    u1.setS1hyejck(CommonsUtil.isNullOrEmpty(s1hyejck));	
	    u1.setS1xfwejck(CommonsUtil.isNullOrEmpty(s1xfwejck));
	    
	    //cjg11hs	cjg12hs	tsbhs	gcs11hy
	    u1.setCjg11hs(CommonsUtil.isNullOrEmpty(cjg11hs));
	    u1.setCjg12hs(CommonsUtil.isNullOrEmpty(cjg12hs));
	    u1.setTsbhs(CommonsUtil.isNullOrEmpty(tsbhs));
	    u1.setGcs11hy(CommonsUtil.isNullOrEmpty(gcs11hy));

	    //s2hytcgjk	s2xfwtcgjk	s2hytcgck	s2xfwtcgck	s2hyqfjck	
	    //s2xfwqfjck	s2hyyjck	s2xfwyjck	s2hyejck	s2xfwejck
	    u1.setS2hytcgjk(CommonsUtil.isNullOrEmpty(s2hytcgjk));
	    u1.setS2xfwtcgjk(CommonsUtil.isNullOrEmpty(s2xfwtcgjk));
	    u1.setS2hytcgck(CommonsUtil.isNullOrEmpty(s2hytcgck));
	    u1.setS2xfwtcgck(CommonsUtil.isNullOrEmpty(s2xfwtcgck));
	    u1.setS2hyqfjck(CommonsUtil.isNullOrEmpty(s2hyqfjck));	
	    u1.setS2xfwqfjck(CommonsUtil.isNullOrEmpty(s2xfwqfjck));	
	    u1.setS2hyyjck(CommonsUtil.isNullOrEmpty(s2hyyjck));	
	    u1.setS2xfwyjck(CommonsUtil.isNullOrEmpty(s2xfwyjck));
	    u1.setS2hyejck(CommonsUtil.isNullOrEmpty(s2hyejck));
	    u1.setS2xfwejck(CommonsUtil.isNullOrEmpty(s2xfwejck));
	    
	    //jcjggcshy	jcjggcsxfw	wfchshy	wfchsxfw
	    u1.setJcjggcshy(CommonsUtil.isNullOrEmpty(jcjggcshy));
	    u1.setJcjggcsxfw(CommonsUtil.isNullOrEmpty(jcjggcsxfw));
	    u1.setWfchshy(CommonsUtil.isNullOrEmpty(wfchshy));
	    u1.setWfchsxfw(CommonsUtil.isNullOrEmpty(wfchsxfw));
	    
	    //bdczyw	c1yw	c2yw	wfcyw	dbczhssl		
	    //t1xynl	t2xynl	cyzfyl	jsj	znj
	    u1.setBdczyw(CommonsUtil.isNullOrEmpty(bdczyw));
	    u1.setC1yw(CommonsUtil.isNullOrEmpty(c1yw));
	    u1.setC2yw(CommonsUtil.isNullOrEmpty(c2yw));
	    u1.setWfcyw(CommonsUtil.isNullOrEmpty(wfcyw));
	    u1.setDbczhssl(CommonsUtil.isNullOrEmpty(dbczhssl));
	    u1.setT1xynl(CommonsUtil.isNullOrEmpty(t1xynl));
	    u1.setT2xynl(CommonsUtil.isNullOrEmpty(t2xynl));
	    u1.setCyzfyl(CommonsUtil.isNullOrEmpty(cyzfyl));
	    u1.setJsj(CommonsUtil.isNullOrEmpty(jsj));
	    u1.setZnj(CommonsUtil.isNullOrEmpty(znj));
	    u1.setGcs12hy(CommonsUtil.isNullOrEmpty(gcs12hy));
	    u1.setSyzhszhyl(CommonsUtil.isNullOrEmpty(syzhszhyl));
	    u1.setBdcsy(CommonsUtil.isNullOrEmpty(bdcsy));
	    //↑ ↓一行
		PcRpdCrudeTransitionT  crude = null;
		List<PcRpdCrudeTransitionT> crudeList = null;
		String RPD_CRUDE_TRANSITION_ID = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("RPD_CRUDE_TRANSITION_ID")));
		String gh = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("gh")));
		String gyw = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("gyw")));
		String dyw = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("dyw")));
		String sm = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("sm")));
		String bm = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("bm")));
		String gw = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("gw")));
		String hs = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("hs")));
		String myl = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("myl")));
		String cyl = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("cyl")));
		String sl = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("sl")));	
		JSONObject  obj  = new JSONObject();
		
			if(RPD_CRUDE_TRANSITION_ID !=null && !"".equals(RPD_CRUDE_TRANSITION_ID)){
				try {
					crudeList = u1ZHRBService.searchCrude(RPD_CRUDE_TRANSITION_ID);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
			if(crudeList !=null && crudeList.size()>0){
				crude = crudeList.get(0);
			}else{
				crude = new PcRpdCrudeTransitionT();
			}
			crude.setRq(DateBean.getStrDate(rq));
			if(gh !=null && !"".equals(gh)){
				crude.setGh(gh);
			
		
			crude.setGyw(CommonsUtil.getBigDecimalData(gyw));
			crude.setDyw(CommonsUtil.getBigDecimalData(dyw));
			crude.setSm(CommonsUtil.getBigDecimalData(sm));
			crude.setBm(CommonsUtil.getBigDecimalData(bm));
			crude.setGw(CommonsUtil.getBigDecimalData(gw));
			crude.setHs(CommonsUtil.getBigDecimalData(hs));
			crude.setMyl(CommonsUtil.getBigDecimalData(myl));
			crude.setCyl(CommonsUtil.getBigDecimalData(cyl));
			crude.setSl(CommonsUtil.getBigDecimalData(sl));
			crude.setOrgId("239068FA3724460581219B7CD401B386");//一号站
			}
		   //↑ ↓ 二行
		
		PcRpdCrudeTransitionT  crude0 = null;
		List<PcRpdCrudeTransitionT> crudeList0 = null;
		String RPD_CRUDE_TRANSITION_ID0 = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("RPD_CRUDE_TRANSITION_ID0")));
		String gh0 = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("gh0")));
		String gyw0 = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("gyw0")));
		String dyw0 = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("dyw0")));
		String sm0 = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("sm0")));
		String bm0 = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("bm0")));
		String gw0 = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("gw0")));
		String hs0 = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("hs0")));
		String myl0 = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("myl0")));
		String cyl0 = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("cyl0")));
		String sl0 = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("sl0")));	
		
			if(RPD_CRUDE_TRANSITION_ID0 !=null && !"".equals(RPD_CRUDE_TRANSITION_ID0)){
				try {
					crudeList0 = u1ZHRBService.searchCrude(RPD_CRUDE_TRANSITION_ID0);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			if(crudeList0 !=null && crudeList0.size()>0){
				crude0 = crudeList0.get(0);
			}else{
				crude0 = new PcRpdCrudeTransitionT();
			}
			crude0.setRq(DateBean.getStrDate(rq));
			if(gh0 !=null && !"".equals(gh0)){
				crude0.setGh(gh0);
			
		
			crude0.setGyw(CommonsUtil.getBigDecimalData(gyw0));
			crude0.setDyw(CommonsUtil.getBigDecimalData(dyw0));
			crude0.setSm(CommonsUtil.getBigDecimalData(sm0));
			crude0.setBm(CommonsUtil.getBigDecimalData(bm0));
			crude0.setGw(CommonsUtil.getBigDecimalData(gw0));
			crude0.setHs(CommonsUtil.getBigDecimalData(hs0));
			crude0.setMyl(CommonsUtil.getBigDecimalData(myl0));
			crude0.setCyl(CommonsUtil.getBigDecimalData(cyl0));
			crude0.setSl(CommonsUtil.getBigDecimalData(sl0));
			crude0.setOrgId("239068FA3724460581219B7CD401B386");//一号站
			}
		
	    String zbr  =StringUtil.toStr(StringUtil.isNullStr(request.getParameter("zbr")));
	    u1.setZbr(zbr);
	    boolean updateflg = false;
	    
	    	 try {
	 			updateflg = u1ZHRBService.updateCrude(crude,crude0);
	 		} catch (Exception e) {
	 			String  errmsg = e.getCause().toString();
	 			
	 			if(!"".equals(errmsg) && errmsg.indexOf("ORA-01438") != -1){
	 				obj = CommonsUtil.getRrturnJson("","您输入信息超过数据库数据精度范围" ,"", null, null);
	 			} else{
	 				obj = CommonsUtil.getRrturnJson("",errmsg ,"", null, null);
	 			}
	 			e.printStackTrace();
	 			out.print(obj);
	 			return null;
	 		}
		
	    try {
			updateflg = u1ZHRBService.updateU1S(u1);
		} catch (Exception e) {
			String  errmsg = e.getCause().toString();
			
			if(!"".equals(errmsg) && errmsg.indexOf("ORA-01438") != -1){
				obj = CommonsUtil.getRrturnJson("","您输入信息超过数据库数据精度范围" ,"", null, null);
			} else{
				obj = CommonsUtil.getRrturnJson("",errmsg ,"", null, null);
			}
			e.printStackTrace();
			out.print(obj);
			return null;
		}
		
		if(updateflg){
			//添加系统LOG
			try {
					User user1 = (User) session.getAttribute("userInfo");
					PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "3", "u1综合日报", u1.getRpdU1GeneralId());
					logService.addLog(log);
			} catch (Exception e) {
				e.printStackTrace();
				obj = CommonsUtil.getRrturnJson("","u1综合日报修改失败" ,"", null, null);
			}
		}
		out.print(obj);
		return null;
	}
	
	@SuppressWarnings("unused")
	public  String onAudit()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONObject obj = new JSONObject();
		User user1 = (User) session.getAttribute("userInfo");
		String  id  = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("id")));
		String  RQ = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("RQ")));
		List<PcRpdU1GeneralT> onList= null;
		PcRpdU1GeneralT u1g = null;
		try {
			onList = u1ZHRBService.onAudit(id);
		} catch (Exception e) {
			obj = CommonsUtil.getRrturnJson("","未获取到生产简报ID" ,"", null, null);
			out.print(obj);
			e.printStackTrace();
		}
		if(onList != null &&  onList.size()>0){
			u1g = onList.get(0);
		}
		u1g.setRq(DateBean.getStrDate(RQ));
		u1g.setShr(user1.getOperName());
		//u1g.setShsj(new Date());
		
		boolean  flag = false;
		try {
			flag = u1ZHRBService.updateOnAudit(u1g);
		} catch (Exception e) {
			e.printStackTrace();
		}
		out.print(obj);
		return  null;
	}
	
	
	public String exportData() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		String txtDate = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("reportDate")));
		if ("".equals(txtDate)) {
			txtDate = DateBean.getSystemTime1();
		}
		String templetFilePath = request.getSession().getServletContext().getRealPath("/") + "exceltemplet\\combination\\一号联合站\\1#稠油联合处理站日生产运行简报.xls";
		//String templetFilePath = request.getSession().getServletContext().getRealPath("/") + "exceltemplet\\combination\\一号联合站\\1#稠油联合处理站日生产运行简报2014.06.30模板.xls";
		//String templetFilePath = request.getSession().getServletContext().getRealPath("/") + "exceltemplet\\combination\\一号联合站\\1#稠油联合处理站日生产运行简报无公式.xls";
		HSSFWorkbook wb = ExcelToHtmlUtils.loadXls( new File( templetFilePath ) );
		HSSFSheet sheet = wb.getSheetAt(0);
//		条sql
		PropertiesConfig pc = new PropertiesConfig();
		String FirstSql = pc.getSystemConfiguration("FirstSql");
		String FirstSqlCenter = pc.getSystemConfiguration("FirstSqlCenter");
		String FirstSqlLast = pc.getSystemConfiguration("FirstSqlLast");
		//String FirstSqlone = pc.getSystemConfiguration("FirstSqlone");
		String SecondSql = pc.getSystemConfiguration("SecondSql");
		String ThirdSql = pc.getSystemConfiguration("ThirdSql");//GH 表不同 pc_rpd_crude_transition_t
		//String ThirdSqlLast = pc.getSystemConfiguration("ThirdSqlLast");
		String FourthSql = pc.getSystemConfiguration("FourthSql");
		//String FourthSqlLats = pc.getSystemConfiguration("FourthSqlLats");
		String FifthSql = pc.getSystemConfiguration("FifthSql");
		//String FifthSqlLast = pc.getSystemConfiguration("FifthSqlLast");
		String SisSql = pc.getSystemConfiguration("SisSql");
		String SisSqlLast = pc.getSystemConfiguration("SisSqlLast");
		String SevenSql = pc.getSystemConfiguration("SevenSql");
		String SevenSqlLast = pc.getSystemConfiguration("SevenSqlLast");
		String EighthSql = pc.getSystemConfiguration("EighthSql");
		//String EighthSqlCenter = pc.getSystemConfiguration("EighthSqlCenter");
		String EighthSqlCenterLast = pc.getSystemConfiguration("EighthSqlCenterLast");
		String Others = pc.getSystemConfiguration("pc_rpd_sql"); //值班人
		
		//String insertPosition =  pc.getSystemConfiguration("insertPosition");
		String insertFir =  pc.getSystemConfiguration("insertFir");
		//String insertFirstCenter = pc.getSystemConfiguration("insertFirstCenter");
		String insertFirstLast = pc.getSystemConfiguration("insertFirstLast");
		//String insertOne = pc.getSystemConfiguration("insertOne");
		String insertSec =  pc.getSystemConfiguration("insertSec");
	String insertThir =  pc.getSystemConfiguration("insertThir");//GH 表不同 pc_rpd_crude_transition_t
		//String insertThirLast = pc.getSystemConfiguration("insertThirLast");
		String insertFour =  pc.getSystemConfiguration("insertFour");
		//String insertFourLast = pc.getSystemConfiguration("insertFourLast");
		String insetFif =  pc.getSystemConfiguration("insetFif");
//		String insetFifLast = pc.getSystemConfiguration("insetFifLast");
		String insertSix =  pc.getSystemConfiguration("insertSix");
		String insertSixLast = pc.getSystemConfiguration("insertSixLast");
		String insertSeven =  pc.getSystemConfiguration("insertSeven");
		String insertSevenLast = pc.getSystemConfiguration("insertSevenLast");
		String insertEigh =  pc.getSystemConfiguration("insertEigh");
		//String insertEighCenter = pc.getSystemConfiguration("insertEighCenter");
		String insertEighLast= pc.getSystemConfiguration("insertEighLast");
//		String insertFirAdd= pc.getSystemConfiguration("insertFirAdd");
		
		List<Object[]> list = u1ZHRBService.searchExportData(txtDate, FirstSql);
		if (list != null && list.size() > 0) {
			U2DataExportUtil.insertDataByPosition(list, wb, sheet, insertFir);
		}
		//List<Object[]>  allList = U2DataExportUtil.addRemoDate(list);
		//算合计
		//U2DataExportUtil.insertDataByPosition(allList, wb, sheet, insertFirAdd);
		
		List<Object[]> listCenter = u1ZHRBService.searchExportData(txtDate, FirstSqlCenter);
		String[] basePostion = pc.getSystemConfiguration( "insertFirstCenter" ).split(",");
		String[] insertFirstCenterA = pc.getSystemConfiguration( "insertFirstCenterA" ).split(",");
		if (list != null && list.size() > 0) {
			//U2DataExportUtil.insertDataByPosition(listCenter, wb, sheet, insertFirstCenter);
			for (int j = 0; j < basePostion.length; j++) {
			if (listCenter.get(0)[j] != null) {
				U2DataExportUtil.insertExcelData(wb, sheet, basePostion[j], 0, new Object[]{listCenter.get(0)[j]});
			}
			}
			for (int j = 0; j < insertFirstCenterA.length; j++) {
			if (listCenter.get(1)[j] != null) {
				U2DataExportUtil.insertExcelData(wb, sheet, insertFirstCenterA[j], 0, new Object[]{listCenter.get(1)[j]});
			}
		}
		}
		
		
//		for (int j = 0; j < basePostion.length; j++) {
//			if (list.get(0)[j] != null) {
//				U2DataExportUtil.insertExcelData(wb, sheet, basePostion[j], 0, new Object[]{list.get(0)[j]});
//			}
//		}
		List<Object[]> listLast = u1ZHRBService.searchExportData(txtDate, FirstSqlLast);
		if (listLast != null && listLast.size() > 0) {
			U2DataExportUtil.insertDataByPosition(listLast, wb, sheet, insertFirstLast);
		}

//		List<Object[]> listLastOne = u1ZHRBService.searchExportData(txtDate, FirstSqlone);
//		if (list != null && list.size() > 0) {
//			U2DataExportUtil.insertDataByPosition(listLastOne, wb, sheet, insertOne);
//		}
		
		List<Object[]> SecondList = u1ZHRBService.searchExportData(txtDate, SecondSql);
		if (SecondList != null && SecondList.size() > 0) {
			U2DataExportUtil.insertDataByPosition(SecondList, wb, sheet, insertSec);
		}
		
		List<Object[]> ThirdList = u1ZHRBService.searchExportDataGH(txtDate, ThirdSql);
		
		if (ThirdList != null && ThirdList.size() > 0) {
			
//			Object[] slData =ThirdList.get(0);
//			Object[] ZS =new Object[ThirdList.get(0).length];
//			List<Object[]> countData = new ArrayList<Object[]>();
//			Object hejiData = null;
//			for (int i = 0; i < slData.length; i++) {
//				
//				if(i==9){
//					 hejiData = CommonsUtil.getDecPoint(CommonsUtil.getNotThreeData(ZS[7]),CommonsUtil.getNotThreeData(ZS[8]));
//				}else{
//					hejiData = slData[i];
//				}
//				ZS[i] = hejiData;
//				
//			}
//			countData.add(ZS);
			U2DataExportUtil.insertDataByPosition(ThirdList, wb, sheet, insertThir);
		}
		
		String ghsql =  pc.getSystemConfiguration("ghsql");
		List<Object[]> ghList = u1ZHRBService.searchExportDataGH(txtDate, ghsql);
		String[] ghins =  pc.getSystemConfiguration("ghins").split(",");//GH 表不同 pc_rpd_crude_transition_t
		if (ghList != null && ghList.size() > 0) {
			//U2DataExportUtil.insertDataByPosition(ThirdList, wb, sheet, insertThir);
			for (int j = 0; j < ghins.length; j++) {
				if (ghList.get(0) != null) {
					U2DataExportUtil.insertExcelData(wb, sheet, ghins[j], 0, new Object[]{ghList.get(0)});
				}
				}
		}
		// 二行 罐号
		
		List<Object[]> gh2List = u1ZHRBService.searchExportDataGH2(txtDate);
		String[] ghins2 =  pc.getSystemConfiguration("ghins2").split(",");//GH 表不同 pc_rpd_crude_transition_t
		if (gh2List != null && gh2List.size() > 0) {
			//U2DataExportUtil.insertDataByPosition(ThirdList, wb, sheet, insertThir);
			for (int j = 0; j < ghins2.length; j++) {
				if (gh2List.get(0) != null) {
					U2DataExportUtil.insertExcelData(wb, sheet, ghins2[j], 0, new Object[]{gh2List.get(0)});
				}
				}
		}
		
//		List<Object[]> ThirdListLast = u1ZHRBService.searchExportDataGH(txtDate, ThirdSqlLast);
//		if (ThirdListLast != null && ThirdListLast.size() > 0) {
//			U2DataExportUtil.insertDataByPosition(ThirdListLast, wb, sheet, insertThirLast);
//		}
		
		List<Object[]> FourthList = u1ZHRBService.searchExportDataToDay(txtDate, FourthSql);
		if (FourthList != null && FourthList.size() > 0) {
			U2DataExportUtil.insertDataByPosition(FourthList, wb, sheet, insertFour);
		}
		
//		List<Object[]> FourthLast = u1ZHRBService.searchExportData(txtDate, FourthSqlLats);
//		if (FourthList != null && FourthList.size() > 0) {
//			U2DataExportUtil.insertDataByPosition(FourthLast, wb, sheet, insertFourLast);
//		}
		
		List<Object[]> FifthList = u1ZHRBService.searchExportData(txtDate, FifthSql);
		if (FifthList != null && FifthList.size() > 0) {
			U2DataExportUtil.insertDataByPosition(FifthList, wb, sheet, insetFif);
		}
//		List<Object[]> FifthListLast = u1ZHRBService.searchExportData(txtDate, FifthSqlLast);
//		if (FourthList != null && FourthList.size() > 0) {
//			U2DataExportUtil.insertDataByPosition(FifthListLast, wb, sheet, insetFifLast);
//		}
		
		List<Object[]> SisList = u1ZHRBService.searchExportData(txtDate, SisSql);
		if (SisList != null && SisList.size() > 0) {
			U2DataExportUtil.insertDataByPosition(SisList, wb, sheet, insertSix);
		}
		List<Object[]> SisListLast = u1ZHRBService.searchExportDataToDay(txtDate, SisSqlLast);
		if (SisListLast != null && SisListLast.size() > 0) {
			U2DataExportUtil.insertDataByPosition(SisListLast, wb, sheet, insertSixLast);
		}
		
		List<Object[]> SevenList = u1ZHRBService.searchExportData(txtDate, SevenSql);
		if (SevenList != null && SevenList.size() > 0) {
			U2DataExportUtil.insertDataByPosition(SevenList, wb, sheet, insertSeven);
		}
		List<Object[]> SevenListLast = u1ZHRBService.searchExportDataToDay(txtDate, SevenSqlLast);
		if (SevenListLast != null && SevenListLast.size() > 0) {
			U2DataExportUtil.insertDataByPosition(SevenListLast, wb, sheet, insertSevenLast);
		}
		List<Object[]> EighthList = u1ZHRBService.searchExportData(txtDate, EighthSql);
		if (EighthList != null && EighthList.size() > 0) {
			U2DataExportUtil.insertDataByPosition(EighthList, wb, sheet, insertEigh);
		}

		List<Object[]> EighthListLast = u1ZHRBService.searchExportData(txtDate, EighthSqlCenterLast);
		if (EighthListLast != null && EighthListLast.size() > 0) {
			U2DataExportUtil.insertDataByPosition(EighthListLast, wb, sheet, insertEighLast);
		}

//		获取值班人、审核人、日期、备注数据位置
		List<Object[]> otherlList = u1ZHRBService.searchExportDataToDay(txtDate, Others);
		if(otherlList !=null && otherlList.size()>0){
			String[] OtherPostion = pc.getSystemConfiguration( "insertPosition" ).split(",");
				for (int j = 0; j < OtherPostion.length; j++) {
					if (otherlList.get(0)[j] != null &&  !"".equals(otherlList.get(0)[j])){
						U2DataExportUtil.insertExcelData(wb, sheet, OtherPostion[j], 0, new Object[]{otherlList.get(0)[j]});
					}
			}
		}
		
		//算合计的
		/*String addsql  = pc.getSystemConfiguration("addsql");
		//String addWZ   = pc.getSystemConfiguration("addWZ");
		
		List<Object[]> addlList = u1ZHRBService.searchExportAdd(txtDate, addsql);
		
		List<Object[]> allList  = new ArrayList<Object[]>();;
		Object[] hj = new Object[addlList.get(0).length];
		if(addlList !=null && addlList.size()>0){
				Object[] yesData = addlList.get(0);
				Object[] toData = addlList.get(1);
				for (int i = 0; i < yesData.length; i++) {
					Object heData = CommonsUtil.getRegu(CommonsUtil.getNotThreeData(toData[i]),CommonsUtil.getNotThreeData(yesData[i]));
					
					hj[i] = heData;
				}
				allList.add(hj);
			String[] OtherPostion = pc.getSystemConfiguration( "addWZ" ).split(",");
				for (int j = 0; j < OtherPostion.length; j++) {
					if (allList.get(0)[j] != null &&  !"".equals(allList.get(0)[j])){
						U2DataExportUtil.insertExcelData(wb, sheet, OtherPostion[j], 0, new Object[]{allList.get(0)[j]});
					}
			}
		}*/
		OutputStreamAndCloseBaos(U2DataExportUtil.ExporDataStream(wb));
		return "excel";
	
	}
}
