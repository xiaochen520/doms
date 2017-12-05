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

import com.echo.dao.Query;
import com.echo.dao.ThinOilWellDao;
import com.echo.dto.PcCdThinoilWellT;



public class ThinOilWellDaoImpl extends HibernateDaoSupport implements ThinOilWellDao,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3078060861794718243L;



	public void setSessionFactoryOverride(SessionFactory sessionFactory)
    {
      super.setSessionFactory(sessionFactory);
    } 


	@SuppressWarnings("unchecked")
	public List<Object[]> searchThinOil(final String boilersInfo,final int start, final int pagesize) {
		List<Object[]> resultList = this.getHibernateTemplate()
		 .executeFind(new HibernateCallback() {
			 String[] cloumnsName = {"WELLID","WELL_NAME","OILSTATIONNAME","QKMC","BLOCKSTATIONNAME","teamGroup","MANIFOLD",
						"PROD_SNS","STATUS_VALUE","COMMISSIONING_DATE","DYEAR","BRANCHINGID","WELL_COLE","BEWELL_NAME","INCREASE_FREQ_FLAG",
						"START_INCREASE_FREQ_TIME","END_INCREASE_FREQ_TIME","INCREASE_INTERVAL","JRBZ","LJJDID","LJJD_S","VALVE_CODING","CHANNEL_NO",
						"RLAST_OPER","RLAST_ODATE","OUTPUT_COEFFICIENT","REMARK","QKID","ORG_ID","ZZZ_ID","GH_ID","A2ID"};
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


	public boolean save(PcCdThinoilWellT thin) throws Exception {
		Serializable flg = this.getHibernateTemplate().save(thin);
		 if(flg != null && !"".equals(flg)){
			 return true;
		 }else{
			 return false;
		 }
//		try {
//			
//			this.getHibernateTemplate().merge(thin);
//			return true;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return false;
	}


	public List<PcCdThinoilWellT> searchById(String hql) throws Exception {
		List<PcCdThinoilWellT> thinList = null;
		thinList = (List<PcCdThinoilWellT>)this.getHibernateTemplate().find(hql);
		return thinList;
	}


	public List<PcCdThinoilWellT> searchByName(String hql) throws Exception {
		List<PcCdThinoilWellT> thinList = null;
		thinList = (List<PcCdThinoilWellT>)this.getHibernateTemplate().find(hql);
		return thinList;
	}

	public boolean updateThin(PcCdThinoilWellT thin) throws Exception {
		boolean flg = true;
		this.getHibernateTemplate().merge(thin);
		return flg;
	}


	@Override
	public List<Object[]> searchData(String thinOilWellRD) throws Exception {
		// TODO Auto-generated method stub
		return Query.getSqlQuery(this.getHibernateTemplate(), thinOilWellRD);
	}
}
