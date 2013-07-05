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

public class TsysParameter extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "TsysParameter";
	public static final String ALIAS_PARAID = "paraid";
	public static final String ALIAS_ISDEFAULT = "isdefault";
	public static final String ALIAS_PARANAME = "paraname";
	public static final String ALIAS_PARANO = "parano";
	public static final String ALIAS_PARA_CLASS = "paraClass";
	public static final String ALIAS_PARA_KEY = "paraKey";
	public static final String ALIAS_PARA_TYPE = "paraType";
	public static final String ALIAS_PARA_ORDER = "paraOrder";
	
	//date formats
	
	//columns START
	private java.lang.String paraid;
	private java.lang.String isdefault;
	private java.lang.String paraname;
	private java.lang.String parano;
	private java.lang.String paraClass;
	private java.lang.String paraKey;
	private java.lang.String paraType;
	private Long paraOrder;
	//columns END

	public TsysParameter(){
	}

	public TsysParameter(
		java.lang.String paraid
	){
		this.paraid = paraid;
	}

	public void setParaid(java.lang.String value) {
		this.paraid = value;
	}
	
	public java.lang.String getParaid() {
		return this.paraid;
	}
	public void setIsdefault(java.lang.String value) {
		this.isdefault = value;
	}
	
	public java.lang.String getIsdefault() {
		return this.isdefault;
	}
	public void setParaname(java.lang.String value) {
		this.paraname = value;
	}
	
	public java.lang.String getParaname() {
		return this.paraname;
	}
	public void setParano(java.lang.String value) {
		this.parano = value;
	}
	
	public java.lang.String getParano() {
		return this.parano;
	}
	public void setParaClass(java.lang.String value) {
		this.paraClass = value;
	}
	
	public java.lang.String getParaClass() {
		return this.paraClass;
	}
	public void setParaKey(java.lang.String value) {
		this.paraKey = value;
	}
	
	public java.lang.String getParaKey() {
		return this.paraKey;
	}
	public void setParaType(java.lang.String value) {
		this.paraType = value;
	}
	
	public java.lang.String getParaType() {
		return this.paraType;
	}
	public void setParaOrder(Long value) {
		this.paraOrder = value;
	}
	
	public Long getParaOrder() {
		return this.paraOrder;
	}

	public String toString() {
		return new ToStringBuilder(this)
			.append("Paraid",getParaid())
			.append("Isdefault",getIsdefault())
			.append("Paraname",getParaname())
			.append("Parano",getParano())
			.append("ParaClass",getParaClass())
			.append("ParaKey",getParaKey())
			.append("ParaType",getParaType())
			.append("ParaOrder",getParaOrder())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getParaid())
			.append(getIsdefault())
			.append(getParaname())
			.append(getParano())
			.append(getParaClass())
			.append(getParaKey())
			.append(getParaType())
			.append(getParaOrder())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TsysParameter == false) return false;
		if(this == obj) return true;
		TsysParameter other = (TsysParameter)obj;
		return new EqualsBuilder()
			.append(getParaid(),other.getParaid())
			.append(getIsdefault(),other.getIsdefault())
			.append(getParaname(),other.getParaname())
			.append(getParano(),other.getParano())
			.append(getParaClass(),other.getParaClass())
			.append(getParaKey(),other.getParaKey())
			.append(getParaType(),other.getParaType())
			.append(getParaOrder(),other.getParaOrder())
			.isEquals();
	}
}

//uncap_first表示首字母小写；cap_first首字母大写；lower_case将字符串转为小写upper_case将字符串转为大写
