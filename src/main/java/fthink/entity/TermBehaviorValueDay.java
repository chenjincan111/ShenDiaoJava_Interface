package fthink.entity;

public class TermBehaviorValueDay {

	private String deviceNo;//设备序列号
	private String analyDay;//日期
	private Double businessTimeValue;//运营时间权重值
	private Double networkValue;//网络环境权重值
	private Double tradeValue;//交易量权重值
	//private Double switchValue;//开关机次数权重值
	private Double behaviorValue;//行为值总和为以上行为值之和
	private String status;//状态（0：正常，1：告警）
	private String createTm;//创建时间
	
	private Double tradeSuccessValue;//交易量成功率行为值
	private Double bootTimeValue;//开机时间行为值
	private Double shutTimeValue;//关机时间行为值
	private Double trafficValue;//流量行为值
	
	private String configDetail;//权重配置详情
	
	public String getConfigDetail() {
		return configDetail;
	}
	public void setConfigDetail(String configDetail) {
		this.configDetail = configDetail;
	}
	public Double getTradeSuccessValue() {
		return tradeSuccessValue;
	}
	public void setTradeSuccessValue(Double tradeSuccessValue) {
		this.tradeSuccessValue = tradeSuccessValue;
	}
	public Double getBootTimeValue() {
		return bootTimeValue;
	}
	public void setBootTimeValue(Double bootTimeValue) {
		this.bootTimeValue = bootTimeValue;
	}
	public Double getShutTimeValue() {
		return shutTimeValue;
	}
	public void setShutTimeValue(Double shutTimeValue) {
		this.shutTimeValue = shutTimeValue;
	}
	public Double getTrafficValue() {
		return trafficValue;
	}
	public void setTrafficValue(Double trafficValue) {
		this.trafficValue = trafficValue;
	}
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
	public Double getBusinessTimeValue() {
		return businessTimeValue;
	}
	public void setBusinessTimeValue(Double businessTimeValue) {
		this.businessTimeValue = businessTimeValue;
	}
	public Double getNetworkValue() {
		return networkValue;
	}
	public void setNetworkValue(Double networkValue) {
		this.networkValue = networkValue;
	}
	public Double getTradeValue() {
		return tradeValue;
	}
	public void setTradeValue(Double tradeValue) {
		this.tradeValue = tradeValue;
	}
	public Double getBehaviorValue() {
		return behaviorValue;
	}
	public void setBehaviorValue(Double behaviorValue) {
		this.behaviorValue = behaviorValue;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreateTm() {
		return createTm;
	}
	public void setCreateTm(String createTm) {
		this.createTm = createTm;
	}
	
}
