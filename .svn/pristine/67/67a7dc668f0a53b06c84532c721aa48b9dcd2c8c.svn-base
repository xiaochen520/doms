package com.echo.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.beans.PageControl;
import com.echo.dao.CommonDao;
import com.echo.dao.GasDailyWHDao;
import com.echo.dao.PageDao;
import com.echo.dto.PcRpdGasDailyT;
import com.echo.dto.PcRpdGasDailybT;
import com.echo.dto.PcRpdThinWellwT;
import com.echo.dto.PcRpdThinWellwbT;
import com.echo.service.GasDailyWHService;
import com.echo.util.CommonsUtil;

public class GasDailyWHServiceImpl implements GasDailyWHService {
		private GasDailyWHDao gasDailyWHDao;
		private  CommonDao commonDao;
		private PageDao pageDao;

		public void setGasDailyWHDao(GasDailyWHDao gasDailyWHDao) {
			this.gasDailyWHDao = gasDailyWHDao;
		}
		public void setCommonDao(CommonDao commonDao) {
			this.commonDao = commonDao;
		}
		public void setPageDao(PageDao pageDao) {
			this.pageDao = pageDao;
		}
		
		@Override
		public HashMap<String, Object> searchData(String oilName,String groupTeam, String stationName,String maniName,
				String wellName, String stime, String etime, int pageNo,
				String sort, String order, int rowsPerpage, String totalNumFlag,String deptype) {
			//15 的时候查询A表
			String  tableView = "";
			if("15".equals(deptype)){
				tableView= "pc_rpd_gas_daily_v";
			}else{
				tableView= "pc_rpd_gas_dailyb_v";
			}
			JSONArray jsonArr = null;
			JSONObject tjo = null;
			HashMap<String,Object> map = new HashMap<String, Object>();
			String cloums = "select   ";
			String formTable = "  from "+tableView+" where 1=1 "+
								"and report_date between TO_DATE('"+stime+"','YYYY-MM-DD') and TO_DATE('"+etime+"','YYYY-MM-DD') ";
			String totalNum = "select count(*) ";
			if(!oilName.equals("")&&oilName!=null&&!oilName.equals("undefined")&!oilName.equals("全部")){
				formTable=formTable+" and oilname='"+oilName+"'";
			}
			if(!groupTeam.equals("")&&groupTeam!=null&&!groupTeam.equals("undefined")){
				formTable=formTable+" and team='"+groupTeam+"'";
			}

			if(!stationName.equals("")&&stationName!=null&&!stationName.equals("undefined")){
				formTable=formTable+" and stationname='"+stationName+"'";
			}
			if(!maniName.equals("")&&maniName!=null&&!maniName.equals("undefined")){
				formTable=formTable+" and maniname='"+maniName+"'";
			}
			if(!wellName.equals("")&&wellName!=null&&!wellName.equals("undefined")){
				formTable=formTable+" and well_name like '%"+wellName+"%'";
			}
			
			String[] cloumnsName = {"well_name","report_date","oilname","stationname","team","maniname","zqzwater","pqjby","jkyy","ty",
									"pqj","jk","pzl","rzl","liquid_flag","remark","class_check_oper","class_check_date","geology_check_oper",
									"geology_check_date","rlast_oper","rlast_odate","org_id","gaswellrpdid"};
			String kk="well_name";
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
			if ("export".equals(totalNumFlag)) {
				String[] cloumnsName2 = {"well_name","report_date","oilname","team","stationname","maniname","zqzwater","pqjby","jkyy","ty",
										"pqj","jk","pzl","rzl","remark"};
				
				 kk="well_name";
				for(int n=1;n<cloumnsName2.length;n++){
					if("report_date".equals(cloumnsName2[n])){
						kk=kk+","+"to_char(report_date,'YYYY-MM-DD') as report_date"; 
//					}else  if("CLASS_CHECK_DATE".equals(cloumnsName[m])){
//						kk=kk+","+"to_char(CLASS_CHECK_DATE,'YYYY-MM-DD') as CLASS_CHECK_DATE"; 
//					} else if("GEOLOGY_CHECK_DATE".equals(cloumnsName[m])){
//						kk=kk+","+"to_char(GEOLOGY_CHECK_DATE,'YYYY-MM-DD') as GEOLOGY_CHECK_DATE"; 
//					} else  if("RLAST_ODATE".equals(cloumnsName[m])){
//						kk=kk+","+"to_char(RLAST_ODATE,'YYYY-MM-DD') as report_date"; 
					}else{ 
						kk=kk+","+cloumnsName2[n];
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
				lo = gasDailyWHDao.searchDataEX(thinOilWellRD);
			}
			else {
				control.init(pageNo, total);
				//开始条数
				int start = control.getStart()-1;
				lo = commonDao.searchPaging(thinOilWellRD,start,rowsPerpage,cloumnsName);
			}
			if ("export".equals(totalNumFlag)) {

				if (lo != null && 0 < lo.size()) {
					// 生成树节点json文档
					jsonArr = new JSONArray();
					for (Object[] o : lo) {
						tjo = new JSONObject();
		
						for (int i = 0; i < o.length; i++) {
							if (o[i] == null||o[i].equals("null")) {
								o[i] = "";
							}
//							if (!"".equals(o[20]) && o[20] !=null) {
//								if(o[20].toString().equals("0")){
//									o[20] ="<span style='color: black;'>"+o[20]+"</span>";
//									//tjo.put(cloumnsName[i], "<span style='color: red'>"+0+"</span>");
//								}else if(o[20].equals("1")){
//									o[20] = ("<span style='color: green;'>"+o[20]+"</span>");
//								}else if(o[20].equals("2")){
//									o[20] = ("<span style='color: purple;'>"+o[20]+"</span>");
//								}else{
//									o[20] = ("<span style='color: red;'>"+o[20]+"</span>");
//								} 
//							}else{
//								o[20] ="";
//							}
								tjo.put(cloumnsName[i], o[i].toString());
							
						}
						jsonArr.add(tjo);
					}
				}
			
			}else{
				if (lo != null && 0 < lo.size()) {
					// 生成树节点json文档
					jsonArr = new JSONArray();
					for (Object[] o : lo) {
						tjo = new JSONObject();
		
						for (int i = 0; i < o.length; i++) {
							if (o[i] == null||o[i].equals("null")) {
								o[i] = "";
							}
							if (!"".equals(o[14]) && o[14] !=null) {
								if(o[14].toString().equals("0")){
									o[14] ="<span style='color: black;'>"+o[14]+"</span>";
									//tjo.put(cloumnsName[i], "<span style='color: red'>"+0+"</span>");
								}else if(o[14].equals("1")){
									o[14] = ("<span style='color: green;'>"+o[14]+"</span>");
								}else if(o[14].equals("2")){
									o[14] = ("<span style='color: purple;'>"+o[14]+"</span>");
								}else{
									o[14] = ("<span style='color: red;'>"+o[14]+"</span>");
								} 
							}else{
								o[14] ="";
							}
								tjo.put(cloumnsName[i], CommonsUtil.getClearNullData(o[i]));
							
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
		public List<PcRpdGasDailyT> searchCheckOnly(String orgId, String rq,String rpdId,String name) throws Exception {
			String  hql = " from PcRpdGasDailyT a where 1=1";
			if(orgId !=null && !"".equals(orgId)){
				hql +="  and a.orgId = '"+orgId+"'"; 
			}
			if(rq !=null && !"".equals(rq)){
				hql +=" and a.reportDate  = to_date('"+rq+"' , 'YYYY--MM-DD')";
			}
			if(rpdId !=null && !"".equals(rpdId)){
				hql +=" and a.gaswellrpdid = '"+rpdId+"'";
			}
			if(name !=null && !"".equals(name)){
				hql +=" and a.wellName = '"+name+"'";
			}
			List<?> list = null;
			list = commonDao.searchClassQuery(hql);
			return (List<PcRpdGasDailyT>) list;
		}

		@Override
		public boolean saveOrUpdate(PcRpdGasDailyT rpdT) throws Exception {
			return gasDailyWHDao.saveRPD(rpdT);
		}

		@Override
		public boolean removeDataRPD(String rpdId,String deptype) throws Exception {
			String sql ="";
			if( "15".equals(deptype)){
				 sql = "delete  from  pc_rpd_gas_daily_t a  where a.gaswellrpdid='"+rpdId+"'";
			}else{
				 sql = "delete  from  pc_rpd_gas_dailyb_t a  where a.gaswellrpdid='"+rpdId+"'";
			}
			//String sql = "delete  from  pc_rpd_thin_wellw_t a  where a.thinwellrpd='"+rpdId+"'";
			return commonDao.removeData(sql);
		}

		@Override
		public String searchWellID(String wellNameEdit) {
			String sql = "select   a.org_id from  pc_cd_thinoil_well_t a  where a.well_name = '"+wellNameEdit+"'";
			List<Object[]> objList = new ArrayList<Object[]>();
			objList = gasDailyWHDao.searchWellID(sql);
			Object  wellID = objList.get(0);
			return (String) wellID;
		}

		@Override
		public List<PcRpdGasDailybT> searchCheckbOnly(String orgId, String rq,String rpdId,String name) throws Exception {
			String hql = " from PcRpdGasDailybT a  where 1=1";
			if(orgId !=null && !"".equals(orgId)){
				hql +="  and a.orgId = '"+orgId+"'"; 
			}
			if(rq !=null && !"".equals(rq)){
				hql +=" and a.reportDate  = to_date('"+rq+"' , 'YYYY--MM-DD')";
			}
			if(rpdId !=null && !"".equals(rpdId)){
				hql +=" and a.gaswellrpdid = '"+rpdId+"'";
			}
			if(name !=null && !"".equals(name)){
				hql +=" and a.wellName = '"+name+"'";
			}
			List<?> list = null;
			list = commonDao.searchClassQuery(hql);
			return (List<PcRpdGasDailybT>) list;
		}

		@Override
		public boolean saveOrUpdateB(PcRpdGasDailybT rpdT) throws Exception {
		
			return gasDailyWHDao.saveRPDB(rpdT);
		}

		@Override
		public List<String> Dataready(String proceduresName, String date,	String param) throws Exception {
			return commonDao.getProcedures(proceduresName, date, param);
		}
		
		public List<String> Automate(String proceduresName, String date,
				String userid,String param) throws Exception {
			String timeCalssql = "select * from PC_CD_TRIGGERED_PROCESS_T where 1=1 and parameter_name='"+param+"'";
			int calcNum = -16;
			List<Object[]> dateNum = commonDao.searchEverySql(timeCalssql);
			if(dateNum != null && dateNum.size()>0){
				calcNum = Integer.parseInt(String.valueOf(dateNum.get(0)[1]));
			}
			return commonDao.getProcedures(proceduresName, date,userid,calcNum);
		}
		@Override
		public String searchOrgId(String wellname) throws Exception {
			//String sql ="select a.org_id   from  pc_cd_thinoil_well_t  a  where a.well_name='"+wellname+"'";
			String sql ="select a.org_id   from  pc_cd_org_t  a  where  a.structurename='"+wellname+"'";
			List<Object[]> objList = commonDao.searchEverySql(sql);
			Object orgId = objList.get(0);
			return (String)orgId;
		}
}
