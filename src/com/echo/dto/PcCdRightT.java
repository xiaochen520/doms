package com.echo.dto;

import java.sql.Clob;
import java.util.HashSet;
import java.util.Set;


/**
 * PcCdRightT entity. @author MyEclipse Persistence Tools
 */

public class PcCdRightT  implements java.io.Serializable {
	private static final long serialVersionUID = -3214765380659951298L;
	private String rightid;
     private String menuid;
     private String rightName;
     private String menuCode;
     private Clob displayColumn;
     private String remark;
  
     private Set<PcCdRoleT> pcCdRoleTs = new HashSet<PcCdRoleT>(0);
     public String getMenuid() {
		return menuid;
	}



	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}



	public String getRemark() {
		return remark;
	}



	public void setRemark(String remark) {
		this.remark = remark;
	}

	

    // Constructors

    /** default constructor */
    public PcCdRightT() {
    }


    
    /** full constructor */
    public PcCdRightT(String menuid, String rightName, String menuCode, Clob displayColumn, String remark, Set<PcCdRoleT> pcCdRoleTs) {
        this.menuid = menuid;
        this.rightName = rightName;
        this.menuCode = menuCode;
        this.displayColumn = displayColumn;
        this.remark = remark;
        this.pcCdRoleTs = pcCdRoleTs;
    }

   
    // Property accessors

    public Set<PcCdRoleT> getPcCdRoleTs() {
		return pcCdRoleTs;
	}

	public void setPcCdRoleTs(Set<PcCdRoleT> pcCdRoleTs) {
		this.pcCdRoleTs = pcCdRoleTs;
	}

	public String getRightid() {
        return this.rightid;
    }
    
    public void setRightid(String rightid) {
        this.rightid = rightid;
    }



    public String getRightName() {
        return this.rightName;
    }
    
    public void setRightName(String rightName) {
        this.rightName = rightName;
    }

    public String getMenuCode() {
        return this.menuCode;
    }
    
    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public Clob getDisplayColumn() {
        return this.displayColumn;
    }
    
    public void setDisplayColumn(Clob displayColumn) {
        this.displayColumn = displayColumn;
    }




   








}