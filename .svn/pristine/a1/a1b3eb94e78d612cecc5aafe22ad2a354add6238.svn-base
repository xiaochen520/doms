package com.echo.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.dao.CommonDao;
import com.echo.dto.PcRpdDtowerT;
import com.echo.dto.PcRpdJzsclT;
import com.echo.service.JzsclrbService;
import com.echo.util.CommonsUtil;
import com.echo.util.DateBean;


public class JzsclrbServiceImpl implements JzsclrbService{
   private CommonDao commonDao;
	
	public void setCommonDao(CommonDao commonDao) {
	this.commonDao = commonDao;
}

	
	@Override
	public JSONArray searchDatas(String reportDate) throws Exception {

		JSONObject obj = new JSONObject();
		JSONArray arr = new JSONArray();
		int dataflg = 0;
		Object[] tableData = null;
		String calcNum = "-16";
		
		
		String stratime =DateBean.getDynamicTime(calcNum, reportDate, "0");
		String endtime =DateBean.getDynamicTime(calcNum, reportDate, "1");
		
		String sql = "select RPD_JZSCL_ID,to_char(BBSJ,'YYYY-MM-DD HH24:MI:SS')as BBSJ,RHQZCSL,CYTZCSL,LT401,LT102,LT101,LT403,ZYBYL1,ZYBYL2,ZYBYL3,PI_201,HARDNESS,OXYGEN_BEARING,CHLORIDE,BASICITY from PC_RPD_JZSCL_T where 1=1";
		sql += " and BBSJ between TO_DATE('"+stratime+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+endtime+"','YYYY-MM-DD HH24:MI:SS') ";
		sql += " order by BBSJ";
		List<Object[]> jzsDatas = commonDao.searchEverySql(sql);
		String[][] dates = DateBean.getReportTime("time", reportDate);
		
		if(jzsDatas !=null && jzsDatas.size()>0){
		
			for(String[] dateList:dates){
			obj = new JSONObject();

					 
					 for(String data:dateList){
							dataflg = 0;
							for(Object[] U1S1SS:jzsDatas){
								obj = new JSONObject();
								
								if(data.toString().equals(U1S1SS[1].toString())){
									dataflg = 1;
									tableData = U1S1SS;

								
							}
						}
					 if(dataflg == 1){
							obj.put("RPD_JZSCL_ID", CommonsUtil.getClearNullData(tableData[0]));
							obj.put("ROWRQ", data.substring(11, 16));
							
							obj.put("RHQZCSL",CommonsUtil.getNotTwoData1(tableData[2]));
							obj.put("CYTZCSL",CommonsUtil.getNotTwoData1(tableData[3]));
							obj.put("LT401",CommonsUtil.getNotTwoData1(tableData[4]));
							obj.put("LT102",CommonsUtil.getNotOneData(tableData[5]));
							obj.put("LT101",CommonsUtil.getNotTwoData1(tableData[6]));
							obj.put("LT403",CommonsUtil.getNotTwoData1(tableData[7]));
							obj.put("ZYBYL1",CommonsUtil.getNotOneData(tableData[8]));
							obj.put("ZYBYL2",CommonsUtil.getNotTwoData1(tableData[9]));
							
							obj.put("ZYBYL3",CommonsUtil.getNotTwoData1(tableData[10]));
							obj.put("PI_201",CommonsUtil.getNotTwoData1(tableData[11]));
							obj.put("HARDNESS",CommonsUtil.getNotOneData(tableData[12]));
							obj.put("OXYGEN_BEARING",CommonsUtil.getNotTwoData1(tableData[13]));
							obj.put("CHLORIDE",CommonsUtil.getNotOneData(tableData[14]));
							obj.put("BASICITY",CommonsUtil.getNotTwoData1(tableData[15]));
						
						
					 	}else{
					 		obj.put("RPD_JZSCL_ID","");
							obj.put("ROWRQ",data.substring(11, 16));

							obj.put("RHQZCSL","");
							obj.put("CYTZCSL","");
							obj.put("LT401","");
							obj.put("LT102","");
							obj.put("LT101","");
							obj.put("LT403","");
							obj.put("ZYBYL1","");
							obj.put("ZYBYL2","");
							
							obj.put("ZYBYL3","");
							obj.put("PI_201","");
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
					obj.put("RPD_JZSCL_ID","");
					obj.put("ROWRQ",data.substring(11, 16));
					obj.put("RHQZCSL","");
					obj.put("CYTZCSL","");
					obj.put("LT401","");
					obj.put("LT102","");
					obj.put("LT101","");
					obj.put("LT403","");
					obj.put("ZYBYL1","");
					obj.put("ZYBYL2","");
					
					obj.put("ZYBYL3","");
					obj.put("PI_201","");
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
	public List<PcRpdJzsclT> SreachDatas(String id, String name)throws Exception {
		List<PcRpdDtowerT> list = null;
		String hql ="from PcRpdJzsclT a  where 1=1";
		if(id!=null && !"".equals(id)){
		      hql +=" and a.rpdJzsclId = '"+id+"'";
		}
		if(name !=null && !"".equals(name)){
			hql=" and a.bbsj = '"+name+"'";
		}
		return (List<PcRpdJzsclT>) commonDao.searchClassQuery(hql);
	}

	

	@Override
	public boolean updateData(PcRpdJzsclT us) throws Exception {
		List<PcRpdJzsclT> list = new ArrayList<PcRpdJzsclT>();
		list.add(us);
		return commonDao.addOrUpdateDatas(list);
	}

	@Override
	public List<Object[]> searchExportDatas(String txtDate, String sql) throws Exception {
			sql += " where 1=1   and to_char(v.bbsj,'yyyy.mm')='"+txtDate+"' order by  v.bbsj";
			List<Object[]> list = commonDao.searchEverySql(sql);
			return list;
	}


	@Override
	public int searchCalcNum() throws Exception {
		String timeCalssql = "select tp.parameter_value from PC_CD_TRIGGERED_PROCESS_T tp where tp.parameter_name='union_station_rpd_start_time'";
		String calcNum = "-16";
		List<Object[]> dataSet = commonDao.searchEverySql(timeCalssql);
		if(dataSet != null && dataSet.size()>0){
			calcNum = dataSet.get(0) + "";
		}
		return Integer.parseInt(calcNum);
	}


	@Override
	public List<Object[]> searchEveryDatas(String sql) throws Exception {
		return commonDao.searchEverySql(sql);
	}


	@Override
	public List<Object[]> searchExportData(String punSql) throws Exception {
		return commonDao.searchEverySql(punSql);
	}


	@Override
	public List<Object[]> searchExportData(String txtDate, String insMainSql) throws Exception {
		if (!"".equals(txtDate)) {
			//String calcNum = searchCalcNum();
			String calcNum = "-16";
			String stratime =DateBean.getDynamicTime(calcNum, txtDate, "0");
			String endtime =DateBean.getDynamicTime(calcNum, txtDate, "1");
			insMainSql += " and BBSJ between TO_DATE('" + stratime + "','YYYY-MM-DD HH24:MI:SS') and TO_DATE('" + endtime + "','YYYY-MM-DD HH24:MI:SS') order by BBSJ";
			
		}
		List<Object[]> list = (List<Object[]>) commonDao.searchEverySql(insMainSql);
		return list;
	}



}
