package com.hihsoft.sso.sysmonitor.syslogs.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hihsoft.baseclass.model.BaseEntity;
import com.hihsoft.sso.sysmonitor.sysaudit.model.AuditableEntity;

/**
 * <p> Title: </p>
 * <p> Description:</p>
 * <p> Copyright: Copyright (c) 2011 </p>
 * <p> Company:javahih.co.,ltd </p>
 *
 * @author zhujw
 * @version 1.0
 */

public class TlogAudit extends BaseEntity {

	// alias
	public static final String TABLE_ALIAS = "TlogAudit";
	public static final String ALIAS_ADUITID = "aduitid";
	public static final String ALIAS_OID = "oid";
	public static final String ALIAS_BEHIND_DATA = "behindData";
	public static final String ALIAS_FRONT_DATA = "frontData";
	public static final String ALIAS_CREATE_USER = "createUser";
	public static final String ALIAS_CREATE_TIME = "createTime";
	public static final String ALIAS_MODIFY_MAN = "modifyMan";
	public static final String ALIAS_MODIFY_TIME = "modifyTime";
	public static final String ALIAS_PROPERTY = "property";
	public static final String ALIAS_OPERATION_TYPE = "operationType";

	// date formats

	// columns START
	private java.lang.String aduitid;
	private java.lang.String oid;
	private java.lang.String behindData;
	private java.lang.String frontData;
	private java.lang.String createUser;
	private java.lang.String createTime;
	private java.lang.String modifyMan;
	private java.lang.String modifyTime;
	private java.lang.String property;
	private java.lang.String operationType;
	private Class entity;

	// columns END

	public TlogAudit() {
	}

	public TlogAudit(java.lang.String aduitid) {
		this.aduitid = aduitid;
	}

	public void setAduitid(java.lang.String value) {
		this.aduitid = value;
	}

	public java.lang.String getAduitid() {
		return this.aduitid;
	}

	public void setOid(java.lang.String value) {
		this.oid = value;
	}

	public java.lang.String getOid() {
		return this.oid;
	}

	public void setBehindData(java.lang.String value) {
		this.behindData = value;
	}

	public java.lang.String getBehindData() {
		return this.behindData;
	}

	public void setFrontData(java.lang.String value) {
		this.frontData = value;
	}

	public java.lang.String getFrontData() {
		return this.frontData;
	}

	public void setCreateUser(java.lang.String value) {
		this.createUser = value;
	}

	public java.lang.String getCreateUser() {
		return this.createUser;
	}

	public void setCreateTime(java.lang.String value) {
		this.createTime = value;
	}

	public java.lang.String getCreateTime() {
		return this.createTime;
	}

	public void setModifyMan(java.lang.String value) {
		this.modifyMan = value;
	}

	public java.lang.String getModifyMan() {
		return this.modifyMan;
	}

	public void setModifyTime(java.lang.String value) {
		this.modifyTime = value;
	}

	public java.lang.String getModifyTime() {
		return this.modifyTime;
	}

	public void setProperty(java.lang.String value) {
		this.property = value;
	}

	public java.lang.String getProperty() {
		return this.property;
	}

	public void setOperationType(java.lang.String value) {
		this.operationType = value;
	}

	public java.lang.String getOperationType() {
		return this.operationType;
	}

	public String toString() {
		return new ToStringBuilder(this).append("Aduitid", getAduitid())
				.append("Oid", getOid()).append("BehindData", getBehindData())
				.append("FrontData", getFrontData())
				.append("CreateUser", getCreateUser())
				.append("CreateTime", getCreateTime())
				.append("ModifyMan", getModifyMan())
				.append("ModifyTime", getModifyTime())
				.append("Property", getProperty())
				.append("OperationType", getOperationType()).toString();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getAduitid()).append(getOid())
				.append(getBehindData()).append(getFrontData())
				.append(getCreateUser()).append(getCreateTime())
				.append(getModifyMan()).append(getModifyTime())
				.append(getProperty()).append(getOperationType()).toHashCode();
	}

	public boolean equals(Object obj) {
		if (obj instanceof TlogAudit == false)
			return false;
		if (this == obj)
			return true;
		TlogAudit other = (TlogAudit) obj;
		return new EqualsBuilder().append(getAduitid(), other.getAduitid())
				.append(getOid(), other.getOid())
				.append(getBehindData(), other.getBehindData())
				.append(getFrontData(), other.getFrontData())
				.append(getCreateUser(), other.getCreateUser())
				.append(getCreateTime(), other.getCreateTime())
				.append(getModifyMan(), other.getModifyMan())
				.append(getModifyTime(), other.getModifyTime())
				.append(getProperty(), other.getProperty())
				.append(getOperationType(), other.getOperationType())
				.isEquals();
	}
}

// uncap_first表示首字母小写；cap_first首字母大写；lower_case将字符串转为小写upper_case将字符串转为大写
