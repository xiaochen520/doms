package com.echo.service.impl;

 
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.beans.PageControl;
import com.echo.dao.LogDao;
import com.echo.dao.PageDao;
import com.echo.dto.PcRdLoginfoT;
import com.echo.service.LogService;
import com.echo.util.CommonsUtil;

public class LogServiceImpl implements LogService{
	private LogDao logDao;
	private PageDao pageDao;

	public void setPageDao(PageDao pageDao) {
		this.pageDao = pageDao;
	}
	public void setLogDao(LogDao logDao) {
		this.logDao = logDao;
	}

	public boolean addLog(PcRdLoginfoT log) throws Exception{
		return logDao.save(log);
	}
	/**
	 * 获取log信息
	 * 	0-日志ID
		1-操作人
		2-操作类别
		3-操作对象
		4-信息ID
		5-操作时间
		6-IP地址
		7-修改前信息
		8-修改后信息
	 */
	public JSONObject searchLog(String username, String logtype, String logtime,int pageNo,String sort,String order,int rowsPerpage) {
		JSONArray jsonArr = null;
		JSONObject tjo = new JSONObject();
		String sqltitle =  "select a.logid,a.rlast_oper,a.operating_class,a.operand,a.infoid,to_char(a.operation_time,'YYYY-MM-DD hh24:mi:ss') as operation_time,a.ip_address,a.minformation_bef,a.minformation_aft  ";
		String sqltable =  "from pc_rd_loginfo_t a  " ;
					
		String sqlwhere =  "where 1=1 ";
		if(username != null && !"".equals(username)){
			sqlwhere += " and a.rlast_oper = '"+username+"' ";
		}
		if(logtype != null && !"".equals(logtype)){
			sqlwhere += " and a.operating_class = '"+CommonsUtil.getLogType(logtype)+"' ";
		}
		if(logtime != null && !"".equals(logtime)){
			sqlwhere += " and a.operation_time >= to_date('"+logtime+"','YYYY-MM-DD') ";
		}
		//获取总条数
		int total1=pageDao.getCounts("select count(*) "+sqltable+sqlwhere);
		
		//排序
		if(!"".equals(sort)){
			sqlwhere +=" order by a."+sort+" " +order;
			
		}else{
			
			sqlwhere += " order by a.operation_time desc";
		}
		//计算分页
		PageControl control = new PageControl();
		//当前页
		control.setInt_num(rowsPerpage);
		control.init(pageNo, total1);
		//开始条数
		int start = control.getStart()-1;
		List<Object[]> list = logDao.seachLog(sqltitle+sqltable+sqlwhere,start,rowsPerpage);
		if(list != null && list.size()>0){
			jsonArr = new JSONArray();  
			for(Object[] loglist  : list){
				
				tjo.put("LOGID", loglist[0]);
				
				//操作人
				tjo.put("RLAST_OPER", loglist[1]);
				//操作类别
				tjo.put("OPERATING_CLASS", CommonsUtil.getLogTypeStr(String.valueOf(loglist[2])));
				//操作对象
				if(loglist[3] != null ){
					tjo.put("OPERAND", loglist[3]);
				}else{
					tjo.put("OPERAND", "");
				}
				
				//操作时间
				tjo.put("OPERATION_TIME", String.valueOf(loglist[5]));
				//IP地址
				tjo.put("IP_ADDRESS", loglist[6]);
				//信息ID
				if(loglist[4] != null){
					tjo.put("INFOID", loglist[4]);
				}else{
					tjo.put("INFOID", "");
				}
				
				
				//修改前信息
				if(loglist[7] != null){
					tjo.put("MINFORMATION_BEF", loglist[7]);
				}else{
					tjo.put("MINFORMATION_BEF", "");
				}
				
				//修改后信息
				if(loglist[8] != null){
					tjo.put("MINFORMATION_AFT", loglist[8]);
				}else{
					tjo.put("MINFORMATION_AFT", "");
				}
				jsonArr.add(tjo);
			}
		}
		
		
		tjo = new JSONObject();
		tjo.put("Rows",jsonArr);
		tjo.put("Total",total1);
		
		return tjo;
	}
	
	
	/**
	 * 获取username信息
	 */
	public List<Object[]> searchUsername() {
		JSONArray jsonArr = null;
		 JSONArray allArr = new JSONArray();
			String usersql = "select d.departmentid,d.department_name,u.userid,u.oper_code from pc_cd_department_t d left join pc_cd_user_t u on d.departmentid = u.departmentid";
			List<Object[]> usernamelist = logDao.seachUsername(usersql);
//			Object[] usernames = null;
//			if(usernamelist != null && usernamelist.size()>0){
//				usernames = usernamelist.toArray();
//			}
			
		
		return usernamelist;
	}


}
