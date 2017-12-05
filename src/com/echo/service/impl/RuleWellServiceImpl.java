package com.echo.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.echo.beans.PageControl;
import com.echo.dao.CommonDao;
import com.echo.dao.RuleWellDao;
import com.echo.dto.PcCdRuleWellT;
import com.echo.dto.PcRpdRuleWellProdT;
import com.echo.dto.PcRpdRuleWellProdbT;
import com.echo.service.RuleWellService;
import com.echo.util.CommonsUtil;
import com.echo.util.DateBean;

public class RuleWellServiceImpl implements RuleWellService{
	private RuleWellDao ruleWellDao;
	private CommonDao commonDao;
	
	public void setRuleWellDao(RuleWellDao ruleWellDao) {
		this.ruleWellDao = ruleWellDao;
	}
	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}
	public JSONObject searchRullWell(String qk,String zh,String jh,String gh,String name,String jrbz1,String dyear,int pageNo,String sort,String order,int rowsPerpage) {
		JSONArray jsonArr = null;
		JSONObject tjo = null;
		String cloums = "select   ";
		String formTable = " from pc_cd_well_rule_v  where 1=1 ";
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
			formTable=formTable+" and well_name like '%"+name+"%'";
		}
		if(!jrbz1.equals("")&&jrbz1!=null&&!jrbz1.equals("undefined") && !jrbz1.equals("全部")){
			formTable=formTable+" and jrbz = '"+jrbz1+"'";
		}
		if(!dyear.equals("")&&dyear!=null&&!dyear.equals("undefined")){
			formTable=formTable+" and dyear = '"+dyear+"'";
		}

		
		String[] cloumnsName = {"RULE_WELLID","ORG_ID","QKID","A2ID","WELL_NAME","WELL_COLE","BEWELL_NAME","COMMISSIONING_DATE","STATUS_VALUE","VALVE_CODING","CHANNEL_NO",
				"RLAST_OPER","RLAST_ODATE","OUTPUT_COEFFICIENT","REMARK","OILSTATIONNAME","AREABLOCK","BLOCKSTATIONNAME","ZZZ_ID","GH_ID","MANIFOLD","PROD_SNS","BRANCHINGID","JRBZ","SWITCH_IN_FLAG","LJJDID","LJJD_S","QKMC",
				"DYEAR","INCREASE_FREQ_FLAG","INCREASE_INTERVAL","START_INCREASE_FREQ_TIME","END_INCREASE_FREQ_TIME","teamGroup"};
		String kk="RULE_WELLID";
		for(int m=1;m<cloumnsName.length;m++){
			if("RLAST_ODATE".equals(cloumnsName[m])){
				kk += ",to_char(RLAST_ODATE,'YYYY-MM-DD HH24:MI:SS') as RLAST_ODATE";
			}else if("START_INCREASE_FREQ_TIME".equals(cloumnsName[m])){
				kk += ",to_char(START_INCREASE_FREQ_TIME,'YYYY-MM-DD HH24:MI') as START_INCREASE_FREQ_TIME";
			}else if("END_INCREASE_FREQ_TIME".equals(cloumnsName[m])){
				kk += ",to_char(END_INCREASE_FREQ_TIME,'YYYY-MM-DD HH24:MI') as END_INCREASE_FREQ_TIME";
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
			total = ruleWellDao.getCounts(totalNum);
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
		
		List<Object[]> lo = ruleWellDao.searchRullWell(boilersInfo,start,rowsPerpage);
		if (lo != null && 0 < lo.size()) {
			// 生成树节点json文档
			jsonArr = new JSONArray();
			for (Object[] o : lo) {
				tjo = new JSONObject();

				for (int i = 0; i < o.length; i++) {
					if (o[i] == null||o[i].equals("null")) {
						o[i] = "";
					}
					if(i==29 && o[i]!=null && o[i].toString().equals("1"))
						tjo.put(cloumnsName[i], "已加密");
					else if(i==29 && o[i]!=null && o[i].toString().equals("0")){
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
		sqls[0] = " DELETE from PC_CD_RULE_WELL_T d where d.rule_wellid = '"+arg+"'";
		sqls[1] = " DELETE from pc_cd_org_t t where t.org_id = '"+orgid+"'";
		
		return ruleWellDao.removeStationInfo(sqls);
	}
	public JSONArray queryOilSationInfo(String a) {
		JSONArray jsonArr = null;
		JSONObject jsobj = null;
		String sql = "select r.ORG_ID,r.STRUCTURENAME from PC_CD_RULETREE_V r where r.STRUCTURETYPE='4' order by nlssort(r.STRUCTURENAME,'NLS_SORT=SCHINESE_STROKE_M')";
		if ("thinoil".equals(a)) {
			sql = "select r.ORG_ID,r.STRUCTURENAME from PC_CD_THINOILTREE_V r where r.STRUCTURETYPE='4' order by nlssort(r.STRUCTURENAME,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		if ("gas".equals(a)) {
			sql = "select r.ORG_ID,r.STRUCTURENAME from PC_CD_GASTREE_V r where r.STRUCTURETYPE='4' order by nlssort(r.STRUCTURENAME,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		if ("injectionTopry".equals(a)) {
			sql = "select r.ORG_ID,r.STRUCTURENAME from PC_CD_INJECTIONTOPRYTREE_V r where r.STRUCTURETYPE='4' order by nlssort(r.STRUCTURENAME,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		if ("injectionFlood".equals(a)) {
			//sql = "select r.ORG_ID,r.STRUCTURENAME from PC_CD_WATERFLOODINGTREE_V r where r.STRUCTURETYPE='4' order by nlssort(r.STRUCTURENAME,'NLS_SORT=SCHINESE_STROKE_M')";
			sql = "select r.ORG_ID,r.STRUCTURENAME from PC_CD_INJECTIONTOPRYTREE_V r where r.STRUCTURETYPE='4' order by nlssort(r.STRUCTURENAME,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		if ("observation".equals(a)) {
			sql = "select r.ORG_ID,r.STRUCTURENAME from PC_CD_OBSERVATIONTREE_V r where r.STRUCTURETYPE='4' order by nlssort(r.STRUCTURENAME,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		if ("manifold".equals(a)) {
			sql = "select r.ORG_ID,r.STRUCTURENAME from PC_CD_MANIFOLDTREE_V r where r.STRUCTURETYPE='4' order by nlssort(r.STRUCTURENAME,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		if ("all".equals(a)) {
			sql = "select oil.org_id,oil.oildrilling_station_name from pc_cd_oildrilling_station_t oil order by nlssort(oil.oildrilling_station_name,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		if ("boiler".equals(a)) {
			sql = "select r.ORG_ID,r.STRUCTURENAME from PC_CD_BOILERTREE_V r where r.STRUCTURETYPE='4' order by nlssort(r.STRUCTURENAME,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		if ("im".equals(a)) {
			sql = "select t.ORG_ID,t.STRUCTURENAME from PC_CD_FACILITYTREE_V t where t.STRUCTURETYPE in (4,5) order by nlssort(t.STRUCTURENAME,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		
		if ("ggdboiler".equals(a)) {
			sql = "select r.ORG_ID,r.STRUCTURENAME from PC_CD_BOILERGGTREE_V r where r.STRUCTURETYPE='4' order by nlssort(r.STRUCTURENAME,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		//SAGD采油站查找
		if("sagdcaiyou".equals(a)){
			sql = "select r.ORG_ID,r.STRUCTURENAME from PC_CD_MANIFOLDTREE_V r where  r.structurename in('SAGD采油一站','SAGD采油二站')  order by nlssort(r.STRUCTURENAME,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		List<Object[]> dataSet = ruleWellDao.queryInfo(sql);
		if(dataSet != null && dataSet.size()>0){
			//生成树节点json文档
			jsonArr = new JSONArray();
			for(Object[] entry : dataSet){
				jsobj = new JSONObject();
				jsobj.put("text", entry[1]);
				jsobj.put("id",entry[0]);
				jsonArr.add(jsobj);
			}
			if ("all".equals(a)) {
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
	
	public JSONArray queryAreablockInfo(String arg) {
		JSONArray jsonArr = null;
		JSONObject jsobj = null;
//		String sql = "select distinct a.qkid,a.qkmc from pc_cd_area_info_t a left join (select s.qkid from PC_CD_STATION_T s left join (select o.org_id from Pc_Cd_Org_t o where o.pid='" + arg + "') o1 on o1.org_id=s.org_id where o1.org_id=s.org_id) q on q.qkid=a.qkid where q.qkid=a.qkid ";
//		if ("".equals(arg)) {
		String	sql = "select distinct a.qkid,a.qkmc from pc_cd_area_info_t a left join pc_cd_rule_well_t r on a.qkid=r.qkid where a.qkid=r.qkid order by nlssort(a.qkmc,'NLS_SORT=SCHINESE_STROKE_M')";
//		}
		if ("thinoil".equals(arg)) {
			sql = "select distinct a.qkid,a.qkmc from pc_cd_area_info_t a left join pc_cd_thinoil_well_t r on a.qkid=r.qkid where a.qkid=r.qkid order by nlssort(a.qkmc,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		if ("gas".equals(arg)) {
			sql = "select distinct a.qkid,a.qkmc from pc_cd_area_info_t a left join pc_cd_gas_well_t r on a.qkid=r.qkid where a.qkid=r.qkid order by nlssort(a.qkmc,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		if ("observation".equals(arg)) {
			sql = "select distinct a.qkid,a.qkmc from pc_cd_area_info_t a left join pc_cd_observation_well_t r on a.qkid=r.qkid where a.qkid=r.qkid order by nlssort(a.qkmc,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		if ("manifold".equals(arg)) {
			sql = "select distinct a.qkid,a.qkmc from pc_cd_area_info_t a left join pc_cd_station_t s on a.qkid=s.qkid left join PC_CD_MANIFOLDTREE_V bv on s.org_id=bv.org_id where bv.structuretype='7' order by nlssort(a.qkmc,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		if ("waterf".equals(arg)) {
			sql = "select distinct a.qkid,a.qkmc from pc_cd_area_info_t a left join PC_CD_WATERFLOODING_WELL_T r on a.qkid=r.qkid where a.qkid=r.qkid order by nlssort(a.qkmc,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		if ("wi".equals(arg)) {
			sql = "select distinct a.qkid,a.qkmc from pc_cd_area_info_t a left join pc_cd_water_injectiontopry_t r on a.qkid=r.qkid where a.qkid=r.qkid order by nlssort(a.qkmc,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		List<Object[]> dataSet = ruleWellDao.queryInfo(sql);
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
	
	public JSONArray queryStationInfo(String oilid,String areaid,String selecteValue) {
		JSONArray jsonArr = null;
		JSONObject jsobj = null;
		String sql = "select s.org_id,s.blockstation_name from PC_CD_STATION_T s left join (select o.* from pc_cd_org_t o left join PC_CD_OILDRILLING_STATION_T oil on o.pid=oil.org_id where o.pid='" + oilid + "') ss on s.org_id=ss.org_id where s.org_id=ss.org_id  order by nlssort(s.blockstation_name,'NLS_SORT=SCHINESE_STROKE_M')";
		if ("".equals(selecteValue) || "1".equals(oilid)) {
			sql = "select r.ORG_ID,r.STRUCTURENAME from PC_CD_RULETREE_V r where r.STRUCTURETYPE='7' order by nlssort(r.STRUCTURENAME,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		if ("2".equals(selecteValue) && "".equals(oilid) && !"".equals(areaid) || "1".equals(oilid)) {//按区块查询 注转站
			sql = "select s.org_id,s.blockstation_name from PC_CD_STATION_T s where s.qkid='" + areaid + "' order by nlssort(s.blockstation_name,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		if ("1".equals(selecteValue) && !"".equals(oilid) && !"".equals(areaid)) {
			sql = "select s.org_id,s.blockstation_name from PC_CD_STATION_T s left join (select o.org_id from Pc_Cd_Org_t o where o.pid='" + oilid + "') o1 on s.org_id=o1.org_id left join pc_cd_area_info_t a on a.qkid=s.qkid where s.org_id=o1.org_id and s.qkid='" + areaid + "' order by nlssort(s.blockstation_name,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		if ("thinoil".equals(oilid)) {
			sql = "select r.ORG_ID,r.STRUCTURENAME from PC_CD_THINOILTREE_V r where r.STRUCTURETYPE='7' order by nlssort(r.STRUCTURENAME,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		if ("observation".equals(oilid)) {
			sql = "select r.ORG_ID,r.STRUCTURENAME from PC_CD_OBSERVATIONTREE_V r where r.STRUCTURETYPE='7' order by nlssort(r.STRUCTURENAME,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		if ("manifold".equals(oilid)) {
			sql = "select r.ORG_ID,r.STRUCTURENAME from PC_CD_MANIFOLDTREE_V r where r.STRUCTURETYPE='7' order by nlssort(r.STRUCTURENAME,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		if ("all".equals(oilid)) {
			sql = "select s.org_id,s.blockstation_name from pc_cd_station_t s order by nlssort(s.blockstation_name,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		if ("noall".equals(oilid)) {//注转站不包含bs_type=2 
			sql = "select s.org_id,s.blockstation_name from pc_cd_station_t s where bs_type<>2 order by nlssort(s.blockstation_name,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		if ("alls".equals(oilid)) {//管汇点
			sql = "select s.org_id,s.blockstation_name from pc_cd_station_t s  order by nlssort(s.blockstation_name,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		if ("im".equals(oilid)) {
			sql = "select t.ORG_ID,t.STRUCTURENAME from PC_CD_FACILITYTREE_V t where t.STRUCTURETYPE in (7,11,13,14) order by nlssort(t.STRUCTURENAME,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		if ("ims".equals(areaid)) {
			sql = "select t.ORG_ID,t.STRUCTURENAME from PC_CD_FACILITYTREE_V t where t.pid='" + oilid + "' order by nlssort(t.STRUCTURENAME,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		List<Object[]> dataSet = ruleWellDao.queryInfo(sql);
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
	
	public JSONArray queryWellInfo(String arg,String orgid) {
		JSONArray jsonArr = null;
		JSONObject jsobj = null;
		String sql = "select w.rule_wellid,w.well_name from PC_CD_RULE_WELL_T w left join (select o1.org_id from pc_cd_org_t o1 left join (select o.org_id from pc_cd_org_t o where o.pid='" + orgid + "') o2 on o1.pid=o2.org_id where o1.pid=o2.org_id) o3 on o3.org_id=w.org_id where o3.org_id=w.org_id order by nlssort(w.well_name,'NLS_SORT=SCHINESE_STROKE_M')";
		if ("".equals(orgid)) {
			sql = "select r.ORG_ID,r.STRUCTURENAME from PC_CD_RULETREE_V r where r.STRUCTURETYPE='9' order by nlssort(r.STRUCTURENAME,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		if ("im".equals(orgid)) {
			sql = "select t.ORG_ID,t.STRUCTURENAME from PC_CD_FACILITYTREE_V t where t.STRUCTURETYPE in (9) order by nlssort(t.STRUCTURENAME,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		if ("ims".equals(arg)) {
			sql = "select f.ORG_ID,f.STRUCTURENAME,f.STRUCTURETYPE,fv.ORG_ID fo,fv.STRUCTURENAME fn,fv.STRUCTURETYPE fp from PC_CD_FACILITYTREE_V f left join PC_CD_FACILITYTREE_V fv on fv.PID=f.ORG_ID where f.pid='" + orgid + "' order by nlssort(f.STRUCTURENAME,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		List<Object[]> dataSet = ruleWellDao.queryInfo(sql);
		if(dataSet != null && dataSet.size()>0){
			//生成树节点json文档
			jsonArr = new JSONArray();
			for(Object[] entry : dataSet){
				jsobj = new JSONObject();
				if (entry.length > 2) {
					if (entry[5] != null && !"".equals(entry[5].toString()) && "9".equals(entry[5].toString())) {
						jsobj.put("text", entry[4]);
						jsobj.put("id",entry[3]);
						jsonArr.add(jsobj);
						continue;
					}
					if ("9".equals(entry[2].toString())) {
						jsobj.put("text", entry[1]);
						jsobj.put("id",entry[0]);
						jsonArr.add(jsobj);
					}
				}else {
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
	public JSONObject cascadeInit() {
		String oilSql = "select r.ORG_ID,r.STRUCTURENAME from PC_CD_RULETREE_V r where r.STRUCTURETYPE='4' order by nlssort(r.STRUCTURENAME,'NLS_SORT=SCHINESE_STROKE_M')";
		String areaSql = "select distinct a.qkid,a.qkmc from pc_cd_area_info_t a left join pc_cd_rule_well_t r on a.qkid=r.qkid where a.qkid=r.qkid";
		String stationSql = "select r.ORG_ID,r.STRUCTURENAME from PC_CD_RULETREE_V r where r.STRUCTURETYPE='7' order by nlssort(r.STRUCTURENAME,'NLS_SORT=SCHINESE_STROKE_M')";
		String wellSql = "select r.ORG_ID,r.STRUCTURENAME from PC_CD_RULETREE_V r where r.STRUCTURETYPE='9' order by nlssort(r.STRUCTURENAME,'NLS_SORT=SCHINESE_STROKE_M')";
		JSONArray oilJsonArr = null;
		JSONArray areaJsonArr = null;
		JSONArray stationArr = null;
		JSONArray wellArr = null;
		JSONObject jsobj = null;
		List<Object[]> oilSet = ruleWellDao.queryInfo(oilSql);
		List<Object[]> areaSet = ruleWellDao.queryInfo(areaSql);
		List<Object[]> stationSet = ruleWellDao.queryInfo(stationSql);
		List<Object[]> wellSet = ruleWellDao.queryInfo(wellSql);
		
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
	public List<PcCdRuleWellT> searchRuleByName(String wellName)throws Exception {
		List<PcCdRuleWellT> ruleList = null;
		String hql = "from PcCdRuleWellT r where 1=1";
		if(wellName !=null && !"".equals(wellName)){
			hql += " and r.wellName ='"+wellName+"'";
		}
		ruleList = ruleWellDao.searchRullWellName(hql);
		return ruleList;
	}
	public boolean addRule(PcCdRuleWellT rule) throws Exception {
		return ruleWellDao.save(rule);
	}
	public List<PcCdRuleWellT> searchRuleById(String rule_wellid, String wellName)throws Exception {
		List<PcCdRuleWellT> ruleList = null;
		String hql = "from PcCdRuleWellT r where 1=1";
		if(rule_wellid !=null && !"".equals(rule_wellid)){
			hql += " and   r.ruleWellid = '"+rule_wellid+"'";
		}
		
		if(wellName != null && !"".equals(wellName)){
			hql += " and r.wellName ='"+wellName+"'";
		}
		ruleList = ruleWellDao.searchRuleName(hql);
		return ruleList;
	}
	@SuppressWarnings("unused")
	public boolean updateRule(PcCdRuleWellT rule) throws Exception {
		boolean  flag = true;
		try {
			flag = ruleWellDao.updateRule(rule);
		} catch (Exception e) {
			flag = false;
		}
		 
	
		
		return false;
	}
	public JSONArray querywellAreaSelf(String wellAreaSelf) throws Exception {
		JSONObject jsobj = null;
		JSONArray jsonArr = null;
		String sql="select qkid,qkmc from pc_cd_area_info_t order by nlssort(qkmc, 'NLS_SORT=SCHINESE_STROKE_M')";
		List<Object[]> dataSet = ruleWellDao.querywellAreaSelf(sql);
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
	public String serarchWellAreaSelf(String id) throws Exception {
		String returnStr = "";
		String sql = " select a.qkid,a.qkmc  from  pc_cd_area_info_t a where 1=1";
		if(id !=null && !"".equals(id)){
			sql += " and a.qkid = '"+id+"'";
		}
		List<Object[]> listObj =  ruleWellDao.serarchWellAreaSelf(sql);
		if(listObj != null && listObj.size()>0){
					returnStr = listObj.get(0)[0].toString();
				}
		return returnStr;
	}
	@SuppressWarnings("unused")
	@Override  
	public HashMap<String, Object> searchOnExport(String oilstationname1,String blockstationname1,String rulewellname1,String areablock,String jrbz1,String dyear,String totalNumFlag)throws Exception {

			JSONArray jsonArr = null;
			JSONObject tjo = null;
			HashMap<String,Object> map = new HashMap<String, Object>();
			String cloums = "select   ";
			String formTable = " from pc_cd_well_rule_v  where 1=1 ";
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
				formTable=formTable+" and well_name like '%"+rulewellname1+"%'";
			}
			if(!jrbz1.equals("")&&jrbz1!=null&&!jrbz1.equals("undefined") && !jrbz1.equals("全部")){
				formTable=formTable+" and jrbz = '"+jrbz1+"'";
			}
			if(!dyear.equals("")&&dyear!=null&&!dyear.equals("undefined")){
				formTable=formTable+" and dyear = '"+dyear+"'";
			}

			
			String[] cloumnsName = {"WELL_NAME","OILSTATIONNAME","QKMC","BLOCKSTATIONNAME","teamGroup","MANIFOLD","PROD_SNS","COMMISSIONING_DATE","DYEAR","BRANCHINGID","WELL_COLE","BEWELL_NAME",
					"INCREASE_FREQ_FLAG","START_INCREASE_FREQ_TIME","END_INCREASE_FREQ_TIME","INCREASE_INTERVAL","JRBZ","LJJD_S","VALVE_CODING","CHANNEL_NO","OUTPUT_COEFFICIENT","REMARK"};
			String kk="WELL_NAME";
			for(int m=1;m<cloumnsName.length;m++){
				if("WELL_NAME".equals(cloumnsName[m])){
					//kk=kk+","+"to_char(CJSJ,'YYYY-MM-DD hh24:mi:ss') as CJSJ";
				}else if("START_INCREASE_FREQ_TIME".equals(cloumnsName[m])){
					kk += ",to_char(START_INCREASE_FREQ_TIME,'YYYY-MM-DD HH24:MI') as START_INCREASE_FREQ_TIME";
				}else if("END_INCREASE_FREQ_TIME".equals(cloumnsName[m])){
					kk += ",to_char(END_INCREASE_FREQ_TIME,'YYYY-MM-DD HH24:MI') as END_INCREASE_FREQ_TIME";
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
				total = ruleWellDao.getCounts(totalNum);
			}
			if ("totalNum".equals(totalNumFlag)) {
				map.put("totalNum", total + "");
				return map;
			}
			//排序
//			if(!"".equals(sort) && !"ghmc".equals(sort)){
//				for (String cloumn : cloumnsName) {
//					if(cloumn.equals(sort)){
//						thinOilWellRD +=" order by " + cloumn + " " + order;
//						break;
//					}
//					
//				}
//			}
//			else {
//				thinOilWellRD +=" order by ghmc ";
//			}
			//计算分页
			PageControl control = new PageControl();
			//当前页
			List<Object[]> lo = new ArrayList<Object[]>();
			//control.setInt_num(rowsPerpage);
			if ("export".equals(totalNumFlag)) {
				lo = ruleWellDao.searchData(thinOilWellRD);
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
	public HashMap<String, Object> searchRullWellRPD(String oil, String banzu,
			String station, String boilersName, String well, String shstatus,
			String startDate, String endDate, int pageNo, String sort,
			String order, int rowsPerpage, String totalNumf,String deptype) {
		String viewName = "pc_rpd_rule_well_prodb_v";
		if("15".equals(deptype)){
			viewName = "pc_rpd_rule_well_prod_v";
		}
		
		String cloums = "select  ";
		String formTable = "  from "+viewName+" a where 1=1 ";
		String totalNum = "select count(*) ";
		HashMap<String,Object> map = new HashMap<String, Object>();
		
		if(!oil.equals("")&&oil!=null&&!oil.equals("undefined")&&!oil.equals("全部")){
			formTable=formTable+" and OILSTATIONNAME='"+oil+"'";
		}
		if(!banzu.equals("")&&banzu!=null&&!banzu.equals("undefined")&&!banzu.equals("全部")){
			formTable=formTable+" and TEAMNAME ='"+banzu+"'";
		}
		if(!station.equals("")&&station!=null&&!station.equals("undefined")){
			formTable=formTable+" and BLOCKSTATIONNAME ='"+station+"'";
		}
		if(!boilersName.equals("")&&boilersName!=null&&!boilersName.equals("undefined")){
			formTable=formTable+" and MANIFOLD='"+boilersName+"'";
		}
		//审核状态 班组地质组判断
		if("15".equals(deptype)){
			if(!shstatus.equals("")&&shstatus!=null&&!shstatus.equals("undefined")&&!shstatus.equals("全部")){
				if("已审核".equals(shstatus)){
					formTable=formTable+" and CLASS_CHECK_DATE is not null";
				}else{
					formTable=formTable+" and CLASS_CHECK_DATE is  null";
				}
			}
		}else{
			if(!shstatus.equals("")&&shstatus!=null&&!shstatus.equals("undefined")&&!shstatus.equals("全部")){
				if("已审核".equals(shstatus)){
					formTable=formTable+" and GEOLOGY_CHECK_DATE is not null";
				}else{
					formTable=formTable+" and GEOLOGY_CHECK_DATE is  null";
				}
				
			}
		}
	
		if(!well.equals("")&&well!=null&&!well.equals("undefined")){
			formTable=formTable+" and WELL_NAME like '%"+well+"%'";
		}
		Date today = new Date();
		String nowdate = DateBean.getTimeStr(today);
		
		if("".equals(startDate)){
			startDate = nowdate.substring(0, 10) + " 00:00:00";
		}else{
			startDate = startDate + " 00:00:00";
		}
		if("".equals(endDate)){
			endDate = nowdate.substring(0, 10) + " 23:59:59";
		}else{
			endDate = endDate + " 23:59:59";
		}
		formTable=formTable+" and REPORT_DATE between TO_DATE('"+startDate+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+endDate+"','YYYY-MM-DD HH24:MI:SS')";
		String cloumnsName[];
		if (!"export".equals(totalNumf)){
		//if("search".equals("search")){  //search 参数
//			String[] cloumnsName1 = {"REPORT_DATE","JHMC","PROC_TIME_RATION","STROKE","JIG_FREQUENCY","FLOW_NIPPLE","TUBING_PRES","CASING_PRES","OIL_TEMP","LIQUID_OUTPUT","sampling","RUNTIME","PUMP_ERROR_TIME","PUMP_ERROR_DESC","REMARK"};
			String[] cloumnsName1 = {
			//井号
			"WELL_NAME",
			//日期 
			"REPORT_DATE",
			//单位 
			"OILSTATIONNAME",
			//班组 
			"TEAMNAME",
			//转油站
			"BLOCKSTATIONNAME",
			//管汇
			"MANIFOLD",
			//生产时间(h) 
			"PROC_TIME_RATION",
			//冲程(m) 
			"STROKE",
			//冲次(/min)
			"JIG_FREQUENCY",
			//油嘴(mm)
			"FLOW_NIPPLE",
			//油压(Mpa)
			"TUBING_PRES",
			//套压(Mpa) 
			"CASING_PRES",
			//回压(Mpa)
			"BACK_PRES",
			//油温(℃)
			"OIL_TEMP",
			//日产液量(t) 
			"LIQUID_OUTPUT",
			"LIQUID_FLAG",
			//日产气量(m3)
			"GAS_OUTPUT",
			//取样(油水)
			"SAMPLING",
			//抽油机运转时间(h) 
			"RUNTIME",
			//抽油机故障时间(h)
			"PUMP_ERROR_TIME",
			//抽油机故障描述 
			"PUMP_ERROR_DESC",
			//备注 
			"REMARK",
			//班组审核人
			"CLASS_CHECK_OPER",
			//班组审核时间
			"CLASS_CHECK_DATE",
			//地质组审核人
			"GEOLOGY_CHECK_OPER",
			//地质组审核时间
			"GEOLOGY_CHECK_DATE",
			//最后修改人
			"RLAST_OPER",
			//最后修改时间 
			"RLAST_ODATE",
			//井号组织结构ID 
			"RULE_ORG_ID",
			//班组ID 
			"TEAM_ORG_ID",
			//转油站ID 
			"BLOCKSTATIONID",
			//管汇 
			"GH_ID",
			"RPD_RULE_WELL_PROD_ID"};
			cloumnsName = cloumnsName1;
		}else{
//			String[] cloumnsName2 = {"REPORT_DATE","JHMC","PROC_TIME_RATION","STROKE","JIG_FREQUENCY","FLOW_NIPPLE","TUBING_PRES","CASING_PRES","OIL_TEMP","LIQUID_OUTPUT","sampling","RUNTIME","PUMP_ERROR_TIME","PUMP_ERROR_DESC","REMARK"};
			String[] cloumnsName2 = {
				//井号
				"WELL_NAME",
				//日期 
				"REPORT_DATE",
				//单位 
				"OILSTATIONNAME",
				//班组 
				"TEAMNAME",
				//转油站
				"BLOCKSTATIONNAME",
				//管汇
				"MANIFOLD",
				//生产时间(h) 
				"PROC_TIME_RATION",
				//冲程(m) 
				"STROKE",
				//冲次(/min)
				"JIG_FREQUENCY",
				//油嘴(mm)
				"FLOW_NIPPLE",
				//油压(Mpa)
				"TUBING_PRES",
				//套压(Mpa) 
				"CASING_PRES",
				//回压(Mpa)
				"BACK_PRES",
				//油温(℃)
				"OIL_TEMP",
				//日产液量(t) 
				"LIQUID_OUTPUT",
				//日产气量(m3)
				"GAS_OUTPUT",
				//取样(油水)
				"SAMPLING",
				//抽油机运转时间(h) 
				"RUNTIME",
				//抽油机故障时间(h)
				"PUMP_ERROR_TIME",
				//抽油机故障描述 
				"PUMP_ERROR_DESC",
				//备注 
				"REMARK"};
			cloumnsName = cloumnsName2;
		}
		//String[] cloumnsName1 = {"REPORT_DATE","JHMC","PROC_TIME_RATION","STROKE","JIG_FREQUENCY","FLOW_NIPPLE","TUBING_PRES","CASING_PRES","OIL_TEMP","LIQUID_OUTPUT","sampling","RUNTIME","PUMP_ERROR_TIME","PUMP_ERROR_DESC","REMARK"};
		if ("export".equals(totalNumf)){
			for (String cn : cloumnsName) {
				cloums +=  cn+",";	
			}
			
			cloums = cloums.substring(0, cloums.length()-1);
		}else{
			for (String cn : cloumnsName) {
				
				if ("CLASS_CHECK_DATE".equals(cn) ) {
					cloums += " to_char(CLASS_CHECK_DATE,'YYYY-MM-DD HH24:MI:SS') as CLASS_CHECK_DATE ,";
				}else if ("GEOLOGY_CHECK_DATE".equals(cn) ) {
					cloums += " to_char(GEOLOGY_CHECK_DATE,'YYYY-MM-DD HH24:MI:SS') as GEOLOGY_CHECK_DATE ,";
				}else if ("RLAST_ODATE".equals(cn) ) {
					cloums += " to_char(RLAST_ODATE,'YYYY-MM-DD HH24:MI:SS') as RLAST_ODATE ,";
				}else{
					cloums +=  cn+",";	
				}
			}
			
			cloums = cloums.substring(0, cloums.length()-1);
		}
		
	
		
		String wellInfo = cloums + formTable;
		//获取总条数
		int total = 0;
		if ("1".equals(totalNum)) {
			total = 1;
		} else {
			totalNum += formTable;
			total = ruleWellDao.getCounts(totalNum);
		}
		if ("totalNum".equals(totalNumf)) {
			map.put("totalNum", total + "");
			return map;
		}
		//排序
		if(!"".equals(sort) && !"JHMC".equals(sort)){
			for (String cloumn : cloumnsName) {
				if(cloumn.equals(sort)){
					wellInfo +=" order by " + cloumn + " " + order;
					break;
				}
			}
		}else {
			wellInfo +=" order by nlssort(WELL_NAME, 'NLS_SORT=SCHINESE_STROKE_M'),REPORT_DATE desc ";
		}
		List<Object[]> lo =null;
		try {
			if ("export".equals(totalNumf)) {
				lo = ruleWellDao.queryInfo(wellInfo);
				map.put("list", lo);
				return map;
			}
			else {
				//计算分页
				PageControl control = new PageControl();
				//当前页
				control.setInt_num(rowsPerpage);
				control.init(pageNo, total);
				//开始条数
				int start = control.getStart()-1;
				lo = ruleWellDao.searchRuleWellRPD(wellInfo,start,rowsPerpage, cloumnsName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		JSONObject tjo = null;
		JSONArray jsonArr = null;
		if (lo != null && 0 < lo.size()) {
			// 生成树节点json文档
			jsonArr = new JSONArray();
			for (Object[] o : lo) {
				tjo = new JSONObject();

				for (int i = 0; i < o.length; i++) {

					if(i == 14){
						
						if (!"".equals(CommonsUtil.getClearNullData(o[15])) ) {
							if(CommonsUtil.getClearNullData(o[15]).equals("1")){
								tjo.put(cloumnsName[i], "<span style='color: green;'>"+CommonsUtil.getClearNullData(o[i])+"</span>");
								
							}else if(CommonsUtil.getClearNullData(o[15]).toString().equals("2")){
								tjo.put(cloumnsName[i], "<span style='color: purple;'>"+CommonsUtil.getClearNullData(o[i])+"</span>");
							}else if(CommonsUtil.getClearNullData(o[15]).toString().equals("3")){
								tjo.put(cloumnsName[i], "<span style='color: red;'>"+CommonsUtil.getClearNullData(o[i])+"</span>");
								
							}else{
								tjo.put(cloumnsName[i], CommonsUtil.getClearNullData(o[i]));
							}
						}else{
							tjo.put(cloumnsName[i], CommonsUtil.getClearNullData(o[i]));
						}
					}else{
						tjo.put(cloumnsName[i], CommonsUtil.getClearNullData(o[i]));
					}
					
						
					
				
				}
				jsonArr.add(tjo);
			}
		}
		tjo = new JSONObject();
		tjo.put("Rows",jsonArr);
		tjo.put("Total",total);
		map.put("json", tjo);
		return map;
	}
	@Override
	public JSONArray queryTeamInfo(String oilid) throws Exception {
		// TODO Auto-generated method stub
		JSONArray jsonArr = null;
		JSONObject jsobj = null;
		String sql = "select s.org_id,s.blockstation_name from PC_CD_STATION_T s left join (select o.* from pc_cd_org_t o left join PC_CD_OILDRILLING_STATION_T oil on o.pid=oil.org_id where o.pid='" + oilid + "') ss on s.org_id=ss.org_id where s.org_id=ss.org_id  order by nlssort(s.blockstation_name,'NLS_SORT=SCHINESE_STROKE_M')";
		
		List<Object[]> dataSet = ruleWellDao.queryInfo(sql);
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
	@SuppressWarnings("unchecked")
	@Override
	public List<PcRpdRuleWellProdT> searchOnlyNameRq(String orgId, String rq, String rpdId) throws Exception {
		//List<PcRpdRuleWellProdT> rpdList =null;
		String hql =" from PcRpdRuleWellProdT a  where 1=1";
		if( orgId !=null && !"".equals(orgId)){
			hql +=" and   a.orgId= '"+orgId+"'";
		}
		if(rq !=null && !"".equals(rq)){
			hql +=" and   a.reportDate= to_date('"+rq+"' , 'YYYY-MM-DD')";
		}
		if( rpdId !=null && !"".equals(rpdId)){
			hql += " and  a.rpdRuleWellProdId= '"+rpdId+"'";
		}
		//rpdList =  ruleWellDao.searchOnlyNameRq(hql);
//		List<?> list = null;
//		list = commonDao.searchClassQuery(hql);
//		return (List<PcRpdRuleWellProdT>) list;
		return (List<PcRpdRuleWellProdT>) commonDao.searchClassQuery(hql);
	}
	@Override
	public String searchRuleOrgId(String wellName)throws Exception {
		String sql = " select a.org_id  from  pc_cd_rule_well_t a  where a.well_name='"+wellName+"'";
		List<Object[]> objList = new ArrayList<Object[]>();
		objList = ruleWellDao.searchData(sql);
		Object  orgId = objList.get(0);
		return (String)orgId;
	}
	@Override
	public boolean saveRpdRull(PcRpdRuleWellProdT rpdR) throws Exception {
		List<PcRpdRuleWellProdT> rpdList = new ArrayList<PcRpdRuleWellProdT>();
	  		rpdList.add(rpdR);
		return commonDao.addOrUpdateDatas(rpdList);
//		ruleWellDao.saveRpdRull(rpdR);
	}
	@Override
	public boolean saveRpdRullB(PcRpdRuleWellProdbT rpdbR) throws Exception {
		return ruleWellDao.saveRpdRullB(rpdbR);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<PcRpdRuleWellProdbT> searchOnlyNameRqB(String orgId, String rq,
			String rpdbId) throws Exception {
		//List<PcRpdRuleWellProdbT> rpdList =null;
		String hql ="FROM PcRpdRuleWellProdbT a  where 1=1 ";
		if( orgId !=null && !"".equals(orgId)){
			hql +=" and   a.orgId= '"+orgId+"'";
		}
		if(rq !=null && !"".equals(rq)){
			hql +=" and   a.reportDate= to_date('"+rq+"' , 'YYYY-MM-DD')";
		}
		if( rpdbId !=null && !"".equals(rpdbId)){
			hql += " and  a.rpdRuleWellProdId= '"+rpdbId+"'";
		}
//		rpdList =  
//			ruleWellDao.searchOnlyNameRqB(hql);
		return (List<PcRpdRuleWellProdbT>) commonDao.searchClassQuery(hql);
	}
	@Override
	public boolean removeRullRPDId(String rullRPDId ,String deptype) throws Exception {
		String sql ="";
		if(deptype.equals("15")){
			sql = "delete  from   pc_rpd_rule_well_prod_t  a where a.rpd_rule_well_prod_id ='"+rullRPDId+"'";
		}else{
			sql = "delete  from   pc_rpd_rule_well_prodb_t  a where a.rpd_rule_well_prod_id ='"+rullRPDId+"'";
		}
		return ruleWellDao.removeRullRPDId(sql);
	}
	@Override
	public List<String> Dataready(String proceduresName, String date, String param) throws Exception {
		return commonDao.getProcedures(proceduresName, date, param);
	}
	@Override
	public List<String> Automate(String proceduresName, String date,
			String userid,String param) throws Exception {
		
		
		String timeCalssql = "select * from PC_CD_TRIGGERED_PROCESS_T where 1=1 and parameter_name='"+param+"'";
		int calcNum = -16;
		List<Object[]> dateNum = commonDao.searchEverySql(timeCalssql);
		if(dateNum != null && dateNum.size()>0){
			calcNum = Integer.parseInt(String.valueOf(dateNum.get(0)[1]));
		}
		return commonDao.getProcedures(proceduresName, date,userid,calcNum);
	}
}
