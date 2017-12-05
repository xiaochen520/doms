package com.echo.dao;
import java.util.List;

import com.echo.dto.PcCdBoilerT;
import com.echo.dto.PcCdOildrillingStationT;
import com.echo.dto.PcCdStationT;
import com.echo.dto.PcCdOrgT;

public interface OilDrillingDao {

	/**
	 * 查看单井信息
	 * @param user
	 * @return
	 */
	public List<Object[]> queryOilStation(String boilersInfo,int start,int pagesize);

	public boolean removeStationInfo(String[] arg);

	public List<PcCdOildrillingStationT> searchOild(String hql) throws Exception;

	public List<PcCdOildrillingStationT> searchoildrillingStationName(String hql) throws Exception;

	public boolean save(PcCdOildrillingStationT oild) throws Exception;

	public List<PcCdOildrillingStationT> searchoild(String hql) throws  Exception;

	public boolean updataOild(PcCdOildrillingStationT oild) throws Exception;
	

}
