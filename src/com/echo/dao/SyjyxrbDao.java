package com.echo.dao;

import com.echo.dto.PcRpdMollifierT;
import com.echo.dto.PcRpdWaterSourceWelldT;

public interface SyjyxrbDao {

	public boolean addOrUpdateDatas(PcRpdWaterSourceWelldT wt);

	public boolean saveOrUpdateDatas(PcRpdMollifierT wt)throws Exception;


}
