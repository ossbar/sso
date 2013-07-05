/**
 * Copyright (c) 2013-2015 www.javahih.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.hihsoft.sso.sysmonitor.syslogs.web.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.WebUtils;

import com.hihframework.core.utils.StringHelpers;
import com.hihframework.exception.ControllerException;
import com.hihsoft.baseclass.web.controller.javahihBaseController;
import com.hihsoft.sso.sysmonitor.syslogs.model.TlogAudit;
import com.hihsoft.sso.sysmonitor.syslogs.service.TlogAuditService;

/**
 * <p> Title: </p>
 * <p> Description:</p>
 * <p> Copyright: Copyright (c) 2013 </p>
 * <p> Company:hihsoft.co.,ltd </p>
 *
 * @author hihsoft.co.,ltd
 * @version 1.0
 */
@Controller
@RequestMapping(value="/tlogAuditController.do")
public class TlogAuditController extends javahihBaseController {
	@Autowired
	private TlogAuditService tlogAuditService;

	/**
	* 访问列表
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(params="method=list")
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		ModelAndView mv = new ModelAndView("audit/tlogauditlist");
		String hql = "from TlogAudit";
		Map<String, Object> filter = WebUtils.getParametersStartingWith(
				request, "srh_");// 得到查询条件
		String orders = getParam(request, "orders");
		Set<String> set = filter.keySet();
		for (Iterator<String> it = set.iterator(); it.hasNext();) {
			String key = it.next();
			Object value = filter.get(key);
			if (StringHelpers.notNull(value)) {
				hql += " and " + key + "='" + value + "'";
			}
		}
		if (orders != null && !"".equals(orders))
			hql += " order by " + orders;
		List<TlogAudit> list = calcPage(request, tlogAuditService, hql);
		mv.addObject("list", list);
		return mv;
	}

	/**
	* 進入新增頁面
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(params="method=add")
	public ModelAndView add(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		return new ModelAndView("audit/tlogauditform");
	}

	/**
	 * 修改
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params="method=modify")
	public ModelAndView modify(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView("audit/tlogauditform");
		String id = request.getParameter("id");
		TlogAudit tlogAudit = tlogAuditService.getTlogAuditById(id);
		mv.addObject("tlogAudit", tlogAudit);
		return mv;
	}

	/**
	* 查看记录详细
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(params="method=view")
	public ModelAndView view(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		String id = request.getParameter("id");
		TlogAudit tlogAudit = tlogAuditService.getTlogAuditById(id);
		this.bind(request, tlogAudit);
		return new ModelAndView("audit/tlogauditform");
	}

	/**
	* 保存
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(params="method=save")
	public ModelAndView save(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		TlogAudit tlogAudit = new TlogAudit();
		this.setValue(request, tlogAudit);
		tlogAuditService.saveOrUpdateTlogAudit(tlogAudit);
		return new ModelAndView(new RedirectView(request.getContextPath()+"/tlogAuditController.do?method=list"));
	}

	/**
	* 删除
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(params="method=delete")
	public ModelAndView delete(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		String ids = request.getParameter("ids");
		List<TlogAudit> list = new ArrayList<TlogAudit>();
		if (ids != null && !"".equals(ids)) {
			String[] arr = ids.split(",");
			for (String id : arr) {
				TlogAudit obj = new TlogAudit();
				obj.setAduitid(id);
				list.add(obj);
			}
		}
		tlogAuditService.deleteBatchVO(list);
		return new ModelAndView(new RedirectView(request.getContextPath()+"/tlogAuditController.do?method=list"));
	}
}
