package com.echo.service;


import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.dto.PcCdWaterInjectiontopryT;

public interface WaterInjectionService {
	
	/**
	 * 获取油井树结构
	 * @param 
	 * @return
	 */
	public JSONObject  searchwaterIT(String qk,String zh,String jh,String name,String JRBZ1,String dyearC,String groupTeam, int pageNo,String sort,String order,int rowsPerpage);
	
	public boolean removeStationInfo(String arg,String orgid);
	public JSONArray queryWellInfo(String arg);
	
	public List<PcCdWaterInjectiontopryT> getInjectiontoprys(String id,String name) throws Exception;
	
	public boolean addINT(PcCdWaterInjectiontopryT pcCdWaterInjectiontopryT) throws Exception;
	
	public boolean updateINT(PcCdWaterInjectiontopryT pcCdWaterInjectiontopryT) throws Exception;

	public HashMap<String, Object> searchOnExport(String stationNumber,String areablock1,String rulewellname1,String JRBZ1,String dyearC,String groupTeam,String totalNum)throws Exception;
}
