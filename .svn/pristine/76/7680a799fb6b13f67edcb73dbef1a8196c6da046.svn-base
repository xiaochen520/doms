package com.echo.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.beans.PageControl;
import com.echo.dao.CommonDao;
import com.echo.dao.GwghjcxxDao;
import com.echo.dto.PcCdNetworkMdT;
import com.echo.service.GwghjcxxService;

public class GwghjcxxServiceImpl implements GwghjcxxService {
	private GwghjcxxDao gwghjcxxDao;
    private  CommonDao commonDao;
	public void setGwghjcxxDao(GwghjcxxDao gwghjcxxDao) {
		this.gwghjcxxDao = gwghjcxxDao;
	}
	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}
	@Override
	public JSONObject searchCascadeInit() throws Exception {
		JSONObject obj = null;
		JSONArray arr = null;
		//采油 注转 管网管汇
		//String OilSql = "SELECT a.org_id,a.structurename  FROM  PC_CD_ORG_T  a  where  a.structuretype='4' ORDER BY a.structurename";
		//String StationSql = "SELECT a.org_id,a.structurename  FROM  PC_CD_ORG_T  a  left join  pc_cd_station_t b on  a.org_id = b.org_id  where  a.structuretype='7' ORDER BY a.structurename asc";
		String OilSql = "select oilid,b.structurename from   (select distinct(oilid) from pc_cd_network_md_v) a left join pc_cd_org_t b on a.oilid = b.org_id order by nlssort(b.STRUCTURENAME,'NLS_SORT=SCHINESE_STROKE_M') ";
		String StationSql = "select stationid,b.structurename  from   (select distinct(stationid) from pc_cd_network_md_v  ) a left join pc_cd_org_t b on a.stationid = b.org_id order by nlssort(b.STRUCTURENAME,'NLS_SORT=SCHINESE_STROKE_M')";
		String PIPESql ="select b.org_id,b.structurename from  (select  distinct(org_id) from pc_cd_network_md_v) a  left join  pc_cd_org_t b  on  a.org_id = b.org_id ORDER BY b.structurename  asc";
		List<Object[]> OilDataSet = null;
		List<Object[]> StationDataSet = null;
		List<Object[]> PIPEDataSet = null;
		
		JSONArray oilArr = null;
		JSONArray statArr = null;
		JSONArray pipeArr = null;
		OilDataSet =commonDao.searchEverySql(OilSql);
		StationDataSet =commonDao.searchEverySql(StationSql);
		PIPEDataSet =commonDao.searchEverySql(PIPESql);
		if(OilDataSet !=null &&  OilDataSet.size()>0){
			oilArr = new JSONArray();
			for (Object[] entry : OilDataSet) {
				obj = new JSONObject();
				obj.put("text", entry[1]);
				obj.put("id", entry[0]);
				oilArr.add(obj);
			}
			obj = new JSONObject();
			obj.put("text", "全部");
			obj.put("id", "1");
			oilArr.add(obj);
			
		}
		if(StationDataSet !=null && StationDataSet.size()>0){
			statArr= new JSONArray();
			for (Object[] entry : StationDataSet) {
				obj=  new JSONObject();
				obj.put("text", entry[1]);
				obj.put("id", entry[0]);
				statArr.add(obj);
			}
		}
		if(PIPEDataSet !=null && PIPEDataSet.size()>0){
			pipeArr= new JSONArray();
			for (Object[] entry : PIPEDataSet) {
				obj=  new JSONObject();
				obj.put("text", entry[1]);
				obj.put("id", entry[0]);
				pipeArr.add(obj);
			}
		}
		JSONObject  retobj = new JSONObject();
		retobj.put("oilText", oilArr);
		retobj.put("stationText", statArr);
		retobj.put("pipeText", pipeArr);
		return retobj;
	}
	@Override
	public JSONObject searchOnChangeOil(String oilname, String stationname)throws Exception {

		JSONObject obj = null;
		JSONArray arr = null;
		String StationSql=null;
		String PIPESql = null;
		String oilSql=null;
		//采油 注转 管网管汇
		//String OilSql = "SELECT a.org_id,a.structurename  FROM  PC_CD_ORG_T  a  where  a.structuretype='4'  ORDER BY NLSSORT(a.structurename,'NLS_SORT = SCHINESE_PINYIN_M')";
		//String OilSql = "SELECT a.org_id,a.structurename  FROM  PC_CD_ORG_T  a  where  a.structuretype='4' ORDER BY a.structurename";
		if(oilname !=null &&  !"".equals(oilname) ){
//		 StationSql = "SELECT a.org_id,a.structurename ,a1.structuretype,a1.structurename FROM  PC_CD_ORG_T  a"+ 
//							" left join  pc_cd_station_t b on  a.org_id = b.org_id"+
//							" left join   pc_cd_org_t a1 on a.pid = a1.org_id  "+
//							" where  a.structuretype='7' and a1.structurename='"+oilname+"' ORDER BY a.structurename asc";
//		 PIPESql ="SELECT a.org_id,a.structurename  FROM  PC_CD_ORG_T  a  left join  pc_cd_network_md_t b on  a.org_id = b.org_id "+
//					" left  join  pc_cd_org_t  o  on  a.pid = o.org_id"+
//					" left join  pc_cd_org_t   o1 on  o.pid = o1.org_id"+
//					" where  a.structuretype='21' and  o1.structurename='"+oilname+"'"+
//					" ORDER BY NLSSORT(a.structurename,'NLS_SORT = SCHINESE_PINYIN_M')";
				//oilSql = "select oilid,b.structurename from   (select distinct(oilid) from pc_cd_network_md_v) a left join pc_cd_org_t b on a.oilid = b.org_id order by nlssort(b.STRUCTURENAME,'NLS_SORT=SCHINESE_STROKE_M')";
			//	StationSql="select stationid,b.structurename  from   (select distinct(stationid) from pc_cd_network_md_v  ) a left join pc_cd_org_t b on a.stationid = b.org_id order by nlssort(b.STRUCTURENAME,'NLS_SORT=SCHINESE_STROKE_M')";
				//PIPESql="select b.org_id,b.structurename from  (select  distinct(org_id) from pc_cd_network_md_v) a  left join  pc_cd_org_t b  on  a.org_id = b.org_id ORDER BY b.structurename  asc";
				StationSql="select  distinct(a.stationid),o.structurename  from  pc_cd_network_md_v a left join pc_cd_org_t o on o.org_id = a.stationid where a.OILNAME='"+oilname+"'"+
						"order by nlssort(o.structurename,'NLS_SORT=SCHINESE_STROKE_M') ";
				PIPESql="        select distinct(a.org_id),a.pipe_network  from  pc_cd_network_md_v a left join pc_cd_org_t o on o.org_id = a.org_id where a.OILNAME='"+oilname+"'"+
							"order by nlssort(a.pipe_network,'NLS_SORT=SCHINESE_STROKE_M') ";
		}else{
			 
//			PIPESql ="SELECT a.org_id,a.structurename  FROM  PC_CD_ORG_T  a  left join  pc_cd_network_md_t b on  a.org_id = b.org_id "+
//					" left  join  pc_cd_org_t  o  on  a.pid = o.org_id"+
//					" where  a.structuretype='21' and  o.structurename='"+stationname+"'"+
//					" ORDER BY NLSSORT(a.structurename,'NLS_SORT = SCHINESE_PINYIN_M')";
			StationSql = "select distinct(a.org_id),a.pipe_network  from  pc_cd_network_md_v a left join pc_cd_org_t o on a.org_id = a.stationid  where a.STATIONNAME='"+stationname+"' order by nlssort(a.pipe_network,'NLS_SORT=SCHINESE_STROKE_M')";
			PIPESql ="select distinct(a.org_id),a.pipe_network  from  pc_cd_network_md_v a left join pc_cd_org_t o   on o.org_id = a.stationid where a.STATIONNAME='"+stationname+"'"+
						"order by  nlssort(a.pipe_network,  'NLS_SORT=SCHINESE_STROKE_M')  ";
		}
		List<Object[]> OilDataSet = null;
		List<Object[]> StationDataSet = null;
		List<Object[]> PIPEDataSet = null;
		JSONArray oilArr = null;
		JSONArray statArr = null;
		JSONArray pipeArr = null;
		if(oilname !=null &&  !"".equals(oilname) &&  !oilname.equals("全部")){
			StationDataSet =commonDao.searchEverySql(StationSql);
			PIPEDataSet =commonDao.searchEverySql(PIPESql);
		}else if(stationname !=null && !"".equals(stationname)){
			StationDataSet =commonDao.searchEverySql(StationSql);
			PIPEDataSet =commonDao.searchEverySql(PIPESql);
		}else{
			//OilDataSet =commonDao.searchEverySql(oilSql);
			//StationDataSet =commonDao.searchEverySql(StationSql);
			//PIPEDataSet =commonDao.searchEverySql(PIPESql);
		}
		
		if(StationDataSet !=null && StationDataSet.size()>0){
			statArr= new JSONArray();
			for (Object[] entry : StationDataSet) {
				obj=  new JSONObject();
				obj.put("text", entry[1]);
				obj.put("id", entry[0]);
				statArr.add(obj);
			}
		}
		if(PIPEDataSet !=null && PIPEDataSet.size()>0){
			pipeArr= new JSONArray();
			for (Object[] entry : PIPEDataSet) {
				obj=  new JSONObject();
				obj.put("text", entry[1]);
				obj.put("id", entry[0]);
				pipeArr.add(obj);
			}
		}
		
//		if(OilDataSet !=null && OilDataSet.size()>0){
//			oilArr= new JSONArray();
//			for (Object[] entry : OilDataSet) {
//				obj=  new JSONObject();
//				obj.put("text", entry[1]);
//				obj.put("id", entry[0]);
//				oilArr.add(obj);
//			}
//		}
		JSONObject  retobj = new JSONObject();
		//retobj.put("oilText", oilArr);
		retobj.put("stationText", statArr);
		retobj.put("pipeText", pipeArr);
		return retobj;
	
	}
	@Override
	public HashMap<String, Object> searchData(String oilName,String stationName, String network, int pageNo, String sort,String order, int rowsPerpage, String totalNumFlag) throws Exception {
		JSONArray jsonArr = null;
		HashMap<String,Object> map = new HashMap<String, Object>();
		JSONObject tjo = null;
		String cloums = "select   ";
		String formTable = "  from pc_cd_network_md_v a  where 1=1 ";
		String totalNum = "select count(*) ";
		if(!oilName.equals("")&&oilName!=null&&!oilName.equals("undefined")&& !oilName.equals("全部") ){
			formTable=formTable+" and OILNAME ='"+oilName+"'";
		}
		if(!stationName.equals("")&&stationName!=null&&!stationName.equals("undefined") ){
			formTable=formTable+" and STATIONNAME ='"+stationName+"'";
		}
		if(!network.equals("")&&network!=null&&!network.equals("undefined") ){
			formTable=formTable+" and pipe_network ='"+network+"'";
		}
		String[] cloumnsName ={"PIPE_NETWORK","PIPE_CODE","STATIONNAME","STATIONCODE","OILNAME","QKMC","QKLB","RTU_CODE","SWITCH_IN_FLAG","JRBZ","SCADA_NODE","LJJD","INSTALL_DATE","RLAST_OPER","RLAST_ODATE","REMARK","NETWORK_MD_ID","ORG_ID","STATIONID"};
		String kk="PIPE_NETWORK";
		for(int m=1;m<cloumnsName.length;m++){
			if("RLAST_ODATE".equals(cloumnsName[m])){
				kk=kk+","+"to_char(RLAST_ODATE,'YYYY-MM-DD hh24:mi:ss') as RLAST_ODATE";
			} else{
				kk=kk+","+cloumnsName[m];
			}
		}
		String pjsql = cloums + kk+formTable;
		if ("export".equals(totalNumFlag)) {
			String[] cloumnsNames2 = {"PIPE_NETWORK","PIPE_CODE","STATIONNAME","STATIONCODE","OILNAME","QKMC","QKLB","RTU_CODE","JRBZ","LJJD","INSTALL_DATE","REMARK"} ;
			kk="PIPE_NETWORK";
			for(int m=1;m<cloumnsNames2.length;m++){
//				if("JLCZSJ".equals(cloumnsNames2[m])){
//					kk=kk+","+"to_char(JLCZSJ,'YYYY-MM-DD hh24:mi:ss') as JLCZSJ";
//				}else{
					kk=kk+","+cloumnsNames2[m];
//				}
//			}
					
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
		//排序
		if(!"".equals(sort) && !"PIPE_NETWORK".equals(sort)){
			for (String cloumn : cloumnsName) {
				if(cloumn.equals(sort)){
					pjsql +=" order by " + cloumn + " " + order;
					break;
				}
			}
		}
		else {
			pjsql +=" order by PIPE_NETWORK asc";
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
	public JSONArray searchStation() throws Exception {
		JSONObject obj =null;
		JSONArray  arr =null;
		
		String  sql ="select a.org_id,a.blockstation_name  from  pc_cd_station_t  a  left join  pc_cd_org_t  b  on  a.org_id = b.org_id  order by a.blockstation_name";
		List<Object[]> staDataSet = commonDao.searchEverySql(sql);
		if(staDataSet !=null &&  staDataSet.size()>0){
			arr = new JSONArray();
			for (Object[] entry : staDataSet) {
				obj = new JSONObject();
				obj.put("text", entry[1]);
				obj.put("id", entry[0]);
				arr.add(obj);
			}
			return  arr;
		}
		arr = new JSONArray();
		obj = new JSONObject();
		obj.put("text", "");
		obj.put("id", "");
		arr.add(obj);
		return arr;
	}
	@Override
	public boolean saveOrUpdate(PcCdNetworkMdT net) throws Exception {
		return gwghjcxxDao.saveOrUpdate(net);
	}
	@Override
	public List<PcCdNetworkMdT> searchCheck(String nETWORKMDID,String editNETWORK) throws Exception {
		String hql = "  from  PcCdNetworkMdT a  where 1=1";
		if(nETWORKMDID !=null && !"".equals(nETWORKMDID)){
			hql +="  and  a.networkMdId = '"+nETWORKMDID+"'";
		}
		if(editNETWORK !=null &&  !"".equals(editNETWORK)){
			hql += " and a.pipeNetwork  ='"+editNETWORK+"'";
		}
		List<?> list =null;
		list= commonDao.searchClassQuery(hql);
		return (List<PcCdNetworkMdT>) list;
	}
	@Override
	public boolean removeData(String mdId, String orgId) throws Exception {
		String[] sqls = new String[2];
		sqls[0] = " DELETE from pc_cd_network_md_t d where d.network_md_id = '"+mdId+"'";
		sqls[1] = " DELETE from pc_cd_org_t t where t.org_id = '"+orgId+"'";
		
		return gwghjcxxDao.removeDatas(sqls);
	}
	@Override
	public List<Object[]> searchCheckNot(String editSTATION) throws Exception {
		String sql ="select *  from  pc_cd_org_t a  left join  pc_cd_station_t b   on  a.org_id = b.org_id  where a.org_id ='"+editSTATION+"'";
		return commonDao.searchEverySql(sql);
	}
}
