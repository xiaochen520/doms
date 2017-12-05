package com.echo.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.echo.dao.IpghdbDao;
import com.echo.dao.Query;
import com.echo.dto.PcCdBoilerT;
import com.echo.dto.PcCdIpSegmentT;
import com.echo.dto.PcCdOildrillingStationT;
import com.echo.dto.PcCdOrgT;
public class IpghdbDaoImpl extends HibernateDaoSupport implements IpghdbDao,Serializable{
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
	public List<Object[]> queryIpghdb(final String ipInfo,final int start, final int pagesize) {
		List<Object[]> resultList = null;
		try {
			resultList = this.getHibernateTemplate()
			 .executeFind(new HibernateCallback() {
				String[] cloumnsName = {"segment_id","segment_code1","segment_code2","segment_name","ip_segment","ip_start","ip_end","is_used","used_count","explanation","remark","rlast_oper","rlast_odate"};
			public List<Object[]> doInHibernate(Session session)
			 throws HibernateException, SQLException {
					SQLQuery s = session.createSQLQuery(ipInfo);
					for (String cloumn : cloumnsName) {
						s.addScalar(cloumn);
					}
					s.setFirstResult(start);
					s.setMaxResults(pagesize);
					return s.list();
				}
			 });
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
	
	public boolean removeIpSeg(String sql){
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

	public List<PcCdIpSegmentT> searchIpSegByCode2(String hql)throws Exception {
		List<PcCdIpSegmentT> oildq= null;
		oildq = (List<PcCdIpSegmentT>)this.getHibernateTemplate().find(hql);
		return oildq;
	}

	public boolean save(PcCdIpSegmentT ipSeg) throws Exception {
		Serializable flg = this.getHibernateTemplate().save(ipSeg);
		if(flg != null && !"".equals(flg)){
			return true;
		}else{
		return false;
		}
	}

	public List<PcCdIpSegmentT> searchIpSeg(String hql)throws Exception {
		List<PcCdIpSegmentT> ipSeg = null;
		ipSeg = (List<PcCdIpSegmentT>)this.getHibernateTemplate().find(hql);
		return ipSeg;
	}

	public boolean updateIpSeg(PcCdIpSegmentT ipSeg) throws Exception {
		boolean flag = true ; 
		this.getHibernateTemplate().merge(ipSeg);
		return flag;
	}

	@Override
	public List<Object[]> queryInfo(String ipInfo) throws Exception {
		// TODO Auto-generated method stub
		return Query.getSqlQuery(this.getHibernateTemplate(), ipInfo);
	}
}
