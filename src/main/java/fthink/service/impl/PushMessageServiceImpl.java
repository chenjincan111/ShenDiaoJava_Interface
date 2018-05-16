package fthink.service.impl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import fthink.dao.TermDynMapper;
import fthink.dto.CmdPushRequest;
import fthink.dto.Message;
import fthink.entity.TermDyn;
import fthink.exception.ServiceException;
import fthink.security.SecurityUtils;
import fthink.service.IPushMessageService;
import fthink.utils.CmdPushResponse;
import fthink.utils.HttpUtils;
import fthink.utils.PushProperties;
import fthink.utils.SysUtil;

@Service("pushMessageService")
public class PushMessageServiceImpl implements IPushMessageService {
	
	private Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private TermDynMapper termDynMapper;
	
	@Override
	public void pushMessage(String deviceNo, String code, String type, String op) throws Exception {
		
		Map<String,Object> contentMap = new HashMap<String,Object>();
		Map<String,Object> cmdMap = new HashMap<String,Object>();
		cmdMap.put("TERM_ID", deviceNo);
		cmdMap.put("TRANS_ID", code);
		cmdMap.put("UUID", SysUtil.uuid());
		cmdMap.put("APP_ID", "");
		cmdMap.put("TYPE", type);
		cmdMap.put("OP", op);
		contentMap.put("msgtype", "cmd");
		contentMap.put("cmd", cmdMap);
		
		Message message = new Message();
		message.setContent(contentMap);
		
		String content = new Gson().toJson(message);
		
		String secretKey = PushProperties.getSecretKey();
		CmdPushRequest request = new CmdPushRequest();
		request.setAccess_id(PushProperties.getAccessId());
		request.setApp_id(PushProperties.getAppId());
		
		request.setDevice_alias(deviceNo);
		request.setExpire_time(100);
		String timestamp = Calendar.getInstance().getTimeInMillis() + "";
		request.setTimestamp(timestamp);
		request.setMessage(content);
		
		Map map = request.toMap();

		request.setSign(SecurityUtils.generateSign(map,secretKey));
		
		logger.info("推送请求数据："+new Gson().toJson(request));

		NameValuePair[] params = new NameValuePair[map.size() + 1];
		int i =0;
		Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = it.next();
			String key = entry.getKey();
			String value = entry.getValue()+"";
			params[i++] =new NameValuePair(key,value);  
		}
		params[i++] =new NameValuePair("sign",request.getSign()); 
		String  retData = HttpUtils.doPost(PushProperties.getCmdpushUrl(), params,"UTF-8");
		logger.info("通讯系统自动推送锁定应答："+retData);
		
		CmdPushResponse response = new Gson().fromJson(retData, CmdPushResponse.class);
		//验证消息
		validateCmdPushResponse(response);
		
		if("0000".equals(response.getErrorCode())) {
			Map result = new Gson().fromJson(response.getResult(), Map.class);
			String retCode = (String) result.get("CODE");
			if("00".equals(retCode)) {
				if("21".equals(code)) {
					TermDyn termDyn = new TermDyn();
					termDyn.setDeviceNo(deviceNo);
					termDyn.setAppNetPerLevel(op);
					termDynMapper.updatePermission(termDyn);
					
				} else if("22".equals(code)) {
					TermDyn termDyn = new TermDyn();
					termDyn.setDeviceNo(deviceNo);
					termDyn.setCipKeyboardPerLevel(op);
					termDynMapper.updatePermission(termDyn);
					
				} else if("23".equals(code)) {
					TermDyn termDyn = new TermDyn();
					termDyn.setDeviceNo(deviceNo);
					termDyn.setCardReaderPerLevel(op);
					termDynMapper.updatePermission(termDyn);
					
				} else if("24".equals(code)) {
					TermDyn termDyn = new TermDyn();
					termDyn.setDeviceNo(deviceNo);
					termDyn.setCerPerLevel(op);
					termDynMapper.updatePermission(termDyn);
				}
			}
		}
	}
	
	private void validateCmdPushResponse(CmdPushResponse response) throws Exception {
		if(null==response) {
			throw new ServiceException("推送服务连接失败");
		}
		if("0000".equals(response.getErrorCode())){
			String result = response.getResult();
			if(!SysUtil.isEmpty(result)){
				Map<String,Object> t = new HashMap<String,Object>();
				t.put("result", result);
				if(SecurityUtils.verifyBodySign(response.getSign(), t, PushProperties.getSecretKey())){
					String content = new String(Base64.decodeBase64(result),"utf-8");
					response.setResult(content);
				}else{
					throw new ServiceException("验证签名不通过");
				}
			}
		}else{
			throw new ServiceException(response.getErrorMessage());
		}
	}

}
