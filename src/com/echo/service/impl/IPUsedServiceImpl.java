package com.echo.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.beans.PageControl;
import com.echo.dao.IPUsedDao;
import com.echo.dto.PcCdIpUsedT;
import com.echo.service.IPUsedService;
import com.echo.util.DateBean;

public class IPUsedServiceImpl implements IPUsedService {
   private IPUsedDao ipUsedDao;

public void setIpUsedDao(IPUsedDao ipUsedDao) {
	this.ipUsedDao = ipUsedDao;
}

@Override
public HashMap<String, Object> searchDatas(String category, String area,String unit,String INSTRU_1TN, String shebeiName, String ip, String code2, String isUsed,
		String rlastOdate ,String txtDate,String txtDate1,int pageNo, String sort, String order, int rowsPerpage, String totalNumFlag)throws Exception {

	
	JSONArray jsonArr = null;
	HashMap<String,Object> map = new HashMap<String, Object>();
	JSONObject tjo = null;
	String cloums = "select   ";
	String formTable = "  from pc_cd_ip_used_v a  where 1=1 ";
	String totalNum = "select count(*) ";
	if(!category.equals("")&&category!=null&&!category.equals("undefined") ){
		formTable=formTable+" and category ='"+category+"'";
	}
	if(!area.equals("")&&area!=null&&!area.equals("undefined")){
		formTable=formTable+" and area='"+area+"'";
	}
	if(!unit.equals("")&&unit!=null&&!unit.equals("undefined")){
		formTable=formTable+" and unit  like'%"+unit+"%'";
		//formTable=formTable+" and unit ='"+unit+"'";
	}
	if(!INSTRU_1TN.equals("")&&INSTRU_1TN!=null&&!INSTRU_1TN.equals("undefined")){
		formTable=formTable+" and INSTRU_1TN='"+INSTRU_1TN+"'";
	}
	if(!shebeiName.equals("")&&shebeiName!=null&&!shebeiName.equals("undefined")){
		formTable=formTable+" and instrumentation_name  like '%"+shebeiName+"%'";
	}
	if(!ip.equals("")&&ip!=null&&!ip.equals("undefined")){
		formTable=formTable+" and IP like '"+ip+"%'";
		//formTable=formTable+" and IP = '"+ip+"'";
	}
	if(!code2.equals("")&&code2!=null&&!code2.equals("undefined")){
		formTable=formTable+" and segment_code2='"+code2+"'";
	}
	if(!isUsed.equals("")&&isUsed!=null&&!isUsed.equals("undefined")){
		formTable=formTable+" and is_used ='"+isUsed+"'";
	}
	if(!rlastOdate.equals("")&&rlastOdate!=null&&!rlastOdate.equals("undefined")){
		formTable=formTable+" and DESIGN_DATE = ='"+rlastOdate+"'";
	}
	if(!txtDate.equals("")&&txtDate!=null&&!txtDate.equals("undefined") && !txtDate1.equals("")&&txtDate1!=null&&!txtDate1.equals("undefined")){
		formTable=formTable+" and rlast_odate  between TO_DATE('"+txtDate+"','YYYY-MM-DD hh24:mi:ss') and TO_DATE('"+txtDate1+"','YYYY-MM-DD  hh24:mi:ss')  ";
	}
	
//	Date today = new Date();
//	String nowdate = DateBean.getTimeStr(today);
	
//	if("".equals(startDate)){
//		startDate = nowdate.substring(0, 10) + " 00:00:00";
//	}else{
//		startDate = startDate + ":00";
//	}
//	if("".equals(endDate)){
//		endDate = nowdate.substring(0, 10) + " 23:59:59";
//	}else{
//		endDate = endDate + ":00";
//	}
	//formTable=formTable+" and CJSJ between TO_DATE('"+startDate+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+endDate+"','YYYY-MM-DD HH24:MI:SS')";
	
//	String[] cloumnsName = {"JLZH","CJSJ","DWMC","QK","GHDH","BRANCHING_NAME","JLCZSJ","GHH","JH","CL","CLSJ","SDSJ","DTFH","WELL_NAME","PLAN_METERING_TIMES",
//			"RUN_METERING_TIMES","OIL_ORGID","BRANCHINGID","QKID","ORG_ID","STATION_ORGID","LIQUID"};
	String[] cloumnsName ={"ip","category","area","unit","design_date","instru_1tn","instrumentation_name","ip_no","device","interface2th","segment_code2","is_used","start_date","stop_date","remark","vpn_ip","rlast_oper","rlast_odate","org_id","object_code","instru_clc","qkid","instru_1tc"};
	String kk="IP";
	for(int m=1;m<cloumnsName.length;m++){
		if("rlast_odate".equals(cloumnsName[m])){
			kk=kk+","+"to_char(rlast_odate,'YYYY-MM-DD hh24:mi:ss') as rlast_odate";
		}else{
			kk=kk+","+cloumnsName[m];
		}
	}
	String product = cloums + kk+formTable;
	if ("export".equals(totalNumFlag)) {
		String[] cloumnsNames2 = {"ip","category","area","unit","design_date","instru_1tn","instrumentation_name","ip_no","device","interface2th","segment_code2","is_used","start_date","stop_date","remark","vpn_ip","org_id","object_code","instru_clc"} ;
		kk="IP";
		for(int m=1;m<cloumnsNames2.length;m++){
//			if("JLCZSJ".equals(cloumnsNames2[m])){
//				kk=kk+","+"to_char(JLCZSJ,'YYYY-MM-DD hh24:mi:ss') as JLCZSJ";
//			}else{
				kk=kk+","+cloumnsNames2[m];
//			}
//		}
				
	}
		 product = cloums + kk+formTable;
	}
	//获取总条数
	int total = 0;
	if ("1".equals(totalNum)) {
		total = 1;
	} else {
		totalNum += formTable;
		total = ipUsedDao.getCounts(totalNum);
	}
	if ("totalNum".equals(totalNumFlag)) {
		map.put("totalNum", total + "");
		return map;
	}
	//排序
	if(!"".equals(sort) && !"IP".equals(sort)){
		for (String cloumn : cloumnsName) {
			if(cloumn.equals(sort)){
				product +=" order by " + cloumn + " " + order;
				break;
			}
		}
	}
	else {
		product +=" order by IP asc";
	}
	//计算分页
	PageControl control = new PageControl();
	//当前页
	List<Object[]> lo = new ArrayList<Object[]>();
	control.setInt_num(rowsPerpage);
	if ("export".equals(totalNumFlag)) {
		lo = ipUsedDao.searchDatas(product);
	}
	else {
		control.init(pageNo, total);
		//开始条数
		int start = control.getStart()-1;
		lo = ipUsedDao.searchDatas(product,start,rowsPerpage,cloumnsName);
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
public JSONArray searchCategory(String arg) throws Exception {
	//String sql = "select distinct(a.category) ,a.category from  pc_cd_ip_used_t a order by nlssort( a.category,'NLS_SORT=SCHINESE_STROKE_M')";
	String sql = "select  z.object_code,z.object_type_name  from pc_cd_object_type_t  z order by nlssort(z.object_type_name ,'nls_sort = schinese_pinyin_m')";
	JSONObject obj = null;
	JSONArray arr= null;
	List<Object[]> dataSet = ipUsedDao.searchSql(sql);
	
	if(dataSet !=null && dataSet.size()>0){
		arr = new JSONArray();
		for (Object[] entry : dataSet) {
			obj = new JSONObject();
			obj.put("text", entry[1]);
			obj.put("id", entry[0]);
			arr.add(obj);
		}
		return arr;
	}
	obj = new JSONObject();
	arr = new JSONArray();
	obj.put("text", "");
	obj.put("id", "");
	arr.add(obj);
	return arr;
}

@Override
public JSONArray searchArea(String arg) throws Exception {
	JSONObject obj = null;
	JSONArray arr= null;
	String sql = "select  b.qkid,b.qkmc  from  PC_CD_AREA_LOGIC_T  b order by nlssort(b.qkmc , 'nls_sort = schinese_pinyin_m')";
	List<Object[]> dataSet = ipUsedDao.searchSql(sql);
	if(dataSet !=null && dataSet.size()>0){
		arr = new JSONArray();
		for (Object[] entry : dataSet) {
			obj = new JSONObject();
			obj.put("text", entry[1]);
			obj.put("id", entry[0]);
			arr.add(obj);
		}
		return arr;
	}
	obj = new JSONObject();
	arr = new JSONArray();
	obj.put("text", "");
	obj.put("id", "");
	arr.add(obj);
	return arr;
}

@Override
public JSONArray searchShebei(String arg) throws Exception {
	JSONObject obj = null;
	JSONArray arr= null;
	//String sql = "select  a.ip,a.instrumentation_name from  pc_cd_ip_used_t a order by nlssort(instrumentation_name,'NLS_SORT=SCHINESE_STROKE_M')";
	String sql = "select  a.INSTRU_CLC ,a.INSTRU_CLN  from  pc_cd_instru_capply_v a order by nlssort(a.INSTRU_CLN ,'nls_sort=schinese_pinyin_m')";

	List<Object[]> dataSet = ipUsedDao.searchSql(sql);
	if(dataSet !=null && dataSet.size()>0){
		arr = new JSONArray();
		for (Object[] entry : dataSet) {
			obj = new JSONObject();
			obj.put("text", entry[1]);
			obj.put("id", entry[0]);
			arr.add(obj);
		}
		return arr;
	}
	obj = new JSONObject();
	arr = new JSONArray();
	obj.put("text", "");
	obj.put("id", "");
	arr.add(obj);
	return arr;
}

@Override
public JSONArray searchDevice(String arg) throws Exception {
	JSONObject obj = null;
	JSONArray arr= null;
	//String sql = "select  a.ip,a.device from  pc_cd_ip_used_t a order by nlssort(instrumentation_name,'NLS_SORT=SCHINESE_STROKE_M')";
	String sql = "select distinct a.device,a.device from pc_cd_ip_used_t a where a.device in (select   a.device from   pc_cd_ip_used_t group by   a.device having count(a.device) > 0)";

	List<Object[]> dataSet = ipUsedDao.searchSql(sql);
	if(dataSet !=null && dataSet.size()>0){
		arr = new JSONArray();
		for (Object[] entry : dataSet) {
			obj = new JSONObject();
			obj.put("text", entry[1]);
			obj.put("id", entry[0]);
			arr.add(obj);
		}
		return arr;
	}
	obj = new JSONObject();
	arr = new JSONArray();
	obj.put("text", "");
	obj.put("id", "");
	arr.add(obj);
	return arr;
}

@Override
public List<PcCdIpUsedT> searchIpId(String id) throws Exception {
	List<PcCdIpUsedT>  list = null;
	String hql = "from PcCdIpUsedT  a where 1=1 ";
	if(id !=null  && !"".equals(id)){
		hql +=" and a.ip = '"+id+"'";
	}
	list = ipUsedDao.searchIpId(hql);
	return  list;
}

@Override
public boolean updateIpUsed(PcCdIpUsedT ip) throws Exception {
	return ipUsedDao.saveIpUsed(ip);
}

@Override
public String searchSegmentId(String segmentId) throws Exception {
	String sql = "select  a.segment_id  from pc_cd_ip_segment_t a where a.segment_code2='"+segmentId+"'";
	List<Object[]> lo = new ArrayList<Object[]>();
	lo = ipUsedDao.searchSegmentId(sql);
	Object blockName = lo.get(0);
	return (String) blockName;
}

@Override
public JSONArray searchUnit() throws Exception {
	String sql="select  b.org_id,b.structurename from  PC_CD_ORG_TABLE_VX  b order by nlssort(b.structurename , 'nls_sort = schinese_pinyin_m')";
	JSONArray  arr = null;
	JSONObject obj = null;
	List<Object[]> unitData = ipUsedDao.searchSql(sql);
	if(unitData !=null && unitData.size()>0){
		arr = new JSONArray();
		for (Object[] entry : unitData) {
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
public JSONArray searchOnChangeUnit(String catid, String arid) throws Exception {
		String sql="select  c.org_id,c.structurename  from  PC_CD_ORG_TABLE_VX c "+
					" where c.object_code='"+catid+"'  and c.QKID='"+arid+"' order by nlssort(c.structurename , 'nls_sort = schinese_pinyin_m')";
		JSONArray  arr = null;
		JSONObject obj = null;
		List<Object[]> unitData = ipUsedDao.searchSql(sql);
		if(unitData !=null && unitData.size()>0){
			arr = new JSONArray();
			for (Object[] entry : unitData) {
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
public JSONArray searchInstruData() throws Exception {
	String sql ="select  a.instru_1tc,a.instru_1tn  from pc_cd_instru_type_t a order by nlssort(a.instru_1tn ,'nls_sort=schinese_pinyin_m')";
	JSONArray  arr= null;
	JSONObject obj = null;
	List<Object[]>  InstruData = ipUsedDao.searchSql(sql);
	if( InstruData !=null && InstruData.size()>0){
		arr = new JSONArray();
		for (Object[] entry : InstruData) {
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
public JSONArray searchOnChangeSB(String catid,String shid) throws Exception {
	String  sql ="select t.INSTRU_CLC,t.INSTRU_CLN from  pc_cd_instru_capply_v t where  "+ 
				"t.object_code='"+catid+"' and substr(t.INSTRU_CLC,1,2) ='"+shid+"' "+
				"order by nlssort(t.INSTRU_CLN , 'nls_sort=schinese_pinyin_m')";
	JSONArray  arr = null;
	JSONObject obj =null;
	List<Object[]> SBNameData =ipUsedDao.searchSql(sql);
	if(SBNameData !=null && SBNameData.size()>0){
		arr = new JSONArray();
		for (Object[] entry : SBNameData) {
			obj = new  JSONObject();
			obj.put("text", entry[1]);
			obj.put("id", entry[0]);
			arr.add(obj);
		}
		return arr;
	}
	arr = new JSONArray();
	obj =new JSONObject();
	obj.put("text", "");
	obj.put("id", "");
	arr.add(obj);
	return arr;
}

@Override
public List<Object[]> searchOrgID(String unit) throws Exception {
	String sql ="select  *  from  pc_cd_org_t a where a.structurename='"+unit+"'";
	List<Object[]> OrgIdData =ipUsedDao.searchSql(sql);
	return OrgIdData;
}
   
}
