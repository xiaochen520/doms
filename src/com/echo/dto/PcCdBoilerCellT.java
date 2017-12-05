package com.echo.dto;

/**
 * PcCdBoilerCellT entity. @author MyEclipse Persistence Tools
 */

public class PcCdBoilerCellT implements java.io.Serializable {

	// Fields

	private String boilerCellId;
	private String boilerId;
	private PcCdBlCellT pcCdBlCellT;

	/** default constructor */
	public PcCdBoilerCellT() {
	}



	public PcCdBlCellT getPcCdBlCellT() {
		return pcCdBlCellT;
	}



	public void setPcCdBlCellT(PcCdBlCellT pcCdBlCellT) {
		this.pcCdBlCellT = pcCdBlCellT;
	}



	public String getBoilerCellId() {
		return boilerCellId;
	}

	public void setBoilerCellId(String boilerCellId) {
		this.boilerCellId = boilerCellId;
	}

	public String getBoilerId() {
		return boilerId;
	}

	public void setBoilerId(String boilerId) {
		this.boilerId = boilerId;
	}



}