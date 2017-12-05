package com.echo.service;

import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;

import com.echo.dto.PcWellDowntime;

public interface CloseWellService {
	
	//添加
	public boolean saveOrUpdate(PcWellDowntime wellInfo)throws Exception;

	public HashMap<String,Object>  searchDatas(String totalNumFlag,String CYZ,String TEAM,String WELLNAME,String tableid,String startDate,String endDate,int pageNo,String sort,String order,int rowsPerpage)throws Exception;
	public boolean removeDatas(String id) throws Exception;
	public List<PcWellDowntime> searchOneData(String id,String name,String param,String param1) throws Exception;
	public List<Object[]> searchDatas(String sql) throws Exception;
	public JSONArray searchDOWNTIME_TYPE()throws Exception;
	

		
}
