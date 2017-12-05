package com.echo.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.dao.U1CZYDao;
import com.echo.dto.PcRpdU1OilAutoT;
import com.echo.service.U1CZYService;
import com.echo.util.CommonsUtil;
import com.echo.util.DateBean;

public class U1CZYServiceImpl implements U1CZYService{
	private U1CZYDao u1czyDao;
	
	public void setU1czyDao(U1CZYDao u1czyDao) {
		this.u1czyDao = u1czyDao;
	}

	public JSONArray searchU1CZY(String reportDate,String totalNumf) throws Exception {
		JSONObject obj = new JSONObject();
		JSONArray arr = new JSONArray();
		List<String> ghjxtcclj = new ArrayList<String>();
		List<String> tsbcclj = new ArrayList<String>();
		List<String> wycclj = new ArrayList<String>();
		List<String> bbclzlj = new ArrayList<String>();
		List<String> yqcccclj = new ArrayList<String>();
		List<String> CZYSUM = new ArrayList<String>();
		
		List<String> ghjxtccljSUM = new ArrayList<String>();
		List<String> tsbccljSUM = new ArrayList<String>();
		List<String> wyccljSUM = new ArrayList<String>();
		List<String> bbclzljSUM = new ArrayList<String>();
		List<String> yqccccljSUM = new ArrayList<String>();
		List<String> CZYSUMTHSUM = new ArrayList<String>();
		List<String> CZYSUMSUM = new ArrayList<String>();
		int dataflg = 0;  //数据存在标识  0：不存在， 1：存在
		String timeCalssql = "select tp.parameter_value from PC_CD_TRIGGERED_PROCESS_T tp where tp.parameter_name='union_station_rpd_start_time'";
		String calcNum = "-16";
		List<Object[]> dataSet = u1czyDao.searchU1CZY(timeCalssql);
		if(dataSet != null && dataSet.size()>0){
			calcNum = dataSet.get(0) + "";
		}
		
		String stratime =DateBean.getDynamicTime(calcNum, reportDate, "0");
		String endtime =DateBean.getDynamicTime(calcNum, reportDate, "1");
		String sql = "select "+
					" ghjxtcclj,tsbcclj,wycclj,bbclzlj,yqcccclj,yqcyccgyw,yqccccll,RPD_U1_OIL_AUTO_ID,to_char(BBSJ,'YYYY-MM-DD HH24:MI:SS')as report_time"+
					" from PC_RPD_U1_OIL_AUTO_T  where 1=1 ";
			sql += " and BBSJ between TO_DATE('"+stratime+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+endtime+"','YYYY-MM-DD HH24:MI:SS') order by BBSJ";
		Object[] u1czyTab = null;
		List<Object[]> u1czys = u1czyDao.searchU1CZY(sql);	
		String[][] dates = DateBean.getReportTime("time", reportDate);
		if(u1czys != null && u1czys.size()>0){
			for (int i = 0; i < dates.length; i++) {
				String[] dateList = dates[i];
				obj = new JSONObject();
				
				for(String data:dateList){
					u1czyTab = null;
					dataflg = 0;
					for(Object[] u1czy:u1czys){
						obj = new JSONObject();
						
						if(data.toString().equals(u1czy[8].toString())){
							dataflg = 1;
							u1czyTab = u1czy;
						}
						
					}
					if(dataflg == 1){
						obj.put("RPD_U1_OIL_AUTO_ID", u1czyTab[7]);
						obj.put("REPORT_TIME", data.substring(11, 16));
						if(u1czyTab[0] != null){
							obj.put("ghjxtcclj", CommonsUtil.getNotOneData0(u1czyTab[0]));
							ghjxtcclj.add(u1czyTab[0].toString());
						}else{
							obj.put("ghjxtcclj", "");
							ghjxtcclj.add("");
						}
						obj.put("ghjxtccljTH",CommonsUtil.format2pData( CommonsUtil.get2THLL(ghjxtcclj)));
						CZYSUM.add(CommonsUtil.get2THLL(ghjxtcclj));
						ghjxtccljSUM.add(CommonsUtil.get2THLL(ghjxtcclj));

						if(u1czyTab[1]!=null){
							obj.put("tsbcclj", CommonsUtil.getNotOneData0(u1czyTab[1]));
							tsbcclj.add(u1czyTab[1].toString());
						}else{
							obj.put("tsbcclj", "");
							tsbcclj.add("");
						}
						obj.put("tsbccljTH", CommonsUtil.format2pData(CommonsUtil.get2THLL(tsbcclj)));
						CZYSUM.add(CommonsUtil.get2THLL(tsbcclj));
						tsbccljSUM.add(CommonsUtil.get2THLL(tsbcclj));
						
						if(u1czyTab[2]!=null){
							obj.put("wycclj", CommonsUtil.getNotOneData0(u1czyTab[2]));
							wycclj.add(u1czyTab[2].toString());
						}else{
							obj.put("wycclj", "");
							wycclj.add("");
						}
						obj.put("wyccljTH", CommonsUtil.format2pData(CommonsUtil.get2THLL(wycclj)));
						CZYSUM.add(CommonsUtil.get2THLL(wycclj));
						wyccljSUM.add(CommonsUtil.get2THLL(wycclj));
						
						if(u1czyTab[3]!=null){
							obj.put("bbclzlj", CommonsUtil.getNotOneData0(u1czyTab[3]));
							bbclzlj.add(u1czyTab[3].toString());
						}else{
							obj.put("bbclzlj", "");
							bbclzlj.add("");
						}
						obj.put("bbclzljTH", CommonsUtil.format2pData(CommonsUtil.get2THLL(bbclzlj)));
						CZYSUM.add(CommonsUtil.get2THLL(bbclzlj));
						bbclzljSUM.add(CommonsUtil.get2THLL(bbclzlj));
						
						if(u1czyTab[4]!=null){
							obj.put("yqcccclj", CommonsUtil.getNotOneData0(u1czyTab[4]));
							yqcccclj.add(u1czyTab[4].toString());
						}else{
							obj.put("yqcccclj", "");
							yqcccclj.add("");
						}
						obj.put("yqccccljTH", CommonsUtil.format2pData(CommonsUtil.get2THLL(yqcccclj)));
						CZYSUM.add(CommonsUtil.get2THLL(yqcccclj));
						yqccccljSUM.add(CommonsUtil.get2THLL(yqcccclj));
						
						obj.put("CZYSUMTH", CommonsUtil.format2pData(CommonsUtil.getSum(CZYSUM)));
						CZYSUMTHSUM.add(CommonsUtil.getSum(CZYSUM));
						obj.put("CZYSUM", CommonsUtil.format2pData(String.valueOf(Double.parseDouble(CommonsUtil.getSum(CZYSUM))*0.88)));
						CZYSUMSUM.add(String.valueOf((Double.parseDouble(CommonsUtil.getSum(CZYSUM))*0.88)));
						
						
						if(u1czyTab[5]!=null){
							obj.put("yqcyccgyw", CommonsUtil.getNotOneData0(u1czyTab[5]));
						}else{
							obj.put("yqcyccgyw", "");
						}
						if(u1czyTab[6]!=null){
							obj.put("yqccccll", CommonsUtil.getNotOneData0(u1czyTab[6]));
						}else{
							obj.put("yqccccll", "");
						}
//						obj.put("yqccccll", CommonsUtil.get2THLL(yqcccclj));
//						yqccccljSUM.add(CommonsUtil.get2THLL(yqcccclj));
						
					}else{
						obj.put("RPD_U1_OIL_AUTO_ID", "");
						obj.put("REPORT_TIME", data.substring(11, 16));
						obj.put("ghjxtcclj", "");
						ghjxtcclj.add("");
						obj.put("ghjxtccljTH", "");

						obj.put("tsbcclj", "");
						tsbcclj.add("");
						obj.put("tsbccljTH", "");
						
						obj.put("wycclj", "");
						wycclj.add("");
						obj.put("wyccljTH", "");
						
						obj.put("bbclzlj", "");
						bbclzlj.add("");
						obj.put("bbclzljTH", "");
						
						obj.put("yqcccclj", "");
						yqcccclj.add("");
						obj.put("yqccccljTH", "");
						
						obj.put("CZYSUMTH", "0.00");
						obj.put("CZYSUM", "0.00");
						
						
						obj.put("yqcyccgyw", "");
						obj.put("yqcccclj", "");
						yqcccclj.add("");
						obj.put("yqccccll", "");
						obj.put("yqccccllTH", "");
						
					}
					arr.add(obj);
					CZYSUM.clear();
				}
			}
			
		}else{
			for(String[] dateList:dates){
				obj = new JSONObject();
				for(String data:dateList){
					obj = new JSONObject();
					obj.put("RPD_U1_OIL_AUTO_ID", "");
					obj.put("REPORT_TIME", data.substring(11, 16));
					obj.put("ghjxtcclj", "");
					obj.put("ghjxtccljTH", "");
					obj.put("tsbcclj", "");
					obj.put("tsbccljTH", "");
					obj.put("wycclj","");
					obj.put("wyccljTH", "");
					
					obj.put("bbclzlj", "");
					obj.put("bbclzljTH", "");
					obj.put("CZYSUMTH", "0.00");
					obj.put("CZYSUM", "0.00");
					obj.put("yqcyccgyw", "");
					obj.put("yqcccclj", "");
					obj.put("yqccccll", "");
					obj.put("yqccccljTH","");
					arr.add(obj);
				}
			}
		}

		if(u1czys != null && u1czys.size()>0){
			
			obj = new JSONObject();
			obj.put("RPD_U1_OIL_AUTO_ID", "");
			obj.put("REPORT_TIME", "合计");
			obj.put("ghjxtcclj", "");
			obj.put("ghjxtccljTH", CommonsUtil.format2pData(CommonsUtil.getSum(ghjxtccljSUM)));
			obj.put("tsbcclj", "");
			obj.put("tsbccljTH", CommonsUtil.format2pData(CommonsUtil.getSum(tsbccljSUM)));
			obj.put("wycclj","");
			obj.put("wyccljTH", CommonsUtil.format2pData(CommonsUtil.getSum(wyccljSUM)));
			
			obj.put("bbclzlj", "");
			obj.put("bbclzljTH", CommonsUtil.format2pData(CommonsUtil.getSum(bbclzljSUM)));
			obj.put("CZYSUMTH", CommonsUtil.format2pData(CommonsUtil.getSum(CZYSUMTHSUM)));
			obj.put("CZYSUM", CommonsUtil.format2pData(CommonsUtil.getSum(CZYSUMSUM)));
			obj.put("yqcyccgyw", "");
			obj.put("yqcccclj", "");
			obj.put("yqccccll", "");
			obj.put("yqccccljTH",CommonsUtil.format2pData(CommonsUtil.getSum(yqccccljSUM)));
			
			arr.add(obj);
		}else{
			obj = new JSONObject();
			obj.put("RPD_U1_OIL_AUTO_ID", "");
			obj.put("REPORT_TIME", "合计");
			obj.put("ghjxtcclj", "");
			obj.put("ghjxtccljTH", "");
			obj.put("tsbcclj", "");
			obj.put("tsbccljTH", "");
			obj.put("wycclj","");
			obj.put("wyccljTH", "");
			
			obj.put("bbclzlj", "");
			obj.put("bbclzljTH", "");
			obj.put("CZYSUMTH", "");
			obj.put("CZYSUM", "");
			obj.put("yqcyccgyw", "");
			obj.put("yqcccclj", "");
			obj.put("yqccccll", "");
			obj.put("yqccccljTH","");
			
			arr.add(obj);
		}
		
		return arr;
	}

	@Override
	public List<String> dayreport() throws Exception {
		return u1czyDao.dayreport();
	}

	public List<Object[]> searchU2_WATER(String rq,String sql) throws Exception {
		if (!"".equals(rq)) {
			String timeCalssql = "select tp.parameter_value from PC_CD_TRIGGERED_PROCESS_T tp where tp.parameter_name='union_station_rpd_start_time'";
			String calcNum = "-16";
			List<Object[]> dataSet = u1czyDao.searchCalcNum(timeCalssql);
			if(dataSet != null && dataSet.size()>0){
				calcNum = dataSet.get(0) + "";
			}
			String stratime =DateBean.getDynamicTime(calcNum, rq, "0");
			String endtime =DateBean.getDynamicTime(calcNum, rq, "1");
			sql += " where BBSJ between TO_DATE('" + stratime + "','YYYY-MM-DD HH24:MI:SS') and TO_DATE('" + endtime + "','YYYY-MM-DD HH24:MI:SS') order by BBSJ";
		}
		
		List<Object[]> list = (List<Object[]>) u1czyDao.searchCalcNum(sql);
		return list;
	}
	
	public String searchFlagDate(String rq) throws Exception {
		String timeCalssql = "select tp.parameter_value from PC_CD_TRIGGERED_PROCESS_T tp where tp.parameter_name='union_station_rpd_modify_time'";
		String calcNum = "-16";
		List<Object[]> dataSet = u1czyDao.queryInfo(timeCalssql);
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
		
		return u1czyDao.SreachOILAuto(hql);
	}
	@Override
	public int searchCalcNum() throws Exception {
		// TODO Auto-generated method stub
		String timeCalssql = "select tp.parameter_value from PC_CD_TRIGGERED_PROCESS_T tp where tp.parameter_name='union_station_rpd_start_time'";
		String calcNum = "-16";
		List<Object[]> dataSet = u1czyDao.searchCalcNum(timeCalssql);
		if(dataSet != null && dataSet.size()>0){
			calcNum = dataSet.get(0) + "";
		}
		return Integer.parseInt(calcNum);
	}
	@Override
	public boolean updateOILAuto(PcRpdU1OilAutoT oil) throws Exception {
		// TODO Auto-generated method stub
		return u1czyDao.updateOILAuto(oil);
	}
}
