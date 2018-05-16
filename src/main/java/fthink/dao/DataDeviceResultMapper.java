package fthink.dao;

import fthink.entity.DataDeviceResult;


public interface DataDeviceResultMapper extends BaseMapper {

	int insertBusinessTime(DataDeviceResult dataDeviceResult);

	int updateBusinessTime(DataDeviceResult dataDeviceResult);

	int insertTradeCount(DataDeviceResult dataDeviceResult);

	int updateTradeCount(DataDeviceResult dataDeviceResult);

	int insertBootTime(DataDeviceResult dataDeviceResult);

	int updateBootTime(DataDeviceResult dataDeviceResult);
	
	int insertTraffic(DataDeviceResult dataDeviceResult);
	int updateTraffic(DataDeviceResult dataDeviceResult);
}