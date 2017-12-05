package com.echo.dao;

import java.util.List;

import com.echo.dto.PcRpdBoilerSuperheatT;

public interface BoilerSuperheatRDDao {
	
	public List<Object[]> searchData(final String sql, final int start,final int pagesize);
	public List<Object[]> searchRPDData(final String sql, final int start,final int pagesize,final String[] cloumnsName);
	public List<Object[]> searchData(String searchThinOil);
	public int getCounts(String sql);
	public PcRpdBoilerSuperheatT searchBoilerCrpssdRPD(String sagdprdid) throws Exception;
	public boolean removeBoilerCrpssdRPD(PcRpdBoilerSuperheatT prbc) throws Exception;
	public List<PcRpdBoilerSuperheatT> searchCross(String hql)throws Exception ;
	public boolean save(PcRpdBoilerSuperheatT psc)throws Exception;
	public boolean update(PcRpdBoilerSuperheatT psc)throws Exception;
	public List<Object[]> searchData1(String thinOilWellRD, int start,
			int rowsPerpage);
	public List<Object[]> searchData1(String thinOilWellRD);
	public List<Object[]> queryInfo(String hql);
}
