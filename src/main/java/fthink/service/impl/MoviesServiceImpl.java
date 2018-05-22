package fthink.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fthink.dao.MovieMapper;
import fthink.entity.Movie;
import fthink.service.IMovieService;
import fthink.utils.BeanUtil;
import fthink.utils.Page;

@Service("movieService")
public class MoviesServiceImpl implements IMovieService {

	@Autowired
	private MovieMapper movieMapper;
	
	@Override
	public List<Movie> list(Movie movie, Page page) throws Exception {
		Map<String,Object> params = BeanUtil.bean2Map(movie);
		params.put("page", page);
		List<Movie> list = movieMapper.list(params);
		return list;
	}

	@Override
	public Movie selectByPrimaryKey(String id) throws Exception {
		return movieMapper.selectByPrimaryKey(id);
	}

}
