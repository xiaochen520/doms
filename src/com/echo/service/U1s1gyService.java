package com.echo.service;

import java.util.List;

import com.echo.dto.PcRpdU1WaterAutoT;
import com.echo.dto.PcRpdU2TSJY;
import com.echo.dto.PcRpdU2TSWS;
import com.echo.dto.PcRpdU2TSZH;
import com.echo.dto.PcRpdU2WaterAutoT;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface U1s1gyService {

	public JSONArray searchU1s1gy(String txtDate)throws Exception;

	public List<PcRpdU1WaterAutoT> SreachU1S1gy(String id, String name)throws Exception;
	
	public List<PcRpdU2WaterAutoT> SreachU2S1gy(String id, String name)throws Exception;
	public List<PcRpdU2TSJY> SreachTSJY(String id, String name)throws Exception;
	public List<PcRpdU2TSWS> SreachTSWS(String id, String name)throws Exception;
	public List<PcRpdU2TSZH> SreachTSZH(String id, String name)throws Exception;

	public int searchCalcNum()throws Exception;

	public boolean updateU1S1gy(PcRpdU1WaterAutoT us)throws Exception;
	
	public boolean updateU2S1gy(Object us)throws Exception;

	public List<Object[]> searchExportU1S1(String txtDate, String fields)throws Exception;
	
	public JSONObject searchU2tscs1(String txtDate)throws Exception;
	
	public JSONArray searchU2tscs2(String txtDate,double lit311)throws Exception;
	
}
