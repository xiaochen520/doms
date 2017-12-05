package com.echo.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.echo.dao.CombinationDao;
import com.echo.dao.ComboBoxDao;
import com.echo.dto.PcCdSwitchInFlgT;


public class ComboBoxDaoImpl extends HibernateDaoSupport implements ComboBoxDao,Serializable{

	
	private static final long serialVersionUID = 1L;
	

	@Override
	public List<PcCdSwitchInFlgT> searchSwitchInflg(String hql) {
		List<PcCdSwitchInFlgT> SwitchInflgs = null;
		SwitchInflgs = (List<PcCdSwitchInFlgT>)this.getHibernateTemplate().find(hql);
		return SwitchInflgs;
	}
	
}
