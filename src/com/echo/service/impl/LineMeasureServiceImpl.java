package com.echo.service.impl;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang.ObjectUtils.Null;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.echo.dao.CommonDao;
import com.echo.dao.LineMeasureDao;
import com.echo.dto.PcRpdCy1ScMessureT;
import com.echo.dto.PcRpdCy2ScMessureT;
import com.echo.dto.PcRpdReportRhs2T;
import com.echo.dto.PcRpdU1DehydrationT;
import com.echo.dto.PcRpd1836;
import com.echo.dto.PcRpd1836zh;
import com.echo.dto.PcRpdU2zyz;
import com.echo.dto.PcRpdU2zyzzh;
import com.echo.dto.PcRpdXylScMessureT;
import com.echo.dto.PcRpdXzyzhg;
import com.echo.dto.PcRpdXzyzhgzh;
import com.echo.dto.PcRpdxczs;
import com.echo.dto.PcRpdxczszh;
import com.echo.service.LineMeasureService;
import com.echo.util.CommonsUtil;
import com.echo.util.DateBean;

public class LineMeasureServiceImpl implements LineMeasureService{

	private LineMeasureDao lineMeasureDao;	
	public void setLineMeasureDao(LineMeasureDao lineMeasureDao) {
		this.lineMeasureDao = lineMeasureDao;
	}
	@SuppressWarnings("unused")
	private  CommonDao commonDao;
	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}

	@Override
	public JSONArray searchLineM(String rq) throws Exception {
		JSONObject obj = new JSONObject();
		JSONArray arr = new JSONArray();
		//一行
		String FIT10109Z = "";
		String FIT10101B = "";
		String FIT10102B = "";
		String FIT10103B = "";
		String FIT10104B = "";
		
		List<String>  FIT10109ZSUM = new ArrayList<String>();
		List<String>  FIT10101BSUM = new ArrayList<String>();
		List<String>  FIT10102BSUM = new ArrayList<String>();
		List<String>  FIT10103BSUM = new ArrayList<String>();
		List<String>  FIT10104BSUM = new ArrayList<String>();
	 
		//-----------------合计
		  List<String> FIT10109ZSUMday  = new ArrayList<String>();
		  List<String> FIT10101BSUMday  = new ArrayList<String>();
		  List<String> FIT10102BSUMday  = new ArrayList<String>();
		  List<String> FIT10103BSUMday  = new ArrayList<String>();
		  List<String> FIT10104BSUMday  = new ArrayList<String>();
		  
		
		
		int dataflg = 0;  //数据存在标识  0：不存在， 1：存在
		String timeCalssql = "select tp.parameter_value from PC_CD_TRIGGERED_PROCESS_T tp where tp.parameter_name='union_station_rpd_start_time'";
		String calcNum = "-16";
		List<Object[]> dataSet = lineMeasureDao.searchLineM(timeCalssql);
		if(dataSet != null && dataSet.size()>0){
			calcNum = dataSet.get(0) + "";
		}
		
		String stratime =DateBean.getDynamicTime(calcNum, rq, "0");
		String endtime =DateBean.getDynamicTime(calcNum, rq, "1");
//		String sql1 ="select RPD_U2_LINE_MEASURE_ID,  to_char(report_time,'YYYY-MM-DD HH24:MI:SS')as report_time ,"+
//					"FIT_10109,  FIT10109Z,PT_10109, TT_10109, FIT10101A, FIT10101B,PT_10101,TT_10101,MR_10101,"+
//					"FIT10102A, FIT10102B, PT_10102,TT_10102,MR_10102,FIT10103A,FIT10103B,  PT_10103,TT_10103,"+
//					"MR_10103,FIT10104A,FIT10104B,PT_10104,TT_10104,MR_10104,"+
//					"FIT_10110,FIT10110Z, PT_10110, TT_10110, FIT10105A, FIT10105B, PT_10105, TT_10105, MR_10105,"+
//					"FIT10106A, FIT10106B, PT_10106, TT_10106, MR_10106, FIT10107A, FIT10107B,    PT_10107,"+
//					"TT_10107, MR_10107, FIT10108A, FIT10108B, PT_10108, TT_10108,"+
//					"MR_10108 from  PC_RPD_U2_LINE_MEASURE_T  where 1=1";
		
		String sql1 ="select RPD_U2_LINE_MEASURE_ID,  to_char(report_time,'YYYY-MM-DD HH24:MI:SS')as report_time ,"+
		"FIT_10109,  FIT10109Z,PT_10109, TT_10109, FIT10101A, FIT10101B,PT_10101,TT_10101,MR_10101,"+
		"FIT10102A, FIT10102B, PT_10102,TT_10102,MR_10102,FIT10103A,FIT10103B,  PT_10103,TT_10103,"+
		"MR_10103,FIT10104A,FIT10104B,PT_10104,TT_10104,MR_10104  from  PC_RPD_U2_LINE_MEASURE_T  where 1=1";
			sql1 += " and report_time between TO_DATE('"+stratime+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+endtime+"','YYYY-MM-DD HH24:MI:SS') order by report_time";
		
//		String sql2 ="select RPD_U2_LINE_MEASURE_ID,  to_char(report_time,'YYYY-MM-DD HH24:MI:SS')as report_time ,"+
//			"FIT_10110,FIT10110Z, PT_10110, TT_10110, FIT10105A, FIT10105B, PT_10105, TT_10105, MR_10105,"+
//			"FIT10106A, FIT10106B, PT_10106, TT_10106, MR_10106, FIT10107A, FIT10107B,    PT_10107,"+
//			"TT_10107, MR_10107, FIT10108A, FIT10108B, PT_10108, TT_10108,"+
//			"MR_10108 from  PC_RPD_U2_LINE_MEASURE_T  where 1=1";
//		sql2 += " and report_time between TO_DATE('"+stratime+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+endtime+"','YYYY-MM-DD HH24:MI:SS') order by report_time";

		Object[] u2oiltable = null;
		//for(int n=1;n<3;n++){
			
		List<Object[]> u2oils = lineMeasureDao.searchLineM(sql1);
		String[][] dates = DateBean.getReportTime("time", rq);
		if(u2oils != null && u2oils.size()>0){

			for (int i = 0; i < dates.length; i++) {
				String[] dateList = dates[i];
				obj = new JSONObject();
				//yi
			
				 
//				 FIT10109ZSUMday.add(Double.parseDouble(FIT10109Z));
//				 FIT10101BSUMday.add(Double.parseDouble(FIT10101B));
//				 FIT10102BSUMday.add(Double.parseDouble(FIT10102B));
//				 FIT10103BSUMday.add(Double.parseDouble(FIT10103B));
//				 FIT10104BSUMday.add(Double.parseDouble(FIT10104B));
			if (i == 2) {
				 @SuppressWarnings("unused")
				JSONObject oobj =  (JSONObject) arr.get(5);
		
				    obj.put("RPD_U2_LINE_MEASURE_ID", "");
					obj.put("REPORT_TIME", "班累积");
					obj.put("FIT_10109", "/");
					if(u2oils.size()>8 && u2oils.get(8)!=null){
						obj.put("FIT10109Z", CommonsUtil.getRegulation0(u2oils.get(8)[3], u2oils.get(4)[3]));
					}else{
						obj.put("FIT10109Z","");
					}					
					obj.put("PT_10109", "/");
				    obj.put("TT_10109", "/");
				    obj.put("FIT10101A", "/");
				    if(u2oils.size()>8 && u2oils.get(8)!=null){
				    	obj.put("FIT10101B",  CommonsUtil.getRegulation0(u2oils.get(8)[7], u2oils.get(4)[7]));
				    }else{
				    	obj.put("FIT10101B",  "");
				    }
				   obj.put("PT_10101", "/");
				   obj.put("TT_10101", "/");
				   obj.put("MR_10101", "/");
				   obj.put("FIT10102A", "/");
				   if(u2oils.size()>8 && u2oils.get(8)!=null){
					   obj.put("FIT10102B",  CommonsUtil.getRegulation0(u2oils.get(8)[12], u2oils.get(4)[12]));
				   }else{
					   obj.put("FIT10102B",  "");
				   }
				   obj.put("PT_10102", "/");
				   obj.put("TT_10102", "/");
				   obj.put("MR_10102", "/");
				   obj.put("FIT10103A", "/");
				   if(u2oils.size()>8 && u2oils.get(8)!=null){
					   obj.put("FIT10103B",  CommonsUtil.getRegulation0(u2oils.get(8)[17], u2oils.get(4)[17]));
				   }else{
					   obj.put("FIT10103B",  ""); 
				   }
				   obj.put("PT_10103", "/");
				   obj.put("TT_10103", "/");
				   obj.put("MR_10103", "/");
				   obj.put("FIT10104A", "/");
				   if(u2oils.size()>8 && u2oils.get(8)!=null){
					   obj.put("FIT10104B",  CommonsUtil.getRegulation0(u2oils.get(8)[22], u2oils.get(4)[22]));
				   }else{
					   obj.put("FIT10104B", "");
				   }
				   obj.put("PT_10104", "/");
				   obj.put("TT_10104", "/");
				   obj.put("MR_10104", "/");
			}else{
				 FIT10109Z = CommonsUtil.getBanSumData0(FIT10109ZSUM);
				 FIT10101B = CommonsUtil.getBanSumData0(FIT10101BSUM);
				 FIT10102B = CommonsUtil.getBanSumData0(FIT10102BSUM);
				 FIT10103B = CommonsUtil.getBanSumData0(FIT10103BSUM);
				 FIT10104B = CommonsUtil.getBanSumData0(FIT10104BSUM);
				 
				 obj.put("RPD_U2_LINE_MEASURE_ID", "");
					obj.put("REPORT_TIME", "班累积");
					obj.put("FIT_10109", "/");
					obj.put("FIT10109Z",FIT10109Z);
					obj.put("PT_10109", "/");
				    obj.put("TT_10109", "/");
				    obj.put("FIT10101A", "/");
				    obj.put("FIT10101B", FIT10101B);
				   obj.put("PT_10101", "/");
				   obj.put("TT_10101", "/");
				   obj.put("MR_10101", "/");
				   obj.put("FIT10102A", "/");
				   obj.put("FIT10102B", FIT10102B);
				   obj.put("PT_10102", "/");
				   obj.put("TT_10102", "/");
				   obj.put("MR_10102", "/");
				   obj.put("FIT10103A", "/");
				   obj.put("FIT10103B", FIT10103B);
				   obj.put("PT_10103", "/");
				   obj.put("TT_10103", "/");
				   obj.put("MR_10103", "/");
				   obj.put("FIT10104A", "/");
				  obj.put("FIT10104B", FIT10104B);
				   obj.put("PT_10104", "/");
				   obj.put("TT_10104", "/");
				   obj.put("MR_10104", "/");
			}
				 
				   arr.add(obj);
				   FIT10109ZSUM = new ArrayList<String>();
				   FIT10101BSUM = new ArrayList<String>();
				   FIT10102BSUM = new ArrayList<String>();
				   FIT10103BSUM = new ArrayList<String>();
				   FIT10104BSUM = new ArrayList<String>();
				   
				   //er
				  
				
				for(String data:dateList){
				//	System.out.println(data);
					u2oiltable = null;
					dataflg = 0;
					
					for(Object[] u2oil:u2oils){
						obj = new JSONObject();
						
						if(data.toString().equals(u2oil[1].toString())){
							dataflg = 1;
							u2oiltable = u2oil;
							//添加班累积值
//					
							if(u2oil[3] != null && !"".equals(u2oil[3])){
								FIT10109ZSUMday.add(u2oil[3].toString());
							}else{
								FIT10109ZSUMday.add("");
							}
							if(u2oil[7] != null && !"".equals(u2oil[7])){
								FIT10101BSUMday.add(u2oil[7].toString());
							}else{
								FIT10101BSUMday.add("");
							}

							if(u2oil[12] != null && !"".equals(u2oil[12])){
								FIT10102BSUMday.add(u2oil[12].toString());
							}else{
								FIT10102BSUMday.add("");
							}

							if(u2oil[17] != null && !"".equals(u2oil[17])){
								FIT10103BSUMday.add(u2oil[17].toString());
							}else{
								FIT10103BSUMday.add("");
							}
							if(u2oil[22] != null && !"".equals(u2oil[22])){
								FIT10104BSUMday.add(u2oil[22].toString());
							}else{
								FIT10104BSUMday.add("");
							}
							
							
						}
					}
						if(dataflg == 1){
							obj.put("RPD_U2_LINE_MEASURE_ID", u2oiltable[0]);
							obj.put("REPORT_TIME",  data.substring(11, 16));

						   obj.put("FIT_10109", CommonsUtil.getIntData(u2oiltable[2]));
						   if(u2oiltable[3] !=null){
						   obj.put("FIT10109Z", CommonsUtil.getIntData(u2oiltable[3]));
						   FIT10109ZSUM.add(u2oiltable[3].toString());
						   }else{
							   obj.put("FIT10109Z", "");
							   FIT10109ZSUM.add("");
						   }
						   obj.put("PT_10109", CommonsUtil.getNotOneData(u2oiltable[4]));
						   obj.put("TT_10109", CommonsUtil.getIntData(u2oiltable[5]));
						   obj.put("FIT10101A", CommonsUtil.getIntData(u2oiltable[6]));
						   if(u2oiltable[7] !=null){
							   obj.put("FIT10101B", CommonsUtil.getIntData(u2oiltable[7]));
							   FIT10101BSUM.add(u2oiltable[7].toString());
						   }else{
							   obj.put("FIT10101B", "");
							   FIT10101BSUM.add("");
						   }
					  
						    obj.put("PT_10101", CommonsUtil.getNotOneData(u2oiltable[8]));
							obj.put("TT_10101", CommonsUtil.getIntData(u2oiltable[9]));
							obj.put("MR_10101", CommonsUtil.getNotOneData(u2oiltable[10]));
							obj.put("FIT10102A", CommonsUtil.getIntData(u2oiltable[11]));
							if(u2oiltable[12] !=null){
								obj.put("FIT10102B", CommonsUtil.getIntData(u2oiltable[12]));
								FIT10102BSUM.add(u2oiltable[12].toString());
							}else{
								obj.put("FIT10102B", "");
								FIT10102BSUM.add("");
							}
							  
							obj.put("PT_10102", CommonsUtil.getNotOneData(u2oiltable[13]));
							obj.put("TT_10102", CommonsUtil.getIntData(u2oiltable[14]));
							obj.put("MR_10102", CommonsUtil.getNotOneData(u2oiltable[15]));
							obj.put("FIT10103A", CommonsUtil.getIntData(u2oiltable[16]));
							if(u2oiltable[17] !=null){
								obj.put("FIT10103B", CommonsUtil.getIntData(u2oiltable[17]));
								FIT10103BSUM.add(u2oiltable[17].toString());
							}else{
								obj.put("FIT10103B", "");
								FIT10103BSUM.add("");
							}
							obj.put("PT_10103", CommonsUtil.getNotOneData(u2oiltable[18]));
							obj.put("TT_10103", CommonsUtil.getIntData(u2oiltable[19]));
							obj.put("MR_10103", CommonsUtil.getNotOneData(u2oiltable[20]));
							obj.put("FIT10104A", CommonsUtil.getIntData(u2oiltable[21]));
							if(u2oiltable[22] !=null){
								obj.put("FIT10104B", CommonsUtil.getIntData(u2oiltable[22]));
								FIT10104BSUM.add(u2oiltable[22].toString());
							}else{
								obj.put("FIT10104B", "");
								FIT10104BSUM.add("");
							}
							obj.put("PT_10104", CommonsUtil.getNotOneData(u2oiltable[23]));
							obj.put("TT_10104", CommonsUtil.getIntData(u2oiltable[24]));
							obj.put("MR_10104", CommonsUtil.getNotOneData(u2oiltable[25]));
							
							
							
						}else{
							obj.put("RPD_U2_LINE_MEASURE_ID", "");
							obj.put("REPORT_TIME",  data.substring(11, 16));

						   obj.put("FIT_10109", "");
						   obj.put("FIT10109Z", "");
						   obj.put("PT_10109", "");
						   obj.put("TT_10109", "");
						   obj.put("FIT10101A", "");
						   obj.put("FIT10101B", "");
					  
						    obj.put("PT_10101", "");
							obj.put("TT_10101", "");
							obj.put("MR_10101", "");
							obj.put("FIT10102A", "");
							obj.put("FIT10102B","");
							  
							obj.put("PT_10102", "");
							obj.put("TT_10102", "");
							obj.put("MR_10102", "");
							obj.put("FIT10103A", "");
							obj.put("FIT10103B","");
							  
							obj.put("PT_10103", "");
							obj.put("TT_10103", "");
							obj.put("MR_10103", "");
							obj.put("FIT10104A", "");
							obj.put("FIT10104B","");
							obj.put("PT_10104", "");
							obj.put("TT_10104", "");
							obj.put("MR_10104", "");
							
							
							FIT10109ZSUM.add("");
							FIT10101BSUM.add("");
							FIT10102BSUM.add("");
							FIT10103BSUM.add("");
							FIT10104BSUM.add("");
						 
							//-----------------合计
							FIT10109ZSUMday.add(""); 
							FIT10101BSUMday.add(""); 
							FIT10102BSUMday.add(""); 
							FIT10103BSUMday.add(""); 
							FIT10104BSUMday.add(""); 
						
						}
						arr.add(obj);
					//	System.out.println(obj);
					
					}
				
			}
			

//			   FIT10109Z = CommonsUtil.getSumData00(FIT10109ZSUMday);
//			   FIT10101B = CommonsUtil.getSumData00(FIT10101BSUMday);
//			   FIT10102B = CommonsUtil.getSumData00(FIT10102BSUMday);
//			   FIT10103B = CommonsUtil.getSumData00(FIT10103BSUMday);
//			   FIT10104B = CommonsUtil.getSumData00(FIT10104BSUMday);
			
//			    obj.put("RPD_U2_LINE_MEASURE_ID", "");
//				obj.put("REPORT_TIME", "班累积");
//				obj.put("FIT_10109", "/");
//				obj.put("FIT10109Z", FIT10109Z);
//				obj.put("PT_10109", "/");
//			    obj.put("TT_10109", "/");
//			    obj.put("FIT10101A", "/");
//			    obj.put("FIT10101B", FIT10101B);
//			   obj.put("PT_10101", "/");
//			   obj.put("TT_10101", "/");
//			   obj.put("MR_10101", "/");
//			   obj.put("FIT10102A", "/");
//			   obj.put("FIT10102B", FIT10102B);
//			   obj.put("PT_10102", "/");
//			   obj.put("TT_10102", "/");
//			   obj.put("MR_10102", "/");
//			   obj.put("FIT10103A", "/");
//			   obj.put("FIT10103B", FIT10103B);
//			   obj.put("PT_10103", "/");
//			   obj.put("TT_10103", "/");
//			   obj.put("MR_10103", "/");
//			   obj.put("FIT10104A", "/");
//			   obj.put("FIT10104B", FIT10104B);
//			   obj.put("PT_10104", "/");
//			   obj.put("TT_10104", "/");
//			   obj.put("MR_10104", "/");
			
			obj.put("RPD_U2_LINE_MEASURE_ID", "");
			obj.put("REPORT_TIME", "班累积");
			obj.put("FIT_10109", "/");
			if(u2oils.size()==13 && u2oils.get(12)[3]!=null){
				obj.put("FIT10109Z", CommonsUtil.getRegulation0(u2oils.get(12)[3], u2oils.get(8)[3]));
			}else{
				obj.put("FIT10109Z", "");
			}
			
			obj.put("PT_10109", "/");
		    obj.put("TT_10109", "/");
		    obj.put("FIT10101A", "/");
		    if(u2oils.size()==13 && u2oils.get(12)[7]!=null){
		    	obj.put("FIT10101B",  CommonsUtil.getRegulation0(u2oils.get(12)[7], u2oils.get(8)[7]));
		    }else{
		    	obj.put("FIT10101B", "");
		    }
		   obj.put("PT_10101", "/");
		   obj.put("TT_10101", "/");
		   obj.put("MR_10101", "/");
		   obj.put("FIT10102A", "/");
		   if(u2oils.size()==13 && u2oils.get(12)[12]!=null){
			   obj.put("FIT10102B",  CommonsUtil.getRegulation0(u2oils.get(12)[12], u2oils.get(8)[12]));
		   }else{
			   obj.put("FIT10102B",  "");
		   }
		   obj.put("PT_10102", "/");
		   obj.put("TT_10102", "/");
		   obj.put("MR_10102", "/");
		   obj.put("FIT10103A", "/");
		   if(u2oils.size()==13 && u2oils.get(12)[17]!=null){
			   obj.put("FIT10103B",  CommonsUtil.getRegulation0(u2oils.get(12)[17], u2oils.get(8)[17]));
		   }else{
			   obj.put("FIT10103B", ""); 
		   }
		   obj.put("PT_10103", "/");
		   obj.put("TT_10103", "/");
		   obj.put("MR_10103", "/");
		   obj.put("FIT10104A", "/");
		   if(u2oils.size()==13 && u2oils.get(12)[22]!=null){
			   obj.put("FIT10104B",  CommonsUtil.getRegulation0(u2oils.get(12)[22], u2oils.get(8)[22]));
		   }else{
			   obj.put("FIT10104B",  "");
		   }
		   obj.put("PT_10104", "/");
		   obj.put("TT_10104", "/");
		   obj.put("MR_10104", "/");	   
			
			  
			  arr.add(obj);
			}else{

				for(String[] dateList:dates){
					obj = new JSONObject();
					for(String data:dateList){
					//	System.out.println(data);
						obj = new JSONObject();
						obj.put("RPD_U2_LINE_MEASURE_ID", "");
						obj.put("REPORT_TIME",  data.substring(11, 16));

					   obj.put("FIT_10109", "");
					   obj.put("FIT10109Z", "");
					   obj.put("PT_10109", "");
					   obj.put("TT_10109", "");
					   obj.put("FIT10101A", "");
					   obj.put("FIT10101B", "");
				  
					    obj.put("PT_10101", "");
						obj.put("TT_10101", "");
						obj.put("MR_10101", "");
						obj.put("FIT10102A", "");
						obj.put("FIT10102B","");
						  
						obj.put("PT_10102", "");
						obj.put("TT_10102", "");
						obj.put("MR_10102", "");
						obj.put("FIT10103A", "");
						obj.put("FIT10103B","");
						  
						obj.put("PT_10103", "");
						obj.put("TT_10103", "");
						obj.put("MR_10103", "");
						obj.put("FIT10104A", "");
						obj.put("FIT10104B","");
						obj.put("PT_10104", "");
						obj.put("TT_10104", "");
						obj.put("MR_10104", "");
						arr.add(obj);
					}
					 obj.put("RPD_U2_LINE_MEASURE_ID", "");
						obj.put("REPORT_TIME", "班累积");
						
						obj.put("FIT_10109", "/");
						  obj.put("FIT10109Z", "");
						obj.put("PT_10109", "/");
					    obj.put("TT_10109", "/");
					    obj.put("FIT10101A", "/");
					    obj.put("FIT10101B", "");
					   obj.put("PT_10101", "/");
					   obj.put("TT_10101", "/");
					   obj.put("MR_10101", "/");
					   obj.put("FIT10102A", "/");
					   obj.put("FIT10102B", "");
					   obj.put("PT_10102", "/");
					   obj.put("TT_10102", "/");
					   obj.put("MR_10102", "/");
					   obj.put("FIT10103A", "/");
					   obj.put("FIT10103B", "");
					   obj.put("PT_10103", "/");
					   obj.put("TT_10103", "/");
					   obj.put("MR_10103", "/");
					   obj.put("FIT10104A", "/");
					   obj.put("FIT10104B", "");
					   obj.put("PT_10104", "/");
					   obj.put("TT_10104", "/");
					   obj.put("MR_10104", "/");
					arr.add(obj);
				}
			
			}
		//}
	
		
		//jieshu
		if(u2oils != null && u2oils.size()>0){
		arr.remove(0);
		obj = new JSONObject();
	
//		FIT10109ZSUMday.remove(0);
//		FIT10101BSUMday.remove(0);
//		FIT10102BSUMday.remove(0);
//		FIT10103BSUMday.remove(0);
//		FIT10104BSUMday.remove(0);
		
		
		
		obj.put("RPD_U2_LINE_MEASURE_ID", "");
		obj.put("REPORT_TIME", "合计");
		obj.put("FIT_10109", "/");
		obj.put("FIT10109Z", CommonsUtil.getSumData0(FIT10109ZSUMday));
		obj.put("PT_10109", "/");
	    obj.put("TT_10109", "/");
	    obj.put("FIT10101A", "/");
	    obj.put("FIT10101B", CommonsUtil.getSumData0(FIT10101BSUMday));
	   obj.put("PT_10101", "/");
	   obj.put("TT_10101", "/");
	   obj.put("MR_10101", "/");
	   obj.put("FIT10102A", "/");
	   obj.put("FIT10102B", CommonsUtil.getSumData0(FIT10102BSUMday));
	   obj.put("PT_10102", "/");
	   obj.put("TT_10102", "/");
	   obj.put("MR_10102", "/");
	   obj.put("FIT10103A", "/");
	   obj.put("FIT10103B", CommonsUtil.getSumData0(FIT10103BSUMday));
	   obj.put("PT_10103", "/");
	   obj.put("TT_10103", "/");
	   obj.put("MR_10103", "/");
	   obj.put("FIT10104A", "/");
	   obj.put("FIT10104B", CommonsUtil.getSumData0(FIT10104BSUMday));
	   obj.put("PT_10104", "/");
	   obj.put("TT_10104", "/");
	   obj.put("MR_10104", "/");
	 
	   arr.add(obj);
		}else{
			obj = new JSONObject();
		
			obj.put("RPD_U2_LINE_MEASURE_ID", "");
			obj.put("REPORT_TIME", "合计");
			obj.put("FIT_10109", "/");
			obj.put("FIT10109Z", "");
			obj.put("PT_10109", "/");
		    obj.put("TT_10109", "/");
		    obj.put("FIT10101A", "/");
		    obj.put("FIT10101B", "");
		   obj.put("PT_10101", "/");
		   obj.put("TT_10101", "/");
		   obj.put("MR_10101", "/");
		   obj.put("FIT10102A", "/");
		   obj.put("FIT10102B", "");
		   obj.put("PT_10102", "/");
		   obj.put("TT_10102", "/");
		   obj.put("MR_10102", "/");
		   obj.put("FIT10103A", "/");
		   obj.put("FIT10103B", "");
		   obj.put("PT_10103", "/");
		   obj.put("TT_10103", "/");
		   obj.put("MR_10103", "/");
		   obj.put("FIT10104A", "/");
		   obj.put("FIT10104B", "");
		   obj.put("PT_10104", "/");
		   obj.put("TT_10104", "/");
		   obj.put("MR_10104", "/");
		 
		   arr.add(obj);
		}
	   return arr;	
		//
	
}

	@Override
	public List<Object[]> searchLineMeasure(String rq, String fields) throws Exception {

		String timeCalssql = "select tp.parameter_value from PC_CD_TRIGGERED_PROCESS_T tp where tp.parameter_name='union_station_rpd_start_time'";
		String calcNum = "-16";
		List<Object[]> dataSet = lineMeasureDao.searchU2OIL(timeCalssql);
		if(dataSet != null && dataSet.size()>0){
			calcNum = dataSet.get(0) + "";
		}
		String stratime =DateBean.getDynamicTime(calcNum, rq, "0");
		String endtime =DateBean.getDynamicTime(calcNum, rq, "1");
		String sql = "select " + fields + " from  PC_RPD_U2_LINE_MEASURE_T   u where u.report_time between TO_DATE('"+stratime+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+endtime+"','YYYY-MM-DD HH24:MI:SS') order by u.report_time";
		List<Object[]> yyList = lineMeasureDao.searchU2OIL(sql);
		return yyList;
	
	}
	
	public List<Object[]> searchLineN1tscs(String rq, String fields) throws Exception {

		String timeCalssql = "select tp.parameter_value from PC_CD_TRIGGERED_PROCESS_T tp where tp.parameter_name='union_station_rpd_start_time'";
		String calcNum = "-16";
		List<Object[]> dataSet = lineMeasureDao.searchU2OIL(timeCalssql);
		if(dataSet != null && dataSet.size()>0){
			calcNum = dataSet.get(0) + "";
		}
		String stratime =DateBean.getDynamicTime(calcNum, rq, "0");
		String endtime =DateBean.getDynamicTime(calcNum, rq, "1");
		String sql = "select " + fields + " from  PC_RPD_U1_DEHYDRATION_T   u where u.BBSJ between TO_DATE('"+stratime+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+endtime+"','YYYY-MM-DD HH24:MI:SS') order by u.BBSJ";
		List<Object[]> yyList = lineMeasureDao.searchU2OIL(sql);
		return yyList;
	
	}
	//分线计量2导出查询
	public List<Object[]> searchLineMeasure1(String rq, String fields) throws Exception {

		String timeCalssql = "select tp.parameter_value from PC_CD_TRIGGERED_PROCESS_T tp where tp.parameter_name='union_station_rpd_start_time'";
		String calcNum = "-16";
		List<Object[]> dataSet = lineMeasureDao.searchU2OIL(timeCalssql);
		if(dataSet != null && dataSet.size()>0){
			calcNum = dataSet.get(0) + "";
		}
		String stratime =DateBean.getDynamicTime(calcNum, rq, "0");
		String endtime =DateBean.getDynamicTime(calcNum, rq, "1");
		String sql = "select " + fields + " from   PC_RPD_U2_LINE_MEASURE2_T   u where u.ACQUISITION_TIME between TO_DATE('"+stratime+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+endtime+"','YYYY-MM-DD HH24:MI:SS') order by u.ACQUISITION_TIME";
		List<Object[]> yyList = lineMeasureDao.searchU2OIL(sql);
		return yyList;
	
	}
	@SuppressWarnings("unused")
	@Override
	public JSONArray searchLineMs(String rq) throws Exception {

		JSONObject obj = new JSONObject();
		JSONArray arr = new JSONArray();
		  
		  //2
		  String  FIT10110Z = "";
		  String  FIT10105B = "";
		  String  FIT10106B = "";
		  String  FIT10107B = "";
		  String  FIT10108B = "";
		  
		  List<String>   FIT10110ZSUM = new ArrayList<String>();
		  List<String>   FIT10105BSUM = new ArrayList<String>();
		  List<String>   FIT10106BSUM = new ArrayList<String>();
		  List<String>   FIT10107BSUM = new ArrayList<String>();
		  List<String>   FIT10108BSUM = new ArrayList<String>();
		  //合计
		  List<String> FIT10110ZSUMday = new ArrayList<String>();
		  List<String> FIT10105BSUMday = new ArrayList<String>();
		  List<String> FIT10106BSUMday = new ArrayList<String>();
		  List<String> FIT10107BSUMday = new ArrayList<String>();
		  List<String> FIT10108BSUMday = new ArrayList<String>();
		
		int dataflg = 0;  //数据存在标识  0：不存在， 1：存在
		String timeCalssql = "select tp.parameter_value from PC_CD_TRIGGERED_PROCESS_T tp where tp.parameter_name='union_station_rpd_start_time'";
		String calcNum = "-16";
		List<Object[]> dataSet = lineMeasureDao.searchLineM(timeCalssql);
		if(dataSet != null && dataSet.size()>0){
			calcNum = dataSet.get(0) + "";
		}
		
		String stratime =DateBean.getDynamicTime(calcNum, rq, "0");
		String endtime =DateBean.getDynamicTime(calcNum, rq, "1");
//		String sql1 ="select RPD_U2_LINE_MEASURE_ID,  to_char(report_time,'YYYY-MM-DD HH24:MI:SS')as report_time ,"+
//					"FIT_10109,  FIT10109Z,PT_10109, TT_10109, FIT10101A, FIT10101B,PT_10101,TT_10101,MR_10101,"+
//					"FIT10102A, FIT10102B, PT_10102,TT_10102,MR_10102,FIT10103A,FIT10103B,  PT_10103,TT_10103,"+
//					"MR_10103,FIT10104A,FIT10104B,PT_10104,TT_10104,MR_10104,"+
//					"FIT_10110,FIT10110Z, PT_10110, TT_10110, FIT10105A, FIT10105B, PT_10105, TT_10105, MR_10105,"+
//					"FIT10106A, FIT10106B, PT_10106, TT_10106, MR_10106, FIT10107A, FIT10107B,    PT_10107,"+
//					"TT_10107, MR_10107, FIT10108A, FIT10108B, PT_10108, TT_10108,"+
//					"MR_10108 from  PC_RPD_U2_LINE_MEASURE_T  where 1=1";
		
		
		String sql2 ="select RPD_U2_LINE_MEASURE_ID,  to_char(report_time,'YYYY-MM-DD HH24:MI:SS')as report_time ,"+
			"FIT_10110,FIT10110Z, PT_10110, TT_10110, FIT10105A, FIT10105B, PT_10105, TT_10105, MR_10105,"+
			"FIT10106A, FIT10106B, PT_10106, TT_10106, MR_10106, FIT10107A, FIT10107B,    PT_10107,"+
			"TT_10107, MR_10107, FIT10108A, FIT10108B, PT_10108, TT_10108,"+
			"MR_10108 from  PC_RPD_U2_LINE_MEASURE_T  where 1=1";
		sql2 += " and report_time between TO_DATE('"+stratime+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+endtime+"','YYYY-MM-DD HH24:MI:SS') order by report_time";

		Object[] u2oiltable = null;
			
		List<Object[]> u2oilss = lineMeasureDao.searchLineM(sql2);
		String[][] dates = DateBean.getReportTime("time", rq);
		if(u2oilss != null && u2oilss.size()>0){
//			for(String[] dateList:dates){
//				obj = new JSONObject();

			for (int i = 0; i < dates.length; i++) {
				String[] dateList = dates[i];
				obj = new JSONObject();
				  //er
				    
//			   FIT10110ZSUMday.add(Double.parseDouble(FIT10110Z));
//			   FIT10105BSUMday.add(Double.parseDouble(FIT10105B));
//			   FIT10106BSUMday.add(Double.parseDouble(FIT10106B));
//			   FIT10107BSUMday.add(Double.parseDouble(FIT10107B));
//			   FIT10108BSUMday.add(Double.parseDouble(FIT10108B));
				  
				   //er
				if (i == 2) {
					 JSONObject oobj =  (JSONObject) arr.get(5);

			   obj.put("RPD_U2_LINE_MEASURE_ID", "");
				obj.put("REPORT_TIME", "班累积");
				   	obj.put("FIT_10110", "/");
					 //26
				   	if(u2oilss.size()>8 && u2oilss.get(8) !=null){
				   	  obj.put("FIT10110Z",  CommonsUtil.getRegulation0(u2oilss.get(8)[3], u2oilss.get(4)[3]));
				   	}else{
				   	  obj.put("FIT10110Z",  "");
				   	}
					 obj.put("PT_10110", "/");
					 obj.put("TT_10110", "/");
					 obj.put("FIT10105A", "/");
					//30
					if(u2oilss.size()>8 && u2oilss.get(8) !=null){
					   obj.put("FIT10105B",   CommonsUtil.getRegulation0(u2oilss.get(8)[7], u2oilss.get(4)[7]));
					}else{
					   obj.put("FIT10105B",   "");
					}
					 obj.put("PT_10105", "/");
					 obj.put("TT_10105", "/");
					 obj.put("MR_10105", "/");
					 obj.put("FIT10106A", "/");
					//35
					 if(u2oilss.size()>8 && u2oilss.get(8) !=null){
						 obj.put("FIT10106B",  CommonsUtil.getRegulation0(u2oilss.get(8)[12], u2oilss.get(4)[12]));
					 }else{
						 obj.put("FIT10106B",  "");
					 }
					 obj.put("PT_10106", "/");
					 obj.put("TT_10106", "/");
					 obj.put("MR_10106", "/");
					 obj.put("FIT10107A", "/");
					 //40
					 if(u2oilss.size()>8 && u2oilss.get(8) !=null){
					   obj.put("FIT10107B",  CommonsUtil.getRegulation0(u2oilss.get(8)[17], u2oilss.get(4)[17]));
					 }else{
					   obj.put("FIT10107B",  "");
					 }
					 obj.put("PT_10107", "/");
					 obj.put("TT_10107", "/");
					 obj.put("MR_10107", "/");
					 obj.put("FIT10108A", "/");
					 //45
					 if(u2oilss.size()>8 && u2oilss.get(8) !=null){
						 obj.put("FIT10108B",  CommonsUtil.getRegulation0(u2oilss.get(8)[22], u2oilss.get(4)[22]));
					  }else{
							 obj.put("FIT10108B",  "");
					}
					  //obj.put(" FIT10108B",FIT10108B);
					 obj.put("PT_10108", "/");
					 obj.put("TT_10108", "/");
					 obj.put("MR_10108", "/");
				}else{
				    FIT10110Z = CommonsUtil.getBanSumData0(FIT10110ZSUM);
				    FIT10105B = CommonsUtil.getBanSumData0(FIT10105BSUM);
				    FIT10106B = CommonsUtil.getBanSumData0(FIT10106BSUM);
				    FIT10107B = CommonsUtil.getBanSumData0(FIT10107BSUM);
				    FIT10108B = CommonsUtil.getBanSumData0(FIT10108BSUM);
				    
					 obj.put("RPD_U2_LINE_MEASURE_ID", "");
						obj.put("REPORT_TIME", "班累积");
						   	obj.put("FIT_10110", "/");
							 //26
						    obj.put("FIT10110Z", FIT10110Z);
							 obj.put("PT_10110", "/");
							 obj.put("TT_10110", "/");
							 obj.put("FIT10105A", "/");
							//30
							  obj.put("FIT10105B", FIT10105B);
							 obj.put("PT_10105", "/");
							 obj.put("TT_10105", "/");
							 obj.put("MR_10105", "/");
							 obj.put("FIT10106A", "/");
							//35
							 obj.put("FIT10106B", FIT10106B);
							 obj.put("PT_10106", "/");
							 obj.put("TT_10106", "/");
							 obj.put("MR_10106", "/");
							 obj.put("FIT10107A", "/");
							 //40
							  obj.put("FIT10107B", FIT10107B);
							 obj.put("PT_10107", "/");
							 obj.put("TT_10107", "/");
							 obj.put("MR_10107", "/");
							 obj.put("FIT10108A", "/");
							 //45
							 if(FIT10108B !=null){
								 obj.put("FIT10108B",FIT10108B);
								 }else{
									 obj.put("FIT10108B",0.00);
								 }
							  //obj.put(" FIT10108B",FIT10108B);
							 obj.put("PT_10108", "/");
							 obj.put("TT_10108", "/");
							 obj.put("MR_10108", "/");
				}
				
				   arr.add(obj);
				  
				   //er
				   FIT10110ZSUM = new ArrayList<String>();
				   FIT10105BSUM = new ArrayList<String>();
				   FIT10106BSUM = new ArrayList<String>();
				   FIT10107BSUM = new ArrayList<String>();
				   FIT10108BSUM = new ArrayList<String>();
				
				for(String data:dateList){
				//	System.out.println(data);
					u2oiltable = null;
					dataflg = 0;
					
					for(Object[] u2oil:u2oilss){
						obj = new JSONObject();
						
						if(data.toString().equals(u2oil[1].toString())){
							dataflg = 1;
							u2oiltable = u2oil;
							//添加班累积值
							
							//er
							if(u2oil[3] != null && !"".equals(u2oil[3])){
								FIT10110ZSUMday.add(u2oil[3].toString());
							}else{
								FIT10110ZSUMday.add("");
							}
							if(u2oil[7] != null && !"".equals(u2oil[7])){
								FIT10105BSUMday.add(u2oil[7].toString());
							}else{
								FIT10105BSUMday.add("");
							}
							if(u2oil[12] != null && !"".equals(u2oil[12])){
								FIT10106BSUMday.add(u2oil[12].toString());
							}else{
								FIT10106BSUMday.add("");
							}
							if(u2oil[17] != null && !"".equals(u2oil[17])){
								FIT10107BSUMday.add(u2oil[17].toString());
							}else{
								FIT10107BSUMday.add("");
							}
							if(u2oil[22] != null && !"".equals(u2oil[22]) && !u2oil[22].equals("undefined") ){
								FIT10108BSUMday.add(u2oil[22].toString());
							}else{
								FIT10108BSUMday.add("");
							}
						}
					}
						if(dataflg == 1){
							
							
							//er
							obj.put("RPD_U2_LINE_MEASURE_ID", u2oiltable[0]);
							obj.put("REPORT_TIME",  data.substring(11, 16));
							obj.put("FIT_10110", u2oiltable[2]);
							 //26 
							if(u2oiltable[3] !=null){
					 		 obj.put("FIT10110Z", CommonsUtil.getIntData(u2oiltable[3]));
					 		FIT10110ZSUM.add(u2oiltable[3].toString());
							}else{
								obj.put("FIT10110Z", "");
								FIT10110ZSUM.add("");
							}
							 obj.put("PT_10110", CommonsUtil.getNotOneData(u2oiltable[4]));
							 obj.put("TT_10110", CommonsUtil.getIntData(u2oiltable[5]));
							 obj.put("FIT10105A", CommonsUtil.getIntData(u2oiltable[6]));
							//30
							 if( u2oiltable[7] !=null){
							 obj.put("FIT10105B",  CommonsUtil.getIntData(u2oiltable[7]));
							 FIT10105BSUM.add(u2oiltable[7].toString());
							 }else{
								 obj.put("FIT10105B","");
								 FIT10105BSUM.add("");
							 }
							   
							 obj.put("PT_10105", CommonsUtil.getNotOneData(u2oiltable[8]));
							 obj.put("TT_10105", CommonsUtil.getIntData(u2oiltable[9]));
							 obj.put("MR_10105", CommonsUtil.getNotOneData(u2oiltable[10]));
							 obj.put("FIT10106A", CommonsUtil.getIntData(u2oiltable[11]));
							//35
							 if(u2oiltable[12] !=null){
								 obj.put("FIT10106B",  CommonsUtil.getIntData(u2oiltable[12]));
								 FIT10106BSUM.add(u2oiltable[12].toString());
							 }else{
								 obj.put("FIT10106B", "");
								 FIT10106BSUM.add("");
							 }
							 
							  
							 obj.put("PT_10106", CommonsUtil.getNotOneData(u2oiltable[13]));
							 obj.put("TT_10106", CommonsUtil.getIntData(u2oiltable[14]));
							 obj.put("MR_10106", CommonsUtil.getNotOneData(u2oiltable[15]));
							 obj.put("FIT10107A", CommonsUtil.getIntData(u2oiltable[16]));
							 //40
							 if(u2oiltable[17] !=null){
							 obj.put("FIT10107B",  CommonsUtil.getIntData(u2oiltable[17]));
							 FIT10107BSUM.add(u2oiltable[17].toString());
							 }else{
								 obj.put("FIT10107B", "");
								 FIT10107BSUM.add("");
							 }
							   
							 obj.put("PT_10107", CommonsUtil.getNotOneData(u2oiltable[18]));
							 obj.put("TT_10107", CommonsUtil.getIntData(u2oiltable[19]));
							 obj.put("MR_10107", CommonsUtil.getNotOneData(u2oiltable[20]));
							 obj.put("FIT10108A", CommonsUtil.getIntData(u2oiltable[21]));
							 //45
							 if(u2oiltable[22] !=null && !u2oiltable[22].equals("") && !u2oiltable[22].equals("undefined")){
							 obj.put("FIT10108B",  CommonsUtil.getIntData(u2oiltable[22]));
							 FIT10108BSUM.add(u2oiltable[22].toString());
							 }else{
								 obj.put("FIT10108B", ""); 
								 FIT10108BSUM.add("");
							 }
							 
							 obj.put("PT_10108", CommonsUtil.getNotOneData(u2oiltable[23]));
							 obj.put("TT_10108", CommonsUtil.getIntData(u2oiltable[24]));
							 obj.put("MR_10108", CommonsUtil.getNotOneData(u2oiltable[25]));
							
						}else{
							
							obj.put("RPD_U2_LINE_MEASURE_ID", "");
							obj.put("REPORT_TIME",  data.substring(11, 16));
							//er
							obj.put("FIT_10110", "");
							 //26  
					 		 obj.put("FIT10110Z", "");
							  
							 obj.put("PT_10110", "");
							 obj.put("TT_10110", "");
							 obj.put("FIT10105A", "");
							//30
							 obj.put("FIT10105B", "");
							   
							 obj.put("PT_10105", "");
							 obj.put("TT_10105", "");
							 obj.put("MR_10105", "");
							 obj.put("FIT10106A", "");
							//35
							 obj.put("FIT10106B","");
							  
							 obj.put("PT_10106", "");
							 obj.put("TT_10106", "");
							 obj.put("MR_10106", "");
							 obj.put("FIT10107A", "");
							 //40
							 obj.put("FIT10107B", "");
							   
							 obj.put("PT_10107", "");
							 obj.put("TT_10107", "");
							 obj.put("MR_10107", "");
							 obj.put("FIT10108A", "");
							 //45
							 obj.put("FIT10108B", "");
							 
							 obj.put("PT_10108", "");
							 obj.put("TT_10108", "");
							 obj.put("MR_10108", "");

							     FIT10110ZSUM.add("");
							     FIT10105BSUM.add("");
							     FIT10106BSUM.add("");
							     FIT10107BSUM.add("");
							     FIT10108BSUM.add("");
							  //合计
							   FIT10110ZSUMday.add("");
							   FIT10105BSUMday.add("");
							   FIT10106BSUMday.add("");
							   FIT10107BSUMday.add("");
							   FIT10108BSUMday.add("");
						}
						arr.add(obj);
				//		System.out.println(obj);
					
					}
				
			}
			

			 
//			 FIT10110Z  =CommonsUtil.getSumData00(FIT10110ZSUMday);
//			 FIT10105B  =CommonsUtil.getSumData00(FIT10105BSUMday);
//			 FIT10106B  =CommonsUtil.getSumData00(FIT10106BSUMday);
//			 FIT10107B  =CommonsUtil.getSumData00(FIT10107BSUMday);
//			 FIT10108B  =CommonsUtil.getSumData00(FIT10108BSUMday);
//			   
//			   //er
//				 obj.put("RPD_U2_WATER_ID", "");
//				 obj.put("REPORT_TIME", "班累积");
//			   obj.put("FIT_10110", "/");
//			   obj.put("FIT10110Z", FIT10110Z);
//				 obj.put("PT_10110", "/");
//				 obj.put("TT_10110", "/");
//				 obj.put("FIT10105A", "/");
//				//30
//				 obj.put("FIT10105B",FIT10105B);
//				 obj.put("PT_10105", "/");
//				 obj.put("TT_10105", "/");
//				 obj.put("MR_10105", "/");
//				 obj.put("FIT10106A", "/");
//				//35
//				 obj.put("FIT10106B",FIT10106B);
//				 obj.put("PT_10106", "/");
//				 obj.put("TT_10106", "/");
//				 obj.put("MR_10106", "/");
//				 obj.put("FIT10107A", "/");
//				 //40
//				 obj.put("FIT10107B", FIT10107B);
//				 obj.put("PT_10107", "/");
//				 obj.put("TT_10107", "/");
//				 obj.put("MR_10107", "/");
//				 obj.put("FIT10108A", "/");
//				 //45
//				 if(FIT10108B !=null){
//				 obj.put("FIT10108B",FIT10108B);
//				 }else{
//					 obj.put("FIT10108B",0.00);
//				 }
//				 obj.put("PT_10108", "/");
//				 obj.put("TT_10108", "/");
//				 obj.put("MR_10108", "/");
	 
				 	obj.put("RPD_U2_LINE_MEASURE_ID", "");
					obj.put("REPORT_TIME", "班累积");
				   	obj.put("FIT_10110", "/");
					 //26
				   	if(u2oilss.size()==13 && u2oilss.get(12)[3] !=null){
				   	  obj.put("FIT10110Z",  CommonsUtil.getRegulation0(u2oilss.get(12)[3], u2oilss.get(8)[3]));
				   	}else{
				   	  obj.put("FIT10110Z",  "");
				   	}
					 obj.put("PT_10110", "/");
					 obj.put("TT_10110", "/");
					 obj.put("FIT10105A", "/");
					//30
					if(u2oilss.size()==13 && u2oilss.get(12)[7] !=null){
					   obj.put("FIT10105B",   CommonsUtil.getRegulation0(u2oilss.get(12)[7], u2oilss.get(8)[7]));
					}else{
					   obj.put("FIT10105B",   "");
					}
					 obj.put("PT_10105", "/");
					 obj.put("TT_10105", "/");
					 obj.put("MR_10105", "/");
					 obj.put("FIT10106A", "/");
					//35
					 if(u2oilss.size()==13 && u2oilss.get(12)[12] !=null){
						 obj.put("FIT10106B",  CommonsUtil.getRegulation0(u2oilss.get(12)[12], u2oilss.get(8)[12]));
					 }else{
						 obj.put("FIT10106B", "");
					 }
					 obj.put("PT_10106", "/");
					 obj.put("TT_10106", "/");
					 obj.put("MR_10106", "/");
					 obj.put("FIT10107A", "/");
					 //40
					 if(u2oilss.size()==13 && u2oilss.get(12)[17] !=null){
					   obj.put("FIT10107B",  CommonsUtil.getRegulation0(u2oilss.get(12)[17], u2oilss.get(8)[17]));
					 }else{
					   obj.put("FIT10107B",  "");
					 }
					 obj.put("PT_10107", "/");
					 obj.put("TT_10107", "/");
					 obj.put("MR_10107", "/");
					 obj.put("FIT10108A", "/");
					 //45
					 if(u2oilss.size()==13 && u2oilss.get(12)[22] !=null){
						 obj.put("FIT10108B",  CommonsUtil.getRegulation0(u2oilss.get(12)[22], u2oilss.get(8)[22]));
					  }else{
							 obj.put("FIT10108B",  "");
					}
					  //obj.put(" FIT10108B",FIT10108B);
					 obj.put("PT_10108", "/");
					 obj.put("TT_10108", "/");
					 obj.put("MR_10108", "/");
					   //er
			  arr.add(obj);
			}else{

				for(String[] dateList:dates){
					obj = new JSONObject();
					for(String data:dateList){
						obj = new JSONObject();
						obj.put("RPD_U2_LINE_MEASURE_ID", "");
						obj.put("REPORT_TIME",  data.substring(11, 16));
						//er
						obj.put("FIT_10110", "");
						 //26  
				 		 obj.put("FIT10110Z", "");
						  
						 obj.put("PT_10110", "");
						 obj.put("TT_10110", "");
						 obj.put("FIT10105A", "");
						//30
						 obj.put("FIT10105B", "");
						   
						 obj.put("PT_10105", "");
						 obj.put("TT_10105", "");
						 obj.put("MR_10105", "");
						 obj.put("FIT10106A", "");
						//35
						 obj.put("FIT10106B","");
						  
						 obj.put("PT_10106", "");
						 obj.put("TT_10106", "");
						 obj.put("MR_10106", "");
						 obj.put("FIT10107A", "");
						 //40
						 obj.put("FIT10107B", "");
						   
						 obj.put("PT_10107", "");
						 obj.put("TT_10107", "");
						 obj.put("MR_10107", "");
						 obj.put("FIT10108A", "");
						 //45
						 obj.put("FIT10108B", "");
						 
						 obj.put("PT_10108", "");
						 obj.put("TT_10108", "");
						 obj.put("MR_10108", "");
						arr.add(obj);
					}
					obj.put("RPD_U2_WATER_ID", "");
					 obj.put("REPORT_TIME", "班累积");
				   obj.put("FIT_10110", "/");
				   obj.put("FIT10110Z", "");
					 obj.put("PT_10110", "/");
					 obj.put("TT_10110", "/");
					 obj.put("FIT10105A", "/");
					//30
					 obj.put("FIT10105B","");
					 obj.put("PT_10105", "/");
					 obj.put("TT_10105", "/");
					 obj.put("MR_10105", "/");
					 obj.put("FIT10106A", "/");
					//35
					 obj.put("FIT10106B","");
					 obj.put("PT_10106", "/");
					 obj.put("TT_10106", "/");
					 obj.put("MR_10106", "/");
					 obj.put("FIT10107A", "/");
					 //40
					 obj.put("FIT10107B", "");
					 obj.put("PT_10107", "/");
					 obj.put("TT_10107", "/");
					 obj.put("MR_10107", "/");
					 obj.put("FIT10108A", "/");
					 //45
					 obj.put("FIT10108B","");
					 obj.put("PT_10108", "/");
					 obj.put("TT_10108", "/");
					 obj.put("MR_10108", "/");
					arr.add(obj);
				}
			
			}
	
		
		//jieshu
		if(u2oilss != null && u2oilss.size()>0){
		arr.remove(0);
		obj = new JSONObject();
		obj.put("RPD_U2_LINE_MEASURE_ID", "");
		obj.put("REPORT_TIME", "合计");
		//er
		
//		FIT10110ZSUMday.remove(0);
//		FIT10105BSUMday.remove(0);
//		FIT10106BSUMday.remove(0);
//		FIT10107BSUMday.remove(0);
//		FIT10108BSUMday.remove(0);
	 
	   //er
	
	   obj.put("FIT_10110", "/");
		 //26  
	   obj.put("FIT10110Z",CommonsUtil.getSumData0(FIT10110ZSUMday)); 
		  
		 obj.put("PT_10110", "/");
		 obj.put("TT_10110", "/");
		 obj.put("FIT10105A", "/");
		//30
		 obj.put("FIT10105B",CommonsUtil.getSumData0(FIT10105BSUMday)); 
		   
		 obj.put("PT_10105", "/");
		 obj.put("TT_10105", "/");
		 obj.put("MR_10105", "/");
		 obj.put("FIT10106A", "/");
		//35
		 obj.put("FIT10106B",CommonsUtil.getSumData0(FIT10106BSUMday)); 
		  
		 obj.put("PT_10106", "/");
		 obj.put("TT_10106", "/");
		 obj.put("MR_10106", "/");
		 obj.put("FIT10107A", "/");
		 //40
		 obj.put("FIT10107B", CommonsUtil.getSumData0(FIT10107BSUMday)); 
		   
		 obj.put("PT_10107", "/");
		 obj.put("TT_10107", "/");
		 obj.put("MR_10107", "/");
		
		 obj.put("FIT10108A", "/");
		 //45
		 obj.put("FIT10108B",CommonsUtil.getSumData0(FIT10108BSUMday)); 
		 obj.put("PT_10108", "/");
		 obj.put("TT_10108", "/");
		 obj.put("MR_10108", "/");
		 
	   arr.add(obj);
		}else{

			obj = new JSONObject();
			obj.put("RPD_U2_LINE_MEASURE_ID", "");
			obj.put("REPORT_TIME", "合计");
		
		   obj.put("FIT_10110", "/");
			 //26  
		   obj.put("FIT10110Z",""); 
			  
			 obj.put("PT_10110", "/");
			 obj.put("TT_10110", "/");
			 obj.put("FIT10105A", "/");
			//30
			 obj.put("FIT10105B",""); 
			   
			 obj.put("PT_10105", "/");
			 obj.put("TT_10105", "/");
			 obj.put("MR_10105", "/");
			 obj.put("FIT10106A", "/");
			//35
			 obj.put("FIT10106B",""); 
			  
			 obj.put("PT_10106", "/");
			 obj.put("TT_10106", "/");
			 obj.put("MR_10106", "/");
			 obj.put("FIT10107A", "/");
			 //40
			 obj.put("FIT10107B", ""); 
			   
			 obj.put("PT_10107", "/");
			 obj.put("TT_10107", "/");
			 obj.put("MR_10107", "/");
			
			 obj.put("FIT10108A", "/");
			 //45
			 obj.put("FIT10108B",""); 
			 obj.put("PT_10108", "/");
			 obj.put("TT_10108", "/");
			 obj.put("MR_10108", "/");
			 
		   arr.add(obj);
			
		}
	   return arr;	
		//
	
	}
	//分线计量2查找数据 map 2015-10-19
	public JSONArray searchLineMs1(String rq) throws Exception {

		JSONObject obj = new JSONObject();
		JSONArray arr = new JSONArray();
		  
		int dataflg = 0;  //数据存在标识  0：不存在， 1：存在
		  //合计
		  double FIT10110ZSUMday = 0.0;
		  double FIT10105BSUMday = 0.0;
		  double FIT10105OSUMday = 0.0;
		  double FIT10106BSUMday = 0.0;
		  double FIT10106OSUMday = 0.0;
		  double FIT10107BSUMday = 0.0;
		  double FIT10107OSUMday = 0.0;
		  double FIT10108BSUMday = 0.0;
		  double FIT10108OSUMday = 0.0;
		  double zongleiji1 = 0.0;
		  double zongleiji2 = 0.0;
		  double zongleiji3 = 0.0;
		  double zongleiji4 = 0.0;
		
		String timeCalssql = "select tp.parameter_value from PC_CD_TRIGGERED_PROCESS_T tp where tp.parameter_name='union_station_rpd_start_time'";
		String calcNum = "-16";
		List<Object[]> dataSet = lineMeasureDao.searchLineM(timeCalssql);
		if(dataSet != null && dataSet.size()>0){
			calcNum = dataSet.get(0) + "";
		}
		
		String stratime =DateBean.getDynamicTime(calcNum, rq, "0");
		String endtime =DateBean.getDynamicTime(calcNum, rq, "1");

		
		
		String sql2 ="select RPD_U2_LINE_MEASURE_ID,  to_char(ACQUISITION_TIME,'YYYY-MM-DD HH24:MI:SS')as ACQUISITION_TIME ,"+
			"FAV10112,FIT_HH_10110AV, PT_10110, TT_10110, FAV10105A, FIT_HH_10105AV, PT_10105, TT_10105, MT10105AV,OAV10105A,"+
			"FAV10106A, FIT_HH_10106AV, PT_10106, TT_10106, MT10106AV,OAV10106A, FAV10107A, FIT_HH_10107AV, PT_10107, TT_10107, MT10107AV,OAV10107A,"+
			"FAV10108A, FIT_HH_10108AV, PT_10108, TT_10108, MT10108AV,OAV10108A,"+
			"OAVMD_FXJL8 from  PC_RPD_U2_LINE_MEASURE2_T  where 1=1";
		sql2 += " and ACQUISITION_TIME between TO_DATE('"+stratime+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+endtime+"','YYYY-MM-DD HH24:MI:SS') order by ACQUISITION_TIME";

			
		List<Object[]> u2oilss = lineMeasureDao.searchLineM(sql2);
		String[][] dates = DateBean.getReportTime("time10", rq);
		if(u2oilss != null && u2oilss.size()>0){
			 
			for (int i = 0; i < dates.length; i++) {
				double   FIT10110ZSUM = 0.0;
				double   FIT10105BSUM = 0.0;
				double   FIT10105OSUM = 0.0;
				double   FIT10106BSUM = 0.0;
				double   FIT10106OSUM = 0.0;
				double   FIT10107BSUM = 0.0;
				double   FIT10107OSUM = 0.0;
				double   FIT10108BSUM = 0.0;
				double   FIT10108OSUM = 0.0; 
				double banleiji1 = 0.0;
				double banleiji2 = 0.0;
				double banleiji3 = 0.0;
				double banleiji4 = 0.0;
				String[] dateList = dates[i];
				obj = new JSONObject();
				for(int j=0;j<dateList.length;j++){
						dataflg = 0;
						obj = new JSONObject();
					for(Object[] u2oil:u2oilss){
						if(dateList[j].toString().equals(u2oil[1].toString())){
							dataflg = 1;
							obj.put("RPD_U2_LINE_MEASURE_ID", u2oil[0]);
							obj.put("REPORT_TIME",  dateList[j].substring(11, 16));
							obj.put("FAV10112",  CommonsUtil.getNotTwoDataZero(u2oil[2]));
					 		 obj.put("FIT_HH_10110AV", CommonsUtil.getNotTwoDataZero(u2oil[3]));
							 obj.put("PT_10110", CommonsUtil.getNotTwoDataZero(u2oil[4]));
							 obj.put("TT_10110", CommonsUtil.getZeroData(u2oil[5]));
							 obj.put("FAV10105A", CommonsUtil.getNotTwoDataZero(u2oil[6]));
							 obj.put("FIT_HH_10105AV",  CommonsUtil.getNotTwoDataZero(u2oil[7])); 
							 obj.put("PT_10105", CommonsUtil.getNotTwoDataZero(u2oil[8]));
							 obj.put("TT_10105", CommonsUtil.getZeroData(u2oil[9]));
							 obj.put("MT10105AV", CommonsUtil.getNotTwoDataZero(u2oil[10]));
							 obj.put("oilI", String.format("%.2f",CommonsUtil.getDoubleData(u2oil[11])*CommonsUtil.getDoubleData(u2oil[30])));
							 obj.put("FAV10106A", CommonsUtil.getNotTwoDataZero(u2oil[12]));
							 obj.put("FIT_HH_10106AV",  CommonsUtil.getNotTwoDataZero(u2oil[13]));
							 obj.put("PT_10106", CommonsUtil.getNotTwoDataZero(u2oil[14]));
							 obj.put("TT_10106", CommonsUtil.getZeroData(u2oil[15]));
							 obj.put("MT10106AV", CommonsUtil.getNotTwoDataZero(u2oil[16]));
							 obj.put("oilII", String.format("%.2f",CommonsUtil.getDoubleData(u2oil[17])*CommonsUtil.getDoubleData(u2oil[30])));
							 obj.put("FAV10107A",  CommonsUtil.getNotTwoDataZero(u2oil[18]));		   
							 obj.put("FIT_HH_10107AV", CommonsUtil.getNotTwoDataZero(u2oil[19]));
							 obj.put("PT_10107", CommonsUtil.getNotTwoDataZero(u2oil[20]));
							 obj.put("TT_10107", CommonsUtil.getZeroData(u2oil[21]));
							 obj.put("MT10107AV", CommonsUtil.getNotTwoDataZero(u2oil[22]));
							 obj.put("oilIII",  String.format("%.2f",CommonsUtil.getDoubleData(u2oil[23])*CommonsUtil.getDoubleData(u2oil[30])));							 
							 obj.put("FAV10108A", CommonsUtil.getNotTwoDataZero(u2oil[24]));
							 obj.put("FIT_HH_10108AV", CommonsUtil.getNotTwoDataZero(u2oil[25]));
							 obj.put("PT_10108", CommonsUtil.getNotTwoDataZero(u2oil[26]));
							 obj.put("TT_10108", CommonsUtil.getZeroData(u2oil[27]));
							 obj.put("MT10108AV", CommonsUtil.getNotTwoDataZero(u2oil[28]));
							 obj.put("oilIV", String.format("%.2f",CommonsUtil.getDoubleData(u2oil[29])*CommonsUtil.getDoubleData(u2oil[30])));
							//添加班累积值
							if(i!=0||j!=0){
								FIT10110ZSUM = CommonsUtil.getDoubleData(u2oil[3])+FIT10110ZSUM;
								FIT10105BSUM = CommonsUtil.getDoubleData(u2oil[7])+FIT10105BSUM;
								FIT10105OSUM = CommonsUtil.getDoubleData(u2oil[11])*CommonsUtil.getDoubleData(u2oil[30])+FIT10105OSUM;
								FIT10106BSUM = CommonsUtil.getDoubleData(u2oil[13])+FIT10106BSUM;
								FIT10106OSUM = CommonsUtil.getDoubleData(u2oil[17])*CommonsUtil.getDoubleData(u2oil[30])+FIT10106OSUM;
								FIT10107BSUM = CommonsUtil.getDoubleData(u2oil[19])+FIT10107BSUM;
								FIT10107OSUM = CommonsUtil.getDoubleData(u2oil[23])*CommonsUtil.getDoubleData(u2oil[30])+FIT10107OSUM;
								FIT10108BSUM = CommonsUtil.getDoubleData(u2oil[25])+FIT10108BSUM;
								FIT10108OSUM = CommonsUtil.getDoubleData(u2oil[29])*CommonsUtil.getDoubleData(u2oil[30])+FIT10108OSUM;
								banleiji1 = CommonsUtil.getDoubleData(u2oil[10])*CommonsUtil.getDoubleData(u2oil[7])+banleiji1;
								banleiji2 = CommonsUtil.getDoubleData(u2oil[16])*CommonsUtil.getDoubleData(u2oil[13])+banleiji2;
								banleiji3 = CommonsUtil.getDoubleData(u2oil[22])*CommonsUtil.getDoubleData(u2oil[19])+banleiji3;
								banleiji4 = CommonsUtil.getDoubleData(u2oil[28])*CommonsUtil.getDoubleData(u2oil[25])+banleiji4;
							}
						}
						
					}
						if(dataflg==0){
							obj.put("RPD_U2_LINE_MEASURE_ID", "");
							obj.put("REPORT_TIME",  dateList[j].substring(11, 16));
							obj.put("FAV10112", "");
					 		 obj.put("FIT_HH_10110AV", "");
							 obj.put("PT_10110", "");
							 obj.put("TT_10110", "");
							 obj.put("FAV10105A", "");
							 obj.put("FIT_HH_10105AV",  ""); 
							 obj.put("PT_10105", "");
							 obj.put("TT_10105", "");
							 obj.put("MT10105AV", "");
							 obj.put("oilI", "");
							 obj.put("FAV10106A", "");
							 obj.put("FIT_HH_10106AV",  "");
							 obj.put("PT_10106", "");
							 obj.put("TT_10106", "");
							 obj.put("MT10106AV", "");
							 obj.put("oilII", "");
							 obj.put("FAV10107A",  "");		   
							 obj.put("FIT_HH_10107AV", "");
							 obj.put("PT_10107", "");
							 obj.put("TT_10107", "");
							 obj.put("MT10107AV", "");
							 obj.put("oilIII", "");							 
							 obj.put("FAV10108A", "");
							 obj.put("FIT_HH_10108AV", "");
							 obj.put("PT_10108", "");
							 obj.put("TT_10108", "");
							 obj.put("MT10108AV", "");
							 obj.put("oilIV", "");
						}
						arr.add(obj);
				}
				obj.put("RPD_U2_LINE_MEASURE_ID", "");
				obj.put("REPORT_TIME",  "班累积");
				obj.put("FAV10112", "/");
		 		 obj.put("FIT_HH_10110AV", String.format("%.2f",FIT10110ZSUM));
				 obj.put("PT_10110", "/");
				 obj.put("TT_10110", "/");
				 obj.put("FAV10105A", "/");
				 obj.put("FIT_HH_10105AV",  String.format("%.2f",FIT10105BSUM)); 
				 obj.put("PT_10105", "/");
				 obj.put("TT_10105", "/");
				 if(FIT10106BSUM==0){
					 obj.put("MT10105AV", "--");
				 }else{
					 obj.put("MT10105AV", String.format("%.2f",banleiji1/FIT10105BSUM));
				 }
				 
				 obj.put("oilI", String.format("%.2f",FIT10105OSUM));
				 obj.put("FAV10106A", "/");
				 obj.put("FIT_HH_10106AV",  String.format("%.2f",FIT10106BSUM));
				 obj.put("PT_10106", "/");
				 obj.put("TT_10106", "/");
				 if(FIT10106BSUM==0){
					 obj.put("MT10106AV", "--");
				 }else{
					 obj.put("MT10106AV", String.format("%.2f",banleiji2/FIT10106BSUM));
				 }
				 
				 obj.put("oilII", String.format("%.2f",FIT10106OSUM));
				 obj.put("FAV10107A",  "/");		   
				 obj.put("FIT_HH_10107AV", String.format("%.2f",FIT10107BSUM));
				 obj.put("PT_10107", "/");
				 obj.put("TT_10107", "/");
				 if(FIT10107BSUM==0){
					 obj.put("MT10107AV", "--");
				 }else{
					 obj.put("MT10107AV", String.format("%.2f",banleiji3/FIT10107BSUM));
				 }
				 
				 obj.put("oilIII", String.format("%.2f",FIT10107OSUM));							 
				 obj.put("FAV10108A", "/");
				 obj.put("FIT_HH_10108AV", String.format("%.2f",FIT10108BSUM));
				 obj.put("PT_10108", "/");
				 obj.put("TT_10108", "/");
				 if(FIT10108BSUM==0){
					 obj.put("MT10108AV", "--");
				 }else{
					 obj.put("MT10108AV", String.format("%.2f",banleiji4/FIT10108BSUM));
				 }
				 
				 obj.put("oilIV", String.format("%.2f",FIT10108OSUM));
				 FIT10110ZSUMday = FIT10110ZSUM+FIT10110ZSUMday;
				 FIT10105BSUMday = FIT10105BSUM+FIT10105BSUMday;
				 FIT10105OSUMday = FIT10105OSUM+FIT10105OSUMday;
				 FIT10106BSUMday = FIT10106BSUM+FIT10106BSUMday;
				 FIT10106OSUMday = FIT10106OSUM+FIT10106OSUMday;
				 FIT10107BSUMday = FIT10107BSUM+FIT10107BSUMday;
				 FIT10107OSUMday = FIT10107OSUM+FIT10107OSUMday;
				 FIT10108BSUMday = FIT10108BSUM+FIT10108BSUMday;
				 FIT10108OSUMday = FIT10108OSUM+FIT10108OSUMday;
				 zongleiji1 = banleiji1+zongleiji1;
				 zongleiji2 = banleiji2+zongleiji2;
				 zongleiji3 = banleiji3+zongleiji3;
				 zongleiji4 = banleiji4+zongleiji4;
				 arr.add(obj);
				}

			 
			}else{

				for(String[] dateList:dates){
					obj = new JSONObject();
					for(String data:dateList){
						obj = new JSONObject();
						obj.put("RPD_U2_LINE_MEASURE_ID", "");
						obj.put("REPORT_TIME",  data.substring(11, 16));
						obj.put("FAV10112", "");
				 		 obj.put("FIT_HH_10110AV", "");
						 obj.put("PT_10110", "");
						 obj.put("TT_10110", "");
						 obj.put("FAV10105A", "");
						 obj.put("FIT_HH_10105AV",  ""); 
						 obj.put("PT_10105", "");
						 obj.put("TT_10105", "");
						 obj.put("MT10105AV", "");
						 obj.put("oilI", "");
						 obj.put("FAV10106A", "");
						 obj.put("FIT_HH_10106AV",  "");
						 obj.put("PT_10106", "");
						 obj.put("TT_10106", "");
						 obj.put("MT10106AV", "");
						 obj.put("oilII", "");
						 obj.put("FAV10107A",  "");		   
						 obj.put("FIT_HH_10107AV", "");
						 obj.put("PT_10107", "");
						 obj.put("TT_10107", "");
						 obj.put("MT10107AV", "");
						 obj.put("oilIII", "");							 
						 obj.put("FAV10108A", "");
						 obj.put("FIT_HH_10108AV", "");
						 obj.put("PT_10108", "");
						 obj.put("TT_10108", "");
						 obj.put("MT10108AV", "");
						 obj.put("oilIV", "");
						arr.add(obj);
					}
					obj.put("RPD_U2_LINE_MEASURE_ID", "");
					obj.put("REPORT_TIME",  "班累积");
					obj.put("FAV10112", "/");
			 		 obj.put("FIT_HH_10110AV", "/");
					 obj.put("PT_10110", "/");
					 obj.put("TT_10110", "/");
					 obj.put("FAV10105A", "/");
					 obj.put("FIT_HH_10105AV",  "/"); 
					 obj.put("PT_10105", "/");
					 obj.put("TT_10105", "/");
					 obj.put("MT10105AV", "/");
					 obj.put("oilI", "/");
					 obj.put("FAV10106A", "/");
					 obj.put("FIT_HH_10106AV",  "/");
					 obj.put("PT_10106", "/");
					 obj.put("TT_10106", "/");
					 obj.put("MT10106AV", "/");
					 obj.put("oilII", "/");
					 obj.put("FAV10107A",  "/");		   
					 obj.put("FIT_HH_10107AV", "/");
					 obj.put("PT_10107", "/");
					 obj.put("TT_10107", "/");
					 obj.put("MT10107AV", "/");
					 obj.put("oilIII", "/");							 
					 obj.put("FAV10108A", "/");
					 obj.put("FIT_HH_10108AV", "/");
					 obj.put("PT_10108", "/");
					 obj.put("TT_10108", "/");
					 obj.put("MT10108AV", "/");
					 obj.put("oilIV", "/");
					arr.add(obj);
				}
			
			}
	
		
		//jieshu
		if(u2oilss != null && u2oilss.size()>0){
			obj = new JSONObject();
			obj.put("RPD_U2_LINE_MEASURE_ID", "");
			obj.put("REPORT_TIME", "合计");
		
			obj.put("FAV10112", "/");
	 		 obj.put("FIT_HH_10110AV", String.format("%.2f",FIT10110ZSUMday));
			 obj.put("PT_10110", "/");
			 obj.put("TT_10110", "/");
			 obj.put("FAV10105A", "/");
			 obj.put("FIT_HH_10105AV",  String.format("%.2f",FIT10105BSUMday)); 
			 obj.put("PT_10105", "/");
			 obj.put("TT_10105", "/");
			 if(FIT10105BSUMday==0){
				 obj.put("MT10105AV", "--");
			 }else{
				 obj.put("MT10105AV", String.format("%.2f",zongleiji1/FIT10105BSUMday));
			 }
			 
			 obj.put("oilI", String.format("%.2f",FIT10105OSUMday));
			 obj.put("FAV10106A", "/");
			 obj.put("FIT_HH_10106AV",  String.format("%.2f",FIT10106BSUMday));
			 obj.put("PT_10106", "/");
			 obj.put("TT_10106", "/");
			 if(FIT10106BSUMday==0){
				 obj.put("MT10106AV", "--");
			 }else{
				 obj.put("MT10106AV", String.format("%.2f",zongleiji2/FIT10106BSUMday));
			 }
			 
			 obj.put("oilII", String.format("%.2f",FIT10106OSUMday));
			 obj.put("FAV10107A",  "/");		   
			 obj.put("FIT_HH_10107AV", String.format("%.2f",FIT10107BSUMday));
			 obj.put("PT_10107", "/");
			 obj.put("TT_10107", "/");
			 if(FIT10107BSUMday==0){
				 obj.put("MT10107AV", "--");
			 }else{
				 obj.put("MT10107AV", String.format("%.2f",zongleiji3/FIT10107BSUMday));
			 }
			 
			 obj.put("oilIII", String.format("%.2f",FIT10107OSUMday));							 
			 obj.put("FAV10108A", "/");
			 obj.put("FIT_HH_10108AV", String.format("%.2f",FIT10108BSUMday));
			 obj.put("PT_10108", "/");
			 obj.put("TT_10108", "/");
			 if(FIT10108BSUMday==0){
				 obj.put("MT10108AV", "--");
			 }else{
				 obj.put("MT10108AV", String.format("%.2f",zongleiji4/FIT10108BSUMday));
			 }
			
			 obj.put("oilIV", String.format("%.2f",FIT10108OSUMday));
		 
			 arr.add(obj);
		}else{

			obj = new JSONObject();
			obj.put("RPD_U2_LINE_MEASURE_ID", "");
			obj.put("REPORT_TIME", "合计");
		
			obj.put("FAV10112", "/");
	 		 obj.put("FIT_HH_10110AV", "/");
			 obj.put("PT_10110", "/");
			 obj.put("TT_10110", "/");
			 obj.put("FAV10105A", "/");
			 obj.put("FIT_HH_10105AV",  "/"); 
			 obj.put("PT_10105", "/");
			 obj.put("TT_10105", "/");
			 obj.put("MT10105AV", "/");
			 obj.put("oilI", "/");
			 obj.put("FAV10106A", "/");
			 obj.put("FIT_HH_10106AV",  "/");
			 obj.put("PT_10106", "/");
			 obj.put("TT_10106", "/");
			 obj.put("MT10106AV", "/");
			 obj.put("oilII", "/");
			 obj.put("FAV10107A",  "/");		   
			 obj.put("FIT_HH_10107AV", "/");
			 obj.put("PT_10107", "/");
			 obj.put("TT_10107", "/");
			 obj.put("MT10107AV", "/");
			 obj.put("oilIII", "/");							 
			 obj.put("FAV10108A", "/");
			 obj.put("FIT_HH_10108AV", "/");
			 obj.put("PT_10108", "/");
			 obj.put("TT_10108", "/");
			 obj.put("MT10108AV", "/");
			 obj.put("oilIV", "/");
			 
		   arr.add(obj);
			
			
		}
	   return arr;	
		//
	
	}
	public JSONArray searchLineM1(String rq) throws Exception {
		JSONObject obj = new JSONObject();
		JSONArray arr = new JSONArray();
		int dataflg = 0;  //数据存在标识  0：不存在， 1：存在

		  //合计
		  double FIT10110ZSUMday = 0.0;
		  double FIT10105BSUMday = 0.0;
		  double FIT10105OSUMday = 0.0;
		  double FIT10106BSUMday = 0.0;
		  double FIT10106OSUMday = 0.0;
		  double FIT10107BSUMday = 0.0;
		  double FIT10107OSUMday = 0.0;
		  double FIT10108BSUMday = 0.0;
		  double FIT10108OSUMday = 0.0;
		  double zongleiji1 = 0.0;
		  double zongleiji2 = 0.0;
		  double zongleiji3 = 0.0;
		  double zongleiji4 = 0.0;
		
		String timeCalssql = "select tp.parameter_value from PC_CD_TRIGGERED_PROCESS_T tp where tp.parameter_name='union_station_rpd_start_time'";
		String calcNum = "-16";
		List<Object[]> dataSet = lineMeasureDao.searchLineM(timeCalssql);
		if(dataSet != null && dataSet.size()>0){
			calcNum = dataSet.get(0) + "";
		}
		
		String stratime =DateBean.getDynamicTime(calcNum, rq, "0");
		String endtime =DateBean.getDynamicTime(calcNum, rq, "1");

		
		
		String sql2 ="select RPD_U2_LINE_MEASURE_ID,  to_char(ACQUISITION_TIME,'YYYY-MM-DD HH24:MI:SS')as ACQUISITION_TIME ,"+
			"FAV10111,FIT_HH_10109AV, PT_10109, TT_10109, FAV10101A, FIT_HH_10101AV, PT_10101, TT_10101, MT10101AV,OAV10101A,"+
			"FAV10102A, FIT_HH_10102AV, PT_10102, TT_10102, MT10102AV,OAV10102A, FAV10103A, FIT_HH_10103AV, PT_10103, TT_10103, MT10103AV,OAV10103A,"+
			"FAV10104A, FIT_HH_10104AV, PT_10104, TT_10104, MT10104AV,OAV10104A,"+
			"OAVMD_FXJL,OAVMD_FXJL3,OAVMD_FXJL4  from  PC_RPD_U2_LINE_MEASURE2_T  where 1=1";
		sql2 += " and ACQUISITION_TIME between TO_DATE('"+stratime+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+endtime+"','YYYY-MM-DD HH24:MI:SS') order by ACQUISITION_TIME";

			
		List<Object[]> u2oilss = lineMeasureDao.searchLineM(sql2);
		String[][] dates = DateBean.getReportTime("time10", rq);
		if(u2oilss != null && u2oilss.size()>0){
			 
			for (int i = 0; i < dates.length; i++) {
				double   FIT10110ZSUM = 0.0;
				double   FIT10105BSUM = 0.0;
				double   FIT10105OSUM = 0.0;
				double   FIT10106BSUM = 0.0;
				double   FIT10106OSUM = 0.0;
				double   FIT10107BSUM = 0.0;
				double   FIT10107OSUM = 0.0;
				double   FIT10108BSUM = 0.0;
				double   FIT10108OSUM = 0.0; 
				double banleiji1 = 0.0;
				double banleiji2 = 0.0;
				double banleiji3 = 0.0;
				double banleiji4 = 0.0;
				String[] dateList = dates[i];
				obj = new JSONObject();
				
				for(int j=0;j<dateList.length;j++){
					
					dataflg = 0;
					obj = new JSONObject();
				for(Object[] u2oil:u2oilss){
					if(dateList[j].toString().equals(u2oil[1].toString())){
						dataflg = 1;
						obj.put("RPD_U2_LINE_MEASURE_ID", u2oil[0]);
						obj.put("REPORT_TIME",  dateList[j].substring(11, 16));
						obj.put("FAV10112", CommonsUtil.getNotTwoDataZero(u2oil[2]));
				 		 obj.put("FIT_HH_10110AV", CommonsUtil.getNotTwoDataZero(u2oil[3]));
						 obj.put("PT_10110", CommonsUtil.getNotTwoDataZero(u2oil[4]));
						 obj.put("TT_10110", CommonsUtil.getZeroData(u2oil[5]));
						 obj.put("FAV10105A", CommonsUtil.getNotTwoDataZero(u2oil[6]));
						 obj.put("FIT_HH_10105AV",  CommonsUtil.getNotTwoDataZero(u2oil[7])); 
						 obj.put("PT_10105", CommonsUtil.getNotTwoDataZero(u2oil[8]));
						 obj.put("TT_10105", CommonsUtil.getZeroData(u2oil[9]));
						 obj.put("MT10105AV", CommonsUtil.getNotTwoDataZero(u2oil[10]));
						 obj.put("oilI", String.format("%.2f",CommonsUtil.getDoubleData(u2oil[11])*CommonsUtil.getDoubleData(u2oil[30])));
						 obj.put("FAV10106A", CommonsUtil.getNotTwoDataZero(u2oil[12]));
						 obj.put("FIT_HH_10106AV",  CommonsUtil.getNotTwoDataZero(u2oil[13]));
						 obj.put("PT_10106", CommonsUtil.getNotTwoDataZero(u2oil[14]));
						 obj.put("TT_10106", CommonsUtil.getZeroData(u2oil[15]));
						 obj.put("MT10106AV", CommonsUtil.getNotTwoDataZero(u2oil[16]));
						 obj.put("oilII", String.format("%.2f",CommonsUtil.getDoubleData(u2oil[17])*CommonsUtil.getDoubleData(u2oil[30])));
						 obj.put("FAV10107A",  CommonsUtil.getNotTwoDataZero(u2oil[18]));		   
						 obj.put("FIT_HH_10107AV", CommonsUtil.getNotTwoDataZero(u2oil[19]));
						 obj.put("PT_10107", CommonsUtil.getNotTwoDataZero(u2oil[20]));
						 obj.put("TT_10107", CommonsUtil.getZeroData(u2oil[21]));
						 obj.put("MT10107AV", CommonsUtil.getNotTwoDataZero(u2oil[22]));
						 obj.put("oilIII",  String.format("%.2f",CommonsUtil.getDoubleData(u2oil[23])*CommonsUtil.getDoubleData(u2oil[31])));							 
						 obj.put("FAV10108A", CommonsUtil.getNotTwoDataZero(u2oil[24]));
						 obj.put("FIT_HH_10108AV", CommonsUtil.getNotTwoDataZero(u2oil[25]));
						 obj.put("PT_10108", CommonsUtil.getNotTwoDataZero(u2oil[26]));
						 obj.put("TT_10108", CommonsUtil.getZeroData(u2oil[27]));
						 obj.put("MT10108AV", CommonsUtil.getNotTwoDataZero(u2oil[28]));
						 obj.put("oilIV", String.format("%.2f",CommonsUtil.getDoubleData(u2oil[29])*CommonsUtil.getDoubleData(u2oil[32])));
						//添加班累积值
						if(i!=0||j!=0){
							FIT10110ZSUM = CommonsUtil.getDoubleData(u2oil[3])+FIT10110ZSUM;
							FIT10105BSUM = CommonsUtil.getDoubleData(u2oil[7])+FIT10105BSUM;
							FIT10105OSUM = CommonsUtil.getDoubleData(u2oil[11])*CommonsUtil.getDoubleData(u2oil[30])+FIT10105OSUM;
							FIT10106BSUM = CommonsUtil.getDoubleData(u2oil[13])+FIT10106BSUM;
							FIT10106OSUM = CommonsUtil.getDoubleData(u2oil[17])*CommonsUtil.getDoubleData(u2oil[30])+FIT10106OSUM;
							FIT10107BSUM = CommonsUtil.getDoubleData(u2oil[19])+FIT10107BSUM;
							FIT10107OSUM = CommonsUtil.getDoubleData(u2oil[23])*CommonsUtil.getDoubleData(u2oil[31])+FIT10107OSUM;
							FIT10108BSUM = CommonsUtil.getDoubleData(u2oil[25])+FIT10108BSUM;
							FIT10108OSUM = CommonsUtil.getDoubleData(u2oil[29])*CommonsUtil.getDoubleData(u2oil[31])+FIT10108OSUM;
							banleiji1 = CommonsUtil.getDoubleData(u2oil[10])*CommonsUtil.getDoubleData(u2oil[7])+banleiji1;
							banleiji2 = CommonsUtil.getDoubleData(u2oil[16])*CommonsUtil.getDoubleData(u2oil[13])+banleiji2;
							banleiji3 = CommonsUtil.getDoubleData(u2oil[22])*CommonsUtil.getDoubleData(u2oil[19])+banleiji3;
							banleiji4 = CommonsUtil.getDoubleData(u2oil[28])*CommonsUtil.getDoubleData(u2oil[25])+banleiji4;
						}
					}
					
				}
					if(dataflg==0){
						obj.put("RPD_U2_LINE_MEASURE_ID", "");
						obj.put("REPORT_TIME",  dateList[j].substring(11, 16));
						obj.put("FAV10112", "");
				 		 obj.put("FIT_HH_10110AV", "");
						 obj.put("PT_10110", "");
						 obj.put("TT_10110", "");
						 obj.put("FAV10105A", "");
						 obj.put("FIT_HH_10105AV",  ""); 
						 obj.put("PT_10105", "");
						 obj.put("TT_10105", "");
						 obj.put("MT10105AV", "");
						 obj.put("oilI", "");
						 obj.put("FAV10106A", "");
						 obj.put("FIT_HH_10106AV",  "");
						 obj.put("PT_10106", "");
						 obj.put("TT_10106", "");
						 obj.put("MT10106AV", "");
						 obj.put("oilII", "");
						 obj.put("FAV10107A",  "");		   
						 obj.put("FIT_HH_10107AV", "");
						 obj.put("PT_10107", "");
						 obj.put("TT_10107", "");
						 obj.put("MT10107AV", "");
						 obj.put("oilIII", "");							 
						 obj.put("FAV10108A", "");
						 obj.put("FIT_HH_10108AV", "");
						 obj.put("PT_10108", "");
						 obj.put("TT_10108", "");
						 obj.put("MT10108AV", "");
						 obj.put("oilIV", "");
					}
					arr.add(obj);
			}
				obj.put("RPD_U2_LINE_MEASURE_ID", "");
				obj.put("REPORT_TIME",  "班累积");
				obj.put("FAV10112", "/");
		 		 obj.put("FIT_HH_10110AV", String.format("%.2f",FIT10110ZSUM));
				 obj.put("PT_10110", "/");
				 obj.put("TT_10110", "/");
				 obj.put("FAV10105A", "/");
				 obj.put("FIT_HH_10105AV",  String.format("%.2f",FIT10105BSUM)); 
				 obj.put("PT_10105", "/");
				 obj.put("TT_10105", "/");
				 if(FIT10105BSUM==0){
					 obj.put("MT10105AV", "--");
				 }else{
					 obj.put("MT10105AV", String.format("%.2f",banleiji1/FIT10105BSUM));
				 }
				 
				 obj.put("oilI", String.format("%.2f",FIT10105OSUM));
				 obj.put("FAV10106A", "/");
				 obj.put("FIT_HH_10106AV",  String.format("%.2f",FIT10106BSUM));
				 obj.put("PT_10106", "/");
				 obj.put("TT_10106", "/");
				 if(FIT10106BSUM==0){
					 obj.put("MT10106AV", "--");
				 }else{
					 obj.put("MT10106AV", String.format("%.2f",banleiji2/FIT10106BSUM));
				 }
				 
				 obj.put("oilII", String.format("%.2f",FIT10106OSUM));
				 obj.put("FAV10107A",  "/");		   
				 obj.put("FIT_HH_10107AV", String.format("%.2f",FIT10107BSUM));
				 obj.put("PT_10107", "/");
				 obj.put("TT_10107", "/");
				 if(FIT10107BSUM==0){
					 obj.put("MT10107AV", "--");
				 }else{
					 obj.put("MT10107AV", String.format("%.2f",banleiji3/FIT10107BSUM));
				 }
				 
				 obj.put("oilIII", String.format("%.2f",FIT10107OSUM));							 
				 obj.put("FAV10108A", "/");
				 obj.put("FIT_HH_10108AV", String.format("%.2f",FIT10108BSUM));
				 obj.put("PT_10108", "/");
				 obj.put("TT_10108", "/");
				 if(FIT10108BSUM==0){
					 obj.put("MT10108AV", "--");
				 }else{
					 obj.put("MT10108AV", String.format("%.2f",banleiji4/FIT10108BSUM));
				 }
				 
				 obj.put("oilIV", String.format("%.2f",FIT10108OSUM));
				 FIT10110ZSUMday = FIT10110ZSUM+FIT10110ZSUMday;
				 FIT10105BSUMday = FIT10105BSUM+FIT10105BSUMday;
				 FIT10105OSUMday = FIT10105OSUM+FIT10105OSUMday;
				 FIT10106BSUMday = FIT10106BSUM+FIT10106BSUMday;
				 FIT10106OSUMday = FIT10106OSUM+FIT10106OSUMday;
				 FIT10107BSUMday = FIT10107BSUM+FIT10107BSUMday;
				 FIT10107OSUMday = FIT10107OSUM+FIT10107OSUMday;
				 FIT10108BSUMday = FIT10108BSUM+FIT10108BSUMday;
				 FIT10108OSUMday = FIT10108OSUM+FIT10108OSUMday;
				 zongleiji1 = banleiji1+zongleiji1;
				 zongleiji2 = banleiji2+zongleiji2;
				 zongleiji3 = banleiji3+zongleiji3;
				 zongleiji4 = banleiji4+zongleiji4;
				 arr.add(obj);
				}

			 
			}else{

				for(String[] dateList:dates){
					obj = new JSONObject();
					for(String data:dateList){
						obj = new JSONObject();
						obj.put("RPD_U2_LINE_MEASURE_ID", "");
						obj.put("REPORT_TIME",  data.substring(11, 16));
						obj.put("FAV10112", "");
				 		 obj.put("FIT_HH_10110AV", "");
						 obj.put("PT_10110", "");
						 obj.put("TT_10110", "");
						 obj.put("FAV10105A", "");
						 obj.put("FIT_HH_10105AV",  ""); 
						 obj.put("PT_10105", "");
						 obj.put("TT_10105", "");
						 obj.put("MT10105AV", "");
						 obj.put("oilI", "");
						 obj.put("FAV10106A", "");
						 obj.put("FIT_HH_10106AV",  "");
						 obj.put("PT_10106", "");
						 obj.put("TT_10106", "");
						 obj.put("MT10106AV", "");
						 obj.put("oilII", "");
						 obj.put("FAV10107A",  "");		   
						 obj.put("FIT_HH_10107AV", "");
						 obj.put("PT_10107", "");
						 obj.put("TT_10107", "");
						 obj.put("MT10107AV", "");
						 obj.put("oilIII", "");							 
						 obj.put("FAV10108A", "");
						 obj.put("FIT_HH_10108AV", "");
						 obj.put("PT_10108", "");
						 obj.put("TT_10108", "");
						 obj.put("MT10108AV", "");
						 obj.put("oilIV", "");
						arr.add(obj);
					}
					obj.put("RPD_U2_LINE_MEASURE_ID", "");
					obj.put("REPORT_TIME",  "班累积");
					obj.put("FAV10112", "/");
			 		 obj.put("FIT_HH_10110AV", "/");
					 obj.put("PT_10110", "/");
					 obj.put("TT_10110", "/");
					 obj.put("FAV10105A", "/");
					 obj.put("FIT_HH_10105AV",  "/"); 
					 obj.put("PT_10105", "/");
					 obj.put("TT_10105", "/");
					 obj.put("MT10105AV", "/");
					 obj.put("oilI", "/");
					 obj.put("FAV10106A", "/");
					 obj.put("FIT_HH_10106AV",  "/");
					 obj.put("PT_10106", "/");
					 obj.put("TT_10106", "/");
					 obj.put("MT10106AV", "/");
					 obj.put("oilII", "/");
					 obj.put("FAV10107A",  "/");		   
					 obj.put("FIT_HH_10107AV", "/");
					 obj.put("PT_10107", "/");
					 obj.put("TT_10107", "/");
					 obj.put("MT10107AV", "/");
					 obj.put("oilIII", "/");							 
					 obj.put("FAV10108A", "/");
					 obj.put("FIT_HH_10108AV", "/");
					 obj.put("PT_10108", "/");
					 obj.put("TT_10108", "/");
					 obj.put("MT10108AV", "/");
					 obj.put("oilIV", "/");
					arr.add(obj);
				}
			
			}
	
		
		//jieshu
		if(u2oilss != null && u2oilss.size()>0){
			obj = new JSONObject();
			obj.put("RPD_U2_LINE_MEASURE_ID", "");
			obj.put("REPORT_TIME", "合计");
		
			obj.put("FAV10112", "/");
	 		 obj.put("FIT_HH_10110AV", String.format("%.2f",FIT10110ZSUMday));
			 obj.put("PT_10110", "/");
			 obj.put("TT_10110", "/");
			 obj.put("FAV10105A", "/");
			 obj.put("FIT_HH_10105AV",  String.format("%.2f",FIT10105BSUMday)); 
			 obj.put("PT_10105", "/");
			 obj.put("TT_10105", "/");
			 if(FIT10105BSUMday==0){
				 obj.put("MT10105AV", "--");
			 }else{
				 obj.put("MT10105AV", String.format("%.2f",zongleiji1/FIT10105BSUMday));
			 }
			 
			 obj.put("oilI", String.format("%.2f",FIT10105OSUMday));
			 obj.put("FAV10106A", "/");
			 obj.put("FIT_HH_10106AV",  String.format("%.2f",FIT10106BSUMday));
			 obj.put("PT_10106", "/");
			 obj.put("TT_10106", "/");
			 if(FIT10106BSUMday==0){
				 obj.put("MT10106AV", "--");
			 }else{
				 obj.put("MT10106AV", String.format("%.2f",zongleiji2/FIT10106BSUMday));
			 }
			 
			 obj.put("oilII", String.format("%.2f",FIT10106OSUMday));
			 obj.put("FAV10107A",  "/");		   
			 obj.put("FIT_HH_10107AV", String.format("%.2f",FIT10107BSUMday));
			 obj.put("PT_10107", "/");
			 obj.put("TT_10107", "/");
			 if(FIT10107BSUMday==0){
				 obj.put("MT10107AV", "--");
			 }else{
				 obj.put("MT10107AV", String.format("%.2f",zongleiji3/FIT10107BSUMday));
			 }
			 
			 obj.put("oilIII", String.format("%.2f",FIT10107OSUMday));							 
			 obj.put("FAV10108A", "/");
			 obj.put("FIT_HH_10108AV", String.format("%.2f",FIT10108BSUMday));
			 obj.put("PT_10108", "/");
			 obj.put("TT_10108", "/");
			 if(FIT10108BSUMday==0){
				 obj.put("MT10108AV", "--");
			 }else{
				 obj.put("MT10108AV", String.format("%.2f",zongleiji4/FIT10108BSUMday));
			 }
			
			 obj.put("oilIV", String.format("%.2f",FIT10108OSUMday));
		 
			 arr.add(obj);
		}else{

			obj = new JSONObject();
			obj.put("RPD_U2_LINE_MEASURE_ID", "");
			obj.put("REPORT_TIME", "合计");
		
			obj.put("FAV10112", "/");
	 		 obj.put("FIT_HH_10110AV", "/");
			 obj.put("PT_10110", "/");
			 obj.put("TT_10110", "/");
			 obj.put("FAV10105A", "/");
			 obj.put("FIT_HH_10105AV",  "/"); 
			 obj.put("PT_10105", "/");
			 obj.put("TT_10105", "/");
			 obj.put("MT10105AV", "/");
			 obj.put("oilI", "/");
			 obj.put("FAV10106A", "/");
			 obj.put("FIT_HH_10106AV",  "/");
			 obj.put("PT_10106", "/");
			 obj.put("TT_10106", "/");
			 obj.put("MT10106AV", "/");
			 obj.put("oilII", "/");
			 obj.put("FAV10107A",  "/");		   
			 obj.put("FIT_HH_10107AV", "/");
			 obj.put("PT_10107", "/");
			 obj.put("TT_10107", "/");
			 obj.put("MT10107AV", "/");
			 obj.put("oilIII", "/");							 
			 obj.put("FAV10108A", "/");
			 obj.put("FIT_HH_10108AV", "/");
			 obj.put("PT_10108", "/");
			 obj.put("TT_10108", "/");
			 obj.put("MT10108AV", "/");
			 obj.put("oilIV", "/");
			 
		   arr.add(obj);
			
		}
	   return arr;	
		//
	
}
	//1号站分线计量导出查询
		public List<Object[]> searchLineMeasureN1(String rq, String fields) throws Exception {

			String timeCalssql = "select tp.parameter_value from PC_CD_TRIGGERED_PROCESS_T tp where tp.parameter_name='union_station_rpd_start_time'";
			String calcNum = "-16";
			List<Object[]> dataSet = lineMeasureDao.searchU2OIL(timeCalssql);
			if(dataSet != null && dataSet.size()>0){
				calcNum = dataSet.get(0) + "";
			}
			String stratime =DateBean.getDynamicTime(calcNum, rq, "0");
			String endtime =DateBean.getDynamicTime(calcNum, rq, "1");
			String sql = "select " + fields + " from   PC_RPD_U1_LINE_MEASURE_T   u where u.ACQUISITION_TIME between TO_DATE('"+stratime+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+endtime+"','YYYY-MM-DD HH24:MI:SS') order by u.ACQUISITION_TIME";
			List<Object[]> yyList = lineMeasureDao.searchU2OIL(sql);
			return yyList;
		
		}
		
		//1号站分线计量查找数据 map 
		public JSONArray searchLineMsN1(String rq) throws Exception {

			JSONObject obj = new JSONObject();
			JSONArray arr = new JSONArray();
			  
			int dataflg = 0;  //数据存在标识  0：不存在， 1：存在
			  //合计
			  double FIT10110ZSUMday = 0.0;
			  double FIT10110OSUMday = 0.0;
			  double FIT10105BSUMday = 0.0;
			  double FIT10105OSUMday = 0.0;
			  double FIT10106BSUMday = 0.0;
			  double FIT10106OSUMday = 0.0;
			  double FIT10107BSUMday = 0.0;
			  double FIT10107OSUMday = 0.0;
			  double FIT10108BSUMday = 0.0;
			  double FIT10108OSUMday = 0.0;
			  double zongleiji1 = 0.0;
			  double zongleiji2 = 0.0;
			  double zongleiji3 = 0.0;
			  double zongleiji4 = 0.0;
			  double zongleiji5 = 0.0;
			String timeCalssql = "select tp.parameter_value from PC_CD_TRIGGERED_PROCESS_T tp where tp.parameter_name='union_station_rpd_start_time'";
			String calcNum = "-16";
			List<Object[]> dataSet = lineMeasureDao.searchLineM(timeCalssql);
			if(dataSet != null && dataSet.size()>0){
				calcNum = dataSet.get(0) + "";
			}
			
			String stratime =DateBean.getDynamicTime(calcNum, rq, "0");
			String endtime =DateBean.getDynamicTime(calcNum, rq, "1");

	
			
			String sql2 ="select RPD_U1_LINE_MEASURE_ID,  to_char(ACQUISITION_TIME,'YYYY-MM-DD HH24:MI:SS')as ACQUISITION_TIME ,"+
				"FT_AVE_007, FIQ_HH_107, PT007, TE007, FT_AVE_002, FIQ_HH_102, PT002, TE002, AT_AVE_002,OL_AVE_002,FT_AVE_005,FIQ_HH_105, PT005, TE005,AT_AVE_005,OL_AVE_005,"+
				"FT_AVE_003, FIQ_HH_103, PT003, TE003, AT_AVE_003,OL_AVE_003, "+
				"FT_AVE_001,FIQ_HH_101, PT001, TE001,AT_AVE_001,OL_AVE_001,AT_AVE_007,OL_AVE_007,"+
				"MD_FXJL from  PC_RPD_U1_LINE_MEASURE_T  where 1=1";
			sql2 += " and ACQUISITION_TIME between TO_DATE('"+stratime+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+endtime+"','YYYY-MM-DD HH24:MI:SS') order by ACQUISITION_TIME";

				
			List<Object[]> u2oilss = lineMeasureDao.searchLineM(sql2);
			String[][] dates = DateBean.getReportTime("time10", rq);
			if(u2oilss != null && u2oilss.size()>0){
				 
				for (int i = 0; i < dates.length; i++) {
					double   FIT10110ZSUM = 0.0;
					double   FIT10110OSUM = 0.0;
					double   FIT10105BSUM = 0.0;
					double   FIT10105OSUM = 0.0;
					double   FIT10106BSUM = 0.0;
					double   FIT10106OSUM = 0.0;
					double   FIT10107BSUM = 0.0;
					double   FIT10107OSUM = 0.0;
					double   FIT10108BSUM = 0.0;
					double   FIT10108OSUM = 0.0; 
					double banleiji1 = 0.0;
					double banleiji2 = 0.0;
					double banleiji3 = 0.0;
					double banleiji4 = 0.0;
					double banleiji5 = 0.0;
					String[] dateList = dates[i];
					obj = new JSONObject();
					for(int j=0;j<dateList.length;j++){
							dataflg = 0;
							obj = new JSONObject();
						for(Object[] u2oil:u2oilss){
							if(dateList[j].toString().equals(u2oil[1].toString())){
								dataflg = 1;
								obj.put("RPD_U1_LINE_MEASURE_ID", u2oil[0]);
								obj.put("REPORT_TIME",  dateList[j].substring(11, 16));
								obj.put("FAV10112", CommonsUtil.getNotTwoDataZero(u2oil[2]));
						 		 obj.put("FIT_HH_10110AV", CommonsUtil.getNotTwoDataZero(u2oil[3]));
								 obj.put("PT_10110", CommonsUtil.getNotTwoDataZero(u2oil[4]));
								 obj.put("TT_10110", CommonsUtil.getZeroData(u2oil[5]));
								 obj.put("FAV10105A", CommonsUtil.getNotTwoDataZero(u2oil[6]));
								 obj.put("FIT_HH_10105AV",  CommonsUtil.getNotTwoDataZero(u2oil[7])); 
								 obj.put("PT_10105", CommonsUtil.getNotTwoDataZero(u2oil[8]));
								 obj.put("TT_10105", CommonsUtil.getZeroData(u2oil[9]));
								 obj.put("MT10105AV", CommonsUtil.getNotTwoDataZero(u2oil[10]));
								 obj.put("oilI", String.format("%.2f",CommonsUtil.getDoubleData(u2oil[11])*CommonsUtil.getDoubleData(u2oil[32])));
								 obj.put("FAV10106A", CommonsUtil.getNotTwoDataZero(u2oil[12]));
								 obj.put("FIT_HH_10106AV",  CommonsUtil.getNotTwoDataZero(u2oil[13]));
								 obj.put("PT_10106", CommonsUtil.getNotTwoDataZero(u2oil[14]));
								 obj.put("TT_10106", CommonsUtil.getZeroData(u2oil[15]));
								 obj.put("MT10106AV", CommonsUtil.getNotTwoDataZero(u2oil[16]));
								 obj.put("oilII", String.format("%.2f",CommonsUtil.getDoubleData(u2oil[17])*CommonsUtil.getDoubleData(u2oil[32])));
//
								 //
								 //
								 //
								 
								 obj.put("FAV10107A",  CommonsUtil.getNotTwoDataZero(u2oil[18]));	
								 
								 
								 
							//	 System.out.println("###############"+CommonsUtil.getNotTwoDataZero(u2oil[18])+"@@@@@@@@@@@@");
								 obj.put("FIT_HH_10107AV", CommonsUtil.getNotTwoDataZero(u2oil[19]));
								 obj.put("PT_10107", CommonsUtil.getNotTwoDataZero(u2oil[20]));
								 obj.put("TT_10107", CommonsUtil.getZeroData(u2oil[21]));
								 obj.put("MT10107AV", CommonsUtil.getNotTwoDataZero(u2oil[22]));
								 obj.put("oilIII",  String.format("%.2f",CommonsUtil.getDoubleData(u2oil[23])*CommonsUtil.getDoubleData(u2oil[32])));							 
								 obj.put("FAV10108A", CommonsUtil.getNotTwoDataZero(u2oil[24]));
								 obj.put("FIT_HH_10108AV", CommonsUtil.getNotTwoDataZero(u2oil[25]));
								 obj.put("PT_10108", CommonsUtil.getNotTwoDataZero(u2oil[26]));
								 obj.put("TT_10108", CommonsUtil.getZeroData(u2oil[27]));
								 obj.put("MT10108AV", CommonsUtil.getNotTwoDataZero(u2oil[28]));
								 obj.put("oilIV", String.format("%.2f",CommonsUtil.getDoubleData(u2oil[29])*CommonsUtil.getDoubleData(u2oil[32])));
								 
								 obj.put("AT_AVE_001", CommonsUtil.getNotTwoDataZero(u2oil[30]));
								 
								 obj.put("oil", String.format("%.2f",CommonsUtil.getDoubleData(u2oil[31])*CommonsUtil.getDoubleData(u2oil[32])));
								//添加班累积值
								if(i!=0||j!=0){
									FIT10110ZSUM = CommonsUtil.getDoubleData(u2oil[3])+FIT10110ZSUM;
									FIT10110OSUM = CommonsUtil.getDoubleData(u2oil[31])*CommonsUtil.getDoubleData(u2oil[32])+FIT10110OSUM;
									FIT10105BSUM = CommonsUtil.getDoubleData(u2oil[7])+FIT10105BSUM;
									FIT10105OSUM = CommonsUtil.getDoubleData(u2oil[11])*CommonsUtil.getDoubleData(u2oil[32])+FIT10105OSUM;
									FIT10106BSUM = CommonsUtil.getDoubleData(u2oil[13])+FIT10106BSUM;
									FIT10106OSUM = CommonsUtil.getDoubleData(u2oil[17])*CommonsUtil.getDoubleData(u2oil[32])+FIT10106OSUM;
									FIT10107BSUM = CommonsUtil.getDoubleData(u2oil[19])+FIT10107BSUM;
									FIT10107OSUM = CommonsUtil.getDoubleData(u2oil[23])*CommonsUtil.getDoubleData(u2oil[32])+FIT10107OSUM;
									FIT10108BSUM = CommonsUtil.getDoubleData(u2oil[25])+FIT10108BSUM;
									FIT10108OSUM = CommonsUtil.getDoubleData(u2oil[29])*CommonsUtil.getDoubleData(u2oil[32])+FIT10108OSUM;
									//班累计是累计流量*含水
									banleiji2 = CommonsUtil.getDoubleData(u2oil[10])*CommonsUtil.getDoubleData(u2oil[7])+banleiji2;
									banleiji3 = CommonsUtil.getDoubleData(u2oil[16])*CommonsUtil.getDoubleData(u2oil[13])+banleiji3;
									banleiji4 = CommonsUtil.getDoubleData(u2oil[22])*CommonsUtil.getDoubleData(u2oil[19])+banleiji4;
									banleiji5 = CommonsUtil.getDoubleData(u2oil[28])*CommonsUtil.getDoubleData(u2oil[25])+banleiji5;
									banleiji1 = CommonsUtil.getDoubleData(u2oil[30])*CommonsUtil.getDoubleData(u2oil[3])+banleiji1;
								}
							}
							
						}
							if(dataflg==0){
								obj.put("RPD_U1_LINE_MEASURE_ID", "");
								obj.put("REPORT_TIME",  dateList[j].substring(11, 16));
								obj.put("FAV10112", "");
						 		 obj.put("FIT_HH_10110AV", "");
								 obj.put("PT_10110", "");
								 obj.put("TT_10110", "");
								 obj.put("FAV10105A", "");
								 obj.put("FIT_HH_10105AV",  ""); 
								 obj.put("PT_10105", "");
								 obj.put("TT_10105", "");
								 obj.put("MT10105AV", "");
								 obj.put("oilI", "");
								 obj.put("FAV10106A", "");
								 obj.put("FIT_HH_10106AV",  "");
								 obj.put("PT_10106", "");
								 obj.put("TT_10106", "");
								 obj.put("MT10106AV", "");
								 obj.put("oilII", "");
								 obj.put("FAV10107A",  "");		   
								 obj.put("FIT_HH_10107AV", "");
								 obj.put("PT_10107", "");
								 obj.put("TT_10107", "");
								 obj.put("MT10107AV", "");
								 obj.put("oilIII", "");							 
								 obj.put("FAV10108A", "");
								 obj.put("FIT_HH_10108AV", "");
								 obj.put("PT_10108", "");
								 obj.put("TT_10108", "");
								 obj.put("MT10108AV", "");
								 obj.put("oilIV", "");
								 obj.put("AT_AVE_001", "");
								 obj.put("oil", "");
							}
							arr.add(obj);
					}
					obj.put("RPD_U1_LINE_MEASURE_ID", "");
					obj.put("REPORT_TIME",  "班累积");
					obj.put("FAV10112", "/");
			 		 obj.put("FIT_HH_10110AV", String.format("%.2f",FIT10110ZSUM));
					 obj.put("PT_10110", "/");
					 obj.put("TT_10110", "/");
					 obj.put("FAV10105A", "/");
					 obj.put("FIT_HH_10105AV",  String.format("%.2f",FIT10105BSUM)); 
					 obj.put("PT_10105", "/");
					 obj.put("TT_10105", "/");
					 if(FIT10105BSUM==0){
						 obj.put("MT10105AV", "--");
					 }else{
						 obj.put("MT10105AV", String.format("%.2f",banleiji2/FIT10105BSUM));
					 }

					 obj.put("oilI", String.format("%.2f",FIT10105OSUM));
					 obj.put("FAV10106A", "/");
					 obj.put("FIT_HH_10106AV",  String.format("%.2f",FIT10106BSUM));
					 obj.put("PT_10106", "/");
					 obj.put("TT_10106", "/");
					 if(FIT10106BSUM==0){
						 obj.put("MT10106AV", "--");
					 }else{
						 obj.put("MT10106AV", String.format("%.2f",banleiji3/FIT10106BSUM));
					 }

					 obj.put("oilII", String.format("%.2f",FIT10106OSUM));
					 obj.put("FAV10107A",  "/");		   
					 obj.put("FIT_HH_10107AV", String.format("%.2f",FIT10107BSUM));
					 obj.put("PT_10107", "/");
					 obj.put("TT_10107", "/");
					 if(FIT10107BSUM==0){
						 obj.put("MT10107AV", "--");
					 }else{
						 obj.put("MT10107AV", String.format("%.2f",banleiji4/FIT10107BSUM));
					 }

					 obj.put("oilIII", String.format("%.2f",FIT10107OSUM));							 
					 obj.put("FAV10108A", "/");
					 obj.put("FIT_HH_10108AV", String.format("%.2f",FIT10108BSUM));
					 obj.put("PT_10108", "/");
					 obj.put("TT_10108", "/");
					 if(FIT10108BSUM==0){
						 obj.put("MT10108AV", "--");
					 }else{
						 obj.put("MT10108AV", String.format("%.2f",banleiji4/FIT10108BSUM));
					 }

					 obj.put("oilIV", String.format("%.2f",FIT10108OSUM));
					 if(FIT10110ZSUM==0){
						 obj.put("AT_AVE_001", "--");
					 }else{
						 obj.put("AT_AVE_001", String.format("%.2f",banleiji1/FIT10110ZSUM));
					 }

					 obj.put("oil", String.format("%.2f",FIT10110OSUM));
					 FIT10110ZSUMday = FIT10110ZSUM+FIT10110ZSUMday;
					 FIT10110OSUMday = FIT10110OSUM+FIT10110OSUMday;
					 FIT10105BSUMday = FIT10105BSUM+FIT10105BSUMday;
					 FIT10105OSUMday = FIT10105OSUM+FIT10105OSUMday;
					 FIT10106BSUMday = FIT10106BSUM+FIT10106BSUMday;
					 FIT10106OSUMday = FIT10106OSUM+FIT10106OSUMday;
					 FIT10107BSUMday = FIT10107BSUM+FIT10107BSUMday;
					 FIT10107OSUMday = FIT10107OSUM+FIT10107OSUMday;
					 FIT10108BSUMday = FIT10108BSUM+FIT10108BSUMday;
					 FIT10108OSUMday = FIT10108OSUM+FIT10108OSUMday;
					 zongleiji1 = banleiji1+zongleiji1;
					 zongleiji2 = banleiji2+zongleiji2;
					 zongleiji3 = banleiji3+zongleiji3;
					 zongleiji4 = banleiji4+zongleiji4;
					 zongleiji5 = banleiji5+zongleiji5;
					 arr.add(obj);
					}

				 
				}else{

					for(String[] dateList:dates){
						obj = new JSONObject();
						for(String data:dateList){
							obj = new JSONObject();
							obj.put("RPD_U1_LINE_MEASURE_ID", "");
							obj.put("REPORT_TIME",  data.substring(11, 16));
							obj.put("FAV10112", "");
					 		 obj.put("FIT_HH_10110AV", "");
							 obj.put("PT_10110", "");
							 obj.put("TT_10110", "");
							 obj.put("FAV10105A", "");
							 obj.put("FIT_HH_10105AV",  ""); 
							 obj.put("PT_10105", "");
							 obj.put("TT_10105", "");
							 obj.put("MT10105AV", "");
							 obj.put("oilI", "");
							 obj.put("FAV10106A", "");
							 obj.put("FIT_HH_10106AV",  "");
							 obj.put("PT_10106", "");
							 obj.put("TT_10106", "");
							 obj.put("MT10106AV", "");
							 obj.put("oilII", "");
							 obj.put("FAV10107A",  "");		   
							 obj.put("FIT_HH_10107AV", "");
							 obj.put("PT_10107", "");
							 obj.put("TT_10107", "");
							 obj.put("MT10107AV", "");
							 obj.put("oilIII", "");							 
							 obj.put("FAV10108A", "");
							 obj.put("FIT_HH_10108AV", "");
							 obj.put("PT_10108", "");
							 obj.put("TT_10108", "");
							 obj.put("MT10108AV", "");
							 obj.put("oilIV", "");
							 obj.put("AT_AVE_001", "");
							 obj.put("oil", "");
							arr.add(obj);
						}
						obj.put("RPD_U1_LINE_MEASURE_ID", "");
						obj.put("REPORT_TIME",  "班累积");
						obj.put("FAV10112", "/");
				 		 obj.put("FIT_HH_10110AV", "/");
						 obj.put("PT_10110", "/");
						 obj.put("TT_10110", "/");
						 obj.put("FAV10105A", "/");
						 obj.put("FIT_HH_10105AV",  "/"); 
						 obj.put("PT_10105", "/");
						 obj.put("TT_10105", "/");
						 obj.put("MT10105AV", "/");
						 obj.put("oilI", "/");
						 obj.put("FAV10106A", "/");
						 obj.put("FIT_HH_10106AV",  "/");
						 obj.put("PT_10106", "/");
						 obj.put("TT_10106", "/");
						 obj.put("MT10106AV", "/");
						 obj.put("oilII", "/");
						 obj.put("FAV10107A",  "/");		   
						 obj.put("FIT_HH_10107AV", "/");
						 obj.put("PT_10107", "/");
						 obj.put("TT_10107", "/");
						 obj.put("MT10107AV", "/");
						 obj.put("oilIII", "/");							 
						 obj.put("FAV10108A", "/");
						 obj.put("FIT_HH_10108AV", "/");
						 obj.put("PT_10108", "/");
						 obj.put("TT_10108", "/");
						 obj.put("MT10108AV", "/");
						 obj.put("oilIV", "/");
						 obj.put("AT_AVE_001", "/");
						 obj.put("oil", "/");
						arr.add(obj);
					}
				
				}
		
			
			//jieshu
			if(u2oilss != null && u2oilss.size()>0){
				obj = new JSONObject();
				obj.put("RPD_U1_LINE_MEASURE_ID", "");
				obj.put("REPORT_TIME", "合计");
			
				obj.put("FAV10112", "/");
		 		 obj.put("FIT_HH_10110AV", String.format("%.2f",FIT10110ZSUMday));
				 obj.put("PT_10110", "/");
				 obj.put("TT_10110", "/");
				 obj.put("FAV10105A", "/");
				 obj.put("FIT_HH_10105AV",  String.format("%.2f",FIT10105BSUMday)); 
				 obj.put("PT_10105", "/");
				 obj.put("TT_10105", "/");
				 if(FIT10105BSUMday==0){
					 obj.put("MT10105AV", "--");
				 }else{
					 obj.put("MT10105AV", String.format("%.2f",zongleiji2/FIT10105BSUMday));
				 }

				 obj.put("oilI", String.format("%.2f",FIT10105OSUMday));
				 obj.put("FAV10106A", "/");
				 obj.put("FIT_HH_10106AV",  String.format("%.2f",FIT10106BSUMday));
				 obj.put("PT_10106", "/");
				 obj.put("TT_10106", "/");
				 if(FIT10106BSUMday==0){
					 obj.put("MT10106AV", "--");
				 }else{
					 obj.put("MT10106AV", String.format("%.2f",zongleiji3/FIT10106BSUMday));
				 }

				 obj.put("oilII", String.format("%.2f",FIT10106OSUMday));
				 obj.put("FAV10107A",  "/");		   
				 obj.put("FIT_HH_10107AV", String.format("%.2f",FIT10107BSUMday));
				 obj.put("PT_10107", "/");
				 obj.put("TT_10107", "/");
				 if(FIT10107BSUMday==0){
					 obj.put("MT10107AV", "--");
				 }else{
					 obj.put("MT10107AV", String.format("%.2f",zongleiji4/FIT10107BSUMday));
				 }

				 obj.put("oilIII", String.format("%.2f",FIT10107OSUMday));							 
				 obj.put("FAV10108A", "/");
				 obj.put("FIT_HH_10108AV", String.format("%.2f",FIT10108BSUMday));
				 obj.put("PT_10108", "/");
				 obj.put("TT_10108", "/");
				 if(FIT10108BSUMday==0){
					 obj.put("MT10108AV", "--");
				 }else{
					 obj.put("MT10108AV", String.format("%.2f",zongleiji5/FIT10108BSUMday));
				 }

				 obj.put("oilIV", String.format("%.2f",FIT10108OSUMday));
				 if(FIT10110ZSUMday==0){
					 obj.put("AT_AVE_001", "--");
				 }else{
					 obj.put("AT_AVE_001", String.format("%.2f",zongleiji1/FIT10110ZSUMday));
				 }
				 obj.put("oil", String.format("%.2f",FIT10110OSUMday));
				 arr.add(obj);
			}else{

				obj = new JSONObject();
				obj.put("RPD_U1_LINE_MEASURE_ID", "");
				obj.put("REPORT_TIME", "合计");
			
				obj.put("FAV10112", "/");
		 		 obj.put("FIT_HH_10110AV", "/");
				 obj.put("PT_10110", "/");
				 obj.put("TT_10110", "/");
				 obj.put("FAV10105A", "/");
				 obj.put("FIT_HH_10105AV",  "/"); 
				 obj.put("PT_10105", "/");
				 obj.put("TT_10105", "/");
				 obj.put("MT10105AV", "/");
				 obj.put("oilI", "/");
				 obj.put("FAV10106A", "/");
				 obj.put("FIT_HH_10106AV",  "/");
				 obj.put("PT_10106", "/");
				 obj.put("TT_10106", "/");
				 obj.put("MT10106AV", "/");
				 obj.put("oilII", "/");
				 obj.put("FAV10107A",  "/");		   
				 obj.put("FIT_HH_10107AV", "/");
				 obj.put("PT_10107", "/");
				 obj.put("TT_10107", "/");
				 obj.put("MT10107AV", "/");
				 obj.put("oilIII", "/");							 
				 obj.put("FAV10108A", "/");
				 obj.put("FIT_HH_10108AV", "/");
				 obj.put("PT_10108", "/");
				 obj.put("TT_10108", "/");
				 obj.put("MT10108AV", "/");
				 obj.put("oilIV", "/");
				 obj.put("AT_AVE_001", "/");
				 obj.put("oil", "/");
			   arr.add(obj);
				
			}
		   return arr;	
			//
		
		}
		public JSONArray searchLineMN1(String rq) throws Exception {
			JSONObject obj = new JSONObject();
			JSONArray arr = new JSONArray();
			  
			int dataflg = 0;  //数据存在标识  0：不存在， 1：存在
			  //合计
			  double FIT10110ZSUMday = 0.0;
			  double FIT10110OSUMday = 0.0;
			  double FIT10105BSUMday = 0.0;
			  double FIT10105OSUMday = 0.0;
			  double FIT10106BSUMday = 0.0;
			  double FIT10106OSUMday = 0.0;
			  double FIT10107BSUMday = 0.0;
			  double FIT10107OSUMday = 0.0;
			  double zongleiji1 = 0.0;
			  double zongleiji2 = 0.0;
			  double zongleiji3 = 0.0;
			  double zongleiji4 = 0.0;
			
			String timeCalssql = "select tp.parameter_value from PC_CD_TRIGGERED_PROCESS_T tp where tp.parameter_name='union_station_rpd_start_time'";
			String calcNum = "-16";
			List<Object[]> dataSet = lineMeasureDao.searchLineM(timeCalssql);
			if(dataSet != null && dataSet.size()>0){
				calcNum = dataSet.get(0) + "";
			}
			
			String stratime =DateBean.getDynamicTime(calcNum, rq, "0");
			String endtime =DateBean.getDynamicTime(calcNum, rq, "1");

			
			
			String sql2 ="select RPD_U1_LINE_MEASURE_ID,  to_char(ACQUISITION_TIME,'YYYY-MM-DD HH24:MI:SS')as ACQUISITION_TIME ,"+
				" FT_AVE_006, FIQ_HH_106, PT006, TE006, FT_AVE_008, FIQ_HH_108, PT008, TE008, AT_AVE_008,OL_AVE_008,"+
				" FT_AVE_004, FIQ_HH_104, PT004, TE004, AT_AVE_004,OL_AVE_004,FT_AVE_009, FIQ_HH_109, PT009, TE009, AT_AVE_009,OL_AVE_009,"+
				"AT_AVE_006,OL_AVE_006,"+
				"MD_FXJL,MD_FXJL2 from  PC_RPD_U1_LINE_MEASURE_T  where 1=1";
			sql2 += " and ACQUISITION_TIME between TO_DATE('"+stratime+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+endtime+"','YYYY-MM-DD HH24:MI:SS') order by ACQUISITION_TIME";

				
			List<Object[]> u2oilss = lineMeasureDao.searchLineM(sql2);
			String[][] dates = DateBean.getReportTime("time10", rq);
			if(u2oilss != null && u2oilss.size()>0){
				 
				for (int i = 0; i < dates.length; i++) {
					double   FIT10110ZSUM = 0.0;
					double   FIT10110OSUM = 0.0;
					double   FIT10105BSUM = 0.0;
					double   FIT10105OSUM = 0.0;
					double   FIT10106BSUM = 0.0;
					double   FIT10106OSUM = 0.0;
					double   FIT10107BSUM = 0.0;
					double   FIT10107OSUM = 0.0;
					double banleiji1 = 0.0;
					double banleiji2 = 0.0;
					double banleiji3 = 0.0;
					double banleiji4 = 0.0;
					String[] dateList = dates[i];
					obj = new JSONObject();
					for(int j=0;j<dateList.length;j++){
							dataflg = 0;
							obj = new JSONObject();
						for(Object[] u2oil:u2oilss){
							if(dateList[j].toString().equals(u2oil[1].toString())){
								dataflg = 1;
								obj.put("RPD_U1_LINE_MEASURE_ID", u2oil[0]);
								obj.put("REPORT_TIME",  dateList[j].substring(11, 16));
								obj.put("FAV10112", CommonsUtil.getNotTwoDataZero(u2oil[2]));
						 		 obj.put("FIT_HH_10110AV", CommonsUtil.getNotTwoDataZero(u2oil[3]));
								 obj.put("PT_10110", CommonsUtil.getNotTwoDataZero(u2oil[4]));
								 obj.put("TT_10110", CommonsUtil.getZeroData(u2oil[5]));
								 obj.put("FAV10105A", CommonsUtil.getNotTwoDataZero(u2oil[6]));
								 obj.put("FIT_HH_10105AV",  CommonsUtil.getNotTwoDataZero(u2oil[7])); 
								 obj.put("PT_10105", CommonsUtil.getNotTwoDataZero(u2oil[8]));
								 obj.put("TT_10105", CommonsUtil.getZeroData(u2oil[9]));
								 obj.put("MT10105AV", CommonsUtil.getNotTwoDataZero(u2oil[10]));
								 obj.put("oilI", String.format("%.2f",CommonsUtil.getDoubleData(u2oil[11])*CommonsUtil.getDoubleData(u2oil[26])));
								 obj.put("FAV10106A", CommonsUtil.getNotTwoDataZero(u2oil[12]));
								 obj.put("FIT_HH_10106AV",  CommonsUtil.getNotTwoDataZero(u2oil[13]));
								 obj.put("PT_10106", CommonsUtil.getNotTwoDataZero(u2oil[14]));
								 obj.put("TT_10106", CommonsUtil.getZeroData(u2oil[15]));
								 obj.put("MT10106AV", CommonsUtil.getNotTwoDataZero(u2oil[16]));
								 obj.put("oilII", String.format("%.2f",CommonsUtil.getDoubleData(u2oil[17])*CommonsUtil.getDoubleData(u2oil[26])));
								 obj.put("FAV10107A",  CommonsUtil.getNotTwoDataZero(u2oil[18]));		   
								 obj.put("FIT_HH_10107AV", CommonsUtil.getNotTwoDataZero(u2oil[19]));
								 obj.put("PT_10107", CommonsUtil.getNotTwoDataZero(u2oil[20]));
								 obj.put("TT_10107", CommonsUtil.getZeroData(u2oil[21]));
								 obj.put("MT10107AV", CommonsUtil.getNotTwoDataZero(u2oil[22]));
								 obj.put("oilIII",  String.format("%.2f",CommonsUtil.getDoubleData(u2oil[23])*CommonsUtil.getDoubleData(u2oil[27])));							 
								
								 obj.put("AT_AVE_001", CommonsUtil.getNotTwoDataZero(u2oil[24]));
								 obj.put("oil", String.format("%.2f",CommonsUtil.getDoubleData(u2oil[25])*CommonsUtil.getDoubleData(u2oil[26])));
								//添加班累积值
								if(i!=0||j!=0){
									FIT10110ZSUM = CommonsUtil.getDoubleData(u2oil[3])+FIT10110ZSUM;
									FIT10110OSUM = CommonsUtil.getDoubleData(u2oil[25])*CommonsUtil.getDoubleData(u2oil[26])+FIT10110OSUM;
									FIT10105BSUM = CommonsUtil.getDoubleData(u2oil[7])+FIT10105BSUM;
									FIT10105OSUM = CommonsUtil.getDoubleData(u2oil[11])*CommonsUtil.getDoubleData(u2oil[26])+FIT10105OSUM;
									FIT10106BSUM = CommonsUtil.getDoubleData(u2oil[13])+FIT10106BSUM;
									FIT10106OSUM = CommonsUtil.getDoubleData(u2oil[17])*CommonsUtil.getDoubleData(u2oil[26])+FIT10106OSUM;
									FIT10107BSUM = CommonsUtil.getDoubleData(u2oil[19])+FIT10107BSUM;
									FIT10107OSUM = CommonsUtil.getDoubleData(u2oil[23])*CommonsUtil.getDoubleData(u2oil[27])+FIT10107OSUM;
									banleiji2 = CommonsUtil.getDoubleData(u2oil[10])*CommonsUtil.getDoubleData(u2oil[7])+banleiji2;
									banleiji3 = CommonsUtil.getDoubleData(u2oil[16])*CommonsUtil.getDoubleData(u2oil[13])+banleiji3;
									banleiji4 = CommonsUtil.getDoubleData(u2oil[22])*CommonsUtil.getDoubleData(u2oil[19])+banleiji4;
									banleiji1 = CommonsUtil.getDoubleData(u2oil[24])*CommonsUtil.getDoubleData(u2oil[3])+banleiji1;
								}
							}
							
						}
							if(dataflg==0){
								obj.put("RPD_U1_LINE_MEASURE_ID", "");
								obj.put("REPORT_TIME",  dateList[j].substring(11, 16));
								obj.put("FAV10112", "");
						 		 obj.put("FIT_HH_10110AV", "");
								 obj.put("PT_10110", "");
								 obj.put("TT_10110", "");
								 obj.put("FAV10105A", "");
								 obj.put("FIT_HH_10105AV",  ""); 
								 obj.put("PT_10105", "");
								 obj.put("TT_10105", "");
								 obj.put("MT10105AV", "");
								 obj.put("oilI", "");
								 obj.put("FAV10106A", "");
								 obj.put("FIT_HH_10106AV",  "");
								 obj.put("PT_10106", "");
								 obj.put("TT_10106", "");
								 obj.put("MT10106AV", "");
								 obj.put("oilII", "");
								 obj.put("FAV10107A",  "");		   
								 obj.put("FIT_HH_10107AV", "");
								 obj.put("PT_10107", "");
								 obj.put("TT_10107", "");
								 obj.put("MT10107AV", "");
								 obj.put("oilIII", "");							 
								 obj.put("AT_AVE_001", "");
								 obj.put("oil", "");
							}
							arr.add(obj);
					}
					obj.put("RPD_U1_LINE_MEASURE_ID", "");
					obj.put("REPORT_TIME",  "班累积");
					obj.put("FAV10112", "/");
			 		 obj.put("FIT_HH_10110AV", String.format("%.2f",FIT10110ZSUM));
					 obj.put("PT_10110", "/");
					 obj.put("TT_10110", "/");
					 obj.put("FAV10105A", "/");
					 obj.put("FIT_HH_10105AV",  String.format("%.2f",FIT10105BSUM)); 
					 obj.put("PT_10105", "/");
					 obj.put("TT_10105", "/");
					 if(FIT10105BSUM==0){
						 obj.put("MT10105AV", "--");
					 }else{
						 obj.put("MT10105AV", String.format("%.2f",banleiji2/FIT10105BSUM));
					 }

					 obj.put("oilI", String.format("%.2f",FIT10105OSUM));
					 obj.put("FAV10106A", "/");
					 obj.put("FIT_HH_10106AV",  String.format("%.2f",FIT10106BSUM));
					 obj.put("PT_10106", "/");
					 obj.put("TT_10106", "/");
					 
						 obj.put("MT10106AV", "/");
					 

					 obj.put("oilII", "/");
					 obj.put("FAV10107A",  "/");		   
					 obj.put("FIT_HH_10107AV", String.format("%.2f",FIT10107BSUM));
					 obj.put("PT_10107", "/");
					 obj.put("TT_10107", "/");
					 if(FIT10107BSUM==0){
						 obj.put("MT10107AV", "--");
					 }else{
						 obj.put("MT10107AV", String.format("%.2f",banleiji4/FIT10107BSUM));
					 }

					 obj.put("oilIII", String.format("%.2f",FIT10107OSUM));							 
					 if(FIT10110ZSUM==0){
						 obj.put("AT_AVE_001", "--");
					 }else{
						 obj.put("AT_AVE_001", String.format("%.2f",banleiji1/FIT10110ZSUM));
					 }

					 obj.put("oil", String.format("%.2f",FIT10110OSUM));
					 FIT10110ZSUMday = FIT10110ZSUM+FIT10110ZSUMday;
					 FIT10110OSUMday = FIT10110OSUM+FIT10110OSUMday;
					 FIT10105BSUMday = FIT10105BSUM+FIT10105BSUMday;
					 FIT10105OSUMday = FIT10105OSUM+FIT10105OSUMday;
					 FIT10106BSUMday = FIT10106BSUM+FIT10106BSUMday;
					 FIT10106OSUMday = FIT10106OSUM+FIT10106OSUMday;
					 FIT10107BSUMday = FIT10107BSUM+FIT10107BSUMday;
					 FIT10107OSUMday = FIT10107OSUM+FIT10107OSUMday;
					 zongleiji1 = banleiji1+zongleiji1;
					 zongleiji2 = banleiji2+zongleiji2;
					 zongleiji3 = banleiji3+zongleiji3;
					 zongleiji4 = banleiji4+zongleiji4;
					 arr.add(obj);
					}

				 
				}else{

					for(String[] dateList:dates){
						obj = new JSONObject();
						for(String data:dateList){
							obj = new JSONObject();
							obj.put("RPD_U1_LINE_MEASURE_ID", "");
							obj.put("REPORT_TIME",  data.substring(11, 16));
							obj.put("FAV10112", "");
					 		 obj.put("FIT_HH_10110AV", "");
							 obj.put("PT_10110", "");
							 obj.put("TT_10110", "");
							 obj.put("FAV10105A", "");
							 obj.put("FIT_HH_10105AV",  ""); 
							 obj.put("PT_10105", "");
							 obj.put("TT_10105", "");
							 obj.put("MT10105AV", "");
							 obj.put("oilI", "");
							 obj.put("FAV10106A", "");
							 obj.put("FIT_HH_10106AV",  "");
							 obj.put("PT_10106", "");
							 obj.put("TT_10106", "");
							 obj.put("MT10106AV", "");
							 obj.put("oilII", "");
							 obj.put("FAV10107A",  "");		   
							 obj.put("FIT_HH_10107AV", "");
							 obj.put("PT_10107", "");
							 obj.put("TT_10107", "");
							 obj.put("MT10107AV", "");
							 obj.put("oilIII", "");							 

							 obj.put("AT_AVE_001", "");
							 obj.put("oil", "");
							arr.add(obj);
						}
						obj.put("RPD_U1_LINE_MEASURE_ID", "");
						obj.put("REPORT_TIME",  "班累积");
						obj.put("FAV10112", "/");
				 		 obj.put("FIT_HH_10110AV", "/");
						 obj.put("PT_10110", "/");
						 obj.put("TT_10110", "/");
						 obj.put("FAV10105A", "/");
						 obj.put("FIT_HH_10105AV",  "/"); 
						 obj.put("PT_10105", "/");
						 obj.put("TT_10105", "/");
						 obj.put("MT10105AV", "/");
						 obj.put("oilI", "/");
						 obj.put("FAV10106A", "/");
						 obj.put("FIT_HH_10106AV",  "/");
						 obj.put("PT_10106", "/");
						 obj.put("TT_10106", "/");
						 obj.put("MT10106AV", "/");
						 obj.put("oilII", "/");
						 obj.put("FAV10107A",  "/");		   
						 obj.put("FIT_HH_10107AV", "/");
						 obj.put("PT_10107", "/");
						 obj.put("TT_10107", "/");
						 obj.put("MT10107AV", "/");
						 obj.put("oilIII", "/");							 

						 obj.put("AT_AVE_001", "/");
						 obj.put("oil", "/");
						arr.add(obj);
					}
				
				}
		
			
			//jieshu
			if(u2oilss != null && u2oilss.size()>0){
				obj = new JSONObject();
				obj.put("RPD_U1_LINE_MEASURE_ID", "");
				obj.put("REPORT_TIME", "合计");
			
				obj.put("FAV10112", "/");
		 		 obj.put("FIT_HH_10110AV", String.format("%.2f",FIT10110ZSUMday));
				 obj.put("PT_10110", "/");
				 obj.put("TT_10110", "/");
				 obj.put("FAV10105A", "/");
				 obj.put("FIT_HH_10105AV",  String.format("%.2f",FIT10105BSUMday)); 
				 obj.put("PT_10105", "/");
				 obj.put("TT_10105", "/");
				 if(FIT10105BSUMday==0){
					 obj.put("MT10105AV", "--");
				 }else{
					 obj.put("MT10105AV", String.format("%.2f",zongleiji2/FIT10105BSUMday));
				 }

				 obj.put("oilI", String.format("%.2f",FIT10105OSUMday));
				 obj.put("FAV10106A", "/");
				 obj.put("FIT_HH_10106AV",  String.format("%.2f",FIT10106BSUMday));
				 obj.put("PT_10106", "/");
				 obj.put("TT_10106", "/");
			
				obj.put("MT10106AV", "/");

				 obj.put("oilII", "/");
				 obj.put("FAV10107A",  "/");		   
				 obj.put("FIT_HH_10107AV", String.format("%.2f",FIT10107BSUMday));
				 obj.put("PT_10107", "/");
				 obj.put("TT_10107", "/");
				 if(FIT10107BSUMday==0){
					 obj.put("MT10107AV", "--");
				 }else{
					 obj.put("MT10107AV", String.format("%.2f",zongleiji4/FIT10107BSUMday));
				 }

				 obj.put("oilIII", String.format("%.2f",FIT10107OSUMday));						 

				 if(FIT10110ZSUMday==0){
					 obj.put("AT_AVE_001", "--");
				 }else{
					 obj.put("AT_AVE_001", String.format("%.2f",zongleiji1/FIT10110ZSUMday));
				 }
				 obj.put("oil", String.format("%.2f",FIT10110OSUMday));
				 arr.add(obj);
			}else{

				obj = new JSONObject();
				obj.put("RPD_U1_LINE_MEASURE_ID", "");
				obj.put("REPORT_TIME", "合计");
			
				obj.put("FAV10112", "/");
		 		 obj.put("FIT_HH_10110AV", "/");
				 obj.put("PT_10110", "/");
				 obj.put("TT_10110", "/");
				 obj.put("FAV10105A", "/");
				 obj.put("FIT_HH_10105AV",  "/"); 
				 obj.put("PT_10105", "/");
				 obj.put("TT_10105", "/");
				 obj.put("MT10105AV", "/");
				 obj.put("oilI", "/");
				 obj.put("FAV10106A", "/");
				 obj.put("FIT_HH_10106AV",  "/");
				 obj.put("PT_10106", "/");
				 obj.put("TT_10106", "/");
				 obj.put("MT10106AV", "/");
				 obj.put("oilII", "/");
				 obj.put("FAV10107A",  "/");		   
				 obj.put("FIT_HH_10107AV", "/");
				 obj.put("PT_10107", "/");
				 obj.put("TT_10107", "/");
				 obj.put("MT10107AV", "/");
				 obj.put("oilIII", "/");							 

				 obj.put("AT_AVE_001", "/");
				 obj.put("oil", "/");
			   arr.add(obj);
				
			}
		   return arr;	
			//
		
	}
		public JSONArray searchLineXy(String rq) throws Exception {
			JSONObject obj = new JSONObject();
			JSONArray arr = new JSONArray();
			  
			int dataflg = 0;  //数据存在标识  0：不存在， 1：存在
			  //合计
			  double FIT10110ZSUMday = 0.0;
			  double FIT10110OSUMday = 0.0;
			  double FIT10105BSUMday = 0.0;
			  double FIT10105OSUMday = 0.0;
			  double zongleiji1 = 0.0;
			  double zongleiji2 = 0.0;

			
			String timeCalssql = "select tp.parameter_value from PC_CD_TRIGGERED_PROCESS_T tp where tp.parameter_name='union_station_rpd_start_time'";
			String calcNum = "-16";
			List<Object[]> dataSet = lineMeasureDao.searchLineM(timeCalssql);
			if(dataSet != null && dataSet.size()>0){
				calcNum = dataSet.get(0) + "";
			}
			
			String stratime =DateBean.getDynamicTime(calcNum, rq, "0");
			String endtime =DateBean.getDynamicTime(calcNum, rq, "1");

			
			
			String sql2 ="select RPD_U_THIN_LINE_MEASURE_ID,  to_char(ACQUISITION_TIME,'YYYY-MM-DD HH24:MI:SS')as ACQUISITION_TIME ,"+
				" FT_AVE2, FT_HH_102, AT_AVE2, OL_HH_102, FT_AVE1, FT_HH_101, AT_AVE1, OL_HH_101"+
				"  from  PC_RPD_U_THIN_LINE_MERSURE_T  where 1=1";
			sql2 += " and ACQUISITION_TIME between TO_DATE('"+stratime+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+endtime+"','YYYY-MM-DD HH24:MI:SS') order by ACQUISITION_TIME";

				
			List<Object[]> u2oilss = lineMeasureDao.searchLineM(sql2);
			String[][] dates = DateBean.getReportTime("time10", rq);
			if(u2oilss != null && u2oilss.size()>0){
				 
				for (int i = 0; i < dates.length; i++) {
					double   FIT10110ZSUM = 0.0;
					double   FIT10110OSUM = 0.0;
					double   FIT10105BSUM = 0.0;
					double   FIT10105OSUM = 0.0;
					double banleiji1 = 0.0;
					double banleiji2 = 0.0;

					String[] dateList = dates[i];
					obj = new JSONObject();
					for(int j=0;j<dateList.length;j++){
							dataflg = 0;
							obj = new JSONObject();
						for(Object[] u2oil:u2oilss){
							if(dateList[j].toString().equals(u2oil[1].toString())){
								dataflg = 1;
								obj.put("RPD_U_THIN_LINE_MEASURE_ID", u2oil[0]);
								obj.put("REPORT_TIME",  dateList[j].substring(11, 16));
								
								 obj.put("FT_AVE2", CommonsUtil.getNotTwoDataZero(u2oil[2]));
								 obj.put("FT_HH_102", CommonsUtil.getNotTwoDataZero(u2oil[3]));
								 
								// if(CommonsUtil.getDoubleData(u2oil[2]) > 5){
									 obj.put("AT_AVE2", CommonsUtil.getNotTwoDataZero(u2oil[4]));
								// }else{
								//	 obj.put("AT_AVE2", "0");
								// }
								 
//								 if(CommonsUtil.getNotTwoDataZero(u2oil[5]).equals("")||CommonsUtil.getNotTwoDataZero(u2oil[10]).equals("")){
//									 obj.put("oil","");
//								 }else{
//									 obj.put("oil", String.format("%.2f",CommonsUtil.getDoubleData(u2oil[5])*CommonsUtil.getDoubleData(u2oil[10])));
//								 }
								 obj.put("oil", CommonsUtil.getNotTwoDataZero(u2oil[5]));
								 obj.put("FT_AVE1", CommonsUtil.getNotTwoDataZero(u2oil[6]));
								 obj.put("FT_HH_101",  CommonsUtil.getNotTwoDataZero(u2oil[7]));								
								 obj.put("AT_AVE1", CommonsUtil.getNotTwoDataZero(u2oil[8]));
//								 if(CommonsUtil.getNotTwoDataZero(u2oil[9]).equals("")||CommonsUtil.getNotTwoDataZero(u2oil[10]).equals("")){
//									 obj.put("oilI","");
//								 }else{
//									 obj.put("oilI", String.format("%.2f",CommonsUtil.getDoubleData(u2oil[9])*CommonsUtil.getDoubleData(u2oil[10])));
//								 }
								 obj.put("oilI", CommonsUtil.getNotTwoDataZero(u2oil[9]));
								
								//添加班累积值
								if(i!=0||j!=0){
									FIT10110ZSUM = CommonsUtil.getDoubleData(u2oil[3])+FIT10110ZSUM;
									FIT10110OSUM = CommonsUtil.getDoubleData(u2oil[5])+FIT10110OSUM;
									FIT10105BSUM = CommonsUtil.getDoubleData(u2oil[7])+FIT10105BSUM;
									FIT10105OSUM = CommonsUtil.getDoubleData(u2oil[9])+FIT10105OSUM;
									banleiji1 = CommonsUtil.getDoubleData(u2oil[3])*CommonsUtil.getDoubleData(u2oil[4])+banleiji1;
									banleiji2 = CommonsUtil.getDoubleData(u2oil[7])*CommonsUtil.getDoubleData(u2oil[8])+banleiji2;
								}
							}							
						}
							if(dataflg==0){
								obj.put("RPD_U_THIN_LINE_MEASURE_ID", "");
								obj.put("REPORT_TIME",  dateList[j].substring(11, 16));
								obj.put("FT_AVE2", "");
								 obj.put("FT_HH_102","");
								 obj.put("AT_AVE2", "");
								 obj.put("oil", "");
								 obj.put("FT_AVE1", "");
								 obj.put("FT_HH_101",  "");								
								 obj.put("AT_AVE1", "");
								 obj.put("oilI", "");
								
							}
							arr.add(obj);
					}
					obj.put("RPD_U_THIN_LINE_MEASURE_ID", "");
					obj.put("REPORT_TIME",  "班累积");
					obj.put("FT_AVE2", "/");
					 obj.put("FT_HH_102",String.format("%.2f",FIT10110ZSUM));
					 if(FIT10110ZSUM==0){
						 obj.put("AT_AVE2", "--");
					 }else{
						 obj.put("AT_AVE2", String.format("%.2f",banleiji1/FIT10110ZSUM));
					 }
					 obj.put("oil", String.format("%.2f",FIT10110OSUM));
					 obj.put("FT_AVE1", "/");
					 obj.put("FT_HH_101",  String.format("%.2f",FIT10105BSUM));	
					 if(FIT10105BSUM==0){
						 obj.put("AT_AVE1", "--");
					 }else{
						 obj.put("AT_AVE1", String.format("%.2f",banleiji2/FIT10105BSUM));
					 }
					 obj.put("oilI", String.format("%.2f",FIT10105OSUM));
					 FIT10110ZSUMday = FIT10110ZSUM+FIT10110ZSUMday;
					 FIT10110OSUMday = FIT10110OSUM+FIT10110OSUMday;
					 FIT10105BSUMday = FIT10105BSUM+FIT10105BSUMday;
					 FIT10105OSUMday = FIT10105OSUM+FIT10105OSUMday;
					 zongleiji1 = banleiji1+zongleiji1;
					 zongleiji2 = banleiji2+zongleiji2;
					 arr.add(obj);
					}

				 
				}else{

					for(String[] dateList:dates){
						obj = new JSONObject();
						for(String data:dateList){
							obj = new JSONObject();
							obj.put("RPD_U_THIN_LINE_MEASURE_ID", "");
							obj.put("REPORT_TIME",  data.substring(11, 16));
							obj.put("FT_AVE2", "");
							 obj.put("FT_HH_102","");
							 obj.put("AT_AVE2", "");
							 obj.put("oil", "");
							 obj.put("FT_AVE1", "");
							 obj.put("FT_HH_101",  "");								
							 obj.put("AT_AVE1", "");
							 obj.put("oilI", "");
							
							arr.add(obj);
						}
						obj.put("RPD_U_THIN_LINE_MEASURE_ID", "");
						obj.put("REPORT_TIME",  "班累积");
						obj.put("FT_AVE2", "/");
						 obj.put("FT_HH_102","/");
						 obj.put("AT_AVE2", "/");
						 obj.put("oil", "/");
						 obj.put("FT_AVE1", "/");
						 obj.put("FT_HH_101",  "/");								
						 obj.put("AT_AVE1", "/");
						 obj.put("oilI", "/");
						
						arr.add(obj);
					}
				
				}
		
			
			//jieshu
			if(u2oilss != null && u2oilss.size()>0){
				obj = new JSONObject();
				obj.put("RPD_U_THIN_LINE_MEASURE_ID", "");
				obj.put("REPORT_TIME", "合计");
			
				obj.put("FT_AVE2", "/");
				 obj.put("FT_HH_102",String.format("%.2f",FIT10110ZSUMday));
				 if(FIT10110ZSUMday==0){
					 obj.put("AT_AVE2", "--");
				 }else{
					 obj.put("AT_AVE2", String.format("%.2f",zongleiji1/FIT10110ZSUMday));
				 }

				 obj.put("oil", String.format("%.2f",FIT10110OSUMday));
				 obj.put("FT_AVE1", "/");
				 obj.put("FT_HH_101",  String.format("%.2f",FIT10105BSUMday));
				 if(FIT10105BSUMday==0){
					 obj.put("AT_AVE1", "--");
				 }else{
					 obj.put("AT_AVE1", String.format("%.2f",zongleiji2/FIT10105BSUMday));
				 }
				 obj.put("oilI", String.format("%.2f",FIT10105OSUMday));
						 

				 arr.add(obj);
			}else{

				obj = new JSONObject();
				obj.put("RPD_U_THIN_LINE_MEASURE_ID", "");
				obj.put("REPORT_TIME", "合计");
			
				obj.put("FT_AVE2", "/");
				 obj.put("FT_HH_102","/");
				 obj.put("AT_AVE2", "/");
				 obj.put("oil", "/");
				 obj.put("FT_AVE1", "/");
				 obj.put("FT_HH_101",  "/");								
				 obj.put("AT_AVE1", "/");
				 obj.put("oilI", "/");					 

			   arr.add(obj);
				
			}
		   return arr;	
			//
		
	}
		//稀油分线计量导出查询
				public List<Object[]> searchLineMeasureXY(String rq, String fields) throws Exception {
					String sql = "";
					if(!rq.equals("")){
						String timeCalssql = "select tp.parameter_value from PC_CD_TRIGGERED_PROCESS_T tp where tp.parameter_name='union_station_rpd_start_time'";
						String calcNum = "-16";
						List<Object[]> dataSet = lineMeasureDao.searchU2OIL(timeCalssql);
						if(dataSet != null && dataSet.size()>0){
							calcNum = dataSet.get(0) + "";
						}
						String stratime =DateBean.getDynamicTime(calcNum, rq, "0");
						String endtime =DateBean.getDynamicTime(calcNum, rq, "1");
						sql = "select " + fields + " from   PC_RPD_U_THIN_LINE_MERSURE_T   u where u.ACQUISITION_TIME between TO_DATE('"+stratime+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+endtime+"','YYYY-MM-DD HH24:MI:SS') order by u.ACQUISITION_TIME";
					}else{
						sql = fields;
					}
					
					List<Object[]> yyList = lineMeasureDao.searchU2OIL(sql);
					return yyList;
				
				}

				@Override
				public JSONArray searchLinex1836rb(String txtDate)
						throws Exception {
					JSONObject obj = new JSONObject();
					JSONArray arr = new JSONArray();
					Object[] u2oiltable = null;
					int dataflg  =0; //数据存在表示 0：不存在； 1： 存在
					String timeCalssql = "select tp.parameter_value from PC_CD_TRIGGERED_PROCESS_T tp where tp.parameter_name='union_station_rpd_modify_time'";
					String calcNum = "-16";
					List<Object[]> dataSet = lineMeasureDao.searchU2OIL(timeCalssql);
					
					if(dataSet !=null && dataSet.size()>0){
						calcNum = dataSet.get(0) + "";
					}
					String stratime =DateBean.getDynamicTime(calcNum, txtDate, "0");
					String endtime =DateBean.getDynamicTime(calcNum, txtDate, "1");
					
					String sql = "select RPD_X18_36ZS_ID ,to_char(a.BBSJ,'YYYY-MM-DD HH24:MI:SS')as BBSJ, PRSA_301,PRSA_306,TRSA_301,ZSB1DY,ZSB1DL,PRSA_302,PRSA_307,TRSA_302,ZSB2DY,ZSB2DL,SR_301,PRSA_303,PRSA_308,TRSA_303,ZSB3DY,"+
					"ZSB3DL,PRSA_304,PRSA_309,TRSA_304,ZSB4DY,ZSB4DL,BP34PL,TSBLLLJ,FRQ_501,SR_501,LRSA_501,LRSA_502,PRSA_305,FRQ_301,FRQ_302 from  PC_RPD_X18_36ZS_T a where 1=1";
					sql += " and a.BBSJ between TO_DATE('"+stratime+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+endtime+"','YYYY-MM-DD HH24:MI:SS') order by a.BBSJ";
	
					List<Object[]> U1S1S = lineMeasureDao.searchU2OIL(sql);
					
					String[][] dates = DateBean.getReportTime("time10", txtDate);
					
					if(U1S1S !=null && U1S1S.size()>0){	
						for(String[] dateList:dates){					 
								 for(String data:dateList){
									 obj = new JSONObject();
								//		System.out.println(data);
										u2oiltable = null;
										dataflg = 0;
										for(Object[] U1S1SS:U1S1S){
											if(data.toString().equals(U1S1SS[1].toString())){
												dataflg = 1;
												u2oiltable = U1S1SS;
										}
									}
								 if(dataflg == 1){
									 		
										obj.put("RPD_X18_36ZS_ID", u2oiltable[0]);
										obj.put("rq", data.substring(11, 16));
											obj.put("PRSA_301",CommonsUtil.getNotTwoData(u2oiltable[2]));

											obj.put("PRSA_306", CommonsUtil.getNotTwoData(u2oiltable[3]));

											obj.put("TRSA_301", CommonsUtil.getNotTwoData(u2oiltable[4]));

											obj.put("ZSB1DY", CommonsUtil.getNotTwoData(u2oiltable[5]));

											obj.put("ZSB1DL", CommonsUtil.getNotTwoData(u2oiltable[6]));

											obj.put("PRSA_302", CommonsUtil.getNotTwoData(u2oiltable[7]));

											obj.put("PRSA_307", CommonsUtil.getNotTwoData(u2oiltable[8]));

											obj.put("TRSA_302", CommonsUtil.getNotTwoData(u2oiltable[9]));

											obj.put("ZSB2DY", CommonsUtil.getNotTwoData(u2oiltable[10]));

											obj.put("ZSB2DL", CommonsUtil.getNotTwoData(u2oiltable[11]));

											obj.put("SR_301", CommonsUtil.getNotTwoData(u2oiltable[12]));

											obj.put("PRSA_303", CommonsUtil.getNotTwoData(u2oiltable[13]));

											obj.put("PRSA_308", CommonsUtil.getNotTwoData(u2oiltable[14]));
		
											obj.put("TRSA_303", CommonsUtil.getNotTwoData(u2oiltable[15]));

											obj.put("ZSB3DY", CommonsUtil.getNotTwoData(u2oiltable[16]));

											obj.put("ZSB3DL", CommonsUtil.getNotTwoData(u2oiltable[17]));

											obj.put("PRSA_304", CommonsUtil.getNotTwoData(u2oiltable[18]));

										
											obj.put("PRSA_309", CommonsUtil.getNotTwoData(u2oiltable[19]));
										
										
											obj.put("TRSA_304", CommonsUtil.getNotTwoData(u2oiltable[20]));
										
									
											obj.put("ZSB4DY", CommonsUtil.getNotTwoData(u2oiltable[21]));
										
										
											obj.put("ZSB4DL", CommonsUtil.getNotTwoData(u2oiltable[22]));
										
										
											obj.put("BP34PL", CommonsUtil.getNotTwoData(u2oiltable[23]));
										
										
											obj.put("TSBLLLJ", CommonsUtil.getNotTwoData(u2oiltable[24]));
										
									
											obj.put("FRQ_501", CommonsUtil.getNotTwoData(u2oiltable[25]));
										
										
											obj.put("SR_501", CommonsUtil.getNotTwoData(u2oiltable[26]));
										
										
											obj.put("LRSA_501", CommonsUtil.getNotTwoData(u2oiltable[27]));
										
										
											obj.put("LRSA_502", CommonsUtil.getNotTwoData(u2oiltable[28]));
										
										
											obj.put("PRSA_305", CommonsUtil.getNotTwoData(u2oiltable[29]));
										
										
											obj.put("FRQ_301", CommonsUtil.getNotTwoData(u2oiltable[30]));
										
										
											obj.put("FRQ_302", CommonsUtil.getNotTwoData(u2oiltable[31]));

								 	}else{
								 		obj.put("RPD_X18_36ZS_ID","");
										obj.put("rq",data.substring(11, 16));
										obj.put("PRSA_301", "");
										obj.put("PRSA_306", "");
										obj.put("TRSA_301", "");
										obj.put("ZSB1DY","");
										obj.put("ZSB1DL","");
										obj.put("PRSA_302", "");
										obj.put("PRSA_307", "");
										obj.put("TRSA_302", "");
										obj.put("ZSB2DY", "");
										obj.put("ZSB2DL", "");
										obj.put("SR_301", "");
										obj.put("PRSA_303", "");
										obj.put("PRSA_308", "");
										obj.put("TRSA_303", "");
										obj.put("ZSB3DY", "");
										obj.put("ZSB3DL", "");
										obj.put("PRSA_304", "");
										obj.put("PRSA_309", "");
										obj.put("TRSA_304", "");
										obj.put("ZSB4DY", "");
										obj.put("ZSB4DL", "");
										obj.put("BP34PL", "");
										obj.put("TSBLLLJ", "");
										obj.put("FRQ_501", "");
										obj.put("SR_501", "");
										obj.put("LRSA_501", "");
										obj.put("LRSA_502", "");
										obj.put("PRSA_305", "");
										obj.put("FRQ_301", "");
										obj.put("FRQ_302", "");

								 }
								 arr.add(obj);
							 }
						}
					}else{
						for(String[] dateList:dates){
							for(String data:dateList){
								obj = new JSONObject();
								obj.put("RPD_X18_36ZS_ID","");
								obj.put("rq",data.substring(11, 16));
								obj.put("PRSA_301", "");
								obj.put("PRSA_306", "");
								obj.put("TRSA_301", "");
								obj.put("ZSB1DY","");
								obj.put("ZSB1DL","");
								obj.put("PRSA_302", "");
								obj.put("PRSA_307", "");
								obj.put("TRSA_302", "");
								obj.put("ZSB2DY", "");
								obj.put("ZSB2DL", "");
								obj.put("SR_301", "");
								obj.put("PRSA_303", "");
								obj.put("PRSA_308", "");
								obj.put("TRSA_303", "");
								obj.put("ZSB3DY", "");
								obj.put("ZSB3DL", "");
								obj.put("PRSA_304", "");
								obj.put("PRSA_309", "");
								obj.put("TRSA_304", "");
								obj.put("ZSB4DY", "");
								obj.put("ZSB4DL", "");
								obj.put("BP34PL", "");
								obj.put("TSBLLLJ", "");
								obj.put("FRQ_501", "");
								obj.put("SR_501", "");
								obj.put("LRSA_501", "");
								obj.put("LRSA_502", "");
								obj.put("PRSA_305", "");
								obj.put("FRQ_301", "");
								obj.put("FRQ_302", "");
								arr.add(obj);
							}
						}
					
					}
					
					return arr;
				}

				@Override
				public JSONArray searchLinex1836rb1(String txtDate)
						throws Exception {
					JSONObject obj = new JSONObject();
					JSONArray arr = new JSONArray();
					String sql1 = "select RPD_X18_36ZS_ZH_ID,B1ZSL,B2ZSL,B3ZSL,RZSL from  PC_RPD_X18_36ZS_ZH_T a where 1=1";
					sql1 += " and a.REPORT_DATE = TO_DATE('"+txtDate+"','YYYY-MM-DD')";
					List<Object[]> U1S1S1 = lineMeasureDao.searchU2OIL(sql1);
					if(U1S1S1 !=null && U1S1S1.size()>0){
						obj.put("ID", CommonsUtil.getClearNullData(U1S1S1.get(0)[0]));
						obj.put("B1ZSL", CommonsUtil.getNotTwoData(U1S1S1.get(0)[1]));
						obj.put("B2ZSL", CommonsUtil.getNotTwoData(U1S1S1.get(0)[2]));
						obj.put("B3ZSL", CommonsUtil.getNotTwoData(U1S1S1.get(0)[3]));
						obj.put("RZSL", CommonsUtil.getNotTwoData(U1S1S1.get(0)[4]));
					}else{
						obj.put("ID", "");
						obj.put("B1ZSL", "");
						obj.put("B2ZSL", "");
						obj.put("B3ZSL", "");
						obj.put("RZSL", "");
					}
					arr.add(obj);
					return arr;
				}
				public JSONArray searchLinexcgyrb1(String txtDate)
						throws Exception {
					JSONObject obj = new JSONObject();
					JSONArray arr = new JSONArray();
					String sql1 = "select RPD_XCGYZS_ZH_ID,B1ZSL,B2ZSL,B3ZSL,RZSL from  PC_RPD_XCGYZS_ZH_T a where 1=1";
					sql1 += " and a.REPORT_DATE = TO_DATE('"+txtDate+"','YYYY-MM-DD')";
					List<Object[]> U1S1S1 = lineMeasureDao.searchU2OIL(sql1);
					if(U1S1S1 !=null && U1S1S1.size()>0){
						obj.put("ID", CommonsUtil.getClearNullData(U1S1S1.get(0)[0]));
						obj.put("B1ZSL", CommonsUtil.getNotTwoData(U1S1S1.get(0)[1]));
						obj.put("B2ZSL", CommonsUtil.getNotTwoData(U1S1S1.get(0)[2]));
						obj.put("B3ZSL", CommonsUtil.getNotTwoData(U1S1S1.get(0)[3]));
						obj.put("RZSL", CommonsUtil.getNotTwoData(U1S1S1.get(0)[4]));
					}else{
						obj.put("ID", "");
						obj.put("B1ZSL", "");
						obj.put("B2ZSL", "");
						obj.put("B3ZSL", "");
						obj.put("RZSL", "");
					}
					arr.add(obj);
					return arr;
				}
				public JSONArray searchLinexcgyrb(String txtDate)
						throws Exception {
					JSONObject obj = new JSONObject();
					JSONArray arr = new JSONArray();
					Object[] u2oiltable = null;
					int dataflg  =0; //数据存在表示 0：不存在； 1： 存在
					String timeCalssql = "select tp.parameter_value from PC_CD_TRIGGERED_PROCESS_T tp where tp.parameter_name='union_station_rpd_modify_time'";
					String calcNum = "-16";
					List<Object[]> dataSet = lineMeasureDao.searchU2OIL(timeCalssql);
					
					if(dataSet !=null && dataSet.size()>0){
						calcNum = dataSet.get(0) + "";
					}
					String stratime =DateBean.getDynamicTime(calcNum, txtDate, "0");
					String endtime =DateBean.getDynamicTime(calcNum, txtDate, "1");
					
					String sql = "select RPD_XCGYZS_ID ,to_char(a.BBSJ,'YYYY-MM-DD HH24:MI:SS')as BBSJ,PRSA_401,PRSA_405,TRSA_401,ZSB1DY,ZSB1DL,PRSA_402,PRSA_406,TRSA_402,"
							+ "ZSB2DY,ZSB2DL,BP12PL,PRSA_403,PRSA_407,TRSA_403,ZSB3DY,ZSB3DL,BP3PL,FRQ_401,FRQ_402,PRSA_404 from  PC_RPD_XCGYZS_T a where 1=1";
					sql += " and a.BBSJ between TO_DATE('"+stratime+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+endtime+"','YYYY-MM-DD HH24:MI:SS') order by a.BBSJ";
	
					List<Object[]> U1S1S = lineMeasureDao.searchU2OIL(sql);
					
					String[][] dates = DateBean.getReportTime("time10", txtDate);
					
					if(U1S1S !=null && U1S1S.size()>0){	
						for(String[] dateList:dates){					 
								 for(String data:dateList){
									 obj = new JSONObject();
								//		System.out.println(data);
										u2oiltable = null;
										dataflg = 0;
										for(Object[] U1S1SS:U1S1S){
											if(data.toString().equals(U1S1SS[1].toString())){
												dataflg = 1;
												u2oiltable = U1S1SS;
										}
									}
								 if(dataflg == 1){
									 		
										obj.put("RPD_XCGYZS_ID", u2oiltable[0]);
										obj.put("rq", data.substring(11, 16));
											obj.put("PRSA_401",CommonsUtil.getNotTwoData(u2oiltable[2]));
											obj.put("PRSA_405", CommonsUtil.getNotTwoData(u2oiltable[3]));
											obj.put("TRSA_401", CommonsUtil.getNotTwoData(u2oiltable[4]));
											obj.put("ZSB1DY", CommonsUtil.getNotTwoData(u2oiltable[5]));
											obj.put("ZSB1DL", CommonsUtil.getNotTwoData(u2oiltable[6]));
											obj.put("PRSA_402", CommonsUtil.getNotTwoData(u2oiltable[7]));
											obj.put("PRSA_406", CommonsUtil.getNotTwoData(u2oiltable[8]));
											obj.put("TRSA_402", CommonsUtil.getNotTwoData(u2oiltable[9]));
											obj.put("ZSB2DY", CommonsUtil.getNotTwoData(u2oiltable[10]));
											obj.put("ZSB2DL", CommonsUtil.getNotTwoData(u2oiltable[11]));
											obj.put("BP12PL", CommonsUtil.getNotTwoData(u2oiltable[12]));
											obj.put("PRSA_403", CommonsUtil.getNotTwoData(u2oiltable[13]));
											obj.put("PRSA_407", CommonsUtil.getNotTwoData(u2oiltable[14]));		
											obj.put("TRSA_403", CommonsUtil.getNotTwoData(u2oiltable[15]));
											obj.put("ZSB3DY", CommonsUtil.getNotTwoData(u2oiltable[16]));
											obj.put("ZSB3DL", CommonsUtil.getNotTwoData(u2oiltable[17]));
											obj.put("BP3PL", CommonsUtil.getNotTwoData(u2oiltable[18]));										
											obj.put("FRQ_401", CommonsUtil.getNotTwoData(u2oiltable[19]));										
											obj.put("FRQ_402", CommonsUtil.getNotTwoData(u2oiltable[20]));																		
											obj.put("PRSA_404", CommonsUtil.getNotTwoData(u2oiltable[21]));
										
								 	}else{
								 		obj.put("RPD_X18_36ZS_ID","");
										obj.put("rq",data.substring(11, 16));
										obj.put("PRSA_401","");
										obj.put("PRSA_405", "");
										obj.put("TRSA_401", "");
										obj.put("ZSB1DY", "");
										obj.put("ZSB1DL", "");
										obj.put("PRSA_402", "");
										obj.put("PRSA_406", "");
										obj.put("TRSA_402", "");
										obj.put("ZSB2DY","");
										obj.put("ZSB2DL","");
										obj.put("BP12PL","");
										obj.put("PRSA_403", "");
										obj.put("PRSA_407", "");		
										obj.put("TRSA_403", "");
										obj.put("ZSB3DY", "");
										obj.put("ZSB3DL", "");
										obj.put("BP3PL", "");										
										obj.put("FRQ_401", "");										
										obj.put("FRQ_402", "");																		
										obj.put("PRSA_404", "");

								 }
								 arr.add(obj);
							 }
						}
					}else{
						for(String[] dateList:dates){
							for(String data:dateList){
								obj = new JSONObject();
								obj.put("RPD_X18_36ZS_ID","");
								obj.put("rq",data.substring(11, 16));
								obj.put("PRSA_401","");
								obj.put("PRSA_405", "");
								obj.put("TRSA_401", "");
								obj.put("ZSB1DY", "");
								obj.put("ZSB1DL", "");
								obj.put("PRSA_402", "");
								obj.put("PRSA_406", "");
								obj.put("TRSA_402", "");
								obj.put("ZSB2DY","");
								obj.put("ZSB2DL","");
								obj.put("BP12PL","");
								obj.put("PRSA_403", "");
								obj.put("PRSA_407", "");		
								obj.put("TRSA_403", "");
								obj.put("ZSB3DY", "");
								obj.put("ZSB3DL", "");
								obj.put("BP3PL", "");										
								obj.put("FRQ_401", "");										
								obj.put("FRQ_402", "");																		
								obj.put("PRSA_404", "");
								arr.add(obj);
							}
						}
					
					}
					
					return arr;
				}
				@Override
				public List<Object[]> searchExportData(String txtDate,
						String fields) throws Exception {
					if (!"".equals(txtDate)) {
						String timeCalssql = "select tp.parameter_value from PC_CD_TRIGGERED_PROCESS_T tp where tp.parameter_name='union_station_rpd_start_time'";
						String calcNum = "-16";
						List<Object[]> dataSet = lineMeasureDao.searchU2OIL(timeCalssql);
						if(dataSet != null && dataSet.size()>0){
							calcNum = dataSet.get(0) + "";
						}
						String stratime =DateBean.getDynamicTime(calcNum, txtDate, "0");
						String endtime =DateBean.getDynamicTime(calcNum, txtDate, "1");
						fields +=  " from PC_RPD_X18_36ZS_T where BBSJ between TO_DATE('"+stratime+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+endtime+"','YYYY-MM-DD HH24:MI:SS') order by BBSJ";
					}
					List<Object[]> yyList = lineMeasureDao.searchU2OIL(fields);
					return yyList;
				}

				@Override
				public JSONArray searchLineMb(String rq) throws Exception {
					JSONObject obj = new JSONObject();
					JSONArray arr = new JSONArray();
					  
					int dataflg = 0;  //数据存在标识  0：不存在， 1：存在
					  //合计
					  double FIT101ZSUMday = 0.0;
					  double FIT10110ZSUMday = 0.0;
					  double FIT10110OSUMday = 0.0;
					  double FIT10105BSUMday = 0.0;
					  double FIT10105OSUMday = 0.0;
					  double zongleiji1 = 0.0;
					  double zongleiji2 = 0.0;

					
					String timeCalssql = "select tp.parameter_value from PC_CD_TRIGGERED_PROCESS_T tp where tp.parameter_name='union_station_rpd_start_time'";
					String calcNum = "-16";
					List<Object[]> dataSet = lineMeasureDao.searchLineM(timeCalssql);
					if(dataSet != null && dataSet.size()>0){
						calcNum = dataSet.get(0) + "";
					}
					
					String stratime =DateBean.getDynamicTime(calcNum, rq, "0");
					String endtime =DateBean.getDynamicTime(calcNum, rq, "1");

					
					
					String sql2 ="select RD_U1_SAGD_MEASURE_ID,  to_char(ACQUISITION_TIME,'YYYY-MM-DD HH24:MI:SS')as ACQUISITION_TIME ,"+
						" FIT_201_AVE,FIT_201_HH,TT_201,AT_201_AVE,OIL_201_AVE,FIT_4203_AVE,FIT_4203_HH,TT_4203,AT_4203_AVE,OIL_4203_AVE,FIT_TSB,FIQ_TSB "+
						" from  PC_RPD_U1_SAGD_MEASURE_T  where 1=1";
					sql2 += " and ACQUISITION_TIME between TO_DATE('"+stratime+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+endtime+"','YYYY-MM-DD HH24:MI:SS') order by ACQUISITION_TIME";

						
					List<Object[]> u2oilss = lineMeasureDao.searchLineM(sql2);
					String[][] dates = DateBean.getReportTime("time10", rq);
//					String[][] dates = DateBean.getReportDays("time15");
					if(u2oilss != null && u2oilss.size()>0){
						 
						for (int i = 0; i < dates.length; i++) {
							double   FIT101ZSUM = 0.0;
							double   FIT10110ZSUM = 0.0;
							double   FIT10110OSUM = 0.0;
							double   FIT10105BSUM = 0.0;
							double   FIT10105OSUM = 0.0;
							double banleiji1 = 0.0;
							double banleiji2 = 0.0;

							String[] dateList = dates[i];
							obj = new JSONObject();
							for(int j=0;j<dateList.length;j++){
									dataflg = 0;
									obj = new JSONObject();
								for(Object[] u2oil:u2oilss){
									if(dateList[j].toString().equals(u2oil[1].toString())){
										dataflg = 1;
										obj.put("RD_U1_SAGD_MEASURE_ID", u2oil[0]);
										obj.put("REPORT_TIME",  dateList[j].substring(11, 16));
										
										 obj.put("FIT_201", CommonsUtil.getNotTwoDataZero(u2oil[2]));
										 obj.put("FIQ_201", CommonsUtil.getNotTwoDataZero(u2oil[3]));
										 obj.put("TT_201", CommonsUtil.getNotTwoDataZero(u2oil[4]));										
										 obj.put("AT_201", CommonsUtil.getNotTwoDataZero(u2oil[5]));										
										 obj.put("OIL_201", CommonsUtil.getNotTwoDataZero(u2oil[6]));
										 obj.put("FIT_4203",  CommonsUtil.getNotTwoDataZero(u2oil[7]));								
										 obj.put("FIQ_4203", CommonsUtil.getNotTwoDataZero(u2oil[8]));
										 obj.put("TT_4203",  CommonsUtil.getNotTwoDataZero(u2oil[9]));								
										 obj.put("AT_4203", CommonsUtil.getNotTwoDataZero(u2oil[10]));
										 obj.put("OIL_4203", CommonsUtil.getNotTwoDataZero(u2oil[11]));
										 obj.put("FIT_TSB", CommonsUtil.getNotTwoDataZero(u2oil[12]));
										 obj.put("FIQ_TSB", CommonsUtil.getNotTwoDataZero(u2oil[13]));
										//添加班累积值
										if(i!=0||j!=0){
											FIT10110ZSUM = CommonsUtil.getDoubleData(u2oil[3])+FIT10110ZSUM;
											FIT10110OSUM = CommonsUtil.getDoubleData(u2oil[6])+FIT10110OSUM;
											FIT10105BSUM = CommonsUtil.getDoubleData(u2oil[8])+FIT10105BSUM;
											FIT10105OSUM = CommonsUtil.getDoubleData(u2oil[11])+FIT10105OSUM;
											FIT101ZSUM = CommonsUtil.getDoubleData(u2oil[13])+FIT101ZSUM;
											banleiji1 = CommonsUtil.getDoubleData(u2oil[3])*CommonsUtil.getDoubleData(u2oil[5])+banleiji1;
											banleiji2 = CommonsUtil.getDoubleData(u2oil[8])*CommonsUtil.getDoubleData(u2oil[10])+banleiji2;
										}
									}
									
								}
									if(dataflg==0){
										obj.put("RD_U1_SAGD_MEASURE_ID", "");
										obj.put("REPORT_TIME",  dateList[j].substring(11, 16));
										obj.put("FIT_201", "");
										 obj.put("FIQ_201", "");
										 obj.put("TT_201", "");										
										 obj.put("AT_201", "");										
										 obj.put("OIL_201", "");
										 obj.put("FIT_4203", "");								
										 obj.put("FIQ_4203", "");
										 obj.put("TT_4203",  "");								
										 obj.put("AT_4203", "");
										 obj.put("OIL_4203", "");
										 obj.put("FIT_TSB","");
										 obj.put("FIQ_TSB", "");
										
									}
									arr.add(obj);
							}
							obj.put("RD_U1_SAGD_MEASURE_ID", "");
							obj.put("REPORT_TIME",  "班累积");
							obj.put("FIT_201", "/");
							 obj.put("FIQ_201",String.format("%.2f",FIT10110ZSUM));
							 obj.put("TT_201", "/");
							 if(FIT10110ZSUM==0){
								 obj.put("AT_201", "--");
							 }else{
								 obj.put("AT_201", String.format("%.2f",banleiji1/FIT10110ZSUM));
							 }
							 obj.put("OIL_201", String.format("%.2f",FIT10110OSUM));
							 obj.put("FIT_4203", "/");
							 obj.put("FIQ_4203",  String.format("%.2f",FIT10105BSUM));
							 obj.put("TT_4203", "/");
							 if(FIT10105BSUM==0){
								 obj.put("AT_4203", "--");
							 }else{
								 obj.put("AT_4203", String.format("%.2f",banleiji2/FIT10105BSUM));
							 }
							 obj.put("OIL_4203", String.format("%.2f",FIT10105OSUM));
							 obj.put("FIT_TSB", "/");
							 obj.put("FIQ_TSB", String.format("%.2f",FIT101ZSUM));
							 FIT10110ZSUMday = FIT10110ZSUM+FIT10110ZSUMday;
							 FIT10110OSUMday = FIT10110OSUM+FIT10110OSUMday;
							 FIT10105BSUMday = FIT10105BSUM+FIT10105BSUMday;
							 FIT10105OSUMday = FIT10105OSUM+FIT10105OSUMday;
							 FIT101ZSUMday = FIT101ZSUM+FIT101ZSUMday;
							 zongleiji1 = banleiji1+zongleiji1;
							 zongleiji2 = banleiji2+zongleiji2;
							 arr.add(obj);
							}

						 
						}else{

							for(String[] dateList:dates){
								obj = new JSONObject();
								for(String data:dateList){
									obj = new JSONObject();
									obj.put("RD_U1_SAGD_MEASURE_ID", "");
									obj.put("REPORT_TIME",  data.substring(11, 16));
									obj.put("FIT_201", "");
									 obj.put("FIQ_201", "");
									 obj.put("TT_201", "");										
									 obj.put("AT_201", "");										
									 obj.put("OIL_201", "");
									 obj.put("FIT_4203", "");								
									 obj.put("FIQ_4203", "");
									 obj.put("TT_4203",  "");								
									 obj.put("AT_4203", "");
									 obj.put("OIL_4203", "");
									 obj.put("FIT_TSB","");
									 obj.put("FIQ_TSB", "");
									
									arr.add(obj);
								}
								obj.put("RD_U1_SAGD_MEASURE_ID", "");
								obj.put("REPORT_TIME",  "班累积");
								obj.put("FIT_201", "/");
								 obj.put("FIQ_201", "/");
								 obj.put("TT_201", "/");										
								 obj.put("AT_201", "/");										
								 obj.put("OIL_201", "/");
								 obj.put("FIT_4203", "/");								
								 obj.put("FIQ_4203", "/");
								 obj.put("TT_4203",  "/");								
								 obj.put("AT_4203", "/");
								 obj.put("OIL_4203", "/");
								 obj.put("FIT_TSB","/");
								 obj.put("FIQ_TSB", "/");
								
								arr.add(obj);
							}
						
						}
				
					
					//jieshu
					if(u2oilss != null && u2oilss.size()>0){
						obj = new JSONObject();
						obj.put("RD_U1_SAGD_MEASURE_ID", "");
						obj.put("REPORT_TIME", "合计");
					
						obj.put("FIT_201", "/");
						 obj.put("FIQ_201",String.format("%.2f",FIT10110ZSUMday));
						 obj.put("TT_201", "/");
						 if(FIT10110ZSUMday==0){
							 obj.put("AT_201", "--");
						 }else{
							 obj.put("AT_201", String.format("%.2f",zongleiji1/FIT10110ZSUMday));
						 }

						 obj.put("OIL_201", String.format("%.2f",FIT10110OSUMday));
						 obj.put("FIT_4203", "/");
						 obj.put("FIQ_4203",  String.format("%.2f",FIT10105BSUMday));
						 obj.put("TT_4203",  "/");
						 if(FIT10105BSUMday==0){
							 obj.put("AT_4203", "--");
						 }else{
							 obj.put("AT_4203", String.format("%.2f",zongleiji2/FIT10105BSUMday));
						 }
						 obj.put("OIL_4203", String.format("%.2f",FIT10105OSUMday));
						 obj.put("FIT_TSB","/");
						 obj.put("FIQ_TSB", String.format("%.2f",FIT101ZSUMday));		 

						 arr.add(obj);
					}else{

						obj = new JSONObject();
						obj.put("RD_U1_SAGD_MEASURE_ID", "");
						obj.put("REPORT_TIME", "合计");
					
						obj.put("FIT_201", "/");
						 obj.put("FIQ_201", "/");
						 obj.put("TT_201", "/");										
						 obj.put("AT_201", "/");										
						 obj.put("OIL_201", "/");
						 obj.put("FIT_4203", "/");								
						 obj.put("FIQ_4203", "/");
						 obj.put("TT_4203",  "/");								
						 obj.put("AT_4203", "/");
						 obj.put("OIL_4203", "/");
						 obj.put("FIT_TSB","/");
						 obj.put("FIQ_TSB", "/");			 

					   arr.add(obj);
						
					}
				   return arr;	
					//
				
				}
				//1号稠油脱水参数
				public JSONArray searchN1tscs(String rq) throws Exception {
					JSONObject obj = new JSONObject();
					JSONArray arr = new JSONArray();
					double t11 = 0.0;double t12 = 0.0;double t13 = 0.0;double t21 = 0.0;double t22 = 0.0;double t23 = 0.0;double t31 = 0.0;double t32 = 0.0;double t33 = 0.0;
					double t41 = 0.0;double t42 = 0.0;double t43 = 0.0;double t51 = 0.0;double t52 = 0.0;double t53 = 0.0;double t61 = 0.0;double t62 = 0.0;double t63 = 0.0;
					double t71 = 0.0;double t72 = 0.0;double t73 = 0.0;double t81 = 0.0;double t82 = 0.0;double t83 = 0.0;double t91 = 0.0;double t92 = 0.0;double t93 = 0.0;
					double t101 = 0.0;double t102 = 0.0;double t103 = 0.0;double t111 = 0.0;double t112 = 0.0;double t113 = 0.0;double t121 = 0.0;double t122 = 0.0;double t123 = 0.0;
					int dataflg = 0;  //数据存在标识  0：不存在， 1：存在
					  //合计
					
					String timeCalssql = "select tp.parameter_value from PC_CD_TRIGGERED_PROCESS_T tp where tp.parameter_name='union_station_rpd_start_time'";
					String calcNum = "-16";
					List<Object[]> dataSet = lineMeasureDao.searchLineM(timeCalssql);
					if(dataSet != null && dataSet.size()>0){
						calcNum = dataSet.get(0) + "";
					}
					
					String stratime =DateBean.getDynamicTime(calcNum, rq, "0");
					String endtime =DateBean.getDynamicTime(calcNum, rq, "1");

					
					
					String sql2 ="select RPD_U1_DEHYDRATION_ID,  to_char(BBSJ,'YYYY-MM-DD HH24:MI:SS')as BBSJ ,"+
						" LT201, LT202, LT203,LT204,LT205,LT206,LT207,LT208,LRR_218,LRR_217,LRR_220,LRR_219,TSB1PL,TSB2PL,TSB3PL,TSB4PL,TSB5PL,FT1001,TR_225"+
						" from  pc_rpd_U1_dehydration_t  where 1=1";
					sql2 += " and BBSJ between TO_DATE('"+stratime+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+endtime+"','YYYY-MM-DD HH24:MI:SS') order by BBSJ";

						
					List<Object[]> u2oilss = lineMeasureDao.searchLineM(sql2);
					String[][] dates = DateBean.getReportTime("time11", rq);
					if(u2oilss != null && u2oilss.size()>0){
						 int x = 0;
						for (int i = 0; i < dates.length; i++) {
							String[] dateList = dates[i];
							obj = new JSONObject();
							for(int j=0;j<dateList.length;j++){
									dataflg = 0;
									obj = new JSONObject();
								for(Object[] u2oil:u2oilss){
									if(dateList[j].toString().equals(u2oil[1].toString())){
										dataflg = 1;
										obj.put("RPD_U1_DEHYDRATION_ID", u2oil[0]);
										obj.put("REPORT_TIME",  dateList[j].substring(11, 16));
										obj.put("LT201", CommonsUtil.getNotTwoDataZero(u2oil[2]));										
								 		 obj.put("LT202", CommonsUtil.getNotTwoDataZero(u2oil[3]));
										 obj.put("LT203", CommonsUtil.getNotTwoDataZero(u2oil[4]));
										 obj.put("LT204", CommonsUtil.getNotTwoDataZero(u2oil[5]));
										 obj.put("LT205", CommonsUtil.getNotTwoDataZero(u2oil[6]));
										 obj.put("LT206",  CommonsUtil.getNotTwoDataZero(u2oil[7])); 
										 obj.put("LT207", CommonsUtil.getNotTwoDataZero(u2oil[8]));
										 obj.put("LT208", CommonsUtil.getNotTwoDataZero(u2oil[9]));
										 obj.put("LRR_218", CommonsUtil.getNotTwoDataZero(u2oil[10]));
										 obj.put("LRR_217", CommonsUtil.getNotTwoDataZero(u2oil[11]));
										 obj.put("LRR_220", CommonsUtil.getNotTwoDataZero(u2oil[12]));
										 obj.put("LRR_219",  CommonsUtil.getNotTwoDataZero(u2oil[13]));
										 obj.put("TSB1PL", CommonsUtil.getNotTwoDataZero(u2oil[14]));
										 obj.put("TSB2PL", CommonsUtil.getNotTwoDataZero(u2oil[15]));
										 obj.put("TSB3PL", CommonsUtil.getNotTwoDataZero(u2oil[16]));
										 obj.put("TSB4PL", CommonsUtil.getNotTwoDataZero(u2oil[17]));
										 obj.put("TSB5PL",  CommonsUtil.getNotTwoDataZero(u2oil[18]));		   
										 obj.put("T1001", CommonsUtil.getNotTwoDataZero(u2oil[19]));
										 obj.put("R_225", CommonsUtil.getZeroData(u2oil[20]));
										 if(x==0){
											 t11 = CommonsUtil.getDoubleData(u2oil[2]);
											 t21 = CommonsUtil.getDoubleData(u2oil[3]);
											 t31 = CommonsUtil.getDoubleData(u2oil[4]);
											 t41 = CommonsUtil.getDoubleData(u2oil[5]);
											 t51 = CommonsUtil.getDoubleData(u2oil[6]);
											 t61 = CommonsUtil.getDoubleData(u2oil[7]);
											 t71 = CommonsUtil.getDoubleData(u2oil[8]);
											 t81 = CommonsUtil.getDoubleData(u2oil[9]);
											 t91 = CommonsUtil.getDoubleData(u2oil[10]);
											 t101 = CommonsUtil.getDoubleData(u2oil[11]);
											 t111 = CommonsUtil.getDoubleData(u2oil[12]);
											 t121 = CommonsUtil.getDoubleData(u2oil[13]);
										}
										if(x==11){
											t12 = CommonsUtil.getDoubleData(u2oil[2]);
											 t22 = CommonsUtil.getDoubleData(u2oil[3]);
											 t32 = CommonsUtil.getDoubleData(u2oil[4]);
											 t42 = CommonsUtil.getDoubleData(u2oil[5]);
											 t52 = CommonsUtil.getDoubleData(u2oil[6]);
											 t62 = CommonsUtil.getDoubleData(u2oil[7]);
											 t72 = CommonsUtil.getDoubleData(u2oil[8]);
											 t82 = CommonsUtil.getDoubleData(u2oil[9]);
											 t92 = CommonsUtil.getDoubleData(u2oil[10]);
											 t102 = CommonsUtil.getDoubleData(u2oil[11]);
											 t112 = CommonsUtil.getDoubleData(u2oil[12]);
											 t122 = CommonsUtil.getDoubleData(u2oil[13]);
										}
										if(x==24){
											t13 = CommonsUtil.getDoubleData(u2oil[2]);
											 t23 = CommonsUtil.getDoubleData(u2oil[3]);
											 t33 = CommonsUtil.getDoubleData(u2oil[4]);
											 t43 = CommonsUtil.getDoubleData(u2oil[5]);
											 t53 = CommonsUtil.getDoubleData(u2oil[6]);
											 t63 = CommonsUtil.getDoubleData(u2oil[7]);
											 t73 = CommonsUtil.getDoubleData(u2oil[8]);
											 t83 = CommonsUtil.getDoubleData(u2oil[9]);
											 t93 = CommonsUtil.getDoubleData(u2oil[10]);
											 t103 = CommonsUtil.getDoubleData(u2oil[11]);
											 t113 = CommonsUtil.getDoubleData(u2oil[12]);
											 t123 = CommonsUtil.getDoubleData(u2oil[13]);
										}
									}
									
								}
									if(dataflg==0){
										obj.put("RPD_U1_DEHYDRATION_ID", "");
										obj.put("REPORT_TIME",  dateList[j].substring(11, 16));
										obj.put("LT201", "");
								 		 obj.put("LT202", "");
										 obj.put("LT203", "");
										 obj.put("LT204", "");
										 obj.put("LT205", "");
										 obj.put("LT206",  ""); 
										 obj.put("LT207", "");
										 obj.put("LT208", "");
										 obj.put("LRR_218", "");
										 obj.put("LRR_217", "");
										 obj.put("LRR_220", "");
										 obj.put("LRR_219",  "");
										 obj.put("TSB1PL", "");
										 obj.put("TSB2PL", "");
										 obj.put("TSB3PL", "");
										 obj.put("TSB4PL", "");
										 obj.put("TSB5PL",  "");		   
										 obj.put("T1001", "");
										 obj.put("R_225", "");
									}
									x++;
									arr.add(obj);
							}
							obj.put("RPD_U1_DEHYDRATION_ID", "");
							obj.put("REPORT_TIME",  "小计");
							if(i==0){
								obj.put("LT201", String.format("%.2f",t12-t11));
						 		 obj.put("LT202", String.format("%.2f",t22-t21));
								 obj.put("LT203", String.format("%.2f",t32-t31));
								 obj.put("LT204", String.format("%.2f",t42-t41));
								 obj.put("LT205", String.format("%.2f",t52-t51));
								 obj.put("LT206",  String.format("%.2f",t62-t61)); 
								 obj.put("LT207", String.format("%.2f",t72-t71));
								 obj.put("LT208", String.format("%.2f",t82-t81));
								 obj.put("LRR_218", String.format("%.2f",t92-t91));
								 obj.put("LRR_217", String.format("%.2f",t102-t101));
								 obj.put("LRR_220", String.format("%.2f",t112-t111));
								 obj.put("LRR_219",  String.format("%.2f",t122-t121));
							}else{
								obj.put("LT201", String.format("%.2f",t13-t12));
						 		 obj.put("LT202", String.format("%.2f",t23-t22));
								 obj.put("LT203", String.format("%.2f",t33-t32));
								 obj.put("LT204", String.format("%.2f",t43-t42));
								 obj.put("LT205", String.format("%.2f",t53-t52));
								 obj.put("LT206",  String.format("%.2f",t63-t62)); 
								 obj.put("LT207", String.format("%.2f",t73-t72));
								 obj.put("LT208", String.format("%.2f",t83-t82));
								 obj.put("LRR_218", String.format("%.2f",t93-t92));
								 obj.put("LRR_217", String.format("%.2f",t103-t102));
								 obj.put("LRR_220", String.format("%.2f",t113-t112));
								 obj.put("LRR_219",  String.format("%.2f",t123-t122));
							}
							
							 obj.put("TSB1PL", "");
							 obj.put("TSB2PL", "");
							 obj.put("TSB3PL", "");
							 obj.put("TSB4PL", "");
							 obj.put("TSB5PL",  "");		   
							 obj.put("T1001", "");
							 obj.put("R_225", "");							
							 arr.add(obj);
							}						 
						}else{
							for(String[] dateList:dates){
								obj = new JSONObject();
								for(String data:dateList){
									obj = new JSONObject();
									obj.put("RPD_U1_DEHYDRATION_ID", "");
									obj.put("REPORT_TIME",  data.substring(11, 16));
									obj.put("LT201", "");
							 		 obj.put("LT202", "");
									 obj.put("LT203", "");
									 obj.put("LT204", "");
									 obj.put("LT205", "");
									 obj.put("LT206",  ""); 
									 obj.put("LT207", "");
									 obj.put("LT208", "");
									 obj.put("LRR_218", "");
									 obj.put("LRR_217", "");
									 obj.put("LRR_220", "");
									 obj.put("LRR_219",  "");
									 obj.put("TSB1PL", "");
									 obj.put("TSB2PL", "");
									 obj.put("TSB3PL", "");
									 obj.put("TSB4PL", "");
									 obj.put("TSB5PL",  "");		   
									 obj.put("T1001", "");
									 obj.put("R_225", "");
									arr.add(obj);
								}
								obj.put("RPD_U1_LINE_MEASURE_ID", "");
								obj.put("REPORT_TIME",  "小计");
								obj.put("LT201", String.format("%.2f",t12-t11));
						 		 obj.put("LT202", String.format("%.2f",t22-t21));
								 obj.put("LT203", String.format("%.2f",t32-t31));
								 obj.put("LT204", String.format("%.2f",t42-t41));
								 obj.put("LT205", String.format("%.2f",t52-t51));
								 obj.put("LT206",  String.format("%.2f",t62-t61)); 
								 obj.put("LT207", String.format("%.2f",t72-t71));
								 obj.put("LT208", String.format("%.2f",t82-t81));
								 obj.put("LRR_218", String.format("%.2f",t92-t91));
								 obj.put("LRR_217", String.format("%.2f",t102-t101));
								 obj.put("LRR_220", String.format("%.2f",t112-t111));
								 obj.put("LRR_219",  String.format("%.2f",t122-t121));
								 obj.put("TSB1PL", "");
								 obj.put("TSB2PL", "");
								 obj.put("TSB3PL", "");
								 obj.put("TSB4PL", "");
								 obj.put("TSB5PL",  "");		   
								 obj.put("T1001", "");
								 obj.put("R_225", "");
								arr.add(obj);
							}
						
						}
				
					
					//jieshu
					obj.put("RPD_U1_LINE_MEASURE_ID", "");
					obj.put("REPORT_TIME",  "总计");
					obj.put("LT201", String.format("%.2f",t13-t11));
			 		 obj.put("LT202", String.format("%.2f",t23-t21));
					 obj.put("LT203", String.format("%.2f",t33-t31));
					 obj.put("LT204", String.format("%.2f",t43-t41));
					 obj.put("LT205", String.format("%.2f",t53-t51));
					 obj.put("LT206",  String.format("%.2f",t63-t61)); 
					 obj.put("LT207", String.format("%.2f",t73-t71));
					 obj.put("LT208", String.format("%.2f",t83-t81));
					 obj.put("LRR_218", String.format("%.2f",t93-t91));
					 obj.put("LRR_217", String.format("%.2f",t103-t101));
					 obj.put("LRR_220", String.format("%.2f",t113-t111));
					 obj.put("LRR_219",  String.format("%.2f",t123-t121));
					 obj.put("TSB1PL", "");
					 obj.put("TSB2PL", "");
					 obj.put("TSB3PL", "");
					 obj.put("TSB4PL", "");
					 obj.put("TSB5PL",  "");		   
					 obj.put("T1001", "");
					 obj.put("R_225", "");
					  arr.add(obj);
				   return arr;				
			}
				public List<PcRpd1836zh> Sreach1836zh(String id, String name)throws Exception{
					String hql ="from PcRpd1836zh a  where 1=1";
					if(id!=null && !"".equals(id)){
					      hql +=" and a.rpd1836zhId = '"+id+"'";
					}
					if(name !=null && !"".equals(name)){
						hql=" and a.bbsj = '"+name+"'";
					}
					return lineMeasureDao.Sreach1836zh(hql);
				}	
				public List<PcRpd1836> Sreach1836(String id, String name)throws Exception {
					String hql ="from PcRpd1836 a  where 1=1";
					if(id!=null && !"".equals(id)){
					      hql +=" and a.rpd1836Id = '"+id+"'";
					}
					if(name !=null && !"".equals(name)){
						hql=" and a.bbsj = '"+name+"'";
					}
					return lineMeasureDao.Sreach1836(hql);
				}	
				public List<PcRpdxczszh> Sreachxczszh(String id, String name)throws Exception {
					String hql ="from PcRpdxczszh a  where 1=1";
					if(id!=null && !"".equals(id)){
					      hql +=" and a.rpdxczszhId = '"+id+"'";
					}
					if(name !=null && !"".equals(name)){
						hql=" and a.bbsj = '"+name+"'";
					}
					return lineMeasureDao.Sreachxczszh(hql);
				}	
				public List<PcRpdxczs> Sreachxczs(String id, String name)throws Exception {
					String hql ="from PcRpdxczs a  where 1=1";
					if(id!=null && !"".equals(id)){
					      hql +=" and a.rpdxczsId = '"+id+"'";
					}
					if(name !=null && !"".equals(name)){
						hql=" and a.bbsj = '"+name+"'";
					}
					return lineMeasureDao.Sreachxczs(hql);
				}
			
				
				public boolean updatedata(Object us) throws Exception {
					return lineMeasureDao.updatedata(us);
				}

				@Override
				public List<PcRpdU1DehydrationT> SreachN1tscsAuto(String id,
						String name)throws Exception  {
					String hql = "from PcRpdU1DehydrationT a   where 1=1"; 
					if(id !=null && !"".equals(id)){
						hql += " and a.rpdU1DehydrationId  ='"+id+"'";
					}
					if(name !=null && !"".equals(name)){
						hql += " and a.bbsj = '"+name+"'";
					}
					return lineMeasureDao.SreachN1tscs(hql);
				}

			
				
				
				
				
				
				@Override
				public JSONArray searchLineU2zyzrb(String txtDate)
						throws Exception {
					JSONObject obj = new JSONObject();
					JSONArray arr = new JSONArray();
					Object[] u2oiltable = null;
					int dataflg  =0; //数据存在表示 0：不存在； 1： 存在
					
					//union_station_rpd_modify_time表示联合站数据修改时间范围统计起始时间,值表示偏离报表日0点的小时数
					String timeCalssql = "select tp.parameter_value from PC_CD_TRIGGERED_PROCESS_T tp where tp.parameter_name='union_station_rpd_modify_time'";
					String calcNum = "-16";
					List<Object[]> dataSet = lineMeasureDao.searchU2OIL(timeCalssql);
					
					if(dataSet !=null && dataSet.size()>0){
						calcNum = dataSet.get(0) + "";
					}
					String stratime =DateBean.getDynamicTime(calcNum, txtDate, "0");
					String endtime =DateBean.getDynamicTime(calcNum, txtDate, "1");
					
					String sql = "select RPD_ZYZ2_ID ,to_char(a.BBSJ,'YYYY-MM-DD HH24:MI:SS')as BBSJ,PT104_1,PT105_1,jrl1jkwd,jrl1ckwd,jrl1jqyl,jrl1sw," +
							"PT106_1,PT107_1,jrl2jkwd,jrl2ckwd,jrl2jqyl,jrl2sw,cyqqy,cyqfy,cyqyw,flq1jyyl,flq1qy,flq1fy,flq1yw,flq2jyyl,flq2qy,flq2fy,"+
							"flq2yw,wsb1kyl,PT108_1,TT104_1,wsb1ckwd,wsb1pl,wsb2kyl,PT109_1,TT105_1,wsb2ckwd,wsb2pl,LIT105_1,LIT106_1,stllllj,cyqlllj from  PC_RPD_ZYZ2_T a where 1=1";
					sql += " and a.BBSJ between TO_DATE('"+stratime+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+endtime+"','YYYY-MM-DD HH24:MI:SS') order by a.BBSJ";
	
					List<Object[]> U1S1S = lineMeasureDao.searchU2OIL(sql);
					
					String[][] dates = DateBean.getReportTime("time1", txtDate);
					
					if(U1S1S !=null && U1S1S.size()>0){	
						for(String[] dateList:dates){					 
								 for(String data:dateList){
									 obj = new JSONObject();
								//		System.out.println(data);
										u2oiltable = null;
										dataflg = 0;
										for(Object[] U1S1SS:U1S1S){
									//		System.out.println(data.toString()+"#################"+U1S1SS[1].toString());
									//		System.out.println(data.toString().equals(U1S1SS[1].toString())+"");
											if(data.toString().equals(U1S1SS[1].toString())){
												dataflg = 1;
												u2oiltable = U1S1SS;
										}
									}
								 if(dataflg == 1){
									 		
										obj.put("RPD_ZYZ2_ID", u2oiltable[0]);
										obj.put("rq", data.substring(11, 16));
											obj.put("PT104_1",CommonsUtil.getNotTwoData(u2oiltable[2]));
											obj.put("PT105_1", CommonsUtil.getNotTwoData(u2oiltable[3]));
											obj.put("jrl1jkwd", CommonsUtil.getNotTwoData(u2oiltable[4]));
											obj.put("jrl1ckwd", CommonsUtil.getNotTwoData(u2oiltable[5]));
											obj.put("jrl1jqyl", CommonsUtil.getNotTwoData(u2oiltable[6]));
											obj.put("jrl1sw", CommonsUtil.getNotTwoData(u2oiltable[7]));
											obj.put("PT106_1", CommonsUtil.getNotTwoData(u2oiltable[8]));
											obj.put("PT107_1", CommonsUtil.getNotTwoData(u2oiltable[9]));
											obj.put("jrl2jkwd", CommonsUtil.getNotTwoData(u2oiltable[10]));
											obj.put("jrl2ckwd", CommonsUtil.getNotTwoData(u2oiltable[11]));
											obj.put("jrl2jqyl", CommonsUtil.getNotTwoData(u2oiltable[12]));
											obj.put("jrl2sw", CommonsUtil.getNotTwoData(u2oiltable[13]));
											obj.put("cyqqy", CommonsUtil.getNotTwoData(u2oiltable[14]));		
											obj.put("cyqfy", CommonsUtil.getNotTwoData(u2oiltable[15]));
											obj.put("cyqyw", CommonsUtil.getNotTwoData(u2oiltable[16]));
											obj.put("flq1jyyl", CommonsUtil.getNotTwoData(u2oiltable[17]));
											obj.put("flq1qy", CommonsUtil.getNotTwoData(u2oiltable[18]));										
											obj.put("flq1fy", CommonsUtil.getNotTwoData(u2oiltable[19]));																				
											obj.put("flq1yw", CommonsUtil.getNotTwoData(u2oiltable[20]));																			
											obj.put("flq2jyyl", CommonsUtil.getNotTwoData(u2oiltable[21]));																				
											obj.put("flq2qy", CommonsUtil.getNotTwoData(u2oiltable[22]));
											obj.put("flq2fy", CommonsUtil.getNotTwoData(u2oiltable[23]));																				
											obj.put("flq2yw", CommonsUtil.getNotTwoData(u2oiltable[24]));
											
											obj.put("wsb1kyl", CommonsUtil.getNotTwoData(u2oiltable[25]));
											obj.put("PT108_1", CommonsUtil.getNotTwoData(u2oiltable[26]));
											obj.put("TT104_1", CommonsUtil.getNotTwoData(u2oiltable[27]));
											obj.put("wsb1ckwd", CommonsUtil.getNotTwoData(u2oiltable[28]));
											obj.put("wsb1pl", CommonsUtil.getNotTwoData(u2oiltable[29]));
											obj.put("wsb2kyl", CommonsUtil.getNotTwoData(u2oiltable[30]));
											obj.put("PT109_1", CommonsUtil.getNotTwoData(u2oiltable[31]));
											obj.put("TT105_1", CommonsUtil.getNotTwoData(u2oiltable[32]));
											obj.put("wsb2ckwd", CommonsUtil.getNotTwoData(u2oiltable[33]));
											obj.put("wsb2pl", CommonsUtil.getNotTwoData(u2oiltable[34]));
											obj.put("LIT105_1", CommonsUtil.getNotTwoData(u2oiltable[35]));
											obj.put("LIT106_1", CommonsUtil.getNotTwoData(u2oiltable[36]));
											obj.put("stllllj", CommonsUtil.getNotTwoData(u2oiltable[37]));
											obj.put("cyqlllj", CommonsUtil.getNotTwoData(u2oiltable[38]));
											
								 	}else{
								 		obj.put("RPD_ZYZ2_ID","");
										obj.put("rq",data.substring(11, 16));
										obj.put("PT104_1","");
										obj.put("PT105_1", "");
										obj.put("jrl1jkwd", "");
										obj.put("jrl1ckwd", "");
										obj.put("jrl1jqyl", "");
										obj.put("jrl1sw",  "");
										obj.put("PT106_1", "");
										obj.put("PT107_1", "");
										obj.put("jrl2jkwd", "");
										obj.put("jrl2ckwd", "");
										obj.put("jrl2jqyl", "");
										obj.put("jrl2sw", "");
										obj.put("cyqqy", "");		
										obj.put("cyqfy", "");
										obj.put("cyqyw", "");
										obj.put("flq1jyyl", "");
										obj.put("flq1qy", "");										
										obj.put("flq1fy", "");																				
										obj.put("flq1yw", "");																			
										obj.put("flq2jyyl", "");																				
										obj.put("flq2qy", "");
										obj.put("flq2fy", "");																				
										obj.put("flq2yw", "");
										
										obj.put("wsb1kyl", "");
										obj.put("PT108_1", "");
										obj.put("TT104_1", "");
										obj.put("wsb1ckwd", "");
										obj.put("wsb1pl", "");
										obj.put("wsb2kyl", "");
										obj.put("PT109_1", "");
										obj.put("TT105_1", "");
										obj.put("wsb2ckwd", "");
										obj.put("wsb2pl", "");
										obj.put("LIT105_1", "");
										obj.put("LIT106_1", "");
										obj.put("stllllj", "");
										obj.put("cyqlllj", "");

								 }
								 arr.add(obj);
							 }
						}
					}else{
						for(String[] dateList:dates){
							for(String data:dateList){
								obj = new JSONObject();
								obj.put("RPD_ZYZ2_ID"," ");
								obj.put("rq",data.substring(11, 16));
								obj.put("PT104_1","");
								obj.put("PT105_1", "");
								obj.put("jrl1jkwd", "");
								obj.put("jrl1ckwd", "");
								obj.put("jrl1jqyl", "");
								obj.put("jrl1sw",  "");
								obj.put("PT106_1", "");
								obj.put("PT107_1", "");
								obj.put("jrl2jkwd", "");
								obj.put("jrl2ckwd", "");
								obj.put("jrl2jqyl", "");
								obj.put("jrl2sw", "");
								obj.put("cyqqy", "");		
								obj.put("cyqfy", "");
								obj.put("cyqyw", "");
								obj.put("flq1jyyl", "");
								obj.put("flq1qy", "");										
								obj.put("flq1fy", "");																				
								obj.put("flq1yw", "");																			
								obj.put("flq2jyyl", "");																				
								obj.put("flq2qy", "");
								obj.put("flq2fy", "");																				
								obj.put("flq2yw", "");
								
								obj.put("wsb1kyl", "");
								obj.put("PT108_1", "");
								obj.put("TT104_1", "");
								obj.put("wsb1ckwd", "");
								obj.put("wsb1pl", "");
								obj.put("wsb2kyl", "");
								obj.put("PT109_1", "");
								obj.put("TT105_1", "");
								obj.put("wsb2ckwd", "");
								obj.put("wsb2pl", "");
								obj.put("LIT105_1", "");
								obj.put("LIT106_1", "");
								obj.put("stllllj", "");
								obj.put("cyqlllj", "");
								arr.add(obj);
							}
						}
					
					}
					
					return arr;
				}

				@Override
				public JSONArray searchLineU2zyzrb1(String txtDate)
						throws Exception {
					
					JSONObject obj = new JSONObject();
					JSONArray arr = new JSONArray();
					String sql1 = "select RPD_ZYZ2_general_ID,rxc,rcye,rcyou,rcq,ryq from  PC_RPD_ZYZ2_general_T a where 1=1";
					sql1 += " and a.RQ = TO_DATE('"+txtDate+"','YYYY-MM-DD')";
					List<Object[]> U1S1S1 = lineMeasureDao.searchU2OIL(sql1);
					if(U1S1S1 !=null && U1S1S1.size()>0){
						obj.put("ID", CommonsUtil.getClearNullData(U1S1S1.get(0)[0]));
						obj.put("rxc", CommonsUtil.getNotTwoData(U1S1S1.get(0)[1]));
						obj.put("rcye", CommonsUtil.getNotTwoData(U1S1S1.get(0)[2]));
						obj.put("rcyou", CommonsUtil.getNotTwoData(U1S1S1.get(0)[3]));
						obj.put("ceq", CommonsUtil.getNotTwoData(U1S1S1.get(0)[4]));
						obj.put("ryq", CommonsUtil.getNotTwoData(U1S1S1.get(0)[5]));
					}else{
						obj.put("ID", "");
						obj.put("rxc", "");
						obj.put("rcye", "");
						obj.put("rcyou", "");
						obj.put("ceq", "");
						obj.put("ryq", "");
					}
					arr.add(obj);
					return arr;
				}

				
				@Override
				public List<PcRpdU2zyzzh> SreachU2zyzzh(String id, String name)
						throws Exception {
					String hql ="from PcRpdU2zyzzh a  where 1=1";
					if(id!=null && !"".equals(id)){
					      hql +=" and a.rpdu2zyzzhId = '"+id+"'";
					}
					if(name !=null && !"".equals(name)){
						hql=" and a.bbsj = '"+name+"'";
					}
					return lineMeasureDao.SreachU2zyzzh(hql);
				}
				@Override
				public List<PcRpdU2zyz> SreachU2zyz(String id, String name)
						throws Exception {
					String hql ="from PcRpdU2zyz a  where 1=1";
					if(id!=null && !"".equals(id)){
					      hql +=" and a.rpdu2zyzId = '"+id+"'";
					}
					if(name !=null && !"".equals(name)){
						hql=" and a.bbsj = '"+name+"'";
					}
					return lineMeasureDao.SreachU2zyz(hql);
				}

				
				

				
				@SuppressWarnings("unused")
				@Override
				public JSONArray searchLineXzyzhgrb(String txtDate)
						throws Exception {

					
					JSONObject obj = new JSONObject();
					JSONArray arr = new JSONArray();
					Object[] u2oiltable = null;
					int dataflg  =0; //数据存在表示 0：不存在； 1： 存在
					
					//union_station_rpd_modify_time表示联合站数据修改时间范围统计起始时间,值表示偏离报表日0点的小时数
					String timeCalssql = "select tp.parameter_value from PC_CD_TRIGGERED_PROCESS_T tp where tp.parameter_name='union_station_rpd_modify_time'";
					String calcNum = "-16";
					List<Object[]> dataSet = lineMeasureDao.searchU2OIL(timeCalssql);
					
					if(dataSet !=null && dataSet.size()>0){
						calcNum = dataSet.get(0) + "";
					}
					String stratime =DateBean.getBeforeDAYTime(txtDate);
					//String endtime =DateBean.getDynamicTime(calcNum, txtDate, "1");
					
					String sql = "select rpd_zyz_id ,to_char(a.BBSJ,'YYYY-MM-DD HH24:MI:SS')as BBSJ,cyqjkyl,cyqckyl,flq1jkyl,flq1ckyl,flq1yw,"+
					"flq2jkyl,flq2ckyl,flq2yw,wsb1jkyl,wsb1ckyl,wsb2jkyl,wsb2ckyl,wsbbppl,xbl1jkyl,xbl1ckyl,xbl1jkwd,xbl1ckwd,xbl2jkyl,xbl2ckyl,"+
					"xbl2jkwd,xbl2ckwd,sggyw,hsyhs,trqyl,bsqqbyl,bsqqbll,bsqqbwd,bsqqblllj,gqqbfqyl,gqqbfhyl,gqqbwd,gqqbll,gqqblllj,qjqll,qjqlllj from  pc_rpd_zyz_t a where 1=1";
					sql += " and a.BBSJ between TO_DATE('"+stratime+" 02:00:00','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+txtDate+" 00:00:00','YYYY-MM-DD HH24:MI:SS') order by a.BBSJ";	
					List<Object[]> U1S1S = lineMeasureDao.searchU2OIL(sql);					
					String[][] dates = DateBean.getReportTime("time12", txtDate);
					
					if(U1S1S !=null && U1S1S.size()>0){	
						for(String[] dateList:dates){					 
								 for(String data:dateList){
									 obj = new JSONObject();
								//		System.out.println(data);
										u2oiltable = null;
										dataflg = 0;
										for(Object[] U1S1SS:U1S1S){
									//		System.out.println(data.toString()+"#################"+U1S1SS[1].toString());
									//		System.out.println(data.toString().equals(U1S1SS[1].toString())+"");											
											if(data.toString().equals(U1S1SS[1].toString())){
												dataflg = 1;
												u2oiltable = U1S1SS;
										}
									}
										if(dataflg == 1){

											obj.put("rpd_zyz_id", u2oiltable[0]);
											obj.put("rq", data.substring(11, 16));
											obj.put("cyqjkyl",CommonsUtil.getNotTwoData(u2oiltable[2]));
											obj.put("cyqckyl", CommonsUtil.getNotTwoData(u2oiltable[3]));
											obj.put("flq1jkyl", CommonsUtil.getNotTwoData(u2oiltable[4]));
											obj.put("flq1ckyl", CommonsUtil.getNotTwoData(u2oiltable[5]));
											obj.put("flq1yw", CommonsUtil.getNotTwoData(u2oiltable[6]));
											obj.put("flq2jkyl", CommonsUtil.getNotTwoData(u2oiltable[7]));
											obj.put("flq2ckyl", CommonsUtil.getNotTwoData(u2oiltable[8]));
											obj.put("flq2yw", CommonsUtil.getNotTwoData(u2oiltable[9]));
											obj.put("wsb1jkyl", CommonsUtil.getNotTwoData(u2oiltable[10]));
											obj.put("wsb1ckyl", CommonsUtil.getNotTwoData(u2oiltable[11]));
											obj.put("wsb2jkyl", CommonsUtil.getNotTwoData(u2oiltable[12]));
											obj.put("wsb2ckyl", CommonsUtil.getNotTwoData(u2oiltable[13]));
											obj.put("wsbbppl", CommonsUtil.getNotTwoData(u2oiltable[14]));		
											obj.put("xbl1jkyl", CommonsUtil.getNotTwoData(u2oiltable[15]));
											obj.put("xbl1ckyl", CommonsUtil.getNotTwoData(u2oiltable[16]));
											obj.put("xbl1jkwd", CommonsUtil.getNotTwoData(u2oiltable[17]));
											obj.put("xbl1ckwd", CommonsUtil.getNotTwoData(u2oiltable[18]));										
											obj.put("xbl2jkyl", CommonsUtil.getNotTwoData(u2oiltable[19]));																				
											obj.put("xbl2ckyl", CommonsUtil.getNotTwoData(u2oiltable[20]));																			
											obj.put("xbl2jkwd", CommonsUtil.getNotTwoData(u2oiltable[21]));																				
											obj.put("xbl2ckwd", CommonsUtil.getNotTwoData(u2oiltable[22]));
											obj.put("sggyw", CommonsUtil.getNotTwoData(u2oiltable[23]));																				
											obj.put("hsyhs", CommonsUtil.getNotTwoData(u2oiltable[24]));

											obj.put("trqyl", CommonsUtil.getNotTwoData(u2oiltable[25]));
											obj.put("bsqqbyl", CommonsUtil.getNotTwoData(u2oiltable[26]));
											obj.put("bsqqbll", CommonsUtil.getNotTwoData(u2oiltable[27]));
											obj.put("bsqqbwd", CommonsUtil.getNotTwoData(u2oiltable[28]));
											obj.put("bsqqblllj", CommonsUtil.getNotTwoData(u2oiltable[29]));		
											obj.put("gqqbfqyl", CommonsUtil.getNotTwoData(u2oiltable[30]));
											obj.put("gqqbfhyl", CommonsUtil.getNotTwoData(u2oiltable[31]));
											obj.put("gqqbwd", CommonsUtil.getNotTwoData(u2oiltable[32]));
											obj.put("gqqbll", CommonsUtil.getNotTwoData(u2oiltable[33]));										
											obj.put("gqqblllj", CommonsUtil.getNotTwoData(u2oiltable[34]));																				
											obj.put("qjqll", CommonsUtil.getNotTwoData(u2oiltable[35]));																			
											obj.put("qjqlllj", CommonsUtil.getNotTwoData(u2oiltable[36]));																				
				

								 	}else{
								 		obj.put("rpd_zyz_id", "");
										obj.put("rq", data.substring(11, 16));
										obj.put("cyqjkyl"," ");
										obj.put("cyqckyl", " ");
										obj.put("flq1jkyl", " ");
										obj.put("flq1ckyl", " ");
										obj.put("flq1yw", " ");
										obj.put("flq2jkyl", " ");
										obj.put("flq2ckyl", " ");
										obj.put("flq2yw", " ");
										obj.put("wsb1jkyl", " ");
										obj.put("wsb1ckyl", " ");
										obj.put("wsb2jkyl", " ");
										obj.put("wsb2ckyl", " ");
										obj.put("wsbbppl", " ");		
										obj.put("xbl1jkyl", " ");
										obj.put("xbl1ckyl", " ");
										obj.put("xbl1jkwd", " ");
										obj.put("xbl1ckwd", " ");										
										obj.put("xbl2jkyl", " ");																				
										obj.put("xbl2ckyl", " ");																			
										obj.put("xbl2jkwd", " ");																				
										obj.put("xbl2ckwd", " ");
										obj.put("sggyw", " ");																				
										obj.put("hsyhs", " ");				
										
										obj.put("trqyl", " ");
										obj.put("bsqqbyl", " ");
										obj.put("bsqqbll", " ");
										obj.put("bsqqbwd", " ");
										obj.put("bsqqblllj", " ");		
										obj.put("gqqbfqyl", " ");
										obj.put("gqqbfhyl", " ");
										obj.put("gqqbwd", " ");
										obj.put("gqqbll", " ");										
										obj.put("gqqblllj", " ");																				
										obj.put("qjqll", " ");																			
										obj.put("qjqlllj", " ");			

								 }
								 arr.add(obj);
							 }
						}
					}else{
						for(String[] dateList:dates){
							for(String data:dateList){
								obj = new JSONObject();
								obj.put("rpd_zyz_id", "");
								obj.put("rq", data.substring(11, 16));
								obj.put("cyqjkyl"," ");
								obj.put("cyqckyl", " ");
								obj.put("flq1jkyl", " ");
								obj.put("flq1ckyl", " ");
								obj.put("flq1yw", " ");
								obj.put("flq2jkyl", " ");
								obj.put("flq2ckyl", " ");
								obj.put("flq2yw", " ");
								obj.put("wsb1jkyl", " ");
								obj.put("wsb1ckyl", " ");
								obj.put("wsb2jkyl", " ");
								obj.put("wsb2ckyl", " ");
								obj.put("wsbbppl", " ");		
								obj.put("xbl1jkyl", " ");
								obj.put("xbl1ckyl", " ");
								obj.put("xbl1jkwd", " ");
								obj.put("xbl1ckwd", " ");										
								obj.put("xbl2jkyl", " ");																				
								obj.put("xbl2ckyl", " ");																			
								obj.put("xbl2jkwd", " ");																				
								obj.put("xbl2ckwd", " ");
								obj.put("sggyw", " ");																				
								obj.put("hsyhs", " ");				
								
								obj.put("trqyl", " ");
								obj.put("bsqqbyl", " ");
								obj.put("bsqqbll", " ");
								obj.put("bsqqbwd", " ");
								obj.put("bsqqblllj", " ");		
								obj.put("gqqbfqyl", " ");
								obj.put("gqqbfhyl", " ");
								obj.put("gqqbwd", " ");
								obj.put("gqqbll", " ");										
								obj.put("gqqblllj", " ");																				
								obj.put("qjqll", " ");																			
								obj.put("qjqlllj", " ");	
								arr.add(obj);
							}
						}
					
					}
					
					return arr;
				}

				@Override
				public JSONArray searchLineXzyzhgrb1(String txtDate)
						throws Exception {
					JSONObject obj = new JSONObject();
					JSONArray arr = new JSONArray();
					String sql1 = "select RPD_ZYZ_general_ID,rwsq from  pc_rpd_zyz_general_t a where 1=1";
					sql1 += " and a.RQ = TO_DATE('"+txtDate+"','YYYY-MM-DD')";
					List<Object[]> U1S1S1 = lineMeasureDao.searchU2OIL(sql1);
					if(U1S1S1 !=null && U1S1S1.size()>0){
						obj.put("ID", CommonsUtil.getClearNullData(U1S1S1.get(0)[0]));
						obj.put("rwsq", CommonsUtil.getNotTwoData(U1S1S1.get(0)[1]));
						
					}else{
						obj.put("ID", "");
						obj.put("rwsq", "");
						
					}
					arr.add(obj);
					return arr;
				}
				@Override
				public List<PcRpdXzyzhgzh> SreachXzyzhgzh(String id, String name)
						throws Exception {
					String hql ="from PcRpdXzyzhgzh a  where 1=1";
					if(id!=null && !"".equals(id)){
					      hql +=" and a.rpdxzyzhgzhId = '"+id+"'";
					}
					if(name !=null && !"".equals(name)){
						hql=" and a.bbsj = '"+name+"'";
					}
					return lineMeasureDao.SreachXzyzhgzh(hql);
				}
				@Override
				public List<PcRpdXzyzhg> SreachXzyzhg(String id, String name)
						throws Exception {
					String hql ="from PcRpdXzyzhg a  where 1=1";
					if(id!=null && !"".equals(id)){
					      hql +=" and a.rpdxzyzhgId = '"+id+"'";
					}
					if(name !=null && !"".equals(name)){
						hql=" and a.bbsj = '"+name+"'";
					}
					return lineMeasureDao.SreachXzyzhg(hql);
				}

				@SuppressWarnings("unused")
				@Override
				public JSONArray searchGrglzb(String txtDate) throws Exception {
					JSONObject obj = new JSONObject();
					JSONArray arr = new JSONArray();
					JSONArray arr1 = new JSONArray();
					int dataflg  =0; //数据存在表示 0：不存在； 1： 存在
					  //合计
					  double glztsSUMday = 0.0;
					  double glyxtsSUMday = 0.0;
					  double hggltsSUMday = 0.0;
					  double bhggltsSUMday = 0.0;
					  double hglMday = 0.0;
					  
					  
					  double glztsSUMday1 = 0.0;
					  double glyxtsSUMday1 = 0.0;
					  double hggltsSUMday1 = 0.0;
					  double bhggltsSUMday1 = 0.0;
					  double hglMday1 = 0.0;
					  
					  double glztsSUMday2 = 0.0;
					  double glyxtsSUMday2 = 0.0;
					  double hggltsSUMday2 = 0.0;
					  double bhggltsSUMday2 = 0.0;
					  double hglMday2 = 0.0;
					  
						String stratime1 =DateBean.getBefore6DAYTime(txtDate);
						String endtime1 = DateBean.getTimeAfterDAYTime(txtDate);
						
						String sql1 = "select rownum,boiler_name,bhgsc from (SELECT BOILER_NAME,SUM(SUPERHEAT_LJ) AS BHGSC FROM " +
								"(select * from PC_RD_BOILER_SUPERHEAT_YC_T  u where u.ACQUISITION_TIME between " +
								"TO_DATE('"+stratime1+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+endtime1+"','YYYY-MM-DD HH24:MI:SS') " +
								"AND SUPERHEAT_LJ >=240 ORDER BY ACQUISITION_TIME) GROUP BY BOILER_NAME order by BHGSC desc) where rownum<=5";
						List<Object[]> grglzpm = lineMeasureDao.searchU2OIL(sql1);	

					  
					//取一周范围的值
					String stratime =DateBean.getBefore6DAYTime(txtDate);
					String endtime = txtDate;
					String sql = "select rownum,acquisition_time,gqdw,glzts,glyxts,hgglts,bhgglts,hgl,bhggllh from (select rownum,acquisition_time,gqdw,glzts,glyxts,hgglts,bhgglts,hgl,bhggllh from PC_RPD_BOILER_SUPERHEAT_YC_T where 1=1"
					 +" and acquisition_time between TO_DATE('"+stratime+"','YYYY-MM-DD') and TO_DATE('"+endtime+"','YYYY-MM-DD') order by acquisition_time,gqdw desc)";	
					Object[] grglTab = null;
					List<Object[]> grgls = lineMeasureDao.searchU2OIL(sql);					
					String[][] rownum = DateBean.getReportDays("time15");
					if(grgls !=null && grgls.size()>0){						 
						for (int i = 0; i < rownum.length; i++) {
							double   glztsSUM = 0.0;
							double   glyxtsSUM = 0.0;
							double   hggltsSUM = 0.0;
							double   bhggltsSUM = 0.0;
							double   hglSUM = 0.0;
							double   glztsSUM1 = 0.0;
							double   glyxtsSUM1 = 0.0;
							double   hggltsSUM1 = 0.0;
							double   bhggltsSUM1 = 0.0;
							double   hglSUM1 = 0.0;
							double   glztsSUM2 = 0.0;
							double   glyxtsSUM2 = 0.0;
							double   hggltsSUM2 = 0.0;
							double   bhggltsSUM2 = 0.0;
							double   hglSUM2 = 0.0;
							String[] dateList = rownum[i];
							obj = new JSONObject();
							for(int j=0;j<dateList.length;j++){
									dataflg = 0;
									obj = new JSONObject();
								for(Object[] grgl:grgls){
									if(dateList[j].toString().equals(grgl[0].toString())){
										dataflg = 1;
										obj.put("rownum", grgl[0]);
										obj.put("acquisition_time", grgl[1].toString());										
										 obj.put("gqdw", grgl[2].toString());
										 obj.put("glzts", CommonsUtil.getNotTwoDataZero(grgl[3]));
										 obj.put("glyxts", CommonsUtil.getNotTwoDataZero(grgl[4]));										
										 obj.put("hgglts", CommonsUtil.getNotTwoDataZero(grgl[5]));	
									//	 System.out.println("*************"+grgl[6]);
										 if(grgl[6]==null || "".equals(grgl[6].toString())){
											 obj.put("bhgglts", " ");
										 }else{
											 obj.put("bhgglts", CommonsUtil.getNotTwoDataZero(grgl[6]));
										 	}
										 obj.put("hgl",  CommonsUtil.getNotTwoDataZero(grgl[7]));
										 if(grgl[8] == null || "".equals(grgl[8].toString())){
											 obj.put("bhggllh", " ");
										 }else{
											 obj.put("bhggllh", grgl[8].toString());
										 }
										//添加日小结值
										//if(i!=0||j!=0){
											glztsSUM = CommonsUtil.getDoubleData(grgl[3])+glztsSUM;
											glyxtsSUM = CommonsUtil.getDoubleData(grgl[4])+glyxtsSUM;
											hggltsSUM = CommonsUtil.getDoubleData(grgl[5])+hggltsSUM;
											bhggltsSUM = CommonsUtil.getDoubleData(grgl[6])+bhggltsSUM;
										//	hglSUM = CommonsUtil.getDoubleData(grgl[7])+hglSUM;
											hglSUM = hggltsSUM/glyxtsSUM*100;
										//}
											if(j%2==0){
												glztsSUM1 = CommonsUtil.getDoubleData(grgl[3])+glztsSUM1;
												glyxtsSUM1 = CommonsUtil.getDoubleData(grgl[4])+glyxtsSUM1;
												hggltsSUM1 = CommonsUtil.getDoubleData(grgl[5])+hggltsSUM1;
												bhggltsSUM1 = CommonsUtil.getDoubleData(grgl[6])+bhggltsSUM1;
											//	hglSUM = CommonsUtil.getDoubleData(grgl[7])+hglSUM;
												hglSUM1 = hggltsSUM1/glyxtsSUM1*100;
											}else{
												glztsSUM2 = CommonsUtil.getDoubleData(grgl[3])+glztsSUM2;
												glyxtsSUM2 = CommonsUtil.getDoubleData(grgl[4])+glyxtsSUM2;
												hggltsSUM2 = CommonsUtil.getDoubleData(grgl[5])+hggltsSUM2;
												bhggltsSUM2 = CommonsUtil.getDoubleData(grgl[6])+bhggltsSUM2;
											//	hglSUM = CommonsUtil.getDoubleData(grgl[7])+hglSUM;
												hglSUM2 = hggltsSUM2/glyxtsSUM2*100;
											}
									}
									
									
								}
									if(dataflg==0){
										obj.put("rownum", "/");
										obj.put("acquisition_time",  dateList[j]);
										obj.put("gqdw", "");
										 obj.put("glzts", "");
										 obj.put("glyxts", "");										
										 obj.put("hgglts", "");										
										 obj.put("bhgglts", "");
										 obj.put("hgl", "");								
										 obj.put("bhggllh", "");										
									}
									arr.add(obj);
							}
							obj.put("rownum", "/");
							obj.put("acquisition_time",  "日小结");
							obj.put("gqdw", "/");
							 obj.put("glzts",String.format("%.2f",glztsSUM));
							 obj.put("glyxts",String.format("%.2f",glyxtsSUM));
							 obj.put("hgglts",String.format("%.2f",hggltsSUM));
							 obj.put("bhgglts",String.format("%.2f",bhggltsSUM));
							 if(hglSUM==0){
								 obj.put("hgl", "--");
							 }else{
								// obj.put("hgl", String.format("%.2f",hglSUM/2));
								 obj.put("hgl", String.format("%.2f",hglSUM));
							 }
							 obj.put("bhggllh", "");							
							 glztsSUMday = glztsSUM+glztsSUMday;
							 glyxtsSUMday = glyxtsSUM+glyxtsSUMday;
							 hggltsSUMday = hggltsSUM+hggltsSUMday;
							 bhggltsSUMday = bhggltsSUM+bhggltsSUMday;
							 hglMday = hglSUM+hglMday;
							 
							 
							 glztsSUMday1 = glztsSUM1+glztsSUMday1;
							 glyxtsSUMday1 = glyxtsSUM1+glyxtsSUMday1;
							 hggltsSUMday1 = hggltsSUM1+hggltsSUMday1;
							 bhggltsSUMday1 = bhggltsSUM1+bhggltsSUMday1;
							 hglMday1 = hglSUM1+hglMday1;
							 
							 glztsSUMday2 = glztsSUM2+glztsSUMday2;
							 glyxtsSUMday2 = glyxtsSUM2+glyxtsSUMday2;
							 hggltsSUMday2 = hggltsSUM2+hggltsSUMday2;
							 bhggltsSUMday2 = bhggltsSUM2+bhggltsSUMday2;
							 hglMday2 = hglSUM2+hglMday2;
							 
							 
							 arr.add(obj);
							}						 
						}else{
							for(String[] dateList:rownum){
								obj = new JSONObject();
								for(String data:dateList){
									obj = new JSONObject();
									obj.put("rownum", "/");
									obj.put("acquisition_time",  data);
									obj.put("gqdw", "");
									 obj.put("glzts", "");
									 obj.put("glyxts", "");										
									 obj.put("hgglts", "");										
									 obj.put("bhgglts", "");
									 obj.put("hgl", "");								
									 obj.put("bhggllh", "");									
									arr.add(obj);
								}
								obj.put("rownum", "/");
								obj.put("acquisition_time",  "日小结");
								obj.put("gqdw", "/");
								 obj.put("glzts", "/");
								 obj.put("glyxts", "/");										
								 obj.put("hgglts", "/");										
								 obj.put("bhgglts", "/");
								 obj.put("hgl", "/");								
								 obj.put("bhggllh", "/");							
								arr.add(obj);
							}						
						}
					if(grgls != null && grgls.size()>0){
						obj = new JSONObject();
						obj.put("rownum", "/");
						obj.put("acquisition_time", "一号供汽站周平均");					
						obj.put("gqdw", "/");
						 obj.put("glzts",String.format("%.2f",glztsSUMday1/7));
						 obj.put("glyxts",String.format("%.2f",glyxtsSUMday1/7));
						 obj.put("hgglts",String.format("%.2f",hggltsSUMday1/7));
						 obj.put("bhgglts",String.format("%.2f",bhggltsSUMday1/7));
						 if(hglMday==0){
							 obj.put("hgl", "--");
						 }else{
							 obj.put("hgl", String.format("%.2f",(hggltsSUMday1/7)/(glyxtsSUMday1/7)*100));
						 }						
						 obj.put("bhggllh","/");						 
						 arr.add(obj);
						 
						 
						 obj.put("rownum", "/");
							obj.put("acquisition_time", "二号供汽站周平均");					
							obj.put("gqdw", "/");
							 obj.put("glzts",String.format("%.2f",glztsSUMday2/7));
							 obj.put("glyxts",String.format("%.2f",glyxtsSUMday2/7));
							 obj.put("hgglts",String.format("%.2f",hggltsSUMday2/7));
							 obj.put("bhgglts",String.format("%.2f",bhggltsSUMday2/7));
							 if(hglMday==0){
								 obj.put("hgl", "--");
							 }else{
								 obj.put("hgl", String.format("%.2f",(hggltsSUMday2/7)/(glyxtsSUMday2/7)*100));
							 }						
							 obj.put("bhggllh","/");						 
						
							 arr.add(obj);
							 
						obj.put("rownum", "/");
						obj.put("acquisition_time", "作业区周平均");					
						obj.put("gqdw", "/");
						 obj.put("glzts",String.format("%.2f",glztsSUMday/7));
						 obj.put("glyxts",String.format("%.2f",glyxtsSUMday/7));
						 obj.put("hgglts",String.format("%.2f",hggltsSUMday/7));
						 obj.put("bhgglts",String.format("%.2f",bhggltsSUMday/7));
						 if(hglMday==0){
							 obj.put("hgl", "--");
						 }else{
							 obj.put("hgl", String.format("%.2f",(hggltsSUMday/7)/(glyxtsSUMday/7)*100));
						 }						
						 obj.put("bhggllh","/");
						 
						 arr.add(obj);
					}else{

						obj = new JSONObject();
						obj.put("rownum", "/");
						obj.put("acquisition_time", "一号供汽站周平均");					
						obj.put("gqdw", "/");
						 obj.put("glzts", "/");
						 obj.put("glyxts", "/");										
						 obj.put("hgglts", "/");										
						 obj.put("bhgglts", "/");
						 obj.put("hgl", "/");								
						 obj.put("bhggllh", "/");
						 arr.add(obj);
						 
						 obj.put("rownum", "/");
							obj.put("acquisition_time", "二号供汽站周平均");					
							obj.put("gqdw", "/");
							 obj.put("glzts", "/");
							 obj.put("glyxts", "/");										
							 obj.put("hgglts", "/");										
							 obj.put("bhgglts", "/");
							 obj.put("hgl", "/");								
							 obj.put("bhggllh", "/");
							 arr.add(obj);
							 
						obj.put("rownum", "/");
						obj.put("acquisition_time", "作业区周平均");					
						obj.put("gqdw", "/");
						 obj.put("glzts", "/");
						 obj.put("glyxts", "/");										
						 obj.put("hgglts", "/");										
						 obj.put("bhgglts", "/");
						 obj.put("hgl", "/");								
						 obj.put("bhggllh", "/");
					   arr.add(obj);						
					}
					if(grglzpm !=null && grglzpm.size()>0){
						String glh1 = grglzpm.get(0)[1].toString();
						double glh1sc = CommonsUtil.getDoubleData(grglzpm.get(0)[2])/60;
						String bhgsc1 = String.format("%.2f",glh1sc);
						String str1 = glh1+"("+bhgsc1+")";
												
						String glh2 = grglzpm.get(1)[1].toString();
						double glh2sc = CommonsUtil.getDoubleData(grglzpm.get(1)[2])/60;
						String bhgsc2 = String.format("%.2f",glh2sc);
						String str2 = glh2+"("+bhgsc2+")";
						
						String glh3 = grglzpm.get(2)[1].toString();
						double glh3sc = CommonsUtil.getDoubleData(grglzpm.get(2)[2])/60;
						String bhgsc3 = String.format("%.2f",glh3sc);
						String str3 = glh3+"("+bhgsc3+")";
						
						String glh4 = grglzpm.get(3)[1].toString();
						double glh4sc = CommonsUtil.getDoubleData(grglzpm.get(3)[2])/60;
						String bhgsc4 = String.format("%.2f",glh4sc);
						String str4 = glh4+"("+bhgsc4+")";
						
						String glh5 = grglzpm.get(4)[1].toString();
						double glh5sc = CommonsUtil.getDoubleData(grglzpm.get(4)[2])/60;
						String bhgsc5 = String.format("%.2f",glh5sc);
						String str5 = glh5+"("+bhgsc5+")";
						
						obj = new JSONObject();
						obj.put("rownum", "/");
						obj.put("acquisition_time", "本周不合格锅炉总时长排名（前五）");					
						obj.put("gqdw","/" );
						obj.put("glzts", str1+" 、"+str2+" 、"+str3+" 、"+str4+" 、"+str5);
						arr.add(obj);
					}else{
						obj = new JSONObject();
						obj.put("rownum", "/");
						obj.put("acquisition_time", "本周不合格锅炉总时长排名（前五）");					
						obj.put("gqdw", "/");
						arr.add(obj);
					}
				   return arr;			
				}

				@Override
				public List<Object[]> searchGrglzbXY(String rq,
						String fields) {
					String sql = "";
					if(!rq.equals("")){
						String stratime =DateBean.getBefore6DAYTime(rq);
						String endtime = rq;
						sql = "select " + fields + " from   PC_RPD_BOILER_SUPERHEAT_YC_T   u where u.ACQUISITION_TIME between TO_DATE('"+stratime+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+endtime+"','YYYY-MM-DD HH24:MI:SS') order by u.ACQUISITION_TIME";
					}else{
						sql = fields;
					}					
					List<Object[]> yyList = lineMeasureDao.searchU2OIL(sql);
					return yyList;
				
				}


				@Override
				public JSONArray searchGrglzbZSC(String txtDate,String sql) {
					JSONObject obj = new JSONObject();
					JSONArray arr = new JSONArray();
					//取一周范围的值				  
					String stratime1 =DateBean.getBefore6DAYTime(txtDate);
					String endtime1 = DateBean.getTimeAfterDAYTime(txtDate);
					
					String sql1 = "select boiler_name,bhgsc from (SELECT BOILER_NAME,SUM(SUPERHEAT_LJ) AS BHGSC FROM " +
							"(select * from PC_RD_BOILER_SUPERHEAT_YC_T  u where u.ACQUISITION_TIME between " +
							"TO_DATE('"+stratime1+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+endtime1+"','YYYY-MM-DD HH24:MI:SS') " +
							"AND where SUPERHEAT_LJ >=240 ORDER BY ACQUISITION_TIME) GROUP BY BOILER_NAME order by BHGSC desc) where rownum<=5";
					List<Object[]> grglzpm = lineMeasureDao.searchU2OIL(sql1);						
					if(grglzpm !=null && grglzpm.size()>0){
						String glh1 = grglzpm.get(0)[1].toString();
						double glh1sc = CommonsUtil.getDoubleData(grglzpm.get(0)[2])/60;
						String bhgsc1 = String.format("%.2f",glh1sc);
						String str1 = glh1+"("+bhgsc1+")";												
						String glh2 = grglzpm.get(1)[1].toString();
						double glh2sc = CommonsUtil.getDoubleData(grglzpm.get(1)[2])/60;
						String bhgsc2 = String.format("%.2f",glh2sc);
						String str2 = glh2+"("+bhgsc2+")";						
						String glh3 = grglzpm.get(2)[1].toString();
						double glh3sc = CommonsUtil.getDoubleData(grglzpm.get(2)[2])/60;
						String bhgsc3 = String.format("%.2f",glh3sc);
						String str3 = glh3+"("+bhgsc3+")";						
						String glh4 = grglzpm.get(3)[1].toString();
						double glh4sc = CommonsUtil.getDoubleData(grglzpm.get(3)[2])/60;
						String bhgsc4 = String.format("%.2f",glh4sc);
						String str4 = glh4+"("+bhgsc4+")";						
						String glh5 = grglzpm.get(4)[1].toString();
						double glh5sc = CommonsUtil.getDoubleData(grglzpm.get(4)[2])/60;
						String bhgsc5 = String.format("%.2f",glh5sc);
						String str5 = glh5+"("+bhgsc5+")";
						
						String shuchu = str1+" 、"+str2+" 、"+str3+" 、"+str4+" 、"+str5;
						obj.put("",shuchu);
					}else{						
					}
					arr.add(obj);
					return arr;					
				}

		
				@SuppressWarnings("unused")
				@Override
				public JSONArray searchFxjl1(List<String> date,String txtYear,String txtMonth) {
					JSONObject obj = new JSONObject();
					JSONArray arr = new JSONArray();
					Object[] cy1fxjl = null;
					String month;
					int dataflg  =0; //数据存在表示 0：不存在； 1： 存在					
					int tmonth = Integer.parseInt(txtMonth);
					if(tmonth<10){
						month = "0".concat(txtMonth);
					}else{
						month = txtMonth;
					}
					
					
					
					int tyear = Integer.parseInt(txtYear);
			//		System.out.println(tyear+"年"+tmonth+"月");					
					Calendar a = Calendar.getInstance(); 
					a.set(Calendar.YEAR, tyear);
					a.set(Calendar.MONTH, tmonth-1);
					a.set(Calendar.DATE, 1);//把日期设置为当月第一天 
					a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天					 
					int maxDate = a.get(Calendar.DATE); 
					int endday = a.getActualMaximum(Calendar.DAY_OF_MONTH);
			//		System.out.println("本月总共有"+maxDate+"天");
			//		System.out.println("本月最后一天是"+endday+"号");
					
					//月合计
					double   jhg_yelMONTH = 0.0;
					double	 jhg_youlMONTH = 0.0;
					double	 jhg_dsMONTH = 0.0;
					double	 tsbMONTH = 0.0;
					double	 wuyMONTH = 0.0;
					double	 wuypkMONTH = 0.0;
					double   fxccyMONTH =0.0;
					double   pankMONTH =0.0;
					double   ftq_105MONTH = 0.0;
					double   ftq_105oqMONTH = 0.0;
					double   ftq_105HSMONTH = 0.0;									
					double   ftq_107MONTH = 0.0;
					double   ftq_107oqMONTH = 0.0;
					double   ftq_107HSMONTH = 0.0;									
					double   ftq_102MONTH = 0.0;
					double   ftq_102oqMONTH = 0.0;
					double   ftq_102HSMONTH = 0.0;									
					double   ftq_c1MONTH = 0.0;
					double   ftq_c1yMONTH = 0.0;									
					double   ftq_103MONTH = 0.0;
					double   ftq_103oqMONTH = 0.0;
					double   ftq_103HSMONTH = 0.0;
					double 	 ftq_s01MONTH=0.0;
					double 	 zjfylMONTH=0.0;
					double   ftq_s01oqMONTH =0.0;
					double 	 ftq_s01hsMONTH =0.0;
					
					double   ftq_s1yMONTH = 0.0;
					double   ftq_s1sMONTH = 0.0;
					double   zyelMONTH=0.0;
					double yelMONTH=0.0;
					double   youlMONTH =0.0;
					//日平均
					double   jhg_yelAVG = 0.0;
					double	 jhg_youlAVG = 0.0;
					double	 jhg_dsAVG = 0.0;
					double	 tsbAVG = 0.0;
					double	 wuyAVG = 0.0;
					double	 wuypkAVG = 0.0;
					double   fxccyAVG =0.0;
					double   pankAVG =0.0;
					double   ftq_105AVG = 0.0;
					double   ftq_105oqAVG = 0.0;
					double   ftq_105HSAVG = 0.0;									
					double   ftq_107AVG = 0.0;
					double   ftq_107oqAVG = 0.0;
					double   ftq_107HSAVG = 0.0;									
					double   ftq_102AVG = 0.0;
					double   ftq_102oqAVG = 0.0;
					double   ftq_102HSAVG = 0.0;									
					double   ftq_c1AVG = 0.0;
					double   ftq_c1yAVG = 0.0;									
					double   ftq_103AVG = 0.0;
					double   ftq_103oqAVG = 0.0;
					double   ftq_103HSAVG = 0.0;
					double 	 ftq_s01AVG=0.0;
					double 	 zjfylAVG=0.0;
					double   ftq_s01oqAVG =0.0;
					double 	 ftq_s01hsAVG =0.0;
					
					double   ftq_s1yAVG = 0.0;
					double   ftq_s1sAVG = 0.0;
					double   zyelAVG=0.0;
					double   yelAVG=0.0;
					double   youlAVG =0.0;
					
					//有多少天的数据
					int count = 0;
					
					String sql = "select rpd_cy1_line_messure_id,report_date,jhg_yel,jhg_youl,jhg_ds,tsb,wuy,"+
								"wuypk,fxccy,pank,ftq_102,ftq_102oq,ftq_102hs,ftq_105,ftq_105oq,ftq_105hs,ftq_107,ftq_107oq,ftq_107hs," +
								" ftq_c1y,ftq_c1,ftq_103,ftq_103oq,ftq_103hs,ftq_s01,zjfyl,ftq_s01oq,ftq_s01hs,"+
								"ftq_s1y,ftq_s1s,zyel,yel,youl,remark  from  PC_RPD_CY1_LINE_MESSURE_V a where 1=1";
								sql += " and a.report_date between '"+date.get(0)+"'and '"+date.get(1)+"' order by a.report_date";						  
					List<Object[]> cy1fx = lineMeasureDao.searchU2OIL(sql);	
					String[][] dates = DateBean.getReportMonths("time17");
					

						for(int i=0;i<10;i++){
							dates[0][i]=txtYear+"-"+month+"-"+dates[0][i];
						}
						for(int i=0;i<10;i++){
							dates[1][i]=txtYear+"-"+month+"-"+dates[1][i];
						}
						for(int i=0;i<maxDate-20;i++){
							dates[2][i]=txtYear+"-"+month+"-"+dates[2][i];
						}
						for(int i=maxDate-20;i<11;i++){
							dates[2][i]="";
						}						
						obj = new JSONObject();							
							if(cy1fx !=null && cy1fx.size()>0){	
								for(String[] dateList:dates){	
									
									//用于统计没询都有多少天
									int xcount = 0;
									
									double   jhg_yelSUM = 0.0;
									double	 jhg_youlSUM = 0.0;
									double	 jhg_dsSUM = 0.0;
									double	 tsbSUM = 0.0;
									double	 wuySUM = 0.0;
									double	 wuypkSUM = 0.0;
									double   fxccySUM =0.0;
									double   pankSUM =0.0;
									double   ftq_105SUM = 0.0;
									double   ftq_105oqSUM = 0.0;
									double   ftq_105HSSUM = 0.0;									
									double   ftq_107SUM = 0.0;
									double   ftq_107oqSUM = 0.0;
									double   ftq_107HSSUM = 0.0;									
									double   ftq_102SUM = 0.0;
									double   ftq_102oqSUM = 0.0;
									double   ftq_102HSSUM = 0.0;									
									double   ftq_c1SUM = 0.0;
									double   ftq_c1ySUM = 0.0;									
									double   ftq_103SUM = 0.0;
									double   ftq_103oqSUM = 0.0;
									double   ftq_103HSSUM = 0.0;
									double 	 ftq_s01SUM=0.0;
									double 	 zjfylSUM=0.0;
									double   ftq_s01oqSUM =0.0;
									double 	 ftq_s01hsSUM =0.0;
									
									double   ftq_s1ySUM = 0.0;
									double   ftq_s1sSUM = 0.0;
									double   zyelSUM=0.0;
									double   yelSUM=0.0;
									double   youlSUM =0.0;
									
										 for(String data:dateList){
											 obj = new JSONObject();
												cy1fxjl = null;
												dataflg = 0;
												
												for(Object[] cy1f:cy1fx){
													if(data.toString().equals(cy1f[1].toString())){
														dataflg = 1;
														cy1fxjl = cy1f;
												}
											}																								
									if(dataflg == 1){			
										obj.put("rpd_cy1_line_messure_id", cy1fxjl[0]);
										obj.put("report_date", cy1fxjl[1].toString());										
										 if(cy1fxjl[2]==null || "".equals(cy1fxjl[2].toString())){
											 obj.put("jhg_yel", " ");
										 }else{
											 obj.put("jhg_yel", CommonsUtil.getNotTwoDataZero(cy1fxjl[2]));
										 	}
										 if(cy1fxjl[3]==null || "".equals(cy1fxjl[3].toString()) ){
											 obj.put("jhg_youl", " ");
										 }else{
											 obj.put("jhg_youl", CommonsUtil.getNotTwoDataZero(cy1fxjl[3]));
										 	}
										 if(cy1fxjl[4]==null || "".equals(cy1fxjl[4].toString()) ){
											 obj.put("jhg_ds", " ");
										 }else{
											 obj.put("jhg_ds", CommonsUtil.getNotTwoDataZero(cy1fxjl[4]));
										 	}
										 if(cy1fxjl[5]==null || "".equals(cy1fxjl[5].toString()) ){
											 obj.put("tsb", " ");
										 }else{
											 obj.put("tsb", CommonsUtil.getNotTwoDataZero(cy1fxjl[5]));
										 	}
										 if(cy1fxjl[6]==null || "".equals(cy1fxjl[6].toString()) ){
											 obj.put("wuy", " ");
										 }else{
											 obj.put("wuy", CommonsUtil.getNotTwoDataZero(cy1fxjl[6]));
										 	}
										 if(cy1fxjl[7]==null || "".equals(cy1fxjl[7].toString()) ){
											 obj.put("wuypk", " ");
										 }else{
											 obj.put("wuypk", CommonsUtil.getNotTwoDataZero(cy1fxjl[7]));
										 	}
										 if(cy1fxjl[8]==null || "".equals(cy1fxjl[8].toString())){
											 obj.put("fxccy", " ");
										 }else{
											 obj.put("fxccy", CommonsUtil.getNotTwoDataZero(cy1fxjl[8]));
										 	}
										 if(cy1fxjl[9]==null || "".equals(cy1fxjl[9].toString())  ){
											 obj.put("pank", " ");
										 }else{
											 obj.put("pank", CommonsUtil.getNotTwoDataZero(cy1fxjl[9]));
										 	}
										 if(cy1fxjl[10]==null || "".equals(cy1fxjl[10].toString())  ){
											 obj.put("ftq_102", " ");
										 }else{
											 obj.put("ftq_102", CommonsUtil.getNotTwoDataZero(cy1fxjl[10]));
										 	}
										 if(cy1fxjl[11]==null || "".equals(cy1fxjl[11].toString())  ){
											 obj.put("ftq_102oq", " ");
										 }else{
											 obj.put("ftq_102oq", CommonsUtil.getNotTwoDataZero(cy1fxjl[11]));
										 	}
										 
										 if(cy1fxjl[12]==null || "".equals(cy1fxjl[12].toString())  ){
											 obj.put("ftq_102hs", " ");
										 }else{
											 obj.put("ftq_102hs", CommonsUtil.getNotTwoDataZero(cy1fxjl[12]));
										 	}
										 
										 if(cy1fxjl[13]==null || "".equals(cy1fxjl[13].toString())  ){
											 obj.put("ftq_105", " ");
										 }else{
											 obj.put("ftq_105", CommonsUtil.getNotTwoDataZero(cy1fxjl[13]));
										 	}
										 if(cy1fxjl[14]==null || "".equals(cy1fxjl[14].toString()) ){
											 obj.put("ftq_105oq", " ");
										 }else{
											 obj.put("ftq_105oq", CommonsUtil.getNotTwoDataZero(cy1fxjl[14]));
										 	}
										 
										 if(cy1fxjl[15]==null || "".equals(cy1fxjl[15].toString())  ){
											 obj.put("ftq_105hs", " ");
										 }else{
											 obj.put("ftq_105hs", CommonsUtil.getNotTwoDataZero(cy1fxjl[15]));
										 	}
										 
										 if(cy1fxjl[16]==null || "".equals(cy1fxjl[16].toString())  ){
											 obj.put("ftq_107", " ");
										 }else{
											 obj.put("ftq_107", CommonsUtil.getNotTwoDataZero(cy1fxjl[16]));
										 	}
										 if(cy1fxjl[17]==null || "".equals(cy1fxjl[17].toString()) ){
											 obj.put("ftq_107oq", " ");
										 }else{
											 obj.put("ftq_107oq", CommonsUtil.getNotTwoDataZero(cy1fxjl[17]));
										 	}
										 
										 if(cy1fxjl[18]==null || "".equals(cy1fxjl[18].toString())  ){
											 obj.put("ftq_107hs", " ");
										 }else{
											 obj.put("ftq_107hs", CommonsUtil.getNotTwoDataZero(cy1fxjl[18]));
										 	}
										 
										 if(cy1fxjl[19]==null || "".equals(cy1fxjl[19].toString())  ){
											 obj.put("ftq_c1", " ");
										 }else{
											 obj.put("ftq_c1", CommonsUtil.getNotTwoDataZero(cy1fxjl[19]));
										 	}
			
										 if(cy1fxjl[20]==null || "".equals(cy1fxjl[20].toString())  ){
											 obj.put("ftq_c1y", " ");
										 }else{
											 obj.put("ftq_c1y", CommonsUtil.getNotTwoDataZero(cy1fxjl[20]));
										 	}
										
										 if(cy1fxjl[21]==null || "".equals(cy1fxjl[21].toString())  ){
											 obj.put("ftq_103", " ");
										 }else{
											 obj.put("ftq_103", CommonsUtil.getNotTwoDataZero(cy1fxjl[21]));
										 }
										 if(cy1fxjl[22]==null || "".equals(cy1fxjl[22].toString())  ){
											 obj.put("ftq_103oq", " ");
										 }else{
											 obj.put("ftq_103oq", CommonsUtil.getNotTwoDataZero(cy1fxjl[22]));
										 } 
										 if(cy1fxjl[23]==null || "".equals(cy1fxjl[23].toString()) ){
											 obj.put("ftq_103hs", " ");
										 }else{
											 obj.put("ftq_103hs", CommonsUtil.getNotTwoDataZero(cy1fxjl[23]));
										 }
										 
										 if(cy1fxjl[24]==null || "".equals(cy1fxjl[24].toString())  ){
											 obj.put("ftq_s01", " ");
										 }else{
											 obj.put("ftq_s01", CommonsUtil.getNotTwoDataZero(cy1fxjl[24]));
										 }									 
										
										 if(cy1fxjl[25]==null || "".equals(cy1fxjl[25].toString())  ){
											 obj.put("zjfyl", " ");
										 }else{
											 obj.put("zjfyl", CommonsUtil.getNotTwoDataZero(cy1fxjl[25]));
										 }	
										 
										 if(cy1fxjl[26]==null || "".equals(cy1fxjl[26].toString())  ){
											 obj.put("ftq_s01oq", " ");
										 }else{
											 obj.put("ftq_s01oq", CommonsUtil.getNotTwoDataZero(cy1fxjl[26]));
										 }
						
										 if(cy1fxjl[27]==null || "".equals(cy1fxjl[27].toString())  ){
											 obj.put("ftq_s01hs","");												
										 }else{			
					
											 obj.put("ftq_s01hs",CommonsUtil.getNotTwoDataZero(cy1fxjl[27]));
										 }
										 
										 if(cy1fxjl[28]==null || "".equals(cy1fxjl[28].toString()) ){
											 obj.put("ftq_s1y", " ");
										 }else{
											 obj.put("ftq_s1y", CommonsUtil.getNotTwoDataZero(cy1fxjl[28]));
										 } 

										 if(cy1fxjl[29]==null || "".equals(cy1fxjl[29].toString()) ){
											 obj.put("ftq_s1s", " ");
										 }else{
											 obj.put("ftq_s1s", CommonsUtil.getNotTwoDataZero(cy1fxjl[29]));
										 }
										
										 if(cy1fxjl[30]==null || "".equals(cy1fxjl[30].toString()) ){
											 obj.put("zyel", " ");
										 }else{
											 obj.put("zyel", CommonsUtil.getNotTwoDataZero(cy1fxjl[30]));
										 }
										 
										 if(cy1fxjl[31]==null || "".equals(cy1fxjl[31].toString()) ){
											 obj.put("yel", " ");
										 }else{
											 obj.put("yel", CommonsUtil.getNotTwoDataZero(cy1fxjl[31]));
										 }
										 if(cy1fxjl[32]==null || "".equals(cy1fxjl[32].toString()) ){
											 obj.put("youl", " ");
										 }else{
											 obj.put("youl", CommonsUtil.getNotTwoDataZero(cy1fxjl[32]));
										 }
						
										 if(cy1fxjl[33]==null || "".equals(cy1fxjl[33].toString())){
											 obj.put("remark", " ");
										 }else{
											 obj.put("remark", cy1fxjl[33].toString());
										 }		
										 
										 //统计天数
										 xcount++;
										 count++;
										 	//计算一旬的和
										 jhg_yelSUM = CommonsUtil.getDoubleData(cy1fxjl[2])+jhg_yelSUM;											     
										 jhg_youlSUM = CommonsUtil.getDoubleData(cy1fxjl[3])+jhg_youlSUM;	
										 jhg_dsSUM = CommonsUtil.getDoubleData(cy1fxjl[4])+jhg_dsSUM;	
										 tsbSUM = CommonsUtil.getDoubleData(cy1fxjl[5])+tsbSUM;	
										 wuySUM = CommonsUtil.getDoubleData(cy1fxjl[6])+wuySUM;	
										 wuypkSUM = CommonsUtil.getDoubleData(cy1fxjl[7])+wuypkSUM;	
										 fxccySUM = CommonsUtil.getDoubleData(cy1fxjl[8])+fxccySUM;	
										 pankSUM = CommonsUtil.getDoubleData(cy1fxjl[9])+pankSUM;											     
										 ftq_102SUM = CommonsUtil.getDoubleData(cy1fxjl[10])+ftq_102SUM;
										 ftq_102oqSUM = CommonsUtil.getDoubleData(cy1fxjl[11])+ftq_102oqSUM;
										 ftq_102HSSUM = CommonsUtil.getDoubleData(cy1fxjl[12])+ftq_102HSSUM;
										 ftq_105SUM = CommonsUtil.getDoubleData(cy1fxjl[13])+ftq_105SUM;
										 ftq_105oqSUM = CommonsUtil.getDoubleData(cy1fxjl[14])+ftq_105oqSUM;
										 ftq_105HSSUM = CommonsUtil.getDoubleData(cy1fxjl[15])+ftq_105HSSUM;
										 ftq_107SUM = CommonsUtil.getDoubleData(cy1fxjl[16])+ftq_107SUM;
										 ftq_107oqSUM = CommonsUtil.getDoubleData(cy1fxjl[17])+ftq_107oqSUM;
										 ftq_107HSSUM = CommonsUtil.getDoubleData(cy1fxjl[18])+ftq_107HSSUM;
										 
										 ftq_c1SUM = CommonsUtil.getDoubleData(cy1fxjl[19])+ftq_c1SUM;
										 ftq_c1ySUM = CommonsUtil.getDoubleData(cy1fxjl[20])+ftq_c1ySUM;
										
										 ftq_103SUM = CommonsUtil.getDoubleData(cy1fxjl[21])+ftq_103SUM;
										 ftq_103oqSUM = CommonsUtil.getDoubleData(cy1fxjl[22])+ftq_103oqSUM;
										 ftq_103HSSUM = CommonsUtil.getDoubleData(cy1fxjl[23])+ftq_103HSSUM;
										 ftq_s01SUM = CommonsUtil.getDoubleData(cy1fxjl[24])+ftq_s01SUM;
										 zjfylSUM = CommonsUtil.getDoubleData(cy1fxjl[25])+zjfylSUM;
										 ftq_s01oqSUM =CommonsUtil.getDoubleData(cy1fxjl[26])+ftq_s01oqSUM;
										 ftq_s01hsSUM = CommonsUtil.getDoubleData(cy1fxjl[27])+ftq_s01hsSUM;										 
										 ftq_s1ySUM = CommonsUtil.getDoubleData(cy1fxjl[28])+ftq_s1ySUM;
										 ftq_s1sSUM = CommonsUtil.getDoubleData(cy1fxjl[29])+ftq_s1sSUM;
										 zyelSUM = CommonsUtil.getDoubleData(cy1fxjl[30])+zyelSUM;
										 yelSUM = CommonsUtil.getDoubleData(cy1fxjl[31])+yelSUM;
										 youlSUM = CommonsUtil.getDoubleData(cy1fxjl[32])+youlSUM;
									
										 //计算月平均
										 jhg_yelAVG = CommonsUtil.getDoubleData(cy1fxjl[2])+jhg_yelAVG;											     
										 jhg_youlAVG = CommonsUtil.getDoubleData(cy1fxjl[3])+jhg_youlAVG;	
										 jhg_dsAVG = CommonsUtil.getDoubleData(cy1fxjl[4])+jhg_dsAVG;	
										 tsbAVG = CommonsUtil.getDoubleData(cy1fxjl[5])+tsbAVG;	
										 wuyAVG = CommonsUtil.getDoubleData(cy1fxjl[6])+wuyAVG;	
										 wuypkAVG = CommonsUtil.getDoubleData(cy1fxjl[7])+wuypkAVG;	
										 fxccyAVG = CommonsUtil.getDoubleData(cy1fxjl[8])+fxccyAVG;	
										 pankAVG = CommonsUtil.getDoubleData(cy1fxjl[9])+pankAVG;											     
										 ftq_102AVG= CommonsUtil.getDoubleData(cy1fxjl[10])+ftq_102AVG;
										 ftq_102oqAVG = CommonsUtil.getDoubleData(cy1fxjl[11])+ftq_102oqAVG;
										 ftq_102HSAVG = CommonsUtil.getDoubleData(cy1fxjl[12])+ftq_102HSAVG;
										 ftq_105AVG = CommonsUtil.getDoubleData(cy1fxjl[13])+ftq_105AVG;
										 ftq_105oqAVG = CommonsUtil.getDoubleData(cy1fxjl[14])+ftq_105oqAVG;
										 ftq_105HSAVG = CommonsUtil.getDoubleData(cy1fxjl[15])+ftq_105HSAVG;
										 ftq_107AVG = CommonsUtil.getDoubleData(cy1fxjl[16])+ftq_107AVG;
										 ftq_107oqAVG = CommonsUtil.getDoubleData(cy1fxjl[17])+ftq_107oqAVG;
										 ftq_107HSAVG = CommonsUtil.getDoubleData(cy1fxjl[18])+ftq_107HSAVG;
										 
										 ftq_c1AVG = CommonsUtil.getDoubleData(cy1fxjl[19])+ftq_c1AVG;
										 ftq_c1yAVG = CommonsUtil.getDoubleData(cy1fxjl[20])+ftq_c1yAVG;
										
										 ftq_103AVG = CommonsUtil.getDoubleData(cy1fxjl[21])+ftq_103AVG;
										 ftq_103oqAVG = CommonsUtil.getDoubleData(cy1fxjl[22])+ftq_103oqAVG;
										 ftq_103HSAVG = CommonsUtil.getDoubleData(cy1fxjl[23])+ftq_103HSAVG;
										 ftq_s01AVG= CommonsUtil.getDoubleData(cy1fxjl[24])+ftq_s01AVG;
										 zjfylAVG = CommonsUtil.getDoubleData(cy1fxjl[25])+zjfylAVG;
										 ftq_s01oqAVG =CommonsUtil.getDoubleData(cy1fxjl[26])+ftq_s01oqAVG;
										 ftq_s01hsAVG = CommonsUtil.getDoubleData(cy1fxjl[27])+ftq_s01hsAVG;										 
										 ftq_s1yAVG = CommonsUtil.getDoubleData(cy1fxjl[28])+ftq_s1yAVG;
										 ftq_s1sAVG = CommonsUtil.getDoubleData(cy1fxjl[29])+ftq_s1sAVG;
										 zyelAVG = CommonsUtil.getDoubleData(cy1fxjl[30])+zyelAVG;
										 yelAVG = CommonsUtil.getDoubleData(cy1fxjl[31])+yelAVG;
										 youlAVG = CommonsUtil.getDoubleData(cy1fxjl[32])+youlAVG;
										 
										 //计算月总和
										 jhg_yelMONTH = CommonsUtil.getDoubleData(cy1fxjl[2])+jhg_yelMONTH;											     
										 jhg_youlMONTH = CommonsUtil.getDoubleData(cy1fxjl[3])+jhg_youlMONTH;	
										 jhg_dsMONTH = CommonsUtil.getDoubleData(cy1fxjl[4])+jhg_dsMONTH;	
										 tsbMONTH = CommonsUtil.getDoubleData(cy1fxjl[5])+tsbMONTH;	
										 wuyMONTH = CommonsUtil.getDoubleData(cy1fxjl[6])+wuyMONTH;	
										 wuypkMONTH = CommonsUtil.getDoubleData(cy1fxjl[7])+wuypkMONTH;	
										 fxccyMONTH = CommonsUtil.getDoubleData(cy1fxjl[8])+fxccyMONTH;	
										 pankMONTH = CommonsUtil.getDoubleData(cy1fxjl[9])+pankMONTH;											     
										 ftq_102MONTH= CommonsUtil.getDoubleData(cy1fxjl[10])+ftq_102MONTH;
										 ftq_102oqMONTH = CommonsUtil.getDoubleData(cy1fxjl[11])+ftq_102oqMONTH;
										 ftq_102HSMONTH = CommonsUtil.getDoubleData(cy1fxjl[12])+ftq_102HSMONTH;
										 ftq_105MONTH = CommonsUtil.getDoubleData(cy1fxjl[13])+ftq_105MONTH;
										 ftq_105oqMONTH = CommonsUtil.getDoubleData(cy1fxjl[14])+ftq_105oqMONTH;
										 ftq_105HSMONTH = CommonsUtil.getDoubleData(cy1fxjl[15])+ftq_105HSMONTH;
										 ftq_107MONTH = CommonsUtil.getDoubleData(cy1fxjl[16])+ftq_107MONTH;
										 ftq_107oqMONTH = CommonsUtil.getDoubleData(cy1fxjl[17])+ftq_107oqMONTH;
										 ftq_107HSMONTH = CommonsUtil.getDoubleData(cy1fxjl[18])+ftq_107HSMONTH;
										 
										 ftq_c1MONTH = CommonsUtil.getDoubleData(cy1fxjl[19])+ftq_c1MONTH;
										 ftq_c1yMONTH = CommonsUtil.getDoubleData(cy1fxjl[20])+ftq_c1yMONTH;
										
										 ftq_103MONTH = CommonsUtil.getDoubleData(cy1fxjl[21])+ftq_103MONTH;
										 ftq_103oqMONTH = CommonsUtil.getDoubleData(cy1fxjl[22])+ftq_103oqMONTH;
										 ftq_103HSMONTH = CommonsUtil.getDoubleData(cy1fxjl[23])+ftq_103HSMONTH;
										 ftq_s01MONTH= CommonsUtil.getDoubleData(cy1fxjl[24])+ftq_s01MONTH;
										 zjfylMONTH = CommonsUtil.getDoubleData(cy1fxjl[25])+zjfylMONTH;
										 ftq_s01oqMONTH =CommonsUtil.getDoubleData(cy1fxjl[26])+ftq_s01oqMONTH;
										 ftq_s01hsMONTH = CommonsUtil.getDoubleData(cy1fxjl[27])+ftq_s01hsMONTH;										 
										 ftq_s1yMONTH = CommonsUtil.getDoubleData(cy1fxjl[28])+ftq_s1yMONTH;
										 ftq_s1sMONTH = CommonsUtil.getDoubleData(cy1fxjl[29])+ftq_s1sMONTH;
										 zyelMONTH = CommonsUtil.getDoubleData(cy1fxjl[30])+zyelMONTH;
										 yelMONTH = CommonsUtil.getDoubleData(cy1fxjl[31])+yelMONTH;
										 youlMONTH = CommonsUtil.getDoubleData(cy1fxjl[32])+youlMONTH;
									}																	
									if(dataflg==0){
										obj.put("rpd_cy1_line_messure_id", "");
										obj.put("report_date",  data.toString());
										obj.put("jhg_yel", "");
										obj.put("jhg_youl", "");
										obj.put("jhg_ds","");										
										obj.put("tsb", "");										
										obj.put("wuy", "");
										obj.put("wuypk", "");								
										obj.put("fxccy", "");
										obj.put("pank", "");
										obj.put("ftq_102","" );
										obj.put("ftq_102oq","" );
										obj.put("ftq_102hs", "");										
										
										obj.put("ftq_105", "");
										obj.put("ftq_105oq", "");
										obj.put("ftq_105hs", "");
										
										obj.put("ftq_107", "");
										obj.put("ftq_107oq", "");
										obj.put("ftq_107hs", "");
										
										obj.put("ftq_c1y", "");									
										obj.put("ftq_c1", "");										
										
										
										obj.put("ftq_103", "");								
										obj.put("ftq_103oq", "");
										obj.put("ftq_103hs", "");
																					
										obj.put("ftq_s01", "");
										obj.put("zjfyl", "");										
										obj.put("ftq_s01oq","");
										obj.put("ftq_s01hs", "");																							
										obj.put("ftq_s1y", "");
										obj.put("ftq_s1s", "");	

										obj.put("zyel", "");
										obj.put("yel", "");
										obj.put("youl", "");
										obj.put("remark", "");	
									}
									arr.add(obj);
								 }
										 	obj.put("rpd_cy1_line_messure_id", "");
											obj.put("report_date",  "旬合计");
											obj.put("jhg_yel", String.format("%.2f",jhg_yelSUM));
											obj.put("jhg_youl", String.format("%.2f",jhg_youlSUM));
											obj.put("jhg_ds", String.format("%.2f",jhg_dsSUM));										
											obj.put("tsb", String.format("%.2f",tsbSUM));										
											obj.put("wuy", String.format("%.2f",wuySUM));
											obj.put("wuypk", String.format("%.2f",wuypkSUM));								
											obj.put("fxccy", String.format("%.2f",fxccySUM));
											obj.put("pank", String.format("%.2f",pankSUM));
											obj.put("ftq_102",String.format("%.2f",ftq_102SUM) );
											obj.put("ftq_102oq",String.format("%.2f",ftq_102oqSUM) );
											obj.put("ftq_102hs", String.format("%.2f",ftq_102HSSUM/xcount));										
											
											obj.put("ftq_105", String.format("%.2f",ftq_105SUM));
											obj.put("ftq_105oq", String.format("%.2f",ftq_105oqSUM));
											obj.put("ftq_105hs", String.format("%.2f",ftq_105HSSUM/xcount));
											
											obj.put("ftq_107", String.format("%.2f",ftq_107SUM));
											obj.put("ftq_107oq", String.format("%.2f",ftq_107oqSUM));
											obj.put("ftq_107hs", String.format("%.2f",ftq_107HSSUM/xcount));	
											
											obj.put("ftq_c1y", String.format("%.2f",ftq_c1ySUM));								
											obj.put("ftq_c1", String.format("%.2f",ftq_c1SUM));										
											
											
											obj.put("ftq_103", String.format("%.2f",ftq_103SUM));								
											obj.put("ftq_103oq", String.format("%.2f",ftq_103oqSUM));
											obj.put("ftq_103hs", String.format("%.2f",ftq_103HSSUM/xcount));
																						
											obj.put("ftq_s01", String.format("%.2f",ftq_s01SUM));
											obj.put("zjfyl", String.format("%.2f",zjfylSUM));										
											obj.put("ftq_s01oq", String.format("%.2f",ftq_s01oqSUM));
											obj.put("ftq_s01hs", String.format("%.2f",ftq_s01hsSUM/xcount));																							
											obj.put("ftq_s1y", String.format("%.2f",ftq_s1ySUM));
											obj.put("ftq_s1s", String.format("%.2f",ftq_s1sSUM));	
	
											obj.put("zyel", String.format("%.2f",zyelSUM));
											obj.put("yel", String.format("%.2f",yelSUM));
											obj.put("youl", String.format("%.2f",youlSUM));
											obj.put("remark", "");			
										arr.add(obj);					
							}
						}else {
							for(String[] dateList:dates){
								obj = new JSONObject();
								for(String data:dateList){
									obj = new JSONObject();
									obj.put("rpd_cy1_line_messure_id", "");
									obj.put("report_date",  data.toString());
									obj.put("jhg_yel", "");
									obj.put("jhg_youl", "");
									obj.put("jhg_ds","");										
									obj.put("tsb", "");										
									obj.put("wuy", "");
									obj.put("wuypk", "");								
									obj.put("fxccy", "");
									obj.put("pank", "");
									obj.put("ftq_102","" );
									obj.put("ftq_102oq","" );
									obj.put("ftq_102hs", "");										
									
									obj.put("ftq_105", "");
									obj.put("ftq_105oq", "");
									obj.put("ftq_105hs", "");
									
									obj.put("ftq_107", "");
									obj.put("ftq_107oq", "");
									obj.put("ftq_107hs", "");	
									
									obj.put("ftq_c1y", "");								
									obj.put("ftq_c1", "");										
							
									
									obj.put("ftq_103", "");								
									obj.put("ftq_103oq", "");
									obj.put("ftq_103hs", "");
																				
									obj.put("ftq_s01", "");
									obj.put("zjfyl", "");										
									obj.put("ftq_s01oq","");
									obj.put("ftq_s01hs", "");																							
									obj.put("ftq_s1y", "");
									obj.put("ftq_s1s", "");	

									obj.put("zyel", "");
									obj.put("yel", "");
									obj.put("youl", "");
									obj.put("remark", "");	
									arr.add(obj);	
								}
								obj.put("rpd_cy1_line_messure_id", "");
								obj.put("report_date",  "旬合计");
								obj.put("jhg_yel", "");
								obj.put("jhg_youl", "");
								obj.put("jhg_ds","");										
								obj.put("tsb", "");										
								obj.put("wuy", "");
								obj.put("wuypk", "");								
								obj.put("fxccy", "");
								obj.put("pank", "");
								obj.put("ftq_102","" );
								obj.put("ftq_102oq","" );
								obj.put("ftq_102hs", "");										
								
								obj.put("ftq_105", "");
								obj.put("ftq_105oq", "");
								obj.put("ftq_105hs", "");
								
								obj.put("ftq_107", "");
								obj.put("ftq_107oq", "");
								obj.put("ftq_107hs", "");
								
								obj.put("ftq_c1y", "");								
								obj.put("ftq_c1", "");										
							
								
								obj.put("ftq_103", "");								
								obj.put("ftq_103oq", "");
								obj.put("ftq_103hs", "");
																			
								obj.put("ftq_s01", "");
								obj.put("zjfyl", "");										
								obj.put("ftq_s01oq","");
								obj.put("ftq_s01hs", "");																							
								obj.put("ftq_s1y", "");
								obj.put("ftq_s1s", "");	
								obj.put("zyel", "");
								obj.put("yel", "");
								obj.put("youl", "");
								obj.put("remark", "");	
								arr.add(obj);					
							}	
						}	
													
					if(cy1fx != null && cy1fx.size()>0){
						obj = new JSONObject();
						obj.put("rpd_cy1_line_messure_id", "");
						obj.put("report_date",  "日平均产量");
						obj.put("jhg_yel", String.format("%.2f",jhg_yelAVG/count));
						obj.put("jhg_youl", String.format("%.2f",jhg_youlAVG/count));
						obj.put("jhg_ds", String.format("%.2f",jhg_dsAVG/count));										
						obj.put("tsb", String.format("%.2f",tsbAVG/count));										
						obj.put("wuy", String.format("%.2f",wuyAVG/count));
						obj.put("wuypk",String.format("%.2f",wuypkAVG/count));								
						obj.put("fxccy", String.format("%.2f",fxccyAVG/count));
						obj.put("pank", String.format("%.2f",pankAVG/count));
						obj.put("ftq_102",String.format("%.2f",ftq_102AVG/count) );
						obj.put("ftq_102oq",String.format("%.2f",ftq_102oqAVG/count) );
						obj.put("ftq_102hs", String.format("%.2f",ftq_102HSAVG/count));										
						
						obj.put("ftq_105", String.format("%.2f",ftq_105AVG/count));
						obj.put("ftq_105oq", String.format("%.2f",ftq_105oqAVG/count));
						obj.put("ftq_105hs", String.format("%.2f",ftq_105HSAVG/count));
						
						obj.put("ftq_107", String.format("%.2f",ftq_107AVG/count));
						obj.put("ftq_107oq", String.format("%.2f",ftq_107oqAVG/count));
						obj.put("ftq_107hs", String.format("%.2f",ftq_107HSAVG/count));	
						
						obj.put("ftq_c1y", String.format("%.2f",ftq_c1yAVG/count));								
						obj.put("ftq_c1", String.format("%.2f",ftq_c1AVG/count));										
						
						
						obj.put("ftq_103", String.format("%.2f",ftq_103AVG/count));								
						obj.put("ftq_103oq", String.format("%.2f",ftq_103oqAVG/count));
						obj.put("ftq_103hs", String.format("%.2f",ftq_103HSAVG/count));
																	
						obj.put("ftq_s01", String.format("%.2f",ftq_s01AVG/count));
						obj.put("zjfyl", String.format("%.2f",zjfylAVG/count));										
						obj.put("ftq_s01oq", String.format("%.2f",ftq_s01oqAVG/count));
						obj.put("ftq_s01hs", String.format("%.2f",ftq_s01hsAVG/count));																							
						obj.put("ftq_s1y", String.format("%.2f",ftq_s1yAVG/count));
						obj.put("ftq_s1s", String.format("%.2f",ftq_s1sAVG/count));	

						obj.put("zyel", String.format("%.2f",zyelAVG/count));
						obj.put("yel", String.format("%.2f",yelAVG/count));
						obj.put("youl", String.format("%.2f",youlAVG/count));
						obj.put("remark", "");			
						arr.add(obj);
						
						
						obj.put("rpd_cy1_line_messure_id", "");
						obj.put("report_date",  "月总产量");
						obj.put("jhg_yel", String.format("%.2f",jhg_yelMONTH));
						obj.put("jhg_youl", String.format("%.2f",jhg_youlMONTH));
						obj.put("jhg_ds", String.format("%.2f",jhg_dsMONTH));										
						obj.put("tsb", String.format("%.2f",tsbMONTH));										
						obj.put("wuy", String.format("%.2f",wuyMONTH));
						obj.put("wuypk", String.format("%.2f",wuypkMONTH));								
						obj.put("fxccy", String.format("%.2f",fxccyMONTH));
						obj.put("pank", String.format("%.2f",pankMONTH));
						obj.put("ftq_102",String.format("%.2f",ftq_102MONTH) );
						obj.put("ftq_102oq",String.format("%.2f",ftq_102oqMONTH) );
						obj.put("ftq_102hs", String.format("%.2f",ftq_102HSMONTH/count));										
						
						obj.put("ftq_105", String.format("%.2f",ftq_105MONTH));
						obj.put("ftq_105oq", String.format("%.2f",ftq_105oqMONTH));
						obj.put("ftq_105hs", String.format("%.2f",ftq_105HSMONTH/count));
						
						obj.put("ftq_107", String.format("%.2f",ftq_107MONTH));
						obj.put("ftq_107oq", String.format("%.2f",ftq_107oqMONTH));
						obj.put("ftq_107hs", String.format("%.2f",ftq_107HSMONTH/count));
						
						obj.put("ftq_c1y", String.format("%.2f",ftq_c1yMONTH));
												
						obj.put("ftq_c1", String.format("%.2f",ftq_c1MONTH));										
						
						obj.put("ftq_103", String.format("%.2f",ftq_103MONTH));								
						obj.put("ftq_103oq", String.format("%.2f",ftq_103oqMONTH));
						obj.put("ftq_103hs", String.format("%.2f",ftq_103HSMONTH/count));
																	
						obj.put("ftq_s01", String.format("%.2f",ftq_s01MONTH));
						obj.put("zjfyl", String.format("%.2f",zjfylMONTH));										
						obj.put("ftq_s01oq", String.format("%.2f",ftq_s01oqMONTH));
						obj.put("ftq_s01hs", String.format("%.2f",ftq_s01hsMONTH/count));																							
						obj.put("ftq_s1y", String.format("%.2f",ftq_s1yMONTH));
						obj.put("ftq_s1s", String.format("%.2f",ftq_s1sMONTH));	

						obj.put("zyel", String.format("%.2f",zyelMONTH));
						obj.put("yel", String.format("%.2f",yelMONTH));
						obj.put("youl", String.format("%.2f",youlMONTH));
						obj.put("remark", "");		 
						 arr.add(obj);
					}else{
						obj = new JSONObject();
						
						obj.put("rpd_cy1_line_messure_id", "");
						obj.put("report_date",  "日平均产量");
						obj.put("jhg_yel", "");
						obj.put("jhg_youl", "");
						obj.put("jhg_ds","");										
						obj.put("tsb", "");										
						obj.put("wuy", "");
						obj.put("wuypk", "");								
						obj.put("fxccy", "");
						obj.put("pank", "");
						obj.put("ftq_102","" );
						obj.put("ftq_102oq","" );
						obj.put("ftq_102hs", "");										
						
						obj.put("ftq_105", "");
						obj.put("ftq_105oq", "");
						obj.put("ftq_105hs", "");
						
						obj.put("ftq_107", "");
						obj.put("ftq_107oq", "");
						obj.put("ftq_107hs", "");											
						obj.put("ftq_c1y", "");								
						obj.put("ftq_c1", "");										
					
						
						obj.put("ftq_103", "");								
						obj.put("ftq_103oq", "");
						obj.put("ftq_103hs", "");
																	
						obj.put("ftq_s01", "");
						obj.put("zjfyl", "");										
						obj.put("ftq_s01oq","");
						obj.put("ftq_s01hs", "");																							
						obj.put("ftq_s1y", "");
						obj.put("ftq_s1s", "");	

						obj.put("zyel", "");
						obj.put("yel", "");
						obj.put("youl", "");
						obj.put("remark", "");	
						arr.add(obj);
						
						obj.put("rpd_cy1_line_messure_id", "");
						obj.put("report_date",  "月总产量");
						obj.put("jhg_yel", "");
						obj.put("jhg_youl", "");
						obj.put("jhg_ds","");										
						obj.put("tsb", "");										
						obj.put("wuy", "");
						obj.put("wuypk", "");								
						obj.put("fxccy", "");
						obj.put("pank", "");
						obj.put("ftq_102","" );
						obj.put("ftq_102oq","" );
						obj.put("ftq_102hs", "");										
						
						obj.put("ftq_105", "");
						obj.put("ftq_105oq", "");
						obj.put("ftq_105hs", "");
						
						obj.put("ftq_107", "");
						obj.put("ftq_107oq", "");
						obj.put("ftq_107hs", "");											
						obj.put("ftq_c1y", "");								
						obj.put("ftq_c1", "");										
					
						
						obj.put("ftq_103", "");								
						obj.put("ftq_103oq", "");
						obj.put("ftq_103hs", "");
																	
						obj.put("ftq_s01", "");
						obj.put("zjfyl", "");										
						obj.put("ftq_s01oq","");
						obj.put("ftq_s01hs", "");																							
						obj.put("ftq_s1y", "");
						obj.put("ftq_s1s", "");	

						obj.put("zyel", "");
						obj.put("yel", "");
						obj.put("youl", "");
						obj.put("remark", "");	

					   arr.add(obj);					
					}
				   return arr;	
				}

				@Override
				public List<Object[]> searchcyfx1(String rq, String fields) {
					String sql = "";
					if(!rq.equals("")){
						String stratime =DateBean.getBefore6DAYTime(rq);
						String endtime = rq;
						sql = "select " + fields + " from   PC_RPD_BOILER_SUPERHEAT_YC_T   u where u.ACQUISITION_TIME between TO_DATE('"+stratime+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+endtime+"','YYYY-MM-DD HH24:MI:SS') order by u.ACQUISITION_TIME";
					}else{
						sql = fields;
					}					
					List<Object[]> yyList = lineMeasureDao.searchU2OIL(sql);
					return yyList;
				}

				@Override
				public List<PcRpdCy1ScMessureT> Sreachfxcy1(String iD,
						String string) {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public JSONArray searchFxjl2(List<String> date, String txtYear,
						String txtMonth) {
					JSONObject obj = new JSONObject();
					JSONArray arr = new JSONArray();
					Object[] cy2fxjl = null;
					String month;
					int dataflg  =0; //数据存在表示 0：不存在； 1： 存在					
					int tmonth = Integer.parseInt(txtMonth);
					if(tmonth<10){
						month = "0".concat(txtMonth);
					}else{
						month = txtMonth;
					}
					int tyear = Integer.parseInt(txtYear);
					
					
					//System.out.println(tyear+"年"+tmonth+"月");					
					Calendar a = Calendar.getInstance(); 
					a.set(Calendar.YEAR, tyear);
					a.set(Calendar.MONTH, tmonth-1);
					a.set(Calendar.DATE, 1);//把日期设置为当月第一天 
					a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天					 
					int maxDate = a.get(Calendar.DATE); 
					//System.out.println("本月总共有"+maxDate+"天");
					
					
					//月合计
					double   fc2yeMonth = 0.0;
					double	 fc2youlMonth = 0.0;
					double	 fc2hsMonth = 0.0;
					double	 fc3yeMonth = 0.0;
					double	 fc3youMonth = 0.0;
					double	 fc3hsMonth = 0.0;
					double   sagd6000yeMonth =0.0;
					double   sagd6000youMonth =0.0;
					double 	 zyelMonth = 0.0;
					double 	 zyoulMonth = 0.0;	
					double   jhgyeMonth = 0.0;
					double   jhgyouMonth = 0.0;
					double   dsMonth = 0.0;									
					double   wyMonth = 0.0;
					double   ccyMonth = 0.0;
					double   pkylMonth = 0.0;	
					//月平均
					double   fc2yeAVG = 0.0;
					double	 fc2youlAVG = 0.0;
					double	 fc2hsAVG = 0.0;
					double	 fc3yeAVG = 0.0;
					double	 fc3youAVG = 0.0;
					double	 fc3hsAVG = 0.0;
					double   sagd6000yeAVG =0.0;
					double   sagd6000youAVG =0.0;
					double   zyelAVG = 0.0;
					double   zyoulAVG = 0.0;
					double   jhgyeAVG = 0.0;
					double   jhgyouAVG = 0.0;
					double   dsAVG = 0.0;									
					double   wyAVG = 0.0;
					double   ccyAVG = 0.0;
					double   pkylAVG = 0.0;									
					//输差油量 输差液量，保持原始值

					
					//有多少天的数据
					int count = 0;
//					int pkcount= 0 ;
					
					String sql = "select rpd_cy2_line_messure_id,report_date,fc2ye,fc2you,fc2hs,fc3ye,fc3you,"+
								"fc3hs,sagd6000ye,sagd6000you,zyel,zyoul,jhgye,jhgyou,cdsgh,chdyw,ds,wy,ccyl,pkyl," +
								"remark  from  PC_RPD_CY2_LINE_MESSURE_V a where 1=1";
								sql += " and report_date between '"+date.get(0)+"' and '"+date.get(1)+"' order by report_date";				  
					List<Object[]> cy2fx = lineMeasureDao.searchU2OIL(sql);	
					String[][] dates = DateBean.getReportMonths("time17");	
							
					
					
					for(int i=0;i<10;i++){
						dates[0][i]=txtYear+"-"+month+"-"+dates[0][i];
					}
					for(int i=0;i<10;i++){
						dates[1][i]=txtYear+"-"+month+"-"+dates[1][i];
					}
					for(int i=0;i<maxDate-20;i++){
						dates[2][i]=txtYear+"-"+month+"-"+dates[2][i];
					}
					for(int i=maxDate-20;i<11;i++){
						dates[2][i]="";
					}						
					obj = new JSONObject();							
							if(cy2fx !=null && cy2fx.size()>0){	
								for(String[] dateList:dates){	
									
									//用于统计没询都有多少天
									int xcount = 0;		
									
									
									double   fc2yeSUM = 0.0;
									double	 fc2youlSUM = 0.0;
									double	 fc2hsSUM = 0.0;
									double	 fc3yeSUM = 0.0;
									double	 fc3youSUM = 0.0;
									double	 fc3hsSUM = 0.0;
									double   sagd6000yeSUM =0.0;
									double   sagd6000youSUM =0.0;
									double   zyelSUM = 0.0;
									double   zyoulSUM = 0.0;
									double   jhgyeSUM = 0.0;
									double   jhgyouSUM = 0.0;
									double   dsSUM = 0.0;									
									double   wySUM = 0.0;
									double   ccySUM = 0.0;
									double  pkylSUM = 0.0;									


										 for(String data:dateList){
											 obj = new JSONObject();
											 cy2fxjl = null;
												dataflg = 0;
												for(Object[] cy2f:cy2fx){
													if(data.toString().equals(cy2f[1].toString())){
														dataflg = 1;
														cy2fxjl = cy2f;
												}
											}																								
									if(dataflg == 1){		
										obj.put("rpd_cy2_line_messure_id", cy2fxjl[0]);
										obj.put("report_date", cy2fxjl[1].toString());										
										 if(cy2fxjl[2]==null || "".equals(cy2fxjl[2].toString())){
											 obj.put("fc2ye", " ");
										 }else{
											 obj.put("fc2ye", CommonsUtil.getNotTwoDataZero(cy2fxjl[2]));
										 	}
										 if(cy2fxjl[3]==null || "".equals(cy2fxjl[3].toString()) ){
											 obj.put("fc2you", " ");
										 }else{
											 obj.put("fc2you", CommonsUtil.getNotTwoDataZero(cy2fxjl[3]));
										 	}
										 if(cy2fxjl[4]==null || "".equals(cy2fxjl[4].toString()) ){
											 obj.put("fc2hs", " ");
										 }else{
											 obj.put("fc2hs", CommonsUtil.getNotTwoDataZero(cy2fxjl[4]));
										 	}
										 if(cy2fxjl[5]==null || "".equals(cy2fxjl[5].toString())){
											 obj.put("fc3ye", " ");
										 }else{
											 obj.put("fc3ye", CommonsUtil.getNotTwoDataZero(cy2fxjl[5]));
										 	}
										 if(cy2fxjl[6]==null || "".equals(cy2fxjl[6].toString()) ){
											 obj.put("fc3you", " ");
										 }else{
											 obj.put("fc3you", CommonsUtil.getNotTwoDataZero(cy2fxjl[6]));
										 	}
										 if(cy2fxjl[7]==null || "".equals(cy2fxjl[7].toString())){
											 obj.put("fc3hs", " ");
										 }else{
											 obj.put("fc3hs", CommonsUtil.getNotTwoDataZero(cy2fxjl[7]));
										 	}
										 if(cy2fxjl[8]==null || "".equals(cy2fxjl[8].toString())){
											 obj.put("sagd6000ye", " ");
										 }else{
											 obj.put("sagd6000ye", CommonsUtil.getNotTwoDataZero(cy2fxjl[8]));
										 	}
										 if(cy2fxjl[9]==null || "".equals(cy2fxjl[9].toString()) ){
											 obj.put("sagd6000you", " ");
										 }else{
											 obj.put("sagd6000you", CommonsUtil.getNotTwoDataZero(cy2fxjl[9]));
										 	}
										 if(cy2fxjl[10]==null || "".equals(cy2fxjl[10].toString()) ){
											 obj.put("zyel", " ");
										 }else{
											 obj.put("zyel", CommonsUtil.getNotTwoDataZero(cy2fxjl[10]));
										 	}
										 
										 if(cy2fxjl[11]==null || "".equals(cy2fxjl[11].toString()) ){
											 obj.put("zyoul", " ");
										 }else{
											 obj.put("zyoul", CommonsUtil.getNotTwoDataZero(cy2fxjl[11]));
										 	}
										
										 
										 if(cy2fxjl[12]==null || "".equals(cy2fxjl[12].toString())){
											 obj.put("jhgye", " ");
										 }else{
											 obj.put("jhgye", CommonsUtil.getNotTwoDataZero(cy2fxjl[12]));
										 	}
										 
										 if(cy2fxjl[13]==null || "".equals(cy2fxjl[13].toString()) ){
											 obj.put("jhgyou", " ");
										 }else{
											 obj.put("jhgyou", CommonsUtil.getNotTwoDataZero(cy2fxjl[13]));
										 	}
										 if(cy2fxjl[14]==null || "".equals(cy2fxjl[14].toString()) ){
											 obj.put("cdsgh", " ");
										 }else{
											 obj.put("cdsgh", cy2fxjl[14].toString());
										 	}
										 
										 if(cy2fxjl[15]==null || "".equals(cy2fxjl[15].toString()) ){
											 obj.put("chdyw", " ");
										 }else{
											 obj.put("chdyw", CommonsUtil.getNotTwoDataZero(cy2fxjl[15]));
										 	}
										 
										 if(cy2fxjl[16]==null || "".equals(cy2fxjl[16].toString()) ){
											 obj.put("ds", " ");
										 }else{
											 obj.put("ds", CommonsUtil.getNotTwoDataZero(cy2fxjl[16]));
										 	}
										 if(cy2fxjl[17]==null || "".equals(cy2fxjl[17].toString()) ){
											 obj.put("wy", " ");
										 }else{
											 obj.put("wy", CommonsUtil.getNotTwoDataZero(cy2fxjl[17]));
										 	}
										 
										 if(cy2fxjl[18]==null || "".equals(cy2fxjl[18].toString())){
											 obj.put("ccyl", " ");
										 }else{
											 obj.put("ccyl", CommonsUtil.getNotTwoDataZero(cy2fxjl[18]));
										 	}
										 if(cy2fxjl[19]==null || "".equals(cy2fxjl[19].toString()) ){
											 obj.put("pkyl", " ");
										 }else{											 
											 obj.put("pkyl", CommonsUtil.getNotTwoDataZero(cy2fxjl[19]));									 
											 
										 	}
										 
										 obj.put("scyel","");
										 obj.put("scyoul","");
										 
										 if(cy2fxjl[20]==null || "".equals(cy2fxjl[20].toString())){
											 obj.put("remark", " ");
										 }else{
											 obj.put("remark", cy2fxjl[20].toString());
										 } 
										 //统计天数
										 xcount++;
										 count++;
										 	//计算一旬的和
										 fc2yeSUM = CommonsUtil.getDoubleData(cy2fxjl[2])+fc2yeSUM;											     
										 fc2youlSUM = CommonsUtil.getDoubleData(cy2fxjl[3])+fc2youlSUM;	
										 fc2hsSUM = CommonsUtil.getDoubleData(cy2fxjl[4])+fc2hsSUM;	
										 fc3yeSUM = CommonsUtil.getDoubleData(cy2fxjl[5])+fc3yeSUM;	
										 fc3youSUM = CommonsUtil.getDoubleData(cy2fxjl[6])+fc3youSUM;	
										 fc3hsSUM = CommonsUtil.getDoubleData(cy2fxjl[7])+fc3hsSUM;	
										 sagd6000yeSUM = CommonsUtil.getDoubleData(cy2fxjl[8])+sagd6000yeSUM;	
										 sagd6000youSUM = CommonsUtil.getDoubleData(cy2fxjl[9])+sagd6000youSUM;	
										 zyelSUM = CommonsUtil.getDoubleData(cy2fxjl[10])+zyelSUM;
										 zyoulSUM = CommonsUtil.getDoubleData(cy2fxjl[11])+zyoulSUM;
										 jhgyeSUM = CommonsUtil.getDoubleData(cy2fxjl[12])+jhgyeSUM;
										 jhgyouSUM = CommonsUtil.getDoubleData(cy2fxjl[13])+jhgyouSUM;
										 dsSUM = CommonsUtil.getDoubleData(cy2fxjl[16])+dsSUM;
										 wySUM = CommonsUtil.getDoubleData(cy2fxjl[17])+wySUM;
										 ccySUM = CommonsUtil.getDoubleData(cy2fxjl[18])+ccySUM;
										 pkylSUM = CommonsUtil.getDoubleData(cy2fxjl[19])+pkylSUM;
										 

										 //计算月平均
										 fc2yeAVG  = CommonsUtil.getDoubleData(cy2fxjl[2])+fc2yeAVG;											     
										 fc2youlAVG  = CommonsUtil.getDoubleData(cy2fxjl[3])+fc2youlAVG;	
										 fc2hsAVG  = CommonsUtil.getDoubleData(cy2fxjl[4])+fc2hsAVG;	
										 fc3yeAVG  = CommonsUtil.getDoubleData(cy2fxjl[5])+fc3yeAVG;	
										 fc3youAVG  = CommonsUtil.getDoubleData(cy2fxjl[6])+fc3youAVG;	
										 fc3hsAVG  = CommonsUtil.getDoubleData(cy2fxjl[7])+fc3hsAVG;	
										 sagd6000yeAVG  = CommonsUtil.getDoubleData(cy2fxjl[8])+sagd6000yeAVG;	
										 sagd6000youAVG  = CommonsUtil.getDoubleData(cy2fxjl[9])+sagd6000youAVG;
										 zyelAVG = CommonsUtil.getDoubleData(cy2fxjl[10])+zyelAVG;
										 zyoulAVG = CommonsUtil.getDoubleData(cy2fxjl[11])+zyoulAVG;
										 jhgyeAVG  = CommonsUtil.getDoubleData(cy2fxjl[12])+jhgyeAVG;
										 jhgyouAVG  = CommonsUtil.getDoubleData(cy2fxjl[13])+jhgyouAVG;
										 dsAVG  = CommonsUtil.getDoubleData(cy2fxjl[16])+dsAVG;
										 wyAVG  = CommonsUtil.getDoubleData(cy2fxjl[17])+wyAVG;
										 ccyAVG  = CommonsUtil.getDoubleData(cy2fxjl[18])+ccyAVG;
										 pkylAVG  = CommonsUtil.getDoubleData(cy2fxjl[19])+pkylAVG;
	
										 
										 //计算月总和
										 fc2yeMonth = CommonsUtil.getDoubleData(cy2fxjl[2])+fc2yeMonth;											     
										 fc2youlMonth  = CommonsUtil.getDoubleData(cy2fxjl[3])+fc2youlMonth;	
										 fc2hsMonth  = CommonsUtil.getDoubleData(cy2fxjl[4])+fc2hsMonth;	
										 fc3yeMonth  = CommonsUtil.getDoubleData(cy2fxjl[5])+fc3yeMonth;	
										 fc3youMonth  = CommonsUtil.getDoubleData(cy2fxjl[6])+fc3youMonth;	
										 fc3hsMonth  = CommonsUtil.getDoubleData(cy2fxjl[7])+fc3hsMonth;	
										 sagd6000yeMonth  = CommonsUtil.getDoubleData(cy2fxjl[8])+sagd6000yeMonth;	
										 sagd6000youMonth  = CommonsUtil.getDoubleData(cy2fxjl[9])+sagd6000youMonth;	
										 zyelMonth = CommonsUtil.getDoubleData(cy2fxjl[10])+zyelMonth;
										 zyoulMonth = CommonsUtil.getDoubleData(cy2fxjl[11])+zyoulMonth;
										 jhgyeMonth  = CommonsUtil.getDoubleData(cy2fxjl[12])+jhgyeMonth;
										 jhgyouMonth  = CommonsUtil.getDoubleData(cy2fxjl[13])+jhgyouMonth;
										 dsMonth  = CommonsUtil.getDoubleData(cy2fxjl[16])+dsMonth;
										 wyMonth  = CommonsUtil.getDoubleData(cy2fxjl[17])+wyMonth;
										 ccyMonth  = CommonsUtil.getDoubleData(cy2fxjl[18])+ccyMonth;
										 pkylMonth  = CommonsUtil.getDoubleData(cy2fxjl[19])+pkylMonth;
	
										
									}																	
									if(dataflg==0){
										obj.put("rpd_cy2_line_messure_id", "/");
										obj.put("report_date",  data.toString());
										obj.put("fc2ye", "");
										obj.put("fc2you", "");
										obj.put("fc2hs", "");										
										obj.put("fc3ye", "");										
										obj.put("fc3you", "");
										obj.put("fc3hs", "");								
										obj.put("sagd6000ye", "");
										obj.put("sagd6000you", "");
										obj.put("zyel", "");
										obj.put("zyoul", "");										
										obj.put("jhgye", "");										
										obj.put("jhgyou", "");
										obj.put("cdsgh", "");								
										obj.put("chdyw", "");
										obj.put("ds", "");
										obj.put("wy", "");
										obj.put("ccyl", "");										
										obj.put("pkyl", "");										
										obj.put("scyel", "");
										obj.put("scyoul", "");								
										obj.put("remark", "");
									}
									arr.add(obj);
								 }
										 obj.put("rpd_cy2_line_messure_id", "/");
											obj.put("report_date",  "旬合计");
											obj.put("fc2ye", String.format("%.2f",fc2yeSUM));
											obj.put("fc2you", String.format("%.2f",fc2youlSUM));
											obj.put("fc2hs", String.format("%.2f",fc2hsSUM/xcount));										
											obj.put("fc3ye", String.format("%.2f",fc3yeSUM));										
											obj.put("fc3you", String.format("%.2f",fc3youSUM));
											obj.put("fc3hs", String.format("%.2f",fc3hsSUM/xcount));								
											obj.put("sagd6000ye", String.format("%.2f",sagd6000yeSUM));
											obj.put("sagd6000you", String.format("%.2f",sagd6000youSUM));
											obj.put("zyel",String.format("%.2f",zyelSUM) );
											obj.put("zyoul",String.format("%.2f",zyoulSUM) );			
											obj.put("jhgye", String.format("%.2f",jhgyeSUM));
											obj.put("jhgyou", String.format("%.2f",jhgyouSUM));							
											obj.put("cdsgh", "");
											obj.put("chdyw", "");																													
											obj.put("ds", String.format("%.2f",dsSUM));										
											obj.put("wy", String.format("%.2f",wySUM));
											obj.put("ccyl", String.format("%.2f",ccySUM));								
											obj.put("pkyl", String.format("%.2f",pkylSUM));																				
											if(jhgyeSUM >0){
												obj.put("scyel", String.format("%.2f",(jhgyeSUM-zyelSUM)/jhgyeSUM*100));
											}else{
												obj.put("scyel","");
											}
											if(pkylSUM>0){
												obj.put("scyoul", String.format("%.2f",(pkylSUM-zyoulSUM)/pkylSUM*100));	
											}else{
												obj.put("scyoul","");
											}																
											obj.put("remark", "");
													
										arr.add(obj);					
							}
						}else {
							for(String[] dateList:dates){
								obj = new JSONObject();
								for(String data:dateList){
									obj = new JSONObject();
									obj.put("rpd_cy2_line_messure_id", "/");
									obj.put("report_date",  data.toString());
									obj.put("fc2ye", "");
									obj.put("fc2you", "");
									obj.put("fc2hs", "");										
									obj.put("fc3ye", "");										
									obj.put("fc3you", "");
									obj.put("fc3hs", "");								
									obj.put("sagd6000ye", "");
									obj.put("sagd6000you", "");
									obj.put("zyel", "");
									obj.put("zyoul", "");										
									obj.put("jhgye", "");										
									obj.put("jhgyou", "");
									obj.put("cdsgh", "");								
									obj.put("chdyw", "");
									obj.put("ds", "");
									obj.put("wy", "");
									obj.put("ccyl", "");										
									obj.put("pkyl", "");										
									obj.put("scyel", "");
									obj.put("scyoul", "");								
									obj.put("remark", "");
									arr.add(obj);	
								}
								obj.put("rpd_cy2_line_messure_id", "/");
								obj.put("report_date",  "旬合计");
								obj.put("fc2ye", "");
								obj.put("fc2you", "");
								obj.put("fc2hs", "");										
								obj.put("fc3ye", "");										
								obj.put("fc3you", "");
								obj.put("fc3hs", "");								
								obj.put("sagd6000ye", "");
								obj.put("sagd6000you", "");
								obj.put("zyel", "");
								obj.put("zyoul", "");										
								obj.put("jhgye", "");										
								obj.put("jhgyou", "");
								obj.put("cdsgh", "");								
								obj.put("chdyw", "");
								obj.put("ds", "");
								obj.put("wy", "");
								obj.put("ccyl", "");										
								obj.put("pkyl", "");										
								obj.put("scyel", "");
								obj.put("scyoul", "");								
								obj.put("remark", "");		
								arr.add(obj);					
							}	
						}	
													
					if(cy2fx != null && cy2fx.size()>0){
						obj = new JSONObject();
						obj.put("rpd_cy2_line_messure_id", "/");
						obj.put("report_date",  "日平均产量");
						obj.put("fc2ye", String.format("%.2f",fc2yeAVG/count));
						obj.put("fc2you", String.format("%.2f",fc2youlAVG/count));
						obj.put("fc2hs", String.format("%.2f",fc2hsAVG/count));										
						obj.put("fc3ye", String.format("%.2f",fc3yeAVG/count));										
						obj.put("fc3you", String.format("%.2f",fc3youAVG/count));
						obj.put("fc3hs", String.format("%.2f",fc3hsAVG/count));								
						obj.put("sagd6000ye", String.format("%.2f",sagd6000yeAVG/count));
						obj.put("sagd6000you", String.format("%.2f",sagd6000youAVG/count));
						obj.put("zyel",String.format("%.2f",zyelAVG/count) );
						obj.put("zyoul",String.format("%.2f",zyoulAVG/count) );			
						obj.put("jhgye", String.format("%.2f",jhgyeAVG/count));
						obj.put("jhgyou", String.format("%.2f",jhgyouAVG/count));							
						obj.put("cdsgh", "");
						obj.put("chdyw", "");																													
						obj.put("ds", String.format("%.2f",dsAVG/count));										
						obj.put("wy", String.format("%.2f",wyAVG/count));
						obj.put("ccyl", String.format("%.2f",ccyAVG/count));								
						obj.put("pkyl", String.format("%.2f", pkylAVG/count));																				
						if(jhgyeAVG/count >0){
							obj.put("scyel", String.format("%.2f",(jhgyeAVG-zyelAVG)/jhgyeAVG*100));
						}else{
							obj.put("scyel","");
						}
						if(pkylAVG/count >0){
							obj.put("scyoul", String.format("%.2f",(pkylAVG-zyoulAVG)/pkylAVG*100));	
						}else{
							obj.put("scyoul","");
						}	
						obj.put("remark", "");	 
						arr.add(obj);
						
						
						obj.put("rpd_cy2_line_messure_id", "/");
						obj.put("report_date",  "月总产量");
						obj.put("fc2ye", String.format("%.2f",fc2yeMonth));
						obj.put("fc2you", String.format("%.2f",fc2youlMonth));
						obj.put("fc2hs", String.format("%.2f",fc2hsMonth/count));										
						obj.put("fc3ye", String.format("%.2f",fc3yeMonth));										
						obj.put("fc3you", String.format("%.2f",fc3youMonth));
						obj.put("fc3hs", String.format("%.2f",fc3hsMonth/count));								
						obj.put("sagd6000ye", String.format("%.2f",sagd6000yeMonth));
						obj.put("sagd6000you", String.format("%.2f",sagd6000youMonth));
						obj.put("zyel",String.format("%.2f",zyelMonth) );
						obj.put("zyoul",String.format("%.2f",zyoulMonth) );			
						obj.put("jhgye", String.format("%.2f",jhgyeMonth));
						obj.put("jhgyou", String.format("%.2f",jhgyouMonth));							
						obj.put("cdsgh", "");
						obj.put("chdyw", "");																													
						obj.put("ds", String.format("%.2f",dsMonth));										
						obj.put("wy", String.format("%.2f",wyMonth));
						obj.put("ccyl", String.format("%.2f",ccyMonth));								
						obj.put("pkyl", String.format("%.2f",pkylMonth));																				
						if(jhgyeMonth >0){
							obj.put("scyel", String.format("%.2f",(jhgyeMonth-zyelMonth)/jhgyeMonth*100));
						}else{
							obj.put("scyel","");
						}
						if(pkylMonth>0){
							obj.put("scyoul", String.format("%.2f",(pkylMonth-zyoulMonth)/pkylMonth*100));	
						}else{
							obj.put("scyoul","");
						}										
						obj.put("remark", "");	 
						 arr.add(obj);
					}else{
						obj = new JSONObject();
						
						obj.put("rpd_cy2_line_messure_id", "/");
						obj.put("report_date",  "日平均产量");
						obj.put("fc2ye", "");
						obj.put("fc2you", "");
						obj.put("fc2hs", "");										
						obj.put("fc3ye", "");										
						obj.put("fc3you", "");
						obj.put("fc3hs", "");								
						obj.put("sagd6000ye", "");
						obj.put("sagd6000you", "");
						obj.put("zyel", "");
						obj.put("zyoul", "");										
						obj.put("jhgye", "");										
						obj.put("jhgyou", "");
						obj.put("cdsgh", "");								
						obj.put("chdyw", "");
						obj.put("ds", "");
						obj.put("wy", "");
						obj.put("ccyl", "");										
						obj.put("pkyl", "");										
						obj.put("scyel", "");
						obj.put("scyoul", "");								
						obj.put("remark", "");
						arr.add(obj);
						
						obj.put("rpd_cy2_line_messure_id", "/");
						obj.put("report_date",  "月总产量");
						obj.put("fc2ye", "");
						obj.put("fc2you", "");
						obj.put("fc2hs", "");										
						obj.put("fc3ye", "");										
						obj.put("fc3you", "");
						obj.put("fc3hs", "");								
						obj.put("sagd6000ye", "");
						obj.put("sagd6000you", "");
						obj.put("zyel", "");
						obj.put("zyoul", "");										
						obj.put("jhgye", "");										
						obj.put("jhgyou", "");
						obj.put("cdsgh", "");								
						obj.put("chdyw", "");
						obj.put("ds", "");
						obj.put("wy", "");
						obj.put("ccyl", "");										
						obj.put("pkyl", "");										
						obj.put("scyel", "");
						obj.put("scyoul", "");								
						obj.put("remark", "");
					   arr.add(obj);					
					}
				   return arr;	
				}

				@Override
				public List<Object[]> searchcyfx2(String string, String fields) {
					String sql = fields;
//					System.out.println("sql二号稠油导出语句:  "+fields);					
					List<Object[]> yyList = lineMeasureDao.searchU2OIL(sql);
					return yyList;
				}

				@SuppressWarnings("unused")
				@Override
				public JSONArray searchFxjl3(List<String> date, String txtYear,
						String txtMonth) {
					JSONObject obj = new JSONObject();
					JSONArray arr = new JSONArray();
					Object[] cy1fxjl = null;
					String month;
					int dataflg  =0; //数据存在表示 0：不存在； 1： 存在					
					int tmonth = Integer.parseInt(txtMonth);
					if(tmonth<10){
						month = "0".concat(txtMonth);
					}else{
						month = txtMonth;
					}
					
					int tyear = Integer.parseInt(txtYear);
					//System.out.println(tyear+"年"+tmonth+"月");					
					Calendar a = Calendar.getInstance(); 
					a.set(Calendar.YEAR, tyear);
					a.set(Calendar.MONTH, tmonth-1);
					a.set(Calendar.DATE, 1);//把日期设置为当月第一天 
					a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天					 
					int maxDate = a.get(Calendar.DATE); 
					int endday = a.getActualMaximum(Calendar.DAY_OF_MONTH);
					//System.out.println("本月总共有"+maxDate+"天");
					//System.out.println("本月最后一天是"+endday+"号");
					
					//月合计
					double   xzjllSUMMonth = 0.0;
					double	 xzjylSUMMonth = 0.0;
					double	 xzjhsSUMMonth = 0.0;
					double	 wehllSUMMonth = 0.0;
					double	 wehylSUMMonth = 0.0;
					double	 wehhsSUMMonth = 0.0;
					double   zyelSUMMonth =0.0;
					double   zyoulSUMMonth =0.0;
					double   jhgyeSUMMonth = 0.0;
					double   jhgyouSUMMonth = 0.0;
					double   pkSUMMonth = 0.0;									

					
					//月平均
					double   xzjllAVG = 0.0;
					double	 xzjylAVG = 0.0;
					double	 xzjhsAVG = 0.0;
					double	 wehllAVG = 0.0;
					double	 wehylAVG = 0.0;
					double   wehhsAVG =0.0;
					double   zyelAVG =0.0;
					double   zyoulAVG = 0.0;
					double   jhgyeAVG = 0.0;
					double   jhgyouAVG = 0.0;									
					double   pkAVG = 0.0;
								
					
					int count = 0;
					
					String sql = "select rpd_xyl_line_messure_id,report_date,xzjll,xzjyl,xzjhs,wehll,wehyl,"+
								"wehhs,zyel,zyoul,jhgye,jhgyou,pk,remark  from  PC_RPD_XYL_LINE_MESSURE_V a where 1=1";
					sql += " and report_date between '"+date.get(0)+"' and '"+date.get(1)+"' order by report_date";	
					List<Object[]> cy1fx = lineMeasureDao.searchU2OIL(sql);	
					String[][] dates = DateBean.getReportMonths("time17");	
							
					
					for(int i=0;i<10;i++){
						dates[0][i]=txtYear+"-"+month+"-"+dates[0][i];
					}
					for(int i=0;i<10;i++){
						dates[1][i]=txtYear+"-"+month+"-"+dates[1][i];
					}
					for(int i=0;i<maxDate-20;i++){
						dates[2][i]=txtYear+"-"+month+"-"+dates[2][i];
					}
					for(int i=maxDate-20;i<11;i++){
						dates[2][i]="";
					}						
					
					
					
					
					obj = new JSONObject();							
							if(cy1fx !=null && cy1fx.size()>0){	
								for(String[] dateList:dates){	
									
									//用于统计每旬都有多少天
									
									int xcount = 0;
									double   xzjllSUM = 0.0;
									double	 xzjylSUM = 0.0;
									double	 xzjhsSUM = 0.0;
									double	 wehllSUM = 0.0;
									double	 wehylSUM = 0.0;
									double	 wehhsSUM = 0.0;
									double   zyelSUM =0.0;
									double   zyoulSUM =0.0;
									double   jhgyeSUM = 0.0;
									double   jhgyouSUM = 0.0;
									double   pkSUM = 0.0;	
									
									
										 for(String data:dateList){
											 obj = new JSONObject();
												cy1fxjl = null;
												dataflg = 0;
												for(Object[] cy1f:cy1fx){
													if(data.toString().equals(cy1f[1].toString())){
														dataflg = 1;
														cy1fxjl = cy1f;
														//System.out.println(cy1f[1].toString());
												}
											}																								
									if(dataflg == 1){	
									
										//obj.put("rownum", cy1fxjl[0]);
										obj.put("rpd_xyl_line_messure_id", cy1fxjl[0]);
										obj.put("report_date", cy1fxjl[1].toString());										
										 if(cy1fxjl[2]==null || "".equals(cy1fxjl[2].toString())){
											 obj.put("xzjll", " ");
										 }else{
											 obj.put("xzjll", CommonsUtil.getNotTwoDataZero(cy1fxjl[2]));
										 	}
										 if(cy1fxjl[3]==null || "".equals(cy1fxjl[3].toString()) ){
											 obj.put("xzjyl", " ");
										 }else{
											 obj.put("xzjyl", CommonsUtil.getNotTwoDataZero(cy1fxjl[3]));
										 	}
										 if(cy1fxjl[4]==null || "".equals(cy1fxjl[4].toString()) ){
											 obj.put("xzjhs", " ");
										 }else{
											 obj.put("xzjhs", CommonsUtil.getNotTwoDataZero(cy1fxjl[4]));
										 	}
										 if(cy1fxjl[5]==null || "".equals(cy1fxjl[5].toString()) ){
											 obj.put("wehll", " ");
										 }else{
											 obj.put("wehll", CommonsUtil.getNotTwoDataZero(cy1fxjl[5]));
										 	}
										 if(cy1fxjl[6]==null || "".equals(cy1fxjl[6].toString())){
											 obj.put("wehyl", " ");
										 }else{
											 obj.put("wehyl", CommonsUtil.getNotTwoDataZero(cy1fxjl[6]));
										 	}
										 if(cy1fxjl[7]==null || "".equals(cy1fxjl[7].toString())){
											 obj.put("wehhs", " ");
										 }else{
											 obj.put("wehhs", CommonsUtil.getNotTwoDataZero(cy1fxjl[7]));
										 	}
										 if(cy1fxjl[8]==null || "".equals(cy1fxjl[8].toString())){
											 obj.put("zyel", " ");
										 }else{
											 obj.put("zyel", CommonsUtil.getNotTwoDataZero(cy1fxjl[8]));
										 	}
										 if(cy1fxjl[9]==null || "".equals(cy1fxjl[9].toString())){
											 obj.put("zyoul", " ");
										 }else{
											 obj.put("zyoul", CommonsUtil.getNotTwoDataZero(cy1fxjl[9]));
										 	}
										
										 	
										 if(cy1fxjl[10]==null || "".equals(cy1fxjl[10].toString()) ){
											 obj.put("jhgye", " ");
										 }else{
											 obj.put("jhgye", CommonsUtil.getNotTwoDataZero(cy1fxjl[10]));
										 	}
										 if(cy1fxjl[11]==null || "".equals(cy1fxjl[11].toString()) ){
											 obj.put("jhgyou", " ");
										 }else{
											 obj.put("jhgyou", CommonsUtil.getNotTwoDataZero(cy1fxjl[11]));
										 	}
										 
										 if(cy1fxjl[12]==null || "".equals(cy1fxjl[12].toString()) ){
											 obj.put("pk", " ");
										 }else{
											 obj.put("pk", CommonsUtil.getNotTwoDataZero(cy1fxjl[12]));
										 	}
										 obj.put("scyel","");
										 obj.put("scyoul","");
										 if(cy1fxjl[13]==null || "".equals(cy1fxjl[13].toString())){
											 obj.put("remark", " ");
										 }else{
											 obj.put("remark", cy1fxjl[13].toString());
										 }												 
										 //统计天数
										
										 xcount = xcount+1;
										 count = count+1;
										 	//计算一旬的和
										 xzjllSUM = CommonsUtil.getDoubleData(cy1fxjl[2])+xzjllSUM;											     
										 xzjylSUM = CommonsUtil.getDoubleData(cy1fxjl[3])+xzjylSUM;	
										 xzjhsSUM = CommonsUtil.getDoubleData(cy1fxjl[4])+xzjhsSUM;	
										 wehllSUM = CommonsUtil.getDoubleData(cy1fxjl[5])+wehllSUM;	
										 wehylSUM = CommonsUtil.getDoubleData(cy1fxjl[6])+wehylSUM;	
										 wehhsSUM = CommonsUtil.getDoubleData(cy1fxjl[7])+wehhsSUM;	
										 zyelSUM = CommonsUtil.getDoubleData(cy1fxjl[8])+zyelSUM;	
										 zyoulSUM = CommonsUtil.getDoubleData(cy1fxjl[9])+zyoulSUM;											     
										 jhgyeSUM = CommonsUtil.getDoubleData(cy1fxjl[10])+jhgyeSUM;
										 jhgyouSUM = CommonsUtil.getDoubleData(cy1fxjl[11])+jhgyouSUM;
										 pkSUM = CommonsUtil.getDoubleData(cy1fxjl[12])+pkSUM;
										 
						
										 

										 //计算日平均产量
										 xzjllAVG  = CommonsUtil.getDoubleData(cy1fxjl[2])+xzjllAVG;											     
										 xzjylAVG  = CommonsUtil.getDoubleData(cy1fxjl[3])+xzjylAVG;	
										 xzjhsAVG  = CommonsUtil.getDoubleData(cy1fxjl[4])+xzjhsAVG;	
										 wehllAVG  = CommonsUtil.getDoubleData(cy1fxjl[5])+wehllAVG;	
										 wehylAVG  = CommonsUtil.getDoubleData(cy1fxjl[6])+wehylAVG;	
										 wehhsAVG  =  CommonsUtil.getDoubleData(cy1fxjl[7])+wehhsAVG;	
										 zyelAVG  = CommonsUtil.getDoubleData(cy1fxjl[8])+zyelAVG;	
										 zyoulAVG  = CommonsUtil.getDoubleData(cy1fxjl[9])+zyoulAVG;											     
										 jhgyeAVG  = CommonsUtil.getDoubleData(cy1fxjl[10])+jhgyeAVG;
										 jhgyouAVG  = CommonsUtil.getDoubleData(cy1fxjl[11])+jhgyouAVG;
										 pkAVG  =  CommonsUtil.getDoubleData(cy1fxjl[12])+pkAVG;

					
										 
										 //计算月总和
										 xzjllSUMMonth = CommonsUtil.getDoubleData(cy1fxjl[2])+xzjllSUMMonth;											     
										 xzjylSUMMonth  = CommonsUtil.getDoubleData(cy1fxjl[3])+xzjylSUMMonth;	
										 xzjhsSUMMonth  = CommonsUtil.getDoubleData(cy1fxjl[4])+xzjhsSUMMonth;	
										 wehllSUMMonth  = CommonsUtil.getDoubleData(cy1fxjl[5])+wehllSUMMonth;	
										 wehylSUMMonth  = CommonsUtil.getDoubleData(cy1fxjl[6])+wehylSUMMonth;	
										 wehhsSUMMonth  = CommonsUtil.getDoubleData(cy1fxjl[7])+wehhsSUMMonth;	
										 zyelSUMMonth  = CommonsUtil.getDoubleData(cy1fxjl[8])+zyelSUMMonth;	
										 zyoulSUMMonth  = CommonsUtil.getDoubleData(cy1fxjl[9])+zyoulSUMMonth;											     
										 jhgyeSUMMonth  = CommonsUtil.getDoubleData(cy1fxjl[10])+jhgyeSUMMonth;
										 jhgyouSUMMonth  = CommonsUtil.getDoubleData(cy1fxjl[11])+jhgyouSUMMonth;
										 pkSUMMonth  = CommonsUtil.getDoubleData(cy1fxjl[12])+pkSUMMonth;
										
									}
									// System.out.println("每旬有"+xcount +"天");

									if(dataflg==0){
										obj.put("rpd_xyl_line_messure_id", "/");
										obj.put("report_date",  data.toString());
										obj.put("xzjll", "");
										obj.put("xzjyl", "");
										obj.put("xzjhs", "");										
										obj.put("wehll", "");										
										obj.put("wehyl", "");
										obj.put("wehhs", "");								
										obj.put("zyel", "");
										obj.put("zyoul", "");
										obj.put("jhgye", "");
										obj.put("jhgyou", "");										
										obj.put("pk", "");										
										obj.put("scyel", "");
										obj.put("scyoul", "");
										obj.put("remark", "");
									}
									arr.add(obj);
								 }
									//	 System.out.println("每月有"+count +"天");
										 
										 obj.put("rpd_xyl_line_messure_id", "/");
											obj.put("report_date",  "旬合计");
											obj.put("xzjll", String.format("%.2f",xzjllSUM));
											obj.put("xzjyl", String.format("%.2f",xzjylSUM));											
											if(xzjhsSUM<=0){
												obj.put("xzjhs", String.format("%.2f",xzjhsSUM));
											}else{
												obj.put("xzjhs", String.format("%.2f",xzjhsSUM/xcount));										
											}										
											obj.put("wehll", String.format("%.2f",wehllSUM));										
											obj.put("wehyl", String.format("%.2f",wehylSUM));
											if(wehhsSUM<=0){
												obj.put("wehhs", String.format("%.2f",wehhsSUM));
											}else{
												obj.put("wehhs", String.format("%.2f",wehhsSUM/xcount));										
											}							
											obj.put("zyel", String.format("%.2f",zyelSUM));
											obj.put("zyoul", String.format("%.2f",zyoulSUM));
											obj.put("jhgye",String.format("%.2f",jhgyeSUM) );
											obj.put("jhgyou",String.format("%.2f",jhgyouSUM) );											
											obj.put("pk", String.format("%.2f",pkSUM));
											if(jhgyeSUM >0){
												obj.put("scyel", String.format("%.2f",(jhgyeSUM-zyelSUM)/jhgyeSUM*100));
											}else{
												obj.put("scyel","");
											}
											if(jhgyouSUM>0){
												obj.put("scyoul", String.format("%.2f",(pkSUM-zyoulSUM)/pkSUM*100));	
											}else{
												obj.put("scyoul","");
											}											
																					
											obj.put("remark", "");			
										arr.add(obj);					
							}
						}else {
							for(String[] dateList:dates){
								obj = new JSONObject();
								for(String data:dateList){
									obj = new JSONObject();
									obj.put("rpd_xyl_line_messure_id", "/");
									obj.put("report_date",  data.toString());
									obj.put("xzjll", "");
									obj.put("xzjyl", "");
									obj.put("xzjhs", "");										
									obj.put("wehll", "");										
									obj.put("wehyl", "");
									obj.put("wehhs", "");								
									obj.put("zyel", "");
									obj.put("zyoul", "");
									obj.put("jhgye", "");
									obj.put("jhgyou", "");										
									obj.put("pk", "");										
									obj.put("scyel", "");
									obj.put("scyoul", "");
									obj.put("remark", "");
									arr.add(obj);	
								}
								obj.put("rpd_xyl_line_messure_id", "/");
								obj.put("report_date",  "旬合计");								
								obj.put("xzjll", "");
								obj.put("xzjyl", "");
								obj.put("xzjhs", "");										
								obj.put("wehll", "");										
								obj.put("wehyl", "");
								obj.put("wehhs", "");								
								obj.put("zyel", "");
								obj.put("zyoul", "");
								obj.put("jhgye", "");
								obj.put("jhgyou", "");										
								obj.put("pk", "");										
								obj.put("scyel", "");
								obj.put("scyoul", "");
								obj.put("remark", "");
								arr.add(obj);					
							}	
						}	
													
					if(cy1fx != null && cy1fx.size()>0){
						obj = new JSONObject();
						obj.put("rpd_xyl_line_messure_id", "/");
						obj.put("report_date",  "日平均产量");
						obj.put("xzjll", String.format("%.2f",xzjllAVG/count));
						obj.put("xzjyl", String.format("%.2f",xzjylAVG/count));
						obj.put("xzjhs", String.format("%.2f",xzjhsAVG/count));										
						obj.put("wehll", String.format("%.2f",wehllAVG/count));										
						obj.put("wehyl", String.format("%.2f",wehylAVG/count));
						obj.put("wehhs", String.format("%.2f",wehhsAVG/count));								
						obj.put("zyel", String.format("%.2f",zyelAVG/count));
						obj.put("zyoul",String.format("%.2f",zyoulAVG/count));
						obj.put("jhgye", String.format("%.2f",jhgyeAVG/count));
						obj.put("jhgyou", String.format("%.2f",jhgyouAVG/count));										
						obj.put("pk", String.format("%.2f",pkAVG/count));										
						
						if(jhgyeAVG/count >0){
							obj.put("scyel", String.format("%.2f",(jhgyeAVG-zyelAVG)/jhgyeAVG*100));
						}else{
							obj.put("scyel","");
						}
						if(pkAVG/count >0){
							obj.put("scyoul", String.format("%.2f",(pkAVG-zyoulAVG)/pkAVG*100));	
						}else{
							obj.put("scyoul","");
						}											
							
						
						
						obj.put("remark", "");
						arr.add(obj);
						
						obj.put("rpd_xyl_line_messure_id", "/");
						obj.put("report_date",  "月总产量");
						obj.put("xzjll", String.format("%.2f",xzjllSUMMonth));
						obj.put("xzjyl", String.format("%.2f",xzjylSUMMonth));
						if(xzjhsSUMMonth<=0){
							obj.put("xzjhs", String.format("%.2f",xzjhsSUMMonth));
						}else{
							obj.put("xzjhs", String.format("%.2f",xzjhsSUMMonth/count));
						};										
						obj.put("wehll", String.format("%.2f",wehllSUMMonth));										
						obj.put("wehyl", String.format("%.2f",wehylSUMMonth));
						if(wehhsSUMMonth<=0){
							obj.put("wehhs", String.format("%.2f",wehhsSUMMonth));
						}else{
							obj.put("wehhs", String.format("%.2f",wehhsSUMMonth/count));
						};							
						obj.put("zyel", String.format("%.2f",zyelSUMMonth));
						obj.put("zyoul", String.format("%.2f",zyoulSUMMonth));
						obj.put("jhgye", String.format("%.2f",jhgyeSUMMonth));
						obj.put("jhgyou", String.format("%.2f",jhgyouSUMMonth));																					
						obj.put("pk", String.format("%.2f",pkSUMMonth));
						if(jhgyeSUMMonth >0){
							obj.put("scyel", String.format("%.2f",(jhgyeSUMMonth-zyelSUMMonth)/jhgyeSUMMonth*100));
						}else{
							obj.put("scyel","");
						}
						if(pkSUMMonth>0){
							obj.put("scyoul", String.format("%.2f",(pkSUMMonth-zyoulSUMMonth)/pkSUMMonth*100));	
						}else{
							obj.put("scyoul","");
						}						
						obj.put("remark", "");		 
						 arr.add(obj);
					}else{
						obj = new JSONObject();						
						obj.put("rpd_xyl_line_messure_id", "/");
						obj.put("report_date",  "日平均产量");
						obj.put("xzjll", "");
						obj.put("xzjyl", "");
						obj.put("xzjhs", "");										
						obj.put("wehll", "");										
						obj.put("wehyl", "");
						obj.put("wehhs", "");								
						obj.put("zyel", "");
						obj.put("zyoul", "");
						obj.put("jhgye", "");
						obj.put("jhgyou", "");										
						obj.put("pk", "");										
						obj.put("scyel", "");
						obj.put("scyoul", "");
						obj.put("remark", "");
						arr.add(obj);
						
						obj.put("rpd_xyl_line_messure_id", "/");
						obj.put("report_date",  "月总产量");
						obj.put("xzjll", "");
						obj.put("xzjyl", "");
						obj.put("xzjhs", "");										
						obj.put("wehll", "");										
						obj.put("wehyl", "");
						obj.put("wehhs", "");								
						obj.put("zyel", "");
						obj.put("zyoul", "");
						obj.put("jhgye", "");
						obj.put("jhgyou", "");										
						obj.put("pk", "");										
						obj.put("scyel", "");
						obj.put("scyoul", "");
						obj.put("remark", "");
					   arr.add(obj);					
					}
				   return arr;	
				}

				@Override
				public List<Object[]> searchxyfx3(String string, String fields) {
					String sql = "";
					if(!string.equals("")){
						String stratime =DateBean.getBefore6DAYTime(string);
						String endtime = string;
						sql = "select " + fields + " from   PC_RPD_BOILER_SUPERHEAT_YC_T   u where u.ACQUISITION_TIME between TO_DATE('"+stratime+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+endtime+"','YYYY-MM-DD HH24:MI:SS') order by u.ACQUISITION_TIME";
					}else{
						sql = fields;
					}					
					List<Object[]> yyList = lineMeasureDao.searchU2OIL(sql);
					return yyList;
				}

				@SuppressWarnings({ "null", "unused" })
				@Override
				public JSONArray searchCysc(String txtDate) {
					JSONObject obj = new JSONObject();
					JSONArray arr = new JSONArray();
					Object[] u2oiltable = null;
					int dataflg  =0; //数据存在表示 0：不存在； 1： 存在
					
					String sql = "select rpd_cy1_general_id,rq,jhg_youl,jhg_ds,tsb,wuy,wuypk,fxccy,pank,ftq_s1o,ftq_s1s,remark  "+
							"  from PC_RPD_CY1_GENERAL_T where rq = TO_DATE('"+txtDate+"','YYYY-MM-DD')";
			
					List<Object[]> U1S1S = lineMeasureDao.searchU2OIL(sql);
					if(U1S1S !=null && U1S1S.size()>0){	
						obj = new JSONObject();						
						obj.put("rpd_cy1_general_id", u2oiltable[0]);
						obj.put("rq", u2oiltable[1]);
						obj.put("jhg_youl",CommonsUtil.getNotTwoData(u2oiltable[2]));
						obj.put("jhg_ds", CommonsUtil.getNotTwoData(u2oiltable[3]));
						obj.put("tsb", CommonsUtil.getNotTwoData(u2oiltable[4]));
						obj.put("wuy", CommonsUtil.getNotTwoData(u2oiltable[5]));
						obj.put("wuypk", CommonsUtil.getNotTwoData(u2oiltable[6]));
						obj.put("fxccy", CommonsUtil.getNotTwoData(u2oiltable[7]));
						obj.put("pank", CommonsUtil.getNotTwoData(u2oiltable[8]));
						obj.put("ftq_s1o", CommonsUtil.getNotTwoData(u2oiltable[9]));
						obj.put("ftq_s1s", CommonsUtil.getNotTwoData(u2oiltable[10]));
						obj.put("remark", CommonsUtil.getNotTwoData(u2oiltable[11]));
					}else{
						obj.put("rpd_cy1_general_id", "");
						obj.put("rq", "");
						obj.put("jhg_youl","");
						obj.put("jhg_ds", "");
						obj.put("tsb", "");
						obj.put("wuy", "");
						obj.put("wuypk", "");
						obj.put("fxccy", "");
						obj.put("pank", "");
						obj.put("ftq_s1o", "");
						obj.put("ftq_s1s", "");
						obj.put("remark", "");
					}
					arr.add(obj);
					return arr;
				}

				@Override
				public List<PcRpdCy1ScMessureT> Sreachcyfx1(String id,
						String name) {
					String hql ="from PcRpdCy1ScMessureT a  where 1=1";
					if(id!=null && !"".equals(id)){
					      hql +=" and a.rpdcy1scmessureid = '"+id+"'";
					}
					if(name !=null && !"".equals(name)){
						hql=" and a.report_date = '"+name+"'";
					}
					return lineMeasureDao.Sreachcyfx1(hql);
				}

				@Override
				public List<PcRpdCy2ScMessureT> Sreachcyfx2(String id,
						String name) {
					String hql ="from PcRpdCy2ScMessureT a  where 1=1";
					if(id!=null && !"".equals(id)){
					      hql +=" and a.rpdcy2scmessureid = '"+id+"'";
					}
					if(name !=null && !"".equals(name)){
						hql=" and a.report_date = '"+name+"'";
					}
					return lineMeasureDao.Sreachcyfx2(hql);
				}

				@Override
				public List<PcRpdXylScMessureT> Sreachxyfx3(String id,
						String name) {
					String hql ="from PcRpdXylScMessureT a  where 1=1";
					if(id!=null && !"".equals(id)){
					      hql +=" and a.rpdxylscmessureid = '"+id+"'";
					}
					if(name !=null && !"".equals(name)){
						hql=" and a.report_date = '"+name+"'";
					}
					return lineMeasureDao.Sreachxyfx3(hql);
				}

				@Override
				public PcRpdCy1ScMessureT Sreachcyfx1rq(String RQ) {
					String hql ="from PcRpdCy1LineMessureT a  where 1=1";
					
					if(RQ !=null && !"".equals(RQ)){
						hql=" and a.bbsj = '"+RQ+"'";
					}
					return lineMeasureDao.Sreachcyfx1rq(hql);
				}

				@Override
				public JSONArray searchFxjlsc1(String date) {
					JSONObject obj = new JSONObject();
					JSONArray arr = new JSONArray();

					String sql = "select rpd_cy1_sc_messure_id ,to_char(a.report_date,'YYYY-MM-DD')as report_date,jhg_youl,jhg_ds,tsb," +
							"wuy,wuypk,fxccy,pank,ftq_s1s,remark from  PC_RPD_CY1_SC_MESSURE_T a where 1=1";
					sql += " and a.report_date =TO_DATE('"+date+"','YYYY-MM-DD')";					
					List<Object[]> U1S1S = lineMeasureDao.searchU2OIL(sql);
					if(U1S1S !=null && U1S1S.size()>0){	
						obj = new JSONObject();						
						obj.put("rpd_cy1_sc_messure_id", U1S1S.get(0)[0]);
						obj.put("report_date",  U1S1S.get(0)[1]);
						obj.put("jhg_youl",CommonsUtil.getNotTwoDataZero( U1S1S.get(0)[2]));
						obj.put("jhg_ds", CommonsUtil.getNotTwoDataZero( U1S1S.get(0)[3]));
						obj.put("tsb", CommonsUtil.getNotTwoDataZero( U1S1S.get(0)[4]));
						obj.put("wuy", CommonsUtil.getNotTwoDataZero( U1S1S.get(0)[5]));
						obj.put("wuypk", CommonsUtil.getNotTwoDataZero( U1S1S.get(0)[6]));
						obj.put("fxccy", CommonsUtil.getNotTwoDataZero( U1S1S.get(0)[7]));
						obj.put("pank", CommonsUtil.getNotTwoDataZero( U1S1S.get(0)[8]));
						obj.put("ftq_s1s", CommonsUtil.getNotTwoDataZero( U1S1S.get(0)[9]));
						if(U1S1S.get(0)[10] == null || U1S1S.get(0)[10].equals("undefined")){
							obj.put("remark",  "");
						}else{
							obj.put("remark",  U1S1S.get(0)[10]);
						}
					}else{
						obj.put("rpd_cy1_sc_messure_id", "");
						obj.put("report_date", "");
						obj.put("jhg_youl","");
						obj.put("jhg_ds", "");
						obj.put("tsb", "");
						obj.put("wuy", "");
						obj.put("wuypk", "");
						obj.put("fxccy", "");
						obj.put("pank", "");
						obj.put("ftq_s1s", "");
						obj.put("remark", "");
					}
					arr.add(obj);
					return arr;
				}

				@Override
				public JSONArray searchFxjlsc2(String txtDate) {
					JSONObject obj = new JSONObject();
					JSONArray arr = new JSONArray();

					String sql = "select rpd_cy2_sc_messure_id ,to_char(a.report_date,'YYYY-MM-DD')as report_date,sagd6000ye,sagd6000you,jhgyou," +
							"cdsgh,chdyw,ds,wy,pkyl,remark from  PC_RPD_CY2_SC_MESSURE_T a where 1=1";
					sql += " and a.report_date =TO_DATE('"+txtDate+"','YYYY-MM-DD')";					
					List<Object[]> U1S1S = lineMeasureDao.searchU2OIL(sql);
					if(U1S1S !=null && U1S1S.size()>0){	
						obj = new JSONObject();						
						obj.put("rpd_cy2_sc_messure_id", U1S1S.get(0)[0]);
						obj.put("report_date",  U1S1S.get(0)[1]);
						obj.put("sagd6000ye",CommonsUtil.getNotTwoDataZero( U1S1S.get(0)[2]));
						obj.put("sagd6000you", CommonsUtil.getNotTwoDataZero( U1S1S.get(0)[3]));
						obj.put("jhgyou", CommonsUtil.getNotTwoDataZero( U1S1S.get(0)[4]));
						if(U1S1S.get(0)[5] == null || U1S1S.get(0)[5].equals("undefined")){
							obj.put("cdsgh",  "");
						}else{
							obj.put("cdsgh",  U1S1S.get(0)[5].toString());
						}
						obj.put("chdyw", CommonsUtil.getNotTwoDataZero( U1S1S.get(0)[6]));
						obj.put("ds", CommonsUtil.getNotTwoDataZero( U1S1S.get(0)[7]));
						obj.put("wy", CommonsUtil.getNotTwoDataZero( U1S1S.get(0)[8]));
						obj.put("pkyl", CommonsUtil.getNotTwoDataZero( U1S1S.get(0)[9]));
						if(U1S1S.get(0)[10] == null || U1S1S.get(0)[10].equals("undefined")){
							obj.put("remark",  "");
						}else{
							obj.put("remark",  U1S1S.get(0)[10].toString());
						}
					}else{
						obj.put("rpd_cy2_sc_messure_id", "");
						obj.put("report_date", "");
						obj.put("sagd6000ye","");
						obj.put("sagd6000you", "");
						obj.put("jhgyou", "");
						obj.put("cdsgh", "");
						obj.put("chdyw", "");
						obj.put("ds", "");
						obj.put("wy", "");
						obj.put("pkyl", "");
						obj.put("remark", "");
					}
					arr.add(obj);
					return arr;
				}

				@Override
				public JSONArray searchFxjlsc3(String txtDate) {
					JSONObject obj = new JSONObject();
					JSONArray arr = new JSONArray();

					String sql = "select rpd_xyl_sc_messure_id ,to_char(a.report_date,'YYYY-MM-DD')as report_date,jhgye,jhgyou,pk," +
							"remark from  PC_RPD_XYL_SC_MESSURE_T a where 1=1";
					sql += " and a.report_date =TO_DATE('"+txtDate+"','YYYY-MM-DD')";					
					List<Object[]> U1S1S = lineMeasureDao.searchU2OIL(sql);
					if(U1S1S !=null && U1S1S.size()>0){	
						obj = new JSONObject();						
						obj.put("rpd_xyl_sc_messure_id", U1S1S.get(0)[0]);
						obj.put("report_date",  U1S1S.get(0)[1]);
						obj.put("jhgye",CommonsUtil.getNotTwoDataZero( U1S1S.get(0)[2]));
						obj.put("jhgyou", CommonsUtil.getNotTwoDataZero( U1S1S.get(0)[3]));
						obj.put("pk", CommonsUtil.getNotTwoDataZero( U1S1S.get(0)[4]));
						if(U1S1S.get(0)[5] == null || U1S1S.get(0)[5].equals("undefined")){
							obj.put("remark",  "");
						}else{
							obj.put("remark",  U1S1S.get(0)[5]);
						}
					}else{
						obj.put("rpd_cy2_sc_messure_id", "");
						obj.put("report_date", "");
						obj.put("jhgye","");
						obj.put("jhgyou", "");
						obj.put("pk", "");
						obj.put("remark", "");
					}
					arr.add(obj);
					return arr;
				}

				@Override
				public JSONArray searchU2rhz(String txtDate) {
					JSONObject obj = new JSONObject();
					JSONArray arr = new JSONArray();

					String sql = "select pc_prd_report_rhs2_id ,to_char(a.report_date,'YYYY-MM-DD')as report_date,rhqts_72qs,rhqts_72ws,rhqts_216ws," +
							"RHQHJ,yxzs_72qs,yxzs_72ws,yxzs_216ws,YXZSHJ,ss_72qs,ss_72ws,ss_216ws,SSHJ,rzl_72qs,rzl_72ws,rzl_216ws,RZLHJ,"+
							"zszs_72qs,zszs_72ws,zszs_216ws,ZSZSHJ,zsysl_72qs,zsysl_72ws,zsysl_216ws,ZSYSLHJ,dwcy_hy_2r,dwcy_hy_2r_jcr,jyb_hy_2r,"+
							"jyb_hy_2r_jcr,GL35_29_HY,GL35_29_HY_JCR,z45sagd_hy,z45sagd_hy_jcr,z1sagd_hy,gwcy_jzs,z1sagd_hy_jcr,gwcy_jzs_jcr,"+
							"jyb_jzs,jyb_jzs_jcr,sds_bb,sds_yb,sds_kc,qs_yd,qs_lg,qs_jd,ws_yd,ws_lg,ws_jd,ghj_yd,ghj_lg,ghj_jd,hy1,hy2,"+
							"hy3,xf1,xf2,xf3,xgnh_d1,xgnh_yd1,XGNH_DL1,xgnh_d2,xgnh_yd2,XGNH_DL2,xgnh_d3,xgnh_yd3,"+
							"XGNH_DL3,xgnh_d4,xgnh_yd4,XGNH_DL4,ch1,ch2,ch3,ch4,ch5,ch6,xgnh_y1_jsr,xgnh_y2_jsr,xgnh_y3_jsr,xgnh_y4_jsr,xgnh_y5_jsr,"+
							"xgnh_y6_jsr,fst_ys,fst_yys,FST_SL,fst_cs,fst_ycs,FST_CSL,hzbsl,hzbsl_y,HZBSL_SL,hgbsl,hgbsl_y,HGBSL_SL,"+
							"ycpwsl,ycpwsl_y,YCPWSL_SL,xgnh_y1,xgnh_y2,xgnh_y3,xgnh_y4,xgnh_y5,xgnh_y6  from  PC_RPD_REPORT_RHS2_V a where 1=1";
					sql += " and a.report_date =TO_DATE('"+txtDate+"','YYYY-MM-DD')";
				
					String menuInfo="pc_prd_report_rhs2_id,report_date,rhqts_72qs,rhqts_72ws,rhqts_216ws," +
							"RHQHJ,yxzs_72qs,yxzs_72ws,yxzs_216ws,YXZSHJ,ss_72qs,ss_72ws,ss_216ws,SSHJ,rzl_72qs,rzl_72ws,rzl_216ws,RZLHJ,"+
							"zszs_72qs,zszs_72ws,zszs_216ws,ZSZSHJ,zsysl_72qs,zsysl_72ws,zsysl_216ws,ZSYSLHJ,dwcy_hy_2r,dwcy_hy_2r_jcr,jyb_hy_2r,"+
							"jyb_hy_2r_jcr,GL35_29_HY,GL35_29_HY_JCR,z45sagd_hy,z45sagd_hy_jcr,z1sagd_hy,gwcy_jzs,z1sagd_hy_jcr,gwcy_jzs_jcr,"+
							"jyb_jzs,jyb_jzs_jcr,sds_bb,sds_yb,sds_kc,qs_yd,qs_lg,qs_jd,ws_yd,ws_lg,ws_jd,ghj_yd,ghj_lg,ghj_jd,hy1,hy2,"+
							"hy3,xf1,xf2,xf3,xgnh_d1,xgnh_yd1,XGNH_DL1,xgnh_d2,xgnh_yd2,XGNH_DL2,xgnh_d3,xgnh_yd3,"+
							"XGNH_DL3,xgnh_d4,xgnh_yd4,XGNH_DL4,ch1,ch2,ch3,ch4,ch5,ch6,xgnh_y1_jsr,xgnh_y2_jsr,xgnh_y3_jsr,xgnh_y4_jsr,xgnh_y5_jsr,"+
							"xgnh_y6_jsr,fst_ys,fst_yys,FST_SL,fst_cs,fst_ycs,FST_CSL,hzbsl,hzbsl_y,HZBSL_SL,hgbsl,hgbsl_y,HGBSL_SL,"+
							"ycpwsl,ycpwsl_y,YCPWSL_SL,xgnh_y1,xgnh_y2,xgnh_y3,xgnh_y4,xgnh_y5,xgnh_y6";
					menuInfo=menuInfo.toLowerCase();
					String[] infos= menuInfo.split(",");				
					List<Object[]> U1S1S = lineMeasureDao.searchU2OIL(sql);
					if(U1S1S !=null && U1S1S.size()>0){	
						
						//处理页面未定义值
						for(int i=0;i<U1S1S.size();i++){
							for (int j = 0; j < U1S1S.get(i).length; j++) {
								if(U1S1S.get(i)[j] == null || U1S1S.get(i)[j].equals("undefined")){
									U1S1S.get(i)[j]="";
								}
							}
						}								
						obj = new JSONObject();		
						for (int i = 0; i < infos.length; i++) {
							obj.put(infos[i], U1S1S.get(0)[i]);
						}
						
						
					/*	
						
						obj.put("pc_prd_report_rhs2_id", U1S1S.get(0)[0]);
						obj.put("report_date",  U1S1S.get(0)[1]);
						obj.put("rhqts_72qs",U1S1S.get(0)[2]);
						obj.put("rhqts_72ws",  U1S1S.get(0)[3]);
						obj.put("rhqts_216ws",  U1S1S.get(0)[4]);
						obj.put("rhqhj",  U1S1S.get(0)[5]);
						obj.put("yxzs_72qs",  U1S1S.get(0)[6]);
						obj.put("yxzs_72ws",  U1S1S.get(0)[7]);
						obj.put("yxzs_216ws",  U1S1S.get(0)[8]);
						obj.put("yxzshj",  U1S1S.get(0)[9]);
						
						obj.put("ss_72qs", CommonsUtil.getNotOneData(U1S1S.get(0)[10]));
						obj.put("ss_72ws", CommonsUtil.getNotOneData(U1S1S.get(0)[11]));
						obj.put("ss_216ws", CommonsUtil.getNotOneData(U1S1S.get(0)[12]));
						obj.put("sshj",  CommonsUtil.getNotOneData(U1S1S.get(0)[13]));
						obj.put("rzl_72qs",  CommonsUtil.getNotOneData(U1S1S.get(0)[14]));
						obj.put("rzl_72ws",  CommonsUtil.getNotOneData(U1S1S.get(0)[15]));
						obj.put("rzl_216ws",  CommonsUtil.getNotOneData(U1S1S.get(0)[16]));
						obj.put("rzlhj",  CommonsUtil.getNotOneData(U1S1S.get(0)[17]));
						
						obj.put("zszs_72qs", U1S1S.get(0)[18]);
						obj.put("zszs_72ws",  U1S1S.get(0)[19]);
						obj.put("zszs_216ws",  U1S1S.get(0)[20]);
						obj.put("zszshj",  U1S1S.get(0)[21]);
						obj.put("zsysl_72qs",  CommonsUtil.getNotOneData(U1S1S.get(0)[22]));
						obj.put("zsysl_72ws",  CommonsUtil.getNotOneData(U1S1S.get(0)[23]));
						obj.put("zsysl_216ws",  CommonsUtil.getNotOneData(U1S1S.get(0)[24]));
						obj.put("zsyslhj",  CommonsUtil.getNotOneData(U1S1S.get(0)[25]));
						obj.put("dwcy_hy_2r", CommonsUtil.getNotTwoData(U1S1S.get(0)[26]));
						obj.put("dwcy_hy_2r_jcr",  CommonsUtil.getNotTwoData(U1S1S.get(0)[27]));
						obj.put("jyb_hy_2r",  CommonsUtil.getNotTwoData(U1S1S.get(0)[28]));
						obj.put("jyb_hy_2r_jcr",  CommonsUtil.getNotTwoData(U1S1S.get(0)[29]));
						obj.put("gl3529_hy",  CommonsUtil.getNotTwoData(U1S1S.get(0)[30]));
						obj.put("gl3529_hy_jcr",  CommonsUtil.getNotTwoData(U1S1S.get(0)[31]);
						obj.put("z45sagd_hy", CommonsUtil.getNotTwoData(U1S1S.get(0)[32]));
						obj.put("z45sagd_hy_jcr",  CommonsUtil.getNotTwoData(U1S1S.get(0)[33]);
						obj.put("z1sagd_hy", CommonsUtil.getNotTwoData(U1S1S.get(0)[34]));
						obj.put("gwcy_jzs",   CommonsUtil.getNotTwoData(U1S1S.get(0)[35]));
						obj.put("z1sagd_hy_jcr",   CommonsUtil.getNotTwoData(U1S1S.get(0)[36]);
						obj.put("gwcy_jzs_jcr",  CommonsUtil.getNotTwoData(U1S1S.get(0)[37]);
						obj.put("jyb_jzs",  CommonsUtil.getNotTwoData(U1S1S.get(0)[38]));
						obj.put("jyb_jzs_jcr",  CommonsUtil.getNotTwoData(U1S1S.get(0)[39]);
						
						
						obj.put("sds_bb",  U1S1S.get(0)[40]);
						obj.put("sds_yb",  U1S1S.get(0)[41]);
						
						obj.put("sds_kc", U1S1S.get(0)[42]);
						
						obj.put("qs_yd",    CommonsUtil.getNotOneData(U1S1S.get(0)[43]));
						obj.put("qs_lg",    CommonsUtil.getNotOneData(U1S1S.get(0)[44]));
						obj.put("qs_jd",   CommonsUtil.getNotOneData(U1S1S.get(0)[45]));
						obj.put("ws_yd",   CommonsUtil.getNotOneData(U1S1S.get(0)[46]));
						obj.put("ws_lg",    CommonsUtil.getNotOneData(U1S1S.get(0)[47]));
						obj.put("ws_jd",  CommonsUtil.getNotOneData(U1S1S.get(0)[48]));
						obj.put("ghj_yd",   CommonsUtil.getNotOneData(U1S1S.get(0)[49]));
						
						obj.put("ghj_lg",  CommonsUtil.getNotOneData(U1S1S.get(0)[50]));
						obj.put("ghj_jd",    CommonsUtil.getNotOneData(U1S1S.get(0)[51]));
						obj.put("hy1",    CommonsUtil.getNotTwoData(U1S1S.get(0)[52]));
						obj.put("hy2",    CommonsUtil.getNotTwoData(U1S1S.get(0)[53]));
						obj.put("hy3",   CommonsUtil.getNotTwoData(U1S1S.get(0)[54]));
						obj.put("xf1",    CommonsUtil.getNotTwoData(U1S1S.get(0)[55]));
						obj.put("xf2",  CommonsUtil.getNotTwoData(U1S1S.get(0)[56]));
						obj.put("xf3",   CommonsUtil.getNotTwoData(U1S1S.get(0)[57]));
						
						obj.put("xgnh_d1", CommonsUtil.getNotOneData(U1S1S.get(0)[58]));
						obj.put("xgnh_yd1",  CommonsUtil.getNotOneData(U1S1S.get(0)[59]));
						obj.put("xgnh_dl1",  CommonsUtil.getNotOneData(U1S1S.get(0)[60]));
						obj.put("xgnh_d2",  CommonsUtil.getNotOneData(U1S1S.get(0)[61]));
						obj.put("xgnh_yd2", CommonsUtil.getNotOneData(U1S1S.get(0)[62]));
						obj.put("xgnh_dl2",  CommonsUtil.getNotOneData(U1S1S.get(0)[63]));
						obj.put("xgnh_yd3", CommonsUtil.getNotOneData(U1S1S.get(0)[64]));
						
						if(U1S1S.get(0)[65]==null || U1S1S.get(0)[65].equals("undefined")){
							obj.put("xgnh_d3", "");
						}else {
							obj.put("xgnh_d3",  CommonsUtil.getNotOneData(U1S1S.get(0)[65]));
						}
						
						obj.put("xgnh_dl3", CommonsUtil.getNotOneData(U1S1S.get(0)[66]));
						obj.put("xgnh_yd4",  CommonsUtil.getNotOneData(U1S1S.get(0)[67]));
						
						if(U1S1S.get(0)[68]==null || U1S1S.get(0)[68].equals("undefined")){
							obj.put("xgnh_d4", "");
						}else{
							obj.put("xgnh_d4",   CommonsUtil.getNotOneData(U1S1S.get(0)[68]));
							
						}
						
						obj.put("xgnh_dl4",   CommonsUtil.getNotOneData(U1S1S.get(0)[69]));
						if(U1S1S.get(0)[70] == null || U1S1S.get(0)[70].equals("undefined")){
							obj.put("ch1",  "");
						}else{
							obj.put("ch1",  U1S1S.get(0)[70]);
						}
						if(U1S1S.get(0)[71] == null || U1S1S.get(0)[71].equals("undefined")){
							obj.put("ch2",  "");
						}else{
							obj.put("ch2",  U1S1S.get(0)[71]);
						}
						if(U1S1S.get(0)[72] == null || U1S1S.get(0)[72].equals("undefined")){
							obj.put("ch3",  "");
						}else{
							obj.put("ch3",  U1S1S.get(0)[72]);
						}
						if(U1S1S.get(0)[73] == null || U1S1S.get(0)[73].equals("undefined")){
							obj.put("ch4",  "");
						}else{
							obj.put("ch4",  U1S1S.get(0)[73]);
						}
						if(U1S1S.get(0)[74] == null || U1S1S.get(0)[74].equals("undefined")){
							obj.put("ch5",  "");
						}else{
							obj.put("ch5",  U1S1S.get(0)[74]);
						}
						if(U1S1S.get(0)[75] == null || U1S1S.get(0)[75].equals("undefined")){
							obj.put("ch6",  "");
						}else{
							obj.put("ch6",  U1S1S.get(0)[75]);
						}
						
						if(U1S1S.get(0)[76] == null || U1S1S.get(0)[76].equals("undefined")){
							obj.put("xgnh_y1_jsr",  "");
						}else{
							obj.put("xgnh_y1_jsr",  U1S1S.get(0)[76]);
						}
						if(U1S1S.get(0)[77] == null || U1S1S.get(0)[77].equals("undefined")){
							obj.put("xgnh_y2_jsr",  "");
						}else{
							obj.put("xgnh_y2_jsr",  U1S1S.get(0)[77]);
						}
						if(U1S1S.get(0)[78] == null || U1S1S.get(0)[78].equals("undefined")){
							obj.put("xgnh_y3_jsr",  "");
						}else{
							obj.put("xgnh_y3_jsr",  U1S1S.get(0)[78]);
						}
						if(U1S1S.get(0)[79] == null || U1S1S.get(0)[79].equals("undefined")){
							obj.put("xgnh_y4_jsr",  "");
						}else{
							obj.put("xgnh_y4_jsr",  U1S1S.get(0)[79]);
						}
						if(U1S1S.get(0)[80] == null || U1S1S.get(0)[80].equals("undefined")){
							obj.put("xgnh_y5_jsr",  "");
						}else{
							obj.put("xgnh_y5_jsr",  U1S1S.get(0)[80]);
						}
						if(U1S1S.get(0)[81] == null || U1S1S.get(0)[81].equals("undefined")){
							obj.put("xgnh_y6_jsr",  "");
						}else{
							obj.put("xgnh_y6_jsr",  U1S1S.get(0)[81]);
						}
						
						obj.put("fst_ys",  CommonsUtil.getNotOneData(U1S1S.get(0)[82]));
						obj.put("fst_yys",    CommonsUtil.getNotOneData(U1S1S.get(0)[83]));
						obj.put("fst_sl",    CommonsUtil.getNotOneData(U1S1S.get(0)[84]));
						obj.put("fst_cs",    CommonsUtil.getNotOneData(U1S1S.get(0)[85]));
						obj.put("fst_ycs",  CommonsUtil.getNotOneData(U1S1S.get(0)[86]));
						obj.put("fst_csl",   CommonsUtil.getNotOneData(U1S1S.get(0)[87]));
						obj.put("hzbsl",    CommonsUtil.getNotOneData(U1S1S.get(0)[88]));
						obj.put("hzbsl_y",   CommonsUtil.getNotOneData(U1S1S.get(0)[89]));
						
						obj.put("hzbsl_sl",  CommonsUtil.getNotOneData(U1S1S.get(0)[90]));
						obj.put("hgbsl",    CommonsUtil.getNotOneData(U1S1S.get(0)[91]));
						obj.put("hgbsl_y",    CommonsUtil.getNotOneData(U1S1S.get(0)[92]));
						obj.put("hgbsl_sl",   CommonsUtil.getNotOneData(U1S1S.get(0)[93]));
						obj.put("ycpwsl",  CommonsUtil.getNotOneData(U1S1S.get(0)[94]));
						obj.put("ycpwsl_y",    CommonsUtil.getNotOneData(U1S1S.get(0)[95]));
						obj.put("ycpwsl_sl",   CommonsUtil.getNotOneData(U1S1S.get(0)[96]));
						
						obj.put("xgnh_y1",   CommonsUtil.getNotOneData(U1S1S.get(0)[97]));
						obj.put("xgnh_y2",   CommonsUtil.getNotOneData(U1S1S.get(0)[98]));
						obj.put("xgnh_y3",   CommonsUtil.getNotOneData(U1S1S.get(0)[99]));
						obj.put("xgnh_y4",   CommonsUtil.getNotOneData(U1S1S.get(0)[100]));
						obj.put("xgnh_y5",  CommonsUtil.getNotOneData(U1S1S.get(0)[101]));
						obj.put("xgnh_y6",   CommonsUtil.getNotOneData(U1S1S.get(0)[102]));
						*/
						
					}else{
						for (int i = 0; i < infos.length; i++) {
							obj.put(infos[i], "");
						}
						/*obj.put("pc_prd_report_rhs2_id",  "");
						obj.put("report_date",   "");
						obj.put("rhqts_72qs",    "");
						obj.put("rhqts_72ws",    "");
						obj.put("rhqts_216ws",   "");
						obj.put("rhqhj",		 "");
						obj.put("yxzs_72qs",     "");
						obj.put("yxzs_72ws",     "");
						obj.put("yxzs_216ws",    "");
						obj.put("yxzshj",     "");
						
						obj.put("ss_72qs",    "");
						obj.put("ss_72ws",     "");
						obj.put("ss_216ws",     "");
						obj.put("sshj",     "");
						obj.put("rzl_72qs",     "");
						obj.put("rzl_72ws",     "");
						obj.put("rzl_216ws",     "");
						obj.put("rzlhj",     "");
						
						obj.put("zszs_72qs",    "");
						obj.put("zszs_72ws",     "");
						obj.put("zszs_216ws",     "");
						obj.put("zszshj",     "");
						obj.put("zsysl_72qs",     "");
						obj.put("zsysl_72ws",     "");
						obj.put("zsysl_216ws",     "");
						obj.put("zsyslhj",     "");
						
						obj.put("dwcy_hy_2r",    "");
						obj.put("dwcy_hy_2r_jcr",      "");
						obj.put("jyb_hy_2r",     "");
						obj.put("jyb_hy_2r_jcr",      "");
						obj.put("cl3529_hy",     "");
						obj.put("cl3529_hy_jcr",      "");
						obj.put("z45sagd_hy",     "");
						obj.put("z45sagd_hy_jcr",     "");
						
						obj.put("z1sagd_hy",    "");
						obj.put("gwcy_jzs",      "");
						obj.put("z1sagd_hy_jcr",     "");
						obj.put("gwcy_jzs_jcr",      "");
						obj.put("jyb_jzs",     "");
						obj.put("jyb_jzs_jcr",      "");
						obj.put("sds_bb",     "");
						obj.put("sds_yb",     "");
						
						obj.put("sds_kc",    "");
						obj.put("qs_yd",      "");
						obj.put("qs_lg",      "");
						obj.put("qs_jd",      "");
						obj.put("ws_yd",     "");
						obj.put("ws_lg",      "");
						obj.put("ws_jd",     "");
						obj.put("ghj_yd",     "");
						
						obj.put("ghj_lg",    "");
						obj.put("ghj_jd",     "");
						obj.put("hy1",     "");
						obj.put("hy2",      "");
						obj.put("hy3",     "");
						obj.put("xf1",      "");
						obj.put("xf2",     "");
						obj.put("xf3",     "");
						
						obj.put("xgnh_d1",    "");
						obj.put("xgnh_yd1",      "");
						obj.put("xgnh_dl1",     "");
						obj.put("xgnh_d2",      "");
						obj.put("xgnh_d3",      "");//处理页面未定义
						obj.put("xgnh_d4",      "");//处理页面未定义
						
						obj.put("gl3529_hy",      "");//处理页面未定义
						obj.put("gl3529_hy_jcr",      "");//处理页面未定义
						
						
						obj.put("xgnh_yd2",     "");
						obj.put("xgnh_dl2",      "");
						obj.put("xgnh_y3",     "");
						obj.put("xgnh_yd3",    "");
						
						obj.put("xgnh_dl3",    "");
						obj.put("xgnh_y4",      "");
						obj.put("xgnh_yd4",      "");
						obj.put("xgnh_dl4",      "");
						obj.put("ch1",      "");
						obj.put("ch2",       "");
						obj.put("ch3",     "");
						obj.put("ch4",      "");
						
						obj.put("ch5",      "");
						obj.put("ch6",       "");
						obj.put("xgnh_y1_jsr",     "");
						obj.put("xgnh_y2_jsr",      "");
						obj.put("xgnh_y3_jsr",     "");
						obj.put("xgnh_y4_jsr",       "");
						obj.put("xgnh_y5_jsr",     "");
						obj.put("xgnh_y6_jsr",      "");
						
						obj.put("fst_ys",    "");
						obj.put("fst_yys",      "");
						obj.put("fst_sl",      "");
						obj.put("fst_cs",      "");
						obj.put("fst_ycs",    "");
						obj.put("fst_csl",      "");
						obj.put("hzbsl",      "");
						obj.put("hzbsl_y",      "");
						
						obj.put("hzbsl_sl",    "");
						obj.put("hgbsl",      "");
						obj.put("hgbsl_y",      "");
						obj.put("hgbsl_sl",      "");
						obj.put("ycpwsl",    "");
						obj.put("ycpwsl_y",      "");
						obj.put("ycpwsl_sl",      "");
						
						obj.put("xgnh_y1",   "");
						obj.put("xgnh_y2",   "");
						obj.put("xgnh_y3",   "");
						obj.put("xgnh_y4",  "");
						obj.put("xgnh_y5",   "");
						obj.put("xgnh_y6",  "");*/
					}
					arr.add(obj);
					return arr;
				}

				@Override
				public List<PcRpdReportRhs2T> Sreachu2rhs(String id,
						String name) {
					String hql ="from PcRpdReportRhs2T a  where 1=1";
					if(id!=null && !"".equals(id)){
					      hql +=" and a.pc_prd_report_rhs2_id = '"+id+"'";
					}
					if(name !=null && !"".equals(name)){
						hql=" and a.report_date = '"+name+"'";
					}
					return lineMeasureDao.Sreachu2rhs(hql);
				}

				@Override
				public List<Object[]> searchExportDatas(String txtDate,
						String sqls) {
					sqls += " from  pc_rpd_report_rhs2_t  where 1=1 and report_date  =TO_DATE('"+txtDate+"','YYYY-MM-DD') ";
					return lineMeasureDao.searchExportData(sqls);
				}

				@Override
				public List<Object[]> searchExportTopDatas(String txtDate,
						String sqls) {
					sqls += " from  PC_RPD_REPORT_MESSAGE_T  where 1=1 and RQ  =TO_DATE('"+txtDate+"','YYYY-MM-DD') and BBMC = '2#软化水站综合日报'";
					return lineMeasureDao.searchExportData(sqls);
				}

}