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

import com.echo.dao.DepartmentDao;
import com.echo.dto.PcCdDepartmentT;


public class DepartmentDaoImpl extends HibernateDaoSupport implements DepartmentDao,Serializable{

	private static final long serialVersionUID = -3064266231401134620L;
	public void setSessionFactoryOverride(SessionFactory sessionFactory)
    {
      super.setSessionFactory(sessionFactory);
    } 
	
	@SuppressWarnings("unchecked")
	public List<PcCdDepartmentT> searchDep(String hql) throws Exception{
		List<PcCdDepartmentT> depList = null;
		depList = (List<PcCdDepartmentT>)this.getHibernateTemplate().find(hql);
		return depList;
	}

	public List<Object[]> searchDeps(final String sql, final int start, final int pagesize)
			throws Exception {
		
		List<Object[]> resultList = this.getHibernateTemplate()
		 .executeFind(new HibernateCallback() {
			 String[] cloumnsName = {"departmentid","department_name","department_header","department_address","department_phone","rlast_odate","rlast_oper","remark"};
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
		Serializable flg =  this.getHibernateTemplate().save(dep);
		 if(flg != null && !"".equals(flg)){
			 return true;
		 }else{
			 return false;
		 }
		
	}

	public boolean updateDep(PcCdDepartmentT dep) throws Exception {
		boolean flg = true;
		 this.getHibernateTemplate().merge(dep);
		return flg;
	}

	public boolean removeDep(String[] sqls) throws Exception {
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
