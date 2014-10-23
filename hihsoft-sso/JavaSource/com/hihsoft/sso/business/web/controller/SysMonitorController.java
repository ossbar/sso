/**
 * Copyright (c) 2013-2015 www.javahih.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.hihsoft.sso.business.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.hihframework.exception.ControllerException;
import com.hihsoft.baseclass.web.controller.javahihBaseController;
/**
 * Title:监控管理，结合druid完成底层代码运行情况监控
 * Description:
 * Copyright: Copyright (c) 2014
 * Company:hihsoft.co.,ltd
 * @author hihsoft.co.,ltd
 * @version 1.4.0
 */
@Controller
@RequestMapping("/sysMonitorController.do")
public class SysMonitorController extends javahihBaseController {
	@RequestMapping(params = "method=ds")
	public ModelAndView ds(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		ModelAndView mv = new ModelAndView(new RedirectView(request.getContextPath()+"/druid/datasource.html"));
		return mv;
	}
	@RequestMapping(params = "method=sql")
	public ModelAndView sql(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		ModelAndView mv = new ModelAndView(new RedirectView(request.getContextPath()+"/druid/sql.html"));
		return mv;
	}
	@RequestMapping(params = "method=url")
	public ModelAndView url(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		ModelAndView mv = new ModelAndView(new RedirectView(request.getContextPath()+"/druid/weburi.html"));
		return mv;
	}
	@RequestMapping(params = "method=session")
	public ModelAndView session(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		ModelAndView mv = new ModelAndView(new RedirectView(request.getContextPath()+"/druid/websession.html"));
		return mv;
	}
	@RequestMapping(params = "method=spring")
	public ModelAndView spring(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		ModelAndView mv = new ModelAndView(new RedirectView(request.getContextPath()+"/druid/spring.html"));
		return mv;
	}
	
}
