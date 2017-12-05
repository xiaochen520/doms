package com.echo.dto;

import java.util.Date;

/**
 * PcProWellHotWashing entity. @author MyEclipse Persistence Tools
 */

public class PcProWellHotWashing implements java.io.Serializable {

	// Fields

	private String hotwashParaffinId;
	private String orgId;
	private String wellId;
	private Date eventDate;
	private String paraffinStimType;
	private String hotWashType;
	private java.math.BigDecimal paraffinMeltTemp;
	private java.math.BigDecimal paraffinMeltVol;
	private String remark;
	private String rlastOper;
	private Date rlastOdate;

	// Constructors

	/** default constructor */
	public PcProWellHotWashing() {
	}

	/** minimal constructor */
	public PcProWellHotWashing(String orgId, String wellId) {
		this.orgId = orgId;
		this.wellId = wellId;
	}

	/** full constructor */
	public PcProWellHotWashing(String orgId, String wellId, Date eventDate,
			String paraffinStimType, String hotWashType,
			java.math.BigDecimal paraffinMeltTemp, java.math.BigDecimal paraffinMeltVol, String remark,
			String rlastOper, Date rlastOdate) {
		this.orgId = orgId;
		this.wellId = wellId;
		this.eventDate = eventDate;
		this.paraffinStimType = paraffinStimType;
		this.hotWashType = hotWashType;
		this.paraffinMeltTemp = paraffinMeltTemp;
		this.paraffinMeltVol = paraffinMeltVol;
		this.remark = remark;
		this.rlastOper = rlastOper;
		this.rlastOdate = rlastOdate;
	}

	// Property accessors

	public String getHotwashParaffinId() {
		return this.hotwashParaffinId;
	}

	public void setHotwashParaffinId(String hotwashParaffinId) {
		this.hotwashParaffinId = hotwashParaffinId;
	}

	public String getOrgId() {
		return this.orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getWellId() {
		return this.wellId;
	}

	public void setWellId(String wellId) {
		this.wellId = wellId;
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

	public String getHotWashType() {
		return this.hotWashType;
	}

	public void setHotWashType(String hotWashType) {
		this.hotWashType = hotWashType;
	}

	public java.math.BigDecimal getParaffinMeltTemp() {
		return this.paraffinMeltTemp;
	}

	public void setParaffinMeltTemp(java.math.BigDecimal paraffinMeltTemp) {
		this.paraffinMeltTemp = paraffinMeltTemp;
	}

	public java.math.BigDecimal getParaffinMeltVol() {
		return this.paraffinMeltVol;
	}

	public void setParaffinMeltVol(java.math.BigDecimal paraffinMeltVol) {
		this.paraffinMeltVol = paraffinMeltVol;
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