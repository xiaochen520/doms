package com.echo.action;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import net.sf.json.JSONObject;

import org.apache.poi.hssf.converter.ExcelToHtmlConverter;
import org.apache.poi.hssf.converter.ExcelToHtmlUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.util.IOUtils;
import org.apache.struts2.ServletActionContext;
import org.w3c.dom.Document;

import com.echo.dto.PcCdRightT;
import com.echo.dto.PcRdLoginfoT;
import com.echo.dto.PcRpdReportMessageT;
import com.echo.dto.PcRpdUThinWaterAutoT;
import com.echo.dto.PcRpdUThinWaterGeneralT;
import com.echo.dto.User;
import com.echo.service.LogService;
import com.echo.service.ReportMessageService;
import com.echo.service.U1s2gyService;
import com.echo.util.AuthorityUtil;
import com.echo.util.CommonsUtil;
import com.echo.util.DateBean;
import com.echo.util.PropertiesConfig;
import com.echo.util.ReportFormsBaseUitl;
import com.echo.util.StringUtil;
import com.echo.util.U2DataExportUtil;
import com.opensymphony.xwork2.ActionContext;

public class U1s2gyAction extends ReportFormsBaseUitl{
	private static final long serialVersionUID = 1L;
	HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
	// 水一区罐液位及流量报表
	private U1s2gyService u1s2gyService;
	private LogService logService;
	private ReportMessageService reportMessageService;
	private final String fileName = "一号稠油联合处理站水二区罐液位及流量报表.xls";
	
	public void setReportMessageService(ReportMessageService reportMessageService) {
		this.reportMessageService = reportMessageService;
	}

	public U1s2gyService getU1s2gyService() {
		return u1s2gyService;
	}

	public void setU1s2gyService(U1s2gyService u1s2gyService) {
		this.u1s2gyService = u1s2gyService;
	}

	public LogService getLogService() {
		return logService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	public String getFileName() {
		return super.getFileName(fileName);
	}
	
	public String exportReactorTohtml() throws Exception {
		request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		
		HSSFWorkbook wb = getWb("reportDate");
		Document doc = ExcelToHtmlConverter.process( wb );
		ByteArrayOutputStream bos = new ByteArrayOutputStream();  

		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer serializer = tf.newTransformer();
		serializer.setOutputProperty(OutputKeys.ENCODING, "ISO8859-1");  
		serializer.setOutputProperty(OutputKeys.INDENT, "yes");  
		serializer.setOutputProperty(OutputKeys.METHOD, "html");  
		serializer.transform(new DOMSource(doc), new StreamResult(bos));
		IOUtils.closeQuietly(bos);
		String tableStr = "<table" + bos.toString().split("<table")[1].split("</body>")[0];
		String authority = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("authority")));//获取权限
		if ("修改".equals(authority)) {//具有修改权限
			String zbr = "</td><td class=\"c3\" colspan=\"6\"><input type='text' id='zbr' style='background:transparent;border:0px solid;width:475px;line-height:25px;height:30px;font-size: 12pt;text-center;' value='";//value='"+ZBR1+"'"
			String bz = "<input id='remark' type='text' style='background:transparent;border:0px solid;width:99%;line-height:25px;height:35px;font-size: 12pt;text-align:left;' value='";//value='";//+otherArr[0].BZ+"'/></td>";
			String[] zStr = tableStr.split("</td><td class=\"c3\" colspan=\"6\">");
			String[] zbfStr = zStr[1].split("</td><td class=\"c2\">");
			if ("&nbsp;".equals(zbfStr[0])) {
				zbfStr[0] = "";
			}
			zStr[1] = zStr[1].replaceFirst(zbfStr[0], "");
			tableStr = zStr[0] + zbr + zbfStr[0] + "'/>" + zStr[1];
			String[] bStr = tableStr.split("13\">");
			String bStrs = tableStr.split(bStr[1])[0];
			String[] bzStr = bStr[1].split("</td>");
			if ("&nbsp;".equals(bzStr[0])) {
				bzStr[0] = "";
			}
			bStr[1] = bStr[1].replaceFirst(bzStr[0], "");
			tableStr = bStrs + bz + bzStr[0] + "'/>" + bStr[1];
			String[] tdTexts = {"s2lsllj", "s2hssllj", "s2qf1lj", "s2qf2lj", "s2wssllj", "s2rxlj", "lit_601b", "lit_601a", "lit_603", "lit_602a", "lit_602b", "lt11015", "lt11016"};
			final String dataTr = "<tr class=\"r4\">";
			final String dataTd = "<td class=\"c7\">";
//			tableStr = tableStr.replaceAll("\r\n", "");
			tableStr = tableStr.replaceAll("c8", "c7");
			String[] tStr = tableStr.split( dataTr );
			String endS = tStr[tStr.length - 1];
			for (int j = 1; j < tStr.length ; j++) {
				String[] temp = tStr[j].split( dataTd );
				if (j == tStr.length - 1) {
					String r5Heji = tStr[j].split( "</tr>" )[0];
					temp = r5Heji.split( dataTd );
				}
				String td = "<td class=\"c7\" id='r" + j + "time'>" + temp[1];
				for (int k = 0; k < tdTexts.length ; k++) {
					String endStr = temp[k + 2];
					if (endStr.indexOf("&nbsp") == -1) {
						td += "<td class=\"c7\" id='r" + j + tdTexts[k] + "'>" + endStr;
						continue;
					}
					td += dataTd + "<input id='r" + j + tdTexts[k] + "' type='text' style='background:transparent;border:0px solid;width:70px;line-height:25px;height:30px;font-size: 12pt;text-align:center;' class=\"{number:true}\" value=''/>" + endStr;
				}
				tStr[j] = dataTr + td;
			}
			tStr[tStr.length - 1] = tStr[tStr.length - 1] + "<tr class=\"r5\">" + endS.split("<tr class=\"r5\">")[1];
			StringBuilder b = new StringBuilder();
			int iMax = tStr.length - 1;//数组转字符串
			for (int i = 0;; i++) {
				b.append(String.valueOf(tStr[i]));
				if (i == iMax)
					break;
			}
			tableStr = b.toString();
		}
		
		String txtDate = getParameters("reportDate");
		//String t = "<input type=\"hidden\" id=\"h_id\" name=\"h_id\" value=\"\"/>";//添加数据id 
		StringBuilder sb = new StringBuilder("<input type=\"hidden\" id=\"h_id\" name=\"h_id\" value=\"");
		List<Object[]> list = u1s2gyService.searchExportData("", "select r.rpd_report_message_id from pc_rpd_report_message_t r where r.bbmc='一号稠油联合处理站水二区罐液位及流量报表' and r.rq=TO_DATE('" + txtDate + "','YYYY-MM-DD') ");
		if (list != null && list.size() > 0) {
			sb.append(String.valueOf(list.get(0)));
		}
		else {
			sb.append("null");
		}
		list = u1s2gyService.searchExportData(txtDate, "select uwa.rpd_u1_water_auto_id,TO_char(uwa.bbsj ,'YYYY-MM-DD HH24:MI:SS') from PC_RPD_U1_WATER_AUTO_T uwa");
		if (list != null && list.size() > 0) {
			sb.append(",");
			int iMax = list.size() - 1;
			for (int i = 0;; i++) {
				sb.append(String.valueOf(list.get(i)[0] + "&" + list.get(i)[1]));
				if (i != iMax) {
					sb.append(",");
				}
				else {
					break;
				}
			}
		}
		sb.append("\"/>");
		
		tableStr += sb.toString();//添加数据ha
		tableStr = tableStr.replaceAll("&nbsp;", "");
		tableStr = tableStr.replaceAll("8:05", "8:00");
		JSONObject obj = new JSONObject();
		obj.put("tableStr", tableStr);
		response.getWriter().print(CommonsUtil.getRrturnJson("","" ,"", obj, null));
		
		return null;
	}
	
	public HSSFWorkbook getWb(String date) throws Exception{
		String txtDate = getParameters(date);
		if ("".equals(txtDate)) {
			txtDate = DateBean.getSystemTime1();
		}
		HSSFWorkbook wb = getWorkBook("exceltemplet\\combination\\一号联合站\\一号稠油联合处理站水二区罐液位及流量报表.xls");
		HSSFSheet sheet = wb.getSheetAt(0);

		PropertiesConfig pc = new PropertiesConfig();
		String U1WaterAuto = pc.getSystemConfiguration("PC_RPD_U1_WATER_AUTO_T");
		String U1WatInsertP = pc.getSystemConfiguration("U1WatInsertP");
		
		List<Object[]> list = u1s2gyService.searchExportData(txtDate, U1WaterAuto);
		if (list != null && list.size() > 0) {
			//调用插入数据样式的方法
			U2DataExportUtil.insertDataByPosition(list, wb, sheet, U1WatInsertP);
		}
		String baseInfoSql = "select r.tbr,r.shr,r.rq,r.bz from pc_rpd_report_message_t r where r.bbmc='一号稠油联合处理站水二区罐液位及流量报表' and r.rq=TO_DATE('" + txtDate + "','YYYY-MM-DD') ";
		list = u1s2gyService.searchExportData("", baseInfoSql);
		if (list != null && list.size() > 0) {
			// 获取单位、值班人、审核人、日期、备注数据插入位置
			String[] basePostion = pc.getSystemConfiguration( "U1WatBaseInfo" ).split(",");
			for (int j = 0; j < basePostion.length; j++) {
				if (list.get(0)[j] != null) {
					U2DataExportUtil.insertExcelData(wb, sheet, basePostion[j], 0, new Object[]{list.get(0)[j]});
				}
			}
		}
		return wb;
	}
	
	public String exportReactor() throws Exception {
		
		HSSFWorkbook wb = getWb("reportDate");

		wb.setForceFormulaRecalculation(true);
		OutputStreamAndCloseBaos(U2DataExportUtil.ExporDataStream(wb));
		return "excel";
	}
	
	public String updateU1s2gy() throws Exception {
		PrintWriter out = ServletActionContext.getResponse().getWriter();
		JSONObject obj = new JSONObject();
		String dataArray = getParameters("dataArray");
		dataArray = dataArray.replaceAll("\\[", "");
		dataArray = dataArray.replaceAll("\"", "");
		String zbr = getParameters("zbr");
		String remark = getParameters("remark");
		String h_id = getParameters("h_id");
		String rq = getParameters("reportDate");
		try {
		boolean upFlag = u1s2gyService.updateU1s2gy(dataArray, zbr, remark, h_id, rq);
		if(upFlag){
				User user1 = (User) session.getAttribute("userInfo");
//				PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "3", "污水处理综合日报", waterGen.getRpdUThinWaterGeneralId());
//				logService.addLog(log);
		}
			} catch (Exception e) {
				e.printStackTrace();
				obj = CommonsUtil.getRrturnJson("","一号稠油联合处理站水二区罐液位及流量报表修改失败" ,"", null, null);
			}
		out.print(obj);
		return null;
	}
	
	public String pageInit()throws Exception{
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONObject obj = new JSONObject();
		//根据用户权限生成用户权限生成页面布局格式
		User user = (User) session.getAttribute("userInfo");
//		
		JSONObject butJson = null;
		try {
			butJson = AuthorityUtil.getButtonJson("MENU115", user);
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
	
	public String onAudit() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONObject obj = new JSONObject();
		User user1 = (User) session.getAttribute("userInfo");
		String firstStr = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("h_id")));
		
		firstStr = firstStr.substring(0,firstStr.length()-1);
		String[] firstList = firstStr.split(";",-1);  
		String h_id="";
		for(int i=0;i<firstList.length;i++){
			String[] firstPra = firstList[i].split(",",-1);
			 h_id = firstPra[0];
		}
			
			
		List<PcRpdReportMessageT> reportMessage = null;
		PcRpdReportMessageT reportM = null;
		if(h_id != null && !"".equals(h_id)){
			reportMessage = reportMessageService.SreachReportMessages(h_id, "", "");
		}else{
			
			obj = CommonsUtil.getRrturnJson("","一号稠油联合处理站水二区罐液位及流量报表ID获取失败" ,"", null, null);
			out.print(obj);
			return null;
		}
		
		if(reportMessage != null && reportMessage.size()>0){
			reportM = reportMessage.get(0);
		}else{
			obj = CommonsUtil.getRrturnJson("","未获取到水二区罐液位及流量日报表ID对应数据" ,"", null, null);
			out.print(obj);
			return null;
		}
		reportM.setShr(user1.getOperName());
		reportM.setShsj(new Date());
		reportM.setBbmc("一号稠油联合处理站水二区罐液位及流量报表");

		boolean updateflg = false;
		try {
			updateflg = reportMessageService.updateReportMessages(reportM);
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
				PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "3", "水二区日报表", reportM.getRpdReportMessageId());
				logService.addLog(log);
			} catch (Exception e) {
				e.printStackTrace();
				obj = CommonsUtil.getRrturnJson("","水二区日志修改失败" ,"", null, null);
			}
		}
		out.print(obj);
		return null;
	}
}

