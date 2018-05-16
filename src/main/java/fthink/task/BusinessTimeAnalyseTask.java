package fthink.task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import fthink.dao.DataDeviceResultMapper;
import fthink.dao.DateBusinessTimeMapper;
import fthink.entity.DataDeviceResult;
import fthink.entity.DateBusinessTime;
import fthink.utils.CommonUtils;
import fthink.utils.DateUtil;

/**
 * 运营时间统计分析 
 *
 */
public class BusinessTimeAnalyseTask {

	private Logger logger = Logger.getLogger(BusinessTimeAnalyseTask.class);
	
	@Resource
	private DateBusinessTimeMapper dateBusinessTimeMapper;
	@Resource
	private DataDeviceResultMapper dataDeviceResultMapper;
	//每次解析的信息数量
	private static int CONSTANT_SIZE = 5;
	//正常运营时间的出现次数
	private static int CONSTANT_COUNT = 2;
	
	/**
	 * 初始化
	 */
	public void init() {
		run(); 
	}

	/**
	 * 开始分析
	 */
	public void run() {
		logger.info("[BusinessTimeAnalyseTask]:开始");
		
		String endDate = CommonUtils.getDate(-1);
		String startDate = CommonUtils.getDate(-11);
		
		Map<String, Object> map  = new HashMap<String,Object>();
		map.put("endDate", endDate);
		map.put("startDate", startDate);
		
		List<DateBusinessTime> termNos = dateBusinessTimeMapper.selectAnalyDeviceNos(map);
		
		for(DateBusinessTime flow: termNos){
			int[] arr = new int[24];
			map.put("deviceNo", flow.getDeviceNo());
			
			List<DateBusinessTime> list = dateBusinessTimeMapper.selectAllMsg(map);
			if(null==list || list.isEmpty()) {
				continue;
			}
			
			List<DateBusinessTime> analyList = new ArrayList<DateBusinessTime>();
			if(list.size() >= CONSTANT_SIZE){  //如果大于5条，则取最近5条记录进行分析
				analyList.addAll(list.subList(0, 5));
			} else {
				analyList.addAll(list);
			}
			
			for(int i=0;i<analyList.size();i++){
				analyseCount(arr,analyList.get(i));
			}
		
			String time = "000000000000000000000000";
			int time_count = 0;
			if(analyList.size()>1) {
				for(int i=0;i<arr.length;i++){
					if(arr[i] >= CONSTANT_COUNT){
						time = time.substring(0, i)+"1"+time.substring(i+1, 24);
						time_count ++;
					}
				}
			} else {
				time = analyList.get(0).getBusinessTime();
				for(int i=0;i<time.length();i++){
					if(time.charAt(i) == '1'){
						time_count ++;
					}
				}
			}
			DataDeviceResult dataDeviceResult = new DataDeviceResult();
			dataDeviceResult.setDeviceNo(flow.getDeviceNo());
			dataDeviceResult.setBusinessTime(time);
			dataDeviceResult.setLastModifyTime(DateUtil.getCurrentTime());
			dataDeviceResult.setTimeCount(time_count);
			
			DataDeviceResult deviceResult=dataDeviceResultMapper.selectByPrimaryKey(flow.getDeviceNo());
			if(null == deviceResult){
				dataDeviceResultMapper.insertBusinessTime(dataDeviceResult);
			}else{
				dataDeviceResultMapper.updateBusinessTime(dataDeviceResult);
			}
			
		}
		logger.info("[BusinessTimeAnalyseTask]:结束");
	}

	private void analyseCount(int[] arr, DateBusinessTime businessTime) {
		String businessTimeStr = businessTime.getBusinessTime();
		for(int i=0;i<businessTimeStr.length();i++){
			if(businessTimeStr.charAt(i) == '1'){
				arr[i]++;
			}
		}
	}
	
	/**
	 * 停止分析
	 */
	public void rest() {
		logger.info("[BusinessTimeAnalyseTask]:休息，啥也不做~");
	}
}
