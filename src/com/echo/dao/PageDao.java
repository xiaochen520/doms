package com.echo.dao;

public interface PageDao {
	/**
	 * 获取分页总条数
	 * @param sql
	 * @return
	 */
	public int getCounts(String sql);
	/**
	 *  获取分页总条数
	 * @param sql
	 * @param obj 对象表明
	 * @return
	 */
	public int getCounts(String sql,Object obj);
}
