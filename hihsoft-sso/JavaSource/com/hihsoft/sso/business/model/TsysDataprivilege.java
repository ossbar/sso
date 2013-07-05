
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

public class TsysDataprivilege extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "TsysDataprivilege";
	public static final String ALIAS_DATAID = "dataid";
	public static final String ALIAS_ROLEID = "userid";
	public static final String ALIAS_ORGID = "orgid";
	
	//date formats
	
	//columns START
	private java.lang.String dataid;
	private java.lang.String userid;
	private java.lang.String orgid;
	//columns END

	public TsysDataprivilege(){
	}

	public TsysDataprivilege(
		java.lang.String dataid
	){
		this.dataid = dataid;
	}

	public void setDataid(java.lang.String value) {
		this.dataid = value;
	}
	
	public java.lang.String getDataid() {
		return this.dataid;
	}
	public java.lang.String getUserid() {
		return userid;
	}

	public void setUserid(java.lang.String userid) {
		this.userid = userid;
	}
	public void setOrgid(java.lang.String value) {
		this.orgid = value;
	}
	
	public java.lang.String getOrgid() {
		return this.orgid;
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
		return new ToStringBuilder(this)
			.append("Dataid",getDataid())
			.append("Roleid",getUserid())
			.append("Orgid",getOrgid())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getDataid())
			.append(getUserid())
			.append(getOrgid())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TsysDataprivilege == false) return false;
		if(this == obj) return true;
		TsysDataprivilege other = (TsysDataprivilege)obj;
		return new EqualsBuilder()
			.append(getDataid(),other.getDataid())
			.append(getUserid(),other.getUserid())
			.append(getOrgid(),other.getOrgid())
			.isEquals();
	}
}

//uncap_first表示首字母小写；cap_first首字母大写；lower_case将字符串转为小写upper_case将字符串转为大写
