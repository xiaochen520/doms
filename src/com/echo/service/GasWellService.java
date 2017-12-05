package com.echo.service;


import java.util.HashMap;
import java.util.List;

import com.echo.dto.PcCdGasWellT;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface GasWellService {
	
	/**
	 * 获取油井树结构
	 * @param 
	 * @return
	 */
	public JSONObject  searchGasWell(String qk,String zh,String jh,String name,String jrbz1,String dyearC,int pageNo,String sort,String order,int rowsPerpage);
	
	public boolean removeStationInfo(String arg,String orgid);
	public JSONArray queryStationInfo(String oilid,String areaid,String selecteValue);
	public JSONArray queryWellInfo(String arg);
	public JSONObject cascadeInit();

	public List<PcCdGasWellT> searchGasName(String gaswellname) throws Exception;
	public List<Object[]> searchGasation(String gasstationname) throws Exception;
	public boolean addGas(PcCdGasWellT gas) throws Exception;

	public List<PcCdGasWellT> searchGasById(String gasid, String gasname) throws Exception;

	public boolean updateGas(PcCdGasWellT gas) throws Exception;

	public HashMap<String, Object> searchOnExport(String oilstationname1,String areablock1,String blockstationname1,String rulewellname1,String jrbz1,String dyearC,String totalNum)throws Exception;

}
