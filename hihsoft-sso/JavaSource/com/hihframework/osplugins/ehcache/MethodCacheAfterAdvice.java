package com.hihframework.osplugins.ehcache;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;
import org.apache.log4j.Logger;
import net.sf.ehcache.Cache;

import java.lang.reflect.Method;
import java.io.Serializable;
import java.util.Iterator;

/**
 * <p> Title: 面向方面编程AOP思想的应用</p>
 * <p> Description:利用ehcache实现Hibernate的二级缓存机制</p>
 * <p> Copyright: Copyright (c) 2013 </p>
 * <p> Company:hihsoft.co.,ltd </p>
 *
 * @author hihsoft.co.,ltd
 * @version 1.0
 */
public class MethodCacheAfterAdvice implements AfterReturningAdvice,
		InitializingBean {
	private Logger logger = Logger.getLogger(MethodCacheAfterAdvice.class);

	private Cache cache;

	public void setCache(Cache cache) {
		this.cache = cache;
	}

	/**
	 *
	 */
	public MethodCacheAfterAdvice() {
		super();
	}

	public void afterReturning(Object returnValue, Method method,
			Object[] args, Object target) throws Throwable {
		for (Iterator itr = cache.getKeys().iterator(); itr.hasNext();) {
			Serializable key = (Serializable) itr.next();
			logger.info("得到要清除缓存的方法－－－－－!" + method.getName());
			// if(key.toString()..contains(target.getClass().getName())){
			cache.remove(key);
			logger.info("正在清除方法缓存!");
		}
	}

	public void afterPropertiesSet() throws Exception {
		Assert.notNull(cache, "需要一个缓存. 使用setCache(Cache)分配一个.");
	}

}
