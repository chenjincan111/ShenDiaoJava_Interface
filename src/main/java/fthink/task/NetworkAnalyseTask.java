package fthink.task;


import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import fthink.dao.DataNetworkMapper;
import fthink.dao.TermMapper;
import fthink.dao.TermPositionFlowMapper;
import fthink.entity.DataNetwork;
import fthink.entity.DataSwitchTimesInfo;
import fthink.entity.Term;
import fthink.entity.TermPositionFlow;
import fthink.utils.CommonUtils;
import fthink.utils.Constants;
import fthink.utils.DateUtil;

/**
 * 网络状态分析
 * @author zhu-jq
 *
 */
public class NetworkAnalyseTask {

	private Logger logger = Logger.getLogger(NetworkAnalyseTask.class);
	
	@Resource
	private TermPositionFlowMapper termPositionFlowMapper;
	@Resource
	private TermMapper termMapper;
	@Resource
	private DataNetworkMapper dataNetworkMapper;
	
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
		logger.info("[NetworkAnalyseTask]:开始");
		//步骤1：获取终端信息
		List<TermPositionFlow> flows=termPositionFlowMapper.listDeviceNo();
		//步骤2：根据终端序列号获取终端位置流水信息
		if(flows!=null && flows.size()>0){
			Map<String, Object> map=new HashMap<>();
//			String nowDate = DateUtil.getCurrentDate();
			String nowDate = CommonUtils.getDate(-1);
			map.put("nowDate", nowDate);
			for (TermPositionFlow positionFlow : flows) {
				map.put("device_no", positionFlow.getDevice_no());
				//根据终端号，获取当前日期的位置流水信息
				List<TermPositionFlow> list= termPositionFlowMapper.selectByDeviceNo(map);
				//判断网络类型和网络名称是否有变化
				if(list!=null && list.size()>0){
					//步骤3：对流水信息进行解析（使用set集合保存网络类型和网络名称，如果set集合大于1则表示有不同项）
					Set<String> typeSet = new HashSet<>();
					Set<String> nameSet = new HashSet<>();
					for (TermPositionFlow termPositionFlow : list) {
						typeSet.add(termPositionFlow.getNetwork_type());
						nameSet.add(termPositionFlow.getNetwork_name());
					}
					DataNetwork dataNetwork = new DataNetwork();
					dataNetwork.setAnalyDate(nowDate);
					dataNetwork.setDeviceNo(positionFlow.getDevice_no());
					dataNetwork.setLastModifyTime(DateUtil.getCurrentTime());
					
					if(typeSet.size()==1 && nameSet.size()==1){
						dataNetwork.setNetworkConditions(Constants.NETWORK_CONDITIONS_NOCHANGE);
						logger.info("设备"+positionFlow.getDevice_no()+"网络正常");
					}else{
						if(nameSet.size()>1){
							dataNetwork.setNetworkConditions(Constants.NETWORK_CONDITIONS_WIFINAMECHANGE);
							logger.info("设备"+positionFlow.getDevice_no()+"的网络名称有变化");
						}else if(typeSet.size()>1){
							dataNetwork.setNetworkConditions(Constants.NETWORK_CONDITIONS_NETWORKCHANGE);
							logger.info("设备"+positionFlow.getDevice_no()+"的网络类型有变化");
						}
					}
					DataNetwork dnet = dataNetworkMapper.selectByPrimaryKeys(dataNetwork);
					if(null == dnet){
						dataNetworkMapper.insert(dataNetwork);
					}else{
						dataNetworkMapper.updateByPrimaryKey(dataNetwork);
					}
				}
			}
		}
		logger.info("[NetworkAnalyseTask]:结束");
	}
	
	/**
	 * 停止分析
	 */
	public void rest() {
		logger.info("[NetworkAnalyseTask]:休息，啥也不做~");
	}
}
