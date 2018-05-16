package fthink.task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import fthink.dao.DataDeviceResultMapper;
import fthink.dao.TermTrafficDayMapper;
import fthink.entity.DataDeviceResult;
import fthink.utils.CommonUtils;
import fthink.utils.DateUtil;

public class TrafficAnalyseTask {
	
	@Resource
	private TermTrafficDayMapper termTrafficDayMapper;
	@Resource
	private DataDeviceResultMapper dataDeviceResultMapper;
	
	private final static int CONSTANT_SIZE = 5;
	
	public void init() {
		
		String endDate  = CommonUtils.getDate(-1);
		String startDate = CommonUtils.getDate(-11);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		List<Map<String,Object>> list = termTrafficDayMapper.selectByDeviceNo(map);
		
		if(null!=list && !list.isEmpty()) {
			for(Map<String,Object> m : list) {
				String deviceNo = String.valueOf(m.get("deviceNo"));
				long traffic = ((Double)m.get("traffic")).longValue();
				int num = ((Long)m.get("num")).intValue();
				
				long aveTraffic = traffic/num;
				
				DataDeviceResult dataDeviceResult = new DataDeviceResult();
				dataDeviceResult.setDeviceNo(deviceNo);
				dataDeviceResult.setTraffic(aveTraffic);
				dataDeviceResult.setLastModifyTime(DateUtil.getCurrentTime());
				
				DataDeviceResult deviceResult=dataDeviceResultMapper.selectByPrimaryKey(deviceNo);
				if(null == deviceResult){
					dataDeviceResultMapper.insertTraffic(dataDeviceResult);
				}else{
					dataDeviceResultMapper.updateTraffic(dataDeviceResult);
				}
			}
		}
		
	}

}
