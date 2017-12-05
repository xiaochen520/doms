package com.echo.service;

import java.util.HashMap;

import net.sf.json.JSONArray;

public interface SjtdjctjbService {

	public JSONArray searchObjectName() throws Exception;

	public HashMap<String, Object> searchData(String oBJECTTYPENAME,
			String cHECKDATE, int pageNo, String sort, String order,
			int rowsPerpage, String totalNum)throws Exception;

	public HashMap<String, Object> searchDTData(String oBJECTTYPENAME,String OBJECT_NAME,
			String cHECKDATE,  String dATAINTERNUM1, String dATAINTERACT1, String dATAINTERNUM2, String dATAINTERACT2, String dATAINTERNUM3, String dATAINTERACT3, int pageNo, String sort, String order,
			int rowsPerpage, String totalNum)throws Exception;

	public JSONArray searchOnChangeName(String typeName)throws Exception;

}
