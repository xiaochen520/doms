package com.echo.dao;

import java.util.List;

import com.echo.dto.PcCdWaterfloodingWellT;

public interface WaterFloodingDao {
	
	public List<Object[]> searchwaterFL(final String sql, final int start,final int pagesize);
	
	public int getCounts(String sql);
	
	public boolean removeStationInfo(String[] arg);
	public List<Object[]> queryInfo(String hql) ;

	public List<PcCdWaterfloodingWellT> searchWaterFlood(String hql)throws Exception;

	public boolean updateFlooding(PcCdWaterfloodingWellT well)throws Exception;

	public boolean save(PcCdWaterfloodingWellT well)throws Exception;

	public List<Object[]> searchData(String thinOilWellRD)throws Exception;

}
