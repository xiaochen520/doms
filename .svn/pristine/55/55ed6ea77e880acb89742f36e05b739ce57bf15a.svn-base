package com.echo.service;


import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.dto.PcCdRightT;
import com.echo.dto.PcCdRoleT;

public interface RoleService {

	public List<PcCdRoleT> searchRole(String roleid,String rolename) throws Exception;
	
	public List<Object[]> searchRole(String rolename) throws Exception;
//	public JSONArray searchRole(String rolename,String roleid);
	public List<PcCdRightT> searchRightByIds(String[] rightNames)throws Exception;
//	public JSONArray searchmenu();
	public boolean addRole(PcCdRoleT role)throws Exception;
	public boolean removeUser(String roleId)throws Exception;
	public boolean updateRole(PcCdRoleT role) throws Exception;
	public JSONArray searchRoleInfo(String roleid)throws Exception;
	public JSONObject  searchAccRoles (String roleid)throws Exception;
	
	
	public JSONArray RoleAccredit(String roleid)throws Exception;
//	public JSONArray searchAllRight();
	


}
