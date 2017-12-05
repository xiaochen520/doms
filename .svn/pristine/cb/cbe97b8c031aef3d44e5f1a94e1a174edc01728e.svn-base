package com.echo.service.impl;

import java.util.List;

import com.echo.dao.FunctionMenuDao;
import com.echo.dao.StationTDao;
import com.echo.service.CommonService;

public class CommonServiceImpl implements CommonService{
	private StationTDao stationTDao;
	private FunctionMenuDao functionMenuDao;
	public void setFunctionMenuDao(FunctionMenuDao functionMenuDao) {
		this.functionMenuDao = functionMenuDao;
	}
	public void setStationTDao(StationTDao stationTDao) {
		this.stationTDao = stationTDao;
	}
	public String getStatusValueINT(String statusvalue)throws Exception {
		
		String returnStr = "";
		String sql = "select * from PC_CD_PRODUC_STATUS_SAGD_T p ";
		
		if(statusvalue != null && !"".equals(statusvalue)){
			sql += " where 1=1 and  p.prod_sns = '"+statusvalue+"'";
			
		}
		List<Object[]> dataSet = stationTDao.queryInfo(sql);
		if(dataSet != null && dataSet.size()>0){
			
			
			returnStr = dataSet.get(0)[0].toString();
		}
		
		
		return returnStr;
	}
	public List<Object[]> getStationList(String value,int type) throws Exception {
		List<Object[]> zzzlist = null;
		String table = "";
		if("水源井基础信息".equals(value)){
			table = "view_tree_syjmenu";
		}
		
		String sql = "select * from "+table+" g where 1=1 and  g.STRUCTURETYPE = "+type;
		zzzlist = functionMenuDao.searchStructureNew(sql);
		return zzzlist;
	}
	
}
