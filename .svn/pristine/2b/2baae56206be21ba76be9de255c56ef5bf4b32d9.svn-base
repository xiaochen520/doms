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
import com.echo.dao.WaterFloodingDao;
import com.echo.dao.WaterInjectionDao;
import com.echo.dto.PcCdWaterSourceWellT;
import com.echo.dto.PcCdWaterfloodingWellT;



public class WaterFloodingDaoImpl extends HibernateDaoSupport implements WaterFloodingDao,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3078060861794718243L;



	public void setSessionFactoryOverride(SessionFactory sessionFactory)
    {
      super.setSessionFactory(sessionFactory);
    } 


	@SuppressWarnings("unchecked")
	public List<Object[]> searchwaterFL(final String boilersInfo,final int start, final int pagesize) {
		List<Object[]> resultList = this.getHibernateTemplate()
		 .executeFind(new HibernateCallback() {
			 String[] cloumnsName = {"WATERFLOODING_WELLID","ORG_ID","QKID","A2ID","WATERFLOODING_NAME","CHANNEL_NUMBER","WATERFLOODING_CODE","STATUS_VALUE","COMMISSIONING_DATE","DYEAR",
						"RLAST_OPER","RLAST_ODATE","REMARK","OILSTATIONNAME","AREABLOCK","BLOCKSTATIONNAME","WATERINJECTIONTOPR","GH_ID","PROD_SNS","QKMC","JRBZ","LJJDID","LJJD_S"};
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


	public boolean save(PcCdWaterfloodingWellT well) throws Exception {
		Serializable flg = this.getHibernateTemplate().save(well);
		if(flg !=null && !"".equals(flg)){
			return true;
		}else{
		return false;
	}
	}


	public List<PcCdWaterfloodingWellT> searchWaterFlood(String hql)throws Exception {
		List<PcCdWaterfloodingWellT> wellList = null;
		
		wellList = (List<PcCdWaterfloodingWellT>)this.getHibernateTemplate().find(hql);
		return wellList;
	}


	public boolean updateFlooding(PcCdWaterfloodingWellT well) throws Exception {
		boolean flg = true ;
		this.getHibernateTemplate().merge(well);
		return flg;
	}


	@Override
	public List<Object[]> searchData(String thinOilWellRD) throws Exception {
		// TODO Auto-generated method stub
		return Query.getSqlQuery(this.getHibernateTemplate(), thinOilWellRD);
	}
}
