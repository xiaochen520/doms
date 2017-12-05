package com.echo.service.impl;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.beans.PageControl;
import com.echo.dao.ControllerDao;
import com.echo.dao.PageDao;
import com.echo.dao.InstruMentationDao;
import com.echo.dto.PcCdControllerT;
import com.echo.dto.PcCdInstruMentationT;
import com.echo.service.ControllerService;

public class ControllerServiceImpl implements ControllerService {
	private ControllerDao controllerDao;
	private InstruMentationDao instruMentationDao;
	private PageDao pageDao;

	public ControllerDao getControllerDao() {
		return controllerDao;
	}
	public void setControllerDao(ControllerDao controllerDao) {
		this.controllerDao = controllerDao;
	}
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
	
	public JSONObject searchControl(String oilstationname ,String blockstationname,String wellnum, String boiler,String instrutype,int pageNo,String sort,String order,int rowsPerpage) {
		JSONArray jsonArr = null;
		JSONObject tjo = null;
		String cloums = "select  ";
		String formTable = " from PC_CD_CONTROLLER_T_V  where 1=1 ";
		String totalNum = "select count(*) ";
		if(!"".equals(oilstationname)&& oilstationname !=null && !oilstationname.equals("undefined")&!oilstationname.equals("1")){
			formTable=formTable+" and oilstationname='"+oilstationname+"'";
		}
		if(!"".equals(blockstationname)&& blockstationname !=null && !blockstationname.equals("undefined")&!blockstationname.equals("1")){
			//if(!blockstationname.equals("") && blockstationname!=null){
				formTable = formTable+ " and oilstationname = '"+blockstationname+"'";
			}
//		if(!"".equals(maniname)&& maniname !=null && !maniname.equals("undefined")&!boiler.equals("1")){
//			//if(!boiler.equals("")&& boiler !=null){
//				formTable = formTable + "and OILSTATIONNAME = '"+maniname+"'";
//			}
		
		if(!"".equals(wellnum)&& wellnum !=null && !wellnum.equals("undefined")&!wellnum.equals("1")){
		//if(!wellnum.equals("")&& wellnum !=null){
			formTable = formTable+ " and oilstationname = '"+wellnum+"'";
		}
		if(!"".equals(boiler)&& boiler !=null && !boiler.equals("undefined")&!boiler.equals("1")){
			//if(!boiler.equals("")&& boiler !=null){
				formTable = formTable + "and oilstationname = '"+boiler+"'";
		}
		if(!"".equals(instrutype)&& instrutype !=null && !boiler.equals("undefined")&!boiler.equals("1")){
			//if(!boiler.equals("")&& boiler !=null){
				formTable = formTable + "and YBNAME = '"+instrutype+"'";
		}
		String[] cloumnsName = {"CONTROLLERID","OILSTATIONNAME","STRUCTURENAME","OBJECT_TYPE_NAME","INSTRUMENTATION_TYPE","YBNAME","FACILITY_NAME","COMMUNICATION_TYPE","IP_ADDRESS","STATION_NO","COMMUNICATION_PORT","EQUIPMENT_STATUS","STATUS","RLAST_OPER","RLAST_ODATE","REMARK","INSTRUMENTATIONID","STRUCTURETYPE","ORG_ID"};
		String kk = "CONTROLLERID"; 
		for(int m=1;m<cloumnsName.length;m++){
			if("RLAST_ODATE".equals(cloumnsName[m])){
				kk += ",to_char(RLAST_ODATE,'YYYY-MM-DD HH24:MI:SS') as RLAST_ODATE";
			}else{
				kk=kk+","+cloumnsName[m];
			}
		}
		String instruInfo = cloums + kk +formTable;
		//获取总条数
		int total = 0;
		if ("1".equals(totalNum)) {
			total = 1;
		} else {
			totalNum += formTable;
			total = pageDao.getCounts(totalNum);
		}
		
		//排序
		if(!"".equals(sort) && !"CONTROLLERID".equals(sort)){
			for (String cloumn : cloumnsName) {
				if(cloumn.equals(sort)){
					instruInfo +=" order  by nlssort(" + cloumn + ",'NLS_SORT=SCHINESE_STROKE_M')" + order;
					break;
				}
			}
		}
		else {
			instruInfo +=" order by nlssort(FACILITY_NAME,'NLS_SORT=SCHINESE_STROKE_M') ";
		}
		//计算分页
		PageControl control = new PageControl();
		//当前页
		control.setInt_num(rowsPerpage);
		control.init(pageNo, total);
		//开始条数
		int start = control.getStart()-1;
		
		List<Object[]> lo = controllerDao.searchControl(instruInfo,start,rowsPerpage,cloumnsName);
		if (lo != null && 0 < lo.size()) {
			// 生成树节点json文档
			jsonArr = new JSONArray();
			for (Object[] o : lo) {
				tjo = new JSONObject();
				for (int i = 0; i < o.length; i++) {
					if (o[i] == null) {
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
		return tjo;
	}
	public JSONObject cascadeInit() {
		String oilSql = "select t.ORG_ID,t.STRUCTURENAME from PC_CD_FACILITYTREE_V t where t.STRUCTURETYPE in (4,5) order by nlssort(t.STRUCTURENAME,'NLS_SORT=SCHINESE_STROKE_M')";
		String stationSql = "select t.ORG_ID,t.STRUCTURENAME from PC_CD_FACILITYTREE_V t where t.STRUCTURETYPE in (7,11,13,14) order by nlssort(t.STRUCTURENAME,'NLS_SORT=SCHINESE_STROKE_M')";
		String wellSql = "select t.ORG_ID,t.STRUCTURENAME from PC_CD_FACILITYTREE_V t where t.STRUCTURETYPE in (9) order by nlssort(t.STRUCTURENAME,'NLS_SORT=SCHINESE_STROKE_M')";
		String boiler = "select gl.org_id,gl.boiler_name from pc_cd_boiler_t gl  order by nlssort(gl.boiler_name,'NLS_SORT=SCHINESE_STROKE_M')";
		JSONArray oilJsonArr = null;
		JSONArray boilerArr = null;
		JSONArray stationArr = null;
		JSONArray wellArr = null;
		JSONObject jsobj = null;
		List<Object[]> oilSet = controllerDao.queryInfo(oilSql);
		List<Object[]> boilerSet = controllerDao.queryInfo(boiler);
		List<Object[]> stationSet = controllerDao.queryInfo(stationSql);
		List<Object[]> wellSet = controllerDao.queryInfo(wellSql);
		
		if(oilSet != null && oilSet.size()>0){
			oilJsonArr = new JSONArray();   
			for(Object[] entry : oilSet){
				jsobj = new JSONObject();
				jsobj.put("text", entry[1]);
				jsobj.put("id",entry[0]);
				oilJsonArr.add(jsobj);
			}
			jsobj = new JSONObject();
			jsobj.put("text", "全部");
			jsobj.put("id", 1);
			oilJsonArr.add(jsobj);
		}
		
		if(boilerSet != null && boilerSet.size()>0){
			boilerArr = new JSONArray();   
			for(Object[] entry : boilerSet){
				jsobj = new JSONObject();
				jsobj.put("text", entry[1]);
				jsobj.put("id",entry[0]);
				boilerArr.add(jsobj);
			}
		}
		
		if(stationSet != null && stationSet.size()>0){
			stationArr = new JSONArray();   
			for(Object[] entry : stationSet){
				jsobj = new JSONObject();
				jsobj.put("text", entry[1]);
				jsobj.put("id",entry[0]);
				stationArr.add(jsobj);
			}
		}
		
		if(wellSet != null && wellSet.size()>0){
			wellArr = new JSONArray();   
			for(Object[] entry : wellSet){
				jsobj = new JSONObject();
				jsobj.put("text", entry[1]);
				jsobj.put("id",entry[0]);
				wellArr.add(jsobj);
			}
		}
		
		jsobj = new JSONObject();
		jsobj.put("oiltext",oilJsonArr);
		jsobj.put("boilertext",boilerArr);
		jsobj.put("stationtext",stationArr);
		jsobj.put("welltext",wellArr);
		return jsobj;
	}
	
	public boolean removeController(String arg){
		//String[] sqls = new String[2];
		String sqls = " DELETE from pc_cd_controller_t d where d.controllerid = '"+arg+"'";
		//sqls[1] = " DELETE from pc_cd_org_t t where t.org_id = '"+orgid+"'";
		
		return controllerDao.removeController(sqls);
	}
	public List<PcCdControllerT> searchControllerId(String ConId,String insId,String name)throws Exception {
		String hql = " FROM  PcCdControllerT a where 1=1";
		if(ConId !=null && !"".equals(ConId)){
			hql +=" and a.controllerid ='"+ConId+"'";
		}
		if(insId !=null && !"".equals(insId)){
			hql +=" and a.instrumentationid = '"+insId+"' ";
		}
		if(name !=null && !"".equals(name)){
			hql +=" and a.facilityName = '"+name+"' ";
		}
		return controllerDao.searchControllerId(hql);
	}
	
	public boolean SaveOrUpdateControl(PcCdControllerT im) throws Exception {
		return controllerDao.SaveOrUpdateControl(im);
	}
	
	public JSONArray searchCommtype(String commtype) throws Exception {
		JSONArray jsonArr = null;
		JSONObject jsobj = null;
		String sql ="select  b.instru_stva ,b.instru_stns  from  pc_cd_instru_status_t    b   order by nlssort(b.instru_stva, 'NLS_SORT=SCHINESE_STROKE_M')";
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
	public JSONArray searchIpadd(String ipadd) throws Exception {
		JSONArray jsonArr = null;
		JSONObject jsobj = null;
		String sql ="select  b.instru_stva ,b.instru_stns  from  pc_cd_instru_status_t    b   order by nlssort(b.instru_stva, 'NLS_SORT=SCHINESE_STROKE_M')";
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
	public JSONArray searchStationno(String stationno) throws Exception {
		JSONArray jsonArr = null;
		JSONObject jsobj = null;
		String sql ="select  b.instru_stva ,b.instru_stns  from  pc_cd_instru_status_t    b   order by nlssort(b.instru_stva, 'NLS_SORT=SCHINESE_STROKE_M')";
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
	public JSONArray searchComport(String comport) throws Exception {
		JSONArray jsonArr = null;
		JSONObject jsobj = null;
		String sql ="select  b.instru_stva ,b.instru_stns  from  pc_cd_instru_status_t    b   order by nlssort(b.instru_stva, 'NLS_SORT=SCHINESE_STROKE_M')";
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
	public JSONArray searchEquipstatus(String equipstatus) throws Exception {
		JSONArray jsonArr = null;
		JSONObject jsobj = null;
		String sql ="select  b.instru_stva ,b.instru_stns  from  pc_cd_instru_status_t    b   order by nlssort(b.instru_stva, 'NLS_SORT=SCHINESE_STROKE_M')";
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
	@Override
	public JSONArray searchYBNameQuery(String instrutype) throws Exception {
		JSONArray jsonArr = null;
		JSONObject jsobj = null;
		String sql =" select distinct b.instrumentation_name  ,b.instrumentation_name from  pc_cd_instru_mentation_t b   where b.instrumentation_type='03'    order by nlssort(b.instrumentation_name,'NLS_SORT=SCHINESE_STROKE_M')"; 
		List<Object[]> dataSet = controllerDao.searchYBNameQuery(sql);
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
	@Override
	public JSONArray searchYBNameQueryJL(String orgid ,String data1) throws Exception {
			JSONArray jsonArr = null;
			JSONObject jsobj = null;
			 String  sql =" select v.instrumentationid,v.instrumentation_name　from  pc_cd_instru_mentation_v  v  where  v.org_id='"+orgid+"'  order by nlssort(v.instrumentation_name,'NLS_SORT=SCHINESE_STROKE_M')"; 
//			if(data1 !=null && !"".equals(data1)){
//				 sql =" select v.org_id,v.instrumentation_name　from  pc_cd_instru_mentation_v  v  where  v.org_id='"+data1+"'  order by nlssort(v.instrumentation_name,'NLS_SORT=SCHINESE_STROKE_M')"; 
//			}
			List<Object[]> dataSet = controllerDao.searchYBNameQuery(sql);
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
	
	@Override
	public JSONArray queryConOilSationInfo(String arg) throws Exception {
		JSONArray jsonArr =null;
		JSONObject jsobj =null;
		String sql ="select distinct(r.ORG_ID),r.oilstationname,r.structuretype from PC_CD_CONTROLLER_T_V r where r.STRUCTURETYPE='7'"+
					"order by nlssort(r.oilstationname,'NLS_SORT=SCHINESE_STROKE_M')";
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
	public JSONArray queryConInjectionTopryInfoAdd(String arg) throws Exception {
		JSONArray jsonArr =null;
		JSONObject jsobj =null;
		String sql ="select r.ORG_ID,r.oilstationname,r.structuretype from PC_CD_CONTROLLER_T_V r where r.STRUCTURETYPE='8'"+
					"order by nlssort(r.oilstationname,'NLS_SORT=SCHINESE_STROKE_M')";
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
	public JSONArray queryConWellInfo(String arg) throws Exception {
		JSONArray jsonArr =null;
		JSONObject jsobj =null;
		String sql ="select r.ORG_ID,r.oilstationname,r.structuretype from PC_CD_CONTROLLER_T_V r where r.STRUCTURETYPE='9'"+
					"order by nlssort(r.oilstationname,'NLS_SORT=SCHINESE_STROKE_M')";
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
	public JSONArray queryConBoilersNameInfo(String arg) throws Exception {
		JSONArray jsonArr =null;
		JSONObject jsobj =null;
		String sql ="select r.ORG_ID,r.oilstationname,r.structuretype from PC_CD_CONTROLLER_T_V r where r.STRUCTURETYPE='10'"+
					"order by nlssort(r.oilstationname,'NLS_SORT=SCHINESE_STROKE_M')";
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
	
}
