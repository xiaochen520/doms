package com.echo.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.echo.dao.LineMeasureDao;
import com.echo.dao.Query;
import com.echo.dao.ReactorDao;

public class ReactorDaoImpl extends HibernateDaoSupport implements ReactorDao,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Override
	public List<Object[]> searchReactor(String sql) {
		// TODO Auto-generated method stub
		return Query.getSqlQuery(this.getHibernateTemplate(), sql);
	}

	
}
