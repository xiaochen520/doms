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
import com.echo.dto.PcRpdBoilerCommonT;
import com.echo.service.SRGLRDService;
import com.echo.util.CommonsUtil;
import com.echo.util.DateBean;

public class SRGLRDServiceImpl implements SRGLRDService{
	private SRGLRDDao srglRDDao;
	
	

	public void setSrglRDDao(SRGLRDDao srglRDDao) {
		this.srglRDDao = srglRDDao;
	}



	public HashMap<String,Object> searchData(String qk,String zh,String jh,String gh,String name,String stime,String etime,String oilNmame,int pageNo,String sort,String order,int rowsPerpage,String totalNumFlag) {
		JSONArray jsonArr = null;
		JSONObject tjo = null;
		HashMap<String,Object> map = new HashMap<String, Object>();
		//etime = DateBean.getTimeAfterDAYTime(etime);
		String cloums = "select   ";
		String formTable = " from  pc_rd_boiler_common_v  where 1=1  and acquisition_time between TO_DATE('"+stime+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+etime+"','YYYY-MM-DD HH24:MI:SS') ";
		String totalNum = "select count(*) ";
		if(!qk.equals("")&&qk!=null&&!qk.equals("undefined")){
			formTable=formTable+" and OILDRILLING_STATION_NAME='"+qk+"'";
		}
		if(!zh.equals("")&&zh!=null&&!zh.equals("undefined")){
			formTable=formTable+" and BLOCK_NAME='"+zh+"'";
		}
		if(!jh.equals("")&&jh!=null&&!jh.equals("undefined")){
			formTable=formTable+" and BLOCKSTATION_NAME='"+jh+"'";
		}
		if(!gh.equals("")&&jh!=null&&!gh.equals("undefined")){
			formTable=formTable+" and STEAM_WORK_GROUP='"+gh+"'";
		}
		if(!name.equals("")&&name!=null&&!name.equals("undefined")){
			formTable=formTable+" and BOILER_NAME='"+name+"'";
		}
		if(!oilNmame.equals("")&&oilNmame!=null&&!oilNmame.equals("undefined")){
			formTable=formTable+" and oilName='"+oilNmame+"'";
		}
		String[] cloumnsName = {"BLOCK_NAME","BLOCKSTATION_NAME","BOILER_NAME","ACQUISITION_TIME","RUN_STATUS","GAS_INTO_PRESS","GAS1_PRESS","FIRE_MEASURE","GAS_FLOW",
				"PUMPIN_PRESS","PUMPOUT_TEMP","PUMPFC_FREQUENCY","WATERSUPPLY_FLOW","STEAMIN_TEMP","STEAMOUT_TEMP","STEAM_DRYNESS",
				"PUMPOUT_PRESS","CSIN_PRESS","CSIN_TEMP","CSOUT_PRESS","CSOUT_TEMP","CONVECTIONR_PRESS_REDUCTION","RSIN_PRESS","RSIN_TEMP",
				"RS_PRESS_REDUCTION","RS_TEMP","GAS2_PRESS","GAS3_PRESS","LUBE_PRESS","LUBE_TEMP","FAN_AIR_INTAKE_TEMP","BURNER_TEMP","HEARTH_PRESS","EJECTSMOKE_TEMP",
				"SYSTEM_VOLTAGE","PUMP_MOTOR_CURRENT","PUMP_MOTOR_TEMP","FAN_MOTOR_CURRENT","FUEL_GAS_DENSITY","H2S_DENSITY","SEWAGE_BUFFER_TANK","STEAM_WORK_GROUP","OILDRILLING_STATION_NAME","STEAM_INJE_UNIT"};
		String kk="BLOCK_NAME";
		for(int m=1;m<cloumnsName.length;m++){
			if("ACQUISITION_TIME".equals(cloumnsName[m])){ 
				//kk=kk+","+"to_char(ACQUISITION_TIME,'YYYY-MM-DD hh24:mi:ss') as CJSJS"; 
				 kk += ",to_char(ACQUISITION_TIME,'YYYY-MM-DD hh24:mi:ss') as CJSJS";
			}else{ 
				kk=kk+","+cloumnsName[m];
				
			} 
		}
		String[] cloumnsName2 = {"BLOCK_NAME","BOILER_NAME","ACQUISITION_TIME","RUN_STATUS","GAS_INTO_PRESS","GAS1_PRESS","FIRE_MEASURE","GAS_FLOW",
				"PUMPIN_PRESS","PUMPOUT_TEMP","PUMPFC_FREQUENCY","WATERSUPPLY_FLOW","STEAMIN_TEMP","STEAMOUT_TEMP","STEAM_DRYNESS",
				"PUMPOUT_PRESS","CSIN_PRESS","CSIN_TEMP","CSOUT_PRESS","CSOUT_TEMP","CONVECTIONR_PRESS_REDUCTION","RSIN_PRESS","RSIN_TEMP",
				"RS_PRESS_REDUCTION","RS_TEMP","GAS2_PRESS","GAS3_PRESS","LUBE_PRESS","LUBE_TEMP","FAN_AIR_INTAKE_TEMP","BURNER_TEMP","HEARTH_PRESS","EJECTSMOKE_TEMP",
				"SYSTEM_VOLTAGE","PUMP_MOTOR_CURRENT","PUMP_MOTOR_TEMP","FAN_MOTOR_CURRENT","FUEL_GAS_DENSITY","H2S_DENSITY","SEWAGE_BUFFER_TANK","STEAM_WORK_GROUP","OILDRILLING_STATION_NAME","STEAM_INJE_UNIT"};
		
		if ("export".equals(totalNumFlag)) {
					
					kk = "BLOCK_NAME";
					for(int m=1;m<cloumnsName2.length;m++){
						if("ACQUISITION_TIME".equals(cloumnsName2[m])){
							kk=kk+","+"to_char(ACQUISITION_TIME,'YYYY-MM-DD hh24:mi:ss') as CJSJS";
						}
						
						else {
							kk=kk+","+cloumnsName2[m];
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
			total = srglRDDao.getCounts(totalNum);
		}
		if ("totalNum".equals(totalNumFlag)) {
			map.put("totalNum", total + "");
			return map;
		}
		//排序
		if ("export".equals(totalNumFlag)) {
			thinOilWellRD +=" order by BOILER_NAME ";
		} else 
		if(!"".equals(sort) && !"CJSJS".equals(sort)){
			for (String cloumn : cloumnsName) {
				if(cloumn.equals(sort)){
					thinOilWellRD +=" order by " + cloumn + " " + order;
					break;
				}
				
			}
		}
		else {
			thinOilWellRD +=" order by CJSJS,BOILER_NAME ";
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
			lo = srglRDDao.searchData(thinOilWellRD,start,rowsPerpage);
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
	public HashMap<String,Object> searchRPData(String oilname,String areablock,String stationNumber,String group,String boilersName,String stime,String etime,String oilNmame,String pageCode,int pageNo,String sort,String order,int rowsPerpage,String totalNumFlag) {
		JSONArray jsonArr = null;
		JSONObject tjo = null;
		HashMap<String,Object> map = new HashMap<String, Object>();
		etime = DateBean.getTimeAfterDAYTime(etime);
		String cloums = "select   ";
		String formTable = " from pc_rpd_boiler_common_v  where 1=1  and REPORT_DATE between TO_DATE('"+stime+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+etime+"','YYYY-MM-DD HH24:MI:SS') ";
		String totalNum = "select count(*) ";
		
		if(!oilname.equals("")&&oilname!=null&&!oilname.equals("undefined")){
			formTable=formTable+" and OILDRILLING_STATION_NAME='"+oilname+"'";
		}
		if(!areablock.equals("")&&areablock!=null&&!areablock.equals("undefined")){
			formTable=formTable+" and BLOCK_NAME='"+areablock+"'";
		}
		if(!stationNumber.equals("")&&stationNumber!=null&&!stationNumber.equals("undefined")){
			formTable=formTable+" and BLOCKSTATION_NAME='"+stationNumber+"'";
		}
		if(!group.equals("")&&group!=null&&!group.equals("undefined")){
			formTable=formTable+" and STEAM_WORK_GROUP='"+group+"'";
		}
		if(!boilersName.equals("")&&boilersName!=null&&!boilersName.equals("undefined")){
			formTable=formTable+" and BOILER_NAME='"+boilersName+"'";
		}
		if(!oilNmame.equals("")&&oilNmame!=null&&!oilNmame.equals("undefined")){
			formTable=formTable+" and oilName='"+oilNmame+"'";
		}
		
		String[] cloumnsName = {"BLOCK_NAME","BOILER_NAME","REPORT_DATE","RUNTIME","GAS_INTO_PRESS","GAS1_PRESS","FIRE_MEASURE","GAS_FLOW",
				"PUMPIN_PRESS","PUMPOUT_TEMP","PUMPFC_FREQUENCY","WATERSUPPLY_FLOW","STEAMIN_TEMP","STEAMOUT_TEMP","STEAM_DRYNESS",
				"PUMPOUT_PRESS","CSIN_PRESS","CSIN_TEMP","CSOUT_PRESS","CSOUT_TEMP","CONVECTIONR_PRESS_REDUCTION","RSIN_PRESS","RSIN_TEMP",
				"RS_PRESS_REDUCTION","RS_TEMP","GAS2_PRESS","GAS3_PRESS","LUBE_PRESS","LUBE_TEMP","FAN_AIR_INTAKE_TEMP","BURNER_TEMP","HEARTH_PRESS","EJECTSMOKE_TEMP",
				"SYSTEM_VOLTAGE","PUMP_MOTOR_CURRENT","PUMP_MOTOR_TEMP","FAN_MOTOR_CURRENT",
				"DAILY_CUMULATIVE_GAS","DAILY_CUMULATIVE_STEAM","FUEL_GAS_DENSITY","H2S_DENSITY","SEWAGE_BUFFER_TANK","STEAM_WORK_GROUP","OILDRILLING_STATION_NAME","STEAM_INJE_UNIT","RPD_BOILER_COMMON_ID","BOILERID",
				"REMARK","CHECK_OPER","CHECK_DATE","RLAST_OPER","RLAST_ODATE"};
		
		String[] cloumnsName2 = {"BLOCK_NAME","BOILER_NAME","REPORT_DATE","RUNTIME","GAS_INTO_PRESS","GAS1_PRESS","FIRE_MEASURE","GAS_FLOW",
				"PUMPIN_PRESS","PUMPOUT_TEMP","PUMPFC_FREQUENCY","WATERSUPPLY_FLOW","STEAMIN_TEMP","STEAMOUT_TEMP","STEAM_DRYNESS",
				"PUMPOUT_PRESS","CSIN_PRESS","CSIN_TEMP","CSOUT_PRESS","CSOUT_TEMP","CONVECTIONR_PRESS_REDUCTION","RSIN_PRESS","RSIN_TEMP",
				"RS_PRESS_REDUCTION","RS_TEMP","GAS2_PRESS","GAS3_PRESS","LUBE_PRESS","LUBE_TEMP","FAN_AIR_INTAKE_TEMP","BURNER_TEMP","HEARTH_PRESS","EJECTSMOKE_TEMP",
				"SYSTEM_VOLTAGE","PUMP_MOTOR_CURRENT","PUMP_MOTOR_TEMP","FAN_MOTOR_CURRENT",
				"FUEL_GAS_DENSITY","H2S_DENSITY","SEWAGE_BUFFER_TANK","DAILY_CUMULATIVE_GAS","DAILY_CUMULATIVE_STEAM","STEAM_WORK_GROUP","OILDRILLING_STATION_NAME","STEAM_INJE_UNIT","REMARK"};
	
		
		String kk="BLOCK_NAME";
		for(int m=1;m<cloumnsName.length;m++){
			if("REPORT_DATE".equals(cloumnsName[m])){ 
				//kk=kk+","+"to_char(ACQUISITION_TIME,'YYYY-MM-DD hh24:mi:ss') as CJSJS"; 
				  kk += ",to_char(REPORT_DATE,'YYYY-MM-DD') as CJSJS";
			}else if("CHECK_DATE".equals(cloumnsName[m])){
				kk += ",to_char(CHECK_DATE,'YYYY-MM-DD HH24:MI') as CHECK_DATE";
			}else if("RLAST_ODATE".equals(cloumnsName[m])){
				kk += ",to_char(RLAST_ODATE,'YYYY-MM-DD HH24:MI') as RLAST_ODATE";
			}else{ 
				kk=kk+","+cloumnsName[m];
				
			} 
		}
//		for(int m=1;m<cloumnsName.length;m++){
//			
//			if("日报".equals(pageCode) && "REPORT_DATE".equals(cloumnsName[m])){
////				if ("日报".equals(pageCode)) {
////					continue;
////				}
//				kk=kk+",to_char(REPORT_DATE,'YYYY-MM-DD') as CJSJS"; 
//			}
//			
//			if ("日报".equals(pageCode) && "CHECK_DATE".equals(cloumnsName[m])) {
//				kk=kk+",CHECK_DATE,to_char(CHECK_DATE,'YYYY-MM-DD HH24:MI') as CHECK_DATE";
//			}
//			if ("日报".equals(pageCode) && "RLAST_ODATE".equals(cloumnsName[m])) {
//				kk=kk+",RLAST_ODATE,to_char(RLAST_ODATE,'YYYY-MM-DD HH24:MI') as RLAST_ODATE";
//			}
//			kk=kk+","+cloumnsName[m];
//		}
		if ("export".equals(totalNumFlag)) {
			
			kk = "BLOCK_NAME";
			for(int m=1;m<cloumnsName2.length;m++){
				if("REPORT_DATE".equals(cloumnsName2[m])){
					kk=kk+","+"to_char(REPORT_DATE,'YYYY-MM-DD') as CJSJS";
				}
				
				else {
					kk=kk+","+cloumnsName2[m];
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
			thinOilWellRD +=" order by CJSJS ,BOILER_NAME";
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
					}else if( i < 4){
					
						tjo.put(cloumnsName[i], o[i].toString());
					}else  if (i>4 && i< 42){
						tjo.put(cloumnsName[i], CommonsUtil.getNotTwoData(o[i].toString()));
					}else{
						tjo.put(cloumnsName[i], o[i].toString());
					}
					
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
	public List<Object[]> searchboilerRPDview(String rbid,String[] cloumnsName) throws Exception {
		String sql = "select ";
		for(int i = 0; i<cloumnsName.length;i++){
			if("REPORT_DATE".equals(cloumnsName[i])){
				sql += "to_char(REPORT_DATE,'YYYY-MM-DD') as CJSJS ,";
			}else{
				sql += " s."+cloumnsName[i]+",";
			}
			
		}
		sql = sql.substring(0, sql.length()-1);
		 sql += " from pc_rpd_boiler_common_t s where 1=1 and  s.rpd_boiler_common_id = '"+rbid+"'";
		List<Object[]> boilerrpd = srglRDDao.searchData(sql);
		
		return boilerrpd;
	}
	public List<Object[]> searchboilerLine(String glid, String prvarid,
			String startDate, String endDate) throws Exception {

		String formTable = "select to_char(REPORT_DATE,'YYYY-MM-DD hh24:mi:ss') as CJSJS,"+prvarid;
		formTable += "  from pc_rpd_boiler_common_t  where 1=1 and BOILERID='"+glid+"'";
		
		Date today = new Date();
		String nowdate = DateBean.getTimeStr(today);
		
		if("".equals(startDate)){
			startDate = nowdate.substring(0, 10) + " 00:00:00";
		}else{
			if(startDate.length() == 10){
				startDate = startDate + " 00:00:00";
			}else{
				startDate = startDate + ":00";
			}
			
		}
		if("".equals(endDate)){
			endDate = nowdate.substring(0, 10) + " 23:59:59";
		}else{
			if(endDate.length() == 10){
				endDate = endDate + " 23:59:59";
			}else{
				endDate = endDate + ":00";
			}
			
		}
		formTable=formTable+" and REPORT_DATE between TO_DATE('"+startDate+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+endDate+"','YYYY-MM-DD HH24:MI:SS')";
		List<Object[]> dataSet = srglRDDao.searchData(formTable);
		return dataSet;
	
	}
	public boolean removeBoilerCommondRP(String arg)throws Exception {
		//String[] sqls = new String[2];
		String sql = "delete from  pc_rpd_boiler_common_t  a  where a.rpd_boiler_common_id='"+arg+"'";
		//sqls[1] = "delete from pc_rpd_boiler_commond_t b where b.boilerid='"+sagddid+"'";
		return srglRDDao.removeBoilerCommondRP(sql);
	}



	public List<Object[]> searchCommobid(String id,String boilerid, String name,String date)throws Exception {
		List<Object[]> pbcList = null;
		String sql ="  select a.report_date,a.boiler_commondid,  a.boilerid , a.boiler_name from pc_rpd_boiler_commond_t a where a.report_date = TO_DATE('"+date+"','YYYY-MM-DD ')   " ;
//		String sql ="select a.report_date,a.boiler_commondid,  a.boilerid , a.boiler_name from pc_rpd_boiler_commond_t"+ 
//					"a where  a.boiler_commondid='"+id+"' "+
//					"and a.boilerid='"+boilerid+"' and a.boiler_name='"+name+"' "+
//					"and  a.report_date = TO_DATE('"+date+"','YYYY-MM-DD ') ";
		if(id != null && !"".equals(id)){
			sql += " and a.boiler_commondid ='"+id+"'";
		}
		if(name !=null && !"".equals(name)){
			sql += " and a.boiler_name ='"+name+"'";
		}
		if(boilerid !=null && !"".equals(boilerid)){
			sql += "  and a.boilerid ='"+boilerid+"' ";
		}
		pbcList = srglRDDao.searchCommobid(sql);
		return  pbcList;
	}
	public boolean addPsgl(PcRpdBoilerCommonT psgl) throws Exception {
		return srglRDDao.save(psgl);
	}
	public boolean updatePsgl(PcRpdBoilerCommonT psgl) throws Exception {
		return srglRDDao.update(psgl);
	}



	public List<Object[]> searchggboilerRPDview(String rbid,
			String[] cloumnsName) throws Exception {

		String sql = "select ";
		for(int i = 0; i<cloumnsName.length;i++){
			if("CJSJS".equals(cloumnsName[i])){
				sql += "to_char(CJSJS,'YYYY-MM-DD') as CJSJS ,";
			}else{
				sql += " s."+cloumnsName[i]+",";
			}
			
		}
		sql = sql.substring(0, sql.length()-1);
		 sql += " from pc_rpd_BOILER_CROSS_v s where 1=1 and  s.boiler_crossddid = '"+rbid+"'";
		List<Object[]> boilerrpd = srglRDDao.searchData(sql);
		
		return boilerrpd;

	}

	public List<PcRpdBoilerCommonT> searchCommobs(String id, String boilerid,
			String name, String date) throws Exception {
		List<PcRpdBoilerCommonT> pbcList = null;
		String sql =" FROM  PcRpdBoilerCommonT a where 1=1 " ;
//		String sql ="select a.report_date,a.boiler_commondid,  a.boilerid , a.boiler_name from pc_rpd_boiler_commond_t"+ 
//					"a where  a.boiler_commondid='"+id+"' "+
//					"and a.boilerid='"+boilerid+"' and a.boiler_name='"+name+"' "+
//					"and  a.report_date = TO_DATE('"+date+"','YYYY-MM-DD ') ";
		if(id != null && !"".equals(id)){
			sql += " and rpdBoilerCommonId ='"+id+"'";
		}
		if(name !=null && !"".equals(name)){
			sql += " and boilerName ='"+name+"'";
		}
		if(boilerid !=null && !"".equals(boilerid)){
			sql += "  and boilerid ='"+boilerid+"' ";
		}
		if(date !=null && !"".equals(date)){
			sql += " and  reportDate = TO_DATE('"+date+"','YYYY-MM-DD ') ";
		}
		pbcList = srglRDDao.searchBoilerCommonds(sql);
		return  pbcList;
	}
	
	



	public List<Object[]> searchGGboilerLine(String glid, String prvarid,
			String startDate, String endDate) throws Exception {


		String formTable = "select to_char(CJSJ,'YYYY-MM-DD hh24:mi:ss') as CJSJS,"+prvarid;
		formTable += "  from pc_rd_boiler_cross_v  where 1=1 and BOILERID='"+glid+"'";
		
		Date today = new Date();
		String nowdate = DateBean.getTimeStr(today);
		
		if("".equals(startDate)){
			startDate = nowdate.substring(0, 10) + " 00:00:00";
		}else{
			if(startDate.length() == 10){
				startDate = startDate + " 00:00:00";
			}else{
				startDate = startDate + ":00";
			}
			
		}
		if("".equals(endDate)){
			endDate = nowdate.substring(0, 10) + " 23:59:59";
		}else{
			if(endDate.length() == 10){
				endDate = endDate + " 23:59:59";
			}else{
				endDate = endDate + ":00";
			}
			
		}
		formTable=formTable+" and CJSJ between TO_DATE('"+startDate+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+endDate+"','YYYY-MM-DD HH24:MI:SS')";
		List<Object[]> dataSet = srglRDDao.searchData(formTable);
		return dataSet;
	
	
	}



	//查询采油站
	public JSONArray queryOilding(String oilid) throws Exception {
		JSONArray  jsonArr=null;
		JSONObject jsobj = null;
		String sql ="select a.org_id,a.structurename from pc_cd_boilersrtree_v a  where a.structuretype='4'  order by nlssort(a.structurename,'NLS_SORT=SCHINESE_STROKE_M')";
		List<Object[]> dataSet = srglRDDao.queryInfo(sql);
		if(dataSet !=null && dataSet.size()>0){
			//生成JSON文档
			jsonArr = new JSONArray();
			for(Object[] entry : dataSet){
				jsobj = new JSONObject();
				jsobj.put("text", entry[1]);
				jsobj.put("id", entry[0]);
				jsonArr.add(jsobj);
			}
			return jsonArr;
		}
		jsobj = new JSONObject();
		jsobj.put("text", "");
		jsobj.put("id", "");
		jsonArr = new JSONArray();
		jsonArr.add(jsobj);
		return jsonArr;
	}
	@Override
	//供热站
	public JSONArray queryStation(String arg) throws Exception {
		JSONArray  jsonArr=null;
		JSONObject jsobj = null;
		String sql ="select  a.org_id ,a.structurename  from pc_cd_boilersrtree_v a  where a.structuretype='7'  order by nlssort(a.structurename,'NLS_SORT=SCHINESE_STROKE_M')";
		List<Object[]> dataSet = srglRDDao.queryInfo(sql);
		if(dataSet !=null && dataSet.size()>0){
			//生成JSON文档
			jsonArr = new JSONArray();
			for(Object[] entry : dataSet){
				jsobj = new JSONObject();
				jsobj.put("text", entry[1]);
				jsobj.put("id", entry[0]);
				jsonArr.add(jsobj);
			}
			return jsonArr;
		}
		jsobj = new JSONObject();
		jsobj.put("text", "");
		jsobj.put("id", "");
		jsonArr = new JSONArray();
		jsonArr.add(jsobj);
		return jsonArr;
	}
	


	@Override
	//锅炉
	public JSONArray queryBoiler(String arg) throws Exception {
		JSONArray  jsonArr=null;
		JSONObject jsobj = null;
		String sql ="select a.org_id,a.structurename from pc_cd_boilersrtree_v a  where a.structuretype='10'  order by nlssort(a.structurename,'NLS_SORT=SCHINESE_STROKE_M')";
		List<Object[]> dataSet = srglRDDao.queryInfo(sql);
		if(dataSet !=null && dataSet.size()>0){
			//生成JSON文档
			jsonArr = new JSONArray();
			for(Object[] entry : dataSet){
				jsobj = new JSONObject();
				jsobj.put("text", entry[1]);
				jsobj.put("id", entry[0]);
				jsonArr.add(jsobj);
			}
			return jsonArr;
		}
		jsobj = new JSONObject();
		jsobj.put("text", "");
		jsobj.put("id", "");
		jsonArr = new JSONArray();
		jsonArr.add(jsobj);
		return jsonArr;
	}



	@Override
	public JSONArray queryArea(String arg) throws Exception {
		JSONArray  jsonArr=null;
		JSONObject jsobj = null;
		String sql ="select  a.boilerid ,a.block_name  from    pc_rd_boiler_common_t  a     order by nlssort(a.block_name,'NLS_SORT=SCHINESE_STROKE_M')";
		List<Object[]> dataSet = srglRDDao.queryInfo(sql);
		if(dataSet !=null && dataSet.size()>0){
			//生成JSON文档
			jsonArr = new JSONArray();
			for(Object[] entry : dataSet){
				jsobj = new JSONObject();
				jsobj.put("text", entry[1]);
				jsobj.put("id", entry[0]);
				jsonArr.add(jsobj);
			}
			return jsonArr;
		}
		jsobj = new JSONObject();
		jsobj.put("text", "");
		jsobj.put("id", "");
		jsonArr = new JSONArray();
		jsonArr.add(jsobj);
		return jsonArr;
	}



	@Override
	public JSONArray queryStationInfo(String arg) throws Exception {
			JSONArray  jsonArr=null;
			JSONObject jsobj = null;
			String sql ="select  a.org_id,a.structurename  from  pc_cd_boilersrtree_v  a  where a.pid='"+arg+"' and a.structuretype='7'   order by nlssort(a.structurename,'NLS_SORT=SCHINESE_STROKE_M')";
			List<Object[]> dataSet = srglRDDao.queryInfo(sql);
			if(dataSet !=null && dataSet.size()>0){
				//生成JSON文档
				jsonArr = new JSONArray();
				for(Object[] entry : dataSet){
					jsobj = new JSONObject();
					jsobj.put("text", entry[1]);
					jsobj.put("id", entry[0]);
					jsonArr.add(jsobj);
				}
				return jsonArr;
			}
			jsobj = new JSONObject();
			jsobj.put("text", "");
			jsobj.put("id", "");
			jsonArr = new JSONArray();
			jsonArr.add(jsobj);
			return jsonArr;
		}



	@Override
	public JSONArray queryBoilersNameInfo(String arg) throws Exception {

		JSONArray  jsonArr=null;
		JSONObject jsobj = null;
		String sql ="select  a.org_id,a.structurename  from  pc_cd_boilersrtree_v  a  where a.pid='"+arg+"' and a.structuretype='10'   order by nlssort(a.structurename,'NLS_SORT=SCHINESE_STROKE_M')";
		List<Object[]> dataSet = srglRDDao.queryInfo(sql);
		if(dataSet !=null && dataSet.size()>0){
			//生成JSON文档
			jsonArr = new JSONArray();
			for(Object[] entry : dataSet){
				jsobj = new JSONObject();
				jsobj.put("text", entry[1]);
				jsobj.put("id", entry[0]);
				jsonArr.add(jsobj);
			}
			return jsonArr;
		}
		jsobj = new JSONObject();
		jsobj.put("text", "");
		jsobj.put("id", "");
		jsonArr = new JSONArray();
		jsonArr.add(jsobj);
		return jsonArr;
	
	}
	public JSONObject cascadeInit() throws Exception {
		//采油站
		String oilSql ="select a.org_id,a.structurename from pc_cd_boilersrtree_v a  where a.structuretype='4'  order by nlssort(a.structurename,'NLS_SORT=SCHINESE_STROKE_M')";
		//注转站
		String stationSql ="select a.org_id,a.structurename from pc_cd_boilersrtree_v a  where a.structuretype='7'  order by nlssort(a.structurename,'NLS_SORT=SCHINESE_STROKE_M')";
		//所有锅炉
		String boilerSql ="select a.org_id,a.structurename from pc_cd_boilersrtree_v a  where a.structuretype='10'  order by nlssort(a.structurename,'NLS_SORT=SCHINESE_STROKE_M')";
		
		JSONArray oilJsonArr = null;
		JSONArray stationArr = null;
		JSONArray boilerArr = null;
		JSONObject jsobj = null;
		List<Object[]> oilSet = srglRDDao.queryInfo(oilSql);
		List<Object[]> stationSet = srglRDDao.queryInfo(stationSql);
		List<Object[]> boilerSet = srglRDDao.queryInfo(boilerSql);
		
		if(oilSet != null && oilSet.size()>0){
			oilJsonArr = new JSONArray();   
			for(Object[] entry : oilSet){
				jsobj = new JSONObject();
				jsobj.put("text", entry[1]);
				jsobj.put("id",entry[0]);
				oilJsonArr.add(jsobj);
			}
//			jsobj = new JSONObject();
//			jsobj.put("text", "全部");
//			jsobj.put("id", 1);
//			oilJsonArr.add(jsobj);
		}
		
		if(stationSet != null && stationSet.size()>0){
			stationArr = new JSONArray();   
			for(Object[] entry : stationSet){
				jsobj = new JSONObject();
				jsobj.put("text", entry[1]);
				jsobj.put("id",entry[0]);
				stationArr.add(jsobj);
			}
		}
		if(boilerSet != null && boilerSet.size()>0){
			boilerArr = new JSONArray();   
			for(Object[] entry : boilerSet){
				jsobj = new JSONObject();
				jsobj.put("text", entry[1]);
				jsobj.put("id",entry[0]);
				boilerArr.add(jsobj);
			}
		}
		jsobj = new JSONObject();
		jsobj.put("oiltext",oilJsonArr);
		jsobj.put("stationtext",stationArr);
		jsobj.put("boilertext",boilerArr);
		return jsobj;
	}



	@Override
	public String searchBlockName(String boilerName) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select o.structurename from pc_cd_org_t o where o.org_id in( select pid from pc_cd_org_t t join pc_cd_boiler_t g1 on g1.org_id=t.org_id where t.structurename='"+boilerName+"')";
		List<Object[]> lo = new ArrayList<Object[]>();
		lo = srglRDDao.searchData(sql);
		Object blockName = lo.get(0);
		return (String) blockName;
	}


	@Override
	public List<Object[]> searchArea(String bLOCKNAME) throws Exception {
		//String sql ="select  a.boilerid ,a.block_name  from    pc_rd_boiler_common_t  a     order by nlssort(a.block_name,'NLS_SORT=SCHINESE_STROKE_M')";
		List<Object[]> stationlist = srglRDDao.queryInfo("select  a.boilerid ,a.block_name  from   pc_rd_boiler_common_t  a   where a.block_name='"+bLOCKNAME+"'");
		return stationlist;
	}



	@Override
	public String serachBoilerid(String boilerName) throws Exception {
		String sql ="select a.boilerid from pc_cd_boiler_t a where a.boiler_name='"+boilerName+"'";
		List<Object[]> lo = new ArrayList<Object[]>();
		lo = srglRDDao.queryBoilerid(sql);
		Object boilerid = lo.get(0);
		return (String) boilerid;
	}



	@Override
	public HashMap<String, Object> searchRPData1(String oilname,
			String areablock, String stationNumber, String group,
			String boilersName, String stime, String etime, String oilNmame,
			String pageCode, int pageNo, String sort, String order,
			int rowsPerpage, String totalNumFlag) {
		JSONArray jsonArr = null;
		JSONObject tjo = null;
		HashMap<String,Object> map = new HashMap<String, Object>();

		Date today = new Date();
		String nowdate = DateBean.getTimeStr(today);
		if("".equals(stime)){
			stime = nowdate.substring(0, 10) + " 00:00:00";
		}else{
			stime = stime + " 00:00:00";
		}
		if("".equals(etime)){
			etime = nowdate.substring(0, 10) + " 23:59:59";
		}else{
			etime = etime + " 23:59:59";
		}
		
		
		String cloums = "select   ";
		String formTable = " from pc_rpd_boiler_common_hours_v  where 1=1  and REPORT_DATE between TO_DATE('"+stime+"','YYYY-MM-DD hh24:mi:ss') and TO_DATE('"+etime+"','YYYY-MM-DD hh24:mi:ss') ";
		String totalNum = "select count(*) ";
		
		if(!oilname.equals("")&&oilname!=null&&!oilname.equals("undefined")){
			formTable=formTable+" and OILDRILLING_STATION_NAME='"+oilname+"'";
		}
		if(!areablock.equals("")&&areablock!=null&&!areablock.equals("undefined")){
			formTable=formTable+" and BLOCK_NAME='"+areablock+"'";
		}
		if(!stationNumber.equals("")&&stationNumber!=null&&!stationNumber.equals("undefined")){
			formTable=formTable+" and BLOCKSTATION_NAME='"+stationNumber+"'";
		}
		if(!group.equals("")&&group!=null&&!group.equals("undefined")){
			formTable=formTable+" and STEAM_WORK_GROUP='"+group+"'";
		}
		if(!boilersName.equals("")&&boilersName!=null&&!boilersName.equals("undefined")){
			formTable=formTable+" and BOILER_NAME='"+boilersName+"'";
		}
		if(!oilNmame.equals("")&&oilNmame!=null&&!oilNmame.equals("undefined")){
			formTable=formTable+" and oilName='"+oilNmame+"'";
		}
		
		String[] cloumnsName = {"BLOCK_NAME","BLOCKSTATION_NAME","BOILER_NAME","REPORT_DATE","RUN_STATUS","GAS_INTO_PRESS","GAS1_PRESS","FIRE_MEASURE","GAS_FLOW",
				"PUMPIN_PRESS","PUMPOUT_TEMP","PUMPFC_FREQUENCY","WATERSUPPLY_FLOW","STEAMIN_TEMP","STEAMOUT_TEMP","STEAM_DRYNESS",
				"PUMPOUT_PRESS","CSIN_PRESS","CSIN_TEMP","CSOUT_PRESS","CSOUT_TEMP","CONVECTIONR_PRESS_REDUCTION","RSIN_PRESS","RSIN_TEMP",
				"RS_PRESS_REDUCTION","RS_TEMP","GAS2_PRESS","GAS3_PRESS","LUBE_PRESS","LUBE_TEMP","FAN_AIR_INTAKE_TEMP","BURNER_TEMP","HEARTH_PRESS","EJECTSMOKE_TEMP",
				"SYSTEM_VOLTAGE","PUMP_MOTOR_CURRENT","PUMP_MOTOR_TEMP","FAN_MOTOR_CURRENT","FUEL_GAS_DENSITY","H2S_DENSITY","SEWAGE_BUFFER_TANK","STEAM_WORK_GROUP","OILDRILLING_STATION_NAME","STEAM_INJE_UNIT"};
		
		String[] cloumnsName2 = {"BLOCK_NAME","BOILER_NAME","REPORT_DATE","RUN_STATUS","GAS_INTO_PRESS","GAS1_PRESS","FIRE_MEASURE","GAS_FLOW",
				"PUMPIN_PRESS","PUMPOUT_TEMP","PUMPFC_FREQUENCY","WATERSUPPLY_FLOW","STEAMIN_TEMP","STEAMOUT_TEMP","STEAM_DRYNESS",
				"PUMPOUT_PRESS","CSIN_PRESS","CSIN_TEMP","CSOUT_PRESS","CSOUT_TEMP","CONVECTIONR_PRESS_REDUCTION","RSIN_PRESS","RSIN_TEMP",
				"RS_PRESS_REDUCTION","RS_TEMP","GAS2_PRESS","GAS3_PRESS","LUBE_PRESS","LUBE_TEMP","FAN_AIR_INTAKE_TEMP","BURNER_TEMP","HEARTH_PRESS","EJECTSMOKE_TEMP",
				"SYSTEM_VOLTAGE","PUMP_MOTOR_CURRENT","PUMP_MOTOR_TEMP","FAN_MOTOR_CURRENT","FUEL_GAS_DENSITY","H2S_DENSITY","SEWAGE_BUFFER_TANK","STEAM_WORK_GROUP","OILDRILLING_STATION_NAME","STEAM_INJE_UNIT"};
	
		
		String kk="BLOCK_NAME";
		for(int m=1;m<cloumnsName.length;m++){
			if("REPORT_DATE".equals(cloumnsName[m])){ 
				//kk=kk+","+"to_char(ACQUISITION_TIME,'YYYY-MM-DD hh24:mi:ss') as CJSJS"; 
				  kk += ",to_char(REPORT_DATE,'YYYY-MM-DD hh24:mi:ss') as REPORT_DATE";
			}else{ 
				kk=kk+","+cloumnsName[m];
				
			} 
		}
//		for(int m=1;m<cloumnsName.length;m++){
//			
//			if("日报".equals(pageCode) && "REPORT_DATE".equals(cloumnsName[m])){
////				if ("日报".equals(pageCode)) {
////					continue;
////				}
//				kk=kk+",to_char(REPORT_DATE,'YYYY-MM-DD') as CJSJS"; 
//			}
//			
//			if ("日报".equals(pageCode) && "CHECK_DATE".equals(cloumnsName[m])) {
//				kk=kk+",CHECK_DATE,to_char(CHECK_DATE,'YYYY-MM-DD HH24:MI') as CHECK_DATE";
//			}
//			if ("日报".equals(pageCode) && "RLAST_ODATE".equals(cloumnsName[m])) {
//				kk=kk+",RLAST_ODATE,to_char(RLAST_ODATE,'YYYY-MM-DD HH24:MI') as RLAST_ODATE";
//			}
//			kk=kk+","+cloumnsName[m];
//		}
		if ("export".equals(totalNumFlag)) {
			
			kk = "BLOCK_NAME";
			for(int m=1;m<cloumnsName2.length;m++){
				if("REPORT_DATE".equals(cloumnsName2[m])){
					kk=kk+","+"to_char(REPORT_DATE,'YYYY-MM-DD hh24:mi:ss') as REPORT_DATE";
				}
				
				else {
					kk=kk+","+cloumnsName2[m];
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
			total = srglRDDao.getCounts(totalNum);
		}
		if ("totalNum".equals(totalNumFlag)) {
			map.put("totalNum", total + "");
			return map;
		}
		//排序
		if(!"".equals(sort) && !"REPORT_DATE".equals(sort)){
			for (String cloumn : cloumnsName) {
				if(cloumn.equals(sort)){
					thinOilWellRD +=" order by " + cloumn + " " + order;
					break;
				}
				
			}
		}
		else {
			thinOilWellRD +=" order by REPORT_DATE ,BOILER_NAME";
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
			lo = srglRDDao.searchRBData1(thinOilWellRD,start,rowsPerpage,cloumnsName);
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
	public JSONObject searchSrglResults(String areablock, String blockstationname, String boilersName,
			String oilationname,String startDate, String endDate) throws Exception {
		
		
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
		String formTable = " from  pc_rd_boiler_common_v  where 1=1  and acquisition_time between TO_DATE('"+startDate+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+endDate+"','YYYY-MM-DD HH24:MI:SS') ";
		

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
		hql += "to_char(acquisition_time,'YYYY-MM-DD hh24:mi:ss') as CJSJ";
		for (int m = 1; m < cloumnsName.length; m++) {
			hql = hql + ", " + cloumnsName[m];
		}

		hql += formTable + "   order by ACQUISITION_TIME ";
		
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
	public JSONObject searchSrglResults1(String areablock, String blockstationname, String boilersName,
			String oilationname,String startDate, String endDate) throws Exception {
		
		
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
		String formTable = " from  pc_rpd_boiler_common_v  where 1=1  and report_date between TO_DATE('"+startDate+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+endDate+"','YYYY-MM-DD HH24:MI:SS') ";
		

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

		hql += formTable + "  order by report_date";
		
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
						// TODO Auto-generated catch block
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
	public JSONObject searchBolierSupResults(String areablock,
			String blockstationname, String boilersName, String oilationname,
			String startDate, String endDate) throws Exception{
		
		JSONObject tjo = new JSONObject();
		List<Long> timeList = new ArrayList<Long>();
		List<BigDecimal> tempList = new ArrayList<BigDecimal>();
		List<BigDecimal> pressList = new ArrayList<BigDecimal>();
		List<BigDecimal> grdyjList = new ArrayList<BigDecimal>();

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
		String formTable = " from  pc_rpd_boiler_superheat_v  where 1=1  and report_date between TO_DATE('"+startDate+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+endDate+"','YYYY-MM-DD HH24:MI:SS') ";
		

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

		String[] cloumnsName = {"report_date","STEAMIN_TEMP","STEAMOUT_TEMP","SUPERHEAT_PRESS_REDUCTION"};
		hql += "to_char(report_date,'YYYY-MM-DD hh24:mi:ss') as CJSJ";
		for (int m = 1; m < cloumnsName.length; m++) {
			hql = hql + ", " + cloumnsName[m];
		}

		hql += formTable + "  order by report_date";
		
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
				grdyjList.add((BigDecimal) o[3]);

			}
		}

		tjo.put("time", timeList);
		tjo.put("temp", tempList);
		tjo.put("press", pressList);
		tjo.put("grdyj", grdyjList);

		return tjo;
	}


}