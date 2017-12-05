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
import com.echo.dao.SRGLRDDao;
import com.echo.dto.PcCdBoilerT;
import com.echo.dto.PcRpdBoilerCommonT;
import com.echo.dto.PcRpdBoilerCommondT;
import com.echo.dto.PcRpdBoilerHighDryT;



public class SRGLRDDaoImpl extends HibernateDaoSupport implements SRGLRDDao,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3078060861794718243L;



	public void setSessionFactoryOverride(SessionFactory sessionFactory)
    {
      super.setSessionFactory(sessionFactory);
    } 


	@SuppressWarnings("unchecked")
	public List<Object[]> searchData(final String boilersInfo,final int start, final int pagesize) {
		List<Object[]> resultList = this.getHibernateTemplate()
		 .executeFind(new HibernateCallback() {
			 String[] cloumnsName = {"BLOCK_NAME","BLOCKSTATION_NAME","BOILER_NAME","CJSJS","RUN_STATUS","GAS_INTO_PRESS","GAS1_PRESS","FIRE_MEASURE","GAS_FLOW",
						"PUMPIN_PRESS","PUMPOUT_TEMP","PUMPFC_FREQUENCY","WATERSUPPLY_FLOW","STEAMIN_TEMP","STEAMOUT_TEMP","STEAM_DRYNESS",
						"PUMPOUT_PRESS","CSIN_PRESS","CSIN_TEMP","CSOUT_PRESS","CSOUT_TEMP","CONVECTIONR_PRESS_REDUCTION","RSIN_PRESS","RSIN_TEMP",
						"RS_PRESS_REDUCTION","RS_TEMP","GAS2_PRESS","GAS3_PRESS","LUBE_PRESS","LUBE_TEMP","FAN_AIR_INTAKE_TEMP","BURNER_TEMP","HEARTH_PRESS","EJECTSMOKE_TEMP",
						"SYSTEM_VOLTAGE","PUMP_MOTOR_CURRENT","PUMP_MOTOR_TEMP","FAN_MOTOR_CURRENT","FUEL_GAS_DENSITY","H2S_DENSITY","SEWAGE_BUFFER_TANK","STEAM_WORK_GROUP","OILDRILLING_STATION_NAME","STEAM_INJE_UNIT"};
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
	public List<Object[]> searchRBData(final String boilersInfo,final int start, final int pagesize,final  String[] cloumnsName) {
		@SuppressWarnings("unchecked")
		List<Object[]> resultList = this.getHibernateTemplate()
		 .executeFind(new HibernateCallback() {
			 String[] cloumnsName = {"BLOCK_NAME","BOILER_NAME","CJSJS","RUNTIME","GAS_INTO_PRESS","GAS1_PRESS","FIRE_MEASURE","GAS_FLOW",
						"PUMPIN_PRESS","PUMPOUT_TEMP","PUMPFC_FREQUENCY","WATERSUPPLY_FLOW","STEAMIN_TEMP","STEAMOUT_TEMP","STEAM_DRYNESS",
						"PUMPOUT_PRESS","CSIN_PRESS","CSIN_TEMP","CSOUT_PRESS","CSOUT_TEMP","CONVECTIONR_PRESS_REDUCTION","RSIN_PRESS","RSIN_TEMP",
						"RS_PRESS_REDUCTION","RS_TEMP","GAS2_PRESS","GAS3_PRESS","LUBE_PRESS","LUBE_TEMP","FAN_AIR_INTAKE_TEMP","BURNER_TEMP","HEARTH_PRESS","EJECTSMOKE_TEMP",
						"SYSTEM_VOLTAGE","PUMP_MOTOR_CURRENT","PUMP_MOTOR_TEMP","FAN_MOTOR_CURRENT",
						"DAILY_CUMULATIVE_GAS","DAILY_CUMULATIVE_STEAM","FUEL_GAS_DENSITY","H2S_DENSITY","SEWAGE_BUFFER_TANK","STEAM_WORK_GROUP","OILDRILLING_STATION_NAME","STEAM_INJE_UNIT","RPD_BOILER_COMMON_ID","BOILERID",
						"REMARK","CHECK_OPER","CHECK_DATE","RLAST_OPER","RLAST_ODATE"};
	
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
	public List<Object[]> searchData(String searchThinOil) {
		List<Object[]> lo = Query.getSqlQuery(this.getHibernateTemplate(), searchThinOil);
		return lo;
	}
	public List<Object[]> searchRBData(String searchThinOil) {
		List<Object[]> lo = Query.getSqlQuery(this.getHibernateTemplate(), searchThinOil);
		return lo;
	}


	public List<Object[]> queryOrdinaryHotBoiler(String sql) throws Exception {
		return Query.getSqlQuery(this.getHibernateTemplate(), sql);
	}
	public List<Object[]> queryOildingName(String sql) throws Exception {
		return Query.getSqlQuery(this.getHibernateTemplate(), sql);
	}
	public boolean removeBoilerCommondRP(String sql) throws Exception {
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
	public List<Object[]> searchCommobid(String sql)throws Exception {
		//List<PcRpdBoilerCommondT>  pbc = null;
		//pbc = (List<PcRpdBoilerCommondT>)this.getHibernateTemplate().find(sql);
		return Query.getSqlQuery(this.getHibernateTemplate(), sql);
	}
	public boolean save(PcRpdBoilerCommonT psgl) throws Exception {
		Serializable flg = this.getHibernateTemplate().save(psgl);
		if(flg !=null && !"".equals(flg)){
			return true;
		}else{
		return false;
		}
	}

	public boolean update(PcRpdBoilerCommonT psgl) throws Exception {
		try {
			this.getHibernateTemplate().merge(psgl);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean PcRpdBoilerCommonT(PcRpdBoilerCommondT psgl) throws Exception {
		boolean flg = true ;
		this.getHibernateTemplate().merge(psgl);
		return flg;
	}


	@SuppressWarnings("unchecked")
	public List<PcRpdBoilerCommonT> searchBoilerCommonds(String sql)
			throws Exception {
		List<PcRpdBoilerCommonT> boilerCommondList = null;
		
		boilerCommondList = (List<PcRpdBoilerCommonT>)this.getHibernateTemplate().find(sql);
		
		return boilerCommondList;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> searchData(final String sql, final String[] cloumnsName,
			final int start, final int pagesize) {

		List<Object[]> resultList = this.getHibernateTemplate()
		 .executeFind(new HibernateCallback() {
				
		public List<Object[]> doInHibernate(Session session)
		 throws HibernateException, SQLException {
				SQLQuery s = session.createSQLQuery(sql);
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
	public List<Object[]> queryInfo(String sql) throws Exception {
		return Query.getSqlQuery(this.getHibernateTemplate(), sql);
	}
	public boolean addOrModifyBoilerOrgInfo(PcRpdBoilerHighDryT prbhd) throws Exception{
		try {
			this.getHibernateTemplate().merge(prbhd);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean removeGGDRDBoiler(PcRpdBoilerHighDryT prbhd) throws Exception{
		boolean flg = true;
		try {
			this.getHibernateTemplate().delete(prbhd);
		} catch (Exception e) {
			e.printStackTrace();
			flg = false;
		}
		return flg;
	}
	
	public PcRpdBoilerHighDryT queryGGDRDBoiler(String rpdBoilerHighDryId) throws Exception{
		PcRpdBoilerHighDryT prbhd = null;
		prbhd = (PcRpdBoilerHighDryT)this.getHibernateTemplate().get(PcRpdBoilerHighDryT.class, rpdBoilerHighDryId);
		return prbhd;
	}
	public PcCdBoilerT searchBoiler(String boilerId) throws Exception{
		PcCdBoilerT boiler = null;
		boiler = (PcCdBoilerT)this.getHibernateTemplate().get(PcCdBoilerT.class, boilerId);
		return boiler;
	}
	@Override
	public List<Object[]> queryBoilerid(String sql) throws Exception {
		// TODO Auto-generated method stub
		return Query.getSqlQuery(this.getHibernateTemplate(), sql);
	}


	@Override
	public List<Object[]> searchRBData1(final String boilersInfo,final int start, final int pagesize,final  String[] cloumnsName) {
		@SuppressWarnings("unchecked")
		List<Object[]> resultList = this.getHibernateTemplate()
		 .executeFind(new HibernateCallback() {
			 String[] cloumnsName = {"BLOCK_NAME","BLOCKSTATION_NAME","BOILER_NAME","REPORT_DATE","RUN_STATUS","GAS_INTO_PRESS","GAS1_PRESS","FIRE_MEASURE","GAS_FLOW",
						"PUMPIN_PRESS","PUMPOUT_TEMP","PUMPFC_FREQUENCY","WATERSUPPLY_FLOW","STEAMIN_TEMP","STEAMOUT_TEMP","STEAM_DRYNESS",
						"PUMPOUT_PRESS","CSIN_PRESS","CSIN_TEMP","CSOUT_PRESS","CSOUT_TEMP","CONVECTIONR_PRESS_REDUCTION","RSIN_PRESS","RSIN_TEMP",
						"RS_PRESS_REDUCTION","RS_TEMP","GAS2_PRESS","GAS3_PRESS","LUBE_PRESS","LUBE_TEMP","FAN_AIR_INTAKE_TEMP","BURNER_TEMP","HEARTH_PRESS","EJECTSMOKE_TEMP",
						"SYSTEM_VOLTAGE","PUMP_MOTOR_CURRENT","PUMP_MOTOR_TEMP","FAN_MOTOR_CURRENT","FUEL_GAS_DENSITY","H2S_DENSITY","SEWAGE_BUFFER_TANK","STEAM_WORK_GROUP","OILDRILLING_STATION_NAME","STEAM_INJE_UNIT"};
	
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


	@Override
	public List<Object[]> searchData1(String thinOilWellRD) {
		List<Object[]> lo = Query.getSqlQuery(this.getHibernateTemplate(), thinOilWellRD);
		return lo;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> searchData1(final String thinOilWellRD,
			final String[] cloumnsNames,final int start,final int rowsPerpage) {

		List<Object[]> resultList = this.getHibernateTemplate()
		 .executeFind(new HibernateCallback() {
				
		public List<Object[]> doInHibernate(Session session)
		 throws HibernateException, SQLException {
				SQLQuery s = session.createSQLQuery(thinOilWellRD);
				for (String cloumn : cloumnsNames) {
					s.addScalar(cloumn);
					
				}
				s.setFirstResult(start);
				s.setMaxResults(rowsPerpage);
				return s.list();
			}
		 });
		
		return resultList;
	
	}


}
