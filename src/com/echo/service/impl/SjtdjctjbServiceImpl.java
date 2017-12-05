package com.echo.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.beans.PageControl;
import com.echo.dao.CommonDao;
import com.echo.service.SjtdjctjbService;
import com.echo.util.CommonsUtil;

public class SjtdjctjbServiceImpl  implements SjtdjctjbService {  
		private CommonDao commonDao;
		public void setCommonDao(CommonDao commonDao) {
			this.commonDao = commonDao;
		}
		@Override
		public JSONArray searchObjectName() throws Exception {
			String sql = "select  a.object_code,a.object_type_name  from  pc_cd_object_type_t a  ORDER BY NLSSORT(a.object_code,'NLS_SORT = SCHINESE_PINYIN_M')";
			List<Object[]> dataList = commonDao.searchEverySql(sql);
			JSONObject obj = null;
			JSONArray  arr = null;
			if(dataList !=null && dataList.size()>0){
				arr = new JSONArray();
				for (Object[] entry : dataList) {
					obj= new JSONObject();
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
		public JSONArray searchOnChangeName(String typeName) throws Exception {
			String sql = "select a.ORG_ID, a.structurename  from pc_rd_data_check_comm_v  a  where a.OBJECT_CODE='"+typeName+"'  ORDER BY NLSSORT(a.structurename ,'NLS_SORT = SCHINESE_PINYIN_M')";
			List<Object[]> dataList = commonDao.searchEverySql(sql);
			JSONObject obj = null;
			JSONArray  arr = null;
			if(dataList !=null && dataList.size()>0){
				arr = new JSONArray();
				for (Object[] entry : dataList) {
					obj= new JSONObject();
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
		public HashMap<String, Object> searchData(String oBJECTTYPENAME,
				String cHECKDATE, int pageNo, String sort, String order,
				int rowsPerpage, String totalNumFlag) throws Exception {

			JSONArray jsonArr = null;
			HashMap<String,Object> map = new HashMap<String, Object>();
			JSONObject tjo = null;
			String cloums = "select   ";
			String formTable = "  from PC_RD_DATA_CHECK_OSTATIS_T a  where 1=1 ";
			String totalNum = "select count(*) ";
			if(!oBJECTTYPENAME.equals("")&&oBJECTTYPENAME!=null&&!oBJECTTYPENAME.equals("undefined")){
				formTable=formTable+" and OBJECT_TYPE_NAME ='"+oBJECTTYPENAME+"'";
			}
			if(!cHECKDATE.equals("")&&cHECKDATE!=null&&!cHECKDATE.equals("undefined") ){
				formTable=formTable+" and CHECK_DATE  =to_date('"+cHECKDATE+"','YYYY-MM-DD')"; 
			}
	
			String[] cloumnsName = null;
			if ("export".equals(totalNumFlag)) {
				cloumnsName = new String[]{"OBJECT_TYPE_NAME","CHECK_DATE","OBJECT_CODE","OTOTAL_NUM","NOACCESS_NUM","NONETO_NUM","HAACCESS_NUM",
										  "HAACCESS_RATIO","DATAAOBJ_NUM","DANONEOBJ_NUM","DABNOOBJ_NUM","DABNOOBJ_RATIO","DATAAOBJ_DESC",
										  "DANONEOBJ_DESC","NONETO_DESC","SERVER_DES"};
			}else{
				cloumnsName = new  String[]{"OBJECT_TYPE_NAME","CHECK_DATE","OBJECT_CODE","OTOTAL_NUM","NOACCESS_NUM","NONETO_NUM","HAACCESS_NUM",
						  "HAACCESS_RATIO","DATAAOBJ_NUM","DANONEOBJ_NUM","DABNOOBJ_NUM","DABNOOBJ_RATIO","DATAAOBJ_DESC",
						  "DANONEOBJ_DESC","NONETO_DESC","SERVER_DES"} ;
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
		public HashMap<String, Object> searchDTData(String oBJECTTYPENAME,
				String OBJECT_NAME, String cHECKDATE, String dATAINTERNUM1, String dATAINTERACT1, String dATAINTERNUM2, String dATAINTERACT2, String dATAINTERNUM3, String dATAINTERACT3, int pageNo, String sort, String order,
				int rowsPerpage,  String totalNumFlag) throws Exception {


			JSONArray jsonArr = null;
			HashMap<String,Object> map = new HashMap<String, Object>();
			JSONObject tjo = null;
			String cloums = "select   ";
			String formTable = "  from pc_rd_data_check_comm_v a  where 1=1 ";
			String totalNum = "select count(*) ";
			if(!oBJECTTYPENAME.equals("")&&oBJECTTYPENAME!=null&&!oBJECTTYPENAME.equals("undefined")){
				formTable=formTable+" and OBJECT_CODE ='"+oBJECTTYPENAME+"'";
			}
			if(!OBJECT_NAME.equals("")&&OBJECT_NAME!=null&&!OBJECT_NAME.equals("undefined")){
				formTable=formTable+" and structurename ='"+OBJECT_NAME+"'";
			}
			if(!cHECKDATE.equals("")&&cHECKDATE!=null&&!cHECKDATE.equals("undefined") ){
				formTable=formTable+" and CHECK_DATE  =to_date('"+cHECKDATE+"','YYYY-MM-DD')"; 
			}
	
			if(!dATAINTERNUM1.equals("")&&dATAINTERNUM1!=null&&!dATAINTERNUM1.equals("undefined")){
				formTable=formTable+" and DATA_INTERNUM1 >'"+dATAINTERNUM1+"'";
			}
			if(!dATAINTERACT1.equals("")&&dATAINTERACT1!=null&&!dATAINTERACT1.equals("undefined")){
				formTable=formTable+" and DATA_INTERACT1 >'"+dATAINTERACT1+"'";
			}
			
			if(!dATAINTERNUM2.equals("")&&dATAINTERNUM2!=null&&!dATAINTERNUM2.equals("undefined")){
				formTable=formTable+" and DATA_INTERNUM2 >'"+dATAINTERNUM2+"'";
			}
			if(!dATAINTERACT2.equals("")&&dATAINTERACT2!=null&&!dATAINTERACT2.equals("undefined")){
				formTable=formTable+" and DATA_INTERACT2 >'"+dATAINTERACT2+"'";
			}
			if(!dATAINTERNUM3.equals("")&&dATAINTERNUM3!=null&&!dATAINTERNUM3.equals("undefined")){
				formTable=formTable+" and DATA_INTERNUM3 >'"+dATAINTERNUM3+"'";
			}
			if(!dATAINTERACT3.equals("")&&dATAINTERACT3!=null&&!dATAINTERACT3.equals("undefined")){
				formTable=formTable+" and DATA_INTERACT2 >'"+dATAINTERACT3+"'";
			}
			String[] cloumnsName = null;
			if ("export".equals(totalNumFlag)) {
				cloumnsName = new String[]{"CHECK_DATE","OBJECT_CODE","STRUCTURENAME","DWMC","QKMC","BLOCKSTATION_NAME","PROD_SNS",
						"DESCRIPTION","CHECK_UNITNUM","CHECK_UNITDS1","CHECK_SFIELDNA1","CHECK_SFIELDDS1","CHECK_UNITDS2","CHECK_SFIELDNA2",
						"CHECK_SFIELDDS2","CHECK_UNITDS3","DATA_INTERNUM3","DATA_INTERACT3","RECODE_VDNUM","ATIME_INTERVAL","CHECK_SFIELDNA1",
						"CHECK_INTERCON1","CHECK_SFIELDNA2","CHECK_INTERCON2","CHECK_SFIELDNA2","CHECK_INTERCON2",
						"CHECK_SFIELDNA3","CHECK_INTERCON3","ORG_ID","RTABLE_NAME","OBJECT_CODE"
				};
			}else{
				cloumnsName = new  String[]{"CHECK_DATE","RTABLE_NAME","OBJECT_CODE","STRUCTURENAME","DWMC","QKMC","BLOCKSTATION_NAME",
						  "PROD_SNS","DESCRIPTION","CHECK_UNITNUM","CHECK_UNITDS1","CHECK_SFIELDNA1","CHECK_SFIELDDS1",
						  "CHECK_INTERCON1","DATA_INTERNUM1","DATA_INTERACT1","CHECK_UNITDS2","CHECK_SFIELDNA2","CHECK_SFIELDDS2",
						  "CHECK_INTERCON2","DATA_INTERNUM2","DATA_INTERACT2","CHECK_UNITDS3","CHECK_SFIELDNA3",
						  "CHECK_SFIELDDS3","CHECK_INTERCON3","DATA_INTERNUM3","DATA_INTERACT3","ATIME_INTERVAL","RECODE_VDNUM",
						  "ORG_ID"} ;
			}
			
			String kk="CHECK_DATE";
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
			if(!"".equals(sort) && !"CHECK_DATE".equals(sort)){
				for (String cloumn : cloumnsName) {
					if(cloumn.equals(sort)){
						pjsql +=" order by " + cloumn + " " + order;
						break;
					}
				}
			}
			else {
				pjsql +=" order by CHECK_DATE asc";
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
		
}
