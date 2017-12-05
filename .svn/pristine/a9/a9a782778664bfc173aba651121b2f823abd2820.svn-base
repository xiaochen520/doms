package com.echo.dao.impl;

import java.sql.CallableStatement;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleTypes;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.echo.dao.Query;
import com.echo.dao.U1ZHRBDao;
import com.echo.dto.PcRpdCrudeTransitionT;
import com.echo.dto.PcRpdU1GeneralT;
import com.echo.util.DateBean;

public class U1ZHRBDaoImpl extends HibernateDaoSupport implements U1ZHRBDao{

	@Override
	public List<String> getProcedures(String pname, String rq)throws Exception {
		List<String> ls = new ArrayList<String>();
		String procdure = "{Call "+pname+"(?,?,?)}";  
        CallableStatement cs = this.getHibernateTemplate().getSessionFactory().openSession().connection().prepareCall(procdure);  
        cs.setDate(1,   new java.sql.Date(DateBean.getsqlDateTime(rq)));  
        cs.registerOutParameter(2, OracleTypes.INTEGER); 
        cs.registerOutParameter(3, OracleTypes.VARCHAR); 
        cs.execute();
        //System.out.println(cs.getInt(3)+cs.getString(4));
        ls.add(String.valueOf(cs.getInt(2)));
        ls.add(cs.getString(3));
        
        return ls;
	}

	@Override
	public List<Object[]> searchMain(String sql) {
		return Query.getSqlQuery(this.getHibernateTemplate(), sql);
	}

	@Override
	public boolean updateU1S(PcRpdU1GeneralT u1) throws Exception {
		boolean  flg = true ; 
		this.getHibernateTemplate().merge(u1);
		return flg;
	}

	@Override
	public List<PcRpdCrudeTransitionT> searchCrude(String hql) throws Exception {
		List<PcRpdCrudeTransitionT> cList = null;
		cList = this.getHibernateTemplate().find(hql);
		return cList;
	}

	@Override
	public boolean updateCrude(PcRpdCrudeTransitionT crude,PcRpdCrudeTransitionT crude0) throws Exception {
		boolean flg = true;
		if(crude.getGh() !=null && !"".equals(crude.getGh())){
			this.getHibernateTemplate().merge(crude);
		}
		if(crude0.getGh() !=null && !"".equals(crude0.getGh())){
			this.getHibernateTemplate().merge(crude0);
		}
		return flg;
	}

	@Override
	public List<PcRpdU1GeneralT> onAudit(String hql) throws Exception {
		 List<PcRpdU1GeneralT>  u1gOnList = null;
		 u1gOnList = this.getHibernateTemplate().find(hql);
		return u1gOnList;
	}

	@Override
	public boolean updateOnAudit(PcRpdU1GeneralT u1g) throws Exception {
		boolean flag = true; 
		this.getHibernateTemplate().merge(u1g);
		return flag;
	}

	@Override
	public List<Object[]> searchExportData(String sql) {
		return Query.getSqlQuery(this.getHibernateTemplate(), sql);
	}

}
