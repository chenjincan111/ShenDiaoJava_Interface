package fthink.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PushProperties {

	static Properties props = new Properties();

	public static String getCmdpushUrl() {
		return props.getProperty("CMD_PUSH_URL");
	}
	
	public static String getBatchpushUrl() {
		return props.getProperty("BATCH_PUSH_URL");
	}
	
	public static String getTagpushUrl() {
		return props.getProperty("TAG_PUSH_URL");
	}
	
	public static String getAllpushUrl() {
		return props.getProperty("ALL_PUSH_URL");
	}
	
	public static String getSecretKey() {
		return props.getProperty("SECRET_KEY");
	}
	
	public static String getAccessId() {
		return props.getProperty("ACCESS_ID");
	}
	
	public static String getAppId() {
		return props.getProperty("APP_ID");
	}
	

	// 加载配置文件
	private void init() throws IOException {
		InputStream inStream = this.getClass().getClassLoader()
				.getResourceAsStream("push.properties");
		props.load(inStream);
		inStream.close();
	}

}
