package com.echo.dao.impl;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import oracle.jdbc.OracleTypes;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.echo.dao.InstruMentationDao;
import com.echo.dao.Query;
import com.echo.dto.PcCdBoilerT;
import com.echo.dto.PcCdInstruMentationT;
import com.echo.dto.PcCdOrgT;
import com.echo.util.DateBean;

public class InstruMentationDaoImpl extends HibernateDaoSupport implements InstruMentationDao,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3078060861794718243L;

	public void setSessionFactoryOverride(SessionFactory sessionFactory)
    {
      super.setSessionFactory(sessionFactory);
    }
	@SuppressWarnings("unchecked")//,final String[] cloumnsName
	public List<Object[]> searchInstru(final String instruInfo,final int start, final int pagesize) {
		List<Object[]> resultList = this.getHibernateTemplate()
		 .executeFind(new HibernateCallback() {
				String[] cloumnsName = {"INSTRUMENTATIONID","OILSTATIONNAME","OBJECT_CODE","OBJECT_TYPE_NAME","INSTRU_STVA","INSTRU_STNS","INSTRUMENTATION_NAME","INSTRUMENTATION_TYPE","INSTRU_NAM","INSTRU_NAC","PSJID","INSTRU_1TC","INSTRU_1TN","INSTRU_CLC","INSTRU_CLN","FACTORY_NF","RLAST_OPER","RLAST_ODATE","REMARK","ORG_ID","STRUCTURETYPE","STRUCTURENAME","INSTRU_NAMECID","FACTORY_UC"};
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
	
	public boolean removeInstrumention(String sqls){
		SessionFactory sessionFactory=this.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();  
		tx.begin();
	//	for(int i = 0;i < sqls.length; i++){
			org.hibernate.Query query  =  session.createSQLQuery(sqls);
			query.executeUpdate();
		//}
		tx.commit();
		session.close();
		return true;
	}
	
	public List<Object[]> queryInfo(String hql) {
		return Query.getSqlQuery(this.getHibernateTemplate(), hql);
	}
	public List<Object[]> searchCode(String sql) throws Exception {
		return Query.getSqlQuery(this.getHibernateTemplate(), sql);
	}
	public boolean SaveOrUpdateInstru(PcCdInstruMentationT im) throws Exception {
		try {
			this.getHibernateTemplate().merge(im);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public List<PcCdInstruMentationT> searchYbId(String hql) throws Exception {
		List<PcCdInstruMentationT> imList=null;
		imList = (List<PcCdInstruMentationT>)this.getHibernateTemplate().find(hql);
		return imList;
	}
	
	public List<Object[]> queryInstru(String sql) throws Exception {
		
		return Query.getSqlQuery(this.getHibernateTemplate(), sql);
	}
//	public List<PcCdInstruNamecT> getFac(String sql) throws Exception {
//		List<PcCdInstruNamecT>  insnameList = null;
//		insnameList = (List<PcCdInstruNamecT>  )this.getHibernateTemplate().find(sql);
//		return insnameList;
//	}
	@Override
	public List<String> getProcedures(String pName, String oilName,
			String objectCode, String ownObject, String staName,
			String areaName, String iNSTRUCLN, String iNSTRUSTNS,
			String superStns, String pointCode, String alamOr,String userid ,String MyDate)throws Exception {
		List<String> rr = new ArrayList<String>();
		Date b_date = new Date();
		ResultSet rs =null; 
		String procdure = "{Call "+pName+"(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";  
		            CallableStatement cs = this.getHibernateTemplate().getSessionFactory().openSession().connection().prepareCall(procdure);  
		            cs.setString(1,  oilName);  
		            cs.setString(2,  objectCode); 
		            cs.setString(3,  ownObject);  
		            cs.setString(4,  staName); 
		            cs.setString(5,  areaName);  
		            cs.setString(6,  iNSTRUCLN); 
		            cs.setString(7,  iNSTRUSTNS);  
		            cs.setString(8,  superStns); 
		            cs.setString(9,  pointCode);  
		            cs.setString(10,  alamOr); 
		            cs.registerOutParameter(11, OracleTypes.INTEGER); 
		            cs.setString(12,  userid);  
		            //cs.setDate(13, new java.sql.Date(DateBean.getsqlDateTime(MyDate))); 
		            cs.setString(13,  MyDate);  
		           // new java.sql.Date(DateBean.getsqlDateTime(date))
		            cs.registerOutParameter(14, OracleTypes.INTEGER); 
		            cs.registerOutParameter(15, OracleTypes.VARCHAR); 
		            cs.execute();
		            //System.out.println(cs.getInt(3)+cs.getString(4));
		            rr.add(String.valueOf(cs.getInt(14)));
		            rr.add(cs.getString(15));
		            
		      return rr;
	}
	@Override
	public List<String> getProceduresInsert(String pName, String iNSTRUMENTID,
			String editInstruGHID, String editInstruGHIDN,
			String editSuperName, String editObjectCode, String editInstruSBMC,
			String editInstruSJSB, String editInstruZT, String editRemark,
			String user, Date date) throws Exception {

		List<String> rr = new ArrayList<String>();
		Date b_date = new Date();
		ResultSet rs =null; 
		String procdure = "{Call "+pName+"(?,?,?,?,?,?,?,?,?,?,?)}";  
		            CallableStatement cs = this.getHibernateTemplate().getSessionFactory().openSession().connection().prepareCall(procdure);  
		            cs.setString(1,  iNSTRUMENTID);  
		            cs.setString(2,  editInstruGHID); 
		            cs.setString(3,  editInstruGHIDN);  
		            cs.setString(4,  editSuperName); 
		            cs.setString(5,  editObjectCode);  
		            cs.setString(6,  editInstruSBMC); 
		            cs.setString(7,  editInstruSJSB);  
		            cs.setString(8,  editInstruZT); 
		            cs.setString(9,  editRemark);  
		            cs.setString(10,  user); 
		            cs.setDate(11,  new java.sql.Date(DateBean.dateToLong(date)));  
		            cs.execute();
		            //System.out.println(cs.getInt(3)+cs.getString(4));
		           // rr.add(String.valueOf(cs.getInt(14)));
		           // rr.add(cs.getString(15));
		            
		      return rr;
	
	}
}