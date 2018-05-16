package fthink.task;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import fthink.dao.DateBusinessTimeMapper;
import fthink.dao.TermPositionFlowMapper;
import fthink.entity.DateBusinessTime;
import fthink.entity.TermPositionFlow;
import fthink.utils.CommonUtils;
import fthink.utils.DateUtil;

/**
 * 运营时间数据保存
 *
 */
public class BusinessTimeTask {

	private Logger logger = Logger.getLogger(BusinessTimeTask.class);
	
	@Resource
	private TermPositionFlowMapper termPositionFlowMapper;
	@Resource
	private DateBusinessTimeMapper dateBusinessTimeMapper;
	
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
		logger.info("[BusinessTimeTask]:开始");
		
		//获取前一天
		String beforeDate = CommonUtils.getDate(-1);
		
		Map<String, Object> map  = new HashMap<String,Object>();
		map.put("beforeDate", beforeDate);
		
		List<TermPositionFlow> termNos = termPositionFlowMapper.selectAllTermNo(map);
		
//		String currentDate = DateUtil.getCurrentDate();
		
		for(TermPositionFlow flow: termNos){
			String deviceNo = flow.getDevice_no();
			map.put("termNo", deviceNo);
			List<TermPositionFlow> positionFlows = termPositionFlowMapper.selectByPrimaryKeys(map);
			List<Integer> timeList = new ArrayList<>(); 
			for(TermPositionFlow positionFlow: positionFlows){
				if(positionFlow.getCreateTm().length() == 14){
					timeList.add(Integer.parseInt(positionFlow.getCreateTm().substring(8, 10)));
				}
			}
			LinkedHashSet h  =   new  LinkedHashSet(timeList);   
			timeList.clear();   
			timeList.addAll(h);
			
			DateBusinessTime dateBusinessTime = new DateBusinessTime();
			dateBusinessTime.setDeviceNo(deviceNo);
			dateBusinessTime.setAnalyDay(beforeDate);
		    //String json = getBusinessTimeJson(timeList);
			String bTime = getBusinessTime(timeList);
		    dateBusinessTime.setBusinessTime(bTime);
			dateBusinessTime.setTimeCount(timeList.size());
			
			DateBusinessTime businessTime = dateBusinessTimeMapper.selectByPrimaryKeys(dateBusinessTime);
			if(null == businessTime){
				dateBusinessTimeMapper.insert(dateBusinessTime);
			}else{
				dateBusinessTime.setLastModifyTime(DateUtil.getCurrentTime());
				dateBusinessTimeMapper.updateByPrimaryKey(dateBusinessTime);
			}
			
		}
		logger.info("[BusinessTimeTask]:结束");
	}

	private String getBusinessTime(List<Integer> timeList) {
		String time = "000000000000000000000000";
		for(int index: timeList){
			time = time.substring(0, index)+"1"+time.substring(index+1, 24);
		}
		return time;
	}
	
	/*private String getBusinessTimeJson(List<Integer> timeList) {
		List<BusinessTimeDto> jsonList = new ArrayList<>();
		for(int i=0;i<timeList.size();i++){
			int start = timeList.get(i);
			int end = timeList.get(i)+1;
			int n = 1;
			while((i+1) < timeList.size() && start+n == timeList.get(i+1)){
				end = timeList.get(i+1)+1;
				n++;i++;
			}
			BusinessTimeDto dto = new BusinessTimeDto();
			dto.setStart(start);
			dto.setEnd(end);
			jsonList.add(dto);
		}
		Gson gson = new Gson();
		return gson.toJson(jsonList);
	}*/

	/**
	 * 停止分析
	 */
	public void rest() {
		logger.info("[BusinessTimeTask]:休息，啥也不做~");
	}
}
