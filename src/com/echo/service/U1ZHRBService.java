package com.echo.service;

import java.util.List;

import com.echo.dto.PcRpdCrudeTransitionT;
import com.echo.dto.PcRpdU1GeneralT;

import net.sf.json.JSONObject;

public interface U1ZHRBService {

public 	JSONObject searchU1ZHRB(String txtDate) throws Exception;

public boolean updateU1S(PcRpdU1GeneralT u1)throws Exception;

public List<PcRpdCrudeTransitionT> searchCrude(String rPDCRUDETRANSITIONID) throws Exception;

public boolean updateCrude(PcRpdCrudeTransitionT crude,PcRpdCrudeTransitionT crude0) throws Exception;

public List<PcRpdU1GeneralT> onAudit(String id)throws Exception;

public boolean updateOnAudit(PcRpdU1GeneralT u1g)throws Exception;

public List<Object[]> searchExportData(String txtDate, String sqls);

public List<Object[]> searchExportDataToDay(String txtDate, String sisSql);

public List<Object[]> searchExportDataGH(String txtDate, String thirdSqlLast);

public List<Object[]> searchExportAdd(String txtDate, String addsql)throws Exception;

public List<Object[]> searchExportDataGH2(String txtDate)throws Exception;

}
