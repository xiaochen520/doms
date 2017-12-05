package com.echo.service.impl;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.beans.PageControl;
import com.echo.beans.Series;
import com.echo.dao.SubcoolDao;
import com.echo.dto.PcCdOrgT;
import com.echo.dto.PcCdSubcoolCalParamsInfoT;
import com.echo.dto.PcCdSubcoolDefaultParam;
import com.echo.dto.PcCdWellSagdT;
import com.echo.dto.PcRdSubcoolCalResultsT;
import com.echo.dto.PcRdSubcoolModifyNewT;
import com.echo.dto.PcRdSubcoolModifyT;
import com.echo.dto.PcRdSuggestionRulesT;
import com.echo.dto.User;
import com.echo.service.SubcoolService;
import com.echo.util.DateBean;
import com.echo.util.DynamicDataExportUtil;

/**
 * 
 * @Company: Etrol
 * @ClassName: SubcoolServiceImpl
 * @author LIJUN
 * @date 2015-8-20
 */
public class SubcoolServiceImpl implements SubcoolService {

	private SubcoolDao subcoolDao;

	public void setSubcoolDao(SubcoolDao subcoolDao) {
		this.subcoolDao = subcoolDao;
	}

	@Override
	public boolean setSubcoolDefaultParam(PcCdSubcoolDefaultParam subcoolParam) {
		boolean result = false;
		try {
			result = subcoolDao.addOrUpdateSubcoolDefaultParam(subcoolParam);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public JSONObject getSubcoolDefaultParam(String key) {
		JSONObject jsobj = null;
		String sql = "select * from PC_CD_SUBCOOLDEFAULTPARAMSINFO where systemname = '"
				+ key + "'";
		Object[] entry = subcoolDao.getSubcoolDefaultParam(sql);
		if (entry != null) {
			jsobj = new JSONObject();
			jsobj.put("cal", entry[2]);
			jsobj.put("max_subcool", entry[4]);
			jsobj.put("min_subcool", entry[5]);
			jsobj.put("flow", entry[6]);
		}

		return jsobj;
	}

	@Override
	public boolean removeSubcoolAlarmParam(String calid) {
		PcCdSubcoolCalParamsInfoT alarmParam = subcoolDao
				.getCalParamsById(calid);
		return subcoolDao.removeSubcoolCalParams(alarmParam);
	}

	@Override
	public HashMap<String, Object> searchSubcoolAlarmParam(String area,
			String block, String jh, int pageNo, String sort, String order,
			int rowsPerpage) {
		JSONArray jsonArr = null;
		JSONObject tjo = null;
		HashMap<String, Object> map = new HashMap<String, Object>();

		String hql = "select ";
		String formTable = " from pc_cd_subcoolcalparamsinfo_v a where 1=1 ";
		String totalNum = "select count(*)";
		int total = 0;
		if (!area.equals("") && area != null && !area.equals("undefined")
				&& !"全部".equals(area)) {
			formTable = formTable + " and AREABLOCK='" + area + "'";
		}
		if (!block.equals("") && block != null && !block.equals("undefined")) {
			formTable = formTable + " and BLOCKSTATIONNAME='" + block + "'";
		}
		if (!jh.equals("") && jh != null && !jh.equals("undefined")) {
			formTable = formTable + " and jhmc like '%" + jh + "%'";
		}

		String[] cloumnsName = { "CALID", "SAGDID", "ORG_ID", "JHMC", "JHMC_S",
				"OILSTATIONNAME", "AREABLOCK", "BLOCKSTATIONNAME",
				"PRODSTAGES", "calculate_methods", "calculate_methodsid",
				"calculate_rate", "max_subcool", "min_subcool", "flow_methods",
				"flowid" };
		hql += "CALID";
		for (int m = 1; m < cloumnsName.length; m++) {
			hql = hql + ", " + cloumnsName[m];
		}

		hql += formTable;
		totalNum += formTable;
		total = subcoolDao.getCounts(totalNum);

		// 排序
		if (!"".equals(sort) && !"JHMC".equals(sort)) {
			for (String cloumn : cloumnsName) {
				if (cloumn.equals(sort)) {
					hql += " order by " + cloumn + " " + order;
					break;
				}
			}
		} else {
			hql += " order by nlssort(JHMC, 'NLS_SORT=SCHINESE_STROKE_M') ";
		}

		// 计算分页
		PageControl control = new PageControl();
		// 当前页
		control.setInt_num(rowsPerpage);
		control.init(pageNo, total);
		// 开始条数
		int start = control.getStart() - 1;
		List<Object[]> lo = subcoolDao.searchSubcoolAlarmParams(hql, start,
				rowsPerpage);

		if (lo != null && 0 < lo.size()) {
			jsonArr = new JSONArray();
			for (Object[] o : lo) {
				tjo = new JSONObject();
				for (int i = 0; i < o.length; i++) {
					if (o[i] == null || o[i].equals("null")) {
						o[i] = "";
					} else if (i == 14) {
						int flow = Integer.parseInt(o[i].toString());
						if (flow == 1) {
							tjo.put(cloumnsName[i], "流量计1");
						} else if (flow == 2) {
							tjo.put(cloumnsName[i], "流量计2");
						} else {
							tjo.put(cloumnsName[i], "取和");
						}
					} else {
						tjo.put(cloumnsName[i], o[i].toString());
					}
				}
				jsonArr.add(tjo);
			}
		}

		tjo = new JSONObject();
		tjo.put("Rows", jsonArr);
		tjo.put("Total", total);
		map.put("list", lo);
		map.put("json", tjo);
		return map;
	}

	@Override
	public boolean setSubcoolAlarmParam(PcCdSubcoolCalParamsInfoT alarmParam) {
		return subcoolDao.addSubcoolCalParams(alarmParam);
	}

	@Override
	public int addSubcoolAlarmParam(String area, String block, String jh,
			int cal, BigDecimal min, BigDecimal max, int flow) {
		int count = 0;
		String cloums = "select ";
		String formTable = " from pc_cd_well_sagd_v a where 1=1 ";

		if (!area.equals("") && area != null && !area.equals("undefined")
				&& !"全部".equals(area)) {
			formTable = formTable + " and qkmc='" + area + "'";
		}
		if (!block.equals("") && block != null && !block.equals("undefined")) {
			formTable = formTable + " and BLOCKSTATIONNAME='" + block + "'";
		}
		if (!jh.equals("") && jh != null && !jh.equals("undefined")) {
			formTable = formTable + " and jhmc like '%" + jh + "%'";
		}

		String kk = "SAGDID, ORG_ID, JHMC";
		String hql = cloums + kk + formTable;
		List<Object[]> objs = subcoolDao.queryInfo(hql);

		for (int i = 0; i < objs.size(); i++) {
			Object[] obj = objs.get(i);
			String sagdId = (String) obj[0];
			PcCdSubcoolCalParamsInfoT temp = subcoolDao
					.findCalParsmsBySagd(sagdId);
			if ("".equals(temp) || temp == null) {
				PcCdSubcoolCalParamsInfoT calParams = new PcCdSubcoolCalParamsInfoT();
				calParams.setSagdId(sagdId);
				calParams.setCalculateMethodsid(cal);
				switch (cal) {
				case 1:
					calParams.setCalculateMethods("I井套压 ");
					break;
				case 2:
					calParams.setCalculateMethods("(I井注汽压力+I井套压)/2");
					break;
				case 3:
					calParams.setCalculateMethods("I井注汽压力 - 1 ");
					break;
				default:
					break;
				}
				calParams.setCalculateRate(20);
				calParams.setMinSubcool(min);
				calParams.setMaxSubcool(max);
				calParams.setFlowMethods(flow);
				boolean result = subcoolDao.addSubcoolCalParams(calParams);
				if (result)
					count++;
			}
		}
		return count;
	}

	@Override
	public boolean modifySubcoolAlarmParam(String calId, int cal,
			BigDecimal min, BigDecimal max, int flow) {
		if (!calId.equals("") && calId != null && !calId.equals("undefined")) {
			PcCdSubcoolCalParamsInfoT calParams = subcoolDao
					.getCalParamsById(calId);
			switch (cal) {
			case 1:
				calParams.setCalculateMethods("I井套压 ");
				break;
			case 2:
				calParams.setCalculateMethods("(I井注汽压力+I井套压)/2");
				break;
			case 3:
				calParams.setCalculateMethods("I井注汽压力 - 1 ");
				break;
			default:
				break;
			}
			calParams.setCalculateMethodsid(cal);
			calParams.setCalculateRate(20);
			calParams.setMinSubcool(min);
			calParams.setMaxSubcool(max);
			calParams.setFlowMethods(flow);

			return subcoolDao.updataSubcoolCalParams(calParams);
		}
		return false;
	}

	@Override
	public PcRdSubcoolCalResultsT getSubcoolCalResultById(String calrdId) {
		return subcoolDao.getCalResultsById(calrdId);
	}

	@Override
	public HashMap<String, Object> searchSubcoolCalResults(String area,
			String block, String jh, String filter, String startTime,
			String endTime, int pageNo, String sort, String order,
			int rowsPerpage) {
		JSONArray jsonArr = null;
		JSONObject tjo = null;
		HashMap<String, Object> map = new HashMap<String, Object>();

		String hql = "select ";
		String formTable = " from pc_rd_subcoolcalresults_v a where 1=1 ";
		String totalNum = "select count(*)";
		int total = 0;
		if (!area.equals("") && area != null && !area.equals("undefined")
				&& !"全部".equals(area)) {
			formTable = formTable + " and AREABLOCK='" + area + "'";
		}
		if (!block.equals("") && block != null && !block.equals("undefined")) {
			formTable = formTable + " and BLOCKSTATIONNAME='" + block + "'";
		}
		if (!jh.equals("") && jh != null && !jh.equals("undefined")) {
			formTable = formTable + " and jhmc like '%" + jh + "%'";
		}
		if ("true".equals(filter)) {
			formTable = formTable
					+ " and (CURSUBCOOL < MINSUBCOOL or CURSUBCOOL > MAXSUBCOOL)";
		}
		Date today = new Date();
		String nowdate = DateBean.getTimeStr(today);
		if ("".equals(startTime)) {
			startTime = nowdate.substring(0, 10) + " 00:00:00";
		} else {
			startTime = startTime + " 00:00:00";
		}
		if ("".equals(endTime)) {
			endTime = nowdate.substring(0, 10) + " 23:59:59";
		} else {
			endTime = endTime + " 23:59:59";
		}
		formTable = formTable + " and CALTIME between TO_DATE('" + startTime
				+ "','YYYY-MM-DD HH24:MI:SS') and TO_DATE('" + endTime
				+ "','YYYY-MM-DD HH24:MI:SS')";

		String[] cloumnsName = { "RDID", "SAGDID", "ORG_ID", "JHMC", "JHMC_S",
				"OILSTATIONNAME", "AREABLOCK", "BLOCKSTATIONNAME",
				"PRODSTAGES", "CALTIME", "CALMETHOD", "CALMETHODID",
				"OPERPRESS", "CURSUBCOOL", "MAXSUBCOOL", "MINSUBCOOL",
				"CURFLOW", "CURJIG", "CURNIP", "SUGFLOW", "SUGJIG", "SUGNIP",
				"MODIFYFLOW", "MODIFYJIG", "MODIFYNIP", "MODIFYTIME", "MODIFYUSER" };
		hql += "RDID";
		for (int m = 1; m < cloumnsName.length; m++) {
			if ("CALTIME".equals(cloumnsName[m])) {
				hql = hql + ","
						+ "to_char(CALTIME,'YYYY-MM-DD hh24:mi:ss') as CALTIME";
			} else if ("MODIFYTIME".equals(cloumnsName[m])) {
				hql = hql + ","
				+ "to_char(MODIFYTIME,'YYYY-MM-DD hh24:mi:ss') as MODIFYTIME";
			} else {
				hql = hql + ", " + cloumnsName[m];
			}
		}

		hql += formTable;
		totalNum += formTable;
		total = subcoolDao.getCounts(totalNum);

		// 排序
		if (!"".equals(sort) && !"JHMC".equals(sort)) {
			for (String cloumn : cloumnsName) {
				if (cloumn.equals(sort)) {
					hql += " order by " + cloumn + " " + order;
					break;
				}
			}
		} else {
			hql += " order by JHMC, CALTIME desc ";
		}

		// 计算分页
		PageControl control = new PageControl();
		// 当前页
		control.setInt_num(rowsPerpage);
		control.init(pageNo, total);
		// 开始条数
		int start = control.getStart() - 1;
		List<Object[]> lo = subcoolDao.searchSubcoolCalResults(hql, start,
				rowsPerpage, cloumnsName);

		if (lo != null && 0 < lo.size()) {
			jsonArr = new JSONArray();
			for (Object[] o : lo) {
				tjo = new JSONObject();
				for (int i = 0; i < o.length; i++) {
					if (o[i] == null || o[i].equals("null")) {
						o[i] = "";
					}
					tjo.put(cloumnsName[i], o[i].toString());
				}
				jsonArr.add(tjo);
			}
		}

		tjo = new JSONObject();
		tjo.put("Rows", jsonArr);
		tjo.put("Total", total);
		map.put("list", lo);
		map.put("json", tjo);
		return map;
	}
	


	@Override
	public JSONObject searchSubcoolNew(String area, String block, String jh) {
		JSONArray jsonArr = null;
		JSONObject tjo = null;
		HashMap<String, Object> map = new HashMap<String, Object>();

		String hql = "select ";
		String formTable = " from pc_rd_subcoolnew_v a where 1=1 ";

		if (!area.equals("") && area != null && !area.equals("undefined")
				&& !"全部".equals(area)) {
			formTable = formTable + " and AREABLOCK='" + area + "'";
		}
		if (!block.equals("") && block != null && !block.equals("undefined")) {
			formTable = formTable + " and BLOCKSTATIONNAME='" + block + "'";
		}
		if (!jh.equals("") && jh != null && !jh.equals("undefined")) {
			formTable = formTable + " and jhmc like '%" + jh + "%'";
		}

		String[] cloumnsName = { "SAGDID", "ORG_ID", "JHMC", "JHMC_S",
				"OILSTATIONNAME", "AREABLOCK", "BLOCKSTATIONNAME",
				"PRODSTAGES", "CALTIME", "CALMETHOD", "CALMETHODID",
				"OPERPRESS", "CURSUBCOOL", "MAXSUBCOOL", "MINSUBCOOL",
				"CURFLOW", "CURJIG", "CURNIP", "SUGFLOW", "SUGJIG", "SUGNIP",
				"MODIFYFLOW", "MODIFYJIG", "MODIFYNIP", "MODIFYTIME", 
				"MODIFYUSER", "SUGNIP", "LASTTIME", "MODIFYMODE", "CALID" };
		hql += "SAGDID";
		for (int m = 1; m < cloumnsName.length; m++) {
			if ("CALTIME".equals(cloumnsName[m])) {
				hql = hql + ","
						+ "to_char(CALTIME,'YYYY-MM-DD hh24:mi:ss') as CALTIME";
			} else if ("MODIFYTIME".equals(cloumnsName[m])) {
				hql = hql + ","
				+ "to_char(MODIFYTIME,'YYYY-MM-DD hh24:mi:ss') as MODIFYTIME";
			} else if ("LASTTIME".equals(cloumnsName[m])) {
				hql = hql + ","
				+ "to_char(LASTTIME,'YYYY-MM-DD hh24:mi:ss') as LASTTIME";
			} else {
				hql = hql + ", " + cloumnsName[m];
			}
		}

		hql += formTable;

		hql += " order by BLOCKSTATIONNAME, JHMC, CALTIME desc ";

		List<Object[]> lo = subcoolDao.queryInfo(hql);

		if (lo != null && 0 < lo.size()) {
			jsonArr = new JSONArray();
			for (Object[] o : lo) {
				tjo = new JSONObject();
				for (int i = 0; i < o.length; i++) {
					if (o[i] == null || o[i].equals("null")) {
						o[i] = "";
					}
					if(i==28){
						if("1".equals(o[28].toString())){
							tjo.put(cloumnsName[i], "采纳建议参数");
						}else if("2".equals(o[28].toString())){
							tjo.put(cloumnsName[i], "人工调整参数 ");
						}else{
							tjo.put(cloumnsName[i], o[i].toString());
						}
					} else {
						tjo.put(cloumnsName[i], o[i].toString());
					}
				}
				jsonArr.add(tjo);
			}
		}
		tjo = new JSONObject();
		tjo.put("Rows", jsonArr);
		return tjo;
	}

	@Override
	public JSONObject getSubcoolCalResult(String sagdid) {
		String[] cloumnsName = { "SAGDID", "ORG_ID", "SAGD_RDID",
				"JHMC", "JHMC_S", "OILSTATIONNAME", "AREABLOCK",
				"BLOCKSTATIONNAME", "PRODSTAGES", "CALTIME", "CALMETHOD",
				"CALMETHODID", "CURSUBCOOL", "MAXSUBCOOL", "MINSUBCOOL",
				"CURFLOW", "CURJIG", "CURNIP", "SUGFLOW", "SUGJIG", "SUGNIP",
				"P_TEMP1", "P_TEMP2", "P_TEMP3", "P_TEMP4", "P_TEMP5",
				"P_TEMP6", "P_TEMP7", "P_TEMP8", "P_TEMP9", "P_TEMP10",
				"P_TEMP11", "P_TEMP12", "I_DRIVEPIPE_PRESS", "I_PRESSURE_PRESS" };
		String hql = "select ";
		String formTable = " from pc_rd_subcoolall_v t where 1 = 1 and sagdid = '"
				+ sagdid + "'";

		hql += "SAGDID";
		for (int m = 1; m < cloumnsName.length; m++) {
			if ("CALTIME".equals(cloumnsName[m])) {
				hql = hql + ","
						+ "to_char(CALTIME,'YYYY-MM-DD hh24:mi:ss') as CALTIME";
			} else {
				hql = hql + ", " + cloumnsName[m];
			}
		}

		hql = hql + formTable;
		Object[] o = subcoolDao.getSubcoolCalResult(hql, cloumnsName);
		JSONObject tjo = new JSONObject();
		for (int i = 0; i < o.length; i++) {
			if (o[i] == null || o[i].equals("null")) {
				tjo.put(cloumnsName[i], "");
			} else {
				tjo.put(cloumnsName[i], o[i].toString());
			}
		}

		String time = tjo.getString("CALTIME").substring(0, 10);
		hql = "select (nvl(P_DAILY_CUMULATIVE_STEAM,0) + nvl(I_DAILY_CUMULATIVE_STEAM,0)) as DAILY_CUMULATIVE_STEAM, P_DAILY_OUTPUT from PC_RPD_WELL_SAGDD_T where SAGDID = '"
				+ sagdid + "' and REPORT_DATE = TO_DATE('" + time + "','YYYY-MM-DD')";
		List<Object[]> li = subcoolDao.queryInfo(hql);
		
		if(li.size() > 0) {
			Object[] obj = li.get(0);
			if("".equals(obj[0]) || obj[0] == null) {
				tjo.put("DAILY_CUMULATIVE_STEAM", "" );
			} else {
				tjo.put("DAILY_CUMULATIVE_STEAM", obj[0].toString());
			}
			
			if("".equals(obj[1]) || obj[1] == null) {
				tjo.put("P_DAILY_OUTPUT", "");
			} else {
				tjo.put("P_DAILY_OUTPUT", obj[1].toString());
			}
		} else {
			tjo.put("DAILY_CUMULATIVE_STEAM", "");
			tjo.put("P_DAILY_OUTPUT", "");
		}
		
		return tjo;
	}

	@Override
	public boolean modifySubcoolSuggestParam(String rdid, BigDecimal modifyflow,
			BigDecimal modifyjig, BigDecimal modifynip, int modifymode, User user) {
		boolean result = false;
		if (!rdid.equals("") && rdid != null && !rdid.equals("undefined")) {
			PcRdSubcoolCalResultsT cal = subcoolDao.getCalResultsById(rdid);
			
			PcRdSuggestionRulesT suggestion = new PcRdSuggestionRulesT();
			suggestion.setSagdId(cal.getSagdId());
			suggestion.setRuleTime(new Date());
			suggestion.setCurrentSubcool(cal.getCurrentSubcool());
			suggestion.setOffsetSubcool(cal.getOffsetSubcool());
			suggestion.setOffsetFlow(modifyflow.subtract(cal.getCurrentFlow()));
			suggestion.setOffsetJigFrequency(modifyjig.subtract(cal.getCurrentJigFrequency()));
			suggestion.setOffsetFlowNipple(modifynip.subtract(cal.getCurrentFlowNipple()));
			suggestion.setReserve1(new BigDecimal(0));
			suggestion.setReserve2(new BigDecimal(0));
			suggestion.setReserve3(new BigDecimal(0));
			result = subcoolDao.addSuggestionrules(suggestion);
			
			PcRdSubcoolModifyNewT modifyNew = subcoolDao.getSubcoolModifyNew(cal.getSagdId());
			Date modifyDate = new Date();
			modifyNew.setModifyTime(modifyDate);
			modifyNew.setModifyUser(user.getOperName());
			modifyNew.setModifyFlow(modifyflow);
			modifyNew.setModifyJigFrequency(modifyjig);
			modifyNew.setModifyFlowNipple(modifynip);
			modifyNew.setCalTime(cal.getCalculateTime());
			modifyNew.setModifyMode(modifymode);
			modifyNew.setSagdRdId(cal.getSagdrdId());
			result = subcoolDao.updateSubcoolModifyNew(modifyNew);
			
			PcRdSubcoolModifyT modify = new PcRdSubcoolModifyT();
			modify.setCalRdId(cal.getCalrdId());
			modify.setModifyTime(modifyDate);
			modify.setModifyUser(user.getOperName());
			modify.setModifyFlow(modifyflow);
			modify.setModifyJigFrequency(modifyjig);
			modify.setModifyFlowNipple(modifynip);
			modify.setModifyMode(modifymode);
			result = subcoolDao.addSubcoolModify(modify);
			
			return result;
		}
		return result;
	}

	@Override
	public JSONObject searchSubcoolCalResults(String area, String block,
			String jh, String startTime, String endTime) {
		String start = "";
		String end = "";
		JSONObject tjo = new JSONObject();
		List<Long> timeList = new ArrayList<Long>();
		List<BigDecimal> pressList = new ArrayList<BigDecimal>();
		List<BigDecimal> subcoolList = new ArrayList<BigDecimal>();
		List<BigDecimal> jigList = new ArrayList<BigDecimal>();
		List<BigDecimal> nipList = new ArrayList<BigDecimal>();
		List<Long> dayList = new ArrayList<Long>();
		List<BigDecimal> steamList = new ArrayList<BigDecimal>();
		List<BigDecimal> outputList = new ArrayList<BigDecimal>();

		String hql = "select ";
		String formTable = " from pc_rd_subcoolcalresults_v a where 1=1 ";
		if (!area.equals("") && area != null && !area.equals("undefined")
				&& !"全部".equals(area)) {
			formTable = formTable + " and AREABLOCK='" + area + "'";
		}
		if (!block.equals("") && block != null && !block.equals("undefined")) {
			formTable = formTable + " and BLOCKSTATIONNAME='" + block + "'";
		}
		if (!jh.equals("") && jh != null && !jh.equals("undefined")) {
			formTable = formTable + " and jhmc like '%" + jh + "%'";
		}
		
		Date today = new Date();
		String nowdate = DateBean.getTimeStr(today);
		if ("".equals(startTime)) {
			start = nowdate.substring(0, 10) + " 00:00:00";
		} else {
			start = startTime + " 00:00:00";
		}
		if ("".equals(endTime)) {
			end = nowdate.substring(0, 10) + " 23:59:59";
		} else {
			end = endTime + " 23:59:59";
		}
		formTable = formTable + " and CALTIME between TO_DATE('" + start
				+ "','YYYY-MM-DD HH24:MI:SS') and TO_DATE('" + end
				+ "','YYYY-MM-DD HH24:MI:SS') order by CALTIME desc ";

		String[] cloumnsName = {"CALTIME", "OPERPRESS", "CURSUBCOOL", "CURJIG", "CURNIP"};
		hql += "to_char(CALTIME,'YYYY-MM-DD hh24:mi:ss') as CALTIME";
		for (int m = 1; m < cloumnsName.length; m++) {
			hql = hql + ", " + cloumnsName[m];
		}

		hql += formTable;
		
		List<Object[]> lo = subcoolDao.queryInfo(hql);
		
		if (lo != null && 0 < lo.size()) {
			for (int i = 0; i < lo.size(); i++) {
				Object[] o = lo.get(i);
				
				String times = o[0].toString();				
				SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date;
				long time = 0;
					try {
						date = format.parse(times);
						time =  date.getTime();						
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}				
				timeList.add(time);
				pressList.add((BigDecimal) o[1]);
				subcoolList.add((BigDecimal) o[2]);
				jigList.add((BigDecimal) o[3]);
				nipList.add((BigDecimal) o[4]);
			}
		}
		
		hql = "select REPORT_DATE, (P_DAILY_CUMULATIVE_STEAM + I_DAILY_CUMULATIVE_STEAM) as DAILY_CUMULATIVE_STEAM, P_DAILY_OUTPUT from PC_RPD_WELL_SAGDD_V where 1 = 1";
		if (!area.equals("") && area != null && !area.equals("undefined")
				&& !"全部".equals(area)) {
			hql = hql + " and QKMC='" + area + "'";
		}
		if (!block.equals("") && block != null && !block.equals("undefined")) {
			hql = hql + " and BLOCKSTATIONNAME='" + block + "'";
		}
		if (!jh.equals("") && jh != null && !jh.equals("undefined")) {
			hql = hql + " and jhmc like '%" + jh + "%'";
		}

		hql = hql + " and REPORT_DATE between TO_DATE('" + startTime
				+ "','YYYY-MM-DD') and TO_DATE('" + endTime
				+ "','YYYY-MM-DD') order by REPORT_DATE desc ";
		
		lo = subcoolDao.queryInfo(hql);
		if (lo != null && 0 < lo.size()) {
			for (int i = 0; i < lo.size(); i++) {
				Object[] o = lo.get(i);
				
				String times = o[0].toString();				
				SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd");
				Date date;
				long day = 0;
					try {
						date = format.parse(times);
						day =  date.getTime();						
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}				
				
				
				dayList.add(day);
				steamList.add((BigDecimal) o[1]);
				outputList.add((BigDecimal) o[2]);
			}
		}
		
		tjo.put("time", timeList);
		tjo.put("press", pressList);
		tjo.put("subcool", subcoolList);
		tjo.put("jig", jigList);
		tjo.put("nip", nipList);
		tjo.put("day", dayList);
		tjo.put("steam", steamList);
		tjo.put("output", outputList);
		return tjo;
	}

	@Override
	public List<Object[]> exportSuggest(String sagds) {
		List<Object[]> dataLsit = new ArrayList<Object[]>();
		String[] sagd = sagds.split(",");
		String hql = "select ";
		String[] cloumnsName = { "OILSTATIONNAME", "JHMC", "CURNIP", "MODIFYNIP", "CURJIG", "MODIFYJIG", "CURFLOW", "MODIFYFLOW" };
		hql += "OILSTATIONNAME";
		for (int m = 1; m < cloumnsName.length; m++) {
			hql = hql + ", " + cloumnsName[m];
		}
		hql = hql + " from pc_rd_subcoolnew_v where sagdid = '";
		for (int i = 0; i < sagd.length; i++) {
			List<Object[]> list = subcoolDao.queryInfo(hql + sagd[i] + "'");
			if(list.size() > 0) {
				dataLsit.add(list.get(0));
			}
		}
		
		return dataLsit;
	}

	@Override
	public List<Object[]> exportAlarmInfo(String area, String block, String jh,
			String startTime, String endTime) {
		String hql = "select ";
		String formTable = " from pc_rd_subcoolcalresults_v a where 1=1 ";
		String totalNum = "select count(*)";
		int total = 0;
		if (!area.equals("") && area != null && !area.equals("undefined")
				&& !"全部".equals(area)) {
			formTable = formTable + " and AREABLOCK='" + area + "'";
		}
		if (!block.equals("") && block != null && !block.equals("undefined")) {
			formTable = formTable + " and BLOCKSTATIONNAME='" + block + "'";
		}
		if (!jh.equals("") && jh != null && !jh.equals("undefined")) {
			formTable = formTable + " and jhmc like '%" + jh + "%'";
		}

		Date today = new Date();
		String nowdate = DateBean.getTimeStr(today);
		if ("".equals(startTime)) {
			startTime = nowdate.substring(0, 10) + " 00:00:00";
		} else {
			startTime = startTime + " 00:00:00";
		}
		if ("".equals(endTime)) {
			endTime = nowdate.substring(0, 10) + " 23:59:59";
		} else {
			endTime = endTime + " 23:59:59";
		}
		formTable = formTable + " and CALTIME between TO_DATE('" + startTime
				+ "','YYYY-MM-DD HH24:MI:SS') and TO_DATE('" + endTime
				+ "','YYYY-MM-DD HH24:MI:SS')";

		String[] cloumnsName = { "JHMC", "CALTIME", "AREABLOCK", "BLOCKSTATIONNAME",
				"CALMETHOD", "OPERPRESS", "CURSUBCOOL", "MINSUBCOOL", "MAXSUBCOOL",
				"CURFLOW", "CURJIG", "CURNIP", "SUGFLOW", "SUGJIG", "SUGNIP",
				"MODIFYFLOW", "MODIFYJIG", "MODIFYNIP", "MODIFYTIME", "MODIFYUSER" };
		hql += "JHMC";
		for (int m = 1; m < cloumnsName.length; m++) {
			if ("CALTIME".equals(cloumnsName[m])) {
				hql = hql + ","
						+ "to_char(CALTIME,'YYYY-MM-DD hh24:mi:ss') as CALTIME";
			} else if ("MODIFYTIME".equals(cloumnsName[m])) {
				hql = hql + ","
				+ "to_char(MODIFYTIME,'YYYY-MM-DD hh24:mi:ss') as MODIFYTIME";
			} else {
				hql = hql + ", " + cloumnsName[m];
			}
		}

		hql += formTable;
		hql += " order by JHMC, CALTIME desc ";
		List<Object[]> dataLsit = subcoolDao.queryInfo(hql);
		return dataLsit;
	}

	@Override
	public int getExportTotal(String area, String block, String jh,
			String startTime, String endTime) {
		String hql = "select ";
		String formTable = " from pc_rd_subcoolcalresults_v a where 1=1 ";
		String totalNum = "select count(*)";
		int total = 0;
		if (!area.equals("") && area != null && !area.equals("undefined")
				&& !"全部".equals(area)) {
			formTable = formTable + " and AREABLOCK='" + area + "'";
		}
		if (!block.equals("") && block != null && !block.equals("undefined")) {
			formTable = formTable + " and BLOCKSTATIONNAME='" + block + "'";
		}
		if (!jh.equals("") && jh != null && !jh.equals("undefined")) {
			formTable = formTable + " and jhmc like '%" + jh + "%'";
		}

		Date today = new Date();
		String nowdate = DateBean.getTimeStr(today);
		if ("".equals(startTime)) {
			startTime = nowdate.substring(0, 10) + " 00:00:00";
		} else {
			startTime = startTime + " 00:00:00";
		}
		if ("".equals(endTime)) {
			endTime = nowdate.substring(0, 10) + " 23:59:59";
		} else {
			endTime = endTime + " 23:59:59";
		}
		formTable = formTable + " and CALTIME between TO_DATE('" + startTime
				+ "','YYYY-MM-DD HH24:MI:SS') and TO_DATE('" + endTime
				+ "','YYYY-MM-DD HH24:MI:SS')";

		String[] cloumnsName = { "RDID", "SAGDID", "ORG_ID", "JHMC", "JHMC_S",
				"OILSTATIONNAME", "AREABLOCK", "BLOCKSTATIONNAME",
				"PRODSTAGES", "CALTIME", "CALMETHOD", "CALMETHODID",
				"OPERPRESS", "CURSUBCOOL", "MAXSUBCOOL", "MINSUBCOOL",
				"CURFLOW", "CURJIG", "CURNIP", "SUGFLOW", "SUGJIG", "SUGNIP",
				"MODIFYFLOW", "MODIFYJIG", "MODIFYNIP", "MODIFYTIME", "MODIFYUSER" };
		hql += "RDID";
		for (int m = 1; m < cloumnsName.length; m++) {
			if ("CALTIME".equals(cloumnsName[m])) {
				hql = hql + ","
						+ "to_char(CALTIME,'YYYY-MM-DD hh24:mi:ss') as CALTIME";
			} else if ("MODIFYTIME".equals(cloumnsName[m])) {
				hql = hql + ","
				+ "to_char(MODIFYTIME,'YYYY-MM-DD hh24:mi:ss') as MODIFYTIME";
			} else {
				hql = hql + ", " + cloumnsName[m];
			}
		}

		hql += formTable;
		totalNum += formTable;
		total = subcoolDao.getCounts(totalNum);
		return total;
	}

}