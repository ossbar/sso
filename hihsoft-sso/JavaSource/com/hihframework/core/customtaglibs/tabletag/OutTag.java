/**
 * Copyright (c) 2013-2015 www.javahih.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.hihframework.core.customtaglibs.tabletag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;
import org.springframework.web.servlet.tags.RequestContextAwareTag;

import com.hihframework.core.utils.StringHelpers;

public class OutTag extends RequestContextAwareTag {
	private static final long serialVersionUID = 8423073613167558341L;
	private Object val;
	private String defaultVal;
	
	public String getDefaultVal() {
		return defaultVal;
	}
	public void setDefaultVal(String defaultVal) {
		this.defaultVal = defaultVal;
	}
	public void setVal(Object val) {
		try {
			if (val == null) val = "";
			this.val = ExpressionEvaluatorManager.evaluate("val", val.toString(), Object.class, this, pageContext);
		} catch (JspException e) {
			this.val = "";
		}
	}
	public Object getVal() {
		return val;
	}
	
	@Override
	protected int doStartTagInternal() throws Exception {
		JspWriter out = pageContext.getOut();
		if (StringHelpers.isNull(defaultVal)) defaultVal = "&nbsp;";
		String txt = StringHelpers.isNull(val) ? defaultVal : val.toString();
		out.write(txt);
		return 0;
	}

}
