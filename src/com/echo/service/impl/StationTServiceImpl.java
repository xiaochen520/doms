package com.echo.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.beans.PageControl;
import com.echo.dao.PageDao;
import com.echo.dao.StationTDao;
import com.echo.dto.PcCdOrgT;
import com.echo.dto.PcCdRuleWellT;
import com.echo.dto.PcCdStationT;
import com.echo.service.StationTService;

public class StationTServiceImpl implements StationTService{
	private StationTDao stationTDao;
	private PageDao pageDao;
	public StationTDao getStationTDao() {
		return stationTDao;
	}
	public void setStationTDao(StationTDao stationTDao) {
		this.stationTDao = stationTDao;
	}
	public PageDao SetPageDao() {
		return pageDao;
	}
	


	public PageDao getPageDao() {
		return pageDao;
	}
	public void setPageDao(PageDao pageDao) {
		this.pageDao = pageDao;
	}
	public JSONObject queryStationBasicInfo(String dyear,String qk,String zh,String jh,String jm,String type,String jrbz, int pageNo,String sort,String order,int rowsPerpage) throws Exception{
		JSONArray jsonArr = null;
		JSONObject tjo = null;
		String cloums = "select ";
		String formTable = " from pc_cd_station_v t where 1=1";
		String totalNum = "select count(*)";

		if(!qk.equals("")&&qk!=null&&!qk.equals("undefined")&!qk.equals("全部")){
			formTable=formTable+" and OILDRILLING_NAME='"+qk+"'";
		}
		if(!zh.equals("")&&zh!=null&&!zh.equals("undefined")){ 
			formTable=formTable+" and yxz ='"+zh+"'";
		}
		if(!jh.equals("")&&jh!=null&&!jh.equals("undefined")){
			formTable=formTable+" and qkmc='"+jh+"'";
		}
		if(!jm.equals("")&&jm!=null&&!jm.equals("undefined")){
			formTable=formTable+" and blockstation_name like '%"+jm+"%'";
		}
		if(!dyear.equals("")&&dyear!=null&&!dyear.equals("undefined")){
			formTable=formTable+" and DYEAR='"+dyear+"'";
		}
		if(!type.equals("")&&type!=null&&!type.equals("undefined")){
//			String mm="";
//			if(type.equals("注汽接转站")){
//				mm="1";
//			}else if(type.equals("注汽站")){
//				mm="2";
//			}else if(type.equals("接转站")){
//				mm="3";
//			}else if(type.equals("管汇")){
//				mm="4";
//			}
//			String mm="";
//			if(type.equals("1")){
//				mm="注汽接转站";
//			}else if(type.equals("2")){
//				mm="注汽站";
//			}else if(type.equals("3")){
//				mm="接转站";
//			}else if(type.equals("4")){
//				mm="管汇";
//			}
			formTable=formTable+" and bs_type='"+type+"'";
		}
		if(!jrbz.equals("")&&jrbz!=null&&!jrbz.equals("undefined") && !"全部".equals(jrbz)){
			formTable=formTable+" and jrbz ='"+jrbz+"'";
		}


		String[] cloumnsName = {"BLOCKSTATIONID","ORG_ID","A2ID","BLOCKSTATION_NAME","BS_TYPE","QKMC","OILDRILLING_NAME","GHS","CYGS","CYGGG","ZYBS","JRBZ","STATUS_VALUE","COMMISSIONING_DATE","RLAST_OPER","RLAST_ODATE","REMARK","GH_ID","PROD_SNS","YXZ","YXZID","QKID","LJJDID","LJJD_S","BLOCKSTATION_CODE","BOILER_QTY","STEAM_REMARK","DYEAR"};
		String kk="BLOCKSTATIONID";
		for(int m=1;m<cloumnsName.length;m++){
			if("RLAST_ODATE".equals(cloumnsName[m])){
				kk += ",to_char(RLAST_ODATE,'YYYY-MM-DD HH24:MI:SS') as RLAST_ODATE";
			}else{
				kk=kk+","+cloumnsName[m];
			}
		}
		String boilersInfo = cloums +kk+ formTable;
		//获取总条数
		int total = 0;
		if ("1".equals(totalNum)) {
			total = 1;
		} else {
			totalNum += formTable;
			total = pageDao.getCounts(totalNum);
		}
		//排序
		if(!"".equals(sort) && !"BLOCKSTATION_NAME".equals(sort)){
			for (String cloumn : cloumnsName) {
				if(cloumn.equals(sort)){
					boilersInfo +=" order by t." + cloumn + " " + order;
					break;
				}
				
			}
		}
		else {
			boilersInfo +=" order by t.BLOCKSTATION_NAME ";
		}
		//计算分页
		PageControl control = new PageControl();
		//当前页
		control.setInt_num(rowsPerpage);
		control.init(pageNo, total);
		//开始条数
		int start = control.getStart()-1;
		
		List<Object[]> lo = stationTDao.queryStationBasicInfo(boilersInfo,start,rowsPerpage);
		if (lo != null && 0 < lo.size()) {
			// 生成树节点json文档
			jsonArr = new JSONArray();
			for (Object[] o : lo) {
				tjo = new JSONObject();
//				if (o[0] != null) {
//					o[0] = String.valueOf(o[0]).substring(0,10);
//				}
				for (int i = 0; i < o.length; i++) {
					if (o[i] == null) {
						o[i] = "";
					}
					if(i==4){
						if(o[4].equals("1")){
							tjo.put(cloumnsName[i], "注汽接转站");
						}else if(o[4].equals("2")){
							tjo.put(cloumnsName[i], "注汽站");
						}else if(o[4].equals("3")){
							tjo.put(cloumnsName[i], "接转站");
						}else if(o[4].equals("4")){
							tjo.put(cloumnsName[i], "管汇");
						}else{
							tjo.put(cloumnsName[i], o[i].toString());}
					}else if(i==11){
						if(o[11].equals("1")){
							tjo.put(cloumnsName[11], "接入");
						}else if(o[11].equals("0")){
							tjo.put(cloumnsName[11], "未接入");
						}
						else {
							tjo.put(cloumnsName[11], o[11].toString());
						}
					}
						
						
						
					
					else{
						tjo.put(cloumnsName[i], o[i].toString());
					}
				}
				jsonArr.add(tjo);
			}
		}
		tjo = new JSONObject();
		tjo.put("Rows",jsonArr);
		tjo.put("Total",total);
		return tjo;
	}
	
	public JSONArray queryOilSationInfo(String arg) {
		JSONArray jsonArr = null;
		JSONObject jsobj = null;
		String sql = "select r.ORG_ID,r.STRUCTURENAME from PC_CD_STATIONTREE_V r where r.STRUCTURETYPE='4' order by nlssort(r.STRUCTURENAME,'NLS_SORT=SCHINESE_STROKE_M')";
		if ("gas".equals(arg)) {
			sql = "select r.ORG_ID,r.STRUCTURENAME from PC_CD_GAS_STATIONTREE_V r where r.STRUCTURETYPE='4' order by nlssort(r.STRUCTURENAME,'NLS_SORT=SCHINESE_STROKE_M')";
		}else if("CLIQUE_POOL".equals(arg)){
			
			sql = "select a.OIL_STATION_id,b.structurename from (select distinct(OIL_STATION_id) from pc_cd_CLIQUE_POOL_v)a left join pc_cd_org_t b on a.OIL_STATION_id = b.org_id order by nlssort(b.STRUCTURENAME,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		List<Object[]> dataSet = stationTDao.queryInfo(sql);
		if(dataSet != null && dataSet.size()>0){
			//生成树节点json文档
			jsonArr = new JSONArray();
			for(Object[] entry : dataSet){
				jsobj = new JSONObject();
				jsobj.put("text", entry[1]);
				jsobj.put("id",entry[0]);
				jsonArr.add(jsobj);
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
	
	
	public JSONArray queryAreablockInfo(String arg) {
		JSONArray jsonArr = null;
		JSONObject jsobj = null;
		// 查询选择的采油站拥有相同锅炉的所有区块
		String sql = "select distinct a.qkid,a.qkmc from pc_cd_area_info_t a left join (select s.qkid from pc_cd_station_t s left join (select *from  pc_cd_org_t t where t.pid='" + arg + "') o on s.org_id=o.org_id where s.org_id=o.org_id ) sss on a.qkid=sss.qkid where a.qkid=sss.qkid order by nlssort(a.qkmc,'NLS_SORT=SCHINESE_STROKE_M')";
		if ("".equals(arg)) {
			sql = "select distinct a.qkid,a.qkmc from pc_cd_area_info_t a left join pc_cd_station_t r on a.qkid=r.qkid where a.qkid=r.qkid order by nlssort(a.qkmc,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		if ("gas".equals(arg)) {
			sql = "select distinct a.qkid,a.qkmc from pc_cd_area_info_t a left join pc_cd_gas_station_t r on a.qkid=r.qkid where a.qkid=r.qkid order by nlssort(a.qkmc,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		if ("boilerbasicinfo".equals(arg)) {
			sql = "select distinct a.qkid,a.qkmc from pc_cd_boiler_t a left join pc_cd_gas_station_t r on a.qkid=r.qkid where a.qkid=r.qkid order by nlssort(a.qkmc,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		if ("sqdw".equals(arg)) {
			sql = "select distinct a.org_id,a.structurename from pc_cd_org_t a where a.structuretype=4 order by nlssort(a.structurename,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		if ("factory".equals(arg)) {
			sql = "select distinct a.FACTORY_UC,a.FACTORY_NF from PC_CD_FACTORY_CODE_T a  order by nlssort(a.FACTORY_NF,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		List<Object[]> dataSet = stationTDao.queryInfo(sql);
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
	public JSONArray queryStationInfo(String combinationid,String oilid,String areaid,String selecteValue,String views) {
		JSONArray jsonArr = null;
		JSONObject jsobj = null;
		//按采油站查询 注转站
		//oilid 不能等于1
//		String sql = "select distinct s.org_id,s.blockstation_name from PC_CD_STATION_T s left join (select o.* from pc_cd_org_t o left join PC_CD_OILDRILLING_STATION_T oil on o.pid=oil.org_id where o.pid='" + oilid + "') ss on s.org_id=ss.org_id where s.org_id=ss.org_id";
		String sql = "select s.blockstationid,s.blockstation_name from pc_cd_station_t s left join pc_cd_org_t o on o.org_id=s.org_id where o.pid='" + oilid + "' order by nlssort(s.blockstation_name,'NLS_SORT=SCHINESE_STROKE_M')";
		//按联合站查注转站
		if (!"".equals(combinationid)) {
			sql = "select s.blockstationid,s.blockstation_name from pc_cd_station_t s left join pc_cd_org_t o on s.org_id=o.org_id left join pc_cd_org_t o1 on o.pid=o1.org_id where o1.pid='" + combinationid + "' order by nlssort(s.blockstation_name,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		if ("".equals(selecteValue)) {//查询所有含锅炉的注转站
			sql = "select s.org_id,s.blockstation_name from PC_CD_STATION_T s  order by nlssort(s.blockstation_name,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		if ("2".equals(selecteValue) && !"".equals(areaid)) {//按区块查询 注转站
			sql = "select distinct s.org_id,s.blockstation_name from PC_CD_STATION_T s where s.qkid='" + areaid + "' order by nlssort(s.blockstation_name,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		
		//按采油站和区块查询 注转站
		if ("1".equals(selecteValue) && !"".equals(oilid) && !"".equals(areaid)) {
			sql = "select s.blockstationid,s.blockstation_name from pc_cd_station_t s left join pc_cd_org_t o on o.org_id=s.org_id left join pc_cd_area_info_t a on a.qkid=s.qkid  where o.pid='" + oilid + "' and a.qkid='" + areaid + "' order by nlssort(s.blockstation_name,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		//按联合站和区块查询 注转站
		if ("1".equals(selecteValue) && !"".equals(combinationid) && !"".equals(areaid)) {
			sql = "select s.blockstationid,s.blockstation_name from pc_cd_station_t s left join pc_cd_org_t o on s.org_id=o.org_id left join pc_cd_org_t o1 on o.pid=o1.org_id left join pc_cd_area_info_t a on a.qkid=s.qkid where o1.pid='" + combinationid + "' and a.qkid='" + areaid + "' order by nlssort(s.blockstation_name,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		if (!"".equals(combinationid) && "sation".equals(views)) {
			sql = "select s.blockstationid,s.blockstation_name from pc_cd_station_t s left join PC_CD_STATIONTREE_V v on s.org_id=v.org_id where s.org_id=v.org_id and v.structuretype='7' and v.pid='" + combinationid + "' order by nlssort(s.blockstation_name,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		if ("".equals(combinationid) && "sation".equals(views)) {
			sql = "select s.blockstationid,s.blockstation_name from pc_cd_station_t s left join PC_CD_STATIONTREE_V v on s.org_id=v.org_id where s.org_id=v.org_id and v.structuretype='7' order by nlssort(s.blockstation_name,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		if (!"".equals(oilid) && "sation".equals(views)) {
			sql = "select s.blockstationid,s.blockstation_name from pc_cd_station_t s left join PC_CD_STATIONTREE_V v on s.org_id=v.org_id where s.org_id=v.org_id and v.structuretype='7' and v.pid='" + oilid + "' order by nlssort(s.blockstation_name,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		if (!"".equals(areaid) && "sation".equals(views)) {
			sql = "select s.blockstationid,s.blockstation_name from pc_cd_station_t s left join PC_CD_STATIONTREE_V v on s.org_id=v.org_id where s.org_id=v.org_id and v.structuretype='7' and s.qkid='" + areaid + "' order by nlssort(s.blockstation_name,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		if (!"".equals(areaid) && !"".equals(oilid) && "sation".equals(views)) {
			sql = "select s.blockstationid,s.blockstation_name from pc_cd_station_t s left join PC_CD_STATIONTREE_V v on s.org_id=v.org_id where s.org_id=v.org_id and v.structuretype='7' and v.pid='" + oilid + "' and s.qkid='" + areaid + "' order by nlssort(s.blockstation_name,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		List<Object[]> dataSet = stationTDao.queryInfo(sql);
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
	
	
//	public boolean saveOrUpdate(String grzh,String boilerId,
//			String boilerName,String boilerType,String boilerCode,
//			String rlastOper,Date rlastOdate,String remark){
//		PcCdBoilerT boiler = new PcCdBoilerT();
//		PcCdOrgT org = new PcCdOrgT();
//		if (!"".equals(boilerId)) {//有锅炉id 为更新操作
//			org = (PcCdOrgT) stationTDao.queryOrg(grzh);
//			org.setStructurename(boilerName);
//			boiler = (PcCdBoilerT) stationTDao.queryBoiler(boilerId);
//		}
	public boolean saveOrUpdate(String zzzmc2,String stationid2,String org_id2,byte ghs2,byte cygs2,String cyggg2,long zybs2,long jrbz2,String rlastOper,Date rlastOdate,String zzzlx2,String remark){
		PcCdStationT station = new PcCdStationT();
		PcCdOrgT org = new PcCdOrgT();
		if(!"".equals(stationid2)){//有注转站ID为更新操作
			org = (PcCdOrgT) stationTDao.queryOrg(org_id2);
			org.setStructurename(stationid2);
			station = (PcCdStationT) stationTDao.queryInfo(zzzmc2);
		}
		else {
//			org.setOrgId(orgId);
			org.setStructuretype((byte) 8);
			org.setStructurename(zzzmc2);
//			org.setPid();
			org.setTreeorder("");
			org.setA2id("");
			org.setStatusValue("");
			org.setCommissioningDate(new Date());
			//org.setDesignDate(new Date());
			org.setRemark(remark);
		}
	
		station.setBlockstationName(zzzmc2);
//		station.setStationType(stationid2);
//		station.setPcCdOrgT(org_id2);
//		station.setPcCdOrgT(ghs2);
//		station.setPcCdOrgT(cygs2);
//		station.setPcCdOrgT(cyggg2);
//		station.setPcCdOrgT(zybs2);
//		station.setPcCdOrgT(jrbz2);
//		station.setRlastOper(rlastOper);
//		station.setRlastOdate(rlastOdate);
//		station.setPcCdOrgT(zzzlx2);
//		station.setRemark(remark);
//		stationTDao.addOrModifyBoilerOrgInfo(boiler);
		return false;
	}
	public boolean removeStationInfo(String arg,String  org_id){
		String[] sqls = new String[2];
		sqls[0] = " DELETE from pc_cd_station_t d where d.blockstationid = '"+arg+"'";
		sqls[1] = " DELETE from pc_cd_org_t t where t.org_id = '"+org_id+"'";
		
		return stationTDao.removeStationInfo(sqls);
	}
	//添加
	public boolean addSta(PcCdStationT sta) throws Exception {
		return stationTDao.save(sta);
		}
	//更新
	public boolean updateSta(PcCdStationT sta) throws Exception {
		boolean flag = true;
		try {
			flag = stationTDao.updateDep(sta);
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}
	public List<PcCdStationT> searchstatinById(String blockstationid,
			String stationName) throws Exception {
		List<PcCdStationT> stationList = null;
		String hql = "FROM PcCdStationT u WHERE 1=1 ";
		if(blockstationid != null && !"".equals(blockstationid) ){
			hql += " and u.blockstationid = '"+blockstationid+"' ";
		}
		if(stationName != null && !"".equals(stationName) ){
			hql += " and u.blockstationName = '"+stationName+"'";
		}
		stationList = stationTDao.searchStations(hql);
		return stationList;
	}

	public List<PcCdStationT> searchstatinByName(String blockstationName)throws Exception {
		List<PcCdStationT> stationList = null;
		String hql = "FROM PcCdStationT u WHERE 1=1 ";
		
		if(blockstationName != null && !"".equals(blockstationName) ){
			hql += " and u.blockstationName = '"+blockstationName+"'";
		}
		stationList = stationTDao.searchblockstationName(hql);
		return stationList;
	}

	public JSONArray searchStatusValue() {
		JSONArray jsonArr = null;
		JSONObject jsobj = null;
		String sql = "select t.prod_sva,t.prod_sns from PC_CD_PRODUC_STATUS_SAGD_T t";
		List<Object[]> dataSet = stationTDao.queryInfo(sql);
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
	
	public JSONArray searchStatusValue1() {
		JSONArray jsonArr = new JSONArray();;
		JSONObject jsobj = null;
		String oilSql = "select t.prod_sva,t.prod_sns from PC_CD_PRODUC_STATUS_SAGD_T t";
		List<Object[]> oilSet = stationTDao.queryInfo(oilSql);
//		String comSql = "select distinct cs.org_id,cs.combination_station_name from PC_CD_COMBINATION_STATION_T cs left join pc_cd_org_t o on o.pid=cs.org_id where o.structuretype='11' order by nlssort(cs.combination_station_name,'NLS_SORT=SCHINESE_STROKE_M')";
//		List<Object[]> comSet = stationTDao.queryInfo(comSql);
		if(oilSet != null && oilSet.size()>0){
			for(Object[] entry : oilSet){
				jsobj = new JSONObject();
				jsobj.put("text", entry[1]);
				jsobj.put("id",entry[0]);
				jsonArr.add(jsobj);
			}
		}
		jsobj = new JSONObject();
		jsobj.put("text", "全部");
		jsobj.put("id","100");
		//jsonArr = new JSONArray();
		jsonArr.add(jsobj);
		return jsonArr;
	}
	public JSONArray searchSationInfo() {
		JSONArray jsonArr = new JSONArray();;
		JSONObject jsobj = null;
		String oilSql = "select oil.org_id ,oil.OILDRILLING_STATION_NAME from PC_CD_OILDRILLING_STATION_T oil  order by nlssort(oil.OILDRILLING_STATION_NAME,'NLS_SORT=SCHINESE_STROKE_M')";
		List<Object[]> oilSet = stationTDao.queryInfo(oilSql);
//		String comSql = "select distinct cs.org_id,cs.combination_station_name from PC_CD_COMBINATION_STATION_T cs left join pc_cd_org_t o on o.pid=cs.org_id where o.structuretype='11' order by nlssort(cs.combination_station_name,'NLS_SORT=SCHINESE_STROKE_M')";
//		List<Object[]> comSet = stationTDao.queryInfo(comSql);
		if(oilSet != null && oilSet.size()>0){
			for(Object[] entry : oilSet){
				jsobj = new JSONObject();
				jsobj.put("text", entry[1]);
				jsobj.put("id",entry[0]);
				jsonArr.add(jsobj);
			}
			return jsonArr;
		}
//		if(comSet != null && comSet.size()>0){
//			for(Object[] entry : comSet){
//				jsobj = new JSONObject();
//				jsobj.put("text", entry[1]);
//				jsobj.put("id",entry[0]);
//				jsonArr.add(jsobj);
//			}
//			return jsonArr;
//		}
		jsobj = new JSONObject();
		jsobj.put("text", "");
		jsobj.put("id","");
		jsonArr = new JSONArray();
		jsonArr.add(jsobj);
		return jsonArr;
		
	}
	
	public JSONObject cascadeInit() {
		String oilSql = "select r.ORG_ID,r.STRUCTURENAME from PC_CD_STATIONTREE_V r where r.STRUCTURETYPE='4' order by nlssort(r.STRUCTURENAME,'NLS_SORT=SCHINESE_STROKE_M')";
		String areaSql = "select distinct a.qkid,a.qkmc from pc_cd_area_info_t a left join pc_cd_station_t r on a.qkid=r.qkid where a.qkid=r.qkid order by nlssort(a.qkmc,'NLS_SORT=SCHINESE_STROKE_M')";
		String stationSql = "select s.blockstationid,s.blockstation_name from pc_cd_station_t s left join PC_CD_STATIONTREE_V v on s.org_id=v.org_id where s.org_id=v.org_id and v.structuretype='7' order by nlssort(s.blockstation_name,'NLS_SORT=SCHINESE_STROKE_M')";
		
		JSONArray oilJsonArr = null;
		JSONArray areaJsonArr = null;
		JSONArray stationArr = null;
		JSONObject jsobj = null;
		List<Object[]> oilSet = stationTDao.queryInfo(oilSql);
		List<Object[]> areaSet = stationTDao.queryInfo(areaSql);
		List<Object[]> stationSet = stationTDao.queryInfo(stationSql);
		
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
		
		jsobj = new JSONObject();
		jsobj.put("oiltext",oilJsonArr);
		jsobj.put("areatext",areaJsonArr);
		jsobj.put("stationtext",stationArr);
		return jsobj;
	}
	
	public JSONArray stationType() {
		JSONArray jsonArr = null;
		JSONObject jsobj = null;
		String sql = "select * from PC_CD_PRODUC_STATUS_SAGD_T t";
		List<Object[]> dataSet = stationTDao.queryInfo(sql);
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
	public boolean updateRule(PcCdRuleWellT rule) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public HashMap<String, Object> searchOnExport(String dyear,String oilstationname1,String areablock1, String blockstationtype,
			String blockstationname, String jrbz1, String combination,String totalNumFlag) throws Exception {
		JSONArray jsonArr = null;
		JSONObject tjo = null;
		HashMap<String,Object> map = new HashMap<String, Object>();
		String cloums = "select   ";
		String formTable = " from pc_cd_station_v t where 1=1";
		String totalNum = "select count(*)";

		if(!oilstationname1.equals("")&&oilstationname1!=null&&!oilstationname1.equals("undefined")&!oilstationname1.equals("全部")){
			formTable=formTable+" and OILDRILLING_NAME='"+oilstationname1+"'";
		}
		if(!combination.equals("")&&combination!=null&&!combination.equals("undefined")){ 
			formTable=formTable+" and yxz ='"+combination+"'";
		}
		if(!areablock1.equals("")&&areablock1!=null&&!areablock1.equals("undefined")){
			formTable=formTable+" and qkmc='"+areablock1+"'";
		}
		if(!blockstationname.equals("")&&blockstationname!=null&&!blockstationname.equals("undefined")){
			formTable=formTable+" and blockstation_name = '"+blockstationname+"'";
		}
		if(!dyear.equals("")&&dyear!=null&&!dyear.equals("undefined")){
			formTable=formTable+" and DYEAR='"+dyear+"'";
		}
		if(!blockstationtype.equals("")&&blockstationtype!=null&&!blockstationtype.equals("undefined")){
//			String mm="";
//			if(blockstationtype.equals("1")){
//				mm="注汽接转站";
//			}else if(blockstationtype.equals("2")){
//				mm="注汽站";
//			}else if(blockstationtype.equals("3")){
//				mm="接转站";
//			}else if(blockstationtype.equals("4")){
//				mm="管汇";
//			}
			formTable=formTable+" and bs_type='"+blockstationtype+"'";
		}
		if(!jrbz1.equals("")&&jrbz1!=null&&!jrbz1.equals("undefined") && !"全部".equals(jrbz1)){
			formTable=formTable+" and jrbz ='"+jrbz1+"'";
		}

		String[] cloumnsName = {"BLOCKSTATION_NAME","BLOCKSTATION_CODE","BS_TYPE","QKMC","OILDRILLING_NAME","PROD_SNS","COMMISSIONING_DATE","DYEAR","GHS","CYGS","CYGGG","ZYBS","YXZ","BOILER_QTY","STEAM_REMARK","JRBZ","JRBZ","LJJD_S","REMARK"};
		String kk="BLOCKSTATION_NAME";
		for(int m=1;m<cloumnsName.length;m++){
			if("BLOCKSTATION_NAME".equals(cloumnsName[m])){
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
			total = pageDao.getCounts(totalNum);
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
			lo = stationTDao.searchData(thinOilWellRD);
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
	public JSONArray searchTeamOil() {
		JSONArray jsonArr = new JSONArray();;
		JSONObject jsobj = null;
		String oilSql = "select t.org_id,t.oildrilling_station_name from PC_CD_OILDRILLING_STATION_T t left join pc_cd_org_t o on o.org_id = t.org_id order by nlssort(t.oildrilling_station_name,'NLS_SORT=SCHINESE_STROKE_M')";
		List<Object[]> oilSet = stationTDao.queryInfo(oilSql);
//		String comSql = "select distinct cs.org_id,cs.combination_station_name from PC_CD_COMBINATION_STATION_T cs left join pc_cd_org_t o on o.pid=cs.org_id where o.structuretype='11' order by nlssort(cs.combination_station_name,'NLS_SORT=SCHINESE_STROKE_M')";
//		List<Object[]> comSet = stationTDao.queryInfo(comSql);
		if(oilSet != null && oilSet.size()>0){
			for(Object[] entry : oilSet){
				jsobj = new JSONObject();
				jsobj.put("text", entry[1]);
				jsobj.put("id",entry[0]);
				jsonArr.add(jsobj);
			}
			return jsonArr;
		}
//		if(comSet != null && comSet.size()>0){
//			for(Object[] entry : comSet){
//				jsobj = new JSONObject();
//				jsobj.put("text", entry[1]);
//				jsobj.put("id",entry[0]);
//				jsonArr.add(jsobj);
//			}
//			return jsonArr;
//		}
		jsobj = new JSONObject();
		jsobj.put("text", "");
		jsobj.put("id","");
		jsonArr = new JSONArray();
		jsonArr.add(jsobj);
		return jsonArr;
		
	}
}
