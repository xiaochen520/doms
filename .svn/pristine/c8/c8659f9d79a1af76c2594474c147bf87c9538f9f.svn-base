package com.echo.service;


import java.util.List;

import net.sf.json.JSONObject;

import com.echo.dto.PcRpdU1SagdAutoT;
import com.echo.dto.PcRpdU1SagdGeneralT;
import com.echo.dto.PcRpdU1SagdLiquidT;
import com.echo.dto.PcRpdU1SagdPivotalT;
import com.echo.dto.PcRpdUThinOilAutoT;
import com.echo.dto.PcRpdUThinOilGeneralT;

public interface XyyyclService {
	
	public JSONObject searchZHRB(String rq) throws Exception;
	
	public List<PcRpdU1SagdPivotalT> searchU1SagdPivotal(String id,String rq)throws Exception;
	
	public int searchCalcNum();

	public boolean updatePivotal(PcRpdU1SagdPivotalT pivotalRec) throws Exception;

	public List<PcRpdU1SagdAutoT> searchU1SagdAuto(String id,String rq)throws Exception;

	public boolean updateAuto(PcRpdU1SagdAutoT autoRec) throws Exception;

	public List<PcRpdU1SagdGeneralT> SreachGeneral(String id,String rq)throws Exception;

	public boolean updateGeneral(PcRpdU1SagdGeneralT sagdGen) throws Exception;

	public List<PcRpdU1SagdLiquidT> SreachLiquid(String id,String rq)throws Exception;

	public boolean updateLiq(PcRpdU1SagdLiquidT sagdliq)throws Exception;

	public List<List<Object[]>> searchExportData(String rq) throws Exception;
	
	public List<Object[]> searchExportDataTime(String rq, String sql, String dateField, String talbelFalg) throws Exception;

	public List<Object[]> searchExportOther(String rq, String sql, String dateField, String talbelFalg) throws Exception;

	public List<Object[]> searchExportDataDay(String txtDate,String sagdGeneral, String sgt, String talbelFalg)throws Exception;

	// 原油处理
	public List<PcRpdUThinOilGeneralT> SreachOilGeneral(String zhId,String date)throws Exception;
	// 原油处理  审核    更新综合数据
	public boolean updateOilGeneral(PcRpdUThinOilGeneralT oil) throws Exception;

	public List<PcRpdUThinOilAutoT> SreachAutp(String id,String name)throws Exception;

	public boolean updateAuto(PcRpdUThinOilAutoT auto)throws Exception;


}
