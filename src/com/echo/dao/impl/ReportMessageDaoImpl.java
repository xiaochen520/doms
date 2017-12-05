package com.echo.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.echo.dao.ReportMessageDao;
import com.echo.dto.PcRpdReportMessageT;
import com.echo.dto.PcRpdWaterMessageT;


public class ReportMessageDaoImpl extends HibernateDaoSupport implements ReportMessageDao,Serializable{

	
	private static final long serialVersionUID = 1L;
	

	public List<PcRpdReportMessageT> SearchReportMessageTs(String hql) {
		List<PcRpdReportMessageT> reportMessages = null;
		reportMessages = (List<PcRpdReportMessageT>)this.getHibernateTemplate().find(hql);
		return reportMessages;
	}

	@Override
	public boolean updateReportMessages(PcRpdReportMessageT reportMessage) {
		boolean flg = true ;
		this.getHibernateTemplate().merge(reportMessage);
		return flg;
	}


	public List<PcRpdWaterMessageT> SearchwATERMessageTs(String hql)throws Exception {
		List<PcRpdWaterMessageT> waterMessage = null;
		waterMessage = (List<PcRpdWaterMessageT>)this.getHibernateTemplate().find(hql);
		return waterMessage;
	}

	@Override
	public boolean updateWaterMessage(PcRpdWaterMessageT rm) throws Exception {
		boolean flag = true;
		this.getHibernateTemplate().merge(rm);
		return false;
	}
	
}
