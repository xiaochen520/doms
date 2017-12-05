package com.echo.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.echo.dao.GwghjcxxDao;
import com.echo.dto.PcCdNetworkMdT;

public class GwghjcxxDaoImpl extends HibernateDaoSupport implements GwghjcxxDao{

	@Override
	public boolean saveOrUpdate(PcCdNetworkMdT net) throws Exception {
		boolean  flag = true ;
		this.getHibernateTemplate().merge(net);
		return flag;
	}

	@Override
	public boolean removeDatas(String[] sqls) {
		SessionFactory sessionFactory=this.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();  
		tx.begin();
		for(int i = 0;i < sqls.length; i++){
			org.hibernate.Query query  =  session.createSQLQuery(sqls[i]);
			query.executeUpdate();
		}
		tx.commit();
		session.close();

		return true;
	}

}
