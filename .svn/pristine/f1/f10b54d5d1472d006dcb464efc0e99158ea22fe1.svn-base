package com.echo.service;

import java.util.List;

import com.echo.dto.PcCdControllerT;
import com.echo.dto.PcCdInstruMentationT;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public interface ControllerService {

	public JSONObject searchControl(String oilstationname,String blockstationname,String wellnum, String boiler,String instrutype,int pageNo,String sort,String order,int rowsPerpage);
	public JSONObject cascadeInit();
	public boolean removeController(String arg);
	public boolean SaveOrUpdateControl(PcCdControllerT im) throws Exception;
	public List<PcCdControllerT> searchControllerId(String ConId,String insId,String name)throws Exception;
	public JSONArray searchCommtype(String commtype) throws Exception;
	public JSONArray searchIpadd(String ipadd) throws Exception;
	public JSONArray searchStationno(String stationno) throws Exception;
	public JSONArray searchComport(String comport) throws Exception;
	public JSONArray searchEquipstatus(String equipstatus) throws Exception;
	public JSONArray searchYBNameQuery(String instrutype) throws Exception;
	public JSONArray searchYBNameQueryJL(String orgid,String data1)throws Exception;
	//查询
	public JSONArray queryConBoilersNameInfo(String arg) throws Exception;
	public JSONArray queryConWellInfo(String arg) throws Exception;
	public JSONArray queryConInjectionTopryInfoAdd(String arg) throws Exception;
	public JSONArray queryConOilSationInfo(String arg) throws Exception;
}
