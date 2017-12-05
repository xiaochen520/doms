package com.echo.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.echo.dao.Query;

import com.echo.dao.BoilerRoomGasMonitoringDataDao;
import com.echo.dto.BoilerRoomGasMonitoringData;

/**
 * 
 * @author  王博
 * @date 2017-5-4
 * @time 上午11:27:11
 *
 */
@SuppressWarnings("all")
public class BoilerRoomGasMonitoringDataDaoImpl extends HibernateDaoSupport
		implements BoilerRoomGasMonitoringDataDao, Serializable {
	private static final long serialVersionUID = -3064246231402134628L;
	public void setSessionFactoryOverride(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	@Override
	public List<BoilerRoomGasMonitoringData> searchDataBySql(String str) {
		return null;
	}

	@Override
	public List<Object[]> searchDataBySql(final String sql, final int start,
			final int pagesize) {

		List<Object[]> resultList = this.getHibernateTemplate().executeFind(
				new HibernateCallback() {
					String[] cloumnsName = { "BOILER_H2S_T_ID",
							"ACQUISITIOM_TIME", "BLOCKSTATION_NAME",
							"H2S_ND_1", "KRQT_ND_1", "H2S_ND_2", "KRQT_ND_2",
							"H2S_ND_3", "KRQT_ND_3", "H2S_ND_4", "KRQT_ND_4",
							"H2S_ND_5", "KRQT_ND_5" };

					public List<Object[]> doInHibernate(Session session)
							throws HibernateException, SQLException {
						SQLQuery s = session.createSQLQuery(sql);
						for (String cloumn : cloumnsName) {
							s.addScalar(cloumn);
						}
						s.setFirstResult(start);
						s.setMaxResults(pagesize);
						return s.list();
					}
				});

		return null;
	}
	
	@Override
	public List<Object[]> searchData(final String boilersInfo,
			final String[] cloumnsName, final int start, final int pagesize) {
		List<Object[]> resultList = this.getHibernateTemplate().executeFind(
				new HibernateCallback() {
					public List<Object[]> doInHibernate(Session session)
							throws HibernateException, SQLException {
						SQLQuery s = session.createSQLQuery(boilersInfo);
						for (String cloumn : cloumnsName) {
							s.addScalar(cloumn);
						}
						s.setFirstResult(start);
						s.setMaxResults(pagesize);
						return s.list();
					}
				});
		return resultList;
	}

	/**
	 * 获取总条数
	 * 
	 * @param hibernateTemplate
	 * @param sql
	 * @return
	 */
	@Override
	public int getCounts(String sql) {
		int count = 0;
		List<Object[]> list = new ArrayList<Object[]>();
		list = Query.getSqlQuery(this.getHibernateTemplate(), sql);
		count = Integer.parseInt(String.valueOf(list.get(0)));
		return count;
	}

	@Override
	public List<Object[]> searchData(String searchThinOil) {
		List<Object[]> lo = Query.getSqlQuery(this.getHibernateTemplate(),
				searchThinOil);
		return lo;
	}

}
