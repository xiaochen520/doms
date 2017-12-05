package com.echo.dao;

import java.util.List;

import com.echo.dto.PcRpdCrudeTransitionT;
import com.echo.dto.PcRpdReportMessageT;
import com.echo.dto.PcRpdU2GeneralT;

public interface PublicDao {
	List<Object[]> searchObjectList(String hql) throws Exception;
	
	List<PcRpdCrudeTransitionT> searchCrudeTransition(String hql) throws Exception;
	
	
	List<PcRpdU2GeneralT> searchU2General(String hql) throws Exception;
	
	
	public boolean updateZHRB(PcRpdReportMessageT reportMessage,PcRpdU2GeneralT u2General,PcRpdCrudeTransitionT crudeTransition0,PcRpdCrudeTransitionT crudeTransition1,PcRpdCrudeTransitionT crudeTransition2)throws Exception;

}
