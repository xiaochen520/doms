package com.echo.service;


import java.util.List;

import net.sf.json.JSONArray;

public interface SCLJYService {
	
	public JSONArray  searchSCLJYRPD(String endDate,String totalNumf) throws Exception;
	//public boolean addOrUpdateSCLJYRPD(PcRpdWellSagddT prws)throws Exception;
	public List<String> dayreport()throws Exception;
	
	public List<Object[]> searchU2_WATER(String rq,String fields) throws Exception;
	public String searchFlagDate(String rq) throws Exception;
}
