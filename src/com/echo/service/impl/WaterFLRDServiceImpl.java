package com.echo.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.beans.PageControl;
import com.echo.dao.BoilerCrosslRDDao;
import com.echo.dao.SRGLRDDao;
import com.echo.dao.ThinOilWellDao;
import com.echo.dao.ThinOilWellRDDao;
import com.echo.dao.WaterFLRDDao;
import com.echo.dto.PcCdThinoilWellT;
import com.echo.service.BoilerCrosslRDService;
import com.echo.service.SRGLRDService;
import com.echo.service.ThinOilWellRDService;
import com.echo.service.ThinOilWellService;
import com.echo.service.WaterFLRDService;

public class WaterFLRDServiceImpl implements WaterFLRDService{
	private WaterFLRDDao waterFLRDDao;
	

	public void setWaterFLRDDao(WaterFLRDDao waterFLRDDao) {
		this.waterFLRDDao = waterFLRDDao;
	}


	public HashMap<String,Object> searchData(String qk,String zh,String jh,String gh,String name,String stime,String etime,int pageNo,String sort,String order,int rowsPerpage,String totalNumFlag,String groupName) {
		JSONArray jsonArr = null;
		JSONObject tjo = null;
		HashMap<String,Object> map = new HashMap<String, Object>();
		String cloums = "select   ";
		String formTable = " from pc_rd_waterflooding_well_v  where 1=1  and cjsj between TO_DATE('"+stime+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+etime+"','YYYY-MM-DD HH24:MI:SS') ";
		String totalNum = "select count(*) ";
		if(!qk.equals("")&&qk!=null&&!qk.equals("undefined")&!qk.equals("全部")){
			formTable=formTable+" and OILSTATIONNAME='"+qk+"'";
		}
		if(!zh.equals("")&&zh!=null&&!zh.equals("undefined")){
			formTable=formTable+" and areablock='"+zh+"'";
		}
		if(!jh.equals("")&&jh!=null&&!jh.equals("undefined")){
			formTable=formTable+" and WATERINJECTIONTOPR='"+jh+"'";
		}
		if(!gh.equals("")&&jh!=null&&!gh.equals("undefined")){
			formTable=formTable+" and gh_id='"+gh+"'";
		}
		if(!name.equals("")&&name!=null&&!name.equals("undefined")){
			formTable=formTable+" and jhs='"+name+"'";
		}
		if(!groupName.equals("")&&groupName!=null&&!groupName.equals("undefined")){
			formTable=formTable+" and blockstationname='"+groupName+"'";
		}
		
		String[] cloumnsName = {"OILSTATIONNAME","BLOCKSTATIONNAME","AREABLOCK","GH_ID","WATERINJECTIONTOPR","WATERFLOODING_WELLID","WATERFLOODING_WELLRID","QKID","JHS","CJSJ","BUS_PRES","PRES","INSTANTANEOUS_DELIVERY","CUMULATIVE_DISCHARGE"
};
		String kk="OILSTATIONNAME";
		for(int m=1;m<cloumnsName.length;m++){
			if(m==9){ 
				kk=kk+","+"to_char(CJSJ,'YYYY-MM-DD hh24:mi:ss') as CJSJ"; 
			}else{ 
				kk=kk+","+cloumnsName[m];
			} 
		}
		if ("export".equals(totalNumFlag)) {
			String[] cloumnsName2 = {"JHS","CJSJ","OILSTATIONNAME","BLOCKSTATIONNAME","AREABLOCK","WATERINJECTIONTOPR","BUS_PRES","PRES","INSTANTANEOUS_DELIVERY","CUMULATIVE_DISCHARGE"};
			kk = "JHS";
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
			total = waterFLRDDao.getCounts(totalNum);
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
			lo = waterFLRDDao.searchData(thinOilWellRD);
		}
		else {
			control.init(pageNo, total);
			//开始条数
			int start = control.getStart()-1;
			lo = waterFLRDDao.searchData(thinOilWellRD,start,rowsPerpage);
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
