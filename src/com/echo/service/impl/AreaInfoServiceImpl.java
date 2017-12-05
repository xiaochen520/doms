package com.echo.service.impl;

 
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.beans.PageControl;
import com.echo.dao.AreaInfoDao;
import com.echo.dao.PageDao;
import com.echo.dto.PcCdAreaInfoT;
import com.echo.dto.PcCdZoneT;
import com.echo.service.AreaInfoService;

public class AreaInfoServiceImpl implements AreaInfoService{
	
	private AreaInfoDao areaInfoDao;
	private PageDao pageDao;
	
	
	public PageDao getPageDao() {
		return pageDao;
	}
	public void setPageDao(PageDao pageDao) {
		this.pageDao = pageDao;
	}
	public void setAreaInfoDao(AreaInfoDao areaInfoDao) {
		this.areaInfoDao = areaInfoDao;
	}
	
	public List<PcCdAreaInfoT> searchRole(String areaid,String areaname) throws Exception{
		String hql = "FROM PcCdAreaInfoT R WHERE 1=1 ";
		
		if(areaid != null && !"".equals(areaid)){
			hql += "AND R.qkid = '"+areaid+"'";
		}
		
		if(areaname != null && !"".equals(areaname)){
			
			hql += "AND R.qkmc like '%"+areaname+"%'";
		}
		
		List<PcCdAreaInfoT> areaList = areaInfoDao.searchArea(hql);
		
		return areaList;
	}




	public JSONObject searchAreaInfo(String stationNumber, String boilersName,
			String area, int pageNo, String sort, String order, int rowsPerpage) {
		JSONArray jsonArr = null;
		JSONObject tjo = null;
		String cloums = "select  * ";
		String formTable = "from pc_cd_area_info_v t where 1=1";
		String totalNum = "select count(*)";
		if(!stationNumber.equals("")&&stationNumber!=null){
			formTable=formTable+" and t.qkmc like '%"+stationNumber+"%'";
		}


		String boilersInfo = cloums + formTable;
		String[] cloumnsName = {"QKID","ZONE_CODE","QKMC","QKMC_S","ZONE_NAME","ORG_ID"};
		//获取总条数
		int total = 0;
		if ("1".equals(totalNum)) {
			total = 1;
		} else {
			totalNum += formTable;
			total = pageDao.getCounts(totalNum);
		}
		//排序
		if(!"".equals(sort) && !"QKMC".equals(sort)){
			for (String cloumn : cloumnsName) {
				if(cloumn.equals(sort)){
					boilersInfo +=" order by t." + cloumn + " " + order;
					break;
				}
				
			}
		}
		else {
			boilersInfo +=" order by  nlssort(t.QKMC, 'NLS_SORT=SCHINESE_STROKE_M') ";
		}
		//计算分页
		PageControl control = new PageControl();
		//当前页
		control.setInt_num(rowsPerpage);
		control.init(pageNo, total);
		//开始条数
		int start = control.getStart()-1;
		
		List<Object[]> lo = areaInfoDao.searchAreaInfo(boilersInfo,start,rowsPerpage);
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




	public boolean removeStationInfo(String arg,String org_id) {
		String[] sqls = new String[2];
		sqls[0] = " DELETE from pc_cd_area_info_t d where d.qkid = '"+arg+"'";
		sqls[1] = " DELETE from pc_cd_org_t t where t.org_id = '"+org_id+"'";
		
		return areaInfoDao.removeStationInfo(sqls);
	}
	public List<PcCdZoneT> searchZonefo(String name ,String id)throws Exception {
		List<PcCdZoneT> zones = null;
		String hql = " FROM PcCdZoneT z WHERE 1=1" ;
		if(name != null && !"".equals(name)){
			hql += "and z.zoneName = '"+name+"'";
		}
		if(id != null && !"".equals(id)){
			hql += "and z.zoneCode = '"+id+"'";
		}
		
		hql +=	" order by z.zoneName";
		zones = areaInfoDao.searchZones(hql);
		return zones;
	}
	public List<PcCdAreaInfoT> searchQkmc(String qkmc) throws Exception {
		List<PcCdAreaInfoT> areaList = null;
		String hql = " from  PcCdAreaInfoT a where 1=1";
		if(qkmc !=null && !"".equals(qkmc)){
		hql += "and a.qkmc ='"+qkmc+"'";
		}
		areaList = areaInfoDao.searchQkmc(hql);
		return areaList;
	}
	public boolean addArea(PcCdAreaInfoT area) throws Exception {
		return areaInfoDao.save(area);
	}
	public List<PcCdAreaInfoT> searchByName(String qkid, String qkmc)throws Exception {
		List<PcCdAreaInfoT> areaList = null;
		String hql = " from  PcCdAreaInfoT a where 1=1";
		if(qkid !=null && !"".equals(qkid)){
			hql += "and a.qkid  ='"+qkid+"'";
		}
		if(qkmc !=null && !"".equals(qkmc)){
		hql += "and a.qkmc  ='"+qkmc+"'";
		}
		areaList = areaInfoDao.searchByName(hql);
		return areaList;
	}
	public boolean updateArea(PcCdAreaInfoT area) throws Exception {
		boolean flag =  true;
		try {
			flag = areaInfoDao.updateArea(area);
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
	@Override
	public List<PcCdAreaInfoT> searchByQkmc_s(String qkid,String qkmcS)throws Exception {
		// TODO Auto-generated method stub
		List<PcCdAreaInfoT> areaList = null;
		String hql = " from  PcCdAreaInfoT a where 1=1";
		if(qkid !=null && !"".equals(qkid)){
			hql += " and a.qkid<>'"+qkid+"'";
		}
		if(qkmcS !=null && !"".equals(qkmcS)){
			hql += " and a.qkmcS='"+qkmcS+"'";
		}
		areaList = areaInfoDao.searchByName(hql);
		return areaList;
	}

}
