package com.echo.dao;

import java.util.List;

import com.echo.dto.PcRpdReportMessageT;
import com.echo.dto.PcRpdWaterMessageT;

public interface ReportMessageDao {

	
	public List<PcRpdReportMessageT> SearchReportMessageTs(String hql);
	
	public boolean updateReportMessages(PcRpdReportMessageT reportMessage);

	public List<PcRpdWaterMessageT> SearchwATERMessageTs(String hql)throws Exception;

	public boolean updateWaterMessage(PcRpdWaterMessageT rm)throws Exception;
	
}
