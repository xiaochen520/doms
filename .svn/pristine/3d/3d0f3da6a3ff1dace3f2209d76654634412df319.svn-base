package com.echo.service.impl;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.beans.PageControl;
import com.echo.dao.PageDao;
import com.echo.dao.GasstationDao;
import com.echo.dto.PcCdOrgT;
import com.echo.dto.PcCdRuleWellT;
import com.echo.dto.PcCdGasstation;
import com.echo.service.GasstationService;

public class GasstationServiceImpl implements GasstationService{
	private GasstationDao gasstationDao;
	private PageDao pageDao;
	public GasstationDao getGasstationDao() {
		return gasstationDao;
	}
	public void setGasstationDao(GasstationDao gasstationDao) {
		this.gasstationDao = gasstationDao;
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
	public JSONObject queryStationBasicInfo(String jrbz,String qk,String zh,String jh,String jm,String type,String dyearC, int pageNo,String sort,String order,int rowsPerpage) throws Exception{
		JSONArray jsonArr = null;
		JSONObject tjo = null;
		String cloums = "select ";
		String formTable = " from PC_CD_GAS_STATION_V t where 1=1";
		String totalNum = "select count(*)";

		if(!qk.equals("")&&qk!=null&&!qk.equals("undefined")&!qk.equals("全部")){
			formTable=formTable+" and OILDRILLING_NAME='"+qk+"'";
		}
		if(!zh.equals("")&&zh!=null&&!zh.equals("undefined")){
			//formTable=formTable+" and AREABLOCK='"+zh+"'";
		}
		if(!jh.equals("")&&jh!=null&&!jh.equals("undefined")){
			formTable=formTable+" and qkmc='"+jh+"'";
		}
		if(!jm.equals("")&&jh!=null&&!jm.equals("undefined")){
			formTable=formTable+" and blockstation_name='"+jm+"'";
		}
		if(!dyearC.equals("")&&dyearC!=null&&!dyearC.equals("undefined")){
			formTable=formTable+" and DYEAR='"+dyearC+"'";
		}
		if(!jrbz.equals("")&&jrbz!=null&&!jrbz.equals("全部")){
			formTable=formTable+" and jrbz='"+jrbz+"'";
		}
		String[] cloumnsName = {"BLOCKSTATIONID","GH_ID","ORG_ID","A2ID","BLOCKSTATION_NAME","CODE","QKMC","QKID","OILDRILLING_NAME","BS_TYPE","GHS","CYGS","CYGGG","ZYBS","JRBZ","PROD_SNS","STATUS_VALUE","COMMISSIONING_DATE","DYEAR","RLAST_OPER","RLAST_ODATE","REMARK","LJJDID","LJJD_S"};
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
			boilersInfo +=" order by nlssort(t.BLOCKSTATION_NAME, 'NLS_SORT=SCHINESE_STROKE_M') ";
		}
		
		//计算分页
		PageControl control = new PageControl();
		//当前页
		control.setInt_num(rowsPerpage);
		control.init(pageNo, total);
		//开始条数
		int start = control.getStart()-1;
		
		List<Object[]> lo = gasstationDao.queryStationBasicInfo(boilersInfo,start,rowsPerpage,cloumnsName);
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
					
					if(i==13){
						if(o[13].equals("1")){
							tjo.put(cloumnsName[i], "接入");
						}else if(o[13].equals("0")){
							tjo.put(cloumnsName[i], "未接入");
						}else{
							tjo.put(cloumnsName[i], o[i].toString());}
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
	
	public JSONArray queryOilSationInfo() {
		JSONArray jsonArr = null;
		JSONObject jsobj = null;
		String sql = "select oil.org_id ,oil.OILDRILLING_STATION_NAME from PC_CD_OILDRILLING_STATION_T oil order by nlssort(oil.OILDRILLING_STATION_NAME,'NLS_SORT=SCHINESE_STROKE_M')";
		
		List<Object[]> dataSet = gasstationDao.queryInfo(sql);
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
		if ("".equals(arg)) {//查询所有含锅炉的区块
			sql = "select distinct a.qkid,a.qkmc from pc_cd_area_info_t a  order by nlssort(a.qkmc,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		List<Object[]> dataSet = gasstationDao.queryInfo(sql);
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
	public JSONArray queryStationInfo(String combinationid,String oilid,String areaid,String selecteValue) {
		JSONArray jsonArr = null;
		JSONObject jsobj = null;
		//按采油站查询 注转站
		//oilid 不能等于1
		String sql = "select r.ORG_ID,r.STRUCTURENAME from PC_CD_GAS_STATIONTREE_V r where r.STRUCTURETYPE='12' order by nlssort(r.STRUCTURENAME,'NLS_SORT=SCHINESE_STROKE_M')";
		/*//按联合站查注转站
		if (!"".equals(combinationid)) {
			sql = "select s.stationid,s.station_name from PC_CD_GAS_STATION_t s left join pc_cd_org_t o on s.org_id=o.org_id left join pc_cd_org_t o1 on o.pid=o1.org_id where o1.pid='" + combinationid + "' order by nlssort(s.blockstation_name,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		if ("".equals(selecteValue)) {
			sql = "select w.stationid,w.station_name from PC_CD_GAS_STATION_t w order by nlssort(w.station_name,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		if ("2".equals(selecteValue) && !"".equals(areaid) || "1".equals(oilid)) {//按区块查询 注转站
			sql = "select distinct s.stationid,s.station_name from PC_CD_GAS_STATION_t s where s.qkid='" + areaid + "' order by nlssort(s.blockstation_name,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		
		//按采油站和区块查询 注转站
		if ("1".equals(selecteValue) && !"".equals(oilid) && !"".equals(areaid)) {
			sql = "select s.blockstationid,s.blockstation_name from pc_cd_gas_station_t s left join pc_cd_org_t o on o.org_id=s.org_id left join pc_cd_area_info_t a on a.qkid=s.qkid  where o.pid='" + oilid + "' and a.qkid='" + areaid + "' order by nlssort(s.blockstation_name,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		//按联合站和区块查询 注转站
		if ("1".equals(selecteValue) && !"".equals(combinationid) && !"".equals(areaid)) {
			sql = "select s.blockstationid,s.blockstation_name from pc_cd_gas_station_t s left join pc_cd_org_t o on s.org_id=o.org_id left join pc_cd_org_t o1 on o.pid=o1.org_id left join pc_cd_area_info_t a on a.qkid=s.qkid where o1.pid='" + combinationid + "' and a.qkid='" + areaid + "' order by nlssort(s.blockstation_name,'NLS_SORT=SCHINESE_STROKE_M')";
		}*/
		if (!"".equals(areaid)) {
			sql = "select r.ORG_ID,r.STRUCTURENAME from PC_CD_GAS_STATIONTREE_V r left join pc_cd_gas_station_t gst on r.org_id=gst.org_id where r.org_id=gst.org_id and gst.qkid='" + areaid + "' and r.STRUCTURETYPE='12' order by nlssort(r.STRUCTURENAME,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		if (!"".equals(oilid)) {
			sql = "select r.ORG_ID,r.STRUCTURENAME from PC_CD_GAS_STATIONTREE_V r where r.STRUCTURETYPE='12' and r.pid='" + oilid + "' order by nlssort(r.STRUCTURENAME,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		if (!"".equals(areaid) && !"".equals(oilid)) {
			sql = "select r.ORG_ID,r.STRUCTURENAME from PC_CD_GAS_STATIONTREE_V r left join pc_cd_gas_station_t gst on r.org_id=gst.org_id where r.org_id=gst.org_id and gst.qkid='" + areaid + "' and r.pid='" + oilid + "' and r.STRUCTURETYPE='12' order by nlssort(r.STRUCTURENAME,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		List<Object[]> dataSet = gasstationDao.queryInfo(sql);
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
//			org = (PcCdOrgT) gasstationDao.queryOrg(grzh);
//			org.setStructurename(boilerName);
//			boiler = (PcCdBoilerT) gasstationDao.queryBoiler(boilerId);
//		}
	public boolean saveOrUpdate(String zzzmc2,String stationid2,String org_id2,byte ghs2,byte cygs2,String cyggg2,long zybs2,long jrbz2,String rlastOper,Date rlastOdate,String zzzlx2,String remark){
		PcCdGasstation station = new PcCdGasstation();
		PcCdOrgT org = new PcCdOrgT();
		if(!"".equals(stationid2)){//有注转站ID为更新操作
			org = (PcCdOrgT) gasstationDao.queryOrg(org_id2);
			org.setStructurename(stationid2);
			station = (PcCdGasstation) gasstationDao.queryInfo(zzzmc2);
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
	
		station.setStationName(zzzmc2);
//		station.setGasstationype(stationid2);
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
//		gasstationDao.addOrModifyBoilerOrgInfo(boiler);
		return false;
	}
	public boolean removeStationInfo(String arg){
		String[] sqls = new String[2];
		sqls[0] = " DELETE from pc_cd_gas_station_t d where d.org_id = '"+arg+"'";
		sqls[1] = " DELETE from pc_cd_org_t t where t.org_id = '"+arg+"'";
		
		return gasstationDao.removeStationInfo(sqls);
	}
	//添加
	public boolean addSta(PcCdGasstation sta) throws Exception {
		return gasstationDao.save(sta);
		}
	//更新
	public boolean updateSta(PcCdGasstation sta) throws Exception {
		boolean flag = true;
		try {
			flag = gasstationDao.updateDep(sta);
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}
	public List<PcCdGasstation> searchstatinById(String stationid,
			String stationName) throws Exception {
		List<PcCdGasstation> stationList = null;
		String hql = "FROM PcCdGasstation u WHERE 1=1 ";
		if(stationid != null && !"".equals(stationid) ){
			hql += " and u.stationid = '"+stationid+"' ";
		}
		if(stationName != null && !"".equals(stationName) ){
			hql += " and u.stationName = '"+stationName+"'";
		}
		stationList = gasstationDao.searchStations(hql);
		return stationList;
	}

	public List<PcCdGasstation> searchstatinByName(String stationName)throws Exception {
		List<PcCdGasstation> stationList = null;
		String hql = "FROM PcCdGasstation u WHERE 1=1 ";
		
		if(stationName != null && !"".equals(stationName) ){
			hql += " and u.stationName = '"+stationName+"'";
		}
		stationList = gasstationDao.searchblockstationName(hql);
		return stationList;
	}

	public JSONArray searchStatusValue() {
		JSONArray jsonArr = null;
		JSONObject jsobj = null;
		String sql = "select t.prod_sva,t.prod_sns from PC_CD_PRODUC_STATUS_SAGD_T t";
		List<Object[]> dataSet = gasstationDao.queryInfo(sql);
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
	public JSONArray searchSationInfo() {
		JSONArray jsonArr = new JSONArray();;
		JSONObject jsobj = null;
		String oilSql = "select oil.org_id ,oil.OILDRILLING_STATION_NAME from PC_CD_OILDRILLING_STATION_T oil  order by nlssort(oil.OILDRILLING_STATION_NAME,'NLS_SORT=SCHINESE_STROKE_M')";
		List<Object[]> oilSet = gasstationDao.queryInfo(oilSql);
		String comSql = "select cs.org_id,cs.combination_station_name from PC_CD_COMBINATION_STATION_T cs order by nlssort(cs.combination_station_name,'NLS_SORT=SCHINESE_STROKE_M')";
		List<Object[]> comSet = gasstationDao.queryInfo(comSql);
		if(oilSet != null && oilSet.size()>0){
			for(Object[] entry : oilSet){
				jsobj = new JSONObject();
				jsobj.put("text", entry[1]);
				jsobj.put("id",entry[0]);
				jsonArr.add(jsobj);
			}
		}
		if(comSet != null && comSet.size()>0){
			for(Object[] entry : comSet){
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
		String oilSql = "select r.ORG_ID,r.STRUCTURENAME from PC_CD_GAS_STATIONTREE_V r where r.STRUCTURETYPE='4' order by nlssort(r.STRUCTURENAME,'NLS_SORT=SCHINESE_STROKE_M')";
		String areaSql = "select distinct a.qkid,a.qkmc from pc_cd_area_info_t a left join pc_cd_gas_station_t r on a.qkid=r.qkid where a.qkid=r.qkid order by nlssort(a.qkmc,'NLS_SORT=SCHINESE_STROKE_M')";
		String stationSql = "select w.stationid,w.station_name from PC_CD_GAS_STATION_t w order by nlssort(w.station_name,'NLS_SORT=SCHINESE_STROKE_M')";
		
		JSONArray oilJsonArr = null;
		JSONArray areaJsonArr = null;
		JSONArray stationArr = null;
		JSONObject jsobj = null;
		List<Object[]> oilSet = gasstationDao.queryInfo(oilSql);
		List<Object[]> areaSet = gasstationDao.queryInfo(areaSql);
		List<Object[]> stationSet = gasstationDao.queryInfo(stationSql);
		
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
	
	public JSONArray gasstationype() {
		JSONArray jsonArr = null;
		JSONObject jsobj = null;
		String sql = "select * from PC_CD_PRODUC_STATUS_SAGD_T t";
		List<Object[]> dataSet = gasstationDao.queryInfo(sql);
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

}
