package com.echo.dao;

import java.util.List;

import com.echo.dto.PcRpdUThinWaterAutoT;
import com.echo.dto.PcRpdUThinWaterGeneralT;

public interface XYWSCLDao {

	
	public List<Object[]> searchXYWSDatas(String sql);

	public List<PcRpdUThinWaterGeneralT> SreachWaterGeneral(String hql);
	public List<PcRpdUThinWaterAutoT> SreachWaterAuto(String hql);
	public boolean updateWaterGeneral(PcRpdUThinWaterGeneralT waterGen);
	public boolean updateWaterAuto(PcRpdUThinWaterAutoT waterAuto);

	public List<Object[]> searchCalcNum(String timeCalssql);
}
