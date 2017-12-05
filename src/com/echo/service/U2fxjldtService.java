package com.echo.service;

import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONObject;

public interface U2fxjldtService {

	public HashMap<String, Object> searchProducedRD(String startDate,String endDate, int pageNo, String sort, String order,
			int rowsPerpage, String totalNum)throws Exception;
	public JSONObject searchfxjlrb(List<String> date) throws Exception;
	public List<Object[]> searchExportData(List<String> date, String fields)
			throws Exception ;
	public JSONObject searchSagdghResults(List<String> date);
	public HashMap<String, Object> searchDatas(String startDate,
			String endDate, int pageNo, String sort, String order,
			int rowsPerpage, String totalNum) throws Exception;
}
