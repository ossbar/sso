/**
 * Copyright (c) 2013-2015 www.javahih.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.hihframework.core.utils;

import java.text.DecimalFormat;
import java.text.ParseException;

import org.apache.log4j.*;
import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.lang.StringUtils;

/**
 * <p> Title:货币转换辅助类</p>
 * <p> Description:格式成特定格式：100，200.00</p>
 * <p> Copyright: Copyright (c) 2013 </p>
 * <p> Company:hihsoft.co.,ltd</p>
 *  @author hihsoft.co.,ltd
 * @version 1.0
 */
public class CurrencyConverter {
	protected static final Logger log = Logger
			.getLogger(CurrencyConverter.class);

	protected final DecimalFormat formatter = new DecimalFormat("###,###.00");

	/**
	 * Convert a String to a Double and a Double to a String
	 *
	 * @param type the class type to output
	 * @param value the object to convert
	 * @return object the converted object (Double or String)
	 */
	public final Object convert(final Class type, final Object value) {
		// for a null value, return null
		if (value == null) {
			return null;
		} else {
			if (value instanceof String) {
				if (log.isDebugEnabled()) {
					log.debug("value (" + value + ") instance of String");
				}

				try {
					if (StringUtils.isNotEmpty(String.valueOf(value))) {
						return null;
					}

					if (log.isDebugEnabled()) {
						log.debug("converting '" + value + "' to a decimal");
					}

					//formatter.setDecimalSeparatorAlwaysShown(true);
					Number num = formatter.parse(String.valueOf(value));

					return new Double(num.doubleValue());
				} catch (ParseException pe) {
					pe.printStackTrace();
				}
			} else if (value instanceof Double) {
				if (log.isDebugEnabled()) {
					log.debug("value (" + value + ") instance of Double");
					log.debug("returning double: " + formatter.format(value));
				}

				return formatter.format(value);
			}
		}

		throw new ConversionException("Could not convert " + value + " to "
				+ type.getName() + "!");
	}
}
