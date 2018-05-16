package fthink.entity;

/**
 * 开关机次数表
 * @author zhu-jq
 *
 */
public class DataSwitchTimesInfo {

	private String device_no;
	private String analy_date;
	private int switch_times;
	private String last_modify_time;
	
	
	public String getAnaly_date() {
		return analy_date;
	}
	public void setAnaly_date(String analy_date) {
		this.analy_date = analy_date;
	}
	public String getDevice_no() {
		return device_no;
	}
	public void setDevice_no(String device_no) {
		this.device_no = device_no;
	}
	public int getSwitch_times() {
		return switch_times;
	}
	public void setSwitch_times(int switch_times) {
		this.switch_times = switch_times;
	}
	public String getLast_modify_time() {
		return last_modify_time;
	}
	public void setLast_modify_time(String last_modify_time) {
		this.last_modify_time = last_modify_time;
	}
	
}
