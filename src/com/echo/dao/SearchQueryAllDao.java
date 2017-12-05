package com.echo.dao;

import java.util.List;

public interface SearchQueryAllDao {

	public	List<Object[]> queryGroupInfo(String sql) throws Exception;

}
