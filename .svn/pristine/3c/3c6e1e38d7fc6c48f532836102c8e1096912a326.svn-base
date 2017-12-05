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
import com.echo.dto.PcCdGasWellT;
import com.echo.service.GasWellService;
import com.echo.service.RuleWellService;
import com.echo.service.SagdService;
import com.echo.service.ThinOilWellService;

public class GasWellServiceImpl implements GasWellService{
	private GasWellDao gasWellDao;
	
	
	public void setGasWellDao(GasWellDao gasWellDao) {
		this.gasWellDao = gasWellDao;
	}
	public JSONObject searchGasWell(String qk,String zh,String jh,String name,String jrbz1,String dyearC,int pageNo,String sort,String order,int rowsPerpage) {
		JSONArray jsonArr = null;
		JSONObject tjo = null;
		String cloums = "select   ";
		String formTable = " from pc_cd_well_gas_v  where 1=1 ";
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
		
		if(!name.equals("")&&name!=null&&!name.equals("undefined")){
			formTable=formTable+" and well_name like '%"+name+"%'";
		}
		if(!jrbz1.equals("") && jrbz1!=null && !jrbz1.equals("undefined") && !jrbz1.equals("全部")){
			formTable=formTable+" and jrbz ='"+jrbz1+"'";
		}
		if(!dyearC.equals("") && dyearC!=null && !dyearC.equals("undefined")){
			formTable=formTable+" and DYEAR ='"+dyearC+"'";
		}

		
		String[] cloumnsName = {"GAS_WELLID","ORG_ID","QKID","A2ID","WELL_NAME","WELL_COLE","BEWELL_NAME","COMMISSIONING_DATE","DYEAR","STATUS_VALUE",
				"RLAST_OPER","RLAST_ODATE","REMARK","OILSTATIONNAME","AREABLOCK","BLOCKSTATIONNAME","GH_ID","PROD_SNS","BRANCHINGID","JRBZ","SWITCH_IN_FLAG","LJJDID","LJJD_S","QKMC"};
		String kk="GAS_WELLID";
		for(int m=1;m<cloumnsName.length;m++){
			if("RLAST_ODATE".equals(cloumnsName[m])){
				kk += ",to_char(RLAST_ODATE,'YYYY-MM-DD HH24:MI:SS') as RLAST_ODATE";
			}else{
				kk=kk+","+cloumnsName[m];
			}
		}
		String boilersInfo = cloums + kk+formTable;
		//System.out.println(boilersInfo);
		//获取总条数
		int total = 0;
		if ("1".equals(totalNum)) {
			total = 1;
		} else {
			totalNum += formTable;
			total = gasWellDao.getCounts(totalNum);
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
		
		List<Object[]> lo = gasWellDao.searchGasWell(boilersInfo,start,rowsPerpage);
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
		sqls[0] = " DELETE from pc_cd_gas_well_t d where d.gas_wellid = '"+arg+"'";
		sqls[1] = " DELETE from pc_cd_org_t t where t.org_id = '"+orgid+"'";
		
		return gasWellDao.removeStationInfo(sqls);
	}
	
	public JSONArray queryStationInfo(String oilid,String areaid,String selecteValue) {
		JSONArray jsonArr = null;
		JSONObject jsobj = null;
		String sql = "select gs.org_id,gs.station_name from pc_cd_gas_station_t gs left join (select o.* from pc_cd_org_t o left join PC_CD_OILDRILLING_STATION_T oil on o.pid=oil.org_id where o.pid='" + oilid + "') ss on gs.org_id=ss.org_id where gs.org_id=ss.org_id  order by nlssort(gs.station_name,'NLS_SORT=SCHINESE_STROKE_M')";
		if ("".equals(selecteValue)) {//查询所有含锅炉的注转站
			sql = "select gs.org_id,gs.station_name from pc_cd_gas_station_t gs  order by nlssort(gs.station_name,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		if ("2".equals(selecteValue) && "".equals(oilid) && !"".equals(areaid)) {//按区块查询 注转站
			sql = "select gs.org_id,gs.station_name from pc_cd_gas_station_t gs where gs.qkid='" + areaid + "' order by nlssort(gs.station_name,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		if ("1".equals(selecteValue) && !"".equals(oilid) && !"".equals(areaid)) {
			sql = "select gs.org_id,gs.station_name from pc_cd_gas_station_t gs left join (select o.org_id from Pc_Cd_Org_t o where o.pid='" + oilid + "') o1 on gs.org_id=o1.org_id left join pc_cd_area_info_t a on a.qkid=gs.qkid where gs.org_id=o1.org_id and gs.qkid='" + areaid + "' order by nlssort(gs.station_name,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		List<Object[]> dataSet = gasWellDao.queryInfo(sql);
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

	public JSONArray queryWellInfo(String arg) {
		JSONArray jsonArr = null;
		JSONObject jsobj = null;
		String sql = "select w.gas_wellid,w.well_name from PC_CD_GAS_WELL_T w left join (select o.org_id from pc_cd_org_t o where o.pid='" + arg + "') o1 on o1.org_id=w.org_id where o1.org_id=w.org_id order by nlssort(w.well_name,'NLS_SORT=SCHINESE_STROKE_M')";
		if ("".equals(arg)) {//全部的锅炉
			sql = "select w.gas_wellid,w.well_name from PC_CD_GAS_WELL_T w order by nlssort(w.well_name,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		List<Object[]> dataSet = gasWellDao.queryInfo(sql);
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
		String oilSql = "select r.ORG_ID,r.STRUCTURENAME from PC_CD_GASTREE_V r where r.STRUCTURETYPE='4' order by nlssort(r.STRUCTURENAME,'NLS_SORT=SCHINESE_STROKE_M')";
		String areaSql = "select distinct a.qkid,a.qkmc from pc_cd_area_info_t a left join pc_cd_gas_well_t r on a.qkid=r.qkid where a.qkid=r.qkid order by nlssort(a.qkmc,'NLS_SORT=SCHINESE_STROKE_M')";
		String stationSql = "select gs.org_id,gs.station_name from pc_cd_gas_station_t gs  order by nlssort(gs.station_name,'NLS_SORT=SCHINESE_STROKE_M')";
		String wellSql = "select w.gas_wellid,w.well_name from PC_CD_GAS_WELL_T w order by nlssort(w.well_name,'NLS_SORT=SCHINESE_STROKE_M')";
		JSONArray oilJsonArr = null;
		JSONArray areaJsonArr = null;
		JSONArray stationArr = null;
		JSONArray wellArr = null;
		JSONObject jsobj = null;
		List<Object[]> oilSet = gasWellDao.queryInfo(oilSql);
		List<Object[]> areaSet = gasWellDao.queryInfo(areaSql);
		List<Object[]> stationSet = gasWellDao.queryInfo(stationSql);
		List<Object[]> wellSet = gasWellDao.queryInfo(wellSql);
		
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
	public List<PcCdGasWellT> searchGasName(String gaswellname)throws Exception {
		List<PcCdGasWellT> gasList = null;
		 String hql = "from PcCdGasWellT g where 1=1 ";
		 if(gaswellname !=null && !"".equals(gaswellname)){
			 hql += " and g.wellName ='"+gaswellname+"'";
		 }
		 gasList = gasWellDao.searchGasName(hql);
		return gasList;
	}
	public List<Object[]> searchGasation(String gasstationname)throws Exception {
		 List<Object[]> manilist = null;
			//String sql ="select t.station_name  from pc_cd_gas_station_t t where 1=1 and t.station_name = '"+gasstationname+"'";
			String sql = "select * from pc_cd_org_t o where 1=1 and o.structurename = '"+gasstationname+"' ";

			manilist = gasWellDao.queryInfo(sql);
			return manilist;
		}
	public boolean addGas(PcCdGasWellT gas) throws Exception {
		return gasWellDao.save(gas);
	}
	public List<PcCdGasWellT> searchGasById(String gasid, String gasname)throws Exception {
		List<PcCdGasWellT> gasList = null;
		String hql = " from PcCdGasWellT g where 1=1 ";
		if(gasid !=null && !"".equals(gasid)){
			hql += " and g.gasWellid ='"+gasid+"'";
		}
		if(gasname !=null && !"".equals(gasname)){
			hql += "and g.wellName ='"+gasname+"'";
		}
		gasList = gasWellDao.searchGasById(hql);
		return gasList;
	}
	public boolean updateGas(PcCdGasWellT gas) throws Exception {
		boolean flag = true;
		try {
			flag = gasWellDao.updateGas(gas);
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
	@Override
	public HashMap<String, Object> searchOnExport(String oilstationname1,String areablock1,String blockstationname1,String rulewellname1,String jrbz1,String dyearC,String totalNumFlag)throws Exception {

		JSONArray jsonArr = null;
		JSONObject tjo = null;
		HashMap<String,Object> map = new HashMap<String, Object>();
		String cloums = "select   ";
		String formTable = " from pc_cd_well_gas_v  where 1=1 ";
		String totalNum = "select count(*) ";
		if(!oilstationname1.equals("")&&oilstationname1!=null&&!oilstationname1.equals("undefined")&!oilstationname1.equals("全部")){
			formTable=formTable+" and oilstationname='"+oilstationname1+"'";
		}
		if(!areablock1.equals("")&&areablock1!=null&&!areablock1.equals("undefined")){
			formTable=formTable+" and qkmc='"+areablock1+"'";
		}
		if(!blockstationname1.equals("")&&blockstationname1!=null&&!blockstationname1.equals("undefined")){
			formTable=formTable+" and BLOCKSTATIONNAME='"+blockstationname1+"'";
		}
		
		if(!rulewellname1.equals("")&&rulewellname1!=null&&!rulewellname1.equals("undefined")){
			formTable=formTable+" and well_name ='"+rulewellname1+"'";
		}
		if(!jrbz1.equals("") && jrbz1!=null && !jrbz1.equals("undefined") && !jrbz1.equals("全部")){
			formTable=formTable+" and jrbz ='"+jrbz1+"'";
		}
		if(!dyearC.equals("") && dyearC!=null && !dyearC.equals("undefined")){
			formTable=formTable+" and DYEAR ='"+dyearC+"'";
		}

		String[] cloumnsName = {"OILSTATIONNAME","QKMC","BLOCKSTATIONNAME","WELL_NAME","WELL_COLE","BEWELL_NAME","PROD_SNS","COMMISSIONING_DATE","DYEAR","JRBZ",
				"LJJD_S","REMARK"};
		
		String kk="OILSTATIONNAME";
		for(int m=1;m<cloumnsName.length;m++){
			if("OILSTATIONNAME".equals(cloumnsName[m])){
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
			total = gasWellDao.getCounts(totalNum);
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
			lo = gasWellDao.searchData(thinOilWellRD);
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
}
