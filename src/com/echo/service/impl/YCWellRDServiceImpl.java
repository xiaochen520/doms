package com.echo.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.beans.PageControl;
import com.echo.dao.YCWellRDDao;
import com.echo.service.YCWellRDService;

public class YCWellRDServiceImpl implements YCWellRDService{
	private YCWellRDDao yCWellRDDao;
	
	public void setyCWellRDDao(YCWellRDDao yCWellRDDao) {
		this.yCWellRDDao = yCWellRDDao;
	}

	public HashMap<String,Object> searchYC(String qk,String zh,String jh,String gh,String name,String stime,String etime,int pageNo,String sort,String order,int rowsPerpage,String totalNumFlag) {
		JSONArray jsonArr = null;
		JSONObject tjo = null;
		HashMap<String,Object> map = new HashMap<String, Object>();
		String cloums = "select   ";
		if("".equals(stime)){
			stime = stime + " 00:00:00";
		}else{
			stime = stime + ":00";
		}
		if("".equals(etime)){
			etime += " 23:59:59";
		}else{
			etime = etime + ":00";
		}
		String formTable = " from PC_RD_YC_WELL_V  where 1=1  and rq between TO_DATE('"+stime+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+etime+"','YYYY-MM-DD HH24:MI:SS') ";
		String totalNum = "select count(*) ";
		if(!qk.equals("")&&qk!=null&&!qk.equals("undefined")&!qk.equals("全部")){
			formTable=formTable+" and OILSTATIONNAME='"+qk+"'";
		}
		if(!zh.equals("")&&zh!=null&&!zh.equals("undefined")){
			formTable=formTable+" and AREABLOCK='"+zh+"'";
		}
		if(!jh.equals("")&&jh!=null&&!jh.equals("undefined")){
			formTable=formTable+" and BLOCKSTATIONNAME='"+jh+"'";
		}
		if(!gh.equals("")&&jh!=null&&!gh.equals("undefined")){
			formTable=formTable+" and pid='"+gh+"'";
		}
		if(!name.equals("")&&name!=null&&!name.equals("undefined")){
			formTable=formTable+" and JHMC='"+name+"'";
		}
		
		String[] cloumnsName = {"SA_WELLID","OILSTATIONNAME","AREABLOCK","BLOCKSTATIONNAME","MANIFOLD","JHMC","JHDM","DWMC","DWDM","ZKMC","RQ","RCYL","RZQL","ZGYL_PJ","QJMC",
				"QKDM","CWMC","CWDM","DBMC","DBDM","ZKDM"};
		String kk="SA_WELLID";
		for(int m=1;m<cloumnsName.length;m++){ 
			// to_char(u.audbshor_date,'YYYY-MM-DD hh24:mi:ss') as audbshor_date 
			if(m==10){ 
			kk=kk+","+"to_char(RQ,'YYYY-MM-DD hh24:mi:ss') as RQ"; 
			}else{ 
			kk=kk+","+cloumnsName[m];
			} 
		}
		String[] cloumnsNames = null;
		if ("export".equals(totalNumFlag)) {
			String[] cloumnsNames2 = {"SA_WELLID","DWMC","DWDM","ZKMC","JHMC","JHDM","RQ","RCYL","RZQL","ZGYL_PJ","QJMC","QKDM","CWMC","CWDM","DBMC","DBDM","ZKDM"};
			cloumnsNames = cloumnsNames2;
			kk = "SA_WELLID";
			for(int m=1;m<cloumnsNames.length;m++){
				if(m==6){ 
					kk=kk+","+"to_char(RQ,'YYYY-MM-DD hh24:mi:ss') as RQ"; 
				}else{
				kk=kk+","+cloumnsNames[m];
				}
			}
		}
		
		String yCWellRD = cloums + kk+formTable;
		//获取总条数
		int total = 0;
		if ("1".equals(totalNum)) {
			total = 1;
		} else {
			totalNum += formTable;
			total = yCWellRDDao.getCounts(totalNum);
		}
		if ("totalNum".equals(totalNumFlag)) {
			map.put("totalNum", total + "");
			return map;
		}
		//排序
		if(!"".equals(sort) && !"rq".equals(sort)){
			for (String cloumn : cloumnsName) {
				if(cloumn.equals(sort)){
					yCWellRD +=" order by " + cloumn + " " + order;
					break;
				}
				
			}
		}
		else {
			yCWellRD +=" order by rq ";
		}
		//计算分页
		PageControl control = new PageControl();
		//当前页
		List<Object[]> lo = new ArrayList<Object[]>();
		control.setInt_num(rowsPerpage);
		if ("export".equals(totalNumFlag)) {
			lo = yCWellRDDao.searchYC(yCWellRD);
		}
		else {
			control.init(pageNo, total);
			//开始条数
			int start = control.getStart()-1;
			lo = yCWellRDDao.searchYC(yCWellRD,start,rowsPerpage);
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
					if(i==10){
						if (o[i] != null||!o[i].equals("null")) {
							tjo.put(cloumnsName[i], o[i].toString());
						}
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
