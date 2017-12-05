package com.echo.dao;

import java.util.List;

import net.sf.json.JSONArray;

import com.echo.dto.PcCdBoilerT;
import com.echo.dto.PcCdManifoldT;
import com.echo.dto.PcCdOrgT;

public interface ManifoldBasicDao {

	/**
	 * 查看单井信息
	 * @param user
	 * @return
	 */
	public List<Object[]> queryManifoldBasicInfo(String boilersInfo,int start,int pagesize);
	public List<Object[]> queryInfo(String hql);
	public List<PcCdBoilerT> queryBoiler(String hql);
	public List<PcCdOrgT> queryOrg(String hql);
	
	public List<PcCdManifoldT> searchManifold(String hql)throws Exception;
	
	public boolean addManifold(PcCdManifoldT pcCdManifoldT) throws Exception;
	
	public boolean updateManifold(PcCdManifoldT pcCdManifoldT) throws Exception;
	public boolean removeManifoldBasicInfo(String[] sqls)throws Exception;
	
	public List<Object[]> searchData(String thinOilWellRD)throws Exception;
	public List<Object[]> searchQKID(String sql)throws Exception;
}
