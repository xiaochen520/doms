package com.echo.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * AbstractTbUser entity provides the base persistence definition of the TbUser
 * entity. @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {

	private static final long serialVersionUID = -4907635034701315740L;
	private String userid;
	private String depid;
	private String depname;
	private String depType;
	private String operCode;
	private String operName;
	private String operPass;
	private String operSdbsadbse;
	private Date audbshorDate;
	private Date sdbsadbseDate;
	private Date LLoginDate;
	private Date LLogoudbsDate;
	private String rlastOper;
	private Date rlastOdate;
	private String remark;
	private List<PcCdRoleT> pcCdUserRoleTs = new ArrayList<PcCdRoleT>(0);
	private List<PcCdRightT> pcCdRightTs = new ArrayList<PcCdRightT>(0);
	
	private List<Object[]> authorityDatas = new ArrayList<Object[]>();
	
	private String cyz;  //选择器采油站ID
	private String team; //选择器班组ID
	
	public String getDepid() {
		return depid;
	}
	public void setDepid(String depid) {
		this.depid = depid;
	}
	public String getDepname() {
		return depname;
	}
	public void setDepname(String depname) {
		this.depname = depname;
	}
	public String getDepType() {
		return depType;
	}
	public void setDepType(String depType) {
		this.depType = depType;
	}

	public List<Object[]> getAuthorityDatas() {
		return authorityDatas;
	}
	public void setAuthorityDatas(List<Object[]> authorityDatas) {
		this.authorityDatas = authorityDatas;
	}
	public List<PcCdRoleT> getPcCdUserRoleTs() {
		return pcCdUserRoleTs;
	}
	public void setPcCdUserRoleTs(List<PcCdRoleT> pcCdUserRoleTs) {
		this.pcCdUserRoleTs = pcCdUserRoleTs;
	}
	public List<PcCdRightT> getPcCdRightTs() {
		return pcCdRightTs;
	}
	public void setPcCdRightTs(List<PcCdRightT> pcCdRightTs) {
		this.pcCdRightTs = pcCdRightTs;
	}
	private List<PcCdMenuT> menuList = new ArrayList<PcCdMenuT>();
	private String ip;
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	public String getOperCode() {
		return operCode;
	}
	public void setOperCode(String operCode) {
		this.operCode = operCode;
	}
	public String getOperName() {
		return operName;
	}
	public void setOperName(String operName) {
		this.operName = operName;
	}
	public String getOperPass() {
		return operPass;
	}
	public void setOperPass(String operPass) {
		this.operPass = operPass;
	}
	public String getOperSdbsadbse() {
		return operSdbsadbse;
	}
	public void setOperSdbsadbse(String operSdbsadbse) {
		this.operSdbsadbse = operSdbsadbse;
	}
	public Date getAudbshorDate() {
		return audbshorDate;
	}
	public void setAudbshorDate(Date audbshorDate) {
		this.audbshorDate = audbshorDate;
	}
	public Date getSdbsadbseDate() {
		return sdbsadbseDate;
	}
	public void setSdbsadbseDate(Date sdbsadbseDate) {
		this.sdbsadbseDate = sdbsadbseDate;
	}
	public Date getLLoginDate() {
		return LLoginDate;
	}
	public void setLLoginDate(Date lLoginDate) {
		LLoginDate = lLoginDate;
	}
	public Date getLLogoudbsDate() {
		return LLogoudbsDate;
	}
	public void setLLogoudbsDate(Date lLogoudbsDate) {
		LLogoudbsDate = lLogoudbsDate;
	}
	public String getRlastOper() {
		return rlastOper;
	}
	public void setRlastOper(String rlastOper) {
		this.rlastOper = rlastOper;
	}
	public Date getRlastOdate() {
		return rlastOdate;
	}
	public void setRlastOdate(Date rlastOdate) {
		this.rlastOdate = rlastOdate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}


	public List<PcCdMenuT> getMenuList() {
		return menuList;
	}
	public void setMenuList(List<PcCdMenuT> menuList) {
		this.menuList = menuList;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getCyz() {
		return cyz;
	}
	public void setCyz(String cyz) {
		this.cyz = cyz;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}



}