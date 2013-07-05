package com.hihsoft.baseclass.web.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.hihframework.core.utils.Eryptogram;
import com.hihframework.core.utils.ParamUtil;
import com.hihframework.core.utils.StringHelpers;
import com.hihframework.exception.ControllerException;
import com.hihframework.osplugins.json.JsonUtil;
import com.hihsoft.baseclass.constants.Consts;
import com.hihsoft.baseclass.service.BaseService;
import com.hihsoft.sso.business.model.TaclUserinfo;
import com.hihsoft.sso.business.model.TsysDuty;
import com.hihsoft.sso.business.model.TsysOrg;
import com.hihsoft.sso.business.model.TsysParameter;
import com.hihsoft.sso.business.model.TsysUpload;
import com.hihsoft.sso.business.service.TaclUserinfoService;
import com.hihsoft.sso.business.service.TsysDutyService;
import com.hihsoft.sso.business.service.TsysOrgService;
import com.hihsoft.sso.sysmonitor.sysaudit.model.AuditableEntity;

/**
 * <p>
 * Title:所有页面控制类的 基类，可以考虑继承它、获取更多约定的功能
 * Description:基于命名规则的CRUD Controller基类
 * Copyright: Copyright (c) 2013
 * Company:hihsoft.co.,ltd
  * @author hihsoft.co.,ltd
 * @version 1.0
 */
@Controller
public class javahihBaseController extends BaseController {
	@Autowired
	protected  BaseService baseService;
	
	/** The index. 默认为主页 */
	protected String v_index = null;

	/**
	 * Gets the v_index.
	 * 
	 * @return the v_index
	 */
	public String getV_index() {
		return v_index;
	}

	/**
	 * Sets the v_index.
	 * 
	 * @param v_index
	 *            the new v_index
	 */
	public void setV_index(final String v_index) {
		this.v_index = v_index;
	}

	/** The v_list：列表动作. */
	protected String v_list = null;

	/** The v_form.表单动作 */
	protected String v_form = null;

	/** The v_save.保存动作 */
	protected String v_save = null;

	/** The v_success.成功动作 */
	protected String v_success = null;

	/** The v_failure.失败动作 */
	protected String v_failure = null;

	/** The v_delete. 删除动作 */
	protected String v_delete = null;

	/** The Constant SUCCESS. 成功标识 */
	protected final static String SUCCESS = "1";

	/** The Constant FAILURE. 失败标识 */
	protected final static String FAILURE = "0";

	/**
	 * Gets the v_list.
	 * 
	 * @return the v_list
	 */
	public String getV_list() {
		return v_list;
	}
	/**
	 * Sets the v_list.
	 * 
	 * @param v_list
	 *            the new v_list
	 */
	public void setV_list(final String v_list) {
		this.v_list = v_list;
	}

	/**
	 * Gets the v_form.
	 * 
	 * @return the v_form
	 */
	public String getV_form() {
		return v_form;
	}

	/**
	 * Sets the v_form.
	 * 
	 * @param v_form
	 *            the new v_form
	 */
	public void setV_form(final String v_form) {
		this.v_form = v_form;
	}

	/**
	 * Gets the v_save.
	 * 
	 * @return the v_save
	 */
	public String getV_save() {
		return v_save;
	}

	/**
	 * Sets the v_save.
	 * 
	 * @param v_save
	 *            the new v_save
	 */
	public void setV_save(final String v_save) {
		this.v_save = v_save;
	}

	/**
	 * Gets the v_success.
	 * 
	 * @return the v_success
	 */
	public String getV_success() {
		return v_success;
	}

	/**
	 * Sets the v_success.
	 * 
	 * @param v_success
	 *            the new v_success
	 */
	public void setV_success(final String v_success) {
		this.v_success = v_success;
	}

	/**
	 * Gets the v_failure.
	 * 
	 * @return the v_failure
	 */
	public String getV_failure() {
		return v_failure;
	}

	/**
	 * Sets the v_failure.
	 * 
	 * @param v_failure
	 *            the new v_failure
	 */
	public void setV_failure(final String v_failure) {
		this.v_failure = v_failure;
	}

	/**
	 * Gets the v_delete.
	 * 
	 * @return the v_delete
	 */
	public String getV_delete() {
		return v_delete;
	}

	/**
	 * Sets the v_delete.
	 * 
	 * @param v_delete
	 *            the new v_delete
	 */
	public void setV_delete(final String v_delete) {
		this.v_delete = v_delete;
	}

	/**
	 * 设置默认函数为list().
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the model and view
	 * @throws ControllerException
	 *             the ControllerException
	 */
	@RequestMapping(params="method=index")
	public ModelAndView index(final HttpServletRequest request,
			final HttpServletResponse response) throws ControllerException {
		return list(request, response);
	}

	/**
	 * 显示对象列表页面的模板方法.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the model and view
	 * @throws ControllerException
	 *             the ControllerException
	 */
	@RequestMapping(params="method=list")
	public ModelAndView list(final HttpServletRequest request,
			final HttpServletResponse response) throws ControllerException {
		final ModelAndView mav = new ModelAndView(v_list);
		mav.addAllObjects(referenceData(request));
		list(request, response, mav);
		return mav;
	}

	/**
	 * 显示创建新对象的输入页面的模板方法.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the model and view
	 * @throws ControllerException
	 *             the ControllerException
	 */
	@RequestMapping(params="method=add")
	public ModelAndView add(final HttpServletRequest request,
			final HttpServletResponse response) throws ControllerException {
		final ModelAndView mav = new ModelAndView(v_form);
		mav.addAllObjects(referenceData(request));
		add(request, response, mav);
		return mav;
	}

	/**
	 * 保存新增、修改对象的模板方法.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the model and view
	 * @throws ControllerException
	 *             the ControllerException
	 */
	@RequestMapping(params="method=save")
	public ModelAndView save(final HttpServletRequest request,
			final HttpServletResponse response) throws ControllerException {
		final ModelAndView mav = new ModelAndView(v_success);
		save(request, response, mav, true);
		return mav;
	}

	/**
	 * 显示对象详情页面的模板方法。该页面中的对象属性不能修改.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the model and view
	 * @throws ControllerException
	 *             the ControllerException
	 */
	@RequestMapping(params="method=view")
	public ModelAndView view(final HttpServletRequest request,
			final HttpServletResponse response) throws ControllerException {
		final ModelAndView mav = new ModelAndView(v_form);
		mav.addAllObjects(referenceData(request));
		view(request, response, mav);
		return mav;
	}

	/**
	 * 删除选择的对象的模板方法.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the model and view
	 * @throws ControllerException
	 *             the ControllerException
	 */
	@RequestMapping(params="method=delete")
	public ModelAndView delete(final HttpServletRequest request,
			final HttpServletResponse response) throws ControllerException {
		final ModelAndView mav = new ModelAndView(v_success);
		mav.addAllObjects(referenceData(request));
		delete(request, response, mav);
		return mav;
	}

	/*
	 * 以下为回调函数列表,在各子类中实现:约定规则：list为查询列表；add为进入新增界面；view为明细页面；save为保存、修改页面；
	 * delete为删除页面
	 */

	/**
	 * List.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param mav
	 *            the mav
	 * @throws ControllerException
	 *             the ControllerException
	 */
	protected void list(final HttpServletRequest request,
			final HttpServletResponse response, final ModelAndView mav)
			throws ControllerException {
	}

	/**
	 * Adds the.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param mav
	 *            the mav
	 * @throws ControllerException
	 *             the ControllerException
	 */
	protected void add(final HttpServletRequest request,
			final HttpServletResponse response, final ModelAndView mav)
			throws ControllerException {
	}

	/**
	 * View.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param mav
	 *            the mav
	 * @throws ControllerException
	 *             the ControllerException
	 */
	protected void view(final HttpServletRequest request,
			final HttpServletResponse response, final ModelAndView mav)
			throws ControllerException {
	}

	/**
	 * Save.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param mav
	 *            the mav
	 * @param isNew
	 *            the is new
	 * @throws ControllerException
	 *             the ControllerException
	 */
	protected void save(final HttpServletRequest request,
			final HttpServletResponse response, final ModelAndView mav,
			final boolean isNew) throws ControllerException {
	}

	/**
	 * Delete.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param mav
	 *            the mav
	 * @throws ControllerException
	 *             the ControllerException
	 */
	protected void delete(final HttpServletRequest request,
			final HttpServletResponse response, final ModelAndView mav)
			throws ControllerException {
	}

	/**
	 * 将辅助显示的对象,如參數列表,状态列表 放入request的Attribute中. 实际调用
	 * {@link #referenceData(HttpServletRequest, Map)}, 多此一层封装仅仅为了初始化module.
	 * 
	 * @param request
	 *            the request
	 * @return the map
	 */
	protected Map referenceData(final HttpServletRequest request) {
		final Map model = new HashMap();
		referenceData(request, model);
		return model;
	}

	/**
	 * 将辅助显示的对象,如类别列表,状态列表 放入request的Attribute中. 在各子类实现.
	 * 
	 * @param request
	 *            the request
	 * @param model
	 *            the model
	 */
	protected void referenceData(final HttpServletRequest request,
			final Map model) {
		//ConfigLoader.listenForChanges();
	}

	/**
	 * 回调函数.校验出错时,将出错的对象及出错信息放回model. 在
	 * {@link #onSave(HttpServletRequest,HttpServletResponse,ModelAndView,boolean)}
	 * 中调用.
	 * 
	 * @param request
	 *            the request
	 * @param result
	 *            the result
	 * @param mav
	 *            the mav
	 * @param viewName
	 *            the view name
	 * @return the model and view
	 */
	@RequestMapping(params="method=bindError")
	protected ModelAndView bindError(final HttpServletRequest request,
			final BindingResult result, final ModelAndView mav,
			final String viewName) {
		mav.setViewName(viewName);
		mav.addAllObjects(result.getModel());
		mav.addAllObjects(referenceData(request));
		return mav;
	}

	/**
	 * 为AuditableEntity接口重载了
	 * {@link BaseController#bindObject(javax.servlet.http.HttpServletRequest, Object)}
	 * 的bindObject函数. 如果对象实现了该接口: 自动对createUser/modifyUser取session中的"user"属性.
	 * 自动对createTime/modifyTime取当前时间.
	 * 
	 * @param request
	 *            the request
	 * @param command
	 *            the command
	 * @return the binding result
	 * @throws ControllerException
	 *             the ControllerException
	 */
	@Override
	protected BindingResult bindObject(final HttpServletRequest request,
			final Object command) throws ControllerException {
		BindingResult result = null;
		try {
			result = super.bindObject(request, command);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (command instanceof AuditableEntity) {
			final AuditableEntity ve = (AuditableEntity) command;
			final TaclUserinfo user = (TaclUserinfo) request.getSession()
					.getAttribute(Consts.USER_INFO);// 修改从会话中获取用户信息add by zhujw
			if (ve.getCreateUser() == null)
				ve.setCreateUser(user);
			else
				ve.setModifyUser(user);

			if (ve.getCreateTime() == null)
				ve.setCreateTime(new Date());
			else
				ve.setModifyTime(new Date());
		}
		return result;
	}

	/**
	 * 将上传文件写到服务器硬盘. 一些不支持BLOB类型的数据库,必须把上传的文件写到硬盘.
	 * 本函数写得并不严谨,如文件同名判断,加入时间戳作为文件名一部分等未做,just for demo
	 * 
	 * @param request
	 *            the request
	 * @param uploadDir
	 *            the upload dir
	 * @param parameterName
	 *            the parameter name
	 * @return 文件的相对路径
	 * @throws IOControllerException
	 *             Signals that an I/O ControllerException has occurred.
	 */
	protected String uploadImageToFile(final HttpServletRequest request,
			final String uploadDir, final String parameterName)
			throws IOException {

		String fileRelativePath = null;
		MultipartHttpServletRequest multipartRequest = null;

		try {
			multipartRequest = (MultipartHttpServletRequest) request;
		} catch (final ClassCastException e) {
			return null; // only for testcast 中,mockservlet不能转为MultipartRequest
		}

		final MultipartFile multipartFile = multipartRequest
				.getFile(parameterName);
		if (StringUtils.isNotEmpty(multipartFile.getOriginalFilename())) {
			final String fileName = multipartFile.getOriginalFilename();
			final String fileRealPath = getServletContext().getRealPath(
					uploadDir)
					+ File.separator + fileName;
			final File file = new File(fileRealPath);
			multipartFile.transferTo(file);
			fileRelativePath = uploadDir + "/" + fileName;
		}

		return fileRelativePath;
	}

	/**
	 * 获得机构树
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ControllerException
	 * @author Xiaojf
	 * @since 2011-5-30
	 */
	@RequestMapping(params="method=getOrgTree")
	public ModelAndView getOrgTree(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		String orgId = getParam(request, "orgId");
		String userId = getParam(request, "userId");
		String orgClass = getParam(request, "orgClass");
		if (StringHelpers.isNull(orgId)) {
			orgId = getOrgId(request);
		}
		List<Map<String, Object>> tree = baseService.getOrgTree(userId, orgId, orgClass);
		renderJson(response, JsonUtil.toString(tree));
		return null;
	}
	@RequestMapping(params="method=getAssignedOrgTree")
	public void getAssignedOrgTree(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		String userId = getParam(request, "userId");
		String orgClass = getParam(request, "orgClass");
		if (StringHelpers.isNull(userId))
			userId = getUserId(request);
		List<Map<String, Object>> tree = null;
		String full = getParam(request, "full");
		if ("true".equals(full)) {
			tree = baseService.getOrgTree(null, null, null);
		} else {
			tree = baseService.getAssignedOrgTree(userId, orgClass);
		}
		renderJson(response, JsonUtil.toString(tree));
	}

	/**
	 * 获得模块树
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ControllerException
	 * @author Xiaojf
	 * @since 2011-5-30
	 */
	@RequestMapping(params="method=getModuleTree")
	public ModelAndView getModuleTree(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		String roleId = getParam(request, "roleId");
		String flatId = getParam(request, "flatId");
		String listoper = getParam(request, "listoper");
		boolean oper = StringHelpers.notNull(listoper);
		try{
			List<Map<String, Object>> tree = null;
			if (StringHelpers.notNull(roleId)) {
				String[] ids = roleId.split(",");
				tree = baseService.getModuleTree(flatId, oper, ids);
			} else tree = baseService.getModuleTree(flatId, oper);
			String str = JsonUtil.toString(tree);
			renderJson(response, str);
		}catch(ControllerException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取分级角色机构下拉框
	 * 
	 * @param request
	 * @param response
	 * @throws ControllerException
	 * @author xiaoB
	 * @since 2011-6-30
	 * */
	@RequestMapping(params="method=getRoleType")
	public List<TsysParameter> getRoleType(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		TsysOrg org = (TsysOrg) request.getSession().getAttribute(
				Consts.USER_DEFAULT_ORG);
		int orgtype = 0;
		String orgType = org.getOrgClass();
		if (StringHelpers.isNull(orgType)) {
			orgtype = 9999;
		} else {
			orgtype = Integer.parseInt(orgType);
		}
		List<TsysParameter> roleType = ParamUtil.getInstance().getByType("roleType");
		List<TsysParameter> selectRole = new ArrayList<TsysParameter>();

		for (TsysParameter tsys : roleType) {
			if (orgtype == Integer.parseInt(tsys.getParano())) {
				selectRole.add(tsys);
			}
		}
		return selectRole;
	}
	
	/**
	 * 获得当前角色级别的下级级别
	 * 
	 * @param request
	 * @param response
	 * @throws ControllerException
	 * @author xiaoB
	 * @since 2011-6-30
	 * */
	@RequestMapping(params="method=getRoleTypeLower")
	public String getRoleTypeLower(HttpServletRequest request,
			HttpServletResponse response){
		TsysOrg org = (TsysOrg) request.getSession().getAttribute(
				Consts.USER_DEFAULT_ORG);
		String orgType = org.getOrgClass();
		int orgtype = Integer.parseInt(orgType);
		List<TsysParameter> roleType = ParamUtil.getInstance().getByType("roleType");
		StringBuffer buffer = new StringBuffer();
		for(int i = orgtype;i <= roleType.size(); i++){
				buffer.append("'"+i+"'");
				buffer.append(",");
		}
		if(buffer.length()>0){
			buffer.deleteCharAt(buffer.length()-1);
		}
		return buffer.toString();
	}
	
	/**
	 * 判断用户是否集团公司
	 * @param request
	 * @param response
	 * @throws ControllerException
	 * @author xiaoB
	 * @since 2011-7-2
	 * */
	
	public boolean isGroup(HttpServletRequest request,
			HttpServletResponse response){
		TsysOrg org = (TsysOrg) request.getSession().getAttribute(Consts.USER_DEFAULT_ORG);
		String orgParent = org.getParentorgid();
		if(orgParent!=null){
			return false;
		}
		return true;
	}
	
	/**
	 * 获得所有岗位
	 * @param request
	 * @return list
	 * @author Xiaob
	 * @since 2011-8-15
	 * */
	public Map<String , TsysDuty> getAllDuty(){
		TsysDutyService dutysvc = (TsysDutyService) getApplicationContext().getBean("tsysDutyService");
		List<TsysDuty> alldety = dutysvc.getAllTsysDuty();
		Map<String , TsysDuty> detys = new HashMap<String, TsysDuty>();
		for (TsysDuty o : alldety) {
			TsysDuty duty = o;
			detys.put(duty.getDutyid(), duty);
		}
		return detys;
	}
	
	/**
	 * 取得所有的机构
	 * @param request
	 * @return
	 * @author Xiaojf
	 * @since 2011-8-15
	 */
	@SuppressWarnings("unchecked")
	public Map<String, TsysOrg> getAllOrgs() {
		TsysOrgService orgsvc = (TsysOrgService) getApplicationContext().getBean("tsysOrgService");
		List<TsysOrg> allorgs = orgsvc.getAllTsysOrg();
		Map<String, TsysOrg> orgs = new HashMap<String, TsysOrg>();
		for (Object o : allorgs) {
			TsysOrg org = (TsysOrg) o;
			orgs.put(org.getOrgid(), org);
		}
		return orgs;
	}
	/**
	 * 取得所有的用户
	 * @param request
	 * @return
	 * @author Xiaojf
	 * @since 2011-8-15
	 */
	@SuppressWarnings("unchecked")
	public Map<String, TaclUserinfo> getAllUsers() {
		TaclUserinfoService usersvc = (TaclUserinfoService) getApplicationContext().getBean("taclUserinfoService");
		List<TaclUserinfo> allusers = usersvc.getAllTaclUserinfo();
		Map<String, TaclUserinfo> users = new HashMap<String, TaclUserinfo>();
		for (Object o : allusers) {
			TaclUserinfo user = (TaclUserinfo) o;
			users.put(user.getUserid(), user);
		}
		return users;
	}
	/**
	 * 转换机构ID为机构名称
	 * @param request
	 * @param datas
	 * @param fieldName
	 * @author Xiaojf
	 * @since 2011-8-3
	 */
	public void convertOrgId(List<?> datas, String... fieldName) {
		convertList(datas, getAllOrgs(), "orgname", fieldName);
	}
	/**
	 * 转换某一个对象的机构号为机构名称
	 * @param request
	 * @param bean
	 * @param fieldName
	 * @author Xiaojf
	 * @since 2011-9-6
	 */
	public <T> void convertOrgId(T bean, String... fieldName) {
		List<T> list = new ArrayList<T>();
		list.add(bean);
		convertList(list, getAllOrgs(), "orgname", fieldName);
	}
	/**
	 * 转换用户ID为用户名称
	 * @param request
	 * @param datas
	 * @param fieldName
	 * @author Xiaojf
	 * @since 2011-8-3
	 */
	public void convertUserId(List<?> datas, String... fieldName) {
		convertList(datas, getAllUsers(), "username", fieldName);
	}
	/**
	 * 转换岗位ID为岗位Name
	 * @param request
	 * @param datas
	 * @param fiedName
	 * @author Xiaob
	 * @since 2011-11-29
	 * */
	public void convertDutyId(List<?> list , String... fieldName){
		convertList(list ,getAllDuty() , "dutyname" , fieldName);
	}
	/**
	 * 转换单个对象的指定属性
	 * @param request
	 * @param bean
	 * @param source
	 * @param fieldName
	 * @return
	 * @author Xiaojf
	 * @since 2011-8-15
	 */
	public <T> T convertBean(T bean, Map<String, ?> source, String sourceField, String... fieldName) {
		List<T> list = new ArrayList<T>();
		list.add(bean);
		convertList(list, source, sourceField, fieldName);
		return bean;
	}
	/**
	 * 转换多个对象的指定属性
	 * @param request
	 * @param datas
	 * @param source
	 * @param fieldName
	 * @author Xiaojf
	 * @since 2011-8-15
	 */
	public <T> void convertList(List<?> datas, Map<String, T> source, String sourceField, String... fieldNames) {
		if (source == null || fieldNames == null || fieldNames.length == 0) return;
		for (String fieldName : fieldNames) {
			String mname = StringHelpers.fistCapital(fieldName);
			Method setter = null;
			Method getter = null;
			for (Object o : datas) {
				try {
					if (setter == null) {
						try {
							setter = o.getClass().getDeclaredMethod("set" + mname, String.class);
						} catch (SecurityException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (NoSuchMethodException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					if (getter == null) {
						try {
							getter = o.getClass().getDeclaredMethod("get" + mname);
						} catch (SecurityException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (NoSuchMethodException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					if (setter != null && getter != null) {
						T t = null;
						try {
							t = (T) source.get(getter.invoke(o));
						} catch (IllegalArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						String m = StringHelpers.fistCapital(sourceField);
						if (t != null) {
							Method gt = null;
							try {
								gt = t.getClass().getDeclaredMethod("get" + m);
							} catch (SecurityException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (NoSuchMethodException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							if (gt != null)
								try {
									setter.invoke(o, gt.invoke(t));
								} catch (IllegalArgumentException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (IllegalAccessException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (InvocationTargetException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
						}
					}
				} catch (ControllerException e) {
				}
			}
		}
	}
	
	/**
	 * 获取所有用户，根据数据权限过滤
	 * @param request
	 * @param response
	 * @author xjf721
	 * @since 2011-11-8
	 */
	@RequestMapping(params="method=getUserToJson")
	public void getUserToJson(HttpServletRequest request, HttpServletResponse response) {
		String orgId = getParam(request, "orgId");
		String hql = "from TaclUserinfo where 1=1 ";
		String full = getParam(request, "full");
		if (StringHelpers.isNull(orgId)) {
			orgId = baseService.getPrivilege(getUserId(request));
		} else if (!"true".equals(full)) {
			orgId = baseService.getPrivilege(getUserId(request), orgId);
		} else {
			orgId = StringHelpers.join(orgId.split(","));
		}
		hql +=  " and orgid in (" + orgId + ")";
		Map<String, Object> map = WebUtils.getParametersStartingWith(request, "filter_");
		for (Iterator<String> it = map.keySet().iterator();it.hasNext();) {
			String key = it.next();
			String val = (String) map.get(key);
			if (StringHelpers.notNull(val)) {
				hql += " and " + key + " like '%" + val + "%'";
			}
		}
		String state = getParam(request, "userstate");
		if (StringHelpers.isNull(state)) {
			hql += " and userstate='1'";
		} else {
			hql += " and userstate in ("+StringHelpers.join(state.split(","))+")";
		}
		String exclude = getParam(request, "excludes");
		String include = getParam(request, "includes");
		if (StringHelpers.notNull(exclude)) {
			String[] excludes = exclude.split(",");
			if (excludes.length > 0) {
				hql += " and userid not in (" + StringHelpers.join(excludes)+")";
			}
		}
		if (StringHelpers.notNull(include)) {
			String[] includes = include.split(",");
			if (includes.length > 0) {
				hql += " and userid in (" + StringHelpers.join(includes)+")";
			}
		}
		List<TaclUserinfo> allusers = calcPage(request, baseService, hql);
		json.put("total", request.getAttribute("total"));
		json.put("rows", allusers);
		renderJson(response, getJsonAndReset("tsysDuty", "tsysOrg"));
	}
	
	/**
	 * 获取所有用户，不根据数据权限过滤
	 * @param request
	 * @param response
	 * @author xiaob
	 * @since 2012-05-11
	 */
	@RequestMapping(params="method=getAllUserToJson")
	public void getAllUserToJson(HttpServletRequest request, HttpServletResponse response) {
		String hql = "from TaclUserinfo where 1=1 ";
		String full = getParam(request, "full");
		Map<String, Object> map = WebUtils.getParametersStartingWith(request, "filter_");
		for (Iterator<String> it = map.keySet().iterator();it.hasNext();) {
			String key = it.next();
			String val = (String) map.get(key);
			if (StringHelpers.notNull(val)) {
				hql += " and " + key + " like '%" + val + "%'";
			}
		}
		String state = getParam(request, "userstate");
		if (StringHelpers.isNull(state)) {
			hql += " and userstate='1'";
		} else {
			hql += " and userstate in ("+StringHelpers.join(state.split(","))+")";
		}
		String exclude = getParam(request, "excludes");
		String include = getParam(request, "includes");
		if (StringHelpers.notNull(exclude)) {
			String[] excludes = exclude.split(",");
			if (excludes.length > 0) {
				hql += " and userid not in (" + StringHelpers.join(excludes)+")";
			}
		}
		if (StringHelpers.notNull(include)) {
			String[] includes = include.split(",");
			if (includes.length > 0) {
				hql += " and userid in (" + StringHelpers.join(includes)+")";
			}
		}
		List<TaclUserinfo> allusers = calcPage(request, baseService, hql);
		json.put("total", request.getAttribute("total"));
		json.put("rows", allusers);
		renderJson(response, getJsonAndReset("tsysDuty", "tsysOrg"));
	}
	@RequestMapping(params="method=changePwd")
	public ModelAndView changePwd(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		String id = getUserId(request);
		TaclUserinfo user = baseService.getBean(TaclUserinfo.class, id);
		if (user == null) {
			putToJson("success", false);
			putToJson("message", "用户不存在!");
			renderJson(response, getJsonAndReset());
			return null;
		}
		String oldpwd = request.getParameter("oldpassword");
		String encrypt = Eryptogram.getUserPasswd(oldpwd);
		String newpassword=request.getParameter("newpassword");
		
		String dbpwd = user.getUserpw();
		if (!dbpwd.equals(encrypt)) {
			putToJson("success", false);
			putToJson("message", "旧密码不正确!");
			renderJson(response, getJsonAndReset());
			return null;
		}
		user.setUserpw(Eryptogram.getUserPasswd(newpassword));
		baseService.saveOrUpdateVO(user);
		putToJson("success", true);
		renderJson(response, getJsonAndReset());
		return null;
	}
	@RequestMapping(params="method=getHelpMenu")
	public void getHelpMenu(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		String hql = "from TsysUpload";
		List<?> list = baseService.getValueObjectsByHQL(hql);
		renderJson(response, JsonUtil.toString(list));
	}

	public void downloadFile(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		String fileId = getParam(request, "fileId");
		if (StringHelpers.isNull(fileId)) return;
		try {
        	TsysUpload upload = baseService.getBean(TsysUpload.class, fileId);
        	
        	String failPage = "<html><font size='3'>系统找不到指定的文件.<a href='#' onclick='javascript:window.close()'>点击这里返回</a></font></html>";
        	if (upload == null) {
				renderHtml(response, failPage);
        		return;
        	}
        	String path = upload.getFileUrl();
            // path是指欲下载的文件的路径。
            File file = new File(path);
            if (!file.exists()) {
            	renderHtml(response, failPage);
        		return;
            }
            // 取得文件名。
            String filename = upload.getSecondName();
            // 取得文件的后缀名。
            String name = upload.getFileName();
			String ext = name.substring(name.lastIndexOf("."));

            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes("GBK"), "ISO-8859-1") + ext);
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            log.error(ex);
        }
	}
	@RequestMapping(params="method=getDutyTree")
	public void getDutyTree(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		String dutyId = getParam(request, "dutyId");
		List<Map<String,Object>> tree = baseService.getDutyTree(dutyId);
		renderJson(response, JsonUtil.toString(tree));
	}
}
