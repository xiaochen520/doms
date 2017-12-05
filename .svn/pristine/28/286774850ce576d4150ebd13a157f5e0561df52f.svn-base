package com.echo.service;

import java.util.HashMap;
import java.util.List;

import com.echo.dto.PcCdCliquePoolT;
import com.echo.dto.PcCdOrgT;

import net.sf.json.JSONObject;

public interface FcjcxxService {

	public HashMap<String,Object> searchDatas(String oilstationname,String blockstationname,String cliquepool,String cliquetype,String totalNumFlag,int pageNo,String sort,String order,int rowsPerpage) throws Exception;
	
	public List<PcCdCliquePoolT> searchData(String id, String name)throws Exception;
	
	public List<PcCdOrgT> searchOrgData(String id, String name)throws Exception;
	
	public boolean removeData(String arg)throws Exception;
	
	public boolean addOrUpdateData(PcCdCliquePoolT pool) throws Exception;
	

}
