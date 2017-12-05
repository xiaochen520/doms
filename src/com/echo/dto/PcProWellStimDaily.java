package com.echo.dto;

import java.util.Date;

/**
 * PcProWellStimDaily entity. @author MyEclipse Persistence Tools
 */

public class PcProWellStimDaily implements java.io.Serializable {

	// Fields

	private String measureId;
	private String orgId;
	private String wellId;
	private Date startDatetime;
	private Date endDatetime;
	private String stimCode;
	private byte keyDownTag;
	private String remark;
	private String rlastOper;
	private Date rlastOdate;

	// Constructors

	/** default constructor */
	public PcProWellStimDaily() {
	}

	/** minimal constructor */
	public PcProWellStimDaily(String orgId, String wellId) {
		this.orgId = orgId;
		this.wellId = wellId;
	}

	/** full constructor */
	public PcProWellStimDaily(String orgId, String wellId, Date startDatetime,
			Date endDatetime, String stimCode, byte keyDownTag, String remark,
			String rlastOper, Date rlastOdate) {
		this.orgId = orgId;
		this.wellId = wellId;
		this.startDatetime = startDatetime;
		this.endDatetime = endDatetime;
		this.stimCode = stimCode;
		this.keyDownTag = keyDownTag;
		this.remark = remark;
		this.rlastOper = rlastOper;
		this.rlastOdate = rlastOdate;
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

	public String getWellId() {
		return this.wellId;
	}

	public void setWellId(String wellId) {
		this.wellId = wellId;
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

}