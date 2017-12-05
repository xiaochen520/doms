package com.echo.dao.impl;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.echo.dao.SyjyxrbDao;
import com.echo.dto.PcRpdMollifierT;
import com.echo.dto.PcRpdWaterSourceWelldT;

public class RhqyxrbDaoImpl extends HibernateDaoSupport implements SyjyxrbDao{

	@Override
	public boolean addOrUpdateDatas(PcRpdWaterSourceWelldT wt) {
		boolean  flag = true;
		this.getHibernateTemplate().merge(wt);
		return flag;
	}

	@Override
	public boolean saveOrUpdateDatas(PcRpdMollifierT wt) throws Exception {
		boolean  flag = true;
		this.getHibernateTemplate().merge(wt);
		return flag;
	}

}
