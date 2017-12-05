package com.echo.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.echo.dto.PcCdRuleWellT;
import com.echo.dto.PcCdStationT;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface StationTService {

	public JSONObject queryStationBasicInfo(String dyear, String stationNumber,
			String lhz, String boilersName, String area, String type,
			String jrbz, int pageNo, String sort, String order, int rowsPerpage)
			throws Exception;

	public JSONArray queryAreablockInfo(String arg);

	public JSONArray queryStationInfo(String combinationid, String oilid,
			String areaid, String selecteValue, String views);

	public boolean saveOrUpdate(String zzzmc2, String stationid2,
			String org_id2, byte ghs2, byte cygs2, String cyggg2, long zybs2,
			long jrbz2, String rlastOper, Date rlastOdate, String zzzlx2,
			String remark);

	public boolean removeStationInfo(String arg, String org_id);

	// 添加
	public boolean addSta(PcCdStationT sta) throws Exception;

	// 更新
	public boolean updateSta(PcCdStationT sta) throws Exception;

	public JSONArray queryOilSationInfo(String arg);

	public JSONArray searchSationInfo();

	public JSONArray searchTeamOil();

	public List<PcCdStationT> searchstatinById(String blockstationid,
			String stationName) throws Exception;

	public JSONArray searchStatusValue();

	public JSONArray searchStatusValue1();

	public List<PcCdStationT> searchstatinByName(String blockstationName)
			throws Exception;

	public JSONObject cascadeInit();

	public JSONArray stationType();

	public boolean updateRule(PcCdRuleWellT rule) throws Exception;

	public HashMap<String, Object> searchOnExport(String dyear,
			String oilstationname1, String areablock1, String blockstationtype,
			String blockstationname, String jrbz1, String combination,
			String totalNum) throws Exception;
}
