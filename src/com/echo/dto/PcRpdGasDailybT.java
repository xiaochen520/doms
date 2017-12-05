package com.echo.dto;

import java.util.Date;

/**
 * PcRpdGasDailybT entity. @author MyEclipse Persistence Tools
 */

public class PcRpdGasDailybT implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String gaswellrpdid;
	private String orgId;
	private String wellName;
	private Date reportDate;
	private java.math.BigDecimal zqzwater;
	private java.math.BigDecimal pqjby;
	private java.math.BigDecimal jkyy;
	private java.math.BigDecimal ty;
	private java.math.BigDecimal pqj;
	private java.math.BigDecimal jk;
	private java.math.BigDecimal pzl;
	private java.math.BigDecimal rzl;
	private byte liquidFlag;
	private String remark;
	private String classCheckOper;
	private Date classCheckDate;
	private String geologyCheckOper;
	private Date geologyCheckDate;
	private String rlastOper;
	private Date rlastOdate;

	// Constructors

	/** default constructor */
	public PcRpdGasDailybT() {
	}

	/** minimal constructor */
	public PcRpdGasDailybT(String orgId) {
		this.orgId = orgId;
	}

	/** full constructor */
	public PcRpdGasDailybT(String orgId, String wellName, Date reportDate,
			java.math.BigDecimal zqzwater, java.math.BigDecimal pqjby, java.math.BigDecimal jkyy, java.math.BigDecimal ty, java.math.BigDecimal pqj,
			java.math.BigDecimal jk, java.math.BigDecimal pzl, java.math.BigDecimal rzl,byte liquidFlag, String remark,
			String classCheckOper, Date classCheckDate,
			String geologyCheckOper, Date geologyCheckDate, String rlastOper,
			Date rlastOdate) {
		this.orgId = orgId;
		this.wellName = wellName;
		this.reportDate = reportDate;
		this.zqzwater = zqzwater;
		this.pqjby = pqjby;
		this.jkyy = jkyy;
		this.ty = ty;
		this.pqj = pqj;
		this.jk = jk;
		this.pzl = pzl;
		this.rzl = rzl;
		this.liquidFlag = liquidFlag;
		this.remark = remark;
		this.classCheckOper = classCheckOper;
		this.classCheckDate = classCheckDate;
		this.geologyCheckOper = geologyCheckOper;
		this.geologyCheckDate = geologyCheckDate;
		this.rlastOper = rlastOper;
		this.rlastOdate = rlastOdate;
	}

	// Property accessors

	public String getGaswellrpdid() {
		return this.gaswellrpdid;
	}

	public void setGaswellrpdid(String gaswellrpdid) {
		this.gaswellrpdid = gaswellrpdid;
	}

	public String getOrgId() {
		return this.orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getWellName() {
		return this.wellName;
	}

	public void setWellName(String wellName) {
		this.wellName = wellName;
	}

	public Date getReportDate() {
		return this.reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	public java.math.BigDecimal getZqzwater() {
		return this.zqzwater;
	}

	public void setZqzwater(java.math.BigDecimal zqzwater) {
		this.zqzwater = zqzwater;
	}

	public java.math.BigDecimal getPqjby() {
		return this.pqjby;
	}

	public void setPqjby(java.math.BigDecimal pqjby) {
		this.pqjby = pqjby;
	}

	public java.math.BigDecimal getJkyy() {
		return this.jkyy;
	}

	public void setJkyy(java.math.BigDecimal jkyy) {
		this.jkyy = jkyy;
	}

	public java.math.BigDecimal getTy() {
		return this.ty;
	}

	public void setTy(java.math.BigDecimal ty) {
		this.ty = ty;
	}

	public java.math.BigDecimal getPqj() {
		return this.pqj;
	}

	public void setPqj(java.math.BigDecimal pqj) {
		this.pqj = pqj;
	}

	public java.math.BigDecimal getJk() {
		return this.jk;
	}

	public void setJk(java.math.BigDecimal jk) {
		this.jk = jk;
	}

	public java.math.BigDecimal getPzl() {
		return this.pzl;
	}

	public void setPzl(java.math.BigDecimal pzl) {
		this.pzl = pzl;
	}

	public java.math.BigDecimal getRzl() {
		return this.rzl;
	}

	public void setRzl(java.math.BigDecimal rzl) {
		this.rzl = rzl;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public byte getLiquidFlag() {
		return liquidFlag;
	}

	public void setLiquidFlag(byte liquidFlag) {
		this.liquidFlag = liquidFlag;
	}

}