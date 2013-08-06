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

public class TsysTreeprivilege extends BaseEntity {
	
	private static final long serialVersionUID = -3436579201146384647L;
	//alias
	public static final String TABLE_ALIAS = "TsysTreeprivilege";
	public static final String ALIAS_TREEID = "treeid";
	public static final String ALIAS_ORGID = "orgid";
	public static final String ALIAS_USERID = "userid";
	
	//date formats
	
	//columns START
	private java.lang.String treeid;
	private java.lang.String orgid;
	private java.lang.String userid;
	//columns END

	public TsysTreeprivilege(){
	}

	public TsysTreeprivilege(
		java.lang.String treeid
	){
		this.treeid = treeid;
	}

	public void setTreeid(java.lang.String value) {
		this.treeid = value;
	}
	
	public java.lang.String getTreeid() {
		return this.treeid;
	}
	public void setOrgid(java.lang.String value) {
		this.orgid = value;
	}
	
	public java.lang.String getOrgid() {
		return this.orgid;
	}
	public void setUserid(java.lang.String value) {
		this.userid = value;
	}
	
	public java.lang.String getUserid() {
		return this.userid;
	}

	public String toString() {
		return new ToStringBuilder(this)
			.append("Treeid",getTreeid())
			.append("Orgid",getOrgid())
			.append("Userid",getUserid())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getTreeid())
			.append(getOrgid())
			.append(getUserid())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TsysTreeprivilege == false) return false;
		if(this == obj) return true;
		TsysTreeprivilege other = (TsysTreeprivilege)obj;
		return new EqualsBuilder()
			.append(getTreeid(),other.getTreeid())
			.append(getOrgid(),other.getOrgid())
			.append(getUserid(),other.getUserid())
			.isEquals();
	}
}

//uncap_first表示首字母小写；cap_first首字母大写；lower_case将字符串转为小写upper_case将字符串转为大写
