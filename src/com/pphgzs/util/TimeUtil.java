package com.pphgzs.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;

public class TimeUtil {
	static SimpleDateFormat formatter;

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

	// @Test
	// public void ttt() {
	// String start = "2012-02-01";
	// String end = "2012-03-02";
	// List<String> lDate = null;
	// try {
	// lDate = findDates(start, end);
	// } catch (ParseException e) {
	// e.printStackTrace();
	// }
	// for (String date : lDate) {
	// System.out.println(date);
	// }
	// }

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
		return lDate;
	}
}
