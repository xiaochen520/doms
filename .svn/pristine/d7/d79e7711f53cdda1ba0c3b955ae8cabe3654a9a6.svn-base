package com.echo.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.beans.PageControl;
import com.echo.dao.CommonDao;
import com.echo.dto.PcCdSmallStationT;
import com.echo.service.JzcjdxxService;
import com.echo.util.CommonsUtil;

public class JzcjdxxServiceImpl implements JzcjdxxService{
	private CommonDao commonDao;

	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}

	public HashMap<String, Object> searchData(String oBJECTCODEQ,
				String iNSTRUMENTCALLEDQ, String cONTROLORQ, String aCCESSSIGNSQ,
				String cONTROLLERTYPEQ, String pOINTTYPEQ, String aLARMORQ,
				String aLARMCODEQ, String hISTORYCURVEQ, String cREATEDATEQ,
				String cREATEDATEQA, String pOINTCODEQ, int pageNo, String sort,
				String order, int rowsPerpage, String totalNumFlag)throws Exception {

		JSONArray jsonArr = null;
		HashMap<String,Object> map = new HashMap<String, Object>();
		JSONObject tjo = null;
		String cloums = "select   ";
		String formTable = "  from PC_CD_SMALL_STATION_V a  where 1=1 ";
		String totalNum = "select count(*) ";
		if(!oBJECTCODEQ.equals("")&&oBJECTCODEQ!=null&&!oBJECTCODEQ.equals("undefined")){
			formTable=formTable+" and object_type_name ='"+oBJECTCODEQ+"'";
		}
		if(!iNSTRUMENTCALLEDQ.equals("")&&iNSTRUMENTCALLEDQ!=null&&!iNSTRUMENTCALLEDQ.equals("undefined") ){
			formTable=formTable+" and YCGLSB  like '%"+iNSTRUMENTCALLEDQ+"'";
		}
		if(!cONTROLORQ.equals("")&&cONTROLORQ!=null&&!cONTROLORQ.equals("undefined") ){
			formTable=formTable+" and CONTROL_OR ='"+cONTROLORQ+"'";
		}
		if(!aCCESSSIGNSQ.equals("")&&aCCESSSIGNSQ!=null&&!aCCESSSIGNSQ.equals("undefined")){
			formTable=formTable+" and ACCESS_SIGNS ='"+aCCESSSIGNSQ+"'";
		}
		if(!cONTROLLERTYPEQ.equals("")&&cONTROLLERTYPEQ!=null&&!cONTROLLERTYPEQ.equals("undefined") ){
			formTable=formTable+" and JRKZQ ='"+cONTROLLERTYPEQ+"'";
		}
		if(!pOINTTYPEQ.equals("")&&pOINTTYPEQ!=null&&!pOINTTYPEQ.equals("undefined") ){
			formTable=formTable+" and POINT_TYPE ='"+pOINTTYPEQ+"'";
		}
		if(!aLARMORQ.equals("")&&aLARMORQ!=null&&!aLARMORQ.equals("undefined") ){
			formTable=formTable+" and ALARM_OR ='"+aLARMORQ+"'";
		}
		if(!aLARMCODEQ.equals("")&&aLARMCODEQ!=null&&!aLARMCODEQ.equals("undefined")){
			formTable=formTable+" and ALARM_VALUE ='"+aLARMCODEQ+"'";
		}
		if(!hISTORYCURVEQ.equals("")&&hISTORYCURVEQ!=null&&!hISTORYCURVEQ.equals("undefined")){
			formTable=formTable+" and HISTORY_CURVE ='"+hISTORYCURVEQ+"'";
		}
		if(!pOINTCODEQ.equals("")&&pOINTCODEQ!=null&&!pOINTCODEQ.equals("undefined")){
			formTable=formTable+" and POINT_Code  like '%"+pOINTCODEQ+"%'";
		}
	
		if(!cREATEDATEQ.equals("")&&cREATEDATEQ!=null&&!cREATEDATEQ.equals("undefined") && !cREATEDATEQA.equals("")&&cREATEDATEQA!=null&&!cREATEDATEQA.equals("undefined")){
			
			formTable=formTable+" and CREATE_DATE between to_date('"+cREATEDATEQ+"','YYYY-MM-DD') AND to_date('"+cREATEDATEQA+"','YYYY-MM-DD')";
		}
		//if(!cREATEDATEQ.equals("")&&cREATEDATEQ!=null&&!cREATEDATEQ.equals("undefined")){
			
		//}
		//if(!cREATEDATEQA.equals("")&&cREATEDATEQA!=null&&!cREATEDATEQA.equals("undefined")){
			
		//}
		String[] cloumnsName = null;
		if ("export".equals(totalNumFlag)) {
			cloumnsName = new String[]{"OBJECT_TYPE_NAME","POINT_NAME","INTERFACE_REMARK","ACCESS_SIGNS","POINT_TYPE",
					"RANGES_UPPER","RANGES_DOWN","IFIX_UNIT","CONTROL_OR","HISTORY_CURVE","ORACLE_SAVE","ALARM_OR","ALARM_VALUE",
					"ALARM_LIMITLL","ALARM_LIMITL","ALARM_LIMITL","ALARM_LIMITH","ALARM_LIMITHH","CREATE_DATE","REMARK","JRKZQ",
					"YCGLSB","POINT_CODE","DATA_TYPE","PLC_ADDRESS","POINT_DESC","POINT_REMARK","VALUE_RATIO",
					"MAILING_ADDRESS","MAILING_ADDRESSB","DATA_TYPE2","PLC_ADDRESS2","POINT_DESC2","POINT_REMARK2","VALUE_RATIO2",
					"MAILING_ADDRESS2","MAILING_ADDRESSB2"};
		}else{
			cloumnsName = new  String[]{"OBJECT_TYPE_NAME","OBJECT_CODE","POINT_NAME","INTERFACE_REMARK","ACCESS_SIGNS","POINT_TYPE",
					"RANGES_UPPER","RANGES_DOWN","IFIX_UNIT","CONTROL_OR","HISTORY_CURVE","ORACLE_SAVE","ALARM_OR","ALARM_VALUE",
					"ALARM_LIMITLL","ALARM_LIMITL","ALARM_LIMITH","ALARM_LIMITHH","CREATE_DATE","REMARK","TORTU_CODE","JRKZQ",
					"INSTRUMENT_CALLED","YCGLSB","POINT_CODE","DATA_TYPE","PLC_ADDRESS","POINT_DESC","POINT_REMARK","VALUE_RATIO",
					"MAILING_ADDRESS","MAILING_ADDRESSB","DATA_TYPE2","PLC_ADDRESS2","POINT_DESC2","POINT_REMARK2","VALUE_RATIO2",
					"MAILING_ADDRESS2","MAILING_ADDRESSB2","RLAST_OPER","RLAST_ODATE","SMALL_STATION_ID"} ;
		}
		
		String kk="OBJECT_TYPE_NAME";
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
		if(!"".equals(sort) && !"OBJECT_TYPE_NAME".equals(sort)){
			for (String cloumn : cloumnsName) {
				if(cloumn.equals(sort)){
					pjsql +=" order by " + cloumn + " " + order;
					break;
				}
			}
		}
		else {
			pjsql +=" order by OBJECT_TYPE_NAME asc";
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

	@Override
	public JSONObject searchCascadeInit() throws Exception {
		JSONObject  obj = null;
		
		String typeSql = "select a.object_code,a.object_type_name  from  PC_CD_OBJECT_TYPE_T a  ORDER BY NLSSORT(a.object_type_name,'NLS_SORT = SCHINESE_PINYIN_M')";
		String systemSql="select a.instru_clc,a.instru_cln from  PC_CD_INSTRU_CLASS_T a  ORDER BY NLSSORT(a.instru_cln,'NLS_SORT = SCHINESE_PINYIN_M')";
		String jqkzqSql="select  a.instru_clc,a.instru_cln from  PC_CD_INSTRU_CLASS_T a  where a.instru_1tc = 03  ORDER BY NLSSORT(a.instru_cln,'NLS_SORT = SCHINESE_PINYIN_M')";
		String controllerSql =" select  distinct(c.point_code) ,c.point_code from pc_cd_small_station_t c  ORDER BY NLSSORT(c.point_code,'NLS_SORT = SCHINESE_PINYIN_M')";
		//a.instru_nam
		String pointSql="select a.point_type,a.point_type from  PC_CD_POINT_TYPE_T a  ORDER BY NLSSORT(a.point_order,'NLS_SORT = SCHINESE_PINYIN_M')";
		String alarmSql ="select a.alarm_code,a.alarm_value from  PC_CD_ALARM_TYPE_T a  ORDER BY NLSSORT(a.alarm_code,'NLS_SORT = SCHINESE_PINYIN_M')";
		String basicSql ="select a.data_typec,a.data_typec  from  pc_cd_data_type_t  a   ORDER BY NLSSORT(a.data_typec,'NLS_SORT = SCHINESE_PINYIN_M')";
		
		List<Object[]> typeDataSet = commonDao.searchEverySql(typeSql);
		List<Object[]> sysDataSet = commonDao.searchEverySql(systemSql);
		List<Object[]> facDataSet = commonDao.searchEverySql(jqkzqSql);
		List<Object[]> conDataSet = commonDao.searchEverySql(controllerSql);
		List<Object[]> poiDataSet = commonDao.searchEverySql(pointSql);
		List<Object[]> alaDataSet = commonDao.searchEverySql(alarmSql);
		List<Object[]> basDataSet = commonDao.searchEverySql(basicSql);
		
		JSONArray  typeArr = null;
		JSONArray  sysArr = null;
		JSONArray  facArr = null;
		JSONArray  conArr = null;
		JSONArray  poiArr = null;
		JSONArray  alaArr = null;
		JSONArray  basArr = null;
		
		if(typeDataSet !=null && typeDataSet.size()>0){
			typeArr = new JSONArray();
			for (Object[] entry : typeDataSet) {
				obj = new JSONObject();
				obj.put("text", entry[1]);
				obj.put("id", entry[0]);
				typeArr.add(obj);
			}
		}
		if(sysDataSet !=null && sysDataSet.size()>0){
			sysArr = new JSONArray();
			for (Object[] entry : sysDataSet) {
					obj = new JSONObject();
					obj.put("text", entry[1]);
					obj.put("id", entry[0]);
					sysArr.add(obj);
			}
		}
		if(facDataSet !=null && facDataSet.size()>0){
			facArr = new JSONArray();
			for (Object[] entry : facDataSet) {
					obj = new JSONObject();
					obj.put("text", entry[1]);
					obj.put("id", entry[0]);
					facArr.add(obj);
			}
		}
		if(conDataSet !=null && conDataSet.size()>0){
			conArr = new JSONArray();
			for (Object[] entry : conDataSet) {
					obj = new JSONObject();
					obj.put("text", CommonsUtil.getClearNullData(entry[1]));
					obj.put("id", CommonsUtil.getClearNullData(entry[0]));
					conArr.add(obj);
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
		if(alaDataSet !=null && alaDataSet.size()>0){
			alaArr = new JSONArray();
			for (Object[] entry : alaDataSet) {
					obj = new JSONObject();
					obj.put("text", entry[1]);
					obj.put("id", entry[0]);
					alaArr.add(obj);
			}
		}
		if(basDataSet !=null && basDataSet.size()>0){
			basArr = new JSONArray();
			for (Object[] entry : basDataSet) {
					obj = new JSONObject();
					obj.put("text", entry[1]);
					obj.put("id", entry[0]);
					basArr.add(obj);
			}
		}
		obj = new JSONObject();
		obj.put("typeText", typeArr);
		obj.put("sysText", sysArr);
		obj.put("facText", facArr);
		obj.put("conText", conArr);
		obj.put("poiText", poiArr);
		obj.put("alaText", alaArr);
		obj.put("basText", basArr);
		return obj;
	}

	@Override
	public List<PcCdSmallStationT> searchCheckD(String sMALLSTATIONID,String POINTCODE ,String OBJECT_CODE) throws Exception {
		String  hql =" from  PcCdSmallStationT a  where 1=1 ";
		if(sMALLSTATIONID !=null  &&  !"".equals(sMALLSTATIONID)){
			hql +=" and a.smallStationId ='"+sMALLSTATIONID+"'";
		}
		if(POINTCODE !=null  &&  !"".equals(POINTCODE)){
			hql +=" and a.pointType = '"+POINTCODE+"'";
		}
		if(OBJECT_CODE !=null  &&  !"".equals(OBJECT_CODE)){
			hql +=" and a.objectCode = '"+OBJECT_CODE+"'";
		}
		List<PcCdSmallStationT> list = null;
		list = (List<PcCdSmallStationT>) commonDao.searchClassQuery(hql);
		return list;
	}

	@Override
	public boolean saveData(PcCdSmallStationT sm) throws Exception {
		List<PcCdSmallStationT> list = new ArrayList<PcCdSmallStationT>();
		list.add(sm);
		return commonDao.addOrUpdateDatas(list);
	}

	@Override
	public boolean deleteData(String sMALLSTATIONID) throws Exception {
		String sql =" delete from PC_CD_SMALL_STATION_t a  where a.small_station_id ='"+sMALLSTATIONID+"'";
		return commonDao.removeData(sql);
	}

	@Override
	public JSONArray searchOnChangeData(String qxlxId) throws Exception {
		String qsql ="select o.instru_namecid,o.instru_c3n from  PC_CD_INSTRU_NAMEC_T  o where o.instru_clc ='"+qxlxId+"' ORDER BY NLSSORT(o.instru_c3n,'NLS_SORT = SCHINESE_PINYIN_M')";
		JSONObject  obj = null;
		JSONArray   arr = null;
		List<Object[]> kzqxhList = commonDao.searchEverySql(qsql);
		if(kzqxhList !=null && kzqxhList.size()>0){
			arr = new JSONArray();
			for (Object[] entry : kzqxhList) {
				obj = new JSONObject();
				obj.put("text", entry[1]);
				obj.put("id", entry[0]);
				arr.add(obj);
			}
			return arr;
		}	
		arr = new JSONArray();
		obj =new JSONObject();
		obj.put("text","");
		obj.put("id", "");
		arr.add(obj);
		return arr;
	}

	@Override
	public JSONObject searchOnChangeData(String queID, String editId)throws Exception {
		String queYCSql ="";
		String queJRSql ="";
		
		String ediYCSql ="";
		String ediJRSql ="";
		List<Object[]>  queYCData = null;
		List<Object[]>  queJRData = null;
		List<Object[]>  ediYCData = null;
		List<Object[]>  ediJRData = null;
		if(queID !=null &&  !"".equals(queID)){
			queYCSql="SELECT  C.INSTRU_CLC,C.INSTRU_CLN  FROM  PC_CD_OBJECT_TYPE_T A"+  
						 " LEFT JOIN  pc_cd_instru_capply_t  B  ON A.OBJECT_CODE =  B.OBJECT_CODE"+
						 " LEFT JOIN  PC_CD_INSTRU_CLASS_T   C  ON  B.INSTRU_CLC = C.INSTRU_CLC"+
						 " where a.object_code='"+queID+"'";
			
			queJRSql="SELECT  C.INSTRU_CLC,C.INSTRU_CLN  FROM  PC_CD_OBJECT_TYPE_T A"+  
			 " LEFT JOIN  pc_cd_instru_capply_t  B  ON A.OBJECT_CODE =  B.OBJECT_CODE"+
			 " LEFT JOIN  PC_CD_INSTRU_CLASS_T   C  ON  B.INSTRU_CLC = C.INSTRU_CLC"+
			 " where a.object_code='"+queID+"'  AND INSTRU_1TC ='03'";
		}else{
			queYCSql="SELECT  C.INSTRU_CLC,C.INSTRU_CLN  FROM  PC_CD_OBJECT_TYPE_T A"+  
			 " LEFT JOIN  pc_cd_instru_capply_t  B  ON A.OBJECT_CODE =  B.OBJECT_CODE"+
			 " LEFT JOIN  PC_CD_INSTRU_CLASS_T   C  ON  B.INSTRU_CLC = C.INSTRU_CLC";
			

			queJRSql="SELECT  C.INSTRU_CLC,C.INSTRU_CLN  FROM  PC_CD_OBJECT_TYPE_T A"+  
			" LEFT JOIN  pc_cd_instru_capply_t  B  ON A.OBJECT_CODE =  B.OBJECT_CODE"+
			" LEFT JOIN  PC_CD_INSTRU_CLASS_T   C  ON  B.INSTRU_CLC = C.INSTRU_CLC"+
			" where INSTRU_1TC ='03'";
		}
		
		
//		if(editId !=null &&  !"".equals(editId)){
//			ediYCSql="SELECT  C.INSTRU_CLC,C.INSTRU_CLN  FROM  PC_CD_OBJECT_TYPE_T A"+  
//			 " LEFT JOIN  pc_cd_instru_capply_t  B  ON A.OBJECT_CODE =  B.OBJECT_CODE"+
//			 " LEFT JOIN  PC_CD_INSTRU_CLASS_T   C  ON  B.INSTRU_CLC = C.INSTRU_CLC"+
//			 " where a.object_code='"+editId+"'";
//
//			ediJRSql="SELECT  C.INSTRU_CLC,C.INSTRU_CLN  FROM  PC_CD_OBJECT_TYPE_T A"+  
//			" LEFT JOIN  pc_cd_instru_capply_t  B  ON A.OBJECT_CODE =  B.OBJECT_CODE"+
//			" LEFT JOIN  PC_CD_INSTRU_CLASS_T   C  ON  B.INSTRU_CLC = C.INSTRU_CLC"+
//			" where a.object_code='"+editId+"'  AND INSTRU_1TC ='03'";
//		}else{
//			ediYCSql="SELECT  C.INSTRU_CLC,C.INSTRU_CLN  FROM  PC_CD_OBJECT_TYPE_T A"+  
//			 " LEFT JOIN  pc_cd_instru_capply_t  B  ON A.OBJECT_CODE =  B.OBJECT_CODE"+
//			 " LEFT JOIN  PC_CD_INSTRU_CLASS_T   C  ON  B.INSTRU_CLC = C.INSTRU_CLC";
//
//			ediJRSql="SELECT  C.INSTRU_CLC,C.INSTRU_CLN  FROM  PC_CD_OBJECT_TYPE_T A"+  
//			" LEFT JOIN  pc_cd_instru_capply_t  B  ON A.OBJECT_CODE =  B.OBJECT_CODE"+
//			" LEFT JOIN  PC_CD_INSTRU_CLASS_T   C  ON  B.INSTRU_CLC = C.INSTRU_CLC"+
//			" where  INSTRU_1TC ='03'";
//		}
		
		  queYCData = commonDao.searchEverySql(queYCSql);
		  queJRData = commonDao.searchEverySql(queJRSql);
		//  ediYCData = commonDao.searchEverySql(ediYCSql);
		 // ediJRData = commonDao.searchEverySql(ediJRSql);
		  
		  JSONArray  queYCArr = null;
		  JSONArray  queJRArr = null;
		  JSONArray  ediYCArr = null;
		  JSONArray  ediJRArr = null;
		  JSONObject   obj = null;
		  if(queYCData !=null && queYCData.size()>0){
			  queYCArr = new JSONArray();
			  for (Object entry[] : queYCData) {
				  obj = new JSONObject();
				  obj.put("text", CommonsUtil.getClearNullData(entry[1]));
				  obj.put("id", CommonsUtil.getClearNullData(entry[0]));
				  queYCArr.add(obj);
			}
		  }
		  
		  if(queJRData !=null && queJRData.size()>0){
			  queJRArr = new JSONArray();
			  for (Object entry[] : queJRData) {
				  obj = new JSONObject();
				  obj.put("text", CommonsUtil.getClearNullData(entry[1]));
				  obj.put("id", CommonsUtil.getClearNullData(entry[0]));
				  queJRArr.add(obj);
			}
		  }
//		  if(ediYCData !=null && ediYCData.size()>0){
//			  ediYCArr = new JSONArray();
//			  for (Object entry[] : ediYCData) {
//				  obj = new JSONObject();
//				  obj.put("text", CommonsUtil.getClearNullData(entry[1]));
//				  obj.put("id", CommonsUtil.getClearNullData(entry[0]));
//				  ediYCArr.add(obj);
//			}
//		  }
//		  if(ediJRData !=null && ediJRData.size()>0){
//			  ediJRArr = new JSONArray();
//			  for (Object entry[] : ediJRData) {
//				  obj = new JSONObject();
//				  obj.put("text", CommonsUtil.getClearNullData(entry[1]));
//				  obj.put("id", CommonsUtil.getClearNullData(entry[0]));
//				  ediJRArr.add(obj);
//			}
//		  }
			obj = new JSONObject();
			obj.put("QUYCGLSBText", queYCArr);
			obj.put("QUJRKZQText", queJRArr);
			//obj.put("YCGLSBText", ediYCArr);
			//obj.put("JRKZQText", ediJRArr);
			return obj;
	}

	@Override
	public JSONObject searchOnChangeDataEdit(String editId) throws Exception {

		String queYCSql ="";
		String queJRSql ="";
		
		String ediYCSql ="";
		String ediJRSql ="";
		List<Object[]>  queYCData = null;
		List<Object[]>  queJRData = null;
		List<Object[]>  ediYCData = null;
		List<Object[]>  ediJRData = null;
//		if(queID !=null &&  !"".equals(queID)){
//			queYCSql="SELECT  C.INSTRU_CLC,C.INSTRU_CLN  FROM  PC_CD_OBJECT_TYPE_T A"+  
//						 " LEFT JOIN  pc_cd_instru_capply_t  B  ON A.OBJECT_CODE =  B.OBJECT_CODE"+
//						 " LEFT JOIN  PC_CD_INSTRU_CLASS_T   C  ON  B.INSTRU_CLC = C.INSTRU_CLC"+
//						 " where a.object_code='"+queID+"'";
//			
//			queJRSql="SELECT  C.INSTRU_CLC,C.INSTRU_CLN  FROM  PC_CD_OBJECT_TYPE_T A"+  
//			 " LEFT JOIN  pc_cd_instru_capply_t  B  ON A.OBJECT_CODE =  B.OBJECT_CODE"+
//			 " LEFT JOIN  PC_CD_INSTRU_CLASS_T   C  ON  B.INSTRU_CLC = C.INSTRU_CLC"+
//			 " where a.object_code='"+queID+"'  AND INSTRU_1TC ='03'";
//		}else{
//			queYCSql="SELECT  C.INSTRU_CLC,C.INSTRU_CLN  FROM  PC_CD_OBJECT_TYPE_T A"+  
//			 " LEFT JOIN  pc_cd_instru_capply_t  B  ON A.OBJECT_CODE =  B.OBJECT_CODE"+
//			 " LEFT JOIN  PC_CD_INSTRU_CLASS_T   C  ON  B.INSTRU_CLC = C.INSTRU_CLC";
//			
//
//			queJRSql="SELECT  C.INSTRU_CLC,C.INSTRU_CLN  FROM  PC_CD_OBJECT_TYPE_T A"+  
//			" LEFT JOIN  pc_cd_instru_capply_t  B  ON A.OBJECT_CODE =  B.OBJECT_CODE"+
//			" LEFT JOIN  PC_CD_INSTRU_CLASS_T   C  ON  B.INSTRU_CLC = C.INSTRU_CLC"+
//			" where INSTRU_1TC ='03'";
//		}
		
		
		if(editId !=null &&  !"".equals(editId)){
			ediYCSql="SELECT  C.INSTRU_CLC,C.INSTRU_CLN  FROM  PC_CD_OBJECT_TYPE_T A"+  
			 " LEFT JOIN  pc_cd_instru_capply_t  B  ON A.OBJECT_CODE =  B.OBJECT_CODE"+
			 " LEFT JOIN  PC_CD_INSTRU_CLASS_T   C  ON  B.INSTRU_CLC = C.INSTRU_CLC"+
			 " where a.object_code='"+editId+"'";

			ediJRSql="SELECT  C.INSTRU_CLC,C.INSTRU_CLN  FROM  PC_CD_OBJECT_TYPE_T A"+  
			" LEFT JOIN  pc_cd_instru_capply_t  B  ON A.OBJECT_CODE =  B.OBJECT_CODE"+
			" LEFT JOIN  PC_CD_INSTRU_CLASS_T   C  ON  B.INSTRU_CLC = C.INSTRU_CLC"+
			" where a.object_code='"+editId+"'  AND INSTRU_1TC ='03'";
		}else{
			ediYCSql="SELECT  C.INSTRU_CLC,C.INSTRU_CLN  FROM  PC_CD_OBJECT_TYPE_T A"+  
			 " LEFT JOIN  pc_cd_instru_capply_t  B  ON A.OBJECT_CODE =  B.OBJECT_CODE"+
			 " LEFT JOIN  PC_CD_INSTRU_CLASS_T   C  ON  B.INSTRU_CLC = C.INSTRU_CLC";

			ediJRSql="SELECT  C.INSTRU_CLC,C.INSTRU_CLN  FROM  PC_CD_OBJECT_TYPE_T A"+  
			" LEFT JOIN  pc_cd_instru_capply_t  B  ON A.OBJECT_CODE =  B.OBJECT_CODE"+
			" LEFT JOIN  PC_CD_INSTRU_CLASS_T   C  ON  B.INSTRU_CLC = C.INSTRU_CLC"+
			" where  INSTRU_1TC ='03'";
		}
		
		  //queYCData = commonDao.searchEverySql(queYCSql);
		  //queJRData = commonDao.searchEverySql(queJRSql);
		  ediYCData = commonDao.searchEverySql(ediYCSql);
		  ediJRData = commonDao.searchEverySql(ediJRSql);
		  
		  JSONArray  queYCArr = null;
		  JSONArray  queJRArr = null;
		  JSONArray  ediYCArr = null;
		  JSONArray  ediJRArr = null;
		  JSONObject   obj = null;
//		  if(queYCData !=null && queYCData.size()>0){
//			  queYCArr = new JSONArray();
//			  for (Object entry[] : queYCData) {
//				  obj = new JSONObject();
//				  obj.put("text", CommonsUtil.getClearNullData(entry[1]));
//				  obj.put("id", CommonsUtil.getClearNullData(entry[0]));
//				  queYCArr.add(obj);
//			}
//		  }
//		  
//		  if(queJRData !=null && queJRData.size()>0){
//			  queJRArr = new JSONArray();
//			  for (Object entry[] : queJRData) {
//				  obj = new JSONObject();
//				  obj.put("text", CommonsUtil.getClearNullData(entry[1]));
//				  obj.put("id", CommonsUtil.getClearNullData(entry[0]));
//				  queJRArr.add(obj);
//			}
//		  }
		  if(ediYCData !=null && ediYCData.size()>0){
			  ediYCArr = new JSONArray();
			  for (Object entry[] : ediYCData) {
				  obj = new JSONObject();
				  obj.put("text", CommonsUtil.getClearNullData(entry[1]));
				  obj.put("id", CommonsUtil.getClearNullData(entry[0]));
				  ediYCArr.add(obj);
			}
		  }
		  if(ediJRData !=null && ediJRData.size()>0){
			  ediJRArr = new JSONArray();
			  for (Object entry[] : ediJRData) {
				  obj = new JSONObject();
				  obj.put("text", CommonsUtil.getClearNullData(entry[1]));
				  obj.put("id", CommonsUtil.getClearNullData(entry[0]));
				  ediJRArr.add(obj);
			}
		  }
			obj = new JSONObject();
			//obj.put("QUYCGLSBText", queYCArr);
			//obj.put("QUJRKZQText", queJRArr);
			obj.put("YCGLSBText", ediYCArr);
			obj.put("JRKZQText", ediJRArr);
			return obj;
	
	}
}
