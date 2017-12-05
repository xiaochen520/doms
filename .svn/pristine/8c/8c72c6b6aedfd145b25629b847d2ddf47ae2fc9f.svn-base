package com.echo.service.impl;

 
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.beans.PageControl;
import com.echo.dao.CommonDao;
import com.echo.dao.PageDao;
import com.echo.dto.PcCdTeamT;
import com.echo.service.TeamService;

public class TeamServiceImpl implements TeamService{
	private CommonDao commonDao;
	private PageDao pageDao;

	public void setPageDao(PageDao pageDao) {
		this.pageDao = pageDao;
	}

	

	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}


	public JSONObject searchDeps(String departmentname,int pageNo,String sort,String order,int rowsPerpage)
			throws Exception {
		JSONObject tjo = null;
		JSONArray jsonArr = null;
		String sqlcount = "select count(*) from pc_cd_team_t t "+
		" left join  pc_cd_org_t o   on t.org_id = o.org_id "+
		" left join  pc_cd_org_t o1   on o1.org_id = o.pid where 1=1  ";
		
		String sql = "select t.team_id, t.org_id, t.team, t.remark, t.rlast_oper, to_char(t.rlast_odate, 'YYYY-MM-DD hh24:mi:ss') as rlast_odate, o1.org_id as pid,o1.structurename as department_name "+ 
		" from pc_cd_team_t t "+
		" left join  pc_cd_org_t o   on t.org_id = o.org_id "+
		" left join  pc_cd_org_t o1   on o1.org_id = o.pid where 1=1  ";
		String where = "";
		if(departmentname != null && !"".equals(departmentname)){
			
			where += " and  t.team like '%"+departmentname+"%'";
		}
		
		//获取总条数
		int total1= 0;
		total1 = pageDao.getCounts(sqlcount+where);
		//排序
		String orderby = "";
		if(!"".equals(sort)){
				
			orderby = "  order by "+ sort+" "+order;
		}else{
			orderby = "order by nlssort(t.team ,'NLS_SORT=SCHINESE_RADICAL_M')";
		}
		//计算分页
		PageControl control = new PageControl();
		//当前页
		control.setInt_num(rowsPerpage);
		control.init(pageNo, total1);
		//开始条数
		int start = control.getStart()-1;
		
		String[] cloumnsName = {"team_id","org_id","team","remark","rlast_odate","rlast_oper","pid","department_name"};
		List<Object[]> depList = commonDao.searchPaging(sql+where+orderby, start, rowsPerpage, cloumnsName);
		
		if(depList != null && depList.size()>0){
			jsonArr = new JSONArray();   
			for(Object[] entry  : depList){
				tjo = new JSONObject();
				tjo.put("TEAM_ID", entry[0]);
				tjo.put("ORG_ID",entry[1]);
				tjo.put("TEAM",String.valueOf(entry[2]));
				tjo.put("REMARK",String.valueOf(entry[3]));
				tjo.put("RLAST_ODATE",String.valueOf(entry[4])); 
				tjo.put("RLAST_OPER",String.valueOf(entry[5])); 
				tjo.put("PID",String.valueOf(entry[6])); 
				tjo.put("DEPARTMENT_NAME",entry[7]);
				jsonArr.add(tjo);
				
			}
			
		}
		tjo = new JSONObject();
		tjo.put("Rows",jsonArr);
		tjo.put("Total",total1);
		return tjo;
	}


	@Override
	public boolean addOrupdateTeam(PcCdTeamT dep) throws Exception {
		boolean flag = true;
		List<PcCdTeamT> deps = new ArrayList<PcCdTeamT>();
		deps.add(dep);
		try {
			flag = commonDao.addOrUpdateDatas(deps);
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}



	@Override
	public boolean removeTeam(String depId, String orgId) throws Exception {
		List<String> deles = new ArrayList<String>();
		String[] sqls = new String[1];
		deles.add(" DELETE from Pc_Cd_org_t o where o.org_id = '"+orgId+"'");
		deles.add(" DELETE from Pc_Cd_team_t d where d.team_id = '"+depId+"'");
		
		return commonDao.removeDatas(deles);
	}



	@Override
	public List<PcCdTeamT> searchTea(String department, String departmentname)
			throws Exception {

		String hql = "FROM PcCdTeamT d WHERE 1=1 " ;
			if(department != null && !"".equals(department)){
				hql += " and d.teamId = '"+department+"'";
			}
			
			if(departmentname != null && !"".equals(departmentname)){
				hql += " and d.team = '"+departmentname+"'";
			}
		List<PcCdTeamT> depList = (List<PcCdTeamT>) commonDao.searchClassQuery(hql);
		return depList;
	
	}


}
