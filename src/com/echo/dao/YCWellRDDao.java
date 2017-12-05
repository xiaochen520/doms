package com.echo.dao;

import java.util.List;

import com.echo.dto.PcCdThinoilWellT;

public interface YCWellRDDao {
	
	public List<Object[]> searchYC(final String sql, final int start,final int pagesize);
	public List<Object[]> searchYC(String searchYC);
	public int getCounts(String sql);
	
}
