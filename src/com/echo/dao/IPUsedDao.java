package com.echo.dao;

import java.util.List;

import com.echo.dto.PcCdIpUsedT;

public interface IPUsedDao {

	public int getCounts(String totalNum);

	public List<Object[]> searchDatas(final String product, final int start,final  int rowsPerpage, final String[] cloumnsName) throws Exception;

	public List<Object[]> searchDatas(String product)throws Exception;

	public List<Object[]> searchSql(String sql)throws Exception;

	public List<PcCdIpUsedT> searchIpId(String hql) throws Exception;

	public boolean saveIpUsed(PcCdIpUsedT ip)throws Exception;

	public List<Object[]> searchSegmentId(String sql)throws Exception;

}
