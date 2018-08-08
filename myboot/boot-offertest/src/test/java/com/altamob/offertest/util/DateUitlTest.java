package com.altamob.offertest.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class DateUitlTest {

	@Test
	public void test() {
		String pattern = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Calendar c = Calendar.getInstance();
		c.add(Calendar.HOUR_OF_DAY, -1);
		
		Date date = c.getTime();
		System.out.println(sdf.format(date));

	}

	@Test
	public void getHour() {
		System.out.println(DateUtils.getHour(-1));
	}
}
