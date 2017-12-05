package com.echo.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.echo.beans.PageControl;
import com.echo.dao.ManifoldBasicDao;
import com.echo.dao.PageDao;
import com.echo.dto.PcCdBoilerT;
import com.echo.dto.PcCdManifoldT;
import com.echo.dto.PcCdOrgT;
import com.echo.service.ManifoldBasicService;

public class ManifoldBasicServiceImpl implements ManifoldBasicService {
	private ManifoldBasicDao manifoldBasicDao;
	private PageDao pageDao;

	public void setPageDao(PageDao pageDao) {
		this.pageDao = pageDao;
	}
	public void setPcdDao(PageDao pageDao) {
		this.pageDao = pageDao;
	}

	public ManifoldBasicDao getManifoldBasicDao() {
		return manifoldBasicDao;
	}
	public void setManifoldBasicDao(ManifoldBasicDao manifoldBasicDao) {
		this.manifoldBasicDao = manifoldBasicDao;
	}
	public JSONObject queryManifoldBasicInfo(String qk,String zh,String jh,String name ,String jrbz1,String dyearC,String teamGROUP, int pageNo,String sort,String order,int rowsPerpage) {
		JSONArray jsonArr = null;
		JSONObject tjo = null;
		String cloums = "select   ";
		String formTable = " from pc_cd_manifold_info_v  where 1=1 ";
		String totalNum = "select count(*) ";
		if(!qk.equals("")&&qk!=null&&!qk.equals("undefined")&!qk.equals("全部")){
			formTable=formTable+" and OILSTATIONNAME='"+qk+"'";
		}
		
		if(!jh.equals("")&&jh!=null&&!jh.equals("undefined")&!jh.equals("全部")){
			formTable=formTable+" and BLOCKSTATIONNAME='"+jh+"'";
		}
		if(!name.equals("")&&name!=null&&!name.equals("undefined")&!name.equals("全部")){
			formTable=formTable+" and GHMC like '%"+name+"%'";
		}
		if(!zh.equals("")&&zh!=null&&!zh.equals("undefined")&!zh.equals("全部")){
			formTable=formTable+" and ORG_ID='"+zh+"'";
		}

		if(!jrbz1.equals("") && jrbz1 !=null && !jrbz1.equals("udnefiend")&&!jrbz1.equals("全部")){
			formTable=formTable+" and jrbz='"+jrbz1+"'";
		}
		
		if(!dyearC.equals("") && dyearC !=null && !dyearC.equals("udnefiend")){
			formTable=formTable+" and DYEAR='"+dyearC+"'";
		}
		
		if(!teamGROUP.equals("") && teamGROUP !=null && !teamGROUP.equals("udnefiend")){
			formTable=formTable+" and teamGroup='"+teamGROUP+"'";
		}
		
		String[] cloumnsName = {"MANIFOLDID","ORG_ID","QKID","A2ID","GHMC","GHDM","DTFS","DTFMC1","DTFTDS1","DTFMC2","DTFTDS2","INSTRU_STVA","JRBZ","STATUS_VALUE","COMMISSIONING_DATE","DYEAR","CODE",
				"RLAST_OPER","RLAST_ODATE","REMARK","OILSTATIONNAME","AREABLOCK","BLOCKSTATIONNAME","teamGroup","PROD_SNS","GRZHID","BRANCHING","BRANCHINGID","LJJDID","LJJD_S","JLATIONNAME","PID2"};
		String kk="MANIFOLDID";
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
		if(!"".equals(sort) && !"ghmc".equals(sort)){
			for (String cloumn : cloumnsName) {
				if(cloumn.equals(sort)){
					boilersInfo +=" order by " + cloumn + " " + order;
					break;
				}
				
			}
		}
		else {
			boilersInfo +=" order by nlssort(ghmc, 'NLS_SORT=SCHINESE_STROKE_M') ";
		}
		//计算分页
		PageControl control = new PageControl();
		//当前页
		control.setInt_num(rowsPerpage);
		control.init(pageNo, total);
		//开始条数
		int start = control.getStart()-1;
		
		List<Object[]> lo = manifoldBasicDao.queryManifoldBasicInfo(boilersInfo,start,rowsPerpage);
		if (lo != null && 0 < lo.size()) {
			// 生成树节点json文档
			jsonArr = new JSONArray();
			for (Object[] o : lo) {
				tjo = new JSONObject();
				
				for (int i = 0; i < o.length; i++) {
					if (o[i] == null) {
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
	public JSONArray queryOilSationInfo() {
		JSONArray jsonArr = null;
		JSONObject jsobj = null;
		//查询所有包含锅炉的采油站
		String sql = "select distinct oil.org_id ,oil.OILDRILLING_STATION_NAME from PC_CD_OILDRILLING_STATION_T oil,(select o.pid from pc_cd_org_t o left join PC_CD_STATION_T s on s.org_id=o.org_id where s.org_id=o.org_id and s.bs_type='4') o1 where o1.pid=oil.org_id  order by nlssort(oil.OILDRILLING_STATION_NAME,'NLS_SORT=SCHINESE_STROKE_M')";
		
		List<Object[]> dataSet = manifoldBasicDao.queryInfo(sql);
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
		String sql = "select distinct a.qkid,a.qkmc from pc_cd_area_info_t a left join (select s.qkid from PC_CD_STATION_T s left join (select o.org_id from Pc_Cd_Org_t o where o.pid='" + arg + "') o1 on o1.org_id=s.org_id where o1.org_id=s.org_id and s.bs_type='4') q on q.qkid=a.qkid where q.qkid=a.qkid  order by nlssort(a.qkmc,'NLS_SORT=SCHINESE_STROKE_M')";
		if ("".equals(arg)) {//查询所有含锅炉的区块
			sql = "select distinct a.qkid,a.qkmc from pc_cd_area_info_t a left join PC_CD_STATION_T s on a.qkid=s.qkid and s.bs_type='4' where a.qkid=s.qkid and s.bs_type='4'  order by nlssort(a.qkmc,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		List<Object[]> dataSet = manifoldBasicDao.queryInfo(sql);
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
		//按采油站查询 注转站
		//oilid 不能等于1
		String sql = "select s.org_id,s.blockstation_name from PC_CD_STATION_T s left join (select o.* from pc_cd_org_t o left join PC_CD_OILDRILLING_STATION_T oil on o.pid=oil.org_id where o.pid='" + oilid + "') ss on s.org_id=ss.org_id where s.org_id=ss.org_id order by nlssort(s.blockstation_name,'NLS_SORT=SCHINESE_STROKE_M')";
		
		if ("2".equals(selecteValue) && !"".equals(oilid) && !"".equals(areaid)) {//按区块查询 注转站
			sql = "select s.org_id,s.blockstation_name from PC_CD_STATION_T s where s.qkid='" + areaid + "' order by nlssort(s.blockstation_name,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		//按采油站和区块查询 注转站
		if ("1".equals(selecteValue) && !"".equals(oilid) && !"".equals(areaid)) {
			sql = "select s.org_id,s.blockstation_name from PC_CD_STATION_T s left join (select o.org_id from Pc_Cd_Org_t o where o.pid='" + oilid + "') o1 on s.org_id=o1.org_id left join pc_cd_area_info_t a on a.qkid=s.qkid where s.org_id=o1.org_id and s.qkid='" + areaid + "' order by nlssort(s.blockstation_name,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		List<Object[]> dataSet = manifoldBasicDao.queryInfo(sql);
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
	
	public JSONArray queryManifoldNameInfo(String arg,String ghorg) {
		JSONArray jsonArr = null;
		JSONObject jsobj = null;
		String sql = "select t.manifoldid,t.ghmc from PC_CD_MANIFOLD_T t left join pc_cd_org_t o on t.org_id=o.org_id where o.pid='" + arg + "' order by nlssort(t.ghmc,'NLS_SORT=SCHINESE_STROKE_M')";
		if ("".equals(arg) || "all".equals(ghorg)) {//全部的管汇
			sql = "select t.manifoldid,t.ghmc from PC_CD_MANIFOLD_T t order by nlssort(t.ghmc,'NLS_SORT=SCHINESE_STROKE_M')";
		//	sql = "select distinct r.manifold  from pc_cd_well_sagd_v r order by nlssort(r.manifold,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		if ("orgid".equals(ghorg)) {//管汇
			sql = "select t.org_id,t.ghmc from PC_CD_MANIFOLD_T t left join pc_cd_org_t o on t.org_id=o.org_id where o.pid='" + arg + "' order by nlssort(t.ghmc,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		List<Object[]> dataSet = manifoldBasicDao.queryInfo(sql);
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
	
	@SuppressWarnings("unused")
	public boolean saveOrUpdate(String grzh,String boilerid2,String boilername2,String boilertype2,String boilercode2,String rlastoper2,String rlastodate2,String remark2){
		PcCdBoilerT boiler = new PcCdBoilerT();
		PcCdOrgT org = new PcCdOrgT();
		if (!"".equals(boilerid2)) {//有锅炉id 为更新操作
			
		}
		
		return false;
	}
	public boolean removeManifoldBasicInfo(String manifoldid , String orgid) throws Exception{
		String[] sqls = new String[2];
		sqls[0]=" DELETE from pc_cd_manifold_t d where d.manifoldid = '"+manifoldid+"'";
		sqls[1]="DELETE from pc_cd_org_t t where t.org_id = '"+orgid+"'";
		return manifoldBasicDao.removeManifoldBasicInfo(sqls);
	}
	
	public JSONObject cascadeInit() {
		//所有含锅炉的采油站
		String oilSql = "select r.ORG_ID,r.STRUCTURENAME from PC_CD_MANIFOLDTREE_V r where r.STRUCTURETYPE='4' order by nlssort(r.STRUCTURENAME,'NLS_SORT=SCHINESE_STROKE_M')";
		//所有含锅炉的区块
		String areaSql = "select distinct a.qkid,a.qkmc from pc_cd_area_info_t a left join pc_cd_station_t s on a.qkid=s.qkid left join PC_CD_MANIFOLDTREE_V bv on s.org_id=bv.org_id where bv.structuretype='7' order by nlssort(a.qkmc,'NLS_SORT=SCHINESE_STROKE_M')";
		//所有含锅炉的注转站
		String stationSql = "select r.ORG_ID,r.STRUCTURENAME from PC_CD_MANIFOLDTREE_V r where r.STRUCTURETYPE='7' order by nlssort(r.STRUCTURENAME,'NLS_SORT=SCHINESE_STROKE_M')";
		//所有锅炉
		String ghmcSql = "select t.manifoldid,t.ghmc from PC_CD_MANIFOLD_T t order by nlssort(t.ghmc,'NLS_SORT=SCHINESE_STROKE_M')";
		//所有banzu
		String GroupSql = "select t.team_id ,t.team  from  pc_cd_team_t t    order by  nlssort(t.team, 'NLS_SORT=SCHINESE_STROKE_M')";
		JSONArray oilJsonArr = null;
		JSONArray areaJsonArr = null;
		JSONArray stationArr = null;
		JSONArray ghmcArr = null;
		JSONObject jsobj = null;
		JSONArray GroupArr = null;
		List<Object[]> oilSet = manifoldBasicDao.queryInfo(oilSql);
		List<Object[]> areaSet = manifoldBasicDao.queryInfo(areaSql);
		List<Object[]> stationSet = manifoldBasicDao.queryInfo(stationSql);
		List<Object[]> ghmcSet = manifoldBasicDao.queryInfo(ghmcSql);
		List<Object[]> GroupSet =  manifoldBasicDao.queryInfo(GroupSql);
		
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
		
		if(ghmcSet != null && ghmcSet.size()>0){
			ghmcArr = new JSONArray();   
			for(Object[] entry : ghmcSet){
				jsobj = new JSONObject();
				jsobj.put("text", entry[1]);
				jsobj.put("id",entry[0]);
				ghmcArr.add(jsobj);
			}
		}
		if(GroupSet != null && GroupSet.size()>0){
			GroupArr = new JSONArray();
			for (Object[] entry : GroupSet) {
					jsobj = new JSONObject();
					jsobj.put("text", entry[1]);
					jsobj.put("id",entry[0]);
					GroupArr.add(jsobj);
			}
		}
		
		jsobj = new JSONObject();
		jsobj.put("oiltext",oilJsonArr);
		jsobj.put("areatext",areaJsonArr);
		jsobj.put("stationtext",stationArr);
		jsobj.put("ghmctext",ghmcArr);
		jsobj.put("GroupText",GroupArr);
		return jsobj;
	}

		public List<Object[]> searchMani(String stationmc, String ghmc)throws Exception {
			String sql = "select * from ( "+
						"	select * from pc_cd_org_t "+
						"	start with structurename='"+stationmc+"'  "+
						"	connect by prior org_id = pid ) a where a.structurename = '"+ghmc+"'";
			
			List<Object[]> manilist = manifoldBasicDao.queryInfo(sql);
			return manilist;
	}
		
		public List<Object[]> searchManid(String ghmc)throws Exception {
			String sql = "select * from  pc_cd_org_t o where 1=1 and o.structurename='"+ghmc+"'";

			List<Object[]> manilist = manifoldBasicDao.queryInfo(sql);
			return manilist;
			
		}
		
		public List<Object[]> searchPidBymanifoldid(String id)throws Exception {
			String sql = "select * from  pc_cd_manifold_t o where 1=1 and o.org_id='"+id+"'";

			List<Object[]> manilist = manifoldBasicDao.queryInfo(sql);
			return manilist;
			
		}
		public List<Object[]> searchManiCheck(String stationid, String ghmc)
				throws Exception {
			//String sql = "select * from pc_cd_org_t o where 1=1 and o.structurename = '"+ghmc+"' and o.pid ='"+stationid+"'";
			String  sql ="select a.ghmc,a.manifoldid  from  pc_cd_manifold_t a  where a.ghmc='"+ghmc+"' ";
			List<Object[]> manilist = manifoldBasicDao.queryInfo(sql);
			return manilist;
		}
		public List<Object[]> searchStationByName(String stationName)
				throws Exception {
			List<Object[]> stationlist = manifoldBasicDao.queryInfo("select ORG_ID from PC_CD_STATION_T  s WHERE BLOCKSTATION_NAME='"+stationName+"'");
			return stationlist;
		}
		public List<PcCdManifoldT> searchManiByid(String id , String  name) throws Exception {
			List<PcCdManifoldT> manilist = null;
			String hql = "FROM PcCdManifoldT m WHERE 1=1 ";
			if(id != null && !"".equals(id)){
				hql += " and m.manifoldid ='"+id+"'";
			}
			if(name != null && !"".equals(name)){
				hql += " and m.ghmc ='"+name+"'";
			}
			manilist = manifoldBasicDao.searchManifold(hql);
			return manilist;
		}
		public boolean addManifold(PcCdManifoldT pcCdManifoldT)
				throws Exception {
			return manifoldBasicDao.addManifold(pcCdManifoldT);
		}
		public boolean updateManifold(PcCdManifoldT pcCdManifoldT)
				throws Exception {
			return manifoldBasicDao.updateManifold(pcCdManifoldT);
		}
		@Override
		public List<PcCdManifoldT> searchManiBydm(String ghdm2,
				String blockstationid) throws Exception {
			// TODO Auto-generated method stub
			List<PcCdManifoldT> manilist = new ArrayList<PcCdManifoldT>();
//			String hql = "FROM PcCdManifoldT m WHERE 1=1 ";
//			if(manifoldid != null && !"".equals(manifoldid)){
//				hql += " and m.manifoldid <>'"+manifoldid+"'";
//			}
//			if(ghdm2 != null && !"".equals(ghdm2)){
//				hql += " and m.ghdm ='"+ghdm2+"'";
//			}
			List<Object[]> maniFold = manifoldBasicDao.queryInfo("select m.qkid,m.areablock from pc_cd_manifold_info_v m where m.ghdm='" + ghdm2 + "'");
			List<Object[]> qk = manifoldBasicDao.queryInfo("select s.qkid,s.qkmc from pc_cd_station_v s where s.blockstationid='" + blockstationid + "'");

			//manilist = manifoldBasicDao.searchManifold(hql);
			if (maniFold != null && 0 < maniFold.size()) {
				for (Object[] o : maniFold) {
					if (o[0].equals(qk.get(0)[0])) {
						return null;
					}	
				}
			}
			return manilist;
		}
		@Override
		public HashMap<String, Object> searchOnExport(String oilationname,String blockstationname, String boilersName, String jrbz1, int pageNo, String sort, String order, int rowsPerpage,String dyearC,String totalNumFlag) throws Exception {

			JSONArray jsonArr = null;
			JSONObject tjo = null;
			HashMap<String,Object> map = new HashMap<String, Object>();
			String cloums = "select   ";
			String formTable = " from pc_cd_manifold_info_v  where 1=1 ";
			String totalNum = "select count(*) ";
			if(!oilationname.equals("")&&oilationname!=null&&!oilationname.equals("undefined")&!oilationname.equals("全部")){
				formTable=formTable+" and OILSTATIONNAME='"+oilationname+"'";
			}
			if(!blockstationname.equals("")&&blockstationname!=null&&!blockstationname.equals("undefined")&!blockstationname.equals("全部")){
				formTable=formTable+" and BLOCKSTATIONNAME='"+blockstationname+"'";
			}
			if(!boilersName.equals("")&&boilersName!=null&&!boilersName.equals("undefined")&!boilersName.equals("全部")){
				formTable=formTable+" and GHMC like '%"+boilersName+"%'";
			}
			if(!jrbz1.equals("") && jrbz1 !=null && !jrbz1.equals("udnefiend")&&!jrbz1.equals("全部")){
				formTable=formTable+" and jrbz='"+jrbz1+"'";
			}
			if(!dyearC.equals("") && dyearC !=null && !dyearC.equals("udnefiend")){
				formTable=formTable+" and DYEAR='"+dyearC+"'";
			}
			String[] cloumnsName = {"GHMC","OILSTATIONNAME","BLOCKSTATIONNAME","BRANCHING","GHDM","CODE","DTFS","DTFMC1","DTFTDS1","DTFMC2","DTFTDS2","PROD_SNS",
					"COMMISSIONING_DATE","DYEAR","DYEAR","INSTRU_STVA","JRBZ","LJJD_S","REMARK"};
			String kk="GHMC";
			for(int m=1;m<cloumnsName.length;m++){
				if("GHMC".equals(cloumnsName[m])){
					//kk=kk+","+"to_char(CJSJ,'YYYY-MM-DD hh24:mi:ss') as CJSJ";
				}else{
					kk=kk+","+cloumnsName[m];
				}
			}
			//String[] cloumnsName2 = {"GHMC","OILSTATIONNAME","BLOCKSTATIONNAME","BRANCHING","GHDM","CODE","DTFS","DTFMC1","DTFTDS1","DTFMC2","DTFTDS2","STATUS_VALUE","COMMISSIONING_DATE",
			//		"JRBZ","LJJD_S","REMARK"};
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
			if(!"".equals(sort) && !"ghmc".equals(sort)){
				for (String cloumn : cloumnsName) {
					if(cloumn.equals(sort)){
						thinOilWellRD +=" order by " + cloumn + " " + order;
						break;
					}
					
				}
			}
			else {
				thinOilWellRD +=" order by nlssort(ghmc, 'NLS_SORT=SCHINESE_STROKE_M') ";
			}
			//计算分页
			PageControl control = new PageControl();
			//当前页
			List<Object[]> lo = new ArrayList<Object[]>();
			control.setInt_num(rowsPerpage);
			if ("export".equals(totalNumFlag)) {
				lo = manifoldBasicDao.searchData(thinOilWellRD);
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
		public List<Object[]> searchQKID(String qkid,String ghdm2) throws Exception {
			//String sql = "select a.qkid,a.manifoldid  from pc_cd_GHDMverify_v a where a.manifoldid='"+manifoldid+"'  order by nlssort(a.qkid,'NLS_SORT=SCHINESE_STROKE_M') ";
			String sql = "select a.manifoldid,a.qkid ,a.ghdm from pc_cd_GHDMverify_v a where a.qkid='"+qkid+"' and a.ghdm='"+ghdm2+"'  order by nlssort(a.qkid,'NLS_SORT=SCHINESE_STROKE_M') ";
			List<Object[]> qdjArr = new ArrayList<Object[]>();
			qdjArr = manifoldBasicDao.searchQKID(sql);
			return qdjArr;
		}
		@Override
		//注转战ID manifoldid
		public String searchQUKUAI(String manifoldid) throws Exception {
			String sql = "select a.qkid  from pc_cd_station_t a where a.org_id='"+manifoldid+"'  order by nlssort(a.qkid,'NLS_SORT=SCHINESE_STROKE_M') ";
			List<Object[]> lo = new ArrayList<Object[]>();
			lo = manifoldBasicDao.searchData(sql);
			Object qkid = lo.get(0);
			return (String) qkid;
		}
		@Override
		public JSONArray queryTeamGInfo( String group) throws Exception {
			String sql ="select  a.org_id,a.team  from  pc_cd_team_t a order by nlssort(a.team,'NLS_SORT=SCHINESE_STROKE_M')";
		List<Object[]> oList = manifoldBasicDao.queryInfo(sql);
		JSONObject obj = null;
		JSONArray  jArr = null;
		if(oList !=null && oList.size()>0){
			jArr = new JSONArray();
			for (Object[] entry : oList) {
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
	
}