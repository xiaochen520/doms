package com.echo.service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.dto.User;

public interface SearchQueryAllService {

	public	JSONArray queryGroupInfo(String oilid)throws Exception;

	public JSONArray queryGuanHuiInfo(String oilid)throws Exception;

	public JSONArray queryOilGuanHuiInfo(String oilid)throws Exception;
//"cyzid":cyzid,"zzzid":zzzid,"teamid":teamid,"param":param
	
	public JSONArray searchGHcommon(String cyzid,String zzzid,String teamid,String param)throws Exception;

	public JSONObject searchOilChangeData(String oilid)throws Exception;

	public JSONArray searchAllGroup()throws Exception;

	public JSONArray searchAllSatation(String paramArg)throws Exception;

	public JSONArray searchAllMani(String groupMani,String stationMani, String ManiWell ,String nullParam)throws Exception;

	public JSONObject searchConnect(String oilUnitID, String groupTeamID,String stationID, String maniID, String wellNameID) throws Exception;

	public JSONObject searchOilGroupWaterInjec(String oilName, String groupName,String injeName) throws Exception;

	public JSONArray searchGroupWellOnchange(String teamName)throws Exception;

	public JSONArray searchOnChangeManiWell(String groupName,String stationName, String maniName,String arg)throws Exception;

	public JSONArray searchChangeGroupOnWell(String groupName)throws Exception;

	public JSONArray searchOnChangeManiRuleWell(String maniName)throws Exception;
	
	
	public JSONArray searchCYZTEAMJH(String CYZ,String TEAM,String arg)throws Exception;
	public JSONObject  searchALLCYZ(String arg) throws Exception;
	
	public JSONArray  searchTeam(String CYZ,String arg) throws Exception;
	public JSONArray  searchUserWell(User user) throws Exception;
	
	
	public JSONObject  searchALLZKCJD(String arg) throws Exception;
	
	
}
