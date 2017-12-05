package com.echo.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.echo.dto.PcCdInstruMentationDT;
import com.echo.dto.PcCdInstruMentationT;
import com.echo.dto.User;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public interface InstruMentationService {

	public HashMap<String, Object> searchInstru(String oilName,
			String objectCode, String areaName, String staName,
			String ownObject, String iNSTRU_1TN, String iNSTRUCLN,
			String fACTORYNS, String iNSTRUC3N, String iNSTRUSTNS,
			String superStns, String txtDate, String txtDate1, int pageNo,
			String sort, String order, int rowsPerpage, String totalNum)throws Exception;
	public JSONObject cascadeInit();
	public JSONArray searchOnChangeArea(String oilName, String objectCode,
			String areaName,String args)throws Exception;
	public JSONArray searchOnChangeOwnObject(String oilName,String objectCode,String areaName,String staName,String args)throws Exception;
	public JSONArray searchOnChangeInstru(String objectCode,String INSTRU_1TN) throws Exception;
	public JSONArray searchOnChangeFactoryns(String INSTRU_CLN)throws Exception;
	public JSONArray searchOnChangeInstruc3n(String INSTRU_CLN,String FACTORY_NS)throws Exception;
	public JSONArray searchOnChangeSuperStns(String objectCode) throws Exception;
	public JSONArray searchGHID() throws Exception;
	public JSONArray searchOnChangeYBGhuo(String objectCode, String iNSTRU_1TN)throws Exception;
	public List<PcCdInstruMentationT> searchOnlyData(String iNSTRUMENTID)throws Exception;
	
	public List<PcCdInstruMentationDT> searchOnlyDataDTDB(String iNSTRUMENTID)throws Exception;
	
	public boolean removeInstrumention(String INSTRUMENT_ID);
	public boolean SaveOrUpdateInstru(PcCdInstruMentationT im) throws Exception;
	
	public boolean SaveOrUpdateInstruDT(PcCdInstruMentationDT bm) throws Exception;
	
	public List<PcCdInstruMentationT> searchYbId(String Id,String name,String org)throws Exception;
	
	public List<Object[]> searchAllData(String oilName, String objectCode, String ownObject, String staName, String areaName, String iNSTRUCLN, String iNSTRUSTNS, String superStns, String pointCode, String alamOr, String myDate)throws Exception;
	
	public List<Object[]> searchMafDatas(String args)throws Exception;
	public List<Object[]> searchOilName(String ORG_ID , String type)throws Exception;
	
	//调用存储过程
	public List<String> creaTable(String pName, String oilName,
			String objectCode, String ownObject, String staName,
			String areaName, String iNSTRUCLN, String iNSTRUSTNS,
			String superStns, String pointCode, String alamOr,String userid, String MyDate)throws Exception;
	//调用存储过程 插入
	public List<String> creaPIns(String pName, String iNSTRUMENTID,
			String editInstruGHID, String editInstruGHIDN,
			String editSuperName, String editObjectCode, String editInstruSBMC,
			String editInstruSJSB, String editInstruZT, String editRemark,
			String user, Date date)throws Exception;
	public List<Object[]> searchUnique(String editObjectCode,
			String editSuperName, String editInstruGHIDN, String editInstruSBMC);
	


	
	

	
	
	
	

	
	
	
}
