package com.hihframework.core.customtaglibs.pagetag;

import java.io.IOException;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;

import com.hihframework.core.customtaglibs.util.HTMLEncoder;
import com.hihsoft.baseclass.constants.Consts;

public class PagesRollerTag extends TagBase {
	int sumPages;
	int count;

	public PagesRollerTag() {
	}

	public void setSumPages(int sumPages) {
		this.sumPages = sumPages;
	}

	public void setSumPages(String sumPages) throws NumberFormatException {
		this.sumPages = Integer.parseInt(sumPages);
	}

	public int doStartTag() throws JspException {
		Hashtable paramTable = new Hashtable();
		HttpServletRequest request = (HttpServletRequest) pageContext
				.getRequest();
		String action = (String) request.getAttribute("action");
		paramTable.put("action", action);
		paramTable.put("method", request.getParameter("method"));
		pageContext.setAttribute("url",
				String.valueOf(HTMLEncoder.encodeUrlParameters(paramTable)));
		String scount = request.getParameter(Consts.SYS_PAGE_NO);

		try {
			count = Integer.parseInt(scount);
		} catch (NumberFormatException e) {
			count = 1;
		}
		pageContext.setAttribute(Consts.SYS_PAGE_NO,
				"".concat(String.valueOf(String.valueOf(count))));
		pageContext.setAttribute("sumpages",
				"".concat(String.valueOf(String.valueOf(sumPages))));
		paramTable.put(Consts.SYS_PAGE_NO, "1");
		// pageContext.setAttribute("firsturl",
		// String.valueOf(request.getRequestURI()) +
		// String.valueOf(HTMLEncoder.encodeUrlParameters(paramTable)));
		pageContext.setAttribute("firsturl",
				String.valueOf(HTMLEncoder.encodeUrlParameters(paramTable)));

		paramTable.put(Consts.SYS_PAGE_NO,
				"".concat(String.valueOf(String.valueOf(sumPages))));
		// pageContext.setAttribute("lasturl",
		// String.valueOf(request.getRequestURI()) +
		// String.valueOf(HTMLEncoder.encodeUrlParameters(paramTable)));
		pageContext.setAttribute("lasturl",
				String.valueOf(HTMLEncoder.encodeUrlParameters(paramTable)));

		if (count > 1) {
			pageContext.setAttribute("hasprev", "true");
			paramTable.put(Consts.SYS_PAGE_NO,
					String.valueOf(String.valueOf(count - 1)).concat(""));
			// pageContext.setAttribute("prevurl",
			// String.valueOf(request.getRequestURI()) +
			// String.valueOf(HTMLEncoder.encodeUrlParameters(paramTable)));
			pageContext
					.setAttribute("prevurl", String.valueOf(HTMLEncoder
							.encodeUrlParameters(paramTable)));

		} else {
			pageContext.setAttribute("hasprev", "false");
			pageContext.setAttribute("prevurl", "#");
		}
		if (count < sumPages) {
			paramTable.put(Consts.SYS_PAGE_NO, String.valueOf(String
					.valueOf((new StringBuffer(String.valueOf(String
							.valueOf(count + 1)))).append(""))));
			pageContext.setAttribute("hasnext", "true");
			// pageContext.setAttribute("nexturl",
			// String.valueOf(request.getRequestURI()) +
			// String.valueOf(HTMLEncoder.encodeUrlParameters(paramTable)));
			pageContext
					.setAttribute("nexturl", String.valueOf(HTMLEncoder
							.encodeUrlParameters(paramTable)));

		} else {
			pageContext.setAttribute("hasnext", "false");
			pageContext.setAttribute("nexturl", "#");
		}
		return 2;
	}

	public int dojavahiherBody() throws JspException {
		try {
			bodyOut.writeOut(bodyOut.getEnclosingWriter());
			int i = 0;
			return i;
		} catch (IOException ex) {
			throw new JspTagException(ex.toString());
		}
	}
}
