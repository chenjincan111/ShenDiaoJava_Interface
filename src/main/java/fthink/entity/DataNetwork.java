package fthink.entity;

public class DataNetwork {

	private String deviceNo;//设备序列号
	private String analyDate;//日期
	private String networkConditions;//网络环境情况
	private String lastModifyTime;//最后修改时间
	
	public String getDeviceNo() {
		return deviceNo;
	}
	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}
	public String getAnalyDate() {
		return analyDate;
	}
	public void setAnalyDate(String analyDate) {
		this.analyDate = analyDate;
	}
	public String getNetworkConditions() {
		return networkConditions;
	}
	public void setNetworkConditions(String networkConditions) {
		this.networkConditions = networkConditions;
	}
	public String getLastModifyTime() {
		return lastModifyTime;
	}
	public void setLastModifyTime(String lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}
	
}
