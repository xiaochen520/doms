package com.echo.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

import com.echo.dto.PcRdLoginfoT;
import com.echo.dto.PcRpdUThinOilAutoT;
import com.echo.dto.PcRpdUThinOilGeneralT;
import com.echo.dto.User;
import com.echo.service.LogService;
import com.echo.service.U1s1gyService;
import com.echo.service.XyyyclService;
import com.echo.util.AuthorityUtil;
import com.echo.util.CommonsUtil;
import com.echo.util.DateBean;
import com.echo.util.PropertiesConfig;
import com.echo.util.ReportFormsBaseUitl;
import com.echo.util.StringUtil;
import com.echo.util.U2DataExportUtil;

public class XyyyclAction extends ReportFormsBaseUitl {
	private static final long serialVersionUID = 12L;
	HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
	private XyyyclService xyyyclService;
	private  U1s1gyService u1s1gyService;
	private LogService logService;
	public void setXyyyclService(XyyyclService xyyyclService) {
		this.xyyyclService = xyyyclService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	
	public void setU1s1gyService(U1s1gyService u1s1gyService) {
		this.u1s1gyService = u1s1gyService;
	}

	private final String fileName = "稀油注输联合站原油处理日报表.xls";
	//
	public String getFileName() {
		return super.getFileName(fileName);
	}
	public String searchXyyycl() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONObject jsonobj = new JSONObject();
		String txtDate = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("txtDate")));
	
		JSONObject obj = new JSONObject();
		if(txtDate == null || "".equals(txtDate)){
			txtDate = DateBean.getSystemTime1();
		}
		
		try {
			jsonobj = xyyyclService.searchZHRB(txtDate);
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
	
	//根据权限进行页面初始化
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
			butJson = AuthorityUtil.getButtonJson("MENU091", user);
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
	
	public String updateOil() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONObject obj = new JSONObject();
		String id = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("RPD_U_THIN_OIL_GENERAL_ID")));
		String  zbr = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("ZBR")));
		String bz   = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("BZ")));
		String rq   = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("BBRQ")));
		
		//一段    加药量
		String ydjyl  = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("YDJYL")));
		String ezyw  = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("EZYW")));
		String xzjyw  = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("XZJYW")));
		String jyl  = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("JYL")));
		String edjyl  = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("EDJYL")));
		String ezcq  = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("EZCQ")));
		String xzjbsq  = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("XZJBSQ")));
		String wehjql  = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("WEHJQL")));
		String zyql  = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("ZYQL")));
		String xycs  = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("XYCS")));
		String xzjgq  = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("XZJGQ")));
		String xzjjql  = StringUtil.toStr(StringUtil.isNullStr(request.getParameter("XZJJQL")));
		
		
		List<PcRpdUThinOilGeneralT> list = null;
		PcRpdUThinOilGeneralT  oil =null;
		try {
			 list = xyyyclService.SreachOilGeneral(id, "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(list != null && list.size()>0){
			oil = list.get(0);
		}else{
		
//				obj = CommonsUtil.getRrturnJson("","稀油联合站油综合信息ID获取失败" ,"", null, null);
//				out.print(obj);
			oil=new PcRpdUThinOilGeneralT();
			
		}
		oil.setRpdUThinOilGeneralId(id);
		oil.setZbr(zbr);
		oil.setBz(bz);
		oil.setBbrq(DateBean.getStrDate(rq));
		oil.setYdjyl(CommonsUtil.isNullOrEmpty(ydjyl));
		oil.setEzyw(CommonsUtil.isNullOrEmpty(ezyw));
		oil.setXzjyw(CommonsUtil.isNullOrEmpty(xzjyw));
		oil.setJyl(CommonsUtil.isNullOrEmpty(jyl));
		oil.setEdjyl(CommonsUtil.isNullOrEmpty(edjyl));
		oil.setEzcq(CommonsUtil.isNullOrEmpty(ezcq));
		oil.setXzjbsq(CommonsUtil.isNullOrEmpty(xzjbsq));
		oil.setWehjql(CommonsUtil.isNullOrEmpty(wehjql));
		oil.setZyql(CommonsUtil.isNullOrEmpty(zyql));
		oil.setXycs(CommonsUtil.isNullOrEmpty(xycs));
		oil.setXzjgq(CommonsUtil.isNullOrEmpty(xzjgq));
		oil.setXzjjql(CommonsUtil.isNullOrEmpty(xzjjql));

		boolean  updateflg = false;
		try {
			updateflg = xyyyclService.updateOilGeneral(oil);
		} catch (Exception e) {
			String errmsg  = e.getClass().toString();
			
			if(!"".equals(errmsg) && errmsg.indexOf("ORA-01438") != -1){
				obj = CommonsUtil.getRrturnJson("","您输入信息超过数据库数据精度范围" ,"", null, null);
			} else{
				obj = CommonsUtil.getRrturnJson("",errmsg ,"", null, null);
			}
			e.printStackTrace();
			out.print(obj);
			return null;
		}
		
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String firstStr = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("firstStr")));
		firstStr = firstStr.substring(0,firstStr.length()-1);
		String[] firstList = firstStr.split(";",-1);
		for(int i=0;i<firstList.length;i++){
			String[] firstPra = firstList[i].split(",",-1);
				String RPD_U_THIN_OIL_AUTO_ID = firstPra[0];
				List<PcRpdUThinOilAutoT> autoList = null;
				PcRpdUThinOilAutoT auto = null;
				if(RPD_U_THIN_OIL_AUTO_ID != null && !"".equals(RPD_U_THIN_OIL_AUTO_ID)){
					try {
						autoList = xyyyclService.SreachAutp(RPD_U_THIN_OIL_AUTO_ID, "");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				if(autoList != null && autoList.size()>0){
					auto = autoList.get(0);
					
				}else{
					auto = new PcRpdUThinOilAutoT();
				}
				String beforetime = DateBean.getBeforeDAYTime(rq);
				int calcNum = u1s1gyService.searchCalcNum();
				int zeroIndex = -calcNum/2;
				if(i>=zeroIndex){
					auto.setBbsj(sf.parse(rq +" "+ firstPra[1]));
				}else{
					auto.setBbsj(sf.parse(beforetime +" "+ firstPra[1]));
				}
				auto.setRpdUThinOilAutoId(firstPra[0]);
//第一行
				auto.setLit1201(CommonsUtil.isNullOrEmpty(firstPra[2]));
				auto.setLit1202(CommonsUtil.isNullOrEmpty(firstPra[3]));
				auto.setLit1203(CommonsUtil.isNullOrEmpty(firstPra[4]));
				auto.setLit1204(CommonsUtil.isNullOrEmpty(firstPra[5]));
				auto.setLit1205(CommonsUtil.isNullOrEmpty(firstPra[6]));
				auto.setLit1206(CommonsUtil.isNullOrEmpty(firstPra[7]));
				auto.setLit1207(CommonsUtil.isNullOrEmpty(firstPra[8]));
				auto.setLit1208(CommonsUtil.isNullOrEmpty(firstPra[9]));
				auto.setLit1209(CommonsUtil.isNullOrEmpty(firstPra[10]));
				auto.setLit1210(CommonsUtil.isNullOrEmpty(firstPra[11]));
				auto.setLit1601(CommonsUtil.isNullOrEmpty(firstPra[12]));
				
				auto.setLit1101(CommonsUtil.isNullOrEmpty(firstPra[13]));
				auto.setLit1102(CommonsUtil.isNullOrEmpty(firstPra[14]));
				auto.setLit1103(CommonsUtil.isNullOrEmpty(firstPra[15]));
				auto.setPt1101(CommonsUtil.isNullOrEmpty(firstPra[16]));
				auto.setFlqyl(CommonsUtil.isNullOrEmpty(firstPra[17]));
				auto.setTe1101(CommonsUtil.isNullOrEmpty(firstPra[18]));
				auto.setW51lywd(CommonsUtil.isNullOrEmpty(firstPra[19]));
				auto.setW52lywd(CommonsUtil.isNullOrEmpty(firstPra[20]));
				auto.setTe1103(CommonsUtil.isNullOrEmpty(firstPra[21]));
				auto.setMt1402(CommonsUtil.isNullOrEmpty(firstPra[22]));
				auto.setSei1201(CommonsUtil.isNullOrEmpty(firstPra[23]));
				auto.setMt1401(CommonsUtil.isNullOrEmpty(firstPra[24]));
				auto.setSeo1201(CommonsUtil.isNullOrEmpty(firstPra[25]));
				
				//第二行


				auto.setPt1303(CommonsUtil.isNullOrEmpty(firstPra[26]));
				auto.setTe1301(CommonsUtil.isNullOrEmpty(firstPra[27]));
				auto.setTe1302(CommonsUtil.isNullOrEmpty(firstPra[28]));
				auto.setPt1301(CommonsUtil.isNullOrEmpty(firstPra[29]));
				auto.setPt1302(CommonsUtil.isNullOrEmpty(firstPra[30]));
				auto.setTes1304(CommonsUtil.isNullOrEmpty(firstPra[31]));
				auto.setTes1301(CommonsUtil.isNullOrEmpty(firstPra[32]));
				auto.setTes1302(CommonsUtil.isNullOrEmpty(firstPra[33]));
				auto.setTes1404(CommonsUtil.isNullOrEmpty(firstPra[34]));
				auto.setTes1401(CommonsUtil.isNullOrEmpty(firstPra[35]));
				auto.setTes1402(CommonsUtil.isNullOrEmpty(firstPra[36]));
				
				auto.setWehFt1(CommonsUtil.isNullOrEmpty(firstPra[37]));
				auto.setWehxfwlj(CommonsUtil.isNullOrEmpty(firstPra[38]));
				auto.setWehHs1(CommonsUtil.isNullOrEmpty(firstPra[39]));
				auto.setWehyl(CommonsUtil.isNullOrEmpty(firstPra[40]));
				
				auto.setWehFt2(CommonsUtil.isNullOrEmpty(firstPra[41]));
				auto.setXzjxfwlj(CommonsUtil.isNullOrEmpty(firstPra[42]));
				auto.setWehHs2(CommonsUtil.isNullOrEmpty(firstPra[43]));
				
				auto.setXzjyl(CommonsUtil.isNullOrEmpty(firstPra[44]));
				auto.setFit11103(CommonsUtil.isNullOrEmpty(firstPra[45]));
				auto.setFit11103Q(CommonsUtil.isNullOrEmpty(firstPra[46]));
				auto.setFit1601(CommonsUtil.isNullOrEmpty(firstPra[47]));
				auto.setW33bsxfwlj(CommonsUtil.isNullOrEmpty(firstPra[48]));
				
				//第三行
				auto.setFit1101(CommonsUtil.isNullOrEmpty(firstPra[49]));
				auto.setFit1101Q(CommonsUtil.isNullOrEmpty(firstPra[50]));
				auto.setFit1301(CommonsUtil.isNullOrEmpty(firstPra[51]));
				auto.setFit1301Q(CommonsUtil.isNullOrEmpty(firstPra[52]));
         		//boolean updateflg = false;;
        		try {
        			updateflg = xyyyclService.updateAuto(auto);
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
        			obj = CommonsUtil.getRrturnJson("","第"+(i+1)+"行修改失败" ,"", null, null);
        		}
			
		
		}
		if(updateflg){
			//添加系统LOG
			try {
				User user1 = (User) session.getAttribute("userInfo");
				PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "3", "原油处理", oil.getRpdUThinOilGeneralId());
				logService.addLog(log);
			} catch (Exception e) {
				e.printStackTrace();
				obj = CommonsUtil.getRrturnJson("","原油处理日志修改失败" ,"", null, null);
			}
		}
		out.print(obj);
		return  null;
	}
	

	
	public String onAudit() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONObject obj = new JSONObject();
		User user1 = (User) session.getAttribute("userInfo");
		String zhId = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("RPD_U_THIN_OIL_GENERAL_ID")));
		List<PcRpdUThinOilGeneralT> oilGeneral = null;
		PcRpdUThinOilGeneralT oil = null;
		if(zhId != null && !"".equals(zhId)){
			oilGeneral = xyyyclService.SreachOilGeneral(zhId, "");
		}else{
			
			obj = CommonsUtil.getRrturnJson("","未获取到稀油联合站原油处理系统日报数据ID" ,"", null, null);
			out.print(obj);
			return null;
		}
		
		if(oilGeneral != null && oilGeneral.size()>0){
			oil = oilGeneral.get(0);
		}else{
			obj = CommonsUtil.getRrturnJson("","未获取到稀油联合站原油处理系统日报数据ID" ,"", null, null);
			out.print(obj);
			return null;
		}
		oil.setShr(user1.getOperName());
		oil.setShsj(new Date());

		boolean updateflg = false;
		try {
			updateflg = xyyyclService.updateOilGeneral(oil);
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
				PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "3", "原油处理", oil.getRpdUThinOilGeneralId());
				logService.addLog(log);
			} catch (Exception e) {
				e.printStackTrace();
				obj = CommonsUtil.getRrturnJson("","原油处理日志修改失败" ,"", null, null);
			}
		}
		out.print(obj);
		return null;
	}
	
	
	
	
	@SuppressWarnings("unused")
	public String exportData() throws Exception {
		HSSFWorkbook wb = getWb("reportDate");

		wb.setForceFormulaRecalculation(true);
		OutputStreamAndCloseBaos(U2DataExportUtil.ExporDataStream(wb));
		return "excel";
	}
	
	public HSSFWorkbook getWb(String date) throws Exception{
		String txtDate = getParameters(date);
		if ("".equals(txtDate)) {
			txtDate = DateBean.getSystemTime1();
		}
		HSSFWorkbook wb = getWorkBook("exceltemplet\\combination\\稀油注输联合站\\稀油注输联合站-原油处理综合日报.xls");
		HSSFSheet sheet = wb.getSheetAt(0);

		PropertiesConfig pc = new PropertiesConfig();

		String FIRSTINSERT = pc.getSystemConfiguration("FIRSTINSERT");
		String SECONDINSERTR = pc.getSystemConfiguration("SECONDINSERTR");
		String THIRDINSERT = pc.getSystemConfiguration("THIRDINSERT");
//		String U1WatInsertP = pc.getSystemConfiguration("U1WatInsertP");
		List<List<Object[]>> lists = xyyyclService.searchExportData(txtDate);
		List<List<Object[]>> list = getFGdata(lists);
		
		if (list != null && list.size() > 0) {
			//调用插入数据样式的方法
			U2DataExportUtil.insertDataByPosition(list.get(0), wb, sheet, FIRSTINSERT);
			U2DataExportUtil.insertDataByPosition(list.get(1), wb, sheet, SECONDINSERTR);
			U2DataExportUtil.insertDataByPosition(list.get(2), wb, sheet, THIRDINSERT);
		}
		String baseInfoSql = "select r.tbr,r.shr,r.rq,r.bz from pc_rpd_report_message_t r where r.bbmc='一号稠油联合处理站水二区罐液位及流量报表' and r.rq=TO_DATE('" + txtDate + "','YYYY-MM-DD') ";
//		list = u1s2gyService.searchExportData("", baseInfoSql);
		if (list != null && list.size() > 0) {
			// 获取单位、值班人、审核人、日期、备注数据插入位置
			String[] basePostion = pc.getSystemConfiguration( "XYYYCLINSERT" ).split(",");
			for (int j = 0; j < basePostion.length; j++) {
				if (lists.get(1) != null && lists.get(1).size()>0 && lists.get(1).get(0)[j+1] != null) {
					if(j == 0){
						U2DataExportUtil.insertExcelData(wb, sheet, basePostion[j], 0, new Object[]{DateBean.getChinaDate(lists.get(1).get(0)[j+1].toString())});
					}else{
						U2DataExportUtil.insertExcelData(wb, sheet, basePostion[j], 0, new Object[]{lists.get(1).get(0)[j+1]});
					}
					
				}
			}
		}
		return wb;
	}
	
	
	public List<List<Object[]>> getFGdata(List<List<Object[]>> list){
		List<List<Object[]>> newlist = new ArrayList<List<Object[]>>();
		List<Object[]>  list1 = new ArrayList<Object[]>();
		List<Object[]>  list2 = new ArrayList<Object[]>();
		List<Object[]>  list3 = new ArrayList<Object[]>();
		Object[] obj1 = null;
		Object[] obj2 = null;
		if(list != null && list.size() ==2){
			List<Object[]> pc_rpd_u_thin_oil_auto_tlist = list.get(0);
			for(int i = 0; i<pc_rpd_u_thin_oil_auto_tlist.size();i++){
				obj1 = new Object[24];
				obj2 = new Object[23];
				for(int j=0;j<pc_rpd_u_thin_oil_auto_tlist.get(i).length; j++){
					if(j>= 2 && j < 26){
						obj1[j-2] = pc_rpd_u_thin_oil_auto_tlist.get(i)[j];
					}else if(j>= 26 && j < 49){
						obj2[j-26] = pc_rpd_u_thin_oil_auto_tlist.get(i)[j];
						
					}
				}
				list1.add(obj1);
				list2.add(obj2);
				
			}
			
			Object[] obj4 = new Object[13];
			Object[] obj5 = new Object[13];
			Object[] obj6 = new Object[13];
			Object[] obj7 = new Object[13];
			for(int i = 0; i<pc_rpd_u_thin_oil_auto_tlist.size();i++){
					
					obj4[i] = pc_rpd_u_thin_oil_auto_tlist.get(i)[49];
					obj5[i] = pc_rpd_u_thin_oil_auto_tlist.get(i)[50];
					obj6[i] = pc_rpd_u_thin_oil_auto_tlist.get(i)[51];
					obj7[i] = pc_rpd_u_thin_oil_auto_tlist.get(i)[52];
				
			}
			list3.add(obj4);
			list3.add(obj5);
			list3.add(obj6);
			list3.add(obj7);
		}
		
		
		newlist.add(list1);
		newlist.add(list2);
		newlist.add(list3);
		return newlist;
	}
}
