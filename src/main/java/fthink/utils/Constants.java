package fthink.utils;

public class Constants {
	
	public static final String NETWORK_CONDITIONS_NOCHANGE = "noChange"; //无变化
	public static final String NETWORK_CONDITIONS_NETWORKCHANGE = "networkChange"; //网络类型变化
	public static final String NETWORK_CONDITIONS_WIFINAMECHANGE = "wifiNameChange"; //wifi名称变化
	
	/**
	 * 行为数据告警变化值
	 */
	public static final int BEHAVIOR_ALARM_VALUE = 30;
	
	public static final String BEHAVIOR_VALUE_STATUS0 = "0";//行为数据状态正常
	public static final String BEHAVIOR_VALUE_STATUS1 = "1";//行为数据状态轻微告警
	public static final String BEHAVIOR_VALUE_STATUS2 = "2";//行为数据状态中度告警
	public static final String BEHAVIOR_VALUE_STATUS3 = "3";//行为数据状态严重告警
	
	public static final String BEHAVIOR_VALUE_TYPE11 = "11";//硬件模块调用次数
	public static final String BEHAVIOR_VALUE_TYPE12 = "12";//硬件模块调用成功次数
	
	
}
