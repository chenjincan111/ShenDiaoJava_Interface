package fthink.dao;

import java.util.List;
import java.util.Map;

import fthink.entity.BootLine;


public interface BootLineMapper extends BaseMapper {

	List<BootLine> listBootLine(Map<String, Object> map);
	
	List<BootLine> listDeviceNo(Map<String, Object> map);

	List<BootLine> listFirstBootTime(Map<String, Object> map);
}