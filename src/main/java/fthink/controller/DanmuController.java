package fthink.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import fthink.entity.Danmu;
import fthink.service.IDanmuService;
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
}
