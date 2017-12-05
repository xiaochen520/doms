package com.echo.dao;

import java.util.List;

import com.echo.dto.PcRdLoginfoT;

public interface LogDao {

	/**
	 * 
	 * @param user
	 * @return
	 * @throws Exception 
	 */
	public boolean save(PcRdLoginfoT log) throws Exception;
	
	public List<Object[]> seachLog(String sql,int start,int pagesize);
	
	public List<Object[]> seachUsername(String sql);
	
}
