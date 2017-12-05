package com.echo.service.impl;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.beans.PageControl;
import com.echo.dao.CommonDao;
import com.echo.dto.PcRpdClosingWellbT;
import com.echo.dto.PcWellDowntime;
import com.echo.service.CloseWellService;
import com.echo.util.CommonsUtil;
import com.echo.util.DateBean;

public class CloseWellServiceImpl implements CloseWellService {
	private CommonDao commonDao;

	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}

	@Override
	public boolean removeDatas(String id) throws Exception{
		String sql = "delete from pc_well_downtime t where t.closing_well_id ='"+id+"'";
		
		return commonDao.removeData(sql);
	}

	@Override
	public boolean saveOrUpdate(PcWellDowntime wellInfo)throws Exception {
		// TODO Auto-generated method stub
		List<PcWellDowntime> list = new ArrayList<PcWellDowntime>();
		list.add(wellInfo);
		return commonDao.addOrUpdateDatas(list);
	}

	@Override
	public List<PcWellDowntime> searchOneData(String id,String name,String param,String param1) throws Exception {
		String hql = " from PcWellDowntime t where 1=1 ";
		if(id != null && !"".equals(id)){
			hql += " and t.closingWellId ='"+id+"' ";
		}
		
		if(name != null && !"".equals(name)){
			hql += " and t.wellId ='"+name+"' ";
				}
		
		if(param != null && !"".equals(param)){
			param = DateBean.getParamTime(param);
			hql += " and t.startDatetime = to_char('"+param+"','YYYY-MM-DD HH24:MI:SS')";
		}
		
		if(param1 != null && !"".equals(param1)){
			param1 = DateBean.getParamTime(param1);
			hql += " and t.endDatetime = to_char('"+param1+"','YYYY-MM-DD HH24:MI:SS')";
		}
		
		
		return (List<PcWellDowntime>) commonDao.searchClassQuery(hql);
	}

	@Override
	public HashMap<String, Object> searchDatas(String totalNumFlag, String CYZ,
			String TEAM, String WELLNAME, String tableid,String startDate,String endDate, int pageNo,
			String sort, String order, int rowsPerpage) throws Exception{
		JSONArray jsonArr = null;
		JSONObject tjo = null;
		HashMap<String,Object> map = new HashMap<String, Object>();
		String cloums = "select   ";
		String formTable = " from pc_rpd_closing_well_v  where 1=1 ";
//		if("B".equals(tableid)){
//			formTable = " from pc_rpd_closing_wellb_v  where 1=1 ";
//		}
		String totalNum = "select count(*) ";
		if(!CYZ.equals("")&&CYZ!=null&&!CYZ.equals("undefined")&!CYZ.equals("全部")){
			formTable=formTable+" and oil_station_name='"+CYZ+"'";
		}
		if(!TEAM.equals("")&&TEAM!=null&&!TEAM.equals("undefined")){
			formTable=formTable+" and team_station_name='"+TEAM+"'";
		}
		if(!WELLNAME.equals("")&&WELLNAME!=null&&!WELLNAME.equals("undefined")){
			formTable=formTable+" and WELL_ID like '%"+WELLNAME+"%'";
		}
		String nowdate = DateBean.getSystemTime();
		
		if("".equals(startDate)){
			startDate = nowdate.substring(0, 10) + " 00:00:00";
		}else{
			startDate = startDate + " 00:00:00";
		}
		if("".equals(endDate)){
			endDate = nowdate.substring(0, 10) + " 23:59:59";
		}else{
			endDate = endDate + " 23:59:59";
		}
		
//		startDate = DateBean.getParamTime(startDate);
		formTable=formTable+" and START_DATETIME between TO_DATE('"+startDate+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+endDate+"','YYYY-MM-DD HH24:MI:SS')";
		
//		if(endDate != null && !"".equals(endDate)){
//			endDate = DateBean.getParamTime(endDate);
//			formTable=formTable+" and START_DATETIME between TO_DATE('"+endDate+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+DateBean.getSystemTime()+"','YYYY-MM-DD HH24:MI:SS')";
//		}
		
		String[] cloumnsName = {"WELL_ID" ,"DOWNTIME_NAME" ,"START_DATETIME" ,	"END_DATETIME" ,"KEY_DOWN_TAG" ,"REMARK" ,"RLAST_OPER" ,"RLAST_ODATE" ,	"ORG_ID" ,"DOWNTIME_TYPE" ,"CLOSING_WELL_ID"};
		if (!"".equals(totalNumFlag)) {
			cloumnsName = new String[]{"WELL_ID" ,"DOWNTIME_NAME" ,"START_DATETIME" ,	"END_DATETIME" ,"KEY_DOWN_TAG" ,"REMARK"};
		}
		
		
		String kk = "";
		for(int m = 0 ;m<cloumnsName.length;m++){
			if("RLAST_ODATE".equals(cloumnsName[m])){
				kk += " to_char(RLAST_ODATE,'YYYY-MM-DD HH24:MI:SS') as RLAST_ODATE  ,";
			}else if("START_DATETIME".equals(cloumnsName[m])){
				kk += " to_char(START_DATETIME,'YYYY-MM-DD HH24:MI:SS') as START_DATETIME  ,";
			}else if("END_DATETIME".equals(cloumnsName[m])){
				kk += " to_char(END_DATETIME,'YYYY-MM-DD HH24:MI:SS') as END_DATETIME  ,";
			} else{
				kk=kk+cloumnsName[m]+",";
			}
		}
		kk = kk.substring(0, kk.length()-1);
		
		String sql = cloums + kk+formTable;
		
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
		if(!"".equals(sort) && !"WELL_ID".equals(sort)){
			for (String cloumn : cloumnsName) {
				if(cloumn.equals(sort)){
					sql +=" order by " + cloumn + " " + order;
					break;
				}
				
			}
		}
		else {
			sql +=" order by nlssort(WELL_ID, 'NLS_SORT=SCHINESE_STROKE_M') ";
		}
		
		
		List<Object[]> lo = null;
		if ("export".equals(totalNumFlag)) {
			lo = commonDao.searchEverySql(sql);
		}
		else {
			//计算分页
			PageControl control = new PageControl();
			//当前页
			control.setInt_num(rowsPerpage);
			control.init(pageNo, total);
			//开始条数
			int start = control.getStart()-1;
			lo = commonDao.searchPaging(sql, start, rowsPerpage, cloumnsName);
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
					
					if(i == 4){
						if ("1".equals(String.valueOf(o[4]))) {
							o[4] = "是";
						}else{
							o[4] = "否";
						}
					}
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

	@Override
	public JSONArray searchDOWNTIME_TYPE() throws Exception {
		JSONObject obj = null;
		JSONArray jArr =null;
		String sql = "select t.shutin_code,t.p_description from PC_PK_SHUTIN_TYPE_T t order by  nlssort(t.p_description, 'NLS_SORT=SCHINESE_STROKE_M')";
		List<Object[]> objList = commonDao.searchEverySql(sql);
		if(objList !=null && objList.size()>0){
			jArr = new JSONArray();
			for (Object[] entry : objList) {
				obj = new JSONObject();
				obj.put("text", entry[1]);
				obj.put("id", entry[0]);
				jArr.add(obj);
			}
			return jArr;
		}
		jArr = new JSONArray();
		obj= new JSONObject();
		obj.put("text", "");
		obj.put("id", "");
		jArr.add(obj);
		return jArr;
	}

	@Override
	public List<Object[]> searchDatas(String sql) throws Exception {
		return commonDao.searchEverySql(sql);
	}

	
	
}
