package fthink.entity;

public class DateBusinessTime {

	private String deviceNo;//设备序列号
	private String analyDay;//日期
	private String businessTime;//运营时间段
	private int timeCount;//运营时长
	private String lastModifyTime;//最后修改时间
	
	public String getDeviceNo() {
		return deviceNo;
	}
	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}
	public String getAnalyDay() {
		return analyDay;
	}
	public void setAnalyDay(String analyDay) {
		this.analyDay = analyDay;
	}
	public String getBusinessTime() {
		return businessTime;
	}
	public void setBusinessTime(String businessTime) {
		this.businessTime = businessTime;
	}
	public int getTimeCount() {
		return timeCount;
	}
	public void setTimeCount(int timeCount) {
		this.timeCount = timeCount;
	}
	public String getLastModifyTime() {
		return lastModifyTime;
	}
	public void setLastModifyTime(String lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}
	
	
}
