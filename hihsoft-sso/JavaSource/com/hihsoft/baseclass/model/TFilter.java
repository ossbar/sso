/**
 * Copyright (c) 2013-2015 www.javahih.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.hihsoft.baseclass.model;

import javax.servlet.GenericServlet;

public class TFilter extends BaseEntity {
	private static final long serialVersionUID = 1L;
	/**
	 * 请求规则
	 */
	private String urlrules;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 描述
	 */
	private String fdesc;
	/**
	 * 启用拦截类名
	 */
	private String classname;
	/**
	 * 顺序
	 */
	private Long px;
	/**
	 * 参数
	 */
	private String params;
	/**
	 * 状态
	 */
	private String status;

	public String getUrlrules() {
		return this.urlrules;
	}

	public void setUrlrules(String urlrules) {
		this.urlrules = urlrules;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFdesc() {
		return this.fdesc;
	}

	public void setFdesc(String fdesc) {
		this.fdesc = fdesc;
	}

	public String getClassname() {
		return this.classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public Long getPx() {
		return this.px;
	}

	public void setPx(Long px) {
		this.px = px;
	}

	public String getParams() {
		return this.params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
