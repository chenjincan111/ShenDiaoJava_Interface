package fthink.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.NameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import fthink.entity.Danmu;
import fthink.service.IDanmuService;
import fthink.utils.HttpUtil;
import fthink.utils.HttpUtils;
import fthink.utils.SysUtil;

@Controller
@RequestMapping("danmu")
public class DanmuController {

	private Map<String,Object> jsonMap = new HashMap<String,Object>();
	
	@Autowired
	private IDanmuService danmuService;
	
	@RequestMapping("/insert")
	@ResponseBody
	public Map<String,Object> insert(Danmu danmu) throws Exception{
		danmu.setId(SysUtil.uuid());
		danmuService.insert(danmu);
		jsonMap.put("success", true);
		jsonMap.put("message", "操作成功");
		
		return jsonMap;
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public Map<String,Object> list(Danmu danmu) throws Exception{
		List<Danmu> list = danmuService.list(danmu, null);
		
		jsonMap.put("message", "获取数据成功");
		jsonMap.put("success", true);
		jsonMap.put("danmus", list);
		
		return jsonMap;
	}
	
	@RequestMapping("/getAllDanmu")
	@ResponseBody
	public Map<String,Object> getAllDanmu(Danmu danmu) throws Exception{
		List<Danmu> list = danmuService.getAllDanmu(danmu, null);
		
		jsonMap.put("message", "获取数据成功");
		jsonMap.put("success", true);
		jsonMap.put("danmus", list);
		
		return jsonMap;
	}
	
	public static void main(String[] args) throws Exception {
		String url = "https://api.weixin.qq.com/sns/jscode2session?appid=wxd2a113aae5fcf61a&secret"
				+ "=82eb199a5cbf97d71c08cc9cc8e129cf&js_code=081pYYJb21pmUO0WqoJb29f4Kb2pYYJx&grant_type=authorization_code";
		String data = HttpUtils.getHttpContent(url);
		System.out.println(data);
		Map returnMap = new Gson().fromJson(data, Map.class);
		System.out.println(null == returnMap.get("errcode"));
		System.out.println(returnMap.get("errmsg"));
		double a = (double) returnMap.get("errcode");
		System.out.println(a==40029);
	}
}
