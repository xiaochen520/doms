package com.echo.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.beans.PageControl;
import com.echo.dao.CommonDao;
import com.echo.service.XysqdtService;
import com.echo.util.DateBean;

public class XysqdtServiceImpl implements XysqdtService {
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
		String formTable = " from PC_RD_U_THIN_WATER_AUTO_T a where 1=1 ";
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
		formTable=formTable+" and CJSJ between TO_DATE('"+startDate+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+endDate+"','YYYY-MM-DD HH24:MI:SS')";
		
		String[] cloumnsName = {"CJSJ","F_1_FYTSB","F_1_GLTSB","F_3_FYTSB","FI_1_FYJ","FI_2_FYQ","FI_2_YJO1","FI_2_YJO2","FI_2_YJO3","FI_3_FYJO1","FI_3_FYJO2","FI_3_FYJO3","FI_3_FYQ","FI_JHSO1","FI_YTZS","FI_YYCLZLS","FT_DX","FT_DX_ADD","FT_WSB","FT_XX","FT_XX_ADD","FT_ZSQ1","L1_1_TCG","LI_1_HCG","LI_1_JHG","LI_1_WSC","LI_2_HCG","LI_2_JHG","LI_2_TCG","LI_2_WSC","LI_WYG","LT_XFYW","PT_101A","PT_101B","PT_102A","PT_102B","PT_103A","PT_103B","PT_104A","PT_104B","PT_DX_ZSB","PT_XX_ZSB","PT_ZSB","PT_ZSQ","TT_101","TT_102","TT_103","TT_104","TT_DX_ZSB","TT_XX_ZSB","TT_ZSBF_HSV","WYG_LT_LSV","YJB1_SC_SV","YJB2_SC1_SV","YJB2_SC2_SV","YJB2_SC3_SV","YJB3_SC1_SV","YJB3_SC2_SV","FI_1_FYQ"};
		for(int m=0;m<cloumnsName.length;m++){
			if(m == cloumnsName.length-1){
				kk=kk+cloumnsName[m];
			}else{
				if("CJSJ".equals(cloumnsName[m])){
					kk=kk+"to_char(CJSJ,'YYYY-MM-DD hh24:mi:ss') as CJSJ,";
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
		if(!"".equals(sort) && !"CJSJ".equals(sort)){
			for (String cloumn : cloumnsName) {
				if(cloumn.equals(sort)){
					product +=" order by " + cloumn + " " + order;
					break;
				}
			}
		}
		else {
			product +=" order by CJSJ desc";
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
