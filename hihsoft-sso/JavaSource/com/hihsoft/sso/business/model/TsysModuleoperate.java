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

public class TsysModuleoperate extends BaseEntity {
	
	private static final long serialVersionUID = -5807201266230537336L;
	//alias
	public static final String TABLE_ALIAS = "TsysModuleoperate";
	public static final String ALIAS_OPERATEID = "operateid";
	public static final String ALIAS_MODULEID = "moduleid";
	public static final String ALIAS_OPERATENAME = "operatename";
	public static final String ALIAS_OPERATENO = "operateno";
	
	
	//date formats
	
	//columns START
	private java.lang.String operateid;
	private java.lang.String moduleid;
	private java.lang.String operatename;
	private java.lang.String operateno;
	private String operateonlycode;
	//columns END

	public String getOperateonlycode() {
		return operateonlycode;
	}

	public void setOperateonlycode(String operateonlycode) {
		this.operateonlycode = operateonlycode;
	}

	public TsysModuleoperate(){
	}

	public TsysModuleoperate(
		java.lang.String operateid
	){
		this.operateid = operateid;
	}

	public void setOperateid(java.lang.String value) {
		this.operateid = value;
	}
	
	public java.lang.String getOperateid() {
		return this.operateid;
	}
	public void setModuleid(java.lang.String value) {
		this.moduleid = value;
	}
	
	public java.lang.String getModuleid() {
		return this.moduleid;
	}
	public void setOperatename(java.lang.String value) {
		this.operatename = value;
	}
	
	public java.lang.String getOperatename() {
		return this.operatename;
	}
	public void setOperateno(java.lang.String value) {
		this.operateno = value;
	}
	
	public java.lang.String getOperateno() {
		return this.operateno;
	}

	private TsysModuleinfo tsysModuleinfo;
	
	public void setTsysModuleinfo(TsysModuleinfo tsysModuleinfo){
		this.tsysModuleinfo = tsysModuleinfo;
	}
	
	public TsysModuleinfo getTsysModuleinfo() {
		return tsysModuleinfo;
	}

	public String toString() {
		return new ToStringBuilder(this)
			.append("Operateid",getOperateid())
			.append("Moduleid",getModuleid())
			.append("Operatename",getOperatename())
			.append("Operateno",getOperateno())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getOperateid())
			.append(getModuleid())
			.append(getOperatename())
			.append(getOperateno())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TsysModuleoperate == false) return false;
		if(this == obj) return true;
		TsysModuleoperate other = (TsysModuleoperate)obj;
		return new EqualsBuilder()
			.append(getOperateid(),other.getOperateid())
			.append(getModuleid(),other.getModuleid())
			.append(getOperatename(),other.getOperatename())
			.append(getOperateno(),other.getOperateno())
			.isEquals();
	}
}

//uncap_first表示首字母小写；cap_first首字母大写；lower_case将字符串转为小写upper_case将字符串转为大写
