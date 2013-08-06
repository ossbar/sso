/**
 * Copyright (c) 2013-2015 www.javahih.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.hihsoft.sso.business.web.controller;

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
import com.hihsoft.sso.business.model.TsysFlat;
import com.hihsoft.sso.business.service.TsysFlatService;

/**
 * <p> Title:综合业务平台中添加多个子系统 </p>
 * <p> Description:</p>
 * <p> Copyright: Copyright (c) 2013 </p>
 * <p> Company:hihsoft.co.,ltd </p>
 *
 * @author hihsoft.co.,ltd
 * @version 1.0
 */
@Controller
@RequestMapping("/tsysFlatController.do")
public class TsysFlatController extends javahihBaseController {
	@Autowired
	private TsysFlatService tsysFlatService;
	
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
		ModelAndView mv = new ModelAndView("/flat/tsysflatlist");
		String hql = "from TsysFlat";
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
		else{
			hql+=" order by flatdesc asc";
		}
		addOrders(request, orders);
		List<TsysFlat> list = calcPage(request, tsysFlatService, hql);
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
		return new ModelAndView("/flat/tsysflatform");
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
			HttpServletResponse response) throws ControllerException {
		ModelAndView mv = new ModelAndView("/flat/tsysflatform");
		String id=request.getParameter("id");
		TsysFlat tsysFlat=tsysFlatService.getTsysFlatById(id);
	    mv.addObject("tsysFlat", tsysFlat);
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
		String id=request.getParameter("id");
		TsysFlat tsysFlat=tsysFlatService.getTsysFlatById(id);
	    this.bind(request,tsysFlat);
		return new ModelAndView("/flat/tsysflatform");
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
		TsysFlat tsysFlat=new TsysFlat();
	    this.setValue(request,tsysFlat);
	    tsysFlatService.saveOrUpdateTsysFlat(tsysFlat);
		return new ModelAndView(new RedirectView(request.getContextPath()+"/tsysFlatController.do?method=list"));
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
		List<TsysFlat> list = new ArrayList<TsysFlat>();
		if (ids != null && !"".equals(ids)) {
			String[] arr = ids.split(",");
			for (String id : arr) {
				TsysFlat obj = new TsysFlat();
				obj.setFlatid(id);
				list.add(obj);
			}
		}
		tsysFlatService.deleteBatchVO(list);
		return new ModelAndView(new RedirectView(request.getContextPath()+"/tsysFlatController.do?method=list"));
	}
	
}

