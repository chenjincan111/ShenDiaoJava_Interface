package fthink.dao;

import java.util.List;
import java.util.Map;

import fthink.entity.DateBusinessTime;

public interface DateBusinessTimeMapper extends BaseMapper {

	List<DateBusinessTime> selectAllMsg(Map<String,Object> map);

	DateBusinessTime selectByPrimaryKeys(DateBusinessTime dateBusinessTime);
	
	List<DateBusinessTime> selectAnalyDeviceNos(Map<String,Object> map);

}
