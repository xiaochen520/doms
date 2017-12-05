package com.echo.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.echo.dao.BjfzDao;
import com.echo.dao.Query;
import com.echo.dto.PcCdAlarmManagT;

public class BjfzDaoImpl extends HibernateDaoSupport implements BjfzDao,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void setSessionFactoryOverride(SessionFactory sessionFactory)
    {
      super.setSessionFactory(sessionFactory);
    } 
	
	
	@Override
	public int getCounts(String sql) {
		int count = 0;
		List<Object[]> list = new ArrayList<Object[]>();
		list = Query.getSqlQuery(this.getHibernateTemplate(), sql);
		count = Integer.parseInt(String.valueOf(list.get(0))) ;
		return count;
	}

	@Override
	public List<Object[]> searchBjxzcx(String searchbjxzcx) {
		List<Object[]> lo = Query.getSqlQuery(this.getHibernateTemplate(), searchbjxzcx);
		return lo;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> searchBjxzcx(final String sql, final int start,final int pagesize) {
		List<Object[]> resultList = this.getHibernateTemplate()
				 .executeFind(new HibernateCallback(){
			String[] cloumnsName = {"sqr","acquisition_time","spr","zxr","bqmc","zxqk"}; 
			 public List<Object[]> doInHibernate(Session session)
					 throws HibernateException, SQLException {
							SQLQuery s = session.createSQLQuery(sql);
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


	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> searchBjzsk(final String bjfzzskInfo,final  int start,
			final int rowsPerpage) {
		List<Object[]> resultList = this.getHibernateTemplate()
				 .executeFind(new HibernateCallback() {
					 String[] cloumnsName = {"zsk_id","miaoshu","csyy","clfs"};
				public List<Object[]> doInHibernate(Session session)
				 throws HibernateException, SQLException {
						SQLQuery s = session.createSQLQuery(bjfzzskInfo);
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
	public boolean removeBjzsk(String sql) {
		SessionFactory sessionFactory=this.getSessionFactory();
		Session session = sessionFactory.openSession();		
		Transaction tx = session.beginTransaction();  
		tx.begin();
			org.hibernate.Query query  =  session.createSQLQuery(sql);
			query.executeUpdate();
		tx.commit();
		session.close();

		return true;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> searchDxyhgl(final String bjfzzskInfo, final int start,
			final int rowsPerpage) {
		List<Object[]> resultList = this.getHibernateTemplate()
				 .executeFind(new HibernateCallback() {
					 String[] cloumnsName = {"alarmuserid","username","phone","dept","alarm_key","dep_manag","data_unit","remark"};
				public List<Object[]> doInHibernate(Session session)
				 throws HibernateException, SQLException {
						SQLQuery s = session.createSQLQuery(bjfzzskInfo);
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
	public boolean updateUser(PcCdAlarmManagT alarmMana) {
		 boolean flg = true;
		 this.getHibernateTemplate().merge(alarmMana);
		 return flg;
	}


	@Override
	public List<Object[]> searchEverySql(String sql) {
		return Query.getSqlQuery(this.getHibernateTemplate(), sql);
	}


	@Override
	public List<Object[]> searchDxcx(String searchdxts) {
		List<Object[]> lo = Query.getSqlQuery(this.getHibernateTemplate(), searchdxts);
		return lo;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> searchDxcx(final String searchdxts,final int start,
			final int rowsPerpage) {
		List<Object[]> resultList = this.getHibernateTemplate()
				 .executeFind(new HibernateCallback() {
			String[] cloumnsName = {"jsbm","acquisition_time","jsr","szdts","tsbm","tsr"};
				public List<Object[]> doInHibernate(Session session)
				 throws HibernateException, SQLException {
						SQLQuery s = session.createSQLQuery(searchdxts);
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
	public List<Object[]> searchAlarmName(String hql) {
		List<Object[]> userList = null;
		userList = Query.getSqlQuery(this.getHibernateTemplate(), hql);
		return userList;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<PcCdAlarmManagT> searchUser(String hql) {
		List<PcCdAlarmManagT> userList = null;		
		userList = (List<PcCdAlarmManagT>)this.getHibernateTemplate().find(hql);		
		return userList;
	}


	@Override
	public boolean removeUser(String sql) {
		SessionFactory sessionFactory=this.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		Transaction tx = session.beginTransaction();  
		tx.begin();
		org.hibernate.Query query  =  session.createSQLQuery(sql);
		query.executeUpdate();
		tx.commit();
		session.close();
		return true;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> searchBjxzsp(final String string,final int start,final int rowsPerpage) {
		List<Object[]> resultList = this.getHibernateTemplate()
				 .executeFind(new HibernateCallback() {
			String[] cloumnsName = {"acquisition_time","spbmc","sqry","zxzt"};
				public List<Object[]> doInHibernate(Session session)
				 throws HibernateException, SQLException {
						SQLQuery s = session.createSQLQuery(string);
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




}
