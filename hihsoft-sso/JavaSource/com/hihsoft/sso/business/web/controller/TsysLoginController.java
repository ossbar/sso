package com.hihsoft.sso.business.web.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hihframework.core.utils.Eryptogram;
import com.hihframework.core.utils.ParamUtil;
import com.hihframework.core.utils.StringHelpers;
import com.hihframework.exception.ControllerException;
import com.hihframework.osplugins.json.JsonUtil;
import com.hihsoft.baseclass.constants.Consts;
import com.hihsoft.baseclass.web.controller.javahihBaseController;
import com.hihsoft.sso.business.model.TaclUserinfo;
import com.hihsoft.sso.business.model.TsysFlat;
import com.hihsoft.sso.business.service.TaclUserinfoService;
import com.hihsoft.sso.business.service.TsysFlatService;
import com.hihsoft.sso.business.service.TsysUploadService;

/**
 * Title:系统登录主界面
 * Description:
 * Copyright: Copyright (c) 2013
 * Company:hihsoft.co.,ltd
 * @author hihsoft.co.,ltd
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/tsysLoginController.do")
public class TsysLoginController extends javahihBaseController {
	@Autowired
	private TsysFlatService tsysFlatService;
	@Autowired
	private TaclUserinfoService taclUserinfoService;
	@Autowired
	private TsysUploadService tsysUploadService;
//	@Autowired
//	private TlogLoginlogService tlogLoginlogService;
	
	/**
	 * 访问列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params="method=index_bak",method=RequestMethod.POST)
	public ModelAndView index_bak(final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView(v_list);
		if (StringHelpers.notNull(getParam(request, "test")))
			return mv;
		String jumpTo = getParam(request, "jumpTo");
		if (StringHelpers.notNull(jumpTo)) {
			clearSession(request);
			response.sendRedirect(jumpTo);
			return null;
		}

		String u = getParam(request, "u");
		String p = getParam(request, "p");
		TaclUserinfo user = null;
		if (StringHelpers.notNull(u) && StringHelpers.notNull(p)) {
			u = URLDecoder.decode(u, "UTF-8");
			user = taclUserinfoService.getUserByLoginName(u);
			if (user != null && p.equals(user.getUserpw())) {
				ModelAndView login = login(request, user, mv);
				if (login != null)
					return login;
			}
		} else {
			user = (TaclUserinfo) request.getSession().getAttribute(
					Consts.USER_INFO);
			if (user == null) {
				String username = getParam(request, "username");
				String password = getParam(request, "password");

				if (StringHelpers.isNull(username)
						|| StringHelpers.isNull(password)) {
					mv.addObject("msg", "用户名和密码不能为空！");
					return mv;
				}
				user = taclUserinfoService.getUserByLoginName(username);
				String pwd = Eryptogram.encrypt(password);
				if (user == null) {
					mv.addObject("msg", "该用户不存在！");
					return mv;
				}
				if (!user.getUserpw().equals(pwd)) {
					mv.addObject("msg", "密码不正确！");
					return mv;
				}
				String state = ParamUtil.getInstance().getValByKey("userState", "正常");
				if (StringHelpers.notNull(state)
						&& !state.equals(user.getUserstate())) {
					mv.addObject("msg", "该用户已停用！");
					return mv;
				}
				ModelAndView login = login(request, user, mv);
				if (login != null)
					return login;
			}
		}
		if (user != null) {
			List<TsysFlat> flatList = tsysFlatService.getFlatsByUser(user
					.getUserid());
			request.setAttribute("flatlist", flatList);
			String lastUrl = request.getParameter("url");
			if (StringHelpers.notNull(lastUrl)) {
				String url = Eryptogram.decrypt(lastUrl);
				response.sendRedirect(url);
				return null;
			}
			TsysFlat flat = flatList.get(0);
			String flatid = getParam(request, "f");
			if (StringHelpers.isNull(flatid) && flat != null) {
				flatid = flat.getFlatid();
				String ctx = request.getContextPath();
				String url = flat.getFlaturl();
				if (url.indexOf(ctx) == -1) {

					url += "?method=index&u="
							+ URLEncoder.encode(URLEncoder.encode(user
									.getLoginname(), "UTF-8"), "UTF-8") + "&p="
							+ user.getUserpw() + "&f=" + flatid;

					clearSession(request);
					response.sendRedirect(url);
				}
			}
			putSession(request, "curFlatId", flatid);
		}
		mv.addObject("menus", tsysUploadService.getAllTsysUpload());
		mv.setViewName(v_index);
		return mv;
	}

	/**
	 * 访问列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params="method=index")
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		ModelAndView mv = new ModelAndView("index");
		if (StringHelpers.notNull(getParam(request, "test"))) {
			return null;
		}
		String jumpTo = getParam(request, "jumpTo");
		if (StringHelpers.notNull(jumpTo)) {
			clearSession(request);
			try {
				response.sendRedirect(jumpTo);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		TaclUserinfo user = getUser(request);
		if (user != null) {
			ModelAndView login = login(request, user, mv);
			if (login != null) return login;
			mv.setViewName("/common/main");
			return mv;
		}
		String username = getParam(request, "u");
		String password = getParam(request, "p");
		if (StringHelpers.isNull(username)
				|| StringHelpers.isNull(password)) {
			mv.addObject("msg", "用户名密码不能为空！");
			return mv;
		}
		try {
			username = URLDecoder.decode(username, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<TaclUserinfo> list = taclUserinfoService.getValueObjectsByHQL(
				"from TaclUserinfo where loginname=?", username);
		if (list.size() > 0)
			user = list.get(0);
		if (user != null) {
			String p = Eryptogram.getUserPasswd(password);
			String pwd = user.getUserpw();
			boolean passwordEq = pwd.equals(password) || pwd.equals(p);
			if (!passwordEq) {
				mv.addObject("msg", "密码不正确！");
				return mv;
			}
			ModelAndView login = login(request, user, mv);
			if (login != null) return login;
			String lastUrl = request.getParameter("url");
			if (StringHelpers.notNull(lastUrl)) {
				String url = Eryptogram.decrypt(lastUrl);
				try {
					response.sendRedirect(url);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}
			mv.setViewName("/common/main");
		} else {
			mv.addObject("msg", "不存在该用户");
		}
		return mv;
	}
	
	private ModelAndView login(HttpServletRequest request, TaclUserinfo user,
			ModelAndView mv) {
		Map<String, Map<String, String>> roles = taclUserinfoService
				.getUserRoles(user);

		if (roles == null || roles.isEmpty()) {
			mv.addObject("msg", "该用户没有授权，请联系管理员");
			return mv;
		}
		
		String state = ParamUtil.getInstance().getValByKey("userState", "正常");
		log.info("用户当前状态"+state);
			if (!state.equals(user.getUserstate())) {
			mv.addObject("msg", "该用户已停用！");
			return mv;
		}
		putSession(request, Consts.USER_PRIVILEGES_DATA, roles);
		putSession(request, Consts.USER_INFO, user);
		putSession(request, Consts.USER_ID, user.getUserid());
		if (user.getTsysOrg() != null) {
			putSession(request, Consts.CUR_ORGID, user.getTsysOrg().getOrgid());
			putSession(request, Consts.USER_DEFAULT_ORG, user.getTsysOrg());
		}
		
		List<TsysFlat> flatList = tsysFlatService.getFlatsByUser(user
				.getUserid());
		request.setAttribute("flatlist", flatList);
		String flatid = getParam(request, "f");
		log.info("登录的平台"+flatid);
		if (StringHelpers.isNull(flatid)) flatid = flatList.get(0).getFlatid();
		putSession(request, "curFlatId", flatid);
		
		// add by zhujw 登录日志
//		TlogLoginlog tlogLoginlog = new TlogLoginlog();
//		tlogLoginlog.setOrgid(user.getTsysOrg().getOrgid());
//		tlogLoginlog.setUserid(user.getUserid());
//		tlogLoginlog.setLogintime(DateUtils.getNowDateTime());
//		tlogLoginlog.setIpaddr(request.getRemoteAddr());
//		taclUserinfoService.saveOrUpdateVO(tlogLoginlog);
		return null;
	}

	/**
	 * 進入登录頁面，加载各子系统数据
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params="method=loginpage")
	public ModelAndView loginpage(final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		final List flatList = tsysFlatService.getAllTsysFlat();
		request.setAttribute("flatList", flatList);
		return new ModelAndView("index");
	}

	/**
	 * 注销登陆
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author Xiaojf
	 * @since 2011-6-11
	 */
	@RequestMapping(params="method=logout")
	public ModelAndView logout(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
//		String casUser = request.getRemoteUser();
		String jumpTo = getParam(request, "jumpTo");
		if (StringHelpers.notNull(jumpTo)) {
			request.getSession().invalidate();
			response.sendRedirect(jumpTo);
			return null;
		}
		String userId = getUserId(request);
		List<TsysFlat> flatList = tsysFlatService.getFlatsByUser(userId);
		if (!flatList.isEmpty()) {
			String url = flatList.get(0).getFlaturl();
			request.getSession().invalidate();
			response.sendRedirect(url);
		}
		return null;
//		if (casUser != null) {
//			String casServerLogoutUrl = (String) request.getSession()
//					.getServletContext().getInitParameter("casServerLogoutUrl");
//			return new ModelAndView("redirect:" + casServerLogoutUrl);
//		} else {
//			return new ModelAndView(v_list);
//		}
	}

	/**
	 * 获取用户菜单
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 * @author Xiaojf
	 * @since 2011-6-13
	 */
	@RequestMapping(params="method=getMenus")
	public void getMenus(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String flatid = getParam(request, "flatid");
		String menus = baseService.getMenus(getUser(request), flatid);
		renderJson(response, menus);
	}
	@RequestMapping(params="method=getFlats")
	public void getFlats(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<?> flats = tsysFlatService.getAllTsysFlat();
		String json = JsonUtil.toString(flats, JsonUtil.COLLECTION_FILTER);
		renderJson(response, json);
	}

}
