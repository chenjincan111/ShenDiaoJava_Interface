package fthink.entity;


/**
 * @author chen-jc
 *
 */
public class MonitorConfig {

	private String corpNo;//机构编号
	private String trafficConfig;//流量监控配置，json格式保存
	private String shiftConfig;//移机监控配置，json格式保存
	private String stateConfig;//状态监控配置，json格式保存
	private String authConfig;//认证监控配置，json格式保存
	private String behaviorConfig;//行为监控配置，json格式保存
	private String createTm;//创建时间
	private String creator;//创建人
	private String lastModifyTm;//最后修改时间
	
	private String corpName;
	
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
	public String getTrafficConfig() {
		return trafficConfig;
	}
	public void setTrafficConfig(String trafficConfig) {
		this.trafficConfig = trafficConfig;
	}
	public String getShiftConfig() {
		return shiftConfig;
	}
	public void setShiftConfig(String shiftConfig) {
		this.shiftConfig = shiftConfig;
	}
	public String getStateConfig() {
		return stateConfig;
	}
	public void setStateConfig(String stateConfig) {
		this.stateConfig = stateConfig;
	}
	public String getAuthConfig() {
		return authConfig;
	}
	public void setAuthConfig(String authConfig) {
		this.authConfig = authConfig;
	}
	public String getBehaviorConfig() {
		return behaviorConfig;
	}
	public void setBehaviorConfig(String behaviorConfig) {
		this.behaviorConfig = behaviorConfig;
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
