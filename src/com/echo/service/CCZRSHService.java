package com.echo.service;


import java.util.List;

public interface CCZRSHService {
	
	public List<Object[]> searchCCDatas(String stautsid,String txtDate,String deptype,String depname);
	
	public List<Object[]> searchZRDatas(String stautsid,String txtDate,String deptype,String depname);
	
	public int onAudit(String sql)throws Exception;
	
	public List<String> onAuditAfter(String proceduresName, String date, String param)throws Exception;
	

}
