package com.echo.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.beans.PageControl;
import com.echo.dao.CommonDao;
import com.echo.service.U2sqdtService;
import com.echo.util.DateBean;

public class U2sqdtServiceImpl implements U2sqdtService {
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
		String formTable = " from PC_RD_U2_WATER_T a where 1=1 ";
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
		
		String[] cloumnsName = {"ACQUISITION_TIME","SV_20502A_YND","SC_20601A","SC_20601B","SC_20601C","SC_20502A","SC_20502B","SC_20503A","SC_20503B","SC_20503C","SC_20503D","SC_20503E","SC_20503F","SC_20504A","SC_20504B","SC_20504C","SC_20504D","SC_20504E","SC_20504F","SC_20505A","SC_20505B","SC_20201A","SC_20201B","SC_20201C","SC_20201D","SC_20201E","SC_20401A","SC_20401B","SC_20401C","SC_20401D","LIT_20701A","LIT_20701B","LIT_20702A","LIT_20702B","LIT_20302A","LIT_20302B","LIT_20401A","LIT_20401B","LIT_20601A","LIT_20601B","LIT_20601C","LIT_20601D","LIT_20602A","LIT_20602B","LIT_20101A","LIT_20101B","LIT_20201A","LIT_20201B","LIT_20301A","LIT_20301B","LIT_20301C","LIT_20301D","LIT_20301E","JINGHAUGUAN","FLOW_1","FLOW_2","FLOW_3","FLOW_4","FLOW_5","FLOW_6","FLOW_7","FLOW_8","FLOW_9","FLOW_10","FLOW_11","FLOW_12","FLOW_13","FLOW_14","FLOW_15","FLOW_16","FLOW_17","FLOW_18","FIT_20701","FIT_20702","FIT_20703","FIT_20704","FIT_20705","FIT_20706","FIT_20707","FIT_20708","FIT_20601","FIT_20505","FIT_20504","FIT_20501A","FIT_20501B","FIT_20502A","FIT_20502B","FIT_20502C","FIT_20502D","FIT_20502E","FIT_20502F","FIT_20503A","FIT_20503B","FIT_20503C","FIT_20503D","FIT_20503E","FIT_20503F","FIT_20401B","FIT_20401","FIT_20401A","FIT_20301B","FIT_20301C","FIT_20301D","FIT_20301E","FIT_20301","FIT_20301A","FIT_20202","FIT_20201B","FIT_2020X","FIT_20201A","FI1003","FI1002","FI1001_A","FI1001_B","FI1001_C","FI1001_D","FI1001_E","FI1001_F","FI1001_G","FI1001_H","FI1001_I","FI1001_J","FI1001_K","FI1001_L","FI1001_M","FI1001_N","FI1001_O","FI1001","AT_20401","AT_20301","LIT_10102B","LIT_10102A","LIT_10101B","LIT_10101A","FIT20501Z","FIT20502AZ","FIT20502BZ","FIT20502CZ","FIT20502DZ","FIT20502EZ","FIT20502FZ","FIT20503AZ","FIT20503BZ","FIT20503CZ","FIT20503DZ","FIT20503EZ","FIT20503FZ","FIT20401AZ","FIT20202Z","FIT20201AZ","FIT20301EZ","FIT20301DZ","FIT20301CZ","FIT20301BZ","FIT20301AZ","FIT20301FZ","FIT_20301F","FIT20401Z","FLOW_9Z","FLOW_8Z","FLOW_7Z","FLOW_6Z","FLOW_5Z","FLOW_4Z","FLOW_3Z","FLOW_2Z","FLOW_18Z","FLOW_17Z","FLOW_16Z","FLOW_15Z","FLOW_14Z","FLOW_13Z","FLOW_12Z","FLOW_11Z","FLOW_10Z","FLOW_1Z"};
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
