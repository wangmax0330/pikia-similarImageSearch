package com.pikia.app.service;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public abstract interface SessionService {
	public abstract <T> T getCurrentUser(HttpServletRequest paramHttpRequest,
			Class<T> paramClass);

	public void setCurrentUser(HttpServletRequest request, Object obj);

	public abstract <T> T getCurrentUser(HttpSession paramHttpSession,
			Class<T> paramClass);

	public abstract void setCurrentUser(HttpSession paramHttpSession,
			Object paramObject);

	public abstract void setCurrentUserId(HttpSession paramHttpSession,
			Long paramLong);

	public abstract Long getCurrentUserId(HttpSession paramHttpSession);

	public abstract Locale getCurrentLocal(
			HttpServletRequest paramHttpServletRequest);

	public abstract Locale getCurrentLocal(HttpSession paramHttpSession);

	public abstract void setCurrentLocal(
			HttpServletRequest paramHttpServletRequest, String paramString);

	public abstract void setCurrentLocal(HttpSession paramHttpSession,
			String paramString);
}