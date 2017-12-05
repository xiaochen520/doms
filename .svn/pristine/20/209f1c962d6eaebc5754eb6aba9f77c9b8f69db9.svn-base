package com.echo.dao;

import java.util.List;

import com.echo.dto.PcCdCombinationStationT;

public interface CombinationDao {
	
	public List<Object[]> searchCombination(final String sql, final int start,final int pagesize);
	
	public int getCounts(String sql);
	
	public boolean removeStationInfo(String[] arg);

	public List<PcCdCombinationStationT> searchCombinationStationName(String hql) throws Exception;

	public boolean save(PcCdCombinationStationT comb) throws Exception;

	public List<PcCdCombinationStationT> searchComb(String hql) throws Exception;

	public boolean updateComb(PcCdCombinationStationT comb) throws Exception;
}
