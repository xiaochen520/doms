package com.echo.service;


import java.util.HashMap;
import java.util.List;

import com.echo.dto.PcCdWaterfloodingWellT;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface WaterFloodingService {
	
	/**
	 * 获取油井树结构
	 * @param 
	 * @return
	 */
	public JSONObject  searchwaterFL(String qk,String zh,String jh,String name,String zsq,String JRBZ1,String dyearC,String groupName,int pageNo,String sort,String order,int rowsPerpage);
	
	public boolean removeStationInfo(String arg,String orgid);
	public JSONArray queryWaterflooding(String arg);
	public JSONObject cascadeInit();

	public List<PcCdWaterfloodingWellT> searchWaterFlood(String id,String wellName) throws Exception;
	public List<Object[]> searchQiao(String name, String type) throws Exception;
	public boolean updatewell(PcCdWaterfloodingWellT Well)throws Exception;
	public boolean addwell(PcCdWaterfloodingWellT Well)throws Exception;


	public JSONArray queryOilStation(String arg)throws Exception;

	public HashMap<String, Object> searchOnExport(String oilstationname1,
			String rulewellname1, String waterflooding1, String areablock,
			String jrbz1,String dyearC,String groupName, String totalNum)throws Exception;

	public JSONArray injectName()throws Exception;
}
