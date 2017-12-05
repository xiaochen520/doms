package com.echo.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.dao.SCLJYDao;
import com.echo.service.SCLJYService;
import com.echo.util.CommonsUtil;
import com.echo.util.DateBean;

public class SCLJYServiceImpl implements SCLJYService{
	private SCLJYDao sCLJYDao;
	
	public void setsCLJYDao(SCLJYDao sCLJYDao) {
		this.sCLJYDao = sCLJYDao;
	}
	
	public JSONArray searchSCLJYRPD(String reportDate,String totalNumf) throws Exception {
		JSONObject obj = new JSONObject();
		JSONArray arr = new JSONArray();
		String FIT20501ZTemp = "";
		String FIT20502AZTemp = "";
		String FIT20502BZTemp = "";
		String FIT20502CZTemp = "";
		String FIT20502DZTemp = "";
		String FIT20502EZTemp = "";
		String FIT20502FZTemp = "";
		String FIT20503AZTemp = "";
		String FIT20503BZTemp = "";
		String FIT20503CZTemp = "";
		String FIT20503DZTemp = "";
		String FIT20503EZTemp = "";
		String FIT20503FZTemp = "";
		
		List<String> FIT20501Z = new ArrayList<String>();
		List<String> FIT20502AZ = new ArrayList<String>();
		List<String> FIT20502BZ = new ArrayList<String>();
		List<String> FIT20502CZ = new ArrayList<String>();
		List<String> FIT20502DZ = new ArrayList<String>();
		List<String> FIT20502EZ = new ArrayList<String>();
		List<String> FIT20502FZ = new ArrayList<String>();
		List<String> FIT20503AZ = new ArrayList<String>();
		List<String> FIT20503BZ = new ArrayList<String>();
		List<String> FIT20503CZ = new ArrayList<String>();
		List<String> FIT20503DZ = new ArrayList<String>();
		List<String> FIT20503EZ = new ArrayList<String>();
		List<String> FIT20503FZ = new ArrayList<String>();
		
		List<String> FIT20501ZSUM = new ArrayList<String>();
		List<String> FIT20502AZSUM = new ArrayList<String>();
		List<String> FIT20502BZSUM = new ArrayList<String>();
		List<String> FIT20502CZSUM = new ArrayList<String>();
		List<String> FIT20502DZSUM = new ArrayList<String>();
		List<String> FIT20502EZSUM = new ArrayList<String>();
		List<String> FIT20502FZSUM = new ArrayList<String>();
		List<String> FIT20503AZSUM = new ArrayList<String>();
		List<String> FIT20503BZSUM = new ArrayList<String>();
		List<String> FIT20503CZSUM = new ArrayList<String>();
		List<String> FIT20503DZSUM = new ArrayList<String>();
		List<String> FIT20503EZSUM = new ArrayList<String>();
		List<String> FIT20503FZSUM = new ArrayList<String>();
		int dataflg = 0;  //数据存在标识  0：不存在， 1：存在
		String timeCalssql = "select tp.parameter_value from PC_CD_TRIGGERED_PROCESS_T tp where tp.parameter_name='union_station_rpd_start_time'";
		String calcNum = "-16";
		List<Object[]> dataSet = sCLJYDao.searchSCLJYRPD(timeCalssql);
		if(dataSet != null && dataSet.size()>0){
			calcNum = dataSet.get(0) + "";
		}
		
		String stratime =DateBean.getDynamicTime(calcNum, reportDate, "0");
		String endtime =DateBean.getDynamicTime(calcNum, reportDate, "1");
		String sql = "select "+
					" a.FIT20501Z,a.FIT20502AZ,a.FIT20502BZ,a.FIT20502CZ,a.FIT20502DZ,a.FIT20502EZ,a.FIT20502FZ,a.FIT20503AZ,a.FIT20503BZ,a.FIT20503CZ,a.FIT20503DZ,a.FIT20503EZ,a.FIT20503FZ, a.rpd_u2_water_id,to_char(a.report_time,'YYYY-MM-DD HH24:MI:SS')as report_time"+
					" from PC_RPD_U2_WATER_T A where 1=1 ";
			sql += " and a.report_time between TO_DATE('"+stratime+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+endtime+"','YYYY-MM-DD HH24:MI:SS') order by a.report_time";
		Object[] ccsclTab = null;
		List<Object[]> ccscls = sCLJYDao.searchSCLJYRPD(sql);	
		String[][] dates = DateBean.getReportTime("time", reportDate);
		if(ccscls != null && ccscls.size()>0){
			for (int i = 0; i < dates.length; i++) {
				String[] dateList = dates[i];
				obj = new JSONObject();				
				if (i == 2) {
					 JSONObject oobj =  (JSONObject) arr.get(5);
					obj.put("RPD_U2_WATER_ID", "");
					obj.put("REPORT_TIME", "班累积");
					obj.put("FIT20501Z", "");
					obj.put("OTHER", "/");
					String[] argValue = {"FIT20502AZ","FIT20502BZ","FIT20502CZ","FIT20502DZ",
							"FIT20502EZ","FIT20502FZ","FIT20503AZ","FIT20503BZ","FIT20503CZ",
							"FIT20503DZ","FIT20503EZ","FIT20503FZ"};
					for (int j = 1; j <= argValue.length; j++) {
						if (ccscls.size() < 9) {
							obj.put(argValue[j - 1], "");
							continue; 
						}
						if (ccscls.get(8)[j] != null) {
							obj.put(argValue[j - 1], CommonsUtil.getRegulation0(ccscls.get(8)[j], ccscls.get(4)[j]));
						}else{
							obj.put(argValue[j - 1], "");
						}
					}
//					if(ccscls.size()>=8){
//						obj.put("FIT20502AZ",   CommonsUtil.getRegulation0(ccscls.get(8)[1], ccscls.get(4)[1]));
//					}
//						obj.put("FIT20502BZ",  CommonsUtil.getRegulation0(ccscls.get(8)[2], ccscls.get(4)[2]));
//						obj.put("FIT20502CZ",  CommonsUtil.getRegulation0(ccscls.get(8)[3], ccscls.get(4)[3]));
//						obj.put("FIT20502DZ",  CommonsUtil.getRegulation0(ccscls.get(8)[4], ccscls.get(4)[4]));
//						obj.put("FIT20502EZ",  CommonsUtil.getRegulation0(ccscls.get(8)[5], ccscls.get(4)[5]));
//						obj.put("FIT20502FZ",  CommonsUtil.getRegulation0(ccscls.get(8)[6], ccscls.get(4)[6]));
//						obj.put("FIT20503AZ",  CommonsUtil.getRegulation0(ccscls.get(8)[7], ccscls.get(4)[7]));
//						obj.put("FIT20503BZ",  CommonsUtil.getRegulation0(ccscls.get(8)[8], ccscls.get(4)[8]));
//						obj.put("FIT20503CZ",  CommonsUtil.getRegulation0(ccscls.get(8)[9], ccscls.get(4)[9]));
//						obj.put("FIT20503DZ",  CommonsUtil.getRegulation0(ccscls.get(8)[10], ccscls.get(4)[10]));
//						obj.put("FIT20503EZ",  CommonsUtil.getRegulation0(ccscls.get(8)[11], ccscls.get(4)[11]));
//						obj.put("FIT20503FZ",  CommonsUtil.getRegulation0(ccscls.get(8)[12], ccscls.get(4)[12]));
//					}else{
//						obj.put("FIT20502AZ",   CommonsUtil.getRegulation0(0, ccscls.get(4)[1]));
//						obj.put("FIT20502BZ",  CommonsUtil.getRegulation0(0, ccscls.get(4)[2]));
//						obj.put("FIT20502CZ",  CommonsUtil.getRegulation0(0, ccscls.get(4)[3]));
//						obj.put("FIT20502DZ",  CommonsUtil.getRegulation0(0, ccscls.get(4)[4]));
//						obj.put("FIT20502EZ",  CommonsUtil.getRegulation0(0, ccscls.get(4)[5]));
//						obj.put("FIT20502FZ",  CommonsUtil.getRegulation0(0, ccscls.get(4)[6]));
//						obj.put("FIT20503AZ",  CommonsUtil.getRegulation0(0, ccscls.get(4)[7]));
//						obj.put("FIT20503BZ",  CommonsUtil.getRegulation0(0, ccscls.get(4)[8]));
//						obj.put("FIT20503CZ",  CommonsUtil.getRegulation0(0, ccscls.get(4)[9]));
//						obj.put("FIT20503DZ",  CommonsUtil.getRegulation0(0, ccscls.get(4)[10]));
//						obj.put("FIT20503EZ",  CommonsUtil.getRegulation0(0, ccscls.get(4)[11]));
//						obj.put("FIT20503FZ",  CommonsUtil.getRegulation0(0, ccscls.get(4)[12]));
//					}
					
				}else{
					FIT20501ZTemp = CommonsUtil.getBanSumData0(FIT20501ZSUM);
					FIT20502AZTemp = CommonsUtil.getBanSumData0(FIT20502AZSUM);
					FIT20502BZTemp = CommonsUtil.getBanSumData0(FIT20502BZSUM);
					FIT20502CZTemp = CommonsUtil.getBanSumData0(FIT20502CZSUM);
					FIT20502DZTemp = CommonsUtil.getBanSumData0(FIT20502DZSUM);
					FIT20502EZTemp = CommonsUtil.getBanSumData0(FIT20502EZSUM);
					FIT20502FZTemp = CommonsUtil.getBanSumData0(FIT20502FZSUM);
					FIT20503AZTemp = CommonsUtil.getBanSumData0(FIT20503AZSUM);
					FIT20503BZTemp = CommonsUtil.getBanSumData0(FIT20503BZSUM);
					FIT20503CZTemp = CommonsUtil.getBanSumData0(FIT20503CZSUM);
					FIT20503DZTemp = CommonsUtil.getBanSumData0(FIT20503DZSUM);
					FIT20503EZTemp = CommonsUtil.getBanSumData0(FIT20503EZSUM);
					FIT20503FZTemp = CommonsUtil.getBanSumData0(FIT20503FZSUM);
					
					obj.put("RPD_U2_WATER_ID", "");
					obj.put("REPORT_TIME", "班累积");
					obj.put("FIT20501Z", "");
					//obj.put("FIT20501Z", FIT20501ZTemp);
					obj.put("OTHER", "/");
					obj.put("FIT20502AZ", FIT20502AZTemp);
					obj.put("FIT20502BZ", FIT20502BZTemp);
					obj.put("FIT20502CZ", FIT20502CZTemp);
					
					obj.put("FIT20502DZ", FIT20502DZTemp);
					obj.put("FIT20502EZ", FIT20502EZTemp);
					obj.put("FIT20502FZ", FIT20502FZTemp);
					obj.put("FIT20503AZ", FIT20503AZTemp);
					obj.put("FIT20503BZ", FIT20503BZTemp);
					obj.put("FIT20503CZ", FIT20503CZTemp);
					obj.put("FIT20503DZ", FIT20503DZTemp);
					obj.put("FIT20503EZ", FIT20503EZTemp);
					obj.put("FIT20503FZ", FIT20503FZTemp);
				}
				arr.add(obj);
				
				FIT20501ZSUM = new ArrayList<String>();
				FIT20502AZSUM = new ArrayList<String>();
				FIT20502BZSUM = new ArrayList<String>();
				FIT20502CZSUM = new ArrayList<String>();
				FIT20502DZSUM = new ArrayList<String>();
				FIT20502EZSUM = new ArrayList<String>();
				FIT20502FZSUM = new ArrayList<String>();
				FIT20503AZSUM = new ArrayList<String>();
				FIT20503BZSUM = new ArrayList<String>();
				FIT20503CZSUM = new ArrayList<String>();
				FIT20503DZSUM = new ArrayList<String>();
				FIT20503EZSUM = new ArrayList<String>();
				FIT20503FZSUM = new ArrayList<String>();
				for(String data:dateList){
					ccsclTab = null;
					dataflg = 0;
					for(Object[] ccscl:ccscls){
						obj = new JSONObject();
						
						if(data.toString().equals(ccscl[14].toString())){
							dataflg = 1;
							ccsclTab = ccscl;
							//添加班累积值
							if(ccscl[0] != null && !"".equals(ccscl[0].toString())){
								FIT20501ZSUM.add(ccscl[0].toString());
							}else{
								FIT20501ZSUM.add("");
							}
							if(ccscl[1] != null && !"".equals(ccscl[1].toString())){
								FIT20502AZSUM.add(ccscl[1].toString());
							}else{
								FIT20502AZSUM.add("");
							}
							if(ccscl[2] != null && !"".equals(ccscl[2].toString())){
								FIT20502BZSUM.add(ccscl[2].toString());
							}else{
								FIT20502BZSUM.add("");
							}
							if(ccscl[3] != null && !"".equals(ccscl[3].toString())){
								FIT20502CZSUM.add(ccscl[3].toString());
							}else{
								FIT20502CZSUM.add("");
							}
							
							if(ccscl[4] != null && !"".equals(ccscl[4].toString())){
								FIT20502DZSUM.add(ccscl[4].toString());
							}else{
								FIT20502DZSUM.add("");
							}
							if(ccscl[5] != null && !"".equals(ccscl[5].toString())){
								FIT20502EZSUM.add(ccscl[5].toString());
							}else{
								FIT20502EZSUM.add("");
							}
							if(ccscl[6] != null && !"".equals(ccscl[6].toString())){
								FIT20502FZSUM.add(ccscl[6].toString());
							}else{
								FIT20502FZSUM.add("");
							}
							if(ccscl[7] != null && !"".equals(ccscl[7].toString())){
								FIT20503AZSUM.add(ccscl[7].toString());
							}else{
								FIT20503AZSUM.add("");
							}
							
							if(ccscl[8] != null && !"".equals(ccscl[8].toString())){
								FIT20503BZSUM.add(ccscl[8].toString());
							}else{
								FIT20503BZSUM.add("");
							}
							if(ccscl[9] != null && !"".equals(ccscl[9].toString())){
								FIT20503CZSUM.add(ccscl[9].toString());
							}else{
								FIT20503CZSUM.add("");
							}
							if(ccscl[10] != null && !"".equals(ccscl[10].toString())){
								FIT20503DZSUM.add(ccscl[10].toString());
							}else{
								FIT20503DZSUM.add("");
							}
							if(ccscl[11] != null && !"".equals(ccscl[11].toString())){
								FIT20503EZSUM.add(ccscl[11].toString());
							}else{
								FIT20503EZSUM.add("");
							}
							if(ccscl[12] != null && !"".equals(ccscl[12].toString())){
								FIT20503FZSUM.add(ccscl[12].toString());
							}else{
								FIT20503FZSUM.add("");
							}
						}
						
					}
					if(dataflg == 1){
						obj.put("RPD_U2_WATER_ID", ccsclTab[13]);
						obj.put("REPORT_TIME", data.substring(11, 16));
						if(ccsclTab[0] != null){
							obj.put("FIT20501Z", CommonsUtil.getNotOneData0(ccsclTab[0]));
							FIT20501Z.add(ccsclTab[0].toString());
						}else{
							obj.put("FIT20501Z", "");
							FIT20501Z.add("");
						}
						obj.put("OTHER", "0");
						if(ccsclTab[1]!=null){
							obj.put("FIT20502AZ", CommonsUtil.getNotOneData0(ccsclTab[1]));
							FIT20502AZ.add(ccsclTab[1].toString());
						}else{
							obj.put("FIT20502AZ", "");
							FIT20502AZ.add("");
						}
						if(ccsclTab[2]!=null){
							obj.put("FIT20502BZ", CommonsUtil.getNotOneData0(ccsclTab[2]));
							FIT20502BZ.add(ccsclTab[2].toString());
						}else{
							obj.put("FIT20502BZ", "");
							FIT20502BZ.add("");
						}
						if(ccsclTab[3]!=null){
							obj.put("FIT20502CZ", CommonsUtil.getNotOneData0(ccsclTab[3]));
							FIT20502CZ.add(ccsclTab[3].toString());
						}else{
							obj.put("FIT20502CZ", "");
							FIT20502CZ.add("");
						}
						if(ccsclTab[4]!=null){
							obj.put("FIT20502DZ", CommonsUtil.getNotOneData0(ccsclTab[4]));
							FIT20502DZ.add(ccsclTab[4].toString());
						}else{
							obj.put("FIT20502DZ", "");
							FIT20502DZ.add("");
						}
						if(ccsclTab[5]!=null){
							obj.put("FIT20502EZ", CommonsUtil.getNotOneData0(ccsclTab[5]));
							FIT20502EZ.add(ccsclTab[5].toString());
						}else{
							obj.put("FIT20502EZ", "");
							FIT20502EZ.add("");
						}
						if(ccsclTab[6]!=null){
							obj.put("FIT20502FZ", CommonsUtil.getNotOneData0(ccsclTab[6]));
							FIT20502FZ.add(ccsclTab[6].toString());
						}else{
							obj.put("FIT20502FZ", "");
							FIT20502FZ.add("");
						}
						if(ccsclTab[7]!=null){
							obj.put("FIT20503AZ", CommonsUtil.getNotOneData0(ccsclTab[7]));
							FIT20503AZ.add(ccsclTab[7].toString());
						}else{
							obj.put("FIT20503AZ", "");
							FIT20503AZ.add("");
						}
						if(ccsclTab[8]!=null){
							obj.put("FIT20503BZ", CommonsUtil.getNotOneData0(ccsclTab[8]));
							FIT20503BZ.add(ccsclTab[8].toString());
						}else{
							obj.put("FIT20503BZ", "");
							FIT20503BZ.add("");
						}
						if(ccsclTab[9]!=null){
							obj.put("FIT20503CZ", CommonsUtil.getNotOneData0(ccsclTab[9]));
							FIT20503CZ.add(ccsclTab[9].toString());
						}else{
							obj.put("FIT20503CZ", "");
							FIT20503CZ.add("");
						}
						
						if(ccsclTab[10]!=null){
							obj.put("FIT20503DZ", CommonsUtil.getNotOneData0(ccsclTab[10]));
							FIT20503DZ.add(ccsclTab[10].toString());
						}else{
							obj.put("FIT20503DZ", "");
							FIT20503DZ.add("");
						}
						if(ccsclTab[11]!=null){
							obj.put("FIT20503EZ", CommonsUtil.getNotOneData0(ccsclTab[11]));
							FIT20503EZ.add(ccsclTab[11].toString());
						}else{
							obj.put("FIT20503EZ", "");
							FIT20503EZ.add("");
						}
						if(ccsclTab[12]!=null){
							obj.put("FIT20503FZ", CommonsUtil.getNotOneData0(ccsclTab[12]));
							FIT20503FZ.add(ccsclTab[12].toString());
						}else{
							obj.put("FIT20503FZ", "");
							FIT20503FZ.add("");
						}
						
					}else{
						obj.put("RPD_U2_WATER_ID", "");
						obj.put("REPORT_TIME", data.substring(11, 16));
						obj.put("FIT20501Z", "");
						obj.put("OTHER", "");
						obj.put("FIT20502AZ", "");
						obj.put("FIT20502BZ", "");
						obj.put("FIT20502CZ","");
						obj.put("FIT20502DZ", "");
						
						obj.put("FIT20502EZ", "");
						obj.put("FIT20502FZ", "");
						obj.put("FIT20503AZ", "");
						obj.put("FIT20503BZ", "");
						obj.put("FIT20503CZ", "");
						obj.put("FIT20503DZ", "");
						obj.put("FIT20503EZ", "");
						obj.put("FIT20503FZ","");
						
						FIT20501Z.add("");
						FIT20502AZ.add("");
						FIT20502BZ.add("");
						FIT20502CZ.add("");
						FIT20502DZ.add("");
						FIT20502EZ.add("");
						FIT20502FZ.add("");
						FIT20503AZ.add("");
						FIT20503BZ.add("");
						FIT20503CZ.add("");
						FIT20503DZ.add("");
						FIT20503EZ.add("");
						FIT20503FZ.add("");
						
						FIT20501ZSUM.add("");
						FIT20502AZSUM.add("");
						FIT20502BZSUM.add("");
						FIT20502CZSUM.add("");
						FIT20502DZSUM.add("");
						FIT20502EZSUM.add("");
						FIT20502FZSUM.add("");
						FIT20503AZSUM.add("");
						FIT20503BZSUM.add("");
						FIT20503CZSUM.add("");
						FIT20503DZSUM.add("");
						FIT20503EZSUM.add("");
						FIT20503FZSUM.add("");
						
					}
					arr.add(obj);
				}
			}
			
			FIT20501ZTemp = CommonsUtil.getSumData00(FIT20501Z);
			FIT20502AZTemp = CommonsUtil.getSumData00(FIT20502AZ);
			FIT20502BZTemp = CommonsUtil.getSumData00(FIT20502BZ);
			FIT20502CZTemp = CommonsUtil.getSumData00(FIT20502CZ);
			FIT20502DZTemp = CommonsUtil.getSumData00(FIT20502DZ);
			FIT20502EZTemp = CommonsUtil.getSumData00(FIT20502EZ);
			FIT20502FZTemp = CommonsUtil.getSumData00(FIT20502FZ);
			FIT20503AZTemp = CommonsUtil.getSumData00(FIT20503AZ);
			FIT20503BZTemp = CommonsUtil.getSumData00(FIT20503BZ);
			FIT20503CZTemp = CommonsUtil.getSumData00(FIT20503CZ);
			FIT20503DZTemp = CommonsUtil.getSumData00(FIT20503DZ);
			FIT20503EZTemp = CommonsUtil.getSumData00(FIT20503EZ);
			FIT20503FZTemp = CommonsUtil.getSumData00(FIT20503FZ);
			
			obj.put("RPD_U2_WATER_ID", "");
			obj.put("REPORT_TIME", "班累积");
			obj.put("FIT20501Z", "");
			//obj.put("FIT20501Z", FIT20501ZTemp);
			obj.put("OTHER", "/");
			obj.put("FIT20502AZ", FIT20502AZTemp);
			obj.put("FIT20502BZ", FIT20502BZTemp);
			obj.put("FIT20502CZ", FIT20502CZTemp);
			
			obj.put("FIT20502DZ", FIT20502DZTemp);
			obj.put("FIT20502EZ", FIT20502EZTemp);
			obj.put("FIT20502FZ", FIT20502FZTemp);
			obj.put("FIT20503AZ", FIT20503AZTemp);
			obj.put("FIT20503BZ", FIT20503BZTemp);
			obj.put("FIT20503CZ", FIT20503CZTemp);
			obj.put("FIT20503DZ", FIT20503DZTemp);
			obj.put("FIT20503EZ", FIT20503EZTemp);
			obj.put("FIT20503FZ", FIT20503FZTemp);
			
//			FIT20501Z.add(FIT20501ZTemp);
//			FIT20502AZ.add(FIT20502AZTemp);
//			FIT20502BZ.add(FIT20502BZTemp);
//			FIT20502CZ.add(FIT20502CZTemp);
//			FIT20502DZ.add(FIT20502DZTemp);
//			FIT20502EZ.add(FIT20502EZTemp);
//			FIT20502FZ.add(FIT20502FZTemp);
//			FIT20503AZ.add(FIT20503AZTemp);
//			FIT20503BZ.add(FIT20503BZTemp);
//			FIT20503CZ.add(FIT20503CZTemp);
//			FIT20503DZ.add(FIT20503DZTemp);
//			FIT20503EZ.add(FIT20503EZTemp);
//			FIT20503FZ.add(FIT20503FZTemp);
			
			arr.add(obj);
		}else{
			for(String[] dateList:dates){
				obj = new JSONObject();
				for(String data:dateList){
					obj = new JSONObject();
					obj.put("RPD_U2_WATER_ID", "");
					obj.put("REPORT_TIME", data.substring(11, 16));
					obj.put("FIT20501Z", "");
					obj.put("OTHER", "");
					obj.put("FIT20502AZ", "");
					obj.put("FIT20502BZ", "");
					obj.put("FIT20502CZ","");
					obj.put("FIT20502DZ", "");
					
					obj.put("FIT20502EZ", "");
					obj.put("FIT20502FZ", "");
					obj.put("FIT20503AZ", "");
					obj.put("FIT20503BZ", "");
					obj.put("FIT20503CZ", "");
					obj.put("FIT20503DZ", "");
					obj.put("FIT20503EZ", "");
					obj.put("FIT20503FZ","");
					arr.add(obj);
				}
				obj.put("RPD_U2_WATER_ID", "");
				obj.put("REPORT_TIME", "班累积");
				obj.put("FIT20501Z", "");
				obj.put("OTHER", "/");
				obj.put("FIT20502AZ", "");
				obj.put("FIT20502BZ", "");
				obj.put("FIT20502CZ", "");
				
				obj.put("FIT20502DZ", "");
				obj.put("FIT20502EZ", "");
				obj.put("FIT20502FZ", "");
				obj.put("FIT20503AZ", "");
				obj.put("FIT20503BZ", "");
				obj.put("FIT20503CZ", "");
				obj.put("FIT20503DZ", "");
				obj.put("FIT20503EZ", "");
				obj.put("FIT20503FZ", "");
				arr.add(obj);
			}
		}
		if(ccscls != null && ccscls.size()>0){
			arr.remove(0);
//			FIT20501Z.remove(0);
//			FIT20502AZ.remove(0);
//			FIT20502BZ.remove(0);
//			FIT20502CZ.remove(0);
//			FIT20502DZ.remove(0);
//			FIT20502EZ.remove(0);
//			FIT20502FZ.remove(0);
//			FIT20503AZ.remove(0);
//			FIT20503BZ.remove(0);
//			FIT20503CZ.remove(0);
//			FIT20503DZ.remove(0);
//			FIT20503EZ.remove(0);
//			FIT20503FZ.remove(0);
			
			obj = new JSONObject();
			obj.put("RPD_U2_WATER_ID", "");
			obj.put("REPORT_TIME", "合计");
			obj.put("FIT20501Z", "");
			//obj.put("FIT20501Z", CommonsUtil.getSumData0(FIT20501Z));
			obj.put("OTHER", "/");
			obj.put("FIT20502AZ", CommonsUtil.getSumData0(FIT20502AZ));
			obj.put("FIT20502BZ", CommonsUtil.getSumData0(FIT20502BZ));
			obj.put("FIT20502CZ", CommonsUtil.getSumData0(FIT20502CZ));
			obj.put("FIT20502DZ", CommonsUtil.getSumData0(FIT20502DZ));
			
			obj.put("FIT20502EZ", CommonsUtil.getSumData0(FIT20502EZ));
			obj.put("FIT20502FZ", CommonsUtil.getSumData0(FIT20502FZ));
			obj.put("FIT20503AZ", CommonsUtil.getSumData0(FIT20503AZ));
			obj.put("FIT20503BZ", CommonsUtil.getSumData0(FIT20503BZ));
			obj.put("FIT20503CZ", CommonsUtil.getSumData0(FIT20503CZ));
			obj.put("FIT20503DZ", CommonsUtil.getSumData0(FIT20503DZ));
			obj.put("FIT20503EZ", CommonsUtil.getSumData0(FIT20503EZ));
			obj.put("FIT20503FZ", CommonsUtil.getSumData0(FIT20503FZ));
			
			arr.add(obj);
		}else{
			obj = new JSONObject();
			obj.put("RPD_U2_WATER_ID", "");
			obj.put("REPORT_TIME", "合计");
			obj.put("FIT20501Z", "");
			obj.put("OTHER", "/");
			obj.put("FIT20502AZ", "");
			obj.put("FIT20502BZ", "");
			obj.put("FIT20502CZ", "");
			obj.put("FIT20502DZ", "");
			
			obj.put("FIT20502EZ", "");
			obj.put("FIT20502FZ", "");
			obj.put("FIT20503AZ", "");
			obj.put("FIT20503BZ", "");
			obj.put("FIT20503CZ", "");
			obj.put("FIT20503DZ", "");
			obj.put("FIT20503EZ", "");
			obj.put("FIT20503FZ", "");
			
			arr.add(obj);
		}
		
		return arr;
	}
//	public boolean addOrUpdateSagddRPD(PcRpdWellSagddT prws)throws Exception{
//		return sCLJYDao.addOrUpdateSagddRPD(prws);
//	}
	
	@Override
	public List<String> dayreport() throws Exception {
		return sCLJYDao.dayreport();
	}

	public List<Object[]> searchU2_WATER(String rq,String fields) throws Exception {
		String timeCalssql = "select tp.parameter_value from PC_CD_TRIGGERED_PROCESS_T tp where tp.parameter_name='union_station_rpd_start_time'";
		String calcNum = "-16";
		List<Object[]> dataSet = sCLJYDao.queryInfo(timeCalssql);
		if(dataSet != null && dataSet.size()>0){
			calcNum = dataSet.get(0) + "";
		}
		String stratime =DateBean.getDynamicTime(calcNum, rq, "0");
		String endtime =DateBean.getDynamicTime(calcNum, rq, "1");
		String sql = "select " + fields + " from PC_RPD_U2_WATER_T u where report_time between TO_DATE('"+stratime+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+endtime+"','YYYY-MM-DD HH24:MI:SS') order by u.report_time";
		List<Object[]> yyList = sCLJYDao.queryInfo(sql);
		return yyList;
	}
	
	public String searchFlagDate(String rq) throws Exception {
		String timeCalssql = "select tp.parameter_value from PC_CD_TRIGGERED_PROCESS_T tp where tp.parameter_name='union_station_rpd_modify_time'";
		String calcNum = "-16";
		List<Object[]> dataSet = sCLJYDao.queryInfo(timeCalssql);
		if(dataSet != null && dataSet.size()>0){
			calcNum = dataSet.get(0) + "";
		}
		String stratime =DateBean.getDynamicTime(calcNum, rq, "0");
		String endtime =DateBean.getDynamicTime(calcNum, rq, "1");
		return stratime + "&" + endtime;
	}
}
