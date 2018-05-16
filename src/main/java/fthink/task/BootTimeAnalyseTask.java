package fthink.task;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import fthink.dao.BootLineMapper;
import fthink.dao.DataDeviceResultMapper;
import fthink.dao.TermPositionFlowMapper;
import fthink.entity.BootLine;
import fthink.entity.DataDeviceResult;
import fthink.utils.DateUtil;

/**
 * 开关机时间分析 
 *
 */
public class BootTimeAnalyseTask {

	private Logger logger = Logger.getLogger(BootTimeAnalyseTask.class);
	
	@Resource
	private TermPositionFlowMapper termPositionFlowMapper;
	@Resource
	private BootLineMapper bootLineMapper;
	@Resource
	private DataDeviceResultMapper dataDeviceResultMapper;
	
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
		logger.info("[BootTimeAnalyseTask]:开始");
		
		Map<String, Object> map=new HashMap<>();
		map.put("maxDate", DateUtil.getCurrentDate());
		map.put("minDate", getBeforeDay(-10));
		List<BootLine> bootLines=bootLineMapper.listDeviceNo(map);
		
		if(bootLines!=null && bootLines.size()>0){
			
			for (BootLine bootLine : bootLines) {
				//步骤3：统计次数，将数据插入到终端开机次数统计表
				map.put("device_no", bootLine.getDevice_no());
				List<BootLine> list= bootLineMapper.listFirstBootTime(map);
				if(list!=null && list.size()>0){
					int bootTotal = 0;
					int shutTotal = 0;
					for(BootLine boot:list){
						//分析精确度分钟
						bootTotal += Integer.parseInt(boot.getLastOnTm().substring(8, 10))*60+Integer.parseInt(boot.getLastOnTm().substring(10, 12));
						shutTotal += Integer.parseInt(boot.getLastOffTm().substring(8, 10))*60+Integer.parseInt(boot.getLastOffTm().substring(10, 12));
					}
					DataDeviceResult dataDeviceResult = new DataDeviceResult();
					dataDeviceResult.setDeviceNo(bootLine.getDevice_no());
					dataDeviceResult.setLastModifyTime(DateUtil.getCurrentTime());
					int bootavg = bootTotal/list.size();
					int shutavg = shutTotal/list.size();
					dataDeviceResult.setBootTime(bootavg);
					dataDeviceResult.setOffTime(shutavg);
					
					DataDeviceResult deviceResult=dataDeviceResultMapper.selectByPrimaryKey(bootLine.getDevice_no());
					if(null == deviceResult){
						dataDeviceResultMapper.insertBootTime(dataDeviceResult);
					}else{
						dataDeviceResultMapper.updateBootTime(dataDeviceResult);
					}
				}
			}
		}
		logger.info("[BootTimeAnalyseTask]:结束");
	}


	/**
	 * @param dayNum 后dayNum天的时间
	 * @return
	 */
	private String getBeforeDay(int dayNum) {
		Date dNow = new Date();   //当前时间
		Date dBefore = new Date();
		Calendar calendar = Calendar.getInstance(); //得到日历
		calendar.setTime(dNow);//把当前时间赋给日历
		calendar.add(Calendar.DAY_OF_MONTH, dayNum);  //设置为前一天
		dBefore = calendar.getTime();   //得到前一天的时间

		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd"); //设置时间格式
		String defaultStartDate = sdf.format(dBefore);    //格式化前一天
		System.out.println("前一天的时间是：" + defaultStartDate.toString());
		return defaultStartDate.toString();
	}

	/**
	 * 停止分析
	 */
	public void rest() {
		logger.info("[BootTimeAnalyseTask]:休息，啥也不做~");
	}
}
