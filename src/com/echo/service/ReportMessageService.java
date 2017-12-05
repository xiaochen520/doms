package com.echo.service;


import java.util.List;

import com.echo.dto.PcRpdReportMessageT;
import com.echo.dto.PcRpdWaterMessageT;

public interface ReportMessageService {
	
	
	public List<PcRpdReportMessageT> SreachReportMessages(String id,String bbmc,String rq)throws Exception;
	public List<Object[]> SreachReportMessages(String date, String reportName) throws Exception;
	public boolean updateReportMessages(PcRpdReportMessageT reportMessage)throws Exception;
	
	
	public List<PcRpdWaterMessageT> SreachWaterMessageT(String MID,String BBMC, String txtDate) throws Exception;
	public boolean updateWaterMessage(PcRpdWaterMessageT rm)throws Exception;
	
	
	public List<PcRpdWaterMessageT> SreachWaterMessages(String id,String bbmc,String rq)throws Exception;
	public boolean updateWatertMessages(PcRpdWaterMessageT reportMessage)throws Exception;



	

}