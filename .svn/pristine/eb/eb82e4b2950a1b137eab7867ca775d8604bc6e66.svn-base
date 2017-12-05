package com.echo.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.echo.dao.ManifoldRDADao;
import com.echo.dao.ProducedRDDao;
import com.echo.dao.Query;

public class ManifoldRDADaoImpl extends HibernateDaoSupport implements ManifoldRDADao, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3078060861794718243L;



	public void setSessionFactoryOverride(SessionFactory sessionFactory)
    {
      super.setSessionFactory(sessionFactory);
    } 


	@SuppressWarnings("unchecked")
	public List<Object[]> searchManifoldRD(final String boilersInfo,final int start, final int pagesize) {
		List<Object[]> resultList = this.getHibernateTemplate()
		 .executeFind(new HibernateCallback() {
			 
				String[] cloumnsName = {"OILSTATIONNAME","AREABLOCK","BLOCKSTATIONNAME","BRANCHINGNAME","PIPE_SINK","MANIFOLDRID","CJSJ","BLOCK","STATIONNO","TEMP","PRESS"};

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
	
	/**
	 * 获取总条数
	 * @param hibernateTemplate
	 * @param sql
	 * @return
	 */
	public int getCounts(String sql) {
		int count = 0;
		List<Object[]> list = new ArrayList<Object[]>();
		list = Query.getSqlQuery(this.getHibernateTemplate(), sql);
		count = Integer.parseInt(String.valueOf(list.get(0))) ;
		return count;
	}
	public List<Object[]> searchManifoldRD(String searchThinOil) {
		List<Object[]> lo = Query.getSqlQuery(this.getHibernateTemplate(), searchThinOil);
		return lo;
	}
}
