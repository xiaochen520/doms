package com.echo.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.beans.PageControl;
import com.echo.dao.CommonDao;
import com.echo.dto.PcCdStationPointT;
import com.echo.service.ZkcjdService;
import com.echo.util.AuthorityUtil;
import com.echo.util.CommonsUtil;
import com.echo.util.StringUtil;

public class ZkcjdServiceImpl implements ZkcjdService{
	private CommonDao  commonDao;

	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}
	
	public HashMap<String, Object> searchData(String sYSTEMCODEQ, String pOINTTYPEQ,
			String cREATEDATEQ, String fLOWCODEQ, String aLARMORQ,
			String aLARMCODEQ, String dEVICECODEQ, int pageNo, String sort,
			String order, int rowsPerpage, String totalNumFlag , String ptable) throws Exception {

		JSONArray jsonArr = null;
		HashMap<String,Object> map = new HashMap<String, Object>();
		JSONObject tjo = null;
		String cloums = "select   ";
		String formTable = "  from PC_CD_STATION_POINT_V a  where 1=1 ";
		String totalNum = "select count(*) ";
		if(ptable !=null &&  ptable.equals("Eptable")){
			formTable = "  from pc_cd_ptable a  where 1=1 ";
		}
		if(ptable !=null &&  ptable.equals("Eptable") || ptable !=null &&  ptable.equals("ptable")){
			if(!sYSTEMCODEQ.equals("")&&sYSTEMCODEQ!=null&&!sYSTEMCODEQ.equals("undefined")){
				formTable=formTable+" and system_code ='"+sYSTEMCODEQ+"'";
			}
		}else{
			if(!sYSTEMCODEQ.equals("")&&sYSTEMCODEQ!=null&&!sYSTEMCODEQ.equals("undefined")){
				formTable=formTable+" and system_name ='"+sYSTEMCODEQ+"'";
			}
		}
		//if(!sYSTEMCODEQ.equals("")&&sYSTEMCODEQ!=null&&!sYSTEMCODEQ.equals("undefined")){
			//formTable=formTable+" and system_name ='"+sYSTEMCODEQ+"'";
		//}
		if(!pOINTTYPEQ.equals("")&&pOINTTYPEQ!=null&&!pOINTTYPEQ.equals("undefined") ){
			formTable=formTable+" and point_order ='"+pOINTTYPEQ+"'";
		}
		if(!cREATEDATEQ.equals("")&&cREATEDATEQ!=null&&!cREATEDATEQ.equals("undefined") ){
			formTable=formTable+" and create_date = TO_DATE('"+cREATEDATEQ+"' ,'YYYY-MM-DD')";
		}
		
		if(!fLOWCODEQ.equals("")&&fLOWCODEQ!=null&&!fLOWCODEQ.equals("undefined")){
			formTable=formTable+" and FLOW_CODE ='"+fLOWCODEQ+"'";
		}
		if(!aLARMCODEQ.equals("")&&aLARMCODEQ!=null&&!aLARMCODEQ.equals("undefined") ){
			formTable=formTable+" and alarm_value ='"+aLARMCODEQ+"'";
		}
		if(!dEVICECODEQ.equals("")&&dEVICECODEQ!=null&&!dEVICECODEQ.equals("undefined") ){
			formTable=formTable+" and device_name ='"+dEVICECODEQ+"'";
		}
		if(!aLARMORQ.equals("")&&aLARMORQ!=null&&!aLARMORQ.equals("undefined")){
			formTable=formTable+" and alarm_or ='"+aLARMORQ+"'";
		}
		
		String[] cloumnsName = null;
		if (!"export".equals(totalNumFlag)) {
			cloumnsName = new String[]{"SYSTEM_CODE","SYSTEM_NAME","TAG_NAME","REMARK","ACCESS_SIGNS","POINT_ORDER","POINT_TYPE","UNIT",
					"CREATE_DATE","FLOW_CODE","FLOW_NAME","RANGES_DOWN","RANGES_UPPER","ALARM_LIMITLL","ALARM_LIMITL","ALARM_LIMITH",
					"ALARM_LIMITHH","ALARM_OR","ALARM_CODE","ALARM_VALUE","DEVICE_CODE","DEVICE_NAME","CONTROL_OR","HISTORY_CURVE",
					"ORACLE_SAVE","BEIZHU","RLAST_OPER","RLAST_ODATE","STATION_POINT_ID"};
		}else{
			cloumnsName = new String[]{"SYSTEM_CODE","SYSTEM_NAME","TAG_NAME","REMARK","ACCESS_SIGNS","POINT_ORDER","UNIT","UNIT",
					"CREATE_DATE","FLOW_NAME","RANGES_DOWN","RANGES_UPPER","ALARM_LIMITLL","ALARM_LIMITL","ALARM_LIMITH",
					"ALARM_LIMITHH","ALARM_OR","ALARM_VALUE","DEVICE_NAME","CONTROL_OR","HISTORY_CURVE",
					"ORACLE_SAVE","BEIZHU"} ;
			
		}
		
		String kk="SYSTEM_CODE";
		for(int m=1;m<cloumnsName.length;m++){
			if("RLAST_ODATE".equals(cloumnsName[m])){
				kk=kk+","+"to_char(RLAST_ODATE,'YYYY-MM-DD hh24:mi:ss') as RLAST_ODATE";
			} else{
				kk=kk+","+cloumnsName[m];
			}
		}
		String pjsql = cloums + kk+formTable;
		
		if(ptable !=null &&  ptable.equals("Eptable")){
			String[] cloumnsNamesPTable = {"scada_node","tag_name","remark","point_type","ranges_down",
					"ranges_upper","unit","alarm_or","alarm_code","alarm_limitll","alarm_limitl",
					"alarm_limith","alarm_limithh" } ;
			kk="scada_node";
			for(int m=1;m<cloumnsNamesPTable.length;m++){
				kk=kk+","+cloumnsNamesPTable[m];
			}
			pjsql = cloums + kk+formTable;
		}
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
		if(ptable !=null &&  ptable.equals("ptable")){
			map.put("ptable", total + "");
			return map;
		}
		//排序
		if(!"".equals(sort) && !"SYSTEM_CODE".equals(sort)){
			for (String cloumn : cloumnsName) {
				if(cloumn.equals(sort)){
					pjsql +=" order by " + cloumn + " " + order;
					break;
				}
			}
		} else  if(ptable !=null &&  ptable.equals("Eptable")){
			pjsql +=" order by scada_node asc";
		}
		else {
			pjsql +=" order by SYSTEM_CODE asc";
		}
		//计算分页
		PageControl control = new PageControl();
		//当前页
		List<Object[]> lo = new ArrayList<Object[]>();
		control.setInt_num(rowsPerpage);
		if ("export".equals(totalNumFlag)) {
			lo = commonDao.searchEverySql(pjsql);
		}else  if(ptable !=null &&  ptable.equals("Eptable")){
			lo = commonDao.searchEverySql(pjsql);
		}
		else {
			control.init(pageNo, total);
			//开始条数
			int start = control.getStart()-1;
			lo = commonDao.searchPaging(pjsql,start,rowsPerpage,cloumnsName);
		}
		if ("export".equals(totalNumFlag)){
			List<Object[]>  newlo = new ArrayList<Object[]>();
			if (lo != null && 0 < lo.size()) {
				
				List<Object[]> lclists = null;
				List<String> flownames = null;
				
				Object[] newo = null;
				String paramName = "";
				
				String sql = "select t.flow_code,t.flow_name from PC_CD_SYSTEM_FLOW_T t";
				lclists = AuthorityUtil.commonssql(sql);
				// 生成树节点json文档
				jsonArr = new JSONArray();
				for (Object[] o : lo) {
					tjo = new JSONObject();
					newo = new Object[o.length -1];
					for (int i = 0; i < o.length; i++) {
						
						if (i == 7) {
							if(CommonsUtil.getClearNullData(o[i-1]).indexOf(";") != -1){
								
								if(lclists != null && lclists.size()>0){
									String[] flowcodes = CommonsUtil.getClearNullData(o[i-1]).split(";");
									flownames = new ArrayList<String>();
									paramName = "";
									if(flowcodes != null && flowcodes.length>0){
										for (String code : flowcodes){
											for(Object[] systemflow : lclists){
												if(code.equals(CommonsUtil.getClearNullData(systemflow[0]))){
													flownames.add(CommonsUtil.getClearNullData(systemflow[1]));
												}
											}
										}
									}
									
									
									if(flownames != null && flownames.size()>0){
										for(String name :flownames){
											paramName += name+";";
										}
									}
									
									paramName = paramName.substring(0, paramName.length()-1);
									tjo.put(cloumnsName[i], paramName);
									newo[i-1] = paramName;
								}else{
									tjo.put(cloumnsName[i], CommonsUtil.getClearNullData(o[i]));
									newo[i-1] = CommonsUtil.getClearNullData(o[i]);
								}
								
								
							}else{
								tjo.put(cloumnsName[i], CommonsUtil.getClearNullData(o[i]));
								newo[i-1] = CommonsUtil.getClearNullData(o[i]);
							}
							
						}else{
							if(i != 6){
								tjo.put(cloumnsName[i], CommonsUtil.getClearNullData(o[i]));
								if( i < 6){
									newo[i] = CommonsUtil.getClearNullData(o[i]);
								}else{
									newo[i-1] = CommonsUtil.getClearNullData(o[i]);
								}
								
							}
							
						}
						
					}
					jsonArr.add(tjo);
					newlo.add(newo);
				}
				
			}
			lo = newlo;
			
		}else{
		if (lo != null && 0 < lo.size()) {
			
			List<Object[]> lclists = null;
			List<String> flownames = null;
			String paramName = "";
			
			String sql = "select t.flow_code,t.flow_name from PC_CD_SYSTEM_FLOW_T t";
			lclists = AuthorityUtil.commonssql(sql);
			// 生成树节点json文档
			jsonArr = new JSONArray();
			for (Object[] o : lo) {
				tjo = new JSONObject();

				for (int i = 0; i < o.length; i++) {
					if (i == 8) {
						if(CommonsUtil.getClearNullData(o[i-1]).indexOf(";") != -1){
							
							if(lclists != null && lclists.size()>0){
								String[] flowcodes = CommonsUtil.getClearNullData(o[i-1]).split(";");
								flownames = new ArrayList<String>();
								paramName = "";
								if(flowcodes != null && flowcodes.length>0){
									for (String code : flowcodes){
										for(Object[] systemflow : lclists){
											if(code.equals(CommonsUtil.getClearNullData(systemflow[0]))){
												flownames.add(CommonsUtil.getClearNullData(systemflow[1]));
											}
										}
									}
								}
								
								
								if(flownames != null && flownames.size()>0){
									for(String name :flownames){
										paramName += name+";";
									}
								}
								
								paramName = paramName.substring(0, paramName.length()-1);
								tjo.put(cloumnsName[i], paramName);
							}else{
								tjo.put(cloumnsName[i], CommonsUtil.getClearNullData(o[i]));
							}
							
							
						}else{
							tjo.put(cloumnsName[i], CommonsUtil.getClearNullData(o[i]));
						}
					}else{
						tjo.put(cloumnsName[i], CommonsUtil.getClearNullData(o[i]));
					}
					
				}
				jsonArr.add(tjo);
			}
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
	public JSONObject searchCascadeInit() throws Exception {
		JSONObject  obj = null;
		//String  sysSql = "select  a.system_code,a.system_code  from  PC_CD_LARGE_STATION_T  a   order  by  a.system_code";
		String  sysSql = "select distinct(a.system_code),a.system_name from  pc_cd_station_point_v a left join  PC_CD_LARGE_STATION_T b  "+
						" on a.system_code = b.system_code  ORDER BY NLSSORT(a.system_name,'NLS_SORT = SCHINESE_RADICAL_M')";
		String  poiSql = "select a.point_type,a.point_type  from  PC_CD_POINT_TYPE_T  a order by a.point_type";
		String  lcSql  = " select a.flow_code,a.flow_name   from  PC_CD_SYSTEM_FLOW_T  a ORDER BY NLSSORT(a.flow_code,'NLS_SORT = SCHINESE_PINYIN_M')";
		String  alaSql ="select a.alarm_code, a.alarm_value from  PC_CD_ALARM_TYPE_T  a   ORDER BY NLSSORT(a.alarm_code,'NLS_SORT = SCHINESE_PINYIN_M')";
		String  codSql ="select a.device_code,a.device_name from  PC_CD_DEVICE_FLOW_T  a   ORDER BY NLSSORT(a.device_name ,'NLS_SORT = SCHINESE_PINYIN_M')";
		
		List<Object[]>  sysDataSet = commonDao.searchEverySql(sysSql);
		List<Object[]>  poiDataSet = commonDao.searchEverySql(poiSql);
		List<Object[]>  lcDataSet = commonDao.searchEverySql(lcSql);
		List<Object[]>  alaDataSet = commonDao.searchEverySql(alaSql);
		List<Object[]>  codDataSet = commonDao.searchEverySql(codSql);


		JSONArray  sysArr = null;
		JSONArray  poiArr = null;
		JSONArray  lcArr = null;
		JSONArray  alaArr = null;
		JSONArray  codArr = null;
		
		if(sysDataSet !=null && sysDataSet.size()>0){
			sysArr = new JSONArray();
			for (Object[] entry : sysDataSet) {
				obj = new JSONObject();
				obj.put("text", entry[1]);
				obj.put("id", entry[0]);
				sysArr.add(obj);
			}
		}
		if(poiDataSet !=null && poiDataSet.size()>0){
			poiArr = new JSONArray();
			for (Object[] entry : poiDataSet) {
					obj = new JSONObject();
					obj.put("text", entry[1]);
					obj.put("id", entry[0]);
					poiArr.add(obj);
			}
		}
		if(lcDataSet !=null && lcDataSet.size()>0){
			lcArr = new JSONArray();
			for (Object[] entry : lcDataSet) {
					obj = new JSONObject();
					obj.put("text", entry[1]);
					obj.put("id", entry[0]);
					lcArr.add(obj);
			}
		}
		if(alaDataSet !=null && alaDataSet.size()>0){
			alaArr = new JSONArray();
			for (Object[] entry : alaDataSet) {
					obj = new JSONObject();
					obj.put("text", entry[1]);
					obj.put("id", entry[0]);
					alaArr.add(obj);
			}
		}
		if(codDataSet !=null && codDataSet.size()>0){
			codArr = new JSONArray();
			for (Object[] entry : codDataSet) {
					obj = new JSONObject();
					obj.put("text", entry[1]);
					obj.put("id", entry[0]);
					codArr.add(obj);
			}
		}
		obj = new JSONObject();
		obj.put("sysText", sysArr);
		obj.put("poiText", poiArr);
		obj.put("lcText", lcArr);
		obj.put("alaText", alaArr);
		obj.put("codText", codArr);
		return obj;
	}

	@Override
	public boolean deleteData(String sMALLSTATIONID) throws Exception {
		String sql ="  delete from  PC_CD_STATION_POINT_T a  where a.STATION_POINT_ID='"+sMALLSTATIONID+"'";
		return commonDao.removeData(sql);
	}

	@Override
	public boolean saveData(PcCdStationPointT sp) throws Exception {
		List<PcCdStationPointT>  list  = new ArrayList<PcCdStationPointT>();
		list.add(sp);
		return commonDao.addOrUpdateDatas(list);
	}

	@Override
	public List<PcCdStationPointT> searchCheckD(String sTATIONPOINTID,
			String tAGNAME) throws Exception {
		String hql ="  from  PcCdStationPointT a  where 1=1";
		if(sTATIONPOINTID !=null  &&  !"".equals(sTATIONPOINTID)){
			 hql +=" and  a.stationPointId ='"+sTATIONPOINTID+"'";
		}
		if(tAGNAME !=null  &&  !"".equals(tAGNAME)){
			 hql +=" and  a.tagName ='"+tAGNAME+"'";
		}
		List<PcCdStationPointT> list  =null;
		list = (List<PcCdStationPointT>) commonDao.searchClassQuery(hql);
		return list;
	}

	@Override
	public JSONArray searchOnChange(String code) throws Exception {
		String  sql  = "select  b.device_code,b.device_name  from  PC_CD_LARGE_STATION_T  a  "+
						"left join  PC_CD_DEVICE_FLOW_T b  on  a.system_code = b.system_code "+
						"where a.system_code='"+code+"' and  b.device_name is not null   ORDER BY NLSSORT(b.device_name ,'NLS_SORT = SCHINESE_PINYIN_M')";
		List<Object[]> dataSet = commonDao.searchEverySql(sql);
		JSONArray arr = null;
		JSONObject  obj = null;
		if(dataSet !=null &&  dataSet.size()>0){
			arr = new JSONArray();
			for (Object[] entry : dataSet) {
				obj = new JSONObject();
				obj.put("text", entry[1]);
				obj.put("id", entry[0]);
				arr.add(obj);
			}
			return arr;
		}
		arr = new JSONArray();
		obj = new JSONObject();
		obj.put("text", "");
		obj.put("id", "");
		arr.add(obj);
		return arr;
	}

	@Override
	public JSONArray searchOnChangeLC(String code) throws Exception {
		String  sql  = " select b.flow_code,b.flow_name from PC_CD_LARGE_STATION_T a  left join  PC_CD_SYSTEM_FLOW_T b  on a.system_code = b.system_code   "+
			" where a.system_code ='"+code+"'  and  b.flow_name is not null "+
			" ORDER BY NLSSORT(b.flow_code ,'NLS_SORT = SCHINESE_PINYIN_M')";
		List<Object[]> dataSet = commonDao.searchEverySql(sql);
		JSONArray arr = null;
		JSONObject  obj = null;
		if(dataSet !=null &&  dataSet.size()>0){
		arr = new JSONArray();
		for (Object[] entry : dataSet) {
		obj = new JSONObject();
		obj.put("text", entry[1]);
		obj.put("id", entry[0]);
		arr.add(obj);
		}
		return arr;
		}
		arr = new JSONArray();
		obj = new JSONObject();
		obj.put("text", "");
		obj.put("id", "");
		arr.add(obj);
		return arr;
	}

	@Override
	public JSONArray searchOnChangeLCeDIT(String code) throws Exception {
		String  sql  = " select b.flow_code,b.flow_name from PC_CD_LARGE_STATION_T a  left join  PC_CD_SYSTEM_FLOW_T b  on a.system_code = b.system_code   "+
			" where a.system_code ='"+code+"'  and  b.flow_name is not null "+
			" ORDER BY NLSSORT(b.flow_code ,'NLS_SORT = SCHINESE_PINYIN_M')";
		List<Object[]> dataSet = commonDao.searchEverySql(sql);
		JSONArray arr = null;
		JSONObject  obj = null;
		if(dataSet !=null &&  dataSet.size()>0){
		arr = new JSONArray();
		for (Object[] entry : dataSet) {
		obj = new JSONObject();
		obj.put("text", entry[1]);
		obj.put("id", entry[0]);
		arr.add(obj);
		}
		return arr;
		}
		arr = new JSONArray();
		obj = new JSONObject();
		obj.put("text", "");
		obj.put("id", "");
		arr.add(obj);
		return arr;
	}

}
