package com.echo.dto;

import java.util.Date;

/**
 * PcRpdBoilerCrossdT entity. @author MyEclipse Persistence Tools
 */

public class PcRpdBoilerCrossdT implements java.io.Serializable {

	// Fields

	private String boilerCrossddid;
	private String boilerid;
	//private PcCdBoilerT pcCdBoilerT;
	private long steamoutTemp;
	private long steaminTemp;
	private long ejectsmokeTemp;
	private long burnerTemp;
	private long csinPress;
	private long csoutPress;
	private long csinTemp;
	private long csoutTemp;
	private long slLevel;
	private long superheatTemp;
	private long superheatPiezoresistance;
	private long superheatinTemp;
	private long superheatoutTemp;
	private long superheatinPress;
	private long superheatoutPress;
	private long superheatinFlow;
	private long hearthPress;
	private long gas1Press;
	private long gas2Press;
	private long gas3Press;
	private long rsTemp;
	private long rsDryness;
	private long rsPiezoresistance;
	private long rsinPress;
	private long rsPress;
	private long rsinTemp;
	private long rsoutTemp;
	private long pumpaFlow;
	private long pumpbFlow;
	private long pumpcFlow;
	private long pumpaPress;
	private long pumpbPress;
	private long pumpcPress;
	private long fanaElectricity;
	private long fanbElectricity;
	private long fancElectricity;
	private long pumpfcFrequency;
	private long pumpinPress;
	private long pumpoutPress;
	private long pumpinTemp;
	private long pumpoutTemp;
	private long watersupplyFlow;
	private long watersupplyTotal;
	private long gasFlow;
	private long gasTotal;
	private long steaminjectionTotal;
	private Date reportDate;
	private String oildrillingStationName;
	private String blockstationName;
	private String boilerName;
	private long dailyCumulativeWater;
	private long dailyCumulativeGas;
	private long dailyCumulativeSteam;

	// Constructors

	/** default constructor */
	public PcRpdBoilerCrossdT() {
	}

	/** full constructor */
	public PcRpdBoilerCrossdT(String boilerid, long steamoutTemp,
			long steaminTemp, long ejectsmokeTemp, long burnerTemp,
			long csinPress, long csoutPress, long csinTemp,
			long csoutTemp, long slLevel, long superheatTemp,
			long superheatPiezoresistance, long superheatinTemp,
			long superheatoutTemp, long superheatinPress,
			long superheatoutPress, long superheatinFlow,
			long hearthPress, long gas1Press, long gas2Press,
			long gas3Press, long rsTemp, long rsDryness,
			long rsPiezoresistance, long rsinPress, long rsPress,
			long rsinTemp, long rsoutTemp, long pumpaFlow,
			long pumpbFlow, long pumpcFlow, long pumpaPress,
			long pumpbPress, long pumpcPress, long fanaElectricity,
			long fanbElectricity, long fancElectricity,
			long pumpfcFrequency, long pumpinPress, long pumpoutPress,
			long pumpinTemp, long pumpoutTemp, long watersupplyFlow,
			long watersupplyTotal, long gasFlow, long gasTotal,
			long steaminjectionTotal, Date reportDate,
			String oildrillingStationName, String blockstationName,
			String boilerName, long dailyCumulativeWater,
			long dailyCumulativeGas, long dailyCumulativeSteam) {
		this.boilerid = boilerid;
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
		this.reportDate = reportDate;
		this.oildrillingStationName = oildrillingStationName;
		this.blockstationName = blockstationName;
		this.boilerName = boilerName;
		this.dailyCumulativeWater = dailyCumulativeWater;
		this.dailyCumulativeGas = dailyCumulativeGas;
		this.dailyCumulativeSteam = dailyCumulativeSteam;
	}

	// Property accessors

	public String getBoilerCrossddid() {
		return this.boilerCrossddid;
	}

	public void setBoilerCrossddid(String boilerCrossddid) {
		this.boilerCrossddid = boilerCrossddid;
	}


	public String getBoilerid() {
		return boilerid;
	}

	public void setBoilerid(String boilerid) {
		this.boilerid = boilerid;
	}

	public long getSteamoutTemp() {
		return this.steamoutTemp;
	}

	public void setSteamoutTemp(long steamoutTemp) {
		this.steamoutTemp = steamoutTemp;
	}

	public long getSteaminTemp() {
		return this.steaminTemp;
	}

	public void setSteaminTemp(long steaminTemp) {
		this.steaminTemp = steaminTemp;
	}

	public long getEjectsmokeTemp() {
		return this.ejectsmokeTemp;
	}

	public void setEjectsmokeTemp(long ejectsmokeTemp) {
		this.ejectsmokeTemp = ejectsmokeTemp;
	}

	public long getBurnerTemp() {
		return this.burnerTemp;
	}

	public void setBurnerTemp(long burnerTemp) {
		this.burnerTemp = burnerTemp;
	}

	public long getCsinPress() {
		return this.csinPress;
	}

	public void setCsinPress(long csinPress) {
		this.csinPress = csinPress;
	}

	public long getCsoutPress() {
		return this.csoutPress;
	}

	public void setCsoutPress(long csoutPress) {
		this.csoutPress = csoutPress;
	}

	public long getCsinTemp() {
		return this.csinTemp;
	}

	public void setCsinTemp(long csinTemp) {
		this.csinTemp = csinTemp;
	}

	public long getCsoutTemp() {
		return this.csoutTemp;
	}

	public void setCsoutTemp(long csoutTemp) {
		this.csoutTemp = csoutTemp;
	}

	public long getSlLevel() {
		return this.slLevel;
	}

	public void setSlLevel(long slLevel) {
		this.slLevel = slLevel;
	}

	public long getSuperheatTemp() {
		return this.superheatTemp;
	}

	public void setSuperheatTemp(long superheatTemp) {
		this.superheatTemp = superheatTemp;
	}

	public long getSuperheatPiezoresistance() {
		return this.superheatPiezoresistance;
	}

	public void setSuperheatPiezoresistance(long superheatPiezoresistance) {
		this.superheatPiezoresistance = superheatPiezoresistance;
	}

	public long getSuperheatinTemp() {
		return this.superheatinTemp;
	}

	public void setSuperheatinTemp(long superheatinTemp) {
		this.superheatinTemp = superheatinTemp;
	}

	public long getSuperheatoutTemp() {
		return this.superheatoutTemp;
	}

	public void setSuperheatoutTemp(long superheatoutTemp) {
		this.superheatoutTemp = superheatoutTemp;
	}

	public long getSuperheatinPress() {
		return this.superheatinPress;
	}

	public void setSuperheatinPress(long superheatinPress) {
		this.superheatinPress = superheatinPress;
	}

	public long getSuperheatoutPress() {
		return this.superheatoutPress;
	}

	public void setSuperheatoutPress(long superheatoutPress) {
		this.superheatoutPress = superheatoutPress;
	}

	public long getSuperheatinFlow() {
		return this.superheatinFlow;
	}

	public void setSuperheatinFlow(long superheatinFlow) {
		this.superheatinFlow = superheatinFlow;
	}

	public long getHearthPress() {
		return this.hearthPress;
	}

	public void setHearthPress(long hearthPress) {
		this.hearthPress = hearthPress;
	}

	public long getGas1Press() {
		return this.gas1Press;
	}

	public void setGas1Press(long gas1Press) {
		this.gas1Press = gas1Press;
	}

	public long getGas2Press() {
		return this.gas2Press;
	}

	public void setGas2Press(long gas2Press) {
		this.gas2Press = gas2Press;
	}

	public long getGas3Press() {
		return this.gas3Press;
	}

	public void setGas3Press(long gas3Press) {
		this.gas3Press = gas3Press;
	}

	public long getRsTemp() {
		return this.rsTemp;
	}

	public void setRsTemp(long rsTemp) {
		this.rsTemp = rsTemp;
	}

	public long getRsDryness() {
		return this.rsDryness;
	}

	public void setRsDryness(long rsDryness) {
		this.rsDryness = rsDryness;
	}

	public long getRsPiezoresistance() {
		return this.rsPiezoresistance;
	}

	public void setRsPiezoresistance(long rsPiezoresistance) {
		this.rsPiezoresistance = rsPiezoresistance;
	}

	public long getRsinPress() {
		return this.rsinPress;
	}

	public void setRsinPress(long rsinPress) {
		this.rsinPress = rsinPress;
	}

	public long getRsPress() {
		return this.rsPress;
	}

	public void setRsPress(long rsPress) {
		this.rsPress = rsPress;
	}

	public long getRsinTemp() {
		return this.rsinTemp;
	}

	public void setRsinTemp(long rsinTemp) {
		this.rsinTemp = rsinTemp;
	}

	public long getRsoutTemp() {
		return this.rsoutTemp;
	}

	public void setRsoutTemp(long rsoutTemp) {
		this.rsoutTemp = rsoutTemp;
	}

	public long getPumpaFlow() {
		return this.pumpaFlow;
	}

	public void setPumpaFlow(long pumpaFlow) {
		this.pumpaFlow = pumpaFlow;
	}

	public long getPumpbFlow() {
		return this.pumpbFlow;
	}

	public void setPumpbFlow(long pumpbFlow) {
		this.pumpbFlow = pumpbFlow;
	}

	public long getPumpcFlow() {
		return this.pumpcFlow;
	}

	public void setPumpcFlow(long pumpcFlow) {
		this.pumpcFlow = pumpcFlow;
	}

	public long getPumpaPress() {
		return this.pumpaPress;
	}

	public void setPumpaPress(long pumpaPress) {
		this.pumpaPress = pumpaPress;
	}

	public long getPumpbPress() {
		return this.pumpbPress;
	}

	public void setPumpbPress(long pumpbPress) {
		this.pumpbPress = pumpbPress;
	}

	public long getPumpcPress() {
		return this.pumpcPress;
	}

	public void setPumpcPress(long pumpcPress) {
		this.pumpcPress = pumpcPress;
	}

	public long getFanaElectricity() {
		return this.fanaElectricity;
	}

	public void setFanaElectricity(long fanaElectricity) {
		this.fanaElectricity = fanaElectricity;
	}

	public long getFanbElectricity() {
		return this.fanbElectricity;
	}

	public void setFanbElectricity(long fanbElectricity) {
		this.fanbElectricity = fanbElectricity;
	}

	public long getFancElectricity() {
		return this.fancElectricity;
	}

	public void setFancElectricity(long fancElectricity) {
		this.fancElectricity = fancElectricity;
	}

	public long getPumpfcFrequency() {
		return this.pumpfcFrequency;
	}

	public void setPumpfcFrequency(long pumpfcFrequency) {
		this.pumpfcFrequency = pumpfcFrequency;
	}

	public long getPumpinPress() {
		return this.pumpinPress;
	}

	public void setPumpinPress(long pumpinPress) {
		this.pumpinPress = pumpinPress;
	}

	public long getPumpoutPress() {
		return this.pumpoutPress;
	}

	public void setPumpoutPress(long pumpoutPress) {
		this.pumpoutPress = pumpoutPress;
	}

	public long getPumpinTemp() {
		return this.pumpinTemp;
	}

	public void setPumpinTemp(long pumpinTemp) {
		this.pumpinTemp = pumpinTemp;
	}

	public long getPumpoutTemp() {
		return this.pumpoutTemp;
	}

	public void setPumpoutTemp(long pumpoutTemp) {
		this.pumpoutTemp = pumpoutTemp;
	}

	public long getWatersupplyFlow() {
		return this.watersupplyFlow;
	}

	public void setWatersupplyFlow(long watersupplyFlow) {
		this.watersupplyFlow = watersupplyFlow;
	}

	public long getWatersupplyTotal() {
		return this.watersupplyTotal;
	}

	public void setWatersupplyTotal(long watersupplyTotal) {
		this.watersupplyTotal = watersupplyTotal;
	}

	public long getGasFlow() {
		return this.gasFlow;
	}

	public void setGasFlow(long gasFlow) {
		this.gasFlow = gasFlow;
	}

	public long getGasTotal() {
		return this.gasTotal;
	}

	public void setGasTotal(long gasTotal) {
		this.gasTotal = gasTotal;
	}

	public long getSteaminjectionTotal() {
		return this.steaminjectionTotal;
	}

	public void setSteaminjectionTotal(long steaminjectionTotal) {
		this.steaminjectionTotal = steaminjectionTotal;
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

	public long getDailyCumulativeWater() {
		return this.dailyCumulativeWater;
	}

	public void setDailyCumulativeWater(long dailyCumulativeWater) {
		this.dailyCumulativeWater = dailyCumulativeWater;
	}

	public long getDailyCumulativeGas() {
		return this.dailyCumulativeGas;
	}

	public void setDailyCumulativeGas(long dailyCumulativeGas) {
		this.dailyCumulativeGas = dailyCumulativeGas;
	}

	public long getDailyCumulativeSteam() {
		return this.dailyCumulativeSteam;
	}

	public void setDailyCumulativeSteam(long dailyCumulativeSteam) {
		this.dailyCumulativeSteam = dailyCumulativeSteam;
	}

}