package com.echo.service;


import java.util.List;

import com.echo.dto.PcCdDepartmentT;
import com.echo.dto.PcCdObservationWellT;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface ObservationService {
	
	/**
	 * 获取油井树结构
	 * @param 
	 * @return
	 */
	public JSONObject  searchObservationWell(String qk,String st,String zh,String jh,int pageNo,String sort,String order,int rowsPerpage);
	
	public JSONArray searchStructureJc(String hql);
	
	public boolean addObs(PcCdObservationWellT well) throws Exception;
	
	public String serachOrgByName(String name) throws Exception;

	public boolean UpdateObs(PcCdObservationWellT well) throws Exception;

	public List<PcCdObservationWellT>searchObservation(String id,String name) throws Exception;
	public JSONArray queryWellInfo(String arg);
	public JSONObject cascadeInit();

	public List<Object[]> searchSation(String name, String type) throws Exception;

	public boolean removeObswell(String obsId, String orgid) throws Exception;
}
