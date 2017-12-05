package com.echo.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.echo.dao.Query;
import com.echo.dao.U1s2gyDao;
import com.echo.dto.PcRpdReportMessageT;
import com.echo.dto.PcRpdU1SagdPivotalT;
import com.echo.dto.PcRpdU1WaterAutoT;


public class U1s2gyDaoImpl extends HibernateDaoSupport implements U1s2gyDao,Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<Object[]> searchEverySql(String sql) {
		return Query.getSqlQuery(this.getHibernateTemplate(), sql);
	}
	
	public boolean updateU1s2gys(PcRpdU1WaterAutoT us) throws Exception {
		boolean flg = true;
		this.getHibernateTemplate().merge(us);
		return flg;
	}
	
	@SuppressWarnings("unchecked")
	public List<PcRpdU1WaterAutoT> SreachU1S1gy(String hql) throws Exception {
		List<PcRpdU1WaterAutoT> list = null;
		list = (List<PcRpdU1WaterAutoT>) this.getHibernateTemplate().find(hql);
		return list;
	}
	
	public boolean updateU1s2gys(Integer[] ints, Double[] d,String id) throws Exception {
		String sql = "update PC_RPD_U1_WATER_AUTO_T set s2lsllj=?,s2hssllj=?,s2qf1lj=?,s2qf2lj=?,s2wssllj=?,s2rxlj=?,lit_601b=?,"
				+ "lit_601a=?,lit_603=?,lit_602a=?,lit_602b=?,lt11015=?,lt11016=? where rpd_u1_water_auto_id=?;";
		Session s = (Session) this.getSession();
		Transaction t = s.beginTransaction();
		t.begin();
		SQLQuery q = this.getSession().createSQLQuery(sql);
		q.setInteger(0, ints[0]);
		q.setInteger(1, ints[1]);
		q.setInteger(2, ints[2]);
		q.setInteger(3, ints[3]);
		q.setInteger(4, ints[4]);
		q.setInteger(5, ints[5]);
		q.setDouble(6, d[0]);
		q.setDouble(7, d[1]);
		q.setDouble(8, d[2]);
		q.setDouble(9, d[3]);
		q.setDouble(10, d[4]);
		q.setDouble(11, d[5]);
		q.setDouble(12, d[6]);
		q.setString(13, id);
		int rowCount = q.executeUpdate();
		if (rowCount > 0) {
//			q.list();
//			this.getSession().flush();
//			this.getSession().clear();
//			this.getSession().close();
			//q.list();
			t.commit();
		}
//		this.getSession().getTransaction().commit();
		System.out.println("rowCount:" + rowCount);
		boolean flg = true ;
//		this.getHibernateTemplate().merge(pivotalRec);
		return flg;
	}
	@SuppressWarnings("unchecked")
	public List<PcRpdReportMessageT> SearchReportMessageTs(String hql) {
		List<PcRpdReportMessageT> reportMessages = null;
		reportMessages = (List<PcRpdReportMessageT>)this.getHibernateTemplate().find(hql);
		return reportMessages;
	}
	
	public boolean updateReportMessages(PcRpdReportMessageT reportMessage) throws Exception{
		boolean flg = true ;
		this.getHibernateTemplate().merge(reportMessage);
		return flg;
	}
}
