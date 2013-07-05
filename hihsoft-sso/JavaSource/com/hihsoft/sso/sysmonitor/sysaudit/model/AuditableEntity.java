/**
 * Copyright (c) 2013-2015 www.javahih.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.hihsoft.sso.sysmonitor.sysaudit.model;

import java.util.Date;

import com.hihsoft.baseclass.model.IUser;

/**
 * 标识商业对象需要有审计记录的接口,领域对象记录创建,修改时间及创建,修改人的接口.
 */
public interface AuditableEntity {
	public Date getCreateTime();

	public void setCreateTime(Date createTime);

	public Date getModifyTime();

	public void setModifyTime(Date modifyTime);

	public IUser getCreateUser();

	public void setCreateUser(IUser user);

	public IUser getModifyUser();

	public void setModifyUser(IUser user);

	public String getId();
}
