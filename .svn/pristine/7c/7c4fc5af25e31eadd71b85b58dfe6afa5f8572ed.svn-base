package com.echo.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.echo.dao.PageDao;
import com.echo.dao.Query;


public class PageDaoImpl extends HibernateDaoSupport implements PageDao,Serializable{

	private static final long serialVersionUID = -7769355133448838082L;
	/**
	 * 获取sql计算总条数
	 */
	public int getCounts(String sql) {
		int count = 0;
		List<Object[]> list = new ArrayList<Object[]>();
		list = Query.getSqlQuery(this.getHibernateTemplate(), sql);
		count = Integer.parseInt(String.valueOf(list.get(0))) ;
		return count;
	}

	public int getCounts(String sql, Object obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	


}
