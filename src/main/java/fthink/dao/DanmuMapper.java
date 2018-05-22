package fthink.dao;

import java.util.List;
import java.util.Map;

import fthink.entity.Danmu;

public interface DanmuMapper {
    int insert(Danmu record);

    int insertSelective(Danmu record);

	List<Danmu> list(Map<String, Object> params);

	List<Danmu> getAllDanmu(Map<String, Object> params);
}