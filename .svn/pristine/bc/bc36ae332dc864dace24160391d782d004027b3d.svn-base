package com.echo.dao;

import java.util.List;
import java.util.Map;

import com.echo.dto.PcRpdCrudeTransitionT;
import com.echo.dto.PcRpdReportMessageT;
import com.echo.dto.PcRpdU1SagdAutoT;
import com.echo.dto.PcRpdU1SagdGeneralT;
import com.echo.dto.PcRpdU1SagdLiquidT;
import com.echo.dto.PcRpdU1SagdPivotalT;
import com.echo.dto.PcRpdU2GeneralT;

public interface MBZHRBDao {
	List<Object[]> searchObjectList(String hql) throws Exception;
	
	List<PcRpdCrudeTransitionT> searchCrudeTransition(String hql) throws Exception;
	
	
	List<PcRpdU1SagdPivotalT> searchU1SagdPivotal(String hql) throws Exception;
	
	
	public boolean updateZHRB(PcRpdReportMessageT reportMessage,PcRpdU2GeneralT u2General,PcRpdCrudeTransitionT crudeTransition0,PcRpdCrudeTransitionT crudeTransition1,PcRpdCrudeTransitionT crudeTransition2)throws Exception;

	@SuppressWarnings("unchecked")
	Map seachSystemInit(String rq) throws Exception;

	List<Object[]> searchCalcNum(String timeCalssql);

	boolean updatePivotal(PcRpdU1SagdPivotalT pivotalRec)throws Exception;

	List<PcRpdU1SagdAutoT> searchU1SagdAuto(String hql)throws Exception;

	boolean updateAuto(PcRpdU1SagdAutoT autoRec)throws Exception;

	List<PcRpdU1SagdGeneralT> SreachGeneral(String hql)throws Exception;

	boolean updateGen(PcRpdU1SagdGeneralT sagdGen)throws Exception;

	List<PcRpdU1SagdLiquidT> SreachLiuqid(String hql)throws Exception;

	boolean updateLiq(PcRpdU1SagdLiquidT sagdliq)throws Exception;
}
