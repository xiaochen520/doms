package com.echo.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.echo.dao.ProducedRDDao;
import com.echo.dao.Query;

public class ProducedRDDaoImpl extends HibernateDaoSupport implements ProducedRDDao,Serializable{

	public int getCounts(String sql) {
		int count = 0;
		List<Object[]> list = new ArrayList<Object[]>();
		list = Query.getSqlQuery(this.getHibernateTemplate(), sql);
		count = Integer.parseInt(String.valueOf(list.get(0))) ;
		return count;
	}

	public List<Object[]> searchProduct(String product) {
		List<Object[]> lo = Query.getSqlQuery(this.getHibernateTemplate(), product);
		return lo;
	}

	
	@SuppressWarnings("unchecked")
	public List<Object[]> searchProduct(final String boilersInfo,final int start, final int pagesize,final String[] cloumnsName) {
		List<Object[]> resultList = this.getHibernateTemplate()
		 .executeFind(new HibernateCallback() {
		public List<Object[]> doInHibernate(Session session)
		 throws HibernateException, SQLException {
				SQLQuery s = session.createSQLQuery(boilersInfo);
				for (String cloumn : cloumnsName) {
					s.addScalar(cloumn);
				}
				s.setFirstResult(start);
				s.setMaxResults(pagesize);
				return s.list();
			}
		 });
		return resultList;
	}

}
