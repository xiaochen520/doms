package com.echo.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transaction;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.echo.dao.FloodingRPDDao;
import com.echo.dao.Query;
import com.echo.dto.PcRpdWaterfloodingWellT;

public class FloodingRPDDaoImpl extends HibernateDaoSupport implements FloodingRPDDao ,Serializable{

	@Override
	public List<Object[]> searchData(final String thinOilWellRD, final int start,
			final int rowsPerpage, final String[] cloumnsName) {
		List<Object[]> lo = this.getHibernateTemplate().executeFind(new HibernateCallback() {
			
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				SQLQuery s = session.createSQLQuery(thinOilWellRD);
				s.setFirstResult(start);
				s.setMaxResults(rowsPerpage);
				return s.list();
			}
		});
		return lo;
	}

	@Override
	public List<Object[]> searchDataEX(String sql) {
		List<Object[]> lo = new ArrayList<Object[]>();
		 lo =Query.getSqlQuery(this.getHibernateTemplate(), sql);
		return lo;
	}

	@Override
	public boolean removeWaterRPD(String sql) throws Exception {
		SessionFactory sessionFactory = this.getSessionFactory();
		Session  session = sessionFactory.openSession();
		org.hibernate.Transaction tx  = session.beginTransaction();
		tx.begin();
		org.hibernate.Query query = session.createSQLQuery(sql);
		query.executeUpdate();
		tx.commit();
		session.close();
		return true;
	}

	@Override
	public boolean addOrUpdate(PcRpdWaterfloodingWellT rpd) throws Exception {
		boolean flag = true;
		this.getHibernateTemplate().merge(rpd);
		return flag;
	}

	@Override
	public List<Object[]> searchWFLoodID(String sql) {
		return Query.getSqlQuery(this.getHibernateTemplate(), sql);
	}

}
