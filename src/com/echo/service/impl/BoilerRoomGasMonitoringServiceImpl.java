package com.echo.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.beans.PageControl;
import com.echo.dao.BoilerRoomGasMonitoringDataDao;
import com.echo.service.BoilerRoomGasMonitoringService;
import com.echo.util.DateBean;

/**
 * 
 * @author  王博
 * @date 2017-5-3
 * @time 下午04:15:37
 *
 */
public class BoilerRoomGasMonitoringServiceImpl implements BoilerRoomGasMonitoringService{
     private  BoilerRoomGasMonitoringDataDao boilerRoomGasMonitoringDataDao;


     /*
 	 * @param turnNote 接转站
 	 * @param stime 开始时间
 	 * @param etime 结束时间
 	 * @param pageNo
 	 * @param sort
 	 * @param order
 	 * @param rowsPerpage
 	 * @param totalNum
 	 * @return
 	 * @throws Exception
 	 */
     @Override
 	public HashMap<String,Object> searchData(String turnNote ,String stime,String etime,int pageNo,String sort,String order,int rowsPerpage,String totalNumFlag)throws Exception{
		JSONArray jsonArr = null;
		JSONObject tjo = null;
		HashMap<String,Object> map = new HashMap<String, Object>();
		String select = "select   ";
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
		String formTable = " from PC_RD_BOILER_H2S_T  where 1=1  and ACQUISITION_TIME between TO_DATE('"+stime+"','YYYY-MM-DD hh24:mi:ss') and TO_DATE('"+etime+"','YYYY-MM-DD hh24:mi:ss') ";
		String totalNum = "select count(*) ";

		if(!turnNote.equals("")&&turnNote!=null&&!turnNote.equals("undefined")){
			formTable=formTable+" and BLOCKSTATION_NAME='"+turnNote+"'";   
		}
	
		 String[] cloumnsName = {"BLOCKSTATION_NAME","ACQUISITION_TIME",
				 "H2S_ND_1","KRQT_ND_1",
				 "H2S_ND_2","KRQT_ND_2",
				 "H2S_ND_3","KRQT_ND_3",
				 "H2S_ND_4","KRQT_ND_4",
				 "H2S_ND_5","KRQT_ND_5" };
		 String[] cloumnsNames2 = {"BLOCKSTATION_NAME","ACQUISITION_TIME",
				 "H2S_ND_1","KRQT_ND_1",
				 "H2S_ND_2","KRQT_ND_2",
				 "H2S_ND_3","KRQT_ND_3",
				 "H2S_ND_4","KRQT_ND_4",
				 "H2S_ND_5","KRQT_ND_5" };
		String CLOUMN="BLOCKSTATION_NAME";
		for(int m=1;m<cloumnsName.length;m++){
			if("ACQUISITION_TIME".equals(cloumnsName[m])){
				CLOUMN=CLOUMN+","+"to_char(ACQUISITION_TIME,'YYYY-MM-DD hh24:mi:ss') as ACQUISITION_TIME";
			}
			else {
				CLOUMN=CLOUMN+","+cloumnsName[m];
			}
		}
		String performSQL = select + CLOUMN+formTable;
		//获取总条数
		int total = 0;
		if ("1".equals(totalNum)) {
			total = 1;
		} else {
			totalNum += formTable;
			total = boilerRoomGasMonitoringDataDao.getCounts(totalNum);
		}
		if ("totalNum".equals(totalNumFlag)) {
			map.put("totalNum", total + "");
			return map;
		}
		//排序
		if(!"".equals(sort) && !"ACQUISITION_TIME".equals(sort)){
			for (String cloumn : cloumnsName) {
				if(cloumn.equals(sort)){
					performSQL +=" order by " + cloumn + " " + order;
					break;
				}
				
			}
		}
		else {
			performSQL +=" order by ACQUISITION_TIME ";
		}
		//计算分页
		PageControl control = new PageControl();
		//当前页
		List<Object[]> lo = new ArrayList<Object[]>();
		control.setInt_num(rowsPerpage);
		if ("export".equals(totalNumFlag)) {
			lo = boilerRoomGasMonitoringDataDao.searchData(performSQL);
		}
		else {
			control.init(pageNo, total);
			//开始条数
			int start = control.getStart()-1;
			if ("export".equals(totalNumFlag)) {
				lo = boilerRoomGasMonitoringDataDao.searchData(performSQL,cloumnsNames2,start,rowsPerpage);
			}else{
				lo = boilerRoomGasMonitoringDataDao.searchData(performSQL,cloumnsName,start,rowsPerpage);
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


	public BoilerRoomGasMonitoringDataDao getBoilerRoomGasMonitoringDataDao() {
		return boilerRoomGasMonitoringDataDao;
	}


	public void setBoilerRoomGasMonitoringDataDao(
			BoilerRoomGasMonitoringDataDao boilerRoomGasMonitoringDataDao) {
		this.boilerRoomGasMonitoringDataDao = boilerRoomGasMonitoringDataDao;
	}
	
	

}
