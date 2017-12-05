package com.echo.service.impl;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.dao.SearchQueryAllDao;
import com.echo.dto.User;
import com.echo.service.SearchQueryAllService;
import com.echo.util.CommonsUtil;

public class SearchQueryAllServiceImpl implements SearchQueryAllService {
	private SearchQueryAllDao searchQueryAllDao;

	public SearchQueryAllDao getSearchQueryAllDao() {
		return searchQueryAllDao;
	}

	public void setSearchQueryAllDao(SearchQueryAllDao searchQueryAllDao) {
		this.searchQueryAllDao = searchQueryAllDao;
	}
	
	@Override
	public JSONArray queryGroupInfo(String oilid) throws Exception {
		JSONObject  obj = null;
		JSONArray  jArr = null;
		String sql ="";
		if(oilid.endsWith("全部")){
			sql ="select g.org_id,g.team from  pc_cd_team_t g";
		}else{
			sql ="select g.org_id,g.team from  pc_cd_team_t g" 
					+" left join pc_cd_org_t o on g.org_id = o.org_id"
					+" left join pc_cd_org_t o1 on o1.org_id = o.pid where o1.structurename='"+oilid+"'";
		}
		List<Object[]> olData = searchQueryAllDao.queryGroupInfo(sql);
		if(olData !=null && olData.size()>0){
			jArr = new JSONArray();
			for (Object[] entry : olData) {
				obj = new JSONObject();
				obj.put("text", entry[1]);
				obj.put("id", entry[0]);
				jArr.add(obj);
			}
			return jArr;
		}
		jArr = new JSONArray();
		obj = new JSONObject();
		obj.put("text", "");
		obj.put("id", "");
		jArr.add(obj);
		return jArr;
	}

	@Override
	public JSONArray queryGuanHuiInfo(String oilid) throws Exception {
		JSONObject obj = null;
		JSONArray jArr = null;
		String sql ="select a.ghmc,a.manifoldid  from  pc_cd_manifold_t a where a.team_id='"+oilid+"'";
		List<Object[]> GHLList = searchQueryAllDao.queryGroupInfo(sql);
		if(GHLList !=null && GHLList.size()>0){
			jArr = new JSONArray();
			for (Object[] entry : GHLList) {
				obj = new JSONObject();
				obj.put("text", entry[1]);
				obj.put("id", entry[0]);
				jArr.add(obj);
			}
			return jArr;
		}
		jArr = new JSONArray();
		obj = new JSONObject();
		obj.put("text", "");
		obj.put("id", "");
		jArr.add(obj);
		return jArr;
	}

	@Override
	public JSONArray queryOilGuanHuiInfo(String oilid) throws Exception {
		JSONObject obj = null;
		JSONArray jArr =null;
		String sql ="select  m.manifoldid, m.ghmc from pc_cd_manifold_t m  "
					+" left join  pc_cd_org_t o on o.org_id=m.org_id"
					+" left join pc_cd_org_t o1 on o.pid=o1.org_id"
					+" left join pc_cd_org_t o2 on o1.pid=o2.org_id  where o2.structurename='"+oilid+"'";
		List<Object[]> objList = searchQueryAllDao.queryGroupInfo(sql);
		if(objList !=null && objList.size()>0){
			jArr = new JSONArray();
			for (Object[] entry : objList) {
				obj = new JSONObject();
				obj.put("text", entry[1]);
				obj.put("id", entry[0]);
				jArr.add(obj);
			}
			return jArr;
		}
		jArr = new JSONArray();
		obj= new JSONObject();
		obj.put("text", "");
		obj.put("id", "");
		jArr.add(obj);
		return jArr;
	}

	@Override
	public JSONArray searchGHcommon(String cyzid, String zzzid, String teamid,
			String param) throws Exception {

		JSONObject obj = null;
		JSONArray jArr =null;
		String sql = "";

		
		
		if(cyzid != null && !"".equals(cyzid)){
			sql = "select  m.manifoldid, m.ghmc from pc_cd_manifold_t m  "
				+" left join  pc_cd_org_t o on o.org_id=m.org_id"
				+" left join pc_cd_org_t o1 on o.pid=o1.org_id"
				+" left join pc_cd_org_t o2 on o1.pid=o2.org_id  where o2.org_id = '"+cyzid+"' order by  nlssort(m.ghmc,'NLS_SORT=SCHINESE_STROKE_M')";
		}else if(zzzid != null && !"".equals(zzzid)){
			
			sql = "select  m.manifoldid, m.ghmc from pc_cd_manifold_t m  "
				+" left join  pc_cd_org_t o on o.org_id=m.org_id"
				+" left join pc_cd_org_t o1 on o.pid=o1.org_id where o1.org_id = '"+cyzid+"' order by  nlssort(m.ghmc,'NLS_SORT=SCHINESE_STROKE_M')";
				
		}if(teamid != null && !"".equals(teamid)){
			sql = "select m.manifoldid, m.ghmc  from pc_cd_manifold_t m  where m.team_id = '"+teamid+"' order by  nlssort(m.ghmc,'NLS_SORT=SCHINESE_STROKE_M')";
		}else{
			sql = "select  m.manifoldid, m.ghmc from pc_cd_manifold_t m left join pc_cd_org_t o on o.org_id = m.org_id order by  nlssort(m.ghmc,'NLS_SORT=SCHINESE_STROKE_M')";
			
		}
		List<Object[]> objList = searchQueryAllDao.queryGroupInfo(sql);
		if(objList !=null && objList.size()>0){
			jArr = new JSONArray();
			for (Object[] entry : objList) {
				obj = new JSONObject();
				obj.put("text", entry[1]);
				obj.put("id", entry[0]);
				jArr.add(obj);
			}
			return jArr;
		}
		jArr = new JSONArray();
		obj= new JSONObject();
		obj.put("text", "");
		obj.put("id", "");
		jArr.add(obj);
		return jArr;
	
	}

	@Override
	public JSONObject searchOilChangeData(String oilid) throws Exception {
		//选择采油站， 关联 ，注转战，班组  ，管汇
		String StationSql ="";
		String GroupSql ="";
		String ManiSql ="";
		//String WellSql = "";
		if(oilid.equals("全部")){
			StationSql ="select a.blockstationid,a.blockstation_name from  pc_cd_station_t  a ";
			GroupSql   ="select  b.org_id,b.team  from pc_cd_team_t b ";
			ManiSql    ="select c.manifoldid,c.ghdm from  pc_cd_manifold_t  c  ";
			//WellSql="";
		}else{
		 StationSql =" select o.org_id,o.structurename  from  pc_cd_station_t t"
						 +"  left join  pc_cd_org_t o on t.org_id = o.org_id"
						 +"  left join pc_cd_org_t o1 on o1.org_id = o.pid  where o1.structurename='"+oilid+"' and o.structuretype='7'" 
						 +"  order by  nlssort(o.structurename,'NLS_SORT=SCHINESE_STROKE_M')";
		
		 GroupSql = "select o.org_id,o.structurename  from  pc_cd_team_t t "
						+"  left join  pc_cd_org_t o on t.org_id = o.org_id"
						+"  left join pc_cd_org_t o1 on o1.org_id = o.pid  where o1.structurename='"+oilid+"' and o.structuretype='15'" 
						+"  order by  nlssort(o.structurename,'NLS_SORT=SCHINESE_STROKE_M')";
		 ManiSql = "select o.org_id,o.structurename  from  pc_cd_manifold_t t "
						+"  left join  pc_cd_org_t o on t.org_id = o.org_id"
						+"  left join pc_cd_org_t o1 on o1.org_id = o.pid  "
						+"  left join pc_cd_org_t o2 on o2.org_id = o1.pid  where o2.structurename='"+oilid+"' and o.structuretype='8' "
						+"  order by  nlssort(o.structurename,'NLS_SORT=SCHINESE_STROKE_M')";
		// WellSql="";
		}
		List<Object[]> StationDataSet = searchQueryAllDao.queryGroupInfo(StationSql);
		List<Object[]> GroupDataSet = searchQueryAllDao.queryGroupInfo(GroupSql);
		List<Object[]> ManiDataSet = searchQueryAllDao.queryGroupInfo(ManiSql);
		 JSONArray  StationArr = null;
		 JSONArray  GroupArr = null;
		 JSONArray  ManiArr = null;
		 JSONObject obj = null;
		// JSONArray arrAll = null;
		if(StationDataSet !=null && StationDataSet.size()>0){
			StationArr = new JSONArray();
			for (Object[] entry : StationDataSet) {
				obj = new JSONObject();
				obj.put("text", entry[1]);
				obj.put("id", entry[0]);
				StationArr.add(obj);
			}
		}
		if(GroupDataSet !=null && GroupDataSet.size()>0){
			GroupArr = new JSONArray();
			for (Object[] entry : GroupDataSet) {
				obj = new JSONObject();
				obj.put("text", entry[1]);
				obj.put("id", entry[0]);
				GroupArr.add(obj);
			}
				}
		if(ManiDataSet !=null && ManiDataSet.size()>0){
			ManiArr = new JSONArray();
			for (Object[] entry : ManiDataSet) {
				obj = new JSONObject();
				obj.put("text", entry[1]);
				obj.put("id", entry[0]);
				ManiArr.add(obj);
			}
		}
		//arrAll = new JSONArray();
		obj = new JSONObject();
		obj.put("StationText", StationArr);
		obj.put("GroupText", GroupArr);
		obj.put("ManiText", ManiArr);
		return obj;
	}

	@Override
	public JSONArray searchAllGroup() throws Exception {
		JSONArray   Arr = null;
		JSONObject  obj = null;
		String  sql ="select  a.org_id,a.team  from  pc_cd_team_t a  order by  nlssort(a.team ,'NLS_SORT=SCHINESE_STROKE_M')";  
		List<Object[]> groupData = searchQueryAllDao.queryGroupInfo(sql);
		if(groupData !=null && groupData.size()>0){
			Arr  = new JSONArray();
			for (Object[] entry : groupData) {
				obj = new JSONObject();
				obj.put("text", entry[1]);
				obj.put("id", entry[0]);
				Arr.add(obj);
			}
			return Arr;
		}
		Arr = new JSONArray();
		obj = new JSONObject();
		obj = new JSONObject();
		obj.put("text", "");
		obj.put("id", "");
		Arr.add(obj);
		return Arr;
	}

	@Override
	public JSONArray searchAllSatation(String paramArg) throws Exception {
		JSONArray Arr = null;
		JSONObject obj = null;
		String sql ="";
		//if( paramArg != null  && !"".endsWith("paramArg")){
			//sql="";
		//}else{
			sql="select  b.org_id,b.blockstation_name  from  pc_cd_station_t b  order by  nlssort(blockstation_name ,'NLS_SORT=SCHINESE_RADICAL_M')";
		//}
		List<Object[]> StationData = searchQueryAllDao.queryGroupInfo(sql);
		if(StationData !=null && StationData.size()>0){
			Arr  = new JSONArray();
			for (Object[] entry : StationData) {
				obj = new JSONObject();
				obj.put("text", entry[1]);
				obj.put("id", entry[0]);
				Arr.add(obj);
			}
			return Arr;
		}
		Arr = new JSONArray();
		obj = new JSONObject();
		obj = new JSONObject();
		obj.put("text", "");
		obj.put("id", "");
		Arr.add(obj);
		return Arr;
	}

	@Override
	public JSONArray searchAllMani(String groupMani,String stationMani, String ManiWell ,String nullParam) throws Exception {
		JSONArray jArr =null;
		JSONObject obj = null;
		String sql ="";
		if(groupMani !=null && !"".equals(groupMani)){
			sql = "select m.manifoldid, m.ghmc  from pc_cd_manifold_t m  where m.team_id = '"+groupMani+"' order by  nlssort(m.ghmc,'NLS_SORT=SCHINESE_STROKE_M')";
		//班组↓管汇
		}else if(stationMani !=null && !"".equals(stationMani)){
			sql = "select  m.manifoldid, m.ghmc from pc_cd_manifold_t m  "
				+" left join  pc_cd_org_t o on o.org_id=m.org_id"
				+" left join pc_cd_org_t o1 on o.pid=o1.org_id where o1.org_id = '"+stationMani+"' order by  nlssort(m.ghmc,'NLS_SORT=SCHINESE_STROKE_M')";
			//注转战 ↓管汇
		}else{
			sql = "select  m.manifoldid, m.ghmc from pc_cd_manifold_t m left join pc_cd_org_t o on o.org_id = m.org_id order by  nlssort(m.ghmc,'NLS_SORT=SCHINESE_STROKE_M')";
				//所有管汇
		}
		List<Object[]> objList = searchQueryAllDao.queryGroupInfo(sql);
		if(objList !=null && objList.size()>0){
			jArr = new JSONArray();
			for (Object[] entry : objList) {
				obj = new JSONObject();
				obj.put("text", entry[1]);
				obj.put("id", entry[0]);
				jArr.add(obj);
			}
			return jArr;
		}
		jArr = new JSONArray();
		obj= new JSONObject();
		obj.put("text", "");
		obj.put("id", "");
		jArr.add(obj);
		return jArr;
	}

	@Override
	public JSONObject searchConnect(String oilUnitID, String groupTeamID,String stationID, String maniID, String wellNameID) throws Exception{
		String sql ="";
		if(oilUnitID !=null && !"".equals(oilUnitID)){
			sql="";
		}else{
			sql= "select  z.org_id,z.waterflooding_name from  pc_cd_waterflooding_well_t  z order by  nlssort(z.waterflooding_name,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		return null;
	}

	@Override
	public JSONObject searchOilGroupWaterInjec(String oilName, String groupName,String injeName) throws Exception {
		String sql ="";
		JSONObject obj = null;
		JSONArray  arr = null;
		String groupSql="";
		String injectSql="";
		String wellSql="";
		
		JSONArray groupArr = null;
		JSONArray injectArr = null;
		JSONArray wellArr = null;
		if(oilName !=null && !"".equals(oilName)){
			if(oilName.equals("全部")){
				 groupSql="select a.org_id,a.team  from pc_cd_team_t  a left join   pc_cd_org_t  o on  o.org_id = a.org_id  left join pc_cd_org_t o1 on o1.org_id = o.pid";
			}else{
			 groupSql="select a.org_id,a.team  from pc_cd_team_t  a left join   pc_cd_org_t  o on  o.org_id = a.org_id  left join pc_cd_org_t o1 on o1.org_id = o.pid   where  o1.structurename='"+oilName+"'    order by  nlssort(a.team,'NLS_SORT=SCHINESE_STROKE_M')";
			}
			 List<Object[]> groupData =  searchQueryAllDao.queryGroupInfo(groupSql);
				if(groupData !=null && groupData.size()>0){
					groupArr = new JSONArray();
					for (Object[] entry : groupData) {
						obj = new JSONObject();
						obj.put("text", entry[1]);
						obj.put("id", entry[0]);
						groupArr.add(obj);
					}
				}
		} 
		if(groupName !=null && !"".equals(groupName)){
			 injectSql="select z.org_id,z.water_injectiontopr_name from  pc_cd_water_injectiontopry_t  z  left join  pc_cd_org_t o on  o.org_id = z.org_id "+
				"left join pc_cd_org_t o1 on o1.org_id = o.pid   where o1.structurename ='"+groupName+"' " +
				"order by  nlssort(z.water_injectiontopr_name,'NLS_SORT=SCHINESE_STROKE_M')";
				List<Object[]> injectData =  searchQueryAllDao.queryGroupInfo(injectSql);
				if(injectData !=null && injectData.size()>0){
					injectArr = new JSONArray();
					for (Object[] entry : injectData) {
						obj = new JSONObject();
						obj.put("text", entry[1]);
						obj.put("id", entry[0]);
						injectArr.add(obj);
					}
				}
		
		}
		if(injeName !=null && !"".equals(injeName)){
			 wellSql="select x.org_id,x.waterflooding_name  from  pc_cd_waterflooding_well_t x left join pc_cd_org_t o on o.org_id = x.org_id"+
				" left join pc_cd_org_t o1 on o1.org_id = o.pid   where o1.structurename ='"+injeName+"' "+
				" order by  nlssort(x.waterflooding_name,'NLS_SORT=SCHINESE_STROKE_M')";
	
		List<Object[]> wellData =  searchQueryAllDao.queryGroupInfo(wellSql);
		
		if(wellData !=null && wellData.size()>0){
			wellArr = new JSONArray();
			for (Object[] entry : wellData) {
				obj = new JSONObject();
				obj.put("text", entry[1]);
				obj.put("id", entry[0]);
				wellArr.add(obj);
			}
		}
		}
		
		obj = new JSONObject();
		obj.put("groupText",groupArr);
		obj.put("injectText", injectArr);
		obj.put("wellText", wellArr);
		return obj;
	}

	@Override
	public JSONArray searchGroupWellOnchange(String teamName) throws Exception {
		JSONObject  obj = null;
		JSONArray  jArr =null;
		String sql ="";
		  sql="select z.org_id,z.water_injectiontopr_name from  pc_cd_water_injectiontopry_t  z  left join  pc_cd_org_t o on  o.org_id = z.org_id "+
			"left join pc_cd_org_t o1 on o1.org_id = o.pid   where o1.structurename ='"+teamName+"' " +
			"order by  nlssort(z.water_injectiontopr_name,'NLS_SORT=SCHINESE_STROKE_M')";
		if("".equals(teamName)){
			 sql="select z.org_id,z.water_injectiontopr_name from  pc_cd_water_injectiontopry_t  z  left join  pc_cd_org_t o on  o.org_id = z.org_id "+
			"left join pc_cd_org_t o1 on o1.org_id = o.pid ";
		}
		 List<Object[]> objList = searchQueryAllDao.queryGroupInfo(sql);
			if(objList !=null && objList.size()>0){
				jArr = new JSONArray();
				for (Object[] entry : objList) {
					obj = new JSONObject();
					obj.put("text", entry[1]);
					obj.put("id", entry[0]);
					jArr.add(obj);
				}
				return jArr;
			}
			jArr = new JSONArray();
			obj= new JSONObject();
			obj.put("text", "");
			obj.put("id", "");
			jArr.add(obj);
			return jArr;
	}

	@Override
	public JSONArray searchOnChangeManiWell(String groupName,String stationName, String maniName,String arg) throws Exception {
		String sql ="";
		JSONObject obj = null;
		JSONArray jArr =null;
	if(arg.equals("manifold")){
		sql ="select o.org_id,o.structurename  from   pc_cd_org_t  o "+
        " left join pc_cd_org_t o1 on o1.org_id = o.pid  where o1.structurename='"+maniName+"' "+
        " order by  nlssort(o.structurename,'NLS_SORT=SCHINESE_STROKE_M') ";
	}else{
		if(maniName !=null && !"".equals(maniName)){
			sql ="select t.org_id,t.well_name  from pc_cd_thinoil_well_t t  left join pc_cd_org_t o on t.org_id = o.org_id"+
				" left join pc_cd_org_t o1 on o1.org_id = o.pid  where o1.structurename='"+maniName+"' "+
				" order by  nlssort(t.well_name,'NLS_SORT=SCHINESE_STROKE_M')";
		}else{
			sql = "select t.org_id,t.well_name  from pc_cd_thinoil_well_t t  left join pc_cd_org_t o on t.org_id = o.org_id order by  nlssort(t.well_name,'NLS_SORT=SCHINESE_STROKE_M')";
		}
	}
		 List<Object[]> objList = searchQueryAllDao.queryGroupInfo(sql);
			if(objList !=null && objList.size()>0){
				jArr = new JSONArray();
				for (Object[] entry : objList) {
					obj = new JSONObject();
					obj.put("text", entry[1]);
					obj.put("id", entry[0]);
					jArr.add(obj);
				}
				return jArr;
			}
			jArr = new JSONArray();
			obj= new JSONObject();
			obj.put("text", "");
			obj.put("id", "");
			jArr.add(obj);
			return jArr;
	}

	@Override
	public JSONArray searchChangeGroupOnWell(String groupName) throws Exception {
		JSONObject obj =null;
		JSONArray  jArr = null;
		String sql ="";
		if(groupName !=null && !"".equals(groupName)){
			sql ="select   t.org_id,t.well_name  from  pc_cd_thinoil_well_t  t  left join pc_cd_org_t o  on o.org_id = t.org_id"+
					" left join pc_cd_org_t o1 on o1.org_id = o.pid  left join pc_cd_manifold_t  m on o1.org_id = m.org_id"+
					" left join pc_cd_team_t  n on m.team_id = n.org_id where  n.team ='"+groupName+"'"+
					" order by  nlssort(t.well_name,'NLS_SORT=SCHINESE_STROKE_M')";
		}else{
			sql ="select   t.org_id,t.well_name  from  pc_cd_thinoil_well_t  t  left join pc_cd_org_t o  on o.org_id = t.org_id"+
					"  order by  nlssort(t.well_name,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		 List<Object[]> objList = searchQueryAllDao.queryGroupInfo(sql);
		 
			if(objList !=null && objList.size()>0){
				jArr = new JSONArray();
				for (Object[] entry : objList) {
					obj = new JSONObject();
					obj.put("text", entry[1]);
					obj.put("id", entry[0]);
					jArr.add(obj);
				}
				return jArr;
			}
			jArr = new JSONArray();
			obj= new JSONObject();
			obj.put("text", "");
			obj.put("id", "");
			jArr.add(obj);
			return jArr;
	}

	@Override
	public JSONArray searchOnChangeManiRuleWell(String maniName)throws Exception {

		JSONObject obj = null;
		JSONArray jArr =null;
	
		String 	sql = " select  t.org_id, t.well_name  from pc_cd_rule_well_t t  left join  pc_cd_org_t o  on t.org_id = o.org_id  left join"+
    "  pc_cd_org_t o1  on o1.org_id = o.pid    where o1.structurename='"+maniName+"'   order by  nlssort(t.well_name, 'NLS_SORT=SCHINESE_STROKE_M')";
		 List<Object[]> objList = searchQueryAllDao.queryGroupInfo(sql);
			if(objList !=null && objList.size()>0){
				jArr = new JSONArray();
				for (Object[] entry : objList) {
					obj = new JSONObject();
					obj.put("text", entry[1]);
					obj.put("id", entry[0]);
					jArr.add(obj);
				}
				return jArr;
			}
			jArr = new JSONArray();
			obj= new JSONObject();
			obj.put("text", "");
			obj.put("id", "");
			jArr.add(obj);
			return jArr;
	
	}

	@Override
	public JSONArray searchCYZTEAMJH(String CYZ,String TEAM,String arg) throws Exception {
		JSONObject obj = null;
		JSONArray jArr =null;
		String sql = "";
		if("CLIQUE".equals(arg)){
			sql = "select a.org_id,a.CLIQUE_POOL_NAME from pc_cd_CLIQUE_POOL_v a where 1=1 ";
			if(CYZ != null && !"".equals(CYZ)){
				sql += " and a.OIL_STATION_id = '"+CYZ+"'  order by nlssort(a.CLIQUE_POOL_NAME,'NLS_SORT=SCHINESE_STROKE_M')";
			}else if(TEAM != null && !"".equals(TEAM)){
				sql += " and a.STATION_ID = '"+TEAM+"'  order by nlssort(a.CLIQUE_POOL_NAME,'NLS_SORT=SCHINESE_STROKE_M')";
			}
		}else if("GTDT".equals(arg)){
			
			sql = "SELECT v.org_id,v.structurename FROM pc_cd_celltree_v v WHERE 1=1 AND v.structuretype = 9  ";
			if(CYZ != null && !"".equals(CYZ) && !"1".equals(CYZ) && !"0".equals(CYZ)){
				sql = "select a.org_id,a.structurename from ( "+
				 		"	select * from pc_cd_celltree_v  "+
				 		 "	start with  org_id='"+CYZ+"' "+
				 		 "	connect by prior org_id = pid ) a where a.structuretype = 9 order by nlssort(a.structurename,'NLS_SORT=SCHINESE_STROKE_M')";
				
			}else if(TEAM != null && !"".equals(TEAM)){
				sql = "select a.org_id,a.structurename from ( "+
		 		"	select * from pc_cd_celltree_v  "+
		 		 "	start with  org_id='"+TEAM+"' "+
		 		 "	connect by prior org_id = pid ) a where a.structuretype = 9 order by nlssort(a.structurename,'NLS_SORT=SCHINESE_STROKE_M')";
				
			}
			
		}else{
			sql = "select v.org_id,v.well_name from pc_cd_orgall_v v where 1=1  ";
			if(CYZ != null && !"".equals(CYZ) && !"1".equals(CYZ) && !"0".equals(CYZ)){
				sql += " and v.oil_satation_id = '"+CYZ+"'  order by  nlssort(v.oil_station_name, 'NLS_SORT=SCHINESE_STROKE_M') ";
			}else if(TEAM != null && !"".equals(TEAM)){
				sql += " and v.team_satation_id = '"+TEAM+"'  order by  nlssort(v.team_station_name, 'NLS_SORT=SCHINESE_STROKE_M') ";
			}
			
		}
		
		
		
		List<Object[]> objList = searchQueryAllDao.queryGroupInfo(sql);
		if(objList !=null && objList.size()>0){
			jArr = new JSONArray();
			for (Object[] entry : objList) {
				obj = new JSONObject();
				obj.put("text", entry[1]);
				obj.put("id", entry[0]);
				jArr.add(obj);
			}
			return jArr;
		}
		jArr = new JSONArray();
		obj= new JSONObject();
		obj.put("text", "");
		obj.put("id", "");
		jArr.add(obj);
		return jArr;
	}

	@Override
	public JSONObject searchALLCYZ(String arg) throws Exception {
		JSONObject reobj = new JSONObject();
		JSONObject obj = null;
		JSONArray jArr =null;
		String sql = "";
		String teamsql = "";
		String wellsql = "";
		if("CLIQUE".equals(arg)){
			sql = "select a.OIL_STATION_id,b.structurename from (select distinct(OIL_STATION_id) from pc_cd_CLIQUE_POOL_v)a left join pc_cd_org_t b on a.OIL_STATION_id = b.org_id order by nlssort(b.STRUCTURENAME,'NLS_SORT=SCHINESE_STROKE_M')";
			teamsql = "select a.STATION_id,b.structurename from (select distinct(STATION_id) from pc_cd_CLIQUE_POOL_v)a left join pc_cd_org_t b on a.STATION_id = b.org_id order by nlssort(b.STRUCTURENAME,'NLS_SORT=SCHINESE_STROKE_M')";
			wellsql = "select a.org_id,a.CLIQUE_POOL_NAME from pc_cd_CLIQUE_POOL_v a order by nlssort(a.CLIQUE_POOL_NAME,'NLS_SORT=SCHINESE_STROKE_M')";
		}else if("GTDT".equals(arg)){
			
			//sql = "SELECT V.org_id,V.structurename FROM pc_cd_celltree_v V WHERE V.structuretype = 4 order by nlssort(V.structurename,'NLS_SORT=SCHINESE_STROKE_M')";
			sql="select r.ORG_ID,r.STRUCTURENAME from PC_CD_MANIFOLDTREE_V r where  r.structurename in('SAGD采油一站','SAGD采油二站')  order by nlssort(r.STRUCTURENAME,'NLS_SORT=SCHINESE_STROKE_M')";			
			teamsql = "SELECT V.org_id,V.structurename FROM pc_cd_celltree_v V WHERE V.structuretype = 7 order by nlssort(V.structurename,'NLS_SORT=SCHINESE_STROKE_M')";			
			//teamsql = "select s.org_id,s.blockstation_name from PC_CD_STATION_T s  order by nlssort(s.blockstation_name,'NLS_SORT=SCHINESE_STROKE_M')";
			wellsql = "SELECT V.org_id,V.structurename FROM pc_cd_celltree_v V WHERE V.structuretype = 9 order by nlssort(V.structurename,'NLS_SORT=SCHINESE_STROKE_M')";
			
		//	ghsql = "SELECT ";
		}else{
			sql = " select v.oil_satation_id ,o.structurename from (select   distinct(oil_satation_id) from pc_cd_orgall_v )v left join pc_cd_org_t o on o.org_id = v.oil_satation_id order by  nlssort(o.structurename, 'NLS_SORT=SCHINESE_STROKE_M')";
			teamsql = " select v.team_satation_id ,o.structurename from (select distinct(team_satation_id) from pc_cd_orgall_v where 1=1 )v left join pc_cd_org_t o on o.org_id = v.team_satation_id order by  nlssort(o.structurename, 'NLS_SORT=SCHINESE_STROKE_M') ";
			wellsql = "select v.org_id,v.well_name from pc_cd_orgall_v v where 1=1  order by  nlssort(v.team_station_name, 'NLS_SORT=SCHINESE_STROKE_M') ";
		}
		
		List<Object[]> objList = searchQueryAllDao.queryGroupInfo(sql);
		if(objList !=null && objList.size()>0){
			jArr = new JSONArray();
			obj = new JSONObject();
			obj.put("text", "全部");
			obj.put("id", "1");
			jArr.add(obj);
			for (Object[] entry : objList) {
				if(entry[0] != null && !"".equals(CommonsUtil.getClearNullData(entry[0]))){
					obj = new JSONObject();
					obj.put("text", entry[1]);
					obj.put("id", entry[0]);
					jArr.add(obj);
				}
				
			}
		}
		
		reobj.put("oilStationtext", jArr);
		objList = searchQueryAllDao.queryGroupInfo(teamsql);
		jArr = new JSONArray();
		if(objList !=null && objList.size()>0){
			for (Object[] entry : objList) {
				if(entry[0] != null && !"".equals(CommonsUtil.getClearNullData(entry[0]))){
				obj = new JSONObject();
				obj.put("text", entry[1]);
				obj.put("id", entry[0]);
				jArr.add(obj);
				}
			}
		}
		
		reobj.put("teamStationtext", jArr);
		objList = searchQueryAllDao.queryGroupInfo(wellsql);
		jArr = new JSONArray();
		if(objList !=null && objList.size()>0){
			for (Object[] entry : objList) {
				if(entry[0] != null && !"".equals(CommonsUtil.getClearNullData(entry[0]))){
				obj = new JSONObject();
				obj.put("text", entry[1]);
				obj.put("id", entry[0]);
				jArr.add(obj);
				}
			}
		}
		reobj.put("wellstationtext", jArr);
		return reobj;
	}

	@Override
	public JSONArray searchTeam(String CYZ,String arg) throws Exception {
		JSONObject obj = null;
		JSONArray jArr =null;
		String sql = "";
		if("CLIQUE".equals(arg)){
			sql ="select a.STATION_id,b.structurename from (select distinct(STATION_id) from pc_cd_CLIQUE_POOL_v where 1=1 and ";
			if(CYZ != null && !"".equals(CYZ) && !"0".equals(CYZ) && !"1".equals(CYZ)){		
				sql += " OIL_STATION_id='"+CYZ+"'" ;
			}
			sql +=	")a left join pc_cd_org_t b on a.STATION_id = b.org_id order by nlssort(b.STRUCTURENAME,'NLS_SORT=SCHINESE_STROKE_M')";
			
		}else if("GTDT".equals(arg)){
			sql ="SELECT V.org_id,V.structurename FROM pc_cd_celltree_v V WHERE 1=1 and V.structuretype = 7 ";
			if(CYZ != null && !"".equals(CYZ) && !"0".equals(CYZ)&& !"1".equals(CYZ)){		
				sql = "SELECT V.org_id,V.structurename FROM pc_cd_celltree_v V WHERE 1=1 and V.pid ='"+CYZ+"'" ;
			}
			sql +=	" order by nlssort(V.structurename,'NLS_SORT=SCHINESE_STROKE_M')";
			
		}else{
			sql = " select v.team_satation_id ,o.structurename from (select distinct(team_satation_id) from pc_cd_orgall_v where 1=1 " ;
			if(CYZ != null && !"".equals(CYZ) && !"0".equals(CYZ) && !"1".equals(CYZ)){
				sql += " and oil_satation_id ='"+CYZ+"' " ;
			}		
			sql += " )v left join pc_cd_org_t o on o.org_id = v.team_satation_id order by  nlssort(o.structurename, 'NLS_SORT=SCHINESE_STROKE_M')";
			
		}
		
		List<Object[]> objList = searchQueryAllDao.queryGroupInfo(sql);
		if(objList !=null && objList.size()>0){
			jArr = new JSONArray();
			for (Object[] entry : objList) {
				obj = new JSONObject();
				obj.put("text", entry[1]);
				obj.put("id", entry[0]);
				jArr.add(obj);
			}
			return jArr;
		}
		jArr = new JSONArray();
		obj= new JSONObject();
		obj.put("text", "");
		obj.put("id", "");
		jArr.add(obj);
		return jArr;
	}

	@Override
	public JSONArray searchUserWell(User user) throws Exception {
		JSONObject obj = null;
		JSONArray jArr =null;
		String depType = user.getDepType();
		String sql = "select v.org_id,v.well_name from pc_cd_orgall_v v where 1=1  ";
		if("3".equals(depType)){
			sql += " and v.factory_id = '"+user.getDepid()+"'  order by  nlssort(v.well_name, 'NLS_SORT=SCHINESE_STROKE_M') ";
		}else if("4".equals(depType)){
			sql += " and v.oil_satation_id = '"+user.getDepid()+"'  order by  nlssort(v.well_name, 'NLS_SORT=SCHINESE_STROKE_M') ";
		}else if("15".equals(depType)){
			sql += " and v.team_satation_id = '"+user.getDepid()+"'  order by  nlssort(v.well_name, 'NLS_SORT=SCHINESE_STROKE_M') ";
		}
		
		List<Object[]> objList = searchQueryAllDao.queryGroupInfo(sql);
		if(objList !=null && objList.size()>0){
			jArr = new JSONArray();
			for (Object[] entry : objList) {
				obj = new JSONObject();
				obj.put("text", entry[1]);
				obj.put("id", entry[0]);
				jArr.add(obj);
			}
			return jArr;
		}
		jArr = new JSONArray();
		obj= new JSONObject();
		obj.put("text", "");
		obj.put("id", "");
		jArr.add(obj);
		return jArr;
	}

	@Override
	public JSONObject searchALLZKCJD(String arg) throws Exception {

		JSONObject reobj = new JSONObject();
		JSONObject obj = null;
		JSONArray jArr =null;
		String sql1 = "";
		String sql2 = "";
		String sql3 = "";
		if("ZKXTXX".equals(arg)){
			sql1 = "select t.object_code,t.object_type_name from PC_CD_OBJECT_TYPE_T t  order by nlssort(t.object_type_name,'NLS_SORT=SCHINESE_STROKE_M')";
			sql2 = "select  b.factory_uc,b.factory_nf  from pc_cd_factory_code_t b  order by nlssort(b.factory_nf ,'NLS_SORT=SCHINESE_STROKE_M')";
			sql3 = "select  b.scada_node,b.remark  from pc_cd_server_node_t b  order by nlssort(b.remark ,'NLS_SORT=SCHINESE_STROKE_M')";
		}else{
			
		}
		
		List<Object[]> objList = searchQueryAllDao.queryGroupInfo(sql1);
		if(objList !=null && objList.size()>0){
			jArr = new JSONArray();
			obj = new JSONObject();
			for (Object[] entry : objList) {
				obj = new JSONObject();
				obj.put("text", entry[1]);
				obj.put("id", entry[0]);
				jArr.add(obj);
			}
		}
		
		reobj.put("INITDATA1", jArr);
		objList = searchQueryAllDao.queryGroupInfo(sql2);
		jArr = new JSONArray();
		if(objList !=null && objList.size()>0){
			for (Object[] entry : objList) {
				obj = new JSONObject();
				obj.put("text", entry[1]);
				obj.put("id", entry[0]);
				jArr.add(obj);
			}
		}
		
		reobj.put("INITDATA2", jArr);
		objList = searchQueryAllDao.queryGroupInfo(sql3);
		jArr = new JSONArray();
		if(objList !=null && objList.size()>0){
			for (Object[] entry : objList) {
				obj = new JSONObject();
				obj.put("text", entry[1]);
				obj.put("id", entry[0]);
				jArr.add(obj);
			}
		}
		reobj.put("INITDATA3", jArr);
		return reobj;
	
	} 
}
