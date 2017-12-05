package com.echo.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * PcCdWaterfloodingWellT entity. @author MyEclipse Persistence Tools
 */

public class PcCdWaterfloodingWellT implements java.io.Serializable {

	// Fields

	private String waterfloodingWellid;
	private PcCdOrgT pcCdOrgT;
	private String waterfloodingName;
	private Byte channelNumber;
	private String rlastOper;
	private Date rlastOdate;
	private String remark;
	private String qkid;
	private String dyear;
	private Set pcRdWaterfloodingWellTs = new HashSet(0);
	private Set pcRpdWaterfloodingWelldTs = new HashSet(0);
	private Set pcRpdWaterfloodingWellcTs = new HashSet(0);

	// Constructors

	/** default constructor */
	public PcCdWaterfloodingWellT() {
	}

	// Property accessors

	
	public String getWaterfloodingWellid() {
		return this.waterfloodingWellid;
	}

	public String getDyear() {
		return dyear;
	}

	public void setDyear(String dyear) {
		this.dyear = dyear;
	}

	public void setWaterfloodingWellid(String waterfloodingWellid) {
		this.waterfloodingWellid = waterfloodingWellid;
	}

	public PcCdOrgT getPcCdOrgT() {
		return this.pcCdOrgT;
	}

	public void setPcCdOrgT(PcCdOrgT pcCdOrgT) {
		this.pcCdOrgT = pcCdOrgT;
	}

	public String getWaterfloodingName() {
		return this.waterfloodingName;
	}

	public void setWaterfloodingName(String waterfloodingName) {
		this.waterfloodingName = waterfloodingName;
	}



	public Byte getChannelNumber() {
		return this.channelNumber;
	}

	public void setChannelNumber(Byte channelNumber) {
		this.channelNumber = channelNumber;
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

	public String getQkid() {
		return this.qkid;
	}

	public void setQkid(String qkid) {
		this.qkid = qkid;
	}

	public Set getPcRdWaterfloodingWellTs() {
		return this.pcRdWaterfloodingWellTs;
	}

	public void setPcRdWaterfloodingWellTs(Set pcRdWaterfloodingWellTs) {
		this.pcRdWaterfloodingWellTs = pcRdWaterfloodingWellTs;
	}

	public Set getPcRpdWaterfloodingWelldTs() {
		return this.pcRpdWaterfloodingWelldTs;
	}

	public void setPcRpdWaterfloodingWelldTs(Set pcRpdWaterfloodingWelldTs) {
		this.pcRpdWaterfloodingWelldTs = pcRpdWaterfloodingWelldTs;
	}

	public Set getPcRpdWaterfloodingWellcTs() {
		return this.pcRpdWaterfloodingWellcTs;
	}

	public void setPcRpdWaterfloodingWellcTs(Set pcRpdWaterfloodingWellcTs) {
		this.pcRpdWaterfloodingWellcTs = pcRpdWaterfloodingWellcTs;
	}

}