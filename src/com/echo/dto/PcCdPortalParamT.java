package com.echo.dto;

/**
 * PcCdPortalParamT entity. @author MyEclipse Persistence Tools
 */

public class PcCdPortalParamT implements java.io.Serializable {

	// Fields

	private String portalParamId;
	private String portalmenuid;
	private String paramname;
	private String paramvalue;
	private String remark;

	// Constructors

	/** default constructor */
	public PcCdPortalParamT() {
	}

	/** minimal constructor */
	public PcCdPortalParamT(String portalmenuid, String paramname,
			String paramvalue) {
		this.portalmenuid = portalmenuid;
		this.paramname = paramname;
		this.paramvalue = paramvalue;
	}

	/** full constructor */
	public PcCdPortalParamT(String portalmenuid, String paramname,
			String paramvalue, String remark) {
		this.portalmenuid = portalmenuid;
		this.paramname = paramname;
		this.paramvalue = paramvalue;
		this.remark = remark;
	}

	// Property accessors

	public String getPortalParamId() {
		return this.portalParamId;
	}

	public void setPortalParamId(String portalParamId) {
		this.portalParamId = portalParamId;
	}

	public String getPortalmenuid() {
		return this.portalmenuid;
	}

	public void setPortalmenuid(String portalmenuid) {
		this.portalmenuid = portalmenuid;
	}

	public String getParamname() {
		return this.paramname;
	}

	public void setParamname(String paramname) {
		this.paramname = paramname;
	}

	public String getParamvalue() {
		return this.paramvalue;
	}

	public void setParamvalue(String paramvalue) {
		this.paramvalue = paramvalue;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}