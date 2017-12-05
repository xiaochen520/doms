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

import com.echo.dao.CombinationDao;
import com.echo.dao.Query;
import com.echo.dto.PcCdCombinationStationT;


public class CombinationDaoImpl extends HibernateDaoSupport implements CombinationDao,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3078060861794718243L;



	public void setSessionFactoryOverride(SessionFactory sessionFactory)
    {
      super.setSessionFactory(sessionFactory);
    } 




	@SuppressWarnings("unchecked")
	public List<Object[]> searchCombination(final String boilersInfo,final int start, final int pagesize) {
		List<Object[]> resultList = this.getHibernateTemplate()
		 .executeFind(new HibernateCallback() {
			 String[] cloumnsName = {"COMBINATION_STATIONID","ORG_ID","A2ID","COMBINATION_STATION_NAME","COMBINATION_STATION_TYPE","STATUS_VALUE","RLAST_OPER","RLAST_ODATE","REMARK","PROD_SNS","COMBINATION_STATION_CODE"};
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




	public List<PcCdCombinationStationT> searchCombinationStationName(String hql)throws Exception {
		List<PcCdCombinationStationT> com = null;
		com = (List<PcCdCombinationStationT>) this.getHibernateTemplate().find(hql);
		return com;
	}




	public boolean save(PcCdCombinationStationT comb) throws Exception {
		Serializable flg = this.getHibernateTemplate().save(comb);
		if( flg != null && !"".equals(flg)){
			return  true;
		}else{
		return false;
		}
	}




	public List<PcCdCombinationStationT> searchComb(String hql)throws Exception {
		List<PcCdCombinationStationT> comb = null;
		comb = (List<PcCdCombinationStationT>)this.getHibernateTemplate().find(hql);
		return comb;
	}




	public boolean updateComb(PcCdCombinationStationT comb) throws Exception {
		boolean flag = true;
		this.getHibernateTemplate().merge(comb);
		return flag;
	}
}
