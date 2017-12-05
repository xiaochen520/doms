package com.echo.service;


import java.util.HashMap;
import java.util.List;

import com.echo.dto.PcCdThinoilWellT;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface ThinOilWellService {
	
	/**
	 * 获取油井树结构
	 * @param 
	 * @return
	 */
	public JSONObject  searchThinOil(String qk,String zh,String jh,String gh,String name,String jrbz1,String dyearC,int pageNo,String sort,String order,int rowsPerpage);
	
	public boolean removeStationInfo(String arg,String orgid);
	public JSONArray queryWellInfo(String arg);
	public JSONObject cascadeInit();

	public List<PcCdThinoilWellT> searchThisName(String thinoilwellname) throws Exception;

	public boolean addThin(PcCdThinoilWellT thin) throws Exception;

	public List<PcCdThinoilWellT> searchThinById(String thinwellid,String thinoilwellname) throws Exception;

	public boolean updateThin(PcCdThinoilWellT thin) throws Exception;

	public HashMap<String, Object> searchOnExport(String oilstationname1,String blockstationname1,String rulewellname1,String areablock,String jrbz1,String dyearC,String totalNum)throws Exception;
}
