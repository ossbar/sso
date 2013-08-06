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

public class TlogSysexception extends BaseEntity {
	
	private static final long serialVersionUID = 3592613808607821150L;
	//alias
	public static final String TABLE_ALIAS = "TlogSysexception";
	public static final String ALIAS_EXCEPTION_OID = "exceptionOid";
	public static final String ALIAS_EXCEPTION_TIMES = "exceptionTimes";
	public static final String ALIAS_EXCEPTION_LASTTIME = "exceptionLasttime";
	public static final String ALIAS_EXCEPTION_NAME = "exceptionName";
	public static final String ALIAS_EXCEPTION_MSG = "exceptionMsg";
	public static final String ALIAS_EXCEPTION_CLASS = "exceptionClass";
	public static final String ALIAS_USERID = "userid";
	
	//date formats
	
	//columns START
	private java.lang.String exceptionOid;
	private java.lang.String exceptionTimes;
	private java.lang.String exceptionLasttime;
	private java.lang.String exceptionName;
	private java.lang.String exceptionMsg;
	private java.lang.String exceptionClass;
	private java.lang.String userid;
	//columns END

	public TlogSysexception(){
	}

	public TlogSysexception(
		java.lang.String exceptionOid
	){
		this.exceptionOid = exceptionOid;
	}

	public void setExceptionOid(java.lang.String value) {
		this.exceptionOid = value;
	}
	
	public java.lang.String getExceptionOid() {
		return this.exceptionOid;
	}
	public void setExceptionTimes(java.lang.String value) {
		this.exceptionTimes = value;
	}
	
	public java.lang.String getExceptionTimes() {
		return this.exceptionTimes;
	}
	public void setExceptionLasttime(java.lang.String value) {
		this.exceptionLasttime = value;
	}
	
	public java.lang.String getExceptionLasttime() {
		return this.exceptionLasttime;
	}
	public void setExceptionName(java.lang.String value) {
		this.exceptionName = value;
	}
	
	public java.lang.String getExceptionName() {
		return this.exceptionName;
	}
	public void setExceptionMsg(java.lang.String value) {
		this.exceptionMsg = value;
	}
	
	public java.lang.String getExceptionMsg() {
		return this.exceptionMsg;
	}
	public void setExceptionClass(java.lang.String value) {
		this.exceptionClass = value;
	}
	
	public java.lang.String getExceptionClass() {
		return this.exceptionClass;
	}
	public void setUserid(java.lang.String value) {
		this.userid = value;
	}
	
	public java.lang.String getUserid() {
		return this.userid;
	}

	public String toString() {
		return new ToStringBuilder(this)
			.append("ExceptionOid",getExceptionOid())
			.append("ExceptionTimes",getExceptionTimes())
			.append("ExceptionLasttime",getExceptionLasttime())
			.append("ExceptionName",getExceptionName())
			.append("ExceptionMsg",getExceptionMsg())
			.append("ExceptionClass",getExceptionClass())
			.append("Userid",getUserid())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getExceptionOid())
			.append(getExceptionTimes())
			.append(getExceptionLasttime())
			.append(getExceptionName())
			.append(getExceptionMsg())
			.append(getExceptionClass())
			.append(getUserid())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TlogSysexception == false) return false;
		if(this == obj) return true;
		TlogSysexception other = (TlogSysexception)obj;
		return new EqualsBuilder()
			.append(getExceptionOid(),other.getExceptionOid())
			.append(getExceptionTimes(),other.getExceptionTimes())
			.append(getExceptionLasttime(),other.getExceptionLasttime())
			.append(getExceptionName(),other.getExceptionName())
			.append(getExceptionMsg(),other.getExceptionMsg())
			.append(getExceptionClass(),other.getExceptionClass())
			.append(getUserid(),other.getUserid())
			.isEquals();
	}
}

//uncap_first表示首字母小写；cap_first首字母大写；lower_case将字符串转为小写upper_case将字符串转为大写
