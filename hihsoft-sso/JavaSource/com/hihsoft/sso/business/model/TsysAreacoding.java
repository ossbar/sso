/**
 * Copyright (c) 2013-2015 www.javahih.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.hihsoft.sso.business.model;
import com.hihsoft.baseclass.model.BaseEntity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.*;
/**
 * <p> Title: </p>
 * <p> Description:</p>
 * <p> Copyright: Copyright (c) 2011 </p>
 * <p> Company:javahih.co.,ltd </p>
 *
 * @author zhujw
 * @version 1.0
 */

public class TsysAreacoding extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "TsysAreacoding";
	public static final String ALIAS_AREACODINGID = "areacodingid";
	public static final String ALIAS_AREACODE = "areacode";
	public static final String ALIAS_AREANAME = "areaname";
	public static final String ALIAS_AREATYPE = "areatype";
	public static final String ALIAS_PARENTAREA = "parentarea";
	
	//date formats
	
	//columns START
	private java.lang.String areacodingid;
	private java.lang.String areacode;
	private java.lang.String areaname;
	private java.lang.String areatype;
	private java.lang.String parentarea;
	//columns END

	public TsysAreacoding(){
	}

	public TsysAreacoding(
		java.lang.String areacodingid
	){
		this.areacodingid = areacodingid;
	}

	public void setAreacodingid(java.lang.String value) {
		this.areacodingid = value;
	}
	
	public java.lang.String getAreacodingid() {
		return this.areacodingid;
	}
	public void setAreacode(java.lang.String value) {
		this.areacode = value;
	}
	
	public java.lang.String getAreacode() {
		return this.areacode;
	}
	public void setAreaname(java.lang.String value) {
		this.areaname = value;
	}
	
	public java.lang.String getAreaname() {
		return this.areaname;
	}
	public void setAreatype(java.lang.String value) {
		this.areatype = value;
	}
	
	public java.lang.String getAreatype() {
		return this.areatype;
	}
	public void setParentarea(java.lang.String value) {
		this.parentarea = value;
	}
	
	public java.lang.String getParentarea() {
		return this.parentarea;
	}

	public String toString() {
		return new ToStringBuilder(this)
			.append("Areacodingid",getAreacodingid())
			.append("Areacode",getAreacode())
			.append("Areaname",getAreaname())
			.append("Areatype",getAreatype())
			.append("Parentarea",getParentarea())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getAreacodingid())
			.append(getAreacode())
			.append(getAreaname())
			.append(getAreatype())
			.append(getParentarea())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TsysAreacoding == false) return false;
		if(this == obj) return true;
		TsysAreacoding other = (TsysAreacoding)obj;
		return new EqualsBuilder()
			.append(getAreacodingid(),other.getAreacodingid())
			.append(getAreacode(),other.getAreacode())
			.append(getAreaname(),other.getAreaname())
			.append(getAreatype(),other.getAreatype())
			.append(getParentarea(),other.getParentarea())
			.isEquals();
	}
}

//uncap_first表示首字母小写；cap_first首字母大写；lower_case将字符串转为小写upper_case将字符串转为大写
