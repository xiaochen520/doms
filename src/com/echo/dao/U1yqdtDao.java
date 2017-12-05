package com.echo.dao;

import java.util.List;

public interface U1yqdtDao {

	public  int getCounts(String totalNum);

	public List<Object[]> serarchU1yqdt(final String sql, final int start,final int rowsPerpage, final String[] cloumnsName);

	public List<Object[]> serarchU1sqdt(final String sql, final int start,final int rowsPerpage, final String[] cloumnsName);

	public List<Object[]> searchEverySql(String product);



}
