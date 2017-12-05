package com.echo.action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.poi.hssf.converter.ExcelToHtmlUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.ServletActionContext;

import com.echo.dto.PcRdLoginfoT;
import com.echo.dto.PcRpdU1WaterQuality2T;
import com.echo.dto.User;
import com.echo.service.LogService;
import com.echo.service.U1szjcService;
import com.echo.util.AuthorityUtil;
import com.echo.util.CommonsUtil;
import com.echo.util.DateBean;
import com.echo.util.ExcelUtil;
import com.echo.util.PageVariableUtil;
import com.echo.util.PropertiesConfig;
import com.echo.util.ReportFormsBaseUitl;
import com.echo.util.StringUtil;
import com.echo.util.U2DataExportUtil;

public class U1Q2szjcAction extends ReportFormsBaseUitl {
	
	private static final long serialVersionUID = 1L;

	HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
	private InputStream excelFile = null;
	private U1szjcService u1szjcService;


	public void setU1szjcService(U1szjcService u1szjcService) {
		this.u1szjcService = u1szjcService;
	}


	private LogService logService;

	public LogService getLogService() {
		return logService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	
//	public String onAudit() throws Exception {
//		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
//		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
//		response.setCharacterEncoding("utf-8");
//		response.setHeader("ContentType","text/xml");
//		PrintWriter out = response.getWriter();
//		JSONObject obj = new JSONObject();
//		User user1 = (User) session.getAttribute("userInfo");
//		String RPDREPORTMESSAGEID = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("RPDREPORTMESSAGEID")));
//		List<PcRpdReportMessageT> reportMessage = null;
//		PcRpdReportMessageT reportM = null;
//		if(RPDREPORTMESSAGEID != null && !"".equals(RPDREPORTMESSAGEID)){
//			reportMessage = reportMessageService.SreachReportMessages(RPDREPORTMESSAGEID, "", "");
//		}else{
//			
//			obj = CommonsUtil.getRrturnJson("","未获取到二号稠油联合站原油系统日报数据ID" ,"", null, null);
//			out.print(obj);
//			return null;
//		}
//		
//		if(reportMessage != null && reportMessage.size()>0){
//			reportM = reportMessage.get(0);
//		}else{
//			obj = CommonsUtil.getRrturnJson("","未获取到二号稠油联合站原油系统日报数据ID对应数据" ,"", null, null);
//			out.print(obj);
//			return null;
//		}
//		reportM.setShr(user1.getOperName());
//		reportM.setShsj(new Date());
//
//		boolean updateflg = false;
//		try {
//			updateflg = reportMessageService.updateReportMessages(reportM);
//		} catch (Exception e) {
//			String  errmsg = e.getCause().toString();
//			
//			if(!"".equals(errmsg) && errmsg.indexOf("ORA-01438") != -1){
//				obj = CommonsUtil.getRrturnJson("","您输入信息超过数据库数据精度范围" ,"", null, null);
//			} else{
//				obj = CommonsUtil.getRrturnJson("",errmsg ,"", null, null);
//			}
//			e.printStackTrace();
//			out.print(obj);
//			return null;
//		}
//		
//		if(updateflg){
//			//添加系统LOG
//			try {
//				PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "3", "原油处理加药", reportM.getRpdReportMessageId());
//				logService.addLog(log);
//			} catch (Exception e) {
//				e.printStackTrace();
//				obj = CommonsUtil.getRrturnJson("","原油处理加药日志修改失败" ,"", null, null);
//			}
//		}
//		out.print(obj);
//		return null;
//	}
	public String updateU2szjc() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONObject obj = new JSONObject();
		
		String HYS2TCGJK = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("HYS2TCGJK")));
		String HYS2TCGCK = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("HYS2TCGCK")));
		String HYG4_3 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("HYG4_3")));
		String HYS2QFCK = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("HYS2QFCK")));
		String HYS2HCC = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("HYS2HCC")));
		String HYS2YJCK = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("HYS2YJCK")));
		String HYS2EJCK = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("HYS2EJCK")));
		String XFS2TCGJK = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("XFS2TCGJK")));
		String XFS2TCGCK = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("XFS2TCGCK")));
		String XFG4_3 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("XFG4_3")));
		String XFS2QFCK = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("XFS2QFCK")));
		String XFS2HCC = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("XFS2HCC")));
		String XFS2YJCK = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("XFS2YJCK")));
		String XFS2EJCK = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("XFS2EJCK")));
		String YDS2TCGCK = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("YDS2TCGCK")));
		String YDS2EJCK = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("YDS2EJCK")));
		
		String YDS2TCGJK = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("YDS2TCGJK")));
		String YD4_3 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("YD4_3")));
		String YDS2HCC = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("YDS2HCC")));
		String YDS2QF = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("YDS2QF")));
		String YDS2YJCK = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("YDS2YJCK")));
		String BZ = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("BZ")));
		String RPD_U1_WATER_QUALITY2_ID = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("RPD_U1_WATER_QUALITY2_ID")));
		
		PcRpdU1WaterQuality2T prs = null;
		User user = (User) session.getAttribute("userInfo");
		//请求的long参数
			if (RPD_U1_WATER_QUALITY2_ID != null && !"".equals(RPD_U1_WATER_QUALITY2_ID)) {
				List<PcRpdU1WaterQuality2T> pros = null;
				try {
					pros = u1szjcService.searchU1WaterQuality(RPD_U1_WATER_QUALITY2_ID);
				} catch (Exception e) {
					e.printStackTrace();
					obj = CommonsUtil.getRrturnJson("","一号稠油联合处理站水一区水质监测记录数据获取失败，请联系管理员" ,"", null, null);
					out.print(obj);
					return null;
				}
				
				if(pros != null && pros.size()>0){
					prs = pros.get(0);
					
					prs.setHys2tcgjk(CommonsUtil.getBigDecimalData(HYS2TCGJK));
					prs.setHys2tcgck(CommonsUtil.getBigDecimalData(HYS2TCGCK));
					prs.setHyg43(CommonsUtil.getBigDecimalData(HYG4_3));
					prs.setHys2qfck(CommonsUtil.getBigDecimalData(HYS2QFCK));
					prs.setHys2hcc(CommonsUtil.getBigDecimalData(HYS2HCC));
					prs.setHys2yjck(CommonsUtil.getBigDecimalData(HYS2YJCK));
					prs.setHys2ejck(CommonsUtil.getBigDecimalData(HYS2EJCK));
					prs.setXfs2tcgjk(CommonsUtil.getBigDecimalData(XFS2TCGJK));
					prs.setXfs2tcgck(CommonsUtil.getBigDecimalData(XFS2TCGCK));
					prs.setXfg43(CommonsUtil.getBigDecimalData(XFG4_3));
					prs.setXfs2qfck(CommonsUtil.getBigDecimalData(XFS2QFCK));
					prs.setXfs2hcc(CommonsUtil.getBigDecimalData(XFS2HCC));
					prs.setXfs2yjck(CommonsUtil.getBigDecimalData(XFS2YJCK));
					prs.setXfs2ejck(CommonsUtil.getBigDecimalData(XFS2EJCK));
					prs.setYds2tcgck(CommonsUtil.getBigDecimalData(YDS2TCGCK));
					prs.setYds2ejck(CommonsUtil.getBigDecimalData(YDS2EJCK));
					
					prs.setYds2tcgjk(CommonsUtil.getBigDecimalData(YDS2TCGJK));
					prs.setYd4_3(CommonsUtil.getBigDecimalData(YD4_3));
					prs.setYds2hcc(CommonsUtil.getBigDecimalData(YDS2HCC));
					prs.setYds2qf(CommonsUtil.getBigDecimalData(YDS2QF));
					prs.setYds2yjck(CommonsUtil.getBigDecimalData(YDS2YJCK));
					prs.setBz(BZ);
					
					prs.setRlastOper(user.getOperName());
					prs.setRlastOdate(new Date(System.currentTimeMillis()));
					
					
					boolean flg = false;
					try {
						flg = u1szjcService.addOrUpdateData(prs);
					} catch (Exception e) {
						e.printStackTrace();
						obj = CommonsUtil.getRrturnJson("","水质监测数据更新失败" ,"", null, null);
						out.print(obj);
						return null;
					}
					
					if(flg){
						//添加系统LOG
						try {
							PcRdLoginfoT log = CommonsUtil.getLogInfo(user, "3", "U1水二区水质监测", RPD_U1_WATER_QUALITY2_ID);
							logService.addLog(log);
						} catch (Exception e) {
							e.printStackTrace();
							obj = CommonsUtil.getRrturnJson("","稠油1号联合站水一区水质监测数据更新LOG添加失败" ,"", null, null);
							out.print(obj);
							return null;
						}
					}
					
					
				}else{
					obj = CommonsUtil.getRrturnJson("","水质监测数据ID数据获取失败，请联系管理员" ,"", null, null);
					out.print(obj);
					return null;
				}
				
			}else{
				obj = CommonsUtil.getRrturnJson("","您要修改的数据未获取到水质监测数据ID,请重新选择数据" ,"", null, null);
				out.print(obj);
				return null;
			}
			
//		}
		
		out.print(obj);
		return null;
	}
	
	public String searchU2SZJC() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONObject obj = null;
		String txtYeah = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("SYear")));
		String txtMonth = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("SMonth")));
		List<String> date = null;
		JSONObject u2szjcs = null;
		if(txtYeah != null && !"".equals(txtYeah) && txtMonth != null && !"".equals(txtMonth)){
			date = DateBean.getYeahMonth(txtYeah, txtMonth);
		}else{
			Calendar now = Calendar.getInstance();  
			date = DateBean.getYeahMonth(String.valueOf(now.get(Calendar.YEAR)), String.valueOf((now.get(Calendar.MONTH) + 1)));
		
		}
		
		try {
			u2szjcs = u1szjcService.searchU1Q2szjc(date);
			obj = CommonsUtil.getRrturnJson("","" ,"", u2szjcs, null);
		} catch (Exception e) {
			e.printStackTrace();
			obj = CommonsUtil.getRrturnJson("","二号稠油联合站水质监测数据获取失败" ,"", null, null);
			out.print(obj);
			return null;
		}

		out.print(u2szjcs);
		
	return null;
	
	
	
}
	
	private final String fileName = "一号稠油联合处理站水二区水质监测记录报表.xls";
	
	public String getFileName() {
		return super.getFileName(fileName);
	}
	
	@SuppressWarnings("unused")
	public String exportU2_SZJC() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		String txtYeah = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("SYear")));
		String txtMonth = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("SMonth")));
		
		String templetFilePath = request.getSession().getServletContext().getRealPath("/") + "exceltemplet\\combination\\一号联合站\\一号稠油联合处理站水二区水质监测记录报表.xls";
		HSSFWorkbook wb = ExcelToHtmlUtils.loadXls( new File( templetFilePath ) );
		HSSFSheet sheet = wb.getSheetAt(0);
		
		String temp = txtYeah+"."+txtMonth;
		wb.setSheetName(0, temp);
		List<String> date = null;
		if(txtYeah != null && !"".equals(txtYeah) && txtMonth != null && !"".equals(txtMonth)){
			date = DateBean.getYeahMonth(txtYeah, txtMonth);
		}else{
			Calendar now = Calendar.getInstance();  
			date = DateBean.getYeahMonth(String.valueOf(now.get(Calendar.YEAR)), String.valueOf((now.get(Calendar.MONTH) + 1)));
		
		}
		PropertiesConfig pc = new PropertiesConfig();
		String fields = pc.getSystemConfiguration("U1S2SZJCSQL");		
		List<Object[]> list = u1szjcService.searchExportData(date,fields);
		String U2UZJC_START_INSERT = pc.getSystemConfiguration("U1S2SZJC_START_INSERT");		
		if (list.size() != 0 && list != null) {
			PropertiesConfig propertiesConfig = new PropertiesConfig();//写在adction 里 在查询拼接sql及导出报表时都要用到						
			CellStyle cs1 = wb.createCellStyle();
			cs1 = ExcelUtil.getDataStyle(cs1);			
			List<Object[]> objDataList = U2DataExportUtil.splitU2szjcData(list);
			U2DataExportUtil.insertDataByPosition(objDataList, wb, sheet, U2UZJC_START_INSERT);			
			CellReference cr = new CellReference(U2UZJC_START_INSERT);
			int startRowNum = cr.getRow();
			Row dataRow;
			sheet.getLastRowNum();
			Cell dataCell;			
			int k = objDataList.get(0).length;			
			for (int i = startRowNum; i < objDataList.size()+startRowNum; i++) {
				dataRow = sheet.getRow(i);
				
				dataCell = dataRow.getCell(0);
				System.out.println("ewrew"+dataRow.getCell(3));
				if (dataCell.getStringCellValue() == null || "".equals(dataCell.getStringCellValue())) {
					CellStyle style = wb.createCellStyle();
					ExcelUtil.setStyle(wb, style);
					style.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.getIndex());
				    style.setFillPattern(CellStyle.SOLID_FOREGROUND);
				    for (int j = 0; j <k; j++) {
				    	dataRow.getCell(j).setCellStyle(style);
					}
				}

			}			
		}
		OutputStreamAndCloseBaos(U2DataExportUtil.ExporDataStream(wb));
		return "excel";
	}
	
	
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
			gridJson = AuthorityUtil.getGridJson1("MENU117", user, PageVariableUtil.U1_WATER_QUALITY2);
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
}