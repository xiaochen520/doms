package com.echo.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.dao.U1SCLCSDao;
import com.echo.dto.PcRpdU1OilAutoT;
import com.echo.service.U1SCLCSService;
import com.echo.util.CommonsUtil;
import com.echo.util.DateBean;

public class U1SCLCSServiceImpl implements U1SCLCSService{
	private U1SCLCSDao u1sclcsDao;
	
	public void setU1sclcsDao(U1SCLCSDao u1sclcsDao) {
		this.u1sclcsDao = u1sclcsDao;
	}
	public JSONArray searchU1SCLCS(String reportDate,String totalNumf) throws Exception {
		JSONObject obj = new JSONObject();
		JSONArray arr = new JSONArray();
		List<String> ft1001 = new ArrayList<String>();
		List<String> s2lsllj = new ArrayList<String>();
		List<String> ft1005 = new ArrayList<String>();
		List<String> ft1004 = new ArrayList<String>();
		List<String> ft1003 = new ArrayList<String>();
		List<String> ft1002 = new ArrayList<String>();
		List<String> s2qf1lj = new ArrayList<String>();
		List<String> s2qf2lj = new ArrayList<String>();
		List<String> ft1016 = new ArrayList<String>();
		List<String> s2wssllj = new ArrayList<String>();
		
		int dataflg = 0;  //数据存在标识  0：不存在， 1：存在
		String timeCalssql = "select tp.parameter_value from PC_CD_TRIGGERED_PROCESS_T tp where tp.parameter_name='union_station_rpd_start_time'";
		String calcNum = "-16";
		List<Object[]> dataSet = u1sclcsDao.searchU1SCLCS(timeCalssql);
		if(dataSet != null && dataSet.size()>0){
			calcNum = dataSet.get(0) + "";
		}
		
		String stratime =DateBean.getDynamicTime(calcNum, reportDate, "0");
		String endtime =DateBean.getDynamicTime(calcNum, reportDate, "1");
		String sql = "select "+
					" ft1001,s2lsllj,ft1005,ft1004,ft1003,ft1002,s2qf1lj,s2qf2lj,ft1016,s2wssllj,RPD_U1_WATER_AUTO_ID,to_char(BBSJ,'YYYY-MM-DD HH24:MI:SS')as report_time"+
					" from PC_RPD_U1_WATER_AUTO_T  where 1=1 ";
			sql += " and BBSJ between TO_DATE('"+stratime+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+endtime+"','YYYY-MM-DD HH24:MI:SS') order by BBSJ";
		Object[] u1sclcsTab = null;
		List<Object[]> u1sclcss = u1sclcsDao.searchU1SCLCS(sql);	
		String[][] dates = DateBean.getReportTime("time", reportDate);
		if(u1sclcss != null && u1sclcss.size()>0){
			for (int i = 0; i < dates.length; i++) {
				String[] dateList = dates[i];
				obj = new JSONObject();
				
				for(String data:dateList){
					u1sclcsTab = null;
					dataflg = 0;
					for(Object[] u1sclcs:u1sclcss){
						obj = new JSONObject();
						
						if(data.toString().equals(u1sclcs[11].toString())){
							dataflg = 1;
							u1sclcsTab = u1sclcs;
						}
						
					}
					if(dataflg == 1){
						obj.put("RPD_U1_WATER_AUTO_ID", u1sclcsTab[10]);
						obj.put("REPORT_TIME", data.substring(11, 16));
						if(u1sclcsTab[0] != null){
							obj.put("ft1001", CommonsUtil.getNotTwoData(u1sclcsTab[0]));
							ft1001.add(u1sclcsTab[0].toString());
						}else{
							obj.put("ft1001", "");
							ft1001.add("");
						}

						if(u1sclcsTab[1]!=null){
							obj.put("s2lsllj", CommonsUtil.getNotTwoData(u1sclcsTab[1]));
							s2lsllj.add(u1sclcsTab[1].toString());
						}else{
							obj.put("s2lsllj", "");
							s2lsllj.add("");
						}
						
						if(u1sclcsTab[2]!=null){
							obj.put("ft1005", CommonsUtil.getNotTwoData(u1sclcsTab[2]));
							ft1005.add(u1sclcsTab[2].toString());
						}else{
							obj.put("ft1005", "");
							ft1005.add("");
						}
						
						if(u1sclcsTab[3]!=null){
							obj.put("ft1004", CommonsUtil.getNotTwoData(u1sclcsTab[3]));
							ft1004.add(u1sclcsTab[3].toString());
						}else{
							obj.put("ft1004", "");
							ft1004.add("");
						}
						
						if(u1sclcsTab[4]!=null){
							obj.put("ft1003", CommonsUtil.getNotTwoData(u1sclcsTab[4]));
							ft1003.add(u1sclcsTab[4].toString());
						}else{
							obj.put("ft1003", "");
							ft1003.add("");
						}
						
						
						if(u1sclcsTab[5]!=null){
							obj.put("ft1002", CommonsUtil.getNotTwoData(u1sclcsTab[5]));
							ft1002.add(u1sclcsTab[5].toString());
						}else{
							obj.put("ft1002", "");
							ft1002.add("");
						}
						
						if(u1sclcsTab[6]!=null){
							obj.put("s2qf1lj", CommonsUtil.getNotTwoData(u1sclcsTab[6]));
							s2qf1lj.add(u1sclcsTab[6].toString());
						}else{
							obj.put("s2qf1lj", "");
							s2qf1lj.add("");
						}
						if(u1sclcsTab[7]!=null){
							obj.put("s2qf2lj", CommonsUtil.getNotTwoData(u1sclcsTab[7]));
							s2qf2lj.add(u1sclcsTab[7].toString());
						}else{
							obj.put("s2qf2lj", "");
							s2qf2lj.add("");
						}
						
						if(u1sclcsTab[8] != null){
							obj.put("ft1016", CommonsUtil.getNotTwoData(u1sclcsTab[8]));
							ft1016.add(u1sclcsTab[8].toString());
						}else{
							obj.put("ft1016", "");
							ft1016.add("");
						}

						if(u1sclcsTab[9]!=null){
							obj.put("s2wssllj", CommonsUtil.getNotTwoData(u1sclcsTab[9]));
							s2wssllj.add(u1sclcsTab[9].toString());
						}else{
							obj.put("s2wssllj", "");
							s2wssllj.add("");
						}
						
					}else{
						obj.put("RPD_U1_WATER_AUTO_ID", "");
						obj.put("REPORT_TIME", data.substring(11, 16));
						obj.put("ft1001", "");
						ft1001.add("");
						obj.put("s2lsllj", "");
						s2lsllj.add("");
						obj.put("ft1005", "");
						ft1005.add("");
						obj.put("ft1004", "");
						ft1004.add("");
						obj.put("ft1003", "");
						ft1003.add("");
						obj.put("ft1002", "");
						ft1002.add("");
						obj.put("s2qf1lj", "");
						s2qf1lj.add("");
						obj.put("s2qf2lj", "");
						s2qf2lj.add("");
						obj.put("ft1016", "");
						ft1016.add("");
						obj.put("s2wssllj", "");
						s2wssllj.add("");
						
						
					}
					arr.add(obj);
				}
			}
			
		}else{
			for(String[] dateList:dates){
				obj = new JSONObject();
				for(String data:dateList){
					obj.put("RPD_U1_WATER_AUTO_ID", "");
					obj.put("REPORT_TIME", data.substring(11, 16));
					obj.put("ft1001", "");
					ft1001.add("");
					obj.put("s2lsllj", "");
					s2lsllj.add("");
					obj.put("ft1005", "");
					ft1005.add("");
					obj.put("ft1004", "");
					ft1004.add("");
					obj.put("ft1003", "");
					ft1003.add("");
					obj.put("ft1002", "");
					ft1002.add("");
					obj.put("s2qf1lj", "");
					s2qf1lj.add("");
					obj.put("s2qf2lj", "");
					s2qf2lj.add("");
					obj.put("ft1016", "");
					ft1016.add("");
					obj.put("s2wssllj", "");
					s2wssllj.add("");
					arr.add(obj);
				}
			}
		}

		if(u1sclcss != null && u1sclcss.size()>0){
			
			obj = new JSONObject();
			obj.put("RPD_U1_WATER_AUTO_ID", "");
			obj.put("REPORT_TIME", "合计");
			obj.put("ft1001", CommonsUtil.format2pData(CommonsUtil.getSumData2(ft1001)));
			obj.put("s2lsllj", CommonsUtil.format2pData(CommonsUtil.getSumData2(s2lsllj)));
			obj.put("ft1005", CommonsUtil.format2pData(CommonsUtil.getSumData2(ft1005)));
			obj.put("ft1004", CommonsUtil.format2pData(CommonsUtil.getSumData2(ft1004)));
			obj.put("ft1003", CommonsUtil.format2pData(CommonsUtil.getSumData2(ft1003)));
			obj.put("ft1002", CommonsUtil.format2pData(CommonsUtil.getSumData2(ft1002)));
			obj.put("s2qf1lj", CommonsUtil.format2pData(CommonsUtil.getSumData2(s2qf1lj)));
			obj.put("s2qf2lj", CommonsUtil.format2pData(CommonsUtil.getSumData2(s2qf2lj)));
			obj.put("ft1016", CommonsUtil.format2pData(CommonsUtil.getSumData2(ft1016)));
			obj.put("s2wssllj", CommonsUtil.format2pData(CommonsUtil.getSumData2(s2wssllj)));
			
			arr.add(obj);
		}else{
			obj = new JSONObject();
			obj.put("RPD_U1_WATER_AUTO_ID", "");
			obj.put("REPORT_TIME", "合计");
			obj.put("ft1001", "");
			obj.put("s2lsllj", "");
			obj.put("ft1005", "");
			obj.put("ft1004", "");
			obj.put("ft1003", "");
			obj.put("ft1002", "");
			obj.put("s2qf1lj", "");
			obj.put("s2qf2lj", "");
			obj.put("ft1016", "");
			obj.put("s2wssllj", "");
			
			arr.add(obj);
		}
		
		return arr;
	}
	
	@Override
	public List<String> dayreport() throws Exception {
		return u1sclcsDao.dayreport();
	}

	public List<Object[]> searchU2_WATER(String rq,String sql) throws Exception {
		if (!"".equals(rq)) {
			String timeCalssql = "select tp.parameter_value from PC_CD_TRIGGERED_PROCESS_T tp where tp.parameter_name='union_station_rpd_start_time'";
			String calcNum = "-16";
			List<Object[]> dataSet = u1sclcsDao.searchCalcNum(timeCalssql);
			if(dataSet != null && dataSet.size()>0){
				calcNum = dataSet.get(0) + "";
			}
			String stratime =DateBean.getDynamicTime(calcNum, rq, "0");
			String endtime =DateBean.getDynamicTime(calcNum, rq, "1");
			sql += " where BBSJ between TO_DATE('" + stratime + "','YYYY-MM-DD HH24:MI:SS') and TO_DATE('" + endtime + "','YYYY-MM-DD HH24:MI:SS') order by BBSJ";
		}
		
		List<Object[]> list = (List<Object[]>) u1sclcsDao.searchCalcNum(sql);
		return list;
	}
	
	public String searchFlagDate(String rq) throws Exception {
		String timeCalssql = "select tp.parameter_value from PC_CD_TRIGGERED_PROCESS_T tp where tp.parameter_name='union_station_rpd_modify_time'";
		String calcNum = "-16";
		List<Object[]> dataSet = u1sclcsDao.queryInfo(timeCalssql);
		if(dataSet != null && dataSet.size()>0){
			calcNum = dataSet.get(0) + "";
		}
		String stratime =DateBean.getDynamicTime(calcNum, rq, "0");
		String endtime =DateBean.getDynamicTime(calcNum, rq, "1");
		return stratime + "&" + endtime;
	}
	@Override
	public List<PcRpdU1OilAutoT> SreachOILAuto(String id, String rq)
			throws Exception {
		// TODO Auto-generated method stub
		String hql = " FROM PcRpdU1OilAutoT r WHERE 1=1 ";
		if(id != null && !"".equals(id)){
			hql += " and r.rpdU1OilAutoId = '"+id+"'";
		}
		
		if(rq != null && !"".equals(rq)){
			hql += " and r.bbsj = TO_DATE('"+rq+"','YYYY-MM-DD')";
		}
		
		return u1sclcsDao.SreachOILAuto(hql);
	}
	@Override
	public int searchCalcNum() throws Exception {
		// TODO Auto-generated method stub
		String timeCalssql = "select tp.parameter_value from PC_CD_TRIGGERED_PROCESS_T tp where tp.parameter_name='union_station_rpd_start_time'";
		String calcNum = "-16";
		List<Object[]> dataSet = u1sclcsDao.searchCalcNum(timeCalssql);
		if(dataSet != null && dataSet.size()>0){
			calcNum = dataSet.get(0) + "";
		}
		return Integer.parseInt(calcNum);
	}
	@Override
	public boolean updateOILAuto(PcRpdU1OilAutoT oil) throws Exception {
		// TODO Auto-generated method stub
		return u1sclcsDao.updateOILAuto(oil);
	}
}
