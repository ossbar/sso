/**
 * Copyright (c) 2013-2015 www.javahih.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.hihsoft.ui_widget.datagrid;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hihframework.core.utils.StringHelpers;
import com.hihframework.exception.ControllerException;
import com.hihframework.osplugins.json.JsonUtil;
import com.hihsoft.baseclass.web.controller.javahihBaseController;
import com.hihsoft.sso.business.service.TsysFlatService;

/**
 * <p> Title:使用优秀的开源列表组件flexigrid演示 </p>
 * <p> Description:主要以子系统列表为功能演示</p>
 * <p> Copyright: Copyright (c) 2013 </p>
 * <p> Company:hihsoft.co.,ltd </p>
 *
 * @author hihsoft.co.,ltd
 * @version 1.0
 */
@Controller
@RequestMapping("/uiDataGridDemoController.do")
public class UI_DatagridDemoController extends javahihBaseController {
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
		ModelAndView mv = new ModelAndView("/ui_widget/ui_datagrid_demo");
	    return mv;
	}
	/**
	* 访问数据
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(params="method=listDatas")
	public ModelAndView listDatas(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		//分页操作
		String tpage=request.getParameter("page");
		String trows=request.getParameter("rp");
		int page=tpage==null?1:Integer.parseInt(tpage);
		int rows=trows==null?15:Integer.parseInt(trows);
		//查询条件
		String querySQL="";
		String query=request.getParameter("query");
		String qtype = request.getParameter("qtype"); 
		if(!"".equals(query)&&query!=null){
			querySQL=" and "+qtype+" like '%"+query+"%'";
		}
		//排序操作
		String sortname=request.getParameter("sortname");
		String sortorder = request.getParameter("sortorder"); 
		String orders=" order by "+sortname+" "+sortorder;
		//查询数据
		List list= tsysFlatService.queryForPagedListByHQL("from TsysFlat where 1=1"+querySQL+orders, page, rows);
		int total=tsysFlatService.getTotalNumBySQL("select * from T_SYS_FLAT where 1=1");
		String json = JsonUtil.toString(list);
		renderJson(response, "{\"total\": " + total +",\"page\": " + page +", \"rows\": "
				+ json+"}");
	    return null;
	}
}

