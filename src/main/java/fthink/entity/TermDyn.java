package fthink.entity;

public class TermDyn {
	private String deviceNo; // 终端编号
	private String latestAccessTm; // 最近通讯时间
	private String ip; // IP地址
	private String baseStation; // 基站信息
	private String longitude; // 经度
	private String latitude; // 纬度
	private String province; // 所在省
	private String city; // 所在市
	private String county;
	private String hardwareStatBmp; // 硬件状态位图
	private String errorInf; // 异常信息
	private String androidVersion; // OS版本号
	private String hwVersion; // HW版本
	private String spVersion; // SP版本
	private String romVersion; // ROM版本
	private String kernelVersion; // 内核版本
	private String networkStatus; // 网络状态
	private String networkType; // 网络类型
	private String paramVer; // 通讯参数版本
	private String createTm; // 创建时间
	private String lastModifyTm; // 最后修改日期
	private String shiftFlag; // 移机标识0-未移机，1-移机
	private String securityType;
	private String deviceModelNo;
	private String cpuId;
	private String flashId;
	private String mac;
	private String hash;
	private String identifyStatus;

	private String ssid;
	private String bssid;
	private String dns;
	private String imei;
	private String screenSize;
	private String screenResolution;
	private String memorySize;
	private String batteryRate;
	private String romRate;
	private String romSize;

	private String shiftLongitude;
	private String shiftLatitude;
	
	private String cardReaderPerLevel;
	private String appNetPerLevel;
	private String cipKeyboardPerLevel;
	private String cerPerLevel;
	
	
	public String getCardReaderPerLevel() {
		return cardReaderPerLevel;
	}

	public void setCardReaderPerLevel(String cardReaderPerLevel) {
		this.cardReaderPerLevel = cardReaderPerLevel;
	}

	public String getAppNetPerLevel() {
		return appNetPerLevel;
	}

	public void setAppNetPerLevel(String appNetPerLevel) {
		this.appNetPerLevel = appNetPerLevel;
	}

	public String getCipKeyboardPerLevel() {
		return cipKeyboardPerLevel;
	}

	public void setCipKeyboardPerLevel(String cipKeyboardPerLevel) {
		this.cipKeyboardPerLevel = cipKeyboardPerLevel;
	}

	public String getCerPerLevel() {
		return cerPerLevel;
	}

	public void setCerPerLevel(String cerPerLevel) {
		this.cerPerLevel = cerPerLevel;
	}

	public String getShiftLongitude() {
		return shiftLongitude;
	}

	public void setShiftLongitude(String shiftLongitude) {
		this.shiftLongitude = shiftLongitude;
	}

	public String getShiftLatitude() {
		return shiftLatitude;
	}

	public void setShiftLatitude(String shiftLatitude) {
		this.shiftLatitude = shiftLatitude;
	}

	public TermDyn() {
		super();
	}

	public TermDyn(String deviceNo, String latestAccessTm, String ip,
			String androidVersion, String hwVersion, String spVersion,
			String romVersion, String kernelVersion, String deviceModelNo,
			String cpuId, String flashId, String mac, String hash, String ssid,
			String bssid, String dns, String imei, String screenSize,
			String screenResolution, String memorySize, String romSize) {
		this.deviceNo = deviceNo;
		this.latestAccessTm = latestAccessTm;
		this.ip = ip;
		this.androidVersion = androidVersion;
		this.hwVersion = hwVersion;
		this.spVersion = spVersion;
		this.romVersion = romVersion;
		this.kernelVersion = kernelVersion;
		this.deviceModelNo = deviceModelNo;
		this.cpuId = cpuId;
		this.flashId = flashId;
		this.mac = mac;
		this.hash = hash;
		this.ssid = ssid;
		this.bssid = bssid;
		this.dns = dns;
		this.imei = imei;
		this.screenSize = screenSize;
		this.screenResolution = screenResolution;
		this.memorySize = memorySize;
		this.romSize = romSize;
	}

	public String getRomSize() {
		return romSize;
	}

	public void setRomSize(String romSize) {
		this.romSize = romSize;
	}

	public String getBatteryRate() {
		return batteryRate;
	}

	public void setBatteryRate(String batteryRate) {
		this.batteryRate = batteryRate;
	}

	public String getRomRate() {
		return romRate;
	}

	public void setRomRate(String romRate) {
		this.romRate = romRate;
	}

	public String getMemorySize() {
		return memorySize;
	}

	public void setMemorySize(String memorySize) {
		this.memorySize = memorySize;
	}

	public String getDeviceNo() {
		return deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getAndroidVersion() {
		return androidVersion;
	}

	public void setAndroidVersion(String androidVersion) {
		this.androidVersion = androidVersion;
	}

	public String getHwVersion() {
		return hwVersion;
	}

	public void setHwVersion(String hwVersion) {
		this.hwVersion = hwVersion;
	}

	public String getSpVersion() {
		return spVersion;
	}

	public void setSpVersion(String spVersion) {
		this.spVersion = spVersion;
	}

	public String getRomVersion() {
		return romVersion;
	}

	public void setRomVersion(String romVersion) {
		this.romVersion = romVersion;
	}

	public String getKernelVersion() {
		return kernelVersion;
	}

	public void setKernelVersion(String kernelVersion) {
		this.kernelVersion = kernelVersion;
	}

	public String getSecurityType() {
		return securityType;
	}

	public void setSecurityType(String securityType) {
		this.securityType = securityType;
	}

	public String getDeviceModelNo() {
		return deviceModelNo;
	}

	public void setDeviceModelNo(String deviceModelNo) {
		this.deviceModelNo = deviceModelNo;
	}

	public String getSsid() {
		return ssid;
	}

	public void setSsid(String ssid) {
		this.ssid = ssid;
	}

	public String getBssid() {
		return bssid;
	}

	public void setBssid(String bssid) {
		this.bssid = bssid;
	}

	public String getDns() {
		return dns;
	}

	public void setDns(String dns) {
		this.dns = dns;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getScreenSize() {
		return screenSize;
	}

	public void setScreenSize(String screenSize) {
		this.screenSize = screenSize;
	}

	public String getScreenResolution() {
		return screenResolution;
	}

	public void setScreenResolution(String screenResolution) {
		this.screenResolution = screenResolution;
	}

	public String getCpuId() {
		return cpuId;
	}

	public void setCpuId(String cpuId) {
		this.cpuId = cpuId;
	}

	public String getFlashId() {
		return flashId;
	}

	public void setFlashId(String flashId) {
		this.flashId = flashId;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getIdentifyStatus() {
		return identifyStatus;
	}

	public void setIdentifyStatus(String identifyStatus) {
		this.identifyStatus = identifyStatus;
	}

	public String getShiftFlag() {
		return shiftFlag;
	}

	public void setShiftFlag(String shiftFlag) {
		this.shiftFlag = shiftFlag;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getParamVer() {
		return paramVer;
	}

	public void setParamVer(String paramVer) {
		this.paramVer = paramVer;
	}

	public String getErrorInf() {
		return errorInf;
	}

	public void setErrorInf(String errorInf) {
		this.errorInf = errorInf;
	}

	public String getNetworkStatus() {
		return networkStatus;
	}

	public void setNetworkStatus(String networkStatus) {
		this.networkStatus = networkStatus;
	}

	public String getNetworkType() {
		return networkType;
	}

	public void setNetworkType(String networkType) {
		this.networkType = networkType;
	}

	public String getLatestAccessTm() {
		return latestAccessTm;
	}

	public void setLatestAccessTm(String latestAccessTm) {
		this.latestAccessTm = latestAccessTm == null ? null : latestAccessTm
				.trim();
	}

	public String getBaseStation() {
		return baseStation;
	}

	public void setBaseStation(String baseStation) {
		this.baseStation = baseStation == null ? null : baseStation.trim();
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude == null ? null : longitude.trim();
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude == null ? null : latitude.trim();
	}

	public String getHardwareStatBmp() {
		return hardwareStatBmp;
	}

	public void setHardwareStatBmp(String hardwareStatBmp) {
		this.hardwareStatBmp = hardwareStatBmp == null ? null : hardwareStatBmp
				.trim();
	}

	public String getCreateTm() {
		return createTm;
	}

	public void setCreateTm(String createTm) {
		this.createTm = createTm == null ? null : createTm.trim();
	}

	public String getLastModifyTm() {
		return lastModifyTm;
	}

	public void setLastModifyTm(String lastModifyTm) {
		this.lastModifyTm = lastModifyTm == null ? null : lastModifyTm.trim();
	}
}