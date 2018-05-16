package fthink.dao;

import java.util.List;
import java.util.Map;

import fthink.entity.TermTrafficDay;


public interface TermTrafficDayMapper extends BaseMapper {
	
	public List<TermTrafficDay> getTermNos(Map<String,Object> map);
	
	public List<Map<String,Object>> selectByDeviceNo(Map<String,Object> map);
	
	public TermTrafficDay selectOneByConditions(TermTrafficDay termTrafficDay);
	
//	public List<TermTrafficDayMapper> selectByTermNo(String termNo);
}