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
import com.echo.dao.RuleWellDao;
import com.echo.dto.PcCdOildrillingStationT;
import com.echo.dto.PcCdRuleWellT;
import com.echo.dto.PcRpdRuleWellProdT;
import com.echo.dto.PcRpdRuleWellProdbT;



public class RuleWellDaoImpl extends HibernateDaoSupport implements RuleWellDao,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3078060861794718243L;



	public void setSessionFactoryOverride(SessionFactory sessionFactory)
    {
      super.setSessionFactory(sessionFactory);
    } 


	@SuppressWarnings("unchecked")
	public List<Object[]> searchRullWell(final String boilersInfo,final int start, final int pagesize) {
		List<Object[]> resultList = this.getHibernateTemplate()
		 .executeFind(new HibernateCallback() {
			 String[] cloumnsName = {"RULE_WELLID","ORG_ID","QKID","A2ID","WELL_NAME","WELL_COLE","BEWELL_NAME","COMMISSIONING_DATE","STATUS_VALUE","VALVE_CODING","CHANNEL_NO",
						"RLAST_OPER","RLAST_ODATE","OUTPUT_COEFFICIENT","REMARK","OILSTATIONNAME","AREABLOCK","BLOCKSTATIONNAME","ZZZ_ID","GH_ID","MANIFOLD","PROD_SNS","BRANCHINGID","JRBZ","SWITCH_IN_FLAG","LJJDID","LJJD_S","QKMC",
						"DYEAR","INCREASE_FREQ_FLAG","INCREASE_INTERVAL","START_INCREASE_FREQ_TIME","END_INCREASE_FREQ_TIME","teamGroup"};
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


	@SuppressWarnings("unchecked")
	public List<PcCdRuleWellT> searchRullWellName(String hql) throws Exception {
		List<PcCdRuleWellT> rules= null;
		rules = (List<PcCdRuleWellT>)this.getHibernateTemplate().find(hql);
		return rules;
	}


	public boolean save(PcCdRuleWellT rule) throws Exception {
		Serializable flg = this.getHibernateTemplate().save(rule);
		if(flg !=null && !"".equals(flg)){
			return  true;
		}else{
		return false;
		}
	}


	public List<PcCdRuleWellT> searchRuleName(String hql) throws Exception {
		List<PcCdRuleWellT> rul = null;
		rul = (List<PcCdRuleWellT>)this.getHibernateTemplate().find(hql);
		return rul;
	}


	public boolean updateRule(PcCdRuleWellT rule) throws Exception {
		boolean flag = true ; 
		this.getHibernateTemplate().merge(rule);
		return flag;
	}


	public List<Object[]> querywellAreaSelf(String sql) throws Exception {
		return Query.getSqlQuery(this.getHibernateTemplate(), sql);
		
	}
	public List<Object[]> serarchWellAreaSelf(String sql) throws Exception {
		return Query.getSqlQuery(this.getHibernateTemplate(), sql);
	}


	@Override
	public List<Object[]> searchData(String thinOilWellRD) throws Exception {
		// TODO Auto-generated method stub
		return Query.getSqlQuery(this.getHibernateTemplate(), thinOilWellRD);
	}


	@Override
	public List<Object[]> searchRuleWellRPD(final String wellInfo, final int start,final int pagesize, final String[] cloumnsName) throws Exception {
		// TODO Auto-generated method stub
		List<Object[]> resultList = this.getHibernateTemplate()
		 .executeFind(new HibernateCallback() {
			//String[] cloumnsName = {"JHMC","WELL_CODE","OILSTATIONNAME","QKMC","BLOCKSTATIONNAME","MANIFOLD","BRANCHING_NAME","REPORT_DATE","P_PRESSURE_PRESS","P_PRESSURE_TEMP","P_LOOPED_PRESS","P_LOOPED_TEMP","P_DRIVEPIPE_PRESS","I_PRESSURE_PRESS","I_PRESSURE_TEMP","I_LOOPED_PRESS","I_LOOPED_TEMP","I_DRIVEPIPE_PRESS","NO1STEAM_TEMP","NO1STEAM_PRESS","NO1STEAM_DIFP","NO1INSTANT_FLOW","LOWSUM_FLOW1","NO1INSTANT_DRYNESS","NO1CONTROL_STATUS","NO1CONTROL_APERTURE","NO1FLOW_SET","NO2STEAM_TEMP","NO2STEAM_PRESS","NO2STEAM_DIFP","NO2INSTANT_FLOW","LOWSUM_FLOW2","NO2INSTANT_DRYNESS","NO2CONTROL_STATUS","NO2CONTROL_APERTURE","NO2FLOW_SET","P_PRESS1","P_PRESS2","P_TEMP1","P_TEMP2","P_TEMP3","P_TEMP4","P_TEMP5","P_TEMP6","P_TEMP7","P_TEMP8","P_TEMP9","P_TEMP10","P_TEMP11","P_TEMP12","NOW_LOAD","MOTOR_PRESS_A","MOTOR_TEMP_A","MOTOR_PRESS_B","MOTOR_TEMP_B","MOTOR_PRESS_C","MOTOR_TEMP_C","CLIQUE_CONTROL_APERTURE2","ACTIVE_ENERGY","IDLE_ENERGY","POWER_FACTOR","JIG_FREQUENCY","STROKE","PUMPING_RUNNING_STATE","MOTORSTATUS","CONVERSION_PRESS","NOW_FREQUENCY","CONVERSION_ELECTRICITY","MOTOR_MODE","CLIQUE_CONTROL_APERTURE1","RUNTIME","DAILY_OUTPUT","DAILY_POWER_CONSUMPTIVE","DAILY_CUMULATIVE_STEAM1","DAILY_CUMULATIVE_STEAM2","REMARK","ORG_ID","A2ID","SAGDID"};
		public List<Object[]> doInHibernate(Session session)
		 throws HibernateException, SQLException {
				SQLQuery s = session.createSQLQuery(wellInfo);
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


	@Override
	public List<PcRpdRuleWellProdT> searchOnlyNameRq(String hql)throws Exception {
		List<PcRpdRuleWellProdT> rpdList = null;
		rpdList = this.getHibernateTemplate().find(hql);
		return rpdList;
	}


	@Override
	public boolean saveRpdRull(PcRpdRuleWellProdT rpdR) throws Exception {
		boolean flag = true ;
		this.getHibernateTemplate().merge(rpdR);
		return flag;
	}


	@Override
	public boolean saveRpdRullB(PcRpdRuleWellProdbT rpdbR) throws Exception {
		boolean flag = true;
		this.getHibernateTemplate().merge(rpdbR);
		return flag;
	}


	@Override
	public List<PcRpdRuleWellProdbT> searchOnlyNameRqB(String hql)throws Exception {
		List<PcRpdRuleWellProdbT> rpdList = null;
		rpdList =(List<PcRpdRuleWellProdbT>)this.getHibernateTemplate().find(hql);
		return rpdList;
	}


	@Override
	public boolean removeRullRPDId(String sql) throws Exception {
		SessionFactory sessionFactory=this.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();  
		tx.begin();
		//for(int i = 0;i < sqls.length; i++){
			org.hibernate.Query query  =  session.createSQLQuery(sql);
			query.executeUpdate();
		//}
		
		tx.commit();
		session.close();

		return true;
	}
}
