package com.echo.dao.impl;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import oracle.jdbc.OracleTypes;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.echo.dao.CommonDao;
import com.echo.dao.Query;
import com.echo.dto.PcRpdUThinOilAutoT;
import com.echo.dto.PcRpdUThinOilGeneralT;
import com.echo.util.DateBean;


public class CommonDaoImpl<T> extends HibernateDaoSupport implements CommonDao,Serializable{
	private static final long serialVersionUID = 2739843474668469596L;

	@Override
	public List<Object[]> searchEverySql(String sql) {
		return Query.getSqlQuery(this.getHibernateTemplate(), sql);
	}

	@Override
	public int updateButhSql(String sql) throws Exception{
		// TODO Auto-generated method stub
		return Query.bulkDelete(this.getHibernateTemplate(), sql);
	}
	@SuppressWarnings("unchecked")
	public List<T> searchClassQuery(String hql) {
		List<T> lists = (List<T>)this.getHibernateTemplate().find(hql);
		return lists;
	}


	@Override
	public boolean addOrUpdateDatas(List<?> t) throws Exception {
		try {
			this.getHibernateTemplate().merge(t.get(0));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PcRpdUThinOilGeneralT> SreachOilGeneral(String hql)
			throws Exception {
		List<PcRpdUThinOilGeneralT> oilList = null;
		oilList = this.getHibernateTemplate().find(hql);
		return oilList;
	}

	@Override
	public boolean updateOilGeneral(PcRpdUThinOilGeneralT oil) throws Exception {
		boolean flg = true ;
		this.getHibernateTemplate().merge(oil);
		return flg;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PcRpdUThinOilAutoT> SreachAutp(String hql) throws Exception {
		List<PcRpdUThinOilAutoT> autoList = null;
		autoList = (List<PcRpdUThinOilAutoT>) this.getHibernateTemplate().find(hql);
		return autoList;
	}

	@Override
	public boolean updateAuto(PcRpdUThinOilAutoT auto) throws Exception {
		boolean flg = true;
		this.getHibernateTemplate().merge(auto);
		return flg;
	}


	@Override
	public List<String> getProcedures(String proceduresName,String date) throws Exception {
		List<String> rr = new ArrayList<String>();
		Date b_date = new Date();
		ResultSet rs =null; 
		String procdure = "{Call "+proceduresName+"(?,?,?)}";  
		            CallableStatement cs = this.getHibernateTemplate().getSessionFactory().openSession().connection().prepareCall(procdure);  
		            cs.setDate(1,   new java.sql.Date(DateBean.getsqlDateTime(date)));  
		            cs.registerOutParameter(2, OracleTypes.INTEGER); 
		            cs.registerOutParameter(3, OracleTypes.VARCHAR); 
		            cs.execute();
		            //System.out.println(cs.getInt(3)+cs.getString(4));
		            rr.add(String.valueOf(cs.getInt(2)));
		            rr.add(cs.getString(3));
		            
		      return rr;
	}
	@Override
	public List<String> getProcedures(String proceduresName,String date,String param) throws Exception {
		List<String> rr = new ArrayList<String>();
		Date b_date = new Date();
		ResultSet rs =null; 
		String procdure = "{Call "+proceduresName+"(?,?,?,?)}";  
		            CallableStatement cs = this.getHibernateTemplate().getSessionFactory().openSession().connection().prepareCall(procdure);  
		            cs.setDate(1,   new java.sql.Date(DateBean.getsqlDateTime(date)));  
		            cs.setString(2,   param); 
		            cs.registerOutParameter(3, OracleTypes.INTEGER); 
		            cs.registerOutParameter(4, OracleTypes.VARCHAR); 
		            cs.execute();
		            //System.out.println(cs.getInt(3)+cs.getString(4));
		            rr.add(String.valueOf(cs.getInt(3)));
		            rr.add(cs.getString(4));
		            
		      return rr;
	}
	
	public int getCounts(String sql) throws Exception{
		int count = 0;
		List<Object[]> list = new ArrayList<Object[]>();
		list = Query.getSqlQuery(this.getHibernateTemplate(), sql);
		count = Integer.parseInt(String.valueOf(list.get(0))) ;
		return count;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> searchPaging(final String boilersInfo,final int start, final int pagesize,final String[] cloumnsName) {
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
	public boolean removeDatas(List<String> sqls) throws Exception {

		SessionFactory sessionFactory=this.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		Transaction tx = session.beginTransaction();  
		tx.begin();
		for(int i = 0;i < sqls.size(); i++){
			org.hibernate.Query query  =  session.createSQLQuery(sqls.get(i));
			query.executeUpdate();
		}
		
		tx.commit();
		session.close();

		return true;
	
	}
	
	public boolean removeData(String sql) throws Exception {

		SessionFactory sessionFactory=this.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		Transaction tx = session.beginTransaction();  
		tx.begin();
		//for(int i = 0;i < sqls.size(); i++){
			org.hibernate.Query query  =  session.createSQLQuery(sql);
			query.executeUpdate();
		//}
		
		tx.commit();
		session.close();

		return true;
	
	}


	@Override
	public List<String> getProcedures(String proceduresName, String date,
			String param, int param1) throws Exception {

		List<String> rr = new ArrayList<String>();
		Date b_date = new Date();
		ResultSet rs =null; 
		String procdure = "{Call "+proceduresName+"(?,?,?,?,?)}";  
		            CallableStatement cs = this.getHibernateTemplate().getSessionFactory().openSession().connection().prepareCall(procdure);  
		            cs.setDate(1,   new java.sql.Date(DateBean.getsqlDateTime(date)));  
		            cs.setString(2,   param);
		            cs.setInt(3,   param1); 
		            cs.registerOutParameter(4, OracleTypes.INTEGER); 
		            cs.registerOutParameter(5, OracleTypes.VARCHAR); 
		            cs.execute();
		            //System.out.println(cs.getInt(3)+cs.getString(4));
		            rr.add(String.valueOf(cs.getInt(4)));
		            rr.add(cs.getString(5));
		            
		      return rr;
	
	}

	@Override
	public List<String> dayreport() throws Exception {
		List<String> rr = new ArrayList<String>();
		Date b_date = new Date();
		ResultSet rs =null; 
		String procdure = "{Call P_GENE_D_GQLHZZH(?,?,?)}";  
		            CallableStatement cs = this.getHibernateTemplate().getSessionFactory().openSession().connection().prepareCall(procdure);  
		            cs.setDate(1,   new java.sql.Date(b_date.getTime()));  
		            cs.registerOutParameter(2, OracleTypes.INTEGER); 
		            cs.registerOutParameter(3, OracleTypes.VARCHAR); 
		            cs.execute();
		            //System.out.println(cs.getInt(3)+cs.getString(4));
		            rr.add(String.valueOf(cs.getInt(2)));
		            rr.add(cs.getString(3));
		            
		      return rr;
	}

	@Override
	public List<Object[]> queryInfo(String timeCalssql) {
		return Query.getSqlQuery(this.getHibernateTemplate(), timeCalssql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> searchRhqzs1(final String sql,final int start,final int rowsPerpage)
			throws Exception {
		List<Object[]> resultList = this.getHibernateTemplate()
				 .executeFind(new HibernateCallback() {

					String[] cloumnsName = {"zh","rq","rhqbh","situation_log1"};	
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
	public List<Object[]> searchRhqzs1(String sql) throws Exception {
		List<Object[]> lo = Query.getSqlQuery(this.getHibernateTemplate(), sql);
		return lo;
	}

	@Override
	public List<Object[]> searchU2fxcy(String searchsagd183) throws Exception {
		List<Object[]> lo = Query.getSqlQuery(this.getHibernateTemplate(), searchsagd183);
		return lo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> searchU2fxcy( final String sql, final int start,final int pagesize) throws Exception {
		List<Object[]> resultList = this.getHibernateTemplate()
				 .executeFind(new HibernateCallback() {
					 
					 String[] cloumnsName = {"report_date","FIT_HH_10101AV","DB10101","FIT_HH_10102AV","DB10102","FIT_HH_10103AV","DB10103","FIT_HH_10104AV",
								"DB10104","FIT_HH_109","DB109","FIT_HH_10108AV","DB10108","FC2YE","DB_FC2YE","FC3YE","DB_FC3YE"};
					 
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


	
		

}