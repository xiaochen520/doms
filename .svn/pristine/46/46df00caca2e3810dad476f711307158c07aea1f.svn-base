package com.echo.dao;

import java.util.List;

import com.echo.dto.PcCdBoilerT;
import com.echo.dto.PcCdOrgT;

public interface BoilerBasicDao {

	/**
	 * 查看单井信息
	 * @param user
	 * @return
	 */
	public List<Object[]> queryBoilerBasicInfo(String boilersInfo,int start,int pagesize);
	public List<Object[]> queryInfo(String hql);
	public boolean removeBoilerBasicInfo(String arg);
	public boolean removeBoilerOrgInfo(PcCdBoilerT boiler,PcCdOrgT org);
	public PcCdOrgT queryOrg(String orgId);
	public PcCdBoilerT queryBoiler(String boilerId);
	public List<PcCdBoilerT> serchBoiler(String hql)throws Exception;
	public boolean addOrModifyBoilerOrgInfo(PcCdBoilerT boiler);
//	public JSONObject cascadeInit();
	public List<Object[]> searchData(String thinOilWellRD)throws Exception;
}
