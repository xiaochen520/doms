package com.echo.service;

import java.util.HashMap;
import java.util.List;

import com.echo.dto.PcProWellStimDaily;

import net.sf.json.JSONArray;

public interface MeasuresService {

	public HashMap<String, Object> searchData(String oilName, String groupTeam,
			String wellname, String stime, String etime, int pageNo,
			String sort, String order, int rowsPerpage, String totalNum,
			String deptype)throws Exception;

	public JSONArray searchStmiuType()throws Exception;

	public List<PcProWellStimDaily> serarchCheckOnly(String mEASUREID,
			String orgId, String wellName,String START_DATETIME,String stmiuCode)throws Exception;

	public boolean saveOrUpdate(List<PcProWellStimDaily> stimDList)throws Exception;

	public List<Object[]> searchCOnly(String pDESCRIPTION)throws Exception;

	public String searchRem(String pDESCRIPTION);

	public boolean removeDatas(String mEASUREID)throws Exception;

	public String searchOrd_Id(String wELLNAME);

}
