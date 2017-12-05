package com.echo.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.dao.CommonDao;
import com.echo.dto.PcRpdDtowerT;
import com.echo.service.TytyxrbService;
import com.echo.util.CommonsUtil;
import com.echo.util.DateBean;


public class TytyxrbServiceImpl implements TytyxrbService{
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
		
		String sql = "select RPD_DTOWER_ID ,to_char(a.BBSJ,'YYYY-MM-DD HH24:MI:SS')as BBSJ, XH_PUMP_P,GS_PUMP_P,ZY_PUMP_P,W1LT,W1YO,ZY_PUMP_FREQ,W1FT,CYQ_DO from  PC_RPD_DTOWER_T a where 1=1";
		sql += " and a.BBSJ between TO_DATE('"+stratime+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+endtime+"','YYYY-MM-DD HH24:MI:SS') ";
		sql += " and SCLZMC = '"+sclz+"水处理站'";
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
							obj.put("RPD_DTOWER_ID", CommonsUtil.getClearNullData(tableData[0]));
							obj.put("ROWRQ", data.substring(11, 16));
							
							obj.put("XH_PUMP_P",CommonsUtil.getNotTwoData1(tableData[2]));
							obj.put("GS_PUMP_P",CommonsUtil.getNotTwoData1(tableData[3]));
							obj.put("ZY_PUMP_P",CommonsUtil.getNotTwoData1(tableData[4]));
							obj.put("W1LT",CommonsUtil.getNotOneData(tableData[5]));
							obj.put("W1YO",CommonsUtil.getNotTwoData1(tableData[6]));
							obj.put("ZY_PUMP_FREQ",CommonsUtil.getNotTwoData1(tableData[7]));
							obj.put("W1FT",CommonsUtil.getNotOneData(tableData[8]));
							obj.put("CYQ_DO",CommonsUtil.getNotTwoData1(tableData[9]));
						
						
					 	}else{
					 		obj.put("RPD_DTOWER_ID","");
							obj.put("ROWRQ",data.substring(11, 16));

							obj.put("XH_PUMP_P","");
							obj.put("GS_PUMP_P","");
							obj.put("ZY_PUMP_P","");
							obj.put("W1LT","");
							obj.put("W1YO","");
							obj.put("ZY_PUMP_FREQ","");
							obj.put("W1FT","");
							obj.put("CYQ_DO","");
					 }
					 arr.add(obj);
				 }
			}
		}else{
			for(String[] dateList:dates){
				obj = new JSONObject();
				for(String data:dateList){
					obj = new JSONObject();
					obj.put("RPD_DTOWER_ID","");
					obj.put("ROWRQ",data.substring(11, 16));
					obj.put("XH_PUMP_P","");
					obj.put("GS_PUMP_P","");
					obj.put("ZY_PUMP_P","");
					obj.put("W1LT","");
					obj.put("W1YO","");
					obj.put("ZY_PUMP_FREQ","");
					obj.put("W1FT","");
					obj.put("CYQ_DO","");
					arr.add(obj);
				}
			}
		
		}
		
		arr.add(obj);
		return arr;
	
	}

	@Override
	public List<PcRpdDtowerT> SreachDatas(String id, String name)throws Exception {
		List<PcRpdDtowerT> list = null;
		String hql ="from PcRpdDtowerT a  where 1=1";
		if(id!=null && !"".equals(id)){
		      hql +=" and a.rpdDtowerId = '"+id+"'";
		}
		if(name !=null && !"".equals(name)){
			hql=" and a.bbsj = '"+name+"'";
		}
		return (List<PcRpdDtowerT>) commonDao.searchClassQuery(hql);
	}

	

	@Override
	public boolean updateData(PcRpdDtowerT us) throws Exception {
		List<PcRpdDtowerT> list = new ArrayList<PcRpdDtowerT>();
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
	public List<Object[]> searchExportData(String punSql) throws Exception {
		return commonDao.searchEverySql(punSql);
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



}
