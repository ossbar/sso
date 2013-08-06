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
 * <p> Copyright: Copyright (c) 2013 </p>
 * <p> Company:hihsoft.co.,ltd </p>
 *
 * @author zhujw
 * @version 1.0
 */

public class TaclDutyuser extends BaseEntity {
	
	private static final long serialVersionUID = -1461477907187419160L;
	private java.lang.String dutyuserid;
	private java.lang.String userid;
	private java.lang.String dutyid;
	//columns END

	public TaclDutyuser(){
	}

	public TaclDutyuser(
		java.lang.String dutyuserid
	){
		this.dutyuserid = dutyuserid;
	}

	public void setDutyuserid(java.lang.String value) {
		this.dutyuserid = value;
	}
	
	public java.lang.String getDutyuserid() {
		return this.dutyuserid;
	}
	public void setUserid(java.lang.String value) {
		this.userid = value;
	}
	
	public java.lang.String getUserid() {
		return this.userid;
	}
	public void setDutyid(java.lang.String value) {
		this.dutyid = value;
	}
	
	public java.lang.String getDutyid() {
		return this.dutyid;
	}
	
	private TaclUserinfo taclUserinfo;
	
	public void setTaclUserinfo(TaclUserinfo taclUserinfo){
		this.taclUserinfo = taclUserinfo;
	}
	
	public TaclUserinfo getTaclUserinfo() {
		return taclUserinfo;
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
			.append("Roleuserid",getDutyuserid())
			.append("Userid",getUserid())
			.append("Roleid",getDutyid())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getDutyuserid())
			.append(getUserid())
			.append(getDutyid())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TaclDutyuser == false) return false;
		if(this == obj) return true;
		TaclDutyuser other = (TaclDutyuser)obj;
		return new EqualsBuilder()
			.append(getDutyuserid(),other.getDutyuserid())
			.append(getUserid(),other.getUserid())
			.append(getDutyid(),other.getDutyid())
			.isEquals();
	}
}

//uncap_first表示首字母小写；cap_first首字母大写；lower_case将字符串转为小写upper_case将字符串转为大写
