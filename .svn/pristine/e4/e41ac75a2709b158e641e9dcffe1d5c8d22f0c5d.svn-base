package com.echo.dao;

import java.util.List;

import com.echo.dto.PcCdGasWellT;

public interface GasWellDao {
	
	public List<Object[]> searchGasWell(final String sql, final int start,final int pagesize);
	
	public int getCounts(String sql);
	
	public boolean removeStationInfo(String[] arg);
	public List<Object[]> queryInfo(String hql);

	public List<PcCdGasWellT> searchGasName(String hql) throws Exception;

	public boolean save(PcCdGasWellT gas)throws Exception;

	public List<PcCdGasWellT> searchGasById(String hql) throws Exception;

	public boolean updateGas(PcCdGasWellT gas) throws Exception;

	public List<Object[]> searchData(String thinOilWellRD)throws Exception;
}
