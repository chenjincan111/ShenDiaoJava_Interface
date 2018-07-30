package fthink.service;

import java.util.List;

import fthink.entity.Movie;
import fthink.utils.Page;

public interface IMovieService {

	public List<Movie> list(Movie movie,Page page) throws Exception; 
	
	public Movie selectByPrimaryKey(String id) throws Exception;

	public void insert(Movie movie);
	
}
