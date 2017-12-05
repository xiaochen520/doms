package com.echo.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import net.sf.json.JSONObject;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.echo.dao.BoilerBasicDao;
import com.echo.dao.Query;
import com.echo.dto.PcCdBoilerT;
import com.echo.dto.PcCdOrgT;

public class BoilerBasicDaoImpl extends HibernateDaoSupport implements BoilerBasicDao,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3078060861794718243L;

	public void setSessionFactoryOverride(SessionFactory sessionFactory)
    {
      super.setSessionFactory(sessionFactory);
    } 

	public List<Object[]> queryBoilerBasicInfo(String boilersInfo) {
		List<Object[]> lo = Query.getSqlQuery(this.getHibernateTemplate(), boilersInfo);
		return lo;
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> queryBoilerBasicInfo(final String boilersInfo,final int start, final int pagesize) {
		List<Object[]> resultList = this.getHibernateTemplate()
		 .executeFind(new HibernateCallback() {
			 String[] cloumnsName = {"BOILER_NAME","BOILERID","ORG_ID","YXZ","YXZID","BLOCKSTATIONNAME","BLOCKSTATIONNAMEID","AREABLOCK",
						"QKID","ACCEPT_UNIT","SQDW","A2ID","BOILER_TYPE","BOILER_CODE","RATING_CAPACITY","INJECTION_ALLOCATION_RATE","PROD_SNS",
						"STATUS_VALUE","JRBZ","SWITCH_IN_FLAG","LJJDID","LJJD_S","COMMISSIONING_DATE","DYEAR","FACTORY_UC","FACTORY_NF","RLAST_OPER","RLAST_ODATE","REMARK"};
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
	
	public boolean removeBoilerBasicInfo(String id){
		boolean flg = true;
		try {
			this.getHibernateTemplate().delete((PcCdBoilerT)this.getHibernateTemplate().get(PcCdBoilerT.class, id));
//			this.getHibernateTemplate().flush();
		} catch (Exception e) {
			e.printStackTrace();
			flg = false;
		}
		return flg;
	}
	
	public boolean removeBoilerOrgInfo(PcCdBoilerT boiler,PcCdOrgT org){
		boolean flg = true;
		try {
			this.getHibernateTemplate().delete(boiler);
			this.getHibernateTemplate().delete(org);
//			this.getHibernateTemplate().flush();
		} catch (Exception e) {
			e.printStackTrace();
			flg = false;
		}
		return flg;
	}
	
//	@SuppressWarnings("unchecked")
//	public List<PcCdOrgT> queryOrg(String hql) {
//		List<PcCdOrgT> PcCdOrgTList = null;
//		PcCdOrgTList = (List<PcCdOrgT>)this.getHibernateTemplate().find(hql);
//		return PcCdOrgTList;
//	}
//	
//	@SuppressWarnings("unchecked")
//	public List<PcCdBoilerT> queryBoiler(String hql) {
//		List<PcCdBoilerT> PcCdBoilerTList = null;
//		PcCdBoilerTList = (List<PcCdBoilerT>)this.getHibernateTemplate().find(hql);
//		return PcCdBoilerTList;
//	}
	
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
		try {
			this.getHibernateTemplate().merge(boiler);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public JSONObject cascadeInit() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PcCdBoilerT> serchBoiler(String hql) throws Exception {
		List<PcCdBoilerT> PcCdBoilerTList = null;
		PcCdBoilerTList = (List<PcCdBoilerT>)this.getHibernateTemplate().find(hql);
		return PcCdBoilerTList;
	}

	@Override
	public List<Object[]> searchData(String thinOilWellRD) throws Exception {
		// TODO Auto-generated method stub
		return Query.getSqlQuery(this.getHibernateTemplate(), thinOilWellRD);
	}
}