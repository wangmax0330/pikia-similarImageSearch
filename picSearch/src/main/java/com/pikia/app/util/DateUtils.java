package com.pikia.app.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

public class DateUtils {
	public static final String DEFAULT_DATE_PATTERN = "yyy-MM-dd";
	public static final String DEFAULT_TIME_PATERN = "HH:mm:ss";
	public static final String DEFAULT_TIMESTAMP_PATTERN = "yyyy-MM-dd HH:mm:ss";

	public static Date str2Date(String str) {
		Date date = null;
		if (StringUtils.isNotBlank(str)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			try {
				date = sdf.parse(str);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return date;
	}

	public static String datetime2Str(java.util.Date date) {
		if (date == null)
			return "";
		return date2Str(date, "yyyy-MM-dd HH:mm:ss");
	}

	public static String date2Str(Date date) {
		if (date == null)
			return "";
		return date2Str(date, "yyyy-MM-dd");
	}

	public static String date2Str(Date date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

}
