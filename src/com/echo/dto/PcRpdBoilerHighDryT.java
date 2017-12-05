package com.echo.dto;

import java.util.Date;

/**
 * PcRpdBoilerHighDryT entity. @author MyEclipse Persistence Tools
 */

public class PcRpdBoilerHighDryT implements java.io.Serializable {

	// Fields

	private String rpdBoilerHighDryId;
	private PcCdBoilerT pcCdBoilerT;
	private Date reportDate;
	private String oildrillingStationName;
	private String blockstationName;
	private String boilerName;
	private java.math.BigDecimal steamoutTemp;
	private java.math.BigDecimal steaminTemp;
	private java.math.BigDecimal ejectsmokeTemp;
	private java.math.BigDecimal burnerTemp;
	private java.math.BigDecimal csinPress;
	private java.math.BigDecimal csoutPress;
	private java.math.BigDecimal csinTemp;
	private java.math.BigDecimal csoutTemp;
	private java.math.BigDecimal slLevel;
	private java.math.BigDecimal superheatTemp;
	private java.math.BigDecimal superheatPiezoresistance;
	private java.math.BigDecimal superheatinTemp;
	private java.math.BigDecimal superheatoutTemp;
	private java.math.BigDecimal superheatinPress;
	private java.math.BigDecimal superheatoutPress;
	private java.math.BigDecimal superheatinFlow;
	private java.math.BigDecimal hearthPress;
	private java.math.BigDecimal gas1Press;
	private java.math.BigDecimal gas2Press;
	private java.math.BigDecimal gas3Press;
	private java.math.BigDecimal rsTemp;
	private java.math.BigDecimal rsDryness;
	private java.math.BigDecimal rsPiezoresistance;
	private java.math.BigDecimal rsinPress;
	private java.math.BigDecimal rsPress;
	private java.math.BigDecimal rsinTemp;
	private java.math.BigDecimal rsoutTemp;
	private java.math.BigDecimal pumpaFlow;
	private java.math.BigDecimal pumpbFlow;
	private java.math.BigDecimal pumpcFlow;
	private java.math.BigDecimal pumpaPress;
	private java.math.BigDecimal pumpbPress;
	private java.math.BigDecimal pumpcPress;
	private java.math.BigDecimal fanaElectricity;
	private java.math.BigDecimal fanbElectricity;
	private java.math.BigDecimal fancElectricity;
	private java.math.BigDecimal pumpfcFrequency;
	private java.math.BigDecimal pumpinPress;
	private java.math.BigDecimal pumpoutPress;
	private java.math.BigDecimal pumpinTemp;
	private java.math.BigDecimal pumpoutTemp;
	private java.math.BigDecimal watersupplyFlow;
	private java.math.BigDecimal watersupplyTotal;
	private java.math.BigDecimal gasFlow;
	private java.math.BigDecimal gasTotal;
	private java.math.BigDecimal steaminjectionTotal;
	private java.math.BigDecimal dailyCumulativeWater;
	private java.math.BigDecimal dailyCumulativeGas;
	private java.math.BigDecimal dailyCumulativeSteam;
	private java.math.BigDecimal gasIntoPress;
	private java.math.BigDecimal fireMeasure;
	private java.math.BigDecimal mixerPressReduction;
	private java.math.BigDecimal convectionrPressReduction;
	private java.math.BigDecimal lubeTemp;
	private java.math.BigDecimal systemVoltage;
	private java.math.BigDecimal pumpMotorTemp;
	private java.math.BigDecimal pumpMotorCurrent;
	private java.math.BigDecimal rsPressReduction;
	private java.math.BigDecimal superheatPressReduction;
	private java.math.BigDecimal fanMotorCurrent;
	private java.math.BigDecimal steamDryness;
	private java.math.BigDecimal reheatPressEntrance;
	private java.math.BigDecimal reheatTempEntrance;
	private java.math.BigDecimal reheatTempExport;
	private String blockName;
	private String steamWorkGroup;
	private String steamInjeUnit;
	private java.math.BigDecimal fanAirIntakeTemp;
	private java.math.BigDecimal h2sDensity;
	private java.math.BigDecimal fuelGasDensity;
	private java.math.BigDecimal systemPressReduction;
	private java.math.BigDecimal runtime;
	private java.math.BigDecimal rerheatPressReduction;
	private java.math.BigDecimal sewageBufferTank;

	private String rlastOper;
	private Date rlastOdate;
	private String checkOper;
	private Date checkDate;
	private String remark;
	// Constructors

	/** default constructor */
	public PcRpdBoilerHighDryT() {
	}

	/** minimal constructor */
	public PcRpdBoilerHighDryT(Date reportDate, String oildrillingStationName,
			String blockstationName, String boilerName) {
		this.reportDate = reportDate;
		this.oildrillingStationName = oildrillingStationName;
		this.blockstationName = blockstationName;
		this.boilerName = boilerName;
	}

	/** full constructor */
	public PcRpdBoilerHighDryT(PcCdBoilerT pcCdBoilerT, Date reportDate,
			String oildrillingStationName, String blockstationName,
			String boilerName, java.math.BigDecimal steamoutTemp, java.math.BigDecimal steaminTemp,
			java.math.BigDecimal ejectsmokeTemp, java.math.BigDecimal burnerTemp, java.math.BigDecimal csinPress,
			java.math.BigDecimal csoutPress, java.math.BigDecimal csinTemp, java.math.BigDecimal csoutTemp,
			java.math.BigDecimal slLevel, java.math.BigDecimal superheatTemp,
			java.math.BigDecimal superheatPiezoresistance, java.math.BigDecimal superheatinTemp,
			java.math.BigDecimal superheatoutTemp, java.math.BigDecimal superheatinPress,
			java.math.BigDecimal superheatoutPress, java.math.BigDecimal superheatinFlow,
			java.math.BigDecimal hearthPress, java.math.BigDecimal gas1Press, java.math.BigDecimal gas2Press,
			java.math.BigDecimal gas3Press, java.math.BigDecimal rsTemp, java.math.BigDecimal rsDryness,
			java.math.BigDecimal rsPiezoresistance, java.math.BigDecimal rsinPress, java.math.BigDecimal rsPress,
			java.math.BigDecimal rsinTemp, java.math.BigDecimal rsoutTemp, java.math.BigDecimal pumpaFlow,
			java.math.BigDecimal pumpbFlow, java.math.BigDecimal pumpcFlow, java.math.BigDecimal pumpaPress,
			java.math.BigDecimal pumpbPress, java.math.BigDecimal pumpcPress, java.math.BigDecimal fanaElectricity,
			java.math.BigDecimal fanbElectricity, java.math.BigDecimal fancElectricity,
			java.math.BigDecimal pumpfcFrequency, java.math.BigDecimal pumpinPress, java.math.BigDecimal pumpoutPress,
			java.math.BigDecimal pumpinTemp, java.math.BigDecimal pumpoutTemp, java.math.BigDecimal watersupplyFlow,
			java.math.BigDecimal watersupplyTotal, java.math.BigDecimal gasFlow, java.math.BigDecimal gasTotal,
			java.math.BigDecimal steaminjectionTotal, java.math.BigDecimal dailyCumulativeWater,
			java.math.BigDecimal dailyCumulativeGas, java.math.BigDecimal dailyCumulativeSteam,
			java.math.BigDecimal gasIntoPress, java.math.BigDecimal fireMeasure,
			java.math.BigDecimal mixerPressReduction, java.math.BigDecimal convectionrPressReduction,
			java.math.BigDecimal lubeTemp, java.math.BigDecimal systemVoltage, java.math.BigDecimal pumpMotorTemp,
			java.math.BigDecimal pumpMotorCurrent, java.math.BigDecimal rsPressReduction,
			java.math.BigDecimal superheatPressReduction, java.math.BigDecimal fanMotorCurrent,
			java.math.BigDecimal steamDryness, java.math.BigDecimal reheatPressEntrance,
			java.math.BigDecimal reheatTempEntrance, java.math.BigDecimal reheatTempExport,
			String blockName, String steamWorkGroup, String steamInjeUnit,
			java.math.BigDecimal fanAirIntakeTemp, java.math.BigDecimal h2sDensity, java.math.BigDecimal fuelGasDensity,
			java.math.BigDecimal systemPressReduction, java.math.BigDecimal runtime,
			java.math.BigDecimal rerheatPressReduction,
			java.math.BigDecimal sewageBufferTank,
			String rlastOper, Date rlastOdate,String checkOper,Date checkDate, String remark) {
		this.pcCdBoilerT = pcCdBoilerT;
		this.reportDate = reportDate;
		this.oildrillingStationName = oildrillingStationName;
		this.blockstationName = blockstationName;
		this.boilerName = boilerName;
		this.steamoutTemp = steamoutTemp;
		this.steaminTemp = steaminTemp;
		this.ejectsmokeTemp = ejectsmokeTemp;
		this.burnerTemp = burnerTemp;
		this.csinPress = csinPress;
		this.csoutPress = csoutPress;
		this.csinTemp = csinTemp;
		this.csoutTemp = csoutTemp;
		this.slLevel = slLevel;
		this.superheatTemp = superheatTemp;
		this.superheatPiezoresistance = superheatPiezoresistance;
		this.superheatinTemp = superheatinTemp;
		this.superheatoutTemp = superheatoutTemp;
		this.superheatinPress = superheatinPress;
		this.superheatoutPress = superheatoutPress;
		this.superheatinFlow = superheatinFlow;
		this.hearthPress = hearthPress;
		this.gas1Press = gas1Press;
		this.gas2Press = gas2Press;
		this.gas3Press = gas3Press;
		this.rsTemp = rsTemp;
		this.rsDryness = rsDryness;
		this.rsPiezoresistance = rsPiezoresistance;
		this.rsinPress = rsinPress;
		this.rsPress = rsPress;
		this.rsinTemp = rsinTemp;
		this.rsoutTemp = rsoutTemp;
		this.pumpaFlow = pumpaFlow;
		this.pumpbFlow = pumpbFlow;
		this.pumpcFlow = pumpcFlow;
		this.pumpaPress = pumpaPress;
		this.pumpbPress = pumpbPress;
		this.pumpcPress = pumpcPress;
		this.fanaElectricity = fanaElectricity;
		this.fanbElectricity = fanbElectricity;
		this.fancElectricity = fancElectricity;
		this.pumpfcFrequency = pumpfcFrequency;
		this.pumpinPress = pumpinPress;
		this.pumpoutPress = pumpoutPress;
		this.pumpinTemp = pumpinTemp;
		this.pumpoutTemp = pumpoutTemp;
		this.watersupplyFlow = watersupplyFlow;
		this.watersupplyTotal = watersupplyTotal;
		this.gasFlow = gasFlow;
		this.gasTotal = gasTotal;
		this.steaminjectionTotal = steaminjectionTotal;
		this.dailyCumulativeWater = dailyCumulativeWater;
		this.dailyCumulativeGas = dailyCumulativeGas;
		this.dailyCumulativeSteam = dailyCumulativeSteam;
		this.gasIntoPress = gasIntoPress;
		this.fireMeasure = fireMeasure;
		this.mixerPressReduction = mixerPressReduction;
		this.convectionrPressReduction = convectionrPressReduction;
		this.lubeTemp = lubeTemp;
		this.systemVoltage = systemVoltage;
		this.pumpMotorTemp = pumpMotorTemp;
		this.pumpMotorCurrent = pumpMotorCurrent;
		this.rsPressReduction = rsPressReduction;
		this.superheatPressReduction = superheatPressReduction;
		this.fanMotorCurrent = fanMotorCurrent;
		this.steamDryness = steamDryness;
		this.reheatPressEntrance = reheatPressEntrance;
		this.reheatTempEntrance = reheatTempEntrance;
		this.reheatTempExport = reheatTempExport;
		this.blockName = blockName;
		this.steamWorkGroup = steamWorkGroup;
		this.steamInjeUnit = steamInjeUnit;
		this.fanAirIntakeTemp = fanAirIntakeTemp;
		this.h2sDensity = h2sDensity;
		this.fuelGasDensity = fuelGasDensity;
		this.systemPressReduction = systemPressReduction;
		this.runtime = runtime;
		this.rerheatPressReduction = rerheatPressReduction;
		this.sewageBufferTank = sewageBufferTank;
		this.rlastOper = rlastOper;
		this.rlastOdate = rlastOdate;
		this.checkOper = checkOper;
		this.checkDate = checkDate;
		this.remark = remark;
	}

	// Property accessors

	public String getRpdBoilerHighDryId() {
		return this.rpdBoilerHighDryId;
	}

	public java.math.BigDecimal getSewageBufferTank() {
		return sewageBufferTank;
	}

	public void setSewageBufferTank(java.math.BigDecimal sewageBufferTank) {
		this.sewageBufferTank = sewageBufferTank;
	}

	public void setRpdBoilerHighDryId(String rpdBoilerHighDryId) {
		this.rpdBoilerHighDryId = rpdBoilerHighDryId;
	}

	public PcCdBoilerT getPcCdBoilerT() {
		return this.pcCdBoilerT;
	}

	public void setPcCdBoilerT(PcCdBoilerT pcCdBoilerT) {
		this.pcCdBoilerT = pcCdBoilerT;
	}

	public Date getReportDate() {
		return this.reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	public String getOildrillingStationName() {
		return this.oildrillingStationName;
	}

	public void setOildrillingStationName(String oildrillingStationName) {
		this.oildrillingStationName = oildrillingStationName;
	}

	public String getBlockstationName() {
		return this.blockstationName;
	}

	public void setBlockstationName(String blockstationName) {
		this.blockstationName = blockstationName;
	}

	public String getBoilerName() {
		return this.boilerName;
	}

	public void setBoilerName(String boilerName) {
		this.boilerName = boilerName;
	}

	public java.math.BigDecimal getSteamoutTemp() {
		return this.steamoutTemp;
	}

	public void setSteamoutTemp(java.math.BigDecimal steamoutTemp) {
		this.steamoutTemp = steamoutTemp;
	}

	public java.math.BigDecimal getSteaminTemp() {
		return this.steaminTemp;
	}

	public void setSteaminTemp(java.math.BigDecimal steaminTemp) {
		this.steaminTemp = steaminTemp;
	}

	public java.math.BigDecimal getEjectsmokeTemp() {
		return this.ejectsmokeTemp;
	}

	public void setEjectsmokeTemp(java.math.BigDecimal ejectsmokeTemp) {
		this.ejectsmokeTemp = ejectsmokeTemp;
	}

	public java.math.BigDecimal getBurnerTemp() {
		return this.burnerTemp;
	}

	public void setBurnerTemp(java.math.BigDecimal burnerTemp) {
		this.burnerTemp = burnerTemp;
	}

	public java.math.BigDecimal getCsinPress() {
		return this.csinPress;
	}

	public void setCsinPress(java.math.BigDecimal csinPress) {
		this.csinPress = csinPress;
	}

	public java.math.BigDecimal getCsoutPress() {
		return this.csoutPress;
	}

	public void setCsoutPress(java.math.BigDecimal csoutPress) {
		this.csoutPress = csoutPress;
	}

	public java.math.BigDecimal getCsinTemp() {
		return this.csinTemp;
	}

	public void setCsinTemp(java.math.BigDecimal csinTemp) {
		this.csinTemp = csinTemp;
	}

	public java.math.BigDecimal getCsoutTemp() {
		return this.csoutTemp;
	}

	public void setCsoutTemp(java.math.BigDecimal csoutTemp) {
		this.csoutTemp = csoutTemp;
	}

	public java.math.BigDecimal getSlLevel() {
		return this.slLevel;
	}

	public void setSlLevel(java.math.BigDecimal slLevel) {
		this.slLevel = slLevel;
	}

	public java.math.BigDecimal getSuperheatTemp() {
		return this.superheatTemp;
	}

	public void setSuperheatTemp(java.math.BigDecimal superheatTemp) {
		this.superheatTemp = superheatTemp;
	}

	public java.math.BigDecimal getSuperheatPiezoresistance() {
		return this.superheatPiezoresistance;
	}

	public void setSuperheatPiezoresistance(java.math.BigDecimal superheatPiezoresistance) {
		this.superheatPiezoresistance = superheatPiezoresistance;
	}

	public java.math.BigDecimal getSuperheatinTemp() {
		return this.superheatinTemp;
	}

	public void setSuperheatinTemp(java.math.BigDecimal superheatinTemp) {
		this.superheatinTemp = superheatinTemp;
	}

	public java.math.BigDecimal getSuperheatoutTemp() {
		return this.superheatoutTemp;
	}

	public void setSuperheatoutTemp(java.math.BigDecimal superheatoutTemp) {
		this.superheatoutTemp = superheatoutTemp;
	}

	public java.math.BigDecimal getSuperheatinPress() {
		return this.superheatinPress;
	}

	public void setSuperheatinPress(java.math.BigDecimal superheatinPress) {
		this.superheatinPress = superheatinPress;
	}

	public java.math.BigDecimal getSuperheatoutPress() {
		return this.superheatoutPress;
	}

	public void setSuperheatoutPress(java.math.BigDecimal superheatoutPress) {
		this.superheatoutPress = superheatoutPress;
	}

	public java.math.BigDecimal getSuperheatinFlow() {
		return this.superheatinFlow;
	}

	public void setSuperheatinFlow(java.math.BigDecimal superheatinFlow) {
		this.superheatinFlow = superheatinFlow;
	}

	public java.math.BigDecimal getHearthPress() {
		return this.hearthPress;
	}

	public void setHearthPress(java.math.BigDecimal hearthPress) {
		this.hearthPress = hearthPress;
	}

	public java.math.BigDecimal getGas1Press() {
		return this.gas1Press;
	}

	public void setGas1Press(java.math.BigDecimal gas1Press) {
		this.gas1Press = gas1Press;
	}

	public java.math.BigDecimal getGas2Press() {
		return this.gas2Press;
	}

	public void setGas2Press(java.math.BigDecimal gas2Press) {
		this.gas2Press = gas2Press;
	}

	public java.math.BigDecimal getGas3Press() {
		return this.gas3Press;
	}

	public void setGas3Press(java.math.BigDecimal gas3Press) {
		this.gas3Press = gas3Press;
	}

	public java.math.BigDecimal getRsTemp() {
		return this.rsTemp;
	}

	public void setRsTemp(java.math.BigDecimal rsTemp) {
		this.rsTemp = rsTemp;
	}

	public java.math.BigDecimal getRsDryness() {
		return this.rsDryness;
	}

	public void setRsDryness(java.math.BigDecimal rsDryness) {
		this.rsDryness = rsDryness;
	}

	public java.math.BigDecimal getRsPiezoresistance() {
		return this.rsPiezoresistance;
	}

	public void setRsPiezoresistance(java.math.BigDecimal rsPiezoresistance) {
		this.rsPiezoresistance = rsPiezoresistance;
	}

	public java.math.BigDecimal getRsinPress() {
		return this.rsinPress;
	}

	public void setRsinPress(java.math.BigDecimal rsinPress) {
		this.rsinPress = rsinPress;
	}

	public java.math.BigDecimal getRsPress() {
		return this.rsPress;
	}

	public void setRsPress(java.math.BigDecimal rsPress) {
		this.rsPress = rsPress;
	}

	public java.math.BigDecimal getRsinTemp() {
		return this.rsinTemp;
	}

	public void setRsinTemp(java.math.BigDecimal rsinTemp) {
		this.rsinTemp = rsinTemp;
	}

	public java.math.BigDecimal getRsoutTemp() {
		return this.rsoutTemp;
	}

	public void setRsoutTemp(java.math.BigDecimal rsoutTemp) {
		this.rsoutTemp = rsoutTemp;
	}

	public java.math.BigDecimal getPumpaFlow() {
		return this.pumpaFlow;
	}

	public void setPumpaFlow(java.math.BigDecimal pumpaFlow) {
		this.pumpaFlow = pumpaFlow;
	}

	public java.math.BigDecimal getPumpbFlow() {
		return this.pumpbFlow;
	}

	public void setPumpbFlow(java.math.BigDecimal pumpbFlow) {
		this.pumpbFlow = pumpbFlow;
	}

	public java.math.BigDecimal getPumpcFlow() {
		return this.pumpcFlow;
	}

	public void setPumpcFlow(java.math.BigDecimal pumpcFlow) {
		this.pumpcFlow = pumpcFlow;
	}

	public java.math.BigDecimal getPumpaPress() {
		return this.pumpaPress;
	}

	public void setPumpaPress(java.math.BigDecimal pumpaPress) {
		this.pumpaPress = pumpaPress;
	}

	public java.math.BigDecimal getPumpbPress() {
		return this.pumpbPress;
	}

	public void setPumpbPress(java.math.BigDecimal pumpbPress) {
		this.pumpbPress = pumpbPress;
	}

	public java.math.BigDecimal getPumpcPress() {
		return this.pumpcPress;
	}

	public void setPumpcPress(java.math.BigDecimal pumpcPress) {
		this.pumpcPress = pumpcPress;
	}

	public java.math.BigDecimal getFanaElectricity() {
		return this.fanaElectricity;
	}

	public void setFanaElectricity(java.math.BigDecimal fanaElectricity) {
		this.fanaElectricity = fanaElectricity;
	}

	public java.math.BigDecimal getFanbElectricity() {
		return this.fanbElectricity;
	}

	public void setFanbElectricity(java.math.BigDecimal fanbElectricity) {
		this.fanbElectricity = fanbElectricity;
	}

	public java.math.BigDecimal getFancElectricity() {
		return this.fancElectricity;
	}

	public void setFancElectricity(java.math.BigDecimal fancElectricity) {
		this.fancElectricity = fancElectricity;
	}

	public java.math.BigDecimal getPumpfcFrequency() {
		return this.pumpfcFrequency;
	}

	public void setPumpfcFrequency(java.math.BigDecimal pumpfcFrequency) {
		this.pumpfcFrequency = pumpfcFrequency;
	}

	public java.math.BigDecimal getPumpinPress() {
		return this.pumpinPress;
	}

	public void setPumpinPress(java.math.BigDecimal pumpinPress) {
		this.pumpinPress = pumpinPress;
	}

	public java.math.BigDecimal getPumpoutPress() {
		return this.pumpoutPress;
	}

	public void setPumpoutPress(java.math.BigDecimal pumpoutPress) {
		this.pumpoutPress = pumpoutPress;
	}

	public java.math.BigDecimal getPumpinTemp() {
		return this.pumpinTemp;
	}

	public void setPumpinTemp(java.math.BigDecimal pumpinTemp) {
		this.pumpinTemp = pumpinTemp;
	}

	public java.math.BigDecimal getPumpoutTemp() {
		return this.pumpoutTemp;
	}

	public void setPumpoutTemp(java.math.BigDecimal pumpoutTemp) {
		this.pumpoutTemp = pumpoutTemp;
	}

	public java.math.BigDecimal getWatersupplyFlow() {
		return this.watersupplyFlow;
	}

	public void setWatersupplyFlow(java.math.BigDecimal watersupplyFlow) {
		this.watersupplyFlow = watersupplyFlow;
	}

	public java.math.BigDecimal getWatersupplyTotal() {
		return this.watersupplyTotal;
	}

	public void setWatersupplyTotal(java.math.BigDecimal watersupplyTotal) {
		this.watersupplyTotal = watersupplyTotal;
	}

	public java.math.BigDecimal getGasFlow() {
		return this.gasFlow;
	}

	public void setGasFlow(java.math.BigDecimal gasFlow) {
		this.gasFlow = gasFlow;
	}

	public java.math.BigDecimal getGasTotal() {
		return this.gasTotal;
	}

	public void setGasTotal(java.math.BigDecimal gasTotal) {
		this.gasTotal = gasTotal;
	}

	public java.math.BigDecimal getSteaminjectionTotal() {
		return this.steaminjectionTotal;
	}

	public void setSteaminjectionTotal(java.math.BigDecimal steaminjectionTotal) {
		this.steaminjectionTotal = steaminjectionTotal;
	}

	public java.math.BigDecimal getDailyCumulativeWater() {
		return this.dailyCumulativeWater;
	}

	public void setDailyCumulativeWater(java.math.BigDecimal dailyCumulativeWater) {
		this.dailyCumulativeWater = dailyCumulativeWater;
	}

	public java.math.BigDecimal getDailyCumulativeGas() {
		return this.dailyCumulativeGas;
	}

	public void setDailyCumulativeGas(java.math.BigDecimal dailyCumulativeGas) {
		this.dailyCumulativeGas = dailyCumulativeGas;
	}

	public java.math.BigDecimal getDailyCumulativeSteam() {
		return this.dailyCumulativeSteam;
	}

	public void setDailyCumulativeSteam(java.math.BigDecimal dailyCumulativeSteam) {
		this.dailyCumulativeSteam = dailyCumulativeSteam;
	}

	public java.math.BigDecimal getGasIntoPress() {
		return this.gasIntoPress;
	}

	public void setGasIntoPress(java.math.BigDecimal gasIntoPress) {
		this.gasIntoPress = gasIntoPress;
	}

	public java.math.BigDecimal getFireMeasure() {
		return this.fireMeasure;
	}

	public void setFireMeasure(java.math.BigDecimal fireMeasure) {
		this.fireMeasure = fireMeasure;
	}

	public java.math.BigDecimal getMixerPressReduction() {
		return this.mixerPressReduction;
	}

	public void setMixerPressReduction(java.math.BigDecimal mixerPressReduction) {
		this.mixerPressReduction = mixerPressReduction;
	}

	public java.math.BigDecimal getConvectionrPressReduction() {
		return this.convectionrPressReduction;
	}

	public void setConvectionrPressReduction(java.math.BigDecimal convectionrPressReduction) {
		this.convectionrPressReduction = convectionrPressReduction;
	}

	public java.math.BigDecimal getLubeTemp() {
		return this.lubeTemp;
	}

	public void setLubeTemp(java.math.BigDecimal lubeTemp) {
		this.lubeTemp = lubeTemp;
	}

	public java.math.BigDecimal getSystemVoltage() {
		return this.systemVoltage;
	}

	public void setSystemVoltage(java.math.BigDecimal systemVoltage) {
		this.systemVoltage = systemVoltage;
	}

	public java.math.BigDecimal getPumpMotorTemp() {
		return this.pumpMotorTemp;
	}

	public void setPumpMotorTemp(java.math.BigDecimal pumpMotorTemp) {
		this.pumpMotorTemp = pumpMotorTemp;
	}

	public java.math.BigDecimal getPumpMotorCurrent() {
		return this.pumpMotorCurrent;
	}

	public void setPumpMotorCurrent(java.math.BigDecimal pumpMotorCurrent) {
		this.pumpMotorCurrent = pumpMotorCurrent;
	}

	public java.math.BigDecimal getRsPressReduction() {
		return this.rsPressReduction;
	}

	public void setRsPressReduction(java.math.BigDecimal rsPressReduction) {
		this.rsPressReduction = rsPressReduction;
	}

	public java.math.BigDecimal getSuperheatPressReduction() {
		return this.superheatPressReduction;
	}

	public void setSuperheatPressReduction(java.math.BigDecimal superheatPressReduction) {
		this.superheatPressReduction = superheatPressReduction;
	}

	public java.math.BigDecimal getFanMotorCurrent() {
		return this.fanMotorCurrent;
	}

	public void setFanMotorCurrent(java.math.BigDecimal fanMotorCurrent) {
		this.fanMotorCurrent = fanMotorCurrent;
	}

	public java.math.BigDecimal getSteamDryness() {
		return this.steamDryness;
	}

	public void setSteamDryness(java.math.BigDecimal steamDryness) {
		this.steamDryness = steamDryness;
	}

	public java.math.BigDecimal getReheatPressEntrance() {
		return this.reheatPressEntrance;
	}

	public void setReheatPressEntrance(java.math.BigDecimal reheatPressEntrance) {
		this.reheatPressEntrance = reheatPressEntrance;
	}

	public java.math.BigDecimal getReheatTempEntrance() {
		return this.reheatTempEntrance;
	}

	public void setReheatTempEntrance(java.math.BigDecimal reheatTempEntrance) {
		this.reheatTempEntrance = reheatTempEntrance;
	}

	public java.math.BigDecimal getReheatTempExport() {
		return this.reheatTempExport;
	}

	public void setReheatTempExport(java.math.BigDecimal reheatTempExport) {
		this.reheatTempExport = reheatTempExport;
	}

	public String getBlockName() {
		return this.blockName;
	}

	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}

	public String getSteamWorkGroup() {
		return this.steamWorkGroup;
	}

	public void setSteamWorkGroup(String steamWorkGroup) {
		this.steamWorkGroup = steamWorkGroup;
	}

	public String getSteamInjeUnit() {
		return this.steamInjeUnit;
	}

	public void setSteamInjeUnit(String steamInjeUnit) {
		this.steamInjeUnit = steamInjeUnit;
	}

	public java.math.BigDecimal getFanAirIntakeTemp() {
		return this.fanAirIntakeTemp;
	}

	public void setFanAirIntakeTemp(java.math.BigDecimal fanAirIntakeTemp) {
		this.fanAirIntakeTemp = fanAirIntakeTemp;
	}

	public java.math.BigDecimal getH2sDensity() {
		return this.h2sDensity;
	}

	public void setH2sDensity(java.math.BigDecimal h2sDensity) {
		this.h2sDensity = h2sDensity;
	}

	public java.math.BigDecimal getFuelGasDensity() {
		return this.fuelGasDensity;
	}

	public void setFuelGasDensity(java.math.BigDecimal fuelGasDensity) {
		this.fuelGasDensity = fuelGasDensity;
	}

	public java.math.BigDecimal getSystemPressReduction() {
		return this.systemPressReduction;
	}

	public void setSystemPressReduction(java.math.BigDecimal systemPressReduction) {
		this.systemPressReduction = systemPressReduction;
	}

	public java.math.BigDecimal getRuntime() {
		return this.runtime;
	}

	public void setRuntime(java.math.BigDecimal runtime) {
		this.runtime = runtime;
	}

	public java.math.BigDecimal getRerheatPressReduction() {
		return this.rerheatPressReduction;
	}

	public void setRerheatPressReduction(java.math.BigDecimal rerheatPressReduction) {
		this.rerheatPressReduction = rerheatPressReduction;
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

	public String getCheckOper() {
		return checkOper;
	}

	public void setCheckOper(String checkOper) {
		this.checkOper = checkOper;
	}

	public Date getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}