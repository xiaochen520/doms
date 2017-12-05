package com.echo.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.echo.dao.Query;
import com.echo.dao.RoleDao;
import com.echo.dto.PcCdRoleT;


public class RoleDaoImpl extends HibernateDaoSupport implements RoleDao,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3078060861794718243L;

	public void setSessionFactoryOverride(SessionFactory sessionFactory)
    {
      super.setSessionFactory(sessionFactory);
    } 

	
	@SuppressWarnings("unchecked")
	public List<PcCdRoleT> searchRole(String hql) throws Exception{
		List<PcCdRoleT> roleList = null;
		
		roleList = (List<PcCdRoleT>)this.getHibernateTemplate().find(hql);
		
		return roleList;
	}
	
	
	public boolean save(PcCdRoleT role) throws Exception{
		 Serializable flg =  this.getHibernateTemplate().save(role);
			// TODO Auto-generated catch block
		 if(flg != null && !"".equals(flg)){
			 return true;
		 }else{
			 return false;
		 }
		
	
	}

	public boolean removeRole(String[] sqls) throws Exception{

		SessionFactory sessionFactory=this.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		Transaction tx = session.beginTransaction();  
		tx.begin();
		for(int i = 0;i < sqls.length; i++){
			org.hibernate.Query query  =  session.createSQLQuery(sqls[i]);
			query.executeUpdate();
		}
		
		tx.commit();
		session.close();

		return true;
	}


	public boolean updateRole(PcCdRoleT role) throws Exception{
		boolean flg = true;
		try {
			this.getHibernateTemplate().merge(role);
		} catch (Exception e) {
			flg = false;
		}
		return flg;
	}


	public List<Object[]> searchRoles(final String hql,final List<String> cloumnsName) throws Exception {
		List<Object[]> resultList = this.getHibernateTemplate()
		 .executeFind(new HibernateCallback() {
		public List<Object[]> doInHibernate(Session session)
		 throws HibernateException, SQLException {
				SQLQuery s = session.createSQLQuery(hql);
				for (String cloumn : cloumnsName) {
					s.addScalar(cloumn);
				}
				
				return s.list();
			}
		 });
		
		return resultList;
	}


	@Override
	public List<Object[]> searchRoles(String hql) throws Exception {
		List<Object[]> roleList = null;
		
		roleList = Query.getSqlQuery(this.getHibernateTemplate(), hql);
		
		return roleList;
	}




}
