package com.echo.dao;

import java.util.List;

import com.echo.dto.PcCdRightT;

public interface RightDao {
	public List<PcCdRightT> searchRight(String hql) throws Exception;
	
	public List<Object[]> searchAllRight(String hql) throws Exception;

}