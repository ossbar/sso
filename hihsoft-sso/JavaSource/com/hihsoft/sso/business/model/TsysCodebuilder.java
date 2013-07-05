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

public class TsysCodebuilder extends BaseEntity {

	// alias
	public static final String TABLE_ALIAS = "TsysCodebuilder";
	public static final String ALIAS_CODEID = "codeid";
	public static final String ALIAS_PRI = "pri";
	public static final String ALIAS_TABLENAME = "tablename";
	public static final String ALIAS_FIELDS = "fields";
	public static final String ALIAS_QUERYCONDITION = "querycondition";
	public static final String ALIAS_FILL = "fill";
	public static final String ALIAS_DESCRIPT = "descript";
	public static final String ALIAS_CONTROL = "control";

	// date formats

	// columns START
	private java.lang.String codeid;
	private java.lang.String pri;
	private java.lang.String tablename;
	private java.lang.String fields;
	private java.lang.String querycondition;
	private java.lang.String fill;
	private java.lang.String descript;
	private java.lang.String control;

	// columns END

	public TsysCodebuilder() {
	}

	public TsysCodebuilder(java.lang.String codeid) {
		this.codeid = codeid;
	}

	public void setCodeid(java.lang.String value) {
		this.codeid = value;
	}

	public java.lang.String getCodeid() {
		return this.codeid;
	}

	public void setPri(java.lang.String value) {
		this.pri = value;
	}

	public java.lang.String getPri() {
		return this.pri;
	}

	public void setTablename(java.lang.String value) {
		this.tablename = value;
	}

	public java.lang.String getTablename() {
		return this.tablename;
	}

	public void setFields(java.lang.String value) {
		this.fields = value;
	}

	public java.lang.String getFields() {
		return this.fields;
	}

	public void setQuerycondition(java.lang.String value) {
		this.querycondition = value;
	}

	public java.lang.String getQuerycondition() {
		return this.querycondition;
	}

	public void setFill(java.lang.String value) {
		this.fill = value;
	}

	public java.lang.String getFill() {
		return this.fill;
	}

	public void setDescript(java.lang.String value) {
		this.descript = value;
	}

	public java.lang.String getDescript() {
		return this.descript;
	}

	public void setControl(java.lang.String value) {
		this.control = value;
	}

	public java.lang.String getControl() {
		return this.control;
	}

	public String toString() {
		return new ToStringBuilder(this).append("Codeid", getCodeid())
				.append("Pri", getPri()).append("Tablename", getTablename())
				.append("Fields", getFields())
				.append("Querycondition", getQuerycondition())
				.append("Fill", getFill()).append("Descript", getDescript())
				.append("Control", getControl()).toString();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getCodeid()).append(getPri())
				.append(getTablename()).append(getFields())
				.append(getQuerycondition()).append(getFill())
				.append(getDescript()).append(getControl()).toHashCode();
	}

	public boolean equals(Object obj) {
		if (obj instanceof TsysCodebuilder == false)
			return false;
		if (this == obj)
			return true;
		TsysCodebuilder other = (TsysCodebuilder) obj;
		return new EqualsBuilder().append(getCodeid(), other.getCodeid())
				.append(getPri(), other.getPri())
				.append(getTablename(), other.getTablename())
				.append(getFields(), other.getFields())
				.append(getQuerycondition(), other.getQuerycondition())
				.append(getFill(), other.getFill())
				.append(getDescript(), other.getDescript())
				.append(getControl(), other.getControl()).isEquals();
	}
}

// uncap_first表示首字母小写；cap_first首字母大写；lower_case将字符串转为小写upper_case将字符串转为大写
