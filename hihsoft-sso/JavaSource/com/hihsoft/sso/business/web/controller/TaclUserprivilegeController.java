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
import org.springframework.web.util.WebUtils;

import com.hihframework.core.utils.StringHelpers;
import com.hihframework.exception.ControllerException;
import com.hihsoft.baseclass.web.controller.javahihBaseController;
import com.hihsoft.sso.business.model.TaclUserprivilege;
import com.hihsoft.sso.business.service.TaclUserprivilegeService;

/**
 * Title:
 * Description:用戶分配特權服務
 * Copyright: Copyright (c) 2013
 * Company:hihsoft.co.,ltd
 * @author hihsoft.co.,ltd
 * @version 1.0
 */
@Controller
@RequestMapping("/taclUserprivilegeController.do")
public class TaclUserprivilegeController extends javahihBaseController {
	@Autowired
	private TaclUserprivilegeService taclUserprivilegeService;
	
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
		ModelAndView mv = new ModelAndView("");
		String hql = "from TaclUserprivilege";
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
		addOrders(request, orders);
		List<TaclUserprivilege> list = calcPage(request, taclUserprivilegeService, hql);
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
		return new ModelAndView(v_form);
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
		ModelAndView mv = new ModelAndView(v_form);
		String id=request.getParameter("id");
		TaclUserprivilege taclUserprivilege=taclUserprivilegeService.getTaclUserprivilegeById(id);
	    mv.addObject("taclUserprivilege", taclUserprivilege);
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
		TaclUserprivilege taclUserprivilege=taclUserprivilegeService.getTaclUserprivilegeById(id);
	    this.bind(request,taclUserprivilege);
		return new ModelAndView(v_form);
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
		TaclUserprivilege taclUserprivilege=new TaclUserprivilege();
	    this.setValue(request,taclUserprivilege);
	    taclUserprivilegeService.saveOrUpdateTaclUserprivilege(taclUserprivilege);
		return new ModelAndView(v_success);
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
		List<TaclUserprivilege> list = new ArrayList<TaclUserprivilege>();
		if (ids != null && !"".equals(ids)) {
			String[] arr = ids.split(",");
			for (String id : arr) {
				TaclUserprivilege obj = new TaclUserprivilege();
				obj.setUserprviid(id);
				list.add(obj);
			}
		}
		taclUserprivilegeService.deleteBatchVO(list);
		return new ModelAndView(v_success);
	}
	
}

