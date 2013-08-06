/**
 * Copyright (c) 2013-2015 www.javahih.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.hihsoft.sso.business.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hihframework.exception.ControllerException;
import com.hihsoft.baseclass.web.controller.javahihBaseController;
import com.hihsoft.sso.business.model.TsysCodebuilder;
import com.hihsoft.sso.business.service.TsysCodebuilderService;

/**
 * Title:
 * Description:生成源代码服務
 * Copyright: Copyright (c) 2013
 * Company:hihsoft.co.,ltd
 * @author hihsoft.co.,ltd
 * @version 1.0
 */
@Controller
@RequestMapping("/tsysCodebuilderController.do")
public class TsysCodebuilderController extends javahihBaseController {
	@Autowired
	private TsysCodebuilderService tsysCodebuilderService;

	/**
	 * 访问列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params="method=list")
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		List<?> list = tsysCodebuilderService.getAllTsysCodebuilder();
		TsysCodebuilder tsysCodebuilder = new TsysCodebuilder();
		this.bind(request, tsysCodebuilder);
		request.setAttribute("list", list);
		return new ModelAndView(v_list);
	}

	/**
	 * 進入新增頁面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params="method=add")
	public ModelAndView add(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {

		return new ModelAndView(v_form);
	}

	/**
	 * 查看记录详细
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params="method=view")
	public ModelAndView view(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		String id = request.getParameter("codeid");
		TsysCodebuilder tsysCodebuilder = tsysCodebuilderService
				.getTsysCodebuilderById(id);
		this.bind(request, tsysCodebuilder);
		return new ModelAndView(v_form);
	}

	/**
	 * 新增、修改
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params="method=save")
	public ModelAndView save(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		TsysCodebuilder tsysCodebuilder = new TsysCodebuilder();
		this.bind(request, tsysCodebuilder);
		tsysCodebuilderService.saveOrUpdateTsysCodebuilder(tsysCodebuilder);
		return new ModelAndView(v_success);
	}

	/**
	 * 删除
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params="method=delete")
	public ModelAndView delete(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		String id = request.getParameter("codeid");
		tsysCodebuilderService.deleteTsysCodebuilder(id);
		return new ModelAndView(v_success);
	}

}
