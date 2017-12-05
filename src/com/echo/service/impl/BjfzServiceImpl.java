package com.echo.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.echo.beans.PageControl;
import com.echo.dao.BjfzDao;
import com.echo.dto.PcCdAlarmManagT;
import com.echo.service.BjfzService;
import com.echo.util.DateBean;

public class BjfzServiceImpl implements BjfzService{
	

	private BjfzDao bjfzDao;
	public void setBjfzDao(BjfzDao bjfzDao) {
		this.bjfzDao = bjfzDao;
	}
	
	
	@Override
	public HashMap<String, Object> searchBjxzcx(String startDate,
			String endDate, int pageNo, String sort, String order,
			int rowsPerpage, String totalNumf) {
		
		JSONArray jsonArr = null;
		JSONObject tjo = null;
		HashMap<String,Object> map = new HashMap<String, Object>();
		String cloums = "select   ";
	
		Date today = new Date();
		String nowdate = DateBean.getTimeStr(today);
		
		if("".equals(startDate)){
			startDate = nowdate.substring(0, 10) + " 00:00:00";
		}else{
			startDate = startDate + ":00";
		}
		if("".equals(endDate)){
			endDate = nowdate.substring(0, 10) + " 23:59:59";
		}else{
			endDate = endDate + ":00";
		}
		
		String formTable = " from PC_RPD_WARNING_LIMIT_T where 1=1  and acquisition_time between TO_DATE('"+startDate+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+endDate+"','YYYY-MM-DD HH24:MI:SS') ";
		String totalNum = "select count(*) ";
		String[] cloumnsName = {"sqr","acquisition_time","spr","zxr","bqmc","zxqk"};

		String kk="sqr";
		for(int m=1;m<cloumnsName.length;m++){
			if("acquisition_time".equals(cloumnsName[m])){
				kk=kk+","+"to_char(acquisition_time,'YYYY-MM-DD hh24:mi:ss') as acquisition_time";
			}else{ 
				kk=kk+","+cloumnsName[m];
			} 
		}
		if ("export".equals(totalNumf)) {
			String[] cloumnsName2 = {"sqr","acquisition_time","spr","zxr","bqmc","zxqk"};
			kk = "sqr";
			for(int m=1;m<cloumnsName2.length;m++){
				if("acquisition_time".equals(cloumnsName2[m])){
					kk=kk+","+"to_char(acquisition_time,'YYYY-MM-DD hh24:mi:ss') as acquisition_time";
				}else{
					kk=kk+","+cloumnsName2[m];
				}
			}
		}
		String searchbjxzcx = cloums + kk+formTable;
		//获取总条数
		int total = 0;
		if ("1".equals(totalNum)) {
			total = 1;
		} else {
			totalNum += formTable;
			total = bjfzDao.getCounts(totalNum);
		}
		if ("totalNum".equals(totalNumf)) {
			map.put("totalNum", total + "");
			return map;
		}
		//排序
		if(!"".equals(sort) && !"acquisition_time".equals(sort)){
			for (String cloumn : cloumnsName) {
				if(cloumn.equals(sort)){
					searchbjxzcx +=" order by " + cloumn + " " + order;
					break;
				}				
			}
		}
		else {
			searchbjxzcx +=" order by acquisition_time desc";
		}
		//计算分页
		PageControl control = new PageControl();
		//当前页
		List<Object[]> lo = new ArrayList<Object[]>();
		control.setInt_num(rowsPerpage);
		if ("export".equals(totalNumf)) {
			lo = bjfzDao.searchBjxzcx(searchbjxzcx);
		}
		else {
			control.init(pageNo, total);
			//开始条数
			int start = control.getStart()-1;
			lo = bjfzDao.searchBjxzcx(searchbjxzcx,start,rowsPerpage);
		}
		
		if (lo != null && 0 < lo.size()) {
			// 生成树节点json文档
			jsonArr = new JSONArray();
			for (Object[] o : lo) {
				tjo = new JSONObject();

				for (int i = 0; i < o.length; i++) {
					if (o[i] == null||o[i].equals("null")) {
						o[i] = "";
					}


						tjo.put(cloumnsName[i], o[i].toString());					
				}
				jsonArr.add(tjo);
			}
		}
		tjo = new JSONObject();
		tjo.put("Rows",jsonArr);
		tjo.put("Total",total);
		map.put("list", lo);
		map.put("json", tjo);
		return map;
	}


	@Override
	public HashMap<String, Object> searchDxtscx(String tsbm,String tsr,String jsbm,String jsr, String szd,String startDate,
			String endDate, int pageNo, String sort, String order,
			int rowsPerpage, String totalNumf) {
		JSONArray jsonArr = null;
		JSONObject tjo = null;
		HashMap<String,Object> map = new HashMap<String, Object>();
		String cloums = "select   ";	
		Date today = new Date();
		String nowdate = DateBean.getTimeStr(today);
		
		if("".equals(startDate)){
			startDate = nowdate.substring(0, 10) + " 00:00:00";
		}else{
			startDate = startDate + ":00";
		}
		if("".equals(endDate)){
			endDate = nowdate.substring(0, 10) + " 23:59:59";
		}else{
			endDate = endDate + ":00";
		}
		

		
		String formTable = " from PC_RPD_WARNING_MSG_T where 1=1  and acquisition_time between TO_DATE('"+startDate+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+endDate+"','YYYY-MM-DD HH24:MI:SS') ";
		String totalNum = "select count(*) ";
		if(!tsbm.equals("") && tsbm!=null && !tsbm.equals("undefined") ){
			formTable=formTable+" and tsbm='"+tsbm+"'";
		}
		if(!tsr.equals("") && tsr!=null && !tsr.equals("undefined") ){
			formTable=formTable+" and tsr='"+tsr+"'";
		}
		if(!jsbm.equals("") && jsbm!=null && !jsbm.equals("undefined") ){
			formTable=formTable+" and jsbm='"+jsbm+"'";
		}
		if(!jsr.equals("") && jsr!=null && !jsr.equals("undefined") ){
			formTable=formTable+" and jsr='"+jsr+"'";
		}
		if(!szd.equals("") && szd!=null && !szd.equals("undefined") ){
			formTable=formTable+" and szdts='"+szd+"'";
		}
		String[] cloumnsName = {"jsbm","acquisition_time","jsr","szdts","tsbm","tsr"};

		String kk="jsbm";
		for(int m=1;m<cloumnsName.length;m++){
			if("acquisition_time".equals(cloumnsName[m])){
				kk=kk+","+"to_char(acquisition_time,'YYYY-MM-DD hh24:mi:ss') as acquisition_time";
			}else{ 
				kk=kk+","+cloumnsName[m];
			} 
		}
		if ("export".equals(totalNumf)) {
			String[] cloumnsName2 = {"jsbm","acquisition_time","jsr","szdts","tsbm","tsr"};
			kk = "jsbm";
			for(int m=1;m<cloumnsName2.length;m++){
				if("acquisition_time".equals(cloumnsName2[m])){
					kk=kk+","+"to_char(acquisition_time,'YYYY-MM-DD hh24:mi:ss') as acquisition_time";
				}else{
					kk=kk+","+cloumnsName2[m];
				}
			}
		}
		String searchdxts = cloums + kk+formTable;
		//获取总条数
		int total = 0;
		if ("1".equals(totalNum)) {
			total = 1;
		} else {
			totalNum += formTable;
			total = bjfzDao.getCounts(totalNum);
		}
		if ("totalNum".equals(totalNumf)) {
			map.put("totalNum", total + "");
			return map;
		}
		//排序
		if(!"".equals(sort) && !"acquisition_time".equals(sort)){
			for (String cloumn : cloumnsName) {
				if(cloumn.equals(sort)){
					searchdxts +=" order by " + cloumn + " " + order;
					break;
				}				
			}
		}
		else {
			searchdxts +=" order by acquisition_time desc";
		}
		//计算分页
		PageControl control = new PageControl();
		//当前页
		List<Object[]> lo = new ArrayList<Object[]>();
		control.setInt_num(rowsPerpage);
		if ("export".equals(totalNumf)) {
			lo = bjfzDao.searchDxcx(searchdxts);
		}
		else {
			control.init(pageNo, total);
			//开始条数
			int start = control.getStart()-1;
			lo = bjfzDao.searchDxcx(searchdxts,start,rowsPerpage);
		}
		
		if (lo != null && 0 < lo.size()) {
			// 生成树节点json文档
			jsonArr = new JSONArray();
			for (Object[] o : lo) {
				tjo = new JSONObject();

				for (int i = 0; i < o.length; i++) {
					if (o[i] == null||o[i].equals("null")) {
						o[i] = "";
					}


						tjo.put(cloumnsName[i], o[i].toString());					
				}
				jsonArr.add(tjo);
			}
		}
		tjo = new JSONObject();
		tjo.put("Rows",jsonArr);
		tjo.put("Total",total);
		map.put("list", lo);
		map.put("json", tjo);
		return map;
	}


	@Override
	public JSONObject searchBjzsk(int pageNo, String sort, String order,
			int rowsPerpage) {
		
		JSONArray jsonArr = null;
		JSONObject tjo = new JSONObject();
		List<Object[]> zskList = null;
		String sqlcount = "select count(*) from PC_CD_WARNING_KNOW_T u where 1=1";
		String sqlwhere = "select zsk_id,miaoshu,CSYY,CLFS  from PC_CD_WARNING_KNOW_T u where 1=1";
		String where = "";					
	
		//获取总条数
		int total1= 0;
		total1 = bjfzDao.getCounts(sqlcount+where);
		//排序
		String orderby = "  order by zsk_id";

		PageControl control = new PageControl();
		control.setInt_num(rowsPerpage);
		control.init(pageNo, total1);
		int start = control.getStart()-1;		
		zskList = bjfzDao.searchBjzsk(sqlwhere+where+orderby,start,rowsPerpage);				
		if(zskList != null && zskList.size()>0){
			jsonArr = new JSONArray();   			
			for(Object[] entry  : zskList){
				tjo.put("zsk_id", entry[0]);
				tjo.put("miaoshu", entry[1]);
				tjo.put("CSYY",entry[2]);
				tjo.put("CLFS",entry[3]);
				jsonArr.add(tjo);				
			}			
		}		
		tjo = new JSONObject();
		tjo.put("Rows",jsonArr);
		tjo.put("Total",total1);		
		return tjo;
	}


	@Override
	public boolean removeBjzsk(String zskid) {
		String sql = "";
		sql = " DELETE from PC_CD_WARNING_KNOW_T d where d.qkid = '"+zskid+"'";
		return bjfzDao.removeBjzsk(sql);
	}


	@Override
	public List<Object[]> searchUserChick(String username) {
		List<Object[]> userList = null;
		String hql = "select u.username,u.alarmuserid  from PC_CD_ALARM_MANAG_T u where u.username ='"+username+"'";
		
		userList = bjfzDao.searchAlarmName(hql);
		return userList;	
	}


	@Override
	public JSONArray searchUserDep() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public JSONObject searchDxyhgl(String depname, String username, int pageNo,
			String sort, String order, int rowsPerpage) {
		JSONArray jsonArr = null;
		JSONObject tjo = new JSONObject();
		List<Object[]> userList = null;
		String sqlcount = "select count(*) from PC_CD_ALARM_MANAG_T u where 1=1";
		String sqlwhere = "select u.alarmuserid ,u.username, u.phone, u.dept, u.alarm_key, u.dep_manag,u.data_unit, u.remark from PC_CD_ALARM_MANAG_T u where 1=1";
		String where = "";					
		if(username != null && !"".equals(username)){			
			where = where+" and  u.username like '%"+username+"%'";
		}		
		if(depname != null && !"".equals(depname)){			
			where = where+" and  d.dept = '"+depname+"'";
		}		
		//获取总条数
		int total1= 0;
		total1 = bjfzDao.getCounts(sqlcount+where);
		//排序
		String orderby = "  order by u.username";
		if(!"".equals(sort)){			
			if("ROLES".equals(sort)){				
				orderby  = " order by r.username " +order;
			}else if("DEPARTMENT".equals(sort)){				
				orderby = "  order by d.dept " +order;
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
		userList = bjfzDao.searchDxyhgl(sqlwhere+where+orderby,start,rowsPerpage);
				
		if(userList != null && userList.size()>0){
			//usersAssociation 从新组织用户信息，方法将多个权限拼接字符串
		//	List<Object[]> newuser = this.usersAssociation(userList,"name");
			//获取采油站数据
			//生成树节点json文档
			jsonArr = new JSONArray();   			
			for(Object[] entry  : userList){
				//0：userid=用户ID
				tjo.put("alarmuserid", entry[0]);
				//1:DEPARTMENTID=部门ID
				tjo.put("username", entry[1]);
//				2：oper_code=操作员代码
				tjo.put("phone",entry[2]);
//				3：oper_name=真实姓名
				tjo.put("dept",entry[3]);
//				4：oper_pass=口令
				tjo.put("alarm_key",entry[4]);
				tjo.put("dep_manag",entry[5]);
				tjo.put("data_unit",entry[6]);
//				5：oper_sdbsadbse=当前状态
				tjo.put("remark",entry[7]);
//				//6：audbshor_date=注册日期
//				if(entry[6] != null){
////					
//					tjo.put("AUDBSHOR_DATE",String.valueOf(entry[6]));
//				}else{
//					tjo.put("AUDBSHOR_DATE","");
//				}				
////				7：sdbsadbse_date=状态改变日期
//				if(entry[7] != null){
//					tjo.put("SDBSADBSE_DATE",String.valueOf(entry[7]));
//				}else{
//					tjo.put("SDBSADBSE_DATE","");
//				}
////				8：l_login_date=最后登录时间
//				if(entry[8] != null){
//					tjo.put("L_LOGIN_DATE",String.valueOf(entry[8]));
//				}else{
//					tjo.put("L_LOGIN_DATE","");
//				}				
////				9: l_logoudbs_date=最后退出时间
////				10：rlast_oper=此记录上次操作者
//				tjo.put("RLAST_OPER",entry[10]);
////				11：rlast_odate=此记录上次操作日期
//				if(entry[11] != null){
//					tjo.put("RLAST_ODATE",String.valueOf(entry[11])); 
//				}else{
//					tjo.put("RLAST_ODATE",""); 
//				}				
////				12：remark=备注
//				tjo.put("REMARK",entry[12]);
////				13:roleid=角色ID
//				tjo.put("ROLEID",entry[13]);
////				14：role_name=角色名称
//				tjo.put("ROLES",entry[14]);
////				16：department_name=部门名称
//				tjo.put("DEPARTMENT",entry[15]);
			
				
				jsonArr.add(tjo);				
			}			
		}		
		tjo = new JSONObject();
		tjo.put("Rows",jsonArr);
		tjo.put("Total",total1);		
		return tjo;
	}


	@Override
	public List<Object[]> searchDatas(String sql) {
		
		System.out.println(sql);
		return bjfzDao.searchEverySql(sql);
	}


	@Override
	public boolean addUser(PcCdAlarmManagT alarmMana) {
		return bjfzDao.updateUser(alarmMana);
	}


	@Override
	public List<PcCdAlarmManagT> searchUser(String userid) {
		String hql =  "FROM PcCdAlarmManagT u WHERE 1=1 ";
		if(userid != null && !"".equals(userid)){
			hql +=" and u.alarmuserid ='"+userid+"'";
		}
		List<PcCdAlarmManagT> userList = bjfzDao.searchUser(hql);
		
		return userList;
	}


	@Override
	public boolean updateUser(PcCdAlarmManagT user) {
		return bjfzDao.updateUser(user);
	}


	@Override
	public boolean removeUser(String userId) {
		String sql = " delete from pc_cd_alarm_manag_t r where 1=1 and r.alarmuserid='"+userId+"'";		
		return bjfzDao.removeUser(sql);
	}


	@Override
	public JSONObject searchBjxzsp(String sqname, String zxzt, int pageNo,
			String sort, String order, int rowsPerpage) {
				
		
		JSONArray jsonArr = null;
		JSONObject tjo = new JSONObject();
		List<Object[]> zskList = null;
		String sqlcount = "select count(*) from PC_RPD_WARNING_AUDIT_T u where 1=1";
		String sqlwhere = "select acquisition_time,spbmc,sqry,zxzt  from PC_RPD_WARNING_AUDIT_T u where 1=1";
		String where = "";					
		
		if(sqname != null && !"".equals(sqname)){
			sqlwhere = sqlwhere+ " and  sqry like '%"+sqname+"%'";
		}
		if(zxzt != null && !"".equals(zxzt) && "全部".equals(zxzt)){
			sqlwhere = sqlwhere +" and zxzt like '%"+zxzt+"%'";
		}
		
		//获取总条数
		int total1= 0;
		total1 = bjfzDao.getCounts(sqlcount+where);
		//排序
		String orderby = "  order by acquisition_time";

		PageControl control = new PageControl();
		control.setInt_num(rowsPerpage);
		control.init(pageNo, total1);
		int start = control.getStart()-1;		
		zskList = bjfzDao.searchBjxzsp(sqlwhere+where+orderby,start,rowsPerpage);				
		if(zskList != null && zskList.size()>0){
			jsonArr = new JSONArray();   			
			for(Object[] entry  : zskList){
				tjo.put("acquisition_time", entry[0].toString());
				tjo.put("spbmc", entry[1]);
				tjo.put("sqry",entry[2]);
				tjo.put("zxzt",entry[3]);
				jsonArr.add(tjo);				
			}			
		}		
		tjo = new JSONObject();
		tjo.put("Rows",jsonArr);
		tjo.put("Total",total1);		
		return tjo;
	}
}
