
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

public class TaclRole extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "TaclRole";
	public static final String ALIAS_ROLEID = "roleid";
	public static final String ALIAS_REMARK = "remark";
	public static final String ALIAS_ROLENAME = "rolename";
	public static final String ALIAS_ROLETYPE = "roletype";
	public static final String ALIAS_ORGID = "orgid";
	public static final String ALIAS_ROLE_SORT = "roleSort";
	
	//date formats
	
	//columns START
	private java.lang.String roleid;
	private java.lang.String remark;
	private java.lang.String rolename;
	private java.lang.String roletype;
	private java.lang.String orgid;
	private java.lang.String roleSort;
	//columns END

	public TaclRole(){
	}

	public TaclRole(
		java.lang.String roleid
	){
		this.roleid = roleid;
	}

	public void setRoleid(java.lang.String value) {
		this.roleid = value;
	}
	
	public java.lang.String getRoleid() {
		return this.roleid;
	}
	public void setRemark(java.lang.String value) {
		this.remark = value;
	}
	
	public java.lang.String getRemark() {
		return this.remark;
	}
	public void setRolename(java.lang.String value) {
		this.rolename = value;
	}
	
	public java.lang.String getRolename() {
		return this.rolename;
	}
	public void setRoletype(java.lang.String value) {
		this.roletype = value;
	}
	
	public java.lang.String getRoletype() {
		return this.roletype;
	}
	public void setOrgid(java.lang.String value) {
		this.orgid = value;
	}
	
	public java.lang.String getOrgid() {
		return this.orgid;
	}
	public void setRoleSort(java.lang.String value) {
		this.roleSort = value;
	}
	
	public java.lang.String getRoleSort() {
		return this.roleSort;
	}
	
	private Set taclRoleusers = new HashSet(0);
	public void setTaclRoleusers(Set taclRoleuser){
		this.taclRoleusers = taclRoleuser;
	}
	
	public Set getTaclRoleusers() {
		return taclRoleusers;
	}
	
	private Set tsysDataprivileges = new HashSet(0);
	public void setTsysDataprivileges(Set tsysDataprivilege){
		this.tsysDataprivileges = tsysDataprivilege;
	}
	
	public Set getTsysDataprivileges() {
		return tsysDataprivileges;
	}
	
	private TsysOrg tsysOrg;
	
	public void setTsysOrg(TsysOrg tsysOrg){
		this.tsysOrg = tsysOrg;
	}
	
	public TsysOrg getTsysOrg() {
		return tsysOrg;
	}

	public String toString() {
		return new ToStringBuilder(this)
			.append("Roleid",getRoleid())
			.append("Remark",getRemark())
			.append("Rolename",getRolename())
			.append("Roletype",getRoletype())
			.append("Orgid",getOrgid())
			.append("RoleSort",getRoleSort())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getRoleid())
			.append(getRemark())
			.append(getRolename())
			.append(getRoletype())
			.append(getOrgid())
			.append(getRoleSort())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TaclRole == false) return false;
		if(this == obj) return true;
		TaclRole other = (TaclRole)obj;
		return new EqualsBuilder()
			.append(getRoleid(),other.getRoleid())
			.append(getRemark(),other.getRemark())
			.append(getRolename(),other.getRolename())
			.append(getRoletype(),other.getRoletype())
			.append(getOrgid(),other.getOrgid())
			.append(getRoleSort(),other.getRoleSort())
			.isEquals();
	}
}

//uncap_first表示首字母小写；cap_first首字母大写；lower_case将字符串转为小写upper_case将字符串转为大写
