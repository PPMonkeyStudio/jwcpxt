package com.pphgzs.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class TimeUtil {
	static SimpleDateFormat formatter;

	public static String longDateFormatDate(String time) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmm");
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

}
