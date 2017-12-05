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

import com.echo.dao.GasWellDao;
import com.echo.dao.Query;
import com.echo.dao.RuleWellDao;
import com.echo.dao.ThinOilWellDao;
import com.echo.dao.WaterInjectionDao;
import com.echo.dto.PcCdWaterInjectiontopryT;
import com.echo.dto.User;



public class WaterInjectionDaoImpl extends HibernateDaoSupport implements WaterInjectionDao,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3078060861794718243L;



	public void setSessionFactoryOverride(SessionFactory sessionFactory)
    {
      super.setSessionFactory(sessionFactory);
    } 


	@SuppressWarnings("unchecked")
	public List<Object[]> searchwaterIT(final String boilersInfo,final int start, final int pagesize) {
		List<Object[]> resultList = this.getHibernateTemplate()
		 .executeFind(new HibernateCallback() {
			 String[] cloumnsName = {"WATER_INJECTIONTOPRID","ORG_ID","QKID","A2ID","WATER_INJECTIONTOPR_NAME","COMMISSIONING_DATE","DYEAR","STATUS_VALUE",
						"RLAST_OPER","RLAST_ODATE","REMARK","OILSTATIONNAME","AREABLOCK","CODE","BLOCKSTATIONNAME","GH_ID","PROD_SNS","JRBZ","LJJDID","LJJD_S"};
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
	public List<Object[]> queryInfo(String hql) {
		return Query.getSqlQuery(this.getHibernateTemplate(), hql);
	}


	public List<PcCdWaterInjectiontopryT> getInjectiontoprys(String hql)
			throws Exception {
		List<PcCdWaterInjectiontopryT> injectiontopryList = null;
		injectiontopryList = (List<PcCdWaterInjectiontopryT>)this.getHibernateTemplate().find(hql);
		return injectiontopryList;
	}


	public boolean saveINT(PcCdWaterInjectiontopryT pcCdWaterInjectiontopryT)
			throws Exception {
		Serializable flg =  this.getHibernateTemplate().save(pcCdWaterInjectiontopryT);
		 if(flg != null && !"".equals(flg)){
			 return true;
		 }else{
			 return false;
		 }
	}


	public boolean updateINT(PcCdWaterInjectiontopryT pcCdWaterInjectiontopryT)
			throws Exception {
		boolean flg = true ;
		this.getHibernateTemplate().merge(pcCdWaterInjectiontopryT);
		return flg;
	}


	@Override
	public List<Object[]> searchData(String thinOilWellRD) throws Exception {
		// TODO Auto-generated method stub
		return Query.getSqlQuery(this.getHibernateTemplate(), thinOilWellRD);
	}
}
