package com.echo.service;

import java.util.HashMap;

public interface ThinOilWellRDService {
	
	/**
	 * 获取油井树结构
	 * @param 
	 * @return
	 */
	public HashMap<String,Object> searchThinOil(String qk,String zh,String jh,String gh,String name,String stime,String etime,int pageNo,String sort,String order,int rowsPerpage,String totalNum);
	
}
