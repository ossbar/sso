/**
 * Copyright (c) 2013-2015 www.javahih.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.hihsoft.sso.business.web.controller;
import java.util.ArrayList;
import java.util.HashMap;
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

import com.hihframework.core.utils.Eryptogram;
import com.hihframework.core.utils.ParamUtil;
import com.hihframework.core.utils.StringHelpers;
import com.hihframework.exception.ControllerException;
import com.hihframework.osplugins.json.JsonUtil;
import com.hihsoft.baseclass.web.controller.javahihBaseController;
import com.hihsoft.sso.business.model.TaclDutyuser;
import com.hihsoft.sso.business.model.TaclRole;
import com.hihsoft.sso.business.model.TaclRoleuser;
import com.hihsoft.sso.business.model.TaclUserinfo;
import com.hihsoft.sso.business.model.TsysOrg;
import com.hihsoft.sso.business.service.TaclRoleService;
import com.hihsoft.sso.business.service.TaclRoleuserService;
import com.hihsoft.sso.business.service.TaclUserinfoService;
import com.hihsoft.sso.business.service.TsysDutyService;
import com.hihsoft.sso.business.service.TsysOrgService;

/**
 * Title:
 * Description:用戶基本信息的服務
 * Copyright: Copyright (c) 2013
 * Company:hihsoft.co.,ltd
 * @author hihsoft.co.,ltd
 * @version 1.0
 */
@Controller
@RequestMapping("/taclUserinfoController.do")
public class TaclUserinfoController extends javahihBaseController {
	@Autowired
	private TaclUserinfoService taclUserinfoService;
	@Autowired
	private TaclRoleuserService taclRoleuserService;
	@Autowired
	private TaclRoleService taclRoleService;
	@Autowired
	private TsysOrgService tsysOrgService;
	@Autowired
	private TsysDutyService tsysDutyService;
	@RequestMapping(params="method=getTree")
	public ModelAndView getTree(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView("userinfo/tacluserinfotree");
		return mv;
	}
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
		ModelAndView mv = new ModelAndView("userinfo/tacluserinfolist");
		String hql = "from TaclUserinfo where 1=1 ";
		String orders = getParam(request, "orders");
		String orgid = getParam(request, "orgid");
		if (StringHelpers.isNull(orgid)) {
			orgid = taclUserinfoService.getPrivilege(getUserId(request));
		} else {
			orgid = taclUserinfoService.getPrivilege(getUserId(request), orgid);
		}
		hql +=  " and orgid in (" + orgid + ")";
		Map<String, Object> filter = WebUtils.getParametersStartingWith(
				request, "srh_");// 得到查询条件
		Set<String> set = filter.keySet();
		String state = (String) filter.get("userstate");
		if (StringHelpers.isNull(state)) {
			hql += " and userstate!='2'";
		}
		for (Iterator<String> it = set.iterator(); it.hasNext();) {
			String key = it.next();
			Object value = filter.get(key);
			if (key.equals("orgid")) continue;
			if (StringHelpers.notNull(value)) {
				hql += " and " + key + " like '%" + value + "%'";
				mv.addObject("srh_"+key, value);
			}
		}
		if (orders != null && !"".equals(orders))
			hql += " order by " + orders;
		addOrders(request, orders);
		List<TaclUserinfo> list = calcPage(request, taclUserinfoService, hql);
		Map<String, String> params = new HashMap<String, String>();
		params.put("usertype", "userType");
		params.put("sex", "sex");
		params.put("userstate", "userState");
		convertParam(list, params);
		mv.addObject("list", list);
	    return mv;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params="method=listRole")
	public ModelAndView listRole(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView("userinfo/definerole");
		String userId = getParam(request, "userId");
		String hql = "from TaclRole t where 1=1 ";
		if (isGroup(request, response)) {
			hql += " and t.roleSort =1 or t.roletype=1";
		} else {
			hql += " and (t.orgid in('"
					+ getOrgId(request)
					+ "') and t.roleSort=2) or (t.roletype in("
					+ getRoleTypeLower(request, response)
					+ ") and t.roleSort=1)";
		}
		List<TaclRole> roles = taclRoleService.getTaclRoleByHQL(hql);
		if (StringHelpers.notNull(userId) && userId.indexOf(",") == -1) {
			List<TaclRole> defiends = taclRoleuserService.getDefinedRole(userId);
			for (Iterator<TaclRole> it = roles.iterator(); it.hasNext();) {
				TaclRole role = it.next();
				if (defiends.contains(role)) {
					it.remove();
				}
			}
			mv.addObject("defiends", defiends);
		}
		mv.addObject("roles", roles);
		mv.addObject("userId", userId);
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
		ModelAndView mv = new ModelAndView("userinfo/tacluserinfoform");
		String orgid = getParam(request, "orgid");
		String deptid = getParam(request, "deptid");
		if (StringHelpers.notNull(orgid)) {
			TsysOrg org = tsysOrgService.getTsysOrgById(orgid);
			mv.addObject("org", org);
		}
		List dutys = tsysDutyService.getAllTsysDuty();
		mv.addObject("dutys", dutys);
		return mv;
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
			HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView("userinfo/tacluserinfoform");
		String id=request.getParameter("id");
		TaclUserinfo taclUserinfo=taclUserinfoService.getTaclUserinfoById(id);
		String pwd = taclUserinfo.getUserpw();
		List dutys = tsysDutyService.getAllTsysDuty();
		mv.addObject("dutys", dutys);
		mv.addObject("org", taclUserinfo.getTsysOrg());
//		mv.addObject("dept", taclUserinfo.getTsysDept());
	    mv.addObject("taclUserinfo", taclUserinfo);
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
		ModelAndView mv = new ModelAndView("userinfo/userdetail");
		String id=request.getParameter("id");
		TaclUserinfo taclUserinfo=taclUserinfoService.getTaclUserinfoById(id);
		List<TaclRole> roles = taclRoleuserService.getDefinedRole(id);
		String dutySetName = taclUserinfoService.getDutyAllNameByUserId(id);
		String[] roleIds = new String[roles.size()];
		int index = 0;
		for (TaclRole role : roles) {
			roleIds[index++] = role.getRoleid();
		}
		String rids = StringHelpers.join(roleIds, ",", null);
		Map<String, String> params = new HashMap<String, String>();
		params.put("usertype", "userType");
		params.put("sex", "sex");
		params.put("userstate", "userState");
		convertParam(taclUserinfo, params);
	    mv.addObject("taclUserinfo", taclUserinfo);
	    mv.addObject("roles", roles);
	    mv.addObject("org", taclUserinfo.getTsysOrg());
		mv.addObject("duty", taclUserinfo.getTsysDuty());
		mv.addObject("roleIds", rids);
		mv.addObject("dutySetName", dutySetName);
		return mv;
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
		TaclUserinfo taclUserinfo=taclUserinfoService.getTaclUserinfoById(getParam(request,"userid"));
		TaclDutyuser TaclDutyuser=new TaclDutyuser();
		if(StringHelpers.isNull(taclUserinfo)){
			taclUserinfo=new TaclUserinfo();
	    }
	    this.setValue(request,taclUserinfo);
	    String pwdStr = taclUserinfo.getUserpw();
	    if (StringHelpers.isNull(pwdStr)) {
	    	pwdStr = Eryptogram.getUserPasswd("1234");
	    	taclUserinfo.setUserpw(pwdStr);
	    }
	    String state = taclUserinfo.getUserstate();
	    if (StringHelpers.isNull(state)) {
	    	taclUserinfo.setUserstate(ParamUtil.getInstance().getValByKey("userState", "正常"));
	    }
	    String dataSet = getParam(request, "dataSet");
	    String treeSet = getParam(request, "treeSet");
	    //支持單個用戶多個崗位
	//  String dutySet=getParam(request, "dutySet");
	    String dutySet=getParam(request, "dutyid");
	    
	    boolean flag = true;
	    try {
		    taclUserinfoService.saveOrUpdateTaclUserinfo(taclUserinfo);
		    taclUserinfoService.saveOrUpdateTaclDutyUser(taclUserinfo.getUserid(), dutySet);
		    taclUserinfoService.saveDataSet(taclUserinfo.getUserid(), dataSet);
		    taclUserinfoService.saveTreeSet(taclUserinfo.getUserid(), treeSet);
	    } catch (Exception e) {
	    	logger.error(e);
	    	flag = false;
	    }
	    renderJson(response, toJson("success", flag));
		return null;
	}
	
	/**
	 * 定义用户所属角色
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author Xiaojf
	 * @since 2011-6-2
	 */
	@RequestMapping(params="method=saveRoleSet")
	public ModelAndView saveRoleSet(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String userId = getParam(request, "userId");
		String roleStr = getParam(request, "ids");
		String[] roleIds = roleStr.split(",");
		String[] userIds = userId.split(",");
		ArrayList<TaclRoleuser> list = new ArrayList<TaclRoleuser>();
		taclRoleuserService.executeHQL("delete from TaclRoleuser where userid in(" + StringHelpers.join(userIds, ",", "'") + ")");
		for (String uid : userIds)
			for (String roleId : roleIds) {
				if (StringHelpers.isNull(roleId)) continue;
				TaclRoleuser tr = new TaclRoleuser();
				tr.setRoleid(roleId);
				tr.setUserid(uid);
				list.add(tr);
			}
		taclRoleuserService.saveOrUpdateBatchVO(list);
		renderJson(response, toJson("success", true));
		return null;
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
		taclUserinfoService.deleteTaclUserinfo(ids);
		renderJson(response, toJson("success", true));
		return null;
	}
	/**
	 * 收回权限
	 * @param request
	 * @param response
	 * @throws Exception
	 * @author Xiaojf
	 * @since 2011-6-25
	 */
	@RequestMapping(params="method=clearRole")
	public void clearRole(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String id = getParam(request, "id");
		taclUserinfoService.clearRole(id);
		renderJson(response, toJson("success", true));
	}
	/**
	 * 取得当前用户已有权限及特权树
	 * @param request
	 * @param response
	 * @throws Exception
	 * @author Xiaojf
	 * @since 2011-6-27
	 */
	@RequestMapping(params="method=getPrivilegeTree")
	public void getPrivilegeTree(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userId = getParam(request, "userId");
		if (StringHelpers.isNull(userId)) return;
		String json = taclUserinfoService.getPrivilegeTree(userId, getUserId(request));
		renderJson(response, json);
	}
	/**
	 * 跳转到特权设置页面
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author Xiaojf
	 * @since 2011-6-28
	 */
	@RequestMapping(params="method=privilege")
	public ModelAndView privilege(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userId = getParam(request, "userId");
		ModelAndView mv = new ModelAndView("userinfo/userprivilege");
		mv.addObject("userId", userId);
		return mv;
	}
	/**
	 * 保存特权设置
	 * @param request
	 * @param response
	 * @throws Exception
	 * @author Xiaojf
	 * @since 2011-6-28
	 */
	@RequestMapping(params="method=savePrivilege")
	public void savePrivilege(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String moduleSet = getParam(request, "moduleSet");
		String dataSet = getParam(request, "dataSet");
		String treeSet = getParam(request, "treeSet");
		String userId = getParam(request, "userId");
		taclUserinfoService.savePrivilege(userId, moduleSet);
		taclUserinfoService.saveDataSet(userId, dataSet);
		taclUserinfoService.saveTreeSet(userId, treeSet);
		renderJson(response, toJson("success", true));
	}
	/**
	 * 检查用户名是否已经存在
	 * @param request
	 * @param response
	 * @throws Exception
	 * @author Xiaojf
	 * @since 2011-7-5
	 */
	@RequestMapping(params="method=testUserName")
	public void testUserName(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String uname = getParam(request, "fieldValue");
		String fieldId = getParam(request, "fieldId");
		TaclUserinfo userinfo = taclUserinfoService.getUserByLoginName(uname);
		boolean flag = userinfo == null;
		renderJson(response, toJsArray(fieldId, flag ? "" + flag : "", "ajaxNameCall"));
	}
	
	@Override
	@RequestMapping(params="method=getModuleTree")
	public ModelAndView getModuleTree(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		String userId = getParam(request, "userId");
		String json = null;
		try {
			List<Map<String,Object>> tree = taclUserinfoService.getModuleTree(userId);
			json = JsonUtil.toString(tree);
		} catch (Exception e) {
			json = "[]";
		}
		renderJson(response, json);
		return null;
	}
	
	@Override
	@RequestMapping(params="method=getOrgTree")
	public ModelAndView getOrgTree(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		String userId = getParam(request, "userId");
		String json = null;
		try {
//			String hql = "from TsysDataprivilege where userid=?";
//			List<TsysDataprivilege> assigned = baseService.getValueObjectsByHQL(hql, userId);
			String sql = "select orgid from t_sys_dataprivilege where userid=?";
			Map<String, Object> assigned = baseService.queryAsMapBySQL(sql, userId);
			List<Map<String,Object>> tree = baseService.getAssignedOrgTree(getUserId(request), null);
			eachMap(tree, assigned);
			json = JsonUtil.toString(tree);
		} catch (Exception e) {
			json = "[]";
		}
		renderJson(response, json);
		return null;
	}
	@SuppressWarnings("unchecked")
	private void eachMap(List<Map<String,Object>> tree, Map<String, Object> assigned) {
		for (Map<String, Object> map : tree) {
			if (assigned.get((map.get("id"))) != null) {
				map.put("checked", true);
			} else {
				map.put("checked", false);
			}
			List<Map<String,Object>> children = (List<Map<String, Object>>) map.get("children");
			if (children != null) {
				eachMap(children, assigned);
			}
		}
	}
	@RequestMapping(params="method=password")
	public ModelAndView password(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView("userinfo/tacluserinfopassword");
	}
}

