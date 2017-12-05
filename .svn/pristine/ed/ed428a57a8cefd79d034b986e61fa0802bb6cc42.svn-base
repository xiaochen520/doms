package com.echo.service;

import java.util.HashMap;
import java.util.List;

import com.echo.dto.PcCdIpUsedT;

import net.sf.json.JSONArray;

public interface IPUsedService {

public HashMap<String, Object> searchDatas(String category, String area,String unit,String INSTRU_1TN, String shebeiName, String ip, String code2,
			String isUsed,String rlastOdate,String txtDate,String txtDate1, int pageNo, String sort, String order,int rowsPerpage, String totalNum)throws Exception;

public JSONArray searchShebei(String arg)throws Exception;
public JSONArray searchArea(String arg)throws Exception;
public JSONArray searchCategory(String arg)throws Exception;
public JSONArray searchDevice(String arg)throws Exception;

public List<PcCdIpUsedT> searchIpId(String id)throws Exception;

public boolean updateIpUsed(PcCdIpUsedT ip)throws Exception;

public String searchSegmentId(String segmentId)throws Exception;

public JSONArray searchUnit()throws Exception;

public JSONArray searchOnChangeUnit(String catid, String arid)throws Exception;

public JSONArray searchInstruData()throws Exception;

public JSONArray searchOnChangeSB(String catid,String shid)throws Exception;

public List<Object[]> searchOrgID(String unit)throws Exception;

}
