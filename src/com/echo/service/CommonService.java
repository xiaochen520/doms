package com.echo.service;

import java.util.List;



public interface CommonService {
	
	/**
	 * 获取油井树结构
	 * @param 
	 * @return
	 */
	
	public String getStatusValueINT(String statusvalue)throws Exception;
	
	public List<Object[]> getStationList(String value,int type)throws Exception;
}
