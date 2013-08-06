/**
 * Copyright (c) 2013-2015 www.javahih.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.hihsoft.sso.business.model;
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

public class TaclUserprivilege extends BaseEntity {
	
	private static final long serialVersionUID = 2095883715615710204L;
	//alias
	public static final String TABLE_ALIAS = "TaclUserprivilege";
	public static final String ALIAS_USERPRVIID = "userprviid";
	public static final String ALIAS_MODULEID = "moduleid";
	public static final String ALIAS_OPERATEID = "operateid";
	public static final String ALIAS_USERID = "userid";
	
	//date formats
	
	//columns START
	private java.lang.String userprviid;
	private java.lang.String moduleid;
	private java.lang.String operateid;
	private java.lang.String userid;
	//columns END

	public TaclUserprivilege(){
	}

	public TaclUserprivilege(
		java.lang.String userprviid
	){
		this.userprviid = userprviid;
	}

	public void setUserprviid(java.lang.String value) {
		this.userprviid = value;
	}
	
	public java.lang.String getUserprviid() {
		return this.userprviid;
	}
	public void setModuleid(java.lang.String value) {
		this.moduleid = value;
	}
	
	public java.lang.String getModuleid() {
		return this.moduleid;
	}
	public void setOperateid(java.lang.String value) {
		this.operateid = value;
	}
	
	public java.lang.String getOperateid() {
		return this.operateid;
	}
	public void setUserid(java.lang.String value) {
		this.userid = value;
	}
	
	public java.lang.String getUserid() {
		return this.userid;
	}

	public String toString() {
		return new ToStringBuilder(this)
			.append("Userprviid",getUserprviid())
			.append("Moduleid",getModuleid())
			.append("Operateid",getOperateid())
			.append("Userid",getUserid())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getUserprviid())
			.append(getModuleid())
			.append(getOperateid())
			.append(getUserid())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TaclUserprivilege == false) return false;
		if(this == obj) return true;
		TaclUserprivilege other = (TaclUserprivilege)obj;
		return new EqualsBuilder()
			.append(getUserprviid(),other.getUserprviid())
			.append(getModuleid(),other.getModuleid())
			.append(getOperateid(),other.getOperateid())
			.append(getUserid(),other.getUserid())
			.isEquals();
	}
}

//uncap_first表示首字母小写；cap_first首字母大写；lower_case将字符串转为小写upper_case将字符串转为大写
