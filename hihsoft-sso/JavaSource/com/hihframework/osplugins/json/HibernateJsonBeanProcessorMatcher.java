/**
 * Copyright (c) 2013-2015 www.javahih.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.hihframework.osplugins.json;

import java.util.Set;

import net.sf.json.processors.JsonBeanProcessorMatcher;

import org.apache.log4j.Logger;

/**
 * @author hihsoft.co.,ltd
 *
 */
public class HibernateJsonBeanProcessorMatcher extends JsonBeanProcessorMatcher {

	private static Logger log = Logger
			.getLogger(HibernateJsonBeanProcessorMatcher.class);

	@Override
	public Object getMatch(Class target, Set set) {
		if (target.getName().contains("$$EnhancerByCGLIB$$")) {
			log.warn("Found Lazy-References in Hibernate object "
					+ target.getName());
			return org.hibernate.proxy.HibernateProxy.class;
		}
		return DEFAULT.getMatch(target, set);
	}

}
