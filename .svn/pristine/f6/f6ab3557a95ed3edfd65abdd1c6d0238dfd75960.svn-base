package com.echo.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.beans.PageControl;
import com.echo.dao.WaterSWRDDao;
import com.echo.service.WaterSWRDService;
import com.echo.util.DateBean;

public class WaterSWRDServiceImpl implements WaterSWRDService{
	private WaterSWRDDao waterSWRDDao;
	
	public void setWaterSWRDDao(WaterSWRDDao waterSWRDDao) {
		this.waterSWRDDao = waterSWRDDao;
	}

	public HashMap<String,Object> searchData(String qk,String zh,String jh,String gh,String name,String stime,String etime,int pageNo,String sort,String order,int rowsPerpage,String totalNumFlag) {
		JSONArray jsonArr = null;
		JSONObject tjo = null;
		HashMap<String,Object> map = new HashMap<String, Object>();
		String cloums = "select   ";
		Date today = new Date();
		String nowdate = DateBean.getTimeStr(today);
		if("".equals(stime)){
			stime = nowdate.substring(0, 10) + " 00:00:00";
		}else{
			stime = stime + ":00";
		}
		if("".equals(etime)){
			etime = nowdate.substring(0, 10) + " 23:59:59";
		}else{
			etime = etime + ":00";
		}
		String formTable = " from pc_rd_water_sour_v  where 1=1  and cjsj between TO_DATE('"+stime+"','YYYY-MM-DD hh24:mi:ss') and TO_DATE('"+etime+"','YYYY-MM-DD hh24:mi:ss') ";
		String totalNum = "select count(*) ";
		if(!qk.equals("")&&qk!=null&&!qk.equals("undefined")&!qk.equals("全部")){
			formTable=formTable+" and CNAME='"+qk+"'";
		}
		if(!zh.equals("")&&zh!=null&&!zh.equals("undefined")){
			formTable=formTable+" and ZNAME='"+zh+"'";
		}
//		if(!jh.equals("")&&jh!=null&&!jh.equals("undefined")){
//			formTable=formTable+" and WELL_NAME='"+jh+"'";
//		}
//		if(!gh.equals("")&&jh!=null&&!gh.equals("undefined")){
//			formTable=formTable+" and pid='"+gh+"'";
//		}
		if(!name.equals("")&&name!=null&&!name.equals("undefined")){
			formTable=formTable+" and WELL_NAME like '%"+name+"%'";
		}
		
		String[] cloumnsName = {"WELL_NAME","CNAME","ZNAME","CJSJ","WATER_SOURCE_WELLRID","WATER_SOURCE_WELLID","INSTANTANEOUS_DELIVERY","LIQUID_LEVEL","PRES","CURRENTA","CURRENTB","CURRENTC",
				"FREQUENCY","YAC_FLOW","TAC_FLOW","CUMULATIVE_DISCHARGE","CUMULATIVE_TIME","REV_STOP_STATE"};
		String kk="WELL_NAME";
		for(int m=1;m<cloumnsName.length;m++){
			if("CJSJ".equals(cloumnsName[m])){
				kk=kk+","+"to_char(CJSJ,'YYYY-MM-DD hh24:mi:ss') as CJSJ";
			}
//			else if("CUMULATIVE_TIME".equals(cloumnsName[m])) {
//				kk=kk+","+"to_char(CUMULATIVE_TIME,'YYYY-MM-DD hh24:mi:ss') as CUMULATIVE_TIME";
//			}
			else {
				kk=kk+","+cloumnsName[m];
			}
		}
		if ("export".equals(totalNumFlag)) {
			String[] cloumnsNames2 = {"WELL_NAME","CJSJ","CNAME","ZNAME","INSTANTANEOUS_DELIVERY","LIQUID_LEVEL","PRES","CURRENTA","CURRENTB","CURRENTC",
					"FREQUENCY","YAC_FLOW","TAC_FLOW","CUMULATIVE_DISCHARGE","CUMULATIVE_TIME","REV_STOP_STATE"};
			kk = "WELL_NAME";
			for(int m=1;m<cloumnsNames2.length;m++){
				if("CJSJ".equals(cloumnsNames2[m])){
					kk=kk+","+"to_char(CJSJ,'YYYY-MM-DD hh24:mi:ss') as CJSJ";
				}
//				else if("CUMULATIVE_TIME".equals(cloumnsName[m])) {
//					kk=kk+","+"to_char(CUMULATIVE_TIME,'YYYY-MM-DD hh24:mi:ss') as CUMULATIVE_TIME";
//				}
				else {
					kk=kk+","+cloumnsNames2[m];
				}
			}
		}
		String thinOilWellRD = cloums + kk+formTable;
		//获取总条数
		int total = 0;
		if ("1".equals(totalNum)) {
			total = 1;
		} else {
			totalNum += formTable;
			total = waterSWRDDao.getCounts(totalNum);
		}
		if ("totalNum".equals(totalNumFlag)) {
			map.put("totalNum", total + "");
			return map;
		}
		//排序
		if(!"".equals(sort) && !"CJSJ".equals(sort)){
			for (String cloumn : cloumnsName) {
				if(cloumn.equals(sort)){
					thinOilWellRD +=" order by " + cloumn + " " + order;
					break;
				}
				
			}
		}
		else {
			thinOilWellRD +=" order by CJSJ ";
		}
		//计算分页
		PageControl control = new PageControl();
		//当前页
		List<Object[]> lo = new ArrayList<Object[]>();
		control.setInt_num(rowsPerpage);
		if ("export".equals(totalNumFlag)) {
			lo = waterSWRDDao.searchData(thinOilWellRD);
		}
		else {
			control.init(pageNo, total);
			//开始条数
			int start = control.getStart()-1;
			lo = waterSWRDDao.searchData(thinOilWellRD,start,rowsPerpage);
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
	
}
