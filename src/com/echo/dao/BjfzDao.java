package com.echo.dao;

import java.util.List;

import com.echo.dto.PcCdAlarmManagT;


public interface BjfzDao {

	public int getCounts(String sql);

	public List<Object[]> searchBjxzcx(String searchbjxzcx);


	List<Object[]> searchBjxzcx(String sql, int start, int pagesize);

	public List<Object[]> searchBjzsk(String boilersInfo, int start,
			int rowsPerpage);

	public boolean removeBjzsk(String sql);

	public List<Object[]> searchDxyhgl(String bjfzzskInfo, int start,
			int rowsPerpage);

	public boolean updateUser(PcCdAlarmManagT alarmMana);

	public List<Object[]> searchEverySql(String sql);

	public List<Object[]> searchDxcx(String searchdxts);

	public List<Object[]> searchDxcx(String searchdxts, int start,
			int rowsPerpage);

	public List<Object[]> searchAlarmName(String hql);

	public List<PcCdAlarmManagT> searchUser(String hql);

	public boolean removeUser(String sql);

	public List<Object[]> searchBjxzsp(String string, int start, int rowsPerpage);
}
