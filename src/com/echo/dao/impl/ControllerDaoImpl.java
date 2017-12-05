package com.echo.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.echo.dao.ControllerDao;
import com.echo.dao.Query;
import com.echo.dto.PcCdControllerT;
import com.echo.dto.PcCdInstruMentationT;

public class ControllerDaoImpl extends HibernateDaoSupport implements ControllerDao,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3078060861794718243L;

	public void setSessionFactoryOverride(SessionFactory sessionFactory)
    {
      super.setSessionFactory(sessionFactory);
    }
	@SuppressWarnings("unchecked")
	public List<Object[]> searchControl(final String instruInfo,final int start, final int pagesize,final String[] cloumnsName) {
		List<Object[]> resultList = this.getHibernateTemplate()
		 .executeFind(new HibernateCallback() {
				String[] cloumnsName = {"CONTROLLERID","OILSTATIONNAME","STRUCTURENAME","OBJECT_TYPE_NAME","INSTRUMENTATION_TYPE","YBNAME","FACILITY_NAME","COMMUNICATION_TYPE","IP_ADDRESS","STATION_NO","COMMUNICATION_PORT","EQUIPMENT_STATUS","STATUS","RLAST_OPER","RLAST_ODATE","REMARK","INSTRUMENTATIONID","STRUCTURETYPE","ORG_ID"};
		public List<Object[]> doInHibernate(Session session)
		 throws HibernateException, SQLException {
				SQLQuery s = session.createSQLQuery(instruInfo);
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
	public List<Object[]> queryInfo(String hql) {
		return Query.getSqlQuery(this.getHibernateTemplate(), hql);
	}
	
	public boolean removeController(String sqls){
		SessionFactory sessionFactory=this.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();  
		tx.begin();
		//for(int i = 0;i < sqls.length; i++){
			org.hibernate.Query query  =  session.createSQLQuery(sqls);
			query.executeUpdate();
		//}
		tx.commit();
		session.close();
		return true;
	}
	public List<PcCdControllerT> searchControllerId(String hql) throws Exception {
		List<PcCdControllerT> imList=null;
		imList = (List<PcCdControllerT>)this.getHibernateTemplate().find(hql);
		return imList;
	}
	
	public boolean SaveOrUpdateControl(PcCdControllerT im) throws Exception {
//		try {
//			this.getHibernateTemplate().merge(im);
//			return true;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		boolean flag = true;
		this.getHibernateTemplate().merge(im);
		return flag;
//		return false;
	}
	@Override
	public List<Object[]> searchYBNameQuery(String sql) throws Exception {
			return Query.getSqlQuery(this.getHibernateTemplate(), sql);
	}
	
}