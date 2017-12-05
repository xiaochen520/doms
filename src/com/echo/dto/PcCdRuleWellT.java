package com.echo.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * PcCdRuleWellT entity. @author MyEclipse Persistence Tools
 */

public class PcCdRuleWellT implements java.io.Serializable {

	// Fields常规油井

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ruleWellid;
	private PcCdOrgT pcCdOrgT;
	private String wellName;
	private String bewellName;
	private Byte valveCoding;
	private Byte channelNo;
	private String rlastOper;
	private Date rlastOdate;
	private String remark;
	private String qkid;
	private java.math.BigDecimal outputCoefficient;
	private Set pcRpdRuleWelldTs = new HashSet(0);
	private Set pcRdRuleWellTs = new HashSet(0);
	private Set pcRpdRuleWellcTs = new HashSet(0);
	
	private String dYear;
	private Byte increaseFreqFlag;
	private Short increaseInterval;
	private Date startIncreaseFreqTime;
	private Date endIncreaseFreqTime;

	// Constructors

	/** default constructor */
	public PcCdRuleWellT() {
	}

	/** minimal constructor */
	public PcCdRuleWellT(String wellName,  String rlastOper,
			Date rlastOdate) {
		this.wellName = wellName;
		this.rlastOper = rlastOper;
		this.rlastOdate = rlastOdate;
	}

	


	public String getRuleWellid() {
		return this.ruleWellid;
	}

	public void setRuleWellid(String ruleWellid) {
		this.ruleWellid = ruleWellid;
	}

	public PcCdOrgT getPcCdOrgT() {
		return this.pcCdOrgT;
	}

	public void setPcCdOrgT(PcCdOrgT pcCdOrgT) {
		this.pcCdOrgT = pcCdOrgT;
	}

	public String getWellName() {
		return this.wellName;
	}

	public void setWellName(String wellName) {
		this.wellName = wellName;
	}



	public String getBewellName() {
		return this.bewellName;
	}

	public void setBewellName(String bewellName) {
		this.bewellName = bewellName;
	}



	public Byte getValveCoding() {
		return this.valveCoding;
	}

	public void setValveCoding(Byte valveCoding) {
		this.valveCoding = valveCoding;
	}

	public Byte getChannelNo() {
		return this.channelNo;
	}

	public void setChannelNo(Byte channelNo) {
		this.channelNo = channelNo;
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

	public Set getPcRpdRuleWelldTs() {
		return this.pcRpdRuleWelldTs;
	}

	public void setPcRpdRuleWelldTs(Set pcRpdRuleWelldTs) {
		this.pcRpdRuleWelldTs = pcRpdRuleWelldTs;
	}

	public Set getPcRdRuleWellTs() {
		return this.pcRdRuleWellTs;
	}

	public void setPcRdRuleWellTs(Set pcRdRuleWellTs) {
		this.pcRdRuleWellTs = pcRdRuleWellTs;
	}

	public Set getPcRpdRuleWellcTs() {
		return this.pcRpdRuleWellcTs;
	}

	public void setPcRpdRuleWellcTs(Set pcRpdRuleWellcTs) {
		this.pcRpdRuleWellcTs = pcRpdRuleWellcTs;
	}

	public java.math.BigDecimal getOutputCoefficient() {
		return outputCoefficient;
	}

	public void setOutputCoefficient(java.math.BigDecimal outputCoefficient) {
		this.outputCoefficient = outputCoefficient;
	}

	public String getdYear() {
		return dYear;
	}

	public void setdYear(String dYear) {
		this.dYear = dYear;
	}


	public void setIncreaseFreqFlag(Byte increaseFreqFlag) {
		this.increaseFreqFlag = increaseFreqFlag;
	}

	public Byte getIncreaseFreqFlag() {
		return increaseFreqFlag;
	}

	public Short getIncreaseInterval() {
		return increaseInterval;
	}

	public void setIncreaseInterval(Short increaseInterval) {
		this.increaseInterval = increaseInterval;
	}

	public Date getStartIncreaseFreqTime() {
		return startIncreaseFreqTime;
	}

	public void setStartIncreaseFreqTime(Date startIncreaseFreqTime) {
		this.startIncreaseFreqTime = startIncreaseFreqTime;
	}

	public Date getEndIncreaseFreqTime() {
		return endIncreaseFreqTime;
	}

	public void setEndIncreaseFreqTime(Date endIncreaseFreqTime) {
		this.endIncreaseFreqTime = endIncreaseFreqTime;
	}

	
}