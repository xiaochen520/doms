package com.echo.dao;

import java.util.List;

import com.echo.dto.PcRpdU1OilAutoT;

public interface U1CZYDao {
	
	public int getCounts(String sql);
	public List<Object[]> queryInfo(String hql);
	public List<Object[]> searchU1CZY(String sql);
	//public boolean addOrUpdateSagddRPD(PcRpdWellSagddT prws) throws Exception;
	public List<String> dayreport() throws Exception;
	public List<PcRpdU1OilAutoT> SreachOILAuto(String hql)throws Exception;
	public List<Object[]> searchCalcNum(String timeCalssql)throws Exception;
	public boolean updateOILAuto(PcRpdU1OilAutoT oil)throws Exception;
}
