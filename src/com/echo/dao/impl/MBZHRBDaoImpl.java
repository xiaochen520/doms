package com.echo.dao.impl;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import oracle.jdbc.OracleTypes;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.echo.dao.MBZHRBDao;
import com.echo.dao.Query;
import com.echo.dto.PcRpdCrudeTransitionT;
import com.echo.dto.PcRpdReportMessageT;
import com.echo.dto.PcRpdU1SagdAutoT;
import com.echo.dto.PcRpdU1SagdGeneralT;
import com.echo.dto.PcRpdU1SagdLiquidT;
import com.echo.dto.PcRpdU1SagdPivotalT;
import com.echo.dto.PcRpdU2GeneralT;

public class MBZHRBDaoImpl extends HibernateDaoSupport implements  MBZHRBDao,java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 页面分页是使用的查询
	 */
	
	/**
	 * 导出报表及不分页时是使用的查询
	 */
	@Override
	public List<Object[]> searchObjectList(String hql) throws Exception{
		// TODO Auto-generated method stub
		return Query.getSqlQuery(this.getHibernateTemplate(), hql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PcRpdCrudeTransitionT> searchCrudeTransition(String hql)
			throws Exception {
		List<PcRpdCrudeTransitionT> crudeTransitions = null;
		crudeTransitions = (List<PcRpdCrudeTransitionT>)this.getHibernateTemplate().find(hql);
		return crudeTransitions;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PcRpdU1SagdPivotalT> searchU1SagdPivotal(String hql) throws Exception {
		List<PcRpdU1SagdPivotalT> pivotals = null;
		pivotals = (List<PcRpdU1SagdPivotalT>)this.getHibernateTemplate().find(hql);
		return pivotals;
	}

	@Override
	public boolean updateZHRB(PcRpdReportMessageT reportMessage,
			PcRpdU2GeneralT u2General, PcRpdCrudeTransitionT crudeTransition0,
			PcRpdCrudeTransitionT crudeTransition1,
			PcRpdCrudeTransitionT crudeTransition2) throws Exception {
		boolean flg = true ;
		this.getHibernateTemplate().merge(reportMessage);
		this.getHibernateTemplate().merge(u2General);
//		if(crudeTransition0.getRpdCrudeTransitionId() != null && !"".equals(crudeTransition0.getRpdCrudeTransitionId()) &&
				if(	crudeTransition0.getGh() != null && !"".equals(crudeTransition0.getGh())){
			this.getHibernateTemplate().merge(crudeTransition0);
		}
//		if(crudeTransition1.getRpdCrudeTransitionId() != null && !"".equals(crudeTransition1.getRpdCrudeTransitionId()) &&
				if(	crudeTransition1.getGh() != null && !"".equals(crudeTransition1.getGh())){
			this.getHibernateTemplate().merge(crudeTransition1);
		}
//		if(crudeTransition2.getRpdCrudeTransitionId() != null && !"".equals(crudeTransition2.getRpdCrudeTransitionId()) &&
				if(	crudeTransition2.getGh() != null && !"".equals(crudeTransition2.getGh())){
			this.getHibernateTemplate().merge(crudeTransition2);
		}
		return flg;
	}

	@SuppressWarnings({ "unchecked", "deprecation", "rawtypes", "unused" })
	@Override
	public Map seachSystemInit(String rq) throws Exception {
		// TODO Auto-generated method stub
		Date b_date = new Date();
		Map map = new HashMap();
		ResultSet rs =null; 
		String procdure = "{Call p_gene_d_u1_sagd_auto(?,?,?)}";  
        CallableStatement cs = this.getHibernateTemplate().getSessionFactory().openSession().connection().prepareCall(procdure);  
        cs.setTimestamp(1, new java.sql.Timestamp(b_date.getTime()));  
        cs.registerOutParameter(2, OracleTypes.INTEGER); 
        cs.registerOutParameter(3, OracleTypes.VARCHAR); 
        cs.execute();
        if(cs.getInt(2)==0)
        	map.put(cs.getInt(2), "出错"); //cs.getString(3)
        
        procdure = "{Call p_gene_d_u1_sagd_g(?,?,?)}";  
        cs = this.getHibernateTemplate().getSessionFactory().openSession().connection().prepareCall(procdure);  
        cs.setTimestamp(1, new java.sql.Timestamp(b_date.getTime()));  
        cs.registerOutParameter(2, OracleTypes.INTEGER); 
        cs.registerOutParameter(3, OracleTypes.VARCHAR); 
        cs.execute();
        if(cs.getInt(2)==0)
        	map.put(cs.getInt(2), cs.getString(3));
        
        procdure = "{Call p_gene_d_u1_sagd_liquid(?,?,?)}";  
        cs = this.getHibernateTemplate().getSessionFactory().openSession().connection().prepareCall(procdure);  
        cs.setTimestamp(1, new java.sql.Timestamp(b_date.getTime()));  
        cs.registerOutParameter(2, OracleTypes.INTEGER); 
        cs.registerOutParameter(3, OracleTypes.VARCHAR); 
        cs.execute();
        if(cs.getInt(2)==0)
        	map.put(cs.getInt(2), cs.getString(3));
        
        procdure = "{Call p_gene_d_u1_sagd_pivotal(?,?,?)}";  
        cs = this.getHibernateTemplate().getSessionFactory().openSession().connection().prepareCall(procdure);  
        cs.setTimestamp(1, new java.sql.Timestamp(b_date.getTime()));  
        cs.registerOutParameter(2, OracleTypes.INTEGER); 
        cs.registerOutParameter(3, OracleTypes.VARCHAR); 
        cs.execute();
        if(cs.getInt(2)==0)
        	map.put(cs.getInt(2), cs.getString(3));
        
        return map;
	}
	
	@Override
	public List<Object[]> searchCalcNum(String sql) {
		// TODO Auto-generated method stub
		return Query.getSqlQuery(this.getHibernateTemplate(), sql);
	}

	@Override
	public boolean updatePivotal(PcRpdU1SagdPivotalT pivotalRec)
			throws Exception {
		// TODO Auto-generated method stub
		boolean flg = true ;
		this.getHibernateTemplate().merge(pivotalRec);
		return flg;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PcRpdU1SagdAutoT> searchU1SagdAuto(String hql) throws Exception {
		// TODO Auto-generated method stub
		List<PcRpdU1SagdAutoT> autos = null;
		autos = (List<PcRpdU1SagdAutoT>)this.getHibernateTemplate().find(hql);
		return autos;
	}

	@Override
	public boolean updateAuto(PcRpdU1SagdAutoT autoRec) throws Exception {
		// TODO Auto-generated method stub
		boolean flg = true ;
		this.getHibernateTemplate().merge(autoRec);
		return flg;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PcRpdU1SagdGeneralT> SreachGeneral(String hql) throws Exception {
		// TODO Auto-generated method stub
		List<PcRpdU1SagdGeneralT> gens = null;
		gens = (List<PcRpdU1SagdGeneralT>)this.getHibernateTemplate().find(hql);
		return gens;
	}

	@Override
	public boolean updateGen(PcRpdU1SagdGeneralT sagdGen) throws Exception {
		// TODO Auto-generated method stub
		boolean flg = true ;
		this.getHibernateTemplate().merge(sagdGen);
		return flg;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PcRpdU1SagdLiquidT> SreachLiuqid(String hql) throws Exception {
		// TODO Auto-generated method stub
		List<PcRpdU1SagdLiquidT> liqs = null;
		liqs = (List<PcRpdU1SagdLiquidT>)this.getHibernateTemplate().find(hql);
		return liqs;
	}

	@Override
	public boolean updateLiq(PcRpdU1SagdLiquidT sagdliq) throws Exception {
		// TODO Auto-generated method stub
		boolean flg = true ;
		this.getHibernateTemplate().merge(sagdliq);
		return flg;
	}
}