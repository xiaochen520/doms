package com.echo.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import net.sf.json.JSONArray;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.echo.dao.ManifoldBasicDao;
import com.echo.dao.Query;
import com.echo.dao.BoilerBasicDao;
import com.echo.dto.PcCdBoilerT;
import com.echo.dto.PcCdManifoldT;
import com.echo.dto.PcCdOrgT;
import com.echo.dto.User;

public class ManifoldBasicDaoImpl extends HibernateDaoSupport implements ManifoldBasicDao,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3078060861794718243L;

	public void setSessionFactoryOverride(SessionFactory sessionFactory)
    {
      super.setSessionFactory(sessionFactory);
    } 

	@SuppressWarnings("unchecked")
	public List<Object[]> queryManifoldBasicInfo(final String boilersInfo,final int start, final int pagesize) {
		List<Object[]> resultList = this.getHibernateTemplate()
		 .executeFind(new HibernateCallback() {
			 String[] cloumnsName = {"MANIFOLDID","ORG_ID","QKID","A2ID","GHMC","GHDM","DTFS","DTFMC1","DTFTDS1","DTFMC2","DTFTDS2","INSTRU_STVA","JRBZ","STATUS_VALUE","COMMISSIONING_DATE","DYEAR"
						,"CODE","RLAST_OPER","RLAST_ODATE","REMARK","OILSTATIONNAME","AREABLOCK","BLOCKSTATIONNAME","teamGroup","PROD_SNS","GRZHID","BRANCHING","BRANCHINGID","LJJDID","LJJD_S","JLATIONNAME","PID2"};
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
	public boolean removeManifoldBasicInfo(String[] sqls) throws Exception {
		SessionFactory sessionFactory = this.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		tx.begin();
		for( int i =0;i<sqls.length;i++){
			org.hibernate.Query query = session.createSQLQuery(sqls[i]);
			query.executeUpdate();
		}
		tx.commit();
		session.close();
		return true;
	}
//	public boolean removeBoilerOrgInfo(PcCdBoilerT boiler){
//		boolean flg = true;
//		try {
//			this.getHibernateTemplate().delete(boiler);
//			this.getHibernateTemplate().flush();
//		} catch (Exception e) {
//			e.printStackTrace();
//			flg = false;
//		}
//		return flg;
//	}
	
	@SuppressWarnings("unchecked")
	public List<PcCdOrgT> queryOrg(String hql) {
		List<PcCdOrgT> PcCdOrgTList = null;
		PcCdOrgTList = (List<PcCdOrgT>)this.getHibernateTemplate().find(hql);
		return PcCdOrgTList;
	}
	
	@SuppressWarnings("unchecked")
	public List<PcCdBoilerT> queryBoiler(String hql) {
		List<PcCdBoilerT> PcCdBoilerTList = null;
		PcCdBoilerTList = (List<PcCdBoilerT>)this.getHibernateTemplate().find(hql);
		return PcCdBoilerTList;
	}

	public List<PcCdManifoldT> searchManifold(String hql) throws Exception {
		List<PcCdManifoldT> ManifoldList = null;
		ManifoldList = (List<PcCdManifoldT>)this.getHibernateTemplate().find(hql);
		return ManifoldList;
	}

	public boolean addManifold(PcCdManifoldT pcCdManifoldT) throws Exception {
		Serializable flg =  this.getHibernateTemplate().save(pcCdManifoldT);
		 if(flg != null && !"".equals(flg)){
			 return true;
		 }else{
			 return false;
		 }
	}

	public boolean updateManifold(PcCdManifoldT pcCdManifoldT) throws Exception {
		boolean flg = true ;
		this.getHibernateTemplate().merge(pcCdManifoldT);
		return flg;
	}


	@Override
	public List<Object[]> searchData(String thinOilWellRD) throws Exception {
		// TODO Auto-generated method stub
		 List<Object[]> lo = Query.getSqlQuery(this.getHibernateTemplate(), thinOilWellRD);
		 return lo;
	}

	@Override
	public List<Object[]> searchQKID(String sql) throws Exception {
		// TODO Auto-generated method stub
		List<Object[]> qdjArr = new ArrayList<Object[]>();
		qdjArr= Query.getSqlQuery(this.getHibernateTemplate(), sql);
		return qdjArr;
	}

	
}