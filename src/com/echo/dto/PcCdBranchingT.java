package com.echo.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * PcCdBranchingT entity. @author MyEclipse Persistence Tools
 */

public class PcCdBranchingT implements java.io.Serializable {

	// Fields

	private String branchingid;
	private String branchingName;
	private String branchingCode;
	private String combinationStation;
	private String rlastOper;
	private Date rlastOdate;
	private String remark;
	private Set pcCdManifoldTs = new HashSet(0);

	// Constructors

	/** default constructor */
	public PcCdBranchingT() {
	}

	/** full constructor */
	public PcCdBranchingT(String branchingName, String branchingCode,
			String combinationStation, String rlastOper, Date rlastOdate,
			String remark, Set pcCdManifoldTs) {
		this.branchingName = branchingName;
		this.branchingCode = branchingCode;
		this.combinationStation = combinationStation;
		this.rlastOper = rlastOper;
		this.rlastOdate = rlastOdate;
		this.remark = remark;
		this.pcCdManifoldTs = pcCdManifoldTs;
	}

	// Property accessors

	public String getBranchingid() {
		return this.branchingid;
	}

	public void setBranchingid(String branchingid) {
		this.branchingid = branchingid;
	}

	public String getBranchingName() {
		return this.branchingName;
	}

	public void setBranchingName(String branchingName) {
		this.branchingName = branchingName;
	}

	public String getBranchingCode() {
		return this.branchingCode;
	}

	public void setBranchingCode(String branchingCode) {
		this.branchingCode = branchingCode;
	}

	public String getCombinationStation() {
		return this.combinationStation;
	}

	public void setCombinationStation(String combinationStation) {
		this.combinationStation = combinationStation;
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

	public Set getPcCdManifoldTs() {
		return this.pcCdManifoldTs;
	}

	public void setPcCdManifoldTs(Set pcCdManifoldTs) {
		this.pcCdManifoldTs = pcCdManifoldTs;
	}

}