package com.echo.dao;

import java.util.List;

import com.echo.dto.BoilerRoomGasMonitoringData;

/**
 * 
 * @author 王博
 * @date 2017-5-3
 * @time 下午03:28:55
 * 
 */
public interface BoilerRoomGasMonitoringDataDao {

	public List<BoilerRoomGasMonitoringData> searchDataBySql(String str);

	public List<Object[]> searchDataBySql(final String sql, final int start,
			final int pagesize);

	public List<Object[]> searchData(final String sql, String[] cloumnsName,
			final int start, final int pagesize);

	public List<Object[]> searchData(String searchThinOil);

	public int getCounts(String sql);

}
