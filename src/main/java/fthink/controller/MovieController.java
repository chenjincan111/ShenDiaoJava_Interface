package fthink.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("movie")
public class MovieController {

private Map<String,Object> jsonMap = new HashMap<String,Object>();
	
	@RequestMapping("/getAllMovies")
	@ResponseBody
	public Map<String,Object> getAllMovies() throws Exception {
		
		List list = new ArrayList<>();
		
		Map dateMap = new HashMap<String,Object>();
		
		dateMap.put("id", "1");
		dateMap.put("title", "霸王别姬");
		dateMap.put("date", "20180516224530");
		dateMap.put("type", "movie");
		dateMap.put("rating", 9.6);
		dateMap.put("imgUrl", "");
		dateMap.put("author", "陈凯歌");
		dateMap.put("genres", "剧情");
		dateMap.put("vedioUrl", "");
		dateMap.put("summary", "段小楼（张丰毅）与程蝶衣（张国荣）是一对打小一起长大的师兄弟，两人一个演生，一个饰旦，一向配合天衣无缝，尤其一出《霸王别姬》，更是誉满京城，为此，两人约定合演一辈子《霸王别姬》。但两人对戏剧与人生关系的理解有本质不同，段小楼深知戏非人生，程蝶衣则是人戏不分。" + 
				"段小楼在认为该成家立业之时迎娶了名妓菊仙（巩俐），致使程蝶衣认定菊仙是可耻的第三者，使段小楼做了叛徒，自此，三人围绕一出《霸王别姬》生出的爱恨情仇战开始随着时代风云的变迁不断升级，终酿成悲剧。");
		list.add(dateMap);
		
		jsonMap.put("message", "获取数据成功");
		jsonMap.put("code", "0000");
		jsonMap.put("movies", list);
		return jsonMap;
	}
	
}
