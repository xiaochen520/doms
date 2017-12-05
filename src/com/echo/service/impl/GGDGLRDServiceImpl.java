package com.echo.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.echo.beans.PageControl;
import com.echo.dao.SRGLRDDao;
import com.echo.dto.PcCdBoilerT;
import com.echo.dto.PcRpdBoilerHighDryT;
import com.echo.service.GGDGLRDService;
import com.echo.util.CommonsUtil;
import com.echo.util.DateBean;


public class GGDGLRDServiceImpl implements GGDGLRDService{
	private SRGLRDDao srglRDDao;
	
	

	public void setSrglRDDao(SRGLRDDao srglRDDao) {
		this.srglRDDao = srglRDDao;
	}



	public HashMap<String,Object> searchData(String oilationname,String areablock,String blockstationname,String ghname,String boilersName,String acceptunit,String stime,String etime,int pageNo,String sort,String order,int rowsPerpage,String pageCode,String totalNumFlag) throws Exception{
		JSONArray jsonArr = null;
		JSONObject tjo = null;
		HashMap<String,Object> map = new HashMap<String, Object>();

		Date today = new Date();
		String nowdate = DateBean.getTimeStr(today);
		if("".equals(stime)){
			stime = nowdate.substring(0, 10) + " 00:00:00";
		}else{
			//stime = stime;
		}
		if("".equals(etime)){
			etime = nowdate.substring(0, 10) + " 23:59:59";
		}else{
			//etime = etime;
		}
		
		
		String cloums = "select   ";
		String formTable = " from PC_RD_BOILER_HIGH_DRY_V  where 1=1  and ACQUISITION_TIME between TO_DATE('"+stime+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+etime+"','YYYY-MM-DD HH24:MI:SS') ";
		String totalNum = "select count(*) ";
		if ("日报".equals(pageCode) || "rb".equals(pageCode)|| "日报维护".equals(pageCode)) {
			formTable = " from PC_RPD_BOILER_HIGH_DRY_V  where 1=1  and REPORT_DATE between TO_DATE('"+stime+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+etime+"','YYYY-MM-DD HH24:MI:SS') ";
		}
		if(!oilationname.equals("")&&oilationname!=null&&!oilationname.equals("undefined")){
			formTable=formTable+" and oilationname='"+oilationname+"'";
		}
			if(!"".equals(areablock)){
				formTable=formTable+" and BLOCK_NAME = '"+areablock+"'";
			}
			
			if(!blockstationname.equals("")&&blockstationname!=null&&!blockstationname.equals("undefined")){
				formTable=formTable+" and BLOCKSTATION_NAME='"+blockstationname+"'";
			}

			if(!boilersName.equals("")&&boilersName!=null&&!boilersName.equals("undefined")){
				formTable=formTable+" and BOILER_NAME='"+boilersName+"'";
			}
			if(!acceptunit.equals("")&&acceptunit!=null&&!acceptunit.equals("undefined")&!acceptunit.equals("全部")){
				formTable=formTable+" and OILDRILLING_STATION_NAME='"+acceptunit+"'";
			}
//		String[] cloumnsName = {"BLOCK_NAME","BOILER_NAME","ACQUISITION_TIME","RUN_STATUS","GAS_INTO_PRESS","GAS1_PRESS","FIRE_MEASURE","GAS_FLOW","PUMPIN_PRESS","PUMPOUT_TEMP"
//				,"PUMPFC_FREQUENCY","WATERSUPPLY_FLOW","STEAMIN_TEMP","STEAMOUT_TEMP","STEAM_DRYNESS","RS_PRESS_REDUCTION","SUPERHEAT_PRESS_REDUCTION","RERHEAT_PRESS_REDUCTION","MIXER_PRESS_REDUCTION"
//				,"PUMPOUT_PRESS","CSIN_PRESS","CSIN_TEMP","CSOUT_PRESS","CSOUT_TEMP","CONVECTIONR_PRESS_REDUCTION","RSIN_PRESS","RSIN_TEMP","RS_PRESS","RSOUT_TEMP","RS_TEMP"
//				,"REHEAT_PRESS_ENTRANCE","REHEAT_TEMP_ENTRANCE","REHEAT_TEMP_EXPORT","GAS2_PRESS","GAS3_PRESS","LUBE_TEMP","BURNER_TEMP","HEARTH_PRESS","EJECTSMOKE_TEMP"
//				,"SYSTEM_VOLTAGE","PUMP_MOTOR_CURRENT","PUMP_MOTOR_TEMP","FAN_MOTOR_CURRENT","STEAM_WORK_GROUP","OILDRILLING_STATION_NAME","STEAM_INJE_UNIT","FAN_AIR_INTAKE_TEMP","FUEL_GAS_DENSITY","H2S_DENSITY","SYSTEM_PRESS_REDUCTION"};
//		String[] cloumnsNames ={"BLOCK_NAME","BOILER_NAME","REPORT_DATE","RUNTIME","GAS_INTO_PRESS",
//								"GAS1_PRESS","FIRE_MEASURE","GAS_FLOW","PUMPIN_PRESS","PUMPOUT_TEMP",
//								"PUMPFC_FREQUENCY","WATERSUPPLY_FLOW","STEAMIN_TEMP","STEAMOUT_TEMP",
//								"STEAM_DRYNESS","PUMPOUT_PRESS","CSIN_PRESS","CSIN_TEMP","CSOUT_PRESS",
//								"CSOUT_TEMP","CONVECTIONR_PRESS_REDUCTION","RSIN_PRESS,RSIN_TEMP",
//								"RS_PRESS","RSOUT_TEMP","RS_TEMP","RS_PRESS_REDUCTION","REHEAT_PRESS_ENTRANCE",
//								"REHEAT_TEMP_ENTRANCE","REHEAT_TEMP_EXPORT","RERHEAT_PRESS_REDUCTION","GAS2_PRESS","GAS3_PRESS",
//								"LUBE_TEMP","BURNER_TEMP","HEARTH_PRESS","EJECTSMOKE_TEMP","SYSTEM_VOLTAGE","PUMP_MOTOR_CURRENT",
//								"PUMP_MOTOR_TEMP","FAN_MOTOR_CURRENT","FUEL_GAS_DENSITY","H2S_DENSITY","SEWAGE_BUFFER_TANK",
//								"DAILY_CUMULATIVE_GAS","DAILY_CUMULATIVE_STEAM","STEAM_WORK_GROUP","OILDRILLING_STATION_NAME","STEAM_INJE_UNIT","REMARK"}; 
//		
//				//{"BLOCK_NAME","BOILER_NAME","GAS_INTO_PRESS","GAS1_PRESS","FIRE_MEASURE","GAS_FLOW","PUMPIN_PRESS","PUMPOUT_TEMP"
//				//,"PUMPFC_FREQUENCY","WATERSUPPLY_FLOW","STEAMIN_TEMP","STEAMOUT_TEMP","STEAM_DRYNESS","RS_PRESS_REDUCTION","SUPERHEAT_PRESS_REDUCTION","RERHEAT_PRESS_REDUCTION","MIXER_PRESS_REDUCTION"
//				//,"PUMPOUT_PRESS","CSIN_PRESS","CSIN_TEMP","CSOUT_PRESS","CSOUT_TEMP","CONVECTIONR_PRESS_REDUCTION","RSIN_PRESS","RSIN_TEMP","RS_PRESS","RSOUT_TEMP","RS_TEMP"
//				//,"REHEAT_PRESS_ENTRANCE","REHEAT_TEMP_ENTRANCE","REHEAT_TEMP_EXPORT","GAS2_PRESS","GAS3_PRESS","LUBE_TEMP","BURNER_TEMP","HEARTH_PRESS","EJECTSMOKE_TEMP"
//				//,"SYSTEM_VOLTAGE","PUMP_MOTOR_CURRENT","PUMP_MOTOR_TEMP","FAN_MOTOR_CURRENT","STEAM_WORK_GROUP","OILDRILLING_STATION_NAME","STEAM_INJE_UNIT","FAN_AIR_INTAKE_TEMP","FUEL_GAS_DENSITY","H2S_DENSITY","SYSTEM_PRESS_REDUCTION","RUNTIME","RPD_BOILER_HIGH_DRY_ID","REPORT_DATE","REMARK","CHECK_OPER","CHECK_DATE","RLAST_OPER","RLAST_ODATE"};
//		String kk="BLOCK_NAME";
//		for(int m=1;m<cloumnsName.length;m++){
//			if("ACQUISITION_TIME".equals(cloumnsName[m])){
//				if ("日报".equals(pageCode)) {
//					continue;
//				}
//				kk=kk+","+"to_char(ACQUISITION_TIME,'YYYY-MM-DD hh24:mi:ss') as ACQUISITION_TIME"; 
//			}else{
//				if("RUN_STATUS".equals(cloumnsName[m]) && "日报".equals(pageCode)){
//					continue;
//				}
//				kk=kk+","+cloumnsName[m];
//			}
//			if ("日报".equals(pageCode) && "SYSTEM_PRESS_REDUCTION".equals(cloumnsName[m])) {
//				kk=kk+",RPD_BOILER_HIGH_DRY_ID,to_char(REPORT_DATE,'YYYY-MM-DD') as REPORT_DATE ,CHECK_OPER,to_char(CHECK_DATE,'YYYY-MM-DD HH24:MI') as CHECK_DATE,RLAST_OPER,to_char(RLAST_ODATE,'YYYY-MM-DD HH24:MI') as RLAST_ODATE";
//			}
//		}
//		String thinOilWellRD = cloums + kk+formTable;
//		if (!"".equals(totalNumFlag)) {
//			thinOilWellRD = cloums + " BLOCK_NAME,BOILER_NAME,to_char(ACQUISITION_TIME,'YYYY-MM-DD hh24:mi:ss') as ACQUISITION_TIME,RUN_STATUS,GAS_INTO_PRESS,GAS1_PRESS,FIRE_MEASURE,GAS_FLOW,PUMPIN_PRESS,PUMPOUT_TEMP,PUMPFC_FREQUENCY,WATERSUPPLY_FLOW,STEAMIN_TEMP,STEAMOUT_TEMP,STEAM_DRYNESS,RS_PRESS_REDUCTION,SUPERHEAT_PRESS_REDUCTION,RERHEAT_PRESS_REDUCTION,SYSTEM_PRESS_REDUCTION,PUMPOUT_PRESS,CSIN_PRESS,CSIN_TEMP,CSOUT_PRESS,CSOUT_TEMP,CONVECTIONR_PRESS_REDUCTION,RSIN_PRESS,RSIN_TEMP,RS_PRESS,RSOUT_TEMP,RS_TEMP,REHEAT_PRESS_ENTRANCE,REHEAT_TEMP_ENTRANCE,REHEAT_TEMP_EXPORT,GAS2_PRESS,GAS3_PRESS,LUBE_TEMP,FAN_AIR_INTAKE_TEMP,BURNER_TEMP,HEARTH_PRESS,EJECTSMOKE_TEMP,SYSTEM_VOLTAGE,PUMP_MOTOR_CURRENT,PUMP_MOTOR_TEMP,FAN_MOTOR_CURRENT,FUEL_GAS_DENSITY,H2S_DENSITY,STEAM_WORK_GROUP,OILDRILLING_STATION_NAME,STEAM_INJE_UNIT " +formTable;
//			if ("日报".equals(pageCode) || "rb".equals(pageCode) ) {
//				thinOilWellRD = cloums + " BLOCK_NAME,BOILER_NAME,to_char(REPORT_DATE,'YYYY-MM-DD hh24:mi:ss') as REPORT_DATE,RUNTIME,GAS_INTO_PRESS,GAS1_PRESS,FIRE_MEASURE,GAS_FLOW,PUMPIN_PRESS,PUMPOUT_TEMP,PUMPFC_FREQUENCY,WATERSUPPLY_FLOW,STEAMIN_TEMP,STEAMOUT_TEMP,STEAM_DRYNESS,PUMPOUT_PRESS,CSIN_PRESS,CSIN_TEMP,CSOUT_PRESS,CSOUT_TEMP,CONVECTIONR_PRESS_REDUCTION,RSIN_PRESS,RSIN_TEMP,RS_PRESS,RSOUT_TEMP,RS_TEMP,RS_PRESS_REDUCTION,REHEAT_PRESS_ENTRANCE,REHEAT_TEMP_ENTRANCE,REHEAT_TEMP_EXPORT,RERHEAT_PRESS_REDUCTION,GAS2_PRESS,GAS3_PRESS,LUBE_TEMP,BURNER_TEMP,HEARTH_PRESS,EJECTSMOKE_TEMP,SYSTEM_VOLTAGE,PUMP_MOTOR_CURRENT,PUMP_MOTOR_TEMP,FAN_MOTOR_CURRENT,FUEL_GAS_DENSITY,H2S_DENSITY,SEWAGE_BUFFER_TANK,DAILY_CUMULATIVE_GAS,DAILY_CUMULATIVE_STEAM,STEAM_WORK_GROUP,OILDRILLING_STATION_NAME,STEAM_INJE_UNIT,REMARK " +formTable;
//				
//			}
//		}
//		//获取总条数
//		int total = 0;
//		if ("1".equals(totalNum)) {
//			total = 1;
//		}else if ("rb".equals(pageCode)){ 
//			totalNum += formTable;
//			total = srglRDDao.getCounts(totalNum);
//		}else {
//			totalNum += formTable;
//			total = srglRDDao.getCounts(totalNum);
//		}
//		if ("totalNum".equals(totalNumFlag)) {
//			map.put("totalNum", total + "");
//			return map;
//		}
//		//排序
//		if(!"".equals(sort) && (!"ACQUISITION_TIME".equals(sort) || !"REPORT_DATE".equals(sort))){
//			for (String cloumn : cloumnsName) {
//				if(cloumn.equals(sort)){
//					thinOilWellRD +=" order by " + cloumn + " " + order;
//					break;
//				}
//				
//			}
//		}
//		else if (!"日报".equals(pageCode) &&   !"rb".equals(pageCode)){
//			thinOilWellRD +=" order by ACQUISITION_TIME ";
//		}
//		else {
//			thinOilWellRD +=" order by REPORT_DATE ";
//		}
//		//计算分页
//		PageControl control = new PageControl();
//		//当前页
//		List<Object[]> lo = new ArrayList<Object[]>();
//		control.setInt_num(rowsPerpage);
//		if ("export".equals(totalNumFlag)) {
//			lo = srglRDDao.searchData(thinOilWellRD);
//		}
//		else {
//			control.init(pageNo, total);
//			//开始条数
//			int start = control.getStart()-1;
//			if ("日报".equals(pageCode)) {
//				lo = srglRDDao.searchData(thinOilWellRD,cloumnsNames,start,rowsPerpage);
//			}
//			else {
//				lo = srglRDDao.searchData(thinOilWellRD,cloumnsName,start,rowsPerpage);
//			}
//			if (lo != null && 0 < lo.size()) {
//				// 生成树节点json文档
//				jsonArr = new JSONArray();
//				for (Object[] o : lo) {
//					tjo = new JSONObject();
//					
//					for (int i = 0; i < o.length; i++) {
//						if (o[i] == null||o[i].equals("null")) {
//							o[i] = "";
//						}
//						if ("日报".equals(pageCode)) {
//							tjo.put(cloumnsNames[i], o[i].toString());
//							
//						}else {
//							tjo.put(cloumnsName[i], o[i].toString());
//						}
//					}
//					jsonArr.add(tjo);
//				}
//			}
//		}
			if ("日报".equals(pageCode) || "rb".equals(pageCode)) {
				String[] cloumnsNames ={"BLOCK_NAME","BOILER_NAME","REPORT_DATE","RUNTIME","GAS_INTO_PRESS",
						"GAS1_PRESS","FIRE_MEASURE","GAS_FLOW","PUMPIN_PRESS","PUMPOUT_TEMP",
						"PUMPFC_FREQUENCY","WATERSUPPLY_FLOW","STEAMIN_TEMP","STEAMOUT_TEMP",
						"STEAM_DRYNESS","PUMPOUT_PRESS","CSIN_PRESS","CSIN_TEMP","CSOUT_PRESS",
						"CSOUT_TEMP","CONVECTIONR_PRESS_REDUCTION","RSIN_PRESS","RSIN_TEMP","RS_PRESS",
						"RSOUT_TEMP","RS_TEMP","RS_PRESS_REDUCTION","REHEAT_PRESS_ENTRANCE",
						"REHEAT_TEMP_ENTRANCE","REHEAT_TEMP_EXPORT","RERHEAT_PRESS_REDUCTION","GAS2_PRESS","GAS3_PRESS",
						"LUBE_TEMP","FAN_AIR_INTAKE_TEMP","BURNER_TEMP","HEARTH_PRESS","EJECTSMOKE_TEMP","SYSTEM_VOLTAGE","PUMP_MOTOR_CURRENT",
						"PUMP_MOTOR_TEMP","FAN_MOTOR_CURRENT","FUEL_GAS_DENSITY","H2S_DENSITY","SEWAGE_BUFFER_TANK",
						"DAILY_CUMULATIVE_GAS","DAILY_CUMULATIVE_STEAM","STEAM_WORK_GROUP","OILDRILLING_STATION_NAME","STEAM_INJE_UNIT","REMARK"
						}; 
				String kk="BLOCK_NAME";
				for(int m=1;m<cloumnsNames.length;m++){
					if("REPORT_DATE".equals(cloumnsNames[m])){ 
						kk=kk+","+"to_char(REPORT_DATE,'YYYY-MM-DD hh24:mi') as CJSJS"; 
					}else if("CHECK_DATE".equals(cloumnsNames[m])){
						kk=kk+","+"to_char(CHECK_DATE,'YYYY-MM-DD hh24:mi') as CHECK_DATE"; 
					}else if("RLAST_ODATE".equals(cloumnsNames[m])){
						kk=kk+","+"to_char(RLAST_ODATE,'YYYY-MM-DD hh24:mi') as RLAST_ODATE"; 
					}else{
						kk=kk+","+cloumnsNames[m];
					}
				}
					
				String thinOilWellRD = cloums + kk+formTable;
				if ("日报".equals(pageCode) || "rb".equals(pageCode) ) {
					thinOilWellRD = cloums + " BLOCK_NAME,BOILER_NAME,to_char(REPORT_DATE,'YYYY-MM-DD hh24:mi:ss') as REPORT_DATE,RUNTIME,GAS_INTO_PRESS,GAS1_PRESS,FIRE_MEASURE,GAS_FLOW,PUMPIN_PRESS,PUMPOUT_TEMP,PUMPFC_FREQUENCY,WATERSUPPLY_FLOW,STEAMIN_TEMP,STEAMOUT_TEMP,STEAM_DRYNESS,PUMPOUT_PRESS,CSIN_PRESS,CSIN_TEMP,CSOUT_PRESS,CSOUT_TEMP,CONVECTIONR_PRESS_REDUCTION,RSIN_PRESS,RSIN_TEMP,RS_PRESS,RSOUT_TEMP,RS_TEMP,RS_PRESS_REDUCTION,REHEAT_PRESS_ENTRANCE,REHEAT_TEMP_ENTRANCE,REHEAT_TEMP_EXPORT,RERHEAT_PRESS_REDUCTION,GAS2_PRESS,GAS3_PRESS,LUBE_TEMP,FAN_AIR_INTAKE_TEMP,BURNER_TEMP,HEARTH_PRESS,EJECTSMOKE_TEMP,SYSTEM_VOLTAGE,PUMP_MOTOR_CURRENT,PUMP_MOTOR_TEMP,FAN_MOTOR_CURRENT,FUEL_GAS_DENSITY,H2S_DENSITY,SEWAGE_BUFFER_TANK,DAILY_CUMULATIVE_GAS,DAILY_CUMULATIVE_STEAM,STEAM_WORK_GROUP,OILDRILLING_STATION_NAME,STEAM_INJE_UNIT,REMARK " +formTable;
					
				}
					
				//获取总条数
				int total = 0;
				if ("1".equals(totalNum)) {
					total = 1;
				}else if ("rb".equals(pageCode)){ 
					totalNum += formTable;
					total = srglRDDao.getCounts(totalNum);
				}else {
					totalNum += formTable;
					total = srglRDDao.getCounts(totalNum);
				}
				if ("totalNum".equals(totalNumFlag)) {
					map.put("totalNum", total + "");
					return map;
				}
				//排序
				if(!"".equals(sort) && !"REPORT_DATE".equals(sort)){
					for (String cloumn : cloumnsNames) {
						if(cloumn.equals(sort)){
							thinOilWellRD +=" order by " + cloumn + " " + order;
							break;
						}
						
					}
				}
				else {
					thinOilWellRD +=" order by REPORT_DATE,BOILER_NAME ";
				}
				//计算分页
				PageControl control = new PageControl();
				//当前页
				List<Object[]> lo = new ArrayList<Object[]>();
				control.setInt_num(rowsPerpage);
				if ("export".equals(totalNumFlag)) {
					lo = srglRDDao.searchData(thinOilWellRD);
				}
				else {
					control.init(pageNo, total);
					//开始条数
					int start = control.getStart()-1;
						lo = srglRDDao.searchData(thinOilWellRD,cloumnsNames,start,rowsPerpage);
					if (lo != null && 0 < lo.size()) {
						// 生成树节点json文档
						jsonArr = new JSONArray();
						for (Object[] o : lo) {
							tjo = new JSONObject();
							
							for (int i = 0; i < o.length; i++) {
								if (o[i] == null||o[i].equals("null")) {
									o[i] = "";
								}else if( i < 3){
								
									tjo.put(cloumnsNames[i], o[i].toString());
								}else  if (i>3 && i< 47){
									tjo.put(cloumnsNames[i], CommonsUtil.getNotTwoData(o[i].toString()));
								}else{
									tjo.put(cloumnsNames[i], o[i].toString());
								}
									//tjo.put(cloumnsNames[i], o[i].toString());
							}
							jsonArr.add(tjo);
						}
					}
				}
				tjo = new JSONObject();
				tjo.put("Rows",jsonArr);
				tjo.put("Total",total);
				map.put("list", lo);
				map.put("json", tjo);
				return map;
			}else if ("日报维护".equals(pageCode) || "rb".equals(pageCode)) {
				String[] cloumnsNames ={"BLOCK_NAME","BOILER_NAME","BLOCKSTATION_NAME","REPORT_DATE","RUNTIME","GAS_INTO_PRESS",
						"GAS1_PRESS","FIRE_MEASURE","GAS_FLOW","PUMPIN_PRESS","PUMPOUT_TEMP",
						"PUMPFC_FREQUENCY","WATERSUPPLY_FLOW","STEAMIN_TEMP","STEAMOUT_TEMP",
						"STEAM_DRYNESS","PUMPOUT_PRESS","CSIN_PRESS","CSIN_TEMP","CSOUT_PRESS",
						"CSOUT_TEMP","CONVECTIONR_PRESS_REDUCTION","RSIN_PRESS","RSIN_TEMP","RS_PRESS",
						"RSOUT_TEMP","RS_TEMP","RS_PRESS_REDUCTION","REHEAT_PRESS_ENTRANCE",
						"REHEAT_TEMP_ENTRANCE","REHEAT_TEMP_EXPORT","RERHEAT_PRESS_REDUCTION","GAS2_PRESS","GAS3_PRESS",
						"LUBE_TEMP","FAN_AIR_INTAKE_TEMP","BURNER_TEMP","HEARTH_PRESS","EJECTSMOKE_TEMP","SYSTEM_VOLTAGE","PUMP_MOTOR_CURRENT",
						"PUMP_MOTOR_TEMP","FAN_MOTOR_CURRENT","FUEL_GAS_DENSITY","H2S_DENSITY","SEWAGE_BUFFER_TANK",
						"DAILY_CUMULATIVE_GAS","DAILY_CUMULATIVE_STEAM","STEAM_WORK_GROUP","OILDRILLING_STATION_NAME","STEAM_INJE_UNIT","REMARK"
						,"RPD_BOILER_HIGH_DRY_ID","CHECK_OPER","CHECK_DATE","RLAST_OPER","RLAST_ODATE"}; 
			
		String kk="BLOCK_NAME";
		for(int m=1;m<cloumnsNames.length;m++){
			if("REPORT_DATE".equals(cloumnsNames[m])){ 
				kk=kk+","+"to_char(REPORT_DATE,'YYYY-MM-DD') as REPORT_DATE"; 
			}else if("CHECK_DATE".equals(cloumnsNames[m])){
				kk=kk+","+"to_char(CHECK_DATE,'YYYY-MM-DD hh24:mi') as CHECK_DATE"; 
			}else if("RLAST_ODATE".equals(cloumnsNames[m])){
				kk=kk+","+"to_char(RLAST_ODATE,'YYYY-MM-DD hh24:mi') as RLAST_ODATE"; 
			}else{
				kk=kk+","+cloumnsNames[m];
			}
		}
			
		String thinOilWellRD = cloums + kk+formTable;
		if ("日报".equals(pageCode) || "rb".equals(pageCode) ) {
			thinOilWellRD = cloums + " BLOCK_NAME,BOILER_NAME,BLOCKSTATION_NAME,to_char(REPORT_DATE,'YYYY-MM-DD hh24:mi:ss') as REPORT_DATE,RUNTIME,GAS_INTO_PRESS,GAS1_PRESS,FIRE_MEASURE,GAS_FLOW,PUMPIN_PRESS,PUMPOUT_TEMP,PUMPFC_FREQUENCY,WATERSUPPLY_FLOW,STEAMIN_TEMP,STEAMOUT_TEMP,STEAM_DRYNESS,PUMPOUT_PRESS,CSIN_PRESS,CSIN_TEMP,CSOUT_PRESS,CSOUT_TEMP,CONVECTIONR_PRESS_REDUCTION,RSIN_PRESS,RSIN_TEMP,RS_PRESS,RSOUT_TEMP,RS_TEMP,RS_PRESS_REDUCTION,REHEAT_PRESS_ENTRANCE,REHEAT_TEMP_ENTRANCE,REHEAT_TEMP_EXPORT,RERHEAT_PRESS_REDUCTION,GAS2_PRESS,GAS3_PRESS,LUBE_TEMP,FAN_AIR_INTAKE_TEMP,BURNER_TEMP,HEARTH_PRESS,EJECTSMOKE_TEMP,SYSTEM_VOLTAGE,PUMP_MOTOR_CURRENT,PUMP_MOTOR_TEMP,FAN_MOTOR_CURRENT,FUEL_GAS_DENSITY,H2S_DENSITY,SEWAGE_BUFFER_TANK,DAILY_CUMULATIVE_GAS,DAILY_CUMULATIVE_STEAM,STEAM_WORK_GROUP,OILDRILLING_STATION_NAME,STEAM_INJE_UNIT,REMARK " +formTable;
			
		}
			
		//获取总条数
		int total = 0;
		if ("1".equals(totalNum)) {
			total = 1;
		}else if ("rb".equals(pageCode)){ 
			totalNum += formTable;
			total = srglRDDao.getCounts(totalNum);
		}else {
			totalNum += formTable;
			total = srglRDDao.getCounts(totalNum);
		}
		if ("totalNum".equals(totalNumFlag)) {
			map.put("totalNum", total + "");
			return map;
		}
		//排序
		if(!"".equals(sort) && !"REPORT_DATE".equals(sort)){
			for (String cloumn : cloumnsNames) {
				if(cloumn.equals(sort)){
					thinOilWellRD +=" order by " + cloumn + " " + order;
					break;
				}
				
			}
		}
		else {
			thinOilWellRD +=" order by REPORT_DATE,BOILER_NAME ";
		}
		//计算分页
		PageControl control = new PageControl();
		//当前页
		List<Object[]> lo = new ArrayList<Object[]>();
		control.setInt_num(rowsPerpage);
		if ("export".equals(totalNumFlag)) {
			lo = srglRDDao.searchData(thinOilWellRD);
		}
		else {
			control.init(pageNo, total);
			//开始条数
			int start = control.getStart()-1;
				lo = srglRDDao.searchData(thinOilWellRD,cloumnsNames,start,rowsPerpage);
				if (lo != null && 0 < lo.size()) {
					// 生成树节点json文档
					jsonArr = new JSONArray();
					for (Object[] o : lo) {
						tjo = new JSONObject();
						
						for (int i = 0; i < o.length; i++) {
							if (o[i] == null||o[i].equals("null")) {
								o[i] = "";
							}else if( i < 3){
							
								tjo.put(cloumnsNames[i], o[i].toString());
							}else  if (i>3 && i< 48){
								tjo.put(cloumnsNames[i], CommonsUtil.getNotTwoData(o[i].toString()));
							}else{
								tjo.put(cloumnsNames[i], o[i].toString());
							}
								//tjo.put(cloumnsNames[i], o[i].toString());
						}
						jsonArr.add(tjo);
					}
				}
		}
		tjo = new JSONObject();
		tjo.put("Rows",jsonArr);
		tjo.put("Total",total);
		map.put("list", lo);
		map.put("json", tjo);
		return map;
		}else{
			String[] cloumnsNames ={"BLOCK_NAME","BOILER_NAME","ACQUISITION_TIME","RUN_STATUS","GAS_INTO_PRESS",
					"GAS1_PRESS","FIRE_MEASURE","GAS_FLOW","PUMPIN_PRESS","PUMPOUT_TEMP",
					"PUMPFC_FREQUENCY","WATERSUPPLY_FLOW","STEAMIN_TEMP","STEAMOUT_TEMP",
					"STEAM_DRYNESS","PUMPOUT_PRESS","CSIN_PRESS","CSIN_TEMP","CSOUT_PRESS",
					"CSOUT_TEMP","CONVECTIONR_PRESS_REDUCTION","RSIN_PRESS","RSIN_TEMP","RS_PRESS",
					"RSOUT_TEMP","RS_PRESS_REDUCTION","RS_TEMP","REHEAT_PRESS_ENTRANCE",
					"REHEAT_TEMP_ENTRANCE","REHEAT_TEMP_EXPORT","RERHEAT_PRESS_REDUCTION","GAS2_PRESS","GAS3_PRESS",
					"LUBE_TEMP","FAN_AIR_INTAKE_TEMP","BURNER_TEMP","HEARTH_PRESS","EJECTSMOKE_TEMP","SYSTEM_VOLTAGE","PUMP_MOTOR_CURRENT",
					"PUMP_MOTOR_TEMP","FAN_MOTOR_CURRENT","FUEL_GAS_DENSITY","H2S_DENSITY","SEWAGE_BUFFER_TANK",
					"STEAM_WORK_GROUP","OILDRILLING_STATION_NAME","STEAM_INJE_UNIT"
					}; 
		
	String kk="BLOCK_NAME";
	for(int m=1;m<cloumnsNames.length;m++){
		if("ACQUISITION_TIME".equals(cloumnsNames[m])){ 
			kk=kk+","+"to_char(ACQUISITION_TIME,'YYYY-MM-DD hh24:mi') as ACQUISITION_TIME"; 
		}else if("CHECK_DATE".equals(cloumnsNames[m])){
			kk=kk+","+"to_char(CHECK_DATE,'YYYY-MM-DD hh24:mi') as CHECK_DATE"; 
		}else if("RLAST_ODATE".equals(cloumnsNames[m])){
			kk=kk+","+"to_char(RLAST_ODATE,'YYYY-MM-DD hh24:mi') as RLAST_ODATE"; 
		}else{
			kk=kk+","+cloumnsNames[m];
		}
	}
		
	String thinOilWellRD = cloums + kk+formTable;
	if ("export".equals(totalNumFlag)) {
		//thinOilWellRD = cloums + " BLOCK_NAME,BOILER_NAME,to_char(REPORT_DATE,'YYYY-MM-DD hh24:mi:ss') as REPORT_DATE,RUNTIME,GAS_INTO_PRESS,GAS1_PRESS,FIRE_MEASURE,GAS_FLOW,PUMPIN_PRESS,PUMPOUT_TEMP,PUMPFC_FREQUENCY,WATERSUPPLY_FLOW,STEAMIN_TEMP,STEAMOUT_TEMP,STEAM_DRYNESS,PUMPOUT_PRESS,CSIN_PRESS,CSIN_TEMP,CSOUT_PRESS,CSOUT_TEMP,CONVECTIONR_PRESS_REDUCTION,RSIN_PRESS,RSIN_TEMP,RS_PRESS,RSOUT_TEMP,RS_TEMP,RS_PRESS_REDUCTION,REHEAT_PRESS_ENTRANCE,REHEAT_TEMP_ENTRANCE,REHEAT_TEMP_EXPORT,RERHEAT_PRESS_REDUCTION,GAS2_PRESS,GAS3_PRESS,LUBE_TEMP,FAN_AIR_INTAKE_TEMP,BURNER_TEMP,HEARTH_PRESS,EJECTSMOKE_TEMP,SYSTEM_VOLTAGE,PUMP_MOTOR_CURRENT,PUMP_MOTOR_TEMP,FAN_MOTOR_CURRENT,FUEL_GAS_DENSITY,H2S_DENSITY,SEWAGE_BUFFER_TANK,DAILY_CUMULATIVE_GAS,DAILY_CUMULATIVE_STEAM,STEAM_WORK_GROUP,OILDRILLING_STATION_NAME,STEAM_INJE_UNIT,REMARK " +formTable;
		thinOilWellRD = cloums + " BLOCK_NAME,BOILER_NAME,to_char(ACQUISITION_TIME,'YYYY-MM-DD hh24:mi:ss') as ACQUISITION_TIME,RUN_STATUS,GAS_INTO_PRESS,"+
		"GAS1_PRESS,FIRE_MEASURE,GAS_FLOW,PUMPIN_PRESS,PUMPOUT_TEMP,"+
		"PUMPFC_FREQUENCY,WATERSUPPLY_FLOW,STEAMIN_TEMP,STEAMOUT_TEMP,"+
		"STEAM_DRYNESS,PUMPOUT_PRESS,CSIN_PRESS,CSIN_TEMP,CSOUT_PRESS,"+
		"CSOUT_TEMP,CONVECTIONR_PRESS_REDUCTION,RSIN_PRESS,RSIN_TEMP,RS_PRESS,"+
		"RSOUT_TEMP,RS_PRESS_REDUCTION,RS_TEMP,REHEAT_PRESS_ENTRANCE,"+
		"REHEAT_TEMP_ENTRANCE,REHEAT_TEMP_EXPORT,RERHEAT_PRESS_REDUCTION,GAS2_PRESS,GAS3_PRESS,"+
		"LUBE_TEMP,FAN_AIR_INTAKE_TEMP,BURNER_TEMP,HEARTH_PRESS,EJECTSMOKE_TEMP,SYSTEM_VOLTAGE,PUMP_MOTOR_CURRENT,"+
		"PUMP_MOTOR_TEMP,FAN_MOTOR_CURRENT,FUEL_GAS_DENSITY,H2S_DENSITY,SEWAGE_BUFFER_TANK,"+
		"STEAM_WORK_GROUP,OILDRILLING_STATION_NAME,STEAM_INJE_UNIT " +formTable;
	}
		
	//获取总条数
	int total = 0;
	if ("1".equals(totalNum)) {
		total = 1;
	}else if ("rb".equals(pageCode)){ 
		totalNum += formTable;
		total = srglRDDao.getCounts(totalNum);
	}else {
		totalNum += formTable;
		total = srglRDDao.getCounts(totalNum);
	}
	if ("totalNum".equals(totalNumFlag)) {
		map.put("totalNum", total + "");
		return map;
	}
	//排序
	if(!"".equals(sort) && !"ACQUISITION_TIME".equals(sort)){
		for (String cloumn : cloumnsNames) {
			if(cloumn.equals(sort)){
				thinOilWellRD +=" order by " + cloumn + " " + order;
				break;
			}
			
		}
	}
	else {
		thinOilWellRD +=" order by ACQUISITION_TIME,BOILER_NAME ";
	}
	//计算分页
	PageControl control = new PageControl();
	//当前页
	List<Object[]> lo = new ArrayList<Object[]>();
	control.setInt_num(rowsPerpage);
	if ("export".equals(totalNumFlag)) {
		lo = srglRDDao.searchData(thinOilWellRD);
	}
	else {
		control.init(pageNo, total);
		//开始条数
		int start = control.getStart()-1;
			lo = srglRDDao.searchData(thinOilWellRD,cloumnsNames,start,rowsPerpage);
		if (lo != null && 0 < lo.size()) {
			// 生成树节点json文档
			jsonArr = new JSONArray();
			for (Object[] o : lo) {
				tjo = new JSONObject();
				
				for (int i = 0; i < o.length; i++) {
					if (o[i] == null||o[i].equals("null")) {
						o[i] = "";
//					}else if( i < 3){
//					
//						tjo.put(cloumnsNames[i], o[i].toString());
//					}else  if (i>3 && i< 45){
//						tjo.put(cloumnsNames[i], CommonsUtil.getNotTwoData(o[i].toString()));
					}else{
						tjo.put(cloumnsNames[i], o[i].toString());
					}
						//tjo.put(cloumnsNames[i], o[i].toString());
				}
				jsonArr.add(tjo);
			}
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
	public HashMap<String,Object> searchRPData(String oil,String areablock,String station,String ghname,String boilersName,String stime,String etime,int pageNo,String sort,String order,int rowsPerpage,String totalNumFlag) {
		JSONArray jsonArr = null;
		JSONObject tjo = null;
		HashMap<String,Object> map = new HashMap<String, Object>();
		String cloums = "select   ";
		String formTable = " from pc_rpd_srgl_v v where 1=1  and cjsjS between TO_DATE('"+stime+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+etime+"','YYYY-MM-DD HH24:MI:SS') ";
		String totalNum = "select count(*) ";
		if(!oil.equals("")&&oil!=null&&!oil.equals("undefined")&!oil.equals("全部")){
			formTable=formTable+" and OILDRILLING_STATION_NAME='"+oil+"'";
		}
		if(!areablock.equals("")&&areablock!=null&&!areablock.equals("undefined")){
			formTable=formTable+" and areablock='"+areablock+"'";
		}
		if(!station.equals("")&&station!=null&&!station.equals("undefined")){
			formTable=formTable+" and GRZH='"+station+"'";
		}
		if(!ghname.equals("")&&station!=null&&!ghname.equals("undefined")){
			formTable=formTable+" and gh_id='"+ghname+"'";
		}
		if(!boilersName.equals("")&&boilersName!=null&&!boilersName.equals("undefined")){
			formTable=formTable+" and BOILER_NAME='"+boilersName+"'";
		}
		
		String[] cloumnsName = {"BOILER_NAME","CJSJS","GLBH","GRZH","OILDRILLING_STATION_NAME","BLOCKSTATION_NAME","YXZ","AREABLOCK","BOILER_COMMONDID","BOILERID","HL","SL","LJSL_4","RHYYL",
				"LTYL","NQYL","YZWD","GBWD","YQWD","RFWD","KRQTBJ","BJKYL","BCKYL","BCKWD","DLDJY","DLDJW"
				,"DLDCY","DLDCW","FSDJY","FSDJW","ZQWD","ZQYL","TRQFQ","TRQFH","JLYL","TRQLL","LLRQL","LJSL_5","BJKWD","LHQND","PZGYL","DAILY_CUMULATIVE_GAS","DAILY_CUMULATIVE_WATER"};
		String kk="BOILER_NAME";
		for(int m=1;m<cloumnsName.length;m++){
//			if("CJSJS".equals(cloumnsName[m])){
//				kk=kk+","+"to_char(CJSJS,'YYYY-MM-DD hh24:mi:ss') as CJSJS";
//			}else{ 
				kk=kk+","+cloumnsName[m];
//			} 
		}
		if ("export".equals(totalNumFlag)) {
			String[] cloumnsName2 =  {"BOILER_NAME","CJSJS","GLBH","GRZH","OILDRILLING_STATION_NAME","BLOCKSTATION_NAME","YXZ","AREABLOCK","HL","SL","LJSL_4","RHYYL",
					"LTYL","NQYL","YZWD","GBWD","YQWD","RFWD","KRQTBJ","BJKYL","BCKYL","BCKWD","DLDJY","DLDJW"
					,"DLDCY","DLDCW","FSDJY","FSDJW","ZQWD","ZQYL","TRQFQ","TRQFH","JLYL","TRQLL","LLRQL","LJSL_5","BJKWD","LHQND","PZGYL","DAILY_CUMULATIVE_GAS","DAILY_CUMULATIVE_WATER"};
			kk = "BOILER_NAME";
			for(int m=1;m<cloumnsName2.length;m++){
//				if("CJSJS".equals(cloumnsName2[m])){
//					kk=kk+","+"to_char(CJSJS,'YYYY-MM-DD hh24:mi:ss') CJSJS";
//				}else{
					kk=kk+","+cloumnsName2[m];
//				}
			}
		}
		String thinOilWellRD = cloums + kk+formTable;
		//获取总条数
		int total = 0;
		if ("1".equals(totalNum)) {
			total = 1;
		} else {
			totalNum += formTable;
			total = srglRDDao.getCounts(totalNum);
		}
		if ("totalNum".equals(totalNumFlag)) {
			map.put("totalNum", total + "");
			return map;
		}
		//排序
		if(!"".equals(sort) && !"CJSJS".equals(sort)){
			for (String cloumn : cloumnsName) {
				if(cloumn.equals(sort)){
					thinOilWellRD +=" order by " + cloumn + " " + order;
					break;
				}
				
			}
		}
		else {
			thinOilWellRD +=" order by v.CJSJS ";
		}
		//计算分页
		PageControl control = new PageControl();
		//当前页
		List<Object[]> lo = new ArrayList<Object[]>();
		control.setInt_num(rowsPerpage);
		if ("export".equals(totalNumFlag)) {
			lo = srglRDDao.searchData(thinOilWellRD);
		}
		else {
			control.init(pageNo, total);
			//开始条数
			int start = control.getStart()-1;
			lo = srglRDDao.searchRBData(thinOilWellRD,start,rowsPerpage,cloumnsName);
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
	
	@Override
	public boolean saveOrUpdate(PcRpdBoilerHighDryT prbhd) throws Exception {
		return srglRDDao.addOrModifyBoilerOrgInfo(prbhd);
	}
	public boolean removeGGDRDBoiler(String rpdBoilerHighDryId) throws Exception{
		PcRpdBoilerHighDryT prbhd = (PcRpdBoilerHighDryT) srglRDDao.queryGGDRDBoiler(rpdBoilerHighDryId);
		boolean deleteFlg = srglRDDao.removeGGDRDBoiler(prbhd);
		return deleteFlg;
	}
	public PcRpdBoilerHighDryT searchGGDRDBoiler(String rpdBoilerHighDryId) throws Exception {
		PcRpdBoilerHighDryT prbhd = (PcRpdBoilerHighDryT) srglRDDao.queryGGDRDBoiler(rpdBoilerHighDryId);
		return prbhd;
	}
	public PcCdBoilerT searchBoiler(String boilerid) throws Exception {
		List<Object[]> boilerList = srglRDDao.queryInfo("select b.boilerid,b.boiler_name from pc_cd_boiler_t b where b.boiler_name='"+boilerid+"'");
		boilerid = boilerList.get(0)[0].toString();
		PcCdBoilerT boiler = (PcCdBoilerT) srglRDDao.searchBoiler(boilerid);
		return boiler;
	}



	@Override
	public HashMap<String, Object> searchData1(String oilationname,
			String areablock, String blockstationname, String ghname,
			String boilersName, String acceptunit, String stime, String etime,
			int pageNo, String sort, String order, int rowsPerpage,
			String pageCode, String totalNumFlag) {
		JSONArray jsonArr = null;
		JSONObject tjo = null;
		HashMap<String,Object> map = new HashMap<String, Object>();
		//etime = DateBean.getTimeAfterDAYTime(etime);
		String cloums = "select   ";
		String formTable = "";
		String totalNum = "select count(*) ";
		formTable = " from PC_RPD_BOILER_HIGH_HOURS_V  where 1=1  and REPORT_DATE between TO_DATE('"+stime+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+etime+"','YYYY-MM-DD HH24:MI:SS') ";
		
			if(!oilationname.equals("")&&oilationname!=null&&!oilationname.equals("undefined")){
				formTable=formTable+" and oilationname='"+oilationname+"'";
			}
			if(!"".equals(areablock)){
				formTable=formTable+" and BLOCK_NAME = '"+areablock+"'";
			}
			
			if(!blockstationname.equals("")&&blockstationname!=null&&!blockstationname.equals("undefined")){
				formTable=formTable+" and BLOCKSTATION_NAME='"+blockstationname+"'";
			}

			if(!boilersName.equals("")&&boilersName!=null&&!boilersName.equals("undefined")){
				formTable=formTable+" and BOILER_NAME='"+boilersName+"'";
			}
			if(!acceptunit.equals("")&&acceptunit!=null&&!acceptunit.equals("undefined")&!acceptunit.equals("全部")){
				formTable=formTable+" and OILDRILLING_STATION_NAME='"+acceptunit+"'";
			}

			String[] cloumnsNames ={"BLOCK_NAME","BOILER_NAME","REPORT_DATE","RUN_STATUS","GAS_INTO_PRESS",
					"GAS1_PRESS","FIRE_MEASURE","GAS_FLOW","PUMPIN_PRESS","PUMPOUT_TEMP",
					"PUMPFC_FREQUENCY","WATERSUPPLY_FLOW","STEAMIN_TEMP","STEAMOUT_TEMP",
					"STEAM_DRYNESS","PUMPOUT_PRESS","CSIN_PRESS","CSIN_TEMP","CSOUT_PRESS",
					"CSOUT_TEMP","CONVECTIONR_PRESS_REDUCTION","RSIN_PRESS","RSIN_TEMP","RS_PRESS",
					"RSOUT_TEMP","RS_PRESS_REDUCTION","RS_TEMP","REHEAT_PRESS_ENTRANCE",
					"REHEAT_TEMP_ENTRANCE","REHEAT_TEMP_EXPORT","RERHEAT_PRESS_REDUCTION","GAS2_PRESS","GAS3_PRESS",
					"LUBE_TEMP","FAN_AIR_INTAKE_TEMP","BURNER_TEMP","HEARTH_PRESS","EJECTSMOKE_TEMP","SYSTEM_VOLTAGE","PUMP_MOTOR_CURRENT",
					"PUMP_MOTOR_TEMP","FAN_MOTOR_CURRENT","FUEL_GAS_DENSITY","H2S_DENSITY","SEWAGE_BUFFER_TANK",
					"STEAM_WORK_GROUP","OILDRILLING_STATION_NAME","STEAM_INJE_UNIT"
					}; 
		
	String kk="BLOCK_NAME";
	for(int m=1;m<cloumnsNames.length;m++){
		if("REPORT_DATE".equals(cloumnsNames[m])){ 
			kk=kk+","+"to_char(REPORT_DATE,'YYYY-MM-DD hh24:mi') as REPORT_DATE"; 
		}else if("CHECK_DATE".equals(cloumnsNames[m])){
			kk=kk+","+"to_char(CHECK_DATE,'YYYY-MM-DD hh24:mi') as CHECK_DATE"; 
		}else if("RLAST_ODATE".equals(cloumnsNames[m])){
			kk=kk+","+"to_char(RLAST_ODATE,'YYYY-MM-DD hh24:mi') as RLAST_ODATE"; 
		}else{
			kk=kk+","+cloumnsNames[m];
		}
	}
		
	String thinOilWellRD = cloums + kk+formTable;
	if ("export".equals(totalNumFlag)) {
		//thinOilWellRD = cloums + " BLOCK_NAME,BOILER_NAME,to_char(REPORT_DATE,'YYYY-MM-DD hh24:mi:ss') as REPORT_DATE,RUNTIME,GAS_INTO_PRESS,GAS1_PRESS,FIRE_MEASURE,GAS_FLOW,PUMPIN_PRESS,PUMPOUT_TEMP,PUMPFC_FREQUENCY,WATERSUPPLY_FLOW,STEAMIN_TEMP,STEAMOUT_TEMP,STEAM_DRYNESS,PUMPOUT_PRESS,CSIN_PRESS,CSIN_TEMP,CSOUT_PRESS,CSOUT_TEMP,CONVECTIONR_PRESS_REDUCTION,RSIN_PRESS,RSIN_TEMP,RS_PRESS,RSOUT_TEMP,RS_TEMP,RS_PRESS_REDUCTION,REHEAT_PRESS_ENTRANCE,REHEAT_TEMP_ENTRANCE,REHEAT_TEMP_EXPORT,RERHEAT_PRESS_REDUCTION,GAS2_PRESS,GAS3_PRESS,LUBE_TEMP,FAN_AIR_INTAKE_TEMP,BURNER_TEMP,HEARTH_PRESS,EJECTSMOKE_TEMP,SYSTEM_VOLTAGE,PUMP_MOTOR_CURRENT,PUMP_MOTOR_TEMP,FAN_MOTOR_CURRENT,FUEL_GAS_DENSITY,H2S_DENSITY,SEWAGE_BUFFER_TANK,DAILY_CUMULATIVE_GAS,DAILY_CUMULATIVE_STEAM,STEAM_WORK_GROUP,OILDRILLING_STATION_NAME,STEAM_INJE_UNIT,REMARK " +formTable;
		thinOilWellRD = cloums + " BLOCK_NAME,BOILER_NAME,to_char(REPORT_DATE,'YYYY-MM-DD hh24:mi:ss') as REPORT_DATE,RUN_STATUS,GAS_INTO_PRESS,"+
		"GAS1_PRESS,FIRE_MEASURE,GAS_FLOW,PUMPIN_PRESS,PUMPOUT_TEMP,"+
		"PUMPFC_FREQUENCY,WATERSUPPLY_FLOW,STEAMIN_TEMP,STEAMOUT_TEMP,"+
		"STEAM_DRYNESS,PUMPOUT_PRESS,CSIN_PRESS,CSIN_TEMP,CSOUT_PRESS,"+
		"CSOUT_TEMP,CONVECTIONR_PRESS_REDUCTION,RSIN_PRESS,RSIN_TEMP,RS_PRESS,"+
		"RSOUT_TEMP,RS_PRESS_REDUCTION,RS_TEMP,REHEAT_PRESS_ENTRANCE,"+
		"REHEAT_TEMP_ENTRANCE,REHEAT_TEMP_EXPORT,RERHEAT_PRESS_REDUCTION,GAS2_PRESS,GAS3_PRESS,"+
		"LUBE_TEMP,FAN_AIR_INTAKE_TEMP,BURNER_TEMP,HEARTH_PRESS,EJECTSMOKE_TEMP,SYSTEM_VOLTAGE,PUMP_MOTOR_CURRENT,"+
		"PUMP_MOTOR_TEMP,FAN_MOTOR_CURRENT,FUEL_GAS_DENSITY,H2S_DENSITY,SEWAGE_BUFFER_TANK,"+
		"STEAM_WORK_GROUP,OILDRILLING_STATION_NAME,STEAM_INJE_UNIT " +formTable;
	}
		
	//获取总条数
	int total = 0;
	if ("1".equals(totalNum)) {
		total = 1;
	}else if ("rb".equals(pageCode)){ 
		totalNum += formTable;
		total = srglRDDao.getCounts(totalNum);
	}else {
		totalNum += formTable;
		total = srglRDDao.getCounts(totalNum);
	}
	if ("totalNum".equals(totalNumFlag)) {
		map.put("totalNum", total + "");
		return map;
	}
	//排序
	if(!"".equals(sort) && !"report_Date".equals(sort)){
		for (String cloumn : cloumnsNames) {
			if(cloumn.equals(sort)){
				thinOilWellRD +=" order by " + cloumn + " " + order;
				break;
			}
			
		}
	}
	else {
		thinOilWellRD +=" order by report_date,BOILER_NAME ";
	}
	//计算分页
	PageControl control = new PageControl();
	//当前页
	List<Object[]> lo = new ArrayList<Object[]>();
	control.setInt_num(rowsPerpage);
	if ("export".equals(totalNumFlag)) {
		lo = srglRDDao.searchData1(thinOilWellRD);
	}
	else {
		control.init(pageNo, total);
		//开始条数
		int start = control.getStart()-1;
			lo = srglRDDao.searchData1(thinOilWellRD,cloumnsNames,start,rowsPerpage);
		if (lo != null && 0 < lo.size()) {
			// 生成树节点json文档
			jsonArr = new JSONArray();
			for (Object[] o : lo) {
				tjo = new JSONObject();
				
				for (int i = 0; i < o.length; i++) {
					if (o[i] == null||o[i].equals("null")) {
						o[i] = "";
					}else{
						tjo.put(cloumnsNames[i], o[i].toString());
					}
				}
				jsonArr.add(tjo);
			}
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
	public JSONObject searchGgdglResults(String areablock,
			String blockstationname, String boilersName, String oilationname,
			String startDate, String endDate) throws Exception{
		
		JSONObject tjo = new JSONObject();
		List<Long> timeList = new ArrayList<Long>();
		List<BigDecimal> tempList = new ArrayList<BigDecimal>();
		List<BigDecimal> pressList = new ArrayList<BigDecimal>();

		String hql = "select   ";
		if("".equals(startDate)){
			startDate = startDate + " 00:00:00";
		}else{
			startDate = startDate + ":00";
		}
		if("".equals(endDate)){
			endDate += " 23:59:59";
		}else{
			endDate = endDate + ":00"; 
		}
		String formTable = " from  PC_RD_BOILER_HIGH_DRY_V  where 1=1  and ACQUISITION_TIME between TO_DATE('"+startDate+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+endDate+"','YYYY-MM-DD HH24:MI:SS') ";
		

		if(!areablock.equals("")&&areablock!=null&&!areablock.equals("undefined")){
			formTable=formTable+" and BLOCK_NAME='"+areablock+"'";
		}
		if(!blockstationname.equals("")&&blockstationname!=null&&!blockstationname.equals("undefined")){
			formTable=formTable+" and BLOCKSTATION_NAME='"+blockstationname+"'";
		}
		if(!boilersName.equals("")&&boilersName!=null&&!boilersName.equals("undefined")){
			formTable=formTable+" and boiler_name='"+boilersName+"'";
		}
		if(!oilationname.equals("")&&oilationname!=null&&!oilationname.equals("undefined")){
			formTable=formTable+" and oildrilling_station_name='"+oilationname+"'";
		}

		String[] cloumnsName = {"ACQUISITION_TIME","STEAMIN_TEMP","STEAMOUT_TEMP"};
		hql += "to_char(ACQUISITION_TIME,'YYYY-MM-DD hh24:mi:ss') as CJSJ";
		for (int m = 1; m < cloumnsName.length; m++) {
			hql = hql + ", " + cloumnsName[m];
		}

		hql += formTable + " order by ACQUISITION_TIME ";
		
		List<Object[]> lo = srglRDDao.queryInfo(hql);
		
		if (lo != null && 0 < lo.size()) {
			for (int i = 0; i < lo.size(); i++) {
				Object[] o = lo.get(i);				
				String times = o[0].toString();				
				SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date;
				long time = 0;
					try {
						date = format.parse(times);
						time =  date.getTime();						
					} catch (ParseException e) {
						e.printStackTrace();
					}		
				timeList.add(time);
				pressList.add((BigDecimal) o[1]);
				tempList.add((BigDecimal) o[2]);

			}
		}

		tjo.put("time", timeList);
		tjo.put("temp", tempList);
		tjo.put("press", pressList);

		return tjo;
	}



	@Override
	public JSONObject searchGgdglResults1(String areablock,
			String blockstationname, String boilersName, String oilationname,
			String startDate, String endDate) throws Exception {
		JSONObject tjo = new JSONObject();
		List<Long> timeList = new ArrayList<Long>();
		List<BigDecimal> tempList = new ArrayList<BigDecimal>();
		List<BigDecimal> pressList = new ArrayList<BigDecimal>();

		String hql = "select   ";
		if("".equals(startDate)){
			startDate = startDate + " 00:00:00";
		}else{
			startDate = startDate + ":00";
		}
		if("".equals(endDate)){
			endDate += " 23:59:59";
		}else{
			endDate = endDate + ":00"; 
		}
		String formTable = " from  PC_RPD_BOILER_HIGH_DRY_V  where 1=1  and report_date between TO_DATE('"+startDate+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+endDate+"','YYYY-MM-DD HH24:MI:SS') ";
		

		if(!areablock.equals("")&&areablock!=null&&!areablock.equals("undefined")){
			formTable=formTable+" and BLOCK_NAME='"+areablock+"'";
		}
		if(!blockstationname.equals("")&&blockstationname!=null&&!blockstationname.equals("undefined")){
			formTable=formTable+" and BLOCKSTATION_NAME='"+blockstationname+"'";
		}
		if(!boilersName.equals("")&&boilersName!=null&&!boilersName.equals("undefined")){
			formTable=formTable+" and boiler_name='"+boilersName+"'";
		}
		if(!oilationname.equals("")&&oilationname!=null&&!oilationname.equals("undefined")){
			formTable=formTable+" and oildrilling_station_name='"+oilationname+"'";
		}

		String[] cloumnsName = {"report_date","STEAMIN_TEMP","STEAMOUT_TEMP"};
		hql += "to_char(report_date,'YYYY-MM-DD hh24:mi:ss') as CJSJ";
		for (int m = 1; m < cloumnsName.length; m++) {
			hql = hql + ", " + cloumnsName[m];
		}

		hql += formTable + " order by report_date ";
		
		List<Object[]> lo = srglRDDao.queryInfo(hql);
		
		if (lo != null && 0 < lo.size()) {
			for (int i = 0; i < lo.size(); i++) {
				Object[] o = lo.get(i);				
				String times = o[0].toString();				
				SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date;
				long time = 0;
					try {
						date = format.parse(times);
						time =  date.getTime();						
					} catch (ParseException e) {
						e.printStackTrace();
					}		
				timeList.add(time);
				pressList.add((BigDecimal) o[1]);
				tempList.add((BigDecimal) o[2]);

			}
		}

		tjo.put("time", timeList);
		tjo.put("temp", tempList);
		tjo.put("press", pressList);

		return tjo;
	}
}
