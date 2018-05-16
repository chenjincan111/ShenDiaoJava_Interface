package fthink.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigProperties {
	
	static Properties props = new Properties();

	 static final int MAX_IMPORT_TERM= 1000; 	//最大数字
	 
	/**
	 * 获取配置文件key值
	 * @param key
	 * @return
	 */
	public static String getProperty(String key) {
		return props.getProperty(key);
	}
	
	public static double[] getBehaviorLevel1() {
		String behaviorLevel1 = props.getProperty("behavior.level1");
		String[] value = behaviorLevel1.split("~");
		double[] retValue = new double[2];
		retValue[0] = Double.parseDouble(value[0]);
		retValue[1] = Double.parseDouble(value[1]);
		return retValue;
	}
	/**
	 * 属于轻微告警
	 * @return
	 */
	public static double[] getBehaviorLevel2() {
		String behaviorLevel2 = props.getProperty("behavior.level2");
		String[] value = behaviorLevel2.split("~");
		double[] retValue = new double[2];
		retValue[0] = Double.parseDouble(value[0]);
		retValue[1] = Double.parseDouble(value[1]);
		return retValue;
	}
	/**
	 * 属于中等告警
	 * @return
	 */
	public static double[] getBehaviorLevel3() {
		String behaviorLevel1 = props.getProperty("behavior.level3");
		String[] value = behaviorLevel1.split("~");
		double[] retValue = new double[2];
		retValue[0] = Double.parseDouble(value[0]);
		retValue[1] = Double.parseDouble(value[1]);
		return retValue;
	}
	/**
	 * 属于严重告警
	 * @return
	 */
	public static double getBehaviorLevel4() {
		String behaviorLevel1 = props.getProperty("behavior.level4");
		double retValue = Double.parseDouble(behaviorLevel1);
		return retValue;
	}
	
	
	// 加载配置文件
	private void init() throws IOException {
		InputStream inStream = this.getClass().getClassLoader().getResourceAsStream("config.properties");
		props.load(inStream);
		inStream.close();
	}
}
