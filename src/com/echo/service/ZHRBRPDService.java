package com.echo.service;


import java.util.List;

import com.echo.dto.PcRpdCrudeTransitionT;
import com.echo.dto.PcRpdReportMessageT;
import com.echo.dto.PcRpdU2GeneralT;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface ZHRBRPDService {
	public List<Object[]> searchU2_GENERAL(String rq,String fields, String talbelFalg) throws Exception;
	
	public JSONObject searchZHRB(String rq) throws Exception;
	
	public JSONArray searchYYJJ(String rq)throws Exception;
	
	public List<PcRpdCrudeTransitionT> searchCrudeTransition(String id,String gh,String rq,String lhzID)throws Exception;
	
	public List<PcRpdU2GeneralT> searchU2General(String id,String rq)throws Exception;
	
	public boolean updateZHRB(PcRpdReportMessageT reportMessage,PcRpdU2GeneralT u2General,PcRpdCrudeTransitionT crudeTransition0,PcRpdCrudeTransitionT crudeTransition1,PcRpdCrudeTransitionT crudeTransition2)throws Exception;
}
