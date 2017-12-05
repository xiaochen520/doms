package com.echo.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.echo.dao.Query;
import com.echo.dao.RightDao;
import com.echo.dto.PcCdRightT;


public class RightDaoImpl extends HibernateDaoSupport implements RightDao,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8701235407918711669L;


	/**
	 * 
	 */

	public void setSessionFactoryOverride(SessionFactory sessionFactory)
    {
      super.setSessionFactory(sessionFactory);
    } 

	
	@SuppressWarnings("unchecked")
	public List<PcCdRightT> searchRight(String hql) throws Exception{
		List<PcCdRightT> rightList = null;
		
		rightList = (List<PcCdRightT>)this.getHibernateTemplate().find(hql);
		
		return rightList;
	}


	public List<Object[]> searchAllRight(String hql) throws Exception {
		// TODO Auto-generated method stub
		return Query.getSqlQuery(this.getHibernateTemplate(), hql);
	}
	
}
