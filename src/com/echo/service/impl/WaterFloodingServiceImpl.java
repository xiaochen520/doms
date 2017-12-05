package com.echo.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.beans.PageControl;
import com.echo.dao.GasWellDao;
import com.echo.dao.RuleWellDao;
import com.echo.dao.SagdDao;
import com.echo.dao.ThinOilWellDao;
import com.echo.dao.WaterFloodingDao;
import com.echo.dao.WaterInjectionDao;
import com.echo.dto.PcCdWaterfloodingWellT;
import com.echo.service.GasWellService;
import com.echo.service.RuleWellService;
import com.echo.service.SagdService;
import com.echo.service.ThinOilWellService;
import com.echo.service.WaterFloodingService;
import com.echo.service.WaterInjectionService;

public class WaterFloodingServiceImpl implements WaterFloodingService{
	private WaterFloodingDao waterFloodingDao;
	
	public void setWaterFloodingDao(WaterFloodingDao waterFloodingDao) {
		this.waterFloodingDao = waterFloodingDao;
	}
	public JSONObject searchwaterFL(String qk,String zh,String jh,String name,String zsq,String JRBZ1,String dyearC,String groupName ,int pageNo,String sort,String order,int rowsPerpage) {
		JSONArray jsonArr = null;
		JSONObject tjo = null;
		String cloums = "select   ";
		String formTable = " from pc_cd_water_flooding_v  where 1=1 ";
		String totalNum = "select count(*) ";
		if(!qk.equals("")&&qk!=null&&!qk.equals("undefined")&!qk.equals("全部")){
			formTable=formTable+" and OILSTATIONNAME='"+qk+"'";
		}
		
		if(!name.equals("")&&name!=null&&!name.equals("undefined")){
			formTable=formTable+" and waterinjectiontopr='"+name+"'";
		}
		if(!zsq.equals("")&&zsq!=null&&!zsq.equals("undefined")){
			formTable=formTable+" and WATERFLOODING_NAME='"+zsq+"'";
		}
		
		if(!zh.equals("")&&zh!=null&&!zh.equals("undefined")){
			formTable=formTable+" and QKMC='"+zh+"'";
		}
		if(!JRBZ1.equals("") && JRBZ1!=null &&  !JRBZ1.equals("undefined") && !JRBZ1.equals("全部")){
			formTable=formTable+" and jrbz='"+JRBZ1+"'";
		}
		if(!dyearC.equals("") && dyearC!=null &&  !dyearC.equals("undefined")){
			formTable=formTable+" and DYEAR='"+dyearC+"'";
		}
		if(!groupName.equals("") && groupName!=null &&  !groupName.equals("undefined")){
			formTable=formTable+" and BLOCKSTATIONNAME='"+groupName+"'";
		}
		
		String[] cloumnsName = {"WATERFLOODING_WELLID","ORG_ID","QKID","A2ID","WATERFLOODING_NAME","CHANNEL_NUMBER","WATERFLOODING_CODE","STATUS_VALUE","COMMISSIONING_DATE","DYEAR",
				"RLAST_OPER","RLAST_ODATE","REMARK","OILSTATIONNAME","AREABLOCK","BLOCKSTATIONNAME","WATERINJECTIONTOPR","GH_ID","PROD_SNS","QKMC","JRBZ","LJJDID","LJJD_S"};
		String kk="WATERFLOODING_WELLID";
		for(int m=1;m<cloumnsName.length;m++){
			if("RLAST_ODATE".equals(cloumnsName[m])){
				kk += ",to_char(RLAST_ODATE,'YYYY-MM-DD HH24:MI:SS') as RLAST_ODATE";
			}else{
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
			total = waterFloodingDao.getCounts(totalNum);
		}
		//排序
		if(!"".equals(sort) && !"waterflooding_name".equals(sort)){
			for (String cloumn : cloumnsName) {
				if(cloumn.equals(sort)){
					boilersInfo +=" order by " + cloumn + " " + order;
					break;
				}
				
			}
		}
		else {
			boilersInfo +=" order by nlssort(waterflooding_name, 'NLS_SORT=SCHINESE_STROKE_M') ";
		}
		//计算分页
		PageControl control = new PageControl();
		//当前页
		control.setInt_num(rowsPerpage);
		control.init(pageNo, total);
		//开始条数
		int start = control.getStart()-1;
		
		List<Object[]> lo = waterFloodingDao.searchwaterFL(boilersInfo,start,rowsPerpage);
		if (lo != null && 0 < lo.size()) {
			// 生成树节点json文档
			jsonArr = new JSONArray();
			for (Object[] o : lo) {
				tjo = new JSONObject();

				for (int i = 0; i < o.length; i++) {
					if (o[i] == null||o[i].equals("null")) {
						o[i] = "";
					}
					
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
		sqls[0] = " DELETE from pc_cd_waterflooding_well_t d where d.waterflooding_wellid = '"+arg+"'";
		sqls[1] = " DELETE from pc_cd_org_t t where t.org_id = '"+orgid+"'";
		
		return waterFloodingDao.removeStationInfo(sqls);
	}
	
	public JSONArray queryWaterflooding(String arg) {
		JSONArray jsonArr = null;
		JSONObject jsobj = null;
		String sql = "select wf.waterflooding_wellid,wf.waterflooding_name from PC_CD_WATERFLOODING_WELL_T wf left join (select o.org_id,o.structurename from pc_cd_org_t o where o.pid='" + arg + "') o1 on o1.org_id=wf.org_id where o1.org_id=wf.org_id order by nlssort(wf.waterflooding_name,'NLS_SORT=SCHINESE_STROKE_M')";
		if ("".equals(arg)) {//全部的锅炉
			sql = "select we.waterflooding_wellid,we.waterflooding_name from PC_CD_WATERFLOODING_WELL_T we order by nlssort(we.waterflooding_name,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		List<Object[]> dataSet = waterFloodingDao.queryInfo(sql);
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
		String oilSql = "select r.ORG_ID,r.STRUCTURENAME from PC_CD_INJECTIONTOPRYTREE_V r where r.STRUCTURETYPE='4' order by nlssort(r.STRUCTURENAME,'NLS_SORT=SCHINESE_STROKE_M')";
		String wellSql = "select w.org_id,w.WATER_INJECTIONTOPR_NAME from PC_CD_WATER_INJECTIONTOPRY_T w order by nlssort(w.WATER_INJECTIONTOPR_NAME,'NLS_SORT=SCHINESE_STROKE_M')";
		String waterfloodingSql = "select we.waterflooding_wellid,we.waterflooding_name from PC_CD_WATERFLOODING_WELL_T we order by nlssort(we.waterflooding_name,'NLS_SORT=SCHINESE_STROKE_M')";
		String teamgSql = "select  a.org_id,a.team  from  pc_cd_team_t a order by nlssort(a.team,'NLS_SORT=SCHINESE_STROKE_M')";

		JSONArray oilJsonArr = null;
		JSONArray wellArr = null;
		JSONArray waterfloodingArr = null;
		JSONArray teamArr = null;
		JSONObject jsobj = null;
		List<Object[]> oilSet = waterFloodingDao.queryInfo(oilSql);
		List<Object[]> wellSet = waterFloodingDao.queryInfo(wellSql);
		List<Object[]> waterfloodingSet = waterFloodingDao.queryInfo(waterfloodingSql);
		List<Object[]> teamSet = waterFloodingDao.queryInfo(teamgSql);
		
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
		
		if(wellSet != null && wellSet.size()>0){
			wellArr = new JSONArray();   
			for(Object[] entry : wellSet){
				jsobj = new JSONObject();
				jsobj.put("text", entry[1]);
				jsobj.put("id",entry[0]);
				wellArr.add(jsobj);
			}
		}
		if(waterfloodingSet != null && waterfloodingSet.size()>0){
			waterfloodingArr = new JSONArray();   
			for(Object[] entry : waterfloodingSet){
				jsobj = new JSONObject();
				jsobj.put("text", entry[1]);
				jsobj.put("id",entry[0]);
				waterfloodingArr.add(jsobj);
			}
		}
		if(teamSet != null && teamSet.size()>0){
			teamArr = new JSONArray();   
			for(Object[] entry : teamSet){
				jsobj = new JSONObject();
				jsobj.put("text", entry[1]);
				jsobj.put("id",entry[0]);
				teamArr.add(jsobj);
			}
		}
		jsobj = new JSONObject();
		jsobj.put("oiltext",oilJsonArr);
		jsobj.put("welltext",wellArr);
		jsobj.put("waterfloodtext",waterfloodingArr);
		jsobj.put("TeamText",teamArr);
		return jsobj;
	}
	public boolean addwell(PcCdWaterfloodingWellT Well) throws Exception {
		
		return waterFloodingDao.save(Well);
	}
	public List<Object[]> searchQiao(String name, String type)throws Exception {
		List<Object[]> orglist =  null;
		String sql = "select * from pc_cd_org_t o where 1=1 and o.structurename = '"+name+"' and o.structuretype="+type;
		
		orglist = waterFloodingDao.queryInfo(sql);
		
		return orglist;
	}
	public List<PcCdWaterfloodingWellT> searchWaterFlood(String id,String wellName) throws Exception {
		String  hql = " from  PcCdWaterfloodingWellT w  where 1=1";
		List<PcCdWaterfloodingWellT> wellList= null;
		if(id != null && !"".equals(id)){
			hql += " and w.waterfloodingWellid = '"+id+"'";
		}
		if(wellName != null && !"".equals(wellName)){
			hql += " and w.waterfloodingName = '"+wellName+"'";
		}
		wellList = waterFloodingDao.searchWaterFlood(hql);
		return wellList;
	}
	public boolean updatewell(PcCdWaterfloodingWellT Well) throws Exception {
		
		return waterFloodingDao.updateFlooding(Well);
	}
	@Override
	public HashMap<String, Object> searchOnExport(String oilstationname1,
			String rulewellname1, String waterflooding1 , String areablock,
			String jrbz1,String dyearC,String groupName, String totalNumFlag)throws Exception{

		JSONArray jsonArr = null;
		JSONObject tjo = null;
		HashMap<String,Object> map = new HashMap<String, Object>();
		String cloums = "select   ";
		String formTable = " from pc_cd_water_flooding_v  where 1=1 ";
		String totalNum = "select count(*) ";
		
		if(!oilstationname1.equals("")&&oilstationname1!=null&&!oilstationname1.equals("undefined")&!oilstationname1.equals("全部")){
			formTable=formTable+" and OILSTATIONNAME='"+oilstationname1+"'";
		}
		
		if(!rulewellname1.equals("")&&rulewellname1!=null&&!rulewellname1.equals("undefined")){
			formTable=formTable+" and waterinjectiontopr='"+rulewellname1+"'";
		}
		if(!waterflooding1.equals("")&&waterflooding1!=null&&!waterflooding1.equals("undefined")){
			formTable=formTable+" and WATERFLOODING_NAME='"+waterflooding1+"'";
		}
		
		if(!areablock.equals("")&&areablock!=null&&!areablock.equals("undefined")){
			formTable=formTable+" and QKMC='"+areablock+"'";
		}
		if(!jrbz1.equals("") && jrbz1!=null &&  !jrbz1.equals("undefined") && !jrbz1.equals("全部")){
			formTable=formTable+" and jrbz='"+jrbz1+"'";
		}
		if(!dyearC.equals("") && dyearC!=null &&  !dyearC.equals("undefined")){
			formTable=formTable+" and DYEAR='"+dyearC+"'";
		}
		if(!groupName.equals("") && groupName!=null &&  !groupName.equals("undefined")){
			formTable=formTable+" and BLOCKSTATIONNAME='"+groupName+"'";
		}
		String[] cloumnsName = {"WATERFLOODING_NAME","WATERFLOODING_CODE","OILSTATIONNAME","BLOCKSTATIONNAME","QKMC","WATERINJECTIONTOPR","CHANNEL_NUMBER", "PROD_SNS","COMMISSIONING_DATE","DYEAR",
				"JRBZ","LJJD_S","REMARK"};
		String kk="WATERFLOODING_NAME";
		for(int m=1;m<cloumnsName.length;m++){
			if("WATERFLOODING_NAME".equals(cloumnsName[m])){
				//kk=kk+","+"to_char(CJSJ,'YYYY-MM-DD hh24:mi:ss') as CJSJ";
			}else{
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
			total = waterFloodingDao.getCounts(totalNum);
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
			lo = waterFloodingDao.searchData(thinOilWellRD);
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
	@Override
	public JSONArray queryOilStation(String arg) throws Exception {

		JSONArray jsonArr = null;
		JSONObject jsobj = null;
		String sql = " select a.oildrilling_stationid,a.oildrilling_station_name  from  pc_cd_oildrilling_station_t a order by nlssort(a.oildrilling_station_name,'NLS_SORT=SCHINESE_STROKE_M')";
		List<Object[]> dataSet = waterFloodingDao.queryInfo(sql);
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
	@Override
	public JSONArray injectName() throws Exception {
		JSONArray  jsonArr = null;
		JSONObject  jsobj =null;
		String sql ="select a.org_id,a.water_injectiontopr_name  from  pc_cd_water_injectiontopry_t  a order by nlssort(a.water_injectiontopr_name,'NLS_SORT=SCHINESE_STROKE_M')";
		List<Object[]> dataSet = waterFloodingDao.queryInfo(sql);
		if(dataSet != null && dataSet.size()>0){
			//生成树节点json文档
			jsonArr = new JSONArray();   
			for(Object[] entry : dataSet){
				jsobj = new JSONObject();
				jsobj.put("text",entry[1]);
				jsobj.put("id",entry[0]);
				jsonArr.add(jsobj);
			}
			return jsonArr;
		}
		jsobj = new JSONObject();
		jsobj.put("text","");
		jsobj.put("id","");
		jsonArr = new JSONArray();
		jsonArr.add(jsobj);
		return jsonArr;
	}

}
