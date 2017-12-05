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
import com.echo.dao.WaterSoWellDao;
import com.echo.dto.PcCdBoilerT;
import com.echo.dto.PcCdOrgT;
import com.echo.dto.PcCdWaterSourceWellT;

public class WaterSoWellDaoImpl extends HibernateDaoSupport implements WaterSoWellDao{
	
	public void setSessionFactoryOverride(SessionFactory sessionFactory)
    {
      super.setSessionFactory(sessionFactory);
    } 

	
//获取总条数
	public int getCounts(String sql) {
		int count = 0;
		List<Object[]> list = new ArrayList<Object[]>();
		list = Query.getSqlQuery(this.getHibernateTemplate(), sql);
		count = Integer.parseInt(String.valueOf(list.get(0)));
		return count;
	}
//添加
	public boolean save(PcCdWaterSourceWellT wsw) throws Exception {
		Serializable flg = this.getHibernateTemplate().save(wsw);
		if(flg !=null && !"".equals(flg)){
			return true;
		}else{
		return false;
	}
	}
//修改
	@SuppressWarnings("unchecked")
	public List<PcCdWaterSourceWellT> searchWswname(String sql)throws Exception {
		List<PcCdWaterSourceWellT> wswList = null;
		wswList = (List<PcCdWaterSourceWellT> ) this.getHibernateTemplate().find(sql);
		return wswList;
	}

	public boolean updateWaterSoWell(PcCdWaterSourceWellT wsw) throws Exception {
		boolean flg = true ;
		this.getHibernateTemplate().merge(wsw);
		return flg;
	}
//查询区块
	public List<Object[]> queryWorkarea(String sql) {
		// TODO Auto-generated method stub
		return Query.getSqlQuery(this.getHibernateTemplate(), sql);
	}
//查询站号
	public List<Object[]> queryWellarea(String sql) {
		// TODO Auto-generated method stub
		return Query.getSqlQuery(this.getHibernateTemplate(), sql);
	}
//查询水源井

	public List<Object[]> queryWellmonth(String sql) {
		// TODO Auto-generated method stub
		return Query.getSqlQuery(this.getHibernateTemplate(), sql);
	}
//删除
	@SuppressWarnings("unchecked")
	public PcCdWaterSourceWellT queryWaterSourceWell(String waterSourceWellid) {
		PcCdWaterSourceWellT pcCdWaterSourceWellT = null;
		pcCdWaterSourceWellT = (PcCdWaterSourceWellT)this.getHibernateTemplate().get(PcCdBoilerT.class, waterSourceWellid);
		return pcCdWaterSourceWellT;
	}

	public List<PcCdOrgT> queryOrg(String hql) {
		List<PcCdOrgT> PcCdOrgTList = null;
		PcCdOrgTList = (List<PcCdOrgT>)this.getHibernateTemplate().find(hql);
		return PcCdOrgTList;
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> searchWaterSoWell(final String boilersInfo,final int start, final int pagesize) {
		List<Object[]> resultList = this.getHibernateTemplate()
		 .executeFind(new HibernateCallback() {
			 String[] cloumnsName = {"WATER_SOURCE_WELLID","WELL_NAME","COMBINATION","STATIONNUM","WELL_CODE","BEWELL_NAME","STATUS_VALUE","PROD_SNS","COMMISSIONING_DATE","DYEAR",
						"JRBZ","LJJDID","LJJD_S","RLAST_OPER","RLAST_ODATE","REMARK","GH_ID","ORG_ID","A2ID",};
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


	public List<PcCdWaterSourceWellT> searchwellLists(String hql)
			throws Exception {
		List<PcCdWaterSourceWellT> wellList = null;
		
		wellList = (List<PcCdWaterSourceWellT>)this.getHibernateTemplate().find(hql);
		
		return wellList;
	}
	

}
