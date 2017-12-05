package com.echo.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.beans.PageControl;
import com.echo.dao.CommonDao;
import com.echo.service.U2fxjldtService;
import com.echo.util.CommonsUtil;
import com.echo.util.DateBean;
import com.echo.util.PropertiesConfig;

public class U2fxjldtServiceImpl implements U2fxjldtService {
	private  CommonDao commonDao;
	

	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}


	public HashMap<String,Object>  searchProducedRD(String startDate, String endDate, int pageNo,
			String sort, String order, int rowsPerpage, String totalNumFlag)throws Exception {
		
		JSONArray jsonArr = null;
		HashMap<String,Object> map = new HashMap<String, Object>();
		JSONObject tjo = null;
		String cloums = "select   ";
		String formTable = " from PC_RD_U2_LINE_MEASURE_T a where 1=1 ";
		String totalNum = "select count(*) ";
		String kk="";
		Date today = new Date();
		String nowdate = DateBean.getTimeStr(today);
		
		if("".equals(startDate)){
			startDate = nowdate.substring(0, 10) + " 00:00:00";
		}else{
			startDate = startDate + ":00";
		}
		if("".equals(endDate)){
			endDate = nowdate.substring(0, 10) + " 23:59:59";
		}else{
			endDate = endDate + ":00";
		}
		formTable=formTable+" and ACQUISITION_TIME between TO_DATE('"+startDate+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+endDate+"','YYYY-MM-DD HH24:MI:SS')";
		
		String[] cloumnsName = {"ACQUISITION_TIME","FIT10101A","FIT10102A","FIT10103A","FIT10104A","FIT10105A","FIT10106A","FIT10107A","FIT10108A","FIT_10109","FIT_10110","FT2HZ","FIT10101B","FIT10102B","FIT10103B","FIT10104B","FIT10105B","FIT10106B","FIT10107B","FIT10108B","FIQ2HZ","PT_10101","PT_10102","PT_10103","PT_10104","PT_10105","PT_10106","PT_10107","PT_10108","PT_10109","PT_10110","PT2HZ","TT_10101","TT_10102","TT_10103","TT_10104","TT_10105","TT_10106","TT_10107","TT_10108","TT_10109","TT_10110","TE2HZ","MR_10101","MR_10102","MR_10103","MR_10104","MR_10105","MR_10106","MR_10107","MR_10108","MT2HZ"};
		for(int m=0;m<cloumnsName.length;m++){
			if(m == cloumnsName.length-1){
				kk=kk+cloumnsName[m];
			}else{
				if("ACQUISITION_TIME".equals(cloumnsName[m])){
					kk=kk+"to_char(ACQUISITION_TIME,'YYYY-MM-DD hh24:mi:ss') as ACQUISITION_TIME,";
				}else{
					kk=kk+cloumnsName[m]+",";
				}
			}
			
		}
		kk.subSequence(0, kk.length()-2);
	
		String product = cloums + kk+formTable;
		//获取总条数
		int total = 0;
		if ("1".equals(totalNum)) {
			total = 1;
		} else {
			totalNum += formTable;
			total = commonDao.getCounts(totalNum);
		}
		if ("totalNum".equals(totalNumFlag)) {
			map.put("totalNum", total + "");
			return map;
		}
		//排序
		if(!"".equals(sort) && !"ACQUISITION_TIME".equals(sort)){
			for (String cloumn : cloumnsName) {
				if(cloumn.equals(sort)){
					product +=" order by " + cloumn + " " + order;
					break;
				}
			}
		}
		else {
			product +=" order by ACQUISITION_TIME desc";
		}
		//计算分页
		PageControl control = new PageControl();
		//当前页
		List<Object[]> lo = new ArrayList<Object[]>();
		control.setInt_num(rowsPerpage);
		if ("export".equals(totalNumFlag)) {
			lo = commonDao.searchEverySql(product);
		}
		else {
			control.init(pageNo, total);
			//开始条数
			int start = control.getStart()-1;
			lo = commonDao.searchPaging(product,start,rowsPerpage,cloumnsName);
		}
		
		if (lo != null && 0 < lo.size()) {
			// 生成树节点json文档
			jsonArr = new JSONArray();
			for (Object[] o : lo) {
				tjo = new JSONObject();

				for (int i = 0; i < o.length; i++) {
					if (o[i] == null||o[i].equals("null")) {
						o[i] = "";
					}
					tjo.put(cloumnsName[i], o[i].toString());
				}
				jsonArr.add(tjo);
			}
		}
		tjo = new JSONObject();
		tjo.put("Rows",jsonArr);
		tjo.put("Total",total);
		map.put("list", lo);
		map.put("json", tjo);
		return map;
	}
	@SuppressWarnings("unused")
	public JSONObject searchfxjlrb(List<String> date) throws Exception {
		JSONObject tjo = null;
		JSONArray jsonArr = new JSONArray();
		//获取当前系统日期
		String[] cloumnsName = {"REPORT_DATE","FTQ_102","FTQ_102O","FTQ_102OQ","FTQ_102R","FTQ_105","FTQ_105O","FTQ_105OQ","FTQ_105R",
				"FTQ_107","FTQ_107O","FTQ_107OQ","FTQ_107R","FTQ_C1Y","FTQ_C1","FTQ_10101A","FTQ_10101AO","FTQ_10101AOQ","FTQ_10101AR","FTQ_10102A","FTQ_10102AO","FTQ_10102AOQ","FTQ_10102AR",
				"FTQ_C2Y","FTQ_C2","FTQ_103","FTQ_103O","FTQ_103OQ","FTQ_103R","FTQ_109","FTQ_109O","FTQ_109OQ","FTQ_109R","FTQ_10103A","FTQ_10103AO","FTQ_10103AOQ","FTQ_10103AR" ,"FTQ_10104A","FTQ_10104AO","FTQ_10104AOQ","FTQ_10104AR","FTQ_C3Y","FTQ_C3","FTQ_S01","FTQ_S01O","FTQ_S01OQ","FTQ_S01R",
				"FTQ_S02","FTQ_S02O","FTQ_S02OQ","FTQ_S02R","FTQ_S03","FTQ_S03O","FTQ_S03OQ","FTQ_S03R","FTQ_10108A","FTQ_10108AO","FTQ_10108AOQ","FTQ_10108AR","FTQ_S1Y","FTQ_S1","FTQ_X01","FTQ_X01O","FTQ_X01OQ","FTQ_X01R","FTQ_W01","FTQ_W01O","FTQ_W01OQ","FTQ_W01R",
				"FTQ_CYZY","FTQ_CYZO","FTQ_XYZY","FTQ_XYZO"};
		String timeCalssql = "select ";
		for (String cn : cloumnsName) {
			timeCalssql += cn +",";
		}
		timeCalssql = timeCalssql.substring(0, timeCalssql.length()-1);
//		RPD_U2_WATER_QUALITY_ID,report_date,to_char(report_time,'YYYY-MM-DD HH24:MI:SS') as report_time,hycjc,hydj,hy1_2,hydc,hyfc,hyhnc,hyyjj,hyyjc,hyejc,hyrhs,xfcjc,xfdj,xf1_2,xfdc,xffc,xfhnc,xfyjj,xfyjc,xfejc,xfrhs,ydcjc,yddj,yddc,ydfc,ydhnc,ydyjj,hnyjc,hnejc "+
		timeCalssql +=	" from PC_RPD_ZH_LINE_MESSURE_V t" ;
		
		timeCalssql += " where t.REPORT_DATE between TO_DATE('"+date.get(0)+"','YYYY-MM-DD') and TO_DATE('"+date.get(1)+"','YYYY-MM-DD')";
		timeCalssql += 	" order by t.REPORT_DATE";
		List<Object[]> lo = commonDao.searchEverySql(timeCalssql);
		PropertiesConfig pc = new PropertiesConfig();//写在adction 里 在查询拼接sql及导出报表时都要用到 
		if (lo != null && 0 < lo.size()) {
			// 生成树节点json文档
			jsonArr = new JSONArray();
			for (Object[] o : lo) {
					tjo = new JSONObject();
					for (int i = 0; i < o.length; i++) {
						if(i!=0){
							tjo.put(cloumnsName[i],CommonsUtil.getNotTwoDataZero(o[i]));
						}else{
							tjo.put(cloumnsName[i], CommonsUtil.getClearNullData(o[i]));
						}
					}								
				jsonArr.add(tjo);
			}
		}
		tjo = new JSONObject();
 		tjo.put("Rows",jsonArr);
 		if(lo != null && lo.size()>0){
 			tjo.put("Total",lo.size());
 		}else{
 			tjo.put("Total",0);
 		}

		return tjo;
	}
	@Override
	public List<Object[]> searchExportData(List<String> date, String fields)
			throws Exception {
		String sql = fields + "  and t.REPORT_DATE between TO_DATE('"+date.get(0)+"','YYYY-MM-DD') and TO_DATE('"+date.get(1)+"','YYYY-MM-DD') order by t.REPORT_DATE";
	
		List<Object[]> list = commonDao.searchEverySql(sql);
		return list;
	}


	@Override
	public JSONObject searchSagdghResults(List<String> date) {
		JSONObject tjo = new JSONObject();
		List<Long> dayList = new ArrayList<Long>();
		
		List<BigDecimal> x32bljList = new ArrayList<BigDecimal>();
		List<BigDecimal> x32bylmList = new ArrayList<BigDecimal>();
		List<BigDecimal> x32byltList = new ArrayList<BigDecimal>();
		List<BigDecimal> x32bhsList = new ArrayList<BigDecimal>();
		
		List<BigDecimal> x513ljList = new ArrayList<BigDecimal>();
		List<BigDecimal> x513ylmList = new ArrayList<BigDecimal>();
		List<BigDecimal> x513yltList = new ArrayList<BigDecimal>();
		List<BigDecimal> x513hsList = new ArrayList<BigDecimal>();
		
		List<BigDecimal> x732ljList = new ArrayList<BigDecimal>();
		List<BigDecimal> x732ylmList = new ArrayList<BigDecimal>();
		List<BigDecimal> x732yltList = new ArrayList<BigDecimal>();
		List<BigDecimal> x732hsList = new ArrayList<BigDecimal>();
		
		List<BigDecimal> x182ljList = new ArrayList<BigDecimal>();
		List<BigDecimal> x182ylmList = new ArrayList<BigDecimal>();
		List<BigDecimal> x182yltList = new ArrayList<BigDecimal>();
		List<BigDecimal> x182hsList = new ArrayList<BigDecimal>();
		
		List<BigDecimal> x282ljList = new ArrayList<BigDecimal>();
		List<BigDecimal> x282ylmList = new ArrayList<BigDecimal>();
		List<BigDecimal> x282yltList = new ArrayList<BigDecimal>();
		List<BigDecimal> x282hsList = new ArrayList<BigDecimal>();
		
		List<BigDecimal> xt181ljList = new ArrayList<BigDecimal>();
		List<BigDecimal> xt181ylmList = new ArrayList<BigDecimal>();
		List<BigDecimal> xt181yltList = new ArrayList<BigDecimal>();
		List<BigDecimal> xt181hsList = new ArrayList<BigDecimal>();
		
		List<BigDecimal> x3183ljList = new ArrayList<BigDecimal>();
		List<BigDecimal> x3183ylmList = new ArrayList<BigDecimal>();
		List<BigDecimal> x3183yltList = new ArrayList<BigDecimal>();
		List<BigDecimal> x3183hsList = new ArrayList<BigDecimal>();
		
		List<BigDecimal> x4184ljList = new ArrayList<BigDecimal>();
		List<BigDecimal> x4184ylmList = new ArrayList<BigDecimal>();
		List<BigDecimal> x4184yltList = new ArrayList<BigDecimal>();
		List<BigDecimal> x4184hsList = new ArrayList<BigDecimal>();
		
		List<BigDecimal> sagd2ljList = new ArrayList<BigDecimal>();
		List<BigDecimal> sagd2ylmList = new ArrayList<BigDecimal>();
		List<BigDecimal> sagd2yltList = new ArrayList<BigDecimal>();
		List<BigDecimal> sagd2hsList = new ArrayList<BigDecimal>();
		
		List<BigDecimal> ccymbljList = new ArrayList<BigDecimal>();
		List<BigDecimal> ccymbylmList = new ArrayList<BigDecimal>();
		List<BigDecimal> ccymbyltList = new ArrayList<BigDecimal>();
		List<BigDecimal> ccymbhsList = new ArrayList<BigDecimal>();
		
		List<BigDecimal> z1hrxljList = new ArrayList<BigDecimal>();
		List<BigDecimal> z1hrxylmList = new ArrayList<BigDecimal>();
		List<BigDecimal> z1hrxyltList = new ArrayList<BigDecimal>();
		List<BigDecimal> z1hrxhsList = new ArrayList<BigDecimal>();
		
		List<BigDecimal> xzjljList = new ArrayList<BigDecimal>();
		List<BigDecimal> xzjylmList = new ArrayList<BigDecimal>();
		List<BigDecimal> xzjyltList = new ArrayList<BigDecimal>();
		List<BigDecimal> xzjhsList = new ArrayList<BigDecimal>();
		
		List<BigDecimal> wehljList = new ArrayList<BigDecimal>();
		List<BigDecimal> wehylmList = new ArrayList<BigDecimal>();
		List<BigDecimal> wehyltList = new ArrayList<BigDecimal>();
		List<BigDecimal> wehhsList = new ArrayList<BigDecimal>();
		
		List<BigDecimal> fc1zyeList = new ArrayList<BigDecimal>();
		List<BigDecimal> fc1zyouList = new ArrayList<BigDecimal>();
		
		List<BigDecimal> fc2zyeList = new ArrayList<BigDecimal>();
		List<BigDecimal> fc2zyouList = new ArrayList<BigDecimal>();
		
		List<BigDecimal> fc3zyeList = new ArrayList<BigDecimal>();
		List<BigDecimal> fc3zyouList = new ArrayList<BigDecimal>();
		
		List<BigDecimal> sagdyeList = new ArrayList<BigDecimal>();
		List<BigDecimal> sagdyouList = new ArrayList<BigDecimal>();
		
		List<BigDecimal> cyyeList = new ArrayList<BigDecimal>();
		List<BigDecimal> cyyouList = new ArrayList<BigDecimal>();
		
		List<BigDecimal> xyyeList = new ArrayList<BigDecimal>();
		List<BigDecimal> xyyouList = new ArrayList<BigDecimal>();
		

		String[] cloumnsName = {"REPORT_DATE","FTQ_102","FTQ_102O","FTQ_102OQ","FTQ_102R","FTQ_105","FTQ_105O","FTQ_105OQ","FTQ_105R","FTQ_107","FTQ_107O","FTQ_107OQ","FTQ_107R",
				"FTQ_10101A","FTQ_10101AO","FTQ_10101AOQ","FTQ_10101AR","FTQ_10102A","FTQ_10102AO","FTQ_10102AOQ","FTQ_10102AR","FTQ_103","FTQ_103O","FTQ_103OQ","FTQ_103R",
				"FTQ_10103A","FTQ_10103AO","FTQ_10103AOQ","FTQ_10103AR" ,"FTQ_10104A","FTQ_10104AO","FTQ_10104AOQ","FTQ_10104AR","FTQ_S01","FTQ_S01O","FTQ_S01OQ","FTQ_S01R",
				"FTQ_S02","FTQ_S02O","FTQ_S02OQ","FTQ_S02R","FTQ_S03","FTQ_S03O","FTQ_S03OQ","FTQ_S03R","FTQ_X01","FTQ_X01O","FTQ_X01OQ","FTQ_X01R","FTQ_W01","FTQ_W01O","FTQ_W01OQ","FTQ_W01R",
				"FTQ_C1Y","FTQ_C1","FTQ_C2Y","FTQ_C2","FTQ_C3Y","FTQ_C3","FTQ_S1Y","FTQ_S1","FTQ_CYZY","FTQ_CYZO","FTQ_XYZY","FTQ_XYZO"};
		String timeCalssql = "select ";
		for (String cn : cloumnsName) {
			timeCalssql += cn +",";
		}
		timeCalssql = timeCalssql.substring(0, timeCalssql.length()-1);

		timeCalssql +=	" from PC_RPD_ZH_LINE_MESSURE_V t" ;
		
		timeCalssql += " where t.REPORT_DATE between TO_DATE('"+date.get(0)+"','YYYY-MM-DD') and TO_DATE('"+date.get(1)+"','YYYY-MM-DD')";
		timeCalssql += 	" order by t.REPORT_DATE";
		
		List<Object[]> lo = commonDao.queryInfo(timeCalssql);
		
		if (lo != null && 0 < lo.size()) {
			for (int i = 0; i < lo.size(); i++) {
				Object[] o = lo.get(i);				
				String times = o[0].toString();				
				SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd");
				Date date1;
				long day = 0;
					try {
						date1 = format.parse(times);
						day =  date1.getTime();						
					} catch (ParseException e) {
						e.printStackTrace();
					}				
				dayList.add(day);
				x32bljList.add((BigDecimal) o[1]);
				x32bylmList.add((BigDecimal) o[2]);
				x32byltList.add((BigDecimal) o[3]);
				x32bhsList.add((BigDecimal) o[4]);
				
				x513ljList.add((BigDecimal) o[5]);
				x513ylmList.add((BigDecimal) o[6]);
				x513yltList.add((BigDecimal) o[7]);
				x513hsList.add((BigDecimal) o[8]);
				
				x732ljList.add((BigDecimal) o[9]);
				x732ylmList.add((BigDecimal) o[10]);
				x732yltList.add((BigDecimal) o[11]);
				x732hsList.add((BigDecimal) o[12]);
				
				x182ljList.add((BigDecimal) o[13]);
				x182ylmList.add((BigDecimal) o[14]);
				x182yltList.add((BigDecimal) o[15]);
				x182hsList.add((BigDecimal) o[16]);
				
				x282ljList.add((BigDecimal) o[17]);
				x282ylmList.add((BigDecimal) o[18]);
				x282yltList.add((BigDecimal) o[19]);
				x282hsList.add((BigDecimal) o[20]);
				
				xt181ljList.add((BigDecimal) o[21]);
				xt181ylmList.add((BigDecimal) o[22]);
				xt181yltList.add((BigDecimal) o[23]);
				xt181hsList.add((BigDecimal) o[24]);
				
				x3183ljList.add((BigDecimal) o[25]);
				x3183ylmList.add((BigDecimal) o[26]);
				x3183yltList.add((BigDecimal) o[27]);
				x3183hsList.add((BigDecimal) o[28]);
				
				x4184ljList.add((BigDecimal) o[29]);
				x4184ylmList.add((BigDecimal) o[30]);
				x4184yltList.add((BigDecimal) o[31]);
				x4184hsList.add((BigDecimal) o[32]);
				
				sagd2ljList.add((BigDecimal) o[33]);
				sagd2ylmList.add((BigDecimal) o[34]);
				sagd2yltList.add((BigDecimal) o[35]);
				sagd2hsList.add((BigDecimal) o[36]);
				
				ccymbljList.add((BigDecimal) o[37]);
				ccymbylmList.add((BigDecimal) o[38]);
				ccymbyltList.add((BigDecimal) o[39]);
				ccymbhsList.add((BigDecimal) o[40]);
				
				z1hrxljList.add((BigDecimal) o[41]);
				z1hrxylmList.add((BigDecimal) o[42]);
				z1hrxyltList.add((BigDecimal) o[43]);
				z1hrxhsList.add((BigDecimal) o[44]);
				
				xzjljList.add((BigDecimal) o[45]);
				xzjylmList.add((BigDecimal) o[46]);
				xzjyltList.add((BigDecimal) o[47]);
				xzjhsList.add((BigDecimal) o[48]);
				
				wehljList.add((BigDecimal) o[49]);
				wehylmList.add((BigDecimal) o[50]);
				wehyltList.add((BigDecimal) o[51]);
				wehhsList.add((BigDecimal) o[52]);
				
				fc1zyeList.add((BigDecimal) o[53]);
				fc1zyouList.add((BigDecimal) o[54]);
				
				fc2zyeList.add((BigDecimal) o[55]);
				fc2zyouList.add((BigDecimal) o[56]);
				
				fc3zyeList.add((BigDecimal) o[57]);
				fc3zyouList.add((BigDecimal) o[58]);
				
				sagdyeList.add((BigDecimal) o[59]);
				sagdyouList.add((BigDecimal) o[60]);
				
				cyyeList.add((BigDecimal) o[61]);
				cyyouList.add((BigDecimal) o[62]);
				
				xyyeList.add((BigDecimal) o[63]);
				xyyouList.add((BigDecimal) o[64]);
				
			}
		}		
	
		tjo.put("day", dayList);
		tjo.put("x32blj", x32bljList);
		tjo.put("x32bylm", x32bylmList);
		tjo.put("x32bylt", x32byltList);
		tjo.put("x32bhs", x32bhsList);
		
		tjo.put("x513lj", x513ljList);
		tjo.put("x513ylm", x513ylmList);
		tjo.put("x513ylt", x513yltList);
		tjo.put("x513hs", x513hsList);
		
		tjo.put("x732lj", x732ljList);
		tjo.put("x732ylm", x732ylmList);
		tjo.put("x732ylt", x732yltList);
		tjo.put("x732hs", x732hsList);
		
		tjo.put("x182lj", x182ljList);
		tjo.put("x182ylm", x182ylmList);
		tjo.put("x182ylt", x182yltList);
		tjo.put("x182hs", x182hsList);
		
		tjo.put("x282lj", x282ljList);
		tjo.put("x282ylm", x282ylmList);
		tjo.put("x282ylt", x282yltList);
		tjo.put("x282hs", x282hsList);
		
		tjo.put("xt181lj", xt181ljList);
		tjo.put("xt181ylm", xt181ylmList);
		tjo.put("xt181ylt", xt181yltList);
		tjo.put("xt181hs", xt181hsList);
		
		tjo.put("x3183lj", x3183ljList);
		tjo.put("x3183ylm", x3183ylmList);
		tjo.put("x3183ylt", x3183yltList);
		tjo.put("x3183hs", x3183hsList);
		
		tjo.put("x4184lj", x4184ljList);
		tjo.put("x4184ylm", x4184ylmList);
		tjo.put("x4184ylt", x4184yltList);
		tjo.put("x4184hs", x4184hsList);
		
		tjo.put("sagd2lj", sagd2ljList);
		tjo.put("sagd2ylm", sagd2ylmList);
		tjo.put("sagd2ylt", sagd2yltList);
		tjo.put("sagd2hs", sagd2hsList);
		
		tjo.put("ccymblj", ccymbljList);
		tjo.put("ccymbylm", ccymbylmList);
		tjo.put("ccymbylt", ccymbyltList);
		tjo.put("ccymbhs", ccymbhsList);
		
		tjo.put("z1hrxlj", z1hrxljList);
		tjo.put("z1hrxylm", z1hrxylmList);
		tjo.put("z1hrxylt", z1hrxyltList);
		tjo.put("z1hrxhs", z1hrxhsList);
		
		tjo.put("xzjlj", xzjljList);
		tjo.put("xzjylm", xzjylmList);
		tjo.put("xzjylt", xzjyltList);
		tjo.put("xzjhs", xzjhsList);
		
		tjo.put("wehlj", wehljList);
		tjo.put("wehylm", wehylmList);
		tjo.put("wehylt", wehyltList);
		tjo.put("wehhs", wehhsList);
		
		tjo.put("fc1zye", fc1zyeList);
		tjo.put("fc1zyou", fc1zyouList);
		
		tjo.put("fc2zye", fc2zyeList);
		tjo.put("fc2zyou", fc2zyouList);
		
		tjo.put("fc3zye", fc3zyeList);
		tjo.put("fc3zyou", fc3zyouList);
		
		tjo.put("sagdye", sagdyeList);
		tjo.put("sagdyou", sagdyouList);
		
		tjo.put("cyye", cyyeList);
		tjo.put("cyyou", cyyouList);
		
		tjo.put("xyye", xyyeList);
		tjo.put("xyyou", xyyouList);

		return tjo;
	}


	@Override
	public HashMap<String, Object> searchDatas(String startDate,
			String endDate, int pageNo, String sort, String order,
			int rowsPerpage, String totalNumf) throws Exception {
		JSONArray jsonArr = null;
		JSONObject tjo = null;
		HashMap<String,Object> map = new HashMap<String, Object>();
		String cloums = "select   ";
	
		Date today = new Date();
		String nowdate = DateBean.getTimeStr(today);
		
		if("".equals(startDate)){
			startDate = nowdate.substring(0, 10) + " 00:00:00";
		}else{
			startDate = startDate + ":00";
		}
		if("".equals(endDate)){
			endDate = nowdate.substring(0, 10) + " 23:59:59";
		}else{
			endDate = endDate + ":00";
		}
		
		String formTable = " from PC_RPD_U2_LINE_CLASS_T where 1=1  and report_date between TO_DATE('"+startDate+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+endDate+"','YYYY-MM-DD HH24:MI:SS') ";
		String totalNum = "select count(*) ";
		String[] cloumnsName = {"report_date","FIT_HH_10101AV","DB10101","FIT_HH_10102AV","DB10102","FIT_HH_10103AV","DB10103","FIT_HH_10104AV",
				"DB10104","FIT_HH_109","DB109","FIT_HH_10108AV","DB10108","FC2YE","DB_FC2YE","FC3YE","DB_FC3YE"};

		String kk="";
		for(int m=0;m<cloumnsName.length;m++){
			if("report_date".equals(cloumnsName[m])){
				kk=kk+"to_char(report_date,'YYYY-MM-DD hh24:mi:ss') as report_date";
			}else{ 
				kk=kk+","+cloumnsName[m];
			} 
		}
		if ("export".equals(totalNumf)) {
			String[] cloumnsName2 = {"report_date","FIT_HH_10101AV","DB10101","FIT_HH_10102AV","DB10102","FIT_HH_10103AV","DB10103","FIT_HH_10104AV",
					"DB10104","FIT_HH_109","DB109","FIT_HH_10108AV","DB10108","FC2YE","DB_FC2YE","FC3YE","DB_FC3YE"};
			kk = "";
			for(int m=0;m<cloumnsName2.length;m++){
				if("report_date".equals(cloumnsName2[m])){
					kk=kk+"to_char(report_date,'YYYY-MM-DD hh24:mi:ss') as report_date";
				}else{
					kk=kk+","+cloumnsName2[m];
				}
			}
		}
		String searchfxjl2cy = cloums + kk+formTable;
		//获取总条数
		int total = 0;
		if ("1".equals(totalNum)) {
			total = 1;
		} else {
			totalNum += formTable;
			total = commonDao.getCounts(totalNum);
		}
		if ("totalNum".equals(totalNumf)) {
			map.put("totalNum", total + "");
			return map;
		}
		//排序
		if(!"".equals(sort) && !"report_date".equals(sort)){
			for (String cloumn : cloumnsName) {
				if(cloumn.equals(sort)){
					searchfxjl2cy +=" order by " + cloumn + " " + order;
					break;
				}
				
			}
		}
		else {
			searchfxjl2cy +=" order by report_date desc";
		}
		//计算分页
		PageControl control = new PageControl();
		//当前页
		List<Object[]> lo = new ArrayList<Object[]>();
		control.setInt_num(rowsPerpage);
		if ("export".equals(totalNumf)) {
			lo = commonDao.searchU2fxcy(searchfxjl2cy);
		}
		else {
			control.init(pageNo, total);
			//开始条数
			int start = control.getStart()-1;
			lo = commonDao.searchU2fxcy(searchfxjl2cy,start,rowsPerpage);
		}
		
		if (lo != null && 0 < lo.size()) {
			// 生成树节点json文档
			jsonArr = new JSONArray();
			for (Object[] o : lo) {
				tjo = new JSONObject();

				for (int i = 0; i < o.length; i++) {
					if (o[i] == null||o[i].equals("null")) {
						o[i] = "";
					}


						tjo.put(cloumnsName[i], o[i].toString());					
				}
				jsonArr.add(tjo);
			}
		}
		tjo = new JSONObject();
		tjo.put("Rows",jsonArr);
		tjo.put("Total",total);
		map.put("list", lo);
		map.put("json", tjo);
		return map;
	}
}
