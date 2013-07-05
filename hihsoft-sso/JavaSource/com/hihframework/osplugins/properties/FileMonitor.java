/**
 * Copyright (c) 2013-2015 www.javahih.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.hihframework.osplugins.properties;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;

/**
 * <p> Title:监听属性文件配置，实时刷新内容 </p>
 * <p> Description:</p>
 * <p> Copyright: Copyright (c) 2013 </p>
 * <p> Company:hihsoft.co.,ltd </p>
 *
 * @author hihsoft.co.,ltd
 * @version 1.0
 */
public class FileMonitor {
	private static Logger log = Logger.getLogger(FileMonitor.class);

	private static final FileMonitor instance = new FileMonitor();

	private Timer timer;

	private Map timerEntries;

	private FileMonitor() {
		this.timerEntries = new HashMap();
		this.timer = new Timer(true);
	}

	public static FileMonitor getInstance() {
		return instance;
	}

	/**
	 * 添加文件改变的监听配置
	 * @param filename
	 * @param period
	 */
	public void addFileChangeListener(String filename, long period) {
		this.removeFileChangeListener(filename);
		log.info("监控的文件名称"+filename);
		FileMonitorTask task = new FileMonitorTask(filename);
		this.timerEntries.put(filename, task);
		this.timer.schedule(task, period, period);
	}

	/**
	 * 停止监控
	 * @param filename
	 */
	public void removeFileChangeListener(String filename) {
		FileMonitorTask task = (FileMonitorTask) this.timerEntries
				.remove(filename);

		if (task != null) {
			task.cancel();
		}
	}

	private static class FileMonitorTask extends TimerTask {
		private String filename;

		private File monitoredFile;

		private long lastModified;

		public FileMonitorTask(String filename) {
			this.filename = filename;

			this.monitoredFile = new File(filename);
			if (!this.monitoredFile.exists()) {
				return;
			}

			this.lastModified = this.monitoredFile.lastModified();
		}

		public void run() {
			long latestChange = this.monitoredFile.lastModified();
			if (this.lastModified != latestChange) {
				this.lastModified = latestChange;

			}
		}
	}
}
