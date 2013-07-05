/**
 * Copyright (c) 2013-2015 www.javahih.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.hihsoft.sso.sysmonitor.syslogs.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hihsoft.baseclass.model.BaseEntity;

/**
 * <p> Title: </p>
 * <p> Description:</p>
 * <p> Copyright: Copyright (c) 2011 </p>
 * <p> Company:javahih.co.,ltd </p>
 *
 * @author zhujw
 * @version 1.0
 */

public class TlogServicecall extends BaseEntity
{
	//alias
	public static final String TABLE_ALIAS = "TlogServicecall";
	public static final String ALIAS_CALLID = "callid";
	public static final String ALIAS_SERVICE_CLASS = "serviceClass";
	public static final String ALIAS_USERID = "userid";
	public static final String ALIAS_METHOD_NAME = "methodName";
	public static final String ALIAS_CONSUME_TIME = "consumeTime";
	public static final String ALIAS_CALL_T_I_M_E = "callTime";

	//date formats

	//columns START
	private java.lang.String callid;
	private java.lang.String serviceClass;
	private java.lang.String userid;
	private java.lang.String methodName;
	private java.lang.String consumeTime;
	private java.lang.String callTime;

	//columns END

	public TlogServicecall()
	{
	}

	public TlogServicecall(final java.lang.String callid)
	{
		this.callid = callid;
	}

	public void setCallid(final java.lang.String value)
	{
		this.callid = value;
	}

	public java.lang.String getCallid()
	{
		return this.callid;
	}

	public void setServiceClass(final java.lang.String value)
	{
		this.serviceClass = value;
	}

	public java.lang.String getServiceClass()
	{
		return this.serviceClass;
	}

	public void setUserid(final java.lang.String value)
	{
		this.userid = value;
	}

	public java.lang.String getUserid()
	{
		return this.userid;
	}

	public void setMethodName(final java.lang.String value)
	{
		this.methodName = value;
	}

	public java.lang.String getMethodName()
	{
		return this.methodName;
	}

	public void setConsumeTime(final java.lang.String value)
	{
		this.consumeTime = value;
	}

	public java.lang.String getConsumeTime()
	{
		return this.consumeTime;
	}

	public void setCallTime(final java.lang.String value)
	{
		this.callTime = value;
	}

	public java.lang.String getCallTime()
	{
		return this.callTime;
	}

	@Override
	public String toString()
	{
		return new ToStringBuilder(this).append("Callid", getCallid()).append("ServiceClass", getServiceClass()).append("Userid", getUserid()).append("MethodName", getMethodName()).append("ConsumeTime", getConsumeTime()).append("CallTime", getCallTime()).toString();
	}

	@Override
	public int hashCode()
	{
		return new HashCodeBuilder().append(getCallid()).append(getServiceClass()).append(getUserid()).append(getMethodName()).append(getConsumeTime()).append(getCallTime()).toHashCode();
	}

	@Override
	public boolean equals(final Object obj)
	{
		if (obj instanceof TlogServicecall == false)
			return false;
		if (this == obj)
			return true;
		final TlogServicecall other = (TlogServicecall) obj;
		return new EqualsBuilder().append(getCallid(), other.getCallid()).append(getServiceClass(), other.getServiceClass()).append(getUserid(), other.getUserid()).append(getMethodName(), other.getMethodName()).append(getConsumeTime(), other.getConsumeTime())
				.append(getCallTime(), other.getCallTime()).isEquals();
	}
}

//uncap_first表示首字母小写；cap_first首字母大写；lower_case将字符串转为小写upper_case将字符串转为大写
