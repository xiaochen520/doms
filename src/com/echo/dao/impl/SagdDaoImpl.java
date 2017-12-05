package com.echo.dao.impl;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import oracle.jdbc.OracleTypes;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.echo.dao.Query;
import com.echo.dao.SagdDao;
import com.echo.dto.PcCdServerNodeT;
import com.echo.dto.PcCdWellSagdT;
import com.echo.dto.PcRpdWellSagddT;


public class SagdDaoImpl extends HibernateDaoSupport implements SagdDao,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3078060861794718243L;



	public void setSessionFactoryOverride(SessionFactory sessionFactory)
    {
      super.setSessionFactory(sessionFactory);
    } 


	@SuppressWarnings("unchecked")
	public List<Object[]> searchSagdWell(final String boilersInfo,final int start, final int pagesize) {
		List<Object[]> resultList = this.getHibernateTemplate()
		 .executeFind(new HibernateCallback() {
			 String[] cloumnsName = {"SAGDID","ORG_ID","A2ID","JHMC","JHMC_S","DTFMC_S","TDH","AUF","INCREASE_FREQ_FLAG","START_INCREASE_FREQ_TIME","END_INCREASE_FREQ_TIME","INCREASE_INTERVAL","WHRTU_CNF","WSRTU_CNF",
						"P_UGRTU_CNF","P_UGTEM_NUM","P_PURTU_CNF","WWORK_FLAG","WWORK_DATE","WABNORMAL_FLAG","WABNORMAL_DATE",
						"WELLS_NUM","WELLS_NAM","PROD_SNS","STATUS_VALUE","COMMISSIONING_DATE","SCAN_TIME","PHASE","DYEAR","P_DLT_CNF","WELL_RTU_ADR",
						"LJJD_S","JRBZ","RLAST_OPER","RLAST_ODATE","OUTPUT_COEFFICIENT","PROD_STAGES","P_PROD_MODE",
						"I_PROD_MODE","REMARK","OILSTATIONNAME","AREABLOCK","BLOCKSTATIONNAME","ZZZ_ID","GH_ID","MANIFOLD",
						"BRANCHINGID","QKID","QKMC","LJJDID","PMODENAME","IMODENAME","BBSX","BZHF"};
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
	
	@SuppressWarnings("unchecked")
	public List<Object[]> searchSagdRDWell(final String boilersInfo,final int start, final int pagesize,final List<String> cloumnsName) throws Exception{
		List<Object[]> resultList = this.getHibernateTemplate()
		 .executeFind(new HibernateCallback() {
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
	@SuppressWarnings("unchecked")
	public List<Object[]> searchSagdRPD(final String wellInfo,final int start, final int pagesize,final String[] cloumnsName) {
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
	public List<PcCdWellSagdT> searchSagdname(String hql) throws Exception {
		List<PcCdWellSagdT> sagdList = null;
		sagdList = (List<PcCdWellSagdT>)this.getHibernateTemplate().find(hql);
		return sagdList;
	}

	@SuppressWarnings("unchecked")
	public List<PcRpdWellSagddT> searchSagrdByName(String hql) throws Exception {
		return (List<PcRpdWellSagddT>)this.getHibernateTemplate().find(hql);
	}

	public boolean save(PcCdWellSagdT sagd) throws Exception {
		Serializable flg =  this.getHibernateTemplate().save(sagd);
		 if(flg != null && !"".equals(flg)){
			 return true;
		 }else{
			 return false;
		 }
	}


	public boolean updateSagd(PcCdWellSagdT sagd) throws Exception {
		boolean flg = true ;
		this.getHibernateTemplate().merge(sagd);
		return flg;
	}


	@SuppressWarnings("unchecked")
	public List<PcCdServerNodeT> searchServer(String hql) throws Exception {
		List<PcCdServerNodeT> serverList = null;
		serverList = (List<PcCdServerNodeT>)this.getHibernateTemplate().find(hql);
		return serverList;
	}

	public boolean addOrUpdateSagddRPD(PcRpdWellSagddT prws) throws Exception{
		try {
			this.getHibernateTemplate().merge(prws);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public PcRpdWellSagddT searchSagddRPD(String sagdprdid) throws Exception{
		PcRpdWellSagddT rpdSagd = null;
		rpdSagd = (PcRpdWellSagddT)this.getHibernateTemplate().get(PcRpdWellSagddT.class, sagdprdid);
		return rpdSagd;
	}
	
	public boolean removeSagddRPD(PcRpdWellSagddT prws) throws Exception{
		try {
			this.getHibernateTemplate().delete(prws);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<Object[]> searchPmode(String sql) throws Exception {
		return Query.getSqlQuery(this.getHibernateTemplate(), sql);
	}

	public List<Object[]> searchPstages(String sql) throws Exception {
		return Query.getSqlQuery(this.getHibernateTemplate(), sql);
	}


	@SuppressWarnings({ "unused", "deprecation" })
	@Override
	public List<String> dayreport(String date1) throws Exception {
		List<String> rr = new ArrayList<String>();

		String date =date1;
//		Date b_date = new Date();
		
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date b_date = sdf.parse(date); //转换为util.date
		
		System.out.println(b_date+"wwwwwwwwwwwwwwwwwwwwwwwwww");

		ResultSet rs =null; 
		String procdure = "{Call p_gene_daily_sagd_auto(?,?,?)}";  
		            CallableStatement cs = this.getHibernateTemplate().getSessionFactory().openSession().connection().prepareCall(procdure);  
		            cs.setDate(1,   new java.sql.Date(b_date.getTime()));  
		            cs.registerOutParameter(2, OracleTypes.INTEGER); 
		            cs.registerOutParameter(3, OracleTypes.VARCHAR); 
		            cs.execute();
		            rr.add(String.valueOf(cs.getInt(2)));
		            rr.add(cs.getString(3));
		            
		      return rr;
	}

	public List<Object[]> searchStructureNew(String hql) {
		List<Object[]> menuList = null;
		menuList = Query.getSqlQuery(this.getHibernateTemplate(), hql);
		return menuList;
	}




	@Override
	public List<Object[]> searchSagdghRD(String searchThinOil) {
		List<Object[]> lo = Query.getSqlQuery(this.getHibernateTemplate(), searchThinOil);
		return lo;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> searchSagdghRD(final String sql, final int start, final int pagesize) {
		List<Object[]> resultList = this.getHibernateTemplate()
				 .executeFind(new HibernateCallback() {

					 String[] cloumnsName = {"OILSTATIONNAME","CJSJ","PIPE_SINK","pressure_press","pressure_temp","looped_press","looped_temp","measure_press","measure_temp"};
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
	public List<Object[]> searchSagd183(String searchsagd183) {
		List<Object[]> lo = Query.getSqlQuery(this.getHibernateTemplate(), searchsagd183);
		return lo;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> searchSagd183(final String sql, final int start,final int pagesize) {
		List<Object[]> resultList = this.getHibernateTemplate()
				 .executeFind(new HibernateCallback() {
					 
					 String[] cloumnsName = {"acquisition_time","flq_qckyl1","flq_ywgd1","flq_qdkfkd1","flq_ydkfkd1","flq_qckyl2","flq_ywgd2","flq_qdkfkd2",
								"flq_ydkfkd2","flq_qckyl3","flq_ywgd3","flq_qdkfkd3","flq_ydkfkd3","pump_in_zx_press","pump_in_zx_tem","pump_in_fx_press","pump_in_fx_tem","pump_out_zx_press","pump_out_zx_tem","pump_out_fx_press","pump_out_fx_tem",
								"pump_zt1","pump_pl1","pump_sd1","pump_zt2","pump_pl2","pump_sd2","pump_zt3","pump_pl3","pump_sd3"};
					 
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
	public List<Object[]> searchSagdnow(String searchSagdNow) {
		List<Object[]> lo = Query.getSqlQuery(this.getHibernateTemplate(), searchSagdNow);
		return lo;
	}



	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> searchSagdnow(final String sql, final int start,final int pagesize) {	
		List<Object[]> resultList = this.getHibernateTemplate()
				 .executeFind(new HibernateCallback() {
						String[] cloumnsName = {"a.oilstationname","a.cjsj","a.PIPE_SINK","a.PRESSURE_PRESS","a.PRESSURE_TEMP","a.LOOPED_PRESS",
								"a.LOOPED_TEMP","a.MEASURE_PRESS","a.MEASURE_TEMP"};
					 
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


	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> searchSagdss(final String boilersInfo,final int start, final int pagesize,final List<String> cloumnsName) {
		List<Object[]> resultList = this.getHibernateTemplate()
				 .executeFind(new HibernateCallback() {
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
	public List<Object[]> searchGrglzb1(String searchgrglzb1) {
		List<Object[]> lo = Query.getSqlQuery(this.getHibernateTemplate(), searchgrglzb1);
		return lo;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> searchGrglzb1(final String sql, final int start,final int pagesize) {
		List<Object[]> resultList = this.getHibernateTemplate()
				 .executeFind(new HibernateCallback() {
					 
					 String[] cloumnsName = {"gqdw","acquisition_time","glzts","glyxts","hgglts","bhgglts","hgl","bhggllh"};
					 
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
	public List<Object[]> searchEverySql(String sql) {

		return Query.getSqlQuery(this.getHibernateTemplate(), sql);
	}


	@SuppressWarnings("unchecked")
	public List<Object[]> serarchZqpqq(final String sql,final int start,
			final int rowsPerpage) {
		List<Object[]> resultList = this.getHibernateTemplate()
				 .executeFind(new HibernateCallback() {
		 String[] cloumnsName = {"steam_name", "acquisition_time","pt_101", "tt_101", "fit_101", "fiq_101","pt_102","tt_102",
				 "fit_102","fiq_102","pt_103","tt_103","fit_103","fiq_103","pt_104","tt_104","fit_104","fiq_104",
				 "pt_105","tt_105","fit_105","fiq_105"};
		 
		 public List<Object[]> doInHibernate(Session session)
				 throws HibernateException, SQLException {
						SQLQuery s = session.createSQLQuery(sql);
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


	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> serarchYcgx(final String sql,final int start,
			final int rowsPerpage) {
		List<Object[]> resultList = this.getHibernateTemplate()
				 .executeFind(new HibernateCallback() {
			
					 String[] cloumnsName = {"acquisition_time", "steam_name","pt_101", "tt_104", "pt_102", "tt_103","pt_103","tt_102", "pt_104","tt_101"};
		 
		 public List<Object[]> doInHibernate(Session session)
				 throws HibernateException, SQLException {
						SQLQuery s = session.createSQLQuery(sql);
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
	public List<Object[]> searchSagd1hrdt(String searchsagd1hrdt) {
			List<Object[]> lo = Query.getSqlQuery(this.getHibernateTemplate(), searchsagd1hrdt);
			return lo;
		}


	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> searchSagd1hrdt(final String sql, final int start,final int pagesize) {
		List<Object[]> resultList = this.getHibernateTemplate()
				 .executeFind(new HibernateCallback() {
					 
						String[] cloumnsName = {"acquisition_time","ZJYX_TEM","XHJY_TEM","FLQ_QCKYL1","FLQ_YCKYL1","FLQ_YWGD1","FLQ_QDKFKD1","FLQ_YDKFKD1","FLQ_QCKYL2","FLQ_YCKYL2",
								"FLQ_YWGD2","FLQ_QDKFKD2","FLQ_YDKFKD2","FLQ_QCKYL3","FLQ_YCKYL3","FLQ_YWGD3","FLQ_QDKFKD3","FLQ_YDKFKD3","PUMP_IN_PRESS1","PUMP_IN_TEM1","PUMP_OUT_PRESS1","PUMP_OUT_TEM1","PUMP_ZT1",
								"PUMP_PL1","PUMP_IN_PRESS2","PUMP_IN_TEM2","PUMP_OUT_PRESS2","PUMP_OUT_TEM2","PUMP_ZT2","PUMP_PL2","PUMP_IN_PRESS3","PUMP_IN_TEM3","PUMP_OUT_PRESS3","PUMP_OUT_TEM3","PUMP_ZT3","PUMP_PL3","PUMP_IN_PRESS4","PUMP_IN_TEM4",
								"PUMP_OUT_PRESS4","PUMP_OUT_TEM4","PUMP_ZT4","PUMP_PL4","PUMP_IN_PRESS5","PUMP_IN_TEM5","PUMP_OUT_PRESS5","PUMP_OUT_TEM5","PUMP_ZT5","PUMP_PL5"};
					 
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


	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> serarchFc160wf(final String sql,final int start,
			final int rowsPerpage) {
		List<Object[]> resultList = this.getHibernateTemplate()
				 .executeFind(new HibernateCallback() {
			
					 String[] cloumnsName = {"acquisition_time", "level1","pump_zt1", "pump_pl1", "pump_zt2", "pump_pl2","pump_zt3","pump_pl3"};
		 
		 public List<Object[]> doInHibernate(Session session)
				 throws HibernateException, SQLException {
						SQLQuery s = session.createSQLQuery(sql);
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


	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> serarchSagdlng(final String sql,final int start,
			final int rowsPerpage) {
		List<Object[]> resultList = this.getHibernateTemplate()
				 .executeFind(new HibernateCallback() {
			
					 String[] cloumnsName = {"acquisition_time", "LIT_1","LIT_2", "LIT_3", "LIT_4", "LIT_5","LIT_6","LIT_7","LIT_8","LIT_9","LIT_10","LIT_11","LIT_12","LIT_13","LIT_14"};
		 
		 public List<Object[]> doInHibernate(Session session)
				 throws HibernateException, SQLException {
						SQLQuery s = session.createSQLQuery(sql);
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


	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> serarchSagd1qtjc(final String sql,final int start,
			final int rowsPerpage) {
		List<Object[]> resultList = this.getHibernateTemplate()
				 .executeFind(new HibernateCallback() {
			
					 String[] cloumnsName = {"acquisition_time", "KRQT_ND1","KRQT_ND2", "YDQT_ND1", "YDQT_ND2", "YDQT_ND3","YDQT_ND4","YDQT_ND5","YDQT_ND6"};
		 public List<Object[]> doInHibernate(Session session)
				 throws HibernateException, SQLException {
						SQLQuery s = session.createSQLQuery(sql);
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


}
