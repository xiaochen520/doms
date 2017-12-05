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

import com.echo.dao.GasWellRPDDao;
import com.echo.dao.Query;
import com.echo.dto.PcRpdGasWelldT;

public class GasWellRPDDaoImpl extends HibernateDaoSupport implements GasWellRPDDao, Serializable {

	@Override
	public int getCounts(String totalNum) {
		int count = 0;
		List<Object[]> list = new ArrayList<Object[]>();
		list = Query.getSqlQuery(this.getHibernateTemplate(), totalNum);
		count = Integer.parseInt(String.valueOf(list.get(0))) ;
		return count;
	}

	@Override
	public List<Object[]> searchGWRPD(final String sql, final int start, final int rowsPerpage, final String[] cloumnsName){
	List<Object[]> resultList = this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				SQLQuery s = session.createSQLQuery(sql);
				for (String column : cloumnsName) {
					s.addScalar(column);
				}
				s.setFirstResult(start);
				s.setMaxResults(rowsPerpage);
				return s.list();
			}
		});
		return resultList;
	}

	@Override
	public List<Object[]> searchGWRPDE(String sql){
		List<Object[]> lo = null;
		lo = Query.getSqlQuery(this.getHibernateTemplate(), sql);
		return lo;
	}

	@Override
	public List<Object[]> searchWelID(String sql) {
		List<Object[]> lo = null;
		lo = Query.getSqlQuery(this.getHibernateTemplate(), sql);
		return lo;
	}

	@Override
	public boolean saveOrupdateGasRPDWH(PcRpdGasWelldT wh) throws Exception {
		boolean flag= true;
		this.getHibernateTemplate().merge(wh);
		return flag;
	}

	@Override
	public List<PcRpdGasWelldT> searchOnly(String hql) throws Exception {
		List<PcRpdGasWelldT> lista = null;
		 lista =(List<PcRpdGasWelldT>) this.getHibernateTemplate().find(hql);
		return lista;
	}

	@Override
	public boolean removeGasRPDWH(String sql) throws Exception {
		SessionFactory  sessionFactory = this.getSessionFactory();
		Session session = sessionFactory.openSession();
		org.hibernate.Transaction tx  = session.beginTransaction();
		tx.begin();
		org.hibernate.Query query = session.createSQLQuery(sql);
		query.executeUpdate();
		tx.commit();
		session.close();
		return true;
	}

}
