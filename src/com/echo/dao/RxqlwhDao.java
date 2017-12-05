package com.echo.dao;

import java.util.List;

public interface RxqlwhDao {

	public List<Object[]> searchDataEX(String mosaicSql)throws Exception;

	public List<Object[]> searchData(final String mosaicSql, final int start,
			final int rowsPerpage, final String[] cloumnsName)throws Exception;

}
