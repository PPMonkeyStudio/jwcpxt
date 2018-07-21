package com.pphgzs.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;

/**
 * 
 * 获取某一时间段特定星期几的日期
 * 
 * @author finder.zhou
 * 
 */
public class WeekDayUtil {

	@Test
	public void tessss() {
		try {
			List<String> lll = getMonthBetween("2015-01-12", "2015-01-22");
			for (String string : lll) {
				System.out.println("String:" + string);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param minDate
	 *            最小时间 2015-01
	 * @param maxDate
	 *            最大时间 2015-10
	 * @return 日期集合 格式为 年-月
	 * @throws Exception
	 */
	public static List<String> getMonthBetween(String minDate, String maxDate) throws Exception {
		ArrayList<String> result = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// 格式化为年月

		Calendar min = Calendar.getInstance();
		Calendar max = Calendar.getInstance();

		min.setTime(sdf.parse(minDate));
		min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

		max.setTime(sdf.parse(maxDate));
		max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

		Calendar curr = min;
		while (curr.before(max)) {
			result.add(sdf.format(curr.getTime()));
			curr.add(Calendar.MONTH, 1);
		}
		if (result.size() == 1) {
			result.add(getFirstDayOfNextMonth(result.get(0), "yyyy-MM-dd"));
		}
		return result;
	}

	/**
	 * 获取指定日期下个月的第一天
	 * 
	 * @param dateStr
	 * @param format
	 * @return
	 */
	public static String getFirstDayOfNextMonth(String dateStr, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			Date date = sdf.parse(dateStr);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			calendar.add(Calendar.MONTH, 1);
			return sdf.format(calendar.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * 获取某一时间段特定星期几的日期
	 * 
	 * @param dateFrom
	 *            开始时间
	 * 
	 * @param dateEnd
	 *            结束时间
	 * 
	 * @param weekDays
	 *            星期
	 * 
	 * @return 返回时间数组
	 * 
	 */
	public static List<String> getDates(String dateFrom, String dateEnd, String weekDays) {
		long time = 1l;
		long perDayMilSec = 24 * 60 * 60 * 1000;
		List<String> dateList = new ArrayList<String>();
		dateList.add(dateFrom);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// 需要查询的星期系数
		String strWeekNumber = weekForNum(weekDays);
		try {
			dateFrom = sdf.format(sdf.parse(dateFrom).getTime() - perDayMilSec);
			while (true) {
				time = sdf.parse(dateFrom).getTime();
				time = time + perDayMilSec;
				Date date = new Date(time);
				dateFrom = sdf.format(date);
				if (dateFrom.compareTo(dateEnd) <= 0) {
					// 查询的某一时间的星期系数
					Integer weekDay = dayForWeek(date);
					// 判断当期日期的星期系数是否是需要查询的
					if (strWeekNumber.indexOf(weekDay.toString()) != -1) {
						dateList.add(dateFrom);
					}
				} else {
					break;
				}
			}
		} catch (ParseException e) {
			System.out.println("统计时间格式错误!");
			e.printStackTrace();
		}
		dateList.add(dateEnd);
		return dateList;
	}

	// 等到当期时间的周系数。星期日：1，星期一：2，星期二：3，星期三：4，星期四：5，星期五：6，星期六：7
	public static Integer dayForWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 
	 * 得到对应星期的系数 星期日：1，星期一：2，星期二：3，星期三：4，星期四：5，星期五：6，星期六：7
	 * 
	 * @param weekDays
	 *            星期格式 星期一|星期二
	 * 
	 */

	public static String weekForNum(String weekDays) {
		// 返回结果为组合的星期系数
		String weekNumber = "";
		// 解析传入的星期
		if (weekDays.indexOf("|") != -1) {// 多个星期数
			String[] strWeeks = weekDays.split("\\|");
			for (int i = 0; i < strWeeks.length; i++) {
				weekNumber = weekNumber + "" + getWeekNum(strWeeks[i]).toString();
			}
		} else {// 一个星期数
			weekNumber = getWeekNum(weekDays).toString();
		}
		return weekNumber;
	}

	// 将星期转换为对应的系数 星期日：1，星期一：2，星期二：3，星期三：4，星期四：5，星期五：6，星期六：7
	public static Integer getWeekNum(String strWeek) {
		Integer number = 1;// 默认为星期日
		if ("星期日".equals(strWeek)) {
			number = 1;
		} else if ("星期一".equals(strWeek)) {
			number = 2;
		} else if ("星期二".equals(strWeek)) {
			number = 3;
		} else if ("星期三".equals(strWeek)) {
			number = 4;
		} else if ("星期四".equals(strWeek)) {
			number = 5;
		} else if ("星期五".equals(strWeek)) {
			number = 6;
		} else if ("星期六".equals(strWeek)) {
			number = 7;
		}
		return number;
	}
}