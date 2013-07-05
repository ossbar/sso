package com.hihframework.core.customtaglibs.pagetag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.*;

/*****************************************************************************
 * <p>Title: 这个包用于分页处理</p>
 * <p>Description: </p>
 * <p> Copyright: Copyright (c) 2013 </p>
 * <p> Company:hihsoft.co.,ltd </p>
 * @author hihsoft.co.,ltd
 * @version 1.0
 * */
public abstract class TagBase extends BodyTagSupport {
	protected BodyContent bodyOut;

	protected PageContext pageContext;

	protected Tag parent;

	public TagBase() {
	}

	public void setParent(Tag parent) {
		this.parent = parent;
	}

	public void setBodyContent(BodyContent bodyOut) {
		this.bodyOut = bodyOut;
	}

	public void setPageContext(PageContext pageContext) {
		this.pageContext = pageContext;
	}

	public Tag getParent() {
		return parent;
	}

	public int doStartTag() throws JspException {
		return 0;
	}

	public int doEndTag() throws JspException {
		return 6;
	}

	public void doInitBody() throws JspException {
	}

	public int dojavahiherBody() throws JspException {
		return 0;
	}

	public void release() {
		bodyOut = null;
		pageContext = null;
		parent = null;
	}
}
