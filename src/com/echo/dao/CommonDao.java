package com.echo.dao;

import java.util.List;

import com.echo.dto.PcRpdUThinOilAutoT;
import com.echo.dto.PcRpdUThinOilGeneralT;

public interface CommonDao {

	public List<Object[]> searchEverySql(String sql);
	
	public int updateButhSql(String sql)throws Exception;
	
	public List<?> searchClassQuery(String hql);

	public boolean addOrUpdateDatas(List<?> t) throws Exception;
	
	public int getCounts(String sql)throws Exception;
	
	
	public boolean removeDatas(List<String> sqls)throws Exception;
	public boolean removeData(String sql)throws Exception;
	
	public List<Object[]> searchPaging(final String sql, final int start,final int pagesize,final String[] cloumnsName);

	public List<PcRpdUThinOilGeneralT> SreachOilGeneral(String hql) throws Exception;

	public boolean updateOilGeneral(PcRpdUThinOilGeneralT oil)  throws Exception;

	public List<PcRpdUThinOilAutoT> SreachAutp(String hql)throws Exception;

	public boolean updateAuto(PcRpdUThinOilAutoT auto)throws Exception;
	
	public List<String> getProcedures(String proceduresName,String date) throws Exception;

	public List<String> getProcedures(String proceduresName,String date,String param) throws Exception;
	
	
	public List<String> getProcedures(String proceduresName,String date,String param,int param1) throws Exception;

	public List<String> dayreport()throws Exception;

	public List<Object[]> queryInfo(String timeCalssql);

	public List<Object[]> searchRhqzs1(String sql, int start, int rowsPerpage) throws Exception;

	public List<Object[]> searchRhqzs1(String sql) throws Exception;

	public List<Object[]> searchU2fxcy(String searchsagd183) throws Exception;

	public List<Object[]> searchU2fxcy(String searchsagd183, int start,
			int rowsPerpage) throws Exception;
	
	
	
	
}
