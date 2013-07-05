package com.hihsoft.sso.sysmonitor.syswatch.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hihsoft.baseclass.constants.Consts;

/**
 * <p> Title: 判断会话是否失效，否则重新登录</p>
 * <p> Description:</p>
 * <p> Copyright: Copyright (c) 2013 </p>
 * <p> Company:hihsoft.co.,ltd </p>
 *
 * @author hihsoft.co.,ltd
 * @version 1.0
 */
public class SessionInterceptor extends HandlerInterceptorAdapter {
	protected static Logger log = Logger.getLogger(SessionInterceptor.class);
	private String redirectTo;
	
	@SuppressWarnings("unchecked")
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String url = request.getRequestURI();
		if(url.endsWith("login") || url.endsWith("tsysLoginController/login"))
			return true;
		if(request.getSession() != null && request.getSession().getAttribute(Consts.USER_INFO) != null) 
			return true;
		response.sendRedirect(request.getContextPath() + redirectTo);	
		return super.preHandle(request, response, handler);

	}

	public void setRedirectTo(String redirectTo) {
		this.redirectTo = redirectTo;
	}

}
