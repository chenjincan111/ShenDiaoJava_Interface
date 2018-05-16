package fthink.dao;

import java.util.List;

import fthink.entity.DataSwitchTimesInfo;



public interface DataSwitchTimesMapper extends BaseMapper {
	
	DataSwitchTimesInfo selectByPrimaryKey(DataSwitchTimesInfo dataSwitchTimesInfo);

	//获取5天记录
	List<DataSwitchTimesInfo> selectFiveData(String device_no);	
}