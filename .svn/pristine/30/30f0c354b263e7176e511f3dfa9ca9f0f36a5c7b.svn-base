package com.echo.service.impl;

 
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.beans.PageControl;
import com.echo.dao.DepartmentDao;
import com.echo.dao.PageDao;
import com.echo.dto.PcCdDepartmentT;
import com.echo.service.DepartmentService;

public class DepartmentServiceImpl implements DepartmentService{
	private DepartmentDao departmentDao;
	private PageDao pageDao;

	public void setPageDao(PageDao pageDao) {
		this.pageDao = pageDao;
	}

	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}

	public List<PcCdDepartmentT> searchDepartment(String department,String departmentname)
			throws Exception {
		String hql = "FROM PcCdDepartmentT d WHERE 1=1 " ;
			if(department != null && !"".equals(department)){
				hql += " and d.departmentid = '"+department+"'";
			}
			
			if(departmentname != null && !"".equals(departmentname)){
				hql += " and d.departmentName = '"+departmentname+"'";
			}
		List<PcCdDepartmentT> depList = departmentDao.searchDep(hql);
		return depList;
	}

	public JSONObject searchDeps(String departmentname,int pageNo,String sort,String order,int rowsPerpage)
			throws Exception {
		JSONObject tjo = null;
		JSONArray jsonArr = null;
		String sqlcount = "select count(*) ";
		String sql = "select d.departmentid,d.department_name,d.department_header,d.department_address,d.department_phone,to_char(d.rlast_odate,'YYYY-MM-DD hh24:mi:ss') as rlast_odate,d.rlast_oper,d.remark ";
		String sqlwhere = " from pc_cd_department_t d ";
		String where = "where 1=1 ";
		if(departmentname != null && !"".equals(departmentname)){
			
			where += " and  d.department_name like '%"+departmentname+"%'";
		}
		
		//获取总条数
		int total1= 0;
		total1 = pageDao.getCounts(sqlcount+sqlwhere+where);
		//排序
		String orderby = "order by nlssort(d.department_name, 'NLS_SORT=SCHINESE_STROKE_M')";
		if(!"".equals(sort)){
				
			orderby = "  order by u."+ sort+" "+order;
		}
		//计算分页
		PageControl control = new PageControl();
		//当前页
		control.setInt_num(rowsPerpage);
		control.init(pageNo, total1);
		//开始条数
		int start = control.getStart()-1;
		
		List<Object[]> depList = departmentDao.searchDeps(sql+sqlwhere+where+orderby,start,rowsPerpage);
		
		if(depList != null && depList.size()>0){
			jsonArr = new JSONArray();   
			for(Object[] entry  : depList){
				tjo = new JSONObject();
				tjo.put("DEPARTMENTID", entry[0]);
				//tjo.put("ORG_ID",entry[1]);
				tjo.put("DEPARTMENT_NAME",entry[1]);
				//if(entry[3] != null){
//					
					tjo.put("DEPARTMENT_HEADER",String.valueOf(entry[2]));
				//}else{
				//	tjo.put("DEPARTMENT_HEADER","");
				//}
				
//				7：sdbsadbse_date=状态改变日期
				//if(entry[4] != null){
					tjo.put("DEPARTMENT_ADDRESS",String.valueOf(entry[3]));
//				}else{
//					tjo.put("DEPARTMENT_ADDRESS","");
//				}
//				8：l_login_date=最后登录时间
				//if(entry[5] != null){
					tjo.put("DEPARTMENT_PHONE",String.valueOf(entry[4]));
//				}else{
//					tjo.put("DEPARTMENT_PHONE","");
//				}
				//if(entry[6] != null){
					tjo.put("RLAST_ODATE",String.valueOf(entry[5])); 
//				}else{
//					tjo.put("RLAST_ODATE",""); 
//				}
				//if(entry[7] != null){
					tjo.put("RLAST_OPER",String.valueOf(entry[6])); 
//				}else{
//					tjo.put("RLAST_OPER",""); 
//					
//				}
				
				
//				12：remark=备注
				tjo.put("REMARK",entry[7]);
				
			//	tjo.put("ORG_ID",entry[8]);
				
				jsonArr.add(tjo);
				
			}
			
		}
		tjo = new JSONObject();
		tjo.put("Rows",jsonArr);
		tjo.put("Total",total1);
		return tjo;
	}

	public boolean addDep(PcCdDepartmentT dep) throws Exception {
		return departmentDao.save(dep);
	}

	public boolean updateDep(PcCdDepartmentT dep) throws Exception {
		boolean flag = true;
		try {
			flag = departmentDao.updateDep(dep);
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	public boolean removeDep(String depId,String org_id) throws Exception {
		String[] sqls = new String[1];
		sqls[0] = " DELETE from Pc_Cd_Department_t d where d.departmentid = '"+depId+"'";
		//sqls[1] = " DELETE from pc_cd_org_t t where t.org_id = '"+org_id+"'";
		
		return departmentDao.removeDep(sqls);
	}


}
