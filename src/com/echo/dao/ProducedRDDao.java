package com.echo.dao;

import java.util.List;

public interface ProducedRDDao {
	public int getCounts(String sql) throws Exception;

	public List<Object[]> searchProduct(String product);

	public List<Object[]> searchProduct(final String sql, final int start,final int pagesize,final String[] cloumnsName);

}
