package com.echo.dao;

import java.util.List;

import com.echo.dto.PcCdSubcoolCalParamsInfoT;
import com.echo.dto.PcCdSubcoolDefaultParam;
import com.echo.dto.PcRdSubcoolCalResultsT;
import com.echo.dto.PcRdSubcoolModifyNewT;
import com.echo.dto.PcRdSubcoolModifyT;
import com.echo.dto.PcRdSuggestionRulesT;

/**
 * 
 * @Company: Etrol
 * @ClassName: SubcoolDao 
 * @author LIJUN
 * @date 2015-8-20
 */
public interface SubcoolDao {

	public boolean addOrUpdateSubcoolDefaultParam(PcCdSubcoolDefaultParam subcoolParam);
	public Object[] getSubcoolDefaultParam(String sql);
	
	public PcCdSubcoolCalParamsInfoT getCalParamsById(String id);
	public PcCdSubcoolCalParamsInfoT findCalParsmsBySagd(String sagdId);
	public boolean addSubcoolCalParams(PcCdSubcoolCalParamsInfoT alarmParam);
	public boolean updataSubcoolCalParams(PcCdSubcoolCalParamsInfoT alarmParam);
	public boolean removeSubcoolCalParams(PcCdSubcoolCalParamsInfoT alarmParam);

	public List<Object[]> searchSubcoolAlarmParams(String hql, int start,
			int rowsPerpage);
	
	public PcRdSubcoolCalResultsT getCalResultsById(String id);
	public boolean addSubcoolCalResults(PcRdSubcoolCalResultsT calResults);
	public boolean updataSubcoolCalResults(PcRdSubcoolCalResultsT calResults);
	public List<Object[]> searchSubcoolCalResults(String hql, int start,
			int rowsPerpage, String[] cloumnsName);
	
	public List<Object[]> queryInfo(String hql);
	public int getCounts(String sql);
	public Object[] getSubcoolCalResult(String hql, String[] cloumnsName);
	
	public boolean addSuggestionrules(PcRdSuggestionRulesT suggestion);
	
	public PcRdSubcoolModifyNewT getSubcoolModifyNew(String id);
	
	public boolean updateSubcoolModifyNew(PcRdSubcoolModifyNewT modify);
	
	public boolean addSubcoolModify(PcRdSubcoolModifyT modify);
}
