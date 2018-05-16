package fthink.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import fthink.dto.CommonResponse;

public class CmdPushResponse extends CommonResponse {
	
	private String result;
	
	private String sign;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

}
