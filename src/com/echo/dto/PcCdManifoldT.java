package com.echo.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * PcCdManifoldT entity. @author MyEclipse Persistence Tools
 */

public class PcCdManifoldT implements java.io.Serializable {

	// Fields

	private String manifoldid;
	private PcCdOrgT pcCdOrgT;
	private PcCdBranchingT pcCdBranchingT;
	private String ghdm;
	private String ghmc;
	private Byte dtfs;
	private String dtfmc1;
	private Byte dtftds1;
	private String dtfmc2;
	private Byte dtftds2;
	private String remark;
	private String rlastOper;
	private Date rlastOdate;
	private String dyear;
	private String teamId;
	private String Pid2;
	private java.math.BigDecimal instruStva;
	private Set pcRdManifoldTs = new HashSet(0);
	private Set pcRpdManifolddTs = new HashSet(0);
	private Set pcRpdManifoldcTs = new HashSet(0);

	// Constructors

	/** default constructor */
	public PcCdManifoldT() {
	}

	/** minimal constructor */
	public PcCdManifoldT(PcCdBranchingT pcCdBranchingT, String ghdm,
			String ghmc, Byte dtfs, String dtfmc1, Byte dtftds1,
			String rlastOper, Date rlastOdate,String dyear,String teamId,String Pid2) {
		this.pcCdBranchingT = pcCdBranchingT;
		this.ghdm = ghdm;
		this.ghmc = ghmc;
		this.dtfs = dtfs;
		this.dtfmc1 = dtfmc1;
		this.dtftds1 = dtftds1;
		this.rlastOper = rlastOper;
		this.rlastOdate = rlastOdate;
		this.dyear=dyear;
		this.teamId = teamId;
		this.Pid2 = Pid2;
	}

	/** full constructor */
	public PcCdManifoldT(PcCdOrgT pcCdOrgT, PcCdBranchingT pcCdBranchingT,
			String ghdm, String ghmc, Byte dtfs, String dtfmc1,
			Byte dtftds1, String dtfmc2, Byte dtftds2, String remark,
			String rlastOper, Date rlastOdate,  Set pcRdManifoldTs,
			Set pcRpdManifolddTs, Set pcRpdManifoldcTs,String dyear,String teamId,String Pid2) {
		this.pcCdOrgT = pcCdOrgT;
		this.pcCdBranchingT = pcCdBranchingT;
		this.ghdm = ghdm;
		this.ghmc = ghmc;
		this.dtfs = dtfs;
		this.dtfmc1 = dtfmc1;
		this.dtftds1 = dtftds1;
		this.dtfmc2 = dtfmc2;
		this.dtftds2 = dtftds2;
		this.remark = remark;
		this.rlastOper = rlastOper;
		this.rlastOdate = rlastOdate;
		this.pcRdManifoldTs = pcRdManifoldTs;
		this.pcRpdManifolddTs = pcRpdManifolddTs;
		this.pcRpdManifoldcTs = pcRpdManifoldcTs;
		this.dyear=dyear;
		this.teamId = teamId;
		this.Pid2 = Pid2;
	}

	// Property accessors

	public String getManifoldid() {
		return this.manifoldid;
	}

	public String getDyear() {
		return dyear;
	}

	public void setDyear(String dyear) {
		this.dyear = dyear;
	}

	public void setManifoldid(String manifoldid) {
		this.manifoldid = manifoldid;
	}

	public PcCdOrgT getPcCdOrgT() {
		return this.pcCdOrgT;
	}

	public void setPcCdOrgT(PcCdOrgT pcCdOrgT) {
		this.pcCdOrgT = pcCdOrgT;
	}

	public PcCdBranchingT getPcCdBranchingT() {
		return this.pcCdBranchingT;
	}

	public void setPcCdBranchingT(PcCdBranchingT pcCdBranchingT) {
		this.pcCdBranchingT = pcCdBranchingT;
	}

	public String getGhdm() {
		return this.ghdm;
	}

	public void setGhdm(String ghdm) {
		this.ghdm = ghdm;
	}

	public String getGhmc() {
		return this.ghmc;
	}

	public void setGhmc(String ghmc) {
		this.ghmc = ghmc;
	}

	public Byte getDtfs() {
		return this.dtfs;
	}

	public void setDtfs(Byte dtfs) {
		this.dtfs = dtfs;
	}

	public String getDtfmc1() {
		return this.dtfmc1;
	}

	public void setDtfmc1(String dtfmc1) {
		this.dtfmc1 = dtfmc1;
	}

	public Byte getDtftds1() {
		return this.dtftds1;
	}

	public void setDtftds1(Byte dtftds1) {
		this.dtftds1 = dtftds1;
	}

	public String getDtfmc2() {
		return this.dtfmc2;
	}

	public void setDtfmc2(String dtfmc2) {
		this.dtfmc2 = dtfmc2;
	}

	public Byte getDtftds2() {
		return this.dtftds2;
	}

	public void setDtftds2(Byte dtftds2) {
		this.dtftds2 = dtftds2;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
	public Set getPcRdManifoldTs() {
		return this.pcRdManifoldTs;
	}

	public void setPcRdManifoldTs(Set pcRdManifoldTs) {
		this.pcRdManifoldTs = pcRdManifoldTs;
	}

	public Set getPcRpdManifolddTs() {
		return this.pcRpdManifolddTs;
	}

	public void setPcRpdManifolddTs(Set pcRpdManifolddTs) {
		this.pcRpdManifolddTs = pcRpdManifolddTs;
	}

	public Set getPcRpdManifoldcTs() {
		return this.pcRpdManifoldcTs;
	}

	public void setPcRpdManifoldcTs(Set pcRpdManifoldcTs) {
		this.pcRpdManifoldcTs = pcRpdManifoldcTs;
	}

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	public String getPid2() {
		return Pid2;
	}

	public void setPid2(String Pid2) {
		this.Pid2 = Pid2;
	}

	public java.math.BigDecimal getInstruStva() {
		return instruStva;
	}

	public void setInstruStva(java.math.BigDecimal instruStva) {
		this.instruStva = instruStva;
	}

}