package fthink.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.protocol.Protocol;


/**
 * 
 * @author Jiangping Wang
 *
 */
public class HttpUtils {
	
	/*public static String getHttpContent(String url, Map ) {
		
	}*/
	
	/**
	 * 获取网页内容
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static String getHttpContent(String url) throws Exception {
		//构造HttpClient的实例
		HttpClient httpClient = new HttpClient();
		
		//创建GET方法的实例
		GetMethod getMethod = new GetMethod(url);
		//使用系统提供的默认的恢复策略
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
		getMethod.getParams().setSoTimeout(60000); //30秒
		InputStream inputStream = null;
		BufferedReader reader = null;
		try {
			//执行getMethod
			int statusCode = httpClient.executeMethod(getMethod);
			if (statusCode != HttpStatus.SC_OK) {
				throw new Exception("Request failed");
			}
			//处理内容
			StringBuffer stringBuffer = new StringBuffer();
			inputStream = getMethod.getResponseBodyAsStream();
			reader = new BufferedReader(new InputStreamReader(inputStream, getCharset(getMethod)));
			String line;
            while((line = reader.readLine())!=null) {
            	stringBuffer.append(line);
            	stringBuffer.append('\n');
            }
			return stringBuffer.toString();
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new Exception("Request failed,url is " + url);
		}
		finally {
			try {
				reader.close();
			}
			catch(Exception e) {
				
			}
			try {
				inputStream.close();
			}
			catch(Exception e) {
				
			}
			//释放连接
			getMethod.releaseConnection();
		}
	}
	public static String doPost(String url, Object data) throws Exception {
		return doPost(url, data, null);
	}
	/**
	 * 提交
	 * @param url
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static String doPost(String url, Object data, String charset) throws Exception {
		
		//构造HttpClient的实例
		HttpClient httpClient = new HttpClient();
		PostMethod postMethod = new PostMethod(url);
		if(charset != null) {
			postMethod.getParams().setContentCharset(charset); 
		}
		else {
			postMethod.getParams().setContentCharset("UTF-8"); 
		}

		postMethod.getParams().setSoTimeout(65000); //20秒
		InputStream inputStream = null;
		BufferedReader reader = null;
		try {
			//将表单的值放入postMethod中
			if(data!=null) {
				if(data instanceof String) {
					postMethod.setRequestBody((String)data);
				}
				else {
					postMethod.setRequestBody((NameValuePair[])data);
				}
			}
			//执行postMethod
			int statusCode = httpClient.executeMethod(postMethod);
			//HttpClient对于要求接受后继服务的请求，象POST和PUT等不能自动处理转发
			//301或者302
			
			if(statusCode == HttpStatus.SC_MOVED_PERMANENTLY ||	statusCode == HttpStatus.SC_MOVED_TEMPORARILY) {
				// 从头中取出转向的地址
				Header locationHeader = postMethod.getResponseHeader("location");
				if(locationHeader != null) {
					return "REDIRECT TO:" + locationHeader.getValue();
				}
			}
			else if(statusCode==200) {
				StringBuffer stringBuffer = new StringBuffer();
				inputStream = postMethod.getResponseBodyAsStream();
				reader = new BufferedReader(new InputStreamReader(inputStream, getCharset(postMethod)));
				String line;
				while((line = reader.readLine())!=null){
					stringBuffer.append(line);
					stringBuffer.append('\n');
				}
				return stringBuffer.toString();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(reader!=null) {
					reader.close();
				}
			}
			catch(Exception e) {
				
			}
			try {
				if(inputStream!=null) {
					inputStream.close();
				}
			}
			catch(Exception e) {
				
			}
			//释放连接
			postMethod.releaseConnection();
		}
		return null;
	}
	
	/**
	 * 获取字符集
	 * @param getMethod
	 * @return
	 */
	private static String getCharset(HttpMethod httpMethod) {
		String charset = "gbk";
		Header contentTypeHeader = httpMethod.getResponseHeader("Content-Type");
		if(contentTypeHeader!=null) {
			String contentType = contentTypeHeader.getValue();
			int index = contentType.indexOf("charset=");
			if(index!=-1) {
				charset = contentType.substring(index + "charset=".length());
			}
		}
		return charset;
	}
}
