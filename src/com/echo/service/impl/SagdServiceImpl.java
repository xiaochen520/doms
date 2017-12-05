package com.echo.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.beans.PageControl;
import com.echo.dao.SagdDao;
import com.echo.dto.PcCdServerNodeT;
import com.echo.dto.PcCdWellSagdT;
import com.echo.dto.PcRpdWellSagddT;
import com.echo.service.SagdService;
import com.echo.util.CommonsUtil;
import com.echo.util.DateBean;

public class SagdServiceImpl implements SagdService {
	private SagdDao sagdDao;

	public void setSagdDao(SagdDao sagdDao) {
		this.sagdDao = sagdDao;
	}

	public HashMap<String, Object> searchSagdWell(String dyear, String jrbz,
			String prodStages, String statusValue, String totalNumFlag,
			String qk, String zh, String oilNumber, String gh, String name,
			int pageNo, String sort, String order, int rowsPerpage) {
		JSONArray jsonArr = null;
		JSONObject tjo = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		String cloums = "select   ";
		String formTable = " from pc_cd_well_sagd_v a where 1=1 ";
		String totalNum = "select count(*) ";
		if (!qk.equals("") && qk != null && !qk.equals("undefined")) {
			formTable = formTable + " and OILSTATIONNAME='" + qk + "'";
		}
		if (!zh.equals("") && zh != null && !zh.equals("undefined")
				&& !"全部".equals(zh)) {
			formTable = formTable + " and qkmc='" + zh + "'";
		}
		if (!oilNumber.equals("") && oilNumber != null
				&& !oilNumber.equals("undefined") && !oilNumber.equals("全部")) {
			formTable = formTable + " and OILSTATIONNAME='" + oilNumber + "'";
		}
		if (!jrbz.equals("") && jrbz != null && !"全部".equals(jrbz)) {
			formTable = formTable + " and jrbz='" + jrbz + "'";
		}
		if (!dyear.equals("") && dyear != null && !dyear.equals("undefined")) {
			formTable = formTable + " and dyear='" + dyear + "'";
		}
		if (!prodStages.equals("") && prodStages != null
				&& !prodStages.equals("undefined")) {
			formTable = formTable + " and prod_stages='" + prodStages + "'";
		}
		if (!gh.equals("") && gh != null && !gh.equals("undefined")) {
			formTable = formTable + " and MANIFOLD='" + gh + "'";
		}
		if (!name.equals("") && name != null && !name.equals("undefined")) {
			formTable = formTable + " and jhmc like '%" + name + "%'";
		}
		if (!statusValue.equals("") && statusValue != null
				&& !statusValue.equals("undefined")
				&& !statusValue.equals("全部")) {

			formTable = formTable + " and prod_sns = '" + statusValue + "'";
		}
		String[] cloumnsName = { "SAGDID", "ORG_ID", "A2ID", "JHMC", "JHMC_S",
				"DTFMC_S", "TDH", "AUF", "INCREASE_FREQ_FLAG",
				"START_INCREASE_FREQ_TIME", "END_INCREASE_FREQ_TIME",
				"INCREASE_INTERVAL", "WHRTU_CNF", "WSRTU_CNF", "P_UGRTU_CNF",
				"P_UGTEM_NUM", "P_PURTU_CNF", "WWORK_FLAG", "WWORK_DATE",
				"WABNORMAL_FLAG", "WABNORMAL_DATE", "WELLS_NUM", "WELLS_NAM",
				"PROD_SNS", "STATUS_VALUE", "COMMISSIONING_DATE", "SCAN_TIME",
				"PHASE", "DYEAR", "P_DLT_CNF", "WELL_RTU_ADR", "LJJD_S",
				"JRBZ", "RLAST_OPER", "RLAST_ODATE", "OUTPUT_COEFFICIENT",
				"PROD_STAGES", "P_PROD_MODE", "I_PROD_MODE", "REMARK",
				"OILSTATIONNAME", "AREABLOCK", "BLOCKSTATIONNAME", "ZZZ_ID",
				"GH_ID", "MANIFOLD", "BRANCHINGID", "QKID", "QKMC", "LJJDID",
				"PMODENAME", "IMODENAME", "BBSX", "BZHF" };
		String kk = "SAGDID";
		for (int m = 1; m < cloumnsName.length; m++) {
			if ("RLAST_ODATE".equals(cloumnsName[m])) {
				kk += ",to_char(RLAST_ODATE,'YYYY-MM-DD HH24:MI:SS') as RLAST_ODATE";
			} else if ("START_INCREASE_FREQ_TIME".equals(cloumnsName[m])) {
				kk += ",to_char(START_INCREASE_FREQ_TIME,'YYYY-MM-DD HH24:MI:SS') as START_INCREASE_FREQ_TIME";
			} else if ("END_INCREASE_FREQ_TIME".equals(cloumnsName[m])) {
				kk += ",to_char(END_INCREASE_FREQ_TIME,'YYYY-MM-DD HH24:MI:SS') as END_INCREASE_FREQ_TIME";
			} else {
				kk = kk + "," + cloumnsName[m];
			}
		}
		String boilersInfo = cloums + kk + formTable;
		if (!"".equals(totalNumFlag)) {
			boilersInfo = cloums
					+ " JHMC,PROD_SNS,PROD_STAGES,PMODENAME,IMODENAME,COMMISSIONING_DATE,DYEAR,OILSTATIONNAME,QKMC,BLOCKSTATIONNAME,MANIFOLD,BRANCHINGID,JHMC_S,DTFMC_S,TDH,WWORK_FLAG,WWORK_DATE,WABNORMAL_FLAG,WABNORMAL_DATE,INCREASE_FREQ_FLAG,to_char(START_INCREASE_FREQ_TIME,'YYYY-MM-DD HH24:MI:SS') as START_INCREASE_FREQ_TIME,to_char(END_INCREASE_FREQ_TIME,'YYYY-MM-DD HH24:MI:SS') as END_INCREASE_FREQ_TIME,INCREASE_INTERVAL,WHRTU_CNF,P_UGRTU_CNF,P_UGTEM_NUM,P_PURTU_CNF,WELLS_NUM,WELLS_NAM,SCAN_TIME,PHASE,P_DLT_CNF,WELL_RTU_ADR,LJJD_S,JRBZ,RLAST_OPER,to_char(RLAST_ODATE,'YYYY-MM-DDHH24:MI:SS')asRLAST_ODATE,OUTPUT_COEFFICIENT,REMARK "
					+ formTable;
		}
		// 获取总条数
		int total = 0;
		if ("1".equals(totalNum)) {
			total = 1;
		} else {
			totalNum += formTable;
			total = sagdDao.getCounts(totalNum);
		}
		if ("totalNum".equals(totalNumFlag)) {
			map.put("totalNum", total + "");
			return map;
		}
		// 排序
		if (!"".equals(sort) && !"JHMC".equals(sort)) {
			for (String cloumn : cloumnsName) {
				if (cloumn.equals(sort)) {
					boilersInfo += " order by " + cloumn + " " + order;
					break;
				}

			}
		} else {
			boilersInfo += " order by BBSX ";
		}
		List<Object[]> lo = null;
		if ("export".equals(totalNumFlag)) {
			lo = sagdDao.queryInfo(boilersInfo);
			if (lo != null && 0 < lo.size()) {
				for (Object[] o : lo) {
					if ("1".equals(o[15])) {
						o[15] = "施工";
					} else if ("".equals(o[15])) {
						o[15] = "未施工";
					}
					if ("1".equals(o[17])) {
						o[17] = "正常";
					} else if ("0".equals(o[17])) {
						o[17] = "异常";
					}
					if ("1".equals(o[19])) {
						o[19] = "已加密";
					} else if ("0".equals(o[19])) {
						o[19] = "未加密";
					}
					if ("1".equals(o[23])) {
						o[23] = "已接";
					} else if ("0".equals(o[23])) {
						o[23] = "未接";
					}
					if ("1".equals(o[24])) {
						o[24] = "已接";
					} else if ("0".equals(o[24])) {
						o[24] = "未接";
					}
					if ("1".equals(o[26])) {
						o[26] = "已接";
					} else if ("0".equals(o[26])) {
						o[26] = "未接";
					}
					if ("1".equals(o[31])) {
						o[31] = "无";
					} else if ("0".equals(o[31])) {
						o[31] = "有";
					}
					if ("1".equals(o[34])) {
						o[34] = "接入";
					} else if ("0".equals(o[34])) {
						o[34] = "未接入";
					}
				}
			}
		} else {
			// 计算分页
			PageControl control = new PageControl();
			// 当前页
			control.setInt_num(rowsPerpage);
			control.init(pageNo, total);
			// 开始条数
			int start = control.getStart() - 1;
			lo = sagdDao.searchSagdWell(boilersInfo, start, rowsPerpage);
			if (lo != null && 0 < lo.size()) {
				// 生成树节点json文档
				jsonArr = new JSONArray();
				for (Object[] o : lo) {
					tjo = new JSONObject();

					for (int i = 0; i < o.length; i++) {
						if (o[i] == null || o[i].equals("null")) {
							o[i] = "";
						}
						if (i == 8) {
							if (o[8].equals("1")) {
								tjo.put(cloumnsName[i], "已加密");
							} else if (o[8].equals("0")) {
								tjo.put(cloumnsName[i], "未加密");
							} else {
								tjo.put(cloumnsName[i], o[i].toString());
							}
						} else if (i == 12) {
							if (o[12].equals("1")) {
								tjo.put(cloumnsName[i], "已接");
							} else if (o[12].equals("0")) {
								tjo.put(cloumnsName[i], "未接");
							} else {
								tjo.put(cloumnsName[i], o[i].toString());
							}
						} else if (i == 13) {
							if (o[13].equals("1")) {
								tjo.put(cloumnsName[i], "已接");
							} else if (o[13].equals("0")) {
								tjo.put(cloumnsName[i], "未接");
							} else {
								tjo.put(cloumnsName[i], o[i].toString());
							}
						} else if (i == 14) {
							if (o[14].equals("1")) {
								tjo.put(cloumnsName[i], "已接");
							} else if (o[14].equals("0")) {
								tjo.put(cloumnsName[i], "未接");
							} else {
								tjo.put(cloumnsName[i], o[i].toString());
							}
						} else if (i == 16) {
							if (o[16].equals("1")) {
								tjo.put(cloumnsName[i], "已接");
							} else if (o[16].equals("0")) {
								tjo.put(cloumnsName[i], "未接");
							} else {
								tjo.put(cloumnsName[i], o[i].toString());
							}
						} else if (i == 17) {
							if (o[17].equals("1")) {
								tjo.put(cloumnsName[i], "施工");
							} else if (o[17].equals("0")) {
								tjo.put(cloumnsName[i], "未施工");
							} else {
								tjo.put(cloumnsName[i], o[i].toString());
							}
						} else if (i == 19) {
							if (o[19].equals("1")) {
								tjo.put(cloumnsName[i], "异常");
							} else if (o[19].equals("0")) {
								tjo.put(cloumnsName[i], "正常");
							} else {
								tjo.put(cloumnsName[i], o[i].toString());
							}
						} else if (i == 28) {
							if (o[28].equals("1")) {
								tjo.put(cloumnsName[i], "无");
							} else if (o[28].equals("0")) {
								tjo.put(cloumnsName[i], "有");
							} else {
								tjo.put(cloumnsName[i], o[i].toString());
							}
						} else if (i == 29) {
							if (o[29].equals("1")) {
								tjo.put(cloumnsName[i], "无");
							} else if (o[29].equals("0")) {
								tjo.put(cloumnsName[i], "有");
							} else {
								tjo.put(cloumnsName[i], o[i].toString());
							}
						} else {
							tjo.put(cloumnsName[i], o[i].toString());
						}
					}
					jsonArr.add(tjo);
				}
			}
		}
		tjo = new JSONObject();
		tjo.put("Rows", jsonArr);
		tjo.put("Total", total);
		map.put("list", lo);
		map.put("json", tjo);
		return map;
	}

	/**
	 * 井号名称 JHMC 0 采集时间 ACQUISITION_TIME 1 井号编码 JHMC_S 2 所属采油站 OILSTATIONNAME 3
	 * 所属区块 AREABLOCK 4 所属注转站 BLOCKSTATIONNAME 5 所属管汇 MANIFOLD 6 所属分线
	 * BRANCHINGID 7 SAGD井ID SAGDID 8 组织结构ID ORG_ID 9
	 * 
	 * 电机状态 MOTORSTATUS 10 电机A相电压（V） MOTOR_PRESS_A 11 电机B相电压（V） MOTOR_PRESS_B 12
	 * 电机C相电压（V MOTOR_PRESS_C 13 电机A相电流(A) MOTOR_TEMP_A 14 电机B相电流(A)
	 * MOTOR_TEMP_B 15 电机C相电流(A) MOTOR_TEMP_C 16 有功电量(kWh) ACTIVE_ENERGY 17
	 * 无功电量(kVar) IDLE_ENERGY 18 功率因素(%) POWER_FACTOR 19 变频电压（V）
	 * CONVERSION_PRESS 20 变频电流（A） CONVERSION_ELECTRICITY 21 当前频率（HZ）
	 * NOW_FREQUENCY 22 冲程（m） STROKE 23 冲次（次/分） JIG_FREQUENCY 24 报警状态 ALMSTATE
	 * 25 MOTOR_MODE 26 当前载荷 NOW_LOAD 27 抽油机运行状态' PUMPING_RUNNING_STATE 28
	 * 
	 * P井主管压力 P_PRESSURE_PRESS 29 P井主管温度（℃） P_PRESSURE_TEMP 30 P井副管压力（MPa）
	 * P_LOOPED_PRESS 31 P井副管温度（℃） P_LOOPED_TEMP 32 P井套管压力（MPa）
	 * P_DRIVEPIPE_PRESS 33 I井主管压力 I_PRESSURE_PRESS 34 I井主管温度（℃） I_PRESSURE_TEMP
	 * 35 I井副管压力（MPa） I_LOOPED_PRESS 36 I井副管温度（℃） I_LOOPED_TEMP 37 I井套管压力（MPa
	 * I_DRIVEPIPE_PRESS 38
	 * 
	 * P井压力1（MPa） P_PRESS1 39 P井压力2（MPa） P_PRESS2 40 P井温度1（℃） P_TEMP1 41
	 * P井温度2（℃） P_TEMP2 42 P井温度3（℃） P_TEMP3 43 P井温度4（℃） P_TEMP4 44 P井温度5（℃）
	 * P_TEMP5 45 P井温度6（℃） P_TEMP6 46 P井温度7（℃） P_TEMP7 47 P井温度8（℃） P_TEMP8 48
	 * P井温度9（℃） P_TEMP9 49 P井温度10（℃） P_TEMP10 50 P井温度11（℃） P_TEMP11 51 P井温度12（℃）
	 * P_TEMP12 52
	 * 
	 * 蒸汽压力1# NO1STEAM_PRESS 53 蒸汽压力2# NO2STEAM_PRESS 54 蒸汽温度1# NO1STEAM_TEMP 55
	 * 蒸汽温度2# NO2STEAM_TEMP 56 蒸汽差压1# NO1STEAM_DIFP 57 蒸汽差压2# NO2STEAM_DIFP 58
	 * 瞬时干度1# NO2INSTANT_DRYNESS 59 瞬时干度2# NO2INSTANT_DRYNESS 60 瞬时流量1#
	 * NO1INSTANT_FLOW 61 瞬时流量2# NO2INSTANT_FLOW 62 调节阀开度1# NO1CONTROL_APERTURE
	 * 63 调节阀开度2# NO2CONTROL_APERTURE 64 阀控制状态1# NO1CONTROL_STATUS 65 阀控制状态2#
	 * NO2CONTROL_STATUS 66 阀控制开度1# CLIQUE_CONTROL_APERTURE1 67 阀控制开度2#
	 * CLIQUE_CONTROL_APERTURE2 68 流量设定1# NO1FLOW_SET 69 流量设定2# NO2FLOW_SET 70
	 * 昨日累积流量1# YCUMULATIVE_FLOW1 71 昨日累积流量2# YCUMULATIVE_FLOW2 72 今日累积流量1#
	 * TCUMULATIVE_FLOW1 73 今日累积流量2# TCUMULATIVE_FLOW2 74 --总累积流量1# SUM_FLOW1 75
	 * --总累积流量2# SUM_FLOW2 76 备注 REMARK 75
	 */
	public HashMap<String, Object> searchSagdRDWell(String datastype,
			String oilNumber, String ghmc, String name, String startDate,
			String endDate, int pageNo, String sort, String order,
			int rowsPerpage, String totalNumf) {
		JSONArray jsonArr = null;
		JSONObject tjo = null;
		String cloums = "select   ";
		String formTable = " from PC_RD_WELL_SAGD_V a where 1=1 ";
		String totalNum = "select count(*) ";
		HashMap<String, Object> map = new HashMap<String, Object>();

		if (!oilNumber.equals("") && oilNumber != null
				&& !oilNumber.equals("undefined") && !oilNumber.equals("全部")) {
			formTable = formTable + " and OILSTATIONNAME='" + oilNumber + "'";
		}
		if (!ghmc.equals("") && ghmc != null && !ghmc.equals("undefined")) {
			formTable = formTable + " and MANIFOLD='" + ghmc + "'";
		}
		if (!name.equals("") && name != null && !name.equals("undefined")) {
			formTable = formTable + " and jhmc like '%" + name + "%'";
		}
		Date today = new Date();
		String nowdate = DateBean.getTimeStr(today);

		if ("".equals(startDate)) {
			startDate = nowdate.substring(0, 10) + " 00:00:00";
		} else {
			startDate = startDate + ":00";
		}
		if ("".equals(endDate)) {
			endDate = nowdate.substring(0, 10) + " 23:59:59";
		} else {
			endDate = endDate + ":00";
		}
		List<String> cloumnsName = new ArrayList<String>();
		formTable = formTable + " and ACQUISITION_TIME between TO_DATE('"
				+ startDate + "','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"
				+ endDate + "','YYYY-MM-DD HH24:MI:SS')";

		cloumnsName.add("JHMC");
		cloumnsName.add("ACQUISITION_TIME");
		cloumnsName.add("JHMC_S");
		cloumnsName.add("OILSTATIONNAME");
		cloumnsName.add("AREABLOCK");
		cloumnsName.add("BLOCKSTATIONNAME");
		cloumnsName.add("MANIFOLD");
		cloumnsName.add("BRANCHINGID");
		if (!"export".equals(totalNumf)) {
			cloumnsName.add("SAGDID");
			cloumnsName.add("ORG_ID");
		}
		// 抽油机参数
		if (!"".equals(totalNumf) && "2".equals(datastype)) {
			cloumnsName.add("MOTORSTATUS");
			cloumnsName.add("MOTOR_PRESS_A");
			cloumnsName.add("MOTOR_PRESS_B");
			cloumnsName.add("MOTOR_PRESS_C");
			cloumnsName.add("MOTOR_TEMP_A");
			cloumnsName.add("MOTOR_TEMP_B");
			cloumnsName.add("MOTOR_TEMP_C");
			cloumnsName.add("ACTIVE_ENERGY");
			cloumnsName.add("IDLE_ENERGY");
			cloumnsName.add("POWER_FACTOR");
			cloumnsName.add("CONVERSION_PRESS");
			cloumnsName.add("CONVERSION_ELECTRICITY");
			cloumnsName.add("NOW_FREQUENCY");
			cloumnsName.add("STROKE");
			cloumnsName.add("JIG_FREQUENCY");
			cloumnsName.add("ALMSTATE");
			cloumnsName.add("NOW_LOAD");
			cloumnsName.add("MOTOR_MODE");
			cloumnsName.add("PUMPING_RUNNING_STATE");
			// 井口数据
		} else if (!"".equals(totalNumf) && "3".equals(datastype)) {
			cloumnsName.add("P_PRESSURE_PRESS");
			cloumnsName.add("P_PRESSURE_TEMP");
			cloumnsName.add("P_LOOPED_PRESS");
			cloumnsName.add("P_LOOPED_TEMP");
			cloumnsName.add("P_DRIVEPIPE_PRESS");
			cloumnsName.add("I_PRESSURE_PRESS");
			cloumnsName.add("I_PRESSURE_TEMP");
			cloumnsName.add("I_LOOPED_PRESS");
			cloumnsName.add("I_LOOPED_TEMP");
			cloumnsName.add("I_DRIVEPIPE_PRESS");

			// 井下数据
		} else if (!"".equals(totalNumf) && "4".equals(datastype)) {
			cloumnsName.add("P_PRESS1");
			cloumnsName.add("P_PRESS2");
			cloumnsName.add("P_TEMP1");
			cloumnsName.add("P_TEMP2");
			cloumnsName.add("P_TEMP3");
			cloumnsName.add("P_TEMP4");
			cloumnsName.add("P_TEMP5");
			cloumnsName.add("P_TEMP6");
			cloumnsName.add("P_TEMP7");
			cloumnsName.add("P_TEMP8");
			cloumnsName.add("P_TEMP9");
			cloumnsName.add("P_TEMP10");
			cloumnsName.add("P_TEMP11");
			cloumnsName.add("P_TEMP12");

			// 蒸汽数据
		} else if (!"".equals(totalNumf) && "5".equals(datastype)) {
			cloumnsName.add("NO1STEAM_PRESS");
			cloumnsName.add("NO2STEAM_PRESS");
			cloumnsName.add("NO1STEAM_TEMP");
			cloumnsName.add("NO2STEAM_TEMP");
			cloumnsName.add("NO1STEAM_DIFP");
			cloumnsName.add("NO2STEAM_DIFP");
			cloumnsName.add("NO1INSTANT_DRYNESS");
			cloumnsName.add("NO2INSTANT_DRYNESS");
			cloumnsName.add("NO1INSTANT_FLOW");
			cloumnsName.add("NO2INSTANT_FLOW");
			cloumnsName.add("NO1CONTROL_APERTURE");
			cloumnsName.add("NO2CONTROL_APERTURE");
			cloumnsName.add("NO1CONTROL_STATUS");
			cloumnsName.add("NO2CONTROL_STATUS");
			cloumnsName.add("CLIQUE_CONTROL_APERTURE1");
			cloumnsName.add("CLIQUE_CONTROL_APERTURE2");
			cloumnsName.add("NO1FLOW_SET");
			cloumnsName.add("NO2FLOW_SET");
			cloumnsName.add("YCUMULATIVE_FLOW1");
			cloumnsName.add("YCUMULATIVE_FLOW2");
			cloumnsName.add("TCUMULATIVE_FLOW1");
			cloumnsName.add("TCUMULATIVE_FLOW2");
			// cloumnsName.add("SUM_FLOW1");
			// cloumnsName.add("SUM_FLOW2");

		} else {
			cloumnsName.add("MOTORSTATUS");
			cloumnsName.add("MOTOR_PRESS_A");
			cloumnsName.add("MOTOR_PRESS_B");
			cloumnsName.add("MOTOR_PRESS_C");
			cloumnsName.add("MOTOR_TEMP_A");
			cloumnsName.add("MOTOR_TEMP_B");
			cloumnsName.add("MOTOR_TEMP_C");
			cloumnsName.add("ACTIVE_ENERGY");
			cloumnsName.add("IDLE_ENERGY");
			cloumnsName.add("POWER_FACTOR");
			cloumnsName.add("CONVERSION_PRESS");
			cloumnsName.add("CONVERSION_ELECTRICITY");
			cloumnsName.add("NOW_FREQUENCY");
			cloumnsName.add("STROKE");
			cloumnsName.add("JIG_FREQUENCY");
			cloumnsName.add("ALMSTATE");
			cloumnsName.add("NOW_LOAD");
			cloumnsName.add("MOTOR_MODE");
			cloumnsName.add("PUMPING_RUNNING_STATE");

			cloumnsName.add("P_PRESSURE_PRESS");
			cloumnsName.add("P_PRESSURE_TEMP");
			cloumnsName.add("P_LOOPED_PRESS");
			cloumnsName.add("P_LOOPED_TEMP");
			cloumnsName.add("P_DRIVEPIPE_PRESS");
			cloumnsName.add("I_PRESSURE_PRESS");
			cloumnsName.add("I_PRESSURE_TEMP");
			cloumnsName.add("I_LOOPED_PRESS");
			cloumnsName.add("I_LOOPED_TEMP");
			cloumnsName.add("I_DRIVEPIPE_PRESS");

			cloumnsName.add("P_PRESS1");
			cloumnsName.add("P_PRESS2");
			cloumnsName.add("P_TEMP1");
			cloumnsName.add("P_TEMP2");
			cloumnsName.add("P_TEMP3");
			cloumnsName.add("P_TEMP4");
			cloumnsName.add("P_TEMP5");
			cloumnsName.add("P_TEMP6");
			cloumnsName.add("P_TEMP7");
			cloumnsName.add("P_TEMP8");
			cloumnsName.add("P_TEMP9");
			cloumnsName.add("P_TEMP10");
			cloumnsName.add("P_TEMP11");
			cloumnsName.add("P_TEMP12");

			cloumnsName.add("NO1STEAM_PRESS");
			cloumnsName.add("NO2STEAM_PRESS");
			cloumnsName.add("NO1STEAM_TEMP");
			cloumnsName.add("NO2STEAM_TEMP");
			cloumnsName.add("NO1STEAM_DIFP");
			cloumnsName.add("NO2STEAM_DIFP");
			cloumnsName.add("NO1INSTANT_DRYNESS");
			cloumnsName.add("NO2INSTANT_DRYNESS");
			cloumnsName.add("NO1INSTANT_FLOW");
			cloumnsName.add("NO2INSTANT_FLOW");
			cloumnsName.add("NO1CONTROL_APERTURE");
			cloumnsName.add("NO2CONTROL_APERTURE");
			cloumnsName.add("NO1CONTROL_STATUS");
			cloumnsName.add("NO2CONTROL_STATUS");
			cloumnsName.add("CLIQUE_CONTROL_APERTURE1");
			cloumnsName.add("CLIQUE_CONTROL_APERTURE2");
			cloumnsName.add("NO1FLOW_SET");
			cloumnsName.add("NO2FLOW_SET");
			cloumnsName.add("YCUMULATIVE_FLOW1");
			cloumnsName.add("YCUMULATIVE_FLOW2");
			cloumnsName.add("TCUMULATIVE_FLOW1");
			cloumnsName.add("TCUMULATIVE_FLOW2");
			// cloumnsName.add("SUM_FLOW1");
			// cloumnsName.add("SUM_FLOW2");
		}

		cloumnsName.add("REMARK");
		String kk = "JHMC";
		for (int m = 1; m < cloumnsName.size(); m++) {
			// to_char(u.audbshor_date,'YYYY-MM-DD hh24:mi:ss') as audbshor_date
			if ("ACQUISITION_TIME".equals(cloumnsName.get(m))) {
				kk = kk
						+ ","
						+ "to_char(ACQUISITION_TIME,'YYYY-MM-DD hh24:mi:ss') as ACQUISITION_TIME";
			} else {
				kk = kk + "," + cloumnsName.get(m);
			}

		}

		String boilersInfo = cloums + kk + formTable;
		// 获取总条数
		int total = 0;
		if ("1".equals(totalNum)) {
			total = 1;
		} else {
			totalNum += formTable;
			total = sagdDao.getCounts(totalNum);
		}
		if ("totalNum".equals(totalNumf)) {
			map.put("totalNum", total + "");
			return map;
		}
		// 排序
		if (!"".equals(sort) && !"JHMC".equals(sort)) {
			for (String cloumn : cloumnsName) {
				if (cloumn.equals(sort)) {
					boilersInfo += " order by " + cloumn + " " + order;
					break;
				}

			}
		} else {
			boilersInfo += " order by JHMC,ACQUISITION_TIME desc ";
		}
		List<Object[]> lo = null;
		try {
			if ("export".equals(totalNumf)) {
				lo = sagdDao.queryInfo(boilersInfo);
			} else {
				// 计算分页
				PageControl control = new PageControl();
				// 当前页
				control.setInt_num(rowsPerpage);
				control.init(pageNo, total);
				// 开始条数
				int start = control.getStart() - 1;
				lo = sagdDao.searchSagdRDWell(boilersInfo, start, rowsPerpage,
						cloumnsName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (lo != null && 0 < lo.size()) {
			// 生成树节点json文档
			jsonArr = new JSONArray();
			for (Object[] o : lo) {
				tjo = new JSONObject();

				for (int i = 0; i < o.length; i++) {
					if (o[i] == null || o[i].equals("null")) {
						o[i] = "";
					}
					if (i == 10) {
						if (o[10].toString().equals("1")) {
							tjo.put(cloumnsName.get(i), "自动");
							o[10] = "自动";
						} else if (o[10].toString().equals("0")) {
							tjo.put(cloumnsName.get(i), "手动");
							o[10] = "手动";
						} else {
							tjo.put(cloumnsName.get(i), o[i].toString());
						}
					} else if (i == 65) {
						if (o[64].toString().equals("1")) {
							tjo.put(cloumnsName.get(i), "自动");
							o[64] = "自动";
						} else if (o[64].toString().equals("0")) {
							tjo.put(cloumnsName.get(i), "手动");
							o[64] = "手动";
						} else {
							tjo.put(cloumnsName.get(i), o[i].toString());
						}
					} else if (i == 66) {
						if (o[65].toString().equals("1")) {
							tjo.put(cloumnsName.get(i), "自动");
							o[65] = "自动";
						} else if (o[65].toString().equals("0")) {
							tjo.put(cloumnsName.get(i), "手动");
							o[65] = "手动";
						} else {
							tjo.put(cloumnsName.get(i), o[i].toString());
						}
					} else if (i == 28) {
						if (o[28].toString().equals("1")) {
							tjo.put(cloumnsName.get(i), "指示");
							o[28] = "指示";
						} else if (o[28].toString().equals("2")) {
							tjo.put(cloumnsName.get(i), "空闲");
							o[28] = "空闲";
						} else if (o[28].toString().equals("4")) {
							tjo.put(cloumnsName.get(i), "停机");
							o[28] = "停机";
						} else if (o[28].toString().equals("8")) {
							tjo.put(cloumnsName.get(i), "死停");
							o[28] = "死停";
						} else {
							tjo.put(cloumnsName.get(i), o[i].toString());
						}
					} else if (i == 27) {
						if (o[27].toString().equals("1")) {
							tjo.put(cloumnsName.get(i), "变频");
							o[27] = "变频";
						} else if (o[27].toString().equals("0")) {
							tjo.put(cloumnsName.get(i), "工频");
							o[28] = "工频";
						} else {
							tjo.put(cloumnsName.get(i), o[i].toString());
						}
					} else {
						tjo.put(cloumnsName.get(i), o[i].toString());
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

	public HashMap<String, Object> searchSagdRPD(String oilNumber, String ghmc,
			String well, String area, String startDate, String endDate,
			int pageNo, String sort, String order, int rowsPerpage,
			String totalNumf, String search, String BZHF) throws Exception {
		String cloums = "select ";
		String formTable = " from PC_RPD_WELL_SAGDD_v a where 1=1 ";

		String totalNum = "select count(*) ";
		HashMap<String, Object> map = new HashMap<String, Object>();

		if (!oilNumber.equals("") && oilNumber != null
				&& !oilNumber.equals("undefined") && !oilNumber.equals("全部")) {
			formTable = formTable + " and OILSTATIONNAME='" + oilNumber + "'";
		}
		if (!ghmc.equals("") && ghmc != null && !ghmc.equals("undefined")) {
			formTable = formTable + " and MANIFOLD='" + ghmc + "'";
		}
		if (!well.equals("") && well != null && !well.equals("undefined")) {
			formTable = formTable + " and jhmc like '%" + well + "%'";
		}
		if (!area.equals("") && area != null && !area.equals("undefined")
				&& !area.equals("全部")) {
			formTable = formTable + " and qkmc like '%" + area + "%'";
		}
		if (!BZHF.equals("") && BZHF != null && !BZHF.equals("undefined")
				&& !BZHF.equals("全部")) {
			formTable = formTable + " and BZHF like '%" + BZHF + "%'";
		}

		Date today = new Date();
		String nowdate = DateBean.getTimeStr(today);

		if ("".equals(startDate)) {
			startDate = nowdate.substring(0, 10) + " 00:00:00";
		} else {
			startDate = startDate + " 00:00:00";
		}
		if ("".equals(endDate)) {
			endDate = nowdate.substring(0, 10) + " 23:59:59";
		} else {
			endDate = endDate + " 23:59:59";
		}
		formTable = formTable + " and REPORT_DATE between TO_DATE('"
				+ startDate + "','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"
				+ endDate + "','YYYY-MM-DD HH24:MI:SS')";
		// String[] cloumnsName =
		// {"WELL_CODE","OILSTATIONNAME","QKMC","BLOCKSTATIONNAME","MANIFOLD","BRANCHING_NAME","REPORT_DATE","P_PRESSURE_PRESS","P_PRESSURE_TEMP","P_LOOPED_PRESS","P_LOOPED_TEMP","P_DRIVEPIPE_PRESS","I_PRESSURE_PRESS","I_PRESSURE_TEMP","I_LOOPED_PRESS","I_LOOPED_TEMP","I_DRIVEPIPE_PRESS","NO1STEAM_TEMP","NO1STEAM_PRESS","NO1STEAM_DIFP","NO1INSTANT_FLOW","LOWSUM_FLOW1","NO1INSTANT_DRYNESS","NO1CONTROL_STATUS","NO1CONTROL_APERTURE","NO1FLOW_SET","NO2STEAM_TEMP","NO2STEAM_PRESS","NO2STEAM_DIFP","NO2INSTANT_FLOW","LOWSUM_FLOW2","NO2INSTANT_DRYNESS","NO2CONTROL_STATUS","NO2CONTROL_APERTURE","NO2FLOW_SET","P_PRESS1","P_PRESS2","P_TEMP1","P_TEMP2","P_TEMP3","P_TEMP4","P_TEMP5","P_TEMP6","P_TEMP7","P_TEMP8","P_TEMP9","P_TEMP10","P_TEMP11","P_TEMP12","NOW_LOAD","MOTOR_PRESS_A","MOTOR_TEMP_A","MOTOR_PRESS_B","MOTOR_TEMP_B","MOTOR_PRESS_C","MOTOR_TEMP_C","CLIQUE_CONTROL_APERTURE2","ACTIVE_ENERGY","IDLE_ENERGY","POWER_FACTOR","JIG_FREQUENCY","STROKE","PUMPING_RUNNING_STATE","MOTORSTATUS","CONVERSION_PRESS","NOW_FREQUENCY","CONVERSION_ELECTRICITY","MOTOR_MODE","CLIQUE_CONTROL_APERTURE1","RUNTIME","DAILY_OUTPUT","DAILY_POWER_CONSUMPTIVE","DAILY_CUMULATIVE_STEAM1","DAILY_CUMULATIVE_STEAM2","REMARK","ORG_ID","A2ID","SAGDID"};
		String cloumnsName[];
		if ("search".equals(search)) {
			// String[] cloumnsName1 = {"REPORT_DATE"
			// ,"JHMC","I_INJE_TIME_RATION","I_PROC_TIME_RATION","I_P_FLOW_NIPPLE","I_PRESSURE_PRESS","I_PRESSURE_TEMP","I_I_FLOW_NIPPLE","I_LOOPED_PRESS","I_LOOPED_TEMP","I_DRIVEPIPE_PRESS","I_DAILY_OUTPUT","I_DAILY_OIL_OUTPUT","I_WATER_RATION","I_STEAM_FLOW","I_DAILY_CUMULATIVE_STEAM","I_REMARK","P_INJE_TIME_RATION","P_PROC_TIME_RATION","RUNTIME","PUMP_ERROR_TIME","PUMP_DIAMETER","STROKE","JIG_FREQUENCY","P_P_FLOW_NIPPLE","P_PRESSURE_PRESS","P_PRESSURE_TEMP","P_I_FLOW_NIPPLE","P_LOOPED_PRESS","P_LOOPED_TEMP","P_DRIVEPIPE_PRESS","P_DAILY_OUTPUT","P_DAILY_OIL_OUTPUT","P_WATER_RATION","P_STEAM_FLOW","P_DAILY_CUMULATIVE_STEAM","P_TEMP1","P_TEMP2","P_TEMP3","P_TEMP4","P_TEMP5","P_TEMP6","P_TEMP7","P_TEMP8","P_TEMP9","P_TEMP10","P_TEMP11","P_TEMP12","REMARK","SAGDDID","SAGDID","VERIFIER","VERIFY_TIME","RLAST_OPER","RLAST_ODATE","P_BACK_PRES","I_BACK_PRES","containing_water_flg","ORG_ID","P_STIMU_VALUE",
			// "P_SHUTIN_VALUE","P_SHUTIN_TIME","P_OPEN_TIME","I_STIMU_VALUE","I_SHUTIN_VALUE","I_SHUTIN_TIME","I_OPEN_TIME"};
			String[] cloumnsName1 = { "REPORT_DATE", "JHMC",
					"I_INJE_TIME_RATION", "I_PROC_TIME_RATION",
					"I_P_FLOW_NIPPLE", "I_PRESSURE_PRESS", "I_PRESSURE_TEMP",
					"I_I_FLOW_NIPPLE", "I_LOOPED_PRESS", "I_LOOPED_TEMP",
					"I_BACK_PRES", "I_DRIVEPIPE_PRESS", "I_DAILY_OUTPUT",
					"I_DAILY_OIL_OUTPUT", "I_WATER_RATION", "NO1INSTANT_FLOW",
					"I_DAILY_CUMULATIVE_STEAM", "I_REMARK",
					"P_INJE_TIME_RATION", "P_PROC_TIME_RATION", "RUNTIME",
					"PUMP_ERROR_TIME", "PUMP_DIAMETER", "STROKE",
					"JIG_FREQUENCY", "P_P_FLOW_NIPPLE", "P_PRESSURE_PRESS",
					"P_PRESSURE_TEMP", "P_I_FLOW_NIPPLE", "P_LOOPED_PRESS",
					"P_LOOPED_TEMP", "P_BACK_PRES", "P_DRIVEPIPE_PRESS",
					"P_DAILY_OUTPUT", "P_DAILY_OIL_OUTPUT", "P_WATER_RATION",
					"NO2INSTANT_FLOW", "P_DAILY_CUMULATIVE_STEAM", "P_TEMP1",
					"P_TEMP2", "P_TEMP3", "P_TEMP4", "P_TEMP5", "P_TEMP6",
					"P_TEMP7", "P_TEMP8", "P_TEMP9", "P_TEMP10", "P_TEMP11",
					"P_TEMP12", "REMARK", "RLAST_OPER", "RLAST_ODATE",
					"VERIFIER", "VERIFY_TIME", "SAGDDID", "SAGDID", "BBSX",
					"BZHF"
			// ,"ORG_ID","CONTAINING_WATER_FLG"
			};
			cloumnsName = cloumnsName1;
			// }else if ("export".equals(totalNumf)){
			// String[] cloumnsName3 =
			// {"REPORT_DATE","JHMC","I_INJE_TIME_RATION","I_PROC_TIME_RATION","I_P_FLOW_NIPPLE","I_PRESSURE_PRESS","I_PRESSURE_TEMP","I_I_FLOW_NIPPLE","I_LOOPED_PRESS","I_LOOPED_TEMP","I_BACK_PRES","I_DRIVEPIPE_PRESS","I_DAILY_OUTPUT","I_DAILY_OIL_OUTPUT","I_WATER_RATION","I_STEAM_FLOW","I_DAILY_CUMULATIVE_STEAM","I_REMARK","P_INJE_TIME_RATION","P_PROC_TIME_RATION","RUNTIME","PUMP_ERROR_TIME","PUMP_DIAMETER","STROKE","JIG_FREQUENCY","P_P_FLOW_NIPPLE","P_PRESSURE_PRESS","P_PRESSURE_TEMP","P_I_FLOW_NIPPLE","P_LOOPED_PRESS","P_LOOPED_TEMP","P_BACK_PRES","P_DRIVEPIPE_PRESS","P_DAILY_OUTPUT","P_DAILY_OIL_OUTPUT","P_WATER_RATION","P_STEAM_FLOW","P_DAILY_CUMULATIVE_STEAM","P_TEMP1","P_TEMP2","P_TEMP3","P_TEMP4","P_TEMP5","P_TEMP6","P_TEMP7","P_TEMP8","P_TEMP9","P_TEMP10","P_TEMP11","P_TEMP12","REMARK"};
			// cloumnsName = cloumnsName3;
		} else {
			// String[] cloumnsName2 =
			// {"REPORT_DATE","JHMC","I_INJE_TIME_RATION","I_PROC_TIME_RATION","I_P_FLOW_NIPPLE","I_PRESSURE_PRESS","I_PRESSURE_TEMP","I_I_FLOW_NIPPLE","I_LOOPED_PRESS","I_LOOPED_TEMP","I_DRIVEPIPE_PRESS","I_DAILY_OUTPUT","I_DAILY_OIL_OUTPUT","I_WATER_RATION","I_STEAM_FLOW","I_DAILY_CUMULATIVE_STEAM","I_REMARK","P_INJE_TIME_RATION","P_PROC_TIME_RATION","RUNTIME","PUMP_ERROR_TIME","PUMP_DIAMETER","STROKE","JIG_FREQUENCY","P_P_FLOW_NIPPLE","P_PRESSURE_PRESS","P_PRESSURE_TEMP","P_I_FLOW_NIPPLE","P_LOOPED_PRESS","P_LOOPED_TEMP","P_DRIVEPIPE_PRESS","P_DAILY_OUTPUT","P_DAILY_OIL_OUTPUT","P_WATER_RATION","P_STEAM_FLOW","P_DAILY_CUMULATIVE_STEAM","P_TEMP1","P_TEMP2","P_TEMP3","P_TEMP4","P_TEMP5","P_TEMP6","P_TEMP7","P_TEMP8","P_TEMP9","P_TEMP10","P_TEMP11","P_TEMP12","REMARK","SAGDDID","SAGDID","VERIFIER","VERIFY_TIME","RLAST_OPER","RLAST_ODATE","P_BACK_PRES","I_BACK_PRES","containing_water_flg","ORG_ID","P_SHUTIN_TIME","P_OPEN_TIME","I_SHUTIN_CODE","I_SHUTIN_TIME","I_OPEN_TIME"};
			String[] cloumnsName2 = { "REPORT_DATE", "JHMC",
					"I_INJE_TIME_RATION", "I_PROC_TIME_RATION",
					"I_P_FLOW_NIPPLE", "I_PRESSURE_PRESS", "I_PRESSURE_TEMP",
					"I_I_FLOW_NIPPLE", "I_LOOPED_PRESS", "I_LOOPED_TEMP",
					"I_BACK_PRES", "I_DRIVEPIPE_PRESS", "I_DAILY_OUTPUT",
					"I_DAILY_OIL_OUTPUT", "I_WATER_RATION", "NO1INSTANT_FLOW",
					"I_DAILY_CUMULATIVE_STEAM", "I_REMARK",
					"P_INJE_TIME_RATION", "P_PROC_TIME_RATION", "RUNTIME",
					"PUMP_ERROR_TIME", "PUMP_DIAMETER", "STROKE",
					"JIG_FREQUENCY", "P_P_FLOW_NIPPLE", "P_PRESSURE_PRESS",
					"P_PRESSURE_TEMP", "P_I_FLOW_NIPPLE", "P_LOOPED_PRESS",
					"P_LOOPED_TEMP", "P_BACK_PRES", "P_DRIVEPIPE_PRESS",
					"P_DAILY_OUTPUT", "P_DAILY_OIL_OUTPUT", "P_WATER_RATION",
					"NO2INSTANT_FLOW", "P_DAILY_CUMULATIVE_STEAM", "P_TEMP1",
					"P_TEMP2", "P_TEMP3", "P_TEMP4", "P_TEMP5", "P_TEMP6",
					"P_TEMP7", "P_TEMP8", "P_TEMP9", "P_TEMP10", "P_TEMP11",
					"P_TEMP12", "REMARK", "RLAST_OPER", "RLAST_ODATE",
					"VERIFIER", "VERIFY_TIME", "SAGDDID", "SAGDID", "ORG_ID",
					"CONTAINING_WATER_FLG", "BBSX", "BZHF" };
			cloumnsName = cloumnsName2;
		}
		// String[] cloumnsName =
		// {"REPORT_DATE","JHMC","I_PROC_TIME_RATION","I_P_FLOW_NIPPLE","I_PRESSURE_PRESS","I_PRESSURE_TEMP","I_I_FLOW_NIPPLE","I_LOOPED_PRESS","I_LOOPED_TEMP","I_DRIVEPIPE_PRESS","I_DAILY_OUTPUT","I_DAILY_OIL_OUTPUT","I_WATER_RATION","I_STEAM_FLOW","I_DAILY_CUMULATIVE_STEAM","I_REMARK","P_PROC_TIME_RATION","RUNTIME","PUMP_ERROR_TIME","PUMP_DIAMETER","STROKE","JIG_FREQUENCY","P_P_FLOW_NIPPLE","P_PRESSURE_PRESS","P_PRESSURE_TEMP","P_I_FLOW_NIPPLE","P_LOOPED_PRESS","P_LOOPED_TEMP","P_DRIVEPIPE_PRESS","P_DAILY_OUTPUT","P_DAILY_OIL_OUTPUT","P_WATER_RATION","P_STEAM_FLOW","P_DAILY_CUMULATIVE_STEAM","P_TEMP1","P_TEMP2","P_TEMP3","P_TEMP4","P_TEMP5","P_TEMP6","P_TEMP7","P_TEMP8","P_TEMP9","P_TEMP10","P_TEMP11","P_TEMP12","P_PRESS1","P_PRESS2","REMARK","SAGDDID","SAGDID","VERIFIER","VERIFY_TIME","RLAST_OPER","RLAST_ODATE","P_BACK_PRES","I_BACK_PRES","ORG_ID"};
		String[] cloumnsName1 = { "REPORT_DATE", "JHMC", "I_INJE_TIME_RATION",
				"I_PROC_TIME_RATION", "I_P_FLOW_NIPPLE", "I_PRESSURE_PRESS",
				"I_PRESSURE_TEMP", "I_I_FLOW_NIPPLE", "I_LOOPED_PRESS",
				"I_LOOPED_TEMP", "I_BACK_PRES", "I_DRIVEPIPE_PRESS",
				"I_DAILY_OUTPUT", "I_DAILY_OIL_OUTPUT", "I_WATER_RATION",
				"NO1INSTANT_FLOW", "I_DAILY_CUMULATIVE_STEAM", "I_REMARK",
				"P_INJE_TIME_RATION", "P_PROC_TIME_RATION", "RUNTIME",
				"PUMP_ERROR_TIME", "PUMP_DIAMETER", "STROKE", "JIG_FREQUENCY",
				"P_P_FLOW_NIPPLE", "P_PRESSURE_PRESS", "P_PRESSURE_TEMP",
				"P_I_FLOW_NIPPLE", "P_LOOPED_PRESS", "P_LOOPED_TEMP",
				"P_BACK_PRES", "P_DRIVEPIPE_PRESS", "P_DAILY_OUTPUT",
				"P_DAILY_OIL_OUTPUT", "P_WATER_RATION", "NO2INSTANT_FLOW",
				"P_DAILY_CUMULATIVE_STEAM", "P_TEMP1", "P_TEMP2", "P_TEMP3",
				"P_TEMP4", "P_TEMP5", "P_TEMP6", "P_TEMP7", "P_TEMP8",
				"P_TEMP9", "P_TEMP10", "P_TEMP11", "P_TEMP12", "REMARK" };
		if ("export".equals(totalNumf)) {
			for (String cn : cloumnsName1) {
				cloums += " " + cn + ",";
			}

			cloums = cloums.substring(0, cloums.length() - 1);
		} else {
			for (String cn : cloumnsName) {
				if ("export".equals(totalNumf) && "SAGDDID".equals(cn)) {
					break;
				}
				if (!"REPORT_DATE".equals(cn)) {
					if ("RLAST_ODATE".equals(cn)) {
						cloums += ", to_char(RLAST_ODATE,'YYYY-MM-DD HH24:MI:SS') as RLAST_ODATE";
					} else if ("I_SHUTIN_TIME".equals(cn)) {
						cloums += ", to_char(I_SHUTIN_TIME,'YYYY-MM-DD HH24:MI') as I_SHUTIN_TIME";
					} else if ("I_OPEN_TIME".equals(cn)) {
						cloums += ", to_char(I_OPEN_TIME,'YYYY-MM-DD HH24:MI') as I_OPEN_TIME";
					} else if ("P_SHUTIN_TIME".equals(cn)) {
						cloums += ", to_char(P_SHUTIN_TIME,'YYYY-MM-DD HH24:MI') as P_SHUTIN_TIME";
					} else if ("P_OPEN_TIME".equals(cn)) {
						cloums += ", to_char(P_OPEN_TIME,'YYYY-MM-DD HH24:MI') as P_OPEN_TIME";
					} else if ("VERIFY_TIME".equals(cn)) {
						cloums += ", to_char(VERIFY_TIME,'YYYY-MM-DD HH24:MI') as VERIFY_TIME";
					} else {
						cloums += "," + cn;
					}

					continue;
				}
				// cloums +=
				// "to_char(REPORT_DATE,'YYYY-MM-DD HH24:MI:SS') as REPORT_DATE";

				cloums += "REPORT_DATE ";
			}
		}
		String wellInfo = cloums + formTable;
		// 获取总条数
		int total = 0;
		if ("1".equals(totalNum)) {
			total = 1;
		} else {
			totalNum += formTable;
			total = sagdDao.getCounts(totalNum);
		}
		if ("totalNum".equals(totalNumf)) {
			map.put("totalNum", total + "");
			return map;
		}
		// 排序
		if (!"".equals(sort) && !"JHMC".equals(sort)) {
			for (String cloumn : cloumnsName) {
				if (cloumn.equals(sort)) {
					wellInfo += " order by " + cloumn + " " + order;
					break;
				}
			}
		} else {
			wellInfo += " order by BBSX ";
		}
		List<Object[]> lo = null;
		List<Object[]> lobj = new ArrayList<Object[]>();
		;
		try {
			if ("export".equals(totalNumf)) {
				lo = sagdDao.queryInfo(wellInfo);
				if (lo != null && 0 < lo.size()) {
					for (int j = 0; j < lo.size(); j++) {
						Object[] firData = lo.get(j);
						Object[] param = null;
						param = new Object[firData.length];
						for (int i = 0; i < firData.length; i++) {
							if (i == 2 || i == 3 || i == 4 || i == 7 || i == 11
									|| i == 14 || i == 18 || i == 19 || i == 20
									|| i == 21 || i == 24 || i == 25 || i == 28
									|| i == 35) {
								param[i] = CommonsUtil
										.getNotOneDataZ(firData[i]);
								// tjo.put(cloumnsName[i],
								// CommonsUtil.getNotOneDataZ( o[i]));
							} else if (i == 5 || i == 8 || i == 10 || i == 12
									|| i == 13 || i == 15 || i == 26 || i == 29
									|| i == 31 || i == 32 || i == 33 || i == 34
									|| i == 36) {
								// tjo.put(cloumnsName[i],
								// CommonsUtil.getNotTwoDataZero( o[i]));
								param[i] = CommonsUtil
										.getNotTwoDataZero(firData[i]);
							} else if (i == 6 || i == 9 || i == 16 || i == 22
									|| i == 23 || i == 27 || i == 30 || i == 37
									|| i == 37 || i == 38 || i == 39 || i == 40
									|| i == 41 || i == 42 || i == 43 || i == 44
									|| i == 45 || i == 46 || i == 47 || i == 48
									|| i == 49) {
								// tjo.put(cloumnsName[i],
								// CommonsUtil.getIntData( o[i]));
								param[i] = CommonsUtil.getIntData(firData[i]);
							} else {
								// tjo.put(cloumnsName[i],
								// CommonsUtil.getClearNullData(o[i]));
								param[i] = firData[i];
							}
							// param[j] = firData[j];
						}
						lobj.add(param);
					}
				}
				map.put("list", lobj);
				return map;
			} else {
				// 计算分页
				PageControl control = new PageControl();
				// 当前页
				control.setInt_num(rowsPerpage);
				control.init(pageNo, total);
				// 开始条数
				int start = control.getStart() - 1;
				lo = sagdDao.searchSagdRPD(wellInfo, start, rowsPerpage,
						cloumnsName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONObject tjo = null;
		JSONArray jsonArr = null;
		if (lo != null && 0 < lo.size()) {
			// 生成树节点json文档
			jsonArr = new JSONArray();
			for (Object[] o : lo) {
				tjo = new JSONObject();

				for (int i = 0; i < o.length; i++) {
					// if (o[i] == null||o[i].equals("null")) {
					// o[i] = "";
					// }
					if (i == 2 || i == 3 || i == 4 || i == 7 || i == 11
							|| i == 14 || i == 18 || i == 19 || i == 20
							|| i == 21 || i == 24 || i == 25 || i == 28
							|| i == 35) {
						tjo.put(cloumnsName[i], CommonsUtil
								.getNotOneDataZ(o[i]));
					} else if (i == 5 || i == 8 || i == 10 || i == 12
							|| i == 13 || i == 15 || i == 26 || i == 29
							|| i == 31 || i == 32 || i == 33 || i == 34
							|| i == 36) {
						tjo.put(cloumnsName[i], CommonsUtil
								.getNotTwoDataZero(o[i]));
					} else if (i == 6 || i == 9 || i == 16 || i == 22
							|| i == 23 || i == 27 || i == 30 || i == 37
							|| i == 37 || i == 38 || i == 39 || i == 40
							|| i == 41 || i == 42 || i == 43 || i == 44
							|| i == 45 || i == 46 || i == 47 || i == 48
							|| i == 49) {
						tjo.put(cloumnsName[i], CommonsUtil.getIntData(o[i]));
					} else {
						tjo.put(cloumnsName[i], CommonsUtil
								.getClearNullData(o[i]));
					}
				}
				jsonArr.add(tjo);
			}
		}
		tjo = new JSONObject();
		tjo.put("Rows", jsonArr);
		tjo.put("Total", total);
		map.put("json", tjo);
		return map;
	}

	public HashMap<String, Object> searchSagdRPD1(String oilNumber,
			String ghmc, String well, String area, String startDate,
			String endDate, int pageNo, String sort, String order,
			int rowsPerpage, String totalNumf, String search, String BZHF)
			throws Exception {
		String cloums = "select ";

		String formTable = " from PC_RPD_WELL_SAGDD_AUTO_v a where 1=1 ";
		String totalNum = "select count(*) ";
		HashMap<String, Object> map = new HashMap<String, Object>();

		if (!oilNumber.equals("") && oilNumber != null
				&& !oilNumber.equals("undefined") && !oilNumber.equals("全部")) {
			formTable = formTable + " and OILSTATIONNAME='" + oilNumber + "'";
		}
		if (!ghmc.equals("") && ghmc != null && !ghmc.equals("undefined")) {
			formTable = formTable + " and MANIFOLD = '" + ghmc + "'";
		}
		if (!well.equals("") && well != null && !well.equals("undefined")) {
			formTable = formTable + " and jhmc like '%" + well + "%'";
		}

		if (!area.equals("") && area != null && !area.equals("undefined")
				&& !area.equals("全部")) {
			formTable = formTable + " and qkmc like '%" + area + "%'";
		}
		if (!BZHF.equals("") && BZHF != null && !BZHF.equals("undefined")
				&& !BZHF.equals("全部")) {
			formTable = formTable + " and BZHF like '%" + BZHF + "%'";
		}

		Date today = new Date();
		String nowdate = DateBean.getTimeStr(today);

		if ("".equals(startDate)) {
			startDate = nowdate.substring(0, 10) + " 00:00:00";
		} else {
			startDate = startDate + " 00:00:00";
		}
		if ("".equals(endDate)) {
			endDate = nowdate.substring(0, 10) + " 23:59:59";
		} else {
			endDate = endDate + " 23:59:59";
		}
		formTable = formTable + " and REPORT_DATE between TO_DATE('"
				+ startDate + "','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"
				+ endDate + "','YYYY-MM-DD HH24:MI:SS')";
		// String[] cloumnsName =
		// {"WELL_CODE","OILSTATIONNAME","QKMC","BLOCKSTATIONNAME","MANIFOLD","BRANCHING_NAME","REPORT_DATE","P_PRESSURE_PRESS","P_PRESSURE_TEMP","P_LOOPED_PRESS","P_LOOPED_TEMP","P_DRIVEPIPE_PRESS","I_PRESSURE_PRESS","I_PRESSURE_TEMP","I_LOOPED_PRESS","I_LOOPED_TEMP","I_DRIVEPIPE_PRESS","NO1STEAM_TEMP","NO1STEAM_PRESS","NO1STEAM_DIFP","NO1INSTANT_FLOW","LOWSUM_FLOW1","NO1INSTANT_DRYNESS","NO1CONTROL_STATUS","NO1CONTROL_APERTURE","NO1FLOW_SET","NO2STEAM_TEMP","NO2STEAM_PRESS","NO2STEAM_DIFP","NO2INSTANT_FLOW","LOWSUM_FLOW2","NO2INSTANT_DRYNESS","NO2CONTROL_STATUS","NO2CONTROL_APERTURE","NO2FLOW_SET","P_PRESS1","P_PRESS2","P_TEMP1","P_TEMP2","P_TEMP3","P_TEMP4","P_TEMP5","P_TEMP6","P_TEMP7","P_TEMP8","P_TEMP9","P_TEMP10","P_TEMP11","P_TEMP12","NOW_LOAD","MOTOR_PRESS_A","MOTOR_TEMP_A","MOTOR_PRESS_B","MOTOR_TEMP_B","MOTOR_PRESS_C","MOTOR_TEMP_C","CLIQUE_CONTROL_APERTURE2","ACTIVE_ENERGY","IDLE_ENERGY","POWER_FACTOR","JIG_FREQUENCY","STROKE","PUMPING_RUNNING_STATE","MOTORSTATUS","CONVERSION_PRESS","NOW_FREQUENCY","CONVERSION_ELECTRICITY","MOTOR_MODE","CLIQUE_CONTROL_APERTURE1","RUNTIME","DAILY_OUTPUT","DAILY_POWER_CONSUMPTIVE","DAILY_CUMULATIVE_STEAM1","DAILY_CUMULATIVE_STEAM2","REMARK","ORG_ID","A2ID","SAGDID"};
		String cloumnsName[];
		if ("search".equals(search)) {
			// String[] cloumnsName1 = {"REPORT_DATE"
			// ,"JHMC","I_INJE_TIME_RATION","I_PROC_TIME_RATION","I_P_FLOW_NIPPLE","I_PRESSURE_PRESS","I_PRESSURE_TEMP","I_I_FLOW_NIPPLE","I_LOOPED_PRESS","I_LOOPED_TEMP","I_DRIVEPIPE_PRESS","I_DAILY_OUTPUT","I_DAILY_OIL_OUTPUT","I_WATER_RATION","I_STEAM_FLOW","I_DAILY_CUMULATIVE_STEAM","I_REMARK","P_INJE_TIME_RATION","P_PROC_TIME_RATION","RUNTIME","PUMP_ERROR_TIME","PUMP_DIAMETER","STROKE","JIG_FREQUENCY","P_P_FLOW_NIPPLE","P_PRESSURE_PRESS","P_PRESSURE_TEMP","P_I_FLOW_NIPPLE","P_LOOPED_PRESS","P_LOOPED_TEMP","P_DRIVEPIPE_PRESS","P_DAILY_OUTPUT","P_DAILY_OIL_OUTPUT","P_WATER_RATION","P_STEAM_FLOW","P_DAILY_CUMULATIVE_STEAM","P_TEMP1","P_TEMP2","P_TEMP3","P_TEMP4","P_TEMP5","P_TEMP6","P_TEMP7","P_TEMP8","P_TEMP9","P_TEMP10","P_TEMP11","P_TEMP12","REMARK","SAGDDID","SAGDID","VERIFIER","VERIFY_TIME","RLAST_OPER","RLAST_ODATE","P_BACK_PRES","I_BACK_PRES","containing_water_flg","ORG_ID","P_STIMU_VALUE",
			// "P_SHUTIN_VALUE","P_SHUTIN_TIME","P_OPEN_TIME","I_STIMU_VALUE","I_SHUTIN_VALUE","I_SHUTIN_TIME","I_OPEN_TIME"};
			String[] cloumnsName1 = { "REPORT_DATE", "JHMC",
					"I_INJE_TIME_RATION", "I_PROC_TIME_RATION",
					"I_P_FLOW_NIPPLE", "I_PRESSURE_PRESS", "I_PRESSURE_TEMP",
					"I_I_FLOW_NIPPLE", "I_LOOPED_PRESS", "I_LOOPED_TEMP",
					"I_BACK_PRES", "I_DRIVEPIPE_PRESS", "I_DAILY_OUTPUT",
					"I_DAILY_OIL_OUTPUT", "I_WATER_RATION", "NO1INSTANT_FLOW",
					"I_DAILY_CUMULATIVE_STEAM", "I_REMARK",
					"P_INJE_TIME_RATION", "P_PROC_TIME_RATION", "RUNTIME",
					"PUMP_ERROR_TIME", "PUMP_DIAMETER", "STROKE",
					"JIG_FREQUENCY", "P_P_FLOW_NIPPLE", "P_PRESSURE_PRESS",
					"P_PRESSURE_TEMP", "P_I_FLOW_NIPPLE", "P_LOOPED_PRESS",
					"P_LOOPED_TEMP", "P_BACK_PRES", "P_DRIVEPIPE_PRESS",
					"P_DAILY_OUTPUT", "P_DAILY_OIL_OUTPUT", "P_WATER_RATION",
					"NO2INSTANT_FLOW", "P_DAILY_CUMULATIVE_STEAM", "P_TEMP1",
					"P_TEMP2", "P_TEMP3", "P_TEMP4", "P_TEMP5", "P_TEMP6",
					"P_TEMP7", "P_TEMP8", "P_TEMP9", "P_TEMP10", "P_TEMP11",
					"P_TEMP12", "REMARK", "RLAST_OPER", "RLAST_ODATE",
					"VERIFIER", "VERIFY_TIME", "SAGDDID", "SAGDID", "BBSX",
					"BZHF"
			// ,"ORG_ID","CONTAINING_WATER_FLG"
			};
			cloumnsName = cloumnsName1;
			// }else if ("export".equals(totalNumf)){
			// String[] cloumnsName3 =
			// {"REPORT_DATE","JHMC","I_INJE_TIME_RATION","I_PROC_TIME_RATION","I_P_FLOW_NIPPLE","I_PRESSURE_PRESS","I_PRESSURE_TEMP","I_I_FLOW_NIPPLE","I_LOOPED_PRESS","I_LOOPED_TEMP","I_BACK_PRES","I_DRIVEPIPE_PRESS","I_DAILY_OUTPUT","I_DAILY_OIL_OUTPUT","I_WATER_RATION","I_STEAM_FLOW","I_DAILY_CUMULATIVE_STEAM","I_REMARK","P_INJE_TIME_RATION","P_PROC_TIME_RATION","RUNTIME","PUMP_ERROR_TIME","PUMP_DIAMETER","STROKE","JIG_FREQUENCY","P_P_FLOW_NIPPLE","P_PRESSURE_PRESS","P_PRESSURE_TEMP","P_I_FLOW_NIPPLE","P_LOOPED_PRESS","P_LOOPED_TEMP","P_BACK_PRES","P_DRIVEPIPE_PRESS","P_DAILY_OUTPUT","P_DAILY_OIL_OUTPUT","P_WATER_RATION","P_STEAM_FLOW","P_DAILY_CUMULATIVE_STEAM","P_TEMP1","P_TEMP2","P_TEMP3","P_TEMP4","P_TEMP5","P_TEMP6","P_TEMP7","P_TEMP8","P_TEMP9","P_TEMP10","P_TEMP11","P_TEMP12","REMARK"};
			// cloumnsName = cloumnsName3;
		} else {
			// String[] cloumnsName2 =
			// {"REPORT_DATE","JHMC","I_INJE_TIME_RATION","I_PROC_TIME_RATION","I_P_FLOW_NIPPLE","I_PRESSURE_PRESS","I_PRESSURE_TEMP","I_I_FLOW_NIPPLE","I_LOOPED_PRESS","I_LOOPED_TEMP","I_DRIVEPIPE_PRESS","I_DAILY_OUTPUT","I_DAILY_OIL_OUTPUT","I_WATER_RATION","I_STEAM_FLOW","I_DAILY_CUMULATIVE_STEAM","I_REMARK","P_INJE_TIME_RATION","P_PROC_TIME_RATION","RUNTIME","PUMP_ERROR_TIME","PUMP_DIAMETER","STROKE","JIG_FREQUENCY","P_P_FLOW_NIPPLE","P_PRESSURE_PRESS","P_PRESSURE_TEMP","P_I_FLOW_NIPPLE","P_LOOPED_PRESS","P_LOOPED_TEMP","P_DRIVEPIPE_PRESS","P_DAILY_OUTPUT","P_DAILY_OIL_OUTPUT","P_WATER_RATION","P_STEAM_FLOW","P_DAILY_CUMULATIVE_STEAM","P_TEMP1","P_TEMP2","P_TEMP3","P_TEMP4","P_TEMP5","P_TEMP6","P_TEMP7","P_TEMP8","P_TEMP9","P_TEMP10","P_TEMP11","P_TEMP12","REMARK","SAGDDID","SAGDID","VERIFIER","VERIFY_TIME","RLAST_OPER","RLAST_ODATE","P_BACK_PRES","I_BACK_PRES","containing_water_flg","ORG_ID","P_SHUTIN_TIME","P_OPEN_TIME","I_SHUTIN_CODE","I_SHUTIN_TIME","I_OPEN_TIME"};
			String[] cloumnsName2 = { "REPORT_DATE", "JHMC",
					"I_INJE_TIME_RATION", "I_PROC_TIME_RATION",
					"I_P_FLOW_NIPPLE", "I_PRESSURE_PRESS", "I_PRESSURE_TEMP",
					"I_I_FLOW_NIPPLE", "I_LOOPED_PRESS", "I_LOOPED_TEMP",
					"I_BACK_PRES", "I_DRIVEPIPE_PRESS", "I_DAILY_OUTPUT",
					"I_DAILY_OIL_OUTPUT", "I_WATER_RATION", "NO1INSTANT_FLOW",
					"I_DAILY_CUMULATIVE_STEAM", "I_REMARK",
					"P_INJE_TIME_RATION", "P_PROC_TIME_RATION", "RUNTIME",
					"PUMP_ERROR_TIME", "PUMP_DIAMETER", "STROKE",
					"JIG_FREQUENCY", "P_P_FLOW_NIPPLE", "P_PRESSURE_PRESS",
					"P_PRESSURE_TEMP", "P_I_FLOW_NIPPLE", "P_LOOPED_PRESS",
					"P_LOOPED_TEMP", "P_BACK_PRES", "P_DRIVEPIPE_PRESS",
					"P_DAILY_OUTPUT", "P_DAILY_OIL_OUTPUT", "P_WATER_RATION",
					"NO2INSTANT_FLOW", "P_DAILY_CUMULATIVE_STEAM", "P_TEMP1",
					"P_TEMP2", "P_TEMP3", "P_TEMP4", "P_TEMP5", "P_TEMP6",
					"P_TEMP7", "P_TEMP8", "P_TEMP9", "P_TEMP10", "P_TEMP11",
					"P_TEMP12", "REMARK", "RLAST_OPER", "RLAST_ODATE",
					"VERIFIER", "VERIFY_TIME", "SAGDDID", "SAGDID", "ORG_ID",
					"CONTAINING_WATER_FLG", "BBSX", "BZHF" };
			cloumnsName = cloumnsName2;
		}
		// String[] cloumnsName =
		// {"REPORT_DATE","JHMC","I_PROC_TIME_RATION","I_P_FLOW_NIPPLE","I_PRESSURE_PRESS","I_PRESSURE_TEMP","I_I_FLOW_NIPPLE","I_LOOPED_PRESS","I_LOOPED_TEMP","I_DRIVEPIPE_PRESS","I_DAILY_OUTPUT","I_DAILY_OIL_OUTPUT","I_WATER_RATION","I_STEAM_FLOW","I_DAILY_CUMULATIVE_STEAM","I_REMARK","P_PROC_TIME_RATION","RUNTIME","PUMP_ERROR_TIME","PUMP_DIAMETER","STROKE","JIG_FREQUENCY","P_P_FLOW_NIPPLE","P_PRESSURE_PRESS","P_PRESSURE_TEMP","P_I_FLOW_NIPPLE","P_LOOPED_PRESS","P_LOOPED_TEMP","P_DRIVEPIPE_PRESS","P_DAILY_OUTPUT","P_DAILY_OIL_OUTPUT","P_WATER_RATION","P_STEAM_FLOW","P_DAILY_CUMULATIVE_STEAM","P_TEMP1","P_TEMP2","P_TEMP3","P_TEMP4","P_TEMP5","P_TEMP6","P_TEMP7","P_TEMP8","P_TEMP9","P_TEMP10","P_TEMP11","P_TEMP12","P_PRESS1","P_PRESS2","REMARK","SAGDDID","SAGDID","VERIFIER","VERIFY_TIME","RLAST_OPER","RLAST_ODATE","P_BACK_PRES","I_BACK_PRES","ORG_ID"};
		String[] cloumnsName1 = { "REPORT_DATE", "JHMC", "I_INJE_TIME_RATION",
				"I_PROC_TIME_RATION", "I_P_FLOW_NIPPLE", "I_PRESSURE_PRESS",
				"I_PRESSURE_TEMP", "I_I_FLOW_NIPPLE", "I_LOOPED_PRESS",
				"I_LOOPED_TEMP", "I_BACK_PRES", "I_DRIVEPIPE_PRESS",
				"I_DAILY_OUTPUT", "I_DAILY_OIL_OUTPUT", "I_WATER_RATION",
				"NO1INSTANT_FLOW", "I_DAILY_CUMULATIVE_STEAM", "I_REMARK",
				"P_INJE_TIME_RATION", "P_PROC_TIME_RATION", "RUNTIME",
				"PUMP_ERROR_TIME", "PUMP_DIAMETER", "STROKE", "JIG_FREQUENCY",
				"P_P_FLOW_NIPPLE", "P_PRESSURE_PRESS", "P_PRESSURE_TEMP",
				"P_I_FLOW_NIPPLE", "P_LOOPED_PRESS", "P_LOOPED_TEMP",
				"P_BACK_PRES", "P_DRIVEPIPE_PRESS", "P_DAILY_OUTPUT",
				"P_DAILY_OIL_OUTPUT", "P_WATER_RATION", "NO2INSTANT_FLOW",
				"P_DAILY_CUMULATIVE_STEAM", "P_TEMP1", "P_TEMP2", "P_TEMP3",
				"P_TEMP4", "P_TEMP5", "P_TEMP6", "P_TEMP7", "P_TEMP8",
				"P_TEMP9", "P_TEMP10", "P_TEMP11", "P_TEMP12", "REMARK" };
		if ("export".equals(totalNumf)) {
			for (String cn : cloumnsName1) {
				cloums += " " + cn + ",";
			}

			cloums = cloums.substring(0, cloums.length() - 1);
		} else {
			for (String cn : cloumnsName) {
				if ("export".equals(totalNumf) && "SAGDDID".equals(cn)) {
					break;
				}
				if (!"REPORT_DATE".equals(cn)) {
					if ("RLAST_ODATE".equals(cn)) {
						cloums += ", to_char(RLAST_ODATE,'YYYY-MM-DD HH24:MI:SS') as RLAST_ODATE";
					} else if ("I_SHUTIN_TIME".equals(cn)) {
						cloums += ", to_char(I_SHUTIN_TIME,'YYYY-MM-DD HH24:MI') as I_SHUTIN_TIME";
					} else if ("I_OPEN_TIME".equals(cn)) {
						cloums += ", to_char(I_OPEN_TIME,'YYYY-MM-DD HH24:MI') as I_OPEN_TIME";
					} else if ("P_SHUTIN_TIME".equals(cn)) {
						cloums += ", to_char(P_SHUTIN_TIME,'YYYY-MM-DD HH24:MI') as P_SHUTIN_TIME";
					} else if ("P_OPEN_TIME".equals(cn)) {
						cloums += ", to_char(P_OPEN_TIME,'YYYY-MM-DD HH24:MI') as P_OPEN_TIME";
					} else if ("VERIFY_TIME".equals(cn)) {
						cloums += ", to_char(VERIFY_TIME,'YYYY-MM-DD HH24:MI') as VERIFY_TIME";
					} else {
						cloums += "," + cn;
					}
					continue;
				}
				// cloums +=
				// "to_char(REPORT_DATE,'YYYY-MM-DD HH24:MI:SS') as REPORT_DATE";

				cloums += "REPORT_DATE ";
			}
		}
		String wellInfo = cloums + formTable;
		// 获取总条数
		int total = 0;
		if ("1".equals(totalNum)) {
			total = 1;
		} else {
			totalNum += formTable;
			total = sagdDao.getCounts(totalNum);
		}
		if ("totalNum".equals(totalNumf)) {
			map.put("totalNum", total + "");
			return map;
		}
		// 排序
		if (!"".equals(sort) && !"JHMC".equals(sort)) {
			for (String cloumn : cloumnsName) {
				if (cloumn.equals(sort)) {
					wellInfo += " order by " + cloumn + " " + order;
					break;
				}
			}
		} else {
			wellInfo += " order by BBSX ";
		}
		List<Object[]> lo = null;
		List<Object[]> lobj = new ArrayList<Object[]>();
		;
		try {
			if ("export".equals(totalNumf)) {
				lo = sagdDao.queryInfo(wellInfo);
				if (lo != null && 0 < lo.size()) {
					for (int j = 0; j < lo.size(); j++) {
						Object[] firData = lo.get(j);
						Object[] param = null;
						param = new Object[firData.length];
						for (int i = 0; i < firData.length; i++) {
							if (i == 2 || i == 3 || i == 4 || i == 7 || i == 11
									|| i == 14 || i == 18 || i == 19 || i == 20
									|| i == 21 || i == 24 || i == 25 || i == 28
									|| i == 35) {
								param[i] = CommonsUtil
										.getNotOneDataZ(firData[i]);
								// tjo.put(cloumnsName[i],
								// CommonsUtil.getNotOneDataZ( o[i]));
							} else if (i == 5 || i == 8 || i == 10 || i == 12
									|| i == 13 || i == 15 || i == 26 || i == 29
									|| i == 31 || i == 32 || i == 33 || i == 34
									|| i == 36) {
								// tjo.put(cloumnsName[i],
								// CommonsUtil.getNotTwoDataZero( o[i]));
								param[i] = CommonsUtil
										.getNotTwoDataZero(firData[i]);
							} else if (i == 6 || i == 9 || i == 16 || i == 22
									|| i == 23 || i == 27 || i == 30 || i == 37
									|| i == 37 || i == 38 || i == 39 || i == 40
									|| i == 41 || i == 42 || i == 43 || i == 44
									|| i == 45 || i == 46 || i == 47 || i == 48
									|| i == 49) {
								// tjo.put(cloumnsName[i],
								// CommonsUtil.getIntData( o[i]));
								param[i] = CommonsUtil.getIntData(firData[i]);
							} else {
								// tjo.put(cloumnsName[i],
								// CommonsUtil.getClearNullData(o[i]));
								param[i] = firData[i];
							}
							// param[j] = firData[j];
						}
						lobj.add(param);
					}

				}
				map.put("list", lobj);
				return map;
			} else {
				// 计算分页
				PageControl control = new PageControl();
				// 当前页
				control.setInt_num(rowsPerpage);
				control.init(pageNo, total);
				// 开始条数
				int start = control.getStart() - 1;
				lo = sagdDao.searchSagdRPD(wellInfo, start, rowsPerpage,
						cloumnsName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONObject tjo = null;
		JSONArray jsonArr = null;
		if (lo != null && 0 < lo.size()) {
			// 生成树节点json文档
			jsonArr = new JSONArray();
			for (Object[] o : lo) {
				tjo = new JSONObject();

				for (int i = 0; i < o.length; i++) {
					// if (o[i] == null||o[i].equals("null")) {
					// o[i] = "";
					// }
					if (i == 2 || i == 3 || i == 4 || i == 7 || i == 11
							|| i == 14 || i == 18 || i == 19 || i == 20
							|| i == 21 || i == 24 || i == 25 || i == 28
							|| i == 35) {
						tjo.put(cloumnsName[i], CommonsUtil
								.getNotOneDataZ(o[i]));
					} else if (i == 5 || i == 8 || i == 10 || i == 12
							|| i == 13 || i == 15 || i == 26 || i == 29
							|| i == 31 || i == 32 || i == 33 || i == 34
							|| i == 36) {
						tjo.put(cloumnsName[i], CommonsUtil
								.getNotTwoDataZero(o[i]));
					} else if (i == 6 || i == 9 || i == 16 || i == 22
							|| i == 23 || i == 27 || i == 30 || i == 37
							|| i == 37 || i == 38 || i == 39 || i == 40
							|| i == 41 || i == 42 || i == 43 || i == 44
							|| i == 45 || i == 46 || i == 47 || i == 48
							|| i == 49) {
						tjo.put(cloumnsName[i], CommonsUtil.getIntData(o[i]));
					} else {
						tjo.put(cloumnsName[i], CommonsUtil
								.getClearNullData(o[i]));
					}
				}
				jsonArr.add(tjo);
			}
		}
		tjo = new JSONObject();
		tjo.put("Rows", jsonArr);
		tjo.put("Total", total);
		map.put("json", tjo);
		return map;
	}

	public boolean removeStationInfo(String arg, String orgid) {
		String[] sqls = new String[2];
		sqls[0] = " DELETE from pc_cd_well_sagd_t d where d.sagdid = '" + arg
				+ "'";
		sqls[1] = " DELETE from pc_cd_org_t t where t.org_id = '" + orgid + "'";

		return sagdDao.removeStationInfo(sqls);
	}

	public JSONArray queryAreablockInfo(String arg) {
		JSONArray jsonArr = null;
		JSONObject jsobj = null;
		String sql = "select distinct a.qkid,a.qkmc from pc_cd_area_info_t a order by nlssort(a.qkmc,'NLS_SORT=SCHINESE_STROKE_M')";
		if ("sagd".equals(arg)) {
			sql = "select distinct a.qkid,a.qkmc from pc_cd_area_info_t a left join pc_cd_well_sagd_t r on a.qkid=r.qkid where a.qkid=r.qkid order by nlssort(a.qkmc,'NLS_SORT=SCHINESE_STROKE_M')";

			// sql =
			// "select r.ORG_ID,r.STRUCTURENAME from PC_CD_MANIFOLDTREE_V r where r.STRUCTURETYPE='4' and structurename in('SAGD采油一站','SAGD采油二站')  order by nlssort(r.STRUCTURENAME,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		List<Object[]> dataSet = sagdDao.queryInfo(sql);
		if (dataSet != null && dataSet.size() > 0) {
			// 生成树节点json文档
			jsonArr = new JSONArray();
			for (Object[] entry : dataSet) {
				jsobj = new JSONObject();
				jsobj.put("text", entry[1]);
				jsobj.put("id", entry[0]);
				jsonArr.add(jsobj);
			}
			if ("a".equals(arg)) {
				return jsonArr;
			}
			jsobj = new JSONObject();
			jsobj.put("text", "全部");
			jsobj.put("id", 1);
			jsonArr.add(jsobj);
			return jsonArr;
		}
		jsobj = new JSONObject();
		jsobj.put("text", "");
		jsobj.put("id", "");
		jsonArr = new JSONArray();
		jsonArr.add(jsobj);
		return jsonArr;
	}

	public JSONArray queryoilationnameInfo(String arg) {
		JSONArray jsonArr = null;
		JSONObject jsobj = null;
		String sql = "select r.ORG_ID,r.STRUCTURENAME from PC_CD_MANIFOLDTREE_V r where r.STRUCTURETYPE='4' and structurename in('SAGD采油一站','SAGD采油二站')  order by nlssort(r.STRUCTURENAME,'NLS_SORT=SCHINESE_STROKE_M')";
		if ("sagd".equals(arg)) {
			// sql =
			// "select distinct a.qkid,a.qkmc from pc_cd_area_info_t a left join pc_cd_well_sagd_t r on a.qkid=r.qkid where a.qkid=r.qkid order by nlssort(a.qkmc,'NLS_SORT=SCHINESE_STROKE_M')";

			sql = "select r.ORG_ID,r.STRUCTURENAME from PC_CD_MANIFOLDTREE_V r where r.STRUCTURETYPE='4' and structurename in('SAGD采油一站','SAGD采油二站')  order by nlssort(r.STRUCTURENAME,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		List<Object[]> dataSet = sagdDao.queryInfo(sql);
		if (dataSet != null && dataSet.size() > 0) {
			// 生成树节点json文档
			jsonArr = new JSONArray();
			for (Object[] entry : dataSet) {
				jsobj = new JSONObject();
				jsobj.put("text", entry[1]);
				jsobj.put("id", entry[0]);
				jsonArr.add(jsobj);
			}
			if ("a".equals(arg)) {
				return jsonArr;
			}
			jsobj = new JSONObject();
			jsobj.put("text", "全部");
			jsobj.put("id", 1);
			jsonArr.add(jsobj);
			return jsonArr;
		}
		jsobj = new JSONObject();
		jsobj.put("text", "");
		jsobj.put("id", "");
		jsonArr = new JSONArray();
		jsonArr.add(jsobj);
		return jsonArr;
	}

	public JSONArray searchoilationnameInfo(String arg) {
		JSONArray jsonArr = null;
		JSONObject jsobj = null;
		String sql = "select r.ORG_ID,r.STRUCTURENAME from PC_CD_MANIFOLDTREE_V r where r.STRUCTURETYPE='4' and structurename in('SAGD采油一站','SAGD采油二站')  order by nlssort(r.STRUCTURENAME,'NLS_SORT=SCHINESE_STROKE_M')";
		if ("sagd".equals(arg)) {
			// sql =
			// "select distinct a.qkid,a.qkmc from pc_cd_area_info_t a left join pc_cd_well_sagd_t r on a.qkid=r.qkid where a.qkid=r.qkid order by nlssort(a.qkmc,'NLS_SORT=SCHINESE_STROKE_M')";

			sql = "select r.ORG_ID,r.STRUCTURENAME from PC_CD_MANIFOLDTREE_V r where r.STRUCTURETYPE='4' and structurename in('SAGD采油一站','SAGD采油二站')  order by nlssort(r.STRUCTURENAME,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		List<Object[]> dataSet = sagdDao.queryInfo(sql);
		if (dataSet != null && dataSet.size() > 0) {
			// 生成树节点json文档
			jsonArr = new JSONArray();
			for (Object[] entry : dataSet) {
				jsobj = new JSONObject();
				jsobj.put("text", entry[1]);
				jsobj.put("id", entry[0]);
				jsonArr.add(jsobj);
			}
		}
		jsobj = new JSONObject();
		jsobj.put("text", "");
		jsobj.put("id", "");
		jsonArr = new JSONArray();
		jsonArr.add(jsobj);
		return jsonArr;
	}

	public JSONArray searchAreablockInfo(String arg) {
		JSONArray jsonArr = null;
		JSONObject jsobj = null;
		String sql = "select distinct a.qkid,a.qkmc from pc_cd_area_info_t a order by nlssort(a.qkmc,'NLS_SORT=SCHINESE_STROKE_M')";
		if ("sagd".equals(arg)) {
			sql = "select distinct a.qkid,a.qkmc from pc_cd_area_info_t a left join pc_cd_well_sagd_t r on a.qkid=r.qkid where a.qkid=r.qkid order by nlssort(a.qkmc,'NLS_SORT=SCHINESE_STROKE_M')";
			// sql =
			// "select r.ORG_ID,r.STRUCTURENAME from PC_CD_MANIFOLDTREE_V r where r.STRUCTURETYPE='4' and structurename in('SAGD采油一站','SAGD采油二站')  order by nlssort(r.STRUCTURENAME,'NLS_SORT=SCHINESE_STROKE_M')";
		}

		List<Object[]> dataSet = sagdDao.queryInfo(sql);
		if (dataSet != null && dataSet.size() > 0) {
			// 生成树节点json文档
			jsonArr = new JSONArray();
			for (Object[] entry : dataSet) {
				jsobj = new JSONObject();
				jsobj.put("text", entry[1]);
				jsobj.put("id", entry[0]);
				jsonArr.add(jsobj);
			}
		}
		jsobj = new JSONObject();
		jsobj.put("text", "");
		jsobj.put("id", "");
		jsonArr = new JSONArray();
		jsonArr.add(jsobj);
		return jsonArr;
	}

	public JSONArray queryStationInfo(String areaid) {
		JSONArray jsonArr = null;
		JSONObject jsobj = null;
		// 按采油站查询 注转站
		// oilid 不能等于1

		String sql = " select s.org_id,s.blockstation_name from pc_cd_station_t s where s.qkid='"
				+ areaid
				+ "' order by nlssort(s.blockstation_name,'NLS_SORT=SCHINESE_STROKE_M')";
		if ("".equals(areaid)) {// 查询所有含锅炉的注转站
			sql = "select r.ORG_ID,r.STRUCTURENAME from PC_CD_SAGDTREE_V r where r.STRUCTURETYPE='7' order by nlssort(r.STRUCTURENAME,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		if ("all".equals(areaid)) {
			sql = "select s.org_id,s.blockstation_name from pc_cd_station_t s order by nlssort(s.blockstation_name,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		if ("noall".equals(areaid)) {
			sql = "select s.org_id,s.blockstation_name from pc_cd_station_t s  order by nlssort(s.blockstation_name,'NLS_SORT=SCHINESE_STROKE_M')";
		}

		// where bs_type<>2
		List<Object[]> dataSet = sagdDao.queryInfo(sql);
		if (dataSet != null && dataSet.size() > 0) {
			// 生成树节点json文档
			jsonArr = new JSONArray();
			for (Object[] entry : dataSet) {
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

	// sagd井级联
	public JSONArray queryGhmcInfo(String oilationnameid) {
		JSONArray jsonArr = null;
		JSONObject jsobj = null;
		// 按采油站查询 注转站
		// oilid 不能等于1
		// System.out.println("￥￥￥￥"+cyzid+"!!!!!!!!!!!!");
		String sql = "select  m.org_id, m.ghmc from pc_cd_manifold_t m  "
				+ " left join  pc_cd_org_t o on o.org_id=m.org_id"
				+ " left join pc_cd_org_t o1 on o.pid=o1.org_id"
				+ " left join pc_cd_org_t o2 on o1.pid=o2.org_id  where o2.org_id = '"
				+ oilationnameid
				+ "' order by  nlssort(m.ghmc,'NLS_SORT=SCHINESE_STROKE_M')";
		if ("".equals(oilationnameid)) {// 查询所有含锅炉的注转站
			sql = "select distinct r.gh_id,r.manifold  from pc_cd_well_sagd_v r order by nlssort(r.manifold,'NLS_SORT=SCHINESE_STROKE_M')";
		}

		// where bs_type<>2
		List<Object[]> dataSet = sagdDao.queryInfo(sql);
		if (dataSet != null && dataSet.size() > 0) {
			// 生成树节点json文档
			jsonArr = new JSONArray();
			for (Object[] entry : dataSet) {
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

	public JSONArray queryWellNameInfo(String arg) {
		JSONArray jsonArr = null;
		JSONObject jsobj = null;
		// String sql =
		// "select w.sagdid,w.jhmc from PC_CD_WELL_SAGD_T w left join (select o1.org_id from pc_cd_org_t o1 left join (select o.org_id from pc_cd_org_t o where o.pid='"
		// + arg +
		// "') o2 on o1.pid=o2.org_id where o1.pid=o2.org_id) o3 on o3.org_id=w.org_id where o3.org_id=w.org_id order by nlssort(w.jhmc,'NLS_SORT=SCHINESE_STROKE_M')";

		String sql = "select w.sagdid,w.jhmc from pc_cd_well_sagd_v w where gh_id = '"
				+ arg
				+ "' order by nlssort(w.jhmc,'NLS_SORT=SCHINESE_STROKE_M')";
		if ("".equals(arg)) {// 全部的锅炉
			sql = "select w.sagdid,w.jhmc from PC_CD_WELL_SAGD_T w order by nlssort(w.jhmc,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		List<Object[]> dataSet = sagdDao.queryInfo(sql);
		if (dataSet != null && dataSet.size() > 0) {
			// 生成树节点json文档
			jsonArr = new JSONArray();
			for (Object[] entry : dataSet) {
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

	public JSONArray queryWellNameInfo1(String arg) {
		JSONArray jsonArr = null;
		JSONObject jsobj = null;
		String sql = "select w.sagdid,w.jhmc from PC_CD_WELL_SAGD_T w left join (select o1.org_id from pc_cd_org_t o1 left join (select o.org_id from pc_cd_org_t o where o.pid='"
				+ arg
				+ "') o2 on o1.pid=o2.org_id where o1.pid=o2.org_id) o3 on o3.org_id=w.org_id where o3.org_id=w.org_id order by nlssort(w.jhmc,'NLS_SORT=SCHINESE_STROKE_M')";
		if ("".equals(arg)) {// 全部的锅炉
			sql = "select w.sagdid,w.jhmc from PC_CD_WELL_SAGD_T w order by nlssort(w.jhmc,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		List<Object[]> dataSet = sagdDao.queryInfo(sql);
		if (dataSet != null && dataSet.size() > 0) {
			// 生成树节点json文档
			jsonArr = new JSONArray();
			for (Object[] entry : dataSet) {
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

	public JSONArray queryDescInfo(String arg) {
		JSONArray jsonArr = null;
		JSONObject jsobj = null;
		String sql = "select t.stimu_code,t.p_description from PC_PK_STIMU_TYPE_T t order by nlssort(t.p_description,'NLS_SORT=SCHINESE_STROKE_M')";
		if ("".equals(arg)) {// 措施数据
			sql = "select t.shutin_code,t.p_description from PC_PK_SHUTIN_TYPE_T t order by nlssort(t.p_description,'NLS_SORT=SCHINESE_STROKE_M')";
		}
		List<Object[]> dataSet = sagdDao.queryInfo(sql);
		if (dataSet != null && dataSet.size() > 0) {
			// 生成树节点json文档
			jsonArr = new JSONArray();
			for (Object[] entry : dataSet) {
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

	public JSONObject cascadeInit() {
		String areaSql = "select distinct a.qkid,a.qkmc from pc_cd_area_info_t a left join pc_cd_well_sagd_t r on a.qkid=r.qkid where a.qkid=r.qkid order by nlssort(a.qkmc,'NLS_SORT=SCHINESE_STROKE_M')";
		String stationSql = "select r.ORG_ID,r.STRUCTURENAME from PC_CD_SAGDTREE_V r where r.STRUCTURETYPE='7' order by nlssort(r.STRUCTURENAME,'NLS_SORT=SCHINESE_STROKE_M')";
		String wellSql = "select w.sagdid,w.jhmc from PC_CD_WELL_SAGD_T w order by nlssort(w.jhmc,'NLS_SORT=SCHINESE_STROKE_M')";

		JSONArray areaJsonArr = null;
		JSONArray stationArr = null;
		JSONArray wellArr = null;
		JSONObject jsobj = null;
		List<Object[]> areaSet = sagdDao.queryInfo(areaSql);
		List<Object[]> stationSet = sagdDao.queryInfo(stationSql);
		List<Object[]> wellSet = sagdDao.queryInfo(wellSql);

		if (areaSet != null && areaSet.size() > 0) {
			areaJsonArr = new JSONArray();
			for (Object[] entry : areaSet) {
				jsobj = new JSONObject();
				jsobj.put("text", entry[1]);
				jsobj.put("id", entry[0]);
				areaJsonArr.add(jsobj);
			}
			jsobj = new JSONObject();
			jsobj.put("text", "全部");
			jsobj.put("id", 1);
			areaJsonArr.add(jsobj);
		}

		if (stationSet != null && stationSet.size() > 0) {
			stationArr = new JSONArray();
			for (Object[] entry : stationSet) {
				jsobj = new JSONObject();
				jsobj.put("text", entry[1]);
				jsobj.put("id", entry[0]);
				stationArr.add(jsobj);
			}
		}

		if (wellSet != null && wellSet.size() > 0) {
			wellArr = new JSONArray();
			for (Object[] entry : wellSet) {
				jsobj = new JSONObject();
				jsobj.put("text", entry[1]);
				jsobj.put("id", entry[0]);
				wellArr.add(jsobj);
			}
		}

		jsobj = new JSONObject();
		jsobj.put("areatext", areaJsonArr);
		jsobj.put("stationtext", stationArr);
		jsobj.put("welltext", wellArr);
		return jsobj;
	}

	// sagd分站级联采油站-管汇-井
	public JSONObject cascadeInit1() {
		String oilationnameSql = "select r.ORG_ID,r.STRUCTURENAME from PC_CD_MANIFOLDTREE_V r where r.STRUCTURETYPE='4' and structurename in('SAGD采油一站','SAGD采油二站')  order by nlssort(r.STRUCTURENAME,'NLS_SORT=SCHINESE_STROKE_M')";
		String ghmcSql = "select distinct r.gh_id,r.manifold from pc_cd_well_sagd_v r order by nlssort(r.manifold,'NLS_SORT=SCHINESE_STROKE_M')";
		String wellSql = "select w.sagdid,w.jhmc from PC_CD_WELL_SAGD_T w order by nlssort(w.jhmc,'NLS_SORT=SCHINESE_STROKE_M')";

		JSONArray oilationnameArr = null;
		JSONArray ghmcArr = null;
		JSONArray wellArr = null;
		JSONObject jsobj = null;
		List<Object[]> oilationnameSet = sagdDao.queryInfo(oilationnameSql);
		List<Object[]> ghmcSet = sagdDao.queryInfo(ghmcSql);
		List<Object[]> wellSet = sagdDao.queryInfo(wellSql);

		if (oilationnameSet != null && oilationnameSet.size() > 0) {
			oilationnameArr = new JSONArray();
			for (Object[] entry : oilationnameSet) {
				jsobj = new JSONObject();
				jsobj.put("text", entry[1]);
				jsobj.put("id", entry[0]);
				oilationnameArr.add(jsobj);
			}
			jsobj = new JSONObject();
			jsobj.put("text", "全部");
			jsobj.put("id", 1);
			oilationnameArr.add(jsobj);
		}

		if (ghmcSet != null && ghmcSet.size() > 0) {
			ghmcArr = new JSONArray();
			for (Object[] entry : ghmcSet) {
				jsobj = new JSONObject();
				jsobj.put("text", entry[1]);
				jsobj.put("id", entry[0]);
				System.out.println("#############" + entry[0]
						+ "#################");
				ghmcArr.add(jsobj);
			}
		}

		if (wellSet != null && wellSet.size() > 0) {
			wellArr = new JSONArray();
			for (Object[] entry : wellSet) {
				jsobj = new JSONObject();
				jsobj.put("text", entry[1]);
				jsobj.put("id", entry[0]);
				wellArr.add(jsobj);
			}
		}

		jsobj = new JSONObject();
		jsobj.put("oilationnametext", oilationnameArr);
		jsobj.put("ghmctext", ghmcArr);
		jsobj.put("welltext", wellArr);
		return jsobj;
	}

	public List<PcCdWellSagdT> searchSagdname(String sagdid, String sagdwellname)
			throws Exception {
		List<PcCdWellSagdT> sagdList = null;
		String hql = " from  PcCdWellSagdT s where 1=1 ";
		if (sagdid != null && !"".equals(sagdid)) {
			hql += "and s.sagdid = '" + sagdid + "'";

		}
		if (sagdwellname != null && !"".equals(sagdwellname)) {
			hql += "and s.jhmc = '" + sagdwellname + "'";
		}
		sagdList = sagdDao.searchSagdname(hql);
		return sagdList;
	}

	public boolean addSagd(PcCdWellSagdT sagd) throws Exception {
		return sagdDao.save(sagd);
	}

	public boolean updateSagd(PcCdWellSagdT sagd) throws Exception {
		return sagdDao.updateSagd(sagd);
	}

	public List<PcCdServerNodeT> getServerNode(String id) throws Exception {
		List<PcCdServerNodeT> sertverList = null;

		String sql = "FROM PcCdServerNodeT s where 1=1 ";
		if (id != null && !"".equals(id)) {
			sql += " and s.scadaNode = '" + id + "'";
		}
		sertverList = sagdDao.searchServer(sql);

		return sertverList;
	}

	public boolean addOrUpdateSagddRPD(PcRpdWellSagddT prws) throws Exception {
		return sagdDao.addOrUpdateSagddRPD(prws);
	}

	public boolean removeSagddRPD(String sagddid) throws Exception {
		PcRpdWellSagddT prs = new PcRpdWellSagddT();
		prs = sagdDao.searchSagddRPD(sagddid);
		return sagdDao.removeSagddRPD(prs);
	}

	public PcRpdWellSagddT searchSagrd(String sagddid) throws Exception {
		return sagdDao.searchSagddRPD(sagddid);
	}

	public List<Object[]> searchSagdRPDview(String sagddid, String[] cloumnsName)
			throws Exception {
		String sql = "select ";
		for (int i = 0; i < cloumnsName.length; i++) {
			sql += " s." + cloumnsName[i] + ",";
		}
		sql = sql.substring(0, sql.length() - 1);
		sql += " from PC_RPD_WELL_SAGDD_v s where 1=1 and  s.SAGDDID = '"
				+ sagddid + "'";
		List<Object[]> sagdrpd = sagdDao.queryInfo(sql);

		return sagdrpd;
	}

	public List<Object[]> searchSagdLine(String sagdid, String prvarid,
			String startDate, String endDate) throws Exception {
		String formTable = "select to_char(ACQUISITION_TIME,'YYYY-MM-DD hh24:mi:ss') as ACQUISITION_TIME,"
				+ prvarid;
		formTable += "  from PC_RD_WELL_SAGD_V  where 1=1 and SAGDID='"
				+ sagdid + "'";

		Date today = new Date();
		String nowdate = DateBean.getTimeStr(today);

		if ("".equals(startDate)) {
			startDate = nowdate.substring(0, 10) + " 00:00:00";
		} else {
			if (startDate.length() == 10) {
				startDate = startDate + " 00:00:00";
			} else {
				startDate = startDate + ":00";
			}

		}
		if ("".equals(endDate)) {
			endDate = nowdate.substring(0, 10) + " 23:59:59";
		} else {
			if (endDate.length() == 10) {
				endDate = endDate + " 23:59:59";
			} else {
				endDate = endDate + ":00";
			}

		}
		formTable = formTable + " and ACQUISITION_TIME between TO_DATE('"
				+ startDate + "','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"
				+ endDate + "','YYYY-MM-DD HH24:MI:SS')";
		formTable = formTable + " ORDER BY ACQUISITION_TIME";
		List<Object[]> dataSet = sagdDao.queryInfo(formTable);
		return dataSet;
	}

	public List<Object[]> searchSagdLine2(String sagdid, String prvarid,
			String startDate, String endDate) throws Exception {
		String formTable = "select to_char(REPORT_DATE,'YYYY-MM-DD') as REPORT_DATE,"
				+ prvarid;
		formTable += "  from PC_RPD_WELL_SAGDD_V  where 1=1 and SAGDID='"
				+ sagdid + "'";

		Date today = new Date();
		String nowdate = DateBean.getTimeStr(today);

		if ("".equals(startDate)) {
			startDate = nowdate.substring(0, 10);
		}

		if ("".equals(endDate)) {
			endDate = nowdate.substring(0, 10);
		}
		formTable = formTable + " and REPORT_DATE between TO_DATE('"
				+ startDate + "','YYYY-MM-DD') and TO_DATE('" + endDate
				+ "','YYYY-MM-DD')";
		formTable = formTable + " ORDER BY REPORT_DATE";
		List<Object[]> dataSet = sagdDao.queryInfo(formTable);
		return dataSet;
	}

	public List<PcRpdWellSagddT> searchSagrdByName(String sagdid,
			String reportDate) throws Exception {
		List<PcRpdWellSagddT> sagdList = sagdDao
				.searchSagrdByName("From PcRpdWellSagddT where sagdid='"
						+ sagdid + "' and reportDate= to_date('" + reportDate
						+ "','yyyy-mm-dd')");
		return sagdList;
	}

	public JSONArray SearchPmode(String arg) throws Exception {
		JSONArray jsonArr = null;
		JSONObject jsobj = null;
		String sql = "select y.prod_mode , y.prod_mode_desc  from  PC_CD_PROD_MODE_T y ";
		List<Object[]> dataSet = sagdDao.searchPmode(sql);
		if (dataSet != null && dataSet.size() > 0) {
			// 生成树节点json文档
			jsonArr = new JSONArray();
			for (Object[] entry : dataSet) {
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

	public JSONArray SearchPstages(String arg) throws Exception {
		JSONArray jsonArr = null;
		JSONObject jsobj = null;
		String sql = "select x.prod_stages ,x.prod_stages_desc from  PC_CD_PROD_STAGES_T x  order by x.prod_stages_desc";
		List<Object[]> dataSet = sagdDao.searchPstages(sql);
		if (dataSet != null && dataSet.size() > 0) {
			// 生成树节点json文档
			jsonArr = new JSONArray();
			for (Object[] entry : dataSet) {
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

	public List<Object[]> SearchMode(String id, String name) throws Exception {

		String sql = "select y.prod_mode , y.prod_mode_desc  from  PC_CD_PROD_MODE_T y where 1=1 ";
		if (id != null && !"".equals(id)) {
			sql += " and y.prod_mode = '" + id + "'";
		}

		if (name != null && !"".equals(name)) {
			sql += " and y.prod_mode_desc = '" + name + "'";
		}

		return sagdDao.searchPmode(sql);
	}

	@Override
	public List<PcCdWellSagdT> searchSagdjhmc_s(String jhmcS) throws Exception {
		// TODO Auto-generated method stub
		List<PcCdWellSagdT> sagdList = null;
		String hql = " from  PcCdWellSagdT s where 1=1 ";
		if (jhmcS != null && !"".equals(jhmcS)) {
			hql += "and s.jhmc_s = '" + jhmcS + "'";

		}
		sagdList = sagdDao.searchSagdname(hql);
		return sagdList;
	}

	public JSONArray queryGridDataInfo(String wellName, String orgId)
			throws Exception {
		// JSONObject jsonArrObj = null;
		JSONArray dailyOutput = null;
		JSONArray waterRation = null;
		JSONObject jsObj = null;
		String timeCals = "select tp.parameter_value from PC_CD_TRIGGERED_PROCESS_T tp where tp.parameter_name='sagd_output_start_time'";
		String calcNum = "-16";
		List<Object[]> dataSet = sagdDao.queryInfo(timeCals);
		if (dataSet != null && dataSet.size() > 0) {
			calcNum = dataSet.get(0) + "";
		}
		String producedMetering = "select to_char(pm.cjsj,'YYYY-MM-DD HH24:MI:SS') ascjsj,pm.cl from pc_rd_produced_metering_t pm where pm.clsj>=30 and pm.org_id='"
				+ orgId
				+ "' and pm.cjsj between TO_DATE('"
				+ getNowTime(calcNum, "", "")
				+ "','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"
				+ getNowTime(calcNum, "endTime", "")
				+ "','YYYY-MM-DD HH24:MI:SS')";
		String waterRationSql = "select wr.TEST_BSW,wr.SAMPLE_TAKEN_DATE from pc_cd_water_ration_v wr where wr.WELL_COMMON_NAME='"
				+ wellName
				+ "' and SAMPLE_TAKEN_DATE = TO_DATE('"
				+ getNowTime(calcNum, "", "waterRation") + "','YYYY-MM-DD')";
		if (!"".endsWith(orgId)) {
			dataSet = sagdDao.queryInfo(producedMetering);
			if (dataSet != null && dataSet.size() > 0) {
				dailyOutput = new JSONArray();
				String[] cloumnsName = { "cjsj", "cl" };
				for (Object[] o : dataSet) {
					jsObj = new JSONObject();
					for (int i = 0; i < cloumnsName.length; i++) {
						if (o[i] == null) {
							o[i] = "";
						}
						jsObj.put(cloumnsName[i], o[i]);
					}
					dailyOutput.add(jsObj);
				}
			}
			return dailyOutput;
		}
		if (!"".endsWith(wellName)) {
			dataSet = sagdDao.queryInfo(waterRationSql);
			if (dataSet != null && dataSet.size() > 0) {
				waterRation = new JSONArray();
				String[] cloumnsName = { "TEST_BSW" };
				for (Object[] o : dataSet) {
					jsObj = new JSONObject();
					for (int i = 0; i < cloumnsName.length; i++) {
						if (o[i] == null) {
							o[i] = "";
						}
						jsObj.put(cloumnsName[i], o[i]);
					}
					waterRation.add(jsObj);
				}
				return waterRation;
			}
		}
		return (JSONArray) new JSONArray();
	}

	public JSONObject getNeedData(JSONObject relustJson, Object k, Object o) {
		if (o != null) {
			relustJson.put(k, o);
		}
		return relustJson;
	}

	@Override
	public List<String> dayreport(String date) throws Exception {
		String date1 = date;
		return sagdDao.dayreport(date1);
	}

	@Override
	public JSONArray searchAutoCompleteData(String flg) {
		String hql = "";
		JSONArray jsonArr = null;
		JSONObject jsobj = new JSONObject();
		List<Object[]> structureList = sagdDao.searchStructureNew(hql);
		Object[] obj = null;
		if (structureList.size() > 0 && structureList != null) {
			jsonArr = new JSONArray();
			obj = new Object[structureList.size()];
			for (int i = 0; i < structureList.size(); i++) {
				obj[i] = structureList.get(i)[3];
			}
		}
		jsobj.put("WELLDATAS", obj);
		jsonArr.add(jsobj);
		return jsonArr;
	}

	public String getNowTime(String calcNum, String calcFlag, String resultFlag)
			throws Exception {
		Calendar c = Calendar.getInstance();//
		calcNum = (Integer.parseInt(calcNum) + 24) + "";
		int year = c.get(Calendar.YEAR); // 获取年
		int month = c.get(Calendar.MONTH) + 1; // 获取月份，0表示1月份
		int day = c.get(Calendar.DAY_OF_MONTH); // 获取当前天数
		if (!"endTime".equals(calcFlag)) {
			day = day - 1;
		}
		if ("waterRation".equals(resultFlag)) {// 含水日期
			return year + "-" + month + "-" + day;
		}
		return year + "-" + month + "-" + day + " " + calcNum + ":00:00";
	}

	public String searchCSName(String code) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select t.p_description from PC_PK_STIMU_TYPE_T t where t.stimu_code='"
				+ code + "'";
		List<Object[]> lo = new ArrayList<Object[]>();
		lo = sagdDao.searchPstages(sql);
		String csName = null;
		if (lo.size() > 0) {
			csName = (String) lo.toArray()[0];
		}
		// Object blockName = lo.get(0);
		return csName;
	}

	public String searchGJName(String code) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select t.p_description from PC_PK_SHUTIN_TYPE_T t where t.shutin_code='"
				+ code + "'";
		List<Object[]> lo = new ArrayList<Object[]>();
		lo = sagdDao.searchPstages(sql);
		String gjName = "";
		if (lo.size() > 0) {
			gjName = (String) lo.toArray()[0];
		}
		// Object blockName = lo.get(0);
		return gjName;
	}

	@Override
	public HashMap<String, Object> searchSagdgh(String oilationname,
			String ghmc, String startDate, String endDate, int pageNo,
			String sort, String order, int rowsPerpage, String totalNumf) {
		JSONArray jsonArr = null;
		JSONObject tjo = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		String cloums = "select   ";
		if ("".equals(startDate)) {
			startDate = startDate + " 00:00:00";
		} else {
			startDate = startDate + ":00";
		}
		if ("".equals(endDate)) {
			endDate += " 23:59:59";
		} else {
			endDate = endDate + ":00";
		}
		String formTable = " from PC_RD_MANIFOLD_SAGD_V where 1=1  and CJSJ between TO_DATE('"
				+ startDate
				+ "','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"
				+ endDate + "','YYYY-MM-DD HH24:MI:SS') ";
		String totalNum = "select count(*) ";
		if (!oilationname.equals("") && oilationname != null
				&& !oilationname.equals("undefined")
				& !oilationname.equals("全部")) {
			formTable = formTable + " and OILSTATIONNAME='" + oilationname
					+ "'";
		}
		if (!ghmc.equals("") && ghmc != null && !ghmc.equals("undefined")) {
			formTable = formTable + " and PIPE_SINK='" + ghmc + "'";
		}

		String[] cloumnsName = { "OILSTATIONNAME", "CJSJ", "PIPE_SINK",
				"pressure_press", "pressure_temp", "looped_press",
				"looped_temp", "measure_press", "measure_temp" };
		String kk = "OILSTATIONNAME";
		for (int m = 1; m < cloumnsName.length; m++) {
			if ("CJSJ".equals(cloumnsName[m])) {
				kk = kk + "," + "to_char(CJSJ,'YYYY-MM-DD hh24:mi:ss') as CJSJ";
			} else {
				kk = kk + "," + cloumnsName[m];
			}
		}
		if ("export".equals(totalNumf)) {
			String[] cloumnsName2 = { "OILSTATIONNAME", "CJSJ", "PIPE_SINK",
					"pressure_press", "pressure_temp", "looped_press",
					"looped_temp", "measure_press", "measure_temp" };
			kk = "OILSTATIONNAME";
			for (int m = 1; m < cloumnsName2.length; m++) {
				if ("CJSJ".equals(cloumnsName2[m])) {
					kk = kk + ","
							+ "to_char(CJSJ,'YYYY-MM-DD hh24:mi:ss') as CJSJ";
				} else {
					kk = kk + "," + cloumnsName2[m];
				}
			}
		}
		String thinOilWellRD = cloums + kk + formTable;
		// 获取总条数
		int total = 0;
		if ("1".equals(totalNum)) {
			total = 1;
		} else {
			totalNum += formTable;
			total = sagdDao.getCounts(totalNum);
		}
		if ("totalNum".equals(totalNumf)) {
			map.put("totalNum", total + "");
			return map;
		}
		// 排序
		if (!"".equals(sort) && !"CJSJ".equals(sort)) {
			for (String cloumn : cloumnsName) {
				if (cloumn.equals(sort)) {
					thinOilWellRD += " order by " + cloumn + " " + order;
					break;
				}
			}
		} else {
			thinOilWellRD += " order by CJSJ ";
		}
		// 计算分页
		PageControl control = new PageControl();
		// 当前页
		List<Object[]> lo = new ArrayList<Object[]>();
		control.setInt_num(rowsPerpage);
		if ("export".equals(totalNumf)) {
			lo = sagdDao.searchSagdghRD(thinOilWellRD);
		} else {
			control.init(pageNo, total);
			// 开始条数
			int start = control.getStart() - 1;
			lo = sagdDao.searchSagdghRD(thinOilWellRD, start, rowsPerpage);
		}
		if (lo != null && 0 < lo.size()) {
			// 生成树节点json文档
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
	public JSONObject searchSagdghResults(String oilationname, String ghmc,
			String startDate, String endDate) {
		JSONObject tjo = new JSONObject();
		List<Long> timeList = new ArrayList<Long>();
		List<BigDecimal> zhupList = new ArrayList<BigDecimal>();
		List<BigDecimal> zhutList = new ArrayList<BigDecimal>();
		List<BigDecimal> fupList = new ArrayList<BigDecimal>();
		List<BigDecimal> futList = new ArrayList<BigDecimal>();
		List<BigDecimal> jlpList = new ArrayList<BigDecimal>();
		List<BigDecimal> jltList = new ArrayList<BigDecimal>();

		String hql = "select   ";
		if ("".equals(startDate)) {
			startDate = startDate + " 00:00:00";
		} else {
			startDate = startDate + ":00";
		}
		if ("".equals(endDate)) {
			endDate += " 23:59:59";
		} else {
			endDate = endDate + ":00";
		}
		String formTable = " from PC_RD_MANIFOLD_SAGD_V where 1=1  and CJSJ between TO_DATE('"
				+ startDate
				+ "','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"
				+ endDate + "','YYYY-MM-DD HH24:MI:SS') ";

		if (!oilationname.equals("") && oilationname != null
				&& !oilationname.equals("undefined")
				& !oilationname.equals("全部")) {
			formTable = formTable + " and OILSTATIONNAME='" + oilationname
					+ "'";
		}
		if (!ghmc.equals("") && ghmc != null && !ghmc.equals("undefined")) {
			formTable = formTable + " and PIPE_SINK='" + ghmc + "'";
		}

		String[] cloumnsName = { "CJSJ", "pressure_press", "pressure_temp",
				"looped_press", "looped_temp", "measure_press", "measure_temp" };
		hql += "to_char(CJSJ,'YYYY-MM-DD hh24:mi:ss') as CJSJ";
		for (int m = 1; m < cloumnsName.length; m++) {
			hql = hql + ", " + cloumnsName[m];
		}

		hql += formTable;

		List<Object[]> lo = sagdDao.queryInfo(hql);

		if (lo != null && 0 < lo.size()) {
			for (int i = 0; i < lo.size(); i++) {
				Object[] o = lo.get(i);
				String times = o[0].toString();
				SimpleDateFormat format = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				Date date;
				long time = 0;
				try {
					date = format.parse(times);
					time = date.getTime();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// System.out.println("############"+time+"##############");

				// SimpleDateFormat format1 = new
				// SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				// long time1=new Long(1470758409000L);
				// String d = format.format(time1);
				//
				// System.out.println("Format To String(Date):"+d);

				timeList.add(time);
				zhupList.add((BigDecimal) o[1]);
				zhutList.add((BigDecimal) o[2]);
				fupList.add((BigDecimal) o[3]);
				futList.add((BigDecimal) o[4]);
				jlpList.add((BigDecimal) o[5]);
				jltList.add((BigDecimal) o[6]);
			}
		}

		tjo.put("time", timeList);
		tjo.put("zhup", zhupList);
		tjo.put("zhut", zhutList);
		tjo.put("fup", fupList);
		tjo.put("fut", futList);
		tjo.put("jlp", jlpList);
		tjo.put("jlt", jltList);
		return tjo;
	}

	@Override
	public HashMap<String, Object> searchSagd183dt(String startDate,
			String endDate, int pageNo, String sort, String order,
			int rowsPerpage, String totalNumf) {

		JSONArray jsonArr = null;
		JSONObject tjo = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		String cloums = "select   ";

		Date today = new Date();
		String nowdate = DateBean.getTimeStr(today);

		if ("".equals(startDate)) {
			startDate = nowdate.substring(0, 10) + " 00:00:00";
		} else {
			startDate = startDate + ":00";
		}
		if ("".equals(endDate)) {
			endDate = nowdate.substring(0, 10) + " 23:59:59";
		} else {
			endDate = endDate + ":00";
		}

		String formTable = " from PC_RD_BSTATION_TURNSAGD3_T where 1=1  and acquisition_time between TO_DATE('"
				+ startDate
				+ "','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"
				+ endDate + "','YYYY-MM-DD HH24:MI:SS') ";
		String totalNum = "select count(*) ";
		String[] cloumnsName = { "acquisition_time", "flq_qckyl1", "flq_ywgd1",
				"flq_qdkfkd1", "flq_ydkfkd1", "flq_qckyl2", "flq_ywgd2",
				"flq_qdkfkd2", "flq_ydkfkd2", "flq_qckyl3", "flq_ywgd3",
				"flq_qdkfkd3", "flq_ydkfkd3", "pump_in_zx_press",
				"pump_in_zx_tem", "pump_in_fx_press", "pump_in_fx_tem",
				"pump_out_zx_press", "pump_out_zx_tem", "pump_out_fx_press",
				"pump_out_fx_tem", "pump_zt1", "pump_pl1", "pump_sd1",
				"pump_zt2", "pump_pl2", "pump_sd2", "pump_zt3", "pump_pl3",
				"pump_sd3" };

		String kk = "";
		for (int m = 0; m < cloumnsName.length; m++) {
			if ("acquisition_time".equals(cloumnsName[m])) {
				kk = kk
						+ "to_char(acquisition_time,'YYYY-MM-DD hh24:mi:ss') as acquisition_time";
			} else {
				kk = kk + "," + cloumnsName[m];
			}
		}
		if ("export".equals(totalNumf)) {
			String[] cloumnsName2 = { "acquisition_time", "flq_qckyl1",
					"flq_ywgd1", "flq_qdkfkd1", "flq_ydkfkd1", "flq_qckyl2",
					"flq_ywgd2", "flq_qdkfkd2", "flq_ydkfkd2", "flq_qckyl3",
					"flq_ywgd3", "flq_qdkfkd3", "flq_ydkfkd3",
					"pump_in_zx_press", "pump_in_zx_tem", "pump_in_fx_press",
					"pump_in_fx_tem", "pump_out_zx_press", "pump_out_zx_tem",
					"pump_out_fx_press", "pump_out_fx_tem", "pump_zt1",
					"pump_pl1", "pump_sd1", "pump_zt2", "pump_pl2", "pump_sd2",
					"pump_zt3", "pump_pl3", "pump_sd3" };
			kk = "";
			for (int m = 0; m < cloumnsName2.length; m++) {
				if ("acquisition_time".equals(cloumnsName2[m])) {
					kk = kk
							+ "to_char(acquisition_time,'YYYY-MM-DD hh24:mi:ss') as acquisition_time";
				} else {
					kk = kk + "," + cloumnsName2[m];
				}
			}
		}
		String searchsagd183 = cloums + kk + formTable;
		// 获取总条数
		int total = 0;
		if ("1".equals(totalNum)) {
			total = 1;
		} else {
			totalNum += formTable;
			total = sagdDao.getCounts(totalNum);
		}
		if ("totalNum".equals(totalNumf)) {
			map.put("totalNum", total + "");
			return map;
		}
		// 排序
		if (!"".equals(sort) && !"acquisition_time".equals(sort)) {
			for (String cloumn : cloumnsName) {
				if (cloumn.equals(sort)) {
					searchsagd183 += " order by " + cloumn + " " + order;
					break;
				}

			}
		} else {
			searchsagd183 += " order by acquisition_time desc";
		}
		// 计算分页
		PageControl control = new PageControl();
		// 当前页
		List<Object[]> lo = new ArrayList<Object[]>();
		control.setInt_num(rowsPerpage);
		if ("export".equals(totalNumf)) {
			lo = sagdDao.searchSagd183(searchsagd183);
		} else {
			control.init(pageNo, total);
			// 开始条数
			int start = control.getStart() - 1;
			lo = sagdDao.searchSagd183(searchsagd183, start, rowsPerpage);
		}

		if (lo != null && 0 < lo.size()) {
			// 生成树节点json文档
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
	public HashMap<String, Object> searchSagdnow(String oilationname,
			String ghmc, String startDate, String endDate, int pageNo,
			String sort, String order, int rowsPerpage, String totalNumf) {
		JSONArray jsonArr = null;
		JSONObject tjo = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		String cloums = "select   ";

		Date today = new Date();
		String nowdate = DateBean.getTimeStr(today);

		if ("".equals(startDate)) {
			startDate = nowdate.substring(0, 10) + " 00:00:00";
		} else {
			startDate = startDate + ":00";
		}
		if ("".equals(endDate)) {
			endDate = nowdate.substring(0, 10) + " 23:59:59";
		} else {
			endDate = endDate + ":00";
		}

		String formTable = " from PC_RD_MANIFOLD_SAGD_v a where not exists(select 1 from PC_RD_MANIFOLD_SAGD_v b where b.pipe_sink=a.pipe_sink and  b.cjsj>a.cjsj)";
		String totalNum = "select count(*) ";
		String[] cloumnsName = { "a.oilstationname", "a.cjsj", "a.PIPE_SINK",
				"a.PRESSURE_PRESS", "a.PRESSURE_TEMP", "a.LOOPED_PRESS",
				"a.LOOPED_TEMP", "a.MEASURE_PRESS", "a.MEASURE_TEMP" };

		String kk = "a.oilstationname";
		for (int m = 1; m < cloumnsName.length; m++) {
			if ("a.cjsj".equals(cloumnsName[m])) {
				kk = kk + ","
						+ "to_char(a.cjsj,'YYYY-MM-DD hh24:mi:ss') as cjsj";
			} else {
				kk = kk + "," + cloumnsName[m];
			}
		}
		if ("export".equals(totalNumf)) {
			String[] cloumnsName2 = { "a.oilstationname", "a.cjsj",
					"a.PIPE_SINK", "a.PRESSURE_PRESS", "a.PRESSURE_TEMP",
					"a.LOOPED_PRESS", "a.LOOPED_TEMP", "a.MEASURE_PRESS",
					"a.MEASURE_TEMP" };
			kk = "a.oilstationname";
			for (int m = 1; m < cloumnsName2.length; m++) {
				if ("a.cjsj".equals(cloumnsName2[m])) {
					kk = kk + ","
							+ "to_char(a.cjsj,'YYYY-MM-DD hh24:mi:ss') as cjsj";
				} else {
					kk = kk + "," + cloumnsName2[m];
				}
			}
		}
		String searchsagdnow = cloums + kk + formTable;
		// 获取总条数
		int total = 0;
		if ("1".equals(totalNum)) {
			total = 1;
		} else {
			totalNum += formTable;
			total = sagdDao.getCounts(totalNum);
		}
		if ("totalNum".equals(totalNumf)) {
			map.put("totalNum", total + "");
			return map;
		}

		// 计算分页
		PageControl control = new PageControl();
		// 当前页
		List<Object[]> lo = new ArrayList<Object[]>();
		control.setInt_num(rowsPerpage);
		if ("export".equals(totalNumf)) {
			lo = sagdDao.searchSagdnow(searchsagdnow);
		} else {
			control.init(pageNo, total);
			// 开始条数
			int start = control.getStart() - 1;
			lo = sagdDao.searchSagdnow(searchsagdnow, start, rowsPerpage);
		}

		if (lo != null && 0 < lo.size()) {
			// 生成树节点json文档
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
	public HashMap<String, Object> searchSagdss(String oilNumber, String ghmc,
			String oilname, int pageNo, String sort, String order,
			int rowsPerpage, String totalNumf) {
		// TODO Auto-generated method stub
		JSONArray jsonArr = null;
		JSONObject tjo = null;
		HashMap<String, Object> map = new HashMap<String, Object>();

		Date today = new Date();
		String nowdate = DateBean.getTimeStr(today);
		System.out.println(nowdate);
		String startDate = nowdate.substring(0, 10) + " 00:00:00";
		String endDate = nowdate;

		String cloums = "select   ";
		String formTable = " from PC_RD_WELL_SAGD_V  a,(select max(ACQUISITION_TIME) as cjsj,jhmc from PC_RD_WELL_SAGD_V b where  ACQUISITION_TIME between TO_DATE('"
				+ startDate
				+ "','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"
				+ endDate
				+ "','YYYY-MM-DD HH24:MI:SS') group by jhmc order by CJSJ desc)  x where a.ACQUISITION_TIME=x.cjsj and a.jhmc=x.jhmc";
		String totalNum = "select count(*) ";

		if (!oilNumber.equals("") && oilNumber != null
				&& !oilNumber.equals("undefined") && !oilNumber.equals("全部")) {
			formTable = formTable + " and OILSTATIONNAME='" + oilNumber + "'";
		}
		if (!ghmc.equals("") && ghmc != null && !ghmc.equals("undefined")) {
			formTable = formTable + " and MANIFOLD='" + ghmc + "'";
		}
		if (!oilname.equals("") && oilname != null
				&& !oilname.equals("undefined")) {
			formTable = formTable + " and a.jhmc like '%" + oilname + "%'";
		}

		List<String> cloumnsName = new ArrayList<String>();
		formTable = formTable + " and ACQUISITION_TIME between TO_DATE('"
				+ startDate + "','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"
				+ endDate + "','YYYY-MM-DD HH24:MI:SS')";

		cloumnsName.add("JHMC");
		cloumnsName.add("ACQUISITION_TIME");
		cloumnsName.add("JHMC_S");
		cloumnsName.add("OILSTATIONNAME");
		cloumnsName.add("AREABLOCK");
		cloumnsName.add("BLOCKSTATIONNAME");
		cloumnsName.add("MANIFOLD");
		cloumnsName.add("BRANCHINGID");
		if (!"export".equals(totalNumf)) {
			cloumnsName.add("SAGDID");
			cloumnsName.add("ORG_ID");

			cloumnsName.add("MOTORSTATUS");
			cloumnsName.add("MOTOR_PRESS_A");
			cloumnsName.add("MOTOR_PRESS_B");
			cloumnsName.add("MOTOR_PRESS_C");
			cloumnsName.add("MOTOR_TEMP_A");
			cloumnsName.add("MOTOR_TEMP_B");
			cloumnsName.add("MOTOR_TEMP_C");
			cloumnsName.add("ACTIVE_ENERGY");
			cloumnsName.add("IDLE_ENERGY");
			cloumnsName.add("POWER_FACTOR");
			cloumnsName.add("CONVERSION_PRESS");
			cloumnsName.add("CONVERSION_ELECTRICITY");
			cloumnsName.add("NOW_FREQUENCY");
			cloumnsName.add("STROKE");
			cloumnsName.add("JIG_FREQUENCY");
			cloumnsName.add("ALMSTATE");
			cloumnsName.add("NOW_LOAD");
			cloumnsName.add("MOTOR_MODE");
			cloumnsName.add("PUMPING_RUNNING_STATE");

			cloumnsName.add("P_PRESSURE_PRESS");
			cloumnsName.add("P_PRESSURE_TEMP");
			cloumnsName.add("P_LOOPED_PRESS");
			cloumnsName.add("P_LOOPED_TEMP");
			cloumnsName.add("P_DRIVEPIPE_PRESS");
			cloumnsName.add("I_PRESSURE_PRESS");
			cloumnsName.add("I_PRESSURE_TEMP");
			cloumnsName.add("I_LOOPED_PRESS");
			cloumnsName.add("I_LOOPED_TEMP");
			cloumnsName.add("I_DRIVEPIPE_PRESS");

			cloumnsName.add("P_PRESS1");
			cloumnsName.add("P_PRESS2");
			cloumnsName.add("P_TEMP1");
			cloumnsName.add("P_TEMP2");
			cloumnsName.add("P_TEMP3");
			cloumnsName.add("P_TEMP4");
			cloumnsName.add("P_TEMP5");
			cloumnsName.add("P_TEMP6");
			cloumnsName.add("P_TEMP7");
			cloumnsName.add("P_TEMP8");
			cloumnsName.add("P_TEMP9");
			cloumnsName.add("P_TEMP10");
			cloumnsName.add("P_TEMP11");
			cloumnsName.add("P_TEMP12");

			cloumnsName.add("NO1STEAM_PRESS");
			cloumnsName.add("NO2STEAM_PRESS");
			cloumnsName.add("NO1STEAM_TEMP");
			cloumnsName.add("NO2STEAM_TEMP");
			cloumnsName.add("NO1STEAM_DIFP");
			cloumnsName.add("NO2STEAM_DIFP");
			cloumnsName.add("NO1INSTANT_DRYNESS");
			cloumnsName.add("NO2INSTANT_DRYNESS");
			cloumnsName.add("NO1INSTANT_FLOW");
			cloumnsName.add("NO2INSTANT_FLOW");
			cloumnsName.add("NO1CONTROL_APERTURE");
			cloumnsName.add("NO2CONTROL_APERTURE");
			cloumnsName.add("NO1CONTROL_STATUS");
			cloumnsName.add("NO2CONTROL_STATUS");
			cloumnsName.add("CLIQUE_CONTROL_APERTURE1");
			cloumnsName.add("CLIQUE_CONTROL_APERTURE2");
			cloumnsName.add("NO1FLOW_SET");
			cloumnsName.add("NO2FLOW_SET");
			cloumnsName.add("YCUMULATIVE_FLOW1");
			cloumnsName.add("YCUMULATIVE_FLOW2");
			cloumnsName.add("TCUMULATIVE_FLOW1");
			cloumnsName.add("TCUMULATIVE_FLOW2");
			// cloumnsName.add("SUM_FLOW1");
			// cloumnsName.add("SUM_FLOW2");
		}

		cloumnsName.add("REMARK");
		String kk = "a.JHMC as JHMC";
		for (int m = 1; m < cloumnsName.size(); m++) {
			// to_char(u.audbshor_date,'YYYY-MM-DD hh24:mi:ss') as audbshor_date
			if ("ACQUISITION_TIME".equals(cloumnsName.get(m))) {
				kk = kk
						+ ","
						+ "to_char(ACQUISITION_TIME,'YYYY-MM-DD hh24:mi:ss') as ACQUISITION_TIME";
			} else {
				kk = kk + "," + cloumnsName.get(m);
			}

		}

		String boilersInfo = cloums + kk + formTable;
		// 获取总条数
		int total = 0;
		if ("1".equals(totalNum)) {
			total = 1;
		} else {
			totalNum += formTable;
			total = sagdDao.getCounts(totalNum);
		}
		if ("totalNum".equals(totalNumf)) {
			map.put("totalNum", total + "");
			return map;
		}
		// 排序
		if (!"".equals(sort) && !"a.JHMC as JHMC".equals(sort)) {
			for (String cloumn : cloumnsName) {
				if (cloumn.equals(sort)) {
					boilersInfo += " order by " + cloumn + " " + order;
					break;
				}
			}
		} else {
			boilersInfo += " order by ACQUISITION_TIME desc ";
		}
		List<Object[]> lo = null;
		try {
			if ("export".equals(totalNumf)) {
				lo = sagdDao.queryInfo(boilersInfo);
			} else {
				// 计算分页
				PageControl control = new PageControl();
				// 当前页
				control.setInt_num(rowsPerpage);
				control.init(pageNo, total);
				// 开始条数
				int start = control.getStart() - 1;
				lo = sagdDao.searchSagdss(boilersInfo, start, rowsPerpage,
						cloumnsName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (lo != null && 0 < lo.size()) {
			// 生成树节点json文档
			jsonArr = new JSONArray();
			for (Object[] o : lo) {
				tjo = new JSONObject();

				for (int i = 0; i < o.length; i++) {
					if (o[i] == null || o[i].equals("null")) {
						o[i] = "";
					}
					if (i == 10) {
						if (o[10].toString().equals("1")) {
							tjo.put(cloumnsName.get(i), "自动");
							o[10] = "自动";
						} else if (o[10].toString().equals("0")) {
							tjo.put(cloumnsName.get(i), "手动");
							o[10] = "手动";
						} else {
							tjo.put(cloumnsName.get(i), o[i].toString());
						}
					} else if (i == 65) {
						if (o[64].toString().equals("1")) {
							tjo.put(cloumnsName.get(i), "自动");
							o[64] = "自动";
						} else if (o[64].toString().equals("0")) {
							tjo.put(cloumnsName.get(i), "手动");
							o[64] = "手动";
						} else {
							tjo.put(cloumnsName.get(i), o[i].toString());
						}
					} else if (i == 66) {
						if (o[65].toString().equals("1")) {
							tjo.put(cloumnsName.get(i), "自动");
							o[65] = "自动";
						} else if (o[65].toString().equals("0")) {
							tjo.put(cloumnsName.get(i), "手动");
							o[65] = "手动";
						} else {
							tjo.put(cloumnsName.get(i), o[i].toString());
						}
					} else if (i == 28) {
						if (o[28].toString().equals("1")) {
							tjo.put(cloumnsName.get(i), "指示");
							o[28] = "指示";
						} else if (o[28].toString().equals("2")) {
							tjo.put(cloumnsName.get(i), "空闲");
							o[28] = "空闲";
						} else if (o[28].toString().equals("4")) {
							tjo.put(cloumnsName.get(i), "停机");
							o[28] = "停机";
						} else if (o[28].toString().equals("8")) {
							tjo.put(cloumnsName.get(i), "死停");
							o[28] = "死停";
						} else {
							tjo.put(cloumnsName.get(i), o[i].toString());
						}
					} else if (i == 27) {
						if (o[27].toString().equals("1")) {
							tjo.put(cloumnsName.get(i), "变频");
							o[27] = "变频";
						} else if (o[27].toString().equals("0")) {
							tjo.put(cloumnsName.get(i), "工频");
							o[28] = "工频";
						} else {
							tjo.put(cloumnsName.get(i), o[i].toString());
						}
					} else {
						tjo.put(cloumnsName.get(i), o[i].toString());
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
	public HashMap<String, Object> searchGrglzb1(String txtDate, int pageNo,
			String sort, String order, int rowsPerpage, String totalNumf) {
		JSONArray jsonArr = null;
		JSONObject tjo = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		String cloums = "select   ";
		// 取一周范围的值
		String stratime = DateBean.getBefore6DAYTime(txtDate);
		String endtime = txtDate;
		String formTable = " from PC_RPD_BOILER_SUPERHEAT_YC_T where 1=1  and acquisition_time between TO_DATE('"
				+ stratime
				+ "','YYYY-MM-DD') and TO_DATE('"
				+ endtime
				+ "','YYYY-MM-DD') ";
		String totalNum = "select count(*) ";
		/*
		 * if(!gqdw.equals("")&&gqdw!=null&&!gqdw.equals("undefined")&&!gqdw.equals
		 * ("全部")){ formTable=formTable+" and gqdw='"+gqdw+"'"; }
		 * if(!hgl.equals("")&&hgl!=null&&!hgl.equals("undefined")){
		 * formTable=formTable+" and hgl='"+hgl+"'"; }
		 */

		String[] cloumnsName = { "gqdw", "acquisition_time", "glzts", "glyxts",
				"hgglts", "bhgglts", "hgl", "bhggllh" };

		String kk = "gqdw";
		for (int m = 1; m < cloumnsName.length; m++) {
			if ("acquisition_time".equals(cloumnsName[m])) {
				kk = kk
						+ ","
						+ "to_char(acquisition_time,'YYYY-MM-DD') as acquisition_time";
			} else {
				kk = kk + "," + cloumnsName[m];
			}
		}
		if ("export".equals(totalNumf)) {
			String[] cloumnsName2 = { "gqdw", "acquisition_time", "glzts",
					"glyxts", "hgglts", "bhgglts", "hgl", "bhggllh" };
			kk = "gqdw";
			for (int m = 1; m < cloumnsName2.length; m++) {
				if ("acquisition_time".equals(cloumnsName2[m])) {
					kk = kk
							+ ","
							+ "to_char(acquisition_time,'YYYY-MM-DD') as acquisition_time";
				} else {
					kk = kk + "," + cloumnsName2[m];
				}
			}
		}
		String searchgrglzb1 = cloums + kk + formTable;
		// 获取总条数
		int total = 0;
		if ("1".equals(totalNum)) {
			total = 1;
		} else {
			totalNum += formTable;
			total = sagdDao.getCounts(totalNum);
		}
		if ("totalNum".equals(totalNumf)) {
			map.put("totalNum", total + "");
			return map;
		}
		// 排序
		if (!"".equals(sort) && !"acquisition_time".equals(sort)) {
			for (String cloumn : cloumnsName) {
				if (cloumn.equals(sort)) {
					searchgrglzb1 += " order by " + cloumn + " " + order;
					break;
				}
			}
		} else {
			searchgrglzb1 += " order by acquisition_time desc";
		}
		// 计算分页
		PageControl control = new PageControl();
		// 当前页
		List<Object[]> lo = new ArrayList<Object[]>();
		control.setInt_num(rowsPerpage);
		if ("export".equals(totalNumf)) {
			lo = sagdDao.searchGrglzb1(searchgrglzb1);
		} else {
			control.init(pageNo, total);
			// 开始条数
			int start = control.getStart() - 1;
			lo = sagdDao.searchGrglzb1(searchgrglzb1, start, rowsPerpage);
		}
		if (lo != null && 0 < lo.size()) {
			// 生成树节点json文档
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
	public JSONArray queryBZInfo(String arg) {

		JSONArray jsonArr = null;
		JSONObject jsobj = null;
		// String sql =
		// "select  distinct m.bzhf  from pc_cd_well_sagd_v m  where m.oilationname = '"+oilationname+"'";
		// String sql = "select  distinct m.bzhf from pc_cd_well_sagd_v m  "
		// +" left join  pc_cd_org_t o on o.org_id=m.org_id"
		// +" left join pc_cd_org_t o1 on o.pid=o1.org_id"
		// +" left join pc_cd_org_t o2 on o1.pid=o2.org_id  where o2.org_id = '"+oilationnameid+"'";

		String sql = "";
		if ("sagd".equals(arg)) {// 查询所有含锅炉的注转站
			sql = "select rownum,t.BZHF from (select distinct BZHF from pc_cd_well_sagd_T) t where t.BZHF!='NULL'";
		}
		List<Object[]> dataSet = sagdDao.queryInfo(sql);
		if (dataSet != null && dataSet.size() > 0) {
			// 生成树节点json文档
			jsonArr = new JSONArray();
			for (Object[] entry : dataSet) {
				jsobj = new JSONObject();
				jsobj.put("text", entry[1]);
				jsobj.put("id", entry[0]);
				jsonArr.add(jsobj);
			}
			jsobj = new JSONObject();
			jsobj.put("text", "全部");
			jsobj.put("id", 1);
			jsonArr.add(jsobj);
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
	public JSONObject searchSagd183Results(String startDate, String endDate) {

		JSONObject tjo = new JSONObject();
		List<Long> timeList = new ArrayList<Long>();
		List<BigDecimal> yl1List = new ArrayList<BigDecimal>();
		List<BigDecimal> gd1List = new ArrayList<BigDecimal>();
		List<BigDecimal> yl2List = new ArrayList<BigDecimal>();
		List<BigDecimal> gd2List = new ArrayList<BigDecimal>();
		List<BigDecimal> yl3List = new ArrayList<BigDecimal>();
		List<BigDecimal> gd3List = new ArrayList<BigDecimal>();

		List<BigDecimal> bjzxList = new ArrayList<BigDecimal>();
		List<BigDecimal> bczxList = new ArrayList<BigDecimal>();

		String hql = "select   ";
		if ("".equals(startDate)) {
			startDate = startDate + " 00:00:00";
		} else {
			startDate = startDate + ":00";
		}
		if ("".equals(endDate)) {
			endDate += " 23:59:59";
		} else {
			endDate = endDate + ":00";
		}
		String formTable = " from PC_RD_BSTATION_TURNSAGD3_T where 1=1  and acquisition_time between TO_DATE('"
				+ startDate
				+ "','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"
				+ endDate + "','YYYY-MM-DD HH24:MI:SS') ";
		String[] cloumnsName = { "acquisition_time", "flq_qckyl1", "flq_ywgd1",
				"flq_qckyl2", "flq_ywgd2", "flq_qckyl3", "flq_ywgd3",
				"pump_in_zx_press", "pump_out_zx_press" };
		hql += "to_char(acquisition_time,'YYYY-MM-DD hh24:mi:ss') as acquisition_time";
		for (int m = 1; m < cloumnsName.length; m++) {
			hql = hql + ", " + cloumnsName[m];
		}

		hql += formTable;

		List<Object[]> lo = sagdDao.queryInfo(hql);

		if (lo != null && 0 < lo.size()) {
			for (int i = 0; i < lo.size(); i++) {
				Object[] o = lo.get(i);
				String times = o[0].toString();
				SimpleDateFormat format = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				Date date;
				long time = 0;
				try {
					date = format.parse(times);
					time = date.getTime();
				} catch (ParseException e) {
					e.printStackTrace();
				}

				timeList.add(time);
				yl1List.add((BigDecimal) o[1]);
				gd1List.add((BigDecimal) o[2]);
				yl2List.add((BigDecimal) o[3]);
				gd2List.add((BigDecimal) o[4]);
				yl3List.add((BigDecimal) o[5]);
				gd3List.add((BigDecimal) o[6]);
				bjzxList.add((BigDecimal) o[7]);
				bczxList.add((BigDecimal) o[8]);
			}
		}

		tjo.put("time", timeList);
		tjo.put("yl1", yl1List);
		tjo.put("gd1", yl1List);
		tjo.put("yl2", yl2List);
		tjo.put("gd2", gd2List);
		tjo.put("yl3", yl3List);
		tjo.put("gd3", gd3List);
		tjo.put("jkzx", bjzxList);
		tjo.put("ckzx", bczxList);
		return tjo;
	}

	@Override
	public HashMap<String, Object> searchZqPqqdt(String zqpqq,
			String startDate, String endDate, int pageNo, String sort,
			String order, int rowsPerpage, String totalNumFlag) {

		JSONArray jsonArr = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		JSONObject tjo = null;
		String cloums = "select   ";
		String formTable = " from PC_RD_STEAM_DISTRIBUTION_T a where ";
		String totalNum = "select count(*) ";
		String kk = "";
		Date today = new Date();
		String nowdate = DateBean.getTimeStr(today);

		if ("".equals(startDate)) {
			startDate = nowdate.substring(0, 10) + " 00:00:00";
		} else {
			startDate = startDate + ":00";
		}
		if ("".equals(endDate)) {
			endDate = nowdate.substring(0, 10) + " 23:59:59";
		} else {
			endDate = endDate + ":00";
		}
		if (zqpqq.equals("") || zqpqq.equals("全部")) {
			formTable = formTable + "1=1";
		} else {
			formTable = formTable + "steam_name = '" + zqpqq + "'";
		}
		formTable = formTable + " and acquisition_time between TO_DATE('"
				+ startDate + "','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"
				+ endDate + "','YYYY-MM-DD HH24:MI:SS')";

		String[] cloumnsName = { "steam_name", "acquisition_time", "pt_101",
				"tt_101", "fit_101", "fiq_101", "pt_102", "tt_102", "fit_102",
				"fiq_102", "pt_103", "tt_103", "fit_103", "fiq_103", "pt_104",
				"tt_104", "fit_104", "fiq_104", "pt_105", "tt_105", "fit_105",
				"fiq_105" };
		for (int m = 0; m < cloumnsName.length; m++) {
			if (m == cloumnsName.length - 1) {
				kk = kk + cloumnsName[m];
			} else {
				if ("acquisition_time".equals(cloumnsName[m])) {
					kk = kk
							+ "to_char(acquisition_time,'YYYY-MM-DD HH24:MI:SS') as acquisition_time,";
				} else {
					kk = kk + cloumnsName[m] + ",";
				}
			}

		}
		kk.subSequence(0, kk.length() - 2);

		String product = cloums + kk + formTable;
		// 获取总条数
		int total = 0;
		if ("1".equals(totalNum)) {
			total = 1;
		} else {
			totalNum += formTable;
			total = sagdDao.getCounts(totalNum);
		}
		if ("totalNum".equals(totalNumFlag)) {
			map.put("totalNum", total + "");
			return map;
		}
		// 排序
		if (!"".equals(sort) && !"acquisition_time".equals(sort)) {
			for (String cloumn : cloumnsName) {
				if (cloumn.equals(sort)) {
					product += " order by " + cloumn + " " + order;
					break;
				}
			}
		} else {
			product += " order by acquisition_time desc";
		}
		// 计算分页
		PageControl control = new PageControl();
		// 当前页
		List<Object[]> lo = new ArrayList<Object[]>();
		control.setInt_num(rowsPerpage);
		if ("export".equals(totalNumFlag)) {
			lo = sagdDao.searchEverySql(product);
		} else {
			control.init(pageNo, total);
			// 开始条数
			int start = control.getStart() - 1;
			lo = sagdDao.serarchZqpqq(product, start, rowsPerpage);
		}

		if (lo != null && 0 < lo.size()) {
			// 生成树节点json文档
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
	// /未使用
	public JSONArray searchPqq() {
		JSONArray jsonArr = null;
		JSONObject jsobj = null;
		String sql = "select distinct structurename from pc_cd_org_t t where structuretype = '16'";

		List<Object[]> dataSet = sagdDao.queryInfo(sql);
		if (dataSet != null && dataSet.size() > 0) {
			// 生成树节点json文档
			jsonArr = new JSONArray();
			for (Object[] entry : dataSet) {
				jsobj = new JSONObject();
				jsobj.put("text", entry[1]);
				jsobj.put("id", entry[0]);
				jsonArr.add(jsobj);
			}
			jsobj = new JSONObject();
			jsobj.put("text", "全部");
			jsobj.put("id", 1);
			jsonArr.add(jsobj);
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
	public HashMap<String, Object> searchYcgxdt(String ycgx, String startDate,
			String endDate, int pageNo, String sort, String order,
			int rowsPerpage, String totalNumFlag) {
		JSONArray jsonArr = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		JSONObject tjo = null;
		String cloums = "select   ";
		String formTable = " from PC_RD_YC_DISTRIBUTION_T a where ";
		String totalNum = "select count(*) ";
		String kk = "";
		Date today = new Date();
		String nowdate = DateBean.getTimeStr(today);

		if ("".equals(startDate)) {
			startDate = nowdate.substring(0, 10) + " 00:00:00";
		} else {
			startDate = startDate + ":00";
		}
		if ("".equals(endDate)) {
			endDate = nowdate.substring(0, 10) + " 23:59:59";
		} else {
			endDate = endDate + ":00";
		}
		if (ycgx.equals("") || ycgx.equals("全部")) {
			formTable = formTable + "1=1";
		} else {
			formTable = formTable + "steam_name = '" + ycgx + "'";
		}
		formTable = formTable + " and acquisition_time between TO_DATE('"
				+ startDate + "','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"
				+ endDate + "','YYYY-MM-DD HH24:MI:SS')";

		String[] cloumnsName = { "acquisition_time", "steam_name", "pt_101",
				"tt_104", "pt_102", "tt_103", "pt_103", "tt_102", "pt_104",
				"tt_101" };
		for (int m = 0; m < cloumnsName.length; m++) {
			if (m == cloumnsName.length - 1) {
				kk = kk + cloumnsName[m];
			} else {
				if ("acquisition_time".equals(cloumnsName[m])) {
					kk = kk
							+ "to_char(acquisition_time,'YYYY-MM-DD HH24:MI:SS') as acquisition_time,";
				} else {
					kk = kk + cloumnsName[m] + ",";
				}
			}

		}
		kk.subSequence(0, kk.length() - 2);

		String product = cloums + kk + formTable;
		// 获取总条数
		int total = 0;
		if ("1".equals(totalNum)) {
			total = 1;
		} else {
			totalNum += formTable;
			total = sagdDao.getCounts(totalNum);
		}
		if ("totalNum".equals(totalNumFlag)) {
			map.put("totalNum", total + "");
			return map;
		}
		// 排序
		if (!"".equals(sort) && !"acquisition_time".equals(sort)) {
			for (String cloumn : cloumnsName) {
				if (cloumn.equals(sort)) {
					product += " order by " + cloumn + " " + order;
					break;
				}
			}
		} else {
			product += " order by acquisition_time desc";
		}
		// 计算分页
		PageControl control = new PageControl();
		// 当前页
		List<Object[]> lo = new ArrayList<Object[]>();
		control.setInt_num(rowsPerpage);
		if ("export".equals(totalNumFlag)) {
			lo = sagdDao.searchEverySql(product);
		} else {
			control.init(pageNo, total);
			// 开始条数
			int start = control.getStart() - 1;
			lo = sagdDao.serarchYcgx(product, start, rowsPerpage);
		}

		if (lo != null && 0 < lo.size()) {
			// 生成树节点json文档
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
	public HashMap<String, Object> searchSagd1hrdt(String startDate,
			String endDate, int pageNo, String sort, String order,
			int rowsPerpage, String totalNumf) {
		JSONArray jsonArr = null;
		JSONObject tjo = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		String cloums = "select   ";

		Date today = new Date();
		String nowdate = DateBean.getTimeStr(today);

		if ("".equals(startDate)) {
			startDate = nowdate.substring(0, 10) + " 00:00:00";
		} else {
			startDate = startDate + ":00";
		}
		if ("".equals(endDate)) {
			endDate = nowdate.substring(0, 10) + " 23:59:59";
		} else {
			endDate = endDate + ":00";
		}

		String formTable = " from PC_RD_HTSTATION_Z1SAGD_T where 1=1  and acquisition_time between TO_DATE('"
				+ startDate
				+ "','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"
				+ endDate + "','YYYY-MM-DD HH24:MI:SS') ";
		String totalNum = "select count(*) ";
		String[] cloumnsName = { "acquisition_time", "ZJYX_TEM", "XHJY_TEM",
				"FLQ_QCKYL1", "FLQ_YCKYL1", "FLQ_YWGD1", "FLQ_QDKFKD1",
				"FLQ_YDKFKD1", "FLQ_QCKYL2", "FLQ_YCKYL2", "FLQ_YWGD2",
				"FLQ_QDKFKD2", "FLQ_YDKFKD2", "FLQ_QCKYL3", "FLQ_YCKYL3",
				"FLQ_YWGD3", "FLQ_QDKFKD3", "FLQ_YDKFKD3", "PUMP_IN_PRESS1",
				"PUMP_IN_TEM1", "PUMP_OUT_PRESS1", "PUMP_OUT_TEM1", "PUMP_ZT1",
				"PUMP_PL1", "PUMP_IN_PRESS2", "PUMP_IN_TEM2",
				"PUMP_OUT_PRESS2", "PUMP_OUT_TEM2", "PUMP_ZT2", "PUMP_PL2",
				"PUMP_IN_PRESS3", "PUMP_IN_TEM3", "PUMP_OUT_PRESS3",
				"PUMP_OUT_TEM3", "PUMP_ZT3", "PUMP_PL3", "PUMP_IN_PRESS4",
				"PUMP_IN_TEM4", "PUMP_OUT_PRESS4", "PUMP_OUT_TEM4", "PUMP_ZT4",
				"PUMP_PL4", "PUMP_IN_PRESS5", "PUMP_IN_TEM5",
				"PUMP_OUT_PRESS5", "PUMP_OUT_TEM5", "PUMP_ZT5", "PUMP_PL5" };

		String kk = "";
		for (int m = 0; m < cloumnsName.length; m++) {
			if ("acquisition_time".equals(cloumnsName[m])) {
				kk = kk
						+ "to_char(acquisition_time,'YYYY-MM-DD hh24:mi:ss') as acquisition_time";
			} else {
				kk = kk + "," + cloumnsName[m];
			}
		}
		if ("export".equals(totalNumf)) {
			String[] cloumnsName2 = { "acquisition_time", "ZJYX_TEM",
					"XHJY_TEM", "FLQ_QCKYL1", "FLQ_YCKYL1", "FLQ_YWGD1",
					"FLQ_QDKFKD1", "FLQ_YDKFKD1", "FLQ_QCKYL2", "FLQ_YCKYL2",
					"FLQ_YWGD2", "FLQ_QDKFKD2", "FLQ_YDKFKD2", "FLQ_QCKYL3",
					"FLQ_YCKYL3", "FLQ_YWGD3", "FLQ_QDKFKD3", "FLQ_YDKFKD3",
					"PUMP_IN_PRESS1", "PUMP_IN_TEM1", "PUMP_OUT_PRESS1",
					"PUMP_OUT_TEM1", "PUMP_ZT1", "PUMP_PL1", "PUMP_IN_PRESS2",
					"PUMP_IN_TEM2", "PUMP_OUT_PRESS2", "PUMP_OUT_TEM2",
					"PUMP_ZT2", "PUMP_PL2", "PUMP_IN_PRESS3", "PUMP_IN_TEM3",
					"PUMP_OUT_PRESS3", "PUMP_OUT_TEM3", "PUMP_ZT3", "PUMP_PL3",
					"PUMP_IN_PRESS4", "PUMP_IN_TEM4", "PUMP_OUT_PRESS4",
					"PUMP_OUT_TEM4", "PUMP_ZT4", "PUMP_PL4", "PUMP_IN_PRESS5",
					"PUMP_IN_TEM5", "PUMP_OUT_PRESS5", "PUMP_OUT_TEM5",
					"PUMP_ZT5", "PUMP_PL5" };
			kk = "";
			for (int m = 0; m < cloumnsName2.length; m++) {
				if ("acquisition_time".equals(cloumnsName2[m])) {
					kk = kk
							+ "to_char(acquisition_time,'YYYY-MM-DD hh24:mi:ss') as acquisition_time";
				} else {
					kk = kk + "," + cloumnsName2[m];
				}
			}
		}
		String searchsagd1hrdt = cloums + kk + formTable;
		// 获取总条数
		int total = 0;
		if ("1".equals(totalNum)) {
			total = 1;
		} else {
			totalNum += formTable;
			total = sagdDao.getCounts(totalNum);
		}
		if ("totalNum".equals(totalNumf)) {
			map.put("totalNum", total + "");
			return map;
		}
		// 排序
		if (!"".equals(sort) && !"acquisition_time".equals(sort)) {
			for (String cloumn : cloumnsName) {
				if (cloumn.equals(sort)) {
					searchsagd1hrdt += " order by " + cloumn + " " + order;
					break;
				}

			}
		} else {
			searchsagd1hrdt += " order by acquisition_time desc";
		}
		// 计算分页
		PageControl control = new PageControl();
		// 当前页
		List<Object[]> lo = new ArrayList<Object[]>();
		control.setInt_num(rowsPerpage);
		if ("export".equals(totalNumf)) {
			lo = sagdDao.searchSagd1hrdt(searchsagd1hrdt);
		} else {
			control.init(pageNo, total);
			// 开始条数
			int start = control.getStart() - 1;
			lo = sagdDao.searchSagd1hrdt(searchsagd1hrdt, start, rowsPerpage);
		}

		if (lo != null && 0 < lo.size()) {
			// 生成树节点json文档
			jsonArr = new JSONArray();
			for (Object[] o : lo) {
				tjo = new JSONObject();

				for (int i = 0; i < o.length; i++) {
					if (o[i] == null || o[i].equals("null")) {
						o[i] = "";
					}
					//
					// if(i==20 || i==26 || i==32 || i==38 || i==44 ){
					// if("0".equals(o[i].toString())){
					// tjo.put(cloumnsName[i], "启井");
					// }else if("1".equals(o[i].toString())){
					// tjo.put(cloumnsName[i], "停井");
					// }else{
					// tjo.put(cloumnsName[i], "");
					// }
					// }else{
					tjo.put(cloumnsName[i], o[i].toString());
					// }
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
	public HashMap<String, Object> searchFc160wfdt(String startDate,
			String endDate, int pageNo, String sort, String order,
			int rowsPerpage, String totalNumFlag) {
		JSONArray jsonArr = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		JSONObject tjo = null;
		String cloums = "select   ";
		String formTable = " from PC_ID_TEMPORARY_160WF_T a where 1=1 ";
		String totalNum = "select count(*) ";
		String kk = "";
		Date today = new Date();
		String nowdate = DateBean.getTimeStr(today);

		if ("".equals(startDate)) {
			startDate = nowdate.substring(0, 10) + " 00:00:00";
		} else {
			startDate = startDate + ":00";
		}
		if ("".equals(endDate)) {
			endDate = nowdate.substring(0, 10) + " 23:59:59";
		} else {
			endDate = endDate + ":00";
		}
		formTable = formTable + " and acquisition_time between TO_DATE('"
				+ startDate + "','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"
				+ endDate + "','YYYY-MM-DD HH24:MI:SS')";

		String[] cloumnsName = { "acquisition_time", "level1", "pump_zt1",
				"pump_pl1", "pump_zt2", "pump_pl2", "pump_zt3", "pump_pl3" };
		for (int m = 0; m < cloumnsName.length; m++) {
			if (m == cloumnsName.length - 1) {
				kk = kk + cloumnsName[m];
			} else {
				if ("acquisition_time".equals(cloumnsName[m])) {
					kk = kk
							+ "to_char(acquisition_time,'YYYY-MM-DD HH24:MI:SS') as acquisition_time,";
				} else {
					kk = kk + cloumnsName[m] + ",";
				}
			}

		}
		kk.subSequence(0, kk.length() - 2);

		String product = cloums + kk + formTable;
		// 获取总条数
		int total = 0;
		if ("1".equals(totalNum)) {
			total = 1;
		} else {
			totalNum += formTable;
			total = sagdDao.getCounts(totalNum);
		}
		if ("totalNum".equals(totalNumFlag)) {
			map.put("totalNum", total + "");
			return map;
		}
		// 排序
		if (!"".equals(sort) && !"acquisition_time".equals(sort)) {
			for (String cloumn : cloumnsName) {
				if (cloumn.equals(sort)) {
					product += " order by " + cloumn + " " + order;
					break;
				}
			}
		} else {
			product += " order by acquisition_time desc";
		}
		// 计算分页
		PageControl control = new PageControl();
		// 当前页
		List<Object[]> lo = new ArrayList<Object[]>();
		control.setInt_num(rowsPerpage);
		if ("export".equals(totalNumFlag)) {
			lo = sagdDao.searchEverySql(product);
		} else {
			control.init(pageNo, total);
			// 开始条数
			int start = control.getStart() - 1;
			lo = sagdDao.serarchFc160wf(product, start, rowsPerpage);
		}

		if (lo != null && 0 < lo.size()) {
			// 生成树节点json文档
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
	public HashMap<String, Object> searchSagd1lng(String startDate,
			String endDate, int pageNo, String sort, String order,
			int rowsPerpage, String totalNumFlag) {
		JSONArray jsonArr = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		JSONObject tjo = null;
		String cloums = "select   ";
		String formTable = " from PC_RD_HTSTATION_Z1SAGD_T a where 1=1 ";
		String totalNum = "select count(*) ";
		String kk = "";
		Date today = new Date();
		String nowdate = DateBean.getTimeStr(today);

		if ("".equals(startDate)) {
			startDate = nowdate.substring(0, 10) + " 00:00:00";
		} else {
			startDate = startDate + ":00";
		}
		if ("".equals(endDate)) {
			endDate = nowdate.substring(0, 10) + " 23:59:59";
		} else {
			endDate = endDate + ":00";
		}
		formTable = formTable + " and acquisition_time between TO_DATE('"
				+ startDate + "','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"
				+ endDate + "','YYYY-MM-DD HH24:MI:SS')";

		String[] cloumnsName = { "acquisition_time", "LIT_1", "LIT_2", "LIT_3",
				"LIT_4", "LIT_5", "LIT_6", "LIT_7", "LIT_8", "LIT_9", "LIT_10",
				"LIT_11", "LIT_12", "LIT_13", "LIT_14" };
		for (int m = 0; m < cloumnsName.length; m++) {
			if (m == cloumnsName.length - 1) {
				kk = kk + cloumnsName[m];
			} else {
				if ("acquisition_time".equals(cloumnsName[m])) {
					kk = kk
							+ "to_char(acquisition_time,'YYYY-MM-DD HH24:MI:SS') as acquisition_time,";
				} else {
					kk = kk + cloumnsName[m] + ",";
				}
			}

		}
		kk.subSequence(0, kk.length() - 2);

		String product = cloums + kk + formTable;
		// 获取总条数
		int total = 0;
		if ("1".equals(totalNum)) {
			total = 1;
		} else {
			totalNum += formTable;
			total = sagdDao.getCounts(totalNum);
		}
		if ("totalNum".equals(totalNumFlag)) {
			map.put("totalNum", total + "");
			return map;
		}
		// 排序
		if (!"".equals(sort) && !"acquisition_time".equals(sort)) {
			for (String cloumn : cloumnsName) {
				if (cloumn.equals(sort)) {
					product += " order by " + cloumn + " " + order;
					break;
				}
			}
		} else {
			product += " order by acquisition_time desc";
		}
		// 计算分页
		PageControl control = new PageControl();
		// 当前页
		List<Object[]> lo = new ArrayList<Object[]>();
		control.setInt_num(rowsPerpage);
		if ("export".equals(totalNumFlag)) {
			lo = sagdDao.searchEverySql(product);
		} else {
			control.init(pageNo, total);
			// 开始条数
			int start = control.getStart() - 1;
			lo = sagdDao.serarchSagdlng(product, start, rowsPerpage);
		}

		if (lo != null && 0 < lo.size()) {
			// 生成树节点json文档
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
	public HashMap<String, Object> searchSagd1qtjc(String startDate,
			String endDate, int pageNo, String sort, String order,
			int rowsPerpage, String totalNumFlag) {
		JSONArray jsonArr = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		JSONObject tjo = null;
		String cloums = "select   ";
		String formTable = " from PC_RD_HTSTATION_Z1SAGD_T a where 1=1 ";
		String totalNum = "select count(*) ";
		String kk = "";
		Date today = new Date();
		String nowdate = DateBean.getTimeStr(today);

		if ("".equals(startDate)) {
			startDate = nowdate.substring(0, 10) + " 00:00:00";
		} else {
			startDate = startDate + ":00";
		}
		if ("".equals(endDate)) {
			endDate = nowdate.substring(0, 10) + " 23:59:59";
		} else {
			endDate = endDate + ":00";
		}
		formTable = formTable + " and acquisition_time between TO_DATE('"
				+ startDate + "','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"
				+ endDate + "','YYYY-MM-DD HH24:MI:SS')";

		String[] cloumnsName = { "acquisition_time", "KRQT_ND1", "KRQT_ND2",
				"YDQT_ND1", "YDQT_ND2", "YDQT_ND3", "YDQT_ND4", "YDQT_ND5",
				"YDQT_ND6" };
		for (int m = 0; m < cloumnsName.length; m++) {
			if (m == cloumnsName.length - 1) {
				kk = kk + cloumnsName[m];
			} else {
				if ("acquisition_time".equals(cloumnsName[m])) {
					kk = kk
							+ "to_char(acquisition_time,'YYYY-MM-DD HH24:MI:SS') as acquisition_time,";
				} else {
					kk = kk + cloumnsName[m] + ",";
				}
			}

		}
		kk.subSequence(0, kk.length() - 2);

		String product = cloums + kk + formTable;
		// 获取总条数
		int total = 0;
		if ("1".equals(totalNum)) {
			total = 1;
		} else {
			totalNum += formTable;
			total = sagdDao.getCounts(totalNum);
		}
		if ("totalNum".equals(totalNumFlag)) {
			map.put("totalNum", total + "");
			return map;
		}
		// 排序
		if (!"".equals(sort) && !"acquisition_time".equals(sort)) {
			for (String cloumn : cloumnsName) {
				if (cloumn.equals(sort)) {
					product += " order by " + cloumn + " " + order;
					break;
				}
			}
		} else {
			product += " order by acquisition_time desc";
		}
		// 计算分页
		PageControl control = new PageControl();
		// 当前页
		List<Object[]> lo = new ArrayList<Object[]>();
		control.setInt_num(rowsPerpage);
		if ("export".equals(totalNumFlag)) {
			lo = sagdDao.searchEverySql(product);
		} else {
			control.init(pageNo, total);
			// 开始条数
			int start = control.getStart() - 1;
			lo = sagdDao.serarchSagd1qtjc(product, start, rowsPerpage);
		}

		if (lo != null && 0 < lo.size()) {
			// 生成树节点json文档
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
	public JSONObject searchSagdCurve(String wellName, String startDate,
			String endDate) throws Exception {

		JSONObject jsonObject = new JSONObject();
		List<Long> timeList = new ArrayList<Long>();// 采集时间
		List<BigDecimal> pressList = new ArrayList<BigDecimal>();// I井主管压力
		List<String> wellNames = new ArrayList<String>();// 井号
		List<BigDecimal> maxList = new ArrayList<BigDecimal>();// 注汽压力max
		List<BigDecimal> minList = new ArrayList<BigDecimal>();// 注汽压力min

		if ("".equals(startDate)) {
			startDate = startDate + " 00:00:00";
		} else {
			startDate = startDate + ":00";
		}
		if ("".equals(endDate)) {
			endDate += " 23:59:59";
		} else {
			endDate = endDate + ":00";
		}
		String formTable = " from  PC_RD_WELL_SAGD_V  where 1=1  and ACQUISITION_TIME between TO_DATE('"
				+ startDate
				+ "','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"
				+ endDate + "','YYYY-MM-DD HH24:MI:SS') and JHMC ='"+wellName+"' ";

		/*
		 * "ACQUISITION_TIME"; //采集时间 "JHMC";//, --井号名称' "I_PRESSURE_PRESS"
		 * I井主管压力 "PP_SX";//注汽压力上限值 "PP_YJ";//注汽压力下限值
		 */
		String[] cloumnsName = { "JHMC", "I_PRESSURE_PRESS", "PP_SX", "PP_YJ" };

		String hql = "select ";

		hql += "to_char(ACQUISITION_TIME,'YYYY-MM-DD hh24:mi:ss') as ACQUISITION_TIME";

		for (int m = 0; m < cloumnsName.length; m++) {
			hql = hql + ", " + cloumnsName[m];
		}

		hql += formTable + "   order by ACQUISITION_TIME ";

		List<Object[]> lo = sagdDao.queryInfo(hql);

		if (lo != null && 0 < lo.size()) {
			for (int i = 0; i < lo.size(); i++) {
				Object[] o = lo.get(i);
				String times = o[0].toString();
				SimpleDateFormat format = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				Date date;
				long time = 0;
				try {
					date = format.parse(times);
					time = date.getTime();
				} catch (ParseException e) {
					e.printStackTrace();
				}
	
				timeList.add(time);
				if (o[1] != null) {
					wellNames.add(o[1] + "");
				}
				pressList.add((BigDecimal) o[2]);
				
				if (o[3] != null) {
					maxList.add((BigDecimal) o[3]);
				}
				
				if (o[4] != null) {
					minList.add((BigDecimal) o[4]);
				}
			}
			
			int flag=timeList.size()-maxList.size();
			for(int i=0;i<flag;i++){
				maxList.add(maxList.get(0));
				minList.add(minList.get(0));
			}
		}
		jsonObject.put("time", timeList);
		jsonObject.put("wellName", wellNames);
		jsonObject.put("press", pressList);
		jsonObject.put("max", maxList);
		jsonObject.put("min", minList);
		
		return jsonObject;

	}

}
