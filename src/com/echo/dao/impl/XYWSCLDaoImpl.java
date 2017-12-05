package com.echo.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.echo.dao.Query;
import com.echo.dao.XYWSCLDao;
import com.echo.dto.PcRpdUThinWaterAutoT;
import com.echo.dto.PcRpdUThinWaterGeneralT;


public class XYWSCLDaoImpl extends HibernateDaoSupport implements XYWSCLDao,Serializable{

	private static final long serialVersionUID = 759338865259822939L;

	@Override
	public List<Object[]> searchXYWSDatas(String sql) {
		return Query.getSqlQuery(this.getHibernateTemplate(), sql);
	}

	@Override
	public List<PcRpdUThinWaterGeneralT> SreachWaterGeneral(String hql) {
		// TODO Auto-generated method stub
		List<PcRpdUThinWaterGeneralT> waterGeneral = null;
		waterGeneral = (List<PcRpdUThinWaterGeneralT>)this.getHibernateTemplate().find(hql);
		return waterGeneral;
	}

	@Override
	public boolean updateWaterGeneral(PcRpdUThinWaterGeneralT waterGen) {
		// TODO Auto-generated method stub
		boolean flg = true ;
		this.getHibernateTemplate().merge(waterGen);
		return flg;
	}

	@Override
	public List<PcRpdUThinWaterAutoT> SreachWaterAuto(String hql) {
		List<PcRpdUThinWaterAutoT> autoList = null;
		autoList = (List<PcRpdUThinWaterAutoT>)this.getHibernateTemplate().find(hql);
		return  autoList;
	}

	@Override
	public boolean updateWaterAuto(PcRpdUThinWaterAutoT waterAuto) {
		// TODO Auto-generated method stub
		boolean flg = true ;
		this.getHibernateTemplate().merge(waterAuto);
		return flg;
	}

	@Override
	public List<Object[]> searchCalcNum(String sql) {
		// TODO Auto-generated method stub
		return Query.getSqlQuery(this.getHibernateTemplate(), sql);
	}

}
