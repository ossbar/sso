package com.hihframework.core.customtaglibs.tabletag;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.hihframework.core.utils.StringHelpers;
/**
 * <p> Title:数据表格标签 </p>
 * <p> Description:</p>
 * <p> Copyright: Copyright (c) 2013 </p>
 * <p> Company:hihsoft.co.,ltd </p>
 *
 * @author hihsoft.co.,ltd
 * @version 1.0
 */
public class TableTag extends BodyTagSupport {
	
	private static final long serialVersionUID = 9112012774327637585L;
	
	private List<?> items;
	private String var;
	private String cssName;
	private String style;
	
	private Iterator<?> it;
	private boolean headerRendered;
	private int curIndex;
	private StringBuffer buffer;
	private int columnNum;
	
	public int getCurIndex() {
		return curIndex;
	}
	
	public void addColumn() {
		columnNum++;
	}
	
	public boolean isHeaderRendered() {
		return headerRendered;
	}
	public void setHeaderRendered(boolean headerRendered) {
		this.headerRendered = headerRendered;
	}
	
	public StringBuffer getBuffer() {
		return buffer;
	}
	
	@Override
	public int doStartTag() throws JspException {
		buffer = new StringBuffer("<table class=\""+(StringHelpers.notNull(cssName) ? cssName : "data-grid")+"\" style=\""+(StringHelpers.notNull(style) ? style : "")+"\" cellspacing=\"0\" cellpadding=\"0\">\n");
		if (items == null) {
			return SKIP_BODY;
		}
		if (StringHelpers.isNull(var)) var = "bean";
		it = items.iterator();
		buffer.append("\t<thead><tr class=\"data-grid-header\">\n");
		return EVAL_BODY_BUFFERED;
	}
	
	@Override
	public int doAfterBody() throws JspException {
		buffer.append("\t</tr>\n");
		if (!headerRendered) buffer.append("</thead><tbody>");
		if (it.hasNext()) {
			buffer.append("\t<tr>\n");
			Object bean = it.next();
			pageContext.setAttribute(var, bean);
			pageContext.setAttribute("curIndex", curIndex);
			curIndex++;
			headerRendered = true;
			return EVAL_BODY_BUFFERED;
		} else {
			if (items.size() == 0) {
				buffer.append("\n<tr><td colspan=\"" +columnNum+ "\" align='center'>没有找到相关记录</td></tr>");
			}
			return SKIP_BODY;
		}
	}
	
	@Override
	public int doEndTag() throws JspException {
		buffer.append("\t</tbody>\n</table>");
		try {
			pageContext.getOut().write(buffer.toString());
			items = null;
			curIndex = 0;
			columnNum = 0;
			var = null;
			cssName = null;
			style = null;
			headerRendered = false;
		} catch (Exception e) {e.printStackTrace();}
		return super.doEndTag();
	}
	
	public Object getItems() {
		return items;
	}

	public void setItems(Object items) {
//		try {
//			this.items = ExpressionEvaluatorManager.evaluate("items", items.toString(), Object.class, this, pageContext);
//		} catch (JspException e) {
//		}
		this.items = (List<?>) items;
		if (this.items == null) this.items = new ArrayList<Map<String, ?>>();
	}
	
	public void setVar(String var) {
		this.var = var;
	}
	
	public String getVar() {
		return var;
	}

	public String getCssName() {
		return cssName;
	}

	public void setCssName(String cssName) {
		this.cssName = cssName;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}
	
}
