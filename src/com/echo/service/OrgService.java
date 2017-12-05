package com.echo.service;


import java.util.List;

import com.echo.dto.PcCdOrgT;

public interface OrgService {

	public List<Object[]> searchOrg(String jbm, String orgid, String sql) throws Exception;
	
}
