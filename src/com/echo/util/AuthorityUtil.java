package com.echo.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.dao.CommonDao;
import com.echo.dao.FunctionMenuDao;
import com.echo.dto.PcCdMenuT;
import com.echo.dto.PcCdPortalMenuT;
import com.echo.dto.PcCdRightT;
import com.echo.dto.User;

/**
 * 权限通用类
 * @author yanlong
 *
 */
public class AuthorityUtil {
	private static FunctionMenuDao functionMenuDao;
	private static CommonDao commonDao; 
	public void setCommonDao(CommonDao commonDao) {
		AuthorityUtil.commonDao = commonDao;
	}
	@SuppressWarnings("static-access")
	public void setFunctionMenuDao(FunctionMenuDao functionMenuDao) {
		this.functionMenuDao = functionMenuDao;
	}
	
	public static List<Object[]> getDatas(String sql)throws Exception{
		return commonDao.searchEverySql(sql);
	}
	/**
	 * 通过井号 判断 是否存在计量数据
	 * @param wellname 井号
	 * @return
	 */
	public static byte getKEYTAG(String wellname){
		byte tag = 0;
		String welltepe ="";
		String sql = "select t.welltepe,t.well_name from pc_cd_orgall_v t where 1=1 and t.well_name ='"+wellname+"'";
		List<Object[]> list = commonDao.searchEverySql(sql);
		
		if(list != null && list.size()>0){
			welltepe = list.get(0)[0].toString();
		}
		sql = "";
		if(welltepe != null && !"".equals(welltepe)){
			if("0".equals(welltepe) || "1".equals(welltepe) || "2".equals(welltepe) ){
				sql = "select v.org_id,v.structurename from pc_cd_chytree_v v where 1=1 and v.structurename= '"+wellname+"' and v.structuretype = 9 ";
			}else if("3".equals(welltepe)){
				sql = "select v.org_id,v.structurename from pc_cd_waterfloodingtree_v v where 1=1 and v.structurename= '"+wellname+"'";
			}
			
		}
		
		if(sql != null && !"".equals(sql)){
			List<Object[]> list1 = commonDao.searchEverySql(sql);
			if(list1 != null && list1.size()>0){
				tag = 1;
			}
		}
		return tag;
	}
	
	/**
	 * 根据要修改的生成单位与 用户所拥有允许修改的生产单位组织结构名称进行判断用户是否拥有添加或修改该生产单位的权限
	 * @param orgid 要修改的生成单位
	 * @param dataIds 用户所拥有允许修改的生产单位组织结构id,名称，类型 集合
	 * @return false 无权限，true 有权限
	 * @throws Exception
	 */
	public static boolean authorityDos(String orgid,List<Object[]> dataIds)throws Exception{
		boolean flg = false;
		for(int i = 0; i<dataIds.size();i++){
			if(orgid.equals(dataIds.get(i)[1].toString())){
				flg = true;
			}
			
		}
		
		return flg;
	}
	/**
	 * 根据session 中用户状态判断 用户是否已无 操作权限
	 * 目前使用的class有CCZRSHAction,Excel1Action,ExcelSagdAction,FloodingRPDAction,
	 * GasDailyWHAction,RuleWellRPDAction,ThinWellRPDWHAction
	 * 分别是采出注入审核前需要判断；Excel1Action好像未用；
	 * 注水、常规稠油井、稀油、注水日报的自动化提取、删除记录前需要进行判断。
	 * @param date 当前系统时间
	 * @param operat  当前操作
	 * @param depname 所属部门名称
	 * @param userStauts session中用户状态
	 * @return  flg 用户 是否拥有操作权限  ，true： 有，false :无
	 * @throws Exception
	 */
	public static boolean checkeUserStauts(String date,String operat,User user)throws Exception{
		boolean flg = false;
		
/*		String auditStaus = "";
		if("4".equals(user.getDepType())){
			auditStaus = date+"_"+user.getDepname()+"_地质组审核";
		}else if("15".equals(user.getDepType())){
			auditStaus = date+"_"+user.getDepname()+"_班组审核";
		}
		
		if(operat != null && !"".equals(operat)){
			String nowStaus = date+"_"+user.getDepname()+"_"+operat;
			if(!user.getOperSdbsadbse().equals(nowStaus) && !user.getOperSdbsadbse().equals(auditStaus)){
				flg = true;
			}
		}else{
			if(!user.getOperSdbsadbse().equals(auditStaus)){
				flg = true;
			}
			
		}*/
		/*
		 * 目前只有采出注入报表的审核以及
		 */
		if(operat != null && !"".equals(operat)){
			String nowStaus = date+"_"+user.getDepname()+"_"+operat;
			if(!user.getOperSdbsadbse().contains(nowStaus) ){
				flg = true;
			}
		}else{
			flg = true;
		}
		return flg;
	}
	
//	public static int updateUserStauts(String date,String operat,HttpSession session)throws Exception{
//		User user = (User) session.getAttribute("userInfo");
//		int flg = -1;
//		String userStauts = date+"_"+user.getDepname()+"_"+operat;
//		String sql = "update pc_cd_user_t u set u.oper_sdbsadbse = '"+userStauts+"' where u.departmentid ='"+user.getDepid()+"'";
//		flg = commonDao.updateButhSql(sql);
//		if(flg > 0){
//			user.setOperSdbsadbse(userStauts);
//			session.setAttribute("userInfo", user); 
//		}
//		return flg;
//	}
	//2015.11.5
	public static int updateUserStauts(String date,String operat,HttpSession session)throws Exception{
		User user = (User) session.getAttribute("userInfo");
		int flg = -1;
		String userStauts = date+"_"+user.getDepname()+"_"+operat;
		
		String [] operats=null;
		String newOperSdbsadbse=user.getOperSdbsadbse();
		/*
		 * operat包含采出审核和注入审核两类
		 * 如果有以前相同operat的记录，则替换以前的信息；
		 * 没有且不为空则添加”；“+本审核状态；
		 * 为空则使用本审核状态
		 */
		if (user.getOperSdbsadbse() != null && !"null".equals(user.getOperSdbsadbse()) && !"".equals(user.getOperSdbsadbse().trim())){
			operats = user.getOperSdbsadbse().split(";");
			boolean updateFlg=false;
			if (operats!=null ){
				for(int i =0 ;i< operats.length;i++){
					if(operats[i]!=null && !"".equals(operats[i])){
						if(operats[i].contains(operat)){
							newOperSdbsadbse=newOperSdbsadbse.replace(operats[i], userStauts);
							updateFlg=true;
							break;
						}
					}
				}
			}
			if(!updateFlg){
				newOperSdbsadbse=newOperSdbsadbse.concat(";").concat(userStauts);
			}
		}
		else{
			newOperSdbsadbse=userStauts;
		}
			


		String sql = "update pc_cd_user_t u set u.oper_sdbsadbse = '"+newOperSdbsadbse/*userStauts*/+"' where u.departmentid ='"+user.getDepid()+"'";
		flg = commonDao.updateButhSql(sql);
		if(flg > 0){
			user.setOperSdbsadbse(newOperSdbsadbse/*userStauts*/);
			session.setAttribute("userInfo", user); 
		}
		return flg;
	}
	/**
	 * 公用方法传入sql 获取数据
	 * @param user
	 * @return
	 */
	public static List<Object[]> commonssql(String sql)throws Exception{
		
		return commonDao.searchEverySql(sql);
		
	}
	
	/**
	 * 根据用户session中所属部门 获取其管辖内所有在组织结构中
	 * @param user
	 * @return
	 */
	public static List<Object[]> updateDatasAuthority(User user,HttpSession session)throws Exception{
		List<Object[]> dataIds = new ArrayList<Object[]>();
		if(user.getAuthorityDatas() == null || user.getAuthorityDatas().size() == 0){
				
				String deptype = user.getDepType();
				String sql = "";
//					" select STRUCTURETYPE  from pc_cd_org_t where org_id = '"+user.getDepid()+"'";
//				List<Object[]> dep = commonDao.searchEverySql(sql);
				
//				if(dep != null && dep.size() >0){
					//用户权限所属班组
					if("15".equals(deptype)){
						sql = "select org_id,STRUCTURENAME,STRUCTURETYPE from  pc_cd_org_t start with  org_id in(select m.org_id from pc_cd_manifold_t m  where m.team_id='"+user.getDepid()+"')  connect by prior org_id = pid";
					//用户权限所属组织结构
					}else{
						sql = "select org_id,STRUCTURENAME,STRUCTURETYPE from pc_cd_org_t start with  org_id='"+user.getDepid()+"'  connect by prior org_id = pid";
					}
					
					dataIds = commonDao.searchEverySql(sql);
					
//				}
				user.setAuthorityDatas(dataIds);
				session.setAttribute("userInfo", user); 
			
		}else{
			
			dataIds = user.getAuthorityDatas();
		}
		
		return dataIds;
		
	}
	
	public static List<Object[]> updateDatasAuthorityCheck(User user,HttpSession session)throws Exception{
		List<Object[]> dataIds = new ArrayList<Object[]>();
		if(user.getAuthorityDatas() == null || user.getAuthorityDatas().size() == 0){
				
				String deptype = user.getDepType();
				String sql = "";
//					" select STRUCTURETYPE  from pc_cd_org_t where org_id = '"+user.getDepid()+"'";
//				List<Object[]> dep = commonDao.searchEverySql(sql);
				
//				if(dep != null && dep.size() >0){
					//用户权限所属班组
					if("15".equals(deptype)){
						sql = "select org_id,STRUCTURENAME,STRUCTURETYPE from  pc_cd_org_t start with  org_id in(select m.org_id from pc_cd_team_t m  where m.org_id='"+user.getDepid()+"')  connect by prior org_id = pid";
					//用户权限所属组织结构
					}else{
						sql = "select org_id,STRUCTURENAME,STRUCTURETYPE from pc_cd_org_t start with  org_id='"+user.getDepid()+"'  connect by prior org_id = pid";
					}
					
					dataIds = commonDao.searchEverySql(sql);
					
//				}
				user.setAuthorityDatas(dataIds);
				session.setAttribute("userInfo", user); 
			
		}else{
			
			dataIds = user.getAuthorityDatas();
		}
		
		return dataIds;
		
	}
	/**
	 * 将用户权限取出转行为LIST
	 * @param user
	 * @return
	 */
	public static List<PcCdRightT> userAuthority(User user){
//		Set<PcCdRightT>  rights = null;
//		rights = user.getPcCdRightT();
//		List<PcCdRightT> usrRight= new ArrayList<PcCdRightT>();
//		if(rights != null && rights.size()>0){
//			Iterator it = rights.iterator();
//			while(it.hasNext()){
//				 PcCdRightT r = (PcCdRightT)it.next();
//				 //过滤获取所有用户权限
//				 usrRight.add(r);
//			 }
//		}
		return user.getPcCdRightTs();
	}
	/**
	 * 根据用户权限赛选所显示功能菜单
	 * @param user
	 * @param menuList
	 * @return
	 */
	public static List<PcCdMenuT> MenuAuthority(User user,List<PcCdMenuT> menuList){
		List<PcCdMenuT> MenuAuthorityList = new ArrayList<PcCdMenuT>();
		List<String> menuAuthority = new ArrayList<String>();
		List<PcCdRightT> usrRight = userAuthority(user);
		
		if(usrRight != null && usrRight.size()>0){
			for(PcCdRightT entry : usrRight){
				 if(entry.getRightName() != null && "显示功能菜单".equals(entry.getRightName()) 
						 && entry.getMenuid() != null && !"".equals(entry.getMenuid())){
					 //将该用户所拥有显示菜单ID 放入LIST
					 menuAuthority.add(entry.getMenuid());
				 }
				
			}
		}
		
		//数据赛选，将权限中有的菜单ID  放入返回数组中
		if(menuAuthority != null && menuAuthority.size()>0 && menuList != null && menuList.size()>0){
			
			for(PcCdMenuT menuid : menuList){
				for(String id : menuAuthority){
					
					if(menuid.getMenuid().equals(id)){
						MenuAuthorityList.add(menuid);
					}
				}
				
			}
			
		}
		
		return MenuAuthorityList;
	}
	
	
	/**
	 * 根据用户权限赛选所显示PORTAL功能菜单
	 * @param user
	 * @param menuList
	 * @return
	 */
	public static List<PcCdPortalMenuT> PortalMenuAuthority(User user,List<PcCdPortalMenuT> menuList){
		List<PcCdPortalMenuT> MenuAuthorityList = new ArrayList<PcCdPortalMenuT>();
		List<String> menuAuthority = new ArrayList<String>();
		List<PcCdRightT> usrRight = userAuthority(user);
		
		if(usrRight != null && usrRight.size()>0){
			for(PcCdRightT entry : usrRight){
				 if(entry.getRightName() != null && "显示功能菜单".equals(entry.getRightName()) 
						 && entry.getMenuid() != null && !"".equals(entry.getMenuid())){
					 //将该用户所拥有显示菜单ID 放入LIST
					 menuAuthority.add(entry.getMenuid());
				 }
				
			}
		}
		
		//数据赛选，将权限中有的菜单ID  放入返回数组中
		if(menuAuthority != null && menuAuthority.size()>0 && menuList != null && menuList.size()>0){
			
			for(PcCdPortalMenuT menuid : menuList){
				for(String id : menuAuthority){
					
					if(menuid.getPortalmenuid().equals(id)){
						MenuAuthorityList.add(menuid);
					}
				}
				
			}
			
		}
		
		return MenuAuthorityList;
	}
	
	/**
	 * 获取用户页面中权限
	 * @param user session中user
	 * @param pageid 当前页面id
	 * @return
	 */
	@SuppressWarnings("unused")
	public static String PageAuthority(User user,String page) throws Exception{
		
		//根据页面名称获取当前页面ID
		String pageid = null;
		try {
			pageid = getPageId(page);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<PcCdRightT> usrRight = userAuthority(user);
		List<String> buttonAuthority = new ArrayList<String>();
		
		if(usrRight != null && usrRight.size()>0){
			for(PcCdRightT entry : usrRight){
					
					if(entry != null && entry.getRightName() != null && !"不显示列名".equals(entry.getRightName()) 
							&&!"显示功能菜单".equals(entry.getRightName()) && !"组织节点显示".equals(entry.getRightName()) &&
							entry.getMenuid() != null && pageid.equals(entry.getMenuid())){
						
						buttonAuthority.add(entry.getRightName());
					}
				}
			}
		JSONObject tjo = null;
		StringBuffer sb = new StringBuffer();
		if(buttonAuthority != null && buttonAuthority.size()>0){

			tjo = new JSONObject();
			String[] rights = new String[buttonAuthority.size()];
			int flag = 0 ;
			for(int i = 0; i<buttonAuthority.size();i++ ){
				rights[i] = buttonAuthority.get(i);
				if(buttonAuthority.get(i) != null && "添加".equals(buttonAuthority.get(i))){
					
					
				}else if(buttonAuthority.get(i) != null && "修改".equals(buttonAuthority.get(i))){
					
				}
				String buttone = buttonAuthority.get(i);
				String iconStr = "";
				String iconid = "";
//				1:添加操作
				if("添加".equals(buttone)){
					//id:'savedisabled';
					iconid = "addid";
					iconStr = "add";
					flag = 1;
//				2:删除操作;
				}else if("删除".equals(buttone)){
					iconid = "delid";
					iconStr = "delete";
//				3:修改操作;
				}else if("修改".equals(buttone)){
					iconid = "modid";
					iconStr = "modify";
					flag = 1;
//				4:查看操作;
				}else if("查询".equals(buttone)){
					iconid = "viewid";
					iconStr = "view";
//				5:权限分配;
				}
				sb.append("{id:'"+iconid+"', text: '"+buttone+"', click: itemclick, icon: '"+iconStr+"' },");
				
			}
			
			if(flag == 1){
				sb.append("{id:'savedisabled',text: '保存', click: itemclick, icon: 'save-disabled' },");
			}
		
			
			//tjo.put("RIGHTS", rights);
			
		}
		String returnStr = "";
		if(sb != null && sb.length() > 0){
			returnStr =  "{ items:["+sb.substring(0, sb.length()-1)+"]}";
		}
		
		return returnStr;
		
	}
	/**根据权限将页面中功能按钮生成json 
	 *  0:用于显示列名;
		1:添加操作
		2:删除操作;
		3:修改操作;
		4:查看操作;
		5:布局设置操作;
		
		8:显示菜单
		9:组织结构节点显示； 
		本方法操作按钮功能，所以不包括  0、8、9
	 * @param user
	 * @return
	 */
	@SuppressWarnings("unused")
	public static String MenuAuthority(User user,String pageid){
		
		List<PcCdRightT> usrRight = userAuthority(user);
		List<String> buttonAuthority = new ArrayList<String>();
		StringBuffer sb = new StringBuffer();
		String returnStr = "";
		if(usrRight != null && usrRight.size()>0){
			for(PcCdRightT entry : usrRight){
					
					if(entry != null && entry.getRightName() != null && !"不显示列名".equals(entry.getRightName()) 
							&&!"显示功能菜单".equals(entry.getRightName()) && !"组织节点显示".equals(entry.getRightName()) &&
							entry.getMenuid() != null && pageid.equals(entry.getMenuid())){
						
						buttonAuthority.add(entry.getRightName());
					}
				}
			}
		
		
		if(buttonAuthority != null && buttonAuthority.size()>0){
			
			//buttonAuthority 重新排序
			Collections.sort(buttonAuthority);
			List<String> authority = new ArrayList<String>();
			int flag = 0;
			for(String buttone :buttonAuthority){
				
//				if(!"注汽分配".equals(buttone) && !"报表生成".equals(buttone)){
					
				
				String iconStr = "";
				String imgStr = "";
				String iconid = "";

//				1:添加操作
				if("添加".equals(buttone)){
					
					iconid = "addid";
					iconStr = "add";
					imgStr = "../../lib/ligerUI/skins/icons/add.gif";
					flag = 1;
					
//				2:删除操作;
				}else if("删除".equals(buttone)){
					iconid = "deleteid";
					iconStr = "delete";
					imgStr = "../../lib/ligerUI/skins/icons/delete.gif";
//				3:修改操作;
				}else if("修改".equals(buttone)){
					iconid = "modifyid";
					iconStr = "modify";
					imgStr = "../../lib/ligerUI/skins/icons/modify.gif";
					flag = 1;
//				4:查看操作;
				}else if("查询".equals(buttone)){
					iconid = "viewid";
					iconStr = "view";
					imgStr = "../../lib/ligerUI/skins/icons/publish.gif";
//				5:角色授权;
				}else if("授权".equals(buttone)){
					iconid = "accreditid";
					iconStr = "accredit";
					imgStr = "../../lib/ligerUI/skins/icons/customers.gif";
//					6:审核	
				}else if("审核".equals(buttone)){
					iconid = "auditid";
					iconStr = "audit";
					imgStr = "../../lib/ligerUI/skins/icons/role.gif";
					
				}else if("报表生成".equals(buttone)){
					iconid = "statementid";
					iconStr = "statement";
					imgStr = "../../lib/ligerUI/skins/icons/role.gif";
					
				}else if("报表导入".equals(buttone)){
					iconid = "importid";
					iconStr = "importd";
					imgStr = "../../lib/ligerUI/skins/icons/import.png";
					
				}else if("注汽分配".equals(buttone)){
					iconid = "allocationid";
					iconStr = "allocation";
					imgStr = "../../lib/ligerUI/skins/icons/role.gif";
					
				}else if("班组审核".equals(buttone)){
					iconid = "bzauditid";
					iconStr = "bzaudit";
					imgStr = "../../lib/ligerUI/skins/icons/role.gif";
					
				}else if("地质组审核".equals(buttone)){
					iconid = "dzzauditid";
					iconStr = "dzzaudit";
					imgStr = "../../lib/ligerUI/skins/icons/role.gif";
					
				}else if("数据准备".equals(buttone)){
					iconid = "datareadyid";
					iconStr = "dataready";
					imgStr = "../../lib/ligerUI/skins/icons/database.gif";
					
				}else if("自动化提取".equals(buttone)){
					iconid = "automateid";
					iconStr = "automate";
					imgStr = "../../lib/ligerUI/skins/icons/back.gif";
					
				}else if("解锁".equals(buttone)){
					iconid = "unlockid";
					iconStr = "unlock";
					imgStr = "../../lib/ligerUI/skins/icons/lock.gif";
					
				}else if("生成点表".equals(buttone)){
					iconid = "involvesTableid";
					iconStr = "involvesTable";
					imgStr = "../../lib/ligerUI/skins/icons/involvesTable.gif";
					
				}
				
				

				//TODO..
			
//				{ text: '删除', click: "javascript:itemclick", img: '../../lib/ligerUI/skins/icons/delete.gif',icon: 'delete' },
				sb.append("{id:'"+iconid+"', text: '"+buttone+"', click: itemclick, ");
				if(!"".equals(imgStr)){
					sb.append("img: '"+imgStr+"',");
				}
				sb.append("icon: '"+iconStr+"' },");
				//sb.append("{ line:true },");

//				}
			}
			if(flag == 1){
				//{ id:'dispalyline',line:true },{id:'saveline', line:true },{id:'upid', text: '显示', click: itemclick, img: '../../lib/ligerUI/skins/icons/up.gif',icon: 'up' },
				sb.append("{id:'saveid', text: '保存', click: itemclick, img: '../../lib/ligerUI/skins/icons/save.gif',icon: 'save' },");
			}
			if(sb != null && sb.length()>1){
				returnStr = sb.substring(0, sb.length()-1);
			}
			
			returnStr = " ,toolbar: { items: ["+returnStr+"]}";
		}
		
		
		return returnStr;
	}
	
	/**
	 * 根据页面名称获取当前页面ＩＤ　
	 * @param pageName
	 * @return
	 */
	public static String getPageId(String pageName)throws Exception{
		
		String hql = "FROM PcCdMenuT menu where menu.rightName = '"+pageName+"'";
		List<PcCdMenuT> menuList = null;
		try {
			menuList = functionMenuDao.searchMenu(hql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		if(menuList != null && menuList.size()>0){
			
			return menuList.get(0).getMenuid();
		}else{
			return "";
		}
	}
	
	/**
	 * 获取　GridJson　对象
	 * @param pageName 页面名称
	 * @param user　ｓｅｓｓｉｏｎ中ｕｓｅｒ
	 * @param gridColumns　用户没有设置页面布局时默认生成　表格字段的字符串
	 * @return
	 */
	@SuppressWarnings("unused")
	public static String getGridJson(String pageName,User user ,String gridColumns)throws Exception{
		String gridjson = "";
		String pageId = "";
		try {
			pageId = getPageId(pageName);
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		//获取功能按钮ＪＳＯＮ
		String functionStr = MenuAuthority(user, pageId);
		
//		String columnTitle = getColumn(user);
		String columnTitle = null;
		
		if(columnTitle != null && !"".equals(columnTitle)){
			gridjson = columnTitle;
			
		}else{
			gridjson = gridColumns;
			
		}
//		gridjson = ;
		
		return "{"+gridjson+functionStr+"}";
	}
	
	
	/**
	 * 获取　GridJson　对象
	 * @param pageName 页面名称
	 * @param user　ｓｅｓｓｉｏｎ中ｕｓｅｒ
	 * @param gridColumns　用户没有设置页面布局时默认生成　表格字段的字符串
	 * @return
	 */
	@SuppressWarnings("unused")
	public static String getGridJson1(String pageId,User user ,String gridColumns)throws Exception{
		String gridjson = "";
		
		//获取功能按钮ＪＳＯＮ
		String functionStr = MenuAuthority(user, pageId);
		
//		String columnTitle = getColumn(user);
		String columnTitle = null;
		
		if(columnTitle != null && !"".equals(columnTitle)){
			gridjson = columnTitle;
			
		}else{
			gridjson = gridColumns;
			
		}
//		gridjson = ;
		
		return "{"+gridjson+functionStr+"}";
	}
	
	/**
	 * 
	 * @param pageName
	 * @param user
	 * @param gridColumns
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	public static String getMenuJson(String pageName,User user )throws Exception{
		String menujson = "";
		String pageId = "";
		try {
			pageId = getPageId(pageName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONArray arr = new JSONArray();
		//获取功能按钮ＪＳＯＮ
//		String functionStr = MenuAuthority(user, pageId);
		List<PcCdRightT> usrRight = userAuthority(user);
		List<String> buttonAuthority = new ArrayList<String>();
		StringBuffer sb = new StringBuffer();
		if(usrRight != null && usrRight.size()>0){
			for(PcCdRightT entry : usrRight){
					
					if(entry != null && entry.getRightName() != null && !"不显示列名".equals(entry.getRightName()) 
							&&!"显示功能菜单".equals(entry.getRightName()) && !"组织节点显示".equals(entry.getRightName()) &&
							entry.getMenuid() != null && pageId.equals(entry.getMenuid())){
						
						buttonAuthority.add(entry.getRightName());
					}
				}
			}
		
		if(buttonAuthority != null && buttonAuthority.size()>0){
			for(int i =0;i<buttonAuthority.size();i++){
				JSONObject obj = new JSONObject();
				String buttone = buttonAuthority.get(i);
				String iconStr = "";
				String textstr = "";
//				1:添加操作
				if("添加".equals(buttone)){
					textstr = "添加节点";
					iconStr = "add";
//				2:删除操作;
				}else if("删除".equals(buttone)){
					textstr = "删除节点";
					iconStr = "delete";
//				3:修改操作;
				}else if("修改".equals(buttone)){
					textstr = "更新节点";
					iconStr = "modify";
//				4:查看操作;
				}else if("查询".equals(buttone)){
					textstr = "viewid";
					iconStr = "view";
//				5:权限分配;
				}
				sb.append("{ text: '"+textstr+"', click: itemclick, icon: '"+iconStr+"' },");
			}
			
			
			
		}
		sb.append("{ text: '重新加载', click: itemclick, icon: 'reload' },");
		sb.append("{ text: '打开连接', click: itemclick, icon: 'open' },");
		menujson = sb.substring(0, sb.length()-1);
		
		return "["+menujson+"]";
	}
	
	/**
	 * 
	 * @param pageId   页面ID
	 * @param user	session中USER   值为1，为拥有该权限
	 * @return
	 * @throws Exception
	 */
	public static JSONObject getButtonJson(String pageId,User user )throws Exception{
		
		JSONObject obj = new JSONObject();
		//获取功能按钮ＪＳＯＮ
		List<PcCdRightT> usrRight = userAuthority(user);
		List<String> buttonAuthority = new ArrayList<String>();
		if(usrRight != null && usrRight.size()>0){
			for(PcCdRightT entry : usrRight){
					
					if(entry != null && entry.getRightName() != null && !"不显示列名".equals(entry.getRightName()) 
							&&!"显示功能菜单".equals(entry.getRightName()) && !"组织节点显示".equals(entry.getRightName()) &&
							entry.getMenuid() != null && pageId.equals(entry.getMenuid())){
						
						buttonAuthority.add(entry.getRightName());
					}
				}
			}
		
		if(buttonAuthority != null && buttonAuthority.size()>0){
			for(int i =0;i<buttonAuthority.size();i++){
				String buttone = buttonAuthority.get(i);
//				1:添加操作
				if("添加".equals(buttone)){
					obj.put("add", "1");
//				2:删除操作;
				}else if("删除".equals(buttone)){
					obj.put("delete", "1");
//				3:修改操作;
				}else if("修改".equals(buttone)){
					obj.put("modify", "1");
//				4:查看操作;
				}else if("查询".equals(buttone)){
					obj.put("view", "1");
//				5:审核;
				}else if("审核".equals(buttone)){
					obj.put("audit", "1");
//					5:空值修改;
				}else if("空值修改".equals(buttone)){
					obj.put("nullmodify", "1");
				}
//				arr.add(obj);
			}
			
			
			
		}
		return obj;
	}
//	/ **
//	 * 获取页面表格显示列名基本信息
//	 * @param user
//	 * @return
//	 */
//	public static String getColumn(User user){
//		String gridJson = "";
//		String hql = "FROM TbBasicconfiguration bf WHERE 1=1 ";
//		if(user.getUserid() != null && !"".equals(user.getUserid())){
//			
//			hql += " AND fb.userid = '"+user.getUserid()+"'";
//		}
//		
////		TbBasicconfiguration basiccfg = getBasiccfg(hql);
//		//从数据库中获取数据不为空　根据用户设置基本信息　重新组织表要显示列名
//		if(basiccfg != null ){
//			//TODO..
//		//用户没有设置页面基本信息，系统默认生成。	
//			gridJson = "";
//		}
//		
//		return gridJson;
//	}
	
//	public static TbBasicconfiguration getBasiccfg(String hql){
//		
//		return null;
//	}
	
	public static int Unlock(String userid) throws Exception{
		int flg = 0;
		String sql = "update pc_cd_user_t set OPER_SDBSADBSE = '' where USERID='"+userid+"'";
		flg = commonDao.updateButhSql(sql);
		return flg;
	}
	public static String getMenuNameById (String id ,List<PcCdMenuT> menuList){
		String menuName = "";
		if(menuList != null && menuList.size()>0){
			
			for(PcCdMenuT entry : menuList){
				if(id.equals(entry.getMenuid())){
					menuName = entry.getRightName();
				}
			}
		}
		
		return menuName;
	}
	
	
	public static String getMenuNameByName (String name ,List<PcCdMenuT> menuList){
		String menuid = "";
		if(menuList != null && menuList.size()>0){
			
			for(PcCdMenuT entry : menuList){
				if(name.equals(entry.getRightName())){
					menuid = entry.getMenuid();
				}
			}
		}
		
		return menuid;
	}
	
}
