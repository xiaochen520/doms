package com.echo.dao;

import java.util.List;

import com.echo.dto.PcCdRoleT;

public interface RoleDao {
	public List<PcCdRoleT> searchRole(String hql) throws Exception;
	
	public boolean save(PcCdRoleT role)throws Exception;
	
	public boolean removeRole(String[] sqls)throws Exception;
	
	public boolean updateRole(PcCdRoleT role)throws Exception;
	
	
	public List<Object[]> searchRoles(String hql,List<String> cloumnsName) throws Exception;
	
	public List<Object[]> searchRoles(String hql) throws Exception;
	
	

}