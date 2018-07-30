package fthink.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import fthink.entity.Movie;
import fthink.service.IMovieService;
import fthink.utils.DateUtil;
import fthink.utils.SysUtil;

@Controller
@RequestMapping("movie")
public class MovieController {

	private Map<String,Object> jsonMap = new HashMap<String,Object>();

	@Autowired
	private IMovieService movieService;

	@RequestMapping("/getAllMovies")
	@ResponseBody
	public Map<String,Object> getAllMovies(Movie movie) throws Exception {
		
		List<Movie> mlist = movieService.list(movie, null);
		
		jsonMap.put("message", "获取数据成功");
		jsonMap.put("success", true);
		jsonMap.put("title", "视频排行");
		jsonMap.put("movies", mlist);
		
		return jsonMap;
	}
	
	@RequestMapping("/getMovieDetail")
	@ResponseBody
	public Map<String,Object> getMovieDetail(String id) throws Exception{
		Movie movie = movieService.selectByPrimaryKey(id);
		jsonMap = new HashMap<String,Object>();
		jsonMap.put("message", "获取数据成功");
		jsonMap.put("success", true);
		jsonMap.put("movie", movie);
		return jsonMap;
	}
	
	@RequestMapping("/insert")
	@ResponseBody
	public Map<String,Object> insert(Movie movie) throws Exception{
		movie.setId(SysUtil.uuid());
		movie.setDate(DateUtil.getCurrentTime("yyyy-MM-dd HH:mm:ss"));
		movie.setRating(0.0);
		movieService.insert(movie);
		jsonMap.put("success", true);
		jsonMap.put("message", "操作成功");
		
		return jsonMap;
	}
	
	@RequestMapping("/getMyMovies")
	@ResponseBody
	public Map<String,Object> getMyMovies(Movie movie) throws Exception {
		
		List<Movie> mlist = movieService.list(movie, null);
		
		jsonMap.put("message", "获取数据成功");
		jsonMap.put("success", true);
		jsonMap.put("title", "我的视频");
		jsonMap.put("movies", mlist);
		
		return jsonMap;
	}
	
}
