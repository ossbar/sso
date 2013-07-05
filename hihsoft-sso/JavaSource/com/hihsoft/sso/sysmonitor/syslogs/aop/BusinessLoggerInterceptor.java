package com.hihsoft.sso.sysmonitor.syslogs.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hihsoft.sso.sysmonitor.syslogs.service.TlogBusinesslogService;

/**
 * <p> Title:业务日志拦截器 </p>
 * <p> Description:主要针对的是控制层controller</p>
 * <p> Copyright: Copyright (c) 2013 </p>
 * <p> Company:hihsoft.co.,ltd </p>
 *
 * @author hihsoft.co.,ltd
 * @version 1.0
 */
public class BusinessLoggerInterceptor extends HandlerInterceptorAdapter {
	private static final Logger log = Logger
			.getLogger(BusinessLoggerInterceptor.class);
	@Autowired
	private TlogBusinesslogService tlogBusinesslogService;

	public void setTlogBusinesslogService(TlogBusinesslogService tlogBusinesslogService) {
		this.tlogBusinesslogService = tlogBusinesslogService;
	}

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

		//long startTime = (Long) request.getAttribute("startTime");

		//long endTime = System.currentTimeMillis();

		//long executeTime = endTime - startTime;

		// modified the exisitng modelAndView
		//modelAndView.addObject("executeTime", executeTime);

		// log it
		
		//log.debug("[" + handler + "] executeTime : " + executeTime + "ms");

	}
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
//		String method = request.getParameter("method");
//		HttpSession session = request.getSession(true);
		
		super.afterCompletion(request, response, handler, ex);
//		if (ex == null && StringHelpers.notNull(method)) {
//			TlogBusinesslog bl = new TlogBusinesslog();
//			bl.setContextPath(request.getContextPath());
//			bl.setCreateDate(DateUtils.getToday());
//			bl.setCustip(request.getRemoteAddr());
//			bl.setOrgid((String) session.getAttribute(Consts.CUR_ORGID));
//			bl.setUserid((String) session.getAttribute(Consts.USER_ID));
//			tlogBusinesslogService.saveOrUpdateTlogBusinesslog(bl);
//		}
	}

	public TlogBusinesslogService getTlogBusinesslogService() {
		return tlogBusinesslogService;
	}

}
