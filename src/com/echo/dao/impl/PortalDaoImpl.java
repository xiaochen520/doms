package com.echo.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.echo.dao.PortalDao;
import com.echo.dao.Query;
import com.echo.dto.PcCdPortalMenuT;
import com.echo.dto.PcCdPortalParamT;


public class PortalDaoImpl extends HibernateDaoSupport implements PortalDao,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3078060861794718243L;

	public void setSessionFactoryOverride(SessionFactory sessionFactory)
    {
      super.setSessionFactory(sessionFactory);
    } 

	
	@SuppressWarnings("unchecked")
	public List<PcCdPortalMenuT> searchPortal(String hql) throws Exception{
		List<PcCdPortalMenuT> portalList = null;
		
		portalList = (List<PcCdPortalMenuT>)this.getHibernateTemplate().find(hql);
		
		return portalList;
	}
	
	
	public boolean savePortal(PcCdPortalMenuT portal) throws Exception{
		 Serializable flg =  this.getHibernateTemplate().save(portal);
		 if(flg != null && !"".equals(flg)){
			 return true;
		 }else{
			 return false;
		 }
		
	
	}

	public boolean removePortal(String[] sqls) throws Exception{

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

	public boolean removePortalParam(String sqls) throws Exception{

		SessionFactory sessionFactory=this.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		Transaction tx = session.beginTransaction();  
		tx.begin();
		//for(int i = 0;i < sqls.length; i++){
			org.hibernate.Query query  =  session.createSQLQuery(sqls);
			query.executeUpdate();
		//}
		
		tx.commit();
		session.close();

		return true;
	}
	public boolean updatePortal(PcCdPortalMenuT portal) throws Exception{
		boolean flg = true;
		try {
			this.getHibernateTemplate().merge(portal);
		} catch (Exception e) {
			flg = false;
		}
		return flg;
	}


	public List<Object[]> searchPortals(String sql) throws Exception {
		List<Object[]> portalList = null;
		portalList = Query.getSqlQuery(this.getHibernateTemplate(), sql);
		return portalList;
	}


	public List<Object[]> searchMenuname(String sql) {
		List<Object[]> lo  = new ArrayList<Object[]>();
		lo =  Query.getSqlQuery(this.getHibernateTemplate(), sql);
		return lo;
	}


	@Override
	public boolean addOrUpdatePortal(PcCdPortalParamT param) {
		boolean flag  = true;
		this.getHibernateTemplate().merge(param);
		return flag;
	}


	@Override
	public List<PcCdPortalParamT> searchPor(String sql) {
		List<PcCdPortalParamT> portalList = null;
		
		portalList = (List<PcCdPortalParamT>)this.getHibernateTemplate().find(sql);
		
		return portalList;
	}


	@Override
	public boolean addOrUpdatePortalMenu(PcCdPortalMenuT menu) {
		boolean flag  = true;
		this.getHibernateTemplate().merge(menu);
		return flag;
	}


	@Override
	public List<Object[]> searchParam(String sql) throws Exception {
		return Query.getSqlQuery(this.getHibernateTemplate(), sql);
	}
}
