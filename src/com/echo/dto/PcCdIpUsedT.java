package com.echo.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * PcCdIpUsedT entity. @author MyEclipse Persistence Tools
 */

public class PcCdIpUsedT implements java.io.Serializable {

	// Fields

	private String ip;
	private String category;
	private String area;
	private String unit;
	private String instrumentationName;
	private BigDecimal ipNo;
	private String device;
	private String interface2th;
	private String segmentId;
	private BigDecimal isUsed;
	private Date startDate;
	private Date stopDate;
	private String remark;
	private String vpnIp;
	private String rlastOper;
	private Date rlastOdate;
	private  String orgId;
	private String objectCode;
	private String instruClc;

	// Constructors

	/** default constructor */
	public PcCdIpUsedT() {
	}

	// Property accessors

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getInstrumentationName() {
		return this.instrumentationName;
	}

	public void setInstrumentationName(String instrumentationName) {
		this.instrumentationName = instrumentationName;
	}

	public BigDecimal getIpNo() {
		return this.ipNo;
	}

	public void setIpNo(BigDecimal ipNo) {
		this.ipNo = ipNo;
	}

	public String getDevice() {
		return this.device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public String getInterface2th() {
		return this.interface2th;
	}

	public void setInterface2th(String interface2th) {
		this.interface2th = interface2th;
	}

	public String getSegmentId() {
		return this.segmentId;
	}

	public void setSegmentId(String segmentId) {
		this.segmentId = segmentId;
	}

	public BigDecimal getIsUsed() {
		return this.isUsed;
	}

	public void setIsUsed(BigDecimal isUsed) {
		this.isUsed = isUsed;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getStopDate() {
		return this.stopDate;
	}

	public void setStopDate(Date stopDate) {
		this.stopDate = stopDate;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getVpnIp() {
		return this.vpnIp;
	}

	public void setVpnIp(String vpnIp) {
		this.vpnIp = vpnIp;
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

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getObjectCode() {
		return objectCode;
	}

	public void setObjectCode(String objectCode) {
		this.objectCode = objectCode;
	}

	public String getInstruClc() {
		return instruClc;
	}

	public void setInstruClc(String instruClc) {
		this.instruClc = instruClc;
	}

}