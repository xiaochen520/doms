package com.echo.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.echo.dao.OrgDao;
import com.echo.dao.Query;
import com.echo.dto.PcCdOrgT;

public class OrgDaoImpl extends HibernateDaoSupport implements OrgDao,Serializable {

	@Override
	public List<Object[]> searchOrg(String hql) throws Exception{
		// TODO Auto-generated method stub
		return Query.getSqlQuery(this.getHibernateTemplate(), hql);
	}

}
