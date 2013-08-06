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

public class TlogLoginlog extends BaseEntity {
	
	private static final long serialVersionUID = 2526149712159264767L;
	//alias
	public static final String TABLE_ALIAS = "TlogLoginlog";
	public static final String ALIAS_LOGID = "logid";
	public static final String ALIAS_IPADDR = "ipaddr";
	public static final String ALIAS_LOGINTIME = "logintime";
	public static final String ALIAS_LOGOUTTIME = "logouttime";
	public static final String ALIAS_ONLINETIME = "onlinetime";
	public static final String ALIAS_USERID = "userid";
	public static final String ALIAS_LOGOUT = "logout";
	public static final String ALIAS_ORGID = "orgid";
	
	//date formats
	
	//columns START
	private java.lang.String logid;
	private java.lang.String ipaddr;
	private java.lang.String logintime;
	private java.lang.String logouttime;
	private java.lang.String onlinetime;
	private java.lang.String userid;
	private java.lang.String logout;
	private java.lang.String orgid;
	//columns END

	public TlogLoginlog(){
	}

	public TlogLoginlog(
		java.lang.String logid
	){
		this.logid = logid;
	}

	public void setLogid(java.lang.String value) {
		this.logid = value;
	}
	
	public java.lang.String getLogid() {
		return this.logid;
	}
	public void setIpaddr(java.lang.String value) {
		this.ipaddr = value;
	}
	
	public java.lang.String getIpaddr() {
		return this.ipaddr;
	}
	public void setLogintime(java.lang.String value) {
		this.logintime = value;
	}
	
	public java.lang.String getLogintime() {
		return this.logintime;
	}
	public void setLogouttime(java.lang.String value) {
		this.logouttime = value;
	}
	
	public java.lang.String getLogouttime() {
		return this.logouttime;
	}
	public void setOnlinetime(java.lang.String value) {
		this.onlinetime = value;
	}
	
	public java.lang.String getOnlinetime() {
		return this.onlinetime;
	}
	public void setUserid(java.lang.String value) {
		this.userid = value;
	}
	
	public java.lang.String getUserid() {
		return this.userid;
	}
	public void setLogout(java.lang.String value) {
		this.logout = value;
	}
	
	public java.lang.String getLogout() {
		return this.logout;
	}
	public void setOrgid(java.lang.String value) {
		this.orgid = value;
	}
	
	public java.lang.String getOrgid() {
		return this.orgid;
	}

	public String toString() {
		return new ToStringBuilder(this)
			.append("Logid",getLogid())
			.append("Ipaddr",getIpaddr())
			.append("Logintime",getLogintime())
			.append("Logouttime",getLogouttime())
			.append("Onlinetime",getOnlinetime())
			.append("Userid",getUserid())
			.append("Logout",getLogout())
			.append("Orgid",getOrgid())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getLogid())
			.append(getIpaddr())
			.append(getLogintime())
			.append(getLogouttime())
			.append(getOnlinetime())
			.append(getUserid())
			.append(getLogout())
			.append(getOrgid())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TlogLoginlog == false) return false;
		if(this == obj) return true;
		TlogLoginlog other = (TlogLoginlog)obj;
		return new EqualsBuilder()
			.append(getLogid(),other.getLogid())
			.append(getIpaddr(),other.getIpaddr())
			.append(getLogintime(),other.getLogintime())
			.append(getLogouttime(),other.getLogouttime())
			.append(getOnlinetime(),other.getOnlinetime())
			.append(getUserid(),other.getUserid())
			.append(getLogout(),other.getLogout())
			.append(getOrgid(),other.getOrgid())
			.isEquals();
	}
}

//uncap_first表示首字母小写；cap_first首字母大写；lower_case将字符串转为小写upper_case将字符串转为大写
