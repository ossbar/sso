/**
 * Copyright (c) 2013-2015 www.javahih.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.hihframework.core.utils;

import java.util.HashMap;
import java.util.Map;

public class ServiceFactory {
	
	private static Map<String, Object> factory = new HashMap<String, Object>();
	
	public static void addService(String key, Object service) {
		factory.put(key, service);
	}
	
	public static <T> T getService(String key, Class<T> clazz) {
		Object obj = factory.get(key);
		if (obj != null) {
			return clazz.cast(obj);
		}
		return null;
	}

}
