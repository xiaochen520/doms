package com.echo.dto;

import java.util.Date;

/**
 * PcRpdMeasureT entity. @author MyEclipse Persistence Tools
 */

public class PcRpdMeasureT implements java.io.Serializable {

	// Fields

	private String measureId;
	private String orgId;
	private String wellname;
	private Date startDatetime;
	private Date endDatetime;
	private String stimCode;
	private byte keyDownTag;
	private String remark;
	private String rlastOper;
	private Date rlastOdate;
	private String checkOper;
	private Date checkDate;
	private Date reportDate;

	// Constructors

	/** default constructor */
	public PcRpdMeasureT() {
	}

	/** minimal constructor */
	public PcRpdMeasureT(String orgId, String wellname) {
		this.orgId = orgId;
		this.wellname = wellname;
	}

	/** full constructor */
	public PcRpdMeasureT(String orgId, String wellname, Date startDatetime,
			Date endDatetime, String stimCode, byte keyDownTag, String remark,
			String rlastOper, Date rlastOdate, String checkOper,
			Date checkDate, Date reportDate) {
		this.orgId = orgId;
		this.wellname = wellname;
		this.startDatetime = startDatetime;
		this.endDatetime = endDatetime;
		this.stimCode = stimCode;
		this.keyDownTag = keyDownTag;
		this.remark = remark;
		this.rlastOper = rlastOper;
		this.rlastOdate = rlastOdate;
		this.checkOper = checkOper;
		this.checkDate = checkDate;
		this.reportDate = reportDate;
	}

	// Property accessors

	public String getMeasureId() {
		return this.measureId;
	}

	public void setMeasureId(String measureId) {
		this.measureId = measureId;
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

	public Date getStartDatetime() {
		return this.startDatetime;
	}

	public void setStartDatetime(Date startDatetime) {
		this.startDatetime = startDatetime;
	}

	public Date getEndDatetime() {
		return this.endDatetime;
	}

	public void setEndDatetime(Date endDatetime) {
		this.endDatetime = endDatetime;
	}

	public String getStimCode() {
		return this.stimCode;
	}

	public void setStimCode(String stimCode) {
		this.stimCode = stimCode;
	}

	public byte getKeyDownTag() {
		return this.keyDownTag;
	}

	public void setKeyDownTag(byte keyDownTag) {
		this.keyDownTag = keyDownTag;
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