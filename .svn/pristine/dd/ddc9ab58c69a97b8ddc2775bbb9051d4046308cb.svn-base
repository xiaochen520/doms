package com.echo.dao;

import java.util.List;

import com.echo.dto.PcCdOrgT;
import com.echo.dto.PcCdWaterSourceWellT;

public interface WaterSoWellDao {

//查询区块
	public List<Object[]> queryWorkarea(String sql);
//查询站号
	public List<Object[]> queryWellarea(String sql);
//查询水源井

	public List<Object[]> queryWellmonth(String sql);

	public PcCdWaterSourceWellT queryWaterSourceWell(String waterSourceWellid);

	public List<PcCdOrgT> queryOrg(String orgId);

	public List<Object[]> searchWaterSoWell(final String sql, final int start,final int pagesize);
	
	public int getCounts(String sql);
	
	public boolean removeStationInfo(String[] arg);

	public List<Object[]> queryInfo(String hql);	
	
	public List<PcCdWaterSourceWellT> searchwellLists(String hql)throws Exception;	
	
	public boolean save(PcCdWaterSourceWellT well)throws Exception;
	
	public boolean updateWaterSoWell(PcCdWaterSourceWellT well)throws Exception;
	
}
