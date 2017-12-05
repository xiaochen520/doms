package com.echo.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.beans.PageControl;
import com.echo.dao.CommonDao;
import com.echo.service.U1mbdtService;
import com.echo.util.DateBean;

public class U1mbdtServiceImpl implements U1mbdtService {
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
		String formTable = " from PC_RD_U1_SAGD_AUTO_T a where 1=1 ";
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
		
		String[] cloumnsName = {"CJSJ","CHUYANGA_FITBACK","CHUYANGA_FITOUTA","CHUYANGA_FITOUTB","CHUYANGA_PTOUT","CHUYANGB_FITBACK","CHUYANGB_FITOUTA","CHUYANGB_FITOUTB",
				"CHUYANGB_PTOUT","CSJ_TCS_1","CSJ_TCS_2","CSJ_TCS_3","CSJ_TCS_4","FIQ_101","FIQ_102","FIQ_103","FIQ_104","FIQ_201","FIQ_201_RS","FIQ_202","FIQ_202_RS",
				"FIQ_203","FIQ_203_RS","FIT_101","FIT_102","FIT_103","FIT_104","FIT_201","FIT_2101","FIT_2102","FIT_2103","FIT_2104","FIT_2105",
				"FIT_2106","FIT_2107","FIT_2108","FIT_2109","FIT_2110","FIT_2112","FIT_2113","FIT_2114","FIT_2115","FIT_2116A","FIT_2116B",
				"FIT2201","FIT2202","FIT2203","FITQ_201","FRC_2111","FRQ_3102","FRQ_3102_RS","FRQ_3201A","FRQ_3201A_RS","FRQ_3201B","FRQ_3201C",
				"FRQ_3201D","FRQ_3202","FRQ_3202_RS","FRQC_3401","LIT_3101A","LIT_3101B","LIT_3101C","LRC_101_1","LRC_2101A","LRC_2101B","LRC_2202A",
				"LRC_2202B","LRC_3102A","LRC_3102B","LRC_3102C","LRC_3103A","LRC_3103B","LRC_3103C","LRC_3201A","LRC_3201B","LRC_3201C",
				"LRC_3201D","LRC_3202A","LRC_3202B","LRC_3202C","LRC_3202D","LRC_3203A","LRC_3203B","LRC_3203C","LRC_3FLQ","LRCS_102",
				"LRCS_103","LRSA_3601","LRSA_3602","LV_2101AS","LV_2101BS","MR_3201A","MR_3201B","MR_3201C","MR_3201D","MT_201","PR_3101",
				"PR_3101A","PR_3101B","PR_3101C","PR_3103","PR_3104","PR_3201A","PR_3201B","PR_3201C","PR_3202","PR_3203","PR_3501",
				"PR_3502","PR_3902","PRA_3201D","PRC_2105","PRC_2113","PRC_2201","PRC_3102","PRC_3301","PRC_3702","PRC_3703","PRC_3704",
				"PRC_3705","PRC_3706","PRC_3707","PRC_3901","PRC_3FLQ","PT_101","PT_102","PT_103","PT_104","PT_105","PT_106","PT_201",
				"PT_201_1","PT_202","PT_202_1","PT_204_1","PT_2101","PT_2102","PT_2103","PT_2104","PT_2106","PT_2107","PT_2108","PT_2109",
				"PT_2110","PT_2111","PT_2112","PT_2114","PT_GE_3FLQ","PTC_203_1","PV_2201AS","PV_2201BS","SR_3401","TE_2101","TE_2102",
				"TE_2103","TE_2104","TE_2107","TE_2108","TE_2109","TE_2110","TE_2111","TE_2112","TE_2113","TE_2114","TE_2115","TE_2116",
				"TE_2117","TE_2120","TE_2121","TE_2122","TE_2123","TE_2124","TE_2125","TE_2126","TE_2127","TE_2128","TE_2129","TE_2130",
				"TE_2131","TE_2132","TE_2134","TE_2135","TE_2136","TE_2137","TE_2138","TE_2139","TE_2140","TE_2141","TR_3101","TR_3101A",
				"TR_3101B","TR_3101C","TR_3103","TR_3104","TR_3201A","TR_3201B","TR_3201C","TR_3201D","TR_3202","TR_3203","TR_3501",
				"TR_3502","TR_3901","TRC_2106","TRC_2119","TRC_2133","TRC_3301","TT_101","TT_102","TT_103","TT_104","TT_105","TT_106",
				"TT_107","TT_108","TT_111","TT_112","TT_115","TT_201","TT_201_1","TT_202","TT_202_1","TT_203_1","YSJ_P1","YSJ_P2",
				"YSJ_T1","YSJ_T2"};
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
