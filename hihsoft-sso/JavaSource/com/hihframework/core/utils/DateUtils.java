package com.hihframework.core.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.log4j.Logger;

/**
 * <p> Title:取得系统当前日期、当前时间、当前年、当前月、当前季度、格式化日期处理</p>
 * <p> Description:日期 时间处理工具类</p>
 * <p> Copyright: Copyright (c) 2013 </p>
 * <p> Company:hihsoft.co.,ltd </p>
 *
 * @author hihsoft.co.,ltd
 * @version 1.0
 */

public class DateUtils {
	protected static final Logger log = Logger.getLogger(DateUtils.class);

	private static String defaultDatePattern = "yyyy-MM-dd";
	
	public static final String FM_DATE_AND_TIME = "yyyy-MM-dd HH:mm:ss";

	public final static String FM_PATTERN_CN_MD_HM = "MM月dd日 HH:mm";

	public final static String FM_PATTERN_CN_MD_NO = "MM月dd日";

	public final static String FM_PATTERN_CN_YMD_HM = "yyyy年MM月dd日 HH:mm";

	public final static String FM_PATTERN_CN_YMD_NO = "yyyy年MM月dd日";

	public final static String FM_PATTERN_CN_YM_NO = "yyyy年MM月";

	public final static String FM_PATTERN_EN_MD_HM = "MM-dd HH:mm";

	public final static String FM_PATTERN_EN_MD_NO = "MM-dd";

	public final static String FM_PATTERN_EN_YMD_HM = "yyyy/MM/dd HH:mm";

	public final static String FM_PATTERN_EN_YMD_NO = "yyyy/MM/dd";

	public final static String FM_PATTERN_EN_YM_NO = "yyyy/MM";

	public final static String[] WeekCn = { "星期日", "星期一", "星期二", "星期三", "星期四",
			"星期五", "星期六" };

	public final static String[] WeekEn = { "Sunday", "Monday", "Tuesday",
			"Wednsday", "Thursday", "Friday", "Saturday" };

	public final static String[] MonthCn = { "一", "二", "三", "四", "五", "六", "七",
			"八", "九", "十", "十一", "十二" };

	public final static String[] NumberCnS = { "〇", "一", "二", "三", "四", "五",
			"六", "七", "八", "九", "十" };

	public final static String[] NumberCnF = { "零", "壹", "贰", "叁", "肆", "伍",
			"陆", "柒", "捌", "玖", "拾" };

	public final static String[] MonthEn = { "January", "February", "March",
			"April", "May", "June", "July", "August", "September", "October",
			"November", "December" };

	public final static Calendar cal = Calendar.getInstance();

	// 不能外部实例化
	private DateUtils() {

	}

	public static String getDateFormatStr(String formart) {
		return new SimpleDateFormat(formart).format(new java.util.Date());
	}

	/**
	 * 取得当前日期
	 *
	 * @return 当前日期
	 */
	public static java.sql.Date getNowDate() {
		Calendar cal = Calendar.getInstance();
		return new java.sql.Date(cal.getTimeInMillis());
	}

	/**
	 * 取得当前时间
	 *
	 * @return 当前时间
	 */
	public static java.sql.Timestamp getNowTimestamp() {
		Calendar cal1 = Calendar.getInstance();
		return new java.sql.Timestamp(cal1.getTimeInMillis());
	}

	public static String formatDateLong(long millis) {
		String pattern = "yyyy-MM-dd HH:mm";
		String date = DateFormatUtils.format(millis, pattern, Locale.CHINA);
		return date;
	}

	/**
	 * 格式化输入的日期 时间
	 *
	 * @param date 日期 时间
	 * @return 日期
	 */
	public static java.sql.Date getDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return new java.sql.Date(cal.getTimeInMillis());
	}

	/**
	 * 把字符串形式的日期 转换为 date类型
	 *
	 * @param dateString
	 * @return 日期
	 */
	public static java.sql.Date getStringDateToDate(String dateString) {
		return java.sql.Date.valueOf(dateString);
	}

	// 格式化日期时间 yyyy年MM月dd日 HH时mm分ss秒
	public static String fotmatDateToYMDHMS(Date myDate) {
		SimpleDateFormat formatter = new SimpleDateFormat(
				"yyyy年MM月dd日 HH时mm分ss秒");
		String strDate = formatter.format(myDate);
		return strDate;
	}

	// 格式化日期 yyyy年MM月dd日
	public static String fotmatDateToyyMd(Date myDate) {
		SimpleDateFormat formatter = new SimpleDateFormat(FM_PATTERN_CN_YMD_NO);
		String strDate = formatter.format(myDate);
		return strDate;
	}

	/**
	 * 当前日期，时间转换为长整型
	 * 
	 * @return 长整型数字
	 */
	public static long getNowDateTimeLong() {
		Calendar cal = Calendar.getInstance();
		return cal.getTimeInMillis();
	}

	// 格式化时间 yyyy-MM-dd HH:mm:ss
	public static String fotmatDateTOyMdHms(Date myDate) {
		String strDate = null;
		if (myDate == null)
			strDate = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		strDate = formatter.format(myDate);
		return strDate;
	}

	// 格式化日期时间 yyyy-MM-dd
	public static String fotmatDateTOymd(Date myDate) {
		String strDate = null;
		if (myDate == null)
			strDate = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		strDate = formatter.format(myDate);
		return strDate;
	}

	// 格式化日期时间 yyyy/MM/dd
	public static String fotmatDateToyMd(Date myDate) {
		SimpleDateFormat formatter = new SimpleDateFormat(FM_PATTERN_EN_YMD_NO);
		String strDate = formatter.format(myDate);
		return strDate;
	}

	// 格式化日期时间 MM-dd HH:mm
	public static String fotmatDateToMdHm(Date myDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd HH:mm");
		String strDate = formatter.format(myDate);
		return strDate;
	}

	// 格式化日期时间 yyyyMMdd HH:mm:ss
	public static String fotmatDateToymdHms(Date myDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
		String strDate = formatter.format(myDate);
		return strDate;
	}

	/**
	 * 将java.sql.Date类对象转换为java.sql.Timestamp类对象， 时间部分为"00:00:00.000"
	 *
	 * @param date java.sql.Date 要转换的Date型对象
	 * @return java.sql.Timestamp 转换后的Timestamp型对象
	 */
	public static Timestamp convertDateToTimestampMin(java.sql.Date date) {
		return Timestamp.valueOf(date.toString() + " 00:00:00.000");
	}

	/**
	 * 将java.sql.Date类对象转换为java.sql.Timestamp类对象， 时间部分为"23:59:59.999"
	 *
	 * @param date java.sql.Date 要转换的Date型对象
	 * @return java.sql.Timestamp 转换后的Timestamp型对象
	 */
	public static Timestamp convertDateToTimestampMax(java.sql.Date date) {
		return Timestamp.valueOf(date.toString() + " 23:59:59.999");
	}

	/**
	 * 用于获取指定日期该月的所有日期
	 *
	 * @param date java.sql.Date 要获取的月日期列表的指定日期
	 * @return Date[] java.sql.Date 返回的日期列表
	 */
	public static java.sql.Date[] getMonthDays(java.sql.Date date) {

		Calendar cale = Calendar.getInstance();
		cale.setTime(date);

		int today = cale.get(Calendar.DAY_OF_MONTH);
		int days = cale.getActualMaximum(Calendar.DAY_OF_MONTH);
		long millis = cale.getTimeInMillis();

		java.sql.Date dates[] = new java.sql.Date[days];
		for (int i = 1; i <= days; i++) {
			long sub = (today - i) * 24 * 60 * 60 * 1000L;
			dates[i - 1] = new java.sql.Date(millis - sub);
		}

		cale = null;
		return dates;
	}

	/**
	 * 用于获取指定日期该周的所有日期
	 *
	 * @param date java.sql.Date 要获取的周日期列表的指定日期
	 * @return Date[] java.sql.Date 返回的日期列表
	 */
	public static java.sql.Date[] getWeekDays(java.sql.Date date) {

		Calendar cale = Calendar.getInstance();
		cale.setTime(date);
		cale.setFirstDayOfWeek(Calendar.SUNDAY);

		int days = 7;
		int today = cale.get(Calendar.DAY_OF_WEEK);
		long millis = cale.getTimeInMillis();

		java.sql.Date dates[] = new java.sql.Date[days];
		for (int i = 1; i <= days; i++) {
			long sub = (today - i) * 24 * 60 * 60 * 1000L;
			dates[i - 1] = new java.sql.Date(millis - sub);
		}

		cale = null;
		return dates;
	}

	/**
	 * 根据开始时间和结束时间返回相应的时间段字符串，如果开始时间和结束时间在同一天，
	 * 则返回的格式为“X点X分-X点X分”，如果不在同一天，返回的格式为“X月X日-X月X日”
	 *
	 * @param startTime 开始时间
	 * @param endTime   结束时间
	 * @return 返回的时间段字符串
	 */
	public static String getTimeSlice(Timestamp startTime, Timestamp endTime) {

		String rtn = "";

		Calendar caleStart = Calendar.getInstance();
		Calendar caleEnd = Calendar.getInstance();
		caleStart.setTimeInMillis(startTime.getTime());
		caleEnd.setTimeInMillis(endTime.getTime());

		String dayStart = caleStart.get(Calendar.YEAR) + "年"
				+ (caleStart.get(Calendar.MONTH) + 1) + "月"
				+ caleStart.get(Calendar.DAY_OF_MONTH) + "日";
		String dayEnd = caleEnd.get(Calendar.YEAR) + "年"
				+ (caleEnd.get(Calendar.MONTH) + 1) + "月"
				+ caleEnd.get(Calendar.DAY_OF_MONTH) + "日";

		if (dayStart.equals(dayEnd)) {
			// 同一天
			rtn = caleStart.get(Calendar.HOUR_OF_DAY) + "点"
					+ caleStart.get(Calendar.MINUTE) + "分-"
					+ caleEnd.get(Calendar.HOUR_OF_DAY) + "点"
					+ caleEnd.get(Calendar.MINUTE) + "分";
		} else {
			// 不在同一天
			rtn = (caleStart.get(Calendar.MONTH) + 1) + "月"
					+ caleStart.get(Calendar.DAY_OF_MONTH) + "日" + "-"
					+ (caleEnd.get(Calendar.MONTH) + 1) + "月"
					+ caleEnd.get(Calendar.DAY_OF_MONTH) + "日";
		}

		caleStart = null;
		caleEnd = null;
		return rtn;
	}

	/**
	 * 根据日期获得日期星期几格式的字符串，如“2004-07-28 星期三”
	 *
	 * @param date 指定的日期
	 * @return 返回的日期星期几格式的字符串
	 */
	public static String getDayWeek(java.sql.Date date) {

		String days[] = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar cale = Calendar.getInstance();
		cale.setTime(date);
		cale.setFirstDayOfWeek(Calendar.SUNDAY);

		return date.toString() + " " + days[cale.get(Calendar.DAY_OF_WEEK) - 1];
	}

	/**
	 * 获得指定日期所在月最小时间，时间部分为“00:00:00.000” 例如：param:2004-08-20 return:2004-08-01
	 * 00.00.00.000
	 *
	 * @param date 指定的日期
	 * @return 指定日期所在月的最小时间
	 */
	public static Timestamp getMinDayInMonth(java.sql.Date date) {

		Calendar cale = Calendar.getInstance();
		cale.setTime(date);
		cale.set(Calendar.DAY_OF_MONTH,
				cale.getActualMinimum(Calendar.DAY_OF_MONTH));
		java.sql.Date newDate = new java.sql.Date(cale.getTimeInMillis());

		cale = null;
		return Timestamp.valueOf(newDate.toString() + " 00:00:00.000");

	}

	/**
	 * 获得指定日期所在月的最大时间，时间部分为“23.59.59.999” 例如：param:2004-08-20,return:2004-08-31
	 * 23.59.59.999
	 *
	 * @param date
	 * @return
	 */
	public static Timestamp getMaxDayInMonth(java.sql.Date date) {

		Calendar cale = Calendar.getInstance();
		cale.setTime(date);
		cale.set(Calendar.DAY_OF_MONTH,
				cale.getActualMaximum(Calendar.DAY_OF_MONTH));
		java.sql.Date newDate = new java.sql.Date(cale.getTimeInMillis());

		cale = null;
		return Timestamp.valueOf(newDate.toString() + " 23:59:59.999");
	}

	// 得到当前年-月

	public static String getThisYearAndMonth() {
		String dateString = "";

		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
				"yyyy-MM");
		java.util.Date currentTime_1 = new java.util.Date();

		dateString = formatter.format(currentTime_1);

		return dateString;
	}

	// 得到当前年

	public static String getThisYear() {
		String dateString = "";

		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
				"yyyy");
		java.util.Date currentTime_1 = new java.util.Date();
		dateString = formatter.format(currentTime_1);

		return dateString;
	}

	// 得到当前月

	public static String getThisMonth() {

		String dateString = "";

		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
				"MM");
		java.util.Date currentTime_1 = new java.util.Date();
		dateString = formatter.format(currentTime_1);

		return dateString;
	}

	// 得到当前季度
	public static String getThisQuarter() {

		String quarter = "";
		String month = getThisMonth();

		if (month.equals("01") || month.equals("02") || month.equals("03")) {
			quarter = "第一季度";

		} else if (month.equals("04") || month.equals("05")
				|| month.equals("06")) {
			quarter = "第二季度";

		} else if (month.equals("07") || month.equals("08")
				|| month.equals("09")) {
			quarter = "第三季度";

		} else {
			quarter = "第四季度";

		}

		return quarter;
	}

	// 将字符串日期转换成Date类型
	// Date d = DateFormat("2001-02-17");
	public static Date toDate(String datetime) {
		String[] dt = SplitString(datetime, "-");

		if ((dt == null) || (dt.length != 3)) {
			return null;
		}

		int[] idt = new int[3];

		for (int i = 0; i < idt.length; i++) {
			idt[i] = StringHelpers.toInt(dt[i]);
		}

		java.util.Calendar c = java.util.Calendar.getInstance();
		c.set(idt[0], idt[1] - 1, idt[2]);

		return new Date(c.getTime().getTime());
	}

	public static Date DateFormat(String datetime) {
		String[] dt = SplitString(datetime, "-");

		if ((dt == null) || (dt.length != 3)) {
			return null;
		}

		int[] idt = new int[3];

		for (int i = 0; i < idt.length; i++) {
			idt[i] = StringHelpers.toInt(dt[i]);
		}

		java.util.Calendar c = java.util.Calendar.getInstance();
		c.set(idt[0], idt[1] - 1, idt[2]);

		return new Date(c.getTime().getTime());
	}

	// Date d = DateFormat("yyyy-MM-dd","2001-02-17");
	// Date d = DateFormat("yyyyMMdd","20010217");
	public static Date DateFormat(String format, String datetime) {
		SimpleDateFormat sdf = null;
		Date d = null;

		try {
			sdf = new SimpleDateFormat(format, Locale.CHINA);
			d = (java.sql.Date) sdf.parse(datetime);
		} catch (Exception e) {
			log.info(e);
		}

		return d;
	}

	/**
	 * 计算出两个日期段之间的每一个月的分段日期列表
	 * start：开始日期
	 * end：结束日期
	 * 返回：两个日期段之间的月份分隔日期列表。
	 * 如：2002-05-23至2002-07-15两个日期段之间的日期分隔列表是2002-05-23、2002-06-01、2002-07-15
	 * 注意：开始日期必须小于结束日期。
	 */
	public static Date[][] getMonthDividedDateList(java.sql.Date startDate,
			java.sql.Date endDate) {
		Date[][] result = new Date[0][2];

		if ((startDate != null) && (endDate != null)) {
			Date start = (startDate.getTime() > endDate.getTime()) ? endDate
					: startDate;
			Date end = (startDate.getTime() < endDate.getTime()) ? endDate
					: startDate;
			java.util.Vector v = new java.util.Vector();

			// 计算开始日期的月份
			cal.setTime(new java.util.Date(start.getTime()));

			int month = cal.get(Calendar.MONTH);

			// 计算结束日期的时间
			cal.setTime(new java.util.Date(end.getTime()));

			long endtime = end.getTime();
			long tmptime = 0;
			v.add(start);

			do {
				// 取当月最后一天
				cal.set(Calendar.MONTH, month);
				cal.set(Calendar.DAY_OF_MONTH,
						cal.getActualMaximum(Calendar.DAY_OF_MONTH));
				tmptime = cal.getTime().getTime();

				if (tmptime <= endtime) {
					v.add(new Date(tmptime));

					// 取下月第一天
					cal.set(Calendar.MONTH, month + 1);
					cal.set(Calendar.DAY_OF_MONTH, 1);
					tmptime = cal.getTime().getTime();

					if (tmptime <= endtime) {
						v.add(new Date(tmptime));
					}
				}

				month++;
			} while (tmptime < endtime);

			v.add(end);
			result = new Date[v.size() / 2][2];

			for (int i = 0; i < result.length; i++) {
				result[i][0] = (Date) v.get((i * 2) + 0);
				result[i][1] = (Date) v.get((i * 2) + 1);
			}

			v.removeAllElements();
		}

		return result;
	}

	// 取当前日期
	public static Date getTodayDate() {
		long date = System.currentTimeMillis();
		java.sql.Date result = new java.sql.Date(date);

		return result;
	}

	// 比较两个日期是否按天相等
	public static boolean isDateEqualsByDay(Date date1, Date date2) {
		if ((date1 == null) || (date2 == null)) {
			return false;
		}

		return DateUtils.getDateDays(date1) == DateUtils.getDateDays(date2);
	}

	// 比较某个日期是否在两个日期之间，日期值不为空。
	public static boolean isDateInPeriod(Date date, Date periodStart,
			Date periodEnd) {
		if ((date == null) || (periodStart == null) || (periodEnd == null)) {
			return false;
		}

		long d = DateUtils.getDateDays(date);
		long s = DateUtils.getDateDays(periodStart);
		long e = DateUtils.getDateDays(periodEnd);

		return ((d >= s) && (d <= e));
	}

	// 取日期按天的数值；本地时区为+8所以要减去8小时
	public static long getDateDays(Date date) {
		if (date == null) {
			return -1;
		}

		return (long) ((date.getTime() + (1000 * 3600 * 8)) / (1000 * 3600 * 24));

		// return date.getTime();
	}

	// 将日期转换成字符串
	// datetype,timetype,weektype=0：不显示
	// sorttype：排列次序
	// con：连接符号，null表示不留间隔，"-",".","/"," "都有可能
	// public static String getDateString(Date date,int datetype,int
	// timetype,int weektype,int sorttype,String con){
	public static String getDateString(java.util.Date date) {
		if (date == null) {
			return "";
		}

		String result = "";

		cal.setTime(new java.util.Date(date.getTime()));

		int _year = cal.get(Calendar.YEAR);
		int _month = cal.get(Calendar.MONTH) + 1;
		int _date = cal.get(Calendar.DATE);
		int _week = cal.get(Calendar.DAY_OF_WEEK) - 1;
		result += (_year + "年" + _month + "月" + _date + "日" + " " + WeekCn[_week]);

		return result;
	}

	// 将字符串按照规则进行分段，如将"2001-10-12"按照"-"划分，则分为"2001"、"10"和"12"三段
	public static String[] SplitString(String szSource, String token) {
		if ((szSource == null) || (token == null)) {
			return null;
		}

		StringTokenizer st1 = new StringTokenizer(szSource, token);
		String[] d1 = new String[st1.countTokens()];

		for (int x = 0; x < d1.length; x++)
			if (st1.hasMoreTokens()) {
				d1[x] = st1.nextToken();
			}

		return d1;
	}

	// "2000-01-02" -> "20000102"
	public static String getDateString(java.sql.Date dd) {
		if (dd == null) {
			return "";
		}

		String tds = dd.toString();
		String temp = "";

		for (int x = 0; x < tds.length(); x++) {
			if (tds.charAt(x) != '-') {
				temp = temp + tds.charAt(x);
			}
		}

		return temp;
	}

	public static String getStr(String str) {
		return str == null ? "" : str;
	}

	// "2000-01-02" -> "2000-01-02 8:30:00"
	public static String getDateTimeString(java.sql.Date dd) {
		if (dd == null) {
			return "";
		}

		String tds = dd.toString();
		java.sql.Time tt = new java.sql.Time(dd.getTime());
		tds += (" " + tt.toString());

		return tds;
	}

	// 取得当前时间格式 09:20:10
	public static String getCurrTime() {
		Date currdate = getTodayDate();
		java.sql.Time tt = new java.sql.Time(currdate.getTime());

		return tt.toString();
	}

	// "2000-01-02" -> "2000-01-02 8:30"

	/*
	 * public static String getShortDateTimeString(java.sql.Date dd){ if
	 * (dd==null) return ""; String tds=dd.toString(); java.sql.Time tt=new
	 * java.sql.Time(dd.getTime()); tds+=" "+tt.toString(); int
	 * x=tds.lastIndexOf(":"); tds=tds.substring(0,x); return tds; }
	 */

	// 日期按天累加
	public static Date getDateAddDay(java.sql.Date date, long days) {
		if (date == null) {
			return null;
		}

		long times = date.getTime();

		for (int i = 0; i < (int) (days / 10); i++)
			times += (10 * 60 * 60 * 1000 * 24);

		times += ((days % 10) * 60 * 60 * 1000 * 24);

		java.sql.Date dret = new java.sql.Date(times); // 有问题

		return dret;
	}

	// 日期按天减
	public static Date getDateSubDay(java.sql.Date date, int days) {
		if (date == null) {
			return null;
		}

		long times = date.getTime();

		for (int i = 0; i < (int) (days / 10); i++)
			times += ((-1) * 10 * 60 * 60 * 1000 * 24);

		times += ((-1) * (days % 10) * 60 * 60 * 1000 * 24);

		java.sql.Date dret = new java.sql.Date(times); // 有问题

		return dret;
	}

	// 日期按月累加
	public static Date getDateAddMonth(java.sql.Date date, int months) {
		if (date == null) {
			return null;
		}

		cal.setTime(new java.util.Date(date.getTime()));

		int month = cal.get(java.util.Calendar.MONTH);
		cal.set(java.util.Calendar.MONTH, month + months);

		return new java.sql.Date(cal.getTime().getTime());
	}

	// 设置日期中的参数，可以对年、月、日、时、分、秒等进行设置
	public static void setDateParameter(java.sql.Date date, int param, int value) {
		if (date == null) {
			return;
		}
		cal.setTime(new java.util.Date(date.getTime()));
		cal.set(param, value);
		date.setTime(cal.getTime().getTime());
	}

	// 取日期中的参数，可以对年、月、日、时、分、秒等进行读取
	public static int getDateParameter(java.sql.Date date, int param) {
		if (date == null) {
			return -1;
		}
		cal.setTime(new java.util.Date(date.getTime()));

		return cal.get(param);
	}

	/**
	 * ************  Extend ***********
	 */
	public static java.sql.Timestamp toCalendarDate(String date, String time) {
		if ((date != null) && (date.length() >= 9) && (time != null)
				&& !(time.equals(""))) {
			time = time.trim();

			String hour = time.substring(0, time.indexOf(":"));
			String min = time.substring(time.indexOf(":") + 1, time.length());
			int h = StringHelpers.toInt(hour);
			int m = StringHelpers.toInt(min);
			cal.setTime(toDate(date));
			cal.set(Calendar.HOUR_OF_DAY, h);
			cal.set(Calendar.MINUTE, m);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);

			java.sql.Timestamp d = new java.sql.Timestamp(cal.getTime()
					.getTime());

			return d;
		} else {
			return null;
		}
	}

	// 2002-09-23 09:30:00
	public static String getCalendarModel(java.sql.Timestamp date) {
		if (date != null) {
			return getHours(date.toString());
		} else {
			return null;
		}
	}

	// 2002--09-23 09:30:00 -->09:30
	public static String getHours(String str) {
		if (str != null) {
			String tmpStr = str.trim();
			int pos = tmpStr.lastIndexOf(":");
			str = str.substring(pos - 5, pos);

			return str;
		} else {
			return null;
		}
	}

	// *2*3*4*--->Collection (2,3,4)
	public static Collection getColString(String str, String str1) {
		if ((str != null) && (str.length() > 2)) {
			Collection col = new ArrayList();
			int len = str.length();

			while (len > 2) {
				int last = str.lastIndexOf(str1);
				String tmp = str.substring(0, last - 1);
				int sec = tmp.lastIndexOf(str1);
				String num = str.substring(sec + 1, last);
				str = str.substring(0, sec + 1);
				len = str.length();
				col.add(num);
			}

			return col;
		}

		return null;
	}

	public static Collection getAllYearForDay(String yy, String mm,
			Collection incol, int start, int len, String op) {
		Collection col = null;

		if ((incol != null) && (incol.size() > 0)) {
			col = new java.util.ArrayList();

			java.util.Iterator it = incol.iterator();

			while (it.hasNext()) {
				String whichday = (String) it.next();

				if (whichday.length() == 1) {
					whichday = "0" + whichday;
				}

				if (len > 0) {
					int curryear = start;

					for (int i = 1; i <= len; i++) {
						curryear++;

						if (op.equals("Y")) {
							String sDay = curryear + "-" + mm + "-" + whichday;

							if ((mm == "02") && (whichday == "29")) {
								if ((((curryear % 4) == 0) && ((curryear % 100) != 0))
										|| ((curryear % 400) == 0)) {
									col.add(sDay);
								}
							} else {
								col.add(sDay);
							}
							// if(mm)
						} else {
							for (int j = 1; j <= 12; j++) {
								String msDay = null;

								if (j < 10) {
									msDay = curryear + "-0" + j + "-"
											+ whichday;
								} else {
									msDay = curryear + "-" + j + "-" + whichday;
								}

								if ((mm == "02") && (whichday == "29")) {
									if ((((curryear % 4) == 0) && ((curryear % 100) != 0))
											|| ((curryear % 400) == 0)) {
										col.add(msDay);
									}
								} else {
									col.add(msDay);
								}
								// if(mm)
							}
						}
					}
					// for
				}
				// if(len)
			}
			// while
		}
		// incol

		log.info("-----col.size=" + col.size());

		return col;
	}

	public static long getDiffDate(Date newdate, Date olddate) {
		return getDateDays(newdate) - getDateDays(olddate);
	}

	public static Collection getAllWeekForDay(String yy, String mm,
			Collection incol, int start, int len) {
		Collection col = null;
		String max = "2010-12-31";
		long lMax = toDate(max).getTime();
		long span = 7 * 24 * 60 * 60 * 1000;

		if ((incol != null) && (incol.size() > 0)) {
			col = new ArrayList();

			java.util.Iterator weekit = incol.iterator();

			while (weekit.hasNext()) {
				String strDay = (String) weekit.next();

				if (strDay.length() == 1) {
					strDay = "0" + strDay;
				}

				strDay = yy + "-" + mm + "-" + strDay;

				java.util.Date date = toDate(strDay);
				long tt = date.getTime();
				java.sql.Timestamp stamp = new java.sql.Timestamp(tt);
				col.add(stamp);

				while (tt <= lMax) {
					tt = tt + span;
					stamp = new java.sql.Timestamp(tt);
					col.add(stamp);
				}
			}
		}

		return col;
	}

	/*
	 * 功能: 判断是否是闰年. 输入: year = 给出的 1582 年以后的年份. 输出: TRUE 是闰年, FALSE 不是.
	 */
	public static boolean isLeapYear(int year) {
		/*
		 * 能被100整除, 不能被400整除的年份, 不是闰年. 能被100整除, 也能被400整除的年份, 是闰年.
		 */
		if ((year % 100) == 0) {
			return ((year % 400) == 0);
		}

		/* 不能被100整除, 能被4整除的年份是闰年. */
		return ((year & 4) == 0);
	}

	/*
	 * 功能: 将字符串转换成HTML格式的字串 输入: String 输出: String
	 */
	public static String toHtmlString(String input) {
		if ((input == null) || (input.trim().length() == 0)) {
			return input;
		}

		StringBuffer result = new StringBuffer();
		char tmp;
		char lstchar = ' ';

		for (int i = 0; i < input.length(); i++) {
			tmp = input.charAt(i);

			if (((int) tmp) == 13) {
				result.append("<br>");
				lstchar = tmp;
			} else if (((int) tmp) == 10) {
				if (((int) lstchar) == 13) {
					result.append("");
				} else {
					result.append("<br>");
				}
			} else {
				result.append(input.charAt(i));
			}
		}

		return result.toString();
	}

	/*
	 * 功能: 将字符串转换成HTML格式的字串 输入: String 输出: String
	 */
	public static String toHtmlScriptString(String input) {
		if ((input == null) || (input.trim().length() == 0)) {
			return input;
		}

		StringBuffer result = new StringBuffer();
		char tmp;
		char lstchar = ' ';

		for (int i = 0; i < input.length(); i++) {
			tmp = input.charAt(i);

			if (((int) tmp) == 13) {
				result.append("<br>");
				lstchar = tmp;
			} else if (((int) tmp) == 10) {
				if (((int) lstchar) == 13) {
					lstchar = ' ';
				} else {
					result.append("<br>");
				}
			} else if (tmp == '\'') {
				result.append("\\'");
			} else if (tmp == '\"') {
				result.append("\\\"");
			} else {
				result.append(input.charAt(i));
			}
		}

		return result.toString();
	}

	public static Date getNextMonthFirstDate(Date date) {
		Date tmpdate = getMonthFirstDate(date);
		cal.setTime(tmpdate);
		cal.add(Calendar.MONTH, 1);
		return new Date(cal.getTime().getTime());
	}

	public static Date getMonthFirstDate(Date date) {
		Date tmpdate = new Date(date.getTime());
		return tmpdate;
	}

	public static Date getMonthLastDate(Date date) {
		cal.setTime(date);
		cal.add(Calendar.MONTH, 1);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		Date tmpdate1 = new Date(cal.getTime().getTime());
		return tmpdate1;

	}

	public static Date getDateDiff(Date date, int day) {
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, day);
		Date tmpdate = new Date(cal.getTime().getTime());
		return tmpdate;
	}

	// 如果当前日期大于15号就取下个月一号，否则取本月一号
	public static Date getFirstDateByDate(Date date) {
		int curday = DateUtils.getNowDate().getDate();
		if (curday > 15)
			return getNextMonthFirstDate(date);
		else
			return getMonthFirstDate(date);
	}

	/**
	 * 当前日期，时间转换为长整型
	 * 作用用来生成随机数
	 *
	 * @return 长整型数字
	 */
	public static long getNowDateTimeToLong() {
		return cal.getTimeInMillis();
	}

	public static void main(String[] args) {
		log.info("系统当前日期" + DateUtils.getNowTimestamp());

	}

	// private static final Logger logger = Logger.getLogger(DateUtil.class);

	/**
	 * 获得默认的 date pattern
	 */
	public static String getDatePattern() {
		return defaultDatePattern;
	}

	public static String getDateTimePattern() {
		return DateUtils.getDatePattern() + " HH:mm:ss.S";
	}

	/**
	 * 返回预设Format的当前日期字符串
	 */
	public static String getToday() {
		Date today = new Date();
		return format(today);
	}

	/**
	 * 使用预设Format格式化Date成字符串
	 */
	public static String format(Date date) {
		return date == null ? "" : format(date, getDatePattern());
	}

	/**
	 * 使用参数Format格式化Date成字符串
	 */
	public static String format(Date date, String pattern) {
		return date == null ? "" : new SimpleDateFormat(pattern).format(date);
	}

	/**
	 * 使用预设格式将字符串转为Date
	 */
	public static Date parse(String strDate) throws ParseException {
		return StringUtils.isBlank(strDate) ? null : parse(strDate,
				getDatePattern());
	}

	/**
	 * 使用参数Format将字符串转为Date
	 */
	public static Date parse(String strDate, String pattern)
			throws ParseException {
		return StringUtils.isBlank(strDate) ? null : new SimpleDateFormat(
				pattern).parse(strDate);
	}

	/**
	 * 在日期上增加数个整月
	 */
	public static Date addMonth(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, n);
		return cal.getTime();
	}

	/**
	 * 在日期上增加数个整天
	 */
	public static Date addDay(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, n);
		return cal.getTime();
	}

	/**
	 * 在日期上增加数个小时
	 */
	public static Date addHour(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR_OF_DAY, n);
		return cal.getTime();
	}

	/**
	 * 在日期上增加数个分钟
	 */
	public static Date addMinute(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, n);
		return cal.getTime();
	}

	/**
	 * 得到两个日期时间的差额
	 */
	public static long betDate(Date date, Date otherDate) {
		return date.getTime() - otherDate.getTime();
	}

	/**
	 * 以年，月，日来构造一个日子
	 */
	public static Date makeDate(int year, int month, int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(1, year);
		calendar.set(2, month - 1);
		calendar.set(5, day);
		calendar.set(11, 0);
		calendar.set(12, 0);
		calendar.set(13, 0);
		calendar.set(14, 0);
		return calendar.getTime();
	}

	/**
	 * 以年，月，日，小时，分钟，秒来构造一个日子
	 */
	public static Date makeDate(int year, int month, int day, int hour,
			int minute, int second) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(1, year);
		calendar.set(2, month - 1);
		calendar.set(5, day);
		calendar.set(11, hour);
		calendar.set(12, minute);
		calendar.set(13, second);
		calendar.set(14, 0);
		return calendar.getTime();
	}

	/**
	 * 返回指定日期是哪一月
	 */
	public static int getMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(2) + 1;
	}

	/**
	 * 返回指定日期是哪一年
	 */
	public static int getYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(1);
	}

	/**
	 * 返回指定日期是哪一年
	 */
	public static int getDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(5);
	}

	/**
	 * 返回指定日期是周几
	 */
	public static int getWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int week = calendar.get(Calendar.DAY_OF_WEEK);

		if (week == 1)
			return 7;
		else
			return week - 1;

	}

	/*
	 * 返回指定日期是几点
	 */
	public static int getHour(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.HOUR_OF_DAY);

	}

	/**
	 * 获得更定日期所在月的最后一天,可能29,30,31,28等
	 */
	public static Date getLastDateOfMonth(Date date) {
		int year = DateUtils.getYear(date);
		int month = DateUtils.getMonth(date);
		return DateUtils.addDay(
				DateUtils.addMonth(DateUtils.makeDate(year, month, 1), 1), -1);

	}

	/**
	 * 获得指定日期所在月的的第一天
	 */
	public static Date getFirstDateOfMonth(Date date) {
		int year = DateUtils.getYear(date);
		int month = DateUtils.getMonth(date);
		return DateUtils.makeDate(year, month, 1);
	}

	/**
	 * 先得到指定日期所在周,再得到当周指定周几的日期,resultWeek用于指定周几,取值范围1-7
	 */
	public static Date getDayOfWeek(Date date, int resultWeek) {
		if (resultWeek < 1 || resultWeek > 7)
			throw new IllegalArgumentException("resultWeek must in 1-7");
		int week = DateUtils.getWeek(date);
		return DateUtils.addDay(date, resultWeek - week);
	}

	/**
	 * 根据年龄取得对于的出生时间
	 */
	public static String getBirthday(String age) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.YEAR, -Integer.parseInt(age));
		SimpleDateFormat format = new SimpleDateFormat(defaultDatePattern);
		return format.format(c.getTime());
	}

	/**
	 * 根据生日得到年龄
	 */
	public static Integer getAge(String year) {
		Calendar c = Calendar.getInstance();
		if (year == null) {
			return 20;
		}
		year = year.substring(0, 4);
		Integer age = c.get(Calendar.YEAR) - Integer.parseInt(year);
		return age;
	}

	/**
	 * 年龄转化为：integer
	 */
	public static Integer getBirthday1(Integer age) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.YEAR, -age);
		c.set(Calendar.MONTH, 0);
		c.set(Calendar.DAY_OF_YEAR, 0);

		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		return Integer.parseInt(format.format(c.getTime()));
	}

	/**
	 * 得到以当前时间为基数的序列号，方法为同步安全的
	 * 
	 * @return
	 */
	public synchronized static Long getSerialNum() {
		Long result = new Long(DateUtils.format(new Date(), "yyyyMMddHHmmssS"));
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// logger.error(e);
			throw new RuntimeException(e);
		}
		return result;
	}
	/**
	 * 取得当前时间，精确到时分秒
	 * @return
	 * @author Xiaojf
	 * @since 2011-9-14
	 */
	public static String getNowDateTime() {
		return format(getNowDate(), DateUtils.FM_DATE_AND_TIME);
	}
}
