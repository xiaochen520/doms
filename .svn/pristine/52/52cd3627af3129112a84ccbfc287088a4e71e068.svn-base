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

import com.echo.dao.BranchingBasicDao;
import com.echo.dao.Query;
import com.echo.dao.BoilerBasicDao;
import com.echo.dto.PcCdBoilerT;
import com.echo.dto.PcCdBranchingT;
import com.echo.dto.PcCdOrgT;
import com.echo.dto.User;

public class BranchingBasicDaoImpl extends HibernateDaoSupport implements BranchingBasicDao,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3078060861794718243L;

	public void setSessionFactoryOverride(SessionFactory sessionFactory)
    {
      super.setSessionFactory(sessionFactory);
    } 

	@SuppressWarnings("unchecked")
	public List<Object[]> queryBranchingBasicInfo(final String boilersInfo,final int start, final int pagesize) {
		List<Object[]> resultList = this.getHibernateTemplate()
		 .executeFind(new HibernateCallback() {
			 String[] cloumnsName = {"BRANCHINGID","BRANCHING_NAME","BRANCHING_CODE"
						,"RLAST_OPER","RLAST_ODATE","REMARK","COMBINATION_STATION","COMSTATIONID","PROD_SNS"};
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
	
	public boolean removeBranchingBasicInfo(String id){
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

	public boolean removeBoilerBasicInfo(String arg) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<PcCdBranchingT> searchBranchs(String hql) throws Exception {
		List<PcCdBranchingT> branchingList = null;
		branchingList = (List<PcCdBranchingT>)this.getHibernateTemplate().find(hql);
		return branchingList;
	}

	public boolean saveBranch(PcCdBranchingT pcCdBranchingT) throws Exception {
		Serializable flg =  this.getHibernateTemplate().save(pcCdBranchingT);
		 if(flg != null && !"".equals(flg)){
			 return true;
		 }else{
			 return false;
		 }
	}

	public boolean updateBranch(PcCdBranchingT pcCdBranchingT) throws Exception {
		boolean flg = true ;
		this.getHibernateTemplate().merge(pcCdBranchingT);
		return flg;
	}

	public boolean removeBranch(String sql) throws Exception {
		SessionFactory sessionFactory = this.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		tx.begin();
		for(int i =0 ; i<sql.length();i++){
			org.hibernate.Query query = session.createSQLQuery(sql);
			query.executeUpdate();
		}
		tx.commit();
		session.close();
		return true;
	}
}