package fthink.dto;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class CmdPushRequest  {
	
	private String access_id;
	
	private String device_alias;
	
	private String app_id;
	
	private String message;
	
	private int expire_time;
	
	private String timestamp;
	private String sign;
	
	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}


	
	
	public String getAccess_id() {
		return access_id;
	}

	public void setAccess_id(String access_id) {
		this.access_id = access_id;
	}

	public String getDevice_alias() {
		return device_alias;
	}

	public void setDevice_alias(String device_alias) {
		this.device_alias = device_alias;
	}

	public String getApp_id() {
		return app_id;
	}

	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getExpire_time() {
		return expire_time;
	}

	public void setExpire_time(int expire_time) {
		this.expire_time = expire_time;
	}
	
	public Map<String, Object> toMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		Field[] fields = this.getClass().getDeclaredFields();
		for (Field field : fields) {
			Object obj;
			try {
				obj = field.get(this);
				if (obj != null) {
					if (!field.getName().equals("sign")) {
						map.put(field.getName(), obj);
					}
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return map;
	}

}
