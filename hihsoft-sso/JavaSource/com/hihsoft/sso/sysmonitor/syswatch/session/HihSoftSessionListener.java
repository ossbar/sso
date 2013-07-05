/**
 * Copyright (c) 2013-2015 www.javahih.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.hihsoft.sso.sysmonitor.syswatch.session;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import jxl.common.Logger;
/**
 * 监听所有会话的创建和失效,供后期监控及分析用
 * @author hihsoft.co.,ltd
 * @since 2013-3-2
 */
public class HihSoftSessionListener implements HttpSessionListener {
	Logger logger = Logger.getLogger(HihSoftSessionListener.class);
	
	private static final Map<String, HttpSession> context = new HashMap<String, HttpSession>();
	
	@Override
	public void sessionCreated(HttpSessionEvent e) {
		context.put(e.getSession().getId(), e.getSession());
		logger.info("已创建新会话，当前会话数量："+context.size());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent e) {
		context.remove(e.getSession().getId());
		logger.info("已销毁会话，当前会话数量："+context.size());
	}
	/**
	 * 根据sessionId来获取一个已有会话
	 * @param sessionId
	 * @return
	 * @author xjf721
	 * @since 2012-3-2
	 */
	public synchronized static HttpSession getSession(String sessionId) {
		return context.get(sessionId);
	}

}
