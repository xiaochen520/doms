package com.echo.dao;
import java.util.List;

import com.echo.dto.PcCdBoilerT;
import com.echo.dto.PcCdStationT;
import com.echo.dto.PcCdOrgT;

public interface StationTDao {

	/**
	 * 查看单井信息
	 * @param user
	 * @return
	 */
	public List<Object[]> queryStationBasicInfo(String boilersInfo,int start,int pagesize);
	public List<Object[]> queryInfo(String hql);
	public boolean removeStationInfo(String[] arg);
	public boolean removeBoilerOrgInfo(PcCdBoilerT boiler);
	public PcCdOrgT queryOrg(String orgId);
	public PcCdBoilerT queryBoiler(String boilerId);
	public boolean addOrModifyBoilerOrgInfo(PcCdBoilerT boiler);
	//添加
	public boolean save(PcCdStationT sta)throws Exception;
	public boolean updateDep(PcCdStationT sta)throws Exception;
	
	
	public List<PcCdStationT> searchStations(String hql)throws Exception;
	public List<PcCdStationT> searchblockstationName(String hql) throws Exception;
	public List<Object[]> searchData(String thinOilWellRD) throws Exception;

}
