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
import com.echo.dao.BoilerSuperheatRDDao;
import com.echo.dto.PcRpdBoilerSuperheatT;
import com.echo.service.BoilerSuperheatRDService;
import com.echo.util.DateBean;

public class BoilerSuperheatRDServiceImpl implements BoilerSuperheatRDService{
	private BoilerSuperheatRDDao boilerSuperheatRDDao;
	
	
	public void setBoilerSuperheatRDDao(BoilerSuperheatRDDao boilerSuperheatRDDao) {
		this.boilerSuperheatRDDao = boilerSuperheatRDDao;
	}

	public HashMap<String,Object> searchData(String oilname,String blockname,String areablock,String ghname,String boilersname,String acceptunit,String stime,String etime,int pageNo,String sort,String order,int rowsPerpage,String totalNumFlag,String group) {
		JSONArray jsonArr = null;
		JSONObject tjo = null;
		HashMap<String,Object> map = new HashMap<String, Object>();
		String cloums = "select   ";
		String formTable = " from pc_rd_boiler_superheat_v  where 1=1  and ACQUISITION_TIME between TO_DATE('"+stime+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+etime+"','YYYY-MM-DD HH24:MI:SS') ";
		String totalNum = "select count(*) ";
		if(!oilname.equals("")&&oilname!=null&&!oilname.equals("undefined")){
			formTable=formTable+" and oilationname='"+oilname+"'";
		}
		if(!blockname.equals("")&&blockname!=null&&!blockname.equals("undefined")){
			formTable=formTable+" and BLOCKSTATION_NAME='"+blockname+"'";
		}
		if(!areablock.equals("")&&areablock!=null&&!areablock.equals("undefined")){
			formTable=formTable+" and block_name='"+areablock+"'";
		}
		if(!boilersname.equals("")&&boilersname!=null&&!boilersname.equals("undefined")){
			formTable=formTable+" and boiler_name='"+boilersname+"'";
		}
		if(!acceptunit.equals("")&&acceptunit!=null&&!acceptunit.equals("undefined")){
			formTable=formTable+" and OILDRILLING_STATION_NAME='"+acceptunit+"'";
		}
		
		if(!group.equals("")&&group!=null&&!group.equals("undefined")){
			formTable=formTable+" and STEAM_WORK_GROUP='"+group+"'";
		}
		
		String[] cloumnsName = {"BLOCK_NAME","BOILER_NAME","ACQUISITION_TIME","RUN_STATUS","GAS_INTO_PRESS","GAS1_PRESS","FIRE_MEASURE","GAS_FLOW","PUMPIN_PRESS","PUMPOUT_TEMP","PUMPFC_FREQUENCY","WATERSUPPLY_FLOW","STEAMIN_TEMP","STEAMOUT_TEMP","SUPERHEAT_DEGREE","SUPERHEATOUT_TEMP","SL_LEVEL","RS_PRESS_REDUCTION","SUPERHEAT_PRESS_REDUCTION","MIXER_PRESS_REDUCTION","PUMPOUT_PRESS","CSIN_PRESS","CSIN_TEMP","CSOUT_PRESS","CSOUT_TEMP","CONVECTIONR_PRESS_REDUCTION","RSIN_PRESS","RSIN_TEMP","RS_PRESS","RSOUT_TEMP","RS_TEMP","RS_DRYNESS","SEPARATOR_PRESS_EXPORT","SUPERHEATIN_PRESS","SUPERHEATIN_TEMP","SUPERHEATOUT_PRESS","SUPERHEAT_TEMP","SUPERHEATIN_FLOW","MIXER_PRESS_WATER","GAS2_PRESS","GAS3_PRESS","LUBE_TEMP","FAN_AIR_EXPORT_PRESS","FAN_AIR_INTAKE_TEMP","BURNER_TEMP","HEARTH_PRESS","EJECTSMOKE_TEMP","SYSTEM_VOLTAGE","PUMP_MOTOR_CURRENT","PUMP_MOTOR_TEMP","FAN_MOTOR_CURRENT","FUEL_GAS_DENSITY","H2S_DENSITY","SEWAGE_BUFFER_TANK","STEAM_WORK_GROUP","STEAM_INJE_UNIT","OILDRILLING_STATION_NAME","LEVEL_TJFKD"};
		
		String kk="";
		for(int m=0;m<cloumnsName.length;m++){
			if(m==2){ 
				kk=kk+","+"to_char(ACQUISITION_TIME,'YYYY-MM-DD hh24:mi') as CJSJS"; 
			}else{
				kk=kk+","+cloumnsName[m];
			}
		}
		String[] cloumnsNames = null;
		if ("export".equals(totalNumFlag)) {
			String[] cloumnsNames2 = {"BLOCK_NAME","BOILER_NAME","ACQUISITION_TIME","RUN_STATUS","GAS_INTO_PRESS","GAS1_PRESS","FIRE_MEASURE","GAS_FLOW","PUMPIN_PRESS","PUMPOUT_TEMP","PUMPFC_FREQUENCY","WATERSUPPLY_FLOW","STEAMIN_TEMP","STEAMOUT_TEMP","SUPERHEAT_DEGREE","SUPERHEATOUT_TEMP","SL_LEVEL","RS_PRESS_REDUCTION","SUPERHEAT_PRESS_REDUCTION","MIXER_PRESS_REDUCTION","PUMPOUT_PRESS","CSIN_PRESS","CSIN_TEMP","CSOUT_PRESS","CSOUT_TEMP","CONVECTIONR_PRESS_REDUCTION","RSIN_PRESS","RSIN_TEMP","RS_PRESS","RSOUT_TEMP","RS_TEMP","RS_DRYNESS","SEPARATOR_PRESS_EXPORT","SUPERHEATIN_PRESS","SUPERHEATIN_TEMP","SUPERHEATOUT_PRESS","SUPERHEAT_TEMP","SUPERHEATIN_FLOW","MIXER_PRESS_WATER","GAS2_PRESS","GAS3_PRESS","LUBE_TEMP","FAN_AIR_EXPORT_PRESS","FAN_AIR_INTAKE_TEMP","BURNER_TEMP","HEARTH_PRESS","EJECTSMOKE_TEMP","SYSTEM_VOLTAGE","PUMP_MOTOR_CURRENT","PUMP_MOTOR_TEMP","FAN_MOTOR_CURRENT","FUEL_GAS_DENSITY","H2S_DENSITY","SEWAGE_BUFFER_TANK","STEAM_WORK_GROUP","OILDRILLING_STATION_NAME","STEAM_INJE_UNIT","LEVEL_TJFKD"};

			cloumnsNames = cloumnsNames2;
			kk = "";
			for(int m=0;m<cloumnsNames.length;m++){
				if(m==2){ 
					kk=kk+","+"to_char(ACQUISITION_TIME,'YYYY-MM-DD hh24:mi') as CJSJS"; 
				}else{
				kk=kk+","+cloumnsNames[m];
				}
			}
		}
		kk = kk.substring(1);
		String thinOilWellRD = cloums + kk+formTable;
		//获取总条数
		int total = 0;
		if ("1".equals(totalNum)) {
			total = 1;
		} else {
			totalNum += formTable;
			total = boilerSuperheatRDDao.getCounts(totalNum);
		}
		if ("totalNum".equals(totalNumFlag)) {
			map.put("totalNum", total + "");
			return map;
		}
		//排序
		if ("export".equals(totalNumFlag)) {
			thinOilWellRD +=" order by BOILER_NAME ";
		}else
		if(!"".equals(sort) && !"CJSJS".equals(sort)){
			for (String cloumn : cloumnsName) {
				if(cloumn.equals(sort)){
					thinOilWellRD +=" order by " + cloumn + " " + order;
					break;
				}
				
			}
		}
		else {
			thinOilWellRD +=" order by CJSJS ";
		}
		//计算分页
		PageControl control = new PageControl();
		//当前页
		List<Object[]> lo = new ArrayList<Object[]>();
		control.setInt_num(rowsPerpage);
		if ("export".equals(totalNumFlag)) {
			lo = boilerSuperheatRDDao.searchData(thinOilWellRD);
		}
		else {
			control.init(pageNo, total);
			//开始条数
			int start = control.getStart()-1;
			lo = boilerSuperheatRDDao.searchData(thinOilWellRD,start,rowsPerpage);
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
					
						tjo.put(cloumnsName[i].toUpperCase(), o[i].toString());
					
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




	public List<PcRpdBoilerSuperheatT> searchCross(String id, String boilerid,String name, String date) throws Exception {
		List<PcRpdBoilerSuperheatT> pscList = null;
		String hql =" FROM  PcRdBoilerSuperheatT a where 1=1 " ;
		if(id != null && !"".equals(id)){
			hql += " and a.boilerCrossddid ='"+id+"'";
		}
		if(name !=null && !"".equals(name)){
			hql += " and a.boilerName ='"+name+"'";
		}
		if(boilerid !=null && !"".equals(boilerid)){
			hql += "  and a.boilerid ='"+boilerid+"' ";
		}
		if(date !=null && !"".equals(date)){
			hql += " and  a.reportDate = TO_DATE('"+date+"','YYYY-MM-DD ') ";
		}
		pscList = boilerSuperheatRDDao.searchCross(hql);
		return  pscList;
	}
	public boolean addPsc(PcRpdBoilerSuperheatT psc) throws Exception {
		
		return boilerSuperheatRDDao.save(psc);
	}
	public boolean updatePsc(PcRpdBoilerSuperheatT psc) throws Exception {
		return boilerSuperheatRDDao.update(psc);
	}
	
	public HashMap<String,Object> searchRPDData(String oilationname,String blockstationname,String group,String ghname,String boilersName,String stime,String etime,int pageNo,String sort,String order,int rowsPerpage,String totalNumFlag) {
		JSONArray jsonArr = null;
		JSONObject tjo = null;
		HashMap<String,Object> map = new HashMap<String, Object>();
		String cloums = "select BOILER_NAME ";
		String formTable = " from pc_rpd_boiler_cross_v  where 1=1  and cjsjs between TO_DATE('"+stime+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+etime+"','YYYY-MM-DD HH24:MI:SS') ";
		String totalNum = "select count(*) ";
		
		if(!oilationname.equals("")&&oilationname!=null&&!oilationname.equals("undefined")&!oilationname.equals("全部")){
			formTable=formTable+" and OILDRILLING_STATION_NAME='"+oilationname+"'";
		}
		if(!blockstationname.equals("")&&blockstationname!=null&&!blockstationname.equals("undefined")){
			formTable=formTable+" and GRZH='"+blockstationname+"'";
		}
		if(!group.equals("")&&group!=null&&!group.equals("undefined")){
			formTable=formTable+" and yxz='"+group+"'";
		}
		if(!ghname.equals("")&&group!=null&&!ghname.equals("undefined")){
			formTable=formTable+" and gh_id='"+ghname+"'";
		}
		if(!boilersName.equals("")&&boilersName!=null&&!boilersName.equals("undefined")){
			formTable=formTable+" and boiler_name='"+boilersName+"'";
		}
		
		String[] cloumnsName = {"BOILER_NAME","CJSJS","GLBH","GRZH","OILDRILLING_STATION_NAME","BLOCKSTATION_NAME","YXZ","STEAMOUT_TEMP","STEAMIN_TEMP","EJECTSMOKE_TEMP","BURNER_TEMP","CSIN_PRESS","CSOUT_PRESS","CSIN_TEMP","CSOUT_TEMP","SL_LEVEL","SUPERHEAT_TEMP","SUPERHEAT_PIEZORESISTANCE","SUPERHEATIN_TEMP","SUPERHEATOUT_TEMP","SUPERHEATIN_PRESS","SUPERHEATOUT_PRESS","SUPERHEATIN_FLOW","HEARTH_PRESS","GAS1_PRESS","GAS2_PRESS","GAS3_PRESS","RS_TEMP","RS_DRYNESS","RS_PIEZORESISTANCE","RSIN_PRESS","RS_PRESS","RSIN_TEMP","RSOUT_TEMP","PUMPA_FLOW","PUMPB_FLOW","PUMPC_FLOW","PUMPA_PRESS","PUMPB_PRESS","PUMPC_PRESS","FANA_ELECTRICITY","FANB_ELECTRICITY","FANC_ELECTRICITY","PUMPFC_FREQUENCY","PUMPIN_PRESS","PUMPOUT_PRESS","PUMPIN_TEMP","PUMPOUT_TEMP","WATERSUPPLY_FLOW","WATERSUPPLY_TOTAL","GAS_FLOW","GAS_TOTAL","STEAMINJECTION_TOTAL","DAILY_CUMULATIVE_WATER","DAILY_CUMULATIVE_GAS","DAILY_CUMULATIVE_STEAM","BOILER_CROSSDDID","BOILERID"};
		for (String cn : cloumnsName) {
			if ("BOILER_CROSSDDID".equals(cn) && "export".equals(totalNumFlag)) {
				break;
			}
			if ("CJSJS".equals(cn)) {
				cloums += ",to_char(CJSJS,'YYYY-MM-DD HH24:MI:SS') as CJSJS";
				continue;
			}
			if (!"BOILER_NAME".equals(cn)) {
				cloums += "," + cn;
			}
		}
		String thinOilWellRD = cloums + formTable;
		//获取总条数
		int total = 0;
		if ("1".equals(totalNum)) {
			total = 1;
		} else {
			totalNum += formTable;
			total = boilerSuperheatRDDao.getCounts(totalNum);
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
			thinOilWellRD +=" order by CJSJS ";
		}
		//计算分页
		PageControl control = new PageControl();
		//当前页
		List<Object[]> lo = new ArrayList<Object[]>();
		control.setInt_num(rowsPerpage);
		if ("export".equals(totalNumFlag)) {
			lo = boilerSuperheatRDDao.searchData(thinOilWellRD);
		}
		else {
			control.init(pageNo, total);
			//开始条数
			int start = control.getStart()-1;
			lo = boilerSuperheatRDDao.searchRPDData(thinOilWellRD,start,rowsPerpage, cloumnsName);
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
	public boolean removeBoilerCrpssdRPD(String boilerCrossddid)throws Exception{
		PcRpdBoilerSuperheatT prbc = new PcRpdBoilerSuperheatT();
		prbc = boilerSuperheatRDDao.searchBoilerCrpssdRPD(boilerCrossddid);
		return boilerSuperheatRDDao.removeBoilerCrpssdRPD(prbc);
	}

	@Override
	public HashMap<String, Object> searchData1(String oilname,
			String blockname, String areablock, String ghname,
			String boilersname, String acceptunit, String stime, String etime,
			int pageNo, String sort, String order, int rowsPerpage,
			String totalNumFlag, String group) {
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
		String formTable = " from pc_rpd_boiler_sup_hours_v  where 1=1  and REPORT_DATE between TO_DATE('"+stime+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+etime+"','YYYY-MM-DD HH24:MI:SS') ";
		String totalNum = "select count(*) ";
		if(!oilname.equals("")&&oilname!=null&&!oilname.equals("undefined")){
			formTable=formTable+" and oilationname='"+oilname+"'";
		}
		if(!blockname.equals("")&&blockname!=null&&!blockname.equals("undefined")){
			formTable=formTable+" and BLOCKSTATION_NAME='"+blockname+"'";
		}
		if(!areablock.equals("")&&areablock!=null&&!areablock.equals("undefined")){
			formTable=formTable+" and block_name='"+areablock+"'";
		}
		if(!boilersname.equals("")&&boilersname!=null&&!boilersname.equals("undefined")){
			formTable=formTable+" and boiler_name='"+boilersname+"'";
		}
		if(!acceptunit.equals("")&&acceptunit!=null&&!acceptunit.equals("undefined")){
			formTable=formTable+" and OILDRILLING_STATION_NAME='"+acceptunit+"'";
		}
		
		if(!group.equals("")&&group!=null&&!group.equals("undefined")){
			formTable=formTable+" and STEAM_WORK_GROUP='"+group+"'";
		}
		
		String[] cloumnsName = {"BLOCK_NAME","BOILER_NAME","REPORT_DATE","RUN_STATUS","GAS_INTO_PRESS","GAS1_PRESS","FIRE_MEASURE","GAS_FLOW","PUMPIN_PRESS","PUMPOUT_TEMP","PUMPFC_FREQUENCY","WATERSUPPLY_FLOW","STEAMIN_TEMP","STEAMOUT_TEMP","SUPERHEAT_DEGREE","SUPERHEATOUT_TEMP","SL_LEVEL","RS_PRESS_REDUCTION","SUPERHEAT_PRESS_REDUCTION","MIXER_PRESS_REDUCTION","PUMPOUT_PRESS","CSIN_PRESS","CSIN_TEMP","CSOUT_PRESS","CSOUT_TEMP","CONVECTIONR_PRESS_REDUCTION","RSIN_PRESS","RSIN_TEMP","RS_PRESS","RSOUT_TEMP","RS_TEMP","RS_DRYNESS","SEPARATOR_PRESS_EXPORT","SUPERHEATIN_PRESS","SUPERHEATIN_TEMP","SUPERHEATOUT_PRESS","SUPERHEAT_TEMP","SUPERHEATIN_FLOW","MIXER_PRESS_WATER","GAS2_PRESS","GAS3_PRESS","LUBE_TEMP","FAN_AIR_EXPORT_PRESS","FAN_AIR_INTAKE_TEMP","BURNER_TEMP","HEARTH_PRESS","EJECTSMOKE_TEMP","SYSTEM_VOLTAGE","PUMP_MOTOR_CURRENT","PUMP_MOTOR_TEMP","FAN_MOTOR_CURRENT","FUEL_GAS_DENSITY","H2S_DENSITY","SEWAGE_BUFFER_TANK","STEAM_WORK_GROUP","STEAM_INJE_UNIT","OILDRILLING_STATION_NAME"};
		
		String kk="";
		for(int m=0;m<cloumnsName.length;m++){
			if(m==2){ 
				kk=kk+","+"to_char(REPORT_DATE,'YYYY-MM-DD hh24:mi') as REPORT_DATE"; 
			}else{
				kk=kk+","+cloumnsName[m];
			}
		}
		String[] cloumnsNames = null;
		if ("export".equals(totalNumFlag)) {
			String[] cloumnsNames2 = {"BLOCK_NAME","BOILER_NAME","REPORT_DATE","RUN_STATUS","GAS_INTO_PRESS","GAS1_PRESS","FIRE_MEASURE","GAS_FLOW","PUMPIN_PRESS","PUMPOUT_TEMP","PUMPFC_FREQUENCY","WATERSUPPLY_FLOW","STEAMIN_TEMP","STEAMOUT_TEMP","SUPERHEAT_DEGREE","SUPERHEATOUT_TEMP","SL_LEVEL","RS_PRESS_REDUCTION","SUPERHEAT_PRESS_REDUCTION","MIXER_PRESS_REDUCTION","PUMPOUT_PRESS","CSIN_PRESS","CSIN_TEMP","CSOUT_PRESS","CSOUT_TEMP","CONVECTIONR_PRESS_REDUCTION","RSIN_PRESS","RSIN_TEMP","RS_PRESS","RSOUT_TEMP","RS_TEMP","RS_DRYNESS","SEPARATOR_PRESS_EXPORT","SUPERHEATIN_PRESS","SUPERHEATIN_TEMP","SUPERHEATOUT_PRESS","SUPERHEAT_TEMP","SUPERHEATIN_FLOW","MIXER_PRESS_WATER","GAS2_PRESS","GAS3_PRESS","LUBE_TEMP","FAN_AIR_EXPORT_PRESS","FAN_AIR_INTAKE_TEMP","BURNER_TEMP","HEARTH_PRESS","EJECTSMOKE_TEMP","SYSTEM_VOLTAGE","PUMP_MOTOR_CURRENT","PUMP_MOTOR_TEMP","FAN_MOTOR_CURRENT","FUEL_GAS_DENSITY","H2S_DENSITY","SEWAGE_BUFFER_TANK","STEAM_WORK_GROUP","OILDRILLING_STATION_NAME","STEAM_INJE_UNIT"};

			cloumnsNames = cloumnsNames2;
			kk = "";
			for(int m=0;m<cloumnsNames.length;m++){
				if(m==2){ 
					kk=kk+","+"to_char(REPORT_DATE,'YYYY-MM-DD hh24:mi') as REPORT_DATE"; 
				}else{
				kk=kk+","+cloumnsNames[m];
				}
			}
		}
		kk = kk.substring(1);
		String thinOilWellRD = cloums + kk+formTable;
		//获取总条数
		int total = 0;
		if ("1".equals(totalNum)) {
			total = 1;
		} else {
			totalNum += formTable;
			total = boilerSuperheatRDDao.getCounts(totalNum);
		}
		if ("totalNum".equals(totalNumFlag)) {
			map.put("totalNum", total + "");
			return map;
		}
		//排序
		if ("export".equals(totalNumFlag)) {
			thinOilWellRD +=" order by BOILER_NAME ";
		}else
		if(!"".equals(sort) && !"REPORT_DATE".equals(sort)){
			for (String cloumn : cloumnsName) {
				if(cloumn.equals(sort)){
					thinOilWellRD +=" order by " + cloumn + " " + order;
					break;
				}
				
			}
		}
		else {
			thinOilWellRD +=" order by REPORT_DATE ";
		}
		//计算分页
		PageControl control = new PageControl();
		//当前页
		List<Object[]> lo = new ArrayList<Object[]>();
		control.setInt_num(rowsPerpage);
		if ("export".equals(totalNumFlag)) {
			lo = boilerSuperheatRDDao.searchData1(thinOilWellRD);
		}
		else {
			control.init(pageNo, total);
			//开始条数
			int start = control.getStart()-1;
			lo = boilerSuperheatRDDao.searchData1(thinOilWellRD,start,rowsPerpage);
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
					
						tjo.put(cloumnsName[i].toUpperCase(), o[i].toString());
					
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
	public JSONObject searchBolierSupResults(String areablock,
			String blockstationname, String boilersName, String oilationname,
			String startDate, String endDate) {
		
		JSONObject tjo = new JSONObject();
		List<Long> timeList = new ArrayList<Long>();
		List<BigDecimal> tempList = new ArrayList<BigDecimal>();
		List<BigDecimal> pressList = new ArrayList<BigDecimal>();
		List<BigDecimal> grdyjList = new ArrayList<BigDecimal>();
		List<BigDecimal> chqList = new ArrayList<BigDecimal>();

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
		String formTable = " from pc_rd_boiler_superheat_v    where 1=1  and ACQUISITION_TIME between TO_DATE('"+startDate+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+endDate+"','YYYY-MM-DD HH24:MI:SS') ";


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

		String[] cloumnsName = {"ACQUISITION_TIME","STEAMIN_TEMP","STEAMOUT_TEMP","SUPERHEAT_PRESS_REDUCTION","MIXER_PRESS_REDUCTION"};
		hql += "to_char(ACQUISITION_TIME,'YYYY-MM-DD hh24:mi:ss') as CJSJ";
		for (int m = 1; m < cloumnsName.length; m++) {
			hql = hql + ", " + cloumnsName[m];
		}

		hql += formTable+"  order by ACQUISITION_TIME";
		
		List<Object[]> lo = boilerSuperheatRDDao.queryInfo(hql);
		
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
				chqList.add((BigDecimal) o[4]);
			}
		}

		tjo.put("time", timeList);
		tjo.put("temp", tempList);
		tjo.put("press", pressList);
		tjo.put("grdyj", grdyjList);
		tjo.put("chq", chqList);

		return tjo;
	}



}
