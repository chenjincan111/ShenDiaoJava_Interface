package fthink.interceptor;

import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;



public class MdcInterceptor implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception e)
			throws Exception {
		// TODO Auto-generated method stub
		MDC.clear();
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object object, ModelAndView mv) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object object) throws Exception {
		// TODO Auto-generated method stub
		try {
			String ip = getRemoteHost(request);
			String uuid = UUID.randomUUID().toString().replaceAll("-", "");
			MDC.put("ip", ip);
			MDC.put("uuid", uuid);
			MDC.put("url", request.getRequestURI());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	

	/**
	 * 获取客户端IP
	 * @param request
	 * @return
	 */
	public String getRemoteHost(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
	}

}
