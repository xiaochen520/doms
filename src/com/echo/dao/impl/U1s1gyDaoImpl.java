package com.echo.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.echo.dao.Query;
import com.echo.dao.U1s1gyDao;
import com.echo.dto.PcRpdU1WaterAutoT;
import com.echo.dto.PcRpdU2TSJY;
import com.echo.dto.PcRpdU2TSWS;
import com.echo.dto.PcRpdU2TSZH;
import com.echo.dto.PcRpdU2WaterAutoT;


public class U1s1gyDaoImpl extends HibernateDaoSupport implements U1s1gyDao,Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public List<Object[]> searchU1s1gy(String sql) throws Exception {
		return Query.getSqlQuery(this.getHibernateTemplate(), sql);
	}

	@Override
	public List<PcRpdU1WaterAutoT> SreachU1S1gy(String hql) throws Exception {
		List<PcRpdU1WaterAutoT> list = null;
		list = (List<PcRpdU1WaterAutoT>) this.getHibernateTemplate().find(hql);
		return list;
	}
	public List<PcRpdU2WaterAutoT> SreachU2S1gy(String hql) throws Exception {
		List<PcRpdU2WaterAutoT> list = null;
		list = (List<PcRpdU2WaterAutoT>) this.getHibernateTemplate().find(hql);
		return list;
	}
	public List<PcRpdU2TSJY> SreachTSJY(String hql) throws Exception {
		List<PcRpdU2TSJY> list = null;
		list = (List<PcRpdU2TSJY>) this.getHibernateTemplate().find(hql);
		return list;
	}
	public List<PcRpdU2TSWS> SreachTSWS(String hql) throws Exception {
		List<PcRpdU2TSWS> list = null;
		list = (List<PcRpdU2TSWS>) this.getHibernateTemplate().find(hql);
		return list;
	}
	public List<PcRpdU2TSZH> SreachTSZH(String hql) throws Exception {
		List<PcRpdU2TSZH> list = null;
		list = (List<PcRpdU2TSZH>) this.getHibernateTemplate().find(hql);
		return list;
	}
	@Override
	public boolean updateU1S1gy(PcRpdU1WaterAutoT us) throws Exception {
		boolean flg = true;
		this.getHibernateTemplate().merge(us);
		return flg;
	}
	public boolean updateU2S1gy(Object us) throws Exception {
		boolean flg = true;
		this.getHibernateTemplate().merge(us);
		return flg;
	}
}
