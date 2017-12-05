package com.echo.dto;

import java.util.Date;

/**
 * PcCdInstruMentationT entity. @author MyEclipse Persistence Tools
 */

public class PcCdInstruMentationT implements java.io.Serializable {

	// Fields

	private String instrumentId;
//	private PcCdObjectTypeT pcCdObjectTypeT;
//	private PcCdOrgT pcCdOrgT;
//	private PcCdInstruClassT pcCdInstruClassTByInstruClc;
//	private PcCdInstruStatusT pcCdInstruStatusT;
//	private PcCdInstruSupplycT pcCdInstruSupplycT;
//	private PcCdInstruClassT pcCdInstruClassTBySjrtuClc;
	
	private String objectCode;
	private String orgId;
	private String instruClc;
	private java.math.BigDecimal instruStva;
	private String instrusId;
	private String sjrtuClc;
	
	private String instrumentIdn;
	private String remark;
	private String rlastOper;
	private Date rlastOdate;
	private String stationNo;

	// Constructors

	/** default constructor */
	public PcCdInstruMentationT() {
	}

	// Property accessors

	public String getInstrumentId() {
		return this.instrumentId;
	}

	public void setInstrumentId(String instrumentId) {
		this.instrumentId = instrumentId;
	}


	public String getObjectCode() {
		return objectCode;
	}

	public void setObjectCode(String objectCode) {
		this.objectCode = objectCode;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getInstruClc() {
		return instruClc;
	}

	public void setInstruClc(String instruClc) {
		this.instruClc = instruClc;
	}

	public java.math.BigDecimal getInstruStva() {
		return instruStva;
	}

	public void setInstruStva(java.math.BigDecimal instruStva) {
		this.instruStva = instruStva;
	}

	public String getInstrusId() {
		return instrusId;
	}

	public void setInstrusId(String instrusId) {
		this.instrusId = instrusId;
	}

	public String getSjrtuClc() {
		return sjrtuClc;
	}

	public void setSjrtuClc(String sjrtuClc) {
		this.sjrtuClc = sjrtuClc;
	}

	public String getInstrumentIdn() {
		return this.instrumentIdn;
	}

	public void setInstrumentIdn(String instrumentIdn) {
		this.instrumentIdn = instrumentIdn;
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

	public String getStationNo() {
		return stationNo;
	}

	public void setStationNo(String stationNo) {
		this.stationNo = stationNo;
	}

}