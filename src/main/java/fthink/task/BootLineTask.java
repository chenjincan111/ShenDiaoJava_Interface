package fthink.task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import fthink.dao.BootLineMapper;
import fthink.dao.DataSwitchTimesMapper;
import fthink.dao.TermMapper;
import fthink.entity.BootLine;
import fthink.entity.DataSwitchTimesInfo;
import fthink.utils.CommonUtils;
import fthink.utils.DateUtil;

public class BootLineTask {
	private Logger logger = Logger.getLogger(BootLineAnalyseTask.class);
	
	@Resource
	private TermMapper termMapper;
	@Resource
	private BootLineMapper bootLineMapper;
	@Resource
	private DataSwitchTimesMapper dataSwitchTimesMapper;
	
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
		logger.info("[BootLineTask]:开始");
		//步骤1：获取终端信息
		Map<String, Object> param=new HashMap<>();
		param.put("maxDate", DateUtil.getCurrentDate());
		param.put("minDate", CommonUtils.getDate(-5));
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
					//保存开关机记录
					insertDataSwitch(bootLine.getDevice_no(),list.size());
				}
			}
		}
		logger.info("[BootLineTask]:结束");
	}

	/**
	 * 停止分析
	 */
	public void rest() {
		logger.info("[BootLineTask]:休息，啥也不做~");
	}
	
	//==========================================================
	
	private void insertDataSwitch(String deviceNo, int size) {
		DataSwitchTimesInfo switchTimesInfo=new DataSwitchTimesInfo();
		switchTimesInfo.setAnaly_date(CommonUtils.getDate(-1));
		switchTimesInfo.setDevice_no(deviceNo);
		switchTimesInfo.setLast_modify_time(DateUtil.getCurrentTime());
		switchTimesInfo.setSwitch_times(size);
		
		DataSwitchTimesInfo dbDataSwitchTimesInfo = dataSwitchTimesMapper.selectByPrimaryKey(switchTimesInfo);
		if(null==dbDataSwitchTimesInfo) {
			dataSwitchTimesMapper.insert(switchTimesInfo);
		} else {
			dataSwitchTimesMapper.updateByPrimaryKey(switchTimesInfo);
		}
	}
	
}
