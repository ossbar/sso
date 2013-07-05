/**
 * Copyright (c) 2013-2015 www.javahih.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.hihframework.exception;

/**
 * <p> Title:统一异常处理</p>
 * <p> Description:实现手拉手业务基础平台异常处理类</p>
 * <p> Copyright: Copyright (c) 2013 </p>
 * <p> Company:hihsoft.co.,ltd </p>
 *  @author hihsoft.co.,ltd
 * @version 1.0
 */
public class HihSoftException extends RuntimeException {
	// 异常代码
	private String key;
	private Object[] values;

	// 构造器重载
	public HihSoftException() {
		super();
	}

	public HihSoftException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public HihSoftException(String message) {
		super(message);
	}

	public HihSoftException(Throwable throwable) {
		super(throwable);
	}

	public HihSoftException(String message, String key) {
		super(message);
		this.key = key;
	}

	public HihSoftException(String message, String key, Object value) {
		super(message);
		this.key = key;
		this.values = new Object[] { value };
	}

	public HihSoftException(String message, String key, Object[] values) {
		super(message);
		this.key = key;
		this.values = values;
	}

	public String getKey() {
		return key;
	}

	public Object[] getValues() {
		return values;
	}

}
