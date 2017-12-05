package com.echo.service;


import java.util.List;

import com.echo.dto.PcCdCombinationStationT;

import net.sf.json.JSONObject;

public interface CombinationService {
	
	/**
	 * 获取油井树结构
	 * @param 
	 * @return
	 */
	public JSONObject  searchCombination(String stationNumber,String zh,String jh,int pageNo,String sort,String order,int rowsPerpage);
	
	public boolean removeStationInfo(String arg,String orgid);

	public List<PcCdCombinationStationT> searchName(String combinationStationName) throws Exception;

	public boolean addComb(PcCdCombinationStationT comb) throws Exception;

	public List<PcCdCombinationStationT> serachCombById(String combinationStationid,
			String combinationStationName) throws Exception;

	public boolean updateComb(PcCdCombinationStationT comb) throws Exception;

}
