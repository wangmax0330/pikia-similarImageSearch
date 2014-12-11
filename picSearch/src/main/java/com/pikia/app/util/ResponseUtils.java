package com.pikia.app.util;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

public class ResponseUtils {
	public static void writeJsonSuccessMessage(HttpServletResponse response,
			String message, String... paramValues) {
		writeMessage(response,
				JsonUtils.getJsonResult("1", message, paramValues));
	}

	public static void writeJsonErrorMessage(HttpServletResponse response,
			String errorMessage, String[] paramValues) {
		writeMessage(response,
				JsonUtils.getJsonResult("0", errorMessage, paramValues));
	}

	public static void writeJsonErrorMessage(HttpServletResponse response,
			String errorMessage) {
		writeMessage(response, JsonUtils.getJsonResult("0", errorMessage, null));

	}

	public static void writeGetJsonSuccessMessage(HttpServletResponse response,
			String message, String jsonmsg, boolean usejson, String callback) {
		String str = "{\"isSuc\":\"1\",\"msg\":\"" + message + "\",\"json\":"
				+ jsonmsg + "}";
		writeMessage(response, str, callback);
	}

	public static void writeGetJsonSuccesssMessage(
			HttpServletResponse response, String message, String jsonmsg,
			boolean usejson) {
		String str = "{\"isSuc\":\"1\",\"msg\":\"" + message + "\",\"json\":"
				+ jsonmsg + "}";
		writeMessage(response, str);
	}

	public static void writeGetJsonErrorMessage(HttpServletResponse response,
			String callback, String errorMessage, String[] parmValues) {
		writeMessage(response,
				JsonUtils.getJsonResult("0", errorMessage, parmValues),
				callback);
	}

	public static void writeMessage(HttpServletResponse response, String message) {
		writeMessage(response, message, null);
	}

	public static void writeMessage(HttpServletResponse response,
			String message, String callback) {
		try {
			response.setContentType("text/plain ; charset=UTF-8");
			if (StringUtils.isNotBlank(callback)) {
				response.getWriter().write(callback + "(" + message + ")");
			} else
				response.getWriter().write(message);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}
