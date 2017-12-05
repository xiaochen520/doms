package com.echo.service;

import java.util.List;

import net.sf.json.JSONObject;

import com.echo.dto.PcCdDepartmentT;

public interface DepartmentService {
	
	public List<PcCdDepartmentT> searchDepartment (String department,String departmentname) throws Exception;
	
	public JSONObject searchDeps (String departmentname ,int pageNo,String sort,String order,int rowsPerpage) throws Exception;
	
	public boolean addDep(PcCdDepartmentT dep) throws Exception;
	
	public boolean updateDep(PcCdDepartmentT dep) throws Exception;
	
	public boolean removeDep(String depId,String org_id) throws Exception;

	
}
