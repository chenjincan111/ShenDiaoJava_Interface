package fthink.task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import fthink.dao.BootLineMapper;
import fthink.dao.DataDeviceResultMapper;
import fthink.dao.DataSwitchTimesMapper;
import fthink.dao.TermMapper;
import fthink.entity.BootLine;
import fthink.entity.DataDeviceResult;
import fthink.entity.DataSwitchTimesInfo;
import fthink.utils.CommonUtils;
import fthink.utils.DateUtil;

/**
 * 开关机分析
 * @author zhu-jq
 *
 */
public class BootLineAnalyseTask {

	private Logger logger = Logger.getLogger(BootLineAnalyseTask.class);

	@Resource
	private TermMapper termMapper;
	
	@Resource
	private BootLineMapper bootLineMapper;
	
	@Resource
	private DataSwitchTimesMapper dataSwitchTimesMapper;
	
	@Resource
	private DataDeviceResultMapper resultMapper;
	
	
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
		
		logger.info("[BootLineAnalyseTask]:开始");
		//步骤1：获取终端信息
		Map<String, Object> param=new HashMap<>();
		param.put("maxDate", DateUtil.getCurrentDate());
		param.put("minDate", CommonUtils.getDate(-11));
		List<BootLine> bootLines=bootLineMapper.listDeviceNo(param);
		//步骤2：根据终端序列号获取开关机信息
		if(bootLines!=null && bootLines.size()>0){
			Map<String, Object> map=new HashMap<>();
			map.put("nowDate", CommonUtils.getDate(-1));
			for (BootLine bootLine : bootLines) {
				//步骤3：统计次数，将数据插入到终端开机次数统计表
				map.put("device_no", bootLine.getDevice_no());
				List<BootLine> list= bootLineMapper.listBootLine(map);
				if(list!=null && list.size()>0){
					//步骤4：获取记录,选择最近的5条记录进行解析，如果大于5条则选择最近5条，如果小于5条则有多少条就分析多少条
					analyseDataSwitch(bootLine.getDevice_no(), list.size());
				}
			}
		}
	}
	private void analyseDataSwitch(String deviceNo,int size){
		List<DataSwitchTimesInfo> dataSwitchTimesInfos=dataSwitchTimesMapper.selectFiveData(deviceNo);
		if(dataSwitchTimesInfos!=null && !dataSwitchTimesInfos.isEmpty()){
			int resultNum = 0;  //结果
			//步骤1：根据设备序列号从设备分析结果表获取记录
			DataDeviceResult deviceResult=resultMapper.selectByPrimaryKey(deviceNo);
			//步骤2：判断开关机次数的最大值和最小值差值（若大于基准数据则不进行平均数计算，否则重新计算平均数）
			int comNum=dataSwitchTimesInfos.get(0).getSwitch_times()-dataSwitchTimesInfos.get(dataSwitchTimesInfos.size()-1).getSwitch_times();
			if(comNum<10){
				//最大值和最小值差值小于10，需要重新计算平均数
				int aveNum=getAvgNum(dataSwitchTimesInfos);
				if(deviceResult!=null){
					//已有数据更新设备分析结果表
					deviceResult.setDeviceNo(deviceNo);
					deviceResult.setSwitchTimes(aveNum);
					deviceResult.setLastModifyTime(DateUtil.getCurrentTime());
					resultMapper.updateByPrimaryKey(deviceResult);
				}else{
					//若无记录，需要新增一条
					DataDeviceResult dataDeviceResult=new DataDeviceResult();
					dataDeviceResult.setDeviceNo(deviceNo);
					dataDeviceResult.setSwitchTimes(aveNum);
					dataDeviceResult.setLastModifyTime(DateUtil.getCurrentTime());
					resultMapper.insert(dataDeviceResult);
				}
				resultNum=size-aveNum;
			}else{
				//最大值和最小值差值大于等于10
				if(deviceResult!=null){
					resultNum=size-deviceResult.getSwitchTimes();
				}
			}
			resultNum=Math.abs(resultNum);
			//步骤3：返回结果
			if(resultNum>=1 && resultNum<3){
				logger.info("设备"+deviceNo+"开关机结果为："+25);
			}else if(resultNum>=3 && resultNum<5){
				logger.info("设备"+deviceNo+"开关机结果为："+20);
			}else if(resultNum>=5 && resultNum<10){
				logger.info("设备"+deviceNo+"开关机结果为："+10);
			}else if(resultNum>=10){
				logger.info("设备"+deviceNo+"开关机结果为："+0);
			}
		}
	}
	
	private int getAvgNum(List<DataSwitchTimesInfo> dataSwitchTimesInfos){
		int countNum=0;
		int aveNum=0;
		for (DataSwitchTimesInfo dataSwitchTimesInfo : dataSwitchTimesInfos) {
			countNum=countNum+dataSwitchTimesInfo.getSwitch_times();
		}
		aveNum=countNum/dataSwitchTimesInfos.size();
		return aveNum;
	}
}
