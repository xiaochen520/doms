package com.echo.service;


import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.dto.PcCdMenuT;
import com.echo.dto.User;



public interface SystemTreeService {
	
	/**
	 *  获取系统功能菜单数据
	 * @param 
	 * @return
	 * @throws Exception 
	 */
	public String searchMenu(User user) throws Exception;
	
	public JSONArray searchMenuNew(User user) throws Exception;
	
	public JSONArray searchPortalMenu(User user) throws Exception;
	
	public JSONArray searchChridMenu(User user,String menuid) throws Exception;
	
	public List<PcCdMenuT> searchMenu() throws Exception;
	
	public JSONArray searchStructure(String type);
	
	public JSONArray searchStructure()throws Exception;
	
	public JSONArray  menuTreeData(String sql)throws Exception;
	
	public JSONArray  searchFilter(String sql)throws Exception;
	
	public JSONArray searchOrgChrid(String sql)throws Exception;
	
	public JSONArray searchFilterOrg(String node)throws Exception;
	
	public JSONObject searchNodeInfo(String nodeid)throws Exception;
	
	public String searchLocalStructure(String node);
	
	public String searchQjLocalStructure(String node,String type);
	
//	public boolean addStructure(TbStructure ts)throws Exception;
	
	public Integer findTreeOrder(int type);
	
//	public TbStructure searchByStructureName(String name);
//	public TbStructure searchByStructureName(String name,int stype);
	
//	public TbStructure searchByStructureName(String name , String type);
//	public TbStructure searchByStructureid(String id);
	
	public JSONArray searchStructureJqz(String hql);
	
//	public boolean updateStructure(TbStructure ts);
	
	public List<String> getallwell(String node);
	public JSONArray searchPcdStructure();
	/**
	 * 
	 * @param flg 获取自动补全数据标识
	 *  0： 获取井口数据
	 *  1：获取井场数据
	 *  2：获取井口和井场数据（井场数据后默认补充井场字符）
	 * @return
	 */
	public JSONArray searchAutoCompleteData(String flg);
	
}
