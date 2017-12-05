package com.echo.service;

import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;

import com.echo.dto.PcCdCliquePoolT;
import com.echo.dto.PcCdLargeStationT;
import com.echo.dto.PcCdOrgT;

public interface ZkxtxxService {

	public HashMap<String,Object> searchDatas(String param1,String param2,String param3,String param4,String param5,String totalNumFlag,int pageNo,String sort,String order,int rowsPerpage) throws Exception;
	
	public List<PcCdLargeStationT> searchData(String id, String param1,String param2,String param3)throws Exception;
	
	public List<PcCdOrgT> searchOrgData(String id, String name)throws Exception;
	
	public boolean removeData(String arg)throws Exception;
	
	public boolean addOrUpdateData(PcCdLargeStationT pool) throws Exception;

	public JSONArray getComData(String oilid)throws Exception;
	

}
