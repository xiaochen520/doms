package com.echo.service;

import java.util.List;

import net.sf.json.JSONObject;

import com.echo.dto.PcCdDepartmentT;
import com.echo.dto.PcCdTeamT;

public interface TeamService {
	
	public List<PcCdTeamT> searchTea (String department,String departmentname) throws Exception;
	
	public JSONObject searchDeps (String departmentname ,int pageNo,String sort,String order,int rowsPerpage) throws Exception;
	
	
	public boolean addOrupdateTeam(PcCdTeamT dep) throws Exception;
	
	public boolean removeTeam(String depId,String org_id) throws Exception;

	
}
