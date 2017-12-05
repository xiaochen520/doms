package com.echo.service;


import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echo.dto.PcRpdUThinWaterAutoT;
import com.echo.dto.PcRpdUThinWaterGeneralT;

public interface XYWSCLService {
	
	
	public JSONObject searchXYWSDatas(String rq)throws Exception;
	public JSONArray searchWSCLOther(String txtDate)throws Exception;
	public List<Object[]> searchWSCLOther(String rq,String fields,String talbelFalg) throws Exception;
	public List<PcRpdUThinWaterGeneralT> SreachWaterGeneral(String rPDUTHINWATERGENERALID, String rq)throws Exception ;
	public List<PcRpdUThinWaterAutoT> SreachWaterAuto(String rPDUTHINWATERAUTOID, String rq)throws Exception ;
	public boolean updateWaterGeneral(PcRpdUThinWaterGeneralT waterGen)throws Exception;
	public boolean updateWaterAuto(PcRpdUThinWaterAutoT waterA)throws Exception;
	public int searchCalcNum();
	public List<PcRpdUThinWaterGeneralT> SreachWaterGen(String txtDate)throws Exception;
}
