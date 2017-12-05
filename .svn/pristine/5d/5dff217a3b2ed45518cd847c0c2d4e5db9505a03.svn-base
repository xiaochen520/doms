package com.echo.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.beans.PageControl;
import com.echo.dao.PageDao;
import com.echo.dao.BoilerBasicDao;
import com.echo.dto.PcCdBoilerT;
import com.echo.dto.PcCdOrgT;
import com.echo.dto.User;
import com.echo.service.BoilerBasicService;
import com.echo.util.DateBean;

public class BoilerBasicServiceImpl implements BoilerBasicService {
	private BoilerBasicDao boilerBasicDao;
	private PageDao pageDao;

	public void setPageDao(PageDao pageDao) {
		this.pageDao = pageDao;
	}
	public void setPcdDao(PageDao pageDao) {
		this.pageDao = pageDao;
	}

	public BoilerBasicDao getBoilerBasicDao() {
		return boilerBasicDao;
	}
	public void setBoilerBasicDao(BoilerBasicDao boilerBasicDao) {
		this.boilerBasicDao = boilerBasicDao;
	}
	public JSONObject queryBoilerBasicInfo(String qk,String zh,String jh,String gh,String name,String jrbz,String status_value,String dyear, int pageNo,String sort,String order,int rowsPerpage) {
		JSONArray jsonArr = null;
		JSONObject tjo = null;
		String cloums = "select   ";
		String formTable = " from pc_cd_boiler_info_v  where 1=1 ";
		String totalNum = "select count(*) ";
		if(!qk.equals("")&&qk!=null&&!qk.equals("undefined") && !"全部".equals(qk)){
			formTable=formTable+" and OILDRILLING_NAME='"+qk+"'";
		}
		if(!zh.equals("")&&zh!=null&&!zh.equals("undefined")){
			formTable=formTable+" and BLOCKSTATIONNAME='"+zh+"'";
		}
		if(!jh.equals("")&&jh!=null&&!jh.equals("undefined")){
			formTable=formTable+" and AREABLOCK='"+jh+"'";
		}
		if(!gh.equals("")&&jh!=null&&!gh.equals("undefined")){
			formTable=formTable+" and SQDW ='"+gh+"'";
		}
		if(!name.equals("")&&name!=null&&!name.equals("undefined")){
			formTable=formTable+" and BOILER_NAME='"+name+"'";
		}
		if(!jrbz.equals("")&&jrbz!=null&&!jrbz.equals("undefined") && !"全部".equals(jrbz)){
			formTable=formTable+" and JRBZ ='"+jrbz+"'";
		}
		if(!status_value.equals("")&&status_value!=null&&!status_value.equals("undefined") && !"全部".equals(status_value)){
			formTable=formTable+" and PROD_SNS='"+status_value+"'";
		}
		if(!dyear.equals("")&&dyear!=null&&!dyear.equals("undefined")){
			formTable=formTable+" and DYEAR ='"+dyear+"'";
		}
		

//		'锅炉名称--BOILER_NAME
//		'锅炉ID--BOILERID
//		'组织结构ID--ORG_ID
//		'运行组--YXZ
//		'运行组ID--YXZID
//		'供热站--BLOCKSTATIONNAME
//		'供热站ID--BLOCKSTATIONNAMEID
//		'受汽区块--AREABLOCK
//		'受汽区块ID--QKID
//		'A2ID--A2ID
//		'锅炉类型--BOILER_TYPE
//		'锅炉编号--BOILER_CODE
//		'额定水量--RATING_CAPACITY
//		'配注量--INJECTION_ALLOCATION_RATE
//		'建设生产状态--PROD_SNS
//		'建设生产状态CODE --STATUS_VALUE
//		'接入标志--JRBZ
//		'服务器逻辑节点名ID--LJJDID
//		'服务器逻辑节点名--LJJD_S
//		'投产日期--COMMISSIONING_DATE
//		'厂家--FACTORY
//		'厂家ID--FACTORY_UC
//		'上次操作者--RLAST_OPER
//		'上次操作日期--RLAST_ODATE
//		'备注--REMARK
		
		String[] cloumnsName = {"BOILER_NAME","BOILERID","ORG_ID","YXZ","YXZID","BLOCKSTATIONNAME","BLOCKSTATIONNAMEID","AREABLOCK",
				"QKID","ACCEPT_UNIT","SQDW","A2ID","BOILER_TYPE","BOILER_CODE","RATING_CAPACITY","INJECTION_ALLOCATION_RATE","PROD_SNS",
				"STATUS_VALUE","JRBZ","SWITCH_IN_FLAG","LJJDID","LJJD_S","COMMISSIONING_DATE","DYEAR","FACTORY_UC","FACTORY_NF","RLAST_OPER","RLAST_ODATE","REMARK"};
		String kk="BOILER_NAME";
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
			total = pageDao.getCounts(totalNum);
		}
		
		//排序
		if(!"".equals(sort) && !"boiler_name".equals(sort)){
			for (String cloumn : cloumnsName) {
				if(cloumn.equals(sort)){
					boilersInfo +=" order by " + cloumn + " " + order;
					break;
				}
				
			}
		}
		else {
			boilersInfo +=" order by nlssort(boiler_name, 'NLS_SORT=SCHINESE_STROKE_M') ";
		}
		//计算分页
		PageControl control = new PageControl();
		//当前页
		control.setInt_num(rowsPerpage);
		control.init(pageNo, total);
		//开始条数
		int start = control.getStart()-1;
		
		List<Object[]> lo = boilerBasicDao.queryBoilerBasicInfo(boilersInfo,start,rowsPerpage);
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
//					if(i==5){
//						if(o[5].equals("1")){
//							tjo.put(cloumnsName[i], "湿蒸");
//						}else if(o[5].equals("2")){
//							tjo.put(cloumnsName[i], "过热");
//						}else if(o[5].equals("3")){
//							tjo.put(cloumnsName[i], "高干度");
//						}else if(o[5].equals("4")){
//							tjo.put(cloumnsName[i], "燃煤");
//						}else{
//							tjo.put(cloumnsName[i], o[i].toString());
//						}
//					}
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
	
	public JSONArray queryOilSationInfo() {
		JSONArray jsonArr = null;
		JSONObject jsobj = null;
		//查询所有包含锅炉的采油站
		String sql = "select distinct oil.org_id ,oil.OILDRILLING_STATION_NAME from PC_CD_OILDRILLING_STATION_T oil  order by nlssort(oil.OILDRILLING_STATION_NAME,'NLS_SORT=SCHINESE_STROKE_M')";
//		String sql = "select distinct oil.org_id ,oil.OILDRILLING_STATION_NAME from PC_CD_OILDRILLING_STATION_T oil,(select o.pid from pc_cd_org_t o left join PC_CD_STATION_T s on s.org_id=o.org_id where s.org_id=o.org_id and s.bs_type='2') o1 where o1.pid=oil.org_id";
		
		List<Object[]> dataSet = boilerBasicDao.queryInfo(sql);
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
		
		String sql = "select distinct a.qkid,a.qkmc from pc_cd_area_info_t a left join pc_cd_station_t s on a.qkid=s.qkid left join PC_CD_BOILERTREE_V bv on s.org_id=bv.org_id where bv.structuretype='7' order by nlssort(a.qkmc,'NLS_SORT=SCHINESE_STROKE_M')";
		if("boilerbasicinfo".equals(arg)){
			sql = "select distinct a.qkid,s.qkmc from PC_CD_BOILER_T a left join  pc_cd_area_info_t s on a.qkid=s.qkid  order by nlssort(s.qkmc,'NLS_SORT=SCHINESE_STROKE_M')";
		}else if("srqk".equals(arg)){
			
			sql = "select distinct a.qkid,a.qkmc from pc_cd_area_info_t a order by nlssort(a.qkmc,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		List<Object[]> dataSet = boilerBasicDao.queryInfo(sql);
		if(dataSet != null && dataSet.size()>0){
			//生成树节点json文档
			jsonArr = new JSONArray();
			
			for(Object[] entry : dataSet){
				if(entry[0] != null && !"".equals(entry[0]) && !"null".equals(entry[0])){
					jsobj = new JSONObject();
					jsobj.put("text", entry[1]);
					jsobj.put("id",entry[0]);
					jsonArr.add(jsobj);
				}
				
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
	
	public JSONArray queryStationInfo(String oilid,String areaid,String selecteValue) {
		JSONArray jsonArr = null;
		JSONObject jsobj = null;
		//按联合站查询 注转站
		//oilid 不能等于1
 		String sql = "select s.org_id,s.blockstation_name from PC_CD_STATION_T s left join pc_cd_org_t o1 on o1.org_id=s.org_id left join pc_cd_org_t o on o1.pid=o.org_id where o.pid='" + oilid + "' order by nlssort(s.blockstation_name,'NLS_SORT=SCHINESE_STROKE_M')";

		if ("".equals(selecteValue)) {//查询所有含锅炉的注转站
			sql = "select bv.org_id,bv.structurename from PC_CD_BOILERTREE_V bv where bv.structuretype='7' order by nlssort(bv.structurename,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		if ("2".equals(selecteValue) && !"".equals(oilid) && !"".equals(areaid) || "1".equals(oilid) || ("".equals(oilid) && !"".equals(areaid))) {//按区块查询 注转站
			sql = "select s.org_id,s.blockstation_name from PC_CD_STATION_T s left join PC_CD_BOILERTREE_V bv on bv.org_id=s.org_id where s.qkid='" + areaid + "' and bv.structuretype='7' order by nlssort(s.blockstation_name,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		//按联合站和区块查询 注转站
		if ("1".equals(selecteValue) && !"".equals(oilid) && !"".equals(areaid) && !"1".equals(oilid)) {
			sql = "select s.org_id,s.blockstation_name from PC_CD_STATION_T s left join pc_cd_org_t o1 on o1.org_id=s.org_id left join pc_cd_org_t o on o1.pid=o.org_id left join pc_cd_area_info_t a on a.qkid=s.qkid where o.pid='" + areaid + "'  order by nlssort(s.blockstation_name,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		if ("4".equals(selecteValue)) {
			sql = "select s.org_id,s.blockstation_name from PC_CD_STATION_T s left join pc_cd_org_t o on o.org_id=s.steam_injection_owner where o.org_id='" + oilid + "' order by nlssort(s.blockstation_name,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		if ("all".equals(areaid)) {
			sql = "select s.org_id,s.blockstation_name from PC_CD_STATION_T s order by nlssort(s.blockstation_name,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		if ("5".equals(selecteValue)) {
			sql = "select o.org_id,o.structurename from PC_CD_BOILERTREE_V o where o.pid='" + oilid + "' order by nlssort(o.structurename,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		if ("6".equals(selecteValue)) {
			sql = "select o.org_id,o.structurename from PC_CD_BOILERGGTREE_V o where o.pid='" + oilid + "' order by nlssort(o.structurename,'NLS_SORT=SCHINESE_STROKE_M')";
			if ("".equals(oilid)) {
				sql = "select bv.org_id,bv.structurename from PC_CD_BOILERGGTREE_V bv where bv.structuretype='7' order by nlssort(bv.structurename,'NLS_SORT=SCHINESE_STROKE_M')";
			}
		}
		List<Object[]> dataSet = boilerBasicDao.queryInfo(sql);
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
	
	public JSONArray queryBoilersNameInfo(String arg,String pageid) {
		JSONArray jsonArr = null;
		JSONObject jsobj = null;
		String sql = "select gl.org_id,gl.boiler_name from pc_cd_boiler_t gl left join pc_cd_org_t o on gl.org_id=o.org_id where o.pid='" + arg + "' order by nlssort(gl.boiler_name,'NLS_SORT=SCHINESE_STROKE_M')";
		if ("".equals(arg)) {//全部的锅炉
			sql = "select gl.org_id,gl.boiler_name from pc_cd_boiler_t gl  order by nlssort(gl.boiler_name,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		if (!"".equals(pageid)) {//
			sql = "select o.org_id,o.structurename from PC_CD_BOILERGGTREE_V o where o.pid='" + arg + "' order by nlssort(o.structurename,'NLS_SORT=SCHINESE_STROKE_M')";
			if ("".equals(arg)) {
				sql = "select bv.org_id,bv.structurename from PC_CD_BOILERGGTREE_V bv where bv.structuretype='10' order by nlssort(bv.structurename,'NLS_SORT=SCHINESE_STROKE_M')";
				
			}
		}
		List<Object[]> dataSet = boilerBasicDao.queryInfo(sql);
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
	
	public boolean addBoilerBasicInfo(String arg){
		return false;
	}
	public boolean saveOrUpdate(String yxz2,String grzh,String boilerid,String org_id,
			String boilerName,String boilerType,String boilerCode,
			String rlastOper,Date rlastOdate,String jrbz,String scadaNode,String commissioningDate,String statusValue,String remark)throws Exception{
		PcCdBoilerT boiler = new PcCdBoilerT();
		PcCdOrgT org = null;
		org = new PcCdOrgT();
		
		if (!"".equals(boilerid)) {//有锅炉id 为更新操作
			org = (PcCdOrgT) boilerBasicDao.queryOrg(org_id);
			boiler = (PcCdBoilerT) boilerBasicDao.queryBoiler(boilerid);
			org.setStructurename(boilerName);
			org.setPid(grzh);
			org.setStatusValue(statusValue);
			org.setCommissioningDate(DateBean.getStrDate(commissioningDate));
			org.setScadaNode(scadaNode);
			org.setCode(boilerCode);
			org.setSwitchInFlag(jrbz);
		}else {
			org.setStructurename(boilerName);
			org.setStructuretype((byte) 10);
			org.setPid(grzh);
			//org.setTreeorder("");
			//org.setA2id("");
			org.setStatusValue(statusValue);
			org.setCommissioningDate(DateBean.getStrDate(commissioningDate));
			org.setScadaNode(scadaNode);
			org.setCode(boilerCode);
			org.setSwitchInFlag(jrbz);
			//org.setDesignDate(new Date());
			org.setRemark(remark);
		}
		boiler.setBoilerName(boilerName);
		boiler.setBoilerType(boilerType);
		
		//org.setCode(boilerCode);
		boiler.setRlastOper(rlastOper);
		boiler.setRlastOdate(rlastOdate);
		boiler.setRemark(remark);
		boiler.setPcCdOrgT(org);
		return boilerBasicDao.addOrModifyBoilerOrgInfo(boiler);
	}
	public boolean removeBoilerBasicInfo(String bid,String org_id){
		PcCdBoilerT boiler = (PcCdBoilerT) boilerBasicDao.queryBoiler(bid);
		PcCdOrgT org = (PcCdOrgT) boilerBasicDao.queryOrg(org_id);
		boiler.setPcCdOrgT(org);
		
		boolean deleteFlg = boilerBasicDao.removeBoilerOrgInfo(boiler, org);
		return deleteFlg;
	}
	
	public JSONObject cascadeInit(String pageid) {
		//所有含锅炉的采油站
		String oilSql = "select r.ORG_ID,r.STRUCTURENAME from PC_CD_BOILERTREE_V r where r.STRUCTURETYPE='4' order by nlssort(r.STRUCTURENAME,'NLS_SORT=SCHINESE_STROKE_M')";
		//所有含锅炉的区块
//		String areaSql = "select distinct a.qkid,a.qkmc from pc_cd_area_info_t a left join pc_cd_station_t s on a.qkid=s.qkid left join PC_CD_BOILERTREE_V bv on s.org_id=bv.org_id where bv.structuretype='7' order by nlssort(a.qkmc,'NLS_SORT=SCHINESE_STROKE_M')";
		String areaSql = "select distinct a.qkid,s.qkmc from PC_CD_BOILER_T a left join pc_cd_area_info_t s on a.qkid=s.qkid where a.qkid=s.qkid order by nlssort(s.qkmc,'NLS_SORT=SCHINESE_STROKE_M')";
		//所有含锅炉的注转站
		String stationSql = "select bv.org_id,bv.structurename from PC_CD_BOILERTREE_V bv where bv.structuretype='7' order by nlssort(bv.structurename,'NLS_SORT=SCHINESE_STROKE_M')";
		//所有锅炉
		String boilerSql = "select gl.org_id,gl.boiler_name from pc_cd_boiler_t gl order by nlssort(gl.boiler_name,'NLS_SORT=SCHINESE_STROKE_M')";
		if ("6".equals(pageid)) {
			oilSql = "select r.ORG_ID,r.STRUCTURENAME from PC_CD_BOILERGGTREE_V r where r.STRUCTURETYPE='4' order by nlssort(r.STRUCTURENAME,'NLS_SORT=SCHINESE_STROKE_M')";
			stationSql = "select bv.org_id,bv.structurename from PC_CD_BOILERGGTREE_V bv where bv.structuretype='7' order by nlssort(bv.structurename,'NLS_SORT=SCHINESE_STROKE_M')";
			boilerSql = "select bv.org_id,bv.structurename from PC_CD_BOILERGGTREE_V bv where bv.structuretype='10' order by nlssort(bv.structurename,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		if ("基础信息".equals(pageid)) {
			oilSql = "select distinct a.org_id,a.structurename from pc_cd_org_t a where a.structuretype=4 order by nlssort(a.structurename,'NLS_SORT=SCHINESE_STROKE_M')";
		//stationSql = "select bv.org_id,bv.structurename from PC_CD_BOILERGGTREE_V bv where bv.structuretype='7' order by nlssort(bv.structurename,'NLS_SORT=SCHINESE_STROKE_M')";
			//boilerSql = "select gl.org_id,gl.boiler_name from pc_cd_boiler_t gl  order by nlssort(gl.boiler_name,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		JSONArray oilJsonArr = null;
		JSONArray areaJsonArr = null;
		JSONArray stationArr = null;
		JSONArray boilerArr = null;
		JSONObject jsobj = null;
		List<Object[]> oilSet = boilerBasicDao.queryInfo(oilSql);
		List<Object[]> areaSet = null;
		if ("基础信息".equals(pageid)) {
			areaSet = boilerBasicDao.queryInfo(areaSql);
		}
		List<Object[]> stationSet = boilerBasicDao.queryInfo(stationSql);
		List<Object[]> boilerSet = boilerBasicDao.queryInfo(boilerSql);
		
		if(oilSet != null && oilSet.size()>0){
			oilJsonArr = new JSONArray();   
			for(Object[] entry : oilSet){
				jsobj = new JSONObject();
				jsobj.put("text", entry[1]);
				jsobj.put("id",entry[0]);
				oilJsonArr.add(jsobj);
			}
			if (!"基础信息".equals(pageid)) {
				jsobj = new JSONObject();
				jsobj.put("text", "全部");
				jsobj.put("id", 1);
			}
			oilJsonArr.add(jsobj);
		}
		
		if ("基础信息".equals(pageid)) {
			if(areaSet != null && areaSet.size()>0){
				areaJsonArr = new JSONArray();   
				for(Object[] entry : areaSet){
					jsobj = new JSONObject();
					jsobj.put("text", entry[1]);
					jsobj.put("id",entry[0]);
					areaJsonArr.add(jsobj);
				}
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
		
		if(boilerSet != null && boilerSet.size()>0){
			boilerArr = new JSONArray();   
			for(Object[] entry : boilerSet){
				jsobj = new JSONObject();
				jsobj.put("text", entry[1]);
				jsobj.put("id",entry[0]);
				boilerArr.add(jsobj);
			}
		}
		
		jsobj = new JSONObject();
		jsobj.put("oiltext",oilJsonArr);
		jsobj.put("areatext",areaJsonArr);
		jsobj.put("stationtext",stationArr);
		jsobj.put("boilertext",boilerArr);
		return jsobj;
	}

	public JSONArray searchGroupInfo(String arg) {
		JSONArray jsonArr = null;
		JSONObject jsobj = null;
		String sql = "select o.org_id,o.structurename from pc_cd_org_t o where o.structuretype='14' order by nlssort(o.structurename,'NLS_SORT=SCHINESE_STROKE_M')";
		if (!"".equals(arg)) {
			sql = "select o.org_id,o.structurename from pc_cd_org_t o where o.structuretype='14' and o.pid='" + arg + "' order by nlssort(o.structurename,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		List<Object[]> dataSet = boilerBasicDao.queryInfo(sql);
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

	public List<Object[]> searchBoilerByName(String boilerName) throws Exception {
		List<Object[]> stationlist = boilerBasicDao.queryInfo(" select BOILERID, ORG_ID, BOILER_NAME from PC_CD_BOILER_T b where b.BOILER_NAME='" + boilerName + "'");
		return stationlist;
	}
	public List<Object[]> searchStationByName(String stationName) throws Exception {
		List<Object[]> stationlist = boilerBasicDao.queryInfo(" select s.org_id,s.blockstation_name from PC_CD_STATION_T s where s.blockstation_name='" + stationName + "'");
		return stationlist;
	}
	@Override
	public List<PcCdBoilerT> searchBoilerByName(String boilerName, String id)
			throws Exception {
		String hql = "from  PcCdBoilerT s where 1=1 ";
		if(id != null && !"".equals(id)){
			hql += " and s.boilerid ='"+id+"'";
		}
		
		if(boilerName != null && !"".equals(boilerName)){
			hql += " and s.boilerName ='"+boilerName+"'";
		}
		return (List<PcCdBoilerT>) boilerBasicDao.serchBoiler(hql);
	}
	@Override
	public boolean saveOrUpdate(PcCdBoilerT pcCdBoilerT, String flg)
			throws Exception {
		return boilerBasicDao.addOrModifyBoilerOrgInfo(pcCdBoilerT);
	}
	@Override
	public HashMap<String, Object> searchOnExport(String acceptunit,
			String blockstationname, String boilersName, String areablock,
			String jrbz1, String statusValue1,String dyear, String totalNumFlag)
			throws Exception {

		JSONArray jsonArr = null;
		JSONObject tjo = null;
		HashMap<String,Object> map = new HashMap<String, Object>();
		String cloums = "select   ";
		String formTable = " from pc_cd_boiler_info_v  where 1=1 ";
		String totalNum = "select count(*) ";
		if(!acceptunit.equals("")&&acceptunit!=null&&!acceptunit.equals("undefined") && !"全部".equals(acceptunit)){
			formTable=formTable+" and OILDRILLING_NAME='"+acceptunit+"'";
		}
		if(!blockstationname.equals("")&&blockstationname!=null&&!blockstationname.equals("undefined")){
			formTable=formTable+" and BLOCKSTATIONNAME='"+blockstationname+"'";
		}
		if(!areablock.equals("")&&areablock!=null&&!areablock.equals("undefined")){
			formTable=formTable+" and AREABLOCK='"+areablock+"'";
		}
	
		if(!boilersName.equals("")&&boilersName!=null&&!boilersName.equals("undefined")){
			formTable=formTable+" and BOILER_NAME='"+boilersName+"'";
		}
		if(!jrbz1.equals("")&&jrbz1!=null&&!jrbz1.equals("undefined") && !"全部".equals(jrbz1)){
			formTable=formTable+" and JRBZ ='"+jrbz1+"'";
		}
		if(!statusValue1.equals("")&&statusValue1!=null&&!statusValue1.equals("undefined") && !"全部".equals(statusValue1)){
			formTable=formTable+" and PROD_SNS='"+statusValue1+"'";
		}
		if(!dyear.equals("")&&dyear!=null&&!dyear.equals("undefined")){
			formTable=formTable+" and DYEAR ='"+dyear+"'";
		}
		String[] cloumnsName = {"BOILER_NAME","YXZ","BLOCKSTATIONNAME","BOILER_CODE","BOILER_TYPE","RATING_CAPACITY",
				"INJECTION_ALLOCATION_RATE","AREABLOCK","SQDW","PROD_SNS","COMMISSIONING_DATE","DYEAR","FACTORY_NF","JRBZ",
				"LJJD_S","REMARK"};
		String kk="BOILER_NAME";
		for(int m=1;m<cloumnsName.length;m++){
			if("BOILER_NAME".equals(cloumnsName[m])){
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
//			thinOilWellRD +=" order by nlssort(ghmc, 'NLS_SORT=SCHINESE_STROKE_M') ";
//		}
		//计算分页
		PageControl control = new PageControl();
		//当前页
		List<Object[]> lo = new ArrayList<Object[]>();
		//control.setInt_num(rowsPerpage);
		if ("export".equals(totalNumFlag)) {
			lo = boilerBasicDao.searchData(thinOilWellRD);
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
