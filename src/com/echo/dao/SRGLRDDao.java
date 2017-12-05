package com.echo.dao;

import java.util.List;

import com.echo.dto.PcCdBoilerT;
import com.echo.dto.PcCdOrgT;
import com.echo.dto.PcRpdBoilerCommonT;
import com.echo.dto.PcRpdBoilerCommondT;
import com.echo.dto.PcRpdBoilerHighDryT;


public interface SRGLRDDao {
	
	public List<Object[]> searchData(final String sql, final int start,final int pagesize);
	public List<Object[]> searchData(final String sql,final String[] cloumnsName, final int start,final int pagesize);
	public List<Object[]> searchData(String searchThinOil);
	public List<Object[]> searchRBData(final String sql, final int start,final int pagesize,final  String[] cloumnsName);
	public List<Object[]> searchRBData(String searchThinOil);
	public int getCounts(String sql);
	public List<Object[]> queryOrdinaryHotBoiler(String sql) throws Exception;
	public List<Object[]> queryOildingName(String sql) throws Exception;
	public boolean removeBoilerCommondRP(String sql) throws Exception;
	public List<Object[]> searchCommobid(String sql)throws Exception;
	public boolean save(PcRpdBoilerCommonT psgl) throws Exception;
	public boolean update(PcRpdBoilerCommonT psgl) throws Exception;
	
	public List<PcRpdBoilerCommonT> searchBoilerCommonds(String sql) throws Exception;
	public List<Object[]> queryInfo(String Sql) throws Exception;
	public List<Object[]> queryBoilerid(String sql)throws Exception;
	
	public boolean addOrModifyBoilerOrgInfo(PcRpdBoilerHighDryT prbhd) throws Exception;
	public boolean removeGGDRDBoiler(PcRpdBoilerHighDryT prbhd) throws Exception;
	public PcRpdBoilerHighDryT queryGGDRDBoiler(String rpdBoilerHighDryId) throws Exception;
	public PcCdBoilerT searchBoiler(String boilerId) throws Exception;
	public List<Object[]> searchRBData1(String thinOilWellRD, int start,
			int rowsPerpage, String[] cloumnsName);
	public List<Object[]> searchData1(String thinOilWellRD);
	public List<Object[]> searchData1(String thinOilWellRD,
			String[] cloumnsNames, int start, int rowsPerpage);
}
