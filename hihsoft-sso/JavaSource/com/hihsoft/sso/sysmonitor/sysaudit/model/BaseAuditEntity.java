package com.hihsoft.sso.sysmonitor.sysaudit.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.hihframework.core.utils.DateUtils;
import com.hihsoft.baseclass.model.IUser;

/**
 * <p>
 * Title: 面向对象的值对象传递：所有实体类的基类
 * Description:公共属性
 * Copyright: Copyright (c) 2013
 * Company:hihsoft.co.,ltd
 * @author hihsoft.co.,ltd
 * @version 1.0
 */
public class BaseAuditEntity implements Serializable, Cloneable,
		HistorizableEntity {
	private static final long serialVersionUID = 1L;
	private Date createTime;

	private Date modifyTime;

	private IUser createUser;

	private IUser modifyUser;
	private String contextPath;

	public String getContextPath() {
		return contextPath;
	}

	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public IUser getCreateUser() {
		return createUser;
	}

	public void setCreateUser(IUser createUser) {

		this.createUser = createUser;
	}

	public IUser getModifyUser() {
		return modifyUser;
	}

	public void setModifyUser(IUser modifyUser) {
		this.modifyUser = modifyUser;
	}
}
