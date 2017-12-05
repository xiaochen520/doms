package com.echo.service.impl;

import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.echo.dao.U1ZHRBDao;
import com.echo.dto.PcRpdCrudeTransitionT;
import com.echo.dto.PcRpdU1GeneralT;
import com.echo.service.U1ZHRBService;
import com.echo.util.CommonsUtil;
import com.echo.util.DateBean;

public class U1ZHRBServiceImpl implements U1ZHRBService{
	private U1ZHRBDao u1ZHRBDao;
	
	public void setU1ZHRBDao(U1ZHRBDao u1zhrbDao) {
		u1ZHRBDao = u1zhrbDao;
	}
	public JSONObject searchU1ZHRB( String rq )throws Exception{
		String sql =" select RQ, ";//diyihang  14 ge
				sql += "cyel,  cyoul,  wsyl,  kcyl,  ccl,  xll,  zxjyl, zxnd, cjgckfxyl, cjgckfxynd, cygckfxyl, cygckfxynd,  z1xhyryyclj,  z1xhyryycljnd, " ;
			//	分线计量 （左）	6
				sql +="z32nx,	z32bx,	z181x,	z1311x,	z1xhyry,xyt ,";
				// sagd井密闭站  15
				sql += "syzdtshyl,  syzccl,  syzcsl,  syzyl,  syzcyl,  syzyclj,  syzzxprj,  syzcshy,  syzcyhs,  syzwshyl , qfyl,	qfsl,	qflyl,	qfjsj,	qfznj,";
				//水区 原油处理即水质化验 13
				sql += "s1lsl,	s1fyq	,s1glcs,	s1hsl,	s1wp,  s2lsl,	s2qfjcll,	s2glcs,	s2wshsl,	s2jsjjyl,	s2jsjnd,	s2xnjjyl,	s2xnjnd ,";
				//水一区  16
				sql += "s1hytcgjk,	s1xfwtcgjk,	s1hytcgck,	s1xfwtcgck,	s1hyfygck,	s1xfwfygck,	s1hyyjck,	s1xfwyjck,	s1hyejck,	s1xfwejck , cjg11hs,	cjg12hs,	tsbhs,	gcs11hy,syzcshy,syzcyhs,";
				// 水二区  14
				sql += "s2hytcgjk,	s2xfwtcgjk,	s2hytcgck,	s2xfwtcgck	,s2hyqfjck,	s2xfwqfjck	,s2hyyjck,	s2xfwyjck,	s2hyejck,	s2xfwejck,jcjggcshy,jcjggcsxfw,	wfchshy	,wfchsxfw,";
				// 博达池及万方池运行情况
				sql += " bdczyw,	c1yw,	c2yw,	wfcyw,	dbczhssl,		t1xynl,	t2xynl,	cyzfyl,	jsj	,znj,";
				//审核人
				sql +="RPD_U1_GENERAL_ID, zbr,  shr, bz ,  shsj,  tbr,  tbsj ,";
				//油区指标
				sql +="gcs12hy, syzhszhyl, bdcsy";
				
				sql +=  "  from  pc_rpd_u1_general_t a where 1=1  ";
				
				//sql += " and RQ TO_DATE('"+rq+"','YYYY-MM-DD') order by RQ ";
				String beforetime = DateBean.getBeforeDAYTime(rq);
				sql += " and RQ between TO_DATE('"+beforetime+"','YYYY-MM-DD') and TO_DATE('"+rq+"','YYYY-MM-DD') order by RQ ";
				
				String sqla = "select   gh	,	gyw,	dyw	,sm	,bm	,gw	,hs	,myl	,cyl ,	sl, RQ ,RPD_CRUDE_TRANSITION_ID   from  pc_rpd_crude_transition_t";
						sqla += "  where  1=1  and   org_id='239068FA3724460581219B7CD401B386' and RQ =TO_DATE('"+rq+"','YYYY-MM-DD') and rownum =1 order by RQ ,gh";

				String  sqlb = "select  *   from  ( select  row_number() over(order by  gh) as xuhao,"+
						" gh , gyw, dyw , sm , bm , gw , hs , myl , cyl , sl, RQ , RPD_CRUDE_TRANSITION_ID    from "+
						" pc_rpd_crude_transition_t   where  1=1  and   org_id='239068FA3724460581219B7CD401B386'  "+
						" and RQ =TO_DATE('"+rq+"','YYYY-MM-DD')   ) where  xuhao =2";		
		List<Object[]>  listMain = u1ZHRBDao.searchMain(sql);
		List<Object[]>  listOil  = u1ZHRBDao.searchMain(sqla);
		List<Object[]>  listOilb  = u1ZHRBDao.searchMain(sqlb);
		List<Object[]> 	dataSet = null;
			if(listMain == null || listMain.size() ==0 ){
				List<String> returnmsg = u1ZHRBDao.getProcedures("p_gene_d_u1_general_g",rq);
				if("1".equals(returnmsg.get(0)) || returnmsg.get(1).indexOf("日报数据，不能再次生成") != -1){
					dataSet = u1ZHRBDao.searchMain(sql);
				}else{
					
				}
			}
			JSONObject obj = new JSONObject();
			obj = GetJSonObj(listMain,listOil,listOilb,dataSet,beforetime,rq);
	return obj;
		
	}
	public JSONObject GetJSonObj(List<Object[]> listMain ,List<Object[]> listOil,List<Object[]>listOilb, List<Object[]> dataSet,String beforetime,String nowDate){
		
		int yeardayflag = 0; // 昨日数据存在   0：不存在，1存在
		int nowflag = 0; // 今日数据存在   0：不存在，1存在
		Object[] yearData = null;
		Object[]  nowData = null;
		
		for(Object[] data:listMain){
			if(data[0] != null && beforetime.equals(String.valueOf(data[0]))){
				yeardayflag = 1;
				yearData = data;
			}
			
			if(data[0] != null && nowDate.equals(String.valueOf(data[0]))){
				nowflag = 1; 
				nowData = data;
			}
		}
		JSONObject  obj =null;
		JSONArray   Fiarr =null;
		JSONArray   Searr = null;
		JSONArray   Tharr =null;
		JSONArray   Foarr = new JSONArray();
		JSONArray   Fivearr = null;
	
		 obj = new JSONObject();
		obj.put("LINEDATA", "昨日");   //行标识 ：昨日，今日，增减
		if(yeardayflag == 1){
			//昨日
			Fiarr = new JSONArray();
			obj.put("cyel", CommonsUtil.getIntData(yearData[1]));
			obj.put("cyoul", CommonsUtil.getIntData(yearData[2]));
			obj.put("wsyl", CommonsUtil.getIntData(yearData[3]));
			obj.put("kcyl", CommonsUtil.getIntData(yearData[4]));
			obj.put("ccl", CommonsUtil.getIntData(yearData[5]));
			obj.put("xll", CommonsUtil.getIntData(yearData[6]));
			obj.put("zxjyl", CommonsUtil.getIntData(yearData[7]));
			obj.put("zxnd", CommonsUtil.getIntData(yearData[8]));
			obj.put("cjgckfxyl", CommonsUtil.getIntData(yearData[9]));
			obj.put("cjgckfxynd", CommonsUtil.getIntData(yearData[10]));
			obj.put("cygckfxyl", CommonsUtil.getIntData(yearData[11]));
			obj.put("cygckfxynd", CommonsUtil.getIntData(yearData[12]));
			obj.put("z1xhyryyclj", CommonsUtil.getIntData(yearData[13]));
			obj.put("z1xhyryycljnd", CommonsUtil.getIntData(yearData[14]));
			
			obj.put("z32nx", CommonsUtil.getIntData(yearData[15]));
			obj.put("z32bx", CommonsUtil.getIntData(yearData[16]));
			obj.put("z181x", CommonsUtil.getIntData(yearData[17]));
			obj.put("z1311x", CommonsUtil.getIntData(yearData[18]));
			obj.put("z1xhyry", CommonsUtil.getIntData(yearData[19]));
			obj.put("xyt", CommonsUtil.getIntData(yearData[20]));
			
			obj.put("syzdtshyl", CommonsUtil.getIntData(yearData[21]));
			obj.put("syzccl", CommonsUtil.getIntData(yearData[22]));
			obj.put("syzcsl", CommonsUtil.getIntData(yearData[23]));
			obj.put("syzyl", CommonsUtil.getIntData(yearData[24]));
			obj.put("syzcyl", CommonsUtil.getIntData(yearData[25]));
			obj.put("syzyclj", CommonsUtil.getIntData(yearData[26]));
			obj.put("syzzxprj", CommonsUtil.getIntData(yearData[27]));
			obj.put("syzcshy", CommonsUtil.getIntData(yearData[28]));
			obj.put("syzcyhs", CommonsUtil.getIntData(yearData[29]));
			obj.put("syzwshyl", CommonsUtil.getIntData(yearData[30]));
			obj.put("qfyl", CommonsUtil.getIntData(yearData[31]));
			obj.put("qfsl", CommonsUtil.getIntData(yearData[32]));
			obj.put("qflyl", CommonsUtil.getIntData(yearData[33]));
			obj.put("qfjsj", CommonsUtil.getIntData(yearData[34]));
			obj.put("qfznj", CommonsUtil.getIntData(yearData[35]));
			
			obj.put("s1lsl", CommonsUtil.getIntData(yearData[36]));
			obj.put("s1fyq", CommonsUtil.getIntData(yearData[37]));
			obj.put("s1glcs", CommonsUtil.getIntData(yearData[38]));
			obj.put("s1hsl", CommonsUtil.getIntData(yearData[39]));
			obj.put("s1wp", CommonsUtil.getIntData(yearData[40]));
			obj.put("s2lsl", CommonsUtil.getIntData(yearData[41]));
			obj.put("s2qfjcll", CommonsUtil.getIntData(yearData[42]));
			obj.put("s2glcs", CommonsUtil.getIntData(yearData[43]));
			obj.put("s2wshsl", CommonsUtil.getIntData(yearData[44]));
			obj.put("s2jsjjyl", CommonsUtil.getIntData(yearData[45]));
			obj.put("s2jsjnd", CommonsUtil.getIntData(yearData[46]));
			obj.put("s2xnjjyl", CommonsUtil.getIntData(yearData[47]));
			obj.put("s2xnjnd", CommonsUtil.getIntData(yearData[48]));
			
//			obj.put("s1hytcgjk", CommonsUtil.getIntData(yearData[49]));
//			obj.put("s1xfwtcgjk", CommonsUtil.getIntData(yearData[50]));
//			obj.put("s1hytcgck", CommonsUtil.getIntData(yearData[51]));
//			obj.put("s1xfwtcgck", CommonsUtil.getIntData(yearData[52]));
//			
//			obj.put("s1hyfygck", CommonsUtil.getNotTwoData(yearData[53]));
//			obj.put("s1xfwfygck", CommonsUtil.getNotTwoData(yearData[54]));
//			obj.put("s1hyyjck", CommonsUtil.getNotTwoData(yearData[55]));
//			obj.put("s1xfwyjck", CommonsUtil.getNotTwoData(yearData[56]));
//			obj.put("s1hyejck", CommonsUtil.getNotTwoData(yearData[57]));
//			obj.put("s1xfwejck", CommonsUtil.getNotTwoData(yearData[58]));
//			obj.put("cjg11hs", CommonsUtil.getNotOneData(yearData[59]));
//			obj.put("cjg12hs", CommonsUtil.getNotOneData(yearData[60]));
//			obj.put("tsbhs", 	CommonsUtil.getNotOneData(yearData[61]));
//			obj.put("gcs11hy", CommonsUtil.getNotOneData(yearData[62]));
//			obj.put("syzcshy", CommonsUtil.getIntData(yearData[63]));
//			obj.put("syzcyhs", CommonsUtil.getNotTwoData(yearData[64]));
//			
//			obj.put("s2hytcgjk", CommonsUtil.getNotTwoData(yearData[65]));
//			obj.put("s2xfwtcgjk", CommonsUtil.getNotTwoData(yearData[66]));
//			obj.put("s2hytcgck", CommonsUtil.getNotTwoData(yearData[67]));
//			obj.put("s2xfwtcgck", CommonsUtil.getNotTwoData(yearData[68]));
//			obj.put("s2hyqfjck", CommonsUtil.getNotTwoData(yearData[69]));
//			obj.put("s2xfwqfjck", CommonsUtil.getNotTwoData(yearData[70]));
//			obj.put("s2hyyjck", CommonsUtil.getNotTwoData(yearData[71]));
//			obj.put("s2xfwyjck", CommonsUtil.getNotTwoData(yearData[72]));
//			obj.put("s2hyejck", CommonsUtil.getNotTwoData(yearData[73]));
//			obj.put("s2xfwejck", CommonsUtil.getNotTwoData(yearData[74]));
//			obj.put("jcjggcshy", CommonsUtil.getIntData(yearData[75]));
//			obj.put("jcjggcsxfw", CommonsUtil.getIntData(yearData[76]));
//			obj.put("wfchshy", CommonsUtil.getIntData(yearData[77]));
//			obj.put("wfchsxfw", CommonsUtil.getIntData(yearData[78]));

			obj.put("bdczyw", CommonsUtil.getNotTwoData(yearData[79]));
			obj.put("c1yw", CommonsUtil.getNotTwoData(yearData[80]));
			obj.put("c2yw", CommonsUtil.getNotTwoData(yearData[81]));
			obj.put("wfcyw", CommonsUtil.getNotTwoData(yearData[82]));
			obj.put("dbczhssl", CommonsUtil.getIntData(yearData[83]));
			obj.put("t1xynl", CommonsUtil.getIntData(yearData[84]));
			obj.put("t2xynl", CommonsUtil.getIntData(yearData[85]));
			obj.put("cyzfyl", CommonsUtil.getIntData(yearData[86]));
			obj.put("jsj", CommonsUtil.getIntData(yearData[87]));
			obj.put("znj", CommonsUtil.getIntData(yearData[88]));
			
			obj.put("syzhszhyl", CommonsUtil.getNotTwoData(yearData[97]));
			obj.put("bdcsy", CommonsUtil.getNotTwoData(yearData[98]));
			Fiarr.add(obj);
		}else{
			Fiarr = new JSONArray();
			//昨日
			obj.put("cyel", "");
			obj.put("cyoul",  "");
			obj.put("wsyl",  "");
			obj.put("kcyl",  "");
			obj.put("ccl",  "");
			obj.put("xll",  "");
			obj.put("zxjyl", "");
			obj.put("zxnd",  "");
			obj.put("cjgckfxyl",  "");
			obj.put("cjgckfxynd", "");
			obj.put("cygckfxyl",  "");
			obj.put("cygckfxynd",  "");
			obj.put("z1xhyryyclj",  "");
			obj.put("z1xhyryycljnd", "");
			
			obj.put("z32nx", "");
			obj.put("z32bx", "");
			obj.put("z181x", "");
			obj.put("z1311x",  "");
			obj.put("z1xhyry", "");
			obj.put("xyt", "");
			
			obj.put("syzdtshyl",  "");
			obj.put("syzccl",  "");
			obj.put("syzcsl",  "");
			obj.put("syzyl",  "");
			obj.put("syzcyl",  "");
			obj.put("syzyclj", "");
			obj.put("syzzxprj",  "");
			obj.put("syzcshy", "");
			obj.put("syzcyhs", "");
			obj.put("syzwshyl",  "");
			obj.put("qfyl", "");
			obj.put("qfsl",  "");
			obj.put("qflyl",  "");
			obj.put("qfjsj", "");
			obj.put("qfznj",  "");
			
			obj.put("s1lsl", "");
			obj.put("s1fyq",  "");
			obj.put("s1glcs", "");
			obj.put("s1hsl",  "");
			obj.put("s1wp", "");
			obj.put("s2lsl",  "");
			obj.put("s2qfjcll",  "");
			obj.put("s2glcs",  "");
			obj.put("s2wshsl", "");
			obj.put("s2jsjjyl", "");
			obj.put("s2jsjnd", "");
			obj.put("s2xnjjyl", "");
			obj.put("s2xnjnd",  "");
			
//			obj.put("s1hytcgjk",  "");
//			obj.put("s1xfwtcgjk", "");
//			obj.put("s1hytcgck", "");
//			obj.put("s1xfwtcgck",  "");
//			
//			obj.put("s1hyfygck",  "");
//			obj.put("s1xfwfygck",  "");
//			obj.put("s1hyyjck",  "");
//			obj.put("s1xfwyjck", "");
//			obj.put("s1hyejck",  "");
//			obj.put("s1xfwejck",  "");
//			obj.put("cjg11hs",  "");
//			obj.put("cjg12hs",  "");
//			obj.put("tsbhs", 	 "");
//			obj.put("gcs11hy",  "");
//			obj.put("syzcshy",  "");
//			obj.put("syzcyhs",  "");
//			
//			obj.put("s2hytcgjk",  "");
//			obj.put("s2xfwtcgjk",  "");
//			obj.put("s2hytcgck",  "");
//			obj.put("s2xfwtcgck", "");
//			obj.put("s2hyqfjck", "");
//			obj.put("s2xfwqfjck",  "");
//			obj.put("s2hyyjck",  "");
//			obj.put("s2xfwyjck",  "");
//			obj.put("s2hyejck",  "");
//			obj.put("s2xfwejck",  "");
//			obj.put("jcjggcshy",  "");
//			obj.put("jcjggcsxfw",  "");
//			obj.put("wfchshy", "");
//			obj.put("wfchsxfw",  "");

			obj.put("bdczyw",  "");
			obj.put("c1yw",  "");
			obj.put("c2yw",  "");
			obj.put("wfcyw",  "");
			obj.put("dbczhssl",  "");
			obj.put("t1xynl",  "");
			obj.put("t2xynl",  "");
			obj.put("cyzfyl",  "");
			obj.put("jsj",  "");
			obj.put("znj", "");
			
			obj.put("syzhszhyl", "");
			obj.put("bdcsy", "");
			Fiarr.add(obj);
		}
		 obj = new JSONObject();
		obj.put("LINEDATA", "今日");
		if(nowflag == 1){
			Searr = new JSONArray();
			obj.put("cyel", CommonsUtil.getIntData(nowData[1]));
			obj.put("cyoul", CommonsUtil.getIntData(nowData[2]));
			obj.put("wsyl", CommonsUtil.getIntData(nowData[3]));
			obj.put("kcyl", CommonsUtil.getIntData(nowData[4]));
			obj.put("ccl", CommonsUtil.getIntData(nowData[5]));
			obj.put("xll", CommonsUtil.getIntData(nowData[6]));
			obj.put("zxjyl", CommonsUtil.getIntData(nowData[7]));
			obj.put("zxnd", CommonsUtil.getIntData(nowData[8]));
			obj.put("cjgckfxyl", CommonsUtil.getIntData(nowData[9]));
			obj.put("cjgckfxynd", CommonsUtil.getIntData(nowData[10]));
			obj.put("cygckfxyl", CommonsUtil.getIntData(nowData[11]));
			obj.put("cygckfxynd", CommonsUtil.getIntData(nowData[12]));
			obj.put("z1xhyryyclj", CommonsUtil.getIntData(nowData[13]));
			obj.put("z1xhyryycljnd", CommonsUtil.getIntData(nowData[14]));
			
			obj.put("z32nx", CommonsUtil.getIntData(nowData[15]));
			obj.put("z32bx", CommonsUtil.getIntData(nowData[16]));
			obj.put("z181x", CommonsUtil.getIntData(nowData[17]));
			obj.put("z1311x", CommonsUtil.getIntData(nowData[18]));
			obj.put("z1xhyry", CommonsUtil.getIntData(nowData[19]));
			obj.put("xyt", CommonsUtil.getIntData(nowData[20]));
			
			obj.put("syzdtshyl", CommonsUtil.getIntData(nowData[21]));
			obj.put("syzccl", CommonsUtil.getIntData(nowData[22]));
			obj.put("syzcsl", CommonsUtil.getIntData(nowData[23]));
			obj.put("syzyl", CommonsUtil.getIntData(nowData[24]));
			obj.put("syzcyl", CommonsUtil.getIntData(nowData[25]));
			obj.put("syzyclj", CommonsUtil.getIntData(nowData[26]));
			obj.put("syzzxprj", CommonsUtil.getIntData(nowData[27]));
			obj.put("syzcshy", CommonsUtil.getIntData(nowData[28]));
			obj.put("syzcyhs", CommonsUtil.getIntData(nowData[29]));
			obj.put("syzwshyl", CommonsUtil.getIntData(nowData[30]));
			obj.put("qfyl", CommonsUtil.getIntData(nowData[31]));
			obj.put("qfsl", CommonsUtil.getIntData(nowData[32]));
			obj.put("qflyl", CommonsUtil.getIntData(nowData[33]));
			obj.put("qfjsj", CommonsUtil.getIntData(nowData[34]));
			obj.put("qfznj", CommonsUtil.getIntData(nowData[35]));
			
			obj.put("s1lsl", CommonsUtil.getIntData(nowData[36]));
			obj.put("s1fyq", CommonsUtil.getIntData(nowData[37]));
			obj.put("s1glcs", CommonsUtil.getIntData(nowData[38]));
			obj.put("s1hsl", CommonsUtil.getIntData(nowData[39]));
			obj.put("s1wp", CommonsUtil.getIntData(nowData[40]));
			obj.put("s2lsl", CommonsUtil.getIntData(nowData[41]));
			obj.put("s2qfjcll", CommonsUtil.getIntData(nowData[42]));
			obj.put("s2glcs", CommonsUtil.getIntData(nowData[43]));
			obj.put("s2wshsl", CommonsUtil.getIntData(nowData[44]));
			obj.put("s2jsjjyl", CommonsUtil.getIntData(nowData[45]));
			obj.put("s2jsjnd", CommonsUtil.getIntData(nowData[46]));
			obj.put("s2xnjjyl", CommonsUtil.getIntData(nowData[47]));
			obj.put("s2xnjnd", CommonsUtil.getIntData(nowData[48]));
			
			obj.put("s1hytcgjk", CommonsUtil.getIntData(nowData[49]));
			obj.put("s1xfwtcgjk", CommonsUtil.getIntData(nowData[50]));
			obj.put("s1hytcgck", CommonsUtil.getIntData(nowData[51]));
			obj.put("s1xfwtcgck", CommonsUtil.getIntData(nowData[52]));
			
			obj.put("s1hyfygck", CommonsUtil.getNotTwoData(nowData[53]));
			obj.put("s1xfwfygck", CommonsUtil.getNotTwoData(nowData[54]));
			obj.put("s1hyyjck", CommonsUtil.getNotTwoData(nowData[55]));
			obj.put("s1xfwyjck", CommonsUtil.getNotTwoData(nowData[56]));
			obj.put("s1hyejck", CommonsUtil.getNotTwoData(nowData[57]));
			obj.put("s1xfwejck", CommonsUtil.getNotTwoData(nowData[58]));
			obj.put("cjg11hs", CommonsUtil.getNotOneData(nowData[59]));
			obj.put("cjg12hs", CommonsUtil.getNotOneData(nowData[60]));
			obj.put("tsbhs", 	CommonsUtil.getNotOneData(nowData[61]));
			obj.put("gcs11hy", CommonsUtil.getNotOneData(nowData[62]));
			obj.put("syzcshy", CommonsUtil.getIntData(nowData[63]));
			obj.put("syzcyhs", CommonsUtil.getNotTwoData(nowData[64]));
			
			obj.put("s2hytcgjk", CommonsUtil.getNotTwoData(nowData[65]));
			obj.put("s2xfwtcgjk", CommonsUtil.getNotTwoData(nowData[66]));
			obj.put("s2hytcgck", CommonsUtil.getNotTwoData(nowData[67]));
			obj.put("s2xfwtcgck", CommonsUtil.getNotTwoData(nowData[68]));
			obj.put("s2hyqfjck", CommonsUtil.getNotTwoData(nowData[69]));
			obj.put("s2xfwqfjck", CommonsUtil.getNotTwoData(nowData[70]));
			obj.put("s2hyyjck", CommonsUtil.getNotTwoData(nowData[71]));
			obj.put("s2xfwyjck", CommonsUtil.getNotTwoData(nowData[72]));
			obj.put("s2hyejck", CommonsUtil.getNotTwoData(nowData[73]));
			obj.put("s2xfwejck", CommonsUtil.getNotTwoData(nowData[74]));
			obj.put("jcjggcshy", CommonsUtil.getIntData(nowData[75]));
			obj.put("jcjggcsxfw", CommonsUtil.getIntData(nowData[76]));
			obj.put("wfchshy", CommonsUtil.getIntData(nowData[77]));
			obj.put("wfchsxfw", CommonsUtil.getIntData(nowData[78]));

			obj.put("bdczyw", CommonsUtil.getNotTwoData(nowData[79]));
			obj.put("c1yw", CommonsUtil.getNotTwoData(nowData[80]));
			obj.put("c2yw", CommonsUtil.getNotTwoData(nowData[81]));
			obj.put("wfcyw", CommonsUtil.getNotTwoData(nowData[82]));
			obj.put("dbczhssl", CommonsUtil.getIntData(nowData[83]));
			obj.put("t1xynl", CommonsUtil.getIntData(nowData[84]));
			obj.put("t2xynl", CommonsUtil.getIntData(nowData[85]));
			obj.put("cyzfyl", CommonsUtil.getIntData(nowData[86]));
			obj.put("jsj", CommonsUtil.getIntData(nowData[87]));
			obj.put("znj", CommonsUtil.getIntData(nowData[88]));
			//sql +="RPD_U1_GENERAL_ID, zbr,  shr,  shsj,  tbr,  tbsj";
			
			obj.put("RPD_U1_GENERAL_ID",nowData[89].toString());
			if(nowData[90] !=null && !"".equals(nowData[90])){
				obj.put("zbr", nowData[90]);	
			}else{
				obj.put("zbr", "");
			}
			if(nowData[91] !=null && !"".equals(nowData[91])){
				obj.put("shr", nowData[91]);
			}else{
				obj.put("shr", "");
			}	
			if(nowData[92] !=null && !"".equals(nowData[92])){
				obj.put("bz", nowData[92]);
			}else{
				obj.put("bz", "");
			}
			if(nowData[0] !=null && !"".equals(nowData[0])){
				obj.put("rq", nowData[0].toString());
			}else{
				obj.put("rq", "");
			}
			
			/*if(nowData[0] !=null && !"".equals(nowData[96])){
				obj.put("gcs12hy", nowData[96].toString());
			}else{
				obj.put("gcs12hy", "");
			}*/
			
			obj.put("gcs12hy", CommonsUtil.getNotTwoData(nowData[96]));
			obj.put("syzhszhyl", CommonsUtil.getNotTwoData(nowData[97]));
			obj.put("bdcsy", CommonsUtil.getNotTwoData(nowData[98]));
			Searr.add(obj);
		}else{
			Searr = new JSONArray();
			obj.put("cyel", "");
			obj.put("cyoul",  "");
			obj.put("wsyl",  "");
			obj.put("kcyl",  "");
			obj.put("ccl",  "");
			obj.put("xll",  "");
			obj.put("zxjyl", "");
			obj.put("zxnd",  "");
			obj.put("cjgckfxyl",  "");
			obj.put("cjgckfxynd", "");
			obj.put("cygckfxyl",  "");
			obj.put("cygckfxynd",  "");
			obj.put("z1xhyryyclj",  "");
			obj.put("z1xhyryycljnd", "");
			
			obj.put("z32nx", "");
			obj.put("z32bx", "");
			obj.put("z181x", "");
			obj.put("z1311x",  "");
			obj.put("z1xhyry", "");
			obj.put("xyt", "");
			
			obj.put("syzdtshyl",  "");
			obj.put("syzccl",  "");
			obj.put("syzcsl",  "");
			obj.put("syzyl",  "");
			obj.put("syzcyl",  "");
			obj.put("syzyclj", "");
			obj.put("syzzxprj",  "");
			obj.put("syzcshy", "");
			obj.put("syzcyhs", "");
			obj.put("syzwshyl",  "");
			obj.put("qfyl", "");
			obj.put("qfsl",  "");
			obj.put("qflyl",  "");
			obj.put("qfjsj", "");
			obj.put("qfznj",  "");
			
			obj.put("s1lsl", "");
			obj.put("s1fyq",  "");
			obj.put("s1glcs", "");
			obj.put("s1hsl",  "");
			obj.put("s1wp", "");
			obj.put("s2lsl",  "");
			obj.put("s2qfjcll",  "");
			obj.put("s2glcs",  "");
			obj.put("s2wshsl", "");
			obj.put("s2jsjjyl", "");
			obj.put("s2jsjnd", "");
			obj.put("s2xnjjyl", "");
			obj.put("s2xnjnd",  "");
			
			obj.put("s1hytcgjk",  "");
			obj.put("s1xfwtcgjk", "");
			obj.put("s1hytcgck", "");
			obj.put("s1xfwtcgck",  "");
			
			obj.put("s1hyfygck",  "");
			obj.put("s1xfwfygck",  "");
			obj.put("s1hyyjck",  "");
			obj.put("s1xfwyjck", "");
			obj.put("s1hyejck",  "");
			obj.put("s1xfwejck",  "");
			obj.put("cjg11hs",  "");
			obj.put("cjg12hs",  "");
			obj.put("tsbhs", 	 "");
			obj.put("gcs11hy",  "");
			obj.put("syzcshy",  "");
			obj.put("syzcyhs",  "");
			
			obj.put("s2hytcgjk",  "");
			obj.put("s2xfwtcgjk",  "");
			obj.put("s2hytcgck",  "");
			obj.put("s2xfwtcgck", "");
			obj.put("s2hyqfjck", "");
			obj.put("s2xfwqfjck",  "");
			obj.put("s2hyyjck",  "");
			obj.put("s2xfwyjck",  "");
			obj.put("s2hyejck",  "");
			obj.put("s2xfwejck",  "");
			obj.put("jcjggcshy",  "");
			obj.put("jcjggcsxfw",  "");
			obj.put("wfchshy", "");
			obj.put("wfchsxfw",  "");

			obj.put("bdczyw",  "");
			obj.put("c1yw",  "");
			obj.put("c2yw",  "");
			obj.put("wfcyw",  "");
			obj.put("dbczhssl",  "");
			obj.put("t1xynl",  "");
			obj.put("t2xynl",  "");
			obj.put("cyzfyl",  "");
			obj.put("jsj",  "");
			obj.put("znj", "");
			obj.put("zbr", "");
			obj.put("shr", "");
			obj.put("bz", "");
			obj.put("rq", "");
			
			obj.put("gcs12hy", "");
			obj.put("syzhszhyl", "");
			obj.put("bdcsy", "");
			Searr.add(obj);
		}
		obj = new JSONObject();
		obj.put("LINEDATA", "增减");
		if( yeardayflag ==1 && nowflag ==1 ){
		Tharr= new JSONArray();
			obj.put("cyel", CommonsUtil.getRegulationZero(nowData[1],yearData[1]));
			obj.put("cyoul", CommonsUtil.getRegulationZero(nowData[2], yearData[2]));
			obj.put("wsyl", CommonsUtil.getRegulationZero(nowData[3], yearData[3]));
			obj.put("kcyl", CommonsUtil.getRegulationZero(nowData[4], yearData[4]));
			obj.put("ccl", CommonsUtil.getRegulationZero(nowData[5], yearData[5]));
			obj.put("xll", CommonsUtil.getRegulationZero(nowData[6], yearData[6]));
			obj.put("zxjyl", CommonsUtil.getRegulationZero(nowData[7], yearData[7]));
			obj.put("zxnd", CommonsUtil.getRegulationZero(nowData[8], yearData[8]));
			obj.put("cjgckfxyl", CommonsUtil.getRegulationZero(nowData[9], yearData[9]));
			obj.put("cjgckfxynd", CommonsUtil.getRegulationZero(nowData[10], yearData[10]));
			obj.put("cygckfxyl", CommonsUtil.getRegulationZero(nowData[11], yearData[11]));
			obj.put("cygckfxynd", CommonsUtil.getRegulationZero(nowData[12], yearData[12]));
			obj.put("z1xhyryyclj", CommonsUtil.getRegulationZero(nowData[13], yearData[13]));
			obj.put("z1xhyryycljnd", CommonsUtil.getRegulationZero(nowData[14], yearData[14]));
			
			obj.put("z32nx", CommonsUtil.getRegulationZero(nowData[15], yearData[15]));
			obj.put("z32bx", CommonsUtil.getRegulationZero(nowData[16], yearData[16]));
			obj.put("z181x", CommonsUtil.getRegulationZero(nowData[17], yearData[17]));
			obj.put("z1311x", CommonsUtil.getRegulationZero(nowData[18], yearData[18]));
			obj.put("z1xhyry", CommonsUtil.getRegulationZero(nowData[19], yearData[19]));
			if(nowData[20] != null && !"".equals(nowData[20]) && yearData[20] != null && !"".equals(yearData[20])){
				obj.put("xyt", CommonsUtil.getRegulationZero(nowData[20], yearData[20]));
			}else{
				obj.put("xyt", "");
			}
			obj.put("syzdtshyl", CommonsUtil.getRegulationZero(nowData[21], yearData[21]));
			obj.put("syzccl", CommonsUtil.getRegulationZero(nowData[22], yearData[22]));
			obj.put("syzcsl", CommonsUtil.getRegulationZero(nowData[23], yearData[23]));
			obj.put("syzyl", CommonsUtil.getRegulationZero(nowData[24], yearData[24]));
			obj.put("syzcyl", CommonsUtil.getRegulationZero(nowData[25], yearData[25]));
			obj.put("syzyclj", CommonsUtil.getRegulationZero(nowData[26], yearData[26]));
			obj.put("syzzxprj", CommonsUtil.getRegulationZero(nowData[27], yearData[27]));
			obj.put("syzcshy", CommonsUtil.getRegulationZero(nowData[28], yearData[28]));
			obj.put("syzcyhs", CommonsUtil.getRegulationZero(nowData[29], yearData[29]));
			obj.put("syzwshyl", CommonsUtil.getRegulationZero(nowData[30], yearData[30]));
			obj.put("qfyl", CommonsUtil.getRegulationZero(nowData[31], yearData[31]));
			obj.put("qfsl", CommonsUtil.getRegulationZero(nowData[32], yearData[32]));
			obj.put("qflyl", CommonsUtil.getRegulationZero(nowData[33], yearData[33]));
			obj.put("qfjsj", CommonsUtil.getRegulationZero(nowData[34], yearData[34]));
			obj.put("qfznj", CommonsUtil.getRegulationZero(nowData[35], yearData[35]));
			
			obj.put("s1lsl", CommonsUtil.getRegulationZero(nowData[36], yearData[36]));
			obj.put("s1fyq", CommonsUtil.getRegulationZero(nowData[37], yearData[37]));
			obj.put("s1glcs", CommonsUtil.getRegulationZero(nowData[38], yearData[38]));
			obj.put("s1hsl", CommonsUtil.getRegulationZero(nowData[39], yearData[39]));
			obj.put("s1wp", CommonsUtil.getRegulationZero(nowData[40], yearData[40]));
			obj.put("s2lsl", CommonsUtil.getRegulationZero(nowData[41], yearData[41]));
			obj.put("s2qfjcll", CommonsUtil.getRegulationZero(nowData[42], yearData[42]));
			obj.put("s2glcs", CommonsUtil.getRegulationZero(nowData[43], yearData[43]));
			obj.put("s2wshsl", CommonsUtil.getRegulationZero(nowData[44], yearData[44]));
			obj.put("s2jsjjyl", CommonsUtil.getRegulationZero(nowData[45], yearData[45]));
			obj.put("s2jsjnd", CommonsUtil.getRegulationZero(nowData[46], yearData[46]));
			obj.put("s2xnjjyl", CommonsUtil.getRegulationZero(nowData[47], yearData[47]));
			obj.put("s2xnjnd", CommonsUtil.getRegulationZero(nowData[48], yearData[48]));
			
//			obj.put("s1hytcgjk", CommonsUtil.getRegulationZero(nowData[49], yearData[49]));
//			obj.put("s1xfwtcgjk", CommonsUtil.getRegulationZero(nowData[50], yearData[50]));
//			obj.put("s1hytcgck", CommonsUtil.getRegulationZero(nowData[51], yearData[51]));
//			obj.put("s1xfwtcgck", CommonsUtil.getRegulationZero(nowData[52], yearData[52]));
//			
//			obj.put("s1hyfygck", CommonsUtil.getRegulationZero(nowData[53], yearData[53]));
//			obj.put("s1xfwfygck", CommonsUtil.getRegulationZero(nowData[54], yearData[54]));
//			obj.put("s1hyyjck", CommonsUtil.getRegulationZero(nowData[55], yearData[55]));
//			obj.put("s1xfwyjck", CommonsUtil.getRegulationZero(nowData[56], yearData[56]));
//			obj.put("s1hyejck", CommonsUtil.getRegulationZero(nowData[57], yearData[57]));
//			obj.put("s1xfwejck", CommonsUtil.getRegulationZero(nowData[58], yearData[58]));
//			obj.put("cjg11hs", CommonsUtil.getRegulationZero(nowData[59], yearData[59]));
//			obj.put("cjg12hs", CommonsUtil.getRegulationZero(nowData[60], yearData[60]));
//			obj.put("tsbhs", 	CommonsUtil.getRegulationZero(nowData[61], yearData[61]));
//			obj.put("gcs11hy", CommonsUtil.getRegulationZero(nowData[62], yearData[62]));
//			obj.put("syzcshy", CommonsUtil.getRegulationZero(nowData[63], yearData[63]));
//			obj.put("syzcyhs", CommonsUtil.getRegulationZero(nowData[64], yearData[64]));
//			
//			obj.put("s2hytcgjk", CommonsUtil.getRegulationZero(nowData[65], yearData[65]));
//			obj.put("s2xfwtcgjk", CommonsUtil.getRegulationZero(nowData[66], yearData[66]));
//			obj.put("s2hytcgck", CommonsUtil.getRegulationZero(nowData[67], yearData[67]));
//			obj.put("s2xfwtcgck", CommonsUtil.getRegulationZero(nowData[68], yearData[68]));
//			obj.put("s2hyqfjck", CommonsUtil.getRegulationZero(nowData[69], yearData[69]));
//			obj.put("s2xfwqfjck", CommonsUtil.getRegulationZero(nowData[70], yearData[70]));
//			obj.put("s2hyyjck", CommonsUtil.getRegulationZero(nowData[71], yearData[71]));
//			obj.put("s2xfwyjck", CommonsUtil.getRegulationZero(nowData[72], yearData[72]));
//			obj.put("s2hyejck", CommonsUtil.getRegulationZero(nowData[73], yearData[73]));
//			obj.put("s2xfwejck", CommonsUtil.getRegulationZero(nowData[74], yearData[74]));
//			obj.put("jcjggcshy", CommonsUtil.getRegulationZero(nowData[75], yearData[75]));
//			obj.put("jcjggcsxfw", CommonsUtil.getRegulationZero(nowData[76], yearData[76]));
//			obj.put("wfchshy", CommonsUtil.getRegulationZero(nowData[77], yearData[77]));
//			obj.put("wfchsxfw", CommonsUtil.getRegulationZero(nowData[78], yearData[78]));

			obj.put("bdczyw", CommonsUtil.getRegulation2(nowData[79], yearData[79]));
			obj.put("c1yw", CommonsUtil.getRegulation2(nowData[80], yearData[80]));
			obj.put("c2yw", CommonsUtil.getRegulation2(nowData[81], yearData[81]));
			obj.put("wfcyw", CommonsUtil.getRegulation2(nowData[82], yearData[82]));
			obj.put("dbczhssl", CommonsUtil.getRegulationZero(nowData[83], yearData[83]));
			obj.put("t1xynl", CommonsUtil.getRegulationZero(nowData[84], yearData[84]));
			obj.put("t2xynl", CommonsUtil.getRegulationZero(nowData[85], yearData[85]));
			obj.put("cyzfyl", CommonsUtil.getRegulationZero(nowData[86], yearData[86]));
			obj.put("jsj", CommonsUtil.getRegulationZero(nowData[87], yearData[87]));
			obj.put("znj", CommonsUtil.getRegulationZero(nowData[88], yearData[88]));
			
			obj.put("syzhszhyl", CommonsUtil.getRegulationZero(nowData[97], yearData[97]));
			obj.put("bdcsy", CommonsUtil.getRegulationZero(nowData[98], yearData[98]));

			Tharr.add(obj);
		}else{
			Tharr = new JSONArray();
			obj= new JSONObject();
			obj.put("cyel", "");
			obj.put("cyoul", "");
			obj.put("wsyl","");
			obj.put("kcyl", "");
			obj.put("ccl", "");
			obj.put("xll", "");
			obj.put("zxjyl","");
			obj.put("zxnd", "");
			obj.put("cjgckfxyl", "");
			obj.put("cjgckfxynd","");
			obj.put("cygckfxyl", "");
			obj.put("cygckfxynd", "");
			obj.put("z1xhyryyclj", "");
			obj.put("z1xhyryycljnd", "");
			
			obj.put("z32nx","");
			obj.put("z32bx", "");
			obj.put("z181x", "");
			obj.put("z1311x", "");
			obj.put("z1xhyry","");
			obj.put("xyt", "");
			
			obj.put("syzdtshyl","");
			obj.put("syzccl", "");
			obj.put("syzcsl", "");
			obj.put("syzyl", "");
			obj.put("syzcyl", "");
			obj.put("syzyclj", "");
			obj.put("syzzxprj","");
			obj.put("syzcshy", "");
			obj.put("syzcyhs","");
			obj.put("syzwshyl", "");
			obj.put("qfyl", "");
			obj.put("qfsl","");
			obj.put("qflyl","");
			obj.put("qfjsj","");
			obj.put("qfznj","");
			
			obj.put("s1lsl","");
			obj.put("s1fyq", "");
			obj.put("s1glcs", "");
			obj.put("s1hsl", "");
			obj.put("s1wp", "");
			obj.put("s2lsl", "");
			obj.put("s2qfjcll","");
			obj.put("s2glcs", "");
			obj.put("s2wshsl", "");
			obj.put("s2jsjjyl","");
			obj.put("s2jsjnd", "");
			obj.put("s2xnjjyl", "");
			obj.put("s2xnjnd", "");
			
			obj.put("bdczyw", "");
			obj.put("c1yw", "");
			obj.put("c2yw", "");
			obj.put("wfcyw", "");
			obj.put("dbczhssl", "");
			obj.put("t1xynl","");
			obj.put("t2xynl","");
			obj.put("cyzfyl","");
			obj.put("jsj", "");
			obj.put("znj", "");
			
			obj.put("syzhszhyl", "");
			obj.put("bdcsy", "");
			Tharr.add(obj);
		}
		if(listOil !=null && listOil.size()>0){
			for (Object[] jyqk : listOil) {
//				if(nowDate !=null && nowDate.equals(String.valueOf(jyqk[10]))){
//					//Oil  = jyqk ;
//				}
				obj = new JSONObject();
				Foarr = new JSONArray();
				obj.put("gh", jyqk[0]);
				obj.put("gyw", CommonsUtil.getNotThreeData(jyqk[1]));
				obj.put("dyw", CommonsUtil.getNotThreeData(jyqk[2]));
				obj.put("sm", CommonsUtil.getNotOneData(jyqk[3]));
				obj.put("bm", CommonsUtil.getNotOneData(jyqk[4]));
				obj.put("gw", CommonsUtil.getNotOneData(jyqk[5]));
				obj.put("hs", CommonsUtil.getNotThreeData(jyqk[6]));
				obj.put("myl", CommonsUtil.getNotThreeData(jyqk[7]));
				obj.put("cyl", CommonsUtil.getNotThreeData(jyqk[8]));
				//obj.put("sl", CommonsUtil.getNotThreeData(jyqk[9]));
				obj.put("sl", CommonsUtil.getNotThreeData(CommonsUtil.getDecPoint(CommonsUtil.getNotThreeData(jyqk[7]),CommonsUtil.getNotThreeData(jyqk[8]))));
				if( jyqk[11] !=null && !"".equals(jyqk[11]) && !jyqk[11].equals("undefind")){
					obj.put("RPD_CRUDE_TRANSITION_ID", jyqk[11].toString());
				}else{
					obj.put("RPD_CRUDE_TRANSITION_ID","");
				}
				
				Foarr.add(obj);
			}
		}else{
				obj = new JSONObject();
				Foarr = new JSONArray();
				obj.put("gh", "");
				obj.put("gyw","");
				obj.put("dyw","");
				obj.put("sm","");
				obj.put("bm","");
				obj.put("gw","");
				obj.put("hs","");
				obj.put("myl","");
				obj.put("cyl","");
				obj.put("sl","");	
				Foarr.add(obj);
		}
		
		if(listOilb !=null && listOilb.size()>0){
			for (Object[] gh2 : listOilb) {
//			
				obj = new JSONObject();
				Fivearr = new JSONArray();
				obj.put("gh0", gh2[1]);
				obj.put("gyw0", CommonsUtil.getNotThreeData(gh2[2]));
				obj.put("dyw0", CommonsUtil.getNotThreeData(gh2[3]));
				obj.put("sm0", CommonsUtil.getNotOneData(gh2[4]));
				obj.put("bm0", CommonsUtil.getNotOneData(gh2[5]));
				obj.put("gw0", CommonsUtil.getNotOneData(gh2[6]));
				obj.put("hs0", CommonsUtil.getNotThreeData(gh2[7]));
				obj.put("myl0", CommonsUtil.getNotThreeData(gh2[8]));
				obj.put("cyl0", CommonsUtil.getNotThreeData(gh2[9]));
				//obj.put("sl0", CommonsUtil.getNotThreeData(gh2[10]));
				obj.put("sl0", CommonsUtil.getNotThreeData(CommonsUtil.getDecPoint(CommonsUtil.getNotThreeData(gh2[8]),CommonsUtil.getNotThreeData(gh2[9]))));

				if( gh2[12] !=null && !"".equals(gh2[12]) && !gh2[12].equals("undefind")){
					obj.put("RPD_CRUDE_TRANSITION_ID0", gh2[12].toString());
				}else{
					obj.put("RPD_CRUDE_TRANSITION_ID0","");
				}
				
				Fivearr.add(obj);
			}
		}else{
				obj = new JSONObject();
				Fivearr = new JSONArray();
				obj.put("gh0", "");
				obj.put("gyw0","");
				obj.put("dyw0","");
				obj.put("sm0","");
				obj.put("bm0","");
				obj.put("gw0","");
				obj.put("hs0","");
				obj.put("myl0","");
				obj.put("cyl0","");
				obj.put("sl0","");	
				Fivearr.add(obj);
		}
		
			obj = new JSONObject();
			obj.put("firstArr", Fiarr);
			obj.put("secondArr", Searr);
			obj.put("thirdArr", Tharr);
			obj.put("fourArr", Foarr);
			obj.put("fiveArr", Fivearr);
		return obj;
		
	}
	@Override
	public boolean updateU1S(PcRpdU1GeneralT u1) throws Exception {
		return u1ZHRBDao.updateU1S(u1);
	}
	@Override
	public List<PcRpdCrudeTransitionT> searchCrude(String rPDCRUDETRANSITIONID)throws Exception {
		String hql = " from  PcRpdCrudeTransitionT a where 1=1";
		if(rPDCRUDETRANSITIONID !=null && !"".equals(rPDCRUDETRANSITIONID)){
			hql += "  and  a.rpdCrudeTransitionId = '"+rPDCRUDETRANSITIONID+"'";
		}
		return u1ZHRBDao.searchCrude(hql);
	}
	@Override
	public boolean updateCrude(PcRpdCrudeTransitionT crude ,PcRpdCrudeTransitionT crude0) throws Exception {
		return u1ZHRBDao.updateCrude(crude,crude0);
	}
	@Override
	public List<PcRpdU1GeneralT> onAudit(String id) throws Exception {
		String hql = " from  PcRpdU1GeneralT a  where 1=1";
		if(id !=null && !"".equals(id)){
			hql +=" and a.rpdU1GeneralId  = '"+id+"'";
		}
		return u1ZHRBDao.onAudit(hql);
	}
	@Override
	public boolean updateOnAudit(PcRpdU1GeneralT u1g) throws Exception {
		
		return u1ZHRBDao.updateOnAudit(u1g);
	}
	@Override
	public List<Object[]> searchExportData(String txtDate, String sqls) {
		String beforetime = DateBean.getBeforeDAYTime(txtDate);
		//String  sql  = " sqls where 1=1";
		sqls += "   from pc_rpd_u1_general_t  where 1=1 and RQ between TO_DATE('"+beforetime+"','YYYY-MM-DD') and TO_DATE('"+txtDate+"','YYYY-MM-DD') order by RQ ";
		return u1ZHRBDao.searchExportData(sqls);
	}
	
	public List<Object[]> searchExportDataToDay(String txtDate, String sqls) {
		//String beforetime = DateBean.getBeforeDAYTime(txtDate);
		//String  sql  = " sqls where 1=1";
		sqls += " from pc_rpd_u1_general_t  where 1=1 and RQ  =TO_DATE('"+txtDate+"','YYYY-MM-DD') order by RQ ";
		return u1ZHRBDao.searchExportData(sqls);
	}

	@Override
	public List<Object[]> searchExportDataGH(String txtDate, String thirdSqlLast) {
		thirdSqlLast +="  from  pc_rpd_crude_transition_t where 1=1 and RQ  =TO_DATE('"+txtDate+"','YYYY-MM-DD') and org_id='239068FA3724460581219B7CD401B386' order by RQ,gh ";
		return u1ZHRBDao.searchExportData(thirdSqlLast);
	}
	@Override
	public List<Object[]> searchExportAdd(String txtDate, String addsql)
			throws Exception {
		String beforetime = DateBean.getBeforeDAYTime(txtDate);
		//String  sql  = " sqls where 1=1";
		addsql += "   from pc_rpd_u1_general_t  where 1=1 and RQ between TO_DATE('"+beforetime+"','YYYY-MM-DD') and TO_DATE('"+txtDate+"','YYYY-MM-DD') order by RQ ";
		return u1ZHRBDao.searchExportData(addsql);
	}
	@Override
	public List<Object[]> searchExportDataGH2(String txtDate)
			throws Exception {
		String  sqlb = "select  gh   from  ( select  row_number() over(order by  gh) as xuhao,"+
		" gh , gyw, dyw , sm , bm , gw , hs , myl , cyl , sl, RQ , RPD_CRUDE_TRANSITION_ID    from "+
		" pc_rpd_crude_transition_t   where  1=1  and   org_id='239068FA3724460581219B7CD401B386'  "+
		" and RQ =TO_DATE('"+txtDate+"','YYYY-MM-DD')   ) where  xuhao =2";	
		return u1ZHRBDao.searchExportData(sqlb);
	}
}
