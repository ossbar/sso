package com.hihsoft.sso.sysmonitor.syswatch.memory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
/**
 * <p> Title: 系统内存监控过滤器</p>
 * <p> Description:</p>
 * <p> Copyright: Copyright (c) 2013 </p>
 * <p> Company:hihsoft.co.,ltd </p>
 *
 * @author hihsoft.co.,ltd
 * @version 1.0
 */
public class SystemMemoryInterceptor extends HandlerInterceptorAdapter{
	protected static Logger logger = LoggerFactory.getLogger(SystemMemoryInterceptor.class);
	public static boolean enabled = true;

	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		if(enabled){
			int a=1024*1024;
			String maxMem=Runtime.getRuntime().maxMemory()/a+"MB";
			String currentMem=Runtime.getRuntime().totalMemory()/a+"MB";
			String  residualMem=Runtime.getRuntime().freeMemory()/a+"MB";
			request.setAttribute("maxMem", maxMem);
			request.setAttribute("currentMem", currentMem);
			request.setAttribute("residualMem", residualMem);
			float b=(float)(a);
			logger.info("JVM内存分配: 额定"+(float)((float)Runtime.getRuntime().maxMemory()/b)+"MB 实际"+(float)(Runtime.getRuntime().totalMemory()/b)+"MB 剩余"+(float)Runtime.getRuntime().freeMemory()/b+"MB");
		}
		return super.preHandle(request, response, handler);
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled2) {
		enabled = enabled2;
	}

	

}
