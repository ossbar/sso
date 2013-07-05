package com.hihsoft.sso.sysmonitor.syslogs.aop;

import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;

import com.hihframework.core.utils.DateUtils;
import com.hihframework.core.utils.SpringBeanLoader;
import com.hihsoft.sso.sysmonitor.syslogs.model.TlogServicecall;
import com.hihsoft.sso.sysmonitor.syslogs.service.TlogServicecallService;

/**
 * <p> Title:日志异步存入数据库 </p>
 * <p> Description:</p>
 * <p> Copyright: Copyright (c) 2013 </p>
 * <p> Company:hihsoft.co.,ltd </p>
 *
 * @author hihsoft.co.,ltd
 * @version 1.0
 */
public class LoggerThreadService implements Runnable {
	private final Logger log = Logger.getLogger(LoggerThreadService.class);

	private TlogServicecallService tlogServicecallService;
	



	/**
	 * 异步执行任务入口
	 */
	public void run() {
		final SimpleDateFormat format = new SimpleDateFormat(
		"yyyy-MM-dd HH:mm:ss");
		TlogServicecall tlogServicecall=new TlogServicecall();
		tlogServicecall.setCallTime(format.format(DateUtils.getNowDate()));
		tlogServicecall.setConsumeTime("20");
		log.info("--------------begin异常执行日志写入数据库 的操作-----------");
		final TlogServicecallService tlogServicecallService = (TlogServicecallService) SpringBeanLoader.getBean("tlogServicecallService");
		tlogServicecallService.saveOrUpdateTlogServicecall(tlogServicecall);
		log.info("--------------end异常执行日志写入数据库 的操作-----------");
	}

}
