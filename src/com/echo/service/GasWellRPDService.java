package com.echo.service;

import java.util.HashMap;
import java.util.List;

import com.echo.dto.PcRpdGasWelldT;

import net.sf.json.JSONObject;

public interface GasWellRPDService {

public	HashMap<String, Object> searchGWRPD(String oil, String areablock1, String blockstationname1,String rulewellname1,String startDate, String endDate, String totalNum, int pageNo,String sort, String order, int rowsPerpage);

public JSONObject searchGasWelRPDWH(String gasName, String startDate,
		String endStart, int pageNo, String sort, String order, int rowsPerpage)throws Exception;

public String searchWelID(String welName);

public boolean saveOrupdateGasRPDWH(PcRpdGasWelldT wh)throws Exception;

public List<PcRpdGasWelldT> searchOnly(String gasWelldid, String wellName,
		String reportDate)throws Exception;

public boolean removeGasRPDWH(String whId)throws Exception;

}
