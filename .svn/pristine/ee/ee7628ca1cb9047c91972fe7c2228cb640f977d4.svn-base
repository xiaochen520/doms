package com.echo.service.impl;

 
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.dao.YYCLJYRPDDao;
import com.echo.service.YYCLJYRPDService;
import com.echo.util.CommonsUtil;
import com.echo.util.DateBean;

public class YYCLJYRPDServiceImpl implements YYCLJYRPDService{
	private YYCLJYRPDDao yYCLJYRPDDao;
	public void setyYCLJYRPDDao(YYCLJYRPDDao yYCLJYRPDDao) {
		this.yYCLJYRPDDao = yYCLJYRPDDao;
	}
	@Override
	public JSONArray searchU2OIL(String rq) throws Exception {
		JSONObject obj = new JSONObject();
		JSONArray arr = new JSONArray();

		String FIT10501ZSUM1 = "";
		String FIT10502ZSUM1 = "";
		String FIT10503ZSUM1 = "";
		String FIT10504ZSUM1 = "";
//		//-----------------合计
//		double FIT10501ZSUMday =0.00;
//		double FIT10502ZSUMday =0.00;
//		double FIT10503ZSUMday =0.00;
//		double FIT10504ZSUMday =0.00;
		
		List<String> FIT10501ZSUM = new ArrayList<String>();
		List<String> FIT10502ZSUM = new ArrayList<String>();
		List<String> FIT10503ZSUM = new ArrayList<String>();
		List<String> FIT10504ZSUM = new ArrayList<String>();
		//-----------------合计
		List<String> FIT_10501ZSUMday = new ArrayList<String>();
		List<String> FIT_10502ZSUMday = new ArrayList<String>();
		List<String> FIT_10503ZSUMday = new ArrayList<String>();
		List<String> FIT_10504ZSUMday = new ArrayList<String>();
		
		List<String> FIT10501ZSUMday = new ArrayList<String>();
		List<String> FIT10502ZSUMday = new ArrayList<String>();
		List<String> FIT10503ZSUMday = new ArrayList<String>();
		List<String> FIT10504ZSUMday = new ArrayList<String>();

		int dataflg = 0;  //数据存在标识  0：不存在， 1：存在
		String timeCalssql = "select tp.parameter_value from PC_CD_TRIGGERED_PROCESS_T tp where tp.parameter_name='union_station_rpd_start_time'";
		String calcNum = "-16";
		List<Object[]> dataSet = yYCLJYRPDDao.searchU2OIL(timeCalssql);
		if(dataSet != null && dataSet.size()>0){
			calcNum = dataSet.get(0) + "";
		}
		
		String stratime =DateBean.getDynamicTime(calcNum, rq, "0");
		String endtime =DateBean.getDynamicTime(calcNum, rq, "1");
		String sql = "select u.rpd_u2_oil_id,to_char(u.report_time,'YYYY-MM-DD HH24:MI:SS')as report_time , "+
					" u.FIT_10501,u.FIT_10502,u.FIT_10503,u.FIT_10504,u.FIT10501Z,u.FIT10502Z,u.FIT10503Z,u.FIT10504Z"+
					" from PC_RPD_U2_OIL_T u where 1=1 ";
			sql += " and u.report_time between TO_DATE('"+stratime+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+endtime+"','YYYY-MM-DD HH24:MI:SS') order by u.report_time";
		Object[] u2oiltable = null;
		List<Object[]> u2oils = yYCLJYRPDDao.searchU2OIL(sql);	
		String[][] dates = DateBean.getReportTime("time", rq);
		if(u2oils != null && u2oils.size()>0){
		
//			for(String[] dateList:dates){
//				obj = new JSONObject();

			for (int i = 0; i < dates.length; i++) {
				String[] dateList = dates[i];
				obj = new JSONObject();
				
			
				
				
//				FIT10501ZSUMday.add(Double.parseDouble(FIT10501ZSUM1));
//				FIT10502ZSUMday.add(Double.parseDouble(FIT10502ZSUM1));
//				FIT10503ZSUMday.add(Double.parseDouble(FIT10503ZSUM1));
//				FIT10504ZSUMday.add(Double.parseDouble(FIT10504ZSUM1));

				if (i == 2) {
				 JSONObject oobj =  (JSONObject) arr.get(5);

					obj.put("RPD_U2_OIL_ID", "");
					obj.put("REPORT_TIME", "班累积");
					obj.put("FIT_10501", "/");
					obj.put("FIT_10502", "/");
					obj.put("FIT_10503", "/");
					obj.put("FIT_10504", "/");
					
					obj.put("FIT_ZXYG1", "/");
					obj.put("FIT_ZXYG2", "/");
					obj.put("FIT_FXYG1", "/");
					obj.put("FIT_FXYG2", "/");
//					if(u2oils.size()>8 && u2oils.get(8) !=null){
//					obj.put("FIT10501Z",   CommonsUtil.getRegulation0(u2oils.get(8)[6], u2oils.get(4)[6]));
//					obj.put("FIT10502Z",   CommonsUtil.getRegulation0(u2oils.get(8)[7], u2oils.get(4)[7]));
//					obj.put("FIT10503Z",   CommonsUtil.getRegulation0(u2oils.get(8)[8], u2oils.get(4)[8]));
//					obj.put("FIT10504Z",   CommonsUtil.getRegulation0(u2oils.get(8)[9], u2oils.get(4)[9]));
//					}else{
//						obj.put("FIT10501Z", "");
//						obj.put("FIT10502Z", "");
//						obj.put("FIT10503Z", "");
//						obj.put("FIT10504Z", "");
//					}
					if(u2oils.size()>8 && u2oils.get(8)[6] !=null && u2oils.get(4)[6] !=null){
						obj.put("FIT10501Z",   CommonsUtil.getRegulation0(u2oils.get(8)[6], u2oils.get(4)[6]));
					}else{
						obj.put("FIT10501Z", "");
					}
					if(u2oils.size()>8 && u2oils.get(8)[7] !=null && u2oils.get(4)[7] !=null){
						obj.put("FIT10502Z",   CommonsUtil.getRegulation0(u2oils.get(8)[7], u2oils.get(4)[7]));
					}else{
						obj.put("FIT10502Z", "");
					}	
					if(u2oils.size()>8 && u2oils.get(8)[8] !=null && u2oils.get(4)[8] !=null){
						obj.put("FIT10503Z",   CommonsUtil.getRegulation0(u2oils.get(8)[8], u2oils.get(4)[8]));
					}else{
						obj.put("FIT10503Z", "");
					}
					if(u2oils.size()>8 && u2oils.get(8)[9] !=null && u2oils.get(4)[9] !=null){
						obj.put("FIT10504Z",   CommonsUtil.getRegulation0(u2oils.get(8)[9], u2oils.get(4)[9]));
					}else{
						obj.put("FIT10504Z", "");
					}
				}else{
					FIT10501ZSUM1 = CommonsUtil.getBanSumData0(FIT10501ZSUM);
					FIT10502ZSUM1 = CommonsUtil.getBanSumData0(FIT10502ZSUM);
					FIT10503ZSUM1 = CommonsUtil.getBanSumData0(FIT10503ZSUM);
					FIT10504ZSUM1 = CommonsUtil.getBanSumData0(FIT10504ZSUM);
					
					obj.put("RPD_U2_OIL_ID", "");
					obj.put("REPORT_TIME", "班累积");
					obj.put("FIT_10501", "/");
					obj.put("FIT_10502", "/");
					obj.put("FIT_10503", "/");
					obj.put("FIT_10504", "/");
					
					obj.put("FIT_ZXYG1", "/");
					obj.put("FIT_ZXYG2", "/");
					obj.put("FIT_FXYG1", "/");
					obj.put("FIT_FXYG2", "/");
					obj.put("FIT10501Z", FIT10501ZSUM1);
					obj.put("FIT10502Z", FIT10502ZSUM1);
					obj.put("FIT10503Z", FIT10503ZSUM1);
					obj.put("FIT10504Z", FIT10504ZSUM1);
				}
				arr.add(obj);
				 FIT10501ZSUM = new ArrayList<String>();
				 FIT10502ZSUM = new ArrayList<String>();
				 FIT10503ZSUM = new ArrayList<String>();
				 FIT10504ZSUM = new ArrayList<String>();
				
				for(String data:dateList){
					System.out.println(data);
					u2oiltable = null;
					dataflg = 0;
					for(Object[] u2oil:u2oils){
						obj = new JSONObject();
						
						if(data.toString().equals(u2oil[1].toString())){
							dataflg = 1;
							u2oiltable = u2oil;
							//添加班累积值
							if(u2oil[6] != null && !"".equals(u2oil[6].toString())){
//								FIT10501ZSUM += Double.parseDouble(u2oil[6].toString());
								FIT10501ZSUM.add(u2oil[6].toString());
							}else{
								FIT10501ZSUM.add("");
							}
							if(u2oil[7] != null && !"".equals(u2oil[7].toString())){
//								FIT10502ZSUM += u2oil[7].toString());
								FIT10502ZSUM.add(u2oil[7].toString());
							}else{
								FIT10502ZSUM.add("");
							}
							if(u2oil[8] != null && !"".equals(u2oil[8].toString())){
//								FIT10503ZSUM += u2oil[8].toString());
								FIT10503ZSUM.add(u2oil[8].toString());
							}else{
								FIT10503ZSUM.add("");
							}
							if(u2oil[9] != null && !"".equals(u2oil[9].toString())){
//								FIT10504ZSUM += u2oil[9].toString());
								FIT10504ZSUM.add(u2oil[9].toString());
							}else{
								FIT10504ZSUM.add("");
							}
						}
						
					}
					if(dataflg == 1){
						obj.put("RPD_U2_OIL_ID", u2oiltable[0]);
						obj.put("REPORT_TIME", data.substring(11, 16));
						if(u2oiltable[2]!=null){
							obj.put("FIT_10501", CommonsUtil.getNotOneData(u2oiltable[2]));
							FIT_10501ZSUMday.add(u2oiltable[2].toString());
						}else{
							obj.put("FIT_10501", "");
							FIT_10501ZSUMday.add("");
						}
						if(u2oiltable[3]!=null){
							obj.put("FIT_10502", CommonsUtil.getNotOneData(u2oiltable[3]));
							FIT_10502ZSUMday.add(u2oiltable[3].toString());
						}else{
							obj.put("FIT_10502", "");
							FIT_10502ZSUMday.add("");
						}
						
						if(u2oiltable[4]!=null){
							obj.put("FIT_10503", CommonsUtil.getNotOneData(u2oiltable[4]));
							FIT_10503ZSUMday.add(u2oiltable[4].toString());
						}else{
							obj.put("FIT_10503", "");
							FIT_10503ZSUMday.add("");
						}
						
						if(u2oiltable[5]!=null){
							obj.put("FIT_10504", CommonsUtil.getNotOneData(u2oiltable[5]));
							FIT_10504ZSUMday.add(u2oiltable[5].toString());
						}else{
							obj.put("FIT_10504", "");
							FIT_10504ZSUMday.add("");
						}
						
						obj.put("FIT_ZXYG1", "");
						obj.put("FIT_ZXYG2", "");
						obj.put("FIT_FXYG1", "");
						obj.put("FIT_FXYG2", "");
						if(u2oiltable[6]!=null){
							obj.put("FIT10501Z", CommonsUtil.getIntData(u2oiltable[6]));
							FIT10501ZSUMday.add(u2oiltable[6].toString());
						}else{
							obj.put("FIT10501Z", "");
							FIT10501ZSUMday.add("");
						}
						if(u2oiltable[7]!=null){
							obj.put("FIT10502Z", CommonsUtil.getIntData(u2oiltable[7]));
							FIT10502ZSUMday.add(u2oiltable[7].toString());
						}else{
							obj.put("FIT10502Z", "");
							FIT10502ZSUMday.add("");
						}
						
						if(u2oiltable[8]!=null){
							obj.put("FIT10503Z", CommonsUtil.getIntData(u2oiltable[8]));
							FIT10503ZSUMday.add(u2oiltable[8].toString());
						}else{
							obj.put("FIT10503Z", "");
							FIT10503ZSUMday.add("");
						}
						
						if(u2oiltable[9]!=null){
							obj.put("FIT10504Z", CommonsUtil.getIntData(u2oiltable[9]));
							FIT10504ZSUMday.add(u2oiltable[9].toString());
						}else{
							obj.put("FIT10504Z", "");
							FIT10504ZSUMday.add("");
						}
					}else{
						obj.put("RPD_U2_OIL_ID", "");
						obj.put("REPORT_TIME", data.substring(11, 16));
						obj.put("FIT_10501", "");
						obj.put("FIT_10502", "");
						obj.put("FIT_10503", "");
						obj.put("FIT_10504", "");
						
						obj.put("FIT_ZXYG1", "");
						obj.put("FIT_ZXYG2", "");
						obj.put("FIT_FXYG1", "");
						obj.put("FIT_FXYG2", "");
						obj.put("FIT10501Z", "");
						obj.put("FIT10502Z", "");
						obj.put("FIT10503Z", "");
						obj.put("FIT10504Z", "");
						
						FIT_10501ZSUMday.add("");
						FIT_10502ZSUMday.add("");
						FIT_10503ZSUMday.add("");
						FIT_10504ZSUMday.add("");
						
						FIT10501ZSUMday.add("");
						FIT10502ZSUMday.add("");
						FIT10503ZSUMday.add("");
						FIT10504ZSUMday.add("");
						
						FIT10501ZSUM.add("");
						FIT10502ZSUM.add("");
						FIT10503ZSUM.add("");
						FIT10504ZSUM.add("");
					}
					arr.add(obj);
				}
			}
//			FIT10501ZSUMday +=FIT10501ZSUM;
//			FIT10502ZSUMday +=FIT10502ZSUM;
//			FIT10503ZSUMday +=FIT10503ZSUM;
//			FIT10504ZSUMday +=FIT10504ZSUM;
//			obj.put("RPD_U2_OIL_ID", "");
//			obj.put("REPORT_TIME", "班累积");
//			obj.put("FIT_10501", "/");
//			obj.put("FIT_10502", "/");
//			obj.put("FIT_10503", "/");
//			obj.put("FIT_10504", "/");
//			
//			obj.put("FIT_ZXYG1", "/");
//			obj.put("FIT_ZXYG2", "/");
//			obj.put("FIT_FXYG1", "/");
//			obj.put("FIT_FXYG2", "/");
//
//			FIT10501ZSUM1 = CommonsUtil.getSumData00(FIT10501ZSUMday);
//			FIT10502ZSUM1 = CommonsUtil.getSumData00(FIT10502ZSUMday);
//			FIT10503ZSUM1 = CommonsUtil.getSumData00(FIT10503ZSUMday);
//			FIT10504ZSUM1 = CommonsUtil.getSumData00(FIT10504ZSUMday);
//			
//			obj.put("FIT10501Z", FIT10501ZSUM1);
//			obj.put("FIT10502Z", FIT10502ZSUM1);
//			obj.put("FIT10503Z", FIT10503ZSUM1);
//			obj.put("FIT10504Z", FIT10504ZSUM1);

			obj.put("RPD_U2_OIL_ID", "");
			obj.put("REPORT_TIME", "班累积");
			obj.put("FIT_10501", "/");
			obj.put("FIT_10502", "/");
			obj.put("FIT_10503", "/");
			obj.put("FIT_10504", "/");
			
			obj.put("FIT_ZXYG1", "/");
			obj.put("FIT_ZXYG2", "/");
			obj.put("FIT_FXYG1", "/");
			obj.put("FIT_FXYG2", "/");
			if(u2oils.size()==13 && u2oils.get(12)[6] !=null){
				obj.put("FIT10501Z",   CommonsUtil.getRegulation0(u2oils.get(8)[6], u2oils.get(4)[6]));
			}else{
				obj.put("FIT10501Z", "");
			}
			if(u2oils.size()==13 && u2oils.get(12)[7] !=null){
				obj.put("FIT10502Z",   CommonsUtil.getRegulation0(u2oils.get(8)[7], u2oils.get(4)[7]));
			}else{
				obj.put("FIT10502Z", "");
			}
			if(u2oils.size()==13 && u2oils.get(12)[8] !=null){
				obj.put("FIT10503Z",   CommonsUtil.getRegulation0(u2oils.get(8)[8], u2oils.get(4)[8]));
			}else{
				obj.put("FIT10503Z", "");
				}
			if(u2oils.size()==13 && u2oils.get(12)[9] !=null){
				obj.put("FIT10504Z",   CommonsUtil.getRegulation0(u2oils.get(8)[9], u2oils.get(4)[9]));
			}else{
				obj.put("FIT10504Z", "");
				}
			arr.add(obj);
		}else{
			for(String[] dateList:dates){
				obj = new JSONObject();
				for(String data:dateList){
					System.out.println(data);
					obj = new JSONObject();
					obj.put("RPD_U2_OIL_ID", "");
					obj.put("REPORT_TIME", data.substring(11, 16));
					obj.put("FIT_10501", "");
					obj.put("FIT_10502", "");
					obj.put("FIT_10503", "");
					obj.put("FIT_10504", "");
					
					obj.put("FIT_ZXYG1", "");
					obj.put("FIT_ZXYG2", "");
					obj.put("FIT_FXYG1", "");
					obj.put("FIT_FXYG2", "");
					obj.put("FIT10501Z", "");
					obj.put("FIT10502Z", "");
					obj.put("FIT10503Z", "");
					obj.put("FIT10504Z", "");
					arr.add(obj);
				}
				obj.put("RPD_U2_OIL_ID", "");
				obj.put("REPORT_TIME", "班累积");
				obj.put("FIT_10501", "/");
				obj.put("FIT_10502", "/");
				obj.put("FIT_10503", "/");
				obj.put("FIT_10504", "/");
				
				obj.put("FIT_ZXYG1", "/");
				obj.put("FIT_ZXYG2", "/");
				obj.put("FIT_FXYG1", "/");
				obj.put("FIT_FXYG2", "/");

				
				
				obj.put("FIT10501Z", "");
				obj.put("FIT10502Z", "");
				obj.put("FIT10503Z", "");
				obj.put("FIT10504Z", "");
				
				
				arr.add(obj);
			}
		}
		
		
		if(u2oils != null && u2oils.size()>0){
			obj = new JSONObject();
			arr.remove(0);
		
		obj.put("RPD_U2_OIL_ID", "");
		obj.put("REPORT_TIME", "合计");
		obj.put("FIT_10501", "/");
		obj.put("FIT_10502", "/");
		obj.put("FIT_10503", "/");
		obj.put("FIT_10504", "/");
		
		obj.put("FIT_ZXYG1", "/");
		obj.put("FIT_ZXYG2", "/");
		obj.put("FIT_FXYG1", "/");
		obj.put("FIT_FXYG2", "/");
		
//		FIT10501ZSUMday.remove(0);
//		FIT10502ZSUMday.remove(0);
//		FIT10503ZSUMday.remove(0);
//		FIT10504ZSUMday.remove(0);
		
		obj.put("FIT10501Z", CommonsUtil.getSumData0(FIT10501ZSUMday));
		obj.put("FIT10502Z", CommonsUtil.getSumData0(FIT10502ZSUMday));
		obj.put("FIT10503Z", CommonsUtil.getSumData0(FIT10503ZSUMday));
		obj.put("FIT10504Z", CommonsUtil.getSumData0(FIT10504ZSUMday));
		
		arr.add(obj);
		}else{
			obj = new JSONObject();
			obj.put("RPD_U2_OIL_ID", "");
			obj.put("REPORT_TIME", "合计");
			obj.put("FIT_10501", "/");
			obj.put("FIT_10502", "/");
			obj.put("FIT_10503", "/");
			obj.put("FIT_10504", "/");
			
			obj.put("FIT_ZXYG1", "/");
			obj.put("FIT_ZXYG2", "/");
			obj.put("FIT_FXYG1", "/");
			obj.put("FIT_FXYG2", "/");
			
			obj.put("FIT10501Z", "");
			obj.put("FIT10502Z", "");
			obj.put("FIT10503Z", "");
			obj.put("FIT10504Z", "");
			arr.add(obj);
		}
		return arr;
	}

	public List<Object[]> searchU2OIL(String rq,String fields) throws Exception {
		String timeCalssql = "select tp.parameter_value from PC_CD_TRIGGERED_PROCESS_T tp where tp.parameter_name='union_station_rpd_start_time'";
		String calcNum = "-16";
		List<Object[]> dataSet = yYCLJYRPDDao.searchU2OIL(timeCalssql);
		if(dataSet != null && dataSet.size()>0){
			calcNum = dataSet.get(0) + "";
		}
		String stratime =DateBean.getDynamicTime(calcNum, rq, "0");
		String endtime =DateBean.getDynamicTime(calcNum, rq, "1");
		String sql = "select " + fields + " from PC_RPD_U2_OIL_T u where u.report_time between TO_DATE('"+stratime+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+endtime+"','YYYY-MM-DD HH24:MI:SS') order by u.report_time";
		List<Object[]> yyList = yYCLJYRPDDao.searchU2OIL(sql);
		return yyList;
	}

}
