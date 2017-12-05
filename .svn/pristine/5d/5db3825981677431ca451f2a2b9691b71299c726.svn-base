package com.echo.service.impl;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.dao.CrudeOilDao;
import com.echo.service.CrudeOilService;
import com.echo.util.CommonsUtil;
import com.echo.util.DateBean;

public class CrudeOilServiceImpl implements CrudeOilService {
    private CrudeOilDao crudeOilDao;

	public void setCrudeOilDao(CrudeOilDao crudeOilDao) {
		this.crudeOilDao = crudeOilDao;
	}

	
public JSONArray searchCrudeOil(String txtDate) throws Exception {
		 
		String gh ="";  //罐号
		String gyw= "";  //高油位(m)
		String dyw= "";  // 底油位(m)
		String sm =""; // 视密(mg/cm³)视密(mg/cm³)
		String bm= "";  //标密(mg/cm³)
		String gw= ""; //罐温(℃)
		String hs= ""; //含水(%)
		String myl= "";  //毛油量(t)
		String cyl= ""; //纯油量(t)
		String sl =""; //水量（t）
		
		//String  sql ="select  b.gyw,b.dyw,b.myl,b.cyl  from   pc_rpd_crude_transition_t  b";
		
		//String sql ="select  a.rpd_crude_transition_id,a.rq,a.org_id,a.gh,a.gyw,a.dyw,  a.myl,a.cyl,a.sl,a.hs,a.sm,a.bm,a.gw from  PC_RPD_CRUDE_TRANSITION_T  a where  to_number(to_char(a.rq,'mm')) = 9";

		List<Object[]> oilAll = null;
		 List<Object[]> oilCount =null;
		JSONObject obj = null;
		JSONArray arr = null;
		arr = new JSONArray();
		int dataflg = 1;  //数据存在标识  0：不存在， 1：存在
//		String sql ="select  a.rpd_crude_transition_id,a.rq,a.org_id,a.gh,a.gyw,a.dyw,  a.myl,a.cyl,a.sl,a.hs,a.sm,a.bm,a.gw from  PC_RPD_CRUDE_TRANSITION_T  a  where 1=1";
//			   sql+="and to_char(a.rq,'yyyy-mm')='"+txtDate+"' order by  a.rq,a.gh";
			
		String sql ="select  a.rpd_crude_transition_id,a.rq,a.org_id,a.gh,a.gyw,a.dyw,  a.myl,a.cyl,a.sl,a.hs,a.sm,a.bm,a.gw from  PC_RPD_CRUDE_TRANSITION_T  a  where 1=1";
		       //sql+="and to_char(a.rq,'yyyy-mm')='"+txtDate+"' order by  a.rq,a.gh";
		       sql+=" and  org_id='4028e64f42c12b590142c13777560003' and a.rq   between TO_DATE('"+txtDate+".01','yyyy.mm.dd') and TO_DATE('"+txtDate+".10','yyyy.mm.dd')  order by  a.rq,nlssort(a.gh, 'NLS_SORT=SCHINESE_STROKE_M')";
		oilAll = crudeOilDao.searchCrudeOil(sql);
		
		String sqls = " select count(*)  from PC_RPD_CRUDE_TRANSITION_T  a   where 1=1 ";
		       sqls+=" and org_id='4028e64f42c12b590142c13777560003' and a.rq   between TO_DATE('"+txtDate+".01','yyyy.mm.dd') and TO_DATE('"+txtDate+".10','yyyy.mm.dd')  order by  a.rq,nlssort(a.gh, 'NLS_SORT=SCHINESE_STROKE_M')";
		       oilCount  = crudeOilDao.searchCrudeOil(sqls);
		       
		       //String[][] dates = DateBean.getDay("day", txtDate);
		Object[] table = null;
		if(oilAll.size()>0 && oilAll !=null){
			Object aa=oilAll.get(0)[1];
				//if(Integer.parseInt(aa.toString().substring(8, 10))<=10 ){
				for(Object[] oilAlls : oilAll){
					
					obj = new JSONObject();
					table = oilAlls;
					obj.put("rpd_crude_transition_id", oilAlls[0]);
					String RQ = oilAlls[1].toString().replaceAll("-", ".");
					obj.put("rq",RQ);
					obj.put("gh", oilAlls[3].toString());  //罐号
					//if(oilAlls[4] !=null && oilAlls[4].equals("undefined")){
					if(oilAlls[4] !=null ){
						obj.put("gyw",CommonsUtil.getNotThreeData(oilAlls[4]));  //高油位(m)
					}else{
						obj.put("gyw","");
					}
					//if(oilAlls[5] !=null && oilAlls[5].equals("undefined")){
					if(oilAlls[5] !=null){
						obj.put("dyw",CommonsUtil.getNotThreeData(oilAlls[5]));  // 底油位(m)
					}else{
						obj.put("dyw","");
					}
					if(oilAlls[4]!=null && oilAlls[5]!=null){
						obj.put("cyw",CommonsUtil.getDecPoint(oilAlls[4].toString(), oilAlls[5].toString()));
					}else{
						obj.put("cyw","");
					}
					if( oilAlls[4]!=null && oilAlls[5]!=null && !oilAlls[4].toString().equals(oilAlls[5].toString())){
						Object diff = CommonsUtil.getDecPoint(oilAlls[4].toString(), oilAlls[5].toString());
						obj.put("mmcy", CommonsUtil.getDivide(oilAlls[6].toString(), diff));
					}else{
						obj.put("mmcy","");
					}
					if(oilAlls[4]!=null && oilAlls[5]!=null && !oilAlls[4].toString().equals(oilAlls[5].toString())){
						Object diff = CommonsUtil.getDecPoint(oilAlls[4].toString(), oilAlls[5].toString());
						obj.put("mmmy", CommonsUtil.getDivide(oilAlls[7].toString(),diff));
					}else{
						obj.put("mmmy","");
					}
					if(oilAlls[6] !=null && !oilAlls[6].equals("undefined")){
						obj.put("myl",  CommonsUtil.getNotThreeData(oilAlls[6]));  //毛油量(t)
					}else{
						obj.put("myl", "");
					}
					if(oilAlls[7] !=null && !oilAlls[7].equals("undefined")){
						obj.put("cyl",  CommonsUtil.getNotThreeData(oilAlls[7])); //纯油量(t)
					}else{
						obj.put("cyl",""); 
					}
					if(oilAlls[8] !=null && !oilAlls[8].equals("undefined")){
					obj.put("sl", CommonsUtil.getNotThreeData(oilAlls[8])); //含水(%)
					}else{
						obj.put("sl", "");
					}
					if(oilAlls[9] !=null && !oilAlls[9].equals("undefined")){
					obj.put("hs", CommonsUtil.getNotThreeData(oilAlls[9])); //水量（t）
					}else{
						obj.put("hs","");
					}
					if(oilAlls[10] !=null && !oilAlls[10].equals("undefined")){
						obj.put("sm", CommonsUtil.getNotOneData(oilAlls[10])); // 视密(mg/cm³)视密(mg/cm³)
					}else{
						obj.put("sm","");
					}
					if(oilAlls[11] !=null && !oilAlls[11].equals("undefined")){
						obj.put("bm", CommonsUtil.getNotOneData(oilAlls[11]));  //标密(mg/cm³)
					}else{
						obj.put("bm","");
					}
					if(oilAlls[12] !=null && !oilAlls[12].equals("undefined")){
						obj.put("gw", CommonsUtil.getNotOneData(oilAlls[12])); //罐温(℃)
					}else{
						obj.put("gw","");
					}
					arr.add(obj);
				}
				//if( Integer.parseInt(obj.toString().substring(8,10))>9  ){
					
				obj = new JSONObject();
				obj.put("rpd_crude_transition_id", "");
				obj.put("rq","合计交罐");
				obj.put("gh", oilCount.get(0));  //罐号
				obj.put("gyw","");  //高油位(m)
				obj.put("dyw","");  // 底油位(m)
				obj.put("cyw","");
				obj.put("mmcy","");
				obj.put("mmmy","");
				obj.put("myl",  "");  //毛油量(t)
				obj.put("cyl",  ""); //纯油量(t)
				obj.put("hs", ""); //含水(%)
				obj.put("sl", ""); //水量（t）
				obj.put("sm", ""); // 视密(mg/cm³)视密(mg/cm³)
				obj.put("bm", "");  //标密(mg/cm³)
				obj.put("gw", ""); //罐温(℃)
				arr.add(obj);
		}
		String sql1 ="select  a.rpd_crude_transition_id,a.rq,a.org_id,a.gh,a.gyw,a.dyw,  a.myl,a.cyl,a.sl,a.hs,a.sm,a.bm,a.gw from  PC_RPD_CRUDE_TRANSITION_T  a  where 1=1";
	       //sql+="and to_char(a.rq,'yyyy-mm')='"+txtDate+"' order by  a.rq,a.gh";
	       sql1+=" and org_id='4028e64f42c12b590142c13777560003' and  a.rq   between TO_DATE('"+txtDate+".11','yyyy.mm.dd') and TO_DATE('"+txtDate+".20','yyyy.mm.dd')  order by  a.rq,nlssort(a.gh, 'NLS_SORT=SCHINESE_STROKE_M')";
	       oilAll = crudeOilDao.searchCrudeOil(sql1);
	       
	       String sqls1 = " select count(*)  from PC_RPD_CRUDE_TRANSITION_T  a   where 1=1 ";
	       			sqls1+=" and org_id='4028e64f42c12b590142c13777560003' and   a.rq   between TO_DATE('"+txtDate+".11','yyyy.mm.dd') and TO_DATE('"+txtDate+".20','yyyy.mm.dd')  order by  a.rq,nlssort(a.gh, 'NLS_SORT=SCHINESE_STROKE_M')";
	       			oilCount  = crudeOilDao.searchCrudeOil(sqls1);
	//String[][] dates = DateBean.getDay("day", txtDate);
		if(oilAll.size()>0 && oilAll !=null){
			Object aa=oilAll.get(0)[1];
				//if(Integer.parseInt(aa.toString().substring(8, 10))<=10 ){
				for(Object[] oilAlls : oilAll){
					
					obj = new JSONObject();
					table = oilAlls;
					obj.put("rpd_crude_transition_id", oilAlls[0]);
					String RQ = oilAlls[1].toString().replaceAll("-", ".");
					obj.put("rq",RQ);
					obj.put("gh", oilAlls[3].toString());  //罐号
					//if(oilAlls[4] !=null && oilAlls[4].equals("undefined")){
					if(oilAlls[4] !=null ){
						obj.put("gyw",CommonsUtil.getNotThreeData(oilAlls[4]));  //高油位(m)
					}else{
						obj.put("gyw","");
					}
					//if(oilAlls[5] !=null && oilAlls[5].equals("undefined")){
					if(oilAlls[5] !=null){
						obj.put("dyw",CommonsUtil.getNotThreeData(oilAlls[5]));  // 底油位(m)
					}else{
						obj.put("dyw","");
					}
					if(oilAlls[4]!=null && oilAlls[5]!=null){
						obj.put("cyw",CommonsUtil.getDecPoint(oilAlls[4].toString(), oilAlls[5].toString()));
					}else{
						obj.put("cyw","");
					}
					if( oilAlls[4]!=null && oilAlls[5]!=null && !oilAlls[4].toString().equals(oilAlls[5].toString())){
						Object diff = CommonsUtil.getDecPoint(oilAlls[4].toString(), oilAlls[5].toString());
						obj.put("mmcy", CommonsUtil.getDivide(oilAlls[6].toString(), diff));
					}else{
						obj.put("mmcy","");
					}
					if(oilAlls[4]!=null && oilAlls[5]!=null && !oilAlls[4].toString().equals(oilAlls[5].toString())){
						Object diff = CommonsUtil.getDecPoint(oilAlls[4].toString(), oilAlls[5].toString());
						obj.put("mmmy", CommonsUtil.getDivide(oilAlls[7].toString(),diff));
					}else{
						obj.put("mmmy","");
					}
					if(oilAlls[6] !=null && !oilAlls[6].equals("undefined")){
						obj.put("myl",  CommonsUtil.getNotThreeData(oilAlls[6]));  //毛油量(t)
					}else{
						obj.put("myl", "");
					}
					if(oilAlls[7] !=null && !oilAlls[7].equals("undefined")){
						obj.put("cyl",  CommonsUtil.getNotThreeData(oilAlls[7])); //纯油量(t)
					}else{
						obj.put("cyl",""); 
					}
					if(oilAlls[8] !=null && !oilAlls[8].equals("undefined")){
					obj.put("sl", CommonsUtil.getNotThreeData(oilAlls[8])); //含水(%)
					}else{
						obj.put("sl", "");
					}
					if(oilAlls[9] !=null && !oilAlls[9].equals("undefined")){
					obj.put("hs", CommonsUtil.getNotThreeData(oilAlls[9])); //水量（t）
					}else{
						obj.put("hs","");
					}
					if(oilAlls[10] !=null && !oilAlls[10].equals("undefined")){
						obj.put("sm", CommonsUtil.getNotOneData(oilAlls[10])); // 视密(mg/cm³)视密(mg/cm³)
					}else{
						obj.put("sm","");
					}
					if(oilAlls[11] !=null && !oilAlls[11].equals("undefined")){
						obj.put("bm", CommonsUtil.getNotOneData(oilAlls[11]));  //标密(mg/cm³)
					}else{
						obj.put("bm","");
					}
					if(oilAlls[12] !=null && !oilAlls[12].equals("undefined")){
						obj.put("gw", CommonsUtil.getNotOneData(oilAlls[12])); //罐温(℃)
					}else{
						obj.put("gw","");
					}
					arr.add(obj);
				}
				//if( Integer.parseInt(obj.toString().substring(8,10))>9  ){
					
				obj = new JSONObject();
				obj.put("rpd_crude_transition_id", "");
				obj.put("rq","合计交罐");
				obj.put("gh", oilCount.get(0));  //罐号
				obj.put("gyw","");  //高油位(m)
				obj.put("dyw","");  // 底油位(m)
				obj.put("cyw","");
				obj.put("mmcy","");
				obj.put("mmmy","");
				obj.put("myl",  "");  //毛油量(t)
				obj.put("cyl",  ""); //纯油量(t)
				obj.put("hs", ""); //含水(%)
				obj.put("sl", ""); //水量（t）
				obj.put("sm", ""); // 视密(mg/cm³)视密(mg/cm³)
				obj.put("bm", "");  //标密(mg/cm³)
				obj.put("gw", ""); //罐温(℃)
				arr.add(obj);
		}
			//String sql2 ="select  a.rpd_crude_transition_id,a.rq,a.org_id,a.gh,a.gyw,a.dyw,  a.myl,a.cyl,a.sl,a.hs,a.sm,a.bm,a.gw from  PC_RPD_CRUDE_TRANSITION_T  a  where 1=1";
		       //sql2+="and to_char(a.rq,'yyyy-mm')='"+txtDate+"' order by  a.rq,a.gh";
		       //sql2+=" and a.rq   between TO_DATE('"+txtDate+"-21','yyyy-mm-dd') and TO_DATE('"+txtDate+"-30','yyyy-mm-dd')  order by  a.rq,a.gh";
			String sql2=" select * from (select  a.rpd_crude_transition_id,a.rq,a.org_id,a.gh,a.gyw,a.dyw,  a.myl,a.cyl,a.sl,a.hs,a.sm,a.bm,a.gw from  PC_RPD_CRUDE_TRANSITION_T  a  where 1=1";
			sql2 +=" and  org_id='4028e64f42c12b590142c13777560003' and  to_char(a.rq,'yyyy.mm')='"+txtDate+"' order by  a.rq,nlssort(a.gh, 'NLS_SORT=SCHINESE_STROKE_M')) a where   a.rq > to_date('"+txtDate+".20','yyyy.mm.dd')";
			oilAll = crudeOilDao.searchCrudeOil(sql2);
			
			String sqls2=" select count(*) from (select  a.rpd_crude_transition_id,a.rq,a.org_id,a.gh,a.gyw,a.dyw,  a.myl,a.cyl,a.sl,a.hs,a.sm,a.bm,a.gw from  PC_RPD_CRUDE_TRANSITION_T  a  where 1=1";
				sqls2 +=" and  org_id='4028e64f42c12b590142c13777560003' and   to_char(a.rq,'yyyy.mm')='"+txtDate+"' order by  a.rq,nlssort(a.gh, 'NLS_SORT=SCHINESE_STROKE_M')) a where   a.rq > to_date('"+txtDate+".20','yyyy.mm.dd')";
    			oilCount  = crudeOilDao.searchCrudeOil(sqls2);
			//String[][] dates = DateBean.getDay("day", txtDate);
			if(oilAll.size()>0 && oilAll !=null){
				Object aa=oilAll.get(0)[1];
					//if(Integer.parseInt(aa.toString().substring(8, 10))<=10 ){
					for(Object[] oilAlls : oilAll){
						
					obj = new JSONObject();
					table = oilAlls;
					obj.put("rpd_crude_transition_id", oilAlls[0]);
					String RQ = oilAlls[1].toString().replaceAll("-", ".");
					obj.put("rq",RQ);
					obj.put("gh", oilAlls[3].toString());  //罐号
					//if(oilAlls[4] !=null && oilAlls[4].equals("undefined")){
					if(oilAlls[4] !=null ){
						obj.put("gyw",CommonsUtil.getNotThreeData(oilAlls[4]));  //高油位(m)
					}else{
						obj.put("gyw","");
					}
					//if(oilAlls[5] !=null && oilAlls[5].equals("undefined")){
					if(oilAlls[5] !=null){
						obj.put("dyw",CommonsUtil.getNotThreeData(oilAlls[5]));  // 底油位(m)
					}else{
						obj.put("dyw","");
					}
					if(oilAlls[4]!=null && oilAlls[5]!=null){
						obj.put("cyw",CommonsUtil.getDecPoint(oilAlls[4].toString(), oilAlls[5].toString()));
					}else{
						obj.put("cyw","");
					}
					if( oilAlls[4]!=null && oilAlls[5]!=null && !oilAlls[4].toString().equals(oilAlls[5].toString())){
						Object diff = CommonsUtil.getDecPoint(oilAlls[4].toString(), oilAlls[5].toString());
						obj.put("mmcy", CommonsUtil.getDivide(oilAlls[6].toString(), diff));
					}else{
						obj.put("mmcy","");
					}
					if(oilAlls[4]!=null && oilAlls[5]!=null && !oilAlls[4].toString().equals(oilAlls[5].toString())){
						Object diff = CommonsUtil.getDecPoint(oilAlls[4].toString(), oilAlls[5].toString());
						obj.put("mmmy", CommonsUtil.getDivide(oilAlls[7].toString(),diff));
					}else{
						obj.put("mmmy","");
					}
					if(oilAlls[6] !=null && !oilAlls[6].equals("undefined")){
						obj.put("myl",  CommonsUtil.getNotThreeData(oilAlls[6]));  //毛油量(t)
					}else{
						obj.put("myl", "");
					}
					if(oilAlls[7] !=null && !oilAlls[7].equals("undefined")){
						obj.put("cyl",  CommonsUtil.getNotThreeData(oilAlls[7])); //纯油量(t)
					}else{
						obj.put("cyl",""); 
					}
					if(oilAlls[8] !=null && !oilAlls[8].equals("undefined")){
					obj.put("sl", CommonsUtil.getNotThreeData(oilAlls[8])); //含水(%)
					}else{
						obj.put("sl", "");
					}
					if(oilAlls[9] !=null && !oilAlls[9].equals("undefined")){
					obj.put("hs", CommonsUtil.getNotThreeData(oilAlls[9])); //水量（t）
					}else{
						obj.put("hs","");
					}
					if(oilAlls[10] !=null && !oilAlls[10].equals("undefined")){
						obj.put("sm", CommonsUtil.getNotOneData(oilAlls[10])); // 视密(mg/cm³)视密(mg/cm³)
					}else{
						obj.put("sm","");
					}
					if(oilAlls[11] !=null && !oilAlls[11].equals("undefined")){
						obj.put("bm", CommonsUtil.getNotOneData(oilAlls[11]));  //标密(mg/cm³)
					}else{
						obj.put("bm","");
					}
					if(oilAlls[12] !=null && !oilAlls[12].equals("undefined")){
						obj.put("gw", CommonsUtil.getNotOneData(oilAlls[12])); //罐温(℃)
					}else{
						obj.put("gw","");
					}
					arr.add(obj);
				}
				//if( Integer.parseInt(obj.toString().substring(8,10))>9  ){
					
				obj = new JSONObject();
				obj.put("rpd_crude_transition_id", "");
				obj.put("rq","合计交罐");
				obj.put("gh", oilCount.get(0));  //罐号
				obj.put("gyw","");  //高油位(m)
				obj.put("dyw","");  // 底油位(m)
				obj.put("cyw","");
				obj.put("mmcy","");
				obj.put("mmmy","");
				obj.put("myl",  "");  //毛油量(t)
				obj.put("cyl",  ""); //纯油量(t)
				obj.put("hs", ""); //含水(%)
				obj.put("sl", ""); //水量（t）
				obj.put("sm", ""); // 视密(mg/cm³)视密(mg/cm³)
				obj.put("bm", "");  //标密(mg/cm³)
				obj.put("gw", ""); //罐温(℃)
				arr.add(obj);
		}
			
		
		return arr;
	}
    
	public List<Object[]> searchExportData(String rq, String sql) throws Exception{
		sql += " where 1=1   and to_char(v.rq,'yyyy.mm')='"+rq+"' order by  v.rq,nlssort(v.gh, 'NLS_SORT=SCHINESE_STROKE_M')";
		List<Object[]> list = crudeOilDao.searchCrudeOil(sql);
		return list;
	}
}
