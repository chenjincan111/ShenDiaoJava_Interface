package fthink.utils;

import java.math.BigDecimal;

/**
 * 相似度计算方法
 * @author CAIJIE
 *
 */
public class SimilarUtil {
	
	/**
	 * 计算相似度
	 * @param x 当前值
	 * @param y 平均值
	 * @return
	 */
	public static double calcSimilar(double x, double y) {
		if(x == y) {
			return 1d;
		}
		double r = 0d;
		if(x == 0 || y == 0 || x > (2*y)) {
			return 0d;
		}
		if(x < y) {
			r = x / y;
		} else if(x < (2 * y)) {
			r = (2*y-x)/y;
		}
		BigDecimal result = new BigDecimal(r);
		//BigDecimal.ROUND_HALF_DOWN四舍五入
		r = result.setScale(4, BigDecimal.ROUND_HALF_DOWN).doubleValue();
		return r > 0d ? r : 0d; 
	}
	
	public static void main(String[] args) {
		double r = calcSimilar(0,0);
		System.out.println(r);
	}

}
