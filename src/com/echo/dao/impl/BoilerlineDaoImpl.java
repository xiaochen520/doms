package com.echo.dao.impl;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import oracle.jdbc.OracleTypes;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.echo.dao.BoilerlineDao;
import com.echo.dao.Query;
import com.echo.dto.PcCdBlCellT;
import com.echo.dto.PcCdDepartmentT;
import com.echo.dto.PcRpdWellSagddT;

public class BoilerlineDaoImpl extends HibernateDaoSupport implements
		BoilerlineDao, Serializable {

	private static final long serialVersionUID = -3064266231401134620L;

	public void setSessionFactoryOverride(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	public List<PcCdDepartmentT> searchDep(String hql) throws Exception {
		List<PcCdDepartmentT> depList = null;
		depList = (List<PcCdDepartmentT>) this.getHibernateTemplate().find(hql);
		return depList;
	}

	public List<Object[]> searchBlines(final String sql, final int start,
			final int pagesize) throws Exception {

		List<Object[]> resultList = this.getHibernateTemplate().executeFind(
				new HibernateCallback() {
					String[] cloumnsName = { "CELL_ID", "SPLIT_MODEL",
							"VALID_DATE", "CELL_NAME", "OWNER_ORG", "REMARK",
							"RLAST_OPER", "RLAST_ODATE" };

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

		return resultList;
	}

	public boolean save(PcCdDepartmentT dep) throws Exception {
		Serializable flg = this.getHibernateTemplate().save(dep);
		if (flg != null && !"".equals(flg)) {
			return true;
		} else {
			return false;
		}

	}

	public boolean updateDep(PcCdDepartmentT dep) throws Exception {
		boolean flg = true;
		this.getHibernateTemplate().merge(dep);
		return flg;
	}

	public boolean removeLX(String[] sqls) throws Exception {
		SessionFactory sessionFactory = this.getSessionFactory();
		Session session = sessionFactory.openSession();

		Transaction tx = session.beginTransaction();
		tx.begin();
		for (int i = 0; i < sqls.length; i++) {
			if (sqls[i] != null && !"".equals(sqls[i])
					&& !"null".equals(sqls[i])) {
				org.hibernate.Query query = session.createSQLQuery(sqls[i]);
				query.executeUpdate();
			}
		}

		tx.commit();
		session.close();

		return true;
	}

	public List<PcCdBlCellT> seachGLJKS(String hql) throws Exception {
		List<PcCdBlCellT> blCellTs = null;

		blCellTs = (List<PcCdBlCellT>) this.getHibernateTemplate().find(hql);

		return blCellTs;
	}

	public List<Object[]> searchObj(String sql) throws Exception {
		List<Object[]> objList = null;
		objList = Query.getSqlQuery(this.getHibernateTemplate(), sql);
		return objList;
	}

	public List<Object[]> searchInfo(String hql) {
		return Query.getSqlQuery(this.getHibernateTemplate(), hql);
	}

	public boolean addOrUpdateBoilerLine(PcCdBlCellT blCell) throws Exception {
		try {
			this.getHibernateTemplate().merge(blCell);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean seachSystemInit() throws Exception {

		Date b_date = new Date();
		ResultSet rs = null;
		String procdure = "{Call p_generate_bl_cell(?,?,?,?)}";
		CallableStatement cs = this.getHibernateTemplate().getSessionFactory()
				.openSession().connection().prepareCall(procdure);
		cs.setDate(1, new java.sql.Date(b_date.getTime()));
		cs.setInt(2, 0);
		cs.registerOutParameter(3, OracleTypes.INTEGER);
		cs.registerOutParameter(4, OracleTypes.VARCHAR);
		cs.execute();
		// System.out.println(cs.getInt(3)+cs.getString(4));
		if (cs.getInt(3) == 1) {
			return true;
		} else {

			return false;
		}

	}

	@SuppressWarnings("unchecked")
	public List<PcCdBlCellT> searchBoilerLineByName(String hql)
			throws Exception {
		return (List<PcCdBlCellT>) this.getHibernateTemplate().find(hql);
	}

	public List<String> getSAGDFP() throws Exception {
		List<String> rr = new ArrayList<String>();
		Date b_date = new Date();
		ResultSet rs = null;
		String procdure = "{Call p_handle_inject_steam(?,?,?)}";
		CallableStatement cs = this.getHibernateTemplate().getSessionFactory()
				.openSession().connection().prepareCall(procdure);
		cs.setDate(1, new java.sql.Date(b_date.getTime()));
		cs.registerOutParameter(2, OracleTypes.INTEGER);
		cs.registerOutParameter(3, OracleTypes.VARCHAR);
		cs.execute();
		// System.out.println(cs.getInt(3)+cs.getString(4));
		rr.add(String.valueOf(cs.getInt(2)));
		rr.add(cs.getString(3));

		return rr;
	}

	public boolean addBoilerLine(PcCdBlCellT blCell) throws Exception {
		try {
			this.getHibernateTemplate().save(blCell);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean removeLXs(List<String> sqls) throws Exception {
		SessionFactory sessionFactory = this.getSessionFactory();
		Session session = sessionFactory.openSession();

		Transaction tx = session.beginTransaction();
		tx.begin();
		for (int i = 0; i < sqls.size(); i++) {
			if (sqls.get(i) != null && !"".equals(sqls.get(i))
					&& !"null".equals(sqls.get(i))) {
				org.hibernate.Query query = session.createSQLQuery(sqls.get(i));
				query.executeUpdate();
			}
		}

		tx.commit();
		session.close();

		return true;
	}

}
