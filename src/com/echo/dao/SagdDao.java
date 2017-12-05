package com.echo.dao;

import java.util.List;

import com.echo.dto.PcCdServerNodeT;
import com.echo.dto.PcCdWellSagdT;
import com.echo.dto.PcRpdWellSagddT;

public interface SagdDao {
	
	public List<Object[]> searchSagdWell(final String sql, final int start,final int pagesize);
	
	public List<Object[]> searchSagdRDWell(final String sql, final int start,final int pagesize,List<String> cloumnsName)throws Exception;
	public int getCounts(String sql);
	public List<Object[]> searchSagdRPD(final String wellInfo,final int start, final int pagesize,final String[] cloumnsName);
	public boolean removeStationInfo(String[] arg);
	public List<Object[]> queryInfo(String hql);

	public List<PcCdWellSagdT> searchSagdname(String hql) throws Exception;

	public boolean save(PcCdWellSagdT sagd) throws Exception;
	
	public boolean updateSagd(PcCdWellSagdT sagd) throws Exception;
	
	public List<PcCdServerNodeT> searchServer(String hql) throws Exception;
	public boolean addOrUpdateSagddRPD(PcRpdWellSagddT prws) throws Exception;
	public PcRpdWellSagddT searchSagddRPD(String sagdprdid) throws Exception;
	public boolean removeSagddRPD(PcRpdWellSagddT prws) throws Exception;
	public List<PcRpdWellSagddT> searchSagrdByName(String hql) throws Exception;

	public List<Object[]> searchPmode(String sql)throws Exception;
	public List<Object[]> searchPstages(String sql)throws Exception;
	
	public List<String> dayreport(String date1) throws Exception;
	public List<Object[]> searchStructureNew(String hql);
	
	//sagd井管汇信息
	public List<Object[]> searchSagdghRD(final String sql, final int start,final int pagesize);
	public List<Object[]> searchSagdghRD(String searchThinOil);

	//sagd18-3#站信息
	public List<Object[]> searchSagd183(String searchsagd183);


	List<Object[]> searchSagd183(String sql, int start, int pagesize);

	public List<Object[]> searchSagdnow(String searchsagdnow);

	public List<Object[]> searchSagdnow(String searchsagdnow, int start,
			int rowsPerpage);

	public List<Object[]> searchSagdss(String boilersInfo, int start,
			int rowsPerpage, List<String> cloumnsName);

	public List<Object[]> searchGrglzb1(String searchgrglzb1);

	public List<Object[]> searchGrglzb1(String searchgrglzb1, int start,
			int rowsPerpage);

	public List<Object[]> searchEverySql(String product);

	public List<Object[]> serarchZqpqq(String product, int start,
			int rowsPerpage);

	public List<Object[]> serarchYcgx(String product, int start, int rowsPerpage);

	public List<Object[]> searchSagd1hrdt(String searchsagd1hrdt);

	public List<Object[]> searchSagd1hrdt(String searchsagd1hrdt, int start,
			int rowsPerpage);

	public List<Object[]> serarchFc160wf(String product, int start,
			int rowsPerpage);

	public List<Object[]> serarchSagdlng(String product, int start,
			int rowsPerpage);

	public List<Object[]> serarchSagd1qtjc(String product, int start,
			int rowsPerpage);



}
