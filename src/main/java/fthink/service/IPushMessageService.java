package fthink.service;


public interface IPushMessageService {
	
	public void pushMessage(String deviceNo, String code, String type, String op) throws Exception;
	
	
}
