package com.echo.service.impl;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.dao.CommonDao;
import com.echo.dao.T2sclrbDao;
import com.echo.dto.PcRpdRhsclT2T;
import com.echo.service.T2sclrbService;
import com.echo.util.CommonsUtil;
import com.echo.util.DateBean;

public class T2sclrbServiceImpl implements T2sclrbService{
	private T2sclrbDao t2sclrbDao;
	private CommonDao commonDao;

	public void setT2sclrbDao(T2sclrbDao t2sclrbDao) {
		this.t2sclrbDao = t2sclrbDao;
	}
	
	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}

	@Override
	public JSONArray searchDataSet(String txtDate) throws Exception {



		JSONObject obj = new JSONObject();
		JSONArray arr = new JSONArray();
		int dataflg = 0;
		Object[] tableData = null;
		String calcNum = "-16";
		
		
		String stratime =DateBean.getDynamicTime(calcNum, txtDate, "0");
		String endtime =DateBean.getDynamicTime(calcNum, txtDate, "1");
		
		String sql = "select T.RPD_RHSCL_T2_ID,to_CHAR(T.BBSJ,'YYYY-MM-DD HH24:MI:SS'),RHQZCSL,  CYQZCSL,  ZYBZCSL,  LT_30101A,  "+
					 "LT_30101B,  LT_30102A,	LT_30102B,	LT_30301,	LT_30302,	ZYBYL1,	ZYBYL2,"+
					 "YSKQYL1	,YSKQYL2,HARDNESS,OXYGEN_BEARING,CHLORIDE,BASICITY"+
					 " from PC_RPD_RHSCL_T2_T T where 1=1";
		sql += " and T.BBSJ between TO_DATE('"+stratime+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+endtime+"','YYYY-MM-DD HH24:MI:SS')";
		sql += " order by T.BBSJ";
		List<Object[]> disData = commonDao.searchEverySql(sql);
		String[][] dates = DateBean.getReportTime("time", txtDate);
		if(disData !=null && disData.size()>0){
			
			for(String[] dateList:dates){
			obj = new JSONObject();
					 
					 for(String data:dateList){
							dataflg = 0;
							for(Object[] t2l:disData){
								obj = new JSONObject();
								
								if(data.toString().equals(t2l[1].toString())){
									dataflg = 1;
									tableData = t2l;
							}
						}
					 if(dataflg == 1){
							obj.put("RPD_RHSCL_T2_ID", CommonsUtil.getClearNullData(tableData[0]));
							obj.put("BBSJ", data.substring(11, 16));
							
							obj.put("RHQZCSL",CommonsUtil.getNotOneData(tableData[2]));
							obj.put("CYQZCSL",CommonsUtil.getNotOneData(tableData[3]));
							obj.put("ZYBZCSL",CommonsUtil.getNotOneData(tableData[4]));
							obj.put("LT_30101A",CommonsUtil.getNotTwoData1(tableData[5]));
							
							obj.put("LT_30101B",CommonsUtil.getNotTwoData1(tableData[6]));
							obj.put("LT_30102A",CommonsUtil.getNotTwoData1(tableData[7]));
							obj.put("LT_30102B",CommonsUtil.getNotTwoData1(tableData[8]));
							obj.put("LT_30301",CommonsUtil.getNotTwoData1(tableData[9]));
							
							obj.put("LT_30302",CommonsUtil.getNotTwoData1(tableData[10]));
							obj.put("ZYBYL1",CommonsUtil.getNotTwoData1(tableData[11]));
							obj.put("ZYBYL2",CommonsUtil.getNotTwoData1(tableData[12]));
							obj.put("YSKQYL1",CommonsUtil.getNotTwoData1(tableData[13]));
							
							obj.put("YSKQYL2",CommonsUtil.getNotTwoData1(tableData[14]));
							
							obj.put("HARDNESS",CommonsUtil.getNotOneData(tableData[15]));
							obj.put("OXYGEN_BEARING",CommonsUtil.getNotTwoData1(tableData[16]));
							obj.put("CHLORIDE",CommonsUtil.getNotOneData(tableData[17]));
							obj.put("BASICITY",CommonsUtil.getNotOneData(tableData[18]));
						
						
					 	}else{
							
							obj.put("RPD_RHSCL_T2_ID", "");
							obj.put("BBSJ", data.substring(11, 16));
							
							obj.put("RHQZCSL","");
							obj.put("CYQZCSL","");
							obj.put("ZYBZCSL","");
							obj.put("LT_30101A","");
							
							obj.put("LT_30101B","");
							obj.put("LT_30102A","");
							obj.put("LT_30102B","");
							obj.put("LT_30301","");
							
							obj.put("LT_30302","");
							obj.put("ZYBYL1","");
							obj.put("ZYBYL2","");
							obj.put("YSKQYL1","");
							
							obj.put("YSKQYL2","");
							
							obj.put("HARDNESS","");
							obj.put("OXYGEN_BEARING","");
							obj.put("CHLORIDE","");
							obj.put("BASICITY","");
					 	
					 }
					 arr.add(obj);
				 }
			}
		
		}else{

			for(String[] dateList:dates){
				obj = new JSONObject();
				for(String data:dateList){
					obj = new JSONObject();

					obj.put("RPD_RHSCL_T2_ID", "");
					obj.put("BBSJ", data.substring(11, 16));
					
					obj.put("RHQZCSL","");
					obj.put("CYQZCSL","");
					obj.put("ZYBZCSL","");
					obj.put("LT_30101A","");
					
					obj.put("LT_30101B","");
					obj.put("LT_30102A","");
					obj.put("LT_30102B","");
					obj.put("LT_30301","");
					
					obj.put("LT_30302","");
					obj.put("ZYBYL1","");
					obj.put("ZYBYL2","");
					obj.put("YSKQYL1","");
					
					obj.put("YSKQYL2","");
					
					obj.put("HARDNESS","");
					obj.put("OXYGEN_BEARING","");
					obj.put("CHLORIDE","");
					obj.put("BASICITY","");
			 
					arr.add(obj);
				}
			}
		
		
		}
		return arr;
	}

	@Override
		public int searchCalcNum() {
			String timeCalssql = "select tp.parameter_value from PC_CD_TRIGGERED_PROCESS_T tp where tp.parameter_name='union_station_rpd_start_time'";
			String calcNum = "-16";
			List<Object[]> dataSet = commonDao.searchEverySql(timeCalssql);
			if(dataSet != null && dataSet.size()>0){
				calcNum = dataSet.get(0) + "";
			}
			return Integer.parseInt(calcNum);
	}

	@Override
	public boolean updateData(PcRpdRhsclT2T wt) throws Exception {
		
		return t2sclrbDao.saveData(wt);
	}

	@Override
	public List<PcRpdRhsclT2T> SreachCheckOn(String rpdid, String bbsj)
			throws Exception {
		String hql = "  from PcRpdRhsclT2T  a  where  1=1 ";
		if(rpdid !=null &&  !"".equals(rpdid)){
			hql +=" and a.rpdRhsclT2Id = '"+rpdid+"'";
		}
		List<?> list =null;
		list = commonDao.searchClassQuery(hql);
		return (List<PcRpdRhsclT2T>) list;
	}

	@Override
	public List<Object[]> searchExportData(String txtDate, String jZSCLMainSql)
			throws Exception {
		if (!"".equals(txtDate)) {
			//String calcNum = searchCalcNum();
			String calcNum = "-16";
			String stratime =DateBean.getDynamicTime(calcNum, txtDate, "0");
			String endtime =DateBean.getDynamicTime(calcNum, txtDate, "1");
			jZSCLMainSql += " and BBSJ between TO_DATE('" + stratime + "','YYYY-MM-DD HH24:MI:SS') and TO_DATE('" + endtime + "','YYYY-MM-DD HH24:MI:SS') order by BBSJ";
			
		}
		List<Object[]> list = (List<Object[]>) commonDao.searchEverySql(jZSCLMainSql);
		return list;
	}

	@Override
	public List<Object[]> searchExportData(String punSql) throws Exception {
		return commonDao.searchEverySql(punSql);
	}
	
}
