package com.echo.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.beans.PageControl;
import com.echo.dao.U1yqdtDao;
import com.echo.service.U1yqdtService;
import com.echo.util.DateBean;

public class U1yqdtServiceImpl implements U1yqdtService{
  private U1yqdtDao u1yqdtDao;

	public void setU1yqdtDao(U1yqdtDao u1yqdtDao) {
		this.u1yqdtDao = u1yqdtDao;
		
	}
	
	public U1yqdtDao getU1yqdtDao() {
		return u1yqdtDao;
	}
	@Override
	public HashMap<String, Object> serarchU1yqdt(String startDate,String endDate, int pageNo, String sort, String order,int rowsPerpage, String totalNumFlag) throws Exception {


		
		JSONArray jsonArr = null;
		HashMap<String,Object> map = new HashMap<String, Object>();
		JSONObject tjo = null;
		String cloums = "select   ";
		String formTable = " from PC_RD_U1_OIL_AUTO_T a where 1=1 ";
		String totalNum = "select count(*) ";
		String kk="";
		Date today = new Date();
		String nowdate = DateBean.getTimeStr(today);
		
		if("".equals(startDate)){
			startDate = nowdate.substring(0, 10) + " 00:00:00";
		}else{
			startDate = startDate + ":00";
		}
		if("".equals(endDate)){
			endDate = nowdate.substring(0, 10) + " 23:59:59";
		}else{
			endDate = endDate + ":00";
		}
		formTable=formTable+" and CJSJ between TO_DATE('"+startDate+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+endDate+"','YYYY-MM-DD HH24:MI:SS')";
		
		 String[] cloumnsName = {"CJSJ","TR_220A", "TR_220B", "TR_220C","TR_220D","TR_221A","TR_221B","TR_221C","TR_221D","TR_222A",
				 "TR_222B", "TR_222C", "TR_222D","TR_223A","TR_223B", "TR_223C", "TR_223D","TE210A", "TE202A","TE202B","TE202C",
				 "TE202D","TE203A","TE203B", "TE203C","TE203D", "TE204A","TE204B", "TE204C","TE204D","TE205A", "TE205B","TE205C",
				 "TE205D","TE206A", "TE206B", "TE206C", "TE206D", "TE207A","TE207B", "TE207C", "TE207D", "TE208A","TE208B","TE208C",
				 "TE208D","TE209A","TE209B", "TE209C","TE209D", "TT01_001","TT01_002", "TT01_003","TT01_004", "TT02_001","TT02_002","TT02_003",
				 "TT03_001","TT03_002","TT03_003","TR_226", "TR_225","TE201","REG_014", "REG_013","PT201","PT0001","PT_001","PT_002", "PT_003",
				 "MT202","MT201", "MR_210", "LIT408","LIT407", "LT201","LT202", "LT203","LT204","LT205","LT206","LT207","LT208",
				 "LRR_217","LRR_218","LRR_219", "LRR_220","LIT_PZG", "LIT_222", "LIT_221","LIT_002","FITQ001", "FIT202","FIT201","FIT_001", "FIQ202",
				 "FIQ201", "AR_101","AR_102","AR_103","AR_104","PT210SC","TE219RC","FIT210SC","AIT001","AIT002","AIT003","AIT004",
				 "AIT005", "AIT006","AIT007","AIT008","FT001","FT002","FT003","FT005","FT006","FT007","FT008","PT001","PT002",
				 "PT003","PT004","PT005","PT006","PT007","PT008","TE001","TE002","TE003","TE004","TE005","TE006", "TE007","TE008",
				 "PT2HZ","MT2HZ","TE2HZ","FT2HZ","FIQ2HZ"};
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
	
		String product = cloums + kk+formTable;
		//获取总条数
		int total = 0;
		if ("1".equals(totalNum)) {
			total = 1;
		} else {
			totalNum += formTable;
			total = u1yqdtDao.getCounts(totalNum);
		}
		if ("totalNum".equals(totalNumFlag)) {
			map.put("totalNum", total + "");
			return map;
		}
		//排序
		if(!"".equals(sort) && !"CJSJ".equals(sort)){
			for (String cloumn : cloumnsName) {
				if(cloumn.equals(sort)){
					product +=" order by " + cloumn + " " + order;
					break;
				}
			}
		}
		else {
			product +=" order by CJSJ desc";
		}
		//计算分页
		PageControl control = new PageControl();
		//当前页
		List<Object[]> lo = new ArrayList<Object[]>();
		control.setInt_num(rowsPerpage);
		if ("export".equals(totalNumFlag)) {
			lo = u1yqdtDao.searchEverySql(product);
		}
		else {
			control.init(pageNo, total);
			//开始条数
			int start = control.getStart()-1;
			lo = u1yqdtDao.serarchU1yqdt(product,start,rowsPerpage,cloumnsName);
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
//分线计量动态数据
	public HashMap<String, Object> serarchU1fxdt(String startDate,String endDate, int pageNo, String sort, String order,int rowsPerpage, String totalNumFlag) throws Exception {

		JSONArray jsonArr = null;
		HashMap<String,Object> map = new HashMap<String, Object>();
		JSONObject tjo = null;
		String cloums = "select   ";
		String formTable = " from PC_RD_U1_OIL_AUTO_T a where 1=1 ";
		String totalNum = "select count(*) ";
		String kk="";
		Date today = new Date();
		String nowdate = DateBean.getTimeStr(today);
		
		if("".equals(startDate)){
			startDate = nowdate.substring(0, 10) + " 00:00:00";
		}else{
			startDate = startDate + ":00";
		}
		if("".equals(endDate)){
			endDate = nowdate.substring(0, 10) + " 23:59:59";
		}else{
			endDate = endDate + ":00";
		}
		formTable=formTable+" and CJSJ between TO_DATE('"+startDate+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+endDate+"','YYYY-MM-DD HH24:MI:SS')";
		
		 String[] cloumnsName = {"CJSJ","FT001","PT001","TE001","AIT001","FITQ101","FT002","PT002","TE002","AIT002","FITQ102","FT003","PT003","TE003","AIT003","FITQ103",
				 "FT004","PT004","TE004","AIT004","FITQ104","FT005","PT005","TE005","AIT005","FITQ105","FT006","PT006","TE006","AIT006",
				 "FITQ106","FT007","PT007","TE007","AIT007","FITQ107","FT008","PT008","TE008","AIT008","FITQ108","FT2HZ","PT2HZ","TE2HZ","MT2HZ","FIQ2HZ"};
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
	
		String product = cloums + kk+formTable;
		//获取总条数
		int total = 0;
		if ("1".equals(totalNum)) {
			total = 1;
		} else {
			totalNum += formTable;
			total = u1yqdtDao.getCounts(totalNum);
		}
		if ("totalNum".equals(totalNumFlag)) {
			map.put("totalNum", total + "");
			return map;
		}
		//排序
		if(!"".equals(sort) && !"CJSJ".equals(sort)){
			for (String cloumn : cloumnsName) {
				if(cloumn.equals(sort)){
					product +=" order by " + cloumn + " " + order;
					break;
				}
			}
		}
		else {
			product +=" order by CJSJ desc";
		}
		//计算分页
		PageControl control = new PageControl();
		//当前页
		List<Object[]> lo = new ArrayList<Object[]>();
		control.setInt_num(rowsPerpage);
		if ("export".equals(totalNumFlag)) {
			lo = u1yqdtDao.searchEverySql(product);
		}
		else {
			control.init(pageNo, total);
			//开始条数
			int start = control.getStart()-1;
			lo = u1yqdtDao.serarchU1yqdt(product,start,rowsPerpage,cloumnsName);
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
	//密闭转接动态
		public HashMap<String, Object> serarchmbzjdt(String startDate,String endDate, int pageNo, String sort, String order,int rowsPerpage, String totalNumFlag,String name) throws Exception {

			JSONArray jsonArr = null;
			HashMap<String,Object> map = new HashMap<String, Object>();
			JSONObject tjo = null;
			String cloums = "select   ";
			String formTable = " from PC_RD_BSTATION_TURNHOT_T a where ";
			String totalNum = "select count(*) ";
			String kk="";
			Date today = new Date();
			String nowdate = DateBean.getTimeStr(today);
			
			if("".equals(startDate)){
				startDate = nowdate.substring(0, 10) + " 00:00:00";
			}else{
				startDate = startDate + ":00";
			}
			if("".equals(endDate)){
				endDate = nowdate.substring(0, 10) + " 23:59:59";
			}else{
				endDate = endDate + ":00";
			}
			if(name.equals("")||name.equals("全部")){
				formTable=formTable+"1=1";
			}else{
				formTable=formTable+"blockstation = '"+name+"'";
			}
			formTable=formTable+" and ACQUISITION_TIME between TO_DATE('"+startDate+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+endDate+"','YYYY-MM-DD HH24:MI:SS')";
			
			 String[] cloumnsName = {"BLOCKSTATION", "ACQUISITION_TIME","SGG_YW1", "SGG_YW2", "STEAM_YW1", "STEAM_YW2","STEAM_YW3","HCFLQ_YW",
					 "CLQQXCKYL","TLWD","OILPUMP_PL1","OILPUMP_PL2","OILPUMP_PL3","HCFLQ_PUMP_ZT1","HCFLQ_PUMP_ZT2","PUMP_JYGW","PUMP_JYGY","PUMP_CYGY",
					 "TANK200_KZT","GAS_CKF_KZT","TLG_WDF_KZT","KLJ_CKGW1","KLJ_CKGW2","KLJ_CKGW3","KLJ_PL1","KLJ_PL2","KLJ_PL3"};
			for(int m=0;m<cloumnsName.length;m++){
				if(m == cloumnsName.length-1){
					kk=kk+cloumnsName[m];
				}else{
					if("ACQUISITION_TIME".equals(cloumnsName[m])){
						kk=kk+"to_char(ACQUISITION_TIME,'YYYY-MM-DD hh24:mi:ss') as ACQUISITION_TIME,";
					}else{
						kk=kk+cloumnsName[m]+",";
					}
				}
				
			}
			kk.subSequence(0, kk.length()-2);
		
			String product = cloums + kk+formTable;
			//获取总条数
			int total = 0;
			if ("1".equals(totalNum)) {
				total = 1;
			} else {
				totalNum += formTable;
				total = u1yqdtDao.getCounts(totalNum);
			}
			if ("totalNum".equals(totalNumFlag)) {
				map.put("totalNum", total + "");
				return map;
			}
			//排序
			if(!"".equals(sort) && !"ACQUISITION_TIME".equals(sort)){
				for (String cloumn : cloumnsName) {
					if(cloumn.equals(sort)){
						product +=" order by " + cloumn + " " + order;
						break;
					}
				}
			}
			else {
				product +=" order by ACQUISITION_TIME desc";
			}
			//计算分页
			PageControl control = new PageControl();
			//当前页
			List<Object[]> lo = new ArrayList<Object[]>();
			control.setInt_num(rowsPerpage);
			if ("export".equals(totalNumFlag)) {
				lo = u1yqdtDao.searchEverySql(product);
			}
			else {
				control.init(pageNo, total);
				//开始条数
				int start = control.getStart()-1;
				lo = u1yqdtDao.serarchU1yqdt(product,start,rowsPerpage,cloumnsName);
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
	public HashMap<String, Object> serarchU1sqdt(String startDate,String endDate, int pageNo, String sort, String order,int rowsPerpage, String totalNumFlag) throws Exception {

		
		JSONArray jsonArr = null;
		HashMap<String,Object> map = new HashMap<String, Object>();
		JSONObject tjo = null;
		String cloums = "select   ";
		String formTable = " from PC_RD_U1_WATER_AUTO_T a where 1=1 ";
		String totalNum = "select count(*) ";
		String kk="";
		Date today = new Date();
		String nowdate = DateBean.getTimeStr(today);
		
		if("".equals(startDate)){
			startDate = nowdate.substring(0, 10) + " 00:00:00";
		}else{
			startDate = startDate + ":00";
		}
		if("".equals(endDate)){
			endDate = nowdate.substring(0, 10) + " 23:59:59";
		}else{
			endDate = endDate + ":00";
		}
		formTable=formTable+" and CJSJ between TO_DATE('"+startDate+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+endDate+"','YYYY-MM-DD HH24:MI:SS')";
		
		String[] cloumnsName = { "CJSJ","W5",                  
		  "W4",                  
		  "W3",                  
		  "W2",                  
		  "W1",                 
		  "SYJS_03",            
		  "SYJS_02",             
		  "SYJS_01",             
		  "ST1004",             
		  "ST1003",              
		  "ST1002",              
		  "ST1001",              
		  "SRJS_03",             
		  "SRJS_02",             
		  "SRJS_01",             
		  "QFH_01",              
		  "Q5",                  
		  "Q4",                  
		  "Q3",                  
		  "Q2",                  
		  "Q1",                  
		  "PPM4_3",              
		  "PPM4_2",              
		  "PPM_34",              
		 "PPM3_3",              
		  "PPM3_2",              
		  "PPM2_3",              
		  "PPM22_2",             
		  "PPM1_3",             
		  "PPM1_2",              
		  "PPM111",              
		 "PIC603E_MV",          
		  "PIC603D_MV",          
		  "PIC603C_MV",          
		  "PIC603B_MV",          
		  "PIC603A_MV",          
		  "P603E_AI",            
		  "P603D_AI",            
		  "P603C_AI",            
		  "P603B_AI",           
		  "P603A_AI",            
		  "P602D_AI",            
		  "P602C_AI",            
		  "P602B_AI",            
		 "P602A_AI",            
		  "P601B_AI",            
		  "P601A_AI",            
		  "N55",                 
		  "N5",                  
		  "N44",                 
		  "N4",                  
		  "N33",                 
		  "N3",                  
		  "N22",                 
		  "N2",                  
		  "N11",                 
		  "N1",                  
		  "LT604",               
		  "LT603",               
		  "LT404",               
		  "LT403",               
		  "LT402",               
		  "LT401",               
		  "LT201",               
		  "LT11016",             
		  "LT11015",             
		  "LT11014",             
		  "LT11013",             
		  "LT1012",              
		  "LT1011",              
		  "LT1010",              
		  "LT1009",              
		  "LT1008",              
		  "LT1007",              
		  "LT1006",              
		  "LT1005",              
		  "LT1004",              
		  "LT1003",              
		  "LT1002",              
		  "LT1001",              
		  "LIT_606C",            
		  "LIT_606B",            
		  "LIT_606A",            
		  "LIT_605",             
		  "LIT_604B",            
		  "LIT_604A",            
		  "LIT_603",             
		  "LIT_602B",            
		  "LIT_602A",            
		  "LIT_601B",            
		  "LIT_601A",            
		  "K_1",                 
		  "FT1016",              
		  "FT1015",              
		  "FT1014",              
		  "FT1013",              
		  "FT1012",              
		  "FT1011",              
		  "FT1010",             
		  "FT1009",              
		  "FT1008",              
		  "FT1007",              
		  "FT1006",              
		  "FT1005",              
		  "FT1004",             
		  "FT1003",              
		 "FT1002",              
		  "FT1001",              
		  "FT03",                
		  "FT02",                
		  "FT01",               
		  "FT_02",               
		  "FT_01",              
		  "FITQ203",           
		 "FITQ202",            
		  "FITQ201",            
		  "FIT801",              
		  "FIT_1234",            
		  "FIT_606",             
		  "FIT_605",             
		  "FIT_604",             
		  "FIT_603",             
		  "FIT_602",             
		  "FIT_601E",            
		  "FIT_601D",            
		  "FIT_601C",            
		  "FIT_601B",            
		  "FIT_601A",            
		  "FIT_317",             
		  "FIQ_601E",            
		  "FIQ_601D",          
		  "FIQ_601C",           
		  "FIQ_601B",           
		  "FIQ_601A",           
		  "FIQ_317",            
		  "BP06_MV",             
		  "BP05_MV",             
		  "BP04_MV",             
		  "BP03_MV",             
		  "BP02_MV",             
		  "BP01_MV",             
		  "BP006PL",             
		  "BP005PL",             
		  "BP004PL",             
		  "BP003PL",             
		  "BP002PL",             
		  "BP001PL"};
		
		
		
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
	
		String product = cloums + kk+formTable;
		//获取总条数
		int total = 0;
		if ("1".equals(totalNum)) {
			total = 1;
		} else {
			totalNum += formTable;
			total = u1yqdtDao.getCounts(totalNum);
		}
		if ("totalNum".equals(totalNumFlag)) {
			map.put("totalNum", total + "");
			return map;
		}
		//排序
		if(!"".equals(sort) && !"CJSJ".equals(sort)){
			for (String cloumn : cloumnsName) {
				if(cloumn.equals(sort)){
					product +=" order by " + cloumn + " " + order;
					break;
				}
			}
		}
		else {
			product +=" order by CJSJ desc";
		}
		//计算分页
		PageControl control = new PageControl();
		//当前页
		List<Object[]> lo = new ArrayList<Object[]>();
		control.setInt_num(rowsPerpage);
		if ("export".equals(totalNumFlag)) {
			lo = u1yqdtDao.searchEverySql(product);
		}
		else {
			control.init(pageNo, total);
			//开始条数
			int start = control.getStart()-1;
			lo = u1yqdtDao.serarchU1sqdt(product,start,rowsPerpage,cloumnsName);
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