package com.echo.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.beans.PageControl;
import com.echo.dao.CommonDao;
import com.echo.dto.PcCdCliquePoolT;
import com.echo.dto.PcCdLargeStationT;
import com.echo.dto.PcCdOrgT;
import com.echo.service.ZkxtxxService;
import com.echo.util.CommonsUtil;

public class ZkxtxxServiceImpl implements ZkxtxxService{
	private CommonDao commonDao;

	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}

	public HashMap<String,Object> searchDatas(String param1,String param2,String param3,String param4,String param5,String totalNumFlag, int pageNo,String sort,String order,int rowsPerpage) throws Exception{
		JSONArray jsonArr = null;
		JSONObject tjo = null;
		HashMap<String,Object> map = new HashMap<String, Object>();
		String cloums = "select ";
		String formTable = " from PC_CD_LARGE_STATION_V t where 1=1";
		String totalNum = "select count(*)";

		if(!param1.equals("")&&param1!=null&&!param1.equals("undefined")&!param1.equals("全部")){
			formTable=formTable+" and OBJECT_NAME='"+param1+"'";
		}
		if(param2!=null&&!param2.equals("")&&!param2.equals("undefined")){
			formTable=formTable+" and QKMC  like '%"+param2+"%'";
		}
		if(param3!=null&&!param3.equals("")&&!param3.equals("undefined")){
			formTable=formTable+" and UNIT_NAME  like '%"+param3+"%'";
		}
		if(param4!=null&&!param4.equals("")&&!param4.equals("undefined")){
			
			formTable=formTable+" and SYSTEM_CODE='"+param4+"'";
		}
		if(param5!=null&&!param5.equals("")&&!param5.equals("undefined")){
			
			formTable=formTable+" and LJJD_S='"+param5+"'";
		}
		
		String[] cloumnsName = null;
		
		
		if (!"".equals(totalNumFlag)) {
			cloumnsName = new String[]{"OBJECT_NAME","COMUNIT","ACCESS_SIGNS","QKMC","UNIT_NAME","SYSTEM_CODE","DOWN_SOFTWARE_MODEL","DOWN_SOFTWARE_NAME","UPPER_SOFTWARE_MODEL","UPPER_SOFTWARE_NAME","INTEGRATOR_NAME","LJJD_S","REMARK"};
		}else{
			//"IP","STATION_NO",
			cloumnsName = new String[]{"OBJECT_NAME","COMUNIT","ACCESS_SIGNS","QKMC","UNIT_NAME","SYSTEM_CODE","DOWN_SOFTWARE_MODEL","DOWN_SOFTWARE_NAME","UPPER_SOFTWARE_MODEL","UPPER_SOFTWARE_NAME","INTEGRATOR_NAME","LJJD_S","REMARK","CREATE_DATE","RLAST_OPER","RLAST_ODATE","LJJD_ID","DOWN_SOFTWARE_FACTURER","UPPER_SOFTWARE_FACTURER","INTEGRATOR_CODE","OBJECT_CODE","LARGE_STATION_ID","COMBINATION_STATIONID"};
			
		}
		
			//"IP","STATION_NO",
			
		String kk ="";
		for(int m=0;m<cloumnsName.length;m++){
			if("RLAST_ODATE".equals(cloumnsName[m])){
				kk += "to_char(RLAST_ODATE,'YYYY-MM-DD HH24:MI:SS') as RLAST_ODATE,";
			}else{
				kk=kk+cloumnsName[m]+",";
			}
		}
		kk = kk.substring(0, kk.length()-1);
		String boilersInfo = cloums +kk+ formTable;
		
		//获取总条数
		int total = 0;
		if ("1".equals(totalNum)) {
			total = 1;
		} else {
			totalNum += formTable;
			total = commonDao.getCounts(totalNum);
		}
		
		if ("totalNum".equals(totalNumFlag)) {
			map.put("totalNum", total + "");
			return map;
		}
		//排序
		if(!"".equals(sort) && !"OBJECT_NAME".equals(sort)){
			for (String cloumn : cloumnsName) {
				if(cloumn.equals(sort)){
					boilersInfo +=" order by t." + cloumn + " " + order;
					break;
				}
				
			}
		}
		else {
			boilersInfo +=" order by nlssort(OBJECT_NAME, 'NLS_SORT=SCHINESE_STROKE_M') ";
		}
		
		
		List<Object[]> lo = null;
		if ("export".equals(totalNumFlag)) {
			lo = commonDao.searchEverySql(boilersInfo);
		}else{
			//计算分页
			PageControl control = new PageControl();
			//当前页
			control.setInt_num(rowsPerpage);
			control.init(pageNo, total);
			//开始条数
			int start = control.getStart()-1;
			
			lo = commonDao.searchPaging(boilersInfo,start,rowsPerpage,cloumnsName);
		}
		if (lo != null && 0 < lo.size()) {
			// 生成树节点json文档
			jsonArr = new JSONArray();
			for (Object[] o : lo) {
				tjo = new JSONObject();
				for (int i = 0; i < o.length; i++) {

							tjo.put(cloumnsName[i], CommonsUtil.getClearNullData(o[i]));
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
	
	
	public boolean addOrUpdateData(PcCdLargeStationT pool)throws Exception{
		List<PcCdLargeStationT> list = new ArrayList<PcCdLargeStationT>();
		list.add(pool);
		return commonDao.addOrUpdateDatas(list);
	}
	public boolean removeStationInfo(String arg)throws Exception{
		List<String> list = new ArrayList<String>();
		list.add(" DELETE from pc_cd_clique_pool_t d where d.org_id = '"+arg+"'");
		list.add(" DELETE from pc_cd_org_t t where t.org_id = '"+arg+"'");
		
		return commonDao.removeDatas(list);
	}

	@Override
	public boolean removeData(String arg) throws Exception {
		List<String> list = new ArrayList<String>();
		list.add(" DELETE from pc_cd_clique_pool_t d where d.org_id = '"+arg+"'");
		list.add(" DELETE from pc_cd_org_t t where t.org_id = '"+arg+"'");
		
		return commonDao.removeDatas(list);
	}

	@Override
	public List<PcCdLargeStationT> searchData(String id,String param1,String param2,String param3)
			throws Exception {
		String hql = "FROM PcCdLargeStationT t WHERE 1=1 ";
		if(id != null && !"".equals(id)){
			hql += " and t.largeStationId = '"+id+"'";
		}
		
		if(param1 != null && !"".equals(param1)){
			hql += " and t.qkmc = '"+param1+"'";
		}

		if(param2 != null && !"".equals(param2)){
			hql += " and t.unitName = '"+param2+"'";
		}

		if(param3 != null && !"".equals(param3)){
			hql += " and t.systemCode = '"+param3+"'";
		}
		return (List<PcCdLargeStationT>) commonDao.searchClassQuery(hql);
	}

	@Override
	public List<PcCdOrgT> searchOrgData(String id, String name)
			throws Exception {
		String hql = "FROM PcCdOrgT t WHERE 1=1 ";
		if(id != null && !"".equals(id)){
			hql += " and t.orgId = '"+id+"'";
		}else if(name != null && !"".equals(name)){
			hql += " and t.structurename = '"+name+"'";
		}
		return (List<PcCdOrgT>) commonDao.searchClassQuery(hql);
	}

	@Override
	public JSONArray getComData(String oilid) throws Exception {
		JSONObject  obj = null;
		JSONArray  jArr = null;
		String sql ="select  a.combination_stationid,a.combination_station_name from  pc_cd_combination_station_t a ORDER BY NLSSORT(combination_station_name,'NLS_SORT = SCHINESE_STROKE_M')";
		
		List<Object[]> olData = commonDao.searchEverySql(sql);
		if(olData !=null && olData.size()>0){
			jArr = new JSONArray();
			for (Object[] entry : olData) {
				obj = new JSONObject();
				obj.put("text", entry[1]);
				obj.put("id", entry[0]);
				jArr.add(obj);
			}
			return jArr;
		}
		jArr = new JSONArray();
		obj = new JSONObject();
		obj.put("text", "");
		obj.put("id", "");
		jArr.add(obj);
		return jArr;
	}
	

}
