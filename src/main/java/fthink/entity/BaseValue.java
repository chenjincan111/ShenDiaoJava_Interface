package fthink.entity;

import java.util.List;

import fthink.dto.StartEndValue;

public class BaseValue {

	private List<StartEndValue> businessTimeList;
	private List<StartEndValue> tradeList;
	private List<StartEndValue> switchList;
	private NetworkChangeValue networkChangeValue;

	public NetworkChangeValue getNetworkChangeValue() {
		return networkChangeValue;
	}

	public void setNetworkChangeValue(NetworkChangeValue networkChangeValue) {
		this.networkChangeValue = networkChangeValue;
	}

	public List<StartEndValue> getBusinessTimeList() {
		return businessTimeList;
	}

	public void setBusinessTimeList(List<StartEndValue> businessTimeList) {
		this.businessTimeList = businessTimeList;
	}

	public List<StartEndValue> getTradeList() {
		return tradeList;
	}

	public void setTradeList(List<StartEndValue> tradeList) {
		this.tradeList = tradeList;
	}

	public List<StartEndValue> getSwitchList() {
		return switchList;
	}

	public void setSwitchList(List<StartEndValue> switchList) {
		this.switchList = switchList;
	}
	
	
}
