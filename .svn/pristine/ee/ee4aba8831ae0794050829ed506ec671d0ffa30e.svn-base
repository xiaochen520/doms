package com.echo.service;

import java.util.List;

import com.echo.dto.PcRpdGqlhzzhT;

import net.sf.json.JSONObject;

public interface GQLHZZHService {

	public JSONObject searchDataSet(String txtDate)throws Exception;

	public boolean saveData(List<PcRpdGqlhzzhT> gqList)throws Exception;

	public List<PcRpdGqlhzzhT> searchCheckData(String rPDGQLHZID, String bbsj)throws Exception;

	public List<Object[]> searchExportDataFirst(String txtDate,
			String gqfirstSql);

	public List<Object[]> searchExportDataThree(String txtDate, String gqfourSql);

	public List<Object[]> searchExportDataZH(String punSql);

	public List<String> dayreport()throws Exception;

}
