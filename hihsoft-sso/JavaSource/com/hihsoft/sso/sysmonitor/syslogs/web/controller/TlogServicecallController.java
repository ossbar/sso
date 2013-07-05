package com.hihsoft.sso.sysmonitor.syslogs.web.controller;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.hihframework.exception.ControllerException;
import com.hihsoft.baseclass.web.controller.javahihBaseController;
import com.hihsoft.sso.sysmonitor.syslogs.model.TlogServicecall;
import com.hihsoft.sso.sysmonitor.syslogs.service.TlogServicecallService;

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
@RequestMapping(value="/tlogServicecallController.do")
public class TlogServicecallController extends javahihBaseController {
@Autowired
private TlogServicecallService tlogServicecallService;

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
	String hql="";
	List list = tlogServicecallService.getTlogServicecallByHQL(hql);
	TlogServicecall tlogServicecall=new TlogServicecall();
    this.bind(request,tlogServicecall);
	request.setAttribute("list", list);
    return new ModelAndView(v_list);
}

/**
* 進入新增頁面
* @param request
* @param response
* @return
* @throws Exception
*/
public ModelAndView add(HttpServletRequest request,
		HttpServletResponse response) throws ControllerException {
	
	return new ModelAndView(v_form);
}

/**
* 查看记录详细
* @param request
* @param response
* @return
* @throws Exception
*/
public ModelAndView view(HttpServletRequest request,
		HttpServletResponse response) throws ControllerException {
	
	return new ModelAndView(v_form);
}

/**
* 新增、修改
* @param request
* @param response
* @return
* @throws Exception
*/
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
public ModelAndView delete(HttpServletRequest request,
		HttpServletResponse response) throws ControllerException {
	
	return new ModelAndView(new RedirectView(request.getContextPath()+"/tlogLoginlogController.do?method=list"));
}
}

