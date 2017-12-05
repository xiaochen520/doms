package com.echo.service.impl;

 
import java.util.List;

import com.echo.dao.CommonDao;
import com.echo.service.CCZRSHService;

public class CCZRSHServiceImpl implements CCZRSHService{
	private CommonDao commonDao;
	
	
	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}


	@Override
	public List<Object[]> searchCCDatas(String stautsid, String txtDate,
			String deptype,String depname) {
		String tablename = "";
		String sql = "";
		System.out.println("txtDate===="+txtDate);
		//管理员
		if("3".equals(deptype)){
			
			if("15".equals(deptype)){
				tablename = " pc_rpd_thin_wellw_v " ;
			}else{
				tablename = " pc_rpd_thin_wellwb_v " ;
			}
			sql = " select o1.structurename ,a.TEAMNAME,a.zjs,a.kjs,a.cyl,a.bzsh,a.dzsh from "+
			" (select TEAM as TEAMNAME, count(*) as zjs, "+
			" 	sum(case when PROTT >0 then 1 else 0 end) as kjs , "+
			" 	sum(NFV) as cyl,  "+
			" 	sum(case when CLASS_CHECK_OPER is not null then 1 else 0 end) as bzsh, "+
			" 	sum(case when GEOLOGY_CHECK_OPER is not null then 1 else 0 end) as dzsh "+
			" 	from "+tablename+" where 1=1 "+
			" 	and REPORT_DATE =to_date('"+txtDate+"','YYYY-MM-DD') "+
			" 	GROUP BY TEAM) a  "+
			" 	left join pc_cd_org_t o on a.TEAMNAME = o.structurename "+
			" 	left join pc_cd_org_t o1 on o1.org_id = o.pid where 1=1  ";
			if(stautsid != null && !"".equals(stautsid)){
				if("1".equals(stautsid)){
					sql += " and a.bzsh = 0 ";
				}else if("2".equals(stautsid)){
					sql += " and a.bzsh != 0 ";
				}
			}
			if("15".equals(deptype)){
				//sql += "and a.TEAMNAME ='"+depname+"'";
				tablename = " pc_rpd_rule_well_prod_v " ;
			}else{
				//sql += "and o1.structurename ='"+depname+"'";
				tablename = " pc_rpd_rule_well_prodb_v " ;
			}
			sql += "   UNION "+
			" 	select o1.structurename ,a.TEAMNAME,a.zjs,a.kjs,a.cyl,a.bzsh,a.dzsh from "+
			" 	(select TEAMNAME, count(*) as zjs, "+
			" 	sum(case when PROC_TIME_RATION >0 then 1 else 0 end) as kjs , "+
			" 	sum(LIQUID_OUTPUT) as cyl,  "+
			" 	sum(case when CLASS_CHECK_OPER is not null then 1 else 0 end) as bzsh,  "+
			" 	sum(case when GEOLOGY_CHECK_OPER is not null then 1 else 0 end) as dzsh "+
			" 	from "+tablename+" where 1=1  "+
			" 	and REPORT_DATE =to_date('"+txtDate+"','YYYY-MM-DD') "+
			" 	GROUP BY TEAMNAME) a  "+
			" 	left join pc_cd_org_t o on a.TEAMNAME = o.structurename "+
			" 	left join pc_cd_org_t o1 on o1.org_id = o.pid where 1=1  ";
//			if("15".equals(deptype)){
//				sql += "and a.TEAMNAME ='"+depname+"'";
//			}else{
//				sql += "and o1.structurename ='"+depname+"'";
//			}
			
			if(stautsid != null && !"".equals(stautsid)){
				if("1".equals(stautsid)){
					sql += " and a.bzsh = 0 ";
				}else if("2".equals(stautsid)){
					sql += " and a.bzsh != 0 ";
				}
			}
			
//			" order by nlssort(structurename, 'NLS_SORT=SCHINESE_STROKE_M'), "+
//			" nlssort(TEAMNAME, 'NLS_SORT=SCHINESE_STROKE_M') "+
			
		}else{
			//稀油井
			if(depname.indexOf("乌尔禾") != -1 || depname.indexOf("夏子街") != -1){
				if("15".equals(deptype)){
					tablename = " pc_rpd_thin_wellw_v " ;
				}else{
					tablename = " pc_rpd_thin_wellwb_v " ;
				}
			
				sql ="select o1.structurename ,a.TEAMNAME as ,a.zjs,a.kjs,a.cyl,a.bzsh,a.dzsh from "+
					" (select TEAM as TEAMNAME, count(*) as zjs, "+
					" sum(case when PROTT >0 then 1 else 0 end) as kjs , "+
					" sum(NFV) as cyl, "+
					" sum(case when CLASS_CHECK_OPER is not null then 1 else 0 end) as bzsh,  "+
					" sum(case when GEOLOGY_CHECK_OPER is not null then 1 else 0 end) as dzsh "+
					" from "+tablename+" where 1=1 "+
					" and REPORT_DATE =to_date('"+txtDate+"','YYYY-MM-DD') "+
					" GROUP BY TEAM) a "+
					" left join pc_cd_org_t o on a.TEAMNAME = o.structurename "+
					" left join pc_cd_org_t o1 on o1.org_id = o.pid where 1=1 ";
					if("15".equals(deptype)){
						sql += "and a.TEAMNAME ='"+depname+"'";
					}else{
						sql += "and o1.structurename ='"+depname+"'";
					}
					if(stautsid != null && !"".equals(stautsid)){
						if("1".equals(stautsid)){
							sql += " and a.bzsh = 0 ";
						}else if("2".equals(stautsid)){
							sql += " and a.bzsh != 0 ";
						}
					}
					//sql += " order by nlssort(a.TEAMNAME, 'NLS_SORT=SCHINESE_STROKE_M')";
					
			//稠油井
			}else{
				if("15".equals(deptype)){
					tablename = " pc_rpd_rule_well_prod_v " ;
				}else{
					tablename = " pc_rpd_rule_well_prodb_v " ;
				}
				sql = "select o1.structurename ,a.TEAMNAME,a.zjs,a.kjs,a.cyl,a.bzsh,a.dzsh from "+
						" (select TEAMNAME, count(*) as zjs, "+
						" sum(case when PROC_TIME_RATION >0 then 1 else 0 end) as kjs , "+
						" sum(LIQUID_OUTPUT) as cyl,  "+
						" sum(case when CLASS_CHECK_OPER is not null then 1 else 0 end) as bzsh,  "+
						" 	sum(case when GEOLOGY_CHECK_OPER is not null then 1 else 0 end) as dzsh "+
						" from "+tablename+ " where 1=1 "+
						" and REPORT_DATE =to_date('"+txtDate+"','YYYY-MM-DD') "+
						" GROUP BY TEAMNAME) a "+
						" left join pc_cd_org_t o on a.TEAMNAME = o.structurename "+
						" left join pc_cd_org_t o1 on o1.org_id = o.pid where 1=1 ";
						if("15".equals(deptype)){
							sql += "and a.TEAMNAME ='"+depname+"'";
						}else{
							sql += "and o1.structurename ='"+depname+"'";
						}
						if(stautsid != null && !"".equals(stautsid)){
							if("1".equals(stautsid)){
								sql += " and a.bzsh = 0 ";
							}else if("2".equals(stautsid)){
								sql += " and a.bzsh != 0 ";
							}
						}
						//sql += " order by nlssort(a.TEAMNAME, 'NLS_SORT=SCHINESE_STROKE_M')";
						
				
			}
		}
		System.out.println("sql1===="+sql);
		  
		return commonDao.searchEverySql(sql);
	}


	@Override
	public int onAudit(String sql) throws Exception{
		return commonDao.updateButhSql(sql);
	}


	@Override
	public List<String> onAuditAfter(String proceduresName, String date,
			String param) throws Exception {
		return commonDao.getProcedures(proceduresName, date, param);
	}


	@Override
	public List<Object[]> searchZRDatas(String stautsid, String txtDate,
			String deptype, String depname) {
		String tablename = "";
		String sql = "";
		//管理员
		if("3".equals(deptype)){
			
			if("15".equals(deptype)){
				tablename = " pc_rpd_gas_daily_v " ;
			}else{
				tablename = " pc_rpd_gas_dailyb_v " ;
			}
			sql = " select o1.structurename ,a.TEAMNAME,a.zjs,a.kjs,a.cyl,a.bzsh,a.dzsh from "+
			" (select TEAM as TEAMNAME, count(*) as zjs, "+
			" 	sum(case when ZQZWATER >0 then 1 else 0 end) as kjs , "+
			" 	sum(RZL) as cyl,  "+
			" 	sum(case when CLASS_CHECK_OPER is not null then 1 else 0 end) as bzsh, "+
			" 	sum(case when GEOLOGY_CHECK_OPER is not null then 1 else 0 end) as dzsh "+
			" 	from "+tablename+" where 1=1 "+
			" 	and REPORT_DATE =to_date('"+txtDate+"','YYYY-MM-DD') "+
			" 	GROUP BY TEAM) a  "+
			" 	left join pc_cd_org_t o on a.TEAMNAME = o.structurename "+
			" 	left join pc_cd_org_t o1 on o1.org_id = o.pid where 1=1  ";
			if(stautsid != null && !"".equals(stautsid)){
				if("1".equals(stautsid)){
					sql += " and a.bzsh = 0 ";
				}else if("2".equals(stautsid)){
					sql += " and a.bzsh != 0 ";
				}
			}
			if("15".equals(deptype)){
				sql += "and a.TEAMNAME ='"+depname+"'";
				tablename = " pc_rpd_waterflooding_well_v " ;
			}else{
				sql += "and o1.structurename ='"+depname+"'";
				tablename = " pc_rpd_waterflooding_wellb_v " ;
			}
			sql += "   UNION "+
			" 	select o1.structurename ,a.TEAMNAME,a.zjs,a.kjs,a.cyl,a.bzsh,a.dzsh from "+
			" 	(select TEAM as TEAMNAME, count(*) as zjs, "+
			" 	sum(case when ZQZWATER >0 then 1 else 0 end) as kjs , "+
			" 	sum(RZL) as cyl,  "+
			" 	sum(case when CLASS_CHECK_OPER is not null then 1 else 0 end) as bzsh,  "+
			" 	sum(case when GEOLOGY_CHECK_OPER is not null then 1 else 0 end) as dzsh "+
			" 	from "+tablename+" where 1=1  "+
			" 	and REPORT_DATE =to_date('"+txtDate+"','YYYY-MM-DD') "+
			" 	GROUP BY TEAM) a  "+
			" 	left join pc_cd_org_t o on a.TEAMNAME = o.structurename "+
			" 	left join pc_cd_org_t o1 on o1.org_id = o.pid where 1=1  ";
			if("15".equals(deptype)){
				sql += "and a.TEAMNAME ='"+depname+"'";
			}else{
				sql += "and o1.structurename ='"+depname+"'";
			}
			
			if(stautsid != null && !"".equals(stautsid)){
				if("1".equals(stautsid)){
					sql += " and a.bzsh = 0 ";
				}else if("2".equals(stautsid)){
					sql += " and a.bzsh != 0 ";
				}
			}
			
//			" order by nlssort(structurename, 'NLS_SORT=SCHINESE_STROKE_M'), "+
//			" nlssort(TEAMNAME, 'NLS_SORT=SCHINESE_STROKE_M') "+
			
		}else{
			//稀油井
			if(depname.indexOf("乌尔禾") != -1 || depname.indexOf("夏子街") != -1){
				if("15".equals(deptype)){
					tablename = " pc_rpd_waterflooding_well_v " ;
				}else{
					tablename = " pc_rpd_waterflooding_wellb_v " ;
				}
			
				sql ="select o1.structurename ,a.TEAMNAME as ,a.zjs,a.kjs,a.cyl,a.bzsh,a.dzsh from "+
					" (select TEAM as TEAMNAME, count(*) as zjs, "+
					" sum(case when ZQZWATER >0 then 1 else 0 end) as kjs , "+
					" sum(RZL) as cyl, "+
					" sum(case when CLASS_CHECK_OPER is not null then 1 else 0 end) as bzsh,  "+
					" sum(case when GEOLOGY_CHECK_OPER is not null then 1 else 0 end) as dzsh "+
					" from "+tablename+" where 1=1 "+
					" and REPORT_DATE =to_date('"+txtDate+"','YYYY-MM-DD') "+
					" GROUP BY TEAM) a "+
					" left join pc_cd_org_t o on a.TEAMNAME = o.structurename "+
					" left join pc_cd_org_t o1 on o1.org_id = o.pid where 1=1 ";
					if("15".equals(deptype)){
						sql += "and a.TEAMNAME ='"+depname+"'";
					}else{
						sql += "and o1.structurename ='"+depname+"'";
					}
					if(stautsid != null && !"".equals(stautsid)){
						if("1".equals(stautsid)){
							sql += " and a.bzsh = 0 ";
						}else if("2".equals(stautsid)){
							sql += " and a.bzsh != 0 ";
						}
					}
					//sql += " order by nlssort(a.TEAMNAME, 'NLS_SORT=SCHINESE_STROKE_M')";
					
			//稠油井
			}else{
				if("15".equals(deptype)){
					tablename = " pc_rpd_gas_daily_v " ;
				}else{
					tablename = " pc_rpd_gas_dailyb_v " ;
				}
				sql = "select o1.structurename ,a.TEAMNAME,a.zjs,a.kjs,a.cyl,a.bzsh,a.dzsh from "+
						" (select TEAM as TEAMNAME, count(*) as zjs, "+
						" sum(case when ZQZWATER >0 then 1 else 0 end) as kjs , "+
						" sum(RZL) as cyl,  "+
						" sum(case when CLASS_CHECK_OPER is not null then 1 else 0 end) as bzsh,  "+
						" 	sum(case when GEOLOGY_CHECK_OPER is not null then 1 else 0 end) as dzsh "+
						" from "+tablename+ " where 1=1 "+
						" and REPORT_DATE =to_date('"+txtDate+"','YYYY-MM-DD') "+
						" GROUP BY TEAM) a "+
						" left join pc_cd_org_t o on a.TEAMNAME = o.structurename "+
						" left join pc_cd_org_t o1 on o1.org_id = o.pid where 1=1 ";
						if("15".equals(deptype)){
							sql += "and a.TEAMNAME ='"+depname+"'";
						}else{
							sql += "and o1.structurename ='"+depname+"'";
						}
						if(stautsid != null && !"".equals(stautsid)){
							if("1".equals(stautsid)){
								sql += " and a.bzsh = 0 ";
							}else if("2".equals(stautsid)){
								sql += " and a.bzsh != 0 ";
							}
						}
						//sql += " order by nlssort(a.TEAMNAME, 'NLS_SORT=SCHINESE_STROKE_M')";
						
				
			}
		}

		System.out.println("sql==="+sql);
		return commonDao.searchEverySql(sql);
	}




}
