package fthink.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import fthink.dto.WxJscodeDto;
import fthink.utils.HttpUtils;

@Controller
@RequestMapping("wx")
public class WxController {

	private Map<String,Object> jsonMap = new HashMap<String,Object>();
	
	@RequestMapping("/getOpenId")
	@ResponseBody
	public Map<String,Object> getOpenId(WxJscodeDto wxDto) throws Exception {
		
		jsonMap.put("message", "获取数据成功");
		jsonMap.put("success", true);
		
		String param = "appid="+wxDto.getAppid()+"&secret="+wxDto.getSecret()+"&js_code="+wxDto.getJs_code()
						+"&grant_type="+wxDto.getGrant_type();
		String data = HttpUtils.getHttpContent("https://api.weixin.qq.com/sns/jscode2session?"+param);
		Map returnMap = new Gson().fromJson(data, Map.class);
		if(null == returnMap.get("errcode")) {
			jsonMap.put("session_key", returnMap.get("session_key"));
			jsonMap.put("openid", returnMap.get("openid"));
		}else {
			jsonMap.put("message", returnMap.get("errmsg"));
			jsonMap.put("success", false);
		}
		
		return jsonMap;
	}
	
}
