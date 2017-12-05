package com.echo.service;

import java.util.HashMap;
import java.util.List;

import com.echo.dto.PcCdStationPointT;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface ZkcjdService {

	public HashMap<String, Object> searchData(String sYSTEMCODEQ, String pOINTTYPEQ,
			String cREATEDATEQ, String fLOWCODEQ, String aLARMORQ,
			String aLARMCODEQ, String dEVICECODEQ, int pageNo, String sort,
			String order, int rowsPerpage, String totalNum,String ptable)throws Exception;

	public JSONObject searchCascadeInit()throws Exception;

	public boolean deleteData(String sMALLSTATIONID)throws Exception;

	public List<PcCdStationPointT> searchCheckD(String sTATIONPOINTID,
			String tAGNAME)throws Exception;

	public boolean saveData(PcCdStationPointT sp)throws Exception;

	public JSONArray searchOnChange(String code)throws Exception;

	public JSONArray searchOnChangeLC(String code)throws Exception;

	public JSONArray searchOnChangeLCeDIT(String code)throws Exception;

}
