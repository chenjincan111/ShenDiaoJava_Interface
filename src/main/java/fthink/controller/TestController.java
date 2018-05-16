package fthink.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

	private Map<String,Object> jsonMap = new HashMap<String,Object>();
	
	@RequestMapping("/test")
	@ResponseBody
	public Map<String,Object> test() throws Exception {
		
		jsonMap.put("message", "连接神雕爪蛙接口成功");
		jsonMap.put("code", "0000");
		
		return jsonMap;
	}
	
}
