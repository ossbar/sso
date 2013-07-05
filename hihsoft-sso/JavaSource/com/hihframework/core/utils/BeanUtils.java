/**
 * Copyright (c) 2013-2015 www.javahih.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.hihframework.core.utils;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.hibernate.mapping.Collection;

import com.hihframework.osplugins.json.JsonUtil;
import com.hihsoft.sso.business.model.TsysParameter;
/**
 * <p> Title:JAVABEAN实用类 </p>
 * <p> Description:</p>
 * <p> Copyright: Copyright (c) 2013 </p>
 * <p> Company:hihsoft.co.,ltd </p>
 *
 * @author hihsoft.co.,ltd
 * @version 1.0
 */
public class BeanUtils {
	static Logger log = Logger.getLogger(BeanUtils.class);
	/**
	 * 按字段名从对象中取值。不区分大小写
	 * @param bean
	 * @param name
	 * @return
	 * @author 
	 * @since 
	 */
	public static String getProperty(Object bean, String name) {
		try {
			Method m = bean.getClass().getMethod(
					"get" + StringHelpers.upperFirst(name));
			if (m != null && Modifier.isPublic(m.getModifiers())) {
				Object val = m.invoke(bean);
				return val==null?null:val.toString();
			}
		} catch (Exception e) {}
		try {
			Method[] methods = bean.getClass().getDeclaredMethods();
			for (Method method : methods) {
				String n = method.getName();
				if (!n.startsWith("get"))
					continue;
				n = n.substring(3);
				if (!name.equalsIgnoreCase(n))
					continue;
				if (!Modifier.isPublic(method.getModifiers()))
					continue;
				Object val = method.invoke(bean);
				return val == null ? null : val.toString();
			}
		} catch (Exception e) {}
		return null;
	}
	/**
	 * 将java bean转换成一个Map
	 * @param bean
	 * @author 
	 * @since 
	 */
	public static Map<String, Object> beanToMap(Object bean) {
		Map<String, Object> map = new HashMap<String, Object>();
		beanToMap(bean, map);
		return map;
	}
	/**
	 * 将java bean转换成一个Map
	 * @param bean
	 * @param map
	 * @author 
	 * @since 
	 */
	public static void beanToMap(Object bean, Map<String, Object> map) {
		if (map == null || bean == null) return;
		Method[] methods = bean.getClass().getDeclaredMethods();
		for (Method m : methods) {
			String name = m.getName();
			if (!Modifier.isPublic(m.getModifiers())) continue;
			if (!name.startsWith("get") && !name.startsWith("is")) continue;
			int position = name.startsWith("get") ? 3 : 2;
			String n = StringHelpers.lowerFirst(name.substring(position));
			try {
				Object val = m.invoke(bean);
				map.put(n, val);
			} catch (Exception e) {
			}
		}
	}
	@SuppressWarnings("unchecked")
	public static Map<String, Object> bean2Map(Object bean) {
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject json = JSONObject.fromObject(bean, JsonUtil.COLLECTION_FILTER);
		for (Iterator<Map.Entry<String, Object>> it = json.entrySet().iterator(); it.hasNext();) {
			Map.Entry<String, Object> next = it.next();
			map.put(next.getKey(), next.getValue());
		}
		return map;
	}
	
	public static void bean2Bean(Object src, Object dest, String... excludes) {
		if (src == null || dest == null || src == dest) return;
		Method[] methods = src.getClass().getDeclaredMethods();
		for (Method m : methods) {
			String name = m.getName();
			if (!Modifier.isPublic(m.getModifiers())) continue;
			if (!name.startsWith("get") && !name.startsWith("is")) continue;
			if (Collection.class.isAssignableFrom(m.getReturnType())) continue;
			boolean exc = false;
			for (String exclude : excludes) {
				if (name.equalsIgnoreCase(exclude)) {
					exc = true;
					break;
				}
			}
			if (exc) continue;
			int position = name.startsWith("get") ? 3 : 2;
			String method = name.substring(position);
			try {
				Object val = m.invoke(src);
				if (val == null) continue;
				Method targetFun = dest.getClass().getMethod("set" + method, m.getReturnType());
				targetFun.invoke(dest, val);
			} catch (Exception e) {
				log.error(e);
			}
			
		}
	}
	
	/**
	 * 把Map转换成java bean
	 * @param map
	 * @param bean
	 * @author 
	 * @since 
	 */
	public static void mapToBean(Map<?, ?> map, Object bean) {
		if (map == null || bean == null) return;
		try {
			org.apache.commons.beanutils.BeanUtils.populate(bean, map);
		} catch (Exception e) {
			log.error(e);
		}
	}

	public static String getSqlFormObj(Object obj, String... excludes) {
		String sql = "select * from "
				+ StringHelpers.getTableName(obj.getClass()) + " where 1=1 "
				+ getConditions(obj, excludes);
		return sql;
	}
	
	public static String getConditions(Object obj, String... excludes) {
		String[] fieldsName = ReflectUtil.getAllFieldsName(obj);
		ArrayList<Object> args = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer();
		for (String fieldName : fieldsName) {
			Object value = ReflectUtil.getFieldValue(obj, fieldName);
			if (fieldName.equals("id")) continue;
			for (String exclude : excludes) {
				if (fieldName.equals(exclude)) continue;
			}
			if (value != null) {
				sb.append(" and "+ fieldName + "=?,");
				args.add(value);
			}
		}
		String sql = sb.toString();
		if (sql.endsWith(",")) {
			sb.deleteCharAt(sb.length()-1);
			sql = sb.toString();
		}
		return sql;
	}
	
	public static Object[] getArgs(Object obj, String... excludes) {
		ArrayList<Object> args = new ArrayList<Object>();
		String[] fieldsName = ReflectUtil.getAllFieldsName(obj);
		for (String fieldName : fieldsName) {
			Object value = ReflectUtil.getFieldValue(obj, fieldName);
			if (fieldName.equals("id")) continue;
			for (String exclude : excludes) {
				if (fieldName.equals(exclude)) continue;
			}
			if (value != null) {
				args.add(value);
			}
		}
		return args.toArray(new Object[args.size()]);
	}
	
	public static void main(String[] args) {
		TsysParameter rp = new TsysParameter();
		Map<String, String> map = new HashMap<String, String>();
		map.put("paraOrder", "1");
		mapToBean(map, rp);
		System.out.println(rp.getParaOrder());
	}
}
