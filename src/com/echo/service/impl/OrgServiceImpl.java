package com.echo.service.impl;

import java.util.List;

import com.echo.dao.OrgDao;
import com.echo.dto.PcCdOrgT;
import com.echo.service.OrgService;

public class OrgServiceImpl implements OrgService {

	private OrgDao orgDao;
	
	public void setOrgDao(OrgDao orgDao) {
		this.orgDao = orgDao;
	}

	@Override
	public List<Object[]> searchOrg(String jbm,String orgid,String sql) throws Exception {
		// TODO Auto-generated method stub
		List<Object[]> orgList = null;
		String hql =sql;
		if(jbm !=null && !"".equals(jbm)){
			hql += "and o.code = '"+jbm+"'";
			
		}
		if(orgid !=null && !"".equals(orgid)){
			hql += " and s.org_Id <> '"+orgid+"'";
			
		}
		try {
			orgList = orgDao.searchOrg(hql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orgList;
	}

}
