package com.echo.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.beans.PageControl;
import com.echo.dao.ProducedRDDao;
import com.echo.service.ProducedRDService;
import com.echo.util.DateBean;

public class ProducedRDServiceImpl implements ProducedRDService {
	private  ProducedRDDao producedRDDao;
	
	public void setProducedRDDao(ProducedRDDao producedRDDao) {
		this.producedRDDao = producedRDDao;
	}

	public HashMap<String,Object>  searchProducedRD(String cyz, String qk, String zzz,String gh, String wellname,
			String startDate, String endDate, int pageNo,
			String sort, String order, int rowsPerpage, String totalNumFlag)throws Exception {
		
		JSONArray jsonArr = null;
		HashMap<String,Object> map = new HashMap<String, Object>();
		JSONObject tjo = null;
		String cloums = "select   ";
		String formTable = " from PC_RD_PRODUCED_METERING_V a where 1=1 ";
		String totalNum = "select count(*) ";
		if(!cyz.equals("")&&cyz!=null&&!cyz.equals("undefined") && !"全部".equals(cyz)){
			formTable=formTable+" and cyzh ='"+cyz+"'";
		}
		if(!qk.equals("")&&qk!=null&&!qk.equals("undefined")){
			formTable=formTable+" and QK='"+qk+"'";
		}
		if(!zzz.equals("")&&zzz!=null&&!zzz.equals("undefined")){
			formTable=formTable+" and zhzh='"+zzz+"'";
		}
		if(!gh.equals("")&&gh!=null&&!gh.equals("undefined")){
			formTable=formTable+" and ghd='"+gh+"'";
		}
		if(!wellname.equals("")&&wellname!=null&&!wellname.equals("undefined")){
			formTable=formTable+" and WELL_NAME like'%"+wellname+"%'";
		}
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
		
//		String[] cloumnsName = {"JLZH","CJSJ","DWMC","QK","GHDH","BRANCHING_NAME","JLCZSJ","GHH","JH","CL","CLSJ","SDSJ","DTFH","WELL_NAME","PLAN_METERING_TIMES",
//				"RUN_METERING_TIMES","OIL_ORGID","BRANCHINGID","QKID","ORG_ID","STATION_ORGID","LIQUID"};
		String[] cloumnsName = {"GHD","CJSJ","GHDH","WELL_NAME","ZHZH","JLZH","CYZH","QK","DWMC","BRANCHING_NAME","LIQUID","DTFH","GHH","CL","CLSJ","SDSJ","JLCZSJ","PLAN_METERING_TIMES","RUN_METERING_TIMES"};
		String kk="GHD";
		for(int m=1;m<cloumnsName.length;m++){
			if("CJSJ".equals(cloumnsName[m])){
				kk=kk+","+"to_char(CJSJ,'YYYY-MM-DD hh24:mi:ss') as CJSJ";
			}else if("JLCZSJ".equals(cloumnsName[m])){
				kk=kk+","+"to_char(JLCZSJ,'YYYY-MM-DD hh24:mi:ss') as JLCZSJ";
			}else{
				kk=kk+","+cloumnsName[m];
			}
		}
		if ("export".equals(totalNumFlag)) {
			String[] cloumnsNames2 = {"CJSJ","GHD","GHDH","WELL_NAME","ZHZH","JLZH","CYZH","QK","DTFH","GHH","CL","CLSJ","SDSJ","JLCZSJ","PLAN_METERING_TIMES","RUN_METERING_TIMES","DWMC","BRANCHING_NAME"};
			kk = "to_char(CJSJ,'YYYY-MM-DD hh24:mi:ss') as CJSJ";
			for(int m=1;m<cloumnsNames2.length;m++){
				if("JLCZSJ".equals(cloumnsNames2[m])){
					kk=kk+","+"to_char(JLCZSJ,'YYYY-MM-DD hh24:mi:ss') as JLCZSJ";
				}else{
					kk=kk+","+cloumnsNames2[m];
				}
			}
		}
		String product = cloums + kk+formTable;
		//获取总条数
		int total = 0;
		if ("1".equals(totalNum)) {
			total = 1;
		} else {
			totalNum += formTable;
			total = producedRDDao.getCounts(totalNum);
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
			lo = producedRDDao.searchProduct(product);
		}
		else {
			control.init(pageNo, total);
			//开始条数
			int start = control.getStart()-1;
			lo = producedRDDao.searchProduct(product,start,rowsPerpage,cloumnsName);
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

	@Override
	public JSONArray searchWellName(String ccy) throws Exception {
		String sql="select  a.ORG_ID,a.structurename  from  pc_cd_chytree_v a where a.structuretype='9' order by  nlssort(a.structurename ,'NLS_SORT=SCHINESE_RADICAL_M')";
		JSONObject obj = null;
		JSONArray  jArr = null;
		List<Object[]> dataSet = null;
		  dataSet =producedRDDao.searchProduct(sql);
		 if(dataSet !=null && dataSet.size()>0){
			 jArr = new JSONArray();
				for (Object[] entry : dataSet) {
					obj = new JSONObject();
					obj.put("text", entry[1]);
					obj.put("id", entry[0]);
					jArr.add(obj);
				}
				return jArr;
			}
			jArr = new JSONArray();
			obj= new JSONObject();
			obj.put("text", "");
			obj.put("id", "");
			jArr.add(obj);
			return jArr;
	}
}
