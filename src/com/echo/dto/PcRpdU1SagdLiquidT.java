package com.echo.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * PcRpdU1SagdLiquidT entity. @author MyEclipse Persistence Tools
 */

public class PcRpdU1SagdLiquidT implements java.io.Serializable {

	// Fields

	private String rpdU1SagdLiquidId;
	private Date bbsj;
	private BigDecimal zyl;
	private BigDecimal sxcs;
	private BigDecimal rhxcs;
	private BigDecimal dtcyl;

	// Constructors

	/** default constructor */
	public PcRpdU1SagdLiquidT() {
	}

	/** minimal constructor */
	public PcRpdU1SagdLiquidT(Date bbsj) {
		this.bbsj = bbsj;
	}

	/** full constructor */
	public PcRpdU1SagdLiquidT(Date bbsj, BigDecimal zyl, BigDecimal sxcs, BigDecimal rhxcs,
			BigDecimal dtcyl) {
		this.bbsj = bbsj;
		this.zyl = zyl;
		this.sxcs = sxcs;
		this.rhxcs = rhxcs;
		this.dtcyl = dtcyl;
	}

	// Property accessors

	public String getRpdU1SagdLiquidId() {
		return this.rpdU1SagdLiquidId;
	}

	public void setRpdU1SagdLiquidId(String rpdU1SagdLiquidId) {
		this.rpdU1SagdLiquidId = rpdU1SagdLiquidId;
	}

	public Date getBbsj() {
		return this.bbsj;
	}

	public void setBbsj(Date bbsj) {
		this.bbsj = bbsj;
	}

	public BigDecimal getZyl() {
		return this.zyl;
	}

	public void setZyl(BigDecimal zyl) {
		this.zyl = zyl;
	}

	public BigDecimal getSxcs() {
		return this.sxcs;
	}

	public void setSxcs(BigDecimal sxcs) {
		this.sxcs = sxcs;
	}

	public BigDecimal getRhxcs() {
		return this.rhxcs;
	}

	public void setRhxcs(BigDecimal rhxcs) {
		this.rhxcs = rhxcs;
	}

	public BigDecimal getDtcyl() {
		return this.dtcyl;
	}

	public void setDtcyl(BigDecimal dtcyl) {
		this.dtcyl = dtcyl;
	}

}