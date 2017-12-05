package com.echo.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.echo.dao.IPUsedDao;
import com.echo.dao.Query;
import com.echo.dto.PcCdIpUsedT;

public class IPUsedDaoImpl extends HibernateDaoSupport implements IPUsedDao ,Serializable{

	//获取总条数
	public int getCounts(String sql) {
		int count = 0;
		List<Object[]> list = new ArrayList<Object[]>();
		list = Query.getSqlQuery(this.getHibernateTemplate(), sql);
		count = Integer.parseInt(String.valueOf(list.get(0)));
		return count;
	}

	@Override
	public List<Object[]> searchDatas(final String product, final int start,final  int rowsPerpage, final String[] cloumnsName) throws Exception {
		List<Object[]> resultList = this.getHibernateTemplate().executeFind(new HibernateCallback() {
			
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				SQLQuery s = session.createSQLQuery(product);
				for (String column : cloumnsName) {
					s.addScalar(column);
				}
				s.setFirstResult(start);
				s.setMaxResults(rowsPerpage);
				return s.list();
			}
		});
		return resultList;
	}

	@Override
	public List<Object[]> searchDatas(String product) throws Exception {
			List<Object[]> lo = Query.getSqlQuery(this.getHibernateTemplate(), product);
			return lo;
		}

	@Override
	public List<Object[]> searchSql(String sql) throws Exception {
		return Query.getSqlQuery(this.getHibernateTemplate(), sql);
	}

	@Override
	public List<PcCdIpUsedT> searchIpId(String hql) throws Exception {
	List<PcCdIpUsedT> list = (List<PcCdIpUsedT>)this.getHibernateTemplate().find(hql);
		return list;
	}

	@Override
	public boolean saveIpUsed(PcCdIpUsedT ip) throws Exception {
		boolean flg = true;
		this.getHibernateTemplate().merge(ip);
		return flg;
	}

	@Override
	public List<Object[]> searchSegmentId(String sql) throws Exception {
		List<Object[]> lo = Query.getSqlQuery(this.getHibernateTemplate(), sql);
		return lo;
	}
}
