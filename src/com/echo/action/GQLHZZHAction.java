package com.echo.action;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
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
import com.echo.dto.PcRpdGqlhzzhT;
import com.echo.dto.PcRpdWaterMessageT;
import com.echo.dto.User;
import com.echo.service.GQLHZZHService;
import com.echo.service.LogService;
import com.echo.service.ReportMessageService;
import com.echo.util.AuthorityUtil;
import com.echo.util.CommonsUtil;
import com.echo.util.DateBean;
import com.echo.util.PropertiesConfig;
import com.echo.util.ReportFormsBaseUitl;
import com.echo.util.StringUtil;
import com.echo.util.U2DataExportUtil;

public class GQLHZZHAction extends ReportFormsBaseUitl{
		private GQLHZZHService gqlhzzhService;
		private ReportMessageService reportMessageService;

		public void setGqlhzzhService(GQLHZZHService gqlhzzhService) {
			this.gqlhzzhService = gqlhzzhService;
		}
		HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
		
		private final String fileName = "供汽联合站综合日报 .xls";
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
				butJson = AuthorityUtil.getButtonJson("MENU138", user);
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
			JSONObject dataArr = null;
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
				jsonobj = gqlhzzhService.searchDataSet(txtDate);
				//if(dataArr != null && dataArr.size()>0){
					//jsonobj = new JSONObject();
					//jsonobj.put("dataArr", dataArr);
				//}
			} catch (Exception e) {
				e.printStackTrace();
				obj = CommonsUtil.getRrturnJson("","当前日期搜索失败请选择其他日期" ,"", null, null);
				out.print(obj);
				return null;
			}
			try {
				waterMess = reportMessageService.SreachWaterMessageT("", "供汽联合站综合日报", txtDate);
				if(waterMess != null && waterMess.size()>0){
					rm = waterMess.get(0);
					
				}else{
					rm = new PcRpdWaterMessageT();
					rm.setRq(DateBean.getStrDate(txtDate));
					rm.setBbmc("供汽联合站综合日报");
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
					waterMess = reportMessageService.SreachWaterMessageT("", "供汽联合站综合日报" , txtDate);
					rm = waterMess.get(0);
					if(updateflg){
						//添加系统LOG
						try {
							User user1 = (User) session.getAttribute("userInfo");
							PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "1", "供汽联合报表", rm.getWaterMessageId());
							logService.addLog(log);
						} catch (Exception e) {
							e.printStackTrace();
							obj = CommonsUtil.getRrturnJson("","供汽联合综合报表日志添加失败" ,"", null, null);
						}
					}
				}
				
				obj = new JSONObject();
				obj.put("WATERMESSAGEID", rm.getWaterMessageId());
				obj.put("BBMC", rm.getBbmc());
				obj.put("RQ", DateBean.getChinaDate(txtDate));

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
				if( rm.getTbr1()!=null &&  !rm.getTbr1().equals("")){
					obj.put("TBR1", rm.getTbr1()); 
				}else{
					obj.put("TBR1", ""); 
				}
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
		
		
    public String updateDatas() throws Exception {
			HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
			HttpServletResponse response=ServletActionContext.getResponse();//响应对象
			response.setCharacterEncoding("utf-8");
			response.setHeader("ContentType","text/xml");
			PrintWriter out = response.getWriter();
			JSONObject obj = new JSONObject();

  			String	BZ1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("BZ1")));
  			String	TBR1 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("TBR1")));
  			
  			String WATERMESSAGEID = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("WATERMESSAGEID")));
  			String RQ =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("txtDate")));
  			
			//↓
			String RPD_GQLHZ_ID =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("RPD_GQLHZ_ID")));
			String DLS =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("DLS")));
			String SZQZQL =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("SZQZQL")));
			String GRGGZQL =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("GRGGZQL")));
			String TLTC =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("TLTC")));
			String GLZYXSJ =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("GLZYXSJ")));
			String JHTLSJ =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("JHTLSJ")));
			String FJHTLSJ =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("FJHTLSJ")));
			//String GGDCJHGL =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("GGDCJHGL")));
			//String GRDCJHGL =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("GRDCJHGL")));
			//String SLCJHGL =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("SLCJHGL")));
			//String DHCJHGL =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("DHCJHGL")));
			String SZQGLRHTRQ =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("SZQGLRHTRQ")));
			String GRGLRHTRQ =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("GRGLRHTRQ")));
			String SZQGLRHD =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("SZQGLRHD")));
			String GRGLRHD =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("GRGLRHD")));
			String SCLZRHD =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("SCLZRHD")));
			String HML =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("HML")));
			String SYJ =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("SYJ")));
			String LJS =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("LJS")));
			String TJHWS1 =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("TJHWS1")));
			String TJHWS2 =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("TJHWS2")));
			String RHQZGSL =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("RHQZGSL")));
			String CYQZGSL =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("CYQZGSL")));
			String HYA =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("HYA")));
			String XFA =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("XFA")));
			String YDA =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("YDA")));
			String LGA =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("LGA")));
			String HYB =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("HYB")));
			String XFB =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("XFB")));
			String YDB =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("YDB")));
			String LGB =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("LGB")));
			String YDC =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("YDC")));
			String LGC =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("LGC")));
			String JZCYQCKHY =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("JZCYQCKHY")));
			String YDD =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("YDD")));
			String LGD =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("LGD")));
			String CYQCKHY1 =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("CYQCKHY1")));
			String YDE =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("YDE")));
			String LGE =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("LGE")));
			String CYQCKHY2 =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("CYQCKHY2")));
			String YDF =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("YDF")));
			String LGF =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("LGF")));
			String XDF =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("XDF")));
			String HYF =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("HYF")));
			String PHZ =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("PHZ")));
			String WYSCLCK =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("WYSCLCK")));
			String JZSSGYW =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("JZSSGYW")));
			String JZZSGYW =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("JZZSGYW")));
			String JZRSGYW =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("JZRSGYW")));
			String TQSSSGYW1 =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("TQSSSGYW1")));
			String TQRSSGYW1 =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("TQRSSGYW1")));
			String TJHSSSGYW1 =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("TJHSSSGYW1")));
			String QSSSGYW2 =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("QSSSGYW2")));
			String QSRSGYW2 =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("QSRSGYW2")));
			String JHSSGYW2 =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("JHSSGYW2")));
			String JHRSGYW2 =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("JHRSGYW2")));
			String JZZSBYL =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("JZZSBYL")));
			String JZZYBYL =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("JZZYBYL")));
			String YQZYBYL1 =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("YQZYBYL1")));
			String EQZYBYL1 =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("EQZYBYL1")));
			String SQZYBYL1 =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("SQZYBYL1")));
			String YQZYBYL2 =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("YQZYBYL2")));
			String EQZYBYL2 =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("EQZYBYL2")));
			String JZZSSBSL =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("JZZSSBSL")));
			String JZKYJYL =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("JZKYJYL")));
			String ZSSBSL1 =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("ZSSBSL1")));
			String TKYJYL1 =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("TKYJYL1")));
			String ZSSBSL2 =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("ZSSBSL2")));
			String TKYJYL2 =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("TKYJYL2")));
			String FSTGSSS =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("FSTGSSS")));
			String GSLJL =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("GSLJL")));
			String NYSPSL =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("NYSPSL")));
			String RHGYW =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("RHGYW")));
			String ZGJJY =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("ZGJJY")));
			String KYJYL =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("KYJYL")));
			String ZYBYL =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("ZYBYL")));
			String BTJYL =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("BTJYL")));
			String JZJY =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("JZJY")));
			String JZYL =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("JZYL")));
			String JY1 =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("JY1")));
			String YY1 =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("YY1")));
			String JY2 =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("JY2")));
			String YY2 =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("YY2")));
			String ZQL =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("ZQL")));
			String YSL =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("YSL")));
			String GHYCSL =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("GHYCSL")));
			String GYSPFL =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("GYSPFL")));
			String CML =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("CML")));
			String YDL =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("YDL")));
			String XPJYL =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("XPJYL")));
			String LSEQNYL =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("LSEQNYL")));
			String SHSYL =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("SHSYL")));
  			
			List<PcRpdGqlhzzhT> gqList = null;
			PcRpdGqlhzzhT gq = null;
			if(RPD_GQLHZ_ID !=null &&  !"".equals(RPD_GQLHZ_ID)){
				try {
					gqList = gqlhzzhService.searchCheckData(RPD_GQLHZ_ID,"");
				} catch (Exception e) {
					e.printStackTrace();
					obj = CommonsUtil.getRrturnJson("","供气联合站ID获取失败" , "",null, null);
					out.print(obj);
					return null;
				}
			}
			if(gqList !=null && gqList.size()>0){
				gq = gqList.get(0);
			}else{
				//gq = new PcRpdGqlhzzhT();
				obj = CommonsUtil.getRrturnJson("",""+RQ+"供气联合站ID获取失败" , "",null, null);
				out.print(obj);
				return null;
			}
			gq.setBbsj(DateBean.getStrDate(RQ));
			gq.setDls(CommonsUtil.getBigDecimalData(DLS));
			gq.setSzqzql(CommonsUtil.getBigDecimalData(SZQZQL));
			gq.setGrggzql(CommonsUtil.getBigDecimalData(GRGGZQL));
			gq.setTltc(CommonsUtil.getBigDecimalData(TLTC));
			gq.setGlzyxsj(CommonsUtil.getBigDecimalData(GLZYXSJ));
			gq.setJhtlsj(CommonsUtil.getBigDecimalData(JHTLSJ));
			gq.setFjhtlsj(CommonsUtil.getBigDecimalData(FJHTLSJ));
			
			gq.setSzqglrhtrq(CommonsUtil.getBigDecimalData(SZQGLRHTRQ));
			gq.setGrglrhtrq(CommonsUtil.getBigDecimalData(GRGLRHTRQ));
			gq.setSzqglrhd(CommonsUtil.getBigDecimalData(SZQGLRHD));
			gq.setGrglrhd(CommonsUtil.getBigDecimalData(GRGLRHD));
			gq.setSclzrhd(CommonsUtil.getBigDecimalData(SCLZRHD));
			gq.setHml(CommonsUtil.getBigDecimalData(HML));
			gq.setSyj(CommonsUtil.getBigDecimalData(SYJ));
			gq.setLjs(CommonsUtil.getBigDecimalData(LJS));
			gq.setTjhws1(CommonsUtil.getBigDecimalData(TJHWS1));
			gq.setTjhws2(CommonsUtil.getBigDecimalData(TJHWS2));
			
			gq.setRhqzgsl(CommonsUtil.getBigDecimalData(RHQZGSL));
			gq.setCyqzgsl(CommonsUtil.getBigDecimalData(CYQZGSL));
			gq.setHya(CommonsUtil.getBigDecimalData(HYA));
			gq.setXfa(CommonsUtil.getBigDecimalData(XFA));
			gq.setYda(CommonsUtil.getBigDecimalData(YDA));
			gq.setLga(CommonsUtil.getBigDecimalData(LGA));
			gq.setHyb(CommonsUtil.getBigDecimalData(HYB));
			gq.setXfb(CommonsUtil.getBigDecimalData(XFB));
			gq.setYdb(CommonsUtil.getBigDecimalData(YDB));
			gq.setLgb(CommonsUtil.getBigDecimalData(LGB));
			gq.setYdc(CommonsUtil.getBigDecimalData(YDC));
			gq.setLgc(CommonsUtil.getBigDecimalData(LGC));
			
			gq.setJzcyqckhy(CommonsUtil.getBigDecimalData(JZCYQCKHY));
			gq.setYdd(CommonsUtil.getBigDecimalData(YDD));
			gq.setLgd(CommonsUtil.getBigDecimalData(LGD));
			gq.setCyqckhy1(CommonsUtil.getBigDecimalData(CYQCKHY1));
			gq.setYde(CommonsUtil.getBigDecimalData(YDE));
			gq.setLge(CommonsUtil.getBigDecimalData(LGE));
			gq.setCyqckhy2(CommonsUtil.getBigDecimalData(CYQCKHY2));
			gq.setYdf(CommonsUtil.getBigDecimalData(YDF));
			gq.setLgf(CommonsUtil.getBigDecimalData(LGF));
			gq.setXdf(CommonsUtil.getBigDecimalData(XDF));
			gq.setHyf(CommonsUtil.getBigDecimalData(HYF));
			gq.setPhz(CommonsUtil.getBigDecimalData(PHZ));
			gq.setWysclck(CommonsUtil.getBigDecimalData(WYSCLCK));
			gq.setJzssgyw(CommonsUtil.getBigDecimalData(JZSSGYW));
			gq.setJzzsgyw(CommonsUtil.getBigDecimalData(JZZSGYW));
			gq.setJzrsgyw(CommonsUtil.getBigDecimalData(JZRSGYW));
			gq.setTqsssgyw1(CommonsUtil.getBigDecimalData(TQSSSGYW1));
			gq.setTqrssgyw1(CommonsUtil.getBigDecimalData(TQRSSGYW1));
			gq.setTjhsssgyw1(CommonsUtil.getBigDecimalData(TJHSSSGYW1));
			gq.setQsssgyw2(CommonsUtil.getBigDecimalData(QSSSGYW2));
			gq.setQsrsgyw2(CommonsUtil.getBigDecimalData(QSRSGYW2));
			gq.setJhssgyw2(CommonsUtil.getBigDecimalData(JHSSGYW2));
			gq.setJhrsgyw2(CommonsUtil.getBigDecimalData(JHRSGYW2));
			gq.setJzzsbyl(CommonsUtil.getBigDecimalData(JZZSBYL));
			gq.setJzzybyl(CommonsUtil.getBigDecimalData(JZZYBYL));
		
			gq.setYqzybyl1(CommonsUtil.getBigDecimalData(YQZYBYL1));
			gq.setEqzybyl1(CommonsUtil.getBigDecimalData(EQZYBYL1));
			gq.setSqzybyl1(CommonsUtil.getBigDecimalData(SQZYBYL1));
			gq.setYqzybyl2(CommonsUtil.getBigDecimalData(YQZYBYL2));
			gq.setEqzybyl2(CommonsUtil.getBigDecimalData(EQZYBYL2));
			gq.setJzzssbsl(CommonsUtil.getBigDecimalData(JZZSSBSL));
			gq.setJzkyjyl(CommonsUtil.getBigDecimalData(JZKYJYL));
			gq.setZssbsl1(CommonsUtil.getBigDecimalData(ZSSBSL1));
			gq.setTkyjyl1(CommonsUtil.getBigDecimalData(TKYJYL1));
			gq.setZssbsl2(CommonsUtil.getBigDecimalData(ZSSBSL2));
			gq.setTkyjyl2(CommonsUtil.getBigDecimalData(TKYJYL2));
			gq.setFstgsss(CommonsUtil.getBigDecimalData(FSTGSSS));
			gq.setGsljl(CommonsUtil.getBigDecimalData(GSLJL));
			gq.setNyspsl(CommonsUtil.getBigDecimalData(NYSPSL));
			gq.setRhgyw(CommonsUtil.getBigDecimalData(RHGYW));
			gq.setZgjjy(CommonsUtil.getBigDecimalData(ZGJJY));
			gq.setKyjyl(CommonsUtil.getBigDecimalData(KYJYL));
			gq.setZybyl(CommonsUtil.getBigDecimalData(ZYBYL));
			gq.setBtjyl(CommonsUtil.getBigDecimalData(BTJYL));
			gq.setJzjy(CommonsUtil.getBigDecimalData(JZJY));
			gq.setJzyl(CommonsUtil.getBigDecimalData(JZYL));
			gq.setJy1(CommonsUtil.getBigDecimalData(JY1));
			gq.setYy1(CommonsUtil.getBigDecimalData(YY1));
			gq.setJy2(CommonsUtil.getBigDecimalData(JY2));
			gq.setYy2(CommonsUtil.getBigDecimalData(YY2));
			gq.setZql(CommonsUtil.getBigDecimalData(ZQL));
			gq.setYsl(CommonsUtil.getBigDecimalData(YSL));
			gq.setGhycsl(CommonsUtil.getBigDecimalData(GHYCSL));
			gq.setGyspfl(CommonsUtil.getBigDecimalData(GYSPFL));
			gq.setCml(CommonsUtil.getBigDecimalData(CML));
			gq.setYdl(CommonsUtil.getBigDecimalData(YDL));
			gq.setXpjyl(CommonsUtil.getBigDecimalData(XPJYL));
			gq.setLseqnyl(CommonsUtil.getBigDecimalData(LSEQNYL));
			gq.setShsyl(CommonsUtil.getBigDecimalData(SHSYL));
			gqList.clear();
			gqList.add(gq);
			boolean flag = false;
			try {
				flag = gqlhzzhService.saveData(gqList);
			} catch (Exception e) {
				e.printStackTrace();
				String  errmsg = e.getCause().toString();
				if(!"".equals(errmsg) && errmsg.indexOf("ORA-01438") != -1){
					obj = CommonsUtil.getRrturnJson("","您输入信息超过数据库数据精度范围" ,"", null, null);
				} else{
					obj = CommonsUtil.getRrturnJson("",errmsg ,"", null, null);
				}
			}
			if(flag){
				//添加系统LOG
				try {
					User user1 = (User) session.getAttribute("userInfo");
					PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "3", "供汽综合", gq.getRpdGqlhzId());
					logService.addLog(log);
				} catch (Exception e) {
					e.printStackTrace();
					obj = CommonsUtil.getRrturnJson("","供汽联合站综合报表日志添加失败" ,"", null, null);
				}
			}
  			//↑
			List<PcRpdWaterMessageT> meList = null;
			PcRpdWaterMessageT reportM = null;
			if(WATERMESSAGEID !=null && !"".equals(WATERMESSAGEID)){
				meList = reportMessageService.SreachWaterMessages(WATERMESSAGEID, "", "");
			}
		
			if(meList != null && meList.size()>0){
				reportM = meList.get(0);
			}else{
				if(RQ != null && !"".equals(RQ)){
					
					meList = reportMessageService.SreachWaterMessages("", "供汽联合站综合日报", RQ);
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
			reportM.setBz1(BZ1);
			
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
					PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "3", "四合一", reportM.getWaterMessageId());
					logService.addLog(log);
				} catch (Exception e) {
					e.printStackTrace();
					obj = CommonsUtil.getRrturnJson("","四合一水处理日报数据修改日志添加失败" ,"", null, null);
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
			
			obj = CommonsUtil.getRrturnJson("","供汽联合站综合日报数据ID获取失败" ,"", null, null);
			out.print(obj);
			return null;
		}
		
		if(watMessage != null && watMessage.size()>0){
			wm = watMessage.get(0);
		}else{
			obj = CommonsUtil.getRrturnJson("","供汽联合站综合日报 数据ID获取失败" ,"", null, null);
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
				PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "7", "供气联合站", wm.getWaterMessageId());
				logService.addLog(log);
			} catch (Exception e) {
				e.printStackTrace();
				obj = CommonsUtil.getRrturnJson("","供汽联合站综合日报日志修改失败" ,"", null, null);
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
		}
		PropertiesConfig pc = new PropertiesConfig();
		
		String gqfirstLie = pc.getSystemConfiguration("gqfirstLie"); //位置
		String gqfirstSql = pc.getSystemConfiguration("gqfirstSql");
		String templetFilePath = request.getSession().getServletContext().getRealPath("/") + "exceltemplet\\ExportRBWH\\供汽综合报表.xls";
		HSSFWorkbook wb = ExcelToHtmlUtils.loadXls( new File( templetFilePath ) );
		HSSFSheet sheet = wb.getSheetAt(0);
		
		List<Object[]> listFir = gqlhzzhService.searchExportDataFirst(txtDate, gqfirstSql);
		List<Object[]>  listo = null;
		listo =new ArrayList<Object[]>();
		int arrIndexo = 0;
		Object[] oTempo;
		if (listFir != null && listFir.size() > 0) {
			//调用插入数据样式的方法
			for (int i = 0; i < listFir.size(); i++) {
				Object[] firData = listFir.get(i);
				if (i == 1) {
					for (int j = 0; j < firData.length; j++) {
						listo.get(arrIndexo++)[1] = firData[j];
						oTempo = new Object[2];
						oTempo[1] = firData[j];
						//listo.add(oTempo);
						//oTempo[2]=Double.parseDouble(oTempo[1].toString()) - Double.parseDouble(oTempo[0].toString());
					}
				}else{
					for (int j = 0; j < firData.length; j++) {
						oTempo = new Object[2];
						oTempo[0] = firData[j];
						listo.add(oTempo);
					}
				}
				
			}
			U2DataExportUtil.insertDataByPosition(listo, wb, sheet, gqfirstLie);
		}
		//对比
		//comInser = E5
		String comInser = pc.getSystemConfiguration("comInser"); //位置
		Object[] newobj = null;
		List<Object[]> comList = new ArrayList<Object[]>();
		for (int i = 0; i < listo.size(); i++) {
			newobj = new Object[1];
//			for(int k = 0 ; k< listo.get(i).length; k ++){
//				newobj[k] = listo.get(i)[k];
//			}
			
//			0,3,7,8,9,10
//			16
			if(i == 0 || i == 3 ||i == 7 ||i == 8 ||i == 9 ||i == 10 ){
				newobj[0] = CommonsUtil.getSubtractInt(listo.get(i)[1], listo.get(i)[0]);
			}else if(i == 16){
				newobj[0] = CommonsUtil.getSubtractTwo(listo.get(i)[1], listo.get(i)[0]);
			}else{
				newobj[0] = CommonsUtil.getSubtractOne(listo.get(i)[1], listo.get(i)[0]);
			}
			
			comList.add(newobj);
			
		}
		U2DataExportUtil.insertDataByPosition(comList, wb, sheet, comInser);
		
		String gqsecondLie = pc.getSystemConfiguration("gqsecondLie"); //位置
		String gqsecondSql = pc.getSystemConfiguration("gqsecondSql");
		
		List<Object[]> listSec = gqlhzzhService.searchExportDataFirst(txtDate, gqsecondSql);
		 List<Object[]> listoSec = new ArrayList<Object[]>();
		int arrIndexo1 = 0;
		Object[] oTempo1;
		if (listSec != null && listSec.size() > 0) {
			//调用插入数据样式的方法
			for (int i = 0; i < listSec.size(); i++) {
				Object[] firData = listSec.get(i);
				if (i == 1) {
					for (int j = 0; j < firData.length; j++) {
						listoSec.get(arrIndexo1++)[1] = firData[j];
						oTempo1 = new Object[2];
						oTempo1[1] = firData[j];
						//listo.add(oTempo);
					}
				}else{
					for (int j = 0; j < firData.length; j++) {
						oTempo1 = new Object[2];
						oTempo1[0] = firData[j];
						listoSec.add(oTempo1);
					}
				}
			}
			U2DataExportUtil.insertDataByPosition(listoSec, wb, sheet, gqsecondLie);
		}
		
		String middInser = pc.getSystemConfiguration("middInser"); //位置
		Object[] newobjMidd = null;
		List<Object[]> comMiddList = new ArrayList<Object[]>();
		for (int i = 0; i < listoSec.size(); i++) {
			newobjMidd = new Object[1];
				newobjMidd[0] = CommonsUtil.getSubtractOne(listoSec.get(i)[1], listoSec.get(i)[0]);
				comMiddList.add(newobjMidd);
			
		}
		U2DataExportUtil.insertDataByPosition(comMiddList, wb, sheet, middInser);
		
		String gqthreeLie = pc.getSystemConfiguration("gqthreeLie"); //位置
		String gqthreeSql = pc.getSystemConfiguration("gqthreeSql");
		
		List<Object[]> listThr = gqlhzzhService.searchExportDataThree(txtDate, gqthreeSql);
		 listo = new ArrayList<Object[]>();
		int arrIndexo2 = 0;
		Object[] oTempo2;
		if (listThr != null && listThr.size() > 0) {
			//调用插入数据样式的方法
			for (int i = 0; i < listThr.size(); i++) {
				Object[] firData = listThr.get(i);
				
					for (int j = 0; j < firData.length; j++) {
						oTempo2 = new Object[1];
						oTempo2[0] = firData[j];
						listo.add(oTempo2);
					}
				
			}
			U2DataExportUtil.insertDataByPosition(listo, wb, sheet, gqthreeLie);
		}
		
		String gqfourLie = pc.getSystemConfiguration("gqfourLie"); //位置
		String gqfourSql = pc.getSystemConfiguration("gqfourSql");
		
		List<Object[]> listFou = gqlhzzhService.searchExportDataThree(txtDate, gqfourSql);
		 listo = new ArrayList<Object[]>();
		int arrIndexo3 = 0;
		Object[] oTempo3;
		if (listFou != null && listFou.size() > 0) {
			//调用插入数据样式的方法
			for (int i = 0; i < listFou.size(); i++) {
				Object[] firData = listFou.get(i);
				
					for (int j = 0; j < firData.length; j++) {
						oTempo3 = new Object[1];
						oTempo3[0] = firData[j];
						listo.add(oTempo3);
					}
				
			}
			U2DataExportUtil.insertDataByPosition(listo, wb, sheet, gqfourLie);
		}
		List<Object[]> zList=null;
		String gqzhSql = pc.getSystemConfiguration("gqzhSql");
		String punSql = "select "+gqzhSql+" from pc_rpd_water_message_t a  where  a.rq=to_date('"+txtDate+"' ,'YYYY-MM-DD')  and a.water_message_id='"+WATERMESSAGEID+"'";
			zList = gqlhzzhService.searchExportDataZH(punSql);
		if (zList != null && zList.size() > 0) {
			// 获取单位、值班人、审核人、日期、备注数据插入位置
			//String[] basePostion = pc.getSystemConfiguration( "U1ComInsert" )
			String[] gqzh = pc.getSystemConfiguration("gqzh").split(",");
			for (int j = 0; j < gqzh.length; j++) {
				if (zList.get(0)[j] != null) {
					U2DataExportUtil.insertExcelData(wb, sheet, gqzh[j], 0, new Object[]{zList.get(0)[j]});
				}
			}
		}
		OutputStreamAndCloseBaos(U2DataExportUtil.ExporDataStream(wb));
		return "excel";
	}
	
	public String dayreport() throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		List<String> result = new ArrayList<String>(); 
		JSONObject obj = new JSONObject();
		try {
			result = gqlhzzhService.dayreport();
		} catch (Exception e) {
			e.printStackTrace();
			obj = CommonsUtil.getRrturnJson("","当日日报生成失败" ,"", null, null);
		}
		
		if(result != null && result.size()>0){
			if("1".equals(result.get(0).toString())){
				obj = CommonsUtil.getRrturnJson("","" ,result.get(1).toString(), null, null);
			}else{
				obj = CommonsUtil.getRrturnJson("",result.get(1).toString() ,"", null, null);
			}
			
	
		}else{
			obj = CommonsUtil.getRrturnJson("","当日日报生成失败" ,"", null, null);
		}
		out.println(obj);
		return null;
	}
}
