package com.echo.dto;

import java.math.BigDecimal;

public class PcCdSubcoolCalParamsInfoT implements java.io.Serializable {
	
	private String calparamId;
	private String sagdId;
	private String calculateMethods;
	private Integer calculateMethodsid;
	private Integer calculateRate;
	private BigDecimal maxSubcool;
	private BigDecimal minSubcool;
	private Integer flowMethods;
	private BigDecimal subtract;
	public String getCalparamId() {
		return calparamId;
	}
	public void setCalparamId(String calparamId) {
		this.calparamId = calparamId;
	}
	
	public String getSagdId() {
		return sagdId;
	}
	public void setSagdId(String sagdId) {
		this.sagdId = sagdId;
	}
	public String getCalculateMethods() {
		return calculateMethods;
	}
	public void setCalculateMethods(String calculateMethods) {
		this.calculateMethods = calculateMethods;
	}
	public Integer getCalculateMethodsid() {
		return calculateMethodsid;
	}
	public void setCalculateMethodsid(Integer calculateMethodsid) {
		this.calculateMethodsid = calculateMethodsid;
	}
	public Integer getCalculateRate() {
		return calculateRate;
	}
	public void setCalculateRate(Integer calculateRate) {
		this.calculateRate = calculateRate;
	}
	public BigDecimal getMaxSubcool() {
		return maxSubcool;
	}
	public void setMaxSubcool(BigDecimal maxSubcool) {
		this.maxSubcool = maxSubcool;
	}
	public BigDecimal getMinSubcool() {
		return minSubcool;
	}
	public void setMinSubcool(BigDecimal minSubcool) {
		this.minSubcool = minSubcool;
	}
	public Integer getFlowMethods() {
		return flowMethods;
	}
	public void setFlowMethods(Integer flowMethods) {
		this.flowMethods = flowMethods;
	}
	public BigDecimal getSubtract() {
		return subtract;
	}
	public void setSubtract(BigDecimal subtract) {
		this.subtract = subtract;
	}
	@Override
	public String toString() {
		return "PcCdSubcoolCalParamsInfoT [calculateMethods="
				+ calculateMethods + ", calculateMethodsid="
				+ calculateMethodsid + ", calculateRate=" + calculateRate
				+ ", calparamId=" + calparamId + ", flowMethods=" + flowMethods
				+ ", maxSubcool=" + maxSubcool + ", minSubcool=" + minSubcool
				+ ", sagdId=" + sagdId + "]";
	}

}
