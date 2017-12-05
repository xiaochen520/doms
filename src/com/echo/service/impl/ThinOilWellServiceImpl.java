package com.echo.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.beans.PageControl;
import com.echo.dao.ThinOilWellDao;
import com.echo.dto.PcCdThinoilWellT;
import com.echo.service.ThinOilWellService;

public class ThinOilWellServiceImpl implements ThinOilWellService{
	private ThinOilWellDao thinOilWellDao;
	
	
	public void setThinOilWellDao(ThinOilWellDao thinOilWellDao) {
		this.thinOilWellDao = thinOilWellDao;
	}
	public JSONObject searchThinOil(String qk,String zh,String jh,String gh,String name,String jrbz1,String dyearC,int pageNo,String sort,String order,int rowsPerpage) {
		JSONArray jsonArr = null;
		JSONObject tjo = null;
		String cloums = "select   ";
		String formTable = " from PC_CD_THINOIL_WELL_V  where 1=1 ";
		String totalNum = "select count(*) ";
		if(!qk.equals("")&&qk!=null&&!qk.equals("undefined")&!qk.equals("全部")){
			formTable=formTable+" and oilstationname='"+qk+"'";
		}
		if(!zh.equals("")&&zh!=null&&!zh.equals("undefined")){
			formTable=formTable+" and qkmc='"+zh+"'";
		}
		if(!jh.equals("")&&jh!=null&&!jh.equals("undefined")){
			formTable=formTable+" and BLOCKSTATIONNAME='"+jh+"'";
		}
		if(!gh.equals("")&&jh!=null&&!gh.equals("undefined")){
			formTable=formTable+" and gh_id='"+gh+"'";
		}
		if(!name.equals("")&&name!=null&&!name.equals("undefined")){
			formTable=formTable+" and well_name='"+name+"'";
		}
		if(!jrbz1.equals("")&&jrbz1!=null&&!jrbz1.equals("undefined") && !jrbz1.equals("全部")){
			formTable=formTable+" and jrbz='"+jrbz1+"'";
		}
		if(!dyearC.equals("")&&dyearC!=null&&!dyearC.equals("undefined")){
			formTable=formTable+" and DYEAR='"+dyearC+"'";
		}
		
		String[] cloumnsName = {"WELLID","WELL_NAME","OILSTATIONNAME","QKMC","BLOCKSTATIONNAME","teamGroup","MANIFOLD",
				"PROD_SNS","STATUS_VALUE","COMMISSIONING_DATE","DYEAR","BRANCHINGID","WELL_COLE","BEWELL_NAME","INCREASE_FREQ_FLAG",
				"START_INCREASE_FREQ_TIME","END_INCREASE_FREQ_TIME","INCREASE_INTERVAL","JRBZ","LJJDID","LJJD_S","VALVE_CODING","CHANNEL_NO",
				"RLAST_OPER","RLAST_ODATE","OUTPUT_COEFFICIENT","REMARK","QKID","ORG_ID","ZZZ_ID","GH_ID","A2ID"};
		String kk="WELLID";
		for(int m=1;m<cloumnsName.length;m++){
			if("RLAST_ODATE".equals(cloumnsName[m])){
				kk += ",to_char(RLAST_ODATE,'YYYY-MM-DD HH24:MI:SS') as RLAST_ODATE";
			}else if("START_INCREASE_FREQ_TIME".equals(cloumnsName[m])){
				kk +=",to_char(START_INCREASE_FREQ_TIME,'YYYY-MM-DD HH24:MI') as START_INCREASE_FREQ_TIME";
			}
			else if("END_INCREASE_FREQ_TIME".equals(cloumnsName[m])){
				kk +=",to_char(END_INCREASE_FREQ_TIME,'YYYY-MM-DD HH24:MI') as END_INCREASE_FREQ_TIME";
			}
			else{
				kk=kk+","+cloumnsName[m];
			}
		
		}
		String boilersInfo = cloums + kk+formTable;
		//获取总条数
		int total = 0;
		if ("1".equals(totalNum)) {
			total = 1;
		} else {
			totalNum += formTable;
			total = thinOilWellDao.getCounts(totalNum);
		}
		//排序
		if(!"".equals(sort) && !"well_name".equals(sort)){
			for (String cloumn : cloumnsName) {
				if(cloumn.equals(sort)){
					boilersInfo +=" order by " + cloumn + " " + order;
					break;
				}
				
			}
		}
		else {
			boilersInfo +=" order by nlssort(well_name, 'NLS_SORT=SCHINESE_STROKE_M') ";
		}
		//计算分页
		PageControl control = new PageControl();
		//当前页
		control.setInt_num(rowsPerpage);
		control.init(pageNo, total);
		//开始条数
		int start = control.getStart()-1;
		
		List<Object[]> lo = thinOilWellDao.searchThinOil(boilersInfo,start,rowsPerpage);
		if (lo != null && 0 < lo.size()) {
			// 生成树节点json文档
			jsonArr = new JSONArray();
			for (Object[] o : lo) {
				tjo = new JSONObject();

				for (int i = 0; i < o.length; i++) {
					if (o[i] == null||o[i].equals("null")) {
						o[i] = "";
					}
					if(i==13 && o[i]!=null && o[i].toString().equals("1"))
						tjo.put(cloumnsName[i], "已加密");
					
					else if(i==13 && o[i] !=null && o[i].toString().equals("0")){
						tjo.put(cloumnsName[i], "未加密");
					}else
					
						tjo.put(cloumnsName[i], o[i].toString());
				}
				jsonArr.add(tjo);
			}
		}
		tjo = new JSONObject();
		tjo.put("Rows",jsonArr);
		tjo.put("Total",total);
		return tjo;
	}
	public boolean removeStationInfo(String arg,String orgid){
		String[] sqls = new String[2];
		sqls[0] = " DELETE from pc_cd_thinoil_well_t d where d.wellid = '"+arg+"'";
		sqls[1] = " DELETE from pc_cd_org_t t where t.org_id = '"+orgid+"'";
		
		return thinOilWellDao.removeStationInfo(sqls);
	}

	public JSONArray queryWellInfo(String arg) {
		JSONArray jsonArr = null;
		JSONObject jsobj = null;
		String sql = "select w.WELLID,w.WELL_NAME from PC_CD_THINOIL_WELL_T w left join (select o1.org_id from pc_cd_org_t o1 left join (select o.org_id from pc_cd_org_t o where o.pid='" + arg + "') o2 on o1.pid=o2.org_id where o1.pid=o2.org_id) o3 on o3.org_id=w.org_id where o3.org_id=w.org_id order by nlssort(w.WELL_NAME,'NLS_SORT=SCHINESE_STROKE_M')";
		if ("".equals(arg)) {//全部的锅炉
			sql = "select w.WELLID,w.WELL_NAME from PC_CD_THINOIL_WELL_T w order by nlssort(w.WELL_NAME,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		List<Object[]> dataSet = thinOilWellDao.queryInfo(sql);
		if(dataSet != null && dataSet.size()>0){
			//生成树节点json文档
			jsonArr = new JSONArray();   
			for(Object[] entry : dataSet){
				jsobj = new JSONObject();
				jsobj.put("text", entry[1]);
				jsobj.put("id",entry[0]);
				jsonArr.add(jsobj);
			}
			return jsonArr;
		}
		jsobj = new JSONObject();
		jsobj.put("text", "");
		jsobj.put("id","");
		jsonArr = new JSONArray();
		jsonArr.add(jsobj);
		return jsonArr;
	}
	public JSONObject cascadeInit() {
		String oilSql = "select r.ORG_ID,r.STRUCTURENAME from PC_CD_THINOILTREE_V r where r.STRUCTURETYPE='4' order by nlssort(r.STRUCTURENAME,'NLS_SORT=SCHINESE_STROKE_M')";
		String areaSql = "select distinct a.qkid,a.qkmc from pc_cd_area_info_t a left join pc_cd_thinoil_well_t r on a.qkid=r.qkid where a.qkid=r.qkid";
		String stationSql = "select r.ORG_ID,r.STRUCTURENAME from PC_CD_THINOILTREE_V r where r.STRUCTURETYPE='7' order by nlssort(r.STRUCTURENAME,'NLS_SORT=SCHINESE_STROKE_M')";
		String wellSql = "select w.WELLID,w.WELL_NAME from PC_CD_THINOIL_WELL_T w order by nlssort(w.WELL_NAME,'NLS_SORT=SCHINESE_STROKE_M')";
		JSONArray oilJsonArr = null;
		JSONArray areaJsonArr = null;
		JSONArray stationArr = null;
		JSONArray wellArr = null;
		JSONObject jsobj = null;
		List<Object[]> oilSet = thinOilWellDao.queryInfo(oilSql);
		List<Object[]> areaSet = thinOilWellDao.queryInfo(areaSql);
		List<Object[]> stationSet = thinOilWellDao.queryInfo(stationSql);
		List<Object[]> wellSet = thinOilWellDao.queryInfo(wellSql);
		
		if(oilSet != null && oilSet.size()>0){
			oilJsonArr = new JSONArray();   
			for(Object[] entry : oilSet){
				jsobj = new JSONObject();
				jsobj.put("text", entry[1]);
				jsobj.put("id",entry[0]);
				oilJsonArr.add(jsobj);
			}
			jsobj = new JSONObject();
			jsobj.put("text", "全部");
			jsobj.put("id", 1);
			oilJsonArr.add(jsobj);
		}
		
		if(areaSet != null && areaSet.size()>0){
			areaJsonArr = new JSONArray();   
			for(Object[] entry : areaSet){
				jsobj = new JSONObject();
				jsobj.put("text", entry[1]);
				jsobj.put("id",entry[0]);
				areaJsonArr.add(jsobj);
			}
		}
		
		if(stationSet != null && stationSet.size()>0){
			stationArr = new JSONArray();   
			for(Object[] entry : stationSet){
				jsobj = new JSONObject();
				jsobj.put("text", entry[1]);
				jsobj.put("id",entry[0]);
				stationArr.add(jsobj);
			}
		}
		
		if(wellSet != null && wellSet.size()>0){
			wellArr = new JSONArray();   
			for(Object[] entry : wellSet){
				jsobj = new JSONObject();
				jsobj.put("text", entry[1]);
				jsobj.put("id",entry[0]);
				wellArr.add(jsobj);
			}
		}
		
		jsobj = new JSONObject();
		jsobj.put("oiltext",oilJsonArr);
		jsobj.put("areatext",areaJsonArr);
		jsobj.put("stationtext",stationArr);
		jsobj.put("welltext",wellArr);
		return jsobj;
	}
	public boolean addThin(PcCdThinoilWellT thin) throws Exception {
		return thinOilWellDao.save(thin);
	}
	public List<PcCdThinoilWellT> searchThinById(String thinwellid,String thinoilwellname) throws Exception {
		List<PcCdThinoilWellT> thinList = null;
		 String hql = "from PcCdThinoilWellT t where 1=1";
		 if(thinwellid !=null && !"".equals(thinwellid)){
			 hql +=" and t.wellid ='"+thinwellid+"'";
		 }
		 if(thinoilwellname != null && !"".equals(thinoilwellname)){
			 hql += " and t.wellName ='"+thinoilwellname+"'";
		 }
		 
		 thinList = thinOilWellDao.searchById(hql);
		return thinList;
	}
	public List<PcCdThinoilWellT> searchThisName(String thinoilwellname)throws Exception {
		List<PcCdThinoilWellT> thiList= null;
		 String hql = "from PcCdThinoilWellT t where 1=1";
		 if(thinoilwellname != null && !"".equals(thinoilwellname)){
			 hql += " and t.wellName = '"+thinoilwellname+"'";
		 }
		 thiList = thinOilWellDao.searchByName(hql);
		return thiList;
	}
	public boolean updateThin(PcCdThinoilWellT thin) throws Exception {
		boolean flg = true;
		try {
			flg = thinOilWellDao.updateThin(thin);
		} catch (Exception e) {
			e.printStackTrace();
			flg = false;
		}
		return false;
	}
	@Override
	public HashMap<String, Object> searchOnExport(String oilstationname1,String blockstationname1,String rulewellname1,String areablock,String jrbz1,String dyearC,String totalNumFlag)throws Exception {

		JSONArray jsonArr = null;
		JSONObject tjo = null;
		HashMap<String,Object> map = new HashMap<String, Object>();
		String cloums = "select   ";
		String formTable = " from PC_CD_THINOIL_WELL_V  where 1=1 ";
		String totalNum = "select count(*) ";
		
		if(!oilstationname1.equals("")&&oilstationname1!=null&&!oilstationname1.equals("undefined")&!oilstationname1.equals("全部")){
			formTable=formTable+" and oilstationname='"+oilstationname1+"'";
		}
		if(!areablock.equals("")&&areablock!=null&&!areablock.equals("undefined")){
			formTable=formTable+" and qkmc='"+areablock+"'";
		}
		if(!blockstationname1.equals("")&&blockstationname1!=null&&!blockstationname1.equals("undefined")){
			formTable=formTable+" and BLOCKSTATIONNAME='"+blockstationname1+"'";
		}
		
		if(!rulewellname1.equals("")&&rulewellname1!=null&&!rulewellname1.equals("undefined")){
			formTable=formTable+" and well_name='"+rulewellname1+"'";
		}
		if(!jrbz1.equals("")&&jrbz1!=null&&!jrbz1.equals("undefined") && !jrbz1.equals("全部")){
			formTable=formTable+" and jrbz='"+jrbz1+"'";
		}
		if(!dyearC.equals("")&&dyearC!=null&&!dyearC.equals("undefined")){
			formTable=formTable+" and DYEAR='"+dyearC+"'";
		}
		String[] cloumnsName = {"WELL_NAME","OILSTATIONNAME","QKMC","BLOCKSTATIONNAME","teamGroup","MANIFOLD","PROD_SNS","COMMISSIONING_DATE","DYEAR","BRANCHINGID",
				"WELL_COLE","BEWELL_NAME","INCREASE_FREQ_FLAG",
				"START_INCREASE_FREQ_TIME","END_INCREASE_FREQ_TIME","INCREASE_INTERVAL","JRBZ","LJJD_S","VALVE_CODING","CHANNEL_NO",
				"OUTPUT_COEFFICIENT","REMARK"};
		String kk="WELL_NAME";
		for(int m=1;m<cloumnsName.length;m++){
			 if("START_INCREASE_FREQ_TIME".equals(cloumnsName[m])){
				kk +=",to_char(START_INCREASE_FREQ_TIME,'YYYY-MM-DD HH24:MI') as START_INCREASE_FREQ_TIME";
			}
			else if("END_INCREASE_FREQ_TIME".equals(cloumnsName[m])){
				kk +=",to_char(END_INCREASE_FREQ_TIME,'YYYY-MM-DD HH24:MI') as END_INCREASE_FREQ_TIME";
			}
			else{
				kk=kk+","+cloumnsName[m];
			}
		
		}
		String thinOilWellRD = cloums +kk+formTable;
		//获取总条数
		int total = 0;
		if ("1".equals(totalNum)) {
			total = 1;
		} else {
			totalNum += formTable;
			total = thinOilWellDao.getCounts(totalNum);
		}
		if ("totalNum".equals(totalNumFlag)) {
			map.put("totalNum", total + "");
			return map;
		}
		//排序
//		if(!"".equals(sort) && !"ghmc".equals(sort)){
//			for (String cloumn : cloumnsName) {
//				if(cloumn.equals(sort)){
//					thinOilWellRD +=" order by " + cloumn + " " + order;
//					break;
//				}
//				
//			}
//		}
//		else {
//			thinOilWellRD +=" order by ghmc ";
//		}
		//计算分页
		PageControl control = new PageControl();
		//当前页
		List<Object[]> lo = new ArrayList<Object[]>();
		//control.setInt_num(rowsPerpage);
		if ("export".equals(totalNumFlag)) {
			lo = thinOilWellDao.searchData(thinOilWellRD);
		}
		if (lo != null && 0 < lo.size()) {
			// 生成树节点json文档
			jsonArr = new JSONArray();
			for (Object[] o : lo) {
				tjo = new JSONObject();

				for (int i = 0; i < o.length; i++) {
					if (o[i] == null||o[i].equals("null")) {
						o[i] = "";
					}
					if(i==11 && o[i]!=null && o[i].toString().equals("1"))
						tjo.put(cloumnsName[i], "已加密");
					
					else if(i==11 && o[i] !=null && o[i].toString().equals("0")){
						tjo.put(cloumnsName[i], "未加密");
					}else
					
						tjo.put(cloumnsName[i], o[i].toString());
				}
				jsonArr.add(tjo);
			}
		}
		tjo = new JSONObject();
		tjo.put("Rows",jsonArr);
		tjo.put("Total",total);
		map.put("list", lo);
		map.put("json", tjo);
		return map;
	
	
	}
}
