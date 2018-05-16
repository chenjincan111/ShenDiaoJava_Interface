package fthink.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;

import fthink.exception.ServiceException;
/**
 * 异常统一拦截
 * @author CAIJIE
 *
 */
public class ServiceExceptionHandler implements HandlerExceptionResolver {
	
	private static Logger logger = Logger.getLogger(ServiceExceptionHandler.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object obj, Exception ex) {
		
		ex.printStackTrace();
		logger.error(ex);
		
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		jsonMap.put("success", false);
		
		if(ex instanceof ServiceException) {
			jsonMap.put("message", ex.getMessage());
		}
		else if(ex instanceof CommunicationsException) {
			jsonMap.put("message", "数据库连接失败");
		}
		else if(ex instanceof SQLException) {
			jsonMap.put("message", "操作数据库异常");
		}
		
		else {
			jsonMap.put("message", "系统异常");
		}
		try {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print(new Gson().toJson(jsonMap));
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return new ModelAndView();
	}

}
