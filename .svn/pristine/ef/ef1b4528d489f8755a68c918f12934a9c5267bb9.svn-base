package com.echo.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.beans.PageControl;
import com.echo.dao.CommonDao;
import com.echo.service.U2yqdtService;
import com.echo.util.DateBean;

public class U2yqdtServiceImpl implements U2yqdtService {
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
		String formTable = " from PC_RD_U2_OIL_T a where 1=1 ";
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
		
		String[] cloumnsName = {"ACQUISITION_TIME","LIT_20201B","LIT_20201A","LIT_10301","LIT_10302","LIT_10303","LIT_10304","LIT_10305","LIT_10306","LIT_10307","LIT_10308","LIT_10309","LIT_10310","LIT_10311","LIT_10312","LIT_10313","LIT_10314","TT10301A","TT10301B","TT10301C","TT10301D","TT10302A","TT10302B","TT10302C","TT10302D","TT10303A","TT10303B","TT10303C","TT10303D","TT10304A","TT10304B","TT10304C","TT10304D","TT10305A","TT10305B","TT10305C","TT10305D","TT10306A","TT10306B","TT10306C","TT10306D","TT10307A","TT10307B","TT10307C","TT10307D","TT10308A","TT10308B","TT10308C","TT10308D","TT10309A","TT10309B","TT10309C","TT10309D","TT10310A","TT10310B","TT10310C","TT10310D","TT_10406","TT_10405","TT_10404","TT_10403","TT_10402","TT_10401","TT_10312","TT_10311A","TT_10311B","TT_10111","TT_10112","SC_10501A","SC_10501B","SC_10501C","SC_10501D","SC_10501E","SC_10501F","RMLIT1001","RMLIT2001","RMTT1002","RMTT2002","PV_10801","PT_11101","PT_10801","PT_10601","PT_10602","PT_10603","PT_10406","PT_10405","PT_10404","PT_10403","PT_10402","PT_10401","PT_10302","PT_10301","PT_10111","PT_10112","MT_10301","LIT10303YH","LIT10301YH","KYZ_PT1001","KYZ_TT1001","KYZ_TT2001","FIT_10501","FIT_10502","FIT_10503","FIT_10504","FIT_10301","FIT_10302","RHS_PT30401","RHS_PT30402","RHS_PT30403","RHS_PT30404","RHS_TT30401","RHS_TT30402","RHS_TT30403","RHS_TT30404","RHS_LIT30401","RMTT1001","RMTT2001","FIT10501Z","FIT10502Z","FIT10503Z","FIT10504Z","LIT_00001","LIT_00002","LIT_00003","LIT_00004"};
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
}
