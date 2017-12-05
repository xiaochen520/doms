package com.echo.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.echo.dao.BoilerCrosslRDDao;
import com.echo.dao.Query;
import com.echo.dao.SRGLRDDao;
import com.echo.dao.ThinOilWellDao;
import com.echo.dao.ThinOilWellRDDao;
import com.echo.dao.WaterFLRDDao;
import com.echo.dao.WaterFloodingDao;
import com.echo.dto.PcCdThinoilWellT;



public class WaterFLRDDaoImpl extends HibernateDaoSupport implements WaterFLRDDao,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3078060861794718243L;



	public void setSessionFactoryOverride(SessionFactory sessionFactory)
    {
      super.setSessionFactory(sessionFactory);
    } 


	@SuppressWarnings("unchecked")
	public List<Object[]> searchData(final String boilersInfo,final int start, final int pagesize) {
		List<Object[]> resultList = this.getHibernateTemplate()
		 .executeFind(new HibernateCallback() {

			 String[] cloumnsName = {"OILSTATIONNAME","BLOCKSTATIONNAME","AREABLOCK","GH_ID","WATERINJECTIONTOPR","WATERFLOODING_WELLID","WATERFLOODING_WELLRID","QKID","JHS","CJSJ","BUS_PRES","PRES","INSTANTANEOUS_DELIVERY","CUMULATIVE_DISCHARGE"
			 };
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
	 * @param hibernateTemplate
	 * @param sql
	 * @return
	 */
	public int getCounts(String sql) {
		int count = 0;
		List<Object[]> list = new ArrayList<Object[]>();
		list = Query.getSqlQuery(this.getHibernateTemplate(), sql);
		count = Integer.parseInt(String.valueOf(list.get(0))) ;
		return count;
	}
	public List<Object[]> searchData(String searchThinOil) {
		List<Object[]> lo = Query.getSqlQuery(this.getHibernateTemplate(), searchThinOil);
		return lo;
	}
}
