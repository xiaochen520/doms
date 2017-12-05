package com.echo.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.beans.PageControl;
import com.echo.dao.CommonDao;
import com.echo.dao.InstruMentationDao;
import com.echo.dao.PageDao;
import com.echo.dto.PcCdInstruMentationDT;
import com.echo.dto.PcCdInstruMentationT;
import com.echo.dto.PcCdServerNodeT;
import com.echo.service.InstruMentationService;
import com.echo.util.CommonsUtil;

public class InstruMentationServiceImpl implements InstruMentationService {
	private InstruMentationDao instruMentationDao;
	private CommonDao commonDao;
	private PageDao pageDao;
	public InstruMentationDao getInstruMentationDao() {
		return instruMentationDao;
	}
	public void setInstruMentationDao(InstruMentationDao instruMentationDao) {
		this.instruMentationDao = instruMentationDao;
	}
	public PageDao getPageDao() {
		return pageDao;
	}
	public void setPageDao(PageDao pageDao) {
		this.pageDao = pageDao;
	}
	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}
	
	public HashMap<String, Object> searchInstru(String oilName,
			String objectCode, String areaName, String staName,
			String ownObject, String iNSTRU_1TN, String iNSTRUCLN,
			String fACTORYNS, String iNSTRUC3N, String iNSTRUSTNS,
			String superStns, String txtDate, String txtDate1, int pageNo,
			String sort, String order, int rowsPerpage, String totalNumFlag)throws Exception {


		JSONArray jsonArr = null;
		HashMap<String,Object>  map = new HashMap<String, Object>();
		JSONObject tjo = null;
		String cloums = "select   ";
		String formTable = "  from pc_cd_instru_mentation_v2 a  where 1=1 ";
		String totalNum = "select count(*) ";
		
		if(!oilName.equals("")&&oilName!=null&&!oilName.equals("undefined")){
			formTable=formTable+" and DWname ='"+oilName+"'";
		}
		if(!objectCode.equals("")&&objectCode!=null&&!objectCode.equals("undefined")){
			formTable=formTable+" and object_type_name ='"+objectCode+"'";
		}
		
		if(!areaName.equals("")&&areaName!=null&&!areaName.equals("undefined")){
			formTable=formTable+" and QKname ='"+areaName+"'";
		}
		if(!staName.equals("")&&staName!=null&&!staName.equals("undefined")){
			formTable=formTable+" and ZHname ='"+staName+"'";
		}
		if(!ownObject.equals("")&&ownObject!=null&&!ownObject.equals("undefined")){
			formTable=formTable+" and structurename ='"+ownObject+"'";
		}
		if(!iNSTRU_1TN.equals("")&&iNSTRU_1TN!=null&&!iNSTRU_1TN.equals("undefined")){
			formTable=formTable+" and INSTRU_1TN ='"+iNSTRU_1TN+"'";
		}
		if(!iNSTRUCLN.equals("")&&iNSTRUCLN!=null&&!iNSTRUCLN.equals("undefined")){
			formTable=formTable+" and INSTRU_CLN like '"+iNSTRUCLN+"'";
		}
		if(!fACTORYNS.equals("")&&fACTORYNS!=null&&!fACTORYNS.equals("undefined")){
			formTable=formTable+" and SFACTORY_NS ='"+fACTORYNS+"'";
		}
		if(!iNSTRUC3N.equals("")&&iNSTRUC3N!=null&&!iNSTRUC3N.equals("undefined")){
			formTable=formTable+" and INSTRU_C3N ='"+iNSTRUC3N+"'";
		}
		if(!iNSTRUSTNS.equals("")&&iNSTRUSTNS!=null&&!iNSTRUSTNS.equals("undefined")){
			formTable=formTable+" and INSTRU_STNS ='"+iNSTRUSTNS+"'";
		}
		if(!superStns.equals("")&&superStns!=null&&!superStns.equals("undefined")){
			formTable=formTable+" and SJRTU_cln ='"+superStns+"'";
		}
		
		if(!txtDate.equals("")&&txtDate!=null&&!txtDate.equals("undefined") &&!txtDate1.equals("")&&txtDate1!=null&&!txtDate1.equals("undefined")){
			formTable=formTable+" and  rlast_odate between TO_DATE('"+txtDate+"','YYYY-MM-DD') and TO_DATE('"+txtDate1+"','YYYY-MM-DD')  ";
		}
		String[] cloumnsName = null;
		if ("export".equals(totalNumFlag)) {
			cloumnsName = new String[]{"STRUCTURENAME","OBJECT_TYPE_NAME","DWNAME","QKNAME","ZHNAME","SWITCH_IN_DESC",
					"INSTRU_CLN","INSTRU_STNS","SFACTORY_NS","MFACTORY_NS","INSTRU_C3N","SJRTU_CLN","IP",
					"IPINSTRU_CLN","STATION_NO","COMMUNICATION_PORT","REMARK","RLAST_OPER","RLAST_ODATE","INSTRU_1TN","INSTRUS_ID",
					"INSTRUS_IDN","SCADA_NODE","OBJECT_CODE","INSTRU_CLC","INSTRU_STVA","SJRTU_CLC","ORG_ID"};
		}else{
			cloumnsName = new  String[]{"STRUCTURENAME","OBJECT_TYPE_NAME","DWNAME","QKNAME","ZHNAME","SWITCH_IN_DESC",
					"INSTRU_CLN","INSTRU_STNS","SFACTORY_NS","MFACTORY_NS","INSTRU_C3N","SJRTU_CLN","IP",
					"IPINSTRU_CLN","STATION_NO","COMMUNICATION_PORT","REMARK","RLAST_OPER","RLAST_ODATE","INSTRU_1TN","INSTRUS_ID",
					"INSTRUS_IDN","DATA_AMC","DESIGN_DATE","COMMISSIONING_DATE","SCADA_NODE","CODE","INSCODE",
					"SFACTORY_NF","INSTRUMENT_ID","DWID","QKID","ZHID","ORG_ID","OBJECT_CODE","INSTRU_CLC","INSTRU_1TC",
					"SJRTU_CLC","INSTRU_STVA"} ;
		}
		
		String kk="STRUCTURENAME";
		for(int m=1;m<cloumnsName.length;m++){
			if("RLAST_ODATE".equals(cloumnsName[m])){
				kk=kk+","+"to_char(RLAST_ODATE,'YYYY-MM-DD hh24:mi:ss') as RLAST_ODATE";
			} else{
				kk=kk+","+cloumnsName[m];
			}
		}
		String pjsql = cloums + kk+formTable;
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
		if(!"".equals(sort) && !"STRUCTURENAME".equals(sort)){
			for (String cloumn : cloumnsName) {
				if(cloumn.equals(sort)){
					pjsql +=" order by " + cloumn + " " + order;
					break;
				}
			}
		}
		else {
			pjsql +=" order by STRUCTURENAME asc";
		}
		//计算分页
		PageControl control = new PageControl();
		//当前页
		List<Object[]> lo = new ArrayList<Object[]>();
		control.setInt_num(rowsPerpage);
		if ("export".equals(totalNumFlag)) {
			lo = commonDao.searchEverySql(pjsql);
		}
		else {
			control.init(pageNo, total);
			//开始条数
			int start = control.getStart()-1;
			lo = commonDao.searchPaging(pjsql,start,rowsPerpage,cloumnsName);
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
					tjo.put(cloumnsName[i], CommonsUtil.getClearNullData(o[i]));
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
	
	public JSONObject cascadeInit() {
		String oilNameSql = "select  a.org_id, a.structurename from  pc_cd_station_Logic_v  a ";
		String objectCodeSql = "select  a.object_code , a.object_type_name from  pc_cd_object_type_t a order by NLSSORT(a.object_type_name ,'NLS_SORT=SCHINESE_PINYIN_M')";
		String areaNameSql = "select  a.qkid , a.qkmc from  pc_cd_area_logic_t a order by NLSSORT(a.qkmc ,'NLS_SORT=SCHINESE_PINYIN_M')";
		String staNameSql = "select  a.blockstationid , a.blockstation_name from  pc_cd_station_t a order by NLSSORT(a.blockstation_name ,'NLS_SORT=SCHINESE_PINYIN_M')";
		String instru1TnSql ="select  a.instru_1tc , a.instru_1tn from  pc_cd_instru_type_t a order by NLSSORT(a.instru_1tc ,'NLS_SORT=SCHINESE_PINYIN_M')"; 
		String factoryNsSql  ="select  a.factory_uc , a.factory_ns from  PC_CD_FACTORY_CODE_T a order by NLSSORT(a.factory_ns ,'NLS_SORT=SCHINESE_PINYIN_M')";
		String instruC3nSql ="select  a.instru_1tc , a.instru_c3n from  pc_cd_instru_version_t a order by NLSSORT(a.instru_c3n ,'NLS_SORT=SCHINESE_PINYIN_M')";
		String instruStnsSql =  "select  a.instru_stva , a.instru_stns from  pc_cd_instru_status_t a order by NLSSORT(a.instru_stva ,'NLS_SORT=SCHINESE_PINYIN_M')";
		String superStnsSql = "select  a.instru_clc , a.instru_cln from   pc_cd_instru_class_t a where a.sjrtu_bs='1' order by NLSSORT(a.instru_cln ,'NLS_SORT=SCHINESE_PINYIN_M')";
		
		
		JSONArray oilNameArr = null;
		JSONArray objectCodeArr = null;
		JSONArray areaNameArr = null;
		JSONArray staNameArr = null;
		JSONArray instru1TnArr = null;
		JSONArray factoryNsArr = null;
		JSONArray instruC3nArr = null;
		JSONArray instruStnsArr = null;
		JSONArray superStnsArr = null;
		
		JSONObject jsobj = null;
		List<Object[]> oilNameSet = instruMentationDao.queryInfo(oilNameSql);
		List<Object[]> objectCodeSet = instruMentationDao.queryInfo(objectCodeSql);
		List<Object[]> areaNameSet = instruMentationDao.queryInfo(areaNameSql);
		List<Object[]> staNameSet = instruMentationDao.queryInfo(staNameSql);
		List<Object[]> instru1TnSet = instruMentationDao.queryInfo(instru1TnSql);
		List<Object[]> factoryNsSet = instruMentationDao.queryInfo(factoryNsSql);
		List<Object[]> instruC3nSet = instruMentationDao.queryInfo(instruC3nSql);
		List<Object[]> instruStnsSet = instruMentationDao.queryInfo(instruStnsSql);
		List<Object[]> superStnsSet = instruMentationDao.queryInfo(superStnsSql);
		
		if(oilNameSet != null && oilNameSet.size()>0){
			oilNameArr = new JSONArray();   
			for(Object[] entry : oilNameSet){
				jsobj = new JSONObject();
				jsobj.put("text", entry[1]);
				jsobj.put("id",entry[0]);
				oilNameArr.add(jsobj);
			}
		}
		
		if(objectCodeSet != null && objectCodeSet.size()>0){
			objectCodeArr = new JSONArray();   
			for(Object[] entry : objectCodeSet){
				jsobj = new JSONObject();
				jsobj.put("text", entry[1]);
				jsobj.put("id",entry[0]);
				objectCodeArr.add(jsobj);
			}
		}
		
		if(areaNameSet != null && areaNameSet.size()>0){
			areaNameArr = new JSONArray();   
			for(Object[] entry : areaNameSet){
				jsobj = new JSONObject();
				jsobj.put("text", entry[1]);
				jsobj.put("id",entry[0]);
				areaNameArr.add(jsobj);
			}
		}
		
		if(staNameSet != null && staNameSet.size()>0){
			staNameArr = new JSONArray();   
			for(Object[] entry : staNameSet){
				jsobj = new JSONObject();
				jsobj.put("text", entry[1]);
				jsobj.put("id",entry[0]);
				staNameArr.add(jsobj);
			}
		}
		if(instru1TnSet != null && instru1TnSet.size()>0){
			instru1TnArr = new JSONArray();   
			for(Object[] entry : instru1TnSet){
				jsobj = new JSONObject();
				jsobj.put("text", entry[1]);
				jsobj.put("id",entry[0]);
				instru1TnArr.add(jsobj);
			}
		}
		if(factoryNsSet != null && factoryNsSet.size()>0){
			factoryNsArr = new JSONArray();   
			for(Object[] entry : factoryNsSet){
				jsobj = new JSONObject();
				jsobj.put("text", entry[1]);
				jsobj.put("id",entry[0]);
				factoryNsArr.add(jsobj);
			}
		}
		if(instruC3nSet != null && instruC3nSet.size()>0){
			instruC3nArr = new JSONArray();   
			for(Object[] entry : instruC3nSet){
				jsobj = new JSONObject();
				jsobj.put("text", entry[1]);
				jsobj.put("id",entry[0]);
				instruC3nArr.add(jsobj);
			}
		}
		if(instruStnsSet != null && instruStnsSet.size()>0){
			instruStnsArr = new JSONArray();   
			for(Object[] entry : instruStnsSet){
				jsobj = new JSONObject();
				jsobj.put("text", entry[1]);
				jsobj.put("id",entry[0]);
				instruStnsArr.add(jsobj);
			}
		}
		if(superStnsSet != null && superStnsSet.size()>0){
			superStnsArr = new JSONArray();   
			for(Object[] entry : superStnsSet){
				jsobj = new JSONObject();
				jsobj.put("text", entry[1]);
				jsobj.put("id",entry[0]);
				superStnsArr.add(jsobj);
			}
		}
		jsobj = new JSONObject();
		jsobj.put("oilNameText",oilNameArr);
		jsobj.put("objectCodeText",objectCodeArr);
		jsobj.put("areaNameText",areaNameArr);
		jsobj.put("staNameText",staNameArr);
		jsobj.put("instru1TnText", instru1TnArr);
		jsobj.put("factoryNsText", factoryNsArr);
		jsobj.put("instruC3nText", instruC3nArr);
		jsobj.put("instruStnsText", instruStnsArr);
		jsobj.put("superStnsText", superStnsArr);
		return jsobj;
	}

	@SuppressWarnings("unchecked")
	public JSONArray searchOnChangeArea(String oilName, String objectCode,String areaName,String args)throws Exception{
		JSONArray jsonArr = null;
		JSONObject jsobj = null;
		String  sql  = null;
//		String  sql ="SELECT DISTINCT(A.ZHID) ,  A.ZHname  FROM   PC_CD_INSTRU_MENTATION_V2  A LEFT JOIN  PC_CD_ORG_T B ON A.ZHname = B.ORG_ID  "+
//					  "WHERE  DWID='"+oilName+"'  AND QKID='"+areaName+"' AND object_code='"+objectCode+"'   "+
//					 "ORDER BY nlssort(A.ZHname ,'nls_sort = schinese_pinyin_m')";
		if(args !=null && args.equals("ALL")){
			sql ="SELECT DISTINCT(A.ZHID) ,  b.structurename  FROM   Pc_Cd_Org_Table_Vx  A   left join  pc_cd_org_t b on a.ZHID = b.org_id "+
			  "WHERE  DWID='"+oilName+"'  AND QKID='"+areaName+"' AND object_code='"+objectCode+"'  and b.structuretype ='7' "+
			 "ORDER BY nlssort(b.structurename ,'nls_sort = schinese_pinyin_m')";
		}else{
			sql ="SELECT DISTINCT(A.ZHID) ,  A.ZHname  FROM   PC_CD_INSTRU_MENTATION_V2  A "+
			  "WHERE  DWID='"+oilName+"'  AND QKID='"+areaName+"' AND object_code='"+objectCode+"' and structuretype ='7' "+
			 "ORDER BY nlssort(A.ZHname ,'nls_sort = schinese_pinyin_m')";
		}
		List<Object[]> dataSet = instruMentationDao.searchCode(sql);
		if(dataSet != null && dataSet.size()>0){
			//生成树节点json文档
			jsonArr = new JSONArray();   
			for(Object[] entry : dataSet){
				jsobj = new JSONObject();
				jsobj.put("text", entry[1]);
				jsobj.put("id",entry[0]);
				jsonArr.add(jsobj);
			}
			return jsonArr;
		}
		jsobj = new JSONObject();
		jsobj.put("text", "");
		jsobj.put("id","");
		jsonArr = new JSONArray();
		jsonArr.add(jsobj);
		return jsonArr;
	}
	
	public JSONArray searchOnChangeOwnObject(String oilName,String objectCode,String areaName,String staName,String args)throws Exception{
		JSONArray jsonArr =null;
		JSONObject jsobj =null;
		//String sql =" SELECT  ORG_ID ,STRUCTURENAME FROM  PC_CD_INSTRU_MENTATION_V2 WHERE DWID='"+oilName+"' AND ZHID ='"+staName+"' AND QKID='"+areaName+"' AND object_code='"+objectCode+"' "+
		//		" GROUP BY ORG_ID ,STRUCTURENAME HAVING COUNT(ORG_ID)>1  ORDER BY   nlssort(STRUCTURENAME , 'nls_sort = schinese_pinyin_m')";
		String sql  =  null;
		if(args !=null && args.equals("ALL")){
			sql  = " SELECT DISTINCT(A.org_id) ,  A.structurename  FROM   Pc_Cd_Org_Table_Vx  A  "+
			 	"	 WHERE DWID='"+oilName+"' AND ZHID ='"+staName+"' AND QKID='"+areaName+"' AND object_code='"+objectCode+"' "+ 
			 	"		ORDER BY nlssort(A.STRUCTURENAME ,'nls_sort = schinese_pinyin_m')";
		}else{
			sql  = "SELECT DISTINCT(A.ORG_ID) ,  A.STRUCTURENAME  FROM   PC_CD_INSTRU_MENTATION_V2  A "+
				   "	WHERE DWID='"+oilName+"' AND ZHID ='"+staName+"' AND QKID='"+areaName+"' AND object_code='"+objectCode+"' "+ 
				   "		ORDER BY nlssort(A.STRUCTURENAME ,'nls_sort = schinese_pinyin_m')";
		}
		
		List<Object[]> dataSet = instruMentationDao.queryInstru(sql);
		if(dataSet !=null && dataSet.size()>0){
			//生成JSON文档
			jsonArr = new JSONArray();
			for(Object[] entry : dataSet){
				jsobj = new JSONObject();
				jsobj.put("text", entry[1]);
				jsobj.put("id", entry[0]);
				jsonArr.add(jsobj);
			}
			return jsonArr;
		}
		jsobj = new JSONObject();
		jsobj.put("text", "");
		jsobj.put("id", "");
		jsonArr = new JSONArray();
		jsonArr.add(jsobj);
		return jsonArr;
	}
	
	public JSONArray searchOnChangeInstru(String objectCode,String INSTRU_1TN) throws Exception{
		JSONArray jsonArr =null;
		JSONObject jsobj =null;
		String sql =" select t.INSTRU_CLC, t.INSTRU_CLN  from pc_cd_instru_capply_v t  where   t.object_code='"+objectCode+"'  and INSTRU_1TC ='"+INSTRU_1TN+"' "+
					"order by  nlssort(t.INSTRU_CLN ,  'nls_sort=schinese_pinyin_m')";
		List<Object[]> dataSet = instruMentationDao.queryInstru(sql);
		if(dataSet !=null && dataSet.size()>0){
			//生成JSON文档
			jsonArr = new JSONArray();
			for(Object[] entry : dataSet){
				jsobj = new JSONObject();
				jsobj.put("text", entry[1]);
				jsobj.put("id", entry[0]);
				jsonArr.add(jsobj);
			}
			return jsonArr;
		}
		jsobj = new JSONObject();
		jsobj.put("text", "");
		jsobj.put("id", "");
		jsonArr = new JSONArray();
		jsonArr.add(jsobj);
		return jsonArr;
	}
	
	public JSONArray searchOnChangeFactoryns(String INSTRU_CLN)throws Exception{
		JSONArray jsonArr =null;
		JSONObject jsobj =null;
		String sql =" SELECT distinct(a.sfactory_uc) ,a.sfactory_ns FROM  pc_cd_instru_namec_v A "+
					"left join PC_CD_FACTORY_CODE_T  b  on A.sfactory_uc = b.factory_uc  where a.INSTRU_CLc='"+INSTRU_CLN+"' "+
					"order by nlssort(a.sfactory_ns,'NLS_SORT=SCHINESE_STROKE_M')";
		List<Object[]> dataSet = instruMentationDao.queryInstru(sql);
		if(dataSet !=null && dataSet.size()>0){
			//生成JSON文档
			jsonArr = new JSONArray();
			for(Object[] entry : dataSet){
				jsobj = new JSONObject();
				jsobj.put("text", entry[1]);
				jsobj.put("id", entry[0]);
				jsonArr.add(jsobj);
			}
			return jsonArr;
		}
		jsobj = new JSONObject();
		jsobj.put("text", "");
		jsobj.put("id", "");
		jsonArr = new JSONArray();
		jsonArr.add(jsobj);
		return jsonArr;
	}
	
	@Override
	public JSONArray searchOnChangeInstruc3n(String INSTRU_CLN,String FACTORY_NS)throws Exception{
		JSONArray jsonArr =null;
		JSONObject jsobj =null;
		String sql ="  SELECT instru_c3c,instru_c3n FROM  pc_cd_instru_namec_v   where " +
					"	instru_clc ='"+INSTRU_CLN+"'  and sfactory_uc ='"+FACTORY_NS+"' "+
					"order by nlssort(instru_c3n,'NLS_SORT=SCHINESE_STROKE_M')";
		List<Object[]> dataSet = instruMentationDao.queryInstru(sql);
		if(dataSet !=null && dataSet.size()>0){
			//生成JSON文档
			jsonArr = new JSONArray();
			for(Object[] entry : dataSet){
				jsobj = new JSONObject();
				jsobj.put("text", entry[1]);
				jsobj.put("id", entry[0]);
				jsonArr.add(jsobj);
			}
			return jsonArr;
		}
		jsobj = new JSONObject();
		jsobj.put("text", "");
		jsobj.put("id", "");
		jsonArr = new JSONArray();
		jsonArr.add(jsobj);
		return jsonArr;
	}
	public JSONArray searchOnChangeSuperStns(String objectCode) throws Exception {
		JSONArray jsonArr = null;
		JSONObject jsobj = null;
		String sql ="  select t.INSTRU_CLC, t.INSTRU_CLN   from  pc_cd_instru_capply_v t   where  "+
					" t.object_code='"+objectCode+"'    and  SJRTU_BS ='1' "+
					" order by nlssort(t.INSTRU_CLN ,'nls_sort=schinese_pinyin_m')";
		List<Object[]> dataSet = instruMentationDao.searchCode(sql);
		if(dataSet != null && dataSet.size()>0){
			jsonArr = new JSONArray();   
			for(Object[] entry : dataSet){
				jsobj = new JSONObject();
				jsobj.put("text", entry[1]);
				jsobj.put("id",entry[0]);
				jsonArr.add(jsobj);
			}
			return jsonArr;
		}
		jsobj = new JSONObject();
		jsobj.put("text", "");
		jsobj.put("id","");
		jsonArr = new JSONArray();
		jsonArr.add(jsobj);
		return jsonArr;
	}
	
	@Override
	public JSONArray searchGHID() throws Exception {

		JSONArray jsonArr = null;
		JSONObject jsobj = null;
		String sql =" select   a.instrus_id,a.instrus_idn from  PC_CD_INSTRU_SUPPLYC_T  a  "+
					" order by nlssort(a.instrus_id ,'nls_sort=schinese_pinyin_m')";
		List<Object[]> dataSet = instruMentationDao.searchCode(sql);
		if(dataSet != null && dataSet.size()>0){
			jsonArr = new JSONArray();   
			for(Object[] entry : dataSet){
				jsobj = new JSONObject();
				jsobj.put("text", entry[1]);
				jsobj.put("id",entry[0]);
				jsonArr.add(jsobj);
			}
			return jsonArr;
		}
		jsobj = new JSONObject();
		jsobj.put("text", "");
		jsobj.put("id","");
		jsonArr = new JSONArray();
		jsonArr.add(jsobj);
		return jsonArr;
	
	}
	@Override
	public JSONArray searchOnChangeYBGhuo(String objectCode, String iNSTRU_1TN)throws Exception {


		JSONArray jsonArr = null;
		JSONObject jsobj = null;
		String sql =" select distinct(u.instrus_id),u.instrus_idn  from pc_cd_instru_capply_v t "+
					" left join   PC_CD_INSTRU_NAMEC_T y on t.INSTRU_CLC = y.instru_clc   "+
					" left join PC_CD_INSTRU_SUPPLYC_T u on u.instrus_id = y.instrus_id   "+
					"where t.object_code='"+objectCode+"' and  t.instru_1tc='"+iNSTRU_1TN+"'  "+
					" order by nlssort(u.instrus_id ,'nls_sort=schinese_pinyin_m')";
		List<Object[]> dataSet = instruMentationDao.searchCode(sql);
		if(dataSet != null && dataSet.size()>0){
			jsonArr = new JSONArray();   
			for(Object[] entry : dataSet){
				jsobj = new JSONObject();
				jsobj.put("text", entry[1]);
				jsobj.put("id",entry[0]);
				jsonArr.add(jsobj);
			}
			return jsonArr;
		}
		jsobj = new JSONObject();
		jsobj.put("text", "");
		jsobj.put("id","");
		jsonArr = new JSONArray();
		jsonArr.add(jsobj);
		return jsonArr;
	
	
	}

	
	public boolean removeInstrumention(String INSTRUMENT_ID){
		//String[] sqls = new String[1];
		String sqls= " DELETE from pc_cd_instru_mentation_t d where d.instrument_id = '"+INSTRUMENT_ID+"'";
		//sqls[1] = " DELETE from pc_cd_org_t t where t.org_id = '"+orgid+"'";
		return instruMentationDao.removeInstrumention(sqls);
	}
	
	
	public boolean SaveOrUpdateInstru(PcCdInstruMentationT im) throws Exception {
		return instruMentationDao.SaveOrUpdateInstru(im);
	}
	@Override
	public boolean SaveOrUpdateInstruDT(PcCdInstruMentationDT bm)
			throws Exception {
		List<PcCdInstruMentationDT> dtList = new ArrayList<PcCdInstruMentationDT>();
		dtList.add(bm);
		return commonDao.addOrUpdateDatas(dtList);
	}
	
	public List<PcCdInstruMentationT> searchYbId(String Id, String name , String org)throws Exception {
		String hql = " FROM  PcCdInstruMentationT a where 1=1";
		if(Id !=null && !"".equals(Id)){
			hql +=" and a.instrumentationid ='"+Id+"'";
		}
		if(name !=null && !"".equals(name)){
			hql +=" and a.instrumentationName = '"+name+"' ";
		}
		if(org !=null && !"".equals(org)){
			hql +=" and a.orgid = '"+org+"' ";
		}
		return instruMentationDao.searchYbId(hql);
}
	
	
//	@Override
//	public List<PcCdInstruNamecT> getFac(String factoryNf1) throws Exception {
//
//		List<PcCdInstruNamecT> sertverList = null;
//		
//		String sql = "FROM PcCdInstruNamecT s where 1=1 ";
//		
//		if(factoryNf1 != null && !"".equals(factoryNf1)){
//			sql += " and s.factoryUc = '"+factoryNf1+"'";
//		}
//		sertverList =  instruMentationDao.getFac(sql);
//		
//		
//		return sertverList;
//	
//	}
//	@Override
//	public List<PcCdInstruNamecT> getCln(String instruCln) throws Exception {
//
//		List<PcCdInstruNamecT> sertverList = null;
//		String sql = "FROM PcCdInstruNamecT s where 1=1 ";
//		
//		if(instruCln != null && !"".equals(instruCln)){
//			sql += " and s.instruClc = '"+instruCln+"'";
//		}
//		return sertverList;
//	}
//	@Override
//	public List<PcCdInstruNamecT> getPcCdInstruNamecT(String id)
//			throws Exception {
//		List<PcCdInstruNamecT> sertverList = null;
//		String sql = "FROM PcCdInstruNamecT s where 1=1 ";
//		
//		if(id != null && !"".equals(id)){
//			sql += " and s.instruNamecid = '"+id+"'";
//		}
//		
//		sertverList =  instruMentationDao.getFac(sql);
//		return sertverList;
//	}
	@Override
	public List<Object[]> searchAllData(String oilName, String objectCode, String ownObject, String staName, String areaName, String iNSTRUCLN, String iNSTRUSTNS, String superStns, String pointCode, String alamOr, String myDate)throws Exception{
		String  sql = "select  ROW_NUMBER() OVER (ORDER BY POINT_TYPE ) AS XUHAO ,point_type,tag_desc,ALARM_OR,ALARM_VALUE, "+ 
					" ALARM_LIMITLL,ALARM_LIMITL,ALARM_LIMITH,ALARM_LIMITHH,RANGES_DOWN,RANGES_UPPER,IFIX_UNIT,  "+
					" '' as DQDDX, '' as DQDX,'' as DQGX,'' as DQGGX,'' as DQValue, SCADA_NODE,INSCODE,POINT_CODE, "+
					" POINT_NAME, SJRTU_CLN,INSTRU_CLN,STRUCTURENAME,OBJECT_TYPE_NAME,ZHNAME,QKNAME, "+
					" DWNAME,INSTRU_STNS,CONTROL_OR,HISTORY_CURVE,ORACLE_SAVE "+
					" from  PC_CD_INSTRU_POINTT_T  where RLAST_ODATE = to_date('"+myDate+"' , 'YYYY-MM-DD HH24:MI:SS')";
		
		List<Object[]> dataSet = null;
		dataSet = instruMentationDao.queryInfo(sql);
		return dataSet;
	}

	public List<Object[]> searchOilName(String ORG_ID , String type) throws Exception {
		String sql ="";
		if(type !=null && type.equals("7")){
			 sql = "SELECT  a.bname,a.aname ,structuretype  FROM  PC_CD_ORG_TREE_V a where  a.org_id='"+ORG_ID+"'";
		}
		if(type !=null && type.equals("8")){
			 sql = "SELECT  a.cname,a.bname ,structuretype FROM  PC_CD_ORG_TREE_V a where  a.org_id='"+ORG_ID+"'";
		}
		if(type !=null && type.equals("9")){
			 sql = "SELECT  a.dname,a.cname ,structuretype FROM  PC_CD_ORG_TREE_V a where  a.org_id='"+ORG_ID+"'";
		}
		if(type !=null && type.equals("10")){
			 sql = "SELECT  a.cname,a.bname ,structuretype FROM  PC_CD_ORG_TREE_V a where  a.org_id='"+ORG_ID+"'";
		}
		
		List<Object[]> dataSet = null;
		dataSet = instruMentationDao.queryInfo(sql);
		return dataSet;
	}
	
	@Override
	public List<Object[]> searchMafDatas(String args) throws Exception {
		String  sql = "";
//		if( ptable !=null  &&  ptable.equals("ptable")){
//			sql = "select count(*) from  pc_cd_point_table_v az where 1=1 ";
//		}
		//String  sql ="select  object_type_name,instru_cln,instru_c3n  from  pc_cd_point_table_v az where 1=1 ";
		  sql ="select ROW_NUMBER() OVER (ORDER BY POINT_TYPE ) AS XUHAO, POINT_TYPE,pjTag_Name,alarm_or,alarm_value,DD,D,G,GG,RANGES_DOWN,RANGES_UPPER,"+
					 "IFIX_UNIT,DQZ,remark,insCode,POINT_CODE,ycglsb,instru_cln,myName,qkmc,object_type_name "+
					"from  pc_cd_point_table_v az where 1=1 ";
		if(args !=null  && !"".equals(args)){
			sql +=" and az.pid in  ("+args+")";
		}
		sql += " and  az.instrumentation_type='03'";
		sql +="  and az.structuretype ='8'";
		sql += " and  az.access_signs='0'";
		
		List<Object[]> dataSet = null;
		dataSet = instruMentationDao.queryInfo(sql);
		return dataSet;
	}
	@Override
	public List<PcCdInstruMentationT> searchOnlyData(String iNSTRUMENTID)
			throws Exception {
		String hql =" from PcCdInstruMentationT a  where 	1=1 ";
		if(iNSTRUMENTID !=null && !"".equals(iNSTRUMENTID)){
			hql +=" and a.instrumentId ='"+iNSTRUMENTID+"'";
		}
//		if(date !=null && !"".equals(date)){
//			hql +=" and rlastOdate =(SELECT MAX(rlastOdate) FROM PcCdInstruMentationT)'";
//		}
		//SELECT * FROM pc_cd_instru_mentation_t   t WHERE 
		//t.rlast_odate=(SELECT MAX(rlast_odate) FROM pc_cd_instru_mentation_t)  for update 
		return instruMentationDao.searchYbId(hql);
	}
	
	@Override
	public List<PcCdInstruMentationDT> searchOnlyDataDTDB(String iNSTRUMENTID)
			throws Exception {
		String hql =" from PcCdInstruMentationDT a  where 	1=1 ";
		if(iNSTRUMENTID !=null && !"".equals(iNSTRUMENTID)){
			hql +=" and a.instrumentId ='"+iNSTRUMENTID+"'";
		}
		List<?> list = null;
		list = commonDao.searchClassQuery(hql);
		return (List<PcCdInstruMentationDT>) list;
	}
	
	@Override
	public List<String> creaTable(String pName, String oilName,
			String objectCode, String ownObject, String staName,
			String areaName, String iNSTRUCLN, String iNSTRUSTNS,
			String superStns, String pointCode, String alamOr,String userid, String MyDate) throws Exception {
		return instruMentationDao.getProcedures(pName, oilName,objectCode,ownObject,staName,areaName,iNSTRUCLN,iNSTRUSTNS,superStns,pointCode,alamOr,userid,MyDate);
	}
	@Override
	public List<String> creaPIns(String pName, String iNSTRUMENTID,
			String editInstruGHID, String editInstruGHIDN,
			String editSuperName, String editObjectCode, String editInstruSBMC,
			String editInstruSJSB, String editInstruZT, String editRemark,
			String user, Date date) throws Exception {
		
		return instruMentationDao.getProceduresInsert(pName,iNSTRUMENTID,editInstruGHID,editInstruGHIDN,editSuperName,
				editObjectCode,editInstruSBMC,editInstruSJSB,editInstruZT,editRemark,user,date);
	}
	@Override
	public List<Object[]> searchUnique(String editObjectCode,
			String editSuperName, String editInstruGHIDN, String editInstruSBMC) {
		String sql="select *  from  pc_cd_instru_mentation_t a where a.object_code='"+editObjectCode+"' " +
				"  and a.org_id='"+editSuperName+"'  and a.instrus_id ='"+editInstruGHIDN+"'  and a.INSTRU_CLC ='"+editInstruSBMC+"'";
		List<Object[]> listData = commonDao.searchEverySql(sql);
		return listData;
	}

	



}
