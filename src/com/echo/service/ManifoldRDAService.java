package com.echo.service;

import java.util.HashMap;

import net.sf.json.JSONObject;

public interface ManifoldRDAService {	
	
	public HashMap<String,Object> searchManifoldRDA(String qk,String zh,String jh,String gh,String name,String stime,String etime,int pageNo,String sort,String order,int rowsPerpage,String totalNum);

}
