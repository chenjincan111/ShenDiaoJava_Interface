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
		
		jsonMap.put("message", "获取数据成功");
		jsonMap.put("success", true);
		jsonMap.put("movie", movie);
		return jsonMap;
	}
	
}
