package com.echo.dto;

/**
 * PcCdWellCellT entity. @author MyEclipse Persistence Tools
 */

public class PcCdWellCellT implements java.io.Serializable {

	
	private static final long serialVersionUID = 4468173127603254177L;

	private String wellCellId;
	private String orgId;
	private PcCdBlCellT pcCdBlCellT;
	private java.math.BigDecimal splitCoefficient;
	private Integer PFlowmeter;
	private Integer IFlowmeter;

	// Constructors

	/** default constructor */
	public PcCdWellCellT() {
	}


	// Property accessors


	public String getOrgId() {
		return orgId;
	}


	public String getWellCellId() {
		return wellCellId;
	}


	public void setWellCellId(String wellCellId) {
		this.wellCellId = wellCellId;
	}




	public PcCdBlCellT getPcCdBlCellT() {
		return pcCdBlCellT;
	}


	public void setPcCdBlCellT(PcCdBlCellT pcCdBlCellT) {
		this.pcCdBlCellT = pcCdBlCellT;
	}


	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}


	public java.math.BigDecimal getSplitCoefficient() {
		return splitCoefficient;
	}


	public void setSplitCoefficient(java.math.BigDecimal splitCoefficient) {
		this.splitCoefficient = splitCoefficient;
	}


	public Integer getPFlowmeter() {
		return PFlowmeter;
	}


	public void setPFlowmeter(Integer pFlowmeter) {
		PFlowmeter = pFlowmeter;
	}


	public Integer getIFlowmeter() {
		return IFlowmeter;
	}


	public void setIFlowmeter(Integer iFlowmeter) {
		IFlowmeter = iFlowmeter;
	}

	

}