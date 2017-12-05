package com.echo.service;


import java.util.List;

import net.sf.json.JSONObject;

import com.echo.dto.PcRpdU1WaterQuality1T;
import com.echo.dto.PcRpdU1WaterQuality2T;

public interface U1szjcService {
	
	
	public JSONObject searchU2szjc(List<String> date) throws Exception;
	
	public JSONObject searchU1Q2szjc(List<String> date) throws Exception;
	
	public List<Object[]>  searchDatas (List<String> date) throws Exception;
	
	public List<Object[]>  searchExportData (List<String> date,String fields) throws Exception;
	
	public List<PcRpdU1WaterQuality1T> searchU2WaterQuality(String id) throws Exception;
	
	public List<PcRpdU1WaterQuality2T> searchU1WaterQuality(String id) throws Exception;
	
	public boolean addOrUpdateData(PcRpdU1WaterQuality1T prws)throws Exception;
	
	public boolean addOrUpdateData(PcRpdU1WaterQuality2T prws)throws Exception;

}
