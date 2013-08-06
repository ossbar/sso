/**
 * Copyright (c) 2013-2015 www.javahih.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.hihsoft.sso.business.web.controller;

import java.io.UnsupportedEncodingException;
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
import org.springframework.web.util.WebUtils;

import com.hihframework.core.utils.StringHelpers;
import com.hihframework.exception.ControllerException;
import com.hihframework.osplugins.json.JsonUtil;
import com.hihsoft.baseclass.constants.Consts;
import com.hihsoft.baseclass.web.controller.javahihBaseController;
import com.hihsoft.sso.business.model.TaclRole;
import com.hihsoft.sso.business.model.TaclRoleuser;
import com.hihsoft.sso.business.model.TaclUserinfo;
import com.hihsoft.sso.business.model.TsysOrg;
import com.hihsoft.sso.business.model.TsysParameter;
import com.hihsoft.sso.business.service.TaclRoleService;
import com.hihsoft.sso.business.service.TaclRoleprivilegeService;
import com.hihsoft.sso.business.service.TaclRoleuserService;
import com.hihsoft.sso.business.service.TaclUserinfoService;
import com.hihsoft.sso.business.service.TsysDutyService;
import com.hihsoft.sso.business.service.TsysOrgService;
/**
 * Title:角色控制层
 * Description:
 * Copyright: Copyright (c) 2013
 * Company:hihsoft.co.,ltd
 * @author hihsoft.co.,ltd
 * @version 1.0
 */
@Controller
@RequestMapping("/taclRoleController.do")
public class TaclRoleController extends javahihBaseController {
	@Autowired
	private TaclRoleService taclRoleService;
	@Autowired
	private TaclRoleprivilegeService taclRoleprivilegeService; // role
	@Autowired
	private TaclUserinfoService taclUserinfoService; // userInfo
	@Autowired
	private TaclRoleuserService taclRoleuserService; // roleUser
	@Autowired
	private TsysDutyService tsysDutyService;
	@Autowired
	private TsysOrgService tsysOrgService;


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
			HttpServletResponse response) throws ControllerException {
		ModelAndView mv = new ModelAndView("role/taclrolelist");
		TaclUserinfo user = (TaclUserinfo) request.getSession().getAttribute(
				Consts.USER_INFO);
		String hql = "from TaclRole t where 1=1";
		TaclRole taclRole = new TaclRole();
		Map<String, Object> filter = WebUtils.getParametersStartingWith(
				request, "srh_");// 得到查询条件
		String rolename = (String) filter.get("rolename");
		boolean falg = true;
		if(StringHelpers.notNull(rolename)){
			hql += " and t.rolename like '%"+rolename+"%'";
			mv.addObject("rolename",rolename);
			falg = false;
		}
		/*if(StringHelpers.notNull(rolesort)){
			hql += " and t.rolesort = '"+rolesort+"'";
			mv.addObject("rolesort",rolesort);
		}*/
		boolean isGroup = isGroup(request, response); // 是否集团公司
		if (StringHelpers.notNull(user.getOrgid()) && falg) {
			if (isGroup) {
				hql += " and (t.roleSort =1 or t.roletype=1)";
			} else {
				TsysOrg orgid = tsysOrgService.getTsysOrgById(user.getOrgid());
				String orgids = orgid.getOrgid();
				if(orgid.getOrgSort().equals("2")){
					orgids = returnOrgId(orgid.getOrgid());
				}
				hql += " and (t.orgid in('" + orgids
						+ "') and t.roleSort=2) or (t.roletype in("
						+ getRoleTypeLower(request, response)
						+ ") and t.roleSort=1)";
			}
		}
		this.bind(request, taclRole);
		String pageSize = getParam(request, "rows");
		String pageNo = getParam(request, "page");
		String orders = getParam(request, "orders");

		if (orders != null && !"".equals(orders))
			hql += " order by " + orders;
		addOrders(request,orders);
		if (pageSize == null)
			pageSize = "15";
		if (pageNo == null)
			pageNo = "1";
		@SuppressWarnings("unchecked")
		List<TaclRole> list = (List<TaclRole>) taclRoleService.getPageDataByHQL(hql,
				Integer.parseInt(pageSize), Integer.parseInt(pageNo));
		for (TaclRole role : list) {
			TsysOrg org = role.getTsysOrg();
			if (org != null)
				role.setOrgid(org.getOrgname());
		}
		int total = taclRoleService.getDataTotalNum(hql);
		int rows = Integer.parseInt(pageSize);
		int pages = total % rows == 0 ? (total / rows) : (total / rows + 1);
		convertParam(list, "roletype", "roleType");
		convertParam(list, "roleSort", "rolesort");
		List<TsysParameter> selectRole = getRoleType(request, response);
		mv.addObject("isGroup", isGroup);
		mv.addObject("selectRole",selectRole);
		mv.addObject("total", total);
		mv.addObject("rows", pageSize);
		mv.addObject("page", Integer.parseInt(pageNo));
		mv.addObject("pages", pages);
		mv.addObject("list", list);
		mv.addObject("rolename", rolename);
		mv.addObject("isGroup", isGroup);
		return mv;
	}
	@RequestMapping(params="method=listRole")
	public ModelAndView listRole(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		ModelAndView mv = new ModelAndView("userinfo/authorize");
		String ids = getParam(request, "userIds");
		String rolename = getParam(request, "rolename");
		mv.addObject("userIds", ids);
		String hql = "from TaclRole";
		if (StringHelpers.notNull(rolename)) {
			try {
				rolename = java.net.URLDecoder.decode(rolename, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			hql += " where rolename like '%" + rolename + "%'";
		}
		List<?> list = calcPage(request, taclRoleService, hql);
		mv.addObject("list", list);
		return mv;
	}

	/**
	 * 進入新增頁面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ControllerException
	 */
	@RequestMapping(params="method=add")
	public ModelAndView add(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		ModelAndView mv = new ModelAndView("role/taclroleform");
		List<TsysParameter> selectRole = getRoleType(request, response);
		boolean isGroup = isGroup(request, response);
		mv.addObject("selectRole", selectRole);
		mv.addObject("isGroup", isGroup);
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
	@RequestMapping(params="method=modifyInit")
	public ModelAndView modifyInit(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		boolean isGroup = isGroup(request, response);
		String roleId = request.getParameter("id");
		TaclRole taclRole = taclRoleService.getTaclRoleById(roleId);
		ModelAndView mv = new ModelAndView("role/taclroleform");
		List<TsysParameter> selectRole = getRoleType(request, response);
		mv.addObject("isGroup", isGroup);
		mv.addObject("selectRole", selectRole);
		mv.addObject("taclRole", taclRole);
		mv.addObject("roleId", roleId);
		return mv;
	}

	/**
	 * 用户授权
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @threwos ControllerException
	 * */
	@RequestMapping(params="method=userRole")
	public ModelAndView userRole(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		ModelAndView mv = new ModelAndView("role/taclroleauthorization");
		String roleId = request.getParameter("roleId"); // 角色id
		String hql = "from TaclUserinfo t where 1=1 ";
		TaclUserinfo taclUserinfo = new TaclUserinfo();
		Map<String, Object> filter = WebUtils.getParametersStartingWith(
				request, "srh_");// 得到查询条件
		this.bind(request, taclUserinfo);
		String pageSize = getParam(request, "rows");
		String pageNo = getParam(request, "page");
		String orders = getParam(request, "orders");
		String loginname = (String) filter.get("loginname");
		// String usertype = getParam(request, "usertype");
		String mobile = (String) filter.get("mobile");
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
		if (StringUtils.isNotEmpty(dutyid)) {
			hql += " and t.dutyid like '%" + dutyid + "%'";
		}
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
		List<?> dutys = tsysDutyService.getAllTsysDuty();
		mv.addObject("dutys", dutys);
		// mv.addObject("orgid",orgid);
		// mv.addObject("srh_loginname", loginname);
		// mv.addObject("usertype", usertype);
		// mv.addObject("srh_mobile", mobile);
		// mv.addObject("total", total);
		// mv.addObject("rows", pageSize);
		// mv.addObject("page", Integer.parseInt(pageNo));
		// mv.addObject("pages", pages);
		mv.addObject("list", list);
		mv.addObject("roleId", roleId); // 角色ID
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
	@RequestMapping(params="method=authorization")
	public ModelAndView authorization(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		String ids = request.getParameter("ids"); // 用户ID
		String roleId = request.getParameter("roleId"); // 角色ID
		List<TaclRoleuser> roleList = taclRoleService
				.getTaclRoleUserByRoleId(roleId);
		if (ids != null && !"".equals(ids)) {
			String arr[] = ids.split(","); // 拆分多个用户ID
			for (String userId : arr) {
				boolean found = false;
				for (TaclRoleuser taclUser : roleList) {
					if (taclUser.getUserid().equals(userId)
							&& taclUser.getRoleid().equals(roleId)) {
						found = true;
						log.info(userId + "已经存在-------------");
						continue;
					}
				}
				if (!found) {
					TaclRoleuser roleUser = new TaclRoleuser();
					roleUser.setRoleid(roleId);
					roleUser.setUserid(userId);
					taclRoleuserService.saveOrUpdateTaclRoleuser(roleUser);
					log.info("为角色" + roleId + "分配了用户" + userId);
				}
			}
		}
		renderJson(response, toJson("success", true));
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
		String roleId = request.getParameter("roleId"); // 角色ID
		if (ids != null && !"".equals(ids)) {
			String arr[] = ids.split(",");
			for (String userId : arr) {
				String hql = "from TaclRoleuser where userid = '" + userId
						+ "' and roleid ='" + roleId + "'";
				@SuppressWarnings("unchecked")
				List<TaclRoleuser> roleUser = (List<TaclRoleuser>) taclRoleuserService
						.getTaclRoleuserByHQL(hql);
				taclRoleuserService.deleteBatchVO(roleUser);
			}
		}
		renderJson(response, toJson("success", true));
		return null;
	}

	/**
	 * 取消分配角色用户信息
	 * 
	 * @param request
	 * @param response
	 * @return mv
	 * @throws ControllerException
	 * */
	@RequestMapping(params="method=userView")
	public ModelAndView userView(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("role/taclroleview");
		String roleId = request.getParameter("id");
		List<TaclRoleuser> roleList = taclRoleService
				.getTaclRoleUserByRoleId(roleId);
		List<TaclUserinfo> userList = new ArrayList<TaclUserinfo>();
		for (TaclRoleuser taclUserinfo : roleList) {
			TaclUserinfo userInfo = taclUserinfoService
					.getTaclUserinfoById(taclUserinfo.getUserid());
			userList.add(userInfo);
		}
		TaclRole taclRole = taclRoleService.getTaclRoleById(roleId);
		convertParam(taclRole, "roletype", "roleType");
		mv.addObject("taclRole", taclRole);
		mv.addObject("userList", userList);
		mv.addObject("roleId", roleId);
		return mv;
	}

	/**
	 * 查看角色基本信息及分配的用戶及角色中的權限
	 * 
	 * @param request
	 * @param response
	 * @return mv
	 * @throws ControllerException
	 * */
	@RequestMapping(params="method=roleView")
	public ModelAndView roleView(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("role/taclrolerole");
		String roleId = request.getParameter("id");
		String dutyname="";
		List<TaclRoleuser> roleList = taclRoleService
				.getTaclRoleUserByRoleId(roleId);
		List<TaclUserinfo> userList = new ArrayList<TaclUserinfo>();
		for (TaclRoleuser taclUserinfo : roleList) {
			TaclUserinfo userInfo = taclUserinfoService
					.getTaclUserinfoById(taclUserinfo.getUserid());
			userList.add(userInfo);
			//modify by zhujw用戶多崗位顯示
			dutyname= taclUserinfoService.getDutyAllNameByUserId(taclUserinfo.getUserid());
		}
		TaclRole taclRole = taclRoleService.getTaclRoleById(roleId);
		convertParam(taclRole, "roletype", "roleType");
		mv.addObject("taclRole", taclRole);
		mv.addObject("userList", userList);
		mv.addObject("dutyname", dutyname);
		mv.addObject("roleId", roleId);
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
		TaclRole taclRole = taclRoleService.getTaclRoleById(id);
		this.bind(request, taclRole);
		return new ModelAndView("role/taclroleform");
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
		TaclRole taclRole = new TaclRole();
		TaclUserinfo user = (TaclUserinfo) request.getSession().getAttribute(
				Consts.USER_INFO);
		this.setValue(request, taclRole);
		String roleSort = request.getParameter("roleSort");
		taclRole.setRoleSort(roleSort);
		if (taclRole.getOrgid() == null) {
			taclRole.setOrgid(user.getOrgid());
		}
		taclRoleService.saveOrUpdateTaclRole(taclRole);
		String moduleSet = request.getParameter("moduleSet");
		boolean flag = taclRoleprivilegeService.saveRoleSet(
				taclRole.getRoleid(), moduleSet);
		renderJson(response, toJson("success", flag));
		return null;
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
		List<TaclRole> list = new ArrayList<TaclRole>();
		boolean flag = true;
		List<TaclRoleuser> roleList = new ArrayList<TaclRoleuser>();
		if (ids != null && !"".equals(ids)) {
			String[] arr = ids.split(",");
			for (String id : arr) {
				roleList = taclRoleService.getTaclRoleUserByRoleId(id); // 获得该角色下用户
				if (roleList.size() == 0) {
					TaclRole obj = new TaclRole();
					obj.setRoleid(id);
					String hql = "delete from TaclRoleprivilege where roleid='"
							+ id + "'"; // 删除角色时候删除树结构
					taclRoleprivilegeService.executeHQL(hql);
					list.add(obj);
				} else {
					flag = false;
				}
			}
		}
		taclRoleService.deleteBatchVO(list);
		renderJson(response, toJson("success", flag));
		return null;
	}
	@RequestMapping(params="method=getModuleTree")
	public ModelAndView getModuleTree(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		String roleId = getParam(request, "roleId");
		String curUserId = getUserId(request);
		String curOrgId = getOrgId(request);
		
		List<Map<String, Object>> tree = taclRoleService.getModuleTree(roleId, curUserId, curOrgId);
		renderJson(response, JsonUtil.toString(tree));
		return null;
	}

	
	//根据传入orgid , 获得当前父节点为机构的OrgId 
		public String returnOrgId(String orgId){
			TsysOrg org = tsysOrgService.getTsysOrgById(orgId);	//获得当前Org对象
			String parentoOrgId = org.getParentorgid();		//获得当前父节点Org
			TsysOrg parentoOrg = tsysOrgService.getTsysOrgById(parentoOrgId);
			if(parentoOrg.getOrgSort().equals("1")){
				//当前父节点为机构的话
				return parentoOrg.getOrgid();
			}
			return returnOrgId(parentoOrgId);		//传入父节点继续递归
		}
		
}