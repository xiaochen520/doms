package com.echo.dao.impl;

import com.echo.dao.PublicDao;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.echo.dao.Query;
import com.echo.dto.PcRpdCrudeTransitionT;
import com.echo.dto.PcRpdReportMessageT;
import com.echo.dto.PcRpdU2GeneralT;

public class PublicDaoImpl extends HibernateDaoSupport implements  PublicDao,java.io.Serializable{

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

	@Override
	public List<PcRpdCrudeTransitionT> searchCrudeTransition(String hql)
			throws Exception {
		List<PcRpdCrudeTransitionT> crudeTransitions = null;
		crudeTransitions = (List<PcRpdCrudeTransitionT>)this.getHibernateTemplate().find(hql);
		return crudeTransitions;
	}

	@Override
	public List<PcRpdU2GeneralT> searchU2General(String hql) throws Exception {
		List<PcRpdU2GeneralT> U2Generals = null;
		U2Generals = (List<PcRpdU2GeneralT>)this.getHibernateTemplate().find(hql);
		return U2Generals;
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

}
