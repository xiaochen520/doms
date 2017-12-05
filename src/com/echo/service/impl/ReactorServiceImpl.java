package com.echo.service.impl;


import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.dao.ReactorDao;
import com.echo.service.ReactorService;
import com.echo.util.CommonsUtil;
import com.echo.util.DateBean;

public class ReactorServiceImpl implements ReactorService{

	private ReactorDao reactorDao;
	public void setReactorDao(ReactorDao reactorDao) {
		this.reactorDao = reactorDao;
	}

	@SuppressWarnings("null")
	@Override
	public JSONArray searchReactor(String reportDate) throws Exception {
		JSONObject obj = new JSONObject();
		JSONArray arr = new JSONArray();
		
		String aa="";
		String FIT_20301A  = "";
		String FIT20301AZ  = "";
		String FIT_20301B = "";
		String FIT20301BZ  = "";
		String FIT_20301C  = "";
		String FIT20301CZ = "";
		String FIT_20301D  = "";
		String FIT20301DZ = "";
		String FIT_20301E  = "";
		String  FIT20301EZ = "";
		String  FIT_20301F = "";
		String  fit20301fz = "";
		
		List<String> FIT_20301ASUM  = new ArrayList<String>();
		List<String> FIT20301AZSUM  = new ArrayList<String>();
		List<String> FIT_20301BSUM = new ArrayList<String>();
		List<String> FIT20301BZSUM  = new ArrayList<String>();
		List<String> FIT_20301CSUM  = new ArrayList<String>();
		List<String> FIT20301CZSUM = new ArrayList<String>();
		List<String> FIT_20301DSUM  = new ArrayList<String>();
		List<String> FIT20301DZSUM = new ArrayList<String>();
		List<String> FIT_20301ESUM  = new ArrayList<String>();
		List<String> FIT20301EZSUM = new ArrayList<String>();
		List<String> FIT_20301FSUM = new ArrayList<String>();
		List<String> fit20301fzSUM = new ArrayList<String>();
		
		
		//合计
		List<String> FIT_20301ASUMday  = new ArrayList<String>();
		List<String> FIT20301AZSUMday  = new ArrayList<String>();
		List<String> FIT_20301BSUMday = new ArrayList<String>();
		List<String> FIT20301BZSUMday  = new ArrayList<String>();
		List<String> FIT_20301CSUMday  = new ArrayList<String>();
		List<String> FIT20301CZSUMday = new ArrayList<String>();
		List<String> FIT_20301DSUMday  = new ArrayList<String>();
		List<String> FIT20301DZSUMday = new ArrayList<String>();
		List<String> FIT_20301ESUMday  = new ArrayList<String>();
		List<String> FIT20301EZSUMday = new ArrayList<String>();
		List<String> FIT_20301FSUMday = new ArrayList<String>();
		List<String> fit20301fzSUMday = new ArrayList<String>();
		
		int dataflg  =0; //数据存在表示 0：不存在； 1： 存在
		String timeCalssql = "select tp.parameter_value from PC_CD_TRIGGERED_PROCESS_T tp where tp.parameter_name='union_station_rpd_start_time'";
		String calcNum = "-16";
		List<Object[]> dataSet = reactorDao.searchReactor(timeCalssql);
		if(dataSet !=null && dataSet.size()>0){
			calcNum = dataSet.get(0) + "";
		}
		String stratime =DateBean.getDynamicTime(calcNum, reportDate, "0");
		String endtime =DateBean.getDynamicTime(calcNum, reportDate, "1");
		
		String sql = "select a.rpd_u2_water_id,to_char(a.report_time,'YYYY-MM-DD HH24:MI:SS')as report_time ,a.FIT_20301A,"+
						"a.FIT20301AZ,a.FIT_20301B,a.FIT20301BZ,a.FIT_20301C,a.FIT20301CZ,a.FIT_20301D,"+
						"a.FIT20301DZ,a.FIT_20301E,a.FIT20301EZ,a.FIT_20301F,a.fit20301fz " +
						"from  PC_RPD_U2_WATER_T  a where 1=1 ";
		sql += " and a.report_time between TO_DATE('"+stratime+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+endtime+"','YYYY-MM-DD HH24:MI:SS') order by a.report_time";
		Object[] u2oiltable = null;
		List<Object[]> u2oils = reactorDao.searchReactor(sql);
		String[][] dates = DateBean.getReportTime("time", reportDate);
		
		if(u2oils !=null && u2oils.size()>0){
		
			for (int i = 0; i < dates.length; i++) {
				String[] dateList = dates[i];
				obj = new JSONObject();
				 
//				 FIT_20301ASUMday.add(Double.parseDouble(FIT_20301A));
//				 FIT20301AZSUMday.add(Double.parseDouble(FIT20301AZ));
//				 FIT_20301BSUMday.add(Double.parseDouble(FIT_20301B)); 
//				 FIT20301BZSUMday.add(Double.parseDouble(FIT20301BZ));  
//				 FIT_20301CSUMday.add(Double.parseDouble(FIT_20301C));  
//				 FIT20301CZSUMday.add(Double.parseDouble(FIT20301CZ)); 
//				 FIT_20301DSUMday.add(Double.parseDouble(FIT_20301D));  
//				 FIT20301DZSUMday.add(Double.parseDouble(FIT20301DZ)); 
//				 FIT_20301ESUMday.add(Double.parseDouble(FIT_20301E));  
//				 FIT20301EZSUMday.add(Double.parseDouble(FIT20301EZ)); 
//				 FIT_20301FSUMday.add(Double.parseDouble(FIT_20301F)); 
//				fit20301fzSUMday.add(Double.parseDouble(fit20301fz));
				
				if (i == 2) {
					 JSONObject oobj =  (JSONObject) arr.get(5);
					
//					 oobj.get("FIT_20301A");
					 obj.put("RPD_U2_WATER_ID", "");
					 obj.put("REPORT_TIME", "班累积");
					 obj.put("FIT_20301A", "/");
					 if(u2oils.size()>8 && u2oils.get(8)[3] !=null){
						 obj.put("FIT20301AZ", CommonsUtil.getRegulation0(u2oils.get(8)[3], u2oils.get(4)[3]));	 
					 }else{
						 //obj.put("FIT20301AZ", CommonsUtil.getRegulation0(0, u2oils.get(4)[3]));
						 obj.put("FIT20301AZ", "");
					 }
					 obj.put("FIT_20301B",  "/");
					 if(u2oils.size()>8 && u2oils.get(8)[5] !=null){
						 obj.put("FIT20301BZ", CommonsUtil.getRegulation0(u2oils.get(8)[5], u2oils.get(4)[5]));
					 }else{
						 obj.put("FIT20301BZ", ""); 
					 }
					 obj.put("FIT_20301C",  "/");
					 if(u2oils.size()>8 && u2oils.get(8)[7] !=null){
						 obj.put("FIT20301CZ", CommonsUtil.getRegulation0(u2oils.get(8)[7], u2oils.get(4)[7]));
					 }else{
						 obj.put("FIT20301CZ", "");
					 }
					 obj.put("FIT_20301D",  "/");
					 if(u2oils.size()>8 && u2oils.get(8)[9] !=null){
						 obj.put("FIT20301DZ", CommonsUtil.getRegulation0(u2oils.get(8)[9], u2oils.get(4)[9]));
					 }else{
						 obj.put("FIT20301DZ", "");
					 }
					 obj.put("FIT_20301E",  "/");
					 if(u2oils.size()>8 && u2oils.get(8)[11] !=null){
						 obj.put("FIT20301EZ", CommonsUtil.getRegulation0(u2oils.get(8)[11], u2oils.get(4)[11]));
					 }else{ 
						 obj.put("FIT20301EZ", "");
					 }
					 obj.put("FIT_20301F",  "/");
					 if(u2oils.size()>8 && u2oils.get(8)[13] !=null){
						 obj.put("fit20301fz", CommonsUtil.getRegulation0(u2oils.get(8)[13], u2oils.get(4)[13]));
					 }else{
						 obj.put("fit20301fz", "");
					 	}
				}else {
					 FIT_20301A  =CommonsUtil.getBanSumData0(FIT_20301ASUM);
					 FIT20301AZ  =CommonsUtil.getBanSumData0(FIT20301AZSUM);
					 FIT_20301B  =CommonsUtil.getBanSumData0(FIT_20301BSUM);
					 FIT20301BZ  =CommonsUtil.getBanSumData0(FIT20301BZSUM);
					 FIT_20301C  =CommonsUtil.getBanSumData0(FIT_20301CSUM);
					 FIT20301CZ  =CommonsUtil.getBanSumData0(FIT20301CZSUM);
					 FIT_20301D  =CommonsUtil.getBanSumData0(FIT_20301DSUM);
					 FIT20301DZ  =CommonsUtil.getBanSumData0(FIT20301DZSUM);
					 FIT_20301E  =CommonsUtil.getBanSumData0(FIT_20301ESUM);
					 FIT20301EZ  =CommonsUtil.getBanSumData0(FIT20301EZSUM);
					 FIT_20301F  =CommonsUtil.getBanSumData0(FIT_20301FSUM);
					 fit20301fz =CommonsUtil.getBanSumData0(fit20301fzSUM);
					 obj.put("RPD_U2_WATER_ID", "");
					 obj.put("REPORT_TIME", "班累积");
					 obj.put("FIT_20301A", "/");
					 obj.put("FIT20301AZ", FIT20301AZ);
					 obj.put("FIT_20301B",  "/");
					 obj.put("FIT20301BZ", FIT20301BZ);
					 obj.put("FIT_20301C",  "/");
					 obj.put("FIT20301CZ", FIT20301CZ);
					 obj.put("FIT_20301D",  "/");
					 obj.put("FIT20301DZ", FIT20301DZ);
					 obj.put("FIT_20301E",  "/");
					 obj.put("FIT20301EZ", FIT20301EZ);
					 obj.put("FIT_20301F",  "/");
					 obj.put("fit20301fz", fit20301fz);
				}
				  arr.add(obj);
				  
					 FIT_20301ASUM  = new ArrayList<String>();
					 FIT20301AZSUM  = new ArrayList<String>();
					 FIT_20301BSUM = new ArrayList<String>();
					 FIT20301BZSUM  = new ArrayList<String>();
					 FIT_20301CSUM  = new ArrayList<String>();
					 FIT20301CZSUM = new ArrayList<String>();
					 FIT_20301DSUM  = new ArrayList<String>();
					 FIT20301DZSUM = new ArrayList<String>();
					 FIT_20301ESUM  = new ArrayList<String>();
					 FIT20301EZSUM = new ArrayList<String>();
					 FIT_20301FSUM = new ArrayList<String>();
					 fit20301fzSUM = new ArrayList<String>();
					 
					 for(String data:dateList){
							System.out.println(data);
							u2oiltable = null;
							dataflg = 0;
							for(Object[] u2oil:u2oils){
								obj = new JSONObject();
								if(data.toString().equals(u2oil[1].toString())){
									dataflg = 1;
									u2oiltable = u2oil;
								if(u2oil[2] != null && !"".equals(u2oil[2].toString())){
									FIT_20301ASUMday.add(u2oil[2].toString());
								}else{
									FIT_20301ASUMday.add("");
								}
								if(u2oil[3] != null && !"".equals(u2oil[3].toString())){
									FIT20301AZSUMday.add(u2oil[3].toString());
								}else{
									FIT20301AZSUMday.add("");
								}
								if(u2oil[4] != null && !"".equals(u2oil[4].toString())){
									FIT_20301BSUMday.add(u2oil[4].toString());
								}else{
									FIT_20301BSUMday.add("");
								}
								if(u2oil[5] != null && !"".equals(u2oil[5].toString())){
									FIT20301BZSUMday.add(u2oil[5].toString());
								}else{
									FIT20301BZSUMday.add("");
								}
								
								if(u2oil[6] != null && !"".equals(u2oil[6].toString())){
									FIT_20301CSUMday.add(u2oil[6].toString());
								}else{
									FIT_20301CSUMday.add("");
								}
								if(u2oil[7] != null && !"".equals(u2oil[7].toString())){
									FIT20301CZSUMday.add(u2oil[7].toString());
								}else{
									FIT20301CZSUMday.add("");
								}
								if(u2oil[8] != null && !"".equals(u2oil[8].toString())){
									FIT_20301DSUMday.add(u2oil[8].toString());
								}else{
									FIT_20301DSUMday.add("");
								}
								if(u2oil[9] != null && !"".equals(u2oil[9].toString())){
									FIT20301DZSUMday.add(u2oil[9].toString());
								}else{
									FIT20301DZSUMday.add("");
								}
								
								if(u2oil[10] != null && !"".equals(u2oil[10].toString())){
									FIT_20301ESUMday.add(u2oil[10].toString());
								}else{
									FIT_20301ESUMday.add("");
								}
								if(u2oil[11] != null && !"".equals(u2oil[11].toString())){
									FIT20301EZSUMday.add(u2oil[11].toString());
								}else{
									FIT_20301FSUMday.add("");
								}
								if(u2oil[12] != null && !"".equals(u2oil[12].toString())){
									FIT_20301FSUMday.add(u2oil[12].toString());
								}else{
									FIT_20301FSUMday.add("");
								}
								if(u2oil[13] != null && !"".equals(u2oil[13].toString())){
									fit20301fzSUMday.add(u2oil[13].toString());
								}else{
									fit20301fzSUMday.add("");
								}
								
							}
						}
					 if(dataflg == 1){
						 		
							obj.put("RPD_U2_WATER_ID", u2oiltable[0]);
							obj.put("REPORT_TIME", data.substring(11, 16));
							if(u2oiltable[2] !=null && !"".equals(u2oiltable[2]) && !u2oiltable[2].equals("undefined")){
								obj.put("FIT_20301A",CommonsUtil.getIntData(u2oiltable[2]));
								
								FIT_20301ASUM.add(u2oiltable[2].toString());
							}else{
								obj.put("FIT_20301A", "");
								FIT_20301ASUM.add("");
								}
							if(u2oiltable[3] !=null && !"".equals(u2oiltable[3]) && !u2oiltable[3].equals("undefined")){
								obj.put("FIT20301AZ", CommonsUtil.getIntData(u2oiltable[3]));
								
								FIT20301AZSUM.add(u2oiltable[3].toString());
							}else{
								obj.put("FIT20301AZ", "");
								FIT20301AZSUM.add("");
							}
							
							if(u2oiltable[4] !=null){
								obj.put("FIT_20301B", CommonsUtil.getIntData(u2oiltable[4]));
								FIT_20301BSUM.add(u2oiltable[4].toString());
							}else{
								obj.put("FIT_20301B", "");
								FIT_20301BSUM.add("");
							}
							if(u2oiltable[5] !=null){
								obj.put("FIT20301BZ", CommonsUtil.getIntData(u2oiltable[5]));
								FIT20301BZSUM.add(u2oiltable[5].toString());
								}else{
									obj.put("FIT20301BZ","");
									FIT20301BZSUM.add("");
								}
							if(u2oiltable[6] !=null){
								obj.put("FIT_20301C", CommonsUtil.getIntData(u2oiltable[6]));
								FIT_20301CSUM.add(u2oiltable[6].toString());
							}else{
								obj.put("FIT_20301C","");
								FIT_20301CSUM.add("");
							}
							if(u2oiltable[7] !=null){
								obj.put("FIT20301CZ", CommonsUtil.getIntData(u2oiltable[7]));
								FIT20301CZSUM.add(u2oiltable[7].toString());
							}else{
								obj.put("FIT20301CZ", "");
								FIT20301CZSUM.add("");
							}
							if(u2oiltable[8] !=null){
								obj.put("FIT_20301D", CommonsUtil.getIntData(u2oiltable[8]));
								FIT_20301DSUM.add(u2oiltable[8].toString());
							}else{
								obj.put("FIT_20301D", "");
								FIT_20301DSUM.add("");
							}
							if(u2oiltable[9] !=null){
							obj.put("FIT20301DZ", CommonsUtil.getIntData(u2oiltable[9]));
							FIT20301DZSUM.add(u2oiltable[9].toString());
							}else{
								obj.put("FIT20301DZ", "");
								FIT20301DZSUM.add("");
							}
							if(u2oiltable[10] !=null){
							obj.put("FIT_20301E", CommonsUtil.getIntData(u2oiltable[10]));
							FIT_20301ESUM.add(u2oiltable[10].toString());
							}else{
								obj.put("FIT_20301E", "");
								FIT_20301ESUM.add("");
							}
							if(u2oiltable[11] !=null){
							obj.put("FIT20301EZ", CommonsUtil.getIntData(u2oiltable[11]));
							FIT20301EZSUM.add(u2oiltable[11].toString());
							}else{
								obj.put("FIT20301EZ", "");
								FIT20301EZSUM.add("");
							}
							if(u2oiltable[12] !=null && !"".equals(u2oiltable[12]) && !u2oiltable[12].equals("undefined")){
								obj.put("FIT_20301F", CommonsUtil.getIntData(u2oiltable[12]));
								FIT_20301FSUM.add(u2oiltable[12].toString());
							}else{
								obj.put("FIT_20301F", "");
								FIT_20301FSUM.add("");
							}
							if( u2oiltable[13] !=null && !"".equals( u2oiltable[13]) && ! u2oiltable[13].equals("undefined")){
								obj.put("fit20301fz", CommonsUtil.getIntData(u2oiltable[13]));
								fit20301fzSUM.add(u2oiltable[13].toString());
								}else{
									obj.put("fit20301fz", "");
									fit20301fzSUM.add("");
								}
							
					 	}else{
						 obj.put("RPD_U2_WATER_ID", "");
							obj.put("REPORT_TIME", data.substring(11, 16));
							
							obj.put("FIT_20301A", "");
							obj.put("FIT20301AZ", "");
							obj.put("FIT_20301B", "");
							obj.put("FIT20301BZ", "");
							obj.put("FIT_20301C", "");
							obj.put("FIT20301CZ", "");
							obj.put("FIT_20301D", "");
							obj.put("FIT20301DZ", "");
							obj.put("FIT_20301E", "");
							obj.put("FIT20301EZ", "");
							obj.put("FIT_20301F", "");
							obj.put("fit20301fz", "");
							
							
							FIT_20301ASUM.add("");
							FIT20301AZSUM.add("");
							FIT_20301BSUM.add("");
							FIT20301BZSUM.add("");
							FIT_20301CSUM.add("");
							FIT20301CZSUM.add("");
							FIT_20301DSUM.add("");
							FIT20301DZSUM.add("");
							FIT_20301ESUM.add("");
							FIT20301EZSUM.add("");
							FIT_20301FSUM.add("");
							fit20301fzSUM.add("");
							
							
							FIT_20301ASUMday.add("");
							FIT20301AZSUMday.add("");
							FIT_20301BSUMday.add("");
							FIT20301BZSUMday.add("");
							FIT_20301CSUMday.add("");
							FIT20301CZSUMday.add("");
							FIT_20301DSUMday.add("");
							FIT20301DZSUMday.add("");
							FIT_20301ESUMday.add("");
							FIT20301EZSUMday.add("");
							FIT_20301FSUMday.add("");
							fit20301fzSUMday.add("");
					 }
					 arr.add(obj);
				 }
			}
			//第三个班累计值
//			 FIT20301AZ  =CommonsUtil.getSumData00(FIT20301AZSUMday);
//			 FIT20301BZ  =CommonsUtil.getSumData00(FIT20301BZSUMday);
//			 FIT20301CZ  =CommonsUtil.getSumData00(FIT20301CZSUMday);
//			 FIT20301DZ  =CommonsUtil.getSumData00(FIT20301DZSUMday);
//			 FIT20301EZ  =CommonsUtil.getSumData00(FIT20301EZSUMday);
//			 fit20301fz =CommonsUtil.getSumData00(fit20301fzSUMday);

			 obj.put("RPD_U2_WATER_ID", "");
			 obj.put("REPORT_TIME", "班累积");
			 obj.put("FIT_20301A", "/");
			 if(u2oils.size()==13 && u2oils.get(12)[3] !=null){
				 obj.put("FIT20301AZ", CommonsUtil.getRegulation0(u2oils.get(12)[3], u2oils.get(8)[3]));	 
			 }else{
				 //obj.put("FIT20301AZ", CommonsUtil.getRegulation0(0, u2oils.get(4)[3]));
				 obj.put("FIT20301AZ", "");
			 }
			 obj.put("FIT_20301B",  "/");
			 if(u2oils.size()==13 && u2oils.get(12)[5] !=null){
				 obj.put("FIT20301BZ", CommonsUtil.getRegulation0(u2oils.get(12)[5], u2oils.get(8)[5]));
			 }else{
				 obj.put("FIT20301BZ", ""); 
			 }
			 obj.put("FIT_20301C",  "/");
			 if(u2oils.size()==13 && u2oils.get(12)[7] !=null){
				 obj.put("FIT20301CZ", CommonsUtil.getRegulation0(u2oils.get(12)[7], u2oils.get(8)[7]));
			 }else{
				 obj.put("FIT20301CZ", "");
			 }
			 obj.put("FIT_20301D",  "/");
			 if(u2oils.size()==13 && u2oils.get(12)[9] !=null){
				 obj.put("FIT20301DZ", CommonsUtil.getRegulation0(u2oils.get(12)[9], u2oils.get(8)[9]));
			 }else{
				 obj.put("FIT20301DZ", "");
			 }
			 obj.put("FIT_20301E",  "/");
			 if(u2oils.size()==13 && u2oils.get(12)[11] !=null){
				 obj.put("FIT20301EZ", CommonsUtil.getRegulation0(u2oils.get(12)[11], u2oils.get(8)[11]));
			 }else{ 
				 obj.put("FIT20301EZ", "");
			 }
			 obj.put("FIT_20301F",  "/");
			 if(u2oils.size()==13 && u2oils.get(12)[13] !=null){
				 obj.put("fit20301fz", CommonsUtil.getRegulation0(u2oils.get(12)[13], u2oils.get(8)[13]));
			 }else{
				 obj.put("fit20301fz", "");
			 	}
		
			 arr.add(obj);
			 
		}else{
			for(String[] dateList:dates){
				obj = new JSONObject();
				for(String data:dateList){
					obj = new JSONObject();
					 obj.put("RPD_U2_WATER_ID", "");
						obj.put("REPORT_TIME", data.substring(11, 16));
						
						obj.put("FIT_20301A", "");
						obj.put("FIT20301AZ", "");
						obj.put("FIT_20301B", "");
						obj.put("FIT20301BZ", "");
						obj.put("FIT_20301C", "");
						obj.put("FIT20301CZ", "");
						obj.put("FIT_20301D", "");
						obj.put("FIT20301DZ", "");
						obj.put("FIT_20301E", "");
						obj.put("FIT20301EZ", "");
						obj.put("FIT_20301F", "");
						obj.put("fit20301fz", "");
					arr.add(obj);
				}
				 obj.put("RPD_U2_WATER_ID", "");
				 obj.put("REPORT_TIME", "班累积");
				 
				 obj.put("FIT_20301A", "/");
				 obj.put("FIT20301AZ", "/");
				 obj.put("FIT_20301B", "/");
				 obj.put("FIT20301BZ", "/");
				 obj.put("FIT_20301C", "/");
				 obj.put("FIT20301CZ", "/");
				 obj.put("FIT_20301D", "/");
				 obj.put("FIT20301DZ", "/");
				 obj.put("FIT_20301E", "/");
				 obj.put("FIT20301EZ", "/");
				 obj.put("FIT_20301F", "/");
				 obj.put("fit20301fz", "/");
				 
				arr.add(obj);
			}
		
		}
		if(u2oils !=null && u2oils.size()>0){
		arr.remove(0);
		obj = new JSONObject();
		obj.put("RPD_U2_WATER_ID", "");
		obj.put("REPORT_TIME", "合计");
		
//		FIT20301AZSUMday.remove(0);
//		FIT20301BZSUMday.remove(0);
//		FIT20301BZSUMday.remove(0);
//		FIT20301BZSUMday.remove(0);
//		FIT20301EZSUMday.remove(0);
//		fit20301fzSUMday.remove(0);
		 //FIT_20301A  =CommonsUtil.getSumData(FIT_20301ASUM);
		 obj.put("FIT_20301A", "/");
		 obj.put("FIT20301AZ",CommonsUtil.getSumData0(FIT20301AZSUMday));
		 obj.put("FIT_20301B", "/");
		 obj.put("FIT20301BZ",CommonsUtil.getSumData0(FIT20301BZSUMday));
		 obj.put("FIT_20301C", "/");
		 obj.put("FIT20301CZ", CommonsUtil.getSumData0(FIT20301CZSUMday));
		 obj.put("FIT_20301D", "/");
		 obj.put("FIT20301DZ"  ,CommonsUtil.getSumData0(FIT20301DZSUMday));
		 obj.put("FIT_20301E", "/");
		 obj.put("FIT20301EZ",  CommonsUtil.getSumData0(FIT20301EZSUMday));
		 obj.put("FIT_20301F", "/");
		 obj.put("fit20301fz", CommonsUtil.getSumData0(fit20301fzSUMday));

		arr.add(obj);
		}else{
			 obj.put("RPD_U2_WATER_ID", "");
			 obj.put("REPORT_TIME", "合计");
			 
			 obj.put("FIT_20301A", "/");
			 obj.put("FIT20301AZ", "");
			 obj.put("FIT_20301B", "/");
			 obj.put("FIT20301BZ", "");
			 obj.put("FIT_20301C", "/");
			 obj.put("FIT20301CZ", "");
			 obj.put("FIT_20301D", "/");
			 obj.put("FIT20301DZ", "");
			 obj.put("FIT_20301E", "/");
			 obj.put("FIT20301EZ", "");
			 obj.put("FIT_20301F", "/");
			 obj.put("fit20301fz", "");
			arr.add(obj);
		}
		return arr;
	}

	@Override
	public List<Object[]> searchReacEXcep(String rq, String fields)throws Exception {

		String timeCalssql = "select tp.parameter_value from PC_CD_TRIGGERED_PROCESS_T tp where tp.parameter_name='union_station_rpd_start_time'";
		String calcNum = "-16";
		List<Object[]> dataSet = reactorDao.searchReactor(timeCalssql);
		if(dataSet != null && dataSet.size()>0){
			calcNum = dataSet.get(0) + "";
		}
		String stratime =DateBean.getDynamicTime(calcNum, rq, "0");
		String endtime =DateBean.getDynamicTime(calcNum, rq, "1");
		String sql = "select " + fields + " from PC_RPD_U2_WATER_T u where u.report_time between TO_DATE('"+stratime+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+endtime+"','YYYY-MM-DD HH24:MI:SS') order by u.report_time";
		List<Object[]> yyList = reactorDao.searchReactor(sql);
		return yyList;
	
	}

}
