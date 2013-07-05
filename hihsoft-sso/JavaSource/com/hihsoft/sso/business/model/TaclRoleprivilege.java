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

public class TaclRoleprivilege extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "TaclRoleprivilege";
	public static final String ALIAS_RID = "rid";
	public static final String ALIAS_MODULEID = "moduleid";
	public static final String ALIAS_OPERATEID = "operateid";
	public static final String ALIAS_ROLEID = "roleid";
	
	//date formats
	
	//columns START
	private java.lang.String rid;
	private java.lang.String moduleid;
	private java.lang.String operateid;
	private java.lang.String roleid;
	//columns END

	public TaclRoleprivilege(){
	}

	public TaclRoleprivilege(
		java.lang.String rid
	){
		this.rid = rid;
	}

	public void setRid(java.lang.String value) {
		this.rid = value;
	}
	
	public java.lang.String getRid() {
		return this.rid;
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
	public void setRoleid(java.lang.String value) {
		this.roleid = value;
	}
	
	public java.lang.String getRoleid() {
		return this.roleid;
	}

	public String toString() {
		return new ToStringBuilder(this)
			.append("Rid",getRid())
			.append("Moduleid",getModuleid())
			.append("Operateid",getOperateid())
			.append("Roleid",getRoleid())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getRid())
			.append(getModuleid())
			.append(getOperateid())
			.append(getRoleid())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TaclRoleprivilege == false) return false;
		if(this == obj) return true;
		TaclRoleprivilege other = (TaclRoleprivilege)obj;
		return new EqualsBuilder()
			.append(getRid(),other.getRid())
			.append(getModuleid(),other.getModuleid())
			.append(getOperateid(),other.getOperateid())
			.append(getRoleid(),other.getRoleid())
			.isEquals();
	}
}

//uncap_first表示首字母小写；cap_first首字母大写；lower_case将字符串转为小写upper_case将字符串转为大写
