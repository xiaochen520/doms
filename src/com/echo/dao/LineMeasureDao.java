package com.echo.dao;

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

public interface LineMeasureDao {

	public int getCounts(String sql);

	public List<Object[]> searchLineM(String timeCalssql) throws Exception;

	public List<Object[]> searchU2OIL(String timeCalssql);
	public List<PcRpd1836zh> Sreach1836zh(String hql) throws Exception ;
	public List<PcRpd1836> Sreach1836(String hql) throws Exception ;
	public List<PcRpdU2zyzzh> SreachU2zyzzh(String hql) throws Exception ;
	public List<PcRpdU2zyz> SreachU2zyz(String hql) throws Exception ;
	public List<PcRpdXzyzhgzh> SreachXzyzhgzh(String hql) throws Exception;
	public List<PcRpdXzyzhg> SreachXzyzhg(String hql) throws Exception;
	
	
	
	public List<PcRpdxczszh> Sreachxczszh(String hql) throws Exception ;
	public List<PcRpdxczs> Sreachxczs(String hql) throws Exception ;
	public boolean updatedata(Object us) throws Exception;

	public List<PcRpdU1DehydrationT> SreachN1tscs(String hql) throws Exception;

	public List<PcRpdCy1ScMessureT> Sreachcyfx1(String hql);

	public List<PcRpdCy2ScMessureT> Sreachcyfx2(String hql);

	public List<PcRpdXylScMessureT> Sreachxyfx3(String hql);

	public PcRpdCy1ScMessureT Sreachcyfx1rq(String hql);

	public List<PcRpdReportRhs2T> Sreachu2rhs(String hql);

	public List<Object[]> searchExportData(String sqls);

}
