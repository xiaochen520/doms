package com.echo.dao.impl;


import java.io.Serializable;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.echo.dao.Query;
import com.echo.dao.UserDao;
import com.echo.dto.PcCdUserT;
import com.echo.dto.User;
import com.echo.util.DateBean;


public class UserDaoImpl extends HibernateDaoSupport implements UserDao,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3078060861794718243L;

	public void setSessionFactoryOverride(SessionFactory sessionFactory)
    {
      super.setSessionFactory(sessionFactory);
    } 
	/**
	 * 添加user
	 */
	public boolean save(PcCdUserT user) throws Exception{
		 Serializable flg =  this.getHibernateTemplate().save(user);
		 if(flg != null && !"".equals(flg)){
			 return true;
		 }else{
			 return false;
		 }
		
	
	}
	
//	public boolean updateUser(TbUser user) {
//		boolean flg = true;
//		try {
//			this.getHibernateTemplate().saveOrUpdate(user);
//			SessionFactory sessionFactory=this.getSessionFactory();
//			Session session = sessionFactory.openSession();
//			
//			Transaction tx = session.beginTransaction();  
//			session.saveOrUpdate(user);
//			tx.commit();
//			session.close();
////			
//		} catch (Exception e) {
//			flg = false;
//		}
//		
//		 return flg;
//	}
	
	public boolean updateUser(PcCdUserT user) throws Exception{
		boolean flg = true;
		 this.getHibernateTemplate().merge(user);
		 return flg;
	}
	
	@SuppressWarnings("unchecked")
	public List<PcCdUserT> searchUser(String hql) throws Exception{
		List<PcCdUserT> userList = null;
		
		userList = (List<PcCdUserT>)this.getHibernateTemplate().find(hql);
		
		return userList;
	}
	
	@SuppressWarnings("unchecked")
	public List<User> searchUserByName(String hql) {
		List<User> userList = null;
		userList = (List<User>)this.getHibernateTemplate().find(hql);
		return userList;
	}
	
//	@SuppressWarnings("unchecked")
//	public List<Object[]> searchUserName(String hql) {
//		List<Object[]> userList = null;
//		userList = Query.getSqlQuery1(this.getHibernateTemplate(), hql);
//		return userList;
//	}
	
	public List<Object[]> searchUserName(final String hql)throws Exception {
		
		List<Object[]> userList = null;
		userList = Query.getSqlQuery(this.getHibernateTemplate(), hql);
		return userList;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> searchRight(String username, String password, final String hql) throws HibernateException, SQLException {
//		List<Object[]> rlist = null;
//		rlist = this.getHibernateTemplate().find(hql, new Object[]{username,password});
//		SQLQuery s = session.createSQLQuery(hql);
//		return rlist;
		

		
		List<Object[]> resultList = this.getHibernateTemplate()
		 .executeFind(new HibernateCallback() {

		public List<Object[]> doInHibernate(Session session)
		 throws HibernateException, SQLException {
			SQLQuery s = session.createSQLQuery(hql);
	
//			 s.addScalar("userid", Hibernate.STRING);
//			 s.addScalar("rolename", Hibernate.STRING);
//			 s.addScalar("pcdname",Hibernate.STRING);
//			 s.addScalar("STRUCTURENAME", Hibernate.STRING);
//			 s.addScalar("username", Hibernate.STRING);
//			 s.addScalar("REALNAME", Hibernate.STRING);
//			 s.addScalar("PASSWORD", Hibernate.STRING);
//			 s.addScalar("SEX", Hibernate.BYTE);
//			 s.addScalar("JoinedDate", Hibernate.TIMESTAMP);
//			 s.addScalar("RegisterTime", Hibernate.TIMESTAMP);
//			 
//			 s.addScalar("LastLoginTime", Hibernate.TIMESTAMP);
//			 s.addScalar("REMAINUSER", Hibernate.BYTE);
//			 s.addScalar("ENABLED", Hibernate.BYTE);
//			 s.addScalar("Bz", Hibernate.STRING);
			
			return s.list();
	
			}
	
			});
		
		return resultList;
	
		
		
		
	}
	
	public boolean removeUser(String[] sqls) throws Exception{
//		user = (PcCdUserT)this.getHibernateTemplate().load(PcCdUserT.class,userId);
//		if(user != null && user.getUserid() != null && !"".equals(user.getUserid())){
//			this.getHibernateTemplate().delete(user);
//		}
		
//		List<PcCdUserT> list   =   this.getHibernateTemplate().find("from PcCdUserT u where 1=1 and u.userid ='"+userId+"'");
//		
//		if(list != null && list.size()>0){
//			
//			this.getHibernateTemplate().delete(list);
//		}
		
		
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
	
	
//	@SuppressWarnings("unchecked")
	public List<Object[]> searchRole(String hql) throws Exception{
		List<Object[]> roleList = null;
		
		roleList = Query.getSqlQuery(this.getHibernateTemplate(), hql);
		
		return roleList;
	}
	
	public List<Object[]> searchDep(String hql) throws Exception{
		List<Object[]> depList = null;
		
		depList = Query.getSqlQuery(this.getHibernateTemplate(), hql);
		
		return depList;
	}
	
//	public List<TbStructure> searchStructure(String hql) {
//		List<TbStructure> menuList = null;
//		menuList = (List<TbStructure>)this.getHibernateTemplate().find(hql);
//		return menuList;
//	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> searchUserNames(final String sql, final int start,
			final int pagesize) throws Exception{
		List<Object[]> resultList = this.getHibernateTemplate()
		 .executeFind(new HibernateCallback() {
			 String[] cloumnsName = {"userid","departmentid","oper_code","oper_name","oper_pass","oper_sdbsadbse","audbshor_date","sdbsadbse_date","l_login_date","l_logoudbs_date","rlast_oper","rlast_odate","remark","roleid","rolename","departmentname"};
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
	@SuppressWarnings("unused")
	public boolean updateUserLastLogin(String userid,int flg)throws Exception{
		String sql = "  update pc_cd_user_t u set u.";
		Date nowdate = new Date();
		//登录
		if(flg == 1){
			sql	+=	"l_login_date =  TO_DATE('"+DateBean.getSystemTime()+"','YYYY-MM-DD HH24:MI:SS') ";
			
		//退出
		}else{
			sql	+=	"l_logoudbs_date =  TO_DATE('"+DateBean.getSystemTime()+"','YYYY-MM-DD HH24:MI:SS') ";
		}
		
		sql += "where 1=1 and  u.userid='"+userid+"'";
		SessionFactory sessionFactory=this.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		Transaction tx = session.beginTransaction();  
		tx.begin();
		org.hibernate.Query query  =  session.createSQLQuery(sql);
		query.executeUpdate();
		
		tx.commit();
		session.close();

		return true;
	}



}
