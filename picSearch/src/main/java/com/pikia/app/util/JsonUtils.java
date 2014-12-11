package com.pikia.app.util;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.apache.commons.beanutils.BeanUtils;

public class JsonUtils {
	public static String getJsonResult(String isSuc, String msg,
			String[] paramValues) {
		String arrs = ":[";
		if (paramValues != null) {
			for (int i = 0; i < paramValues.length; i++) {
				if (i > 0)
					arrs = arrs + ",";
				arrs = arrs + "\"" + paramValues[i] + "\"";
			}
		}
		arrs = arrs + "]";
		return "{\"isSuc\":\"" + isSuc + "\",\"msg\":\"" + msg + "\",\"attrs\""
				+ arrs + "}";
	}

	public static String JSON_Bean2StringWithFilter(Object ob,
			String[] filterFields) {
		// 通过jsonconfig实例，对包含和需要排除的java 对象属性进行方便的添加或删除
		JsonConfig j = new JsonConfig();
		j.setExcludes(filterFields);
		JSONObject jsonObj = JSONObject.fromObject(ob, j);
		return jsonObj.toString();
	}

	public static String JSON_Bean2String(Object ob) {
		return JSON_Bean2StringWithFilter(ob, null);
	}

	public static String JSON_Bean2StringWithFilter(Object ob) {
		JsonConfig j = new JsonConfig();
		try {
			// BeanUtils 反射技术调用对象属性s
			String[] ss = BeanUtils.getArrayProperty(ob, "filterFields");
			j.setExcludes(ss);
		} catch (Exception e) {
		}
		JSONObject jsonObj = JSONObject.fromObject(ob, j);
		return jsonObj.toString();
	}

	public static String JSON_List2String(Object ob) {
		return JSON_List2StringWithFilter(ob, null);
	}

	public static String JSON_List2StringWithFilter(Object ob,
			PropertyFilter filter) {
		JsonConfig j = new JsonConfig();
		if (filter != null)
			j.setJavaPropertyFilter(filter);
		JSONArray jsonArray = JSONArray.fromObject(ob, j);
		return jsonArray.toString();
	}

	public static String JSON_List2String(Object ob, String[] filterFields) {
		JsonConfig j = new JsonConfig();
		j.setExcludes(filterFields);
		JSONArray jsonArray = JSONArray.fromObject(ob, j);
		return jsonArray.toString();
	}

	public static Object JSON_String2Bean(String s, Class<?> beanClass) {
		JSONObject jsonObj = JSONObject.fromObject(s);
		return JSONObject.toBean(jsonObj, beanClass);
	}

	public static List JSON_String2List(String s, Class<?> beanClass) {
		JSONArray jsonArray = JSONArray.fromObject(s);
		List list = (List) JSONArray.toCollection(jsonArray, beanClass);
		return list;
	}

}
