package fthink.dao;

import java.util.List;


import java.util.Map;

import fthink.entity.TermPositionFlow;


public interface TermPositionFlowMapper extends BaseMapper{
	

	List<TermPositionFlow> selectByDeviceNo(Map<String, Object> map);

	List<TermPositionFlow> selectAllTermNo(Map<String,Object> map);

	List<TermPositionFlow> selectByPrimaryKeys(Map<String, Object> map);
	
	List<TermPositionFlow> listDeviceNo();
}
