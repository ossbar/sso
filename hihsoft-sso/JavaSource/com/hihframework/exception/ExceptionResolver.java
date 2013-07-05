/**
 * Copyright (c) 2013-2015 www.javahih.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.hihframework.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.hihframework.core.utils.StringPrintWriter;
/**
 * <p> Title:异常解释器 </p>
 * <p> Description:实现统一的异常处理</p>
 * <p> Copyright: Copyright (c) 2013 </p>
 * <p> Company:hihsoft.co.,ltd </p>
 *
 * @author hihsoft.co.,ltd
 * @version 1.0
 */
public class ExceptionResolver implements HandlerExceptionResolver {

	/** The Constant log. */
	private static final Logger log = Logger.getLogger(ExceptionResolver.class);
	// 业务逻辑层异常的处理
	/** The service_exception. */
	private String service_exception = null;
	// WEB控制层异常统一处理
	/** The controller_exception. */
	private String controller_exception = null;
	// 自定义异常处理
	/** The javahih_exception. */
	private String javahih_exception = null;
	private String error = null;

	public void setError(String error) {
		this.error = error;
	}

	/**
	 * Sets the service_exception.
	 *
	 * @param service_exception the new service_exception
	 */
	public void setService_exception(String service_exception) {
		this.service_exception = service_exception;
	}

	/**
	 * Sets the controller_exception.
	 *
	 * @param controller_exception the new controller_exception
	 */
	public void setController_exception(String controller_exception) {
		this.controller_exception = controller_exception;
	}

	/**
	 * Sets the javahih_exception.
	 *
	 * @param javahih_exception the new javahih_exception
	 */
	public void setjavahih_exception(String javahih_exception) {
		this.javahih_exception = javahih_exception;
	}

	/**
	 * 处理异常情况
	 */
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse resonpnse, Object object, Exception ex) {
		if (ex instanceof ServiceException) {
			log.info("服务端异常");
			request.setAttribute("error", "服务端异常啊，请检查");
			return new ModelAndView(service_exception);

		} else if (ex instanceof ControllerException) {
			log.info("控制层异常");
			// Map model = new HashMap();
			// model.put("ex", ex.getClass().getSimpleName());
			// model.put("error", ex.getMessage());
			// return new ModelAndView(javahih_exception, model);
			request.setAttribute("error", "控制层异常啊，请检查");
			return new ModelAndView(controller_exception);
		} else if (ex instanceof HihSoftException) {
			log.info("平台自定义异常");
			request.setAttribute("exception", "平台自定义异常啊，请检查");
			return new ModelAndView(javahih_exception);

		}
		Map<String, Object> map = new HashMap<String, Object>();
		StringPrintWriter strintPrintWriter = new StringPrintWriter();
		ex.printStackTrace(strintPrintWriter);
		map.put("errorMsg", strintPrintWriter.getString());// 将错误信息传递给view
		return new ModelAndView(error, map);

	}

}
