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

import com.echo.dao.AreaInfoDao;
import com.echo.dto.PcCdAreaInfoT;
import com.echo.dto.PcCdZoneT;


public class AreaInfoDaoImpl extends HibernateDaoSupport implements AreaInfoDao,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3078060861794718243L;

	public void setSessionFactoryOverride(SessionFactory sessionFactory)
    {
      super.setSessionFactory(sessionFactory);
    } 

	
	@SuppressWarnings("unchecked")
	public List<PcCdAreaInfoT> searchArea(String hql) throws Exception{
		List<PcCdAreaInfoT> areaList = null;
		
		areaList = (List<PcCdAreaInfoT>)this.getHibernateTemplate().find(hql);
		
		return areaList;
	}


	@SuppressWarnings("unchecked")
	public List<Object[]> searchAreaInfo(final String boilersInfo,final int start, final int pagesize) {
		List<Object[]> resultList = this.getHibernateTemplate()
		 .executeFind(new HibernateCallback() {
			 String[] cloumnsName = {"QKID","ZONE_CODE","QKMC","QKMC_S","ZONE_NAME","ORG_ID"};
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
	public boolean removeStationInfo(String[] sqls) {
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
	@SuppressWarnings("unchecked")
	public List<PcCdZoneT> searchZones(String sql) throws Exception {
		List<PcCdZoneT> zones = null;
		zones = (ArrayList<PcCdZoneT>)this.getHibernateTemplate().find(sql);
		return zones;
	}


	public List<PcCdAreaInfoT> searchQkmc(String hql) throws Exception {
		List<PcCdAreaInfoT>  areaList  = null;
		areaList = (List<PcCdAreaInfoT>)this.getHibernateTemplate().find(hql);
		return areaList;
	}


	public boolean save(PcCdAreaInfoT area) throws Exception {
		Serializable flg = this.getHibernateTemplate().save(area);
		if(flg !=null && !"".equals(flg)){
			return true;
		}else{
		return false;
	}
	}


	public List<PcCdAreaInfoT> searchByName(String hql) throws Exception {
		List<PcCdAreaInfoT> areaList = null;
		areaList = (List<PcCdAreaInfoT>)this.getHibernateTemplate().find(hql);
		return areaList;
	}
	public boolean updateArea(PcCdAreaInfoT area) throws Exception {
		boolean flag  = true;
		this.getHibernateTemplate().merge(area);
		return flag;
	}
	
	

}
