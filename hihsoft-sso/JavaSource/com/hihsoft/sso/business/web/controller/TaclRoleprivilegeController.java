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
import com.hihsoft.sso.business.service.TaclRoleService;
import com.hihsoft.sso.business.service.TaclRoleprivilegeService;
import com.mysql.jdbc.StringUtils;

/**
 * <p> Title: </p>
 * <p> Description:</p>
 * <p> Copyright: Copyright (c) 2013 </p>
 * <p> Company:javahih.co.,ltd </p>
 *
 * @author zhujw
 * @version 1.0
 */
@Controller
@RequestMapping("/taclRoleprivilegeController.do")
public class TaclRoleprivilegeController extends javahihBaseController {
	@Autowired
	private TaclRoleprivilegeService taclRoleprivilegeService;
	@Autowired
	private TaclRoleService taclRoleService;

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
		String roleId = request.getParameter("roleId");
		String hql = "from TaclRole where 1=1 ";
		boolean notNull = roleId != null && !"".equals(roleId);
		if (notNull) {
			hql += "and roleid='" + roleId + "'";
		}
		List<?> rolelist = taclRoleService.getTaclRoleByHQL(hql);
		request.setAttribute("rolelist", rolelist);
		return new ModelAndView("/roleprivilege/taclroleprivilegelist");
	}
	@RequestMapping(params="method=saveRoleSet")
	public ModelAndView saveRoleSet(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String roleId = request.getParameter("roleId");
		String moduleSet = request.getParameter("moduleSet");
		if (StringUtils.isNullOrEmpty(roleId) || moduleSet == null) {
			renderJson(response, "{}");
		} else {
			boolean flag = taclRoleprivilegeService.saveRoleSet(roleId, moduleSet);
			renderJson(response, toJson("success", flag));
		}
		return null;
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

		return new ModelAndView("/roleprivilege/taclroleprivilegeform");
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

		return new ModelAndView("/roleprivilege/taclroleprivilegeform");
	}

	/**
	* 新增、修改
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(params="method=saveOrUpdate")
	public ModelAndView saveOrUpdate(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return new ModelAndView(v_save);
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

		return new ModelAndView(v_delete);
	}

}
