package fthink.task;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.core.task.TaskExecutor;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import fthink.dao.BehaviorDataMapper;
import fthink.dao.BehaviorValueConfigMapper;
import fthink.dao.BootLineMapper;
import fthink.dao.DataDeviceResultMapper;
import fthink.dao.DataNetworkMapper;
import fthink.dao.DateBusinessTimeMapper;
import fthink.dao.MonitorConfigMapper;
import fthink.dao.TermBehaviorValueDayMapper;
import fthink.dao.TermMapper;
import fthink.dao.TermPositionFlowMapper;
import fthink.dao.TermTrafficDayMapper;
import fthink.entity.BehaviorData;
import fthink.entity.BehaviorValueConfig;
import fthink.entity.BootLine;
import fthink.entity.DataDeviceResult;
import fthink.entity.DataNetwork;
import fthink.entity.DateBusinessTime;
import fthink.entity.MonitorConfig;
import fthink.entity.Term;
import fthink.entity.TermBehaviorValueDay;
import fthink.entity.TermPositionFlow;
import fthink.entity.TermTrafficDay;
import fthink.service.IPushMessageService;
import fthink.utils.CommonUtils;
import fthink.utils.ConfigProperties;
import fthink.utils.Constants;
import fthink.utils.CosineSimilarUtil;
import fthink.utils.DateUtil;
import fthink.utils.SimilarUtil;
import fthink.utils.SysUtil;

/**
 * 行为数据结果分析 
 *
 */
public class BehaviorDataAnalyseTask {

	private Logger logger = Logger.getLogger(BehaviorDataAnalyseTask.class);
	
	@Resource
	private TermPositionFlowMapper termPositionFlowMapper;
	@Resource
	private DataDeviceResultMapper dataDeviceResultMapper;
	@Resource
	private DateBusinessTimeMapper dateBusinessTimeMapper;
	@Resource
	private BehaviorValueConfigMapper behaviorValueConfigMapper;
	@Resource
	private TermMapper termMapper;
	@Resource
	private BootLineMapper bootLineMapper;
	
	@Resource
	private DataNetworkMapper dataNetworkMapper;
	@Resource
	private TermBehaviorValueDayMapper termBehaviorValueDayMapper;
	@Resource
	private BehaviorDataMapper behaviorDataMapper;
	@Resource
	private TermTrafficDayMapper termTrafficDayMapper;
	
	@Resource
	private IPushMessageService pushMessageService;
	@Resource
	private MonitorConfigMapper monitorConfigMapper;
	
	@Resource(name = "taksExecutor")
	private TaskExecutor taskExecutor;
	
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
		logger.info("[BehaviorDataAnalyseTask]:开始");
		
		boolean pushFlag = false;
		
		//获取前一天
		String beforeDate = CommonUtils.getDate(-1);
		
		Map<String, Object> map  = new HashMap<String,Object>();
		map.put("beforeDate", beforeDate);
		
		List<TermPositionFlow> termNos = termPositionFlowMapper.selectAllTermNo(map);
		
		if(null != termNos && termNos.size()>0){
			for(TermPositionFlow flow: termNos){
				
				TermBehaviorValueDay termBehaviorValueDay = new TermBehaviorValueDay();
				termBehaviorValueDay.setDeviceNo(flow.getDevice_no());
				termBehaviorValueDay.setCreateTm(DateUtil.getCurrentTime());
				
				DataDeviceResult deviceResult=dataDeviceResultMapper.selectByPrimaryKey(flow.getDevice_no());
				if(null == deviceResult){
					logger.error(flow.getDevice_no()+"分析结果数据不存在！");
					continue;
				}
				Term term = termMapper.selectByPrimaryKey(flow.getDevice_no());
				if(null == term){
					logger.error(flow.getDevice_no()+"硬件序列号信息不存在！");
					continue;
				}
				BehaviorValueConfig behaviorValueConfig = behaviorValueConfigMapper.selectByPrimaryKey(term.getCorpNo());
				if(null == behaviorValueConfig){
					logger.error(flow.getDevice_no()+"机构行为权重值未配置！");
					continue;
				}
				
				//运营时间权重分析
				DateBusinessTime dbt = new DateBusinessTime();
				dbt.setAnalyDay(beforeDate);
				dbt.setDeviceNo(flow.getDevice_no());
				//取前一天运营时间
				DateBusinessTime dateBusinessTime = dateBusinessTimeMapper.selectByPrimaryKeys(dbt);
				if(null != dateBusinessTime){
					if(!SysUtil.isEmpty(behaviorValueConfig.getBusinessTimeWeight())) {
						//运营时间计算值
						/*double btime = setBehaviorData(dateBusinessTime.getTimeCount(),deviceResult.getTimeCount(),
								behaviorValueConfig.getBusinessTimeWeight());*/
						double btime = SimilarUtil.calcSimilar(dateBusinessTime.getTimeCount(), deviceResult.getTimeCount());
						logger.info("btime************:"+btime);
						termBehaviorValueDay.setBusinessTimeValue(btime);
					}
				}
				
				//网络环境权重计算
				DataNetwork dataNetwork = new DataNetwork();
				dataNetwork.setDeviceNo(flow.getDevice_no());
				dataNetwork.setAnalyDate(beforeDate);
				DataNetwork dbDataNetwork = dataNetworkMapper.selectOneByConditions(dataNetwork);
				if(null!=dbDataNetwork) {
					//网络未变化值为1，有变化值为0
					if(Constants.NETWORK_CONDITIONS_NOCHANGE.equals(dbDataNetwork.getNetworkConditions())) {
						termBehaviorValueDay.setNetworkValue(1d);
//						if(!SysUtil.isEmpty(behaviorValueConfig.getNetworkWeight())) {
//							DecimalFormat df = new DecimalFormat("0.00");
//							double value = Double.parseDouble(df.format(1*Double.parseDouble(behaviorValueConfig.getNetworkWeight())));
//							termBehaviorValueDay.setNetworkValue(1d);
//						}
					} else {
						termBehaviorValueDay.setNetworkValue(0d);
					}
				}
				
				
				//交易量以及成功率权重分析
				HashMap<String, Object> traceMap = new HashMap<>();
				traceMap.put("deviceNo", flow.getDevice_no());
				traceMap.put("date", beforeDate);
				traceMap.put("dataType", Constants.BEHAVIOR_VALUE_TYPE11);
				BehaviorData behaviorData = behaviorDataMapper.selectByAllPrimaryKey(traceMap);
				traceMap.put("dataType", Constants.BEHAVIOR_VALUE_TYPE12);
				BehaviorData behaviorSuccessData = behaviorDataMapper.selectByAllPrimaryKey(traceMap);
				
				if(null != behaviorData && null != behaviorSuccessData){
					int totalNum = behaviorData.getMagneticCardNum() + behaviorData.getNonConnectedIcNum()
							+ behaviorData.getContactIcNum();
					int successNum = behaviorSuccessData.getMagneticCardNum() + behaviorSuccessData.getNonConnectedIcNum()
							+ behaviorSuccessData.getContactIcNum();
					DecimalFormat df = new DecimalFormat("0.0000");
					double successRate = 0d;
					if(totalNum!=0) {
						successRate = Double.parseDouble(df.format((double)successNum/totalNum));
					}
					if(!SysUtil.isEmpty(behaviorValueConfig.getTradeWeight())) {
//						double traceNum = setBehaviorData(totalNum, deviceResult.getTraceCount(), behaviorValueConfig.getTradeWeight());
						double traceNum = SimilarUtil.calcSimilar(totalNum, deviceResult.getTraceCount());
						logger.info("traceNum************:"+traceNum);
						termBehaviorValueDay.setTradeValue(traceNum);
					}
					if(!SysUtil.isEmpty(behaviorValueConfig.getTradeSuccessWeight())) {
//						double traceSuccess = setBehaviorData(successRate, deviceResult.getTraceSuccessRate(), behaviorValueConfig.getTradeSuccessWeight());
						double traceSuccess = SimilarUtil.calcSimilar(successRate, deviceResult.getTraceSuccessRate());
						logger.info("traceSuccess************:"+traceSuccess);
						termBehaviorValueDay.setTradeSuccessValue(traceSuccess);
					}
				}
				
				//流量权重计算
				TermTrafficDay termTrafficDay = new TermTrafficDay();
				termTrafficDay.setUseDate(beforeDate);
				termTrafficDay.setTermNo(flow.getDevice_no());
				TermTrafficDay dbTermTrafficDay = termTrafficDayMapper.selectOneByConditions(termTrafficDay);
				if(null!=dbTermTrafficDay) {
					if(!SysUtil.isEmpty(deviceResult.getTraffic())) {
						//计算值
//						double value = setBehaviorData(Double.parseDouble(dbTermTrafficDay.getTraffic()),Long.valueOf(deviceResult.getTraffic()).doubleValue(),
//								behaviorValueConfig.getTrafficWeight());
						double value = SimilarUtil.calcSimilar(Double.parseDouble(dbTermTrafficDay.getTraffic()), Long.valueOf(deviceResult.getTraffic()).doubleValue());
						termBehaviorValueDay.setTrafficValue(value);
					}
				}
				
				//开机时间、关机时间权重分析
				HashMap<String, Object> bootMap = new HashMap<>();
				bootMap.put("device_no", flow.getDevice_no());
				bootMap.put("nowDate", beforeDate);
				List<BootLine> list= bootLineMapper.listBootLine(bootMap);
				if(list != null && list.size()>0){
					BootLine bootLine = list.get(0);
					if(!SysUtil.isEmpty(behaviorValueConfig.getBootTimeWeigth())) {
						int bootTime = Integer.parseInt(bootLine.getLastOnTm().substring(8, 10))*60+Integer.parseInt(bootLine.getLastOnTm().substring(10, 12));
//						double bootNum = setBehaviorData(bootTime, deviceResult.getBootTime(), behaviorValueConfig.getBootTimeWeigth());
						double bootNum = SimilarUtil.calcSimilar(bootTime, deviceResult.getBootTime());
						logger.info("bootNum************:"+bootNum);
						termBehaviorValueDay.setBootTimeValue(bootNum);
					}
					bootLine = list.get(list.size()-1);
					if(!SysUtil.isEmpty(behaviorValueConfig.getShutdownTimeWeight())) {
						int shutTime = Integer.parseInt(bootLine.getLastOffTm().substring(8, 10))*60+Integer.parseInt(bootLine.getLastOffTm().substring(10, 12));
//						double shutNum = setBehaviorData(shutTime, deviceResult.getOffTime(), behaviorValueConfig.getShutdownTimeWeight());
						double shutNum = SimilarUtil.calcSimilar(shutTime, deviceResult.getOffTime());
						logger.info("shutNum************:"+shutNum);
						termBehaviorValueDay.setShutTimeValue(shutNum);
					}
				}
				
				termBehaviorValueDay.setAnalyDay(beforeDate);
				TermBehaviorValueDay tbvd = termBehaviorValueDayMapper.selectByPrimaryKeys(termBehaviorValueDay);
				if(null == tbvd){
					pushFlag = false;
					double behaviorValue = 0d;
					/*behaviorValue = analyData(behaviorValue, termBehaviorValueDay.getBusinessTimeValue(), term,pushFlag);
					behaviorValue = analyData(behaviorValue, termBehaviorValueDay.getNetworkValue(), term,pushFlag);
					behaviorValue = analyData(behaviorValue, termBehaviorValueDay.getTradeValue(), term,pushFlag);
					behaviorValue = analyData(behaviorValue, termBehaviorValueDay.getTradeSuccessValue(), term,pushFlag);
					behaviorValue = analyData(behaviorValue, termBehaviorValueDay.getBootTimeValue(), term,pushFlag);
					behaviorValue = analyData(behaviorValue, termBehaviorValueDay.getShutTimeValue(), term,pushFlag);
					behaviorValue = analyData(behaviorValue, termBehaviorValueDay.getTrafficValue(), term,pushFlag);*/
					
					behaviorValue = analyData(termBehaviorValueDay, behaviorValueConfig);
					DecimalFormat df = new DecimalFormat("0.0000");
					behaviorValue = Double.valueOf(df.format(behaviorValue));
					
					termBehaviorValueDay.setBehaviorValue(behaviorValue);
					termBehaviorValueDay.setConfigDetail(new Gson().toJson(behaviorValueConfig));
					termBehaviorValueDayMapper.insert(termBehaviorValueDay);
					
					//告警推送
					autoPush(behaviorValue, term.getDevice_no(), term.getCorpNo());
					
				}else{
					logger.info("行为数据已分析！！！");
				}
				
			}
		}
		logger.info("[BehaviorDataAnalyseTask]:结束");
	}
	
	private double setBehaviorData(Double value, String weight) {
		if(value == null) {
			return 0d;
		}
		DecimalFormat df = new DecimalFormat("0.0000");
		double result = value * 0.01 * Double.parseDouble(weight);
		return Double.parseDouble(df.format(result));
	}

	/*private double setBehaviorData(double count, double count2,String weight) {
		DecimalFormat df = new DecimalFormat("0.0000");
		double result = 0d;
		if(count2!=0d) {
			result = Double.parseDouble(df.format(Math.abs(count-count2)/count2
					*0.01*Double.parseDouble(weight)));
		}
		return result;
	}*/
	
	/*private double analyData(double sum, Double plus, Term term, boolean pushFlag) {
		if(plus==null) {
			return sum;
		}
		sum = sum+plus;
		if(!pushFlag) {
			pushFlag = autoPush(plus, term.getDevice_no(), term.getCorpNo());
		}
		return sum;
	}*/
	
	private double analyData(TermBehaviorValueDay termBehaviorValueDay, BehaviorValueConfig behaviorValueConfig) {
		double sum = 0d;
		sum = setBehaviorData(termBehaviorValueDay.getBusinessTimeValue(),behaviorValueConfig.getBusinessTimeWeight())
				+ setBehaviorData(termBehaviorValueDay.getNetworkValue(),behaviorValueConfig.getNetworkWeight())
				+ setBehaviorData(termBehaviorValueDay.getTradeValue(),behaviorValueConfig.getTradeWeight())
				+ setBehaviorData(termBehaviorValueDay.getTradeSuccessValue(),behaviorValueConfig.getTradeSuccessWeight())
				+ setBehaviorData(termBehaviorValueDay.getBootTimeValue(),behaviorValueConfig.getBootTimeWeigth())
				+ setBehaviorData(termBehaviorValueDay.getShutTimeValue(),behaviorValueConfig.getShutdownTimeWeight())
				+ setBehaviorData(termBehaviorValueDay.getTrafficValue(),behaviorValueConfig.getTrafficWeight());
		return sum;
	}
	
	/**
	 * 自动推送锁定
	 * @param termNo
	 * @param corpNo
	 */
	private void autoPush(final Double value, final String termNo, final String corpNo) {
		if(null==value) {
			return;
		}
		
		//告警状态设置
		double[] behaviorLevel1 = ConfigProperties.getBehaviorLevel1();
		final double[] behaviorLevel2 = ConfigProperties.getBehaviorLevel2();
		final double[] behaviorLevel3 = ConfigProperties.getBehaviorLevel3();
		final double behaviorLevel4 = ConfigProperties.getBehaviorLevel4();
		
		taskExecutor.execute(new Runnable() {
			@Override
			public void run() {
				//获取监控配置
				MonitorConfig monitorConfig = monitorConfigMapper.selectByPrimaryKey(corpNo);
				if(null!=monitorConfig) {
					String behaviorConfig = monitorConfig.getBehaviorConfig();
					logger.info("监控配置——行为异常配置：" + behaviorConfig);
					if(!SysUtil.isEmpty(behaviorConfig)) {
						JsonObject jsonObject = new Gson().fromJson(behaviorConfig, JsonObject.class);
						String status = jsonObject.get("status")==null?"":jsonObject.get("status").getAsString();
						if(!SysUtil.isEmpty(status)) {
							Set<Entry<String, JsonElement>> keySet = jsonObject.entrySet();
							for(Entry<String,JsonElement> entry : keySet) {
								String key = entry.getKey();
								if("status".equals(key)) {
									continue;
								}
								String val = jsonObject.get(key).getAsString();
								if("1".equals(val)) {
									try {
										if(Constants.BEHAVIOR_VALUE_STATUS1.equals(status)) {
											if(value<behaviorLevel2[1]) {
												pushMessageService.pushMessage(termNo, key, "1", "1");
											}
										} else if(Constants.BEHAVIOR_VALUE_STATUS2.equals(status)) {
											if(value<behaviorLevel3[1]) {
												pushMessageService.pushMessage(termNo, key, "1", "1");
											}
										} else if(Constants.BEHAVIOR_VALUE_STATUS3.equals(status)) {
											if(value<=behaviorLevel4) {
												pushMessageService.pushMessage(termNo, key, "1", "1");
											}
										}
									} catch (Exception e) {
										logger.error("行为数据异常自动推送类型"+key+"推送异常。",e);
										e.printStackTrace();
									}
								}
							}
						}
					}
				}
			}
		});
	}

	/**
	 * 停止分析
	 */
	public void rest() {
		logger.info("[BehaviorDataAnalyseTask]:休息，啥也不做~");
	}
}
