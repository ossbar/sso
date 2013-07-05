package com.hihframework.core.customtaglibs.util;

import javax.servlet.http.HttpServletRequest;
/**
 * <p> Title:封装参数传递的辅助类 </p>
 * <p> Description:</p>
 * <p> Copyright: Copyright (c) 2013 </p>
 * <p> Company:hihsoft.co.,ltd </p>
 *
 * @author hihsoft.co.,ltd
 * @version 1.0
 */
public class ParamUtil {

	public ParamUtil() {
	}

	public static String getString(HttpServletRequest request, String paramName) {
		String temp = request.getParameter(paramName);
		if (temp != null && !temp.equals("")) {
			return temp;
		} else {
			return null;
		}
	}

	public static String getString(HttpServletRequest request,
			String paramName, String defaultString) {
		String temp = getString(request, paramName);
		if (temp == null) {
			temp = defaultString;
		}
		return temp;
	}

	public static int getInt(HttpServletRequest request, String paramName)
			throws NumberFormatException {
		return Integer.parseInt(getString(request, paramName));
	}

	public static int getInt(HttpServletRequest request, String paramName,
			int defaultInt) {
		try {
			String temp = getString(request, paramName);
			if (temp == null) {
				return defaultInt;
			} else {
				return Integer.parseInt(temp);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static long getLong(HttpServletRequest request, String paramName)
			throws NumberFormatException {
		return Long.parseLong(getString(request, paramName));
	}

	public static long getLong(HttpServletRequest request, String paramName,
			int defaultInt) {
		try {
			String temp = getString(request, paramName);
			if (temp == null) {
				return defaultInt;
			} else {
				return Long.parseLong(temp);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
