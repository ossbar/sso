package com.hihsoft.baseclass.service;

import org.dom4j.Document;

import com.hihframework.exception.ServiceException;
public interface InitFrameworkService extends BaseService {
	public void saveOrUpdateInitFramework() throws ServiceException;

	public boolean getIsFrameworkInitialized() throws ServiceException;

	public void saveSysInitializeFactory(Document doc) throws ServiceException;
}
