package fthink.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fthink.dao.DanmuMapper;
import fthink.entity.Danmu;
import fthink.service.IDanmuService;
import fthink.utils.BeanUtil;
import fthink.utils.Page;

@Service("danmuService")
public class DanmuServiceImpl implements IDanmuService {

	@Autowired
	private DanmuMapper danmuMapper;
	
	@Override
	public List<Danmu> list(Danmu danmu, Page page) throws Exception {
		Map<String,Object> params = BeanUtil.bean2Map(danmu);
		params.put("page", page);
		List<Danmu> list = danmuMapper.list(params);
		return list;
	}

	@Override
	public Danmu selectByPrimaryKey(String id) throws Exception {
		return null;
	}

	@Override
	public int insert(Danmu danmu) throws Exception {
		return danmuMapper.insert(danmu);
	}

	@Override
	public List<Danmu> getAllDanmu(Danmu danmu, Page page) throws Exception {
		Map<String,Object> params = BeanUtil.bean2Map(danmu);
		params.put("page", page);
		List<Danmu> list = danmuMapper.getAllDanmu(params);
		return list;
	}

}
