package com.echo.service;

import java.util.HashMap;

/**
 * 
 * @author  王博
 * @date 2017-5-3
 * @time 上午11:07:46
 * 锅炉房气体监测数据Service
 */
public interface BoilerRoomGasMonitoringService {
	
	/*
	 * @param turnNote 接转站
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
	public HashMap<String,Object> searchData(String turnNote ,String stime,String etime,int pageNo,String sort,String order,int rowsPerpage,String totalNumFlag)throws Exception;

	

}
