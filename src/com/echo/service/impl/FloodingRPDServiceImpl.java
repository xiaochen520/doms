package com.echo.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.beans.PageControl;
import com.echo.dao.CommonDao;
import com.echo.dao.FloodingRPDDao;
import com.echo.dao.PageDao;
import com.echo.dto.PcRpdRuleWellProdT;
import com.echo.dto.PcRpdWaterfloodingWellT;
import com.echo.dto.PcRpdWaterfloodingWellbT;
import com.echo.service.FloodingRPDService;

public class FloodingRPDServiceImpl implements FloodingRPDService{
	private FloodingRPDDao floodingRPDDao;
	private PageDao pageDao;
	private  CommonDao commonDao;
	
	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}

	public void setFloodingRPDDao(FloodingRPDDao floodingRPDDao) {
		this.floodingRPDDao = floodingRPDDao;
	}

	public void setPageDao(PageDao pageDao) {
		this.pageDao = pageDao;
	}

	@Override
	public HashMap<String, Object> searchData(String oilName,String groupTeam, String stationName, String maniName,
			String wellName,  String stime, String etime, int pageNo,
			String sort, String order, int rowsPerpage, String totalNumFlag,String deptype)
			throws Exception {

		JSONArray jsonArr = null;
		JSONObject tjo = null;
		String tableView = "";
		if("15".equals(deptype)){
			tableView="pc_rpd_waterflooding_well_v";
		}else{
			tableView="pc_rpd_waterflooding_wellb_v";
		}
		HashMap<String,Object> map = new HashMap<String, Object>();
		String cloums = "select   ";
		String formTable = "  from "+tableView+" where 1=1 "+
							"  and report_date between TO_DATE('"+stime+"','YYYY-MM-DD') and TO_DATE('"+etime+"','YYYY-MM-DD') ";
		String totalNum = "select count(*) ";
		if(!oilName.equals("")&&oilName!=null&&!oilName.equals("undefined")&!oilName.equals("全部")){
			formTable=formTable+" and oilname='"+oilName+"'";
		}
		if(!groupTeam.equals("")&&groupTeam!=null&&!groupTeam.equals("undefined")){
			formTable=formTable+" and team='"+groupTeam+"'";
		}
		if(!stationName.equals("")&&stationName!=null&&!stationName.equals("undefined")){
			formTable=formTable+" and injectname='"+stationName+"'";
		}
//		if(!maniName.equals("")&&maniName!=null&&!maniName.equals("undefined")){
//			formTable=formTable+" and maniname='"+maniName+"'";
//		}
		if(!wellName.equals("")&&wellName!=null&&!wellName.equals("undefined")){
			formTable=formTable+" and wellName like '%"+wellName+"%'";
		}
		
		String[] cloumnsName = null;
		if ("export".equals(totalNumFlag)) {
			cloumnsName = new String[]{"wellName","report_date","oilname","team","injectname","zqzwater","pqjby","jkyy","ty",
					"pqj","jk","pzl","rzl","remark"};
		}else{
			cloumnsName = new String[]{"wellName","report_date","oilname","team","injectname","zqzwater","pqjby","jkyy","ty",
					"pqj","jk","pzl","rzl","liquid_flag","remark","class_check_oper","class_check_date","geology_check_oper",
					"geology_check_date","rlast_oper","rlast_odate","org_id","wellrpdid"};
			
		}
		
		String kk="wellName";
		for(int m=1;m<cloumnsName.length;m++){
			if("report_date".equals(cloumnsName[m])){
				kk=kk+","+"to_char(report_date,'YYYY-MM-DD') as report_date"; 
			}else  if("class_check_date".equals(cloumnsName[m])){
				kk=kk+","+"to_char(class_check_date,'YYYY-MM-DD hh24:mi:ss') as class_check_date"; 
			} else if("geology_check_date".equals(cloumnsName[m])){
				kk=kk+","+"to_char(geology_check_date,'YYYY-MM-DD hh24:mi:ss') as geology_check_date"; 
			} else  if("rlast_odate".equals(cloumnsName[m])){
				kk=kk+","+"to_char(rlast_odate,'YYYY-MM-DD hh24:mi:ss') as rlast_odate"; 
			}else{ 
				kk=kk+","+cloumnsName[m];
			} 
		}
		
		String thinOilWellRD = cloums + kk+formTable;
		//获取总条数
		int total = 0;
		if ("1".equals(totalNum)) {
			total = 1;
		} else {
			totalNum += formTable;
			total = pageDao.getCounts(totalNum);
		}
		if ("totalNum".equals(totalNumFlag)) {
			map.put("totalNum", total + "");
			return map;
		}
		//排序
		if(!"".equals(sort) && !"report_date".equals(sort)){
			for (String cloumn : cloumnsName) {
				if(cloumn.equals(sort)){
					thinOilWellRD +=" order by " + cloumn + " " + order;
					break;
				}
				
			}
		}
		else {
			thinOilWellRD +=" order by report_date ";
		}
		//计算分页
		PageControl control = new PageControl();
		//当前页
		List<Object[]> lo = new ArrayList<Object[]>();
		control.setInt_num(rowsPerpage);
		if ("export".equals(totalNumFlag)) {
			lo = floodingRPDDao.searchDataEX(thinOilWellRD);
		}
		else {
			control.init(pageNo, total);
			//开始条数
			int start = control.getStart()-1;
			lo = commonDao.searchPaging(thinOilWellRD,start,rowsPerpage,cloumnsName);
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
	public List<PcRpdWaterfloodingWellT> searchCheckOnly(String waterfloodingWellid, String cjsj ,String rpdID) throws Exception {
		String  hql = "from  PcRpdWaterfloodingWellT a  where 1=1";
		
		if( waterfloodingWellid !=null && !"".equals(waterfloodingWellid)){
			hql +="  and a.orgId = '"+waterfloodingWellid+"'";
		}
		if( cjsj!=null && !"".equals(cjsj)){
			hql += "  and  a.reportDate = TO_DATE('"+cjsj+"' , 'YYYY-MM-DD')";
		}
		if(rpdID !=null && !"".equals(rpdID)){
			hql +="  and  a.wellrpdid= '"+rpdID+"'";
		}
		List<?> list = null;
		list = commonDao.searchClassQuery(hql);
		return (List<PcRpdWaterfloodingWellT>) list;
	}

	@Override
	public boolean saveOrUpdate(PcRpdWaterfloodingWellT rpd) throws Exception {
		return floodingRPDDao.addOrUpdate(rpd);
	}

	@Override
	public boolean removeWaterRPD(String wellrpdid,String deptype) throws Exception {

		String sql ="";
		if( "15".equals(deptype)){
			 sql = "delete  from  pc_rpd_waterflooding_well_t a  where a.wellrpdid='"+wellrpdid+"'";
		}else{
			 sql = "delete  from  pc_rpd_waterflooding_wellb_t a  where a.wellrpdid='"+wellrpdid+"'";
		}
		return commonDao.removeData(sql);
	}

	@Override
	public String searchWFLoodID(String wellname) {
		String sql = "select a.org_id from  pc_cd_waterflooding_well_t a where a.waterflooding_name ='"+wellname+"'";
		List<Object[]> objList = new ArrayList<Object[]>();
		objList = floodingRPDDao.searchWFLoodID(sql);
		Object waterFloodingID = objList.get(0);
		return (String) waterFloodingID;
	}
	public JSONArray searchChangeGroupOnQW(String groupName, String injectName)
	throws Exception {
		String sql ="";
		if(groupName !=null && !"".equals(groupName)){
			sql=" select a.org_id,a.waterflooding_name from  pc_cd_waterflooding_well_t a"+
						" left join pc_cd_org_t o on o.org_id = a.org_id"+
						" left join pc_cd_org_t o1 on o1.org_id = o.pid"+
						" left join  pc_cd_org_t o2 on o2.org_id = o1.pid  where o2.structurename='"+groupName+"'" +
						" order by nlssort(a.waterflooding_name,'NLS_SORT=SCHINESE_STROKE_M')";
		}  else  if(injectName !=null && !"".equals(injectName)){
			sql="select a.org_id,a.waterflooding_name from  pc_cd_waterflooding_well_t a"+
					" left join pc_cd_org_t o on o.org_id = a.org_id"+
					" left join pc_cd_org_t o1 on o1.org_id = o.pid where o1.structurename='"+injectName+"'" +
					" order by nlssort(a.waterflooding_name,'NLS_SORT=SCHINESE_STROKE_M')";
		}else{
			sql="select a.org_id,a.waterflooding_name from  pc_cd_waterflooding_well_t a"+
					" order by nlssort(a.waterflooding_name,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		List<Object[]> objList = commonDao.searchEverySql(sql);
		JSONObject obj = null;
		JSONArray  arr = null;
		if(objList !=null && objList.size()>0){
			arr = new  JSONArray();
			for (Object[] entry : objList) {
				obj= new JSONObject();
				obj.put("text", entry[1]);
				obj.put("id", entry[0]);
				arr.add(obj);
			}
			return  arr;
		}
		arr=  new JSONArray();
		obj = new JSONObject();
		obj.put("text", "");
		obj.put("id", "");
		arr.add(obj);
		return arr;
	}
	public JSONObject searchOnChangeMany(String groupName) throws Exception {
		String grwSql = "";
		String inwSql="";
		JSONObject obj = null;
		JSONArray grwArr = null;
		JSONArray inwArr = null;
			grwSql=" select a.org_id,a.waterflooding_name from  pc_cd_waterflooding_well_t a"+
						" left join pc_cd_org_t o on o.org_id = a.org_id"+
						" left join pc_cd_org_t o1 on o1.org_id = o.pid"+
						" left join  pc_cd_org_t o2 on o2.org_id = o1.pid  where o2.structurename='"+groupName+"'" +
						" order by nlssort(a.waterflooding_name,'NLS_SORT=SCHINESE_STROKE_M')";
		
			inwSql=" select a.org_id,a.water_injectiontopr_name from  pc_cd_water_injectiontopry_t a  left join  pc_cd_org_t o on o.org_id = a.org_id"+
						" left join  pc_cd_org_t o1 on o1.org_id =o.pid  where o1.structurename ='"+groupName+"'"+
						" order by nlssort(a.water_injectiontopr_name,'NLS_SORT=SCHINESE_STROKE_M')";
		List<Object[]> grwSet = null;
		List<Object[]> inwSet = null;
			grwSet = commonDao.searchEverySql(grwSql);
			inwSet = commonDao.searchEverySql(inwSql);
			
			if(grwSet !=null && grwSet.size()>0){
				grwArr = new  JSONArray();
				for (Object[] entry : grwSet) {
					obj= new JSONObject();
					obj.put("text", entry[1]);
					obj.put("id", entry[0]);
					grwArr.add(obj);
				}
			}
			if(inwSet !=null && inwSet.size()>0){
				inwArr = new  JSONArray();
				for (Object[] entry : inwSet) {
					obj= new JSONObject();
					obj.put("text", entry[1]);
					obj.put("id", entry[0]);
					inwArr.add(obj);
				}
			}

		obj = new JSONObject();
		obj.put("wellGRText", grwArr);
		obj.put("wellinText", inwArr);
		return obj;
	}

	public List<String> Dataready(String proceduresName, String date,	String param) throws Exception {
		return commonDao.getProcedures(proceduresName, date, param);
	}
	
	public List<String> Automate(String proceduresName, String date,
			String userid, String param) throws Exception {
		String timeCalssql = "select * from PC_CD_TRIGGERED_PROCESS_T where 1=1 and parameter_name='"+param+"'";
		int calcNum = -16;
		List<Object[]> dateNum = commonDao.searchEverySql(timeCalssql);
		if(dateNum != null && dateNum.size()>0){
			calcNum = Integer.parseInt(String.valueOf(dateNum.get(0)[1]));
		}
		return commonDao.getProcedures(proceduresName, date,userid,calcNum);
	}

	@Override
	public boolean saveOrUpdateB(PcRpdWaterfloodingWellbT rPDb)throws Exception {
		//return commonDao.addOrUpdateDatas((List<?>) rPDb);
		List<PcRpdWaterfloodingWellbT> rpdList = new ArrayList<PcRpdWaterfloodingWellbT>();
  		rpdList.add(rPDb);
  		return commonDao.addOrUpdateDatas(rpdList);
	}

	@Override
	public List<PcRpdWaterfloodingWellbT> searchCheckbOnly(String waterFloodingID, String cjsj, String rpdid) throws Exception {
		String hql =" from PcRpdWaterfloodingWellbT a where  1=1";
		if( waterFloodingID !=null && !"".equals(waterFloodingID)){
			hql +="  and a.orgId = '"+waterFloodingID+"'";
		}
		if( cjsj!=null && !"".equals(cjsj)){
			hql += "  and  a.reportDate = TO_DATE('"+cjsj+"' , 'YYYY-MM-DD')";
		}
		if(rpdid !=null && !"".equals(rpdid)){
			hql +="  and  a.wellrpdid= '"+rpdid+"'";
		} 
		//return (List<PcRpdWaterfloodingWellbT>) commonDao.searchClassQuery(hql);
		List<?> list = null;
		list = commonDao.searchClassQuery(hql);
		return (List<PcRpdWaterfloodingWellbT>) list;
	}
	
}
