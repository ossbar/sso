/**
 * Copyright (c) 2013-2015 www.javahih.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.hihsoft.sso.sysmonitor.syslogs.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hihframework.core.utils.DateUtils;
import com.hihframework.core.utils.SpringContextHolder;
import com.hihframework.core.utils.StringHelpers;
import com.hihsoft.baseclass.constants.Consts;
import com.hihsoft.baseclass.service.impl.BaseServiceImpl;
import com.hihsoft.sso.sysmonitor.syslogs.model.TlogBusinesslog;
import com.hihsoft.sso.sysmonitor.syslogs.service.TlogBusinesslogService;

/**
 * <p>
 * Title:业务日志拦截器
 * </p>
 * <p>
 * Description:主要针对的是控制层controller
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company:hihsoft.co.,ltd
 * </p>
 * 
 * @author hihsoft.co.,ltd
 * @version 1.0
 */
public class BusinessLoggerInterceptor extends HandlerInterceptorAdapter {
	static final Logger log = Logger.getLogger(BusinessLoggerInterceptor.class);

	// private static TlogBusinesslogService tlogBusinesslogService
	// =SpringContextHolder.getBean("tlogBusinesslogService");
	// before the actual handler will be executed
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		long startTime = System.currentTimeMillis();
		request.setAttribute("startTime", startTime);
		return true;
	}

	// after the handler is executed
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		long startTime = (Long) request.getAttribute("startTime");

		long endTime = System.currentTimeMillis();

		long executeTime = endTime - startTime;

		// modified the exisitng modelAndView
		modelAndView.addObject("executeTime", executeTime);

		// log it

		log.debug("[" + handler + "] executeTime : " + executeTime + "ms");

	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

		String requestRri = request.getRequestURI();
		String uriPrefix = request.getContextPath();

		if ((StringUtils.startsWithIgnoreCase(requestRri, uriPrefix) && (StringUtils
				.endsWithIgnoreCase(requestRri, "/save")
				|| StringUtils.endsWithIgnoreCase(requestRri, "/delete")
				|| StringUtils.endsWithIgnoreCase(requestRri, "/edit") || StringUtils
					.endsWithIgnoreCase(requestRri, "/update")))
				|| !StringUtils.endsWithIgnoreCase(requestRri, "index.jsp")
				|| !StringUtils.endsWithIgnoreCase(uriPrefix,
						"/tsysLoginController.do")) {
			log.info("还报错，啥情况");
		}
		// String method = request.getParameter("method");
		// HttpSession session = request.getSession(true);
		// if (ex == null && StringHelpers.notNull(method)) {
		// TlogBusinesslog bl = new TlogBusinesslog();
		// bl.setContextPath(request.getContextPath());
		// bl.setCreateDate(DateUtils.getToday());
		// bl.setCustip(request.getRemoteAddr());
		// bl.setOrgid((String) session.getAttribute(Consts.CUR_ORGID));
		// bl.setUserid((String) session.getAttribute(Consts.USER_ID));
		// // tlogBusinesslogService.saveOrUpdateTlogBusinesslog(bl);
		// }
		// }
	}
}
