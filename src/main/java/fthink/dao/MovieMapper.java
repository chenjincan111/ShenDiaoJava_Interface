package fthink.dao;

import java.util.List;
import java.util.Map;

import fthink.entity.Movie;

public interface MovieMapper {
    int deleteByPrimaryKey(String id);

    int insert(Movie record);

    int insertSelective(Movie record);

    Movie selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Movie record);

    int updateByPrimaryKey(Movie record);

	List<Movie> list(Map<String, Object> params);
}