package com.hihframework.core.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
@Service
public class SpringBeanLoader implements ApplicationContextAware {

	private static ApplicationContext context = null;

	@Override
	public void setApplicationContext(ApplicationContext ctx)
			throws BeansException {
		context = ctx;
	}

	public synchronized static Object getBean(final String beanName) {
		if (context != null) {
			return context.getBean(beanName);
		}
		return null;
	}
	
	public static <T> T getBean(Class<T> clazz) {
		return context.getBean(clazz);
	}
}
