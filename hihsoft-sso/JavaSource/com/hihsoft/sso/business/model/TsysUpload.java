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

public class TsysUpload extends BaseEntity {
	
	private static final long serialVersionUID = -4485738375272593852L;
	//alias
	public static final String TABLE_ALIAS = "TsysUpload";
	public static final String ALIAS_UPLOADID = "uploadid";
	public static final String ALIAS_CREATE_MAN = "createMan";
	public static final String ALIAS_CREATE_TIME = "createTime";
	public static final String ALIAS_FILE_URL = "fileUrl";
	public static final String ALIAS_FILE_NAME = "fileName";
	public static final String ALIAS_FLATID = "flatid";
	public static final String ALIAS_REMARK = "remark";
	public static final String ALIAS_SECOND_NAME = "secondName";
	
	//date formats
	
	//columns START
	private java.lang.String uploadid;
	private java.lang.String createMan;
	private java.lang.String createTime;
	private java.lang.String fileUrl;
	private java.lang.String fileName;
	private java.lang.String flatid;
	private java.lang.String remark;
	private java.lang.String secondName;
	//columns END

	public TsysUpload(){
	}

	public TsysUpload(
		java.lang.String uploadid
	){
		this.uploadid = uploadid;
	}

	public void setUploadid(java.lang.String value) {
		this.uploadid = value;
	}
	
	public java.lang.String getUploadid() {
		return this.uploadid;
	}
	public void setCreateMan(java.lang.String value) {
		this.createMan = value;
	}
	
	public java.lang.String getCreateMan() {
		return this.createMan;
	}
	public void setCreateTime(java.lang.String value) {
		this.createTime = value;
	}
	
	public java.lang.String getCreateTime() {
		return this.createTime;
	}
	public void setFileUrl(java.lang.String value) {
		this.fileUrl = value;
	}
	
	public java.lang.String getFileUrl() {
		return this.fileUrl;
	}
	public void setFileName(java.lang.String value) {
		this.fileName = value;
	}
	
	public java.lang.String getFileName() {
		return this.fileName;
	}
	public void setFlatid(java.lang.String value) {
		this.flatid = value;
	}
	
	public java.lang.String getFlatid() {
		return this.flatid;
	}
	public void setRemark(java.lang.String value) {
		this.remark = value;
	}
	
	public java.lang.String getRemark() {
		return this.remark;
	}
	public void setSecondName(java.lang.String value) {
		this.secondName = value;
	}
	
	public java.lang.String getSecondName() {
		return this.secondName;
	}

	public String toString() {
		return new ToStringBuilder(this)
			.append("Uploadid",getUploadid())
			.append("CreateMan",getCreateMan())
			.append("CreateTime",getCreateTime())
			.append("FileUrl",getFileUrl())
			.append("FileName",getFileName())
			.append("Flatid",getFlatid())
			.append("Remark",getRemark())
			.append("SecondName",getSecondName())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getUploadid())
			.append(getCreateMan())
			.append(getCreateTime())
			.append(getFileUrl())
			.append(getFileName())
			.append(getFlatid())
			.append(getRemark())
			.append(getSecondName())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TsysUpload == false) return false;
		if(this == obj) return true;
		TsysUpload other = (TsysUpload)obj;
		return new EqualsBuilder()
			.append(getUploadid(),other.getUploadid())
			.append(getCreateMan(),other.getCreateMan())
			.append(getCreateTime(),other.getCreateTime())
			.append(getFileUrl(),other.getFileUrl())
			.append(getFileName(),other.getFileName())
			.append(getFlatid(),other.getFlatid())
			.append(getRemark(),other.getRemark())
			.append(getSecondName(),other.getSecondName())
			.isEquals();
	}
}

//uncap_first表示首字母小写；cap_first首字母大写；lower_case将字符串转为小写upper_case将字符串转为大写
