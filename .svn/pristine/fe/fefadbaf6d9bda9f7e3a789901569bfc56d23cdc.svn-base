package com.echo.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import net.sf.json.JSONObject;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.echo.dao.ObservationDao;
import com.echo.dao.Query;
import com.echo.dao.SagdDao;
import com.echo.dto.PcCdObservationWellT;


public class ObservationDaoImpl extends HibernateDaoSupport implements ObservationDao,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3078060861794718243L;



	public void setSessionFactoryOverride(SessionFactory sessionFactory)
    {
      super.setSessionFactory(sessionFactory);
    } 


	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> searchObservationWell(final String boilersInfo,final int start, final int pagesize) {
		List<Object[]> resultList = this.getHibernateTemplate()
		 .executeFind(new HibernateCallback() {
			 String[] cloumnsName = {"OILSTATIONNAME","AREABLOCK","BLOCKSTATIONNAME","GH_ID","OBSERVATION_WELLID","ORG_ID","QKID",
						"RLAST_OPER","RLAST_ODATE","REMARK","A2ID","WELL_NAME","WELL_COLE","BEWELL_NAME","COMMISSIONING_DATE","STATUS_VALUE","PROD_SNS","QKMC","LJJDID","LJJD_S","JRBZ"};
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
	/**
	 * 获取总条数
	 * @param hibernateTemplate
	 * @param sql
	 * @return
	 */
	public int getCounts(String sql) {
		int count = 0;
		List<Object[]> list = new ArrayList<Object[]>();
		list = Query.getSqlQuery(this.getHibernateTemplate(), sql);
		count = Integer.parseInt(String.valueOf(list.get(0))) ;
		return count;
	}



	public List<Object[]> searchStructureJc(String hql) {
		List<Object[]> resultList  = Query.getSqlQuery(this.getHibernateTemplate(), hql) ;
		return resultList;
	}



	public boolean save(PcCdObservationWellT obs) throws Exception {
		Serializable flg =  this.getHibernateTemplate().save(obs);
		 if(flg != null && !"".equals(flg)){
			 return true;
		 }else{
			 return false;
		 }
	}



	public String serachOrgByName(String name) throws Exception {
		// TODO Auto-generated method stub
		String hql="select org_id from PC_CD_ORG_T where structurename='"+name+"'";
		String str="";
		List<Object[]> resultList= Query.getSqlQuery(this.getHibernateTemplate(), hql);
		if(resultList.size()==1){
			Object obj=resultList.get(0);
			//System.out.println(obj);
			return obj.toString();
		}else{
			return null;
		}
		
	}

	public boolean updateObs(PcCdObservationWellT obs) throws Exception {
		boolean flag = true;
		this.getHibernateTemplate().merge(obs);
		return flag;
	}

	public List<PcCdObservationWellT> searchObserName(String hql)throws Exception {
		List<PcCdObservationWellT> obse = null;
		obse = (List<PcCdObservationWellT>)this.getHibernateTemplate().find(hql);
		return obse;
	}



	public List<Object[]> queryInfo(String hql) {
		List<Object[]> resultList  = Query.getSqlQuery(this.getHibernateTemplate(), hql) ;
		return resultList;
	}
	public List<Object[]> queryStation(String sql) throws Exception {
		return Query.getSqlQuery(this.getHibernateTemplate(), sql);
	}




	public boolean removeObswell(String[] sqls) throws Exception {
		SessionFactory sessionFactory = this.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		tx.begin();
		for( int i=0 ;i<sqls.length; i++){
			org.hibernate.Query query = session.createSQLQuery(sqls[i]);
			query.executeUpdate();
		}
		tx.commit();
		session.close();
		return true;
	}
	
}
