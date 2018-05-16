package fthink.entity;

/**
 * 终端位置流水表
 * @author zhu-jq
 *
 */
public class TermPositionFlow {
    private String uuid;

    private String device_no;                 //设备序列号           
 
    private String baseStation;               //基站信息
 
    private String longitude;                 //经度

    private String latitude;                  //纬度

    private String province;                  //所在省

    private String city;                      //所在市

    private String county;                    //所在区县

    private String createTm;                  //创建时间
    
    private String network_name;              //网络名称
    
    private String network_type;              //网络类型

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getDevice_no() {
		return device_no;
	}

	public void setDevice_no(String device_no) {
		this.device_no = device_no;
	}

	public String getNetwork_name() {
		return network_name;
	}

	public void setNetwork_name(String network_name) {
		this.network_name = network_name;
	}

	public String getNetwork_type() {
		return network_type;
	}

	public void setNetwork_type(String network_type) {
		this.network_type = network_type;
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

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county == null ? null : county.trim();
    }

    public String getCreateTm() {
        return createTm;
    }

    public void setCreateTm(String createTm) {
        this.createTm = createTm == null ? null : createTm.trim();
    }
}