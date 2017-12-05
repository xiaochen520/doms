package com.echo.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.echo.dao.Query;
import com.echo.dao.SearchQueryAllDao;

public class SearchQueryAllDaoImpl extends HibernateDaoSupport implements SearchQueryAllDao ,Serializable{

	@Override
	public List<Object[]> queryGroupInfo(String sql) throws Exception {
		return Query.getSqlQuery(this.getHibernateTemplate(), sql);
	} 

}
