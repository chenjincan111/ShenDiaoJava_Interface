package fthink.entity;

/**
 * 终端表
 * @author zhu-jq
 *
 */
public class Term {

	private String device_no;                         //设备序列号
	private String deviceManufNo;                     //所属厂商
	private String deviceModelNo;                     //所属型号
	private String status;                            //状态
	private String corpNo;                            //所属机构
	private String linkMan;                           //联系人
	private String telphone;                          //联系电话
	private String imei;                              //IMEI号
	private String imsi;                              //IMSI号
	private String createTm;                          //创建时间
	private String lastModifyTm;                      //最后修改日期
	private String termKey;                           //终端密钥
	private String lifeStatus;                        //终端生命周期状态
	private String openTm;                            //开通时间
	private String oftenLongitude;                    //经度
	private String oftenLatitude;                     //纬度
	private String oftenProvince;                     //常在省
	private String oftenCity;                         //常在市
	private String oftenCounty;                       //常在区县
	private String public_key;                        //密钥传输公钥
	private String ele_fence_type;                    //电子围栏类型 1：代表属于电子围栏类型
	private String ele_fence_data;                    //电子围栏坐标信息
	private String open_flag;                         //激活标志（0：未激活 1：已激活）
	private String new_device_no;                     //换机设备序列号
	
	
	public String getDevice_no() {
		return device_no;
	}
	public void setDevice_no(String device_no) {
		this.device_no = device_no;
	}
	public String getDeviceManufNo() {
		return deviceManufNo;
	}
	public void setDeviceManufNo(String deviceManufNo) {
		this.deviceManufNo = deviceManufNo;
	}
	public String getDeviceModelNo() {
		return deviceModelNo;
	}
	public void setDeviceModelNo(String deviceModelNo) {
		this.deviceModelNo = deviceModelNo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCorpNo() {
		return corpNo;
	}
	public void setCorpNo(String corpNo) {
		this.corpNo = corpNo;
	}
	public String getLinkMan() {
		return linkMan;
	}
	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getImsi() {
		return imsi;
	}
	public void setImsi(String imsi) {
		this.imsi = imsi;
	}
	public String getCreateTm() {
		return createTm;
	}
	public void setCreateTm(String createTm) {
		this.createTm = createTm;
	}
	public String getLastModifyTm() {
		return lastModifyTm;
	}
	public void setLastModifyTm(String lastModifyTm) {
		this.lastModifyTm = lastModifyTm;
	}
	public String getTermKey() {
		return termKey;
	}
	public void setTermKey(String termKey) {
		this.termKey = termKey;
	}
	public String getLifeStatus() {
		return lifeStatus;
	}
	public void setLifeStatus(String lifeStatus) {
		this.lifeStatus = lifeStatus;
	}
	public String getOpenTm() {
		return openTm;
	}
	public void setOpenTm(String openTm) {
		this.openTm = openTm;
	}
	public String getOftenLongitude() {
		return oftenLongitude;
	}
	public void setOftenLongitude(String oftenLongitude) {
		this.oftenLongitude = oftenLongitude;
	}
	public String getOftenLatitude() {
		return oftenLatitude;
	}
	public void setOftenLatitude(String oftenLatitude) {
		this.oftenLatitude = oftenLatitude;
	}
	public String getOftenProvince() {
		return oftenProvince;
	}
	public void setOftenProvince(String oftenProvince) {
		this.oftenProvince = oftenProvince;
	}
	public String getOftenCity() {
		return oftenCity;
	}
	public void setOftenCity(String oftenCity) {
		this.oftenCity = oftenCity;
	}
	public String getOftenCounty() {
		return oftenCounty;
	}
	public void setOftenCounty(String oftenCounty) {
		this.oftenCounty = oftenCounty;
	}
	public String getPublic_key() {
		return public_key;
	}
	public void setPublic_key(String public_key) {
		this.public_key = public_key;
	}
	public String getEle_fence_type() {
		return ele_fence_type;
	}
	public void setEle_fence_type(String ele_fence_type) {
		this.ele_fence_type = ele_fence_type;
	}
	public String getEle_fence_data() {
		return ele_fence_data;
	}
	public void setEle_fence_data(String ele_fence_data) {
		this.ele_fence_data = ele_fence_data;
	}
	public String getOpen_flag() {
		return open_flag;
	}
	public void setOpen_flag(String open_flag) {
		this.open_flag = open_flag;
	}
	public String getNew_device_no() {
		return new_device_no;
	}
	public void setNew_device_no(String new_device_no) {
		this.new_device_no = new_device_no;
	}
	
}
