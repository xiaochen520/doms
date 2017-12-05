package com.echo.dao.impl;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.echo.dao.T2sclrbDao;
import com.echo.dto.PcRpdRhsclT2T;

public class T2sclrbDaoImpl extends HibernateDaoSupport implements T2sclrbDao {

	@Override
	public boolean saveData(PcRpdRhsclT2T wt) throws Exception {
		boolean  flag = true;
		this.getHibernateTemplate().merge(wt);
		return flag;
	}

}
