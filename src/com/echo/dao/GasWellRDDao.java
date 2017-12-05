package com.echo.dao;

import java.util.List;

import com.echo.dto.PcCdThinoilWellT;

public interface GasWellRDDao {
	
	public List<Object[]> searchData(final String sql, final int start,final int pagesize);
	public List<Object[]> searchData(String sql);
	public int getCounts(String sql);
	
}
