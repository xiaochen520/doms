package com.echo.service;

import java.util.HashMap;
import java.util.List;
import com.echo.dto.PcRpdMollifierT;
import net.sf.json.JSONArray;

public interface RhqyxrbService {

	public JSONArray searchSyjyxrb(String clz, String rhq, String txtDate,String setRHQ) throws Exception;

	public List<Object[]> searchDataEXcep(String txtDate, String fields,String jid);

	public List<Object[]> searchNotNull(String sqlNo);

	public JSONArray searchWaterMany(String clz, String rhq, String txtDate)throws Exception;

	public List<PcRpdMollifierT> SreachCheckOn(String rpdid, String clz,String rhq)throws Exception;

	public boolean updateData(PcRpdMollifierT wt)throws Exception;

	public int searchCalcNum();

	public List<Object[]> searchExportData(String punSql);

	public List<Object[]> searchExportData(String txtDate, String insMainSql ,String sclzmc,String rhq)throws Exception;

	public JSONArray searchSyjyxrb1(String clz, String rhq, String txtDate,
			String setRHQ)throws Exception;

	public HashMap<String, Object> searchRhqzs(String clz, String rhq,
			String startDate, String endDate, int pageNo, String sort,
			String order, int rowsPerpage, String totalNum) throws Exception;


}
