package com.echo.service;

import java.util.HashMap;

import net.sf.json.JSONObject;

public interface U1yqdtService {

	public HashMap<String, Object>  serarchU1yqdt(String startDate,String endDate, int pageNo, String sort, String order,int rowsPerpage, String totalNum)throws Exception;

	public HashMap<String, Object> serarchU1sqdt(String startDate,String endDate, int pageNo, String sort, String order,int rowsPerpage, String totalNum)throws Exception;
	
	public HashMap<String, Object>  serarchU1fxdt(String startDate,String endDate, int pageNo, String sort, String order,int rowsPerpage, String totalNum)throws Exception;
	
	public HashMap<String, Object>  serarchmbzjdt(String startDate,String endDate, int pageNo, String sort, String order,int rowsPerpage, String totalNum,String name)throws Exception;
}
