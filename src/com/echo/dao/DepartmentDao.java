package com.echo.dao;

import java.util.List;

import com.echo.dto.PcCdDepartmentT;

public interface DepartmentDao {
	
	public List<PcCdDepartmentT> searchDep(String hql) throws Exception;
	
	public List<Object[]> searchDeps(String sql,int start,int pagesize) throws  Exception;
	
	public boolean save(PcCdDepartmentT dep) throws Exception;
	
	public boolean updateDep(PcCdDepartmentT dep) throws Exception;
	
	public boolean removeDep(String[] sqls) throws Exception;

}
