package com.echo.service;


import java.util.List;

import com.echo.dto.PcCdPortalMenuT;
import com.echo.dto.PcCdPortalParamT;
import com.echo.dto.User;

public interface PortalService {

	public List<PcCdPortalMenuT> searchPortal(String portalid,String portalname) throws Exception;
	public List<PcCdPortalMenuT> searchPortals(String sql) throws Exception;
	public List<Object[]> searchPortal(String sql) throws Exception;
	public boolean addPortal(PcCdPortalMenuT portal)throws Exception;
	public boolean removePortal(String[] portalIds)throws Exception;
	
	public boolean buthEquerSql(List<String> sqls)throws Exception;
	
	public boolean updatePortal(PcCdPortalMenuT portal) throws Exception;
	public String searchMenuname(String menuname);
	public boolean addOrUpdatePortal(PcCdPortalParamT param);
	public List<PcCdPortalParamT> searchPor(String sql);
	public boolean addOrUpdatePortalMenu(PcCdPortalMenuT menu);
	public String searchParamId(String nowid);
	
	public boolean removePortalParam(String paramID)throws Exception;
	public List<Object[]> searchParam(String sql)throws Exception;
	public String searchOpenMenuId(String id,User user)throws Exception;
	
	
	public boolean removeParams(String sqls)throws Exception;


}
