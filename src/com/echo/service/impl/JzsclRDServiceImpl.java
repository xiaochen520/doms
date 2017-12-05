package com.echo.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.beans.PageControl;
import com.echo.dao.CommonDao;
import com.echo.service.JzsclRDService;
import com.echo.util.CommonsUtil;
import com.echo.util.DateBean;

public class JzsclRDServiceImpl implements JzsclRDService{
	private CommonDao commonDao;
	
	

	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}



	public HashMap<String,Object> searchCyqData(String sclz,String sbbh,String stime,String etime,int pageNo,String sort,String order,int rowsPerpage,String totalNumFlag)throws Exception {
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
		String formTable = " from PC_RD_SCL_CYQ_T  where 1=1  and cjsj between TO_DATE('"+stime+"','YYYY-MM-DD hh24:mi:ss') and TO_DATE('"+etime+"','YYYY-MM-DD hh24:mi:ss') ";
		String totalNum = "select count(*) ";
		if(!sclz.equals("")&&sclz!=null&&!sclz.equals("undefined")&!sclz.equals("全部")){
			formTable=formTable+" and SCLZMC='"+sclz+"'";
		}
		if(!sbbh.equals("")&&sbbh!=null&&!sbbh.equals("undefined")){
			formTable=formTable+" and CYQBH='"+sbbh+"'";
		}

		
		String[] cloumnsName = {"CJSJ","SCLZMC","CYQBH","ZKD","CYQ_CKYL","CYQ_DCLL1","CYQ_DCLL2"};
		String kk="";
		for(int m=0;m<cloumnsName.length;m++){
			if(m == cloumnsName.length-1){
				kk=kk+cloumnsName[m];
			}else{
				if("CJSJ".equals(cloumnsName[m])){
					kk=kk+"to_char(CJSJ,'YYYY-MM-DD hh24:mi:ss') as CJSJ,";
				}else{
					kk=kk+cloumnsName[m]+",";
				}
			}
			
		}
		kk.subSequence(0, kk.length()-2);
		
		String thinOilWellRD = cloums + kk+formTable;
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
		if(!"".equals(sort) && !"CJSJ".equals(sort)){
			for (String cloumn : cloumnsName) {
				if(cloumn.equals(sort)){
					thinOilWellRD +=" order by " + cloumn + " " + order;
					break;
				}
				
			}
		}
		else {
			thinOilWellRD +=" order by SCLZMC,CYQBH,CJSJ desc";
		}
		//计算分页
		PageControl control = new PageControl();
		//当前页
		List<Object[]> lo = new ArrayList<Object[]>();
		control.setInt_num(rowsPerpage);
		if ("export".equals(totalNumFlag)) {
			lo = commonDao.searchEverySql(thinOilWellRD);
		}
		else {
			control.init(pageNo, total);
			//开始条数
			int start = control.getStart()-1;
			lo = commonDao.searchPaging(thinOilWellRD,start,rowsPerpage,cloumnsName);
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
	
	
	public HashMap<String,Object> searchTytData(String sclz,String sbbh,String stime,String etime,int pageNo,String sort,String order,int rowsPerpage,String totalNumFlag)throws Exception {
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
		String formTable = " from PC_RD_SCL_CYT_T  where 1=1  and cjsj between TO_DATE('"+stime+"','YYYY-MM-DD hh24:mi:ss') and TO_DATE('"+etime+"','YYYY-MM-DD hh24:mi:ss') ";
		String totalNum = "select count(*) ";
		if(!sclz.equals("")&&sclz!=null&&!sclz.equals("undefined")&!sclz.equals("全部")){
			formTable=formTable+" and SCLZMC='"+sclz+"水处理站'";
		}
		if(!sbbh.equals("")&&sbbh!=null&&!sbbh.equals("undefined")){
			formTable=formTable+" and TYTBH='"+sbbh+"'";
		}

		
		String[] cloumnsName = {"CJSJ","SCLZMC","TYTBH","W1LT","W1YO","W1FT","XH_PUMP_P","GS_PUMP_P","ZY_PUMP_P","ZY_PUMP_FREQ"};
		String kk="";
		for(int m=0;m<cloumnsName.length;m++){
			if(m == cloumnsName.length-1){
				kk=kk+cloumnsName[m];
			}else{
				if("CJSJ".equals(cloumnsName[m])){
					kk=kk+"to_char(CJSJ,'YYYY-MM-DD hh24:mi:ss') as CJSJ,";
				}else{
					kk=kk+cloumnsName[m]+",";
				}
			}
			
		}
		kk.subSequence(0, kk.length()-2);
		
		String thinOilWellRD = cloums + kk+formTable;
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
		if(!"".equals(sort) && !"CJSJ".equals(sort)){
			for (String cloumn : cloumnsName) {
				if(cloumn.equals(sort)){
					thinOilWellRD +=" order by " + cloumn + " " + order;
					break;
				}
				
			}
		}
		else {
			thinOilWellRD +=" order by SCLZMC,TYTBH,CJSJ desc";
		}
		//计算分页
		PageControl control = new PageControl();
		//当前页
		List<Object[]> lo = new ArrayList<Object[]>();
		control.setInt_num(rowsPerpage);
		if ("export".equals(totalNumFlag)) {
			lo = commonDao.searchEverySql(thinOilWellRD);
		}
		else {
			control.init(pageNo, total);
			//开始条数
			int start = control.getStart()-1;
			lo = commonDao.searchPaging(thinOilWellRD,start,rowsPerpage,cloumnsName);
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
	
	
	public HashMap<String,Object> searchRhqData(String sclz,String sbbh,String stime,String etime,int pageNo,String sort,String order,int rowsPerpage,String totalNumFlag)throws Exception {
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
		String formTable = " from PC_RD_SCL_RHQ_T  where 1=1  and cjsj between TO_DATE('"+stime+"','YYYY-MM-DD hh24:mi:ss') and TO_DATE('"+etime+"','YYYY-MM-DD hh24:mi:ss') ";
		String totalNum = "select count(*) ";
		if(!sclz.equals("")&&sclz!=null&&!sclz.equals("undefined")&!sclz.equals("全部")){
			formTable=formTable+" and SCLZMC='"+sclz+"'";
		}
		if(!sbbh.equals("")&&sbbh!=null&&!sbbh.equals("undefined")){
			formTable=formTable+" and RHQBH='"+sbbh+"'";
		}

		
		String[] cloumnsName = {"CJSJ","SCLZMC","RHQBH","SCL_ALL","SCL_ALLLJ","SCL_BLL","SCL_BLLLJ","SCL_CLL","SCL_CLLLJ","SCL_DLL","SCL_DLLLJ"};
		String kk="";
		for(int m=0;m<cloumnsName.length;m++){
			if(m == cloumnsName.length-1){
				kk=kk+cloumnsName[m];
			}else{
				if("CJSJ".equals(cloumnsName[m])){
					kk=kk+"to_char(CJSJ,'YYYY-MM-DD hh24:mi:ss') as CJSJ,";
				}else{
					kk=kk+cloumnsName[m]+",";
				}
			}
			
		}
		kk.subSequence(0, kk.length()-2);
		
		String thinOilWellRD = cloums + kk+formTable;
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
		if(!"".equals(sort) && !"CJSJ".equals(sort)){
			for (String cloumn : cloumnsName) {
				if(cloumn.equals(sort)){
					thinOilWellRD +=" order by " + cloumn + " " + order;
					break;
				}
				
			}
		}
		else {
			thinOilWellRD +=" order by SCLZMC,RHQBH,CJSJ desc";
		}
		//计算分页
		PageControl control = new PageControl();
		//当前页
		List<Object[]> lo = new ArrayList<Object[]>();
		control.setInt_num(rowsPerpage);
		if ("export".equals(totalNumFlag)) {
			lo = commonDao.searchEverySql(thinOilWellRD);
		}
		else {
			control.init(pageNo, total);
			//开始条数
			int start = control.getStart()-1;
			lo = commonDao.searchPaging(thinOilWellRD,start,rowsPerpage,cloumnsName);
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
	
	
	public HashMap<String,Object> searchJzsclData(String stime,String etime,int pageNo,String sort,String order,int rowsPerpage,String totalNumFlag)throws Exception {
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
		String formTable = " from PC_RD_JZSCL_T  where 1=1  and cjsj between TO_DATE('"+stime+"','YYYY-MM-DD hh24:mi:ss') and TO_DATE('"+etime+"','YYYY-MM-DD hh24:mi:ss') ";
		String totalNum = "select count(*) ";

		
		String[] cloumnsName = {"CJSJ","RHQZCSL","CYTZCSL","LT401","LT402","LT102","LT101","LT403","ZYBYL1","ZYBYL2","ZYBYL3","PI_201"};
		String kk="";
		for(int m=0;m<cloumnsName.length;m++){
			if(m == cloumnsName.length-1){
				kk=kk+cloumnsName[m];
			}else{
				if("CJSJ".equals(cloumnsName[m])){
					kk=kk+"to_char(CJSJ,'YYYY-MM-DD hh24:mi:ss') as CJSJ,";
				}else{
					kk=kk+cloumnsName[m]+",";
				}
			}
			
		}
		kk.subSequence(0, kk.length()-2);
		
		String thinOilWellRD = cloums + kk+formTable;
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
		if(!"".equals(sort) && !"CJSJ".equals(sort)){
			for (String cloumn : cloumnsName) {
				if(cloumn.equals(sort)){
					thinOilWellRD +=" order by " + cloumn + " " + order;
					break;
				}
				
			}
		}
		else {
			thinOilWellRD +=" order by CJSJ desc";
		}
		//计算分页
		PageControl control = new PageControl();
		//当前页
		List<Object[]> lo = new ArrayList<Object[]>();
		control.setInt_num(rowsPerpage);
		if ("export".equals(totalNumFlag)) {
			lo = commonDao.searchEverySql(thinOilWellRD);
		}
		else {
			control.init(pageNo, total);
			//开始条数
			int start = control.getStart()-1;
			lo = commonDao.searchPaging(thinOilWellRD,start,rowsPerpage,cloumnsName);
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
	
	public HashMap<String,Object> searchT2sclData(String stime,String etime,int pageNo,String sort,String order,int rowsPerpage,String totalNumFlag)throws Exception {
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
		String formTable = " from PC_RD_RHSCL_T2_T  where 1=1  and cjsj between TO_DATE('"+stime+"','YYYY-MM-DD hh24:mi:ss') and TO_DATE('"+etime+"','YYYY-MM-DD hh24:mi:ss') ";
		String totalNum = "select count(*) ";
		
		
		String[] cloumnsName = {"CJSJ","RHQZCSL","CYQZCSL","ZYBZCSL","LT_30101A","LT_30101B","LT_30102A","LT_30102B","LT_30301","LT_30302","ZYBYL1","ZYBYL2","YSKQYL1","YSKQYL2"};
		String kk="";
		for(int m=0;m<cloumnsName.length;m++){
			if(m == cloumnsName.length-1){
				kk=kk+cloumnsName[m];
			}else{
				if("CJSJ".equals(cloumnsName[m])){
					kk=kk+"to_char(CJSJ,'YYYY-MM-DD hh24:mi:ss') as CJSJ,";
				}else{
					kk=kk+cloumnsName[m]+",";
				}
			}
			
		}
		kk.subSequence(0, kk.length()-2);
		
		String thinOilWellRD = cloums + kk+formTable;
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
		if(!"".equals(sort) && !"CJSJ".equals(sort)){
			for (String cloumn : cloumnsName) {
				if(cloumn.equals(sort)){
					thinOilWellRD +=" order by " + cloumn + " " + order;
					break;
				}
				
			}
		}
		else {
			thinOilWellRD +=" order by CJSJ desc";
		}
		//计算分页
		PageControl control = new PageControl();
		//当前页
		List<Object[]> lo = new ArrayList<Object[]>();
		control.setInt_num(rowsPerpage);
		if ("export".equals(totalNumFlag)) {
			lo = commonDao.searchEverySql(thinOilWellRD);
		}
		else {
			control.init(pageNo, total);
			//开始条数
			int start = control.getStart()-1;
			lo = commonDao.searchPaging(thinOilWellRD,start,rowsPerpage,cloumnsName);
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
	
	
	public HashMap<String,Object> searchShysclData(String stime,String etime,int pageNo,String sort,String order,int rowsPerpage,String totalNumFlag)throws Exception {
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
		String formTable = " from PC_RD_SHYSCL_T  where 1=1  and cjsj between TO_DATE('"+stime+"','YYYY-MM-DD hh24:mi:ss') and TO_DATE('"+etime+"','YYYY-MM-DD hh24:mi:ss') ";
		String totalNum = "select count(*) ";
		
		
		String[] cloumnsName = {"CJSJ","RHQZCSL","CYQZCSL","ZYBZCSL","LT_30101A","LT_30101B","LT_30102A","LT_30102B","LT_30301","LT_30302","ZYBYL1","ZYBYL2","ZYBYL3","YSKQYL1","YSKQYL2","YSKQYL3"};
		String kk="";
		for(int m=0;m<cloumnsName.length;m++){
			if(m == cloumnsName.length-1){
				kk=kk+cloumnsName[m];
			}else{
				if("CJSJ".equals(cloumnsName[m])){
					kk=kk+"to_char(CJSJ,'YYYY-MM-DD hh24:mi:ss') as CJSJ,";
				}else{
					kk=kk+cloumnsName[m]+",";
				}
			}
			
		}
		kk.subSequence(0, kk.length()-2);
		
		String thinOilWellRD = cloums + kk+formTable;
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
		if(!"".equals(sort) && !"CJSJ".equals(sort)){
			for (String cloumn : cloumnsName) {
				if(cloumn.equals(sort)){
					thinOilWellRD +=" order by " + cloumn + " " + order;
					break;
				}
				
			}
		}
		else {
			thinOilWellRD +=" order by CJSJ desc";
		}
		//计算分页
		PageControl control = new PageControl();
		//当前页
		List<Object[]> lo = new ArrayList<Object[]>();
		control.setInt_num(rowsPerpage);
		if ("export".equals(totalNumFlag)) {
			lo = commonDao.searchEverySql(thinOilWellRD);
		}
		else {
			control.init(pageNo, total);
			//开始条数
			int start = control.getStart()-1;
			lo = commonDao.searchPaging(thinOilWellRD,start,rowsPerpage,cloumnsName);
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



	public HashMap<String,Object> searchFcrdDatas(String oilstationname,String blockstationname,String cliquepool,String cliquetype,String stime,String etime,int pageNo,String sort,String order,int rowsPerpage,String totalNumFlag)throws Exception {
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
		String formTable = " from pc_rd_clique_pool_v  where 1=1  and cjsj between TO_DATE('"+stime+"','YYYY-MM-DD hh24:mi:ss') and TO_DATE('"+etime+"','YYYY-MM-DD hh24:mi:ss') ";
		
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
		
		
		String totalNum = "select count(*) ";
		
		
		String[] cloumnsName = {"CLIQUE_POOL_NAME","CJSJ","STATION_NAME","OIL_STATION_NAME","CLIQUE_TYPE","PRESS"};
		String kk="";
		for(int m=0;m<cloumnsName.length;m++){
			if(m == cloumnsName.length-1){
				kk=kk+cloumnsName[m];
			}else{
				if("CJSJ".equals(cloumnsName[m])){
					kk=kk+"to_char(CJSJ,'YYYY-MM-DD hh24:mi:ss') as CJSJ,";
				}else{
					kk=kk+cloumnsName[m]+",";
				}
			}
			
		}
		kk.subSequence(0, kk.length()-2);
		
		String thinOilWellRD = cloums + kk+formTable;
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
		if(!"".equals(sort) && !"CJSJ".equals(sort)){
			for (String cloumn : cloumnsName) {
				if(cloumn.equals(sort)){
					thinOilWellRD +=" order by " + cloumn + " " + order;
					break;
				}
				
			}
		}
		else {
			thinOilWellRD +=" order by CLIQUE_POOL_NAME,CJSJ desc";
		}
		//计算分页
		PageControl control = new PageControl();
		//当前页
		List<Object[]> lo = new ArrayList<Object[]>();
		control.setInt_num(rowsPerpage);
		if ("export".equals(totalNumFlag)) {
			lo = commonDao.searchEverySql(thinOilWellRD);
		}
		else {
			control.init(pageNo, total);
			//开始条数
			int start = control.getStart()-1;
			lo = commonDao.searchPaging(thinOilWellRD,start,rowsPerpage,cloumnsName);
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
	
	
	public HashMap<String,Object> searchGwghDatas(String oilstationname,String blockstationname,String cliquepool,String stime,String etime,int pageNo,String sort,String order,int rowsPerpage,String totalNumFlag)throws Exception {
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
		String formTable = " from pc_rd_network_md_v  where 1=1  and cjsj between TO_DATE('"+stime+"','YYYY-MM-DD hh24:mi:ss') and TO_DATE('"+etime+"','YYYY-MM-DD hh24:mi:ss') ";
		
		if(!oilstationname.equals("")&&oilstationname!=null&&!oilstationname.equals("undefined")&!oilstationname.equals("全部")){
			formTable=formTable+" and OIL_STATION_NAME='"+oilstationname+"'";
		}
		if(blockstationname!=null&&!blockstationname.equals("")&&!blockstationname.equals("undefined")){
			formTable=formTable+" and STATION_NAME='"+blockstationname+"'";
		}
		if(cliquepool!=null&&!cliquepool.equals("")&&!cliquepool.equals("undefined")){
			formTable=formTable+" and NETWORK_MD_NAME  like '%"+cliquepool+"%'";
		}
		
		
		
		String totalNum = "select count(*) ";
		
		
		String[] cloumnsName = {"NETWORK_MD_NAME","CJSJ","STATION_NAME","OIL_STATION_NAME","PRESS","TEMP"};
		String kk="";
		for(int m=0;m<cloumnsName.length;m++){
			if(m == cloumnsName.length-1){
				kk=kk+cloumnsName[m];
			}else{
				if("CJSJ".equals(cloumnsName[m])){
					kk=kk+"to_char(CJSJ,'YYYY-MM-DD hh24:mi:ss') as CJSJ,";
				}else{
					kk=kk+cloumnsName[m]+",";
				}
			}
			
		}
		kk.subSequence(0, kk.length()-2);
		
		String thinOilWellRD = cloums + kk+formTable;
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
		if(!"".equals(sort) && !"CJSJ".equals(sort)){
			for (String cloumn : cloumnsName) {
				if(cloumn.equals(sort)){
					thinOilWellRD +=" order by " + cloumn + " " + order;
					break;
				}
				
			}
		}
		else {
			thinOilWellRD +=" order by NETWORK_MD_NAME,CJSJ desc";
		}
		//计算分页
		PageControl control = new PageControl();
		//当前页
		List<Object[]> lo = new ArrayList<Object[]>();
		control.setInt_num(rowsPerpage);
		if ("export".equals(totalNumFlag)) {
			lo = commonDao.searchEverySql(thinOilWellRD);
		}
		else {
			control.init(pageNo, total);
			//开始条数
			int start = control.getStart()-1;
			lo = commonDao.searchPaging(thinOilWellRD,start,rowsPerpage,cloumnsName);
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
}
