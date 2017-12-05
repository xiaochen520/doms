package com.echo.dao;

import java.util.List;

import com.echo.dto.PcCdThinoilWellT;

public interface ThinOilWellDao {
	
	public List<Object[]> searchThinOil(final String sql, final int start,final int pagesize);
	
	public int getCounts(String sql);
	
	public boolean removeStationInfo(String[] arg);
	public List<Object[]> queryInfo(String hql);

	

	public List<PcCdThinoilWellT> searchByName(String hql) throws Exception;

	public List<PcCdThinoilWellT> searchById(String hql)throws Exception;

	public boolean save(PcCdThinoilWellT thin)throws Exception;

	public boolean updateThin(PcCdThinoilWellT thin)throws Exception;

	public List<Object[]> searchData(String thinOilWellRD)throws Exception;
}
