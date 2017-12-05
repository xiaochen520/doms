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

import com.echo.dao.OilDrillingDao;
import com.echo.dao.Query;
import com.echo.dao.StationTDao;
import com.echo.dto.PcCdAreaInfoT;
import com.echo.dto.PcCdBoilerT;
import com.echo.dto.PcCdOildrillingStationT;
import com.echo.dto.PcCdStationT;
import com.echo.dto.PcCdOrgT;
import com.echo.dto.User;
public class OilDrillingDaoImpl extends HibernateDaoSupport implements OilDrillingDao,Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3078060661794718243L;

	public void setSessionFactoryOverride(SessionFactory sessionFactory)
    {
      super.setSessionFactory(sessionFactory);
    } 

	public List<Object[]> queryBoilerBasicInfo(String boilersInfo) {
		List<Object[]> lo = Query.getSqlQuery(this.getHibernateTemplate(), boilersInfo);
		return lo;
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> queryOilStation(final String boilersInfo,final int start, final int pagesize) {
		List<Object[]> resultList = this.getHibernateTemplate()
		 .executeFind(new HibernateCallback() {
			String[] cloumnsName = {"OILDRILLING_STATIONID","ORG_ID","A2ID","OILDRILLING_STATION_NAME","STATUS_VALUE","RLAST_OPER","RLAST_ODATE","REMARK","PROD_SNS"};
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
	
	public List<Object[]> queryAreablockInfo(String hql) {
		return Query.getSqlQuery(this.getHibernateTemplate(), hql);
	}
	
	public List<Object[]> queryStationInfo(String hql) {
		return Query.getSqlQuery(this.getHibernateTemplate(), hql);
	}
	
	public List<Object[]> queryBoilersNameInfo(String hql) {
		return Query.getSqlQuery(this.getHibernateTemplate(), hql);
	}
	
	public boolean removeStationInfo(String[] sqls){
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
	
	public boolean removeBoilerOrgInfo(PcCdBoilerT boiler){
		boolean flg = true;
		try {
			this.getHibernateTemplate().delete(boiler);
			this.getHibernateTemplate().flush();
		} catch (Exception e) {
			e.printStackTrace();
			flg = false;
		}
		return flg;
	}
	
	public PcCdOrgT queryOrg(String orgId) {
		PcCdOrgT org = null;
		org = (PcCdOrgT)this.getHibernateTemplate().get(PcCdOrgT.class, orgId);
		return org;
	}
	
	public PcCdBoilerT queryBoiler(String boilerId) {
		PcCdBoilerT pcCdBoilerT = null;
		pcCdBoilerT = (PcCdBoilerT)this.getHibernateTemplate().get(PcCdBoilerT.class, boilerId);
		return pcCdBoilerT;
	}
	
	public boolean addOrModifyBoilerOrgInfo(PcCdBoilerT boiler){
		boolean flg = true;
		try {
			this.getHibernateTemplate().saveOrUpdate(boiler);
//			this.getHibernateTemplate().flush();
		} catch (Exception e) {
			e.printStackTrace();
			flg = false;
		}
		return flg;
	}

	public List<PcCdOildrillingStationT> searchOild(String hql)
			throws Exception {
		List<PcCdOildrillingStationT> oildList = null;
		
		oildList = (List<PcCdOildrillingStationT>)this.getHibernateTemplate().find(hql);
		
		return oildList;
	}

	public List<PcCdOildrillingStationT> searchoildrillingStationName(String hql)throws Exception {
		List<PcCdOildrillingStationT> oildq= null;
		oildq = (List<PcCdOildrillingStationT>)this.getHibernateTemplate().find(hql);
		return oildq;
	}

	public boolean save(PcCdOildrillingStationT oild) throws Exception {
		Serializable flg = this.getHibernateTemplate().save(oild);
		if(flg != null && !"".equals(flg)){
			return true;
		}else{
		return false;
		}
	}

	public List<PcCdOildrillingStationT> searchoild(String hql)throws Exception {
		List<PcCdOildrillingStationT> oild = null;
		oild = (List<PcCdOildrillingStationT>)this.getHibernateTemplate().find(hql);
		return oild;
	}

	public boolean updataOild(PcCdOildrillingStationT oild) throws Exception {
		boolean flag = true ; 
		this.getHibernateTemplate().merge(oild);
		return flag;
	}
}
