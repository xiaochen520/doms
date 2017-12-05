package com.echo.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.echo.util.DateBean;


/**
 * 执行Hql和Sql类
 * 
 * @author yanlong
 * 
 */
public class Query {
	
	private static final Integer resultList = null;
	@SuppressWarnings("unchecked")
	public static List<Object[]> getSqlQuery(HibernateTemplate hibernateTemplate,String sql){
		final String mysql = sql;
		List<Object[]> resultList = hibernateTemplate
		 .executeFind(new HibernateCallback() {

		public List<String> doInHibernate(Session session)
		 throws HibernateException, SQLException {

		SQLQuery sqlQuery = session.createSQLQuery(mysql);
		
		return sqlQuery.list();

		}

		});
		
		
		return resultList;
		
	}
	
	/**
	 *  调用p_generate_bl_cell生成指定的日期的炉线信息  存储过程
	 * @param hibernateTemplate
	 * @return
	 */
	public static List<Object[]> getGenerateblcellQuery(HibernateTemplate hibernateTemplate){
		//final String mysql = sql;
		//final String nowtime = sql;
		//final String param1 = "TO_DATE("+nowtime+",'YYYY-MM-DD')";
		List<Object[]> resultList = hibernateTemplate
		 .executeFind(new HibernateCallback() {

		public List<String> doInHibernate(Session session)
		 throws HibernateException, SQLException {

		//SQLQuery sqlQuery = session.createSQLQuery(mysql);
		SQLQuery query = session.createSQLQuery("{Call p_generate_bl_cell(?,?)}"); 
		query.setDate(0, new Date()); 
		query.setInteger(1, 0); 
		return query.list();

		}

		});
		
		
		return resultList;
		
	}
	
	@SuppressWarnings("unchecked")
	public static List<Object[]> getSqlQueryPage(HibernateTemplate hibernateTemplate,String sql, final int start,
			final int pagesize){
		final String mysql = sql;
		List<Object[]> resultList = hibernateTemplate
		 .executeFind(new HibernateCallback() {

		public List<String> doInHibernate(Session session)
		 throws HibernateException, SQLException {

		SQLQuery sqlQuery = session.createSQLQuery(mysql);
		sqlQuery.setFirstResult(start);
		sqlQuery.setMaxResults(pagesize);
		return sqlQuery.list();

		}

		});
		
		
		return resultList;
		
	}
	/**
	 * 执行sql 更新,删除操作，返回更新数量 
	 * @param hibernateTemplate
	 * @param sql 要执行更新的sql
	 * @param pl 更新sql中参数
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static int updateBySQL(HibernateTemplate hibernateTemplate,final String sql,final List pl) throws Exception { 
		Object resultList = 0;
		try {
			resultList = hibernateTemplate.execute(new HibernateCallback() {
				public Object doInHibernate(Session session) throws HibernateException, SQLException {
					SQLQuery query = session.createSQLQuery(sql);  
		
					 if(pl != null && !pl.isEmpty() && pl.size()>0) {  
			              for(int i = 0;i < pl.size();i++) {  
			                query.setParameter(i,pl.get(i));  
			             }  
			              return query.executeUpdate();  
			            } else{
			            	
			            	return query.executeUpdate(); 
			            }

				}
			});
		} catch (Exception e) {
			 e.printStackTrace();  
		      throw new Exception(e.getMessage());  
		}
		
		
		
		return (Integer)resultList;
		
	}
	/**
	 * 删除操作
	 * @param hibernateTemplate
	 * @param sql
	 * @param ids
	 * @throws Exception
	 */
	public static int bulkDelete(HibernateTemplate hibernateTemplate,final String sql) throws Exception {
		Object resultList = 0;
		try {
			resultList = hibernateTemplate.execute(new HibernateCallback() {
				public Object doInHibernate(Session session) throws HibernateException, SQLException {
				SQLQuery query = session.createSQLQuery(sql);
				return query.executeUpdate();
				}
				});
		} catch (Exception e) {
			e.printStackTrace();  
		      throw new Exception(e.getMessage());  
		}

		
		return (Integer) resultList;
		
		}
	
	

	

}
