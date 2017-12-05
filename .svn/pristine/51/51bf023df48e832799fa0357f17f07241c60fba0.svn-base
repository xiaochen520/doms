package com.echo.service;

import java.util.HashMap;
import java.util.List;

import com.echo.dto.PcCdSmallStationT;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface JzcjdxxService {

	public HashMap<String, Object> searchData(String oBJECTCODEQ,
			String iNSTRUMENTCALLEDQ, String cONTROLORQ, String aCCESSSIGNSQ,
			String cONTROLLERTYPEQ, String pOINTTYPEQ, String aLARMORQ,
			String aLARMCODEQ, String hISTORYCURVEQ, String cREATEDATEQ,
			String cREATEDATEQA, String pOINTCODEQ, int pageNo, String sort,
			String order, int rowsPerpage, String totalNum)throws Exception;

	public JSONObject searchCascadeInit()throws Exception;

	public List<PcCdSmallStationT> searchCheckD(String sMALLSTATIONID,
			String POINTCODE,String OBJECT_CODE)throws Exception;

	public boolean saveData(PcCdSmallStationT sm)throws Exception;

	public boolean deleteData(String sMALLSTATIONID)throws Exception;

	public JSONArray searchOnChangeData(String qxlxId)throws Exception;

	public JSONObject searchOnChangeData(String queID, String editId)throws Exception;

	public JSONObject searchOnChangeDataEdit(String editId)throws Exception;

	

}
