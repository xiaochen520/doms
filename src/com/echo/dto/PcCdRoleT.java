package com.echo.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * PcCdRoleT entity. @author MyEclipse Persistence Tools
 */

public class PcCdRoleT  implements java.io.Serializable {

	private static final long serialVersionUID = -1183037216449678473L;
	private String roleid;
     private String roleName;
     private String roleDescribed;
     private Date rlastOdate;
     private String rlastOper;
	private String remark;
     private Set<PcCdUserT> pcCdUserTs = new HashSet<PcCdUserT>(0);
     private Set<PcCdRightT> pcCdRightTs = new HashSet<PcCdRightT>(0);
 		


    // Constructors

    /** default constructor */
     public Date getRlastOdate() {
 		return rlastOdate;
 	}

 	public void setRlastOdate(Date rlastOdate) {
 		this.rlastOdate = rlastOdate;
 	}

 	public String getRlastOper() {
 		return rlastOper;
 	}

 	public void setRlastOper(String rlastOper) {
 		this.rlastOper = rlastOper;
 	}
    public PcCdRoleT() {
    }

	/** minimal constructor */
    public PcCdRoleT(String roleName) {
        this.roleName = roleName;
    
    }
    
    /** full constructor */
    public PcCdRoleT(String roleName, String roleDescribed, Date rlastOdate, String rlastOper,  String remark, Set<PcCdUserT> pcCdUserTs, Set<PcCdRightT> pcCdRightTs) {
        this.roleName = roleName;
        this.roleDescribed = roleDescribed;
        this.rlastOdate = rlastOdate;
        this.rlastOper = rlastOper;
     
        this.remark = remark;
        this.pcCdUserTs = pcCdUserTs;
        this.pcCdRightTs = pcCdRightTs;
        
    }

   
    // Property accessors

    public Set<PcCdUserT> getPcCdUserTs() {
		return pcCdUserTs;
	}

	public void setPcCdUserTs(Set<PcCdUserT> pcCdUserTs) {
		this.pcCdUserTs = pcCdUserTs;
	}

	public Set<PcCdRightT> getPcCdRightTs() {
		return pcCdRightTs;
	}

	public void setPcCdRightTs(Set<PcCdRightT> pcCdRightTs) {
		this.pcCdRightTs = pcCdRightTs;
	}

	public String getRoleid() {
        return this.roleid;
    }
    
    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }

    public String getRoleName() {
        return this.roleName;
    }
    
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDescribed() {
        return this.roleDescribed;
    }
    
    public void setRoleDescribed(String roleDescribed) {
        this.roleDescribed = roleDescribed;
    }

    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }








}