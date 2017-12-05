package com.echo.dto;

import java.util.Date;

/**
 * PcRpdReportMessageT entity. @author MyEclipse Persistence Tools
 */

public class PcRpdReportMessageT implements java.io.Serializable {
	private static final long serialVersionUID = 287633802145237183L;
	private String rpdReportMessageId;
	private Date rq;
	private String bbmc;
	private String zbr1;
	private String zbr2;
	private String zbr3;
	private String tbr;
	private String shr;
	private Date shsj;
	private String bz;
	// Constructors

	/** default constructor */
	public PcRpdReportMessageT() {
	}

	/** minimal constructor */
	public PcRpdReportMessageT(Date rq, String bbmc) {
		this.rq = rq;
		this.bbmc = bbmc;
	}

	/** full constructor */
	public PcRpdReportMessageT(Date rq, String bbmc, String zbr1, String zbr2,
			String zbr3, String tbr, String shr, Date shsj, String bz) {
		this.rq = rq;
		this.bbmc = bbmc;
		this.zbr1 = zbr1;
		this.zbr2 = zbr2;
		this.zbr3 = zbr3;
		this.tbr = tbr;
		this.shr = shr;
		this.shsj = shsj;
		this.bz = bz;
		
	}

	// Property accessors

	public String getRpdReportMessageId() {
		return this.rpdReportMessageId;
	}

	public void setRpdReportMessageId(String rpdReportMessageId) {
		this.rpdReportMessageId = rpdReportMessageId;
	}

	public Date getRq() {
		return this.rq;
	}

	public void setRq(Date rq) {
		this.rq = rq;
	}

	public String getBbmc() {
		return this.bbmc;
	}

	public void setBbmc(String bbmc) {
		this.bbmc = bbmc;
	}

	public String getZbr1() {
		return this.zbr1;
	}

	public void setZbr1(String zbr1) {
		this.zbr1 = zbr1;
	}

	public String getZbr2() {
		return this.zbr2;
	}

	public void setZbr2(String zbr2) {
		this.zbr2 = zbr2;
	}

	public String getZbr3() {
		return this.zbr3;
	}

	public void setZbr3(String zbr3) {
		this.zbr3 = zbr3;
	}

	public String getTbr() {
		return this.tbr;
	}

	public void setTbr(String tbr) {
		this.tbr = tbr;
	}

	public String getShr() {
		return this.shr;
	}

	public void setShr(String shr) {
		this.shr = shr;
	}

	public Date getShsj() {
		return this.shsj;
	}

	public void setShsj(Date shsj) {
		this.shsj = shsj;
	}

	public String getBz() {
		return this.bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}


}