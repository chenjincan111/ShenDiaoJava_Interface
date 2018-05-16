package fthink.utils;

import java.math.BigDecimal;

/**
 * 余弦相似度计算
 * @author CAIJIE
 *
 */
public class CosineSimilarUtil {
	/**
	 * 计算相似度
	 * x的长度与y的长度要一致
	 * @param x
	 * @param y
	 * @return
	 */
	public static double calcSimilar(double[] x, double[] y) {
		double dividend = 0d;
		for(int i=0;i<x.length;i++) {
			dividend += x[i] * y[i];
		}
		
		double xsum = 0d;
		double ysum = 0d;
		for(int i=0;i<x.length;i++) {
			xsum += x[i] * x[i];
			ysum += y[i] * y[i];
		}
		double divisor = Math.sqrt(xsum * ysum);
		if(dividend == divisor) {
			return 1d;
		}
		if(dividend == 0d || divisor == 0d) {
			return 0d;
		}
		BigDecimal result = new BigDecimal(dividend / divisor);
		//BigDecimal.ROUND_DOWN 直接舍去剩余小数部分
		double r = result.setScale(4, BigDecimal.ROUND_DOWN).doubleValue();
		//如果小于等于0，则直接为0
		return r > 0d ? r : 0d; 
	}
	
	public static double calcSimilar(double x, double y) {
		double temp = x - y;
		double[] xarry = new double[]{temp, x};
		double[] yarry = new double[]{0d-temp, y};
		return calcSimilar(xarry, yarry);
	}
	
	public static void main(String[] args) {
		double result = calcSimilar(100, 200);
		System.out.println(result);
	}
}
