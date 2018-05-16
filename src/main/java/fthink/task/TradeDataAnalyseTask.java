package fthink.task;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import fthink.dao.BehaviorDataMapper;
import fthink.dao.DataDeviceResultMapper;
import fthink.dao.TermPositionFlowMapper;
import fthink.entity.BehaviorData;
import fthink.entity.DataDeviceResult;
import fthink.entity.TermPositionFlow;
import fthink.utils.CommonUtils;
import fthink.utils.Constants;
import fthink.utils.DateUtil;
import fthink.utils.SysUtil;

/**
 * 交易量分析
 *
 */
public class TradeDataAnalyseTask {

	private Logger logger = Logger.getLogger(TradeDataAnalyseTask.class);
	
	@Resource
	private TermPositionFlowMapper termPositionFlowMapper;
	@Resource
	private BehaviorDataMapper behaviorDataMapper;
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
		logger.info("[TradeDataAnalyseTask]:开始");
		
		//获取前一天
		String beforeDate = CommonUtils.getDate(-1);
		
		Map<String, Object> param  = new HashMap<String,Object>();
		param.put("beforeDate", beforeDate);
		
		List<TermPositionFlow> termNos = termPositionFlowMapper.selectAllTermNo(param);
		
		if(null != termNos && termNos.size()>0){
			for(TermPositionFlow flow: termNos){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("deviceNo", flow.getDevice_no());
				map.put("maxDate", DateUtil.getCurrentDate());
				map.put("minDate", CommonUtils.getDate(-10));
				
				//map.put("countDate", DateUtil.getCurrentDate());
				map.put("dataType", Constants.BEHAVIOR_VALUE_TYPE11);
				List<BehaviorData> behaviorDataList = behaviorDataMapper.selectByPrimaryKeys(map);
				map.put("dataType", Constants.BEHAVIOR_VALUE_TYPE12);
				List<BehaviorData> behaviorDataListSuccess = behaviorDataMapper.selectByPrimaryKeys(map);
				
				if(null == behaviorDataList || 0==behaviorDataList.size() 
						|| null == behaviorDataListSuccess || 0==behaviorDataListSuccess.size()){
					return;
				}
				int behaciorTotal = analyseTraceAcvNum(behaviorDataList);
				int behaciorTotalSuccess = analyseTraceAcvNum(behaviorDataListSuccess);
				
				DataDeviceResult dataDeviceResult = new DataDeviceResult();
				dataDeviceResult.setDeviceNo(flow.getDevice_no());
				dataDeviceResult.setLastModifyTime(DateUtil.getCurrentTime());
				dataDeviceResult.setTraceCount(behaciorTotal/behaviorDataList.size());
				DecimalFormat df = new DecimalFormat("0.0000");
				double rate = 0d;
				if(behaciorTotal!=0) {
					rate = Double.parseDouble(df.format((double)behaciorTotalSuccess/behaciorTotal));
				}
				dataDeviceResult.setTraceSuccessRate(rate);
				
				DataDeviceResult deviceResult=dataDeviceResultMapper.selectByPrimaryKey(flow.getDevice_no());
				if(null == deviceResult){
					dataDeviceResultMapper.insertTradeCount(dataDeviceResult);
				}else{
					dataDeviceResultMapper.updateTradeCount(dataDeviceResult);
				}
				
			}
		}
		
		logger.info("[TradeDataAnalyseTask]:结束");
	}
	
	private int analyseTraceAcvNum(List<BehaviorData> behaviorDataList){
		DataDeviceResult dataDeviceResult = new DataDeviceResult();
		dataDeviceResult.setLastModifyTime(DateUtil.getCurrentTime());
		int tradeNum = 0;
		for(BehaviorData behaviorData:behaviorDataList){
				//交易量
			tradeNum += behaviorData.getMagneticCardNum() + behaviorData.getNonConnectedIcNum()
					+ behaviorData.getContactIcNum();
		}
		return tradeNum;
	}
	
	/**
	 * 停止分析
	 */
	public void rest() {
		logger.info("[TradeDataAnalyseTask]:休息，啥也不做~");
	}
}
