package com.echo.dao;

import java.util.List;

import com.echo.dto.PcCdRuleWellT;
import com.echo.dto.PcRpdRuleWellProdT;
import com.echo.dto.PcRpdRuleWellProdbT;

public interface RuleWellDao {
	
	public List<Object[]> searchRullWell(final String sql, final int start,final int pagesize);
	
	public int getCounts(String sql);
	
	public boolean removeStationInfo(String[] arg);
	public List<Object[]> queryInfo(String hql);

	public List<PcCdRuleWellT> searchRullWellName(String hql) throws Exception;

	public boolean save(PcCdRuleWellT rule)throws Exception;

	public List<PcCdRuleWellT> searchRuleName(String hql) throws  Exception;

	public boolean updateRule(PcCdRuleWellT rule) throws Exception;

	public List<Object[]> querywellAreaSelf( final String sql)throws Exception;

	public List<Object[]> serarchWellAreaSelf(String sql)throws Exception;

	public List<Object[]> searchData(String thinOilWellRD)throws Exception;

	public List<Object[]> searchRuleWellRPD(String wellInfo, int start,int rowsPerpage, String[] cloumnsName)throws Exception;

	public List<PcRpdRuleWellProdT> searchOnlyNameRq(String hql)throws Exception;

	public boolean saveRpdRull(PcRpdRuleWellProdT rpdR)throws Exception;

	public boolean saveRpdRullB(PcRpdRuleWellProdbT rpdbR)throws Exception;

	public List<PcRpdRuleWellProdbT> searchOnlyNameRqB(String hql)throws Exception;

	public boolean removeRullRPDId(String sql)throws Exception;

	
}
