package com.hihsoft.sso.sysmonitor.syslogs.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hihsoft.baseclass.model.BaseEntity;
import com.hihsoft.sso.business.model.TaclUserinfo;
import com.hihsoft.sso.business.model.TsysModuleinfo;
import com.hihsoft.sso.business.model.TsysModuleoperate;
import com.hihsoft.sso.business.model.TsysOrg;

/**
 * <p> Title: </p>
 * <p> Description:</p>
 * <p> Copyright: Copyright (c) 2011 </p>
 * <p> Company:javahih.co.,ltd </p>
 *
 * @author zhujw
 * @version 1.0
 */

public class TlogBusinesslog extends BaseEntity {

	// alias
	public static final String TABLE_ALIAS = "TlogBusinesslog";
	public static final String ALIAS_LOGID = "logid";
	public static final String ALIAS_CREATE_DATE = "createDate";
	public static final String ALIAS_CUSTIP = "custip";
	public static final String ALIAS_MODULEID = "moduleid";
	public static final String ALIAS_OPERATEID = "operateid";
	public static final String ALIAS_ORGID = "orgid";
	public static final String ALIAS_USERID = "userid";

	// date formats

	// columns START
	private java.lang.String logid;
	private java.lang.String createDate;
	private java.lang.String custip;
	private java.lang.String moduleid;
	private java.lang.String operateid;
	private java.lang.String orgid;
	private java.lang.String userid;

	// columns END

	public TlogBusinesslog() {
	}

	public TlogBusinesslog(java.lang.String logid) {
		this.logid = logid;
	}

	public void setLogid(java.lang.String value) {
		this.logid = value;
	}

	public java.lang.String getLogid() {
		return this.logid;
	}

	public void setCreateDate(java.lang.String value) {
		this.createDate = value;
	}

	public java.lang.String getCreateDate() {
		return this.createDate;
	}

	public void setCustip(java.lang.String value) {
		this.custip = value;
	}

	public java.lang.String getCustip() {
		return this.custip;
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

	public void setOrgid(java.lang.String value) {
		this.orgid = value;
	}

	public java.lang.String getOrgid() {
		return this.orgid;
	}

	public void setUserid(java.lang.String value) {
		this.userid = value;
	}

	public java.lang.String getUserid() {
		return this.userid;
	}

	private TsysModuleoperate tsysModuleoperate;

	public void setTsysModuleoperate(TsysModuleoperate tsysModuleoperate) {
		this.tsysModuleoperate = tsysModuleoperate;
	}

	public TsysModuleoperate getTsysModuleoperate() {
		return tsysModuleoperate;
	}

	private TsysModuleinfo tsysModuleinfo;

	public void setTsysModuleinfo(TsysModuleinfo tsysModuleinfo) {
		this.tsysModuleinfo = tsysModuleinfo;
	}

	public TsysModuleinfo getTsysModuleinfo() {
		return tsysModuleinfo;
	}

	private TaclUserinfo taclUserinfo;

	public void setTaclUserinfo(TaclUserinfo taclUserinfo) {
		this.taclUserinfo = taclUserinfo;
	}

	public TaclUserinfo getTaclUserinfo() {
		return taclUserinfo;
	}

	private TsysOrg tsysOrg;

	public void setTsysOrg(TsysOrg tsysOrg) {
		this.tsysOrg = tsysOrg;
	}

	public TsysOrg getTsysOrg() {
		return tsysOrg;
	}

	public String toString() {
		return new ToStringBuilder(this).append("Logid", getLogid())
				.append("CreateDate", getCreateDate())
				.append("Custip", getCustip())
				.append("Moduleid", getModuleid())
				.append("Operateid", getOperateid())
				.append("Orgid", getOrgid()).append("Userid", getUserid())
				.toString();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getLogid()).append(getCreateDate())
				.append(getCustip()).append(getModuleid())
				.append(getOperateid()).append(getOrgid()).append(getUserid())
				.toHashCode();
	}

	public boolean equals(Object obj) {
		if (obj instanceof TlogBusinesslog == false)
			return false;
		if (this == obj)
			return true;
		TlogBusinesslog other = (TlogBusinesslog) obj;
		return new EqualsBuilder().append(getLogid(), other.getLogid())
				.append(getCreateDate(), other.getCreateDate())
				.append(getCustip(), other.getCustip())
				.append(getModuleid(), other.getModuleid())
				.append(getOperateid(), other.getOperateid())
				.append(getOrgid(), other.getOrgid())
				.append(getUserid(), other.getUserid()).isEquals();
	}
}

// uncap_first表示首字母小写；cap_first首字母大写；lower_case将字符串转为小写upper_case将字符串转为大写
