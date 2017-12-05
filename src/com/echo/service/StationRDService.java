package com.echo.service;

import java.util.HashMap;

public interface StationRDService {
	
	/**
	 * 
	 * @param cyz 采油站
	 * @param zzz 接转站
	 * @param qkmc 区块
	 * @param stime 开始时间
	 * @param etime 结束时间
	 * @param pageNo
	 * @param sort
	 * @param order
	 * @param rowsPerpage
	 * @param totalNum
	 * @return
	 * @throws Exception
	 */
	public HashMap<String,Object> searchData(String cyz,String zzz,String qkmc,String stime,String etime,int pageNo,String sort,String order,int rowsPerpage,String totalNumFlag)throws Exception;
	
}
