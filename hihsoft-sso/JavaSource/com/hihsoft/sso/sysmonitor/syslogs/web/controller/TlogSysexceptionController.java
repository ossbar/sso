/**
 * Copyright (c) 2013-2015 www.javahih.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.hihsoft.sso.sysmonitor.syslogs.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.WebUtils;

import com.hihframework.exception.ControllerException;
import com.hihsoft.baseclass.web.controller.javahihBaseController;
import com.hihsoft.sso.sysmonitor.syslogs.model.TlogSysexception;
import com.hihsoft.sso.sysmonitor.syslogs.service.TlogSysexceptionService;

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
@RequestMapping(value="/tlogSysexceptionController.do")
public class TlogSysexceptionController extends javahihBaseController {
	@Autowired
	private TlogSysexceptionService tlogSysexceptionService;

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
		ModelAndView mv = new ModelAndView("sysexception/tlogsysexceptionlist");
		String sql = "select exception_oid, exception_times, exception_lasttime, exception_name, exception_msg, exception_class, u.username as userid from t_log_sysexception l,t_acl_userinfo u where l.userid = u.userid";
		// String sql = "select * from t_log_sysexception";
		Map<String, Object> filter = WebUtils.getParametersStartingWith(
				request, "srh_");// 得到查询条件
		String userid = (String) filter.get("userid");
		String exceptionName = (String) filter.get("exceptionName");
		String beginDate = (String) filter.get("beginDate");
		String endDate = (String) filter.get("endDate");
		String exceptionClass = (String) filter.get("exceptionClass");

		if (StringUtils.isNotEmpty(userid)) {
			sql += " and u.username like '%" + userid + "%'";

		}
		if (StringUtils.isNotEmpty(exceptionName)) {
			sql += " and exception_name like '%" + exceptionName + "%'";
		}
		if (StringUtils.isNotEmpty(exceptionClass)) {
			sql += " and exception_class like '%" + exceptionClass + "%'";
		}
		if (StringUtils.isNotEmpty(beginDate)
				&& StringUtils.isNotEmpty(endDate)) {
			sql += " and exception_lasttime > '" + beginDate
					+ "' and exception_lasttime < '" + endDate + "'";
		}
		String pageSize = getParam(request, "rows");
		String pageNo = getParam(request, "page");
		String orders = getParam(request, "orders");
		if (pageSize == null)
			pageSize = "10";
		if (pageNo == null)
			pageNo = "1";
		List list = tlogSysexceptionService.getPageDataBySQL(
				TlogSysexception.class, sql, Integer.parseInt(pageSize),
				Integer.parseInt(pageNo));
		int total = tlogSysexceptionService.getTotalNumBySQL(sql);
		int rows = Integer.parseInt(pageSize);
		int pages = total % rows == 0 ? (total / rows) : (total / rows + 1);
		mv.addObject("userid", userid);
		mv.addObject("exceptionName", exceptionName);
		mv.addObject("beginDate", beginDate);
		mv.addObject("endDate", endDate);
		mv.addObject("exceptionClass", exceptionClass);
		mv.addObject("total", total);
		mv.addObject("rows", pageSize);
		mv.addObject("page", Integer.parseInt(pageNo));
		mv.addObject("pages", pages);
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
		return new ModelAndView("sysexception/tlogsysexceptionform");
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

		return new ModelAndView("sysexception/tlogsysexceptionform");
	}

	/**
	* 新增、修改
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(params="method=save")
	public ModelAndView save(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {

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
		String ids = request.getParameter("ids");
		List<TlogSysexception> list = new ArrayList<TlogSysexception>();

		if (ids != null && !"".equals(ids)) {
			String[] arr = ids.split(",");
			for (String id : arr) {
				TlogSysexception obj = new TlogSysexception();
				obj.setExceptionOid(id);
				list.add(obj);
			}
		}
		tlogSysexceptionService.deleteBatchVO(list);
		return new ModelAndView(new RedirectView(request.getContextPath()+"/tlogSysexceptionController.do?method=list"));
	}
	//修改成新版本的哦
}