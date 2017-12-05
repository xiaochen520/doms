package com.echo.dao;
import java.util.List;

import com.echo.dto.PcCdBoilerT;
import com.echo.dto.PcCdGasstation;
import com.echo.dto.PcCdOrgT;

public interface GasstationDao {

	/**
	 * 查看单井信息
	 * @param user
	 * @return
	 */
	public List<Object[]> queryStationBasicInfo(String boilersInfo,int start,int pagesize,String[] cloumnsName);
	public List<Object[]> queryInfo(String hql);
	public boolean removeStationInfo(String[] arg);
	public boolean removeBoilerOrgInfo(PcCdBoilerT boiler);
	public PcCdOrgT queryOrg(String orgId);
	public PcCdBoilerT queryBoiler(String boilerId);
	public boolean addOrModifyBoilerOrgInfo(PcCdBoilerT boiler);
	//添加
	public boolean save(PcCdGasstation sta)throws Exception;
	public boolean updateDep(PcCdGasstation sta)throws Exception;
	
	
	public List<PcCdGasstation> searchStations(String hql)throws Exception;
	public List<PcCdGasstation> searchblockstationName(String hql) throws Exception;

}
