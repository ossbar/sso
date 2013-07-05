package com.hihsoft.sso.business.model;

import com.hihsoft.baseclass.model.BaseEntity;
import com.hihsoft.sso.sysmonitor.sysaudit.model.BaseAuditEntity;

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

public class TsysFlat extends BaseEntity {

	// alias
	public static final String TABLE_ALIAS = "TsysFlat";
	public static final String ALIAS_FLATID = "flatid";
	public static final String ALIAS_FLATCODE = "flatcode";
	public static final String ALIAS_REMARK = "remark";
	public static final String ALIAS_FLATURL = "flaturl";
	public static final String ALIAS_SHORTNAME = "shortname";
	public static final String ALIAS_FLATDESC = "flatdesc";
	public static final String ALIAS_FLATNAME = "flatname";

	// date formats

	// columns START
	private java.lang.String flatid;
	private java.lang.String flatcode;
	private java.lang.String remark;
	private java.lang.String flaturl;
	private java.lang.String shortname;
	private java.lang.Integer flatdesc;
	private java.lang.String flatname;

	// columns END

	public TsysFlat() {
	}

	public TsysFlat(java.lang.String flatid) {
		this.flatid = flatid;
	}

	public void setFlatid(java.lang.String value) {
		this.flatid = value;
	}

	public java.lang.String getFlatid() {
		return this.flatid;
	}

	public void setFlatcode(java.lang.String value) {
		this.flatcode = value;
	}

	public java.lang.String getFlatcode() {
		return this.flatcode;
	}

	public void setRemark(java.lang.String value) {
		this.remark = value;
	}

	public java.lang.String getRemark() {
		return this.remark;
	}

	public void setFlaturl(java.lang.String value) {
		this.flaturl = value;
	}

	public java.lang.String getFlaturl() {
		return this.flaturl;
	}

	public void setShortname(java.lang.String value) {
		this.shortname = value;
	}

	public java.lang.String getShortname() {
		return this.shortname;
	}

	public void setFlatdesc(java.lang.Integer value) {
		this.flatdesc = value;
	}

	public java.lang.Integer getFlatdesc() {
		return this.flatdesc;
	}

	public void setFlatname(java.lang.String value) {
		this.flatname = value;
	}

	public java.lang.String getFlatname() {
		return this.flatname;
	}

	private Set tsysModuleinfos = new HashSet(0);

	public void setTsysModuleinfos(Set tsysModuleinfo) {
		this.tsysModuleinfos = tsysModuleinfo;
	}

	public Set getTsysModuleinfos() {
		return tsysModuleinfos;
	}

	public String toString() {
		return new ToStringBuilder(this).append("Flatid", getFlatid())
				.append("Flatcode", getFlatcode())
				.append("Remark", getRemark()).append("Flaturl", getFlaturl())
				.append("Shortname", getShortname())
				.append("Flatdesc", getFlatdesc())
				.append("Flatname", getFlatname()).toString();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getFlatid()).append(getFlatcode())
				.append(getRemark()).append(getFlaturl())
				.append(getShortname()).append(getFlatdesc())
				.append(getFlatname()).toHashCode();
	}

	public boolean equals(Object obj) {
		if (obj instanceof TsysFlat == false)
			return false;
		if (this == obj)
			return true;
		TsysFlat other = (TsysFlat) obj;
		return new EqualsBuilder().append(getFlatid(), other.getFlatid())
				.append(getFlatcode(), other.getFlatcode())
				.append(getRemark(), other.getRemark())
				.append(getFlaturl(), other.getFlaturl())
				.append(getShortname(), other.getShortname())
				.append(getFlatdesc(), other.getFlatdesc())
				.append(getFlatname(), other.getFlatname()).isEquals();
	}
}

// uncap_first表示首字母小写；cap_first首字母大写；lower_case将字符串转为小写upper_case将字符串转为大写
