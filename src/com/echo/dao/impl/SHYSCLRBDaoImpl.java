package com.echo.dao.impl;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.echo.dao.SHYSCLRBDao;
import com.echo.dao.T2sclrbDao;
import com.echo.dto.PcRpdRhsclT2T;
import com.echo.dto.PcRpdShysclT;

public class SHYSCLRBDaoImpl extends HibernateDaoSupport implements SHYSCLRBDao {

	@Override
	public boolean saveData(PcRpdShysclT wt) throws Exception {
		boolean  flag = true;
		this.getHibernateTemplate().merge(wt);
		return flag;
	}

}
