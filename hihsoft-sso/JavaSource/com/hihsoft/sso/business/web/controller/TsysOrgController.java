package com.hihsoft.sso.business.web.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.hihframework.exception.ControllerException;
import com.hihsoft.baseclass.web.controller.javahihBaseController;
import com.hihsoft.sso.business.model.TsysDataprivilege;
import com.hihsoft.sso.business.model.TsysOrg;
import com.hihsoft.sso.business.model.TsysTreeprivilege;
import com.hihsoft.sso.business.service.TsysDataprivilegeService;
import com.hihsoft.sso.business.service.TsysOrgService;
import com.hihsoft.sso.systempublic.constants.Constant;
import com.mysql.jdbc.StringUtils;

@Controller
@RequestMapping("/tsysOrgController.do")
public class TsysOrgController extends javahihBaseController {
	@Autowired
	private TsysOrgService tsysOrgService;
	@Autowired
	private TsysDataprivilegeService tsysDataprivilegeService;

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
		return new ModelAndView("org/tsysorglist");
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
		ModelAndView mv = new ModelAndView("org/tsysorgform");
		String orgid = request.getParameter("orgid");
		if (!StringUtils.isNullOrEmpty(orgid)) {
			TsysOrg org = tsysOrgService.getTsysOrgById(orgid);
			mv.addObject("parentorgid", org.getOrgid());
			mv.addObject("orgname", org.getOrgname());
		}
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
	public ModelAndView view(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		ModelAndView mv = new ModelAndView("org/tsysorgform");
		String id = request.getParameter("orgid");
		TsysOrg tsysOrg = tsysOrgService.getTsysOrgById(id);
		mv.addObject("org", tsysOrg);
		return mv;
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
		TsysOrg tsysOrg = new TsysOrg();
		this.setValue(request, tsysOrg);
		tsysOrg.setOrgstate(Constant.ORG_STATUS_NORMAL);
		try {
			tsysOrgService.saveOrUpdateTsysOrg(tsysOrg);
			String userId = getUserId(request);
			TsysDataprivilege dp = new TsysDataprivilege();
			dp.setOrgid(tsysOrg.getOrgid());
			dp.setUserid(userId);
			TsysTreeprivilege tp = new TsysTreeprivilege();
			tp.setOrgid(tsysOrg.getOrgid());
			tp.setUserid(userId);
			tsysDataprivilegeService.saveOrUpdateTsysDataprivilege(dp);
			tsysDataprivilegeService.saveOrUpdateVO(tp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(new RedirectView(request.getContextPath()+"/tsysOrgController.do?method=list"));
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
		String id = request.getParameter("orgid");
		String result = tsysOrgService.deleteTsysOrg(id);
		renderJson(response, toJson(result, true));
		return null;
//		return new ModelAndView(v_success);
	}

}
