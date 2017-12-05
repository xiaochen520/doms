package com.echo.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.beans.PageControl;
import com.echo.dao.BoilerCrosslRDDao;
import com.echo.dto.PcRpdBoilerSuperheatT;
import com.echo.service.BoilerCrosslRDService;
import com.echo.util.CommonsUtil;

public class BoilerCrosslRDServiceImpl implements BoilerCrosslRDService{
	private BoilerCrosslRDDao boilerCrosslRDDao;
	public void setBoilerCrosslRDDao(BoilerCrosslRDDao boilerCrosslRDDao) {
		this.boilerCrosslRDDao = boilerCrosslRDDao;
	}
	public HashMap<String,Object> searchData(String qk,String zh,String jh,String gh,String name,String stime,String etime,int pageNo,String sort,String order,int rowsPerpage,String totalNumFlag) {
		JSONArray jsonArr = null;
		JSONObject tjo = null;
		HashMap<String,Object> map = new HashMap<String, Object>();
		String cloums = "select   ";
		String formTable = " from pc_rd_boiler_cross_v  where 1=1  and cjsj between TO_DATE('"+stime+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+etime+"','YYYY-MM-DD HH24:MI:SS') ";
		String totalNum = "select count(*) ";
		
		if(!qk.equals("")&&qk!=null&&!qk.equals("undefined")&!qk.equals("全部")){
			formTable=formTable+" and OILSTATIONNAME='"+qk+"'";
		}
		if(!zh.equals("")&&zh!=null&&!zh.equals("undefined")){
			formTable=formTable+" and areablock='"+zh+"'";
		}
		if(!jh.equals("")&&jh!=null&&!jh.equals("undefined")){
			formTable=formTable+" and blockstationname='"+jh+"'";
		}
		if(!gh.equals("")&&jh!=null&&!gh.equals("undefined")){
			formTable=formTable+" and gh_id='"+gh+"'";
		}
		if(!name.equals("")&&name!=null&&!name.equals("undefined")){
			formTable=formTable+" and boiler_name='"+name+"'";
		}
		
		String[] cloumnsName = {"OILSTATIONNAME","AREABLOCK","BLOCKSTATIONNAME","YXZ","BOILER_NAME","GLBH","CJSJ","STATIONNO","STEAMOUT_TEMP","STEAMIN_TEMP","EJECTSMOKE_TEMP","BURNER_TEMP","CSIN_PRESS","CSOUT_PRESS",
				"CSIN_TEMP","CSOUT_TEMP","SL_LEVEL","SUPERHEAT_TEMP","SUPERHEAT_PIEZORESISTANCE","SUPERHEATIN_TEMP","SUPERHEATOUT_TEMP","SUPERHEATIN_PRESS","SUPERHEATOUT_PRESS","SUPERHEATIN_FLOW","HEARTH_PRESS","GAS1_PRESS"
				,"GAS2_PRESS","GAS3_PRESS","RS_TEMP","RS_DRYNESS","RS_PIEZORESISTANCE","RSIN_PRESS","RS_PRESS","RSIN_TEMP","RSOUT_TEMP","PUMPA_FLOW","PUMPB_FLOW","PUMPC_FLOW","PUMPA_PRESS","PUMPB_PRESS","PUMPC_PRESS"
				,"FANA_ELECTRICITY","FANB_ELECTRICITY","FANC_ELECTRICITY","PUMPFC_FREQUENCY","PUMPIN_PRESS","PUMPOUT_PRESS","PUMPIN_TEMP","PUMPOUT_TEMP","WATERSUPPLY_FLOW","WATERSUPPLY_TOTAL","GAS_FLOW","GAS_TOTAL","STEAMINJECTION_TOTAL"};
		String kk="OILSTATIONNAME";
		for(int m=1;m<cloumnsName.length;m++){
			kk=kk+","+cloumnsName[m];
		}
		String[] cloumnsNames = null;
		if ("export".equals(totalNumFlag)) {
			String[] cloumnsNames2 = {"OILSTATIONNAME","CJSJ","STATIONNO","GLBH","STEAMOUT_TEMP","STEAMIN_TEMP","EJECTSMOKE_TEMP","BURNER_TEMP","CSIN_PRESS","CSOUT_PRESS","CSIN_TEMP","CSOUT_TEMP","CSIN_PRESS","SL_LEVEL",
					"SUPERHEAT_TEMP","SUPERHEAT_PIEZORESISTANCE","SUPERHEATIN_TEMP","SUPERHEATOUT_TEMP","SUPERHEATIN_PRESS","SUPERHEATOUT_PRESS","SUPERHEATIN_FLOW","HEARTH_PRESS","GAS1_PRESS"
					,"GAS2_PRESS","GAS3_PRESS","RS_TEMP","RS_DRYNESS","RS_PIEZORESISTANCE","RSIN_PRESS","RS_PRESS","RSIN_TEMP","RSOUT_TEMP","PUMPA_FLOW","PUMPB_FLOW","PUMPC_FLOW","PUMPA_PRESS","PUMPB_PRESS","PUMPC_PRESS"
					,"FANA_ELECTRICITY","FANB_ELECTRICITY","FANC_ELECTRICITY","PUMPFC_FREQUENCY","PUMPIN_PRESS","PUMPOUT_PRESS","PUMPIN_TEMP","PUMPOUT_TEMP","WATERSUPPLY_FLOW","WATERSUPPLY_TOTAL","GAS_FLOW","GAS_TOTAL","STEAMINJECTION_TOTAL"};
			cloumnsNames = cloumnsNames2;
			kk = "OILSTATIONNAME";
			for(int m=1;m<cloumnsNames.length;m++){
				if(m==1){ 
					kk=kk+","+"to_char(CJSJ,'YYYY-MM-DD hh24:mi:ss') as CJSJ"; 
				}else{
				kk=kk+","+cloumnsNames[m];
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
			total = boilerCrosslRDDao.getCounts(totalNum);
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
			lo = boilerCrosslRDDao.searchData(thinOilWellRD);
		}
		else {
			control.init(pageNo, total);
			//开始条数
			int start = control.getStart()-1;
			lo = boilerCrosslRDDao.searchData(thinOilWellRD,start,rowsPerpage);
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




	public List<PcRpdBoilerSuperheatT> searchCross(String id, String boilerid,String name, String date) throws Exception {
		List<PcRpdBoilerSuperheatT> pscList = null;
		String hql =" FROM  PcRpdBoilerSuperheatT a where 1=1 " ;
		if(id != null && !"".equals(id)){
			hql += " and rpdBoilerSuperheatId ='"+id+"'";
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
		pscList = boilerCrosslRDDao.searchCross(hql);
		return  pscList;
	}
	public boolean addPsc(PcRpdBoilerSuperheatT psc) throws Exception {
		
		return boilerCrosslRDDao.save(psc);
	}
	public boolean updatePsc(PcRpdBoilerSuperheatT psc) throws Exception {
		return boilerCrosslRDDao.update(psc);
	}
	
	public HashMap<String,Object> searchRPDData(String areablock,String blockstationname,String group,String ghname,String boilersName,String acceptunit,String stime,String etime,int pageNo,String sort,String order,int rowsPerpage,String totalNumFlag) {
		JSONArray jsonArr = null;
		JSONObject tjo = null;
		HashMap<String,Object> map = new HashMap<String, Object>();
		String cloums = "select  ";
		String formTable = " from pc_rpd_boiler_superheat_v  where 1=1  and REPORT_DATE between TO_DATE('"+stime+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+etime+"','YYYY-MM-DD HH24:MI:SS') ";
		String totalNum = "select count(*) ";
		
		if(!areablock.equals("")&&areablock!=null&&!areablock.equals("undefined")&!areablock.equals("全部")){
			formTable=formTable+" and BLOCK_NAME='"+areablock+"'";
		}
		if(!blockstationname.equals("")&&blockstationname!=null&&!blockstationname.equals("undefined")){
			formTable=formTable+" and BLOCKSTATION_NAME='"+blockstationname+"'";
		}
		if(!group.equals("")&&group!=null&&!group.equals("undefined")){
			formTable=formTable+" and STEAM_WORK_GROUP='"+group+"'";
		}
		if(!ghname.equals("")&&group!=null&&!ghname.equals("undefined")){
			formTable=formTable+" and oilationname='"+ghname+"'";
		}
		if(!boilersName.equals("")&&boilersName!=null&&!boilersName.equals("undefined")){
			formTable=formTable+" and boiler_name='"+boilersName+"'";
		}
		if(!acceptunit.equals("")&&acceptunit!=null&&!acceptunit.equals("undefined")){
			formTable=formTable+" and OILDRILLING_STATION_NAME='"+acceptunit+"'";
		}
		String[] cloumnsName = null;
		if("export".equals(totalNumFlag)){
			String[] cloumnsNameC = {"BLOCK_NAME","BOILER_NAME","REPORT_DATE","RUNTIME","GAS_INTO_PRESS","GAS1_PRESS","FIRE_MEASURE","GAS_FLOW","PUMPIN_PRESS","PUMPOUT_TEMP","PUMPFC_FREQUENCY","WATERSUPPLY_FLOW","STEAMIN_TEMP","STEAMOUT_TEMP","SUPERHEAT_DEGREE","SUPERHEATOUT_TEMP","SL_LEVEL","RS_PRESS_REDUCTION","SUPERHEAT_PRESS_REDUCTION","MIXER_PRESS_REDUCTION","PUMPOUT_PRESS","CSIN_PRESS","CSIN_TEMP","CSOUT_PRESS","CSOUT_TEMP","CONVECTIONR_PRESS_REDUCTION","RSIN_PRESS","RSIN_TEMP","RS_PRESS","RSOUT_TEMP","RS_TEMP","RS_DRYNESS","SEPARATOR_PRESS_EXPORT","SUPERHEATIN_PRESS","SUPERHEATIN_TEMP","SUPERHEATOUT_PRESS","SUPERHEAT_TEMP","SUPERHEATIN_FLOW","MIXER_PRESS_WATER","GAS2_PRESS","GAS3_PRESS","LUBE_TEMP","FAN_AIR_EXPORT_PRESS","FAN_AIR_INTAKE_TEMP","BURNER_TEMP","HEARTH_PRESS","EJECTSMOKE_TEMP","SYSTEM_VOLTAGE","PUMP_MOTOR_CURRENT","PUMP_MOTOR_TEMP","FAN_MOTOR_CURRENT","FUEL_GAS_DENSITY","H2S_DENSITY","SEWAGE_BUFFER_TANK","DAILY_CUMULATIVE_GAS","DAILY_CUMULATIVE_STEAM","STEAM_WORK_GROUP","OILDRILLING_STATION_NAME","STEAM_INJE_UNIT","REMARK"};
			cloumnsName = cloumnsNameC;
		}else{
			String[] cloumnsNameE = {"RPD_BOILER_SUPERHEAT_ID","BOILERID","BLOCK_NAME","BOILER_NAME","REPORT_DATE","RUNTIME","GAS_INTO_PRESS","GAS1_PRESS","FIRE_MEASURE","GAS_FLOW","PUMPIN_PRESS","PUMPOUT_TEMP","PUMPFC_FREQUENCY","WATERSUPPLY_FLOW","STEAMIN_TEMP","STEAMOUT_TEMP","SUPERHEAT_DEGREE","SUPERHEATOUT_TEMP","SL_LEVEL","RS_PRESS_REDUCTION","SUPERHEAT_PRESS_REDUCTION","MIXER_PRESS_REDUCTION","PUMPOUT_PRESS","CSIN_PRESS","CSIN_TEMP","CSOUT_PRESS","CSOUT_TEMP","CONVECTIONR_PRESS_REDUCTION","RSIN_PRESS","RSIN_TEMP","RS_PRESS","RSOUT_TEMP","RS_TEMP","RS_DRYNESS","SEPARATOR_PRESS_EXPORT","SUPERHEATIN_PRESS","SUPERHEATIN_TEMP","SUPERHEATOUT_PRESS","SUPERHEAT_TEMP","SUPERHEATIN_FLOW","MIXER_PRESS_WATER","GAS2_PRESS","GAS3_PRESS","LUBE_TEMP","FAN_AIR_EXPORT_PRESS","FAN_AIR_INTAKE_TEMP","BURNER_TEMP","HEARTH_PRESS","EJECTSMOKE_TEMP","SYSTEM_VOLTAGE","PUMP_MOTOR_CURRENT","PUMP_MOTOR_TEMP","FAN_MOTOR_CURRENT","FUEL_GAS_DENSITY","H2S_DENSITY","SEWAGE_BUFFER_TANK","DAILY_CUMULATIVE_GAS","DAILY_CUMULATIVE_STEAM","STEAM_WORK_GROUP","OILDRILLING_STATION_NAME","STEAM_INJE_UNIT","REMARK","CHECK_OPER","CHECK_DATE","RLAST_OPER","RLAST_ODATE"};
			cloumnsName = cloumnsNameE;
		}
		
		for (String cn : cloumnsName) {
			if ("BOILER_CROSSDDID".equals(cn) && "export".equals(totalNumFlag)) {
				break;
			}
			if ("REPORT_DATE".equals(cn)) {
				cloums += "to_char(REPORT_DATE,'YYYY-MM-DD') as REPORT_DATE,";
				continue;
			}
			if ("CHECK_DATE".equals(cn)) {
				cloums += "to_char(CHECK_DATE,'YYYY-MM-DD HH24:MI') as CHECK_DATE,";
				continue;
			}
			if ("RLAST_ODATE".equals(cn)) {
				cloums += "to_char(RLAST_ODATE,'YYYY-MM-DD HH24:MI') as RLAST_ODATE,";
				continue;
			}
			
			cloums += cn + "," ;
		}
		cloums = cloums.substring(0,cloums.length()-1);
		String thinOilWellRD = cloums + formTable;
		//获取总条数
		int total = 0;
		if ("1".equals(totalNum)) {
			total = 1;
		} else {
			totalNum += formTable;
			total = boilerCrosslRDDao.getCounts(totalNum);
		}
		if ("totalNum".equals(totalNumFlag)) {
			map.put("totalNum", total + "");
			return map;
		}
		//排序
		if(!"".equals(sort) && !"REPORT_DATE".equals(sort)){
			for (String cloumn : cloumnsName) {
				if(cloumn.toUpperCase().equals(sort)){
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
			lo = boilerCrosslRDDao.searchData(thinOilWellRD);
		}
		else {
			control.init(pageNo, total);
			//开始条数
			int start = control.getStart()-1;
			lo = boilerCrosslRDDao.searchRPDData(thinOilWellRD,start,rowsPerpage, cloumnsName);
		}
		
		if (lo != null && 0 < lo.size()) {
			// 生成树节点json文档
			jsonArr = new JSONArray();
			for (Object[] o : lo) {
				tjo = new JSONObject();
				for (int i = 0; i < cloumnsName.length; i++) {
					if (!"export".equals(totalNumFlag)) {
						if (o[i] == null||o[i].equals("null")) {
							o[i] = "";
						}else if( i < 5){
						
							tjo.put(cloumnsName[i], o[i].toString());
						}else  if (i>5 && i< 58){
							tjo.put(cloumnsName[i], CommonsUtil.getNotTwoData(o[i].toString()));
						}else{
							tjo.put(cloumnsName[i], o[i].toString());
						}
					}else if (o[i] == null||o[i].equals("null")) {
						o[i] = "";
					}else{
						tjo.put(cloumnsName[i].toUpperCase(), o[i].toString());
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
	public boolean removeBoilerCrpssdRPD(String boilerCrossddid)throws Exception{
		PcRpdBoilerSuperheatT prbc = new PcRpdBoilerSuperheatT();
		prbc = boilerCrosslRDDao.searchBoilerCrpssdRPD(boilerCrossddid);
		return boilerCrosslRDDao.removeBoilerCrpssdRPD(prbc);
	}
	@Override
	public List<Object[]> queryOilStation(String blockstationName) {
		// TODO Auto-generated method stub
		String sql = "select s.* from PC_CD_OILDRILLING_STATION_T s  join (select o.pid from pc_cd_org_t o left join PC_CD_STATION_T oil on o.org_id=oil.org_id where oil.blockstation_name='"+blockstationName+"') ss on s.org_id=ss.pid ";
		return boilerCrosslRDDao.searchData(sql);
	}
	@Override
	public PcRpdBoilerSuperheatT searchCross(String cid) {
		// TODO Auto-generated method stub
		return boilerCrosslRDDao.searchCrossRPD(cid);
	}
	@Override
	public String searchBlockName(String boilerName) {
		// TODO Auto-generated method stub
		String sql = "select o.structurename from pc_cd_org_t o where o.org_id in( select pid from pc_cd_org_t t join pc_cd_boiler_t g1 on g1.org_id=t.org_id where t.structurename='"+boilerName+"')";
		List<Object[]> lo = new ArrayList<Object[]>();
		lo = boilerCrosslRDDao.searchData(sql);
		Object blockName = lo.get(0);
		return (String) blockName;
	}
	@Override
	public HashMap<String, Object> searchRPDData1(String block_name,
			String blockstationname, String group, String ghname,
			String boilersName, String acceptunit, String stime, String etime,
			int pageNo, String sort, String order, int rowsPerpage,
			String totalNum) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
