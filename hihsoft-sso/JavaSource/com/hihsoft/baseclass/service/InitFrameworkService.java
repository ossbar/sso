/**
 * Copyright (c) 2013-2015 www.javahih.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.hihsoft.baseclass.service;

import org.dom4j.Document;

import com.hihframework.exception.ServiceException;
public interface InitFrameworkService extends BaseService {
	public void saveOrUpdateInitFramework() throws ServiceException;

	public boolean getIsFrameworkInitialized() throws ServiceException;

	public void saveSysInitializeFactory(Document doc) throws ServiceException;
}
