
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

public class TaclRoleuser extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "TaclRoleuser";
	public static final String ALIAS_ROLEUSERID = "roleuserid";
	public static final String ALIAS_USERID = "userid";
	public static final String ALIAS_ROLEID = "roleid";
	
	//date formats
	
	//columns START
	private java.lang.String roleuserid;
	private java.lang.String userid;
	private java.lang.String roleid;
	//columns END

	public TaclRoleuser(){
	}

	public TaclRoleuser(
		java.lang.String roleuserid
	){
		this.roleuserid = roleuserid;
	}

	public void setRoleuserid(java.lang.String value) {
		this.roleuserid = value;
	}
	
	public java.lang.String getRoleuserid() {
		return this.roleuserid;
	}
	public void setUserid(java.lang.String value) {
		this.userid = value;
	}
	
	public java.lang.String getUserid() {
		return this.userid;
	}
	public void setRoleid(java.lang.String value) {
		this.roleid = value;
	}
	
	public java.lang.String getRoleid() {
		return this.roleid;
	}
	
	private TaclUserinfo taclUserinfo;
	
	public void setTaclUserinfo(TaclUserinfo taclUserinfo){
		this.taclUserinfo = taclUserinfo;
	}
	
	public TaclUserinfo getTaclUserinfo() {
		return taclUserinfo;
	}
	
	private TaclRole taclRole;
	
	public void setTaclRole(TaclRole taclRole){
		this.taclRole = taclRole;
	}
	
	public TaclRole getTaclRole() {
		return taclRole;
	}

	public String toString() {
		return new ToStringBuilder(this)
			.append("Roleuserid",getRoleuserid())
			.append("Userid",getUserid())
			.append("Roleid",getRoleid())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getRoleuserid())
			.append(getUserid())
			.append(getRoleid())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TaclRoleuser == false) return false;
		if(this == obj) return true;
		TaclRoleuser other = (TaclRoleuser)obj;
		return new EqualsBuilder()
			.append(getRoleuserid(),other.getRoleuserid())
			.append(getUserid(),other.getUserid())
			.append(getRoleid(),other.getRoleid())
			.isEquals();
	}
}

//uncap_first表示首字母小写；cap_first首字母大写；lower_case将字符串转为小写upper_case将字符串转为大写
