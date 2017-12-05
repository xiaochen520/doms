package com.echo.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.beans.PageControl;
import com.echo.dao.StationRDDao;
import com.echo.service.StationRDService;
import com.echo.util.DateBean;

public class StationRDServiceImpl implements StationRDService{
	private StationRDDao stationRDDao;
	

	public void setStationRDDao(StationRDDao stationRDDao) {
		this.stationRDDao = stationRDDao;
	}


	@Override
	public HashMap<String, Object> searchData(String cyz, String zzz,
			String qkmc,String stime,String etime,int pageNo,String sort,String order,int rowsPerpage,String totalNumFlag)throws Exception{

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
		String formTable = " from PC_RD_BSTATION_TURNOIL_v  where 1=1  and ACQUISITION_TIME between TO_DATE('"+stime+"','YYYY-MM-DD hh24:mi:ss') and TO_DATE('"+etime+"','YYYY-MM-DD hh24:mi:ss') ";
		String totalNum = "select count(*) ";
		if(!cyz.equals("")&&cyz!=null&&!cyz.equals("undefined")&!cyz.equals("全部")){
			formTable=formTable+" and CYZ='"+cyz+"'";
		}
		if(!zzz.equals("")&&zzz!=null&&!zzz.equals("undefined")){
			formTable=formTable+" and BLOCKSTATION_NAME='"+zzz+"'";
		}
		if(!qkmc.equals("")&&qkmc!=null&&!qkmc.equals("undefined")){
			formTable=formTable+" and qkmc='"+qkmc+"'";
		}
//		if(!gh.equals("")&&jh!=null&&!gh.equals("undefined")){
//			formTable=formTable+" and pid='"+gh+"'";
//		}
//		if(!name.equals("")&&name!=null&&!name.equals("undefined")){
//			formTable=formTable+" and WELL_NAME='"+name+"'";
//		}
		
//		'接转站--BLOCKSTATION_NAME
//		'采集时间--ACQUISITION_TIME
//		'接转站转油动态ID--BSTATION_TURNOILRID
//		'接转站ID--BLOCKSTATIONID
//		'接转站类型--BLOCKSTATIONTYPE
//		'采油站--CYZ
//		'运行组--YXZ
//		'区块--QKMC
//		'外输压力--EFFLUX_PRES
//		'外输温度--EFFLUX_TEMP
//		'外输泵频率--EFFLUX_PUMP_FREQ
//		'1#罐液位--TANK_LIQUID_LEVEL_1
//		'2#罐液位--TANK_LIQUID_LEVEL_2
//		'罐间压力--AMONGST_TANKS_PRES
//		'1#硫化氢浓度--H2S_CONCENTRATION_1
//		'2#硫化氢浓度--H2S_CONCENTRATION_2
//		'1#泵运行状态--EFFLUX_PUMP_STATE_1
//		'2#泵运行状态--EFFLUX_PUMP_STATE_2
//		'日产量计算系数--DAILY_OUTPUT_COEFFICIENT
		
		String[] cloumnsName = {"BLOCKSTATION_NAME","ACQUISITION_TIME","BSTATION_TURNOILRID","BLOCKSTATIONID","BLOCKSTATIONTYPE","CYZ","YXZ","QKMC","EFFLUX_PRES","EFFLUX_TEMP",
				"EFFLUX_PUMP_FREQ","TANK_LIQUID_LEVEL_1","TANK_LIQUID_LEVEL_2","AMONGST_TANKS_PRES","H2S_CONCENTRATION_1","H2S_CONCENTRATION_2","EFFLUX_PUMP_STATE_1",
				"EFFLUX_PUMP_STATE_2","DAILY_OUTPUT_COEFFICIENT"};
		String[] cloumnsNames2 = {"BLOCKSTATION_NAME","ACQUISITION_TIME","BLOCKSTATIONTYPE","CYZ","YXZ","QKMC","EFFLUX_PRES","EFFLUX_TEMP",
				"EFFLUX_PUMP_FREQ","TANK_LIQUID_LEVEL_1","TANK_LIQUID_LEVEL_2","AMONGST_TANKS_PRES","H2S_CONCENTRATION_1","H2S_CONCENTRATION_2","EFFLUX_PUMP_STATE_1",
				"EFFLUX_PUMP_STATE_2","DAILY_OUTPUT_COEFFICIENT"};
		String kk="BLOCKSTATION_NAME";
		for(int m=1;m<cloumnsName.length;m++){
			if("ACQUISITION_TIME".equals(cloumnsName[m])){
				kk=kk+","+"to_char(ACQUISITION_TIME,'YYYY-MM-DD hh24:mi:ss') as ACQUISITION_TIME";
			}
//			else if("CUMULATIVE_TIME".equals(cloumnsName[m])) {
//				kk=kk+","+"to_char(CUMULATIVE_TIME,'YYYY-MM-DD hh24:mi:ss') as CUMULATIVE_TIME";
//			}
			else {
				kk=kk+","+cloumnsName[m];
			}
		}
		if ("export".equals(totalNumFlag)) {
			
			kk = "BLOCKSTATION_NAME";
			for(int m=1;m<cloumnsNames2.length;m++){
				if("ACQUISITION_TIME".equals(cloumnsNames2[m])){
					kk=kk+","+"to_char(ACQUISITION_TIME,'YYYY-MM-DD hh24:mi:ss') as ACQUISITION_TIME";
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
			total = stationRDDao.getCounts(totalNum);
		}
		if ("totalNum".equals(totalNumFlag)) {
			map.put("totalNum", total + "");
			return map;
		}
		//排序
		if(!"".equals(sort) && !"ACQUISITION_TIME".equals(sort)){
			for (String cloumn : cloumnsName) {
				if(cloumn.equals(sort)){
					thinOilWellRD +=" order by " + cloumn + " " + order;
					break;
				}
				
			}
		}
		else {
			thinOilWellRD +=" order by ACQUISITION_TIME ";
		}
		//计算分页
		PageControl control = new PageControl();
		//当前页
		List<Object[]> lo = new ArrayList<Object[]>();
		control.setInt_num(rowsPerpage);
		if ("export".equals(totalNumFlag)) {
			lo = stationRDDao.searchData(thinOilWellRD);
		}
		else {
			control.init(pageNo, total);
			//开始条数
			int start = control.getStart()-1;
			if ("export".equals(totalNumFlag)) {
				lo = stationRDDao.searchData(thinOilWellRD,cloumnsNames2,start,rowsPerpage);
			}else{
				lo = stationRDDao.searchData(thinOilWellRD,cloumnsName,start,rowsPerpage);
			}
			
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
