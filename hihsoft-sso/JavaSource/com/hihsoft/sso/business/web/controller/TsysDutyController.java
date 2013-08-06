/**
 * Copyright (c) 2013-2015 www.javahih.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.hihsoft.sso.business.web.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.WebUtils;

import com.hihframework.core.utils.ParamUtil;
import com.hihframework.core.utils.StringHelpers;
import com.hihframework.exception.ControllerException;
import com.hihframework.osplugins.json.JsonUtil;
import com.hihsoft.baseclass.constants.Consts;
import com.hihsoft.baseclass.web.controller.javahihBaseController;
import com.hihsoft.sso.business.model.TaclDutyuser;
import com.hihsoft.sso.business.model.TaclUserinfo;
import com.hihsoft.sso.business.model.TsysDuty;
import com.hihsoft.sso.business.model.TsysParameter;
import com.hihsoft.sso.business.service.TaclDutyuserService;
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
	@Autowired
	private TaclDutyuserService taclDutyuserService;
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
	@SuppressWarnings("unchecked")
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
				userList = (List<TaclUserinfo>) taclUserinfoService.getTaclUserinfoByHQL(hql);	//取得当前岗位下用户
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

	/**
	 * 查询
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ControllerException
	 */
	@RequestMapping(params="method=showDutyInfo")
	public ModelAndView showDutyInfo(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
//		String hql = "from TsysDuty t where 1=1";
//		Map<String, Object> filter = WebUtils.getParametersStartingWith(
//				request, "srh_");// 得到查询条件
//		String orders = getParam(request, "orders");
//		
//		String dutyname = (String) filter.get("dutyname");
//		if (StringUtils.isNotEmpty(dutyname)) {
//			hql += " and t.dutyname like '%" + dutyname + "%'";
//		}
//		
//		if (orders != null && !"".equals(orders))
//			hql += " order by " + orders;
//		addOrders(request, orders);
		TsysDuty tsysDuty = new TsysDuty();
		ModelAndView mv = new ModelAndView("duty/tsysdutydetail");
		String hql = "select t.taclUserinfo from TaclDutyuser t where 1=1";
		String dutyid = getParam(request, "dutyid");
		if (StringUtils.isNotEmpty(dutyid)) {
			hql += " and t.tsysDuty.dutyid = '"+dutyid+"'";
			//查询duty信息 
			tsysDuty = tsysDutyService.getTsysDutyById(dutyid);
			tsysDuty.setDutySort(ParamUtil.getInstance().getValByKey("DUTYSORT", tsysDuty.getDutySort()));
			tsysDuty.setDutyType(ParamUtil.getInstance().getValByKey("dutytype", tsysDuty.getDutyType()));
		}
		List<TaclUserinfo> list = calcPage(request, taclUserinfoService, hql);
		Map<String, String> params = new HashMap<String, String>();
		params.put("usertype", "userType");
		params.put("sex", "sex");
		params.put("userstate", "userState");
		convertParam(list, params);
		mv.addObject("list", list);
		mv.addObject("tsysDuty",tsysDuty);
//		mv.addObject("dutyname",dutyname);
		return mv;
	}
	
	/**
	 * 岗位分配用户初始化
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @threwos ControllerException
	 * */
	@RequestMapping(params="method=assinUser")
	public ModelAndView assinUser(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		ModelAndView mv = new ModelAndView("duty/tacluserinfolist4duty");
		String hql = "from TaclUserinfo t where 1=1 ";
		TaclUserinfo taclUserinfo = new TaclUserinfo();
//		Map<String, Object> filter = WebUtils.getParametersStartingWith(
//				request, "srh_");// 得到查询条件
		this.bind(request, taclUserinfo);
		String pageSize = getParam(request, "rows");
		String pageNo = getParam(request, "page");
		String orders = getParam(request, "orders");
		String loginname = getParam(request,"srh_loginname");
		// String usertype = getParam(request, "usertype");
		String mobile = getParam(request,"srh_mobile");
		String orgid = getParam(request, "orgid");
		String dutyid = getParam(request, "dutyid");
		TaclUserinfo user = (TaclUserinfo) request.getSession().getAttribute(
				Consts.USER_INFO);
		if (StringHelpers.notNull(user.getOrgid())) {
			hql += " and t.orgid in ("
					+ baseService.getPrivilege(user.getUserid()) + ")";
		}
		if (StringUtils.isNotEmpty(loginname)) {
			hql += " and t.loginname like '%" + loginname + "%'";
		}
		if (StringUtils.isNotEmpty(mobile)) {
			hql += " and t.mobile like '%" + mobile + "%'";
		}
//		if (StringUtils.isNotEmpty(dutyid)) {
//			hql += " and t.dutyid != '" + dutyid + "'";
//		}
		if (StringUtils.isNotEmpty(orgid)) {
			String[] ids = orgid.split(",");
			orgid = "";
			for (String str : ids) {
				orgid += "'" + str + "'";
				orgid += ",";
			}
			if(orgid.endsWith(",")){	
				orgid = orgid.substring(0, orgid.length()-1);
			}
			hql += " and t.orgid in ("+orgid+")";
			mv.addObject("orgid",orgid);
		} else {
			hql += " and t.orgid in ("+baseService.getPrivilege(getUserId(request))+")";
		}
		
		if (orders != null && !"".equals(orders))
			hql += " order by " + orders;
		addOrders(request, orders);
		if (pageSize == null)
			pageSize = "10";
		if (pageNo == null)
			pageNo = "1";
		/*
		 * List<TaclUserinfo> list = taclUserinfoService.getPageDataByHQL(hql,
		 * new Object[] {}, Integer.parseInt(pageSize),
		 * Integer.parseInt(pageNo));
		 */

		@SuppressWarnings("unchecked")
		List<TaclUserinfo> list = (List<TaclUserinfo>) taclUserinfoService.getValueObjectsByHQL(hql);
		convertParam(list, "usertype", "userType");
		// int total = taclUserinfoService.getDataTotalNum(hql);
		// int rows = Integer.parseInt(pageSize);
		// int pages = total % rows == 0 ? (total / rows) : (total / rows + 1);
//		List dutys = tsysDutyService.getAllTsysDuty();
//		mv.addObject("dutys", dutys);
		// mv.addObject("orgid",orgid);
		// mv.addObject("srh_loginname", loginname);
		// mv.addObject("usertype", usertype);
		// mv.addObject("srh_mobile", mobile);
		// mv.addObject("total", total);
		// mv.addObject("rows", pageSize);
		// mv.addObject("page", Integer.parseInt(pageNo));
		// mv.addObject("pages", pages);
		mv.addObject("taclUserinfoList", list);
		mv.addObject("dutyid", dutyid); // dutyID
		return mv;
	}
	
	/**
	 * 分配用户
	 * 
	 * @param request
	 * @param response
	 * @return mv
	 * @throws ControllerException
	 * */
	@RequestMapping(params="method=doAssinUser")
	public ModelAndView doAssinUser(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		String ids = request.getParameter("ids"); // 用户ID
		String dutyid = request.getParameter("dutyid"); // 岗位ID
		List<TaclDutyuser> taclDutyusers = tsysDutyService.getTaclDutyuserByDutyid(dutyid);
		if (ids != null && !"".equals(ids)) {
			String arr[] = ids.split(","); // 拆分多个用户ID
			for (String userId : arr) {
				boolean found = false;
				for (TaclDutyuser taclUser : taclDutyusers) {
					if (taclUser.getUserid().equals(userId)
							&& taclUser.getDutyid().equals(dutyid)) {
						found = true;
						log.info(userId + "已经存在-------------");
						continue;
					}
				}
				if (!found) {
					TaclDutyuser taclUser = new TaclDutyuser();
					taclUser.setDutyid(dutyid);
					taclUser.setUserid(userId);
					taclDutyuserService.saveOrUpdateTaclDutyuser(taclUser);
					log.info("为岗位" + dutyid + "分配了用户" + userId);
				}
			}
		}
		JSONObject json = new JSONObject();
		json.put("success", true);
		json.put("dutyid", dutyid);
//		renderJson(response, toJson("success", true));
		renderJson(response, JsonUtil.toString(json));
		return null;
	}

	/**
	 * 取消分配
	 * 
	 * @param request
	 * @param response
	 * @return mv
	 * @throws ControllerException
	 * */
	@RequestMapping(params="method=cancel")
	public ModelAndView cancel(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		String ids = request.getParameter("ids"); // 用户ID
		String dutyid = request.getParameter("dutyid"); // 岗位ID
		if (ids != null && !"".equals(ids)) {
			String arr[] = ids.split(",");
			for (String userId : arr) {
				String hql = "from TaclDutyuser where userid = '" + userId
						+ "' and dutyid ='" + dutyid + "'";
				@SuppressWarnings("unchecked")
				List<TaclDutyuser> taclDutyusers = (List<TaclDutyuser>) taclDutyuserService.getValueObjectsByHQL(hql);
				taclDutyuserService.deleteBatchVO(taclDutyusers);
			}
		}
		JSONObject json = new JSONObject();
		json.put("success", true);
		json.put("dutyid", dutyid);
//		renderJson(response, toJson("success", true));
		renderJson(response, JsonUtil.toString(json));
		return null;
	}
}
