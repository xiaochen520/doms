package com.echo.service.impl;

 
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.hibernate.HibernateException;

import com.echo.beans.PageControl;
import com.echo.dao.CommonDao;
import com.echo.dao.PageDao;
import com.echo.dao.UserDao;
import com.echo.dto.PcCdUserT;
import com.echo.dto.User;
import com.echo.service.UserService;

public class UserServiceImpl implements UserService{
	private UserDao userDao;
//	private TbFunctionmenuDao tbFunctionmenuDao;
//	private PcdDao pcdDao;
	private PageDao pageDao;
	private CommonDao commonDao;
	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}

	public void setPageDao(PageDao pageDao) {
		this.pageDao = pageDao;
	}
//
//	public void setPcdDao(PcdDao pcdDao) {
//		this.pcdDao = pcdDao;
//	}
//
//	public void setTbFunctionmenuDao(TbFunctionmenuDao tbFunctionmenuDao) {
//		this.tbFunctionmenuDao = tbFunctionmenuDao;
//	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public List<PcCdUserT> searchUserByNamePas(String username,String password) throws Exception{
		List<PcCdUserT> userList = null;
		String hql = "FROM TbUser u WHERE 1=1 ";
		if(username != null && !"".equals(username) && password != null && !"".equals(password)){
			hql += "and u.username = '"+username+"' and u.password = '"+password+"'";
		}
		userList = userDao.searchUser(hql);
		return userList;
	}
	
	/**
	 * 	0：userid=用户ID
		1:DEPARTMENTID=部门ID
		2：oper_code=操作员代码
		3：oper_name=OPER_NAME
		4：oper_pass=口令
		5：oper_sdbsadbse=当前状态
		6：audbshor_date=注册日期
		7：sdbsadbse_date=状态改变日期
		8：l_login_date=最后登录时间
		9: l_logoudbs_date=最后退出时间
		10：rlast_oper=此记录上次操作者
		11：rlast_odate=此记录上次操作日期
		12：remark=备注
		13:roleid=角色ID
		14：role_name=角色名称
		15：department_name=部门名称
	 */
	public JSONObject searchUserByName(String department,String username, int pageNo, String sort,
			String order, int rowsPerpage) throws Exception{
		JSONArray jsonArr = null;
		JSONObject tjo = new JSONObject();
		List<Object[]> userList = null;
		String sqlcount = "select count(*) from pc_cd_user_t u  left join pc_cd_org_t d  on u.departmentid = d.org_id  where 1=1";
		String sqlwhere = "select u.userid userid, u.departmentid departmentid, u.oper_code oper_code, u.oper_name oper_name, u.oper_pass oper_pass, u.oper_sdbsadbse oper_sdbsadbse, to_char(u.audbshor_date,'YYYY-MM-DD hh24:mi:ss') as audbshor_date, to_char(u.sdbsadbse_date,'YYYY-MM-DD hh24:mi:ss') as sdbsadbse_date, to_char(u.l_login_date,'YYYY-MM-DD hh24:mi:ss') as l_login_date, to_char(u.l_logoudbs_date,'YYYY-MM-DD hh24:mi:ss') as l_logoudbs_date, u.rlast_oper rlast_oper, to_char(u.rlast_odate,'YYYY-MM-DD hh24:mi:ss') as rlast_odate, u.remark remark,"
						+"r.roleid as roleid ,r.role_name as rolename, "
						+" d.structurename as departmentname  from pc_cd_user_t u "
						+"	left join pc_cd_user_role_t ur on u.userid = ur.userid"
						+"	left join pc_cd_role_t r on r.roleid = ur.roleid"
						+"	left join pc_cd_org_t d  on u.departmentid = d.org_id  where 1=1 ";
		String where = "";
	
		if(username != null && !"".equals(username)){
			
			where = where+" and  u.oper_name like '%"+username+"%'";
		}
		
		if(department != null && !"".equals(department)){
			
			where = where+" and  d.structurename = '"+department+"'";
		}
		
		//获取总条数
		int total1= 0;
		total1 = pageDao.getCounts(sqlcount+where);
		//排序
		String orderby = "order by u.oper_code";
		if(!"".equals(sort)){
			
			if("ROLES".equals(sort)){
				
				orderby  = " order by r.role_name " +order;
			}else if("DEPARTMENT".equals(sort)){
				
				orderby = "  order by d.department_name " +order;
			}else{
				
				orderby = "  order by u."+ sort+" "+order;
			}
		}
		//计算分页
		PageControl control = new PageControl();
		//当前页
		control.setInt_num(rowsPerpage);
		control.init(pageNo, total1);
		//开始条数
		int start = control.getStart()-1;
		
		userList = userDao.searchUserNames(sqlwhere+where+orderby,start,rowsPerpage);
		
		
		if(userList != null && userList.size()>0){
			List<Object[]> newuser = this.usersAssociation(userList,"name");
			//获取采油站数据
			//生成树节点json文档
			jsonArr = new JSONArray();   
			
			for(Object[] entry  : newuser){
				//0：userid=用户ID
				tjo.put("USERID", entry[0]);
				//1:DEPARTMENTID=部门ID
				tjo.put("DEPID", entry[1]);
//				2：oper_code=操作员代码
				tjo.put("OPER_CODE",entry[2]);
//				3：oper_name=真实姓名
				tjo.put("OPER_NAME",entry[3]);
//				4：oper_pass=口令
				tjo.put("OPER_PASS",entry[4]);
//				5：oper_sdbsadbse=当前状态
				tjo.put("OPER_SDBSADBSE",entry[5]);
				//6：audbshor_date=注册日期
				if(entry[6] != null){
//					
					tjo.put("AUDBSHOR_DATE",String.valueOf(entry[6]));
				}else{
					tjo.put("AUDBSHOR_DATE","");
				}
				
//				7：sdbsadbse_date=状态改变日期
				if(entry[7] != null){
					tjo.put("SDBSADBSE_DATE",String.valueOf(entry[7]));
				}else{
					tjo.put("SDBSADBSE_DATE","");
				}
//				8：l_login_date=最后登录时间
				if(entry[8] != null){
					tjo.put("L_LOGIN_DATE",String.valueOf(entry[8]));
				}else{
					tjo.put("L_LOGIN_DATE","");
				}
				
//				9: l_logoudbs_date=最后退出时间
//				10：rlast_oper=此记录上次操作者
				tjo.put("RLAST_OPER",entry[10]);
//				11：rlast_odate=此记录上次操作日期
				if(entry[11] != null){
					tjo.put("RLAST_ODATE",String.valueOf(entry[11])); 
				}else{
					tjo.put("RLAST_ODATE",""); 
				}
				
//				12：remark=备注
				tjo.put("REMARK",entry[12]);
//				13:roleid=角色ID
				tjo.put("ROLEID",entry[13]);
//				14：role_name=角色名称
				tjo.put("ROLES",entry[14]);
//				16：department_name=部门名称
				tjo.put("DEPARTMENT",entry[15]);
			
				
				jsonArr.add(tjo);
				
			}
			
		}
		
		tjo = new JSONObject();
		tjo.put("Rows",jsonArr);
		tjo.put("Total",total1);
		
		return tjo;
		
	}
	/**
	 * 从新组织用户信息，将多个权限拼接字符串
	 * @param userinfo
	 * @return
	 */
	@SuppressWarnings("unused")
	public List<Object[]> usersAssociation(List<Object[]> userinfo,String flgs){
		List<Object[]> newuser = new ArrayList<Object[]>();
		List<List<Object[]>> usergourp = new ArrayList<List<Object[]>>();
		Object[] zhnc = null; 
		boolean flg = true;
		//int leg = userinfo.get(0).length;
		String userid = "";
		//List<Integer> meny = new ArrayList<Integer>();
		String rolename = "";
		String roleid = "";
		for(int i = 0;i<userinfo.size();i++){
			userid = String.valueOf(userinfo.get(i)[0]);
			rolename = String.valueOf(userinfo.get(i)[14])+"|";
			roleid  = String.valueOf(userinfo.get(i)[13])+";";
			for( int j = i+1; j<userinfo.size(); j ++){
				
				if(userinfo.get(i)[0].equals(userinfo.get(j)[0])){
					
					//meny.add(j);
					rolename += String.valueOf(userinfo.get(j)[14]) +"|";
					roleid += String.valueOf(userinfo.get(j)[13]) +";";
				}
			}
			
			
			rolename = rolename.substring(0, rolename.length()-1);
			roleid = roleid.substring(0, roleid.length()-1);
			zhnc = userinfo.get(i);
			zhnc[13] = roleid;
			zhnc[14] = rolename;
			
//			if(meny != null && meny.size()>0){
//					for(Integer flg : meny){
//						if(i != flg){
//							newuser.add(zhnc);
//						}
//						
//					}
//				
//				}else{
			if(newuser != null && newuser.size()>0){
				flg = true;
				for(int z=0 ;z<newuser.size();z++){
					if(newuser.get(z)[0].equals(userid)){
						flg = false;
					}
				}
				
				if(flg){
					newuser.add(zhnc);
				}
				
			}else{
				newuser.add(zhnc);
			}
				
//				}
			
			
			}
		
		return newuser;
	}

	
	
//	public JSONArray searchUserByName(String username){
//		JSONArray jsonArr = null;
//		List<TbUser> userList = null;
//		String hql = "FROM TbUser u WHERE 1=1 and u.enabled = 0 ";
//		if(username != null && !"".equals(username)){
//			hql = hql+" and u.username = '"+username+"'";
//		}
//		userList = userDao.searchUserByName(hql);
//		
//		if(userList != null && userList.size()>0){
//			//获取组织结构数据
//			String tbStructureHql  = "FROM TbStructure ts where ts.enabled = 0 order by ts.treeorder";
//			List<TbStructure> tbStructurelist = tbFunctionmenuDao.searchStructure(tbStructureHql);
//			String pcdhql = "FROM Dbat0053 d53 where 1=1 and d53.enabled = 0";
//			
//			List<Dbat0053> pcdList =  pcdDao.searchPcd(pcdhql);
//			//获取采油站数据
//			//生成树节点json文档
//			jsonArr = new JSONArray();   
//			
//			for(TbUser entry  : userList){
//				JSONObject tjo = new JSONObject();
//				
//				tjo.put("USERID", entry.getUserid());
//				tjo.put("USERNAME",entry.getUsername());
//				tjo.put("REALNAME",entry.getRealname());
//				if(entry != null && entry.getSex() == 0){
//					tjo.put("SEX","女");
//				}else{
//					tjo.put("SEX","男");
//				}
//				String jionDate = "";
//				if(entry.getJionDate() != null){
//					jionDate = String.valueOf(entry.getJionDate()).substring(0, 19);
//				}
//				tjo.put("JOINEDDATE",jionDate);
//				
//				String registerTime = "";
//				if(entry.getRegisterTime() != null){
//					registerTime = String.valueOf(entry.getRegisterTime()).substring(0, 19);
//				}
//				tjo.put("REGISTERTIME",registerTime);
//				
//				StringBuffer sb = new StringBuffer();
//				 Set<TbRole> roles = entry.getTbRoles();
//				 if(roles != null && roles.size()>0){
//					 Iterator it = roles.iterator();
//					 while(it.hasNext()){
//							TbRole role = (TbRole)it.next();
//							 //过滤获取所有用户权限
//							sb.append(role.getRolename()+"|");
//						 }
//				 }
//				 if(sb != null && !"".equals(sb) && sb.length()>0){
//					 tjo.put("ROLES",sb.substring(0, sb.length()-1));
//				 }else{
//					 tjo.put("ROLES","");
//				 }
//				 
//				 if(entry.getStructureid() != null){
//					 
//					 if(tbStructurelist != null && tbStructurelist.size()>0){
//						 
//						 for(TbStructure ts : tbStructurelist){
//							 if(entry.getStructureid().equals(ts.getStructureid())){
//								 tjo.put("STRUCTURENAME", ts.getStructurename());
//							 }
//						 }
//					 }else{
//						 tjo.put("STRUCTURENAME", "");  
//					 }
//				 }else{
//					 tjo.put("STRUCTURENAME", ""); 
//				 }
//				 
//				 if(entry.getPcdid() != null && !"".equals(entry.getPcdid())){
//					 String pcdname = "";
//					 if(pcdList != null && pcdList.size()>0){
//						 for(Dbat0053 d53 : pcdList){
//							 if(entry.getPcdid().endsWith(d53.getPcdid())){
//								 pcdname = d53.getP01();
//							 }
//						 }
//						
//					 }
//					 tjo.put("PCDNAME",pcdname); 
//				 }else{
//					 tjo.put("PCDNAME","");
//				 }
//				 
//				 if(entry.getLastLoginTime() != null){
//					 tjo.put("LASTLOGINTIME",String.valueOf(entry.getLastLoginTime()).substring(0, 19)); 
//				 }else{
//					 tjo.put("LASTLOGINTIME",""); 
//				 }
//				 
//				 if(entry.getBz() != null){
//					 tjo.put("BZ",entry.getBz()); 
//				 }else{
//					 tjo.put("BZ","");
//				 }
//				jsonArr.add(tjo);
//				
//			}
//			
//		}
//		
//		return jsonArr;
//		
//	}
//	
	
	public JSONArray searchUserByid(String userid)throws Exception{
		JSONArray jsonArr = null;
		List<Object[]> userList = null;
//		String hql = "FROM TbUser u WHERE 1=1 and u.enabled = 0 ";
		String hql = "select u.userid as userid,r.rolename,p.P01 as pcdname,s.STRUCTURENAME, a.username ,a.REALNAME,a.PASSWORD,a.SEX,a.JoinedDate, "+
					 "	a.RegisterTime,a.LastLoginTime,a.REMAINUSER,a.ENABLED,a.Bz  "+
					 "	from TB_usersRoleLink u  "+
					 "	left join TB_ROLE r on u.roleid= r.roleid  "+
					 "	left join TB_user a on u.userid= a.userid  "+
					 "	left join Tb_Structure s on a.STRUCTUREID = s.STRUCTUREID  "+
					 "	left join Dbat0053 p on a.PCDID = p.PCDID  where 1=1 and a.enabled = 0 ";
						
		if(userid != null && !"".equals(userid)){
			
			hql = hql+" and  a.USERID='"+userid+"'";
		}
		userList = userDao.searchUserName(hql);
		
		if(userList != null && userList.size()>0){
			List<Object[]> newuser = this.usersAssociation(userList,"id");
			//获取采油站数据
			//生成树节点json文档
			jsonArr = new JSONArray();   
			
			for(Object[] entry  : newuser){
				JSONObject tjo = new JSONObject();
				
				tjo.put("USERID", entry[0]);
				//用户名
				tjo.put("USERNAME",entry[4]);
				//真实姓名
				tjo.put("REALNAME",entry[5]);
				//真实姓名
				tjo.put("PASSWORD",entry[6]);
				//性别
				tjo.put("SEX",String.valueOf(entry[7]));
//				if(entry != null && "0".equals(String.valueOf(entry[7]))){
//					tjo.put("SEX","女");
//				}else{
//					tjo.put("SEX","男");
//				}
				//入职日期
				String jionDate = "";
				if(entry[8] != null){
					jionDate = String.valueOf(entry[8]).substring(0, 19);
				}
				tjo.put("JOINEDDATE",jionDate);
				//注册时间
				String registerTime = "";
				if(entry[9] != null){
					registerTime = String.valueOf(entry[9]).substring(0, 19);
				}
				tjo.put("REGISTERTIME",registerTime);
				
				//权限
				tjo.put("ROLES",entry[1]);
				//组织结构
				tjo.put("STRUCTURENAME",entry[3]); 
				//排采队
				tjo.put("PCDNAME",entry[2]);
				 
				//最后登录时间
				 if(entry[10] != null){
					 tjo.put("LASTLOGINTIME",String.valueOf(entry[10]).substring(0, 19)); 
				 }else{
					 tjo.put("LASTLOGINTIME",""); 
				 }
				//备注 
				 tjo.put("BZ",entry[13]); 
				jsonArr.add(tjo);
				
			}
			
		}
		
		return jsonArr;
		
	}

	public boolean addUser(PcCdUserT user) throws Exception{
		return userDao.updateUser(user);
	}
	
	public boolean updateUser(PcCdUserT user)  throws Exception{
		return userDao.updateUser(user);
	}

	
	/**
	 * 0：userid=用户ID
	1：oper_code=操作员代码
	2：oper_name=OPER_NAME
	3：oper_pass=口令
	4：oper_sdbsadbse=当前状态
	5：audbshor_date=注册日期
	6：sdbsadbse_date=状态改变日期
	7：l_login_date=最后登录时间
	8: l_logoudbs_date=最后退出时间
	9：rlast_oper=此记录上次操作者
	10：rlast_odate=此记录上次操作日期
	11：remark=备注
	12：roleid=角色编码
	13：role_name=角色名称
	14：role_described=角色描述
	15：RLAST_OPER=修改人
	16：RLAST_ODATE=修改时间
	17：rightid=权限编码
	18：menuid=菜单编码
	19: right_name=权限名称
	20: menu_code=所在菜单名称
	21：departmentid=部门ID
	22：department_name=部门名称
	 */
	public List<Object[]> searchRight(String username,String password) throws HibernateException, SQLException {
		List<Object[]> rList = null;
//		String hql = "select * from pc_cd_user_t t";
		String hql = "select u.userid,u.oper_code ,u.oper_name,u.oper_pass,u.oper_sdbsadbse,u.audbshor_date,u.sdbsadbse_date,u.l_login_date,u.l_logoudbs_date,u.rlast_oper,u.rlast_odate,u.remark,"
			+" r.roleid,r.role_name,r.role_described,r.rlast_oper,r.rlast_odate,"
			+" ri.rightid,ri.menuid,ri.right_name,ri.menu_code,"
			+" u.departmentid,d.structurename,d.structuretype"
			+" from pc_cd_user_t u  "
			+" left join pc_cd_user_role_t ur on u.userid = ur.userid "
			+" left join pc_cd_role_t r on ur.roleid = r.roleid "
			+" left join pc_cd_role_right_t rr on r.roleid = rr.roleid "
			+" left join pc_cd_right_t ri on ri.rightid = rr.rightid "
			+" left join pc_cd_org_t d on d.org_id = u.departmentid  where 1=1 and u.oper_code='"+username+"' and u.oper_pass='"+password+"'";
		
		rList = userDao.searchRight(username, password, hql);
		return rList;
	}



	public boolean removeUser(String userId) throws Exception{
		String[] sqls = new String[2];
		sqls[0] = " delete from pc_cd_user_role_t ur where 1=1 and ur.userid='"+userId+"'";
		sqls[1] = " delete from pc_cd_user_t r where 1=1 and r.userid='"+userId+"'";
		
		return userDao.removeUser(sqls);
	}



	public User searchUserById(String userId) {
		User user = null;
//		String hql =  "FROM TbUser u WHERE 1=1 and u.userid ='"+userId+"'";
//		List<TbUser> userList = userDao.searchUser(hql);
//		
//		if(userList != null && userList.size()>0){
//			user = userList.get(0);
//			//获取组织结构名称
//			if(user.getStructureid() != null && !"".equals(user.getStructureid())){
//				String shql = "FROM TbStructure R WHERE 1=1 and R.structureid ='"+user.getStructureid()+"'";
//				List<TbStructure>  tbStructure = tbFunctionmenuDao.searchStructure(shql);
//				
//				if(tbStructure != null && tbStructure.size() >0){
//					user.setStructureid(tbStructure.get(0).getStructurename());
//				}
//				
//			}
//			//获取排采队名称
//			if(user.getPcdid() != null && !"".equals(user.getPcdid())){
//				
//				String phql = "FROM Dbat0053 R WHERE 1=1 and R.p01 ='"+user.getPcdid()+"'";
//				List<Dbat0053>  pcdlist = pcdDao.searchPcd(phql);
//				if(pcdlist != null && pcdlist.size()>0){
//					user.setPcdid(pcdlist.get(0).getP01());
//				}
//			}
//		}
		return user;
	}
	
	public List<PcCdUserT> searchUser(String userId) throws Exception{
		
		String hql =  "FROM PcCdUserT u WHERE 1=1 ";
		if(userId != null && !"".equals(userId)){
			hql +=" and u.userid ='"+userId+"'";
		}
		List<PcCdUserT> userList = userDao.searchUser(hql);
		
		return userList;
	}



	public JSONArray searchUserRole() throws Exception{
		JSONArray jsonArr = null ;
		String hql = "select * from pc_cd_role_t r WHERE 1=1  and r.role_name != '超级管理员'";
			List<Object[]> roleList = userDao.searchRole(hql);
		
		if(roleList != null && roleList.size()>0){
			
			//生成树节点json文档
			jsonArr = new JSONArray();   
			
			for(Object[] role  : roleList){
				JSONObject tjo = new JSONObject();
				tjo.put("text", role[1]);
				tjo.put("id",role[0]);
				jsonArr.add(tjo);
				
			}
		}
		return jsonArr;
	}
	
	
	public JSONArray searchUserDep() throws Exception{
		JSONArray jsonArr = null ;
		String hql = "select t.org_id,t.structurename,t.structuretype from pc_cd_org_t t "+
					 "	where t.structuretype in(3,4,5,14,15) "+
//					 "	union "+
//					 "	select a.team_id as org_id,a.team as structurename , 15 as structuretype "+
//					 "	from pc_cd_team_t a ) " +
					 " order by t.structuretype ,nlssort(t.structurename,'NLS_SORT=SCHINESE_STROKE_M')";
			List<Object[]> roleList = userDao.searchDep(hql);
		
		if(roleList != null && roleList.size()>0){
			
			//生成树节点json文档
			jsonArr = new JSONArray();   
			
			for(Object[] dep  : roleList){
				JSONObject tjo = new JSONObject();
				tjo.put("text", dep[1]);
				tjo.put("id",dep[0]);
				jsonArr.add(tjo);
				
			}
		}
		return jsonArr;
	}



//	public List<TbRole> searchRoleByName(String[] roleNames) {
//		List<TbRole> roles = null;
//		
//		if(roleNames != null && roleNames.length >0){
//			StringBuffer sb = new StringBuffer();
//			String roleStr = "";
//			for(String rolename : roleNames){
//				sb.append("'"+rolename+"',");
//			}
//			if(sb != null && sb.length()>0){
//				roleStr = sb.substring(0, sb.length()-1);
//			}
//			String hql = "FROM TbRole R WHERE 1=1 and R.rolename in ("+roleStr+")";
//			roles = userDao.searchRole(hql);
//			
//		}
//		
//		return roles;
//	}



	public String searchStructureByName(String structureName) {
		String id = "";
//		if(structureName != null && !"".equals(structureName)){
//			String hql = "FROM TbStructure R WHERE 1=1 and R.structurename ='"+structureName+"'";
//			List<TbStructure>  tbStructure = tbFunctionmenuDao.searchStructure(hql);
//			if(tbStructure != null && tbStructure.size()>0){
//				id = tbStructure.get(0).getStructureid();
//			}
//		}
		return id;
	}
	
	
	public String searchPcdByName(String pcdName) {
		String id = "";
//		if(pcdName != null && !"".equals(pcdName)){
//			String hql = "FROM Dbat0053 R WHERE 1=1 and R.p01 ='"+pcdName+"'";
//			List<Dbat0053>  pcdlist = pcdDao.searchPcd(hql);
//			if(pcdlist != null && pcdlist.size()>0){
//				id = pcdlist.get(0).getPcdid();
//			}
//		}
		return id;
	}

	public List<Object[]> searchUserChick(String name) throws Exception{

		List<Object[]> userList = null;
		String hql = "select u.oper_code,u.userid  from pc_cd_user_t u where u.oper_code ='"+name+"'";
		
		userList = userDao.searchUserName(hql);
		return userList;		
	}

	/**
	 * 添加用户最后登录/退出时间
	 * userid 用户ID
	 * flg 登录，退出标识  1-登录，2-退出
	 */
	public boolean updateUserLastLogin(String userid,int flg) throws Exception {
		boolean re = true;
		re = userDao.updateUserLastLogin(userid, flg);
		return re;
	}

	@Override
	public List<Object[]> searchDatas(String sql) throws Exception {
		return commonDao.searchEverySql(sql);
	}



}
