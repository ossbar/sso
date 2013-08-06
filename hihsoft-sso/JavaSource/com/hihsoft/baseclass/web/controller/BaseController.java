/**
 * Copyright (c) 2013-2015 www.javahih.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.hihsoft.baseclass.web.controller;

import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.util.WebUtils;

import com.hihframework.core.utils.DateUtils;
import com.hihframework.core.utils.ParamUtil;
import com.hihframework.core.utils.StringHelpers;
import com.hihframework.osplugins.json.JsonUtil;
import com.hihsoft.baseclass.constants.Consts;
import com.hihsoft.baseclass.service.BaseService;
import com.hihsoft.sso.business.model.TaclUserinfo;
import com.hihsoft.sso.sysmonitor.sysaudit.model.AuditableEntity;

/**
 * Title:所有页面控制类的 基类，可以简化时则继承它
 * Description:封装所有的日志、用户的审计信息、会话操作、对象转换
 * Copyright: Copyright (c) 2013
 * Company:hihsoft.co.,ltd
 * @author hihsoft.co.,ltd
 * @version 1.0
 */
public abstract class BaseController extends MultiActionController {

	/** The Constant log. */
	protected final Logger log = Logger.getLogger(this.getClass());
	
	protected final Map<String, Object> json = new HashMap<String, Object>();

	/**
	 * 把信息放入session.
	 * 
	 * @param request
	 *            the request
	 * @param attributeKey
	 *            the attribute key
	 * @param obj
	 *            the obj
	 */
	public void putSession(final HttpServletRequest request,
			final String attributeKey, final Object obj) {
		request.getSession().setAttribute(attributeKey, obj);
	}

	/**
	 * 从session中取得信息.
	 * 
	 * @param request
	 *            the request
	 * @param attributeKey
	 *            the attribute key
	 * @return the session
	 */
	public Object getSession(final HttpServletRequest request,
			final String attributeKey) {
		return request.getSession().getAttribute(attributeKey);
	}

	/**
	 * 从request或session中移除对象
	 * 
	 * @param request
	 * @param key
	 * @author Xiaojf
	 * @since 2011-6-29
	 */
	public void remove(HttpServletRequest request, String key) {
		request.removeAttribute(key);
		request.getSession().removeAttribute(key);
	}
	/**
	 * 清空Session
	 * @param request
	 * @author Xiaojf
	 * @since 2011-8-31
	 */
	@SuppressWarnings("unchecked")
	public void clearSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Enumeration<String> attributeNames = session.getAttributeNames();
		while (attributeNames.hasMoreElements()) {
			session.removeAttribute(attributeNames.nextElement());
		}
	}
	/**
	 * 增加validator. 除了Spring配置文件注入的validators数组外,可以用此函数在子类的代码里再添加新的validator.
	 * 
	 * @param validator
	 *            the validator
	 */
	protected void addValidator(final Validator validator) {
		ArrayUtils.add(getValidators(), validator);
	}

	/**
	 * 保存消息在request中,messages.jsp会取出来显示此消息
	 */
	protected static void saveMessage(HttpServletRequest request, String message) {
		if (StringUtils.isNotBlank(message)) {
			@SuppressWarnings("unchecked")
			List<String> list = getOrCreateRequestAttribute(request, "springMessages",
					ArrayList.class);
			list.add(message);
		}
	}

	/**
	 * 保存错误消息在request中,messages.jsp会取出来显示此消息
	 */
	protected static void saveError(HttpServletRequest request, String errorMsg) {
		if (StringUtils.isNotBlank(errorMsg)) {
			@SuppressWarnings("unchecked")
			List<String> list = getOrCreateRequestAttribute(request, "springErrors",
					ArrayList.class);
			list.add(errorMsg);
		}
	}

	protected void saveParameter(final HttpServletRequest request,
			final String name, final Object value) {
		if (StringUtils.isNotBlank(name)) {
			@SuppressWarnings("unchecked")
			final Map<String, Object> messages = (Map<String, Object>) WebUtils.getOrCreateSessionAttribute(
					request.getSession(), "parameters", HashMap.class);
			messages.put(name, value);
		}
	}

	/**
	 * 数据交换：待验证此方法的正确性(xiaojf发现说有问题)
	 */
	@Override
	protected void bind(final HttpServletRequest request, final Object command) {
		final PropertyDescriptor[] pds = BeanUtils
				.getPropertyDescriptors(command.getClass());
		for (final PropertyDescriptor pd : pds) {
			final Class<?> clas = pd.getPropertyType();// 获取属性的class类
			final boolean isSimpleProperty = BeanUtils.isSimpleProperty(clas);
			final boolean isInterface = clas.isInterface();
			final boolean hasConstruct = clas.getConstructors().length == 0 ? false
					: true;
			if (!isSimpleProperty && !isInterface && hasConstruct) {
				// 获得应该用于写入属性值的方法
				try {
					pd.getWriteMethod().invoke(command, clas.newInstance());
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		try {
			super.bind(request, command);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 初始化binder的回调函数. 默认以DateUtil中的日期格式设置DateEditor及允许Integer,Double的字符串为空.
	 * 
	 * @param request
	 *            the request
	 * @param binder
	 *            the binder
	 * @see MultiActionController#createBinder(HttpServletRequest,Object)
	 */
	@Override
	protected void initBinder(final HttpServletRequest request,
			final ServletRequestDataBinder binder) {
		final SimpleDateFormat dateFormat = new SimpleDateFormat(
				DateUtils.getDatePattern());
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
		binder.registerCustomEditor(Integer.class, new CustomNumberEditor(
				Integer.class, true));
		binder.registerCustomEditor(Double.class, new CustomNumberEditor(
				Double.class, true));
		binder.registerCustomEditor(Long.class, new CustomNumberEditor(
				Long.class, true));
	}

	/**
	 * 从Request中绑定对象并进行校验. Spring MVC中的Bind函数未完全符合需求,因此参考原代码进行了扩展
	 * 
	 * @return 校验错误
	 * @see #preBind(HttpServletRequest,Object,ServletRequestDataBinder)
	 */
	protected BindingResult bindObject(final HttpServletRequest request,
			final Object command) throws Exception {
		Assert.notNull(command);

		// 创建Binder
		final ServletRequestDataBinder binder = createBinder(request, command);
		// 回调函数，供子类扩展对binder做出更进一步设置,并进行不能由binder自动完成的绑定
		preBind(request, command, binder);

		// 绑定
		binder.bind(request);

		// 校验
		final Validator[] validators = getValidators();
		if (validators != null) {
			for (final Validator validator : validators) {
				if (validator.supports(command.getClass())) {
					ValidationUtils.invokeValidator(validator, command,
							binder.getBindingResult());
				}
			}
		}
		return binder.getBindingResult();
	}

	/**
	 * 回调函数，在BindObject的最开始调用。 负责 1.继续对binder进行设置 2.绑定一些不能由Binder自动绑定的属性.
	 * 这些属性通常是需要查询数据库来获得对象的绑定. 要注意设置这些属性为disallow. eg.
	 * 
	 */
	protected void preBind(final HttpServletRequest request,
			final Object object, final ServletRequestDataBinder binder)
			throws Exception {
		// 在子类实现
	}

	/**
	 * 回调函数，声明CommandName--对象的名字,默认为首字母小写的类名.
	 * 
	 * @see #bindObject(HttpServletRequest, Object)
	 */
	@Override
	protected String getCommandName(final Object command) {
		return StringUtils.uncapitalize(command.getClass().getSimpleName());
	}

	/**
	 * 不通过view渲染直接输出纯字符串
	 */
	public void renderText(final HttpServletResponse response,
			final String content) {
		try {
			response.setContentType("text/plain;charset=UTF-8");
			response.getWriter().write(content);
			response.getWriter().flush();
		} catch (final IOException e) {
			logger.error(e);
		}
	}

	/**
	 * 不通过view渲染直接输出纯HTML
	 */
	public void renderHtml(final HttpServletResponse response,
			final String content) {
		try {
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write(content);
		} catch (final IOException e) {
			logger.error(e);
		}
	}

	/**
	 * 不通过view渲染直接输出纯XML
	 */
	public void renderXML(final HttpServletResponse response,
			final String content) {
		try {
			response.setContentType("text/xml;charset=UTF-8");
			response.getWriter().write(content);
		} catch (final IOException e) {
			logger.error(e);
		}
	}

	/**
	 * 不通过view渲染直接输出json
	 */
	public void renderJson(final HttpServletResponse response,
			final String content) {
		try {
			response.setContentType("text/json;charset=UTF-8");
			response.getWriter().write(content);
		} catch (final IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 输出简单的JSON结果
	 * 
	 * @param key
	 * @param value
	 * @return
	 * @author Xiaojf
	 * @since 2011-5-20
	 */
	public String toJson(String key, Object value) {
		JSONObject json = new JSONObject();
		json.put(key, value);
		return JsonUtil.toString(json);
	}
	
	public String toJsArray(String... vals) {
		JSONArray arr = JSONArray.fromObject(vals);
		return JsonUtil.toString(arr);
	}

	/**
	 * Convenience method for getting a i18n key's value. Calling
	 * getMessageSourceAccessor() is used because the RequestContext variable is
	 * not set in unit tests b/c there's no DispatchServlet Request.
	 * 
	 * @param msgKey
	 * @return
	 */
	public String getText(final String msgKey) {
		return getMessageSourceAccessor().getMessage(msgKey);
	}

	/**
	 * Convenient method for getting a i18n key's value with a single string
	 * argument.
	 * 
	 * @param msgKey
	 * @param arg
	 * @return
	 */
	public String getText(final String msgKey, final String arg) {
		return getText(msgKey, new Object[] { arg });
	}

	/**
	 * Convenience method for getting a i18n key's value with arguments.
	 * 
	 * @param msgKey
	 * @param args
	 * @return
	 */
	public String getText(final String msgKey, final Object[] args) {
		return getMessageSourceAccessor().getMessage(msgKey, args);
	}

	/**
	 * 将request中的参数设置到javabean中
	 * 
	 * @param request
	 * @param obj
	 * @author Xiaojf
	 * @since 2011-5-16
	 */
	protected <T> void setValue(HttpServletRequest request, T obj) {
		if (obj instanceof AuditableEntity) {
			AuditableEntity ve = (AuditableEntity) obj;
			TaclUserinfo user = (TaclUserinfo) request.getSession()
					.getAttribute(Consts.USER_INFO);
			if (ve.getCreateUser() == null)
				ve.setCreateUser(user);
			else
				ve.setModifyUser(user);

			if (ve.getCreateTime() == null)
				ve.setCreateTime(new Date());
			else
				ve.setModifyTime(new Date());
		}
		if (obj == null)
			return;
		Method[] methods = obj.getClass().getDeclaredMethods();
		for (Method m : methods) {
			String name = m.getName();
			if (!name.startsWith("set"))
				continue;
			name = name.substring(3).toLowerCase();
			Object value = getParam(request, name);
			if (value != null) {
				try {
					if (value.equals("")) {
						value = null;
					} else {
						Class<?> type = m.getParameterTypes()[0];
						if (Number.class.isAssignableFrom(type)) {
							Constructor<?> con = type.getConstructor(String.class);
							if (con != null)
								value = con.newInstance(value);
						} else if (Date.class.isAssignableFrom(type)) {
							String v = (String) value;
							if (v.indexOf(":") != -1) {
								value = DateUtils.parse(v,
										DateUtils.FM_DATE_AND_TIME);
							} else
								value = DateUtils.parse(value.toString());
						}
					}
					m.invoke(obj, value);
				} catch (Exception e) {
					logger.error(e);
				}
			}
		}
	}

	/**
	 * 对象间的转换
	 * 
	 * @param target
	 * @param source
	 */
	public static void copyProperties(Object target, Object source) {
		BeanUtils.copyProperties(target, source);
	}

	/**
	 * 在request中查找参数值，解决参数名大小写不一致的问题。
	 * 
	 * @param request
	 * @param name
	 * @return
	 * @author Xiaojf
	 * @since 2011-5-17
	 */
	@SuppressWarnings("unchecked")
	protected String getParam(HttpServletRequest request, String name) {
		String val = request.getParameter(name);
		if (val != null)
			return val;
		Enumeration<String> names = request.getParameterNames();
		while (names.hasMoreElements()) {
			String n = names.nextElement();
			if (n.equalsIgnoreCase(name)) {
				return request.getParameter(n);
			}
		}
		return null;
	}

	public static <T> T getOrCreateRequestAttribute(HttpServletRequest request,
			String key, Class<T> clazz) {
		Object value = request.getAttribute(key);
		if (value == null) {
			try {
				value = clazz.newInstance();
			} catch (Exception e) {
				ReflectionUtils.handleReflectionException(e);
			}
			request.setAttribute(key, value);
		}
		return clazz.cast(value);
	}

	/**
	 * 将集合中的编码字段转换为显示值
	 * 
	 * @param <T>
	 * @param datas
	 *            要转换的数据列表
	 * @param params
	 *            其实key为列表中的字段,必须与javabean中字段名一致.value为参数表中的参数类型
	 * @author Xiaojf
	 * @since 2011-6-8
	 */
	protected <T> void convertParam(List<T> datas, Map<String, String> params) {
		if (datas == null || params == null || params.isEmpty()	|| datas.isEmpty())
			return;
		Set<String> keys = params.keySet();
		try {
			for (String field : keys) {
				Method setter = null;
				Method getter = null;
				String type = params.get(field);
				field = field.substring(0, 1).toUpperCase()
						+ field.substring(1);
				for (T t : datas) {
					if (getter == null) {
						getter = t.getClass().getDeclaredMethod("get" + field);
					}
					if (setter == null) {
						setter = t.getClass().getDeclaredMethod("set" + field,
								getter.getReturnType());
					}
					if (getter == null || setter == null)
						break;
					String val = (String) getter.invoke(t);
					String key = ParamUtil.getInstance().getKeyByVal(type, val);
					setter.invoke(t, key);
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}

	}

	/**
	 * 将集合中的编码字段转换为显示值
	 * 
	 * @param <T>
	 * @param datas
	 *            要转换的数据列表
	 * @param field
	 *            对象中要转换的字段名，必须与javabean中字段名一致
	 * @param paramType
	 *            参数类型，对应数据库中的PARA_TYPE字段
	 * @author Xiaojf
	 * @since 2011-5-28
	 */
	protected <T> void convertParam(List<T> datas, String field,
			String paramType) {
		Map<String, String> params = new HashMap<String, String>();
		params.put(field, paramType);
		convertParam(datas, params);
	}

	/**
	 * 将单个对象中的编码字段转换为显示值
	 * 
	 * @param <T>
	 * @param t
	 *            要转换的数据对象
	 * @param field
	 *            对象中要转换的字段名，必须与javabean中字段名一致
	 * @param paramType
	 *            参数类型，对应数据库中的PARA_TYPE字段
	 * @author Xiaojf
	 * @since 2011-6-8
	 */
	protected <T> void convertParam(T t, String field, String paramType) {
		Map<String, String> params = new HashMap<String, String>();
		params.put(field, paramType);
		List<T> datas = new ArrayList<T>();
		datas.add(t);
		convertParam(datas, params);
	}

	protected <T> void convertParam(T t, Map<String, String> map) {
		List<T> datas = new ArrayList<T>();
		datas.add(t);
		convertParam(datas, map);
	}

	/**
	 * 得到分页数据
	 * 
	 * @param <T>
	 * @param request
	 * @param service
	 * @param hql
	 * @return
	 * @author Xiaojf
	 * @since 2011-5-31
	 */
	protected <T> List<T> calcPage(HttpServletRequest request,
			BaseService service, String hql, Object... params) {
		String pageSize = getParam(request, "rows");
		if (StringHelpers.isNull(pageSize)) pageSize = (String) request.getAttribute("rows");
		String pageNo = getParam(request, "page");
		if (StringHelpers.isNull(pageSize))
			pageSize = getDefaultPageSize();
		if (StringHelpers.isNull(pageNo))
			pageNo = "1";
		int total = service.getDataTotalNum(hql, params);
		int rows = Integer.parseInt(pageSize);
		int pages = total % rows == 0 ? (total / rows) : (total / rows + 1);
		int page = Integer.parseInt(pageNo);
		if (page > pages) page = 1;
		request.setAttribute("rows", rows);
		request.setAttribute("total", total);
		request.setAttribute("page", page);
		request.setAttribute("pages", pages);
		@SuppressWarnings("unchecked")
		List<T> list = (List<T>) service.getPageDataByHQL(hql,Integer.parseInt(pageSize), Integer.parseInt(pageNo), params);
		return list;
	}
	
	/**
	 * 以SQL查询，结果是MAP类型的分页
	 * @param request
	 * @param svc
	 * @param sql
	 * @param args
	 * @return
	 * @author xjf721
	 * @since 2012-2-20
	 */
	public List<Map<String, Object>> calcSqlPage(HttpServletRequest request, BaseService svc, String sql, Object... args) {
		String pageSize = getParam(request, "rows");
		if (StringHelpers.isNull(pageSize)) pageSize = (String) request.getAttribute("rows");
		String pageNo = getParam(request, "page");
		if (StringHelpers.isNull(pageSize))
			pageSize = getDefaultPageSize();
		if (StringHelpers.isNull(pageNo))
			pageNo = "1";
		
		int total = (int) svc.getTotalNumBySQL(sql, args);
		int rows = Integer.parseInt(pageSize);
		int pages = total % rows == 0 ? (total / rows) : (total / rows + 1);
		int page = Integer.parseInt(pageNo);
		if (page > pages) page = 1;
		List<Map<String, Object>> list = svc.queryForPagedListBySQL(sql, page, rows, args);
		request.setAttribute("rows", rows);
		request.setAttribute("total", total);
		request.setAttribute("page", page);
		request.setAttribute("pages", pages);
		return list;
	}
	
	protected String calcJsonPage(HttpServletRequest request,
			BaseService service, String hql, String[] excludes, Object[] params) {
		List<Object> list = calcPage(request, service, hql, params);
		putToJson("rows", list);
		putToJson("total", request.getAttribute("total"));
		return JsonUtil.toString(getJsonAndReset(excludes));
	}
	
	protected String calcJsonPage(HttpServletRequest request,
			BaseService service, String hql, Object... params) {
		return calcJsonPage(request, service, hql, null, params);
	}
	
	protected String getDefaultPageSize() {
		return Consts.SYS_DEFAULT_PAGE_SIZE;
	}

	protected TaclUserinfo getUser(HttpServletRequest request) {
		TaclUserinfo user = (TaclUserinfo) request.getSession().getAttribute(
				Consts.USER_INFO);
		return user;
	}

	protected String getUserId(HttpServletRequest request) {
		return (String) getSession(request, Consts.USER_ID);
	}

	protected String getOrgId(HttpServletRequest request) {
		return (String) getSession(request, Consts.CUR_ORGID);
	}

	protected String getDeptId(HttpServletRequest request) {
		return (String) getSession(request, Consts.CUR_DEPTID);
	}
	
	protected void putToJson(String name, Object value) {
		json.put(name, value);
	}
	
	protected String getJsonAndReset(JsonConfig jsonConfig) {
		String str = JsonUtil.toString(json, jsonConfig);
		json.clear();
		return str;     
	}
	
	protected String getJsonAndReset(String... excludePropertis) {
		String str = JsonUtil.toString(json, excludePropertis);
		json.clear();
		return str;
	}
	
	protected String getJsonAndReset() {
		String str = JsonUtil.toString(json);
		json.clear();
		return str;
	}
	/**
	 * 添加排序
	 * @param response
	 * @param value
	 * @author xjf721
	 * @since 2012-3-26
	 */
	protected void addOrders(HttpServletRequest request, String value) {
		request.setAttribute("orders", value);
	}
}
