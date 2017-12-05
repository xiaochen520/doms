package com.echo.service;


import java.util.List;

import net.sf.json.JSONArray;

public interface YYCLJYRPDService {
	
	
	public JSONArray searchU2OIL(String rq)throws Exception;
	public List<Object[]> searchU2OIL(String rq,String fields) throws Exception;

}
