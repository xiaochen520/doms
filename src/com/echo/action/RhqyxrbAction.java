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
import com.echo.dto.PcRpdWaterMessageT;
import com.echo.dto.User;
import com.echo.service.LogService;
import com.echo.service.ReportMessageService;
import com.echo.service.RhqyxrbService;
import com.echo.util.AuthorityUtil;
import com.echo.util.CommonsUtil;
import com.echo.util.DateBean;
import com.echo.util.PropertiesConfig;
import com.echo.util.ReportFormsBaseUitl;
import com.echo.util.StringUtil;
import com.echo.util.U2DataExportUtil;

@SuppressWarnings("serial")
public class RhqyxrbAction extends ReportFormsBaseUitl{
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
				butJson = AuthorityUtil.getButtonJson("MENU133", user);
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
				syjyx = rhqyxrbService.searchSyjyxrb(clz,rhq,txtDate,setRHQ);
				if(syjyx != null && syjyx.size()>0){
					jsonobj = new JSONObject();
					jsonobj.put("syjyx", syjyx);
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
			
			//周期制水量
			String waterA = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("waterA")));
			String waterB = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("waterB")));
			String waterC = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("waterC")));
			String waterD = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("waterD")));
			
			
			// 设备再生记录
			String LOG1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("LOG1")));
			String LOG2 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("LOG2")));
			String LOG3 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("LOG3")));
			 //白班 夜班记事
  			String	BZ1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("BZ1")));
  			String	BZ2 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("BZ2")));
  			 //白班 夜班 填报人
  			String	TBR1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("TBR1")));
  			String	TBR2 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("TBR1")));
  			
  			String WATERMESSAGEID = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("WATERMESSAGEID")));
  			
  			String clz = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("clz")));
  			String rhq = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("rhq")));
  			
  			String StaName = clz;
  			String rhqName =clz +rhq+"号软化器运行日报";
  			
  			
  			String RQ = DateBean.getChinaDateD(StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("RQ"))));
  			
			String firstStr = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("firstStr")));
			firstStr = firstStr.substring(0,firstStr.length()-1);
			String[] firstList = firstStr.split(";",-1);
			for(int i=0;i<firstList.length;i++){
				String[] firstPra = firstList[i].split(",",-1);
				if(!"".equals(CommonsUtil.getClearNullData(firstPra[0]))){
					String rpdid = firstPra[0];
					
					List<PcRpdMollifierT> wtList = null;
					PcRpdMollifierT wt = null;
					if(rpdid != null && !"".equals(rpdid)){
						try {
							wtList = rhqyxrbService.SreachCheckOn(rpdid, "","");
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
						wt = new PcRpdMollifierT();
					}
					String beforetime = DateBean.getBeforeDAYTime(RQ);
					SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
					int calcNum = rhqyxrbService.searchCalcNum();
					int zeroIndex = -calcNum/2;
					if(i>=zeroIndex){
						wt.setBbsj(sf.parse(RQ +" "+ firstPra[1]));
					}else{
						wt.setBbsj(sf.parse(beforetime +" "+ firstPra[1]));
					}
					wt.setSclzmc(StaName);
					wt.setSbbh(rhq);
					wt.setSclAhardness1(CommonsUtil.isNullOrEmpty(firstPra[2]));
					wt.setSclAhardness2(CommonsUtil.isNullOrEmpty(firstPra[3]));
					wt.setSclAll(CommonsUtil.isNullOrEmpty(firstPra[4]));
					wt.setSclAlllj(CommonsUtil.isNullOrEmpty(firstPra[5]));
					
					wt.setSclBhardness1(CommonsUtil.isNullOrEmpty(firstPra[6]));
					wt.setSclBhardness2(CommonsUtil.isNullOrEmpty(firstPra[7]));
					wt.setSclBll(CommonsUtil.isNullOrEmpty(firstPra[8]));
					wt.setSclBlllj(CommonsUtil.isNullOrEmpty(firstPra[9]));
					
					wt.setSclChardness1(CommonsUtil.isNullOrEmpty(firstPra[10]));
					wt.setSclChardness2(CommonsUtil.isNullOrEmpty(firstPra[11]));
					wt.setSclCll(CommonsUtil.isNullOrEmpty(firstPra[12]));
					wt.setSclClllj(CommonsUtil.isNullOrEmpty(firstPra[13]));
					
					wt.setSclDhardness1(CommonsUtil.isNullOrEmpty(firstPra[14]));
					wt.setSclDhardness2(CommonsUtil.isNullOrEmpty(firstPra[15]));
					wt.setSclDll(CommonsUtil.isNullOrEmpty(firstPra[16]));
					wt.setSclDlllj(CommonsUtil.isNullOrEmpty(firstPra[17]));
			
					
	         		boolean updateflg = false;
	        		try {
	        			updateflg = rhqyxrbService.updateData(wt);
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
	    					PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "3", "软化器", wt.getRpdMollifierId());
	    					logService.addLog(log);
	    				} catch (Exception e) {
	    					e.printStackTrace();
	    					obj = CommonsUtil.getRrturnJson("",rhqName+"日志修改失败" ,"", null, null);
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
					
					meList = reportMessageService.SreachWaterMessages("", rhqName, RQ);
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
			reportM.setRq(DateBean.getStrDate(RQ));
			reportM.setBbmc(reportM.getBbmc());
			reportM.setTbr1(TBR1);
			reportM.setTbr2(TBR2);
			reportM.setBz1(BZ1);
			reportM.setBz2(BZ2);
			reportM.setSituationLog1(LOG1);
			reportM.setSituationLog2(LOG2);
			reportM.setSituationLog3(LOG3);
			reportM.setZsllA(waterA);
			reportM.setZsllB(waterB);
			reportM.setZsllC(waterC);
			reportM.setZsllD(waterD);
			
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
					PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "3", "软化器", reportM.getWaterMessageId());
					logService.addLog(log);
				} catch (Exception e) {
					e.printStackTrace();
					obj = CommonsUtil.getRrturnJson("",rhqName+"修改日志添加失败" ,"", null, null);
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
		String clz = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("clz1")));
		String rhq = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("rhq")));
		
		String rhqName =clz +rhq+"号软化器运行日报";
		List<PcRpdWaterMessageT> watMessage = null;
		PcRpdWaterMessageT wm = null;
		if(WATERMESSAGEID != null && !"".equals(WATERMESSAGEID)){
			watMessage = reportMessageService.SreachWaterMessageT(WATERMESSAGEID, "", "");
		}else{
			
			obj = CommonsUtil.getRrturnJson("",rhqName +"运行日报数据ID获取失败" ,"", null, null);
			out.print(obj);
			return null;
		}
		
		if(watMessage != null && watMessage.size()>0){
			wm = watMessage.get(0);
		}else{
			obj = CommonsUtil.getRrturnJson("",rhqName +"运行日报数据ID获取失败" ,"", null, null);
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
				PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "7", "软化器报表", wm.getWaterMessageId());
				logService.addLog(log);
			} catch (Exception e) {
				e.printStackTrace();
				obj = CommonsUtil.getRrturnJson("","软化器报表日志修改失败" ,"", null, null);
			}
		}
		out.print(obj);
		return null;
	}
    
	public String exportExcel() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		String txtDate = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("txtDate")));
		String clz = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("clz")));
		String rhq = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("rhq")));
		String WATERMESSAGEID = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("WATERMESSAGEID")));
		
		String sclzmc = clz;
		//Date rq =  DateBean.getStrDate(txtDate);
	
		if ("".equals(txtDate)) {
			txtDate = DateBean.getSystemTime1();
//			return null;
		}
		PropertiesConfig pc = new PropertiesConfig();
		
		String fields = pc.getSystemConfiguration("RinsZHDataSql");
		
		String insTabHe = pc.getSystemConfiguration("RinsTabHe");
		String TabHeaName ="风城油田作业区供汽联合站"+ clz +rhq+ "号软化器运行日报 ";
		
		String insMain = pc.getSystemConfiguration("RinsMain");
		String insMainSql = pc.getSystemConfiguration("RinsMainSql");
		
	
		
		String templetFilePath = request.getSession().getServletContext().getRealPath("/") + "exceltemplet\\ExportRBWH\\软化器运行日报.xls";
		HSSFWorkbook wb = ExcelToHtmlUtils.loadXls( new File( templetFilePath ) );
		HSSFSheet sheet = wb.getSheetAt(0);
		
		U2DataExportUtil.insertExcelTableHead( wb, sheet, insTabHe ,TabHeaName);
		
		List<Object[]> list = rhqyxrbService.searchExportData(txtDate, insMainSql,sclzmc,rhq);
		if (list != null && list.size() > 0) {
			//调用插入数据样式的方法
			
			U2DataExportUtil.insertDataByPosition(list, wb, sheet, insMain);
		}
		
		List<Object[]> zList=null;
		String punSql = "select "+fields+" from pc_rpd_water_message_t a  where  a.rq=to_date('"+txtDate+"' ,'YYYY-MM-DD')  and a.water_message_id='"+WATERMESSAGEID+"'";
			zList = rhqyxrbService.searchExportData(punSql);
		if (zList != null && zList.size() > 0) {
			// 获取单位、值班人、审核人、日期、备注数据插入位置
			//String[] basePostion = pc.getSystemConfiguration( "U1ComInsert" )
			String[] insZHDataPosit = pc.getSystemConfiguration("RinsZHData").split(",");
			for (int j = 0; j < insZHDataPosit.length; j++) {
				if (zList.get(0)[j] != null) {
					U2DataExportUtil.insertExcelData(wb, sheet, insZHDataPosit[j], 0, new Object[]{zList.get(0)[j]});
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
