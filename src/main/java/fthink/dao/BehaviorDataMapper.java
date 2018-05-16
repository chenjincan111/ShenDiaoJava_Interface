package fthink.dao;

import java.util.List;
import java.util.Map;

import fthink.entity.BehaviorData;

public interface BehaviorDataMapper extends BaseMapper {

	List<BehaviorData> selectByPrimaryKeys(Map<String, Object> map);

	BehaviorData selectByAllPrimaryKey(Map<String, Object> dataMap);

}
