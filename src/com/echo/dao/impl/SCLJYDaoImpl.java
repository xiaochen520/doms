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

import com.echo.dao.Query;
import com.echo.dao.SCLJYDao;
import com.echo.dto.PcCdServerNodeT;
import com.echo.dto.PcCdWellSagdT;
import com.echo.dto.PcRpdWellSagddT;


public class SCLJYDaoImpl extends HibernateDaoSupport implements SCLJYDao,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3078060861794718243L;



	public void setSessionFactoryOverride(SessionFactory sessionFactory)
    {
      super.setSessionFactory(sessionFactory);
    } 


	@SuppressWarnings("unchecked")
	public List<Object[]> searchSCLJYRPD(String sql) {
		return Query.getSqlQuery(this.getHibernateTemplate(), sql);
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
	
	public List<Object[]> queryInfo(String hql) {
		return Query.getSqlQuery(this.getHibernateTemplate(), hql);
	}
//	public boolean addOrUpdateSagddRPD(PcRpdWellSagddT prws) throws Exception{
//		try {
//			this.getHibernateTemplate().merge(prws);
//			return true;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return false;
//	}

	@Override
	public List<String> dayreport() throws Exception {
		List<String> rr = new ArrayList<String>();
		Date b_date = new Date();
		ResultSet rs =null; 
		String procdure = "{Call p_gene_daily_sagd(?,?,?)}";  
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
}
