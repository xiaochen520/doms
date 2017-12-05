package com.echo.service.impl;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.beans.PageControl;
import com.echo.dao.WaterSoWellDao;
import com.echo.dto.PcCdWaterSourceWellT;
import com.echo.service.WaterSoWellService;

public class WaterSoWellServiceImpl implements WaterSoWellService {
	
	private WaterSoWellDao waterSoWellDao;

	public WaterSoWellDao getWaterSoWellDao() {
		return waterSoWellDao;
	}

	public void setWaterSoWellDao(WaterSoWellDao waterSoWellDao) {
		this.waterSoWellDao = waterSoWellDao;
	}
	
//查询区块
	public JSONArray queryWorkarea() {

		JSONArray jsonArr = null;
		JSONObject jsobj = null;
		String sql = "select t.org_id,t.qkmc from PC_CD_AREA_INFO_T t order by nlssort(t.qkmc,'NLS_SORT=SCHINESE_STROKE_M')";
		
		List<Object[]> dataSet = waterSoWellDao.queryWorkarea(sql);
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
//查询站号
	public JSONArray queryWellarea(String arg) {
		JSONArray jsonArr = null;
		JSONObject jsobj = null;
		String sql = "select t.org_id,t.structurename  from pc_cd_station_t t where t.pid='" + arg + "' order by nlssort(t.structurename,'NLS_SORT=SCHINESE_STROKE_M')";
		List<Object[]> dataSet = waterSoWellDao.queryWellarea(sql);
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
//查询水源井
	public JSONArray queryWellmonth(String arg) {
		JSONArray jsonArr = null;
		JSONObject jsobj = null;
		String sql = "select t.org_id,t.structurename from pc_cd_org_t t where t.pid='" + arg + "' order by nlssort(t.structurename,'NLS_SORT=SCHINESE_STROKE_M') order by nlssort(o.structurename,'NLS_SORT=SCHINESE_STROKE_M')";
		List<Object[]> dataSet = waterSoWellDao.queryWellmonth(sql);
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

	public boolean saveOrUpdate(String TJQK, String TJZH,
			String waterSourceWellid, String wellName, String wellCode,
			String bewellName, String startupTime, String rlastOper,
			String rlastOdate, String remark) {
		// TODO Auto-generated method stub
		return false;
	}

	public HashMap<String,Object> searchWaterSoWell(String totalNumFlag,String jrbz,String qk,String zh,String jh,String ghid,String dyearC,int pageNo,String sort,String order,int rowsPerpage){
		JSONArray jsonArr = null;
		JSONObject tjo = null;
		HashMap<String,Object> map = new HashMap<String, Object>();
		String cloums = "select   ";
		String formTable = " from pc_cd_water_sour_v  where 1=1 ";
		String totalNum = "select count(*) ";
		if(!qk.equals("")&&qk!=null&&!qk.equals("undefined")&!qk.equals("全部")){
			formTable=formTable+" and COMBINATION='"+qk+"'";
		}
		if(!zh.equals("")&&zh!=null&&!zh.equals("undefined")){
			formTable=formTable+" and STATIONNUM='"+zh+"'";
		}
		if(!jh.equals("")&&jh!=null&&!jh.equals("undefined")){
			formTable=formTable+" and WATER_SOURCE_WELLID='"+jh+"'";
		}
		if(!ghid.equals("")&&ghid!=null&&!ghid.equals("undefined")){
			formTable=formTable+" and ORG_ID='"+ghid+"'";
		}
		if(!jrbz.equals("")&&ghid!=null&&!jrbz.equals("undefined")&&!"全部".equals(jrbz)){
			formTable=formTable+" and jrbz='"+jrbz+"'";
		}
		if(!dyearC.equals("")&&dyearC!=null&&!dyearC.equals("undefined")){
			formTable=formTable+" and DYEAR='"+dyearC+"'";
		}
		
		String[] cloumnsName = {"WATER_SOURCE_WELLID","WELL_NAME","COMBINATION","STATIONNUM","WELL_CODE","BEWELL_NAME","STATUS_VALUE","PROD_SNS","COMMISSIONING_DATE","DYEAR",
				"JRBZ","LJJDID","LJJD_S","RLAST_OPER","RLAST_ODATE","REMARK","GH_ID","ORG_ID","A2ID",};
		String kk="WATER_SOURCE_WELLID";
		for(int m=1;m<cloumnsName.length;m++){
			if("RLAST_ODATE".equals(cloumnsName[m])){
				kk += ",to_char(RLAST_ODATE,'YYYY-MM-DD HH24:MI:SS') as RLAST_ODATE";
			}else{
				kk=kk+","+cloumnsName[m];
			}
		}
		String boilersInfo = cloums + kk+formTable;
		if (!"".equals(totalNumFlag)) {
			boilersInfo = cloums + " WELL_NAME,COMBINATION,STATIONNUM,WELL_CODE,BEWELL_NAME,PROD_SNS,COMMISSIONING_DATE,DYEAR,JRBZ,LJJD_S,REMARK " +formTable;
		}
		//获取总条数
		int total = 0;
		if ("1".equals(totalNum)) {
			total = 1;
		} else {
			totalNum += formTable;
			total = waterSoWellDao.getCounts(totalNum);
		}
		if ("totalNum".equals(totalNumFlag)) {
			map.put("totalNum", total + "");
			return map;
		}
		//排序
		if(!"".equals(sort) && !"WELL_NAME".equals(sort)){
			for (String cloumn : cloumnsName) {
				if(cloumn.equals(sort)){
					boilersInfo +=" order by " + cloumn + " " + order;
					break;
				}
				
			}
		}
		else {
			boilersInfo +=" order by nlssort(WELL_NAME, 'NLS_SORT=SCHINESE_STROKE_M') ";
		}
		
		
		List<Object[]> lo = null;
		if ("export".equals(totalNumFlag)) {
			lo = waterSoWellDao.queryInfo(boilersInfo);
		}
		else {
			//计算分页
			PageControl control = new PageControl();
			//当前页
			control.setInt_num(rowsPerpage);
			control.init(pageNo, total);
			//开始条数
			int start = control.getStart()-1;
			lo = waterSoWellDao.searchWaterSoWell(boilersInfo,start,rowsPerpage);
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
public boolean removeStationInfo(String arg,String orgid){
	String[] sqls = new String[2];
	sqls[0] = " DELETE from pc_cd_water_source_well_t o where o.water_source_wellid= '"+arg+"'";
	sqls[1] = " DELETE from pc_cd_org_t t where t.org_id = '"+orgid+"'";
	
	return waterSoWellDao.removeStationInfo(sqls);
}

	public JSONArray queryCombinationInfo(String arg) {
		JSONArray jsonArr = null;
		JSONObject jsobj = null;
		//查询所有包含锅炉的采油站
		String combinationStation = "select r.ORG_ID,r.STRUCTURENAME from PC_CD_WATER_SOURCETREE_V r where r.STRUCTURETYPE='5' order by nlssort(r.STRUCTURENAME,'NLS_SORT=SCHINESE_STROKE_M')";
		if ("station".equals(arg)) {
			combinationStation = "select r.ORG_ID,r.STRUCTURENAME from PC_CD_STATIONTREE_V r where r.STRUCTURETYPE='5' order by nlssort(r.STRUCTURENAME,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		if ("gas".equals(arg)) {
			combinationStation = "select r.ORG_ID,r.STRUCTURENAME from PC_CD_GAS_STATIONTREE_V r where r.STRUCTURETYPE='5' order by nlssort(r.STRUCTURENAME,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		if ("boiler".equals(arg)) {
			combinationStation = "select r.ORG_ID,r.STRUCTURENAME from PC_CD_BOILERTREE_V r where r.STRUCTURETYPE='5' order by nlssort(r.STRUCTURENAME,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		if ("group".equals(arg)) {
			combinationStation = "select r.ORG_ID,r.STRUCTURENAME from PC_CD_STATIONTREE_V r where r.STRUCTURETYPE='14' order by nlssort(r.STRUCTURENAME,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		List<Object[]> dataSet = waterSoWellDao.queryInfo(combinationStation);
		if(dataSet != null && dataSet.size()>0){
			//生成树节点json文档
			jsonArr = new JSONArray();
			for(Object[] entry : dataSet){
				jsobj = new JSONObject();
				jsobj.put("text", entry[1]);
				jsobj.put("id",entry[0]);
				jsonArr.add(jsobj);
			}
			if ("station".equals(arg)) {
				return jsonArr;
			}
			if ("group".equals(arg)) {
				return jsonArr;
			}
			jsobj = new JSONObject();
			jsobj.put("text", "全部");
			jsobj.put("id", 1);
			jsonArr.add(jsobj);
			return jsonArr;
		}
		jsobj = new JSONObject();
		jsobj.put("text", "");
		jsobj.put("id","");
		jsonArr = new JSONArray();
		jsonArr.add(jsobj);
		return jsonArr;
	}
	
	public JSONArray queryWaterSoWell(String arg) {
		JSONArray jsonArr = null;
		JSONObject jsobj = null;
		//String observationWell = "select w.observation_wellid,w.well_name from PC_CD_OBSERVATION_WELL_T w";
		// 
		String observationWell = "select w.water_source_wellid,w.well_name  from  pc_cd_water_source_well_t w order by nlssort(w.well_name,'NLS_SORT=SCHINESE_STROKE_M')";
		if (!"".equals(arg)) {
			observationWell = "select sw.water_source_wellid,sw.well_name,sw.org_id from PC_CD_WATER_SOURCE_WELL_T sw left join pc_cd_org_t o on  o.org_id= sw.org_id where o.pid='" + arg + "' order by nlssort(sw.well_name,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		List<Object[]> dataSet = waterSoWellDao.queryInfo(observationWell);
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
		String processingStation = "select t.org_id,t.structurename from PC_CD_WATER_SOURCETREE_V t where t.structuretype='11'";
		if (!"".equals(arg)) {
			processingStation = "select o.org_id,o.structurename from pc_cd_org_t o where o.pid='" + arg + "' and o.structuretype='11' order by nlssort(o.structurename,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		List<Object[]> dataSet = waterSoWellDao.queryInfo(processingStation);
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
		//联合站
		String combinationStation = "select r.ORG_ID,r.STRUCTURENAME from PC_CD_WATER_SOURCETREE_V r where r.STRUCTURETYPE='5' order by nlssort(r.STRUCTURENAME,'NLS_SORT=SCHINESE_STROKE_M')";
		//处理站
		String processingStation = "select t.org_id,t.structurename from PC_CD_WATER_SOURCETREE_V t where t.structuretype='11'";
		//观察井
		String observationWell = "select w.water_source_wellid,w.well_name  from  pc_cd_water_source_well_t w order by nlssort(w.well_name,'NLS_SORT=SCHINESE_STROKE_M')";
		JSONArray combinationStationArr = null;
		JSONArray processingStationArr = null;
		JSONArray observationWellArr = null;
		JSONObject jsobj = null;
		List<Object[]> combinationStationSet = waterSoWellDao.queryInfo(combinationStation);
		List<Object[]> processingStationSet = waterSoWellDao.queryInfo(processingStation);
		List<Object[]> observationWellSet = waterSoWellDao.queryInfo(observationWell);
		
		if(combinationStationSet != null && combinationStationSet.size()>0){
			combinationStationArr = new JSONArray();   
			for(Object[] entry : combinationStationSet){
				jsobj = new JSONObject();
				jsobj.put("text", entry[1]);
				jsobj.put("id",entry[0]);
				combinationStationArr.add(jsobj);
			}
			jsobj = new JSONObject();
			jsobj.put("text", "全部");
			jsobj.put("id", 1);
			combinationStationArr.add(jsobj);
		}
		
		if(processingStationSet != null && processingStationSet.size()>0){
			processingStationArr = new JSONArray();   
			for(Object[] entry : processingStationSet){
				jsobj = new JSONObject();
				jsobj.put("text", entry[1]);
				jsobj.put("id",entry[0]);
				processingStationArr.add(jsobj);
			}
		}
		
		if(observationWellSet != null && observationWellSet.size()>0){
			observationWellArr = new JSONArray();   
			for(Object[] entry : observationWellSet){
				jsobj = new JSONObject();
				jsobj.put("text", entry[1]);
				jsobj.put("id",entry[0]);
				observationWellArr.add(jsobj);
			}
		}
		
		jsobj = new JSONObject();
		jsobj.put("combinationStationtext",combinationStationArr);
		jsobj.put("processingStationtext",processingStationArr);
		jsobj.put("observationWelltext",observationWellArr);
		return jsobj;
	}

	public List<PcCdWaterSourceWellT> searchWaterSoWells(String id, String name)
			throws Exception {
		String hql = "FROM PcCdWaterSourceWellT w WHERE 1=1 ";
		List<PcCdWaterSourceWellT>  wellList = null; 
		if(id != null && !"".equals(id)){
			hql += " and w.waterSourceWellid = '"+id+"'";
			
		}
		
		if(name != null && !"".equals(name)){
			
			hql += " and w.wellName = '"+name+"'";
		}
	
		wellList = waterSoWellDao.searchwellLists(hql);
		
		return wellList;
	}

	public List<Object[]> searchOrg(String name, String type) throws Exception {
		List<Object[]> orglist =  null;
		String sql = "select * from pc_cd_org_t o where 1=1 and o.structurename = '"+name+"' and o.structuretype="+type;
		
		orglist = waterSoWellDao.queryInfo(sql);
		
		return orglist;
	}

	public boolean addwell(PcCdWaterSourceWellT well) throws Exception {
		return waterSoWellDao.save(well);
	}

	public boolean updatewell(PcCdWaterSourceWellT well) throws Exception {
		return waterSoWellDao.updateWaterSoWell(well);
	}
}
