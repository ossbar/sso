/**
 * Copyright (c) 2013-2015 www.javahih.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.hihsoft.sso.sysmonitor.syslogs.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.StopWatch;
import org.apache.log4j.Logger;

/**
 * <p> Title:监控系统中执行服务调用情况，
 * 方便优化程序结构，提高系统性能</p>
 * <p> Description:</p>
 * <p> Copyright: Copyright (c) 2013 </p>
 * <p> Company:hihsoft.co.,ltd </p>
 *  @author hihsoft.co.,ltd
 * @version 1.0
 */
public class LogServiceCallAdvice implements MethodInterceptor
{

	/** The Constant log. */
	private static final Logger log = Logger.getLogger(LogServiceCallAdvice.class);

	/*
	 * 拦截要执行的目标方法
	 * 
	 * @see
	 * org.aopalliance.intercept.MethodInterceptor#invoke(org.aopalliance.intercept
	 * .MethodInvocation)
	 */
	@Override
	public Object invoke(final MethodInvocation invocation) throws Throwable
	{
		final String clazzString = invocation.getThis().getClass().getName();// 类名称
		final String methodName = invocation.getMethod().getName();// 方法名称
		final String fullPath = clazzString + "." + methodName;
		final StopWatch clock = new StopWatch();
		clock.start(); // 计时开始
		final Object result = invocation.proceed();
		clock.stop(); // 计时结束 //方法参数类型，转换成简单类型

		final Class[] params = invocation.getMethod().getParameterTypes();
		final String[] simpleParams = new String[params.length];
		for (int i = 0; i < params.length; i++)
		{
			simpleParams[i] = params[i].getSimpleName();
		}
		//通过log4j.xml文件配置，打印到日志文件中或控制台
		log.info("-----------------------------执行业务方法时，系统开始记录调用耗费的时间------------------------------------------------");
		log.info("执行业务[" + methodName + "(" + StringUtils.join(simpleParams, ",") + ")];总共耗时:" + clock.getTime() + "毫秒;全路径[" + fullPath + "]");
		return result;
	}
}
