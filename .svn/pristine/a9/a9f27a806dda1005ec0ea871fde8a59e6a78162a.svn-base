package com.echo.dao;

import java.util.List;


import com.echo.dto.PcCdObservationWellT;

public interface ObservationDao {
	
	public List<Object[]> searchObservationWell(final String sql, final int start,final int pagesize);
	
	public int getCounts(String sql);
	
	public List<Object[]> searchStructureJc(String hql);
	
	public boolean save(PcCdObservationWellT well) throws Exception;
	
	public String serachOrgByName(String name) throws Exception;

	public boolean updateObs(PcCdObservationWellT well) throws Exception;

	public List<PcCdObservationWellT> searchObserName(String hql) throws Exception;
	public List<Object[]> queryInfo(String hql);

	public List<Object[]> queryStation(String sql) throws Exception;

	public boolean removeObswell(String[] sqls) throws Exception;
}
