package com.echo.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.echo.dao.Query;
import com.echo.dao.U2szjcDao;
import com.echo.dto.PcRpdU2WaterQualityT;


public class U2szjcDaoImpl extends HibernateDaoSupport implements U2szjcDao,Serializable{

	private static final long serialVersionUID = -4754636301135588444L;

	@Override
	public List<Object[]> searchU2szjc(String sql) {
		return Query.getSqlQuery(this.getHibernateTemplate(), sql);
	}

	@Override
	public List<PcRpdU2WaterQualityT> searchU2WaterQuality(String hql) {
		List<PcRpdU2WaterQualityT> lists = null;
		lists = (List<PcRpdU2WaterQualityT>)this.getHibernateTemplate().find(hql);
		return lists;
	}

	@Override
	public boolean addOrUpdateDatas(PcRpdU2WaterQualityT prws) throws Exception {
		try {
			this.getHibernateTemplate().merge(prws);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	

	
	
}
