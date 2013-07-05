package com.hihframework.osplugins.ehcache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;
import java.io.Serializable;

/**
 * <p> Title:利用AOP思想配置缓存特性</p>
 * <p> Description:</p>
 * 1、默认条件下，所有方法返回结果都被缓存了(methodNames 是 null);
 * 2、缓存区利用 IoC 形成 cacheKey 的生成还包括方法参数的因素(参数的改变会影响 cacheKey);
 * <p> Copyright: Copyright (c) 2013 </p>
 * <p> Company:hihsoft.co.,ltd</p>
 *  @author hihsoft.co.,ltd
 * @version 1.0
 */

public class MethodCacheInterceptor implements MethodInterceptor,
		InitializingBean {
	private Logger logger = Logger.getLogger(MethodCacheInterceptor.class);

	private Cache cache;

	/**
	 * 设置缓存名
	 */

	public void setCache(Cache cache) {

		this.cache = cache;

	}

	public MethodCacheInterceptor() {
		super();

	}

	/**
	 * 主方法
	 * 如果某方法可被缓存就缓存其结果
	 * 方法结果必须是可序列化的(serializable)
	 */

	public Object invoke(MethodInvocation invocation) throws Throwable {

		String targetName = invocation.getThis().getClass().getName();

		String methodName = invocation.getMethod().getName();

		Object[] arguments = invocation.getArguments();

		Object result;

		logger.info("在缓存中查找方法返回的对象!");

		String cacheKey = getCacheKey(targetName, methodName, arguments);

		Element element = cache.get(cacheKey);

		if (element == null) {

			logger.info("正在拦截方法!");

			result = invocation.proceed();

			logger.info("正在缓存对象!");

			element = new Element(cacheKey, (Serializable) result);

			cache.put(element);

		}

		return element.getValue();

	}

	/**
	 * 创建一个缓存对象的标识,首先根据key查询缓存(key=className + methodName + arguments)
	 * <p/>
	 * ,缓存中存在则返回,否之调用invocation.proceed()返回结果.
	 */

	private String getCacheKey(String targetName, String methodName,

	Object[] arguments) {

		StringBuffer sb = new StringBuffer();

		sb.append(targetName).append('.').append(methodName);

		if ((arguments != null) && (arguments.length != 0)) {

			for (int i = 0; i < arguments.length; i++) {

				sb.append('.').append(arguments[i]);

			}

		}

		return sb.toString();

	}

	/*
	 * 
	 * 检查是否提供必要参数。
	 */

	public void afterPropertiesSet() throws Exception {

		Assert.notNull(cache, "需要一个缓存. 使用setCache(Cache)分配一个.");

	}

}
