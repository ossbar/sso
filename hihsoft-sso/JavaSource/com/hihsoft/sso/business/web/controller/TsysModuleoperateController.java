package com.hihsoft.sso.business.web.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hihframework.core.utils.StringHelpers;
import com.hihframework.exception.ControllerException;
import com.hihsoft.baseclass.web.controller.javahihBaseController;
import com.hihsoft.sso.business.model.TsysModuleoperate;
import com.hihsoft.sso.business.service.TsysModuleoperateService;
import com.mysql.jdbc.StringUtils;

/**

 * Title:模块操作的控制层服务
 * Description:
 * Copyright: Copyright (c) 2013
 * Company:hihsoft.co.,ltd
 * @author hihsoft.co.,ltd
 * @version 1.0
 */
@Controller
@RequestMapping("/tsysModuleoperateController.do")
public class TsysModuleoperateController extends javahihBaseController {
	@Autowired
	private TsysModuleoperateService tsysModuleoperateService;
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
		ModelAndView mv = new ModelAndView("/operate/tsysmoduleoperatelist");
		String moduleId = request.getParameter("moduleid");
		String hql = "from TsysModuleoperate where moduleid=? ";
		if (StringUtils.isNullOrEmpty(moduleId)) {
			return null;
		}
		List list = calcPage(request, tsysModuleoperateService, hql, moduleId);
		mv.addObject("list", list);
		mv.addObject("moduleid", moduleId);
		return mv;
	}
	/**
	 * 显示树
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author Xiaojf
	 * @since 2011-5-30
	 */
	@RequestMapping(params="method=getTree")
	public ModelAndView getTree(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		ModelAndView mv = new ModelAndView("/operate/tsysmoduleoperatetree");
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
	@RequestMapping(params="method=add")
	public ModelAndView add(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		String moduleid = request.getParameter("moduleid");
		request.setAttribute("moduleid", moduleid);
		return new ModelAndView("/operate/tsysmoduleoperateform");
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
		String id = request.getParameter("operateid");
		TsysModuleoperate tsysModuleoperate = tsysModuleoperateService
				.getTsysModuleoperateById(id);
		this.bind(request, tsysModuleoperate);
		return new ModelAndView("/operate/tsysmoduleoperateform");
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
		TsysModuleoperate tsysModuleoperate = new TsysModuleoperate();
		this.setValue(request, tsysModuleoperate);
		tsysModuleoperateService
				.saveOrUpdateTsysModuleoperate(tsysModuleoperate);
		renderJson(response, toJson("success", true));
		return null;
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
		ModelAndView mv = new ModelAndView("/operate/tsysmoduleoperateform");
		String id=request.getParameter("id");
		if (StringHelpers.notNull(id)) {
			TsysModuleoperate oper = tsysModuleoperateService.getTsysModuleoperateById(id);
			mv.addObject("moduleid", oper.getModuleid());
			mv.addObject("tsysModuleoperate", oper);
		}
		return mv;
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
		String ids = request.getParameter("ids");
		List<TsysModuleoperate> list = new ArrayList<TsysModuleoperate>();
		if (ids != null && !"".equals(ids)) {
			String[] arr = ids.split(",");
			for (String id : arr) {
				TsysModuleoperate obj = new TsysModuleoperate();
				obj.setOperateid(id);
				list.add(obj);
			}
		}
		tsysModuleoperateService.deleteBatchVO(list);
		renderJson(response, toJson("success", true));
		return null;
	}

}
