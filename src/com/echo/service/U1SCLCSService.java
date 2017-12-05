package com.echo.service;


import java.util.List;

import net.sf.json.JSONArray;

import com.echo.dto.PcRpdU1OilAutoT;

public interface U1SCLCSService {
	
	public JSONArray  searchU1SCLCS(String endDate,String totalNumf) throws Exception;
	//public boolean addOrUpdateSCLJYRPD(PcRpdWellSagddT prws)throws Exception;
	public List<String> dayreport()throws Exception;
	
	public List<Object[]> searchU2_WATER(String rq,String fields) throws Exception;
	public String searchFlagDate(String rq) throws Exception;
	public List<PcRpdU1OilAutoT> SreachOILAuto(String id,String rq) throws Exception;
	public boolean updateOILAuto(PcRpdU1OilAutoT oil)throws Exception;
	public int searchCalcNum()throws Exception;
}
