package fthink.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import fthink.utils.MD5;

public class SecurityUtils {


	public static String generateSign(Map<String,Object> map, String tdesKey) {
		
		
		ArrayList<String> list = new ArrayList<String>();
		for(Map.Entry<String,Object> entry:map.entrySet()){
	        if(entry.getValue()!=""){
	        	list.add(entry.getKey() + "=" + entry.getValue() + "&");
	        }
		}
		int size = list.size();
		String [] arrayToSort = list.toArray(new String[size]);
		Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < size; i ++) {
		    sb.append(arrayToSort[i]);
		}
		String result = sb.toString();
		result += "key=" + tdesKey;
		System.out.println("result:"+result);
		result = new MD5().getMD5Str(result).toUpperCase();
				
		return result;
	}
	
	/**
	 * 检验签名的函数
	 * @param tdesKey 3DES加解密Key
	 * @param decBody 解密后的Body
	 * @param sign 客户端上送的签名字符串
	 * @return
	 */
	public static boolean verifyBodySign(String sign, Map<String,Object> map, String tdesKey) {
		String calcSign = generateSign(map, tdesKey);
		return calcSign.equals(sign);
	}
}
