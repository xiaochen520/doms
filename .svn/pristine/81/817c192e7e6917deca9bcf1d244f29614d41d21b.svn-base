package com.echo.service.impl;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.beans.PageControl;
import com.echo.dao.OilDrillingDao;
import com.echo.dao.PageDao;
import com.echo.dto.PcCdOildrillingStationT;
import com.echo.service.OilDrillingService;

public class OilDrillingServiceImpl implements OilDrillingService{
	private OilDrillingDao oilDriDao;
	private PageDao pageDao;
	
	
	public OilDrillingDao getOilDriDao() {
		return oilDriDao;
	}

	public void setOilDriDao(OilDrillingDao oilDriDao) {
		this.oilDriDao = oilDriDao;
	}

	public PageDao getPageDao() {
		return pageDao;
	}

	public void setPageDao(PageDao pageDao) {
		this.pageDao = pageDao;
	}

	public JSONObject queryOilStation(String stationNumber,String boilersName,String area, int pageNo,String sort,String order,int rowsPerpage){
		JSONArray jsonArr = null;
		JSONObject tjo = null;
		String cloums = "select ";
		String formTable = " from pc_cd_oildriling_v t where 1=1";
		String totalNum = "select count(*)";
		if(!stationNumber.equals("")&&stationNumber!=null){
			formTable=formTable+" and t.OILDRILLING_STATION_NAME='"+stationNumber+"'";
		}

		String[] cloumnsName = {"OILDRILLING_STATIONID","ORG_ID","A2ID","OILDRILLING_STATION_NAME","STATUS_VALUE","RLAST_OPER","RLAST_ODATE","REMARK","PROD_SNS"};
		String kk="OILDRILLING_STATIONID";
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
		if(!"".equals(sort) && !"OILDRILLING_STATION_NAME".equals(sort)){
			for (String cloumn : cloumnsName) {
				if(cloumn.equals(sort)){
					boilersInfo +=" order by t." + cloumn + " " + order;
					break;
				}
				
			}
		}
		else {
			boilersInfo +=" order by nlssort(t.OILDRILLING_STATION_NAME, 'NLS_SORT=SCHINESE_STROKE_M') ";
		}
		//计算分页
		PageControl control = new PageControl();
		//当前页
		control.setInt_num(rowsPerpage);
		control.init(pageNo, total);
		//开始条数
		int start = control.getStart()-1;
		
		List<Object[]> lo = oilDriDao.queryOilStation(boilersInfo,start,rowsPerpage);
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
	
	
	
	

	
	
	
	public boolean removeStationInfo(String arg,String org_id){
		String[] sqls = new String[2];
		sqls[0] = " DELETE from pc_cd_oildrilling_station_t d where d.OILDRILLING_STATIONID = '"+arg+"'";
		sqls[1] = " DELETE from pc_cd_org_t t where t.org_id = '"+org_id+"'";
		
		return oilDriDao.removeStationInfo(sqls);
	}

	public List<PcCdOildrillingStationT> serachOild(String oildid,String oildname) throws Exception {
		String hql = "FROM PcCdOildrillingStationT R WHERE 1=1 ";
		
		if(oildid != null && !"".equals(oildid)){
			hql += "AND R.oildrillingStationid = '"+oildid+"'";
		}
		
		if(oildname != null && !"".equals(oildname)){
			
			hql += "AND R.oildrillingStationName = '"+oildname+"'";
		}
		
		List<PcCdOildrillingStationT> oildList = oilDriDao.searchOild(hql);
		
		return oildList;
	}

	public List<PcCdOildrillingStationT> searchstatinByName(String oildrillingStationname) throws Exception {
		List<PcCdOildrillingStationT> oildrList = null;
		String  hql = "from   PcCdOildrillingStationT  o where 1=1";
		if(oildrillingStationname !=null  && !"".equals(oildrillingStationname)){
			hql += " and  o.oildrillingStationName = '"+oildrillingStationname+"'";
		}
		oildrList  =  oilDriDao.searchoildrillingStationName(hql);
		return oildrList;
	}

	public boolean addOil(PcCdOildrillingStationT oild) throws Exception {
		
		return oilDriDao.save(oild);
	}

	public List<PcCdOildrillingStationT> serachOildById(String oildrillingstationid, String oildrillingstationname)
			throws Exception {
		List<PcCdOildrillingStationT> oildList;
		String  hql = " from  PcCdOildrillingStationT  o where 1=1";
		if(oildrillingstationid !=null && !"".equals(oildrillingstationid)){
			hql +="  and o.oildrillingStationid = '"+oildrillingstationid+"' ";
		}
		if(oildrillingstationname !=null && !"".equals(oildrillingstationname)){
			hql += "and o.oildrillingStationName = '"+oildrillingstationname+"'";
		}
		oildList = oilDriDao.searchoild(hql);
		return oildList;
	}

	public boolean updateSta(PcCdOildrillingStationT oild) throws Exception {
		boolean flag = true;
		try {
			flag = oilDriDao.updataOild(oild);
		} catch (Exception e) {
			flag = false;
		}
		
		return false;
	}

}
