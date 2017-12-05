package com.echo.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * PcCdUserT entity. @author MyEclipse Persistence Tools
 */

public class PcCdUserT implements java.io.Serializable {
	private static final long serialVersionUID = 5858228638417271232L;
	private String userid;
	private String departmentid;
	private String operCode;
	private String operName;
	private String operPass;
	private String operSdbsadbse;
	private Date audbshorDate;
	private Date sdbsadbseDate;
	private Date LLoginDate;
	private Date LLogoudbsDate;
	private String rlastOper;
	private Date rlastOdate;
	private String remark;
	private Set<PcCdRoleT> pcCdRoleTs = new HashSet<PcCdRoleT>(0);
	
	

	/** default constructor */
	public PcCdUserT() {
	}

	
	// Property accessors

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	
	public String getDepartmentid() {
		return departmentid;
	}


	public void setDepartmentid(String departmentid) {
		this.departmentid = departmentid;
	}


	public String getOperCode() {
		return this.operCode;
	}

	public void setOperCode(String operCode) {
		this.operCode = operCode;
	}

	public String getOperName() {
		return this.operName;
	}

	public void setOperName(String operName) {
		this.operName = operName;
	}

	public String getOperPass() {
		return this.operPass;
	}

	public void setOperPass(String operPass) {
		this.operPass = operPass;
	}

	public String getOperSdbsadbse() {
		return this.operSdbsadbse;
	}

	public void setOperSdbsadbse(String operSdbsadbse) {
		this.operSdbsadbse = operSdbsadbse;
	}

	public Date getAudbshorDate() {
		return this.audbshorDate;
	}

	public void setAudbshorDate(Date audbshorDate) {
		this.audbshorDate = audbshorDate;
	}

	public Date getSdbsadbseDate() {
		return this.sdbsadbseDate;
	}

	public void setSdbsadbseDate(Date sdbsadbseDate) {
		this.sdbsadbseDate = sdbsadbseDate;
	}

	public Date getLLoginDate() {
		return this.LLoginDate;
	}

	public void setLLoginDate(Date LLoginDate) {
		this.LLoginDate = LLoginDate;
	}

	public Date getLLogoudbsDate() {
		return this.LLogoudbsDate;
	}

	public void setLLogoudbsDate(Date LLogoudbsDate) {
		this.LLogoudbsDate = LLogoudbsDate;
	}

	public String getRlastOper() {
		return this.rlastOper;
	}

	public void setRlastOper(String rlastOper) {
		this.rlastOper = rlastOper;
	}

	public Date getRlastOdate() {
		return this.rlastOdate;
	}

	public void setRlastOdate(Date rlastOdate) {
		this.rlastOdate = rlastOdate;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	// Constructors

	public Set<PcCdRoleT> getPcCdRoleTs() {
		return pcCdRoleTs;
	}

	public void setPcCdRoleTs(Set<PcCdRoleT> pcCdRoleTs) {
		this.pcCdRoleTs = pcCdRoleTs;
	}


	

}