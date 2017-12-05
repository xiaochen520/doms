package com.echo.service;

import java.util.HashMap;
import java.util.List;

import com.echo.dto.PcRpdThinWellwT;
import com.echo.dto.PcRpdThinWellwbT;

public interface ThinWellRPDWHService {

public	HashMap<String, Object> searchData(String oilName,String groupTeam,  String maniName,
			String wellName, String stime, String etime, int pageNo,String sort, String order, int rowsPerpage, String totalNum,String deptype);
public List<PcRpdThinWellwT> searchRpdId(String wellNameEdit, String rq,String THINWELLRPD)throws Exception;

public boolean saveRPD(PcRpdThinWellwT rpdT)throws  Exception;

public boolean removeThinRPD(String rpdId ,String deptype)throws Exception;

public String searchWellID(String wellNameEdit);
public List<PcRpdThinWellwbT> searchRpdBId(String wellid, String rq,String rpdBId)throws Exception;
public boolean saveRPDB(PcRpdThinWellwbT rpdT)throws Exception;
public List<String> Dataready (String proceduresName, String date, String param) throws Exception;
public List<String> Automate (String proceduresName, String date,String userid,String param) throws Exception;

}
