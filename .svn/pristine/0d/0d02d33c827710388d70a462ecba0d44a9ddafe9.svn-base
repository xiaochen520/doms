package com.echo.service.impl;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.beans.PageControl;
import com.echo.dao.CombinationDao;
import com.echo.dao.PageDao;
import com.echo.dto.PcCdCombinationStationT;
import com.echo.service.CombinationService;



public class CombinationServiceImpl implements CombinationService{
	private CombinationDao combinationDao;
	private PageDao pageDao;
	
	

	public PageDao getPageDao() {
		return pageDao;
	}



	public void setPageDao(PageDao pageDao) {
		this.pageDao = pageDao;
	}



	public CombinationDao getCombinationDao() {
		return combinationDao;
	}



	public void setCombinationDao(CombinationDao combinationDao) {
		this.combinationDao = combinationDao;
	}



	public JSONObject searchCombination(String stationNumber,String zh,String jh,int pageNo,String sort,String order,int rowsPerpage) {
		
		JSONArray jsonArr = null;
		JSONObject tjo = null;
		String cloums = "select  ";
		String formTable = " from pc_cd_com_sta_v t where 1=1";
		String totalNum = "select count(*)";
		if(!stationNumber.equals("")&&stationNumber!=null){
			formTable=formTable+" and t.COMBINATION_STATION_NAME='"+stationNumber+"'";
		}
		String[] cloumnsName = {"COMBINATION_STATIONID","ORG_ID","A2ID","COMBINATION_STATION_NAME","COMBINATION_STATION_TYPE","STATUS_VALUE","RLAST_OPER","RLAST_ODATE","REMARK","PROD_SNS","COMBINATION_STATION_CODE"};
		String kk="COMBINATION_STATIONID";
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
		if(!"".equals(sort) && !"COMBINATION_STATION_NAME".equals(sort)){
			for (String cloumn : cloumnsName) {
				if(cloumn.equals(sort)){
					boilersInfo +=" order by t." + cloumn + " " + order;
					break;
				}
				
			}
		}
		else {
			boilersInfo +=" order by nlssort(t.COMBINATION_STATION_NAME, 'NLS_SORT=SCHINESE_STROKE_M') ";
		}
		//计算分页
		PageControl control = new PageControl();
		//当前页
		control.setInt_num(rowsPerpage);
		control.init(pageNo, total);
		//开始条数
		int start = control.getStart()-1;
		
		List<Object[]> lo = combinationDao.searchCombination(boilersInfo,start,rowsPerpage);
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

	public boolean removeStationInfo(String arg,String orgid){
		String[] sqls = new String[2];
		sqls[0] = " DELETE  from pc_cd_combination_station_t a  where  a.combination_stationid='"+arg+"'";
		sqls[1] = "DELETE from  pc_cd_org_t where org_id='"+orgid+"'";
		return combinationDao.removeStationInfo(sqls);
	}



	public List<PcCdCombinationStationT> searchName(String combinationStationName) throws Exception {
		List<PcCdCombinationStationT>  combList = null;
		 String hql =  "from PcCdCombinationStationT c  where 1=1";
		 if(combinationStationName !=null && !"".equals(combinationStationName)){
			 hql += "and c.combinationStationName = '"+combinationStationName+"' ";
		 }
		 combList = combinationDao.searchCombinationStationName(hql);
		return combList;
	}



	public boolean addComb(PcCdCombinationStationT comb) throws Exception {
		
		return combinationDao.save(comb);
	}
	public List<PcCdCombinationStationT> serachCombById(String combinationStationid, String combinationStationName)throws Exception {
		List<PcCdCombinationStationT> combList = null;
		String hql = "from  PcCdCombinationStationT c where 1=1";
		if(combinationStationid != null &&  !"".equals(combinationStationid)){
			hql +=" and  c.combinationStationid = '"+combinationStationid+"'";
		}
		if(combinationStationName !=null && !"".equals(combinationStationName)){
			hql += "and c.combinationStationName ='"+combinationStationName+"'";
		}
		combList = combinationDao.searchComb(hql);
		return combList;
	}



	public boolean updateComb(PcCdCombinationStationT comb) throws Exception {
		boolean flag = true;
		try {
			flag = combinationDao.updateComb(comb);
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return false;
	}


}
