package com.echo.dto;

import java.util.Date;

/**
 * PcCdTeamT entity. @author MyEclipse Persistence Tools
 */

public class PcCdTeamT implements java.io.Serializable {

	private static final long serialVersionUID = 6724325270972170922L;
	private String teamId;
	private PcCdOrgT pcCdOrgT;
	private String team;
	private String remark;
	private String rlastOper;
	private Date rlastOdate;

	// Constructors

	/** default constructor */
	public PcCdTeamT() {
	}


	public String getTeamId() {
		return this.teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	

	public PcCdOrgT getPcCdOrgT() {
		return pcCdOrgT;
	}


	public void setPcCdOrgT(PcCdOrgT pcCdOrgT) {
		this.pcCdOrgT = pcCdOrgT;
	}


	public String getTeam() {
		return this.team;
	}

	public void setTeam(String team) {
		this.team = team;
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

}