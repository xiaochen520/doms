package com.echo.service.impl;

 
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.dao.RightDao;
import com.echo.dao.RoleDao;
import com.echo.dto.MenuRoles;
import com.echo.dto.PcCdRightT;
import com.echo.dto.PcCdRoleT;
import com.echo.service.RoleService;

public class RoleServiceImpl implements RoleService{
	
	private RoleDao roleDao;
	private RightDao rightDao;


	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}


public void setRightDao(RightDao rightDao) {
		this.rightDao = rightDao;
	}


	//
//	public JSONArray searchRole(String rolename,String roleid) {
//		JSONArray jsonArr = null ;
//		String hql = "FROM TbRole R WHERE 1=1 and R.enabled = 0 and  R.rolename !='超级管理员' ";
//		if(rolename != null && !"".equals(rolename)){
//			hql += "AND R.rolename like '%"+rolename+"%'";
//		}
//		
//		if(roleid != null && !"".equals(roleid)){
//			hql += "AND R.roleid = '"+roleid+"'";
//		}
//		
//		List<PcCdRoleT> roleList = roleDao.searchRole(hql);
//		
//		if(roleList != null && roleList.size()>0){
//			
//			//生成树节点json文档
//			jsonArr = new JSONArray();   
//			
//			for(PcCdRoleT entry  : roleList){
//				JSONObject tjo = new JSONObject();
//				tjo.put("ROLEID", entry.getRoleid());
//				tjo.put("ROLE",entry.getRole());
//				tjo.put("ROLENAME", entry.getRolename());
//				tjo.put("ROLEDESCRIBED",entry.getRoledescribed());
//				tjo.put("BZ", entry.getBz());
//				Set<TbRight> tbRights = entry.getTbRights();
//				StringBuffer sb = new StringBuffer();
//				if(tbRights != null && tbRights.size()>0){
//					 Iterator it = tbRights.iterator();
//					 while(it.hasNext()){
//						 TbRight right = (TbRight)it.next();
//							 //过滤获取所有用户权限
//							sb.append(right.getRightid()+",");
//						 }
//				}
//				if(sb != null && sb.length()>0){
//					tjo.put("RIGHTID",sb.substring(0, sb.length()-1));
//				}else{
//					tjo.put("RIGHTID","");
//				}
//				
//				jsonArr.add(tjo);
//				
//			}
//		}
//		return jsonArr;
//	}
//
//	
//	
	public List<PcCdRightT> searchRightByIds(String[] rightNames) throws Exception{
		List<PcCdRightT> roles = null;
		
		if(rightNames != null && rightNames.length >0){
			StringBuffer sb = new StringBuffer();
			String roleStr = "";
			for(String rolename : rightNames){
				sb.append("'"+rolename+"',");
			}
			if(sb != null && sb.length()>0){
				roleStr = sb.substring(0, sb.length()-1);
			}
			String hql = "FROM PcCdRightT R WHERE 1=1 and R.rightid in ("+roleStr+") ";
			roles = rightDao.searchRight(hql);
			
		}
		
		return roles;
	}
//
//	public JSONArray searchmenu(){
//		List<TbRight> rightlist = null;
//		//String hql = "FROM TbFunctionmenu f WHERE 1=1 and f.menuurl is not null";
//		String hql = "FROM TbRight r WHERE 1=1 and r.enabled = 0";
//		rightlist = rightDao.searchRight(hql);
//		JSONArray jsonArr = null;
//		if(rightlist != null && rightlist.size()>0){
//			//生成树节点json文档
//			jsonArr = new JSONArray();   
//			
//			for(TbRight entry  : rightlist){
//					JSONObject tjo = new JSONObject();
//					tjo.put("RIGHTID", entry.getRightid());
//					tjo.put("RIGHTDESCRIBE",entry.getRightdescribe());
//					jsonArr.add(tjo);
//					
//			}
//		}
//		return jsonArr;
//		
//	}
//
//
//
	public boolean addRole(PcCdRoleT role) throws Exception{
		
		return roleDao.save(role);
	}
//
//
//
	public boolean removeUser(String roleId) throws Exception{
		boolean deleteFlg = true;
		
		String hql = "FROM PcCdRoleT r WHERE 1=1 and r.roleid='"+roleId+"'";
		List<PcCdRoleT> roleList = roleDao.searchRole(hql);
		String[] sqls = new String[3];
		if(roleList != null && roleList.size() >0){
			
			if((roleList.get(0).getPcCdUserTs()!= null && roleList.get(0).getPcCdUserTs().size()>0)){
				deleteFlg = false;
			}else{
				
				sqls[0] = " delete from pc_cd_user_role_t ur where 1=1 and ur.roleid='"+roleId+"'";
				sqls[1] = " delete from pc_cd_role_right_t rr where 1=1 and rr.roleid='"+roleId+"'";
				sqls[2] = " delete from pc_cd_role_t r where 1=1 and r.roleid='"+roleId+"'";
				deleteFlg = roleDao.removeRole(sqls);
				
			}
			
		}
		return deleteFlg;
	}

	public boolean updateRole(PcCdRoleT role) throws Exception{
		return roleDao.updateRole(role);
	}



	public List<PcCdRoleT> searchRole(String roleid,String rolename) throws Exception{
		String hql = "FROM PcCdRoleT R WHERE 1=1 ";
		
		if(roleid != null && !"".equals(roleid)){
			hql += "AND R.roleid = '"+roleid+"'";
		}
		
		if(rolename != null && !"".equals(rolename)){
			
			hql += "AND R.roleName = '"+rolename+"'";
		}
		
		List<PcCdRoleT> roleList = roleDao.searchRole(hql);
		
		return roleList;
	}




	

	/**
	 * 组织更新权限JSON数据
	 */
	public JSONArray searchRoleInfo( String roleid) throws Exception{

		List<PcCdRightT> rightlist = null;
		List<String> rightchick = new ArrayList<String>();;
		String hqlall = "FROM PcCdRightT  WHERE 1=1 ";
		rightlist = rightDao.searchRight(hqlall);
		//显示功能菜单权限
		JSONArray jsonArrXIANSHI = new JSONArray(); 
		//页面布局权限
		JSONArray jsonArrBUJU = new JSONArray(); 
		//添加权限
		JSONArray jsonArrTIANJIA = new JSONArray(); 
		//删除权限
		JSONArray jsonArrSHANCHU = new JSONArray(); 
		//更新权限
		JSONArray jsonArrGENGXIN = new JSONArray(); 
		//查询权限
		JSONArray jsonArrCHAXUN = new JSONArray(); 
		//其他权限
		JSONArray jsonArrQITA = new JSONArray(); 
		
		JSONArray jsonArr = null;

		JSONArray roleJsonArr = null ;
		String hql = "FROM PcCdRoleT r WHERE 1=1  ";
		
		if(roleid != null && !"".equals(roleid)){
			hql += " AND r.roleid = '"+roleid+"'";
		}
		
		List<PcCdRoleT> roleList = roleDao.searchRole(hql);
		
		if(roleList != null && roleList.size()>0){
			jsonArr = new JSONArray();
			//生成树节点json文档
			roleJsonArr = new JSONArray();   
			
			for(PcCdRoleT entry  : roleList){
				JSONObject tjo = new JSONObject();
				tjo.put("ROLEID", entry.getRoleid());
				tjo.put("ROLE_NAME", entry.getRoleName());
				tjo.put("ROLE_DESCRIBED", entry.getRoleDescribed());
			
				Set<PcCdRightT> rights = entry.getPcCdRightTs();
				StringBuffer sb = new StringBuffer();
				if(rights != null && rights.size()>0){
					 Iterator it = rights.iterator();
					 while(it.hasNext()){
						 PcCdRightT right = (PcCdRightT)it.next();
							 //过滤获取所有用户权限
						 	rightchick.add(right.getRightid());
							sb.append(right.getRightid()+",");
						 }
				}
				
				if(sb != null && sb.length()>0){
					tjo.put("RIGHTID",sb.substring(0, sb.length()-1));
				}else{
					tjo.put("RIGHTID","");
				}
				
				roleJsonArr.add(tjo);
				
			}
			int rightchicksize = rightchick.size();
			if(rightlist != null && rightlist.size()>0){
				//生成树节点json文档
				
				for(PcCdRightT entry  : rightlist){
						JSONObject tjo = new JSONObject();
						tjo.put("RIGHTID", entry.getRightid());
						tjo.put("RIGHTDESCRIBE",entry.getRemark());
						tjo.put("RIGHTCHICKED",0);	
						if(rightchicksize > 0){
							for(String id :rightchick){
								if(id.equals(entry.getRightid())){
									//以有权限被选中
									tjo.put("RIGHTCHICKED",1);	
								}
							}
						}
						if("显示功能菜单".equals(entry.getRightName())){
							jsonArrXIANSHI.add(tjo);
						}else if("页面布局".equals(entry.getRightName())){
							jsonArrBUJU.add(tjo);
						}else if("添加".equals(entry.getRightName())){
							jsonArrTIANJIA.add(tjo);
						}else if("删除".equals(entry.getRightName())){
							jsonArrSHANCHU.add(tjo);
						}else if("修改".equals(entry.getRightName())){
							jsonArrGENGXIN.add(tjo);
						}else if("查询".equals(entry.getRightName())){
							jsonArrCHAXUN.add(tjo);
						}else{
							jsonArrQITA.add(tjo);
							
						}
						
				}
				JSONObject tjo = new JSONObject();
				tjo.put("ROLEJSON", roleJsonArr);
				tjo.put("JSONARRXIANSHI",jsonArrXIANSHI);
				tjo.put("JSONARRBUJU",jsonArrBUJU);
				tjo.put("JSONARRTIANJIA",jsonArrTIANJIA);
				tjo.put("JSONARRSHANCHU",jsonArrSHANCHU);
				tjo.put("JSONARRGENGXIN",jsonArrGENGXIN);
				tjo.put("JSONARRCHAXUN",jsonArrCHAXUN);
				tjo.put("JSONARRQITA",jsonArrQITA);
				jsonArr.add(tjo);
			}
		}
		return jsonArr;
	
	}
	
	/**
	 * 整理角色在菜单中拥有权限  list 0:menuid 1：menuname 2：添加
	 */
	/**
	 * 组织更新权限JSON数据
	 */
	public JSONObject searchAccRoles( String roleid) throws Exception{

		List<String> rightchick = new ArrayList<String>();;
		//显示功能菜单权限
		int chkflga = 0;
		JSONObject orttjo = new JSONObject();
		JSONArray jsonArr = null;
//		String josnMENUID = "";
//		String josnMENUNAME = "";
//		String josnMENURIGHTS = "";

		JSONArray roleJsonArr = null ;
		String allrightssql = "select m.menuid as menuid ,m.right_name as menuname,ri.right_name as rightname,ri.remark as remark,ri.rightid as rightid "+
					 "from  pc_cd_right_t ri "+
					 "left join pc_cd_menuall_v m on ri.menuid = m.menuid "+
					 "order by m.menu_order,ri.disorder";
		
		String rolerightsql = "select m.menuid as menuid ,m.right_name as menuname,ri.right_name as rightname,ri.remark as remark,ri.rightid as rightid ,r.role_name as rolename,r.roleid as roleid "+
								"from pc_cd_role_t r "+
								"left join pc_cd_role_right_t rr on rr.roleid = r.roleid "+
								"left join pc_cd_right_t ri on ri.rightid = rr.rightid "+
								"left join pc_cd_menuall_v m on ri.menuid = m.menuid "+
								"where r.roleid = '"+roleid+"' "+
								"order by m.menu_order,ri.disorder";
		
		List<String> cloumnsName = new ArrayList<String>();
		cloumnsName.add("menuid");
		cloumnsName.add("menuname");
		cloumnsName.add("rightname");
		cloumnsName.add("remark");
		cloumnsName.add("rightid");
		cloumnsName.add("rolename");
		cloumnsName.add("roleid");
		//cloumnsName.add("DISORDER");

		List<Object[]> roleList = roleDao.searchRoles(rolerightsql,cloumnsName);
		
		if(roleList != null && roleList.size()>0){
			jsonArr = new JSONArray();
			//生成树节点json文档
			roleJsonArr = new JSONArray();  
			
			JSONObject tjo = new JSONObject();
			orttjo.put("ROLEID", roleList.get(0)[6]);
			orttjo.put("ROLE_NAME", roleList.get(0)[5]);
			if(roleList != null && roleList.size()>0){
				for(Object[] entry  : roleList){
					StringBuffer sb = new StringBuffer();
						 //过滤获取所有用户权限
					 	rightchick.add(String.valueOf(entry[4]));
						sb.append(entry[4]+",");
					
					if(sb != null && sb.length()>0){
						orttjo.put("RIGHTID",sb.substring(0, sb.length()-1));
					}else{
						orttjo.put("RIGHTID","");
					}
					
					
				}
				
			}
			
			List<String> cloumnsNames = new ArrayList<String>();
			cloumnsName.add("menuid");
			cloumnsName.add("menuname");
			cloumnsName.add("rightname");
			cloumnsName.add("remark");
			cloumnsName.add("rightid");
			//cloumnsName.add("DISORDER");
			List<Object[]> rightslist = roleDao.searchRoles(allrightssql,cloumnsNames);
			JSONArray menuRights = new JSONArray();
			JSONObject menuobj = new JSONObject();
			if(rightslist != null && rightslist.size()>0){
				String meunid = String.valueOf(rightslist.get(0)[0]) ;
				
				for(int i = 0;i < rightslist.size(); i ++){
					if(i == rightslist.size() -2){
						System.out.println('1');
					}
					//System.out.println("循环开始menuid："+meunid);
					menuobj = new JSONObject();
					tjo = new JSONObject();
					chkflga = 0;
					
					if(rightslist.get(i)[0] != null && meunid.equals(rightslist.get(i)[0]) ){
						//判断当前菜单ID 与上一次菜单ID是否相同，相同为同一菜单下权限
						menuobj.put("RIGHTID", rightslist.get(i)[4]);
						menuobj.put("RIGHTNAME", rightslist.get(i)[2]);
						menuobj.put("REMARK", rightslist.get(i)[3]);
						for(String roleri :rightchick){
							if(rightslist.get(i)[4].equals(roleri)){
								chkflga = 1;
								break;
							}
						}
						menuobj.put("CHECKED", chkflga);
						menuRights.add(menuobj);
						
					}
					
					//最后一次循环
					if(i == rightslist.size()-1){
						tjo.put("MENUID", rightslist.get(i-1)[0]);
						tjo.put("MENUNAME", rightslist.get(i-1)[1]);
						tjo.put("MENURIGHTS", menuRights);
						roleJsonArr.add(tjo);
						menuRights = new JSONArray();
						menuobj.put("RIGHTID", rightslist.get(i)[4]);
						menuobj.put("RIGHTNAME", rightslist.get(i)[2]);
						menuobj.put("REMARK", rightslist.get(i)[3]);
						for(String roleri :rightchick){
							if(rightslist.get(i)[4].equals(roleri)){
								chkflga = 1;
								break;
							}
						}
						menuobj.put("CHECKED", chkflga);
						menuRights.add(menuobj);
						tjo.put("MENUID", rightslist.get(i)[0]);
						tjo.put("MENUNAME", rightslist.get(i)[1]);
						tjo.put("MENURIGHTS", menuRights);
						
						roleJsonArr.add(tjo);
						
					}else{
						if(rightslist.get(i)[0] != null && !meunid.equals(rightslist.get(i)[0]) ){
							tjo.put("MENUID", rightslist.get(i-1)[0]);
							tjo.put("MENUNAME", rightslist.get(i-1)[1]);
							tjo.put("MENURIGHTS", menuRights);
							menuRights = new JSONArray();
							meunid = String.valueOf(rightslist.get(i)[0]) ;
							//System.out.println("menuid赋值后："+meunid);
							roleJsonArr.add(tjo);
							//判断当前菜单ID 与上一次菜单ID是否相同，相同为同一菜单下权限
							menuobj.put("RIGHTID", rightslist.get(i)[4]);
							menuobj.put("RIGHTNAME", rightslist.get(i)[2]);
							menuobj.put("REMARK", rightslist.get(i)[3]);
							for(String roleri :rightchick){
								if(rightslist.get(i)[4].equals(roleri)){
									chkflga = 1;
									break;
								}
							}
							menuobj.put("CHECKED", chkflga);
							menuRights.add(menuobj);
						}
					}					
				}
				
			}
			//System.out.println(roleJsonArr.size());
			orttjo.put("RIGHTSIN", roleJsonArr);
			
			
		}
		return orttjo;	
	}
//
//
//
//	public JSONArray searchAllRight() {
//
//		List<TbRight> rightlist = null;
//		String hqlall = "FROM TbRight r WHERE 1=1 and r.enabled = 0";
//		rightlist = rightDao.searchRight(hqlall);
//		//显示功能菜单权限
//		JSONArray jsonArrXIANSHI = new JSONArray(); 
//		//页面布局权限
//		JSONArray jsonArrBUJU = new JSONArray(); 
//		//添加权限
//		JSONArray jsonArrTIANJIA = new JSONArray(); 
//		//删除权限
//		JSONArray jsonArrSHANCHU = new JSONArray(); 
//		//更新权限
//		JSONArray jsonArrGENGXIN = new JSONArray(); 
//		//查询权限
//		JSONArray jsonArrCHAXUN = new JSONArray(); 
//		//其他权限
//		JSONArray jsonArrQITA = new JSONArray(); 
//		
//		JSONArray jsonArr = new JSONArray();
//		if(rightlist != null && rightlist.size()>0){
//			//生成树节点json文档
//			
//			for(TbRight entry  : rightlist){
//					JSONObject tjo = new JSONObject();
//					tjo.put("RIGHTID", entry.getRightid());
//					tjo.put("RIGHTDESCRIBE",entry.getRightdescribe());
//					tjo.put("RIGHTCHICKED",0);	
//				
//					if("显示功能菜单".equals(entry.getRightname())){
//						jsonArrXIANSHI.add(tjo);
//					}else if("页面布局".equals(entry.getRightname())){
//						jsonArrBUJU.add(tjo);
//					}else if("添加".equals(entry.getRightname())){
//						jsonArrTIANJIA.add(tjo);
//					}else if("删除".equals(entry.getRightname())){
//						jsonArrSHANCHU.add(tjo);
//					}else if("修改".equals(entry.getRightname())){
//						jsonArrGENGXIN.add(tjo);
//					}else if("查询".equals(entry.getRightname())){
//						jsonArrCHAXUN.add(tjo);
//					}else{
//						jsonArrQITA.add(tjo);
//					}
//			}
//			JSONObject tjo = new JSONObject();
//			tjo.put("JSONARRXIANSHI",jsonArrXIANSHI);
//			tjo.put("JSONARRBUJU",jsonArrBUJU);
//			tjo.put("JSONARRTIANJIA",jsonArrTIANJIA);
//			tjo.put("JSONARRSHANCHU",jsonArrSHANCHU);
//			tjo.put("JSONARRGENGXIN",jsonArrGENGXIN);
//			tjo.put("JSONARRCHAXUN",jsonArrCHAXUN);
//			tjo.put("JSONARRQITA",jsonArrQITA);
//			jsonArr.add(tjo);
//		}
//		return jsonArr;
//	
//	
//	}


	@Override
	public List<Object[]> searchRole(String rolename) throws Exception {
		String hql = "select r.ROLEID,r.ROLE_NAME,r.ROLE_DESCRIBED,r.REMARK,r.RLAST_OPER,TO_CHAR(r.RLAST_ODATE , 'YYYY-MM-DD HH24:MI:SS') AS RLAST_ODATE FROM pc_cd_role_t r WHERE 1=1 ";
		
		if(rolename != null && !"".equals(rolename)){
			
			hql += "AND r.role_name like '%"+rolename+"%'";
		}
		
		List<Object[]> roleList = roleDao.searchRoles(hql);
		
		return roleList;
	}


	@Override
	public JSONArray RoleAccredit(String roleid) throws Exception {

		JSONArray arr = null;
		JSONObject obj = null;
		List<MenuRoles> lists = null;
		//获取所有菜单权限
		String allmenurightsql = "select m.menuid,m.right_name as menuname,r.rightid,r.right_name ,m.father_menu_code from pc_cd_menu_t m"+
								 "left join pc_cd_right_t r  on m.menuid = r.menuid order by m.menu_order ,r.disorder";
		
		String rolerightsql = " select * from pc_cd_role_right_t where roleid= '"+roleid+"'";
		String menuid ="";
		List<Object[]>  allmenurights = null;
		List<Object[]>  rolerights = null;
		try {
			allmenurights = roleDao.searchRoles(allmenurightsql);
			rolerights = roleDao.searchRoles(rolerightsql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(allmenurights != null && allmenurights.size()>0){
		
			lists =getMenuRoles(allmenurights,rolerights);
		}
		
		if(lists != null && lists.size()>0){
			
		}
		return null;
	}

	public List<MenuRoles> getMenuRoles(List<Object[]> allmenurights,List<Object[]> rolerights){
		List<MenuRoles> MenuRoless = new ArrayList<MenuRoles>();
		MenuRoles menuroles = null;
		String menuid = "";
		
		String roleflg = "";
		if(allmenurights != null && allmenurights.size()>0){
			menuroles = new MenuRoles();
			menuid = allmenurights.get(0)[0].toString();
			for(int i = 0; i<allmenurights.size();i++){
				roleflg = "";
					if(rolerights != null && rolerights.size()>0){
						for(int j = 0; j<rolerights.size(); j++){
							if(allmenurights.get(i)[2].toString().equals(rolerights.get(j)[1].toString())){
								
								roleflg = rolerights.get(j)[0].toString();
							}
						}
					}
					
					//判断menuid 是否相等
					if(menuid.equals(allmenurights.get(i)[0].toString())){
						menuroles.setMenuid(allmenurights.get(i)[0].toString());
						menuroles.setMenuname(allmenurights.get(i)[1].toString());
						menuroles.setPid(allmenurights.get(i)[2].toString());
						menuroles.setMenuorder(allmenurights.get(i)[3].toString());
					
						
					
						if("显示功能菜单".equals(allmenurights.get(i)[5].toString())){
							menuroles.setDispalyrightid(allmenurights.get(i)[5].toString());
							if(!"".equals(roleflg)){
								menuroles.setDispalyroleid(roleflg);
							}
						}else if("添加".equals(allmenurights.get(i)[5].toString())){
							menuroles.setAddrightid(allmenurights.get(i)[5].toString());
							if(!"".equals(roleflg)){
								menuroles.setAddroleid(roleflg);
							}
						}else if("修改".equals(allmenurights.get(i)[5].toString())){
							menuroles.setModifyrightid(allmenurights.get(i)[5].toString());
							if(!"".equals(roleflg)){
								menuroles.setModifyroleid(roleflg);
							}
						}else if("空值修改".equals(allmenurights.get(i)[5].toString())){
							menuroles.setNullmodifyrightid(allmenurights.get(i)[5].toString());
							if(!"".equals(roleflg)){
								menuroles.setNullmodifyroleid(roleflg);
							}
						}else if("删除".equals(allmenurights.get(i)[5].toString())){
							menuroles.setDeleterightid(allmenurights.get(i)[5].toString());
							if(!"".equals(roleflg)){
								menuroles.setDeleteroleid(roleflg);
							}
						}else if("审核".equals(allmenurights.get(i)[5].toString())){
							menuroles.setAuditrightid(allmenurights.get(i)[5].toString());
							if(!"".equals(roleflg)){
								menuroles.setAuditroleid(roleflg);
							}
						}else if("授权".equals(allmenurights.get(i)[5].toString())){
							menuroles.setAccreditrightid(allmenurights.get(i)[5].toString());
							if(!"".equals(roleflg)){
								menuroles.setAccreditroleid(roleflg);
							}
						}else if("注汽分配".equals(allmenurights.get(i)[5].toString())){
							menuroles.setAllocationrightid(allmenurights.get(i)[5].toString());
							if(!"".equals(roleflg)){
								menuroles.setAllocationroleid(roleflg);
							}
						}else if("报表生成".equals(allmenurights.get(i)[5].toString())){
							menuroles.setReportrightid(allmenurights.get(i)[5].toString());
							if(!"".equals(roleflg)){
								menuroles.setReportroleid(roleflg);
							}
						}
						
					}else{
						menuid = allmenurights.get(i)[0].toString();
						MenuRoless.add(menuroles);
						menuroles = new MenuRoles();
						
						menuroles.setMenuid(allmenurights.get(i)[0].toString());
						menuroles.setMenuname(allmenurights.get(i)[1].toString());
						menuroles.setPid(allmenurights.get(i)[2].toString());
						menuroles.setMenuorder(allmenurights.get(i)[3].toString());
					
						if("显示功能菜单".equals(allmenurights.get(i)[5].toString())){
							menuroles.setDispalyrightid(allmenurights.get(i)[5].toString());
							if(!"".equals(roleflg)){
								menuroles.setDispalyroleid(roleflg);
							}
						}else if("添加".equals(allmenurights.get(i)[5].toString())){
							menuroles.setAddrightid(allmenurights.get(i)[5].toString());
							if(!"".equals(roleflg)){
								menuroles.setAddroleid(roleflg);
							}
						}else if("修改".equals(allmenurights.get(i)[5].toString())){
							menuroles.setModifyrightid(allmenurights.get(i)[5].toString());
							if(!"".equals(roleflg)){
								menuroles.setModifyroleid(roleflg);
							}
						}else if("空值修改".equals(allmenurights.get(i)[5].toString())){
							menuroles.setNullmodifyrightid(allmenurights.get(i)[5].toString());
							if(!"".equals(roleflg)){
								menuroles.setNullmodifyroleid(roleflg);
							}
						}else if("删除".equals(allmenurights.get(i)[5].toString())){
							menuroles.setDeleterightid(allmenurights.get(i)[5].toString());
							if(!"".equals(roleflg)){
								menuroles.setDeleteroleid(roleflg);
							}
						}else if("审核".equals(allmenurights.get(i)[5].toString())){
							menuroles.setAuditrightid(allmenurights.get(i)[5].toString());
							if(!"".equals(roleflg)){
								menuroles.setAuditroleid(roleflg);
							}
						}else if("授权".equals(allmenurights.get(i)[5].toString())){
							menuroles.setAccreditrightid(allmenurights.get(i)[5].toString());
							if(!"".equals(roleflg)){
								menuroles.setAccreditroleid(roleflg);
							}
						}else if("注汽分配".equals(allmenurights.get(i)[5].toString())){
							menuroles.setAllocationrightid(allmenurights.get(i)[5].toString());
							if(!"".equals(roleflg)){
								menuroles.setAllocationroleid(roleflg);
							}
						}else if("报表生成".equals(allmenurights.get(i)[5].toString())){
							menuroles.setReportrightid(allmenurights.get(i)[5].toString());
							if(!"".equals(roleflg)){
								menuroles.setReportroleid(roleflg);
							}
						}
						
					}
//				}
				
				
			}
			
			MenuRoless.add(menuroles);
			
		}
		
		
		return MenuRoless;
		
	}
	
	
//	public MenuRoles setmenurole(MenuRoles menurole,List<Object[]>args,String rightid){
//		
//		if(args != null && args.size()>0){
//			for(Object[] obj : args){
//				if(rightid.equals(obj[4].toString())){
//					
//				}
//				
//			}
//			
//		}
//		return menurole;
//	}
}
