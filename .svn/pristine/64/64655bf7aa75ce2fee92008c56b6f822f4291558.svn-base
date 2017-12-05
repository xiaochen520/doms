package com.echo.dto;

import java.util.HashSet;
import java.util.Set;

/**
 * PcCdZoneT entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("unused")
public class PcCdZoneT implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String zoneCode;
	private String zoneName;
	//private PcCdAreaInfoT pcCdAreaInfoT;
	private Set pcCdAreaInfoTs = new HashSet(0);
	

	// Constructors

	/** default constructor */
	public PcCdZoneT() {
	}

	/** minimal constructor */
	public PcCdZoneT(String zoneName) {
		this.zoneName = zoneName;
	}

	/** full constructor */
	public PcCdZoneT(String zoneName,Set pcCdAreaInfoTs) {
		this.zoneName = zoneName;
		this.pcCdAreaInfoTs = pcCdAreaInfoTs;
	}

	// Property accessors

	public String getZoneCode() {
		return this.zoneCode;
	}

	

	public Set getPcCdAreaInfoTs() {
		return pcCdAreaInfoTs;
	}

	public void setPcCdAreaInfoTs(Set pcCdAreaInfoTs) {
		this.pcCdAreaInfoTs = pcCdAreaInfoTs;
	}

	public void setZoneCode(String zoneCode) {
		this.zoneCode = zoneCode;
	}

	public String getZoneName() {
		return this.zoneName;
	}

	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}


}