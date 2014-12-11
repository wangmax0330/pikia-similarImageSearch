package com.pikia.app.util;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pikia.app.domain.ImageTaskDetail;

public class Const {
	public static Integer need2do = 0;
	public static List<ImageTaskDetail> allTaskLs = new ArrayList();
	public static List<ImageTaskDetail> taskDoneLs = new ArrayList();
	public static Date lastQuery;

	public static boolean autoDeal = false;

	public static Date lastWhile;

	public static String imageUrl;
	public static Map<String, String> amountTypes = new HashMap();
}
