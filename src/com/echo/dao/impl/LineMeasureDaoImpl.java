package com.echo.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.echo.dao.LineMeasureDao;
import com.echo.dao.Query;
import com.echo.dto.PcRpd1836;
import com.echo.dto.PcRpd1836zh;
import com.echo.dto.PcRpdCy1ScMessureT;
import com.echo.dto.PcRpdCy2ScMessureT;
import com.echo.dto.PcRpdReportRhs2T;
import com.echo.dto.PcRpdU1DehydrationT;
import com.echo.dto.PcRpdU2zyz;
import com.echo.dto.PcRpdU2zyzzh;
import com.echo.dto.PcRpdXylScMessureT;
import com.echo.dto.PcRpdXzyzhg;
import com.echo.dto.PcRpdXzyzhgzh;
import com.echo.dto.PcRpdxczs;
import com.echo.dto.PcRpdxczszh;

public class LineMeasureDaoImpl extends HibernateDaoSupport implements LineMeasureDao,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public int getCounts(String sql) {
		int count = 0;
		List<Object[]> list = new ArrayList<Object[]>();
		list = Query.getSqlQuery(this.getHibernateTemplate(), sql);
		count = Integer.parseInt(String.valueOf(list.get(0))) ;
		return count;
	}


	@Override
	public List<Object[]> searchLineM(String sql) throws Exception {
		return Query.getSqlQuery(this.getHibernateTemplate(), sql);
	}


	@Override
	public List<Object[]> searchU2OIL(String sql) {	
		return Query.getSqlQuery(this.getHibernateTemplate(), sql);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<PcRpd1836zh> Sreach1836zh(String hql) throws Exception {
		List<PcRpd1836zh> list = null;
		list = (List<PcRpd1836zh>) this.getHibernateTemplate().find(hql);
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<PcRpd1836> Sreach1836(String hql) throws Exception {
		List<PcRpd1836> list = null;
		list = (List<PcRpd1836>) this.getHibernateTemplate().find(hql);
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<PcRpdxczszh> Sreachxczszh(String hql) throws Exception {
		List<PcRpdxczszh> list = null;
		list = (List<PcRpdxczszh>) this.getHibernateTemplate().find(hql);
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<PcRpdxczs> Sreachxczs(String hql) throws Exception {
		List<PcRpdxczs> list = null;
		list = (List<PcRpdxczs>) this.getHibernateTemplate().find(hql);
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<PcRpdU1DehydrationT> SreachN1tscs(String hql) throws Exception {
		List<PcRpdU1DehydrationT> list = null;
		list = (List<PcRpdU1DehydrationT>) this.getHibernateTemplate().find(hql);
		return list;
	}
	
	public boolean updatedata(Object us) throws Exception {
		boolean flg = true;
		this.getHibernateTemplate().merge(us);
		return flg;
	}


	@SuppressWarnings("unchecked")
	public List<PcRpdU2zyzzh> SreachU2zyzzh(String hql) throws Exception {
		List<PcRpdU2zyzzh> list = null;
		list = (List<PcRpdU2zyzzh>) this.getHibernateTemplate().find(hql);
		return list;		
	}


	@SuppressWarnings("unchecked")

	public List<PcRpdU2zyz> SreachU2zyz(String hql) throws Exception {	
		List<PcRpdU2zyz> list = null;
		list = (List<PcRpdU2zyz>) this.getHibernateTemplate().find(hql);
		return list;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<PcRpdXzyzhgzh> SreachXzyzhgzh(String hql) throws Exception {
		List<PcRpdXzyzhgzh> list = null;
		list = (List<PcRpdXzyzhgzh>) this.getHibernateTemplate().find(hql);
		return list;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<PcRpdXzyzhg> SreachXzyzhg(String hql) throws Exception {
		List<PcRpdXzyzhg> list = null;
		list = (List<PcRpdXzyzhg>) this.getHibernateTemplate().find(hql);
		return list;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<PcRpdCy1ScMessureT> Sreachcyfx1(String hql) {
		List<PcRpdCy1ScMessureT> list = null;
		list = (List<PcRpdCy1ScMessureT>) this.getHibernateTemplate().find(hql);
		return list;
	}

	public PcRpdCy1ScMessureT Sreachcyfx1rq(String hql) {
		PcRpdCy1ScMessureT list = null;
		list = (PcRpdCy1ScMessureT) this.getHibernateTemplate().find(hql);
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PcRpdCy2ScMessureT> Sreachcyfx2(String hql) {
		List<PcRpdCy2ScMessureT> list = null;
		list = (List<PcRpdCy2ScMessureT>) this.getHibernateTemplate().find(hql);
		return list;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<PcRpdXylScMessureT> Sreachxyfx3(String hql) {
		List<PcRpdXylScMessureT> list = null;
		list = (List<PcRpdXylScMessureT>) this.getHibernateTemplate().find(hql);
		return list;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<PcRpdReportRhs2T> Sreachu2rhs(String hql) {
		List<PcRpdReportRhs2T> list = null;
		list = (List<PcRpdReportRhs2T>) this.getHibernateTemplate().find(hql);
		return list;
	}


	@Override
	public List<Object[]> searchExportData(String sqls) {
		return Query.getSqlQuery(this.getHibernateTemplate(), sqls);
	}

}