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

public class TsysDuty extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "TsysDuty";
	public static final String ALIAS_DUTYID = "dutyid";
	public static final String ALIAS_DUTYNAME = "dutyname";
	public static final String ALIAS_REMARK = "remark";
	public static final String ALIAS_PARENT_DUTYID = "parentDutyid";
	public static final String ALIAS_DUTY_TYPE = "dutyType";
	public static final String ALIAS_DUTY_SORY = "dutySort";
	
	//date formats
	
	//columns START
	private java.lang.String dutyid;
	private java.lang.String dutyname;
	private java.lang.String remark;
	private java.lang.String parentDutyid;
	private java.lang.String dutyType;
	private java.lang.String dutySort;
	//columns END

	public TsysDuty(){
	}

	public java.lang.String getDutyType() {
		return dutyType;
	}

	public void setDutyType(java.lang.String dutyType) {
		this.dutyType = dutyType;
	}

	public java.lang.String getDutySort() {
		return dutySort;
	}

	public void setDutySort(java.lang.String dutySort) {
		this.dutySort = dutySort;
	}

	public TsysDuty(
		java.lang.String dutyid
	){
		this.dutyid = dutyid;
	}

	public void setDutyid(java.lang.String value) {
		this.dutyid = value;
	}
	
	public java.lang.String getDutyid() {
		return this.dutyid;
	}
	public void setDutyname(java.lang.String value) {
		this.dutyname = value;
	}
	
	public java.lang.String getDutyname() {
		return this.dutyname;
	}
	public void setRemark(java.lang.String value) {
		this.remark = value;
	}
	
	public java.lang.String getRemark() {
		return this.remark;
	}
	public void setParentDutyid(java.lang.String value) {
		this.parentDutyid = value;
	}
	
	public java.lang.String getParentDutyid() {
		return this.parentDutyid;
	}
	
	private Set taclUserinfos = new HashSet(0);
	public void setTaclUserinfos(Set taclUserinfo){
		this.taclUserinfos = taclUserinfo;
	}
	
	public Set getTaclUserinfos() {
		return taclUserinfos;
	}
	
	private Set tsysDutys = new HashSet(0);
	public void setTsysDutys(Set tsysDuty){
		this.tsysDutys = tsysDuty;
	}
	
	public Set getTsysDutys() {
		return tsysDutys;
	}
	
	private TsysDuty tsysDuty;
	
	public void setTsysDuty(TsysDuty tsysDuty){
		this.tsysDuty = tsysDuty;
	}
	
	public TsysDuty getTsysDuty() {
		return tsysDuty;
	}

	public String toString() {
		return new ToStringBuilder(this)
			.append("Dutyid",getDutyid())
			.append("Dutyname",getDutyname())
			.append("Remark",getRemark())
			.append("ParentDutyid",getParentDutyid())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getDutyid())
			.append(getDutyname())
			.append(getRemark())
			.append(getParentDutyid())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TsysDuty == false) return false;
		if(this == obj) return true;
		TsysDuty other = (TsysDuty)obj;
		return new EqualsBuilder()
			.append(getDutyid(),other.getDutyid())
			.append(getDutyname(),other.getDutyname())
			.append(getRemark(),other.getRemark())
			.append(getParentDutyid(),other.getParentDutyid())
			.isEquals();
	}
}

//uncap_first表示首字母小写；cap_first首字母大写；lower_case将字符串转为小写upper_case将字符串转为大写
