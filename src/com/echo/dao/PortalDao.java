package com.echo.dao;

import java.util.List;

import com.echo.dto.PcCdPortalMenuT;
import com.echo.dto.PcCdPortalParamT;

public interface PortalDao {
	public List<PcCdPortalMenuT> searchPortal(String hql) throws Exception;
	
	public List<Object[]> searchPortals(String sql) throws  Exception;
	
	public boolean savePortal(PcCdPortalMenuT Portal)throws Exception;
	
	public boolean removePortal(String[] sqls)throws Exception;
	
	public boolean updatePortal(PcCdPortalMenuT Portal)throws Exception;


	public List<Object[]> searchMenuname(String sql);

	public boolean addOrUpdatePortal(PcCdPortalParamT param);

	public List<PcCdPortalParamT> searchPor(String sql);

	public boolean addOrUpdatePortalMenu(PcCdPortalMenuT menu);
	
	
	public boolean removePortalParam(String sqls)throws Exception;

	public List<Object[]> searchParam(String sql)throws Exception;

	
	
	
	

}