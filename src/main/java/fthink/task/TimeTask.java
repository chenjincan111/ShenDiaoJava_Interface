package fthink.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

public class TimeTask {
	
	private Logger logger = Logger.getLogger(getClass());
	
	public void run() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(new Date());
		logger.info(date);
	}

}
