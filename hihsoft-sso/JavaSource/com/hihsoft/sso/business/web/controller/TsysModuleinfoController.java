package com.hihsoft.sso.business.web.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.WebUtils;

import com.hihframework.exception.ControllerException;
import com.hihframework.osplugins.json.JsonUtil;
import com.hihsoft.baseclass.web.controller.javahihBaseController;
import com.hihsoft.sso.business.model.TsysModuleinfo;
import com.hihsoft.sso.business.service.TsysFlatService;
import com.hihsoft.sso.business.service.TsysModuleinfoService;

/**
 * Title:模块定义的控制层服务
 * Description:
 * Copyright: Copyright (c) 2013
 * Company:hihsoft.co.,ltd
 * @author hihsoft.co.,ltd
 * @version 1.0
 */
@Controller
@RequestMapping("/tsysModuleinfoController.do")
public class TsysModuleinfoController extends javahihBaseController {
	private static Logger log = Logger.getLogger(TsysModuleinfoController.class);
	@Autowired
	private TsysModuleinfoService tsysModuleinfoService;
	@Autowired
	private TsysFlatService tsysFlatService;


	/**
	 * 访问列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params="method=list")
	public ModelAndView list(final HttpServletRequest request,
			final HttpServletResponse response) throws ControllerException {
		Map<String, Object> filter = WebUtils.getParametersStartingWith(
				request, "srh_");// 得到查询条件
		String orders = getParam(request, "orders");
		String hql = "from TsysModuleinfo t where 1=1";
		String modulename = (String) filter.get("modulename");
		if (StringUtils.isNotEmpty(modulename)) {
			hql += " and t.modulename like '%" + modulename + "%'";
		}
		String moduleno = (String) filter.get("moduleno");
		if (StringUtils.isNotEmpty(moduleno)) {
			hql += " and t.moduleno like '%" + moduleno + "%'";
		}
		if (orders != null && !"".equals(orders))
			hql += " order by " + orders;
		addOrders(request, orders);
		List list = calcPage(request, tsysModuleinfoService, hql);
		request.setAttribute("list", list);
		return new ModelAndView("/module/tsysmodulelist");
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
	public ModelAndView add(final HttpServletRequest request,
			final HttpServletResponse response) throws ControllerException {
		ModelAndView mv = new ModelAndView("/module/tsysmoduleform");
		List flats = tsysFlatService.getAllTsysFlat();
		mv.addObject("flats", flats);
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
	@RequestMapping(params="method=view")
	public ModelAndView view(final HttpServletRequest request,
			final HttpServletResponse response) throws ControllerException {
		return new ModelAndView("/module/tsysmoduleform");
	}

	/**
	 * 新增、修改
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params="method=saveOrUpdate")
	public ModelAndView saveOrUpdate(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		TsysModuleinfo tsysModuleinfo = new TsysModuleinfo();
		this.setValue(request, tsysModuleinfo);
		tsysModuleinfoService.saveOrUpdateTsysModuleinfo(tsysModuleinfo);
		return new ModelAndView(new RedirectView(request.getContextPath()+"/tsysModuleinfoController.do?method=list"));
	}
	@RequestMapping(params="method=modify")
	public ModelAndView modify(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView("/module/tsysmoduleform");
		String id = request.getParameter("id");
		TsysModuleinfo moduleinfo = tsysModuleinfoService
				.getTsysModuleinfoById(id);
		mv.addObject("moduleInfo", moduleinfo);
		List flats = tsysFlatService.getAllTsysFlat();
		mv.addObject("flats", flats);
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
	public ModelAndView delete(final HttpServletRequest request,
			final HttpServletResponse response) throws ControllerException {
		final String ids = request.getParameter("ids");
		final List<TsysModuleinfo> modules = new ArrayList<TsysModuleinfo>();
		if (ids != null && !"".equals(ids)) {
			final String[] moduleIds = ids.split(",");
			for (final String id : moduleIds) {
				modules.add(new TsysModuleinfo(id));
			}
		}
		tsysModuleinfoService.deleteBatchVO(modules);
		return new ModelAndView(new RedirectView(request.getContextPath()+"/tsysModuleinfoController.do?method=list"));
	}
	@RequestMapping(params="method=queryList")
	public ModelAndView queryList(final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		final String hql = "from TsysModuleinfo";
		String pageSize = request.getParameter("rows");
		final String pageNo = request.getParameter("page");
		if (pageSize == null)
			pageSize = "10";
		if (pageNo == null)
			pageSize = "1";
		final List list = tsysModuleinfoService.getTsysModuleinfoPageDataByHQL(
				hql, new Object[] {}, Integer.parseInt(pageSize),
				Integer.parseInt(pageNo));
		final int total = tsysModuleinfoService.getDataTotalNum(hql);
		final String result = "{\"total\": " + total + ", \"rows\": "
				+ JsonUtil.toString(list, JsonUtil.COLLECTION_FILTER) + "}";
		final PrintWriter out = response.getWriter();
		out.write(result);
		return null;
	}

}
