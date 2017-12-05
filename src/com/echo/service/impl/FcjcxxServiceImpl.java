package com.echo.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.beans.PageControl;
import com.echo.dao.CommonDao;
import com.echo.dto.PcCdCliquePoolT;
import com.echo.dto.PcCdOrgT;
import com.echo.service.FcjcxxService;
import com.echo.util.CommonsUtil;

public class FcjcxxServiceImpl implements FcjcxxService{
	private CommonDao commonDao;

	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}

	public HashMap<String,Object> searchDatas(String oilstationname,String blockstationname,String cliquepool,String cliquetype,String totalNumFlag, int pageNo,String sort,String order,int rowsPerpage) throws Exception{
		JSONArray jsonArr = null;
		JSONObject tjo = null;
		HashMap<String,Object> map = new HashMap<String, Object>();
		String cloums = "select ";
		String formTable = " from pc_cd_CLIQUE_POOL_v t where 1=1";
		String totalNum = "select count(*)";

		if(!oilstationname.equals("")&&oilstationname!=null&&!oilstationname.equals("undefined")&!oilstationname.equals("全部")){
			formTable=formTable+" and OIL_STATION_NAME='"+oilstationname+"'";
		}
		if(blockstationname!=null&&!blockstationname.equals("")&&!blockstationname.equals("undefined")){
			formTable=formTable+" and STATION_NAME='"+blockstationname+"'";
		}
		if(cliquepool!=null&&!cliquepool.equals("")&&!cliquepool.equals("undefined")){
			formTable=formTable+" and CLIQUE_POOL_NAME  like '%"+cliquepool+"%'";
		}
		if(cliquetype!=null&&!cliquetype.equals("")&&!cliquetype.equals("undefined")){
			
			formTable=formTable+" and CLIQUE_TYPE='"+cliquetype+"'";
		}
		
		String[] cloumnsName = null;
		if (!"".equals(totalNumFlag)) {
			cloumnsName = new String[]{"CLIQUE_POOL_NAME","CODE","CLIQUE_TYPE","STATION_NAME","STATION_CODE","OIL_STATION_NAME","AREA_NAME","AREA_CLASS_NAME","RTU_CODE","JRBZ","LJJD_S","INSTALL_DATE","REMARK"};
		}else{
			//"IP","STATION_NO",
			cloumnsName = new String[]{"CLIQUE_POOL_NAME","CODE","CLIQUE_TYPE","STATION_NAME","STATION_CODE","OIL_STATION_NAME","AREA_NAME","AREA_CLASS_NAME","RTU_CODE","JRBZ","LJJD_S","INSTALL_DATE","RLAST_OPER","RLAST_ODATE","REMARK","JRBZ_ID","LJJD_ID","STATION_ID","ORG_ID","CLIQUE_ID"};
		}
		
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
		if(!"".equals(sort) && !"CLIQUE_POOL_NAME".equals(sort)){
			for (String cloumn : cloumnsName) {
				if(cloumn.equals(sort)){
					boilersInfo +=" order by t." + cloumn + " " + order;
					break;
				}
				
			}
		}
		else {
			boilersInfo +=" order by nlssort(CLIQUE_POOL_NAME, 'NLS_SORT=SCHINESE_STROKE_M') ";
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
//				if (o[0] != null) {
//					o[0] = String.valueOf(o[0]).substring(0,10);
//				}
				for (int i = 0; i < o.length; i++) {
					
//						if(i == 2){
//							if("01".equals(CommonsUtil.getClearNullData(o[i]))){
//								tjo.put(cloumnsName[i], "油");
//							}else if("02".equals(CommonsUtil.getClearNullData(o[i]))){
//								tjo.put(cloumnsName[i], "气");
//							}else if("03".equals(CommonsUtil.getClearNullData(o[i]))){
//								tjo.put(cloumnsName[i], "水");
//							}else{
//								tjo.put(cloumnsName[i], CommonsUtil.getClearNullData(o[i]));
//							}
//							
//						}else{
							tjo.put(cloumnsName[i], CommonsUtil.getClearNullData(o[i]));
//						}
						
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
	
	
	public boolean addOrUpdateData(PcCdCliquePoolT pool)throws Exception{
		List<PcCdCliquePoolT> list = new ArrayList<PcCdCliquePoolT>();
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
	public List<PcCdCliquePoolT> searchData(String id, String name)
			throws Exception {
		String hql = "FROM PcCdCliquePoolT t WHERE 1=1 ";
		if(id != null && !"".equals(id)){
			hql += " and t.cliqueId = '"+id+"'";
		}else if(name != null && !"".equals(name)){
			hql += " and t.cliquePoolName = '"+name+"'";
		}
		return (List<PcCdCliquePoolT>) commonDao.searchClassQuery(hql);
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
	

}
