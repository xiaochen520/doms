package com.echo.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.dao.U1FXJLDao;
import com.echo.dto.PcRpdU1OilAutoT;
import com.echo.service.U1FXJLService;
import com.echo.util.CommonsUtil;
import com.echo.util.DateBean;

public class U1FXJLServiceImpl implements U1FXJLService{
	private U1FXJLDao u1fxjlDao;
	
	public void setU1fxjlDao(U1FXJLDao u1fxjlDao) {
		this.u1fxjlDao = u1fxjlDao;
	}
	public JSONArray searchU1FXJL(String reportDate,String totalNumf) throws Exception {
		JSONObject obj = new JSONObject();
		JSONArray arr = new JSONArray();
		List<String> te002 = new ArrayList<String>();
		List<String> pt002 = new ArrayList<String>();
		List<String> x2z32bxlj = new ArrayList<String>();
		List<String> te005 = new ArrayList<String>();
		List<String> pt005 = new ArrayList<String>();
		List<String> x5_13_11lj = new ArrayList<String>();
		List<String> te007 = new ArrayList<String>();
		List<String> pt007 = new ArrayList<String>();
		List<String> x7z32nxlj = new ArrayList<String>();
		List<String> te008 = new ArrayList<String>();
		List<String> pt008 = new ArrayList<String>();
		List<String> x8xytxlj = new ArrayList<String>();	
		List<String> pt004 = new ArrayList<String>();
		List<String> ghjxtcclj = new ArrayList<String>();
		List<String> tsbccyl = new ArrayList<String>();
		List<String> tsbcclj = new ArrayList<String>();
		List<String> wyccyl = new ArrayList<String>();
		List<String> wycclj = new ArrayList<String>();
		List<String> te2hz = new ArrayList<String>();
		List<String> pt2hz = new ArrayList<String>();
		List<String> q2zlj = new ArrayList<String>();
		List<String> te201 = new ArrayList<String>();
		List<String> pt201 = new ArrayList<String>();
		List<String> ghzlj = new ArrayList<String>();
		List<String> bdcjcyglj = new ArrayList<String>();
		double   FIT2SUM = 0.0;
		double   FIT5SUM = 0.0;
		double   FIT7SUM = 0.0;
		double   FIT8SUM = 0.0;
		int dataflg = 0;  //数据存在标识  0：不存在， 1：存在
		String timeCalssql = "select tp.parameter_value from PC_CD_TRIGGERED_PROCESS_T tp where tp.parameter_name='union_station_rpd_start_time'";
		String calcNum = "-16";
		List<Object[]> dataSet = u1fxjlDao.searchU1FXJL(timeCalssql);
		if(dataSet != null && dataSet.size()>0){
			calcNum = dataSet.get(0) + "";
		}
		
		String stratime =DateBean.getDynamicTime(calcNum, reportDate, "0");
		String endtime =DateBean.getDynamicTime(calcNum, reportDate, "1");
		String sql = "select "+
					" te002,pt002,x2z32bxlj,te005,pt005,x5_13_11lj,te007,pt007,x7z32nxlj,te008,pt008,x8xytxlj,pt004,ghjxtcclj,tsbccyl,tsbcclj,wyccyl,wycclj,te2hz,pt2hz,q2zlj,te201,pt201,ghzlj,bdcjcyglj,"+
					"AIT002,FTQ_102OQ,AIT005,FTQ_105OQ,AIT007,FTQ_107OQ,AIT008,FTQ_108OQ,RPD_U1_OIL_AUTO_ID,to_char(BBSJ,'YYYY-MM-DD HH24:MI:SS')as report_time from PC_RPD_U1_OIL_AUTO_T  where 1=1 ";
			sql += " and BBSJ between TO_DATE('"+stratime+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+endtime+"','YYYY-MM-DD HH24:MI:SS') order by BBSJ";
		List<Object[]> u1fxjls = u1fxjlDao.searchU1FXJL(sql);	
		String[][] dates = DateBean.getReportTime("time", reportDate);
		if(u1fxjls != null && u1fxjls.size()>0){
			for (int i = 0; i < dates.length; i++) {
				String[] dateList = dates[i];
				obj = new JSONObject();
				
				for(int j=0;j<dateList.length;j++){
					dataflg = 0;
					obj = new JSONObject();
					for(Object[] u1fxjl:u1fxjls){
						if(dateList[j].toString().equals(u1fxjl[34].toString())){
							dataflg = 1;
							obj.put("RPD_U1_OIL_AUTO_ID", u1fxjl[33]);
							obj.put("REPORT_TIME", dateList[j].substring(11, 16));
							if(u1fxjl[0] != null){
								obj.put("te002", CommonsUtil.getNotOneData0(u1fxjl[0]));
								te002.add(u1fxjl[0].toString());
							}else{
								obj.put("te002", "");
								te002.add("");
							}

							if(u1fxjl[1]!=null){
								obj.put("pt002", CommonsUtil.getNotTwoData(u1fxjl[1]));
								pt002.add(u1fxjl[1].toString());
							}else{
								obj.put("pt002", "");
								pt002.add("");
							}
							
							if(u1fxjl[2]!=null){
								obj.put("x2z32bxlj", CommonsUtil.getNotOneData(u1fxjl[2]));
								x2z32bxlj.add(u1fxjl[2].toString());
							}else{
								obj.put("x2z32bxlj", "");
								x2z32bxlj.add("");
							}
							
							if(u1fxjl[3]!=null){
								obj.put("te005", CommonsUtil.getNotOneData0(u1fxjl[3]));
								te005.add(u1fxjl[3].toString());
							}else{
								obj.put("te005", "");
								te005.add("");
							}
							
							if(u1fxjl[4]!=null){
								obj.put("pt005", CommonsUtil.getNotTwoData(u1fxjl[4]));
								pt005.add(u1fxjl[4].toString());
							}else{
								obj.put("pt005", "");
								pt005.add("");
							}
							
							
							if(u1fxjl[5]!=null){
								obj.put("x5_13_11lj", CommonsUtil.getNotOneData(u1fxjl[5]));
								x5_13_11lj.add(u1fxjl[5].toString());
							}else{
								obj.put("x5_13_11lj", "");
								x5_13_11lj.add("");
							}
							if(u1fxjl[6]!=null){
								obj.put("te007", CommonsUtil.getNotOneData0(u1fxjl[6]));
								te007.add(u1fxjl[6].toString());
							}else{
								obj.put("te007", "");
								te007.add("");
							}
							if(u1fxjl[7]!=null){
								obj.put("pt007", CommonsUtil.getNotTwoData(u1fxjl[7]));
								pt007.add(u1fxjl[7].toString());
							}else{
								obj.put("pt007", "");
								pt007.add("");
							}
							
							if(u1fxjl[8] != null){
								obj.put("x7z32nxlj", CommonsUtil.getNotOneData(u1fxjl[8]));
								x7z32nxlj.add(u1fxjl[8].toString());
							}else{
								obj.put("x7z32nxlj", "");
								x7z32nxlj.add("");
							}

							if(u1fxjl[9]!=null){
								obj.put("te008", CommonsUtil.getNotOneData0(u1fxjl[9]));
								te008.add(u1fxjl[9].toString());
							}else{
								obj.put("te008", "");
								te008.add("");
							}
							
							if(u1fxjl[10]!=null){
								obj.put("pt008", CommonsUtil.getNotTwoData(u1fxjl[10]));
								pt008.add(u1fxjl[10].toString());
							}else{
								obj.put("pt008", "");
								pt008.add("");
							}
							if(u1fxjl[11]!=null){
								obj.put("x8xytxlj", CommonsUtil.getNotOneData(u1fxjl[11]));
								x8xytxlj.add(u1fxjl[11].toString());
							}else{
								obj.put("x8xytxlj", "");
								x8xytxlj.add("");
							}
							if(u1fxjl[12]!=null){
								obj.put("pt004", CommonsUtil.getNotTwoData(u1fxjl[12]));
								pt004.add(u1fxjl[12].toString());
							}else{
								obj.put("pt004", "");
								pt004.add("");
							}
							if(u1fxjl[13]!=null){
								obj.put("ghjxtcclj", CommonsUtil.getNotOneData(u1fxjl[13]));
								ghjxtcclj.add(u1fxjl[13].toString());
							}else{
								obj.put("ghjxtcclj", "");
								ghjxtcclj.add("");
							}
							if(u1fxjl[14]!=null){
								obj.put("tsbccyl", CommonsUtil.getNotTwoData(u1fxjl[14]));
								tsbccyl.add(u1fxjl[14].toString());
							}else{
								obj.put("tsbccyl", "");
								tsbccyl.add("");
							}
							if(u1fxjl[15]!=null){
								obj.put("tsbcclj", CommonsUtil.getNotOneData(u1fxjl[15]));
								tsbcclj.add(u1fxjl[15].toString());
							}else{
								obj.put("tsbcclj", "");
								tsbcclj.add("");
							}
							
							if(u1fxjl[16] != null){
								obj.put("wyccyl", CommonsUtil.getNotTwoData(u1fxjl[16]));
								wyccyl.add(u1fxjl[16].toString());
							}else{
								obj.put("wyccyl", "");
								wyccyl.add("");
							}

							if(u1fxjl[17]!=null){
								obj.put("wycclj", CommonsUtil.getNotOneData(u1fxjl[17]));
								wycclj.add(u1fxjl[17].toString());
							}else{
								obj.put("wycclj", "");
								wycclj.add("");
							}
							
							if(u1fxjl[18]!=null){
								obj.put("te2hz", CommonsUtil.getNotOneData0(u1fxjl[18]));
								te2hz.add(u1fxjl[18].toString());
							}else{
								obj.put("te2hz", "");
								te2hz.add("");
							}
							if(u1fxjl[19]!=null){
								obj.put("pt2hz", CommonsUtil.getNotTwoData(u1fxjl[19]));
								pt2hz.add(u1fxjl[19].toString());
							}else{
								obj.put("pt2hz", "");
								pt2hz.add("");
							}
							if(u1fxjl[20]!=null){
								obj.put("q2zlj", CommonsUtil.getNotOneData(u1fxjl[20]));
								q2zlj.add(u1fxjl[20].toString());
							}else{
								obj.put("q2zlj", "");
								q2zlj.add("");
							}
							if(u1fxjl[21]!=null){
								obj.put("te201", CommonsUtil.getNotOneData0(u1fxjl[21]));
								te201.add(u1fxjl[21].toString());
							}else{
								obj.put("te201", "");
								te201.add("");
							}
							if(u1fxjl[22]!=null){
								obj.put("pt201", CommonsUtil.getNotTwoData(u1fxjl[22]));
								pt201.add(u1fxjl[22].toString());
							}else{
								obj.put("pt201", "");
								pt201.add("");
							}
							if(u1fxjl[23]!=null){
								obj.put("ghzlj", CommonsUtil.getNotOneData(u1fxjl[23]));
								ghzlj.add(u1fxjl[23].toString());
							}else{
								obj.put("ghzlj", "");
								ghzlj.add("");
							}
							
							if(u1fxjl[24]!=null){
								obj.put("bdcjcyglj", CommonsUtil.getNotOneData(u1fxjl[24]));
								bdcjcyglj.add(u1fxjl[7].toString());
							}else{
								obj.put("bdcjcyglj", "");
								bdcjcyglj.add("");
							}
							obj.put("AIT002", CommonsUtil.getNotTwoDataZero(u1fxjl[25]));
							obj.put("FTQ_102OQ", CommonsUtil.getNotTwoDataZero(u1fxjl[26]));
							obj.put("AIT005", CommonsUtil.getNotTwoDataZero(u1fxjl[27]));
							obj.put("FTQ_105OQ", CommonsUtil.getNotTwoDataZero(u1fxjl[28]));
							obj.put("AIT007", CommonsUtil.getNotTwoDataZero(u1fxjl[29]));
							obj.put("FTQ_107OQ", CommonsUtil.getNotTwoDataZero(u1fxjl[30]));
							obj.put("AIT008", CommonsUtil.getNotTwoDataZero(u1fxjl[31]));
							obj.put("FTQ_108OQ", CommonsUtil.getNotTwoDataZero(u1fxjl[32]));
							if(i!=0||j!=0){
								FIT2SUM = CommonsUtil.getDoubleData(u1fxjl[26])+FIT2SUM;
								FIT5SUM = CommonsUtil.getDoubleData(u1fxjl[28])+FIT5SUM;
								FIT7SUM = CommonsUtil.getDoubleData(u1fxjl[30])+FIT7SUM;
								FIT8SUM = CommonsUtil.getDoubleData(u1fxjl[32])+FIT8SUM;
							}
						}
						
					}
					if(dataflg == 0){
						obj.put("RPD_U1_OIL_AUTO_ID", "");
						obj.put("REPORT_TIME", dateList[j].substring(11, 16));
						obj.put("te002", "");
						te002.add("");
						obj.put("pt002", "");
						pt002.add("");
						obj.put("x2z32bxlj", "");
						x2z32bxlj.add("");
						obj.put("te005", "");
						te005.add("");
						obj.put("pt005", "");
						pt005.add("");
						obj.put("x5_13_11lj", "");
						x5_13_11lj.add("");
						obj.put("te007", "");
						te007.add("");
						obj.put("pt007", "");
						pt007.add("");
						obj.put("x7z32nxlj", "");
						x7z32nxlj.add("");
						obj.put("te008", "");
						te008.add("");
						obj.put("pt008", "");
						pt008.add("");
						obj.put("x8xytxlj", "");
						x8xytxlj.add("");
						obj.put("pt004", "");
						pt004.add("");
						obj.put("ghjxtcclj", "");
						ghjxtcclj.add("");
						obj.put("tsbccyl", "");
						tsbccyl.add("");
						obj.put("tsbcclj", "");
						tsbcclj.add("");
						obj.put("wyccyl", "");
						wyccyl.add("");
						obj.put("wycclj", "");
						wycclj.add("");
						obj.put("te2hz", "");
						te2hz.add("");
						obj.put("pt2hz", "");
						pt2hz.add("");
						obj.put("q2zlj", "");
						q2zlj.add("");
						obj.put("te201", "");
						te201.add("");
						obj.put("pt201", "");
						pt201.add("");
						obj.put("ghzlj", "");
						ghzlj.add("");
						obj.put("bdcjcyglj", "");
						bdcjcyglj.add("");
						obj.put("AIT002", "");
						obj.put("FTQ_102OQ", "");
						obj.put("AIT005", "");
						obj.put("FTQ_105OQ", "");
						obj.put("AIT007", "");
						obj.put("FTQ_107OQ", "");
						obj.put("AIT008", "");
						obj.put("FTQ_108OQ", "");
					}
					arr.add(obj);
				}
			}
			
		}else{
			for(String[] dateList:dates){
				obj = new JSONObject();
				for(String data:dateList){
					obj.put("RPD_U1_OIL_AUTO_ID", "");
					obj.put("REPORT_TIME", data.substring(11, 16));
					obj.put("te002", "");
					obj.put("pt002", "");
					obj.put("x2z32bxlj", "");
					obj.put("te005", "");
					obj.put("pt005", "");
					obj.put("x5_13_11lj", "");
					obj.put("te007", "");
					obj.put("pt007", "");
					obj.put("x7z32nxlj", "");
					obj.put("te008", "");
					obj.put("pt008", "");
					obj.put("x8xytxlj", "");
					obj.put("pt004", "");
					obj.put("ghjxtcclj", "");
					obj.put("tsbccyl", "");
					obj.put("tsbcclj", "");
					obj.put("wyccyl", "");
					obj.put("wycclj", "");
					obj.put("te2hz", "");
					obj.put("pt2hz", "");
					obj.put("q2zlj", "");
					obj.put("te201", "");
					obj.put("pt201", "");
					obj.put("ghzlj", "");
					obj.put("bdcjcyglj", "");
					obj.put("AIT002", "");
					obj.put("FTQ_102OQ", "");
					obj.put("AIT005", "");
					obj.put("FTQ_105OQ", "");
					obj.put("AIT007", "");
					obj.put("FTQ_107OQ", "");
					obj.put("AIT008", "");
					obj.put("FTQ_108OQ", "");
					arr.add(obj);
				}
			}
		}

		if(u1fxjls != null && u1fxjls.size()>0){
			
			obj = new JSONObject();
			obj.put("RPD_U1_OIL_AUTO_ID", "");
			obj.put("REPORT_TIME", "日累计");
			obj.put("te002", CommonsUtil.getSumData0(te002));
			obj.put("pt002", CommonsUtil.format2pData(CommonsUtil.getSumData2(pt002)));
			obj.put("x2z32bxlj", CommonsUtil.format1pData(CommonsUtil.getSumData(x2z32bxlj)));
			obj.put("te005", CommonsUtil.getSumData0(te005));
			obj.put("pt005", CommonsUtil.format2pData(CommonsUtil.getSumData2(pt005)));
			obj.put("x5_13_11lj", CommonsUtil.format1pData(CommonsUtil.getSumData(x5_13_11lj)));
			obj.put("te007", CommonsUtil.getSumData0(te007));
			obj.put("pt007", CommonsUtil.format2pData(CommonsUtil.getSumData2(pt007)));
			obj.put("x7z32nxlj", CommonsUtil.format1pData(CommonsUtil.getSumData(x7z32nxlj)));
			obj.put("te008", CommonsUtil.getSumData0(te008));
			obj.put("pt008", CommonsUtil.format2pData(CommonsUtil.getSumData2(pt008)));
			obj.put("x8xytxlj", CommonsUtil.format1pData(CommonsUtil.getSumData(x8xytxlj)));
			obj.put("pt004", CommonsUtil.format2pData(CommonsUtil.getSumData2(pt004)));
			obj.put("ghjxtcclj", CommonsUtil.format1pData(CommonsUtil.getSumData(ghjxtcclj)));
			obj.put("tsbccyl", CommonsUtil.format2pData(CommonsUtil.getSumData2(tsbccyl)));
			obj.put("tsbcclj", CommonsUtil.format1pData(CommonsUtil.getSumData(tsbcclj)));
			obj.put("wyccyl", CommonsUtil.format2pData(CommonsUtil.getSumData2(wyccyl)));
			obj.put("wycclj", CommonsUtil.format1pData(CommonsUtil.getSumData(wycclj)));
			obj.put("te2hz", CommonsUtil.getSumData2(te2hz));
			obj.put("pt2hz", CommonsUtil.format2pData(CommonsUtil.getSumData2(pt2hz)));
			obj.put("q2zlj", CommonsUtil.format1pData(CommonsUtil.getSumData(q2zlj)));
			obj.put("te201", CommonsUtil.getSumData0(te201));
			obj.put("pt201", CommonsUtil.format2pData(CommonsUtil.getSumData2(pt201)));
			obj.put("ghzlj", CommonsUtil.format1pData(CommonsUtil.getSumData(ghzlj)));
			obj.put("bdcjcyglj", CommonsUtil.format1pData(CommonsUtil.getSumData(bdcjcyglj)));
			obj.put("AIT002", "/");
			obj.put("FTQ_102OQ", String.format("%.2f",FIT2SUM));
			obj.put("AIT005", "/");
			obj.put("FTQ_105OQ", String.format("%.2f",FIT5SUM));
			obj.put("AIT007", "/");
			obj.put("FTQ_107OQ", String.format("%.2f",FIT7SUM));
			obj.put("AIT008", "/");
			obj.put("FTQ_108OQ", String.format("%.2f",FIT8SUM));
			arr.add(obj);
		}else{
			obj = new JSONObject();
			obj.put("RPD_U1_OIL_AUTO_ID", "");
			obj.put("REPORT_TIME", "日累计");
			obj.put("te002", "");
			obj.put("pt002", "");
			obj.put("x2z32bxlj", "");
			obj.put("te005", "");
			obj.put("pt005", "");
			obj.put("x5_13_11lj", "");
			obj.put("te007", "");
			obj.put("pt007", "");
			obj.put("x7z32nxlj", "");
			obj.put("te008", "");
			obj.put("pt008", "");
			obj.put("x8xytxlj", "");
			obj.put("pt004", "");
			obj.put("ghjxtcclj", "");
			obj.put("tsbccyl", "");
			obj.put("tsbcclj", "");
			obj.put("wyccyl", "");
			obj.put("wycclj", "");
			obj.put("te2hz", "");
			obj.put("pt2hz", "");
			obj.put("q2zlj", "");
			obj.put("te201", "");
			obj.put("pt201", "");
			obj.put("ghzlj", "");
			obj.put("bdcjcyglj", "");
			obj.put("AIT002", "");
			obj.put("FTQ_102OQ", "");
			obj.put("AIT005", "");
			obj.put("FTQ_105OQ", "");
			obj.put("AIT007", "");
			obj.put("FTQ_107OQ", "");
			obj.put("AIT008", "");
			obj.put("FTQ_108OQ", "");
			arr.add(obj);
		}
		
		return arr;
	}
	
	@Override
	public List<String> dayreport() throws Exception {
		return u1fxjlDao.dayreport();
	}

	public List<Object[]> searchU2_WATER(String rq,String sql) throws Exception {
		if (!"".equals(rq)) {
			String timeCalssql = "select tp.parameter_value from PC_CD_TRIGGERED_PROCESS_T tp where tp.parameter_name='union_station_rpd_start_time'";
			String calcNum = "-16";
			List<Object[]> dataSet = u1fxjlDao.searchCalcNum(timeCalssql);
			if(dataSet != null && dataSet.size()>0){
				calcNum = dataSet.get(0) + "";
			}
			String stratime =DateBean.getDynamicTime(calcNum, rq, "0");
			String endtime =DateBean.getDynamicTime(calcNum, rq, "1");
			sql += " where BBSJ between TO_DATE('" + stratime + "','YYYY-MM-DD HH24:MI:SS') and TO_DATE('" + endtime + "','YYYY-MM-DD HH24:MI:SS') order by BBSJ";
		}
		
		List<Object[]> list = (List<Object[]>) u1fxjlDao.searchCalcNum(sql);
		return list;
	}
	
	public String searchFlagDate(String rq) throws Exception {
		String timeCalssql = "select tp.parameter_value from PC_CD_TRIGGERED_PROCESS_T tp where tp.parameter_name='union_station_rpd_modify_time'";
		String calcNum = "-16";
		List<Object[]> dataSet = u1fxjlDao.queryInfo(timeCalssql);
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
		
		return u1fxjlDao.SreachOILAuto(hql);
	}
	@Override
	public int searchCalcNum() throws Exception {
		// TODO Auto-generated method stub
		String timeCalssql = "select tp.parameter_value from PC_CD_TRIGGERED_PROCESS_T tp where tp.parameter_name='union_station_rpd_start_time'";
		String calcNum = "-16";
		List<Object[]> dataSet = u1fxjlDao.searchCalcNum(timeCalssql);
		if(dataSet != null && dataSet.size()>0){
			calcNum = dataSet.get(0) + "";
		}
		return Integer.parseInt(calcNum);
	}
	@Override
	public boolean updateOILAuto(PcRpdU1OilAutoT oil) throws Exception {
		// TODO Auto-generated method stub
		return u1fxjlDao.updateOILAuto(oil);
	}
}
