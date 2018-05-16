package fthink.utils;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import jxl.Sheet;
import jxl.read.biff.BiffException;


public class CommonUtils {
	/**
	 * 判断是否为数字
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isNumber(String s) {

		try {
			Long.parseLong(s);
		} catch (Exception ex) {
			return false;
		}
		return true;
	}

	public static String[][] getStringArray(InputStream is) {
		jxl.Workbook rwb = null;
		try {
			rwb = jxl.Workbook.getWorkbook(is);
			Sheet sheet = rwb.getSheet(0);
			int rows = sheet.getRows();
			int columns = sheet.getColumns();
			String[][] result = new String[rows][columns];
			for (int row = 0; row < rows; row++) {
				for (int column = 0; column < columns; column++) {
					result[row][column] = SysUtil.isEmpty(sheet.getCell(column,
							row).getContents()) ? "" : sheet
							.getCell(column, row).getContents().trim();
				}
			}
			return result;
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return new String[0][0];

	}

	/**
	 * 将 String 转为 map
	 * 
	 * @param param
	 *            aa=11&bb=22&cc=33
	 * @return
	 */
	public static Map<String, Object> getMapFromString(String param) {
		Map<String, Object> map = new HashMap<String, Object>();
		if ("".equals(param) || null == param) {
			return map;
		}
		String[] params = param.split("&");
		for (int i = 0; i < params.length; i++) {
			String[] p = params[i].split("=");
			if (p.length == 2) {
				map.put(p[0], p[1]);
			}
		}
		return map;
	}

	/**
	 * 获取未来时间
	 * 
	 * @param mode
	 *            模式： 1-秒 2-分钟 3-小时 4-天 5-星期 6-月 7-年
	 * 
	 * @param times
	 *            相对于当前的时间差
	 * @return
	 * @exception
	 */
	public static String getFutureTime(int mode, int times) {

		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");

		switch (mode) {
		case 1: // 秒
		{
			calendar.set(Calendar.SECOND, calendar.get(Calendar.SECOND) + times);
			break;
		}
		case 2: // 分
		{
			calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + times);
			break;
		}
		case 3: // 时
		{
			calendar.set(Calendar.HOUR_OF_DAY,
					calendar.get(Calendar.HOUR_OF_DAY) + times);
			break;
		}
		case 4: // 天
		{
			calendar.set(Calendar.DAY_OF_YEAR,
					calendar.get(Calendar.DAY_OF_YEAR) + times);
			break;
		}
		case 5: // 周
		{
			calendar.set(Calendar.WEEK_OF_YEAR,
					calendar.get(Calendar.WEEK_OF_YEAR) + times);
			break;
		}
		case 6: // 月
		{
			calendar.add(Calendar.MONTH, times);
			break;
		}
		case 7: // 年
		{
			calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + times);
			break;
		}
		default:
			break;
		}
		return df.format(calendar.getTime());
	}

	/**
	 * 比较时间差: end - begin
	 * 
	 * @param time_begin
	 * @param time_end
	 * @return 返回对应时间差（毫秒）
	 */
	public static long timeCompare(String time_begin, String time_end) {
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		long diff = 0;
		try {
			Date begin = df.parse(time_begin);
			Date end = df.parse(time_end);
			diff = end.getTime() - begin.getTime();
		} catch (Exception e) {
			diff = 0;
		}
		return diff;
	}

	/**
	 * 格式化时间
	 * 
	 * @param time
	 *            时间
	 * @param format
	 *            格式
	 * @return 格式化后的时间
	 */
	public static String formatTime(String time) {
		String formatTime = time;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DateFormat dfOld = new SimpleDateFormat("yyyyMMddHHmmss");
		try {
			Date formatDate = dfOld.parse(formatTime);
			formatTime = df.format(formatDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			formatTime = time;
		}
		return formatTime;
	}

	/**
	 * 获取当前日期
	 * 
	 * @return
	 */
	public static String getCurrentDate() {
		Date now = new Date();
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		return df.format(now);

	}

	/**
	 * 获取当前时间加上x后的值
	 * 
	 * @param x
	 * @return
	 */
	public static String additonTime(long x) {
		long currentTime = System.currentTimeMillis() + x;
		Date date = new Date(currentTime);
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String expires_time = df.format(date);
		return expires_time;
	}
	
	/**
	 * 获取当前月份yyyyMM
	 * @return
	 */
	public static String getMonthTime(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		Date date = new Date();
		// logger.info(sdf.format(date));
		String month_time = sdf.format(date);
		return month_time;
	}
	
	/**
	 * 获取上个月份yyyyMM
	 * @return
	 */
	public static String getLastMonthTime(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, -1);
		// logger.info(sdf.format(cal.getTime()));
		String last_month_time = sdf.format(cal.getTime());
		return last_month_time;
		
	}
	
	/**
	 * 获取日期
	 * @param add
	 * @return
	 */
	public static String getDate(int add) {
		Calendar cal = Calendar.getInstance();
		if(add !=0) {
			cal.add(Calendar.DAY_OF_MONTH, add);
		}
		Date date = cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String str = sdf.format(date);
		return str;
	}
	
	
	
	

}
