package com.echo.service;

import java.util.HashMap;

public interface WaterSWRDService {
	
	/**
	 * 获取油井树结构
	 * @param 
	 * @return
	 */
	public HashMap<String,Object> searchData(String qk,String zh,String jh,String gh,String name,String stime,String etime,int pageNo,String sort,String order,int rowsPerpage,String totalNum);
	
}
