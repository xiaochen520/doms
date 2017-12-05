package com.echo.dao;

import java.util.List;

import com.echo.dto.PcCdBoilerT;
import com.echo.dto.PcCdBranchingT;
import com.echo.dto.PcCdOrgT;

public interface BranchingBasicDao {

	/**
	 * 查看单井信息
	 * @param user
	 * @return
	 */
	public List<Object[]> queryBranchingBasicInfo(String boilersInfo,int start,int pagesize);
	public List<Object[]> queryInfo(String hql);
	public boolean removeBoilerBasicInfo(String arg);
	public boolean removeBoilerOrgInfo(PcCdBoilerT boiler);
	public List<PcCdBoilerT> queryBoiler(String hql);
	public List<PcCdOrgT> queryOrg(String hql);
	public List<PcCdBranchingT> searchBranchs(String hql) throws Exception;
	public boolean saveBranch(PcCdBranchingT pcCdBranchingT) throws Exception;
	
	public boolean updateBranch(PcCdBranchingT pcCdBranchingT) throws Exception;
	public boolean removeBranch(String sql)throws Exception;
}
