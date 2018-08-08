package com.altamob.offertest.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 获得小时的0分 0秒
	 * @param num 当前小时推num个小时。0为当前小时  >0后推  <0 后推
	 */
	public static String getHour(int num) {
		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_TIME_FORMAT);
		Calendar c = Calendar.getInstance();
		c.add(Calendar.HOUR_OF_DAY, num);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);

		Date date = c.getTime();
		return sdf.format(date);
	}

	public static String dateToString(Date date, String patten) {
		if (org.apache.commons.lang.StringUtils.isBlank(patten)) {
			patten = DEFAULT_DATE_TIME_FORMAT;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(patten);
		return sdf.format(date);

	}
}
