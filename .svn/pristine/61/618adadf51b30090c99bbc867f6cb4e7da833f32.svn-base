package com.echo.action;

import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.hssf.converter.ExcelToHtmlUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import com.echo.dto.PcRdLoginfoT;
import com.echo.dto.PcRpdDeaeratorT;
import com.echo.dto.PcRpdDtowerT;
import com.echo.dto.PcRpdWaterMessageT;
import com.echo.dto.User;
import com.echo.service.LogService;
import com.echo.service.ReportMessageService;
import com.echo.service.TytyxrbService;
import com.echo.util.AuthorityUtil;
import com.echo.util.CommonsUtil;
import com.echo.util.DateBean;
import com.echo.util.PropertiesConfig;
import com.echo.util.ReportFormsBaseUitl;
import com.echo.util.StringUtil;
import com.echo.util.U2DataExportUtil;

public class TytyxrbAction extends ReportFormsBaseUitl{
	/**
	 * // 水一区罐液位及流量报表
	 */
	HttpSession  session = ServletActionContext.getRequest().getSession(true); 
	private static final long serialVersionUID = 1L;
	private TytyxrbService tytyxrbService;
	private ReportMessageService reportMessageService;
	private LogService logService;
	
	

	public void setTytyxrbService(TytyxrbService tytyxrbService) {
		this.tytyxrbService = tytyxrbService;
	}

	public void setReportMessageService(ReportMessageService reportMessageService) {
		this.reportMessageService = reportMessageService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	
	String clz = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("sclz")));
	String rhq = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("sbbh")));
	String TabHeaName ="风城油田作业区供汽联合站"+ clz +"水处理站" +rhq+ "号除氧器运行日报 .xls";
	private final String fileName = TabHeaName;
	//private final String fileName = "脱氧塔日报.xls";

	public String getFileName() {
		return super.getFileName(fileName);
	}
	public String searchDatas()throws Exception{
			HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
			HttpServletResponse response=ServletActionContext.getResponse();//响应对象
			response.setCharacterEncoding("utf-8");
			response.setHeader("ContentType","text/xml");
			PrintWriter out = response.getWriter();
			List<PcRpdWaterMessageT> pcrpdwatermessage = null;
			JSONArray datas = null;
			JSONObject obj = null;
			JSONObject jsonobj = null;
			String txtDate = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("txtDate")));
			String sclz = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("sclz")));
			String sbbh = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("sbbh")));
			String exportName = "";
			if("".equals(sclz)){
				obj = CommonsUtil.getRrturnJson("","水处理站不能为空" ,"", null, null);
				out.print(obj);
				return null;
			}
			
			
			if("".equals(sbbh)){
				obj = CommonsUtil.getRrturnJson("","设备编号不能为空" ,"", null, null);
				out.print(obj);
				return null;
			}
			
			exportName = sclz +"水处理站"+sbbh+"号脱氧塔运行日报";
			//获取当天所以二号稠油联合站原油系统日报数据
			
			
			PcRpdWaterMessageT reportM = null;
			if(txtDate == null || "".equals(txtDate)){
				txtDate = DateBean.getSystemTime1();
			}
			
			try {
				datas = tytyxrbService.searchDatas(txtDate,sclz,sbbh);
				if(datas != null && datas.size()>0){
					jsonobj = new JSONObject();
					jsonobj.put("DATAS", datas);
				}

			} catch (Exception e) {
				e.printStackTrace();
				obj = CommonsUtil.getRrturnJson("","当前日期搜索失败请选择其他日期" ,"", null, null);
				out.print(obj);
				return null;
			}
			try {
				pcrpdwatermessage = reportMessageService.SreachWaterMessages("", exportName, txtDate);
				if(pcrpdwatermessage != null && pcrpdwatermessage.size()>0){
					reportM = pcrpdwatermessage.get(0);
					
				}else{
					reportM = new PcRpdWaterMessageT();
					reportM.setRq(DateBean.getStrDate(txtDate));
					reportM.setBbmc(exportName);
					boolean updateflg = false;
					try {
						updateflg = reportMessageService.updateWatertMessages(reportM);
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
					pcrpdwatermessage = reportMessageService.SreachWaterMessages("", exportName, txtDate);
					reportM = pcrpdwatermessage.get(0);
					if(updateflg){
						//添加系统LOG
						try {
							User user1 = (User) session.getAttribute("userInfo");
							PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "1", exportName, reportM.getWaterMessageId());
							logService.addLog(log);
						} catch (Exception e) {
							e.printStackTrace();
							obj = CommonsUtil.getRrturnJson("",exportName+"日志添加失败" ,"", null, null);
						}
					}
				}
				
				
				
				obj = new JSONObject();
				obj.put("RPDREPORTMESSAGEID", reportM.getWaterMessageId());
				obj.put("TBR1", reportM.getTbr1());
				obj.put("TBR2", reportM.getTbr2());
				obj.put("SHR", reportM.getShr());
				obj.put("RQ", txtDate);
				obj.put("RQ1", DateBean.getChinaDate(txtDate));
				obj.put("BZ1", reportM.getBz1());
				obj.put("BZ2", reportM.getBz2());
				obj.put("EXPORTNAME", exportName);
				
				jsonobj.put("REPORTMESSAGE", obj);
				
				
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
				butJson = AuthorityUtil.getButtonJson("MENU134", user);
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
			String RPDREPORTMESSAGEID = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("RPDREPORTMESSAGEID")));
			String EXPORTNAME = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("EXPORTNAME")));
			List<PcRpdWaterMessageT> reportMessage = null;
			PcRpdWaterMessageT reportM = null;
			if(RPDREPORTMESSAGEID != null && !"".equals(RPDREPORTMESSAGEID)){
				reportMessage = reportMessageService.SreachWaterMessages(RPDREPORTMESSAGEID, "", "");
			}else{
				
				obj = CommonsUtil.getRrturnJson("",EXPORTNAME+"日志信息获取失败" ,"", null, null);
				out.print(obj);
				return null;
			}
			
			if(reportMessage != null && reportMessage.size()>0){
				reportM = reportMessage.get(0);
			}else{
				obj = CommonsUtil.getRrturnJson("","未获取到"+EXPORTNAME+"日报表ID对应数据" ,"", null, null);
				out.print(obj);
				return null;
			}
			reportM.setShr(user1.getOperName());
			reportM.setShsj(new Date());
//			reportM.setBbmc(EXPORTNAME);

			boolean updateflg = false;
			try {
				updateflg = reportMessageService.updateWatertMessages(reportM);
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
					PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "7", EXPORTNAME, reportM.getWaterMessageId());
					logService.addLog(log);
				} catch (Exception e) {
					e.printStackTrace();
					obj = CommonsUtil.getRrturnJson("",EXPORTNAME+"审核日志添加失败" ,"", null, null);
				}
			}
			out.print(obj);
			return null;
		}
		
		public String updateDatas() throws Exception {
			HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
			HttpServletResponse response=ServletActionContext.getResponse();//响应对象
			response.setCharacterEncoding("utf-8");
			response.setHeader("ContentType","text/xml");
			PrintWriter out = response.getWriter();
			JSONObject obj = new JSONObject();
			String RPDREPORTMESSAGEID = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("RPDREPORTMESSAGEID")));
			String TBR1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("TBR1")));
			String TBR2 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("TBR2")));
			String BZ1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("BZ1")));
			String BZ2 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("BZ2")));
			String RQ = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("RQ")));
			
			String SCLZ = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("SCLZ")));
			String SBBH = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("SBBH")));
			
			String EXPORTNAME = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("EXPORTNAME")));

			if("".equals(EXPORTNAME)){
				
				obj = CommonsUtil.getRrturnJson("","当前报表名称为空不能进行修改" ,"", null, null);
				out.print(obj);
				return null;
			}
			List<PcRpdWaterMessageT> reportMessage = null;
			PcRpdWaterMessageT reportM = null;
			if(RPDREPORTMESSAGEID != null && !"".equals(RPDREPORTMESSAGEID)){
				reportMessage = reportMessageService.SreachWaterMessages(RPDREPORTMESSAGEID, "", "");
			}
			
			if(reportMessage != null && reportMessage.size()>0){
				reportM = reportMessage.get(0);
			}else{
				if(RQ != null && !"".equals(RQ)){
					
					reportMessage = reportMessageService.SreachWaterMessages("", EXPORTNAME, RQ);
				}else{
					obj = CommonsUtil.getRrturnJson("","系统无当前日期，请重新选择数据" ,"", null, null);
					out.print(obj);
					return null;
				}
				
				if(reportMessage != null && reportMessage.size()>0){
					obj = CommonsUtil.getRrturnJson("","系统已存在当前报表日报记录，请选择其他日期" ,"", null, null);
					out.print(obj);
					return null;
				}else{
					reportM = new PcRpdWaterMessageT();
				}
			}
			
			//reportM.setWaterMessageId(RPDREPORTMESSAGEID);
			//reportM.setZbr1(ZBR1);
			//reportM.setZbr2(ZBR2);
//			reportM.setShr(SHR);
			reportM.setTbr1(TBR1);
			reportM.setTbr2(TBR2);
			reportM.setRq(DateBean.getStrDate(RQ));
			reportM.setBbmc(EXPORTNAME);
			reportM.setBz1(BZ1);
			reportM.setBz2(BZ2);
			
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String reportDate = df.format(new Date());
			String firstStr = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("firstStr")));
			firstStr = firstStr.substring(0,firstStr.length()-1);
			String[] firstList = firstStr.split(";",-1);
			for(int i=0;i<firstList.length;i++){
				String[] firstPra = firstList[i].split(",",-1);
				if(!"".equals(CommonsUtil.getClearNullData(firstPra[0]))){
					String id = firstPra[0];
					
					List<PcRpdDtowerT> usList = null;
					PcRpdDtowerT us = null;
					if(id != null && !"".equals(id)){
						try {
							usList = tytyxrbService.SreachDatas(id, "");
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					if(usList != null && usList.size()>0){
						us = usList.get(0);
						
					}else{
//						obj = CommonsUtil.getRrturnJson("","报表ID获取失败，没有此时的您要修改的数据，请选择其他时间" ,"", null, null);
//						out.print(obj);
//						return null;
						us = new PcRpdDtowerT();
					}
					String beforetime = DateBean.getBeforeDAYTime(RQ);
					int calcNum = tytyxrbService.searchCalcNum();
					int zeroIndex = -calcNum/2;
					if(i>=zeroIndex){
						us.setBbsj(sf.parse(RQ +" "+ firstPra[1]));
					}else{
						us.setBbsj(sf.parse(beforetime +" "+ firstPra[1]));
					}

						
					
					us.setRpdDtowerId(firstPra[0]);
					us.setXhPumpP(CommonsUtil.isNullOrEmpty(firstPra[2]));
					us.setGsPumpP(CommonsUtil.isNullOrEmpty(firstPra[3]));
					us.setZyPumpP(CommonsUtil.isNullOrEmpty(firstPra[4]));
					us.setW1lt(CommonsUtil.isNullOrEmpty(firstPra[5]));
					
					us.setW1yo(CommonsUtil.isNullOrEmpty(firstPra[6]));
					us.setZyPumpFreq(CommonsUtil.isNullOrEmpty(firstPra[7]));
					us.setW1ft(CommonsUtil.isNullOrEmpty(firstPra[8]));
					us.setCyqDo(CommonsUtil.isNullOrEmpty(firstPra[9]));
					
					us.setSclzmc(SCLZ+"水处理站");
					us.setSbbh(SBBH);

	         		boolean updateflg = false;
	        		try {
	        			updateflg = tytyxrbService.updateData(us);
	        		} catch (Exception e) {
	        			String  errmsg = e.getCause().toString();
	        			
	        			if(!"".equals(errmsg) && errmsg.indexOf("ORA-01438") != -1){
	        				obj = CommonsUtil.getRrturnJson("","您输入信息超过数据库数据精度范围" ,"", null, null);
	        			} else{
	        				obj = CommonsUtil.getRrturnJson("",errmsg ,"", null, null);
	        			}
	        			e.printStackTrace();
	        			out.print(obj);
	        			//return null;
	        		}
	        		if(!updateflg){
	        			obj = CommonsUtil.getRrturnJson("","第"+(i+1)+"行修改失败" ,"", null, null);
	        		}
				
	        		
	        		
					}
			
			}
			boolean updateflg = false;
			try {
				updateflg = reportMessageService.updateWatertMessages(reportM);
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
					PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "3", "EXPORTNAME", reportM.getWaterMessageId());
					logService.addLog(log);
				} catch (Exception e) {
					e.printStackTrace();
					obj = CommonsUtil.getRrturnJson("",EXPORTNAME+"修改日志添加失败" ,"", null, null);
				}
			}
			out.print(obj);
			return null;
		
		}
		
		
		
		public String exportDatas() throws Exception {

			HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
			String txtDate = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("txtDate")));
			String clz = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("sclz")));
			String rhq = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("sbbh")));
			String WATERMESSAGEID = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("RPDREPORTMESSAGEID")));
			
			String sclzmc = clz+"水处理站";
			//Date rq =  DateBean.getStrDate(txtDate);
		
			if ("".equals(txtDate)) {
				txtDate = DateBean.getSystemTime1();
//				return null;
			}
			PropertiesConfig pc = new PropertiesConfig();
			//审核 备注 等
			String fields = pc.getSystemConfiguration("TinsZHDataSql");
			String[] insZHDataPosit = pc.getSystemConfiguration("TinsZHData").split(",");
			
			//表头
			String insTabHe = pc.getSystemConfiguration("TinsTabHe");
			String TabHeaName ="风城油田作业区供汽联合站"+ clz +"水处理站" +rhq+ "号除氧器运行日报 ";
			
			//# 主体数据
			String insMain = pc.getSystemConfiguration("TinsMain");
			String insMainSql = pc.getSystemConfiguration("TinsMainSql");
			
		
			
			String templetFilePath = request.getSession().getServletContext().getRealPath("/") + "exceltemplet\\ExportRBWH\\脱氧塔运行日报.xls";
			HSSFWorkbook wb = ExcelToHtmlUtils.loadXls( new File( templetFilePath ) );
			HSSFSheet sheet = wb.getSheetAt(0);
			
			U2DataExportUtil.insertExcelTableHead( wb, sheet, insTabHe ,TabHeaName);
			
			List<Object[]> list = tytyxrbService.searchExportData(txtDate, insMainSql,sclzmc,rhq);
			if (list != null && list.size() > 0) {
				//调用插入数据样式的方法
				
				U2DataExportUtil.insertDataByPosition(list, wb, sheet, insMain);
			}
			
			List<Object[]> zList=null;
			String punSql = "select "+fields+" from pc_rpd_water_message_t a  where  a.rq=to_date('"+txtDate+"' ,'YYYY-MM-DD')  and a.water_message_id='"+WATERMESSAGEID+"'";
				zList = tytyxrbService.searchExportData(punSql);
			if (zList != null && zList.size() > 0) {
				// 获取单位、值班人、审核人、日期、备注数据插入位置
				//String[] basePostion = pc.getSystemConfiguration( "U1ComInsert" )
				
				for (int j = 0; j < insZHDataPosit.length; j++) {
					if (zList.get(0)[j] != null) {
						U2DataExportUtil.insertExcelData(wb, sheet, insZHDataPosit[j], 0, new Object[]{zList.get(0)[j]});
					}
				}
			}
			

			OutputStreamAndCloseBaos(U2DataExportUtil.ExporDataStream(wb));
			return "excel";
		
		}

}
