package com.echo.service;

import java.util.Date;
import java.util.List;

import com.echo.dto.PcCdRuleWellT;
import com.echo.dto.PcCdGasstation;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface GasstationService {

	public JSONObject queryStationBasicInfo(String jrbz,String stationNumber,String lhz,String boilersName,String area,String type,String dyearC,int pageNo,String sort,String order,int rowsPerpage) throws Exception;

	public JSONArray queryAreablockInfo(String arg);
	public JSONArray queryStationInfo(String combinationid,String oilid,String areaid,String selecteValue);
	public boolean saveOrUpdate(String zzzmc2,String stationid2,String org_id2,byte ghs2,byte cygs2,String cyggg2,long zybs2,long jrbz2,String rlastOper,Date rlastOdate,String zzzlx2,String remark);
	public boolean removeStationInfo(String arg);
//添加
	public boolean addSta(PcCdGasstation sta)throws Exception;
//更新
	public boolean updateSta(PcCdGasstation sta) throws Exception;
	public JSONArray queryOilSationInfo();
	public JSONArray searchSationInfo();
	public List<PcCdGasstation> searchstatinById(String stationid, String stationName) throws Exception;
	
	public JSONArray searchStatusValue();
	public List<PcCdGasstation> searchstatinByName(String stationName)throws Exception;
	public JSONObject cascadeInit();
	public JSONArray gasstationype();
	public boolean updateRule(PcCdRuleWellT rule) throws  Exception;
	

}
