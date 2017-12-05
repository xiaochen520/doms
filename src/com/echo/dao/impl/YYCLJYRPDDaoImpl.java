package com.echo.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.echo.dao.Query;
import com.echo.dao.YYCLJYRPDDao;


public class YYCLJYRPDDaoImpl extends HibernateDaoSupport implements YYCLJYRPDDao,Serializable{

	
	private static final long serialVersionUID = 1L;

	@Override
	public List<Object[]> searchU2OIL(String sql) {
		return Query.getSqlQuery(this.getHibernateTemplate(), sql);
	}
	

	
	
}
