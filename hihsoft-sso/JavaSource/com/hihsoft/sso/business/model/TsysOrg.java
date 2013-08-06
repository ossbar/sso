
/**
 * Copyright (c) 2013-2015 www.javahih.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.hihsoft.sso.business.model;

import com.hihsoft.baseclass.model.BaseEntity;
import com.hihsoft.sso.systempublic.constants.Constant;

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

public class TsysOrg extends BaseEntity {

	private static final long serialVersionUID = -3576372676930106718L;
	// alias
	public static final String TABLE_ALIAS = "TsysOrg";
	public static final String ALIAS_ORGID = "orgid";
	public static final String ALIAS_ORGNO = "orgno";
	public static final String ALIAS_ORGNAME = "orgname";
	public static final String ALIAS_PARENTORGID = "parentorgid";
	public static final String ALIAS_ADDR = "addr";
	public static final String ALIAS_WEBSITE = "website";
	public static final String ALIAS_TELEPHONE = "telephone";
	public static final String ALIAS_FAX = "fax";
	public static final String ALIAS_ORGTYPE = "orgtype";
	public static final String ALIAS_E_MAIL = "email";
	public static final String ALIAS_ORGDESC = "orgdesc";
	public static final String ALIAS_ORGSTATE = "orgstate";
	public static final String ALIAS_ORG_CLASS = "orgClass";
	public static final String ALIAS_ORG_SHORTNAME = "orgShortname";
	public static final String ALIAS_ORG_KIND = "orgKind";
	public static final String ALIAS_CORP_MAN = "corpMan";
	public static final String ALIAS_MOBILE = "mobile";
	public static final String ALIAS_ORG_SORT = "orgSort";

	// date formats

	// columns START
	private java.lang.String orgid;
	private java.lang.String orgno;
	private java.lang.String orgname;
	private java.lang.String parentorgid;
	private java.lang.String addr;
	private java.lang.String website;
	private java.lang.String telephone;
	private java.lang.String fax;
	private java.lang.String orgtype;
	private java.lang.String email;
	private java.lang.String orgdesc;
	private java.lang.String orgstate;
	private java.lang.String orgClass;
	private java.lang.String orgShortname;
	private java.lang.String orgKind;
	private java.lang.String corpMan;
	private java.lang.String mobile;
	private String orgSort;
	private String orgRegion;

	// columns END

	public TsysOrg() {
	}

	public TsysOrg(java.lang.String orgid) {
		this.orgid = orgid;
	}

	public void setOrgid(java.lang.String value) {
		this.orgid = value;
	}

	public java.lang.String getOrgid() {
		return this.orgid;
	}

	public void setOrgno(java.lang.String value) {
		this.orgno = value;
	}

	public java.lang.String getOrgno() {
		return this.orgno;
	}

	public void setOrgname(java.lang.String value) {
		this.orgname = value;
	}

	public java.lang.String getOrgname() {
		return this.orgname;
	}

	public void setParentorgid(java.lang.String value) {
		this.parentorgid = value;
	}

	public java.lang.String getParentorgid() {
		return this.parentorgid;
	}

	public void setAddr(java.lang.String value) {
		this.addr = value;
	}

	public java.lang.String getAddr() {
		return this.addr;
	}

	public void setWebsite(java.lang.String value) {
		this.website = value;
	}

	public java.lang.String getWebsite() {
		return this.website;
	}

	public void setTelephone(java.lang.String value) {
		this.telephone = value;
	}

	public java.lang.String getTelephone() {
		return this.telephone;
	}

	public void setFax(java.lang.String value) {
		this.fax = value;
	}

	public java.lang.String getFax() {
		return this.fax;
	}

	public void setOrgtype(java.lang.String value) {
		this.orgtype = value;
	}

	public java.lang.String getOrgtype() {
		return this.orgtype;
	}

	public void setEmail(java.lang.String value) {
		this.email = value;
	}

	public java.lang.String getEmail() {
		return this.email;
	}

	public void setOrgdesc(java.lang.String value) {
		this.orgdesc = value;
	}

	public java.lang.String getOrgdesc() {
		return this.orgdesc;
	}

	public void setOrgstate(java.lang.String value) {
		this.orgstate = value;
	}

	public java.lang.String getOrgstate() {
		return this.orgstate;
	}

	public void setOrgClass(java.lang.String value) {
		this.orgClass = value;
	}

	public java.lang.String getOrgClass() {
		return this.orgClass;
	}

	public void setOrgShortname(java.lang.String value) {
		this.orgShortname = value;
	}

	public java.lang.String getOrgShortname() {
		return this.orgShortname;
	}

	public void setOrgKind(java.lang.String value) {
		this.orgKind = value;
	}

	public java.lang.String getOrgKind() {
		return this.orgKind;
	}

	public void setCorpMan(java.lang.String value) {
		this.corpMan = value;
	}

	public java.lang.String getCorpMan() {
		return this.corpMan;
	}
	
	public void setOrgSort(String orgSort) {
		this.orgSort = orgSort;
	}
	
	public String getOrgSort() {
		return orgSort;
	}

	private Set<TsysOrg> tsysOrgs = new HashSet<TsysOrg>(0);

	public void setTsysOrgs(Set<TsysOrg> tsysOrg) {
		this.tsysOrgs = tsysOrg;
	}

	public Set<TsysOrg> getTsysOrgs() {
		return tsysOrgs;
	}

	private TsysOrg tsysOrg;

	public void setTsysOrg(TsysOrg tsysOrg) {
		this.tsysOrg = tsysOrg;
	}

	public TsysOrg getTsysOrg() {
		return tsysOrg;
	}
	
	public int childrenSize() {
		int size = tsysOrgs.size();
		for (Object o : tsysOrgs) {
			TsysOrg org = (TsysOrg) o;
			if (Constant.ORG_STATUS_STOPED.equals(org.getOrgstate())) {
				size--;
			}
		}
		return size;
	}

	public String toString() {
		return new ToStringBuilder(this).append("Orgid", getOrgid())
				.append("Orgno", getOrgno()).append("Orgname", getOrgname())
				.append("Parentorgid", getParentorgid())
				.append("Addr", getAddr()).append("Website", getWebsite())
				.append("Telephone", getTelephone()).append("Fax", getFax())
				.append("Orgtype", getOrgtype()).append("Email", getEmail())
				.append("Orgdesc", getOrgdesc())
				.append("Orgstate", getOrgstate())
				.append("OrgClass", getOrgClass())
				.append("OrgShortname", getOrgShortname())
				.append("OrgKind", getOrgKind())
				.append("CorpMan", getCorpMan())
				.append("Mobile", getMobile())
				.toString();
	}

	public java.lang.String getMobile() {
		return mobile;
	}

	public void setMobile(java.lang.String mobile) {
		this.mobile = mobile;
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getOrgid()).append(getOrgno())
				.append(getOrgname()).append(getParentorgid())
				.append(getAddr()).append(getWebsite()).append(getTelephone())
				.append(getFax()).append(getOrgtype()).append(getEmail())
				.append(getOrgdesc()).append(getOrgstate())
				.append(getOrgClass()).append(getOrgShortname())
				.append(getOrgKind()).append(getCorpMan()).append(getMobile())
				.toHashCode();
	}

	public boolean equals(Object obj) {
		if (obj instanceof TsysOrg == false)
			return false;
		if (this == obj)
			return true;
		TsysOrg other = (TsysOrg) obj;
		return new EqualsBuilder().append(getOrgid(), other.getOrgid())
				.append(getOrgno(), other.getOrgno())
				.append(getOrgname(), other.getOrgname())
				.append(getParentorgid(), other.getParentorgid())
				.append(getAddr(), other.getAddr())
				.append(getWebsite(), other.getWebsite())
				.append(getTelephone(), other.getTelephone())
				.append(getFax(), other.getFax())
				.append(getOrgtype(), other.getOrgtype())
				.append(getEmail(), other.getEmail())
				.append(getOrgdesc(), other.getOrgdesc())
				.append(getOrgstate(), other.getOrgstate())
				.append(getOrgClass(), other.getOrgClass())
				.append(getOrgShortname(), other.getOrgShortname())
				.append(getOrgKind(), other.getOrgKind())
				.append(getCorpMan(), other.getCorpMan())
				.append(getMobile(), other.getMobile()).isEquals();

	}

	public String getOrgRegion() {
		return orgRegion;
	}

	public void setOrgRegion(String orgRegion) {
		this.orgRegion = orgRegion;
	}
}

// uncap_first表示首字母小写；cap_first首字母大写；lower_case将字符串转为小写upper_case将字符串转为大写
