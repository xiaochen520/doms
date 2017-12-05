package com.echo.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.echo.dao.GasDailyWHDao;
import com.echo.dao.Query;
import com.echo.dao.ThinWellRPDWHDao;
import com.echo.dto.PcRpdGasDailyT;
import com.echo.dto.PcRpdGasDailybT;
import com.echo.dto.PcRpdThinWellwT;
import com.echo.dto.PcRpdThinWellwbT;

	public class GasDailyWHDaoImpl extends HibernateDaoSupport implements GasDailyWHDao{
	
		public List<Object[]> searchData(final String thinOilWellRD, final int start,
				final int rowsPerpage, final String[] cloumnsName) {
			List<Object[]> lo = this.getHibernateTemplate().executeFind(new HibernateCallback() {
				
				public Object doInHibernate(Session session) throws HibernateException,
						SQLException {
					SQLQuery s = session.createSQLQuery(thinOilWellRD);
					s.setFirstResult(start);
					s.setMaxResults(rowsPerpage);
					return s.list();
				}
			});
			return lo;
		}

		public List<Object[]> searchDataEX(String thinOilWellRD) {
			return Query.getSqlQuery(this.getHibernateTemplate(), thinOilWellRD);
		}

		public boolean saveRPD(PcRpdGasDailyT rpdT) throws Exception {
			boolean flag = true;
			this.getHibernateTemplate().merge(rpdT);
			return flag;
		}

		public List<Object[]> searchWellID(String sql) {
			return Query.getSqlQuery(this.getHibernateTemplate(), sql);
		}

		public boolean saveRPDB(PcRpdGasDailybT rpdT) throws Exception {
			boolean flag = true;
			this.getHibernateTemplate().merge(rpdT);
			return flag;
		}

}
