package fthink.entity;

public class BehaviorValueConfig{

	private String corpNo;//机构编号
	private String businessTimeWeight;//运营时间权重，用json格式保存
	private String networkWeight;//网络环境权重，用json格式保存
	private String tradeWeight;//交易量权重，用json格式保存
	private String switchWeigth;//开关机次数权重，用json格式保存
	private String createTm;//创建时间
	private String creator;//创建人
	private String lastModifyTm;//最后修改时间
	
	private String tradeSuccessWeight;//交易量成功率比重
	private String trafficWeight;//流量比重
	private String bootTimeWeigth;//开机时间比重
	private String shutdownTimeWeight;//关机时间比重
	
	private String corpName;
	
	public String getTradeSuccessWeight() {
		return tradeSuccessWeight;
	}
	public void setTradeSuccessWeight(String tradeSuccessWeight) {
		this.tradeSuccessWeight = tradeSuccessWeight;
	}
	public String getTrafficWeight() {
		return trafficWeight;
	}
	public void setTrafficWeight(String trafficWeight) {
		this.trafficWeight = trafficWeight;
	}
	public String getBootTimeWeigth() {
		return bootTimeWeigth;
	}
	public void setBootTimeWeigth(String bootTimeWeigth) {
		this.bootTimeWeigth = bootTimeWeigth;
	}
	public String getShutdownTimeWeight() {
		return shutdownTimeWeight;
	}
	public void setShutdownTimeWeight(String shutdownTimeWeight) {
		this.shutdownTimeWeight = shutdownTimeWeight;
	}
	public String getCorpName() {
		return corpName;
	}
	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}
	public String getCorpNo() {
		return corpNo;
	}
	public void setCorpNo(String corpNo) {
		this.corpNo = corpNo;
	}
	public String getBusinessTimeWeight() {
		return businessTimeWeight;
	}
	public void setBusinessTimeWeight(String businessTimeWeight) {
		this.businessTimeWeight = businessTimeWeight;
	}
	public String getNetworkWeight() {
		return networkWeight;
	}
	public void setNetworkWeight(String networkWeight) {
		this.networkWeight = networkWeight;
	}
	public String getTradeWeight() {
		return tradeWeight;
	}
	public void setTradeWeight(String tradeWeight) {
		this.tradeWeight = tradeWeight;
	}
	public String getSwitchWeigth() {
		return switchWeigth;
	}
	public void setSwitchWeigth(String switchWeigth) {
		this.switchWeigth = switchWeigth;
	}
	public String getCreateTm() {
		return createTm;
	}
	public void setCreateTm(String createTm) {
		this.createTm = createTm;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getLastModifyTm() {
		return lastModifyTm;
	}
	public void setLastModifyTm(String lastModifyTm) {
		this.lastModifyTm = lastModifyTm;
	}
	
}
