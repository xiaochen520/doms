package com.echo.dao;

import java.util.List;

import com.echo.dto.PcRpdWaterfloodingWellT;

public interface FloodingRPDDao {

public 	List<Object[]> searchData(final String thinOilWellRD, final int start, final int rowsPerpage,
		final String[] cloumnsName);

public 	List<Object[]> searchDataEX(String thinOilWellRD);

public boolean removeWaterRPD(String sql)throws Exception;

public boolean addOrUpdate(PcRpdWaterfloodingWellT rpd)throws Exception;

public List<Object[]> searchWFLoodID(String sql);

}
