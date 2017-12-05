package com.echo.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.beans.PageControl;
import com.echo.dao.GasWellRDDao;
import com.echo.dao.ThinOilWellDao;
import com.echo.dao.ThinOilWellRDDao;
import com.echo.dto.PcCdThinoilWellT;
import com.echo.service.GasWellRDService;
import com.echo.service.ThinOilWellRDService;
import com.echo.service.ThinOilWellService;
import com.echo.util.DateBean;

public class GasWellRDServiceImpl implements GasWellRDService{
	private GasWellRDDao gasWellRDDao;
	
	
	
	
	public void setGasWellRDDao(GasWellRDDao gasWellRDDao) {
		this.gasWellRDDao = gasWellRDDao;
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
		String formTable = " from pc_rd_gas_well_v  where 1=1  and cjsj between TO_DATE('"+stime+"','YYYY-MM-DD hh24:mi:ss') and TO_DATE('"+etime+"','YYYY-MM-DD hh24:mi:ss') ";
		String totalNum = "select count(*) ";
		if(!qk.equals("")&&qk!=null&&!qk.equals("undefined")&&!qk.equals("全部")){
			formTable=formTable+" and OILSTATIONNAME='"+qk+"'";
		}
		if(!zh.equals("")&&zh!=null&&!zh.equals("undefined")){
			formTable=formTable+" and AREABLOCK='"+zh+"'";
		}
		if(!jh.equals("")&&jh!=null&&!jh.equals("undefined")){
			formTable=formTable+" and BLOCKSTATIONNAME='"+jh+"'";
		}
		if(!name.equals("")&&name!=null&&!name.equals("undefined")){
			formTable=formTable+" and GAS_WELLID='"+name+"'";
		}
		String[] cloumnsName = {"OILSTATIONNAME","AREABLOCK","BLOCKSTATIONNAME","GAS_WELLRID","GAS_WELLID","JHS","CJSJ","WELL_PRES","WELL_TEMP","INSTANTANEOUS_DELIVERY","CUMULATIVE_DISCHARGE"};
		String kk="OILSTATIONNAME";
		for(int m=1;m<cloumnsName.length;m++){
			if("CJSJ".equals(cloumnsName[m])){
				kk=kk+","+"to_char(CJSJ,'YYYY-MM-DD hh24:mi:ss') as CJSJ";
			}else{
				kk=kk+","+cloumnsName[m];
			}
		}
		if ("export".equals(totalNumFlag)) {
			String[] cloumnsNames2 = {"JHS","CJSJ","OILSTATIONNAME","AREABLOCK","BLOCKSTATIONNAME","WELL_PRES","WELL_TEMP","INSTANTANEOUS_DELIVERY","CUMULATIVE_DISCHARGE"};
			kk = "JHS";
			for(int m=1;m<cloumnsNames2.length;m++){
				if("CJSJ".equals(cloumnsNames2[m])){
					kk=kk+","+"to_char(CJSJ,'YYYY-MM-DD hh24:mi:ss') as CJSJ";
				}else{
					kk=kk+","+cloumnsNames2[m];
				}
			}
		}
		String boilersInfo = cloums + kk+formTable;
		//获取总条数
		int total = 0;
		if ("1".equals(totalNum)) {
			total = 1;
		} else {
			totalNum += formTable;
			total = gasWellRDDao.getCounts(totalNum);
		}
		if ("totalNum".equals(totalNumFlag)) {
			map.put("totalNum", total + "");
			return map;
		}
		//排序
		if(!"".equals(sort) && !"CJSJ".equals(sort)){
			for (String cloumn : cloumnsName) {
				if(cloumn.equals(sort)){
					boilersInfo +=" order by " + cloumn + " " + order;
					break;
				}
				
			}
		}
		else {
			boilersInfo +=" order by CJSJ ";
		}
		//计算分页
		PageControl control = new PageControl();
	
		control.setInt_num(rowsPerpage);

		List<Object[]> lo = new ArrayList<Object[]>();
		control.setInt_num(rowsPerpage);
		if ("export".equals(totalNumFlag)) {
			lo = gasWellRDDao.searchData(boilersInfo);
		}
		else {
			control.init(pageNo, total);
			//开始条数
			int start = control.getStart()-1;
			lo = gasWellRDDao.searchData(boilersInfo,start,rowsPerpage);
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
