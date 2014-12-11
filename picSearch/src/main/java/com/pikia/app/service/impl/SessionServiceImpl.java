package com.pikia.app.service.impl;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.pikia.app.service.SessionService;
import com.pikia.app.util.ConstUtils;

@Service
public class SessionServiceImpl implements SessionService {

	@Override
	public <T> T getCurrentUser(HttpServletRequest request, Class<T> paramClass) {
		return (T) getCurrentUser(request.getSession(), paramClass);
	}

	@Override
	public void setCurrentUser(HttpServletRequest request, Object obj) {
		setCurrentUser(request.getSession(), obj);
	}

	@Override
	public <T> T getCurrentUser(HttpSession session, Class<T> cls) {
		return (T) session.getAttribute(ConstUtils.SESSION_USER_KEY);
	}

	@Override
	public void setCurrentUser(HttpSession session, Object obj) {
		// if (obj != null) {
		// BaseBean user = (BaseBean) obj;
		// session.setAttribute(ConstUtils.SESSION_USER_KEY, user);
		// this.setCurrentUserId(session, user.getId());
		// } else {
		// session.removeAttribute(ConstUtils.SESSION_USER_KEY);
		// session.removeAttribute(ConstUtils.SESSION_USER_ID);
		// }
	}

	@Override
	public void setCurrentUserId(HttpSession session, Long uid) {
		session.setAttribute(ConstUtils.SESSION_USER_ID, uid);
	}

	@Override
	public Long getCurrentUserId(HttpSession session) {
		return (Long) session.getAttribute(ConstUtils.SESSION_USER_ID);
	}

	@Override
	public Locale getCurrentLocal(HttpServletRequest paramHttpServletRequest) {
		return null;
	}

	@Override
	public Locale getCurrentLocal(HttpSession session) {
		Locale locale = (Locale) session
				.getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME);
		return locale;// locale==null?new Locale(SESSION_DEFAULT_LOCALE):locale;
	}

	@Override
	public void setCurrentLocal(HttpServletRequest paramHttpServletRequest,
			String paramString) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setCurrentLocal(HttpSession session, String localKey) {
		session.setAttribute(
				SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME,
				new Locale(localKey));
	}
}
