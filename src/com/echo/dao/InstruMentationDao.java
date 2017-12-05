package com.echo.dao;

import java.util.Date;
import java.util.List;

import com.echo.dto.PcCdInstruMentationT;

public interface InstruMentationDao {
	public List<Object[]> searchInstru(final String instruInfo,final int start, final int pagesize);//,final String[] cloumnsName
	public List<Object[]> queryInfo(String hql);
	public List<Object[]> searchCode(String sql) throws Exception;
	public boolean removeInstrumention(String sqls);
	public boolean SaveOrUpdateInstru(PcCdInstruMentationT im)throws Exception;
	public List<PcCdInstruMentationT> searchYbId(String hql)throws Exception;
	public List<Object[]> queryInstru(String sql) throws Exception;
	//public List<PcCdInstruNamecT> getFac(String sql) throws Exception;
	public List<String> getProcedures(String pName, String oilName,
			String objectCode, String ownObject, String staName,
			String areaName, String iNSTRUCLN, String iNSTRUSTNS,
			String superStns, String pointCode, String alamOr,String userid ,String MyDate)throws Exception;
	
	public List<String> getProceduresInsert(String pName, String iNSTRUMENTID,
			String editInstruGHID, String editInstruGHIDN,
			String editSuperName, String editObjectCode, String editInstruSBMC,
			String editInstruSJSB, String editInstruZT, String editRemark,
			String user, Date date)throws Exception;
}
