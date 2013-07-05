/**
 * Copyright (c) 2013-2015 www.javahih.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.hihframework.osplugins.json;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonBeanProcessor;

/**
 * @author hihsoft.co.,ltd
 *
 */
public class HibernateJsonBeanProcessor implements JsonBeanProcessor {

	public JSONObject processBean(Object arg0, JsonConfig arg1) {
		return new JSONObject();
	}
}
