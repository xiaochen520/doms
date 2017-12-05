package com.echo.service;

import java.util.List;

import com.echo.dto.PcRpd1836;
import com.echo.dto.PcRpd1836zh;
import com.echo.dto.PcRpdCy1ScMessureT;
import com.echo.dto.PcRpdCy2ScMessureT;
import com.echo.dto.PcRpdReportRhs2T;
import com.echo.dto.PcRpdU1DehydrationT;
import com.echo.dto.PcRpdU2zyz;
import com.echo.dto.PcRpdU2zyzzh;
import com.echo.dto.PcRpdXylScMessureT;
import com.echo.dto.PcRpdXzyzhg;
import com.echo.dto.PcRpdXzyzhgzh;
import com.echo.dto.PcRpdxczs;
import com.echo.dto.PcRpdxczszh;
import net.sf.json.JSONArray;
public interface LineMeasureService {

	public JSONArray searchLineM(String txtDate)throws Exception;
	public JSONArray searchLineM1(String txtDate)throws Exception;
	public JSONArray searchLineMN1(String txtDate)throws Exception;
	public JSONArray searchN1tscs(String txtDate)throws Exception;
	
	public JSONArray searchLinex1836rb(String txtDate)throws Exception;
	public JSONArray searchLinex1836rb1(String txtDate)throws Exception;
	public JSONArray searchLinexcgyrb(String txtDate)throws Exception;
	public JSONArray searchLinexcgyrb1(String txtDate)throws Exception;
	
	//2#转油站日报表
	public JSONArray searchLineU2zyzrb(String txtDate)throws Exception;
	public JSONArray searchLineU2zyzrb1(String txtDate)throws Exception;
	//返回2号转油站List
	public List<PcRpdU2zyzzh> SreachU2zyzzh(String id, String name)throws Exception;
	public List<PcRpdU2zyz> SreachU2zyz(String id, String name)throws Exception;
	//夏转油综合岗日报
	public JSONArray searchLineXzyzhgrb(String txtDate)throws Exception;
	public JSONArray searchLineXzyzhgrb1(String txtDate)throws Exception;
	//返回夏转油综合岗
	public List<PcRpdXzyzhgzh> SreachXzyzhgzh(String id, String name)throws Exception;
	public List<PcRpdXzyzhg> SreachXzyzhg(String id, String name)throws Exception;
	
	public List<Object[]> searchExportData(String txtDate, String fields)throws Exception;

	public List<Object[]> searchLineMeasure(String txtDate, String fields)throws Exception;
	public List<Object[]> searchLineMeasure1(String txtDate, String fields)throws Exception;
	public List<Object[]> searchLineMeasureN1(String txtDate, String fields)throws Exception;
	
	public JSONArray searchLineMs(String txtDate)throws Exception;
	public JSONArray searchLineMs1(String txtDate)throws Exception;
	public JSONArray searchLineMsN1(String txtDate)throws Exception;
	
	public JSONArray searchLineXy(String rq) throws Exception;
	public JSONArray searchLineMb(String rq) throws Exception;
	public List<Object[]> searchLineMeasureXY(String rq, String fields) throws Exception;
	
	public List<PcRpd1836zh> Sreach1836zh(String id, String name)throws Exception;
	public List<PcRpd1836> Sreach1836(String id, String name)throws Exception;
	
	public List<PcRpdxczszh> Sreachxczszh(String id, String name)throws Exception;
	public List<PcRpdxczs> Sreachxczs(String id, String name)throws Exception;

	public boolean updatedata(Object us) throws Exception ;
	public List<PcRpdU1DehydrationT> SreachN1tscsAuto(String id, String string)throws Exception;
	public List<Object[]> searchLineN1tscs(String txtDate, String fields)throws Exception;

	public JSONArray searchGrglzb(String txtDate)throws Exception;
	public List<Object[]> searchGrglzbXY(String string, String fields);
	public List<Object[]> searchGrglzbZSC(String txtDate, String sql1);
	public JSONArray searchFxjl1(List<String> date, String txtYear, String txtMonth);
	public List<Object[]> searchcyfx1(String string, String fields);
	public List<PcRpdCy1ScMessureT> Sreachfxcy1(String iD, String string);
	public JSONArray searchFxjl2(List<String> date, String txtYear,
			String txtMonth);
	public List<Object[]> searchcyfx2(String string, String fields);
	public JSONArray searchFxjl3(List<String> date, String txtYear,
			String txtMonth);
	public List<Object[]> searchxyfx3(String string, String fields);
	public JSONArray searchCysc(String txtDate);
	public List<PcRpdCy1ScMessureT> Sreachcyfx1(String id, String string);
	public List<PcRpdCy2ScMessureT> Sreachcyfx2(String id, String string);
	public List<PcRpdXylScMessureT> Sreachxyfx3(String id, String string);
	
	
	public PcRpdCy1ScMessureT Sreachcyfx1rq(String rQ);
	public JSONArray searchFxjlsc1(String date);
	public JSONArray searchFxjlsc2(String txtDate);
	public JSONArray searchFxjlsc3(String txtDate);
	public JSONArray searchU2rhz(String txtDate);
	public List<PcRpdReportRhs2T> Sreachu2rhs(String id, String string);
	public List<Object[]> searchExportDatas(String txtDate, String sqls);
	public List<Object[]> searchExportTopDatas(String txtDate, String others);



}
