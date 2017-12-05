package com.echo.service.impl;

 
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.echo.dao.CommonDao;
import com.echo.dao.PortalDao;
import com.echo.dto.PcCdPortalMenuT;
import com.echo.dto.PcCdPortalParamT;
import com.echo.dto.User;
import com.echo.service.PortalService;

public class PortalServiceImpl implements PortalService{
	
	private PortalDao portalDao;
	private CommonDao commonDao;

	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}

	public void setPortalDao(PortalDao portalDao) {
		this.portalDao = portalDao;
	}

	public boolean addPortal(PcCdPortalMenuT portal) throws Exception{
		List<PcCdPortalMenuT> list = new ArrayList<PcCdPortalMenuT>();
		list.add(portal);
		return commonDao.addOrUpdateDatas(list);
	}

	public boolean removePortal(String[] portalIds) throws Exception{
		
		String hql = "";
		//String[] sqls = new String[portalIds.length];
		List<String> sqls = new ArrayList<String>();
		for(int i = 0 ; i <portalIds.length ; i ++){
			hql = "delete from PC_CD_PORTAL_PARAM_T   where PORTALMENUID = '"+portalIds[i]+"'"; 
			sqls.add(hql);
			hql = "DELETE from pc_cd_portal_menu_t r WHERE 1=1 and r.portalmenuid='"+portalIds[i]+"'";
			sqls.add(hql);
		}
		
		return commonDao.removeDatas(sqls);
	}

	public boolean updatePortal(PcCdPortalMenuT portal) throws Exception{
		return portalDao.updatePortal(portal);
	}



	public List<PcCdPortalMenuT> searchPortal(String portalid,String portalname) throws Exception{
		String hql = "FROM PcCdPortalMenuT R WHERE 1=1 ";
		
		if(portalid != null && !"".equals(portalid)){
			hql += "AND R.portalmenuid = '"+portalid+"'";
		}
		
		if(portalname != null && !"".equals(portalname)){
			
			hql += "AND R.portalmenuName = '"+portalname+"'";
		}
		
		List<PcCdPortalMenuT> portalList = portalDao.searchPortal(hql);
		
		return portalList;
	}
	
	public List<Object[]> searchPortal(String sql) throws Exception{
		
		List<Object[]> portalList = portalDao.searchPortals(sql);
		
		return portalList;
	}

	public List<PcCdPortalMenuT> searchPortals(String sql) throws Exception {
		List<PcCdPortalMenuT> portalList = portalDao.searchPortal(sql);
		return portalList;
	}

	@Override
	public String searchMenuname(String menuname) {
		String  sql = "select  a.portalmenuid from  PC_CD_PORTAL_MENU_T a   where a.portalmenu_name = '"+menuname+"' ";
		List<Object[]> objList = new ArrayList<Object[]>();
		objList = portalDao.searchMenuname(sql);
		Object menuID =objList.get(0);
		return (String)menuID;
	}

	@Override
	public boolean addOrUpdatePortal(PcCdPortalParamT param) {
	
		return portalDao.addOrUpdatePortal(param);
	}

	@Override
	public List<PcCdPortalParamT> searchPor(String sql) {
		List<PcCdPortalParamT> portalList = portalDao.searchPor(sql);
		return portalList;
	}

	@Override
	public boolean addOrUpdatePortalMenu(PcCdPortalMenuT menu) {
		return portalDao.addOrUpdatePortalMenu(menu);
	}

	@Override
	public String searchParamId(String nowid) {
		List<Object[]> objList = new ArrayList<Object[]>();
		String sql = "select a.portalmenuid  from  pc_cd_portal_param_t a where a.portalmenuid='"+nowid+"'";
		objList =portalDao.searchMenuname(sql);
		Object ParamId = objList.get(0);
		return (String) ParamId;
	}

	@Override
	public boolean removePortalParam(String paramID) throws Exception {
		String sql = "DELETE from pc_cd_portal_param_t r WHERE 1=1 and r.portalmenuid='"+paramID+"'";
		return portalDao.removePortalParam(sql);
	}

	@Override
	public List<Object[]> searchParam(String sql) throws Exception {
		return portalDao.searchParam(sql);
	}

	@Override
	public String searchOpenMenuId(String id,User user) throws Exception {
		String url = "";
		String sql ="select p.paramname,p.paramvalue,m.PORTALMENU_URL from pc_cd_portal_menu_t m left join pc_cd_portal_param_t p on m.portalmenuid = p.portalmenuid where m.portalmenuid = '"+id+"'";
		
		List<Object[]> openList =  commonDao.searchEverySql(sql);
		
		if(openList !=null && openList.size()>0){
			if(openList.get(0)[2] != null && !"".equals(openList.get(0)[2])){
				url = openList.get(0)[2].toString();
				String useradrr = user.getIp();
				if(useradrr.indexOf("192.168") != -1){
					useradrr = "192.168.19.7";
				}else{
					useradrr = "10.72.199.242";
				}
				
				
				if(url.indexOf("192.168") != -1){
					url = url.replace("192.168.19.7", useradrr);
				}else{
					url = url.replace("10.72.199.242", useradrr);
				}
				
//				Arr = new JSONArray();
				for (int i =0; i<openList.size();i++) {
					if(i == 0){
						if(openList.get(i)[0] != null && !"".equals(openList.get(i)[0])){
							url += "?"+openList.get(i)[0].toString()+"="+openList.get(i)[1].toString();
						}
						
					}else{
						if(openList.get(i)[0] != null && !"".equals(openList.get(i)[0])){
							url += "&"+openList.get(i)[0].toString()+"="+openList.get(i)[1].toString();
						}
					}
					
				}
			}
			
		}
		return url;
	}

	@Override
	public boolean removeParams(String sqls) throws Exception {
		return commonDao.removeData(sqls);
	}

	@Override
	public boolean buthEquerSql(List<String> sqls) throws Exception {
		return commonDao.removeDatas(sqls);
	}

}
