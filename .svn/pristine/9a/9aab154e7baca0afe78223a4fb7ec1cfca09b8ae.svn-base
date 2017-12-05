package com.echo.service;

import java.util.HashMap;
import java.util.List;

import com.echo.dto.PcCdNetworkMdT;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface GwghjcxxService {

	public JSONObject searchCascadeInit()throws Exception;

	public JSONObject searchOnChangeOil(String oilname, String stationname)throws Exception;

	public HashMap<String, Object> searchData(String oilName,String stationName, String network, int pageNo, String sort,String order, int rowsPerpage, String totalNum)throws Exception;

	public JSONArray searchStation()throws Exception;

	public boolean saveOrUpdate(PcCdNetworkMdT net)throws Exception;

	public List<PcCdNetworkMdT> searchCheck(String nETWORKMDID,String editNETWORK)throws Exception;

	public boolean removeData(String mdId, String orgId)throws Exception;

	public List<Object[]> searchCheckNot(String editSTATION)throws  Exception;

}
