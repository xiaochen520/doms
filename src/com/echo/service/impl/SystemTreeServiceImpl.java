package com.echo.service.impl;

 
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.dao.CommonDao;
import com.echo.dao.FunctionMenuDao;
import com.echo.dto.PcCdMenuT;
import com.echo.dto.PcCdPortalMenuT;
import com.echo.dto.User;
import com.echo.service.SystemTreeService;
import com.echo.util.AuthorityUtil;

public class SystemTreeServiceImpl implements SystemTreeService{
	private FunctionMenuDao functionMenuDao;
	private CommonDao commonDao;

	
	
	public void setFunctionMenuDao(FunctionMenuDao functionMenuDao) {
		this.functionMenuDao = functionMenuDao;
	}


	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}


	public String searchMenu(User user) throws Exception{
		
		String hql = "FROM PcCdMenuT menu where menu.enabled = 0 order by menu.menuOrder";
		List<PcCdMenuT> menuList = null;
		menuList = functionMenuDao.searchMenu(hql);
		
		//根据用户权限赛选菜单数据
		menuList = AuthorityUtil.MenuAuthority(user, menuList);
		JSONArray jsonStr =  null;
		List<PcCdMenuT> pidlist = new ArrayList<PcCdMenuT>();
		if(menuList != null && menuList.size()>0){
			JSONArray jsonArr =  null;
			JSONObject tjo = null;
			
			//获取所以父菜单
			for(PcCdMenuT entry  : menuList){
				if(entry.getFatherMenuCode() == null || "".equals(entry.getFatherMenuCode())){
					pidlist.add(entry);
				}
				
			}
			
			if(pidlist.size()>0){
				jsonStr = new JSONArray();
				for(PcCdMenuT pmenu  : pidlist){
					jsonArr = new JSONArray();
					
					for(PcCdMenuT entry  : menuList){
						if(!pmenu.getMenuid().equals(entry.getMenuid()) && pmenu.getMenuid().equals(entry.getFatherMenuCode())){
							tjo = new JSONObject();
							tjo.put("url", entry.getMenuUrl());
							tjo.put("text", entry.getRightName());
							tjo.put("menuid", entry.getMenuid());
							jsonArr.add(tjo);
						}
					}
					tjo = new JSONObject();
					tjo.put("text", pmenu.getRightName());
					tjo.put("isexpand", false);
					tjo.put("children", jsonArr);
					jsonStr.add(tjo);
				}
			}
		}
		
		return jsonStr.toString();
	}
	
	@Override
	public JSONArray searchMenuNew(User user) throws Exception {

		
		String hql = "FROM PcCdMenuT menu where menu.enabled = 0 and menu.fatherMenuCode='MENU000' order by menu.menuOrder";
		List<PcCdMenuT> menuList = null;
		menuList = functionMenuDao.searchMenu(hql);
		
		//根据用户权限赛选菜单数据
		menuList = AuthorityUtil.MenuAuthority(user, menuList);
		JSONArray jsonStr =  null;
		List<PcCdMenuT> pidlist = new ArrayList<PcCdMenuT>();
		if(menuList != null && menuList.size()>0){
			JSONArray jsonArr =  null;
			JSONObject tjo = null;
			
			//获取所以父菜单
//			for(PcCdMenuT entry  : menuList){
//				if(entry.getFatherMenuCode() == null || "".equals(entry.getFatherMenuCode())){
//					pidlist.add(entry);
//				}
//				
//			}
			
			if(menuList.size()>0){
				jsonStr = new JSONArray();
				for(PcCdMenuT pmenu  : menuList){
					jsonArr = new JSONArray();
					
//					for(PcCdMenuT entry  : menuList){
//						if(!pmenu.getMenuid().equals(entry.getMenuid()) && pmenu.getMenuid().equals(entry.getFatherMenuCode())){
//							tjo = new JSONObject();
//							tjo.put("url", entry.getMenuUrl());
//							tjo.put("text", entry.getRightName());
//							tjo.put("menuid", entry.getMenuid());
//							jsonArr.add(tjo);
//						}
//					}
					tjo = new JSONObject();
					tjo.put("id", pmenu.getMenuid());
					tjo.put("text", pmenu.getRightName());
					tjo.put("isexpand", false);
					tjo.put("children", jsonArr);
					jsonStr.add(tjo);
				}
			}
		}
		
		return jsonStr;
	
	}
	
	@Override
	public JSONArray searchChridMenu(User user, String menuid) throws Exception {

		
		String hql = "FROM PcCdMenuT menu where menu.enabled = 0 and menu.fatherMenuCode='"+menuid+"' order by menu.menuOrder";
		List<PcCdMenuT> menuList = null;
		menuList = functionMenuDao.searchMenu(hql);
		
		//根据用户权限赛选菜单数据
		menuList = AuthorityUtil.MenuAuthority(user, menuList);
		JSONArray jsonStr =  null;
		if(menuList != null && menuList.size()>0){
			JSONArray jsonArr =  null;
			JSONObject tjo = null;
			if(menuList.size()>0){
				jsonStr = new JSONArray();
				for(PcCdMenuT pmenu  : menuList){
					tjo = new JSONObject();
					if(pmenu.getMenuUrl() != null && !"".equals(pmenu.getMenuUrl())){
						tjo.put("id", pmenu.getMenuid());
						tjo.put("url", pmenu.getMenuUrl());
						tjo.put("text", pmenu.getRightName());
						tjo.put("menuid", pmenu.getMenuid());
						jsonStr.add(tjo);
					}else{
						jsonArr = new JSONArray();
						tjo.put("id", pmenu.getMenuid());
						tjo.put("text", pmenu.getRightName());
						tjo.put("isexpand", false);
						tjo.put("children", jsonArr);
						jsonStr.add(tjo);
					}
					
				}
			}
		}
		
		return jsonStr;
	}
	
	
	public List<PcCdMenuT> searchMenu() throws Exception{
		//where menu.enabled = 0 order by menu.menuorder
		String hql = "FROM PcCdMenuT menu ";
		List<PcCdMenuT> menuList = functionMenuDao.searchMenu(hql);
		
		return menuList;
	}
	
	
	
	public JSONArray searchStructure() throws Exception{
			JSONArray jsonArr = new JSONArray();
			JSONArray childrenArr = new JSONArray();
			JSONObject tjo = null;
				String hql = "select a.* from pc_cd_org_t a  "+
							"where a.pid='F24C74E3ACB645679444FE61D9DD1552'  order by a.structurename";
				
				List<Object[]> structureList = functionMenuDao.searchStructureNew(hql);
				
				if(structureList != null && structureList.size()>0){
					for(int i = 0 ; i< structureList.size();i++){
					
							tjo = new JSONObject();
							tjo.put("text",structureList.get(i)[3]);
							tjo.put("id",structureList.get(i)[0]);
							tjo.put("pid",structureList.get(i)[4]);
							tjo.put("nodetype",structureList.get(i)[2]);
							tjo.put("isexpand",false);
							tjo.put("children",childrenArr);
							jsonArr.add(tjo);
							
					}
					
				}
				return jsonArr;
			}
	
	public JSONArray searchOrgChrid(String sql) throws Exception {
		JSONArray jsonArr = new JSONArray();
		JSONArray childrenArr = new JSONArray();
		JSONObject tjo = null;
		String basid ="";
		
//			String hql = "select a.* from pc_cd_org_t a  "+
//						"where a.pid='"+pid+"'  order by a.structurename";
			String[] sqls = sql.split("-");
//			String sqls = sql.substring(0, sql.length()-1);
//			String leaftype = sql.substring(sql.length()-1, sql.length());
			
			List<Object[]> structureList = functionMenuDao.searchStructureNew(sqls[0]);
			
			if(structureList != null && structureList.size()>0){
				for(int i = 0 ; i< structureList.size();i++){
				
						tjo = new JSONObject();
						tjo.put("text",structureList.get(i)[3]);
						tjo.put("id",structureList.get(i)[0]);
						tjo.put("pid",structureList.get(i)[4]);
						tjo.put("nodetype",structureList.get(i)[2]);
						if( structureList.get(i)[14] == null){
							basid = "";
						}else{
							basid = structureList.get(i)[14].toString();
						}
						tjo.put("basid",basid);
						if(structureList.get(i)[2] != null && !sqls[1].equals(String.valueOf(structureList.get(i)[2]))){
							tjo.put("isexpand",false);
							tjo.put("children",childrenArr);
						}
						jsonArr.add(tjo);
						
				}
				
			}
			return jsonArr;
	}
	
	public JSONArray searchFilterOrg(String node) throws Exception {
		JSONArray jsonArr = new JSONArray();
		JSONObject tjo = null;
			String hql = "select * from pc_cd_org_t  "+
						"	start with structurename like '%"+node+"%'"+
						"	connect by prior org_id = pid";
			
			List<Object[]> structureList = functionMenuDao.searchStructureNew(hql);
			
			if(structureList != null && structureList.size()>0){
				for(int i = 0 ; i< structureList.size();i++){
				
						tjo = new JSONObject();
						tjo.put("text",structureList.get(i)[3]);
						tjo.put("id",structureList.get(i)[0]);
						tjo.put("pid",structureList.get(i)[4]);
						tjo.put("nodetype",structureList.get(i)[2]);
						jsonArr.add(tjo);
				}
				
			}
			return jsonArr;
	}
	
	public JSONObject searchNodeInfo(String nodeid) throws Exception {
		JSONObject tjo = null;
			String hql = "select * from pc_cd_org_t t where 1=1 and t.org_id='"+nodeid+"'";
			
			List<Object[]> structureList = functionMenuDao.searchStructureNew(hql);
			
			if(structureList != null && structureList.size()>0){
						tjo = new JSONObject();
						tjo.put("NAME",structureList.get(0)[3]);
						tjo.put("ID",structureList.get(0)[0]);
						tjo.put("PID",structureList.get(0)[4]);
						tjo.put("NODETYPE",structureList.get(0)[2]);
				
			}
			return tjo;
	}
	/**
	 *  判断用户选择节点是否为气井，不是返回-1 不存在数据返回-2
	 */
	public String searchLocalStructure(String node) {
		String str = "";
		List<Object[]> structurestr = null;
		String hql = "select * from tb_structure s where s.ENABLED = 0 and s.STRUCTURENAME = '"+node+"'";
		List<Object[]> structureList = null;
		try {
			functionMenuDao.searchStructureNew(hql);
		} catch (Exception e) {
		}
		
		if(structureList != null && structureList.size()>0){
			String stype = "";
			if(structureList.get(0)[1] != null){
				stype = String.valueOf(structureList.get(0)[1]);
			}
		if( "5".equals(stype)){
			str = (String) structureList.get(0)[2];
			
		}else if("6".equals(stype)){
			String sql = "select "+
						 "	c.structureid as jqzid,c.structurename as jczname, "+
						 "	b.structureid as jcid,b.structurename as jcname, "+
						 "	c.pid as pid "+
						 "	 from tb_structure b  "+
						 "	left join tb_structure c on c.structureid=b.pid "+
						 "	where  b.structuretype=6 and c.structuretype=5 and b.structurename='"+node+"'";
			structurestr = null;
//				functionMenuDao.searchStructureNew(sql);
			str = structurestr.get(0)[1]+">>"+structurestr.get(0)[3];
			
		}else if("7".equals(stype)){
			String sql = "select "+
			 "c.structureid as jqzid,c.structurename as jczname, "+
			 "b.structureid as jcid,b.structurename as jcname, "+
			 "a.structureid as qjid,a.structurename as qjname, "+
			 "c.pid as pid "+
			 "from tb_structure a  "+
			 "left join tb_structure b on b.structureid=a.pid "+
			 "left join tb_structure c on c.structureid=b.pid "+
			 "where a.structuretype=7 and b.structuretype=6 and c.structuretype=5 and a.structurename='"+node+"'";
//			structurestr = null;functionMenuDao.searchStructureNew(sql);
			str = structurestr.get(0)[1]+">>"+structurestr.get(0)[3]+">>"+structurestr.get(0)[5];
			}
		}
		
		return str;
	}
	
	/**
	 * 判断用户选择节点是否为气井或气井，不是返回-1 不存在数据返回-2
	 * @param node
	 * @param type 节点类型判断
	 * @return
	 */
	public String searchQjLocalStructure(String node,String type) {
		String str = "";
		List<Object[]> structurestr = null;
		String hql = "select * from tb_structure s where s.ENABLED = 0 and s.STRUCTURENAME = '"+node+"'";
		List<Object[]> structureList = null;
//		functionMenuDao.searchStructureNew(hql);
		if(structureList != null && structureList.size()>0){
			String stype = "";
			if(structureList.get(0)[1] != null){
				stype = String.valueOf(structureList.get(0)[1]);
			}
			//判断所传节点级别是否与数据获取级别相等
			if(!"".equals(stype) && type.equals(stype)){
				

				if( "5".equals(stype)){
					str = (String) structureList.get(0)[2];
					
				}else if("6".equals(stype)){
					String sql = "select "+
								 "	c.structureid as jqzid,c.structurename as jczname, "+
								 "	b.structureid as jcid,b.structurename as jcname, "+
								 "	c.pid as pid "+
								 "	 from tb_structure b  "+
								 "	left join tb_structure c on c.structureid=b.pid "+
								 "	where  b.structuretype=6 and c.structuretype=5 and b.structurename='"+node+"'";
					structurestr = null;
//					functionMenuDao.searchStructureNew(sql);
					str = structurestr.get(0)[1]+">>"+structurestr.get(0)[3];
					
				}else if("7".equals(stype)){
					String sql = "select "+
					 "c.structureid as jqzid,c.structurename as jczname, "+
					 "b.structureid as jcid,b.structurename as jcname, "+
					 "a.structureid as qjid,a.structurename as qjname, "+
					 "c.pid as pid "+
					 "from tb_structure a  "+
					 "left join tb_structure b on b.structureid=a.pid "+
					 "left join tb_structure c on c.structureid=b.pid "+
					 "where a.structuretype=7 and b.structuretype=6 and c.structuretype=5 and a.structurename='"+node+"'";
					structurestr = null;
//					functionMenuDao.searchStructureNew(sql);
					str = structurestr.get(0)[1]+">>"+structurestr.get(0)[3]+">>"+structurestr.get(0)[5];
					}
			}else{
				
				str = "-1";
			}
		
		}else{
			
			str = "-2";
		}
		return str;
		
	}


	public Integer findTreeOrder(int type){
		
		return functionMenuDao.findTreeOrder(type);
	}

	public JSONArray searchStructure(String type) {
		String hql = "";
		if("7".equals(type)){
			hql = "select a.structureid as qjid,a.structurename as qjname, "+
			 "	b.structureid as jcid,b.structurename as jcname, "+
			 "	c.structureid as jqzid,c.structurename as jczname, "+
			 "	d.structureid as gsid,d.structurename as gsname "+
			 "	from tb_structure a  "+
			 "	left join tb_structure b on b.structureid=a.pid "+
			 "	left join tb_structure c on c.structureid=b.pid "+
			 "	left join tb_structure d on d.structureid=c.pid "+
			 "	where 1=1  "+
			 "	and a.structuretype=7  "+
			 "	and b.structuretype=6  "+
			 "	and c.structuretype=5   "+
			 "	and d.structuretype=4  "+ 
			 "	and a.ENABLED = 0 and b.ENABLED = 0 and c.ENABLED = 0 and d.ENABLED = 0 "+
			 "	order by gsname,jczname,jcname,qjname";
		}else if("6".equals(type)){
			hql = "select "+
			  "	b.structureid as jcid,b.structurename as jcname, "+
			  "	c.structureid as jqzid,c.structurename as jczname, "+
			  "	d.structureid as gsid,d.structurename as gsname "+
			  "	from tb_structure b  "+
			  "	left join tb_structure c on c.structureid=b.pid "+
			  "	left join tb_structure d on d.structureid=c.pid "+
			  "	where 1=1  "+
			  "	and b.structuretype=6  "+
			  "	and c.structuretype=5   "+
			  "	and d.structuretype=4   "+
			  "	and  b.ENABLED = 0 and c.ENABLED = 0 and d.ENABLED = 0 "+
			  "	order by gsname,jczname,jcname";
		}else if("5".equals(type)){
			hql="select "+
			"c.structureid as jqzid,c.structurename as jczname, "+
			"d.structureid as gsid,d.structurename as gsname "+
			"from tb_structure c  "+
			"left join tb_structure d on d.structureid=c.pid "+
			"where 1=1  "+
			"and c.structuretype=5   "+
			"and d.structuretype=4   "+
			"and   c.ENABLED = 0 and d.ENABLED = 0 "+
			"order by gsname,jczname";
		}else if("4".equals(type)){
			hql = "select  "+
			 "d.structureid as gsid,d.structurename as gsname "+
			 "from tb_structure d  "+
			 "where 1=1  "+
			 "and d.structuretype=4   "+
			 "and    d.ENABLED = 0 "+
			 "order by gsname";
		}
		List<Object[]> structureList = null;
//		functionMenuDao.searchStructureNew(hql);
		JSONArray jsonArr = null;
//		List<TbStructure> nodeLvl7 = new ArrayList<TbStructure>();
		String jczname = "";
		String jcname = "";
		String qjname = "";
		String gsname = "";
		
		//气井json
		JSONArray jqzJsonArr = new JSONArray();
		//气井json
		JSONArray jcJsonArr = new JSONArray();
		//气井json
		JSONArray qjJsonArr = new JSONArray();
		//json单位
		JSONObject tjo = null;
		if(structureList != null && structureList.size()> 0){
			//最小节点为气井
			if("7".equals(type)){
				qjname = String.valueOf(structureList.get(0)[1]);
				jcname = String.valueOf(structureList.get(0)[3]);
				jczname = String.valueOf(structureList.get(0)[5]);
				gsname = String.valueOf(structureList.get(0)[7]);
				
				for(int i = 0; i< structureList.size() ; i++){
					//最后一组数据
					if(i == structureList.size()-1){
						
						//将上面集气站中最后一扣气井添加到json中。
						tjo = new JSONObject();
						tjo.put("text",structureList.get(i)[1]);
						qjJsonArr.add(tjo);
						//井场信息
						tjo = new JSONObject();
						tjo.put("text",structureList.get(i)[3]);
						tjo.put("children",qjJsonArr);
						//将气井放入井场ARR
						jcJsonArr.add(tjo);
						
						//集气站信息
						tjo = new JSONObject();
						tjo.put("text",structureList.get(i)[5]);
						tjo.put("children",jcJsonArr);
						//将气井放入井场ARR
						jqzJsonArr.add(tjo);
						
					}else{
						
						//集气站相同
						if(jczname.equals(String.valueOf(structureList.get(i)[5]))){
							
							//井场相同
							if(jcname.equals(String.valueOf(structureList.get(i)[3]))){
								tjo = new JSONObject();
								tjo.put("text",structureList.get(i)[1]);
								qjJsonArr.add(tjo);
							//井场不相同
							}else{
								
								//井场信息
								tjo = new JSONObject();
								tjo.put("text",jcname);
								tjo.put("children",qjJsonArr);
								//将气井放入井场ARR
								jcJsonArr.add(tjo);
								//将气井ARR 清空
								qjJsonArr = new JSONArray();
								
								//将上面井场中最后一扣气井添加到json中。
								tjo = new JSONObject();
								tjo.put("text",structureList.get(i)[1]);
								qjJsonArr.add(tjo);
								
								jcname = String.valueOf(structureList.get(i)[3]);
								
								
							}
						//集气站不相同	
						}else{
							
							//井场信息
							tjo = new JSONObject();
							tjo.put("text",jcname);
							tjo.put("children",qjJsonArr);
							//将气井放入井场ARR
							jcJsonArr.add(tjo);
							
							//集气站信息
							tjo = new JSONObject();
							tjo.put("text",jczname);
							tjo.put("children",jcJsonArr);
							//将气井放入井场ARR
							jqzJsonArr.add(tjo);

							//将气井ARR 清空
							qjJsonArr = new JSONArray();
							//将井场ARR 清空
							jcJsonArr = new JSONArray();
							
							//将上面集气站中最后一扣气井添加到json中。
							tjo = new JSONObject();
							tjo.put("text",structureList.get(i)[1]);
							qjJsonArr.add(tjo);
							//井场信息
							tjo = new JSONObject();
							tjo.put("text",structureList.get(i)[3]);
							tjo.put("children",qjJsonArr);
							//将气井放入井场ARR
							jcJsonArr.add(tjo);
							jczname = String.valueOf(structureList.get(i)[5]);
						}
					}
					
				
				}
				
			//最小节点为井场
			}else if("6".equals(type)){
				jcname = String.valueOf(structureList.get(0)[1]);
				jczname = String.valueOf(structureList.get(0)[3]);
				gsname = String.valueOf(structureList.get(0)[5]);
				
				for(int i = 0; i< structureList.size() ; i++){
					//最后一组数据
					if(i == structureList.size()-1){
						
					
						//井场信息
						tjo = new JSONObject();
						tjo.put("text",structureList.get(i)[1]);
						//将气井放入井场ARR
						jcJsonArr.add(tjo);
						
						//集气站信息
						tjo = new JSONObject();
						tjo.put("text",structureList.get(i)[3]);
						tjo.put("children",jcJsonArr);
						//将气井放入井场ARR
						jqzJsonArr.add(tjo);
						
					}else{
						
						//集气站相同
						if(jczname.equals(String.valueOf(structureList.get(i)[3]))){
							
							
								tjo = new JSONObject();
								tjo.put("text",structureList.get(i)[1]);
								jcJsonArr.add(tjo);
							
						//集气站不相同	
						}else{
							
							//集气站信息
							tjo = new JSONObject();
							tjo.put("text",jczname);
							tjo.put("children",jcJsonArr);
							//将气井放入井场ARR
							jqzJsonArr.add(tjo);

							//将井场ARR 清空
							jcJsonArr = new JSONArray();
							
							//井场信息
							tjo = new JSONObject();
							tjo.put("text",structureList.get(i)[1]);
							//将气井放入井场ARR
							jcJsonArr.add(tjo);
							jczname = String.valueOf(structureList.get(i)[3]);
						}
					}
					
				
				}
				
			}else if("5".equals(type)){
				jczname = String.valueOf(structureList.get(0)[1]);
				gsname = String.valueOf(structureList.get(0)[3]);
				
				for(int i = 0; i< structureList.size() ; i++){
					tjo = new JSONObject();
					tjo.put("text",structureList.get(i)[1]);
					jqzJsonArr.add(tjo);
				}
				
				
			}else if("4".equals(type)){
				gsname = String.valueOf(structureList.get(0)[1]);
			}
		}

		//System.out.println(jqzJsonArr);
	
		return jqzJsonArr;
	}
	/**
	 * 获取节点下所以油井id
	 * 
	 */
//	public List<String> getallwell(String node) {
//		List<String> snames = new ArrayList<String>();
//		String hql = "FROM TbStructure s where s.enabled = 0 order by s.structuretype";
//		List<TbStructure> structureList = functionMenuDao.searchStructure(hql);
//		if(structureList != null && structureList.size()>0){
//			if(structureList.get(0).getStructuretype() != null ){
//				String type = String.valueOf(structureList.get(0).getStructuretype());
//				if("7".equals(type)){
//					snames.add(structureList.get(0).getStructureid());
//				}else {
//					
//					hql =" select "+
//						"	a.structureid as qjid,a.structurename as qjname, "+
//						"	b.structureid as jcid,b.structurename as jcname,  "+
//						"	c.structureid as jqzid,c.structurename as jczname,  "+
//						"	d.structureid as gsid,d.structurename as gsname  "+
//						"	from tb_structure a   "+
//						"	left join tb_structure b on b.structureid=a.pid  "+
//						"	left join tb_structure c on c.structureid=b.pid  "+
//						"	left join tb_structure d on d.structureid=c.pid "+
//						"	where 1=1   "+
//						"	and a.structuretype=7 "+ 
//						"	and b.structuretype=6   "+
//						"	and c.structuretype=5   "+
//						"	and d.structuretype=4   "+
//						"	and  a.ENABLED = 0 and  b.ENABLED = 0 and c.ENABLED = 0 and d.ENABLED = 0 ";
//							
//						if("6".equals(type)){
//							hql+= "	and b.structureid = '"+structureList.get(0).getStructureid()+"'";
//						}else if("5".equals(type)){
//							hql+="	and c.structureid = '"+structureList.get(0).getStructureid()+"'";
//						}else if("4".equals(type)){
//							hql+="	and d.structureid = '"+structureList.get(0).getStructureid()+"'";
//						}
//						hql+="	order by gsname,jczname,jcname,qjname";
//					
//					List<Object[]> lists = functionMenuDao.searchStructureNew(hql);
//					if(lists != null && lists.size()> 0){
//						for(Object[] obj :lists){
//							snames.add(String.valueOf(obj[0]));
//							
//						}
//						
//						
//					}
//					
//				}
//				
//			}
//			
//		}
//		
//		return null;
//	}

	public JSONArray searchPcdStructure() {
		String hql = "SELECT jc.STRUCTUREID, jc.P01 jcName,pcd.PCDID, pcd.P01 pcdName FROM DBAT0053 pcd left join DBAT1020 jc on pcd.PCDID = jc.PCDID where pcd.ENABLED = 0 and jc.ENABLED = 0 order by pcdName desc,jcName asc";
		JSONArray jsonArr = null;
		JSONObject jsobj = new JSONObject();
		String parent = null;
		JSONArray children = new JSONArray();
		List<Object[]> structureList = null;
//		functionMenuDao.searchStructureNew(hql);
		if (structureList.size() > 0 && structureList != null) {
			jsonArr = new JSONArray();
			for(Object[] entry : structureList){
				if (!entry[3].equals(parent)) {
					if (parent != null) {
						jsobj = new JSONObject();
						jsobj.put("text",parent);
						jsobj.put("children",children);
						jsonArr.add(jsobj);
						children = new JSONArray();
					}
					parent = String.valueOf(entry[3]);
					jsobj = new JSONObject();
					jsobj.put("text", entry[1]);
					children.add(jsobj);
				}
				else {
					jsobj = new JSONObject();
					jsobj.put("text", entry[1]);
					children.add(jsobj);
				}
			}
			jsobj = new JSONObject();
			jsobj.put("text",parent);
			jsobj.put("children",children);
			jsonArr.add(jsobj);
			return jsonArr;
		}
		return jsonArr;
	}

	public List<String> getallwell(String node) {
		// TODO Auto-generated method stub
		return null;
	}

	public JSONArray searchAutoCompleteData(String flg) {
		// TODO Auto-generated method stub
		return null;
	}

	public JSONArray searchStructureJqz(String hql) {
		// TODO Auto-generated method stub
		return null;
	}


	public JSONArray menuTreeData(String sql) throws Exception {

		JSONArray jsonArr = new JSONArray();
		JSONArray childrenArr = new JSONArray();
		JSONObject tjo = null;
		String basid ="";
			String[] sqls = sql.split("-");
			List<Object[]> structureList = functionMenuDao.searchStructureNew(sqls[0]);
			
			if(structureList != null && structureList.size()>0){
				for(int i = 0 ; i< structureList.size();i++){
				
						tjo = new JSONObject();
						tjo.put("text",structureList.get(i)[3]);
						tjo.put("id",structureList.get(i)[0]);
						tjo.put("pid",structureList.get(i)[4]);
						tjo.put("nodetype",structureList.get(i)[2]);
						if( structureList.get(i)[14] == null){
							basid = "";
						}else{
							basid = structureList.get(i)[14].toString();
						}
						tjo.put("basid",basid);
						if(structureList.get(i)[2] != null && !sqls[1].equals(String.valueOf(structureList.get(i)[2]))){
							tjo.put("isexpand",false);
							tjo.put("children",childrenArr);
						}
						jsonArr.add(tjo);
						
				}
				
			}
			return jsonArr;
		
	}


	public JSONArray searchFilter(String sql) throws Exception {

		JSONArray jsonArr = new JSONArray();
		JSONArray childrenArr = new JSONArray();
		JSONObject tjo = null;
			String frinode ="";
			String basid ="";
			String[] sqls = sql.split("-");
			List<Object[]> structureList = functionMenuDao.searchStructureNew(sqls[0]);
			
			if(structureList != null && structureList.size()>0){
				if(!"F24C74E3ACB645679444FE61D9DD1552".equals(structureList.get(0)[0].toString())){
					frinode = structureList.get(0)[2].toString();
				}else{
					frinode = structureList.get(1)[2].toString();
				}
				
				for(int i = 0 ; i< structureList.size();i++){
				
					if(frinode.equals(String.valueOf(structureList.get(i)[2]))){
						tjo = new JSONObject();
						tjo.put("text",structureList.get(i)[3]);
						tjo.put("id",structureList.get(i)[0]);
						tjo.put("pid",structureList.get(i)[4]);
						tjo.put("nodetype",structureList.get(i)[2]);
						if( structureList.get(i)[14] == null){
							basid = "";
						}else{
							basid = structureList.get(i)[14].toString();
						}
						tjo.put("basid",basid);
						if(sqls[1].indexOf(',') != -1){
							String[] nodet = sqls[1].split(",");
							
							if(nodet != null && nodet.length > 1){
								if( nodet.length == 2){
									if(structureList.get(i)[2] != null && !nodet[0].equals(String.valueOf(structureList.get(i)[2])) && !nodet[1].equals(String.valueOf(structureList.get(i)[2]))){
										tjo.put("isexpand",false);
										tjo.put("children",childrenArr);
									}
								}
								
							}
							
						}else{
							if(structureList.get(i)[2] != null && !sqls[1].equals(String.valueOf(structureList.get(i)[2]))){
								tjo.put("isexpand",false);
								tjo.put("children",childrenArr);
							}
						}
						
						jsonArr.add(tjo);
					}
				}
				
			}
			return jsonArr;
	
	}


	@Override
	public JSONArray searchPortalMenu(User user) throws Exception {
//
//		
//		
//		
//		
//		String hql = "FROM PcCdPortalMenuT menu where 1=1  and menu.portalmenuid='AD13630FDC324D90859F5AD363A3DKKA' order by menu.portalmenuName";
//		List<PcCdPortalMenuT> menuList = null;
//		menuList = functionMenuDao.searchMenu(hql);
//		
//		//根据用户权限赛选菜单数据
//		menuList = AuthorityUtil.MenuAuthority(user, menuList);
		JSONArray jsonStr =  null;
//		List<PcCdMenuT> pidlist = new ArrayList<PcCdMenuT>();
//		if(menuList != null && menuList.size()>0){
//			JSONArray jsonArr =  null;
//			JSONObject tjo = null;
//
//			
//			if(menuList.size()>0){
//				jsonStr = new JSONArray();
//				for(PcCdMenuT pmenu  : menuList){
//					jsonArr = new JSONArray();
//
//					tjo = new JSONObject();
//					tjo.put("id", pmenu.getMenuid());
//					tjo.put("text", pmenu.getRightName());
//					tjo.put("isexpand", false);
//					tjo.put("children", jsonArr);
//					jsonStr.add(tjo);
//				}
//			}
//		}
//		
		return jsonStr;
//	
//	
	}


	





	




//	public JSONArray searchAutoCompleteData(String flg) {
//		
//		
//		String hql = "select * from TB_structure a "+
//					"	where 1=1 and a.enabled=0  ";
//					//0： 获取井口数据
//					if("0".equals(flg)){
//						hql += "	and a.structuretype = 7 ";	
//					//1：获取井场数据	
//					}else if("1".equals(flg)){
//						hql += "	and a.structuretype = 6 ";	
//					//	2：获取井口和井场数据（井场数据后默认补充井场字符）	
//					}else if("2".equals(flg)){
//						hql += "	and a.structuretype = 6 or a.structuretype = 7 ";	
//					}else{
//						
//					}
//					hql += "	order by a.structurename,a.structuretype";
//		JSONArray jsonArr = null;
//		JSONObject jsobj = new JSONObject();
//		List<Object[]> structureList = functionMenuDao.searchStructureNew(hql);
//		Object[] obj = null;
//		if (structureList.size() > 0 && structureList != null) {
//			jsonArr = new JSONArray();
//			obj = new Object[structureList.size()];
//			for(int i = 0 ; i<structureList.size(); i ++){
//				//井场数据
//				if(structureList.get(i)[1] != null && "6".equals(String.valueOf(structureList.get(i)[1]))){
//					obj[i] = structureList.get(i)[2]+"井场";
//					
//				//井口数据	
//				}else if(structureList.get(i)[1] != null && "7".equals(String.valueOf(structureList.get(i)[1]))){
//					
//					obj[i] = structureList.get(i)[2];
//				}
//				
//			}
//			
//		}
//		jsobj.put("WELLDATAS", obj);
//		jsonArr.add(jsobj);
//		return jsonArr;
//	}
	
//	public TbStructure searchByStructureName(String name , String type) {
//		String hql = "FROM TbStructure s where s.structurename ='"+name+"'" + type;
//		List<TbStructure> structureList = functionMenuDao.searchStructure(hql);
//		if(structureList.size()>0){
//			return structureList.get(0);
//		}else{
//			return null;
//		}
//	}
}
