package fthink.entity;

public class BehaviorData {

	private String deviceNo;//设备序列号
	private String countDate;//统计日期
	private String dataType;//行为数据类型
	private String dataTime;//行为数据上送时间
	private Integer magneticCardNum;//磁条卡读卡次数
	private Integer nonConnectedIcNum;//非接ic卡读卡次数
	private Integer contactIcNum;//接触ic卡读卡次数
	private Integer cipherKeyboardNum;//密码键盘使用次数
	
	public String getDeviceNo() {
		return deviceNo;
	}
	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}
	public String getCountDate() {
		return countDate;
	}
	public void setCountDate(String countDate) {
		this.countDate = countDate;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getDataTime() {
		return dataTime;
	}
	public void setDataTime(String dataTime) {
		this.dataTime = dataTime;
	}
	public Integer getMagneticCardNum() {
		return magneticCardNum;
	}
	public void setMagneticCardNum(Integer magneticCardNum) {
		this.magneticCardNum = magneticCardNum;
	}
	public Integer getNonConnectedIcNum() {
		return nonConnectedIcNum;
	}
	public void setNonConnectedIcNum(Integer nonConnectedIcNum) {
		this.nonConnectedIcNum = nonConnectedIcNum;
	}
	public Integer getContactIcNum() {
		return contactIcNum;
	}
	public void setContactIcNum(Integer contactIcNum) {
		this.contactIcNum = contactIcNum;
	}
	public Integer getCipherKeyboardNum() {
		return cipherKeyboardNum;
	}
	public void setCipherKeyboardNum(Integer cipherKeyboardNum) {
		this.cipherKeyboardNum = cipherKeyboardNum;
	}
	
}
