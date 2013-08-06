/**
 * Copyright (c) 2013-2015 www.javahih.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.hihframework.core.customtaglibs.formtag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.hihframework.core.utils.BeanUtils;
import com.hihframework.core.utils.StringHelpers;


/**
 * <p> Title:修改jodd进行扩展 </p>
 * <p> Description:简化界面传值与后台实体间的转化</p>
 * <p> Copyright: Copyright (c) 2013 </p>
 * <p> Company:hihsoft.co.,ltd </p>
 *
 * @author hihsoft.co.,ltd
 * @version 1.0
 */
public class FormTag extends BodyTagSupport {
	private static final long serialVersionUID = 8654099979375676520L;
	private String beanName = null;
	private String scope = null;
	private Object bean = null;
	private boolean fromRequest = false;

	/**
	 * Sets bean names with value of the "bean" attribute.
	 *
	 * @param v bean names
	 */
	public void setBean(final String v) {
		beanName = v;
	}

	/**
	 * Gets bean names.
	 *
	 * @return bean names
	 */
	public String getBean() {
		return beanName;
	}

	/**
	 * Sets the value of "scope" attribute, that represent beans scope.
	 *
	 * @param v
	 */
	public void setScope(final String v) {
		scope = v;
	}

	/**
	 * Return value of the "scope" attribute
	 *
	 * @return bean scopes
	 */
	public String getScope() {
		return scope;
	}

	/**
	 * Copies properties of all specified bean into one map.
	 *
	 * @return EVAL_BODY_AGAIN
	 */
	@Override
	public int doStartTag() {
		if ("request".equals(beanName))
			fromRequest = true;
		if (beanName == null || beanName.equalsIgnoreCase("")) {
			return SKIP_BODY;
		}
		if (StringHelpers.isNull(scope)) {
			scope = "request";
		}
		if (!fromRequest) {
			final HttpServletRequest request = (HttpServletRequest) pageContext
					.getRequest();
			final HttpSession session = pageContext.getSession();

			if ((scope.length() == 0) || (scope.equals("page"))) {
				bean = pageContext.getAttribute(beanName);
			} else if (scope.equals("request")) {
				bean = request.getAttribute(beanName);
			} else if (scope.equals("session")) {
				bean = session.getAttribute(beanName);
			}
		}
		return EVAL_BODY_AGAIN;
	}

	/**
	 * Performs smart form population.
	 *
	 * @return SKIP_BODY
	 */
	@Override
	public int doAfterBody() {
		final BodyContent body = getBodyContent();

		try {
			final JspWriter out = body.getEnclosingWriter();
			String bodytext = body.getString();

			if (bean != null || fromRequest == true) {
				bodytext = populateForm(bodytext, bean);
			}

			out.print(bodytext);
		} catch (final Exception ex) {
			ex.printStackTrace();
		}

		return SKIP_BODY;
	}

	/**
	 * End of tag.
	 *
	 * @return EVAL_PAGE
	 */
	@Override
	public int doEndTag() {
		return EVAL_PAGE;
	}

	private String populateForm(final String html, final Object bean) {
		int i = 0;
		int s = 0;
		final StringBuffer result = new StringBuffer(html.length());
		String currentSelectName = null;
		final HttpServletRequest request = (HttpServletRequest) pageContext
				.getRequest();

		while (true) {
			// find starting tag
			i = html.indexOf('<', s);

			if (i == -1) {
				result.append(html.substring(s));

				break; // input tag not found
			}

			result.append(html.substring(s, i)); // tag found, all before tag is
													// stored
			s = i;

			// find closing tag
			i = html.indexOf('>', i);

			if (i == -1) {
				result.append(html.substring(s));

				break; // closing tag not found
			}

			i++;

			// match tags
			String tag = html.substring(s, i);
			final String tagName = HtmlUtil.getTagName(tag);

			if (tagName.equalsIgnoreCase("input") == true) {
				String tagType = HtmlUtil.getAttribute(tag, "type");
				if (tagType != null) {
					final String name = HtmlUtil.getAttribute(tag, "name");
					String value = null;
					try {
						if (!fromRequest)
							value = BeanUtils.getProperty(bean, name);
						else
							value = request.getParameter(name);
					} catch (final Exception e) {

					}
					if (value != null) {
						tagType = tagType.toLowerCase();

						if (tagType.equals("text")) {
							tag = HtmlUtil.addAttribute(tag, "value", value);
						}

						if (tagType.equals("hidden")) {
							tag = HtmlUtil.addAttribute(tag, "value", value);
						}

						if (tagType.equals("image")) {
							tag = HtmlUtil.addAttribute(tag, "value", value);
						}

						if (tagType.equals("password")) {
							tag = HtmlUtil.addAttribute(tag, "value", value);
						}

						if (tagType.equals("checkbox")) {
							String tagValue = HtmlUtil.getAttribute(tag,
									"value");

							if (tagValue == null) {
								tagValue = "true";
							}

							if (tagValue.equals(value)) {
								tag = HtmlUtil.addAttribute(tag, "checked");
							}
						}

						if (tagType.equals("radio")) {
							final String tagValue = HtmlUtil.getAttribute(tag,
									"value");

							if (tagValue != null) {
								if (tagValue.equals(value)) {
									tag = HtmlUtil.addAttribute(tag, "checked");
								}
							}
						}
					}
				}
			} else if (tagName.equalsIgnoreCase("textarea") == true) {
				final String name = HtmlUtil.getAttribute(tag, "name");
				String value = null;
				try {
					if (!fromRequest)
						value = BeanUtils.getProperty(bean, name);
					else
						value = request.getParameter(name);
				} catch (final Exception e) {

				}
				if (value != null) {
					tag += HtmlEncoder.encode(value);
				}
			} else if (tagName.equalsIgnoreCase("select") == true) {
				currentSelectName = HtmlUtil.getAttribute(tag, "name");
			} else if (tagName.equalsIgnoreCase("/select") == true) {
				currentSelectName = null;
			} else if (tagName.equalsIgnoreCase("option") == true) {
				if (currentSelectName != null) {
					final String tagValue = HtmlUtil.getAttribute(tag, "value");

					String value = null;
					try {

						if (!fromRequest)
							value = BeanUtils.getProperty(bean,
									currentSelectName);
						else
							value = request.getParameter(currentSelectName);
					} catch (final Exception e) {

					}
					if (tagValue != null) {
						if (value != null) {
							if (value.equals(tagValue)) {
								tag = HtmlUtil.addAttribute(tag, "selected");
							}
						}
					}
				}
			}
			result.append(tag);
			s = i;
		}

		return result.toString();
	}
}
