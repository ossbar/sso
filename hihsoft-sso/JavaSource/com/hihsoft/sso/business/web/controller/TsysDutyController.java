/**
 * Copyright (c) 2013-2015 www.javahih.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.hihsoft.sso.business.web.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

import com.hihframework.core.utils.ParamUtil;
import com.hihframework.exception.ControllerException;
import com.hihsoft.baseclass.web.controller.javahihBaseController;
import com.hihsoft.sso.business.model.TaclUserinfo;
import com.hihsoft.sso.business.model.TsysDuty;
import com.hihsoft.sso.business.model.TsysParameter;
import com.hihsoft.sso.business.service.TaclUserinfoService;
import com.hihsoft.sso.business.service.TsysDutyService;
/**
 * <p> Title:岗位划分 </p>
 * <p> Description:</p>
 * <p> Copyright: Copyright (c) 2013 </p>
 * <p> Company:javahih.co.,ltd </p>
 *
 * @author zhujw
 * @version 1.0
 */
@Controller
@RequestMapping("/tsysDutyController.do")
public class TsysDutyController extends javahihBaseController {
	@Autowired
	private TsysDutyService tsysDutyService;
	@Autowired
	private TaclUserinfoService taclUserinfoService; // userInfo
	
	/**
	 * 访问列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ControllerException
	 */
	@RequestMapping(params="method=list")
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException{
		ModelAndView mv = new ModelAndView("/duty/tsysdutylist");
		String hql = "from TsysDuty t where 1=1";
		Map<String, Object> filter = WebUtils.getParametersStartingWith(
				request, "srh_");// 得到查询条件
		String orders = getParam(request, "orders");
		
		String dutyname = (String) filter.get("dutyname");
		if (StringUtils.isNotEmpty(dutyname)) {
			hql += " and t.dutyname like '%" + dutyname + "%'";
		}
		
		if (orders != null && !"".equals(orders))
			hql += " order by " + orders;
		addOrders(request, orders);
		List<TsysDuty> list = calcPage(request, tsysDutyService, hql);
		convertParam(list, "dutySort", "DUTYSORT");
		convertParam(list, "dutyType", "dutytype");
		mv.addObject("list", list);
		mv.addObject("dutyname",dutyname);
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
		ModelAndView mv =  new ModelAndView("/duty/tsysdutyform");
		/*List<TsysParameter> selectRole = getRoleType(request, response);
		boolean isGroup = isGroup(request, response);*/
		List<TsysParameter> selectRole = ParamUtil.getInstance().getByType("DUTYSORT");
		Collections.sort(selectRole, new Comparator<Object>(){		//按编号升序。
			@Override
			public int compare(Object o1, Object o2) {
					TsysParameter p1 = (TsysParameter)o1;
					TsysParameter p2 = (TsysParameter)o2;
				return p1.getParano().compareTo(p2.getParano());
			}
		 });
		List<TsysParameter> dutytype = ParamUtil.getInstance().getByType("dutytype");
		mv.addObject("dutytype",dutytype);
		mv.addObject("selectRole", selectRole);
		return mv;
	}
	

	/**
	 * 修改
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ControllerException
	 */
	@RequestMapping(params="method=modify")
	public ModelAndView modify(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		ModelAndView mv = new ModelAndView("/duty/tsysdutyform");
		String id = request.getParameter("id");
		TsysDuty tsysDuty = tsysDutyService.getTsysDutyById(id);
		List<TsysParameter> selectRole = ParamUtil.getInstance().getByType("DUTYSORT");
		List<TsysParameter> dutytype = ParamUtil.getInstance().getByType("dutytype");
		mv.addObject("dutytype",dutytype);
		mv.addObject("selectRole", selectRole);
		mv.addObject("tsysDuty", tsysDuty);
		return mv;
	}

	/**
	 * 查看记录详细
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ControllerException
	 */
	@RequestMapping(params="method=view")
	public ModelAndView view(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		String id = request.getParameter("id");
		TsysDuty tsysDuty = tsysDutyService.getTsysDutyById(id);
		this.bind(request, tsysDuty);
		return new ModelAndView("/duty/tsysdutyform");
	}

	/**
	 * 保存
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ControllerException
	 */
	@RequestMapping(params="method=save")
	public ModelAndView save(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		TsysDuty tsysDuty = new TsysDuty();
		this.setValue(request, tsysDuty);
		tsysDutyService.saveOrUpdateTsysDuty(tsysDuty);
		return new ModelAndView(new RedirectView(request.getContextPath()+"/tsysDutyController.do?method=list"));
	}

	/**
	 * 删除
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ControllerException
	 */
	@RequestMapping(params="method=delete")
	public ModelAndView delete(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		String ids = request.getParameter("ids");
		List<TsysDuty> list = new ArrayList<TsysDuty>();
		List<TaclUserinfo> userList = new ArrayList<TaclUserinfo>();
		boolean flag = true;
		if (ids != null && !"".equals(ids)) {
			String[] arr = ids.split(",");
			for (String id : arr) {
				String hql = "from TaclUserinfo where dutyid='"+id+"'";
				userList = taclUserinfoService.getTaclUserinfoByHQL(hql);	//取得当前岗位下用户
				if(userList.size()==0){
				TsysDuty obj = new TsysDuty();
				obj.setDutyid(id);
				list.add(obj);
				}else{
				flag=false;
				}
			}
		}
		tsysDutyService.deleteBatchVO(list);
		renderJson(response, toJson("success",flag));
		return null;
	}

}
