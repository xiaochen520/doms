package com.echo.dto;

/**
 * PcCdPortalMenuT entity. @author MyEclipse Persistence Tools
 */

public class PcCdPortalMenuT implements java.io.Serializable {

	private static final long serialVersionUID = 6668744526560710298L;
	private String portalmenuid;
	private String portalmenuName;
	private String fatherPortalmenuid;
	private String portalmenuUrl;
	private Byte enabled;
	private String menuOrder;

	// Constructors

	/** default constructor */
	public PcCdPortalMenuT() {
	}


	// Property accessors

	public String getPortalmenuid() {
		return this.portalmenuid;
	}

	public void setPortalmenuid(String portalmenuid) {
		this.portalmenuid = portalmenuid;
	}

	public String getPortalmenuName() {
		return this.portalmenuName;
	}

	public void setPortalmenuName(String portalmenuName) {
		this.portalmenuName = portalmenuName;
	}

	public String getFatherPortalmenuid() {
		return this.fatherPortalmenuid;
	}

	public void setFatherPortalmenuid(String fatherPortalmenuid) {
		this.fatherPortalmenuid = fatherPortalmenuid;
	}

	public String getPortalmenuUrl() {
		return this.portalmenuUrl;
	}

	public void setPortalmenuUrl(String portalmenuUrl) {
		this.portalmenuUrl = portalmenuUrl;
	}

	public Byte getEnabled() {
		return this.enabled;
	}

	public void setEnabled(Byte enabled) {
		this.enabled = enabled;
	}


	public String getMenuOrder() {
		return menuOrder;
	}


	public void setMenuOrder(String menuOrder) {
		this.menuOrder = menuOrder;
	}

}