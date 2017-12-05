package com.echo.dao;

import java.util.List;

import com.echo.dto.PcCdBlCellT;
import com.echo.dto.PcCdDepartmentT;

public interface BoilerlineDao {
	
	public List<PcCdDepartmentT> searchDep(String hql) throws Exception;
	
	public List<Object[]> searchBlines(String sql,int start,int pagesize) throws  Exception;
	
	public List<Object[]> searchObj(String sql) throws  Exception;
	
	public List<PcCdBlCellT> seachGLJKS(String hql) throws  Exception;
	
	public boolean save(PcCdDepartmentT dep) throws Exception;
	
	public boolean updateDep(PcCdDepartmentT dep) throws Exception;
	
	public boolean removeLX(String[] sqls) throws Exception;
	
	public boolean removeLXs(List<String> sqls) throws Exception;
	
	public List<Object[]> searchInfo(String hql);
	public boolean addOrUpdateBoilerLine(PcCdBlCellT blCell) throws Exception;
	public boolean  addBoilerLine (PcCdBlCellT blCell) throws Exception;
	public boolean seachSystemInit() throws Exception;
	public List<PcCdBlCellT> searchBoilerLineByName(String hql) throws Exception;
	
	public List<String> getSAGDFP() throws Exception;

}
