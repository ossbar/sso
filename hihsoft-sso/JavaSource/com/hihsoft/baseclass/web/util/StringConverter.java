package com.hihsoft.baseclass.web.util;

import org.apache.commons.beanutils.Converter;

public class StringConverter implements Converter {

	public StringConverter() {
	}

	public Object convert(Class type, Object value) {
		if (value == null || "".equals(value.toString())) {
			return (String) null;
		} else {
			return value.toString();
		}
	}

}
