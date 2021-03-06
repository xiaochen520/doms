package com.echo.service.impl;

 
import java.util.ArrayList;
import java.util.List;

import com.echo.dao.CommonDao;
import com.echo.dao.PublicDao;
import com.echo.dao.ReportMessageDao;
import com.echo.dto.PcRpdReportMessageT;
import com.echo.dto.PcRpdWaterMessageT;
import com.echo.service.ReportMessageService;

public class ReportMessageServiceImpl implements ReportMessageService{
	private ReportMessageDao reportMessageDao;
	
	private PublicDao publicDao;
	
	private CommonDao commonDao;
	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}


	public void setReportMessageDao(ReportMessageDao reportMessageDao) {
		this.reportMessageDao = reportMessageDao;
	}
	
	
	public PublicDao getPublicDao() {
		return publicDao;
	}



	public void setPublicDao(PublicDao publicDao) {
		this.publicDao = publicDao;
	}



	@Override
	public List<PcRpdReportMessageT> SreachReportMessages(String id,String bbmc,String rq) throws Exception {
		String hql = " FROM PcRpdReportMessageT r WHERE 1=1 ";
			if(id != null && !"".equals(id)){
				hql += " and r.rpdReportMessageId = '"+id+"'";
			}			
			if(bbmc != null && !"".equals(bbmc)){
				hql += " and r.bbmc = '"+bbmc+"'";
			}			
			if(rq != null && !"".equals(rq)){
				hql += " and r.rq = TO_DATE('"+rq+"','YYYY-MM-DD')";
			}			
		return reportMessageDao.SearchReportMessageTs(hql);
	}

	@Override
	public boolean updateReportMessages(PcRpdReportMessageT reportMessage)
			throws Exception {
		return reportMessageDao.updateReportMessages(reportMessage);
	}
	
	public List<Object[]> SreachReportMessages(String date, String reportName) throws Exception {
		List<Object[]> lo = publicDao.searchObjectList("select rm.tbr,rm.shr,rm.rq,rm.bz from Pc_Rpd_Report_Message_T rm where rm.bbmc='" + reportName + "'");
		return lo;
	}


	@Override
	public List<PcRpdWaterMessageT> SreachWaterMessageT(String MID,String BBMC, String txtDate) throws Exception {
		String hql = " FROM PcRpdWaterMessageT r WHERE 1=1 ";
		if(MID != null && !"".equals(MID)){
			hql += " and r.waterMessageId = '"+MID+"'";
		}
		if(BBMC != null && !"".equals(BBMC)){
			hql += " and r.bbmc like '"+BBMC+"'";
		}
		if(txtDate != null && !"".equals(txtDate)){
			hql += " and r.rq = TO_DATE('"+txtDate+"','YYYY-MM-DD')";
		}
		
		return reportMessageDao.SearchwATERMessageTs(hql);
	}


	@Override
	public boolean updateWaterMessage(PcRpdWaterMessageT rm) throws Exception {
		return reportMessageDao.updateWaterMessage(rm);
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<PcRpdWaterMessageT> SreachWaterMessages(String id, String bbmc,
			String rq) throws Exception {
		String hql = " FROM PcRpdWaterMessageT r WHERE 1=1 ";
		
		if(id != null && !"".equals(id)){
			hql += " and r.waterMessageId = '"+id+"'";
		}
		
		if(bbmc != null && !"".equals(bbmc)){
			hql += " and r.bbmc = '"+bbmc+"'";
		}
		
		if(rq != null && !"".equals(rq)){
			hql += " and r.rq = TO_DATE('"+rq+"','YYYY-MM-DD')";
		}
		return (List<PcRpdWaterMessageT>) commonDao.searchClassQuery(hql);
	}


	@Override
	public boolean updateWatertMessages(PcRpdWaterMessageT reportMessage)
			throws Exception {
		List<PcRpdWaterMessageT> list = new ArrayList<PcRpdWaterMessageT>();
		list.add(reportMessage);
		return commonDao.addOrUpdateDatas(list);
	}



}
