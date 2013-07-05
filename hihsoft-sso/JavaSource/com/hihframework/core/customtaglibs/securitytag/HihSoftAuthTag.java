/**
 * Copyright (c) 2013-2015 www.javahih.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.hihframework.core.customtaglibs.securitytag;

import java.util.Map;

import javax.servlet.jsp.JspWriter;

import org.springframework.web.servlet.tags.RequestContextAwareTag;

import com.hihframework.core.customtaglibs.formtag.HtmlUtil;
import com.hihframework.osplugins.properties.PropertiesUtil;
import com.hihsoft.baseclass.constants.Consts;
/**
 * <p> Title:通过自定义按钮标签来控制系统更细的按钮级权限控制 </p>
 * <p> Description:目前支持按钮、链接，暂不支持标签页权限的控制</p>
 * <p> Copyright: Copyright (c) 2013 </p>
 * <p> Company:hihsoft.co.,ltd </p>
 *
 * @author hihsoft.co.,ltd
 * @version 1.0
 */
public class HihSoftAuthTag extends RequestContextAwareTag {

	private static final long serialVersionUID = -7112194947977479337L;
	private String module;
	private String operate;
	private String value;
	private String type;
	private String style;
	private String cssClass;
	private String id;
	private String iconCls;
	
	private static final String TYPE_BTN = "button";
	private static final String TYPE_DIVBTN = "divbutton";
	private static final String TYPE_LINK = "link";

	@Override
	protected int doStartTagInternal() throws Exception {
		JspWriter out = pageContext.getOut();
		if (!hasPermission()) return SKIP_BODY;
		if (type == null) type = TYPE_DIVBTN;
		value = PropertiesUtil.getValue(value, value);
		if (value == null) value = "";
		String cls = "easyui-linkbutton";
		String tag = "<a>";
		tag = attr(tag, "class", cls + " " + val(cssClass));
		tag = attr(tag, "iconCls", val(iconCls));
		tag = attr(tag, "plain", "true");
		tag = attr(tag, "id", val(id));
//		tag = attr(tag, "style", val(style));
		tag = tag + value + "</a>";
		out.write(tag);
		
		return EVAL_PAGE;
	}
	private String attr(String tag, String attr, String value) {
		return HtmlUtil.addAttribute(tag, attr, value);
	}
	@SuppressWarnings("unchecked")
	private boolean hasPermission() {
		boolean flag = false;
		if (module == null || operate == null) return flag;
		Map<String, Map<String, String>> roleSet = (Map<String, Map<String, String>>) pageContext
		.getSession().getAttribute(Consts.USER_PRIVILEGES_DATA);
		if (roleSet == null) return flag;
		Map<String, String> map = roleSet.get(module);
		if (map != null && map.get(operate) != null) {
			flag = true;
		}
		return flag;
	}
	
	private String val(String val) {
		return val == null ? "" : val;
	}
	
	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getCssClass() {
		return cssClass;
	}

	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	
	public String getIconCls() {
		return iconCls;
	}
}
