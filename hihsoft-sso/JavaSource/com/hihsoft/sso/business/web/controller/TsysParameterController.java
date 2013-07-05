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

import org.hibernate.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.hihframework.core.utils.ParamUtil;
import com.hihframework.core.utils.StringHelpers;
import com.hihframework.exception.ControllerException;
import com.hihsoft.baseclass.web.controller.javahihBaseController;
import com.hihsoft.sso.business.model.TsysParameter;
import com.hihsoft.sso.business.service.TsysParameterService;

/**
 * Title:系统参数的控制层的服务
 * Description:
 * Copyright: Copyright (c) 2013
 * Company:hihsoft.co.,ltd
 * @author hihsoft.co.,ltd
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/tsysParameterController.do")
public class TsysParameterController extends javahihBaseController {
	@Autowired
	private TsysParameterService tsysParameterService;

	/**
	 * 访问列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "method=list")
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		ModelAndView mv = new ModelAndView("/parameter/tsysparameterlist");
		String hql = "from TsysParameter where 1=1";
		Map<String, Object> filter = WebUtils.getParametersStartingWith(
				request, "srh_");// 得到查询条件
		String orders = getParam(request, "orders");
		Set<String> set = filter.keySet();
		for (Iterator<String> it = set.iterator(); it.hasNext();) {
			String key = it.next();
			Object value = filter.get(key);
			if (StringHelpers.notNull(value)) {
				if (key.equals("paraType")) {
					hql += " and UPPER(" + key + ") like '%"
							+ StringHelper.toUpperCase(value.toString()) + "%'";
					hql += " and LOWER(" + key + ") like '%"
							+ StringHelper.toLowerCase(value.toString()) + "%'";
				} else
					hql += " and " + key + " like '%" + value + "%'";
			}
		}
		if (orders != null && !"".equals(orders)) {
			hql += " order by " + orders;
		} else {
			hql += " order by paraType,paraOrder";
			orders = "paraType ASC,paraOrder ASC";
		}
		addOrders(request, orders);
		List<TsysParameter> list = calcPage(request, tsysParameterService, hql);
		mv.addObject("list", list);
		return mv;
	}

	/**
	 * 進入新增頁面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "method=add")
	public ModelAndView add(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		return new ModelAndView("/parameter/tsysparameterform");
	}

	/**
	 * 修改
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "method=modify")
	public ModelAndView modify(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		ModelAndView mv = new ModelAndView("/parameter/tsysparameterform");
		String id = request.getParameter("id");
		TsysParameter tsysParameter = tsysParameterService
				.getTsysParameterById(id);
		mv.addObject("tsysParameter", tsysParameter);
		return mv;
	}

	/**
	 * 查看记录详细
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "method=view")
	public ModelAndView view(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		String id = request.getParameter("id");
		TsysParameter tsysParameter = tsysParameterService
				.getTsysParameterById(id);
		this.bind(request, tsysParameter);
		return new ModelAndView("/parameter/tsysparameterform");
	}

	/**
	 * 保存
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "method=save")
	public ModelAndView save(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		TsysParameter tsysParameter = new TsysParameter();
		this.setValue(request, tsysParameter);
		boolean flag = true;
		try {
			tsysParameterService.saveOrUpdateTsysParameter(tsysParameter);
		} catch (Exception e) {
			logger.error(e);
			flag = false;
		}
		ParamUtil.getInstance().updateParams();
		renderJson(response, toJson("success", flag));
		return null;
	}

	/**
	 * 删除
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView delete(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		String ids = request.getParameter("ids");
		boolean flag = true;
		List<TsysParameter> list = new ArrayList<TsysParameter>();
		if (ids != null && !"".equals(ids)) {
			String[] arr = ids.split(",");
			try {
				for (String id : arr) {
					TsysParameter obj = new TsysParameter();
					obj.setParaid(id);
					list.add(obj);
				}
				tsysParameterService.deleteBatchVO(list);
				ParamUtil.getInstance().updateParams();
			} catch (Exception e) {
				logger.error(e);
				flag = false;
			}
		}
		renderJson(response, toJson("success", flag));
		return null;
	}

}
