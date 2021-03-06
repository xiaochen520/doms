package com.echo.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.dao.CCSCLDao;
import com.echo.service.CCSCLService;
import com.echo.util.CommonsUtil;
import com.echo.util.DateBean;
import com.echo.util.PropertiesConfig;

public class CCSCLServiceImpl implements CCSCLService{
	private PropertiesConfig propert = new PropertiesConfig();
	private CCSCLDao cCSCLDao;
	
	public void setcCSCLDao(CCSCLDao cCSCLDao) {
		this.cCSCLDao = cCSCLDao;
	}

//	public ArrayList<CCSCL> searchCCSCL(String reportDate,String totalNumf) throws Exception {
//		String cloums = "select ";
//		String cl = "";
//		String formTable = " from PC_RPD_U2_WATER_V a where 1=1 ";
////		String totalNum = "select count(*) ";
//		HashMap<String,Object> map = new HashMap<String, Object>();
//		
//		Date today = new Date();
//		String nowdate = DateBean.getTimeStr(today);
//		if("".equals(reportDate)){
//			reportDate = nowdate.substring(0, 10) + " 00:00:00";
//		}else{
//			reportDate = reportDate + " 00:00:00";
//		}
//reportDate	= "2014-04-17 00:00:00"; 	
//		formTable=formTable+
//		 " and to_date(to_char(a.report_time,'yyyy-mm-dd hh24:mi:ss'),'yyyy-mm-dd hh24:mi:ss')>= to_date(substr(to_char(to_date(to_char(to_date('"+reportDate+"','yyyy-mm-dd hh24:mi:ss'),'yyyy-mm-dd hh24:mi:ss'),'yyyy-mm-dd hh24:mi:ss')-1,'yyyy-mm-dd hh24:mi:ss'),0,10) || to_char(24+a.parameter_value,'00') || ':00:00','yyyy-mm-dd hh24:mi:ss')"+
//	      "  and to_date(to_char(a.report_time,'yyyy-mm-dd hh24:mi:ss'),'yyyy-mm-dd hh24:mi:ss')<= to_date(substr(to_char(to_date(to_char(to_date('"+reportDate+"','yyyy-mm-dd hh24:mi:ss'),'yyyy-mm-dd hh24:mi:ss'),'yyyy-mm-dd hh24:mi:ss'),'yyyy-mm-dd hh24:mi:ss'),0,10) || to_char(24+a.parameter_value,'00') || ':00:00','yyyy-mm-dd hh24:mi:ss')";
//		String[] cloumnsName = {"FIT_20201A","FIT_20201B","FIT20201AZ","FIT_20202","FIT20202Z","LIT_20201A","LIT_20201B","LIT_20401A","LIT_20401B","FIT_20401","FIT20401Z","JINGHAUGUAN","ACQUISITION_TIME","REPORT_TIME","TBR","SHR","SHSJ","BZ","parameter_value"};
//		for (String cn : cloumnsName) {
//			if ("export".equals(totalNumf) && "SAGDDID".equals(cn)) {
//				break;
//			}
//			if (!"REPORT_TIME".equals(cn) ) {
//				cl += "," + cn;	
//				continue;
//			}else{
//				cl += ",substr(to_char(REPORT_TIME,'yyyy-mm-dd hh24:mi:ss'),12,5) as report_time";
//			}
//		}
//		cloums += cl.substring(1);
//		String wellInfo = cloums + formTable;
//		//获取总条数
////		int total = 0;
////		if ("1".equals(totalNum)) {
////			total = 1;
////		} else {
////			totalNum += formTable;
////			total = cCSCLDao.getCounts(totalNum);
////		}
////		if ("totalNum".equals(totalNumf)) {
////			map.put("totalNum", total + "");
////			return map;
////		}
//		//排序
//		wellInfo +=" order by ACQUISITION_TIME";
//		
//		List<Object[]> lo =null;
//		try {
//			if ("export".equals(totalNumf)) {
//				lo = cCSCLDao.queryInfo(wellInfo);
//				//map.put("list", lo);
//				//return ccsclList;
//			}
//			else {
//				lo = cCSCLDao.searchCCSCLRPD(wellInfo,cloumnsName);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		String start = "";
//		int d1 = 0,d2 = 0,d3 = 0;
//		String str = propert.getSystemConfiguration("time");
//		String[] ary1 = str.split("&");
//		d1 = ary1[0].split(",").length;
//		d2 = ary1[1].split(",").length;
//		d3 = ary1[2].split(",").length;
//		
//		CCSCL ccscl = null;
//		ArrayList<CCSCL> ccsclList = null ;
//		if (lo != null && 0 < lo.size()) {
//			// 生成树节点json文档
//			ccsclList = new ArrayList<CCSCL>();
//			int i=0;
//			for (Object[] o : lo) {
//				ccscl = new CCSCL();
//				String s = "";
//				if(24+Integer.parseInt(o[18].toString())+i*2<24){
//					s = String.format("%02d", 24+Integer.parseInt(o[18].toString())+i*2)+":00";
//				}else{
//					s = String.format("%02d", 24+Integer.parseInt(o[18].toString())+i*2-24)+":00";
//				}
//				
//				if(!s.equals(o[13].toString())){
//					ccscl.setFIT_20201A("");
//					ccscl.setFIT_20201B("");
//					ccscl.setFIT20201AZ("");
//					ccscl.setFIT_20202("");
//					ccscl.setLIT_20201A("");
//					ccscl.setLIT_20201B("");
//					ccscl.setLIT_20401A("");
//					ccscl.setLIT_20401B("");
//					ccscl.setFIT_20401("");
//					ccscl.setFIT20401Z("");
//					ccscl.setJINGHUAGUAN("");
//					ccscl.setACQUISITION_TIME("");
//					ccscl.setREPORT_TIME(s);
//					ccscl.setTBR("");
//					ccscl.setSHR("");
//					ccscl.setSHSJ("");
//					ccscl.setBZ("");
//				}else{
//					if(o[0]!=null)
//						ccscl.setFIT_20201A(o[0].toString());
//					if(o[1]!=null)
//						ccscl.setFIT_20201B(o[1].toString());
//					if(o[2]!=null)
//						ccscl.setFIT20201AZ(o[2].toString());
//					if(o[3]!=null)
//						ccscl.setFIT_20202(o[3].toString());
//					if(o[4]!=null)
//						ccscl.setFIT20202Z(o[4].toString());
//					if(o[5]!=null)
//						ccscl.setLIT_20201A(o[5].toString());
//					if(o[6]!=null)
//						ccscl.setLIT_20201B(o[6].toString());
//					if(o[7]!=null)
//						ccscl.setLIT_20401A(o[7].toString());
//					if(o[8]!=null)
//						ccscl.setLIT_20401B(o[8].toString());
//					if(o[9]!=null)
//						ccscl.setFIT_20401(o[9].toString());
//					if(o[10]!=null)
//						ccscl.setFIT20401Z(o[10].toString());
//					if(o[11]!=null)
//						ccscl.setJINGHUAGUAN(o[11].toString());
//					if(o[12]!=null)
//						ccscl.setACQUISITION_TIME(o[12].toString());
//					if(o[13]!=null)
//						ccscl.setREPORT_TIME(o[13].toString());
//					if(o[14]!=null)
//						ccscl.setTBR(o[14].toString());
//					if(o[15]!=null)
//						ccscl.setSHR(o[15].toString());
//					if(o[16]!=null)
//						ccscl.setSHSJ(o[16].toString());
//					if(o[17]!=null)
//						ccscl.setBZ(o[17].toString());
//				}
//				ccsclList.add(ccscl);
//				i++;
//			}
//			double FIT_20201A = 0;
//			double FIT_20201B = 0;
//			double FIT20201AZ = 0;
//			double FIT_20202 = 0;
//			double FIT20202Z = 0;
//			double LIT_20201A = 0;
//			double LIT_20201B = 0;
//			double LIT_20401A = 0;
//			double LIT_20401B = 0;
//			double FIT_20401 = 0;
//			double FIT20401Z = 0;
//			double JINGHUAGUAN = 0;
//			for(int n1=0;n1<d1;n1++){
//				if(ccsclList.get(n1).getFIT_20201A()!=null)
//					FIT_20201A += Double.parseDouble(ccsclList.get(n1).getFIT_20201A());
//				if(ccsclList.get(n1).getFIT_20201B()!=null)
//					FIT_20201B += Double.parseDouble(ccsclList.get(n1).getFIT_20201B());
//				if(ccsclList.get(n1).getFIT20201AZ()!=null)
//					FIT20201AZ += Double.parseDouble(ccsclList.get(n1).getFIT20201AZ());
//				if(ccsclList.get(n1).getFIT_20202()!=null)
//					FIT_20202 += Double.parseDouble(ccsclList.get(n1).getFIT_20202());
//				if(ccsclList.get(n1).getFIT20202Z()!=null)
//					FIT20202Z += Double.parseDouble(ccsclList.get(n1).getFIT20202Z());
//				if(ccsclList.get(n1).getLIT_20201A()!=null)
//					LIT_20201A += Double.parseDouble(ccsclList.get(n1).getLIT_20201A());
//				if(ccsclList.get(n1).getLIT_20201B()!=null)
//					LIT_20201B += Double.parseDouble(ccsclList.get(n1).getLIT_20201B());
//				if(ccsclList.get(n1).getLIT_20401A()!=null)
//					LIT_20401A += Double.parseDouble(ccsclList.get(n1).getLIT_20401A());
//				if(ccsclList.get(n1).getLIT_20401B()!=null)
//					LIT_20401B += Double.parseDouble(ccsclList.get(n1).getLIT_20401B());
//				if(ccsclList.get(n1).getFIT_20401()!=null)
//					FIT_20401 += Double.parseDouble(ccsclList.get(n1).getFIT_20401());
//				if(ccsclList.get(n1).getFIT20401Z()!=null)
//					FIT20401Z += Double.parseDouble(ccsclList.get(n1).getFIT20401Z());
//				if(ccsclList.get(n1).getJINGHUAGUAN()!=null)
//					JINGHUAGUAN += Double.parseDouble(ccsclList.get(n1).getJINGHUAGUAN());
//			}
//			CCSCL sumCcscl = new CCSCL();
//			sumCcscl.setREPORT_TIME("班累积");
//			sumCcscl.setFIT_20201A(String.valueOf(String.format("%.2f",FIT_20201A)));
//			sumCcscl.setFIT_20201B(String.valueOf(String.format("%.2f",FIT_20201B)));
//			sumCcscl.setFIT20201AZ(String.valueOf(String.format("%.2f",FIT20201AZ)));
//			sumCcscl.setFIT_20202(String.valueOf(String.format("%.2f",FIT_20202)));
//			sumCcscl.setFIT20202Z(String.valueOf(String.format("%.2f",FIT20202Z)));
//			sumCcscl.setLIT_20201A(String.valueOf(String.format("%.2f",LIT_20201A)));
//			sumCcscl.setLIT_20201B(String.valueOf(String.format("%.2f",LIT_20201B)));
//			sumCcscl.setLIT_20401A(String.valueOf(String.format("%.2f",LIT_20401A)));
//			sumCcscl.setLIT_20401B(String.valueOf(String.format("%.2f",LIT_20401B)));
//			sumCcscl.setFIT_20401(String.valueOf(String.format("%.2f",FIT_20401)));
//			sumCcscl.setFIT20401Z(String.valueOf(String.format("%.2f",FIT20401Z)));
//			sumCcscl.setJINGHUAGUAN(String.valueOf(String.format("%.2f",JINGHUAGUAN)));
//			ccsclList.add(d1,sumCcscl);
//			for(int n2=d1;n2<d1+d2;n2++){
//				if(ccsclList.get(n2).getFIT_20201A()!=null)
//					FIT_20201A += Double.parseDouble(ccsclList.get(n2).getFIT_20201A());
//				if(ccsclList.get(n2).getFIT_20201B()!=null)
//					FIT_20201B += Double.parseDouble(ccsclList.get(n2).getFIT_20201B());
//				if(ccsclList.get(n2).getFIT20201AZ()!=null)
//					FIT20201AZ += Double.parseDouble(ccsclList.get(n2).getFIT20201AZ());
//				if(ccsclList.get(n2).getFIT_20202()!=null)
//					FIT_20202 += Double.parseDouble(ccsclList.get(n2).getFIT_20202());
//				if(ccsclList.get(n2).getFIT20202Z()!=null)
//					FIT20202Z += Double.parseDouble(ccsclList.get(n2).getFIT20202Z());
//				if(ccsclList.get(n2).getLIT_20201A()!=null)
//					LIT_20201A += Double.parseDouble(ccsclList.get(n2).getLIT_20201A());
//				if(ccsclList.get(n2).getLIT_20201B()!=null)
//					LIT_20201B += Double.parseDouble(ccsclList.get(n2).getLIT_20201B());
//				if(ccsclList.get(n2).getLIT_20401A()!=null)
//					LIT_20401A += Double.parseDouble(ccsclList.get(n2).getLIT_20401A());
//				if(ccsclList.get(n2).getLIT_20401B()!=null)
//					LIT_20401B += Double.parseDouble(ccsclList.get(n2).getLIT_20401B());
//				if(ccsclList.get(n2).getFIT_20401()!=null)
//					FIT_20401 += Double.parseDouble(ccsclList.get(n2).getFIT_20401());
//				if(ccsclList.get(n2).getFIT20401Z()!=null)
//					FIT20401Z += Double.parseDouble(ccsclList.get(n2).getFIT20401Z());
//				if(ccsclList.get(n2).getJINGHUAGUAN()!=null)
//					JINGHUAGUAN += Double.parseDouble(ccsclList.get(n2).getJINGHUAGUAN());
//			}
//			sumCcscl.setREPORT_TIME("班累积");
//			sumCcscl.setFIT_20201A(String.valueOf(FIT_20201A));
//			sumCcscl.setFIT_20201B(String.valueOf(FIT_20201B));
//			sumCcscl.setFIT20201AZ(String.valueOf(FIT20201AZ));
//			sumCcscl.setFIT_20202(String.valueOf(FIT_20202));
//			sumCcscl.setFIT20202Z(String.valueOf(FIT20202Z));
//			sumCcscl.setLIT_20201A(String.valueOf(LIT_20201A));
//			sumCcscl.setLIT_20201B(String.valueOf(LIT_20201B));
//			sumCcscl.setLIT_20401A(String.valueOf(LIT_20401A));
//			sumCcscl.setLIT_20401B(String.valueOf(LIT_20401B));
//			sumCcscl.setFIT_20401(String.valueOf(FIT_20401));
//			sumCcscl.setFIT20401Z(String.valueOf(FIT20401Z));
//			sumCcscl.setJINGHUAGUAN(String.valueOf(JINGHUAGUAN));
//			ccsclList.add(d1+d2+1,sumCcscl);
//			for(int n3=d1+d2;n3<d1+d2+d3;n3++){
//				if(ccsclList.get(n3).getFIT_20201A()!=null)
//					FIT_20201A += Double.parseDouble(ccsclList.get(n3).getFIT_20201A());
//				if(ccsclList.get(n3).getFIT_20201B()!=null)
//					FIT_20201B += Double.parseDouble(ccsclList.get(n3).getFIT_20201B());
//				if(ccsclList.get(n3).getFIT20201AZ()!=null)
//					FIT20201AZ += Double.parseDouble(ccsclList.get(n3).getFIT20201AZ());
//				if(ccsclList.get(n3).getFIT_20202()!=null)
//					FIT_20202 += Double.parseDouble(ccsclList.get(n3).getFIT_20202());
//				if(ccsclList.get(n3).getFIT20202Z()!=null)
//					FIT20202Z += Double.parseDouble(ccsclList.get(n3).getFIT20202Z());
//				if(ccsclList.get(n3).getLIT_20201A()!=null)
//					LIT_20201A += Double.parseDouble(ccsclList.get(n3).getLIT_20201A());
//				if(ccsclList.get(n3).getLIT_20201B()!=null)
//					LIT_20201B += Double.parseDouble(ccsclList.get(n3).getLIT_20201B());
//				if(ccsclList.get(n3).getLIT_20401A()!=null)
//					LIT_20401A += Double.parseDouble(ccsclList.get(n3).getLIT_20401A());
//				if(ccsclList.get(n3).getLIT_20401B()!=null)
//					LIT_20401B += Double.parseDouble(ccsclList.get(n3).getLIT_20401B());
//				if(ccsclList.get(n3).getFIT_20401()!=null)
//					FIT_20401 += Double.parseDouble(ccsclList.get(n3).getFIT_20401());
//				if(ccsclList.get(n3).getFIT20401Z()!=null)
//					FIT20401Z += Double.parseDouble(ccsclList.get(n3).getFIT20401Z());
//				if(ccsclList.get(n3).getJINGHUAGUAN()!=null)
//					JINGHUAGUAN += Double.parseDouble(ccsclList.get(n3).getJINGHUAGUAN());
//			}
//			sumCcscl.setREPORT_TIME("班累积");
//			sumCcscl.setFIT_20201A(String.valueOf(FIT_20201A));
//			sumCcscl.setFIT_20201B(String.valueOf(FIT_20201B));
//			sumCcscl.setFIT20201AZ(String.valueOf(FIT20201AZ));
//			sumCcscl.setFIT_20202(String.valueOf(FIT_20202));
//			sumCcscl.setFIT20202Z(String.valueOf(FIT20202Z));
//			sumCcscl.setLIT_20201A(String.valueOf(LIT_20201A));
//			sumCcscl.setLIT_20201B(String.valueOf(LIT_20201B));
//			sumCcscl.setLIT_20401A(String.valueOf(LIT_20401A));
//			sumCcscl.setLIT_20401B(String.valueOf(LIT_20401B));
//			sumCcscl.setFIT_20401(String.valueOf(FIT_20401));
//			sumCcscl.setFIT20401Z(String.valueOf(FIT20401Z));
//			sumCcscl.setJINGHUAGUAN(String.valueOf(JINGHUAGUAN));
//			ccsclList.add(d1+d2+d3+2,sumCcscl);
//			
//		}
//		
//		return ccsclList;
//	}
//	public boolean addOrUpdateSagddRPD(PcRpdWellSagddT prws)throws Exception{
//		return sCLJYDao.addOrUpdateSagddRPD(prws);
//	}
	
	@Override
	public List<String> dayreport() throws Exception {
		return cCSCLDao.dayreport();
	}

	public JSONArray searchCCSCLRPD(String reportDate,String totalNumf) throws Exception {
		JSONObject obj = new JSONObject();
		JSONArray arr = new JSONArray();
		String FIT_20201ATemp = "";
		//String FIT_20201BTemp = "";
		String FIT20201AZTemp = "";
		//String FIT_20202Temp = "";
		String FIT20202ZTemp = "";
		//String LIT_20201ATemp = "";
		//String LIT_20201BTemp = "";
		//String LIT_20401ATemp = "";
		//String LIT_20401BTemp = "";
		//String FIT_20401Temp = "";
		String FIT20401ZTemp = "";
		//String JINGHUAGUANTemp = "";
		

		List<String> FIT_20201A = new ArrayList<String>();
		List<String> FIT20201AZ = new ArrayList<String>();
		List<String> FIT20202Z = new ArrayList<String>();
		List<String> FIT20401Z = new ArrayList<String>();
		
		List<String> FIT_20201ASUM = new ArrayList<String>();
		List<String> FIT20201AZSUM = new ArrayList<String>();
		List<String> FIT20202ZSUM = new ArrayList<String>();
		List<String> FIT20401ZSUM = new ArrayList<String>();
		/*double FIT_20201A = 0.00;
		double FIT_20201B = 0.00;
		double FIT20201AZ = 0.00;
		double FIT_20202 = 0.00;
		double FIT20202Z = 0.00;
		double LIT_20201A = 0.00;
		double LIT_20201B = 0.00;
		double LIT_20401A = 0.00;
		double LIT_20401B = 0.00;
		double FIT_20401 = 0.00;
		double FIT20401Z = 0.00;
		double JINGHUAGUAN = 0.00;
		double FIT_20201ASUM = 0.00;
		double FIT_20201BSUM = 0.00;
		double FIT20201AZSUM = 0.00;
		double FIT_20202SUM = 0.00;
		double FIT20202ZSUM = 0.00;
		double LIT_20201ASUM = 0.00;
		double LIT_20201BSUM = 0.00;
		double LIT_20401ASUM = 0.00;
		double LIT_20401BSUM = 0.00;
		double FIT_20401SUM = 0.00;
		double FIT20401ZSUM = 0.00;
		double JINGHUAGUANSUM = 0.00;*/
		int dataflg = 0;  //数据存在标识  0：不存在， 1：存在
		String timeCalssql = "select tp.parameter_value from PC_CD_TRIGGERED_PROCESS_T tp where tp.parameter_name='union_station_rpd_start_time'";
		String calcNum = "-16";
		List<Object[]> dataSet = cCSCLDao.searchCCSCLRPD(timeCalssql);
		if(dataSet != null && dataSet.size()>0){
			calcNum = dataSet.get(0) + "";
		}
		
		String stratime =DateBean.getDynamicTime(calcNum, reportDate, "0");
		String endtime =DateBean.getDynamicTime(calcNum, reportDate, "1");
		String sql = "select "+
					" a.fit_20201A,a.fit_20201b ,a.fit20201az,a.fit_20202,a.FIT20202Z,a.LIT_20201A,a.LIT_20201B,a.LIT_20401A,a.LIT_20401B,a.FIT_20401,a.FIT20401Z,a.LIT_10101A, a.rpd_u2_water_id,to_char(a.report_time,'YYYY-MM-DD HH24:MI:SS')as report_time"+
					" from PC_RPD_U2_WATER_T A where 1=1 ";
			sql += " and a.report_time between TO_DATE('"+stratime+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+endtime+"','YYYY-MM-DD HH24:MI:SS') order by a.report_time";
		Object[] ccsclTab = null;
		List<Object[]> ccscls = cCSCLDao.searchCCSCLRPD(sql);	
		String[][] dates = DateBean.getReportTime("time", reportDate);
		if(ccscls != null && ccscls.size()>0){
//			for(String[] dateList:dates){
//				obj = new JSONObject();
			for (int i = 0; i < dates.length; i++) {
				String[] dateList = dates[i];
				obj = new JSONObject();

				
//				FIT_20201A.add(Double.parseDouble(FIT_20201ATemp));
//				FIT20201AZ.add(Double.parseDouble(FIT20201AZTemp));
//				FIT20202Z.add(Double.parseDouble(FIT20202ZTemp));
//				FIT20401Z.add(Double.parseDouble(FIT20401ZTemp));
				
				
//				FIT_20201A += FIT_20201ASUM;
				//FIT_20201B += FIT_20201BSUM;
//				FIT20201AZ += FIT20201AZSUM;
				//FIT_20202 += FIT_20202SUM;
//				FIT20202Z += FIT20202ZSUM;
				//LIT_20201A += LIT_20201ASUM;
//				LIT_20201B += LIT_20201BSUM;
//				LIT_20401A += LIT_20401ASUM;
//				LIT_20401B += LIT_20401BSUM;
//				FIT_20401 += FIT_20401SUM;
//				FIT20401Z += FIT20401ZSUM;
//				JINGHUAGUAN += JINGHUAGUANSUM;
				if (i == 2) {
					 JSONObject oobj =  (JSONObject) arr.get(5);
					obj.put("RPD_U2_WATER_ID", "");
					obj.put("REPORT_TIME", "班累积");
					obj.put("FIT_20201A", "/");
					obj.put("FIT_20201B", "/");
					if(ccscls.size()>8 && ccscls.get(8) !=null){
						obj.put("FIT20201AZ",   CommonsUtil.getRegulation0(ccscls.get(8)[2], ccscls.get(4)[2]));
					}else{
						obj.put("FIT20201AZ",   "");
					}
					
					obj.put("FIT_20202", "/");
					if(ccscls.size()>8 && ccscls.get(8) !=null){
						obj.put("FIT20202Z",   CommonsUtil.getRegulation0(ccscls.get(8)[4], ccscls.get(4)[4]));
					}else{
						obj.put("FIT20202Z",   "");
					}
					
					obj.put("LIT_20201A", "/");
					obj.put("LIT_20201B", "/");
					obj.put("LIT_20401A", "/");
					obj.put("LIT_20401B", "/");
					obj.put("FIT_20401", "/");
					if(ccscls.size()>8 && ccscls.get(8) !=null){
						obj.put("FIT20401Z",   CommonsUtil.getRegulation0(ccscls.get(8)[10], ccscls.get(4)[10]));
					}else{
						obj.put("FIT20401Z",   "");
					}
					
					obj.put("JINGHUAGUAN", "/");
				}else{

					FIT_20201ATemp = CommonsUtil.getBanSumData0(FIT_20201ASUM);
					FIT20201AZTemp = CommonsUtil.getBanSumData0(FIT20201AZSUM);
					FIT20202ZTemp = CommonsUtil.getBanSumData0(FIT20202ZSUM);
					FIT20401ZTemp = CommonsUtil.getBanSumData0(FIT20401ZSUM);
					
					obj.put("RPD_U2_WATER_ID", "");
					obj.put("REPORT_TIME", "班累积");
					obj.put("FIT_20201A", "/");
					obj.put("FIT_20201B", "/");
					obj.put("FIT20201AZ", FIT20201AZTemp);
					obj.put("FIT_20202", "/");
					
					obj.put("FIT20202Z", FIT20202ZTemp);
					obj.put("LIT_20201A", "/");
					obj.put("LIT_20201B", "/");
					obj.put("LIT_20401A", "/");
					obj.put("LIT_20401B", "/");
					obj.put("FIT_20401", "/");
					obj.put("FIT20401Z", FIT20401ZTemp);
					obj.put("JINGHUAGUAN", "/");
				}
				arr.add(obj);
				
				FIT_20201ASUM = new ArrayList<String>();
//				FIT_20201BSUM = 0.00;
				FIT20201AZSUM = new ArrayList<String>();
//				FIT_20202SUM = 0.00;
				FIT20202ZSUM = new ArrayList<String>();
//				LIT_20201ASUM = 0.00;
//				LIT_20201BSUM = 0.00;
//				LIT_20401ASUM = 0.00;
//				LIT_20401BSUM = 0.00;
//				FIT_20401SUM = 0.00;
				FIT20401ZSUM = new ArrayList<String>();
//				JINGHUAGUANSUM = 0.00;
				
				for(String data:dateList){
					ccsclTab = null;
					dataflg = 0;
					for(Object[] ccscl:ccscls){
						obj = new JSONObject();
						
						if(data.toString().equals(ccscl[13].toString())){
							dataflg = 1;
							ccsclTab = ccscl;
							//添加班累积值
							if(ccscl[0] != null && !"".equals(ccscl[0].toString())){
								//FIT_20201ASUM += Double.parseDouble(ccscl[0].toString());
								FIT_20201ASUM.add(ccscl[0].toString());
							}else{
								FIT_20201ASUM.add("");
							}
							if(ccscl[1] != null && !"".equals(ccscl[1].toString())){
								//FIT_20201BSUM += ccscl[1].toString());
							}
							if(ccscl[2] != null && !"".equals(ccscl[2].toString())){
								//FIT20201AZSUM += ccscl[2].toString());
								FIT20201AZSUM.add(ccscl[2].toString());
							}else{
								FIT20201AZSUM.add("");
							}
							if(ccscl[3] != null && !"".equals(ccscl[3].toString())){
								//FIT_20202SUM += ccscl[3].toString());
							}
							
							if(ccscl[4] != null && !"".equals(ccscl[4].toString())){
								//FIT20202ZSUM += ccscl[4].toString());
								FIT20202ZSUM.add(ccscl[4].toString());
							}else{
								FIT20202ZSUM.add("");
							}
//							if(ccscl[5] != null && !"".equals(ccscl[5].toString())){
//								//LIT_20201ASUM += ccscl[5].toString());
//							}
//							if(ccscl[6] != null && !"".equals(ccscl[6].toString())){
//								LIT_20201BSUM += ccscl[6].toString());
//							}
//							if(ccscl[7] != null && !"".equals(ccscl[7].toString())){
//								LIT_20401ASUM += ccscl[7].toString());
//							}
//							
//							if(ccscl[8] != null && !"".equals(ccscl[8].toString())){
//								LIT_20401BSUM += ccscl[8].toString());
//							}
//							if(ccscl[9] != null && !"".equals(ccscl[9].toString())){
//								FIT_20401SUM += ccscl[9].toString());
//							}
							if(ccscl[10] != null && !"".equals(ccscl[10].toString())){
								//FIT20401ZSUM += ccscl[10].toString());
								FIT20401ZSUM.add(ccscl[10].toString());
							}else{
								FIT20401ZSUM.add("");
							}
//							if(ccscl[11] != null && !"".equals(ccscl[11].toString())){
//								JINGHUAGUANSUM += ccscl[11].toString());
//							}
						}
						
					}
					if(dataflg == 1){
						obj.put("RPD_U2_WATER_ID", ccsclTab[12]);
						obj.put("REPORT_TIME", data.substring(11, 16));
						if(ccsclTab[0] != null){
							obj.put("FIT_20201A", CommonsUtil.getNotOneData0(ccsclTab[0]));
							FIT_20201A.add(ccsclTab[0].toString());
						}else{
							obj.put("FIT_20201A", "");
							FIT_20201A.add("");
						}
						if(ccsclTab[1]!=null){
							obj.put("FIT_20201B", CommonsUtil.getNotOneData0(ccsclTab[1]));
						}else{
							obj.put("FIT_20201B", "");
						}
						if(ccsclTab[2]!=null){
							obj.put("FIT20201AZ", CommonsUtil.getNotOneData0(ccsclTab[2]));
							FIT20201AZ.add(ccsclTab[2].toString());
						}else{
							obj.put("FIT20201AZ", "");
							FIT20201AZ.add("");
						}
						if(ccsclTab[3]!=null){
							obj.put("FIT_20202", CommonsUtil.getNotOneData0(ccsclTab[3]));
						}else{
							obj.put("FIT_20202", "");
						}
						if(ccsclTab[4]!=null){
							obj.put("FIT20202Z", CommonsUtil.getNotOneData0(ccsclTab[4]));
							FIT20202Z.add(ccsclTab[4].toString());
						}else{
							obj.put("FIT20202Z", "");
							FIT20202Z.add("");
						}
						if(ccsclTab[5]!=null){
							obj.put("LIT_20201A", CommonsUtil.getNotOneData(ccsclTab[5]));
						}else{
							obj.put("LIT_20201A", "");
						}
						if(ccsclTab[6]!=null){
							obj.put("LIT_20201B", CommonsUtil.getNotOneData(ccsclTab[6]));
						}else{
							obj.put("LIT_20201B", "");
						}
						if(ccsclTab[7]!=null){
							obj.put("LIT_20401A", CommonsUtil.getNotOneData(ccsclTab[7]));
						}else{
							obj.put("LIT_20401A", "");
						}
						if(ccsclTab[8]!=null){
							obj.put("LIT_20401B", CommonsUtil.getNotOneData(ccsclTab[8]));
						}else{
							obj.put("LIT_20401B", "");
						}
						
						if(ccsclTab[9]!=null){
							obj.put("FIT_20401", CommonsUtil.getNotOneData0(ccsclTab[9]));
						}else{
							obj.put("FIT_20401", "");
						}
						if(ccsclTab[10]!=null){
							obj.put("FIT20401Z", CommonsUtil.getNotOneData0(ccsclTab[10]));
							FIT20401Z.add(ccsclTab[10].toString());
						}else{
							obj.put("FIT20401Z", "");
							FIT20401Z.add("");
						}
						if(ccsclTab[11]!=null){
							obj.put("JINGHUAGUAN", CommonsUtil.getNotOneData(ccsclTab[11]));
						}else{
							obj.put("JINGHUAGUAN", "");
						}
						
					}else{
						obj.put("RPD_U2_WATER_ID", "");
						obj.put("REPORT_TIME", data.substring(11, 16));
						obj.put("FIT_20201A", "");
						obj.put("FIT_20201B", "");
						obj.put("FIT20201AZ","");
						obj.put("FIT_20202", "");
						
						obj.put("FIT20202Z", "");
						obj.put("LIT_20201A", "");
						obj.put("LIT_20201B", "");
						obj.put("LIT_20401A", "");
						obj.put("LIT_20401B", "");
						obj.put("FIT_20401", "");
						obj.put("FIT20401Z", "");
						obj.put("JINGHUAGUAN","");
						
						FIT_20201A.add("");
						FIT20201AZ.add("");
						FIT20202Z.add("");
						FIT20401Z.add("");
						
						FIT_20201ASUM.add("");
						FIT20201AZSUM.add("");
						FIT20202ZSUM.add("");
						FIT20401ZSUM.add("");
					}
					arr.add(obj);
				}
			}
		
				obj.put("RPD_U2_WATER_ID", "");
				obj.put("REPORT_TIME", "班累积");
				obj.put("FIT_20201A", "/");
				obj.put("FIT_20201B", "/");
				if(ccscls.size()==13 && ccscls.get(12)[2] !=null){
					obj.put("FIT20201AZ",   CommonsUtil.getRegulation0(ccscls.get(12)[2], ccscls.get(8)[2]));
				}else{
					obj.put("FIT20201AZ",   "");
				}
				
				obj.put("FIT_20202", "/");
				if(ccscls.size()==13 && ccscls.get(12)[4] !=null){
					obj.put("FIT20202Z",   CommonsUtil.getRegulation0(ccscls.get(12)[4], ccscls.get(8)[4]));
				}else{
					obj.put("FIT20202Z",   "");
				}
				
				obj.put("LIT_20201A", "/");
				obj.put("LIT_20201B", "/");
				obj.put("LIT_20401A", "/");
				obj.put("LIT_20401B", "/");
				obj.put("FIT_20401", "/");
				if(ccscls.size()==13 && ccscls.get(12)[10] !=null){
					obj.put("FIT20401Z",   CommonsUtil.getRegulation0(ccscls.get(12)[10], ccscls.get(8)[10]));
				}else{
					obj.put("FIT20401Z",   "");
				}
				
				obj.put("JINGHUAGUAN", "/");
			
		
			
//			FIT_20201ATemp = CommonsUtil.getSumData00(FIT_20201A);
//			FIT20201AZTemp = CommonsUtil.getSumData00(FIT20201AZ);
//			FIT20202ZTemp = CommonsUtil.getSumData00(FIT20202Z);
//			FIT20401ZTemp = CommonsUtil.getSumData00(FIT20401Z);
//			
//			obj.put("RPD_U2_WATER_ID", "");
//			obj.put("REPORT_TIME", "班累积");
//			obj.put("FIT_20201A", "/");
//			obj.put("FIT_20201B", "/");
//			obj.put("FIT20201AZ", FIT20201AZTemp);
//			obj.put("FIT_20202", "/");
//			
//			obj.put("FIT20202Z", FIT20202ZTemp);
//			obj.put("LIT_20201A", "/");
//			obj.put("LIT_20201B", "/");
//			obj.put("LIT_20401A", "/");
//			obj.put("LIT_20401B", "/");
//			obj.put("FIT_20401", "/");
//			obj.put("FIT20401Z", FIT20401ZTemp);
//			obj.put("JINGHUAGUAN", "/");

			
			arr.add(obj);
			
		}else{
			for(String[] dateList:dates){
				obj = new JSONObject();
				for(String data:dateList){
					obj = new JSONObject();
					obj.put("RPD_U2_WATER_ID", "");
					obj.put("REPORT_TIME", data.substring(11, 16));
					obj.put("FIT_20201A", "");
					obj.put("FIT_20201B", "");
					obj.put("FIT20201AZ","");
					obj.put("FIT_20202", "");
					
					obj.put("FIT20202Z", "");
					obj.put("LIT_20201A", "");
					obj.put("LIT_20201B", "");
					obj.put("LIT_20401A", "");
					obj.put("LIT_20401B", "");
					obj.put("FIT_20401", "");
					obj.put("FIT20401Z", "");
					obj.put("JINGHUAGUAN","");
					arr.add(obj);
				}
				obj.put("RPD_U2_WATER_ID", "");
				obj.put("REPORT_TIME", "班累积");
				obj.put("FIT_20201A", "/");
				obj.put("FIT_20201B", "/");
				obj.put("FIT20201AZ", "");
				obj.put("FIT_20202", "/");
				
				obj.put("FIT20202Z", "");
				obj.put("LIT_20201A", "/");
				obj.put("LIT_20201B", "/");
				obj.put("LIT_20401A", "/");
				obj.put("LIT_20401B", "/");
				obj.put("FIT_20401", "/");
				obj.put("FIT20401Z", "");
				obj.put("JINGHUAGUAN", "/");
				arr.add(obj);
			}
		}
		if(ccscls != null && ccscls.size()>0){
			arr.remove(0);
//			FIT_20201A.remove(0);
//			FIT20201AZ.remove(0);
//			FIT20202Z.remove(0);
//			FIT20401Z.remove(0);
			
			
			obj = new JSONObject();
			obj.put("RPD_U2_WATER_ID", "");
			obj.put("REPORT_TIME", "合计");
			obj.put("FIT_20201A", "/");
			obj.put("FIT_20201B", "/");
			obj.put("FIT20201AZ", CommonsUtil.getSumData0(FIT20201AZ));
			obj.put("FIT_20202", "/");
			
			obj.put("FIT20202Z", CommonsUtil.getSumData0(FIT20202Z));
			obj.put("LIT_20201A", "/");
			obj.put("LIT_20201B", "/");
			obj.put("LIT_20401A", "/");
			obj.put("LIT_20401B", "/");
			obj.put("FIT_20401", "/");
			obj.put("FIT20401Z", CommonsUtil.getSumData0(FIT20401Z));
			obj.put("JINGHUAGUAN","/");
			
			arr.add(obj);
		}else{
			obj = new JSONObject();
			obj.put("RPD_U2_WATER_ID", "");
			obj.put("REPORT_TIME", "合计");
			obj.put("FIT_20201A", "/");
			obj.put("FIT_20201B", "/");
			obj.put("FIT20201AZ", "");
			obj.put("FIT_20202", "/");
			
			obj.put("FIT20202Z", "");
			obj.put("LIT_20201A", "/");
			obj.put("LIT_20201B", "/");
			obj.put("LIT_20401A", "/");
			obj.put("LIT_20401B", "/");
			obj.put("FIT_20401", "/");
			obj.put("FIT20401Z", "");
			obj.put("JINGHUAGUAN","/");
			arr.add(obj);
		}
		return arr;
		
	}
	
	public List<Object[]> searchU2_WATER(String rq,String fields) throws Exception {
		String timeCalssql = "select tp.parameter_value from PC_CD_TRIGGERED_PROCESS_T tp where tp.parameter_name='union_station_rpd_start_time'";
		String calcNum = "-16";
		List<Object[]> dataSet = cCSCLDao.queryInfo(timeCalssql);
		if(dataSet != null && dataSet.size()>0){
			calcNum = dataSet.get(0) + "";
		}
		String stratime =DateBean.getDynamicTime(calcNum, rq, "0");
		String endtime =DateBean.getDynamicTime(calcNum, rq, "1");
		String sql = "select " + fields + " from PC_RPD_U2_WATER_T u where report_time between TO_DATE('"+stratime+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+endtime+"','YYYY-MM-DD HH24:MI:SS') order by u.report_time";
		List<Object[]> yyList = cCSCLDao.queryInfo(sql);
		return yyList;
	}
}
