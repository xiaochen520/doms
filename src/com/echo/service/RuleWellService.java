package com.echo.service;


import java.util.HashMap;
import java.util.List;

import com.echo.dto.PcCdRuleWellT;
import com.echo.dto.PcRpdRuleWellProdT;
import com.echo.dto.PcRpdRuleWellProdbT;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface RuleWellService {
	
	/**
	 * 获取油井树结构
	 * @param dyear 
	 * @param 
	 * @return
	 */
	public JSONObject  searchRullWell(String qk,String zh,String jh,String gh,String name,String jrbz1,String dyear, int pageNo,String sort,String order,int rowsPerpage);
	
	public boolean removeStationInfo(String arg,String orgid);
	public JSONArray queryOilSationInfo(String arg);
	public JSONArray queryAreablockInfo(String arg);
	public JSONArray queryStationInfo(String oilid,String areaid,String selecteValue);
	public JSONArray queryWellInfo(String arg,String orgid);
	public JSONObject cascadeInit();

	public List<PcCdRuleWellT> searchRuleByName(String wellName)throws Exception;

	public boolean addRule(PcCdRuleWellT rule) throws Exception;

	public List<PcCdRuleWellT> searchRuleById(String rule_wellid, String wellName) throws Exception;

	public boolean updateRule(PcCdRuleWellT rule) throws Exception;

	public JSONArray querywellAreaSelf(String wellAreaSelf)throws Exception;

	public String serarchWellAreaSelf(String id)throws Exception;

	public HashMap<String, Object> searchOnExport(String oilstationname1,String blockstationname1,String rulewellname1,String areablock,String jrbz1,String dyear,String totalNum)throws Exception;

	public HashMap<String, Object> searchRullWellRPD(String oil, String banzu,String station, String boilersName, String well, String shstatus,String startDate, String endDate, int pageNo, String sort,String order, int rowsPerpage, String totalNum,String deptype);

	public JSONArray queryTeamInfo(String arg)throws Exception;

	public String searchRuleOrgId(String wellName)throws Exception ;

	public List<PcRpdRuleWellProdT> searchOnlyNameRq(String orgId, String rq,
			String rpdId)throws Exception;

	public boolean saveRpdRull(PcRpdRuleWellProdT rpdR)throws Exception;

	public List<PcRpdRuleWellProdbT> searchOnlyNameRqB(String orgId, String rq,
			String rpdbId)throws Exception;

	public boolean saveRpdRullB(PcRpdRuleWellProdbT rpdbR)throws Exception;

	public boolean removeRullRPDId(String rullRPDId ,String deptype)throws Exception;
	
	public List<String> Dataready (String proceduresName, String date, String param) throws Exception;
	public List<String> Automate (String proceduresName, String date,String userid,String param) throws Exception;
	

	
	
	
}
