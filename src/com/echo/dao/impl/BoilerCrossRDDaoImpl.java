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

import com.echo.dao.BoilerCrosslRDDao;
import com.echo.dao.Query;
import com.echo.dto.PcRpdBoilerSuperheatT;



public class BoilerCrossRDDaoImpl extends HibernateDaoSupport implements BoilerCrosslRDDao,Serializable{

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

			 String[] cloumnsName = {"OILSTATIONNAME","AREABLOCK","BLOCKSTATIONNAME","YXZ","BOILER_NAME","GLBH","CJSJ","STATIONNO","STEAMOUT_TEMP","STEAMIN_TEMP","EJECTSMOKE_TEMP","BURNER_TEMP","CSIN_PRESS","CSOUT_PRESS",
						"CSIN_TEMP","CSOUT_TEMP","SL_LEVEL","SUPERHEAT_TEMP","SUPERHEAT_PIEZORESISTANCE","SUPERHEATIN_TEMP","SUPERHEATOUT_TEMP","SUPERHEATIN_PRESS","SUPERHEATOUT_PRESS","SUPERHEATIN_FLOW","HEARTH_PRESS","GAS1_PRESS"
						,"GAS2_PRESS","GAS3_PRESS","RS_TEMP","RS_DRYNESS","RS_PIEZORESISTANCE","RSIN_PRESS","RS_PRESS","RSIN_TEMP","RSOUT_TEMP","PUMPA_FLOW","PUMPB_FLOW","PUMPC_FLOW","PUMPA_PRESS","PUMPB_PRESS","PUMPC_PRESS"
						,"FANA_ELECTRICITY","FANB_ELECTRICITY","FANC_ELECTRICITY","PUMPFC_FREQUENCY","PUMPIN_PRESS","PUMPOUT_PRESS","PUMPIN_TEMP","PUMPOUT_TEMP","WATERSUPPLY_FLOW","WATERSUPPLY_TOTAL","GAS_FLOW","GAS_TOTAL","STEAMINJECTION_TOTAL"};
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


	@Override
	public PcRpdBoilerSuperheatT searchCrossRPD(String cid) {
		// TODO Auto-generated method stub
		PcRpdBoilerSuperheatT rpdCross = null;
		rpdCross = (PcRpdBoilerSuperheatT)this.getHibernateTemplate().get(PcRpdBoilerSuperheatT.class, cid);
		return rpdCross;
	}

}
