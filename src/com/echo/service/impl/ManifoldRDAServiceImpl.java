package com.echo.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.beans.PageControl;
import com.echo.dao.ManifoldRDADao;
import com.echo.dao.ProducedRDDao;
import com.echo.dao.ThinOilWellRDDao;
import com.echo.service.ManifoldRDAService;
import com.echo.service.ProducedRDService;
import com.echo.util.DateBean;

public class ManifoldRDAServiceImpl implements ManifoldRDAService {

	private ManifoldRDADao manifoldRDADao;
	public void setManifoldRDADao(ManifoldRDADao manifoldRDADao) {
		this.manifoldRDADao = manifoldRDADao;
	}
	public HashMap<String,Object> searchManifoldRDA(String qk,String zh,String jh,String gh,String name,String stime,String etime,int pageNo,String sort,String order,int rowsPerpage,String totalNumFlag) {
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
		String formTable = " from pc_rd_manifold_v  where 1=1  and cjsj between TO_DATE('"+stime+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+etime+"','YYYY-MM-DD HH24:MI:SS') ";
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
			formTable=formTable+" and pipe_sink='"+name+"'";
		}
		
		String[] cloumnsName = {"OILSTATIONNAME","AREABLOCK","BLOCKSTATIONNAME","BRANCHINGNAME","PIPE_SINK","MANIFOLDRID","CJSJ","BLOCK","STATIONNO","TEMP","PRESS"};
		String kk="OILSTATIONNAME";
		for(int m=1;m<cloumnsName.length;m++){
			if(m==6){ 
				kk=kk+","+"to_char(CJSJ,'YYYY-MM-DD hh24:mi:ss') as CJSJ"; 
			}else{ 
				kk=kk+","+cloumnsName[m];
			} 
		}
		if ("export".equals(totalNumFlag)) {
			String[] cloumnsName2 = {"PIPE_SINK","CJSJ","OILSTATIONNAME","AREABLOCK","BLOCKSTATIONNAME","BRANCHINGNAME","STATIONNO","TEMP","PRESS"};
			kk = "PIPE_SINK";
			for(int m=1;m<cloumnsName2.length;m++){
				if("CJSJ".equals(cloumnsName2[m])){
					kk=kk+","+"to_char(CJSJ,'YYYY-MM-DD hh24:mi:ss') as CJSJ";
				}else{
					kk=kk+","+cloumnsName2[m];
				}
			}
		}
		String thinOilWellRD = cloums + kk+formTable;
		//获取总条数
		int total = 0;
		if ("1".equals(totalNum)) {
			total = 1;
		} else {
			totalNum += formTable;
			total = manifoldRDADao.getCounts(totalNum);
		}
		if ("totalNum".equals(totalNumFlag)) {
			map.put("totalNum", total + "");
			return map;
		}
		//排序
		if(!"".equals(sort) && !"CJSJ".equals(sort)){
			for (String cloumn : cloumnsName) {
				if(cloumn.equals(sort)){
					thinOilWellRD +=" order by " + cloumn + " " + order;
					break;
				}
				
			}
		}
		else {
			thinOilWellRD +=" order by CJSJ ";
		}
		//计算分页
		PageControl control = new PageControl();
		//当前页
		List<Object[]> lo = new ArrayList<Object[]>();
		control.setInt_num(rowsPerpage);
		if ("export".equals(totalNumFlag)) {
			lo = manifoldRDADao.searchManifoldRD(thinOilWellRD);
		}
		else {
			control.init(pageNo, total);
			//开始条数
			int start = control.getStart()-1;
			lo = manifoldRDADao.searchManifoldRD(thinOilWellRD,start,rowsPerpage);
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
