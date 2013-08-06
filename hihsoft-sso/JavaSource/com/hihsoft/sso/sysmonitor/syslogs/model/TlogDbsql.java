/**
 * Copyright (c) 2013-2015 www.javahih.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.hihsoft.sso.sysmonitor.syslogs.model;
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

public class TlogDbsql extends BaseEntity {
	
	private static final long serialVersionUID = 5369926891287444693L;
	//alias
	public static final String TABLE_ALIAS = "TlogDbsql";
	public static final String ALIAS_DATABASE_OID = "databaseOid";
	public static final String ALIAS_TABLE_NAME = "tableName";
	public static final String ALIAS_TABLE_OPERATE = "tableOperate";
	public static final String ALIAS_TABLE_ACCESS_TIMES = "tableAccessTimes";
	public static final String ALIAS_TABLE_ACCESS_LAST_TIME = "tableAccessLastTime";
	public static final String ALIAS_COLUMES = "columes";
	public static final String ALIAS_USERID = "userid";
	
	//date formats
	
	//columns START
	private java.lang.String databaseOid;
	private java.lang.String tableName;
	private java.lang.String tableOperate;
	private java.lang.String tableAccessTimes;
	private java.lang.String tableAccessLastTime;
	private java.lang.String columes;
	private java.lang.String userid;
	//columns END

	public TlogDbsql(){
	}

	public TlogDbsql(
		java.lang.String databaseOid
	){
		this.databaseOid = databaseOid;
	}

	public void setDatabaseOid(java.lang.String value) {
		this.databaseOid = value;
	}
	
	public java.lang.String getDatabaseOid() {
		return this.databaseOid;
	}
	public void setTableName(java.lang.String value) {
		this.tableName = value;
	}
	
	public java.lang.String getTableName() {
		return this.tableName;
	}
	public void setTableOperate(java.lang.String value) {
		this.tableOperate = value;
	}
	
	public java.lang.String getTableOperate() {
		return this.tableOperate;
	}
	public void setTableAccessTimes(java.lang.String value) {
		this.tableAccessTimes = value;
	}
	
	public java.lang.String getTableAccessTimes() {
		return this.tableAccessTimes;
	}
	public void setTableAccessLastTime(java.lang.String value) {
		this.tableAccessLastTime = value;
	}
	
	public java.lang.String getTableAccessLastTime() {
		return this.tableAccessLastTime;
	}
	public void setColumes(java.lang.String value) {
		this.columes = value;
	}
	
	public java.lang.String getColumes() {
		return this.columes;
	}
	public void setUserid(java.lang.String value) {
		this.userid = value;
	}
	
	public java.lang.String getUserid() {
		return this.userid;
	}

	public String toString() {
		return new ToStringBuilder(this)
			.append("DatabaseOid",getDatabaseOid())
			.append("TableName",getTableName())
			.append("TableOperate",getTableOperate())
			.append("TableAccessTimes",getTableAccessTimes())
			.append("TableAccessLastTime",getTableAccessLastTime())
			.append("Columes",getColumes())
			.append("Userid",getUserid())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getDatabaseOid())
			.append(getTableName())
			.append(getTableOperate())
			.append(getTableAccessTimes())
			.append(getTableAccessLastTime())
			.append(getColumes())
			.append(getUserid())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TlogDbsql == false) return false;
		if(this == obj) return true;
		TlogDbsql other = (TlogDbsql)obj;
		return new EqualsBuilder()
			.append(getDatabaseOid(),other.getDatabaseOid())
			.append(getTableName(),other.getTableName())
			.append(getTableOperate(),other.getTableOperate())
			.append(getTableAccessTimes(),other.getTableAccessTimes())
			.append(getTableAccessLastTime(),other.getTableAccessLastTime())
			.append(getColumes(),other.getColumes())
			.append(getUserid(),other.getUserid())
			.isEquals();
	}
}

//uncap_first表示首字母小写；cap_first首字母大写；lower_case将字符串转为小写upper_case将字符串转为大写
