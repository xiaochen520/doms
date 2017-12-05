package com.echo.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.echo.dao.MeasuresDao;
import com.echo.dao.Query;

public class MeasuresDaoImpl extends HibernateDaoSupport implements MeasuresDao{

	@Override
	public List<Object[]> searchData(final String mosaicSql, final int start,final int rowsPerpage, final String[] cloumnsName) throws Exception {
		List<Object[]> resultList = this.getHibernateTemplate()
		 .executeFind(new HibernateCallback() {
		public List<Object[]> doInHibernate(Session session)
		 throws HibernateException, SQLException {
				SQLQuery s = session.createSQLQuery(mosaicSql);
				for (String cloumn : cloumnsName) {
					s.addScalar(cloumn);
				}
				s.setFirstResult(start);
				s.setMaxResults(rowsPerpage);
				return s.list();
			}
		 });
		return resultList;
	}

	@Override
	public List<Object[]> searchDataEX(String mosaicSql) throws Exception {
		return Query.getSqlQuery(this.getHibernateTemplate(), mosaicSql);
	}

}
