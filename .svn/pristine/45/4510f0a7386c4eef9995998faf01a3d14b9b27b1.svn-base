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
import com.echo.dao.WaterInjectionDao;
import com.echo.dto.PcCdWaterInjectiontopryT;
import com.echo.service.GasWellService;
import com.echo.service.RuleWellService;
import com.echo.service.SagdService;
import com.echo.service.ThinOilWellService;
import com.echo.service.WaterInjectionService;

public class WaterInjectionServiceImpl implements WaterInjectionService{
	private WaterInjectionDao waterInjectionDao;
	
	public void setWaterInjectionDao(WaterInjectionDao waterInjectionDao) {
		this.waterInjectionDao = waterInjectionDao;
	}
	public JSONObject searchwaterIT(String qk,String zh,String jh,String name,String JRBZ1,String dyearC,String groupTeam ,int pageNo,String sort,String order,int rowsPerpage) {
		JSONArray jsonArr = null;
		JSONObject tjo = null;
		String cloums = "select   ";
		String formTable = " from pc_cd_water_inject_v  where 1=1 ";
		String totalNum = "select count(*) ";
		if(!qk.equals("")&&qk!=null&&!qk.equals("undefined")&!qk.equals("全部")){
			formTable=formTable+" and OILSTATIONNAME='"+qk+"'";
		}
		
		if(!name.equals("")&&name!=null&&!name.equals("undefined")){
			formTable=formTable+" and WATER_INJECTIONTOPR_NAME='"+name+"'";
		}
		
		if(!zh.equals("")&&zh!=null&&!zh.equals("undefined")){
			formTable=formTable+" and AREABLOCK='"+zh+"'";
		}
		if(!JRBZ1.equals("") && JRBZ1!=null && !JRBZ1.equals("undefined") && !JRBZ1.equals("全部")){
			formTable=formTable+" and jrbz='"+JRBZ1+"'";
		}
	
		if(!dyearC.equals("") && dyearC!=null && !dyearC.equals("undefined")){
			formTable=formTable+" and DYEAR='"+dyearC+"'";
		}
		if(!groupTeam.equals("") && groupTeam!=null && !groupTeam.equals("undefined")){
			formTable=formTable+" and BLOCKSTATIONNAME='"+groupTeam+"'";
		}
		String[] cloumnsName = {"WATER_INJECTIONTOPRID","ORG_ID","QKID","A2ID","WATER_INJECTIONTOPR_NAME","COMMISSIONING_DATE","DYEAR","STATUS_VALUE",
				"RLAST_OPER","RLAST_ODATE","REMARK","OILSTATIONNAME","AREABLOCK","CODE","BLOCKSTATIONNAME","GH_ID","PROD_SNS","JRBZ","LJJDID","LJJD_S"};
		String kk="WATER_INJECTIONTOPRID";
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
			total = waterInjectionDao.getCounts(totalNum);
		}
		//排序
		if(!"".equals(sort) && !"water_injectiontopr_name".equals(sort)){
			for (String cloumn : cloumnsName) {
				if(cloumn.equals(sort)){
					boilersInfo +=" order by " + cloumn + " " + order;
					break;
				}
				
			}
		}
		else {
			boilersInfo +=" order by nlssort(water_injectiontopr_name, 'NLS_SORT=SCHINESE_STROKE_M') ";
		}
		//计算分页
		PageControl control = new PageControl();
		//当前页
		control.setInt_num(rowsPerpage);
		control.init(pageNo, total);
		//开始条数
		int start = control.getStart()-1;
		
		List<Object[]> lo = waterInjectionDao.searchwaterIT(boilersInfo,start,rowsPerpage);
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
		sqls[0] = " DELETE from pc_cd_water_injectiontopry_t d where d.water_injectiontoprid = '"+arg+"'";
		sqls[1] = " DELETE from pc_cd_org_t t where t.org_id = '"+orgid+"'";
		
		return waterInjectionDao.removeStationInfo(sqls);
	}
	public JSONArray queryWellInfo(String arg) {
		JSONArray jsonArr = null;
		JSONObject jsobj = null;
		 String sql ="select a.org_id,a.team  from pc_cd_team_t  a left join   pc_cd_org_t  o on  o.org_id = a.org_id  left join pc_cd_org_t o1 on o1.org_id = o.pid   where  o1.structurename='"+arg+"'    order by  nlssort(a.team,'NLS_SORT=SCHINESE_STROKE_M')";
		if ( arg.equals("全部")) {//全部的锅炉
			sql="select a.org_id,a.team  from pc_cd_team_t  a left join   pc_cd_org_t  o on  o.org_id = a.org_id  left join pc_cd_org_t o1 on o1.org_id = o.pid";
		}
		List<Object[]> dataSet = waterInjectionDao.queryInfo(sql);
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
		jsonArr = new JSONArray();
		jsobj.put("text", "");
		jsobj.put("id","");
		
		jsonArr.add(jsobj);
		return jsonArr;
	}
	public List<PcCdWaterInjectiontopryT> getInjectiontoprys(String id,
			String name) throws Exception {
		List<PcCdWaterInjectiontopryT> injectiontoprys = null;
		
		String hql ="FROM PcCdWaterInjectiontopryT t WHERE 1=1 ";
		if(id != null && !"".equals(id)){
			
			hql += " and t.waterInjectiontoprid = '"+id+"'";
		}
		if(name != null && !"".equals(name)){
			hql += " and t.waterInjectiontoprName = '"+name+"'";
			
		}
		
		injectiontoprys = waterInjectionDao.getInjectiontoprys(hql);

		return injectiontoprys;
	}
	public boolean addINT(PcCdWaterInjectiontopryT pcCdWaterInjectiontopryT)
			throws Exception {
		return waterInjectionDao.saveINT(pcCdWaterInjectiontopryT);
	}
	public boolean updateINT(PcCdWaterInjectiontopryT pcCdWaterInjectiontopryT)
			throws Exception {
		return waterInjectionDao.updateINT(pcCdWaterInjectiontopryT);
	}
	@Override
	public HashMap<String, Object> searchOnExport(String stationNumber,String areablock1,String rulewellname1,String JRBZ1,String dyearC,String groupTeam,String totalNumFlag)throws Exception {


		JSONArray jsonArr = null;
		JSONObject tjo = null;
		HashMap<String,Object> map = new HashMap<String, Object>();
		String cloums = "select   ";
		String formTable = " from pc_cd_water_inject_v  where 1=1 ";
		String totalNum = "select count(*) ";
		
		if(!stationNumber.equals("")&&stationNumber!=null&&!stationNumber.equals("undefined")&!stationNumber.equals("全部")){
			formTable=formTable+" and OILSTATIONNAME='"+stationNumber+"'";
		}
		
		if(!rulewellname1.equals("")&&rulewellname1!=null&&!rulewellname1.equals("undefined")){
			formTable=formTable+" and WATER_INJECTIONTOPR_NAME='"+rulewellname1+"'";
		}
		
		if(!areablock1.equals("")&&areablock1!=null&&!areablock1.equals("undefined")){
			formTable=formTable+" and AREABLOCK='"+areablock1+"'";
		}
		if(!JRBZ1.equals("") && JRBZ1!=null && !JRBZ1.equals("undefined") && !JRBZ1.equals("全部")){
			formTable=formTable+" and jrbz='"+JRBZ1+"'";
		}
		if(!dyearC.equals("") && dyearC!=null && !dyearC.equals("undefined")){
			formTable=formTable+" and DYEAR='"+dyearC+"'";
		}
		if(!groupTeam.equals("") && groupTeam!=null && !groupTeam.equals("undefined")){
			formTable=formTable+" and BLOCKSTATIONNAME='"+groupTeam+"'";
		}
		String[] cloumnsName = {"WATER_INJECTIONTOPR_NAME","OILSTATIONNAME","BLOCKSTATIONNAME","AREABLOCK","CODE","PROD_SNS","COMMISSIONING_DATE","DYEAR",
				"JRBZ","LJJD_S","REMARK"};
		String kk="WATER_INJECTIONTOPR_NAME";
		for(int m=1;m<cloumnsName.length;m++){
			if("WATER_INJECTIONTOPR_NAME".equals(cloumnsName[m])){
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
			total = waterInjectionDao.getCounts(totalNum);
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
			lo = waterInjectionDao.searchData(thinOilWellRD);
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
