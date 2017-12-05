package com.echo.action;

import java.io.File;
import java.io.PrintWriter;
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
import com.echo.dto.PcRpdWaterMessageT;
import com.echo.dto.PcRpdWaterSourceWelldT;
import com.echo.dto.User;
import com.echo.service.LogService;
import com.echo.service.ReportMessageService;
import com.echo.service.SyjyxrbService;
import com.echo.util.AuthorityUtil;
import com.echo.util.CommonsUtil;
import com.echo.util.DateBean;
import com.echo.util.PropertiesConfig;
import com.echo.util.ReportFormsBaseUitl;
import com.echo.util.StringUtil;
import com.echo.util.U2DataExportUtil;

public class SyjyxrbAction extends ReportFormsBaseUitl{
		private SyjyxrbService syjyxrbService;
		private ReportMessageService reportMessageService;

		public void setSyjyxrbService(SyjyxrbService syjyxrbService) {
			this.syjyxrbService = syjyxrbService;
		}
		HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话

		private final String fileName = "水源井日报.xls";

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
				butJson = AuthorityUtil.getButtonJson("MENU131", user);
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
		
		public String searchSYJYXRB()throws Exception{
			HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
			HttpServletResponse response=ServletActionContext.getResponse();//响应对象
			response.setCharacterEncoding("utf-8");
			response.setHeader("ContentType","text/xml");
			PrintWriter out = response.getWriter();
			JSONArray syjyx = null;
			JSONObject obj = null;
			JSONObject jsonobj = null;
			String txtDate = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("txtDate")));
			
			String sqlNo =" select a.watersource_welldid ,a.water_source_wellid, a.INSTANTANEOUS_DELIVERY,a.CUMULATIVE_DISCHARGE,a.PRES,LIQUID_LEVEL,remark  from  pc_rpd_water_source_welld_t a where 1=1";
					sqlNo += " and a.REPORT_DATE = TO_DATE('"+txtDate+"','YYYY-MM-DD')";
			List<Object[]> OneList = null;
			OneList = syjyxrbService.searchNotNull(sqlNo);
			if(OneList !=null && OneList.size()>0){
				
			
			String[] waterMessage = new String[9];
			List<PcRpdWaterMessageT> waterMess = null;
			PcRpdWaterMessageT rm = null;
			if(txtDate == null || "".equals(txtDate)){
				txtDate = DateBean.getSystemTime1();
			}
			
			try {
				syjyx = syjyxrbService.searchSyjyxrb(txtDate);
				if(syjyx != null && syjyx.size()>0){
					jsonobj = new JSONObject();
					jsonobj.put("syjyx", syjyx);
				}
//				else{
//					obj = CommonsUtil.getRrturnJson("","当前日期搜索无数据请选择其他日期" ,"", null, null);
//					out.print(obj);
//					return null;
//				}
			} catch (Exception e) {
				e.printStackTrace();
				obj = CommonsUtil.getRrturnJson("","当前日期搜索失败请选择其他日期" ,"", null, null);
				out.print(obj);
				return null;
			}
			try {
				waterMess = reportMessageService.SreachWaterMessageT("", "水源井运行日报", txtDate);
				if(waterMess != null && waterMess.size()>0){
					rm = waterMess.get(0);
					
				}else{
					rm = new PcRpdWaterMessageT();
					rm.setRq(DateBean.getStrDate(txtDate));
					rm.setBbmc("水源井运行日报");
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
					waterMess = reportMessageService.SreachWaterMessageT("", "水源井运行日报", txtDate);
					rm = waterMess.get(0);
					if(updateflg){
						//添加系统LOG
						try {
							User user1 = (User) session.getAttribute("userInfo");
							PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "1", "水源井报表", rm.getWaterMessageId());
							logService.addLog(log);
						} catch (Exception e) {
							e.printStackTrace();
							obj = CommonsUtil.getRrturnJson("","水源井运行日报日志添加失败" ,"", null, null);
						}
					}
				}
				
				obj = new JSONObject();
				obj.put("WATERMESSAGEID", rm.getWaterMessageId());
				obj.put("BBMC", rm.getBbmc());
				obj.put("ZBR1", rm.getZbr1());
				obj.put("RQ", DateBean.getChinaDate(txtDate));
				obj.put("ZBR2", rm.getZbr2());
				
				obj.put("TB1", rm.getTbr1());
				obj.put("TBR2", rm.getTbr2());
				if(rm.getShr() !=null  && !rm.getShr().equals("undefined")){
					obj.put("SHR", rm.getShr());
				}else{
					obj.put("SHR","");
				}
				
				obj.put("BZ1", rm.getBz1());
				obj.put("BZ2", rm.getBz2());
				
				jsonobj.put("WATERMESSAGE", obj);
				
			} catch (Exception e) {
				e.printStackTrace();
				obj = CommonsUtil.getRrturnJson("","当前日期搜索失败请选择其他日期" ,"", null, null);
				out.print(obj);
				return null;
			}
		}else{
			obj = CommonsUtil.getRrturnJson("","当前日期无数据,请选择其他日期" ,"", null, null);
			out.print(obj);
			return null;
		}
			
			obj = CommonsUtil.getRrturnJson("","" ,"", jsonobj, null);
			out.print(obj);
		return null;
		}
		
		
    public String updateSYJyxrb() throws Exception {
			HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
			HttpServletResponse response=ServletActionContext.getResponse();//响应对象
			response.setCharacterEncoding("utf-8");
			response.setHeader("ContentType","text/xml");
			PrintWriter out = response.getWriter();
			JSONObject obj = new JSONObject();
			
		
			String firstStr = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("firstStr")));
			firstStr = firstStr.substring(0,firstStr.length()-1);
			String[] firstList = firstStr.split(";",-1);
			for(int i=0;i<firstList.length;i++){
				String[] firstPra = firstList[i].split(",",-1);
					String rpdid = firstPra[0];
					String id = firstPra[1];
					List<PcRpdWaterSourceWelldT> wtList = null;
					PcRpdWaterSourceWelldT wt = null;
					if(rpdid != null && !"".equals(rpdid)){
						try {
							wtList = syjyxrbService.SreachSYJRBid(rpdid, "");
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					if(wtList != null && wtList.size()>0){
						wt = wtList.get(0);
						
					}else{
						obj = CommonsUtil.getRrturnJson("","第"+(i+1)+" 行数据报表修改失败，没有此时的您要修改的数据" ,"", null, null);
						out.print(obj);
						return null;
						//wt = new PcRpdWaterSourceWelldT();
					}
					
			
					wt.setWaterSourceWellid(id);
					wt.setInstantaneousDelivery(CommonsUtil.getBigDecimalData(firstPra[2]));
					wt.setCumulativeDischarge(CommonsUtil.getBigDecimalData(firstPra[3]));
					wt.setPres(CommonsUtil.getBigDecimalData(firstPra[4]));
					wt.setLiquidLevel(CommonsUtil.getBigDecimalData(firstPra[5]));
					wt.setRemark(firstPra[6]);
					String RQ = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("RQ")));
					wt.setReportDate(DateBean.getStrDate(RQ));
					//wtList.clear();
					//wtList.add(wt);
	         		boolean updateflg = false;
	        		try {
	        			updateflg = syjyxrbService.updateData(wt);
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
	        		if(updateflg){
	    				//添加系统LOG
	    				try {
	    					User user1 = (User) session.getAttribute("userInfo");
	    					PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "3", "水源井", wt.getWatersourceWelldid());
	    					logService.addLog(log);
	    				} catch (Exception e) {
	    					e.printStackTrace();
	    					obj = CommonsUtil.getRrturnJson("","水源井日报日志修改失败" ,"", null, null);
	    				}
	    			}
				
			
			}
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
		String WATERMESSAGEID = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("WATERMESSAGEID")));
		List<PcRpdWaterMessageT> watMessage = null;
		PcRpdWaterMessageT wm = null;
		if(WATERMESSAGEID != null && !"".equals(WATERMESSAGEID)){
			watMessage = reportMessageService.SreachWaterMessageT(WATERMESSAGEID, "", "");
		}else{
			
			obj = CommonsUtil.getRrturnJson("","未获取到水源井运行日报数据ID" ,"", null, null);
			out.print(obj);
			return null;
		}
		
		if(watMessage != null && watMessage.size()>0){
			wm = watMessage.get(0);
		}else{
			obj = CommonsUtil.getRrturnJson("","未获取到水源井运行日报数据ID对应数据" ,"", null, null);
			out.print(obj);
			return null;
		}
		wm.setShr(user1.getOperName());
		wm.setShsj(new Date());

		boolean updateflg = false;
		try {
			updateflg = reportMessageService.updateWaterMessage(wm);
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
				PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "7", "水源井报表", wm.getWaterMessageId());
				logService.addLog(log);
			} catch (Exception e) {
				e.printStackTrace();
				obj = CommonsUtil.getRrturnJson("","水源井报表日志修改失败" ,"", null, null);
			}
		}
		out.print(obj);
		return null;
	}
    
	public String exportExcel() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		String txtDate = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("txtDate")));
		if ("".equals(txtDate)) {
			txtDate = DateBean.getSystemTime1();
//			return null;
		}
		PropertiesConfig pc = new PropertiesConfig();
		String fields = pc.getSystemConfiguration("param");
		String[] inseParam = pc.getSystemConfiguration("inseParam").split(",");
		
		String paramSHR = pc.getSystemConfiguration("paramSHR");
		String inseSHR = pc.getSystemConfiguration("inseSHR");
		
		String paramRQ = pc.getSystemConfiguration("paramRQ");
		String inserq = pc.getSystemConfiguration("inserq");
		
		String templetFilePath = request.getSession().getServletContext().getRealPath("/") + "exceltemplet\\ExportRBWH\\水源井日报.xls";
		HSSFWorkbook wb = ExcelToHtmlUtils.loadXls( new File( templetFilePath ) );
		HSSFSheet sheet = wb.getSheetAt(0);
		
		String[] syjId = pc.getSystemConfiguration("syjId").split(",");
		for (int i = 0; i < syjId.length; i++) {
			String jid = syjId[i];
			String insPos = inseParam[i];
			List<Object[]> waterList = syjyxrbService.searchDataEXcep(txtDate, fields,jid);
			
			if (waterList != null && waterList.size() > 0) {
				//调用插入数据样式的方法
				U2DataExportUtil.insertDataByPosition(waterList, wb, sheet, insPos);
			}
		}

		List<PcRpdWaterMessageT> 	waterMess = reportMessageService.SreachWaterMessageT("", "水源井运行日报", txtDate);
		if (waterMess != null && waterMess.size() > 0) {
			//调用插入数据样式的方法
			//U2DataExportUtil.insertExcelData(waterMess, wb, sheet, paramSHR);
			U2DataExportUtil.insertExcelData(wb, sheet, inseSHR, 0, new Object[]{waterMess.get(0).getShr()});
			U2DataExportUtil.insertExcelData(wb, sheet, inserq, 0, new Object[]{DateBean.getChinaDate(waterMess.get(0).getRq().toString())});
		}
		OutputStreamAndCloseBaos(U2DataExportUtil.ExporDataStream(wb));
		return "excel";
	}
}
