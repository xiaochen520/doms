package com.echo.action;


import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;
import com.echo.dto.PcRdLoginfoT;
import com.echo.dto.PcRpdWaterMessageT;
import com.echo.dto.User;
import com.echo.service.LogService;
import com.echo.service.ReportMessageService;
import com.echo.service.RhqyxrbService;
import com.echo.util.AuthorityUtil;
import com.echo.util.CommonsUtil;
import com.echo.util.DateBean;
import com.echo.util.ReportFormsBaseUitl;
import com.echo.util.StringUtil;

@SuppressWarnings("serial")
public class RhqzsAction extends ReportFormsBaseUitl{

	private RhqyxrbService rhqyxrbService;
	private ReportMessageService reportMessageService;

	
	public void setRhqyxrbService(RhqyxrbService rhqyxrbService) {
		this.rhqyxrbService = rhqyxrbService;
	}
	HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话

	
	String clz = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("clz")));
	String rhq = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("rhq")));
	String TabHeaName ="风城油田作业区供汽联合站"+ clz +rhq+ "号软化器运行日报 .xls";
	//private final String fileName = "软化器日报.xls";
	private final String fileName = TabHeaName;
	public String getFileName() {
		return super.getFileName(fileName);
	}
	private LogService logService;
	
	public LogService getLogService() {
		return logService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	public void setReportMessageService(ReportMessageService reportMessageService) {
		this.reportMessageService = reportMessageService;
	}

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
			butJson = AuthorityUtil.getButtonJson("MENU191", user);
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
	
	@SuppressWarnings("unused")
	public String searchDatas()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONArray syjyx = null;
		JSONArray waterArr = null;
		JSONObject obj = null;
		JSONObject jsonobj = null;
		JSONObject waterObj = null;
		String txtDate = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("txtDate")));
		String clz = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("clz")));
		String rhq = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("rhq")));			
		String setRHQ = clz.concat(rhq);
		String rhqName =clz+rhq+"号软化器运行日报";
	//	String rhqName =clz;	
		if("".equals(clz)){
			obj = CommonsUtil.getRrturnJson("","水处理站不能为空" ,"", null, null);
			out.print(obj);
			return null;
		}						
		if("".equals(rhq)){
			obj = CommonsUtil.getRrturnJson("","设备编号不能为空" ,"", null, null);
			out.print(obj);
			return null;
		}			
		String[] waterMessage = new String[9];
		List<PcRpdWaterMessageT> waterMess = null;
		PcRpdWaterMessageT rm = null;
		if(txtDate == null || "".equals(txtDate)){
			txtDate = DateBean.getSystemTime1();
		}			
		try {
			syjyx = rhqyxrbService.searchSyjyxrb1(clz,rhq,txtDate,setRHQ);
			if(syjyx != null && syjyx.size()>0){
				jsonobj = new JSONObject();
				jsonobj.put("syjyx", syjyx);
			}
		} catch (Exception e) {
			e.printStackTrace();
			obj = CommonsUtil.getRrturnJson("","当前日期搜索失败请选择其他日期" ,"", null, null);
			out.print(obj);
			return null;
		}
		try {
			waterMess = reportMessageService.SreachWaterMessageT("", rhqName, txtDate);
			if(waterMess != null && waterMess.size()>0){
				rm = waterMess.get(0);
				
			}else{
				rm = new PcRpdWaterMessageT();
				rm.setRq(DateBean.getStrDate(txtDate));
				rm.setBbmc(rhqName);
				boolean updateflg = false;
				try {
					updateflg = reportMessageService.updateWaterMessage(rm);
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
				waterMess = reportMessageService.SreachWaterMessageT("", rhqName, txtDate);
				rm = waterMess.get(0);
				if(updateflg){
					//添加系统LOG
					try {
						User user1 = (User) session.getAttribute("userInfo");
						PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "1", "软化器报表", rm.getWaterMessageId());
						logService.addLog(log);
					} catch (Exception e) {
						e.printStackTrace();
						obj = CommonsUtil.getRrturnJson("",rhqName+"运行日报日志添加失败" ,"", null, null);
					}
				}
			}
			
			obj = new JSONObject();
			obj.put("WATERMESSAGEID", rm.getWaterMessageId());
			obj.put("BBMC", rm.getBbmc());
			obj.put("RQ", DateBean.getChinaDate(txtDate));
			if( rm.getZbr1() !=null &&  !rm.getZbr1().equals("")){
				obj.put("ZBR1", rm.getZbr1());
			}else{
				obj.put("ZBR1", "");
			}
			if(rm.getZbr2() !=null &&  !rm.getZbr2().equals("")){
				obj.put("ZBR2", rm.getZbr2());
			}else{
				obj.put("ZBR2", "");
			}
			if( rm.getTbr1()!=null &&  !rm.getTbr1().equals("")){
				obj.put("TBR1", rm.getTbr1()); //白班填报人
			}else{
				obj.put("TBR1", ""); //白班填报人
			}
			if( rm.getTbr2() !=null &&  !rm.getTbr2().equals("")){
				obj.put("TBR2", rm.getTbr2()); //晚班填报人
			}else{
				obj.put("TBR2", ""); //晚班填报人
			}
			if(rm.getShr() !=null  && !rm.getShr().equals("undefined")){
				obj.put("SHR", rm.getShr());
			}else{
				obj.put("SHR","");
			}
			if( rm.getBz1()!=null &&  !rm.getBz1().equals("")){
				obj.put("BZ1", rm.getBz1()); //白班备注
			}else{
				obj.put("BZ1", ""); //白班备注
			}
			if(rm.getBz2() !=null &&  !rm.getBz2().equals("")){
				obj.put("BZ2", rm.getBz2()); //夜班备注
			}else{
				obj.put("BZ2", ""); //夜班备注
			}
			if( rm.getSituationLog1() !=null &&  !rm.getSituationLog1().equals("")){
				obj.put("LOG1", rm.getSituationLog1()); //设备再生情况记录1
			}else{
				obj.put("LOG1",""); //设备再生情况记录1
			}
			if(rm.getSituationLog2() !=null &&  !rm.getSituationLog2().equals("")){
				obj.put("LOG2", rm.getSituationLog2());
			}else{
				obj.put("LOG2", "");
			}
			if( rm.getSituationLog3()!=null &&  !rm.getSituationLog3().equals("")){
				obj.put("LOG3", rm.getSituationLog3());
			}else{
				obj.put("LOG3", "");
			}
			
			if( rm.getZsllA()!=null &&  !rm.getZsllA().equals("")){
				obj.put("waterA", rm.getZsllA());
			}else{
				obj.put("waterA", "");
			}
			if( rm.getZsllB()!=null &&  !rm.getZsllB().equals("")){
				obj.put("waterB", rm.getZsllB());
			}else{
				obj.put("waterB", "");
			}
			if( rm.getZsllC()!=null &&  !rm.getZsllC().equals("")){
				obj.put("waterC", rm.getZsllC());
			}else{
				obj.put("waterC", "");
			}
			if( rm.getZsllD()!=null &&  !rm.getZsllD().equals("")){
				obj.put("waterD", rm.getZsllD());
			}else{
				obj.put("waterD", "");
			}
			
			int ZSCS = 0;
			String str = rm.getSituationLog1();
			if(str!=null &&  !str.equals("")){
				String[] str1 = str.split(",");
				
				for(int i=0;i<str1.length;i++){
					int id1 = str1[i].indexOf("进盐");
					int id2 = str1[i].indexOf("置换");
					int id3 = str1[i].indexOf("正洗");
					if(-1!=id1 &&  -1!=id2 && -1!=id3){
						ZSCS = ZSCS + 1;
					}
				}
				String a;
				for(int i=0;i<1;i++){
					a = str1[i];
					obj.put("A", a);
				}
				String b;
				for(int i=1;i<2;i++){
					b = str1[i];
					obj.put("B", b);
				}

				String c;
				for(int i=2;i<3;i++){
					c = str1[i];
					obj.put("C", c);
				}


				
			}
			obj.put("ZSCS", ZSCS);
			jsonobj.put("WATERMESSAGE", obj);
			
		} catch (Exception e) {
			e.printStackTrace();
			obj = CommonsUtil.getRrturnJson("","当前日期搜索失败请选择其他日期" ,"", null, null);
			out.print(obj);
			return null;
		}
		obj = CommonsUtil.getRrturnJson("","" ,"", jsonobj, null);
		out.print(obj);
	return null;
	}
	
	
}
