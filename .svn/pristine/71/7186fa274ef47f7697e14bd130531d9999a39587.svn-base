package com.echo.dao.impl;

import java.io.Serializable;
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
import com.echo.dao.SubcoolDao;
import com.echo.dto.PcCdSubcoolCalParamsInfoT;
import com.echo.dto.PcCdSubcoolDefaultParam;
import com.echo.dto.PcRdSubcoolCalResultsT;
import com.echo.dto.PcRdSubcoolModifyNewT;
import com.echo.dto.PcRdSubcoolModifyT;
import com.echo.dto.PcRdSuggestionRulesT;

/**
 * 
 * @Company: Etrol
 * @ClassName: SubcoolDaoImpl
 * @author LIJUN
 * @date 2015-8-20
 */
public class SubcoolDaoImpl extends HibernateDaoSupport implements SubcoolDao {

	public void setSessionFactoryOverride(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Override
	public boolean addOrUpdateSubcoolDefaultParam(
			PcCdSubcoolDefaultParam subcoolParam) {
		this.getHibernateTemplate().merge(subcoolParam);
		return true;
	}

	@Override
	public Object[] getSubcoolDefaultParam(String sql) {
		List<Object[]> list = Query.getSqlQuery(this.getHibernateTemplate(),
				sql);
		Object[] obj = null;
		if (list.size() > 0)
			obj = list.get(0);
		return obj;
	}

	@Override
	public PcCdSubcoolCalParamsInfoT getCalParamsById(String id) {

		return (PcCdSubcoolCalParamsInfoT) this.getHibernateTemplate().get(
				PcCdSubcoolCalParamsInfoT.class, id);
	}

	@Override
	public boolean addSubcoolCalParams(PcCdSubcoolCalParamsInfoT alarmParam) {

		Object obj = this.getHibernateTemplate().merge(alarmParam);
		if (!"".equals(obj) || obj != null)
			return true;
		else
			return false;
	}

	@Override
	public boolean updataSubcoolCalParams(PcCdSubcoolCalParamsInfoT alarmParam) {
		try {
			this.getHibernateTemplate().update(alarmParam);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public int getCounts(String sql) {
		int count = 0;
		List<Object[]> list = new ArrayList<Object[]>();
		list = Query.getSqlQuery(this.getHibernateTemplate(), sql);
		count = Integer.parseInt(String.valueOf(list.get(0)));
		return count;
	}

	@Override
	public List<Object[]> queryInfo(String hql) {
		return Query.getSqlQuery(this.getHibernateTemplate(), hql);
	}

	@Override
	public boolean removeSubcoolCalParams(PcCdSubcoolCalParamsInfoT alarmParam) {
		try {
			this.getHibernateTemplate().delete(alarmParam);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public PcCdSubcoolCalParamsInfoT findCalParsmsBySagd(String sagdId) {
		String hql = "from PcCdSubcoolCalParamsInfoT where sagdId = '" + sagdId
				+ "'";
		List<PcCdSubcoolCalParamsInfoT> list = this.getHibernateTemplate()
				.find(hql);
		if (list.size() > 0)
			return list.get(0);
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> searchSubcoolAlarmParams(final String hql,
			final int start, final int pagesize) {
		List<Object[]> resultList = this.getHibernateTemplate().executeFind(
				new HibernateCallback() {
					String[] cloumnsName = { "CALID", "SAGDID", "ORG_ID", "JHMC",
							"JHMC_S", "OILSTATIONNAME", "AREABLOCK",
							"BLOCKSTATIONNAME", "PRODSTAGES",
							"calculate_methods", "calculate_methodsid",
							"calculate_rate", "max_subcool", "min_subcool",
							"flow_methods", "flowid" };

					public List<Object[]> doInHibernate(Session session)
							throws HibernateException, SQLException {
						SQLQuery s = session.createSQLQuery(hql);
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

	@Override
	public boolean addSubcoolCalResults(PcRdSubcoolCalResultsT calResults) {
		Object obj = this.getHibernateTemplate().merge(calResults);
		if (!"".equals(obj) || obj != null)
			return true;
		else
			return false;
	}

	@Override
	public boolean updataSubcoolCalResults(PcRdSubcoolCalResultsT calResults) {
		try {
			this.getHibernateTemplate().update(calResults);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}

	@Override
	public PcRdSubcoolCalResultsT getCalResultsById(String id) {
		return (PcRdSubcoolCalResultsT) this.getHibernateTemplate().get(
				PcRdSubcoolCalResultsT.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> searchSubcoolCalResults(final String hql,
			final int start, final int pagesize, final String[] cloumnsName) {
		List<Object[]> resultList = this.getHibernateTemplate().executeFind(
				new HibernateCallback() {
					public List<Object[]> doInHibernate(Session session)
							throws HibernateException, SQLException {
						SQLQuery s = session.createSQLQuery(hql);
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

	@SuppressWarnings("unchecked")
	@Override
	public Object[] getSubcoolCalResult(final String hql, final String[] cloumnsName) {
		List<Object[]> resultList = this.getHibernateTemplate().executeFind(
				new HibernateCallback() {

					public List<Object[]> doInHibernate(Session session)
							throws HibernateException, SQLException {
						SQLQuery s = session.createSQLQuery(hql);
						for (String cloumn : cloumnsName) {
							s.addScalar(cloumn);
						}
						return s.list();
					}
				});

		return resultList.get(0);
	}

	@Override
	public boolean addSuggestionrules(PcRdSuggestionRulesT suggestion) {
		Object obj = this.getHibernateTemplate().merge(suggestion);
		if (!"".equals(obj) || obj != null)
			return true;
		else
			return false;
	}

	@Override
	public boolean addSubcoolModify(PcRdSubcoolModifyT modify) {
		Object obj = this.getHibernateTemplate().merge(modify);
		if (!"".equals(obj) || obj != null)
			return true;
		else
			return false;
	}

	@Override
	public PcRdSubcoolModifyNewT getSubcoolModifyNew(String id) {
		return (PcRdSubcoolModifyNewT) this.getHibernateTemplate().get(
				PcRdSubcoolModifyNewT.class, id);
	}

	@Override
	public boolean updateSubcoolModifyNew(PcRdSubcoolModifyNewT modify) {
		try {
			this.getHibernateTemplate().update(modify);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
