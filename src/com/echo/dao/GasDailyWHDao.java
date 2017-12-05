package com.echo.dao;

import java.util.List;

import com.echo.dto.PcRpdGasDailyT;
import com.echo.dto.PcRpdGasDailybT;
import com.echo.dto.PcRpdThinWellwT;
import com.echo.dto.PcRpdThinWellwbT;

public interface GasDailyWHDao {


	public List<Object[]> searchDataEX(String thinOilWellRD);

	public List<Object[]> searchData(String thinOilWellRD, int start, int rowsPerpage,
			String[] cloumnsName);

	public boolean saveRPD(PcRpdGasDailyT rpdT)throws Exception;

	public List<Object[]> searchWellID(String sql);

	public boolean saveRPDB(PcRpdGasDailybT rpdT)throws Exception;


}
