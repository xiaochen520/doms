package com.echo.service.impl;

 
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.dao.XYWSCLDao;
import com.echo.dto.PcRpdUThinWaterAutoT;
import com.echo.dto.PcRpdUThinWaterGeneralT;
import com.echo.service.XYWSCLService;
import com.echo.util.CommonsUtil;
import com.echo.util.DateBean;

public class XYWSCLServiceImpl implements XYWSCLService{
	private XYWSCLDao xYWSCLDao;

	public void setxYWSCLDao(XYWSCLDao xYWSCLDao) {
		this.xYWSCLDao = xYWSCLDao;
	}

	@Override
	public JSONObject searchXYWSDatas(String rq) throws Exception {
		String beforetime = DateBean.getBeforeDAYTime(rq);
		
		String timeCalssql = "select tp.parameter_value from PC_CD_TRIGGERED_PROCESS_T tp where tp.parameter_name='union_station_rpd_start_time'";
		String calcNum = "-16";
		List<Object[]> dateNum = xYWSCLDao.searchCalcNum(timeCalssql);
		if(dateNum != null && dateNum.size()>0){
			calcNum = dateNum.get(0) + "";
		}
		
		String stratime =DateBean.getDynamicTime(calcNum, rq, "0");
		String endtime =DateBean.getDynamicTime(calcNum, rq, "1");
		
		//水源井		
		String sql1 = "select SYJ1SL,SYJ2SL,SYJ3SL,SYJ1SL0,SYJ2SL0,SYJ3SL0 from (select  SYJ1SL as SYJ1SL0,SYJ2SL as SYJ2SL0, SYJ3SL as SYJ3SL0 from PC_RPD_U_THIN_WATER_GENERAL_T where BBRQ=TO_DATE('"+beforetime+"','YYYY-MM-DD')),PC_RPD_U_THIN_WATER_GENERAL_T where BBRQ=TO_DATE('"+rq+"','YYYY-MM-DD') ";
		List<Object[]> syData = xYWSCLDao.searchXYWSDatas(sql1);
		
		//一、大罐液位
		String sql = "select L1_1_TCG,LI_2_TCG,LI_1_HCG,LI_2_HCG,LI_1_JHG,LI_2_JHG,LI_1_WSC,LI_2_WSC,LI_WYG,LT_XFYW,";    
		
		//二、流量数据
		sql +="FI_YYCLZLS,YQLSXFWLJ,FI_YTZS,GLQCSXFWLJ,FI_JHSO1,WSBCKXFWLJ,";

		//三、反应器
		sql +="FI_1_FYJ,YJ1XFWLJ, FI_1_FYQ,FYQ1JSXFWLJ, FI_2_YJO1,FYQ1YL2XFWLJ, FI_3_FYJO1,FYQ1YJ3XFWLJ, FI_2_FYQ,FYQ2JSXFWLJ," +
			" FI_2_YJO2,FYQ2YL2XFWLJ, FI_3_FYJO2,FYQ2YJ3XFWLJ, FI_3_FYQ,FYQ3JSXFWLJ," +
			"FI_2_YJO3,FYQ3YL2XFWLJ, FI_3_FYJO3,FYQ3YJ3XFWLJ,";
		
		//日期 ，ID
		sql +=" to_char(BBSJ,'YYYY-MM-DD HH24:MI:SS') as BBSJ,RPD_U_THIN_WATER_AUTO_ID from PC_RPD_U_THIN_WATER_AUTO_T  where 1=1 ";
		
		sql += " and BBSJ between TO_DATE('"+stratime+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+endtime+"','YYYY-MM-DD HH24:MI:SS') order by BBSJ ";
		JSONObject obj = new JSONObject();
		
		List<Object[]> dataSet = xYWSCLDao.searchXYWSDatas(sql);
		
		obj = getTableDataJson(syData,dataSet, rq);
		return obj;
	}
	
	public JSONObject getTableDataJson(List<Object[]> syData,List<Object[]> dataSet,String reportDate){
		
		JSONObject obj = new JSONObject();
		JSONObject obj2 = new JSONObject();
		JSONArray firstArr = new JSONArray(); 
		JSONArray firstArrSum = new JSONArray();
		JSONArray secondArr = new JSONArray();
		
		List<String> YQLSXFWLJ = new ArrayList<String>();
		List<String> GLQCSXFWLJ = new ArrayList<String>();
		List<String> WSBCKXFWLJ = new ArrayList<String>();
		
		List<String> YJ1XFWLJ = new ArrayList<String>();
		List<String> FYQ1JSXFWLJ = new ArrayList<String>();
		List<String> FYQ1YL2XFWLJ = new ArrayList<String>();
		List<String> FYQ1YJ3XFWLJ = new ArrayList<String>();
		
		List<String> FYQ2JSXFWLJ = new ArrayList<String>();
		List<String> FYQ2YL2XFWLJ = new ArrayList<String>();
		List<String> FYQ2YJ3XFWLJ = new ArrayList<String>();
		
		List<String> FYQ3JSXFWLJ = new ArrayList<String>();
		List<String> FYQ3YL2XFWLJ = new ArrayList<String>();
		List<String> FYQ3YJ3XFWLJ = new ArrayList<String>();
		
		Object[] xywsclTab = null;
		int dataflg = 0;  //数据存在标识  0：不存在， 1：存在
		int n = 0;
		Object[] sy = null;
		String a1 = null,a2 = null,a3 = null,b1 = null,b2 = null,b3 = null;
		String s1 = null,s2 = null,s3 = null;
		if(syData!=null && syData.size()>0){
			sy = syData.get(0);
		}
		String[][] dates = DateBean.getReportTime("time", reportDate);
		if(dataSet != null && dataSet.size()>0){
			for(String[] dateList:dates){
				obj = new JSONObject();

				for(String data:dateList){
					xywsclTab = null;
					dataflg = 0;
					for(Object[] xywscl:dataSet){
						if(null != xywscl[36] && data.toString().equals(xywscl[36].toString())){
							dataflg = 1;
							xywsclTab = xywscl;
						}
					}
					n++;
					if(dataflg == 1){
						obj = new JSONObject(); //液位，流量
						obj.put("RPD_U_THIN_WATER_AUTO_ID", xywsclTab[37]);
						obj.put("REPORT_TIME", data.substring(11, 16));
						if(xywsclTab[0] != null){
							obj.put("L1_1_TCG", xywsclTab[0]);
						}else{
							obj.put("L1_1_TCG", "");
						}
						if(xywsclTab[1]!=null){
							obj.put("LI_2_TCG", xywsclTab[1]);
						}else{
							obj.put("LI_2_TCG", "");
						}
						if(xywsclTab[2]!=null){
							obj.put("LI_1_HCG", xywsclTab[2]);
						}else{
							obj.put("LI_1_HCG", "");
						}
						if(xywsclTab[3]!=null){
							obj.put("LI_2_HCG", xywsclTab[3]);
						}else{
							obj.put("LI_2_HCG", "");
						}
						if(xywsclTab[4]!=null){
							obj.put("LI_1_JHG", xywsclTab[4]);
						}else{
							obj.put("LI_1_JHG", "");
						}
						if(xywsclTab[5]!=null){
							obj.put("LI_2_JHG", xywsclTab[5]);
						}else{
							obj.put("LI_2_JHG", "");
						}
						if(xywsclTab[6]!=null){
							obj.put("LI_1_WSC", xywsclTab[6]);
						}else{
							obj.put("LI_1_WSC", "");
						}
						if(xywsclTab[7]!=null){
							obj.put("LI_2_WSC", xywsclTab[7]);
						}else{
							obj.put("LI_2_WSC", "");
						}
						if(xywsclTab[8]!=null){
							obj.put("LI_WYG", xywsclTab[8]);
						}else{
							obj.put("LI_WYG", "");
						}
						
						if(xywsclTab[9]!=null){
							obj.put("LT_XFYW", xywsclTab[9]);
						}else{
							obj.put("LT_XFYW", "");
						}
						obj.put("REPORT_TIME", data.substring(11, 16));

						if(xywsclTab[10]!=null){
							obj.put("FI_YYCLZLS", xywsclTab[10]);
						}else{
							obj.put("FI_YYCLZLS", "");
						}
						if(xywsclTab[11]!=null){
							obj.put("YQLSXFWLJ", xywsclTab[11]);
							YQLSXFWLJ.add(xywsclTab[11].toString());
						}else{
							obj.put("YQLSXFWLJ", "");
							YQLSXFWLJ.add("");
						}
						if(xywsclTab[12]!=null){
							obj.put("FI_YTZS", xywsclTab[12]);
						}else{
							obj.put("FI_YTZS", "");
						}
						
						if(xywsclTab[13]!=null){
							obj.put("GLQCSXFWLJ", xywsclTab[13]);
							GLQCSXFWLJ.add(xywsclTab[13].toString());
						}else{
							obj.put("GLQCSXFWLJ", "");
							GLQCSXFWLJ.add("");
						}
						if(xywsclTab[14]!=null){
							obj.put("FI_JHSO1", xywsclTab[14]);
						}else{
							obj.put("FI_JHSO1", "");
						}
						
						if(xywsclTab[15]!=null){
							obj.put("WSBCKXFWLJ", xywsclTab[15]);
							WSBCKXFWLJ.add(xywsclTab[15].toString());
						}else{
							obj.put("WSBCKXFWLJ", "");
							WSBCKXFWLJ.add("");
						}
						//添加水源井部分
						if(sy!=null){
							if(n==2){
								if(sy[3] !=null){
									obj.put("SYJ1SL", sy[3]);
									b1 = sy[3].toString();
								}else{
									obj.put("SYJ1SL", "");
								}
								if(sy[4] !=null){
									obj.put("SYJ2SL", sy[4]);
									b2 = sy[4].toString();
								}else{
									obj.put("SYJ2SL", "");
								}
								if(sy[5] !=null){
									obj.put("SYJ3SL", sy[5]);
									b3 = sy[5].toString();
								}else{
									obj.put("SYJ3SL", "");
								}
							}else if(n==4){
								if(sy[0] !=null){
									obj.put("SYJ1SL", sy[0]);
									a1 = sy[0].toString();
								}else{
									obj.put("SYJ1SL", "");
								}
								if(sy[1] !=null){
									obj.put("SYJ2SL", sy[1]);
									a2 = sy[1].toString();
								}else{
									obj.put("SYJ2SL", "");
								}
								if(sy[2] !=null){
									obj.put("SYJ3SL", sy[2]);
									a3 = sy[2].toString();
								}else{
									obj.put("SYJ3SL", "");
								}
							}else if(n==6){
								if(a1!=null && a1!="" && b1!=null && b1!=""){
									obj.put("SYJ1SL", Double.parseDouble(a1)-Double.parseDouble(b1));
									s1 = String.valueOf((Double.parseDouble(a1)-Double.parseDouble(b1)));
								}else{
									obj.put("SYJ1SL", "");
								}
								if(a2!=null && a2!="" && b2!=null && b2!=""){
									obj.put("SYJ2SL", Double.parseDouble(a2)-Double.parseDouble(b2));
									s2 = String.valueOf((Double.parseDouble(a2)-Double.parseDouble(b2)));
								}else{
									obj.put("SYJ2SL", "");
								}
								if(a3!=null && a3!="" && b3!=null && b3!=""){
									obj.put("SYJ3SL", Double.parseDouble(a3)-Double.parseDouble(b3));
									s3 = String.valueOf((Double.parseDouble(a3)-Double.parseDouble(b3)));
								}else{
									obj.put("SYJ3SL", "");
								}
							}else if(n==8){
								if((s1==null || s1=="") && (s2==null || s2!="") && (s3==null || s3!="")){
									obj.put("SYJ1SL", "");
								}else{
									String sSum1 = (s1!=null && s1!="")?s1:"0";
									String sSum2 = (s2!=null && s2!="")?s2:"0";
									String sSum3 = (s3!=null && s3!="")?s3:"0";
									obj.put("SYJ1SL", Double.parseDouble(sSum1)+Double.parseDouble(sSum2)+Double.parseDouble(sSum3));
								}
							}
						}else{
							obj.put("SYJ1SL", "");
							obj.put("SYJ2SL", "");
							obj.put("SYJ3SL", "");
						}
						
						//反应器
						obj2 = new JSONObject();
						obj2.put("REPORT_TIME", data.substring(11, 16));
						if(xywsclTab[16] != null){
							obj2.put("FI_1_FYJ", xywsclTab[16]);
						}else{
							obj2.put("FI_1_FYJ", "");
						}
						if(xywsclTab[17]!=null){
							obj2.put("YJ1XFWLJ", xywsclTab[17]);
							YJ1XFWLJ.add(xywsclTab[17].toString());
						}else{
							obj2.put("YJ1XFWLJ", "");
							YJ1XFWLJ.add("");
						}
						if(xywsclTab[18]!=null){
							obj2.put("FI_1_FYQ", xywsclTab[18]);
						}else{
							obj2.put("FI_1_FYQ", "");
						}
						if(xywsclTab[19]!=null){
							obj2.put("FYQ1JSXFWLJ", xywsclTab[19]);
							FYQ1JSXFWLJ.add(xywsclTab[19].toString());
						}else{
							obj2.put("FYQ1JSXFWLJ", "");
							FYQ1JSXFWLJ.add("");
						}
						if(xywsclTab[20]!=null){
							obj2.put("FI_2_YJO1", xywsclTab[20]);
						}else{
							obj2.put("FI_2_YJO1", "");
						}
						if(xywsclTab[21]!=null){
							obj2.put("FYQ1YL2XFWLJ", xywsclTab[21]);
							FYQ1YL2XFWLJ.add(xywsclTab[21].toString());
						}else{
							obj2.put("FYQ1YL2XFWLJ", "");
							FYQ1YL2XFWLJ.add("");
						}
						if(xywsclTab[22]!=null){
							obj2.put("FI_3_FYJO1", xywsclTab[22]);
						}else{
							obj2.put("FI_3_FYJO1", "");
						}
						if(xywsclTab[23]!=null){
							obj2.put("FYQ1YJ3XFWLJ", xywsclTab[23]);
							FYQ1YJ3XFWLJ.add(xywsclTab[23].toString());
						}else{
							obj2.put("FYQ1YJ3XFWLJ", "");
							FYQ1YJ3XFWLJ.add("");
						}
						if(xywsclTab[24]!=null){
							obj2.put("FI_2_FYQ", xywsclTab[24]);
						}else{
							obj2.put("FI_2_FYQ", "");
						}
						
						if(xywsclTab[25]!=null){
							obj2.put("FYQ2JSXFWLJ", xywsclTab[25]);
							FYQ2JSXFWLJ.add(xywsclTab[25].toString());
						}else{
							obj2.put("FYQ2JSXFWLJ", "");
							FYQ2JSXFWLJ.add("");
						}
						if(xywsclTab[26]!=null){
							obj2.put("FI_2_YJO2", xywsclTab[26]);
						}else{
							obj2.put("FI_2_YJO2", "");
						}
						if(xywsclTab[27]!=null){
							obj2.put("FYQ2YL2XFWLJ", xywsclTab[27]);
							FYQ2YL2XFWLJ.add(xywsclTab[27].toString());
						}else{
							obj2.put("FYQ2YL2XFWLJ", "");
							FYQ2YL2XFWLJ.add("");
						}
						if(xywsclTab[28]!=null){
							obj2.put("FI_3_FYJO2", xywsclTab[28]);
						}else{
							obj2.put("FI_3_FYJO2", "");
						}
						
						if(xywsclTab[29]!=null){
							obj2.put("FYQ2YJ3XFWLJ", xywsclTab[29]);
							FYQ2YJ3XFWLJ.add(xywsclTab[29].toString());
						}else{
							obj2.put("FYQ2YJ3XFWLJ", "");
							FYQ2YJ3XFWLJ.add("");
						}
						if(xywsclTab[30]!=null){
							obj2.put("FI_3_FYQ", xywsclTab[30]);
						}else{
							obj2.put("FI_3_FYQ", "");
						}
						
						if(xywsclTab[31]!=null){
							obj2.put("FYQ3JSXFWLJ", xywsclTab[31]);
							FYQ3JSXFWLJ.add(xywsclTab[31].toString());
						}else{
							obj2.put("FYQ3JSXFWLJ", "");
							FYQ3JSXFWLJ.add("");
						}
						
						if(xywsclTab[32]!=null){
							obj2.put("FI_2_YJO3", xywsclTab[32]);
						}else{
							obj2.put("FI_2_YJO3", "");
						}
						
						if(xywsclTab[33]!=null){
							obj2.put("FYQ3YL2XFWLJ", xywsclTab[33]);
							FYQ3YL2XFWLJ.add(xywsclTab[33].toString());
						}else{
							obj2.put("FYQ3YL2XFWLJ", "");
							FYQ3YL2XFWLJ.add("");
						}
						if(xywsclTab[34]!=null){
							obj2.put("FI_3_FYJO3", xywsclTab[34]);
						}else{
							obj2.put("FI_3_FYJO3", "");
						}
						if(xywsclTab[35]!=null){
							obj2.put("FYQ3YJ3XFWLJ", xywsclTab[35]);
							FYQ3YJ3XFWLJ.add(xywsclTab[35].toString());
						}else{
							obj2.put("FYQ3YJ3XFWLJ", "");
							FYQ3YJ3XFWLJ.add("");
						}
					}else{
						obj = new JSONObject();
						obj.put("RPD_U_THIN_WATER_AUTO_ID", "");
						obj.put("REPORT_TIME", data.substring(11, 16));
						obj.put("L1_1_TCG", "");
						obj.put("LI_2_TCG", "");
						obj.put("LI_1_HCG","");
						obj.put("LI_2_HCG", "");
						obj.put("LI_1_JHG", "");
						obj.put("LI_2_JHG", "");
						obj.put("LI_1_WSC", "");
						obj.put("LI_2_WSC", "");
						obj.put("LI_WYG", "");
						obj.put("LT_XFYW", "");
						
						obj.put("REPORT_TIME", data.substring(11, 16));
						obj.put("FI_YYCLZLS", "");
						obj.put("YQLSXFWLJ", "");
						obj.put("FI_YTZS", "");
						obj.put("GLQCSXFWLJ", "");
						obj.put("FI_JHSO1", "");
						obj.put("WSBCKXFWLJ", "");
						YQLSXFWLJ.add("");
						GLQCSXFWLJ.add("");
						WSBCKXFWLJ.add("");
						
						obj.put("SYJ1SL", "");
						obj.put("SYJ2SL", "");
						obj.put("SYJ3SL", "");
						
						
						//反应器
						obj2 = new JSONObject();
						obj2.put("REPORT_TIME", data.substring(11, 16));
						obj2.put("FI_1_FYJ", "");
						obj2.put("YJ1XFWLJ", "");
						obj2.put("FI_1_FYQ", "");
						obj2.put("FYQ1JSXFWLJ", "");
						obj2.put("FI_2_YJO1", "");
						obj2.put("FYQ1YL2XFWLJ", "");
						obj2.put("FI_3_FYJO1", "");
						obj2.put("FYQ1YJ3XFWLJ", "");
						obj2.put("FI_2_FYQ", "");
						obj2.put("FYQ2JSXFWLJ", "");
						obj2.put("FI_2_YJO2", "");
						obj2.put("FYQ2YL2XFWLJ", "");
						obj2.put("FI_3_FYJO2", "");
						obj2.put("FYQ2YJ3XFWLJ", "");
						obj2.put("FI_3_FYQ", "");
						obj2.put("FYQ3JSXFWLJ", "");
						obj2.put("FI_2_YJO3", "");
						obj2.put("FYQ3YL2XFWLJ", "");
						obj2.put("FI_3_FYJO3", "");
						obj2.put("FYQ3YJ3XFWLJ", "");
						
						YJ1XFWLJ.add("");
						FYQ1JSXFWLJ.add("");
						FYQ1YL2XFWLJ.add("");
						FYQ1YJ3XFWLJ.add("");
						FYQ2JSXFWLJ.add("");
						FYQ2YL2XFWLJ.add("");
						FYQ2YJ3XFWLJ.add("");
						FYQ3JSXFWLJ.add("");
						FYQ3YL2XFWLJ.add("");
						FYQ3YJ3XFWLJ.add("");
					}
					firstArr.add(obj);
					secondArr.add(obj2);
				}
			}
		}else{
			int m=0;
			for(String[] dateList:dates){
				for(String data:dateList){
					m++;
					obj = new JSONObject();
					obj2 = new JSONObject();
					obj.put("RPD_U_THIN_WATER_AUTO_ID", "");
					obj.put("REPORT_TIME", data.substring(11, 16));
					obj.put("L1_1_TCG", "");
					obj.put("LI_2_TCG", "");
					obj.put("LI_1_HCG","");
					obj.put("LI_2_HCG", "");
					obj.put("LI_1_JHG", "");
					obj.put("LI_2_JHG", "");
					obj.put("LI_1_WSC", "");
					obj.put("LI_2_WSC", "");
					obj.put("LI_WYG", "");
					obj.put("LT_XFYW", "");
					
					obj.put("REPORT_TIME", data.substring(11, 16));
					obj.put("FI_YYCLZLS", "");
					obj.put("YQLSXFWLJ", "");
					obj.put("FI_YTZS", "");
					obj.put("GLQCSXFWLJ", "");
					obj.put("FI_JHSO1", "");
					obj.put("WSBCKXFWLJ", "");
					
					//添加水源井部分
					if(sy!=null){
						if(m==2){
							if(sy[3] !=null){
								obj.put("SYJ1SL", sy[3]);
								b1 = sy[3].toString();
							}else{
								obj.put("SYJ1SL", "");
							}
							if(sy[4] !=null){
								obj.put("SYJ2SL", sy[4]);
								b2 = sy[4].toString();
							}else{
								obj.put("SYJ2SL", "");
							}
							if(sy[5] !=null){
								obj.put("SYJ3SL", sy[5]);
								b3 = sy[5].toString();
							}else{
								obj.put("SYJ3SL", "");
							}
						}else if(m==4){
							if(sy[0] !=null){
								obj.put("SYJ1SL", sy[0]);
								a1 = sy[0].toString();
							}else{
								obj.put("SYJ1SL", "");
							}
							if(sy[1] !=null){
								obj.put("SYJ2SL", sy[1]);
								a2 = sy[1].toString();
							}else{
								obj.put("SYJ2SL", "");
							}
							if(sy[2] !=null){
								obj.put("SYJ3SL", sy[2]);
								a3 = sy[2].toString();
							}else{
								obj.put("SYJ3SL", "");
							}
						}else if(m==6){
							if(a1!=null && a1!="" && b1!=null && b1!=""){
								obj.put("SYJ1SL", Double.parseDouble(a1)-Double.parseDouble(b1));
								s1 = String.valueOf((Double.parseDouble(a1)-Double.parseDouble(b1)));
							}else{
								obj.put("SYJ1SL", "");
							}
							if(a2!=null && a2!="" && b2!=null && b2!=""){
								obj.put("SYJ2SL", Double.parseDouble(a2)-Double.parseDouble(b2));
								s2 = String.valueOf((Double.parseDouble(a2)-Double.parseDouble(b2)));
							}else{
								obj.put("SYJ2SL", "");
							}
							if(a3!=null && a3!="" && b3!=null && b3!=""){
								obj.put("SYJ3SL", Double.parseDouble(a3)-Double.parseDouble(b3));
								s3 = String.valueOf((Double.parseDouble(a3)-Double.parseDouble(b3)));
							}else{
								obj.put("SYJ3SL", "");
							}
						}else if(m==8){
							if((s1==null || s1=="") && (s2==null || s2!="") && (s3==null || s3!="")){
								obj.put("SYJ1SL", "");
							}else{
								String sSum1 = (s1!=null && s1!="")?s1:"0";
								String sSum2 = (s2!=null && s2!="")?s2:"0";
								String sSum3 = (s3!=null && s3!="")?s3:"0";
								obj.put("SYJ1SL", Double.parseDouble(sSum1)+Double.parseDouble(sSum2)+Double.parseDouble(sSum3));
							}
						}
					}else{
						obj.put("SYJ1SL", "");
						obj.put("SYJ2SL", "");
						obj.put("SYJ3SL", "");
					}
					
					
					//反应器
					obj2.put("REPORT_TIME", data.substring(11, 16));
					obj2.put("FI_1_FYJ", "");
					obj2.put("YJ1XFWLJ", "");
					obj2.put("FI_1_FYQ", "");
					obj2.put("FYQ1JSXFWLJ", "");
					obj2.put("FI_2_YJO1", "");
					obj2.put("FYQ1YL2XFWLJ", "");
					obj2.put("FI_3_FYJO1", "");
					obj2.put("FYQ1YJ3XFWLJ", "");
					obj2.put("FI_2_FYQ", "");
					obj2.put("FYQ2JSXFWLJ", "");
					obj2.put("FI_2_YJO2", "");
					obj2.put("FYQ2YL2XFWLJ", "");
					obj2.put("FI_3_FYJO2", "");
					obj2.put("FYQ2YJ3XFWLJ", "");
					obj2.put("FI_3_FYQ", "");
					obj2.put("FYQ3JSXFWLJ", "");
					obj2.put("FI_2_YJO3", "");
					obj2.put("FYQ3YL2XFWLJ", "");
					obj2.put("FI_3_FYJO3", "");
					obj2.put("FYQ3YJ3XFWLJ", "");
					firstArr.add(obj);
					secondArr.add(obj2);
				}
			}
		}
		
		
		if(dataSet != null && dataSet.size()>0){
			obj2 = new JSONObject();
			obj2.put("REPORT_TIME", "累积值");
			obj2.put("FI_1_FYJ", "");
			obj2.put("YJ1XFWLJ", CommonsUtil.getSumData(YJ1XFWLJ));
			obj2.put("FI_1_FYQ", "");
			obj2.put("FYQ1JSXFWLJ", CommonsUtil.getSumData(FYQ1JSXFWLJ));
			obj2.put("FI_2_YJO1", "");
			obj2.put("FYQ1YL2XFWLJ", CommonsUtil.getSumData(FYQ1YL2XFWLJ));
			obj2.put("FI_3_FYJO1", "");
			obj2.put("FYQ1YJ3XFWLJ", CommonsUtil.getSumData(FYQ1YJ3XFWLJ));
			obj2.put("FI_2_FYQ", "");
			obj2.put("FYQ2JSXFWLJ", CommonsUtil.getSumData(FYQ2JSXFWLJ));
			obj2.put("FI_2_YJO2", "");
			obj2.put("FYQ2YL2XFWLJ", CommonsUtil.getSumData(FYQ2YL2XFWLJ));
			obj2.put("FI_3_FYJO2", "");
			obj2.put("FYQ2YJ3XFWLJ", CommonsUtil.getSumData(FYQ2YJ3XFWLJ));
			obj2.put("FI_3_FYQ", "");
			obj2.put("FYQ3JSXFWLJ", CommonsUtil.getSumData(FYQ3JSXFWLJ));
			obj2.put("FI_2_YJO3", "");
			obj2.put("FYQ3YL2XFWLJ", CommonsUtil.getSumData(FYQ3YL2XFWLJ));
			obj2.put("FI_3_FYJO3", "");
			obj2.put("FYQ3YJ3XFWLJ", CommonsUtil.getSumData(FYQ3YJ3XFWLJ));
			
			secondArr.add(obj2);

			obj = new JSONObject();
			obj.put("YQLSXFWLJ", CommonsUtil.getSumData(YQLSXFWLJ));
			obj.put("GLQCSXFWLJ", CommonsUtil.getSumData(GLQCSXFWLJ));
			obj.put("WSBCKXFWLJ", CommonsUtil.getSumData(WSBCKXFWLJ));
			firstArrSum.add(obj);
		}else{
			obj2 = new JSONObject();
			obj2.put("REPORT_TIME", "累积值");
			obj2.put("FI_1_FYJ", "");
			obj2.put("YJ1XFWLJ", "");
			obj2.put("FI_1_FYQ", "");
			obj2.put("FYQ1JSXFWLJ", "");
			obj2.put("FI_2_YJO1", "");
			obj2.put("FYQ1YL2XFWLJ", "");
			obj2.put("FI_3_FYJO1", "");
			obj2.put("FYQ1YJ3XFWLJ", "");
			obj2.put("FI_2_FYQ", "");
			obj2.put("FYQ2JSXFWLJ", "");
			obj2.put("FI_2_YJO2", "");
			obj2.put("FYQ2YL2XFWLJ", "");
			obj2.put("FI_3_FYJO2", "");
			obj2.put("FYQ2YJ3XFWLJ", "");
			obj2.put("FI_3_FYQ", "");
			obj2.put("FYQ3JSXFWLJ", "");
			obj2.put("FI_2_YJO3", "");
			obj2.put("FYQ3YL2XFWLJ", "");
			obj2.put("FI_3_FYJO3", "");
			obj2.put("FYQ3YJ3XFWLJ", "");
			secondArr.add(obj2);
			
			obj = new JSONObject();
			obj.put("YQLSXFWLJ", "");
			obj.put("GLQCSXFWLJ", "");
			obj.put("WSBCKXFWLJ","");
			firstArrSum.add(obj);
		}
		
		
		
		obj = new JSONObject();
		obj.put("firstArr", firstArr);
		obj.put("firstArrSum", firstArrSum);
		obj.put("secondArr", secondArr);
		
		return obj;
	}

	public JSONArray searchWSCLOther(String rq) throws Exception {
		String sql = "select CJSJHY,DJHY,DCHY,XJHY,XCHY,YJJKHY,YJCKHY,EJCKHY,ZSJKHY,W33HY,GYHY,CGYHY," +
				"CJSJHY1,DJHY1,DCHY1,XJHY1,XCHY1,YJJKHY1,YJCKHY1,EJCKHY1,ZSJKHY1,W33HY1,GYHY1,CGYHY1," +
				"CJSJHY2,DJHY2,DCHY2,XJHY2,XCHY2,YJJKHY2,YJCKHY2,EJCKHY2,ZSJKHY2,W33HY2,GYHY2,CGYHY2," +
				"CJSJXFW,DJXFW,DCXFW,XJXFW,XCXFW,YJJKXFW,YJCKXFW,EJCKXFW,ZSJKXFW,W33XFW,GYXFW,CGYXFW," +
				"CJSJXFW1,DJXFW1,DCXFW1,XJXFW1,XCXFW1,YJJKXFW1,YJCKXFW1,EJCKXFW1,ZSJKXFW1,W33XFW1,GYXFW1,CGYXFW1," +
				"CJSJXFW2,DJXFW2,DCXFW2,XJXFW2,XCXFW2,YJJKXFW2,YJCKXFW2,EJCKXFW2,ZSJKXFW2,W33XFW2,GYXFW2,CGYXFW2," +
				"XJJYL,ZGJYL,FXYL,FPJYL,SNL,ZYJYL,RPD_U_THIN_WATER_GENERAL_ID,ZBR,SHR,BZ,BBRQ" +
				" from PC_RPD_U_THIN_WATER_GENERAL_T where BBRQ=to_date('"+rq+"','yyyy-mm-dd')";
		List<Object[]> dataSet = xYWSCLDao.searchXYWSDatas(sql);
		JSONObject obj = new JSONObject();
		JSONArray arr = new JSONArray();
		
		if(dataSet!=null && dataSet.size()>0){
			
			if(dataSet.get(0)[0]!=null){
				obj.put("CJSJHY", dataSet.get(0)[0]);
			}else{
				obj.put("CJSJHY", "");
			}			
			if(dataSet.get(0)[1]!=null){
				obj.put("DJHY", dataSet.get(0)[1]);
			}else{
				obj.put("DJHY", "");
			}
			if(dataSet.get(0)[2]!=null){
				obj.put("DCHY", dataSet.get(0)[2]);
			}else{
				obj.put("DCHY", "");
			}
			if(dataSet.get(0)[3]!=null){
				obj.put("XJHY", dataSet.get(0)[3]);
			}else{
				obj.put("XJHY", "");
			}
			if(dataSet.get(0)[4]!=null){
				obj.put("XCHY", dataSet.get(0)[4]);
			}else{
				obj.put("XCHY", "");
			}
			if(dataSet.get(0)[5]!=null){
				obj.put("YJJKHY", dataSet.get(0)[5]);
			}else{
				obj.put("YJJKHY", "");
			}
			if(dataSet.get(0)[6]!=null){
				obj.put("YJCKHY", dataSet.get(0)[6]);
			}else{
				obj.put("YJCKHY", "");
			}
			if(dataSet.get(0)[7]!=null){
				obj.put("EJCKHY", dataSet.get(0)[7]);
			}else{
				obj.put("EJCKHY", "");
			}
			if(dataSet.get(0)[8]!=null){
				obj.put("ZSJKHY", dataSet.get(0)[8]);
			}else{
				obj.put("ZSJKHY", "");
			}
			if(dataSet.get(0)[9]!=null){
				obj.put("W33HY", dataSet.get(0)[9]);
			}else{
				obj.put("W33HY", "");
			}
			if(dataSet.get(0)[10]!=null){
				obj.put("GYHY", dataSet.get(0)[10]);
			}else{
				obj.put("GYHY", "");
			}
			if(dataSet.get(0)[11]!=null){
				obj.put("CGYHY", dataSet.get(0)[11]);
			}else{
				obj.put("CGYHY", "");
			}
			
			
			
			if(dataSet.get(0)[12]!=null){
				obj.put("CJSJHY1", dataSet.get(0)[12]);
			}else{
				obj.put("CJSJHY1", "");
			}			
			if(dataSet.get(0)[13]!=null){
				obj.put("DJHY1", dataSet.get(0)[13]);
			}else{
				obj.put("DJHY1", "");
			}
			if(dataSet.get(0)[14]!=null){
				obj.put("DCHY1", dataSet.get(0)[14]);
			}else{
				obj.put("DCHY1", "");
			}
			if(dataSet.get(0)[15]!=null){
				obj.put("XJHY1", dataSet.get(0)[15]);
			}else{
				obj.put("XJHY1", "");
			}
			if(dataSet.get(0)[16]!=null){
				obj.put("XCHY1", dataSet.get(0)[16]);
			}else{
				obj.put("XCHY1", "");
			}
			if(dataSet.get(0)[17]!=null){
				obj.put("YJJKHY1", dataSet.get(0)[17]);
			}else{
				obj.put("YJJKHY1", "");
			}
			if(dataSet.get(0)[18]!=null){
				obj.put("YJCKHY1", dataSet.get(0)[18]);
			}else{
				obj.put("YJCKHY1", "");
			}
			if(dataSet.get(0)[19]!=null){
				obj.put("EJCKHY1", dataSet.get(0)[19]);
			}else{
				obj.put("EJCKHY1", "");
			}
			if(dataSet.get(0)[20]!=null){
				obj.put("ZSJKHY1", dataSet.get(0)[20]);
			}else{
				obj.put("ZSJKHY1", "");
			}
			if(dataSet.get(0)[21]!=null){
				obj.put("W33HY1", dataSet.get(0)[21]);
			}else{
				obj.put("W33HY1", "");
			}
			if(dataSet.get(0)[22]!=null){
				obj.put("GYHY1", dataSet.get(0)[22]);
			}else{
				obj.put("GYHY1", "");
			}
			if(dataSet.get(0)[23]!=null){
				obj.put("CGYHY1", dataSet.get(0)[23]);
			}else{
				obj.put("CGYHY1", "");
			}
			
			
			
			if(dataSet.get(0)[24]!=null){
				obj.put("CJSJHY2", dataSet.get(0)[24]);
			}else{
				obj.put("CJSJHY2", "");
			}			
			if(dataSet.get(0)[25]!=null){
				obj.put("DJHY2", dataSet.get(0)[25]);
			}else{
				obj.put("DJHY2", "");
			}
			if(dataSet.get(0)[26]!=null){
				obj.put("DCHY2", dataSet.get(0)[26]);
			}else{
				obj.put("DCHY2", "");
			}
			if(dataSet.get(0)[27]!=null){
				obj.put("XJHY2", dataSet.get(0)[27]);
			}else{
				obj.put("XJHY2", "");
			}
			if(dataSet.get(0)[28]!=null){
				obj.put("XCHY2", dataSet.get(0)[28]);
			}else{
				obj.put("XCHY2", "");
			}
			if(dataSet.get(0)[29]!=null){
				obj.put("YJJKHY2", dataSet.get(0)[29]);
			}else{
				obj.put("YJJKHY2", "");
			}
			if(dataSet.get(0)[30]!=null){
				obj.put("YJCKHY2", dataSet.get(0)[30]);
			}else{
				obj.put("YJCKHY2", "");
			}
			if(dataSet.get(0)[31]!=null){
				obj.put("EJCKHY2", dataSet.get(0)[31]);
			}else{
				obj.put("EJCKHY2", "");
			}
			if(dataSet.get(0)[32]!=null){
				obj.put("ZSJKHY2", dataSet.get(0)[32]);
			}else{
				obj.put("ZSJKHY2", "");
			}
			if(dataSet.get(0)[33]!=null){
				obj.put("W33HY2", dataSet.get(0)[33]);
			}else{
				obj.put("W33HY2", "");
			}
			if(dataSet.get(0)[34]!=null){
				obj.put("GYHY2", dataSet.get(0)[34]);
			}else{
				obj.put("GYHY2", "");
			}
			if(dataSet.get(0)[35]!=null){
				obj.put("CGYHY2", dataSet.get(0)[35]);
			}else{
				obj.put("CGYHY2", "");
			}
			
			
			
			if(dataSet.get(0)[36]!=null){
				obj.put("CJSJXFW", dataSet.get(0)[36]);
			}else{
				obj.put("CJSJXFW", "");
			}
			if(dataSet.get(0)[37]!=null){
				obj.put("DJXFW", dataSet.get(0)[37]);
			}else{
				obj.put("DJXFW", "");
			}
			if(dataSet.get(0)[38]!=null){
				obj.put("DCXFW", dataSet.get(0)[38]);
			}else{
				obj.put("DCXFW", "");
			}
			if(dataSet.get(0)[39]!=null){
				obj.put("XJXFW", dataSet.get(0)[39]);
			}else{
				obj.put("XJXFW", "");
			}
			if(dataSet.get(0)[40]!=null){
				obj.put("XCXFW", dataSet.get(0)[40]);
			}else{
				obj.put("XCXFW", "");
			}
			if(dataSet.get(0)[41]!=null){
				obj.put("YJJKXFW", dataSet.get(0)[41]);
			}else{
				obj.put("YJJKXFW", "");
			}
			if(dataSet.get(0)[42]!=null){
				obj.put("YJCKXFW", dataSet.get(0)[42]);
			}else{
				obj.put("YJCKXFW", "");
			}
			if(dataSet.get(0)[43]!=null){
				obj.put("EJCKXFW", dataSet.get(0)[43]);
			}else{
				obj.put("EJCKXFW", "");
			}
			if(dataSet.get(0)[44]!=null){
				obj.put("ZSJKXFW", dataSet.get(0)[44]);
			}else{
				obj.put("ZSJKXFW", "");
			}
			if(dataSet.get(0)[45]!=null){
				obj.put("W33XFW", dataSet.get(0)[45]);
			}else{
				obj.put("W33XFW", "");
			}
			
			if(dataSet.get(0)[46]!=null){
				obj.put("GYXFW", dataSet.get(0)[46]);
			}else{
				obj.put("GYXFW", "");
			}
			if(dataSet.get(0)[47]!=null){
				obj.put("CGYXFW", dataSet.get(0)[47]);
			}else{
				obj.put("CGYXFW", "");
			}
			
			
			if(dataSet.get(0)[48]!=null){
				obj.put("CJSJXFW1", dataSet.get(0)[48]);
			}else{
				obj.put("CJSJXFW1", "");
			}
			if(dataSet.get(0)[49]!=null){
				obj.put("DJXFW1", dataSet.get(0)[49]);
			}else{
				obj.put("DJXFW1", "");
			}
			if(dataSet.get(0)[50]!=null){
				obj.put("DCXFW1", dataSet.get(0)[50]);
			}else{
				obj.put("DCXFW1", "");
			}
			if(dataSet.get(0)[51]!=null){
				obj.put("XJXFW1", dataSet.get(0)[51]);
			}else{
				obj.put("XJXFW1", "");
			}
			if(dataSet.get(0)[52]!=null){
				obj.put("XCXFW1", dataSet.get(0)[52]);
			}else{
				obj.put("XCXFW1", "");
			}
			if(dataSet.get(0)[53]!=null){
				obj.put("YJJKXFW1", dataSet.get(0)[53]);
			}else{
				obj.put("YJJKXFW1", "");
			}
			if(dataSet.get(0)[54]!=null){
				obj.put("YJCKXFW1", dataSet.get(0)[54]);
			}else{
				obj.put("YJCKXFW1", "");
			}
			if(dataSet.get(0)[55]!=null){
				obj.put("EJCKXFW1", dataSet.get(0)[55]);
			}else{
				obj.put("EJCKXFW1", "");
			}
			if(dataSet.get(0)[56]!=null){
				obj.put("ZSJKXFW1", dataSet.get(0)[56]);
			}else{
				obj.put("ZSJKXFW1", "");
			}
			if(dataSet.get(0)[57]!=null){
				obj.put("W33XFW1", dataSet.get(0)[57]);
			}else{
				obj.put("W33XFW1", "");
			}
			
			if(dataSet.get(0)[58]!=null){
				obj.put("GYXFW1", dataSet.get(0)[58]);
			}else{
				obj.put("GYXFW1", "");
			}
			if(dataSet.get(0)[59]!=null){
				obj.put("CGYXFW1", dataSet.get(0)[59]);
			}else{
				obj.put("CGYXFW1", "");
			}
			
			
			if(dataSet.get(0)[60]!=null){
				obj.put("CJSJXFW2", dataSet.get(0)[60]);
			}else{
				obj.put("CJSJXFW2", "");
			}
			if(dataSet.get(0)[61]!=null){
				obj.put("DJXFW2", dataSet.get(0)[61]);
			}else{
				obj.put("DJXFW2", "");
			}
			if(dataSet.get(0)[62]!=null){
				obj.put("DCXFW2", dataSet.get(0)[62]);
			}else{
				obj.put("DCXFW2", "");
			}
			if(dataSet.get(0)[63]!=null){
				obj.put("XJXFW2", dataSet.get(0)[63]);
			}else{
				obj.put("XJXFW2", "");
			}
			if(dataSet.get(0)[64]!=null){
				obj.put("XCXFW2", dataSet.get(0)[64]);
			}else{
				obj.put("XCXFW2", "");
			}
			if(dataSet.get(0)[65]!=null){
				obj.put("YJJKXFW2", dataSet.get(0)[65]);
			}else{
				obj.put("YJJKXFW2", "");
			}
			if(dataSet.get(0)[66]!=null){
				obj.put("YJCKXFW2", dataSet.get(0)[66]);
			}else{
				obj.put("YJCKXFW2", "");
			}
			if(dataSet.get(0)[67]!=null){
				obj.put("EJCKXFW2", dataSet.get(0)[67]);
			}else{
				obj.put("EJCKXFW2", "");
			}
			if(dataSet.get(0)[68]!=null){
				obj.put("ZSJKXFW2", dataSet.get(0)[68]);
			}else{
				obj.put("ZSJKXFW2", "");
			}
			if(dataSet.get(0)[69]!=null){
				obj.put("W33XFW2", dataSet.get(0)[69]);
			}else{
				obj.put("W33XFW2", "");
			}
			
			if(dataSet.get(0)[70]!=null){
				obj.put("GYXFW2", dataSet.get(0)[70]);
			}else{
				obj.put("GYXFW2", "");
			}
			if(dataSet.get(0)[71]!=null){
				obj.put("CGYXFW2", dataSet.get(0)[71]);
			}else{
				obj.put("CGYXFW2", "");
			}
			
			
			
			
			
			if(dataSet.get(0)[72]!=null){
				obj.put("XJJYL", dataSet.get(0)[72]);
			}else{
				obj.put("XJJYL", "");
			}
			if(dataSet.get(0)[73]!=null){
				obj.put("ZGJYL", dataSet.get(0)[73]);
			}else{
				obj.put("ZGJYL", "");
			}			
			if(dataSet.get(0)[74]!=null){
				obj.put("FXYL", dataSet.get(0)[74]);
			}else{
				obj.put("FXYL", "");
			}
			if(dataSet.get(0)[75]!=null){
				obj.put("FPJYL", dataSet.get(0)[75]);
			}else{
				obj.put("FPJYL", "");
			}
			if(dataSet.get(0)[76]!=null){
				obj.put("SNL", dataSet.get(0)[76]);
			}else{
				obj.put("SNL", "");
			}
			if(dataSet.get(0)[77]!=null){
				obj.put("ZYJYL", dataSet.get(0)[77]);
			}else{
				obj.put("ZYJYL", "");
			}
			
			//一般信息
			if(dataSet.get(0)[78]!=null){
				obj.put("RPD_U_THIN_WATER_GENERAL_ID", dataSet.get(0)[78]);
			}else{
				obj.put("RPD_U_THIN_WATER_GENERAL_ID", "");
			}
			if(dataSet.get(0)[79]!=null){
				obj.put("ZBR", dataSet.get(0)[79]);
			}else{
				obj.put("ZBR", "");
			}
			if(dataSet.get(0)[80]!=null){
				obj.put("SHR", dataSet.get(0)[80]);
			}else{
				obj.put("SHR", "");
			}
			if(dataSet.get(0)[81]!=null){
				obj.put("BZ", dataSet.get(0)[81]);
			}else{
				obj.put("BZ", "");
			}
			if(dataSet.get(0)[82]!=null){
				obj.put("BBRQ", DateBean.getChinaDate(rq));
				obj.put("BBRQ1", rq);
			}else{
				obj.put("BBRQ", "");
				obj.put("BBRQ1", "");
			}
			
			arr.add(obj);
		}else{
			obj.put("CJSJHY", "");
			obj.put("DJHY", "");
			obj.put("DCHY", "");
			obj.put("XJHY", "");
			obj.put("XCHY", "");
			obj.put("YJJKHY", "");
			obj.put("YJCKHY", "");
			obj.put("EJCKHY", "");
			obj.put("ZSJKHY", "");
			obj.put("W33HY", "");
			obj.put("GYHY", "");
			obj.put("CGYHY", "");
			
			obj.put("CJSJHY1", "");
			obj.put("DJHY1", "");
			obj.put("DCHY1", "");
			obj.put("XJHY1", "");
			obj.put("XCHY1", "");
			obj.put("YJJKHY1", "");
			obj.put("YJCKHY1", "");
			obj.put("EJCKHY1", "");
			obj.put("ZSJKHY1", "");
			obj.put("W33HY1", "");
			obj.put("GYHY1", "");
			obj.put("CGYHY1", "");
			
			obj.put("CJSJHY2", "");
			obj.put("DJHY2", "");
			obj.put("DCHY2", "");
			obj.put("XJHY2", "");
			obj.put("XCHY2", "");
			obj.put("YJJKHY2", "");
			obj.put("YJCKHY2", "");
			obj.put("EJCKHY2", "");
			obj.put("ZSJKHY2", "");
			obj.put("W33HY2", "");
			obj.put("GYHY2", "");
			obj.put("CGYHY2", "");
			
			obj.put("CJSJXFW", "");
			obj.put("DJXFW", "");
			obj.put("DCXFW", "");
			obj.put("XJXFW", "");
			obj.put("XCXFW", "");
			obj.put("YJJKXFW", "");
			obj.put("YJCKXFW", "");
			obj.put("EJCKXFW", "");
			obj.put("ZSJKXFW", "");
			obj.put("W33XFW", "");
			obj.put("GYXFW", "");
			obj.put("CGYXFW", "");
			
			obj.put("CJSJXFW1", "");
			obj.put("DJXFW1", "");
			obj.put("DCXFW1", "");
			obj.put("XJXFW1", "");
			obj.put("XCXFW1", "");
			obj.put("YJJKXFW1", "");
			obj.put("YJCKXFW1", "");
			obj.put("EJCKXFW1", "");
			obj.put("ZSJKXFW1", "");
			obj.put("W33XFW1", "");
			obj.put("GYXFW1", "");
			obj.put("CGYXFW1", "");
			
			obj.put("CJSJXFW2", "");
			obj.put("DJXFW2", "");
			obj.put("DCXFW2", "");
			obj.put("XJXFW2", "");
			obj.put("XCXFW2", "");
			obj.put("YJJKXFW2", "");
			obj.put("YJCKXFW2", "");
			obj.put("EJCKXFW2", "");
			obj.put("ZSJKXFW2", "");
			obj.put("W33XFW2", "");
			obj.put("GYXFW2", "");
			obj.put("CGYXFW2", "");
			
			
			obj.put("XJJYL", "");
			obj.put("ZGJYL", "");
			obj.put("FXYL", "");
			obj.put("FPJYL", "");
			obj.put("SNL", "");
			obj.put("ZYJYL", "");
			
			obj.put("RPD_U_THIN_WATER_GENERAL_ID","");
			obj.put("ZBR", "");
			obj.put("SHR", "");
			obj.put("BZ", "");
			obj.put("BBRQ", DateBean.getChinaDate(rq));
			obj.put("BBRQ1", rq);
			
			
			arr.add(obj);
		}
		return arr;
	}
	
	public List<PcRpdUThinWaterGeneralT> SreachWaterGeneral(String id,String rq) throws Exception {
		String hql = " FROM PcRpdUThinWaterGeneralT r WHERE 1=1 ";
			if(id != null && !"".equals(id)){
				hql += " and r.rpdUThinWaterGeneralId = '"+id+"'";
			}
			
			if(rq != null && !"".equals(rq)){
				hql += " and r.bbrq = TO_DATE('"+rq+"','YYYY-MM-DD')";
			}
			
		return xYWSCLDao.SreachWaterGeneral(hql);
	}

	public List<Object[]> searchWSCLOther(String rq,String fields,String talbelFalg) throws Exception {
		String timeCalssql = "select tp.parameter_value from PC_CD_TRIGGERED_PROCESS_T tp where tp.parameter_name='union_station_rpd_start_time'";
		String calcNum = "-16";
		List<Object[]> dataSet = xYWSCLDao.searchXYWSDatas(timeCalssql);
		if(dataSet != null && dataSet.size()>0){
			calcNum = dataSet.get(0) + "";
		}
		String stratime =DateBean.getDynamicTime(calcNum, rq, "0");
		String endtime =DateBean.getDynamicTime(calcNum, rq, "1");
		String sql = "select " + fields + " from PC_RPD_U_THIN_WATER_AUTO_T u where u.BBSJ between TO_DATE('" + stratime + "','YYYY-MM-DD HH24:MI:SS') and TO_DATE('" + endtime + "','YYYY-MM-DD HH24:MI:SS') order by u.BBSJ";
		if ("PC_RPD_U_THIN_WATER_GENERAL_T".equals(talbelFalg)) {
			sql = "select " + fields + " from PC_RPD_U_THIN_WATER_GENERAL_T where BBRQ=TO_DATE('" + stratime.substring(0, 10) + "','YYYY-MM-DD')),PC_RPD_U_THIN_WATER_GENERAL_T where BBRQ=TO_DATE('" + endtime.substring(0, 10) + "','YYYY-MM-DD')";
		}
		List<Object[]> yyList = xYWSCLDao.searchXYWSDatas(sql);
		return yyList;
	}
	
	@Override
	public boolean updateWaterGeneral(PcRpdUThinWaterGeneralT waterGen)
			throws Exception {
		// TODO Auto-generated method stub
		return xYWSCLDao.updateWaterGeneral(waterGen);
	}

	@Override
	public List<PcRpdUThinWaterAutoT> SreachWaterAuto(
			String id, String rq) throws Exception {
		// TODO Auto-generated method stub
		String hql = " FROM PcRpdUThinWaterAutoT r WHERE 1=1 ";
		if(id != null && !"".equals(id)){
			hql += " and r.rpdUThinWaterAutoId = '"+id+"'";
		}
		
		if(rq != null && !"".equals(rq)){
			hql += " and r.bbsj = TO_DATE('"+rq+"','YYYY-MM-DD')";
		}
		
		return xYWSCLDao.SreachWaterAuto(hql);
	}

	@Override
	public boolean updateWaterAuto(PcRpdUThinWaterAutoT waterA)
			throws Exception {
		// TODO Auto-generated method stub
		return xYWSCLDao.updateWaterAuto(waterA);
	}

	@Override
	public int searchCalcNum() {
		// TODO Auto-generated method stub
		String timeCalssql = "select tp.parameter_value from PC_CD_TRIGGERED_PROCESS_T tp where tp.parameter_name='union_station_rpd_start_time'";
		String calcNum = "-16";
		List<Object[]> dataSet = xYWSCLDao.searchCalcNum(timeCalssql);
		if(dataSet != null && dataSet.size()>0){
			calcNum = dataSet.get(0) + "";
		}
		return Integer.parseInt(calcNum);
	}

	@Override
	public List<PcRpdUThinWaterGeneralT> SreachWaterGen(String rq)
			throws Exception {
		// TODO Auto-generated method stub
		String hql = " FROM PcRpdUThinWaterGeneralT r WHERE 1=1 ";
		if(rq != null && !"".equals(rq)){
			hql += " and r.bbrq = TO_DATE('"+rq+"','YYYY-MM-DD')";
		}
		
		return xYWSCLDao.SreachWaterGeneral(hql);
	}
}
