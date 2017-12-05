package com.echo.dao.impl;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;

import oracle.jdbc.OracleTypes;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.echo.dao.Query;
import com.echo.dao.ZhuWaterPumpDao;
import com.echo.dto.PcRpdUThinWaterAutoT;

public class ZhuWaterPumpDaoImpl extends HibernateDaoSupport  implements ZhuWaterPumpDao,Serializable {

	@Override
	public List<Object[]> searchZhuPump(String sql) {
		// TODO Auto-generated method stub
		return Query.getSqlQuery(this.getHibernateTemplate(), sql);
	}

	@Override
	public List<PcRpdUThinWaterAutoT> SreachPumpAuto(String hql)throws Exception {
		List<PcRpdUThinWaterAutoT> autoList = null;
		autoList = (List<PcRpdUThinWaterAutoT>)this.getHibernateTemplate().find(hql);
		return  autoList;
	}

	@Override
	public boolean updatePumpAuto(PcRpdUThinWaterAutoT waterA) throws Exception {
		boolean flg = true;
		this.getHibernateTemplate().merge(waterA);
		return flg;
	}

//	public List<String> getSAGDFP() throws Exception {
//		List<String> rr = new ArrayList<String>();
//		Date b_date = new Date();
//		ResultSet rs =null; 
//		String procdure = "{Call p_gene_daily_u_thin_oil_g(?,?,?)}";  
//		            CallableStatement cs = this.getHibernateTemplate().getSessionFactory().openSession().connection().prepareCall(procdure);  
//		            cs.setDate(1,   new java.sql.Date(b_date.getTime()));  
//		            cs.registerOutParameter(2, OracleTypes.INTEGER); 
//		            cs.registerOutParameter(3, OracleTypes.VARCHAR); 
//		            cs.execute();
//		            //System.out.println(cs.getInt(3)+cs.getString(4));
//		            rr.add(String.valueOf(cs.getInt(2)));
//		            rr.add(cs.getString(3));
//		            
//		      return rr;
//	}


}
