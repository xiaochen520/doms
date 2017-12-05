package com.echo.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.echo.dao.LogDao;
import com.echo.dao.Query;
import com.echo.dto.PcRdLoginfoT;


public class LogDaoImpl extends HibernateDaoSupport implements LogDao,Serializable{

	private static final long serialVersionUID = 9131285684828373428L;

	public boolean save(PcRdLoginfoT log)throws Exception {
		Serializable flg =  this.getHibernateTemplate().save(log);
		 if(flg != null && !"".equals(flg)){
			 return true;
		 }else{
			 return false;
		 }
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> seachLog(final String sql,final int start,final int pagesize) {
//		List<Object[]> executeFind = this.getHibernateTemplate()
//		 .executeFind(new HibernateCallback() {

//		public List<Object[]> doInHibernate(Session session)
//		 throws HibernateException, SQLException {
//			SQLQuery s = session.createSQLQuery(sql);
//	
//			 s.addScalar("LOGID", Hibernate.STRING);
//			 s.addScalar("USERID", Hibernate.STRING);
//			 s.addScalar("OPERATOR",Hibernate.STRING);
//			 s.addScalar("CATEGORY", Hibernate.BYTE);
//			 s.addScalar("TARGET", Hibernate.STRING);
//			 s.addScalar("MENUNAME", Hibernate.STRING);
//			 s.addScalar("INFOID", Hibernate.STRING);
//			 s.addScalar("LOGTIME", Hibernate.TIMESTAMP);
//			 s.addScalar("IPADRESS", Hibernate.STRING);
//			
//			//设置分页开始条数最大值
//			 s.setFirstResult(start);     
//			 s.setMaxResults(pagesize);  
//			return s.list();
//	
//			}
//	
//			});
//		List<Object[]> resultList = executeFind;
//		
//		return resultList;
			 
		List<Object[]> resultList = this.getHibernateTemplate()
		 .executeFind(new HibernateCallback() {
			 String[] cloumnsName = {"logid","rlast_oper","operating_class","operand","infoid","operation_time","ip_address","minformation_bef","minformation_aft"};
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

	public List<Object[]> seachUsername(String sql) {
			List<Object[]> usernames = null;
			usernames = Query.getSqlQuery(this.getHibernateTemplate(), sql);
			return usernames;

	}

	


}
