package com.echo.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.dao.CommonDao;
import com.echo.dto.PcRpdDeaeratorT;
import com.echo.dto.PcRpdU1WaterAutoT;
import com.echo.service.CyqyxrbService;
import com.echo.util.CommonsUtil;
import com.echo.util.DateBean;


public class CyqyxrbServiceImpl implements CyqyxrbService{
   private CommonDao commonDao;
	
	public void setCommonDao(CommonDao commonDao) {
	this.commonDao = commonDao;
}

	
	@Override
	public JSONArray searchDatas(String reportDate,String sclz,String sbbh) throws Exception {

		JSONObject obj = new JSONObject();
		JSONArray arr = new JSONArray();
		int dataflg = 0;
		Object[] tableData = null;
		String calcNum = "-16";
		
		
		String stratime =DateBean.getDynamicTime(calcNum, reportDate, "0");
		String endtime =DateBean.getDynamicTime(calcNum, reportDate, "1");
		
		String sql = "select RPD_DEAERATOR_ID ,to_char(a.BBSJ,'YYYY-MM-DD HH24:MI:SS')as BBSJ, ZKD, CYQ_CKYL,　 CYQ_DCLL1,  CYQ_DCLL2,  CYQ_DCLY from  PC_RPD_DEAERATOR_T a where 1=1";
		sql += " and a.BBSJ between TO_DATE('"+stratime+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+endtime+"','YYYY-MM-DD HH24:MI:SS') ";
		sql += " and SCLZMC = '"+sclz+"'";
		sql += " and SBBH = "+sbbh;
		sql += " order by a.BBSJ";
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
							obj.put("RPD_DEAERATOR_ID", CommonsUtil.getClearNullData(tableData[0]));
							obj.put("ROWRQ", data.substring(11, 16));
							
							obj.put("ZKD",CommonsUtil.getNotThreeData(tableData[2]));
							obj.put("CYQ_CKYL",CommonsUtil.getNotTwoData1(tableData[3]));
//							obj.put("CYQ_DCLL1",CommonsUtil.getSumTwoData(tableData[4],tableData[5]));
							obj.put("CYQ_DCLL1",CommonsUtil.getNotTwoData1(tableData[4]));
							obj.put("CYQ_DCLY",CommonsUtil.getNotOneData(tableData[6]));
						
						
					 	}else{
					 		obj.put("RPD_DEAERATOR_ID","");
							obj.put("ROWRQ",data.substring(11, 16));
							obj.put("ZKD","");
							obj.put("CYQ_CKYL","");
							obj.put("CYQ_DCLL1","");
							obj.put("CYQ_DCLY","");
					 }
					 arr.add(obj);
				 }
			}
		}else{
			for(String[] dateList:dates){
				obj = new JSONObject();
				for(String data:dateList){
					obj = new JSONObject();
					obj.put("RPD_DEAERATOR_ID","");
					obj.put("ROWRQ",data.substring(11, 16));
					obj.put("ZKD","");
					obj.put("CYQ_CKYL","");
					obj.put("CYQ_DCLL1","");
					obj.put("CYQ_DCLY","");
					arr.add(obj);
				}
			}
		
		}
		
		arr.add(obj);
		return arr;
	
	}

	@SuppressWarnings("unused")
	@Override
	public List<PcRpdDeaeratorT> SreachDatas(String id, String name)throws Exception {
		List<PcRpdDeaeratorT> list = null;
		String hql ="from PcRpdDeaeratorT a  where 1=1";
		if(id!=null && !"".equals(id)){
		      hql +=" and a.rpdDeaeratorId = '"+id+"'";
		}
		if(name !=null && !"".equals(name)){
			//hql=" and a.bbsj = '"+name+"'";
		}
		return (List<PcRpdDeaeratorT>) commonDao.searchClassQuery(hql);
	}

	

	@Override
	public boolean updateData(PcRpdDeaeratorT us) throws Exception {
		List<PcRpdDeaeratorT> list = new ArrayList<PcRpdDeaeratorT>();
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
	public List<Object[]> searchDatas(String sql) throws Exception {
		return commonDao.searchEverySql(sql);
	}


	@Override
	public List<Object[]> searchExportData(String txtDate, String insMainSql,
			String sclzmc, String rhq) throws Exception {
		if (!"".equals(txtDate)) {
			//String calcNum = searchCalcNum();
			String calcNum = "-16";
			String stratime =DateBean.getDynamicTime(calcNum, txtDate, "0");
			String endtime =DateBean.getDynamicTime(calcNum, txtDate, "1");
			insMainSql +="  where  SCLZMC ='"+sclzmc+"'";
			insMainSql +="  and SBBH ='"+rhq+"'";
			insMainSql += " and BBSJ between TO_DATE('" + stratime + "','YYYY-MM-DD HH24:MI:SS') and TO_DATE('" + endtime + "','YYYY-MM-DD HH24:MI:SS') order by BBSJ";
			
		}
		
		List<Object[]> list = (List<Object[]>) commonDao.searchEverySql(insMainSql);
		return list;
	
	}


	@Override
	public List<Object[]> searchExportData(String punSql) throws Exception {
		return commonDao.searchEverySql(punSql);
	}



}
