package com.echo.service;

import java.util.HashMap;
import java.util.List;

import com.echo.dto.PcCdOrgT;
import com.echo.dto.PcCdStationT;
import com.echo.dto.PcCdWaterSourceWellT;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface WaterSoWellService {
	
	//添加
	public boolean saveOrUpdate(String TJQK,String TJZH, String waterSourceWellid,String wellName, String wellCode,
			String bewellName, String startupTime, String rlastOper,
			String rlastOdate, String remark);
//查询区块
	public JSONArray queryWorkarea();
//查询站号
	public JSONArray queryWellarea(String well_ara);
//查询水源井
	public JSONArray queryWellmonth(String arg);
//删除
	/**
	 * 获取油井树结构
	 * @param 
	 * @return
	 */
	public HashMap<String,Object>  searchWaterSoWell(String totalNumFlag,String jrbz,String qk,String zh,String jh,String ghid,String dyearC,int pageNo,String sort,String order,int rowsPerpage);
	
	public boolean removeStationInfo(String arg,String orgid);
	public JSONArray queryCombinationInfo(String arg);
	public JSONArray queryWaterSoWell(String arg);
	public JSONArray queryWellInfo(String arg);
	
	public List<PcCdWaterSourceWellT> searchWaterSoWells(String id,String name) throws Exception;
	
	public List<Object[]> searchOrg(String name,String type) throws Exception;
	
	public boolean addwell(PcCdWaterSourceWellT well)throws Exception;
	
	public boolean updatewell(PcCdWaterSourceWellT well)throws Exception;
	public JSONObject cascadeInit();

		
}
