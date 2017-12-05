package com.echo.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * PcRpdRuleWellProdT entity. @author MyEclipse Persistence Tools
 */

public class PcRpdRuleWellProdbT implements java.io.Serializable {

	// Fields

	private String rpdRuleWellProdId;
	private String orgId;
	private Date reportDate;
	private String wellName;
	private BigDecimal procTimeRation;
	private BigDecimal stroke;
	private BigDecimal jigFrequency;
	private BigDecimal flowNipple;
	private BigDecimal tubingPres;
	private BigDecimal casingPres;
	private BigDecimal backPres;
	private BigDecimal oilTemp;
	private BigDecimal liquidOutput;
	private BigDecimal gasOutput;
	private String sampling;
	private BigDecimal runtime;
	private BigDecimal pumpErrorTime;
	private String pumpErrorDesc;
	private String remark;
	private java.math.BigDecimal liquidFlag;
	private String classCheckOper;
	private Date classCheckDate;
	private String geologyCheckOper;
	private Date geologyCheckDate;
	private String rlastOper;
	private Date rlastOdate;

	// Constructors

	/** default constructor */
	public PcRpdRuleWellProdbT() {
	}

	/** minimal constructor */
	public PcRpdRuleWellProdbT(String orgId) {
		this.orgId = orgId;
	}

	/** full constructor */
	public PcRpdRuleWellProdbT(String orgId, Date reportDate, String wellName,
			BigDecimal procTimeRation, BigDecimal stroke, BigDecimal jigFrequency,
			BigDecimal flowNipple, BigDecimal tubingPres, BigDecimal casingPres,
			BigDecimal backPres, BigDecimal oilTemp, BigDecimal liquidOutput,
			BigDecimal gasOutput, String sampling, BigDecimal runtime,
			BigDecimal pumpErrorTime, String pumpErrorDesc, String remark,
			java.math.BigDecimal liquidFlag, String classCheckOper, Date classCheckDate,
			String geologyCheckOper, Date geologyCheckDate, String rlastOper,
			Date rlastOdate) {
		this.orgId = orgId;
		this.reportDate = reportDate;
		this.wellName = wellName;
		this.procTimeRation = procTimeRation;
		this.stroke = stroke;
		this.jigFrequency = jigFrequency;
		this.flowNipple = flowNipple;
		this.tubingPres = tubingPres;
		this.casingPres = casingPres;
		this.backPres = backPres;
		this.oilTemp = oilTemp;
		this.liquidOutput = liquidOutput;
		this.gasOutput = gasOutput;
		this.sampling = sampling;
		this.runtime = runtime;
		this.pumpErrorTime = pumpErrorTime;
		this.pumpErrorDesc = pumpErrorDesc;
		this.remark = remark;
		this.liquidFlag = liquidFlag;
		this.classCheckOper = classCheckOper;
		this.classCheckDate = classCheckDate;
		this.geologyCheckOper = geologyCheckOper;
		this.geologyCheckDate = geologyCheckDate;
		this.rlastOper = rlastOper;
		this.rlastOdate = rlastOdate;
	}

	// Property accessors

	public String getRpdRuleWellProdId() {
		return this.rpdRuleWellProdId;
	}

	public void setRpdRuleWellProdId(String rpdRuleWellProdId) {
		this.rpdRuleWellProdId = rpdRuleWellProdId;
	}

	public String getOrgId() {
		return this.orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public Date getReportDate() {
		return this.reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	public String getWellName() {
		return this.wellName;
	}

	public void setWellName(String wellName) {
		this.wellName = wellName;
	}

	public BigDecimal getProcTimeRation() {
		return this.procTimeRation;
	}

	public void setProcTimeRation(BigDecimal procTimeRation) {
		this.procTimeRation = procTimeRation;
	}

	public BigDecimal getStroke() {
		return this.stroke;
	}

	public void setStroke(BigDecimal stroke) {
		this.stroke = stroke;
	}

	public BigDecimal getJigFrequency() {
		return this.jigFrequency;
	}

	public void setJigFrequency(BigDecimal jigFrequency) {
		this.jigFrequency = jigFrequency;
	}

	public BigDecimal getFlowNipple() {
		return this.flowNipple;
	}

	public void setFlowNipple(BigDecimal flowNipple) {
		this.flowNipple = flowNipple;
	}

	public BigDecimal getTubingPres() {
		return this.tubingPres;
	}

	public void setTubingPres(BigDecimal tubingPres) {
		this.tubingPres = tubingPres;
	}

	public BigDecimal getCasingPres() {
		return this.casingPres;
	}

	public void setCasingPres(BigDecimal casingPres) {
		this.casingPres = casingPres;
	}

	public BigDecimal getBackPres() {
		return this.backPres;
	}

	public void setBackPres(BigDecimal backPres) {
		this.backPres = backPres;
	}

	public BigDecimal getOilTemp() {
		return this.oilTemp;
	}

	public void setOilTemp(BigDecimal oilTemp) {
		this.oilTemp = oilTemp;
	}

	public BigDecimal getLiquidOutput() {
		return this.liquidOutput;
	}

	public void setLiquidOutput(BigDecimal liquidOutput) {
		this.liquidOutput = liquidOutput;
	}

	public BigDecimal getGasOutput() {
		return this.gasOutput;
	}

	public void setGasOutput(BigDecimal gasOutput) {
		this.gasOutput = gasOutput;
	}

	public String getSampling() {
		return this.sampling;
	}

	public void setSampling(String sampling) {
		this.sampling = sampling;
	}

	public BigDecimal getRuntime() {
		return this.runtime;
	}

	public void setRuntime(BigDecimal runtime) {
		this.runtime = runtime;
	}

	public BigDecimal getPumpErrorTime() {
		return this.pumpErrorTime;
	}

	public void setPumpErrorTime(BigDecimal pumpErrorTime) {
		this.pumpErrorTime = pumpErrorTime;
	}

	public String getPumpErrorDesc() {
		return this.pumpErrorDesc;
	}

	public void setPumpErrorDesc(String pumpErrorDesc) {
		this.pumpErrorDesc = pumpErrorDesc;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public java.math.BigDecimal getLiquidFlag() {
		return liquidFlag;
	}

	public void setLiquidFlag(java.math.BigDecimal liquidFlag) {
		this.liquidFlag = liquidFlag;
	}

	public String getClassCheckOper() {
		return this.classCheckOper;
	}

	public void setClassCheckOper(String classCheckOper) {
		this.classCheckOper = classCheckOper;
	}

	public Date getClassCheckDate() {
		return this.classCheckDate;
	}

	public void setClassCheckDate(Date classCheckDate) {
		this.classCheckDate = classCheckDate;
	}

	public String getGeologyCheckOper() {
		return this.geologyCheckOper;
	}

	public void setGeologyCheckOper(String geologyCheckOper) {
		this.geologyCheckOper = geologyCheckOper;
	}

	public Date getGeologyCheckDate() {
		return this.geologyCheckDate;
	}

	public void setGeologyCheckDate(Date geologyCheckDate) {
		this.geologyCheckDate = geologyCheckDate;
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