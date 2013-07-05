/**
 * Copyright (c) 2013-2015 www.javahih.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.hihframework.osplugins.properties;

import org.apache.log4j.Logger;

import com.hihsoft.baseclass.constants.Consts;

/**
 * <p> Title:加载基本的配置文件：包括全局参数配置，缓存配置文件等 </p>
 * <p> Description:SystemGlobals.properties、ApplicationResources.properties配置文件</p>
 * <p>解决像类似spring等其他属性文件修改后不能自动加载，必须重启应用服务器的问题</p>
 * <p> Copyright: Copyright (c) 2013 </p>
 * <p> Company:hihsoft.co.,ltd </p>
 *  
 * @author hihsoft.co.,ltd
 * @version 1.0
 */

public class ConfigLoader {

	/** The Constant logger. */
	private static final Logger log = Logger.getLogger(ConfigLoader.class);

	/**
	 * Listen for changes.
	 */
	public static void listenForChanges() {
		int fileChangesDelay = PropertiesUtil
				.getIntValue(Consts.FILECHANGES_DELAY);
		log.info("监控属性文件变化的时间设置"+fileChangesDelay);

		if (fileChangesDelay > 0) {
			// 加载spring MVC属性文件的改变
			FileMonitor.getInstance().addFileChangeListener(
					PropertiesUtil.getValue(Consts.SPRING_PROPERTIES),
					fileChangesDelay);
			// 其它属性文件的变化在此添加

		}
	}

}
