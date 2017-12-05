package com.echo.dao;

import java.util.List;

public interface SagdghrdDao {

	public List<Object[]> searchSagdghRD(final String sql, final int start,final int pagesize);
	public List<Object[]> searchSagdghRD(String searchThinOil);
	public int getCounts(String sql);
}
