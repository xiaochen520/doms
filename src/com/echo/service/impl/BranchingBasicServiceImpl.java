package com.echo.service.impl;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.beans.PageControl;
import com.echo.dao.BranchingBasicDao;
import com.echo.dao.PageDao;
import com.echo.dto.PcCdBoilerT;
import com.echo.dto.PcCdBranchingT;
import com.echo.dto.PcCdOrgT;
import com.echo.service.BranchingBasicService;

public class BranchingBasicServiceImpl implements BranchingBasicService {
	private BranchingBasicDao branchingBasicDao;
	private PageDao pageDao;

	public void setPageDao(PageDao pageDao) {
		this.pageDao = pageDao;
	}
	public void setPcdDao(PageDao pageDao) {
		this.pageDao = pageDao;
	}

	public BranchingBasicDao getBranchingBasicDao() {
		return branchingBasicDao;
	}
	public void setBranchingBasicDao(BranchingBasicDao branchingBasicDao) {
		this.branchingBasicDao = branchingBasicDao;
	}
	public JSONObject queryBranchingBasicInfo(String qk,String zh,String jh, int pageNo,String sort,String order,int rowsPerpage) {
		JSONArray jsonArr = null;
		JSONObject tjo = null;
		String cloums = "select   ";
		String formTable = " from pc_cd_bran_info_v  where 1=1 ";
		String totalNum = "select count(*) ";
		if(!qk.equals("")&&qk!=null&&!qk.equals("undefined")&!qk.equals("全部")){
			formTable=formTable+" and COMBINATION_STATION='"+qk+"'";
		}
		if(!zh.equals("")&&zh!=null&&!zh.equals("undefined")){
			formTable=formTable+" and BRANCHING_NAME='"+zh+"'";
		}
		

		
		
		String[] cloumnsName = {"BRANCHINGID","BRANCHING_NAME","BRANCHING_CODE"
				,"RLAST_OPER","RLAST_ODATE","REMARK","COMBINATION_STATION","COMSTATIONID","PROD_SNS"};
		String kk="BRANCHINGID";
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
		if(!"".equals(sort) && !"BRANCHING_NAME".equals(sort)){
			for (String cloumn : cloumnsName) {
				if(cloumn.equals(sort)){
					boilersInfo +=" order by " + cloumn + " " + order;
					break;
				}
				
			}
		}
		else {
			boilersInfo +=" order by nlssort(BRANCHING_NAME, 'NLS_SORT=SCHINESE_STROKE_M') ";
		}
		//计算分页
		PageControl control = new PageControl();
		//当前页
		control.setInt_num(rowsPerpage);
		control.init(pageNo, total);
		//开始条数
		int start = control.getStart()-1;
		
		List<Object[]> lo = branchingBasicDao.queryBranchingBasicInfo(boilersInfo,start,rowsPerpage);
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
	
	public JSONArray queryCombinationStationName(String arg) {
		JSONArray jsonArr = null;
		JSONObject jsobj = null;
		String sql = "select st.combination_stationid,st.combination_station_name from pc_cd_combination_station_t st  order by nlssort(st.combination_station_name,'NLS_SORT=SCHINESE_STROKE_M')";
		if ("".equals(arg)) {
			sql = "select r.ORG_ID,r.STRUCTURENAME from PC_CD_BRANCHINGTREE_V r where r.STRUCTURETYPE='5' order by nlssort(r.STRUCTURENAME,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		List<Object[]> dataSet = branchingBasicDao.queryInfo(sql);
		if(dataSet != null && dataSet.size()>0){
			//生成树节点json文档
			jsonArr = new JSONArray();   
			for(Object[] entry : dataSet){
				jsobj = new JSONObject();
				jsobj.put("text", entry[1]);
				jsobj.put("id",entry[0]);
				jsonArr.add(jsobj);
			}
			if (!"".equals(arg)) {
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
	
	public JSONArray queryBranchingNameInfo(String arg) {
		JSONArray jsonArr = null;
		JSONObject jsobj = null;
		String sql = "select br.branchingid,br.branching_name from PC_CD_BRANCHING_T br  order by nlssort(br.branching_name,'NLS_SORT=SCHINESE_STROKE_M')";
		if (!"".equals(arg) && !"1".equals(arg)) {
			sql = "select br.branchingid,br.branching_name from PC_CD_BRANCHING_T br where br.combination_station='" + arg + "' order by nlssort(br.branching_name,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		List<Object[]> dataSet = branchingBasicDao.queryInfo(sql);
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
	
	public boolean saveOrUpdate(String grzh,String boilerid2,String boilername2,String boilertype2,String boilercode2,String rlastoper2,String rlastodate2,String remark2){
		PcCdBoilerT boiler = new PcCdBoilerT();
		PcCdOrgT org = new PcCdOrgT();
		if (!"".equals(boilerid2)) {//有锅炉id 为更新操作
			
		}
		
		return false;
	}
	public boolean removeBranch(String arg) throws Exception{
		String sql ="DELETE FROM pc_cd_branching_t f where f.branchingid= '"+arg+"'";
//		String boilerSql="";
//		String orgSql="";
//		
//		List<PcCdBoilerT> pcCdBoilerTs = null;
//		List<PcCdOrgT> pcCdOrgTs = null;
//		try {
//			pcCdBoilerTs = boilerBasicDao.queryBoiler("FROM PcCdBoilerT where boilerid='3D4AB1FE350448A8974D75C64F965575'");
//			pcCdOrgTs = boilerBasicDao.queryOrg("FROM PcCdOrgT where orgId='ORG072'");
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		if (pcCdOrgTs != null && pcCdOrgTs.size() > 0 && pcCdBoilerTs != null && pcCdBoilerTs.size() > 0) {
//			pcCdBoilerTs.get(0).setPcCdOrgT((PcCdOrgT)pcCdOrgTs.get(0));
//		}
//		
//		boolean deleteFlg = boilerBasicDao.removeBoilerOrgInfo(pcCdBoilerTs.get(0));;
		return branchingBasicDao.removeBranch(sql);
	}
	public List<PcCdBranchingT> searchBranchs(String id, String name)
			throws Exception {
		List<PcCdBranchingT> branchinglist = null;
		String hql = "FROM PcCdBranchingT b WHERE 1=1 ";
		if(id != null && !"".equals(id)){
			hql += " and b.branchingid = '"+id+"'";
		}
		
		if(id != name && !"".equals(name)){
			hql += " and b.branchingName = '"+name+"'";
		}
		
		branchinglist = branchingBasicDao.searchBranchs(hql);
		return branchinglist;
	}
	public boolean addbranch(PcCdBranchingT pcCdBranchingT) throws Exception {
		return branchingBasicDao.saveBranch(pcCdBranchingT);
	}
	public boolean updatebranch(PcCdBranchingT pcCdBranchingT) throws Exception {
		return branchingBasicDao.updateBranch(pcCdBranchingT);
	}
}
