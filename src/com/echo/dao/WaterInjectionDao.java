package com.echo.dao;

import java.util.List;

import com.echo.dto.PcCdWaterInjectiontopryT;
import com.echo.dto.PcCdWellSagdT;

public interface WaterInjectionDao {
	
	public List<Object[]> searchwaterIT(final String sql, final int start,final int pagesize);
	
	public int getCounts(String sql);
	
	public boolean removeStationInfo(String[] arg);
	public List<Object[]> queryInfo(String hql);
	
	public List<PcCdWaterInjectiontopryT> getInjectiontoprys(String hql)throws Exception;
	
	
	public boolean saveINT(PcCdWaterInjectiontopryT pcCdWaterInjectiontopryT) throws Exception;
	
	public boolean updateINT(PcCdWaterInjectiontopryT pcCdWaterInjectiontopryT) throws Exception;

	public List<Object[]> searchData(String thinOilWellRD)throws Exception;
}
