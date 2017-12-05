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
import com.echo.dao.GasstationDao;
import com.echo.dto.PcCdBoilerT;
import com.echo.dto.PcCdGasstation;
import com.echo.dto.PcCdOrgT;
import com.echo.dto.PcCdUserT;
import com.echo.dto.User;
public class GasstationDaoImpl extends HibernateDaoSupport implements GasstationDao,Serializable{
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
	public List<Object[]> queryStationBasicInfo(final String boilersInfo,final int start, final int pagesize,final String[] cloumnsName) {
		List<Object[]> resultList = this.getHibernateTemplate()
		 .executeFind(new HibernateCallback() {
			//String[] cloumnsName = {"BLOCKSTATIONID","ORG_ID","A2ID","BLOCKSTATION_NAME","BS_TYPE","QKMC","OILDRILLING_NAME","GHS","CYGS","CYGGG","ZYBS","JRBZ","STATUS_VALUE","COMMISSIONING_DATE","RLAST_OPER","RLAST_ODATE","REMARK","GH_ID","PROD_SNS","YXZ","YXZID","LHZ","LHZID","QKID"};
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
	
	
	public List<Object[]> queryInfo(String hql) {
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
//添加
	public boolean save(PcCdGasstation sta) throws Exception {
		Serializable flg =  this.getHibernateTemplate().save(sta);
		 if(flg != null && !"".equals(flg)){
			 return true;
		 }else{
			 return false;
		 }
		
	}
	//修改
	public boolean updateDep(PcCdGasstation sta) throws Exception {
		boolean flg = true;
		 this.getHibernateTemplate().merge(sta);
		return flg;
	}

	public List<PcCdGasstation> searchStations(String hql) throws Exception {
		List<PcCdGasstation> gasstations = null;
		
		gasstations = (List<PcCdGasstation>)this.getHibernateTemplate().find(hql);
		
		return gasstations;
	}

	public List<PcCdGasstation> searchblockstationName(String hql)throws Exception {
		List<PcCdGasstation> gasstations1 = null;
		
		gasstations1 = (List<PcCdGasstation>)this.getHibernateTemplate().find(hql);
		
		return gasstations1;
	}




}
