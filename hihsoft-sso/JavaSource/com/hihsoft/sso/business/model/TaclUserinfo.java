/**
 * Copyright (c) 2013-2015 www.javahih.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.hihsoft.sso.business.model;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hihframework.core.utils.ParamUtil;
import com.hihsoft.baseclass.model.BaseEntity;
import com.hihsoft.baseclass.model.IUser;
/**
 * <p> Title: </p>
 * <p> Description:</p>
 * <p> Copyright: Copyright (c) 2013 </p>
 * <p> Company:hihsoft.co.,ltd </p>
 *
 * @author zhujw
 * @version 1.0
 */

public class TaclUserinfo extends BaseEntity implements IUser {
	
	//alias
	public static final String TABLE_ALIAS = "TaclUserinfo";
	public static final String ALIAS_USERID = "userid";
	public static final String ALIAS_BIRTHDAY = "birthday";
	public static final String ALIAS_CERTIFIED = "certified";
	public static final String ALIAS_CITY = "city";
	public static final String ALIAS_USER_EMAIL = "userEmail";
	public static final String ALIAS_IDCARD = "idcard";
	public static final String ALIAS_IMAGEPATH = "imagepath";
	public static final String ALIAS_ISONLINE = "isonline";
	public static final String ALIAS_LOGINNAME = "loginname";
	public static final String ALIAS_MOBILE = "mobile";
	public static final String ALIAS_MSN = "msn";
	public static final String ALIAS_NICKNAME = "nickname";
	public static final String ALIAS_PROVINCE = "province";
	public static final String ALIAS_QQ = "qq";
	public static final String ALIAS_SEX = "sex";
	public static final String ALIAS_ORGID = "orgid";
	public static final String ALIAS_DEPTID = "deptid";
	public static final String ALIAS_TELEPHONE = "telephone";
	public static final String ALIAS_USERDESC = "userdesc";
	public static final String ALIAS_USERNAME = "username";
	public static final String ALIAS_USERPW = "userpw";
	public static final String ALIAS_USERSTATE = "userstate";
	public static final String ALIAS_USERTYPE = "usertype";
	public static final String ALIAS_WORK = "work";
	public static final String ALIAS_TRADE = "trade";
	public static final String ALIAS_AGE = "age";
	public static final String ALIAS_USER_NO = "userNo";
	public static final String ALIAS_OFFICE_ADDR = "officeAddr";
	public static final String ALIAS_MODIFY_TIME = "modifyTime";
	public static final String ALIAS_FAX = "fax";
	
	
	//date formats
	
	//columns START
	private java.lang.String userid;
	private java.lang.String birthday;
	private java.lang.String certified;
	private java.lang.String city;
	private java.lang.String userEmail;
	private java.lang.String idcard;
	private java.lang.String imagepath;
	private java.lang.String isonline;
	private java.lang.String loginname;
	private java.lang.String mobile;
	private java.lang.String msn;
	private java.lang.String nickname;
	private java.lang.String province;
	private java.lang.String qq;
	private java.lang.String sex;
	private java.lang.String orgid;
	private java.lang.String deptid;
	private java.lang.String telephone;
	private java.lang.String userdesc;
	private java.lang.String username;
	private java.lang.String userpw;
	private java.lang.String userstate;
	private java.lang.String usertype;
	private java.lang.String work;
	private java.lang.String trade;
	private java.lang.String age;
	private java.lang.String userNo;
	private java.lang.String officeAddr;
	private java.lang.String modifyTime;
	private java.lang.String fax;
	//是否为管理员
	private java.lang.String superadmin;
	//columns END

	public java.lang.String getSuperadmin() {
		return superadmin;
	}

	public void setSuperadmin(java.lang.String superadmin) {
		this.superadmin = superadmin;
	}

	public TaclUserinfo(){
	}

	public TaclUserinfo(
		java.lang.String userid
	){
		this.userid = userid;
	}
	public void setUserNo(java.lang.String value) {
		this.userNo = value;
	}
	
	public java.lang.String getUserNo() {
		return this.userNo;
	}

	public void setUserid(java.lang.String value) {
		this.userid = value;
	}
	
	public java.lang.String getUserid() {
		return this.userid;
	}
	public void setBirthday(java.lang.String value) {
		this.birthday = value;
	}
	
	public java.lang.String getBirthday() {
		return this.birthday;
	}
	public void setCertified(java.lang.String value) {
		this.certified = value;
	}
	
	public java.lang.String getCertified() {
		return this.certified;
	}
	public void setCity(java.lang.String value) {
		this.city = value;
	}
	
	public java.lang.String getCity() {
		return this.city;
	}
	public void setUserEmail(java.lang.String value) {
		this.userEmail = value;
	}
	
	public java.lang.String getUserEmail() {
		return this.userEmail;
	}
	public void setIdcard(java.lang.String value) {
		this.idcard = value;
	}
	
	public java.lang.String getIdcard() {
		return this.idcard;
	}
	public void setImagepath(java.lang.String value) {
		this.imagepath = value;
	}
	
	public java.lang.String getImagepath() {
		return this.imagepath;
	}
	public void setIsonline(java.lang.String value) {
		this.isonline = value;
	}
	
	public java.lang.String getIsonline() {
		return this.isonline;
	}
	public void setLoginname(java.lang.String value) {
		this.loginname = value;
	}
	
	public java.lang.String getLoginname() {
		return this.loginname;
	}
	public void setMobile(java.lang.String value) {
		this.mobile = value;
	}
	
	public java.lang.String getMobile() {
		return this.mobile;
	}
	public void setMsn(java.lang.String value) {
		this.msn = value;
	}
	
	public java.lang.String getMsn() {
		return this.msn;
	}
	public void setNickname(java.lang.String value) {
		this.nickname = value;
	}
	
	public java.lang.String getNickname() {
		return this.nickname;
	}
	public void setProvince(java.lang.String value) {
		this.province = value;
	}
	
	public java.lang.String getProvince() {
		return this.province;
	}
	public void setQq(java.lang.String value) {
		this.qq = value;
	}
	
	public java.lang.String getQq() {
		return this.qq;
	}
	public void setSex(java.lang.String value) {
		this.sex = value;
	}
	
	public java.lang.String getSex() {
		return this.sex;
	}
	public void setOrgid(java.lang.String value) {
		this.orgid = value;
	}
	
	public java.lang.String getOrgid() {
		return this.orgid;
	}
	public void setDeptid(java.lang.String value) {
		this.deptid = value;
	}
	
	public java.lang.String getDeptid() {
		return this.deptid;
	}
	public void setTelephone(java.lang.String value) {
		this.telephone = value;
	}
	
	public java.lang.String getTelephone() {
		return this.telephone;
	}
	public void setUserdesc(java.lang.String value) {
		this.userdesc = value;
	}
	
	public java.lang.String getUserdesc() {
		return this.userdesc;
	}
	public void setUsername(java.lang.String value) {
		this.username = value;
	}
	
	public java.lang.String getUsername() {
		return this.username;
	}
	public void setUserpw(java.lang.String value) {
		this.userpw = value;
	}
	
	public java.lang.String getUserpw() {
		return this.userpw;
	}
	public void setUserstate(java.lang.String value) {
		this.userstate = value;
	}
	
	public java.lang.String getUserstate() {
		return this.userstate;
	}
	public void setUsertype(java.lang.String value) {
		this.usertype = value;
	}
	
	public java.lang.String getUsertype() {
		return this.usertype;
	}
	public void setWork(java.lang.String value) {
		this.work = value;
	}
	
	public java.lang.String getWork() {
		return this.work;
	}
	public void setTrade(java.lang.String value) {
		this.trade = value;
	}
	
	public java.lang.String getTrade() {
		return this.trade;
	}
	public void setAge(java.lang.String value) {
		this.age = value;
	}
	
	public java.lang.String getAge() {
		return this.age;
	}
	
	public java.lang.String getOfficeAddr() {
		return officeAddr;
	}

	public void setOfficeAddr(java.lang.String officeAddr) {
		this.officeAddr = officeAddr;
	}

	public java.lang.String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(java.lang.String modifyTime) {
		this.modifyTime = modifyTime;
	}

	public java.lang.String getFax() {
		return fax;
	}

	public void setFax(java.lang.String fax) {
		this.fax = fax;
	}

	private TsysOrg tsysOrg;
	
	public void setTsysOrg(TsysOrg tsysOrg){
		this.tsysOrg = tsysOrg;
	}
	
	public TsysOrg getTsysOrg() {
		return tsysOrg;
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
			.append("Userid",getUserid())
			.append("Birthday",getBirthday())
			.append("Certified",getCertified())
			.append("City",getCity())
			.append("UserEmail",getUserEmail())
			.append("Idcard",getIdcard())
			.append("Imagepath",getImagepath())
			.append("Isonline",getIsonline())
			.append("Loginname",getLoginname())
			.append("Mobile",getMobile())
			.append("Msn",getMsn())
			.append("Nickname",getNickname())
			.append("Province",getProvince())
			.append("Qq",getQq())
			.append("Sex",getSex())
			.append("Orgid",getOrgid())
			.append("Deptid",getDeptid())
			.append("Telephone",getTelephone())
			.append("Userdesc",getUserdesc())
			.append("Username",getUsername())
			.append("Userpw",getUserpw())
			.append("Userstate",getUserstate())
			.append("Usertype",getUsertype())
			.append("Work",getWork())
			.append("Trade",getTrade())
			.append("Age",getAge())
			.append("UserNo",getUserNo())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getUserid())
			.append(getBirthday())
			.append(getCertified())
			.append(getCity())
			.append(getUserEmail())
			.append(getIdcard())
			.append(getImagepath())
			.append(getIsonline())
			.append(getLoginname())
			.append(getMobile())
			.append(getMsn())
			.append(getNickname())
			.append(getProvince())
			.append(getQq())
			.append(getSex())
			.append(getOrgid())
			.append(getDeptid())
			.append(getTelephone())
			.append(getUserdesc())
			.append(getUsername())
			.append(getUserpw())
			.append(getUserstate())
			.append(getUsertype())
			.append(getWork())
			.append(getTrade())
			.append(getAge())
			.append(getUserNo())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TaclUserinfo == false) return false;
		if(this == obj) return true;
		TaclUserinfo other = (TaclUserinfo)obj;
		return new EqualsBuilder()
			.append(getUserid(),other.getUserid())
			.append(getBirthday(),other.getBirthday())
			.append(getCertified(),other.getCertified())
			.append(getCity(),other.getCity())
			.append(getUserEmail(),other.getUserEmail())
			.append(getIdcard(),other.getIdcard())
			.append(getImagepath(),other.getImagepath())
			.append(getIsonline(),other.getIsonline())
			.append(getLoginname(),other.getLoginname())
			.append(getMobile(),other.getMobile())
			.append(getMsn(),other.getMsn())
			.append(getNickname(),other.getNickname())
			.append(getProvince(),other.getProvince())
			.append(getQq(),other.getQq())
			.append(getSex(),other.getSex())
			.append(getOrgid(),other.getOrgid())
			.append(getDeptid(),other.getDeptid())
			.append(getTelephone(),other.getTelephone())
			.append(getUserdesc(),other.getUserdesc())
			.append(getUsername(),other.getUsername())
			.append(getUserpw(),other.getUserpw())
			.append(getUserstate(),other.getUserstate())
			.append(getUsertype(),other.getUsertype())
			.append(getWork(),other.getWork())
			.append(getTrade(),other.getTrade())
			.append(getAge(),other.getAge())
			.append(getUserNo(),other.getUserNo())
			.isEquals();
	}
	
	public boolean isAdmin() {
		List<TsysParameter> list = ParamUtil.getInstance().getByType("SUPER_USER");
		for (TsysParameter p : list) {
			if (p.getParano().equals(loginname)) return true;
		}
		return false;
	}
}

//uncap_first表示首字母小写；cap_first首字母大写；lower_case将字符串转为小写upper_case将字符串转为大写
