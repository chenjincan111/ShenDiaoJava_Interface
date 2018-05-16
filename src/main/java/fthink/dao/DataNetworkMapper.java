package fthink.dao;

import java.util.List;

import fthink.entity.DataNetwork;

public interface DataNetworkMapper extends BaseMapper{

	DataNetwork selectByPrimaryKeys(DataNetwork dataNetwork);

	List<DataNetwork> selectByDeviceNo(String id);
	
	DataNetwork selectOneByConditions(DataNetwork dataNetwork);

}
