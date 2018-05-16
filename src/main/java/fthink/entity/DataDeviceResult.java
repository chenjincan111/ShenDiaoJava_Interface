package fthink.entity;

/**
 * 设备数据分析结果表
 * @author zhu-jq
 *
 */
public class DataDeviceResult {

	private String deviceNo;
	private String businessTime;
	private Integer timeCount;
	private Integer traceCount;
	private Integer switchTimes;
	private String lastModifyTime;
	
	private Double traceSuccessRate;
	private long traffic;
	private Integer bootTime;
	private Integer offTime;
	
	public Double getTraceSuccessRate() {
		return traceSuccessRate;
	}
	public void setTraceSuccessRate(Double traceSuccessRate) {
		this.traceSuccessRate = traceSuccessRate;
	}
	public long getTraffic() {
		return traffic;
	}
	public void setTraffic(long traffic) {
		this.traffic = traffic;
	}
	public Integer getBootTime() {
		return bootTime;
	}
	public void setBootTime(Integer bootTime) {
		this.bootTime = bootTime;
	}
	public Integer getOffTime() {
		return offTime;
	}
	public void setOffTime(Integer offTime) {
		this.offTime = offTime;
	}
	public String getDeviceNo() {
		return deviceNo;
	}
	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}
	public String getBusinessTime() {
		return businessTime;
	}
	public void setBusinessTime(String businessTime) {
		this.businessTime = businessTime;
	}
	public Integer getTimeCount() {
		return timeCount;
	}
	public void setTimeCount(Integer timeCount) {
		this.timeCount = timeCount;
	}
	public Integer getTraceCount() {
		return traceCount;
	}
	public void setTraceCount(Integer traceCount) {
		this.traceCount = traceCount;
	}
	public Integer getSwitchTimes() {
		return switchTimes;
	}
	public void setSwitchTimes(Integer switchTimes) {
		this.switchTimes = switchTimes;
	}
	public String getLastModifyTime() {
		return lastModifyTime;
	}
	public void setLastModifyTime(String lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}
	
}
