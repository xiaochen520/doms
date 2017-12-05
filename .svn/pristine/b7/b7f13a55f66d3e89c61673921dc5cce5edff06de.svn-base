package com.echo.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.echo.dao.CrudeOilDao;
import com.echo.dao.Query;

public class CrudeOilDaoImpl extends HibernateDaoSupport implements CrudeOilDao ,Serializable{

	@Override
	public List<Object[]> searchCrudeOil(String sql) throws Exception {
		// TODO Auto-generated method stub
		return Query.getSqlQuery(this.getHibernateTemplate(), sql);
	}

}
