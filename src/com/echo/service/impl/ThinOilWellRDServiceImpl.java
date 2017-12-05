package com.echo.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.beans.PageControl;
import com.echo.dao.ThinOilWellRDDao;
import com.echo.service.ThinOilWellRDService;

public class ThinOilWellRDServiceImpl implements ThinOilWellRDService{
	private ThinOilWellRDDao thinOilWellRDDao;
	
	
	
	public void setThinOilWellRDDao(ThinOilWellRDDao thinOilWellRDDao) {
		this.thinOilWellRDDao = thinOilWellRDDao;
	}
	public HashMap<String,Object> searchThinOil(String oilstationname,String areablock,String blockstationname,String ghname,String rulewellname,String stime,String etime,int pageNo,String sort,String order,int rowsPerpage,String totalNumFlag) {
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
		String formTable = " from view_thin_rd_well  where 1=1  and cjsj between TO_DATE('"+stime+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+etime+"','YYYY-MM-DD HH24:MI:SS') ";
		String totalNum = "select count(*) ";
		if(!oilstationname.equals("")&&oilstationname!=null&&!oilstationname.equals("undefined")&&!oilstationname.equals("全部")){
			formTable=formTable+" and oilName='"+oilstationname+"'";
		}
		if(!areablock.equals("")&&areablock!=null&&!areablock.equals("undefined")){
			formTable=formTable+" and qkmc='"+areablock+"'";
		}
		if(!blockstationname.equals("")&&blockstationname!=null&&!blockstationname.equals("undefined")){
			formTable=formTable+" and jieStation='"+blockstationname+"'";
		}
		if(!ghname.equals("")&&blockstationname!=null&&!ghname.equals("undefined")){
			formTable=formTable+" and guanhui='"+ghname+"'";
		}
		if(!rulewellname.equals("")&&rulewellname!=null&&!rulewellname.equals("undefined")){
			formTable=formTable+" and wellName='"+rulewellname+"'";
		}
		
		String[] cloumnsName = {"WELLNAME","oilName","qkmc","jieStation","guanhui","branching_name","THINOIL_WELLDSID","daima","CJSJ","QLD","QUA","QIA","QUB","QIB","QUC","QIC",
				"QAP","QQP","QCQ","QCC","QST","QUS","QMA","QFU","QFF","QFI","QMO","WELL_PRES"};
		String kk="WELLNAME";
		for(int m=1;m<cloumnsName.length;m++){
			if("CJSJ".equals(cloumnsName[m])){
				kk=kk+","+"to_char(CJSJ,'YYYY-MM-DD hh24:mi:ss') as CJSJ";
			}else{
				kk=kk+","+cloumnsName[m];
			}
		}
		if ("export".equals(totalNumFlag)) {
			//String[] cloumnsNames2 = {"WELLNAME","CJSJ","OILSTATIONNAME","AREABLOCK","BLOCKSTATIONNAME","MANIFOLD","BRANCHINGID","JHSS","QLD","QUA","QIA","QUB","QIB","QUC","QIC","QAP","QQP","QCQ","QCC","QST","QUS","QMA","QFU","QFF","QFI","QMO","WELL_PRES"};
			String[] cloumnsNames2 = {"WELLNAME","CJSJ","oilName","qkmc","jieStation","guanhui","branching_name","daima","QLD","QUA","QIA","QUB","QIB","QUC","QIC",
					"QAP","QQP","QCQ","QCC","QST","QUS","QMA","QFU","QFF","QFI","QMO","WELL_PRES"};
			kk = "WELLNAME";
			for(int m=1;m<cloumnsNames2.length;m++){
				if("CJSJ".equals(cloumnsNames2[m])){
					kk=kk+","+"to_char(CJSJ,'YYYY-MM-DD hh24:mi:ss') as CJSJ";
				}else{
					kk=kk+","+cloumnsNames2[m];
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
			total = thinOilWellRDDao.getCounts(totalNum);
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
			lo = thinOilWellRDDao.searchThinOil(thinOilWellRD);
		}
		else {
			control.init(pageNo, total);
			//开始条数
			int start = control.getStart()-1;
			lo = thinOilWellRDDao.searchThinOil(thinOilWellRD,start,rowsPerpage);
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
