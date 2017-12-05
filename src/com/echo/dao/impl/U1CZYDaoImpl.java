package com.echo.dao.impl;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import oracle.jdbc.OracleTypes;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.echo.dao.Query;
import com.echo.dao.U1CZYDao;
import com.echo.dto.PcRpdU1OilAutoT;


public class U1CZYDaoImpl extends HibernateDaoSupport implements U1CZYDao,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3078060861794718243L;



	public void setSessionFactoryOverride(SessionFactory sessionFactory)
    {
      super.setSessionFactory(sessionFactory);
    } 


	@SuppressWarnings("unchecked")
	public List<Object[]> searchU1CZY(String sql) {
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
	
	@Override
	public List<Object[]> searchCalcNum(String sql) throws Exception {
		// TODO Auto-generated method stub
		return Query.getSqlQuery(this.getHibernateTemplate(), sql);
	}


	@Override
	public List<PcRpdU1OilAutoT> SreachOILAuto(String hql) throws Exception {
		// TODO Auto-generated method stub
		List<PcRpdU1OilAutoT> autoList = null;
		autoList = (List<PcRpdU1OilAutoT>)this.getHibernateTemplate().find(hql);
		return  autoList;
	}


	@Override
	public boolean updateOILAuto(PcRpdU1OilAutoT oil) throws Exception {
		// TODO Auto-generated method stub
		boolean flg = true ;
		this.getHibernateTemplate().merge(oil);
		return flg;
	}
}
