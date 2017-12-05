package com.echo.service;

import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.dto.PcRpdWaterfloodingWellT;
import com.echo.dto.PcRpdWaterfloodingWellbT;

public interface FloodingRPDService {

public 	HashMap<String, Object> searchData(String oilName,
			String groupTeam, String stationName, String maniName,
			String wellName, String stime, String etime, int pageNo,
			String sort, String order, int rowsPerpage, String totalNum,String deptype)throws Exception;
public List<PcRpdWaterfloodingWellT> searchCheckOnly(String waterfloodingWellid, String cjsj, String rpdID) throws Exception;

public boolean saveOrUpdate(PcRpdWaterfloodingWellT rpd)throws Exception;

public boolean removeWaterRPD(String wellrpdid,String deptype)throws Exception;

public String searchWFLoodID(String wellname);
public JSONArray searchChangeGroupOnQW(String groupName, String injectName)throws Exception;
public JSONObject searchOnChangeMany(String groupName)throws Exception;
public List<String> Dataready (String proceduresName, String date, String param) throws Exception;
public List<String> Automate (String proceduresName, String date,String userid,String param) throws Exception;
public boolean saveOrUpdateB(PcRpdWaterfloodingWellbT rPDb)throws Exception;
public List<PcRpdWaterfloodingWellbT> searchCheckbOnly(String waterFloodingID,
		String cjsj, String rpdid)throws Exception;

}
