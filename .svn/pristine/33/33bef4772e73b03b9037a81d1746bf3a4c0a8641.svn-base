package com.echo.service;

import java.util.HashMap;
import java.util.List;

import com.echo.dto.PcProWellHotWashing;
import com.echo.dto.PcProWellStimDaily;

import net.sf.json.JSONArray;

public interface RxqlwhService {

	public HashMap<String, Object> searchData(String oilName, String groupTeam,
			String wellname, String stime, String etime, int pageNo,
			String sort, String order, int rowsPerpage, String totalNum,
			String deptype)throws Exception;

	public boolean removeDatas(String flowing_paraffin_id)throws Exception;

	public List<Object[]> searchDatas(String sql);

	public List<PcProWellHotWashing> searchCheck(String hotWashId, String orgId,
			String wELLNAME, String eventDate, String stimCode);

	public boolean saveOrUpdate(List<PcProWellHotWashing> hotList)throws Exception;

	public JSONArray searchHOT_WASH_TYPE()throws Exception;

}
