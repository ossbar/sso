/**
 * Copyright (c) 2013-2015 www.javahih.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.hihframework.core.customtaglibs.tabletag;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.log4j.Logger;

import com.hihframework.core.utils.ReflectUtil;
import com.hihframework.core.utils.StringHelpers;
import com.hihframework.osplugins.properties.PropertiesUtil;

public class ColumnTag extends BodyTagSupport {
	private static final long serialVersionUID = 1093543022777340443L;
	
	Logger logger = Logger.getLogger(ColumnTag.class);
	
	private String header;
	private String field;
	private String width;
	private String height;
	private String style;
	private String cssName;
	private String checkbox;
	private String sort;
	private String type;
	private String format;
	private String converter;
	
	private static DecimalFormat numfmt = new DecimalFormat();
	private static SimpleDateFormat datefmt = new SimpleDateFormat();
	
	@Override
	public int doEndTag() throws JspException {
		try {
			TableTag p = (TableTag) getParent();
			String content = getBodyContent() == null ? null : getBodyContent().getString();
			Object val = null;
			if (StringHelpers.notNull(content)) {
				val = content;
			} else {
				Object bean = pageContext.getAttribute(p.getVar());
				if (bean != null) {
					val = getFieldValue(bean, field);
					val = format(type, format, val);
				} else {
					val = "";
				}
			}
			if (StringHelpers.isNull(val)) val = "&nbsp;";
			StringBuffer buf = p.getBuffer();
			if (p.isHeaderRendered()) {
				if ("true".equalsIgnoreCase(checkbox)) {
					buf.append("\t\t<td width=\"100\" class=\"checkbox\">");
					buf.append("<input type=\"checkbox\" name=\"ckh\" value=\""+val+"\" /></td>\n");
				} else {
					buf.append("\t\t<td width=\""+width+"\">" + val + "</td>\n");
				}
			} else {
				p.addColumn();
				if ("true".equalsIgnoreCase(checkbox)) {
					buf.append("\t\t\t<th field=\"" + field + "\" class=\"checkbox\"><input type=\"checkbox\" id=\"selectAll\" name=\""+field+"\"/></th>\n");
				} else {
					buf.append("\t\t\t<th field=\"" + field + "\" sortname=\"" + field + "\" width=\""+width+"\">" + (header+"&nbsp;") + "</th>\n");
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return 0;
	}
	
	protected Object getFieldValue(Object obj, String field) {
		Object val = null;
		if (Map.class.isAssignableFrom(obj.getClass())) {
			@SuppressWarnings("unchecked")
			Map<String, ?> datas = (Map<String, ?>) obj;
			val = datas.get(field);
		} else {
			try {
				val = ReflectUtil.getFieldValue(obj, field);
			} catch (Exception e) {}
		}
		return val == null ? "" : val;
	}
	
	protected Object format(String type, String format, Object value) {
		if (StringHelpers.isNull(value) || StringHelpers.isNull(format)) return value;
		try {
			if ("number".equals(type)) {
				numfmt.applyPattern(format);
				return numfmt.format(value);
			} else if ("date".equals(type)) {
				datefmt.applyPattern(format);
				return datefmt.format(value);
			}
			return value;
		} catch (Exception e) {
			return value;
		}
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = PropertiesUtil.getAppValue(header, header);
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getCssName() {
		return cssName;
	}

	public void setCssName(String cssName) {
		this.cssName = cssName;
	}
	
	public String getCheckbox() {
		return checkbox;
	}

	public void setCheckbox(String checkbox) {
		this.checkbox = checkbox;
	}
	
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getConverter() {
		return converter;
	}

	public void setConverter(String converter) {
		this.converter = converter;
	}
}
