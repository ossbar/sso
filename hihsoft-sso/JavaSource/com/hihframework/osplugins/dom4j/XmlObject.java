/**
 * Copyright (c) 2013-2015 www.javahih.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.hihframework.osplugins.dom4j;

import java.util.Map;

import com.hihsoft.baseclass.model.BaseEntity;

/**
 * <p> Title:初始化表结构数据所需的域对象</p>
 * <p> Description:</p>
 * <p> Copyright: Copyright (c) 2013 </p>
 * <p> Company:hihsoft.co.,ltd </p>
 *  @author hihsoft.co.,ltd
 * @version 1.0
 */
public class XmlObject extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String model;// 对应MODEL中的的class属性

	private String object;// 对应object中的id属性

	private String isload;// 是否保存至数据库;

	private Map<String, Object> propertys;// 对象XmlProperty的集合

	private boolean mapping = false;// 此对象是否以成功映射

	/**
	 * @return the object
	 */
	public String getObject() {
		return object;
	}

	/**
	 * @param object the object to set
	 */
	public void setObject(String object) {
		this.object = object;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * @return the propertys
	 */
	public Map<String, Object> getPropertys() {
		return propertys;
	}

	/**
	 * @param propertys the propertys to set
	 */
	public void setPropertys(Map<String, Object> propertys) {
		this.propertys = propertys;
	}

	/**
	 * @return the mapping
	 */
	public boolean isMapping() {
		return mapping;
	}

	/**
	 * @param mapping the mapping to set
	 */
	public void setMapping(boolean mapping) {
		this.mapping = mapping;
	}

	/**
	 * @return the isload
	 */
	public String getIsload() {
		return isload;
	}

	/**
	 * @param isload the isload to set
	 */
	public void setIsload(String isload) {
		this.isload = isload;
	}
}
