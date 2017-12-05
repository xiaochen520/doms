package com.echo.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.echo.dao.BoilerSuperheatRDDao;
import com.echo.dao.Query;
import com.echo.dto.PcRpdBoilerSuperheatT;



public class BoilerSuperheatRDDaoImpl extends HibernateDaoSupport implements BoilerSuperheatRDDao,Serializable{

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

			 String[] cloumnsName = {"BLOCK_NAME","BOILER_NAME","CJSJS","RUN_STATUS","GAS_INTO_PRESS","GAS1_PRESS","FIRE_MEASURE","GAS_FLOW","PUMPIN_PRESS","PUMPOUT_TEMP","PUMPFC_FREQUENCY","WATERSUPPLY_FLOW","STEAMIN_TEMP","STEAMOUT_TEMP","SUPERHEAT_DEGREE","SUPERHEATOUT_TEMP","SL_LEVEL","RS_PRESS_REDUCTION","SUPERHEAT_PRESS_REDUCTION","MIXER_PRESS_REDUCTION","PUMPOUT_PRESS","CSIN_PRESS","CSIN_TEMP","CSOUT_PRESS","CSOUT_TEMP","CONVECTIONR_PRESS_REDUCTION","RSIN_PRESS","RSIN_TEMP","RS_PRESS","RSOUT_TEMP","RS_TEMP","RS_DRYNESS","SEPARATOR_PRESS_EXPORT","SUPERHEATIN_PRESS","SUPERHEATIN_TEMP","SUPERHEATOUT_PRESS","SUPERHEAT_TEMP","SUPERHEATIN_FLOW","MIXER_PRESS_WATER","GAS2_PRESS","GAS3_PRESS","LUBE_TEMP","FAN_AIR_EXPORT_PRESS","FAN_AIR_INTAKE_TEMP","BURNER_TEMP","HEARTH_PRESS","EJECTSMOKE_TEMP","SYSTEM_VOLTAGE","PUMP_MOTOR_CURRENT","PUMP_MOTOR_TEMP","FAN_MOTOR_CURRENT","FUEL_GAS_DENSITY","H2S_DENSITY","SEWAGE_BUFFER_TANK","STEAM_WORK_GROUP","STEAM_INJE_UNIT","OILDRILLING_STATION_NAME","LEVEL_TJFKD"};
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

	@SuppressWarnings("unchecked")
	public List<PcRpdBoilerSuperheatT> searchCross(String hql) throws Exception {
		List<PcRpdBoilerSuperheatT> boilerCrossdList = null;
		boilerCrossdList = (List<PcRpdBoilerSuperheatT>)this.getHibernateTemplate().find(hql);
		return boilerCrossdList;
	}


	public boolean save(PcRpdBoilerSuperheatT psc) throws Exception {
		Serializable flg = this.getHibernateTemplate().save(psc);
		if(flg !=null && !"".equals(flg)){
			return true;
		}else{
		return false;
		}
		
	}
	public boolean update(PcRpdBoilerSuperheatT psc) throws Exception {
		boolean flg = true ;
		this.getHibernateTemplate().merge(psc);
		return flg;
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> searchRPDData(final String boilersInfo,final int start, final int pagesize,final String[] cloumnsName) {
		List<Object[]> resultList = this.getHibernateTemplate()
		 .executeFind(new HibernateCallback() {
		public List<Object[]> doInHibernate(Session session)
		 throws HibernateException, SQLException {
				SQLQuery s = session.createSQLQuery(boilersInfo);
//				for (String cloumn : cloumnsName) {
//					s.addScalar(cloumn);
//				}
				s.setFirstResult(start);
				s.setMaxResults(pagesize);
				return s.list();
			}
		 });
		
		return resultList;
	}


	
	public PcRpdBoilerSuperheatT searchBoilerCrpssdRPD(String boilerCrossddid) throws Exception {
		PcRpdBoilerSuperheatT prbc = null;
		prbc = (PcRpdBoilerSuperheatT)this.getHibernateTemplate().get(PcRpdBoilerSuperheatT.class, boilerCrossddid);
		return prbc;
	}


	
	public boolean removeBoilerCrpssdRPD(PcRpdBoilerSuperheatT prbc) throws Exception {
		try {
			this.getHibernateTemplate().delete(prbc);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> searchData1(final String thinOilWellRD, final int start,
			final int rowsPerpage) {
		List<Object[]> resultList = this.getHibernateTemplate()
				 .executeFind(new HibernateCallback() {

					 String[] cloumnsName = {"BLOCK_NAME","BOILER_NAME","REPORT_DATE","RUN_STATUS","GAS_INTO_PRESS","GAS1_PRESS","FIRE_MEASURE","GAS_FLOW","PUMPIN_PRESS","PUMPOUT_TEMP","PUMPFC_FREQUENCY","WATERSUPPLY_FLOW","STEAMIN_TEMP","STEAMOUT_TEMP","SUPERHEAT_DEGREE","SUPERHEATOUT_TEMP","SL_LEVEL","RS_PRESS_REDUCTION","SUPERHEAT_PRESS_REDUCTION","MIXER_PRESS_REDUCTION","PUMPOUT_PRESS","CSIN_PRESS","CSIN_TEMP","CSOUT_PRESS","CSOUT_TEMP","CONVECTIONR_PRESS_REDUCTION","RSIN_PRESS","RSIN_TEMP","RS_PRESS","RSOUT_TEMP","RS_TEMP","RS_DRYNESS","SEPARATOR_PRESS_EXPORT","SUPERHEATIN_PRESS","SUPERHEATIN_TEMP","SUPERHEATOUT_PRESS","SUPERHEAT_TEMP","SUPERHEATIN_FLOW","MIXER_PRESS_WATER","GAS2_PRESS","GAS3_PRESS","LUBE_TEMP","FAN_AIR_EXPORT_PRESS","FAN_AIR_INTAKE_TEMP","BURNER_TEMP","HEARTH_PRESS","EJECTSMOKE_TEMP","SYSTEM_VOLTAGE","PUMP_MOTOR_CURRENT","PUMP_MOTOR_TEMP","FAN_MOTOR_CURRENT","FUEL_GAS_DENSITY","H2S_DENSITY","SEWAGE_BUFFER_TANK","STEAM_WORK_GROUP","STEAM_INJE_UNIT","OILDRILLING_STATION_NAME"};
				public List<Object[]> doInHibernate(Session session)
				 throws HibernateException, SQLException {
						SQLQuery s = session.createSQLQuery(thinOilWellRD);
						for (String cloumn : cloumnsName) {
							s.addScalar(cloumn);
						}
						s.setFirstResult(start);
						s.setMaxResults(rowsPerpage);
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


	@Override
	public List<Object[]> queryInfo(String hql) {
		return Query.getSqlQuery(this.getHibernateTemplate(), hql);
	}
	
}
