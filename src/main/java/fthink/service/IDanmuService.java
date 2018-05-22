package fthink.service;

import java.util.List;

import fthink.entity.Danmu;
import fthink.utils.Page;

public interface IDanmuService {

	public List<Danmu> list(Danmu danmu,Page page) throws Exception; 
	
	public Danmu selectByPrimaryKey(String id) throws Exception;
	
	public int insert(Danmu danmu) throws Exception;

	public List<Danmu> getAllDanmu(Danmu danmu,Page page) throws Exception;
	
}
