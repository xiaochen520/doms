package com.echo.service.impl;

 
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.echo.dao.CommonDao;
import com.echo.dto.PcCdOrgT;
import com.echo.dto.PcCdWaterfloodingWellT;
import com.echo.dto.PcRpdGasDailyT;
import com.echo.dto.PcRpdGasDailybT;
import com.echo.dto.PcRpdRuleWellProdT;
import com.echo.dto.PcRpdRuleWellProdbT;
import com.echo.dto.PcRpdThinWellwT;
import com.echo.dto.PcRpdThinWellwbT;
import com.echo.dto.PcRpdWaterfloodingWellT;
import com.echo.dto.PcRpdWaterfloodingWellbT;
import com.echo.dto.PcRpdWellSagddT;
import com.echo.service.ExcelService;
import com.echo.util.CommonsUtil;
import com.echo.util.DateBean;

public class ExcelServiceImpl implements ExcelService{
	
	private CommonDao commonDao;

	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}

	/**
	 * 来源页面及上传EXCEL 表头校验
	 * @param sign --
	 * cgcyrb :常规稠油日报
	 * xyrb   :稀油日报
	 * zsrb	  :注水日报
	 * zqrb   :注汽日报
	 * @param excelTile :上传EXCEL 表头
	 * @return List<Integer> 
	 * 第一位 0：正确，1失败
	 * 第二位 返回上传EXCEL 每行位数
	 */
	@Override
	public List<Integer> excelTile(String sign, String excelTile) throws Exception {
		
		List<Integer> returnParam = new ArrayList<Integer>();
		if("cgcyrb".equals(sign)){
			
			if("常规稠油日报".equals(excelTile)){
				returnParam.add(0);
				returnParam.add(23);
				
			}else{
				returnParam.add(1);
			}
			
		}else if("xyrb".equals(sign)){
			if("稀油日报".equals(excelTile)){
				returnParam.add(0);
				returnParam.add(23);
				
			}else{
				returnParam.add(1);
			}
			
		}else if("zsrb".equals(sign)){
			
			if("注水日报".equals(excelTile)){
				returnParam.add(0);
				returnParam.add(17);
				
			}else{
				returnParam.add(1);
			}
		}else if("zqrb".equals(sign)){
			if("注汽日报".equals(excelTile)){
				returnParam.add(0);
				returnParam.add(17);
				
			}else{
				returnParam.add(1);
			}
			
		}else{
			returnParam.add(1);
		}
		return returnParam;
	}
	/**
	 * @param String deptype 操作人员所属部门类型 
	 * @param List row	要插入数据
	 * @return HashMap<String, Object> returnMap
	 * exportResult : 导入结果  -- 0：成功  1：失败
	 */
	@Override
	public HashMap<String, Object> insertRuleExcel(String deptype, List row,String OperName,int columsun)
			throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		String tableDto = "";
		//班组人员查询A表   
		if("15".equals(deptype)){
			tableDto = "PcRpdRuleWellProdT";
		
			
			if(row.get(0) != null && !"".equals(row.get(0).toString()) && !"null".equals(row.get(0).toString())&&
					row.get(1) != null && !"".equals(row.get(1).toString()) && !"null".equals(row.get(1).toString()) ){
				String hql = "from "+tableDto+" t where 1=1 and t.wellName ='"+row.get(0).toString()+"' and t.reportDate = to_date('"+DateBean.clearDateN(row.get(1).toString())+"','YYYY-MM-DD')";
				String sql ="";
				String error ="";
				List<Object[]> org = null;
				List newRow = row;
				PcRpdRuleWellProdT pcRpdRuleWellProd = null;
				List<PcRpdRuleWellProdT> pcRpdRuleWellProds = (List<PcRpdRuleWellProdT>) commonDao.searchClassQuery(hql);
				//查询导入数据在数据库中是否存在
				
				if(pcRpdRuleWellProds != null && pcRpdRuleWellProds.size() >0){
					pcRpdRuleWellProd = pcRpdRuleWellProds.get(0);
				}else{
					pcRpdRuleWellProd = new PcRpdRuleWellProdT();
					//获取组织结构ID
					sql = "select o.org_id,o.structurename from pc_cd_org_t o where 1=1 and o.structurename ='"+row.get(0).toString()+"'";
					org = commonDao.searchEverySql(sql);
					if(org != null && org.size()>0){
						pcRpdRuleWellProd.setOrgId(org.get(0)[0].toString());
						pcRpdRuleWellProd.setWellName(row.get(0).toString());
						pcRpdRuleWellProd.setReportDate(DateBean.getStrDate(DateBean.clearDateN(row.get(1).toString())));
					}else{
						newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-2, "失败");
				    	newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-1, "井名错误，无该井基础信息！");
				    	returnMap.put("list", newRow);
				    	returnMap.put("exportResult", "1");
				    	return returnMap;
				    	
					}
				}
				
				//数据封装
				pcRpdRuleWellProd.setProcTimeRation(CommonsUtil.getBigDecimalData(row.get(6)));
				pcRpdRuleWellProd.setStroke(CommonsUtil.getBigDecimalData(row.get(7)));
				pcRpdRuleWellProd.setJigFrequency(CommonsUtil.getBigDecimalData(row.get(8)));
				pcRpdRuleWellProd.setFlowNipple(CommonsUtil.getBigDecimalData(row.get(9)));
				pcRpdRuleWellProd.setTubingPres(CommonsUtil.getBigDecimalData(row.get(10)));
				pcRpdRuleWellProd.setCasingPres(CommonsUtil.getBigDecimalData(row.get(11)));
				pcRpdRuleWellProd.setBackPres(CommonsUtil.getBigDecimalData(row.get(12)));
				pcRpdRuleWellProd.setOilTemp(CommonsUtil.getBigDecimalData(row.get(13)));  //油温℃
				pcRpdRuleWellProd.setLiquidOutput(CommonsUtil.getBigDecimalData(row.get(14)));
				pcRpdRuleWellProd.setGasOutput(CommonsUtil.getBigDecimalData(row.get(15))); //日产气量m3
				pcRpdRuleWellProd.setSampling(CommonsUtil.getClearNullData(row.get(16)));
				pcRpdRuleWellProd.setRuntime(CommonsUtil.getBigDecimalData(row.get(17)));
				pcRpdRuleWellProd.setPumpErrorTime(CommonsUtil.getBigDecimalData(row.get(18)));
				pcRpdRuleWellProd.setPumpErrorDesc(CommonsUtil.getClearNullData(row.get(19)));
				pcRpdRuleWellProd.setRemark(CommonsUtil.getClearNullData(row.get(20)));
				//pcRpdRuleWellProd.setLiquidFlag((byte)0);  //计量标志（0为手动录入，1为采出液提取，2为沿用前一天计量结果，3为采出液提取值异常）
				pcRpdRuleWellProd.setLiquidFlag(java.math.BigDecimal.valueOf(0));
				//pcRpdRuleWellProd.setClassCheckOper(OperName);
				//Date date = new Date();
				//pcRpdRuleWellProd.setClassCheckDate(date);
				
				pcRpdRuleWellProd.setRlastOper(OperName);
				pcRpdRuleWellProd.setRlastOdate(new Date());
				pcRpdRuleWellProds.clear();
				pcRpdRuleWellProds.add(pcRpdRuleWellProd);
				boolean exportFlg = true;
				try {
					exportFlg = commonDao.addOrUpdateDatas(pcRpdRuleWellProds);
				} catch (Exception e) {
					e.printStackTrace();
					error =  e.getCause().getCause().toString();
					
					newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-2, "失败");
			    	newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-1, error);
			    	returnMap.put("list", newRow);
			    	returnMap.put("exportResult", "1");
			    	return returnMap;
				}
				
				
				if(exportFlg){
					newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-2, "成功");
					newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-1, "");
			    	returnMap.put("list", newRow);
			    	returnMap.put("exportResult", "0");
			    	
				}else{
					newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-2, "失败");
			    	newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-1, "数据库操作失败，请检查数据");
			    	returnMap.put("list", newRow);
			    	returnMap.put("exportResult", "1");
				}
				
			}
			//地质组人员查询B表   		
		}else{
			tableDto = "PcRpdRuleWellProdbT";
			
			if(row.get(0) != null && !"".equals(row.get(0).toString()) && !"null".equals(row.get(0).toString())&&
					row.get(1) != null && !"".equals(row.get(1).toString()) && !"null".equals(row.get(1).toString()) ){
				String hql = "from "+tableDto+" t where 1=1 and t.wellName ='"+row.get(0).toString()+"' and t.reportDate = to_date('"+DateBean.clearDateN(row.get(1).toString())+"','YYYY-MM-DD')";
				String sql ="";
				String error ="";
				List<Object[]> org = null;
				List newRow = row;
				PcRpdRuleWellProdbT pcRpdRuleWellProd = null;
				List<PcRpdRuleWellProdbT> pcRpdRuleWellProds = (List<PcRpdRuleWellProdbT>) commonDao.searchClassQuery(hql);
				//查询导入数据在数据库中是否存在
				
				if(pcRpdRuleWellProds != null && pcRpdRuleWellProds.size() >0){
					pcRpdRuleWellProd = pcRpdRuleWellProds.get(0);
				}else{
					pcRpdRuleWellProd = new PcRpdRuleWellProdbT();
					//获取组织结构ID
					sql = "select o.org_id,o.structurename from pc_cd_org_t o where 1=1 and o.structurename ='"+row.get(0).toString()+"'";
					org = commonDao.searchEverySql(sql);
					if(org != null && org.size()>0){
						pcRpdRuleWellProd.setOrgId(org.get(0)[0].toString());
						pcRpdRuleWellProd.setWellName(row.get(0).toString());
						pcRpdRuleWellProd.setReportDate(DateBean.getStrDate(row.get(1).toString()));
					}else{
						
				    	newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-2, "失败");
				    	newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-1, "井名错误，无该井基础信息！");
				    	returnMap.put("list", newRow);
				    	returnMap.put("exportResult", "1");
				    	return returnMap;
				    	
					}
				}
				
				//数据封装
				pcRpdRuleWellProd.setProcTimeRation(CommonsUtil.getBigDecimalData(row.get(6)));
				pcRpdRuleWellProd.setStroke(CommonsUtil.getBigDecimalData(row.get(7)));
				pcRpdRuleWellProd.setJigFrequency(CommonsUtil.getBigDecimalData(row.get(8)));
				pcRpdRuleWellProd.setFlowNipple(CommonsUtil.getBigDecimalData(row.get(9)));
				pcRpdRuleWellProd.setTubingPres(CommonsUtil.getBigDecimalData(row.get(10)));
				pcRpdRuleWellProd.setCasingPres(CommonsUtil.getBigDecimalData(row.get(11)));
				pcRpdRuleWellProd.setBackPres(CommonsUtil.getBigDecimalData(row.get(12)));
				pcRpdRuleWellProd.setOilTemp(CommonsUtil.getBigDecimalData(row.get(13)));  //油温℃
				pcRpdRuleWellProd.setLiquidOutput(CommonsUtil.getBigDecimalData(row.get(14)));
				pcRpdRuleWellProd.setGasOutput(CommonsUtil.getBigDecimalData(row.get(15))); //日产气量m3
				pcRpdRuleWellProd.setSampling(CommonsUtil.getClearNullData(row.get(16)));
				pcRpdRuleWellProd.setRuntime(CommonsUtil.getBigDecimalData(row.get(17)));
				pcRpdRuleWellProd.setPumpErrorTime(CommonsUtil.getBigDecimalData(row.get(18)));
				pcRpdRuleWellProd.setPumpErrorDesc(CommonsUtil.getClearNullData(row.get(19)));
				pcRpdRuleWellProd.setRemark(CommonsUtil.getClearNullData(row.get(20)));
				pcRpdRuleWellProd.setLiquidFlag(java.math.BigDecimal.valueOf(0));  //产液量标志（0为手动录入，1为采出液提取，2为沿用前一天计量结果，3为采出液提取值异常）
				//pcRpdRuleWellProd.setClassCheckOper(OperName);
				//Date date = new Date();
				//pcRpdRuleWellProd.setClassCheckDate(date);
				
				pcRpdRuleWellProd.setRlastOper(OperName);
				pcRpdRuleWellProd.setRlastOdate(new Date());
				pcRpdRuleWellProds.clear();
				pcRpdRuleWellProds.add(pcRpdRuleWellProd);
				boolean exportFlg = true;
				try {
					exportFlg = commonDao.addOrUpdateDatas(pcRpdRuleWellProds);
				} catch (Exception e) {
					e.printStackTrace();
					error =  e.getCause().getCause().toString();
					
			    	newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-2, "失败");
			    	newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-1, error);
			    	returnMap.put("list", newRow);
			    	returnMap.put("exportResult", "1");
			    	return returnMap;
				}
				
				
				if(exportFlg){
			    	newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-2, "成功");
			    	newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-1, "");
			    	returnMap.put("list", newRow);
			    	returnMap.put("exportResult", "0");
			    	
				}else{
			    	newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-2, "失败");
			    	newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-1, "数据库操作失败，请检查数据");
			    	returnMap.put("list", newRow);
			    	returnMap.put("exportResult", "1");
				}
				
			}
		}
		
		
		return returnMap;
	}

	@Override
	public HashMap<String, Object> insertBasicExcel(List row,
			int columsun) throws Exception {

		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		
		
				//注水井名
				String wellname = row.get(0).toString();
				String zsqname = row.get(2).toString();
				String qk = row.get(1).toString();
				String sql = "select o.org_id,o.structurename from pc_cd_org_t o where 1=1 and o.structurename ='"+wellname+"' and o.structuretype = 9 ";
				String error ="";
				List newRow = row;
		
				//管汇名
//				String wellname = row.get(0).toString();
//				if(wellname.indexOf("#") == wellname.length()-1){
//					wellname = wellname+"站";
//				}
//				if("乌尔禾采油站".equals(row.get(1).toString())){
//					wellname = "乌尔禾"+wellname;
//				}else if("夏子街采油站".equals(row.get(1).toString())){
//					wellname = "夏子街"+wellname;
//					
//				}
				//String hql = "from "+tableDto+" t where 1=1 and t.blockstationName ='"+row.get(0).toString()+"' ";
//				String sql = "select o.org_id,o.structurename from pc_cd_org_t o where 1=1 and o.structurename ='"+row.get(2).toString()+"' and o.structuretype = 9 ";
//				String error ="";
//				List newRow = row;
				//注转站
				//PcCdStationT dto = new PcCdStationT();
				//List<PcCdStationT> dtos = new ArrayList<PcCdStationT>();
				//管汇
//				PcCdManifoldT dto = new PcCdManifoldT();
//				List<PcCdManifoldT> dtos = new ArrayList<PcCdManifoldT>();
				//稀油井
				PcCdWaterfloodingWellT dto = new PcCdWaterfloodingWellT();
				List<PcCdWaterfloodingWellT> dtos = new ArrayList<PcCdWaterfloodingWellT>();
				PcCdOrgT org = new PcCdOrgT();
				List<Object[]> orgs = commonDao.searchEverySql(sql);
				//查询导入数据在数据库中是否存在
				
				if(orgs != null && orgs.size() >0){
					newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-2, "失败");
			    	newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-1, "数据存在！");
			    	returnMap.put("list", newRow);
			    	returnMap.put("exportResult", "1");
			    	return returnMap;
				}
				
				sql = "select o.org_id,o.structurename from pc_cd_org_t o where 1=1 and o.structurename ='"+zsqname+"' and o.structuretype = 13 ";
				orgs = commonDao.searchEverySql(sql);
				
				if(orgs != null && orgs.size() >0){
					zsqname = orgs.get(0)[0].toString();
				}else{
					newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-2, "失败");
			    	newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-1, "注水撬数据不存在！");
			    	returnMap.put("list", newRow);
			    	returnMap.put("exportResult", "1");
			    	return returnMap;
				}
				
				
				sql = "select o.org_id,o.structurename from pc_cd_org_t o where 1=1 and o.structurename ='"+qk+"' and o.structuretype = 6 ";
				orgs = commonDao.searchEverySql(sql);
				
				if(orgs != null && orgs.size() >0){
					qk = orgs.get(0)[0].toString();
				}else{
					newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-2, "失败");
			    	newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-1, "注水撬数据不存在！");
			    	returnMap.put("list", newRow);
			    	returnMap.put("exportResult", "1");
			    	return returnMap;
				}
				/********************注水井***********************/
				dto.setQkid(qk);
				dto.setWaterfloodingName(wellname);
				
				/********************注转站***********************/
//				dto.setBlockstationName(wellname);
//				dto.setBsType("3");
//				dto.setGhs((byte)1);
//				
//				dto.setRlastOper("安控科技");
//				dto.setRlastOdate(new Date());
//				
//				
//				org.setStructuretype((byte)7);
//				org.setStructurename(wellname);
//				if("乌尔禾采油站".equals(row.get(1).toString())){
//					org.setPid("664D4458C23140C8B998C510F98D6AAB");
//				}else if("夏子街采油站".equals(row.get(1).toString())){
//					org.setPid("CD1D9705353A41EEB536E7FBCCDAE8C4");
//					
//				}
//				org.setStatusValue("5");
//				org.setSwitchInFlag("0");
				
				/*******************************************/
//				dto.setWellName(row.get(2).toString());
//				dto.setBewellName(row.get(2).toString());
//				if("乌尔禾采油站".equals(row.get(1).toString())){
//					dto.setTeamId("402893ce475d1f6901475d26ce090002");
//				}else if("夏子街采油站".equals(row.get(1).toString())){
//					dto.setTeamId("131C09450DC749D9A826D3B0D591ED0A");
//					
//				}
				dto.setRlastOper("安控科技");
				dto.setRlastOdate(new Date());
				
				
				org.setStructuretype((byte)9);
				org.setStructurename(wellname);
				org.setPid(zsqname);
//				sql = "select o.org_id,o.structurename from pc_cd_org_t o where 1=1 and o.structurename ='"+wellname+"' and o.structuretype = 8 ";
//				orgs = commonDao.searchEverySql(sql);
//				//查询导入数据在数据库中是否存在
//				
//				if(orgs != null && orgs.size() >0){
//					org.setPid(orgs.get(0)[0].toString());
//				}else{
//					newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-2, "失败");
//			    	newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-1, "上级节点不存在！");
//			    	returnMap.put("list", newRow);
//			    	returnMap.put("exportResult", "1");
//			    	return returnMap;
//				}
				
				org.setStatusValue("5");
				org.setSwitchInFlag("0");
				org.setCode(wellname);
				dto.setPcCdOrgT(org);
				dtos.add(dto);
				boolean exportFlg = true;
				try {
					exportFlg = commonDao.addOrUpdateDatas(dtos);
				} catch (Exception e) {
					e.printStackTrace();
					error =  e.getCause().getCause().toString();
					
					newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-2, "失败");
			    	newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-1, error);
			    	returnMap.put("list", newRow);
			    	returnMap.put("exportResult", "1");
			    	return returnMap;
				}
				
				
				if(exportFlg){
					newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-2, "成功");
					newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-1, "");
			    	returnMap.put("list", newRow);
			    	returnMap.put("exportResult", "0");
			    	
				}else{
					newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-2, "失败");
			    	newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-1, "数据库操作失败，请检查数据");
			    	returnMap.put("list", newRow);
			    	returnMap.put("exportResult", "1");
				}
				
		
		
		
		return returnMap;
	
	}

	//稀油井日报维护
	public HashMap<String, Object> insertThinExcel(String deptype, List row, String operName, int columsun) throws Exception {

		// TODO Auto-generated method stub
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		String tableDto = "";
		//班组人员查询A表   
		if("15".equals(deptype)){
			tableDto = "PcRpdThinWellwT";
		
			
			if(row.get(0) != null && !"".equals(row.get(0).toString()) && !"null".equals(row.get(0).toString())&&
					row.get(1) != null && !"".equals(row.get(1).toString()) && !"null".equals(row.get(1).toString()) ){
				String hql = "from "+tableDto+" t where 1=1 and t.wellName ='"+row.get(0).toString()+"' and t.reportDate = to_date('"+DateBean.clearDateN(row.get(1).toString())+"','YYYY-MM-DD')";
				String sql ="";
				String error ="";
				List<Object[]> org = null;
				List newRow = row;
				PcRpdThinWellwT rpd = null;
				List<PcRpdThinWellwT> rpdList =null;
				rpdList = (List<PcRpdThinWellwT>) commonDao.searchClassQuery(hql);
				//查询导入数据在数据库中是否存在
				
				if(rpdList != null && rpdList.size() >0){
					rpd = rpdList.get(0);
				}else{
					rpd = new PcRpdThinWellwT();
					//获取组织结构ID
					sql = "select o.org_id,o.structurename from pc_cd_org_t o where 1=1 and o.structurename ='"+row.get(0).toString()+"'";
					org = commonDao.searchEverySql(sql);
					if(org != null && org.size()>0){
						//rpd.setOrgId(org.get(0)[0].toString());
						rpd.setWellName(row.get(0).toString());
						rpd.setWellid(org.get(0)[0].toString());
						rpd.setreportDate(DateBean.getStrDate(DateBean.clearDateN(row.get(1).toString())));
					}else{
						newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-2, "失败");
				    	newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-1, "井名错误，无该井基础信息！");
				    	returnMap.put("list", newRow);
				    	returnMap.put("exportResult", "1");
				    	return returnMap;
				    	
					}
				}
				
				//数据封装
				rpd.setPrott(CommonsUtil.getBigDecimalData(row.get(5)));
				rpd.setStroke(CommonsUtil.getBigDecimalData(row.get(6)));
				rpd.setAtTimes(CommonsUtil.getBigDecimalData(row.get(7)));
				rpd.setNozzle(CommonsUtil.getBigDecimalData(row.get(8)));
				rpd.setPressure(CommonsUtil.getBigDecimalData(row.get(9)));
				rpd.setTcpr(CommonsUtil.getBigDecimalData(row.get(10)));
				rpd.setBackpre(CommonsUtil.getBigDecimalData(row.get(11)));
				rpd.setTot(CommonsUtil.getBigDecimalData(row.get(12)));  //油温℃
				rpd.setNfv(CommonsUtil.getBigDecimalData(row.get(13)));
				rpd.setGasp(CommonsUtil.getBigDecimalData(row.get(14))); //日产气量m3
				rpd.setSampli(CommonsUtil.getClearNullData(row.get(15)));
				rpd.setPumpingTime(CommonsUtil.getBigDecimalData(row.get(16)));
				rpd.setPumpingMachine(CommonsUtil.getBigDecimalData(row.get(17)));
				rpd.setPumpingDescription(CommonsUtil.getClearNullData(row.get(18)));
				rpd.setRemark(CommonsUtil.getClearNullData(row.get(19)));
				//rpd.setLiquidFlag((byte)0);  //计量标志（0为手动录入，1为采出液提取，2为沿用前一天计量结果，3为采出液提取值异常）
				rpd.setLiquidFlag(java.math.BigDecimal.valueOf(0));
				
				rpd.setRlastOper(operName);
				rpd.setRlastOdate(new Date());
				rpdList.clear();
				rpdList.add(rpd);
				boolean exportFlg = true;
				try {
					exportFlg = commonDao.addOrUpdateDatas(rpdList);
				} catch (Exception e) {
					e.printStackTrace();
					error =  e.getCause().getCause().toString();
					
					newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-2, "失败");
			    	newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-1, error);
			    	returnMap.put("list", newRow);
			    	returnMap.put("exportResult", "1");
			    	return returnMap;
				}
				
				
				if(exportFlg){
					newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-2, "成功");
					newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-1, "");
			    	returnMap.put("list", newRow);
			    	returnMap.put("exportResult", "0");
			    	
				}else{
					newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-2, "失败");
			    	newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-1, "数据库操作失败，请检查数据");
			    	returnMap.put("list", newRow);
			    	returnMap.put("exportResult", "1");
				}
				
			}
			//地质组人员查询B表   		
		}else{
			tableDto = "PcRpdThinWellwbT";
			
			if(row.get(0) != null && !"".equals(row.get(0).toString()) && !"null".equals(row.get(0).toString())&&
					row.get(1) != null && !"".equals(row.get(1).toString()) && !"null".equals(row.get(1).toString()) ){
				String hql = "from "+tableDto+" t where 1=1 and t.wellName ='"+row.get(0).toString()+"' and t.reportDate = to_date('"+DateBean.clearDateN(row.get(1).toString())+"','YYYY-MM-DD')";
				String sql ="";
				String error ="";
				List<Object[]> org = null;
				List newRow = row;
				PcRpdThinWellwbT rpdb = null;
				List<PcRpdThinWellwbT> rpdbList = (List<PcRpdThinWellwbT>) commonDao.searchClassQuery(hql);
				//查询导入数据在数据库中是否存在
				
				if(rpdbList != null && rpdbList.size() >0){
					rpdb = rpdbList.get(0);
				}else{
					rpdb = new PcRpdThinWellwbT();
					//获取组织结构ID
					sql = "select o.org_id,o.structurename from pc_cd_org_t o where 1=1 and o.structurename ='"+row.get(0).toString()+"'";
					org = commonDao.searchEverySql(sql);
					if(org != null && org.size()>0){
						rpdb.setWellName(row.get(0).toString());
						rpdb.setWellid(org.get(0)[0].toString());
						rpdb.setreportDate(DateBean.getStrDate(row.get(1).toString()));
					}else{
						
				    	newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-2, "失败");
				    	newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-1, "井名错误，无该井基础信息！");
				    	returnMap.put("list", newRow);
				    	returnMap.put("exportResult", "1");
				    	return returnMap;
				    	
					}
				}
				
				//数据封装
				rpdb.setPrott(CommonsUtil.getBigDecimalData(row.get(5)));
				rpdb.setStroke(CommonsUtil.getBigDecimalData(row.get(6)));
				rpdb.setAtTimes(CommonsUtil.getBigDecimalData(row.get(7)));
				rpdb.setNozzle(CommonsUtil.getBigDecimalData(row.get(8)));
				rpdb.setPressure(CommonsUtil.getBigDecimalData(row.get(9)));
				rpdb.setTcpr(CommonsUtil.getBigDecimalData(row.get(10)));
				rpdb.setBackpre(CommonsUtil.getBigDecimalData(row.get(11)));
				rpdb.setTot(CommonsUtil.getBigDecimalData(row.get(12)));  //油温℃
				rpdb.setNfv(CommonsUtil.getBigDecimalData(row.get(13)));
				rpdb.setGasp(CommonsUtil.getBigDecimalData(row.get(14))); //日产气量m3
				rpdb.setSampli(CommonsUtil.getClearNullData(row.get(15)));
				rpdb.setPumpingTime(CommonsUtil.getBigDecimalData(row.get(16)));
				rpdb.setPumpingMachine(CommonsUtil.getBigDecimalData(row.get(17)));
				rpdb.setPumpingDescription(CommonsUtil.getClearNullData(row.get(18)));
				rpdb.setRemark(CommonsUtil.getClearNullData(row.get(19)));
				rpdb.setLiquidFlag((byte)0);  //计量标志（0为手动录入，1为采出液提取，2为沿用前一天计量结果，3为采出液提取值异常）

				
				rpdb.setRlastOper(operName);
				rpdb.setRlastOdate(new Date());
				rpdbList.clear();
				rpdbList.add(rpdb);
				boolean exportFlg = true;
				try {
					exportFlg = commonDao.addOrUpdateDatas(rpdbList);
				} catch (Exception e) {
					e.printStackTrace();
					error =  e.getCause().getCause().toString();
					
			    	newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-2, "失败");
			    	newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-1, error);
			    	returnMap.put("list", newRow);
			    	returnMap.put("exportResult", "1");
			    	return returnMap;
				}
				
				
				if(exportFlg){
			    	newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-2, "成功");
			    	newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-1, "");
			    	returnMap.put("list", newRow);
			    	returnMap.put("exportResult", "0");
			    	
				}else{
			    	newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-2, "失败");
			    	newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-1, "数据库操作失败，请检查数据");
			    	returnMap.put("list", newRow);
			    	returnMap.put("exportResult", "1");
				}
				
			}
		}
		return returnMap;
	}

	@Override
	public HashMap<String, Object> insertWaterFoodilExcel(String deptype,List row, String operName, int columsun) throws Exception {
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		String tableDto = "";
		//班组人员查询A表   
		if("15".equals(deptype)){
			tableDto = "PcRpdWaterfloodingWellT";
		
			
			if(row.get(0) != null && !"".equals(row.get(0).toString()) && !"null".equals(row.get(0).toString())&&
					row.get(1) != null && !"".equals(row.get(1).toString()) && !"null".equals(row.get(1).toString()) ){
				String hql = "from "+tableDto+" t where 1=1 and t.wellName ='"+row.get(0).toString()+"' and t.reportDate = to_date('"+DateBean.clearDateN(row.get(1).toString())+"','YYYY-MM-DD')";
				String sql ="";
				String error ="";
				List<Object[]> org = null;
				List newRow = row;
				PcRpdWaterfloodingWellT rpd = null;
				List<PcRpdWaterfloodingWellT> rpdList =null;
				rpdList = (List<PcRpdWaterfloodingWellT>) commonDao.searchClassQuery(hql);
				//查询导入数据在数据库中是否存在
				
				if(rpdList != null && rpdList.size() >0){
					rpd = rpdList.get(0);
				}else{
					rpd = new PcRpdWaterfloodingWellT();
					//获取组织结构ID
					sql = "select o.org_id,o.structurename from pc_cd_org_t o where 1=1 and o.structurename ='"+row.get(0).toString()+"'";
					org = commonDao.searchEverySql(sql);
					if(org != null && org.size()>0){
						//rpd.setOrgId(org.get(0)[0].toString());
						rpd.setWellName(row.get(0).toString());
						//rpd.setWellid(org.get(0)[0].toString());
						rpd.setOrgId(org.get(0)[0].toString());
						//rpd.setreportDate(DateBean.getStrDate(DateBean.clearDateN(row.get(1).toString())));
						rpd.setReportDate(DateBean.getStrDate(DateBean.clearDateN(row.get(1).toString())));
					}else{
						newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-2, "失败");
				    	newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-1, "井名错误，无该井基础信息！");
				    	returnMap.put("list", newRow);
				    	returnMap.put("exportResult", "1");
				    	return returnMap;
				    	
					}
				}
				
				//数据封装
				rpd.setZqzwater(CommonsUtil.getBigDecimalData(row.get(5)));
				rpd.setPqjby(CommonsUtil.getBigDecimalData(row.get(6)));
				rpd.setJkyy(CommonsUtil.getBigDecimalData(row.get(7)));
				rpd.setTy(CommonsUtil.getBigDecimalData(row.get(8)));
				rpd.setPqj(CommonsUtil.getBigDecimalData(row.get(9)));
				rpd.setJk(CommonsUtil.getBigDecimalData(row.get(10)));
				rpd.setPzl(CommonsUtil.getBigDecimalData(row.get(11)));
				rpd.setRzl(CommonsUtil.getBigDecimalData(row.get(12)));  //油温℃
				rpd.setRemark(CommonsUtil.getClearNullData(row.get(13)));
				//rpd.setLiquidFlag((byte)0);  //计量标志（0为手动录入，1为采出液提取，2为沿用前一天计量结果，3为采出液提取值异常）
				rpd.setLiquidFlag((byte)0);  //计量标志（0为手动录入，1为采出液提取，2为沿用前一天计量结果，3为采出液提取值异常）
				
				rpd.setRlastOper(operName);
				rpd.setRlastOdate(new Date());
				rpdList.clear();
				rpdList.add(rpd);
				boolean exportFlg = true;
				try {
					exportFlg = commonDao.addOrUpdateDatas(rpdList);
				} catch (Exception e) {
					e.printStackTrace();
					error =  e.getCause().getCause().toString();
					
					newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-2, "失败");
			    	newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-1, error);
			    	returnMap.put("list", newRow);
			    	returnMap.put("exportResult", "1");
			    	return returnMap;
				}
				
				
				if(exportFlg){
					newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-2, "成功");
					newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-1, "");
			    	returnMap.put("list", newRow);
			    	returnMap.put("exportResult", "0");
			    	
				}else{
					newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-2, "失败");
			    	newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-1, "数据库操作失败，请检查数据");
			    	returnMap.put("list", newRow);
			    	returnMap.put("exportResult", "1");
				}
				
			}
			//地质组人员查询B表   		
		}else{
			tableDto = "PcRpdWaterfloodingWellbT";
			
			if(row.get(0) != null && !"".equals(row.get(0).toString()) && !"null".equals(row.get(0).toString())&&
					row.get(1) != null && !"".equals(row.get(1).toString()) && !"null".equals(row.get(1).toString()) ){
				String hql = "from "+tableDto+" t where 1=1 and t.wellName ='"+row.get(0).toString()+"' and t.reportDate = to_date('"+DateBean.clearDateN(row.get(1).toString())+"','YYYY-MM-DD')";
				String sql ="";
				String error ="";
				List<Object[]> org = null;
				List newRow = row;
				PcRpdWaterfloodingWellbT rpdb = null;
				List<PcRpdWaterfloodingWellbT> rpdbList = (List<PcRpdWaterfloodingWellbT>) commonDao.searchClassQuery(hql);
				//查询导入数据在数据库中是否存在
				
				if(rpdbList != null && rpdbList.size() >0){
					rpdb = rpdbList.get(0);
				}else{
					rpdb = new PcRpdWaterfloodingWellbT();
					//获取组织结构ID
					sql = "select o.org_id,o.structurename from pc_cd_org_t o where 1=1 and o.structurename ='"+row.get(0).toString()+"'";
					org = commonDao.searchEverySql(sql);
					if(org != null && org.size()>0){
						rpdb.setWellName(row.get(0).toString());
						rpdb.setOrgId(org.get(0)[0].toString());
						rpdb.setReportDate(DateBean.getStrDate(row.get(1).toString()));
					}else{
						
				    	newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-2, "失败");
				    	newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-1, "井名错误，无该井基础信息！");
				    	returnMap.put("list", newRow);
				    	returnMap.put("exportResult", "1");
				    	return returnMap;
				    	
					}
				}
				
				//数据封装
				rpdb.setZqzwater(CommonsUtil.getBigDecimalData(row.get(5)));
				rpdb.setPqjby(CommonsUtil.getBigDecimalData(row.get(6)));
				rpdb.setJkyy(CommonsUtil.getBigDecimalData(row.get(7)));
				rpdb.setTy(CommonsUtil.getBigDecimalData(row.get(8)));
				rpdb.setPqj(CommonsUtil.getBigDecimalData(row.get(9)));
				rpdb.setJk(CommonsUtil.getBigDecimalData(row.get(10)));
				rpdb.setPzl(CommonsUtil.getBigDecimalData(row.get(11)));
				rpdb.setRzl(CommonsUtil.getBigDecimalData(row.get(12)));  //油温℃
				rpdb.setRemark(CommonsUtil.getClearNullData(row.get(13)));
				rpdb.setLiquidFlag((byte)0);  //计量标志（0为手动录入，1为采出液提取，2为沿用前一天计量结果，3为采出液提取值异常）

				
				rpdb.setRlastOper(operName);
				rpdb.setRlastOdate(new Date());
				rpdbList.clear();
				rpdbList.add(rpdb);
				boolean exportFlg = true;
				try {
					exportFlg = commonDao.addOrUpdateDatas(rpdbList);
				} catch (Exception e) {
					e.printStackTrace();
					error =  e.getCause().getCause().toString();
					
			    	newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-2, "失败");
			    	newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-1, error);
			    	returnMap.put("list", newRow);
			    	returnMap.put("exportResult", "1");
			    	return returnMap;
				}
				
				
				if(exportFlg){
			    	newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-2, "成功");
			    	newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-1, "");
			    	returnMap.put("list", newRow);
			    	returnMap.put("exportResult", "0");
			    	
				}else{
			    	newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-2, "失败");
			    	newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-1, "数据库操作失败，请检查数据");
			    	returnMap.put("list", newRow);
			    	returnMap.put("exportResult", "1");
				}
				
			}
		}
		return returnMap;
	}

	@Override
	public HashMap<String, Object> insertGasDailyExcel(String deptype,
			List row, String operName, int columsun) throws Exception {

		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		String tableDto = "";
		//班组人员查询A表   
		if("15".equals(deptype)){
			tableDto = "PcRpdGasDailyT";
			
			if(row.get(0) != null && !"".equals(row.get(0).toString()) && !"null".equals(row.get(0).toString())&&
					row.get(1) != null && !"".equals(row.get(1).toString()) && !"null".equals(row.get(1).toString()) ){
				String hql = "from "+tableDto+" t where 1=1 and t.wellName ='"+row.get(0).toString()+"' and t.reportDate = to_date('"+DateBean.clearDateN(row.get(1).toString())+"','YYYY-MM-DD')";
				String sql ="";
				String error ="";
				List<Object[]> org = null;
				List newRow = row;
				PcRpdGasDailyT rpd = null;
				List<PcRpdGasDailyT> rpdList =null;
				rpdList = (List<PcRpdGasDailyT>) commonDao.searchClassQuery(hql);
				//查询导入数据在数据库中是否存在
				
				if(rpdList != null && rpdList.size() >0){
					rpd = rpdList.get(0);
				}else{
					rpd = new PcRpdGasDailyT();
					//获取组织结构ID
					sql = "select o.org_id,o.structurename from pc_cd_org_t o where 1=1 and o.structurename ='"+row.get(0).toString()+"'";
					org = commonDao.searchEverySql(sql);
					if(org != null && org.size()>0){
						//rpd.setOrgId(org.get(0)[0].toString());
						rpd.setWellName(row.get(0).toString());
						//rpd.setWellid(org.get(0)[0].toString());
						rpd.setOrgId(org.get(0)[0].toString());
						//rpd.setreportDate(DateBean.getStrDate(DateBean.clearDateN(row.get(1).toString())));
						rpd.setReportDate(DateBean.getStrDate(DateBean.clearDateN(row.get(1).toString())));
					}else{
						newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-2, "失败");
				    	newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-1, "井名错误，无该井基础信息！");
				    	returnMap.put("list", newRow);
				    	returnMap.put("exportResult", "1");
				    	return returnMap;
				    	
					}
				}
				
				//数据封装
				rpd.setZqzwater(CommonsUtil.getBigDecimalData(row.get(6)));
				rpd.setPqjby(CommonsUtil.getBigDecimalData(row.get(7)));
				rpd.setJkyy(CommonsUtil.getBigDecimalData(row.get(8)));
				rpd.setTy(CommonsUtil.getBigDecimalData(row.get(9)));
				rpd.setPqj(CommonsUtil.getBigDecimalData(row.get(10)));
				rpd.setJk(CommonsUtil.getBigDecimalData(row.get(11)));
				rpd.setPzl(CommonsUtil.getBigDecimalData(row.get(12)));
				rpd.setRzl(CommonsUtil.getBigDecimalData(row.get(13)));  //油温℃
				rpd.setRemark(CommonsUtil.getClearNullData(row.get(14)));
				//rpd.setLiquidFlag((byte)0);  //计量标志（0为手动录入，1为采出液提取，2为沿用前一天计量结果，3为采出液提取值异常）
				rpd.setLiquidFlag((byte)0);  //计量标志（0为手动录入，1为采出液提取，2为沿用前一天计量结果，3为采出液提取值异常）
				
				rpd.setRlastOper(operName);
				rpd.setRlastOdate(new Date());
				rpdList.clear();
				rpdList.add(rpd);
				boolean exportFlg = true;
				try {
					exportFlg = commonDao.addOrUpdateDatas(rpdList);
				} catch (Exception e) {
					e.printStackTrace();
					error =  e.getCause().getCause().toString();
					
					newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-2, "失败");
			    	newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-1, error);
			    	returnMap.put("list", newRow);
			    	returnMap.put("exportResult", "1");
			    	return returnMap;
				}
				
				
				if(exportFlg){
					newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-2, "成功");
					newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-1, "");
			    	returnMap.put("list", newRow);
			    	returnMap.put("exportResult", "0");
			    	
				}else{
					newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-2, "失败");
			    	newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-1, "数据库操作失败，请检查数据");
			    	returnMap.put("list", newRow);
			    	returnMap.put("exportResult", "1");
				}
				
			}
			//地质组人员查询B表   		
		}else{
			tableDto = "PcRpdGasDailybT";
			
			if(row.get(0) != null && !"".equals(row.get(0).toString()) && !"null".equals(row.get(0).toString())&&
					row.get(1) != null && !"".equals(row.get(1).toString()) && !"null".equals(row.get(1).toString()) ){
				String hql = "from "+tableDto+" t where 1=1 and t.wellName ='"+row.get(0).toString()+"' and t.reportDate = to_date('"+DateBean.clearDateN(row.get(1).toString())+"','YYYY-MM-DD')";
				String sql ="";
				String error ="";
				List<Object[]> org = null;
				List newRow = row;
				PcRpdGasDailybT rpdb = null;
				List<PcRpdGasDailybT> rpdbList =null;
				rpdbList = (List<PcRpdGasDailybT>) commonDao.searchClassQuery(hql);
				//查询导入数据在数据库中是否存在
				
				if(rpdbList != null && rpdbList.size() >0){
					rpdb = rpdbList.get(0);
				}else{
					rpdb = new PcRpdGasDailybT();
					//获取组织结构ID
					sql = "select o.org_id,o.structurename from pc_cd_org_t o where 1=1 and o.structurename ='"+row.get(0).toString()+"'";
					org = commonDao.searchEverySql(sql);
					if(org != null && org.size()>0){
						rpdb.setWellName(row.get(0).toString());
						rpdb.setOrgId(org.get(0)[0].toString());
						rpdb.setReportDate(DateBean.getStrDate(row.get(1).toString()));
					}else{
						
				    	newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-2, "失败");
				    	newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-1, "井名错误，无该井基础信息！");
				    	returnMap.put("list", newRow);
				    	returnMap.put("exportResult", "1");
				    	return returnMap;
				    	
					}
				}
				
				//数据封装
				rpdb.setZqzwater(CommonsUtil.getBigDecimalData(row.get(6)));
				rpdb.setPqjby(CommonsUtil.getBigDecimalData(row.get(7)));
				rpdb.setJkyy(CommonsUtil.getBigDecimalData(row.get(8)));
				rpdb.setTy(CommonsUtil.getBigDecimalData(row.get(9)));
				rpdb.setPqj(CommonsUtil.getBigDecimalData(row.get(10)));
				rpdb.setJk(CommonsUtil.getBigDecimalData(row.get(11)));
				rpdb.setPzl(CommonsUtil.getBigDecimalData(row.get(12)));
				rpdb.setRzl(CommonsUtil.getBigDecimalData(row.get(13)));  //油温℃
				rpdb.setRemark(CommonsUtil.getClearNullData(row.get(14)));
				rpdb.setLiquidFlag((byte)0);  //计量标志（0为手动录入，1为采出液提取，2为沿用前一天计量结果，3为采出液提取值异常）

				
				rpdb.setRlastOper(operName);
				rpdb.setRlastOdate(new Date());
				rpdbList.clear();
				rpdbList.add(rpdb);
				boolean exportFlg = true;
				try {
					exportFlg = commonDao.addOrUpdateDatas(rpdbList);
				} catch (Exception e) {
					e.printStackTrace();
					error =  e.getCause().getCause().toString();
					
			    	newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-2, "失败");
			    	newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-1, error);
			    	returnMap.put("list", newRow);
			    	returnMap.put("exportResult", "1");
			    	return returnMap;
				}
				
				
				if(exportFlg){
			    	newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-2, "成功");
			    	newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-1, "");
			    	returnMap.put("list", newRow);
			    	returnMap.put("exportResult", "0");
			    	
				}else{
			    	newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-2, "失败");
			    	newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-1, "数据库操作失败，请检查数据");
			    	returnMap.put("list", newRow);
			    	returnMap.put("exportResult", "1");
				}
				
			}
		}
		return returnMap;
	
	}

	@Override
	public HashMap<String, Object> insertSAGDExcel(String deptype, List row,
			String operName, int columsun) throws Exception {
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		String tableDto = "";
		//班组人员查询A表   
		//if("15".equals(deptype)){
			tableDto = "PcRpdWellSagddT";
			Object wellName =row.get(1); 
			String sqls = "SELECT A.SAGDID  FROM  PC_CD_WELL_SAGD_T A WHERE A.JHMC ='"+wellName+"'";
			List<Object[]> obj = commonDao.searchEverySql(sqls);
			 Object id =  obj.get(0);
			 String wellId = (String) id;//sagd井ID
			if(wellId != null && !"".equals(wellId) && !"null".equals(wellId)&&
					row.get(0) != null && !"".equals(row.get(0).toString()) && !"null".equals(row.get(0).toString()) ){
				String hql = "from "+tableDto+" t where 1=1 and t.sagdid ='"+wellId+"' and t.reportDate = to_date('"+DateBean.clearDateN(row.get(0).toString())+"','YYYY-MM-DD')";
				String sql ="";
				String error ="";
				List<Object[]> org = null;
				List newRow = row;
				PcRpdWellSagddT sagd = null;
				List<PcRpdWellSagddT> sagdList = (List<PcRpdWellSagddT>) commonDao.searchClassQuery(hql);
				//查询导入数据在数据库中是否存在
				
				if(sagdList != null && sagdList.size() >0){
					sagd = sagdList.get(0);
					if(sagd.getVerifier() !=null &&  !"".equals(sagd.getVerifier())){
						newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-2, "失败");
				    	newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-1, "本条数据已经审核，不许导入操作 ");
				    	returnMap.put("list", newRow);
				    	returnMap.put("exportResult", "1");
				    	return returnMap;
					}
				}else{
					sagd = new PcRpdWellSagddT();
					//获取组织结构ID
					sql = "select A.SAGDID,A.JHMC from PC_CD_WELL_SAGD_T A where 1=1 and A.JHMC ='"+row.get(1).toString()+"'";
					org = commonDao.searchEverySql(sql);
					if(org != null && org.size()>0){
						sagd.setReportDate(DateBean.getStrDate(DateBean.clearDateN(row.get(0).toString())));
						sagd.setSagdid(CommonsUtil.getClearNullData(org.get(0)[0]));
					}else{
						newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-2, "失败");
				    	newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-1, "井名错误，无该井基础信息！");
				    	returnMap.put("list", newRow);
				    	returnMap.put("exportResult", "1");
				    	return returnMap;
				    	
					}
				}
				
				//数据封装
				
				sagd.setIInjeTimeRation(CommonsUtil.getBigDecimalDataTrim(row.get(2)));
				sagd.setIProcTimeRation(CommonsUtil.getBigDecimalDataTrim(row.get(3)));
				
				sagd.setIPFlowNipple(CommonsUtil.getBigDecimalDataTrim(row.get(4)));
				sagd.setIPressurePress(CommonsUtil.getBigDecimalDataTrim(row.get(5)));
				sagd.setIPressureTemp(CommonsUtil.getBigDecimalDataTrim(row.get(6)));
				sagd.setIIFlowNipple(CommonsUtil.getBigDecimalDataTrim(row.get(7)));
				sagd.setILoopedPress(CommonsUtil.getBigDecimalDataTrim(row.get(8)));
				sagd.setILoopedTemp(CommonsUtil.getBigDecimalDataTrim(row.get(9)));
				sagd.setIbackPres(CommonsUtil.getBigDecimalDataTrim(row.get(10)));
				sagd.setIDrivepipePress(CommonsUtil.getBigDecimalDataTrim(row.get(11)));
				sagd.setIDailyOutput(CommonsUtil.getBigDecimalDataTrim(row.get(12)));
				sagd.setIDailyOilOutput(CommonsUtil.getBigDecimalDataTrim(row.get(13)));
				sagd.setIWaterRation(CommonsUtil.getBigDecimalDataTrim(row.get(14)));
				sagd.setISteamFlow(CommonsUtil.getBigDecimalDataTrim(row.get(15)));
				sagd.setIDailyCumulativeSteam(CommonsUtil.getBigDecimalDataTrim(row.get(16)));
				//sagd.setIShutinCode(CommonsUtil.getClearNullData(row.get(17)));
				//sagd.setIShutinTime(DateBean.getStrDateNotNull(row.get(18).toString().replaceAll("/", "-")));
				//sagd.setIOpenTime(DateBean.getStrDateNotNull(row.get(19).toString().replaceAll("/", "-")));
				//IStimuCode
				//sagd.setIStimuCode(CommonsUtil.getClearNullData(row.get(20)));
				
				sagd.setIRemark(CommonsUtil.getClearNullData(row.get(17)));
				sagd.setPInjeTimeRation(CommonsUtil.getBigDecimalDataTrim(row.get(18)));
				sagd.setPProcTimeRation(CommonsUtil.getBigDecimalDataTrim(row.get(19)));
				
				
				sagd.setRuntime(CommonsUtil.getBigDecimalDataTrim(row.get(20)));
				sagd.setPumpErrorTime(CommonsUtil.getBigDecimalDataTrim(row.get(21)));
				sagd.setPumpDiameter(CommonsUtil.getBigDecimalDataTrim(row.get(22)));
				sagd.setStroke(CommonsUtil.getBigDecimalDataTrim(row.get(23)));
				sagd.setJigFrequency(CommonsUtil.getBigDecimalDataTrim(row.get(24)));
				sagd.setPPFlowNipple(CommonsUtil.getBigDecimalDataTrim(row.get(25)));
				sagd.setPPressurePress(CommonsUtil.getBigDecimalDataTrim(row.get(26)));
				sagd.setPPressureTemp(CommonsUtil.getBigDecimalDataTrim(row.get(27)));
				sagd.setPIFlowNipple(CommonsUtil.getBigDecimalDataTrim(row.get(28)));
				sagd.setPLoopedPress(CommonsUtil.getBigDecimalDataTrim(row.get(29)));
				sagd.setPLoopedTemp(CommonsUtil.getBigDecimalDataTrim(row.get(30)));
				sagd.setPbackPres(CommonsUtil.getBigDecimalDataTrim(row.get(31)));
				sagd.setPDrivepipePress(CommonsUtil.getBigDecimalDataTrim(row.get(32)));
				sagd.setPDailyOutput(CommonsUtil.getBigDecimalDataTrim(row.get(33)));
				sagd.setPDailyOilOutput(CommonsUtil.getBigDecimalDataTrim(row.get(34)));
				sagd.setPWaterRation(CommonsUtil.getBigDecimalDataTrim(row.get(35)));
				sagd.setPSteamFlow(CommonsUtil.getBigDecimalDataTrim(row.get(36)));
				sagd.setPDailyCumulativeSteam(CommonsUtil.getBigDecimalDataTrim(row.get(37)));
				sagd.setPTemp1(CommonsUtil.getBigDecimalDataTrim(row.get(38)));
				sagd.setPTemp2(CommonsUtil.getBigDecimalDataTrim(row.get(39)));
				sagd.setPTemp3(CommonsUtil.getBigDecimalDataTrim(row.get(40)));
				sagd.setPTemp4(CommonsUtil.getBigDecimalDataTrim(row.get(41)));
				sagd.setPTemp5(CommonsUtil.getBigDecimalDataTrim(row.get(42)));
				sagd.setPTemp6(CommonsUtil.getBigDecimalDataTrim(row.get(43)));
				sagd.setPTemp7(CommonsUtil.getBigDecimalDataTrim(row.get(44)));
				sagd.setPTemp8(CommonsUtil.getBigDecimalDataTrim(row.get(45)));
				sagd.setPTemp9(CommonsUtil.getBigDecimalDataTrim(row.get(46)));
				sagd.setPTemp10(CommonsUtil.getBigDecimalDataTrim(row.get(47)));
				sagd.setPTemp11(CommonsUtil.getBigDecimalDataTrim(row.get(48)));
				sagd.setPTemp12(CommonsUtil.getBigDecimalDataTrim(row.get(49)));
				
				//井压力1-2(MPa)
				//sagd.setPPress1(CommonsUtil.getBigDecimalData(row.get(54)));
				//sagd.setPPress2(CommonsUtil.getBigDecimalData(row.get(55)));
				
				//sagd.setPShutinCode(CommonsUtil.getClearNullData(row.get(53)));
				//sagd.setPShutinTime(DateBean.getStrDateNotNull(row.get(54).toString().replaceAll("/", "-")));
				//sagd.setPOpenTime(DateBean.getStrDateNotNull(row.get(55).toString().replaceAll("/", "-")));
				//sagd.setPStimuCode(CommonsUtil.getClearNullData(row.get(59)));
				sagd.setRemark(CommonsUtil.getClearNullData(row.get(50)));
				
				
				sagd.setRlastOper(operName);
				sagd.setRlastOdate(new Date());
				sagdList.clear();
				sagdList.add(sagd);
				boolean exportFlg = true;
				try {
					exportFlg = commonDao.addOrUpdateDatas(sagdList);
				} catch (Exception e) {
					e.printStackTrace();
					error =  e.getCause().getCause().toString();
					
					newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-2, "失败");
			    	newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-1, error);
			    	returnMap.put("list", newRow);
			    	returnMap.put("exportResult", "1");
			    	return returnMap;
				}
				
				
				if(exportFlg){
					newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-2, "成功");
					newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-1, "");
			    	returnMap.put("list", newRow);
			    	returnMap.put("exportResult", "0");
			    	
				}else{
					newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-2, "失败");
			    	newRow = CommonsUtil.getAllDataInfo(newRow, columsun, columsun-1, "数据库操作失败，请检查数据");
			    	returnMap.put("list", newRow);
			    	returnMap.put("exportResult", "1");
				}
				
			}
		//}
		
		return returnMap;
	
	}
	
	
}
