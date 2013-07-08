/**
 * Copyright (c) 2013-2015 www.javahih.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.hihsoft.sso.util;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hihsoft.baseclass.model.BaseEntity;

/**
 * <p> Title:测试框架的基类 </p>
 * <p> Description:</p>
 * 
 * 1、主要实现配置文件的加载ctx
 * 2、模拟前端页面的get和post请求，方便测试前端页面
 * 3、模拟并发,优化系统性能用
 * <p> Copyright: Copyright (c) 2013 </p>
 * <p> Company:hihsoft.co.,ltd </p>
 *
 * @author zhujw
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={"classpath:/resources/springcfg/applicationContext-global.xml","classpath*:resources/mvccfg/springmvc-hihsoft-global.xml"})
public abstract class BaseTestCase extends AbstractJUnit4SpringContextTests {
	protected final Logger log = Logger.getLogger(this.getClass());


	/**
	 * Convenience methods to make tests simpler
	 */
	public MockHttpServletRequest newPost(String url) {
		return new MockHttpServletRequest("POST", url);
	}

	public MockHttpServletRequest newGet(String url) {
		return new MockHttpServletRequest("GET", url);
	}

	public void objectToRequestParameters(Object o,
			MockHttpServletRequest request) throws Exception {
		objectToRequestParameters(o, request, null);
	}

	/**
	 * Object to request parameters.
	 *
	 * @param o the o
	 * @param request the request
	 * @param prefix the prefix
	 * @throws Exception the exception
	 */
	public void objectToRequestParameters(Object o,
			MockHttpServletRequest request, String prefix) throws Exception {
		Class clazz = o.getClass();
		Field[] fields = clazz.getDeclaredFields();
		AccessibleObject.setAccessible(fields, true);

		for (int i = 0; i < fields.length; i++) {
			Object field = (fields[i].get(o));
			if (field instanceof BaseEntity) {
				objectToRequestParameters(field, request, fields[i].getName());
			} else if (!(field instanceof List) && !(field instanceof Set)) {
				String paramName = fields[i].getName();
				if (prefix != null) {
					paramName = prefix + "." + paramName;
				}
				request.addParameter(paramName,
						String.valueOf(fields[i].get(o)));
			}
		}
	}

}
