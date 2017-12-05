package com.echo.action;

import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
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
import com.echo.dto.PcRpdMollifierT;
import com.echo.dto.PcRpdRhsclT2T;
import com.echo.dto.PcRpdWaterMessageT;
import com.echo.dto.User;
import com.echo.service.LogService;
import com.echo.service.ReportMessageService;
import com.echo.service.RhqyxrbService;
import com.echo.service.T2sclrbService;
import com.echo.util.AuthorityUtil;
import com.echo.util.CommonsUtil;
import com.echo.util.DateBean;
import com.echo.util.PropertiesConfig;
import com.echo.util.ReportFormsBaseUitl;
import com.echo.util.StringUtil;
import com.echo.util.U2DataExportUtil;

public class T2sclrbAction extends ReportFormsBaseUitl{
		private T2sclrbService t2sclrbService;
		private ReportMessageService reportMessageService;
		
		public void setT2sclrbService(T2sclrbService t2sclrbService) {
			this.t2sclrbService = t2sclrbService;
		}
		HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
		
		private final String fileName = "特二联水处理日报 .xls";
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
				butJson = AuthorityUtil.getButtonJson("MENU136", user);
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
		
		public String searchDatas()throws Exception{
			HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
			HttpServletResponse response=ServletActionContext.getResponse();//响应对象
			response.setCharacterEncoding("utf-8");
			response.setHeader("ContentType","text/xml");
			PrintWriter out = response.getWriter();
			JSONArray dataArr = null;
			JSONObject obj = null;
			JSONObject jsonobj = null;
			String txtDate = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("txtDate")));
			
			
			String[] waterMessage = new String[9];
			List<PcRpdWaterMessageT> waterMess = null;
			PcRpdWaterMessageT rm = null;
			if(txtDate == null || "".equals(txtDate)){
				txtDate = DateBean.getSystemTime1();
			}
			
			try {
				dataArr = t2sclrbService.searchDataSet(txtDate);
				if(dataArr != null && dataArr.size()>0){
					jsonobj = new JSONObject();
					jsonobj.put("dataArr", dataArr);
				}
//				waterArr = rhqyxrbService.searchWaterMany(clz,rhq,txtDate);
//				if(waterArr != null && waterArr.size()>0){
//					waterObj = new JSONObject();
//					waterObj.put("waterArr", waterArr);
//				}
			} catch (Exception e) {
				e.printStackTrace();
				obj = CommonsUtil.getRrturnJson("","当前日期搜索失败请选择其他日期" ,"", null, null);
				out.print(obj);
				return null;
			}
			try {
				waterMess = reportMessageService.SreachWaterMessageT("", "特二联水处理日报", txtDate);
				if(waterMess != null && waterMess.size()>0){
					rm = waterMess.get(0);
					
				}else{
					rm = new PcRpdWaterMessageT();
					rm.setRq(DateBean.getStrDate(txtDate));
					rm.setBbmc("特二联水处理日报");
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
					waterMess = reportMessageService.SreachWaterMessageT("", "特二联水处理日报" , txtDate);
					rm = waterMess.get(0);
					if(updateflg){
						//添加系统LOG
						try {
							User user1 = (User) session.getAttribute("userInfo");
							PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "1", "软化器报表", rm.getWaterMessageId());
							logService.addLog(log);
						} catch (Exception e) {
							e.printStackTrace();
							obj = CommonsUtil.getRrturnJson("","特二联水处理日报日志添加失败" ,"", null, null);
						}
					}
				}
				
				obj = new JSONObject();
				obj.put("WATERMESSAGEID", rm.getWaterMessageId());
				obj.put("BBMC", rm.getBbmc());
				obj.put("RQ", DateBean.getChinaDate(txtDate));
			
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
			
				//车号1
				if(rm.getCarNo1() !=null && !rm.getCarNo1().equals("")){
					obj.put("CAR_NO1", rm.getCarNo1());
				}else{
					obj.put("CAR_NO1", "");
				}
				if(rm.getCarNo2() !=null && !rm.getCarNo2().equals("")){
					obj.put("CAR_NO2", rm.getCarNo2());
				}else{
					obj.put("CAR_NO2", "");
				}
				if(rm.getCarNo3() !=null && !rm.getCarNo3().equals("")){
					obj.put("CAR_NO3", rm.getCarNo3());
				}else{
					obj.put("CAR_NO3", "");
				}
				if(rm.getCarNo4() !=null && !rm.getCarNo4().equals("")){
					obj.put("CAR_NO4", rm.getCarNo4());
				}else{
					obj.put("CAR_NO4", "");
				}
//					obj.put("CAR_NO1", CommonsUtil.delTrim(rm.getCarNo1()));
//					obj.put("CAR_NO2", CommonsUtil.delTrim(rm.getCarNo2()));
//					obj.put("CAR_NO3", CommonsUtil.delTrim(rm.getCarNo3()));
//					obj.put("CAR_NO4", CommonsUtil.delTrim(rm.getCarNo4()));
//					
				//车载重量1
				if(rm.getCarWeight1() !=null && !"".equals(rm.getCarWeight1())){
					obj.put("CAR_WEIGHT1", rm.getCarWeight1()); 
				}else{
					obj.put("CAR_WEIGHT1", ""); 
				}
				if(rm.getCarWeight2() !=null && !"".equals(rm.getCarWeight2())){
					obj.put("CAR_WEIGHT2", rm.getCarWeight2()); 
				}else{
					obj.put("CAR_WEIGHT2", ""); 
				}
				if(rm.getCarWeight3() !=null && !"".equals(rm.getCarWeight3())){
					obj.put("CAR_WEIGHT3", rm.getCarWeight3()); 
				}else{
					obj.put("CAR_WEIGHT3", ""); 
				}
				if(rm.getCarWeight4() !=null && !"".equals(rm.getCarWeight4())){
					obj.put("CAR_WEIGHT4", rm.getCarWeight4()); 
				}else{
					obj.put("CAR_WEIGHT4", ""); 
				}
//					obj.put("CAR_WEIGHT1", CommonsUtil.getBigDecimalData(rm.getCarWeight1())); 
//					obj.put("CAR_WEIGHT2", CommonsUtil.getBigDecimalData(rm.getCarWeight2()));
//					obj.put("CAR_WEIGHT3", CommonsUtil.getBigDecimalData(rm.getCarWeight3()));
//					obj.put("CAR_WEIGHT4", CommonsUtil.getBigDecimalData(rm.getCarWeight4()));
//				//	亚硫酸钠加药1
				if( rm.getSodiumsDosing1() !=null && !"".equals(rm.getSodiumsDosing1())){
					obj.put("SODIUMS_DOSING1", rm.getSodiumsDosing1());
				}else{
					obj.put("SODIUMS_DOSING1","");
				}
				if( rm.getSodiumsDosing2() !=null && !"".equals(rm.getSodiumsDosing2())){
					obj.put("SODIUMS_DOSING2", rm.getSodiumsDosing2());
				}else{
					obj.put("SODIUMS_DOSING2","");
				}
				if( rm.getSodiumsDosing3() !=null && !"".equals(rm.getSodiumsDosing3())){
					obj.put("SODIUMS_DOSING3", rm.getSodiumsDosing3());
				}else{
					obj.put("SODIUMS_DOSING3","");
				}
				if( rm.getSodiumsDosing4() !=null && !"".equals(rm.getSodiumsDosing4())){
					obj.put("SODIUMS_DOSING4", rm.getSodiumsDosing4());
				}else{
					obj.put("SODIUMS_DOSING4","");
				}
				if( rm.getSodiumsDosing5() !=null && !"".equals(rm.getSodiumsDosing5())){
					obj.put("SODIUMS_DOSING5", rm.getSodiumsDosing5());
				}else{
					obj.put("SODIUMS_DOSING5","");
				}
//					obj.put("SODIUMS_DOSING1", CommonsUtil.delTrim(rm.getSodiumsDosing1()));
//					obj.put("SODIUMS_DOSING2", CommonsUtil.delTrim(rm.getSodiumsDosing2()));
//					obj.put("SODIUMS_DOSING3", CommonsUtil.delTrim(rm.getSodiumsDosing3()));
//					obj.put("SODIUMS_DOSING4", CommonsUtil.delTrim(rm.getSodiumsDosing4()));
//					obj.put("SODIUMS_DOSING5", CommonsUtil.delTrim(rm.getSodiumsDosing5()));
//				//加药重量1
				if( rm.getDosWeight1() !=null &&  !"".equals(rm.getDosWeight1())){
					obj.put("DOS_WEIGHT1", rm.getDosWeight1());
				}else{
					obj.put("DOS_WEIGHT1", "");
				}
				if( rm.getDosWeight2() !=null &&  !"".equals(rm.getDosWeight2())){
					obj.put("DOS_WEIGHT2", rm.getDosWeight2());
				}else{
					obj.put("DOS_WEIGHT2", "");
				}
				if( rm.getDosWeight3() !=null &&  !"".equals(rm.getDosWeight3())){
					obj.put("DOS_WEIGHT3", rm.getDosWeight3());
				}else{
					obj.put("DOS_WEIGHT3", "");
				}
				if( rm.getDosWeight4() !=null &&  !"".equals(rm.getDosWeight4())){
					obj.put("DOS_WEIGHT4", rm.getDosWeight4());
				}else{
					obj.put("DOS_WEIGHT4", "");
				}
				if( rm.getDosWeight5() !=null &&  !"".equals(rm.getDosWeight5())){
					obj.put("DOS_WEIGHT5", rm.getDosWeight5());
				}else{
					obj.put("DOS_WEIGHT5", "");
				}
//					obj.put("DOS_WEIGHT1", CommonsUtil.getBigDecimalData(rm.getDosWeight1()));
//					obj.put("DOS_WEIGHT2", CommonsUtil.getBigDecimalData(rm.getDosWeight2()));
//					obj.put("DOS_WEIGHT3", CommonsUtil.getBigDecimalData(rm.getDosWeight3()));
//					obj.put("DOS_WEIGHT4", CommonsUtil.getBigDecimalData(rm.getDosWeight4()));
//					obj.put("DOS_WEIGHT5", CommonsUtil.getBigDecimalData(rm.getDosWeight5()));
					
				jsonobj.put("WATERMESSAGE", obj);
				
			} catch (Exception e) {
				e.printStackTrace();
				obj = CommonsUtil.getRrturnJson("","当前日期搜索失败请选择其他日期" ,"", null, null);
				out.print(obj);
				return null;
			}
			//JSONObject treeObj = new JSONObject();
			//treeObj.putAll(jsonobj);
			//treeObj.putAll(waterObj);
			obj = CommonsUtil.getRrturnJson("","" ,"", jsonobj, null);
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
			
			//车号
			String	CAR_NO1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("CAR_NO1")));
			String	CAR_NO2 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("CAR_NO2")));
			String	CAR_NO3 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("CAR_NO3")));
			String	CAR_NO4 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("CAR_NO4")));
			//重量
			String	CAR_WEIGHT1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("CAR_WEIGHT1")));
			String	CAR_WEIGHT2 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("CAR_WEIGHT2")));
			String	CAR_WEIGHT3 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("CAR_WEIGHT3")));
			String	CAR_WEIGHT4 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("CAR_WEIGHT4")));
			//加药
			String	SODIUMS_DOSING1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("SODIUMS_DOSING1")));
			String	SODIUMS_DOSING2 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("SODIUMS_DOSING2")));
			String	SODIUMS_DOSING3 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("SODIUMS_DOSING3")));
			String	SODIUMS_DOSING4 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("SODIUMS_DOSING4")));
			String	SODIUMS_DOSING5 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("SODIUMS_DOSING5")));

			//重量
			String	DOS_WEIGHT1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("DOS_WEIGHT1")));
			String	DOS_WEIGHT2 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("DOS_WEIGHT2")));
			String	DOS_WEIGHT3 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("DOS_WEIGHT3")));
			String	DOS_WEIGHT4 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("DOS_WEIGHT4")));
			String	DOS_WEIGHT5 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("DOS_WEIGHT5")));

			 //白班 夜班记事
  			String	BZ1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("BZ1")));
  			String	BZ2 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("BZ2")));
  			 //白班 夜班 填报人
  			String	TBR1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("TBR1")));
  			String	TBR2 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("TBR1")));
  			
  			String WATERMESSAGEID = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("WATERMESSAGEID")));
  			
  			String RQ =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("txtDate")));
  			
			String firstStr = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("firstStr")));
			firstStr = firstStr.substring(0,firstStr.length()-1);
			String[] firstList = firstStr.split(";",-1);
			for(int i=0;i<firstList.length;i++){
				String[] firstPra = firstList[i].split(",",-1);
				 if(!"".equals(CommonsUtil.getClearNullData(firstPra[0]))){
					String rpdid = firstPra[0];
					List<PcRpdRhsclT2T> wtList = null;
					PcRpdRhsclT2T wt = null;
					if(rpdid != null && !"".equals(rpdid)){
						try {
							wtList = t2sclrbService.SreachCheckOn(rpdid,"");
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					if(wtList != null && wtList.size()>0){
						wt = wtList.get(0);
						
					}else{
//						obj = CommonsUtil.getRrturnJson("","第"+(i+1)+" 行数据报表修改失败，没有此时的您要修改的数据" ,"", null, null);
//						out.print(obj);
//						return null;
						wt = new PcRpdRhsclT2T();
					}
					String beforetime = DateBean.getBeforeDAYTime(RQ);
					SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
					int calcNum = t2sclrbService.searchCalcNum();
					int zeroIndex = -calcNum/2;
					if(i>=zeroIndex){
						wt.setBbsj(sf.parse(RQ +" "+ firstPra[1]));
					}else{
						wt.setBbsj(sf.parse(beforetime +" "+ firstPra[1]));
					}
					
					wt.setRhqzcsl(CommonsUtil.isNullOrEmpty(firstPra[2]));
					wt.setCyqzcsl(CommonsUtil.isNullOrEmpty(firstPra[3]));
					wt.setZybzcsl(CommonsUtil.isNullOrEmpty(firstPra[4]));
					wt.setLt30101a(CommonsUtil.isNullOrEmpty(firstPra[5]));
					
					wt.setLt30101b(CommonsUtil.isNullOrEmpty(firstPra[6]));
					wt.setLt30102a(CommonsUtil.isNullOrEmpty(firstPra[7]));
					wt.setLt30102b(CommonsUtil.isNullOrEmpty(firstPra[8]));
					wt.setLt30301(CommonsUtil.isNullOrEmpty(firstPra[9]));
					
					wt.setLt30302(CommonsUtil.isNullOrEmpty(firstPra[10]));
					wt.setZybyl1(CommonsUtil.isNullOrEmpty(firstPra[11]));
					wt.setZybyl2(CommonsUtil.isNullOrEmpty(firstPra[12]));
					wt.setYskqyl1(CommonsUtil.isNullOrEmpty(firstPra[13]));
					wt.setYskqyl2(CommonsUtil.isNullOrEmpty(firstPra[14]));
					
					wt.setHardness(CommonsUtil.isNullOrEmpty(firstPra[15]));
					wt.setOxygenBearing(CommonsUtil.isNullOrEmpty(firstPra[16]));
					wt.setChloride(CommonsUtil.isNullOrEmpty(firstPra[17]));
					wt.setBasicity(CommonsUtil.isNullOrEmpty(firstPra[18]));
			
					
	         		boolean updateflg = false;
	        		try {
	        			updateflg = t2sclrbService.updateData(wt);
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
	        		//if(!updateflg){
	        			//obj = CommonsUtil.getRrturnJson("","第"+(i+1)+"行修改失败" ,"", null, null);
	        		//}
	        		if(updateflg){
	    				//添加系统LOG
	    				try {
	    					User user1 = (User) session.getAttribute("userInfo");
	    					PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "3", "特二联", wt.getRpdRhsclT2Id());
	    					logService.addLog(log);
	    				} catch (Exception e) {
	    					e.printStackTrace();
	    					obj = CommonsUtil.getRrturnJson("","特二联水处理日报表日志修改失败" ,"", null, null);
	    				}
	    			}
				
			}
			}
			//PcRdScl2T
			
			List<PcRpdWaterMessageT> meList = null;
			PcRpdWaterMessageT reportM = null;
			if(WATERMESSAGEID !=null && !"".equals(WATERMESSAGEID)){
				meList = reportMessageService.SreachWaterMessages(WATERMESSAGEID, "", "");
			}
		
			if(meList != null && meList.size()>0){
				reportM = meList.get(0);
			}else{
				if(RQ != null && !"".equals(RQ)){
					
					meList = reportMessageService.SreachWaterMessages("", "特二联水处理日报", RQ);
				}else{
					obj = CommonsUtil.getRrturnJson("","系统无当前日期，请重新选择数据" ,"", null, null);
					out.print(obj);
					return null;
				}
				
				if(meList != null && meList.size()>0){
					obj = CommonsUtil.getRrturnJson("","系统已存在当前报表日报记录，请选择其他日期" ,"", null, null);
					out.print(obj);
					return null;
				}else{
					reportM = new PcRpdWaterMessageT();
				}
			}
			reportM.setRq(reportM.getRq());
			reportM.setBbmc(reportM.getBbmc());
			reportM.setTbr1(TBR1);
			reportM.setTbr2(TBR2);
			reportM.setBz1(BZ1);
			reportM.setBz2(BZ2);
			reportM.setCarNo1(CAR_NO1);
			reportM.setCarNo2(CAR_NO2);
			reportM.setCarNo3(CAR_NO3);
			reportM.setCarNo4(CAR_NO4);
			reportM.setCarWeight1(CommonsUtil.getBigDecimalData(CAR_WEIGHT1));
			reportM.setCarWeight2(CommonsUtil.getBigDecimalData(CAR_WEIGHT2));
			reportM.setCarWeight3(CommonsUtil.getBigDecimalData(CAR_WEIGHT3));
			reportM.setCarWeight4(CommonsUtil.getBigDecimalData(CAR_WEIGHT4));
			reportM.setSodiumsDosing1(SODIUMS_DOSING1);
			reportM.setSodiumsDosing2(SODIUMS_DOSING2);
			reportM.setSodiumsDosing3(SODIUMS_DOSING3);
			reportM.setSodiumsDosing4(SODIUMS_DOSING4);
			reportM.setSodiumsDosing5(SODIUMS_DOSING5);
			reportM.setDosWeight1(CommonsUtil.getBigDecimalData(DOS_WEIGHT1));
			reportM.setDosWeight2(CommonsUtil.getBigDecimalData(DOS_WEIGHT2));
			reportM.setDosWeight3(CommonsUtil.getBigDecimalData(DOS_WEIGHT3));
			reportM.setDosWeight4(CommonsUtil.getBigDecimalData(DOS_WEIGHT4));
			reportM.setDosWeight5(CommonsUtil.getBigDecimalData(DOS_WEIGHT5));
			
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
					PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "3", "特二联", reportM.getWaterMessageId());
					logService.addLog(log);
				} catch (Exception e) {
					e.printStackTrace();
					obj = CommonsUtil.getRrturnJson("","特二联水处理日报数据修改日志添加失败" ,"", null, null);
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
			
			obj = CommonsUtil.getRrturnJson("","特二联水处理日报数据ID获取失败" ,"", null, null);
			out.print(obj);
			return null;
		}
		
		if(watMessage != null && watMessage.size()>0){
			wm = watMessage.get(0);
		}else{
			obj = CommonsUtil.getRrturnJson("","特二联水处理日报 数据ID获取失败" ,"", null, null);
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
		
		if(!updateflg){
			//添加系统LOG
			try {
				PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "7", "特二联报表", wm.getWaterMessageId());
				logService.addLog(log);
			} catch (Exception e) {
				e.printStackTrace();
				obj = CommonsUtil.getRrturnJson("","特二联水处理日报表日志修改失败" ,"", null, null);
			}
		}
		out.print(obj);
		return null;
	}
    
	public String exportExcel() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		String txtDate = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("txtDate")));
		String WATERMESSAGEID = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("WATERMESSAGEID")));
		
	
		if ("".equals(txtDate)) {
			txtDate = DateBean.getSystemTime1();
//			return null;
		}
		PropertiesConfig pc = new PropertiesConfig();
		
		
		
		String JZSCLMain = pc.getSystemConfiguration("T2LMain");
		String JZSCLMainSql = pc.getSystemConfiguration("T2LMainSql");
		
		String T2LZHDataSql = pc.getSystemConfiguration("T2LZHDataSql");
		
	
		
		String templetFilePath = request.getSession().getServletContext().getRealPath("/") + "exceltemplet\\ExportRBWH\\特二联水处理日报.xls";
		HSSFWorkbook wb = ExcelToHtmlUtils.loadXls( new File( templetFilePath ) );
		HSSFSheet sheet = wb.getSheetAt(0);
		
		List<Object[]> list = t2sclrbService.searchExportData(txtDate, JZSCLMainSql);
		if (list != null && list.size() > 0) {
			//调用插入数据样式的方法
			
			U2DataExportUtil.insertDataByPosition(list, wb, sheet, JZSCLMain);
		}
		
		List<Object[]> zList=null;
		String punSql = "select "+T2LZHDataSql+" from pc_rpd_water_message_t a  where  a.rq=to_date('"+txtDate+"' ,'YYYY-MM-DD')  and a.water_message_id='"+WATERMESSAGEID+"'";
			zList = t2sclrbService.searchExportData(punSql);
		if (zList != null && zList.size() > 0) {
			// 获取单位、值班人、审核人、日期、备注数据插入位置
			//String[] basePostion = pc.getSystemConfiguration( "U1ComInsert" )
			String[] T2LZHData = pc.getSystemConfiguration("T2LZHData").split(",");
			for (int j = 0; j < T2LZHData.length; j++) {
				if (zList.get(0)[j] != null) {
					U2DataExportUtil.insertExcelData(wb, sheet, T2LZHData[j], 0, new Object[]{zList.get(0)[j]});
				}
			}
		}
		
//		List<PcRpdWaterMessageT> 	waterMess = reportMessageService.SreachWaterMessageT("", "水源井运行日报", txtDate);
//		if (waterMess != null && waterMess.size() > 0) {
//			//调用插入数据样式的方法
//			//U2DataExportUtil.insertExcelData(waterMess, wb, sheet, paramSHR);
//			U2DataExportUtil.insertExcelData(wb, sheet, inseSHR, 0, new Object[]{waterMess.get(0).getShr()});
//			U2DataExportUtil.insertExcelData(wb, sheet, inserq, 0, new Object[]{DateBean.getChinaDate(waterMess.get(0).getRq().toString())});
//		}
		OutputStreamAndCloseBaos(U2DataExportUtil.ExporDataStream(wb));
		return "excel";
	}
}
