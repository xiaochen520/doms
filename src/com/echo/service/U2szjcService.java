package com.echo.service;


import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.dto.PcRpdU2WaterQualityT;

public interface U2szjcService {
	
	
	public JSONArray searchU2szjc(String rq)throws Exception;
	public JSONObject searchU2szjc(List<String> date) throws Exception;
	
	
	public List<Object[]>  searchDatas (List<String> date) throws Exception;
	
	public List<Object[]>  searchExportData (List<String> date,String fields) throws Exception;
	
	public List<PcRpdU2WaterQualityT> searchU2WaterQuality(String id) throws Exception;
	
	public boolean addOrUpdateData(PcRpdU2WaterQualityT prws)throws Exception;

}
