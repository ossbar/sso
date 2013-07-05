package com.hihsoft.baseclass.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;

import com.hihframework.core.utils.DateUtils;

/**
 * Title: 面向对象的值对象传递：所有实体类的基类
 * Description:公共属性
 * Copyright: Copyright (c) 2013
 * Company:hihsoft.co.,ltd
 * @author hihsoft.co.,ltd
 * @version 1.0
 */
public class BaseEntity implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;
	private String contextPath;

	public String getContextPath() {
		return contextPath;
	}

	public void setContextPath(final String contextPath) {
		this.contextPath = contextPath;
	}

	/**
	 * dto对象的复制
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public String createTimeStamp() {
		final SimpleDateFormat format = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		return format.format(DateUtils.getNowDate());
	}
}
