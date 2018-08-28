package com.pphgzs.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class TimeUtil {
	static SimpleDateFormat formatter;

	/**
	 * 得到几天前的时间
	 * 
	 * @param d
	 * @param day
	 * @return
	 */
	public static String getDateBefore(Date d, int day) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
		return formatter.format(now.getTime());
	}

	/**
	 * 获取刘伟随机数
	 * 
	 * @return
	 */
	public static String sixRandom() {
		String str = String.valueOf(Math.ceil(Math.random() * 500000 + 500000));
		return str.substring(0, str.indexOf("."));

	}

	/**
	 * 
	 * @return
	 */
	public static String getDateBy() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		formatter.setLenient(false);
		return formatter.format(new Date());
	}

	public static String longDateFormatDate(String time) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		formatter.setLenient(false);
		Date newDate = formatter.parse(time);
		formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(newDate);
	}

	// 获得精确到秒的时间
	public static String getStringSecond() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date secondDate = new Date();
		String date = formatter.format(secondDate);
		try {
			secondDate = formatter.parse(date);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
			return "0000-00-00 00:00:00";
		}

	}

	// 获得精确到天的时间
	public static String getStringDay() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date secondDate = new Date();
		String date = formatter.format(secondDate);
		try {
			secondDate = formatter.parse(date);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}

	}

	// 获得精确到天的时间，提前七天
	public static String getStringDay_before7() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date secondDate = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(secondDate);
		calendar.add(Calendar.DAY_OF_MONTH, -7);
		secondDate = calendar.getTime();
		String date = formatter.format(secondDate);
		try {
			secondDate = formatter.parse(date);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}

	}

	// 获得精确到天的时间，提前七天
	public static String getStringDay_beforeDay(int day) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date secondDate = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(secondDate);
		calendar.add(Calendar.DAY_OF_MONTH, day);
		secondDate = calendar.getTime();
		String date = formatter.format(secondDate);
		try {
			secondDate = formatter.parse(date);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}

	}

	@Test
	public void ttt() {
		String start = "2012-02-01";
		String end = "2012-02-01";
		List<String> lDate = null;
		try {
			lDate = findDates(start, end);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		for (String date : lDate) {
			System.out.println(date);
		}
	}

	@Test
	public void tttt() {
		try {
			System.out.println(findDates("2018-08-01", "2018-08-31"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public static List<String> findDates(String dBegin, String dEnd) throws ParseException {
		Date begin = null;
		Date end = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		begin = sdf.parse(dBegin);
		end = sdf.parse(dEnd);
		List<String> lDate = new ArrayList<>();
		lDate.add(dBegin);
		Calendar calBegin = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		calBegin.setTime(begin);
		Calendar calEnd = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		calEnd.setTime(end);
		// 测试此日期是否在指定日期之后
		while (end.after(calBegin.getTime())) {
			// 根据日历的规则，为给定的日历字段添加或减去指定的时间量
			calBegin.add(Calendar.DAY_OF_MONTH, 1);
			lDate.add(sdf.format(calBegin.getTime()));
		}
		calBegin.add(Calendar.DAY_OF_MONTH, 1);
		lDate.add(sdf.format(calBegin.getTime()));
		return lDate;
	}

	@Test
	public void gg() {
		System.out.println(validate("0000-01-01", "9999-12-31"));
	}

	/**
	 * 验证开始和结束时间，格式必须为yyyy-MM-dd或者yyyy/MM/dd，且开始时间要小于结束时间
	 */
	public static boolean validate(String start, String end) {
		// 这个正则匹配的是日期格式为:yyyy/MM/dd或者yyyy-MM-dd
		String rexp = "^(((\\d{2}(([02468][048])|([13579][26]))[\\-]((((0?[13578])|(1[02]))[\\-]((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-]((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-]((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-]((((0?[13578])|(1[02]))[\\-]((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-]((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-]((0?[1-9])|(1[0-9])|(2[0-8]))))))|"
				+ "((\\d{2}(([02468][048])|([13579][26]))[\\/]((((0?[13578])|(1[02]))[\\/]((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\/]((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\/]((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\/]((((0?[13578])|(1[02]))[\\/]((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\/]((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\/]((0?[1-9])|(1[0-9])|(2[0-8])))))))";
		Pattern p = Pattern.compile(rexp);
		Matcher startM = p.matcher(start);
		Matcher endM = p.matcher(end);

		if (startM.matches() == false || endM.matches() == false) {
			return false;

		} else {
			String sfh = start.charAt(4) + "";
			String efh = end.charAt(4) + "";
			String[] startTime = start.split(sfh);
			String[] endTime = end.split(efh);

			Calendar startC = Calendar.getInstance();
			startC.set(Integer.parseInt(startTime[0]), Integer.parseInt(startTime[1]), Integer.parseInt(startTime[2]));

			Calendar endC = Calendar.getInstance();
			endC.set(Integer.parseInt(endTime[0]), Integer.parseInt(endTime[1]), Integer.parseInt(endTime[2]));
			boolean b = startC.before(endC) || startC.equals(endC);
			if (b) {
				return true;
			} else {
				return false;
			}
		}
	}

	public static boolean isMobile(String phone) {
		Pattern p = null;
		Matcher m = null;
		boolean b = false;
		p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$");
		m = p.matcher(phone);
		b = m.matches();
		return b;
	}

	public static boolean isPhone(String phone) {
		Pattern p1 = null, p2 = null;
		Matcher m = null;
		boolean b = false;
		p1 = Pattern.compile("^[0][1-9]{2,3}-[0-9]{5,10}$");
		p2 = Pattern.compile("^[1-9]{1}[0-9]{5,8}$");
		if (phone.length() > 0) {
			m = p1.matcher(phone);
			b = m.matches();
		} else {
			m = p2.matcher(phone);
			b = m.matches();
		}
		return b;
	}

}
