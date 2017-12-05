package com.echo.service;


import java.util.List;

import net.sf.json.JSONObject;

import com.echo.dto.PcRdLoginfoT;

public interface LogService {
	
	public boolean addLog(PcRdLoginfoT log) throws Exception;
	
	/**
	 * 获取LOG日志
	 * @param username 操作人
	 * @param logtype 日志类型
	 * @param logtime 日志时间
	 * @param pageNo 当前页
	 * @param sort 排序名称
	 * @param order 排序方向
	 * @param rowsPerpage 每页显示条数
	 * @return 日志
	 */
	public JSONObject searchLog(String username,String logtype, String logtime,int pageNo,String sort,String order,int rowsPerpage);
	
	public List<Object[]> searchUsername();

}
