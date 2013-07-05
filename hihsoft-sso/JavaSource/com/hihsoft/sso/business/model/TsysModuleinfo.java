/**
 * Copyright (c) 2013-2015 www.javahih.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
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

public class TsysModuleinfo extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "TsysModuleinfo";
	public static final String ALIAS_MODULEID = "moduleid";
	public static final String ALIAS_FLATID = "flatid";
	public static final String ALIAS_LINKEDADDR = "linkedaddr";
	public static final String ALIAS_MODULECLASS = "moduleclass";
	public static final String ALIAS_MODULEDESC = "moduledesc";
	public static final String ALIAS_MODULEICON = "moduleicon";
	public static final String ALIAS_MODULENAME = "modulename";
	public static final String ALIAS_MODULENO = "moduleno";
	public static final String ALIAS_PARENTMODULEID = "parentmoduleid";
	public static final String ALIAS_SORTNUM = "sortnum";
	public static final String ALIAS_LEAF = "leaf";
	public static final String ALIAS_TABSORT = "tabsort";
	
	//date formats
	
	//columns START
	private String moduleid;
	private String flatid;
	private String linkedaddr;
	private String moduleclass;
	private String moduledesc;
	private String moduleicon;
	private String modulename;
	private String moduleno;
	private String parentmoduleid;
	private String sortnum;
	private String leaf;
	private String tabsort;
	private String modulearea;
	//columns END

	public TsysModuleinfo(){
	}

	public TsysModuleinfo(
		String moduleid
	){
		this.moduleid = moduleid;
	}

	public void setModuleid(String value) {
		this.moduleid = value;
	}
	
	public String getModuleid() {
		return this.moduleid;
	}
	public void setFlatid(String value) {
		this.flatid = value;
	}
	
	public String getFlatid() {
		return this.flatid;
	}
	public void setLinkedaddr(String value) {
		this.linkedaddr = value;
	}
	
	public String getLinkedaddr() {
		return this.linkedaddr;
	}
	public void setModuleclass(String value) {
		this.moduleclass = value;
	}
	
	public String getModuleclass() {
		return this.moduleclass;
	}
	public void setModuledesc(String value) {
		this.moduledesc = value;
	}
	
	public String getModuledesc() {
		return this.moduledesc;
	}
	public void setModuleicon(String value) {
		this.moduleicon = value;
	}
	
	public String getModuleicon() {
		return this.moduleicon;
	}
	public void setModulename(String value) {
		this.modulename = value;
	}
	
	public String getModulename() {
		return this.modulename;
	}
	public void setModuleno(String value) {
		this.moduleno = value;
	}
	
	public String getModuleno() {
		return this.moduleno;
	}
	public void setParentmoduleid(String value) {
		this.parentmoduleid = value;
	}
	
	public String getParentmoduleid() {
		return this.parentmoduleid;
	}
	public void setSortnum(String value) {
		this.sortnum = value;
	}
	
	public String getSortnum() {
		return this.sortnum;
	}
	public void setLeaf(String value) {
		this.leaf = value;
	}
	
	public String getLeaf() {
		return this.leaf;
	}
	public void setTabsort(String value) {
		this.tabsort = value;
	}
	
	public String getTabsort() {
		return this.tabsort;
	}
	
	public void setModulearea(String modulearea) {
		this.modulearea = modulearea;
	}
	public String getModulearea() {
		return modulearea;
	}
	
	private Set tsysModuleoperates = new HashSet(0);
	public void setTsysModuleoperates(Set tsysModuleoperate){
		this.tsysModuleoperates = tsysModuleoperate;
	}
	
	public Set getTsysModuleoperates() {
		return tsysModuleoperates;
	}
	
	private Set tsysModuleinfos = new HashSet(0);
	public void setTsysModuleinfos(Set tsysModuleinfo){
		this.tsysModuleinfos = tsysModuleinfo;
	}
	
	public Set getTsysModuleinfos() {
		return tsysModuleinfos;
	}
	
	private TsysModuleinfo tsysModuleinfo;
	
	public void setTsysModuleinfo(TsysModuleinfo tsysModuleinfo){
		this.tsysModuleinfo = tsysModuleinfo;
	}
	
	public TsysModuleinfo getTsysModuleinfo() {
		return tsysModuleinfo;
	}
	
	private TsysFlat tsysFlat;
	
	public void setTsysFlat(TsysFlat tsysFlat){
		this.tsysFlat = tsysFlat;
	}
	
	public TsysFlat getTsysFlat() {
		return tsysFlat;
	}

	public String toString() {
		return new ToStringBuilder(this)
			.append("Moduleid",getModuleid())
			.append("Flatid",getFlatid())
			.append("Linkedaddr",getLinkedaddr())
			.append("Moduleclass",getModuleclass())
			.append("Moduledesc",getModuledesc())
			.append("Moduleicon",getModuleicon())
			.append("Modulename",getModulename())
			.append("Moduleno",getModuleno())
			.append("Parentmoduleid",getParentmoduleid())
			.append("Sortnum",getSortnum())
			.append("Leaf",getLeaf())
			.append("Tabsort",getTabsort())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getModuleid())
			.append(getFlatid())
			.append(getLinkedaddr())
			.append(getModuleclass())
			.append(getModuledesc())
			.append(getModuleicon())
			.append(getModulename())
			.append(getModuleno())
			.append(getParentmoduleid())
			.append(getSortnum())
			.append(getLeaf())
			.append(getTabsort())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TsysModuleinfo == false) return false;
		if(this == obj) return true;
		TsysModuleinfo other = (TsysModuleinfo)obj;
		return new EqualsBuilder()
			.append(getModuleid(),other.getModuleid())
			.append(getFlatid(),other.getFlatid())
			.append(getLinkedaddr(),other.getLinkedaddr())
			.append(getModuleclass(),other.getModuleclass())
			.append(getModuledesc(),other.getModuledesc())
			.append(getModuleicon(),other.getModuleicon())
			.append(getModulename(),other.getModulename())
			.append(getModuleno(),other.getModuleno())
			.append(getParentmoduleid(),other.getParentmoduleid())
			.append(getSortnum(),other.getSortnum())
			.append(getLeaf(),other.getLeaf())
			.append(getTabsort(),other.getTabsort())
			.isEquals();
	}
}

//uncap_first表示首字母小写；cap_first首字母大写；lower_case将字符串转为小写upper_case将字符串转为大写
