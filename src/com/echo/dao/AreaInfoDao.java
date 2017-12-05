package com.echo.dao;

import java.util.List;

import com.echo.dto.PcCdAreaInfoT;
import com.echo.dto.PcCdZoneT;

public interface AreaInfoDao {
	public List<PcCdAreaInfoT> searchArea(String hql) throws Exception;
	
	public List<Object[]> searchAreaInfo(String boilersInfo,int start,int pagesize);

	public boolean removeStationInfo(String[] arg);

	public List<PcCdZoneT> searchZones(String hql) throws Exception;

	public List<PcCdAreaInfoT> searchQkmc(String hql) throws  Exception;

	public boolean save(PcCdAreaInfoT area) throws Exception;

	public List<PcCdAreaInfoT> searchByName(String hql) throws  Exception;

	public boolean updateArea(PcCdAreaInfoT area) throws Exception;

}