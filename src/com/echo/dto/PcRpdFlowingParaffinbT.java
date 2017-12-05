package com.echo.dto;

import java.util.Date;

/**
 * PcRpdFlowingParaffinbT entity. @author MyEclipse Persistence Tools
 */

public class PcRpdFlowingParaffinbT implements java.io.Serializable {

	// Fields

	private String flowingParaffinId;
	private String orgId;
	private String wellname;
	private Date eventDate;
	private String paraffinStimType;
	private java.math.BigDecimal waxCutDepth;
	private java.math.BigDecimal paraffinCutterDiameter;
	private java.math.BigDecimal paraffinCuttingTimes;
	private java.math.BigDecimal chemParaffinCuttingVol;
	private String remark;
	private String rlastOper;
	private Date rlastOdate;
	private String checkOper;
	private Date checkDate;
	private Date reportDate;

	// Constructors

	/** default constructor */
	public PcRpdFlowingParaffinbT() {
	}



	// Property accessors

	public String getFlowingParaffinId() {
		return this.flowingParaffinId;
	}

	public void setFlowingParaffinId(String flowingParaffinId) {
		this.flowingParaffinId = flowingParaffinId;
	}

	public String getOrgId() {
		return this.orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getWellname() {
		return this.wellname;
	}

	public void setWellname(String wellname) {
		this.wellname = wellname;
	}

	public Date getEventDate() {
		return this.eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public String getParaffinStimType() {
		return this.paraffinStimType;
	}

	public void setParaffinStimType(String paraffinStimType) {
		this.paraffinStimType = paraffinStimType;
	}


	public java.math.BigDecimal getWaxCutDepth() {
		return waxCutDepth;
	}



	public void setWaxCutDepth(java.math.BigDecimal waxCutDepth) {
		this.waxCutDepth = waxCutDepth;
	}



	public java.math.BigDecimal getParaffinCutterDiameter() {
		return paraffinCutterDiameter;
	}



	public void setParaffinCutterDiameter(
			java.math.BigDecimal paraffinCutterDiameter) {
		this.paraffinCutterDiameter = paraffinCutterDiameter;
	}



	public java.math.BigDecimal getParaffinCuttingTimes() {
		return paraffinCuttingTimes;
	}



	public void setParaffinCuttingTimes(java.math.BigDecimal paraffinCuttingTimes) {
		this.paraffinCuttingTimes = paraffinCuttingTimes;
	}



	public java.math.BigDecimal getChemParaffinCuttingVol() {
		return chemParaffinCuttingVol;
	}



	public void setChemParaffinCuttingVol(
			java.math.BigDecimal chemParaffinCuttingVol) {
		this.chemParaffinCuttingVol = chemParaffinCuttingVol;
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

	public String getCheckOper() {
		return this.checkOper;
	}

	public void setCheckOper(String checkOper) {
		this.checkOper = checkOper;
	}

	public Date getCheckDate() {
		return this.checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	public Date getReportDate() {
		return this.reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

}