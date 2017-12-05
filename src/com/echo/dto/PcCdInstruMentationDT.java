package com.echo.dto;

import java.util.Date;

/**
 * PcCdInstruMentationDT entity. @author MyEclipse Persistence Tools
 */

public class PcCdInstruMentationDT implements java.io.Serializable {

	// Fields

	private String instrumentId;
	private String instrumentIdn;
	private String instrusId;
	private String orgId;
	private String objectCode;
	private String instruClc;
	private String sjrtuClc;
	private java.math.BigDecimal instruStva;
	private String remark;
	private String rlastOper;
	private Date rlastOdate;

	// Constructors

	/** default constructor */
	public PcCdInstruMentationDT() {
	}

	

	// Property accessors

	public String getInstrumentId() {
		return this.instrumentId;
	}

	public void setInstrumentId(String instrumentId) {
		this.instrumentId = instrumentId;
	}

	public String getInstrumentIdn() {
		return this.instrumentIdn;
	}

	public void setInstrumentIdn(String instrumentIdn) {
		this.instrumentIdn = instrumentIdn;
	}

	public String getInstrusId() {
		return this.instrusId;
	}

	public void setInstrusId(String instrusId) {
		this.instrusId = instrusId;
	}

	public String getOrgId() {
		return this.orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getObjectCode() {
		return this.objectCode;
	}

	public void setObjectCode(String objectCode) {
		this.objectCode = objectCode;
	}

	public String getInstruClc() {
		return this.instruClc;
	}

	public void setInstruClc(String instruClc) {
		this.instruClc = instruClc;
	}

	public String getSjrtuClc() {
		return this.sjrtuClc;
	}

	public void setSjrtuClc(String sjrtuClc) {
		this.sjrtuClc = sjrtuClc;
	}

	public java.math.BigDecimal getInstruStva() {
		return instruStva;
	}

	public void setInstruStva(java.math.BigDecimal instruStva) {
		this.instruStva = instruStva;
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