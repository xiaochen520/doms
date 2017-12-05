package com.echo.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.echo.dao.FunctionMenuDao;
import com.echo.dao.Query;
import com.echo.dto.PcCdMenuT;


public class FunctionMenuDaoImpl extends HibernateDaoSupport implements FunctionMenuDao,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3064266231401134620L;
	public void setSessionFactoryOverride(SessionFactory sessionFactory)
    {
      super.setSessionFactory(sessionFactory);
    } 
	
	@SuppressWarnings("unchecked")
	public List<PcCdMenuT> searchMenu(String hql) throws Exception{
		List<PcCdMenuT> menuList = null;
		menuList = (List<PcCdMenuT>)this.getHibernateTemplate().find(hql);
		return menuList;
	}

//	public List<TbStructure> searchStructure(String hql) {
//		List<TbStructure> menuList = null;
//		menuList = (List<TbStructure>)this.getHibernateTemplate().find(hql);
//		return menuList;
//	}
	
	public List<Object[]> searchStructureNew(String hql) throws Exception{
		List<Object[]> menuList = null;
		menuList = Query.getSqlQuery(this.getHibernateTemplate(), hql);
		return menuList;
	}

//	public boolean addStructure(TbStructure ts) throws Exception{
//		// TODO Auto-generated method stub
//			this.getHibernateTemplate().save(ts);
//	
//		return true;
//	}
//	public boolean updateStructure(TbStructure ts) {
//		// TODO Auto-generated method stub
//		try{
//			this.getHibernateTemplate().update(ts);
//		}catch(Exception ee){
//			ee.printStackTrace();
//			return false;
//		}
//		return true;
//	}

	public Integer findTreeOrder(int type) {
		// TODO Auto-generated method stub
		Integer m=-9999;
		Byte type1=(byte)type;
		
		String hql=" select  max (p.treeorder) FROM TbStructure p where p.structuretype="+type1;
		List<Object> menuList = null;
		menuList=(List<Object>)this.getHibernateTemplate().find(hql);
		if(menuList.size()>0){
			Object ts=menuList.get(0);
			m=Integer.parseInt(ts.toString());
			if(type==5){
				m=m+100000;
			}if(type==6){
				m=m+1000;
			}
			
		}
		return m;
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> searchALLStructureid(final String hql) {
		
		List<Object[]> resultList = this.getHibernateTemplate()
		 .executeFind(new HibernateCallback() {

		public List<Object[]> doInHibernate(Session session)
		 throws HibernateException, SQLException {
			SQLQuery s = session.createSQLQuery(hql);
	
			 s.addScalar("qjid", Hibernate.STRING);
			 s.addScalar("qjname", Hibernate.STRING);
			 s.addScalar("jcid",Hibernate.STRING);
			 s.addScalar("jcname", Hibernate.STRING);
			 s.addScalar("jqzid", Hibernate.STRING);
			 s.addScalar("jczname", Hibernate.STRING);
			 s.addScalar("gsid", Hibernate.STRING);
			 s.addScalar("gsname", Hibernate.STRING);
			
			return s.list();
	
			}
	
			});
		
		return resultList;
	}

	public int getCounts(String sql) {
		int count = 0;
		List<Object[]> list = new ArrayList<Object[]>();
		list = Query.getSqlQuery(this.getHibernateTemplate(), sql);
		count = Integer.parseInt(String.valueOf(list.get(0))) ;
		return count;
	}

	


}
