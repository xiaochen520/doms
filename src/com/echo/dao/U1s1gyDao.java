package com.echo.dao;

import java.util.List;

import com.echo.dto.PcRpdU1WaterAutoT;
import com.echo.dto.PcRpdU2TSJY;
import com.echo.dto.PcRpdU2TSWS;
import com.echo.dto.PcRpdU2TSZH;
import com.echo.dto.PcRpdU2WaterAutoT;

public interface U1s1gyDao {

	public List<Object[]> searchU1s1gy(String timeCalssql) throws Exception;

	public List<PcRpdU1WaterAutoT> SreachU1S1gy(String hql)throws Exception;
	
	public List<PcRpdU2WaterAutoT> SreachU2S1gy(String hql)throws Exception;
	public List<PcRpdU2TSJY> SreachTSJY(String hql)throws Exception;
	public List<PcRpdU2TSWS> SreachTSWS(String hql)throws Exception;
	public List<PcRpdU2TSZH> SreachTSZH(String hql)throws Exception;

	public boolean updateU1S1gy(PcRpdU1WaterAutoT us)throws Exception;
	public boolean updateU2S1gy(Object us)throws Exception;


}
