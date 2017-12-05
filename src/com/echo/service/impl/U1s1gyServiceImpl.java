package com.echo.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.dao.U1s1gyDao;
import com.echo.dto.PcRpdU1WaterAutoT;
import com.echo.dto.PcRpdU2TSJY;
import com.echo.dto.PcRpdU2TSWS;
import com.echo.dto.PcRpdU2TSZH;
import com.echo.dto.PcRpdU2WaterAutoT;
import com.echo.service.U1s1gyService;
import com.echo.util.CommonsUtil;
import com.echo.util.DateBean;


public class U1s1gyServiceImpl implements U1s1gyService{
   private U1s1gyDao u1s1gyDao;
	
	public void setU1s1gyDao(U1s1gyDao u1s1gyDao) {
		this.u1s1gyDao = u1s1gyDao;
	}
	
	@Override
	public JSONArray searchU1s1gy(String reportDate) throws Exception {

		JSONObject obj = new JSONObject();
		JSONArray arr = new JSONArray();
		
//		String ft1001= "";
//		String hssllj= "";
//		String ft1005= "";
//		String ft1004= "";
//		String ft1003= "";
//		String ft1002= "";
//		String ft1016= "";
//		String rgsdlj= "";
//		String fit801= "";
//		String lt1001= "";
//		String lt1002= "";
//		String lt1007= "";
//		String lt1012= "";
//		String lt1011= "";
//		
//		List<String> ft1001sum= new ArrayList<String>();
//		List<String> hsslljsum= new ArrayList<String>();
//		List<String> ft1005sum= new ArrayList<String>();
//		List<String> ft1004sum= new ArrayList<String>();
//		List<String> ft1003sum= new ArrayList<String>();
//		List<String> ft1002sum= new ArrayList<String>();
//		List<String> ft1016sum= new ArrayList<String>();
//		List<String> rgsdljsum= new ArrayList<String>();
//		List<String> fit801sum= new ArrayList<String>();
//		List<String> lt1001sum= new ArrayList<String>();
//		List<String> lt1002sum= new ArrayList<String>();
//		List<String> lt1007sum= new ArrayList<String>();
//		List<String> lt1012sum= new ArrayList<String>();
//		List<String> lt1011sum= new ArrayList<String>();
		
		Object[] u2oiltable = null;
		int dataflg  =0; //数据存在表示 0：不存在； 1： 存在
		String timeCalssql = "select tp.parameter_value from PC_CD_TRIGGERED_PROCESS_T tp where tp.parameter_name='union_station_rpd_start_time'";
		String calcNum = "-16";
		List<Object[]> dataSet = u1s1gyDao.searchU1s1gy(timeCalssql);
		
		if(dataSet !=null && dataSet.size()>0){
			calcNum = dataSet.get(0) + "";
		}
		String stratime =DateBean.getDynamicTime(calcNum, reportDate, "0");
		String endtime =DateBean.getDynamicTime(calcNum, reportDate, "1");
		
		String sql = "select RPD_U1_WATER_AUTO_ID ,to_char(a.BBSJ,'YYYY-MM-DD HH24:MI:SS')as BBSJ, ft1001, S1HSSLLJ,　 ft1005,  ft1004,  ft1003 , ft1002,  ft1016, S1RGSDLJ,　 fit801 ,lt1001, lt1002, lt1007, lt1012 ,lt1011 from  PC_RPD_U1_WATER_AUTO_T a where 1=1";
		sql += " and a.BBSJ between TO_DATE('"+stratime+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+endtime+"','YYYY-MM-DD HH24:MI:SS') order by a.BBSJ";
		
		List<Object[]> U1S1S = u1s1gyDao.searchU1s1gy(sql);
		String[][] dates = DateBean.getReportTime("time", reportDate);
		
		if(U1S1S !=null && U1S1S.size()>0){
		
			for(String[] dateList:dates){
			obj = new JSONObject();
//				 ft1001sum= new ArrayList<String>();
//				 hsslljsum= new ArrayList<String>();
//				 ft1005sum= new ArrayList<String>();
//				 ft1004sum= new ArrayList<String>();
//				 ft1003sum= new ArrayList<String>();
//				 ft1002sum= new ArrayList<String>();
//				 ft1016sum= new ArrayList<String>();
//				 rgsdljsum= new ArrayList<String>();
//				 fit801sum= new ArrayList<String>();
//				 lt1001sum= new ArrayList<String>();
//				 lt1002sum= new ArrayList<String>();
//				 lt1007sum= new ArrayList<String>();
//				 lt1012sum= new ArrayList<String>();
//				 lt1011sum= new ArrayList<String>();
					 
					 for(String data:dateList){
							System.out.println(data);
							u2oiltable = null;
							dataflg = 0;
							for(Object[] U1S1SS:U1S1S){
								obj = new JSONObject();
								
								if(data.toString().equals(U1S1SS[1].toString())){
									dataflg = 1;
									u2oiltable = U1S1SS;
//								if(U1S1SS[2] != null && !"".equals(U1S1SS[2].toString())){
//									ft1001sum.add(U1S1SS[2].toString());
//								}else{
//									ft1001sum.add("");
//								}
//								if(U1S1SS[3] != null && !"".equals(U1S1SS[3].toString())){
//									hsslljsum.add(U1S1SS[3].toString());
//								}else{
//									hsslljsum.add("");
//								}
//								if(U1S1SS[4] != null && !"".equals(U1S1SS[4].toString())){
//									ft1005sum.add(U1S1SS[4].toString());
//								}else{
//									ft1005sum.add("");
//								}
//								if(U1S1SS[5] != null && !"".equals(U1S1SS[5].toString())){
//									ft1004sum.add(U1S1SS[5].toString());
//								}else{
//									ft1004sum.add("");
//								}
//								
//								if(U1S1SS[6] != null && !"".equals(U1S1SS[6].toString())){
//									ft1003sum.add(U1S1SS[6].toString());
//								}else{
//									ft1003sum.add("");
//								}
//								if(U1S1SS[7] != null && !"".equals(U1S1SS[7].toString())){
//									ft1002sum.add(U1S1SS[7].toString());
//								}else{
//									ft1002sum.add("");
//								}
//								if(U1S1SS[8] != null && !"".equals(U1S1SS[8].toString())){
//									ft1016sum.add(U1S1SS[8].toString());
//								}else{
//									ft1016sum.add("");
//								}
//								if(U1S1SS[9] != null && !"".equals(U1S1SS[9].toString())){
//									rgsdljsum.add(U1S1SS[9].toString());
//								}else{
//									rgsdljsum.add("");
//								}
//								
//								if(U1S1SS[10] != null && !"".equals(U1S1SS[10].toString())){
//									fit801sum.add(U1S1SS[10].toString());
//								}else{
//									fit801sum.add("");
//								}
//								if(U1S1SS[11] != null && !"".equals(U1S1SS[11].toString())){
//									lt1001sum.add(U1S1SS[11].toString());
//								}else{
//									lt1001sum.add("");
//								}
//								if(U1S1SS[12] != null && !"".equals(U1S1SS[12].toString())){
//									lt1002sum.add(U1S1SS[12].toString());
//								}else{
//									lt1002sum.add("");
//								}
//								if(U1S1SS[13] != null && !"".equals(U1S1SS[13].toString())){
//									lt1007sum.add(U1S1SS[13].toString());
//								}else{
//									lt1007sum.add("");
//								}
//								if(U1S1SS[14] != null && !"".equals(U1S1SS[14].toString())){
//									lt1012sum.add(U1S1SS[14].toString());
//								}else{
//									lt1012sum.add("");
//								}
//								if(U1S1SS[15] != null && !"".equals(U1S1SS[15].toString())){
//									lt1011sum.add(U1S1SS[15].toString());
//								}else{
//									lt1011sum.add("");
//								}
								
							}
						}
					 if(dataflg == 1){
						 		
							obj.put("RPD_U1_WATER_AUTO_ID", u2oiltable[0]);
							obj.put("rq", data.substring(11, 16));
							if(u2oiltable[2] !=null && !"".equals(u2oiltable[2]) && !u2oiltable[2].equals("undefined")){
								obj.put("ft1001",CommonsUtil.getNotTwoData(u2oiltable[2]));
							}else{
								obj.put("ft1001", "");
								}
							if(u2oiltable[3] !=null && !"".equals(u2oiltable[3]) && !u2oiltable[3].equals("undefined")){
								obj.put("hssllj", CommonsUtil.getNotTwoData(u2oiltable[3]));
								
							}else{
								obj.put("hssllj", "");
							}
							
							if(u2oiltable[4] !=null){
								obj.put("ft1005", CommonsUtil.getNotTwoData(u2oiltable[4]));
							}else{
								obj.put("ft1005", "");
							}
							if(u2oiltable[5] !=null){
								obj.put("ft1004", CommonsUtil.getNotTwoData(u2oiltable[5]));
							}else{
								obj.put("ft1004","");
							}
							if(u2oiltable[6] !=null){
								obj.put("ft1003", CommonsUtil.getNotTwoData(u2oiltable[6]));
							}else{
								obj.put("ft1003","");
							}
							if(u2oiltable[7] !=null){
								obj.put("ft1002", CommonsUtil.getNotTwoData(u2oiltable[7]));
							}else{
								obj.put("ft1002", "");
							}
							if(u2oiltable[8] !=null){
								obj.put("ft1016", CommonsUtil.getNotTwoData(u2oiltable[8]));
							}else{
								obj.put("ft1016", "");
							}
							if(u2oiltable[9] !=null){
								obj.put("rgsdlj", CommonsUtil.getNotTwoData(u2oiltable[9]));
							}else{
								obj.put("rgsdlj", "");
							}
							if(u2oiltable[10] !=null){
								obj.put("fit801", CommonsUtil.getNotTwoData(u2oiltable[10]));
							}else{
								obj.put("fit801", "");
							}
							if(u2oiltable[11] !=null){
								obj.put("lt1001", CommonsUtil.getNotTwoData(u2oiltable[11]));
							}else{
								obj.put("lt1001", "");
							}
							if(u2oiltable[12] !=null && !"".equals(u2oiltable[12]) && !u2oiltable[12].equals("undefined")){
								obj.put("lt1002", CommonsUtil.getNotTwoData(u2oiltable[12]));
							}else{
								obj.put("lt1002", "");
							}
							if( u2oiltable[13] !=null && !"".equals( u2oiltable[13]) && ! u2oiltable[13].equals("undefined")){
								obj.put("lt1007", CommonsUtil.getNotTwoData(u2oiltable[13]));
							}else{
								obj.put("lt1007", "");
							}
							
							if( u2oiltable[14] !=null && !"".equals( u2oiltable[14]) && ! u2oiltable[14].equals("undefined")){
								obj.put("lt1012", CommonsUtil.getNotTwoData(u2oiltable[14]));
							}else{
								obj.put("lt1012", "");
							}
							if( u2oiltable[15] !=null && !"".equals( u2oiltable[15]) && ! u2oiltable[15].equals("undefined")){
								obj.put("lt1011", CommonsUtil.getNotTwoData(u2oiltable[15]));
							}else{
								obj.put("lt1011", "");
							}
					 	}else{
					 		obj.put("RPD_U1_WATER_AUTO_ID","");
							obj.put("rq",data.substring(11, 16));
							obj.put("ft1001","");
							obj.put("hssllj","");
							obj.put("ft1005","");
							obj.put("ft1004","");
							obj.put("ft1003","");
							obj.put("ft1002","");
							obj.put("ft1016","");
							obj.put("rgsdlj","");
							obj.put("fit801","");
							obj.put("lt1001","");
							obj.put("lt1002","");
							obj.put("lt1007","");
							obj.put("lt1012","");
							obj.put("lt1011","");
					 }
					 arr.add(obj);
				 }
			}
		}else{
			for(String[] dateList:dates){
				obj = new JSONObject();
				for(String data:dateList){
					obj = new JSONObject();
					obj.put("RPD_U1_WATER_AUTO_ID","");
					obj.put("rq",data.substring(11, 16));
					obj.put("ft1001","");
					obj.put("hssllj","");
					obj.put("ft1005","");
					obj.put("ft1004","");
					obj.put("ft1003","");
					obj.put("ft1002","");
					obj.put("ft1016","");
					obj.put("rgsdlj","");
					obj.put("fit801","");
					obj.put("lt1001","");
					obj.put("lt1002","");
					obj.put("lt1007","");
					obj.put("lt1012","");
					obj.put("lt1011","");
					arr.add(obj);
				}
			}
		
		}
		//日累计
		obj.put("RPD_U1_WATER_AUTO_ID","");
		obj.put("rq","日累计（m3）");
		String[] argValue = {"ft1001","hssllj","ft1005","ft1004",
				"ft1003","ft1002","ft1016","rgsdlj","fit801"
				,"lt1001","lt1002","lt1007","lt1012","lt1011"};
		for (int j = 2; j <= argValue.length; j++) {
			if (U1S1S.size() < 13) {
				obj.put(argValue[j - 2], "");
				continue; 
			}
			if (U1S1S.get(0)[j] != null && U1S1S.get(12)[j] != null ) {
				obj.put(argValue[j-2], CommonsUtil.getRegulation0(U1S1S.get(12)[j], U1S1S.get(0)[j]));
			}else{
				obj.put(argValue[j - 2], "");
			}
		}
		arr.add(obj);
		return arr;
	
	}

	@Override
	public List<PcRpdU1WaterAutoT> SreachU1S1gy(String id, String name)throws Exception {
		List<PcRpdU1WaterAutoT> list = null;
		String hql ="from PcRpdU1WaterAutoT a  where 1=1";
		if(id!=null && !"".equals(id)){
		      hql +=" and a.rpdU1WaterAutoId = '"+id+"'";
		}
		if(name !=null && !"".equals(name)){
			hql=" and a.bbsj = '"+name+"'";
		}
		return u1s1gyDao.SreachU1S1gy(hql);
	}
	public List<PcRpdU2WaterAutoT> SreachU2S1gy(String id, String name)throws Exception {
		String hql ="from PcRpdU2WaterAutoT a  where 1=1";
		if(id!=null && !"".equals(id)){
		      hql +=" and a.rpdU2WaterAutoId = '"+id+"'";
		}
		if(name !=null && !"".equals(name)){
			hql=" and a.bbsj = '"+name+"'";
		}
		return u1s1gyDao.SreachU2S1gy(hql);
	}
	public List<PcRpdU2TSJY> SreachTSJY(String id, String name)throws Exception {
		String hql ="from PcRpdU2TSJY a  where 1=1";
		if(id!=null && !"".equals(id)){
		      hql +=" and a.rpdU2TSJYId = '"+id+"'";
		}
		if(name !=null && !"".equals(name)){
			hql=" and a.bbsj = '"+name+"'";
		}
		return u1s1gyDao.SreachTSJY(hql);
	}
	public List<PcRpdU2TSWS> SreachTSWS(String id, String name)throws Exception {
		String hql ="from PcRpdU2TSWS a  where 1=1";
		if(id!=null && !"".equals(id)){
		      hql +=" and a.rpdU2TSWSId = '"+id+"'";
		}
		if(name !=null && !"".equals(name)){
			hql=" and a.bbsj = '"+name+"'";
		}
		return u1s1gyDao.SreachTSWS(hql);
	}
	public List<PcRpdU2TSZH> SreachTSZH(String id, String name)throws Exception {
		String hql ="from PcRpdU2TSZH a  where 1=1";
		if(id!=null && !"".equals(id)){
		      hql +=" and a.rpdU2TSZHId = '"+id+"'";
		}
		if(name !=null && !"".equals(name)){
			hql=" and a.bbsj = '"+name+"'";
		}
		return u1s1gyDao.SreachTSZH(hql);
	}
	@Override
	public int searchCalcNum() throws Exception {
		// TODO Auto-generated method stub
		String timeCalssql = "select tp.parameter_value from PC_CD_TRIGGERED_PROCESS_T tp where tp.parameter_name='union_station_rpd_start_time'";
		String calcNum = "-16";
		List<Object[]> dataSet = u1s1gyDao.searchU1s1gy(timeCalssql);
		if(dataSet != null && dataSet.size()>0){
			calcNum = dataSet.get(0) + "";
		}
		return Integer.parseInt(calcNum);
	}

	@Override
	public boolean updateU1S1gy(PcRpdU1WaterAutoT us) throws Exception {
		return u1s1gyDao.updateU1S1gy(us);
	}
	
	public boolean updateU2S1gy(Object us) throws Exception {
		return u1s1gyDao.updateU2S1gy(us);
	}

	@Override
	public List<Object[]> searchExportU1S1(String txtDate, String sql) throws Exception {
			sql += " where 1=1   and to_char(v.bbsj,'yyyy.mm')='"+txtDate+"' order by  v.bbsj";
			List<Object[]> list = u1s1gyDao.searchU1s1gy(sql);
			return list;
	}
	
	//特二联脱水报表
	public JSONObject searchU2tscs1(String reportDate) throws Exception {

		JSONObject obj = new JSONObject();
		JSONArray arr = new JSONArray();
		JSONObject object = new JSONObject();
		double[] arr7 = new double[2];
		Object[] u2oiltable = null;
		int dataflg  =0; //数据存在表示 0：不存在； 1： 存在
		String timeCalssql = "select tp.parameter_value from PC_CD_TRIGGERED_PROCESS_T tp where tp.parameter_name='union_station_rpd_modify_time'";
		String calcNum = "-16";
		List<Object[]> dataSet = u1s1gyDao.searchU1s1gy(timeCalssql);
		
		if(dataSet !=null && dataSet.size()>0){
			calcNum = dataSet.get(0) + "";
		}
		String stratime =DateBean.getDynamicTime(calcNum, reportDate, "0");
		String endtime =DateBean.getDynamicTime(calcNum, reportDate, "1");
		
		String sql = "select RPD_U2_DEHYDRATION_ID ,to_char(a.REPORT_TIME,'YYYY-MM-DD HH24:MI:SS')as REPORT_TIME, LIT_10307, LIT_10308,　 LIT_10309,  LIT_10310,  LIT_10311 , LIT_10312,  LIT_10313, LIT_10314,　 LIT_10305 ,LIT_10306, TSB1PL, TSB2PL, TSB3PL ,TT_10311A,WSBPL from  PC_RPD_U2_DEHYDRATION_T a where 1=1";
		sql += " and a.REPORT_TIME between TO_DATE('"+stratime+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+endtime+"','YYYY-MM-DD HH24:MI:SS') order by a.REPORT_TIME";
		
		List<Object[]> U1S1S = u1s1gyDao.searchU1s1gy(sql);
		String[][] dates = DateBean.getReportTime("time10", reportDate);
		
		if(U1S1S !=null && U1S1S.size()>0){		
			for(String[] dateList:dates){					 
					 for(String data:dateList){
						 obj = new JSONObject();
							System.out.println(data);
							u2oiltable = null;
							dataflg = 0;
							for(Object[] U1S1SS:U1S1S){
								if(data.toString().equals(U1S1SS[1].toString())){
									dataflg = 1;
									u2oiltable = U1S1SS;
							}
						}
					 if(dataflg == 1){
						 		
							obj.put("RPD_U2_DEHYDRATION_ID", u2oiltable[0]);
							obj.put("rq", data.substring(11, 16));
							if(u2oiltable[2] !=null && !"".equals(u2oiltable[2]) && !u2oiltable[2].equals("undefined")){
								obj.put("LIT_10307",CommonsUtil.getNotTwoData(u2oiltable[2]));
							}else{
								obj.put("LIT_10307", "");
								}
							if(u2oiltable[3] !=null && !"".equals(u2oiltable[3]) && !u2oiltable[3].equals("undefined")){
								obj.put("LIT_10308", CommonsUtil.getNotTwoData(u2oiltable[3]));
								
							}else{
								obj.put("LIT_10308", "");
							}
							
							if(u2oiltable[4] !=null){
								obj.put("LIT_10309", CommonsUtil.getNotTwoData(u2oiltable[4]));
							}else{
								obj.put("LIT_10309", "");
							}
							if(u2oiltable[5] !=null){
								obj.put("LIT_10310", CommonsUtil.getNotTwoData(u2oiltable[5]));
							}else{
								obj.put("LIT_10310","");
							}
							if(u2oiltable[6] !=null){
								obj.put("LIT_10311", CommonsUtil.getNotTwoData(u2oiltable[6]));
								if(data.substring(11, 16).equals("8:00")){
									int o = 0;
									arr7[o] = CommonsUtil.getDoubleData(u2oiltable[6]);
									o++;
								}
								
							}else{
								obj.put("LIT_10311","");
							}
							if(u2oiltable[7] !=null){
								obj.put("LIT_10312", CommonsUtil.getNotTwoData(u2oiltable[7]));
							}else{
								obj.put("LIT_10312", "");
							}
							if(u2oiltable[8] !=null){
								obj.put("LIT_10313", CommonsUtil.getNotTwoData(u2oiltable[8]));
							}else{
								obj.put("LIT_10313", "");
							}
							if(u2oiltable[9] !=null){
								obj.put("LIT_10314", CommonsUtil.getNotTwoData(u2oiltable[9]));
							}else{
								obj.put("LIT_10314", "");
							}
							if(u2oiltable[10] !=null){
								obj.put("LIT_10305", CommonsUtil.getNotTwoData(u2oiltable[10]));
							}else{
								obj.put("LIT_10305", "");
							}
							if(u2oiltable[11] !=null){
								obj.put("LIT_10306", CommonsUtil.getNotTwoData(u2oiltable[11]));
							}else{
								obj.put("LIT_10306", "");
							}
							if(u2oiltable[12] !=null && !"".equals(u2oiltable[12]) && !u2oiltable[12].equals("undefined")){
								obj.put("TSB1PL", CommonsUtil.getNotTwoData(u2oiltable[12]));
							}else{
								obj.put("TSB1PL", "");
							}
							if( u2oiltable[13] !=null && !"".equals( u2oiltable[13]) && ! u2oiltable[13].equals("undefined")){
								obj.put("TSB2PL", CommonsUtil.getNotTwoData(u2oiltable[13]));
							}else{
								obj.put("TSB2PL", "");
							}
							
							if( u2oiltable[14] !=null && !"".equals( u2oiltable[14]) && ! u2oiltable[14].equals("undefined")){
								obj.put("TSB3PL", CommonsUtil.getNotTwoData(u2oiltable[14]));
							}else{
								obj.put("TSB3PL", "");
							}
							if( u2oiltable[15] !=null && !"".equals( u2oiltable[15]) && ! u2oiltable[15].equals("undefined")){
								obj.put("TT_10311A", CommonsUtil.getNotTwoData(u2oiltable[15]));
							}else{
								obj.put("TT_10311A", "");
							}
							if( u2oiltable[16] !=null && !"".equals( u2oiltable[16]) && ! u2oiltable[16].equals("undefined")){
								obj.put("WSBPL", CommonsUtil.getNotTwoData(u2oiltable[16]));
							}else{
								obj.put("WSBPL", "");
							}
					 	}else{
					 		obj.put("RPD_U2_DEHYDRATION_ID","");
							obj.put("rq",data.substring(11, 16));
							obj.put("LIT_10307", "");
							obj.put("LIT_10308", "");
							obj.put("LIT_10309", "");
							obj.put("LIT_10310","");
							obj.put("LIT_10311","");
							obj.put("LIT_10312", "");
							obj.put("LIT_10313", "");
							obj.put("LIT_10314", "");
							obj.put("LIT_10305", "");
							obj.put("LIT_10306", "");
							obj.put("TSB1PL", "");
							obj.put("TSB2PL", "");
							obj.put("TSB3PL", "");
							obj.put("TT_10311A", "");
							obj.put("WSBPL", "");
					 }
					 arr.add(obj);
				 }
			}
		}else{
			for(String[] dateList:dates){
				for(String data:dateList){
					obj = new JSONObject();
					obj.put("RPD_U2_DEHYDRATION_ID","");
					obj.put("rq",data.substring(11, 16));
					obj.put("LIT_10307", "");
					obj.put("LIT_10308", "");
					obj.put("LIT_10309", "");
					obj.put("LIT_10310","");
					obj.put("LIT_10311","");
					obj.put("LIT_10312", "");
					obj.put("LIT_10313", "");
					obj.put("LIT_10314", "");
					obj.put("LIT_10305", "");
					obj.put("LIT_10306", "");
					obj.put("TSB1PL", "");
					obj.put("TSB2PL", "");
					obj.put("TSB3PL", "");
					obj.put("TT_10311A", "");
					obj.put("WSBPL", "");
					arr.add(obj);
				}
			}
		
		}
		double lit311 = (arr7[1] - arr7[0])*629;
		object.put("lit311", lit311);
		object.put("arr", arr);
		return object;
	
	}

	@Override
	public JSONArray searchU2tscs2(String txtDate,double lit311) throws Exception {
		JSONObject obj = new JSONObject();
		JSONArray arr = new JSONArray();
		String sql = "select r.GH,r.cyw,r.tyw, r.RPD_U2_TSJY_ID from pc_rpd_u2_tsjy_t r where r.REPORT_DATE = to_date('"+ txtDate +"', 'YYYY-MM-DD')";
		String sql1 = "select r.GH,r.cyw,r.tyw, r.RPD_U2_TSWS_ID from PC_RPD_U2_TSWS_T r where r.REPORT_DATE = to_date('"+ txtDate +"', 'YYYY-MM-DD')";
		String sql2 = "select r.HCGDSHSSJ,r.HCGDSHSBP, r.RPD_U2_TSZH_ID from PC_RPD_U2_TSZH_T r where r.REPORT_DATE = to_date('"+ txtDate +"', 'YYYY-MM-DD')";
		List<Object[]> U1S1S = u1s1gyDao.searchU1s1gy(sql);
		List<Object[]> U1S1S1 = u1s1gyDao.searchU1s1gy(sql1);
		List<Object[]> U1S1S2 = u1s1gyDao.searchU1s1gy(sql2);
		double jyl = 0.0;
		double jyl2 = 0.0;
		double zjy = 0.0;
		double wsl = 0.0;
		double wszl = 0.0;
		double hsl = 0.0;
		double wsl2 = 0.0;
		if(U1S1S !=null && U1S1S.size()>0){			
			obj.put("GH", CommonsUtil.getClearNullData(U1S1S.get(0)[0]));
			obj.put("CYW", CommonsUtil.getNotTwoData(U1S1S.get(0)[1]));
			obj.put("TYW", CommonsUtil.getNotTwoData(U1S1S.get(0)[2]));	
			obj.put("jyid1", CommonsUtil.getClearNullData(U1S1S.get(0)[3]));
			jyl = (CommonsUtil.getDoubleData(U1S1S.get(0)[2])-CommonsUtil.getDoubleData(U1S1S.get(0)[1]))*629;
			obj.put("JYL", String.format("%.2f",jyl ));
			if(U1S1S.size() >= 2){
				obj.put("GH2", CommonsUtil.getClearNullData(U1S1S.get(1)[0]));
				obj.put("CYW2", CommonsUtil.getNotTwoData(U1S1S.get(1)[1]));
				obj.put("TYW2", CommonsUtil.getNotTwoData(U1S1S.get(1)[2]));
				obj.put("jyid2", CommonsUtil.getClearNullData(U1S1S.get(1)[3]));
				jyl2 = (CommonsUtil.getDoubleData(U1S1S.get(1)[2])-CommonsUtil.getDoubleData(U1S1S.get(1)[1]))*629;
				obj.put("JYL2", String.format("%.2f",jyl2 ));
			}else{
				obj.put("GH2", "");
				obj.put("CYW2", "");
				obj.put("TYW2", "");
				obj.put("jyid2", "");
				obj.put("JYL2", "");
			}
			zjy = jyl + jyl2;
			obj.put("ZJY", String.format("%.2f",zjy ));
		}else{
			obj.put("GH", "");
			obj.put("CYW", "");
			obj.put("TYW", "");
			obj.put("jyid1", "");
			obj.put("JYL", "");
			obj.put("GH2", "");
			obj.put("CYW2", "");
			obj.put("TYW2", "");
			obj.put("jyid2", "");
			obj.put("JYL2", "");
			obj.put("ZJY","");
		}
		if(U1S1S1 !=null && U1S1S1.size()>0){
			obj.put("WGH", CommonsUtil.getClearNullData(U1S1S1.get(0)[0]));
			obj.put("WCYW", CommonsUtil.getNotTwoData(U1S1S1.get(0)[1]));
			obj.put("WTYW", CommonsUtil.getNotTwoData(U1S1S1.get(0)[2]));
			obj.put("wsid1", CommonsUtil.getClearNullData(U1S1S1.get(0)[3]));
			wsl = (CommonsUtil.getDoubleData(U1S1S1.get(0)[1])-CommonsUtil.getDoubleData(U1S1S1.get(0)[2]))*633;
			obj.put("WSL", String.format("%.2f",wsl ));
			if(U1S1S1.size() >= 2){
				obj.put("WGH2", CommonsUtil.getClearNullData(U1S1S1.get(1)[0]));
				obj.put("WCYW2", CommonsUtil.getNotTwoData(U1S1S1.get(1)[1]));
				obj.put("WTYW2", CommonsUtil.getNotTwoData(U1S1S1.get(1)[2]));
				obj.put("wsid2", CommonsUtil.getClearNullData(U1S1S1.get(1)[3]));
				wsl2 = (CommonsUtil.getDoubleData(U1S1S1.get(1)[1])-CommonsUtil.getDoubleData(U1S1S1.get(1)[2]))*633;
				obj.put("WSL2", String.format("%.2f",wsl2 ));
			}else{
				obj.put("WGH2", "");
				obj.put("WCYW2", "");
				obj.put("WTYW2", "");
				obj.put("wsid2", "");
				obj.put("WSL2", "");
			}
			wszl = wsl + wsl2;
			obj.put("WSZL", String.format("%.2f",wszl ));
		}else{
			obj.put("WGH", "");
			obj.put("WCYW", "");
			obj.put("WTYW", "");
			obj.put("wsid1", "");
			obj.put("WSL", "");
			obj.put("WGH2", "");
			obj.put("WCYW2", "");
			obj.put("WTYW2", "");
			obj.put("wsid2", "");
			obj.put("WSL2", "");
			obj.put("WSZL","");
		}
		if(U1S1S2 !=null && U1S1S2.size()>0){
			obj.put("HCGDSHSSJ", CommonsUtil.getNotTwoData(U1S1S2.get(0)[0]));
			obj.put("HCGDSHSBP", CommonsUtil.getNotTwoData(U1S1S2.get(0)[1]));
			obj.put("zhid", CommonsUtil.getClearNullData(U1S1S2.get(0)[2]));
			hsl = CommonsUtil.getDoubleData(U1S1S2.get(0)[0]) * CommonsUtil.getDoubleData(U1S1S2.get(0)[1])*2;
			obj.put("HSL", String.format("%.2f",hsl ));
		}else{
			obj.put("HCGDSHSSJ", "");
			obj.put("HCGDSHSBP", "");
			obj.put("zhid", "");
			obj.put("HSL", "");
		}
		obj.put("FYL", String.format("%.2f",lit311+zjy+hsl));
		arr.add(obj);
		// TODO Auto-generated method stub
		return arr;
	}

}
