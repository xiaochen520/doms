package com.echo.service.impl;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.beans.PageControl;
import com.echo.dao.ObservationDao;
import com.echo.dto.PcCdObservationWellT;
import com.echo.dto.PcCdOrgT;

import com.echo.service.ObservationService;


public class ObservationServiceImpl implements ObservationService{
	private ObservationDao observationDao;
	
	public ObservationDao getObservationDao() {
		return observationDao;
	}

	public void setObservationDao(ObservationDao observationDao) {
		this.observationDao = observationDao;
	}

	public JSONObject searchObservationWell(String qk,String st,String zh,String jh,int pageNo,String sort,String order,int rowsPerpage) {
		JSONArray jsonArr = null;
		JSONObject tjo = null;
		String cloums = "select   ";
		String formTable = " from pc_cd_well_obs_v  where 1=1 ";
		String totalNum = "select count(*) ";
		if(!qk.equals("")&&qk!=null&&!qk.equals("undefined")&!qk.equals("全部")){
			formTable=formTable+" and oilstationname='"+qk+"'";
		}
		if(!st.equals("")&&st!=null&&!st.equals("undefined")){
			formTable=formTable+" and qkmc='"+st+"'";
		}
		if(!zh.equals("")&&zh!=null&&!zh.equals("undefined")){
			formTable=formTable+" and blockstationname='"+zh+"'";
		}
		if(!jh.equals("")&&jh!=null&&!jh.equals("undefined")){
			formTable=formTable+" and well_name='"+jh+"'";
		}
		
		
		
		
		
		String[] cloumnsName = {"OILSTATIONNAME","AREABLOCK","BLOCKSTATIONNAME","GH_ID","OBSERVATION_WELLID","ORG_ID","QKID",
				"RLAST_OPER","RLAST_ODATE","REMARK","A2ID","WELL_NAME","WELL_COLE","BEWELL_NAME","COMMISSIONING_DATE","STATUS_VALUE","PROD_SNS","QKMC","LJJDID","LJJD_S","JRBZ"};
		String kk="OILSTATIONNAME";
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
			total = observationDao.getCounts(totalNum);
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
		//计算分页
		PageControl control = new PageControl();
		//当前页
		control.setInt_num(rowsPerpage);
		control.init(pageNo, total);
		//开始条数
		int start = control.getStart()-1;
		List<Object[]> 	lo = observationDao.searchObservationWell(boilersInfo,start,rowsPerpage);
		
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

	public JSONArray searchStructureJc(String hql) {
		// TODO Auto-generated method stub
		JSONArray jsonArr = null ;
		
		List<Object[]> roleList = observationDao.searchStructureJc(hql);
		
		if(roleList != null && roleList.size()>0){
			
			//生成树节点json文档
			jsonArr = new JSONArray();   
			
			for(Object[] entry  : roleList){
				JSONObject tjo = new JSONObject();
				tjo.put("text", entry[1]);
				tjo.put("id",entry[0]);
				jsonArr.add(tjo);
				
			}
		}
		return jsonArr;
	}

	public boolean addObs(PcCdObservationWellT well) throws Exception {
		
		return observationDao.save(well);
	}

	public String serachOrgByName(String name) throws Exception {
		return observationDao.serachOrgByName(name);
	}

	public boolean UpdateObs(PcCdObservationWellT well) throws Exception {
		boolean flag = true ;
		try {
			flag = observationDao.updateObs(well);
		} catch (Exception e) {
			e.printStackTrace();
			return flag;
		}
		return false;
	}

	public List<PcCdObservationWellT> searchObservation(String id,String name)throws Exception {
		List<PcCdObservationWellT> obsList =  null;
		String hql = " from  PcCdObservationWellT  o  where 1=1 ";
		if( id !=null && !"".equals(id)){
			hql += " and o.observationWellid ='"+id+"'";
		}
		if(name !=null && !"".equals(name)){
			hql += " and o.wellName ='"+name+"' ";
		}
		obsList = observationDao.searchObserName(hql);
		return obsList;
	}
	public JSONArray queryWellInfo(String arg) {
		JSONArray jsonArr = null;
		JSONObject jsobj = null;
		String sql = "select w.observation_wellid,w.well_name from PC_CD_OBSERVATION_WELL_T w left join (select o.org_id from pc_cd_org_t o where o.pid='" + arg + "') o1 on o1.org_id=w.org_id where o1.org_id=w.org_id  order by nlssort(w.well_name,'NLS_SORT=SCHINESE_STROKE_M')";
		if ("".equals(arg)) {//全部的锅炉
			sql = "select w.observation_wellid,w.well_name from PC_CD_OBSERVATION_WELL_T w  order by nlssort(w.well_name,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		List<Object[]> dataSet = observationDao.queryInfo(sql);
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
		String oilSql = "select r.ORG_ID,r.STRUCTURENAME from PC_CD_OBSERVATIONTREE_V r where r.STRUCTURETYPE='4' order by nlssort(r.STRUCTURENAME,'NLS_SORT=SCHINESE_STROKE_M')";
		String areaSql = "select distinct a.qkid,a.qkmc from pc_cd_area_info_t a left join pc_cd_observation_well_t r on a.qkid=r.qkid where a.qkid=r.qkid order by nlssort(a.qkmc,'NLS_SORT=SCHINESE_STROKE_M')";
		String stationSql = "select r.ORG_ID,r.STRUCTURENAME from PC_CD_OBSERVATIONTREE_V r where r.STRUCTURETYPE='7' order by nlssort(r.STRUCTURENAME,'NLS_SORT=SCHINESE_STROKE_M')";
		String wellSql = "select w.observation_wellid,w.well_name from PC_CD_OBSERVATION_WELL_T w order by nlssort(w.well_name,'NLS_SORT=SCHINESE_STROKE_M')";
		JSONArray oilJsonArr = null;
		JSONArray areaJsonArr = null;
		JSONArray stationArr = null;
		JSONArray wellArr = null;
		JSONObject jsobj = null;
		List<Object[]> oilSet = observationDao.queryInfo(oilSql);
		List<Object[]> areaSet = observationDao.queryInfo(areaSql);
		List<Object[]> stationSet = observationDao.queryInfo(stationSql);
		List<Object[]> wellSet = observationDao.queryInfo(wellSql);
		
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

	public List<Object[]> searchSation(String name, String type)throws Exception {
		List<Object[]> objList = null;
		String sql = "select * from pc_cd_org_t o where 1=1 and o.structurename = '"+name+"' and o.structuretype="+type;
		objList = observationDao.queryStation(sql);
		return objList;
	}

	public boolean removeObswell(String obsId, String orgid) throws Exception {
		String[] sqls = new String[2];
		sqls[0] = "DELETE FROM  PC_CD_OBSERVATION_WELL_T O WHERE O.OBSERVATION_WELLID= '"+obsId+"'";
		sqls[1]= "DELETE from pc_cd_org_t t where t.org_id = '"+orgid+"'";
		return observationDao.removeObswell(sqls);
	}

}
